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
- **Ligne de matrice, etat du 2026-10-28** : `QLM-CE1-12` reste `non couvert`. Motifs lus au depot : (1) `QC3`, aucune entree de catalogue (`grep -c "ce1-qlm" adapters/adapter-content/src/main/resources/catalogue.yaml` rend 0), donc aucun jeu a nommer dans la cellule "Jeu(x) couvrant" ; (2) Done technique complet, les quatre jobs de `.github/workflows/ci.yml` (`QC8`, A.280 point 2), dont seul `build` a ete lance. Aucun des deux n'est une decision de `nadia-prof`. La ligne bouge quand l'entree est fusionnee et que la CI complete est verte (`S57-QLM-CE1-ENTREES-CATALOGUE`), apres le releve de l'accord (`S57-QLM-CE1-12-ACCORD`) ; fusion du paquet dans `round-two` : `08f7a2ab`.
- **Stories du backlog pour ce lot** (`docs/backlog/backlog.md`, annotation du 2026-10-28, douzieme, `DEC-0120` A.283) : `S57-QLM-CE1-12-V1-MESURE`, `S57-QLM-CE1-12-ACCORD` (signal `P5`, le branchement attend son releve), `S57-MECA-BRAVO-SANS-POINT` (mecanique commune, hors lot). Aucune n'est engagee.
- **Derniere mise a jour** : 2026-10-27 ; 2026-10-28 (`camille-po`, `DEC-0120` A.283 : statut, ligne `verify`, etat de la ligne de matrice, stories).

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
- Premier `verify` complet du lot : **fait au niveau du reacteur, pas par lot** (mis a jour le 2026-10-28 par `camille-po`, `DEC-0120` A.283 ; texte d'origine *"a faire"* perime, conserve ici). `bash outils/mvn-jdk21.sh -B clean verify`, reacteur complet, sur `90c421a7` : `BUILD SUCCESS`, 3618 tests unitaires et 6 d'integration, 0 echec, mesure par `sofia-sm` (`planning.md`, amendement du 2026-10-27 21:34, section 3), **job `build` seul**. `prefixes` et `chemins` n'ont ete lances qu'en commande de garde-fou, forme CI, `outils` n'a pas ete lance : la porte technique complete n'est pas annoncee (A.280 point 2). Sur `1a810b79`, base de cette mise a jour : un `verify` complet vert (3 624 tests, `build` seul) est **annonce par l'agent lanceur et n'est ecrit dans aucun fichier du depot que j'aie lu** ; `git diff --name-only 90c421a7 1a810b79` ne liste que des fichiers `.md`. Que ce `verify` unique tienne lieu de `verify` du lot n'est ecrit nulle part ; `nadia-prof` l'a lu ainsi dans son verdict.

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
(aucun commit `[REL]` depuis le 2026-10-26, aucun fichier de releve dans le dossier du sprint)
**[corrige le 2026-10-29, texte d'origine conserve : le releve existe depuis le 2026-10-29, `f1de3616`, voir l'ajout du
2026-10-29 ci-dessous]**. **Ce Done ne couvre pas cet
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

### Ajout du 2026-10-29 (`nadia-prof`, s57, palier 5) : accord corrige, entree de catalogue ecrite, verdict inchange

Cet ajout ne reecrit ni le verdict du 2026-10-27 ni sa reserve. Lu sur `round-two` a `bdbbac13`.

1. **Le diff de l'accord.** Le commit `f7f9f443` de `marc-dev` (fusionne par `004cc7dc`) tient mes trois conditions :
   seuls les enonces des items 3 et 8 changent, egaux au texte du releve avec l'insecable avant *?* ; l'item 11, les
   propositions, les bonnes reponses et les indices sont inchanges ; hors du paquet ne changent que le test, son jeu fautif et
   les trois lignes de commentaire d'en-tete que `theo-archi` demandait. Preuves : `lecture-nadia-diff-accord-et-titre-ce1-qlm-generations.md`,
   section 1 (dossier du sprint, `9a1e3406`). A `bdbbac13`, les lignes 96 et 131 du paquet portent *de la plus âgée à la plus
   jeune*.
2. **L'entree de catalogue.** Le commit `095b9cd4` de `marc-dev` (fusionne par `bdbbac13`) ecrit l'entree
   `bandit-manchot-generations-ce1`, paquet `ce1-qlm-generations`, 10 manches, titre *"Les générations et les moments de la
   vie"*, consigne *"Lis bien, puis choisis la bonne réponse."*. Je les avais lus avant l'entree (meme fichier, section 3) ;
   `git merge-base --is-ancestor 9a1e3406 095b9cd4` reussit, et les deux chaines de `catalogue.yaml` sont egales a celles de
   ma lecture (comparaison sous Python). L'orthographe du titre et de la consigne est relue juste par le
   `relecteur-pedagogique` (`relecture-orthographe-entree-ce1-qlm-generations.md`, commit `108618b2`, branche
   `s57-p5-releco-12-titre`) : **cette branche n'est pas fusionnee dans `round-two` a `bdbbac13`**, et cette relecture a lu
   mon ecrit, pas la chaine de `catalogue.yaml` (sa section 3, point 1). **Je n'ai pas joue l'entree reelle** sur un jar : mon
   audit du 2026-10-27 a joue une entree de sonde.
3. **Ce que cela change a la section 3.** Le releve du `relecteur-pedagogique` (`releve-relecteur-palier-5-ce1-qlm-generations.md`,
   `f1de3616`) juge l'accord **fautif aux items 3 et 8** et juste a l'item 11 ; la correction est celle du point 1. La phrase
   *"Ce Done ne couvre pas cet accord"* reste vraie : la grammaire n'est pas de mon ressort, et c'est le releve, pas mon Done,
   qui la tient. **Mon Done reste VALIDE SOUS RESERVE**, reserve `V1` inchangee, aucun verdict d'item rouvert : la correction
   ne change ni le sens, ni le vocabulaire, ni la lisibilite que j'ai juges (18 mots avec l'insecable, `lecture-nadia-accord-ce1-qlm-generations.md`,
   `832e86b0`).
4. **Mention de couverture.** Relue : le verdict ne porte aucune mention de couverture partielle (*"`camille-po` peut brancher
   le paquet (`QC3`) et passer la ligne a `couvert-valide`"*), et la *"Limite de ce que le Done certifie"* (aucune frise
   dessinee) dit elle-meme ne pas etre une reserve. **Elle est inchangee** : la correction ne touche que l'accord.
5. **Plafond.** Selon moi, `couvert-valide`, avec `V1` recopiee comme reserve non bloquante. Je ne fais pas passer la ligne :
   c'est a `camille-po`, apres la CI complete verte.

**Ce dont je reste la seule garde** : inchange, section 5, plus ce que ma lecture du diff, section 2, garde a ma lecture
(qu'un enonce n'ecrive qu'une forme de sens). Je n'ai alloue aucun identifiant.

**Signe** : `nadia-prof`, s57, palier 5, 2026-10-29.

### Ajout du 2026-10-30 (`nadia-prof`, s57, cloture) : mesure de `V1`, reserve neuve `V2`, verdict maintenu

Cet ajout ne reecrit ni le verdict du 2026-10-27, ni la lettre de `V1`, ni l'ajout du 2026-10-29. Ecrit sur `round-two` a
`9aa3c860`.

**Ce qui change dans le paquet, et sous quelle condition.** L'utilisatrice a decide le 2026-10-30 : *"Q4 on fusionne et on
integre au catalogue"* (`RAPPORT.md` du sprint, section 7, `Q4` ; mots relayes par l'agent lanceur, non lus de ma main). La
correction est `180889a4` (`s57-cl-marc-12nb`, trait d'union insecable U+2011 dans les propositions), relue par `3e85dc33`
(`s57-cl-rel-12nb`). A `9aa3c860`, elle n'est pas fusionnee (`git merge-base --is-ancestor 180889a4 HEAD` echoue) et le paquet
est l'objet `976b8b1e`, celui de mon Done. **Tout ce qui suit vaut une fois `s57-cl-marc-12nb` fusionnee**, et pour le paquet
`116eb857` seulement : c'est l'objet de `180889a4` et celui de l'arbre que j'ai joue, `e75e3f7e` (`git rev-parse <commit>:<paquet>`).
Si la fusion livre d'autres octets, ma mesure ne les couvre pas.

#### 1. Mesure de `V1`, faite le 2026-10-29

Rappel de la lettre, inchangee : a 744 x 551 et a 360 de large, (1) aucune proposition coupee a l'interieur de
*arrière-grands-parents* ou de *grands-parents* ; (2) aucune reponse attendue sous le pli a la revelation.

| Arbre | Clause 1, 744 x 551 | Clause 1, 360 de large | Clause 2, 744 x 551 | Clause 2, 360 de large |
|---|---|---|---|---|
| avant correction (entrees reelles, paquet `976b8b1e`) | **non tenue** : item 8, deux propositions coupees *"grands-"* / *"parents"* | **non tenue** : coupures sur les items 1, 3, 4, 6, 8, 9 | **non tenue** : reponse nommee sous le pli aux items 3, 8, 9, 12 (582 a 710) | non mesuree |
| apres correction (`e75e3f7e`, paquet `116eb857`) | **tenue** : retours a la ligne apres une virgule ou une espace, item 8 *"arrière-grands-parents, grands-parents,"* / *"parents"* | **tenue** : aucune coupure dans le mot | **non tenue**, inchangee : items 3 (590), 8 (710), 9 (582), 12 (590) | non mesuree |

**`V1` est partiellement tenue une fois la correction fusionnee, et reste ouverte** sur sa clause 2. Je ne la leve pas, je ne
la modifie pas. La clause 2 est une question de gabarit de la revelation, pas du paquet : elle est dans
`S57-PLI-REVELATION-GABARIT-COMMUN`.

**Methode.** Avant correction : `avis-nadia-cloture-s57.md`, section 4 (jar du `verify` a `d9313f9e`, paquets egaux a ceux de
`a3e05f13`). Apres correction : `avis-nadia-corrections-cloture-s57.md`, sections 1 et 3 (jar construit par moi a `e75e3f7e`,
paquet du jar egal a celui de l'arbre). Dans les deux cas : `java -jar` sur un JDK 21, page *Questionner le monde* du CE1, tuile
par son titre, Chrome sans tete pilote par un script, parties ou chaque manche va jusqu'a la revelation, **les 12 items servis**
sur l'ensemble des parties ; coupures lues caractere par caractere, cotes par `getBoundingClientRect()`, police rendue par
`CSS.getPlatformFontsForNode`, captures regardees. A 360 apres correction : emulation mobile (barre superposee, 360 px de mise
en page) et emulation de bureau (barre classique, 345 px).

**Limites, dites.** (a) Navigateur sans tete et clics de script : ni tablette, ni telephone, ni doigt. (b) Une seule police
rendue, Segoe UI, sur un seul poste Windows ; la pile commence par `system-ui` : sous Android, iOS, macOS ou Linux, ni le glyphe
U+2011 ni la largeur du mot ne sont verifies. (c) A 360, la page defile seule : mes cotes a cette largeur ne disent rien du pli,
d'ou *"non mesuree"* dans la derniere colonne. (d) Lecture du U+2011 par un lecteur d'ecran non verifiee. (e) Technique,
perimetre partiel : `mvn -pl adapters/adapter-content -am test` a `e75e3f7e`, module `adapter-content` seul ; ce n'est pas la CI.

**Hors de la lettre de `V1`, et non ajoute a elle** : a 744 x 551, item 8, apres une erreur, ni croix, ni message, ni indice a
l'ecran (`avis-nadia-cloture-s57.md`, constat 1). Il est dans la story de gabarit ; je ne releve pas `V1` pour l'y mettre.

#### 2. Reserve neuve, datee du 2026-10-30 : `V2`, debordement du bouton a 360 de large

`V2` est un repere de cette fiche, comme `V1`, pas un identifiant alloue. Elle n'existe qu'une fois `s57-cl-marc-12nb` fusionnee :
c'est la correction qui la cree, en rendant *arrière-grands-parents* insecable.

- **Ce que l'enfant voit**, a 360 de large : le mot entier, mais plus large que son bouton. La premiere et la derniere lettre
  sortent du bouton clair et s'impriment sur le cadre sombre de la machine, texte sombre sur fond sombre. Items 1, 4, 9
  (*"celle des arrière-grands-parents"*) : 10 px de chaque cote hors du bouton (emulation mobile), 18 px (barre classique).
  Item 8, les trois propositions : 15 px (mobile) ; avec une barre classique, **le *a* initial passe sous le bord gauche de
  l'ecran**, l'enfant lit *"rrière-grands-parents,"*, et la page devient plus large que l'ecran a la revelation (361 pour 360).
  A la revelation, la bonne proposition reste non barree et lisible au centre, avec le meme debordement.
- **Gravite** : non bloquante. A 744 x 551, la taille de mes audits, aucun texte ne depasse son bouton. Avant la correction, la
  meme largeur coupait deja ces mots (clause 1 non tenue) : la correction remplace un defaut de 360 par un autre, elle n'en
  cree pas a 744.
- **Proprietaire** : la cause est de mise en page, pas de contenu : `theo-archi` (cause) et `ines-design` (mesure, levier de
  gabarit) ; la story est a `camille-po` ; l'engagement a `sofia-sm`. Le texte du paquet n'a pas a changer pour cela.
- **Condition de levee** : a 360 de large, en emulation mobile **et** avec une barre de defilement classique, sur les 12 items,
  a l'arrivee de la manche et a la revelation : (1) chaque lettre de chaque proposition est dans son bouton, sur le fond clair ;
  (2) aucune lettre n'est hors de l'ecran, et la page n'est pas plus large que l'ecran ; (3) la clause 1 de `V1` reste tenue.
  Mesure a faire par `ines-design`, avec la methode de la section 1. Si elle montre les trois, `V2` est levee sans rejeu de ma
  part ; si une taille de lettre est reduite pour y arriver, je lis la capture avant de lever.

#### 3. Reponse a `Q-C1` de `camille-po` (backlog, annotation de `S57-QLM-CE1-12-TRAIT-D-UNION-INSECABLE`)

*Question* : l'exigence 1 de `V1` tient-elle a 360, et que faut-il d'un mot qui ne peut plus se couper ; accepte-je une regle
de gabarit qui coupe un mot trop long dans un bouton ?

**Reponse : l'exigence 1 tient a 360, telle qu'ecrite. Je n'accepte pas une regle qui coupe *arrière-grands-parents* ou
*grands-parents* dans un bouton, ni entre deux lettres, ni a un trait d'union.** Motif, et c'est celui qui fondait `V1` : dans
ce paquet, un morceau de ces mots est lui-meme une reponse. *"celle des arrière-"* / *"grands-parents"* fait lire
*grands-parents* a la seconde ligne, qui est une autre generation ; dans les suites de l'item 8, *"grands-"* / *"parents"* fait
lire un terme de plus dans une liste de generations. Pour un enfant de CE1 qui lit ligne a ligne, la coupure change la
reponse. Une coupure entre deux lettres (*"arrière-grands-pa"* / *"rents"*) est pire : elle casse le decodage. Ce n'est pas
un relevement de ma barre : c'est la lettre de `V1` du 2026-10-27, que je maintiens.

**Ce que j'attends d'un mot qui ne peut plus se couper : qu'il tienne entier dans son bouton** (condition de levee de `V2`).
Le moyen n'est pas a moi ; ceux que j'accepte d'avance, sous reserve de lire la capture : une taille de lettre plus petite a
cette largeur seulement, des marges de bouton ou de cadre plus etroites, un bouton plus large. Un changement du texte des
propositions n'est pas le levier attendu ; s'il etait choisi, il repasserait par `marc-dev`, le `relecteur-pedagogique` et ma lecture.

#### 4. La ligne `QLM-CE1-12` reste `couvert-valide`

**Oui. Mon Done pedagogique reste VALIDE SOUS RESERVE, et il couvre le paquet `116eb857` une fois `s57-cl-marc-12nb`
fusionnee** : je l'ai joue a l'ecran, les 12 items servis, a 744 x 551 et a 360 de large, et la correction ne change ni un enonce, ni une bonne
reponse, ni un indice, ni le sens d'une proposition. Reserves ouvertes, toutes non bloquantes : `V1` (clause 2, pli a la
revelation a 744 x 551) et `V2` (debordement a 360). `camille-po` n'a rien a faire bouger dans la matrice ; la cellule de la
ligne recopie `V2` a cote de `V1`. Le Done technique sur le SHA fusionne reste la CI, que je ne lis pas ici.

**Ce dont je reste la seule garde, en plus de la section 5** : la coupure et le debordement a l'ecran des propositions de `-12`.
La garde de `180889a4` interdit le trait d'union ordinaire entre deux lettres dans une proposition ; elle ne mesure ni une
coupure de ligne ni un debordement de bouton. **Aucun test ne tient `V1` ni `V2`.**

Je n'ai alloue aucun identifiant : `V2` est un repere de cette fiche.

**Signe** : `nadia-prof`, s57, cloture, 2026-10-30.
