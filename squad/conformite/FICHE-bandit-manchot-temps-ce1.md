# Fiche de conformité - bandit-manchot-temps-ce1

- **Mécanique** : bandit manchot.
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/ce1-temps.yaml`
  (24 items, type `question`), écrit par `lea-dev` (`24430ed3`).
- **Entrée de catalogue** : `bandit-manchot-temps-ce1`, titre servi *"Quand ça se passe ?"*, consigne
  servie *"Lis bien, puis choisis quand la phrase se passe."*, `nombreDeManches: 10`.
- **Ligne déclarée** : `FRA-CE1-20`. **Story** : `S51-FRA-TEMPS`. **Décision** : `DEC-0110`.
- **Compétence programme visée** : `squad/programme/francais/competences-ce1.md`, `FRA-CE1-20`,
  *"Situer une phrase dans le temps (passé, présent, futur) à partir de son verbe et des mots qui
  indiquent le moment (`hier`, `aujourd'hui`, `demain`)."*
- **Conditions opposables** : `TPS-CE1-1` à `TPS-CE1-3` et `TRV-CE1-1` à `TRV-CE1-7`
  (`squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md`, sections 2 et 5.11), `LON-CE1-1`,
  `LON-CE1-2`, `LON-CE1-5` (même fichier, section 1).
- **Statut global** : **VALIDÉ SOUS RÉSERVE** le 2026-10-06 par `nadia-prof` (s51, instance A). Une
  réserve, `TPS-CE1-R1`. Fiche ouverte par ce verdict : aucune fiche n'existait pour ce jeu.
- **Dernière mise à jour** : 2026-10-06.

---

## Bloc de validation pédagogique - nadia-prof UNIQUEMENT

### Verdict du 2026-10-06 (`nadia-prof`, s51, instance A) : **VALIDÉ SOUS RÉSERVE**

**Done pédagogique prononcé.** `camille-po` peut passer `FRA-CE1-20` à `couvert-valide`, sans mention
de couverture partielle (`DEC-0110`, section 10), et faire une story de la réserve `TPS-CE1-R1`
(section 4).

#### 1. Ce que j'ai fait

**Audit joué.** Worktree `s51-nadia-instance-a` à `271058ec`, qui contient `24430ed3`. Même jar et
même méthode que pour `FICHE-bandit-manchot-graphie-ce1.md` (section 1). **Rendu non vu au
navigateur** : appels HTTP réels, HTML servi lu.

**Porte technique** : `mvn verify` vert sur la racine du worktree, job `chemins` vert ; détail et limites dans `FICHE-bandit-manchot-graphie-ce1.md`, section 6.

**Lecture masquée d'abord** : les 24 phrases lues sans les propositions ni la bonne réponse ; j'ai
situé chacune moi-même avant d'ouvrir le fichier. Mes 24 réponses sont les 24 bonnes réponses du
fichier.

**Soixante-dix parties complètes de dix manches**, mêmes conduites que pour le paquet de graphie.
**Les 24 items tirés et joués**, chacun avec une erreur et une révélation au moins. Sur les 700
manches : propositions servies identiques au fichier, aucun distracteur crédité juste, indice servi
identique au fichier, réponse révélée juste. Position de la bonne réponse sur les 300 manches de la
seconde série : 98, 102 et 100 aux trois places. Ordre des items tiré : deux parties relevées en
entier, sans rapport avec l'ordre du fichier, aucun item servi deux fois dans une partie. Écran de
fin atteint ; après une révélation, il redonne la phrase et *"La bonne réponse : futur"*.

#### 2. Ancrage au programme

Le paquet travaille **cette** ligne : l'enfant situe une phrase, il ne conjugue rien
(`FRA-CE1-17` à `-19` restent intactes). Les paires de phrases qui ne diffèrent que par le verbe
(*Les enfants jouent* / *Les enfants joueront dans la cour*, *Le chat dort* / *dormira sur le lit*,
*Samir ouvre* / *Salma ouvrira la porte*) font exactement ce que la ligne demande : le verbe décide.
Les sept indices qui parlent du mot de temps (*"Ne te fie pas qu'au mot de temps : lis le verbe."*)
ne sont servis que sur des phrases qui en portent un.

#### 3. Les conditions

| Condition | Tenue | Ce que j'ai vérifié |
|---|---|---|
| `TPS-CE1-1` | **oui** | *passé*, *présent*, *futur* sur chaque item ; 8 items chacun |
| `TPS-CE1-2` | **oui** | 16 phrases sans mot de temps, 8 avec (*hier* 3, *aujourd'hui* 1, *demain* 3, *maintenant* 1), chacun cohérent avec le verbe |
| `TPS-CE1-3` | **oui** | passé composé avec *avoir* seul (8) ; futur simple seul (8), *ira* compris ; ni futur proche, ni imparfait |
| `TRV-CE1-1` | **oui** | `CE1`, une ligne, aucun champ à voix haute, aucun texte support |
| `TRV-CE1-2` | **oui** | trois propositions distinctes, une seule acceptable ; *présent*, la plus longue, est la bonne réponse de 8 items sur 24 |
| `TRV-CE1-3` | **sans objet** | aucun son |
| `TRV-CE1-4` | **oui** | *verbe*, *passé*, *présent*, *futur* : admis ; aucun mot exclu |
| `TRV-CE1-5` | **oui** | 6 à 11 mots, huit formes, aucune ne nomme un temps ; aucune n'est fausse |
| `TRV-CE1-6` | **en partie, réserve `TPS-CE1-R1`** | genre : 9 occurrences de prénoms féminins, 9 de masculins, six prénoms de chaque ; origines variées ; aucun *"tu"*. **Trois activités servies du côté du stéréotype** |
| `TRV-CE1-7` | **oui** | 24 items |
| `LON-CE1-1`, `-2`, `-5` | **oui** | phrases de 4 à 8 mots, une phrase ; propositions d'un mot |

#### 4. La réserve, datée du 2026-10-06

**`TPS-CE1-R1` - trois activités sont servies du côté du stéréotype (`TRV-CE1-6`).**

| Item | Phrase |
|---|---|
| 4 | *"Moussa a joué au ballon hier."* |
| 7 | *"Hier, Salma a lavé son pull."* |
| 9 et 11 | *"Demain, Mei chantera avec la classe."*, *"Aïcha chante dans la classe."* |

Le ballon est donné à un garçon, le linge à une fille, et le chant deux fois à des filles, jamais à
un garçon. Pris un par un, aucun n'est grave. Ensemble, ce sont les trois activités du paquet qui
portent un stéréotype connu, et toutes vont du même côté.

**Pourquoi je ne refuse pas.** Le paquet travaille la ligne sans une erreur : 24 phrases justes,
24 réponses justes, aucun indice faux. Le reste de `TRV-CE1-6` est tenu, et deux phrases vont contre
le stéréotype (*Lucas lit un livre*, *Hugo range sa trousse*). Le défaut se répare en changeant des
prénoms, sans toucher une seule réponse.

**Ce qui lève la réserve** : sur le paquet, le ballon, le linge et le chant sont chacun portés au
moins une fois par un personnage de l'autre genre que celui du stéréotype (une fille au ballon, un
garçon au linge, un garçon qui chante), ou par un sujet sans genre (*Les enfants*). L'équilibre de
genre reste à un près. Les phrases ne changent pas autrement.

#### 5. Exactitude, niveau, ton

**Une seule réponse acceptable sur chaque item** : vérifié en lisant chaque phrase avec chacune des
trois propositions. Les présents sans mot de temps (*Le vent souffle très fort*) ne peuvent pas se
lire au passé ni au futur. *ira* et *lira* sont des futurs irréguliers ; ils sont tous deux appuyés
par *demain*.

**Les indices** disent où regarder (*"Cherche le verbe et regarde sa fin."*). *"Demande-toi : quand
a lieu ce que fait le verbe ?"* est la plus abstraite des huit ; elle reste lisible au CE1.

**Niveau** : vocabulaire de la classe et de la maison, aucun mot rare.

#### 6. Ce dont je reste la seule garde sur cette ligne

`PaquetCe1TempsTest` tient les trois propositions, les quarts, la part des mots de temps, les temps
admis, la longueur et les prénoms par genre. **Il ne lit ni le sens d'une phrase ni l'activité
d'un personnage.** Qu'une seule réponse soit acceptable, qu'un mot de temps soit cohérent avec son
verbe, et la répartition des activités par genre (`TPS-CE1-R1`) : je suis la seule garde, en jouant.

**Identifiants** : un neuf, `TPS-CE1-R1`. Forme copiée de `HOM-CE1-R1` : trois segments, niveau au
milieu, marqueur `R` dans le dernier (`ADR-0100`). Alloué dans
`squad/decisions/REGISTRE-identifiants.md`, section 4, dans le même diff. Aucun renommé.

**Signé** : `nadia-prof`, s51, instance A, 2026-10-06.
