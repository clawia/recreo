# Fiche de conformite - bandit-manchot-homophones-cm1

- **Mecanique** : bandit manchot (mecanique generique, partagee avec
  `bandit-manchot-additions-cp` et `bandit-manchot-le-bon-son-cp`).
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/cm1-homophones.yaml`,
  10 items, `type: question`. Matiere premiere issue de `contenu-repris/`.
- **Statut global** : **VALIDE SOUS RESERVE** (Done pedagogique accorde le 2026-08-06, premier
  audit). C'est le premier contenu de CM1 du produit.
- **Derniere mise a jour** : 2026-08-06

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
