# Fiche de conformite - bandit-manchot-problemes-additifs-cp

- **Mecanique** : bandit manchot (mecanique generique, partagee avec les autres jeux du
  catalogue).
- **Contenu(s)** : `adapters/adapter-content/src/main/resources/contenu/cp-problemes-additifs.yaml`
  (a creer, `type: question`, au moins 20 items), story `S9-4` (`docs/backlog/backlog.md`). Contenu
  neuf, aucune matiere premiere identifiee dans `contenu-repris/` pour cette ligne (le fichier
  `contenu-repris/quiz/cp-monnaie.yml` a un format proche mais porte sur la monnaie, ligne
  explicitement differee par `DEC-0019` - a ne pas reprendre ici).
- **Statut global** : **VALIDE SOUS RESERVE** (Done pedagogique accorde le 2026-08-12, second audit).
  **Le REFUSE du 2026-08-12 est leve** : son motif - deux enonces dont la reponse attendue contredisait
  la lecture correcte - est corrige sur la formulation que j'avais moi-meme ecrite. Le refus n'est pas
  efface : il reste lisible ci-dessous avec sa date.
- **Ligne de matrice** : **peut passer a `couvert-valide`** (ligne "Resoudre des problemes simples
  d'addition/soustraction en situation (1 etape)", `squad/programme/MATRICE-couverture.md`). C'est
  `camille-po` qui l'ecrit, pas moi. **A lire avec les reserves ouvertes** : `couvert-valide` dit que la
  competence nommee par le referentiel est travaillee et validee, pas que ce paquet est sans reserve -
  celle de `DEC-0021` sur la regularite de magnitude reste ouverte et differee hors sprint.
- **Arbitrage prealable de mecanique du 2026-08-12** (`DEC-0023` point 6 : l'ecran intermediaire
  disparait) : ecrit dans `FICHE-bandit-manchot-additions-cp.md`, bloc de validation, section
  "Arbitrage prealable au developpement - la mecanique apres la disparition de l'ecran
  intermediaire". **Il vaut pour ce jeu comme pour les huit du catalogue**, la mecanique etant
  commune : conditions M1 a M13, dont **deux bloquantes** - M4 (le message d'erreur doit etre vu
  sans defiler a 360 x 640) et la clause 1 de M13 (apres une reponse, le focus ne va jamais sur la
  sortie, qui detruit la partie sans confirmation). Ce n'est pas un Done pedagogique, rien n'est
  code.
  **Mise a jour du 2026-08-29 (`camille-po`)** : ce compte est perime depuis le 2026-08-28. `M4` est
  sortie de la porte pedagogique (arbitrage de l'utilisatrice, `DEC-0053`) et ne bloquait deja plus
  depuis `DEC-0025` (2026-08-13) ; sa reference `360 x 640` est morte depuis `DEC-0040` (2026-08-22).
  Seule la clause 1 de `M13` reste bloquante par nature, et elle est tenue depuis le 2026-08-14.
  Detail : `squad/conformite/FICHE-bandit-manchot-additions-cp.md`, en-tete, et
  `squad/conformite/ARBITRAGE-M4-ET-BALAYAGE-DES-CONDITIONS-DE-MECANIQUE-2026-08-29.md`
  (`nadia-prof`). Le texte d'origine ci-dessus n'est pas efface.
  **Ce jeu est vise nommement par la condition M4** : ses enonces font **56 caracteres en mediane et
  67 au plus long**, contre 40 et 61 pour "Le bon verbe" dont j'ai mesure le cabinet sous le pli. Par
  derivation - annoncee comme derivation, je ne l'ai jamais mesure - c'est le jeu de CP a regarder en
  premier pour savoir si le message d'erreur y sera visible sans defiler.
- **Second verdict du 2026-08-13 sur le lot de cloture du sprint 10 (`643c6f4`)** : **REFUSE**,
  aucun Done pedagogique, **un seul motif bloquant** - **M13 clause 1 sur l'ecran de revelation de
  M5**. Sur ce jeu, je n'ai pas tire la configuration fautive, mais le mecanisme y est identique.
  **M4 reste NON TENUE**, de 63,19 px en echec et 83,19 px a la revelation. Le verdict complet et
  l'etat de mes trente-huit conditions sont dans
  `squad/conformite/FICHE-bandit-manchot-additions-cp.md`, section "Second verdict du 2026-08-13". **Le
  refus du matin est corrige sur ses quatorze cas** et les clauses 2, 3, 4 et 5 de M13 sont tenues pour
  la premiere fois. **M4 ne bloque plus**, levee par `DEC-0025`, et mon constat n'est pas retire.
- **Derniere mise a jour** : 2026-08-13

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


### Second verdict du 2026-08-13 sur le lot de cloture du sprint 10 : REFUSE

- **Verdict** : **REFUSE.** Aucun Done pedagogique sur ce jeu.
- **Le verdict complet, ses mesures et l'etat de mes trente-huit conditions sont dans**
  `squad/conformite/FICHE-bandit-manchot-additions-cp.md`, section "Second verdict du 2026-08-13". Le
  meuble et la mecanique sont communs aux sept jeux du catalogue : je ne recopie pas le raisonnement,
  j'ecris ici ce qui est propre a ce jeu.
- **Increment audite** : `round-two` a `643c6f4`, jar **reconstruit depuis mon worktree** et correctif
  verifie dans le HTML servi. `mvn -B verify` : BUILD SUCCESS. Done technique acquis.
- **Mon motif de refus sur ce jeu, unique et bloquant - M13 clause 1 sur l'ecran de revelation** :
  - **Mesure sur ce jeu** : une revelation mesuree, reponse revelee en **position 2 sur 3** - la
    tabulation reste dans les propositions et la partie survit. **Je n'ai donc pas constate la
    destruction sur ce jeu**, et je l'ecris plutot que de la supposer.
  - **Mais le defaut y est present**, comme deduction et non comme mesure : la loi verifiee sur 25
    revelations est que la partie est detruite **si et seulement si** la reponse revelee occupe la
    derniere position, et l'ordre est tire a chaque manche.
- **M4 sur ce jeu, mesuree apres le resserrement de marges** (pire cas de 36 etats de message et de
  4 ecrans de revelation, 360 x 640, pli a `y = 640`) :
  - echec avec indice, pire cas : **703,19 px**, soit **63,19 px SOUS le pli** ;
  - **ecran de revelation, pire cas : 723,19 px, soit 83,19 px SOUS le pli** ;
  - "Bravo !" : 591,19 px, 48,81 px au-dessus.
  - **M4 reste NON TENUE sur ce jeu.** Elle etait a 747,19 px ce matin : le resserrement lui rend 44
    px et il en manque encore 63. Ses indices sont les plus longs du CP - *"Il avait des bonbons et il
    en mange : soustrais 4 de 9."* - et c'est ce qui le fait deborder.
- **Ce qui est corrige sur ce jeu et que je credite** : apres une bonne comme apres une mauvaise
  reponse, Tabulation puis Entree laisse **la partie en cours** ; le focus va sur l'**enonce** de la
  question neuve apres une bonne reponse (clause 4) et sur la **proposition pressee, barree**, apres une
  mauvaise (clause 2) ; sur l'ecran de revelation il va sur la **proposition qui porte la reponse
  revelee** (clause 5). Les quatre clauses sont tenues sur ce jeu, mesurees au clavier.
- **Ce qui est propre a ce jeu** : les enonces sont des phrases entieres a lire, en plus de l'indice.
  C'est le seul jeu de CP dont l'enonce et l'indice sont tous deux longs, et le budget vertical n'y
  suffit pas.
- **Aucune ligne de `squad/programme/MATRICE-couverture.md` ne bouge.**

### Verdict courant - chantier du bandit manchot (mecanique, forme, M10), du 2026-08-13 : REFUSE

- **Verdict** : **REFUSE.** Aucun Done pedagogique sur ce jeu.
- **Le verdict complet, ses mesures et mes trente-deux conditions une par une sont dans**
  `squad/conformite/FICHE-bandit-manchot-additions-cp.md`,
  section "Verdict courant - chantier du bandit manchot (mecanique, forme, M10) (...) du
  2026-08-13". Le meuble et la mecanique sont communs aux sept jeux du catalogue : je ne recopie pas
  le raisonnement, j'ecris ici ce qui est propre a ce jeu.
- **Increment audite** : `round-two` a `2d34a15`. Done technique acquis.
- **Mes deux motifs de refus, tous deux bloquants** :
  - **M4, bloquante** : **747,19 px** en echec avec indice, soit **107,19 px sous le pli**, et
    **749,19 px** sur l'ecran de revelation (**109,19 px** dessous). `ines-design` avait annonce
    ce jeu "recupere avec 47 px de marge" ; le rendu livre donne **107 px de deficit**. C'est
    un jeu de **CP** dont les enonces sont les plus longs du catalogue, et c'est le deuxieme
    cas le plus grave apres "Le bon verbe".
  - **M13 clause 1** : apres une bonne comme apres une mauvaise reponse, le focus reste sur
    `BODY`, **une seule Tabulation atteint le bouton "Les jeux"** (`data-role="sortie"`) et
    Entree **detruit la partie sans confirmation**. Verifie au clavier sur ce jeu. Cause :
    `recreo.js` est charge dans le `<head>` et leve
    `TypeError: Cannot read properties of null (reading 'addEventListener')`, donc l'ecouteur
    qui deplace le focus n'est **jamais enregistre** et `data-focus` est un attribut mort.
- **Ce qui est tenu et que je ferme sur ce jeu** : F16 (la marque "deja essayee" est un mot barre a
  **9,55:1 au minimum** sur les quatre teintes, contre un plancher de 3:1), F9, F10 y compris sa
  clause 3 que je n'avais pas pu fermer le 2026-08-13, F13 a F15 (le levier vit dans ses 20 px et
  n'intercepte aucun appui), F17, F18, F19, M6 (le meuble est identique au pixel dans les quatre
  etats), M8, M9, M10 et M12.
- **Condition neuve M14, datee du 2026-08-13** : apres la revelation, un appui sur une autre
  proposition doit la barrer comme n'importe quelle proposition deja essayee, sans compter d'essai,
  sans changer le message, sans mouvement et sans desactivation. Motif complet en section 6 du
  verdict de reference.
- **Aucune ligne de `squad/programme/MATRICE-couverture.md` ne bouge.**


### Verdict courant - second audit du 2026-08-12, sur le correctif livre : le REFUSE est leve

- **Verdict** : **VALIDE SOUS RESERVE.** Done pedagogique **prononce**. Le motif du refus du
  2026-08-12 - deux items dont la reponse attendue contredisait la lecture naturelle de leur propre
  enonce - est corrige, exactement sur la formulation que j'avais ecrite dans le refus. Les quatre
  reserves du premier verdict restent ouvertes ; elles ne conditionnaient pas la levee, je l'avais
  ecrit, et je ne les transforme pas en motifs maintenant.
- **Date** : 2026-08-12 (second audit du meme jour, apres fusion du lot `sofia-sm` dev).
- **Done technique** : acquis, verifie moi-meme dans mon worktree - `mvn -B verify` en BUILD SUCCESS,
  371 tests, aucun echec.
- **Audit joue** : **non degrade**, et plus large que le premier. Jar construit et lance depuis mon
  worktree `s09-nadia-verdicts-2`, joue par les vraies routes. **60 parties completes de 10 manches,
  soit 600 coups**, tous joues volontairement faux pour maintenir la serie d'echecs et forcer
  l'affichage de l'indice a chaque manche. Resultat : **les 22 items du paquet ont ete servis, et les
  22 indices ont ete lus a l'ecran.** Les deux items corriges - le clown et le jardinier - ont ete lus
  a l'ecran dans leur forme neuve.

#### 1. Ce que j'ai verifie du correctif

Les deux enonces servis a l'ecran sont exactement ceux que j'avais demandes, au caractere :

```
| Lis bien, puis trouve la reponse !
| Le clown a 7 ballons et en gonfle 2 de plus. Combien en a-t-il ?
| Il avait des ballons et il en gonfle d'autres : additionne 7 et 2.
```

```
| Le jardinier a 6 fleurs et en plante 2 de plus. Combien en a-t-il ?
| Il avait des fleurs et il en plante d'autres : additionne 6 et 2.
```

**Le defaut est ferme, et il l'est pour la bonne raison.** `de plus` est un quantifieur additif : il
force la lecture incrementale du groupe et neutralise la lecture partitive de `en`. `il en gonfle 2 de
plus` ne peut plus se lire "il gonfle 2 de ses 7 ballons" ; il se lit "2 ballons s'ajoutent". La
reponse a `Combien en a-t-il ?` est donc bien 9 pour le clown et 8 pour le jardinier, et l'enfant qui
lit correctement trouve maintenant sa reponse parmi les boutons. Le mot `d'autres` des indices, qui
etait la preuve du defaut parce qu'il reparait l'enonce, est devenu coherent avec l'enonce : les
indices n'ajoutent plus rien que le texte ne dise.

**C'est la solution deja employee par l'item de Mamie** (`et en fait 4 de plus`), que j'avais validee
au premier audit pour cette raison exacte. Le paquet est donc homogene sur ce point, et non rapiece.

- **Le correctif ne modifie que les deux lignes d'enonce** : 2 insertions, 2 suppressions dans
  `cp-problemes-additifs.yaml`, diff lu ligne par ligne. Bonnes reponses, distracteurs et indices
  inchanges, comme `sofia-sm` le declare, et comme mon refus le demandait.
- **Les 22 operations et les 44 distracteurs servis a l'ecran correspondent exactement au fichier**,
  verifie sur 600 coups : **zero anomalie**. 11 additions, 11 soustractions, toutes arithmetiquement
  exactes, recalculees une par une.
- **Les 22 items sont tous atteignables** et aucun ne se repete dans une meme partie.
- **La position de la bonne reponse parmi les trois boutons est bien brassee** : 198 fois a gauche,
  194 au milieu, 208 a droite sur 600 coups. Constat mesure de ce second audit, pas une exigence
  anterieure.

#### 2. Je confirme la relecture de `sofia-sm` sur le pronom `en`, en la refaisant

`sofia-sm` declare avoir repasse les 22 items au crible du pronom `en`, et conclut que seuls `gonfle`
et `plante` portaient sur des objets deja possedes. **Je ne prends pas cette declaration pour acquise :
je l'ai refaite, verbe par verbe, et je la confirme.**

- **Additions (11)** : `gagne` (billes), `reçoit` (bonbons), `achète` (crayons), `trouve` (images),
  `gagne` (gommes), `adopte` (chat), `gagne` (jetons), `fait 4 de plus` (gateaux), `gagne` (cartes) -
  neuf verbes qui font tous entrer dans la possession du personnage un objet qui n'y etait pas. Aucune
  ambiguite partitive possible : on ne peut pas gagner, recevoir, acheter, trouver ni adopter un objet
  qu'on possede deja. `gonfle` et `plante` etaient les deux seuls a agir sur des objets deja possedes,
  et ce sont exactement les deux items qui portaient mon motif.
- **Soustractions (11)** : `perd`, `mange`, `casse`, `donne`, `perd`, `partent`, `perd`, `s'envolent`,
  `on en mange`, `perd`, `fanent` - onze verbes qui retirent tous des objets deja possedes, ce qui est
  precisement la lecture juste pour une soustraction. Aucun n'a le defaut symetrique.

Le diagnostic de `sofia-sm` est donc exact et son perimetre est le bon. Le defaut etait localise a deux
items, il l'est reste, et rien d'autre dans le paquet ne le porte.

#### 3. La longueur : je verifie mon propre constat avant de m'en servir, et je ne bouge pas ma barre

`sofia-sm` signale que l'item du jardinier passe a **67 caracteres**, au-dela des **64** que j'avais
mesures comme maximum du paquet, et note que j'avais moi-meme calcule et accepte cet ecart. **Elle a
raison, et je le confirme en chiffres remesures :**

- Avant correctif : maximum **64**, minimum 49, moyenne **56,1** (somme 1235 sur 22 items). Ce sont les
  chiffres de mon premier verdict, et je viens de les recalculer : ils etaient exacts.
- Apres correctif : maximum **67** (`Le jardinier a 6 fleurs et en plante 2 de plus. Combien en
  a-t-il ?`), minimum 49, moyenne **56,9** (somme 1251). Le clown est a **64** exactement, soit le
  maximum d'avant.
- **L'allongement est de 8 caracteres par item corrige.** J'avais ecrit dans le refus, mot pour mot :
  "`de plus` leve l'ambiguite **sans allonger la phrase de plus de huit caracteres**". C'est
  exactement 8. **L'ecart tient dans ce que j'avais autorise moi-meme, au caractere pres.**

**Je ne fais donc de ces 67 caracteres ni un motif ni meme une reserve, et je dis pourquoi
explicitement** : ce serait relever une barre apres coup, sur un depassement que ma propre phrase
avait borne d'avance. Une equipe qui atteint la barre que j'ai ecrite voit sa reserve levee, meme si je
vois desormais mieux. La structure qui fonde mon jugement de lisibilite est d'ailleurs intacte : deux
phrases courtes, une situation puis une question, aucune subordonnee, deux nombres a retenir. Les 3
caracteres de plus tombent dans la premiere phrase, pas dans la question.

**Ce que je note comme constat, pas comme reserve** : le nouveau maximum du paquet est 67 et la
nouvelle moyenne 56,9. Que ces chiffres soient ecrits ici evitera qu'un futur audit croie trouver une
derive en comparant a mes 64 d'avant-correctif.

### Ce qui a ete verifie - second audit du 2026-08-12

- **Ancrage au programme** : "Résoudre des problèmes simples d'addition/soustraction en situation
  (1 étape)", `squad/programme/mathematiques/competences-cp.md`, retrouvee au mot. **Et c'est maintenant
  bien cette competence que les 22 items travaillent, y compris les deux corriges** : au premier audit
  deux items sur 22 exercaient l'inverse de la competence en sanctionnant la lecture correcte ; ils ne
  le font plus. Une seule etape, aucune operation ecrite, l'enfant choisit l'operation. Aucun enonce ne
  mentionne piece, billet ni euro : le perimetre de la ligne "monnaie" differee par `DEC-0019` reste
  respecte, reverifie sur les 22 enonces servis.
- **Lisibilite au niveau vise** : bonne, section 3. Deux phrases courtes, present de l'indicatif, aucune
  subordonnee. Les reserves de vocabulaire (`fanent`) et de virgule restent ouvertes.
- **Vocabulaire** : reverifie sur les 22 enonces affiches. `gonfle` et `plante` sont tous deux du
  lexique de CP et restent transparents avec `de plus`. `fanent` ne l'est pas, et c'est toujours une
  reserve (reserve 2).
- **Charge cognitive** : inchangee et bien calibree. Deux nombres, une operation a choisir, trois
  propositions. Le texte reste affiche pendant le choix - reverifie a l'ecran - donc l'enfant n'a pas a
  le retenir pendant qu'il calcule.
- **Progression** : tirage sans remise verifie sur 60 parties, aucun item repete dans une partie,
  additions et soustractions melangees et jamais en bloc. L'enfant rechoisit l'operation a chaque
  manche, ce qui est l'objectif. **Pas de montee en difficulte a l'interieur d'une partie** : meme
  symptome que sur les soustractions, meme cause dans la mecanique, route a `theo-archi` avec le
  perimetre exact etabli par `camille-po` (nomme ici et sur les soustractions, sans objet sur les
  complements a 10, non releve sur la comparaison). Ce n'est pas un motif.
- **Traitement de l'erreur** : reverifie sur 600 coups. `Non, c'est 7.` puis indice apres trois echecs
  consecutifs, maintenu tant que la serie continue. **Et le retournement que je decrivais au premier
  audit a disparu** : sur les deux items corriges, l'enfant qui lit bien n'est plus contredit par la
  machine. C'etait le coeur de mon refus.
- **Exactitude du contenu** : 22 operations, 44 distracteurs et 22 indices reverifies sur le texte
  servi. Toutes les operations exactes, tous les distracteurs entiers, distincts de la bonne reponse et
  rattachables a une erreur identifiable. **Et les 22 indices sont maintenant tous exacts sans rien
  ajouter a leur enonce** - c'etait le point precis qui rendait mon refus certain plutot
  qu'interpretable.
- **Biais et ton** : inchange. Prenoms varies et bien repartis (Léa, Mia, Zoé, Tom, Nino, Léo, Ali). Ton
  neutre. **La reserve sur les adultes reste entiere** : le correctif ne pouvait pas la traiter, il ne
  touche que deux verbes (reserve 3).

### Motif - second audit

**Le refus est leve.** Les deux enonces fautifs sont corriges en `Le clown a 7 ballons et en gonfle 2
de plus. Combien en a-t-il ?` et `Le jardinier a 6 fleurs et en plante 2 de plus. Combien en a-t-il ?`,
soit exactement les deux formulations que j'avais ecrites dans mon refus pour qu'il n'y ait rien a me
redemander. `de plus` est un quantifieur additif : il neutralise la lecture partitive de `en`, la
reponse attendue devient celle de la lecture correcte, et l'enfant qui modelise bien trouve desormais
sa reponse parmi les propositions. Le mot `d'autres` des indices, qui etait la preuve du defaut parce
qu'il reparait l'enonce, est devenu coherent avec lui. J'ai lu les deux items **a l'ecran**, servis par
les vraies routes, ainsi que les 22 enonces et les 22 indices du paquet, sur 60 parties et 600 coups.
J'ai par ailleurs refait moi-meme la relecture des 22 verbes au crible du pronom `en` et je confirme
que le defaut etait localise a ces deux items et n'existe nulle part ailleurs. Le correctif ne touche
que les deux lignes d'enonce : bonnes reponses, distracteurs et indices sont intacts, verifies contre
le texte servi, zero anomalie sur 600 coups. L'allongement de 8 caracteres tient au caractere pres dans
la limite que j'avais moi-meme ecrite, je n'en fais donc rien. Le Done technique est acquis (`mvn -B
verify` en BUILD SUCCESS, 371 tests). Done pedagogique **prononce**, sous les reserves ci-dessous.
**La ligne "Resoudre des problemes simples d'addition/soustraction en situation (1 etape)" de
`squad/programme/MATRICE-couverture.md` peut passer a `couvert-valide`** - c'est `camille-po` qui
l'ecrit, et avec la reserve `DEC-0021` ouverte, dite explicitement en tete de fiche.

### Reserves - au 2026-08-12, second audit

Les quatre reserves du premier verdict sont **reprises telles quelles et toujours ouvertes**. Aucune
n'est nouvelle : ce second audit n'en ajoute pas.

1. **Sur les 11 items d'addition, la bonne reponse est 11 fois sur 11 le plus grand des trois nombres
   proposes.** Toujours vraie apres correctif - je l'ai reverifiee, le correctif ne touche aucune
   proposition. **Tranchee par `camille-po` en `DEC-0021` : differee hors sprint, story ouverte au
   backlog reprenant mes criteres au mot.** Je prends acte de cet arbitrage et je ne le rouvre pas : la
   reserve ne conditionnait pas la levee, je l'avais ecrit, et le fichier etait deja touche par le
   correctif borne. Elle reste **la reserve la plus consequente de ce paquet** - un enfant qui remarque
   la regularite reussit la moitie des items sans calculer - et c'est pour cela que je redis qu'elle est
   ouverte alors que la ligne de matrice passe.
2. **Le mot `fanent` n'est pas du vocabulaire de CP.** Reprise sans changement.
3. **Les trois metiers du paquet sont tous masculins, et la seule adulte est une grand-mere qui fait
   des gateaux.** Reprise sans changement. Le correctif ne pouvait rien y faire.
4. **Deux enonces sont construits sur une virgule dont la seconde proposition commence par un
   chiffre** (`Le fermier a 5 chats, 2 partent.`, `Le clown a 8 ballons, 4 s'envolent.`). Reprise sans
   changement.

Deux points de contexte que je n'ouvre pas, position inchangee depuis le sprint 8 : **le pli** (non
mesure ce sprint, capacite de design gelee, `DEC-0016` amendee le 2026-08-11 l'assume, `ADR-0028` le
declare non gardable - reserve et jamais motif) et **le rendu CSS**, connu insatisfaisant et gele par
l'utilisatrice. Je n'ai mesure aucun pixel ce sprint : les chiffres de la section 3 sont des comptes de
caracteres, pas des mesures de rendu.

### Ce dont je reste seule garde apres ce second verdict

Le correctif est fusionne et la CI reste verte, mais **aucun garde-fou neuf ne couvre les proprietes
qui ont laisse passer ce refus.**

**Relecture de cette liste du 2026-08-12, apres une erreur de fait trouvee dans mes trois autres
fiches de ce sprint.** J'y avais ecrit que l'exactitude arithmetique des items n'etait tenue par
aucun test ; c'etait faux pour les trois paquets concernes, qui portent chacun un test dedie le
faisant. J'ai donc reverifie cette liste-ci, point par point, dans les fichiers de test. Deux
resultats, en sens opposes.

- **Le point 6 est exact, et il l'est pour une raison qu'il faut nommer** : `cp-problemes-additifs`
  est le seul paquet neuf de ce sprint **sans test dedie** (`git` ne porte aucun
  `PaquetCpProblemesAdditifsTest`), et aucun garde-fou generique ne verifie une operation. Ce que
  `yanis-qa` chiffre en section 7 de
  `squad/sprints/s09-mathematiques-cp-nombres-et-calculs/rapport-qualite.md` : "aucun test, generique
  ou existant, ne verifie que les 22 operations de `cp-problemes-additifs` sont arithmetiquement
  exactes". Mon point 6 est donc a lire comme propre a ce paquet et non comme un constat sur le
  depot - les soustractions, les complements a 10 et la comparaison de nombres, eux, sont gardes.
- **Le point 2 me creditait un garde-fou de plus qu'il n'en fait, et je le corrige dans ce sens
  aussi** : voir le point 2 reecrit. Une liste inexacte est inutile dans les deux sens, pas seulement
  quand elle sous-estime.

1. **La validite semantique d'une situation** - qu'un enonce, lu correctement, mene bien a la reponse
   attendue. **C'est la propriete qui a laisse passer ce refus, et elle n'est pas gardable
   automatiquement** : aucun test ne peut lire une histoire et decider si `en gonfle 2` ajoute des
   ballons ou non, ni que `de plus` change cela. Je ne demande pas qu'on essaie. C'est moi, et en
   jouant.
2. **La coherence entre un enonce et son indice, et - correction du 2026-08-12 - la coherence entre
   items elle-meme, que j'avais creditee a tort.** Rien ne compare un indice a **son propre** enonce :
   cela n'a pas change. Mais j'ecrivais aussi, ici et dans mes trois autres fiches, que
   `AucunIndiceNeContreditUnAutreItemDuMemePaquetTest` comparait les indices **entre** items, comme si
   cette moitie-la etait couverte. **Sur le contenu servi aujourd'hui, elle ne l'est pas** : la clause
   de production de ce test, `aucunIndiceDeProductionNeCorrespondALaFormeGeneraleAujourdHui`, epingle
   a **zero** le nombre d'indices de production reconnus par la forme qu'elle sait juger, et son
   message demande de la supprimer le jour ou ce compte cesse de valoir zero. Autrement dit ce
   garde-fou ne juge aucun indice d'aucun paquet de production a ce jour ; il ne mord que sur son
   contre-exemple de test. Trouve par ma propre relecture du fichier de test, pas signale par un
   tiers. La coherence des indices de ce paquet, c'est donc entierement moi, et c'est ce que j'ai lu a
   la main pour constater que `d'autres` est redevenu coherent.
3. **La regularite de magnitude entre bonne reponse et distracteurs** (reserve 1). Celle-ci **serait
   gardable** par un test qui compte les items ou la bonne reponse est le maximum des propositions.
   `DEC-0021` la differe hors sprint ; tant qu'aucun test n'existe, elle n'est tenue par rien du tout, et
   je le dis pour que `couvert-valide` ne soit pas lu comme une couverture de cette propriete.
4. **Le niveau de vocabulaire des enonces** (reserve 2) : aucun garde-fou ne connait le lexique de CP.
5. **Les stereotypes de genre, d'origine et de milieu** (reserve 3).
6. **L'exactitude arithmetique de chaque item** et **la plausibilite de chaque distracteur** :
   verifiees a la main, 22 et 44 fois, deux fois maintenant.
7. **Le fait que tous les items d'un paquet soient reellement atteignables** et que les propositions
   servies soient celles du fichier : verifie moi-meme sur 600 coups, faute de garde-fou.

### Verdict du premier audit - S9-4, prononce sur l'increment livre, du 2026-08-12 (REFUSE, leve le meme jour au second audit ; conserve, jamais efface)

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
   ne compare un indice a **son propre** enonce. **Correction du 2026-08-12** : la premiere moitie de
   cette phrase me creditait trop. Sur le contenu de production servi aujourd'hui, ce garde-fou ne
   juge aucun indice - sa clause de production epingle a zero le nombre d'indices qu'elle reconnait.
   Voir la correction datee de la section "Ce dont je reste seule garde apres ce second verdict".
3. **La regularite de magnitude entre bonne reponse et distracteurs** (reserve 1). Celle-ci, a la
   difference de la 1re, **serait gardable** : un test peut compter les items ou la bonne reponse est
   le maximum des propositions et refuser au-dela d'un seuil. Je le signale a `camille-po` comme une
   propriete testable, sans prescrire la solution technique - c'est `theo-archi` qui tranche.
4. **Le niveau de vocabulaire des enonces** (reserve 2) : aucun garde-fou ne connait le lexique
   attendu au CP.
5. **Les stereotypes de genre, d'origine et de milieu** (reserve 3).
6. **L'exactitude arithmetique de chaque item** et **la plausibilite de chaque distracteur** :
   verifiees a la main, 22 et 44 fois.
