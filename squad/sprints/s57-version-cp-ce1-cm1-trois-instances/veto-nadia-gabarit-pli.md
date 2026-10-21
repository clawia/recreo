# s57 - palier 3 : veto sur le correctif de gabarit du pli a 744 x 551 (`nadia-prof`)

- **Auteure** : `nadia-prof`, professeure des ecoles. **Date** : 2026-10-21, 11:33 (UTC+02:00), lu par `date`.
- **Objet** : la demande de ma section S2 de `lecture-nadia-palier-3.md` (2026-10-20), servie par `ines-design` sur la branche
  `s57-ines-p3-pli`, non fusionnee : **trois** commits `[INES]` (`5302af53`, `edf7f344`, `af8fa4c9`), pas quatre.
- **Lu** : `git show af8fa4c9:.../mesure-ines-palier-3-pli-744x551.md`, sections C1 a C11 ; `git diff round-two...s57-ines-p3-pli`
  sur `recreo.css` et sur `LaFormeCommuneDesPropositionsLonguesEstEpingleeTest.java` ; `git show --stat` de chaque commit ;
  `fragments/manche.html` (ordre du message) ; `recreo.css` lignes 369-388 (`.enonce`, `.texte-support`).
- **Audit degrade, declare** : je n'ai pas lance le jar. Aucune cote n'est rejouee par moi : celles qui suivent sont celles
  d'`ines-design`. Ce document ne prononce aucun Done, ne modifie aucun fichier de la branche, n'ecrit ni n'alloue aucun
  identifiant de condition ou de reserve. Les trois identifiants de la mesure d'`ines-design` restent a allouer par `theo-archi`.

## 0. Correction d'une premisse, avant d'arbitrer

La question qui m'est posee dit que l'interligne de 1,25 *"serre le texte de lecture d'un enfant de CE1"*. **C'est inexact.** Le
texte que l'enfant lit en continu est `.texte-support` (`manche.html`, ligne 66) ; sa regle (`recreo.css`, ligne 385) n'a pas
d'interligne propre, et aucune des quatre regles du diff ne le nomme. Ce qui passe a 1,25, c'est **l'enonce** (la question, en
2 rem gras) et **les propositions**. C'est sur cela que j'arbitre.

## 1. Constat qui conditionne tout le reste : les cotes ne valent pas pour `round-two`

La mesure est faite sur `a0a69859`. Depuis, `round-two` porte `62b65097` (`-06` item 14, texte-support : *"Il se met a pleuvoir."*
devient *"La pluie se met a tomber."*, 4 caracteres de plus) et `d0f3b1aa` (EMC items 8 et 9, enonces). Lu par
`git diff --stat a0a69859 round-two -- adapters/adapter-content/src/main adapters/adapter-web/src/main`. La table de hash d'`ines-design`
porte, pour `-06` item 14, *"egal"* contre l'ancien texte. Par sa propre regle (section 6 de sa mesure) : **hash different, cotes a
refaire.** Pour `-06` 14, cela compte : la marge est de 7 px, et une ligne de texte-support de plus la ferait passer sous le pli.

**Exigence, avant fusion** : la remesure de `-06` (20 items) et de l'EMC (24 items), a 744 x 551 apres une erreur, sur l'arbre de
la branche fusionnee avec `round-two`.

## 2. Verdicts, par commit

| Commit | Verdict | Motif |
|---|---|---|
| `5302af53` (en-tete, interlignes) | **RECEVABLE** | voir 2.1 |
| `edf7f344` (score a droite de la consigne) | **RECEVABLE sous une condition de mesure avant fusion** ; **REFUSE** si elle n'est pas tenue | voir 2.2 |

### 2.1 `5302af53`

**Ma barre de S2 est atteinte, je ne la deplace pas.** (a) Aucune taille de police ni graisse ne change : le diff ne declare aucun
`font-size`. (b) Les trois propositions restent sur l'ecran de l'enonce (bas a 470 au plus, cote d'`ines-design`). (c) Aucun
gabarit n'est modifie (`git diff ... -- templates` vide) : l'ordre est celui du document. Mon veto (cacher le texte support ou les
propositions) n'est pas touche.

**L'interligne de 1,25 sur l'enonce et les propositions** : je ne le refuse pas. Ces textes font une ou deux lignes en 2 rem gras ;
sur une ligne, l'interligne ne change que la hauteur de la boite, pas la lecture. Sur deux lignes, 8 px de moins entre deux lignes
de 32 px restent un ecart lisible a ma lecture. 1,25 est sous le 1,5 que l'on recommande pour un **paragraphe** ; ce n'est pas un
paragraphe, et le paragraphe (le texte-support) garde son interligne. Ce que je ne peux pas affirmer sans l'ecran est en section 5.

**La hauteur de la proposition (58 contre 50 px pour "Les jeux", rapport 1,16 au lieu de 1,32)** : je ne la refuse pas.
`LActionNeDeclarePasMoinsQueLaSortieTest` n'est pas assoupli, la proposition reste plus haute que la sortie, six fois plus large,
en aplat plein. Pour un enfant, ce qui dit "c'est ici que je reponds" est la largeur, l'aplat et la place sous l'enonce bien plus
que 8 px de hauteur. Je le regarde a l'ecran (section 5) ; je ne fixe pas un rapport minimal que je n'avais pas ecrit.

### 2.2 `edf7f344`

Les cotes avec et sans sont celles que je demandais (`reponse-nadia-forme2-palier-1.md`, section 3) : la regle gagne 30 px partout
et elle est la seule a ramener l'indice de `-06` 14 au-dessus du pli (574 sans, 544 avec). L'ordre du document ne change pas : le
score reste lu et tabule en premier.

**Condition** : la regle porte sur **tout** ecran de manche a propositions ecrites sans bloc adulte, CP, CE1 et CM1 ; la mesure
n'a releve que les cinq paquets de CE1. Le flottant retire a la consigne la largeur du score. Or j'ai juge suffisante la
distinction entre consigne et texte-support *"tant que la consigne du jeu tient sur une seule ligne rendue"* (s22, section 7, citee
dans `recreo.css` ligne 382) ; et une consigne qui passe a deux lignes autour du score melange le score a ce que l'enfant lit.
**Exigence** : releve, sur tous les jeux du catalogue servis par cet ecran, de 720 a 1200 px avec et sans barre : **chaque consigne
tient sur une ligne a cote du score.** Une seule consigne sur deux lignes, et ce commit est refuse ; il faut alors un autre levier
pour `-06` 14 (section 3).

## 3. `-06` item 14 : le signal sous le pli

Ma barre de S2 etait **l'indice entier**. Sur la mesure d'`ines-design`, il finit a 544 : barre atteinte, sous reserve de la
remesure de la section 1. Le signal (icone et verdict) qui finit a 570 dans 2 ordres sur 6 n'etait pas dans ma barre : je ne
l'y ajoute pas apres coup.

**Reserve nouvelle, datee du 2026-10-21, non bloquante** (meme statut que le pli, condition 4.1 point 4) : apres une erreur, le
**mot du verdict** doit se lire sans defiler. Si les 19 px sous le pli ne sont que le bas de la boite et de l'icone, la reserve est
acceptable et se leve a l'ecran. Si une ligne du verdict est coupee, elle est a traiter.

**Exigence, si a traiter** (je ne choisis pas entre contenu et gabarit) : `-06` 14, a 744 x 551, apres une erreur, dans les six
ordres : verdict et indice entiers au-dessus du pli, **sans** retirer une phrase au texte-support (`INF-CE1-1`), sans rendre la
bonne reponse reperable a sa longueur (`TRV-CE1-2`), sans revenir sur la correction de langue de S3, et sans rien de ce que mon
veto de S2 interdit.

## 4. La revelation

A la revelation, l'ordre du message est : signal, *"Appuie sur la bonne reponse :"*, la reponse attendue, l'explication, puis
l'indice (`manche.html`, lignes 193-218). L'indice a alors servi ; ce que l'enfant doit voir, c'est **la bonne reponse**.

- **Je n'exige rien sur l'indice a la revelation.** Les 40 items sur 110 ou il passe sous le pli ne sont pas une reserve.
- **Exigence, reserve nouvelle datee du 2026-10-21, non bloquante** : a 744 x 551, a la revelation, dans les six ordres, sur les cinq
  paquets, la phrase *"Appuie sur la bonne reponse :"* et la reponse attendue entieres au-dessus du pli. La mesure releve le bas de
  `.indice`, pas celui de `.reponse-attendue` : **ce point n'est pas mesure**, je ne le presume ni tenu ni rompu.

## 5. Ce que j'affirme sans jouer, et ce qui exige l'ecran

| Affirme sur le diff et les releves d'`ines-design` | Exige l'ecran (moi) |
|---|---|
| taille et graisse des textes inchangees | la lecture d'un enonce ou d'une proposition sur deux lignes a 1,25, **y compris au CP et au CM1**, que la regle touche et que la mesure n'a pas joues |
| interligne du texte-support inchange | sur `cp-nature-mot`, le mot designe (2,3 rem, fond jaune) dans un enonce a 1,25 : aucune boite ne touche la ligne voisine |
| ordre du document inchange, aucun gabarit modifie | que le score a droite se lise comme le score et pas comme la fin de la consigne |
| rien de masque ni de tronque (`scrollWidth`, releve d'`ines-design`, que je n'ai pas refait) | la proposition a 58 px se voit encore comme ce sur quoi on appuie, a cote de la sortie |
| | `-06` 14 : le mot du verdict coupe ou non (section 3) |
| | une police et un appareil reels : la mesure a une police, un navigateur, aucun appareil |

**Je reste la seule garde** de ces six points. Le garde-fou `LaFormeCommuneDesPropositionsLonguesEstEpingleeTest` epingle des
valeurs ; il ne dit rien de leur lecture par un enfant, ni d'une consigne passee a deux lignes sur un jeu non mesure.

## 6. Routage

| A qui | Quoi |
|---|---|
| `ines-design` | la remesure de la section 1 ; le releve des consignes de la section 2.2 ; le bas de `.reponse-attendue` a la revelation (section 4) |
| `sofia-sm` | fusion de `5302af53` apres la section 1 ; de `edf7f344` apres la section 2.2 ; la porte technique, non annoncee par la mesure (`mvn -B verify` et les autres jobs non lances) |
| `theo-archi` | l'allocation des trois identifiants de la mesure d'`ines-design` |
| `camille-po` | les deux reserves nouvelles (sections 3 et 4), en stories |
| moi | les six points de la section 5, au jeu du commit fusionne |

**Signe** : `nadia-prof`, s57, palier 3, 2026-10-21, 11:33.

## Suite du 2026-10-21, 12:24 : verdict apres la remesure d'`ines-design`

- **Lu** : `git show 80781f61:.../mesure-ines-palier-3-pli-744x551.md`, section *"Remesure du 2026-10-21"* (D1 a D7), sur
  l'arbre fusionne local `bee3bb37` (`round-two` a `8805c4dc` + `s57-ines-p3-pli`). Verifie par moi : les 13 jeux dont la
  consigne passe a deux lignes sans le flottant (D3) ont pour paquets `cp-position-syllabe`, `cp-qlm-hygiene`, `cp-qlm-matiere`,
  `cp-qlm-objets-numeriques`, `cm1-sciences-energie`, `-melanges`, `-etats-matiere`, `-familles-materiaux`, `cm1-eps-effets-activite`,
  `cm1-eps-roles`, `cm1-emc-fait-opinion`, `-emotion-ressentir-agir`, `-vote-election` (`catalogue.yaml`) ; **aucun ne porte de
  `texteSupport`** (`grep -c`, 0 sur les 13 fichiers).
- **Audit degrade, comme ce matin** : pas de jar lance par moi, cotes d'`ines-design`. Aucun Done prononce, aucun identifiant
  ecrit ni alloue. La porte technique de l'arbre fusionne n'est pas annoncee (`mvn -B verify` non lance, D7).
- **La section 1 est tenue** : la remesure de `-06` et de l'EMC sur l'arbre fusionne est faite ; aucune regression sur `-07`,
  `-11`, `MAT-CE1-01`.

### V1. `edf7f344` : REFUSE

Ma condition de 2.2 n'est pas tenue : **22 jeux** (CP, CE1, CM1) passent a deux lignes **a cause du score a droite**, dont 3
jusqu'a 992 px. C'est exactement le defaut que je nommais : le score melange a ce que l'enfant lit. Je precise ma propre
condition plutot que de la durcir : elle visait ce que le flottant cree, et je ne compte pas contre lui les 13 jeux qui
depassent la ligne sans lui ; les 22 suffisent.

Les deux autres chemins de D5 ne sont pas des commits : je ne les accepte ni ne les refuse d'avance.
- **Flottant reserve aux consignes courtes** : recevable dans son principe, sous **la meme condition que 2.2**, remesuree sur le
  commit qui le porte (chaque consigne a cote du score sur une ligne, 720 a 1200, avec et sans barre). Le seuil de 67 caracteres
  est une cote de cette police-ci (68 casse, 73 tient) : ma condition porte sur la ligne rendue, pas sur un nombre de caracteres.
- **Score dans l'en-tete** : il change la place et l'ordre de lecture du score. Je ne le juge pas sans mesure ni ecran.

### V2. `5302af53` seul : fusion RECEVABLE

Mon verdict de 2.1 tient, ma condition de section 1 est levee. **Les 13 consignes sur deux lignes ne sont pas un defaut de ce
correctif** : elles le sont deja sur `round-two`, `5302af53` ne touche ni la taille ni la largeur de la consigne, et ma condition
de s22 (consigne d'une ligne) protege la distinction avec un texte-support qu'aucun de ces 13 jeux ne porte. La longueur de ces
consignes (81 a 107 caracteres, dont 4 au CP) est une question de lisibilite du contenu, que je juge en jouant ces jeux, pas
dans ce veto.

### V3. Reponse attendue sous le pli a la revelation : RESERVE NON BLOQUANTE, maintenue

C'est le statut que je lui ai donne ce matin (section 4) ; la mesure ne m'en fait pas changer. Constat : 3 items sur 110 a 744
(`-06` 14 a 576 dans 2 ordres sur 6 ; EMC 13 et `-11` 13 a 560, reponse sur 3 lignes dans une colonne de 444 px), 5 a 720 avec
barre (EMC 21, `-11` 18 en plus). **Ces cotes incluent `edf7f344`, que je refuse** : avec `5302af53` seul, tout bas superieur a
521 passe le pli, et ce compte **n'est pas mesure**. Exigence : remesure sur l'arbre qui sera fusionne.

Ce qui doit etre vrai, sans choisir le levier (contenu ou gabarit) : a 744 x 551, a la revelation, dans les six ordres, la phrase
*"Appuie sur la bonne reponse :"* et la reponse attendue entieres sans defiler ; **sans** rendre la bonne reponse reperable a sa
longueur parmi les propositions (`TRV-CE1-2`), sans retirer a `-11` ce que la negation exige, sans retirer une phrase au
texte-support de `-06` (`INF-CE1-1`), sans cacher le texte-support ni les propositions (mon veto de S2).

### V4. `-06` item 14 a 574 : la reserve non bloquante tient

Avec `5302af53` seul, la reserve de S2 (indice entier apres une erreur) se reduit a **un item sur 110, dans 2 ordres sur 6**. Elle
reste non bloquante, comme je l'ai ecrite le 2026-10-20 ; je ne la durcis pas. La cote de 574 est prise sur l'**ancien** texte
(D5) : a remesurer sur *"La pluie se met a tomber."*. La reserve du mot du verdict (section 3) s'y rattache : sans le flottant,
le verdict descend d'environ 30 px, et sa seconde ligne, deja a 562 avec lui, passe sous le pli. Exigence de section 3 inchangee.

### V5. Ce qui exige encore l'ecran (moi)

1. Lecture d'un enonce ou d'une proposition sur deux lignes a 1,25, au CP, au CE1 et au CM1.
2. `cp-nature-mot` : le mot designe dans un enonce a 1,25, aucune boite sur la ligne voisine.
3. La proposition a 58 px vue comme ce sur quoi on appuie, a cote de la sortie.
4. `-06` 14 apres une erreur : le mot du verdict lisible sans defiler.
5. EMC 13, `-11` 13, `-06` 14 a la revelation : la derniere ligne de la reponse attendue lue ou coupee.
6. Une police et un appareil reels.

Le point *"score lu comme le score"* tombe avec le refus d'`edf7f344`. **Je reste la seule garde de ces six points** : aucun
test ne mesure une ligne coupee ni sa lecture par un enfant.

| A qui | Quoi |
|---|---|
| `sofia-sm` | fusion de `5302af53` seul ; `edf7f344` ne se fusionne pas ; la porte technique de l'arbre fusionne, jobs nommes |
| `ines-design` | apres fusion : `-06` 14 sans flottant sur le nouveau texte ; reponse attendue a la revelation, 110 items |
| `camille-po` | V3 et V4 en stories ; le levier de `-06` 14 et de la revelation, a ouvrir sous les contraintes de V3 |

**Signe** : `nadia-prof`, s57, palier 3, 2026-10-21, 12:24.

### V6. Suite du 2026-10-21 : `5302af53` seul, remesure d'`ines-design`

- **Lu** : `git show 41578ff7:.../mesure-ines-palier-3-pli-744x551.md`, section *"Remesure du 2026-10-21 apres le refus
  d'`edf7f344`"* (E1 a E7), branche `s57-ines-p3-b383`. Cotes d'`ines-design`, non rejouees par moi. Aucun Done, aucun identifiant.
- **Correction de mon V3** : j'y ai ecrit que la reponse d'EMC 13 et de `-11` 13 tenait sur 3 lignes, en reprenant la deduction
  de D4. `ines-design` l'a corrigee (E4) : **2 lignes**. Ce que j'en tirais ne change pas.

**1. Statut.** V3 reste une **reserve non bloquante**, pour la fusion comme pour le Done. Je ne la durcis pas parce que le compte
passe de 3 a 6 items : la barre est celle de ce matin, c'est le refus d'`edf7f344` qui a deplace les cotes. La reserve du verdict
de `-06` 14 (section 3) passe de *"a verifier"* a **"a traiter"** : la seconde ligne du verdict finit a 590, sous le pli (E3). Elle
reste non bloquante, au meme statut que le pli.

**2. Par item, a 744 x 551, dans les six ordres** (je decris ce qui doit etre vrai, pas le levier) :

| Item | Ce qui depasse | Exigence | Nature |
|---|---|---|---|
| `-06` 14 (2 ordres sur 6, trois rangees de propositions) | indice 574, verdict 590, phrase 552, reponse 603 | apres une erreur : verdict et indice entiers ; a la revelation : phrase et reponse entieres | contenu ou gabarit ; contraintes `INF-CE1-1`, `TRV-CE1-2`, S3, mon veto de S2 |
| `-06` 16, 19, 20 | reponse attendue 563 ; boite du signal 560, texte du verdict 550 | reponse attendue entiere a la revelation | contenu ou gabarit, memes contraintes ; **jeu reel** pour la boite du signal (icone coupee ou non) |
| EMC 13, `-11` 13 (et EMC 21, `-11` 18 a 720 avec barre) | reponse attendue 587, 2 lignes | reponse attendue entiere a la revelation | contenu ou gabarit ; `TRV-CE1-2`, et pour `-11` ce que la negation exige |
| `-06` 14, la phrase a 552 (1 px) | derniere ligne du texte | lisible sans defiler | **jeu reel** seulement : 1 px est sous la precision d'un rectangle de ligne (E6) |

**3. Ordre : oui, fusionner `5302af53` maintenant, et traiter les 6 items ensuite.** A ma lecture du diff, ce commit fait remonter
le contenu (en-tete plus mince, interlignes reduits) : il ne cree aucun de ces depassements. Ce point n'est pas mesure sur
`round-two` avant correctif ; c'est une deduction de la forme du diff, pas une cote. Les 6 items deviennent des stories de
`camille-po`. Le Done de `-06`, de l'EMC et de `-11` reste a prononcer **apres** mon jeu de l'arbre fusionne (V5), avec ces reserves
ecrites.

**Signe** : `nadia-prof`, s57, palier 3, 2026-10-21.

## Amendement du 2026-10-21, 18:28 (`nadia-prof`) : ecart 20 du releve de cloture

Source : `releve-relecteur-cloture-palier-3.md`, section 3.3. **Rien au-dessus n'est reecrit.** Convention de citation du
texte servi : `arbitrage-nadia-q05-q10.md`, section 9. Formes exactes :

| Lignes | Ecrit ici | Forme exacte | Source |
|---|---|---|---|
| 22 | *"Il se met a pleuvoir."* | *"Il se met à pleuvoir."* | `ce1-inference` item 14 avant `62b65097` |
| 23, 170 | *"La pluie se met a tomber."* | *"La pluie se met à tomber."* | meme item, depuis `62b65097` |
| 86, 91, 162 | *"Appuie sur la bonne reponse :"* | *"Appuie sur la bonne réponse :"* | `fragments/manche.html`, revelation |

Constat fait en relisant la source, sans verdict : dans `manche.html`, l'espace avant les deux-points de cette phrase est une
espace simple, pas une espace insecable. Le controle appartient au `relecteur-pedagogique` (`DEC-0002`, `DEC-0056`) : je le lui
signale.

Correction de la derniere phrase de la section du 2026-10-21, 12:24 : le Done de `-06`, de l'EMC et de `-11` ne se prononcait
pas *"apres mon jeu de l'arbre fusionne (V5)"* ; je l'ai corrige moi-meme dans `verdict-nadia-palier-3-ce1.md`, section 2
(*"Correction de mon propre constat"*).

**Signe** : `nadia-prof`, 2026-10-21, 18:28.
