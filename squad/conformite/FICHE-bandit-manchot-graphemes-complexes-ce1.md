# Fiche de conformité - bandit-manchot-graphemes-complexes-ce1

- **Mécanique** : bandit manchot.
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/ce1-graphemes-complexes.yaml`
  (24 items, type `question`), écrit par `marc-dev` (`224803e0`).
- **Entrée de catalogue** : `bandit-manchot-graphemes-complexes-ce1`, titre servi *"Le même son"*,
  consigne servie *"Lis bien, puis choisis le mot qui a le même son."*, `nombreDeManches: 10`.
- **Ligne déclarée** : `FRA-CE1-01`. **Story** : `S49-FRA-GRAPHEMES` (`docs/backlog/backlog.md`,
  section "Sprint 49"). **Décision** : `DEC-0107`.
- **Compétence programme visée** : `squad/programme/francais/competences-ce1.md`, `FRA-CE1-01`,
  *"Lire des mots comportant des graphèmes complexes (`ain`/`ein`, `eil`/`eille`, `ill`, `gn`, `oin`,
  `ien`...) et les associer au son qu'ils transcrivent."*
- **Conditions opposables** : `GRC-CE1-1` à `GRC-CE1-3` et `TRV-CE1-1` à `TRV-CE1-7`
  (`squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md`, sections 2 et 3.1), `LON-CE1-1`,
  `LON-CE1-2`, `LON-CE1-5` (même fichier, section 1).
- **Statut global** : **REFUSÉ** le 2026-10-03 par `nadia-prof` (s49, instance A), deux motifs.
  Fiche ouverte par ce verdict : aucune fiche n'existait pour ce jeu.
- **Dernière mise à jour** : 2026-10-03 (second passage, s49) : **VALIDÉ SOUS RÉSERVE**. Les deux
  motifs sont levés ; une réserve neuve, `GRC-CE1-R1`. Le verdict du premier passage reste écrit tel
  quel.
- **Mise à jour du 2026-10-04** (troisième passage, s50) : **VALIDÉ**. `GRC-CE1-R1` levée. Les deux
  verdicts précédents restent écrits tels quels.

---

## Bloc de validation pédagogique - nadia-prof UNIQUEMENT

### Verdict du 2026-10-03 (`nadia-prof`, s49, instance A) : **REFUSÉ**

**Done pédagogique non prononcé.** `FRA-CE1-01` ne passe pas à `couvert-valide`. L'increment
retourne à `camille-po` avec les deux motifs de la section 4. Le refus ne bloque pas le sprint
suivant.

#### 1. Ce que j'ai fait

**Audit joué.** Arbre `round-two` à `bbfdde17`, sans worktree dédié. Jar construit par
`bash outils/mvn-jdk21.sh -B -pl bootstrap -am package -DskipTests` (Maven sur JDK 21.0.10) ; les
quatre YAML de s49 contenus dans le jar sont identiques octet pour octet à ceux de l'arbre. Jar lancé
sur un port dédié, servi par mon propre processus. Aucun job de la CI lancé par moi : le Done
technique est celui de `yanis-qa` (`rapport-qualite.md` de s49). **Rendu non vu au navigateur** :
j'ai joué par appels HTTP réels et lu le HTML servi.

**Dix-sept parties complètes de dix manches** : trois justes du premier coup, huit avec une ou deux
erreurs sur une partie des manches, six en pressant trois fois le même distracteur jusqu'à la
révélation. **Les 24 items ont
été tirés et joués.** Après une erreur : *"Ce n'est pas ça. Essaie encore !"* et l'indice. Après la
troisième : la bonne réponse est montrée. Écran de fin atteint.

#### 2. Ancrage au programme

Le paquet travaille **cette** ligne. Les six familles de graphèmes de la ligne sont cibles de quatre
items chacune ; aucun graphème du CP (`FRA-CP-04`) n'est cible. Le son est porté par un mot de
référence entre guillemets que l'enfant lit, comme `TRV-CE1-3` l'exige. Sur les douze items nasals,
le distracteur de famille (a) (*pleine*, *graine*, *moine*, *chienne*) oblige à lire et pas seulement à
repérer des lettres : c'est le geste que la ligne existe pour travailler.

#### 3. Les conditions

| Condition | Tenue | Ce que j'ai vérifié |
|---|---|---|
| `GRC-CE1-1` | **oui** | six familles, quatre items chacune (16,7 %) |
| `GRC-CE1-2` | **non, motif 1** | cinq items sur 24 portent une seconde proposition qui contient le son du mot de référence. Aucune bonne réponse dans le lexique de *ville* ; aucune terminaison verbale `-ient` |
| `GRC-CE1-3` | **oui** | familles déclarées à côté de chaque item ; (a) sur 12 items sur 24 |
| `TRV-CE1-1` | **oui** | `CE1`, une ligne, aucun champ à voix haute |
| `TRV-CE1-2` | **non, motif 1** | trois propositions distinctes ; bonne réponse strictement la plus longue sur 12 items sur 24, recompté en lettres. "Une seule acceptable" ne tient pas sur cinq items |
| `TRV-CE1-3` | **oui** | aucun graphème isolé nommé comme un son ; aucune opposition régionale (*brin*/*brun*, é/è) |
| `TRV-CE1-4` | **oui** | aucun mot exclu dans les énoncés, indices et propositions |
| `TRV-CE1-5` | **non, motif 2** | huit indices sur 24 ne donnent ni méthode ni endroit où regarder, dont quatre énoncent un conseil faux |
| `TRV-CE1-6` | **oui** | aucun personnage, aucune situation |
| `TRV-CE1-7` | **oui** | 24 items |
| `LON-CE1-1`, `-2`, `-5` | **oui** | énoncés de neuf mots, une phrase ; propositions d'un mot |

#### 4. Motifs du refus

**Motif 1 - cinq items ont deux réponses justes (exactitude, `GRC-CE1-2`, `TRV-CE1-2`).**

| Item | Mot de référence | Bonne réponse | Distracteur qui porte le même son |
|---|---|---|---|
| 2 | *pain* | *grain* | *chien* |
| 9 | *chien* | *ancien* | *plein* |
| 10 | *bien* | *musicien* | *copain* |
| 11 | *rien* | *gardien* | *plein* |
| 12 | *combien* | *parisien* | *copain* |

*Pain* et *chien* riment, *combien* et *copain* aussi : un enfant qui dit les mots tout bas entend le
même son, et il a raison. Joué : sur l'item 2, *chien* reçoit *"Ce n'est pas ça."* ; sur l'item 12,
*copain* reçoit *"Ce n'est pas ça."* puis l'indice *"Ne choisis pas le mot qui te semble familier."*
Le jeu apprend à l'enfant que deux mots qui riment ne partagent pas de son. Une erreur de contenu est
un motif de refus à elle seule.

**La cause est d'abord chez moi.** L'exemple de famille (c) que j'ai écrit dans `GRC-CE1-3`,
*"chien face à plein"*, est exactement ce défaut ; le paquet l'a recopié. Je le corrige ce jour dans
l'arbitrage, par un amendement daté qui laisse l'exemple lisible. **Je ne déplace pas ma barre** :
`GRC-CE1-2` (*"exactement une proposition contient le son"*) était écrite avant le paquet, et c'est elle
que ces cinq items ne tiennent pas.

**Ce qui lève** : sur chaque item dont la cible est `ain`/`ein` ou `ien`, aucun distracteur ne contient
le son de *« main »*, quelle que soit sa graphie. Les cinq distracteurs du tableau sont remplacés ; la
famille (a) reste au moins sur un tiers des items.

**Motif 2 - huit indices ne donnent ni méthode ni endroit où regarder (`TRV-CE1-5`).**

- *"Lis bien les deux mots avant de choisir."* (items 2, 8, 14, 20) : l'écran montre un mot de
  référence et trois propositions. *"Les deux mots"* ne désigne rien que l'enfant puisse retrouver.
- *"Ne choisis pas le mot qui te semble familier."* (items 6, 12, 18, 24) : ce n'est pas une méthode,
  c'est un conseil faux. La bonne réponse est souvent le mot familier (*soin*, *agneau*, *parisien*).
  *Familier* n'est pas un mot qu'un enfant de CE1 lit seul.

**Ce qui lève** : ces huit indices sont remplacés par des indices qui donnent une méthode (*"Dis les
trois mots tout bas."*) ou qui disent où regarder. La décision Q1 ci-dessous l'autorise en nommant des
lettres.

#### 5. Les deux choix tranchés sans moi, Q1 et Q2 de l'avis de faisabilité de s49

**Q1 - réponse : oui, un indice peut nommer des lettres pour dire où regarder.** *"Regarde la lettre
qui suit le n."* désigne des lettres de l'écrit, pas un son : `TRV-CE1-3` ne l'interdit pas. Ce qui
reste interdit, c'est un groupe de lettres employé comme nom d'un son (*"le son ain"*). Le choix de
`marc-dev` de ne nommer aucune lettre est **admis, pas confirmé comme règle** : il n'est pas fautif en
lui-même, mais il a privé les items de famille (a) de la seule méthode qui les défait (*une voyelle
après le n change la lecture*), et c'est ce vide qu'occupent les huit indices du motif 2.

**Q2 - confirmé.** La longueur d'une proposition se compte en lettres : c'est ce que l'enfant voit sur
un mot seul. Recompté : bonne réponse strictement la plus longue sur 12 items sur 24, la moitié exacte,
dans la borne.

#### 6. À reprendre dans le même passage, sans être des motifs de refus

1. **Les mêmes distracteurs reviennent d'un item à l'autre.** *fusil* et *colline* sur les quatre items
   `ill`, *guitare* et *singe* sur les quatre items `gn`, *moine*, *chienne* et *ciel* sur quatre items
   chacun. Joué : dans une partie de dix manches, la même paire revient deux ou trois fois, et à partir
   de la deuxième l'enfant élimine sans lire. Aucune de mes conditions ne l'interdit : je ne le
   transforme pas en motif. Si ce point est encore là au prochain passage, j'ouvrirai une réserve neuve
   et datée.
2. *"Compare le son du mot de référence aux trois mots."* (items 5, 11, 17, 23) : *mot de référence* est
   un mot d'adulte. L'enfant voit un mot entre guillemets.

#### 7. Ce dont je reste la seule garde sur cette ligne

`PaquetCe1GraphemesComplexesTest` tient la forme : familles déclarées, comptes, lexiques, longueur,
indice qui ne contient pas la bonne réponse. **Il ne lit aucun son.** Qu'une seule proposition porte le
son du mot de référence (motif 1), qu'un indice donne une méthode et n'enseigne rien de faux (motif 2),
la terminaison `-ient` et la lecture d'un distracteur de famille (b) ou (c) : je suis la seule garde, en
jouant. Le motif 1 a traversé une CI entièrement verte.

**Identifiants** : aucun neuf, aucun renommé. Les motifs sont numérotés dans cette fiche seulement.

**Signé** : `nadia-prof`, s49, instance A, 2026-10-03.

---

### Second passage du 2026-10-03 (`nadia-prof`, s49, instance A) : **VALIDÉ SOUS RÉSERVE**

**Done pédagogique prononcé.** Les deux motifs du premier passage sont levés, chacun par la barre que
j'avais écrite. `camille-po` peut passer `FRA-CE1-01` à `couvert-valide` et faire une story de la
réserve `GRC-CE1-R1` (section 4).

#### 1. Ce que j'ai fait

Contenu à `bcac0b17` (`marc-dev`), arbre `round-two` à `adf57b0e`. Jar construit par
`bash outils/mvn-jdk21.sh -B -pl bootstrap -am package -DskipTests` (Maven sur JDK 21.0.10), YAML du
jar vérifié identique octet pour octet à l'arbre, lancé sur un port dédié servi par mon propre
processus. `PaquetCe1GraphemesComplexesTest` relancé avec `-am` : 13 tests, aucun échec. Aucun job de
la CI lancé par moi. **Rendu non vu au navigateur** : aucun gabarit n'a changé, j'ai joué par appels
HTTP réels et lu le HTML servi.

**Vingt-sept parties complètes de dix manches** : deux justes du premier coup, deux avec une erreur
par manche, deux en pressant les deux distracteurs, deux en pressant trois fois le même distracteur
jusqu'à la révélation, douze en mélangeant ces conduites, sept pour relever l'indice servi après une
erreur sur chacun des 24 items. **Les 24 items tirés et joués**, chacun avec une erreur au moins ; les
trois propositions changent de place d'une partie à l'autre. `git diff bbfdde17 adf57b0e` : seuls le
YAML de ce paquet et mon propre amendement de l'arbitrage ont bougé.

#### 2. Les deux motifs

| Motif | Barre écrite au premier passage | Servi | Tenu |
|---|---|---|---|
| 1 | sur les items `ain`/`ein` et `ien`, aucun distracteur ne contient le son de *« main »* ; famille (a) au moins sur un tiers | item 2 *garni*, item 9 *canine*, item 10 *cuisine* (et *ancienne* à la place de *chienne*), item 11 *racine*, item 12 *farine* (et *ancienne*). Relu sur les huit items de ces deux familles : aucun distracteur ne porte le son de *« main »*. Famille (a) sur 12 items sur 24 | **oui** |
| 2 | les huit indices donnent une méthode ou disent où regarder | *"Dis les trois mots tout bas."*, servi sur les items 2, 6, 8, 12, 14, 18, 20, 24. Six mots, pas de mot difficile | **oui** |

*Garni*, *canine*, *cuisine*, *racine*, *farine* se déchiffrent avec les graphèmes du CP, et la fin en
*-ine* se lit *[in]* comme dans *« mine »* : un enfant qui dit le mot tout bas n'entend pas le son de
*« main »*. *Canine* et *garni* sont moins courants que les trois autres, mais un distracteur doit être
lu, pas forcément connu. *Garni* reprend les lettres de *grain* dans un autre ordre : c'est un bon
piège de lecture. *Ancienne* porte le son de *« dent »*, pas celui de *« main »*. Joué : sur l'item 2,
*garni* reçoit *"Ce n'est pas ça."* à bon droit.

L'indice *"Dis les trois mots tout bas."* est la méthode qui défait la famille (a) : dire *graine*,
*moineau* ou *ancienne* fait entendre que la voyelle après le *n* change le son. *"Les trois mots"*
désigne les trois boutons. Aucune fuite : l'indice ne contient aucune proposition, vérifié sur les 24
indices servis.

#### 3. Les deux points de la section 6 du premier passage

- **Point 2, traité.** *"Compare le son du mot entre guillemets aux trois mots."* sur les items 5, 11,
  17 et 23 : l'enfant voit les guillemets sur l'énoncé servi.
- **Point 1, traité en partie.** Les paires sont variées : aucune paire de distracteurs ne revient sur
  plus de deux items, contre quatre au premier passage. *Moine*, *chienne*, *ciel*, *fusil*, *guitare*
  et *singe* ne reviennent plus que deux fois. **Mais *colline* reste sur les quatre items `ill`.**
  Joué : *colline* revient dans une même partie dès que deux items `ill` sont tirés, ce qui est arrivé
  dans 12 de mes 20 premières parties. J'avais écrit que j'ouvrirais une réserve neuve et datée si le point
  restait. Il reste pour ce seul mot, j'ouvre donc la réserve pour ce seul mot.

#### 4. La réserve, datée du 2026-10-03

**`GRC-CE1-R1` - *colline* sur les quatre items `ill`.** Un distracteur qui revient à chaque item d'une
famille est éliminé sans être lu dès sa deuxième apparition. **Ce qui lève la réserve** : *colline*
figure sur deux des quatre items `ill` au plus (items 17 à 20). Le mot qui le remplace ne porte pas le
son de *« fille »* et n'appartient pas au lexique de *ville* (`GRC-CE1-2`).

#### 5. Les conditions, recomptées

Les conditions tenues au premier passage restent tenues. Ce qui a changé :

- `TRV-CE1-2` : la bonne réponse n'est plus strictement la plus longue que sur **10 items sur 24** (12
  au premier passage) : *ancienne* a la même longueur que *musicien* et *parisien* (items 10 et 12).
  Dans la borne.
- `TRV-CE1-5` : cinq formes d'indice sur le paquet, dix mots au plus. Aucune n'enseigne une règle
  fausse.
- `GRC-CE1-2` : relu sur les 24 items, une seule proposition porte le son du mot entre guillemets.
  *Hier* (items 14, 16) ne porte pas le son de *« soleil »*, *filet* (items 18, 20) ne porte pas celui
  de *« fille »*, *guépard* et *linge* (items 22, 24) ne portent pas celui de *« montagne »*,
  *moineau* (items 6, 8) ne porte pas celui de *« loin »*.

#### 6. Constats sans réserve, sur ce que l'enfant ne lit pas

- *Cuisine* et *farine* sont déclarés famille (b) (items 10 et 12), *canine* et *racine*, construits
  de la même façon, famille (c) (items 9 et 11). Les deux lectures se défendent ; le fichier en
  choisit deux pour un même geste. Pour `marc-dev`, à unifier quand le fichier sera rouvert.
- L'en-tête du YAML dit encore *"douze items sur vingt-quatre"* (c'est 10), *"six formes distinctes"*
  (c'est 5) et Q1 *"non repondues a ce jour"*. Pour le `relecteur-pedagogique`.

#### 7. Ce dont je reste la seule garde sur cette ligne

Inchangé (section 7 du premier passage). `PaquetCe1GraphemesComplexesTest` ne lit aucun son : c'est
en jouant que j'ai vérifié qu'aucun des cinq nouveaux distracteurs ne porte le son de *« main »*, et
qu'un indice n'enseigne rien de faux. La répétition d'un distracteur (`GRC-CE1-R1`) n'est tenue par
aucun test non plus.

**Identifiants** : un neuf, `GRC-CE1-R1`. Forme copiée de `PRO-CE1-R1` : trois segments, niveau au
milieu, marqueur `R` dans le dernier (`ADR-0100`). Alloué dans
`squad/decisions/REGISTRE-identifiants.md`, section 4, dans le même diff. Aucun renommé.

**Signé** : `nadia-prof`, s49, instance A, 2026-10-03.

---

### Troisième passage du 2026-10-04 (`nadia-prof`, s50, instance A) : **VALIDÉ**, `GRC-CE1-R1` levée

**Je ne rouvre pas le paquet entier** : je juge le seul point corrigé par `lea-dev` (`60dc1188`).
`FRA-CE1-01` reste `couvert-valide` ; la réserve tombe.

#### 1. Ce que j'ai fait

Arbre `round-two` à `655b319b`. Jar construit par
`bash outils/mvn-jdk21.sh -B -q -pl bootstrap -am package -DskipTests` (JDK 21.0.10), YAML du jar
identique octet pour octet à l'arbre, lancé sur un port dédié servi par mon propre processus. Aucun
job de la CI lancé par moi. **Rendu non vu au navigateur** : appels HTTP réels, HTML servi lu.
`git diff 60dc1188~1 60dc1188` sur le YAML : seuls les distracteurs des items 19 et 20 et l'en-tête
ont bougé.

**Trente parties complètes de dix manches** (une juste, une avec une erreur par manche, une jusqu'à
la révélation, vingt-sept en mêlant les trois au hasard). **Les 24 items tirés** ; les quatre items
`ill` de 11 à 14 fois chacun, chacun avec des erreurs (9 à 22) et au moins une révélation.

#### 2. La barre écrite au second passage, contre ce qui est servi

| Item | Mot entre guillemets | Bonne réponse | Distracteurs servis |
|---|---|---|---|
| 17 | *« fille »* | *papillon* | *fusil*, *colline* |
| 18 | *« bille »* | *chenille* | *filet*, *colline* |
| 19 | *« quille »* | *coquille* | *fusil*, *ballon* |
| 20 | *« vanille »* | *brindille* | *filet*, *tulipe* |

- *Colline* figure sur deux des quatre items `ill` : **tenu**.
- *Ballon* et *tulipe* ne portent pas le son de *« fille »* : dits tout bas, ni l'un ni l'autre ne
  le fait entendre : **tenu**. Aucun des deux n'est dans le lexique de *ville*
  (`GRC-CE1-2`) : **tenu**. *Ballon* a les deux `l` sans le `i` : c'est un bon piège de lecture pour
  l'enfant qui repère des lettres sans lire.
- Joué : *ballon* et *tulipe* reçoivent *"Ce n'est pas ça."* à bon droit.

**Ce que la réserve visait, mesuré en jouant** : *colline* revient dans une même partie dans **4 de
mes 30 parties** (les items 17 et 18 tirés ensemble), contre 12 sur 20 au second passage. Sur le
paquet, aucun distracteur ne figure sur plus de deux items, compté sur le fichier (31 distracteurs
distincts, 17 à deux occurrences, aucun au-delà) : c'est la propriété que j'ai acceptée en Q2 le
2026-10-03, et `PaquetCe1GraphemesComplexesTest` la porte désormais.

#### 3. Ce dont je reste la seule garde

Inchangé, sauf un point : la répétition d'un distracteur est maintenant tenue par un test. Que
*ballon* et *tulipe* ne portent pas le son de *« fille »*, aucun test ne le lit.

**Identifiants** : aucun neuf, aucun renommé. `GRC-CE1-R1` levée.

**Signé** : `nadia-prof`, s50, instance A, 2026-10-04.
