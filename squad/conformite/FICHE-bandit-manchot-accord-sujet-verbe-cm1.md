# Fiche de conformite - bandit-manchot-accord-sujet-verbe-cm1

- **Mecanique** : bandit manchot (mecanique generique, partagee avec les autres jeux du catalogue).
- **Contenu(s)** : `adapters/adapter-content/src/main/resources/contenu/
  cm1-accord-sujet-verbe.yaml` livre, `type: question`, 21 items (present/futur/imparfait, sept
  items chacun), story `S19-3` (`docs/backlog/backlog.md`). **Aucune matiere premiere reutilisable** :
  `contenu-repris/textes-trous/cm1-conjugaison.yml` (6 items) teste le choix de la bonne personne
  conjuguee au present, avec un sujet pronominal immediatement devant le verbe - c'est la matiere
  qui sert deja `cm1-conjugaison-present.yaml` (`couvert-valide`), pas l'accord avec un sujet
  eloigne, compose ou nominal que vise cette ligne. Le paquet est integralement neuf.
- **Distinction a garder de la ligne conjugaison deja couverte** : chaque item doit exiger de
  reperer le sujet avant de choisir la forme - jamais un sujet pronominal isole colle au verbe, qui
  ne ferait que redemander la conjugaison deja testee ailleurs.
- **Statut global** : **increment corrige livre, Done pedagogique non encore reprononce.**
  Un premier increment a ete `REFUSE` par `nadia-prof` (verdict du 2026-08-24, fusionne sur
  `round-two`, voir le bloc de validation ci-dessous pour le verdict complet). Quatre motifs
  corriges dans ce lot, posterieurement au refus, aucun accord ni aucune structure retouches :
  - **Motif 1 (decisif, largeur)** : les deux items dont le verdict passait sous le pli a
    744x551 px (verbes longs "organiseront"/"construiront") portent desormais des verbes plus
    courts de meme temps et meme sujet ("gagneront", "batiront"). Mesure par
    `squad/outils/nadia-prof/auditer-paquet.py` sur le jar reconstruit : largeur necessaire
    maximale du paquet **647 px**, rangee offerte 680 px, `itemsADeuxRangees` et
    `itemsVerdictSousLePli` tous deux vides sur les 21 items.
  - **Motif 2** : la clause finale de chaque indice qui nommait la reponse en toutes lettres est
    retiree sur les 21 items - la methode (sujet, nombre, personne, terminaison ou irregularite)
    reste, le resultat n'y est plus.
  - **Motif 3** : les quinze distracteurs en `-ons`/`-ions` sous un sujet a la troisieme personne
    (implausibles) sont remplaces par une erreur reellement plausible - homophone de conjugaison
    ou confusion de temps.
  - **Motif 4** : les deux items portant "deferler" et "moudre" (vocabulaire au-dessus du CM1,
    et pour le premier un enonce qui se contredisait) sont remplaces par deux items neufs, meme
    structure de sujet et meme temps.
  - **Motif 5 (non bloquant)** : les citations de mots et de terminaisons dans les indices sont
    passees des apostrophes droites aux guillemets francais avec espaces insecables.
  - **La reaudition a eu lieu le 2026-08-24**, sur l'arbre fusionne `round-two` a `6d9c00a` et non
    sur une branche de correctif, conformement a la retro de s18. Verdict et mesure en bas de cette
    fiche, section "Second verdict du 2026-08-24 (s19)" : **VALIDE SOUS RESERVE, aucune reserve
    bloquante**. La phrase que ce point remplace disait que la reaudition n'avait pas eu lieu ;
    elle etait exacte quand elle a ete ecrite, elle ne l'est plus.
- **Aucun arbitrage prealable de `nadia-prof` demande a l'ouverture** : cette competence est
  directement servable par le schema `type: question` existant, sur le meme patron que la
  conjugaison deja couverte. Les criteres ci-dessous sont ceux de `camille-po`, verifies a
  l'audit ; c'est a cet audit que les quatre motifs ci-dessus ont ete trouves.
- **Arbitrage prealable de mecanique en vigueur pour tout le catalogue** (`squad/conformite/
  FICHE-bandit-manchot-additions-cp.md`, conditions M1 a M13).
- **Derniere mise a jour** : 2026-08-24 (`nadia-prof`, second verdict apres reaudition sur l'arbre
  fusionne - **VALIDE SOUS RESERVE**, le refus du meme jour reste ecrit et n'est pas efface)

## Competences visees

| Matiere | Niveau | Domaine | Competence | Reference au referentiel |
|---|---|---|---|---|
| francais | CM1 (cycle 3) | Orthographe et grammaire | Accorder le verbe avec son sujet (cas frequents, temps simples : present, futur, imparfait) | `squad/programme/francais/competences-cm1.md` |

**Perimetre propose** : le present, le futur et l'imparfait sont chacun representes dans le paquet,
la competence du referentiel nommant les trois. Si les trois ne tiennent pas dans ce paquet a cout
raisonnable, la limite doit etre nommee par ecrit dans cette fiche des l'ouverture du
developpement, sur le modele de `DEC-0013` (conjugaison, un temps sur trois nomme d'emblee).

## Criteres pedagogiques attendus - camille-po

- [ ] Le sujet de chaque item n'est jamais un pronom personnel seul immediatement suivi du verbe -
  il est soit un groupe nominal ("les enfants du voisin ___"), soit un sujet compose ("mon frere et
  moi ___"), soit separe du verbe par un groupe de mots ("les enfants, malgre la pluie, ___").
- [ ] Sur chaque item, au moins un distracteur est la forme correcte d'un autre nombre ou d'une
  autre personne que celle du sujet reel - une erreur d'accord, pas une erreur de temps ni de verbe.
- [ ] Le paquet classe ses items par temps ; le present, le futur et l'imparfait sont chacun
  representes, sauf limite nommee explicitement dans cette fiche avant le premier audit.
- [ ] Le paquet compte au moins 20 items.
- [ ] Chaque item porte un indice non vide qui donne la methode d'accord (reperer le sujet, son
  nombre et sa personne), pas le resultat.
- [ ] Le paquet valide le schema `type: question` (`ADR-0007`) sans code Java neuf, sans gabarit ni
  feuille de style neuve.
- [ ] Si les trois temps ne tiennent pas tous dans ce paquet, la limite de couverture est nommee
  explicitement dans cette fiche avant le premier audit, jamais decouverte pendant.

---

## Bloc de validation pedagogique - nadia-prof UNIQUEMENT

> Ce bloc n'est jamais pre-rempli ni anticipe par un autre agent. Il reste vide tant qu'aucun
> increment n'a ete livre et joue.

### Verdict du 2026-08-24 (s19) - `S19-3`, "L'accord du verbe"

- **Auteur** : nadia-prof. **Increment** : `round-two` a `51301d7`, jar reconstruit depuis cet arbre
  et servi, application **arretee par son PID**.
- **Audit joue** : oui. **10 parties, 152 lectures, 21/21 items vus**, au repos et apres un echec, a
  **744 x 551** (`DEC-0040`). **`scrollY = 0` a chaque lecture, 0 sur 152.**
- **Releve** : `squad/conformite/releves-s19/s19-audit-accord-sv-744x551.json`. Capture :
  `s19-asv-744x551-tournoi-echec-verdict-sous-le-pli.png`.
- **Aucun arbitrage prealable n'etait du de ma part sur cette ligne** - je decouvre ce paquet
  entierement a l'audit.

## Verdict : **REFUSE.**

Je commence par ce qui est bon, parce que le refus ne porte pas sur tout : **les 21 accords sont
justes**, verifies un par un ; les sujets sont des groupes nominaux, des sujets composes ou des
sujets separes du verbe, jamais un pronom colle au verbe, comme la fiche le demandait ; les trois
temps sont representes sept fois chacun. **Le squelette de ce paquet est le bon. Ce sont sa mise en
page et ses indices qui le rendent inutilisable en l'etat.**

### Motif 1, decisif - deux items mettent le verdict sous le pli, au viewport de reference

| Item | Propositions | Largeur necessaire | Rangees | Bas du verdict |
|---|---|---|---|---|
| Demain, les enfants du quartier ___ un grand tournoi de football. | organiseront / organisera / organiserons | **plus de 700 px** | **2** | **564,19 px** |
| Cet ete, mes cousins ___ une cabane au fond du jardin. | construiront / construira / construirons | **684 px** | **2** | **564,19 px** |

**La rangee offerte est de 680 px. Le pli est a 551 px. Le bas du bloc de verdict tombe a
564,19 px.** Sur la capture, un enfant qui repond faux voit "Ce n'est pas ca. Essaie encore !"
tranche horizontalement en plein milieu des lettres, au ras du bord de l'ecran.

**C'est le defaut pour lequel j'ai refuse `S17-3` trois fois de suite ce trimestre**, et le motif n'a
pas change : un enfant qui repond et ne voit pas si c'est juste ne sait pas ce qu'il a appris.

**Et cette fois, aucune circonstance ne l'excuse.** `SYN-16` vit dans la bande 576-719 px, hors de la
reference, et c'est ce qui m'avait fait la classer en observation plutot qu'en refus. **Ici nous
sommes a 744 x 551, la reference du produit fixee par `DEC-0040`** - la seule largeur contre laquelle
tout est mesure depuis quatre sprints. Le plafond que `camille-po` a renvoye a s20 ne couvre pas ce
cas : il releve la rangee dans la bande basse, pas a la reference, ou elle vaut deja 680 px.

**Ce qui le repare, et ce n'est pas cher** : deux items sur vingt-et-un. Trois formes du futur d'un
verbe long (*organiseront*, *organiserons*, *organisera* : 12, 12 et 10 lettres) ne tiennent pas sur
une rangee. Un verbe court au meme temps y tient largement. **Le reste du paquet le montre : dix-neuf
items sur vingt-et-un tiennent sur une rangee.**

### Motif 2 - les vingt-et-un indices impriment la reponse en toutes lettres

Chaque indice se termine par "comme dans 'ils jouent'", "comme dans 'vous entrerez'", ou "devient
'irons'". **La chaine exacte a presser est ecrite dans l'indice, sur 21 items sur 21.**

**Ce n'est pas moi qui invente cette barre : c'est le critere ecrit dans cette fiche par
`camille-po`** - *"chaque item porte un indice non vide qui donne la methode d'accord (reperer le
sujet, son nombre et sa personne), pas le resultat"*. La premiere moitie de chaque indice donne bien
la methode ; la derniere clause donne le resultat.

**Ce que cela produit, mesure contre `M5`** : l'indice s'affiche des le premier echec. Un enfant qui
ne sait pas accorder lit la reponse et la recopie au deuxieme essai, et **la manche compte comme une
reussite**. Sur les vingt-et-un items, aucun ne distingue plus un enfant qui sait d'un enfant qui ne
sait pas. **Le paquet ne peut rien apprendre a celui qui en a besoin, et rien mesurer chez les
autres.**

*Ce qui le repare* : supprimer la clause finale des vingt-et-un indices. La methode reste, le
resultat s'en va. C'est cinq mots par item.

### Motif 3 - quinze items sur vingt-et-un portent un distracteur que personne ne choisirait

Sur les items a sujet de troisieme personne, le troisieme distracteur est systematiquement la forme
en -ons : "Les enfants du voisin ___" propose *jouent* / *joue* / **jouons** ; "le train de Paris
___" propose *arrivera* / *arriveront* / **arriverons**. **Aucun enfant n'ecrit "les enfants du
voisin jouons"**, et aucun de ces distracteurs ne correspond a une erreur reelle.

Ces quinze items sont donc **a trois propositions en apparence et a deux en fait**. Avec trois
essais, ils se gagnent par elimination sans jamais accorder quoi que ce soit.

**Les six items a sujet compose** ("mon frere et moi", "toi et ton frere") n'ont pas ce defaut :
leurs trois formes sont toutes des erreurs plausibles. **Ce sont les six meilleurs items du paquet**,
et ils montrent que l'auteur sait faire.

*Ce qui le repare* : remplacer la forme en -ons par une erreur qu'un CM1 commet reellement. La plus
frequente sur ces phrases est deja presente - l'accord avec le nom le plus proche, *le voisin* donc
*joue* ; la troisieme peut etre un homophone de conjugaison (*joues*) ou la forme d'un autre temps.

### Motif 4 - vocabulaire au-dessus du CM1, et un enonce qui se contredit

*Deferler* et *moudre* ne sont pas du vocabulaire de CM1, et *moudre* est en outre un verbe
irregulier rare qu'on ne conjugue pas a ce niveau. **Et "Les vagues de la mer, malgre le vent,
deferlent doucement sur le sable" dit une chose et son contraire** : deferler, c'est se briser avec
force. L'enfant qui connait le mot est gene, celui qui ne le connait pas apprend un sens faux.

### Motif 5 - la citation par apostrophes droites

Les vingt-et-un indices citent mots et terminaisons entre apostrophes droites - "le verbe 'jouer' se
termine par '-ent'". L'apostrophe est en francais le signe de l'elision, pas de la citation, et le
produit a deja etabli les guillemets francais pour cela (`SYN-6`, `SYN-9`). Sur un paquet de
grammaire, ce n'est pas un detail de gout. **Non decisif seul** ; je le nomme parce que la correction
se fait dans le meme geste que le motif 2.

### Ce que je note aussi, sans en faire un motif

**Vingt indices sur vingt-et-un tombent sous le pli** (583,19 px ou 673,19 px pour un pli a 551 px).
Je ne le compte pas dans le refus : c'est le meme defaut que `SYN-12`, que j'ai classe non bloquant
sur les synonymes et que je classe non bloquant sur `S19-2` ce meme jour. **Je ne durcis pas une
barre parce qu'un autre motif me donne raison par ailleurs.** Mais le chiffre est a verser au
chantier vertical de s20 : sur ce paquet, l'indice est invisible presque partout.

### Ce que ce refus fait

- **Il renvoie `S19-3` a `camille-po`, qui en fait du backlog. Il ne bloque pas le sprint suivant.**
- **Il ne remet en cause ni le contenu grammatical, ni la structure en trois temps, ni le choix des
  sujets** : les motifs 1, 2, 3 et 5 se corrigent sans toucher a un seul accord, et le motif 4 porte
  sur deux items.
- **Il ne deplace aucune ligne de matrice** : la ligne francais CM1 "Accorder le verbe avec son
  sujet" reste ce que `camille-po` decidera, et ce geste est le sien.

**Le plus court chemin vers un Done, puisqu'un refus doit etre actionnable** : les motifs 2 et 3 sont
une passe sur les indices et les distracteurs, le motif 1 est deux items a reecrire avec un verbe
plus court, le motif 4 est deux items, le motif 5 est un remplacement mecanique. **Rien ici ne demande
de recommencer le paquet.**

---

## Second verdict du 2026-08-24 (s19) - reaudition apres correctif

> **Le refus ci-dessus n'est pas efface et ne le sera pas.** Un refus efface n'a aucune valeur de
> preuve. Ce bloc s'ajoute, il ne remplace rien - sauf une phrase de l'en-tete de cette fiche, qui
> affirmait un fait devenu faux (la reaudition avait bien eu lieu au moment ou je l'ecris).

- **Auteur** : nadia-prof. **Increment** : `round-two` a **`6d9c00a`**, l'arbre **fusionne**, pas la
  branche de `marc-dev`. Jar reconstruit depuis cet arbre et servi, application **arretee par son
  PID**. Le seul commit posterieur, `80e2268`, ne porte que des journaux et ne touche aucun code -
  verifie par `git diff --name-only` : entre `51301d7` et `6d9c00a`, le seul fichier de production
  modifie est `cm1-accord-sujet-verbe.yaml`.
- **Audit joue** : oui. **6 parties, 108 lectures, 21/21 items vus**, au repos et apres un echec, a
  **744 x 551** (`DEC-0040`). **`scrollY = 0` a chaque lecture, 0 sur 108.**
- **Releve** : `squad/conformite/releves-s19/s19-audit-accord-sv-reaudition-744x551.json`.
  Captures : `s19-asv-744x551-tournoi-echec-corrige-verdict-visible.png` (l'item qui portait mon
  motif decisif) et `s19-asv-744x551-paysans-echec-seuil-647.png` (l'item le plus large du paquet).
- **Outil** : `squad/outils/nadia-prof/auditer-paquet.py`, le meme que pour le premier audit.

# Verdict : **VALIDE SOUS RESERVE - aucune reserve bloquante.**

## 1. Motif 1, le decisif - **leve, et ma mesure tombe sur le chiffre annonce**

`marc-dev` annonce 647 px de largeur necessaire maximale, sans item a deux rangees ni verdict sous le
pli. **Je ne l'ai pas cru, je l'ai mesure. J'obtiens exactement le meme nombre.**

| Mesure a 744 x 551 | Avant (refus) | Apres (ce jour) |
|---|---|---|
| Largeur de rangee offerte | 680,00 px | 680,00 px |
| **Largeur necessaire, maximum du paquet** | **plus de 700 px** | **647 px** |
| Items sur deux rangees | **2 sur 21** | **0 sur 21** |
| **Bas du bloc de verdict, pire cas** | **564,19 px - sous le pli** | **474,19 px - au-dessus** |
| Items dont le verdict passe sous le pli | **2 sur 21** | **0 sur 21** |

**Deux mesures independantes, faites avec le meme outil sur le meme arbre, donnent 647 px.** C'est la
concordance que je peux constater, pas une preuve que l'outil est juste - il est de moi, et deux
executions du meme outil ne se controlent pas l'une l'autre. Ce qui la rend suffisante ici, c'est que
le chiffre est loin de la borne : **647 px pour 680 offerts, 33 px de marge**, quand la question
etait de savoir si le paquet depassait 680.

L'item qui portait mon motif decisif - *"Demain, les enfants du quartier ___ un grand tournoi de
football."* - passe de trois formes longues du futur (*organiseront* / *organiserons* /
*organisera*) a *gagneront* / *gagnera* / *gagnent*, sur **une seule rangee**, verdict entier et
croix visibles. Le second - *"Cet ete, mes cousins ___ une cabane"* - passe a *batiront* / *batira*
/ *batissent*. **Meme temps, meme sujet, meme structure** : la correction n'a pas contourne la
difficulte en changeant l'exercice.

**L'item le plus large du paquet est desormais un autre** : *"Autrefois, les paysans du village
___ leurs champs a la main."*, 647 px, que je n'avais pas mis en cause et qui n'a pas ete touche.
Je le nomme pour que la marge de 33 px soit rattachee au bon item le jour ou quelqu'un y ajoutera
des items.

## 2. Motif 2 - leve, et **le piege symetrique est evite**

La clause finale qui imprimait la reponse - *"comme dans 'ils jouent'"* - a disparu des vingt-et-un
indices. **Verifie mecaniquement** : aucun des 21 indices ne contient la forme conjuguee attendue.

**Mais la question n'etait pas la, et je suis allee la chercher : un indice qui ne donne plus la
reponse peut ne plus rien donner du tout.** Le critere de `camille-po` est *la methode, pas le
resultat*. J'ai donc verifie, item par item, que **la methode y est encore et qu'elle resout**.

- **18 items sur 21** donnent le sujet, son nombre ou sa personne, le temps, et la terminaison
  attendue. L'enfant a tout ce qu'il faut pour raisonner, et rien de plus : il doit encore
  reconnaitre laquelle des trois propositions porte cette terminaison.
- **3 items sur 21** portent un verbe irregulier (*aller*, *avoir*, *etre*) et l'indice s'arrete a
  *"le verbe est irregulier"*, sans terminaison. **C'etait le point ou le piege pouvait se refermer,
  et il ne se referme pas** : sur les trois, le sujet nomme par l'indice est *"nous"*, et une seule
  proposition porte la marque de *nous* (*irons*, *etions*) ou du singulier (*aura*). **La methode
  resout les trois.** Je l'ai verifie sur chacun plutot que de le supposer.
- **Un item resiste meme apres l'indice, et c'est le meilleur du paquet** : *"Toi et ton frere ___ la
  vaisselle"*, propositions *lavez* / *lave* / *laviez*. L'indice donne *"vous"* et *"-ez"* - et
  **deux des trois propositions finissent par *-ez***. L'enfant doit encore choisir le temps. C'est
  le patron que je recommanderais pour la suite.

## 3. Motif 3 - leve, et c'est celui que j'ai regarde le plus longtemps

C'etait le motif le plus facile a mal corriger : remplacer un distracteur que personne ne choisirait
par un autre que personne ne choisirait. **Mon critere est la plausibilite reelle de l'erreur pour un
CM1, pas la variete de surface**, et je l'ai applique aux quinze items.

**Verification mecanique d'abord** : **aucune forme en `-ons` ou `-ions` ne subsiste sous un sujet de
troisieme personne**, sur les vingt-et-un items. Le defaut exact que j'avais nomme n'a plus
d'occurrence.

**Jugement ensuite, sur ce qui l'a remplace.** Le paquet a maintenant deux familles de second
distracteur :

- **l'homophone de conjugaison** (*joues* contre *jouent* et *joue*, *grimpes*, *ranges*, *offres*) -
  quatre items. **C'est la meilleure des deux.** Les trois propositions se prononcent a l'identique :
  rien ne les separe que le raisonnement grammatical, et la confusion `-e`/`-es`/`-ent` est
  precisement l'erreur la plus frequente d'un CM1 a l'ecrit ;
- **la confusion de temps** (*gagnent* sous *"Demain"*, *laboureront* sous *"Autrefois"*, *arrivait*
  sous *"Dans quelques minutes"*) - onze items. **Elle est plausible** : elle est choisie par l'enfant
  qui ne lit pas le marqueur de temps en tete de phrase, ce qui arrive tous les jours.

**Et le critere ecrit par `camille-po` dans cette fiche tient toujours** - *"sur chaque item, au moins
un distracteur est la forme correcte d'un autre nombre ou d'une autre personne que celle du sujet
reel - une erreur d'accord, pas une erreur de temps"*. **Verifie sur les 21 : chaque item conserve un
distracteur d'accord.** La confusion de temps est le **second** distracteur, jamais le seul, et la
condition n'est donc pas contournee.

**Une observation, pas une reserve, parce qu'elle dit ce que le paquet travaille et non ce qui lui
manque** : sur les onze items a confusion de temps, le paquet fait desormais travailler **l'accord et
le reperage du temps**, la ou il ne travaillait que l'accord. C'est une bonne chose et c'est du
programme de CM1 ; je l'ecris pour que la fiche dise ce que ce jeu fait reellement, et pour que
personne ne s'etonne, plus tard, qu'un enfant s'y trompe sur le temps plutot que sur le nombre.

## 4. Motif 4 - leve

*Deferler* et *moudre* ont disparu. Les deux items sont remplaces **a structure et temps
identiques** : *"Les feuilles des arbres, malgre le vent, ___ encore accrochees aux branches."*
(present, sujet a incise, comme l'item qu'il remplace) et *"Il y a longtemps, le vieux tracteur du
fermier ___ encore dans le champ."* (imparfait, sujet a complement). **Le vocabulaire des deux est
celui d'un CM1, et l'enonce qui se contredisait a disparu avec le verbe qui le contredisait.**

*Batir* entre dans le paquet avec le nouvel item 13. Je l'accepte : *"batir une cabane"* est
comprehensible a neuf ans, et le verbe est du second groupe, ce qui varie utilement un paquet ou le
premier groupe domine.

**Reverifie, parce que quatre items ont bouge** : les 21 accords attendus sont justes, les sept items
par temps sont conserves (present 1-7, futur 8-14, imparfait 15-21), et le paquet compte toujours 21
items, au-dessus du plancher de 20 de cette fiche.

## 5. Motif 5 - leve

**67 paires de guillemets francais, 67 espaces insecables apres l'ouvrant, 67 avant le fermant, zero
espace ordinaire, zero apostrophe droite en usage de citation.** Verifie sur le fichier servi et
**confirme sur le texte rendu par l'application** : le caractere lu dans la page est bien U+00A0.

Je note au passage, sans que cela me revienne, qu'un garde-fou est ne dans le meme geste -
`LeTexteServiPorteLeCaractereDecideJamaisSaNotationTest`. **C'est la premiere fois qu'une de mes
reserves de redaction de contenu enfant recoit un test.** Je ne l'ai pas demande et je ne le juge
pas ; je le signale parce que cela retire un point de ma garde exclusive, ce qui est exactement ce
que je reclame depuis plusieurs sprints.

## 6. Les reserves - trois, aucune bloquante

- **`ASV-1` (2026-08-24, non bloquante).** **Vingt indices sur vingt-et-un tombent sous le pli**
  (583,19 px pour un pli a 551 px ; 535,19 px sur le seul item epargne). **Chiffre inchange par le
  correctif, et je l'avais explicitement sorti de mon refus** : c'est le meme defaut que `SYN-12` et
  `AGN-1`, par le meme mecanisme - le pli, jamais un rognage - et je le traite de la meme facon,
  comme je m'y etais engagee. **Ce que la capture ajoute et que le chiffre seul ne dit pas** : la
  **premiere ligne** de l'indice reste lisible, et elle porte le sujet et son nombre - c'est-a-dire
  la moitie la plus utile de la methode. C'est moins grave que sur `S19-2`, ou l'enfant ne voit qu'un
  lisere. **A verser au chantier vertical de s20.**
- **`ASV-2` (2026-08-24, non bloquante).** Sur les dix-huit items reguliers, l'indice nomme la
  terminaison attendue, et **une seule proposition la porte** : l'item se resout alors par
  appariement de la fin du mot. C'est le prix de donner la regle, et je ne sais pas le supprimer sans
  supprimer la methode - je ne demande donc rien. Le patron de l'item *lavez / lave / laviez*, ou
  deux propositions finissent par la terminaison annoncee, montre qu'on peut faire mieux, et c'est la
  direction que je recommanderai sur un paquet neuf.
- **`ASV-3` (2026-08-24, non bloquante, un item).** Item 2 : la bonne reponse *grimpe* est contenue
  dans l'infinitif *« grimper »* que l'indice nomme. Un enfant qui cherche une ressemblance la
  trouve. C'est inherent au premier groupe - la troisieme personne du singulier est l'infinitif moins
  le *r* - et cela ne vaut pas qu'on renonce a nommer le verbe. **Un item sur vingt-et-un.**

## 7. Ce que ce verdict fait, et ce qu'il ne fait pas

**Les cinq motifs de mon refus sont leves, et je les ai leves un par un plutot qu'en bloc.** Le motif
decisif l'est par une mesure de moi, pas par une annonce.

**Je ne deplace aucune ligne de matrice** : le geste appartient a `camille-po`. Ce que je lui donne,
c'est le verdict qui le lui permet sur la ligne francais CM1 *"Accorder le verbe avec son sujet (cas
frequents, temps simples : present, futur, imparfait)"*.

**Ce que ce Done ne couvre pas** : ce qu'un lecteur d'ecran annonce (jamais couvert par aucun de mes
Done), ce qu'un enfant reel comprend, et tout rendu sur une largeur, une hauteur ou une pile de
polices que je n'ai pas mesurees - **en particulier la bande 576-719 px, ou les 647 px de ce paquet
depassent largement les 500 px offerts** et ou il se repliera tant que `S19-6a` n'est pas traitee.
Cette bande n'entre pas dans ce verdict, exactement comme elle n'entrait pas dans mon refus.

**Ce que je dois dire a `marc-dev`** : il a corrige cinq motifs sans en contourner un seul, il a
mesure avec mon outil plutot que de m'annoncer une impression, et il a garde la structure des items
qu'il remplacait. **Sur le motif 3, celui ou il etait le plus facile de tricher, il ne l'a pas
fait.** C'est le second paquet du sprint qui revient conforme, et cela n'a rien d'automatique.
