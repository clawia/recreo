# [Sprint s19] Planning - contenu seul et conception de la saisie

- **Horodatage de debut** : 2026-08-24 14:00 +0200
- **Anime par** : sofia-sm, casquette Scrum Master.
- **Repertoire de sprint** : `s19-contenu-seul-et-conception-de-la-saisie`, deja cree par le lot de
  `theo-archi` (`avis-faisabilite.md`). Je le garde tel quel : il nomme le perimetre gele
  ci-dessous, aucun renommage n'est necessaire.

## Ordre du process respecte

`camille-po` a instruit le perimetre (`DEC-0042`), `nadia-prof` a rendu en parallele les deux
arbitrages prealables que `DEC-0042` route et reprend par amendement le jour meme, `theo-archi` a
rendu son avis de faisabilite ecrit sur les six stories avant tout commit de code de story. Je
gele, affecte et ecris.

## Lots fusionnes avant ce planning, provenance verifiee

Les trois lots forkent du meme point (`c3d3d47`, `git merge-base round-two <branche>` rend ce SHA
pour les trois). Aucun recoupement de fichier entre les trois diffs (`git diff --name-only`
compare deux a deux, aucune intersection) : la fusion sequentielle ne pouvait pas produire de
conflit, et n'en a pas produit.

**Verification de provenance faite avant chaque fusion** (regle explicite depuis la retro de s18) :
`git worktree list` montre chaque branche portee par un worktree distinct du depot principal, et
`git reflog show HEAD` sur le worktree principal ne porte aucune trace de checkout de l'une des
trois branches. Aucun des trois lots n'a jamais existe ailleurs que dans son propre worktree dedie.

1. **`camille-po`** : `s19-camille` (`dc80943` puis `4a959fd`). Fusion sans conflit, commit de
   fusion `0d7f358`.
2. **`nadia-prof`** : `s19-nadia` (`6aecee3`). Fusion sans conflit, commit de fusion `a40927d`.
3. **`theo-archi`** : `s19-theo` (`717ed4e`). Fusion sans conflit, commit de fusion `e8ba52b`.

## Porte technique rejouee sur l'arbre fusionne, par ma propre methode

`bash outils/mvn-jdk21.sh -B clean verify` a la racine du reacteur (complet, donc pas de `-am` a
citer) : **BUILD SUCCESS**, cinq modules `SUCCESS` (`recreo`, `recreo-domain`,
`recreo-application`, `recreo-adapter-web`, `recreo-adapter-content`, `recreo-bootstrap`), `Total
time: 02:16 min`.

**624 tests, 0 echec** - somme de l'attribut `tests="..."` de chaque rapport XML de
surefire/failsafe, apres le `clean` de la commande ci-dessus : `recreo-domain` 48,
`recreo-application` 13, `recreo-adapter-web` 321, `recreo-adapter-content` 144, `recreo-bootstrap`
92 (surefire) + 6 (failsafe). 48+13+321+144+92+6 = **624**. Ce chiffre est celui que `theo-archi`
annonce dans `ADR-0058` ; je le confirme ici par ma propre mesure sur l'arbre fusionne, je ne le
recopie pas (regle de s16).

**Ecart trouve, sans consequence sur le total** : la ventilation par module d'`ADR-0058` ("321
`adapter-content` + 144 `adapter-web`") intervertit les deux modules - c'est `adapter-web` qui
porte 321 tests (42 classes) et `adapter-content` qui en porte 144 (23 classes), verifie par
comptage des fichiers `TEST-*.xml` de chaque repertoire. Le total (624) est juste, l'etiquette est
fausse. Verse au dossier de `yanis-qa` plutot que corrige ici : ce n'est pas mon lot.

**32 champs `ArchRule`** sur les fichiers `.java` trackes de l'arbre fusionne
(`git ls-files "*.java" | xargs grep -o "ArchRule [A-Za-z0-9_]* =" | wc -l`), confirmant le chiffre
brut de `theo-archi` ("32 apres ce lot"). Je ne rejoue pas son partage entre regles reelles et
faux depot de contre-exemple (30 reelles, 29 `@ArchTest`) : c'est une lecture, pas un balayage, et
elle est deja versee au dossier de `yanis-qa` par cet avis.

## Objectif gele

**Un lot de couverture en francais servi par la mecanique `bandit-manchot` existante (quatre
paquets neufs, CP et CM1), une dette d'accentuation deja chiffree fermee dans le meme sprint, et la
conception complete de la mecanique de saisie - sans aucune capacite de developpement affectee a
son code. L'audio reste hors perimetre. Le correctif de largeur `S19-6` est differe, sa condition
de levee nommee ci-dessous.**

Deux lignes du referentiel sont desormais classees `non couvrable en l'etat` par `camille-po`
(`DEC-0042`) : lecture a voix haute (CP) et, depuis l'arbitrage de `nadia-prof` la meme journee,
"relire et corriger son texte" (CM1). Le denominateur honnete de la couverture est **45 lignes
atteignables sur 46** ; le compte `couvert-valide` reste 10 avant tout mouvement de ce sprint
(10/45, 22,2 %, sans qu'aucune ligne n'ait ete validee par ce seul effet de denominateur -
`DEC-0042`, section 3).

## L'arbitrage de capacite qui m'appartient - ordre de fusion entre `S19-5` et le Lot A

`theo-archi` refuse de trancher : le seul critere qui separe les deux ordres est du temps de
developpeur, donc c'est un arbitrage de capacite, pas d'architecture. Sa contrainte, verifiee
plutot que recopiee (`squad/sprints/s19-contenu-seul-et-conception-de-la-saisie/avis-faisabilite.md`,
section 7) :

- la dependance est **double** : `UneMatiereNAQuUneSeuleGraphieDansLeContenuServiTest` (une seule
  graphie par matiere sur tout le contenu servi) et l'inventaire **bidirectionnel** de
  `TexteAccentueExposeParLePortEntrantTest` dans `bootstrap`. Chaque paquet neuf y ajoute 6 chaines,
  la bascule en modifie 5 - **les deux lots editent la meme liste dans le meme fichier**, donc un
  conflit textuel de fusion en plus du conflit de graphie ;
- dans les deux ordres, la bascule reste **un commit unique** : tout etat intermediaire est rouge
  par construction, ce n'est pas negociable ;
- `paquet-servi-en-ascii.yaml` (sous `src/test/resources/contreexemple/`) reste en ASCII dans les
  deux cas, sous peine de detruire la morsure du garde-fou sans qu'aucun test ne le signale
  autrement que par sa perte.

### Ma decision : `S19-5` fusionne en premier

**Motif.** L'ordre a zero reecriture coute strictement moins cher, et le cout de l'attente qu'il
impose au Lot A est negligeable au regard de ce qu'il evite : `S19-5` est deja entierement
chiffree, tient en **un seul commit sans ligne de production** (13 YAML + 2 fichiers Java,
perimetre reverifie par `theo-archi` a l'ouverture), et ne suppose aucun arbitrage prealable
pedagogique ni aucune conception a instruire - contrairement a `S19-1` et `S19-4`, qui attendent de
toute facon un arbitrage prealable de `nadia-prof` avant tout code. Faire attendre le Lot A d'un
commit deja pret n'est pas payer une dette de forme avec de la couverture de programme ; ce serait
le cas seulement si `S19-5` restait a instruire ou introduisait un delai reel, ce qui n'est pas la
situation ecrite par `theo-archi`.

**Consequence pour les developpeurs affectes au Lot A** : les quatre paquets neufs (`S19-1` a
`S19-4`) s'ecrivent directement avec `descripteur.matiere: "Français"` accentue et leurs entrees
d'inventaire dans `TexteAccentueExposeParLePortEntrantTest` accentuees des le premier commit -
**zero reprise**, a condition que `S19-5` soit deja fusionnee sur `round-two` avant que l'un d'eux
ne commence a editer ce fichier d'inventaire.

## Tableau des stories - affectation nominative et sequencement

| Story | Competence programme visee | Affectation | Avis de faisabilite (`theo-archi`) | Ce qui est porte au developpeur des l'affectation | Porte de sortie |
|---|---|---|---|---|---|
| **`S19-5`** - migration `ADR-0049` (accentuation) | Transverse, aucune ligne de matrice | `sofia-sm`, casquette dev, **en premier, avant tout le reste** | Faisable en l'etat, perimetre corrige : 13 YAML + 2 Java (`TexteAccentueExposeParLePortEntrantTest` 5 chaines, `PaquetCm1SynonymesTest` ligne 232), zero ligne de production. `YamlContentPackLoaderTest` hors perimetre. Un seul commit. | `paquet-servi-en-ascii.yaml` reste en ASCII sous peine de detruire la morsure du garde-fou de contre-exemple. | `mvn verify` vert sur le commit unique, aucun etat intermediaire commite. |
| **`S19-1`** - antonymes (francais CM1) | Francais, CM1, "Vocabulaire", ferme la moitie manquante de `S17-3` | `marc-dev`, apres `S19-5` | Faisable en l'etat. Paquet et entree de catalogue neufs confirmes seul chemin vert (deux raisons independantes : tirage aleatoire melangerait les operations, `AucuneEntreeDuCatalogueNEnDoubleUneAutreTest` refuse deux entrees sur le meme couple mecanique/paquet). `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` ne verra rien de ce paquet (propositions textuelles, `ADR-0051`) : "aucun distracteur n'est un antonyme acceptable" reste une porte pedagogique seule. | **Arbitrage prealable de `nadia-prof` requis avant tout code de contenu** (pertinence et univocite de chaque antonyme dans sa phrase, profondeur CM1, lisibilite du jeu distinct pour un enfant ayant deja joue "Les synonymes"). Plancher 16 items, indice obligatoire deja dans les criteres. | `mvn verify` vert (9 garde-fous derives du repertoire, 3 du catalogue, 6 chaines d'inventaire). Puis Done pedagogique de `nadia-prof`. |
| **`S19-2`** - accord genre/nombre du GN (francais CM1) | Francais, CM1, "Orthographe et grammaire" | `lea-dev` | Faisable en l'etat, aucun `ADR`. Deux garde-fous mordent sur des points absents des criteres. | **a.** `ChaquePaquetPorteUnIndiceTest` exige un indice sur chaque item (plafond de dette a 0) - absent des criteres, a tenir des l'ecriture. **b.** Un item a plusieurs trous n'a qu'une seule reponse possible en `bandit-manchot` : une proposition doit remplir tous les trous a la fois (un verdict par trou est une mecanique neuve, refusee comme cout de cette story). **c.** Un paquet melangeant 2/3/4 propositions varie la hauteur d'un item a l'autre (famille `SYN-11`/`BPM-1`) : mesure de `nadia-prof`, pas un defaut a corriger d'avance. Si les trois composantes (determinant/nom/adjectif) ne tiennent pas a cout raisonnable, la limite est nommee par ecrit dans la fiche des l'ouverture, jamais decouverte a l'audit. | `mvn verify` vert. Puis Done pedagogique de `nadia-prof`. |
| **`S19-3`** - accord sujet-verbe (francais CM1) | Francais, CM1, "Orthographe et grammaire" | `marc-dev`, apres `S19-1` | Faisable en l'etat, aucun `ADR`. Trois garde-fous mordent. | **a.** Meme indice obligatoire qu'en `S19-2`, absent des criteres. **b.** `AucunIndiceNeContreditUnAutreItemDuMemePaquetTest` a un cliquet a zero (ne de `cm1-conjugaison-present`, refus de `nadia-prof` du 2026-08-10) : les indices doivent etre temperes ("presque toujours") ou nommer leur sujet, jamais une regle generale non temperee - a ecrire ainsi des le premier jet, pas a corriger apres un rouge. **c.** La distinction avec la ligne conjugaison deja couverte (jamais un sujet pronominal isole) n'est gardee par aucun automate : entierement pedagogique. Si present/futur/imparfait ne tiennent pas tous les trois, limite nommee des l'ouverture. | `mvn verify` vert. Puis Done pedagogique de `nadia-prof`. |
| **`S19-4`** - nom/verbe (francais CP) | Francais, CP, "Orthographe et grammaire" | `lea-dev`, apres `S19-2` | Faisable en l'etat, aucun format neuf : deux propositions par item deja servi (`cm1-homophones`, 24 items a 2 propositions, `couvert-valide`). | **Arbitrage prealable de `nadia-prof` requis avant tout code de contenu** (lisibilite reelle des phrases pour un CP en cours d'apprentissage, choix nom/verbe jamais resolu par un indice de position). 16 phrases distinctes exigees (le couple enonce+propositions etant constant, `AucunItemNeRepeteLEcranDUnAutreItemDuMemePaquetTest` se reduit a comparer les enonces). | `mvn verify` vert. Puis Done pedagogique de `nadia-prof`. |
| **`S19-6`** - pli 576-719 px (`SYN-16`/`BPM-8`) | Transverse, aucune ligne de matrice | **Differee, non affectee** | **Infaisable en l'etat sur la bande entiere par un correctif de largeur** : arithmetique fermee (plafond atteignable au pied de bande = 536 px contre 500 px aujourd'hui, +36 px la ou +180 px avaient ete necessaires a 720 px ; effondrer les marges y contreviendrait a `SYN-14`). Une famille verticale existe, chiffree a 43,19 px de budget manquant, jamais instruite ; aucun garde-fou du depot n'epingle un terme vertical. | Sans objet tant que non affectee. | Voir condition de levee ci-dessous. |

## Condition de levee de `S19-6`

`theo-archi` demande une seule mesure avant toute affectation : **a quelle largeur de rangee les
cinq items fautifs de `cm1-synonymes` cessent-ils de se replier**, mesuree par la meme methode que
les mesures deja tenues a s18 (regle injectee, balayage de largeurs). Je l'ai deja demandee a
`nadia-prof` en parallele de ce planning, seule a pouvoir mesurer au navigateur. Deux issues,
aucune tranchee ici :

- si un correctif de largeur suffit au-dessus d'un seuil mesure a l'interieur de la bande, `S19-6`
  redevient affectable sur une bande reduite - decision de perimetre de `camille-po`, pas la
  mienne ;
- si aucun correctif de largeur ne suffit sur la moitie basse de la bande, la famille verticale est
  la seule option restante, et elle appelle un `ADR` de `theo-archi` (aucun garde-fou de ce depot
  n'epingle aujourd'hui un terme vertical) avant tout code.

`S19-1` a `S19-5` n'attendent pas cette mesure : elles sont independantes et affectees des ce
planning.

## Sequencement

1. **`sofia-sm` (casquette dev) fusionne `S19-5` en premier**, seule et sans dependance.
2. **Des que `S19-5` est sur `round-two`**, `marc-dev` et `lea-dev` peuvent commencer `S19-1` et
   `S19-2` en parallele - aucune dependance entre elles, ni avec `S19-3`/`S19-4`.
3. **`S19-1` et `S19-4` ouvrent chacune sur un arbitrage prealable de `nadia-prof`** avant tout code
   de contenu : les developpeurs preparent une proposition d'items (phrases, antonymes candidats)
   et la soumettent avant d'ecrire le paquet final et l'entree de catalogue.
4. **`S19-3` suit `S19-1`** chez `marc-dev`, **`S19-4` suit `S19-2`** chez `lea-dev` : deux stories
   par developpeur, dans l'ordre du tableau, pas de parallelisme force au sein d'un meme agent.
5. **`S19-6` reste hors sequencement tant que la mesure de `nadia-prof` n'est pas rendue.**

## Obstacles ouverts et leur routage

| Obstacle | Route vers | Etat |
|---|---|---|
| Lecture de `SAI-9` : litterale (chaque refus nomme la bonne reponse, `M9` clause 5 a rouvrir) ou lecture (b) de `theo-archi` (chaine de l'enfant a chaque refus, les deux chaines a la revelation, rien a rouvrir) | `nadia-prof` | Deja transmis avec la mesure de `theo-archi` en parallele de ce planning. `ADR-0058` est ecrit sur la lecture (b) ; si elle tranche pour (a), seule `M9` clause 5 est a rouvrir, la section 6 de l'`ADR` ne change pas. |
| Acces a une lettre accentuee d'un geste raisonnable sur le clavier virtuel de la tablette visee | Utilisatrice, via moi | Deja transmis. Ne bloque aucun code : `SAI-1` (tolerance par defaut) est le comportement par defaut de la conception quelle que soit la reponse, seule la valeur pedagogique de la saisie alphabetique en depend. |
| Contexte d'usage reel au CP (seul ou accompagne, quel appareil) | Utilisatrice, via moi | Deja transmis. Change le verdict de `nadia-prof` sur la saisie alphabetique au CP (section 6 de son arbitrage), ne bloque aucun code de ce sprint (aucune story de saisie n'est affectee). |
| Proposition d'amendement de referentiel : faire des "reperes" (majuscule, ponctuation) une competence evaluable a part, distincte de "relire et corriger son texte" | Utilisatrice, via moi | Deja transmis. N'affecte aucune story affectee ce sprint. |
| Compte de tests (624) et compte de regles ArchUnit (32 champs, 30 reelles, 29 `@ArchTest`), avec les trois causes de divergence connues (rapport `.txt` sous-comptant un moteur JUnit, critere enumere, releve automatique cite en preuve) | `yanis-qa` | Verse au dossier de cloture. Chiffres confirmes par ma propre mesure sur l'arbre fusionne (section "Porte technique" ci-dessus), pas recopies. |
| Etiquette inversee dans `ADR-0058` ("321 `adapter-content` + 144 `adapter-web`", en realite l'inverse) | `yanis-qa` | Trouve a cette fusion, sans consequence sur le total (624 juste dans les deux cas). Ne bloque rien, verse au dossier plutot que corrige par moi dans un fichier qui n'est pas le mien. |
| Deux garde-fous mordant sur des points absents des criteres de `S19-2` et `S19-3` (indice obligatoire) | `marc-dev`, `lea-dev` | Portes dans le tableau des stories ci-dessus, pas une decision a rendre : une contrainte d'ecriture a connaitre avant le premier commit. |

## Decisions prises pendant ce planning

- L'ordre de fusion entre `S19-5` et le Lot A des quatre paquets neufs : **`S19-5` en premier**,
  motif ecrit ci-dessus (arbitrage de capacite explicitement route par `theo-archi` a `sofia-sm`,
  `DEC-0042` section 5 et avis de faisabilite section 7).

Aucune autre decision de produit, d'architecture ou de pedagogie n'est prise dans ce planning :
tout le reste est route et attend son autorite.

## Ce que ce planning ne fait pas

- Il ne tranche pas la mesure de `S19-6` : elle appartient a `nadia-prof`.
- Il ne tranche pas la lecture de `SAI-9` : elle appartient a `nadia-prof`.
- Il ne prononce aucun Done pedagogique sur `S19-1` a `S19-5`.
- Il ne repond a aucune des questions routees a l'utilisatrice.
