# s57 - lecture avant `verify` du premier jet de `ce1-qlm-dangers-electricite` (`S57-BR-QLM-CE1-08`)

- **Auteure** : `nadia-prof`, professeure des ecoles, seule autorite sur le Done pedagogique.
- **Date** : debut 2026-10-27 12:08:50 (UTC+01:00), ecriture 12:28:50, lues par `date` ; fin en section 9.
- **Arbre** : branche `s57-p5-nadia-lecture08`, base `round-two` a `bdfed668`, lue par `git rev-parse`.
- **Commit lu** : `c8609344` (`[MARC]`, 2026-10-27 11:33:02 +0100), premier jet de `marc-dev`, fusionne dans `round-two` par
  `d37ac5f7`. Sa base contient le `659b218e` de `theo-archi` (dernier ajustement des gardes, `G1` a `G5`) : lu par
  `git merge-base --is-ancestor`.
- **Type** : lecture avant `verify` (`QC7`). **Aucun Done, aucun verdict de lot**, aucun identifiant neuf, aucune condition neuve.
  Je n'ai modifie ni le paquet, ni les tests.
- **Audit degrade, dit** : rien n'est joue a l'ecran, le paquet n'est pas au catalogue (`QC3`). J'ai joue chaque item sur le papier,
  comme un enfant de CE1, y compris en me trompant et en cherchant les raccourcis qu'un enfant prend apres trois manches.
- **Lu** : `ce1-qlm-dangers-electricite.yaml` (12 items) ; `PaquetCe1QlmDangersElectriciteTest.java` ; le jeu fautif
  `contreexemple/PaquetCe1QlmDangersElectriciteTest/contenu/dangers-fautifs.yaml` (sondes 62 a 77 en detail) ; l'entree de rang du
  paquet ; story `S57-BR-QLM-CE1-08` (criteres 1 a 9 et leurs trois blocs de regles) ; mes trois arbitrages de `-08`
  (premiers jets `-06`/`-08` sections 2.1 a 2.7, contradictions `-06`/`-08` sections 2 et 5, dernier lot sections 1, 6.4 et 7) ; les
  trois avis de gardes de `theo-archi` sur `-08` (dernier ajustement en entier pour `G1` a `G5` et `P1`) ; `DEC-0120` A.272, A.273 ;
  ma lecture de `-04` ; le voisin `cp-emc-alerte` (bonnes reponses et l'item du sac oublie).
- **Mesures lancees** :
  - recompte par script (Python, hors du depot, non commite) : mots par la regle de `PaquetLu.mots`, longueurs en caracteres par la
    comparaison stricte de `ChaquePaquetDeQuestionnerLeMondeDeCe1TientLesConditionsTransversesTest.java:198-199`
    (`String.length`), distinction des indices, sur le paquet puis sur une copie portant mes corrections ;
  - `bash outils/mvn-jdk21.sh -B -q -pl adapters/adapter-content -am test -Dtest=PaquetCe1QlmDangersElectriciteTest
    -Dsurefire.failIfNoSpecifiedTests=false`, JDK 21.0.10 (propriete `java.version` du rapport) : **47 tests, 0 echec, 0 erreur**.
    Perimetre : ce seul test, module `adapter-content` et ses dependances. Ce n'est ni un `verify`, ni la porte technique.

## Resume

| Point | Reponse |
|---|---|
| Items | **5 justes, 7 a corriger, 0 refuse en l'etat** (section 2) |
| Autocontrole A.273 | **a** (indice qui donne la reponse ou n'est pas une methode) : Zoe, Nina ; Adam (l'indice ne fait chercher que la moitie du danger) ; Hugo (l'indice ne vaut que par une phrase qui sort). **b** (distracteur ecarte sans la competence) : 6 items (Sami, Zoe, Maya, Noe, Adam, Nina), dont deux par une regle ecrite que le premier jet ne tient pas (Maya, Adam). **c** (source circulaire) : aucune |
| Exactitude | **une correction** : la radio de Hugo peut marcher a piles ; l'enonce dit qu'elle est branchee (2.3) |
| `DEL-CE1-2` au sens | **tenu** : aucun accident decrit, aucun personnage blesse, meme par periphrase |
| Doutes et points de `marc-dev` | section 3 ; `G3` lu en egalite : **non confirme**, l'ancre revient (`T1`) |
| Reouverture d'une formulation | **aucune** (section 7) |
| Cout | **1,0 au bareme a cette ecriture** ; 1,5 si mes corrections appellent une seconde lecture (section 8) |

---

## 1. Ce que j'ai verifie avant les items

- **`FER-3`, deux operations** : danger 4 items, geste 8. En A, l'enfant juge l'etat de trois choses ; en B, il choisit une conduite
  devant un danger donne. Aucun item de A ne se resout par un geste, aucun item de B ne demande de comparer des etats. **Tenue.**
- **`FER-1` et la regle 1** : `prise` (Jade en A ; Sami, Noe en B), `fil` (Mehdi ; Tom, Ines), `eau` (Maya, Adam ; Lea, Zoe, Hugo,
  Nina). Les trois gestes sont servis : *ne pas toucher* (Tom, Sami, Lea, Ines, Noe), *ne pas approcher de l'eau* (Hugo), *mains*
  (Zoe, Nina). Deux items de B `eau` sans mains mouillees (Lea, deja branche ; Hugo, veut poser) : chacun a la forme de sa
  maniere (contradictions `-06`/`-08`, 2.2). **Tenu**, et mes corrections n'en retirent rien.
- **Realite du danger en A (je suis la seule garde)** : une prise cassee, un fil electrique abime, une lampe branchee pres de
  l'evier, un seche-cheveux branche pres du lavabo sont dangereux. Les distracteurs sont sans danger : prise bien fixee, lampe
  allumee, fil bien range, lampe eteinte, appareil branche loin de l'eau, appareil debranche. **Exact.** Un appareil debranche pres
  de l'eau est sans danger electrique, ce que la correction de Maya et d'Adam fait servir (2.2).
- **Exactitude en B** : chaque bonne reponse est juste devant sa situation, et aucune ne demande d'agir sur l'installation. Une
  correction d'exactitude : Hugo (2.3).
- **`DEL-CE1-2` au sens (je suis la seule garde)** : aucun item ne raconte un accident, aucun personnage nomme n'a mal, ni par un
  mot de la regle 5, ni par une periphrase (*"il a eu tres peur"*, *"sa main lui fait mal"* : rien de tel). Le danger n'est jamais
  dit : il est dans l'objet et dans la question. **Tenu.**
- **`DEL-CE1-1`, aucune pile** : aucun mot *pile*. Mais un appareil de la liste fonctionne souvent a piles, la radio : 2.3.
- **Alertes hors des trois formes de `T2` (je suis la seule garde)** : aucune proposition ne dit d'aller voir, d'avertir, de demander
  a ou d'attendre un adulte. L'adulte n'apparait qu'en refus de signaler. **Tenu** ; garde demandee, `T2` en section 6.
- **Abstentions hors des cinq formes (je suis la seule garde)** : aucun distracteur n'est une conduite qu'un adulte attendrait de
  l'enfant, une fois Hugo corrige (2.3). **Tenu apres correction.**
- **`NRC-CE1-1` au-dela du texte** : aucune bonne reponse n'est une alerte, et aucune n'est celle d'un item de `cp-emc-alerte`. Ce
  que le CP apprend sert pourtant a ecarter un distracteur : le refus de signaler, dans 6 items, l'enfant qui sort de `cp-emc-alerte`
  sait qu'il faut le dire. Je l'avais admis en connaissance de cause (dernier lot, 1.2) ; il reste dans chacun de ces items un
  second distracteur qui demande la competence de `-08`. **Tenu**, avec le constat de 3.2.
- **`TRV-CE1-6`** : chaque situation est vecue par un personnage nomme, *la maison de Jade*, *la cuisine*, *le salon*, *le couloir*,
  *chez Adam* ; aucun lieu n'est celui de l'enfant, aucun objet ne lui appartient, *l'evier* et *le lavabo*, jamais la baignoire.
  **Tenu.**
- **Biais** : six filles, six garcons ; aucun role, aucun metier, aucune configuration familiale (*un adulte*, jamais *papa* ou
  *maman*). Les quatre items de A sont chez deux filles et deux garcons. **Tenu.**
- **Lisibilite, vocabulaire** : enonces de 6 a 17 mots, deux phrases au plus sauf Hugo (trois, deux apres correction), une question
  chacun ; propositions de
  3 a 6 mots ; indices de 6 a 11 mots [script]. *Grille-pain*, *mixeur*, *seche-cheveux*, *evier*, *lavabo*, *tournevis*, *scotch*,
  *chiffon*, *appareil* sont des mots de la maison qu'un enfant de CE1 connait. **Tenu.**
- **Source (`QMT-CE1-2`)** : les situations et les gestes renvoient a `DEL-CE1-1` ; les etats sans danger sont dits sans autre
  source, en toutes lettres. **Non circulaire, tenu.**

## 2. Verdict par item

Numerotation : ordre du fichier, prenom entre parentheses. *Juste* : rien a changer. *A corriger* : correction demandee avant
`verify` ; restee telle quelle a l'audit, ce serait une reserve. Aucun item n'est refuse en l'etat : aucun n'a deux reponses
defendables, sauf Hugo par une phrase ajoutee, que la correction retire (2.3).

| # | Ligne | Tache, situation | Bonne reponse ; distracteurs | Indice | Verdict | Motif |
|---|---|---|---|---|---|---|
| 1 (Jade) | `:62` | A, prise | *une prise cassée* ; *une prise bien fixée*, *une lampe allumée* | *Pense à ce qu'on vérifie avant d'utiliser quelque chose.* | **juste** | Paire minimale sur le meme objet, *cassee* contre *bien fixee* : c'est la competence. Les deux distracteurs ne partagent que *une*, que la bonne reponse a. Indice : une methode (verifier l'etat), sans nommer *casse* |
| 2 (Tom) | `:69` | B, fil | *ne pas toucher le fil* ; *réparer le fil avec du scotch*, *ne rien dire à personne* | *Regarde le plastique qui entoure le fil.* | **juste** | Le scotch est le geste qu'un enfant a vu faire : le vrai piege. *Fil* dans deux propositions. Indice : montre ou est le danger (la gaine abimee), sans le geste |
| 3 (Sami) | `:76` | B, prise | *ne pas toucher la prise* ; *la réparer avec un tournevis*, *ne pas déranger un adulte* | *Regarde l'endroit où il manque un morceau.* | **a corriger** | Juste sur le fond. **La bonne reponse est la seule proposition qui nomme *la prise*, le mot de l'enonce** : l'enfant relie la question a la reponse par le mot (defaut **b**). Correction 1 |
| 4 (Mehdi) | `:83` | A, fil | *un fil électrique abîmé* ; *un fil électrique bien rangé*, *une lampe éteinte* | *Regarde de près chaque chose, une par une.* | **juste** | Paire minimale *abime* contre *bien range*. Aucun mot commun aux distracteurs. Indice : methode de comparaison |
| 5 (Lea) | `:90` | B, eau (deja branche) | *ne pas toucher l'appareil* ; *le débrancher en tirant le fil*, *ne rien dire à un adulte* | *Regarde ce qui se trouve à côté du grille-pain.* | **juste** | *Debrancher en tirant le fil* est le geste que l'enfant croit prudent : mon exemple de manipulation. Indice : doute de `marc-dev`, 3.1 |
| 6 (Zoe) | `:97` | B, eau (mains) | *attendre d'avoir les mains sèches* ; *brancher la lampe tout de suite*, *changer l'ampoule de la lampe* | *Regarde bien l'état de ses mains.* | **a corriger** | **Deux motifs.** *Changer l'ampoule* ne repond pas a la scene (Zoe veut brancher, rien ne dit que la lampe est en panne) : l'enfant l'ecarte sans rien savoir, et l'item devient un choix entre deux (defaut **b**). La bonne reponse est la seule a parler des mains, et l'indice ne dit que *ses mains* : il designe la proposition par le mot (defaut **a**). Correction 2 |
| 7 (Maya) | `:104` | A, eau | *une lampe branchée près de l'évier* ; *une lampe branchée loin de l'évier*, *une lampe débranchée loin de l'évier* | *Compare les trois lampes, une par une.* | **a corriger** | Les trois lampes sont justes sur le fond. **Les deux distracteurs portent *loin*, que la bonne reponse n'a pas** : la regle 3 de A, ecrite (*"les deux distracteurs n'ont pas en commun un mot ... que la bonne reponse n'a pas"*), n'est pas tenue. L'enfant prend *pres* parce qu'il est seul. Correction 3, 2.2 |
| 8 (Ines) | `:111` | B, fil | *laisser le fil sans le toucher* ; *toucher le fil pour voir*, *ne rien dire à un adulte* | *Imagine ce qu'il y a à l'intérieur du fil.* | **juste** | *Toucher pour voir* est la curiosite d'un enfant de 7 ans, mon exemple. La paire *toucher* / *sans le toucher* : 3.1. Indice : ou est le danger |
| 9 (Hugo) | `:118` | B, eau (veut poser) | *ne pas l'approcher de l'eau* ; *la poser près du robinet ouvert*, *ne pas déranger un adulte* | *Pense à ce qui coule près de la radio.* | **a corriger** | **Trois motifs, 2.3** : la phrase ajoutee fait de *ne pas deranger un adulte* une conduite attendue ; une radio peut marcher a piles ; l'indice ne vaut que par la phrase ajoutee. Correction 4 |
| 10 (Noe) | `:125` | B, prise | *ne rien mettre dans la prise* ; *la nettoyer avec un chiffon*, *ne pas en parler* | *Pense à ce qui se trouve à l'intérieur de la prise.* | **a corriger** | Juste sur le fond ; le chiffon sur une prise abimee est un vrai danger. **Meme motif que Sami** : seule la bonne reponse nomme *la prise*, et l'indice, qui la nomme aussi, la designe par le mot. Correction 5 |
| 11 (Adam) | `:132` | A, eau | *un sèche-cheveux branché près du lavabo* ; *un sèche-cheveux débranché près du lavabo*, *un sèche-cheveux débranché loin du lavabo* | *Imagine ce qui se trouve autour de chaque appareil.* | **a corriger** | **Meme motif que Maya** : les deux distracteurs portent *debranche*, que la bonne reponse n'a pas ; regle 3 de A non tenue. L'indice ne regarde que l'endroit : il fait chercher l'eau autour, la moitie du danger. Correction 6, 2.2 |
| 12 (Nina) | `:139` | B, eau (mains) | *se sécher d'abord les mains* ; *l'utiliser sans plus attendre*, *tirer sur le fil du mixeur* | *Pense à ce que Nina vient de faire.* | **a corriger** | **Deux motifs.** La bonne reponse est la seule a parler des mains, que l'enonce nomme (defaut **b**, par le mot). L'indice suppose un fait que l'enonce ne dit pas (Nina vient de se laver les mains ?) et renvoie a ses mains, donc a la seule proposition qui en parle : ni une methode, ni un lieu du danger (defaut **a**). *Sans plus attendre* est une tournure ecrite qu'un enfant de CE1 lit mal. Correction 7 |

### 2.1 Le mot de l'enonce dans la seule bonne reponse (Sami, Zoe, Noe, Nina)

En B, l'enonce nomme l'objet du danger. Quand la bonne reponse est la seule des trois a le reprendre (*la prise* chez Sami et Noe, *les
mains* chez Zoe et Nina) et que les distracteurs le remplacent par un pronom ou n'en parlent pas, l'enfant qui lit vite relie la
question a la reponse par le mot : c'est le defaut **b** d'A.273 par la forme. Tom, Ines (*le fil* dans deux propositions) et Lea,
Hugo (aucun mot de l'enonce repris) n'ont pas ce defaut. La correction est la meme partout : un distracteur nomme l'objet, et il
reste un geste faux qu'un enfant ferait. Ecrire *la prise* plutot que *la* rend aussi la proposition lisible seule, sans remonter a
l'enonce.

### 2.2 Les deux items de A `eau` (Maya, Adam) : la regle 3 de A

L'etat dangereux a deux traits, *branche* et *pres de l'eau*. Le premier jet donne a chaque item les deux distracteurs *(branche,
loin)* et *(debranche, loin)* chez Maya, *(debranche, pres)* et *(debranche, loin)* chez Adam : un trait est commun aux deux
distracteurs et absent de la bonne reponse, *loin* chez Maya, *debranche* chez Adam. **C'est la lettre de ma regle 3 de A**, ecrite
avant le premier jet ; ce n'est pas une barre relevee. La structure tenue est **(branche, loin)** et **(debranche, pres)** : chaque
distracteur differe de la bonne reponse par un seul trait, et l'enfant doit tenir les deux ensemble. C'est la competence elle-meme :
l'eau seule ne fait pas le danger, la prise seule non plus [verifie par script : les mots communs aux deux distracteurs sont alors
*une*, *lampe*, *de*, *l'évier* ; *un*, *sèche-cheveux*, *du*, *lavabo*, tous dans la bonne reponse].

L'indice d'Adam suit : *"Imagine ce qui se trouve autour de chaque appareil."* fait chercher l'eau, et seulement l'eau. Avec *(debranche,
pres)* en distracteur, il pousse vers lui. L'indice doit dire qu'il faut deux choses.

### 2.3 Hugo : trois motifs

1. **La phrase ajoutee, *"Un adulte lave la vaisselle."*** (reponse a `marc-dev`, 3.2). Avec un adulte occupe a l'evier,
   *"ne pas déranger un adulte"* se lit *ne pas deranger quelqu'un qui fait la vaisselle* : c'est une conduite qu'un adulte attend
   de l'enfant, donc une abstention au sens de ma regle 3 (dernier lot, 6.4), donc une seconde reponse defendable. La phrase rend
   aussi l'enonce plus long (17 mots, trois propositions a tenir) sans rien apporter au danger.
2. **La radio peut marcher a piles.** Une radio est l'appareil de la maison qu'un enfant voit le plus souvent sans fil. Posee au bord
   de l'evier, une radio a piles n'est pas un danger electrique pour Hugo : l'item affirmerait un danger que la scene ne porte pas.
   C'est l'exactitude, et la frontiere de `-07` (`DEL-CE1-1`, aucune pile). L'enonce dit *branchée*.
3. **L'indice** *"Pense à ce qui coule près de la radio."* ne vaut que par la vaisselle : sans elle, rien ne coule et la radio n'est
   pas encore pres de l'eau. Il devient *ce qui peut eclabousser la radio* : le lieu du danger, sans le geste, et il sert aussi a
   ecarter *pres du robinet ouvert*.

**Le distracteur negatif** : *"ne rien dire à personne"*. Hugo n'a encore rien fait, il n'y a pas de danger a signaler ; dans cette
scene, *ne rien dire a personne* se lit *le faire en cachette* : un enfant qui sait qu'on le gronderait le choisit, et un adulte
ne l'attend pas. C'est une des quatre formes admises ; rien de neuf.

## 3. Les doutes et les points de `marc-dev`

### 3.1 (a) Les indices

| Item | Doute | Reponse | Motif |
|---|---|---|---|
| Zoe | *"Regarde bien l'état de ses mains."* reprend *mains* de la bonne reponse | **fonde, corrige** | 2.1 et correction 2 : l'indice devient *"Pense à l'eau qui reste sur sa peau."* Il montre le danger (l'eau sur la peau), ne reprend aucune proposition, et sert a ecarter le distracteur neuf (*secouer ses mains* laisse l'eau) |
| Lea | *"à côté du grille-pain"* oriente vers l'eau | **non fonde, juste** | C'est ce que ma regle 4 de B demande : *"il montre ou est le danger dans la situation"*. *Eau* n'est refuse que dans l'indice de A. Savoir que l'eau est a cote ne dit pas s'il faut ne pas toucher ou debrancher : le choix reste la competence |
| Nina | *"Pense à ce que Nina vient de faire."* : methode faible | **fonde, corrige** | 2 et correction 7 : *"Pense aux gouttes d'eau sur ses doigts."* |

Les huit autres indices sont des methodes ou des lieux du danger, et aucun ne porte un verbe de geste (*toucher*, *approcher*,
*brancher*, *sécher*, *prévenir*, *adulte*) en B, ni *abîmé*, *cassé*, *eau* en A ; apres correction, les douze restent distincts
[script].

### 3.2 (b) Les distracteurs

| Point | Reponse | Motif |
|---|---|---|
| Les 6 distracteurs negatifs laissent l'enfant hors du danger | **confirme, admis** | C'est la consequence de mon critere du dernier lot (1.2) : *"un adulte attendrait-il cela de l'enfant ?"*. Non pour les quatre refus de signaler, sauf chez Hugo avec la phrase ajoutee (2.3) |
| Hugo, *"Un adulte lave la vaisselle."* pour rendre *ne pas deranger un adulte* plausible | **non confirme ; autre forme** | La phrase sort, *"ne rien dire à personne"* remplace *"ne pas déranger un adulte"*, *branchée* entre dans l'enonce (2.3, correction 4) |
| Les negatifs portent toujours sur dire, parler, deranger : le verbe designe le bon geste | **constat juste, non corrige ici, lu a l'ecran** | Voir le paragraphe ci-dessous |
| Tache danger eau, structure antipodale | **fonde, corrige** | 2.2, corrections 3 et 6 |
| Prise et fil : *cassee*, *abimee* se lisent sans la competence | **confirme, ce n'est pas un defaut** | En A, savoir qu'une prise cassee est dangereuse **est** le contenu de la ligne au CE1. Ce que je refuse, c'est un distracteur qu'on ecarte sans ce savoir ; *bien fixee*, *bien range* demandent de juger l'etat |
| Echo de l'enonce dans un distracteur (Zoe, *brancher la lampe tout de suite* ; Nina, *tirer sur le fil du mixeur*) | **admis** | L'imprudence de B est souvent de faire ce que le personnage veut, tout de suite : c'est elle que l'enfant doit refuser. Un seul distracteur qui reprend l'enonce ne designe rien. Deux le feraient : mes corrections de Zoe et de Nina n'en mettent jamais deux [verifie par script : apres correction, aucun mot commun aux deux distracteurs que la bonne reponse n'a pas, dans les 8 items de B, hors l'article *un* chez Sami ; au premier jet, Zoe en avait trois, *la*, *lampe*, *de*] |
| *Attendre* bonne reponse chez Zoe, *sans plus attendre* distracteur chez Nina | **admis ; leve par ma correction** | D'un item a l'autre, la paire n'apprend que le contenu (attendre d'avoir les mains seches). *Sans plus attendre* sort de Nina pour sa lisibilite (correction 7), pas pour la paire |
| Ines, *toucher le fil pour voir* contre *laisser le fil sans le toucher* | **admis, juste** | En B, l'imprudence est l'oppose de la precaution, et l'enfant doit savoir laquelle des deux est la bonne. Ma regle 3 refuse la precaution **niee** en distracteur (*ne pas* suivi d'une forme), parce qu'elle donne la reponse par la grammaire ; *toucher pour voir* n'est pas une negation, c'est le geste de la curiosite |
| Longueur : la plus courte strictement dans 4 items (Jade, Tom, Sami, Adam), la plus longue strictement dans 3 (Zoe, Ines, Noe) | **compte confirme** | En caracteres, la mesure de la garde transverse (`String.length`) [script]. Apres mes corrections : plus courte strictement dans 3 (Jade, Tom, Sami), plus longue dans 2 (Zoe, Ines). Aucune limite n'est ecrite pour la plus courte a `-08` ; je n'en deduis rien |
| Repartition des negatifs : *ne pas deranger un adulte* 2, *ne rien dire a un adulte* 2, *ne rien dire a personne* 1, *ne pas en parler* 1 ; aucun dans les deux items des mains | **lue ; apres correction 1, 2, 2, 1** | Hugo passe de *deranger* a *ne rien dire a personne* : *ne pas deranger un adulte* 1 (Sami), *ne rien dire a un adulte* 2 (Lea, Ines), *ne rien dire a personne* 2 (Tom, Hugo), *ne pas en parler* 1 (Noe). Aucune forme ne revient assez pour devenir le signe d'un item. Rien dans les items des mains : leur bonne reponse est affirmative, la regle de grammaire n'y demande aucun *ne* |

**Le raccourci de grammaire du second ordre.** `marc-dev` a raison, sur un compte plus petit que les six negatifs : dans **5**
items de B sur 8 (Tom, Sami, Lea, Hugo, Noe), la bonne reponse commence par *ne* et le seul autre *ne* est un refus de signaler ;
le sixieme negatif est chez Ines, dont la bonne reponse est affirmative. Un enfant qui a compris en
trois manches que *la reponse commence par ne pas* et qui sait par le CP qu'il faut le dire a un adulte trouve la reponse sans rien
savoir de l'electricite. Ce n'est pas un ecart du premier jet : c'est la consequence de mes regles 2 et 3 prises ensemble (six formes
sur neuf en *ne*, l'alerte sortie, les seuls negatifs admis etant des refus de signaler), et `marc-dev` ne peut pas le casser avec
les quatre formes. Ines le casse deja une fois (bonne reponse affirmative, un *ne* qui est faux), Zoe et Nina n'ont aucun *ne*.
**Je ne corrige rien ici et je ne rouvre pas la regle 3 sur le papier** : je ne sais pas si un enfant de CE1 prend ce raccourci,
qui demande de combiner deux indices de forme. **Je le regarderai a l'ecran a l'audit** ; s'il s'y voit, ce sera une reserve
datee et la regle 3 sera rouverte a ce moment-la, pas avant. Je suis la seule garde de cette propriete.

### 3.3 Les points interpretes

| Point | Reponse | Motif |
|---|---|---|
| `G3`, *attendre* en egalite (sonde 71, *"attendre sans rien dire"*, passe) | **non confirme : l'ancre revient** | La question etait a moi (`P1` du dernier ajustement de `theo-archi`, route par `A2`) ; le premier jet l'a tranchee a ma place. *Attendre sans rien dire* n'est pas une des quatre formes de refus admises (dernier lot, 6.4), et elle commence par une abstention : a cote de *ne pas toucher le fil*, un enfant la defend (attendre, ne rien toucher, se taire : il est hors du danger). C'est une seconde reponse, et c'est pourquoi j'avais demande les cinq abstentions **ancrees au debut** (`Q11`). Une proposition qui commence par *attendre* en distracteur n'a aucun usage a `-08` : la seule forme en *attendre* est une bonne reponse, et deux formes dans un item sont deja refusees. Cout sur le paquet : **0** distracteur ne commence par *attendre* [script]. `T1` en section 6 |
| *B au moins 4* tenu par `G1` et `FER-1` (8 items) | **confirme** | C'est le raisonnement de `theo-archi` (dernier ajustement, 1.1) : trois situations et trois gestes ne tiennent pas en trois items |
| Cles fermees (`TRV-CE1-1`) | **confirme** | Quatre champs par item, aucune explication : rien a lire en plus pour l'enfant |
| Declarations *"DEL-CE1-1 tache"* et *"DEL-CE1-1 situation"* a cote de chaque item | **confirme** | C'est la regle 1 ; les douze sont vraies a ma lecture |
| Non encode : partie lexicale de la regle 4, traits partages en A, plausibilite des distracteurs | **confirme pour la plausibilite ; les deux autres sont encodables** | La plausibilite reste a moi. Les deux autres sont des mots : `T3` et `T4` en section 6, si `theo-archi` les juge encodables ; le paquet corrige ne leve ni l'une ni l'autre [script] |
| Huit des neuf formes employees, *ne pas brancher la lampe* non employee | **confirme** | Aucune regle ne demande chaque forme ; la regle 1 demande chaque geste, tenu |

## 4. Corrections pour `marc-dev`

Texte exact. Rien d'autre ne change : ni les autres items, ni les bonnes reponses, ni l'ordre des propositions, ni les declarations
de tache et de situation. Chaque `?` garde l'espace insecable (U+00A0) qui le precede dans le fichier.

**Dans `adapters/adapter-content/src/main/resources/contenu/ce1-qlm-dangers-electricite.yaml`** :

| Point | Item, ligne | Texte actuel | Texte a ecrire |
|---|---|---|---|
| 1 | Sami, `:78` | `distracteurs: ["la réparer avec un tournevis", "ne pas déranger un adulte"]` | `distracteurs: ["réparer la prise avec un tournevis", "ne pas déranger un adulte"]` |
| 2a | Zoe, `:96` | `# TRV-CE1-2 : b, a` | `# TRV-CE1-2 : b, b` |
| 2b | Zoe, `:99` | `distracteurs: ["brancher la lampe tout de suite", "changer l'ampoule de la lampe"]` | `distracteurs: ["brancher la lampe tout de suite", "secouer ses mains puis l'allumer"]` |
| 2c | Zoe, `:100` | `indice: "Regarde bien l'état de ses mains."` | `indice: "Pense à l'eau qui reste sur sa peau."` |
| 3 | Maya, `:106` | `distracteurs: ["une lampe branchée loin de l'évier", "une lampe débranchée loin de l'évier"]` | `distracteurs: ["une lampe branchée loin de l'évier", "une lampe débranchée près de l'évier"]` |
| 4a | Hugo, `:118` | `- enonce: "Hugo veut poser la radio au bord de l'évier. Un adulte lave la vaisselle. Que doit-il faire ?"` | `- enonce: "Hugo veut poser la radio branchée au bord de l'évier. Que doit-il faire ?"` |
| 4b | Hugo, `:120` | `distracteurs: ["la poser près du robinet ouvert", "ne pas déranger un adulte"]` | `distracteurs: ["la poser près du robinet ouvert", "ne rien dire à personne"]` |
| 4c | Hugo, `:121` | `indice: "Pense à ce qui coule près de la radio."` | `indice: "Pense à ce qui peut éclabousser la radio."` |
| 5 | Noe, `:127` | `distracteurs: ["la nettoyer avec un chiffon", "ne pas en parler"]` | `distracteurs: ["nettoyer la prise avec un chiffon", "ne pas en parler"]` |
| 6a | Adam, `:134` | `distracteurs: ["un sèche-cheveux débranché près du lavabo", "un sèche-cheveux débranché loin du lavabo"]` | `distracteurs: ["un sèche-cheveux débranché près du lavabo", "un sèche-cheveux branché loin du lavabo"]` |
| 6b | Adam, `:135` | `indice: "Imagine ce qui se trouve autour de chaque appareil."` | `indice: "Le danger vient de deux choses à la fois."` |
| 7a | Nina, `:141` | `distracteurs: ["l'utiliser sans plus attendre", "tirer sur le fil du mixeur"]` | `distracteurs: ["l'utiliser avec les mains mouillées", "tirer sur le fil du mixeur"]` |
| 7b | Nina, `:142` | `indice: "Pense à ce que Nina vient de faire."` | `indice: "Pense aux gouttes d'eau sur ses doigts."` |

**Ce que chaque texte neuf tient** [script, sur une copie corrigee] : propositions de 5 ou 6 mots (`LON-CE1-5`), enonce de Hugo 13
mots, indices de 7 a 9 mots, douze indices distincts ; aucune proposition neuve n'est une forme de la regle 2, une abstention, une
precaution niee ou une alerte ; aucun mot de la regle 5 ni *pile* ; l'enonce de Hugo ne porte que le repere `eau`. Les familles de
distracteurs : *secouer ses mains puis l'allumer* et *l'utiliser avec les mains mouillees* sont des imprudences (famille b),
*reparer la prise*, *nettoyer la prise* restent des manipulations (famille a). La ligne de declaration de Nina (`:138`, *b, a*) reste
juste.

**Pourquoi ces textes et pas d'autres**, quand ce n'est pas dit en section 2 :
- *Secouer ses mains* (Zoe) est ce qu'un enfant fait vraiment pour se secher, et c'est faux : les mains restent mouillees. Il ne
  reprend pas *brancher*, pour qu'aucun mot ne soit commun aux deux distracteurs sans etre dans la bonne reponse.
- *L'utiliser avec les mains mouillees* (Nina) est l'imprudence dite en clair : l'enfant qui ignore le danger la choisit, celui qui
  le connait l'ecarte. Elle partage *les mains* avec la bonne reponse, qui n'est plus la seule a en parler.
- *Le danger vient de deux choses à la fois* (Adam) est une methode : elle fait tenir *branche* et *pres de l'eau* ensemble, sans
  nommer l'etat.

## 5. Le jeu fautif

Pour `marc-dev`, avec la forme de `T1` : la **sonde 71** (`dangers-fautifs.yaml:507-512`, *"attendre sans rien dire"*) passe
aujourd'hui ; elle devient une sonde **refusee** par la garde des abstentions, et le test qui la juge
(`leJeuFautifCommiteLaisseLesRefusDeSignalerLAbstentionHorsDuDebutEtAttendreSuiviDUnRefusPasser`,
`PaquetCe1QlmDangersElectriciteTest.java:840-846`) ne la compte plus parmi les sondes qui passent. Le paragraphe *"Une lecture a
confirmer"* de la Javadoc (`:68-70`) est tranche par ce fichier. La forme du refus est a `theo-archi`.

## 6. Ce qui revient a `theo-archi` (forme), une ligne par garde

| Repere | Garde | Ajustement |
|---|---|---|
| `T1` | `G3`, abstentions (`ABSTENTION`, `PaquetCe1QlmDangersElectriciteTest.java:181-182`) | Revenir a ta forme du `659b218e` (dernier ajustement, 1.3) : *attendre* ancre au debut comme les quatre autres, borne `(?!\p{L})`. Sonde refusee : *"attendre sans rien dire"* (sonde 71) ; *"attendrir le fil"* (sonde 70) continue de passer. Reponse a ton `P1` : je n'admets pas *attendre* suivi d'un refus de signaler |
| `T2` | Alerte a un adulte (`ALERTE`, `:174-175`), si tu la juges encodable plus large | Refus de toute proposition qui contient *adulte* et ne commence pas par *ne*. Sondes refusees : *"aller voir un adulte"*, *"avertir un adulte sans attendre"*, *"attendre un adulte"* ; passent : *"ne rien dire à un adulte"*, *"ne pas déranger un adulte"*. Le paquet corrige ne leve rien. Sinon *non garde : `NRC-CE1-1`*, je le lis |
| `T3` | Regle 4, partie lexicale, garde neuve si tu la juges encodable | Indice d'un item `geste` : aucune forme de *toucher*, *approcher*, *brancher*, *sécher*, *prévenir*, ni *adulte* ; indice d'un item `danger` : aucune forme de *abîmé*, *cassé*, ni *eau*. Sondes refusees, mes exemples de 2.5 (premiers jets) : B *"Ne touche à rien."*, *"Pense à un adulte."* ; A *"Regarde ce qui est près de l'eau."* Le paquet corrige ne leve rien. Sinon *non garde : `TRV-CE1-5`*, je le lis |
| `T4` | Regle 3 de A, mot commun aux deux distracteurs, garde neuve si tu la juges encodable | Sur `plat`, par mots : refus d'un item `danger` dont un mot est dans les deux distracteurs et pas dans la bonne reponse. Sondes refusees : Maya et Adam **du premier jet** (*loin*, *debranche*) ; passent : les memes corriges, et Jade, Mehdi. Le trait hors du mot (une forme, un usage) reste a moi. Sinon *non garde : `TRV-CE1-2`*, je le lis |

## 7. Cette lecture rouvre-t-elle une formulation ?

**Non.** Aucun texte de la story ni de mes trois arbitrages de `-08` n'est a reecrire. Les corrections de Maya et d'Adam appliquent la
lettre de ma regle 3 de A ; celles de Sami, Zoe, Noe et Nina, le defaut **b** et le defaut **a** d'A.273, deja ecrits ; celle de Hugo,
l'exactitude, ma definition de l'abstention (dernier lot, 6.4) et une des quatre formes de refus admises. `T1` repond a une question
qui m'etait routee et rend sa forme a ma demande de `Q11` ; `T2` a `T4` demandent de garder des regles ecrites. Le raccourci de 3.2
est un constat a verifier a l'ecran, pas une regle. Rien a reporter par `camille-po`.

## 8. Cout

**Au bareme** (1,0 par lot, *"lecture avant `verify` et correction qui la suit comprises"*) : **1,0** a cette ecriture. Mes
corrections touchent sept items : si `marc-dev` les applique telles qu'ecrites, leur relecture est une seconde lecture, **0,5 hors
marge** (`DEC-0120` A.272, A.270 point 4), soit **1,5**. Aucun seuil n'est ecrit pour `-08`, et je n'en deduis aucun. Je ne convertis
pas une duree en bareme : aucune regle ne le fait.

## 9. Duree et verifications

**Duree**, mesuree par `date` : debut **12:08:50** ; derniere lecture de `date` avant l'arret de ma session **12:36:16**, apres les
verifications ci-dessous, soit **27 min 26 s** de lecture et d'ecriture. Reprise a **13:39:28** sur demande de l'agent lanceur, pour
relire le fichier, completer cette section et commiter ; fin de la reprise lue par `date` juste avant le commit, rapportee a l'agent
lanceur et non ecrite ici (ecrire l'heure changerait le fichier mesure). L'intervalle 12:36 a 13:39 est un arret de session, pas
du travail : je ne le compte pas. **[corrige le 2026-10-27, `nadia-prof`, ce fichier : une premiere version de ce paragraphe portait
une fin a 12:47:05 et 38 min 15 s ; ces chiffres avaient ete ecrits avant d'etre lus par `date`, ils etaient faux]** Aucun seuil de
duree ne s'applique a une lecture.

**Verifications avant commit**, ce fichier present dans l'arbre, base `bdfed668`. Il n'ecrit ni ne renomme aucun identifiant de
condition : `T1` a `T4`, les numeros de correction et les reperes de `marc-dev` et de `theo-archi` sont des reperes de demande.

- `bash outils/mvn-jdk21.sh -pl bootstrap -am test -Dtest=ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest`, telle quelle :
  *"BUILD FAILURE"* sur `recreo-domain`, *"No tests matching pattern "ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest" were
  executed!"*. Le garde-fou n'a pas ete lance ; meme symptome que dans mes fichiers precedents.
- La meme commande avec `-Dsurefire.failIfNoSpecifiedTests=false` : *"Tests run: 10, Failures: 0, Errors: 0, Skipped: 0"*,
  *"BUILD SUCCESS"*. Perimetre : ce seul test, module `bootstrap` et ses dependances ; ni un `verify`, ni la porte technique.
  Relancee a la reprise, sur la version finale de ce fichier : resultat rapporte a l'agent lanceur.
- `bash .github/scripts/verifier-chemins.sh < /dev/null`, ce fichier indexe : *"Aucun chemin de poste de travail ni nom de compte
  dans les fichiers verifies."*, sortie 0.
- Aucun autre job de `.github/workflows/ci.yml` lance.

## 10. Routage

| A qui | Quoi |
|---|---|
| `marc-dev` | section 4, points 1 a 7 ; section 5 |
| `theo-archi` | section 6, `T1` a `T4` |
| `sofia-sm` | le cout est en section 8 ; la seconde lecture, s'il y en a une, s'inscrit sur la ligne hors marge |
| `camille-po` | rien (section 7) |

## 11. Ce que ce document ne fait pas

Il ne prononce aucun Done et aucun verdict de lot, n'ecrit aucune condition neuve, n'alloue aucun identifiant, ne modifie ni le
paquet, ni un test, ni le catalogue, ni `DEC`, ni matrice, ni registre, ni backlog.

**Ce dont je reste la seule garde sur `-08`, aucune couverte par un test** : la realite du danger en A et son absence dans les
distracteurs ; qu'une bonne reponse de A ne soit pas un geste deguise (*"réparer le fil"* passerait en A si elle portait le repere de
sa situation, et seule ma lecture l'arrete) ; la plausibilite des distracteurs de B ; les abstentions ecrites hors des cinq formes ;
les alertes hors des trois formes tant que `T2` n'est pas encode ; la methode des indices ; un personnage nomme blesse par
periphrase ; `NRC-CE1-1` au-dela du texte ; le raccourci de grammaire du second ordre (3.2) ; qu'un appareil de B ne puisse pas
marcher a piles.

**Signe** : `nadia-prof`, s57, palier 5, 2026-10-27.
