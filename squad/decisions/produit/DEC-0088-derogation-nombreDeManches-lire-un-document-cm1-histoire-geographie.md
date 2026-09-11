# DEC-0088 - Derogation `nombreDeManches` pour l'entree "Lire un document" issue de la scission de
# `bandit-manchot-histoire-geographie-cm1` (`S35-CONS-4`)

- **Type** : perimetre produit, parametre de catalogue (`ADR-0012`), suite a un obstacle technique
  route par `theo-archi`
- **Date** : 2026-09-11
- **Auteur** : camille-po
- **Sprint** : s35
- **Competence programme visee** : **transverse - pas de contenu pedagogique.** Aucune ligne de
  `squad/programme/MATRICE-couverture.md` ne bouge par cette decision ; les 110 items de
  `cm1-histoire-geographie` continuent de servir les memes lignes qu'avant la scission.
- **Statut** : Acceptee

## 1. Contexte

`theo-archi` a chiffre le blocage de `S35-CONS-4` dans son avis de faisabilite
(`squad/sprints/s35-fermer-ce-qui-n-a-pas-recu-le-go-pedagogique/avis-faisabilite-theo.md`,
section 6). Trois textes du depot, tous valides, ne peuvent pas etre tenus ensemble sur la
quatrieme entree de catalogue que la scission cree :

- **la table des quatre entrees** de `S34-CONS-4`, qui affecte a l'entree "Lire un document"
  exactement `HG-CM1-14` seule, soit **10 items** (comptage verifie par `theo-archi` sur l'arbre
  `60e1c836`, motif `ligneDeReferentiel`) ;
- **`DEC-0064`**, qui fait de `nombreDeManches: 10` le defaut de toute entree de catalogue, sauf
  derogation ecrite ;
- **`ADR-0023`** (`ChaqueJeuDuCatalogueOffrePlusDItemsQueDeManchesTest`), qui exige que le nombre
  d'items distincts soit **strictement superieur** au nombre de manches, l'egalite etant refusee
  comme le defaut par principe (*"un paquet aussi long que la partie ne produit aucune variete
  d'une partie a l'autre"*).

Avec 10 items et `nombreDeManches: 10` (le defaut), le garde-fou d'`ADR-0023` rougit
garanti : ce n'est pas une difficulte d'implementation qu'un developpeur resoudrait en chemin,
c'est une decision qui manque, et elle n'est pas de `theo-archi`.

`theo-archi` nomme trois sorties possibles (section 6.2 de son avis) :

1. **Une derogation ecrite** sur `nombreDeManches` pour cette seule entree - zero ligne de code,
   zero risque technique, decision de catalogue qui m'appartient (`ADR-0012`).
2. **Un onzieme item sur `HG-CM1-14`** - contenu neuf, qui contredit deux fois `S35-CONS-4`
   (*"elle ne cree aucun item neuf"*) et l'objectif du sprint (aucune ligne de programme neuve,
   aucun contenu neuf, uniquement la cloture de refus et de reserves deja prononces).
3. **Fondre "Lire un document" dans une autre entree** - rouvrirait la decision de grain deja
   rendue conjointement par `camille-po` et `nadia-prof` (`ADR-0081`), que `S35-CONS-4` exclut
   explicitement de rouvrir.

## 2. Decision

**Je baisse `nombreDeManches` a 7 pour l'entree de catalogue "Lire un document", quand elle sera
creee. Je ne cree aucun item neuf et je ne rouvre pas la decision de grain d'`ADR-0081`.**

### Le calcul retenu, avec sa marge

`ADR-0023` exige que le nombre d'items distincts soit strictement superieur au nombre de manches.
Avec `nombreDeManches: 7`, le plancher exige au moins **8** items distincts. L'entree porte les
**10** items existants de `HG-CM1-14`, non modifies par cette decision ni par `S35-CONS-4` : marge
de **2** au-dessus du plancher.

**Je n'ai pas retenu `nombreDeManches: 9`** (le plafond arithmetique le plus proche du defaut) :
avec 9 manches, le plancher exige exactement 10 items, la taille exacte de l'entree. `10 > 9` est
techniquement vert, mais **sans aucune marge** - c'est exactement l'egalite fragile qu'`ADR-0023`
refuse par principe pour la partie elle-meme, et le precedent de cette maison (`DEC-0020`) a deja
refuse de la reintroduire au niveau du paquet par commodite : *"le moindre retrait d'un seul item
[...] ferait echouer `ADR-0023` en silence jusqu'au prochain `mvn verify`."* Je ne la reintroduis
pas ici, meme si aucun retrait d'item n'est prevu par cette story - `HG-CM1-14` est deja
`couvert-valide`, et je ne veux pas que la premiere modification future de cette ligne, meme
minime, ait a redecouvrir ce blocage.

**Je n'ai pas retenu `nombreDeManches: 8`** (marge de 1) : rien ne coute a prendre la marge de 2,
puisque, contrairement a `DEC-0020`, le nombre d'items de cette entree n'est contraint par aucune
forme canonique susceptible de se reduire - il est simplement herite tel quel de `HG-CM1-14`. A
cout egal, je retiens la marge la plus large.

**`nombreDeManches: 7`** est aussi la valeur deja en usage dans ce catalogue pour le meme motif
arithmetique (`bandit-manchot-complements-10-cp`, `saisie-complements-10-cp`, `DEC-0020`,
ratifiees par `DEC-0064`) : je ne choisis pas cette valeur par alignement - `DEC-0064`, section 3,
l'interdit explicitement - mais parce qu'elle est le resultat independant du meme calcul de
marge appliquee ici.

### Pourquoi je ne prends pas les deux autres sorties

1. **Un onzieme item neuf.** Contrediction directe et documentee : le critere d'acceptation de
   `S35-CONS-4` dit *"elle ne cree aucun item neuf"*, et l'objectif unique de s35, arbitre par
   l'utilisatrice, est de fermer des refus et des reserves deja prononces, jamais d'ouvrir du
   contenu neuf. Ecrire un item pour satisfaire un garde-fou technique serait fabriquer du contenu
   pedagogique pour une raison qui n'en est pas une - exactement le geste que `nadia-prof` a
   toujours refuse quand une contrainte technique a cherche a se deguiser en decision de contenu.
2. **Fondre l'entree dans une autre.** Rouvrirait `ADR-0081`, une decision de grain deja rendue
   conjointement par moi et `nadia-prof`, sur un sujet que `S35-CONS-4` exclut explicitement de
   retoucher (*"elle ne rouvre pas la decision de grain deja rendue"*). Je ne la rouvre pas pour
   resoudre un probleme qui n'est pas un probleme de grain, mais un probleme de longueur de
   partie.

### Est-ce une question pedagogique ? Non, et je dis pourquoi

`DEC-0064`, section 4, reserve a `nadia-prof` le jugement sur *"la longueur d'une partie"* quand
elle gene un enfant. **Ce n'est pas ce qui est en jeu ici.** Je ne juge pas que sept manches
seraient une meilleure longueur de partie pour la competence "lire un document" ; je constate
que le vivier de dix items, herite sans changement d'une ligne deja validee, ne supporte pas dix
manches sans equite fragile. La question que je tranche est *"combien de manches ce vivier
peut-il porter sans re-servir un item deux fois de suite ni epuiser sa variete"*, pas *"combien de
manches un enfant de CM1 devrait-il lire"*. C'est la meme distinction que `DEC-0020` avait deja
posee pour "complements a 10", et le meme motif technique (2.c de `DEC-0064`, garde-fou
d'`ADR-0023` deja en place, non touche par cette decision) justifie que ce jugement m'appartienne.

### Le cout que j'accepte de payer

**Cette entree comptera 7 manches par partie, contre 10 pour les trois autres entrees issues de la
meme scission** (Situer dans le temps, Nommer en histoire, Nommer en geographie, dont les viviers
de 25, 45 et 30 items rendent le defaut de 10 manches large de marge). C'est une asymetrie reelle
entre quatre entrees qui partageaient hier un seul jeu, qu'un enfant qui passe de l'une a l'autre
peut ressentir. Je prefere payer ce cout-la, visible et borne, plutot que de creer un item pour
la seule raison de satisfaire un garde-fou, ou de rouvrir une decision de grain deja rendue.

## 3. Correction des criteres deja ecrits

`docs/backlog/backlog.md`, story `S35-CONS-4` : deux criteres sont ajoutes dans le meme geste que
cette decision (les deux gestes mecaniques nommes par `theo-archi`, section 6.3 de son avis, qui
rendraient sinon la story rouge garanti a l'execution), et un troisieme cite cette derogation :

- l'entree "Lire un document" porte `nombreDeManches: 7` (cette decision), et non le defaut de
  `DEC-0064` ;
- les items de cette seule entree **perdent** leur cle `ligneDeReferentiel`, puisqu'elle ne sert
  qu'une seule ligne de referentiel (`HG-CM1-14`) - le garde-fou qui verifie cette regle la refuse
  explicitement dans le cas contraire ;
- les quatre paquets issus de la scission conservent le meme descripteur `matiere` /`niveau`
  (`Histoire-geographie`, `CM1`) pour que `HIS-1` (au plus un tiers de restitution) continue de se
  compter sur le lot de matiere entier - 110 items, inchange - et non par paquet.

## 4. Justification pedagogique

Aucune - cette decision porte sur un parametre de catalogue (la longueur d'une partie,
`ADR-0012`), pas sur la qualite d'un contenu. `HG-CM1-14` est deja `couvert-valide` ; aucun de ses
dix items n'est touche, ni son texte, ni son exactitude, ni la tache qu'il sert.

## 5. Impact

- `docs/backlog/backlog.md`, story `S35-CONS-4` : deux criteres d'acceptation ajoutes et un
  troisieme cite (voir section 3).
- `adapters/adapter-content/src/main/resources/catalogue.yaml` : **non touche par cette decision**,
  aucun code n'etant modifie ici. L'entree `nombreDeManches: 7` sera ecrite par qui livrera
  `S35-CONS-4`, une fois affecte.
- `squad/programme/MATRICE-couverture.md` : aucune ligne ne bouge ; aucune note n'est requise, les
  110 items continuant de servir les memes onze lignes qu'avant la scission.
- `squad/decisions/produit/DEC-index.md` : ligne `DEC-0088` ajoutee.
- `squad/sprints/s35-fermer-ce-qui-n-a-pas-recu-le-go-pedagogique/planning.md` : le prealable
  bloquant nomme par `theo-archi` (section 6.2 de son avis) est leve ; `S35-CONS-4` est engageable
  sans garantir un rouge.

## 6. Ce que cette decision ne fait pas

- Elle ne tranche aucune question technique : la mesure et le calcul sont ceux deja rendus par
  `theo-archi`.
- Elle ne prejuge d'aucun Done pedagogique sur `S35-CONS-4` : ce jugement reste entierement celui
  de `nadia-prof`, qui audite chaque consigne texte devant items.
- Elle ne fait passer aucune ligne de `squad/programme/MATRICE-couverture.md` a `couvert-valide`.
- Elle n'affecte aucun developpeur a `S35-CONS-4` : c'est une decision de capacite, qui reste a
  `sofia-sm`.
- Elle ne rouvre ni `ADR-0023`, ni `ADR-0081`, ni `DEC-0064` : elle applique la regle de derogation
  que `DEC-0064`, section 3, laisse ouverte, elle ne la modifie pas.
- Elle ne cree aucun contenu neuf et ne touche a aucun des 110 items existants.

## 7. Alternatives considerees

**Un onzieme item sur `HG-CM1-14`.** Ecartee : contredit deux fois `S35-CONS-4` et l'objectif
unique de s35 (aucune ligne neuve, aucun contenu neuf). Voir section 2, point 1.

**Fondre "Lire un document" dans une autre entree.** Ecartee : rouvrirait `ADR-0081`, hors du
mandat de cette story. Voir section 2, point 2.

**`nombreDeManches: 9`, le plafond le plus proche du defaut.** Ecartee : plancher exige alors
exactement 10 items, la taille exacte de l'entree - egalite fragile qu'`ADR-0023` refuse par
principe, meme si techniquement verte aujourd'hui.

**`nombreDeManches: 8`.** Ecartee : marge de 1 seulement, alors que rien ne coute a prendre la
marge de 2 qu'offre `7`, le vivier de cette entree n'etant contraint par aucune forme canonique
susceptible de se reduire.
