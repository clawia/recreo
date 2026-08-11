# [Sprint s08] Planning - 2026-08-11

- **Horodatage de debut** : 2026-08-11 11:44
- **Anime par** : sofia-sm
- **Participants** : camille-po, theo-archi, nadia-prof, ines-design (decisions et propositions
  deja rendues avant ce planning, citees ci-dessous), marc-dev, lea-dev, sofia-sm (developpement a
  venir), yanis-qa (traduction des criteres en tests au fil des commits).
- **Lu avant d'ecrire ce fichier** : `docs/backlog/backlog.md`, section "Sprint 8" (S8-1 a S8-4) ;
  `DEC-0017-perimetre-sprint-8-la-machine-retrouve-son-contexte-et-trois-verites-corrigees.md` ;
  l'arbitrage prealable de `nadia-prof` du 2026-08-11 (commit `cb4f40e`, quatre fiches de
  `squad/conformite/`) ; `ADR-0028-outiller-le-pli-le-contenu-au-dessus-du-pli-reste-non-gardable.md` ;
  `ADR-0029-toute-colonne-que-le-contenu-peut-produire-porte-son-decalage.md` ;
  `ADR-0030-un-habillage-entoure-sans-rogner-et-toute-animation-finit-sur-son-etat-de-repos.md` ;
  `ADR-index.md` et `DEC-index.md` a jour ; `squad/sprints/s08-cabinet-de-la-machine/
  proposition-ines-cabinet.md` ; `squad/programme/MATRICE-couverture.md` et
  `squad/validation/FILE-ATTENTE.md` corriges ; `adapters/adapter-content/src/main/resources/
  catalogue.yaml` (etat avant fusion : trois entrees) ; `outils/rapport-tokens.py` (`AGENTS_SQUAD`).
- **Lignee verifiee avant d'ecrire ce fichier** : `git merge-base` croise entre `round-two` et
  chacune des trois branches restant a fusionner (`s08-camille-po`, `s08-ines-design`,
  `s08-theo-archi`) : les trois descendent de `f29a2b0` et ne descendent pas l'une de l'autre, sans
  conflit de perimetre. **Ecart constate avant toute fusion, a porter en retro** : le depot
  principal (`round-two`) n'etait pas a `f29a2b0` a l'ouverture de cette ceremonie mais a `cb4f40e`
  - un commit `[NAD]` d'arbitrage prealable, deja present, sans branche `s08-nadia` sur le disque
  (recherche exhaustive, locale et distante : aucune trace). Le reflog local montre une ecriture
  directe (`commit:`), jamais une fusion. C'est la meme faute que celle nommee pour `camille-po` en
  s06 (`squad/README.md` section 9, etape 1 : "tout agent qui ecrit dans le depot ouvre un worktree
  dedie avant sa premiere ecriture, sans exception") : `nadia-prof` a ecrit son arbitrage
  directement dans le depot principal au lieu d'un worktree dedie. Perimetre verifie avant
  d'accepter ce commit tel quel : `git diff --stat f29a2b0 cb4f40e` ne touche que les quatre fiches
  de `squad/conformite/` (579 insertions, 4 suppressions), rien d'autre - comme en s06, rien n'a ete
  perdu parce qu'aucun autre lot ne recouvrait ce perimetre. Je ne fabrique pas de branche
  retroactive pour ce commit deja sur le disque : je le porte tel quel et je le nomme ici pour que
  la retro l'amende.

## Fusion des lots

Perimetres verifies par `git diff --stat` avant chaque fusion, pas sur la seule parole de chaque
agent. Le lot `nadia-prof` (arbitrage prealable, C1 a C6, budget mesure) etait deja sur `round-two`
avant l'ouverture de ce planning (`cb4f40e`, voir constat de lignee ci-dessus) ; les trois lots
suivants ont ete fusionnes dans cette ceremonie :

| Lot | Branche | Tete | Perimetre verifie |
|---|---|---|---|
| nadia-prof | (aucune - ecrit directement sur `round-two`) | `cb4f40e` | quatre fiches de `squad/conformite/` uniquement, deja present avant ce planning. |
| camille-po | `s08-camille-po` | `e9ac1e6` | `DEC-0017`, `docs/backlog/backlog.md`, `DEC-index.md`, `squad/programme/MATRICE-couverture.md`, `squad/validation/FILE-ATTENTE.md`. Aucun fichier de code. |
| ines-design | `s08-ines-design` | `5d03af4` | un seul fichier, `squad/sprints/s08-cabinet-de-la-machine/proposition-ines-cabinet.md`. |
| theo-archi | `s08-theo-archi` | `467cfac` | `recreo.css` (production, correctif S8-2), trois `ADR` neufs, `ADR-index.md`, deux tests neufs et un amende, contre-exemples de test, `outils/mesurer-rendu.sh` et sa suite. |

Fusionnes dans cet ordre (produit, design, architecture) par trois `git merge --no-ff` successifs,
**aucun conflit** : les quatre perimetres (le lot `nadia-prof` compris) ne se recouvrent a aucun
endroit. Tete de `round-two` apres fusion : **`12cd0a1`**.

### Porte technique verifiee avant ce planning

- `JAVA_HOME` positionne sur le JDK 21.0.10 designe par le fichier de toolchains le temps de la
  commande (`outils/verifier-jdk.sh` : `java` du `PATH` en 1.8.0_202, JDK de compilation 21.0.10,
  `JAVA_HOME` vide hors commande, toolchains non lu par Maven sous son nom present sur le disque).
  `mvn -B clean verify` : **BUILD SUCCESS**, six modules en SUCCESS.
- Compte de tests, methode : somme de l'attribut `Tests run` de chaque module dans le rapport
  reacteur, apres un `clean` : 43 (domain) + 7 (application) + 162 (adapter-web) + 36
  (adapter-content) + 77 (bootstrap, surefire) + 3 (bootstrap, failsafe) = **328**, conforme au
  chiffre annonce par `theo-archi` apres `ADR-0030`.
- Compte de regles ArchUnit, methode : recherche de `static final ArchRule` dans les fichiers
  `.java` suivis par Git, exclusion du faux module de test-ressources de
  `LesGardeFousSontMisALEpreuveTest` (jamais compile comme regle du depot) : **28** avant et apres
  fusion, inchange - aucune des clauses neuves de `theo-archi` n'en est une, ArchUnit lisant le
  bytecode et non une nouvelle declaration.
- Garde-fous de CI, joues localement : `verifier-prefixes-test.sh`, `verifier-chemins-test.sh`,
  `commit-atteignable-test.sh` - **tous verts**. Puis application des garde-fous eux-memes sur le
  contenu reel fusionne : `verifier-prefixes.sh` accepte tous les commits (y compris `[NAD]` ecrit
  hors branche dediee, qui porte neanmoins un prefixe connu) et `verifier-chemins.sh` ne trouve
  aucun chemin de poste ni nom de compte - **verts**.
- Suites `outils/*-test.sh` : le repertoire n'en contient **trois**, pas quatre
  (`mesurer-rendu-test.sh`, `verifier-jdk-test.sh`, `verifier-lignee-test.sh` - verification directe
  du repertoire, aucune quatrieme suite sur le disque). Les trois sont vertes.
  `mesurer-rendu-test.sh` est passe de 16 a 23 cas verifies (compte direct des lignes `ok`),
  conforme a l'annonce.

## Objectif du sprint (gele)

**A la review, l'ecran de manche montre une machine reconnaissable - un cabinet visible qui
entoure les colonnes, sans aucun code de jeu d'argent - le decor de levier quitte les boutons de
choix de jeu de l'accueil qui n'en portent plus le sens, la fin du mouvement des colonnes ne
disparait plus d'un coup, et l'explication d'une variation graphemique se distingue visuellement de
la consigne ; "Le bon verbe" rejoint le catalogue jouable et son paquet s'etend au futur et a
l'imparfait sous la barre que `nadia-prof` a ecrite elle-meme ; aucun plancher pedagogique deja
gagne ne recule, et `nadia-prof` rend son verdict en jouant chaque increment livre.**

**Gele** : aucune ligne de `squad/programme/MATRICE-couverture.md` ne change de statut hors la
ligne francais CM1 conjugaison, qui ne passe a `couvert-valide` que si `nadia-prof` leve elle-meme
sa barre fermee apres avoir joue l'increment de S8-4 (`DEC-0017`, chantier 4). Le defaut de pli de
"Le bon petit mot" et les referentiels de matieres non encore ouverts restent hors perimetre
(`DEC-0017`, "Ce qui reste dehors, explicitement").

## Ce que ce planning rend lisible pour un developpeur

**Le cabinet.** Coffret a coins arrondis en trois bandes empilees (bandeau 2 rem, fenetre =
`.propositions` inchangee, socle 1 rem), cadre ferme de 0,5 rem sur les quatre cotes, une seule
couleur (`var(--recreo-entete-fond)`) pour tout le corps afin qu'il se lise comme un objet plein.
`.propositions` est enveloppe dans un `<div class="cabinet">` avec deux freres decoratifs
(`.cabinet-bandeau`, `.cabinet-socle`) portant `aria-hidden="true"` ; consigne, enonce et indice
restent hors du cadre. Une seule image decorative : une etoile blanche dessinee en `clip-path` sur
`.cabinet-bandeau::before`, jamais un fichier (`content: url(...)` deja rouge par ArchUnit ;
`RessourcesTiercesTest` interdit toute ressource externe).

**Piege identifie dans la proposition d'`ines-design`, a ne pas recopier tel quel.** Sa feuille de
style proposee (`proposition-ines-cabinet.md`, section 1) pose `overflow: hidden` sur `.cabinet`
pour que les coins arrondis rognent les enfants. `theo-archi` a construit
`LHabillageNeRogneJamaisUnElementPressableTest` (`ADR-0030`) en reprenant cette feuille telle
quelle comme contre-exemple fautif : la bibliotheque vendorisee peint l'anneau de focus clavier en
ombre portee hors de la boite du bouton, et les colonnes occupent toute la largeur interieure du
cabinet - `overflow: hidden` sur `.cabinet` rend donc cet anneau invisible sur la premiere et la
derniere colonne, et fait du cabinet un conteneur de defilement. Le correctif verifie vert par
`theo-archi` est le simple retrait de cette unique declaration : le cadre, le fond, les coins
arrondis, le bandeau et le socle restent tous intacts sans elle. **Le developpeur qui implemente
S8-1 n'ajoute donc pas `overflow` sur `.cabinet`, sous aucune valeur autre que `visible`.**

**Le budget est epuise.** `336 = 3 x 101,33 + 2 x 16` a 360 px. Seul payeur autorise : la largeur
des colonnes (101 -> 96 px a trois, 160 -> 152 px a deux). Refuses : le `gap` de 1 rem (tenu a
l'egalite exacte), le rembourrage lateral de `.proposition`, `.container` comme unique payeur d'un
flanc. Planchers de colonne : 77, 79, 106 px selon le paquet. Verticalement, 150 px jusqu'au pli
est une limite dure, pas une cible ; `ines-design` mesure 63 px de surcharge sur le cas le plus
charge du catalogue (87 px de marge sous la limite dure, 37 px sous la cible de 100 px).

**Trois contraintes de structure d'`ADR-0030`** : les trois classes du cabinet (`.cabinet`,
`.cabinet-bandeau`, `.cabinet-socle`) portent chacune une regle de la feuille, une bande
decorative sans regle est refusee ; aucun `th:if` sur le cabinet - un habillage conditionnel
ferait entrer sa condition dans l'appariement de saillance de tous les boutons qu'il contient ;
le cabinet vit dans le balisage de `fragments/manche.html` uniquement, jamais un pseudo-element
sur `ecran-de-jeu` ni sur la racine d'un fragment - c'est cette absence de balisage ailleurs, et
non une regle de masquage, qui garantit que le cabinet n'atteint ni l'ecran de retour ni l'ecran
de fin de partie.

**Le cabinet ne va pas sur l'ecran de retour ni de fin de partie.** `nadia-prof` : c'est l'ecran ou
l'enfant apprend, le seul dont la nudite est un avantage. Elle assume que la machine disparaisse
dix fois par partie.

**Le disque decoratif quitte `.demarrer` et `.encore`.** La forme ronde et l'ombre de relief
restent sur les deux. C'est tranche par `nadia-prof` (arbitrage du 2026-08-11), pas a reinstaller.

**L'explication (S8-3)** : aplat et cadre complet, jamais un liseret ; aplat neutre identique
apres juste et apres faux (une explication sur fond rouge apprend que comprendre est une
punition) ; 1,25 rem tenu ; verdict et icone de 3 rem inchanges. `ines-design` propose deux
variables neuves (`--recreo-explication-fond`, `--recreo-explication-bordure`), distinctes de
l'ambre de `.indice` et du vert/rouge du verdict.

**S8-2 est deja livre, porte comme acquis.** La regle `@keyframes rouleau-glisse` terminait sur
`inset(80% 0 0 0)` : `top` et `bottom` progressaient au meme rythme, la bande gardait une hauteur
visible constante de 20 % jusqu'a la derniere image, et l'animation rendait la main sur un
cinquieme de boite encore sombre (13 px mesures par `nadia-prof`, sa reserve unique de s07).
Corrige par `theo-archi` en changeant la seule valeur d'arrivee de `top`, `inset(100% 0 0 0)` :
la hauteur visible decroit desormais jusqu'a 0 sur la derniere image, qui devient identique a
l'etat de repos. Gardee par `LAnimationRetardeMaisNeDetermineRienTest` etendu (`ADR-0030`).

**Les douze acquis dont `nadia-prof` exige le maintien**, dont l'ecran de fin de partie nu (aucun
cabinet, cadre, colonne, levier, disque, animation ni image de machine), et **six points de garde
solitaire neufs qui entrent avec ce chantier** : contour ferme, absence d'animation sur le cabinet,
identite juste/faux, invariance des images, payeur du budget horizontal, continuite de fin de
mouvement. `nadia-prof` en garde desormais plus qu'avant.

## Sequencement et arbitrage de capacite

Deux lots de developpement, sans dependance de fichier entre eux, plus deux gestes isoles :

1. **Lot cabinet (S8-1 + S8-3) - priorite la plus haute, ne cede pas.** S8-1 et S8-3 touchent le
   meme fichier (`recreo.css`) et le meme gabarit (`fragments/manche.html`) : un seul lot, un seul
   agent, pour eviter tout conflit sur la meme zone. C'est la reponse directe au second constat de
   l'utilisatrice ("il manque une machine dans le contexte") et le chantier le plus charge en
   contraintes (budget epuise, piege `overflow` deja identifie ci-dessus). L'avis de faisabilite
   prealable est deja rendu par `theo-archi` sous forme d'`ADR-0030`, et l'arbitrage prealable
   pedagogique par `nadia-prof` (commit `cb4f40e`) : les deux gestes prealables de
   `squad/README.md` section 9 etape 1 et section 10 sont donc deja faits, le developpement peut
   s'ouvrir sans attente.
2. **Lot conjugaison (S8-4 + activation du catalogue) - priorite secondaire, cede en premier si la
   fenetre est courte.** Isole sur `adapter-content` (paquet YAML `cm1-conjugaison-present` et
   `catalogue.yaml`), aucun fichier commun avec le lot cabinet. Il cede en priorite parce qu'il
   etend une couverture deja `en cours` sans repondre a un defaut signale par l'utilisatrice, pas
   parce qu'un conflit reel l'exigerait : les deux lots peuvent demarrer au meme instant.
3. **`AGENTS_SQUAD` d'`outils/rapport-tokens.py` - geste isole, capacite propre de `sofia-sm`.**
   Aucune dependance avec les deux lots ci-dessus.

## Stories et gestes retenus

| Story / geste | Titre | Competence visee | Affecte a | Sequence |
|---|---|---|---|---|
| S8-2 | La fin du mouvement des colonnes se termine sans discontinuite visible | transverse | `theo-archi` | **deja livre**, porte comme acquis au gel (`inset(100% 0 0 0)`). |
| S8-1 | Un cabinet reconnaissable autour des rouleaux, et le levier quitte l'accueil | transverse, habillage de la mecanique | `lea-dev` | lot cabinet, ne cede pas |
| S8-3 | L'explication d'une variation graphemique se distingue visuellement de la consigne | transverse, lisibilite CP | `lea-dev` | lot cabinet, meme lot que S8-1 |
| Activation de "Le bon verbe" (`catalogue.yaml`, quatre entrees) | execution du chantier 2 de `DEC-0017` | francais CM1, conjugaison present (code d'`adapter-content`, aucun jugement pedagogique neuf) | `marc-dev` | lot conjugaison, avant S8-4 |
| S8-4 | Etendre "Le bon verbe" au futur et a l'imparfait de l'indicatif | francais, CM1 (cycle 3), "Conjuguer les verbes frequents au present, futur et imparfait de l'indicatif" | `marc-dev` | lot conjugaison, cede en premier si la fenetre est courte |
| Correction d'`AGENTS_SQUAD` (`outils/rapport-tokens.py`) - `ines-design` manquante | outillage interne, casquette dev | `sofia-sm` | geste isole |

`yanis-qa` traduit chaque critere d'acceptation retenu en test au fil des commits des deux
worktrees et fait tourner la suite. `theo-archi` reste disponible tout le sprint pour tout
obstacle technique remonte en cours de developpement, en particulier sur le respect de la clause
`overflow` d'`ADR-0030` a l'implementation reelle du cabinet.

## Obstacles a ce planning

| Description | Categorie | Destinataire | Statut |
|---|---|---|---|
| Ecart de lignee : le lot `nadia-prof` a ete ecrit directement sur `round-two` sans worktree ni branche dediee (voir constat en tete de ce fichier) | gouvernance / methode, meme faute que celle nommee pour `camille-po` en s06 | `sofia-sm` (moi, pour la retro) | **non bloquant, contenu verifie conforme** ; a porter en retro pour amender `squad/README.md` si la regle merite un garde-fou plutot qu'une seule consigne en prose. |
| Dette nommee par `theo-archi` : la condition C4 (le cabinet est identique apres juste et apres faux) est garantie par la structure du gabarit (aucune regle ne conditionne `.cabinet` a `.signal.est-juste` ou `.signal.est-faux`) mais n'est gardee par aucun test ArchUnit ou structurel ; `theo-archi` refuse d'ecrire une clause qui nommerait `.cabinet` avant que la classe n'existe dans le depot (perimetre enumere et nom cite avant d'exister - dette n°1 de `CLAUDE.md`) | technique, dette de test | `theo-archi` (a rouvrir une fois S8-1 livre) | **inscrite, non fermee.** Une fois `lea-dev` livre `.cabinet` dans `fragments/manche.html`, `theo-archi` ou `yanis-qa` ecrit la clause structurelle equivalente a C4 ; jusque-la, C4 reste verifiee par lecture, pas par CI. |
| Constat de methode de `theo-archi` : un garde-fou qui lit le classpath lit ce qui est installe, pas ce que le depot declare - sa premiere ecriture d'`ADR-0029` etait verte du premier coup sur une population vide (artefact perime du depot local masquant la vraie feuille), attrapee par la clause de non-vacuite d'`ADR-0003` | methode / retro | `sofia-sm` (a porter en retro) | **non bloquant**, sans action de developpement ce sprint. |
| `ADR-index.md` avait derive : les deux `ADR` de s07 etaient ranges sous l'intertitre de s06 | documentation | `theo-archi` | **ferme**, corrige dans le lot fusionne ci-dessus. |
| Defaut de pli sur l'item le plus long des homophones avec son indice (deux boutons a `y = 717` et `y = 790` a 360 x 640, sous le pli) | produit | `camille-po` (deja tranche, `DEC-0017`) | **hors perimetre du sprint 8**, desormais reproductible par une commande versionnee (`outils/mesurer-rendu.sh`) plutot que par une mesure a la main. Reste une intention non ouverte au backlog. |

## Decisions prises

Aucun `ADR` ni aucune `DEC` n'est cree pendant ce planning : `DEC-0017`, `ADR-0028`, `ADR-0029` et
`ADR-0030` sont anterieurs et deja sur le disque, fusionnes ci-dessus. Les decisions prises ici
m'appartiennent en tant que Scrum Master et n'ouvrent ni `ADR` ni `DEC` :

1. **L'ordre des deux lots et celui qui cede en cas de fenetre courte** (section "Sequencement et
   arbitrage de capacite" ci-dessus) : le lot cabinet (S8-1, S8-3) ne cede pas, le lot conjugaison
   (activation du catalogue, S8-4) cede en premier.
2. **Les affectations nominatives** de la section "Stories et gestes retenus" ci-dessus : `lea-dev`
   porte le lot cabinet en un seul bloc, `marc-dev` porte l'activation du catalogue puis S8-4,
   `sofia-sm` porte la correction d'`AGENTS_SQUAD`.
3. **S8-2 est portee comme deja livree** au gel de cet objectif : aucune capacite de developpement
   ne lui est reaffectee ce sprint.
4. **L'ecart de lignee du lot `nadia-prof` est accepte tel quel** (contenu verifie conforme au
   perimetre attendu) plutot que corrige retroactivement par une branche fabriquee apres coup, et
   porte a la retro.

## Prochaines etapes

**Demarrent maintenant** :

- `lea-dev` ouvre un worktree dedie, verifie sa lignee (`outils/verifier-lignee.sh`), enchaine
  S8-1 et S8-3 en un seul lot sur `fragments/manche.html` et `recreo.css`, en suivant la
  proposition d'`ines-design` a l'exception de la clause `overflow` refusee par `ADR-0030` (voir
  section "Ce que ce planning rend lisible pour un developpeur"), tests dans le meme commit.
- `marc-dev` ouvre un worktree dedie, verifie sa lignee, active "Le bon verbe" dans
  `catalogue.yaml` (quatre entrees), puis etend `cm1-conjugaison-present.yaml` au futur et a
  l'imparfait selon les six points repris au mot pres de la barre fermee de `nadia-prof`, tests
  dans le meme commit.
- `sofia-sm`, casquette dev, corrige `AGENTS_SQUAD` dans `outils/rapport-tokens.py` pour y inclure
  `ines-design`, avec le test correspondant dans le meme commit.
- `yanis-qa` traduit chaque critere en test au fil des commits des trois worktrees.
- `theo-archi` reste disponible pour tout obstacle technique remonte en cours de developpement.

**Une fois les lots fusionnes sur `round-two`** : `nadia-prof` joue l'increment du cabinet (S8-1,
S8-2, S8-3) de bout en bout et rend son verdict ; elle joue separement l'increment de S8-4 et
prononce ou non la levee de sa barre fermee sur la conjugaison.

**Une fois l'arbre fusionne et les verdicts rendus** : `yanis-qa` fait tourner `mvn verify` sur
JDK 21 et ecrit le rapport qualite (y compris la fermeture ou non de la dette C4 nommee
ci-dessus) ; `theo-archi` confirme ArchUnit ; `relecteur-pedagogique` releve les livrables non
executables ; `sofia-sm` ecrit `review.md` puis `RAPPORT.md` et lance le rapport de tokens.
