# Fiche de conformité - ce1-lire-l-heure

- **Mécanique** : `bandit-manchot`, `type: question`, sans texte support, **un dessin de cadran par item** (`.image-item`, 176 px) ;
  aucune modification de la mécanique, aucun cadran généré par le serveur (`arbitrage-nadia-palier-5-lp7-d5.md` 3.5).
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/ce1-lire-l-heure.yaml` (12 items, lus par
  `grep -vE '^\s*#' ... | grep -c "bonneReponse:"`) et douze dessins `horloge-a.svg` à `horloge-l.svg` sous
  `adapters/adapter-web/src/main/resources/static/illustrations/`. Écrits par `lea-dev` (instance B, premier jet).
- **Entrée de catalogue** : aucune. `QC3` : un paquet n'entre au catalogue qu'après un Done pédagogique ou un refus levé ;
  `grep -c "paquet: ce1-lire-l-heure" adapters/adapter-content/src/main/resources/catalogue.yaml` rend 0 le 2026-10-28.
- **Ligne déclarée** : `MAT-CE1-11`, moitié *lire l'heure sur une horloge* (la moitié *calendrier* est `ce1-calendrier-dates`,
  `FICHE-bandit-manchot-calendrier-dates-ce1.md`). **Story** : `S57-BR-MAT-CE1-11-HORLOGE` (`docs/backlog/backlog.md`, annotation du
  2026-10-28, treizième). **Décision** : `DEC-0120` A.263, A.265, A.267, A.280, A.282 à A.285.
- **Compétence programme visée** : `squad/programme/mathematiques/competences-ce1.md`, `MAT-CE1-11`, *"Lire l'heure sur une horloge
  (heures, demi-heures, quarts d'heure) et se repérer dans le calendrier (mois, date)."*
- **Conditions opposables** : celles de la story `S57-BR-MAT-CE1-11-HORLOGE`, qui reprend les mots de `nadia-prof` (3.5 de
  `arbitrage-nadia-palier-5-lp7-d5.md` et sa lecture du premier jet), **non recopiées ici** : la story fait foi. Plafond de la
  ligne : `couvert-valide`.
- **Statut global** : **premier jet lu, correction faite, seconde lecture faite (exigences tenues, aucune troisième lecture exigée), en attente du `verify` complet et de l'audit à l'écran**. Fiche ouverte par `camille-po` le
  2026-10-28. Aucun verdict. La ligne `MAT-CE1-11` reste `en cours` dans la matrice, qui ne bouge qu'au verdict de `nadia-prof`, à
  l'entrée de catalogue fusionnée et à la CI complète.
- **Poids** : le relèvement du cliquet des illustrations de 69 203 à 96 873 octets est accordé par l'utilisatrice
  (*"Q1 O1 on l'accorde"*, 2026-10-28, `DEC-0120` A.284) ; douze cadrans, 27 670 octets.
- **Dernière mise à jour** : 2026-10-28 (`camille-po`, `DEC-0120` A.285).

---

## Compétence visée

| Matière | Niveau | Compétence | Référence au référentiel |
|---|---|---|---|
| mathématiques | CE1 (cycle 2) | Lire l'heure sur une horloge (heures, demi-heures, quarts d'heure) et se repérer dans le calendrier (mois, date). Cette fiche porte la première moitié. | `squad/programme/mathematiques/competences-ce1.md`, `MAT-CE1-11` |

## Critères attendus - camille-po

Les critères d'acceptation de cette fiche sont ceux de la story `S57-BR-MAT-CE1-11-HORLOGE` (`docs/backlog/backlog.md`, titre
*"Écrire et brancher le paquet de la lecture de l'heure"*). Je ne les recopie pas : une copie dérive de la source. Je n'écris aucune
mesure ici.

- [ ] Critères de la story `S57-BR-MAT-CE1-11-HORLOGE`, numérotés de 1 à 12 dans le backlog.
- [ ] Conditions de `nadia-prof` sur l'horloge : 3.5 de `arbitrage-nadia-palier-5-lp7-d5.md` (`HOR-1` à `HOR-3`, `HOR-5`
  transposée, `HOR-6` transposée, `CON-5`) et exigences 1 et 2 de sa lecture du premier jet **telles que son arbitrage `arbitrage-nadia-horloge-strategie-de-paire-et-pli.md` les remplace** (quatre stratégies `S1` à `S4`, chacune au plus 6 items sur 12 ; pli à 744 x 551), voir le critère 5 de la story. **[corrige le 2026-10-28 par A.285, texte d'origine conserve]** Texte d'origine de cette fin de ligne : *"exigences 1 et 2 de sa lecture du premier jet."*
- [ ] Texte servi à l'enfant intégralement accentué, orthographe et ponctuation irréprochables (`DEC-0002`).

## Parcours du lot, faits lus au dépôt

Source : `git log` de `round-two` ; chaque SHA est un commit de `round-two` (existence vérifiée par `git cat-file -e`). Les fichiers de
`lea-dev` et de `nadia-prof` sont dans `squad/sprints/s57-version-cp-ce1-cm1-trois-instances/` pour les rapports, sous
`adapters/` pour le paquet et les dessins.

| Geste | Auteur | Commit | Fichier |
|---|---|---|---|
| premier jet (paquet, 12 dessins, garde M, test de paquet, cliquets) | `lea-dev` | `a5d5026b` | `ce1-lire-l-heure.yaml`, `horloge-a.svg` à `horloge-l.svg` |
| rapport du premier jet | `lea-dev` | `d242fe5b` | `rapport-lea-dev-palier-5-horloge-mat-ce1-11.md` |
| lecture du premier jet | `nadia-prof` | `844c524f` | `lecture-nadia-premier-jet-horloge-mat-ce1-11.md` |
| arbitrage de la stratégie de paire et du pli | `nadia-prof` | `a3a72a65` | `arbitrage-nadia-horloge-strategie-de-paire-et-pli.md` |
| correction (exigence 1, garde de paire) | `lea-dev` | `f33d03b1`, rapport `2022d8fc` | `ce1-lire-l-heure.yaml` |
| glyphes et relevé (exigence 2) | `ines-design` | `9da040eb` | `avis-ines-palier-5-horloge-mat-ce1-11.md` |
| seconde lecture | `nadia-prof` | `af73a862` | `relecture-nadia-seconde-lecture-horloge-mat-ce1-11.md` |

Cette fiche n'en reprend aucun verdict : la lecture du premier jet est un geste avant `verify`, pas le Done pédagogique.

## État de la porte technique

**Ce qu'un fichier du dépôt prouve, et rien de plus.**

- **Aucun `mvn verify` complet n'a été lancé pour ce lot** (`lea-dev`, rapport, section 8 : *"Je ne déclare pas la porte technique"*).
- **Lancé par `camille-po` le 2026-10-28, mesure partielle** : `bash outils/mvn-jdk21.sh -B -pl bootstrap -am test
  -Dtest=LesCadransMontrentCeQueLeursItemsAffirmentTest,PaquetCe1LireLHeureTest,ChaqueIllustrationDeContenuDeclareSaGeometrieEtResteSousLeCliquetDePoidsTest
  -Dsurefire.failIfNoSpecifiedTests=false`, périmètre `domain`, `application`, `adapter-web`, `adapter-content`, `bootstrap`, ces
  trois classes seulement : code 0, 10, 6 et 17 tests, 0 échec. **Ce n'est ni le job `build` entier, ni la CI.**
- **Règle en vigueur** (`DEC-0120` A.280, réponse de l'utilisatrice à `Q1`, option `O1`) : un `mvn verify` complet **avant** l'audit
  à l'écran de `nadia-prof` de ce lot ; passage CI complet (`prefixes`, `chemins`, `outils`, `build`) à la clôture du palier ; toute
  annonce de porte nomme les jobs lancés.
- Premier `verify` complet du lot : **à faire**, résultat à inscrire ici par qui le lance, avec les jobs nommés.

## Ce dont `nadia-prof` reste la seule garde sur cette ligne

Ce qu'aucun test ne tient : les cinq propriétés de la section 6 de sa lecture du premier jet (`lecture-nadia-premier-jet-horloge-mat-ce1-11.md`),
résumées ici, sa section fait foi : qu'aucune stratégie qui ignore le cadran ne gagne plus de la moitié des items, que la petite aiguille se lise
entre deux nombres à 176 px, que les glyphes se lisent comme des chiffres, que le texte alternatif ne livre ni l'heure ni la place
d'une aiguille (`CON-5`), que chaque distracteur soit plausible.

---

## Bloc de validation pédagogique - nadia-prof UNIQUEMENT

> Ce bloc n'est jamais pré-rempli ni anticipé par un autre agent.

### Verdict du 2026-10-28 (`nadia-prof`, s57, palier 5, passe `P2`, dixième audit sur dix) : **VALIDE SOUS RÉSERVE**

**Done pédagogique prononcé** sur le paquet `ce1-lire-l-heure` et ses douze cadrans `horloge-a.svg` à `horloge-l.svg` tels qu'ils
sont dans `round-two` à `371425e4`. Le jar joué est construit à `d398add4` ; `git diff --name-only d398add4 371425e4` ne rend que
quatre fichiers `.md` (`backlog.md`, cette fiche, `DEC-0120`, `DEC-index.md`), et `git diff --stat` sur `domain`, `application`,
`adapters`, `bootstrap` et `pom.xml` est vide. `camille-po` peut écrire l'entrée de catalogue (`QC3`, étape distincte que ce commit ne
fait pas, voir la réserve d), corriger les deux textes du critère 11 de la story, puis passer `MAT-CE1-11` à `couvert-valide` quand
les trois conditions du critère 12 sont réunies, avec la mention de couverture ci-dessous. Elle fait des réserves ce qu'elle juge
utile, aucune n'est bloquante.

**Done technique du lot, au sens de `DEC-0120` A.280 point 2.** Le `mvn -B clean verify` complet que l'agent lanceur annonce vert à
`d398add4` (3 644 tests, 0 échec) **n'est écrit dans aucun fichier du dépôt** : ni dans la section *"État de la porte technique"*
ci-dessus, ni dans `planning.md` (`grep` de `d398add4` et de `3 644`). Je ne m'appuie pas sur un chiffre que le dépôt ne porte pas : je
l'ai relancé moi-même, `bash outils/mvn-jdk21.sh -B clean verify`, réacteur complet, sur mon arbre à `371425e4` (code identique à
`d398add4`, ci-dessus). Résultat lu dans sa sortie : JDK 21.0.10 annoncé par l'enveloppe, six modules
`SUCCESS`, `BUILD SUCCESS`, code 0, **3 644 tests, 0 échec, 0 erreur** (somme des six lignes `Tests run` de module : 120, 31, 523,
2 569, 395, 6), et la ligne du garde de paire *"strategies de paire : S1 = 6, S2 = 6, S3 = 6, S4 = 6 sur 12 items"*. Cette fiche
était en cours d'écriture pendant l'exécution ; les gardes de documentation sont relancés sur le texte final avant le commit
(fin de ce bloc). C'est le contenu du job `build` seul. `prefixes`, `chemins` et `outils` ne sont pas
passés en entier : c'est le passage de la clôture du palier, pas une condition de ce verdict.

**Comment j'ai joué, dit.** Le paquet n'est pas au catalogue (`QC3`). J'ai lancé le jar (JDK 21.0.10, port propre au lot, serveur
arrêté par son PID) par son `PropertiesLauncher`, avec un `loader.path` vers un dossier de travail hors du dépôt qui porte une copie
exacte du `catalogue.yaml` servi plus une seule entrée : identifiant `bandit-manchot-lire-l-heure-ce1`, `bandit-manchot`,
10 manches, titre *"L'heure sur l'horloge"*, consigne *"Regarde les deux aiguilles, puis choisis la bonne heure."*. **Ce titre et
cette consigne sont de ma main et n'ont été audités par personne d'autre** (ni `relecteur-pedagogique`, ni `camille-po`). Le
catalogue, le paquet et les douze dessins du jar sont identiques, octet pour octet, à ceux du dépôt (`diff`, `cmp`). Aucune entrée
n'est ajoutée au dépôt. Parcours d'un enfant : page *Mathématiques* de CE1, tuile du jeu, partie. Quinze parties en fragments htmx
(requêtes `HX-Request`), trois par plan d'erreurs : aucune erreur ; une ; deux ; trois, donc révélation ; un mélange tiré par manche.
Puis six parties dans un navigateur sans tête à 744 x 551, une erreur à chaque manche, une seconde erreur une partie sur deux,
captures de chaque cadran à l'arrivée et après l'erreur, à la taille servie et agrandies trois fois, cotes au
`getBoundingClientRect()`. **Les 12 items ont été servis et joués**, de 11 à 15 fois chacun en fragments, 210 manches en tout.

**Constaté à l'écran** :

1. **Texte servi.** Énoncé *"Quelle heure est-il ?"* sur les 12 items, 36 propositions et 2 indices : ceux du paquet, mot pour mot
   (accents, ponctuation, espace insécable : contrôle du `relecteur-pedagogique`, je n'en juge pas). À chaque manche, les trois
   propositions sont la bonne réponse et les deux distracteurs de l'item ; aucun item n'est servi deux fois dans une partie ; 10
   manches par partie. Aucune anomalie.
2. **Retour juste et faux.** Faux : croix et *"Ce n'est pas ça. Essaie encore !"*, avec l'indice de l'item ; la proposition essayée
   est barrée et reste à sa place. Au troisième échec : *"Ce n'est pas ça."*, *"Appuie sur la bonne réponse :"* et la bonne réponse
   nommée ; l'enfant bloqué avance toujours. Juste : coche et *"Bravo !"*. Fin : nombre trouvé seul (premier coup) sur nombre joué.
   C'est le gabarit commun du bandit manchot.
3. **Indices.** *"La petite aiguille montre l'heure."* sur les trois heures pleines, *"La petite aiguille a passé quel nombre ?"* sur
   les neuf autres. Chacun dit quelle aiguille regarder et jamais l'heure (`HOR-7`). Sur les quarts à 9, la question est la bonne :
   à `2 h 45`, la petite aiguille a passé 2 et pas encore 3, c'est la lecture de *moins le quart*.
4. **Ordre des propositions.** 44 ordres distincts sur 60 manches à l'écran ; en fragments, la bonne réponse est en première,
   deuxième et troisième place 43, 51 et 56 fois sur 150. Une seule rangée de trois boutons sur les 60 manches.
5. **Pli à 744 x 551.** Bas de `.indice` à 530 sur les 60 relevés après une erreur et les 30 après deux, pour les 12 items ; le bloc
   du signal finit à 556. Sur les captures, la dernière ligne lue (*"Essaie encore !"*) est entière au-dessus de 551 : les 5 px
   coupés ne portent que le bas du cadre du signal. Rien de lisible n'est coupé.
6. **Les douze cadrans**, regardés un par un à la taille servie (176 x 176) puis agrandis. Chacun montre l'heure de sa bonne réponse.
   La petite aiguille, courte et épaisse, se distingue de la grande, longue et fine, sur les douze (`HOR-3`). À `6 h`, les deux
   aiguilles font une seule droite ; la moitié épaisse, en bas, se lit, et `12 h 30` est justement le distracteur de la lecture
   inversée. **Petite aiguille à 7,5 degrés d'un nombre** : à la taille servie, elle se lit *"juste après 12"* sur `horloge-g` et
   *"juste avant 12"* sur `horloge-l` ; sur `-h`, `-i`, `-j` et `-k`, l'écart est petit mais on voit de quel côté du nombre elle
   est. C'est la géométrie d'une vraie horloge, et c'est la difficulté réelle de la compétence : je garde la géométrie (`O1` de mon
   arbitrage), aucun redessin n'est dû. **Aiguilles près du moyeu** sur `horloge-h` et `-k` : elles se rejoignent en V au moyeu, et
   chacune se suit jusqu'à son bout ; je n'y vois pas de confusion. **Glyphes** : les nombres de 1 à 12 se lisent comme des chiffres
   écrits en classe. Le cadran porte douze traits et aucune graduation des minutes : suffisant pour les heures, les demies et les
   quarts, ce n'est pas une réserve.
7. **`CON-5`.** Le texte alternatif est le même pour les 12 cadrans et ne dit ni l'heure ni la place d'une aiguille.
8. **Titre et consigne, pour `QC3`** (mes textes, non audités). La tuile *"L'heure sur l'horloge"* tient sur une ligne, à côté de
   *"Les dates et le calendrier"*, et nomme le sujet sans donner de réponse. La consigne tient sur une ligne à 744, neuf mots, une
   phrase ; *aiguilles* est le mot de la leçon ; elle dit le geste (regarder les deux aiguilles) qui est la compétence. Je les
   retiens tels quels.

**Vérifié, et tenu** : compétence `MAT-CE1-11`, moitié horloge, travaillée et pas une compétence voisine : trois heures pleines,
trois demies, trois quarts à 3, trois quarts à 9, et chaque item demande de lire les deux aiguilles, les distracteurs piégeant la
lecture inversée des aiguilles, la grande aiguille lue comme l'heure et l'heure voisine. Exactitude des 12 bonnes réponses contre les
cadrans rendus. Distracteurs plausibles tous les 24, `11 h 30` sur `horloge-f` regardé à l'écran : la petite aiguille est à
mi-chemin de 12 et de 1, et `11 h 30` reste l'erreur de *"l'heure d'avant"* prise de l'autre côté de 12, la plus faible des douze,
recevable comme je l'ai écrit. Stratégies de paire `S1` à `S4` : 6, 6, 6, 6 sur 12, recomptées ce jour par un script sur le paquet
du jar, identiques à la ligne du garde. Lisibilité CE1 (énoncé de quatre mots, propositions de 3 à 7 caractères). Aucune
personne, aucun contexte de vie, aucun stéréotype. Le tirage ne suit aucun ordre de difficulté : c'est le gabarit commun, je ne l'ai
demandé nulle part, ce n'est pas une réserve.

**Mes propres constats, revérifiés avant d'arbitrer.** La réserve c de ma seconde lecture était un compte *"à la main"* `[calcul]`.
Recomptée ce jour par un script sur le paquet du jar : **10 sur 12**, identique. La réserve a : l'intrus d'une autre forme est faux
sur **12 items sur 12**, recompté par le même script, identique. Rien à corriger dans mes chiffres.

**Réserves, non bloquantes** :

- **Réserve a, maintenue** (lecture du premier jet, 3.2 ; arbitrage, 4.2 ; seconde lecture, 4) : la proposition d'une autre forme
  de minutes est toujours fausse, 12 sur 12. Un enfant qui l'a remarqué choisit dans une paire, et la barre de `S1` à `S4` (6 sur 12)
  ne vaut que parce que la paire est équilibrée. Ce qui la lèverait : sur une partie des items, la bonne réponse est l'intrus d'une
  autre forme, la barre de paire restant la même et mesurée par le garde.
- **Réserve c, maintenue, inchangée** (seconde lecture, 4, datée du 2026-10-28) : *"aux heures pleines et aux quarts à 15, prendre
  l'heure d'après dans la paire ; aux demies et aux quarts à 45, l'heure d'avant"* gagne 10 items sur 12 ; le plancher que trois
  items par forme permettent est 8. Ce qui la lèverait : plus de trois items par forme, ou la réserve a. Pas une exigence de ce lot.
- **Réserve d, neuve, datée du 2026-10-28** : l'entrée de catalogue de ce paquet n'existe pas, et je n'ai joué que l'entrée locale
  décrite plus haut. Exigence : l'entrée de `QC3` porte le titre *"L'heure sur l'horloge"*, la consigne *"Regarde les deux
  aiguilles, puis choisis la bonne heure."*, la mécanique `bandit-manchot` et 10 manches ; tout autre titre ou toute autre consigne
  me sont montrés avant la fusion. Leur orthographe et leur ponctuation restent au `relecteur-pedagogique` (`DEC-0002`).

**Mention de couverture de la ligne `MAT-CE1-11`, à recopier mot pour mot dans la cellule** (critère 11 de la story) :

> "lire l'heure sur une horloge à aiguilles, en heures pleines, demi-heures et quarts d'heure (grande aiguille sur 12, 6, 3 ou 9),
> réponse écrite en chiffres ('3 h', '3 h 30', '3 h 15', '2 h 45') ; se repérer dans le calendrier par la date seule ; l'ordre des
> mois est servi par `QLM-CP-13` ; restent non couverts : l'heure dite en mots ('et quart', 'et demie', 'moins le quart'), les
> minutes autres que 00, 15, 30 et 45, midi et minuit nommés, la lecture sur 24 heures et l'horloge à affichage numérique"

Les deux jeux couvrants sont `bandit-manchot-calendrier-dates-ce1` et l'entrée à venir de ce paquet (critère 12). Le plafond de la
ligne est `couvert-valide` (3.5 de `arbitrage-nadia-palier-5-lp7-d5.md`) : ce qui reste non couvert n'est pas demandé par la ligne du
référentiel, qui dit *"heures, demi-heures, quarts d'heure"*.

**Je reste la seule garde**, aucun test ne les tient : qu'aucune stratégie sans cadran autre que `S1` à `S4` ne soit majoritaire
(réserve c) ; la plausibilité de chaque distracteur ; la lecture de la petite aiguille entre deux nombres à 176 px ; les glyphes lus
comme des chiffres ; que rien de lisible ne soit coupé sous le pli ; `CON-5` sur le texte alternatif ; le titre et la consigne de
l'entrée à venir (réserve d).

**Coût, selon ce que les textes chiffrent.** Aucun texte ne chiffre un audit à l'écran : `DEC-0120` A.280 (point 3, tableau, et
point 6) écrit pour l'horloge *"il est dans les 2,50 de `MAT-CE1-11` (A.267 point 2) ; aucune règle ne chiffre un audit à l'écran en
plus"*, et A.283 classe l'audit à l'écran d'un lot *"non chiffré"*. Le `verify` que j'ai relancé n'est chiffré par aucune règle
(A.280 point 2). **Je ne fixe aucun prix.** Durée à l'horloge (`date`) : jeu et relevés de 20:54:46 à 21:03:13 ; la lecture des
textes avant le jeu n'est pas chronométrée ; `verify` complet relancé pendant la rédaction, 4 min 03 s selon Maven ; fin, vérifications
avant commit comprises : 21:11:05, soit 16 min 19 s depuis le début du jeu. Gardes relancés sur le texte final : identifiants
(`ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest`, 10 tests, 0 échec ; ce bloc n'écrit aucun identifiant neuf, les réserves a,
c et d sont des lettres locales à mes textes, comme dans ma seconde lecture), tableaux et renvois de décision (18 tests avec le
précédent, 0 échec), `verifier-chemins.sh` et `verifier-prefixes.sh` dans la forme de la CI, code 0 tous les deux.

**Rejeu : aucun n'est dû.** Ce verdict n'est pas un refus ; `P3` (A.265) n'attribue de rejeu qu'à un lot refusé, et un rejeu au plus
par lot. Les dix audits du palier sont faits, **aucun lot refusé sur dix**. La marge de 0,5 de B n'est consommée par aucun rejeu de
l'horloge (A.267 point 2). Q3 d'A.271 (le quatrième rejeu) se rouvre à la fermeture des dix audits, qui est ce verdict ; elle est à
`camille-po`, avec ce fait observé : zéro lot refusé. **[corrige le 2026-10-28 par A.286, texte d'origine conserve]** La question
ne se rouvre plus : l'utilisatrice a répondu le 2026-10-28 *"O1 il tombe"*, et `DEC-0120` A.286 point 3 la consigne close (dix
audits, zéro lot refusé, aucun rejeu dû, pas de report au palier 10). Je n'en décide pas.

**`Q-273-1` (ii)** : fermée dans ses faits, comme je l'ai écrit dans ma seconde lecture (section 5, `af73a862`) : la seconde lecture a
eu lieu, elle est unique, elle est rangée au geste qu'A.272 et A.280 nomment. Rien de neuf ici, aucun prix. (i) n'est pas touchée.

**Signé** : `nadia-prof`, s57, palier 5, 2026-10-28.
