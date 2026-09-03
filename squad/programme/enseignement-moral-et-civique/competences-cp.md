# Référentiel de compétences - Enseignement moral et civique - CP (cycle 2)

> Écrit par `nadia-prof` le 2026-09-03, sur arbitrage de l'utilisatrice du même jour (`DEC-0074`),
> au même grain et dans la même forme que
> `squad/programme/questionner-le-monde/competences-cp.md`, écrit le 2026-09-02 - une compétence
> par ligne, un verbe en tête, bornée par sa parenthèse. **Socle réaliste, non exhaustif et non
> officiel** : à compléter et à faire valider par l'utilisatrice au fil des sprints, en lien avec
> `squad/programme/MATRICE-couverture.md`. Ne remplace pas une lecture des programmes officiels de
> l'Éducation nationale.
>
> **Audit dégradé, et je le déclare** : je n'ai joué aucun incrément, parce qu'il n'en existe aucun
> sur cette matière. Ce document **ne prononce aucun Done pédagogique**, **n'écrit pas la matrice**
> (`camille-po`) et **ne priorise pas** (l'utilisatrice).
>
> **Choix de structure, énoncé parce qu'il change la lecture des lignes.** L'EMC a été organisé
> successivement en quatre cultures (sensibilité, règle et droit, jugement, engagement) puis en
> trois finalités (respecter autrui, acquérir et partager les valeurs de la République, construire
> une culture civique). Les domaines ci-dessous reprennent les **trois finalités**, plus récentes.
> Les compétences sont les mêmes dans les deux découpages ; seul le regroupement change. **Si
> l'utilisatrice préfère l'autre structure, les lignes se redistribuent sans qu'aucune ne
> disparaisse ni ne s'ajoute** - le dénominateur ne bouge pas.
>
> **C'est la matière la plus jouable des quatre que j'écris aujourd'hui, et il faut dire pourquoi
> sans s'en réjouir trop vite** : une large part de l'EMC est constituée de **connaissances** - les
> règles, les symboles, les droits - qui se posent en trois propositions et se vérifient. Mais son
> cœur - le débat réglé, l'engagement, la conduite tenue envers autrui - ne se produit ni par un
> choix ni par une saisie, et il ne se mesure pas par un score.

## Respecter autrui

- Identifier et nommer les émotions d'autrui dans une situation simple (joie, tristesse, colère, peur).
- Distinguer ce qui est permis de ce qui est interdit dans les relations entre élèves (moquerie, insulte, violence).
- Reconnaître une situation de moquerie répétée ou d'exclusion et savoir à quel adulte en parler.
- Reconnaître que les personnes sont différentes et qu'aucune différence ne justifie de traiter quelqu'un moins bien.
- Employer les formules de politesse et de respect dans les échanges (saluer, remercier, demander, écouter sans couper).

## Acquérir et partager les valeurs de la République

- Connaître les règles de vie de la classe et de l'école et dire à quoi elles servent.
- Distinguer une règle qui vaut pour tous d'un simple souhait, et reconnaître qu'une loi s'impose à tous.
- Reconnaître et nommer les symboles de la République française (drapeau tricolore, Marianne, La Marseillaise, la fête nationale).
- Connaître la devise de la République et donner un exemple concret de liberté, d'égalité et de fraternité.
- Identifier les principaux droits de l'enfant (être protégé, être soigné, aller à l'école, jouer).

## Construire une culture civique

- Participer à une décision collective par un vote et accepter le résultat du vote.
- Prendre part à un échange réglé sur une question simple : donner son avis, écouter celui des autres, en changer si on est convaincu.
- Identifier quelques rôles et services de l'école et de la commune (directeur, mairie, pompiers, médecin).
- Connaître les gestes d'alerte les plus simples et savoir donner l'alerte (prévenir un adulte, numéro d'urgence).

---

## Jugement de faisabilité (`nadia-prof`, 2026-09-03)

**Les moyens du produit** : la mécanique de **choix** (`bandit-manchot`), la mécanique de **saisie**,
le **palier 1 visuel** (`ADR-0073`). **Aucun son, aucun geste de tracé, aucune persistance**
(`DEC-0028`, `DEC-0046`, `DEC-0066`). Le **palier 2** n'existe pas, il est constructible, et
`theo-archi` en écrit l'ADR en s28 : une ligne qui l'attend est `non couvert`, **jamais** `non
couvrable en l'etat`.

**Un avertissement qui vaut pour la matière entière et que je place avant le tableau.** Douze lignes
sur quatorze sont techniquement jouables. **C'est précisément ce qui m'inquiète**, exactement comme
en histoire-géographie le 2026-09-02 : une mécanique de choix appliquée à l'EMC produit
spontanément du **catéchisme civique** - des items où l'enfant coche la réponse gentille sans que
rien n'ait été travaillé. Un enfant qui coche *"je vais le dire à la maîtresse"* ne sait pas pour
autant reconnaître une situation d'exclusion. **Les conditions ci-dessous sont la barre que je pose
avant qu'un lot ne soit écrit**, pas après.

La numérotation ci-dessous suit l'ordre de lecture de la liste de compétences.

| # | Compétence | Situation | Motif |
|---|---|---|---|
| 1 | Identifier les émotions d'autrui | **`non couvert`** - jouable au choix, **sous `EMC-1` et `EMC-2`** | *"Léa a perdu son doudou. Que ressent-elle ?"* : la situation est décrite, l'émotion s'infère, et c'est bien ce que le programme demande. **Risque propre** : beaucoup de situations admettent deux émotions défendables - c'est `HIS-2` transposé, et il mord ici plus fort qu'en histoire |
| 2 | Distinguer permis et interdit entre élèves | **`non couvert`** - jouable au choix | Ce n'est pas une opinion : la moquerie et la violence sont interdites, et le programme demande que l'enfant le sache. Énoncés courts, réponse unique, exacte |
| 3 | Reconnaître une situation d'exclusion, en parler à un adulte | **`non couvert`** - jouable au choix, **sous `EMC-3`** | Ligne importante et servable, **et la plus délicate du lot** : mal écrite, elle met l'enfant en position de victime nommée ou lui suggère de se débrouiller seul |
| 4 | Reconnaître les différences sans hiérarchiser | **`non couvert`** - jouable au choix, **sous `EMC-4`, `BIA-1` et `BIA-2`** | **Jouable seulement sous la forme de l'interdit** (*"a-t-on le droit de refuser de jouer avec quelqu'un parce qu'il ne parle pas comme nous ?"*), **jamais sous la forme de l'énumération des différences**. Une mécanique à trois propositions qui décrirait des groupes de personnes fabriquerait un stéréotype en une phrase. C'est la ligne la plus dangereuse de la matière |
| 5 | Employer les formules de politesse | **`non couvert`** - jouable sur la seule moitié **connaissance** | *"Que dit-on quand on reçoit un cadeau ?"* : connaissance courte et exacte. **L'emploi effectif en situation n'est pas servi.** Mention exigée d'avance : *"connaissance des formules seule ; leur emploi en situation n'est pas servi"* |
| 6 | Connaître les règles de vie et dire à quoi elles servent | **`non couvert`** - jouable au choix | **La ligne la plus franchement jouable de la matière.** *"Pourquoi range-t-on le matériel après l'avoir utilisé ?"* : la règle et sa raison se posent toutes deux en trois propositions. Attention à ne pas se contenter d'items qui n'énoncent que la règle : la moitié *"à quoi elle sert"* est ce qui distingue cette ligne d'un règlement récité |
| 7 | Distinguer règle, souhait et loi | **`non couvert`** - jouable au choix, **sous réserve de lisibilité** | Le mot *loi* est au programme du cycle 2 et il est accessible s'il est employé sur des exemples concrets. **Il ne doit pas être défini dans l'énoncé** : une définition juridique en CP est illisible avant d'être fausse |
| 8 | Reconnaître les symboles de la République | **`non couvert`** - jouable au choix textuel, **sous `ART-1`** | Connaissance exacte et vérifiable. **La réponse ne se porte jamais par une couleur perçue** : *"de quelle couleur est le drapeau ?"* est refusé, il exclut un enfant daltonien et ne teste pas le symbole. On demande le **nom** : l'hymne, la devise, la figure, la date de la fête nationale |
| 9 | Connaître la devise et donner un exemple | **`non couvert`** - jouable au choix, **sous `EMC-5`** | La devise elle-même est une restitution exacte. **L'exemple** est la partie utile et la partie risquée : un exemple de fraternité qu'il faut choisir parmi trois demande que les deux autres soient nettement hors sujet, sinon l'item a deux réponses défendables |
| 10 | Identifier les droits de l'enfant | **`non couvert`** - jouable au choix | Connaissance exacte, terrain sûr, vocabulaire accessible. **`BIA-4` s'applique** : aucun item n'interroge l'enfant sur sa propre situation |
| 11 | Participer à une décision collective par un vote | **`non couvrable en l'etat`**, nature **irréversible** | **Un vote sans collectif n'est pas un vote.** Le produit peut faire cliquer un enfant seul sur une préférence ; ce n'est ni une décision collective, ni l'acceptation d'un résultat qui lui échappe, qui est le cœur de la compétence. Faire passer un clic pour un vote serait une contrefaçon (`CON-1`). La connaissance *"comment se passe un vote"* est une autre ligne, que je n'ai pas écrite pour ne pas maquiller celle-ci |
| 12 | Prendre part à un échange réglé | **`non couvrable en l'etat`**, nature **irréversible** | **Production libre argumentée, jugement humain (`ADR-0008`).** Même famille que *rédiger*. Un débat suppose un interlocuteur, une parole tenue et un avis qui peut changer : rien de cela ne se produit par un choix parmi trois, et un score n'y a aucun sens |
| 13 | Identifier des rôles et services de l'école et de la commune | **`non couvert`** - jouable au choix, **sous `EMC-6`** | *"À qui s'adresse-t-on pour une inscription à l'école ?"* : connaissance vérifiable. **Aucun item ne suppose la commune de l'enfant** : le produit ne la connaît pas et ne peut pas la connaître (`DEC-0028`) |
| 14 | Connaître les gestes d'alerte et donner l'alerte | **`non couvert`** - jouable au choix, **sous `EMC-7`** | Connaissance utile et exacte. **Piège d'exactitude que je pose d'avance** : *"quel numéro appelle-t-on en cas d'incendie ?"* admet **deux réponses défendables**, 18 et 112. Un item qui n'en couronnerait qu'une serait faux. C'est `HIS-2` appliqué à un contenu où l'erreur ne coûte pas un point mais un réflexe |

### Conditions opposables, posées d'avance (nouvelles, datées du 2026-09-03)

> **`EMC-1` - Aucun item n'interroge l'enfant sur ce que lui ressent, vit, croit ou fait chez lui.**
> Les situations sont celles d'un personnage nommé, extérieur à l'enfant. Motif : tous les enfants
> n'ont pas le même foyer ni la même histoire, et une question qui le présuppose exclut avant
> d'enseigner. Extension à cette matière de `BIA-4` (2026-09-02).
>
> **`EMC-2` - Un item sur une émotion ne retient que les situations à émotion unique et évidente.**
> Si deux des trois propositions sont défendables devant la situation décrite, l'item est refusé.
> Une émotion n'est pas une opinion à corriger.
>
> **`EMC-3` - Sur la ligne 3, la bonne réponse est toujours *en parler à un adulte*, jamais une
> solution que l'enfant appliquerait seul**, et aucun item ne met l'enfant lui-même en position de
> victime ou d'auteur. Aucun item ne détaille une violence. Motif : cette ligne touche des enfants
> qui vivent réellement la situation décrite ; un exercice ne doit ni les mettre en scène, ni leur
> faire porter la responsabilité de s'en sortir.
>
> **`EMC-4` - Sur la ligne 4, les items portent sur l'interdit de traiter quelqu'un moins bien,
> jamais sur la description des groupes de personnes.** Aucune proposition n'attribue un trait, un
> goût ou une capacité à une catégorie de personnes, même en distracteur. Motif : un distracteur
> stéréotypé est lu par l'enfant même quand il ne le choisit pas.
>
> **`EMC-5` - Aucun item n'a deux réponses défendables.** C'est `HIS-2` (2026-09-02) étendu à cette
> matière, et il y mord plus fort : les questions morales et civiques admettent naturellement
> plusieurs réponses justes, et une mécanique de choix en couronnerait une. Une question dont la
> réponse se discute n'entre pas dans un paquet à trois propositions.
>
> **`EMC-6` - Aucun item ne présuppose un lieu de vie, une commune, un type d'école ou une
> configuration familiale.** Le produit ne conserve rien et ne connaît rien de l'enfant
> (`DEC-0028`) ; toute donnée de contexte est dans l'énoncé ou n'existe pas.
>
> **`EMC-7` - Les items sur les numéros d'urgence sont exacts et sans ambiguïté.** Chaque numéro
> cité porte sa source dans le commentaire du paquet, comme `HIS-3` l'exige en histoire. Un item
> dont deux propositions sont toutes deux correctes est refusé, sans discussion.

### Ce dont je reste la seule garde sur cette matière

Dit explicitement pour que l'équipe ne croie pas couvert ce qui ne l'est pas :

| Propriété | Qui la tient | Automatisable ? |
|---|---|---|
| `EMC-2`, `EMC-5` - aucune situation à deux réponses défendables | **moi seule** | non |
| `EMC-3` - la posture des items sur l'exclusion | **moi seule** | non |
| `EMC-4` - aucun trait attribué à une catégorie de personnes, distracteurs compris | **moi seule** | non |
| `EMC-7` - exactitude des numéros d'urgence et des symboles | **moi**, contre source | non |
| `ART-1` étendu ici - aucune réponse portée par une couleur perçue | **moi**, mais une déclaration de contenu la rendrait mordable | **partiellement** |

### Mention exigée d'avance si une ligne bouge

Les lignes 5 et 6 sont des moitiés : si elles passent à `couvert-valide`, la matrice porte
*"connaissance de la règle et de la formule seule ; leur mise en œuvre en situation n'est pas
servie"*. **Aucune ligne d'EMC ne doit être présentée comme attestant une conduite** : ce produit
vérifie ce qu'un enfant sait, jamais ce qu'il fait.

### Bilan chiffré - enseignement moral et civique CP

| | Nombre |
|---|---|
| Lignes du référentiel | **14** |
| dont `non couvert` | **12** (lignes 1 à 10, 13, 14) |
| dont `non couvrable en l'etat` | **2** (lignes 11 et 12) |
| dont `non couvrable` de nature **réversible** | **0** |
| dont `non couvrable` de nature **irréversible** | **2** |
| **Entrent au dénominateur atteignable** | **12** |
| dont jouables **dès aujourd'hui** par une mécanique existante | **12** |
| dont en attente du **palier 2** | **0** |

**C'est de loin la matière la plus servable des quatre, et la seule dont l'essentiel entre au
dénominateur.** Elle ne demande ni image, ni son, ni palier neuf : douze lignes se jouent au choix
textuel avec ce qui existe. **Ce qu'elle demande, c'est mon temps** - sept conditions opposables,
aucune mordable par un automate, sur une matière où une formulation maladroite ne produit pas une
erreur de calcul mais un stéréotype ou une consigne dangereuse. **Un paquet d'EMC ne se relit pas
comme un paquet d'additions**, et je le facturerai en conséquence le jour où il sera proposé.
