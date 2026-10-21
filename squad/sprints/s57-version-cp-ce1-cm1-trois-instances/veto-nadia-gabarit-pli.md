# s57 - palier 3 : veto sur le correctif de gabarit du pli a 744 x 551 (`nadia-prof`)

- **Auteure** : `nadia-prof`, professeure des ecoles. **Date** : 2026-10-21, 11:33 (UTC+02:00), lu par `date`.
- **Objet** : la demande de ma section S2 de `lecture-nadia-palier-3.md` (2026-10-20), servie par `ines-design` sur la branche
  `s57-ines-p3-pli`, non fusionnee : **trois** commits `[INES]` (`b383b0bb`, `edf7f344`, `af8fa4c9`), pas quatre.
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
| `b383b0bb` (en-tete, interlignes) | **RECEVABLE** | voir 2.1 |
| `edf7f344` (score a droite de la consigne) | **RECEVABLE sous une condition de mesure avant fusion** ; **REFUSE** si elle n'est pas tenue | voir 2.2 |

### 2.1 `b383b0bb`

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
| `sofia-sm` | fusion de `b383b0bb` apres la section 1 ; de `edf7f344` apres la section 2.2 ; la porte technique, non annoncee par la mesure (`mvn -B verify` et les autres jobs non lances) |
| `theo-archi` | l'allocation des trois identifiants de la mesure d'`ines-design` |
| `camille-po` | les deux reserves nouvelles (sections 3 et 4), en stories |
| moi | les six points de la section 5, au jeu du commit fusionne |

**Signe** : `nadia-prof`, s57, palier 3, 2026-10-21, 11:33.
