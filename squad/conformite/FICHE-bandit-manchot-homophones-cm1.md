# Fiche de conformite - bandit-manchot-homophones-cm1

- **Mecanique** : bandit manchot (mecanique generique, partagee avec
  `bandit-manchot-additions-cp` et `bandit-manchot-le-bon-son-cp`).
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/cm1-homophones.yaml`,
  24 items depuis s06 (22 a l'audit de s05, 10 a celui de s04), `type: question`, chacun porteur
  d'un indice. Matiere premiere issue de `contenu-repris/`.
- **Statut global** : **VALIDE** (Done pedagogique accorde le 2026-08-06, reconduit le 2026-08-07,
  **verdict VALIDE sans reserve bloquante le 2026-08-10**). C'est le premier contenu de CM1 du
  produit.
- **Ligne de matrice** : **passe a `couvert-valide`.** Ma condition en six points est **levee** au
  2026-08-10 - motif au verdict du meme jour. C'est la deuxieme ligne du produit a l'atteindre.
  **Arbitrage prealable au developpement du chantier machine a sous du 2026-08-10** (bloc en fin de
  fiche, arbitrage complet dans `FICHE-bandit-manchot-additions-cp.md`) : **deux colonnes validees**,
  158 px chacune, aucune reserve ; **jamais de troisieme colonne vide** ; et l'exigence qui vise ce jeu
  nommement, **aucune colonne ne fait defiler un mot autre que le sien**. Ce n'est pas un Done
  pedagogique et le VALIDE du 2026-08-10 n'est pas touche.
  **Constat neuf du 2026-08-10, mesure** (bloc en fin de fiche) : sur **3 des 24 items**, l'affichage
  de l'indice pousse les **deux** boutons de reponse sous le pli a 360 x 640, et sur l'item 24 il n'en
  reste rien a l'ecran ; **0 sur 24 sans l'indice**. Cause : la longueur de l'enonce a 2 rem, ni le
  nombre de propositions ni les colonnes. **Ce n'est pas un motif** - aucune decision du depot ne fixe
  de hauteur de viewport minimale - **le Done pedagogique reste accorde et la ligne reste
  `couvert-valide`**. A instruire par `camille-po`. Ce constat corrige une inference fausse que j'avais
  faite le meme jour dans `FICHE-bandit-manchot-conjugaison-present-cm1.md`.
  **Chantier machine a sous, Done pedagogique prononce le 2026-08-10 sur l'increment livre (commit
  `10dd74d`) : VALIDE SOUS RESERVE** - verdict transverse complet dans
  `FICHE-bandit-manchot-additions-cp.md`. C'est sur ce jeu que j'ai verifie, image par image sur
  l'animation reelle, qu'**aucune colonne ne montre jamais un mot autre que le sien** : l'exigence qui
  visait ce jeu nommement est tenue. **Le comptage du defaut de pli est corrige : cinq items sur 24, et
  non trois** - mesure en jouant, 41 etats d'item balayes ; et **le passage en colonnes n'a rien gagne
  sur ce defaut**, contrairement au credit que je lui avais accorde. Ni l'un ni l'autre n'est un motif :
  **le Done reste accorde et la ligne reste `couvert-valide`.**
  **Arbitrage prealable au developpement du cabinet visible du 2026-08-11** (bloc en fin de fiche,
  arbitrage complet dans `FICHE-bandit-manchot-additions-cp.md`) : cabinet **compatible avec mon veto
  sous six proprietes verifiables**, **plancher de colonne de 106 px** pour ce paquet a deux
  colonnes, `gap` de 1 rem **refuse comme payeur du cadre**, et **cabinet refuse sur l'ecran de
  retour**. Ce n'est pas un Done pedagogique, il est degrade, et le VALIDE SOUS RESERVE du
  2026-08-10 n'est pas touche. **Le defaut de pli de ce jeu n'est pas rouvert** : mon Done est
  maintenu et je ne deplace pas mes poteaux.
- **Arbitrage prealable de mecanique du 2026-08-12** (`DEC-0023` point 6 : l'ecran intermediaire
  disparait) : ecrit dans `FICHE-bandit-manchot-additions-cp.md`, bloc de validation, section
  "Arbitrage prealable au developpement - la mecanique apres la disparition de l'ecran
  intermediaire". **Il vaut pour ce jeu comme pour les huit du catalogue**, la mecanique etant
  commune : conditions M1 a M13, dont **deux bloquantes** - M4 (le message d'erreur doit etre vu
  sans defiler a 360 x 640) et la clause 1 de M13 (apres une reponse, le focus ne va jamais sur la
  sortie, qui detruit la partie sans confirmation). Ce n'est pas un Done pedagogique, rien n'est
  code.
  **Ce jeu est vise nommement par trois points** : la **reserve A** du 2026-08-12 - **22 de ses 24
  indices** nomment la bonne reponse entre guillemets -, la condition **M2** qui lui interdit donc
  d'afficher son indice sur la question courante en l'etat, et la **reserve C** - ses items n'ont que
  deux propositions, ou se tromper une fois designe la reponse par logique et ou le reessai
  n'apporte rien.
- **Derniere mise a jour** : 2026-08-12

## Competences visees

| Matiere | Niveau | Domaine | Competence | Reference au referentiel |
|---|---|---|---|---|
| francais | CM1 (cycle 3) | Orthographe et grammaire | Distinguer et utiliser correctement les principaux homophones grammaticaux (et/est, son/sont, a/à...) | `squad/programme/francais/competences-cm1.md` |

## Criteres pedagogiques attendus - camille-po

> Section vide a l'ouverture de cette fiche : elle appartient a `camille-po` et je ne la
> pre-remplis pas. Mes reserves ci-dessous sont ecrites pour pouvoir etre converties en criteres
> et en stories sans avoir a me redemander ce que je voulais dire.

---

## Bloc de validation pedagogique - nadia-prof UNIQUEMENT

> Ce bloc n'est jamais pre-rempli ni anticipe par un autre agent.

### Arbitrage prealable au developpement - silhouette du manche, relief et contraste du cabinet (s10), du 2026-08-12

**Ce bloc n'est pas un Done pedagogique et ne touche aucun verdict de cette fiche.** L'arbitrage
complet est ecrit une seule fois, dans `FICHE-bandit-manchot-additions-cp.md`, bloc du 2026-08-12.
**Rien n'est code.** Ce qui concerne **ce jeu-ci**, mesure sur lui aujourd'hui - 29 etats distincts
joues, 360 x 640 :

1. **Le pixel de relief ne coute aucun etat sur ce jeu.** Bas du cabinet reellement observe : 454,
   502, 550, 580, 598, **628**, puis 676, 694, 724, 772. Entre le dernier bas visible (**628**) et le
   premier bas coupe (676) il y a **48 px** : **aucun etat n'a son bas entre 629 et 639**, donc un
   decalage d'1 px n'en fait basculer aucun. C'est ce jeu qui a la marge la plus confortable des deux
   jeux exposes, et ce n'est donc pas sur lui que le pixel se mesure (condition N6, mesuree sur "Le
   bon verbe").
2. **Je corrige mes chiffres du 2026-08-11 sur ce jeu, sans les effacer.** J'avais ecrit un bas de
   cabinet de `y = 450` a `y = 846` ; je remesure aujourd'hui **454** au plus haut et **772** au plus
   bas sur mon echantillon, soit **+4 px** sur la borne basse. L'ecart va dans l'autre sens que celui
   de "Le bon verbe" (-2 px) : ce n'est donc pas un decalage systematique de methode, et **je ne sais
   pas reconstituer la cause**. Ce sont les chiffres d'aujourd'hui qui doivent etre cites.
3. **Le point 2 du verdict ci-dessous n'est pas atteint dans son sens** : 11 etats sur 30 hier, **5 sur
   29** sur mon echantillon d'aujourd'hui, qui n'est pas la meme population (je n'ai pas revu les 30
   etats un par un ce jour-la). Le defaut de `DEC-0016` est le meme, il n'est ni aggrave ni reduit par
   ce lot, et je ne le rouvre pas comme barre.
4. **Rien de ce lot ne touche la largeur des colonnes** : 152 px par colonne, inchange, condition N7.

### Verdict courant - chantier du cabinet (S8-1, S8-2, S8-3), du 2026-08-11 : VALIDE SOUS RESERVE

**Le verdict complet est ecrit une seule fois, dans `FICHE-bandit-manchot-additions-cp.md`, bloc du
2026-08-11** : la mecanique et l'habillage sont communs aux quatre jeux du bandit manchot. **Audit
joue, non degrade** : onze parties de ce jeu, les vingt-quatre items vus, vingt-deux d'entre eux aussi
avec leur indice obtenu par trois echecs consecutifs reels, mesures a 360 x 640. Ce qui concerne **ce
jeu-ci** :

1. **Le budget horizontal est tenu largement.** Colonnes a **152,00 px** contre le plancher de 106 px
   que j'avais fixe pour ce paquet a deux colonnes ; `mais` fait 69,70 px et garde **41,14 px d'air**
   de chaque cote. Aucune troncature, aucune coupure, police a 32 px sur les vingt-quatre items.
   **Mon Done du 2026-08-06 n'est pas atteint par le cabinet.**
2. **Ma reserve 1 de la section 8 de l'autre fiche mord ici, et c'est le jeu ou elle mord le plus
   longtemps.** Le quatrieme cote du cabinet passe sous le pli sur **11 etats sur 30** : 9 des 22
   etats avec indice et 2 des 8 sans. Bas du cabinet mesure de `y = 450` a `y = 846`.
   **Ce n'est pas un motif de refus, et je dis pourquoi plutot que de le laisser deviner** : c'est le
   defaut de pli de `DEC-0016`, que j'ai etabli moi-meme en s07 sur ce jeu precisement (deux boutons a
   `y = 717` et `y = 790`), que j'ai refuse d'opposer a ce Done a l'epoque, et que `DEC-0017` a laisse
   hors du perimetre de ce sprint. Le cabinet l'aggrave de **40 px** - et ma limite de 150 px avait
   ete posee sur "Le bon son", le jeu le plus court, ce qui est mon erreur et non celle d'un
   developpeur. Je ne deplace pas mes poteaux : je maintiens mon Done et j'inscris le chiffre.
3. **Mes reserves de contenu du 2026-08-06 restent ouvertes et inchangees**, aucune n'a ete traitee ce
   sprint et aucune n'etait censee l'etre.

### Arbitrage prealable au developpement - cabinet visible de la machine, du 2026-08-11

**Ce bloc n'est pas un Done pedagogique et il est degrade** : le cabinet n'est pas ecrit. Il ne
touche aucun verdict anterieur. L'arbitrage complet est ecrit une seule fois, dans
`FICHE-bandit-manchot-additions-cp.md`, bloc du 2026-08-11. Ce qui concerne **ce jeu-ci** :

1. **Ce jeu est le cas a deux colonnes, et son plancher de largeur est de 106 px** a 360 px : le mot
   le plus large est `sont`, mesure a 65,5 px, plus les 2 x 20 px de rembourrage declare. Un cadre de
   8 px de chaque cote ramene les colonnes de 160 a **152 px**, donc largement au-dessus.
2. **Deux colonnes restent validees**, comme le 2026-08-10 : l'exercice **est** binaire et deux
   colonnes disent la verite de l'exercice. **Un cabinet ne change rien a cela** : je refuse
   toujours toute colonne vide, muette ou desactivee ajoutee pour atteindre trois, y compris si le
   cadre paraissait mieux rempli a trois. La fidelite de la metaphore n'apprend pas a lire.
3. **C'est ce jeu qui garde le `gap` a l'egalite exacte**, et c'est ici que je le repete : le `gap`
   de 1 rem est **refuse comme payeur du cadre**, sur les deux axes. 16 px declares, 16 px rendus,
   remesures le 2026-08-11. Il n'y a pas 2 px a recuperer.
4. **Le cabinet ne va pas sur l'ecran de retour** ni sur l'ecran de fin de partie.
5. **Le defaut de pli de ce jeu reste hors perimetre**, comme demande : mon Done du 2026-08-10 est
   maintenu, y compris le comptage corrige a cinq items sur 24, et je ne le rouvre pas ici.

### Verdict courant - chantier machine a sous (S7-1 a S7-4), du 2026-08-10

- **Verdict** : **VALIDE SOUS RESERVE.** Done pedagogique du chantier accorde. Le verdict complet et
  son unique reserve sont dans `FICHE-bandit-manchot-additions-cp.md`, bloc "Verdict courant -
  chantier machine a sous". **Il vaut pour ce jeu.** Le Done pedagogique du contenu de ce jeu, accorde
  le 2026-08-10, n'est pas modifie et sa ligne de matrice reste `couvert-valide`.
- **Date** : 2026-08-10. **Commit audite** : `10dd74d`. Audit **joue**, non degrade.
- **Ce que j'ai verifie sur ce jeu precisement** : c'est ici que j'ai fait la verification du point 3
  de ma liste, "aucun autre mot que le sien ne passe dans une colonne", parce que c'est le jeu ou le
  defaut serait le plus grave. Animation reelle figee et deplacee image par image sur l'item `là` /
  `la` : **a aucun instant une colonne ne montre autre chose que son propre mot**, ni un mot vide. Le
  detail est en section 3 du verdict transverse.

| Point | Mesure sur ce jeu |
|---|---|
| colonnes | 2 colonnes de **160 px** a 360 px |
| mot le plus large du paquet | `sont`, **65,5 px** dans 160 px |
| troncature, coupure, compression | **aucune**, sur les 24 items |
| duree du defilement | **778 ms**, decalage de 167 ms entre les deux colonnes |
| ecran immobile | lisible et jouable, voile entierement invisible |

#### Je corrige mon propre comptage du defaut de pli : cinq items sur 24, et non trois

`yanis-qa` confirme sur le code livre que le defaut persiste, et il a raison. En le remesurant, je
trouve **plus** que ce que j'avais compte. Balayage de 41 etats d'item distincts sur huit parties, a
360 x 640, indice affiche :

| Enonce (debut) | Lignes | Bas de l'indice | Boites de reponse | Etat |
|---|---|---|---|---|
| "Après cette longue randonnée en montagne..." | 6 | 705 | **717-791** | les deux boutons entierement sous le pli |
| "Tom cherchait sa gomme depuis le début du cours..." | 5 | 641 | 653-727 | les deux entierement sous le pli |
| "Après la récréation, tous les élèves ___ rangent..." | 4 | 579 | 591-665 | les deux mots sous le pli |
| "Léa a perdu son livre depuis lundi, mais elle ___..." | 4 | 579 | 591-665 | les deux mots sous le pli |
| "Même si le ciel est bien dégagé ce matin, il ___..." | 4 | 579 | 591-665 | les deux mots sous le pli |
| les 19 autres items | 1 a 3 | - | - | au-dessus du pli |
| les 24 items **sans** l'indice | - | - | - | **aucun** en defaut |

J'avais ecrit trois items le 2026-08-10, en mesurant depuis les enonces. **Il y en a cinq**, mesures en
jouant. C'est une correction d'un de mes propres constats, dans le sens de la gravite.

**Et je corrige aussi un credit que j'avais accorde au chantier a tort.** J'avais ecrit que la mise en
page en colonnes "rend le pli confortable la ou il etait juste". C'est faux ici : les colonnes ne
remontent pas la premiere rangee de boutons, dont la position ne depend que de la hauteur de l'enonce
et de l'indice. Mesure sur l'item le plus long : les deux boutons sont a **717-791** sur le code livre,
exactement la ou j'avais releve 717-790 avant le chantier. **Le passage en colonnes n'a rien gagne sur
ce defaut**, et personne ne doit croire qu'il l'a traite. Le detail et la part que je declare degradee
sur cette comparaison sont en section 10, point D, du verdict transverse.

**Rien de tout cela n'est un motif, et je ne change pas d'avis.** J'avais choisi de ne pas l'opposer a
un jeu dont j'avais accorde le Done, parce qu'aucune decision du depot ne fixait de hauteur de
viewport et que je ne fabrique pas une barre en cours d'audit. `DEC-0016` fixe maintenant la regle, ce
qui est le bon endroit pour elle - mais elle est posterieure a l'increment, et l'opposer a ce jeu
aujourd'hui serait exactement le deplacement de poteaux que je refuse. **Le Done pedagogique reste
accorde, la ligne reste `couvert-valide`, et le chiffre corrige part chez `camille-po`.**

- **Ligne de matrice** : **inchangee**, `couvert-valide`. Ce chantier ne porte aucune competence.

---

### Verdict - premier audit, contenu livre en s04

- **Verdict** : **VALIDE SOUS RESERVE.** Done pedagogique accorde. Ce jeu peut etre mis devant
  une classe de CM1.
- **Date** : 2026-08-06.
- **Commit audite** : `c801eb8` (`HEAD` de `round-two`).
- **Moyens** : audit **joue**, non degrade. Fat jar construit et lance en local (JDK 21.0.10),
  parties completes jouees en repondant juste et en me trompant volontairement, y compris jusqu'a
  l'ecran de fin. Rendu reel mesure au navigateur sans tete.

#### 1. Exactitude du contenu - irreprochable, verifiee item par item

Les dix phrases, leur bonne reponse et leur distracteur, un par un :

| Enonce | Attendu | Distracteur | Verdict |
|---|---|---|---|
| "Il ___ mangé une pomme." | `a` | `à` | juste, auxiliaire avoir |
| "Je vais ___ l'école." | `à` | `a` | juste, preposition |
| "Papa ___ maman font la cuisine." | `et` | `est` | juste, "font" au pluriel interdit "est" |
| "Le chat ___ noir." | `est` | `et` | juste |
| "Les enfants ___ partis." | `sont` | `son` | juste |
| "Il prend ___ cartable." | `son` | `sont` | juste |
| "Ils ___ un chien." | `ont` | `on` | juste |
| "___ joue dans la cour." | `On` | `Ont` | juste, majuscule correcte en tete de phrase |
| "___ vas-tu ?" | `Où` | `Ou` | juste |
| "Tu veux du thé ___ du café ?" | `ou` | `où` | juste |

**Zero erreur factuelle.** Chaque phrase n'admet qu'une seule lecture grammaticalement correcte :
il n'y a pas d'item ou les deux propositions passeraient. Les cinq couples annonces (a/à, et/est,
on/ont, son/sont, ou/où) sont tous presents, chacun deux fois, une fois dans chaque sens - c'est
la bonne facon de construire ce type d'exercice, parce qu'elle interdit a l'enfant de retenir
"c'est toujours celui avec l'accent".

**Les distracteurs sont plausibles par construction** : le distracteur est l'autre membre du
couple d'homophones. Il n'y a donc aucun distracteur absurde possible, et aucune devinette. C'est
le point fort de ce paquet.

#### 2. Rattachement au referentiel - exact

La ligne du referentiel CM1 est "Distinguer et utiliser correctement les principaux homophones
grammaticaux (`et/est`, `son/sont`, `a/à`...)". C'est bien cette competence-la que l'increment
travaille, pas une competence voisine plus facile. Rien a redire.

#### 3. Niveau et lisibilite - conformes au CM1

Phrases de cinq a sept mots, vocabulaire entierement courant, aucune tournure obscure. Un enfant
de CM1 lit cela sans effort, ce qui est ce qu'on veut : l'effort doit porter sur le choix du mot,
pas sur le dechiffrage de la phrase. La consigne "Quel mot complète la phrase ?" fait cinq mots
et est exacte. Le trou est marque par trois traits soulignes, bien visibles a 32 px gras.

#### 4. Biais et ton

Pas de condescendance, pas d'infantilisation, aucun classement ni comparaison a d'autres enfants.
Deux points mineurs, portes en reserve et non en motif : voir reserves 3 et 4.

#### 5. Ce que ce verdict ne dit pas

Il ne porte que sur le contenu `cm1-homophones` et sur les libelles de ce jeu. **La saillance des
boutons sur l'ecran de retour est refusee separement**, au titre du chantier Bootstrap, dans
`FICHE-bandit-manchot-additions-cp.md`. Ce refus concerne l'habillage commun aux trois jeux, donc
celui-ci aussi ; il ne remet pas en cause le Done pedagogique accorde ici sur le contenu.

#### Reserves - a convertir en stories par camille-po

1. **Dix items pour une partie de dix manches : chaque partie est identique.** L'enfant voit
   l'integralite du paquet a chaque fois, seul l'ordre change. Des la deuxieme partie il ne
   reste plus rien a apprendre, il ne reste qu'a se souvenir. Il faut plus d'items que de manches.
2. **Les phrases sont trop courtes pour travailler la competence a la profondeur du CM1.** Dans
   neuf items sur dix, le sujet est colle au trou ("Le chat ___ noir.", "Ils ___ un chien."), ce
   qui rend le choix quasi automatique. Le seul item qui demande vraiment de raisonner est
   "Papa ___ maman font la cuisine.", parce qu'il faut aller chercher "font" apres le trou. C'est
   le modele a suivre : la competence ne se travaille que quand la phrase oblige a regarder
   ailleurs que juste avant le trou. En l'etat, l'exercice est juste, mais il est de niveau CE1 ou
   CE2 plutot que CM1 - consolidation, pas apprentissage.
3. **Aucun sujet feminin dans tout le paquet.** "Il" apparait deux fois, "Ils" une fois ; "elle"
   et "elles" n'apparaissent jamais. Sur dix phrases dans un paquet de grammaire, c'est un
   desequilibre evitable, d'autant que le remplacement est gratuit ("Elle ___ mangé une pomme.").
4. **"Papa ___ maman font la cuisine." presuppose une configuration familiale.** Une occurrence
   sur dix, et par ailleurs non stereotypee sur les roles puisque les deux parents cuisinent
   ensemble - ce qui est a porter au credit de l'item. Je le signale sans en faire un motif :
   tous les enfants n'ont pas un papa et une maman. "Léa ___ Tom font la cuisine." dirait la
   meme chose grammaticalement.
5. **Deux propositions donnent une chance sur deux au hasard.** C'est inherent a l'exercice - on
   ne peut pas proposer "et" comme distracteur de "a/à" - et je ne le compte donc pas comme un
   defaut de conception. Mais il faut en tirer la consequence : **le score de ce jeu ne mesure
   pas grand-chose**, un enfant qui repond au hasard finit a 5 sur 10. A garder en tete le jour ou
   le score servira a autre chose qu'a s'encourager.
6. **Aucune aide n'est disponible dans ce jeu.** Meme constat structurel que pour
   `bandit-manchot-le-bon-son-cp`, motif 5 de sa fiche : un paquet `type: question` ne peut pas
   porter d'indice, parce que `QuestionContentSourceFactory` construit chaque `Question` sans
   champ `indice`. C'est moins grave ici qu'au CP - un enfant de CM1 est plus autonome, et
   l'increment reste utilisable sans - donc c'est une reserve et non un motif. Mais quand le
   dispositif sera ouvert aux paquets `question`, ce jeu doit en beneficier : "Remplace par
   *avait* pour t'aider." est exactement le genre d'indice qui apprend quelque chose.
7. **L'accueil ne dit pas a quel niveau s'adresse le jeu.** "Le bon petit mot" ne porte aucune
   mention de CM1 alors que "Calcul CP" porte la sienne. Question produit pour `camille-po`.
8. **"Tu veux du thé ___ du café ?"** propose a un enfant de choisir entre deux boissons
   d'adulte. Ce n'est pas grave et ce n'est pas faux, mais dans un paquet de dix phrases on peut
   trouver un choix qui parle a un enfant de neuf ans.

#### Suites hors de ma main

- `squad/programme/MATRICE-couverture.md`, ligne
  `francais | CM1 | Orthographe et grammaire | Distinguer et utiliser correctement les principaux
  homophones grammaticaux` : **reste `en cours`, ne passe pas encore a `couvert-valide`.**
  Motif, et il est franc : le Done pedagogique est accorde, mais le Done et la matrice ne disent
  pas la meme chose - le Done porte sur l'increment, la matrice porte sur la couverture d'une
  competence. La competence dit "**les principaux** homophones grammaticaux" et le referentiel
  laisse la liste ouverte par des points de suspension. Le paquet en couvre cinq : a/à, et/est,
  on/ont, son/sont, ou/où. **Manquent, parmi ceux qu'un CM1 rencontre couramment : ce/se, ces/ses,
  la/là/l'a, mais/mes, peu/peut.** Cinq couples sur une dizaine, ce n'est pas "les principaux".
  **Condition pour que je fasse passer cette ligne a `couvert-valide` : ces cinq couples ajoutes
  au paquet, avec la reserve 2 traitee dans le meme geste** (des phrases qui obligent a regarder
  ailleurs que juste avant le trou), parce qu'ajouter cinq couples faciles ne ferait qu'allonger
  un exercice de CE2. Je n'ai pas edite cette ligne, elle appartient a `camille-po`.
  **Addendum du 2026-08-06** : cette condition est precisee, sans etre rouverte, par l'arbitrage
  ci-dessous ("Arbitrage du 2026-08-06"). C'est desormais ce texte-la qui dit ce que j'attends.

---

### Arbitrage du 2026-08-06 - la famille `la/là/l'a` et le contour de "les principaux"

- **Nature** : ce n'est pas un audit et ce n'est pas un verdict. Rien n'a ete livre depuis le
  Done pedagogique ci-dessus, qui reste acquis tel quel. Je tranche ici **ma propre condition**,
  saisie par `theo-archi` via `sofia-sm` sur son avis de faisabilite de s05 (S5-6).
- **Ce que j'ai relu** : `cm1-homophones.yaml` (inchange, 10 items), la ligne du referentiel CM1,
  la section S5-6 du backlog et la section S5-6 de l'avis de faisabilite.

#### 1. D'abord, une confusion a lever : ce n'est pas ma propriete qui casse

`theo-archi` ecrit que le triplet casse "la propriete que `nadia-prof` porte au credit du paquet".
Je relis ce que j'ai ecrit, et ce n'est pas tout a fait cela. La propriete que j'ai creditee est :
**toute proposition affichee appartient a la meme famille d'homophones que la bonne reponse**,
donc aucun distracteur absurde et aucune devinette. Trois propositions tirees de `la/là/l'a`
respectent cette propriete integralement.

Ce qui casse, c'est `distracteurs.hasSize(1)`, c'est-a-dire l'**operationnalisation** de ma
propriete dans `PaquetsFrancaisS04Test`, qui est plus etroite que ce que j'ai valide. C'est une
distinction qui compte pour la suite : le jour ou un paquet aura besoin de trois propositions de
la meme famille, je ne le refuserai pas au motif de cette propriete-la. Et j'ajoute que trois
propositions au lieu de deux vont dans le sens de ma reserve 5, pas contre elle : une chance sur
trois mesure un peu mieux qu'une chance sur deux.

Je ne choisis donc pas ci-dessous pour sauver un test. Je choisis pour la raison qui suit.

#### 2. Ce que je retiens : `la/là/l'a` entre en deux paires, pivotant sur `la`

**Decision : deux paires d'items, `la`/`là` et `la`/`l'a`. La paire `là`/`l'a` n'est pas
demandee. Un seul distracteur par item, comme partout ailleurs dans le paquet.**

Le motif est pedagogique et il tient en deux points.

**Ce sont deux apprentissages differents, pas un.** `la` contre `là` est une affaire d'accent, et
le test que l'enfant applique est un test de sens : "est-ce que je peux dire *ici* ?". C'est
exactement la meme forme que `ou/où`, deja dans le paquet et deja validee. `la` contre `l'a` est
une tout autre affaire : c'est un verbe cache derriere une elision, et le test est un test de
temps, "est-ce que je peux dire *l'avait* ?". C'est la forme de `on/ont`, de `son/sont`, de
`a/à`. Un item a trois propositions demande a l'enfant de tenir les deux tests en meme temps, et
de choisir lequel appliquer avant de l'appliquer. Au CM1, c'est une marche de plus que la
competence n'en demande, et elle est ajoutee au moment precis ou l'enfant decouvre la famille.
On enseigne une regle a la fois ; on ne les fusionne qu'une fois chacune acquise.

**`la` est le pivot des fautes reelles.** Ce qu'un enfant de CM1 ecrit de travers, c'est `la` a la
place de `là` (accent oublie) et `la` a la place de `l'a` (apostrophe et verbe oublies). Les deux
fautes ont le meme mot fautif. La paire `là`/`l'a` ne correspond a aucune hesitation courante :
un enfant qui ecrit `là` pour `l'a` a d'abord rate `l'a`. Deux items pivotant sur `la` couvrent
donc les deux erreurs qui existent ; le troisieme appariement travaillerait une erreur qui n'en
est pas une.

**Ce que cela coute, et je l'assume** : deux items au lieu d'un, quatre une fois le point 5
applique. C'est plus de contenu a ecrire, pas moins. Ce n'est pas le choix commode.

**Consequence pour `PaquetsFrancaisS04Test`** : la famille se declare comme deux couples,
`la`/`là` et `la`/`l'a`. Le paquet compte alors onze couples pour dix familles. Que le test dise
"couple" la ou je dis "paire d'un meme ensemble d'homophones" ne me gene pas ; qu'il continue
d'exiger que le distracteur soit un homophone de la bonne reponse, si.

#### 3. Les quatre autres couples : deux d'entre eux cachent bien quelque chose

Aucun des quatre n'est un triplet dans la forme que prendra le paquet. Mais deux portent un
risque du meme ordre, et il vaut mieux le nommer maintenant qu'au moment de l'audit.

**`ces`/`ses` - le plus dangereux des cinq, et de loin.** Ce n'est pas un probleme de troisieme
membre, c'est un probleme d'ambiguite : dans la plupart des phrases, **les deux reponses sont
grammaticalement correctes**. "Il regarde ___ photos." accepte `ces` comme `ses`. Or la propriete
que j'ai creditee au paquet est que chaque phrase n'admet qu'une seule lecture. Un item
`ces`/`ses` mal ecrit la detruit vraiment, et il apprend a l'enfant que la reponse se devine.
La regle a respecter : pour `ces`, la phrase porte une designation explicite (`là`, `là-bas`,
`ici`, une exclamation qui montre) ; pour `ses`, le possesseur est nomme dans la phrase.
Je dis franchement la limite : sur ce couple, la levee d'ambiguite est **pragmatique et non
grammaticale**. Le seuil que j'appliquerai a l'audit : un adulte doit trouver l'autre lecture
franchement incongrue, pas seulement moins probable. J'auditerai ces deux items un par un et je
refuserai tout item ou les deux lectures passent.

**`peu`/`peut` et `mais`/`mes` - la famille a des membres absents des propositions.**
`peu`/`peut` vit a cote de `peux` ; `mais`/`mes` vit a cote de `mets`, `met` et `m'est`. Une
phrase dont la vraie reponse est un de ces mots-la, avec deux propositions qui ne le contiennent
pas, apprend une fausse alternative a l'enfant : elle lui fait choisir entre deux mots faux.
Regles : **aucun sujet `je` ni `tu` dans un item `peu`/`peut`** ("Tu ___ venir" appelle `peux`) ;
**aucun contexte du verbe *mettre*** dans un item `mais`/`mes`.

**`ce`/`se` - le plus propre des quatre.** Une seule prudence : eviter les tournures ou les deux
existent, `ce sera` et `se sera` etant toutes deux du francais correct ("Il ___ sera trompé").

**Phrases modeles**, pour lever toute discussion sur ce que je demande. Elles illustrent le
couple et la levee d'ambiguite, **pas** la profondeur exigee par ma reserve 2 : la moitie des
items neufs doit en plus obliger a regarder ailleurs que juste avant le trou.

| Famille | Un sens | L'autre sens |
|---|---|---|
| `la`/`là` | "Pose ton cartable ___, près de la porte." -> `là` | "Léa cherche ___ règle dans sa trousse." -> `la` |
| `la`/`l'a` | "Tom a perdu sa gomme, puis il ___ retrouvée." -> `l'a` | "Il ___ regarde souvent." -> `la` |
| `ce`/`se` | "___ matin, il pleut." -> `Ce` | "Le chat ___ cache sous le lit." -> `se` |
| `ces`/`ses` | "Regarde ___ oiseaux là-bas !" -> `ces` | "Il range son cahier et ___ crayons." -> `ses` |
| `mais`/`mes` | "Il fait froid ___ le soleil brille." -> `mais` | "J'ai perdu ___ clés." -> `mes` |
| `peu`/`peut` | "Il ___ pleuvoir cet après-midi." -> `peut` | "Il reste ___ de pain." -> `peu` |

#### 4. Ce que "les principaux homophones" recouvre pour un CM1

Le referentiel du depot ecrit "les principaux homophones grammaticaux (`et/est`, `son/sont`,
`a/à`...)" et laisse la liste ouverte par des points de suspension. `camille-po` doit ecrire un
critere, elle ne peut pas ecrire des points de suspension. Voici ce que je mets derriere le mot.

**Ma reference, dite honnetement.** Les programmes de cycle 3 traitent la distinction des
homophones grammaticaux dans l'orthographe grammaticale et **ne donnent pas de liste fermee**. Il
n'y a donc pas de liste officielle a citer, et je n'en inventerai pas une. Ce qui suit est la
liste sur laquelle convergent les progressions de CM1 telles que je les ai pratiquees en classe :
c'est un jugement professionnel, et il s'assume comme tel.

**Le critere, qui vaut mieux que la liste**, parce qu'il permet de trancher un cas non prevu. Un
couple est "principal" au CM1 quand les quatre conditions sont reunies :

1. les deux membres sont des **mots grammaticaux** (determinant, pronom, preposition, conjonction,
   forme verbale d'auxiliaire), pas des mots de vocabulaire ;
2. la confusion est **frequente dans les ecrits d'enfants** de cet age ;
3. il existe une **manipulation que l'enfant peut appliquer seul** (remplacer par un autre mot,
   changer le temps, changer le nombre) ;
4. la distinction est necessaire **pour ecrire**, pas seulement pour lire.

**La liste qui en decoule au CM1**, dix familles : `a/à`, `et/est`, `son/sont`, `on/ont`, `ou/où`,
`ce/se`, `ces/ses`, `la/là/l'a`, `mais/mes`, `peu/peut`. Ce sont les cinq deja couverts plus les
cinq de ma condition. C'est ce que `camille-po` peut ecrire en critere, au mot pres.

**Ce que la liste laisse dehors, et que je nomme plutot que de le taire :**

- **`c'est`/`s'est` (et `sait`/`sais`) satisfait mes quatre criteres.** C'est meme l'une des
  fautes les plus frequentes au CM1. **Je ne l'ajoute pourtant pas a ma condition**, et le motif
  n'est pas pedagogique : j'ai ecrit ma condition, l'equipe s'est engagee dessus, et une condition
  qui grossit apres avoir ete engagee n'est plus une condition, c'est un deplacement de but. Le
  `couvert-valide` que je prononcerai portera donc sur dix familles, avec cette limite **nommee et
  non cachee**. `c'est`/`s'est` est le premier candidat d'une story ulterieure, et je le signale
  des maintenant a `camille-po` pour qu'elle le range dans le backlog des sprints suivants.
- **`leur`/`leurs`** : homophones, mais ce qui se joue la est un accord en nombre, pas une
  discrimination de nature. Cela se travaille avec les accords du groupe nominal, ligne voisine du
  referentiel. Hors de cette competence.
- **`sans`/`s'en`, `dans`/`d'en`, `quand`/`quant`/`qu'en`** : criteres 1 et 3 satisfaits, critere 2
  non - ces confusions apparaissent surtout au CM2 et au college. Hors de "principaux" au CM1.

**Point pour `camille-po` via `sofia-sm`** : le referentiel `competences-cm1.md` gagnerait a porter
cette liste a la place de ses points de suspension. Je ne l'edite pas, ce fichier n'est pas le
mien - mais tant qu'il reste ouvert, chaque audit rejouera cet arbitrage.

#### 5. "Dans les deux sens" : je la reprends a mon compte, elle devient bloquante

`camille-po` a ajoute a S5-6 l'exigence que chaque couple soit represente dans les deux sens, en
prenant soin de l'attribuer explicitement a elle-meme et de la declarer non bloquante pour ma
condition. L'attribution etait juste : je ne l'avais pas demandee. **Je la reprends, et elle
devient bloquante pour `couvert-valide`.**

Le motif, et il est de couverture, pas de style. La competence dit "**distinguer et utiliser**
correctement". Un couple present dans un seul sens ne fait jamais produire le second membre :
si `peut` n'est jamais la bonne reponse, l'enfant n'a jamais eu a choisir `peut`, il a seulement
appris a reconnaitre `peu`. La moitie de la competence n'est pas travaillee, donc pas couverte.
C'est exactement le registre de la ligne de matrice, et c'est pourquoi cela ne peut pas rester une
preference de forme. J'avais d'ailleurs deja porte ce point au credit du paquet dans mon verdict,
en notant que chaque couple y figure "une fois dans chaque sens" ; il serait incoherent de saluer
la propriete sur cinq couples et de l'abandonner sur les cinq suivants.

**Ce que cela veut dire precisement**, y compris pour la famille a trois membres, puisque
`theo-archi` demande que ce soit ecrit sans ambiguite : l'exigence porte sur les **paires**, pas
sur les familles. Avec la decision du point 2, `la/là/l'a` compte pour deux paires, et chacune
doit apparaitre dans ses deux sens - soit quatre items, ou `la` est la bonne reponse deux fois.
C'est comptable sur les bonnes reponses du paquet, donc automatisable, comme `theo-archi` le
demandait.

**Ce que cela coute** : douze items neufs au lieu de dix (cinq familles, six paires, deux sens).
Et c'est un effet secondaire heureux : vingt-deux items pour dix manches, cela entame serieusement
ma reserve 1 - l'enfant ne verra plus l'integralite du paquet a chaque partie.

#### 6. Recapitulatif de ma condition, dans sa forme desormais definitive

Pour que je fasse passer la ligne francais CM1 de `MATRICE-couverture.md` a `couvert-valide` :

1. les cinq familles manquantes sont ajoutees : `ce/se`, `ces/ses`, `la/là/l'a`, `mais/mes`,
   `peu/peut` ;
2. `la/là/l'a` entre en deux paires pivotant sur `la` (`la`/`là` et `la`/`l'a`), un seul
   distracteur par item ;
3. chaque paire, ancienne et nouvelle, est representee dans les deux sens ;
4. aucun item n'admet deux lectures correctes - vigilance particuliere sur `ces`/`ses`, et aucun
   item dont la vraie reponse serait un membre absent des propositions (`peux`, `mets`, `met`) ;
5. au moins la moitie des items neufs oblige a regarder ailleurs que juste avant le trou
   (reserve 2, inchangee) ;
6. et j'aurai joue l'increment avant de le dire.

Rien d'autre. Ce qui n'est pas dans cette liste - dont `c'est`/`s'est` - ne bloque pas
`couvert-valide` et ne pourra pas m'etre oppose comme l'ayant bloque. Je n'ai edite ni le backlog,
ni la matrice, ni le referentiel : ce sont les fichiers de `camille-po`.

---

### Verdict du sprint s05 - audit du 2026-08-07, le paquet a 22 items

- **Verdict sur l'increment** : **VALIDE SOUS RESERVE.** Le Done pedagogique accorde en s04 est
  reconduit et elargi aux douze items neufs et aux vingt-deux indices. Ce jeu peut etre mis devant
  une classe de CM1, et il est nettement meilleur qu'a mon audit precedent.
- **Verdict sur la ligne de matrice** : **la condition n'est PAS levee. La ligne
  `francais | CM1 | Orthographe et grammaire | ... homophones grammaticaux` reste `en cours`.**
  Deux points precis manquent, section 3.
- **Commit audite** : `c0b5241` (`HEAD` de `round-two`).
- **Moyens** : audit **joue**, non degrade. `mvn -B clean package` depuis un arbre propre sous un
  `JAVA_HOME` sur le JDK 21 reel du poste (`java -version` : `21.0.10`), jar relance en local sur
  un port distinct de celui de l'utilisatrice. Parties completes jouees : une en repondant juste
  de bout en bout (score 10 sur 10), une en me trompant a chaque manche, une en enchainant les
  echecs pour faire venir l'aide. Ecrans mesures au navigateur sans tete en 1280 x 900 et en
  360 x 640, dans les memes conditions que mes refus precedents.

#### 1. Le defaut signale par l'utilisatrice est corrige - verifie en jeu

Les phrases n'affichent plus la reponse. Les vingt-deux enonces portent un trou `___`, et je l'ai
vu a l'ecran manche apres manche, pas seulement dans le fichier. C'etait le defaut le plus grave
possible pour ce jeu : un exercice a trou sans trou ne demande rien a l'enfant.

Effet secondaire mesure et reel : **une partie tire dix items distincts sur vingt-deux**, et deux
parties consecutives ne presentent pas les memes phrases. **Ma reserve 1 est levee** - l'enfant ne
voit plus l'integralite du paquet a chaque partie, il lui reste quelque chose a apprendre a la
deuxieme.

#### 2. Ma condition en six points, verifiee point par point en jouant

| Point de ma condition | Verdict |
|---|---|
| 1. Les cinq familles manquantes sont ajoutees (`ce/se`, `ces/ses`, `la/là/l'a`, `mais/mes`, `peu/peut`) | **tenu** - les cinq sont la, jouees a l'ecran |
| 2. `la/là/l'a` en deux paires pivotant sur `la`, un seul distracteur par item | **tenu** - `la`/`là` et `la`/`l'a`, jamais `là`/`l'a`, deux propositions par item partout |
| 3. Chaque paire, ancienne et nouvelle, dans les deux sens | **tenu** - onze paires, vingt-deux items, `la` bonne reponse deux fois |
| 4. Aucun item n'admet deux lectures correctes | **tenu** - detail ci-dessous |
| 5. Au moins la moitie des items neufs oblige a regarder ailleurs que juste avant le trou | **NON TENU** - 5 items sur 12, il en faut 6 |
| 6. J'aurai joue l'increment | **tenu** |

##### Point 4 - mes trois risques nommes, leves un par un

**`ces`/`ses`, celui que j'annoncais comme le plus dangereux : les deux items passent mon seuil.**
Je les ai regardes un par un, en cherchant l'autre lecture plutot qu'en la supposant absente.

- "Regarde ___ oiseaux là-bas, sur la branche !" -> `ces`. La designation explicite que j'exigeais
  est presente deux fois, par `là-bas` et par l'exclamation. Et `ses` n'est licencie par rien :
  aucun possesseur n'est nomme dans la phrase, donc le possessif y pend dans le vide. Un adulte
  trouve l'autre lecture incongrue, pas seulement moins probable. C'est mon seuil, il est atteint.
- "Léa range son cahier et ___ crayons avant de partir." -> `ses`. Le possesseur est nomme
  (`Léa`), et le parallele avec `son cahier` verrouille la coordination. `ces` demanderait un
  geste de designation que rien ne porte. Tenu.

Les deux items respectent ma regle **dans les deux sens** : `ces` n'est retenu que la ou il y a
designation, `ses` que la ou il y a un possesseur nomme. C'est exactement ce que je demandais et
c'est le point que je craignais le plus.

**`peu`/`peut` : aucun sujet `je` ni `tu`.** Les deux items ont `il` pour sujet ("Il reste ___ de
pain...", "...il ___ encore pleuvoir..."). `peux` n'est jamais la vraie reponse cachee. Tenu.

**`mais`/`mes` : aucun contexte du verbe *mettre*.** Les deux items emploient `brille` et `rangé`.
Ni `mets`, ni `met`, ni `m'est` ne sont la vraie reponse d'un item. Tenu.

J'ajoute les deux autres familles, verifiees au meme titre : `ce`/`se` evite le piege `ce sera` /
`se sera` que j'avais nomme ; `la`/`l'a` distingue proprement `range` (present) de `retrouvé`
(participe). **Zero erreur factuelle sur les vingt-deux enonces.**

##### Point 5 - l'ecart signale par marc-dev : son motif est recevable a moitie, ma condition tient

`marc-dev` me signale de lui-meme que seuls 4 de ses 12 items exigent de regarder au-dela du mot
voisin du trou. **Qu'il le signale plutot que de le masquer est ce qui permet de trancher au lieu
de decouvrir**, et je le porte a son credit.

Mon propre comptage en donne 5 et non 4 - je compte "Pose ton cartable ___, à côté du mien." parce
que le voisin de droite est une virgule et que la levee vient de `à côté du mien`. Que ce soit 4
ou 5 ne change rien : **il en faut 6.**

**Son motif est vrai la ou il le dit.** Pour `ce`/`se`, et pour le versant determinant de
`mais`/`mes` et de `la`/`là`, la discrimination *est* determinant contre verbe, ou determinant
contre conjonction : le diagnostic porte sur le mot voisin par nature de la categorie
grammaticale. Les indices l'ecrivent d'ailleurs eux-memes ("Le mot qui suit le trou est un nom
('cahier')"). Forcer ces items-la a regarder plus loin fabriquerait de la complexite postiche, et
il a raison de dire que ce serait courir vers l'ambiguite, c'est-a-dire vers mon point 4, qui est
bloquant. **Je ne lui demanderai pas cela.**

**Mais son motif ne couvre pas tout le paquet, et il le prouve lui-meme.** Quatre de ses items -
"...elle ___ enfin retrouvé...", "Regarde ___ oiseaux là-bas...", "...son cahier et ___
crayons...", "...il ___ encore pleuvoir..." - obligent bel et bien a regarder plus loin, et ils
sont excellents. Ils sortent de `la/l'a`, de `ces/ses` et de `peu/peut`, c'est-a-dire des familles
ou la place du mot decisif est libre. **Ce qui est possible trois fois l'est cinq fois.** Le motif
"c'est intrinsequement local" est donc vrai pour certaines familles et pas pour celles-la.

**Je ne leve donc pas le point 5, et je le retrecis pour qu'il soit tenable** : je ne demande pas
que six items quelconques deviennent non locaux, je demande **deux items neufs de plus dont le mot
decisif n'est pas voisin du trou, pris dans `la`/`l'a`, `ces`/`ses` ou `peu`/`peut`** - les trois
familles ou marc-dev a deja montre que cela se fait sans forcer. `ce`/`se` et le versant
determinant de `mais`/`mes` sont explicitement dispenses. Cela porte le paquet a 24 items, ce qui
va dans le sens de ma reserve 1.

Je precise pourquoi je ne cede pas alors que l'ecart est d'un item ou deux. Ma condition a ete
ecrite avant le sprint, l'equipe s'est engagee dessus, et j'ai refuse pour ce motif d'y ajouter
`c'est`/`s'est` en cours de route. Une condition qui ne grossit pas apres coup ne retrecit pas non
plus parce que ce serait plus rapide. Ce que je fais en revanche, c'est nommer exactement le
reste a faire pour qu'il soit petit, borne et actionnable sans me redemander ce que je voulais.

#### 3. Ce qui manque pour `couvert-valide`, exactement, et rien de plus

Deux points. Ils sont les seuls.

1. **Deux items neufs de plus dont le mot decisif n'est pas voisin du trou**, pris dans
   `la`/`l'a`, `ces`/`ses` ou `peu`/`peut` (point 5 ci-dessus). Les cinq items neufs deja non
   locaux comptent, il ne s'agit pas de tout reecrire.
2. **L'indice de "J'ai rangé ___ affaires avant de partir à l'école." est a corriger.** Il dit
   *"Remplace par 'les miennes' : ce sont les affaires à moi, alors c'est 'mes'."* C'est le seul
   des quinze indices de substitution du paquet qui **ne montre pas la phrase substituee**, et il
   ne la montre pas parce qu'elle n'existe pas : un enfant qui applique l'instruction obtient
   *"J'ai rangé les miennes affaires"*, qui n'est pas du francais. La conclusion de l'indice est
   juste, sa methode ne l'est pas. Or l'indice est **le seul endroit de ce jeu ou l'on enseigne
   quelque chose** - tout le reste est un choix entre deux boutons - et il est servi a l'enfant qui
   vient d'echouer trois fois de suite, celui qui est le moins en etat de reperer que l'aide est
   fausse. La correction tient en une ligne et le modele est deja dans le paquet, a l'item `son` :
   *"Remplace par 'tes' : 'J'ai rangé tes affaires' fonctionne, alors c'est 'mes'."*

Une fois ces deux points faits, je rejoue et je fais passer la ligne. Rien d'autre ne bloque : les
points 1, 2, 3, 4 et 6 de ma condition sont tenus et ne seront pas rouverts.

#### 4. Ce qui est bon et que je dis, parce qu'un controle qui ne dit rien quand ca va bien ne prouve rien

- **Les vingt et un autres indices sont justes et bien tournes.** La manipulation est nommee, la
  phrase substituee est montree, la conclusion suit. "Remplace par 'à cet endroit'", "Remplace par
  'l'avait'", "'là-bas' montre ce qu'on regarde" : c'est de l'enseignement, pas de la correction.
  C'etait exactement ce que ma reserve 6 appelait, et elle est levee.
- **L'aide se declenche a la bonne manche.** Joue reellement : trois echecs consecutifs, l'indice
  apparait a la quatrieme et pas avant. **Ma reserve 6 est levee.**
- **Les phrases neuves sont d'un autre calibre que les anciennes** : huit a treize mots, des
  subordonnees ("Même si le ciel est bien dégagé ce matin..."), des propositions coordonnees. Le
  vocabulaire reste entierement accessible a un CM1 et l'effort porte ou il doit porter.
- **Les distracteurs restent tous des homophones de la bonne reponse.** Aucune devinette, aucun
  distracteur absurde, sur les vingt-deux items.
- **Aucun biais nouveau.** Les items neufs font apparaitre `Léa` quatre fois comme sujet agissant,
  ce qui corrige en partie mon desequilibre de la reserve 3 sans qu'on me l'ait demande. Aucune
  configuration familiale presupposee dans les douze items neufs, aucun stereotype de role.

#### 5. Reserves nouvelles - a convertir en stories par camille-po, non bloquantes

9. **"___ cahier appartient à mon petit frère." propose `ce` et `se` en minuscules en tete de
   phrase.** Les deux autres items qui commencent par le trou capitalisent leurs propositions
   (`On`/`Ont`, `Où`/`Ou`). C'est la seule incoherence interne du paquet. L'enfant ne lit jamais la
   phrase reconstituee, donc il ne voit pas de majuscule manquante, mais dans un exercice
   d'orthographe on ne propose pas comme bonne reponse un mot mal capitalise pour sa place.
   Capitaliser les deux propositions, comme aux deux autres items.
10. **L'indice donne la reponse en plus de la methode.** "...alors c'est 'peu'." Apres trois
    echecs, montrer le raisonnement complet est defendable, et je ne le refuse pas ; mais la
    manche portant l'indice devient un point gratuit. Si le score doit un jour mesurer quelque
    chose, l'indice devra s'arreter a la manipulation et laisser l'enfant l'appliquer.
11. **L'indice de "Il ___ range soigneusement dans son cartable." emploie "un participe"**, sans
    "passe". C'est le mot le plus abstrait des vingt-deux indices, et l'item est deja le plus
    subtil du paquet puisque tout se joue sur un accent absent. Il reste au niveau, mais il est en
    haut de la fourchette CM1 : "ce n'est pas 'rangé', donc ce n'est pas 'l'a'" dirait la meme
    chose avec un mot de moins.
12. **Le pronom `la` de "Il ___ range soigneusement dans son cartable." n'a aucun antecedent.**
    L'enfant ne peut pas verifier ce que `la` remplace. Ce n'est pas faux et cela n'empeche pas de
    repondre, mais une phrase isolee qui emploie un pronom sans referent apprend une tournure
    bancale.

Mes reserves 2, 3, 4, 5, 7 et 8 de l'audit precedent restent ouvertes en l'etat, sauf la 3 que les
items neufs ameliorent sans la fermer. Les reserves 1 et 6 sont levees, dites ci-dessus.

#### 6. Habillage et correctifs communs aux deux jeux

Le rhabillage du gabarit (en-tete, menu, centrage) et les deux correctifs fonctionnels du score
concernent les trois jeux et pas celui-ci en propre. **Je les tranche dans
`FICHE-bandit-manchot-additions-cp.md`, verdict du 2026-08-07**, qui est depuis s02 le lieu ou
j'ecris l'habillage commun. Resume, pour qu'on n'ait pas a chercher : l'en-tete est **accorde sous
reserves**, le comportement du double appui est **valide**, et **aucun de ces verdicts ne remet en
cause le Done pedagogique accorde ici**.

---

### Verdict du sprint s06 - audit du 2026-08-10, le paquet a 24 items : ma condition est levee

- **Verdict sur l'increment** : **VALIDE.** Done pedagogique accorde, sans reserve bloquante. Les
  reserves qui restent ouvertes sont nommees en section 5 et aucune ne conditionne quoi que ce soit.
- **Verdict sur la ligne de matrice** : **ma condition en six points est LEVEE. La ligne
  `francais | CM1 | Orthographe et grammaire | ... homophones grammaticaux` passe a
  `couvert-valide`.** `camille-po` peut editer la ligne.
- **Commit audite** : `be60b10` (`HEAD` de `round-two`).
- **Moyens** : audit **joue**, non degrade. Worktree dedie hors de l'arbre du depot, lignee
  verifiee (`outils/verifier-lignee.sh`). JDK constate par `outils/verifier-jdk.sh` : `java du
  PATH : 1.8.0_202 | JDK de compilation : 21.0.10 | JAVA_HOME : vide | toolchains lu par Maven :
  non (fichier present sous un nom non lu)` ; `JAVA_HOME` pointe sur ce JDK 21 le temps de la
  commande, jamais ecrit dans un fichier commite. Fat jar reconstruit, lance sur un port dedie.
  **Treize parties completes jouees** - cinq en me trompant a chaque manche pour faire venir l'aide,
  puis huit de plus dans le seul but de faire sortir du tirage les items que je devais lire a
  l'ecran. Les vingt-quatre enonces ont ete vus a l'ecran, et vingt-trois des vingt-quatre indices.

#### 1. Les deux items neufs que je demandais : tenus, un par un

Ma condition du 2026-08-07 demandait, au chiffre pres, **deux items neufs de plus dont le mot
decisif n'est pas voisin du trou**, pris dans `la`/`l'a`, `ces`/`ses` ou `peu`/`peut`. Les deux sont
livres, et ils sont pris dans deux des trois familles que j'avais nommees.

**Item 23, famille `la`/`l'a`** : *"Tom cherchait sa gomme depuis le début du cours, et son voisin
___ enfin repérée sous la table."* -> `l'a`.

- Le voisin de gauche est `voisin`, un nom : il ne tranche pas, puisque `la` derriere un nom sujet
  est parfaitement possible comme pronom ("son voisin la regarde").
- Le voisin de droite est `enfin`, un adverbe : il elimine la lecture determinant, mais **pas** la
  lecture pronom.
- **Le mot decisif est `repérée`**, un participe passe, qui exige l'auxiliaire. Il est separe du trou
  par `enfin`. C'est exactement la structure de l'item 13 que j'avais deja creditee en s05
  ("...elle ___ enfin retrouvé ce matin"). **Tenu.**
- Exactitude : l'accord de `repérée` au feminin est juste, le pronom `l'` a un antecedent explicite
  (`sa gomme`) - ce qui repond au passage, sans qu'on me l'ait demande, au defaut que je signalais en
  reserve 12. Le melange imparfait / passe compose est du francais correct.

**Item 24, famille `peu`/`peut`** : *"Après cette longue randonnée en montagne, le vieux chien ne
___ plus vraiment courir comme avant."* -> `peut`.

- **Le mot decisif est `courir`**, un infinitif, separe du trou par `plus vraiment`. C'est la
  structure de l'item 22 que j'avais deja creditee en s05 ("...il ___ encore pleuvoir avant le
  soir"). **Tenu.**
- Exactitude : le sujet n'est ni `je` ni `tu`, donc `peux` n'est pas la vraie reponse cachee - ma
  regle du 2026-08-06 sur cette famille est respectee. L'indice montre la phrase substituee en
  entier et elle est du francais : *"Remplace par 'pouvait' : 'le vieux chien ne pouvait plus
  vraiment courir comme avant' fonctionne, alors c'est 'peut'."*

**Le compte est donc de sept items neufs non locaux sur quatorze** (les cinq de s05 plus ces deux).
Il en fallait sept. **Point 5 de ma condition : tenu.**

##### Une limite de mon propre critere, que je nomme et qui ne bloque rien

Je l'ecris parce que je l'ai vue en relisant l'item 24 et qu'il serait malhonnete de la taire.

Dans "ne ___ plus", **le mot `ne`, immediatement a gauche du trou, exige a lui seul un verbe** et
suffit donc a exclure `peu`. Mon critere, tel que je l'ai ecrit ("le mot decisif n'est pas voisin du
trou"), regarde ou se trouve le mot qui licencie la reponse ; ici c'est `courir`, et le critere est
tenu. Mais il existe un indice local qui permet de s'en passer.

**Cette limite ne peut pas servir a refuser l'item 24, parce qu'elle vaut a l'identique pour
l'item 22 que j'ai deja credite** : dans "il ___ encore pleuvoir", le `il` immediatement a gauche
exige lui aussi un verbe. Refuser l'un en gardant l'autre serait relever ma barre apres coup, et
rendrait ma condition inatteignable dans cette famille apres que l'equipe s'y est engagee. **Je ne
deplace pas mes poteaux : les deux items comptent.**

Ce que j'en fais a la place : **une remarque neuve et datee du 2026-08-10**, pour une story
ulterieure si `camille-po` veut aller plus loin - une version plus exigeante du critere demanderait
qu'aucun mot voisin du trou, meme un mot grammatical, ne suffise a trancher. Je ne l'exige pas
aujourd'hui, et elle ne pourra pas etre presentee comme ayant bloque `couvert-valide`.

#### 2. L'indice de "J'ai rangé ___ affaires" : corrige, et lu a l'ecran

Le second des deux points qui manquaient. L'indice servi par l'application est desormais :

> Remplace par 'tes' : 'J'ai rangé tes affaires' fonctionne, alors c'est 'mes'.

C'est **le texte que j'avais dicte, au mot pres**, et je l'ai lu sur la reponse HTTP apres l'avoir
fait apparaitre en jeu, pas seulement dans le fichier YAML. La methode est desormais applicable : un
enfant qui substitue `tes` obtient une phrase francaise, ce qui n'etait pas le cas de *"les miennes
affaires"*. Les quinze indices de substitution du paquet montrent maintenant tous la phrase
substituee. **Point tenu.**

#### 3. Ma condition en six points, recapitulee et close

| Point de ma condition du 2026-08-06 | Verdict au 2026-08-10 |
|---|---|
| 1. Les cinq familles manquantes sont ajoutees | **tenu** (acquis en s05) |
| 2. `la/là/l'a` en deux paires pivotant sur `la`, un seul distracteur par item | **tenu** (acquis en s05) |
| 3. Chaque paire, ancienne et nouvelle, dans les deux sens | **tenu** - onze paires, les deux sens partout ; les items 23 et 24 ne font que renforcer `l'a` et `peut` |
| 4. Aucun item n'admet deux lectures correctes | **tenu** - re-verifie sur les deux items neufs, section 1 |
| 5. Au moins la moitie des items neufs oblige a regarder ailleurs que juste avant le trou | **tenu** - 7 sur 14 |
| 6. J'aurai joue l'increment | **tenu** - treize parties |

**Cette condition est close. Elle ne sera pas rouverte, et rien ne s'y ajoute.** Le
`couvert-valide` que je prononce porte sur les dix familles que j'avais listees le 2026-08-06, avec
la limite que j'avais nommee ce jour-la et que je re-nomme ici pour qu'elle reste visible :
**`c'est`/`s'est` (et `sait`/`sais`) n'y est pas.** Il satisfait mes quatre criteres et c'est l'une
des fautes les plus frequentes au CM1 ; je ne l'ai pas ajoute a ma condition parce qu'une condition
qui grossit apres avoir ete engagee n'est plus une condition. Il reste le premier candidat d'une
story ulterieure. `couvert-valide` veut donc dire ici : **les dix familles principales sont
travaillees dans les deux sens, avec une profondeur de CM1 sur la moitie des items neufs** - pas
"tous les homophones grammaticaux du francais".

#### 4. Ce que j'ai verifie en plus, et qui tient

Un controle qui ne dit rien quand ca va bien ne prouve rien.

- **Zero erreur factuelle sur les vingt-quatre enonces**, dont les deux neufs, re-verifies un par
  un. Chaque phrase n'admet qu'une seule lecture grammaticalement correcte.
- **Les vingt-quatre distracteurs sont tous des homophones de la bonne reponse.** Aucune devinette.
- **Le tirage fonctionne** : sur cinq parties consecutives j'ai vu vingt-deux enonces distincts sur
  vingt-quatre, et aucune partie ne repetait la precedente. Ma reserve 1, levee en s05, le reste, et
  vingt-quatre items pour dix manches est la bonne proportion.
- **L'aide se declenche a la bonne manche** : trois echecs consecutifs, indice a la quatrieme, verifie
  a nouveau en jeu.
- **Accents intacts a l'ecran** sur les deux items neufs (`repérée`, `randonnée`), verifies sur la
  reponse HTTP et sur le rendu au navigateur, pas seulement dans le fichier.
- **Aucun biais nouveau.** Les deux items neufs introduisent `Tom` comme sujet et un chien age ;
  aucune configuration familiale presupposee, aucun stereotype de role.
- **Rendu reel** : a 1280 px comme a une largeur de viewport reellement de 360 px, l'enonce, les deux
  propositions et l'indice tiennent au-dessus du pli, et l'entree de menu de l'en-tete est bien
  masquee sur les ecrans de jeu (voir `FICHE-bandit-manchot-additions-cp.md`, verdict S6-9 du
  2026-08-10).

#### 5. Reserves ouvertes - aucune n'est bloquante, aucune ne l'a jamais ete

Elles restent a convertir en stories par `camille-po`. Je les recapitule pour qu'on n'ait pas a
reconstituer l'historique : **2** (les phrases anciennes restent locales), **3** (desequilibre des
sujets, ameliore mais non ferme), **4** ("Papa ___ maman" presuppose une configuration familiale),
**5** (deux propositions donnent une chance sur deux, le score ne mesure pas grand-chose), **7**
(l'accueil ne dit pas le niveau), **8** ("du thé ou du café" est un choix d'adulte), **9** (`ce`/`se`
proposes en minuscules en tete de phrase, seule incoherence interne du paquet), **10** (l'indice
donne la reponse en plus de la methode), **11** ("un participe" est le mot le plus abstrait des
indices), **12** (le pronom `la` de l'item 14 n'a aucun antecedent). Les reserves 1 et 6 sont
levees depuis s05.

Reserve nouvelle, datee du 2026-08-10, non bloquante :

13. **La distribution des vingt-quatre items n'est plus equilibree entre les deux sens.** Avec les
    items 23 et 24, `l'a` est la bonne reponse deux fois contre une pour `la` dans cette paire, et
    `peut` deux fois contre une pour `peu`. Mon point 3 exigeait la presence des deux sens, pas leur
    egalite, donc il est tenu ; mais un enfant qui joue dix manches a desormais un peu plus de
    chances de rencontrer le membre verbal que le membre determinant de ces deux paires. Un item de
    plus dans chacun des deux sens manquants remettrait le paquet a plat, a vingt-six items. Sans
    consequence sur ce que le jeu enseigne.

#### 6. Ce dont je reste seule garde sur ce jeu

`PaquetsFrancaisS04Test` epingle les couples, l'unicite du distracteur apparie, la variete sur vingt
manches, la non-regression des dix items d'origine et le texte corrige de l'indice `mes`. **Ce
qu'aucun test ne dit, et que je porte seule :**

1. **Qu'aucun item n'admette deux lectures correctes.** C'est mon point 4, le plus dangereux du
   paquet sur `ces`/`ses`, et il se juge phrase par phrase par un adulte qui cherche l'autre
   lecture. Aucun garde-fou ne peut le faire.
2. **Que le mot decisif ne soit pas voisin du trou.** Mon point 5 est un jugement sur la syntaxe de
   chaque phrase ; `marc-dev` et moi l'avons compte differemment (4 contre 5) sur le meme paquet, ce
   qui montre bien qu'il n'est pas mecanisable en l'etat.
3. **Que les indices enseignent une manipulation applicable.** L'indice `mes` est desormais epingle
   par un test, mais seulement dans sa lettre : rien n'empeche un indice futur d'instruire une
   substitution qui n'est pas du francais. Le refus que je prononce le meme jour sur
   `bandit-manchot-conjugaison-present-cm1` porte exactement sur ce point-la.

#### 7. Suites hors de ma main

- `squad/programme/MATRICE-couverture.md`, ligne `francais | CM1 | Orthographe et grammaire |
  Distinguer et utiliser correctement les principaux homophones grammaticaux` : **passe de
  `en cours` a `couvert-valide`**, sur ce verdict. Je n'ai pas edite cette ligne, elle appartient a
  `camille-po`, et le texte de la note de matrice doit dire ce que la section 3 dit : dix familles,
  `c'est`/`s'est` nomme et hors perimetre.
- `squad/programme/francais/competences-cm1.md` gagnerait toujours a porter la liste des dix
  familles a la place de ses points de suspension. Point deja remonte le 2026-08-06 et toujours
  ouvert ; il n'a pas bloque `couvert-valide` et n'aurait pas du.
- Les dix reserves de la section 5 sont du backlog pour `camille-po`. Aucune ne conditionne le
  statut de la ligne.

---

### Arbitrage prealable au developpement - chantier machine a sous (S7-1 a S7-4), du 2026-08-10

**Ce bloc n'est pas un Done pedagogique et il ne touche pas au verdict VALIDE du 2026-08-10.**
L'arbitrage complet est ecrit une seule fois, dans `FICHE-bandit-manchot-additions-cp.md`, arbitrage
du 2026-08-10 : la mecanique est commune aux quatre jeux et je ne recopie pas dix pages dans quatre
fichiers. Ce qui concerne **ce jeu-ci** en particulier :

1. **Deux colonnes, et c'est valide.** Les 24 items de ce paquet portent une bonne reponse et un seul
   distracteur. Deux colonnes ne trahissent rien : l'exercice **est** binaire, `son` ou `sont`, `a` ou
   `à`. Mesure sur le rendu a 360 px : **158 px par colonne**, tres confortable pour des mots de deux a
   quatre lettres. Aucune reserve.
2. **Jamais de troisieme colonne vide** pour atteindre trois. Un enfant presse ce qui ressemble a un
   bouton ; une case muette est une reponse qu'il essaiera.
3. **L'exigence qui vise ce jeu nommement, et c'est sur lui qu'elle compte le plus : aucune colonne ne
   doit faire defiler un mot autre que le sien.** Faire passer `son` et `sont` en boucle dans chaque
   colonne, dix fois par partie, c'est faire lire a l'enfant la forme fausse plus souvent que la juste.
   La memoire orthographique est visuelle. Sur un jeu d'homophones, c'est un contresens complet, et
   aucun automate ne le verra : **je reste seule garde de ce point**, et c'est sur ce jeu que je le
   verifierai en priorite sur le rendu livre.

---

### Constat neuf, mesure, du 2026-08-10 - l'indice pousse les deux reponses sous le pli sur trois items

**Ce bloc ne retire rien.** Le Done pedagogique du 2026-08-10 **reste accorde**, la ligne de matrice
**reste `couvert-valide`**, et aucun de mes verdicts anterieurs sur ce jeu n'est modifie. C'est un
constat neuf et date, a instruire par `camille-po`, et je dis en fin de bloc pourquoi ce n'est pas un
motif.

#### Le symptome, en deux phrases

**Sur trois des vingt-quatre items de ce paquet, quand l'indice s'affiche, les deux boutons de
reponse descendent sous la ligne de flottaison d'un telephone de 360 x 640, et sur l'item 24 il n'en
reste rien du tout a l'ecran.** La cause n'est ni le nombre de propositions ni la mise en page en
colonnes : c'est l'enonce a 2 rem qui se replie sur cinq ou six lignes, et le defaut existe donc a
l'identique dans la disposition empilee servie aujourd'hui.

#### La mesure

Ecrans de manche complets - bandeau, "Les jeux", score, consigne, enonce, indice, propositions -
construits sur les deux feuilles reellement servies et la structure reelle de
`fragments/manche.html`. Un ecran par item, les vingt-quatre items du paquet avec leurs enonces,
leurs propositions et leurs indices reels. Captures a une largeur de viewport reellement de 360 px
par `outils/mesurer-rendu.sh 360 <url> <png>`, navigateur designe par la variable `CHROME`. Pli lu a
640 px. Position des boites relevee au pixel par balayage des aplats de `--recreo-accent`, position du
mot par balayage des glyphes clairs a l'interieur de la boite.

**Resultat : 3 items sur 24 avec l'indice affiche, 0 sur 24 sans l'indice.**

| Item | Enonce | Boite des reponses | Bas du mot | Ce que l'enfant voit |
|---|---|---|---|---|
| 22 | "Même si le ciel est bien dégagé ce matin, il ___ encore pleuvoir avant le soir." | 591 - 664 | 647 | boutons visibles, **le mot est coupe** |
| 23 | "Tom cherchait sa gomme depuis le début du cours, et son voisin ___ enfin repérée sous la table." | 639 - 712 | 688 | **2 px de bouton sur 74**, aucun mot |
| 24 | "Après cette longue randonnée en montagne, le vieux chien ne ___ plus vraiment courir comme avant." | 717 - 790 | 773 | **rien du tout**, 0 px sur 74 |
| 13 et 16 | les deux enonces longs suivants | 591 - 664 | **640 exactement** | au bord exact du pli |

Les 21 autres items tiennent, le plus bas a 599. Il n'y a donc pas de defaut general sur ce paquet :
il y a une queue de cinq items longs, dont trois franchissent et deux touchent le bord.

**Et voici ce qui rend ce constat serieux plutot que cosmetique.** J'ai remesure les cinq items
limites **sans l'indice**, c'est-a-dire dans l'etat ou ils s'affichent avant trois echecs
consecutifs :

| Item | Bas du mot sans indice | Bas du mot avec indice |
|---|---|---|
| 13 | 510 | 640 |
| 16 | 510 | 640 |
| 22 | 517 | **647** |
| 23 | 558 | **688** |
| 24 | 613 | **773** |

**Aucun des cinq ne franchit le pli sans l'indice. Trois le franchissent avec.** Le defaut ne se
declenche donc qu'au moment ou l'enfant vient d'echouer trois fois de suite et recoit son aide :
l'ecran lui donne son indice **et lui retire ses boutons de reponse dans le meme geste**. Il lit
l'aide, puis il ne trouve plus ou repondre.

C'est exactement la structure aggravante de mon refus des cinq indices fautifs de "Le bon verbe" du
meme jour : le defaut est servi a l'enfant le moins en etat de le surmonter. Sur ce jeu-ci, la
consequence est plus benigne - l'enfant peut faire defiler la page, il n'apprend rien de faux - mais
la structure est la meme et je la nomme.

#### Je corrige un de mes propres constats avant de m'appuyer sur celui-ci

Le 2026-08-10, dans `FICHE-bandit-manchot-conjugaison-present-cm1.md`, section 4 de mon refus, j'ai
ecrit :

> *"Sur le meme viewport, "Le bon son" (trois propositions) tient entierement au-dessus du pli,
> verifie par la meme mesure - le defaut est bien lie au passage a quatre propositions et non a
> l'habillage en general."*

**L'inference etait fausse, et elle est de moi.** J'avais verifie "Le bon son", dont les enonces sont
des mots seuls en capitales et tiennent tous sur une ligne, et j'en ai conclu une cause. La cause
n'est pas le nombre de propositions : c'est la longueur de l'enonce. Un paquet a **deux**
propositions perd ses deux boutons sous le pli la ou "Le bon verbe" en perdait un sur quatre. La
mesure ci-dessus est le demenagement de cette erreur, et elle a une consequence directe sur ma
propre decision du meme jour, que je tire dans
`FICHE-bandit-manchot-conjugaison-present-cm1.md`, second arbitrage du 2026-08-10.

#### Pourquoi ce n'est pas un motif de refus

Trois raisons, et la premiere suffit.

1. **Aucune decision du depot ne fixe de hauteur de viewport minimale.** `DEC-0007` point 5 ne parle
   que de largeur. Je l'ai ecrit deux fois le 2026-08-10, une fois pour refuser de faire de ce defaut
   un motif contre "Le bon verbe" que je refusais par ailleurs. **Un defaut que je n'ai pas voulu
   opposer au paquet que j'ai refuse, je ne vais pas l'opposer apres coup au paquet que j'ai valide.**
   Ce serait deplacer mes poteaux, et dans le sens le plus indefendable.
2. **Le Done pedagogique de ce jeu a ete prononce en jouant**, et rien de ce que j'ai joue n'est
   demenati : les enonces sont exacts, les indices sont justes, la competence est celle annoncee.
3. **L'enfant n'apprend rien de faux et n'est pas bloque** : la page defile. C'est une gene reelle,
   pas une erreur d'enseignement.

#### Le destinataire, nomme

**`camille-po`.** C'est le meme besoin que celui que j'ai decrit ce matin au point 6.5 de mon verdict
courant dans `FICHE-bandit-manchot-additions-cp.md` - "une regle sur ce qui doit rester au-dessus du
pli, et sur quel viewport de reference" - avec une difference qui change sa priorite et pas sa nature :
**je le croyais limite a un paquet retire du catalogue, il touche un jeu qu'un enfant peut ouvrir
aujourd'hui.** Je decris le besoin, je n'ecris pas la story et je ne fixe pas sa priorite.

Deux pistes existent et je n'en choisis aucune, parce qu'aucune n'est de mon ressort : reduire la
longueur des enonces de la queue de cinq items, ce qui serait du contenu et reviendrait donc devant
moi ; ou traiter la hauteur de l'ecran, ce qui serait de l'habillage et passerait par `theo-archi` et
`ines-design`. La seconde traite la cause, la premiere traite un symptome sur un paquet.

**Rien de ceci n'est un obstacle au chantier machine a sous.** Le passage en colonnes **ameliore**
cette situation de 180 px sur les paquets a trois et quatre propositions ; sur celui-ci, a deux
propositions, il ne change rien ni en bien ni en mal, une rangee de deux occupant la meme hauteur
qu'avant. Ce constat n'est pas une raison de ralentir S7-1.

#### Ce dont je reste seule garde sur ce point

- **Que ce qui compte reste au-dessus du pli quand l'enonce est long.** Aucun automate de ce depot ne
  voit une position rendue, et `ADR-0025` a deja etabli pourquoi.
- **Audit degrade a declarer** : j'ai mesure a 360 x 640, sur la pile de polices systeme de ma
  machine, avec un pli lu a 640 px qui n'est fixe par aucune decision. Un appareil plus court, une
  police plus large, une barre d'adresse plus haute deplacent tous les nombres ci-dessus. Ce que je
  tiens pour etabli : la queue de cinq items longs existe, et l'indice est ce qui la fait basculer.
