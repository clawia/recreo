# Fiche de conformité - bandit-manchot-roles-eps-cm1

- **Mécanique** : bandit manchot.
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/cm1-eps-roles.yaml`
  (12 items, type `question`), écrit par `lea-dev` (commit `3b61f781`, fusion `12fd0ae4`), item 3
  reformulé par le `relecteur-pedagogique` (commit `76809af7`, fusion `9fcae7dc`).
- **Entrée de catalogue** : `bandit-manchot-roles-eps-cm1`, titre servi *"Arbitre, observateur,
  marqueur"*, consigne servie *"Lis bien, puis dis ce que décide l'arbitre, ce que relève
  l'observateur, ou le score que donne le marqueur."*, `nombreDeManches: 10`.
- **Ligne déclarée** : `EPS-CM1-09`. **Story** : `S45-EPS-ROLES` (`docs/backlog/backlog.md`, section
  "Sprint 45").
- **Conditions opposables** : `EPS-1` à `EPS-3`
  (`squad/programme/education-physique-et-sportive/competences-cm1.md`), `EPS-10`
  (`squad/conformite/ARBITRAGE-PREALABLE-s45-cinq-lots.md`, section 4), `FER-1` à `FER-3`.
- **Statut global** : **VALIDE SOUS RÉSERVE**, prononcé le 2026-10-01 par `nadia-prof` (s45,
  instance A). Fiche ouverte par ce verdict : aucune fiche n'existait pour ce jeu.
- **Dernière mise à jour** : 2026-10-01.

---

## Bloc de validation pédagogique

### Verdict du 2026-10-01 (`nadia-prof`, s45, instance A) : **VALIDE SOUS RÉSERVE**

**Done pédagogique prononcé.** `camille-po` peut passer `EPS-CM1-09` à `couvert-valide`, **avec la
mention de la section 5, qui est une condition de ce Done**. Les réserves `EPS-CM1-R5` et
`EPS-CM1-R6` ne bloquent pas le mouvement de ligne.

#### 1. Ce que j'ai fait

**Audit complet, pas dégradé.** Jar construit sur `round-two` à `9fcae7dc`, qui contient la
reformulation du `relecteur-pedagogique`, dans un worktree dédié
(`bash outils/mvn-jdk21.sh -B clean package -DskipTests`), lancé sur le JDK 21 de la toolchain. **Six
parties complètes** de dix manches : un tiers des manches juste, un tiers après une erreur, un tiers
après trois erreurs. **Les douze items ont été tirés et joués**, propositions conformes au paquet.
Retour après erreur, révélation et écran de fin conformes.

#### 2. Ancrage au programme

`EPS-CM1-09` : *"Tenir les rôles d'arbitre, d'observateur ou de marqueur dans un jeu, et en appliquer
les règles."* **C'est bien la moitié connaissance de cette compétence** : chaque item décrit une
action de jeu, rappelle la règle et demande ce que le rôle en fait. Ce n'est pas la tâche B du CP :
aucune bonne réponse ne dit ce que devient un joueur.

#### 3. Conditions, vérifiées une par une

| Condition | Tenue | Ce que j'ai vérifié |
|---|---|---|
| `EPS-1` | **oui** | Les douze énoncés rappellent la règle avant la question, lus à l'écran. La remise en jeu à l'équipe qui n'a pas touché le ballon en dernier, le penalty, le coup franc, le service au volley et les barèmes de marque sont rappelés en toutes lettres |
| `EPS-2` | **oui** | Les quatre enfants nommés (Léa, Malo, Hugo, Aya) le sont seuls dans leur action. Aucune comparaison de corps ni de performance. Les scores opposent des équipes |
| `EPS-3` | **oui** | Aucune intention (*exprès*, *tricher*), aucune conduite morale en bonne réponse. L'arbitre décide sur un fait décrit |
| `EPS-10`, exigence 1 | **oui** | Aucun des six jeux du CP, cherché sur tous les textes. Handball, football, basket-ball, volley-ball, relais par équipes : des jeux à deux équipes qui comptent des points ou des buts |
| `EPS-10`, exigence 2 | **oui** | **La bonne réponse est l'acte du rôle sur les douze items.** Arbitre (1 à 4) : l'équipe qui reçoit la remise en jeu, le penalty, le coup franc, le service. Observateur (5 à 8) : ce qu'il coche ou note selon le critère de l'énoncé, jamais une appréciation. Marqueur (9 à 12) : le score après une seule action. Les distracteurs du marqueur sont des erreurs de règle (point à l'autre équipe, point refusé ou compté à tort, mauvais barème), jamais de calcul : les additions sont de 1, 2 ou 3 |
| `EPS-10`, exigence 3 | **oui** | Quatre items par rôle |
| `EPS-10`, exigence 4 (`CYC-1`) | **oui** | Calculé sur les deux paquets : aucune bonne réponse du CP n'est proposition ici, et aucune proposition d'ici n'est proposition du CP |
| `FER-1` | **oui** | Les trois rôles sont portés par l'énoncé, leur acte est en bonne réponse, comme je l'avais écrit en Q4. Aucune bonne réponse n'est un nom de rôle |
| `FER-2` | **oui** | 12 items |
| `FER-3` | **oui** | Trois tâches, quatre items chacune |

**Exactitude des règles de sport**, ma garde : remise en jeu, penalty pour une main dans la surface,
coup franc à l'équipe qui a subi la faute, service à l'équipe qui vient de marquer, ballon sur la ligne
toujours en jeu, trois appuis au handball, panier à trois points, point à chaque échange au volley :
**exacts**. Deux items décrivent pourtant leur sport autrement qu'on le pratique : réserve
`EPS-CM1-R5`.

#### 4. Niveau réel

- **Lisibilité** : énoncés de 30 à 50 mots en trois phrases, règle, action, question. C'est long pour
  un CM1, et c'est le prix d'`EPS-1` : la règle doit être dans l'énoncé. La structure est la même sur
  les douze items, l'enfant la reconnaît dès le deuxième. Aucune barre de longueur ne vaut au CM1
  (`ADR-0089`, section D) ; je ne la crée pas ici.
- **Vocabulaire** : *surface de but*, *coup franc*, *penalty*, *témoin*, *échange* sont les mots de ces
  jeux à l'école.
- **Traitement de l'erreur** : chaque indice rappelle la règle qui décide, sans nommer la bonne
  réponse, sauf l'item 2 (*"la sanction profite toujours à l'autre équipe"*) qui la désigne presque.
  Je ne le compte pas en réserve : l'énoncé disait déjà *"l'équipe adverse"*.

#### 5. Mention de couverture, condition de ce Done

La matrice porte, sur la ligne `EPS-CM1-09`, cette mention **mot pour mot**, celle de mon
référentiel :

> *"connaissance et application de la règle seules ; la tenue du rôle en situation reste non
> couverte"*

#### 6. Les réserves

> **`EPS-CM1-R5` - Items 5 et 6 : le sport nommé ne se joue pas comme l'item le décrit.**
> Item 5 : *"Au volley-ball, l'observateur coche une case à chaque fois qu'une passe est
> attrapée..."*. Au volley-ball, on ne tient pas le ballon : le *volley attrapé* est une étape
> d'apprentissage, pas le jeu, et l'énoncé ne le dit pas. C'est mon exemple de Q4, qui ne nommait pas
> de sport ; il a été placé dans le seul sport où il est faux. Item 6 : *"Au basket-ball,
> l'observateur note une croix chaque fois qu'un joueur avance de plus de trois pas..."*. Trois pas
> sans dribble, c'est la règle du handball (item 8) ; au basket-ball enseigné à l'école, c'est le
> marcher dès le troisième pas.
>
> **Pourquoi ce n'est pas un refus** : aucune réponse attendue n'est fausse. Le critère de
> l'observateur est posé par l'énoncé, comme `EPS-10` le veut, et l'action décrite (rebond au sol,
> quatre pas) donne la même réponse sous la règle réelle. Mais un enfant de CM1 qui joue au basket
> lit que trois pas sont permis, et un enfant qui joue au volley lit qu'on attrape le ballon.
>
> **Correction à faire** : item 5, un sport où l'on attrape le ballon (handball, balle au capitaine)
> à la place du volley-ball ; item 6, *"plus de deux pas"*, avec une action qui reste sans ambiguïté
> (par exemple trois pas).

> **`EPS-CM1-R6` - Items 1, 4 et 8 : un distracteur que personne ne choisit.** *"le jeu reprend par
> un tirage au sort"* (1), *"le service se tire au sort à chaque point"* (4), *"une faute de pas et
> une touche"* (8, la touche n'a rien à voir avec l'action décrite). Aucun n'est une règle de ces
> jeux, aucun n'est une erreur qu'un enfant ferait : l'item se joue entre deux boutons, ce que
> `BIN-1` veut éviter.
>
> **Correction à faire** : un distracteur qui soit une erreur de règle, sur le modèle de mon exemple
> de Q4 (*"l'équipe rouge, au centre du terrain"* : la bonne équipe, au mauvais endroit), ou sur le
> modèle de l'item 7 (*"une touche, et le jeu s'arrête"*), qui est plausible.
>
> **Pourquoi elle n'empêche pas `couvert-valide`** : trois items sur douze, et sur chacun les deux
> autres propositions restent l'erreur de règle attendue (la mauvaise équipe, la faute comptée à
> tort).

Routage : les deux réserves à `sofia-sm`, pour `lea-dev`, autrice du paquet, et pour que
`camille-po` en fasse des stories.

#### 7. Ce dont je reste la seule garde

Le marqueur `# EPS-1: rappelee` et les noms de jeux se lisent sur les chaînes. **Aucun automate ne
vérifie** : que la bonne réponse soit l'acte du rôle et non le statut d'un joueur (`EPS-10`,
exigence 2), que l'observateur relève sans juger, que la difficulté du marqueur soit la règle et non
le calcul, qu'un distracteur soit plausible (`EPS-CM1-R6`), ni l'exactitude de chaque règle de sport
(`EPS-CM1-R5`). Ces propriétés n'ont été vérifiées qu'ici.

---

**Signé** : `nadia-prof`, 2026-10-01.
