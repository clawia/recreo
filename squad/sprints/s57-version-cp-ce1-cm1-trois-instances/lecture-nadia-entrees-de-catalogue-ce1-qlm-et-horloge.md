# s57 - lecture des titres et des consignes des neuf entrées de catalogue, avant leur fusion (`S57-QLM-CE1-ENTREES-CATALOGUE`, critère 2)

- **Auteure** : `nadia-prof`, professeure des écoles, seule autorité sur le Done pédagogique.
- **Date** : 2026-10-28, début 22:33:33 (UTC+01:00), lu par `date`.
- **Arbre** : branche `s57-p5-nadia-qc3`, base `round-two` à `278ee057`.
- **Commit lu** : `83bccb3e` (`[MARC]`, branche `s57-p5-marc-catalogue`, **non fusionné**), lu par `git show` sans checkout :
  `catalogue.yaml` (neuf entrées), `TexteAccentueExposeParLePortEntrantTest.java` (inventaire), et
  `rapport-marc-dev-palier-5-entrees-de-catalogue.md`.
- **Lu aussi** : la story `S57-QLM-CE1-ENTREES-CATALOGUE` (`docs/backlog/backlog.md`, critères 1 à 7 et paragraphe *Engagement*) ;
  `DEC-0120` A.287 ; les neuf paquets (`ce1-qlm-etapes-de-developpement`, `-ce-que-mangent-les-animaux`, `-besoins-de-la-plante`,
  `-familles-d-aliments`, `-eau-trois-etats`, `-aimant`, `-dangers-electricite`, `-paysages`, `ce1-lire-l-heure`), leurs énoncés
  un par un ; mes neuf fiches (`FICHE-ce1-qlm-01` à `-06`, `-08`, `-10`, `FICHE-ce1-lire-l-heure`), sections *comment j'ai joué*
  et *réserves* ; les titres existants du catalogue.
- **Type** : lecture de textes d'entrée (`QC3`). **Aucun Done nouveau** : les neuf Done existent dans leurs fiches. Je dis
  seulement que le titre, la consigne et le nombre de manches de chaque entrée sont lus. Aucun bloc de validation, aucune fiche,
  aucun paquet, aucun test, ni le catalogue, ni la matrice, ni le backlog ne sont touchés. Aucun identifiant de condition neuf.
- **Audit dégradé, dit** : je n'ai rien rejoué à l'écran pour cette lecture. Chaque titre est lu contre les énoncés du paquet
  et contre ce que j'ai joué pour son verdict ; aucune tuile n'a été vue sur la page de l'enfant (section 4).
- **Orthographe** : je ne lis ni ne juge l'orthographe, l'accentuation ni la ponctuation des titres et des consignes. La story
  la confie à `relecteur-pedagogique` (critère 4 ; `DEC-0002`, amendement du 2026-08-04). Les titres que j'écris ci-dessous
  sont mes mots ; leur contrôle orthographique reste le sien.

## 1. Les neuf entrées, telles qu'elles doivent être écrites

Le titre et la consigne de chaque ligne sont **ceux que l'entrée doit porter, mot pour mot**. Deux titres sont remplacés : `-01`
et `-06`. Les sept autres textes sont acceptés tels que `83bccb3e` les écrit.

| Ligne | Paquet | Titre à écrire | Consigne à écrire | Manches | Verdict |
|---|---|---|---|---|---|
| `QLM-CE1-01` | `ce1-qlm-etapes-de-developpement` | "Grandir chez les animaux et les plantes" | "Lis bien, puis choisis la bonne réponse." | 10 | lu ; titre remplacé, consigne acceptée telle quelle |
| `QLM-CE1-02` | `ce1-qlm-ce-que-mangent-les-animaux` | "Ce que mangent les animaux" | "Lis bien, puis choisis la bonne réponse." | 10 | lu ; accepté tel quel |
| `QLM-CE1-03` | `ce1-qlm-besoins-de-la-plante` | "Ce qu'il faut à une plante" | "Lis bien, puis choisis la bonne réponse." | 10 | lu ; accepté tel quel |
| `QLM-CE1-04` | `ce1-qlm-familles-d-aliments` | "Les familles d'aliments" | "Lis bien, puis choisis la bonne réponse." | 10 | lu ; accepté tel quel |
| `QLM-CE1-05` | `ce1-qlm-eau-trois-etats` | "Les trois états de l'eau" | "Lis bien, puis choisis la bonne réponse." | 10 | lu ; accepté tel quel |
| `QLM-CE1-06` | `ce1-qlm-aimant` | "Ce que l'aimant attire ou non" | "Lis bien, puis choisis la bonne réponse." | 10 | lu ; titre remplacé, consigne acceptée telle quelle |
| `QLM-CE1-08` | `ce1-qlm-dangers-electricite` | "Les dangers de l'électricité" | "Lis bien, puis choisis la bonne réponse." | 10 | lu ; accepté tel quel |
| `QLM-CE1-10` | `ce1-qlm-paysages` | "Les paysages" | "Lis bien, puis choisis la bonne réponse." | 10 | lu ; accepté tel quel |
| `MAT-CE1-11` | `ce1-lire-l-heure` | "L'heure sur l'horloge" | "Regarde les deux aiguilles, puis choisis la bonne heure." | 10 | lu ; accepté tel quel (mes textes, réserve *d*) |

Mécanique : `bandit-manchot` pour les neuf, comme dans `83bccb3e`. Les identifiants d'entrée de `83bccb3e` ne sont pas du texte
servi à l'enfant comme titre ; je ne les juge pas.

## 2. Les titres, un par un, contre ce que l'enfant jouera

1. **`QLM-CE1-01`, remplacé.** *"Les étapes pour grandir"* n'a pas de sujet et son *pour* dit un but : un enfant de CE1 le lit
   comme *"ce qu'il faut faire pour grandir"*, donc comme un jeu sur lui-même, ou comme un voisin de `-03` (ce qu'il faut à une
   plante pour grandir). Le paquet ne parle ni de l'enfant ni d'un besoin : ses 15 énoncés commencent tous par *"Dans la vie de"*
   un animal ou une plante (grenouille 5, papillon 4, plante 4, poule 2) et demandent l'étape d'avant, d'après, ou la suite dans
   l'ordre. J'ai écarté *"Les étapes de la vie"* : `-12` (`ce1-qlm-generations`), qui viendra sur la même page, sert la vie d'une
   personne (*"Dans la vie de Sara, quel moment vient juste après sa naissance ?"*), et les deux titres se confondraient. Mon
   titre, **"Grandir chez les animaux et les plantes"**, nomme qui grandit et exclut l'enfant ; *grandir* et *chez* sont des mots
   de CE1. 39 caractères, sous le plus long titre du catalogue (40), sans signe double, sans niveau.
2. **`QLM-CE1-02`, accepté.** Les 12 énoncés sont *"Que mange ..."* : le titre est la question du jeu. C'est le titre que
   j'ai joué pour le verdict (`FICHE-ce1-qlm-02`).
3. **`QLM-CE1-03`, accepté.** J'avais joué *"Les besoins de la plante"*, titre de ma main. *"Ce qu'il faut à une plante"* est
   meilleur pour un CE1 : *besoin* est abstrait, *il faut* est le tour parlé. Il couvre les 14 items : ce qui aide une plante à
   pousser, ce qui lui arrive quand cela manque (5 items), et la graine (4 items), qui est une plante à son début.
4. **`QLM-CE1-04`, accepté.** 8 items sur 12 rangent un aliment dans sa famille ; les 4 autres demandent le menu le plus varié,
   qui se juge aux familles. Le titre ne promet rien que le paquet ne fasse. C'est le titre que j'ai joué (`FICHE-ce1-qlm-04`).
5. **`QLM-CE1-05`, accepté.** Les 12 items demandent ce que devient l'eau (glace, eau, vapeur, buée, brouillard). Le titre nomme
   le contenu sans le confondre avec le jeu de CP *"L'eau solide ou liquide"*.
6. **`QLM-CE1-06`, remplacé. Réponse à la question de `marc-dev` : oui, un titre plus large.** 4 items sur 12 demandent
   *"Lequel l'aimant n'attire-t-il pas ?"*, et la compétence est des deux côtés (*"ceux qu'il attire et ceux qu'il n'attire
   pas"*). *"Ce que l'aimant attire"* annonce un seul sens : sur un item à négation, l'enfant qui lit le titre puis survole
   l'énoncé cherche l'objet attiré et se trompe. **"Ce que l'aimant attire ou non"** annonce les deux sens. *Ou non* est connu
   d'un CE1 (*oui ou non*). Le titre ne dit pas que l'aimant attire les métaux : la croyance fausse qu'`AIM-CE1-2` écarte n'y est
   pas. 29 caractères, sans signe double, sans niveau.
7. **`QLM-CE1-08`, accepté. Réponse à la question de `marc-dev` : non, pas de titre plus large.** 8 items sur 12 demandent
   *"Que doit faire ..."* ; chacun pose d'abord un danger (fil abîmé, prise cassée, mains mouillées, appareil près de l'évier) et
   le geste est la réponse à ce danger. Le titre nomme le sujet de toutes les manches, et rien dans le jeu ne le contredit, à la
   différence de `-06`. Les 12 items se passent à la maison ; *"à la maison"* allongerait le titre sans rien apprendre à
   l'enfant. C'est le titre que j'ai joué (`FICHE-ce1-qlm-08`), qui disait que l'entrée serait couverte si elle le portait.
8. **`QLM-CE1-10`, accepté. *"Les paysages"* est clair pour un CE1** : 8 énoncés sur 13 emploient le mot (*"Quel est ce
   paysage ?"*, *"Dans quel paysage est-on ?"*), les 5 autres nomment un paysage (montagne, littoral, campagne, ville). L'enfant
   lit le mot à chaque manche ; le titre ne lui en apprend pas un nouveau. Il n'y a pas d'image, et le titre n'en promet pas.
9. **`MAT-CE1-11`, accepté.** Titre et consigne sont les miens (réserve *d* de `FICHE-ce1-lire-l-heure`), et l'entrée de
   `83bccb3e` les porte mot pour mot, avec `bandit-manchot` et 10 manches. L'exigence de la réserve *d* est atteinte par l'entrée
   telle qu'elle est écrite ici ; la levée s'écrira dans la fiche, pas dans ce fichier.

## 3. La consigne commune des huit, jeu par jeu

*"Lis bien, puis choisis la bonne réponse."* convient aux huit : chaque item est un énoncé écrit, sans image, suivi de trois
propositions, et la seule chose que la consigne demande est de lire puis de choisir. Elle ne nomme aucune forme d'item, donc
elle ne ment sur aucun des items à négation (`-06`, `-10`) ni sur les menus de `-04`. Une phrase, sept mots.

- **Jouée pour le verdict** : `-01`, `-02`, `-03`, `-04`, `-05`, `-06`, `-08` (fiches, section du jeu ; `-05` la dit
  *"tenue"*). Pour ces sept, l'entrée porte la consigne et les 10 manches que j'ai jouées.
- **`-10`, non jouée** : j'ai joué une consigne de remplissage (`FICHE-ce1-qlm-10`). Je la lis ici contre les 13 énoncés : ce
  sont des questions écrites, *"Lis bien"* y est le bon geste, et c'est la règle de surface de ma réserve de `-10` (lire seulement
  le premier nom) qu'il combat. Acceptée.
- **`-06`, réserve 2 de sa fiche** : elle exigeait que l'entrée porte cette consigne et 10 manches, ou que son titre et toute
  autre consigne me soient montrés. L'entrée porte cette consigne et 10 manches, et son titre est celui de la section 1. La
  condition est atteinte ; la levée s'écrira dans la fiche. *"Lis bien"* sert les 4 items à négation de `-06`.

## 4. Réserves et non-couvert

**Réserves** : aucune.

Ce que cette lecture ne couvre pas :

1. **Le rendu des neuf tuiles** sur la page de questionner le monde CE1 et sur celle de mathématiques CE1 : ni vu, ni mesuré.
   Mes deux titres restent sous la longueur du plus long titre servi (39 et 29 caractères contre 40), ce qui n'est pas une mesure
   de rendu. La tenue d'une tuile sur une ligne est une propriété de `ines-design` et du gabarit.
2. **L'orthographe** des neuf titres et des deux consignes : `relecteur-pedagogique` (section d'en-tête).
3. **`QLM-CE1-12`** : hors périmètre jusqu'au relevé de `S57-QLM-CE1-12-ACCORD` (`DEC-0120` A.283 D3). Son titre, à venir, me
   sera montré ; il ne devra pas se confondre avec celui de `-01`.

**Je reste la seule garde**, aucun test ne les tient : qu'un titre ne promette pas ce que son paquet ne fait pas, et qu'il ne
se confonde pas avec un autre titre de la même page. `AucunTitreDeJeuNePorteSonNiveauTest` lit le niveau, et l'inventaire de
`TexteAccentueExposeParLePortEntrantTest` lit la présence d'une chaîne ; ni l'un ni l'autre ne lit le sens.

## 5. Ordre de fusion

**Ce fichier est fusionné dans `round-two` avant que le commit des entrées soit refait par-dessus.** Le critère 2 exige que le
commit de cet écrit soit ancêtre du commit de l'entrée (`git merge-base --is-ancestor` sort 0). `83bccb3e` ne l'est pas et ne
peut pas le devenir : `marc-dev` refait son commit après la fusion de ce fichier, en reprenant les entrées de `83bccb3e` avec
les textes de la section 1. Un titre changé l'est en deux endroits, mot pour mot : le `titre` de l'entrée dans `catalogue.yaml`
et la même chaîne dans la liste des titres de `TexteAccentueExposeParLePortEntrantTest` (`-01` et `-06`). Si l'entrée écrite
diffère de la section 1 sur un titre, une consigne ou un nombre de manches, ce point n'est pas lu, et il me revient avant la
fusion.

**Signé** : `nadia-prof`, s57, palier 5, 2026-10-28.
