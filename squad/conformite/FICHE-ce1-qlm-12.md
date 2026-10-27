# Fiche de conformite - ce1-qlm-12

- **Mecanique** : `bandit-manchot`, `type: question`, sans texte support, comme les paquets d'EMC CE1 ; aucune modification de
  la mecanique, aucune illustration (`docs/backlog/backlog.md`, annotation du 2026-10-25, cinquieme, *"Ce qui est commun aux
  neuf stories"*).
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/ce1-qlm-generations.yaml` (12 items, lus par
  `grep -c "bonneReponse:"`), ecrit par `marc-dev` (instance neuve, `S57-BR-QLM-CE1`).
- **Entree de catalogue** : aucune. `QC3` : un paquet n'entre au catalogue qu'apres un Done pedagogique ou un refus leve ;
  `grep -c "ce1-qlm" adapters/adapter-content/src/main/resources/catalogue.yaml` rend 0 le 2026-10-27.
- **Ligne declaree** : `QLM-CE1-12`. **Story** : `S57-BR-QLM-CE1-12` (`docs/backlog/backlog.md`). **Decision** : `DEC-0120` A.227, A.250 a A.255,
  A.268 a A.280.
- **Competence programme visee** : `squad/programme/questionner-le-monde/competences-ce1.md`, `QLM-CE1-12`, *"Situer sur une frise les generations d'une famille (enfants, parents, grands-parents) et quelques evenements de la vie d'un personnage."*
- **Conditions opposables** : celles de la story `S57-BR-QLM-CE1-12` et les reperes `QC1` a `QC9` du bloc commun, **non recopiees ici** :
  la story fait foi, ses mots sont ceux de `nadia-prof`. Plafond de la ligne : `couvert-valide`.
- **Statut global** : **en attente d'audit a l'ecran**. Fiche ouverte par `camille-po` le 2026-10-27 (`DEC-0120` A.280, reponse
  de l'utilisatrice a `Q2` : nom `FICHE-ce1-qlm-<lot>.md`). Aucun verdict. La ligne `QLM-CE1-12` reste `non couvert` dans la
  matrice, qui ne bouge qu'au verdict de `nadia-prof`.
  **[corrige le 2026-10-27 par `nadia-prof` : VALIDE SOUS RESERVE, voir le bloc de validation ; texte d'origine conserve]**
- **Derniere mise a jour** : 2026-10-27.

---

## Competence visee

| Matiere | Niveau | Competence | Reference au referentiel |
|---|---|---|---|
| questionner le monde | CE1 (cycle 2) | Situer sur une frise les generations d'une famille (enfants, parents, grands-parents) et quelques evenements de la vie d'un personnage. | `squad/programme/questionner-le-monde/competences-ce1.md`, `QLM-CE1-12` |

## Criteres attendus - camille-po

Les criteres d'acceptation de cette fiche sont ceux de la story `S57-BR-QLM-CE1-12` (`docs/backlog/backlog.md`, titre *"Situer les generations d'une famille et les evenements d'une vie"*) et
les reperes `QC1` a `QC9` du bloc commun. Je ne les recopie pas : une copie derive de la source. Je n'ecris aucune mesure ici.

- [ ] Criteres de la story `S57-BR-QLM-CE1-12`, numerotes dans le backlog.
- [ ] `QC1` a `QC9` du bloc commun (paquet, ligne declaree, branchement, test du paquet, densite, conditions negatives,
  lecture avant `verify`, Done technique, ce que `nadia-prof` garde seule).
- [ ] Texte servi a l'enfant integralement accentue, orthographe et ponctuation irreprochables (`DEC-0002`).

## Parcours du lot, faits lus au depot

Source : `planning.md` du sprint s57, amendement du 2026-10-27 15:40, section 1 ; chaque SHA est un commit de `round-two`.

| Geste | Auteur | Commit | Fichier |
|---|---|---|---|
| premier jet | `marc-dev` | `de814b09` | `ce1-qlm-generations.yaml` |
| lecture du premier jet | `nadia-prof` | `c577ca31` | `lecture-nadia-premier-jet-ce1-qlm-generations.md` |
| corrections | `marc-dev` | `249aaf71` | `ce1-qlm-generations.yaml` |
| relecture des corrections (seconde lecture) | `nadia-prof` | `de626e12` | `relecture-nadia-corrections-ce1-qlm-generations.md` |

Les fichiers de `nadia-prof` sont dans `squad/sprints/s57-version-cp-ce1-cm1-trois-instances/`. Cette fiche n'en reprend aucun
verdict : la lecture et la relecture sont des gestes avant `verify`, pas le Done pedagogique.

## Etat de la porte technique

**Ce qu'un fichier du depot prouve, et rien de plus.**

- **Aucun `mvn verify` complet n'a ete lance pour ce lot** (`planning.md`, amendement du 2026-10-27 15:40, section 1 : fait donne
  par l'agent lanceur, non verifie ; les relectures ecrivent *"aucun autre job de `ci.yml` lance"*).
- **Regle en vigueur** (`DEC-0120` A.280, reponse de l'utilisatrice a `Q1`, option `O1`) : un `mvn verify` complet **avant** l'audit
  a l'ecran de `nadia-prof` de ce lot ; passage CI complet (`prefixes`, `chemins`, `outils`, `build`) a la cloture du palier ; toute
  annonce de porte nomme les jobs lances.
- Premier `verify` complet du lot : **a faire**, resultat a inscrire ici par qui le lance, avec les jobs nommes.

## Ce dont `nadia-prof` reste la seule garde sur cette ligne

Ce qu'aucun test ne tient (`QC9`, `arbitrage-nadia-palier-5-qlm-9-lots.md`, section 6) : l'exactitude de chaque fait contre la source
ecrite en commentaire du paquet, `NRC-CE1-1` sur le sujet de l'item, `CYC-1` contre les paquets voisins, `TRV-CE1-6`, l'operation
mentale de `FER-3`, et les conditions propres a la ligne que la story nomme.

---

## Bloc de validation pedagogique - nadia-prof UNIQUEMENT

> Ce bloc n'est jamais pre-rempli ni anticipe par un autre agent.

### Verdict du 2026-10-27 (`nadia-prof`, s57, palier 5, passe `P2`, rang 9 sur 9) : **VALIDE SOUS RESERVE**

**Done pedagogique prononce** pour `QLM-CE1-12` sur le paquet `ce1-qlm-generations` tel qu'il est dans `round-two` a `d6f2e007`
(dernier changement du paquet : `249aaf71`). `camille-po` peut brancher le paquet (`QC3`) et passer la ligne a `couvert-valide` ;
la reserve `V1` ci-dessous est a faire mesurer, elle ne bloque pas.

#### 1. Ce que j'ai joue, et comment

- **Jar** : le `recreo.jar` que l'agent lanceur m'a donne, construit sur `round-two` et non reconstruit par moi. Le paquet
  qu'il porte, `catalogue.yaml` et `matieres.yaml` ont les memes octets que ces fichiers dans l'arbre de `d6f2e007` (`sha256`).
  `git diff --name-only 90c421a7 d6f2e007` ne liste que des `.md` : le code est celui de `90c421a7`, dont le `verify` complet
  est vert (`planning.md`, amendement du 2026-10-27 21:34, section 3, job `build` seul).
- **Lancement** : JDK 21.0.10 designe par la toolchain (ligne *"using Java 21.0.10"* du journal), `--server.port=18112`
  (propriete Spring Boot `server.port`, comme `JarRecreoIT` ; port libre avant le lancement). Aucune commande portable du depot
  ne lance un jar avec un catalogue de sonde : la commande du `README.md` est `java -jar`. Serveur arrete **par son PID**, port
  libre ensuite.
- **Hors catalogue, comme le veut `QC3`** : j'ai pose devant le jar, sur le chemin de classes, une copie de `catalogue.yaml`
  plus une entree de sonde, hors du depot et non commitee (meme methode que `FICHE-saisie-complements-10-cp.md`, section 1).
  Entree de sonde : titre *"Les générations"*, consigne *"Lis bien, puis choisis la bonne réponse."* (celle des paquets d'EMC
  CE1), 10 manches. **Le titre et la consigne de l'entree reelle ne sont pas audites ici** : si `camille-po` en ecrit
  d'autres, je les lis avant le branchement.
- **Parcours** : accueil, CE1, *Questionner le monde*, *Les générations*, puis huit parties de 10 manches, en requetes HTTP sur
  les memes routes et avec le meme en-tete htmx que le navigateur, fragments rendus lus en texte. Trois parties sans erreur, trois
  avec une erreur par manche, une avec deux erreurs puis le dernier choix restant, une avec trois erreurs pour atteindre la
  revelation. **Les 12 items ont ete servis**, chacun au moins 3 fois ; aucune manche repetee dans une partie.
- **Audit degrade sur un point, dit** : je n'ai pas vu l'ecran dans un navigateur. Aucune capture, aucune mesure de pli ni de
  coupure de ligne. C'est la reserve `V1`.

#### 2. Ce que j'ai constate a l'ecran

| Point | Constat |
|---|---|
| Ancrage | Les deux moities de `QLM-CE1-12` sont servies : generations (items 1, 3, 4, 6, 8, 9, 11) et evenements d'une vie (2, 5, 7, 10, 12). *Enfants*, *parents*, *grands-parents* et un evenement sont bonnes reponses (`FER-1`). Deux taches, voisin (8) et ordre (4) (`FER-3`) |
| Exactitude | Les 12 bonnes reponses sont justes par les liens ecrits dans l'enonce ; aucune proposition fausse n'est defendable. Item 6 (Tom et Mia, enfants de Nora) : *celle des enfants*, juste |
| Lisibilite | Enonces de 13 a 20 mots (decoupe par espaces), trois phrases au plus ; propositions de 2 a 6 mots ; indices de 9 a 12 mots |
| Accents et typographie | Tout le texte rendu est accentue (*génération*, *arrière*, *âgé*, *entrée*, *collège*, *Inès*, *Noé*, *Léa*, *Écris*). Espace insecable `U+00A0` avant chaque *?* des 12 enonces et dans les messages de la mecanique |
| Retour faux | *"Ce n'est pas ça. Essaie encore !"*, la proposition essayee marquee, puis l'indice de l'item, qui reste affiche a la seconde erreur. Les 12 indices servis sont ceux du paquet, ce sont des methodes : aucun ne contient la bonne reponse ni le premier element d'une suite |
| Revelation | A la troisieme erreur (en repressant une proposition deja essayee) : *"Ce n'est pas ça. Appuie sur la bonne réponse :"* suivi de la reponse, indice toujours affiche ; l'appui sur la reponse passe a la manche suivante, sans *Bravo* et sans point |
| Fin de partie | *"Tu as trouvé, tout seul : N"*, *"Tu as joué toutes les questions : 10"*, puis le retour de la derniere manche et *"C'est fini !"* ; apres une revelation : *"Tu n'as pas trouvé."*, la question et *"La bonne réponse : ..."* |
| `GEN-CE1-2`, `BIA-4`, `TRV-CE1-6` | Aucune famille dite complete ou incomplete (Nora, seul parent nomme a l'item 6, sans rien en dire) ; aucun *qui vit avec qui* ; aucun *ton*, *ta*, *tes*. Mamans et papas, fils et filles repartis ; premier emploi dans la vie de Sara, Léa, Paul et Jade, naissance de son enfant dans celle de Malik |
| `R12-6`, `R12-2` | Aucun item ne repond par l'ordre du premier emploi et de la naissance de son enfant. A l'ecran, la suite de l'item 12 n'offre pas *"entrée à l'école, premier emploi, naissance"* |
| Mort, journee, autrefois | Aucun enonce ni indice n'evoque la mort, un moment de la journee ou ce qu'une generation faisait autrefois |

**Comportement de la mecanique, commun a tous les paquets a trois propositions, hors du lot et non rouvert** : apres deux
erreurs, le seul choix restant rend *"Bravo !"* sans point (le score ne bouge pas, *"tout seul : 0"* le dit a la fin). Je le
note parce que je l'ai vu ; ce n'est pas un defaut de `ce1-qlm-generations`.

#### 3. L'accord de *du plus âgé au plus jeune* (signal du 2026-10-27, `P5`)

A l'ecran, les items 3 et 8 affichent *"quelle suite range les générations du plus âgé au plus jeune ?"*, au masculin apres un
nom feminin ; l'item 11 affiche *"Quelle suite va du plus âgé au plus jeune ?"*, sans nom. Le sens est clair pour un enfant de
CE1 et le vocabulaire est de son niveau : sur ce que je garde, rien a corriger. **L'accord n'est pas de mon ressort**
(`DEC-0002`, amendement du 2026-08-04 ; `DEC-0056`) : il reste au `relecteur-pedagogique`, et aucun releve n'existe a ce jour
(aucun commit `[REL]` depuis le 2026-10-26, aucun fichier de releve dans le dossier du sprint). **Ce Done ne couvre pas cet
accord.** S'il est juge fautif, la correction touche la formule de `R12-2`, que `G12-3` lit a la lettre (`theo-archi`). Que le
branchement attende ce releve ou non est a `camille-po`, je ne le decide pas.

#### 4. Reserve, non bloquante, neuve et datee du 2026-10-27

- **`V1`, rendu visuel non mesure.** Je n'ai vu aucun ecran dans un navigateur. Les propositions les plus longues portent des
  mots a trait d'union : *celle des arrière-grands-parents* (items 1, 4, 9) et les suites des items 3 et 8 (jusqu'a 47
  caracteres, *"arrière-grands-parents, grands-parents, parents"*). **Exigence** : a 744 x 551 et a 360 de large, aucune
  proposition coupee a l'interieur de *arrière-grands-parents* ou de *grands-parents*, et aucune reponse attendue sous le pli
  a la revelation. **Mesure a faire** par qui sait capturer l'ecran (`ines-design`, pilote de `releve-ecran-ines-palier-3.md`). Si
  la mesure ne montre rien, la reserve est levee sans rejeu ; si elle montre une coupure, `camille-po` en fait une story. Le
  levier n'est pas choisi par moi.

**Limite de ce que le Done certifie** : la ligne dit *situer sur une frise*. Le paquet fait ranger et situer dans le temps ; il ne
fait lire aucune frise dessinee (bloc commun : aucune illustration), et seul l'indice de l'item 10 fait imaginer une frise. Mes
conditions de `3.12` ne demandaient pas de frise dessinee : la barre est tenue, je ne la releve pas. Ce n'est pas une reserve.

#### 5. Ce dont je reste la seule garde sur cette ligne

L'exactitude de chaque lien de parente et de chaque ordre de vie ; qu'un indice ne dise pas la reponse par la parente ou par la
vie ; qu'un indice-methode reste vrai sur tous les items ; qu'un enonce n'evoque pas la mort ; `GEN-CE1-2`, `BIA-4` et
`TRV-CE1-6` (jugement de sens) ; `NRC-CE1-1` et `CYC-1` contre `cp-frise-de-la-journee` et `cp-qlm-autrefois-aujourdhui` ; le
rendu visuel des propositions longues (`V1`). **Aucune de ces proprietes n'est couverte par un test.**

#### 6. Duree, a l'horloge

Mesuree par `date` : debut **21:52:20**, serveur arrete a 22:12:38, fin donnee dans le rapport de commit (ce fichier ne peut pas
citer sa propre verification). Donnee brute pour la remesure d'A.276 ; je n'en convertis aucune au bareme. Je n'ai modifie ni
paquet, ni test, ni catalogue, et je n'ai alloue aucun identifiant : `V1` est un repere de cette fiche.

**Signe** : `nadia-prof`, s57, palier 5, 2026-10-27.
