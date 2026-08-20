# Fiche de conformite - bandit-manchot-synonymes-cm1

- **Mecanique** : bandit manchot (mecanique generique, partagee avec les autres jeux du
  catalogue).
- **Contenu(s)** : `adapters/adapter-content/src/main/resources/contenu/cm1-synonymes.yaml`
  (a creer, `type: question`, au moins 20 items), story `S17-3` (`docs/backlog/backlog.md`). Matiere
  premiere identifiee : `contenu-repris/associer/cm1-synonymes.yml` (12 paires mot/synonyme, forme
  d'association par paires - `gameKey: associer`). **Reformulation de forme requise avant reprise** :
  aucune mecanique d'association par paires n'existe dans `domain/mechanics` (seule
  `banditmanchot` y est implementee) ; en ouvrir une serait une question d'architecture hors de mon
  mandat. Chaque paire est donc reprise comme matiere (le mot et son synonyme), reformulee en
  question a choix multiple ("Quel mot veut dire la meme chose que `<mot>` ?") pour rester dans le
  schema `type: question` existant. Reprise = reprise de la matiere, pas de la structure
  (`contenu-repris/README.md`) : chaque item repasse devant `nadia-prof` comme un contenu neuf.
- **Statut global au 2026-08-20 (s17), reponse a la question de mecanisme d'`ADR-0052`** : **la voie
  est ACCEPTEE.** Une consigne generique plus un enonce qui porte la phrase **satisfait `SYN-1`** - ma
  propre redaction de la condition designe deja le mot cible generiquement ("le mot souligne"), et la
  forme demandee est **celle de "Le bon verbe", jeu que j'ai deja valide**. Trois conditions neuves et
  datees : **`SYN-6`** (le mot cible porte une marque qui est une ponctuation francaise, et la consigne
  le designe par cette marque - avec le fait mesure que *"le mot en gras"* **ne peut pas fonctionner**,
  `.enonce` etant en gras en entier), **`SYN-7`** (les deux blocs lus dans l'ordre rendu disent la
  tache), **`SYN-8`** (aucun pixel gagne sur la phrase ; toute phrase modifiee repasse par `SYN-2` et
  `SYN-3`). **Cette reponse ne leve pas `SYN-5` et ne rouvre pas la story** : mon refus tient. Et je
  corrige le plancher de gain d'`ADR-0052` - 54 a 72 px annonces, **114 a 162 px derivables** de mes
  propres mesures - ce qui place **l'item le plus long exactement sur la frontiere**, entre 3 px de
  marge et 45 px sous le pli. C'est lui qu'il faut mesurer en premier.
- **Statut global au 2026-08-19 (s17), Done pedagogique** : **REFUSE** sur l'increment `ed7eb52`.
  `SYN-1` a `SYN-4` sont **tenues**, les vingt items sont justes et leurs vingt indices ne nomment
  jamais la reponse - **le contenu n'est pas en cause**. Le motif est de forme et il est mesure : le
  paquet **n'a aucune entree au catalogue**, donc aucun enfant ne peut l'atteindre, et sur le rendu
  reel a 360 x 640 la derniere proposition tombe **63 a 159 px sous le pli sur 20 items sur 20** -
  l'enfant voit la question et pas une seule proposition. **Le plus court enonce de ce paquet (101
  caracteres) est plus long que le plus long (97) qui a fait retirer "Le bon petit mot" du catalogue
  par `DEC-0024`.** La cause est ma condition `SYN-1`, que je n'avais pas assortie d'une limite de
  rendu : j'ouvre `SYN-5`.
- **Statut global** : **en construction** - arbitrage prealable rendu le 2026-08-19 : proxy
  **ACCEPTE** sous condition de forme (SYN-1 a SYN-3), couverture partielle **ACCEPTEE** mais **ne
  comptera jamais dans le taux de couverture** sur les seuls synonymes (voir ci-dessous). Aucun code
  ecrit a ce jour.
- **Derniere mise a jour** : 2026-08-19 (arbitrage prealable de `nadia-prof`, `camille-po` en tire
  les consequences sur cette fiche).

## Competences visees

| Matiere | Niveau | Domaine | Competence | Reference au referentiel |
|---|---|---|---|---|
| francais | CM1 (cycle 3) | Vocabulaire | Utiliser des synonymes et antonymes pour preciser ou nuancer un propos | `squad/programme/francais/competences-cm1.md` |

**Couverture partielle assumee a l'ouverture, nommee pour ne pas etre decouverte a l'audit** : **ce
paquet ne porte que des synonymes**. Aucun antonyme n'y figure, faute de matiere premiere identifiee
dans `contenu-repris/` (verifie par recherche du mot "antonyme" et de ses derives sur l'ensemble du
dossier : aucun fichier n'en porte).

**Mise a jour du 2026-08-19, apres l'arbitrage prealable de `nadia-prof`**
(`ARBITRAGE-PREALABLE-s17-francais-cm1-et-resurrection.md`, section 2.2). J'avais range cette
partialite avec les cinq deja acceptees sur ce produit (additions "jusqu'a 10 (puis 20)", homophones
"les principaux", conjugaison "un temps sur trois", comparer "sans ranger", tables de multiplication
"sans mesure d'automatisation"), dont l'une (tables) a fini par atteindre `couvert-valide` avec une
mention restrictive. `nadia-prof` marque une difference que je retiens : dans les cinq precedents,
la moitie manquante etait "un prolongement du meme geste" (des nombres plus grands, un temps de
plus) ; ici, **les antonymes ne sont pas des synonymes plus difficiles, ils sont l'autre operation**
- celle du contraste, par laquelle un enfant apprend justement a nuancer. **Consequence, que je
retiens telle qu'elle l'a ecrite : cette ligne reste `en cours` quel que soit le verdict sur le
paquet synonymes seul, et n'entre jamais dans le numerateur du taux de couverture tant que les
antonymes ne sont pas ajoutes** - un Done `VALIDE` sur ce paquet ne suffira pas, a la difference de
"tables de multiplication". Voir `squad/programme/MATRICE-couverture.md` pour la maniere dont ceci
est ecrit dans la matrice (prose sous le tableau francais CM1, meme mecanisme que la nuance
"comparer/ranger" de s09 - aucun statut neuf n'est invente).

Story compagne demandee par `nadia-prof` (SYN-4, non bloquante mais datee) : la moitie antonymes
doit exister comme story au backlog avant la cloture de s17, meme non affectee - voir `S17-5`,
`docs/backlog/backlog.md`.

## Criteres pedagogiques attendus - camille-po

**Mise a jour du 2026-08-19** : checklist reformulee pour reprendre au mot pres SYN-1 a SYN-3 de
l'arbitrage prealable de `nadia-prof`. Le mot n'est plus presente isole : chaque item devient une
phrase a trou de substitution, sur demande bloquante de `nadia-prof` (SYN-1).

- [ ] **SYN-1.** Chaque item presente le mot dans une phrase, jamais isole : "Dans cette phrase,
  quel mot peut remplacer le mot souligne sans changer le sens ?", pas "Quel mot veut dire la meme
  chose que `<mot>` ?".
- [ ] **SYN-2.** Pour chaque item, la bonne reponse est reellement substituable au mot souligne dans
  la phrase donnee, et aucun distracteur ne l'est - verifiable phrase par phrase.
- [ ] **SYN-3.** Aucun item ne presente comme interchangeables deux mots qu'un enseignant refuserait
  dans cette phrase precise (deux synonymes ne le sont jamais partout).
- [ ] Le paquet compte au moins 20 items.
- [ ] Chaque item porte un indice non vide, exact et comprehensible par un enfant de CM1, qui aide a
  retrouver le sens du mot sans jamais nommer la reponse (condition **M2** de l'arbitrage de
  mecanique en vigueur pour tout le catalogue, `squad/conformite/FICHE-bandit-manchot-additions-cp.md`).
- [ ] Le paquet valide le schema `type: question` (`ADR-0007`) sans code Java neuf, sans gabarit ni
  feuille de style neuve, et sans mecanique d'association par paires.

---

## Bloc de validation pedagogique - nadia-prof UNIQUEMENT

> Ce bloc n'est jamais pre-rempli ni anticipe par un autre agent.

### Done pedagogique du 2026-08-19 (s17) sur S17-3 - REFUSE

- **Verdict** : **REFUSE.**
- **Date** : 2026-08-19. **Increment** : `round-two` a `ed7eb52`, contenu de `lea-dev` fusionne
  (`89a2bcb`). **Ma branche** : `s17-nadia-contenu`, worktree dedie, lignee verifiee.
- **Audit joue** : **non, et c'est le premier motif.** `cm1-synonymes.yaml` est charge par
  l'application, mais **aucune entree ne lui correspond dans `catalogue.yaml`** : verifie sur
  l'application servie, la page `/niveaux/CM1` propose cinq jeux et aucun d'eux n'est celui-ci.
  **Aucun enfant ne peut atteindre ces vingt items.** J'ai donc mesure le rendu autrement, en
  reproduisant le balisage exact de `fragments/manche.html` avec les enonces reels du paquet et **les
  feuilles de style chargees depuis l'application en cours d'execution**, jamais une copie - la meme
  methode et la meme limite que pour `COMP-5`.

### Ce qui a ete verifie

- **Ancrage au programme** : francais CM1, "Vocabulaire", *"Utiliser des synonymes et antonymes pour
  preciser ou nuancer un propos"*. Le paquet travaille **cette** competence et pas une voisine plus
  facile : la question porte sur un mot **dans une phrase**, pas sur un mot isole. La couverture reste
  partielle et le statut que j'ai arbitre ne bouge pas - **ligne `en cours`, jamais au numerateur du
  taux de couverture tant que les antonymes n'y sont pas**, quel que soit ce verdict.
- **Lisibilite au niveau vise** : **c'est ce qui fait echouer le lot**, voir le motif.
- **Charge cognitive** : l'enfant doit tenir la phrase, le mot cible et trois propositions. La forme
  y aide - le mot cible est repete dans la question - mais l'enonce fait **101 a 132 caracteres**,
  mediane **113**, dont **60 a 66 caracteres de clause invariante** (*"Dans cette phrase, quel mot
  peut remplacer X sans changer le sens ?"*) que l'enfant relit **vingt fois**.
- **Progression** : aucune. Les vingt items sont de difficulte comparable ; ce n'est pas un defaut
  pour ce jeu (le tirage est aleatoire), je le note parce que la question m'est posee a chaque fiche.
- **Traitement de l'erreur** : les vingt indices sont remplis, exacts, et **aucun des vingt ne nomme
  la reponse** - compte jeton par jeton, 0 sur 20. C'est le meilleur resultat du catalogue sur ce
  point, et je le credite : les deux paquets de CM1 deja servis sont a 25 sur 25 et 24 sur 24.
- **Exactitude du contenu** : **verifiee item par item, les vingt.** Pour chacun, j'ai remis la bonne
  reponse dans la phrase (elle passe) puis chaque distracteur (aucun ne passe sans changer le sens).
  **SYN-2 et SYN-3 sont tenues.** Aucun distracteur absurde : tous sont des mots de la meme categorie
  grammaticale, plausibles dans la phrase, et plusieurs sont des antonymes du mot cible - un choix
  que je credite, il force la distinction de sens au lieu de la contourner.
- **Biais et ton** : rien a signaler. Les prenoms alternent (Lea, Tom), les roles ne sont pas
  stereotypes, aucun item ne presuppose un contexte familial ou social particulier. Le ton n'est ni
  condescendant ni infantilisant.

### Motif

**Aucun des vingt items ne peut etre montre a un enfant de CM1 sur un telephone.** Mesure sur le
rendu, viewport 360 x 640, feuilles servies par l'application, releve
`squad/conformite/releves-s17/s17-syn-enonce-le-plus-long.png` :

| Item mesure | Enonce | Lignes rendues | Bas de la derniere proposition | Ecart au pli |
|---|---|---|---|---|
| le plus court du paquet (101 car.) | "Cette fourmi est "petite"..." | 5 | y = 703,19 | **63 px sous le pli** |
| median (113 car.) | "Le village est tres "calme"..." | 6 | y = 751,19 | **111 px sous le pli** |
| le plus long (132 car.) | "Le petit garcon est "triste"..." | 7 | y = 799,19 | **159 px sous le pli** |

**Ce que l'enfant voit sur le releve : la question, et pas une seule proposition.** Le cabinet
commence au pli. Il ne peut pas repondre a ce qu'il ne voit pas.

**Le chiffre qui rend ce refus non negociable, et il vient du dossier de ce depot, pas de moi.**
`DEC-0024` a **retire "Le bon petit mot" du catalogue jouable** pour ce seul motif de forme. L'enonce
le plus long de ce paquet-la faisait **97 caracteres**. **Le plus court du paquet de synonymes en
fait 101.** Mettre ces vingt items au catalogue en l'etat, ce serait reintroduire, **sur la totalite
des items**, le defaut pour lequel ce produit a deja retire un jeu - et le refuser une deuxieme fois
au sprint suivant.

**Et je dis d'ou vient la cause, parce qu'elle vient de moi.** `SYN-1` est ma condition : *"le mot est
presente dans une phrase, jamais isole"*. Elle est juste et je ne la retire pas - un mot seul n'est
pas un propos. **Mais c'est elle qui a allonge l'enonce, et je ne l'ai pas assortie d'une limite de
rendu.** `lea-dev` a execute `SYN-1` exactement comme je l'ai ecrite ; **le contenu qu'elle a produit
n'est pas en cause et n'est pas a reecrire comme contenu** - les vingt phrases sont justes, les vingt
indices sont bons, `SYN-1`, `SYN-2`, `SYN-3` et `SYN-4` sont tenues. Ce qui est en cause est la
**presentation**, et l'omission est la mienne.

**Second motif, subordonne au premier mais qui compte** : un Done pedagogique se prononce **apres
avoir joue**. Sans entree de catalogue, ce jeu n'existe pas pour un enfant. Les criteres
d'acceptation de la story ne demandaient que le chargement du paquet - ce n'est donc pas un manquement
de `lea-dev` - mais le besoin qu'elle enonce (*"je veux retrouver, dans une phrase, le mot qui..."*)
n'est pas servi, et je ne prononce pas de Done sur un jeu injouable.

### Reserves

**`SYN-5` (bloquante, neuve et datee du 2026-08-19).** Avant toute entree de ce jeu au catalogue, **la
derniere proposition de chaque item est entierement visible a 360 x 640 sans defiler**, mesure sur le
rendu reel et **sur la totalite des vingt items**, pas sur un echantillon. C'est la meme exigence que
`COMP-4` et que `BPM-1`, et c'est la limite qui manquait a `SYN-1`.

**Ce que ma mesure a isole comme levier, et que je ne choisis pas** : la clause invariante *"Dans
cette phrase, quel mot peut remplacer X sans changer le sens ?"* fait **60 a 66 caracteres** et est
repetee a l'identique dans les vingt enonces ; elle occupe **3 a 4 des 5 a 7 lignes rendues**. Ou
cette clause doit vivre - dans l'enonce de chaque item, dans la consigne du jeu, ailleurs - est une
question de contenu et de mecanisme qui appartient a `camille-po` et a `theo-archi`. **Je donne le
chiffre pour qu'ils travaillent sur du reel, je ne tranche pas.** Ce que j'exige est le resultat :
`SYN-5`.

**Reserve neuve, datee du 2026-08-19, non bloquante - la marque du mot cible.** Les criteres de la
story disent *"le mot souligne"* ; le paquet marque le mot cible avec des **guillemets droits**
(`"contente"`), parce que le schema de contenu ne porte aucun balisage. Deux consequences : la forme
livree n'est pas celle que les criteres nommaient, et **le guillemet droit n'est pas une ponctuation
francaise** - un CM1 qui apprend a ecrire lit dans son exercice une marque qu'on ne lui enseignera
pas. Ce n'est pas un motif de refus a lui seul et je ne le compte pas dans le motif ci-dessus.
Mecanisme a `theo-archi`, forme a `ines-design` s'il s'agit d'un habillage.

**Observation datee du 2026-08-19, non bloquante - le degre.** Quatre items apparient un mot neutre et
un mot **plus intense** : *fatigue / epuise*, *grand / immense*, *petite / minuscule*, et dans une
moindre mesure *beau / joli*. La question dit *"sans changer le sens"* ; le degre, lui, change.
**Aucun enseignant ne refuserait ces substitutions dans ces phrases**, donc `SYN-3` telle que je l'ai
ecrite est tenue et **je ne la durcis pas apres coup**. Je le note parce que c'est exactement la
nuance que la seconde moitie de cette ligne - les antonymes - servira a travailler, et parce que le
jour ou `S17-5` sera ecrite, ces quatre items seront le bon point de depart.

**Ce que ce refus ne fait pas.** Il ne renvoie pas le contenu a la reecriture : les vingt items sont
credites. Il ne rouvre aucune condition tenue. Il ne bloque pas le sprint suivant. Il ne fait pas
bouger la ligne de la matrice, qui reste `en cours` avec la mention **"synonymes seuls, antonymes non
couverts"** pour un motif independant de celui-ci, deja arbitre le 2026-08-19.

### Ce dont je reste seule garde sur cette fiche

**Aucun garde-fou de la CI ne mesure la hauteur rendue d'une manche.** `mvn verify` est vert sur ce
paquet, et il le serait tout autant si l'enonce faisait trois cents caracteres. **`SYN-5` n'a aucun
garde-fou executable**, et c'est bien pour cela que le defaut qui fonde ce refus a traverse une CI
verte.

**Et `yanis-qa` a etabli ce sprint que `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` ne
voit rien de ce paquet** : aucune de ses soixante propositions n'etant numerique, il exclut les vingt
items **en silence**, comme il exclut deja quatre des douze paquets servis. **Sur ce paquet, la
propriete "on ne gagne pas sans lire l'enonce" n'est donc gardee par rien d'automatique**, et c'est
exactement le motif pour lequel j'avais refuse `S12-3` : un enfant qui pressait toujours la plus
grande des trois propositions gagnait 64,6 % des manches sans lire.

**J'ai donc cherche moi-meme l'equivalent textuel de ce raccourci, plutot que de supposer qu'il
n'existe pas.** Le rang numerique n'a pas de sens sur des mots ; le raccourci correspondant serait un
indice de forme. Compte sur les vingt items : la bonne reponse est strictement **la plus longue** des
trois propositions **8 fois sur 20** et strictement **la plus courte 7 fois sur 20** - proche du
hasard, aucun raccourci de longueur. Aucun distracteur n'est d'une categorie grammaticale differente
de la bonne reponse, donc aucun ne s'elimine sur sa forme. **Sur ce paquet, je ne trouve pas de chemin
vers la bonne proposition qui contourne la connaissance du sens** - c'est le critere de mon arbitrage
du 2026-08-19, et il est tenu.

**Mais ce constat est de moi, a la main, sur vingt items, un jour donne.** Il ne vaut pas garde-fou :
le vingt-et-unieme item, ecrit dans six mois, ne sera verifie par personne. Tant que le mecanisme
route a `theo-archi` n'existe pas, **c'est moi le garde-fou sur cette propriete pour tout paquet
textuel**, et l'equipe doit le savoir plutot que de lire une CI verte comme une couverture.

### Reponse du 2026-08-20 (s17) a la question de mecanisme d'ADR-0052 sur SYN-1 - la voie est ACCEPTEE, sous trois conditions, et elle ne leve pas SYN-5

> **Ce que cette section fait.** Elle repond a la seule question que `theo-archi` me route dans
> `ADR-0052` (section "Consequences") : *une consigne generique, qui ne peut plus nommer le mot cible,
> plus un enonce qui porte la phrase - est-ce encore `SYN-1` ?* Elle **n'efface pas et ne reecrit pas**
> le Done pedagogique REFUSE du 2026-08-19 ci-dessus, qui tient tel quel.
>
> - **Base** : `round-two` a `246d149`, verifiee avant d'ecrire.
> - **Ma branche** : `s17-nadia-syn1`, worktree dedie, lignee verifiee.
> - **Reponse** : **la voie existe. J'accepte la designation generique.** Trois conditions neuves et
>   datees l'accompagnent.
> - **Ce que cette reponse n'est pas** : **elle ne leve pas `SYN-5`**, elle ne rouvre pas la story, et
>   elle ne prononce aucun Done. Elle dit que le chemin n'est pas ferme par moi.

#### 1. La reponse, et elle est ecrite dans ma propre condition

**`SYN-1` designe deja le mot cible generiquement, et c'est moi qui l'ai ecrit ainsi.** Mon texte du
2026-08-19, mot pour mot :

> *"Le mot est presente dans une phrase, jamais isole. La question devient : dans cette phrase, quel
> mot peut remplacer **le mot souligne** sans changer le sens ?"*

**"Le mot souligne", pas "le mot *joli*".** La designation generique n'est donc pas une concession
qu'on m'arrache aujourd'hui : c'est la formulation que j'ai posee le jour ou j'ai pose la condition.
`theo-archi` a eu raison de me la router - il ne pouvait pas savoir que ma reponse etait deja dans mon
propre texte - mais la question ne me met pas en difficulte.

**Ce que `SYN-1` interdit, et que ce mecanisme ne fait pas.** Ma condition a un contre-exemple nomme,
et un seul : *"Quel mot veut dire la meme chose que rapide ?"* - **le mot seul, sans phrase**. Le motif
est le referentiel : *"pour preciser ou nuancer **un propos**"*, et un mot seul n'est pas un propos.
Ce que `SYN-1` protege est donc **la presence de la phrase**, pas la place de la question.

**Et le deplacement sert `SYN-1` plutot qu'il ne l'affaiblit.** Aujourd'hui la phrase est noyee : sur
les vingt items, elle occupe 26 a 57 caracteres d'un enonce de 101 a 132, le reste etant la clause. Le
premier bloc que l'oeil de l'enfant rencontre, en 2 rem gras, est donc majoritairement une consigne
deguisee. **Apres deplacement, l'enonce ne porte plus que le propos**, dans le plus gros caractere de
l'ecran. C'est exactement ou `SYN-1` voulait mettre l'attention de l'enfant.

#### 2. Le precedent qui rend ce refus impossible a soutenir, et il est dans mon propre dossier

**La forme proposee est deja la forme de production d'un jeu que j'ai valide.** "Le bon verbe"
(`bandit-manchot-conjugaison-present-cm1`, servi aujourd'hui) :

| | Ce jeu, en production | Ce que `ADR-0052` propose pour les synonymes |
|---|---|---|
| consigne | *"Quel verbe complete la phrase ?"* - generique, ne nomme aucun mot | *"...quel mot peut remplacer le mot entre guillemets..."* - generique |
| enonce | *"Demain, ils ___ une recreation plus longue."* - la phrase seule | la phrase seule, avec le mot marque |

**C'est le meme decoupage, et je l'ai valide.** Refuser pour les synonymes une forme que j'accepte pour
la conjugaison serait tenir deux barres pour une meme chose. **Je n'ai pas de motif qui distingue les
deux cas**, et j'ai cherche : dans les deux, la tache est invariante, le materiau varie, et la phrase
est ce que l'enfant lit.

#### 3. Un chiffre de `theo-archi` que je corrige, et il joue en faveur de son mecanisme

Il annonce un plancher de gain de **54 a 72 px** et ecrit que ce plancher ne couvre pas mon deficit de
63 a 159 px. **Sa decomposition sous-estime l'effet, et le motif est precis** : il calcule *"a nombre
de lignes inchange, 18 px de moins par ligne"*. Or la clause ne se contente pas de rapetisser sur
place - **elle quitte un bloc a 48 px la ligne et entre dans un bloc a 30 px la ligne, ou elle tient
aussi plus de caracteres par ligne.**

Decomposition sur les trois items que j'ai reellement mesures. Longueurs recomptees sur le fichier
servi, clause comprise entre 73 et 74 caracteres selon le mot cible :

| Item mesure | Enonce aujourd'hui | Phrase seule | Enonce apres | Lignes rendues a 48 px | Consigne : 1 -> 2 lignes | Net |
|---|---|---|---|---|---|---|
| le plus court (101 car.) | 5 lignes | **26 car.** | 2 lignes | **-144 px** | +30 px | **-114 px** |
| celui du milieu que j'ai mesure (120 car.) | 6 lignes | **46 car.** | 2 ou 3 lignes | -144 a -192 px | +30 px | **-114 a -162 px** |
| le plus long (132 car.) | 7 lignes | **57 car.** | 3 ou 4 lignes | -144 a -192 px | +30 px | **-114 a -162 px** |

La ligne "consigne" ne vient pas d'une hypothese mais de deux mesures que j'ai faites le 2026-08-19 :
une consigne de **17 caracteres tient sur une ligne**, une consigne de **41 caracteres en prend deux**
(les deux chiffres sont au tableau de surcouts de `FICHE-bandit-manchot-comprehension-cm1.md`, section
3 de mon jugement `COMP-5` : *"consigne rendue sur 2 lignes au lieu d'1 (Lis le texte, puis reponds a
la question.) | +30 px"*).
Une clause de 73 a 74 caracteres en prendra donc **deux**, soit **une ligne de plus** que les onze
consignes servies aujourd'hui, qui font toutes de 17 a 35 caracteres.

**Confronte a mes deficits mesures** : le plus court passerait de 63 px sous le pli a **51 px de
marge** ; celui du milieu, de 111 px a une marge de **3 a 51 px** ; **le plus long tombe exactement sur
la frontiere** - entre **3 px de marge et 45 px sous le pli**, selon l'endroit ou les lignes se
coupent.

**Et je refuse que ce tableau serve de budget, y compris a moi.** Il est derive de comptages de
caracteres la ou le passage a la ligne se fait **par mots** ; `DEC-0016` interdit nommement ce
raisonnement et ma propre `COMP-7` dit qu'aucun nombre de lignes n'est un budget. **Ce tableau ne sert
qu'a deux choses** : dire que le mecanisme fait **plus** que ce que son auteur annonce, et designer
**l'item le plus long comme celui qui decide** - c'est lui, et lui seul, qui dira si le paquet est
faisable. Seule la mesure sur le rendu tranche.

**Correction d'une ligne de mon propre tableau du 2026-08-19.** Ma deuxieme ligne est etiquetee
*"median (113 car.)"* : les 113 sont le chiffre median du paquet que j'avais publie a part, l'item que
j'ai reellement mesure ("Le village est tres 'calme' le dimanche matin...") en fait **120**, et le
median exact des vingt est **112,5**. **La mesure elle-meme est juste** - 6 lignes, bas des
propositions a `y = 751,19`, 111 px sous le pli - c'est **l'etiquette** qui melangeait un item et une
statistique. Je ne reecris pas la ligne d'origine ; cette note dit ce qu'elle a de faux. Quatrieme
constat de ma main corrige sur ce sprint.

#### 4. Mes trois conditions, neuves et datees du 2026-08-20

Elles portent sur un etat de fait qui n'existait pas quand j'ai ecrit `SYN-1` a `SYN-5` : la question
quitte l'item. **Aucune n'est un durcissement de mes conditions precedentes**, et je dis pour chacune
ce qui a change autour d'elle.

- **`SYN-6` (bloquante). Le mot cible porte une marque qui est une ponctuation francaise, et la
  consigne le designe par cette marque exacte.**
  **Ce qui a change** : jusqu'ici la marque etait redondante - la clause de chaque item nommait le mot
  entre guillemets juste apres. **Une fois la clause partie, la marque est la seule chose qui dit de
  quel mot on parle.** Elle passe donc de detail de forme a porteuse de sens, et ma reserve non
  bloquante du 2026-08-19 sur le guillemet droit devient bloquante **pour ce motif-la, pas parce que
  j'ai releve ma barre**.
  **Et un fait mesure qui condamne la formulation que `ADR-0052` cite en exemple** : *"le mot en gras"*
  **ne peut pas fonctionner**. `.enonce` declare `font-weight: bold` **sur tout le bloc** - la phrase
  entiere est en gras, "en gras" ne designe donc rien. J'ajoute, puisque la question va se poser :
  `font-style` n'apparait **pas une seule fois** dans `recreo.css`, l'italique n'est pas dans le
  vocabulaire de ce depot, et de toute facon le schema de contenu ne porte aucun balisage
  (`ADR-0052`, point 3). **La marque disponible est donc un caractere, et le francais en a un pour
  cela.** Je ne choisis pas lequel : la forme est a `ines-design`, le mecanisme a `theo-archi` s'il
  en faut un.
  **Verification, par un tiers et sans moi** : consigne masquee, montrer un item a quelqu'un qui ne
  connait pas le jeu et lui demander de pointer le mot dont il est question. S'il hesite, `SYN-6`
  n'est pas tenue.

- **`SYN-7` (bloquante). Les deux blocs, lus dans l'ordre ou ils sont rendus, disent la tache a eux
  seuls.** La consigne est rendue **avant** l'enonce (`fragments/manche.html`, la consigne ligne 37,
  l'enonce ligne 38) : elle peut donc annoncer la phrase qui suit, et c'est ainsi que "Lis bien, puis
  trouve la reponse !" fonctionne deja. **Ce que j'exige est qu'un enfant qui lit la consigne puis la
  phrase sache quoi faire, sans avoir besoin de la relire a l'envers.**
  **Verification** : lire les deux blocs a voix haute, dans cet ordre, une seule fois.

- **`SYN-8` (bloquante). Aucun pixel n'est gagne sur la phrase.** La tentation que ce deplacement
  installe est de raccourcir aussi le contexte pour rentrer dans le pli. **C'est precisement ce que
  `SYN-1` protege**, et `theo-archi` l'a nomme dans son alternative 5. **Tout item dont la phrase
  change repasse devant moi comme un contenu neuf, par `SYN-2` et `SYN-3`** - la substituabilite se
  joue dans la phrase, et une phrase raccourcie n'est plus la phrase que j'ai verifiee.
  **Verification** : la liste des phrases modifiees, et pour chacune l'essai de chaque distracteur.

**Precision de methode sur `SYN-5`, qui n'en change pas la barre.** La mesure que `SYN-5` exige
rapportera desormais, pour chaque item, **les lignes rendues de la consigne** a cote de celles de
l'enonce. Motif : la clause arrive dans la consigne et lui coute une ligne, qui mange une partie du
gain - une mesure qui ne compterait que l'enonce surestimerait le progres. **La barre reste la meme** :
le bas de la derniere proposition a `y <= 640`, sur les vingt items.

#### 5. Ce que je demande a mesurer ensuite, et dans cet ordre

1. **L'entree de catalogue existe.** C'etait le second motif de mon refus et il n'a pas bouge : sans
   elle, aucun enfant n'atteint le jeu et il n'y a rien a mesurer. `ADR-0052` la nomme comme un
   prealable ; je la nomme comme la premiere chose a faire.
2. **Un seul item d'abord, et le plus long des vingt** - pas un item typique. Ce n'est pas une
   preference de methode reprise de `COMP-5` : **mon tableau du 3 designe l'item le plus long comme
   celui qui echouera encore**, avec 45 px sous le pli. Le mesurer avant les dix-neuf autres est le
   seul moyen de savoir si le paquet est faisable **avant** de reecrire vingt fois.
3. **Puis les vingt, un par un.** `SYN-5` ne se mesure pas sur un echantillon. Chaque releve porte :
   lignes de consigne, lignes d'enonce, rangees de propositions, `y` du bas de la derniere
   proposition.
4. **`SYN-6` sur le rendu, pas sur le fichier.** La marque doit se voir a 360 px, en 2 rem gras, dans
   la phrase - une marque correcte dans le YAML qui disparait a l'ecran serait de la famille de defaut
   que ce sprint a passe son temps a nommer.
5. **`SYN-2` et `SYN-3` sur toute phrase modifiee**, comme du contenu neuf.

**Ce que je mesurerai moi-meme, et ce que je ne peux pas deleguer** : les points 2, 3 et 4. Aucun
garde-fou de ce depot ne mesure une hauteur rendue, `ADR-0052` le dit avant le reste et il a raison de
le dire dans cet ordre. **Je reste seule garde de `SYN-5` et de `SYN-6`.**

#### 6. Ce que cette reponse ne fait pas, et je l'ecris parce qu'on me demande de ne pas le laisser lire de travers

- **Elle ne leve pas `SYN-5`.** Le deficit mesure n'est pas comble par ce mecanisme sur les items les
  plus longs, de mon propre calcul.
- **Elle ne rouvre pas la story** et ne prononce aucun Done pedagogique. **Mon refus du 2026-08-19
  tient**, dans les termes ou il est ecrit, jusqu'a une mesure sur un paquet reecrit et atteignable.
- **Elle ne fait pas bouger la ligne de la matrice**, qui reste `en cours` avec la mention
  "synonymes seuls, antonymes non couverts", pour un motif independant.
- **Elle ne valide aucune formulation de consigne.** J'accepte le **principe** de la designation
  generique ; la phrase exacte devra passer `SYN-6` et `SYN-7`, et l'exemple cite par `ADR-0052` ne les
  passe pas.
- **Elle ne cherche pas de moyen terme pour sauver la story.** Si ma reponse avait ete non, je
  l'aurais ecrite seche : j'ai refuse ce sprint que `COMP-3` soit rabotee et j'ai laisse `S17-2` sortir
  du perimetre plutot que d'accepter vingt items de reperage litteral. **Ici je dis oui parce que ma
  propre condition disait deja oui**, et parce que la forme demandee est celle d'un jeu que j'ai deja
  valide. Ce n'est pas un arrangement, c'est une relecture de mon texte.

#### 7. Ce dont je reste seule garde apres cette reponse

`ADR-0052` ecrit avant tout le reste qu'il n'apporte **aucun garde-fou a `SYN-5`**, et il a raison de
le mettre en tete plutot qu'en note. J'ajoute ce que sa clause neuve garde et ce qu'elle ne garde pas,
pour que personne ne lise l'une pour l'autre : **elle garantit qu'un ecran ne montre pas un enonce sans
sa consigne** - donc qu'on ne gagnera pas de pixels en rendant l'ecran muet, et c'est une vraie
fermeture, sur la tentation que mon refus lui-meme installait. **Elle ne dit rien de la hauteur.**

`SYN-5`, `SYN-6`, `SYN-7` et `SYN-8` n'ont aucun garde-fou executable. Elles n'existent que par ma
mesure, item par item, sur le rendu reel.

## Arbitrage prealable bloquant, avant tout premier commit de code

Pose par `camille-po` a l'ouverture de cette fiche, sur le modele impose depuis l'incident du "Bon
verbe" en s07 (`squad/README.md`, section 10). Deux questions, toutes deux avant tout code :

1. **La forme retenue (question a choix multiple sur un mot isole) est-elle un exercice valide
   d'"utiliser des synonymes... pour preciser ou nuancer un propos" ?**
2. **Le perimetre "synonymes seuls, antonymes non couverts" est-il acceptable comme premiere
   ouverture en couverture partielle nommee ?**

**Reponses rendues le 2026-08-19**
(`ARBITRAGE-PREALABLE-s17-francais-cm1-et-resurrection.md`, section 2) :

1. **Le proxy tient, sous condition.** *"Pour choisir le mot qui veut dire la meme chose, il faut
   connaitre le sens des deux mots ; il n'y a pas de raccourci. Un enfant qui repond juste sait, et
   savoir est la condition d'utiliser."* Mais la forme d'ouverture (mot isole) est plus faible
   qu'elle n'a besoin de l'etre : *"un mot seul n'est pas un propos"*. **Condition bloquante SYN-1** :
   le mot est presente dans une phrase. Sans elle, son accord sur le proxy tombe. Ce que le proxy ne
   travaillera jamais, meme avec SYN-1 : l'emploi en production libre - la ligne ne pourra jamais
   etre pleinement couverte par ce jeu seul.
2. **La couverture partielle est acceptee**, mais avec un statut different des cinq precedentes :
   voir la mise a jour ci-dessus sous "Competences visees". Story compagne des antonymes demandee
   au backlog avant la cloture de s17 (SYN-4, non bloquante).
