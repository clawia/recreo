# ADR-0068 - Une lecture qui laisse un chiffre derriere elle ne rend pas de valeur

- **Statut** : Accepte
- **Date** : 2026-08-30
- **Sprint** : s23
- **Auteur** : `theo-archi`
- **Precede par** : `ADR-0041` (la valeur d'une proposition est derivee de ce qu'elle ecrit),
  `ADR-0051` (un garde-fou qui n'examine aucun de ses objets le declare), `ADR-0039` (un critere
  se derive des que la promesse est universelle)
- **Declenche par** : `marc-dev`, qui a route une question de perimetre au lieu de la trancher
  lui-meme sur `S23-RAN` (2026-08-30)

---

## Contexte

### Le fait

`aucuneValeurDeProductionNeCoincideAvecUneAutreDansLeMemeItem` echoue des que deux propositions
d'un meme item se lisent a la meme valeur. Zero tolerance, aucun seuil, aucune liste
d'exemption - contrairement a ses deux voisins de la meme classe, qui portent l'un une dette
plafonnee, l'autre `PAQUETS_HORS_DU_DOMAINE_NUMERIQUE`.

Le paquet `cp-ranger-nombres` de `S23-RAN` le rend rouge sur **24 de ses 24 items**. Une
proposition y est litteralement `"32, 33, 40"` : la liste des trois memes nombres dans un ordre,
forme imposee par `RAN-1` et bornee par `RAN-3`. La lecture de valeur s'arrete au premier nombre et
rend **32** ; sur les deux distracteurs `"40, 33, 32"` et `"40, 32, 33"` elle rend **40** deux fois.
Le garde-fou annonce alors que deux propositions valent la meme chose.

Elles ne valent pas la meme chose. Elles ne valent rien : **une liste ordonnee n'a pas de valeur
scalaire**, et deux listes qui commencent par le meme nombre ne sont pas la meme reponse. Le verdict
rendu n'est pas celui de la propriete nommee.

### Ce que le garde-fou disait deja de vrai

Sa Javadoc nomme deux causes possibles a une egalite de valeurs : *"soit les deux propositions
designent reellement le meme nombre (l'item n'a pas de reponse unique), soit la lecture est trop
courte pour les distinguer"*. Nous sommes dans le second cas, et **le garde-fou a raison de le
dire**. Il se trompe sur une seule chose, restee implicite : il suppose qu'une lecture trop courte
peut toujours etre allongee jusqu'a une valeur - vrai d'une somme, faux d'une proposition qui n'a
pas de valeur du tout. Le defaut n'est donc pas dans le seuil du garde-fou, il est **dans la
lecture**, et c'est la qu'il se corrige.

### Le recensement, derive de la forme et non d'un nom

`marc-dev` demandait quelles **autres** formes de proposition la lecture lit mal.
`cm1-problemes-durees` et `cm1-grands-nombres` etaient nommes comme suspects. Un releve par nom
aurait rendu ces deux-la et rien d'autre ; c'est exactement l'erreur commise en s22, ou mon `grep
"int TIRAGES"` rendait 9 lignes la ou la forme en portait 13.

**Methode** : sonde jetable non commitee, chaque paquet charge par le **chargeur reel**, chaque
proposition de chaque item tire reduite a son *squelette* - toute suite de chiffres remplacee par
`N`, toute suite de lettres par `M`, le reste conserve tel quel. Perimetre : les **26** paquets de
`adapters/adapter-content/src/main/resources/contenu/`, soit les 24 commites a `b1d8d9f` **plus les
deux brouillons non commites de `marc-dev`** (`cp-ranger-nombres`, `cm1-problemes-etapes`), sans
lesquels la forme en cause n'existe nulle part. Aucune exclusion. JDK : celui que designe la
toolchain `jdk 21` du poste, qui rapporte `25.0.2` - l'enveloppe le dit a chaque lancement, et je ne
recopie pas une version que je n'ai pas mesuree.

**Treize squelettes portent au moins un chiffre. Un seul est mal lu.**

| Squelette | Occurrences | Exemple | Ce que la lecture en fait |
|---|---|---|---|
| `N` | 826 | `902` | entier, reste vide - **juste** |
| `N M` | 161 | `4 L` | entier + unite, reste `L` - **juste** (clause 5 d'`ADR-0041`) |
| `N M N` | 30 | `15 h 55` | duree en minutes, reste vide - **juste** |
| `N M N M` | 13 | `1 heure 30 minutes` | duree en minutes, reste vide - **juste** |
| `N<separateur>N` | 52 | `40 705` | separateur de milliers retire - **juste** |
| six squelettes de somme | 22 | `40 000 + 700 + 5` | total des termes, reste vide - **juste** |
| `M<signe>N` | 2 | une formule de `cm1-perimetre` | pas de valeur (`null`) - **juste** |
| **`N, N, N`** | **72** | `75, 79, 96` | **premier nombre seul, reste `, 79, 96` - faux** |

Le meme releve dit la chose plus simplement encore : sur les **204** restes non vides du corpus,
**132 sont des unites** (`L`, `g`, `tonnes`, `km`, `cl`, `cm`, `kg`, `ml`, `mm`, `m`) et **72
portent un chiffre** - les 72 propositions de `cp-ranger-nombres`, et rien d'autre.

Les deux suspects nommes sont donc **innocents** : les durees et les grands nombres sont lus en
entier, c'est ce qu'`ADR-0041` a corrige le 2026-08-15. La forme fautive est neuve, elle arrive avec
`S23-RAN`, et elle se reconnait a un signe qui ne nomme aucune forme : **la lecture a laisse un
chiffre derriere elle**.

---

## Decision

**1. Une lecture qui laisse un chiffre dans son reste ne rend pas de valeur.**

`lireValeur` rend `null` - hors du domaine, comme un mot ou un symbole - des lors que le reste de sa
propre lecture porte encore un chiffre. La proposition n'est pas jugee fausse : elle est jugee
**sans valeur comparable**, ce qu'elle est.

Le critere est **le chiffre, jamais la forme qui le porte**. Une liste (`"32, 33, 40"`), une
fraction (`"3/4"`), un intervalle (`"10-20"`), une ecriture decimale a la virgule (`"2,5"`), un
horaire a deux points (`"8:30"`) tombent sous la meme ligne sans qu'aucune ne soit nommee
(`ADR-0039`). Une unite ecrite ne porte pas de chiffre : `"20 cm"` garde sa valeur, et la clause 5
d'`ADR-0041` continue de la garder.

Le reste n'est pas une seconde lecture : c'est le complement exact de la premiere, rendu par elle
(`ADR-0041`, amendement du 2026-08-15). Le critere se lit donc **sur ce que la lecture elle-meme a
laisse**, jamais sur un motif ecrit a cote - la divergence que le depot a deja payee en s13.

**2. Ce qui garde un paquet ainsi sorti du domaine est nomme, et rien n'est laisse au silence.**

Sortir du domaine n'est pas etre absous. Trois garde-fous existants prennent le relais, et aucun
n'est neuf :

- **la declaration d'`ADR-0051`** : un paquet dont plus aucun item n'entre dans le calcul de rang
  est **rouge** tant qu'il n'est pas inscrit dans `PAQUETS_HORS_DU_DOMAINE_NUMERIQUE` avec son motif
  ecrit. Cette table n'a pas de plafond, et c'est delibere ; ce qu'elle mesure est la part du
  contenu servi sur laquelle la propriete de rang n'est gardee par rien ;
- **`AucunePropositionNEstEgaleAUneAutreDansUnItemTest`** : deux propositions textuellement
  identiques restent rouges, sans passer par aucune lecture de valeur. La premiere des deux causes
  que nommait la Javadoc du garde-fou d'egalite - *"l'item n'a pas de reponse unique"* - n'est donc
  pas perdue par cette decision ;
- **la condition de l'arbitrage pedagogique** (`RAN-5` pour ce paquet-ci) : le raccourci propre a
  une famille de contenu est la main de `nadia-prof`, encodee par le test de story, pas par un
  garde-fou generique. Je le dis explicitement parce que la tentation inverse existait : voir
  "Alternatives", point 2.

---

## Regle executable

**Fichier** :
`adapters/adapter-content/src/test/java/com/recreo/adapter/content/LaValeurDUnePropositionEstDeriveeDeCeQuElleEcritTest.java`

**Methode** : `chaqueValeurReelleCesseDEtreLueDesQuUneAutrePropositionLuiEstCollee`

Ce n'est pas une regle ArchUnit : l'objet juge est le contenu servi, pas une dependance entre
paquetages. C'est un garde-fou de balayage, du meme type que les quatre autres clauses de ce
fichier, et il tourne dans la meme porte technique (`mvn -B clean verify`).

**La morsure est prouvee par mutation sur le corpus reel, pas sur une fixture** - la lecon de s05
est qu'une fixture ecrite par l'auteur d'une regle ressemble a ce qu'il imagine. La mutation colle
derriere chaque proposition du domaine **une autre proposition du meme item**, separee par une
virgule : le texte obtenu est une liste de nombres que la production ecrit vraiment, et aucun nombre
n'y est de ma main. Chacune de ces mutations doit cesser d'avoir une valeur.

**Mesure du 2026-08-30.** Perimetre : les 24 paquets commites a `b1d8d9f`, chaque paquet charge par
le chargeur reel, aucune exclusion, JDK rapporte `25.0.2`.

| Etat de la ligne de decision | Mutants | Mutants qui gardent une valeur |
|---|---|---|
| decision appliquee | 1 032 | **0** |
| decision neutralisee (`if (false && ...)`) | 1 032 | **1 032** |

La morsure porte sur **100 %** des propositions reelles du domaine. Le controle inverse - que la
lecture n'est pas devenue trop severe et n'a pas mis a la porte des propositions legitimes - n'est
pas ecrit ici : il est deja tenu par
`aucunPaquetNEstJugeSurUnePartieDeSesItemsSansQueLeDepotLeDeclare`, qui compare **a l'egalite
exacte** le nombre d'items ecartes par paquet (`cm1-perimetre` 2, `cm1-grands-nombres` 14) et qui
rougirait au premier item perdu. Ce test est vert apres la decision.

**Effet mesure sur le contenu, corpus des 26 paquets (les 24 commites plus les deux brouillons)** :

- `aucuneValeurDeProductionNeCoincideAvecUneAutreDansLeMemeItem` : **rouge sur 24 items avant, vert
  apres** ;
- `lesTroisPropositionsDUnItemDuDomaineLaissentLeMemeResteApresLecture` : **rouge avant, vert
  apres** - les 72 propositions de listes y laissaient trois restes differents ;
- `chaqueValeurReelleCesseDEtreLueDesQuUneAutrePropositionLuiEstCollee` : **rouge avant, vert
  apres** ;
- `aucunPaquetNEstEcarteEnSilenceDuCalculDeRang` : **rouge avant et apres**, et c'est correct - la
  declaration d'`ADR-0051` manque, et elle appartient au commit qui apporte le paquet. Le paquet
  etait deja entierement hors du calcul de rang **avant** cette decision, par l'egalite des valeurs ;
  ce que la decision change est le motif du rouge, pas le rouge.

**Aucun fichier de contenu n'est modifie par cette decision**, et aucune valeur du corpus commite ne
change : les 72 propositions concernees vivent toutes dans un brouillon non commite.

---

## Consequences

**Bonnes.**

1. Le garde-fou d'egalite ne rend plus qu'un seul verdict, et c'est le sien : deux propositions
   **entierement lues** qui coincident. Sa zero-tolerance devient defendable, la ou une liste
   d'exemptions l'aurait rendue negociable.
2. La forme fautive est attrapee par ce qu'elle **fait a la lecture**, pas par ce qu'elle est. Une
   fraction, un intervalle, un decimal a la virgule seront hors du domaine le jour ou ils
   arriveront, sans qu'aucun ADR ne soit necessaire pour les nommer un par un.
3. `marc-dev` est debloque sans qu'une exemption soit ecrite a son nom, et sans que je decide quoi
   que ce soit de pedagogique.

**Mauvaises, et elles sont reelles.**

4. **La propriete de rang ne dit plus rien sur `cp-ranger-nombres`, ni sur aucun paquet dont les
   propositions ecrivent plusieurs nombres.** C'est un vrai trou : la ligne de
   `PAQUETS_HORS_DU_DOMAINE_NUMERIQUE` qui sera ecrite le mesure, elle ne le comble pas. Ce qui
   garde ce paquet contre un raccourci est `RAN-5`, une condition pedagogique, et le test de story
   qui l'encode - pas un garde-fou generique.
5. **Le premier nombre d'une liste n'est pas rien**, et je le note pour que personne ne croie que
   cette decision l'a declare sans interet. Il n'est simplement pas *la valeur de la proposition* :
   c'est une **cle de raccourci**, un objet pedagogique, dont le seuil appartient a `nadia-prof`. La
   mesure que j'en ai faite est dans l'avis de faisabilite de s23, section 6, et elle est remontee a
   elle, pas encodee ici.
6. **Une proposition mal ecrite peut desormais sortir du domaine au lieu de rougir.** Un contenu qui
   ecrirait `"32 33 40"` (sans virgule) serait lu `323340` par le motif de milliers, ce qui est faux
   et **n'est attrape ni avant ni apres** cette decision. C'est la non-preuve 3 ci-dessous.

---

## Alternatives considerees

**1. Une liste de declaration sur le garde-fou d'egalite, sur le modele de son voisin.**
`PAQUETS_TOLERANT_LES_EGALITES`, avec `cp-ranger-nombres` dedans et son motif. Ecartee : une
exemption par paquet **eteint aussi les vraies prises** sur ce paquet - deux propositions qui
designeraient reellement le meme nombre y passeraient desormais sans bruit - et elle laisse intacte
la cause, une lecture qui rend une valeur fausse. Une exemption qui masque un symptome dont la cause
est corrigeable en trois lignes est le contraire d'un cliquet.

**2. Apprendre a la lecture a ordonner les listes, lexicographiquement.** `"32, 33, 40"` <
`"40, 32, 33"` < `"40, 33, 32"`, et le rang redevient calculable. Ecartee, et c'est l'alternative la
plus tentante : **elle rend le garde-fou de rang structurellement rouge sur tout paquet de rangement
correct**. Sur un paquet a consigne croissante, la bonne reponse est la liste triee, donc
**toujours** la plus petite dans l'ordre lexicographique - 100 % d'un rang, sur un contenu
irreprochable. Le garde-fou dirait alors "la bonne reponse est toujours celle qui est bien rangee",
ce qui est la definition de l'exercice et non un defaut. Un rouge structurel par construction est un
faux rouge, et un faux rouge finit toujours en exemption.

**3. Une surcharge de `valeurDe` par paquet, ou un parametre de lecture declare dans le YAML.**
Ecartee sur deux motifs. D'abord `ADR-0041` interdit explicitement la **seconde lecture** : *"deux
lectures qui derivent l'une de l'autre finissent par diverger, et c'est alors le garde-fou qui garde
la mauvaise"* - le depot a deja paye cette lecon une fois, en s13. Ensuite l'usage est **unique** :
un seul paquet, non encore commite, ecrit cette forme. Une abstraction se justifie sur un usage
constate ; celui-ci n'en est meme pas un.

**4. Ne rien changer et laisser `marc-dev` choisir d'autres nombres.** Impossible, et c'est ce qu'il
a correctement diagnostique : `RAN-5` exige que des propositions partagent leur premier nombre sur au
moins la moitie des items. Tout jeu de donnees conforme rend le garde-fou rouge. Ce n'est pas un
probleme de donnees.

---

## Non-preuves - ce que cette decision ne couvre pas, nommement

1. **Le cas annonce par la non-preuve 2 d'`ADR-0041` reste ouvert.** *"Coche la plus grande
   longueur : 1 m, 50 cm, 200 mm"* laisse les restes `m`, `cm`, `mm` - aucun chiffre - et garde donc
   trois valeurs `1 < 50 < 200`, c'est-a-dire l'inverse de l'ordre reel. Cette decision **ne le ferme
   pas**. `ADR-0041` annoncait qu'un `ADR` serait necessaire le jour ou ce cas arriverait ; ce n'est
   pas celui-ci, et le declencheur reste le meme : le premier item qui compare deux unites.
2. **Un paquet peut sortir du domaine sans que ce soit voulu, et seule la table d'`ADR-0051` le
   dira.** C'est la non-preuve 1 d'`ADR-0041`, inchangee. Cette decision **augmente** legerement ce
   risque, puisqu'elle cree une seconde facon de sortir du domaine ; elle l'augmente d'exactement ce
   que la table rend visible.
3. **Plusieurs nombres colles sans separateur restent mal lus.** `"32 33 40"` est lu `323340`, le
   motif de milliers avalant les espaces, et le reste est vide : cette decision ne voit rien. Aucun
   paquet ne l'ecrit, `RAN-3` impose la virgule, et je refuse d'ecrire un critere qui devinerait
   qu'un espace entre deux nombres n'est pas un separateur de milliers - la lecon de s05 porte
   exactement sur ce genre de devinette. **Declencheur** : le premier contenu qui ecrit une suite de
   nombres sans ponctuation.
4. **Un nombre negatif dans un reste declenche la regle.** `"-5"` a bien une valeur ; mais une
   proposition comme `"de 5 a -3"` sort du domaine. C'est voulu, et non teste separement : aucun
   paquet n'ecrit de negatif aujourd'hui.
5. **Cette decision ne dit rien de la justesse pedagogique d'une forme de proposition.** Qu'une
   liste `"a, b, c"` soit une bonne facon de faire ranger trois nombres a un enfant de CP est la
   decision de `nadia-prof` (`RAN-1`), et rien ici n'y touche.

---

## Ce qui me ferait changer d'avis

- **Un second paquet ecrit une forme a plusieurs nombres**, et la propriete de rang cesse d'etre
  gardee sur une part du contenu que la table d'`ADR-0051` rendra visible. A deux entrees, la
  question de savoir si une propriete de rang existe pour ces formes redevient ouverte - et elle se
  posera alors avec deux usages constates, pas un imagine.
- **Un item compare deux unites** : la non-preuve 1 devient un rouge, et il faudra apprendre les
  unites a la lecture. Celle-ci cesserait alors d'etre purement syntaxique, ce qui est un autre ADR
  et un autre cout.
