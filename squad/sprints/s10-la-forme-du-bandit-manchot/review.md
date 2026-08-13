# Review du sprint s10 - la forme du bandit manchot

> Ce fichier est ouvert par `nadia-prof` avec la seule section dont elle est l'auteure : la
> conclusion de la porte pedagogique. Les autres sections de la review (avancement, demonstration,
> engagement du sprint suivant) appartiennent a `sofia-sm` et a `camille-po`, qui les ajoutent
> ici. Une section absente n'a pas ete ecrite ; elle n'est pas prononcee.

## Porte pedagogique - reponse de principe du 2026-08-13 : le lieu de l'indice

> Cette section est la reponse a une question de principe posee apres mon refus. **Elle ne contient
> aucun Done pedagogique et elle ne leve rien** : mon refus du 2026-08-13 sur M4 et sur M13 clause 1
> tient entier. La reponse complete est dans
> `squad/conformite/FICHE-bandit-manchot-additions-cp.md`, section "Reponse de principe - ce que le
> lieu 'sous le meuble' portait pour l'indice (M1), du 2026-08-13".

### La question et la reponse

**Question** : ma condition M1 place l'indice sous le meuble, des le premier echec, sur la question
qui bloque. Le lieu portait-il quelque chose, ou seul le rattachement comptait ?

**Reponse** : **le lieu portait quatre choses, et aucune des quatre n'est ce que M1 protege.**
L'indice peut donc remonter au-dessus du meuble sans qu'aucune de mes exigences pedagogiques ne cede.

| Ce que le lieu portait | A qui cela appartient |
|---|---|
| rien ne bouge au-dessus du point ou l'enfant appuie (65 a 155 px de deplacement des colonnes dans l'autre position) | **M8** |
| un seul endroit ou le jeu parle | **M6 clause 1** |
| l'ordre vertical : le verdict au-dessus de l'aide, donc le premier a echapper au pli | **M4** |
| des pixels | **personne - le lieu n'en porte aucun** |
| le rattachement a la question qui bloque, et le premier echec comme moment | **M1, et c'est toute sa substance** |

**Le fait qui tranche** : avant ce sprint l'indice etait deja au-dessus du meuble. Ce que j'ai refuse
le 2026-08-12 n'etait pas sa position, c'etait qu'il n'atteignait pas l'enfant bloque - *"un enfant
qui rate neuf manches sur dix recoit deux aides, et les deux portent sur le seul item qu'il avait
reussi."* **C'est le rattachement qui faisait le travail, et il ne se paie pas en geometrie.** Le
retour au lieu d'avant ce sprint n'est pas un retour a l'etat d'avant ce sprint.

### M6 s'y oppose, et c'est M6 qui cede

Oui, elle s'y oppose : j'avais enumere l'indice moi-meme dans sa clause 1. **Elle cede, et pas parce
que la geometrie l'exige - parce que mon enumeration etait fausse.** M6 parle de ce que le jeu dit de
la **reponse de l'enfant** ; l'indice parle de la **question**. Verifie ce jour sur les **146 indices
des huit paquets servis, un par un : aucun** ne mentionne ce que l'enfant a presse. J'avais range
l'indice avec le verdict parce qu'il arrive au meme moment, et arriver au meme moment n'est pas parler
de la meme chose.

**La clause 2 n'est pas touchee** - le meuble rigoureusement identique dans les quatre etats, que
j'ai appelee le point de garde le plus important de ce chantier, et qui est tenue au pixel dans
l'increment livre.

### M4 ne retrecit pas a deux elements : elle se scinde

Sa phrase contient une **barre** (trois elements au-dessus du pli) et un **lieu** (sous le meuble).
**Le lieu tombe, la barre reste** : les trois elements sont mesures chacun la ou il vit desormais.

**Motif, et il n'est pas formel** : une M4 qui ne compterait plus l'indice deviendrait verte sur
plusieurs jeux **le jour ou l'indice sort du bloc mesure, c'est-a-dire sans qu'un seul pixel ait bouge
pour l'enfant.** J'ai refuse cette forme de vert deux fois ce sprint - sur M10, en exigeant un temoin
dans la meme execution, et sur M13 clause 3, comptee non tenue parce qu'elle n'etait tenue que par la
panne qui cassait ses voisines. Je ne l'accepte pas davantage contre ma propre condition.

Aucun des trois elements ne peut ceder, parce qu'aucun n'est couvert ailleurs : la revelation est le
seul endroit qui dit sur quoi appuyer et son absence est un cul-de-sac ; le message est le seul qui
dit "essaie encore" ; l'indice est la seule chose qui apprend quelque chose au moment ou l'enfant est
bloque.

### Ce que je dois dire avant que l'equipe construise - une derivation, pas une mesure

**Remonter l'indice rend 0 px au message.** Le gabarit servi rend un seul flux vertical de blocs et
`.message-jeu` ne porte aucune propriete dans `recreo.css` : `.indice` a sa propre boite au-dessus
comme en dessous. Deplacer un bloc dans un flux vertical ne change pas la hauteur du flux. Sur "Le
bon verbe", avec le releve d'`ines-design` et le mien : le bas de tout ce que le jeu dit reste a
**891 px** dans les deux positions, et la ligne de verdict passe de 736 a **891 px**. Les 65 a 155 px
de sa mesure sont reels, mais ils sont **gagnes par le cabinet et repris au bloc du dessous dans le
meme mouvement**.

**Ce que la direction choisie change n'est pas une quantite de pixels, c'est lequel des deux textes
est sacrifie.** Je l'annonce comme une derivation - **je n'ai pas joue l'indice remonte, il n'existe
nulle part** - et si la mesure d'`ines-design` sur le rendu reel la contredit, c'est sa mesure qui
vaut et je le dirai comme une correction de mon propre constat.

### Ce que je produis pour `camille-po`

- **Une condition neuve, M15** : l'indice se lit comme une partie de la question et non comme un
  second verdict (aucun signe visuel partage avec le bloc du verdict), et un seul indice a la fois,
  celui de la question courante. C'est le prix de la cession de M6 clause 1, pas un durcissement.
- **Une reserve neuve, Q**, a la suite de mes reserves L a P du meme jour : **la direction choisie
  deplace un defaut d'une condition bloquante (M4) vers une condition qui ne l'est pas (M8)**, puisque
  l'apparition d'un indice au-dessus du meuble deplace les colonnes de 65 a 155 px sous le doigt de
  l'enfant. **Je ne rends pas M8 bloquante apres coup** ; je la signale datee.
- **Trois clauses de lieu retirees, aucune barre baissee** : M1, M4 et M8 perdent chacune les mots
  qui fixaient une position dans la page. C'est un allegement de mes conditions.
- **Une correction de mon propre ecrit** : ma parenthese de M1 ne donnait qu'un des quatre motifs du
  lieu. Un motif ecrit ne fait pas l'inventaire.

### Ce que cette reponse ne tranche pas

Ce qui doit payer si remonter l'indice ne suffit pas - ce sera une autre question, avec les chiffres
d'`ines-design`. La repartition du budget vertical d'un ecran a 360 x 640 : **cette reponse l'appelle**,
puisqu'un flux a somme nulle ne se pilote pas texte par texte, mais `ines-design` la proposera avec
ses mesures et je l'arbitrerai. Aucune route technique. **Et aucun verdict** : mon refus tient, aucun
Done pedagogique n'est prononce, aucune ligne de `squad/programme/MATRICE-couverture.md` ne bouge.
