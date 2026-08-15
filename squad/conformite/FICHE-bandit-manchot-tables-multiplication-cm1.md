# Fiche de conformite - bandit-manchot-tables-multiplication-cm1

- **Mecanique** : bandit manchot (mecanique generique, partagee avec les autres jeux du
  catalogue).
- **Contenu(s)** : `adapters/adapter-content/src/main/resources/contenu/
  cm1-tables-multiplication.yaml` (a creer, `type: question`, au moins 55 items), story `S12-3`
  (`docs/backlog/backlog.md`). **Aucune matiere premiere directement reutilisable** :
  `contenu-repris/cm1-tables-multiplication.yml` existe mais dans le schema de difficulte
  (`difficulty: { minOperand, maxOperand, operations }`, `bounds: { ... }`) que `round-two` ne charge
  pas - c'est exactement la configuration taillee pour l'arithmetique nommee comme dette n°2 de
  `CLAUDE.md`. Le contenu est donc integralement a ecrire en `type: question`, un item par couple de
  facteurs.
- **Statut global** : **REFUSE** par `nadia-prof` le 2026-08-14, apres avoir joue l'increment
  (S12-3). Motif unique et actionnable : la bonne reponse n'est jamais le plus petit des trois
  nombres, et le plus grand dans 64 items sur 99. Detail dans le bloc de validation.
- **Arbitrage prealable de mecanique en vigueur pour tout le catalogue** (`squad/conformite/
  FICHE-bandit-manchot-additions-cp.md`, branche `s10-nadia-mecanique` et audits suivants) :
  conditions M1 a M13, dont deux ont ete bloquantes pour d'autres jeux du catalogue - M4 (le message
  d'erreur doit etre vu sans defiler a 360 x 640) et la clause 1 de M13 (apres une reponse, la
  tabulation ne detruit jamais la partie sans confirmation). Ces deux points devront etre mesures sur
  ce jeu une fois son contenu et son rendu reels disponibles ; rien n'est mesure ici a l'ouverture de
  la fiche, faute d'increment a mesurer.
- **Point de vigilance de capacite** : l'espace canonique de cette competence est fini (55 couples
  distincts de facteurs entre 1 et 10). `DEC-0020` a deja rencontre cette contrainte sur "Les
  complements a 10" (11 valeurs) et abaisse `nombreDeManches` plutot que d'elargir artificiellement
  les formes d'items ; le meme arbitrage pourra devoir etre instruit ici si `nombreDeManches`
  declare au catalogue s'approche de 55.
- **Derniere mise a jour** : 2026-08-14 (verdict de `nadia-prof`)

## Competences visees

| Matiere | Niveau | Domaine | Competence | Reference au referentiel |
|---|---|---|---|---|
| mathematiques | CM1 (cycle 3) | Nombres et calculs | Calcul mental - tables de multiplication (jusqu'a x10), rappel automatise | `squad/programme/mathematiques/competences-cm1.md` |

## Criteres pedagogiques attendus - camille-po

- [ ] Chaque item est une multiplication de deux facteurs entiers, chacun compris entre 1 et 10
  inclus, avec une bonne reponse numeriquement exacte.
- [ ] Chacun des 55 couples distincts de facteurs (1 <= a <= b <= 10) est represente par au moins un
  item du paquet.
- [ ] Chaque item porte exactement trois propositions : la bonne reponse et deux distracteurs
  numeriques plausibles (erreur de lecture d'un facteur, somme au lieu du produit), jamais un
  nombre arbitraire.
- [ ] Le paquet compte au moins 55 items.
- [ ] Chaque item porte un indice non vide, exact et comprehensible par un enfant de CM1.
- [ ] Le paquet valide le schema `type: question` (`ADR-0007`), pas `additions-bornees` (specifique
  a l'addition), sans code Java neuf, sans gabarit ni feuille de style neuve.

---

## Bloc de validation pedagogique - nadia-prof UNIQUEMENT

> Ce bloc n'est jamais pre-rempli ni anticipe par un autre agent.

### Done pedagogique du 2026-08-14 - REFUSE (S12-3, paquet `cm1-tables-multiplication.yaml`)

- **Verdict** : **REFUSE**. Aucun Done pedagogique. La ligne "Calcul mental - tables de
  multiplication (jusqu'a x10), rappel automatise" **ne passe pas** a `couvert-valide` et reste ou
  elle est. Ce refus renvoie l'increment a `camille-po`, qui en fait du backlog ; **il ne bloque pas
  le sprint suivant**, et le jeu peut rester au catalogue en l'etat si elle le decide - il n'est ni
  faux ni dangereux, il est trop facile a contourner.
- **Auteur** : nadia-prof. **Increment** : branche `round-two`, tete `b396234`, 455 tests, 28 regles.
- **Conditions de reference** : mes 27 conditions du
  `squad/conformite/ARBITRAGE-PREALABLE-s12-mathematiques-cm1.md`, section 4 (T-1 a T-7).
- **Mode** : **j'ai joue**, ce n'est pas un audit degrade. Jar reconstruit depuis mon worktree,
  parties completes jouees au navigateur a 360 x 640. Releves :
  `releves-s12/releve-rang-bonne-reponse.json`, `releves-s12/releve-jeu-cm1.json`,
  `releves-s12/releve-m4-cm1-360x640.json`.

#### Le motif, un seul, et il est actionnable

**Sur 99 items sur 99, la bonne reponse n'est jamais le plus petit des trois nombres proposes ; sur
64 items sur 99, elle est le plus grand.**

Un enfant qui ne sait aucune table et presse systematiquement le plus grand nombre affiche **gagne
64,4 % des manches** - mesure en jouant, 76 manches gagnees sur 118 - contre 33,3 % par hasard. Le
calcul exhaustif sur le paquet entier donne 64,6 %, la meme valeur. Et la regle "ne jamais choisir
le plus petit" elimine une proposition sur **la totalite** des 99 items, sans en lire un seul.

**Pourquoi c'est disqualifiant sur cette ligne precisement.** Le verbe de la competence est
"rappel". Un paquet de tables ou **deux manches sur trois se gagnent sans rappeler quoi que ce
soit** ne travaille pas le rappel : il apprend a comparer trois nombres. C'est le meme defaut que
j'ai refuse dans ce sprint sous le nom de devinette, quand j'ai ecarte le proxy d'encadrement de
fractions parce que l'enfant pouvait repondre sans lire.

**La cause, en une phrase.** Pour un enonce "a x b", les deux distracteurs sont toujours
`(a-1) x b` et `a x (b-1)`, qui sont l'un et l'autre necessairement inferieurs a `a x b`. Le procede
est regulier sur tout le paquet, donc le biais l'est aussi.

**La correction, et elle est peu couteuse.** Le produit voisin a deux cotes. Prendre, sur une part
des items, le voisin **au-dessus** - `a x (b+1)` ou `(a+1) x b` - qui est exactement aussi
plausible. C'est la forme de mon propre exemple en T-5 : *"pour 7 x 8 = 56, les distracteurs 48 et
63"* - **63, c'est 7 x 9, et il est superieur a 56**. Rien d'autre n'est a changer dans ce paquet.

**Ce que je ne fais pas, et je le dis pour qu'on ne me le reproche pas comme une incoherence.** Je
ne deplace pas mes poteaux : `lea-dev` a tenu **les sept conditions T-1 a T-7 a la lettre**, et le
refus ne porte sur aucune d'elles. Il porte sur la **plausibilite des distracteurs** et sur **le
niveau tel qu'il se joue**, deux proprietes que j'avais nommement gardees pour moi en section 6 de
mon arbitrage, en annoncant qu'un lot tenant mes vingt-sept conditions pouvait encore etre refuse
pour ce que seul le jeu montre. C'est ce cas-la.

#### Ma reponse au point que `lea-dev` a signale plutot que de le corriger seule

Sa question : l'uniformite de famille est-elle un progres ou un appauvrissement ?

**La famille est un progres, sa direction unique est l'appauvrissement.** Ce sont deux choses
distinctes et il faut les separer :

- **Le choix de la famille "produit voisin" est juste**, et je le credite. C'est celle que j'ai
  nommee sure en T-5. Cent items dont le distracteur se fabrique de la meme facon ne pose aucun
  probleme en soi : un enfant ne peut pas exploiter "c'est un produit voisin" sans connaitre la
  table, ce qui est precisement le contraire d'un raccourci.
- **Eviter le piege T-5 par construction plutot que par detection est legitime**, et je le credite
  aussi. Rendre le cas `2 x 2` impossible en n'employant jamais la somme des facteurs est une
  bonne facon de tenir une condition ; je n'exige pas qu'on detecte ce qu'on peut rendre
  inatteignable.
- **Ce qui appauvrit, c'est d'avoir pris toujours le meme des deux voisins.** `(a-1) x b` et
  `a x (b-1)` sont tous deux en dessous ; `a x (b+1)` et `(a+1) x b` sont tout aussi voisins et
  tout aussi plausibles. En n'employant que la moitie basse de la famille, le procede a fabrique
  une regle de reponse qui ignore le contenu. Ce n'est pas l'uniformite qui coute, c'est
  l'unilateralite.

#### Ce qui est juste dans ce paquet et n'est pas a refaire

Je l'ecris pour que la reprise soit ciblee et courte, et parce que ce paquet est, hors ce defaut, le
mieux construit des trois de ce sprint.

1. **Exactitude : 99 produits sur 99 justes.** Je les ai tous recalcules. Aucune erreur de fait.
2. **T-1 tenue** : les 55 couples (a, b) avec 1 <= a <= b <= 10 sont tous representes.
3. **T-2 tenue** : 19 items a facteur 1 ou 10 sur 99, soit **19,19 %**, sous mon plafond d'une
   manche sur cinq.
4. **T-3 tenue** : les 28 couples a facteurs distincts hors 1 et 10 sont presents **dans les deux
   ordres**.
5. **T-4 tenue** : le signe multiplie (U+00D7) partout, jamais la lettre x.
6. **T-6 tenue, et les indices sont bons** - c'est le point que je verifiais avec le plus de
   mefiance, parce qu'un "Recite ta table." copie cent fois aurait rempli le champ sans rien
   apprendre. Les dix indices sont distincts, chacun donne une **strategie executable**, et je les
   ai verifies un par un sur leurs items : "Table de 9 : prends la table de 10, puis enleve le
   nombre une fois." donne bien 20 - 2 = 18 pour 2 x 9 ; "Table de 8 : prends le double du double du
   double du nombre." donne bien 7, 14, 28, 56 pour 7 x 8 ; "Table de 6 : prends la table de 5, puis
   ajoute le nombre une fois." donne bien 30 + 6 = 36 pour 6 x 6. Aucun ne nomme le produit. C'est
   du bon travail.
7. **T-7 tenue** : aucun facteur hors de 1 a 10 dans un enonce, aucune division, aucun facteur
   manquant, aucune mise en situation, propositions en nombres nus sans unite.
8. **M4 tenue, seule des trois** : a 360 x 640, sur 70 etats "premier echec" joues, le message
   **et** l'indice sont visibles sans defiler **70 fois sur 70**. Les enonces courts de ce paquet
   sont ce qui le permet. La marge sur l'indice est cependant mince - **3 px dans le pire cas** -
   donc tout allongement futur des enonces la ferait sauter.

#### Deux points mineurs, a traiter dans la meme reprise

- **Un meme enonce revient dans une meme partie environ une fois sur sept** : mesure sur 30 parties
  de dix manches, **4 parties sur 30**. Le tirage est bien sans remise, mais il l'est **par
  identifiant d'item**, et les 8 carres (2 x 2 a 9 x 9) portent chacun **trois items au meme
  enonce**, avec des distracteurs differents. Un enfant qui a vu la reponse revelee sur "7 x 7 = ?"
  peut la retrouver deux manches plus loin sans rien rappeler.
- **Deux distracteurs sortent de l'espace des tables** : `11` sur l'item "1 x 10 = ?" et `110` sur
  "10 x 10 = ?". Ils restent plausibles (l'enfant qui additionne au lieu de multiplier ecrit bien 11)
  et T-7 ne portait que sur les facteurs des enonces, donc ce n'est pas un manquement ; je le
  signale parce qu'un produit de deux nombres de 1 a 10 ne vaut jamais 11 ni 110.

#### Ce que la matrice doit dire, et que `camille-po` ne doit pas perdre

Je le repose ici parce que je l'avais pose dans mon arbitrage prealable et que la fiche est le
seul endroit ou il survivra : **cette ligne ne pourra jamais passer en `couvert-valide` sans la
mention "sans mesure d'automatisation".** La ligne du referentiel dit "rappel **automatise**", et
l'automatisation est une affaire de **vitesse** : un enfant automatise quand il repond sans calculer.
**Ce produit ne mesure aucun temps de reponse.** Un paquet, meme corrige de son defaut de rang,
verifiera que l'enfant **sait** ses tables, pas qu'il les a automatisees. C'est une **couverture
partielle**, et la matrice doit l'ecrire avec ces mots le jour ou la ligne s'ouvrira. Ce n'est pas
un reproche a ce lot : savoir ses tables est la condition d'y arriver, et s'entrainer est le bon
usage de ce jeu.

#### Les proprietes dont je reste la seule garde sur ce jeu

Le garde-fou de doublon de proposition, la comparaison de l'ecran lu par l'enfant a un inventaire,
la distribution du facile a 19,19 % et la cardinalite des indices distincts sont desormais tenus par
un automate, verifie par mutation sur materiau reel - je les retire de ma liste et je ne les
reverifierai plus a la main. **Restent sans automate, et c'est moi, en jouant** : l'exactitude
arithmetique de chaque produit, la plausibilite de chaque distracteur, **le rang de la bonne reponse
parmi les trois propositions** (le motif de ce refus - c'est la propriete la plus mecanisable de ce
qui reste, et je la decris sans prescrire le moyen, qui appartient a `theo-archi` et a `yanis-qa`),
l'utilite reelle de chaque indice, et le niveau tel qu'il se joue.
