# DEC-0061 - Le score parfait par elimination sur 94,6 pour cent des items est un **defaut a
# corriger**, et non un score decoratif de plus a assumer. `DEC-0044` n'est pas rouverte sur ce
# qu'elle a juge. Non engage a s24 : premier report, compte comme tel

- **Type** : arbitrage de produit sur une reserve pedagogique elargie, routage technique, rang de
  backlog
- **Date** : 2026-08-31
- **Auteur** : camille-po
- **Sprint** : s24 (en cours)
- **Competence programme visee** : **transverse - pas de contenu pedagogique.** Le defaut est une
  propriete de la mecanique `M5` face au nombre de propositions d'un item, jamais un defaut de
  contenu ; `nadia-prof` l'ecrit dans ces termes et je le reprends d'elle.
- **Statut** : Acceptee
- **Lien** : obstacle 2 du planning de s24, rendu par `sofia-sm` ; reserve 5 de `nadia-prof`,
  elargie le 2026-08-30 ; `DEC-0044`, section 1 ; `DEC-0060`, section 7.

## 0. Ce que ce document tranche, et ce qu'il refuse de trancher

**Il tranche ce que ce fait vaut pour le produit** : `sofia-sm` m'en a rendu le fond en l'excluant de
s24 pour capacite, et c'est exactement la question qui m'appartient.

**Il ne tranche pas comment `M5` doit changer.** Le nombre d'essais, sa modulation eventuelle, ou
toute autre forme de correction sont a `theo-archi`. **Il ne tranche pas non plus ce qu'un enfant
doit pouvoir faire d'une manche** : c'est a `nadia-prof`, et elle a ecrit qu'elle ne le tranchait pas
non plus tant que la question de produit n'etait pas rendue.

**Il ne rouvre pas le perimetre de s24**, gele par `DEC-0059`, et il ne demande a aucun developpeur
d'ouvrir quoi que ce soit dans ce sprint.

---

## 1. Le fait, avec sa source et ce que j'ai verifie moi-meme

**De `nadia-prof`, arbitrage prealable de s24, section 7.1, date du 2026-08-30 - chiffre, population
et methode sont d'elle, je ne les ai pas reproduits** : sur les 615 items enumeres des 27 paquets,
**534 portent trois propositions et 48 en portent deux, soit 582 - 94,6 pour cent**. Seuls **33
items** en portent quatre. Sur les 582, un enfant qui presse au hasard sans jamais represser une
proposition deja barree **a necessairement raison au dernier essai, et ce dernier essai compte comme
une reussite**.

**Ce que j'ai verifie par moi-meme**, dans
`domain/src/main/java/com/recreo/domain/mechanics/banditmanchot/BanditManchotSession.java` sur l'arbre
fusionne de `s24-sofia` - lecture de la classe, aucune mesure, aucun build :

- `ESSAIS_AVANT_REVELATION` vaut **3** ;
- `jouer` evalue `estLaBonneReponse` **avant** d'incrementer `essaisSurLaManche` : une bonne reponse
  ne consomme jamais d'essai ;
- `resoudre(true)` fait `score.reussites() + 1` **sans regarder `essaisSurLaManche`** : une manche
  resolue au troisieme essai vaut exactement une manche resolue au premier ;
- `propositionsEssayees` conserve les propositions pressees, que `M9` affiche barrees sans les
  retirer.

**Le mecanisme decrit par `nadia-prof` est bien celui du code.** Sur un item a trois propositions,
deux erreurs epuisent les distracteurs et le troisieme appui est la bonne reponse, comptee juste.

---

## 2. Ce que je tranche : c'est un defaut, pas un score decoratif

**Decision : le score parfait par elimination sur les items a deux et trois propositions est un
defaut du produit, a corriger. Je refuse de l'assumer comme un score decoratif de plus.**

Quatre motifs, dans l'ordre de leur poids.

### 2.1 Un score qui vaut 10 sur 10 quoi que l'enfant sache ne motive pas non plus

`DEC-0044` a defendu le score decoratif par une phrase que je reprends parce qu'elle est juste :
*"le score existe pour motiver, pas pour classer"*. **C'est precisement ce motif qui se retourne a
94,6 pour cent.** Un enfant qui ne sait rien et un enfant qui sait tout terminent la meme partie sur
le meme ecran, avec le meme chiffre. Le score ne classe pas - il ne dit plus rien du tout, et un
signal constant ne motive personne. **Le produit affiche un unique retour de fin de partie, et sur
94,6 pour cent de ses items ce retour ne porte aucune information.**

### 2.2 Le fait a change de nature, pas de degre - et c'est ce qui autorise a le reposer

`DEC-0044` a tranche le 2026-08-24 sur les items a **deux** propositions. Population visee alors :
**48 items sur 615**, soit 7,8 pour cent. Population visee aujourd'hui : **582 sur 615**, soit 94,6
pour cent. **Un facteur douze.**

A 7,8 pour cent, c'est une propriete marginale d'une poignee de paquets, et le motif de `DEC-0044` -
*"le cout de reparer serait plus haut que le cout du defaut"* - est un arbitrage raisonnable. A 94,6
pour cent, **ce n'est plus une propriete du produit, c'est le score du produit**. Le meme calcul de
cout ne rend pas le meme resultat, et le refaire n'est pas revenir sur une decision : c'est la
premiere fois que la question est posee sur cette population-la.

**`DEC-0044` n'est pas rouverte et n'est pas remplacee.** Elle reste exacte sur ce qu'elle a juge, a
la date ou elle l'a juge, avec la population qu'elle avait. Ce que `DEC-0061` ajoute est la question
neuve que le chiffre du 2026-08-30 fait apparaitre.

### 2.3 Le contre-argument central de `DEC-0044` est propre a deux propositions et ne se transporte pas a trois

`DEC-0044` ecrivait, contre une reduction du nombre d'essais : *"un enfant qui a droit a deux essais
sur un item a deux propositions n'a, par construction, plus aucune chance d'apprendre de son erreur
au second essai non plus, ce qui deplace le probleme plutot que de le resoudre"*. **C'est exact a
deux propositions, et c'est faux a trois.** Sur un item a trois propositions, un enfant a qui l'on
laisserait deux essais et qui presse au hasard se trompe encore une fois sur trois : l'echec redevient
possible, la reussite redevient un renseignement. **Le motif qui fermait la question sur 48 items ne
la ferme pas sur les 534 autres.** C'est de l'arithmetique sur la mecanique telle qu'elle est, pas une
proposition de solution : le choix du remede reste entier et il n'est pas de moi.

### 2.4 Je ne peux pas tenir le meme critere dans un sens et pas dans l'autre

`nadia-prof` chiffre la valeur de la mecanique de saisie en ces termes, et le planning de s24 la
reprend comme ce qui remplace un chiffre de couverture faux : *"la saisie est la premiere mecanique
de ce produit ou un enfant qui ne sait pas peut echouer"*. **C'est le motif qui justifie d'ouvrir une
seconde mecanique dans ce sprint.** Si ce critere vaut assez pour ouvrir une mecanique neuve, alors le
fait qu'un enfant qui ne sait pas ne puisse pas echouer sur 94,6 pour cent du produit existant est un
defaut par le meme critere. **Je ne peux pas m'en servir pour ouvrir la saisie et le declarer sans
objet sur le reste du catalogue.**

---

## 3. Ce que cette decision ne fait pas, et ce serait facile de le lire de travers

- **Elle ne retire aucun Done pedagogique** et n'en conteste aucun. `nadia-prof` l'ecrit elle-meme :
  *"Je ne refuse rien et je ne retire aucun Done. Tous ces paquets ont ete valides sur leur contenu,
  qui est juste."* Le defaut est dans `M5`, pas dans les items.
- **Elle ne fait bouger aucune ligne de la matrice de couverture**, ni dans un sens ni dans l'autre.
  Aucune competence n'est mieux ou moins bien servie par cette decision : c'est le compteur de fin de
  partie qui est en cause, pas ce que le jeu enseigne. **La matrice reste a 24/43 apres cette
  decision, inchangee.**
- **Elle ne demande le retrait d'aucun jeu du catalogue.** `DEC-0045` et `DEC-0055` gouvernent le
  retrait sur `REFUSE` ; il n'y a ici aucun `REFUSE`.
- **Elle ne dit pas comment corriger.** Voir la section 5.
- **Elle ne juge pas la qualite pedagogique d'un contenu.** Ce n'est pas mon travail et rien ici ne
  s'en approche.

---

## 4. Ce que ce n'est pas engage a s24, et le report est compte

**Rien de ce document n'entre au perimetre de s24.** Le motif est celui de `sofia-sm`, et il est le
sien : neuf items sont deja affectes a trois developpeurs, l'objectif est gele, et toucher `M5`
ferait bouger le comportement des 27 jeux existants dans le sprint meme ou une seconde mecanique
nait. **Je ne le rouvre pas. Ce motif tient.**

**Mais un report se compte, et celui-ci est compte ici.**

- **Ce report-ci est le premier de la version elargie, et il est date : s24, 2026-08-31.**
- **La version etroite du meme fait avait deja ete routee trois fois** avant d'etre tranchee :
  s04 (reserve 5 ouverte), s17 (reserve 5 redite, non tranchee), s19 (`DEC-0044`, section 1, qui
  ecrit elle-meme *"ce fait m'a ete route trois fois sans que je tranche"*).
- **Si ce sujet n'est pas engage a s25**, c'est ce paragraphe-ci qui devra etre relu avant d'ecrire
  le motif du deuxieme report. `DEC-0007` a tenu la couverture programme fermee trois sprints
  d'affilee, chaque fois pour un motif defendable, jusqu'a ce que l'utilisatrice demande elle-meme
  pourquoi le produit n'avait qu'un seul jeu.
- **Rang que je lui donne : candidat de tete a l'ouverture de s25**, devant les dettes d'outillage
  des obstacles 4, 6 et 7 du planning de s24 - il touche ce qu'un enfant voit, elles touchent ce
  qu'un agent lit.

---

## 5. Routage - la decision a des proprietaires, et c'est la moitie du geste

**Une decision qui implique un changement de code n'est pas prise tant que ce changement n'a pas de
proprietaire.** `DEC-0009` a coute a ce depot un jeu refuse reste jouable parce que la decision
etait ecrite et le catalogue inchange. Voici les proprietaires, et l'ordre dans lequel ils doivent
rendre.

| # | Ce qui est du | Proprietaire | Pourquoi lui, et pas moi |
|---|---|---|---|
| 1 | **Le resultat pedagogique attendu** : ce qu'un enfant doit pouvoir faire d'une manche quand le nombre de propositions rend l'elimination possible - et ce que "trois essais" doit vouloir dire dans ce cas | `nadia-prof` | C'est un jugement sur ce qu'un enfant apprend d'un essai. Elle a ecrit que ce n'etait pas de son ressort de trancher le remede ; le resultat attendu, lui, est de son ressort et de lui seul |
| 2 | **La forme de la correction dans `M5`** : nombre d'essais, modulation par la cardinalite, comptage du score, ou toute autre voie, avec son `ADR` et sa regle executable | `theo-archi` | Mecanique. Je n'y touche pas, et je note que trois formes au moins sont concevables : je n'en designe aucune |
| 3 | **Le cout du changement sur les 27 jeux existants**, et si la correction est fractionnable | `theo-archi` | Mesure technique. Aucun chiffre ne doit etre avance sans sa methode et son perimetre |
| 4 | **Le sort des Done deja prononces sur les 27 jeux** que la correction ferait bouger | **l'utilisatrice**, question posee par `DEC-0060`, section 8, Q2 | Ce n'est ni a moi, ni a `nadia-prof` : c'est le perimetre de la porte pedagogique, que l'utilisatrice vient d'arbitrer, et la correction de `M5` est exactement le cas que son arbitrage ne tranche pas |
| 5 | **La story et son rang de backlog** | `camille-po` (moi) | **Faite dans le meme geste** : `S25-SCO-1`, `docs/backlog/backlog.md` |
| 6 | **L'affectation, si le sujet entre a s25** | `sofia-sm` | Capacite et sequencement |

**Ordre : 1 avant 2.** `theo-archi` ne peut pas choisir la forme d'une correction avant que le
resultat attendu ne soit ecrit ; c'est la separation que ce produit tient partout ailleurs -
`nadia-prof` fixe le resultat, jamais le moyen.

**Le point 4 est bloquant pour la livraison, pas pour l'instruction.** Les points 1, 2 et 3 peuvent
etre rendus sans que Q2 soit tranchee. Ce que Q2 decide, c'est ce qui se passe apres la fusion.

---

## 6. Ce que le sprint en cours doit en porter, et rien de plus

- L'obstacle 2 du planning de s24 **est clos comme routage** : la `DEC` demandee existe sur le
  disque, ce qui etait la seule condition ecrite de sa fermeture. **Le sujet, lui, reste ouvert** -
  et il l'est jusqu'a ce que `S25-SCO-1` soit livree et validee.
- **Aucun developpeur de s24 n'a rien a faire de ce document.**
- La review et le `RAPPORT.md` de s24 le portent comme **candidat de tete a l'ouverture de s25**,
  avec les points 1 et 2 de la section 5 nommes et non rendus.
