# Review du sprint 12 - mathematiques CM1

## Done pedagogique - nadia-prof, 2026-08-14

Trois lignes de mathematiques CM1 ouvertes par `DEC-0029`, trois paquets livres, trois verdicts.
J'ai **joue les trois increments** : jar reconstruit depuis mon propre worktree avec un JDK 21,
application lancee, parties completes au navigateur a 360 x 640, manches justes et manches fausses,
trois echecs jusqu'a la revelation. Ce n'est pas un audit degrade.

Les developpeurs avaient ecrit contre mes 27 conditions, rendues avant leur code
(`squad/conformite/ARBITRAGE-PREALABLE-s12-mathematiques-cm1.md`). Je n'ai pas reverifie a la main
ce qu'un automate garde desormais : absence de proposition en double, inventaire du texte lu par
l'enfant, part du facile a 19,19 %, cardinalite des indices distincts. J'ai porte mon audit sur ce
qui reste a moi seule - l'exactitude arithmetique, la plausibilite des distracteurs, l'utilite de
l'indice, et le niveau, qui se joue et ne se lit pas.

| Story | Paquet | Verdict | Ligne de matrice |
|---|---|---|---|
| S12-1 | `cm1-perimetre.yaml` (22 items, `marc-dev`) | **VALIDE SOUS RESERVE** | peut passer a `couvert-valide`, sans mention |
| S12-2 | `cm1-mesures.yaml` (24 items, `marc-dev`) | **VALIDE SOUS RESERVE** | peut passer a `couvert-valide`, sans mention |
| S12-3 | `cm1-tables-multiplication.yaml` (99 items, `lea-dev`) | **REFUSE** | **ne bouge pas** |

Les verdicts complets, avec leurs motifs et leurs reserves, sont dans les blocs de validation de
`squad/conformite/FICHE-bandit-manchot-perimetre-cm1.md`,
`FICHE-bandit-manchot-conversions-unites-cm1.md` et
`FICHE-bandit-manchot-tables-multiplication-cm1.md`. Releves dans `squad/conformite/releves-s12/`.

### Ce qui est juste dans les trois paquets

**Aucune erreur de fait, nulle part.** J'ai recalcule les 22 perimetres, aires et moities, les 24
conversions et les 99 produits : **145 items, 145 exacts**. C'est la propriete qu'aucun automate ne
tient et c'est celle qui pouvait tout faire tomber ; elle est tenue.

Les pieges que j'avais nommes sont tous evites : le carre de cote 4 et le rectangle 3 x 6 sont
absents du paquet de perimetre ; aucun decimal nulle part ; la comparaison de grandeurs, que
`DEC-0029` n'avait pas vue, n'a pas ete reprise dans les mesures ; le cas `2 x 2` avec la somme des
facteurs est impossible par construction dans les tables. Les indices sont des methodes executables,
pas des slogans - je les ai lus apres des echecs reels, et ceux des tables, que je verifiais avec le
plus de mefiance, sont bons un par un.

### Le motif du refus, en une phrase

Sur `cm1-tables-multiplication.yaml`, **la bonne reponse n'est jamais le plus petit des trois nombres
proposes, sur 99 items sur 99, et elle est le plus grand sur 64**. Un enfant qui ne sait aucune table
et presse le plus grand nombre gagne **64,4 % des manches** - mesure en jouant, 76 sur 118 - contre
33,3 % par hasard. Sur une ligne dont le verbe est "rappel", deux manches sur trois se gagnent sans
rappeler quoi que ce soit.

La cause : pour "a x b", les deux distracteurs sont toujours `(a-1) x b` et `a x (b-1)`, tous deux
necessairement inferieurs au produit. La correction est peu couteuse : prendre, sur une part des
items, le voisin **au-dessus**, `a x (b+1)` ou `(a+1) x b`, aussi plausible - c'est la forme de mon
propre exemple en T-5, ou 63 (= 7 x 9) accompagnait 48 pour 7 x 8. Rien d'autre n'est a changer.

`lea-dev` a tenu **les sept conditions T-1 a T-7 a la lettre**. Le refus ne porte sur aucune d'elles :
il porte sur deux proprietes que j'avais nommement gardees pour moi, la plausibilite des distracteurs
et le niveau tel qu'il se joue. J'avais ecrit qu'un lot tenant mes vingt-sept conditions pouvait
encore etre refuse pour ce que seul le jeu montre ; c'est ce cas-la.

### Les deux points que les developpeurs ont signales plutot que de les corriger seuls

Tous les deux ont eu raison de me les renvoyer, et je reponds sur les deux.

**`marc-dev`, la plausibilite des distracteurs de mesures : non, `6 000 000 kg` ne teste rien.** Un
distracteur qu'aucun enfant ne choisirait est un remplissage, et l'item devient une question a deux
choix. **12 items sur 24** portent un distracteur valant de 10 000 a 1 000 000 fois la bonne
reponse. Mais **C-7 les admettait par sa lettre** : elle nommait la nature de l'erreur, pas son
ordre de grandeur. `marc-dev` n'a pas devie, ma condition etait courte, et j'avais pose exactement
ce principe deux sections plus loin en T-5 sans penser a le reporter. Reserve neuve et datee, pas
durcissement. Correction : garder les deux erreurs de facteur a un ou deux crans (60 kg et 600 kg
pour "6000 g, combien de kg ?").

**`lea-dev`, l'uniformite de famille : la famille est un progres, sa direction unique est
l'appauvrissement.** Le choix du produit voisin est le bon, et eviter le piege T-5 par construction
plutot que par detection est legitime - je credite les deux. Ce qui appauvrit n'est pas que cent
items se fabriquent de la meme facon : un enfant ne peut pas exploiter "c'est un produit voisin"
sans connaitre sa table. Ce qui appauvrit, c'est de n'avoir jamais pris que la moitie basse de la
famille.

### Deux corrections de mes propres constats, avant de m'appuyer dessus

1. **P-6 fabrique le defaut qu'elle etait censee eviter.** Ma condition nommait deux familles de
   distracteurs et interdisait les autres : l'aire et la moitie du perimetre. Or la moitie est
   toujours inferieure au perimetre et l'aire lui est superieure des que les cotes depassent 4 : la
   bonne reponse tombe **au milieu 18 fois sur 20**, et un enfant qui presse toujours le nombre du
   milieu reussit **90 %** des manches - plus que sur les tables que je refuse. Je ne refuse pas
   `marc-dev` pour avoir applique ma condition ; ce serait deplacer mes poteaux sur son travail. Les
   deux verdicts different parce que la marge de manoeuvre differait : sur les tables, le voisin
   du dessus etait disponible, aussi plausible, et deja illustre par mon propre exemple ; sur le
   perimetre, presque toutes les erreurs reelles sont inferieures au perimetre et l'aire est la
   seule famille qui passe au-dessus. Le remede n'y est donc pas un distracteur neuf mais plus de
   petites figures, dont l'aire tombe sous le perimetre. **Le carre de cote 2 est a exclure** : aire
   4 et moitie 4, deux propositions egales.
2. **J'ai d'abord mesure un catalogue a neuf jeux, sans "Les tables de multiplication".** C'etait un
   serveur laisse par une execution perdue de ce sprint, pas l'increment. Mon propre jar en sert
   dix. Le constat est retire avant d'avoir servi a quoi que ce soit.

### Ce que je n'ai pas ferme, et que personne ne doit prendre pour un acquis

**M4 ne bloque plus depuis `DEC-0025`** et je ne la ressuscite pas sur des lots qui n'en etaient pas
avertis. Mais la mesure est neuve et elle appartient au dossier :

| Jeu | Message d'erreur a 360 x 640 | Indice a 360 x 640 |
|---|---|---|
| Le perimetre | **sous le pli 67 / 67**, de 27 a 75 px | **sous le pli 67 / 67**, de 111 a 159 px |
| Les mesures | visible 61 / 61 | **sous le pli 38 / 61**, jusqu'a 33 px |
| Les tables de multiplication | visible 70 / 70 | visible 70 / 70, marge de 3 px au pire |

"Le perimetre" entre au catalogue avec le defaut de `DEC-0025` sur **100 % de ses echecs** : ses
enonces sont les plus longs des trois paquets. La capture
`squad/conformite/releves-s12/perimetre-echec-avec-indice-360x640.png` montre ce que l'enfant voit
sur un telephone apres s'etre trompe - l'enonce sur quatre lignes, les trois propositions, la marque
barree, et rien d'autre.

### Suites hors de ma main

- `camille-po` : deux lignes de matrice a passer a `couvert-valide` (perimetre, mesures), sans
  mention de couverture partielle pour l'une comme pour l'autre. La ligne des tables **ne bouge
  pas**, et le jour ou elle s'ouvrira, elle ne pourra jamais l'etre sans la mention **"sans mesure
  d'automatisation"** : le referentiel dit "rappel automatise", l'automatisation est une affaire de
  vitesse, et ce produit ne mesure aucun temps de reponse. Couverture partielle, avec ces mots.
- `camille-po` : sept motifs de story, tous ecrits et actionnables dans les trois fiches - le rang
  de la bonne reponse sur les tables (le refus), le rang sur le perimetre (ma reserve 1), l'ordre de
  grandeur des distracteurs de mesures, les deux mesures de pli, l'absence de mise en situation dans
  le perimetre, les quantites de depart a un seul chiffre significatif dans les mesures.
- `camille-po` : deux corrections de fiche - la ligne Contenu(s) du perimetre annonce toujours huit
  items transposables la ou ils sont sept, et celle des mesures nomme
  `cm1-conversions-unites.yaml` la ou le fichier livre est `cm1-mesures.yaml`.
- `theo-archi` et `yanis-qa` : **le rang de la bonne reponse parmi les trois propositions** est la
  propriete neuve que ce sprint fait apparaitre, et c'est la plus mecanisable de ce qui me reste. Je
  la decris, je ne prescris pas le moyen. Tant qu'aucun garde-fou ne la porte, c'est moi, en jouant.

## Reserve N, pressee au clavier - nadia-prof, 2026-08-14

**LEVEE** sur la propriete que j'avais annonce mesurer au clavier. Verdict complet dans le bloc de
validation de `squad/conformite/FICHE-bandit-manchot-additions-cp.md` ; releve dans
`squad/conformite/releves-s12/releve-reserve-n-clavier.json`.

J'avais ecrit : *"je la leverai au clavier, sur l'increment livre."* Il est livre, j'ai reconstruit
le jar depuis mon worktree, et j'ai presse les touches - 69 arrivees sur l'ecran de confirmation, le
meme denominateur que mon chiffre d'avant, reparties sur sept jeux et trois etats de depart, dont
l'etat de revelation qui avait fait tomber la clause 1 de M13 au s10.

| Ce que j'attendais, dans mes termes | Mesure sur 69 arrivees |
|---|---|
| a l'arrivee, le focus est sur la phrase arbitree | **69 / 69** |
| Entree seule ne fait rien | **69 / 69** |
| la premiere Tabulation atteint "Continuer la partie" a chaque fois | **69 / 69** |
| la premiere Tabulation sort de la page | **0 / 69** (avant : 57 / 69) |
| trois gestes deliberes pour perdre la partie | **69 / 69** |

`sofia-sm` a livre exactement la forme que j'avais designee : le point de focus sur la phrase, pas
sur le bouton sur ; sans une ligne de JavaScript neuve ni un octet de CSS ; sans anneau, ce que
j'avais refuse d'avance des le s01.

**Je corrige ma propre mesure avant qu'on s'appuie dessus.** Mon instrument ne reproduit pas le mode
de defaillance qui avait produit le 57 / 69 : en refabriquant l'etat "aucun point de focus", la
premiere Tabulation atteint quand meme "Continuer la partie", 21 fois sur 21, faute de barre
d'adresse ou echapper. J'ai donc rejoue les 69 arrivees dans une fenetre reelle au premier plan -
memes chiffres. Ce que j'etablis est positif, pas une absence : le focus part d'un point declare
dans le document, la premiere Tabulation est deterministe, et j'ai mesure ou elle arrive.

**La moitie que je n'avais pas fermee reste ouverte, et ce n'est l'oubli de personne** : la phrase
recoit le focus mais aucun anneau n'est peint, donc l'enfant ne voit pas ou il en est a l'arrivee.
Je n'en fais pas une condition sur ce lot, parce que ce n'est pas tenu non plus sur l'ecran de
manche que j'ai valide - ce serait un poteau deplace sur un seul ecran. C'est une propriete de toute
l'application et une story a elle seule, pour `camille-po`.

**Ce que la clause 4 de `theo-archi` ne couvre pas** : elle verifie qu'un point de focus est
**declare**, pas qu'il est **atteint**, ni ou la premiere Tabulation arrive ensuite. C'est l'ecart
que mon clavier couvre et qu'aucun automate ne couvre. Il faudra le represser a chaque ecran neuf.

### Ce dont je reste la seule garde sur ces trois jeux

Le garde-fou de doublon de proposition, l'inventaire du texte lu par l'enfant, la part du facile et
la cardinalite des indices distincts sont desormais tenus par un automate, verifie par mutation sur
materiau reel. Je les retire de ma liste. **Restent sans automate, et c'est moi, en jouant** :
l'exactitude arithmetique de chaque reponse, la plausibilite de chaque distracteur, le rang de la
bonne reponse parmi les trois propositions, l'utilite reelle de l'indice, et le niveau - la
lisibilite de l'enonce, la charge de ce que l'enfant tient en tete, et la progression au fil d'une
partie de dix manches.

## Cloture - sofia-sm, du 2026-08-15

> Cette section et les suivantes sont ecrites par `sofia-sm`, casquette Scrum Master, a la fusion
> des deux derniers lots du sprint (`s12-yanis-qualite`, `s12-marc-echantillon`). Aucune section
> pedagogique ci-dessus n'est modifiee.

- **Horodatage de fin** : 2026-08-15 15:06.
- **Horodatage de debut** : **aucun** - le sprint 12 n'a pas eu de ceremonie de planning ecrite
  (aucun fichier `planning.md` dans ce dossier), comme les sprints 2, 4 et 10 avant lui. Le premier
  commit `s12` fusionne sur `round-two` est horodate 2026-08-14 18:02:53 (`f63354a`, perimetre
  mathematiques CM1) : je le cite comme premier point observable dans le depot, pas comme un debut
  de sprint officiel. Je ne fabrique pas retroactivement un `planning.md` que personne n'a ecrit -
  constat porte en retro.
- **Duree effective** : non mesurable, faute d'un horodatage de debut officiel.
- **Commit de tete de `round-two` apres cette cloture** : `295e007`.

### Fusion des deux derniers lots

`round-two` etait a `f8d6a93` a l'ouverture de cette cloture (perimetre, developpements, portes
pedagogiques, matrice, decisions et corrections de redaction deja fusionnes). Deux lots restaient,
aucun des deux ne touchant le meme fichier que l'autre :

| Ordre | Lot | Branche | Tete | Contenu |
|---|---|---|---|---|
| 1 | `yanis-qa` | `s12-yanis-qualite` | `a543344` | un seul fichier neuf, `rapport-qualite.md` |
| 2 | `marc-dev` | `s12-marc-echantillon` | `a37402f` | `AdditionsBorneesContentSourceTest.java`, echantillon porte de 50 a 5000 tirages sur le test de non-negativite des distracteurs |

**Lignee verifiee au prealable** : `git merge-base round-two s12-yanis-qualite` et
`git merge-base round-two s12-marc-echantillon` rendent tous deux `7f6218a`, ancetre commun exact -
les deux branches partent du meme point, et `round-two` a progresse de deux commits `[REL]`
(`3f3f21a`, `f8d6a93`) entre-temps sans que cela ne cree de derive. Fusionnees par deux
`git merge --no-ff` successifs, dans l'ordre du tableau. **Aucun conflit sur aucune des deux
fusions.**

### Porte technique - etablie par moi, pas relayee

**Piege de methode, rencontre et evite avant de mesurer quoi que ce soit** : le premier
`mvn -B clean verify` a echoue par `No compiler is provided in this environment` - le `java` du
`PATH` est un JRE 8, pas un JDK. `bash outils/verifier-jdk.sh` rend le constat citable : JDK de
compilation `21.0.10`, trouve par le fichier de toolchains de Maven **present mais sous un nom que
Maven ne lit pas** (meme forme de piege que celle nommee dans `squad/README.md`, section 6, en
2026-08-07). `JAVA_HOME` designe sur ce JDK 21.0.10 le temps de la commande, jamais ecrit en dur.

`mvn -B clean verify` sur l'arbre fusionne final (`295e007`), apres un `clean` complet et non une
mesure incrementale : **BUILD SUCCESS**, six modules en SUCCESS, aucun test ignore.

| Module | Tests (bloc `Results:` du reacteur Maven, apres `clean`) |
|---|---|
| domain | 47 |
| application | 7 |
| adapter-web | 226 |
| adapter-content | 98 |
| bootstrap (surefire) | 80 |
| bootstrap (failsafe) | 6 |
| **Total** | **464** |

**464 confirme le chiffre annonce par `yanis-qa`**, methode identique (somme du bloc `Results:` de
chaque module, sur un build complet depuis un arbre propre).

**28 regles ArchUnit, confirme par moi en comptant, pas en relayant le chiffre d'un agent** :
recherche litterale de `@ArchTest` sous `src/test/java` des modules de code (fixture de
contre-exemple exclue, elle vit sous `src/test/resources`) rend **27** (1 dans `adapter-web`,
`AssertionsWebTest` ; 26 dans `bootstrap`, dix classes de `com.recreo.archi`). La 28e est
`ScanDeComposantsTest.aucuneClasseDeTestSurLeClasspathDeLApplicationNePorteDeStereotype`, une
`ArchRule` reelle invoquee a la main par `.check(...)` dans une methode `@Test` ordinaire plutot
que par le mecanisme `@ArchTest` - meme piege que celui deja nomme dans `squad/README.md`, section
4 ("compter les annotations `@ArchTest` sous-estime le total d'une unite"), verifie encore vrai ce
sprint. **28 = 27 + 1, methode et resultat conformes a `yanis-qa` et a `ADR-0040`** (qui affirme
lui-meme que le compte reste a 28, sa clause 3 n'etant pas une `ArchRule`).

**Garde-fou de chemins, rejoue par moi avant cette cloture** :

```
git ls-files -- . ':!.github/scripts/verifier-chemins-test.sh' | bash .github/scripts/verifier-chemins.sh
```

rend `Aucun chemin de poste de travail ni nom de compte dans les fichiers verifies.`, **code de
sortie 0**.

### Rapport qualite - yanis-qa

Fichier : `squad/sprints/s12-mathematiques-cm1/rapport-qualite.md`, ecrit sur `round-two` a
`7f6218a`, avant les deux lots fusionnes ci-dessus (aucun des deux ne touche du code de production
ni de test au-dela de l'echantillon de `marc-dev`, deja compte dans les 464).

- **464 tests, 28 regles, garde-fou de chemins en code 0**, confirmes par moi ci-dessus sur l'arbre
  fusionne final, memes methodes, memes resultats.
- **Cinq mutations sur les cinq garde-fous neufs du sprint, toutes sur du materiau de production
  reel, toutes restaurees**, `git status --porcelain` verifie vide avant la premiere et apres la
  derniere. La plus instructive : le clamp retire de la fabrique d'additions corrigee rend **vert
  a tort** un test existant (`auMoinsUnDistracteurPlausibleJamaisNegatifNiNonEntier`, 50 tirages sur
  une seule graine, trop peu pour atteindre le cas rare qui produirait un distracteur negatif) - pas
  un defaut de production, une **faiblesse de detection** du test voisin, remontee et non taise.
  C'est ce constat qui a produit le lot `marc-dev` fusionne ci-dessus (echantillon porte a 5000
  tirages, probabilite de rater le defaut tombee de 80 % a 2,6e-10).
- **Deux dettes plafonnees, mordant dans les deux sens (croissance et peremption), verifiees par
  mutation** : `DETTE_DE_RANG` et `DETTE_DE_SEUIL_MINIMUM`, chaque exemption tracee a un motif
  pedagogique de `nadia-prof`, jamais a une commodite.
- **Deux paquets a exactement 50,0 % pile** (`cp-soustractions` 12/24, `cp-problemes-additifs`
  11/22), rejoues depuis le code de production et confirmes a la decimale : un fait a inscrire (la
  comparaison stricte les fait tenir sur un seul item d'ecart), pas un risque a corriger d'urgence -
  `nadia-prof` les a deja values et le dit elle-meme.
- **Deux instances trouvees de la classe de defaut "un releve mesure autre chose que ce qu'il
  annonce"**, toutes deux deja auto-diagnostiquees par leur autrice avant que `yanis-qa` ne les
  verifie et les qualifie : celle de s0 (position a l'ecran creditee pour rang par valeur) et une
  seconde, neuve, sur la clause 4 d'`ADR-0040` (focus **declare** credite pour focus **atteint et
  visible**). Aucune troisieme instance trouvee malgre une recherche ciblee sur les cinq fiches et
  releves du sprint, mais `yanis-qa` refuse lui-meme de dire cette recherche exhaustive, faute d'un
  marqueur obligatoire equivalent a l'aveu de critere enumere pour cette classe de defaut - il
  propose le reflexe qui devient l'amendement de `squad/README.md` cite plus bas.

### Confirmation ArchUnit - theo-archi

Deux `ADR` neufs ce sprint, tous deux avec leur regle executable et sa morsure mesuree, pas
supposee :

- **`ADR-0039`** derive le critere d'un garde-fou des que sa promesse est universelle (une liste de
  **refus** est verte par defaut et laisse passer ce qu'elle n'a pas nomme ; une liste
  d'**autorisation** est rouge par defaut). Trois garde-fous reecrits sur cette base
  (`RessourcesTiercesTest`, `TexteAccentueDesGabaritsTest`, le recensement 3 de
  `LesGardeFousSontMisALEpreuveTest`), chacun avec sa mutation sur du materiau reel (`alpine.min.js`
  reel et correctement trace, entre vert avant le lot, rouge apres). Explicitement nomme non
  exhaustif par son auteur : "je ne peux pas affirmer que le depot ne compte qu'un critere enumere".
- **`ADR-0040`** interdit qu'un point de focus declare soit lui-meme destructeur et refuse
  d'exempter l'ecran de confirmation de la clause 2 d'`ADR-0038`, sur trois mutations mesurees (le
  focus sur le bouton destructeur, `M2`, etait **vert** avant ce lot - l'etat le plus dangereux que
  l'ecran pouvait prendre passait la CI). Sa clause 3 est celle que `s12-sofia-focus` encode.

**Le compte des regles ArchUnit reste a 28**, confirme independamment ci-dessus : aucun des deux
`ADR` n'ajoute d'`ArchRule` (l'un derive un critere sur des tests JUnit ordinaires, l'autre encode
sa clause dans un test d'`adapter-web` deja existant).

**Verifie par moi dans `ADR-index.md`, commit par commit, pas recopie de l'index seul** : trois
ADR anterieurs sont amendes ce sprint, tous les trois par les deux commits `ADR-0039`/`ADR-0040`
eux-memes, aucun ne change un compte. `ADR-0001` (declencheur d'`adapter-persistence` ferme par
`DEC-0028`, commit distinct de `theo-archi`) ; `ADR-0013` et `ADR-0014` (la case "critere enumere"
et la limite nommee sur `JAVASCRIPT_TIERS_REFUSE`, toutes deux corrigees par `ADR-0039`) ;
`ADR-0038` (clause 3 ajoutee par `ADR-0040`, qui ferme l'ecart que ses deux clauses d'origine ne
couvraient pas). **`ADR-0022` n'est pas de ce sprint** : son amendement de contexte (chiffre de
boite perime) est du 2026-08-14 a 13h42, avant la cloture de s11 (`62b13fa`, 16h37) - je le retire
d'une premiere version fautive de ce paragraphe plutot que de le laisser stagner comme un chiffre
non reverifie.

### Corrections - relecteur-pedagogique

**Deux commits `[REL]` ce sprint, aucun fichier `relecture.md` dedie** (comme pour s09, s10 et s11) :

- `3f3f21a` : "x10" (lettre x) corrige en "×10" (signe multiplie) dans le descripteur de competence
  de `cm1-tables-multiplication.yaml`, coherent avec le referentiel ; et une coupure de mot par
  retour a la ligne automatique recomposee dans `FICHE-bandit-manchot-additions-cp.md`. Aucun
  chiffre ni verdict touche.
- `f8d6a93` : tiret cadratin remplace par un tiret simple dans les quatre referentiels de
  competences (`CLAUDE.md` l'interdit sans exception) - verifie avant correction que toutes les
  citations litterales de ces intitules ailleurs dans le depot utilisaient deja un tiret simple, la
  correspondance etant donc deja rompue par la source depuis douze sprints, pas cassee par ce
  commit.

**Il m'est rapporte, sans artefact dedie sur le depot pour l'etablir moi-meme**, que le
`relecteur-pedagogique` a d'abord valide sur un JDK Temurin 25 en croyant le JDK 21 absent du
poste, alors que `outils/verifier-jdk.sh` (le meme outil que j'ai utilise ci-dessus) l'aurait donne,
et qu'il a rejoue et corrige lui-meme son propre rapport une fois l'ecart constate. Comme pour la
relecture de s11, je ne peux pas verifier ce recit sur un fichier ou un commit qui le tracerait :
je le rapporte comme une annonce, pas comme un fait constate, et je le classe comme obstacle de
methode dans le tableau ci-dessous, pas comme une faute.

### Avis de nadia-prof et Done pedagogique

**Les verdicts ecrits ci-dessus par `nadia-prof` (2026-08-14) sont son premier passage, pas l'etat
final.** Le meme jour et le lendemain, elle a rejoue deux des trois verdicts sur les branches de
correctif fusionnees entre-temps ; je l'etablis moi-meme sur `squad/programme/MATRICE-couverture.md`
et sur les blocs "Done pedagogique du 2026-08-15" des fiches de conformite citees, pas en relayant
une annonce :

| Story | Paquet | Verdict du 2026-08-14 | Verdict final (2026-08-15) |
|---|---|---|---|
| S12-1 | `cm1-perimetre.yaml` | VALIDE SOUS RESERVE | **inchange** - VALIDE SOUS RESERVE |
| S12-2 | `cm1-mesures.yaml` | VALIDE SOUS RESERVE | **inchange** - VALIDE SOUS RESERVE |
| S12-3 | `cm1-tables-multiplication.yaml` | REFUSE | **LEVE le 2026-08-15** - VALIDE SOUS RESERVE ("sans mesure d'automatisation"), apres correction de `lea-dev` (voisins du dessus) |
| non planifie | `additions-cp` (couvert-valide depuis s03) | REFUSE (retire le 2026-08-15 matin, 66,45 % sur le rang intermediaire) | **LEVE le 2026-08-15 apres-midi** - VALIDE sans reserve, apres correction de `marc-dev` (33,55 / 33,55 / 32,90 sur 12 000 manches) |

Aucun de ces deux refus n'a ete simule ni retire par moi : les deux LEVE sont prononces par
`nadia-prof` elle-meme, sur un jar reconstruit depuis son propre worktree, jamais sur parole. Le
verdict initial de chacun reste lisible en entier a sa place dans les fiches, non efface.

**Etat final des quatre Done pedagogiques de ce sprint** : trois `VALIDE` ou `VALIDE SOUS RESERVE`
(perimetre, mesures, tables), un `VALIDE` sans reserve retrouve le jour meme de sa perte
(additions). Aucun Done technique ne remplace ni ne simule ces verdicts - je ne fais ici que les
reconstruire depuis le depot.

### Tableau des obstacles du sprint

| Description | Categorie | Destinataire | Statut |
|---|---|---|---|
| Fabrique d'additions place mecaniquement la bonne reponse au rang intermediaire (66,45 %), Done pedagogique de douze sprints retire | technique, contenu | `marc-dev` | **clos** - `a479388`, revalide par `nadia-prof` a 33,55/33,55/32,90 |
| Distracteurs des tables toujours inferieurs au produit (64,4 % sur le rang le plus grand) | technique, contenu | `lea-dev` | **clos** - `4f0290d` puis correctif rang, revalide VALIDE SOUS RESERVE a 36,4 % |
| Test de non-negativite des distracteurs d'additions vert a tort sur un echantillon de 50 tirages a une graine (defaut reel a 0,44 % des tirages, 80 % de chances de le rater) | qualite, test | `marc-dev` | **clos** - `a37402f`, echantillon porte a 5000 tirages, probabilite de rater tombee a 2,6e-10 |
| P-6 (perimetre) enumerait deux familles de distracteurs et fabriquait un rang intermediaire a 90 % pour tout carre de cote >= 5 | pedagogie | `nadia-prof` | **clos** - condition neuve du 2026-08-15, non retroactive, versee a `FICHE-bandit-manchot-perimetre-cm1.md` |
| Un point de focus declare pouvait etre lui-meme destructeur (M2 : Entree seule, sans tabulation, detruisait la partie) | technique, architecture | `theo-archi` | **clos** - `ADR-0040`, clause 3 encodee et mordante |
| Critere enumere par liste de refus, vert par defaut sur un objet non nomme (`alpine.min.js` reel entre sans faire rougir `RessourcesTiercesTest`) | technique, architecture | `theo-archi` | **clos** - `ADR-0039`, trois garde-fous reecrits en liste d'autorisation ou en inventaire derive |
| Deux paquets a exactement 50,0 % sur le garde-fou de rang, un seul item d'ecart de chaque cote | pedagogie, qualite | `nadia-prof` (values), `yanis-qa` (nomme) | **note, pas un obstacle a router** - `nadia-prof` l'ecrit elle-meme comme juste, pas a deplacer |
| Reflexe de nommer ce qu'un releve automatique peut et ne peut pas voir, avant de le citer comme preuve (deuxieme instance de la classe apres s0) | process, gouvernance de ceremonie | `sofia-sm` (arbitrage) | **clos** - accepte, `squad/README.md` section 9 amende |
| Relecteur-pedagogique aurait valide sur JDK Temurin 25 en croyant le JDK 21 absent, puis rejoue et corrige lui-meme son rapport | process, methode | `relecteur-pedagogique` | **rapporte, non etabli sur le depot** - aucun fichier ni commit ne le trace, je ne peux pas le verifier moi-meme ; obstacle de methode, pas une faute |
| `confirmation-archunit.md` absent depuis s09, alors qu'`ADR-0013` en fait le lieu de la discipline de comptage de `theo-archi` | process, gouvernance de ceremonie | `theo-archi`, `sofia-sm` (suivi, deja repris a mon compte) | **encore ouvert** - quatrieme sprint d'affilee (s09 a s12) sans ce fichier ; la confirmation existe cette fois dans `review.md` (section ci-dessus), mais pas dans le fichier que l'ADR nomme |
| Deux reserves de `nadia-prof` sur les tables (Reserve A - un enonce sur cinq revient, plus lourd que l'estime initiale ; Reserve B - deux distracteurs hors de l'espace des tables) | pedagogie, produit | `camille-po` | **route, non clos** - `camille-po` les nomme hors perimetre de ce sprint, a transformer en stories |
| Libelle "x10" (lettre) contre "×10" (signe multiplie), duplique a six endroits du depot | redaction | `relecteur-pedagogique`, `camille-po` | **laisse ouvert a dessein** - `f8d6a93` ne touche que les quatre referentiels, le signe reste un point a trancher ailleurs |
| Reserve O de `nadia-prof`, seconde moitie ("aucun chemin vers le catalogue ne preserve la partie en cours") | produit | `camille-po` | **encore ouvert**, inchange depuis s11 |
| Story S12-5 (`aria-label="Menu principal"` refuse par `nadia-prof`, a remplacer par "Les jeux" ou rien) | produit, accessibilite | `camille-po` (priorite), `sofia-sm` (planning) | **encore ouvert** - ecrite au backlog, non affectee a un sprint |
| Reserve F11 de `nadia-prof` (emplacement accessible de l'enseigne, aucune ecoute reelle au lecteur d'ecran a ce jour) | pedagogie, accessibilite | `nadia-prof`, `ines-design` | **encore ouvert**, anterieur a s12, inchange |
| Quatorze worktrees de sprint sur le disque a l'ouverture de cette cloture | capacite | `sofia-sm` (casquette dev) | **clos** - les quatorze branches verifiees ancetres de `HEAD`, arbre propre sur chacune, worktrees supprimes |

Rappel du gabarit : un obstacle n'est `clos` que si le fichier qui le tranche existe physiquement.
Les deux obstacles marques "encore ouvert" ou "route, non clos" le restent tant qu'aucun `ADR`,
`DEC` ou fichier de relecture ne les tranche.

## Decisions prises

Aucun `ADR` ni aucune `DEC` n'est cree par cette cloture elle-meme : `ADR-0039`, `ADR-0040`,
`DEC-0028` et `DEC-0029`, ainsi que les amendements de ce sprint a `ADR-0001`, `ADR-0013`,
`ADR-0014` et `ADR-0038`, sont tous anterieurs a cette review, actes pendant le sprint et deja
fusionnes. Les decisions qui m'appartiennent en tant que Scrum Master :

1. **Les deux derniers lots (`yanis-qa`, `marc-dev`) sont fusionnes apres verification de
   lignee**, `git merge-base` rendant l'ancetre commun exact pour les deux avant tout
   `git merge --no-ff`.
2. **`mvn -B clean verify` est rejoue independamment sur l'arbre fusionne final**, apres avoir
   diagnostique et contourne un JDK 8 par defaut sur le `PATH` avec `outils/verifier-jdk.sh` : 464
   tests, 28 regles, garde-fou de chemins en code 0 - memes chiffres que `yanis-qa`, memes methodes.
3. **La proposition de `yanis-qa`** (nommer ce qu'un releve automatique peut et ne peut pas voir,
   avant de le citer comme preuve) **est acceptee** et actee dans `squad/README.md`, section 9.
4. **Quatorze worktrees et quatorze branches de sprint sont nettoyes** (casquette developpeuse),
   chacun verifie ancetre de `HEAD` avant suppression.
5. **Le recit non trace du `relecteur-pedagogique` sur son propre JDK est rapporte comme une
   annonce, pas etabli comme un fait** : aucun commit ni fichier ne le porte, je ne le fabrique pas
   pour clore l'obstacle a sa place.
6. **`confirmation-archunit.md` reste absent pour la quatrieme fois d'affilee (s09 a s12)** : je ne
   fabrique pas ce fichier a la place de `theo-archi`. Le contenu qu'il devrait porter vit dans la
   section "Confirmation ArchUnit" ci-dessus, sourcee sur `ADR-0039` et `ADR-0040` eux-memes. Point
   porte en retro.
