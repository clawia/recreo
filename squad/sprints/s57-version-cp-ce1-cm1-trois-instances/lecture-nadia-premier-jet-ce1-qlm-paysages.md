# s57 - lecture avant `verify` du premier jet de `ce1-qlm-paysages` (`S57-BR-QLM-CE1-10`)

- **Auteure** : `nadia-prof`, professeure des ecoles, seule autorite sur le Done pedagogique.
- **Date** : debut 2026-10-27 12:08:51 (UTC+01:00), ecriture 12:25:58, lues par `date` ; fin en section 9.
- **Arbre** : branche `s57-p5-nadia-lecture10`, base `round-two` a `bdfed668`, lue par `git rev-parse`.
- **Commit lu** : `7cc77996` (`[MARC]`, 2026-10-27 11:38:37 +0100), premier jet de `marc-dev`, fusionne dans `round-two` par
  `3f39bb78`.
- **Type** : lecture avant `verify` (`QC7`). **Aucun Done, aucun verdict de lot**, aucun identifiant neuf, aucune condition neuve.
  Je n'ai modifie ni le paquet, ni les tests. Les reperes `L1`... (corrections), `T1`... (gardes) sont locaux a ce fichier.
- **Audit degrade, dit** : rien n'est joue a l'ecran, le paquet n'est pas au catalogue (`QC3`). J'ai joue chaque item sur le papier,
  comme un enfant de CE1, y compris en me trompant.
- **Lu** : `ce1-qlm-paysages.yaml` (13 items) ; `PaquetCe1QlmPaysagesTest.java` ; les jeux fautifs `paysages-fautifs.yaml` et
  `jugements-fautifs.yaml` ; story `S57-BR-QLM-CE1-10` (critere 9 et ses deux blocs, `PAY-CE1-1`, `PAY-CE1-2`) ; mes quatre arbitrages
  de `-10` ; les trois avis de gardes de `theo-archi` sur `-10` ; `DEC-0120` A.273 ; mes lectures de `-01` a `-04` ; les voisins
  `cp-qlm-modes-de-vie` et `cm1-histoire-geographie-nommer-en-geographie` ; `competences-ce1.md`, section *Se situer dans l'espace*.
- **Mesures lancees** :
  - `bash outils/mvn-jdk21.sh -B -q -pl adapters/adapter-content -am test -Dtest='PaquetCe1QlmPaysagesTest,ChaquePaquetDeQuestionnerLeMondeDeCe1TientLesConditionsTransversesTest' -Dsurefire.failIfNoSpecifiedTests=false`,
    `java.version=21.0.10` (rapport surefire) : **53 tests, 0 echec** pour le paquet ; **18 tests, 0 echec** pour le transverse.
    Perimetre : ces deux classes, module `adapter-content` et ses dependances. Ce n'est ni un `verify`, ni la porte technique.
  - recompte par script (Python, hors du depot, non commite) : mots par la regle de `PaquetLu.mots`, comparaison stricte de longueur
    du transverse (`TRV-CE1-2`), sur une copie du paquet portant mes corrections : **1 item sur 13** ou la bonne reponse est
    strictement la plus longue (item 5), 13 indices distincts, aucun indice au-dela de 12 mots, aucun enonce au-dela de 14.

## Resume

| Point | Reponse |
|---|---|
| Items | **9 justes, 4 a corriger, 0 refuse en l'etat** (section 2) |
| Cause des items 3 et 6 | **ma regle, pas le developpeur** : `R10-2` laisse *le littoral* et *la ville* ensemble en tache A, alors que mon propre motif de `R10-3` dit qu'une ville au bord de la mer a une plage, un port et des immeubles. Les deux items tiennent la lettre et ont deux reponses defendables (section 3) |
| Autocontrole A.273 | **a** (indice qui donne la reponse) : aucun. **b** (distracteur ecarte sans la competence) : aucun. **c** (source circulaire) : aucune. Deux indices a reprendre pour une autre raison : une methode qui ne discrimine pas (item 9), une syntaxe trop lourde (item 7) |
| Proprietes que je garde seule | toutes **tenues**, sauf l'unicite de la bonne reponse des items 3 et 6 (section 1) |
| Doutes et points de `marc-dev` | section 4 |
| Reouverture d'une formulation | **oui, deux phrases** : `R10-2` (littoral et ville jamais ensemble en A) ; `R10-4` B (aucun element dans l'indice de B hors de l'exemple de *littoral*) (section 7) |
| Cout | **1,0 au bareme a cette ecriture** ; 1,5 avec la seconde lecture des corrections (section 8) |

---

## 1. Ce que j'ai verifie avant les items

Numerotation : l'ordre du fichier, items 1 a 13. Le relais du rapport de `marc-dev` nomme les items A1 a A8 et B1 a B5, mais ses
reperes ne tiennent pas ensemble : *"B1 (sur le littoral)"* et *"famille b (A2 littoral, A3 montagne, A6 ville, A7 campagne)"* ne
correspondent a aucun ordre du fichier. Je lis A1 a A8 = items 1, 3, 4, 6, 7, 9, 11, 13 et B1 a B5 = items 2, 5, 8, 10, 12, ce qui
fait coincider ses doutes sur les indices (A2, A4, A5, A7, A8) et *"pistes de ski en distracteur de B5"*. Sous cette lecture, les
items de famille b sont 3 (littoral), 6 (ville), 9 (montagne), 11 (campagne), et l'item B *sur le littoral* est le 5.

| Propriete que je garde seule | Constat |
|---|---|
| Competence `QLM-CE1-10`, et elle seule | **tenue**. A identifie un paysage par ses elements, B demande l'element absent d'un paysage nomme. Aucun item ne demande *pourquoi* ni ne fait reciter une definition. Descripteur *"Se situer dans l'espace"* : c'est le titre de la section de `competences-ce1.md` qui porte la ligne |
| Exactitude des elements contre `R10-1` | **tenue** element par element ; l'en-tete recopie les deux listes sans ecart |
| Une seule bonne reponse defendable | **non tenue sur les items 3 et 6** (section 3) ; tenue sur les onze autres |
| Indice de A : une methode, pas la scene | **tenue** sur les huit ; aucun indice de A ne decrit ce qu'on voit. Item 9 : methode qui ne discrimine pas (section 2) |
| Explication de *littoral* sans definition | **tenue**. Item 5, *"Sur le littoral, il y a par exemple la mer."* : un lieu et un exemple, aucune phrase *"le littoral, c'est..."* |
| Element hors liste dans un indice | **aucun**. Les noms des indices sont *mot*, *paysage*, *choix*, *énoncé* |
| Adjectif dans un indice hors de l'exemple (`PAY-CE1-2`) | **aucun**. *Seul* (*"un seul paysage"*, items 1, 7, 9 corriges) est une quantite et non une qualite du paysage : il n'est pas vise, et je ne le demande pas a une garde |
| `QMT-CE1-2`, source | **tenue**, et non circulaire : l'en-tete cite mon arbitrage (section 1), dit que le redacteur n'a consulte aucune autre source, et ecarte le test et la story |
| `CYC-1` | **tenu**. `cm1-histoire-geographie-nommer-en-geographie` met les immeubles collectifs en espace urbain, les fermes en espace rural, le ski et les pistes en montagne, le metro en ville, un port dans un village de peche : aucun item ne le contredit. Apres mes corrections, *le port* n'est plus dans aucun item |
| `NRC-CE1-1`, au-dela du texte | **tenu**. `cp-qlm-modes-de-vie` demande *pourquoi* un mode de vie (*le métro roule sous la terre*, *en ville le métro et à la campagne la voiture*) ; aucun item de `-10` ne demande pourquoi, aucun ne rejoue un item de CP |
| `TRV-CE1-6` | **tenu**. Aucun prenom, aucun lieu reel, aucun *tu*, aucun lieu de vie ni vacances ; *on* generique partout |
| `FER-3`, deux operations | **tenue**. En A, on cherche le paysage qui porte tous les elements ; en B, on cherche parmi trois elements celui qui manque a un paysage donne. Ce ne sont pas deux habillages d'une meme question |

## 2. Verdict par item

Joue comme un enfant de CE1 qui sait ce que sont une plage, un sommet, un champ et des immeubles, puis comme un enfant qui se trompe
volontairement : je choisis le distracteur le plus tentant et je regarde si l'indice le remet sur le chemin.

| Item | Enonce, bonne reponse ; distracteurs | Verdict | Motif |
|---|---|---|---|
| 1 | sommet, neige : *la montagne* ; campagne, ville | **juste** | *sommet* tranche ; *neige* sert les deux distracteurs ; l'indice est mon exemple |
| 2 | B montagne : *la mer* ; sommet, forêt | **juste** | couple de la table ; *la forêt* va avec la mer comme avec la montagne, l'enfant doit lire *la montagne* ; indice : une methode |
| 3 | plage, port : *le littoral* ; ville, montagne | **a corriger** (`L1`) | deux reponses defendables : une ville au bord de la mer a une plage et un port (section 3) |
| 4 | champ, rivière : *la campagne* ; montagne, ville | **juste** | *champ* tranche ; *rivière* sert les deux distracteurs |
| 5 | B littoral : *les pistes de ski* ; plage, forêt | **juste** | seul couple que la table admet sous le littoral ; explication par exemple, sans definition ; bonne reponse la plus longue, inevitable (section 4, `D4`) |
| 6 | immeubles, port : *la ville* ; littoral, campagne | **a corriger** (`L2`) | meme motif que l'item 3, dans l'autre sens : des immeubles et un port au bord de la mer, c'est aussi le littoral |
| 7 | mer, village : *le littoral* ; montagne, campagne | **a corriger** (`L3`) | contenu juste ; l'indice *"qu'un seul paysage possède"* est une relative a objet inverse sur un verbe rare : un lecteur de CE1 bute sur la syntaxe au moment ou il a besoin de la methode |
| 8 | B campagne : *le phare* ; blé, village | **juste** | couple de la table ; *le village* va aussi au littoral |
| 9 | pistes de ski, ferme : *la montagne* ; campagne, littoral | **a corriger** (`L4`) | l'indice *"un mot qui ne se trouve pas partout"* designe aussi *la ferme*, qui n'est ni au littoral ni en ville. L'enfant qui part de *la ferme* hesite entre la campagne et la montagne : la methode ne discrimine pas. Seul *la rue* est partout ; c'est le mot *seul* qui fait la methode |
| 10 | B ville : *le sommet* ; immeubles, neige | **juste** | couple de la table ; *la neige* tente l'enfant du sud, et elle est juste en ville |
| 11 | blé, ferme : *la campagne* ; montagne, ville | **juste** | *blé* tranche ; *ferme* sert *la montagne* |
| 12 | B montagne : *le métro* ; pistes de ski, rivière | **juste** | couple de la table ; *les pistes de ski* en distracteur casse le signe de la longueur de l'item 5 |
| 13 | immeubles, rue, neige : *la ville* ; montagne, campagne | **juste** | *immeubles* tranche ; *rue* et *neige* servent les deux distracteurs |

Indices de B (items 2, 8, 10, 12) : *"Lis les trois choix, puis pense au paysage de l'énoncé."*, *"Compare chaque choix avec le paysage
de l'énoncé."*, *"Pour chaque choix, cherche si on le voit dans ce paysage."*, *"Pense à ce qu'on voit dans le paysage de l'énoncé."*.
Quatre methodes. Le dernier a la forme *"Pense à ce que..."* que je refuse quand son objet est la bonne reponse (defaut **a**) : ici son
objet est ce qu'on voit a la montagne, donc les deux distracteurs, et il mene a la reponse par elimination. Il passe.

Les items 2 et 12 ont le meme enonce. Avec quatre lieux et cinq items B, une repetition est inevitable ; leurs propositions different.

## 3. Items 3 et 6 : une regle a moi qui laissait deux reponses

**Correction de mon propre ecrit, d'abord.** J'ai ecrit dans `R10-3` : *"Le littoral et la ville ne sont jamais le couple d'un item B,
dans un sens ni dans l'autre : une ville au bord de la mer a une plage, un port, des immeubles et parfois un metro."* Ce motif vaut
autant pour la tache A, et je ne l'y ai pas transpose. `R10-2` permet un item A qui propose a la fois *le littoral* et *la ville*, et
`marc-dev` en a ecrit deux, conformes a la lettre :

- item 3, *"On voit une plage et un port."*, *le littoral*, avec *la ville* en distracteur : un enfant de Nice ou de Marseille repond
  *la ville*, et il a raison ;
- item 6, *"On voit des immeubles et un port."*, *la ville*, avec *le littoral* en distracteur : des immeubles et un port au bord de
  la mer, c'est le littoral, et l'enfant qui le dit a raison.

Aucun element propre ne separe le littoral de la ville au bord de la mer : la plage, la mer, le phare, les immeubles et le metro y
sont tous. La seule regle qui ferme le defaut est donc sur les propositions : **en tache A, *le littoral* et *la ville* ne sont jamais
proposes ensemble.** Consequence que je dis : `R10-2` exige un distracteur parmi les paysages de chaque element partage, et *le port*
n'est que du littoral et de la ville ; *le port* ne peut donc plus entrer dans un item A, et il n'entrait deja dans aucun item B. Il
reste dans la liste de `R10-1`, sans emploi dans ce paquet.

**Ce n'est pas un poteau deplace** : c'est une erreur d'exactitude, motif de correction a elle seule, dont la cause est ma regle. Je
l'ecris comme une phrase neuve et datee (section 7), pas comme une faute du premier jet.

## 4. Reponse aux doutes et aux points de `marc-dev`

| Repere | Son doute ou son point | Ma reponse |
|---|---|---|
| `D1` | indices des items 3, 6, 11, 13 : une procedure (*"regarde si on y voit tous les mots"*, *"Écarte les paysages où l'on ne voit pas tous les mots"*) proche de la question reformulee | **ce sont des methodes, elles restent.** La question demande quel paysage ; l'indice dit comment le trouver : confronter chaque element a chaque paysage. L'appliquer demande de savoir ou l'on voit une plage ou un port, c'est-a-dire la competence. Aucun ne nomme un element ni ne decrit la scene |
| `D2` | item 7, *"Pense d'abord au mot qu'un seul paysage possède"*, proche de mon exemple | la proximite n'est pas un defaut : c'est la methode de la competence, et le transverse garde les indices distincts. Je corrige l'item pour sa syntaxe, pas pour sa ressemblance (`L3`) |
| `D3` | item 5 donne *la mer* en exemple | **c'est ma derogation, a la lettre** : *la mer* est un propre du littoral qui n'est pas une proposition de l'item |
| `D4` | item 5 : *les pistes de ski*, bonne reponse la plus longue (17 caracteres, 4 mots), non egalisable | **accepte.** Sous le littoral, la table n'admet que *les pistes de ski*, et aucun propre du littoral ni partage du littoral et de la montagne n'atteint 17 caracteres. Mesure : 1 item sur 13 ou la bonne reponse est strictement la plus longue, sous le plafond de 6. Ton choix de mettre *les pistes de ski* en distracteur de l'item 12 est le bon : la longueur n'est pas un signe constant |
| `D5` | les B ont tous la meme structure (propre du paysage nomme, partage, propre d'un autre) : l'enfant pourrait la reperer | **reperer la structure ne donne pas la reponse.** Pour savoir lequel des trois est le propre d'un autre paysage, il faut savoir ou l'on voit chacun : c'est la tache B elle-meme. La structure est ma regle (`N10-5`), voulue pour casser le regroupement |
| `D6` | les items A de famille b (3, 6, 9, 11) s'ecartent par un seul element propre | **c'est la competence**, tu le dis toi-meme. Un distracteur qui ne porte aucun des elements s'ecarte en sachant ou sont les elements ; il n'est pas ecartable sans la competence (defaut **b** d'A.273 absent) |
| `D7` | `TRV-CE1-2` : 1 sur 13, plafond 6 | **confirme par mon recompte**, avant et apres mes corrections |
| `D8` | un seul B *sur le littoral* ; *littoral* lu sans explication dans les items A ou il est propose | **conforme a ma derogation** (`Q8-5`). L'enfant qui ne connait pas le mot peut encore le trouver par elimination des trois autres paysages ; aucun garde-fou ne verifie qu'il a vu l'item 5 avant, et je ne le demande pas |
| `P1` | `G11` elargie (tout element de `R10-1` refuse en indice de A) ; deux sondes *passe* de `theo-archi` refusees, remplacees | **ta lecture est la mienne** (trois points, 5.3). **Une correction du relais** : sur le disque, *"Regarde s'il y a une plage."* est la sonde 35, appelee **refusee** (`PaquetCe1QlmPaysagesTest.java:828`), et c'est juste : *la plage* est un propre d'un paysage non propose. *"Regarde la forêt."* est la sonde 34, refusee. Le relais disait *"qui passe"* |
| `P2` | trou : `G10-2` ne verifie pas que le paysage de la bonne reponse porte chaque element partage nomme (*"un sommet et un port"* passerait) | **confirme a la lecture du code** (`:397-403` : seul le distracteur est cherche dans les paysages du partage). C'est une erreur d'exactitude possible, pas un detail : garde demandee (`T1`). Tes huit items A le respectent |
| `P3` | non garde : en B hors littoral, un indice qui nomme un element hors des propositions | **ma regle le refuse deja dans son motif** (trois points, section 4 : *"la derogation de littoral reste la seule ou l'indice nomme des elements"*), sans l'avoir ecrit dans un texte a reporter. Je l'ecris (section 7) et je le demande a une garde (`T3`). Tes quatre indices B hors littoral ne nomment aucun element |
| `P4` | `FORME_PAYSAGE` suit les formes de `theo-archi` : *campagnes* passerait | **a fermer** (`T4`) |
| `P5` | descripteur *"Se situer dans l'espace"* | **juste** : titre de la section du referentiel qui porte `QLM-CE1-10` |
| `P6` | familles a, b, c, d dans l'en-tete | **justes** et conformes a la forme de `TRV-CE1-2` |
| `P7` | deux items A par paysage | **garde-les** : `FER-1` demande un, deux donnent a chaque paysage un item *a, a* et un item *a, b*, sauf la ville, dont les deux items sont *a, a* apres `L2` : sans le littoral, aucun partage de la ville ne laisse un distracteur de famille b (section 5) |

## 5. Corrections a appliquer par `marc-dev`

Texte exact. L'espace avant *?* et avant *:* est insecable (`TYP-1`). Le reste du paquet ne change pas.

| Repere | Item | Champ | Avant | Apres |
|---|---|---|---|---|
| `L1` | 3 | enonce | *"On voit une plage et un port. Quel est ce paysage ?"* | *"On voit une plage et une falaise. Quel est ce paysage ?"* |
| `L1` | 3 | distracteurs | *["la ville", "la montagne"]* | *["la montagne", "la campagne"]* |
| `L1` | 3 | commentaire | *TRV-CE1-2 : a, b* | inchange : *la montagne* porte *la falaise* (a), *la campagne* ne la porte pas (b) |
| `L2` | 6 | enonce | *"On voit des immeubles et un port. Dans quel paysage est-on ?"* | *"On voit des immeubles et une rivière. Dans quel paysage est-on ?"* |
| `L2` | 6 | distracteurs | *["le littoral", "la campagne"]* | *["la montagne", "la campagne"]* |
| `L2` | 6 | commentaire | *TRV-CE1-2 : a, b* | *TRV-CE1-2 : a, a* |
| `L3` | 7 | indice | *"Pense d'abord au mot qu'un seul paysage possède."* | *"Pense d'abord au mot qui ne va qu'avec un seul paysage."* |
| `L4` | 9 | indice | *"Cherche d'abord un mot qui ne se trouve pas partout."* | *"Un des mots ne va qu'avec un seul paysage : trouve-le."* |
| `L5` | en-tete | tache paysage | - | ajouter la phrase de `R10-2` de la section 7 (le littoral et la ville jamais proposes ensemble, *le port* sans emploi) |

Les indices des items 3 et 6 restent : ils sont des methodes et valent pour les nouveaux enonces (*la montagne* a une falaise et pas
de plage ; *la montagne* et *la campagne* ont une riviere et pas d'immeubles). Les comptes de l'en-tete ne changent pas (8 et 5).

**Laisse au developpeur, signale dans ton rapport** : la ligne `CYC-1` de l'en-tete (ajouter ou non que *le port* n'est plus dans
aucun item) ; l'entree de `QC10` et tout total qu'un test recompte, si mes corrections les font bouger : tu les mets a jour dans le
meme commit, et tu le dis.

## 6. Ce qui revient a `theo-archi` (forme), une ligne par garde

| Repere | Garde | Ajustement |
|---|---|---|
| `T1` | `G10-2`, trou de `marc-dev` (`P2`) | pour chaque element partage nomme par l'enonce, le paysage de la bonne reponse est l'un de ses paysages. Sonde refusee : *"On voit un sommet et un port. Quel est ce paysage ?"*, *la montagne* ; *la ville*, *la campagne*. Passe : l'item 1 |
| `T2` | `G10-2`, phrase neuve de `R10-2` (section 7) | refus d'un item A dont les propositions contiennent *le littoral* et *la ville*. Sondes refusees : les items 3 et 6 du premier jet, tels quels ; passe : l'item 3 corrige |
| `T3` | `G10-4` B, phrase neuve de `R10-4` (section 7, `P3`) | B hors littoral : aucune forme d'un element de `R10-1` dans l'indice. B sur le littoral : aucune forme d'un element de `R10-1` hors des segments de l'exemple. Sonde refusee : *"Lequel ne voit-on pas à la montagne ?"*, indice *"Pense à la neige."* (*la neige* non proposee) ; passe : les items 2 et 5 |
| `T4` | `FORME_PAYSAGE` (`P4`) | ajouter *campagnes* et *littoraux*. Sonde refusee : indice de A *"Compare les campagnes."* |
| `T5` | recherche d'un element dans un indice (`G11`, `T3`) | ajouter *piste*, *pistes* et *ski* aux formes cherchees dans l'indice, sans les admettre dans `FORME_ELEMENT` pour l'enonce et les propositions. Sonde refusee : *"Regarde s'il y a des pistes."* (trois points, section 4) |
| `T6` | aucune garde neuve | *seul* n'est pas un adjectif vise par `PAY-CE1-2` (section 1) : ne pas l'ajouter a une liste |

**Ce dont je reste la seule garde sur `-10`, apres ces ajustements** : qu'un indice soit une methode et non une description de la
scene ou une periphrase par un mot hors des listes (*le sable*, *les vagues*, *l'eau*) ; qu'une methode discrimine vraiment (item 9) ;
qu'un indice se lise au CE1 (item 7) ; qu'une explication de *littoral* bien formee ne porte pas aussi une definition ; un adjectif
dans un indice hors de l'exemple ; un jugement hors des 48 formes ; un lieu reel ; l'exactitude de chaque couple et de chaque scene au
CE1 ; `CYC-1`, `NRC-CE1-1`, `TRV-CE1-6`, `FER-3`. **Aucune n'est couverte par un test.**

## 7. Cette lecture rouvre-t-elle une formulation ?

**Oui, deux phrases, a reporter par `camille-po` dans le critere 9 de `S57-BR-QLM-CE1-10`**, marqueur *[arbitre par `nadia-prof` le
2026-10-27, `lecture-nadia-premier-jet-ce1-qlm-paysages.md`]* :

> **`R10-2`, phrase ajoutee.** En tache A, *le littoral* et *la ville* ne sont jamais proposes ensemble : une ville au bord de la mer a
> une plage, un port et des immeubles, et les deux reponses y sont justes. *Le port*, qui n'est que du littoral et de la ville,
> n'entre donc dans aucun enonce de A.
>
> **`R10-4`, tache B, phrase ajoutee.** L'indice d'un item B ne nomme aucun element de `R10-1`, sauf, dans un item *sur le littoral*,
> le ou les exemples qui suivent *par exemple*.

La premiere corrige mon ecrit (section 3). La seconde ecrit une regle que mon motif des trois points disait deja, sans l'avoir mise
dans un texte a reporter ; le premier jet la tient.

**Ecart constate, a `camille-po`** : la phrase de `R10-4` des trois points (`arbitrage-nadia-trois-points-ce1-qlm-04-06-10.md`, 5.3 :
l'indice de A ne nomme aucun element de `R10-1`, propre ou partage) n'est pas dans le backlog. `grep -n
"trois-points-ce1-qlm-04-06-10" docs/backlog/backlog.md` ne rend rien a `bdfed668`. `marc-dev` l'a appliquee depuis mon fichier, et
`theo-archi` l'a encodee ; la story est en retard sur les deux.

## 8. Cout

**Au bareme** (1,0 par lot, *"lecture avant `verify` et correction qui la suit comprises"*) : **1,0** a cette ecriture. Mes
corrections touchent quatre items et l'en-tete : si `marc-dev` les applique telles qu'ecrites, leur relecture est une seconde lecture,
**0,5 hors marge** (`DEC-0120` A.272), soit **1,5**. Aucun seuil n'est ecrit pour `-10`, et je n'en deduis aucun. Je ne convertis pas
une duree en bareme : aucune regle ne le fait.

## 9. Duree et verifications

**Duree**, mesuree par `date` : debut **12:08:51**, fin **12:31:18**, avant le commit, soit **22 min 27 s**, dont la mesure
des deux tests et les executions du garde-fou des identifiants. Aucun seuil de duree ne s'applique a une lecture.

**Verifications avant commit**, ce fichier present dans l'arbre, base `bdfed668`. Il n'ecrit ni ne renomme aucun identifiant de
condition : `L1`, `T1`, `D1`, `P1` sont des reperes de demande, comme ceux de `marc-dev` et de `theo-archi`.

- `bash outils/mvn-jdk21.sh -pl bootstrap -am test -Dtest=ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest`, telle quelle :
  *"BUILD FAILURE"* sur `recreo-domain`, *"No tests matching pattern "ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest" were
  executed!"*. Le garde-fou n'a pas ete lance ; meme symptome que dans mes fichiers precedents.
- La meme commande avec `-Dsurefire.failIfNoSpecifiedTests=false` : *"Tests run: 10, Failures: 0, Errors: 0, Skipped: 0"*,
  *"BUILD SUCCESS"*. Perimetre : ce seul test, module `bootstrap` et ses dependances ; ni un `verify`, ni la porte technique.
- `.github/scripts/verifier-chemins.sh`, entree standard fermee, ce fichier indexe : *"Aucun chemin de poste de travail ni nom de
  compte dans les fichiers verifies."*
- Aucun autre job de `.github/workflows/ci.yml` lance.

## 10. Routage

| A qui | Quoi |
|---|---|
| `marc-dev` | section 5, `L1` a `L5` |
| `theo-archi` | section 6, `T1` a `T6` |
| `camille-po` | section 7 : deux phrases a reporter, et la phrase des trois points absente du backlog |
| `sofia-sm` | le cout est en section 8 ; la seconde lecture s'inscrit sur la ligne hors marge |

## 11. Ce que ce document ne fait pas

Il ne prononce aucun Done et aucun verdict de lot, n'ecrit aucune condition neuve, n'alloue aucun identifiant, ne modifie ni le
paquet, ni un test, ni le catalogue, ni `DEC`, ni matrice, ni registre, ni backlog. Il ne touche aucun fichier de `-04`, `-05`, `-06`,
`-08`, `-12`.

**Signe** : `nadia-prof`, s57, palier 5, 2026-10-27.
