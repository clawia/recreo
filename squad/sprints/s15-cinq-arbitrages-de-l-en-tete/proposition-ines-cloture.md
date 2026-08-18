
# Proposition ines-design - cloture s15 : deux noms de marqueurs et le trou du selecteur compose

- **Auteur** : ines-design. **Date** : 2026-08-18.
- **Branche** : `s15-ines-cloture`, worktree dedie, cree depuis la tete de `round-two` a `6a4fa35` ;
  lignee verifiee par `outils/verifier-lignee.sh`.
- **Ce que ce commit change reellement** : un commentaire de balisage dans
  `fragments/retour.html` (section 1, la confirmation du nom que `theo-archi` attendait). Rien
  d'autre en production. La section 2 est une proposition au sens strict : elle ne modifie ni
  `recreo.css` ni aucun gabarit, parce que la question qu'elle repond appartient a `nadia-prof`
  (ADR-0046, decision point 1 : "quelle apparence un bouton prend sous le pointeur est un
  arbitrage de nadia-prof, pas une decision d'architecture").
- **Methode de mesure** : aucun rendu navigateur necessaire pour ces deux taches. La tache 1 est
  une lecture de gabarits et de feuille de style. La tache 2 porte sur un contraste de couleur,
  calcule par la formule de luminance relative du WCAG (meme methode que F16 et que la mesure de
  `theo-archi` sur l'etat actif, section 748 de `recreo.css` : "a verifier par nadia-prof sur le
  rendu reel, aucun navigateur ici") - je le dis pour ne pas faire passer un calcul pour une
  capture d'ecran.

## 1. Les deux noms de marqueurs d'ecran

### 1.1 `ecran-exclusif` - je garde le nom

Lu dans `fragments/confirmation.html`, `fragments/erreur.html` et `recreo.css` (regle
`body:has(.ecran-exclusif) .chip-niveau` et `body:has(.ecran-exclusif) .lien-accueil`).

Le nom passe le critere que `theo-archi` a pose en refusant `ecran-sans-puces` : il nomme un etat
de l'ecran ("cet ecran est exclusif"), pas l'effet qu'il produit sur un habillage ("cet ecran
masque les puces"). La definition ecrite dans le commentaire de `recreo.css` (ligne 788) - "un
ecran est exclusif quand tout chemin qu'il ouvre a l'enfant vit a l'interieur de lui" - est un
adjectif d'etat, au meme registre que "modal" dans un vocabulaire d'interface : ca decrit ce que
l'ecran *est*, pas ce qu'il fait a l'en-tete. Je n'ai pas de meilleur mot a proposer qui reste en
un ou deux termes ASCII et qui dise la meme chose plus court ou plus clair pour un developpeur qui
lit le gabarit sans le commentaire a cote.

**Motif de garder plutot que renommer** : le nom est deja pose sur deux gabarits, une regle de
masquage et dans les commentaires normatifs qui l'expliquent ; il satisfait la contrainte de forme
que `theo-archi` a posee ; le renommer couterait une edition dans au moins 4 fichiers pour un gain
que je ne sais pas nommer. Principe de simplicite : je ne remplace un nom correct que si j'ai un
nom meilleur a mettre a la place, pas par principe de revue.

### 1.2 `ecran-porte-sa-sortie` - je garde le nom

Lu dans `fragments/retour.html`, `fragments/confirmation.html` et `recreo.css` (regle
`body:has(.ecran-porte-sa-sortie) .lien-accueil`).

Meme critere, et ce nom le passe plus directement encore que le premier : c'est la traduction
litterale, presque mot pour mot, du fait qu'il encode - "cet ecran porte sa propre sortie" - et
`theo-archi` a choisi ce fait precisement parce qu'il est **verifiable contre le document** (le
gabarit contient ou ne contient pas un `[data-role=sortie]`), donc le nom colle exactement a ce
que la clause C qu'il propose devra lire. Je n'introduirais aucune clarte en le remplacant : toute
formulation plus courte ("ecran-sortant", "ecran-avec-sortie") perdrait la nuance de "sa propre"
qui est le coeur du fait (l'ecran ne rend pas n'importe quelle sortie, il rend la sienne, distincte
de celle de l'en-tete - c'est ce qui justifie BARRE-5).

**Motif de garder plutot que renommer** : forme conforme, sens direct, deja ancre dans trois
fichiers de production et un `ADR` en cours d'ecriture. Je ne vois pas de nom qui dise la meme
chose avec moins de mots sans perdre "sa propre".

### Ce que je change dans le depot pour cette tache

`fragments/retour.html` : le commentaire "Nom propose par theo-archi, en attente de confirmation
d'ines-design" est remplace par la confirmation et son motif court, avec un renvoi a ce document.
Aucune classe, aucun selecteur, aucun test ne change : les deux noms restent identiques a ce que
`theo-archi` et `marc-dev` ont deja livre.

## 2. La proposition qui debloque le trou du selecteur compose

### 2.0 Rappel du trou, tel qu'il m'est arrive

La derivation d'`ADR-0046` (qui liste les classes pressables et leurs proprietes declarees au
repos pour exiger un `:hover`/`:active`) reduit un selecteur compose a son premier element de
classe. `.proposition.deja-essayee` est, avec `.chip-niveau.est-actif`, l'une des deux seules
classes pressables composees du depot ; `.chip-niveau.est-actif` porte deja ses etats propres
(`recreo.css`, lignes 775-781, ferme dans ce sprint). `.proposition.deja-essayee` n'en porte
aucun : `recreo.css` ligne 1007 ne declare que `text-decoration-*`, sans variante `:hover` ni
`:active`. `theo-archi` refuse de corriger la derivation elle-meme sans arbitrage, precisement
parce que la corriger ferait immediatement rougir ce cas-la. C'est ce vide que je propose de
remplir.

**Ce qui existe deja et que mes deux variantes preservent sans le toucher** :

- `.proposition` (repos) : quatre fonds pastel cycliques par position (`recreo.css` lignes
  645-648), texte `--recreo-texte` (`#1c2733`), mesure a 9,55:1 minimum contre les quatre fonds
  (F16, contre-verifie ci-dessous par calcul independant) ;
- `.proposition.deja-essayee` (repos) : le meme fond et le meme texte, plus un trait
  (`text-decoration-line: line-through`) dans la meme teinte `--recreo-texte`, epaisseur
  `0,1875rem` - la marque de M9 (`FICHE-bandit-manchot-additions-cp.md`) ;
- M8 : aucune proposition ne change de boite, de police ou de position entre deux essais ;
- M9 clause 2 : la marque ne change ni la boite, ni la police, ni la position, et la cible
  tactile reste au-dessus de 44 px.

### 2.1 Variante 1 - forme neutre, alignee sur ADR-0046 point 4

`.proposition.deja-essayee:hover, .proposition.deja-essayee:active` redeclarent a l'identique les
valeurs de repos (le fond pastel selon la position, le texte et le trait en `--recreo-texte`) :
rien ne change sous le doigt, exactement la forme que `marc-dev` a posee sans arbitrage sur les
cinq boutons pleins et sur les trois boutons de contour d'`ADR-0046`.

### 2.2 Variante 2 - un retour propre, sans nouvelle teinte

`.proposition.deja-essayee:hover, .proposition.deja-essayee:active` gagnent
`outline: 0,125rem solid var(--recreo-texte); outline-offset: -0,125rem` (contour interieur, la
meme teinte que le trait deja mesure, aucune propriete de boite touchee puisque `outline` ne
participe jamais au flux) : un contour apparait sous le doigt pour confirmer "celle-la, tu l'as
deja essayee" au moment precis ou l'enfant appuie, en plus du trait deja visible au repos.

### 2.3 Contraste, chiffre et methode

Calcul par la formule de luminance relative du WCAG (`L = 0,2126 R + 0,7152 G + 0,0722 B` sur les
canaux linearises, ratio `(L1+0,05)/(L2+0,05)`), `--recreo-texte` (`#1c2733`) contre les quatre
fonds de `.proposition` :

| Position | Fond | Contraste calcule |
|---|---|---|
| `nth-child(4n+1)` | `#bdd2ef` | 9,82:1 |
| `nth-child(4n+2)` | `#a9dcd8` | 10,03:1 |
| `nth-child(4n+3)` | `#dcc8ed` | 9,75:1 |
| `nth-child(4n+4)` | `#eec1d8` | 9,55:1 |

Le minimum calcule (9,55:1 sur le quatrieme fond) confirme au centieme le chiffre deja retenu par
F16 dans le commentaire de `recreo.css` (ligne 1004) : meme methode, meme resultat, aucune
nouvelle teinte introduite. La variante 2 ne fait que reutiliser cette meme couleur pour un
contour ; elle n'a donc besoin d'aucun nouveau calcul de contraste, et je ne propose aucune teinte
que je n'ai pas les moyens de verifier ici (pas de navigateur dans ce worktree, comme le note
`ADR-0046`). Ce chiffre reste, comme celui de `theo-archi` sur l'etat actif, un calcul et non une
capture : `nadia-prof` le confirme sur le rendu reel si elle retient la variante 2.

### 2.4 Ce que je recommande

**La variante 2.** Motif, du seul cote qui m'appartient (forme, pas pedagogie) : mon mandat me
demande qu'un signal porte toujours un second canal en plus de la couleur, et qu'un enfant qui
presse ait un retour sous le doigt quand ce retour est possible sans cout. Le contour de la
variante 2 reutilise une teinte deja jugee et deja mesuree, ne deplace aucune boite (M8), ne
change ni police ni position (M9 clause 2), et donne a l'instant de l'appui la meme information
que le trait donne au repos - il ne l'invente pas, il la repete au bon moment. La variante 1 n'est
pas fausse ; elle est simplement le choix "le moins cher a ecrire sans arbitrage", pas
necessairement le mieux pour l'enfant, et c'est exactement pourquoi `marc-dev` ne pouvait pas la
depasser seul.

Ce que je ne propose pas et pourquoi : changer la couleur du fond ou du texte a cet etat. Une
nouvelle teinte est une nouvelle decision de canal de sens (mon propre mandat me l'interdit de
trancher seule) et je n'ai aucun moyen de la mesurer ici sans navigateur - je prefere le dire que
l'inventer.

### 2.5 Ce que ma proposition coute si `nadia-prof` la refuse

Si aucune des deux variantes n'est retenue - refus net ou report - le trou reste ouvert :
`theo-archi` ne corrige pas la derivation d'`ADR-0046` sans cet arbitrage, donc son mecanisme
garde l'angle mort mesure ce sprint (un selecteur compose credite a son premier element). Le
defaut que cet angle mort laisse passer est de la meme famille que celui que `nadia-prof` a
chiffre sur `.chip-niveau.est-actif` avant sa fermeture : un etat interactif non declare sur un
bouton compose, invisible a la CI, qui ne se voit qu'au navigateur. Je ne peux pas affirmer
aujourd'hui que ce meme defaut est deja visible pixel pour pixel sur `.proposition.deja-essayee`
dans l'etat actuel du depot - je n'ai pas d'outil pour simuler un survol ici, et je ne veux pas
recopier une mesure faite sur un autre bouton comme si elle etait la mienne. Ce qui est certain,
et c'est le prix reel du refus : rien ne garantit qu'il le reste demain. Une prochaine retouche
des teintes cycliques de `.proposition`, une montee de version de la feuille tierce qui changerait
les proprietes ou la specificite de `.btn:hover`, ou tout autre geste qui ne touche pas
`.proposition.deja-essayee` elle-meme, pourrait alors faire disparaitre son trait ou son fond sous
le doigt sans qu'aucun des 517 tests actuels ne le voie - exactement le mode de defaillance
qu'`ADR-0046` a ete ecrit pour fermer, et qu'il ne fermera pas ici tant que la derivation reste
non corrigee.

### 2.6 La question exacte que je pose a `nadia-prof`

**Une proposition marquee "deja essayee" doit-elle recevoir son propre retour visuel sous le doigt
et a l'appui (variante 2 : un contour dans la teinte deja mesuree du trait), ou l'absence de tout
changement - celle de `.proposition` ordinaire - continue-t-elle de lui suffire (variante 1) ?**
