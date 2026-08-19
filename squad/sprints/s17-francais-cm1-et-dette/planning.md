# [Sprint s17] Planning - francais CM1 non couvert et la dette

- **Horodatage de debut** : 2026-08-19 18:25 +0200
- **Anime par** : sofia-sm, casquette Scrum Master.
- **Ordre du process respecte, dans son ordre normal et complet** (`squad/README.md`, section 9,
  etape 1) : `camille-po` a propose le perimetre et les stories (`DEC-0039`, fusion `9f39448`),
  `theo-archi` a rendu son avis de faisabilite ecrit avant tout commit de code (fusion `358cf62`,
  verifie par `git diff --stat 9f39448 358cf62 -- '*.java'` : un seul fichier, sa propre classe de
  test neuve `UneMatiereNAQuUneSeuleGraphieDansLeContenuServiTest.java`, 142 lignes ajoutees,
  aucune ligne de production touchee), `nadia-prof` a rendu ses quatre
  arbitrages prealables bloquants (fusion `1dbf8be`), `ines-design` a propose une forme sur `S17-1`
  (fusion `5499240`). Je gele, affecte et ecris.
- **Lots fusionnes avant ce planning**, lignee verifiee sur chacun (`git merge-base`) :
  - `camille-po` : `9f39448` (embarque `d55f07b`, `efb26aa`), forkee de `108b8ce` - un ancetre du
    `round-two` d'aujourd'hui, anterieur aux deux fusions les plus recentes (`cfb48f3`, `e3933d5`).
    Fusion sans conflit ; verifie explicitement que ses deux commits ne touchent aucun fichier de
    `squad/sprints/s16-...` (`git show --stat` des deux commits) - la difference vue dans un premier
    `diff round-two..HEAD` venait uniquement de la base plus ancienne, pas d'une ecriture de
    `camille-po` sur les fichiers de cloture de s16.
  - `theo-archi` : `358cf62` (embarque `deeda8c`), forkee de `e3933d5` (tete du jour). Fusion sans
    conflit.
  - `nadia-prof` : `1dbf8be` (embarque `af0615f`, `c5d2bce`), forkee de `e3933d5`. Fusion sans
    conflit. Ses deux arbitrages sur `S17-2`/`S17-3`/`S17-4` vivent volontairement **a part**
    (`ARBITRAGE-PREALABLE-s17-francais-cm1-et-resurrection.md`) plutot que dans les deux fiches que
    `camille-po` venait d'ouvrir, pour ne pas produire un conflit d'ecriture sur un fichier qu'elle
    n'avait pas encore vu fusionne - rien ne s'est perdu : les deux fiches de `camille-po` et
    l'arbitrage a part de `nadia-prof` coexistent, aucun des deux ne recouvre l'autre.
  - `ines-design` : `5499240` (embarque `38dfa75`), forkee de `e3933d5`. Fusion sans conflit.
- **Repertoire de sprint** : `s17-francais-cm1-et-dette`, cree par le lot d'`ines-design` (et
  repris independamment par `theo-archi` pour son avis) avant que je gele le titre ci-dessus. Je le
  garde tel quel - il nomme le perimetre gele en dessous aussi bien que toute autre formulation - et
  je n'ai donc rien a renommer par `git mv`.

## Porte technique rejouee, par mes deux methodes

`bash outils/mvn-jdk21.sh -B clean verify` sur l'arbre fusionne (les quatre lots ci-dessus, racine
du depot, reacteur complet) : **BUILD SUCCESS**, six modules `SUCCESS`, `Total time: 01:29 min`
(rejoue une seconde fois pour capturer le log complet : `01:30 min`, memes chiffres).

**548 tests, 0 echec, 0 erreur, 0 ignore** - deux methodes independantes, sur le meme `clean` :

1. Somme des blocs `[INFO] Results:` du reacteur, module par module : `domain` 48, `application`
   12, `adapter-web` 269, `adapter-content` 126, `bootstrap` 87 (surefire) + 6 (failsafe) = 93.
   Total 48+12+269+126+93 = **548**.
2. Somme de l'attribut `tests="..."` de la balise `testsuite` des **89 rapports XML** sous
   `*/target/surefire-reports/` et `*/target/failsafe-reports/` des cinq modules declares au pom
   agregateur (`adapters/adapter-web`, `adapters/adapter-content` inclus - une premiere passe sans
   ces deux chemins imbriques donnait un compte faux, corrigee avant de le porter ici) : **548**,
   repartition identique a la methode 1.

**Chiffre annonce par `theo-archi` (548 tests, +3 depuis les 545 qu'il cite comme ceux de
`sofia-sm` a la cloture de s16) : confirme sur l'arbre fusionne, par mes deux methodes, pas
recopie.**

**29 regles ArchUnit**, inchange - `grep -rn "ArchRule .* =" --include=*.java` sur les cinq modules
declares au pom : 29 occurrences, memes onze fichiers qu'a la cloture de s16 plus aucun nouveau -
confirme la lecture de `theo-archi` : `UneMatiereNAQuUneSeuleGraphieDansLeContenuServiTest` n'est
pas une regle ArchUnit, elle juge la valeur d'une cle de ressource, qu'ArchUnit ne voit pas.

`bash .github/scripts/verifier-chemins.sh` : code 0, aucun chemin de poste de travail ni nom de
compte trouve. `bash .github/scripts/verifier-chemins-test.sh` : code 0, tous les cas passent.
`bash .github/scripts/verifier-prefixes.sh` : code 0, tous les commits de l'historique fusionne
portent un prefixe d'agent connu.

## Objectif gele

**Fermer le francais CM1 que l'utilisatrice a designe (comprehension de texte, synonymes en
couverture partielle nommee, retour du "Bon petit mot") et deux dettes de garde-fou heritees de
s16, dans cet ordre de priorite explicite ; poser un indicateur de focus clavier visible sur les
sept pressables d'un ecran de manche ; et transferer definitivement a l'utilisatrice, sans les
reconduire, deux obstacles de capacite que la squad ne peut pas lever elle-meme.**

## Cinq faits, portes sans les adoucir

1. **`nadia-prof` a refuse la matiere premiere de la comprehension en la mesurant** : huit items sur
   huit ou la bonne reponse est une chaine recopiee mot pour mot du texte, quatre sur huit
   resolubles sans lire le texte, zero inference sur huit. "Ce n'est pas une reprise, c'est un
   paquet neuf" - et `theo-archi` arrive a la meme conclusion par une autre voie, le format : le
   chargeur pilote par schema refuse `contenu-repris/quiz/cm1-comprehension.yml` et
   `contenu-repris/associer/cm1-synonymes.yml` des la premiere cle (`schema`, `id`, `type`,
   `descripteur` imbrique absents des deux). `S17-2` et `S17-3` sont des **stories d'ecriture de
   contenu**, pas des conversions.
2. **`camille-po` a leve son propre critere** ("sans gabarit ni feuille de style neuve" sur `S17-2`)
   plutot que de sortir la story, apres que la mesure de `nadia-prof` (5 lignes avec le style
   d'enonce actuel, 8 avec un style dedie) a montre que ce critere et la competence visee ne peuvent
   pas tenir ensemble. Motif ecrit par elle : le critere venait d'elle-meme pour economiser un cout
   de developpement, et le maintenir "aurait ecrit un refus a l'avance pour economiser une decision
   de style".
3. **`theo-archi` a ferme le prealable du filtre par matiere** (`ADR-0049`, la matiere portee
   accentuee par la donnee de contenu elle-meme, aucun champ distinct), apres trois reports - avec
   un motif qui n'est pas de process : l'inventaire de chaines exposees ne (`S16-5`) force a
   inscrire une graphie a chaque entree neuve de catalogue, donc une entree neuve de francais CM1
   sans reponse aurait force a inscrire une graphie qu'on sait fausse pour un enfant. **Le filtre par
   matiere lui-meme reste differe pour la troisieme fois** (`DEC-0032` -> `DEC-0037` -> `DEC-0039`
   section 4) - **cette fois sur instruction expresse de l'utilisatrice**, qui a fixe le perimetre de
   s17 sur "le francais CM1 non couvert, et la dette" sans y inclure le filtre, et non par defaut de
   la squad. Le second prealable du filtre (forme d'un second axe combine, R-BARRE-3/S14-B) reste lui
   aussi sans reponse - `ines-design` le porte toujours.
4. **`ADR-0042` a change de statut** (`Accepte` -> `En attente de son objet`) : l'engagement pris par
   `theo-archi` a la cloture de s16 est tenu, verifie sur les quatre stories de s17 (aucune ne fait
   apparaitre d'arret remplacant). Il en tire, et je le porte pour la retro sans le trancher ici, que
   ce geste est le troisieme du meme genre en deux sprints (apres les statuts d'`ADR-0047` et
   `ADR-0048`) : "le point commun n'est pas l'oubli d'une decision, c'est une condition dont
   l'echeance ne tombait dans le mandat de personne".
5. **Deux agents se sont corriges sur des accusations portees a d'autres.** `theo-archi` avait
   impute a `marc-dev` le defaut de la regle de duree du marqueur (`ADR-0047`) et a etabli, en
   relisant les deux tests avant de laisser l'accusation, que le defaut est dans son propre enonce
   (l'egalite testee ne prouve pas que la duree n'est pas une constante ; c'est une divergence
   fortuite entre deux fixtures, 900 et 1800, qui la fait tenir). `nadia-prof` a recalcule les dix
   chiffres de contraste d'`ines-design` a la formule WCAG plutot que de les croire, avant de s'en
   servir pour arbitrer `S17-1` : les dix reproduisent au centieme, y compris les deux qui condamnent
   la variante exterieure (1,14:1 et 1,07:1).

**Amendement candidat que je porte pour `retro.md`, sans l'ecrire ici** : la reverification d'un
chiffre repris contre sa source primaire, avant qu'il n'entre dans un document qui fait foi -
exactement le geste que `theo-archi` et `nadia-prof` viennent chacun de faire au point 5.

## Ce que ce sprint peut et ne peut pas rendre a la couverture

**L'engagement de `DEC-0037`** (si s17 ne rouvre aucune ligne de la matrice, ce sera ecrit comme un
changement de direction) **est leve par l'ouverture** : deux lignes passent `non couvert` a
`en cours` (comprehension, synonymes), une troisieme progresse sans changer de statut (homophones,
deja `en cours` depuis s10). C'est le premier sprint depuis trois a viser une ligne du programme.

**Ce que cela change reellement au taux de couverture, dit sans l'habiller** : au mieux, **une
seule ligne** peut passer `en cours` a `couvert-valide` a la cloture de s17, celle de la
**comprehension** (`S17-2`), et seulement si `nadia-prof` prononce un Done pedagogique favorable en
jouant l'increment livre - rien n'est acquis d'avance. **La ligne des synonymes ne peut pas
basculer ce sprint, quel que soit le verdict sur le paquet** : `nadia-prof` a explicitement exclu
qu'elle entre au numerateur du taux de couverture tant que les antonymes n'y sont pas
(`S17-5`, non affectee), et `camille-po` a retenu cette distinction dans `DEC-0039`. **La ligne des
homophones ne peut pas non plus basculer ce sprint** : sa condition de retour (`BPM-1`, mesure de
rendu sur la totalite des 24 manches raccourcies) n'est pas produite par la squad ce sprint - voir
`S17-4` ci-dessous. **Le taux de couverture actuel est 19,6 % (9 lignes `couvert-valide` sur 46,
0 lignes rouvertes ce sprint)** : la matrice compte l'ouverture d'une ligne (`non couvert` ->
`en cours`), pas encore un mouvement de couverture validee.

## Tableau des stories

Criteres dans leur **forme reecrite** par `theo-archi` (`avis-faisabilite.md`) et par `nadia-prof`
(les deux arbitrages prealables) quand ils les ont reecrits. Deux dettes de garde-fou de s16
entrent au perimetre par arbitrage de `theo-archi`, gelees directement ici comme `DEC-0037` et
`DEC-0039` l'autorisent pour ce genre de lot.

| Story | Competence programme visee | Affectation | Ce qui change dans les criteres | Porte de sortie |
|---|---|---|---|---|
| **S17-1** - un enfant qui navigue au clavier voit quel pressable est actuellement selectionne | Transverse, pas de contenu pedagogique | `marc-dev`, en premier (aucune dependance restante, arbitrages clos) | Aucun critere reecrit par `theo-archi` (deja encodables). Forme retenue par `nadia-prof` (`ARBITRAGE-PREALABLE-s17-indicateur-de-focus-clavier.md`) : variante 1, anneau interieur 3 px `currentColor` a `:focus-visible` seulement, sur les memes selecteurs qu'`ADR-0046`, jamais a `:hover`/`:active`. | `mvn verify` vert sur les deux criteres testables (propriete visible qui differe au focus, non exclusivement une couleur), **puis** sonde de `nadia-prof` sur l'increment livre (FOC-4, FOC-5, FOC-6 : les sept pressables, la perception a 360 px, S15-8/S15-9 intacts) dans `squad/conformite/FICHE-barre-entree-niveau.md` - elle mesure apres livraison, jamais avant (section suivante). |
| **S17-2** - comprendre un texte narratif ou documentaire (francais CM1) | Francais, CM1, "Lecture et comprehension de l'ecrit" - ligne `non couvert` -> `en cours` | `marc-dev`, apres `S17-1` | Matiere premiere refusee en totalite (fait 1) : paquet de 20 items ecrit integralement neuf. Critere de rendu (360x640) retire des criteres testables par `theo-archi` (non gardable, appartient a la porte de `nadia-prof`) ; critere "sans feuille de style neuve" **leve** par `camille-po` (fait 2) et remplace par `COMP-5`. | `mvn verify` vert sur COMP-1, COMP-2, COMP-3, le comptage (>=20 items) et le schema (`type: question`) ; **puis** Done pedagogique de `nadia-prof` sur COMP-4 (rendu, mesure par elle) et COMP-5 (lisibilite du style retenu), dans `squad/conformite/FICHE-bandit-manchot-comprehension-cm1.md`. |
| **S17-3** - synonymes en contexte (francais CM1, couverture partielle nommee) | Francais, CM1, "Vocabulaire" - ligne `non couvert` -> `en cours`, **jamais `couvert-valide` sur ce seul paquet** | `lea-dev`, en premier (aucune dependance de design restante) | Forme reecrite par `nadia-prof` : mot cible presente en phrase (SYN-1), jamais isole - condition bloquante sans laquelle son accord sur le proxy tombe. | `mvn verify` vert sur SYN-1 a SYN-3, le comptage, l'indice par item, le schema, **et** vigilance nommee par `theo-archi` sur trois garde-fous qui n'ont jamais rencontre ce genre de contenu (rang de la bonne reponse non predictible, aucun item ne repete l'ecran d'un autre, aucune proposition egale a une autre) - a mesurer au premier depot du paquet. Puis Done pedagogique de `nadia-prof` (`FICHE-bandit-manchot-synonymes-cm1.md`). |
| **S17-4** - "Le bon petit mot" revient au catalogue (raccourcissement, pas une ouverture) | Francais, CM1, "Orthographe et grammaire", homophones grammaticaux - ligne deja `en cours` depuis `DEC-0024` (s10), avancee, pas ouverte | `lea-dev`, apres `S17-3` | Aucun critere reecrit - ceux de `camille-po` renvoient aux conditions de `DEC-0024` et de l'arbitrage `BPM-1` a `BPM-4` de `nadia-prof`, deja formulees comme des mesures. | **Porte partielle, gelee comme telle** (voir section dediee juste apres ce tableau) : le raccourcissement des 24 phrases, la correction des 22 indices et la restitution en deux lignes de l'entree de catalogue sont du perimetre du sprint, `mvn verify` vert, et `nadia-prof` rejuge BPM-2/BPM-3/BPM-4 (jugements de contenu, sans rendu). **BPM-1 n'est pas produite par la squad ce sprint** - la story ne peut donc pas atteindre son Done complet a la cloture de s17. |
| **S17-5** - antonymes (francais CM1, story compagne de `S17-3`) | Francais, CM1, "Vocabulaire" - seconde moitie de la ligne ouverte par `S17-3` | **Non affectee**, ecrite au backlog sur demande de `nadia-prof` (SYN-4) - deja satisfaite : la story existe dans `docs/backlog/backlog.md` depuis la fusion du lot de `camille-po`. | - | Candidate d'un sprint futur, arbitrage prealable propre a la story le jour ou elle est affectee - `camille-po` la planifie. |
| **Dette 8.1** - la regle de duree du marqueur (`ADR-0047`) ne garde pas la propriete qu'elle annonce | Transverse, dette de garde-fou | `sofia-sm` (casquette dev), en premier parmi ses deux dettes - priorite explicite de `theo-archi` : "la seule des trois dont la regle ne garde pas aujourd'hui ce qu'elle annonce" | Reformulation de la clause : non "l'egalite est verifiee sur la session de la requete", mais "l'egalite est verifiee sur au moins deux durees differentes dans la meme epreuve" - seule forme ou une constante cablee echoue sans dependre d'un voisin. | `mvn verify` vert, correctif de quelques lignes dans une seule classe (intervalle pose, marqueur lu, second intervalle different, relu), mutation prouvant la morsure (une constante cablee rougit sur au moins l'une des deux durees) decrite dans le commit. |
| **Dette 8.2** - les garde-fous de provenance prouvent un emplacement, jamais une fraicheur | Transverse, dette de garde-fou | `sofia-sm` (casquette dev), en second | Ajout d'une comparaison d'horodatage `.class` amont / `.java` source, en plus de la verification d'emplacement existante ; `theo-archi` nomme explicitement ce que cela ne prouve pas (un `touch` suffirait a le tromper, un `clean` en amont d'une mesure reste la seule garantie). | `mvn verify` vert, garde-fou etendu, preuve de morsure sur un `.class` perime simule. |

## S17-4 - comment je gele une story dont la porte de sortie est hors de la squad

`theo-archi` l'ecrit sans detour (`avis-faisabilite.md`, section 4) : la chaine reelle de `S17-4`
a trois maillons, **un seul est du code** (deux lignes, la restitution de l'entree de catalogue,
gardee dans les deux sens par `ADR-0045`). Les deux autres sont du contenu (raccourcir le paquet,
corriger ses indices) et **une mesure de rendu sur la totalite des 24 manches** que `DEC-0039`
section 3 a explicitement transferee a l'utilisatrice : "aucun agent de la squad, hors
`nadia-prof`, ne peut produire un chiffre de rendu sur ce poste... l'utilisatrice en devient la
proprietaire explicite... je le retire de mon perimetre et de celui de `sofia-sm` a compter de s17".

**Ce que je gele donc, et ce que je ne gele pas.** J'affecte `S17-4` a `lea-dev` pour tout ce que
la squad peut produire elle-meme : le raccourcissement des 24 phrases (sans en supprimer aucune,
`BPM-3`), la correction des 22 indices sur 24 qui nomment la reponse (dans le meme geste,
`DEC-0024`), la verification par un tiers qu'aucun item raccourci ne devient ambigu (`BPM-2`), et
la restitution en deux lignes de l'entree de catalogue. `mvn verify` vert sur ce lot est un critere
que la squad tient seule. **Mais je n'ecris pas une porte de sortie complete pour cette story a la
cloture de s17** : `BPM-1` (l'etat le plus charge de chaque manche raccourcie, mesure par
`outils/mesurer-rendu.sh` sur la totalite des 24 items) reste une condition ouverte, dont la
production n'est plus mandatee a aucun agent de la squad depuis `DEC-0039`. La story reste donc
**techniquement livrable et pedagogiquement jugeable sur BPM-2/BPM-3/BPM-4**, mais **son retour
effectif au catalogue jouable n'est pas acquis a la cloture de s17** : il attend que la mesure
existe, par la main de l'utilisatrice ou par tout autre moyen qu'elle choisira. Je ne la compte donc
pas dans ce que ce sprint peut rendre a la couverture (section ci-dessus), et je ne la liste pas
dans le tableau des obstacles ci-dessous comme un point encore a router - c'est precisement
l'obstacle que `camille-po` m'a demande de ne plus reconduire.

## Sequencement de `S17-1` - la sonde de `nadia-prof` vient apres, pas avant

`nadia-prof` l'ecrit elle-meme (`ARBITRAGE-PREALABLE-s17-indicateur-de-focus-clavier.md`, section
8) : elle mesure **apres** la livraison, jamais avant, parce que mesurer avant l'obligerait a
ecrire la regle elle-meme pour avoir quelque chose a rendre - c'est-a-dire choisir la forme, qui est
a `ines-design`. Son arbitrage de forme (variante 1 retenue) est deja rendu et n'est donc pas une
condition bloquante avant le code ; ce qui reste sequence apres coup, ce sont ses trois conditions
de mesure (FOC-4, FOC-5, FOC-6). J'affecte donc `marc-dev` sur le code des maintenant, et je note
pour `relecteur-pedagogique`/`yanis-qa` que le Done de cette story n'est pas atteint au premier
`mvn verify` vert : il attend la sonde de `nadia-prof` sur l'increment livre.

## Sequencement de `S17-2` - `COMP-5` avant le vingtieme item, pas apres

`nadia-prof` est explicite : si le critere `COMP-5` (style distinct du texte-support, plus petit
que l'enonce, budget mesure a 8 lignes/45 mots) n'est pas satisfait, son accord de principe sur
`S17-2` devient un refus a l'audit. `ines-design` propose la forme, `theo-archi` en confirme le
mecanisme, `nadia-prof` juge la lisibilite - **avant que le premier des vingt items ne soit ecrit
sur cette base**, comme `DEC-0039` l'exige. `marc-dev` peut commencer a choisir et roder les textes-
support et les questions (ce que `COMP-1` a `COMP-3` exigent ne depend pas du style final), mais ne
fixe le paquet dans sa forme rendue qu'une fois `COMP-5` arbitre. Ce n'est pas le cycle iteratif
que ce produit s'interdit (memoire de squad, gel de design au sprint 9) : c'est une proposition
unique suivie d'un jugement unique, du meme type que celui deja tenu sur `S17-1`.

## Affectations et sequencement, en un coup d'oeil

- **`marc-dev`** : `S17-1` (indicateur de focus clavier), puis `S17-2` (comprehension CM1, apres
  l'arbitrage `COMP-5` d'`ines-design`/`nadia-prof`).
- **`lea-dev`** : `S17-3` (synonymes CM1), puis `S17-4` (resurrection du "Bon petit mot", porte de
  sortie partielle - voir section dediee).
- **`sofia-sm`** (casquette dev) : dette 8.1 (duree du marqueur), puis dette 8.2 (garde-fous de
  provenance) - ordre de priorite explicite de `theo-archi`.
- **`yanis-qa`** : traduit les criteres en tests au fil des lots, comme a chaque sprint ; mutation-
  morsure a exercer en priorite sur `UneMatiereNAQuUneSeuleGraphieDansLeContenuServiTest` (regle
  neuve de ce sprint) et sur les deux dettes de `sofia-sm` une fois fusionnees.
- **`relecteur-pedagogique`** : relit chaque lot au fil de l'eau, apres son Done technique -
  affecte explicitement, comme convenu.
- **`nadia-prof`** : sonde post-livraison de `S17-1` ; Done pedagogique de `S17-2`, `S17-3`,
  jugement de contenu (BPM-2/3/4, sans rendu) sur `S17-4` ; discretion sur les deux dettes de
  garde-fou (aucun rendu ne change par construction).
- **`theo-archi`** : confirmation ArchUnit a la review ; disponible si `S17-1` ou les deux dettes
  font apparaitre une question de mecanisme non anticipee.
- **`ines-design`** : proposition deja rendue sur `S17-1` ; due sur `COMP-5` de `S17-2` avant le
  premier item ecrit sur sa base.

## Deux obstacles qui sortent du tableau, definitivement

Par `DEC-0039` section 3, l'utilisatrice a repris a sa charge deux obstacles de capacite que la
squad reconduisait de rapport en rapport sans jamais les trancher. Je les route une derniere fois,
comme demande, puis je cesse de les inscrire dans le tableau des obstacles de `RAPPORT.md` a
compter de s17 :

- **La dependance a une seule personne pour toute mesure de rendu.** Aucun agent hors `nadia-prof`
  ne peut produire un chiffre de rendu sur ce poste, faute d'un navigateur pilote. Proprietaire
  desormais : l'utilisatrice.
- **`R-BARRE-4` / la reserve F11 (l'ecoute reelle au lecteur d'ecran).** Nommee depuis avant s10,
  jamais levee, jamais assignee. Proprietaire desormais : l'utilisatrice. `nadia-prof` continue de
  noter que ses Done, passes et a venir, ne couvrent jamais ce qu'un enfant entend - ce transfert
  n'est pas une fermeture de cette reserve pedagogique.

## Obstacles routes

| Description | Categorie | Destinataire | Ce qui manque pour clore |
|---|---|---|---|
| `S17-4` - `BPM-1` (mesure de rendu sur la totalite des 24 manches) n'est pas produite par la squad | capacite, desormais hors squad | utilisatrice | La mesure elle-meme, par elle ou par tout moyen qu'elle choisit. Non reconduit comme obstacle de la squad (voir section dediee ci-dessus). |
| Dette 8.3 - migration du marqueur d'ecran de jeu `.ecran-de-jeu` vers un attribut | technique | theo-archi (mecanisme), sofia-sm (capacite) | Hors perimetre de s17 sur motif de sprint explicite de `theo-archi` : deux stories ouvrent du contenu que les garde-fous n'ont jamais rencontre, toucher une quinzaine de fixtures en meme temps melangerait deux sources de rouge. Candidate s18. |
| `S17-5` (antonymes) non affectee | produit | camille-po | Affectation a un sprint futur ; arbitrage prealable propre a la story de `nadia-prof` le jour ou elle est affectee. |
| Second prealable du filtre par matiere (forme d'un second axe combine, R-BARRE-3/S14-B) | design | ines-design | Aucune proposition de forme a ce jour ; sans objet tant que le filtre par matiere n'est pas repris a un sprint (differe une troisieme fois sur instruction de l'utilisatrice). |
| Lecon sur `ADR-0042`, `ADR-0047`, `ADR-0048` : trois statuts dus reclames par un tiers en deux sprints | methode | a porter en retro, pas un obstacle a trancher ici | Amendement candidat pour `retro.md` sur qui porte l'echeance d'un engagement pris dans un `ADR`. |

## Decisions prises

1. **L'objectif est gele** sur les quatre stories de francais CM1 (`S17-1` a `S17-4`, `S17-5` non
   affectee) et les deux dettes de garde-fou (8.1, 8.2), avec les criteres dans leur forme reecrite
   par `theo-archi` et `nadia-prof`, et les affectations nominatives ci-dessus.
2. **Les deux dettes 8.1 et 8.2 entrent au perimetre** par mon arbitrage de capacite, dans l'ordre
   de priorite explicite de `theo-archi` (8.1 avant 8.2), gelees directement en planning comme
   `S16-7` l'a ete au sprint precedent.
3. **`S17-4` est gelee avec une porte de sortie partielle**, explicitement : le travail de contenu
   et les deux lignes de code sont du perimetre de la squad, la mesure `BPM-1` ne l'est plus. La
   story ne peut pas atteindre un Done complet a la cloture de s17.
4. **`S17-5` n'est pas affectee** : la demande de `nadia-prof` (SYN-4, la story doit exister au
   backlog avant la cloture de s17) est deja satisfaite par le lot de `camille-po`.
5. **Deux obstacles de capacite sortent du tableau des obstacles de `RAPPORT.md`**, definitivement,
   par transfert a l'utilisatrice acte dans `DEC-0039` section 3.
6. **Le filtre par matiere reste hors perimetre**, troisieme report, sur instruction expresse de
   l'utilisatrice et non par defaut de la squad - fait 3 ci-dessus, non une decision que je prends
   ici.
7. **La sonde de `nadia-prof` sur `S17-1` est sequencee apres la livraison**, jamais avant, sur son
   propre motif ecrit (mesurer avant reviendrait a choisir la forme a la place d'`ines-design`).
8. **`COMP-5` (style du texte-support de `S17-2`) est sequence avant l'ecriture du vingtieme item**,
   proposition d'`ines-design` puis jugement de `nadia-prof`, dans le meme geste qu'exige `DEC-0039`.
9. **Ce que ce sprint peut rendre a la couverture est borne a une seule ligne** (comprehension,
   sous reserve du Done pedagogique) - les synonymes et les homophones ne peuvent pas basculer ce
   sprint, quel que soit le verdict sur leur contenu, pour les motifs ecrits dans la section dediee
   ci-dessus.

## Correction du 2026-08-19 (trouvee par lea-dev, verifiee par camille-po, s17)

Ce document cite deux fois "la correction des 22 indices sur 24" (tableau des stories, ligne
`S17-4`, et section "S17-4 - comment je gele une story dont la porte de sortie est hors de la
squad"). **Le chiffre est faux : c'est 24 sur 24.** `lea-dev` a relu `cm1-homophones.yaml`
caractere par caractere en livrant la story et corrige les deux indices manquants au compte
d'origine (bonne reponse `On`, bonne reponse `Où`), qui echappaient a une comparaison sensible a
la casse : leur bonne reponse porte la majuscule de debut de phrase, l'indice les citait en
minuscule. `camille-po` a verifie ce chiffre et l'a retrouve, coherent, dans cinq fichiers
(`DEC-0024`, `docs/backlog/backlog.md`, la Javadoc de `PaquetsFrancaisS04Test`, et les deux
occurrences de ce document). Le geste demande a `S17-4` ne change pas : corriger tous les indices
fautifs dans le meme mouvement que le raccourcissement. Seul le compte affiche etait faux, et
`lea-dev` a corrige au-dela du compte annonce plutot qu'a sa hauteur. Les deux lignes d'origine
ci-dessus ne sont pas reecrites, conformement a la forme retenue par ce depot depuis s16
(`R-BARRE-1`) : correction par note datee, jamais par reecriture silencieuse.

## Correction du 2026-08-19 (trouvee par relecteur-pedagogique, s17)

La section "Ce que ce sprint peut et ne peut pas rendre a la couverture" ci-dessus affirme qu'**une
seule ligne peut passer `en cours` a `couvert-valide` a la cloture de s17, celle de la
comprehension (`S17-2`)**. **Ce n'est plus vrai : `S17-2` est sortie du perimetre de s17.**
`camille-po` l'a decide par amendement de `DEC-0039` (deuxieme amendement, 2026-08-19) : le budget
vertical compose de la manche de comprehension reste insuffisant de 90 a 120 px meme apres les
rendus d'`ines-design` et de `theo-archi`, et `S17-2` retourne au backlog, non affectee. Depuis ce
meme amendement, **aucune ligne de `squad/programme/MATRICE-couverture.md` n'atteindra
`couvert-valide` a la cloture de s17** ; l'engagement de `DEC-0037` reste leve, mais par
l'ouverture de la ligne des synonymes (`S17-3`) seule, jamais par un franchissement de
`couvert-valide`. Je ne reecris pas la section d'origine, qui datait le gel de l'objectif au
demarrage du sprint : cette note dit ce qu'elle n'a plus de vrai depuis, sur le patron retenu par
ce depot depuis s16 (`R-BARRE-1`) et applique plus haut dans ce meme document pour le chiffre
24/24.
