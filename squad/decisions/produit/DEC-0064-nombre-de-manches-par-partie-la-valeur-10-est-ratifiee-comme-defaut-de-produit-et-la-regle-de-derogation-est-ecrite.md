# DEC-0064 - Le nombre de manches d'une partie : `10` est ratifie comme defaut de produit, la regle
# de derogation est ecrite, et ce qui reste a `nadia-prof` est nomme

- **Type** : parametre de produit jamais decide, ratifie et borne
- **Date** : 2026-08-31
- **Auteur** : camille-po
- **Sprint** : s24 (cloture)
- **Competence programme visee** : **transverse - pas de contenu pedagogique.**
- **Statut** : Acceptee
- **Lien** : review de s24, section 3.3.b et obstacle 13, route par `sofia-sm` ; `DEC-0020` ;
  `DEC-0060` (`Q1`) et `DEC-0063`.

## 1. Le constat, et il est de `lea-dev`

`lea-dev` a mis `nombreDeManches: 10` sur ses deux paquets **parce que les autres y sont**, et
**elle l'a signale plutot que de le taire**. `sofia-sm` a classe le point produit et me l'a route :
le nombre de manches decide de la duree qu'un enfant passe sur un jeu et de la part d'un paquet qu'il
voit ; **il n'a jamais ete decide, seulement recopie.**

### Les chiffres, comptes par moi

**Methode** : lecture de `adapters/adapter-content/src/main/resources/catalogue.yaml` sur l'arbre de
cloture de s24 ; `grep -c '^  - id:'` pour les entrees, comptage des occurrences de
`nombreDeManches:` par valeur. **Aucun build, aucune mesure du reacteur.**

- **31 entrees de catalogue** : 30 sur la mecanique `bandit-manchot`, 1 sur `saisie`.
- **29 entrees a `nombreDeManches: 10`.**
- **2 entrees a `7`** : `bandit-manchot-complements-10-cp` et `saisie-complements-10-cp`, qui servent
  toutes deux le paquet des complements a 10, onze items.

**Un ecart d'un avec le compte de la review, et il n'est pas une erreur** : `sofia-sm` ecrit *"les 30
entrees du catalogue portent 10, sauf une qui porte 7"*. Sur les **30 entrees de bandit manchot**,
c'est exact. Sur le catalogue entier, il y a 31 entrees et deux a `7`, la seconde etant l'entree de
saisie livree le meme jour. **Son perimetre etait celui de la mecanique, le mien est celui du
catalogue** ; je le note parce qu'un compte cite sans son perimetre se recopie, pas parce que sa
phrase est fausse.

## 2. Ce que je ratifie

> **`nombreDeManches: 10` est le defaut de produit d'une partie.** Toute entree de catalogue le porte,
> sauf derogation ecrite.

Trois motifs, du plus fort au plus faible.

**a) La valeur a deja passe la porte pedagogique vingt-neuf fois, en fait sinon en droit.**
`nadia-prof` a joue et valide 29 jeux dont chaque partie comptait dix manches. Elle n'a jamais ete
interrogee sur le parametre - je le dis en 4 - mais elle a joue ce qu'il produit, a chaque audit,
pendant vingt-quatre sprints, sans jamais l'ecrire comme une gene. **Ce n'est pas un verdict sur le
parametre ; c'est un fait sur son exposition, et il pese.**

**b) Depuis `DEC-0060`, changer ce defaut a un cout explicite et il est enorme.** Sous `Q1`, un
changement du code ou du catalogue qui produit l'ecran rend les jeux modifies. **Abaisser ou relever
le defaut ferait entrer 29 jeux dans la porte de `nadia-prof` en une fois**, contre une borne de 4 a
5 paquets par sprint - pour un parametre dont personne, en vingt-quatre sprints, n'a signale qu'il
genait un enfant. **Ce serait le plus cher des gestes gratuits.**

**c) Le plancher est deja garde, et je n'ajoute rien de technique.**
`ChaqueJeuDuCatalogueOffrePlusDItemsQueDeManchesTest` (`adapter-content`) exige que chaque entree du
catalogue rende **strictement plus** d'items distincts que sa partie ne compte de manches, en
derivant du catalogue reel lu par le chargeur. L'egalite est refusee comme le defaut. **Une partie ne
peut donc ni s'arreter avant l'heure faute d'items, ni presenter deux fois de suite exactement le
meme jeu d'items.** Ce garde-fou existe, il est vert, et cette decision ne le touche pas.

## 3. La regle de derogation - c'est elle qui manquait vraiment

`10` n'etait pas le trou : le trou etait qu'aucune regle ne disait **quand on s'en ecarte**, alors
qu'une entree s'en ecartait deja.

> **Une entree de catalogue ne porte une autre valeur que `10` que par une decision produit ecrite,
> qui nomme son motif.** La valeur seule ne se justifie jamais par elle-meme, ni par alignement sur
> une entree voisine.

**Le precedent existe et il est conforme** : `DEC-0020` a abaisse la valeur a `7` pour les
complements a 10, motif ecrit - le paquet compte onze items, et une partie de dix manches en
epuiserait presque la totalite. **`saisie-complements-10-cp` porte `7` par le meme motif, sur le meme
contenu, et cette decision le ratifie retroactivement pour qu'il ne reste pas orphelin.**

**Ce que la regle rend impossible** : qu'une entree neuve porte une valeur choisie par alignement
sans que quiconque puisse dire pourquoi - exactement ce que `lea-dev` a signale.

## 4. Ce que je ne tranche pas, et je le nomme plutot que de le clore par ratification

**Dix manches est-il la bonne longueur pour un enfant de CP ?** C'est un jugement sur ce qu'un enfant
peut soutenir, et **il est a `nadia-prof`, pas a moi.** Cette decision ne le tranche pas et ne le
prejuge pas.

**Ce que je fais** : je ratifie un defaut de produit et je borne les derogations. **Ce que je ne fais
pas** : declarer que la question pedagogique est reglee parce que la question de gouvernance l'est.
Les deux sont distinctes, et confondre l'une avec l'autre serait prononcer un Done qui n'est pas le
mien.

**Si `nadia-prof` juge un jour que la longueur d'une partie gene un enfant**, sur un niveau ou sur un
type de contenu, sa parole ouvre une derogation par la regle de la section 3 - et le cout de la
section 2.b devra alors etre pose devant sa raison, pas contre elle. **Je ne le lui demande pas.**

## 5. Ce que cette decision ne fait pas

- Elle **ne demande a personne de changer une valeur.** Aucune entree de catalogue ne bouge, aucun
  fichier de `adapters/` n'est touche par elle.
- Elle **ne prononce aucun Done pedagogique** et n'en anticipe aucun.
- Elle **ne fait bouger aucune ligne de matrice.**
- Elle **ne cree aucune story** : il n'y a rien a developper.
- Elle **ne tranche rien de technique** : le garde-fou de la section 2.c existe deja et n'est pas de
  ma main.
