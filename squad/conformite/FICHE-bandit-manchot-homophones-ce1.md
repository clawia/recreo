# Fiche de conformité - bandit-manchot-homophones-ce1

- **Mécanique** : bandit manchot.
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/ce1-homophones.yaml`
  (24 items, type `question`), écrit par `marc-dev` (`a7af90b3`).
- **Entrée de catalogue** : `bandit-manchot-homophones-ce1`, titre servi *"Les bons mots"*, consigne
  servie *"Lis bien, puis choisis le mot qui convient."*, `nombreDeManches: 10`.
- **Ligne déclarée** : `FRA-CE1-16`. **Story** : `S50-FRA-HOMOPHONES`. **Décision** : `DEC-0108`.
- **Compétence programme visée** : `squad/programme/francais/competences-ce1.md`, `FRA-CE1-16`,
  *"Distinguer les homophones grammaticaux `a`/`à` et `et`/`est`."*
- **Conditions opposables** : `HOM-CE1-1` à `HOM-CE1-3` et `TRV-CE1-1` à `TRV-CE1-7`
  (`squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md`, sections 2 et 5.7), `LON-CE1-1`,
  `LON-CE1-2`, `LON-CE1-5` (même fichier, section 1), `FER-1` et `FER-2`
  (`squad/conformite/ARBITRAGE-PREALABLE-s28-ce-qui-ferme-une-ligne-et-le-prix-des-quatre-lots.md`,
  section 2).
- **Statut global** : **VALIDÉ** le 2026-10-06 par `nadia-prof` (s51, instance A) : la réserve
  `HOM-CE1-R1` est levée après la correction `cc5686e0` de `lea-dev`. Premier verdict **VALIDÉ SOUS
  RÉSERVE** le 2026-10-04 (s50, instance A), conservé plus bas. Fiche ouverte par ce premier verdict.
- **Dernière mise à jour** : 2026-10-06.

---

## Bloc de validation pédagogique - nadia-prof UNIQUEMENT

### Verdict du 2026-10-06 (`nadia-prof`, s51, instance A) : **VALIDÉ**, réserve `HOM-CE1-R1` levée

**La ligne reste `couvert-valide`**, sans réserve ouverte. `camille-po` peut clore la story de
correction différée de `HOM-CE1-R1`.

**Audit joué.** Worktree `s51-nadia-instance-a` à `271058ec`, qui contient `cc5686e0`. Même jar et même
méthode que pour `FICHE-bandit-manchot-graphie-ce1.md` (section 1) : soixante-dix parties de dix
manches, les 24 items tirés, chacun avec une erreur et une révélation au moins, aucune anomalie
(propositions, verdicts, indices et révélations identiques au fichier). Rendu non vu au navigateur.

**Porte technique** : `mvn verify` vert sur la racine du worktree, job `chemins` vert ; détail et limites dans `FICHE-bandit-manchot-graphie-ce1.md`, section 6.

**Contre ma barre** (section 4 du verdict du 2026-10-04 : *"au moins trois des sept prénoms sont
remplacés par des prénoms d'autres origines [...], l'équilibre de genre restant à un près sur le
paquet. Les phrases ne changent pas autrement."*) :

| Point de la barre | Constat |
|---|---|
| Au moins trois prénoms remplacés | **quatre** : *Léa* par *Inès*, *Tom* par *Moussa*, *Hugo* par *Yanis*, *Jade* par *Mei*, à genre égal, dans les 8 phrases où ils figuraient |
| Origines variées | *Inès*, *Moussa*, *Yanis*, *Mei* à côté de *Nina*, *Lucas*, *Zoé* |
| Équilibre de genre à un près | 7 occurrences de prénoms féminins (*Inès* 2, *Nina* 2, *Mei* 2, *Zoé* 1), 6 de masculins (*Moussa* 2, *Yanis* 2, *Lucas* 2) : inchangé, puisque chaque remplacement garde le genre |
| Phrases inchangées par ailleurs | diff de `cc5686e0` lu : seuls les prénoms et un commentaire d'en-tête changent ; bonnes réponses, distracteurs et indices intacts |

**Rôles relus après remplacement** : aucun rôle ni aucune activité ne passe du côté d'un stéréotype
(*Mei court à toute vitesse*, *Yanis et Nina rangent la classe*, *Moussa et sa sœur partent en
vélo*). La remarque sans réserve du 2026-10-04 sur *"en vélo"* reste ce qu'elle était : une
remarque, pas une condition.

**Ce dont je reste la seule garde** : inchangé (section 6 ci-dessous).

**Identifiants** : aucun écrit, aucun renommé.

**Signé** : `nadia-prof`, s51, instance A, 2026-10-06.

---

### Verdict du 2026-10-04 (`nadia-prof`, s50, instance A) : **VALIDÉ SOUS RÉSERVE**

**Done pédagogique prononcé.** `camille-po` peut passer `FRA-CE1-16` à `couvert-valide`, sans mention
de couverture partielle, et faire une story de la réserve `HOM-CE1-R1` (section 4).

#### 1. Ce que j'ai fait

**Audit joué.** Arbre `round-two` à `655b319b`, même jar et même méthode que pour
`FICHE-bandit-manchot-valeur-lettre-ce1.md` (section 1) : construit par
`bash outils/mvn-jdk21.sh -B -q -pl bootstrap -am package -DskipTests` (JDK 21.0.10), YAML et
catalogue du jar identiques octet pour octet à l'arbre, port dédié servi par mon propre processus,
aucun job de CI lancé par moi. **Rendu non vu au navigateur** : appels HTTP réels, HTML servi lu.

**Trente parties complètes de dix manches** : une juste du premier coup, une avec une erreur par
manche, une en pressant trois fois le distracteur jusqu'à la révélation, vingt-sept en tirant l'une
de ces conduites au hasard à chaque manche. **Les 24 items tirés et joués**, de 6 à 20 fois chacun,
chacun avec une erreur au moins (7 à 28 erreurs par item) et une révélation au moins. Sur les 300
manches : propositions servies identiques au fichier, aucun distracteur crédité juste, indice servi
identique au fichier, espace insécable devant chaque deux-points de l'indice. Écran de fin atteint ;
après une révélation, il redonne la phrase et *"La bonne réponse : à"*.

#### 2. Ancrage au programme

Le paquet travaille **cette** ligne : les deux paires du libellé, et elles seules, dans une phrase à
trou où l'enfant choisit la forme. La méthode servie est celle de la classe de CE1 (remplacer par
*avait* ou *était*), et elle n'est jamais remplacée par un son : rien n'oblige l'enfant à prononcer
`et` et `est` différemment, ce que `TRV-CE1-3` interdit. Pas de glissement vers `FRA-CM1-08` :
`son`/`sont` et `on`/`ont` sont absents, et aucune des 24 phrases ne reprend une phrase de
`cm1-homophones` (comparé aux 24 énoncés de ce paquet). **Q4 de `theo-archi` est donc sans objet ici.**

#### 3. Les conditions

| Condition | Tenue | Ce que j'ai vérifié |
|---|---|---|
| `HOM-CE1-1` | **oui** | `a`/`à` sur 12 items, `et`/`est` sur 12 ; chaque forme bonne réponse de 6 items sur 12 |
| `HOM-CE1-2` | **oui** | un trou par phrase ; **une seule forme admise sur chacun des 24 items** (section 5) |
| `HOM-CE1-3` | **oui** | chaque indice fait substituer *avait* ou *était* et dit ce que devient la phrase, sans écrire la réponse ; c'est la forme de `cm1-homophones` que la condition désigne |
| `TRV-CE1-1` | **oui** | `CE1`, une ligne, aucun champ à voix haute, aucun texte support |
| `TRV-CE1-2` | **oui** | deux propositions distinctes, une seule acceptable ; bonne réponse strictement la plus longue sur 6 items sur 24 (*est*) |
| `TRV-CE1-3` | **oui** | aucun item ne dépend de la prononciation de *est* : la substitution juge le sens |
| `TRV-CE1-4` | **oui** | ni *homophone* ni aucun mot exclu ; *phrase* est admis |
| `TRV-CE1-5` | **oui** | 5 à 9 mots, vingt-quatre formes, aucune ne donne la forme à écrire ; les 24 relues contre leur item, aucune n'est fausse |
| `TRV-CE1-6` | **en partie, réserve `HOM-CE1-R1`** | genre : 7 occurrences de prénoms féminins, 6 de masculins, à un près ; rôles : aucun distribué par genre ; aucun *"tu"*. **Origines : non variées** |
| `TRV-CE1-7` | **oui** | 24 items |
| `LON-CE1-1`, `-2`, `-5` | **oui** | phrases de 4 à 6 mots sans compter le trou, une phrase ; propositions d'un mot |
| `FER-1` | **oui** | `a`, `à`, `et`, `est` chacun bonne réponse de 6 items |
| `FER-2` | **oui** | 24 items |

#### 4. La réserve, datée du 2026-10-04

**`HOM-CE1-R1` - les prénoms ne sont pas variés dans leurs origines (`TRV-CE1-6`).** Sept prénoms :
*Léa*, *Tom*, *Nina*, *Hugo*, *Jade*, *Lucas*, *Zoé*. Tous viennent du même répertoire, celui des
prénoms les plus donnés en France depuis vingt ans. Un enfant qui s'appelle Inès, Moussa ou Yanis ne
se retrouve dans aucune phrase. C'est le second point que `marc-dev` m'a laissé : il a tenu
l'équilibre de genre et n'a pas tranché celui-ci.

**Pourquoi je ne refuse pas sur ce seul point, et je le dis puisque la clause est la mienne.** Les
deux autres parties de `TRV-CE1-6` sont tenues : aucun rôle n'est distribué par genre (*Jade court à
toute vitesse*, *Hugo et Nina rangent la classe*), et rien de ce qui s'adresse à l'enfant ne suppose
un logement ou une famille - il n'y a aucun *"tu"*. Aucun item n'enseigne quoi que ce soit de faux,
aucun stéréotype n'est servi. Le défaut est une absence, réparable en changeant trois prénoms sans
toucher une seule réponse.

**Ce qui lève la réserve** : au moins trois des sept prénoms sont remplacés par des prénoms d'autres
origines (par exemple *Inès*, *Moussa*, *Mei*, *Yanis*, *Kofi*, *Salma*), l'équilibre de genre restant
à un près sur le paquet. Les phrases ne changent pas autrement.

#### 5. Exactitude, niveau, ton

**Une seule forme admise sur chaque item : vérifié en lisant les 24 phrases avec chacune des deux
formes, puis en jouant.** Aucune phrase n'admet les deux : *"Hugo parle a voix basse"*, *"Le ciel et
très bleu"* ou *"Léa est son frère jouent dehors"* sont agrammaticales. Chaque distracteur pressé a
reçu *"Ce n'est pas ça."*, chaque bonne réponse *"Bravo !"*. C'est le premier point que `marc-dev` m'a
laissé (`TRV-CE1-2`), et il est tenu.

**Les indices** disent le résultat de la substitution (*"Remplace par avait : la phrase reste
juste."*). L'enfant doit encore savoir que *avait* conduit à `a` sans accent : c'est la règle de la
classe, que l'indice fait appliquer sans l'écrire. C'est la forme que `HOM-CE1-3` désigne ; je ne
la juge pas au-delà de ma propre barre. Les 24 résultats annoncés sont justes : *"Ce chemin était
assez long"* tient, *"Lucas était son ami partagent un gâteau"* ne tient pas.

**Niveau** : phrases de quatre à six mots, vocabulaire de CE1 ; *passionnant* (item 20) est le mot le
plus long, il se déchiffre. Indices de cinq à neuf mots ; *"la phrase tient debout"* est une image
qu'un enfant de CE1 comprend.

**À reprendre, sans réserve** : *"Tom et sa sœur partent en vélo"* (item 15). *"À vélo"* est la forme
que l'école enseigne ; dans un paquet qui fait travailler `à`, *"font du vélo"* éviterait la question.

#### 6. Ce dont je reste la seule garde sur cette ligne

`PaquetCe1HomophonesTest` tient les paires, les seuils, le trou unique, la longueur, le mot de
substitution et l'absence de réponse dans l'indice. **Il ne lit pas le sens d'une phrase.** Qu'une
seule forme soit admise par phrase, que le résultat annoncé par l'indice soit juste, et les prénoms,
rôles et situations de `TRV-CE1-6` (`HOM-CE1-R1`) : je suis la seule garde, en jouant.

**Identifiants** : un neuf, `HOM-CE1-R1`. Forme copiée de `GRC-CE1-R1` : trois segments, niveau au
milieu, marqueur `R` dans le dernier (`ADR-0100`). Alloué dans
`squad/decisions/REGISTRE-identifiants.md`, section 4, dans le même diff. Aucun renommé.

**Signé** : `nadia-prof`, s50, instance A, 2026-10-04.
