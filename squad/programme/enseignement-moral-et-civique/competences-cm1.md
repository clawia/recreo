# Référentiel de compétences - Enseignement moral et civique - CM1 (cycle 3)

> Écrit par `nadia-prof` le 2026-09-03, sur arbitrage de l'utilisatrice du même jour (`DEC-0074`),
> au même grain et dans la même forme que
> `squad/programme/histoire-geographie/competences-cm1.md`, écrit le 2026-09-02. **Socle réaliste,
> non exhaustif et non officiel** : à compléter et à faire valider par l'utilisatrice au fil des
> sprints, en lien avec `squad/programme/MATRICE-couverture.md`. Ne remplace pas une lecture des
> programmes officiels de l'Éducation nationale.
>
> **Audit dégradé, et je le déclare** : je n'ai joué aucun incrément, parce qu'il n'en existe aucun
> sur cette matière. Ce document **ne prononce aucun Done pédagogique**, **n'écrit pas la matrice**
> (`camille-po`) et **ne priorise pas** (l'utilisatrice).
>
> L'EMC est un **enseignement à part entière** aux deux cycles : il porte ses propres attendus,
> même si l'arrêté des horaires l'adosse à l'histoire-géographie au cycle 3 (`nadia-prof`,
> arbitrage préalable de s27, section 2.2 bis - je compte par chapitre de programme, pas par
> horaire). Le programme s'organise en trois finalités : respecter autrui ; acquérir et partager
> les valeurs de la République ; construire une culture civique.
>
> **La mise en garde qui gouverne tout ce fichier, et elle est propre à cette matière** : onze
> lignes sur douze sont jouables par la mécanique existante, **et c'est précisément ce qui est
> dangereux**. Le `bandit-manchot` produit spontanément, en EMC, l'item dont la bonne réponse se
> devine par la seule politesse - *"que fais-tu si un camarade tombe ?"*. Un tel item ne mesure
> rien, il mesure la réponse attendue. `EMC-8` le refuse, et c'est la barre la plus importante de
> ce référentiel.

## Respecter autrui

- Identifier et nommer l'émotion d'une personne dans une situation donnée, et distinguer ce que l'on ressent de ce que l'on fait.
- Reconnaître ce qui porte atteinte à la personne d'autrui (moquerie, insulte, mise à l'écart, harcèlement) et savoir à qui s'adresser.
- Identifier ce qu'interdit le respect d'autrui dans ses différences (apparence, origine, convictions, situation de handicap).
- Connaître les règles de la vie collective à l'école et dire à quoi elles servent.

## Acquérir et partager les valeurs de la République

- Reconnaître les symboles de la République française et dire ce qu'ils représentent (drapeau, devise, Marianne, hymne, fête nationale).
- Comprendre le sens des principes de la République : liberté, égalité, fraternité, laïcité, refus des discriminations.
- Distinguer un droit d'un devoir, et connaître quelques droits et devoirs de l'élève et de l'enfant.
- Distinguer une règle d'une loi, et identifier ce que la loi autorise, interdit et protège.

## Construire une culture civique

- Identifier le rôle de quelques institutions proches (mairie, conseil municipal, école) et à qui s'adresser selon la situation.
- Comprendre le sens du vote et de la représentation (délégué de classe, élection, suffrage universel).
- Distinguer un fait d'une opinion, et vérifier l'origine d'une information avant de la partager.
- S'engager dans un projet collectif de la classe ou de l'école et y tenir son rôle.

---

## Jugement de faisabilité (`nadia-prof`, 2026-09-03)

**Les moyens du produit** : la mécanique de **choix** (`bandit-manchot`), la mécanique de
**saisie**, le **palier 1 visuel** (`ADR-0073`). **Aucun son, aucun geste de tracé, aucune
persistance** (`DEC-0028`, `DEC-0046`, `DEC-0066`). Le **palier 2** n'existe pas encore : ce qui
l'attend est `non couvert`, jamais `non couvrable en l'etat`.

`EMC-1` à `EMC-7`, posés le 2026-09-03 dans
`squad/programme/enseignement-moral-et-civique/competences-cp.md`, **s'appliquent ici à
l'identique**, et `EMC-8` et `EMC-9` s'y ajoutent pour le CM1. `ART-1` (aucune réponse portée par
une couleur perçue) s'applique à la ligne des symboles. `HIS-2` et `HIS-3` s'appliquent : cette
matière porte des faits institutionnels dont **je ne suis pas la source**, et une erreur y est un
motif de refus à elle seule.

La numérotation ci-dessous suit l'ordre de lecture de la liste de compétences.

| # | Compétence | Situation | Motif |
|---|---|---|---|
| 1 | Identifier une émotion, distinguer ressentir et agir | **`non couvert`** - jouable au choix, **sous `EMC-1` et `EMC-2`** | La situation est décrite, l'émotion s'infère. **Risque propre** : beaucoup de situations admettent deux émotions défendables - `HIS-2` transposé, et il mord ici plus fort qu'en histoire. La seconde moitié - *distinguer ce que l'on ressent de ce que l'on fait* - est la partie utile au CM1 et elle se pose très bien en trois propositions |
| 2 | Reconnaître une atteinte à autrui, savoir à qui s'adresser | **`non couvert`** - jouable au choix, **sous `EMC-3` et `EMC-9`** | Ligne importante, servable, **et la plus délicate du lot avec la 6**. Mal écrite, elle met l'enfant en position de victime nommée ou lui suggère de se débrouiller seul. `EMC-9` s'ajoute au CM1 : un enfant de neuf ans peut croire qu'en répondant, il a alerté quelqu'un |
| 3 | Ce qu'interdit le respect des différences | **`non couvert`** - jouable au choix, **sous `EMC-4`, `BIA-1` et `BIA-2`** | **Jouable seulement sous la forme de l'interdit**, jamais sous la forme de l'énumération des différences. Une mécanique à trois propositions qui décrirait des groupes de personnes fabriquerait un stéréotype en une phrase |
| 4 | Règles de la vie collective et leur raison d'être | **`non couvert`** - jouable au choix | La moitié *"à quoi elles servent"* est ce qui distingue cette ligne d'un règlement récité. Un paquet qui n'énoncerait que des règles ne sert pas la compétence |
| 5 | Symboles de la République | **`non couvert`** - jouable au choix textuel, **sous `ART-1`** | Connaissance exacte et vérifiable, terrain sûr. **La réponse ne se porte jamais par une couleur perçue** : on demande le nom, la devise, la date, la figure - jamais *"de quelle couleur est... ?"*, qui exclut un enfant daltonien et ne teste pas le symbole |
| 6 | Sens des principes de la République | **`non couvert`** - jouable au choix, **et c'est la ligne la plus dangereuse de la matière** | Les principes se définissent et se reconnaissent sur des exemples concrets : c'est le programme. **Mais `EMC-4`, `EMC-5` et `BIA-5` mordent tous les trois ici** : aucun item ne fait porter un jugement sur une conviction, aucun n'a deux réponses défendables, et la laïcité se définit comme **ce que l'école garantit** (chacun croit ou ne croit pas, l'école n'en parle pas), jamais comme un interdit visant un groupe. Un item faux sur cette ligne fait plus de dégâts qu'une absence |
| 7 | Distinguer droit et devoir | **`non couvert`** - jouable au choix | **Excellente forme d'item** : l'association et la distinction ont ici des distracteurs naturels et plausibles. `BIA-4` s'applique - aucun item n'interroge l'enfant sur sa propre situation |
| 8 | Distinguer règle et loi ; ce que la loi protège | **`non couvert`** - jouable au choix | Le mot *loi* est au programme et il est accessible au CM1 sur des exemples concrets. **Il n'est pas défini juridiquement dans l'énoncé** : une définition de droit en CM1 est illisible avant d'être fausse |
| 9 | Rôle des institutions proches | **`non couvert`** - jouable au choix, **sous `EMC-6`** | *"À qui s'adresse-t-on pour signaler un lampadaire cassé ?"* : connaissance vérifiable. **Aucun item ne suppose la commune de l'enfant** : le produit ne la connaît pas et ne peut pas la connaître (`DEC-0028`) |
| 10 | Sens du vote et de la représentation | **`non couvert`** - jouable au choix, **sur la connaissance seule** | **Point à ne pas lire comme une contradiction avec le CP, et je l'écris avant qu'on me le demande** : sur l'EMC de CP, j'ai classé *"participer à une décision collective par un vote"* en `non couvrable en l'etat`, irréversible, et j'ai refusé d'écrire à côté une ligne de connaissance qui l'aurait maquillée. **Ici, c'est le programme du cycle 3 lui-même qui porte la connaissance du vote et de la représentation** comme un attendu propre : l'écrire est fidèle, pas maquillé. **Mention exigée d'avance** : *"connaissance du vote et de la représentation seule ; participer à une décision collective reste non couvrable"* |
| 11 | Distinguer un fait d'une opinion, vérifier une information | **`non couvert`** - jouable au choix, **et c'est la meilleure ligne de la matière** | La forme est exactement celle de `bandit-manchot-comprehension-cm1`, deux fois validée : un énoncé court, trois propositions, une seule défendable. La compétence est réelle, utile, et parfaitement servie par le choix. Condition : les exemples portent sur des sujets **sans enjeu polémique** - la météo, un match, une rumeur de cour de récréation |
| 12 | S'engager dans un projet collectif et y tenir son rôle | **`non couvrable en l'etat`**, nature **irréversible** | **Engagement réel dans un collectif réel**, jugé par un adulte qui l'observe dans la durée (`ADR-0008`). Il n'y a pas d'autre enfant devant l'écran, et le produit ne conserve rien d'une session à l'autre (`DEC-0028`) - mais **le premier motif suffit et il est irréversible**. `EMC-8` interdit par ailleurs la contrefaçon évidente : *"acceptes-tu d'aider ton groupe ?"* n'a qu'une réponse socialement possible |

### Conditions opposables, posées d'avance (nouvelles, datées du 2026-09-03)

> **`EMC-8` - Aucun item ne demande à l'enfant de déclarer un comportement.** La forme *"que
> fais-tu si..."*, *"acceptes-tu de..."*, *"comment réagis-tu quand..."* est refusée. Les items
> portent sur **ce qui est permis, interdit, prévu par la règle ou par la loi**, ou sur ce qu'une
> personne décrite peut faire - jamais sur ce que l'enfant déclare qu'il ferait. **Motif** : la
> réponse socialement attendue se devine sans rien savoir, donc l'item ne teste rien - c'est
> `CON-1` transposé à la morale. Et il est **pire qu'une absence**, parce qu'il fait croire à une
> couverture. C'est la sœur d'`EPS-3`, posée le même jour pour la même raison.
>
> **`EMC-9` - Sur la ligne 2, aucun item ne laisse croire à l'enfant qu'il vient d'alerter
> quelqu'un.** Le produit ne recueille rien, ne transmet rien et ne conserve rien (`DEC-0028`). Un
> item dont le retour dirait *"c'est bien, tu as prévenu"* mentirait à un enfant sur un sujet où le
> mensonge est grave. Le retour dit ce qu'**il faut faire dans la vie**, au présent de vérité
> générale, jamais ce que l'enfant vient de faire.

### Mention exigée d'avance si une ligne bouge

Si des lignes de cette matière passent à `couvert-valide`, la matrice porte cette mention :
*"connaissances civiques et reconnaissance de situations seules ; aucun comportement, aucun
engagement et aucune participation à une décision collective ne sont servis par ce produit"*.
Onze lignes vertes sur douze diraient sinon que le produit fait l'éducation morale et civique
d'un enfant, ce qui serait faux et le resterait quelle que soit la qualité des paquets.

### Bilan chiffré - enseignement moral et civique CM1

| | Nombre |
|---|---|
| Lignes du référentiel | **12** |
| dont `non couvert` | **11** (lignes 1 à 11) |
| dont `non couvrable en l'etat` | **1** (ligne 12), de nature **irréversible** |
| **Entrent au dénominateur atteignable** | **11** |
| dont jouables **dès aujourd'hui** par une mécanique existante | **11** |
| dont en attente du **palier 2** | **0** |

**Onze lignes sur douze entrent au dénominateur : c'est, après les sciences, la matière la plus
servable des six.** Et c'est aussi celle où mon audit coûtera le plus cher par item : sur les
douze lignes, quatre - 2, 3, 6 et 11 - portent un risque que ni une CI ni un relecteur ne verra,
et une seule formulation maladroite y fait plus de dégâts qu'un paquet entier de calcul mental.

**Ce dont je reste la seule garde** : `EMC-1` à `EMC-9`, `BIA-1`, `BIA-2`, `BIA-4`, `BIA-5`, et
l'exactitude institutionnelle de chaque item - un droit, un devoir, une compétence de mairie, une
règle de vote se vérifient contre une source, et je n'en suis pas la source.
