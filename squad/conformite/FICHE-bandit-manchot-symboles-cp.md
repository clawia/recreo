# Fiche de conformite - bandit-manchot-symboles-cp

- **Mecanique** : bandit manchot.
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/cp-emc-symboles.yaml`
  (12 items, type `question`).
- **Entree de catalogue** : `bandit-manchot-symboles-cp`, titre servi *"Les symboles de la
  Republique"*, consigne servie *"Lis bien, puis dis le nom du symbole, ou ce qu'il represente."*,
  `nombreDeManches: 10`.
- **Ligne declaree** : `EMC-CP-08`.
- **Statut global** : **VALIDE** (troisieme audit du 2026-09-24, apres deux corrections ; les deux
  refus du meme jour sont leves, leurs traces sont conservees plus bas). Fiche ouverte
  par `camille-po` a l'ouverture du bloc de construction du sprint s41 (paquet et catalogue livres
  et fusionnes dans `round-two`, commit `b5b07966`).
- **Story** : `S41-EMC-SYMBOLES` - Reconnaitre et nommer les symboles de la Republique francaise
  (`docs/backlog/backlog.md`).
- **Competence programme visee** : enseignement moral et civique, CP (cycle 2), domaine "Acquerir
  et partager les valeurs de la Republique" -
  `squad/programme/enseignement-moral-et-civique/competences-cp.md`, `EMC-CP-08`. Ligne
  `non couvert` dans `squad/programme/MATRICE-couverture.md` au moment d'ouverture de cette fiche.
- **Arbitrage pedagogique prealable deja rendu, cite en entier dans la story, aucun arbitrage neuf
  a router** (`ARBITRAGE-PREALABLE-s31-ce-qui-ferme-une-ligne-d-emc-cp-le-catechisme-civique-et-ma-
  borne.md`, ligne 234) : *"oui (lot a 1,5) [...] Connaissance exacte. `FER-1` : drapeau, Marianne,
  La Marseillaise, fete nationale, chacun en bonne reponse. Deux taches : nommer le symbole
  decrit ; dire ce qu'il represente. `ART-1` : aucune reponse portee par une couleur percue."*
- **Lien decision** : `DEC-0095`.
- **Derniere mise a jour** : 2026-09-24 (troisieme audit apres correction, `nadia-prof` instance A).

---

## Criteres d'acceptation de la story (`docs/backlog/backlog.md`, `S41-EMC-SYMBOLES`)

- Etant donne le paquet livre, quand on le relit dans son entier, alors les quatre symboles du
  libelle (drapeau, Marianne, La Marseillaise, fete nationale) sont chacun en bonne reponse au
  moins une fois (`FER-1`).
- Etant donne un item, quand il s'affiche, alors il porte l'une des deux taches distinctes (nommer
  le symbole decrit ; dire ce qu'il represente), jamais uniquement la premiere sur tout le paquet
  (`FER-3`).
- Etant donne un item portant sur le drapeau, quand la question s'affiche, alors elle ne demande
  jamais la couleur d'un element (par exemple "de quelle couleur est le drapeau ?"), mais son nom,
  son hymne, sa devise ou sa date (`ART-1`).
- Etant donne cette manche, quand les propositions s'affichent, alors la bonne reponse y figure
  exactement une fois, avec des distracteurs qui sont d'autres symboles ou elements reels du
  registre republicain, jamais des elements sans rapport.
- Etant donne chaque item du paquet, quand il declare sa ligne de referentiel, alors il porte
  `EMC-CP-08` et uniquement elle.
- Etant donne le Done pedagogique de ce paquet, quand `nadia-prof` (instance A) le joue, alors
  elle confirme que les quatre symboles sont chacun servis et qu'aucune reponse ne se porte par
  une couleur percue.

## Ce que cette story ne fait pas

Elle ne demande jamais a l'enfant une couleur percue en bonne reponse (`ART-1`) ; elle ne fixe
aucun contenu factuel precis - reste a l'auteur du paquet puis a l'audit de `nadia-prof`.

---

## Bloc de validation pedagogique

# Audit du 2026-09-24 (`nadia-prof`, instance A de s41) - REFUSE

## 1. Ce que j'ai joue, et sur quoi

Reacteur reconstruit depuis `round-two` au commit `3f6b379f`
(`bash outils/mvn-jdk21.sh -B clean package -DskipTests`), `bootstrap/target/recreo.jar` relance
sur le JDK 21 designe par la toolchain. **Audit joue, pas lu** : cinq parties completes de dix
manches, plus deux parties ciblees sur un item precis (section 3), plus une partie ou je me trompe
volontairement a repetition pour voir le traitement de l'erreur.

**Precision de methode, et elle a failli me faire auditer le mauvais arbre** : le jar present dans
`bootstrap/target/` en debut de session etait anterieur a s41 et le catalogue qu'il servait ne
contenait aucun des trois jeux d'EMC de ce sprint. Je l'ai vu parce que j'ai cherche le jeu dans la
page `/niveaux/CP` avant de jouer, et non parce qu'un garde-fou me l'a dit. **Un jar present n'est
pas un jar a jour** : je le note ici pour la prochaine instance qui auditera, et c'est aussi
pourquoi la commande de reconstruction est ecrite ci-dessus plutot que sous-entendue.

Les **douze items** du paquet sont remontes a l'ecran au fil des cinq parties (douze enonces
distincts releves). Rien de ce qui suit ne repose sur la seule lecture du YAML.

## 2. Ce qui est tenu, et que je constate avant de refuser

- **`FER-1`** : les quatre symboles du libelle sont chacun en bonne reponse, vus a l'ecran - le
  drapeau (items 1 et 2), Marianne (3 et 4), la Marseillaise (5 et 6), la fete nationale (7 et 8).
- **`FER-3`** : deux taches distinctes, jouees toutes les deux - nommer le symbole decrit (items 1
  a 8), dire ce qu'il represente (items 9 a 12).
- **`ART-1`, la condition que j'avais posee d'avance** : aucun item ne demande une couleur, aucun
  mot de couleur n'est servi a l'enfant. Le mot *tricolore* n'existe que dans le descripteur
  (recopie du referentiel), jamais dans un enonce, une proposition ou un indice. **Cette condition
  est tenue, et elle l'est a l'ecran.**
- **Lisibilite** : aucun enonce ne depasse quinze mots ni deux phrases (`LON-1`, `LON-2`), la
  seconde phrase est toujours la question. Les huit enonces de la tache de nom sont courts et
  concrets.
- **Traitement de l'erreur** : premiere erreur, *"Ce n'est pas ca. Essaie encore !"* suivi de
  l'indice ; troisieme erreur, la bonne reponse est revelee et l'enfant peut avancer. Aucun
  blocage.
- **Distracteurs** : toujours d'autres elements du registre republicain, jamais un element sans
  rapport.

## 3. Le motif du refus : deux items ont deux reponses defendables, et la bonne est refusee a l'ecran

**`EMC-5`, posee le 2026-09-03 dans
`squad/programme/enseignement-moral-et-civique/competences-cp.md` pour la matiere entiere** :
*"Aucun item n'a deux reponses defendables."* Ce n'est pas une barre neuve et je ne durcis rien :
elle precede de trois semaines l'ecriture de ce paquet.

**Item 9, joue, capture d'ecran de la partie** :

- enonce : *"Que represente le drapeau francais ?"*
- les trois propositions telles que l'enfant les voit : *"le pays et la Republique"* /
  *"l'hymne de la France"* / *"la Republique"*
- j'ai appuye sur *"la Republique"*, et l'application repond : **"Ce n'est pas ca. Essaie
  encore !"**

**Le drapeau tricolore represente la Republique francaise** : c'est l'embleme national de la
Republique, et *"la Republique"* est une reponse juste a la question posee. Le jeu la declare
fausse. **Une reponse exacte qu'un jeu scolaire sanctionne n'est pas une maladresse de
formulation, c'est un contresens enseigne.** De surcroit la proposition refusee est **contenue
mot pour mot dans la proposition couronnee** : *"le pays et la Republique"* contient
*"la Republique"*. Aucun enfant de six ans ne peut trancher entre une reponse et une partie
d'elle-meme, et il n'y a rien a trancher puisque les deux sont vraies.

**Item 10, joue, meme capture** :

- enonce : *"Que represente Marianne ?"*
- propositions : *"le pays et la Republique"* / *"un grand jour de fete pour la France"* /
  *"la Republique"*
- j'ai appuye sur *"le pays et la Republique"*, et l'application repond : **"Ce n'est pas ca.
  Essaie encore !"**

Les deux items sont donc **symetriquement fautifs** : la meme paire de propositions y sert de bonne
reponse et de distracteur, dans un sens sur l'item 9 et dans l'autre sur l'item 10. Ce que le
paquet demande a l'enfant sur ces deux items n'est pas de connaitre un symbole, c'est de deviner
laquelle de deux formules quasi identiques l'auteur a decidee de couronner sur cet item-la. **Deux
items sur douze, soit les deux tiers de ce que le paquet apprend sur Marianne et sur le drapeau
dans la tache de sens.**

**Correction demandee, et elle est bornee** : sur la tache de sens (items 9 a 12), les quatre
reponses attendues doivent etre **mutuellement exclusives** - aucune proposition d'un item ne peut
etre vraie en meme temps que la bonne reponse de cet item, et **aucune proposition ne peut etre
contenue dans une autre**. Concretement, le couple *"le pays et la Republique"* / *"la Republique"*
disparait : une seule des deux formules survit, et le symbole qui perd la sienne recoit une reponse
qui ne recouvre pas celle de l'autre. Les items 11 (la Marseillaise -> *"l'hymne de la France"*) et
12 (le 14 juillet -> *"un grand jour de fete pour la France"*) sont exacts et discriminants, ils ne
sont pas concernes. Les huit items de la tache de nom ne sont pas concernes non plus : **ils sont
bons et je demande qu'ils ne soient pas reecrits.**

## 4. Second point a corriger dans le meme passage (n'est pas, a lui seul, le motif du refus)

**Item 3** : *"Cette statue de femme est dans toutes les mairies. Comment s'appelle-t-elle ?"*
Aucune obligation n'impose un buste de Marianne dans une mairie ; il y en a dans la grande majorite
d'entre elles, pas dans **toutes**. *"dans presque toutes les mairies"* dit la meme chose et est
exact. Point mineur, releve parce que la ligne porte sur une **connaissance exacte** et que le
paquet est de toute facon rouvert.

## 5. Ce dont je reste la seule garde sur ce paquet

Dit explicitement pour que l'equipe ne croie pas couvert ce qui ne l'est pas. La CI est verte sur
cet increment et **aucun de ces deux defauts n'a ete vu par elle** : ils ne sont pas de nature a
etre vus par un automate.

| Propriete | Automatisable ? | Qui la tient |
|---|---|---|
| `EMC-5` - aucun item n'a deux reponses defendables | non | **moi seule** |
| Aucune proposition contenue dans une autre proposition du meme item | **oui, et ce serait mordable** | personne aujourd'hui |
| Exactitude de chaque enonce et de chaque reponse contre source | non | **moi, contre source** |
| `ART-1` - aucune reponse portee par une couleur percue | partiellement | moi ; **tenue ici** |

La deuxieme ligne est la seule des quatre qu'un garde-fou pourrait prendre : *"aucune proposition
d'un item n'est une sous-chaine d'une autre proposition du meme item"* est une propriete de forme,
elle se verifie sur le paquet sans jugement semantique, et elle aurait fait rougir l'item 9 avant
qu'un enfant ne le voie. Je la nomme ; l'ecrire ou non appartient a `theo-archi`.

---

> **Done pedagogique : REFUSE.** 2026-09-24, `nadia-prof`, instance A de s41.
>
> **Motif** : sur la tache de sens, l'item 9 (*"Que represente le drapeau francais ?"*) declare
> fausse la proposition *"la Republique"*, qui est une reponse exacte - le drapeau tricolore
> represente bien la Republique francaise - et qui est de surcroit contenue dans la proposition
> couronnee, *"le pays et la Republique"*. L'item 10 (*"Que represente Marianne ?"*) porte le meme
> defaut en miroir. Deux reponses defendables par item : `EMC-5`, posee le 2026-09-03 pour la
> matiere entiere. Verifie en jouant, pas en lisant.
>
> **Correction demandee** : rendre mutuellement exclusives les quatre reponses de la tache de sens
> (items 9 a 12), aucune proposition ne pouvant etre vraie en meme temps que la bonne reponse ni
> contenue dans elle. Corriger au passage *"dans toutes les mairies"* en *"dans presque toutes les
> mairies"* (item 3). **Les huit items de la tache de nom sont exacts, discriminants et lisibles :
> ils ne doivent pas etre reecrits.**
>
> **Ce refus renvoie a `camille-po`, qui en fait du backlog.** Il ne bloque pas le sprint suivant.
> `EMC-CP-08` reste `non couvert` dans `MATRICE-couverture.md` ; je ne touche pas la matrice.

---

# Second audit (2026-09-24, apres correction de `marc-dev` au commit `60cb9e34`) - REFUSE

**Le premier refus ci-dessus n'est pas efface et ne le sera pas.** Ce second audit s'ajoute.

## 6. Ce que j'ai rejoue

Reacteur reconstruit sur `round-two` a jour (`bcbe3b5f`,
`bash outils/mvn-jdk21.sh -B clean package -DskipTests`), jar relance sur le JDK 21 designe par la
toolchain. Quatre parties completes plus deux parties ciblees sur les items 9 et 10. **Les douze
items sont remontes a l'ecran**, y compris les huit de la tache de nom que je n'avais pas demande
de rejouer : je verifie a chaque fois que la correction n'a rien casse ailleurs.

## 7. Ce que la correction paie, et je le constate d'abord

- **Item 3 : paye et exact.** *"Cette statue de femme est dans presque toutes les mairies"*, vu a
  l'ecran. Le point mineur de ma section 4 est clos.
- **Plus aucune proposition n'est contenue dans une autre.** Les quatre reponses de la tache de
  sens sont desormais *"le pays"*, *"la Republique"*, *"l'hymne de la France"*, *"un grand jour de
  fete pour la France"*. Le couple *"le pays et la Republique"* / *"la Republique"* a disparu.
  **La seconde des deux clauses de ma correction est payee.**
- Les huit items de la tache de nom sont inchanges et toujours bons. Le traitement de l'erreur est
  inchange. `ART-1` est toujours tenue : aucun mot de couleur n'est servi a l'enfant.

## 8. Ce que la correction ne paie pas : la premiere clause, qui etait le motif meme du refus

Ma correction demandait **deux** choses, et elle les demandait dans cet ordre (section 3) :
*"aucune proposition d'un item ne peut etre vraie en meme temps que la bonne reponse de cet item,
**et** aucune proposition ne peut etre contenue dans une autre."* La seconde est payee. **La
premiere ne l'est pas, et c'est elle qui portait le motif du refus.**

**Item 9, rejoue, capture de la partie** :

- enonce : *"Que represente le drapeau francais ?"*
- propositions telles que l'enfant les voit : *"l'hymne de la France"* / *"la Republique"* /
  *"le pays"*
- j'ai appuye sur *"la Republique"*, et l'application repond, **exactement comme avant la
  correction** : **"Ce n'est pas ca. Essaie encore !"**

Le drapeau tricolore est l'embleme national de la Republique francaise. *"la Republique"* est une
reponse juste a la question posee, et le jeu la declare toujours fausse. **Le defaut que j'ai
refuse est intact** : seule sa forme la plus visible, le recouvrement de deux formules, a ete
retiree.

**Item 10, rejoue** : *"Que represente Marianne ?"*, propositions *"un grand jour de fete pour la
France"* / *"le pays"* / *"la Republique"*. J'ai appuye sur *"le pays"* : *"Ce n'est pas ca."*
Marianne est la figure de la Republique **et** la personnification de la France ; la proposition
est defendable et elle est sanctionnee. Meme defaut, moins franc que sur l'item 9.

## 9. Et la correction introduit un defaut neuf, qu'il faut nommer

En donnant au drapeau la reponse *"le pays"* **et en gardant "la Republique" comme distracteur du
meme item**, le paquet n'enseigne plus seulement une chose incomplete : il enseigne activement
que le drapeau represente le pays **et pas la Republique**. Sur une ligne dont le domaine est
*"Acquerir et partager les valeurs de la Republique"* et dont le libelle est *"les symboles de la
Republique francaise"*, c'est le contraire de ce que la competence demande. **Avant la correction,
l'enfant qui repondait juste etait sanctionne ; apres, il est sanctionne et on lui apprend
pourquoi, a tort.**

## 10. Ma part dans ce second tour, et je la dis avant de redemander

**La prescription concrete que j'avais ajoutee a ma correction etait insuffisante, et elle a
oriente vers ce qui vient d'etre fait.** J'avais ecrit : *"Concretement, le couple 'le pays et la
Republique' / 'la Republique' disparait : une seule des deux formules survit, et le symbole qui
perd la sienne recoit une reponse qui ne recouvre pas celle de l'autre."* Un auteur qui applique
cette phrase a la lettre, sans la clause de verite qui la precede, obtient exactement le paquet que
je viens de rejouer. **La clause de verite etait ecrite, elle etait meme ecrite en premier, mais
mon exemple la contredisait en pratique.** Je ne deplace pas ma barre - je la reecris de facon a ce
qu'elle ne puisse plus etre lue a moitie.

## 11. Correction demandee, formulee cette fois sans exemple qui puisse se substituer a la regle

**La propriete, et elle s'evalue item par item, jamais sur le paquet entier** :

> **Dans un item donne, aucune des trois propositions affichees ne doit etre vraie en meme temps
> que la bonne reponse de cet item.** La contrainte de non-recouvrement s'apprecie de la meme
> facon : a l'interieur d'un item.

Ce que cela implique ici, et c'est mecanique : *"la Republique"* est vraie du drapeau, donc **elle
ne peut pas figurer parmi les propositions de l'item 9**. *"le pays"* est defendable de Marianne,
donc **elle ne peut pas figurer parmi les propositions de l'item 10**. Les deux items doivent donc
tirer leurs distracteurs parmi les reponses **fausses** pour leur symbole : *"l'hymne de la
France"* et *"un grand jour de fete pour la France"* sont fausses du drapeau comme de Marianne, et
suffisent aux deux items.

**Une fois ce tirage corrige, le choix du libelle de la bonne reponse de l'item 9 redevient libre**
et il appartient a l'auteur : *"le pays et la Republique"* redevient possible et exact, puisque
*"la Republique"* ne sera plus une proposition du meme item. Je ne tranche pas ce libelle - **je
tranche que l'enfant ne doit jamais voir, dans un meme item, deux propositions vraies.**

Les items 11 et 12 tiennent deja cette propriete et ne doivent pas etre touches : *"la Republique"*
et *"le pays"* sont fausses de la Marseillaise comme du 14 juillet. Les huit items de la tache de
nom et l'item 3 corrige ne sont pas concernes.

---

> **Done pedagogique : REFUSE** (second refus). 2026-09-24, `nadia-prof`, instance A de s41.
>
> **Motif** : la correction paie la clause de non-recouvrement et laisse intacte la clause de
> verite, qui portait le motif du premier refus. Rejoue : l'item 9 (*"Que represente le drapeau
> francais ?"*) affiche toujours *"la Republique"* parmi ses trois propositions et repond toujours
> *"Ce n'est pas ca"* quand l'enfant l'appuie, alors que le drapeau tricolore est l'embleme de la
> Republique. L'item 10 sanctionne *"le pays"* devant Marianne, qui est defendable. En donnant au
> drapeau la reponse *"le pays"* tout en gardant *"la Republique"* en distracteur du meme item, la
> correction ajoute un defaut neuf : le paquet enseigne desormais que le drapeau represente le pays
> **et pas** la Republique, sur une ligne dont le libelle est *"les symboles de la Republique
> francaise"*.
>
> **Correction demandee** : dans un item donne, aucune des trois propositions ne doit etre vraie en
> meme temps que la bonne reponse. Concretement et mecaniquement : *"la Republique"* sort des
> propositions de l'item 9, *"le pays"* sort de celles de l'item 10 ; les deux items prennent leurs
> distracteurs parmi *"l'hymne de la France"* et *"un grand jour de fete pour la France"*, fausses
> pour les deux symboles. Le libelle de la bonne reponse de l'item 9 redevient alors libre et
> m'importe peu. Items 11, 12, les huit items de la tache de nom et l'item 3 corrige : **ne pas
> toucher**.
>
> **Ma part, dite avant de redemander** (section 10) : l'exemple concret que j'avais joint a ma
> correction pouvait s'appliquer a la lettre sans satisfaire la clause de verite qui le precedait.
> La regle ci-dessus est reecrite pour ne plus pouvoir etre lue a moitie. **Ce n'est pas une barre
> relevee : c'est la meme, ecrite sans son exemple trompeur.**
>
> **Ce refus renvoie a `camille-po`.** Il ne bloque pas le sprint suivant. `EMC-CP-08` reste
> `non couvert` ; je ne touche pas la matrice.

---

# Troisieme audit (2026-09-24, apres correction de `marc-dev` au commit `8860e01c`) - VALIDE

**Les deux refus ci-dessus ne sont pas effaces et ne le seront pas.** Ils sont leves par ce
troisieme audit, leur trace reste lisible - un refus efface n'a aucune valeur de preuve.

## 12. Ce que j'ai rejoue

Reacteur reconstruit sur `round-two` a jour (`1ecdecf6`,
`bash outils/mvn-jdk21.sh -B clean package -DskipTests`), jar relance sur le JDK 21 designe par la
toolchain. Quatre parties completes plus trois parties ciblees sur les items 9 et 10 - bonne
reponse et mauvaise reponse, pour voir les deux ecrans. **Les douze items sont remontes a
l'ecran.**

## 13. La propriete que je demandais est tenue, item par item

Ma demande du second audit (section 11) : *"dans un item donne, aucune des trois propositions
affichees ne doit etre vraie en meme temps que la bonne reponse de cet item."* Les quatre items de
la tache de sens, vus a l'ecran :

| Item | Bonne reponse | Les deux autres propositions | Une seule vraie ? |
|---|---|---|---|
| 9 - le drapeau | *le pays* | *l'hymne de la France*, *un grand jour de fete pour la France* | **oui** |
| 10 - Marianne | *la Republique* | *l'hymne de la France*, *un grand jour de fete pour la France* | **oui** |
| 11 - la Marseillaise | *l'hymne de la France* | *la Republique*, *le pays* | **oui** |
| 12 - le 14 juillet | *un grand jour de fete pour la France* | *la Republique*, *l'hymne de la France* | **oui** |

*"la Republique"* ne figure plus parmi les propositions de l'item 9, *"le pays"* ne figure plus
parmi celles de l'item 10. **Les deux items ne s'opposent plus l'un a l'autre.** Joue : j'appuie
sur *"le pays"* devant le drapeau, l'application repond *"Bravo !"* ; j'appuie sur *"la
Republique"* devant Marianne, *"Bravo !"* ; j'appuie sur *"un grand jour de fete pour la France"*
devant le drapeau, *"Ce n'est pas ca. Essaie encore !"* suivi de l'indice. **Plus aucune reponse
exacte n'est sanctionnee.** C'etait le motif des deux refus, et il est paye.

## 14. Le libelle *"le pays"*, et pourquoi je ne le rouvre pas

Le drapeau recoit desormais *"le pays"*, ce qui est vrai et incomplet : le drapeau tricolore
represente aussi la Republique. **J'ai ecrit au second audit (section 11) que ce libelle etait
libre et qu'il appartenait a l'auteur, une fois le tirage corrige. Je tiens cette phrase.** Le
defaut que j'avais nomme n'etait pas le libelle seul, c'etait le libelle **conjugue** au maintien
de *"la Republique"* en distracteur du meme item - cette conjonction enseignait *"le drapeau
represente le pays et pas la Republique"*. La conjonction a disparu : le paquet n'enseigne plus
rien de faux, il enseigne une moitie vraie.

Ce que la ligne demande - *reconnaitre et nommer les symboles de la Republique francaise* - est
servi par les **huit items de la tache de nom**, qui sont la competence elle-meme et que j'ai
valides des le premier audit. La tache de sens est la seconde tache exigee par `FER-3`, et le lien
entre le drapeau et la Republique y reste porte ailleurs : par l'item 10 (Marianne -> *la
Republique*) et par l'enonce de l'item 7 (*"C'est le jour ou la France fete la Republique"*).
**Aucune reserve ouverte sur ce libelle.**

## 15. Un point que j'ai examine a ce passage, et que je ne retiens pas contre ce lot

Je le consigne parce que je l'ai vu et que ne pas l'ecrire serait pire que l'ecrire.

**Constat.** L'item 11 (*"Que represente la Marseillaise ?"*, bonne reponse *"l'hymne de la
France"*) porte *"le pays"* en distracteur, et son indice est *"On la chante pour honorer le
pays"* - l'indice contient donc le mot d'une proposition qu'il ne faut pas choisir.

**Pourquoi je ne le retiens pas.** J'ai juge l'item 11 exact et discriminant **deux fois par
ecrit**, au premier audit (section 3, *"les items 11 et 12 sont exacts et discriminants"*) et au
second (section 11, *"'la Republique' et 'le pays' sont fausses de la Marseillaise comme du 14
juillet"*). Cet item n'a pas ete touche par les corrections. **Revenir dessus au troisieme passage,
sur une ligne que j'ai deja fermee deux fois, ferait de ma porte un caprice** - et c'est exactement
ce que je m'interdis. Sur le fond je tiens aussi mon jugement : la question demande **ce que le
symbole est** dans un jeu de quatre symboles et de quatre reponses appariees, et la Marseillaise
est l'hymne, pas le pays.

**Ce que j'en fais** : une observation datee du 2026-09-24, **non bloquante et qui ne conditionne
rien**. Ce n'est pas une reserve : `camille-po` n'a pas de story a en tirer, et le Done ci-dessous
n'en depend pas. Si un jour ce paquet est rouvert pour un autre motif, l'indice de l'item 11
gagnerait a ne pas reprendre le mot d'un distracteur (*"On la chante debout, lors des ceremonies"*
dirait la meme chose sans ce mot).

## 16. Rien n'a casse ailleurs

Verifie a l'ecran sur les douze items : les huit items de la tache de nom sont inchanges au
caractere pres et toujours bons ; l'item 3 corrige au second passage dit toujours *"dans presque
toutes les mairies"* ; `FER-1` est tenue, les quatre symboles restent chacun en bonne reponse ;
`FER-3` est tenue, les deux taches sont servies ; `ART-1` est tenue, aucun mot de couleur n'est
servi a l'enfant ; `LON-1` et `LON-2` sont tenues ; le traitement de l'erreur est intact
(*"Ce n'est pas ca. Essaie encore !"* puis l'indice, puis revelation au troisieme echec) ; l'ecran
de fin de partie s'affiche normalement.

## 17. Ce dont je reste la seule garde sur ce paquet

Inchange, et je le redis parce qu'un verdict vert ne le supprime pas : `EMC-5` (aucun item a deux
reponses defendables) et l'exactitude de chaque enonce contre source sont des jugements semantiques
qu'aucun automate de ce depot ne rend. **La CI etait verte sur les deux versions que j'ai
refusees**, y compris sur celle ou le jeu sanctionnait une reponse exacte.

La propriete de forme que j'avais nommee au premier audit reste, elle, mordable et non ecrite :
*"aucune proposition d'un item n'est une sous-chaine d'une autre proposition du meme item"*. Elle
aurait attrape le premier defaut avant qu'un enfant ne le voie. Elle n'aurait **pas** attrape le
second, qui demandait de savoir ce qui est vrai. Je la nomme a nouveau ; l'ecrire appartient a
`theo-archi`.

---

> **Done pedagogique : VALIDE.** 2026-09-24, `nadia-prof`, instance A de s41. Les deux refus du
> meme jour sont **leves**, leurs traces sont conservees ci-dessus.
>
> **Ce que j'ai verifie** : les douze items rejoues a l'ecran sur un jar reconstruit depuis
> `round-two` a jour. Sur les quatre items de la tache de sens, une seule proposition est vraie par
> item - *"la Republique"* est sortie des propositions de l'item 9, *"le pays"* de celles de l'item
> 10. Joue dans les deux sens : aucune reponse exacte n'est plus sanctionnee, et les mauvaises
> reponses declenchent bien l'indice. Les huit items de la tache de nom sont inchanges et toujours
> exacts, `FER-1`, `FER-3`, `ART-1`, `LON-1` et `LON-2` sont tenues, aucun distracteur n'est
> absurde, un enfant bloque avance.
>
> **`camille-po` peut passer `EMC-CP-08` a `couvert-valide`** dans `MATRICE-couverture.md`. Je ne
> touche pas la matrice.
>
> **Aucune reserve ouverte.** L'observation de la section 15 (l'indice de l'item 11 reprend le mot
> d'un distracteur) ne conditionne rien et n'appelle aucune story : je ne rouvre pas un item que
> j'ai ferme deux fois.
