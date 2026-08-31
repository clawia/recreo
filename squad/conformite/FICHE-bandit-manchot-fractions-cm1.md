# Fiche de conformite - bandit-manchot-fractions-cm1

- **Mecanique** : bandit manchot (mecanique generique, partagee avec les autres jeux du catalogue).
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/cm1-fractions.yaml`, `type:
  question`, **24 items**, six par forme, story `S24-FRA` (`DEC-0059`). Ecriture de `marc-dev`,
  matiere premiere `contenu-repris/fractions/cm1-compare.yml` **refusee** a s23 sur sa seconde
  moitie.
- **Entree de catalogue** : `bandit-manchot-fractions-cm1`, titre *"Les fractions simples"*,
  consigne *"Lis bien la fraction, puis choisis la réponse."*, 10 manches, niveau CM1.
- **Conditions applicables** : `FRA-1` a `FRA-9` (2026-08-24), `FRA-10` (2026-08-30), et
  **`FRA-5` famille (e) avec `FRA-5e-1` a `FRA-5e-3`** (2026-08-31,
  `ARBITRAGE-FRA-la-forme-d-contre-le-seuil-minimal-de-rang.md`).
- **Statut global** : **Done pedagogique prononce le 2026-08-31, sous une reserve non bloquante.**

## Competences visees

Mathematiques CM1 - **moitie *"notion de fraction simple"* seule**. `FRA-9` exige que la matrice
porte la mention exacte : **"moitie 'notion de fraction simple' seule ; la moitie 'reperage sur une
droite numerique' reste non couverte"**. **Cette mention est une condition, pas une remarque.** Mon
avis du 2026-08-14 sur le reperage est inchange : il exige une droite reellement dessinee et graduee,
et je prefere une case vide a une case fausse.

---

## Bloc de validation pedagogique - nadia-prof UNIQUEMENT

### Verdict du 2026-08-31 (s24) - `S24-FRA`

## Verdict : **VALIDE SOUS RESERVE** - une reserve, non bloquante

Done pedagogique ouvert. `camille-po` peut passer la ligne de matrice a `couvert-valide`, **avec la
mention exacte de `FRA-9`**, et fera de la reserve 1 une story d'un sprint suivant.

### 1. Ce que j'ai fait

**J'ai joue**, depuis le jar servi construit sur l'arbre de cloture, sur le JDK 21 de la toolchain
declaree. Le jeu s'ouvre depuis la liste du niveau CM1. J'ai joue plusieurs parties et **repondu
faux volontairement**, y compris en repressant un bouton deja barre pour atteindre la revelation.

**Ce que j'ai vu.** Sur *"Dans 2/5, en combien de parts égales le tout est-il partagé ?"* les trois
propositions etaient **5, 6, 7** - la bonne reponse est **la plus petite**, ce qui n'existait sur
aucun item de forme (d) avant aujourd'hui. Sur *"Dans 7/10..."* : **7, 9, 10**, la bonne est la plus
grande. Apres trois echecs, la revelation dit *"C'est trois quarts. Appuie sur trois quarts."* :
**un enfant bloque avance.**

**Puis j'ai lu les 24 items, tous.**

### 2. La resolution du 2026-08-31, verifiee sur le paquet livre

`marc-dev` a reecrit **deux** des six items de forme (d) et non les six. **Sa lecture est juste et je
l'ai verifiee moi-meme**, item par item : les quatre autres portaient deja la famille (a) et
couvraient deja deux des trois rangs ; seuls les items qui devaient rendre la bonne reponse **la
plus petite** demandaient la famille (e).

*Methode : lecture du fichier YAML servi, valeur de chaque proposition, rang de la bonne reponse
parmi les trois valeurs triees. Population : les **6 items a trois propositions numeriques**, qui
sont exactement les items de forme (d) - les 18 autres sont hors du domaine de la propriete de rang.
Aucun build.*

| Item de forme (d) | Reponse | Propositions | Familles declarees | Rang |
|---|---|---|---|---|
| 3/8 | 8 | 8, 9, 11 | (b) `d+1`, **(e)** | la plus petite |
| 2/5 | 5 | 5, 6, 7 | (b) `d+1`, **(e)** | la plus petite |
| 3/4 | 4 | 3, 4, 5 | (a), (b) `d+1` | celle du milieu |
| 1/9 | 9 | 1, 9, 10 | (a), (b) `d+1` | celle du milieu |
| 1/6 | 6 | 1, 5, 6 | (a), (b) `d-1` | la plus grande |
| 7/10 | 10 | 7, 9, 10 | (a), (b) `d-1` | la plus grande |

- **Rangs : 33,3 / 33,3 / 33,3 %.** Plancher minimal de 10 % tenu, plafond de majorite de 50 % tenu.
- **`FRA-5e-1`** - famille (a) sur **4 items sur 6, 66,7 %**, au-dessus du plancher de la moitie.
- **`FRA-5e-2`** - les deux items portant (b) et (e) ont un numerateur d'au moins 2 : `3/8` et `2/5`.
  Aucune collision avec `FRA-6`.
- **`FRA-5e-3`** - la famille (e) n'apparait sur aucune des formes (a), (b), (c).
- **Chaque distracteur est recalculable depuis sa famille declaree : verifie sur les 12 distracteurs
  des six items, zero ecart.**

**Ces chiffres sont exactement ceux de ma preuve de satisfaisabilite du 2026-08-31.** Je les ai
refaits sur le paquet livre au lieu de les reprendre : ils ne sont pas confirmes parce qu'ils
coincident avec les miens, ils sont mesures.

### 3. Mes conditions, une par une

| Condition | Verdict | Mesure ou lecture |
|---|---|---|
| **`FRA-1`** - fractions enoncees et bonnes reponses inferieures a 1, denominateur de 2 a 10 | **tenue** | Les 24 fractions. Aucune hors bornes. *La clause ne porte pas sur les distracteurs, et `11` en distracteur est licite comme je l'ai ecrit le 2026-08-30* |
| **`FRA-2`** - quatre formes admises, et seulement celles-la | **tenue** | Aucune comparaison de deux fractions, aucun calcul, aucune equivalence |
| **`FRA-3`** - au moins trois items par forme | **tenue** | **Six par forme**, 24 en tout |
| **`FRA-4`** - unites fractionnaires servies | **tenue** | **10 items** a numerateur 1, exige : 8 |
| **`FRA-5`** + famille (e) | **tenue** | Section 2 |
| **`FRA-6`** - trois propositions distinctes | **tenue** | Aucun item en defaut |
| **`FRA-7`** - enonce au plus 25 mots, aucun mot de plus de onze lettres | **tenue** | Le plus long : **18 mots**. Mot le plus long : **9 lettres** |
| **`FRA-8`** - l'indice rappelle ce que dit le denominateur, jamais la reponse ; douze mots au plus | **tenue** | Le plus long : **11 mots**. Aucun indice ne contient la bonne reponse. **Aucune situation de partage ne suppose un gouter, un anniversaire, une part de gateau ou un objet possede** : bande de papier, ruban, ficelle, longueur, corde, piste |
| **`FRA-9`** - au moins vingt items, mention exacte sur la matrice | **tenue pour les items** ; la mention est **due a `camille-po`** | 24 items |
| **`FRA-10`** - decalage alternatif en cas de collision | **tenue** | Aucune collision residuelle |

### 4. Reserve 1 - deux indices parlent du **nom** de la part la ou la reponse est un **nombre**

**Le fait, vu en jouant avant d'etre lu dans le fichier.** Le paquet emploie six indices qui tournent
a l'identique sur les quatre formes. Deux d'entre eux disent que le nombre du bas donne **le nom** de
la part :

- item 21, *"Dans 3/4, en combien de parts égales le tout est-il partagé ?"*, indice *"Le nombre du
  bas donne le nom de la part."* ;
- item 23, *"Dans 7/10, en combien de parts égales le tout est-il partagé ?"*, indice *"Le nom de la
  part vient du nombre du bas."*

**Ce qui accroche.** Sur la forme (d), la reponse attendue est un **nombre** - `4`, `10` - et non une
denomination. Un enfant bloque, au moment ou il a le plus besoin d'etre conduit, lit une phrase qui
parle de nommer alors qu'on lui demande de compter. **Il peut chercher *"dixièmes"* parmi 7, 9 et 10
et ne rien trouver.** Les quatre autres indices - *"il compte les parts égales"*, *"en combien de
parts on partage"* - sont, eux, exactement sur la cible.

**Pourquoi c'est une reserve et non un refus.** `FRA-8` demande que l'indice **rappelle ce que dit le
denominateur** et ne nomme jamais la reponse : les six indices le font, y compris les deux en cause,
qui pointent bien vers le nombre du bas. **Ma condition n'a jamais exige que l'indice soit accorde a
la forme de l'item**, et l'exiger aujourd'hui contre un lot livre serait deplacer mes poteaux. Le
defaut ne bloque pas : l'indice dirige l'attention au bon endroit, avec un mot qui ne correspond pas
a la question.

**Ce que je demande** : que les indices des items de forme (d) parlent de **compter** les parts et
non de les **nommer**. Deux items sont concernes, et quatre indices deja ecrits dans ce paquet
conviennent tels quels. **Je ne redige pas le texte** : je pose le resultat.

**Et une condition neuve, datee du 2026-08-31, non retroactive** :

> **`FRA-11`** - Sur un paquet a plusieurs formes d'items, **l'indice est accorde a la forme de son
> item** : il conduit vers ce que cette forme-la demande. Un indice tournant, distribue sans regard
> a la forme, ne tient pas cette condition.

Elle **ne s'applique pas a ce lot** et vaut pour les paquets a formes multiples suivants.

### 5. Contenu et niveau

- **Exactitude** : les 24 bonnes reponses sont justes ; les 48 distracteurs sont tous des erreurs
  reelles et aucun n'est une seconde reponse acceptable. **Aucune erreur factuelle.**
- **Plausibilite des distracteurs** - la propriete que j'ai nommee comme restant dans ma seule main
  le 2026-08-31 : la famille (e) produit `11` sur `3/8` et `7` sur `2/5`. **Ce sont des nombres qu'un
  enfant de CM1 propose reellement** quand il additionne les deux termes ou lit le denominateur comme
  les parts restantes. **Aucun distracteur absurde**, aucun qui transforme l'exercice en devinette.
- **Lisibilite CM1** : enonces de six a dix-huit mots, une seule question.
- **Traitement de l'erreur** : l'indice conduit sans donner, la revelation debloque.
- **Biais et ton** : aucun prenom, aucune situation domestique, aucun objet possede. Les supports
  sont neutres - bande de papier, ruban, ficelle, corde, piste. **C'est exactement ce que `FRA-8`
  demandait, et c'est tenu.**

### 6. Sur quelles proprietes je reste la seule garde

- **`FRA-5e`** - la plausibilite de `n + d` item par item. Aucun test ne la dira.
- **`FRA-8`** - ce qu'une situation de partage presuppose, et ce que dit l'indice.
- **`FRA-11`** desormais, pour les paquets suivants.
- **Le jugement de niveau** sur les 24 items.

### 7. Ce que ce verdict fait, et ce qu'il ne fait pas

- Il **ouvre le Done pedagogique** sur `bandit-manchot-fractions-cm1`, avec **une** reserve nommee.
- Il **exige la mention exacte de `FRA-9`** sur la ligne de matrice. Sans elle, la ligne dirait plus
  que ce que le jeu sert.
- Il **ne rouvre pas** le refus de s23 sur la moitie *"reperage sur une droite numerique"*.
- Il **n'est pas fonde sur la CI.**
- Il **ne dit rien** du score de fin de partie, sous la reserve 5 elargie et tranche par `DEC-0061`.
  **Je l'ai revu en jouant ce jeu** : sur un item a trois propositions, deux erreurs epuisent les
  distracteurs et le troisieme appui est compte comme une reussite. Ce n'est pas un defaut de ce
  contenu.

---

### Verdict du 2026-08-31, second passage (s24) - apres le correctif de `marc-dev`

## Verdict : **VALIDE** - la reserve 1 est **levee**

**Le verdict du premier passage n'est pas efface**, ni sa reserve. Ils restent lisibles avec leur
date.

*Arbre joue : ma branche `s24-nadia-2`, qui reunit les trois correctifs de ce second passage.
Empreinte du fichier audite : `9bd75dafd99703a78aa867df3b5e858ae5ac50b8`.*

### 1. La reserve 1 - ce que je demandais, et ce que j'ai obtenu

**Ce que je demandais** : *"que les indices des items de forme (d) parlent de compter les parts et
non de les nommer"*, sur les deux items que j'avais nommes.

**Les six indices de la forme (d), lus dans le fichier livre :**

| Fraction | Reponse | Indice |
|---|---|---|
| 3/8 | 8 | Le nombre du bas dit en combien de parts on partage. |
| 2/5 | 5 | Regarde le nombre du bas : il compte les parts egales. |
| **3/4** | **4** | **Le nombre du bas dit combien de parts il faut compter.** |
| 1/6 | 6 | Compte les parts annoncees par le nombre du bas. |
| **7/10** | **10** | **Cherche le nombre de parts, il est ecrit en bas.** |
| 1/9 | 9 | Le nombre du bas compte toutes les parts egales. |

**Les six parlent desormais de compter. Aucun ne parle de nommer.**

**Et je les ai joues**, pas seulement lus. Sur *"Dans 7/10, en combien de parts egales le tout
est-il partage ?"*, une reponse fausse fait apparaitre *"Cherche le nombre de parts, il est ecrit en
bas."* : l'indice pointe le 10 et demande de compter. **Un enfant bloque ne cherche plus une
denomination parmi 7, 9 et 10.** Meme verification sur *"Dans 3/4"*.

`FRA-8` reste tenue sur les deux textes neufs : **11 mots et 10 mots**, borne 12 ; aucun ne nomme sa
reponse.

### 2. Ce que le correctif ne touche pas - verifie champ par champ, pas suppose

Le fichier declare que rien d'autre n'a bouge. **Je ne l'ai pas cru : j'ai compare les 24 items
avant et apres, champ par champ.**

> **Deux champs modifies sur tout le paquet. Les deux sont des indices. Les deux sont sur les items
> 21 et 23, ceux que j'avais nommes.**

Aucune bonne reponse, aucun distracteur, aucun enonce, aucune fraction, aucune forme, aucune famille
declaree n'a change. Et les comptes que je verifie a chaque passage sont identiques : **24 items, six
par forme, rangs 2/2/2 sur la forme (d), famille (a) sur 4 des 6, 10 fractions de numerateur 1.**

### 3. Ce qu'il a refuse de faire, et il a eu raison

**Il n'a pas etendu la correction aux autres formes.** Les deux textes retires de la forme (d)
restent en place sur des items des formes (a), (b) et (c) - **ou la reponse *est* une denomination**,
et ou parler du nom de la part est exactement ce que la question demande.

**C'est le bon geste et c'est celui que ma reserve appelait.** Le defaut n'etait jamais dans les
textes : il etait dans leur distribution mecanique, sans regard a la forme. Corriger les textes
partout aurait remplace un desaccord par un autre.

**Il n'a ecrit aucun garde-fou pour `FRA-11`**, et son motif est juste : *"qu'un indice conduise vers
ce que sa forme demande est un jugement de sens ; un automate qui pretendrait le tenir serait
decoratif."* **Je le confirme, et c'est ma porte qui parle** : `FRA-11` reste dans ma seule main, et
je prefere une condition que je tiens a la main a une regle verte qui ne verrait rien. Ce depot a
une dette nommee pour ce genre de regle.

**Et il a trouve une faute dans son propre outil de verification** plutot que de conclure trop vite -
son controle rougissait a tort sur un mot qui contient la sous-chaine cherchee. **Verifier son
instrument avant de s'en servir est ce que je demande a chacun**, y compris a moi ; je l'ai fait deux
fois cette semaine sur mes propres constats.

### 4. Ce que ce second verdict fait, et ce qu'il ne fait pas

- Il **leve la reserve 1** et **prononce le Done pedagogique** sur `bandit-manchot-fractions-cm1`.
- Il **maintient l'exigence de `FRA-9`** : la ligne de matrice porte la mention exacte *"moitie
  'notion de fraction simple' seule ; la moitie 'reperage sur une droite numerique' reste non
  couverte"*. **Sans elle, la ligne dirait plus que ce que le jeu sert.**
- Il **n'applique `FRA-11` a aucun item de ce paquet** : elle est neuve et non retroactive, et elle
  vaut pour les paquets a formes multiples suivants.
- Il **ne rouvre pas** le refus de s23 sur la moitie *"reperage sur une droite numerique"*.
- Il **n'est pas fonde sur la CI.**
- **Sur quelles proprietes je reste la seule garde** : inchange - la plausibilite de la famille (e)
  item par item, `FRA-8`, `FRA-11`, et le jugement de niveau.
