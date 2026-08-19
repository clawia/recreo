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
