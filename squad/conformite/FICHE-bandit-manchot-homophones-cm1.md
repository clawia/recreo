# Fiche de conformite - bandit-manchot-homophones-cm1

- **Mecanique** : bandit manchot (mecanique generique, partagee avec
  `bandit-manchot-additions-cp` et `bandit-manchot-le-bon-son-cp`).
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/cm1-homophones.yaml`,
  22 items, `type: question`, chacun porteur d'un indice. Matiere premiere issue de
  `contenu-repris/`.
- **Statut global** : **VALIDE SOUS RESERVE** (Done pedagogique accorde le 2026-08-06, reconduit
  le 2026-08-07 sur l'increment de s05). C'est le premier contenu de CM1 du produit.
- **Ligne de matrice** : **reste `en cours`.** Ma condition de `couvert-valide` n'est pas levee -
  motif au verdict du 2026-08-07, section 3.
- **Derniere mise a jour** : 2026-08-07

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
