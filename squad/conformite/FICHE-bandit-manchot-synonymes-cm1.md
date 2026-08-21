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
- **Statut global au 2026-08-21 (s17), mesure des seize items** : **Done pedagogique REFUSE.**
  **`SYN-5` n'est pas tenue** : **sept items sur seize** posent la derniere proposition **sous le pli**,
  six a 87 px et un a 39 px. Methode : 8 parties jouees au clavier, **80 tirages**, `scrollY = 0` verifie
  sur chacun, les seize items vus 3 a 8 fois, chiffre retenu = **pire tirage**. **`SYN-6`, `SYN-7`,
  `SYN-8` et `SYN-9` sont tenues** - `SYN-9` impeccable, espaces insecables sur les seize et **zero
  coupure guillemet/mot sur 80 tirages** ; ma reserve d'un caractere est fermee. **Et je corrige mon
  propre chiffre du 2026-08-20** : "l'item pilote TIENT a 2,81 px" etait vrai du tirage que j'avais sous
  les yeux et **faux de l'item** - il est l'un des cinq dont le nombre de rangees varie, et sur un autre
  tirage il est **87 px sous le pli**. Le fait neuf : **le comportement face au pli est une propriete du
  couple (item, tirage)**, pas de l'item. D'ou **`SYN-10`**, neuve et datee : `SYN-5` se mesure au pire
  tirage, jamais sur un tirage. La barre n'a pas bouge ; c'est ma methode de verification qui etait
  insuffisante.
- **Statut global au 2026-08-20 (s17), mesure de l'item pilote** : **il TIENT**, a **2,81 px** pres.
  L'entree de catalogue existe et est servie sur `/niveaux/CM1` - **le second motif de mon refus est
  leve**. Releve a `scrollY = 0` verifie : consigne **2 lignes** (60 px), enonce **3 lignes** (144 px,
  contre 7 avant), 3 propositions sur **2 rangees**, bas de la derniere proposition a **y = 637,19**
  pour un pli a 640. Le meme item etait a **799,19** le 2026-08-19 : gain net **162 px**, la borne haute
  de ce que j'avais derive, et ma prevision "entre 3 px de marge et 45 px sous le pli" tombe juste.
  **`SYN-7` et `SYN-8` tenues** (verifiee par moi : un seul enonce a change, les dix-neuf autres sont
  identiques octet pour octet, la phrase n'a pas bouge d'un caractere). **`SYN-6` tenue** - guillemets
  francais lisibles, designes par leur nom dans la consigne - **avec `SYN-9` neuve et datee** : les
  espaces a l'interieur des guillemets sont **ordinaires** et la ligne se coupe entre le guillemet et
  le mot sur l'item pilote lui-meme ; une espace insecable corrige cela pour un caractere et zero pixel.
  **Et je corrige la sequence que j'avais demandee** : le pire cas n'est pas la phrase la plus longue
  mais le couple (lignes d'enonce, rangees de propositions) - une rangee de plus coute 90 px et ne
  depend pas de la phrase. **Aucun Done pedagogique n'est prononce** : un item sur vingt tient, `SYN-5`
  porte sur les vingt, mon refus reste en vigueur.
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

**Note du 2026-08-20 (camille-po) - le plancher "au moins 20 items" est faux, c'est "au moins 16"
depuis ma decision sur les quatre items a trois rangees.** `nadia-prof` a mesure que quatre items
(`vite`, `ancien`, `poli`, `drole`) ne tiennent pas au-dessus du pli par le seul deplacement de la
clause invariante hors de l'enonce, leurs propositions occupant trois rangees plutot que deux ; j'ai
decide de les retirer du paquet plutot que de reecrire leurs etiquettes de reponse, ce qui aurait
ete un contenu neuf a rejuger par `SYN-2`/`SYN-3` (`DEC-0039`, amendement du 2026-08-20). `lea-dev`
a livre le paquet a seize items, retires du fichier et non simplement ecartes, et verifie par
recherche exhaustive qu'aucun garde-fou du depot ne presupposait vingt items.

Je ne reecris ni la ligne "Contenu(s)" en tete de cette fiche ni le point de checklist ci-dessus :
ils restent lisibles tels qu'ecrits le 2026-08-19, pour que la trajectoire ne soit pas effacee. **Le
chiffre juste, a lire a leur place : le paquet compte au moins 16 items**, et non 20 - ce plancher a
toujours ete ma propre checklist d'ouverture, jamais une condition pedagogique de `nadia-prof`
(ses conditions `SYN-1` a `SYN-9` ne portent jamais sur le nombre d'items). Ce que ce chiffre errone
ne change pas : `ADR-0023` (plus d'items que de manches par partie) reste tenu a seize items contre
dix manches (`catalogue.yaml`, `nombreDeManches: 10`).

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

### Mesure du 2026-08-20 (s17) de l'item pilote de S17-3 - il PASSE a 2,81 px pres, et la suite n'est pas celle que j'avais demandee

> **Ce que cette section fait.** Elle rend la mesure que j'avais demandee en premier : l'item pilote,
> celui que mon tableau du 2026-08-20 designait comme etant sur la frontiere. Elle s'ajoute a la suite
> de mon refus du 2026-08-19 et de ma reponse sur `SYN-1`, sans reecrire ni l'un ni l'autre.
>
> - **Base relevee par moi** : `round-two` a **`c8727d2`** (fusion du lot de `lea-dev`, `99e2ee6`).
>   J'ai aussi verifie ce que j'avais laisse en suspens : **`ADR-0053` existe desormais**, fusionnee a
>   `eb3543b`, avec son garde-fou `UneCouleurDePositionSurvitATousLesEtatsTest`. Ce que j'observais le
>   2026-08-20 - la decision absente du depot et *"il reste quelque chose a fusionner"* - etait exact
>   au moment ou je regardais, et ne l'est plus.
> - **Ma branche** : `s17-nadia-pilote`, worktree dedie, lignee verifiee.
> - **Verdicts** : **l'entree de catalogue existe**, **l'item pilote tient au-dessus du pli**,
>   **`SYN-7` et `SYN-8` sont tenues**, **`SYN-6` est tenue avec une reserve d'un seul caractere**.
>   **Une condition neuve datee, `SYN-9`.** Et **la sequence que j'avais demandee est fausse** : je la
>   corrige.

#### 0. Un artefact de ma propre mesure que j'ai attrape avant de le publier, et il aurait ete un faux vert

**Mon premier releve donnait 119,81 px de marge. Il etait faux.** Le script de focus de `M13` focalise
`.enonce` a chaque manche fraiche, et **focaliser un element le fait defiler dans la vue** : la page
etait descendue de 117 px au moment ou je lisais les rectangles. Mon `window.scrollTo(0,0)` etait bien
appele, mais je lisais avant qu'il ne prenne effet.

**Correction** : la sonde verifie desormais que `window.scrollY` vaut **0** avant chaque lecture, et
recommence jusqu'a ce qu'il le vaille. Tous les chiffres ci-dessous portent `scrollY = 0`.

**Je l'ecris en premier parce que c'est le pire genre d'erreur que je puisse commettre** : elle allait
dans le sens de la story, elle donnait 117 px de marge la ou il y en a 3, et rien ne l'aurait
signalee. C'est exactement la famille de defaut que je passe ce sprint a reprocher aux autres, et
c'est la sixieme fois que je corrige un chiffre de ma main - la premiere ou la correction change un
verdict.

#### 1. L'entree de catalogue existe - le second motif de mon refus est leve

`bandit-manchot-synonymes-cm1` est servie sur `/niveaux/CM1`, aux cotes des cinq autres jeux de ce
niveau. **Un enfant atteint le jeu**, je l'ai joue au clavier seul, et j'ai pu mesurer un rendu reel au
lieu de le reconstituer. C'etait le second motif de mon refus du 2026-08-19 ; il n'a plus d'objet.

**Et je verifie ce que `sofia-sm` a nomme** : aucun test n'exerce la clause *"la consigne est
porteuse"* sur l'ecran reel de ce jeu-ci, le garde-fou d'`ADR-0052` tournant sur un jeu fictif. **Je
suis donc la premiere a le regarder, et je confirme que la consigne est bien rendue sur l'ecran reel
de ce jeu** : deux lignes, 20 px, graisse normale, au-dessus de l'enonce. Le fait est etabli
aujourd'hui ; le trou de garde-fou reste ouvert et n'est pas de mon ressort.

#### 2. L'item pilote tient - releve complet, `scrollY = 0`, feuilles servies

Viewport 360 x 640, application servie, sonde par element.

| Bloc | Lignes | Taille | Graisse | Haut | Bas | Hauteur |
|---|---|---|---|---|---|---|
| en-tete | - | - | - | y = 0 | y = 143,19 | 143,19 px |
| score | 1 | 20 px | 700 | y = 183,19 | y = 213,19 | 30,00 px |
| **consigne** | **2** | **20 px** | **400** | y = 221,19 | y = 281,19 | **60,00 px** |
| **enonce** | **3** | **32 px** | **700** | y = 285,19 | y = 429,19 | **144,00 px** |
| propositions | 3 boutons, **2 rangees**, 74 px chacune | | | | y = **637,19** | |

**Bas de la derniere proposition : `y = 637,19`. Pli a 640. L'item TIENT, avec 2,81 px de marge.**

Le meme item mesurait **799,19**, soit **159,19 px sous le pli**, le 2026-08-19. **Gain net : 162 px**,
exactement la borne haute de ce que j'avais derive.

**Et mon estimation etait juste.** J'avais ecrit le 2026-08-20 : *"le plus long tombe exactement sur la
frontiere - entre 3 px de marge et 45 px sous le pli, selon l'endroit ou les lignes se coupent."* La
mesure donne **2,81 px de marge**. L'enonce est passe de 7 lignes a **3**, la consigne de 1 a **2**.
`s17-syn-item-pilote-360x640.png` montre l'ecran tel qu'un enfant le voit : la consigne, la phrase, et
**les trois propositions, la derniere posee au ras du pli**.

#### 3. `SYN-6` sur le rendu - tenue, avec une reserve d'un seul caractere

**Ce que je credite.** La marque est un vrai guillemet francais, `«` et `»`, et non le guillemet droit
que ma reserve du 2026-08-19 refusait. Elle est **parfaitement lisible a 2 rem gras**
(`s17-syn-item-pilote-enonce-agrandi.png`). Et la consigne la designe **par son nom exact** - *"le mot
entre guillemets"* - donc l'enfant sait quelle marque chercher sans qu'on lui nomme le mot.

**Epreuve de `SYN-6`, consigne masquee** : je regarde l'enonce seul et je dois pointer le mot dont il
est question. **Aucune hesitation** : `« triste »` est le seul groupe marque de la phrase.

**Ce que la mesure trouve, et qui n'etait pas visible sur le fichier.** Les espaces a l'interieur des
guillemets sont des **espaces ordinaires** (U+0020), pas des espaces insecables (U+00A0) - verifie par
les points de code, pas au jugement. **Consequence, et elle se produit sur l'item pilote lui-meme** :
la ligne se coupe **entre le guillemet ouvrant et le mot**. L'enfant lit une premiere ligne qui finit
par un guillemet seul, et une deuxieme qui commence par le mot suivi du guillemet fermant.

**Ce que j'en conclus, et je separe les deux choses.** L'**identification** survit : le guillemet
fermant ferme encore le mot, on voit lequel est marque. La **ponctuation francaise**, elle, ne survit
pas : le francais impose une espace insecable a l'interieur des guillemets **precisement pour que cela
n'arrive pas**. Dans un exercice de vocabulaire francais, lu par un enfant qui apprend a ecrire, une
marque de ponctuation orpheline en fin de ligne n'est pas un detail de gout.

**`SYN-6` est donc tenue** - la marque est bien une ponctuation francaise et la consigne la designe -
**et j'ouvre une condition sur l'espacement**, parce que la corriger coute un caractere et zero pixel,
et que ne pas la corriger la reproduirait vingt fois.

- **`SYN-9` (bloquante avant la reecriture des dix-neuf autres, datee du 2026-08-20). Les espaces a
  l'interieur des guillemets sont insecables**, de sorte que la marque ne puisse jamais etre separee du
  mot qu'elle marque par un passage a la ligne. **Verification** : les points de code de l'enonce, et
  le rendu a 360 px sur chaque item. **Ce n'est pas un durcissement de `SYN-6`** : `SYN-6` a ete ecrite
  avant qu'aucun rendu n'existe, et c'est le rendu qui vient de montrer ce que l'espace ordinaire
  produit. **Cout en pixels : nul** - une espace insecable occupe la meme largeur.

#### 4. `SYN-7` - tenue

Les deux blocs, lus dans l'ordre ou ils sont rendus : la consigne dit *"Quel mot peut remplacer le mot
entre guillemets sans changer le sens ?"*, puis l'enonce donne *"Le petit garcon est « triste » depuis
le depart de son ami."*

**La tache est dite, puis la matiere est donnee.** Et `lea-dev` a fait mieux que ce que `SYN-7`
demandait : sa consigne ne dit pas *"dans cette phrase"* mais *"le mot entre guillemets"* - elle pointe
**la marque** et non la phrase, donc elle n'a besoin d'aucun renvoi vers ce qui la suit. Elle se lit
juste, seule, avant d'avoir vu quoi que ce soit. `SYN-7` tenue.

#### 5. `SYN-8` - verifiee par moi, pas par son test

J'ai compare les vingt enonces du fichier servi a ceux d'avant le lot (`99e2ee6^`), un par un :

- **un seul enonce a change**, le quatrieme ; **les dix-neuf autres sont identiques octet pour octet** ;
- sur l'item transforme, la phrase est **identique caractere pour caractere** apres neutralisation de la
  seule marque, et rien d'autre ne bouge ;
- l'enonce passe de **132 a 59 caracteres** : les 73 retires sont exactement la clause deplacee, et les
  2 ajoutes sont les guillemets francais, plus larges d'un caractere de chaque cote.

**Aucun pixel n'a ete gagne sur la phrase.** `SYN-8` tenue.

#### 6. Ce que la mesure change a la sequence que j'avais demandee - et c'est moi qui me corrige

**J'avais demande qu'on mesure "le plus long des vingt" en premier, au motif que mon tableau le
designait comme celui qui echouerait. C'etait la bonne premiere mesure et c'est une mauvaise regle.**
Les onze autres items que j'ai vus passer pendant que je cherchais le pilote montrent pourquoi :

| Item vu | Lignes d'enonce | Rangees de propositions | Bas des propositions |
|---|---|---|---|
| "grand", "calme", "fatigue", "beau", "malin", "fort", "douce" | 6 | **2** | y = 781,19 |
| "courageux" | 7 | 2 | y = 829,19 |
| "vite" | **5** | **3** | y = 823,19 |
| "ancien", "poli" | 6 | **3** | y = 871,19 |

**Une rangee de propositions de plus coute 90 px, et elle ne depend pas du tout de la longueur de la
phrase** : elle depend de la largeur des trois etiquettes de reponse. "vite" a **l'enonce le plus court
des onze** et se retrouve **plus bas** que sept items a 6 lignes, parce que le mot `silencieusement`
fait passer ses propositions a trois rangees.

**Donc le pire cas n'est pas la phrase la plus longue : c'est le couple (lignes d'enonce, rangees de
propositions).** L'item pilote a tenu avec **2,81 px** en etant a **2 rangees**. **Un item a 3 rangees
partirait de 90 px de plus, c'est-a-dire environ 87 px sous le pli**, quelle que soit la brievete de sa
phrase.

**Prediction que je pose pour qu'on puisse me la reprocher** : sur les dix-neuf a reecrire, ceux dont
les propositions tiennent sur **trois** rangees - `vite`, `ancien`, `poli` et `drole`, les quatre que
j'ai vus - **ne passeront pas** par le seul deplacement de la clause. Je ne dis pas quoi en faire :
raccourcir une etiquette de reponse est un choix de contenu qui appartient a `camille-po` et a
`lea-dev`, et il devra repasser par `SYN-2` et `SYN-3`.

#### 7. Ce que je demande maintenant, dans l'ordre corrige

1. **`SYN-9` d'abord**, sur l'item pilote : une espace insecable de chaque cote, avant que dix-neuf
   items ne reproduisent l'espace ordinaire.
2. **Puis les items a trois rangees de propositions**, pas les phrases les plus longues - `vite`,
   `ancien`, `poli`, `drole` en tete. Ce sont eux qui decident maintenant.
3. **Puis les vingt, un par un.** `SYN-5` ne se mesure pas sur un echantillon, et l'item pilote vient
   de montrer pourquoi : il tient a **2,81 px**, une marge qu'aucune moyenne, aucune extrapolation et
   aucun comptage de caracteres n'aurait predite au bon signe. Chaque releve porte : `scrollY = 0`,
   lignes de consigne, lignes d'enonce, rangees de propositions, `y` du bas de la derniere proposition.
4. **`SYN-2` et `SYN-3` sur tout item dont la phrase ou une etiquette de reponse change.**

#### 8. Une observation datee du 2026-08-20, non bloquante, que je ne compte pas contre ce lot

**Un enfant qui arrive au clavier voit un cadre autour de la question.** Mesure : le script de `M13`
focalise `.enonce` avec `tabindex="-1"` ; arrive au clavier, l'element satisfait `:focus-visible` et
recoit **l'anneau de focus par defaut du navigateur** (`outline: rgb(16, 16, 16) auto 1px`), qu'aucune
regle maison ne neutralise sur un `<p>`. Arrive a la souris, `:focus-visible` est faux et il n'y a
aucun cadre - verifie sur trois jeux.

**Ce n'est ni propre a ce jeu ni cause par ce lot** : c'est general, sur toute manche fraiche de tout
jeu, et cela date de `M13` (s10). Ce que je note, c'est que **la question porte alors le meme
vocabulaire visuel qu'un bouton focalise**, sur un element qui n'est pas pressable. **C'est la meme
ligne de `recreo.js` qui produit `S17-C`**, que `camille-po` a sortie du perimetre de s17 ; je verse
cette observation au meme dossier plutot que d'ouvrir un jeton de plus.

#### 9. Ce que cette mesure ne fait pas

- **Elle ne prononce aucun Done pedagogique sur `S17-3`.** Un item sur vingt tient ; `SYN-5` porte sur
  les vingt. **Mon refus du 2026-08-19 reste en vigueur** jusqu'a ce que les vingt soient mesures.
- **Elle ne fait pas bouger la ligne de la matrice**, qui reste `en cours` avec la mention "synonymes
  seuls, antonymes non couverts".
- **Elle ne dit rien de bon des dix-neuf autres** : je les ai remesures au passage, ils sont
  aujourd'hui **141 a 231 px sous le pli**.
- **Elle ne comble aucun trou de garde-fou.** `SYN-5` a `SYN-9` n'ont aucun test executable, et aucun
  test n'exerce la consigne sur l'ecran reel de ce jeu. **Je reste seule garde**, et l'artefact de la
  section 0 dit ce que cela coute quand la seule garde se trompe.

### Done pedagogique du 2026-08-21 (s17) sur S17-3, paquet a seize items - REFUSE, et je corrige mon propre chiffre du 2026-08-20

> **Ce que cette section fait.** Elle rend la mesure des seize items, celle que ma sequence corrigee
> demandait. Elle s'ajoute a la suite de mon refus du 2026-08-19, de ma reponse sur `SYN-1` et de la
> mesure de l'item pilote, sans reecrire aucun des trois. Elle ne touche pas au critere "20 items" de
> la checklist de `camille-po`.
>
> - **Base relevee par moi** : `round-two` a **`e96d682`**, qui **contient deja** `e2d53d0`. J'avais
>   fusionne `s17-lea-syn3-16items` dans mon worktree avant de mesurer, au cas ou : **c'etait le
>   second cas, et il est devenu inutile pendant que je mesurais**. J'ai verifie que le fichier de
>   contenu que j'ai mesure est **identique octet pour octet** a celui de `round-two`.
> - **Ma branche** : `s17-nadia-seize`, worktree dedie, lignee verifiee par `outils/verifier-lignee.sh`.
> - **Verdict** : **REFUSE.** **`SYN-5` n'est pas tenue** : **sept items sur seize** posent la derniere
>   proposition **sous le pli**, de 39 a 87 px. **`SYN-6`, `SYN-7`, `SYN-8` et `SYN-9` sont tenues.**
> - **Une condition neuve, `SYN-10`, datee du 2026-08-21.**

#### 0. Ce que je corrige de ma main, et c'est mon chiffre le plus cite de ce sprint

**J'ai ecrit le 2026-08-20 que l'item pilote "TIENT, marge 2,81 px". C'etait vrai du tirage que j'avais
sous les yeux et faux de l'item.** L'item pilote - *"Le petit garcon est « triste » depuis le depart de
son ami."* - est l'un de ceux dont le nombre de rangees de propositions **varie d'un tirage a l'autre**.
Sur cinq tirages observes je l'ai vu a **2 rangees** (bas a 637,19, marge 2,81 px) **et a 3 rangees**
(bas a **727,19**, soit **87 px sous le pli**).

**Ma mesure du 2026-08-20 n'etait pas fausse : elle etait incomplete, et je ne l'avais pas dit.** Je
n'avais mesure l'item pilote qu'**une fois**. C'est exactement le reproche que je fais aux autres depuis
le debut de ce sprint - un chiffre juste dont on ne dit pas ce qu'il ne couvre pas - et cette fois il
est de moi, sur le chiffre que toute la squad a repris. **Je ne reecris pas la section du 2026-08-20** :
elle reste, et celle-ci dit ce qu'elle a d'incomplet.

**Consequence sur la decision de `camille-po`** : elle a retire quatre items sur la foi de ma prediction,
et **ma prediction etait juste sur ces quatre-la** - ils etaient bien a trois rangees. Ce que je n'avais
pas vu, c'est qu'**ils n'etaient pas les seuls**, et qu'un item peut passer a trois rangees **sans
qu'aucun de ses mots ne change**. Le retrait des quatre n'etait donc pas une erreur ; il etait
insuffisant, et il ne pouvait pas etre suffisant sur la base que je lui avais donnee.

#### 1. Le fait qui commande tout : le pli depend du tirage, pas seulement de l'item

**Mesure** : conteneur des propositions **320 px**, ecart entre colonnes **16 px**, etiquettes a **2 rem**.
Sur les seize items, **les trois etiquettes ne tiennent jamais sur une seule rangee** : chaque item est
sur **2 ou 3 rangees**. Et **laquelle des deux depend de l'ordre dans lequel la mecanique tire les
propositions** - l'etiquette qui se retrouve seule sur une rangee y est etiree a 320 px.

**Observe directement, pas deduit** : sur **cinq** des seize items, j'ai vu **les deux** nombres de
rangees au fil des tirages. Une rangee de plus coute **90 px**, le chiffre que j'avais etabli le
2026-08-20.

**Donc le comportement d'un item face au pli n'est pas une propriete de l'item : c'est une propriete du
couple (item, tirage).** C'est le fait neuf de cette mesure, et il invalide la methode de verification
que j'avais moi-meme ecrite pour `SYN-5`.

#### 2. `SYN-5` : les seize, tirage par tirage - sept ne tiennent pas

**Methode** : application servie, navigateur pilote par son protocole de deverminage, viewport impose a
360 x 640, **huit parties jouees au clavier seul**, **80 tirages** au total, **`scrollY = 0` verifie
avant chaque lecture** (0 lecture a `scrollY` non nul sur 80). Les seize items ont ete vus, chacun de 3
a 8 fois. Le chiffre retenu par item est **son pire tirage observe**.

| Etat | Tirages | Rangees vues | Pire bas | Verdict | Item |
|---|---|---|---|---|---|
| variable | 5 | 2 et 3 | 727,19 | **87 px sous le pli** | « triste » (l'item pilote) |
| variable | 8 | 2 et 3 | 727,19 | **87 px sous le pli** | « calme » |
| stable | 3 | 3 | 727,19 | **87 px sous le pli** | « gentil » |
| variable | 4 | 2 et 3 | 727,19 | **87 px sous le pli** | « courageux » |
| variable | 6 | 2 et 3 | 727,19 | **87 px sous le pli** | « facile » |
| variable | 5 | 2 et 3 | 727,19 | **87 px sous le pli** | « difficile » |
| stable | 6 | 3 | 679,19 | **39 px sous le pli** | « contente » |
| stable | 5 | 2 | 637,19 | tient, +2,81 px | « beau » |
| stable | 5 | 2 | 637,19 | tient, +2,81 px | « douce » |
| stable | 4 a 6 | 2 | 589,19 | tient, +50,81 px | « propre », « fort », « gaie », « grand », « petite », « malin », « fatigue » |

**Sept items sur seize ne tiennent pas.** Six a 87 px sous le pli, un a 39 px. Les neuf autres tiennent,
dont deux a **2,81 px** de marge.

**Ce que l'enfant voit** (`s17-syn16-echec-contente.png`) : sur *"Lea est « contente » de son cadeau."* -
**la phrase la plus courte du paquet**, deux lignes d'enonce - il lit la consigne, la phrase, deux
propositions entieres, et **la troisieme coupee en deux par le bas de l'ecran**. Il doit deviner ou
defiler pour savoir ce qu'on lui propose.

**Et je verifie ce qu'on me demandait de ne pas croire.** `lea-dev` annonce n'avoir trouve **aucun** item
a trois rangees parmi les seize. **Il y en a sept**, et cinq d'entre eux ne le montrent que sur certains
tirages - ce qui explique qu'une lecture du fichier ne pouvait pas les voir. **Ce n'est pas une
negligence de sa part** : la propriete n'est pas dans le fichier, elle est dans le rendu et dans le
tirage. C'est ma mesure du 2026-08-20 qui l'a mise sur la piste des rangees, et c'est ma mesure du
2026-08-20 qui lui a donne une liste incomplete.

#### 3. `SYN-6`, `SYN-7`, `SYN-8`, `SYN-9` - les quatre sont tenues

- **`SYN-9` tenue, et c'est le point le mieux tenu du lot.** Les espaces a l'interieur des guillemets
  sont des **espaces insecables (U+00A0) sur les seize items**, verifie par les points de code. Et sur
  le rendu, **zero coupure entre un guillemet et son mot sur 80 tirages** - mesure par les rectangles du
  guillemet et du premier caractere du mot, jamais par relecture visuelle. L'item pilote, qui portait
  encore des espaces ordinaires le 2026-08-20, est corrige. **Ma reserve d'un caractere est fermee.**
- **`SYN-6` tenue.** Guillemets francais sur les seize, designes par la consigne (*"le mot entre
  guillemets"*), lisibles a 2 rem gras. Epreuve consigne masquee sur les captures : le mot marque est
  identifiable sans hesitation.
- **`SYN-7` tenue.** La consigne se lit avant la phrase et suffit a dire la tache, sans renvoi vers ce
  qui la suit.
- **`SYN-8` tenue, verifiee par moi et non par son test.** J'ai compare les seize enonces a ceux d'avant
  le lot : **les seize phrases sont identiques caractere pour caractere** apres neutralisation de la
  seule marque ; **aucune etiquette de reponse n'a ete modifiee** sur aucun des seize ; et les **quatre
  items designes sont bien supprimes du fichier**, pas seulement ecartes. Aucun pixel n'a ete gagne sur
  la phrase.

#### 4. Verdict : REFUSE

**Le Done pedagogique de `S17-3` est REFUSE.** Motif, en une phrase : **sur sept items sur seize,
l'enfant ne voit pas toutes les propositions sans defiler**, de 39 a 87 px de deficit, et sur cinq de
ces sept le defaut **n'apparait que sur certains tirages**, donc en jeu et pas en relecture.

**Mon refus du 2026-08-19 reste donc en vigueur**, dans les termes ou il est ecrit. Le contenu reste
credite : les seize phrases sont justes, les indices ne nomment jamais la reponse, `SYN-1` a `SYN-4`,
`SYN-6` a `SYN-9` sont tenues. **Ce qui bloque est ce qui bloquait le 2026-08-19 : la hauteur rendue.**

**Et je ne cherche pas de moyen terme.** Je pourrais ecrire que neuf items sur seize tiennent, que le
mecanisme d'`ADR-0052` a produit un gain reel de 162 px, que `SYN-9` est impeccable - tout cela est vrai
et rien de tout cela ne fait qu'un enfant voie ses trois reponses. **`camille-po` a leve deux de ses
propres criteres ce sprint plutot que d'entamer une de mes conditions ; je ne vais pas entamer la mienne
pour lui rendre la politesse.**

#### 5. Condition neuve, datee du 2026-08-21

- **`SYN-10` (bloquante). `SYN-5` se mesure sur le pire tirage de chaque item, jamais sur un tirage.**
  Un item ne tient que s'il tient **quel que soit l'ordre dans lequel ses propositions sont tirees**.
  **Verification, au choix de qui la produit** : soit mesurer chaque item jusqu'a avoir observe tous les
  nombres de rangees qu'il peut produire, soit rendre le nombre de rangees independant de l'ordre - dans
  ce second cas une seule mesure par item suffit a nouveau.
  **Ce n'est pas un durcissement de `SYN-5`** : `SYN-5` demande depuis le 2026-08-19 que *"la derniere
  proposition de chaque item soit entierement visible"*, et c'est **ma methode de verification** qui
  etait insuffisante, pas la barre. Je corrige ma methode ; la barre n'a pas bouge d'un pixel.

**Le levier, mesure, et je ne le choisis pas** : les trois etiquettes d'un item ne tiennent jamais sur
une rangee de 320 px. Reduire la largeur totale des etiquettes, ou changer la mise en page du cabinet,
sont respectivement un choix de contenu (`camille-po`, `lea-dev`, avec repassage par `SYN-2` et `SYN-3`)
et un choix de forme (`ines-design`, mecanisme a `theo-archi`). **Ce que j'exige est le resultat** :
`SYN-5` sur les seize, au pire tirage.

**Ce que je ne demande pas** : de retirer sept items de plus. Le paquet est deja passe de vingt a seize
sur ma prediction ; le reduire a neuf ferait de la couverture une fiction, et **le nombre d'items n'a
jamais ete une de mes conditions** - `camille-po` l'a verifie a la source et elle a raison.

#### 6. Ce que cette mesure ne fait pas

- **Elle ne rouvre aucune condition tenue** et ne durcit ni `SYN-5`, ni `SYN-6` a `SYN-9`.
- **Elle ne fait pas bouger la ligne de la matiere**, qui reste `en cours` avec la mention "synonymes
  seuls, antonymes non couverts", pour un motif independant deja arbitre.
- **Elle ne touche pas au critere "20 items"** de la checklist de `camille-po`, qui figure encore dans
  cette fiche. **Fait a signaler plutot qu'a corriger** : on m'a indique qu'elle l'avait amende par note
  datee ; je ne trouve cette note **ni a la ligne du critere, ni ailleurs dans cette fiche** - sa
  decision de lever le compte vit dans `DEC-0039` et au backlog. Le critere est le sien, je ne le
  reecris pas, et je note l'ecart comme j'ai note l'absence d'`ADR-0053` le 2026-08-20.
- **Elle ne dit rien du Done technique.** Une precision pour que personne ne cherche un fantome : ma
  premiere reconstruction du jar a echoue sur le `clean` de `bootstrap`. **La cause etait moi** - un
  processus java de mon invocation precedente, morte avant que je puisse l'arreter, tenait encore le
  jar. Arrete par son PID, la reconstruction passe. **Rien dans l'arbre n'etait en cause.**

#### 7. Ce dont je reste seule garde

`SYN-5` a `SYN-10` n'ont **aucun garde-fou executable**, et cette mesure vient de montrer pourquoi le
trou est plus large qu'on ne le croyait : **la propriete a garder n'est pas dans le fichier de contenu.**
Un test qui lit le YAML - et `lea-dev` en a ecrit d'excellents, sur les points de code de `SYN-9`, sur la
suppression des quatre items, sur `SYN-8` par comparaison a des `Question` generees depuis le fichier -
**ne peut pas voir** un defaut qui n'existe qu'au rendu et qui varie selon le tirage. C'est le cas le
plus net de tout ce sprint : **des tests justes, verts, bien construits, et sept items sur seize que
l'enfant ne peut pas jouer.** Tant qu'aucun outil ne mesure une hauteur rendue, c'est moi le garde-fou,
et le fait que je me sois trompee le 2026-08-20 en ne mesurant qu'un tirage dit exactement ce que cette
situation coute.

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
