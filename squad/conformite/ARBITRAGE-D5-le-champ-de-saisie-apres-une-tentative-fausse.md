# `D5` - ce que devient le champ de saisie apres une tentative fausse. Variante A, sous une condition
# qui supprime le risque nomme par `ines-design`

- **Auteur** : nadia-prof
- **Date** : 2026-08-31
- **Sprint** : s24 (en cours). **Du avant la fusion du lot 4** de `marc-dev`.
- **Saisi par** : `squad/sprints/s24-trois-paquets-et-ouverture-de-la-saisie/proposition-ines-ecran-de-saisie.md`,
  section 4 et ligne `D5` du tableau de dependances. `ines-design` recommande la variante A, nomme le
  risque qu'elle porte, et ecrit que *"c'est un jugement sur ce qu'un enfant fait, donc c'est celui de
  `nadia-prof`, pas le mien"*. Elle a raison sur les deux moities.
- **Ce document ne prononce aucun Done pedagogique** : rien n'est livre. Il fixe un **resultat
  attendu**, jamais un chemin de mise en oeuvre.

---

## 1. Ce que je tranche

**Variante A - le champ garde la tentative refusee et elle y est corrigeable - sous une condition
qui n'est dans aucune des deux variantes telles qu'elles sont decrites.**

> **Au moment ou l'enfant peut appuyer pour renvoyer, la chaine presente dans le champ doit lui etre
> montree comme deja refusee.**

Le resultat est la ; **la forme appartient a `ines-design` et a `theo-archi`.** Deux formes au moins
la servent - la meme chaine barree dans la liste des tentatives au-dessus du champ, ou le champ
lui-meme porte une marque de refus qui s'efface a la premiere frappe - et je n'en designe aucune.

---

## 2. Pourquoi A, et non B

**Le mot qui fonde `SAI-9a` est *corriger*** : *"le retour dit qu'il s'est trompe et lui laisse ce
qu'il a produit sous les yeux, **pour qu'il le corrige plutot que de retaper a l'aveugle**"*
(2026-08-24). Un champ vide ne se corrige pas, il se remplit. `ines-design` fait exactement ce
raisonnement et il est juste.

**`SEUIL-3` pousse dans le meme sens.** *"Si l'enfant ne peut pas corriger sa saisie avant de
valider, la saisie ne doit pas exister."* La variante B ne franchit pas ce seuil - dans un essai
donne, l'enfant peut toujours effacer et retaper avant d'envoyer - mais elle le sert au minimum : a
chaque nouvel essai, l'enfant repart de zero. La variante A le sert au mieux : effacer une lettre
suffit.

**Et le motif qui pese le plus lourd est de charge cognitive.** Un enfant de six ans qui a ecrit
`sept` et s'est trompe d'un caractere doit pouvoir voir ou est la faute et la reparer. S'il doit
tout retaper, il ne repare rien : il refait, et il refait souvent la meme chose, parce que retaper
c'est reproduire le geste qui a produit la faute. C'est aussi ce que `CPL-7` disait deja de la
frappe au CP : chaque caractere tape a un cout, et ce cout se paie sur ce que l'enfant avait en tete.

**Ce que je note sans m'en servir** : sur le paquet de s24, les deux variantes sont presque
indiscernables. Par `CPL-2`, toute bonne reponse est un entier de 0 a 10, donc au plus deux
caracteres a retaper. **La decision se prend pour la mecanique, pas pour ce paquet-la** : elle vaudra
pour le premier paquet alphabetique, ou retaper `élève` a la main coute tout autre chose.

---

## 3. Pourquoi la condition, et pourquoi elle n'est pas un ajout de confort

### 3.1 Le risque qu'`ines-design` a nomme est reel

Elle l'ecrit sans le minimiser : en variante A, *"la meme chaine est a un appui d'etre renvoyee
identique"*, et `ADR-0058` confirme qu'une chaine retapee a l'identique consomme un essai. Elle
ajoute qu'*"il n'existe aucune parade dans mon perimetre"*. C'est exact, et c'est pour cela que la
parade est dans le mien.

Avec trois essais avant la revelation, un essai brule sur un renvoi involontaire n'est pas un detail
d'ergonomie : **c'est un tiers de la manche perdu sur un geste qui n'apprend rien.**

### 3.2 Ce qui rend le risque grave n'est pas le champ rempli, c'est l'ambiguite de ce qu'il contient

Voila ce que ni A ni B ne traitent, et c'est le coeur de mon jugement.

En variante A telle qu'elle est decrite, la chaine du champ porte **deux sens a la fois** :

- *"voila ce que tu as ecrit, et il a ete refuse"* - une information sur le passe ;
- *"voila ce que tu vas envoyer si tu appuies"* - une proposition sur l'avenir.

Un adulte tient les deux ensemble. **Un enfant de six ans en tient un seul a la fois**, et rien a
l'ecran ne lui dit lequel. Un champ qui contient du texte ressemble a un champ pret a partir.

**La comparaison avec `M9` est celle qui tranche**, et elle joue contre A telle qu'elle est ecrite.
Sur la mecanique a boutons, represser un bouton deja essaye coute un essai lui aussi - `theo-archi`
l'a etabli et je l'ai relu dans `jouer`. Mais le bouton **est barre** : l'enfant qui le represse a
sous les yeux le fait qu'il l'a deja essaye. **La symetrie avec `M9` n'est pas "un renvoi identique
coute un essai" ; elle est "un renvoi identique coute un essai, et l'enfant voit que c'est un
renvoi".** La variante A garde la premiere moitie et perd la seconde. La condition la rend.

### 3.3 La description de la variante A affaiblit `SAI-12`, et je le releve

`ines-design` decrit A comme portant, dans la liste sous le verdict, *"les tentatives **anterieures**
seulement"*, la derniere vivant dans le champ. Le motif est sa ligne *"rien n'est affiche deux
fois"*, qui est un bon critere de rendu et que je ne conteste pas comme critere.

**Mais cela met la liste a vide au deuxieme essai** : la seule trace de la tentative refusee est le
contenu du champ, c'est-a-dire l'endroit meme ou elle a cesse d'etre lisible comme une tentative.

Or j'ai deja tranche ce point le 2026-08-30, en repondant a `theo-archi` sur `SAI-12` : *"Toutes
celles de la manche, cumulativement : une au deuxieme essai, deux au troisieme."* **Toutes celles de
la manche inclut la derniere.** La variante A telle qu'elle est ecrite s'en ecarte.

**Je ne le lis pas comme une erreur d'`ines-design`** : elle a arbitre entre deux criteres de rendu
sans avoir a connaitre le detail de ma reponse a `theo-archi`, qui vit dans un autre document. Je le
releve parce que le lot 4 partirait sinon avec un ecart silencieux entre une proposition de rendu et
une condition pedagogique deja rendue - et un ecart silencieux entre deux documents est exactement la
famille de defaut que ce depot paie le plus cher.

### 3.4 Ce que je reponds a *"rien n'est affiche deux fois"*

**Ce n'est pas deux fois la meme chose.** Une chaine barree dans la liste et la meme chaine vivante
dans le champ ne disent pas la meme chose : l'une dit *"refusee"*, l'autre dit *"modifiable"*. Ce
sont deux informations, pas une repetition.

**Et je ne l'impose pas comme forme.** Si `ines-design` trouve une forme qui porte les deux sens sans
ecrire la chaine deux fois - une marque sur le champ qui disparait a la premiere frappe, par
exemple - **elle sert ma condition aussi bien**, et son critere de rendu est preserve. Le choix est a
elle. Je fixe le resultat.

---

## 4. Ce que la condition ne fait pas

- Elle **n'ajoute aucune condition numerotee** a `SAI-1` a `SAI-12` ni a `SEUIL-1` a `SEUIL-5`. Elle
  est la lecture de `SAI-9a` et de `SAI-12` **prises ensemble** sur un ecran qui n'existait pas
  quand je les ai ecrites. Aucun poteau n'est deplace : le libelle de `SAI-12` du 2026-08-24 et ma
  reponse du 2026-08-30 la donnent deja, l'une dit *toutes celles de la manche*, l'autre dit *pour
  qu'il le corrige*.
- Elle **ne touche pas au comptage des essais.** Un renvoi identique consomme un essai, comme
  represser un bouton barre. Je ne demande pas de l'en dispenser : je demande que l'enfant sache ce
  qu'il fait avant de le faire. **Une mecanique qui interdirait le renvoi identique lui cacherait sa
  propre repetition**, ce qui est precisement ce que `SAI-12` existe pour rendre visible.
- Elle **n'engage pas `SEUIL-2`.** Une chaine fausse renvoyee a l'identique n'est pas une reponse
  juste refusee pour une question de forme.
- Elle **ne rouvre pas** la proposition de rendu d'`ines-design`, dont tout le reste - les regles de
  feuille, les dix largeurs mesurees, le prototype - n'est pas en cause ici et que je n'ai pas
  arbitre.
- Elle **ne dit rien du clavier virtuel de tablette**, sur lequel je reste aveugle : personne dans
  cette squad n'a l'appareil, et `SAI-2c` reste ma seule protection ecrite contre ce trou.

---

## 5. Sur quoi je reste la seule garde, sur ce point precis

Pour que l'equipe ne croie pas couvert ce qui ne l'est pas.

- **Qu'un enfant comprenne, avant d'appuyer, que la chaine du champ a deja ete refusee.** Aucun test
  ne dira cela. Un test verifiera qu'un attribut est present ou qu'une chaine figure dans le HTML ;
  il ne dira pas si un enfant de six ans le lit comme un refus. **Je le verrai en jouant, et je suis
  seule a pouvoir le voir.**
- **`SAI-9a`, `SAI-9b`, `SAI-12`, `SAI-2c` et `SAI-8`** - la formulation de tout ce que l'enfant lit
  apres avoir repondu. Deja nommees le 2026-08-30, inchangees.

---

## 6. Ce que je route

| Suite | Destinataire | Ce que je demande |
|---|---|---|
| **Variante A retenue**, avec la condition de la section 1 | `ines-design`, `marc-dev` | La forme est a `ines-design`. Deux formes au moins la servent, je n'en designe aucune |
| **La liste des tentatives porte toutes celles de la manche, la derniere comprise** (3.3) - ou une forme equivalente qui porte les deux sens | `ines-design`, `marc-dev` | Que le lot 4 ne parte pas avec un ecart entre la proposition de rendu et ma reponse du 2026-08-30 sur `SAI-12` |
| **`D5` est levee, le lot 4 n'attend plus sur moi** | `sofia-sm`, `theo-archi` | Rien d'autre |
| **La decision est prise pour la mecanique, pas pour le paquet de s24** (section 2) | `camille-po` | Qu'elle ne soit pas rediscutee au premier paquet alphabetique : elle a ete prise en pensant a lui |
