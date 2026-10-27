# s57 - dernier arbitrage avant les premiers jets de `-04`, `-05`, `-06`, `-08`, `-10`, `-12`

- **Auteure** : `nadia-prof`, professeure des ecoles, seule autorite sur le Done pedagogique.
- **Date** : 2026-10-27, debut 09:28:04 (UTC+01:00), lue par `date` ; fin en section 8.
- **Arbre** : branche `s57-p5-nadia-dernier`, base `round-two` a `05e66408`, lue par `git rev-parse`.
- **Repond a** : `docs/backlog/backlog.md`, annotation du 2026-10-27, neuvieme (`Q9-1` a `Q9-5`) ;
  `avis-theo-palier-5-ajustements-gardes-ce1-qlm-06-08.md`, section 5 (`Q8` a `Q12`) ;
  `avis-theo-palier-5-ajustements-gardes-ce1-qlm-04-05.md`, section 4 (`P1` a `P3`) ;
  `avis-theo-palier-5-ajustements-gardes-ce1-qlm-10-12.md`, section 10 (`P1` a `P3`, `F1`).
- **Type** : arbitrage avant premier jet, **dernier lot**. Aucun Done, aucun verdict de lot, aucun identifiant de condition
  neuf, aucune condition neuve ni reecrite. Les points de `theo-archi` portent le prefixe du lot pour lever l'homonymie de ses
  trois fichiers : `P1-0405`, `P1-1012`... Les reperes `G1`... (section 7) sont locaux a ce fichier. Je ne modifie ni story, ni
  paquet, ni test : `camille-po` reporte la section 6, `theo-archi` la section 7.
- **Lu** : les trois avis d'ajustement de `theo-archi` en entier ; mes trois fichiers de contradictions du 2026-10-27 en
  entier ; mon `arbitrage-nadia-premiers-jets-ce1-qlm-04-05.md` (sections 2.2 a 5.2) et
  `arbitrage-nadia-premiers-jets-ce1-qlm-06-08.md` (sections 1.1 a 1.7) ; l'annotation neuvieme ; `AIM-CE1-1`, `AIM-CE1-2`
  (`squad/conformite/ARBITRAGE-PREALABLE-s57-instruction-questionner-le-monde-et-emc-ce1.md`, 3.6) ; les quatre items `ordre` de
  `ce1-qlm-etapes-de-developpement.yaml` ; `rotationsDe` dans `PaquetCe1QlmEtapesDeDeveloppementTest.java` ; `squad/lessons.md`.
- **Audit degrade, dit** : aucun des six paquets n'existe. Je juge des regles. Les comptes de mots sont faits a la main,
  marques **[calcul a la main, a recompter par `PaquetLu.mots`]**.

## 0. Table des reponses

| Point | Reponse | Regle a reporter, ou ce qui en suit |
|---|---|---|
| `Q9-1` (`-08`) | **oui** | chacun des trois gestes est la bonne reponse d'au moins un item de B ; B passe a 4 au moins (6.4) |
| `Q9-2` (`-08`) | **reformulation** | admis : un refus de signaler sans adulte nomme (*"ne rien dire à personne"*, *"ne pas en parler"*) ; refuse : *"ne pas faire attention"* ; mon critere de 2.4 est corrige (6.4) |
| `Q9-3` (`-05`) | **oui** | *impossible* veut dire *sans objet* : un changement d'une matiere que l'enonce ne decrit pas ; le changement que la temperature empeche est le distracteur attendu (6.2) |
| `Q9-4` (`-06`) | **oui, voulu, avec un ajout** | tout item de B compte (deja ecrit le 2026-10-26) ; au moins un item de A compte aussi (6.3) |
| `Q9-5` (`-06`) | **non tolere** | le bon groupe n'est le seul plus court que dans la moitie des items de B au plus ; garde demandee (6.3) |
| `Q8` (`-08`) | **ma regle l'emporte, ma table est corrigee** | *a aucun temps* ; temps simples et *on ne* exempte, tels qu'encodes |
| `Q9` (`-08`) | **exempter *personne ne*** | *"pour que personne ne se blesse"* passe (6.4) |
| `Q10` (`-08`) | **oui, garde** | un enonce `geste` qui porte les reperes de deux situations est refuse |
| `Q11` (`-08`) | **oui, garde** | les cinq abstentions nommees, ancrees au debut, refusees en distracteur |
| `Q12` (`-08`) | **oui, garde** | un distracteur egal a *ne pas* ou *ne jamais* suivi d'une forme de la table est refuse |
| `P1-0405` (`-05`) | **confirme** | la garde de la regle 4 lit les deux taches ; la regle 3 de A ne propose pas *rien ne change* |
| `P2-0405` (`-05`) | **oui pour *au-dessous de* ; refus de *bouteille d'eau gelée* confirme** | alternative ajoutee a `SOUS_ZERO` ; `EAU` inchange |
| `P3-0405` (`-04`) | **confirme** | les formes de genre et de nombre et *feculents* restent ; ma phrase *"liste fermee"* est corrigee (6.1) |
| `P1-1012` (`-10`) | **oui** | aucun adjectif sur un element ou un paysage, indice compris (6.5) |
| `P2-1012` (`-10`) | **oui, et plus large** | en A, l'indice ne nomme aucun element propre d'un paysage propose (6.5) |
| `P3-1012` (`-10`) | **garde-la** | *littoral* refuse dans l'indice d'un item B dont le lieu n'est pas le littoral |
| `F1` (`-01`) | **rien a corriger** | devinable en theorie par la meme heuristique, mais inevitable sur un cycle : les trois seuls ordres admis a `-01` designent la bonne reponse par paire (section 5) |

**Ce qui reste au developpeur, a signaler dans son rapport de premier jet** : la repartition des distracteurs negatifs de
`-08` entre les items (6.4) ; la place de *fil de fer* dans les groupes de B de `-06` au-dela de la regle de longueur (6.3).

---

## 1. `-08` (`ce1-qlm-dangers-electricite`)

### 1.1 `Q9-1` : un item par geste restant. **Oui.**

`DEL-CE1-1` enumere par *"ou"*, et je l'ai lue ainsi le 2026-10-27 (contradictions `-06`/`-08`, 2.1) : elle n'exige pas chaque
geste. Ce n'est pas `DEL-CE1-1` qui m'arrete ici, c'est ce que l'enfant apprendrait. Si toutes les bonnes reponses de B sont des
formes de *ne pas toucher*, l'enfant repond *ne pas toucher* sans lire la situation, et il n'a jamais a savoir que l'eau et
l'electricite ensemble sont un danger, qui est le seul contenu de `-08` que le CP n'a pas. C'est le defaut **b** d'A.273 sous une
forme repetee : une bonne reponse qu'on reconnait a son verbe.

**Les trois gestes, classes par forme** (regle 2, inchangee) :

- *ne pas toucher* : *"ne pas toucher le fil"*, *"laisser le fil sans le toucher"*, *"ne pas toucher la prise"*, *"ne rien mettre
  dans la prise"*, *"ne pas toucher l'appareil"* ;
- *ne pas approcher de l'eau* : *"ne pas l'approcher de l'eau"* ;
- *ne pas brancher avec les mains mouillees* : *"attendre d'avoir les mains sèches"*, *"se sécher d'abord les mains"*, *"ne pas
  brancher la lampe"*.

**Ce que cela coute, compte** [calcul a la main] : B porte au moins un item `fil`, un `prise`, un `eau` *approcher* et un `eau`
*mains mouillees*, donc **4** au lieu de 3. L'item `eau` sans mains mouillees que je demandais (2.3 de mes contradictions) est
tenu par l'item *approcher*. Le plancher effectif du paquet reste 11 (`DEC-0120` A.258).

**Ce n'est pas un poteau deplace** : aucun item n'existe. C'est une regle ajoutee, datee ici.

### 1.2 `Q9-2` : un distracteur negatif sans adulte. **Reformulation, et une correction de mon critere.**

**Correction de mon propre ecrit [corrige le 2026-10-27, `nadia-prof`, ce fichier, section 1.2].** Mon critere de 2.4
(contradictions `-06`/`-08`) etait : *"un enfant qui ferait cela serait-il hors de danger ? Oui : abstention, refusee."* Applique a
*"ne rien dire à un adulte"*, que j'admets dans le meme paragraphe, il repond *oui* : l'enfant qui se tait et ne touche a rien
est hors de danger. Mon critere refusait mon exemple. Ce que je voulais distinguer, c'est **ce qu'un adulte attendrait de
l'enfant**. Le critere devient : *"un adulte attendrait-il cela de l'enfant ?"* Oui (*s'éloigner*, *ne rien toucher*) :
abstention, seconde reponse, refusee. Non : distracteur admissible, s'il est plausible.

**Ce qui est admis** : un distracteur qui refuse de signaler le danger, sans nommer d'adulte. *"ne rien dire à personne"* (5 mots),
*"ne pas en parler"* (4). Un enfant qui a peur d'etre gronde les choisit vraiment, et un adulte ne les attend pas. Ils rejoignent
*"ne rien dire à un adulte"* et *"ne pas déranger un adulte"*.

**Ce qui est refuse : *"ne pas faire attention"*.** Il nomme la faute au lieu d'une conduite. Aucun enfant de CE1 ne choisit
*ne pas faire attention* a la question *"que doit-il faire ?"* : on lui dit *fais attention* depuis la maternelle. Un distracteur
que personne ne choisit fait de l'item un choix entre deux. Meme refus pour toute proposition qui nomme une imprudence par un
mot de jugement (*etre imprudent*, *faire n'importe quoi*).

**Ce que je laisse au developpeur, a signaler dans son rapport** : la regle 3 demande un *ne ...* parmi les distracteurs chaque
fois que la bonne reponse commence par *ne*, soit dans 6 formes sur 9. Les distracteurs negatifs admis sont quatre formes de la
meme idee. Comment il les repartit entre les items, et combien de fois chacun revient, je le lis au jeu ; il ecrit le compte par
forme dans son rapport.

### 1.3 `Q8` : *present conjugue* contre *a aucun temps*. **Ma regle l'emporte.**

**[corrige le 2026-10-27, `nadia-prof`, ce fichier, section 1.3]** : dans la table de la section 5 de
`arbitrage-nadia-contradictions-ce1-qlm-06-08.md`, ligne `T5`, *"(present conjugue de se blesser, se faire mal, sauf apres
on)"* devient *"(se blesser, se faire mal conjugues a un temps simple avec un sujet autre que on, on ne, personne ne)"*. La
regle 5 disait *a aucun temps* ; la table ne nommait que l'exemple. `theo-archi` a suivi la regle, et c'est juste. *"On ne se
blesse pas si on ne touche pas le fil."* est le present generique nie : il passe.

### 1.4 `Q9` : *"pour que personne ne se blesse"*. **Exempter *personne ne*.**

C'est une consequence generique, sans personnage, du meme ordre que *"pour ne pas se blesser"*. La lettre *"sauf apres on"* la
refusait parce que je n'avais pense qu'a *on* ; ma regle visait *un personnage nomme*. *"Un élève se blesse"* reste refuse : il
met en scene un accident.

### 1.5 `Q10`, `Q11`, `Q12` : **les trois gardes, oui.**

- **`Q10`** : un enonce porte une seule situation, et deux situations font deux gestes justes. *"Le sèche-cheveux est branché à la
  prise près du lavabo."* porte les reperes de `prise` et de `eau` : refuse, et c'est voulu, parce que *"ne pas toucher la
  prise"* y devient une seconde reponse. Il se reecrit *"branché au bord du lavabo"*.
- **`Q11`** : les cinq abstentions nommees, ancrees au debut, refusees en distracteur. Une abstention ecrite autrement reste a
  ma lecture.
- **`Q12`** : un distracteur egal a *ne pas* ou *ne jamais* suivi d'une forme de la table, quel que soit l'item. Dans l'item de
  sa situation, il donne la reponse par la paire de contraires ; ailleurs, il est hors situation.

## 2. `-05` (`ce1-qlm-eau-trois-etats`)

### 2.1 `Q9-3` : *impossible*. **Oui : *sans objet*.**

**[corrige le 2026-10-27, `nadia-prof`, ce fichier, section 2.1]** : regle 2 de 5.2 de
`arbitrage-nadia-premiers-jets-ce1-qlm-04-05.md`, derniere phrase, *"Aucune proposition n'est un changement que l'etat decrit
rend impossible."* A la lettre, elle refusait tous les distracteurs de B, y compris ceux de mes trois exemples : dans le
congelateur, *le glacon fond* est impossible aussi. Elle voulait dire : aucune proposition n'est le changement d'une matiere
que l'enonce ne decrit pas. *L'eau gele* sans eau liquide dans l'enonce, *la glace fond* sans glace : l'enfant les ecarte sans
rien savoir de la temperature. **Le changement que la temperature de l'enonce empeche est le distracteur attendu** : c'est lui
qui demande la competence.

La regle 3 de A (*"jamais un etat que la temperature de la situation rend impossible (la glace au soleil)"*) est une autre
regle, sur une autre tache, et reste telle qu'elle est.

### 2.2 `P1-0405` : la regle 4 lue sur les deux taches. **Confirme.**

La regle 3 de A ne nomme comme distracteurs que la conception a deplacer et un element de la famille de la buee : *rien ne
change* n'y a pas de place. La garde plus large ne refuse donc rien que mes regles admettent, et son motif vaut pour les deux
taches.

### 2.3 `P2-0405` : *au-dessous de 0 degre* et *bouteille d'eau gelée*.

- ***Au-dessous de*** : **ajoute**. C'est du francais correct, qu'un redacteur ecrit ; un faux rouge sur une forme juste ne gagne
  rien.
- ***Une bouteille d'eau gelée*** : **le refus est confirme**. Le mot *eau* dans un item ou *rien ne change* est juste fait lire a
  l'enfant de l'eau la ou il n'y a que de la glace. *Un glaçon* est plus court et sans ambiguite.

## 3. `-04` (`ce1-qlm-familles-d-aliments`)

### 3.1 `P3-0405` : les formes au-dela des sept mots. **Confirme.**

**[corrige le 2026-10-27, `nadia-prof`, ce fichier, section 3.1]** : la phrase que j'ai fait ajouter a la regle 6 (contradictions
`-04`/`-05`, 6.2), *"La liste des mots est fermee"*, voulait dire fermee **aux relations de sens** (*la pomme* n'est pas
refusee parce qu'elle est un fruit). Elle ne voulait pas dire fermee aux sept mots de la parenthese, qui sont des exemples. La
regle porte sur les mots des cinq noms de famille et leurs formes de genre et de nombre : *feculent*, *laitier*, *laitiere*,
*sucree* en sont, comme *poisson* et *oeuf*. Ce que `T04-3e` encode est la regle.

## 4. `-06` (`ce1-qlm-aimant`)

### 4.1 `Q9-4` : le quart tenu par B seul. **Voulu, avec un ajout.**

Ma regle 1 du 2026-10-26 le dit : *"Tout item de B compte, par construction."* Chaque groupe distracteur porte un objet en
aluminium ou en cuivre, et l'enfant doit le juger non attire pour l'ecarter : cet objet est la cible du jugement, et c'est ce
qu'`AIM-CE1-2` veut (*"le piege est servi"*). `camille-po` a raison sur la lettre : la bonne reponse de B n'est pas cet objet. Ce
n'est pas la bonne reponse qui sert le piege, c'est l'item.

**Ce que je n'avais pas vu** : si le quart est tenu par B seul, A peut etre fait entierement d'items que la croyance fausse
resout (*le clou* parmi *la gomme* et *la bille en verre* ; *la gomme* parmi deux objets en fer). A ne travaillerait alors plus la
ligne. **Au moins un item de A compte pour le quart.** A porte deja 3 items au moins ; le plancher ne change pas.

### 4.2 `Q9-5` : la bonne reponse de B, groupe le plus court. **Non tolere.**

**Le constat se corrige d'abord.** `LON-CE1-5` n'interdit pas *fil de fer* dans un distracteur : *"fil de fer, papier
d'aluminium, clou"* fait 6 mots et passe ; seuls *fil de fer* avec *barquette en aluminium* ou *fil de cuivre* font 7 (mesure de
`theo-archi`, ajustements `-06`/`-08`, 1.3). Le reste du constat est juste [calcul a la main] : sans *fil de fer*, le bon groupe
fait 3 mots, et tout groupe distracteur en fait 4 ou 5, puisque chaque objet en aluminium ou en cuivre a deux mots au moins.

**Pourquoi je ne le tolere pas.** Sur trois listes de trois objets, un groupe de trois mots a cote de deux groupes de cinq se voit
avant d'etre lu. Apres deux manches, l'enfant prend le plus court. C'est le defaut **b**, et `TRV-CE1-2` le refuse pour la plus
longue ; je le refuse pour la plus courte.

**Ce qui le leve** : un bon groupe qui porte *fil de fer*. Exemple [calcul a la main] : bonne reponse *"fil de fer, clou,
agrafe"* (5) ; distracteurs *"fil de fer, épingle, papier d'aluminium"* (6) et *"clou, épingle, fil de cuivre"* (5). Aucun groupe
n'est le seul plus court. Chaque groupe partage un objet avec chacun des deux autres : la regle 3 de B tient. Chaque distracteur
porte deux objets en fer et un en aluminium ou en cuivre : la regle 2 tient.

**Qui le lit** : la garde, sur `PaquetLu.mots` (`G4`, section 7). Ce qu'elle ne lit pas : que *fil de fer* ne devienne pas a son
tour le signe du bon groupe. Je laisse au developpeur la composition, et il signale dans son rapport, pour chaque item de B, dans
combien de groupes *fil de fer* figure.

## 5. `F1` : `-01` et la paire que j'interdis a `-12`

**Oui, les quatre suites de `-01` sont devinables par l'heuristique que je refuse a `-12`, en theorie.** Pour chaque position, la
bonne reponse porte l'element que deux propositions sur trois y portent ; c'est le cas des quatre items (*"œuf, têtard,
grenouille"* ; *"têtard, œuf, grenouille"*, *"grenouille, têtard, œuf"* : *têtard* au milieu deux fois, *grenouille* a la fin
deux fois).

**Je ne corrige rien a `-01`.** A `-01`, la suite est prise dans un cycle de vie, et une rotation y est une seconde reponse
defendable (*"têtard, grenouille, œuf"* est vrai : la grenouille pond). C'est ma propre regle, gardee par `rotationsDe` : *"trois
ordres sur six sont des rotations du bon ordre, dont la bonne reponse ; les trois autres seuls sont des distracteurs"*. Les trois
autres sont les deux echanges de voisins et l'inverse. **Toute paire prise parmi ces trois designe la bonne reponse** (table de
`theo-archi`, ajustements `-10`/`-12`, 1.2 : echange et inverse, 2 ; 1 ; 1 ; deux echanges, 2 ; 1 ; 1). Corriger `-01` dans le
sens de `-12` demanderait d'y admettre une rotation, c'est-a-dire une seconde bonne reponse : une erreur de contenu, pire que
l'heuristique. A `-12`, une suite de generations n'est pas un cycle, les rotations sont fausses, et la paire sure ne coute rien :
c'est pourquoi la regle y est neuve et n'y est que la.

**Ce que je pese** : c'est une heuristique de candidat qui compare des positions, rare chez un enfant de 7 ans, qui lit une
suite de gauche a droite. A `-01`, l'inverse s'ecarte d'abord par le sens (l'adulte en tete), et le choix restant entre la bonne
suite et un echange de voisins demande de savoir l'ordre. Je le regarderai quand je jouerai `-01`. Ce n'est ni une reserve ni
une correction : rien a classer pour `camille-po`.

## 6. Textes a reporter dans les stories, par `camille-po`

Texte exact, avec le marqueur *[arbitre par `nadia-prof` le 2026-10-27, `arbitrage-nadia-dernier-lot-ce1-qlm-04-a-12.md`]*. Les
regles nommees sont celles des blocs *"Corrections de `nadia-prof` du 2026-10-27"* deja reportes ; le reste ne change pas.

### 6.1 `S57-BR-QLM-CE1-04`, critere 11

> **Regle 6, phrase ajoutee le 2026-10-27, remplacee.** *"La liste des mots est fermee."* devient : Les mots refuses sont ceux des
> cinq noms de famille, avec leurs formes de genre et de nombre (*feculent*, *laitier*, *laitière*, *sucrée*, *poisson*, *œuf*...) ;
> la parenthese en donne des exemples. La regle ne porte que sur les mots, jamais sur le sens : un aliment dont le nom ne porte
> aucun de ces mots (*la pomme*, *la carotte*) est admis en enonce de A, meme s'il est un fruit ou un legume.

### 6.2 `S57-BR-QLM-CE1-05`, critere 12

> **Regle 2, derniere phrase, remplacee.** *"Aucune proposition n'est un changement que l'etat decrit rend impossible."* devient :
> Aucune proposition de B n'est le changement d'une matiere que l'enonce ne decrit pas (*l'eau gèle* sans eau liquide decrite,
> *la glace fond* ou *le glaçon fond* sans glace decrite). Le changement que la temperature de l'enonce empeche (*le glaçon fond*
> sous 0 degre) est un distracteur attendu.
>
> **Regle 2, phrase ajoutee.** Sous 0 degre s'ecrit *sous 0 degré*, *en dessous de 0 degré* ou *au-dessous de 0 degré*. Dans un
> item dont la bonne reponse est *rien ne change*, l'enonce n'emploie pas le mot *eau*, meme pour de l'eau gelee (*un glaçon*, pas
> *une bouteille d'eau gelée*).

### 6.3 `S57-BR-QLM-CE1-06`, critere 10

> **Regle 1, phrase ajoutee.** Tout item de B compte pour le quart. Au moins un item de A compte aussi pour le quart : A n'est
> jamais fait que d'items que la croyance *l'aimant attire les metaux* resout.
>
> **Regle 2, tache B, phrase ajoutee.** Le bon groupe n'est le seul plus court des trois, en mots (`PaquetLu.mots`), que dans la
> moitie des items de B au plus. Dans les autres, il porte *fil de fer* et un groupe distracteur a autant de mots que lui ou
> moins (bonne reponse *"fil de fer, clou, agrafe"* ; distracteurs *"fil de fer, épingle, papier d'aluminium"*, *"clou, épingle,
> fil de cuivre"*). Le developpeur signale dans son rapport, pour chaque item de B, le nombre de groupes qui portent *fil de fer*.

### 6.4 `S57-BR-QLM-CE1-08`, critere 9

> **Regle 1, tache B, phrase ajoutee.** Chacun des trois gestes est la bonne reponse d'au moins un item de B : *ne pas toucher*
> (*"ne pas toucher le fil"*, *"laisser le fil sans le toucher"*, *"ne pas toucher la prise"*, *"ne rien mettre dans la prise"*,
> *"ne pas toucher l'appareil"*) ; *ne pas approcher de l'eau* (*"ne pas l'approcher de l'eau"*) ; *ne pas brancher avec les mains
> mouillees* (*"attendre d'avoir les mains sèches"*, *"se sécher d'abord les mains"*, *"ne pas brancher la lampe"*). B porte au
> moins 4 items.
>
> **Regle 3, phrase remplacee.** La definition de l'abstention par *"laisse l'enfant hors du danger"* devient : une abstention est
> une conduite qu'un adulte attendrait de l'enfant (*s'éloigner*, *attendre*, *ne rien toucher*, *ne pas s'approcher*, *continuer
> à jouer ailleurs*) ; elle est refusee en distracteur.
>
> **Regle 3, phrase ajoutee.** Un distracteur negatif admis refuse de signaler le danger : *"ne rien dire à un adulte"*, *"ne pas
> déranger un adulte"*, *"ne rien dire à personne"*, *"ne pas en parler"*. Une proposition qui nomme l'imprudence par un mot de
> jugement (*"ne pas faire attention"*, *"être imprudent"*) est refusee : personne ne la choisit. Le developpeur repartit les
> distracteurs negatifs et signale dans son rapport combien de fois chacun revient.
>
> **Regle 5, phrase remplacee.** *"Un personnage nomme ne se blesse ni ne se fait mal, a aucun temps"* est complete : le present
> generique avec *on*, *on ne* ou *personne ne* (*"on se blesse"*, *"pour que personne ne se blesse"*) et l'infinitif passent ; un
> sujet qui designe une personne de la scene (*"Lina se blesse"*, *"Un élève se blesse"*) est refuse, a tout temps.

### 6.5 `S57-BR-QLM-CE1-10`, critere 9

> **`R10-4`, tache A, phrase ajoutee.** L'indice de A ne nomme aucun element propre d'un des trois paysages proposes : nommer
> *la mer* quand *le littoral* est un distracteur l'ecarte, ce qui est une demi-reponse.
>
> **`R10-4`, phrase ajoutee.** *Littoral* n'est ecrit dans aucun indice d'un item B dont le lieu n'est pas le littoral.
>
> **`R10-5`, derniere phrase, completee.** Aucun adjectif n'accompagne un element ou un paysage, dans l'enonce, une proposition
> ou l'indice (ni *"par exemple la grande mer"*).

### 6.6 `S57-BR-QLM-CE1-12` et `-01`

Rien a reporter.

## 7. Ajustements demandes a `theo-archi`, une ligne par garde (forme a lui)

| Repere | Lot, garde | Ce que je veux |
|---|---|---|
| `G1` | `-08`, `g1` partie paquet | refus d'un paquet ou l'un des trois gestes de 6.4 n'est la bonne reponse d'aucun item de B ; B au moins 4 |
| `G2` | `-08`, garde neuve (`Q10`) | refus d'un enonce `geste` qui porte les reperes de deux situations ; sonde refusee : *"Le sèche-cheveux est branché à la prise près du lavabo. Que doit faire Lina ?"* |
| `G3` | `-08`, garde neuve (`Q11`) | refus en distracteur des cinq abstentions nommees, ancrees au debut de la proposition |
| `G4` | `-08`, garde neuve (`Q12`) | refus d'un distracteur egal a *ne pas* ou *ne jamais* suivi d'une forme de la table de 2.1 de ton avis, dans tout item |
| `G5` | `-08`, `g7` (`Q8`, `Q9`) | forme du 2026-10-27 confirmee ; exempter *personne ne* comme *on ne* ; sondes passe *"pour que personne ne se blesse"*, refusee *"Un élève se blesse pendant la récréation."* |
| `G6` | `-05`, `F3` (`P2-0405`) | ajouter *au-dessous de* a `SOUS_ZERO` ; `EAU` inchange |
| `G7` | `-05`, regle 2 de B (`Q9-3`) | si tu le juges encodable : refus de *l'eau gèle* dans un item B dont l'enonce n'a pas *eau*, et de *la glace fond* ou *le glaçon fond* dans un item B dont l'enonce n'a ni *glace*, ni *glaçon*, ni *gelé* ; sinon *non garde : `ETE-CE1-2`*, je le lis |
| `G8` | `-05`, `F2` (`P1-0405`), `-04`, `T04-3e` (`P3-0405`) | inchanges |
| `G9` | `-06`, `g4` | refus d'un paquet ou aucun item de A ne compte pour le quart ; sonde : le paquet reel prive de ses items A qui comptent |
| `G10` | `-06`, garde neuve (`Q9-5`) | refus d'un paquet ou le bon groupe est le seul plus court des trois en mots (`PaquetLu.mots`) dans plus de la moitie des items de B ; l'exemple de 6.3 passe |
| `G11` | `-10`, `G10-4` A (`P2-1012`) | en A, refus d'un indice qui contient une forme d'un element propre d'un des trois paysages proposes |
| `G12` | `-10`, `G10-4` B (`P3-1012`) | refus de *littoral* dans l'indice d'un item B dont le lieu n'est pas le littoral, tel que tu l'as ecrit |
| `G13` | `-10`, `P1-1012` | rien de plus que ta regle (iii) pour l'exemple ; l'adjectif ailleurs dans l'indice, *non garde : `PAY-CE1-2`*, je le lis |

**Ce dont je reste la seule garde, en plus de ce que mes fichiers du 2026-10-27 et vos avis listent** : qu'un distracteur
negatif soit plausible et ne nomme pas un jugement (`-08`) ; la repartition des distracteurs negatifs (`-08`) ; que *fil de fer*
ne devienne pas le signe du bon groupe (`-06`) ; l'adjectif dans l'indice hors de l'exemple de *littoral* (`-10`) ; le
changement sans objet en B de `-05`, si `G7` n'est pas encode. **Aucune n'est couverte par un test.**

## 8. Cout, duree, verifications

**Cout : 0,00**, par la decision de l'utilisatrice du 2026-10-27 : les arbitrages avant premier jet ne sont pas chiffres.

**Duree**, lue par `date` : debut **09:28:04**, fin **09:41:32**, apres les verifications et avant le commit, soit
**13 min 28 s**, dont deux executions du garde-fou des identifiants.

**Verifications avant commit**, ce fichier present dans l'arbre, base `05e66408`. Il n'ecrit ni ne renomme aucun identifiant de
condition : `Q9-1`, `P1-0405`, `G1`... sont des reperes de demande.

- `bash outils/mvn-jdk21.sh -pl bootstrap -am test -Dtest=ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest`, telle quelle :
  *"BUILD FAILURE"* sur `recreo-domain`, *"No tests matching pattern "ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest" were
  executed!"*. Le garde-fou n'a pas ete lance ; meme symptome que dans mes fichiers du 2026-10-26 et du 2026-10-27.
- La meme commande avec `-Dsurefire.failIfNoSpecifiedTests=false` : *"Tests run: 10, Failures: 0, Errors: 0, Skipped: 0"*,
  *"BUILD SUCCESS"*. Perimetre : ce seul test, module `bootstrap` et ses dependances ; ni un `verify`, ni la porte technique.
- `.github/scripts/verifier-chemins.sh`, entree standard fermee : *"Aucun chemin de poste de travail ni nom de compte dans les
  fichiers verifies."*
- Aucun autre job de `.github/workflows/ci.yml` lance.

**Signe** : `nadia-prof`, s57, palier 5, 2026-10-27.
