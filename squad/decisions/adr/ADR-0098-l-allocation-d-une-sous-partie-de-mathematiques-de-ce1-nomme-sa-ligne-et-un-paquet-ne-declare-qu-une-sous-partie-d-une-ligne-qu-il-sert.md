# ADR-0098 - L'allocation d'une sous-partie de mathematiques de CE1 nomme sa ligne de referentiel, et un paquet ne declare qu'une sous-partie d'une ligne qu'il sert

- **Statut** : Accepte
- **Date** : 2026-09-22
- **Auteur** : theo-archi
- **Sprint** : s39
- **Route par** : mon propre avis de faisabilite de s39
  (`squad/sprints/s39-couverture-maximale-six-lignes-candidates/avis-faisabilite.md`, section 18.3),
  qui conclut *"un `ADR` est du"* sur `S39-LONGUEUR` et `S39-MONNAIE`, et le confirme pour
  `S39-SOLIDES` (section 8, meme obstacle). Entree pedagogique :
  `squad/conformite/ARBITRAGE-PREALABLE-s39-den-longueur-monnaie-solides.md` (`nadia-prof`,
  2026-09-22). Gouvernance : `DEC-0092`, section 5 - cet `ADR` n'affecte personne et n'autorise
  aucun paquet.

## Contexte

`ADR-0097` a decide qu'un paquet de mathematiques de CE1 declare sa sous-partie par la cle
`competence` de son descripteur, et que cette declaration est allouee en un seul endroit,
`SousPartieDeMathematiquesCe1`. La regle est verte et elle mord : mesure du 2026-09-22 sur ce
worktree, `Tests run: 10, Failures: 0` avant ce changement.

**Le defaut n'est pas cette regle, c'est l'ecart entre son perimetre et son allocation.** Sa
population est *tout paquet du contenu servi qui declare le niveau `CE1` et la matiere
Mathematiques*. Son allocation, elle, s'intitule *sous-partie de `MAT-CE1-03`* et ses quatre entrees
en sont. Tant que `MAT-CE1-03` etait la seule ligne de mathematiques de CE1 servie, les deux
coincidaient exactement - comptage du 2026-09-22 sur le repertoire de contenu, descripteur par
descripteur : **4 paquets de mathematiques de CE1 servis sur 78, tous sur `MAT-CE1-03`**.

`S39-LONGUEUR` (`MAT-CE1-09`), `S39-MONNAIE` (`MAT-CE1-13`) et `S39-SOLIDES` (`MAT-CE1-16`) font
apparaitre l'ecart. Chacun de ces paquets serait **rouge** faute d'entree, et l'entree qu'il faudrait
lui donner le rangerait dans un tableau dont le nom et la Javadoc affirment qu'il n'enumere que les
sous-parties d'une autre ligne. C'est la famille de defaut que mon propre mandat me designe - **un
perimetre qui echoue par inclusion**, et `ADR-0097` l'avait d'ailleurs annonce dans ses consequences
(*"un paquet de mathematiques de CE1 hors de `MAT-CE1-03` devra allouer sa sous-partie"*).

**Ce qui aurait ete la reponse paresseuse, et pourquoi elle ne suffit pas.** Ajouter trois entrees a
l'enumeration et renommer la Javadoc. Le tableau resterait **plat** : une competence y serait
allouee sans que rien ne dise de quelle ligne elle est une sous-partie. Un paquet de solides
pourrait alors declarer la competence des additions - chaine allouee, donc verte - et entrerait
**muet** dans la population derivee que `ChaquePaquetDAdditionsDeCe1TientSaRepartitionParFormeTest`
lit, qui rougirait sur des enonces qu'elle n'a jamais eu a juger, ou pire resterait verte en ayant
lu autre chose que ce qu'elle croit. C'est exactement le mode de defaillance qu'`ADR-0097` a ferme
dans l'autre sens, rouvert par l'elargissement.

## Decision

**1. Chaque entree de `SousPartieDeMathematiquesCe1` alloue un couple - un code de ligne de
referentiel et la competence exacte que le descripteur declare.** La ligne cesse d'etre supposee par
le nom du tableau, elle est portee par l'entree. L'allocation vaut pour **toute** ligne de
mathematiques de CE1, et non pour `MAT-CE1-03` seule.

**2. Un paquet de mathematiques de CE1 dont la competence est allouee sur une ligne qu'il ne declare
pas dans `lignesDeReferentiel` est rouge.** C'est la moitie de cette decision qui interdit quelque
chose. Le test est une appartenance, jamais une egalite : un paquet peut servir plusieurs lignes, il
lui suffit de declarer celle de sa sous-partie.

**3. Les trois lignes candidates de s39 recoivent leur sous-partie, avant leur paquet** -
`MAT-CE1-09`, `MAT-CE1-13`, `MAT-CE1-16`. C'est le geste d'`ADR-0097` reconduit : allouer avant
d'ecrire est ce qui rend le garde-fou mordant le jour ou le paquet arrive, plutot que complaisant.

**4. Aucune cle neuve au schema de paquet, aucune ligne de Java de production.** `competence` et
`lignesDeReferentiel` existent toutes deux, sont chargees, et sont declarees par les 78 paquets
servis. La seconde est deja gardee par `ChaquePaquetServiDeclareLesLignesDeReferentielQuIlSertTest`
(`ADR-0081`), qui refuse un code que `squad/programme` n'alloue pas - c'est ce qui permet de s'y
appuyer sans la re-verifier ici.

**Ce que cette decision ne fait pas.** Elle ne decide aucun contenu, aucune borne, aucun distracteur.
Elle ne dit pas qu'une ligne sera servie, ni par quel paquet, ni par qui - l'affectation reste a
`sofia-sm` (`DEC-0092`, section 5), et le Done pedagogique a `nadia-prof` seule. Elle ne remplace pas
`ADR-0097` : sa decision 2 est intacte et cet `ADR` l'etend. Elle ne rouvre pas `ADR-0068` et ne
tranche pas l'ecriture des montants de `S39-MONNAIE` - ce point est une contrainte de conception du
lot, ecrite en section 21 de l'avis de faisabilite de s39, avec le motif pour lequel il **n'est pas**
un `ADR`.

## Sur la formulation des trois competences allouees

Meme registre qu'`ADR-0097`, et meme frontiere : ce n'est pas une decision de contenu. La competence
est une etiquette de descripteur, elle n'est montree a aucun enfant, et son libelle se change dans
l'allocation et dans le paquet **dans le meme diff**. Les trois libelles sont ceux du referentiel
(`squad/programme/mathematiques/competences-ce1.md`), **prives du deux-points et du point-virgule
qu'il porte** : une competence est exposee par le port entrant, `TYP-1` (`ADR-0067`) exige un blanc
insecable devant ces deux signes, et une cle de garde-fou dont un caractere invisible fait partie est
une cle qu'on ne peut plus recopier a l'oeil. Retirer le signe coute moins cher que de gerer le
blanc. Si `nadia-prof` ou le developpeur affecte prefere une autre formulation, elle se change la.

## Regle executable

Fichier :
`adapters/adapter-content/src/test/java/com/recreo/adapter/content/ChaquePaquetDeMathematiquesDeCe1DeclareSaSousPartieTest.java`
Jeu fautif commite :
`adapters/adapter-content/src/test/resources/contreexemple/ChaquePaquetDeMathematiquesDeCe1DeclareSaSousPartieTest/`

**Ce n'est pas une regle ArchUnit, et le motif est celui d'`ADR-0097`** : la population est du YAML et
des descripteurs, pas du bytecode. Ni la declaration d'un paquet, ni son allocation, ni la ligne
qu'il sert ne sont encodables en ArchUnit. L'automate qui garde la decision est ce garde-fou de
contenu, execute par `mvn verify` dans la suite d'`adapter-content` et recense par
`LesGardeFousSontMisALEpreuveTest`, qui exige son jeu fautif. **Le compte de regles ArchUnit du depot
est inchange, et il est mesure apres ce changement, pas recopie d'`ADR-0097`** : **36
`static final ArchRule`, 35 `@ArchTest`**, le 2026-09-22. Methode, identique a celle d'`ADR-0095` :
occurrences litterales de ces deux chaines dans `src/test/java` des cinq modules declares au pom
agregateur, `target/` exclu. Les deux comptes different d'une unite pour la cause qu'`ADR-0094` a
nommee - une `ArchRule` du depot n'est pas annotee `@ArchTest`.

**Ce que la clause neuve interdit reellement.** Le geste suivant, qui serait gratuit et invisible
apres un simple elargissement de l'enumeration : ecrire un paquet de solides, lui donner la
competence des additions parce qu'elle est allouee, et le voir entrer dans la population du garde-fou
de repartition des additions. Concretement, elle refuse tout paquet dont la sous-partie declaree
appartient a une ligne absente de ses `lignesDeReferentiel`.

**Ce qu'elle ne dit pas**, et qui reste ce qu'`ADR-0097` avait deja borne : que la declaration soit
**vraie**. Un paquet qui declare `MAT-CE1-16` et la sous-partie des solides tout en servant des
figures planes passe ici. Aucun automate ne lit une declaration pour en juger la verite ; c'est le
premier des six points que `nadia-prof` garde seule.

**Perimetre derive, jamais enumere** (`ADR-0013` obligation 1). Inchange : niveau `CE1` et matiere
Mathematiques lus dans le descripteur, aucun nom de paquet ecrit dans la regle. La troisieme cle
lue - `lignesDeReferentiel` - est gardee par `ADR-0081`, le niveau par `ADR-0095`, la matiere par
`ADR-0049` : aucune des trois ne peut sortir du perimetre par une faute de frappe sans qu'un autre
garde-fou le dise.

**Polarite** (`ADR-0039`) : l'allocation reste du cote qui **exige une entree**. Un paquet qu'elle ne
nomme pas est rouge, et un paquet qu'elle nomme sur une autre ligne que la sienne est rouge aussi.
Elle n'entre pas au recensement des criteres enumeres.

**Quatre morsures neuves, sur les quatorze du fichier** :

1. **La morsure de la clause, portee par du materiau reel mute** : chaque paquet de mathematiques de
   CE1 reellement servi garde sa competence et voit ses lignes declarees remplacees par celle des
   solides. Le message doit nommer la ligne d'allocation et celle du paquet. C'est une mutation du
   corpus, pas une fixture de ma main - la lecon de s05.
2. **Le paquet qui ne declare aucune ligne** : une clause qui s'appuie sur une cle doit dire ce
   qu'elle fait quand la cle est absente, sans quoi elle passe sur le paquet le plus muet.
3. **Le paquet qui declare plusieurs lignes dont la sienne reste vert** : la moitie qui empeche la
   clause de trop refuser. Une egalite au lieu d'une appartenance interdirait a un paquet de servir
   deux lignes, ce qu'`ADR-0081` autorise.
4. **Le jeu fautif commite**, etendu a cinq paquets et **deux** fautes, une par clause -
   `paquet-moities-ce1` (sous-partie non allouee, `ADR-0097`) et `paquet-solides-ce1` (sous-partie
   allouee sur une ligne non declaree, `ADR-0098`). Il traverse la lecture disque et le balayage du
   repertoire, et l'assertion exige que `paquet-conforme-ce1` **ne soit pas** nomme : c'est ce qui
   prouve que la regle discrimine encore apres l'ajout d'une clause.

Plus une assertion de non-vacuite sur l'allocation elle-meme : chaque entree nomme une ligne non
vide. Une entree sans ligne ferait passer la clause par vacuite sur le paquet qui la declare,
puisqu'aucune chaine vide n'est jamais dans `lignesDeReferentiel`.

**Mesure, sur ce worktree** : `bash outils/mvn-jdk21.sh -B -pl adapters/adapter-content -am test
-Dtest=ChaquePaquetDeMathematiquesDeCe1DeclareSaSousPartieTest`, **`Tests run: 14, Failures: 0`**
(10 avant ce changement), JDK **21.0.10** rapporte par la machine virtuelle des tests. Le `-am` est
porte parce que la mesure est partielle (`CLAUDE.md`). La porte du depot reste `mvn -B clean verify`
sur la racine, rapportee en fin de document.

## Consequences

- **Les trois lots de s39 cessent d'etre bloques par leur allocation.** Un paquet de longueurs, de
  monnaie ou de solides reprend le libelle alloue et declare sa ligne, et il est vert. Ce n'est pas
  une autorisation d'ecrire le paquet.
- **Le cout d'`ADR-0097` est paye, pas supprime.** Une ligne de mathematiques de CE1 neuve demande
  toujours une entree dans l'allocation, sans quoi le build est rouge. Ce que cet `ADR` change est
  que cette entree a desormais un endroit qui la decrit correctement.
- **Une contrainte neuve sur les paquets, et elle est reelle** : la competence et les lignes
  declarees d'un paquet doivent etre coherentes entre elles. Un paquet qui sert deux lignes et
  declare la competence de l'une des deux passe ; un paquet qui declare une competence d'une
  troisieme ligne ne passe pas.
- **Une sous-partie allouee peut dormir sans que rien ne rougisse.** Les trois entrees de s39 ne sont
  servies par aucun paquet a la date de cet `ADR`, et c'est voulu - c'est ce qui donne sa morsure a
  l'allocation prealable. La contrepartie est qu'une entree peut survivre a un lot abandonne. Je ne
  pose pas de clause *"aucune allocation ne dort"* : elle punirait exactement le geste que cette
  decision recherche. **Ce qui me ferait changer d'avis** : une allocation qui dormirait plus de
  trois sprints, qui serait alors une trace d'intention et non une allocation.
- **Le libelle alloue etend l'inventaire de `TexteAccentueExposeParLePortEntrantTest`** le jour ou un
  paquet le sert - les trois portent des accents, et ils sont fautifs si le paquet les sert sans.
- **Les garde-fous de repartition de s39, s'ils sont ecrits, derivent leur population de
  l'allocation** et non d'un motif sur le francais d'un enonce. C'est la raison d'etre du tableau, et
  elle vaut pour les trois lignes neuves comme elle valait pour les moities.

## Alternatives considerees

**1. Un amendement a `ADR-0097` plutot qu'un `ADR` neuf.** Ecartee. Un amendement corrige un texte
qui a derive par rapport a une decision ; ici la decision d'`ADR-0097` est exacte et reste en
vigueur, et ce qui s'ajoute est une **interdiction neuve** - la coherence entre la competence
declaree et les lignes servies - que son auteur n'avait ni ecrite ni gardee. Numeroter ce qui
interdit quelque chose de neuf est ce qui permet a un message d'echec de citer un `ADR` qui le decide.

**2. Elargir l'enumeration sans la ligne.** La reponse paresseuse, decrite en contexte : trois
entrees de plus et une Javadoc reecrite. Ecartee parce qu'elle laisse le tableau plat, et qu'un
tableau plat autorise en silence un paquet a entrer dans la population d'une sous-partie qu'il ne
sert pas. Une regle qui n'interdit rien de neuf a ce prix-la est decorative.

**3. Une enumeration par ligne, une par matiere, ou une enumeration imbriquee.** Ecartee sur la
simplicite : trois tableaux la ou un couple suffit, et trois endroits a tenir a jour au lieu d'un.
Le couple est la plus petite piece qui rende la ligne portee. **Ce qui me la ferait reprendre** : le
jour ou une ligne porterait assez de sous-parties pour que la lecture du tableau devienne penible -
`MAT-CE1-03` en a quatre, ce n'est pas le cas.

**4. Verifier ici que le code de ligne alloue existe au referentiel.** Ecartee, et pas par gout :
`squad/programme` est lu par `ChaquePaquetServiDeclareLesLignesDeReferentielQuIlSertTest`, qui vit
dans `bootstrap` - le seul module qui voie la racine du depot. Le faire ici demanderait a un test
d'`adapter-content` de remonter hors de son module. Le prix de ne pas le faire est nomme : une faute
de frappe dans un code de ligne alloue produit un **rouge** le jour ou le paquet arrive, avec un
message qui nomme les deux codes - un faux rouge, donc visible, jamais un faux vert. **Ce qui me
ferait changer d'avis** : un second appelant ayant besoin des codes de ligne dans `adapter-content`.

**5. Deriver la sous-partie de la ligne declaree, sans competence.** C'est-a-dire supprimer
l'allocation et prendre `lignesDeReferentiel` comme cle. Ecartee : `MAT-CE1-03` se sert en quatre
paquets, et sa ligne ne distingue pas les additions des moities. La sous-partie est d'un cran plus
fin que la ligne, ce qui est precisement le constat d'`ADR-0097`.

## Porte technique

`bash outils/mvn-jdk21.sh -B clean verify` sur la racine de ce worktree, **BUILD SUCCESS**, JDK
**21.0.10** rapporte par la machine virtuelle des tests. Les jobs `prefixes`, `chemins` et `outils`
de `.github/workflows/ci.yml` ne sont pas lances par `mvn` et ne sont donc pas annonces verts ici
sur la foi de cette commande (`CLAUDE.md`, amendement du 2026-09-08) - `verifier-chemins.sh` a ete
lance separement sur le diff, son resultat est note dans le rapport de la tache.
