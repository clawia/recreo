# s57, palier 2 - `nadia-prof` : lecture complete de `ce1-pronoms` et `ce1-familles-de-mots`, mots de `Q-N4`, reponses aux questions du palier

- **Auteure** : `nadia-prof`, professeure des ecoles, seule autorite sur le Done pedagogique.
- **Date** : 2026-10-16, 08:15 (UTC+02:00), lu par `date`.
- **Arbre** : `round-two` a `f6134910`, branche `s57-nadia-p2-a`, worktree dedie ; `bash outils/verifier-lignee.sh` sort 0, `git status --short` vide avant ecriture.
- **Instances** : A (`ce1-pronoms`, `Q-P5`, `Q-P6`, `Q-S9`), A' (`ce1-familles-de-mots`, `Q-P3`, `Q-P4`, `Q-S8`, `Q-B2`, annotation de la fiche de `phrases-types`). Pris sur le 0,5 d'arbitrage prealable du palier 2 de chaque instance (`arbitrage-nadia-palier-2.md`, section 12). Aucun prix neuf.
- **Audit degrade, declare** : aucun des deux lots n'est branche (`grep -c "paquet: ce1-pronoms$"` et `grep -c "paquet: ce1-familles-de-mots$"` sur `catalogue.yaml` rendent 0, `planning.md`, amendement du 2026-10-15, section 9). **Je n'ai rien joue et aucun jar n'a ete lance.** J'ai lu les items dans les fichiers. **Aucun Done, aucun verdict, aucune levee** : les audits viennent apres les livraisons.

## Resume

| Code | Question ou point | Decision | Section |
|---|---|---|---|
| RQ1 | Lecture complete, `S57-ARB-PALIER-2` critere 2 | **Faite** : `ce1-pronoms` 20 items, `ce1-familles-de-mots` 24 items | 1 |
| RQ2 | Mots de l'utilisatrice pour `Q-N4` | **Annotes** dans les deux fichiers ; **ils ne changent pas le regime du pli** | 2 |
| RQ3 | `Q-P3`, `PA3` (`R3-1`) | **Accord**, une condition regardee au rejeu | 3.1 |
| RQ4 | `Q-P3`, `PA4` (ascenseur a la revelation) | **Observation sans reserve**, comme le decalage de 7,5 px, une condition regardee au rejeu | 3.2 |
| RQ5 | `Q-P4` (base de code identique) | **Oui, si l'arbre de tout ce qui entre dans le jar est identique** ; sinon la cote se remesure | 3.3 |
| RQ6 | `Q-P5`, `PA7` (`RCT-CE1-R1`, 3 items a 552) | **Regle du bandeau non accordee** ; textes plus courts donnes pour les trois items, sous une mesure | 3.4 |
| RQ7 | `Q-P6` (item 15 de `ce1-pronoms`) | **Correction avant le branchement, cible 0** ; texte donne | 3.5 |
| RQ8 | `Q-S8` (item *livre*) | **Item remplace** : *chat*, *chaton* ; texte donne | 3.6 |
| RQ9 | `Q-B2` | **Deja repondue le 2026-10-14** ; relecture des cinq items promise, faite ici, et correction de mon propre constat | 3.7 |
| RQ10 | `Q-S9` (voie de `VSJ-CE1-R2`) | **La forme commune**, sans imposer la voie | 3.8 |
| RQ11 | Lecture de `ce1-pronoms` : indices des items 10 et 13 | **Correction demandee** (chevrons, mon point d'audit) | 1.1 |

---

## 0. Ce que j'ai lu et mesure

| Mesure | Commande ou fichier | Rendu |
|---|---|---|
| Arbre | `git log -1 --format=%h` | `f6134910` |
| Compte des items | `grep -c 'bonneReponse:'` sur `ce1-pronoms.yaml` et `ce1-familles-de-mots.yaml` | 20 et 24 |
| Dernier commit de chaque paquet | `git log -1 --format=%h -- <paquet>` | `46490d0d` (`ce1-pronoms`), `29d02bc6` (`ce1-familles-de-mots`) |
| Espaces des enonces | `od -c` sur une ligne d'enonce de chaque paquet | U+00A0 dans les chevrons et devant `?` |
| Lu en entier | les deux paquets ; `arbitrage-nadia-palier-2.md` ; `planning.md`, amendement du 2026-10-15 20:56 (sections 1 a 13) ; `composition-palier-2.md` ; `rapport-ines-r8-forme-commune.md` ; backlog, stories `S57-BR-FRA-CE1-05`, `-24`, `S57-FRA-FAMILLES-LIVRE`, `S57-ARB-PALIER-2`, `S57-FORME-COMMUNE-PROPOSITIONS-LONGUES`, `S57-VSJ-CE1-R2`, `S57-RCT-CE1-R1` et son amendement ; mes fichiers `reponse-nadia-forme2-palier-1.md`, `reponse-nadia-q-palier-1.md` (1, 5.3), `lecture-nadia-palier-1.md` (1.7), `rejeu-nadia-b2-palier-1.md` (4) ; `DEC-0062` (3.1, A, B) ; s48, `TRV-CE1-2` a `-7` ; items 13, 17 et 18 de `ce1-texte-narratif` | - |

**Regle de comptage des mots** : celle de `lecture-nadia-palier-1.md` section 0 (un mot porte au moins une lettre ou un chiffre ; U+00A0 est un blanc).

## 1. Lecture complete (`S57-ARB-PALIER-2`, critere 2)

Lue le 2026-10-16 sur `f6134910`, item par item, enonce, texte, bonne reponse, distracteurs et indice. **Ces lignes precedent tout verdict sur ces deux paquets** ; le commit qui les porte devra etre ancetre de celui de chaque verdict (critere 3).

- lecture complete : `ce1-pronoms`, 20 items lus (2026-10-16, version de `46490d0d`).
- lecture complete : `ce1-familles-de-mots`, 24 items lus (2026-10-16, version de `29d02bc6`).

*Forme corrigee le 2026-10-16 (`nadia-prof`), fond inchange : ces deux lignes portaient `**` autour des deux premiers mots, ce qui les ecartait de ma propre forme (arbitrage du palier 2, 6.1, point 2) et faisait rendre 0 a la commande de `DEC-0120` A.96 et A.98. Ecart trouve par `sofia-sm`.*

**Ce que la ligne lit** : ces deux versions. Les corrections demandees ci-dessous changent trois items de `ce1-pronoms` (10, 13, 15) et un item de `ce1-familles-de-mots` (*livre*) ; je relirai au jeu, dans le verdict meme, chaque item qui aura change entre ces versions et le commit joue, comme au palier 1 (`arbitrage-nadia-palier-2.md`, section 4).

### 1.1 `ce1-pronoms` : ce que la lecture releve

| Item (compte depuis 1) | Constat | Suite |
|---|---|---|
| 15 | *"Elias"* est la seule proposition a majuscule (`PN4`) | correction, `Q-P6` (3.5) |
| 10 et 13 | l'indice *"Elle désigne un mot féminin : cherche-le dans la phrase d'avant."* parle du mot *elle*, pas d'une personne : **c'est un mot cite**, au sens de mon point d'audit (`reponse-nadia-q-palier-1.md` 4.2), que `PN7` me laissait lire | **correction demandee** ci-dessous |
| 16 | *"Mina et Hamza regardent un chien. Le garçon lui donne de l'eau."* : seul le prenom dit qui est le garcon | point d'audit, joue ; ni refus ni reserve a la lecture |
| 19 | *"Nael"* | orthographe d'un prenom : au `relecteur-pedagogique`, pas a moi |
| les 16 autres | referent unique, `PRN-CE1-3` tenue a la lecture, aucun `il` impersonnel | rien |

**Correction demandee, items 10 et 13, texte exact de l'indice** (meme typographie des chevrons que les enonces du paquet) :

> `« Elle » désigne un mot féminin : cherche-le dans la phrase d'avant.`

Dix mots (`TRV-CE1-5` : douze au plus). Rien d'autre ne change sur ces deux items. Motif : sans chevrons, *"Elle désigne"* se lit comme une personne qui designe, et l'enfant de CE1 cherche une fille dans le texte avant de comprendre qu'on parle du mot.

### 1.2 `ce1-familles-de-mots` : ce que la lecture releve

| Item | Constat | Suite |
|---|---|---|
| *livre* | *librairie*, famille savante (`FAM-CE1-2`) | remplacement, `Q-S8` (3.6) |
| les 14 items de famille | chaque indice ne departage les deux propositions restantes qu'apres une des deux erreurs possibles | `Q-B2` (3.7) |
| *découvrir* | *"le contraire de couvrir"* : sens du prefixe, seul sens propose ; reponse unique | rien |
| les 9 autres items d'affixe | indices *"Reconnais-tu un mot connu..."* au debut ou a la fin, conformes au mot ; *préfixe*, *suffixe*, *radical* absents (`TRV-CE1-4`) | rien |

## 2. `Q-N4` : les mots de l'utilisatrice

**Mots** : *"le pli n'est pas bloquant"*. **Provenance**, forme 2 de `DEC-0062` 3.1 : mots donnes par l'utilisatrice a l'agent principal le 2026-10-16 ; la date de son echange d'origine n'a pas ete fournie (le depot date la reponse relayee au 2026-10-14). Relayes a moi par l'agent principal ; je ne les ai pas lus de sa main. **Cette occurrence fige la citation** (`DEC-0062`, 3.5) : tout fichier ulterieur cite celui-ci ou les deux annotations ci-dessous.

**Ecrits** dans `rejeu-nadia-palier-1.md` et `squad/conformite/FICHE-bandit-manchot-phrases-types-ce1.md`, par une annotation datee en fin de fichier ; rien au-dessus n'est efface.

**Ce qu'ils changent** : `D3` du `relecteur-pedagogique` (forme 2 incomplete) est rempli pour les mots ; la date reste incomplete et l'annotation le dit. **Ce qu'ils ne changent pas** : ils disent la meme chose que ma notation *"`Q-N4` = non"* ; aucun verdict ne bouge ; le regime du pli pour les lots du palier 2 reste celui de `arbitrage-nadia-palier-2.md` 6.1, point 4 (reserve non bloquante, nommee et datee, precedent de `RDO-CP-R1`). La clause de la section 9 de l'arbitrage (*"si ses mots disaient autre chose que non"*) ne joue pas.

**Ce qu'ils ne disent pas, et que je ne leur fais pas dire** : que le pli cesse d'etre une reserve ou d'etre mesure ; qu'un critere de levee change ; qu'un debordement a droite (`RDO-CP-R3`) ou un autre motif de refus soit couvert ; qu'une forme commune soit ou non a construire. Je les lis sur la question posee, rien de plus.

**`S2`** : toujours sans mots. Je n'en ecris aucun.

## 3. Reponses

### 3.1 `Q-P3`, `PA3` : accord sur `R3-1`

**Decision : accord.** `R3-1` (`padding-top: 0` sur `.zone-jeu` quand elle porte une manche) entre dans la forme commune.

**Motif.** Elle vaut 32 des 102 px, et sans elle l'item le plus bas finit a 580 (`rapport-ines-r8-forme-commune.md`, section 3) : la forme ne leve plus les reserves, et l'enfant bloque ne voit pas l'indice. Ce qu'elle coute est l'air au-dessus du score, un texte que l'enfant n'a pas besoin de lire pour repondre ni pour comprendre son erreur. C'est le meme arbitrage que mon motif de `L1` : *"entre un sourire decoratif et la phrase qu'on lui lit, je garde la phrase"*. Le saut de 32 px vers l'ecran de fin se fait au changement d'ecran, pas sous le doigt.

**Condition, regardee au rejeu de `TPH-CE1-R1`** : a 744 x 551, aucune lettre du score n'est couverte ni touchee par la bande de l'en-tete, et le score se lit comme une ligne du jeu, pas de l'en-tete. Si elle n'est pas tenue, c'est une reserve neuve, datee, non bloquante, qui ne retire pas la levee. Les captures d'`ines-design` ne sont pas versees : je juge sur mon jeu, pas sur sa description.

**Ce qui ne depend pas de moi et reste ouvert** : `PA5` et `E3` (`theo-archi`). Mon accord ne les remplace pas.

### 3.2 `Q-P3`, `PA4` : l'ascenseur a la revelation

**Decision : observation sans reserve**, du meme ordre que le decalage de 7,5 px d'`ART-CP-10` et que la reduction a 720 (`rejeu-nadia-b2-palier-1.md`, section 4).

**Motif.** Le mouvement (7,5 px a 744 ; cases plus etroites de 15 px a 720) vient de la barre de defilement, pas d'une regle qui vise un etat ; il arrive a la revelation, quand l'enfant voit deux propositions barrees et la seule qui reste ; aucune rangee ne change (0 sur 72 aux trois largeurs, rapport, 6.1). En faire une reserve ici, apres avoir classe la meme cause deux fois, ce serait relever ma barre.

**Condition, regardee au rejeu** : a 720, aucun libelle de proposition ne change de nombre de lignes quand les cases retrecissent. Le rapport mesure les rangees, pas les lignes dans une case. Un libelle qui passe d'une ligne a deux a la revelation serait une reserve neuve, datee, non bloquante.

**Ce que je ne demande pas** : `scrollbar-gutter`, regle globale, est a `theo-archi` (`PA5`). S'il la retient, la levee se mesure sur le jar qui la porte.

### 3.3 `Q-P4` : une base de code identique garde-t-elle la cote ?

**Decision : oui, a une condition.** La cote mesuree sur le jar de `6dbb855d` vaut pour la base reelle de la branche de la forme (`Q-R5` : le commit d'ou elle part) **si l'arbre de tout ce qui entre dans le jar est identique entre les deux** : `git diff --stat 6dbb855d <base> -- pom.xml domain application adapters bootstrap` ne rend rien, commande et sortie dans le fichier date de la cote. Deux arbres identiques servent le meme jar : la cote ne mesure pas un autre ecran, ce que `Q-R5` empeche.

**Sinon, la cote se remesure sur la base reelle**, sans exception de contenu : un lot fusionne (`-05`, `-24`) touche `adapters/adapter-content` et rend la sortie non vide. Je ne juge pas, a la place d'une mesure, qu'un paquet neuf ne change pas l'ecran d'un autre jeu. Si `theo-archi` nomme un perimetre du jar different de celui-ci, le sien fait foi.

**Ce qui reste inchange** : la methode, la largeur, la hauteur et les deux commandes de `Q-R5` (`merge-base --is-ancestor`, `diff --stat <base> <commit livre>`). La regle de `planning.md` section 9 (*"toute fusion sur `round-two` entre la mesure et la coupe force la remesure"*) est plus stricte que la mienne ; elle est a `sofia-sm`, je ne l'assouplis pas.

### 3.4 `Q-P5`, `PA7` : `RCT-CE1-R1`, les trois items a 552

**Decision : la regle du bandeau (b) n'est pas accordee.** Elle reduit le bandeau du meuble sur les 145 jeux pour faire gagner 4 px a trois items d'un jeu ; c'est une part de `L1`, que je n'accorde pas d'avance (`Q-T5`), et le releve que `Q-T5` point 3 demande pour trancher (avec et sans, sur ces paquets **et** sur la clause de non-regression) n'existe pas pour elle : deux jeux mesures sur quatre, ni la clause ni l'echantillon. Elle laisserait aussi 3 px de marge.

**Restent la voie (a), des propositions plus courtes, ou tout autre moyen sans `L1` ni `L2`.** Le choix entre eux reste au developpeur et a `theo-archi` (critere 3 amende ; mes mots *"Je ne choisis pas la voie."* tiennent). **Si c'est (a), les textes sont a moi, et les voici** ; textes support, enonces et indices inchanges (numeros comptes depuis 0, comme le rapport d'`ines-design`) :

| Item | Champ | Texte exact |
|---|---|---|
| 13, Inès | `bonneReponse` | `saute dans une flaque` |
| | `distracteurs` | `["enfile ses bottes", "sort dehors"]` |
| 17, Ibrahim | `bonneReponse` | `s'assoit près de la fenêtre` |
| | `distracteurs` | `["attend le bus", "monte"]` |
| 18, Chloé | `bonneReponse` | `une fleur pousse` (inchangee) |
| | `distracteurs` | `["Chloé plante une graine", "elle l'arrose"]` |

Chaque proposition est une copie raccourcie du texte (`RCT-CE1-2`), six mots au plus (`LON-CE1-5`), et aucune bonne reponse n'est seule a majuscule (`D1`). Sur Inès et Ibrahim, les trois propositions prennent la forme sans sujet des items de Nour et de Sara, ce qui retire l'intrus du premier mot ; sur Ibrahim, *"le bus arrive"*, que le bus fait et pas Ibrahim, devient *"monte"*, une action d'Ibrahim, distracteur d'ordre plus juste.

**Condition, qui n'existe pas encore** : une mesure, par le developpeur ou `ines-design`, sur le jar qui porte la forme et ces textes, a 744 x 551, apres une erreur, dans les six ordres : bas de `.indice` au plus 551 sur les 20 items. **Je ne sais pas si ces textes font gagner une rangee** ; si la mesure dit non, la question me revient, et (b) ne revient qu'avec le releve de `Q-T5` point 3. Prix : 0,5 (des textes changent), celui que la composition retient. Engagement apres la fusion de la forme, inchange.

### 3.5 `Q-P6` : item 15 de `ce1-pronoms`

**Decision : correction avant le branchement, cible 0** (`PN4`), comme `D1` pour `ce1-texte-narratif` (`lecture-nadia-palier-1.md`, 1.1). Une valeur consignee et jugee au jeu me ferait valider un item dont la majuscule donne la reponse a l'enfant qui ne lit pas le texte.

**Texte exact** (enonce et indice inchanges) :

```
  - enonce: "Que désigne « il » dans le texte ?"
    texteSupport: "Le chat de Julie voit Elias. Il lui donne un biscuit."
    bonneReponse: "Elias"
    distracteurs: ["le chat", "Julie"]
    indice: "Le sens de la phrase aide à choisir."
```

Espaces de l'enonce : celles du paquet (U+00A0 dans les chevrons et devant `?`). **Ce qui tient** : *il* n'apparait qu'une fois et son referent est dans la phrase d'avant (`PRN-CE1-3`) ; deux candidats masculins singuliers, *le chat* et *Elias*, que le sens departage (l'item reste dans les dix de `PRN-CE1-2`) ; *Julie* s'ecarte par l'accord ; deux propositions a majuscule (`PN4` a 0) ; onze mots dans le texte (`PN5`). **Ce qui change ailleurs** : un prenom feminin de plus, 21 pour 20 masculins, dans l'equilibre a un pres de `TRV-CE1-6` ; le commentaire de tete du paquet qui dit *"vingt prenoms feminins"* est au developpeur.

### 3.6 `Q-S8` : l'item *livre*

**Decision : l'item est remplace par un autre mot de depart.** *Livret* garderait *livre*, mais face a *livrer* l'item se jouerait sur une lettre finale : un exercice de decodage, pas de famille. *Chat* et *chaton* sont une famille transparente pour un enfant de CE1, et *château* est la fausse famille que `FAM-CE1-2` (a) demande.

**Texte exact**, a la place de l'item *livre*, meme rang dans le paquet :

```
  # FAM-CE1-2: a, b
  - enonce: "Quel mot est de la même famille que « chat » ?"
    bonneReponse: "chaton"
    distracteurs: ["château", "souris"]
    indice: "Le bon mot garde un morceau et parle de la même chose."
```

Espaces de l'enonce : celles des 13 autres enonces de famille. **Ce qui tient** : 14 items de famille et 10 d'affixe (`FM2`) ; distracteurs dans l'ordre (a), (b) ; *chaton* n'est pas la plus longue proposition (`TRV-CE1-2`) ; indice de douze mots, qui ne donne pas la reponse et departage apres chacune des deux erreurs (3.7) ; aucun mot de `TRV-CE1-4`. `grep -c 'bonneReponse: "librairie"'` rendra 0. L'orthotypographie est au `relecteur-pedagogique`.

### 3.7 `Q-B2` : deja repondue, et la relecture promise

**`Q-B2` a ete repondue le 2026-10-14** (`lecture-nadia-palier-1.md`, section 1.7, `D8`, commit `7161ab69`, sur `round-two`) : l'indice que j'avais cite n'etait pas celui de l'item, et le motif de fond tient. `composition-palier-2.md` (section 3) et l'amendement du backlog `S57-FRA-FAMILLES-LIVRE` l'ecrivent *"non repondue"* : c'est inexact ; la correction est a leur auteure, dans ses fichiers.

**J'y avais promis de relire a l'arbitrage du palier 2 les cinq items qui portent *"Cherche le mot qui parle de la même chose."*. Je ne l'ai pas fait dans l'arbitrage ; je le fais ici.**

**Correction de mon propre constat.** J'ai ecrit en 1.7 que cet indice *"fait chercher par le theme dans une ligne qui apprend le radical"*. C'est la moitie du fait. Sur chaque item de famille il reste, apres la premiere erreur, la bonne reponse et un distracteur :

- *"Cherche le mot qui parle de la même chose."* (5 items : *lait*, *danse*, *livre*, *pomme*, *sable*) departage apres une erreur sur le distracteur de theme (b), pas apres une erreur sur la fausse famille (a) : *laitier* et *fromage* parlent tous deux du lait ;
- *"Un mot de la même famille garde un morceau du mot."* (5 items : *dent*, *terre*, *savon*, *porte*, *nuage*) fait l'inverse : *terrain* et *terrible* gardent tous deux un morceau ;
- *"Compare les trois mots avec le mot de départ."* (4 items) ne departage aucune des deux.

**Aucun de ces indices n'enseigne une regle fausse** (`TRV-CE1-5`) : chacun dit une moitie de ce qu'est une famille, et l'enfant avance toujours par la revelation. **Pas un motif de refus, pas une correction exigee avant le branchement**, hors l'item *livre*, remplace en 3.6. **Recommandation non bloquante**, a `camille-po` de la prendre ou non : l'indice de 3.6 sur les 14 items de famille. Si elle ne la prend pas, je juge au jeu, en commettant les deux erreurs sur chaque item de famille, si l'enfant qui s'est trompe peut avancer ; ce que j'y trouverai sera au plus une reserve non bloquante, et je l'ecris ici pour qu'elle ne surprenne personne.

### 3.8 `Q-S9` : la voie de `VSJ-CE1-R2`

**Decision : la voie de la forme commune.** Elle porte les 24 items a 418 - 496 (rapport, section 4), 55 px sous le pli au plus bas, sans toucher un paquet `VALIDE` ; la forme se construit de toute facon pour `TPH-CE1-R1` et `GNA-CE1-R1`. Les textes changeraient deux items d'un contenu valide pour 31 px que la forme couvre deja. La cote de 570 sous Courier New n'est pas un motif : la feuille ne sert pas cette police (`reponse-nadia-forme2-palier-1.md`, 2.1).

**Ce que ce n'est pas** : une voie imposee contre `theo-archi` et le developpeur (critere 3 de la story : *"je ne l'impose pas"*). S'ils choisissent les textes, je joue les textes. **Consequences** : prix 0,25 (paquet inchange), et non 0,5 ; l'ecart reste non affecte (composition, section 2). p2-11 s'engage avec la forme, comme p2-8, et se rejoue sur le jar ou `TPH-CE1-R1` est leve ou non. **Condition qui n'existe pas encore** : l'avis de `theo-archi` sur la forme (`PA5`). S'il retire une regle dont depend la cote de `ce1-verbe-sujet`, la question me revient.

## 4. Ce dont je reste la seule garde, ajoute par ce fichier

| Propriete | Pourquoi aucun test ne la tient |
|---|---|
| Que le sens departage *le chat* et *Elias* sur l'item 15 corrige | un test voit le genre des mots, pas le sens |
| Qu'un indice departage apres chaque erreur sur les items de famille | un test lit un indice, pas ce qu'il laisse a l'enfant apres une erreur donnee |
| Que *chaton* et *château* soient la vraie et la fausse famille | un test voit un mot, pas son etymologie |
| Que le score se lise comme une ligne du jeu avec `R3-1` | une cote ne dit pas si deux blocs se confondent |
| Qu'aucun libelle ne change de lignes a la revelation a 720 | le rapport mesure les rangees, pas les lignes |

## 5. Routage

| A qui | Quoi |
|---|---|
| `marc-dev` (p2-3) | 1.1 (indices des items 10 et 13) et 3.5 (item 15), recopies sans reformulation, avant le commit du lot ; `PN8` |
| `sofia-sm` (developpeuse, p2-4) | 3.6 (item *livre* remplace), recopie sans reformulation ; `FM5` ; 3.7 seulement si `camille-po` prend la recommandation |
| `sofia-sm` (Scrum Master) | `PA3`, `PA4`, `PA7`, `Q-P4`, `Q-P6`, `Q-S8`, `Q-S9` repondues ; la lecture complete (section 1) pour la cloture ; prix de p2-11 : 0,25 par la forme |
| `camille-po` | amendement de `PN8` et `FM5` portant ces corrections ; *"`Q-B2` non repondue"* a corriger dans ses fichiers (3.7) ; la recommandation de 3.7 |
| `theo-archi`, `ines-design` | 3.1 et 3.2 pour la forme ; 3.3 pour la cote ; 3.4, la mesure de la voie (a) si elle est choisie |
| `relecteur-pedagogique` | *"Nael"* (1.1) ; l'orthotypographie des textes de 1.1, 3.4, 3.5 et 3.6 avant mes audits |

## 6. Ce que ce fichier ne fait pas

- Il ne prononce aucun Done, ne leve aucune reserve, ne joue aucun jeu, ne lance aucun jar.
- Il ne modifie ni un paquet, ni le catalogue, ni la matrice, ni du code : les textes ci-dessus sont a recopier par les developpeurs nommes.
- Il n'ecrit et n'alloue **aucun identifiant de condition ou de reserve** ; `RQ1` a `RQ11` sont des reperes de ce fichier.
- Il ne tranche ni `Q-S4`, ni `Q-S6`, ni `Q-S7`, ni `PA5`, ni `PA8`.

**Signe** : `nadia-prof`, s57, palier 2, instances A et A', 2026-10-16.

## 7. Jobs de `ci.yml` lances pour ce commit

Sur ce worktree, les trois fichiers du commit dans l'index, avant commit. Je ne deduis aucun job que je n'ai pas lance.

- **`chemins`** : `verifier-chemins-test.sh` sort 0 ; `git ls-files` (moins le test du garde-fou) passe a `verifier-chemins.sh` : *"Aucun chemin de poste de travail ni nom de compte dans les fichiers verifies."*, sortie 0.
- **`prefixes`** : `verifier-prefixes-test.sh`, `commit-atteignable-test.sh` et `verifier-garde-fou-accent-test.sh` (21 cas) sortent 0 ; le sujet du commit passe dans `verifier-prefixes.sh` : *"Tous les commits portent un prefixe d'agent connu."*, sortie 0. Le controle du proprietaire du garde-fou de l'accent n'est pas lance : aucun de ses chemins n'est touche.
- **`build`, partiel** : `bash outils/mvn-jdk21.sh -B -pl bootstrap -am test` restreint a `ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest` et `AucuneLigneDeTableauNePortePlusDeCellulesQueSonEnTeteTest`, perimetre `bootstrap` et ses amonts : `BUILD SUCCESS`, 15 tests (10 et 5), 0 echec. Lance parce que ces tests lisent `squad/` ; aucun identifiant n'est ecrit ni alloue. Le reste du `build` et l'etape *"Package du fat jar"* ne sont pas lances : ce commit ne touche ni code ni contenu servi. Le build a lu ce fichier avant l'ajout de cette section, qui ne contient aucun tableau.
- **`outils`** : non lance ; aucun outil n'est modifie.

Aucun de ces resultats n'est celui de la CI distante, que je n'ai pas lue.
