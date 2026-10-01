# ADR-0103 - La repartition par forme des additions de CE1 garde les paquets qui declarent `MAT-CE1-03`, et un paquet qui declare honnetement `MAT-CE1-04` en sort

- **Statut** : Accepte
- **Date** : 2026-10-01
- **Sprint** : s45
- **Auteur** : `theo-archi`
- **Route par** : mon propre avis de faisabilite de s45
  (`squad/rapports/avis-faisabilite-s45-cinq-lots.md`, section 4.1 et question Q8), route par
  `sofia-sm` avant que le paquet de `S45-MAT-ADDITION` (`MAT-CE1-04`) ne soit code
- **Arbre mesure** : worktree `s45-theo-adr-0103`, branche partie de `round-two` a `c6dcba81`. JDK
  **21.0.10**, version que la machine virtuelle des tests rapporte d'elle-meme dans le rapport
  Surefire (`ADR-0071`). Toute mesure partielle du reacteur porte `-am` et nomme son module
- **Amende** : `ADR-0096`, decision 1 (le perimetre du garde-fou d'`ADD-CE1-2`)
- **Prolonge** : `ADR-0013` (perimetre derive), `ADR-0081` (`lignesDeReferentiel` obligatoire)

## Contexte

`ChaquePaquetDAdditionsDeCe1TientSaRepartitionParFormeTest` (`ADR-0096`, decision 1) prend pour
population tout paquet de niveau `CE1` dont **tous** les items reconnaissent le motif `a + b = ?`.
La ligne de referentiel declaree n'y est pas lue. Sur cette population il exige les huit bandes
d'`ADD-CE1-2`, dont `SOMME_JUSQUA_20` entre 10 et 20 %.

`S45-MAT-ADDITION` engage `MAT-CE1-04`, l'addition posee avec retenue. **Un paquet de cette ligne
ne peut pas tenir la deuxieme bande, et c'est une propriete, pas un tirage.** `ADP-CE1-2`
(`squad/conformite/ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-1.md`, section 4.2) sert des
nombres a deux et a trois chiffres, et une retenue dans tout item. Deux operandes d'au moins deux
chiffres donnent une somme d'au moins 20, et la seule somme egale a 20 est `10 + 10`, sans
retenue. La proportion de `somme <= 20` vaut donc 0 % quel que soit le contenu. Le motif plus
general est l'intitule meme d'`ADD-CE1-2` : *"dans un paquet d'additions de CE1 a
`sommeMaximale = 100`"*. La table est celle de l'echelle 100 et de `MAT-CE1-03` ; l'appliquer a
`MAT-CE1-04` l'applique hors de son domaine.

La forme naturelle d'un enonce de `MAT-CE1-04` est celle du precedent de calcul pose
(`cm1-multiplication-posee`, `"47 × 6 = ?"`), exactement la forme que le motif reconnait. Le
garde-fou serait rouge a coup sur sur le premier paquet honnete de cette ligne.

Le garde-fou fait pourtant ce pour quoi il existe : sa troisieme mise a l'epreuve,
`unPaquetQuiBasculeSurLAdditionAvecRetenueEstRefuse`, est un paquet qui **declare** `MAT-CE1-03` et
sert `MAT-CE1-04`. Le defaut n'est pas dans le critere, il est dans le perimetre, qui ne lit pas la
declaration.

## Decision

**1. La population d'`ADD-CE1-2` est l'ensemble des paquets de niveau `CE1`, dont tous les items
sont des additions, et qui declarent `MAT-CE1-03` parmi leurs `lignesDeReferentiel`.** C'est un
perimetre derive (`ADR-0013`), pas une enumeration : la cle est deja obligatoire sur chaque paquet
servi et chacun de ses codes resout (`ADR-0081`). Un paquet qui declare plusieurs lignes dont
`MAT-CE1-03` y entre : s'il se dit servir cette ligne, il en tient la table.

**2. Le critere ne change pas.** Les huit bandes, leur ordre et le juge `formeDe` sont ceux
d'`ADR-0096`, mot pour mot.

**3. Le glissement inverse n'est pas confie a ce garde-fou.** Servir `MAT-CE1-03` sous le code
`MAT-CE1-04` est refuse par `ADP-CE1-2` (retenue dans tout item) et `ADP-CE1-3` (deux tiers des
items a trois chiffres ou a somme superieure a 100), encodes dans le test du paquet de
`MAT-CE1-04`. Un second paquet de cette ligne aurait a porter les memes assertions.

**4. `ChaquePaquetDeSoustractionsDeCe1TientSaRepartitionParFormeTest` n'est pas touche, et il n'a
pas a l'etre.** Mon avis (section 4.1) ecrivait qu'il *"a la meme construction"* et qu'il faudrait
le changer le jour ou `MAT-CE1-05` serait engagee. **C'est faux, et je l'ai constate au code** : son
perimetre ne derive pas du motif `a - b = ?` mais de la competence declaree, celle qu'alloue
`SousPartieDeMathematiquesCe1.SOUSTRACTIONS` (`ADR-0097`, decision 2). Cette allocation nomme
`MAT-CE1-03`, et `ChaquePaquetDeMathematiquesDeCe1DeclareSaSousPartieTest` rend rouge tout paquet
qui porte cette competence sans declarer cette ligne (`ADR-0098`). Un paquet de `MAT-CE1-05`
declarera donc une sous-partie neuve et sortira de la population sans qu'une ligne de ce test
bouge. `MAT-CE1-05` n'est pas engagee et aucun paquet ne la sert aujourd'hui : rien n'est a
verifier de plus avant qu'elle le soit.

## Regle executable ArchUnit

Fichier :
`adapters/adapter-content/src/test/java/com/recreo/adapter/content/ChaquePaquetDAdditionsDeCe1TientSaRepartitionParFormeTest.java`

Ce n'est pas une regle ArchUnit, pour le motif qu'`ADR-0096` a deja ecrit : la population est du
YAML et des couples d'entiers, pas du bytecode. Le mecanisme automatique qui la garde est ce test
de contenu, lance par `mvn -B clean verify` dans le job `build` de la CI.

**Le predicat** : le recensement ne retient un paquet que si `lignesDeReferentiel` est une liste
dont un element vaut `MAT-CE1-03` apres retrait des blancs de bord (constante `LIGNE_GARDEE`).

**Trois mises a l'epreuve neuves**, et un jeu fautif etendu d'un paquet
(`src/test/resources/contreexemple/ChaquePaquetDAdditionsDeCe1TientSaRepartitionParFormeTest/contenu/paquet-avec-retenue-ce1.yaml`,
dix additions avec retenue a deux et a trois chiffres, `lignesDeReferentiel: ["MAT-CE1-04"]`) :

1. `unPaquetDAdditionsDeCe1QuiDeclareMatCe104SortDuPerimetre` : ce paquet n'est pas dans la
   population, donc vert sans tenir les huit bandes.
2. `lePaquetDeMatCe104RedeclareMatCe103EntreDansLaPopulationEtRougit` : le meme fichier, dont seule
   la declaration redevient `MAT-CE1-03`, est la seule entree de la population, et le juge le
   refuse sur `somme <= 20` et `avec retenue`. C'est la mise a l'epreuve 3, cette fois traversee
   par la lecture disque et le recensement.
3. `lePaquetServiRedeclareMatCe104SortDeLaPopulation` : sur **materiau reel**, le paquet servi
   `ce1-additions-jusqu-a-100`, dont seule la declaration devient `MAT-CE1-04`, sort de la
   population. Le predicat lit donc la cle sous la forme ou le depot l'ecrit, et pas seulement sous
   celle de ma fixture.

`leJeuFautifCommiteEstRefuseSurSonSeulPaquetFautif`, qui exige `containsExactly` sur les deux
paquets de `MAT-CE1-03` du jeu fautif, devient une quatrieme preuve sans changer : le paquet de
`MAT-CE1-04` est desormais dans le meme repertoire.

**Ce qu'elle interdit reellement, mesure par mutation.** Le predicat retire (la ligne
`noneMatch(... LIGNE_GARDEE ...)` supprimee), la classe passe de 13 verts a **3 rouges sur 13** :
les mises a l'epreuve 1 et 3 et le `containsExactly` du jeu fautif. La mise a l'epreuve 2 reste
verte dans les deux etats, et c'est attendu : elle prouve l'inclusion, que la mutation n'enleve
pas. La mise a l'epreuve 3 d'`ADR-0096` reste verte : le juge `verifier` ne lit pas la
declaration et n'a pas change. Commande, sur le module qui porte le test :
`bash outils/mvn-jdk21.sh -B -pl adapters/adapter-content -am test
-Dtest=ChaquePaquetDAdditionsDeCe1TientSaRepartitionParFormeTest
-Dsurefire.failIfNoSpecifiedTests=false`, puis lecture du rapport Surefire de la classe.

**La population reelle ne perd rien.** Methode : le recensement reproduit hors Java (meme motif,
meme classe de blancs, niveau `CE1`, tous les items reconnus) sur **tous** les fichiers YAML de
`adapters/adapter-content/src/main/resources/contenu/`, servis ou non, a `c6dcba81`. Avant le
changement, la population compte **un** paquet, `ce1-additions-jusqu-a-100`, qui declare
`["MAT-CE1-03"]` ; apres, le meme. `ce1-complements-dizaine` (`21 + ? = 30`) et `ce1-moities` ne
reconnaissent pas le motif et n'y etaient deja pas. `leRecensementTrouveLePaquetDAdditionsServi`
le confirme cote Java apres le changement.

## Consequences

- Le premier paquet de `MAT-CE1-04` peut etre ecrit sur la forme servie sans faire rougir un
  garde-fou qui ne le concerne pas. C'est le seul gain, et il est la condition de `S45-MAT-ADDITION`.
- **Un trou est ouvert, et il est nomme.** Un paquet d'additions de CE1 qui declare une autre ligne
  que `MAT-CE1-03` - par erreur ou pour s'evader - n'est plus juge par `ADD-CE1-2`. `ADR-0081` ne
  verifie que la resolution d'un code, jamais sa verite. Ce qui le referme en partie :
  `ChaquePaquetDeMathematiquesDeCe1DeclareSaSousPartieTest` interdit de garder la competence des
  additions de `MAT-CE1-03` en declarant une autre ligne (`ADR-0098`), si bien que s'evader demande
  d'ecrire **deux** declarations fausses, la ligne et la competence, visibles au diff. Le reste est a
  `nadia-prof`, qui lit la ligne declaree a l'audit.
- Le glissement de `MAT-CE1-03` sous le code `MAT-CE1-04` n'est tenu que par un test de paquet, pas
  par un garde-fou a perimetre derive. Un second paquet de `MAT-CE1-04` qui omettrait ces
  assertions ne serait pas juge.
- **Constat fait en lisant le recensement, que je ne corrige pas ici** : la Javadoc d'`ADR-0096`
  disait la population faite de paquets *servis*, mais `retenir` parcourt tous les fichiers YAML du
  repertoire de contenu sans croiser la liste des entrees servies, qui ne sert qu'au controle de
  non-vacuite. A `c6dcba81` les deux lectures donnent la meme population. La Javadoc de la classe
  ne dit plus *servis* ; le comportement est inchange.

## Alternatives considerees

**1. Ecrire les enonces de `MAT-CE1-04` autrement** (`"Pose et calcule 247 + 186."`) pour que le
motif ne les reconnaisse plus. Ecartee : le rouge disparait, mais le perimetre d'`ADD-CE1-2`
dependrait alors d'une formulation, et un paquet de `MAT-CE1-03` ecrit de la meme facon en
sortirait sans bruit. C'est un faux vert construit.

**2. Exclure les paquets qui declarent `MAT-CE1-04`, au lieu d'inclure ceux qui declarent
`MAT-CE1-03`.** Le trou de la deuxieme consequence serait plus etroit. Ecartee : c'est une liste
d'exceptions, qui grandirait avec chaque ligne de CE1 dont les items seraient des additions, et elle
dirait ce que la regle n'est pas plutot que ce qu'elle est. `ADD-CE1-2` est une condition de
`MAT-CE1-03`.

**3. Deriver le perimetre de la competence declaree**, comme le jumeau des soustractions
(`ADR-0097`, decision 2). Ecartee pour l'instant : sur le depot reel elle donne la meme population,
et elle sortirait du perimetre un paquet de `MAT-CE1-03` dont tous les items sont des additions
mais qui declarerait une autre sous-partie. Aucun n'existe ; c'est donc ce qui me ferait changer
d'avis (ci-dessous), pas un motif de choix.

**4. Exempter `MAT-CE1-04` par une liste nommee de paquets.** Ecartee par `ADR-0013` : un perimetre
enumere echoue en silence.

## Ce qui me ferait changer d'avis

- Une **seconde sous-partie de `MAT-CE1-03` dont tous les items sont des additions** (des doubles,
  par exemple), qui demanderait sa propre table. Le perimetre se deriverait alors de la competence
  allouee, comme celui des soustractions.
- Un paquet qui servirait `MAT-CE1-03` et `MAT-CE1-04` a la fois en declarant sa ligne **item par
  item**. La population se definirait alors par item et non par paquet. Aucun paquet de
  mathematiques de CE1 ne le fait aujourd'hui.
