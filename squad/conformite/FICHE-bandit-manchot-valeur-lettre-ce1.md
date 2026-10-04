# Fiche de conformité - bandit-manchot-valeur-lettre-ce1

- **Mécanique** : bandit manchot.
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/ce1-valeur-lettre.yaml`
  (24 items, type `question`), écrit par `marc-dev` (`fc6f9c16`).
- **Entrée de catalogue** : `bandit-manchot-valeur-lettre-ce1`, titre servi *"Une lettre, deux sons"*,
  consigne servie *"Lis bien, puis choisis le bon mot."*, `nombreDeManches: 10`.
- **Ligne déclarée** : `FRA-CE1-02`. **Story** : `S50-FRA-VALEUR-LETTRE`. **Décision** : `DEC-0108`.
- **Compétence programme visée** : `squad/programme/francais/competences-ce1.md`, `FRA-CE1-02`,
  *"Déterminer la valeur d'une lettre selon les lettres qui l'entourent (`c`/`ç`, `g`/`ge`/`gu`,
  `s`/`ss` entre deux voyelles)."*
- **Conditions opposables** : `VLE-CE1-1` à `VLE-CE1-3` et `TRV-CE1-1` à `TRV-CE1-7`
  (`squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md`, sections 2 et 3.2), `LON-CE1-1`,
  `LON-CE1-2`, `LON-CE1-5` (même fichier, section 1), `FER-1` et `FER-2`
  (`squad/conformite/ARBITRAGE-PREALABLE-s28-ce-qui-ferme-une-ligne-et-le-prix-des-quatre-lots.md`,
  section 2).
- **Statut global** : **REFUSÉ** le 2026-10-04 par `nadia-prof` (s50, instance A), deux motifs.
  Fiche ouverte par ce verdict : aucune fiche n'existait pour ce jeu.
- **Dernière mise à jour** : 2026-10-04.

---

## Bloc de validation pédagogique - nadia-prof UNIQUEMENT

### Verdict du 2026-10-04 (`nadia-prof`, s50, instance A) : **REFUSÉ**

**Done pédagogique non prononcé.** `FRA-CE1-02` ne passe pas à `couvert-valide`. L'incrément
retourne à `camille-po` avec les deux motifs de la section 4. Le refus ne bloque pas le sprint
suivant.

#### 1. Ce que j'ai fait

**Audit joué.** Arbre `round-two` à `655b319b`, sans worktree dédié. Jar construit par
`bash outils/mvn-jdk21.sh -B -q -pl bootstrap -am package -DskipTests` (Maven sur JDK 21.0.10) ; le
YAML de ce paquet et `catalogue.yaml` contenus dans le jar sont identiques octet pour octet à ceux de
l'arbre (`cmp`). Jar lancé sur un port dédié, servi par mon propre processus. Aucun job de la CI lancé
par moi : le Done technique est celui de `yanis-qa` (`rapport-qualite.md` de s50). **Rendu non vu au
navigateur** : j'ai joué par appels HTTP réels et lu le HTML servi.

**Trente parties complètes de dix manches** : une juste du premier coup, une avec une erreur par
manche, une en pressant trois fois le distracteur jusqu'à la révélation, vingt-sept en tirant l'une
de ces trois conduites au hasard à chaque manche. **Les 24 items tirés et joués**, de 8 à 20 fois
chacun, et chacun avec une erreur au moins (7 à 27 erreurs par item) ; les deux propositions
changent de place d'une partie à l'autre. Sur les 300 manches : les deux propositions servies sont
exactement celles du fichier, aucun distracteur n'est crédité juste, l'indice servi après une erreur
est celui du fichier. Après une erreur : *"Ce n'est pas ça. Essaie encore !"* et l'indice. Après la
troisième : *"Appuie sur la bonne réponse : rose"*. Écran de fin atteint sur les trente parties.

#### 2. Ancrage au programme

Le paquet travaille **cette** ligne, et pas la voisine `FRA-CE1-01` : l'enfant ne cherche pas un son
commun à deux mots, il décide de la lecture d'une lettre qu'on lui nomme, et la réponse dépend des
lettres qui l'entourent. Les huit items de `g` font lire `gu` (*guitare*, *guépard*) et `ge` devant
`o` (*bourgeon*, *pigeon*), ce qui est le cœur de la ligne. Les huit items de `s` opposent `s` entre
deux voyelles (*maison*, *cuisine*, *fraise*, *voisin*), `ss` (*poisson*, *tasse*) et `s` initial
(*soleil*, *salade*). **Mais `ç` n'est servi par aucun item** (motif 1).

#### 3. Les conditions

| Condition | Tenue | Ce que j'ai vérifié |
|---|---|---|
| `VLE-CE1-1` | **oui en compte, non en périmètre (motif 1)** | `c`, `g`, `s` : huit items chacun (33,3 %) ; chaque valeur bonne réponse de quatre items sur huit ; `gu`/`ge` sur quatre items de `g` sur huit. Aucun `ç` |
| `VLE-CE1-2` | **oui** | *car*/*cerise*, *gare*/*girafe*, *sel*/*rose* sur tout le paquet ; dans chacun, la lettre n'a qu'une lecture ; le mot entre guillemets n'est jamais un repère |
| `VLE-CE1-3` | **oui** | aucun `sc`, `cc`, `ch`, `gn` sur la lettre visée ; aucun `s` final ni `s` après une consonne n'est la lettre visée ; ni *second*, ni *gageure*, ni `x` |
| `TRV-CE1-1` | **oui** | `CE1`, une ligne, aucun champ à voix haute, aucun texte support |
| `TRV-CE1-2` | **oui** | deux propositions distinctes ; **une seule acceptable**, relu et joué sur les 24 items (section 5) ; bonne réponse strictement la plus longue sur 12 items sur 24, recompté en lettres : la moitié exacte, dans la borne |
| `TRV-CE1-3` | **oui** | chaque son porté par un mot que l'enfant lit ; la lettre est nommée (*"le g"*), forme de mon propre exemple ; aucune lecture régionale |
| `TRV-CE1-4` | **oui** | aucun mot exclu dans les énoncés, indices et propositions |
| `TRV-CE1-5` | **non, motif 2** | douze mots au plus, huit formes, jamais la réponse. Trois indices de `s` dirigent l'enfant vers un critère qui ne décide pas de la lecture du `s` |
| `TRV-CE1-6` | **oui** | aucun personnage, aucune situation |
| `TRV-CE1-7` | **oui** | 24 items |
| `LON-CE1-1`, `-2`, `-5` | **oui** | énoncés de dix mots, une phrase ; propositions d'un mot |
| `FER-1` | **non, motif 1** | `c`, `g`, `ge`, `gu`, `s`, `ss` sont chacun en position de bonne réponse ; `ç` ne l'est nulle part |
| `FER-2` | **oui** | 24 items servent la ligne |

#### 4. Motifs du refus

**Motif 1 - `ç` n'est pas servi (`FER-1`, `VLE-CE1-1`).** La parenthèse de la ligne énumère `c`/`ç`.
`FER-1`, écrite le 2026-09-03, exige que chaque élément énuméré par la parenthèse soit servi par au
moins un item où il est en position de bonne réponse ; un élément ne peut manquer que s'il est hors
de portée du palier, avec mention de couverture partielle. `ç` n'est pas hors de portée : c'est un
mot écrit, lu par l'enfant, comme les 24 autres. Et c'est l'élément qui fait comprendre la règle du
`c` : *garçon*, *leçon*, *maçon* montrent que devant `a`, `o`, `u` le `c` se lit comme dans
*« car »* **sauf** quand on lui met une cédille. Sans lui, le paquet enseigne la règle sans son
seul outil d'écriture.

**Ce n'est pas une barre neuve.** `VLE-CE1-1` nomme *"`c` et `ç` ensemble"* depuis le 2026-10-03,
avant le paquet, et `FER-1` le précède d'un mois.

**La question routée par `marc-dev` (`fc6f9c16`, en-tête du YAML) reçoit ici sa réponse** : non, `ç`
ne peut pas sortir du périmètre de ce paquet. Le symptôme qu'il décrit (servir *"le ç"* seul dans
l'énoncé fait rougir `AucunMotServiNeSEcarteDeSonAccentTest`) est technique : je le décris, la cause
revient à `theo-archi`. Une forme d'énoncé qui ne sert pas `ç` isolé existe et tient mes bornes :
*"Dans « garçon », le c avec une cédille se lit comme dans quel mot ?"* - **cerise** / car. Treize
mots, une phrase ; *cédille* est un mot de CE1.

**Ce qui lève** : au moins un item dont la lettre visée est `ç`, la bonne réponse étant le repère de
*cerise* ; `ç` jamais devant `e` ou `i` (`VLE-CE1-3`) ; le mot entre guillemets ne porte qu'un `c`.
*Pour que le correctif ne heurte pas une autre de mes bornes* : *cerise* est le repère le plus long,
un item de `ç` ajouté fait passer la bonne réponse strictement la plus longue à 13 items sur 25, au-dessus
de la moitié (`TRV-CE1-2`). Un item de `ç` qui **remplace** un des quatre items de `c` lu comme dans
*cerise* (*citron*, *cinéma*, *cinq*, *cigale*) garde le compte à 12 sur 24 et `VLE-CE1-1` tenue.

**Motif 2 - trois indices de `s` enseignent un critère faux (`TRV-CE1-5`).**

| Item | Mot | Indice servi | Pourquoi il trompe |
|---|---|---|---|
| 20 | *salade* | *"Pense à la lettre qui suit."* | la lettre qui suit le `s` est une voyelle dans *salade* comme dans *maison*, et les deux `s` ne se lisent pas pareil |
| 22 | *cuisine* | *"Regarde les lettres juste après."* | même défaut : ce qui fait lire *cuisine* comme *rose*, c'est la voyelle **avant** le `s` autant que celle d'après |
| 24 | *voisin* | *"Écoute si le son est doux ou dur."* | *doux* et *dur* se disent du `c` et du `g` en classe, pas du `s` : l'enfant ne sait pas lequel de *sel* et de *rose* est *doux* |

Sur `c` et `g`, *"la lettre qui suit"* est la bonne méthode, et le paquet la sert à bon droit (items
4, 6, 12, 14 : *cinéma*, *cube*, *guépard*, *bourgeon*). Recopiée sur `s`, elle devient une règle
fausse : un enfant qui la suit sur *salade* ne peut pas trancher, et il apprend que les trois lettres
se lisent selon la même règle. Joué : sur l'item 20, *rose* reçoit *"Ce n'est pas ça."* puis *"Pense
à la lettre qui suit."* L'enfant regarde le `a`, et rien ne l'aide.

**Ce qui lève** : sur les huit items de `s`, aucun indice ne dit ni ne laisse entendre que la lecture
du `s` dépend de la seule lettre qui suit, et aucun n'emploie *doux* ou *dur*. Un indice de `s` qui
dit où regarder désigne les deux côtés du `s` (*"Regarde les lettres avant et après le s."*) ; un
indice de méthode reste admis (*"Dis le mot à voix basse."*). Q1 de s49 vaut ici : nommer la lettre
dans l'indice est permis.

#### 5. Exactitude, niveau, ton

**Exactitude : les 24 items sont justes.** Relu item par item : `c` se lit comme dans *car* dans
*carotte*, *costume*, *cube*, *camion*, comme dans *cerise* dans *citron*, *cinéma*, *cinq*,
*cigale* ; `g` comme dans *gare* dans *gâteau*, *gomme*, *guitare*, *guépard*, comme dans *girafe*
dans *gilet*, *bourgeon*, *pigeon*, *gymnastique* ; `s` comme dans *sel* dans *soleil*, *poisson*,
*tasse*, *salade*, comme dans *rose* dans *maison*, *cuisine*, *fraise*, *voisin*. Aucun mot ne
porte deux lectures de la lettre visée (piège 2 de `theo-archi`). Chaque item n'a qu'une réponse
acceptable : c'est le premier point que `marc-dev` m'a laissé, et il est tenu.

**Niveau** : énoncés de dix mots, tous les mots entre guillemets se déchiffrent au CE1 ; *gymnastique*
et *guépard* sont les plus longs, et c'est la lettre visée qui les rend utiles. Deux propositions, pas
de devinette : chaque lettre n'a que deux lectures. Aucun biais, aucun ton condescendant.

#### 6. À reprendre dans le même passage, sans être des motifs de refus

1. *"Dans « poisson », le s se lit..."* (items 18, 19) : il y a deux `s`, l'énoncé en nomme un. *"les
   deux s se lisent"* dirait ce que l'enfant voit.
2. Les quatre items de `c` lu comme dans *cerise* sont tous des `ci` (*citron*, *cinéma*, *cinq*,
   *cigale*) : aucun `ce` ni `cy`. Le repère *cerise* porte `ce`, la règle n'est donc pas tronquée,
   mais un item `ce` (*glace*, *pouce*) varierait la lettre qui suit. Le motif 1 remplaçant un de
   ces items, c'est le moment de le faire.

#### 7. Ce dont je reste la seule garde sur cette ligne

`PaquetCe1ValeurLettreTest` recalcule la lecture de la lettre depuis le mot et la confronte à la
bonne réponse : l'exactitude des 24 items est désormais gardée par un test, ce qui n'était pas le cas
de `FRA-CE1-01`. **Il ne lit pas l'indice autrement qu'en longueur et en fuite de réponse.** Qu'un
indice n'enseigne pas une règle fausse pour la lettre de son item (motif 2), et que la parenthèse de
la ligne soit servie en entier (motif 1, aucun test ne lit `FER-1`) : je suis la seule garde, en
jouant. Les deux motifs ont traversé une CI entièrement verte.

**Identifiants** : aucun neuf, aucun renommé. Les motifs sont numérotés dans cette fiche seulement.

**Signé** : `nadia-prof`, s50, instance A, 2026-10-04.
