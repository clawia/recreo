# s57, palier 2, instance A' - rejeux de `nadia-prof` : p2-8 `TPH-CE1-R1`, puis p2-9 `GNA-CE1-R1`, sur le meme jar

- **Auteure** : `nadia-prof`, professeure des ecoles, seule autorite sur le Done pedagogique.
- **Date** : 2026-10-16, 15:51 (UTC+02:00), lu par `date`.
- **Arbre** : `round-two` a `e8d14ddb`, branche `s57-nadia-p2-ap`, worktree dedie, `git status --short` vide avant ecriture.
  Code servi : celui de `0c0d2142` (`git diff --quiet 0c0d2142 HEAD -- adapters domain application bootstrap pom.xml outils`
  sort 0).
- **Type** : rejeu borne de deux reserves (`PAR-10`), dans l'ordre de `planning.md`, amendement du 2026-10-16 15:27, section 6.
  Audit joue : jar construit et joue par moi.

## Resume

| Poste | Reserve | Verdict | Fiche |
|---|---|---|---|
| p2-8 | `TPH-CE1-R1` | **levee** ; `FRA-CE1-10` passe a **VALIDE** | `squad/conformite/FICHE-bandit-manchot-phrases-types-ce1.md`, rejeu du 2026-10-16 |
| p2-9 | `GNA-CE1-R1` | **levee** ; `FRA-CE1-15` passe a **VALIDE** | `squad/conformite/FICHE-bandit-manchot-accord-groupe-nominal-ce1.md`, rejeu du 2026-10-16 |

Aucune reserve neuve. Deux conditions regardees au rejeu (`reponse-nadia-q-palier-2.md` 3.1 et 3.2) : **tenues**. Clause
`Q-R4` : **tenue**, sur la cote d'`ines-design`, que je cite et ne remesure pas en entier (section 3).

**Prix** : p2-8 **0,5** (inchange). p2-9 **0,25** : la forme de `ce1-accord-groupe-nominal` ne differe pas de celle de
`ce1-phrases-types` (memes regles de `recreo.css`, meme jar, paquet servi inchange, section 4). La composition retenait 0,5 :
**0,25 revient a l'ecart non affecte de A'**. Le compte est a `sofia-sm` et `camille-po`.

## 1. Ce que j'ai lance

| Mesure | Commande ou geste | Rendu |
|---|---|---|
| Jar | `bash outils/mvn-jdk21.sh -B clean package -DskipTests`, racine du worktree | `BUILD SUCCESS` ; Maven sur le JDK de la toolchain, 21.0.10 ; tests sautes |
| Empreinte du jar | `sha256sum bootstrap/target/recreo.jar` | `db419e94b685ba3d37591b853bfd7decdf9fdf55e8516defe0f7b5e9785c48d7` |
| Lancement | `java -jar bootstrap/target/recreo.jar --server.port=18512` | *"Tomcat started on port 18512"*, PID 27416 lu dans le journal ; machine virtuelle 21.0.12.1 (ligne *"Starting RecreoApplication"*) |
| Ports avant | `netstat -ano` filtre sur 18512 et 19212 | rien |
| Feuille servie | `curl /css/recreo.css`, retours chariot retires, `sha256sum` | `c312cd1b...3ef6`, egal a la feuille du sommet de la cote (section 1) |
| Navigateur | Chrome 155.0.8059.40 sans tete, profil neuf dans mon dossier de poste, port de debogage 19212 | arrete par son PID ; aucun processus restant dont la ligne de commande nomme mon dossier |
| Arret du jar | `taskkill` sur le PID 27416 seul | aucune ecoute sur 18512 ni 19212 ensuite |

Le jar n'a pas l'empreinte de celui de la cote (`61874630...`) : un jar ne se reconstruit pas a l'octet (cote, section 11). Ce
qui l'identifie au jar mesure : le meme code (`git diff --quiet` ci-dessus) et la meme feuille servie. La machine virtuelle qui
sert le jar (21.0.12.1) n'est pas celle de la cote (21.0.10) ; le rendu est celui du navigateur, et je le dis sans en tirer plus.

**Jeu** : par les boutons servis, depuis `/niveaux/CE1/matieres/francais`, comme un enfant : premiere erreur, seconde erreur,
troisieme appui sur une proposition barree (la revelation), puis la bonne reponse ; sur un item deja vu, la bonne reponse au
premier coup une manche sur deux, sinon une erreur puis la bonne. Mesure : `getBoundingClientRect()` de `.indice` apres la
premiere erreur, a 720, 744 et 768 x 551, dans l'ordre servi et dans les six ordres (boutons reranges puis remis) ; captures a
744 x 551 de chaque item apres la premiere erreur, que j'ai regardees.

| Jeu | Parties | Items | Erreurs | Revelations | Bonne au premier coup | Ecarts au fichier |
|---|---|---|---|---|---|---|
| `bandit-manchot-phrases-types-ce1` | 7 | 24 sur 24 | 98 | 24 | 20 | 0 |
| `bandit-manchot-accord-groupe-nominal-ce1` | 6 | 24 sur 24 | 90 | 24 | 18 | 0 |

*Ecart* : enonce ou propositions non retrouves dans le paquet, indice avant la premiere erreur, indice different du fichier
apres la premiere ou la seconde erreur, reponse revelee differente de la bonne reponse, score inchange apres une bonne reponse.

## 2. Ce que le jeu a montre

### 2.1 Le critere de levee

| Jeu | Largeur | `.indice` au plus 551, ordre servi | Six ordres | Bas le plus haut | Avant la forme (fiche) |
|---|---|---|---|---|---|
| phrases-types | 720 | 24 / 24 | 24 / 24 | 548 | - |
| phrases-types | 744 | **24 / 24** | 24 / 24 | 548 | 0 / 24 entierement visibles |
| phrases-types | 768 | 24 / 24 | 24 / 24 | 548 | - |
| accord-groupe-nominal | 720 | 24 / 24 | 24 / 24 | 548 | - |
| accord-groupe-nominal | 744 | **24 / 24** | 24 / 24 | 548 | 1 / 24 |
| accord-groupe-nominal | 768 | 24 / 24 | 24 / 24 | 548 | - |

Mes chiffres a 744 egalent ceux de la cote sur le sommet (section 5 : 24 / 24, maxima 548 et 548). Le bas le plus haut, 548, est
celui des items a trois rangees (quatre de reconnaissance sur `phrases-types`, *"des manteaux chauds"* sur
`accord-groupe-nominal`) : sur la capture, le cadre de l'indice est entier, trois pixels au-dessus du bord.

**Item 5 d'`accord-groupe-nominal`** (*"les cheveux longs"*, valeur transitoire de 355,8 de la cote, section 7) : **474** a 744
dans les six ordres et a 720, `.indice` a gauche 276, a droite 720. Je ne reproduis pas 355,8 ; sa cause reste non trouvee, et
elle n'est pas a moi.

### 2.2 Ce que l'enfant voit apres une erreur

Mesure sur les 48 items, et regard sur cinq captures (items 2, 4 et 7 de `phrases-types`, 5 et 9 d'`accord-groupe-nominal`, les
plus bas et l'item de la valeur transitoire) : la proposition choisie est barree a sa place ; sous le meuble, a gauche, la croix et
*"Ce n'est pas ça."* puis *"Essaie encore !"* sur deux lignes, coupure entre les deux phrases sur les 48 items (mesure ; le regime
que j'ai accepte pour le poste 8) ; a droite, l'indice dans son cadre, en entier, sans defiler. L'enfant lit de gauche a droite :
le verdict, puis l'aide. **C'est ce que la reserve demandait** : l'enfant bloque voit l'indice sans le chercher.

**Observation sans reserve** : la colonne de droite fait 444 px a 744, et cinq indices distincts y laissent un mot seul sur la
seconde ligne (*"phrase."*, *"groupes."*, *"dernier."*, *"plusieurs ?"*, *"féminin ?"*, ces deux derniers avec leur signe). Deux
lignes courtes se lisent en CE1, aucune coupure ne tombe dans un mot ni ne separe un signe de son mot. Ce n'est pas un motif.

### 2.3 Condition de `R3-1` (`reponse-nadia-q-palier-2.md` 3.1) : tenue

A 744 et a 720 : la bande de l'en-tete finit a y = 82 ; la boite du score commence a 82, la boite du texte du score a 83. Sur
15 points repartis sur le texte du score, `elementFromPoint` rend le score sur les 15, sur les 48 items, a 720, 744 et 768. Sur la capture, les
lettres de *"Score : 2"* sont sur le fond clair du jeu, separees de la bande, alignees a gauche sur la consigne qu'elles
precedent : le score se lit comme une ligne du jeu. Aucune lettre couverte ni touchee.

### 2.4 Condition de `PA4` a 720 (`reponse-nadia-q-palier-2.md` 3.2) : tenue

A 720, entre la premiere erreur et la revelation, **aucun libelle ne change de nombre de lignes** : 144 libelles (48 items, trois
propositions), une ligne avant, une ligne apres. Aucun debordement a droite (`scrollWidth` egale `clientWidth`) a 720, 744, 768
apres l'erreur, ni a 744 a la revelation.

### 2.5 La revelation, hors levee (`Q-R2`)

A 744, *"Appuie sur la bonne réponse :"* et la reponse sont visibles sur les 48 items (bas de la reponse au plus 550) ; l'indice,
qui reste affiche sous la reponse, passe sous le pli sur 46 items (560 a 634), et reste au-dessus sur deux (530, *"Le vent souffle
fort."* ; 486, *"les gâteaux"*). La fiche de `phrases-types` notait la phrase de revelation et la
reponse sous le pli : c'est un gain. Le pli de l'indice a la revelation n'est pas dans la levee, et je ne l'y mets pas.

### 2.6 Contenu

Les deux paquets n'ont change depuis les commits de mes verdicts (`1f6642ab`, `72931c94`) que par deux lignes de commentaire
chacun (`29d02bc6`, `D5`) ; aucun item servi ne change, et les deux entrees du catalogue n'ont perdu aucune ligne. Joues au
jar : 0 ecart au fichier (section 1). Mes constats d'exactitude des fiches (sections 3) valent toujours ; aucune lecture complete
neuve n'est due, aucun item n'ayant change.

## 3. Clause `Q-R4`, sur la cote ecrite

**Source** : `cote-de-reference-forme-commune-2026-10-16.md`, commit `96f5e101` (`ines-design`, fusionne par `056f8c88`),
inchangee depuis (`git diff --quiet 96f5e101 HEAD` sur ce fichier sort 0). Base `aa9d703c`, sommet `0c0d2142`. Ce que je n'ai pas
mesure, je le cite de ce fichier.

| Element | Ce que dit la cote | Mesure par moi | Sortie de la clause |
|---|---|---|---|
| 1, `ce1-verbe-sujet`, 22 items a 534 | 418 a 448, 22 sur 22, six ordres (4.1) | non (p2-11, instance A) | aucun item perdu |
| 1, *"les gâteaux"* | 486 -> 400 (4.1) | **oui** : item 16 d'`accord-groupe-nominal`, 400 a 720, 744, 768 | perdu : non |
| 2, `ce1-texte-narratif`, 11 items a 550 | 434, 11 sur 11, six ordres (4.2) | non | aucun item perdu |
| 3, `additions-cp`, 65 enonces | 486 -> 370, 65 sur 65 (4.3) | non | aucun item perdu |
| 4, `comprehension-cm1`, 20 items | 0 item perd le pli ; l'item 0 passe de 550 a 464 (4.4) | non | aucun item perdu |

**La clause est tenue** : tout item dont l'indice finissait au plus 551 sur le jar de base y reste sur le sommet. Je ne pose aucune
barre sur les items deja sous le pli (`comprehension-cm1`, 11 sur 20 ; items 13, 17 et 18 de `ce1-texte-narratif`, 552 au pire
ordre) : ils restent ce qu'ils sont, et `RCT-CE1-R1` reste ouverte.

**`Q-R5`, a la lettre et sur le fond.** Base : `aa9d703c`, parent du premier commit de la forme (`git merge-base --is-ancestor
aa9d703c dc6f423d` sort 0, cote section 1 ; `planning.md` 15:27, section 2). Seconde commande : **tenue pour `dc6f423d`, non tenue
pour le sommet que je joue** (22 fichiers, dont deux paquets neufs et `catalogue.yaml`). Je tranche le point que la cote me laisse
(section 2) : **la cote vaut pour le jar que je joue.** Motif : la seconde commande sert a imputer a la forme un item perdu entre
les deux jars ; ici, la comparaison est faite item par item sur le sommet lui-meme, et elle ne perd aucun item. Les ajouts du
sommet ne touchent aucun des six paquets de la cote ni aucun gabarit (`git diff --stat aa9d703c HEAD` sur ces six paquets et sur
`templates` : vide, lu par moi) ; sous `adapters/adapter-web/src/main`, seul `recreo.css` change. Je ne juge pas a la place d'une
mesure : la mesure existe, sur l'ecran que l'enfant aura. Si un item avait ete perdu sur le sommet, il aurait fallu le rejouer sur
`dc6f423d` pour savoir qui l'a perdu.

**Ce dont la cote ne parle pas** : sa section 9 (largeurs d'`additions-cp`, polices, appareils, lecteur d'ecran, jeux hors de
l'echantillon). Rien de cela n'entre dans la clause telle que je l'ai ecrite.

## 4. Pourquoi p2-9 vaut 0,25

Mon bareme : 0,25 quand le paquet ne change pas et qu'aucune forme neuve n'est a juger ; 0,5 si la forme de ce paquet differe
(`arbitrage-nadia-palier-2.md` 5 ; `reponse-nadia-forme2-palier-1.md` 5.1). **Constats** : meme jar ; memes regles de la forme
(elles visent `.ecran-de-jeu:has(.propositions):not(.ecran-deux-oeuvres)`, aucune ne nomme un paquet) ; meme disposition du
message a l'ecran (2.2) ; paquet servi inchange (2.6). Rien de neuf n'etait a juger sur `GNA` apres `TPH`.

## 5. Ce dont je reste la seule garde

| Propriete | Pourquoi aucun test ne la tient |
|---|---|
| La position de `.indice` par rapport au pli sur ces deux jeux | aucun automate du reacteur ne rend une page ; la cote est une mesure datee, pas un garde-fou |
| Que le score se lise comme une ligne du jeu, `R3-1` | une cote ne dit pas si deux blocs se confondent |
| Qu'aucun libelle ne change de lignes a la revelation a 720 | rien ne le mesure a chaque commit |
| Que le verdict sur deux lignes coupe entre ses deux phrases | aucun test ne lit la coupure rendue |

`LaFormeCommuneDesPropositionsLonguesEstEpingleeTest` epingle le texte des regles ; il ne rend aucune page. Une regle voisine qui
ajoute une rangee repasserait la CI.

## 6. Routage

| A qui | Quoi |
|---|---|
| `camille-po` | `FRA-CE1-10` et `FRA-CE1-15` : reserve levee, VALIDE ; stories `S57-TPH-CE1-R1` et `S57-GNA-CE1-R1` closes par ce rejeu |
| `sofia-sm` | les deux verdicts pour la cloture du palier 2 ; prix : p2-8 0,5, p2-9 0,25, 0,25 rendu a l'ecart non affecte de A' |
| `ines-design`, `theo-archi` | la valeur de 355,8 non reproduite (2.1) ; rien a reprendre sur la forme pour ces deux jeux |
| `yanis-qa` | 5 : aucune propriete de la levee n'a de garde executable |

## 7. Ce que je n'ai pas joue

- `ce1-verbe-sujet` (p2-11, instance A), `ce1-texte-narratif`, `additions-cp`, `comprehension-cm1` : cites de la cote (section 3).
- Les largeurs 992 et 1200, les polices autres que celle du poste, un appareil reel, un lecteur d'ecran, un autre navigateur.
- Le jar de `dc6f423d`.

## 8. Ce que ce document ne fait pas

Il ne modifie ni code, ni paquet, ni catalogue, ni matrice. Il n'ecrit et n'alloue **aucun identifiant de condition ou de
reserve** : `TPH-CE1-R1` et `GNA-CE1-R1` sont deja alloues, leur levee n'en cree pas.

**Tarif consomme** : 0,75 sur A' (0,5 et 0,25). Marge de rejeu de A' intacte.

**Signe** : `nadia-prof`, s57, palier 2, instance A', 2026-10-16.

## 9. Jobs de `ci.yml` lances pour ce commit

Sur ce worktree, les trois fichiers du commit dans l'index, avant commit. Je ne deduis aucun job que je n'ai pas lance.

- **`chemins`** : `verifier-chemins-test.sh` sort 0 (*"Tous les cas passent : le garde-fou de chemins mord, et sur le bon
  motif."*) ; `git ls-files` (moins le test du garde-fou) passe a `verifier-chemins.sh` : *"Aucun chemin de poste de travail ni
  nom de compte dans les fichiers verifies."*, sortie 0.
- **`prefixes`** : `verifier-prefixes-test.sh`, `commit-atteignable-test.sh` et `verifier-garde-fou-accent-test.sh` sortent 0 ; le
  sujet du commit passe dans `verifier-prefixes.sh` : *"Tous les commits portent un prefixe d'agent connu."*, sortie 0. Le controle
  du proprietaire du garde-fou de l'accent n'est pas lance : aucun de ses chemins n'est touche.
- **`build`, partiel** : `bash outils/mvn-jdk21.sh -B -pl bootstrap -am test` restreint a
  `ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest`, `AucuneLigneDeTableauNePortePlusDeCellulesQueSonEnTeteTest`,
  `ToutesLesReferencesDeDecisionResolventTest` et `ConcordanceDesTagsDAgentTest`, perimetre `bootstrap` et ses amonts :
  `BUILD SUCCESS`, 19 tests (10, 5, 3 et 1), 0 echec. Aucun identifiant n'est ecrit ni alloue. Le reste du `build` n'est pas
  lance : ce commit ne touche ni code ni contenu servi. Le jar joue a ete construit par `clean package -DskipTests` (tests sautes).
  Le build a lu ce fichier avant l'ajout de cette section, qui ne contient aucun tableau.
- **`outils`** : non lance ; aucun outil n'est modifie.

Aucun de ces resultats n'est celui de la CI distante, que je n'ai pas lue. Rien n'est pousse.
