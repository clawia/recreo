# Arbitrage préalable du 2026-10-02 - s47, les questions Q4, Q5 et Q6 de `theo-archi`

- **Auteur** : `nadia-prof`. **Date** : 2026-10-02. **Sprint** : s47.
- **Ce qui m'est posé** : Q4 (`EMC-CP-14`, `lea-dev`), Q5 (`LVE-CP-06`, `sofia-sm`) et Q6
  (`EMC-CM1-10`, `sofia-sm`), `squad/rapports/avis-faisabilite-s47-cinq-lots.md`, section 9, routées
  par `squad/sprints/s47-tables-ce1-decouverte-cp-et-emc/planning.md`, sections 5 et 8.
- **Ce document ne prononce aucun Done**, n'écrit ni backlog ni garde-fou, et ne crée aucun
  identifiant de condition. Les trois règles ci-dessous appliquent des conditions déjà écrites
  (`EMC-5`, `EMC-6`, `EMC-7`, `HIS-3`, le critère d'acceptation de `S47-LVE-CULTURE-CP`, le motif 1
  de l'arbitrage de s27, section 7.3). Elles ne relèvent aucune barre : aucun des trois paquets
  n'existe.
- **Je n'ai rien joué, et ce n'est pas un audit dégradé : il n'y a rien à jouer.** J'ai relu à leur
  source `EMC-5` à `EMC-9`, ma ligne 14 du jugement de faisabilité d'EMC CP, la section 7.3 de
  `ARBITRAGE-PREALABLE-s27-les-matieres-absentes-du-programme.md`, les items `LVE-CM1-07` de
  `cm1-anglais.yaml` et le paquet `cm1-emc-institutions.yaml` en entier.

---

## Q4 - `EMC-CP-14` : le 112 à côté du 15, du 17 et du 18

### Décision

**Le 112 n'apparaît pas dans le paquet `EMC-CP-14` de s47** : ni dans un énoncé, ni dans une
proposition, ni dans un indice.

### Motif

Le 112 est correct dans toute urgence. Il n'a donc aucune place sûre dans un item de choix :

- **en distracteur** d'un item dont la bonne réponse est 15, 17 ou 18, il est une réponse juste
  comptée fausse. Un enfant qui a appris le 112 apprend qu'il s'est trompé. C'est une erreur de
  contenu, motif de refus à lui seul ;
- **en bonne réponse à côté de 15, 17 ou 18**, l'item a deux réponses défendables (`EMC-5`, `EMC-7`) ;
- **en bonne réponse avec d'autres distracteurs**, il faut des numéros qui ne sont pas des numéros
  d'urgence. Un nombre inventé est un distracteur absurde, et les numéros réels voisins (114, 115,
  119) sont eux-mêmes des numéros d'aide ou d'urgence.

Aucune de ces formes n'est lisible et exacte au CP. L'élément *"numéro d'urgence"* de `FER-1` est
servi par le 15, le 17 et le 18 sans le 112. Ce que le paquet laisse de côté, c'est le 112 : ce
n'est pas une réserve, et une forme sûre proposée plus tard repassera devant moi.

### Règle pour `lea-dev`

1. **Un item à numéro a exactement trois propositions, `"15"`, `"17"` et `"18"`, nues** (pas
   `"le 18"`, voir le piège 1 de l'avis, section 6).
2. **L'énoncé nomme le service, jamais une situation.** *"Quel est le numéro des pompiers ?"* est
   recevable. *"Il y a le feu. Quel numéro faut-il appeler ?"* est refusé : la bonne réponse y est
   18 **et** 112, même si le 112 n'est pas proposé, et l'item enseigne qu'il n'y en a qu'une. C'est
   le piège de l'incendie de ma ligne 14, refusé sans discussion par `EMC-7`.
3. **Correspondances exactes** : 15 pour le SAMU (urgence médicale), 17 pour la police (police
   secours), 18 pour les pompiers. Le marqueur `# EMC-7:` de chaque item à numéro cite sa source
   (page "Numéros d'urgence" de service-public.fr).
4. **Une situation décrite ne se sert que sur l'élément *"prévenir un adulte"***, ou en demandant
   quel service appeler (*"les pompiers"*) sans numéro dans les propositions.
5. Les trois valeurs sont chacune bonne réponse d'au moins un item. L'équilibre des rangs reste au
   garde-fou de rang (piège 1 de l'avis).

---

## Q5 - `LVE-CP-06` : un nom de fête anglais en bonne réponse

### Correction d'une prémisse

La question suppose qu'au CM1 le nom de fête est *"entouré de texte français"*. **C'est inexact** :
`cm1-anglais.yaml` sert `"Thanksgiving"`, `"Halloween"`, `"Big Ben"` et `"Washington"` en bonne
réponse, nus, sous un énoncé français. Le patron du CM1 n'est donc pas un patron pour le CP, et il ne
doit pas être recopié.

### Décision

**Au CP, aucun mot écrit à l'anglaise n'apparaît dans un texte que l'enfant lit** : énoncé,
proposition ou indice. Cela vaut pour les noms de fêtes, de lieux, de monuments et d'objets.
*"Halloween"* et *"Thanksgiving"* sont refusés en bonne réponse comme en distracteur, y compris
*"Halloween"* qui figure dans les dictionnaires français.

### Motif

1. **Le critère de la story le dit déjà** : *"sans demander de lire ou de produire un mot
   d'anglais"*. Choisir une proposition, c'est la lire.
2. **Le motif 1 de mon refus de s27** (section 7.3) : un enfant de CP apprend des correspondances
   graphème-phonème stables. *Thanksgiving* (*th*, *-ing*) et *Halloween* (*ee*, *w*) se décodent
   faux en français. Le présenter, c'est demander à l'enfant d'inhiber la règle qu'on lui enseigne.
3. **Ma réserve du 2026-09-02** : un adulte qui lit la proposition à voix haute prononce de
   l'anglais, et le produit ne sait pas s'il le prononce bien.

Au CM1, ces deux derniers motifs ne valent pas : le décodage est acquis et la ligne `LVE-CM1-04`
fait lire de l'anglais. C'est pourquoi le CM1 n'est pas un précédent.

### Règle pour `sofia-sm`

1. **Test à appliquer à chaque item** : si l'enfant ne connaît aucun mot d'anglais et ne lit que le
   français, peut-il lire tout ce qui s'affiche ? Si non, l'item est refusé.
2. **Recevables** : les noms français de pays, de villes et de fleuves (*les États-Unis*, *le
   Royaume-Uni*, *l'Écosse*, *l'Australie*, *Londres*, *la Tamise*), et les descriptions en
   français.
3. **Une fête se sert par ce qu'on y fait, pas par son nom.** Exemple de forme : *"Aux États-Unis,
   une fête de novembre réunit la famille. Que mange-t-on souvent ?"*, bonne réponse
   *"de la dinde"*. Le marqueur reste `# element: fete`.
4. Un trait attribué à un pays n'est pas aussi vrai des pays proposés en distracteur (`EMC-5`
   appliqué ici par analogie : une fête d'automne avec déguisements existe dans plusieurs pays
   anglophones).

---

## Q6 - `EMC-CM1-10` contre `EMC-CM1-09`

### Décision

**La frontière passe par l'objet de la question, pas par l'institution nommée.**

- `EMC-CM1-09` (fermée) : **ce que fait une institution** et à qui s'adresser. Le conseil municipal
  y vote les décisions de la commune.
- `EMC-CM1-10` : **comment on choisit ceux qui représentent, et ce que représenter veut dire**.
  Qui vote, comment, pour combien de temps, ce que fait un délégué pour ceux qui l'ont élu.

Un item sur l'élection du conseil municipal par les électeurs de la commune est donc une ligne
`EMC-CM1-10`, sans redite. **Un item qui porte le rôle du conseil municipal en bonne réponse n'en
est pas une**, même s'il contient le mot *"vote"*.

### Motif

Les deux lignes nomment le même organe, mais `EMC-CM1-09` sert le vote **du** conseil, et
`EMC-CM1-10` le vote **pour** le conseil. Distinguer par l'objet évite qu'un item compté sur
`EMC-CM1-10` ne répète les items de rôle de `cm1-emc-institutions.yaml`.

### Règle pour `sofia-sm`

1. **Aucune bonne réponse ne décrit ce que décide le conseil municipal** (budget, projets, nom des
   rues). Aucune situation de `cm1-emc-institutions.yaml` n'est reprise (terrain de sport, nom de
   rue, lampadaire).
2. **Piège d'exactitude : le maire n'est pas élu par les habitants.** Il est élu par le conseil
   municipal, parmi ses membres (CGCT, article L2122-4). Tout item qui dit ou laisse entendre que
   les habitants élisent le maire est faux. Ce fait peut servir de bonne réponse
   (*"Qui élit le maire ?"*, *"le conseil municipal"*).
3. **Piège d'exactitude : "les habitants" ne votent pas tous.** Les électeurs sont les personnes
   majeures inscrites sur les listes, de nationalité française ou d'un pays de l'Union européenne
   pour les municipales. Écrire *"les électeurs de la commune"*, ou poser la question par le
   *comment* (*"par une élection"*), jamais *"les habitants"* comme corps électoral.
4. **Chaque fait institutionnel porte sa source** dans le marqueur `# HIS-3:` (code électoral, CGCT),
   comme dans `cm1-emc-institutions.yaml`.
5. **Le délégué de classe se sert dans une classe décrite à la troisième personne.** L'élection de
   délégués n'est pas obligatoire à l'école élémentaire. Un énoncé qui suppose que la classe de
   l'enfant en a viole `EMC-6`, et un énoncé qui lui demande pour qui il voterait viole `EMC-8`.

---

## Ce dont je reste la seule garde sur ces trois points

Aucun test ne vérifie : que l'énoncé d'un item à numéro nomme un service et non une situation (Q4,
règle 2), qu'aucun texte de `LVE-CP-06` ne contient un mot écrit à l'anglaise (Q5), et qu'aucune
bonne réponse de `EMC-CM1-10` ne décrit le rôle du conseil municipal (Q6, règle 1). Je les
vérifierai en jouant, item par item, au Done pédagogique.
