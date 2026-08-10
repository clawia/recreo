# [Sprint s06] Retrospective - 2026-08-10

- **Anime par** : sofia-sm

## Ce qui a marche

- **Le mecanisme de la branche jetable (`DEC-0009`) a servi une seconde fois, sans improvisation.**
  Sorti de quarantaine, "Le bon son" est rentre au catalogue par une fusion `octopus` de la fiche
  qui porte le verdict et de la branche jetable qui ajoute la ligne, en un seul commit - exactement
  le sequencement que j'avais concu en s05 pour ce meme jeu. Un mecanisme pense une fois pour un cas
  a servi sans reinvention pour un cas voisin.
- **L'arbitrage prealable, invente en s05 pour "Le bon son", a fonctionne deux fois de plus.**
  `theo-archi` a route a `nadia-prof` les deux questions techniques de S6-9 et de S6-10 avant que
  `lea-dev` ne code, et les deux arbitrages ont chacun evite de defaire un acquis (l'etat
  `sortie-pleine` de S3-6, le fondu du favicon de s05) sans qu'aucune ligne de code n'ait ete
  ecrite pour rien. Aucune CI n'aurait vu ces deux regressions.
- **Trois autorites ont corrige leur propre travail plutot que de le defendre.** `nadia-prof`,
  deux fois : son perimetre mal decrit sur le favicon (une image la ou le fichier en contient
  trois), et une contradiction interne a son propre avis du 2026-08-06 sur `MAIN`/`PAIN`. Sa
  formule vaut d'etre citee au mot pres : *"ce qui lie l'equipe est ma propriete, pas ma liste."*
  `theo-archi` a corrige son propre epinglage de la veille (l'extension d'`ADR-0017`) des qu'il a
  vu qu'elle etait devenue sans objet. `lea-dev` a signale un defaut hors du mandat de sa propre
  story (le contre-exemple devenu un vrai bean Spring, en s05 deja, et cette fois le motif
  technique des mots `MAIN`/`PAIN`) plutot que de le corriger sans mandat ou de le taire.
- **Deux defauts de `verifier-lignee.sh` ont ete trouves par des agents qui l'appliquaient pour de
  vrai, pas par relecture de code.** `theo-archi` puis `nadia-prof` ont chacun rencontre le script
  sur leur propre worktree, obtenu un refus qu'ils ont su reconnaitre comme faux, et l'ont signale
  avec le fait discriminant deja verifie plutot qu'une simple plainte. Le script a ete corrige les
  deux fois avec une preuve de morsure par mutation reelle sur le worktree fautif lui-meme, pas sur
  une fixture imaginee.
- **`DEC-0014` n'a pas repete le defaut de `DEC-0012` en s05** : la regle actee a la retro de s05
  ("une decision qui change un comportement observable est executee dans le meme commit que sa
  levee, ou l'ecart est constate avant la review") a fonctionne comme prevu - l'obstacle a ete
  route, execute, et cloture avant cette review plutot que decouvert a sa cloture.

## Ce qui a coince

### 1. Le fait le plus instructif du sprint : des garde-fous qui mordent sur la mauvaise propriete

Le paquet de conjugaison a traverse les criteres d'acceptation de `camille-po`, l'avis de
faisabilite de `theo-archi`, le garde-fou d'`ADR-0016` qui exige un indice sur chaque item, celui
d'`ADR-0023` qui compte les items, une partie jouee par `marc-dev` lui-meme, 279 tests verts, deux
fusions et une relecture complete. **Tous ont verifie qu'un indice existait ; aucun n'a lu ce
qu'il disait.** Cinq indices sur douze enoncaient une regle de conjugaison fausse, trois se
contredisant entre items du meme paquet. Le seul controle capable de l'attraper etait une
professeure des ecoles jouant jusqu'a la manche 10.

C'est une forme nouvelle par rapport a s05 : la ou s05 avait des garde-fous verts qui ne mordaient
pas du tout, s06 a des garde-fous qui mordent **parfaitement sur une propriete qui n'etait pas
celle qui comptait**. Le relecteur a bien lu ces mêmes indices - il verifie l'orthographe et
l'accentuation de tout texte lu par un enfant - et n'a rien vu a y redire, ce qui est conforme a
sa frontiere et non un manquement : la justesse d'une regle de grammaire enseignee n'est pas de
son ressort.

**Je ne propose pas d'amendement pour ce point**, et je le dis explicitement plutot que de laisser
un vide : `nadia-prof` elle-meme ecrit que "cette regle de grammaire est-elle vraie" n'est pas une
propriete qu'un automate peut trancher. Le double Done du produit anticipe deja exactement cette
classe de defaut - un increment techniquement irreprochable peut etre pedagogiquement faux - et ce
sprint en est la demonstration la plus nette depuis l'ouverture du projet, pas la decouverte d'un
trou dans la regle. La seule reponse possible reste celle qui existe deja : l'autorite exclusive de
`nadia-prof`, exercee en jouant.

### 2. Deux defauts successifs dans un garde-fou livre ce sprint, plus une lacune de CI sur le meme lot

`verifier-lignee.sh` a ete trouve fautif deux fois (`theo-archi`, puis `nadia-prof`), chaque fois
sur un cas voisin du precedent - un seul test d'ancetralite ne peut pas couvrir toutes les formes
saines d'un ecart avec `round-two`. Et `theo-archi` a etabli, independamment, que ni
`verifier-lignee-test.sh` ni `verifier-jdk-test.sh` n'etaient lances par aucun automate : les deux
scripts etaient livres comme "garde-fous testes" et seule la main d'un agent executait leurs tests.
Deux amendements ecrits (section 9, etapes 1 et 4) : le worktree obligatoire pour tout agent qui
ecrit, et l'exigence qu'un script "teste" le soit reellement par une etape de CI, perimetre derive
du repertoire.

### 3. Trois mesures du meme fait, trois resultats, aucun mensonge

`lea-dev` trouve un debordement de menu a 360 px ; `ines-design` ne le reproduit pas et met en
cause la commande ; `yanis-qa` etablit le plancher de `chrome --headless --window-size` a
exactement 500 px et conclut a l'artefact de mesure. La regle de methode actee en section 4 ne
couvrait que les **chiffres de comptage**, jamais les **mesures de rendu** : etendue par
amendement (voir plus bas). `theo-archi` a ferme la cause racine (`outils/mesurer-rendu.sh`,
`ADR-0025`) et declare la propriete de debordement elle-meme **non gardable** par un automate,
confiee a `nadia-prof` en jouant - ce n'est pas un defaut a corriger davantage, c'est une limite
reelle du rendu que trois agents honnetes ont mesuree chacun a sa maniere.

### 4. Un defaut de coordination qui est le mien

Ma consigne aux agents disait "ne pousse rien et ne fusionne pas". `camille-po` l'a lue comme "ne
commite pas" - une lecture defendable de la phrase telle qu'ecrite - et son travail est reste **non
commite dans le depot principal**, la ou j'ecrivais en parallele sous ma casquette dev. Rien n'a
ete perdu, uniquement parce que nos perimetres ne se recouvraient pas ; un nettoyage d'arbre de ma
part l'aurait efface sans laisser de trace. La meme consigne a ete donnee a tous les autres agents
et n'a mordu chez aucun, **parce qu'ils travaillaient tous dans un worktree separe** -
`camille-po` est la seule a ne pas en avoir ouvert un. Ce n'est donc pas un defaut de sa part :
c'est une consigne ambigue de ma part, plus l'absence d'une regle rendant le worktree obligatoire
pour tout agent qui ecrit, quelle que soit la nature de ce qu'il ecrit. Amendement ecrit (section
9, etape 1).

### Deux ironies, a valeur de constat plutot que d'amendement

- **`relecture.md`, le rapport qui verifie l'absence de chemins de poste de travail, faisait
  rougir `verifier-chemins.sh` par sa propre citation d'exemple** (une forme Git Bash suivie d'un
  segment reel, contrairement a la forme tronquee de `CLAUDE.md`). Trouve en relancant le
  garde-fou sur l'arbre fusionne avant de le declarer vert, corrige directement. Un cas isole, pas
  un motif recurrent : aucun amendement.
- **`DEC-0014` a ete decidee sans etre executee jusqu'a ce qu'elle soit routee** - le meme defaut
  de gouvernance que celui constate sur `DEC-0012` a la cloture de s05, sauf que cette fois il a
  ete rattrape **avant** la review, par la regle deja actee a cette meme retro de s05. C'est la
  preuve que l'amendement de s05 fonctionne, pas la decouverte d'un trou : aucun amendement
  supplementaire.

### Un ecart de comptage, deja referme dans le corps de la regle qu'il concerne

Le depot compte 27 `@ArchTest` pour 28 `ArchRule` (la 28e s'execute par `.check()` explicite,
`ScanDeComposantsTest`). En s05 les deux nombres coincidaient, donc rien ne l'aurait signale a
l'epoque. Ce n'est pas un nouvel amendement : c'est une precision ajoutee au corps de la regle de
methode de comptage deja actee (section 4), pour qu'un futur comptage par `@ArchTest` ne se croie
pas complet.

### Anomalie de comptage de tokens - constat, sans amendement

Le rapport de tokens de ce sprint fait apparaitre, dans le cumul "hors sprint" de
`squad/rapports/JOURNAL-tokens.md`, un volume tres eleve pour `theo-archi` (1 427 tours, environ
200 M de cache lu, sur deux modeles). Deux agents ont repris leur tache apres une erreur
d'infrastructure ce sprint (`relecteur-pedagogique`, `theo-archi`), ce qui aurait pu faire craindre
une perte de mesure liee a la reprise. Verifie avant de conclure : `relecteur-pedagogique` ne porte
aucune entree dans "hors sprint" et son volume en fenetre est du meme ordre qu'en s05 - sa reprise
semble entierement captee. Le tres gros volume de `theo-archi` s'explique d'abord par un fait
deja connu et documente independamment de ce sprint : `s02` et `s04` n'ont **aucune** fenetre
temporelle enregistree (`planning.md` sans horodatage de debut dans les deux cas), donc toute son
activite architecturale de ces deux sprints tombe dans "hors sprint" a chaque execution du script,
depuis toujours. Je ne peux pas exclure completement qu'une part de sa reprise du 2026-08-10 y soit
aussi tombee si son horodatage a echappe de peu a la fenetre du sprint, mais je ne trouve rien qui
le prouve, et je ne repartis aucun token entre fenetres sans preuve - la regle du script
("jamais reparti arbitrairement") vaut aussi pour mes propres doutes. Je ne propose pas
d'amendement : reconstruire retroactivement les fenetres de `s02` et `s04` est un chantier
distinct de ce sprint, deja nomme et non traite depuis plusieurs cloitures, et l'ajouter ici
serait une decision de perimetre qui ne m'appartient pas seule a prendre sans en avoir mesure le
cout.

## Amendements au process

| Section de squad/README.md | Changement | Raison |
|---|---|---|
| 9, etape 1 (Planning) | Tout agent qui ecrit dans le depot ouvre un worktree dedie avant sa premiere ecriture, sans exception, y compris un agent qui ne produit que des fichiers de decision. | `camille-po` a travaille directement dans le depot principal sous une consigne de fusion ambigue ; rien n'a ete perdu par chance de perimetre disjoint, pas par construction. |
| 9, etape 4 (Porte technique) | Un script livre avec sa propre suite de tests n'est declare "teste" qu'une fois qu'une etape de CI l'execute reellement, pas seulement parce que le fichier `*-test.sh` existe dans le depot. | `verifier-lignee-test.sh` et `verifier-jdk-test.sh`, livres testes en s06, n'etaient executes par aucun automate avant que `theo-archi` ne l'ajoute. |
| 4 (Les ceremonies), methode de mesure | La regle de methode obligatoire (deja actee pour les chiffres de comptage) s'etend a toute mesure de rendu : commande exacte citee dans le meme paragraphe que le resultat. Precision ajoutee au corps de la regle existante : le piege `@ArchTest` vs `ArchRule` (28 regles, 27 `@ArchTest`). | Trois agents ont mesure le meme debordement par trois methodes distinctes et obtenu trois resultats differents, sans qu'aucun n'ait menti, faute d'une commande citable dans le depot. |
| 10 (Frontieres entre agents) | Toute story qui modifierait un ecran, un texte ou un comportement deja accorde par `nadia-prof` est signalee pour un arbitrage prealable avant developpement, pas seulement auditee apres coup. | Cinq stories ou constats du sprint auraient defait un acquis sans qu'aucune CI ne les voie ; deux arbitrages prealables (S6-9, S6-10) ont fonctionne exactement comme prevu la ou ils ont eu lieu. |

Quatre amendements ce tour, contre six a la retro de s05 : je n'en propose pas pour chaque
constat - le fait le plus instructif du sprint (section "Ce qui a coince", point 1) et deux
"ironies" nommees restent des constats geres par des regles deja en place, pas des trous a
combler par une regle nouvelle.

## Decisions prises

Aucun `ADR` ni aucune `DEC` n'est cree pendant cette retro. Les quatre amendements ci-dessus sont
ecrits directement dans `squad/README.md` dans le meme geste que cette retro, conformement a la
section 9 etape 9 ("Sofia anime, ecrit `retro.md`, et amende ce README si le process doit
evoluer") : contrairement a la retro de s05, je ne les laisse pas en propositions tracees pour un
planning ulterieur, l'utilisatrice ayant deja confirme cette lecture de l'etape 9 lors du planning
de ce meme sprint.
