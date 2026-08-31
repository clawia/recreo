# Fiche de conformite - `bandit-manchot-monnaie-cp`

- **Jeu** : `bandit-manchot-monnaie-cp`, paquet `cp-monnaie`, 12 items illustres.
- **Competence visee** : Mathematiques CP, "Nombres et calculs" - *"Reconnaitre et utiliser la
  monnaie (pieces/billets simples) dans des problemes concrets"*.
- **Conditions opposables** : `MON-1` a `MON-5` (2026-08-31), `CON-1` a `CON-5` (2026-08-31).
- **Auteure du verdict** : `nadia-prof`. **Un verdict n'est jamais efface.**

---

## Verdict du 2026-08-31 (s25, seconde paire visuelle)

# Verdict : **VALIDE SOUS RESERVE** - Done pedagogique **accorde**, deux reserves non bloquantes

**La ligne de matrice peut passer `couvert-valide`, ligne entiere.** Les deux verbes de la ligne -
*reconnaitre* et *utiliser* - sont servis par le meme paquet, et c'est ce que j'avais annonce le
2026-08-31 en corrigeant mon propre compte de deux paquets a un.

### 1. Ce que j'ai fait

**J'ai joue** sur le meme jar que le paquet de figures, avec la meme reserve sur l'outil de
construction (`FICHE-bandit-manchot-figures-planes-cp.md`, section 2, qui vaut ici sans changement).
Partie ouverte, reponse fausse, ecran d'apres.

**Et j'ai fait la passe que je m'etais reservee : les douze dessins, valeur par valeur, recomptes
contre la reponse declaree.** *Methode : chaque objet dessine porte sa valeur en chiffres traces
comme geometrie ; j'ai releve la suite des chiffres de chaque dessin et calcule la somme.*

### 2. L'exactitude des douze dessins - **zero ecart**

| Item | Ce que le dessin porte | Somme | Reponse declaree | Verdict |
|---|---|---|---|---|
| 1 | 2, 1 | 3 | 3 euros | juste |
| 2 | 2, 2, 1 | 5 | 5 euros | juste |
| 3 | 1, 1, 1 | 3 | 3 euros | juste |
| 4 | 5, 2 | 7 | 7 euros | juste |
| 5 | 5, 5 | 10 | 10 euros | juste |
| 6 | 10, 2, 2 | 14 | 14 euros | juste |
| 7 | 10, 5, 2, 1, 1 | 19 | 19 euros | juste |
| 8 | billet 5 / bourse 2, 2, 1 | 5 = 5 | 2 + 2 + 1 | juste |
| 9 | billet 5 / bourse 2, 1, 1, 1 | 5 = 5 | 2 + 1 + 1 + 1 | juste |
| 10 | piece 2 / bourse 1, 1, 1 | 2 = 2 | 1 + 1 | juste |
| 11 | billet 10 / bourse 5, 2, 2, 1 | 10 = 10 | 5 + 2 + 2 + 1 | juste |
| 12 | billet 20 / bourse 10, 5, 2, 2, 1 | 20 = 20 | 10 + 5 + 2 + 2 + 1 | juste |

**Douze dessins, douze sommes justes.** C'est la verification que je ne peux pas deleguer et je la
donne avec sa methode pour qu'un tiers la refasse.

### 3. `MON-1` a `MON-5`

| Condition | Verdict | Ce que j'ai verifie |
|---|---|---|
| **`MON-1`** - les pieces et billets sont **montres**, pas nommes | **TENUE, 12 sur 12** | Aucun enonce ne nomme un objet ni sa valeur. Deux formulations en tout : *"Combien y a-t-il en tout ?"* et *"Comment faire la meme somme que le billet ?"* (ou *"que la piece"*) |
| **`MON-2`** - seules les valeurs qui existent | **TENUE** | Valeurs servies : 1, 2, 5, 10, 20. **Aucune valeur inventee**, aucun centime |
| **`MON-3`** - au moins un tiers des items demandent de composer avec les valeurs montrees | **TENUE : 5 items sur 12**, plancher a 4 | Les items 8 a 12. Je traite en section 5 la question que `sofia-sm` me route sur la forme forte |
| **`MON-4`** - sommes dans la limite que le CP sait calculer, au plus 20 euros, sans centimes | **TENUE a la lettre**, et c'est la reserve 2 | Somme maximale : 20 euros, exactement a la borne |
| **`MON-5`** - aucune situation ne suppose de l'argent de poche, une course faite seul, un achat | **TENUE** | Aucun item ne met en scene un achat, un magasin ni un enfant qui paie. On compte de l'argent pose, on ne l'echange pas |

**`CON-1` a `CON-5`** : **TENUES.** En effacant l'image, aucun des douze items ne se repond -
*"Combien y a-t-il en tout ?"* sans image ne designe rien. Aucun enonce ne dit une quantite ni une
valeur. Les textes alternatifs disent **ce qu'il y a a regarder** sans dire combien : *"Des pieces
posees cote a cote."*, *"Un billet a gauche d'un trait, de la monnaie a droite."*

### 4. Ce que j'ai vu en jouant

- **Le dessin survit a l'echec**, comme sur les figures. Verifie a l'ecran.
- **La consigne** *"Regarde bien l'argent, puis reponds."* : cinq mots, tous lisibles au CP, et elle
  dit le geste dans l'ordre.
- **Le trait qui separe le billet de la bourse** sur les items de composition est un trait, sans
  texte, et il est lisible. L'indice le nomme correctement : *"Lis d'abord ce qui est a gauche du
  trait, puis cherche a droite."*
- **Les distracteurs sont plausibles** : sur les items de somme, ils sont a un ou deux euros de la
  bonne reponse, jamais absurdes. Sur les items de composition, ils different d'une piece.

### 5. La forme forte de `MON-3` - je ne la demande pas, et le motif n'est pas `ADR-0041`

`sofia-sm` me route le fond : `ADR-0041` interdit qu'un distracteur ait la meme valeur que la bonne
reponse, ce qui interdit la forme la plus forte de `MON-3` - un distracteur **juste
arithmetiquement mais impossible avec les pieces montrees**. Elle n'a pas affaibli le garde-fou et
elle a eu raison.

**Je ne demande pas cette forme forte, et pas parce qu'un `ADR` me l'interdit : parce que je la
refuserais moi-meme.**

Sur l'item 8, la bourse porte 2, 2, 1. Le distracteur fort serait *"1 + 1 + 1 + 1 + 1"* : il vaut 5
comme la bonne reponse, mais la bourse ne contient qu'une piece de 1. **Or l'enonce dit *"Comment
faire la meme somme que le billet ?"* - il ne dit pas *"avec cette monnaie-la"*.** Sous cet enonce,
*"1 + 1 + 1 + 1 + 1"* **est une reponse juste**, et l'item en aurait deux dont une seule serait
comptee bonne.

**C'est exactement ce que j'ai refuse sur `LIA`** : *un item a deux reponses justes dont une seule
est comptee bonne n'apprend pas une regle, il apprend que l'application a raison.* Je ne vais pas
l'exiger ici.

**Et je nomme la limite de fond, parce qu'elle n'est pas un defaut de ce paquet** : dans une
mecanique a trois propositions dont une seule est juste, **la constructibilite ne peut pas etre le
discriminant**, puisque departager par la valeur et departager par les pieces disponibles sont deux
exigences contradictoires. **C'est une limite de mecanique, pas un manquement de contenu.** Elle
tomberait avec une mecanique ou l'enfant **choisit les pieces** lui-meme - ce serait un palier
au-dela du palier 1, et je ne le reclame pas ce sprint.

**Ce que le paquet fait quand meme, et qui n'est pas rien** : sur l'item 10, la bourse porte trois
pieces de 1 et la reponse est *"1 + 1"*. **L'enfant doit ne pas tout prendre.** C'est le seul item
qui casse l'habitude *"utilise tout ce que tu vois"*, et c'est le meilleur du lot.

### 6. `MON-1`, lettre ou motif - la question de `sofia-sm`, tranchee

**Elle a applique la lettre, elle a bien fait de me le demander, et c'est le motif qui gouverne.**

**Elle a raison sur le fait qui tranche, et c'est elle qui l'a vu : mon propre exemple porte un
chiffre.** `MON-1` dit : *"Un enonce qui ecrit 'une piece de 2 euros' a deja fait la reconnaissance
a la place de l'enfant."* **Ce que j'interdis n'est pas le chiffre : c'est de nommer la valeur d'un
objet que l'image montre.**

> **Lecture arretee le 2026-08-31, et elle vaut pour la suite** : un nombre qui **nomme la valeur
> d'un objet montre** est interdit ; un nombre qui **nomme une cible que l'enfant doit atteindre**
> est permis. *"Il faut payer 7 euros"* est permis - l'enfant doit encore lire chaque piece pour
> composer 7, et rien ne lui a ete donne. *"Tu as une piece de 2 euros"* reste interdit.

**Ce que cela change a ce paquet : rien.** Les douze enonces evitent le chiffre et fonctionnent. **Ma
reponse ne demande aucune reecriture** ; elle rouvre une formulation naturelle pour les paquets
suivants, que la lettre lui avait fermee a tort.

### 7. Les deux reserves, non bloquantes

**Reserve 1 - le vocabulaire de l'indice.** *"Lis le nombre grave sur chaque objet, puis ajoute."*
Deux mots me font m'arreter. **"grave"** n'est pas un mot du CP : l'enfant le dechiffrera, il ne le
comprendra pas forcement. **"objet"** est abstrait pour designer une piece ou un billet. L'indice
reste utilisable - *"Lis le nombre ... sur chaque ..., puis ajoute"* porte l'instruction meme si les
deux mots manquent - **et c'est pourquoi cela ne bloque pas.** Une formulation qui nomme ce que
l'enfant voit ferait mieux. **Je ne l'ecris pas : je pose le resultat.**

**Reserve 2 - le nombre de termes, que `MON-4` ne borne pas et aurait du.** L'item 12 demande
*"10 + 5 + 2 + 2 + 1"*, **cinq termes, et le total franchit la dizaine**. `MON-4` plafonne la
**somme** a 20 euros et ce plafond est respecte a l'unite pres. **Mais son motif etait "la limite que
le CP sait calculer", et une addition a cinq termes qui franchit la dizaine est au-dela de ce qu'un
CP tient de tete**, meme si son total reste dans la borne.

**Je ne refuse pas sur ce point, et je dis pourquoi** : `MON-4` est de moi, elle est ecrite,
elle est respectee. **Refuser sur son motif apres avoir ecrit sa lettre serait deplacer mon
poteau**, et je ne le fais pas - c'est ce que j'ai deja refuse de faire sur `NAR` a s24.

> **`MON-6`, neuve, datee du 2026-08-31, non retroactive** - Sur un paquet de composition au CP, la
> reponse attendue compte **au plus quatre termes**, et **la somme des trois premiers ne franchit pas
> la dizaine**. *Unite de compte : l'item.* Motif : le plafond de `MON-4` borne le resultat et non
> l'effort, et c'est l'effort qui bloque un enfant de six ans.
>
> **Elle ne s'applique pas aux douze items de ce paquet**, deja valides. Elle vaut pour les paquets de
> composition suivants.

### 8. Sur quelles proprietes je reste la seule garde sur ce jeu

- **Les douze sommes.** Recomptees a la main contre les dessins ; rien ne le fera a ma place.
- **`CON-1` a `CON-5`**, et particulierement `CON-5` : sept textes alternatifs distincts ici, contre
  douze identiques sur les figures, **parce que la reponse est une somme et non un nom**. Aucun ne
  dit combien.
- **La plausibilite des distracteurs**, item par item.
- **`MON-6`** desormais, sur les paquets suivants.

### 9. Le fait de produit que ce paquet confirme

**Cette ligne non plus n'est pas jouable sans la vue**, et pour la meme raison que les figures : la
reponse **est** ce que l'image montre. `sofia-sm` a mesure sept textes alternatifs distincts ici
contre douze identiques la-bas, et sa lecture est la bonne - **c'est la nature de la ligne qui
decide, pas le palier**. Je le confirme et je ne le tranche pas : cela appartient a `camille-po` puis
a l'utilisatrice.

### 10. Ce que ce verdict ne fait pas

- Il **n'est pas fonde sur la CI**.
- Il **n'exige aucune reecriture** du paquet livre : les deux reserves sont pour la suite.
- Il **ne demande l'affaiblissement d'aucun garde-fou**, et surtout pas `ADR-0041`.
- Il **ne modifie aucun fichier** de code ni de contenu.
