# Review du sprint s10 - la forme du bandit manchot

> Ce fichier est ouvert par `nadia-prof` avec la seule section dont elle est l'auteure : la
> conclusion de la porte pedagogique. Les autres sections de la review (avancement, demonstration,
> engagement du sprint suivant) appartiennent a `sofia-sm` et a `camille-po`, qui les ajoutent
> ici. Une section absente n'a pas ete ecrite ; elle n'est pas prononcee.

## Porte pedagogique - arbitrage du 2026-08-13 : l'indice appele par l'enfant (une ampoule)

> Cette section est ma reponse a une proposition qui touche ma condition M1, adressee apres mon
> refus. **Elle ne contient aucun Done pedagogique et elle ne leve rien** : mon refus du 2026-08-13
> sur M4 et sur M13 clause 1 tient entier. L'arbitrage complet est dans
> `squad/conformite/FICHE-bandit-manchot-additions-cp.md`, section "Arbitrage prealable au
> developpement - l'indice appele par l'enfant (une ampoule), du 2026-08-13".

### La proposition et ma reponse

**Proposition** : l'indice ne s'affiche plus de lui-meme ; l'enfant l'appelle, par une boite qu'on
ouvre ou une ampoule.

**Reponse** : **ACCEPTE dans son principe, sous six conditions M16 a M21, dont M17 bloquante.**
**Je cede l'affichage automatique sur les essais 1 et 2, et je garde deux morceaux de M1** :

| Ce que M1 disait | Ce qu'il en reste |
|---|---|
| l'indice est **rattache a la question qui bloque** | **intact** |
| **au premier echec**, quelque chose vient a l'enfant | **intact, sous une autre forme** : l'ampoule se marque au premier echec (M16 clause 2), et l'enfant ouvre d'un appui |
| l'indice **s'affiche** de lui-meme | **cede sur les essais 1 et 2** ; garde sur l'ecran de revelation (M17 clause 2), ou il n'y a plus rien a divulguer |

**L'affaiblissement, dit comme tel : un appui.** Un enfant de six ans en difficulte n'appelle pas
une aide - c'est mon jugement de metier, il n'est pas mesure, et c'est la seule chose de cette
reponse que je ne peux pas etayer par un chiffre (**reserve R**). C'est pour cela que je refuse une
aide seulement *disponible* et que j'exige qu'elle s'annonce.

### Les trois faits mesures qui me font accepter

1. **Sur "Le bon verbe", l'aide automatique n'est pas une aide, c'est la reponse.** L'utilisatrice
   l'a constate en jouant, je l'ai reproduit : **un seul appui faux**, et l'indice dit *"il devient
   'va'"*. **25 indices sur 25** de ce paquet nomment la bonne reponse.
2. **L'aide automatique coute a l'enfant le texte qui lui dit qu'il s'est trompe.** La boite
   d'indice pese **58 a 148 px** ; c'est elle qui pousse "Ce n'est pas ça. Essaie encore !" sous le
   pli sur quatre jeux qui l'auraient garde. Entre les deux, celui qui doit rester est celui qui dit
   ou l'enfant en est.
3. **Deux blocs de texte a la fois, pour un lecteur de CP, c'est un de trop.**

### Ce que l'ampoule rend, et ce qu'elle ne rend pas - 49 etats joues a 360 x 640

| | Etats d'essai | Reste a payer |
|---|---|---|
| Cinq jeux de CP legers | de **+11,19 / +41,19** a **-84,81 px** : **tenus** | rien dans ces etats |
| Petits problemes | de +107,19 a **+11,19 px** | **+11,19 px** |
| Le bon verbe | de +251,19 a **+95,19 px** (enonce long) | **+95,19 px**, et **-0,81 px** seulement sur les items a enonce court |
| Le bon verbe, **"Bravo !"** | **+67,19 px sur les items a enonce long, inchange** | **je ne credite rien a M11 de l'espace libere** |
| Ecrans de revelation | inchanges, l'indice y reste (M17 clause 2) | +13,19 a +251,19 px |

**Donc : allegement reel, remede a M4 non.** Mon refus n'est pas leve d'un pixel, et le vrai sujet
reste le budget vertical d'un ecran a 360 x 640 - ma **reserve P**.

### Mes six conditions, en une ligne chacune

| # | Ce qu'elle exige |
|---|---|
| **M16** | l'ampoule est la des le premier rendu, et elle **se marque** des le premier echec, sans qu'un pixel de la page bouge |
| **M17** | **bloquante.** L'indice ouvert est **entierement au-dessus du pli** ; ni sous le pli, ni en recouvrant les propositions. Et il **s'affiche seul sur l'ecran de revelation** |
| **M18** | le bas du cabinet **ne descend sur aucun jeu** : 455,19 / 551,19 / 539,19 et 635,19 px, mesures ce jour |
| **M19** | elle porte le mot **"Aide"**, texte au moins 20 px, cible au moins 44 x 44 px, pictogramme en plus du mot et jamais a sa place |
| **M20** | hors du contour du cabinet (C2) ; l'ouvrir n'est pas un essai, ne deplace aucune proposition, et Tabulation puis Entree ne detruit jamais la partie |
| **M21** | **pas d'ampoule sur un paquet dont les indices nomment la reponse** - "Le bon verbe" n'en portera pas. C'est M2 transposee, pas une condition neuve |

### Trois de mes propres constats que je corrige

1. **Mon 251,19 px etait le pire cas, pas l'etat du jeu** : sur "Le bon verbe" le deficit depend de
   l'item tire, **125,19 a 251,19 px**.
2. **Un seul paquet sur huit porte des explications** (`cp-le-bon-son`, 6 items sur 15). Ma ligne M7
   decrivait la revelation comme portant une explication : c'etait vrai d'un jeu, pas des sept. **Six
   jeux sur sept revelent la reponse sans rien expliquer**, et c'est le motif de M17 clause 2.
3. **Un garde-fou litteral "l'indice contient la bonne reponse" est faux** : il disqualifierait deux
   indices de CP parfaitement sains (`9 + ? = 10`, `10 + ? = 10`). Ma correction du matin - quatre
   indices de CP, tous sans consequence - tient.

### Ce que je laisse aux autres

- **La forme de l'ampoule** : `ines-design` propose, j'arbitre. Mes contraintes sont M17 a M20 et
  elles sont chiffrees.
- **La route technique** : `theo-archi`.
- **Le backlog** : `camille-po`. Six conditions, deux reserves neuves (**R**, **S**), et une priorite
  que je nomme - **reserve A**, reecrire les 25 indices de `cm1-conjugaison-present`, cesse d'etre une
  amelioration et devient ce qui rend "Le bon verbe" enseignable.

### Ce dont je reste la seule garde

Mes six points du verdict du matin, plus deux que cette reponse ajoute : **qu'une aide appelee
montre quelque chose a l'enfant qui l'appelle**, et **qu'une ampoule se marque quand l'enfant est
bloque**. Aucun test ne compare une ordonnee au pli, aucun ne compare deux rendus successifs de la
meme question. Tant que ces garde-fous n'existent pas, c'est moi le garde-fou, et je le suis en
jouant.

### Ce que cet arbitrage est degrade sur - dit avant mes conclusions

Trois points nommes : les appuis sont **programmes et non faits au doigt** ; **je n'ai pas joue
l'ampoule**, elle n'existe nulle part, et mes chiffres sur ce qu'elle rendrait sont une soustraction
sur un rendu reel, pas la mesure d'un ecran ; **je n'ai pas observe d'enfant**, et c'est la question
qui decide (reserve R).
