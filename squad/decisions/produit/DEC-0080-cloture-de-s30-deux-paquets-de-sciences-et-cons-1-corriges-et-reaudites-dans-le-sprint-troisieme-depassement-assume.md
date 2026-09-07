# DEC-0080 - Cloture de s30 : les deux paquets de sciences refuses et le reliquat de `CONS-1` sont corriges et reaudites dans le sprint ; le sprint passe a 8,50 sur une borne de 8,0, troisieme depassement consecutif, assume et date

- **Type** : deux arbitrages de l'utilisatrice, enregistres et non rouverts.
- **Date** : 2026-09-06
- **Auteur** : `camille-po`
- **Sprint** : s30 (cloture)
- **Competence programme visee** : transverse - pas de contenu pedagogique. Cette decision arbitre
  une composition et un cout de sprint ; les competences visees par les deux paquets de sciences
  sont deja nommees dans `docs/backlog/backlog.md`, stories `S30-1` et `S30-2`, et le Done
  pedagogique des deux reaudits reste enti erement au jugement de `nadia-prof`.
- **Statut** : Acceptee. **Non executee au moment ou j'ecris** : les correctifs sont en cours
  d'ecriture par `lea-dev` sur son propre worktree, et les deux reaudits restent a rendre par
  `nadia-prof`. Cette decision enregistre le cout que l'arbitrage engage, pas un resultat deja
  constate.
- **Lien** : `DEC-0079` (perimetre de s30, `PAR-9` : budget opposable de 4,0 par instance, borne de
  8,0), `DEC-0078` (`CONS-1`), `DEC-0076` (precedent de forme : corriger et reauditer un increment
  refuse dans le sprint plutot que le reporter, deja arbitre dans le meme sens a la cloture de s28),
  `squad/sprints/s30-sciences-cm1-et-le-prix-des-reserves/verdicts-nadia-instance-A.md` (section 7,
  reliquat de `CONS-1` sur cinq entrees ; section 10, cout reel de l'instance),
  `squad/sprints/s30-sciences-cm1-et-le-prix-des-reserves/verdicts-nadia-instance-B.md` (section 3,
  les deux refus ; section 5, les trois issues chiffrees et l'avertissement du troisieme
  depassement), `squad/conformite/FICHE-bandit-manchot-chaine-alimentaire-cm1.md`,
  `squad/conformite/FICHE-bandit-manchot-objet-technique-cm1.md` (motifs de refus et proprietes a
  tenir).

---

## 1. Le fait, avant tout arbitrage

**Instance B - deux paquets de sciences REFUSES le 2026-09-06.**

- `SCI-CM1-17` (milieu proche et chaine alimentaire, `bandit-manchot-chaine-alimentaire-cm1`) :
  cinq items de la seconde tache sont un seul item servi cinq fois avec un autre nom d'animal ;
  `FER-2` et `FER-3` echouent. Six items sur onze tiennent et ne sont pas a refaire.
- `SCI-CM1-12` (objet technique, `bandit-manchot-objet-technique-cm1`) : les quatre items de
  fonction reproduisent le defaut de designation de `OBJ-R2`, un jour apres qu'il a ete nomme sur le
  paquet jumeau de CP ; `FER-2` echoue. Sept items sur onze tiennent et ne sont pas a refaire.

`nadia-prof` chiffre chaque reaudit a 0,5 par `PAR-10` (un rejeu se paie par paquet), soit **1,0**
pour les deux. Sa marge disponible sur son budget opposable de 4,0 (`PAR-9`) est de **0,5** : elle
consomme 3,5 sur les quatre lots de contenu et le bloc D (`POS-R2`/`POS-R3`), et l'ecrit elle-meme -
*"je peux reauditer une des deux corrections dans s30, pas les deux"*. Elle chiffre trois issues et
laisse le choix a `camille-po` et `sofia-sm` ; la deuxieme, *"les deux reprises sont livrees"*, elle
la chiffre a **8,5 sur une borne de 8,0** et la nomme d'avance comme *"le troisieme depassement de
suite"*.

**Instance A - le reliquat de `CONS-1` reste ouvert sur cinq entrees.** Rejuge une par une contre le
paquet qu'elle introduit, la consigne generique ne tient que sur cinq des dix entrees reecrites ;
les cinq qui ne tiennent pas (`corps-sens-cp`, `histoire-geographie-cm1`, `anglais-cm1`,
`droit-devoir-cm1`, `vivant-non-vivant-cp`) restent a corriger, pour un reliquat chiffre a **0,5**
en rejeu borne. Sa marge disponible sur son budget opposable de 4,0 est egalement de **0,5** : elle
consomme 3,5 sur les cinq blocs de reprise, `CONS-1`, `REV-3` et la passe transverse.

## 2. Les deux arbitrages de l'utilisatrice, rendus le 2026-09-06

### 2.1 Le reliquat de `CONS-1` est corrige et reaudite dans le sprint

Sur la marge de 0,5 de l'Instance A. **Consequence** : l'Instance A finit a **4,0 pile sur son
budget opposable de 4,0**, marge non consommee ramenee a zero. Aucun depassement de son budget
individuel.

### 2.2 Les deux paquets de sciences refuses sont corriges et reaudites dans le sprint

Plutot que d'en reporter un a s31, ce que l'issue 1 de `nadia-prof` permettait sans aucun
depassement. **Consequence, chiffree sans l'adoucir** : l'Instance B consomme 3,50 + 1,0 (deux
reaudits a 0,5 chacun) = **4,50 sur son budget opposable de 4,0 (`PAR-9`)** - un depassement de
**0,50** de son budget individuel, qui reste sous le plafond dur individuel de 5,0 (`PAR-8`). Le
sprint entier passe de 8,0 (Instance A a 4,0 + Instance B a 4,0) a **8,50 sur une borne de 8,0** -
un depassement de **0,50** de la borne du sprint.

## 3. Le motif du depassement, ecrit pour ne pas etre decouvert au comptage de cloture

**Le motif : deux paquets ecrits, payes en contenu, et corrigeables en contenu pur.** Aucun des deux
paquets n'est a jeter - six items sur onze tiennent sur la chaine alimentaire, sept sur onze sur
l'objet technique, et les motifs de refus portent sur une partie identifiee du paquet, pas sur son
ensemble. Laisser un des deux refus attendre s31 aurait laisse une matiere qui vient tout juste
d'ouvrir (aucune ligne de sciences et technologie CM1 n'existait avant ce sprint) fermer moins de
lignes que ce que le travail deja livre permet, alors que la correction ne demande pas de contenu
neuf mais la reprise de ce qui est deja ecrit.

**C'est le troisieme depassement consecutif de la borne d'un sprint**, et je l'ecris comme tel plutot
que de le laisser se decouvrir au comptage :

| Sprint | Cout reel | Borne | Ecart | Decision |
|---|---|---|---|---|
| s28 | 5,25 | 5,0 | +0,25 | `DEC-0076` |
| s29 | 9,50 | 8,0 | +1,50 | `DEC-0077`, amendee a la cloture |
| s30 | 8,50 | 8,0 | +0,50 | `DEC-0080` (la presente decision) |

`nadia-prof` a elle-meme ecrit, dans son arbitrage prealable de s30, qu'*"une borne qu'on depasse
deux sprints de suite n'est plus une borne"*. **`PAR-9` a ete posee par elle a ce sprint meme,
contre elle-meme, pour corriger le defaut de `PAR-8` qui avait laisse s29 couter 9,50 sur 8,0 sans
que la composition qui le produisait soit arbitree a l'avance.** Le present arbitrage ne revient pas
sur `PAR-9` : le budget opposable de 4,0 par instance reste la regle, l'Instance A le respecte
exactement, et le depassement de l'Instance B est **connu, chiffre et arbitre avant qu'il soit
consomme**, non decouvert au comptage de cloture comme l'avait ete celui de s29. C'est la difference
de forme que ce depassement-ci porte, et qui manquait au precedent.

**Precedent de forme : `DEC-0076`.** A la cloture de s28, l'utilisatrice avait deja arbitre de
corriger et reauditer un increment refuse dans le sprint plutot que de laisser un contenu faux
servi en production jusqu'au sprint suivant, au prix d'un depassement alors chiffre a 5,25 sur 5,0.
Le present arbitrage est rendu dans le meme sens, sur un fait de meme nature - deux refus au lieu
d'un, un budget par instance au lieu d'une borne unique - et le motif de l'utilisatrice n'est pas
rediscute ici : il est enregistre.

## 4. Ce que je ratifie

> **Le cout reel de s30 est 8,50 sur la borne de 8,0, par arbitrage explicite de l'utilisatrice du
> 2026-09-06**, rendu apres que `nadia-prof` a ecrit les deux refus, le reliquat de `CONS-1` et le
> prix exact de chaque reprise. Ce depassement est le troisieme consecutif (s28 : +0,25 ; s29 :
> +1,50 ; s30 : +0,50) et il est ecrit ici avant d'etre consomme, pas decouvert au comptage de
> cloture. L'Instance A finit a 4,0 pile sur son budget opposable, sans depassement individuel ;
> l'Instance B finit a 4,50 sur un budget opposable de 4,0, un depassement de 0,50 qui reste sous
> son plafond dur individuel de 5,0 (`PAR-8`).

## 5. Ce que cette decision ne fait pas

- Elle ne prononce aucun Done pedagogique : les deux reaudits de sciences et la reverification du
  reliquat de `CONS-1` restent entierement au jugement de `nadia-prof`, non encore rendus au moment
  ou j'ecris.
- Elle ne fait bouger aucune ligne de `squad/programme/MATRICE-couverture.md` : `SCI-CM1-17` et
  `SCI-CM1-12` restent `non couvert` tant que le reaudit n'est pas rendu ; je ne les anticipe pas.
- Elle ne cree aucune story neuve : les corrections des deux paquets et du reliquat de `CONS-1`
  relevent des stories deja ouvertes (`S30-1`, `S30-2`, `S30-9`, `docs/backlog/backlog.md`).
- Elle ne modifie pas `PAR-9` ni le budget opposable de 4,0 par instance pour les sprints suivants :
  cette regle reste celle de `DEC-0079`, posee par `nadia-prof`. Le depassement qu'elle enregistre
  est un arbitrage explicite sur ce sprint precis, pas un precedent silencieux qui la vide de sens.

## 6. Impact

- `squad/decisions/produit/DEC-index.md` : ligne `DEC-0080` ajoutee.
- Aucun impact sur `squad/programme/MATRICE-couverture.md` (differe a la cloture, une fois les
  reaudits rendus) ni sur `docs/backlog/backlog.md` (aucune story neuve).

## 7. Amendement du 2026-09-07 (`camille-po`) - les deux reaudits sont rendus ; le cout prevu en
## section 4 se confirme au centime pres ; `CONS-1` ne ferme pas entierement ; `PAR-11` est
## enregistree

**Les deux reaudits, rendus le 2026-09-06 par `nadia-prof`.**

**Instance B - les deux refus sont leves.** `SCI-CM1-17` (chaine alimentaire) et `SCI-CM1-12`
(objet technique) passent **REFUSE -> VALIDE SOUS RESERVE** apres correction des taches en cause.
Avec `SCI-CM1-05` et `SCI-CM1-02`, deja fermees au premier passage, **les quatre lignes de sciences
visees par cette instance ferment**, le plafond arithmetique de `DEC-0079` (4 lignes) etant atteint
sur cette seule moitie. Trois reserves neuves du reaudit (`CHA-R1`, `OBT-R1`, `OBT-R2`), aucune
bloquante. **Cout final : 4,50 / 4,00**, exactement le chiffre que la section 4 ci-dessus avait
enregistre avant d'etre consomme.

**Instance A - le reliquat de `CONS-1` n'est pas entierement corrige, et c'est different de ce que
la section 1 laissait attendre.** Rejuge une par une contre son paquet, **neuf entrees sur dix
tiennent desormais**, pas cinq : trois defauts de designation de tache et une inexactitude de fait
(`ANG-R2`, **levee**) sont payes dans le reaudit. **Une seule entree ne tient toujours pas** -
`bandit-manchot-histoire-geographie-cm1`, dont la consigne nomme deux taches qui excluent 31 des 57
items du paquet, dont toute la geographie. `CONS-1` **n'est donc pas levee** : elle est ramenee de
cinq entrees ouvertes a une seule, routee au backlog de s31 (rejeu borne, 0,5). **Cout final :
4,00 / 4,00**, egalement le chiffre attendu en section 4 - le reaudit a coute exactement les 0,5 de
marge prevus, quel que soit le nombre d'entrees qu'il a fallu rejuger pour y arriver.

**Ce que je corrige dans la section 1 ci-dessus** : elle presentait le reliquat de `CONS-1` comme
devant etre "corrige" au sens de soldee ; le mot etait impropre. Le reliquat est **rejuge**, pas
soldee au premier reaudit : son perimetre change de forme (cinq entrees a une), il ne disparait pas.
`squad/sprints/s30-sciences-cm1-et-le-prix-des-reserves/verdicts-nadia-instance-A.md`, complement du
2026-09-06, porte le detail entree par entree.

> **Le cout reel de s30 se confirme a 8,50 sur la borne de 8,0**, sans aucun ecart avec le chiffre
> enregistre en section 4 avant que les reaudits soient rendus. Troisieme depassement consecutif,
> ecrit d'avance et non decouvert au comptage de cloture.

### `PAR-11`, clause neuve de `nadia-prof`, enregistree ici

**Le fait qui la produit** : `nadia-prof` a ecrit dans son arbitrage prealable de s30 que la marge
de rejeu de 1,0 (nommee au niveau du sprint, `PAR-9`) *"couvre exactement"* le cas de deux reaudits
a 0,5 chacun. **Le calcul etait juste, le dimensionnement etait faux** : `PAR-2` concentre les deux
lots de sciences sur une seule instance, si bien que les deux reaudits possibles tombaient tous deux
sur la moitie de cette marge (0,5), et non sur le tout (1,0). Elle l'a decouvert en consommant 4,50
sur son propre budget opposable de 4,0.

> **`PAR-11` - la marge de rejeu se dimensionne par instance sur son exposition reelle, jamais par
> part egale de la marge du sprint.** Une instance qui audite *n* lots de contenu neuf porte *n*
> reaudits possibles ; sa marge est de **0,5 par lot neuf qu'elle audite**, et la marge du sprint
> est la **somme** de ces marges instance par instance, non un total reparti a parts egales. `PAR-11`
> ne releve aucune borne : elle deplace la marge la ou le risque est.

**Ce que je fais de `PAR-11`** : je l'enregistre ici, a cote du cout qu'elle explique, et je note
qu'elle **corrige l'application de `PAR-9`** (`DEC-0079`) sans la remplacer - le budget opposable de
4,0 par instance reste la regle ; ce qui change est la facon de calculer la marge de rejeu **avant**
qu'un sprint a plusieurs lots par instance ne s'ouvre. `DEC-0079` porte une reference courte vers la
presente section. A appliquer des la composition de s31 si elle repartit plusieurs lots neufs sur
une meme instance.

**Ce que cette section ne fait pas** :
- Elle ne fait bouger `squad/programme/MATRICE-couverture.md` que sur les quatre lignes de sciences
  nommees ci-dessus - fait dans le meme geste, section "Sciences et technologie - CM1".
- Elle ne considere pas `CONS-1` comme levee : une entree reste ouverte, routee a s31.
- Elle ne rouvre aucun montant deja arbitre par l'utilisatrice en section 2 : le cout de 8,50/8,0
  est confirme, pas rediscute.
