# s57, palier 1 - reponses de `nadia-prof` aux questions qui bloquent des postes, lecture des items des trois lots, engagement des postes 7 et 8

- **Auteure** : `nadia-prof`, professeure des ecoles, seule autorite sur le Done pedagogique.
- **Date** : 2026-10-14, 16:27 (UTC+02:00), lu par `date`.
- **Arbre** : `round-two` a `3a3ea97e`, branche `s57-nadia-lecture`, worktree dedie ; `bash outils/verifier-lignee.sh` sort 0.
- **Instances** : A (`Q-B1`, `Q-Y6`, `Q-Y2`, lecture de `ce1-texte-narratif`), A' (lecture de `ce1-phrases-types` et de
  `ce1-accord-groupe-nominal`), B (`Q1`, `Q2`, `Q-Y5`) ; `Q-B2` porte sur le palier 2. Pris sur le 0,5 d'arbitrage prealable de chaque
  instance (`arbitrage-nadia-palier-1.md`, section 4 : *"la lecture complete des items de chaque lot reste due [...] sur ce meme
  poste"*). Aucun prix neuf.
- **Audit degrade, declare** : aucun des trois lots n'est branche (`grep -c "paquet: <nom>$"` sur `catalogue.yaml` rend 0 pour
  chacun, `arbitrage-nadia-palier-1.md` section 0, inchange depuis) et `ART-CP-10` n'est pas servi. **Je n'ai rien joue.** J'ai lu les
  items dans les fichiers et compte par un script de poste hors depot. **Une lecture de fichier n'est pas un audit** ; aucun Done,
  aucun verdict.

## Resume

| # | Question ou point | Decision | Section |
|---|---|---|---|
| D1 | `Q-B1`, point 6.1 (1), majuscule | **correction avant le branchement, cible 0**, sur les 20 items | 1.1 |
| D2 | `Q-B1`, point 6.1 (2), indice de Sara | **correction avant le branchement, cible 0** | 1.1 |
| D3 | `Q-Y6`, comptage de `LON-CE1-3` et `-4` | **la regle qui fait foi est celle de s47**, deja ecrite ; elle rend 22, 4 et 10, comme celle de `yanis-qa` | 1.2 |
| D4 | `Q-Y2`, mon exemple de `VSJ-CE1-R1` | **une illustration, fautive contre ma propre `J1`** ; retiree comme modele ; propriete 8 amendee | 1.3 |
| D5 | `Q1`, `RDO-CP-R1` dans le meme rejeu | **oui** : la forme vise 125 px a 744 et 17 px a 992. Le refus se leve sur son seul critere | 1.4 |
| D6 | `Q2`, voie de `RDO-CP-R2` | **voie (b)**, le controleur ; (a) et (c) ecartees | 1.5 |
| D7 | `Q-Y5`, liste qui ferme `RDO-CP-R2` | **une liste fermee des textes permis**, pas une liste de mots interdits | 1.6 |
| D8 | `Q-B2`, l'indice cite en 6.3 | **non, ce n'est pas celui de l'item** ; le motif tient, et l'indice de l'item l'aggrave | 1.7 |
| D9 | Propriete 8 | **n'etait pas amendee ; amendee dans ce commit** | 3 |
| D10 | Postes 7 et 8 | **engageables** aux conditions de la section 4 ; rien n'y manque de ma part | 4 |

---

## 0. Ce que j'ai lu et mesure

| Mesure | Commande ou fichier | Rendu |
|---|---|---|
| Arbre | `git rev-parse --short HEAD` | `3a3ea97e` |
| Propriete 8 amendee avant ce commit ? | `git log --oneline -- squad/conformite/ARBITRAGE-PREALABLE-s56-instance-a-francais-ce1.md` ; `grep -n -i amend` sur ce fichier | un seul commit, `c92ad8f7` (s56) ; aucune ligne d'amendement de la propriete 8 : **non amendee** |
| `LON-CE1-3`, `-4` sur `ce1-texte-narratif` | script de poste, regle de s47 (1.2) | au plus 22 mots, 4 phrases, 10 mots par phrase |
| Bonne reponse seule a majuscule, `ce1-texte-narratif` | meme script, premiere lettre en majuscule Unicode | 2 items sur 20 (Sara, Nour) |
| Propositions hors du texte (`RCT-CE1-2`) | meme script, comparaison sans casse | 0 |
| Bonne reponse strictement la plus longue (`TRV-CE1-2`) | meme script | `ce1-texte-narratif` 5 / 20 ; `ce1-phrases-types` 5 / 24 ; `ce1-accord-groupe-nominal` 10 / 24 |
| `TPH-CE1-1` recompte sur les champs | script, `bonneReponse` | reconnaitre 9 ; declarative 5, interrogative 5, exclamative 5 |
| `GNA-CE1-1` recompte sur les champs | script, `bonneReponse` | adjectif 21 ; pluriel en -x 12 ; feminin 6 ; pluriel en -s 6 |
| Indices distincts | script | 18 ; 5 ; 4 |
| Exemple de `VSJ-CE1-R1` corrige | worktree jetable sur `3a3ea97e`, `bash outils/mvn-jdk21.sh -B -pl adapters/adapter-content -am test -Dtest=PaquetCe1VerbeSujetTest` | 22 tests, 2 echecs attendus (amendement, 2.8 de l'arbitrage de s56) ; JDK 21.0.10 |
| Indice de l'item *livre* | `grep -n -B3 -A4 librairie` sur `ce1-familles-de-mots.yaml` | ligne 84 : *"Cherche le mot qui parle de la même chose."* |
| Ecran de fin | `fragments/retour.html` ; `PartieController.retourModele` | textes du gabarit et booleens lus (1.5, 1.6) |
| Lu en entier | les trois paquets du palier 1 ; `cp-rapprocher-deux-oeuvres.yaml` ; `PaquetCe1VerbeSujetTest` ; fiches de `-13` et d'`ART-CP-10` ; arbitrages s47, s48 (2, 3.4, 5.1, 5.4, 5.6, 6.2), s55 A (3.2 a 3.4), s56 A ; `planning.md`, `avis-faisabilite.md`, `lecture-testabilite.md`, `composition-palier-1.md` ; backlog, stories `S56-*` et `S57-*` du palier | - |

**Regle de mes scripts**, dite pour que ces nombres se reproduisent : un mot est une suite de caracteres separee par des blancs
(U+00A0 compris) qui porte au moins une lettre ou un chiffre ; une fin de phrase est un `.`, un `?` ou un `!` qui termine le texte ou
qu'une majuscule suit. Une proposition est cherchee dans le texte sans casse.

---

## 1. Reponses

### 1.1 `Q-B1` : `ce1-texte-narratif`, points 6.1 (1) et (2)

**Correction de mon propre constat, avant d'arbitrer.** J'ai ecrit en 6.1 (1) que le biais de la majuscule *"n'est pas
systematique"* parce que, sur les quatre autres items d'ordre, la majuscule designe un distracteur. **Relu item par item, c'est
l'inverse** : sur les six items d'ordre ou une seule proposition porte une majuscule, **cette proposition est chaque fois le premier
evenement du texte**, parce que le premier evenement ouvre le texte avec le prenom (Inès, Sara, Paul, Nour, Ibrahim, Chloé). Le signe
de surface est donc parfaitement regulier : *"majuscule = ce qui arrive en premier"*. Sur les deux items qui demandent ce qui arrive en
premier (*"Que fait Sara avant de couper la pomme ?"*, *"Que fait Nour en premier ?"*), il donne la reponse sans lire le texte. Ces
deux items ne travaillent pas `FRA-CE1-04` (etape 2 de ma methode, l'ancrage) : c'est le principe de `NAT-1` et de `CLM-CE1-3`,
*"aucun signe de surface ne repond a la place de l'enfant"*, deja applique a `-13` (`J1`).

**Et "je le jugerai en jouant" etait un mauvais renvoi.** La propriete se decide sur le fichier, et jouer ne la fera pas disparaitre.
Renvoyer au jeu ce que le fichier montre deja, c'est laisser `marc-dev` brancher un defaut que j'ai lu. Je tranche ici.

> **D1 - point 6.1 (1) : correction avant le branchement.** Propriete, la forme de `J1` (propriete 2 de -13) : **sur aucun item du
> paquet, la bonne reponse n'est la seule des trois propositions a commencer par une majuscule.** Cible : **0** sur 20 items
> (aujourd'hui 2). Le test du paquet la porte, avec une majuscule Unicode (la commande `awk` de `S2` ne lit que `[A-Z]` : elle suffit
> aux deux items d'aujourd'hui, pas a un prenom comme *Élise*).
>
> **D2 - point 6.1 (2) : correction avant le branchement.** L'indice de Sara (*"Pense à ce qu'on fait avant de couper un fruit."*)
> fait deduire du monde au lieu de relire (`RCT-CE1-1`, *"aucune inference"* ; `TRV-CE1-5`, *"dit ou regarder"*), et ce qu'il fait
> deduire est faux en general : on ne pele pas toujours un fruit avant de le couper (`TRV-CE1-5`, *"n'enseigne jamais une regle
> fausse"*). Cible : **0** sur la mesure de `S3` (aujourd'hui 1).

**Forme qui tient, verifiee contre `RCT-CE1-2`, `LON-CE1-5`, `TRV-CE1-2` et `TRV-CE1-5`, a titre d'exemple** : les textes sont au
developpeur, et je les juge en jouant. Les propositions sont des copies raccourcies du texte, ce que `RCT-CE1-2` permet
(*"eventuellement raccourcie"*), sans le sujet, dans les trois propositions de l'item :

| Item | Bonne reponse | Distracteurs | Indice |
|---|---|---|---|
| Sara | `pèle une pomme` | `la coupe en morceaux`, `la mange` | `Relis le début du texte.` |
| Nour | `range ses jouets` | `se lave les mains`, `se met à table` | inchange (*"Relis la première phrase."*) |

Aucune proposition a majuscule, toutes dans le texte, six mots au plus, la bonne reponse n'est la plus longue sur aucun des deux
items. L'indice neuf commence par *"Relis"* et ne contient aucune proposition. Le texte de Sara et celui de Nour ne changent pas.

**Limite que je nomme, pas une barre.** Sur les quatre items *"en dernier"*, *"apres"*, *"a la fin"*, la majuscule marque encore le
premier evenement, qui y est un distracteur : elle en elimine un, pas la reponse. `D1` ne la vise pas. Je regarde au jeu si un enfant
s'en sert ; si oui, ce sera une reserve neuve et datee.

**Recommandation, non bloquante.** Item d'Inès : *"Elle sort dehors."* est un pleonasme que la classe corrige. *"Elle sort."* suffit
(distracteur `elle sort`). Si elle n'est pas prise, ce n'est pas une reserve.

**Pour `camille-po`** : `S2` et `S3` ont leur cible (0) ; le YAML du paquet entre dans la liste de `K2` (`S5`) ; l'amendement de
`S57-BR-FRA-CE1-04` precede le worktree du poste 7.

### 1.2 `Q-Y6` : la regle de comptage de `LON-CE1-3` et `LON-CE1-4`

**Elle existe deja, et elle est la mienne** : `ARBITRAGE-s47-longueur-du-texte-support-au-ce1.md`, section 2 - *"La convention de
comptage est celle d'`ADR-0089`"* : un mot contient au moins une lettre ou un chiffre, une forme elidee compte pour un mot, une fin de
phrase est un `.`, un `?` ou un `!` qui termine le texte ou qu'une majuscule suit. **C'est elle qui fait foi.** Le perimetre est le
champ `texteSupport` de chaque item.

**Mesure sous cette regle** : au plus **22** mots par texte (borne 30), **4** phrases (borne 4, atteinte par un texte : *"Samir et Jade
font un gâteau. [...] Nina goûte la pâte."*), **10** mots par phrase (borne 12). C'est le chiffre de `yanis-qa` : sa regle de fin de
phrase (un signe suivi d'un blanc) et la mienne (un signe suivi d'une majuscule) ne se separent sur aucun des vingt textes. Elles se
separeraient sur un texte dont une phrase s'ouvre par un chiffre ou une minuscule : le test suit la mienne.

**Propriete de la majuscule** : celle de `D1` (1.1). Le module du test (`adapter-content`, `K4`) est deja ecrit par `camille-po`.

### 1.3 `Q-Y2` : mon exemple de `VSJ-CE1-R1`

**Une illustration, et elle est fausse contre ma propre `J1`** : *Léa* y est la seule proposition a majuscule. Elle ne se sert pas
telle quelle. La propriete 8 est amendee, avec un exemple qui tient, verifie sur le test du paquet (section 3). Les trois tests que
`yanis-qa` nomme : `J1` reste exigee (le texte du developpeur doit la tenir, pas le test changer) ; le plancher de 5 par forme reste
exige (on transforme des items sans changer la forme de leur sujet) ; la liste des ouvertures du test recoit le mot qui ouvre le
complement, et lui seul. **Seul le test de la propriete 8 change de fond.** `marc-dev` applique l'amendement et, s'il doit toucher un
autre test que ces deux-la, il s'arrete et route (planning, `Q-Y2`).

### 1.4 `Q1` : `RDO-CP-R1` dans le meme rejeu, 125 px

> **D5 - oui, `RDO-CP-R1` se leve dans le meme rejeu.** La forme vise : a 744 x 551, apres une erreur, la boite de `.indice` finit
> au-dessus de y = 551 sur les six items (**au moins 125 px** sur le releve de s56) ; a 992 x 551, la pastille de chaque oeuvre
> essayee finit au-dessus de y = 551 (**au moins 17 px**) ; a 744 x 551, a la revelation, la boite de `.reponse-revelee` finit
> au-dessus de y = 551.

**Motif.** Un enfant de CP qui se trompe a 744 x 551 voit une croix et rien d'autre : l'indice, la seule aide qu'on lui donne, est
sous le pli. Il rejoue au hasard, se trompe une seconde fois, et arrive a la revelation par le chemin que l'indice devait lui eviter.
Le refus porte sur la fin de ce chemin ; `RDO-CP-R1` en porte le milieu. Corriger l'un sans l'autre, c'est payer une seconde forme
pour la meme cause.

**Ce que je ne deplace pas.** **Le refus se leve sur son seul critere** (fiche, *"Ce qui leve le refus"*, 105 px). Si le rejeu montre
la phrase de `M4` au-dessus du pli et l'indice encore dessous, le refus est leve et `RDO-CP-R1` reste une reserve non bloquante. 125
est la cible de la forme, pas une barre relevee du verdict.

**Ce qui est a proposer par `ines-design`, et que je ne decide pas** : d'ou viennent les pixels (habillage, case, espacements du bloc
de message), leur forme, leur couleur, dans les conditions `C1` a `C4` de `theo-archi`. **Ce que je jugerai sur sa proposition**, puis
au rejeu sur le jar :

1. `M1` : la reference et les trois propositions sur le meme ecran, sans defilement, avant tout geste, a la meme taille rendue ;
   `M2` ; le message reste sous le meuble, jamais au-dessus (`M1`, `M4`).
2. `N1` a `N6` : la croix reste un signe de 40 px sous la case, jamais sur l'image.
3. **Regle 6 (`Q8`) : la propriete se voit a la taille rendue.** La femme au luth tient *"de justesse"* a 117 et 154 px (fiche,
   section 4). Une forme qui prend ses pixels sur la taille des reproductions peut me faire refuser cet item. **Je conseille de les
   prendre ailleurs** ; ce n'est pas une interdiction de forme, c'est ce que je regarderai d'abord.
4. Ma condition `C4` de s55 : le meuble est identique apres une bonne et une mauvaise reponse. C'est aussi `C1` de `theo-archi`.

Les cotes ne se jugent que sur le jar (`C4` de `theo-archi` ; `squad/lessons.md`, s56) : je juge la proposition sur ces quatre
points, jamais sur une cote de vehicule.

### 1.5 `Q2` : la voie de `RDO-CP-R2`

> **D6 - voie (b) : sur l'ecran de fin d'un jeu dont la bonne reponse est une reproduction, ni la question rappelee ni l'explication
> ne sont posees**, que la derniere manche soit juste ou revelee. Le predicat est celui que le controleur emploie deja pour
> `reponseAttendueVisible` (`PartieController.retourModele`).

**Pourquoi pas (a), les textes.** *"celle-ci"* et *"Dans ces deux œuvres"* sont justes la ou l'enfant les lit d'abord : l'enonce
pendant la manche, la reference a l'ecran ; l'explication a la revelation, les deux oeuvres a l'ecran (`M4`, `M6`). Aucun mot ne peut
designer une oeuvre a la manche et n'en designer aucune a la fin : une explication de rapprochement parle de deux oeuvres. Reecrire
abimerait l'ecran qui fonctionne pour reparer celui qui ne montre rien.

**Pourquoi pas (c), l'oeuvre a l'ecran de fin.** `ADR-0104` prevoit **une** image de plus. Une seule oeuvre a l'ecran ferait designer a
*"celle-ci"* la bonne reponse au lieu de la reference : un texte qui designe la mauvaise oeuvre est pire qu'un texte qui n'en designe
aucune. Deux oeuvres, ce serait refaire la revelation sur un ecran dont la seule action est d'en sortir, contre le motif de ma `X7` :
*"le moment ou l'enfant apprend est `M4`, ou les deux sont visibles"*. `X7` ne change pas.

**Ce que (b) retire, mesure.** Derniere manche revelee : rien que l'enfant n'ait deja lu, la question et l'explication etant a l'ecran
de la revelation, les deux oeuvres visibles. Derniere manche juste : l'explication de cet item. Sur un jeu a reponses en image, elle
n'apparait jamais sur une manche juste (`M6`), et sans les deux oeuvres elle ne designe rien. Le principe de S5-9 (*"l'enfant qui a
trouve [...] par elimination a autant besoin de l'explication"*) suppose une explication lisible seule ; ici elle ne l'est pas.

**Ce qui reste a l'ecran** : *"Les jeux"*, les deux lignes de score, le verdict (*"Bravo, c'est juste !"* ou *"Tu n'as pas
trouvé."*), *"C'est fini !"*. **Aucun mot neuf.** Le contenu du paquet ne change pas : `grep -c 'celle-ci'` y rend 6 et doit rester a
6, ce n'est pas un defaut.

**Amendements** : `ADR-0104` decision 4 point 5 et `ADR-0084` (`REV-3`) pour cet ecran, de la main de `theo-archi`, dans le commit du
code (avis, 2.4). **Pour `ines-design`** : rien a proposer sur `Q2`, sauf si l'ecran reduit pose une question de mise en page qu'elle
voit.

### 1.6 `Q-Y5` : ce qui ferme `RDO-CP-R2`

**Aucune liste de mots interdits ne la ferme** : *celle-ci*, *celle-là*, *cette œuvre*, *ces œuvres*, *les deux*, *l'autre*, *la
même*... la langue en fournira toujours un de plus. Avec la voie (b), la propriete se ferme par la liste inverse, qui est finie et que
le gabarit porte deja :

> **Sur l'ecran de fin d'un jeu dont la bonne reponse est une reproduction, juste ou revele, tout texte rendu par le fragment `retour`
> appartient a cette liste** : `Les jeux` ; `Tu as trouvé, tout seul :` ; `Tu as joué toutes les questions :` ; les deux nombres du
> score ; `Bravo, c'est juste !` ; `Tu n'as pas trouvé.` ; `C'est fini !`. **Aucun texte de l'item** (enonce, explication, indice) n'y
> figure.

Decidable par un test de vue (selecteurs `jsoup`, comme `PartieControllerPropositionsEnImageTest`) ; la forme est a `yanis-qa` et au
developpeur. Mutation qui doit le faire rougir : remettre la question rappelee ou l'explication sur cet ecran. **Je reste seule
garde** de ce que ces textes disent a un enfant de CP ; le test garde qu'il n'y en a pas d'autres.

### 1.7 `Q-B2` : l'indice cite en 6.3

**Non, et `camille-po` a raison.** *"Un mot de la même famille garde un morceau du mot."* est l'indice de cinq items du paquet, pas
celui de l'item *livre*, qui porte *"Cherche le mot qui parle de la même chose."* (ligne 84). J'ai ecrit *"l'indice du paquet"* en
pensant a la methode que ces cinq items enseignent ; la phrase laissait lire l'indice de l'item. **Correction de mon constat.**

**Le motif de fond tient, et l'indice de l'item l'aggrave.** *Livre* et *librairie* sont une famille savante (`FAM-CE1-2`). L'enfant
qui applique la methode des cinq autres items choisit *livrer*. Celui qui lit l'indice de l'item cherche *"le mot qui parle de la même
chose"* : c'est le critere du distracteur (b) de `FAM-CE1-2` (*"un mot du meme theme sans le radical"*), que *cahier* remplit aussi.
L'indice fait chercher par le theme dans une ligne qui apprend le radical. **A corriger avant le branchement du palier 2**
(`S57-FRA-FAMILLES-LIVRE`, inchangee). L'indice *"Cherche le mot qui parle de la même chose."* est sur cinq items : je les relirai tous
les cinq a l'arbitrage du palier 2, sans le trancher ici.

---

## 2. Lecture des items des trois lots (audit degrade, rien n'est joue)

### 2.1 `ce1-texte-narratif` (A, `FRA-CE1-04`), 20 items

| Condition | Constat a la lecture |
|---|---|
| `RCT-CE1-1` | personnage 4, lieu 4, moment 5, ordre 7 : ordre au moins un quart, moment au moins un cinquieme, personnage et lieu 8 sur 20. **Tenu.** Chaque reponse est ecrite dans le texte, sauf l'indice de Sara (`D2`) |
| `RCT-CE1-2` | 0 proposition hors du texte. **Tenu** |
| `RCT-CE1-3` | trois personnages nommes au plus ; trois evenements au moins sur les sept textes d'ordre ; aucun passe simple. **Tenu** |
| `LON-CE1-3`, `-4` | 22 mots, 4 phrases, 10 mots par phrase au plus (1.2). **Tenu, sans garde-fou** |
| `LON-CE1-5` | six mots au plus (*"il s'assoit près de la fenêtre"* : 6). **Tenu** |
| `TRV-CE1-2` | bonne reponse strictement la plus longue sur 5 items sur 20. **Tenu** |
| `TRV-CE1-5` | 18 indices distincts, aucun de plus de douze mots ; un renvoie au monde (`D2`) |
| `TRV-CE1-6` | 17 prenoms masculins, 17 feminins, origines variees ; ranger et cuisiner des deux cotes ; Aya joue au foot. **Tenu** |
| Majuscule | 2 items (`D1`) |
| Exactitude | les 20 reponses sont justes a la lecture ; *"Elle sort dehors."* (recommandation, 1.1) |

**Frontiere avec `-05`, a surveiller au jeu** : *"Ce soir, il la partage avec Élise."* fait resoudre *la* (la compote). La question ne
cite pas le pronom, donc l'item reste une question de moment (`PRN-CE1-1`) ; l'indice *"Cherche le mot « partage »"* y mene sans
resoudre la reprise.

### 2.2 `ce1-phrases-types` (A', `FRA-CE1-10`), 24 items

**Rien a signaler. Mon 6.2 tient.** Recompte sur les champs : reconnaitre 9 (au moins un tiers), type 15 (au moins la moitie), 5 par
type (au moins un sixieme) : `TPH-CE1-1` tenue. `TPH-CE1-2` : un seul groupe a majuscule et point, distracteurs (a) et (b), memes mots,
aucun desordre. `TPH-CE1-3` : aucune imperative, aucun signe combine ; *"Quelle heure est-il ?"* et *"Quelle belle journée !"*
s'ouvrent pareil, l'enfant doit lire le signe final : c'est la ligne. Prenoms 3 et 3. Exactitude : 24 sur 24.

**Observation, sans reserve** : sur les quatre items de reconnaissance dont la phrase commence par un prenom, le distracteur (a)
(*"inès range ses crayons."*) manque deux majuscules, celle de la phrase et celle du nom propre. La reponse reste unique et la faute
montree reste une faute de majuscule ; je regarde au jeu si l'indice *"Regarde comment commence [...]"* suffit.

### 2.3 `ce1-accord-groupe-nominal` (A', `FRA-CE1-15`), 24 items

**Rien a signaler. Mon 6.2 tient.** Recompte sur les champs : adjectif 21 sur 24, -x 12, feminin 6, -s 6 : `GNA-CE1-1` tenue.
`GNA-CE1-2`, groupe par groupe : les onze noms des douze items en -x sont en -eau ou -eu (*chapeaux, gâteaux, bateaux, cheveux, jeux, manteaux, oiseaux,
tableaux, cadeaux, drapeaux, feux*, et l'adjectif *beaux*), aucune exception, ni -al ni -ou ; les six feminins ajoutent un -e sans
autre changement (*petite, grande, chaude, ronde, noire, verte*), aucun adjectif deja en -e au feminin (*rouges* est un pluriel). Une
seule reponse juste par item, les deux distracteurs sont fautifs. `GNA-CE1-3` : (a), (b), (c) presents. Bonne reponse la plus longue
sur 10 items sur 24.

**Ce que je reste seule a garder** : l'exactitude de chaque marque (`GNA-CE1-2`), ici lue, a rejuger a l'ecran.

---

## 3. La propriete 8 de `-13`

**Elle n'etait pas amendee** (section 0). `marc-dev` l'attend (planning, porte P-3). **Amendee dans ce commit** :
`squad/conformite/ARBITRAGE-PREALABLE-s56-instance-a-francais-ce1.md`, section 2.8, bloc *"Amendement du 2026-10-14"*, avec une note
datee dans la fiche de `-13`. En une phrase : sur chaque item de sujet, au moins un distracteur apres le verbe et au plus un avant ;
sur au moins trois items, un distracteur avant, qui est un complement de temps ou de lieu en tete suivi d'une virgule, avec un indice
qui n'est pas *"regarde les mots devant"*. Le critere de levee de `VSJ-CE1-R1` ne change pas.

---

## 4. Engagement des postes 7 et 8

### 4.1 Poste 7, `ce1-texte-narratif` : **engageable**

Ce qui l'arretait, `Q-B1` et `Q-Y6`, est repondu (1.1, 1.2). **Rien ne manque de ma part.** Manque, avant le worktree (porte P-5) :

1. l'amendement de `S57-BR-FRA-CE1-04` par `camille-po` : cibles de `S2` et `S3` a 0, `S2` sur les 20 items et en majuscule Unicode,
   le YAML dans la liste de `K2` (`S5`), la regle de comptage de `S4` nommee (s47, section 2) ;
2. l'amendement date de `planning.md` par `sofia-sm` qui engage le poste.

Prix inchange : 1,0 (lot). La recommandation d'Inès ne bloque rien.

### 4.2 Poste 8, correction d'`ART-CP-10` : **engageable des que la forme est proposee**

Ce qui l'arretait de ma part, `Q1`, `Q2` et `Q-Y5`, est repondu (1.4 a 1.6). Manque (porte P-6) :

1. **la proposition de forme d'`ines-design`** pour les pixels de `Q1` ; je la juge sur les quatre points de 1.4, **sans cote** ;
   `Q2` ne lui demande rien ;
2. l'amendement de `S56-RDO-CP-R1` et `S56-RDO-CP-R2` par `camille-po` : *"meme rejeu"* confirme (`D5`), la voie (b) (`D6`) et la
   liste fermee de `D7` comme critere de `RDO-CP-R2` ;
3. la seconde moitie de `Q-Y4` (juge pur sur le releve), a `theo-archi` et `yanis-qa`, qui ne me revient pas.

`C1` a `C4` vont au rapport du developpeur, les amendements d'`ADR-0104` et d'`ADR-0084` au commit du code. Le poste 9 (B2) attend
toujours `Q3` (`camille-po`). Prix du rejeu inchange : 0,5, marge de B intacte.

---

## 5. Ce dont je reste la seule garde, ajoute par cette lecture

| Propriete | Pourquoi aucun test ne la tient |
|---|---|
| Qu'un complement en tete de `-13` soit de temps ou de lieu, et qu'aucun distracteur ne fasse l'action | le test voit la virgule et le rang, pas le sens |
| Que les copies raccourcies de Sara et de Nour se lisent comme des actions pour un enfant de CE1 (*"la coupe en morceaux"*) | lisibilite |
| Que la majuscule ne serve pas sur les items *"en dernier"* (1.1, limite) | conduite d'enfant, au jeu |
| Ce que les textes de l'ecran de fin d'un jeu a images disent a un enfant de CP (`D7`) | le test garde la liste, pas le sens |
| Que la forme de `Q1` laisse la propriete visible a la taille rendue (regle 6) | aucun automate ne regarde une image |
| `LON-CE1-3`, `LON-CE1-4`, l'exactitude des marques d'accord | inchange (`arbitrage-nadia-palier-1.md`, section 7), jusqu'aux tests de paquet |

## 6. Routage

| A qui | Quoi |
|---|---|
| `camille-po` | `S57-BR-FRA-CE1-04` (4.1) ; `S56-RDO-CP-R1`, `S56-RDO-CP-R2` (4.2) ; `Q-B2` repondue (1.7) |
| `sofia-sm` | amendements de `planning.md` qui engagent les postes 7 et 8 quand leurs portes sont remplies ; `Q-Y2` repondue (1.3) |
| `marc-dev` | porte P-3 remplie : l'amendement de la propriete 8 est sur la branche `s57-nadia-lecture`, a fusionner avant son commit |
| `ines-design` | la forme des pixels de `Q1`, sur les quatre points de 1.4 |
| `theo-archi` | voie (b) retenue : amendements d'`ADR-0104` et d'`ADR-0084` dans le commit du code |
| `yanis-qa` | la regle de comptage (1.2) ; la propriete de `D1` ; la liste fermee de `D7` |
| Utilisatrice | **aucune question neuve** |

## 7. Ce que ce document ne fait pas

- Il ne prononce aucun Done, ne joue aucun jeu, ne touche ni la matrice, ni le catalogue, ni un paquet, ni du code.
- Il ne rouvre ni `X7`, ni `M1` a `M6`, ni `N1` a `N6`, ni `VSJ-CE1-1` a `-3`, ni `J1` a `J5`, ni `RCT-CE1-1` a `-3`. `D1` applique a
  `-04` la forme de `J1`, decidee avant tout audit de ce paquet.
- Il n'ecrit et n'alloue **aucun identifiant de condition ou de reserve**. `D1` a `D10` sont des reperes de ce fichier.
- Il ne repond pas a `Q-S3` (prix d'`APM-R2`, instance de `RP-ACCUEIL-147`) : non route dans cette tache.

## 8. Jobs de `ci.yml` lances pour ce commit

Sur ce worktree, avec les trois fichiers du commit, avant commit. Je ne deduis aucun job que je n'ai pas lance.

- **`build`** : `bash outils/mvn-jdk21.sh -B clean verify` depuis la racine, sans `-pl` : `BUILD SUCCESS`, 2498 tests (120, 31, 486,
  1484, 371 et 6 d'integration), 0 echec, 0 erreur, 0 ignore ; JDK 21.0.10 (`java.version` des rapports de `bootstrap`). Le build a lu
  ce fichier avant l'ajout de cette section, qui ne contient aucun tableau. Etape *"Package du fat jar"* non lancee. En plus,
  `bash outils/mvn-jdk21.sh -pl bootstrap -am test -Dtest=ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest` : 10 tests, 0 echec,
  `BUILD SUCCESS` (aucun identifiant neuf).
- **`chemins`** : `verifier-chemins-test.sh` sort 0 ; la liste des fichiers suivis et non suivis passe a `verifier-chemins.sh` : sort 0.
- **`prefixes`** : `verifier-prefixes-test.sh`, `commit-atteignable-test.sh` et `verifier-garde-fou-accent-test.sh` (21 cas) sortent
  0 ; le sujet du commit passe dans `verifier-prefixes.sh` : sort 0.
- **`outils`** : non lance ; aucun outil n'est modifie.

Aucun de ces resultats n'est celui de la CI distante, que je n'ai pas lue.

**Signe** : `nadia-prof`, s57, palier 1, instances A, A' et B, 2026-10-14.
