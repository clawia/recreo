# s57 - trois points residuels avant les premiers jets de `-04`, `-06`, `-10`

- **Auteure** : `nadia-prof`, professeure des ecoles, seule autorite sur le Done pedagogique.
- **Date** : 2026-10-27, debut 10:04:47 (UTC+01:00), lu par `date` ; fin en section 7.
- **Arbre** : branche `s57-p5-nadia-trois`, base `round-two` a `97c67bb2`, lue par `git rev-parse`.
- **Repond a** : les points residuels releves par `camille-po` en reportant
  `arbitrage-nadia-dernier-lot-ce1-qlm-04-a-12.md` dans les stories (`R1` a `R5`, numerotation de sa demande).
- **Type** : arbitrage avant premier jet. Aucun Done, aucun verdict de lot, aucun identifiant de condition neuf, aucune
  condition neuve ni reecrite. Les reperes `A1`... (section 6) sont locaux a ce fichier. Je ne modifie ni story, ni paquet,
  ni test : `camille-po` reporte la section 5, `theo-archi` la section 6.
- **Audit degrade, dit** : aucun des trois paquets n'existe. Je juge des regles.
- **Lu** : mes `arbitrage-nadia-premiers-jets-ce1-qlm-06-08.md` (1.6), `arbitrage-nadia-premiers-jets-ce1-qlm-04-05.md`
  (regles 6 et 7 de `-04`), `arbitrage-nadia-premiers-jets-ce1-qlm-10-12.md` (`R10-1` a `R10-4`),
  `arbitrage-nadia-contradictions-ce1-qlm-06-08.md`, `arbitrage-nadia-contradictions-ce1-qlm-10-12.md` (`R10-2`, `R10-4`),
  `arbitrage-nadia-dernier-lot-ce1-qlm-04-a-12.md` en entier ; `avis-theo-palier-5-gardes-ce1-qlm-06-08.md` (2.0, `g4`) ;
  `avis-theo-palier-5-ajustements-gardes-ce1-qlm-06-08.md` (1.1, `g11`) ; le motif de `T04-3e`
  (`avis-theo-palier-5-gardes-ce1-qlm-04-05-et-article-02.md`) ; `PaquetLu.mots`.
- **Comptes de mots** : lus sur une copie de `PaquetLu.mots` executee par `jshell` (JDK 21), pas a la main. Ce n'est pas le
  test : le garde-fou transverse reste la preuve.

## 0. Table des reponses

| Point | Reponse | En une ligne |
|---|---|---|
| `R1` (`-06`, bloquant) | **reformulation : la regle 2 de A porte la premisse** | la question de A peut etre precedee de *"Ces trois objets sont en métal."* ou *"Tous ces objets sont en métal."* ; la premisse est vraie pour les trois objets des propositions, bonne reponse et distracteurs, sinon refusee : `g11` dit juste |
| `R2` (`-06`) | **mon compte est confirme** | *papier d'aluminium* fait 2 mots par `PaquetLu.mots` ; *"fil de fer, papier d'aluminium, clou"* fait 6 et passe |
| `R3` (`-06`) | **le quart n'est pas tenu par B seul** | tout item de B compte, et au moins un item de A compte aussi (dernier lot, 4.1) |
| `R4` (`-04`) | **non : *lait* seul est refuse dans l'enonce de A** | *le lait* est un aliment, pas un nom de famille, mais *produits laitiers* est fait sur lui : l'enfant trouve la reponse en voyant les memes lettres. Meme refus pour *le sucre*. Mon texte du dernier lot les avait fait tomber, je le corrige |
| `R5` (`-10`) | **non : l'indice de A ne nomme aucun element de `R10-1`, propre ou partage** | un element partage nomme dans l'indice ecarte tout paysage propose qui ne le porte pas, ou ne dit rien ; la methode suffit |

---

## 1. `R1` : la forme de l'enonce de A avec premisse (`-06`, bloquant)

**Le constat est juste, et la faute est dans mon texte.** Le 2026-10-27 (contradictions `-06`/`-08`, 1.2), j'ai confirme les
enonces fermes *"avec ou sans l'une des deux premisses"*, et `theo-archi` a encode `FORME_A` ainsi des le 2026-10-26
(`PREMISSE` facultative, 2.0 de son avis). Mais la regle 2 de la story, tache A, n'a jamais recu la premisse : elle ne cite que
*"Lequel de ces objets l'aimant attire-t-il ?"*. Lue avec ma phrase *"Un enonce hors des formes de la regle 2 est refuse"*, elle
rend impossible l'item de A que ma regle 1 exige. La garde etait juste, la lettre de la story ne l'etait pas.

**Formes de A, fermees** [corrige le 2026-10-27, `nadia-prof`, ce fichier, section 1] :

- *"Lequel de ces objets l'aimant attire-t-il ?"* et *"Lequel de ces objets l'aimant n'attire-t-il pas ?"* ; *"de ces objets"*
  est facultatif ;
- chacune peut etre precedee d'une des deux premisses, et d'elles seules : *"Ces trois objets sont en métal."* ou *"Tous ces
  objets sont en métal."*

C'est exactement `FORME_A`. Je n'admets aucune forme ouverte : sur une ligne ou `AIM-CE1-2` interdit une phrase, une premisse
libre est la porte de la regle fausse (*"Ces objets sont tous en métal, l'aimant les attire."*).

**La premisse est vraie pour tous les objets des propositions, oui.** Elle affirme quelque chose des trois objets que l'enfant
voit, donc des trois propositions, bonne reponse comprise. Un seul objet sans metal, a n'importe quelle place, en fait une erreur
de contenu. `g11` refuse dans ce sens : c'est ma regle 1 (ajout du 2026-10-27), elle est juste et je ne demande rien de plus.

**Ce qui en suit pour le quart, sans regle neuve** : un item de A avec premisse est vrai et compte (`g4`) dans les deux
polarites, et seulement ainsi :

- *attire* : la bonne reponse est en fer, les deux distracteurs en aluminium ou en cuivre (*"Ces trois objets sont en métal.
  Lequel l'aimant attire-t-il ?"*, *le clou* ; *le papier d'aluminium*, *le fil de cuivre*, 9 mots d'enonce) ;
- *n'attire pas* : la bonne reponse est en aluminium ou en cuivre, les deux distracteurs en fer (*"Tous ces objets sont en
  métal. Lequel l'aimant n'attire-t-il pas ?"*, *le fil de cuivre* ; *le fil de fer*, *l'agrafe*).

La premisse n'est jamais obligatoire : un item de A sans premisse reste admis, il sert `FER-1` sans compter.

## 2. `R2` et `R3` : mes comptes (`-06`, information)

**`R2`, confirme.** `PaquetLu.mots` coupe sur les blancs ; *d'aluminium* n'en porte pas et fait un mot. Comptes lus sur la copie
executee :

| Texte | Mots |
|---|---|
| *papier d'aluminium* | 2 |
| *"fil de fer, papier d'aluminium, clou"* | 6, passe |
| *"fil de fer, fil de cuivre, clou"* | 7, refuse |
| *"fil de fer, clou, agrafe"* | 5 |
| *"fil de fer, épingle, papier d'aluminium"* | 6 |
| *"clou, épingle, fil de cuivre"* | 5 |

*Papier d'aluminium* est un objet de deux mots pour la regle de longueur, quoi qu'en dise l'oeil. C'est le compte du garde-fou
transverse qui decide, et `marc-dev` applique celui-la. Mes exemples de 6.3 (dernier lot) tiennent.

**`R3`, confirme.** Le quart n'est pas tenu par les items de B seuls. Ma regle 1, phrase ajoutee le 2026-10-27 (dernier lot, 4.1
et 6.3) : tout item de B compte, **et au moins un item de A compte aussi**. Avec 3 items de B, il faut donc au moins 4 items qui
comptent. Le tableau de l'annotation qui dit le contraire est a corriger par `camille-po`. C'est `R1` qui rend cet item de A
ecrivable.

## 3. `R4` : *lait* dans l'enonce de A (`-04`)

**Non, *lait* n'est pas admis : il est refuse.** *Le lait* est un aliment, et ce n'est pas un nom de famille. Mais le motif de
la regle 6 n'a jamais ete *"un nom de famille"* : c'est que l'enfant ne trouve pas la famille en reconnaissant des lettres.
*"Dans quelle famille range-t-on le lait ?"*, avec *produits laitiers* parmi les propositions : l'enfant voit *lait* dans
*laitiers* et repond sans rien savoir des familles. C'est le defaut **b**. Meme refus pour *le sucre* devant *produits sucrés*.

**La faute est dans mon texte du dernier lot (6.1).** La parenthese d'origine de la regle 6 nommait *lait* et *sucre* ; ma
phrase de remplacement ne retient que *"les mots des cinq noms de famille, avec leurs formes de genre et de nombre"*. *Lait*
n'est pas une forme de genre ou de nombre de *laitiers*, ni *sucre* de *sucrés* : a la lettre, ma phrase les admettait. Ce
n'etait pas voulu. Le motif de `T04-3e` les refuse deja (`laits?`, `sucres?`) : la garde est juste, rien a lui demander.

**Texte corrige** en 5.2. Il n'ajoute rien a la parenthese d'origine : il la remet.

## 4. `R5` : un element partage dans l'indice de A (`-10`)

**Correction du constat d'abord.** Dans l'exemple de la demande, *les champs* est donne comme partage entre la campagne et le
littoral. Ce n'est pas `R10-1` : *le champ* est un element **propre** de la campagne. Les elements partages sont *la forêt*, *la
rivière*, *la neige*, *le port*, *le village*, *la falaise*, *la ferme*, *la rue*.

**Regle : en tache A, l'indice ne nomme aucun element de `R10-1`, propre ou partage, a aucun article ni nombre.** Ce que fait un
element partage nomme dans l'indice, cas par cas :

- s'il n'appartient pas a tous les paysages proposes, il ecarte celui qui ne le porte pas (*la forêt* dans l'indice, *la ville*
  proposee : la ville sort). C'est la demi-reponse que ma regle de 6.5 refuse pour un element propre ;
- s'il appartient aux trois paysages proposes, il n'ecarte rien et repete l'enonce : des mots de plus a lire, rien a apprendre ;
- s'il n'est pas dans l'enonce, l'indice ajoute un element a la scene, ce qui change l'item.

Et *"il y a des pistes"* est refuse dans tous les cas : comme element propre de la bonne reponse (`R10-4`), d'un distracteur
(dernier lot, 6.5), ou d'un paysage non propose, ou il contredit l'enonce. Une phrase *"il y a ..."* dit la scene, ce n'est pas
une methode. La methode suffit : *"Cherche le mot qui ne va qu'avec un seul paysage."* (10 mots).

**Ce n'est pas un poteau deplace** : aucun item n'existe, et la regle elargit la mienne de 6.5, datee ici.

En tache B, rien ne change : la derogation de *littoral* (`R10-4`, contradictions `-10`/`-12`) reste la seule ou l'indice nomme
des elements.

## 5. Textes a reporter dans les stories, par `camille-po`

Marqueur : *[arbitre par `nadia-prof` le 2026-10-27, `arbitrage-nadia-trois-points-ce1-qlm-04-06-10.md`]*.

### 5.1 `S57-BR-QLM-CE1-06`, critere 10

> **Regle 2, tache A, premiere phrase, remplacee.** *"Lequel de ces objets l'aimant attire-t-il ?"* ou *"... n'attire-t-il pas
> ?"* devient : *"Lequel de ces objets l'aimant attire-t-il ?"* ou *"Lequel de ces objets l'aimant n'attire-t-il pas ?"*, *"de
> ces objets"* facultatif. La question peut etre precedee d'une premisse, *"Ces trois objets sont en métal."* ou *"Tous ces
> objets sont en métal."*, et d'aucune autre phrase ; la premisse n'est ecrite que si les trois objets des propositions sont en
> metal (regle 1). Exemples : *"Ces trois objets sont en métal. Lequel l'aimant attire-t-il ?"* (*le clou* ; *le papier
> d'aluminium*, *le fil de cuivre*) ; *"Tous ces objets sont en métal. Lequel l'aimant n'attire-t-il pas ?"* (*le fil de
> cuivre* ; *le fil de fer*, *l'agrafe*). Le reste de la tache A ne change pas.

Le tableau de l'annotation qui fait tenir le quart par les 3 items de B seuls est a corriger : au moins un item de A compte
aussi (regle 1, deja reportee).

### 5.2 `S57-BR-QLM-CE1-04`, critere 11

> **Regle 6, phrase du dernier lot, remplacee.** *"Les mots refuses sont ceux des cinq noms de famille, avec leurs formes de genre
> et de nombre (feculent, laitier, laitière, sucrée, poisson, œuf...)"* devient : Les mots refuses sont ceux des cinq noms de
> famille, avec leurs formes de genre et de nombre (*feculent*, *laitier*, *laitière*, *sucrée*, *poisson*, *œuf*...), et les deux
> noms d'aliments sur lesquels un nom de famille est fait : *le lait* (*produits laitiers*) et *le sucre* (*produits sucrés*). La
> suite de la phrase (*"La regle ne porte que sur les mots, jamais sur le sens..."*) ne change pas.

### 5.3 `S57-BR-QLM-CE1-10`, critere 9

> **`R10-4`, tache A, phrase du dernier lot, remplacee.** *"L'indice de A ne nomme aucun element propre d'un des trois paysages
> proposes"* devient : L'indice de A ne nomme aucun element de `R10-1`, propre ou partage, a aucun article ni nombre : un element
> propre d'un paysage propose l'ecarte ou le designe ; un element partage ecarte le paysage propose qui ne le porte pas, ou
> repete l'enonce. L'indice de A est une methode (*"Cherche le mot qui ne va qu'avec un seul paysage."*).

## 6. Ajustements demandes a `theo-archi`, une ligne par garde (forme a lui)

| Repere | Lot, garde | Ce que je veux |
|---|---|---|
| `A1` | `-06`, `FORME_A`, `g11`, `g4` | rien : elles disent deja ce que 5.1 ecrit |
| `A2` | `-04`, `T04-3e` | rien : `laits?` et `sucres?` y sont ; garder une sonde refusee *"Dans quelle famille range-t-on le lait ?"* |
| `A3` | `-10`, `G11` du dernier lot (`G10-4` A) | en A, refus d'un indice qui contient une forme de n'importe quel element de `R10-1`, propre ou partage, et non plus des seuls elements propres des paysages proposes ; sonde refusee *"Regarde la forêt."* quand *la ville* est proposee |

**Ce dont je reste la seule garde, en plus de ce que mes fichiers du 2026-10-27 listent** : qu'un indice de A de `-10` soit une
methode et non une description de la scene, au-dela des mots de `R10-1`. **Non couvert par un test.**

## 7. Cout, duree, verifications

**Cout : 0,00**, par la decision de l'utilisatrice du 2026-10-27 : les arbitrages avant premier jet ne sont pas chiffres.

**Duree**, lue par `date` : debut **10:04:47**, fin **10:14:39**, apres les verifications et avant le commit, soit
**9 min 52 s**, dont deux executions du garde-fou des identifiants.

**Verifications avant commit**, ce fichier present dans l'arbre, base `97c67bb2`. Il n'ecrit ni ne renomme aucun identifiant de
condition : `R1`, `A1`... sont des reperes de demande.

- `bash outils/mvn-jdk21.sh -pl bootstrap -am test -Dtest=ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest`, telle quelle :
  *"BUILD FAILURE"* sur `recreo-domain`, *"No tests matching pattern "ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest" were
  executed!"*. Le garde-fou n'a pas ete lance ; meme symptome que dans mes fichiers precedents.
- La meme commande avec `-Dsurefire.failIfNoSpecifiedTests=false` : *"Tests run: 10, Failures: 0, Errors: 0, Skipped: 0"*,
  *"BUILD SUCCESS"*. Perimetre : ce seul test, module `bootstrap` et ses dependances ; ni un `verify`, ni la porte technique.
- `.github/scripts/verifier-chemins.sh`, entree standard fermee : *"Aucun chemin de poste de travail ni nom de compte dans les
  fichiers verifies."*
- Aucun autre job de `.github/workflows/ci.yml` lance.

**Signe** : `nadia-prof`, s57, palier 5, 2026-10-27.
