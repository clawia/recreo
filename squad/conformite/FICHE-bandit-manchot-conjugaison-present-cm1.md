# Fiche de conformite - bandit-manchot-conjugaison-present-cm1

- **Mecanique** : bandit manchot (mecanique generique, partagee avec
  `bandit-manchot-additions-cp`, `bandit-manchot-homophones-cm1` et
  `bandit-manchot-le-bon-son-cp`).
- **Contenu** : paquet a creer sous `adapters/adapter-content/src/main/resources/contenu/`
  (nom de fichier laisse a `theo-archi` / aux developpeurs, par exemple
  `cm1-conjugaison-present.yaml`), `type: question`. Matiere premiere issue de `contenu-repris/
  textes-trous/cm1-conjugaison.yml`, 6 items a l'etat de reserve, a completer et a repasser devant
  `nadia-prof` comme un contenu neuf (`contenu-repris/README.md`, `CLAUDE.md`).
- **Contenu livre et audite** :
  `adapters/adapter-content/src/main/resources/contenu/cm1-conjugaison-present.yaml`, 12 items,
  `type: question`, chacun porteur d'un indice. Entree de catalogue
  `bandit-manchot-conjugaison-present-cm1`, titre "Le bon verbe", 10 manches. Six items repris de
  `contenu-repris/textes-trous/cm1-conjugaison.yml`, six rediges en s06 ; les douze indices sont
  neufs.
- **Statut global** : **REFUSE** (Done pedagogique refuse le 2026-08-10, premier audit). Motif
  unique et suffisant : cinq des douze indices enoncent une regle de conjugaison fausse, et trois
  d'entre elles sont dementies par un autre item du meme paquet.
- **Ligne de matrice** : **reste `en cours`.** Elle ne pouvait de toute facon pas atteindre
  `couvert-valide` sur cet increment (un temps sur trois, `DEC-0013`) ; elle ne l'atteint pas
  davantage, et le Done pedagogique lui-meme n'est pas accorde.
- **Derniere mise a jour** : 2026-08-10

## Competences visees

| Matiere | Niveau | Domaine | Competence | Reference au referentiel |
|---|---|---|---|---|
| francais | CM1 (cycle 3) | Orthographe et grammaire | Conjuguer les verbes frequents au present, futur et imparfait de l'indicatif - **sur le present uniquement pour ce jeu** | `squad/programme/francais/competences-cm1.md` |

## Criteres pedagogiques attendus - camille-po

- [ ] Chaque manche presente une phrase a trou dont la bonne reponse est un verbe frequent
      conjugue au present de l'indicatif, jamais a un autre temps.
- [ ] La bonne reponse figure exactement une fois parmi les propositions ; chaque distracteur est
      une autre forme conjuguee du meme verbe au present (jamais un verbe different, jamais une
      forme d'un autre temps).
- [ ] Chacune des six personnes grammaticales (je, tu, il/elle, nous, vous, ils/elles) est
      representee par au moins un item du paquet.
- [ ] Le paquet demarre une partie sans erreur avec la mecanique bandit manchot existante et sans
      qu'aucune ligne de `domain/mechanic` ni `domain/mechanics` n'ait ete modifiee pour
      l'accueillir (`ADR-0007`, `CatalogueChargeableIT`).
- [ ] Sur 20 manches jouees a la suite, les phrases proposees ne sont pas toutes identiques.
- [ ] Tout texte destine a l'enfant (consigne, retours, indices s'il en existe) est redige en
      francais correct et integralement accentue, orthographe et ponctuation irreprochables
      (exigence de categorie B ajoutee par l'amendement du 2026-08-04 a `DEC-0002`).
- [ ] Les phrases sont d'un niveau de vocabulaire et de longueur adapte a un eleve de CM1 ; les
      verbes retenus sont effectivement "frequents" au sens du referentiel.
- [ ] L'accueil mentionne le niveau vise (reserve commune deja nommee sur les deux autres jeux de
      francais, `docs/backlog/backlog.md`, "Backlog - sprints suivants"), sauf si `camille-po` la
      traite ailleurs avant l'audit de ce jeu.

Voir `docs/backlog/backlog.md`, story S6-3, pour la formulation complete de ces criteres en
entree/sortie observables.

---

## Bloc de validation pedagogique - nadia-prof UNIQUEMENT

> Ce bloc n'est jamais pre-rempli ni anticipe par un autre agent.

### Verdict - premier audit, increment livre en s06

- **Verdict** : **REFUSE.** Done pedagogique non accorde. Ce jeu ne peut pas etre mis devant une
  classe de CM1 en l'etat.
- **Date** : 2026-08-10.
- **Commit audite** : `be60b10` (`HEAD` de `round-two`).
- **Moyens** : audit **joue**, non degrade. Worktree dedie hors de l'arbre du depot, lignee
  verifiee (`outils/verifier-lignee.sh` : "le worktree sur la branche '<ma branche>' descend de
  round-two"). JDK constate par `outils/verifier-jdk.sh` : `java du PATH : 1.8.0_202 | JDK de
  compilation : 21.0.10 | JAVA_HOME : vide | toolchains lu par Maven : non (fichier present sous un
  nom non lu)` ; `JAVA_HOME` pointe sur ce JDK 21 le temps de la commande, jamais ecrit dans un
  fichier commite. Fat jar reconstruit, lance sur un port dedie. Deux parties completes de dix
  manches jouees : une juste de bout en bout (score 10 sur 10), une fausse a chaque manche pour
  faire venir l'aide. Ecrans mesures au navigateur sans tete en 1280 x 700 et a une largeur de
  viewport reellement de 360 px (methode du rapport qualite de `yanis-qa`, voir la section 4).

#### 1. Ce qui est juste, et je le dis d'abord parce que le refus ne porte pas la-dessus

**Les douze enonces sont exacts, verifies un par un.** Pour chaque item, une seule des quatre
propositions est grammaticalement possible : aucun item n'admet deux reponses vraies.

| Enonce | Attendu | Les trois distracteurs | Verdict |
|---|---|---|---|
| "Tous les jours, il ___ à l'école." | `va` | vais, vont, allez | juste |
| "Nous ___ une histoire." | `lisons` | lit, lisent, lis | juste |
| "Vous ___ du chocolat." | `mangez` | mange, mangeons, mangent | juste |
| "Je ___ mes devoirs." | `fais` | fait, faisons, font | juste |
| "Elles ___ dans le jardin." | `jouent` | joue, jouons, jouez | juste |
| "Tu ___ un dessin." | `dessines` | dessine, dessinent, dessinez | juste |
| "Je ___ mon cahier dans mon sac." | `cherche` | cherches, cherchons, cherchent | juste |
| "Tu ___ ton exercice avant la récréation." | `finis` | finit, finissons, finissent | juste |
| "Elle ___ toujours ses affaires avant de partir en récréation." | `range` | ranges, rangeons, rangent | juste |
| "Nous ___ dans la cour pendant la récréation." | `courons` | cours, court, courent | juste |
| "Vous ___ vos cahiers avant de commencer." | `prenez` | prend, prenons, prennent | juste |
| "Ils ___ une chanson en rentrant de l'école." | `chantent` | chante, chantons, chantez | juste |

Trois qualites reelles, que je porte au credit du paquet :

- **Les six personnes grammaticales sont chacune representee deux fois exactement** : `je` (items 4
  et 7), `tu` (6 et 8), `il`/`elle` (1 et 9), `nous` (2 et 10), `vous` (3 et 11), `ils`/`elles`
  (5 et 12). C'est le bon equilibre, et il n'est pas frequent qu'un paquet l'atteigne du premier
  coup.
- **Les trois groupes sont travailles** : six verbes du premier groupe, `finir` pour le deuxieme,
  et cinq irreguliers frequents (`aller`, `lire`, `faire`, `courir`, `prendre`). Le referentiel dit
  "verbes frequents" ; ce sont bien ceux-la.
- **Les distracteurs sont tous des formes du meme verbe au meme temps**, donc aucun distracteur
  absurde et aucune devinette. Mieux : sur les items du premier groupe, les quatre propositions
  sont homophones entre elles (`dessine`, `dessines`, `dessinent`), ce qui est exactement la
  difficulte que cette competence doit faire travailler - on ne peut pas la resoudre a l'oreille.

Le contenu **travaille bien la competence annoncee**, et non une competence voisine plus facile :
c'est l'accord de la forme verbale avec la personne, au present, et rien d'autre. Sur le perimetre
d'un temps sur trois, je n'ai rien a ajouter a ce que `DEC-0013` assume deja : voir la section 5.

#### 2. Motif du refus - cinq indices sur douze enoncent une regle fausse

C'est le motif, il est unique, et il suffit.

| Item | Texte de l'indice | Ce qui cloche |
|---|---|---|
| 5 (`jouent`) | "Avec 'ils' ou 'elles', le verbe se termine par '-ent' au présent : c'est 'jouent'." | faux : `ils vont`, `ils ont`, `ils sont`, `ils font` |
| 6 (`dessines`) | "Avec 'tu', le verbe se termine par '-es' au présent : c'est 'dessines'." | faux, et **dementi par l'item 8 du meme paquet** : "Tu ___ ton exercice" -> `finis` |
| 7 (`cherche`) | "Avec 'je', le verbe se termine par '-e' au présent : c'est 'cherche'." | faux, et **dementi par l'item 4** : "Je ___ mes devoirs" -> `fais` |
| 9 (`range`) | "Avec 'il' ou 'elle', le verbe se termine par '-e' au présent : c'est 'range'." | faux, et **dementi par l'item 1** : "Tous les jours, il ___" -> `va` |
| 12 (`chantent`) | "Avec 'ils' ou 'elles', le verbe se termine par '-ent' au présent : c'est 'chantent'." | meme defaut que l'item 5 |

**On ne peut pas lire "le verbe" comme "le verbe de cette phrase-ci", et c'est le paquet lui-meme
qui l'interdit.** Il emploie deux formulations distinctes, et il les distingue :

- quand il veut parler d'un verbe precis, il le nomme : *"le verbe 'finir' se termine par '-is'"*
  (item 8), *"le verbe 'courir'"* (item 10), *"le verbe 'prendre'"* (item 11), *"Le verbe 'aller'
  est irrégulier"* (item 1), *"Le verbe 'faire' est irrégulier"* (item 4) ;
- quand il veut enoncer une regle generale, il ecrit "le verbe" tout court **et il la tempere** :
  *"le verbe se termine **presque toujours** par '-ons'"* (item 2), *"**presque toujours** par
  '-ez'"* (item 3).

Les cinq indices du tableau ci-dessus ecrivent "le verbe" tout court **sans temperer**. Sous la
convention du paquet, ce sont donc des regles generales, et elles sont fausses.

**Pourquoi cela ne peut pas etre une reserve.** L'indice est **le seul endroit de ce jeu ou l'on
enseigne quelque chose** : tout le reste est un choix entre quatre boutons. Et je l'ai verifie en
jouant : il n'apparait qu'a partir de la quatrieme manche, apres trois echecs consecutifs, donc il
est servi a l'enfant qui vient de se tromper trois fois de suite - celui qui est le moins en etat de
reperer que l'aide est fausse. Ce que cet enfant emporte de la partie, c'est "avec je, le verbe se
termine par -e", et il ecrira *je prende*, *il alle*, *je faie*. La faute que le jeu lui apprend est
exactement celle que le jeu existe pour prevenir.

**Et je l'ai vu se produire dans une seule partie**, ce n'est pas une deduction sur le fichier : a
la septieme manche j'ai lu "Avec 'tu', le verbe se termine par '-es' au présent", et a la dixieme
manche de la meme partie le jeu m'a demande "Tu ___ ton exercice avant la récréation." dont la
reponse est `finis`. Le jeu se contredit devant l'enfant, en deux minutes.

**La correction est petite, exacte, et ses trois modeles sont deja dans le paquet.** Pour chacun des
cinq indices, une seule des trois formes suffit :

1. temperer, comme les indices 2 et 3 : "le verbe se termine **presque toujours** par '-ent'" ;
2. nommer le groupe : "**les verbes en -er** se terminent par '-e' avec 'je'" - c'est la forme la
   plus juste des trois, parce que les cinq items concernes sont tous du premier groupe ;
3. nommer le verbe, comme les indices 8, 10 et 11 : "le verbe 'chanter' se termine par '-ent'".

Je ne choisis pas laquelle : les trois sont acceptables et le choix appartient a l'auteur du
contenu. Ce que j'exige, c'est qu'aucun indice de ce paquet n'affirme d'une terminaison qu'elle vaut
pour "le verbe" sans restriction.

#### 3. Ce dont je reste seule garde sur ce jeu

Pour qu'on ne croie pas couvert ce qui ne l'est pas. `PaquetsFrancaisS04Test` epingle desormais que
les propositions sont toutes des formes du meme verbe au present, que les six personnes sont
representees, et que le paquet varie sur vingt manches. **Aucun de ces garde-fous ne regarde le
contenu des indices, et aucun ne peut le faire** : "cette regle de grammaire est-elle vraie" n'est
pas une propriete qu'un test peut trancher. La verite de ce qui est enseigne a l'enfant est ma
charge, et ce refus en est la demonstration : la CI est verte sur ce paquet.

#### 4. Constat neuf, mesure, qui ne fonde PAS ce refus

Je le separe explicitement du motif, parce qu'il ne doit pas etre lu comme une barre que j'aurais
posee apres coup.

**Sur un telephone de 360 x 640, la quatrieme proposition est sous le pli.** "Le bon verbe" est le
premier jeu du produit a quatre propositions ; les deux autres en ont trois. Mesure a une largeur et
une hauteur de viewport reellement de 360 x 640 - la page reelle chargee dans un cadre de largeur
et de hauteur CSS fixees, dans une fenetre Chrome de 900 px, pour contourner le plancher de 500 px
etabli par `yanis-qa` :

| Element | Position verticale | Sous le pli a 640 px ? |
|---|---|---|
| bandeau | 0 - 76 | non |
| proposition 1 | 368 - 437 | non |
| proposition 2 | 458 - 527 | non |
| proposition 3 | 548 - 617 | non |
| **proposition 4** | **638 - 707** | **oui, 2 px visibles sur 69** |
| hauteur totale du document | 753 px | |

L'enfant voit trois propositions et un liseré. Comme l'ordre des propositions est tire a chaque
manche, la bonne reponse sera parfois la quatrieme : l'enfant repondra alors en ignorant qu'elle
existait. Sur le meme viewport, "Le bon son" (trois propositions) tient entierement au-dessus du
pli, verifie par la meme mesure - le defaut est bien lie au passage a quatre propositions et non a
l'habillage en general.

**Pourquoi ce n'est pas un motif.** Aucune decision du depot ne fixe de hauteur de viewport
minimale : `DEC-0007` ne parle que de largeur. Je ne vais pas fabriquer une barre en cours d'audit
pour la faire porter sur un increment qui n'a pas pu s'y preparer. C'est donc un **constat neuf et
date du 2026-08-10**, a instruire par `camille-po` comme une story, et il concernera tout futur
paquet a plus de trois propositions. Il ne durcit aucune reserve anterieure.

#### 5. Ou est la barre pour `couvert-valide`, puisque personne ne me demande de la prononcer

`DEC-0013` assume que la ligne du referentiel nomme trois temps et que ce paquet n'en couvre qu'un.
Je confirme que ce choix de perimetre est defendable - on n'ouvre pas trois temps a la fois - et je
n'en fais pas un motif. Mais je dis ou est la barre, comme je l'ai fait pour les homophones, pour
qu'elle ne se decouvre pas dans deux sprints :

1. **le futur de l'indicatif et l'imparfait de l'indicatif sont ajoutes**, avec les six personnes
   chacun, sur des verbes frequents des trois groupes comme le present les a deja ;
2. **au moins un item par temps oppose deux temps entre eux** et pas seulement deux personnes d'un
   meme temps. Le motif est de couverture : la competence dit "conjuguer au present, futur et
   imparfait", et un enfant qui n'a jamais eu a choisir *entre* deux temps n'a jamais eu a
   identifier le temps demande. Aujourd'hui les quatre propositions sont toujours au present, donc
   le temps est donne, jamais choisi ;
3. **la marque du temps est reperable dans la phrase** (un complement comme "hier", "demain",
   "tous les jours") plutot que devinable : c'est ce qui rend le point 2 jouable sans ambiguite ;
4. **aucun item n'admet deux lectures correctes**, comme aujourd'hui - et le risque augmente des
   qu'on melange les temps ("hier" avec un present de narration, par exemple), donc j'auditerai ces
   items un par un ;
5. **les indices de ces items nomment la marque du temps** et ne se contentent pas de nommer la
   personne ;
6. et **j'aurai joue l'increment** avant de le dire.

Rien d'autre. Ce qui n'est pas dans cette liste ne bloquera pas `couvert-valide` et ne pourra pas
m'etre oppose comme l'ayant bloque. Le motif de refus de la section 2 est independant de cette
liste : il doit etre corrige quel que soit le sort du futur et de l'imparfait.

#### 6. Reserves - a convertir en stories par camille-po, non bloquantes

Elles ne fondent pas le refus et elles ne le grossissent pas.

1. **Le sujet est toujours un pronom, colle au trou, dans les douze items.** Le meme constat que ma
   reserve 2 sur les homophones : la discrimination se fait sur le mot immediatement a gauche, donc
   sans jamais avoir a lire la phrase. Au CM1, la vraie difficulte est un sujet en groupe nominal
   ("Les élèves de la classe ___") ou un sujet eloigne du verbe ("Les enfants, quand la cloche
   sonne, ___"). En l'etat l'exercice est juste mais il est de consolidation CE2 plutot que
   d'apprentissage CM1. C'est le premier point que je traiterais apres le motif de la section 2, et
   c'est aussi ce qui donnerait sa profondeur au point 2 de la section 5.
2. **L'indice donne la reponse en plus de la methode** ("...: c'est 'lisons'."). Meme remarque que ma
   reserve 10 sur les homophones : apres trois echecs c'est defendable, mais la manche portant
   l'indice devient un point gratuit. Le jour ou le score mesurera quelque chose, l'indice devra
   s'arreter a la manipulation.
3. **Douze items pour dix manches : une partie montre dix items sur douze.** Deux items de
   difference entre deux parties, c'est peu ; l'enfant a pratiquement tout vu des la premiere. Le
   paquet des homophones est a vingt-quatre items pour dix manches et c'est la bonne proportion.
4. **"Elle ___ toujours ses affaires avant de partir en récréation."** est le seul item ou une fille
   est sujet d'une action, et cette action est de ranger. Ce n'est pas grave, ce n'est pas faux, et
   `Elles ___ dans le jardin.` equilibre en partie ; je le signale parce que le remplacement est
   gratuit et qu'on n'ecrit pas douze phrases en laissant celle-la seule.
5. **"récréation" apparait dans trois items sur douze** (8, 9, 10), et "école" dans deux. Sur un
   paquet aussi court, cela donne une impression de decor unique. Sans consequence
   d'apprentissage.
6. **L'accueil ne dit pas a quel niveau s'adresse le jeu.** "Le bon verbe" n'annonce pas le CM1
   alors que "Calcul CP" annonce le CP. Reserve deja ouverte sur les deux autres jeux de francais
   (critere de `camille-po` dans cette fiche) : elle n'est **pas** traitee, et elle empire, puisque
   l'accueil propose desormais deux jeux de CM1 non marques a cote d'un jeu de CP marque. Un enfant
   de CP peut ouvrir celui-ci. Question produit pour `camille-po`, pas un motif.

#### 7. Suites hors de ma main

- `squad/programme/MATRICE-couverture.md`, ligne `francais | CM1 | Orthographe et grammaire |
  Conjuguer les verbes frequents au present, futur et imparfait de l'indicatif` : **reste
  `en cours`**, et le Done pedagogique n'est pas accorde. Je n'ai pas edite cette ligne, elle
  appartient a `camille-po`.
- Un refus renvoie l'increment a `camille-po`, qui en fait du backlog. Il ne bloque pas le sprint
  suivant, et le contenu deja fusionne n'a pas besoin d'etre defait : les douze enonces, les douze
  couples personne/forme et les sept indices justes sont acquis et servent de base a la correction.
- Rien de ce refus ne concerne l'habillage commun aux jeux : je le tranche dans
  `FICHE-bandit-manchot-additions-cp.md`, qui est depuis s02 le lieu ou je l'ecris.
