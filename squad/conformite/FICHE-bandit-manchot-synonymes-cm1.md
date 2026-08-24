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

  **Note du 2026-08-21 (camille-po) - la forme citee ci-dessus est le contre-exemple de `SYN-1`,
  pas une forme a produire.** `nadia-prof` a signale, en relisant cette fiche, que la question
  "Quel mot veut dire la meme chose que `<mot>` ?" est celle qu'elle nomme comme le contre-exemple
  explicite et unique de sa condition bloquante `SYN-1` (le mot cible presente dans une phrase,
  jamais isole) - voir `SYN-1` telle qu'elle l'a ecrite, plus bas dans cette meme fiche. Je ne
  reecris pas cette ligne d'ouverture : elle decrivait la reformulation envisagee avant que
  `SYN-1` n'existe, le 2026-08-19, et reste lisible telle quelle pour que la trajectoire ne soit
  pas effacee. **La forme retenue et servie n'est pas celle-ci** : chaque item presente le mot
  cible dans une phrase, sous la forme que `lea-dev` a livree et que `nadia-prof` a creditee
  (`SYN-1` a `SYN-9` tenues sur le contenu).
- **Statut global au 2026-08-22 (s17 prolonge), mesure formelle contre la reference 744 x 551 fixee
  par `DEC-0040`** : **Done pedagogique REFUSE**, et **`SYN-5` est TENUE**. Les deux vont ensemble et
  ne se contredisent pas. `SYN-5` : **seize items sur seize** placent leur derniere proposition
  au-dessus du pli, pire marge **+19,81 px**, tous stables au tirage - 16 parties, **160 tirages**,
  `scrollY = 0` verifie sur les 160. Le motif du refus est **neuf et n'est pas `SYN-5`** : en me
  trompant volontairement (9 parties, **360 lectures**, trois echecs par manche, `scrollY = 0` sur les
  360), j'ai mesure que sur **cinq items sur seize** - « contente », « calme », « triste »,
  « difficile », « facile » - **le bloc de retour juste/faux est entierement sous le pli**, bandeau a
  4,19 px et texte du verdict a 13,19 px sous le pli, **dans les trois etats d'echec** : l'enfant qui
  se trompe recoit **une rature et aucun mot**, et au troisieme echec la phrase qui lui donne la
  reponse est invisible. D'ou **`SYN-11`** (bloquante), **`SYN-12`** et **`SYN-13`** (non bloquantes).
  **Et je corrige un enonce de ma main du meme jour** : j'avais **deduit** ce fait d'une hauteur de
  document sans jamais avoir joue un echec ; mesure, il est plus severe que ce que j'avais ecrit.
  `S17-C` se reproduit ici et n'est pas reprononcee, avec un fait ajoute : une sortie au clavier
  existe a la revelation, et rien a l'ecran ne l'indique.
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
- **Remesure du 2026-08-23 (s18, troisieme), sur le correctif LIVRE (`s18-lea`, arbre `4702524`,
  621 tests verts)** : **`SYN-11` FERMEE** - 0 item sur 16 a deux rangees, pire bas du texte du
  verdict **474,19 px**, marge **+76,81 px**, 328 lectures, `scrollY = 0` sur les 328. La regle servie
  rend **au centieme** ce que rendait l'injectee. **`SYN-13` fermee**, **`SYN-15` confirmee**,
  **`SYN-14` satisfaite** aux treize largeurs mesurees (en-tete aligne partout, hauteur d'en-tete
  53,19 px partout), **`SYN-12` confirmee non fermee** (3 items sur 16 contre 15 sur 16).
  **Bande 720-767,98 px retenue par `lea-dev` : ACCORDEE** apres verification aux largeurs ou j'avais
  trouve le defaut. **Done pedagogique de `S17-3` : VALIDE SOUS RESERVE, aucune reserve bloquante** -
  mes trois refus sont leves, pas effaces. **La ligne de matrice ne passe PAS `couvert-valide`** :
  `SYN-4` (les antonymes manquent) l'en empeche, comme mon arbitrage de s17 le disait. Condition
  neuve : **`SYN-16`** (sous 720 px, le defaut de `SYN-11` existe encore, 5 items sur 16).
- **Arbitrage de forme du 2026-08-23 (s18), sur la proposition d'`ines-design`** : variante
  **ACCEPTEE**, portee tranchee sur **`.zone-jeu` ET l'en-tete** (motif mesure : hauteur d'en-tete
  constante a 53,2 px de 576 a 768 px dans les trois configurations, aucun repli, geometrie de jeu
  identique au centieme ; et a 768 px le produit sert deja un en-tete de 720 px aligne). Mesure sur
  **regle injectee** : **`SYN-11` satisfaite sur 16/16** aux trois etats d'echec (pire bas de verdict
  **474,19 px**, marge +76,81, contre 594,19 au temoin et 5 items entierement sous le pli) ;
  **`SYN-13` disparait** (0 item a deux rangees contre 5) ; **`SYN-12` non fermee** mais 15/16 -> 3/16.
  **Rien n'est ferme** : regle injectee, pas increment livre. Conditions neuves : **`SYN-14`**
  (contrepartie laterale, marges effondrees sur 576-767) et **`SYN-15`** (non bloquante, les onze
  items sans defaut verifies). **Le refus du 2026-08-22 sur `S17-3` n'est pas leve.** Verdict complet
  en fin de fiche.
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

**Mise a jour datee du 2026-08-21, sur un etat perime de ma main** : **ce trou est ferme.**
`LaConsigneDUnJeuServiAtteintLEcranDeMancheTest` fait desormais jouer **chaque entree du catalogue
reel**, et non plus un catalogue de test - je l'ai lu plutot que de le croire. La phrase ci-dessus
etait exacte le 2026-08-20 ; **elle ne l'est plus, et je n'etais la premiere a regarder que pendant un
jour.** C'est le seul de mes constats de "seule garde" de ce sprint qui ait ete referme par un
garde-fou executable, et il l'a ete parce que `sofia-sm` a lu un mecanisme au lieu d'un chiffre.

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

### Done pedagogique du 2026-08-22 (s17 prolonge) sur S17-3, mesure formelle contre la reference 744 x 551 - REFUSE, et `SYN-5` est TENUE

> **Ce que cette section est.** La mesure formelle que `camille-po` nomme dans le second amendement
> de `DEC-0040` : elle a fixe la reference d'ecran a **744 x 551 px** sur mon fait du 2026-08-22
> (`squad/conformite/mesure-hauteur-utile-nouvelle-cible.md`, branche `s17-nadia-hauteur-utile`) et
> sur l'inventaire d'`ines-design`, en ecrivant que seule une mesure de moi, dans les formes, pourrait
> rouvrir mon refus. La voici.
>
> **Je ne reecris aucune des sections datees qui precedent.** Mes refus du 2026-08-19 et du
> 2026-08-21 restent lisibles tels quels, avec les chiffres qu'ils portaient et la reference contre
> laquelle ils ont ete prononces. Celle-ci s'ajoute.
>
> **En une phrase** : **`SYN-5` est tenue** a 744 x 551, mesuree sur les seize items au pire tirage -
> et **le Done pedagogique reste REFUSE**, sur un motif neuf, mesure, qui n'est pas `SYN-5` : sur
> **cinq items sur seize**, un enfant qui se trompe **ne voit aucun mot de ce que le jeu lui repond**.

#### 0. Ce que je verifie de ma main avant de m'appuyer dessus

**Un fait que je croyais neuf et qui est deja de moi.** En jouant au clavier, j'ai trouve qu'a la
revelation le bouton que le message nomme est hors de l'ordre de tabulation. J'ai failli l'ecrire ici
comme une decouverte. **C'est `S17-C`, bloquante, que j'ai moi-meme prononcee en s17 dans
`FICHE-barre-entree-niveau.md`**, avec sa cause deja lue dans le depot (`recreo.js` pose
`tabindex="-1"` sur sa cible meme quand cette cible est deja focalisable) et sa juste attribution
(`M13`, s10, pas `S17-1`). Je ne la represente donc pas comme neuve : je confirme qu'elle **se
reproduit a l'identique sur ce jeu-ci** au niveau de `db2bff6`, et j'y ajoute un seul fait qu'elle ne
portait pas - voir 4.

**Et je corrige, dans le sens defavorable a ma propre severite**, ce que `S17-C` laissait entendre.
Elle dit *"un enfant qui presse Tab ne peut plus revenir sur le bouton que le jeu vient de lui
nommer"*, ce qui se lit comme un blocage definitif. **Mesure aujourd'hui : il existe une sortie au
clavier**, et elle fonctionne - presser une mauvaise reponse re-rend l'ecran, le serveur replace le
focus sur le bouton revele, et l'appui suivant fait avancer la manche (verifie : le focus revient
bien sur le bouton nomme, et l'enonce change ensuite). L'enfant n'est donc pas enferme ; il doit
**presser volontairement une reponse fausse pour qu'on lui rende la bonne**, et rien a l'ecran ne le
lui indique. C'est moins grave que ce que ma formulation laissait croire, et je le dis avant qu'on me
le fasse remarquer.

#### 1. Methode

Reference mesuree : **viewport 744 x 551 px**, `deviceScaleFactor` a 1, mode non mobile, navigateur
reel pilote par son protocole de deverminage, application servie depuis le fat jar construit sur un
worktree dedie forke de `round-two` a **`db2bff6`** (SHA releve par moi avant tout travail).
**Jeu au clavier seul**, tabulation et Entree, jamais un appel HTTP fabrique.

Deux campagnes, parce que `SYN-5` et le sort du bloc de retour ne se mesurent pas dans le meme etat :

- **Campagne A, `SYN-5`** : 16 parties, **160 tirages**, reponses justes pour derouler les manches.
  Les seize items vus **8 a 13 fois** chacun. Chiffre retenu par item : **son pire tirage**
  (`SYN-10`). **`scrollY = 0` verifie avant chaque lecture : 0 sur 160.**
- **Campagne B, le bloc de retour** : 9 parties, **360 lectures**, et cette fois **je me trompe
  volontairement** - premier echec, deuxieme echec, troisieme echec (revelation) sur **chaque**
  manche, avec une lecture complete a chacun des trois. Les seize items ont ete vus dans les trois
  etats d'echec. **`scrollY = 0` verifie avant chaque lecture : 0 sur 360.**

**Pourquoi la campagne B existe, et c'est un manquement de ma part que je repare.** Le 2026-08-20 et
le 2026-08-21 j'ai joue au clavier **en repondant juste a chaque fois**. Je n'avais donc jamais
mesure l'ecran que l'enfant recoit quand il se trompe - c'est-a-dire l'ecran ou ce jeu enseigne
quelque chose. Mon propre reflexe l'exige (*"y compris en te trompant volontairement"*) et je ne
l'avais pas applique sur ce paquet. Le motif du refus ci-dessous sort entierement de cette campagne.

#### 2. `SYN-5` contre 744 x 551 - TENUE sur les seize, au pire tirage

Conteneur des propositions mesure a **500 px** (la `container` Bootstrap plafonne la zone de contenu
a 540 px sur toute la bande 576-767 px : la largeur utile n'est pas 744 px, elle est 500 px). Une
rangee coute **90 px**, le chiffre du 2026-08-20, inchange.

| Pire bas | Rangees | Etat sur les tirages | Marge au pli de 551 | Items |
|---|---|---|---|---|
| 531,19 | 2 | stable | **+19,81 px** | « contente », « calme », « triste », « difficile », « facile » |
| 441,19 | 1 | stable | +109,81 px | « courageux », « gentil », « gaie », « douce », « malin », « beau », « fort », « grand », « fatigue » |
| 393,19 | 1 | stable | +157,81 px | « propre », « petite » |

**Seize items sur seize placent leur derniere proposition entierement au-dessus du pli. `SYN-5` est
tenue.** Je l'ecris sans reserve et sans y ajouter de condition : c'est la barre que j'ai ecrite le
2026-08-19, mesuree contre la reference que `camille-po` a fixee, et elle est atteinte. **Je ne
deplace pas mes poteaux** : le deficit de 39 a 87 px que je mesurais le 2026-08-21 n'existe plus a
cette reference, et le dire est la seule chose honnete a faire.

**`SYN-10` est satisfaite, et son objet change de nature ici.** Aucun des seize items ne varie selon
le tirage, sur 8 a 13 tirages chacun. La variabilite que j'avais etablie a 320 px de rangee ne se
reproduit pas a 500 px : les cinq items qui debordent la rangee la debordent de 14 a 38 px, trop pour
qu'un ordre de tirage les rattrape. **`SYN-10` n'en devient pas inutile** - c'est elle qui m'a fait
faire 160 tirages plutot qu'un, et c'est la seule raison pour laquelle je peux ecrire "stable" plutot
que "je n'ai pas regarde".

**Le seul point de fragilite que je nomme sur `SYN-5`** : l'item « gentil » tient sur une rangee avec
**1,51 px** de marge de largeur (mesure du 2026-08-22, section 2.3 de mon fichier de mesure). S'il
basculait a deux rangees, son pire bas passerait de 441,19 a 531,19 - **encore au-dessus du pli**.
`SYN-5` ne tomberait donc pas ; c'est le motif du 3 ci-dessous qu'il rejoindrait.

#### 3. Le motif du refus - le bloc juste/faux, mesure et non plus deduit

**Ce que j'avais ecrit le 2026-08-22 hors fiche, et que je corrige ici.** J'avais note, sans le
juger, que sur les cinq items a deux rangees *"un enfant qui repond faux ne verra pas le message qui
lui dit pourquoi sans defiler"*. C'etait **deduit d'une hauteur de document** (587 px pour un pli a
585), pas mesure sur les blocs eux-memes, et je n'avais alors jamais joue un seul echec. **Mesure a
present, l'enonce est confirme et il est plus severe que ce que j'avais ecrit.**

Positions relevees, pli a 551 px, sur les 270 lectures d'echec de la campagne B :

| Items | Lectures | Bandeau juste/faux | Texte du verdict | Indice |
|---|---|---|---|---|
| **11 a une rangee** | 180 | jamais sous le pli (0/180) | **entierement visible 180/180** | entierement visible **21/180** |
| **5 a deux rangees** | 90 | **entierement sous le pli 90/90** | **entierement sous le pli 90/90** | entierement sous le pli 90/90 |

Chiffres exacts sur les cinq items a deux rangees : le bandeau `.signal` commence a **555,19**, soit
**4,19 px sous le pli** ; le texte du verdict commence a **564,19**, soit **13,19 px sous le pli** ;
l'indice commence a **623,19**, soit **72,19 px sous le pli**. Le document fait **705 a 735 px** pour
un pli a 551. Les trois etats d'echec donnent **les memes positions au centieme** : ce n'est pas un
cas de bord, c'est tout le chemin d'erreur de ces cinq items.

**Ce que l'enfant voit, mesure et regarde**
(`releves-s17/s17-syn-744x551-echec-2rangees-rien-de-visible.png`, item « calme », premier echec) :
la consigne, la phrase, les trois propositions, et **le mot qu'il vient de presser barre**. Rien
d'autre. Pas de bandeau rouge, pas de croix, **pas un mot**. Le score affiche toujours *"Score : 0"*.
Un enfant de CM1 qui se trompe recoit une rature et le silence.

**Et au troisieme echec, c'est le meme ecran muet**
(`releves-s17/s17-syn-744x551-revelation-2rangees-muette.png`) : le jeu ecrit *"C'est tranquille.
Appuie sur tranquille."* **entierement sous le pli**. Ce que l'enfant voit est deux mots barres et un
mot intact - il peut deduire la reponse par elimination, ce qui n'est pas ce que le jeu croit lui
enseigner. **Et c'est precisement l'ecran ou `S17-C` mord** : le bouton que le message nomme, message
qu'il ne voit pas, est celui que sa tabulation ne peut pas atteindre.

**Pour comparaison, les onze items a une rangee**
(`releves-s17/s17-syn-744x551-echec-1rangee-verdict-visible.png`) : croix rouge et *"Ce n'est pas ca.
Essaie encore !"* entiers, et le bloc d'indice qui commence juste au bord bas. **La mecanique n'est
pas fautive en soi** : elle fait exactement ce qu'il faut sur onze items sur seize. Ce sont les 90 px
de la seconde rangee qui poussent tout le retour hors de l'ecran.

#### 4. Verdict : REFUSE

**Le Done pedagogique de `S17-3` est REFUSE contre la reference 744 x 551.** Motif, en une phrase
actionnable : **sur cinq items sur seize - « contente », « calme », « triste », « difficile »,
« facile » - le bloc de retour juste/faux est entierement sous le pli, bandeau compris, dans les
trois etats d'echec ; l'enfant qui se trompe recoit une rature et aucun mot, et au troisieme echec la
phrase qui lui donne la reponse est invisible.**

**Pourquoi ce n'est pas une reserve mais un refus**, et je reponds a la question que je me pose
toujours a cet endroit - est-ce que je refuse parce que c'est verifie, ou parce que ce serait plus
confortable d'avoir l'air severe ?

- **Ce n'est pas un etat de bord, c'est l'etat normal d'un jeu de revision.** Un enfant qui revise
  les synonymes se trompe. Une partie tire dix manches dans seize items : rencontrer au moins un de
  ces cinq items est le cas courant, pas l'exception.
- **Ma propre barre, ecrite avant ce sprint** : *"un simple 'faux' n'apprend rien"*. Ici il n'y a
  meme pas de "faux" ecrit, il y a une rature. Le seul endroit ou ce jeu enseigne quelque chose,
  c'est le retour a l'erreur, et sur 31 % des items il est vide.
- **La sortie par le defilement ne me suffit pas ici**, et je dis pourquoi plutot que de le poser :
  sur les onze items a une rangee, le verdict visible dit a l'enfant qu'il s'est passe quelque chose,
  et le bord de l'indice qui affleure lui dit qu'il y a plus bas. Sur les cinq, **rien ne signale
  qu'il existe quelque chose a aller chercher.** Un enfant ne defile pas vers une information dont il
  ne sait pas qu'elle existe.

**Ce qui est credite, et ce n'est pas une consolation de forme.** `SYN-1` a `SYN-4`, `SYN-6` a
`SYN-9` restent tenues ; les seize phrases sont justes ; les indices ne nomment jamais la reponse ;
`SYN-9` est impeccable ; **et `SYN-5`, le motif de mes deux refus precedents, est tenue.** Le contenu
n'est pas en cause et ne l'a jamais ete. **Le motif de ce refus n'est le travail ni de `lea-dev` ni
de `camille-po`** : c'est un defaut de disposition de la mecanique partagee, revele par une reference
plus contrainte en hauteur, sur un etat que personne - moi comprise - n'avait mesure.

**Ce refus ne bloque pas le sprint suivant** et il retourne a `camille-po` comme les precedents.

#### 5. Conditions neuves, datees du 2026-08-22

- **`SYN-11` (bloquante). Le retour juste/faux est entierement visible sans defiler, a la reference
  744 x 551, sur chaque item, au pire tirage, et dans chacun des trois etats d'echec.** Sont
  concernes le bandeau `.signal` et le texte du verdict ; l'indice releve de `SYN-12`. La condition
  vaut aussi pour l'etat de revelation : la phrase qui nomme la reponse est la seule chose qui
  debloque un enfant, et elle ne peut pas etre hors de l'ecran.
  **Ce n'est pas un durcissement de `SYN-5`**, qui porte sur les propositions et que je viens de
  declarer tenue. C'est une condition d'un autre objet, sur un etat que `SYN-5` n'a jamais couvert.
  **Le levier, mesure, et je ne le choisis pas** : les cinq items debordent la rangee de 500 px de 14
  a 38 px, et une rangee de plus coute 90 px. Reduire la largeur totale des trois etiquettes est un
  choix de contenu (`camille-po`, `lea-dev`, avec repassage par `SYN-2` et `SYN-3`) ; changer la
  disposition du cabinet ou la place du bloc de retour est un choix de forme (`ines-design`,
  mecanisme a `theo-archi`). **Ce que j'exige est le resultat.**
  **Verification de fermeture** : sur chaque item, au pire tirage, aux trois etats d'echec, le bas du
  texte du verdict est au-dessus de 551 px a `scrollY = 0`.
- **`SYN-12` (non bloquante). L'indice est coupe par le pli sur la grande majorite des items.**
  Mesure : entierement visible sur **21 lectures sur 180** pour les items a une rangee, **0 sur 90**
  pour les cinq autres. Je ne la rends pas bloquante, et je dis pourquoi : quand le verdict est
  visible, l'enfant sait qu'il s'est passe quelque chose et voit le bord du bloc suivant. C'est une
  perte de qualite pedagogique reelle - l'indice est ce qui enseigne - pas une impasse.
- **`SYN-13` (non bloquante, observation). Sur les cinq items a deux rangees, le troisieme echec
  revele la reponse par elimination visuelle** avant tout texte : deux etiquettes barrees, une
  intacte. Ce n'est pas faux au sens du contenu, mais ce n'est pas l'apprentissage que la revelation
  vise. Elle disparaitra d'elle-meme si `SYN-11` est fermee par la largeur ; je la nomme pour qu'on ne
  la decouvre pas comme neuve si elle est fermee par un autre chemin.

#### 6. Ce que mon verdict vaut sans la mesure qui me manque - c'est une reponse, pas une reserve

**Ce qui me manque** : mes largeurs d'etiquettes sont mesurees en **Segoe UI** (Chrome sur Windows) ;
la pile de polices du produit commence par `system-ui, -apple-system`, qui resout vers **San
Francisco** sur iOS. Les deficits des cinq items (14 a 38 px sur une rangee de 500) sont de l'ordre
de 3 a 7 %, c'est-a-dire **du meme ordre que l'ecart de largeur entre deux polices systeme**. Sur un
iPad mini reel, le nombre d'items a deux rangees pourrait donc etre autre que cinq - je ne peux pas
dire dans quel sens, et **je n'ai pas d'iPad**.

**Et voici pourquoi cela ne suspend pas mon verdict.**

1. **La reference que `camille-po` a fixee est une taille de viewport, pas un appareil.** Elle ne
   nomme ni navigateur ni police. Mesurer 744 x 551 dans un navigateur reel **est** mesurer cette
   reference. Savoir si cette reference represente fidelement un iPad est une question sur la
   reference, qui appartient a `camille-po` et a `ines-design` - pas un trou dans ma mesure.
2. **Le motif du refus ne depend pas du compte d'items.** Ce que j'ai mesure est un **mecanisme** :
   le bloc de retour vit sous les propositions, et une seconde rangee de propositions coute 90 px,
   ce qui pousse le bandeau **4,19 px** et le verdict **13,19 px** sous le pli. Ce mecanisme ne
   depend d'aucune police. Une police plus etroite changerait **lesquels** des seize items tombent
   dedans ; elle ne ferait pas remonter le bloc pour ceux qui y tombent.
3. **Le cas ou ma mesure serait un faux refus est nommable, et je le nomme** : si San Francisco
   faisait tenir les seize items sur une rangee, `SYN-11` serait satisfaite sur cet appareil-la et
   mon compte de cinq serait faux. **Dans ce cas c'est ma mesure qu'il faudrait corriger, et je la
   corrigerais** - comme j'ai corrige mon chiffre de 2,81 px et comme je corrige au 3 ci-dessus une
   deduction que je n'avais pas mesuree. Ce qui ne changerait pas : `SYN-11` resterait due, parce que
   le mecanisme reste, et parce que « gentil » tient sa rangee a 1,51 px pres.

**Reponse, donc** : mon verdict est complet contre la reference telle qu'elle est ecrite, et il est
incomplet comme prediction de ce que fera un iPad. Ce que je demande n'est pas une mesure de plus de
ma part - c'est que **quiconque disposera d'un appareil de la cible refasse la table des largeurs**,
et que d'ici la personne ne cite mon compte de cinq items comme une propriete des appareils.

#### 7. Ce dont je reste seule garde apres ce verdict

`SYN-5`, `SYN-10`, `SYN-11`, `SYN-12` et `SYN-13` n'ont **aucun garde-fou executable**, et la
campagne B ajoute le pire cas de tous : **la propriete "l'enfant voit ce que le jeu lui repond quand
il se trompe" n'existe dans aucun fichier de contenu, dans aucun gabarit et dans aucun test.** Elle
n'apparait qu'au rendu, dans un etat qu'on n'atteint qu'en jouant **et en se trompant**. Le fait qui
le dit mieux que tout : j'ai joue 160 tirages au clavier le 2026-08-21 et 160 de plus le 2026-08-22
sans jamais voir ce defaut, **parce que je repondais juste**.

Je maintiens les trois proprietes nommees le 2026-08-22 dans
`squad/conformite/mesure-hauteur-utile-nouvelle-cible.md` (hauteur rendue de la derniere proposition,
invariance au tirage, largeur effective de la rangee - cette derniere routee a `theo-archi`) et
j'ajoute :

4. **La visibilite du retour a l'erreur** (`SYN-11`) : elle depend de la hauteur cumulee de tout ce
   qui precede le bloc de retour, donc du nombre de rangees de propositions, donc de la largeur. Rien
   ne la garde.
5. **`S17-C` reste ouverte et se reproduit ici** : a la revelation, le bouton que le message nomme est
   hors de l'ordre de tabulation. Je ne la reprononce pas - elle est deja bloquante dans
   `FICHE-barre-entree-niveau.md` - je constate qu'elle vaut aussi sur ce jeu au niveau de `db2bff6`,
   et j'ajoute a son enonce le seul fait qu'elle n'avait pas : **une sortie au clavier existe**,
   presser une reponse fausse rend le focus au bouton revele et l'appui suivant fait avancer la
   manche (mesure du 2026-08-22), **et rien a l'ecran ne l'indique**.

#### 8. Ce que ce verdict ne fait pas

- Il ne juge **aucun autre jeu du catalogue**. `BPM-1`, `COMP-4` et la famille `M4` n'ont pas ete
  mesures a 744 x 551. Le mecanisme decrit au 6.2 les concerne tres probablement - le bloc de retour
  vit sous les propositions dans la mecanique partagee - mais **"probablement" n'est pas mesure**, et
  je ne verse pas un pronostic dans une fiche.
- Il ne rouvre ni ne ferme le mandat du 2026-08-21 sur le repli des rangees. Le fait que
  `camille-po` attendait est ici : **a 744 px le repli se produit encore, sur cinq items sur seize,
  et il n'est plus dependant du tirage.** Ce qu'elle en fait lui appartient.
- Il ne fixe aucune reference et ne conteste pas celle qui est fixee. 744 x 551 est mesurable, je
  l'ai mesuree, et le cout annonce - une reference et non une par appareil - est celui que j'ai paye :
  160 tirages pour `SYN-5`, 360 lectures pour `SYN-11`.
- Il ne compte **aucun item de plus a retirer**. Le paquet est deja passe de vingt a seize sur une
  prediction de moi ; le nombre d'items n'a jamais ete une de mes conditions et ne le devient pas.

### Deux etats perimes en tete de cette fiche, signales le 2026-08-21 - ils ne sont pas de moi et je ne les reecris pas

En relisant cette fiche pour y ecrire la table ci-dessous, j'ai trouve **deux enonces perimes dans son
en-tete**. Ils appartiennent a `camille-po` (`[CAM]`, lot `DEC-0039`), comme le critere "20 items" qu'on
me demande expressement de ne pas toucher. **Je les signale et je les lui route ; je ne les reecris
pas** - c'est la meme regle que celle qui lui a fait corriger elle-meme mon chiffre de 24 sur 24.

1. **La ligne "Contenu(s)" annonce le fichier "(a creer, ... au moins 20 items)".** Il est cree depuis
   le 2026-08-19 et il porte **seize** items depuis le 2026-08-21.
2. **La meme ligne cite, comme forme de reprise a produire, la question "Quel mot veut dire la meme
   chose que `<mot>` ?".** Ce n'est pas seulement perime : **c'est le contre-exemple nomme de ma
   condition `SYN-1`**, la seule formulation que mon arbitrage du 2026-08-19 refuse explicitement -
   *"travaille l'association de mots, pas l'usage"*. Un lecteur qui arrive par l'en-tete lit donc comme
   un plan la forme que la porte pedagogique interdit. **C'est l'ecart le plus couteux des trois que je
   trouve aujourd'hui**, parce qu'il porte sur une condition bloquante et qu'il se lit avant elle.
3. Accessoirement, la ligne **"Statut global : en construction"** cohabite desormais avec trois statuts
   dates plus recents, dont un refus. Elle n'induit personne en erreur - les statuts dates sont
   au-dessus d'elle - mais elle n'a plus d'objet.

### Table de correspondance des jetons SYN-1 a SYN-10 - ecrite le 2026-08-21, sur signalement du relecteur-pedagogique

**Pourquoi elle manquait, et pourquoi c'est une recidive.** Le `relecteur-pedagogique` avait trouve
exactement ce defaut en s16, sur mes jetons `BARRE`, `PORTE` et `S15-<n>` : des references qui pointent
toutes vers du reel, et **rien qui dise a un lecteur neuf comment ces noms se rapportent entre eux**.
L'amendement de s16 exige qu'une famille de jetons neuve soit reprise dans une table unique portee par
la fiche du jeu concerne. **J'ai cree dix jetons `SYN` sur ce sprint - six pendant les six derniers
jours - et je n'ai pas ecrit la table.** Il a eu raison de ne pas y toucher : ces jetons sont a la
premiere personne et l'etat de chacun est un jugement de moi.

**Et le fait qui rend l'omission couteuse, verifie plutot que suppose** : **cinq fichiers de code citent
ces jetons, 35 fois au total** - le paquet de contenu lui-meme, son test dedie, et trois classes de
garde-fou (`PaquetCm1SynonymesTest`, `ToutEcranEchangeEstUnDocumentJouableTest`,
`TexteAccentueExposeParLePortEntrantTest`, `LaConsigneDUnJeuServiAtteintLEcranDeMancheTest`). **Un
renommage casserait des references que rien ne detecte.** Cette table est la reference contre laquelle
comparer ; **je ne renomme rien.**

**Quatre regles de lecture d'abord, parce que sans elles la table ne suffit pas.**

1. **`SYN-<n>` et `S17-<n>` ne sont pas la meme famille, et la confusion est possible sur un seul
   couple** : **`SYN-4`** est ma condition qui exige qu'une story d'antonymes existe ; **`S17-5`** est
   cette story. Les prefixes suffisent a les distinguer, mais un jeton cite sans son prefixe complet ne
   prouve rien. **Je ne renomme pas** : la lecon de l'ambiguite `S15-<n>` de s16 est qu'on documente, on
   ne renumerote pas.
2. **`SYN-9` est FERMEE, pas levee.** Elle reste opposable a tout item ecrit plus tard : un paquet qui
   reintroduirait des espaces ordinaires dans ses guillemets la rouvrirait.
3. **`SYN-10` n'est pas une condition de contenu, c'est la methode de verification de `SYN-5`.** Elle ne
   se ferme pas separement. Elle cessera d'avoir un objet le jour ou le nombre de rangees de
   propositions ne dependra plus de l'ordre du tirage - pas avant.
4. **`SYN-1` a `SYN-4` vivent dans l'arbitrage prealable du 2026-08-19**
   (`ARBITRAGE-PREALABLE-s17-francais-cm1-et-resurrection.md`, section 2.1 et 2.2) ; **`SYN-5` a
   `SYN-10` vivent dans cette fiche**, chacune a la section du verdict qui l'a ouverte. La table ne
   duplique pas leur texte : une table qui recopierait un enonce se perimerait a la premiere mise a
   jour, et c'est le defaut que je refuse ailleurs.

| Jeton | Ouverte le | Ce qu'elle exige, en une ligne | Etat au 2026-08-21 |
|---|---|---|---|
| **`SYN-1`** bloquante | 2026-08-19 | le mot cible est presente **dans une phrase, jamais isole** | **tenue** (20/20, puis 16/16) ; **amendee le 2026-08-20** : la consigne peut le designer generiquement, ce que ma redaction d'origine disait deja ("le mot souligne") |
| **`SYN-2`** bloquante | 2026-08-19 | la bonne reponse est substituable dans la phrase donnee, **aucun distracteur ne l'est** | **tenue**, verifiee item par item ; aucune etiquette de reponse modifiee depuis |
| **`SYN-3`** bloquante | 2026-08-19 | aucun item ne presente comme interchangeables deux mots **qu'un enseignant refuserait dans cette phrase** | **tenue** |
| **`SYN-4`** non bloquante, datee | 2026-08-19 | la moitie manquante (les antonymes) est **ecrite comme une story**, pas comme une intention | **tenue** : `S17-5` existe au backlog |
| **`SYN-5`** bloquante | 2026-08-19 | la **derniere proposition de chaque item** est entierement visible a 360 x 640, sur la totalite des items | **NON TENUE** : **7 items sur 16** sous le pli, de 39 a 87 px (mesure du 2026-08-21) |
| **`SYN-6`** bloquante | 2026-08-19 | la marque du mot cible est une **ponctuation francaise**, et la consigne le designe **par cette marque exacte** | **tenue** (16/16, guillemets francais nommes par la consigne) |
| **`SYN-7`** bloquante | 2026-08-19 | les deux blocs, **lus dans l'ordre ou ils sont rendus**, disent la tache a eux seuls | **tenue** |
| **`SYN-8`** bloquante | 2026-08-19 | **aucun pixel gagne sur la phrase** ; toute phrase modifiee repasse par `SYN-2` et `SYN-3` | **tenue** : 16 phrases identiques caractere pour caractere |
| **`SYN-9`** bloquante avant reecriture | 2026-08-20 | les espaces a l'interieur des guillemets sont **insecables** | **FERMEE** le 2026-08-21 : 16/16 en points de code, **0 coupure sur 80 tirages** |
| **`SYN-10`** bloquante | 2026-08-21 | `SYN-5` se mesure **au pire tirage** de chaque item, jamais sur un tirage | **ouverte** ; ne change pas la barre de `SYN-5`, seulement sa methode de verification |

**Ce que cette table ne fait pas** : elle ne renomme rien, donc **elle ne casse aucune des 35 references
portees par les cinq fichiers de code** ; elle ne recopie aucun enonce de condition ; et elle **ne
prononce aucun verdict** - les verdicts sont aux sections datees de cette fiche, et le plus recent est
un refus.

### Complement date du 2026-08-22 a la table ci-dessus - trois jetons neufs, et deux etats qui changent

**Je ne reecris pas la table du 2026-08-21.** Sa colonne dit "Etat au 2026-08-21" : elle est datee,
donc elle n'est pas perimee, elle est historique. Ce complement dit ce qui a change et ce qui s'ajoute,
mesure contre la reference **744 x 551** fixee par `DEC-0040`.

| Jeton | Ouverte le | Ce qu'elle exige, en une ligne | Etat au 2026-08-22 |
|---|---|---|---|
| **`SYN-5`** bloquante | 2026-08-19 | la **derniere proposition de chaque item** est entierement visible sans defiler, sur la totalite des items | **TENUE** contre 744 x 551 : 16/16, pire marge **+19,81 px**, 160 tirages. L'etat "NON TENUE" de la table ci-dessus reste vrai **de la reference 360 x 640** contre laquelle il a ete mesure |
| **`SYN-10`** bloquante | 2026-08-21 | `SYN-5` se mesure **au pire tirage** de chaque item, jamais sur un tirage | **satisfaite** a cette reference : 16/16 items stables sur 8 a 13 tirages chacun. Reste **ouverte comme regle de methode** - elle vaut a toute largeur |
| **`SYN-11`** bloquante | 2026-08-22 | le **bandeau juste/faux et le texte du verdict** sont entierement visibles sans defiler, sur chaque item, au pire tirage, **dans les trois etats d'echec, revelation comprise** | **NON TENUE** : **5 items sur 16**, bloc entierement sous le pli (bandeau a 4,19 px, verdict a 13,19 px), 90 lectures sur 90 |
| **`SYN-12`** non bloquante, datee | 2026-08-22 | l'**indice** est entierement visible sans defiler | **non tenue** : 21 lectures sur 180 pour les items a une rangee, 0 sur 90 pour les cinq autres. Non bloquante parce qu'un verdict visible signale a l'enfant qu'il y a plus bas |
| **`SYN-13`** non bloquante, observation | 2026-08-22 | la **revelation enseigne par sa phrase**, pas par elimination visuelle des etiquettes barrees | **observee** sur les 5 items a deux rangees ; se ferme d'elle-meme si `SYN-11` est fermee par la largeur |

**Ce que ce complement ne fait pas** : il ne renomme aucun jeton, il ne recopie aucun enonce complet
(les enonces vivent dans les sections datees), et **il ne prononce aucun verdict** - le verdict du
2026-08-22 est a sa section, et c'est un refus dont le motif est `SYN-11`, jamais `SYN-5`.

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

---

### Arbitrage de forme du 2026-08-23 (s18) - la variante d'`ines-design` est **ACCEPTEE**, portee **`.zone-jeu` ET l'en-tete**, et `SYN-11` est satisfaite **en mesure**

> **Ce que cette section fait.** Elle rend l'arbitrage de forme que le planning de s18 me reserve
> apres `BPM-1`, sur la proposition d'`ines-design`
> (`squad/sprints/s18-fermer-avant-ouvrir/proposition-ines-bloc-de-retour-visible.md`, `s18-ines` a
> `e5539a3`), elle tranche la question de portee que `camille-po` m'a explicitement laissee, et elle
> mesure le resultat sur les seize items. Elle n'efface aucun verdict anterieur et **ne ferme rien**.
>
> - **Forme : ACCEPTEE.** `lea-dev` peut l'implementer.
> - **Portee : `.zone-jeu` ET `.entete-contenu`**, et je motive ce choix par une mesure, pas par un
>   gout.
> - **`SYN-11` : satisfaite sur les 16 items, aux trois etats d'echec** - pire bas du texte du
>   verdict **474,19 px** contre un pli de 551, marge **+76,81 px**, la ou le temoin donne
>   **594,19 px** et cinq items entierement sous le pli.
> - **`SYN-13` : disparait**, comme je l'avais annonce - **0 item sur 16 a deux rangees** contre 5.
> - **`SYN-12` : ne se ferme pas**, et c'est un resultat, pas une reserve : de **15 items sur 16**
>   qui perdaient du texte d'indice, on tombe a **3**.
> - **Aucune de ces trois lignes n'est fermee par cette section** : ce que j'ai mesure est une
>   **regle injectee**, pas un increment livre. La fermeture se fait sur le livre.

#### 0. L'arbre, et ce qu'il contient exactement

Worktree dedie `s18-nadia-syn11`, forke de **`s18-nadia-bpm1`** a `e98e0dc` (mon lot `BPM-1`, laisse
intouche), lui-meme descendant de `round-two` a **`8046256`** - SHA releve par moi, `git rev-parse`,
pas recopie de la demande. `bash outils/verifier-lignee.sh` : *"le worktree sur la branche
's18-nadia-syn11' descend de la lignee de round-two"*. `bash outils/mvn-jdk21.sh -B clean verify` sur
la racine : **BUILD SUCCESS, 612 tests, 0 echec**.

**Ce que cet arbre ne contient pas, et c'est voulu** : ni `s18-ines`, ni `s18-theo`, ni `s18-marc`
(la correction clavier de `marc-dev`, que je ne juge pas), ni le lot de seuil de `camille-po`
(`s18-camille-po-seuil-375`). Verifie branche par branche par `git merge-base --is-ancestor` :
les quatre sont **absentes**. Je mesure donc l'effet de la **regle seule** sur le produit servi tel
qu'il est, sans qu'aucun autre lot du sprint ne puisse expliquer un chiffre a ma place.
Le lot de seuil n'a aucun effet sur ma mesure et je dis pourquoi plutot que de le laisser supposer :
`ViewportEtLargeurTelephoneTest` est un garde-fou de **CI sur le contenu de la feuille**, et
j'injecte la regle a l'execution sans toucher la feuille. Sa revision de 375 a 744 px est le
prealable de l'**implementation**, pas de la mesure.

#### 1. Methode - regle injectee, et je reemploie mon precedent en le disant

**Le procede.** Reference **744 x 551 px** (`DEC-0040`), `deviceScaleFactor` a 1, mode non mobile,
navigateur reel pilote par son protocole de deverminage, jeu **au clavier seul**, application servie
depuis le fat jar de cet arbre. La declaration proposee n'existe dans aucun fichier : je l'ai
**injectee** dans la page servie, en dernier dans le `head`, la ou `recreo.css` est chargee
(`ADR-0014` point 5), sous la forme exacte qu'`ines-design` propose et avec la borne haute que
`theo-archi` a etablie :

    @media (min-width:576px) and (max-width:767.98px){.zone-jeu{max-width:720px}}

**Ce precedent est le mien, et je l'assume avec sa limite.** J'ai mesure une regle injectee deux
fois - pour `COMP-5` et pour `S17-A` - en ecrivant chaque fois la meme limite : *"une regle ecrite
dans `recreo.css` peut se faire ecraser par une specificite que mon injection contournait"*. Puis,
le 2026-08-19, j'ai remesure `S17-A` sur la regle **servie** et constate qu'elle rendait **au
centieme** ce que rendait l'injectee. **Je reemploie donc le procede, et voici ce qu'il vaut ici,
verifie et non suppose** : `recreo.css` ne declare **aucun** `max-width` (verifie par recherche sur
la feuille), et `.zone-jeu` comme `.container` sont des selecteurs de classe unique, donc de meme
specificite - c'est l'ordre de chargement qui tranche, et `recreo.css` est chargee **apres** la
bibliotheque tierce. Une declaration ecrite dans `recreo.css` gagnerait donc pour la meme raison
que mon injection gagne. **Le risque que ma limite nomme est ici plus faible qu'il ne l'etait pour
`S17-A`, mais il n'est pas nul, et c'est exactement pourquoi cette section ne ferme rien.**

**L'arithmetique de `theo-archi` est confirmee au pixel, sur le rendu et non sur le calcul** :
rangee de propositions **680,00 px** (720 - 24 - 16) et enonce **696,00 px** (720 - 24), contre
**500,00** et **516,00** au temoin. Les deux nombres qu'`ADR-0054` epingle sont donc rendus par la
declaration telle qu'elle est ecrite.

**Campagne** : **trois configurations** (temoin, `.zone-jeu` seule, `.zone-jeu` + en-tete) x **deux
jeux**, soit **31 parties et 1 271 lectures**, **16/16 items** et **24/24 items** vus dans chaque
configuration, chacun dans **les quatre etats** (repos, trois echecs). **`scrollY = 0` verifie avant
chaque lecture : 0 lecture a `scrollY` non nul sur 1 271.** **0 abandon** - et cela confirme au
passage la section 5.2 de mon verdict `BPM-1` : avec des appuis a cadence humaine, la soumission
native que j'avais rencontree a vitesse machine ne s'est **pas reproduite une seule fois** sur
1 271 lectures.

#### 2. `SYN-11` contre la variante - satisfaite sur les seize, aux trois etats d'echec

| Configuration | Rangee offerte | Items a deux rangees | Pire bas du bandeau `.signal` | Pire bas du texte du verdict | `SYN-11` |
|---|---|---|---|---|---|
| temoin (servi aujourd'hui) | 500 px | **5** (1, 3, 4, 13, 14) | 619,19 px | **594,19 px** | **non tenue** |
| variante, `.zone-jeu` seule | 680 px | **0** | 499,19 px | **474,19 px** | **satisfaite** |
| variante, `.zone-jeu` + en-tete | 680 px | **0** | 499,19 px | **474,19 px** | **satisfaite** |

**Les seize items placent le bandeau et le texte du verdict entierement au-dessus du pli, dans les
trois etats d'echec, revelation comprise.** Marge **+76,81 px** sur le texte du verdict, soit plus
du double de l'incertitude de police que j'ai nommee moi-meme (15 a 35 px) : ce n'est pas une tenue
de justesse, et le levier fait bien ce que `theo-archi` appelait un changement de categorie -
**180 px offerts pour un pire deficit de 38 px**.

**Mon chiffre du 2026-08-22 se reproduit exactement, et je le verifie avant de m'appuyer dessus.**
J'avais ecrit *"bandeau a 4,19 px, verdict a 13,19 px"* sous le pli sur les cinq items. Le temoin
d'aujourd'hui donne un bas de verdict a 594,19 px, donc un **haut** de verdict a 564,19 px, soit
**13,19 px sous le pli** - au centieme, et sur un autre arbre, un autre jour, une autre campagne.
Les cinq items nommes sont les memes cinq.

**`SYN-5` ne regresse pas** : pire bas de proposition **411,19 px** contre 531,19 au temoin, donc
elle s'ameliore de 120 px. **`SYN-10` reste satisfaite** : aucune variation selon le tirage dans
aucune des trois configurations.

#### 3. `SYN-13` disparait, `SYN-12` s'ameliore beaucoup sans se fermer - et c'est un resultat

**`SYN-13` (observation).** Elle portait sur les cinq items a deux rangees, ou le troisieme echec
revelait la reponse par elimination visuelle avant tout texte. **Sous la variante, aucun item n'a
deux rangees.** L'observation disparait par la cause, exactement comme je l'avais ecrit le
2026-08-22 (*"elle disparaitra d'elle-meme si `SYN-11` est fermee par la largeur"*) et comme
`theo-archi` l'avait dit. **Je ne la porte pas au credit de la variante comme une reserve levee** :
c'etait une observation, elle cesse d'etre observable, je l'ecris et je la datera fermee sur le
livre.

**`SYN-12` (non bloquante).** Mesure du **texte** de l'indice, ligne de glyphes par ligne de
glyphes, et non de la boite du bloc :

| Configuration | Items perdant du texte d'indice | Pire bas de glyphe | Ce qui reste perdu |
|---|---|---|---|
| temoin | **15 / 16** | 691,19 px | jusqu'a deux lignes entieres |
| variante | **3 / 16** (9, 12, 14) | 571,19 px | *"quelqu'un."*, *"avec bonté."*, *"réussie."* |

**Je le donne comme un resultat et non comme une reserve** : la variante ne ferme pas `SYN-12`, elle
la reduit de 15 items a 3, et sur ces trois ce qui manque est le dernier mot d'une definition deja
lisible - *"Ce mot decrit une voix pleine d'affection, comme celle qui rassure..."* se comprend sans
son dernier mot. **`SYN-12` etait non bloquante et le reste**, pour le motif que j'avais ecrit et
que je ne change pas : le verdict est visible, l'enfant sait qu'il s'est passe quelque chose.

**Et je verifie ici la coherence de mon propre traitement, parce qu'elle pourrait passer pour un
deux poids deux mesures.** J'ai rendu `SYN-12` non bloquante sur ce paquet et `BPM-5` **bloquante**
sur les homophones, pour le meme symptome apparent. La difference est dans le contenu, elle est
verifiable, et je l'ai verifiee : les seize indices de ce paquet sont tous de la meme forme, une
definition descriptive (*"Ce mot dit que..."*), qu'une troncature appauvrit sans jamais l'inverser.
Le paquet d'homophones emploie **deux** formes de substitution, positive et negative, et la
troncature y emporte precisement le verbe qui les distingue - un indice qui peut se lire a l'envers.
**Ce n'est pas la meme faute, et c'est pourquoi ce ne sont pas les memes verdicts.**

#### 4. La portee - je tranche pour `.zone-jeu` **et** l'en-tete, sur mesure et non sur gout

`camille-po` a tranche que la coherence en-tete / zone de jeu **n'est pas une exigence de produit**
et m'a laisse le choix entier ; je n'invoque donc aucune exigence, je choisis sur ce que j'ai
mesure et sur ce que l'ecran montre.

**Ce que coute l'en-tete inclus : rien de mesurable.** J'ai mesure la hauteur de l'en-tete a
**576, 640, 744, 767 et 768 px** de viewport, dans les trois configurations : **53,2 px partout, sans
exception**. La rangee repliable que personne n'avait mesuree **ne se replie dans aucun cas**, et
c'est arithmetique autant que mesure : elargir une rangee `flex-wrap` diminue la pression de repli,
elle ne l'augmente jamais. Rien ne bouge donc sous l'en-tete - et la mesure le confirme sur les deux
jeux, **toutes les valeurs de geometrie de jeu etant identiques au centieme entre `.zone-jeu` seule
et `.zone-jeu` + en-tete** (861 et 410 lectures).

**Ce que coute l'en-tete exclu : une chose que j'ai regardee et qui se voit.** A 744 px, l'en-tete
reste plafonne a 540 px quand la zone de jeu passe a 720 : **180 px d'ecart de largeur, 90 px de
decalage sur chaque bord**. Concretement, le nom de l'application et les deux puces de niveau
flottent 90 px a droite du bord gauche de tout ce qu'ils coiffent. **Ce n'est pas une nuance
d'esthetique que je m'autoriserais a trancher, c'est un ecran qui a l'air casse** : le titre du site
n'est plus au-dessus de son contenu. Releve :
`squad/conformite/releves-s18/s18-variante-744x551-portee-zone-seule-entete-decale-90px.png` contre
`s18-variante-744x551-portee-zone-et-entete-aligne.png`.

**Et le fait qui rend ce choix facile, que personne n'avait mis sur la table.** A **768 px**, sans
aucune regle ajoutee, le produit sert **deja** un en-tete de 720 px aligne sur une zone de jeu de
720 px - je l'ai mesure. Inclure l'en-tete ne cree donc aucun etat neuf : cela **avance d'un palier
l'alignement que le produit possede deja**, ce qui est exactement l'argument par lequel
`ines-design` justifie 680 px plutot qu'une valeur inventee. **L'exclure, en revanche, creerait un
etat que le produit n'a nulle part** : un en-tete plus etroit que son contenu.

**Sur la preference de portee de `camille-po`**, qui voulait m'epargner une mesure sur un ecran que
`SYN-11` ne concerne pas : la mesure est **faite**, elle a coute cinq largeurs de viewport et une
sonde, et elle rend une reponse nette. Sa preference etait la bonne par defaut ; elle n'a plus
d'objet une fois la mesure produite.

#### 5. Ce que j'ai trouve que personne n'avait nomme - la regle ne fait pas que donner 180 px a 744

**Un `max-width` releve cesse de mordre en dessous de sa nouvelle valeur.** J'ai mesure la
declaration sur toute la bande qu'elle couvre, pas seulement au point de reference, et le resultat
n'est pas celui que sa description laisse attendre :

| Viewport | Bord gauche de la zone de jeu, temoin | Bord gauche, variante | Bord gauche du premier bouton, temoin -> variante |
|---|---|---|---|
| 576 px | 10,5 px | **0,0 px** | 22,5 -> **12,0 px** |
| 600 px | 22,5 px | **0,0 px** | 34,5 -> **12,0 px** |
| 640 px | 42,5 px | **0,0 px** | 54,5 -> **12,0 px** |
| 700 px | 72,5 px | **0,0 px** | 84,5 -> **12,0 px** |
| 744 px | 94,5 px | 4,5 px | 106,5 -> **16,5 px** |

En dessous de 720 px de viewport, le plafond de 720 px ne mord plus : la zone perd le centrage que
`.container` lui donnait et s'etale **bord a bord**, ne laissant que le rembourrage de 12 px de
`.container`. **Ce n'est pas l'effet decrit, qui etait "180 px de plus a 744 px" ; c'est le
retrait du plafond sur toute la bande 576-767.** Releve :
`squad/conformite/releves-s18/s18-variante-576-marges-laterales-effondrees.png` - a 576 px, l'icone
de l'application est collee dans l'angle.

**Ce que j'en fais, et ce que je n'en fais pas.** Je **ne bloque pas** la variante pour autant : a la
reference que `DEC-0040` fixe (744 px), le premier bouton garde **16,5 px** de bord, ce que
j'accepte, et aucun de mes constats de contenu ne se degrade. Mais c'est un changement d'aspect sur
**tous** les ecrans de la bande, y compris la liste des jeux que `SYN-11` ne concerne pas, et il
touche le bord ou se posent les pouces d'un enfant qui tient une tablette. **Il vient de la forme du
mecanisme - relever un plafond - et pas de la cible de 680 px** : une forme qui conserverait le
centrage rendrait 680 px sans effondrer les marges. Je ne concois pas cette forme, ce n'est pas mon
role ; je nomme le resultat que j'exige a la remesure (`SYN-14` ci-dessous).

#### 6. Conditions neuves, datees du 2026-08-23

- **`SYN-14` (bloquante a la remesure, pas a l'implementation), 2026-08-23.** Sur toute la bande que
  la declaration couvre (576-767 px), **aucun element que l'enfant doit presser ne se rapproche du
  bord du viewport plus que le rembourrage de la zone de contenu**, et **l'en-tete reste aligne sur
  la zone de jeu**. Verification : bord gauche du premier bouton et bord droit du dernier, releves a
  576, 640, 744 et 767 px, plus la hauteur de l'en-tete aux memes largeurs. **Ce n'est pas un
  durcissement de `SYN-11`**, qui porte sur la position verticale du bloc de retour et qui est
  satisfaite : c'est la contrepartie laterale du meme mecanisme, que personne n'avait mesuree avant
  aujourd'hui.
- **`SYN-15` (non bloquante), 2026-08-23.** Les onze items qui n'avaient pas le defaut voient leurs
  boutons s'elargir de 500 a 680 px de rangee. **Mesure : aucune regression** - `SYN-5` s'ameliore de
  120 px, aucun item ne passe a deux rangees, la cible de doigt grandit. Je l'ecris parce
  qu'`ines-design` a demande explicitement que je confirme les onze en plus des cinq : **c'est fait,
  sur les seize.** Rien a corriger ; la ligne existe pour que la remesure du livre reverifie ce point
  plutot que de le supposer acquis.

#### 7. Ce que cette section ne fait pas

- **Elle ne ferme ni `SYN-11`, ni `SYN-12`, ni `SYN-13`.** J'ai mesure une **regle injectee** par moi
  dans une page servie, pas un increment livre. C'est ma propre distinction, posee deux fois, et je ne
  vais pas l'abandonner le jour ou elle m'arrangerait : **`SYN-11` se fermera quand `lea-dev` aura
  livre, que la CI sera verte, et que j'aurai remesure les 16 items aux trois etats sur le servi.**
  Les chiffres a retrouver alors sont ecrits ci-dessus : 0 item a deux rangees, pire bas de verdict
  474,19 px.
- **Elle ne juge pas la correction clavier de `marc-dev`** ni l'`ADR` que `theo-archi` ecrit dessus :
  absents de l'arbre mesure, verifie.
- **Elle ne rejuge aucun contenu.** `SYN-1` a `SYN-4`, `SYN-6` a `SYN-9` sont creditees et je n'y
  touche pas ; les seize phrases et les seize indices ne sont pas rouverts.
- **Elle ne leve pas le refus du 2026-08-22 sur `S17-3`.** Un refus ne se leve pas sur une regle que
  personne n'a encore ecrite dans un fichier.

#### 8. Ce dont je reste seule garde

- **Que la regle livree rende ce que l'injectee a rendu.** C'est la limite nommee de ce procede, et
  la seule chose qui la lever est la remesure.
- **La contrepartie laterale (`SYN-14`)**, qu'aucun garde-fou ne voit : `ViewportEtLargeurTelephoneTest`
  compare des declarations dans une feuille, il ne voit pas ou tombe un bord a l'ecran.
- **Audit degrade a declarer** : pile de polices systeme de cette machine. La marge de `SYN-11`
  (+76,81 px) est tres au-dessus de l'incertitude de police que j'ai nommee, donc robuste ; celle de
  `SYN-12` sur les trois items residuels ne l'est pas, et une police plus large en ajouterait
  d'autres.

---

### Remesure du 2026-08-23 (s18, troisieme) sur l'increment livre - `SYN-11` **FERMEE**, bande de `lea-dev` **ACCORDEE**, Done pedagogique de `S17-3` : **VALIDE SOUS RESERVE**

> **Ce que cette section fait.** Elle rend la remesure que j'ai moi-meme posee ce matin comme la seule
> qui puisse fermer quoi que ce soit : le correctif est ecrit dans la feuille servie, plus dans une
> regle que j'injecte. Elle juge aussi l'ecart de bande que `lea-dev` expose au lieu de le taire. Elle
> n'efface aucun verdict anterieur, y compris mes trois refus.
>
> - **La bande retenue par `lea-dev` (720-767,98 px au lieu de 576-767,98) : ACCORDEE**, apres
>   verification et non sur parole.
> - **`SYN-11` : FERMEE.** 0 item sur 16 a deux rangees, pire bas du texte du verdict **474,19 px**,
>   marge **+76,81 px**, sur **328 lectures**.
> - **`SYN-14` : satisfaite** sur ses deux clauses, aux treize largeurs mesurees.
> - **`SYN-13` : fermee.** **`SYN-15` : confirmee** sur le servi.
> - **`SYN-12` : confirmee non fermee**, 3 items sur 16, non bloquante comme depuis le 2026-08-22.
> - **Done pedagogique de `S17-3` : VALIDE SOUS RESERVE**, aucune reserve bloquante.
> - **La ligne de matrice ne passe PAS `couvert-valide` pour autant** : mon propre arbitrage de s17
>   l'en empeche sans les antonymes (`SYN-4`), et ce verdict ne le change pas.

#### 0. L'arbre mesure

Worktree neuf `s18-nadia-remesure`, forke de `round-two` a **`8046256`** (releve par
`git rev-parse`, pas recopie), puis `git merge s18-lea` - **avance rapide** vers **`4702524`**, la
branche du dev etant un descendant direct. L'arbre embarque donc aussi `s18-theo` (`ADR-0056`,
`ADR-0057`) et le lot de `marc-dev` ; j'y ai ensuite fusionne mes deux lots documentaires anterieurs
pour que cette fiche porte son histoire complete. Verifie par `git merge-base --is-ancestor` :
`round-two`, `s18-lea`, `s18-theo`, `s18-marc`, `s18-nadia-bpm1` et `s18-nadia-syn11` sont tous
ancetres. `outils/verifier-lignee.sh` : descend de la lignee de `round-two`.

**`bash outils/mvn-jdk21.sh -B clean verify` sur la racine : BUILD SUCCESS, 621 tests, 0 echec**
(48 + 13 + 321 + 144 + 89 + 6, somme faite sur les six blocs `Results`, pas recopiee). Le Done
technique est acquis.

**La regle est bien dans la feuille servie**, pas dans ma sonde : verifie en interrogeant
`/css/recreo.css` sur l'application en marche, avant toute mesure.

#### 1. La regle servie rend exactement ce que rendait la regle injectee

| Grandeur | Regle injectee (ce matin) | Regle **servie** (ce soir) |
|---|---|---|
| Plafond de la zone de jeu | 720,00 px | **720,00 px** |
| Plafond de l'en-tete | 720,00 px | **720,00 px** |
| Rangee de propositions | 680,00 px | **680,00 px** |
| Largeur de l'enonce | 696,00 px | **696,00 px** |
| Items a deux rangees | 0 / 16 | **0 / 16** |
| Pire bas du bandeau `.signal` | 499,19 px | **499,19 px** |
| Pire bas du texte du verdict | 474,19 px | **474,19 px** |
| `SYN-5`, pire bas de proposition | 411,19 px | **411,19 px** |
| `SYN-12`, items perdant du texte | 3 / 16 (9, 12, 14) | **3 / 16 (9, 12, 14)**, memes mots |

**Au centieme, sur chaque ligne, et sur un echantillon plus large que la mesure injectee** (328
lectures servies contre 123 injectees). **C'est la troisieme fois que je verifie ce procede et la
troisieme fois que le risque que j'avais nomme ne se realise pas** (`COMP-5`, `S17-A`, celui-ci). Je
continuerai a le nommer et a remesurer : trois concordances ne sont pas une preuve, et le jour ou un
lot introduira une specificite concurrente, c'est cette remesure-la qui le verra.

#### 2. `SYN-11` - FERMEE

**Campagne sur le servi** : 8 parties, **328 lectures**, **16/16 items**, chacun dans les quatre
etats (repos, trois echecs), **`scrollY = 0` verifie avant chaque lecture, 0 sur 328**, **0 abandon**.

**Verification de fermeture, dans les termes exacts ou je l'avais posee le 2026-08-22** - *"sur chaque
item, au pire tirage, aux trois etats d'echec, le bas du texte du verdict est au-dessus de 551 px a
`scrollY = 0`"* : **474,19 px sur les seize, aux trois etats**, bandeau `.signal` a 499,19 px.
**`SYN-11` est FERMEE.** Je ne l'efface pas de la table des conditions : je l'y marque fermee avec sa
date, et mes trois refus restent lisibles la ou ils ont ete ecrits.

**`SYN-5` s'ameliore de 120 px** (531,19 -> 411,19). **`SYN-10` reste satisfaite** : aucune variation
selon le tirage sur les 328 lectures. Releve :
`squad/conformite/releves-s18/s18-servi-744x551-syn11-une-rangee-verdict-et-indice-visibles.png` -
trois propositions sur une rangee, verdict **et** indice entiers a l'ecran.

#### 3. `SYN-13` fermee, `SYN-12` confirmee non fermee, `SYN-15` confirmee

- **`SYN-13`** : **0 item sur 16 a deux rangees.** L'elimination visuelle qu'elle observait n'est plus
  observable. **Fermee par la cause**, comme je l'avais annonce le 2026-08-22.
- **`SYN-12`** : **3 items sur 16** (9, 12, 14) perdent encore la fin de leur indice - *"quelqu'un."*,
  *"avec bonté."*, *"réussie."* - contre **15 sur 16** avant le correctif. **Non fermee, non
  bloquante**, pour le motif que j'avais ecrit et que je ne change pas : le verdict est visible, la
  definition tronquee reste comprehensible, et aucune de ces trois troncatures ne peut se lire a
  l'envers - contrairement a celles des homophones, ou j'ai bloque.
- **`SYN-15`** : **confirmee sur le servi.** Les onze items sans defaut ne regressent pas, la rangee
  passe de 500 a 680 px, la cible de doigt grandit, aucun item ne passe a deux rangees. C'est la
  confirmation qu'`ines-design` avait demandee explicitement, faite sur le livre.

#### 4. La bande de `lea-dev` - ACCORDEE, apres verification

**Elle a raison de me la soumettre, et je le dis avant de trancher.** Ce n'est pas une simple
correction numerique : **la bande faisait partie de la forme que j'ai accordee** ce matin, dans les
termes d'`ines-design` (576-767,98 px). La changer change cette forme. L'exposer et demander
confirmation est exactement ce qu'il fallait faire.

**Son motif est verifie, pas cru** - mesure aux largeurs ou j'avais trouve le defaut :

| Viewport | Zone de jeu | En-tete | Premier bouton | Aligne |
|---|---|---|---|---|
| 576 px | 540 px, gauche 10,5 | 540 px, gauche 10,5 | 22,5 px | oui |
| 640 px | 540 px, gauche 42,5 | 540 px, gauche 42,5 | 54,5 px | oui |
| 700 px | 540 px, gauche 72,5 | 540 px, gauche 72,5 | 84,5 px | oui |
| 719 px | 540 px, gauche 82,0 | 540 px, gauche 82,0 | 94,0 px | oui |
| 720 px | 705 px, gauche 0 | 705 px, gauche 0 | 12,0 px (liste) / **20,0 px** (proposition) | oui |
| 744 px | 720 px, gauche 4,5 | 720 px, gauche 4,5 | 16,5 px (liste) / **32,0 px** (proposition) | oui |
| 767 px | 720 px, gauche 16,0 | 720 px, gauche 16,0 | 28,0 px | oui |
| 768 px | 720 px, gauche 16,5 | 720 px, gauche 16,5 | 28,5 px | oui |

**Sous 720 px, tout est identique a l'avant-correctif.** L'effondrement des marges que j'avais mesure
sur 576-719 n'existe plus. **Treize largeurs mesurees, hauteur d'en-tete 53,19 px partout, sans
exception** : aucun repli neuf, ni sur l'accueil ni sur un ecran de manche. **J'accorde cette bande**,
et le motif est qu'elle sert mieux `SYN-14` que la forme que j'avais acceptee.

#### 5. `SYN-14` - satisfaite, et je dis ou ma propre redaction etait plus lache que mon intention

**Clause "en-tete aligne sur la zone de jeu"** : bords gauche et droit **identiques au centieme aux
treize largeurs**. Satisfaite sans discussion.

**Clause "aucun element a presser ne se rapproche du bord plus que le rembourrage de la zone"** : le
rembourrage vaut **12 px**. Sur un ecran de manche, la premiere proposition est a **20 px** du bord a
720-735 px (12 de rembourrage + 8 de bordure de cabinet) et a **32 px** a la reference. Sur la liste
des jeux, le premier bouton est a **12 px** a 720-735 px, c'est-a-dire **exactement** le rembourrage.
**Satisfaite - a la borne.**

**Et je dis le reste, parce qu'un tiers le verrait.** En ecrivant `SYN-14` ce matin, je designais
12 px comme le probleme ; ma redaction en a fait le plancher. **Le correctif atteint le plancher que
j'ai ecrit, pas l'intention que j'avais.** Je ne relis pas ma condition plus severement apres coup -
c'est la troisieme fois aujourd'hui que cette discipline me coute quelque chose (les 2,19 px de la
boite d'indice, les 12 px ici) et c'est precisement ce qui fait qu'une barre de moi veut dire quelque
chose. **`SYN-14` est satisfaite.** Si je veux un plancher chiffre, il s'ecrira avant, pas apres.

#### 6. Constat neuf et date du 2026-08-23 - le correctif ne descend pas sous 720 px

**Ce que personne n'avait mesure, et que je nomme pour que la squad ne croie pas couvert ce qui ne
l'est pas.** J'ai joue les seize items a **640, 700 et 719 px** sur l'increment livre :

| Viewport | Items a deux rangees | Pire bas du texte du verdict |
|---|---|---|
| 640 px | **5** (1, 3, 4, 13, 14) | **594,19 px - sous le pli** |
| 700 px | **5** (1, 3, 4, 13, 14) | **594,19 px - sous le pli** |
| 719 px | **5** (1, 3, 4, 13, 14) | **594,19 px - sous le pli** |
| 720 px et au-dela | 0 | 474,19 px |

**Le defaut pour lequel j'ai refuse `S17-3` trois fois existe encore, inchange, sur toute la bande
576-719 px.** Il n'est pas corrige, il est hors de la bande du correctif.

**Ce que j'en fais.** `SYN-11`, telle que je l'ai ecrite le 2026-08-22, porte sur *"la reference
744 x 551"*. **Elle est satisfaite a cette reference, donc elle est fermee**, et je ne l'elargis pas
apres coup a toutes les largeurs du palier : ce serait deplacer mes poteaux, et punir `lea-dev`
d'avoir suivi `SYN-14`, que j'ai ecrite. **Ce n'est donc pas un motif de refus**, c'est une
**observation datee du 2026-08-23**, et elle devient `SYN-16` ci-dessous.

**Le choix que `lea-dev` a fait sans le nommer ainsi, et qu'il faut voir** : entre 576 et 719 px, les
deux objectifs se contredisent - une forme qui corrige `SYN-11` y effondre les marges, une forme qui
preserve les marges y laisse le verdict sous le pli. Elle a preserve les marges. **Je ne le lui
reproche pas** : c'est ce que `SYN-14` demandait, et la reference du produit est a 744 px. Mais le
constat est que **le produit n'est corrige qu'au-dessus de 720 px**, et savoir si une forme atteint
les deux a la fois est une question de mecanisme pour `theo-archi`, pas une question que je tranche
et surtout pas un chiffre que j'invente ici.

#### 7. Done pedagogique de `S17-3` - **VALIDE SOUS RESERVE**

**Etat de mes conditions sur l'increment livre `4702524`** : `SYN-1`, `SYN-2`, `SYN-3` tenues ;
`SYN-4` tenue (la story des antonymes existe) ; **`SYN-5` tenue** (411,19 px) ; `SYN-6`, `SYN-7`,
`SYN-8` tenues ; `SYN-9` fermee ; `SYN-10` satisfaite ; **`SYN-11` FERMEE** ; `SYN-12` ouverte non
bloquante ; **`SYN-13` fermee** ; **`SYN-14` satisfaite** ; **`SYN-15` confirmee**. **Aucune condition
bloquante ne reste ouverte.**

**Audit joue** : oui - 8 parties, 328 lectures, les seize items dans les quatre etats, au clavier
seul, sur l'application servie depuis le fat jar de cet arbre. Ce n'est pas un audit degrade, a la
reserve de police pres.

**Verdict : VALIDE SOUS RESERVE.** Reserves, aucune bloquante :

1. **`SYN-12`** - trois items sur seize perdent la fin de leur indice. Reste ouverte, datee du
   2026-08-22.
2. **`SYN-16` (neuve, datee du 2026-08-23, non bloquante)** - **sous 720 px de viewport, le defaut de
   `SYN-11` existe encore, inchange** : 5 items sur 16, verdict a 594,19 px, mesure a 640, 700 et
   719 px. Ce n'est pas une reouverture de `SYN-11`, qui porte sur la reference et qui est fermee.
   `camille-po` decide s'il existe un appareil cible dans cette bande ; si oui, cela demande un
   mecanisme, et il n'est ni concu ni chiffre ici.
3. **`SYN-4`** - la moitie de la competence (les antonymes) manque. **C'est elle, et elle seule, qui
   empeche la ligne de matrice de passer `couvert-valide`**, exactement comme mon arbitrage de s17 le
   disait. **Ce Done ouvre la porte pedagogique de `S17-3` ; il ne fait pas bouger la ligne.**

**Ce que ce Done ne couvre pas** : ce qu'un lecteur d'ecran annonce (jamais couvert par aucun de mes
Done), ce qu'un enfant reel comprend, et tout rendu sur une largeur, une hauteur ou une pile de
polices que je n'ai pas mesurees. **Il ne juge pas la correction clavier de `marc-dev`**, presente sur
cet arbre : son Done reste une autre question, que je n'instruis pas ici.

**Audit degrade a declarer** : pile de polices systeme de cette machine. La marge de `SYN-11`
(+76,81 px) est plus du double de l'incertitude de police que j'ai nommee (15 a 35 px), donc ce
verdict-la est robuste. Celle de `SYN-12` sur les trois items residuels ne l'est pas.

#### 8. Ce dont je reste seule garde

- **La position de tout ce que l'enfant lit sous le meuble, a toute largeur.** La CI est verte a 621
  tests sur l'arbre exact ou `SYN-16` existe : aucun garde-fou ne voit une position rendue.
  `LaLargeurOffreeAUneRangeeEstUneFonctionEpingleeTest` epingle desormais **deux** lignes de plafond
  (`lea-dev` a corrige le chiffre que `theo-archi` et `sofia-sm` lui donnaient : deux selecteurs, deux
  chaines, donc deux lignes) - il garde la **declaration**, jamais la consequence a l'ecran.
- **Que la regle livree continue de rendre ce que rend celle d'aujourd'hui.** Trois concordances ne
  sont pas une preuve.
- **`SYN-16`**, que rien n'observe : le produit n'est corrige qu'au-dessus de 720 px, et seule une
  mesure le dit.

---

## Mesure du 2026-08-24 (s19) - la largeur de rangee **necessaire** est de **539 px**, et elle change le verdict sur `SYN-16`

> **Ce bloc est une mesure, pas un verdict.** Aucun Done n'est prononce ici : rien n'a ete livre sur
> `SYN-16`. `S17-3` reste `VALIDE SOUS RESERVE` du 2026-08-23, `SYN-16` reste ouverte et non
> bloquante, et la ligne de matrice ne bouge pas - elle est bloquee par `SYN-4`, les antonymes, et
> par rien d'autre.

**Pourquoi cette mesure existe.** `theo-archi` (avis de faisabilite de s19, section 6) ferme `S19-6`
comme *"infaisable en l'etat sur la bande entiere"* par une arithmetique tiree de mes propres
chiffres de s18, et il nomme lui-meme le chiffre qui manque : **680 px est une largeur *suffisante*,
la largeur *necessaire* n'a jamais ete mesuree.** C'est le seul nombre qui separe deux verdicts
opposes. Il me le route parce qu'il demande un navigateur, et qu'aucun test de ce depot ne lit une
position rendue.

### 1. Methode, et ce qu'elle reemploie

**Arbre mesure** : `s19-nadia` a `6aecee3`, c'est-a-dire `round-two` a la cloture de s18 plus mes
deux arbitrages prealables, qui ne touchent aucun fichier de code. **Le contenu servi est donc bien
celui de l'increment livre** : `cm1-synonymes` n'a pas change depuis le verdict du 2026-08-23.
Application servie depuis le fat jar reconstruit de cet arbre, **arretee par son PID**.

**Procede**, identique a celui de mes campagnes de s18 : navigateur reel pilote par son protocole de
deverminage, `deviceScaleFactor` a 1, mode non mobile, declaration de largeur **injectee** en dernier
dans le `head`, la ou `recreo.css` est chargee (`ADR-0014` point 5) - la feuille n'est jamais
modifiee. La limite que j'ecris a chaque fois vaut encore : une regle ecrite dans `recreo.css`
pourrait se faire ecraser par une specificite que mon injection contourne. Elle est ici du meme ordre
qu'a s18, et pour la meme raison verifiee alors : `.zone-jeu` et `.container` sont des selecteurs de
classe unique, et `recreo.css` est chargee apres la bibliotheque tierce.

**Ce que ce procede ajoute a ceux de s18, et c'est ce qui rend la mesure abordable** : la largeur est
balayee **sur la manche affichee**, sans recharger ni perdre l'item. Chaque item visite rend donc son
propre seuil par recherche dichotomique, au pixel, en une seule visite. Les deux scripts sont commits
dans le depot pour que la campagne se rejoue sans etre reconstruite :
`squad/outils/nadia-prof/balayage-largeur-rangee.py` et
`squad/outils/nadia-prof/confirmer-largeur-dans-la-bande.py`. Releves complets dans
`squad/conformite/releves-s19/`.

**Ce que je mesure** : le nombre de rangees occupees par les propositions, par le nombre de valeurs
distinctes du bord superieur des boutons. Un item est "a deux rangees" quand ce nombre vaut 2.

### 2. Le balayage - largeur necessaire item par item, a la reference 744 x 551

**16 items sur 16 vus, 4 parties.** Seuil = plus petite largeur de rangee, au pixel, pour laquelle
l'item tient sur une seule rangee.

| Seuil | Item | Propositions |
|---|---|---|
| **539 px** | Ce jeu de societe est "facile" pour les debutants. | simple / dangereux / couteux |
| **538 px** | Lea est "contente" de son cadeau. | heureuse / inquiete / surprise |
| **527 px** | Cet exercice de mathematiques est "difficile". | complique / amusant / court |
| **517 px** | Le petit garcon est "triste" depuis le depart de son ami. | malheureux / inquiet / gene |
| **515 px** | Le village est tres "calme" le dimanche matin. | tranquille / bruyant / anime |
| 499 px | Le nouveau voisin est "gentil" avec tout le monde. | aimable / celebre / elegant |
| 490 px | Le pompier reste "courageux" face au danger. | brave / maladroit / discret |
| 485 px | Cette fourmi est "petite". | minuscule / rapide / noire |
| 479 px | Ce moteur de camion est tres "fort". | puissant / leger / bruyant |
| 478 px | Apres la course, Tom est "fatigue". | epuise / content / inquiet |
| 471 px | Le bureau de Lea est "propre". | net / encombre / sombre |
| 449 px | Ce desert de sable est tres "grand". | immense / chaud / vide |
| 433 px | Dans les fables, le renard est "malin". | ruse / gentil / peureux |
| 432 px | La musique du carnaval est "gaie". | joyeuse / lente / grave |
| 416 px | La maman parle a son bebe d'une voix "douce". | tendre / forte / grave |
| 400 px | Ce paysage de montagne est "beau". | joli / vaste / aride |

**La largeur necessaire au paquet entier est le maximum de cette colonne : 539 px.**

**Mon chiffre de s18 se reexplique exactement, et je le verifie avant de m'appuyer dessus.** J'avais
compte **cinq** items a deux rangees a 500 px de rangee. Ce tableau donne exactement cinq seuils
au-dessus de 500 px, et ce sont **les memes cinq items**. Le sixieme est a 499 px - un pixel sous la
rangee servie, ce qui explique pourquoi il ne s'est jamais replie. Le comptage de s18 n'etait pas une
estimation : c'etait le bon nombre, et voici pourquoi.

### 3. Confirmation dans la bande, a 640 x 551, en jouant les seize items jusqu'a l'echec

Le balayage mesure a la reference. Le defaut vit dans la bande. Trois campagnes de confirmation, avec
la declaration injectee **sous la meme borne de media que celle qui est servie**
(`min-width:576px` et `max-width:767.98px`), chaque item joue jusqu'a un echec pour lire la position
reelle du bloc de verdict.

| Plafond de `.zone-jeu` | Rangee offerte | Items vus | Items a deux rangees | Pire bas du texte du verdict | Sous le pli |
|---|---|---|---|---|---|
| **540 px** (temoin, servi aujourd'hui) | 500 px | 16/16 | **5** | **594,19 px** | 5 |
| 578 px | 538 px | 16/16 | **1** (le seul a 539) | 594,19 px | 1 |
| **579 px** | **539 px** | 16/16 | **0** | **504,19 px** | **0** |

**`scrollY = 0` verifie a chaque lecture, 0 lecture a defilement non nul sur les trois campagnes.**

**Le temoin reproduit mon chiffre de s18 au centieme** - 594,19 px, cinq items, les memes cinq - sur
un autre jour, un autre arbre, une autre campagne, et par un outil different de celui de s18. C'est
la verification que je devais a ma propre methode avant de m'appuyer sur elle.

**La borne est nette et elle est au pixel** : a 538 px de rangee il reste exactement l'item de 539 px ;
a 539 px il n'en reste aucun, et le verdict remonte de 90 px, a **504,19 px pour un pli a 551 px**,
soit une marge de **+46,81 px**.

Captures : `squad/conformite/releves-s19/s19-syn16-640x551-item-facile-temoin-rangee-500.png` et
`...-correctif-rangee-539.png`, sur l'item le plus large, apres un echec.

### 4. Ce que cela fait au verdict de `theo-archi`, et je commence par ce qu'il a juste

**Son arithmetique est exacte et je la reprends telle quelle** : a un viewport `V`, la rangee ne peut
pas depasser `V - 40`. Elle reproduit mes deux mesures de s18, et mes trois campagnes d'aujourd'hui
la reproduisent une quatrieme fois (plafond 579 injecte, rangee mesuree 539,00).

**Ce qui change est la seule inconnue qu'il avait nommee.** Avec 539 px et non 680 px :

| Viewport | Rangee atteignable (`V - 40`) | 539 px atteints ? |
|---|---|---|
| 576 px | 536 px | **non**, il manque 3 px |
| 577 px | 537 px | **non**, il manque 2 px |
| 578 px | 538 px | **non**, il manque 1 px |
| **579 px** | **539 px** | **oui, exactement** |
| 640 px | 600 px | oui, 61 px de marge |
| 719 px | 679 px | oui, 140 px de marge |

**Un correctif de largeur n'est pas infaisable sur la bande : il est faisable sur 579-719 px et
echoue sur exactement trois pixels de viewport - 576, 577, 578.** Sur les 144 largeurs entieres de la
bande, il en couvre 141.

**Je ne le tourne pas contre lui.** Il a nomme lui-meme le chiffre qui lui manquait, il a dit ou son
verdict basculerait, et il a refuse de l'inventer. **Le chiffre manquait parce que ma mesure de s18
l'avait laisse manquer** : j'ai mesure une largeur suffisante et je n'ai pas mesure la necessaire.
C'est mon trou, pas le sien, et c'est pourquoi c'est moi qui le comble.

### 5. Les bords lateraux avec un plafond de 579 px - `SYN-14` tenue, a la borne que j'ai deja acceptee

Mesure a huit viewports, plafond de 579 px injecte sur `.entete-contenu` et `.zone-jeu`
(`squad/conformite/releves-s19/s19-syn16-bords-lateraux-plafond-579.json`) :

| Viewport | Largeur de zone | Bord gauche de la zone | En-tete aligne | Bord gauche de la premiere proposition |
|---|---|---|---|---|
| 576 px | 576 px | 0 | oui | 20,0 px |
| 578 px | 578 px | 0 | oui | 20,0 px |
| 579 px | 579 px | 0 | oui | 20,0 px |
| 580 px | 579 px | 0,5 | oui | 20,5 px |
| 600 px | 579 px | 10,5 | oui | 30,5 px |
| 640 px | 579 px | 30,5 | oui | 50,5 px |
| 700 px | 579 px | 60,5 | oui | 80,5 px |
| 719 px | 579 px | 70,0 | oui | 90,0 px |

**`SYN-14`, clause "en-tete aligne sur la zone de jeu" : tenue aux huit largeurs**, bord gauche
identique au centieme.

**`SYN-14`, clause "aucun element a presser ne se rapproche du bord plus que le rembourrage de la
zone" : tenue, a la borne.** Le pire cas est au pied de la bande, ou la zone occupe tout le viewport :
la premiere proposition y est a **20,0 px** du bord (12 de rembourrage + 8 de bordure de cabinet), et
le premier bouton du catalogue a **12,0 px**, c'est-a-dire exactement le rembourrage. **C'est le meme
chiffre, au pixel, que celui que j'ai mesure et accepte le 2026-08-23 a 720-735 px** en ecrivant
*"satisfaite - a la borne"*. Je ne relis pas ma condition plus severement parce que le correctif
descend plus bas : elle dit ce qu'elle dit, et 12 px reste 12 px.

**Un point qui n'est pas de moi et que je route plutot que de le trancher** : un plafond de 579 px
donne des marges **plus larges** au sommet de la bande qu'un plafond de 680 px (70 px contre 19,5 px
a 719 px de viewport). Le plafond minimal est donc aussi le plus genereux pour `SYN-14` - mais
choisir sa valeur exacte, entre le strict necessaire et une reserve de securite, est une question de
forme. `ines-design` propose, je trancherai.

### 6. Ce dont je reste seule garde, et la reserve que je ne cache pas

- **Le seuil de 539 px est mesure sur la pile de polices systeme de cette machine**, comme toutes mes
  mesures depuis s10. Une police plus large deplace ce seuil vers le haut, et **c'est ici que cela
  compte le plus** : il ne faut que **0,6 % de largeur de texte en plus** pour que 539 devienne 543 et
  que le pied de la bande recule de 579 a 583 px. Au sommet de la bande la marge est de 140 px et rien
  ne la menace ; au pied, elle est nulle par construction. **Un plafond fixe au strict necessaire ne
  laisse aucune reserve de police, et je ne validerai pas un correctif qui s'y colle sans en avoir
  parle.** Ce n'est pas une condition d'audit, c'est ce que je dirai le jour ou le lot arrivera.
- **Rien dans ce depot ne verifie aucun de ces chiffres.** `LaLargeurOffreeAUneRangeeEstUneFonction
  EpingleeTest` garde la **declaration**, jamais la consequence a l'ecran. Le nombre de rangees
  qu'occupent trois mots dans un cabinet n'est vu par aucun test, et il ne le sera pas : c'est le
  meme constat qu'a s18, et il vaut pour cette mesure comme pour la precedente.
- **Ce que ce bloc ne dit pas** : il ne dit pas si la famille verticale que `theo-archi` chiffre a
  43,19 px est meilleure ou moins bonne. Elle reste ouverte et entiere, et je ne l'instruis pas ici -
  `ines-design` propose les termes verticaux, je trancherai. **Une remarque tout de meme, pour qu'elle
  ne se perde pas** : les deux familles ne s'excluent pas et ne couvrent pas la meme chose. La largeur
  ferme 141 pixels de la bande sur 144 et laisse les trois derniers ; une reduction verticale de
  43,19 px les fermerait sans toucher a la largeur. Qui doit etre choisie, ou les deux, n'est pas ma
  decision - c'est `camille-po` qui priorise et `theo-archi` qui chiffre.

### 7. Suites hors de ma main

- **`theo-archi`** : le chiffre qu'il a nomme comme manquant existe maintenant - **539 px**. Son
  arithmetique tient, sa conclusion *"infaisable sur la bande entiere"* devient *"faisable sur
  579-719 px, infaisable sur 576-578 px"*. Il lui revient de dire s'il amende son avis.
- **`camille-po`** : `S19-6` n'est pas ferme par l'arithmetique. Il reste un arbitrage de perimetre -
  couvrir 141 pixels de la bande sur 144 est-il ce qu'elle veut, ou faut-il les 144. **Ce n'est pas ma
  decision** : c'est elle qui a etabli, a la cloture de s18, qu'un appareil reel vit dans cette bande.
- **`ines-design`** : deux objets de forme, l'un et l'autre a proposer, pas a trancher - la valeur du
  plafond (le strict necessaire de 579 px, ou une reserve de police au-dessus) et les termes verticaux
  de la seconde famille.
- **`sofia-sm`** : rien de cette mesure n'ouvre ni ne ferme un Done, et `SYN-16` reste ouverte.
