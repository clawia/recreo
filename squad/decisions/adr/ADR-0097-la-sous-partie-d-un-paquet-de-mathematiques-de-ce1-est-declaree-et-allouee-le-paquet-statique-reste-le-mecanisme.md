# ADR-0097 - Declarer la sous-partie de `MAT-CE1-03` dans le descripteur du paquet et l'allouer en un seul endroit, et servir les soustractions et les moities de CE1 par des paquets statiques

- **Statut** : Accepte. **Etendu par `ADR-0098` le 2026-09-22** (s39) : l'allocation de la decision 2
  nomme desormais la ligne de referentiel de chaque sous-partie et ne vaut plus pour `MAT-CE1-03`
  seule. La decision et la regle ci-dessous sont inchangees ; seule leur portee s'elargit.
- **Date** : 2026-09-22
- **Auteur** : theo-archi
- **Sprint** : s38
- **Route par** : `sofia-sm`,
  `squad/sprints/s38-fermeture-mat-ce1-03/routage-complements-dizaine-et-adr-mecanisme.md`,
  routage 2 - *"rediger l'ADR qui tranche le mecanisme (statique) pour `S38-SOUSTRACTIONS` et
  `S38-MOITIES`, avec sa regle ecrite dans le meme geste, et la question de conception de ta
  section 9 tranchee"*. Entree pedagogique :
  `squad/conformite/ARBITRAGE-s38-soustractions-complements-dizaine-et-moities-ce1.md`
  (`nadia-prof`, 2026-09-22), six clauses chiffrees.

## Contexte

`ADR-0096` a decide le paquet statique **pour le premier paquet CE1, nommement** : sa decision 3
(*"la regle est la table, jamais le moyen"*) est generale, sa decision 2 ne l'est pas. Trois paquets
neufs de `MAT-CE1-03` - soustractions, complements a la dizaine, moities - reposent donc la meme
question, et aucun `ADR` en vigueur ne la couvre. C'est une question posee trois fois, pas trois
questions : un seul `ADR` suffit.

Une seconde question, absente de s37, se pose avec ces paquets-ci, et c'est la seule vraie question
de conception du sprint. Le perimetre d'un garde-fou derive doit se deduire de la **forme des
items**, jamais d'une liste de noms (`ADR-0013`). Pour les additions, cette derivation est
mecanique : `ChaquePaquetDAdditionsDeCe1TientSaRepartitionParFormeTest` reconnait `a + b = ?` par un
motif sur l'enonce, et un paquet d'additions neuf entre dans sa population le jour ou il entre.
**Pour les moities, elle n'existe pas** : l'enonce est du francais libre - *"Quelle est la moitie de
58 ?"* - et un motif sur le mot *moitie* serait un perimetre derive **en apparence seulement**. Un
paquet neuf qui formulerait autrement - *"58, c'est le double de quel nombre ?"* - sortirait de la
population **en silence**, et son garde-fou passerait au vert sans avoir rien lu. C'est la famille de
defaut que ce depot sanctionne le plus souvent, et elle serait ici de ma main.

Le depot declare deja la **ligne de referentiel** d'un paquet (`lignesDeReferentiel`, `ADR-0081`,
codes alloues dans un registre). Verifie avant d'ecrire quoi que ce soit : cette declaration est
d'un cran trop grossiere pour ce qui nous occupe - les quatre paquets de `MAT-CE1-03` portent tous
`MAT-CE1-03`, et c'est correct. Ce qui manque est la **sous-partie**, un cran en dessous de la ligne.

## Decision

**1. Les paquets de `S38-SOUSTRACTIONS` et de `S38-MOITIES` sont des paquets statiques de type
`question`. Le generateur `additions-bornees` n'est pas etendu, et aucune `ContentSource` neuve
n'est ecrite.** La meme decision vaut pour `S38-COMPLEMENTS-DIZAINE` : la question du mecanisme y
est identique mot pour mot, et rien dans ses clauses ne demande un tirage. Ce lot n'est pas traite a
part ici pour autant - son obstacle etait pedagogique, pas technique.

**2. La sous-partie de `MAT-CE1-03` qu'un paquet sert est declaree dans son descripteur, par sa cle
`competence`, et cette declaration est allouee en un seul endroit du depot** -
`SousPartieDeMathematiquesCe1`, dans le fichier de la regle ci-dessous. Un garde-fou de repartition
par forme derive sa population **de cette allocation**, jamais d'un motif sur le francais d'un
enonce.

**3. Un paquet de mathematiques de CE1 qui ne declare aucune competence, ou qui en declare une que
l'allocation ne connait pas, est rouge.** Un paquet muet n'est pas un paquet exempte. C'est la
moitie de la decision 2 qui interdit quelque chose : sans elle, l'allocation serait une convention,
c'est-a-dire un souhait.

**4. Aucune cle neuve n'entre au schema de paquet, et aucune ligne de Java de production n'est
ecrite.** La cle `competence` existe, elle est deja chargee par `YamlContentPackLoader` et declaree
par **les 75 paquets** du contenu servi (comptage du 2026-09-22 : fichiers `.yaml` de
`adapters/adapter-content/src/main/resources/contenu/`) ; lui ajouter une `sousPartie` jumelle serait
une abstraction pour un usage qu'une donnee existante couvre deja.

**Ce que cette decision ne fait pas.** Elle ne touche a aucune clause pedagogique : les six clauses
de `nadia-prof` restent ce qu'elle a ecrit. Elle ne prononce aucun Done. Elle n'affecte personne
(`DEC-0091`, section 2.2). Et elle ne debloque pas `S38-COMPLEMENTS-DIZAINE` : ce n'etait pas en son
pouvoir. L'obstacle de ce lot etait pedagogique - la section 2.3 de l'arbitrage rendait `d`
systematiquement la plus grande des trois propositions, donc le rang *"la plus grande"* a 0 % sur
tout paquet conforme, refuse par `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` sans
recours d'exemption (avis de faisabilite de s38, section 10). **Il a ete leve par `nadia-prof`
pendant la redaction de cet `ADR`** (amendement du 2026-09-22, commit `52d899ad` : le second
distracteur devient `d - a - 1`, avec deux cas de repli sur `d - a + 1`, et elle verifie les neuf
valeurs de `unite(a)` a la main). La sous-partie de ce lot est donc allouee ici comme les deux
autres, et sa decision de mecanisme est la meme. Une allocation n'est toujours pas une autorisation
d'ecrire le paquet : l'affectation reste a `sofia-sm`.

## Justification de la decision 1, sur ce qui a ete mesure

Les cinq motifs d'`ADR-0096` se transposent ; deux se renforcent, et un troisieme est neuf.

**1. La condition de reversibilite qu'`ADR-0096` s'etait ecrite n'est pas atteinte.** Elle disait :
*"ce qui me ferait changer d'avis : un deuxieme paquet demandant la meme table"*. `SOU-CE1-2` (sept
cases, emprunt) et `MOI-CE1-2` (quatre cases, parite) sont des tables **differentes** d'`ADD-CE1-2`
et l'une de l'autre. Chaque table sert un paquet et un seul : il n'y a rien a mutualiser, et un
generateur parametre par trois tables disjointes serait l'usage anticipe sous son nom le plus
classique.

**2. Le juge reste independant de ce qu'il juge** (`ADR-0096`, justification 4). La partition doit
etre encodee de toute facon pour le garde-fou ; avec un paquet statique elle est ecrite une fois,
dans le test, et rien en production ne la connait.

**3. Les deux tables sont satisfaisables en comptes entiers, pas seulement en pourcentages, et sur
la taille de paquet que le depot pratique deja.** `nadia-prof` a verifie la satisfaisabilite en
pourcentages ; sur un paquet fini, la quantification peut rendre une bande inatteignable. Mesure sur
32 items, la taille du paquet de s37, bornes incluses : `SOU-CE1-2` admet `(0, 0, 6, 5, 7, 7, 7)`,
`MOI-CE1-2` admet `(0, 14, 9, 9)`. Aucune des deux tables n'impose donc de taille de paquet
particuliere, et aucune ne demande le tirage continu qu'un generateur offrirait.

**4. Une seule contrainte de dimensionnement, et elle borne le statique par le haut** : la forme 3
de `MOI-CE1-2` n'offre que **huit** valeurs de `n` (multiples de dix de 30 a 100), et sa bande
15-30 % plafonne donc un paquet sans enonce repete a environ **53** items. Sans effet a 32 ; c'est
le seul chiffre qui ferait pencher vers le generatif, et il ne mord qu'au-dela d'une taille que
`nadia-prof` n'a jamais demandee.

**5. Cout constate, pas estime.** Le patron exact de `S37-1` (commit `9412f659`) est de cinq
fichiers et **zero ligne de Java de production** : une entree dans `catalogue.yaml`, un YAML de
contenu, un garde-fou derive, son jeu fautif commite (`ADR-0013`, obligation 3), et l'extension de
l'inventaire de `TexteAccentueExposeParLePortEntrantTest`. Deux lots, deux fois ce patron.

**Ce qui me ferait changer d'avis, ecrit maintenant plutot que decouvert plus tard** : une taille de
paquet demandee au-dela d'environ 53 items sur les moities, ou deux sous-parties demandant la meme
table. La regle etant la table et la declaration, jamais le moyen, ce changement se paierait en
production seule.

## Justification de la decision 2, et pourquoi elle est la moitie qui compte

La decision 1 choisit entre deux implementations qui passeraient **toutes les deux** tous les
garde-fous du depot. C'est exactement pourquoi `ADR-0096` decision 3 a refuse d'encoder le moyen, et
je ne l'encode pas davantage : une regle qui figerait le type du paquet fermerait une reversibilite
qui ne coute rien a garder ouverte, et n'interdirait rien qu'une table ne verifie deja.

La decision 2, elle, a un mode de defaillance **muet**, et c'est le seul du sprint : un paquet qui
sort de la population d'un garde-fou sans que rien ne rougisse. Deriver le perimetre d'un motif sur
l'enonce marche pour l'arithmetique ecrite en chiffres et **cesse de marcher** des que l'enonce est
une phrase. Les trois options examinees sont en fin de document ; celle-ci est retenue parce
qu'elle est la seule ou le geste fautif **coute un rouge** au lieu d'etre gratuit.

**Sur la formulation des trois competences allouees.** Ce n'est pas une decision de contenu : la
competence est une etiquette de descripteur, elle n'est montree a aucun enfant - verifie, aucun
gabarit ne la rend - et sa formulation suit la convention des competences de calcul mental deja
servies (*"Calcul mental - additions dans la limite de 100"*, *"Calcul mental - soustractions dans
la limite de 10"*). Si `nadia-prof` ou le developpeur affecte prefere une autre formulation, elle se
change dans l'allocation et dans le paquet, **dans le meme diff** : c'est precisement la visibilite
recherchee, et non un droit de veto de l'architecte sur un libelle.

## Regle executable

Fichier :
`adapters/adapter-content/src/test/java/com/recreo/adapter/content/ChaquePaquetDeMathematiquesDeCe1DeclareSaSousPartieTest.java`
Jeu fautif commite :
`adapters/adapter-content/src/test/resources/contreexemple/ChaquePaquetDeMathematiquesDeCe1DeclareSaSousPartieTest/`

**Ce n'est pas une regle ArchUnit, et le motif est celui d'`ADR-0096` et de la troisieme regle
d'`ADR-0095`** : la population est du YAML et des descripteurs, pas du bytecode. Aucune propriete de
cette decision n'est encodable en ArchUnit - ni la declaration d'un paquet, ni son allocation, ni le
type d'un contenu - et rien d'autre qu'un garde-fou de contenu ne peut les tenir. L'automate qui
garde la decision est donc celui-ci, execute par `mvn verify` dans la suite de `adapter-content`, et
recense par `LesGardeFousSontMisALEpreuveTest` qui exige son jeu fautif. Le compte de regles
ArchUnit du depot est **inchange**, et il est mesure apres l'ajout de ce fichier, pas recopie
d'`ADR-0096` : **36 `static final ArchRule`, 35 `@ArchTest`**, le 2026-09-22. Methode, identique a
celle d'`ADR-0095` : occurrences litterales de ces deux chaines dans `src/test/java` des cinq modules
declares au pom agregateur, `target/` exclu. Les deux comptes different d'une unite pour la cause
qu'`ADR-0094` a nommee - une `ArchRule` du depot n'est pas annotee `@ArchTest`.

**Ce que la regle interdit reellement.** Un paquet servi dont le descripteur declare le niveau `CE1`
et la matiere *Mathematiques* doit declarer une competence figurant dans
`SousPartieDeMathematiquesCe1`. Concretement, elle interdit le geste suivant, qui serait aujourd'hui
gratuit et invisible : ecrire le paquet de moities avec sa propre formulation de competence, et
livrer a cote un garde-fou `MOI-CE1-2` qui ne le lirait jamais. Elle ne dit pas que la declaration
est **vraie** - qu'un paquet declarant les moities serve reellement des moities - et ne le dira
jamais ; c'est le premier des six points que `nadia-prof` garde seule (arbitrage, section 5).

**Le perimetre est derive, et ses deux cles sont gardees ailleurs**, ce qui empeche d'en sortir par
une faute de frappe : le niveau par `ChaqueNiveauServiEstNommeDansLaSequenceTest` (`ADR-0095`), la
matiere par `UneMatiereNAQuUneSeuleGraphieDansLeContenuServiTest` (`ADR-0049`). La matiere est
comparee sur sa forme repliee - sans accent, sans casse, sans blanc de bord - pour la meme raison
qu'`ADR-0049` la replie ; la competence, elle, est comparee **exactement**, parce qu'elle sert de
cle a un garde-fou derive et que deux cles qui different d'un accent sont deux cles.

**Polarite** (`ADR-0039`) : l'allocation est du cote qui **exige une entree**, jamais du cote qui
dispense. Un paquet qu'elle ne nomme pas est rouge, pas vert - meme forme que la sequence
d'`ADR-0095`, et l'inverse d'une liste de refus. Elle n'entre donc pas au recensement des criteres
enumeres.

**Huit mises a l'epreuve**, dont la premiere est celle qui compte :

1. **Le paquet de moities qui formule sa sous-partie autrement** - le geste exact que la section 9
   de l'avis de faisabilite decrit, et le seul qui serait aujourd'hui muet.
2. **Le paquet muet** : aucune competence declaree, donc rien a contredire.
3. **L'ecart d'un seul accent** sur une chaine par ailleurs identique.
4. Une **population vide** (`ADR-0051`).
5. Le **jeu fautif commite** - quatre paquets, une seule faute - qui traverse la lecture disque et le
   balayage du repertoire, et prouve que la regle **discrimine**.
6. Le **perimetre derive dans ses deux dimensions** : un paquet de francais de CE1 et un paquet de
   mathematiques de CM1, tous deux sans competence allouee, restent hors de la population.
7. Le **repliage de la matiere**, prouve sur l'accent, la casse et le blanc de bord, et prouve
   **negativement** sur *"Maths"* que le repliage ne pretend pas fusionner deux mots differents.
8. Les quatre competences allouees sont **distinctes** : deux entrees identiques rendraient la cle
   ambigue.

**Le jeu fautif ecrit sa matiere sans accent a dessein.** Sa presence dans le recensement est ce qui
prouve que le repliage fonctionne aussi a la lecture disque, et pas seulement sur des chaines
fabriquees en memoire - la fixture ecrite par l'auteur de la regle ressemble a ce qu'il imagine, et
c'est la seule facon a ma portee de la faire ressembler a ce qui s'ecrit reellement.

## Consequences

- **Les garde-fous de `SOU-CE1-2` et de `MOI-CE1-2` peuvent etre ecrits avec un perimetre derive
  sur.** Ils selectionnent `SousPartieDeMathematiquesCe1.SOUSTRACTIONS` ou `MOITIES` et lisent
  l'expression arithmetique **dans les items** du paquet ainsi selectionne. La difference avec s37
  est que la selection ne depend plus de la forme de l'enonce.
- **Un paquet de mathematiques de CE1 hors de `MAT-CE1-03` devra allouer sa sous-partie.** C'est le
  prix de cette decision et il est reel : le jour ou un paquet de geometrie de CE1 entre, il faut
  une ligne de plus dans l'allocation, sans quoi le build est rouge. Le perimetre aurait pu etre
  retreci aux seuls paquets de calcul mental, mais il se serait alors derive de la chaine meme qui
  est jugee - un paquet en sortirait en renommant sa competence, c'est-a-dire par le geste exact que
  cette regle existe pour interdire.
- **L'allocation peut grandir, et c'est voulu.** Ce n'est pas une dette plafonnee : une sous-partie
  neuve est un fait legitime, pas un defaut a resorber. Ce que la regle garantit n'est pas que la
  liste soit courte, c'est qu'elle soit **le seul endroit** ou une sous-partie existe, et qu'y
  entrer demande un diff.
- **Chaque paquet neuf etend l'inventaire de `TexteAccentueExposeParLePortEntrantTest`** de son
  titre, de sa consigne et de sa competence - les deux competences allouees ici portent des accents
  (*compléments à*, *moitiés*), et elles sont fautives si le paquet les sert sans.
- **Ce que la decision 1 laisse ouvert** : un mecanisme generatif qui tiendrait sa table et
  declarerait sa sous-partie passerait les deux regles sans qu'une ligne bouge. La reversibilite
  d'`ADR-0096` decision 3 est conservee telle quelle.
- **Les contraintes du depot qui s'appliquent aux paquets neufs sont inchangees** et ne sont pas
  recopiees ici : indice obligatoire sur chaque item (`ChaquePaquetPorteUnIndiceTest`, plafond de
  dette a 0), blanc insecable devant `?` `:` `!` `;` (`ADR-0067`), titre sans niveau (`ADR-0078`),
  ecran unique par item, rang de la bonne reponse non predictible. Elles sont listees dans l'avis de
  faisabilite de s38, section 12.

## Alternatives considerees

**1. Un motif sur l'enonce, comme pour les additions.** La conception de s37, transposee. Ecartee
pour les moities et pour elles seules : `a - b = ?` se reconnait, *"Quelle est la moitie de 58 ?"* ne
se reconnait que si l'on devine la phrase que l'auteur a ecrite. Un motif sur le mot *moitie* serait
un perimetre derive en apparence, vert par defaut sur toute autre formulation - et vert **en
silence**. Retenue pour les soustractions ? Non : deux mecanismes de perimetre pour quatre paquets de
la meme ligne, c'est deux choses a comprendre la ou une suffit, et celle qui resterait serait la plus
fragile.

**2. Une cle `sousPartie` neuve dans le descripteur.** Ma preference initiale, ecrite en section 9 de
l'avis de faisabilite - *"faire declarer sa forme au descripteur du paquet"* - et je la retrecis
apres avoir lu le chargeur. Elle demande un champ de plus a `ContentDescriptor` (`domain`), sa
lecture dans `YamlContentPackLoader`, et la mise a jour des **24** constructions du record
(occurrences de `new ContentDescriptor(` hors `target/`, mesurees le 2026-09-22). C'est du code
de production pour une information que `competence` porte deja et que tous les paquets declarent. La
decision est la meme - la forme se declare, elle ne se devine pas - et elle est prise sur la donnee
existante. **Ce qui me la ferait reprendre** : le jour ou deux paquets de la meme sous-partie
devraient porter deux competences differentes, ou l'inverse. Aucun des deux n'est constate.

**3. Un registre en Markdown, du genre de `REGISTRE-identifiants.md`.** Ecartee : un registre qu'aucun
test ne lit est un souhait, et celui-ci doit etre lu par les garde-fous de repartition eux-memes.
L'allocation vit donc dans le code de test, la ou elle est a la fois executable et citable.

**4. Enumerer les paquets gardes par leur identifiant.** Ecartee par `ADR-0013`, et pour la raison
qu'il ecrit : un perimetre enumere echoue par omission autant que par inclusion, et le paquet neuf
qui oublie de s'y inscrire est exactement celui qu'on voulait garder.

**5. Etendre le generateur `additions-bornees` a la soustraction et a la moitie.** Ecartee sur les
cinq motifs de la decision 1, et sur le second en premier. La conception generative qu'`ADR-0096`
avait ecrite pour n'avoir pas a etre retrouvee vaut telle quelle ici : partition calculee au
constructeur, vecteur de poids, tirage dans la case. Elle reste disponible.
