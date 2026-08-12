# Fiche de conformite - bandit-manchot-problemes-additifs-cp

- **Mecanique** : bandit manchot (mecanique generique, partagee avec les autres jeux du
  catalogue).
- **Contenu(s)** : `adapters/adapter-content/src/main/resources/contenu/cp-problemes-additifs.yaml`
  (a creer, `type: question`, au moins 20 items), story `S9-4` (`docs/backlog/backlog.md`). Contenu
  neuf, aucune matiere premiere identifiee dans `contenu-repris/` pour cette ligne (le fichier
  `contenu-repris/quiz/cp-monnaie.yml` a un format proche mais porte sur la monnaie, ligne
  explicitement differee par `DEC-0019` - a ne pas reprendre ici).
- **Statut global** : en construction.
- **Derniere mise a jour** : 2026-08-12

## Competences visees

| Matiere | Niveau | Domaine | Competence | Reference au referentiel |
|---|---|---|---|---|
| mathematiques | CP (cycle 2) | Nombres et calculs | Resoudre des problemes simples d'addition/soustraction en situation (1 etape) | `squad/programme/mathematiques/competences-cp.md` |

## Criteres pedagogiques attendus - camille-po

- [ ] Chaque enonce decrit une situation concrete en une ou deux phrases, sans notation
  mathematique explicite (pas de signe "+" ni "-"), qui se resout par une seule operation
  d'addition ou de soustraction ; la bonne reponse est le resultat entier de cette operation.
- [ ] Les items qui se resolvent par une addition et ceux qui se resolvent par une soustraction
  representent chacun au moins un tiers du paquet (l'enfant doit choisir l'operation, pas
  seulement l'executer).
- [ ] Aucun enonce ne fait reference a une piece, un billet ou une valeur en euros (perimetre
  distinct de la ligne "monnaie", differee par `DEC-0019`).
- [ ] Chaque item a exactement trois propositions numeriques, dont deux distracteurs correspondant
  a une erreur de raisonnement identifiable (par exemple l'operation inverse de celle attendue, ou
  un nombre de l'enonce recopie sans calcul).
- [ ] Le paquet compte au moins 20 items.
- [ ] Chaque item porte un indice non vide, exact et comprehensible par un enfant de CP.
- [ ] Le paquet valide le schema `type: question` (`ADR-0007`) sans code Java neuf, sans gabarit ni
  feuille de style neuve.

---

## Bloc de validation pedagogique - nadia-prof UNIQUEMENT

> Ce bloc n'est jamais pre-rempli ni anticipe par un autre agent.

### Verdict courant - S9-4, prononce sur l'increment livre, du 2026-08-12

- **Verdict** : **REFUSE.** Done pedagogique **non** prononce. Motif en section 1 : deux items dont
  la reponse attendue contredit la lecture naturelle de leur propre enonce, et dont la reponse juste
  n'est meme pas proposee. `camille-po` en fait du backlog ; ce refus ne bloque pas le sprint
  suivant.
- **Date** : 2026-08-12.
- **Audit joue** : **non degrade.** Jar construit et lance depuis mon worktree
  `s09-nadia-verdicts`, joue par les vraies routes, deux parties completes de 10 manches (une en
  ratant tout, une en ratant les trois premieres manches puis en repondant juste). L'item des
  ballons est tombe dans la premiere partie, en manche 3 : c'est en le lisant a l'ecran, en position
  d'enfant, que j'ai vu le defaut. Les 22 items lus dans
  `adapters/adapter-content/src/main/resources/contenu/cp-problemes-additifs.yaml`.

#### 1. Motif du refus : deux items punissent l'enfant qui lit correctement

**Item des ballons** : `Le clown a 7 ballons et en gonfle 2. Combien en a-t-il ?` - reponse
attendue `9`, propositions `9`, `5`, `2`.

**Item des fleurs** : `Le jardinier a 6 fleurs et en plante 2. Combien en a-t-il ?` - reponse
attendue `8`, propositions `8`, `4`, `2`.

Dans ces deux enonces, le pronom **"en" est partitif** : "il **en** gonfle 2" se lit "il gonfle 2
**de ses** ballons", "il **en** plante 2" se lit "il plante 2 **de ses** fleurs". Sous cette
lecture - qui est la lecture normale du francais - **le nombre d'objets ne change pas** : le clown a
toujours 7 ballons, dont 2 sont maintenant gonfles ; le jardinier a toujours 6 fleurs, dont 2 sont
maintenant plantees. La reponse a "Combien en a-t-il ?" est **7** pour les ballons et **6** pour les
fleurs.

**Ni 7 ni 6 ne figure parmi les propositions.** L'enfant qui modelise correctement la situation ne
trouve aucun bouton juste et doit choisir une reponse qu'il sait fausse.

**Ce qui rend le defaut certain plutot qu'interpretable : l'indice repare l'enonce.** Les deux
indices ajoutent un mot que l'enonce ne dit pas :

- ballons : `Il avait des ballons et il en gonfle d'autres : additionne 7 et 2.`
- fleurs : `Il avait des fleurs et il en plante d'autres : additionne 6 et 2.`

"**d'autres**". L'auteure a eu besoin d'ecrire "d'autres" dans l'indice parce que sans ce mot
l'addition ne se justifie pas - et ce mot est absent de l'enonce que l'enfant lit d'abord. L'indice
n'arrive qu'apres trois echecs consecutifs : l'enfant qui joue ces items du premier coup n'a jamais
acces a la lecture qui rend la reponse attendue correcte.

**Pourquoi c'est un refus et non une reserve**, en trois points.

1. **Ce n'est pas un defaut de forme, c'est le coeur de la competence.** La competence visee est
   "resoudre des problemes en situation", c'est-a-dire **construire un modele mathematique a partir
   d'un texte**. Un item dont la reponse attendue contredit le modele correct de son propre texte
   n'exerce pas cette competence : il l'inverse. Il apprend a l'enfant que lire attentivement mene a
   la mauvaise reponse, et que la bonne strategie est de reperer deux nombres et de deviner
   l'operation d'apres l'allure de la phrase. C'est exactement l'habitude que ce jeu doit combattre.
2. **Les 20 autres items montrent que l'auteure connait la distinction.** "gagne", "reçoit",
   "achète", "trouve", "adopte", "fait 4 de plus" : tous ces verbes font entrer des objets neufs
   dans la possession du personnage, et l'addition y est indiscutable. "gonfler" et "planter"
   agissent sur des objets deja possedes. Le defaut est donc localise et la regle a appliquer est
   deja etablie dans le paquet.
3. **Je m'y suis tenue ailleurs.** J'ai ecrit dans ma methode qu'une erreur factuelle dans un
   contenu scolaire est un motif de refus a elle seule. Un enonce dont la reponse attendue est fausse
   sous la lecture correcte est pire qu'une erreur de calcul : une erreur de calcul se voit, celle-ci
   fait douter l'enfant de sa lecture.

**Ce que la correction demande** : reecrire ces deux enonces, ou remplacer les deux verbes. Deux
formes qui conviennent, et je les ecris pour qu'il n'y ait rien a me redemander :

- `Le clown a 7 ballons et en gonfle 2 de plus. Combien en a-t-il ?`
- `Le jardinier a 6 fleurs et en plante 2 de plus. Combien en a-t-il ?`

"de plus" leve l'ambiguite sans allonger la phrase de plus de huit caracteres, et c'est deja la
solution employee par l'item de Mamie (`et en fait 4 de plus`), qui est correct pour cette raison
exacte. Les indices peuvent alors garder leur "d'autres", qui devient coherent avec l'enonce.

#### 2. La longueur des enonces : ce n'est PAS un motif, et voici ce que j'ai juge

On m'a demande de juger la lisibilite du texte, pas la geometrie. Je n'ai mesure aucun pixel ce
sprint.

**Les 64 caracteres retenus par `sofia-sm` sont un bon choix de redaction, et je le valide comme
tel.** Chiffres du paquet : maximum **64** (`Le jardinier a 8 fleurs et 5 fanent. Combien lui en
reste-t-il ?`), minimum 49, moyenne 56,1. Ce qui compte pour un CP n'est pas ce total mais la
structure, et elle est bonne : **chaque enonce est fait de deux phrases courtes**, une situation
d'environ 35 caracteres puis une question d'environ 20. Un lecteur debutant traite deux phrases
brèves bien mieux qu'une seule phrase longue de meme total. Aucun enonce ne depasse deux phrases,
aucun ne porte de subordonnee, aucun ne demande de retenir plus de deux nombres.

**Je confirme aussi qu'aucun enonce ne porte de notation mathematique** ("+", "-", "=") : le critere
est tenu sur les 22 items, et c'est ce qui distingue ce jeu des trois autres du sprint.

La longueur n'est donc pas un motif, ni meme une reserve. Le vocabulaire, lui, l'est (reserve 2).

### Ce qui a ete verifie

- **Ancrage au programme** : "Résoudre des problèmes simples d'addition/soustraction en situation
  (1 étape)", `squad/programme/mathematiques/competences-cp.md`, retrouvee au mot. L'increment
  travaille bien **cette** competence et pas une voisine plus facile : aucune operation n'est ecrite,
  l'enfant doit la choisir. Une seule etape sur les 22 items, verifie. Aucun enonce ne mentionne
  piece, billet ni euro - le perimetre de la ligne "monnaie" differee par `DEC-0019` est respecte.
  **Mais deux items sur 22 exercent l'inverse de cette competence** (section 1).
- **Lisibilite au niveau vise** : bonne dans l'ensemble (section 2). Deux phrases courtes, present de
  l'indicatif, aucune subordonnee. Reserve sur deux points : le vocabulaire (`fanent`) et les deux
  enonces construits sur une virgule (`Le fermier a 5 chats, 2 partent.`), ou un chiffre ouvre la
  seconde proposition - difficile a phraser pour un lecteur debutant.
- **Charge cognitive** : deux nombres a retenir, une operation a choisir, trois propositions. C'est
  le bon calibre pour une competence de resolution de problemes au CP. Le texte est lu **avant** le
  calcul et n'a pas a etre retenu pendant, puisqu'il reste affiche - verifie a l'ecran.
- **Progression** : tirage aleatoire sans remise, verifie sur deux parties complètes : aucun item
  repete dans une meme partie, et les additions et soustractions arrivent melangees, jamais en bloc.
  L'enfant doit donc rechoisir l'operation a chaque manche, ce qui est exactement l'objectif. Pas de
  montee en difficulte a l'interieur d'une partie (meme constat que sur les soustractions, meme
  cause dans la mecanique, meme statut : signale, pas un motif).
- **Traitement de l'erreur** : la mecanique fait ce qu'elle doit (`Non, c'est 7.`, puis indice apres
  trois echecs consecutifs, verifie a l'ecran en manche 4). **Mais sur les deux items de la section
  1, ce traitement se retourne** : l'enfant qui a bien lu se voit dire `Non, c'est 9.` alors que sa
  reponse etait la bonne, et il n'obtient l'explication qui le reconcilierait qu'apres deux autres
  echecs.
- **Exactitude du contenu** : **22 operations verifiees une par une, toutes arithmetiquement
  exactes** ; 11 additions et 11 soustractions, soit la moitie chacune, largement au-dessus du tiers
  exige. **44 distracteurs verifies un par un** : tous entiers, tous distincts de la bonne reponse,
  tous rattachables a une erreur identifiable (l'operation inverse, ou un nombre de l'enonce
  recopie). **22 indices verifies un par un** : tous non vides, integralement accentues, correctement
  orthographies, et tous exacts **sauf les deux de la section 1, qui sont exacts au prix d'un mot
  absent de l'enonce**. Le defaut n'est pas dans l'arithmetique : il est dans la situation, et dans
  un probleme en situation la situation **est** le contenu.
- **Biais et ton** : les prenoms sont varies et bien repartis - Léa, Mia, Zoé (3 filles), Tom, Nino,
  Léo, Ali (4 garcons) ; la presence d'Ali est une bonne chose et je la note. Le ton est neutre,
  jamais condescendant, et aucune situation ne presuppose un logement, un objet couteux ni une
  configuration familiale particuliere - les objets sont des billes, des gommes, des images, des
  jetons. **Une reserve reelle sur les adultes** (reserve 3).

### Motif

**Deux items sur 22 attendent une reponse qui contredit la lecture naturelle de leur propre enonce,
et n'offrent pas la reponse juste parmi leurs propositions.** `Le clown a 7 ballons et en gonfle 2.
Combien en a-t-il ?` attend `9` ; le "en" partitif fait que le clown a toujours 7 ballons, et 7
n'est pas propose (propositions `9`, `5`, `2`). Meme defaut sur `Le jardinier a 6 fleurs et en
plante 2. Combien en a-t-il ?`, qui attend `8` alors que la reponse est 6, non proposee. Que les
deux indices doivent ajouter le mot "d'autres", absent des enonces, pour justifier l'addition, prouve
que le texte lu par l'enfant ne dit pas ce que l'item exige de lui - et l'indice n'apparait qu'apres
trois echecs consecutifs. Sur une competence de resolution de problemes, dont l'objet est justement
de construire un modele a partir d'un texte, un item qui sanctionne la lecture correcte enseigne
l'inverse de ce qu'il vise. Correction attendue : `en gonfle 2 de plus` et `en plante 2 de plus`,
sur le modele de l'item de Mamie (`et en fait 4 de plus`) qui est correct pour cette raison exacte.
Les 20 autres items sont bons et ne sont pas a reprendre.

### Reserves

Reserves nouvelles, datees du 2026-08-12, a convertir en stories par `camille-po`. Elles ne
conditionnent pas la levee du refus, qui se leve avec la seule correction de la section 1. La
reserve 1 gagne cependant a etre traitee dans la meme story, puisqu'elle touche le meme fichier.

1. **Sur les 11 items d'addition, la bonne reponse est a chaque fois le plus grand des trois
   nombres proposes - 11 fois sur 11.** Verifie item par item : `5` contre {1, 2}, `7` contre {1, 3},
   `7` contre {3, 2}, `9` contre {3, 6}, `7` contre {3, 5}, `3` contre {1, 2}, `9` contre {1, 5}, `9`
   contre {5, 2}, `7` contre {1, 4}, `9` contre {7, 1}, `8` contre {4, 2}. La cause est comprehensible
   et plutot bonne en soi : les deux distracteurs sont l'operation inverse et un nombre recopie, tous
   deux necessairement plus petits que la somme. **L'effet, lui, ne l'est pas** : un enfant qui
   remarque que sur une histoire de gain il suffit de prendre le plus grand nombre reussit la moitie
   du paquet **sans jamais calculer**. Il ne s'agit pas d'un distracteur absurde mais d'une
   regularite qui transforme la moitie des items en devinette. Correction : donner a plusieurs items
   d'addition un distracteur **superieur** a la bonne reponse (pour `3 + 2`, un `6` plausible par
   surcomptage plutot qu'un second nombre recopie). Les 11 items de soustraction n'ont pas ce defaut,
   la reponse n'y est jamais le plus grand.
2. **Le mot "fanent" n'est pas du vocabulaire de CP.** `Le jardinier a 8 fleurs et 5 fanent.` - un
   enfant de CP qui ne connait pas "faner" ne peut pas savoir si les fleurs partent, se ferment ou se
   multiplient, donc ne peut pas choisir l'operation. Le verbe est aussi difficile a decoder qu'a
   comprendre. A remplacer par un verbe transparent ("5 sont fanées" ne resout rien ; "il en donne 5"
   ou "5 sont mortes" oui). Meme examen a faire, plus legerement, sur "adopte".
3. **Les trois metiers du paquet sont tous masculins, et la seule adulte est une grand-mere qui fait
   des gateaux.** Le fermier, le clown et le jardinier sont trois hommes sur trois metiers ; Mamie
   est la seule figure adulte feminine et elle est en cuisine. Les prenoms d'enfants, eux, sont bien
   equilibres, ce qui montre que l'attention y etait. A corriger simplement en feminisant un ou deux
   metiers (`La jardinière a 6 fleurs...`, `La clownesse` est maladroit, `Le clown` peut devenir un
   autre metier) : ca ne coute aucun caractere de lisibilite.
4. **Deux enonces sont construits sur une virgule dont la seconde proposition commence par un
   chiffre** : `Le fermier a 5 chats, 2 partent.` et `Le clown a 8 ballons, 4 s'envolent.` Pour un
   lecteur debutant, une virgule ne signale pas assez fort le changement de sujet, et commencer une
   proposition par un chiffre casse la phrase. A reecrire avec "et" comme les 20 autres :
   `Le fermier a 5 chats et 2 partent.`

Deux points de contexte que je n'ouvre pas : **le pli** (non mesure ce sprint, capacite de design
gelee, `DEC-0016` amendee le 2026-08-11 l'assume, `ADR-0028` le declare non gardable - meme position
qu'au sprint 8, ce n'est pas un motif) et **le rendu CSS**, connu insatisfaisant et gele par
l'utilisatrice.

### Ce dont je reste seule garde apres ce verdict

1. **La validite semantique d'une situation** - qu'un enonce, lu correctement, mene bien a la reponse
   attendue. **C'est la propriete qui a laisse passer ce refus** : aucun test ne peut lire une
   histoire et decider si "en gonfle 2" ajoute ou non des ballons. Elle n'est pas gardable
   automatiquement, et je ne demande pas qu'on essaie : c'est moi, et en jouant.
2. **La coherence entre un enonce et son indice** - ici l'indice ajoute un mot que l'enonce ne dit
   pas. `AucunIndiceNeContreditUnAutreItemDuMemePaquetTest` compare les indices **entre items** ; rien
   ne compare un indice a **son propre** enonce.
3. **La regularite de magnitude entre bonne reponse et distracteurs** (reserve 1). Celle-ci, a la
   difference de la 1re, **serait gardable** : un test peut compter les items ou la bonne reponse est
   le maximum des propositions et refuser au-dela d'un seuil. Je le signale a `camille-po` comme une
   propriete testable, sans prescrire la solution technique - c'est `theo-archi` qui tranche.
4. **Le niveau de vocabulaire des enonces** (reserve 2) : aucun garde-fou ne connait le lexique
   attendu au CP.
5. **Les stereotypes de genre, d'origine et de milieu** (reserve 3).
6. **L'exactitude arithmetique de chaque item** et **la plausibilite de chaque distracteur** :
   verifiees a la main, 22 et 44 fois.
