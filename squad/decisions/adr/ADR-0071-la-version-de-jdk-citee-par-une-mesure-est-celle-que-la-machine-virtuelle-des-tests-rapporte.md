# ADR-0071 - La version de JDK citee par une mesure est celle que la machine virtuelle des tests rapporte, jamais celle qu'une toolchain declare

- **Statut** : Accepte. **Son garde-fou est ecrit, vert, et mordu sur le mecanisme reel dans le meme
  commit** -
  `bootstrap/src/test/java/com/recreo/archi/LaMachineVirtuelleDesTestsPorteLaVersionQueLeBuildExigeTest.java`,
  4 tests.
- **Sprint** : s24. **Date** : 2026-08-30. **Auteur** : theo-archi.
- **Arbre mesure** : worktree `s24-theo`, branche `round-two` a `335e6c1`.
- **Prolonge** : `ADR-0009` (les garde-fous de CI sont des scripts testes), `ADR-0010` (aucun chemin
  de poste dans un fichier suivi), `ADR-0013` (perimetre derive, garde-fou mordant), `ADR-0055`
  (un plancher ne garde qu'une moitie de comparaison).
- **Reprend une dette routee et non faite depuis s21** :
  `squad/sprints/s21-fermer-le-pli-avant-le-volume/correctif-outillage-de-mesure-s21-1.md`,
  section 6, et l'amendement `S21-6` qui a corrige douze rapports.
- **Ne code aucune mecanique, ne prononce aucun Done.**

## Contexte

Des dizaines de fichiers de ce depot portent une phrase de la forme *"mesure sur le JDK 21.0.10 de
la toolchain declaree"*. **Rien ne verifiait cette phrase.**

**Le mecanisme, etabli et non suppose.** `maven-toolchains-plugin` apparie une entree du fichier de
toolchains sur la **version que cette entree declare**, jamais sur celle du binaire qu'elle designe.
Une entree qui declare `21` en pointant la racine d'un JDK 25 est donc appariee sans un mot, le
build reussit, et le compilateur comme surefire s'executent sur 25 pendant que le journal affiche
*"Required toolchain: jdk [ version='21' ]"*.

**La reproduction, faite le 2026-08-30 et non deduite.** Avec un fichier de toolchains fabrique
declarant `21` et designant une racine de JDK 25, passe a Maven par son option de fichier de
toolchains, le journal du build rend `Found matching toolchain for type jdk: JDK[...jdk-25...]` puis
`Toolchain in maven-surefire-plugin: JDK[...jdk-25...]`, et le build ne bronche pas. C'est la famille
de defaut que `CLAUDE.md` sanctionne : **la commande reussit, le chiffre s'affiche, et il decrit une
autre chaine que celle qu'on croit mesurer.**

**Ce que la mesure du jour dit du poste de mesure, et il faut le dire dans les deux sens.** Le
2026-08-30, sur le worktree `s24-theo`, la machine virtuelle qui execute les tests rapporte
d'elle-meme `java.version = 21.0.10`, `java.class.version = 65.0`, `Runtime.version().feature() = 21`
- sonde posee dans `bootstrap`, executee par surefire, retiree ensuite. **Le chiffre ecrit dans les
documents de ce depot est donc juste ; c'est la methode qui ne l'etait pas.** Il etait lu sur ce que
le plugin declare, et non sur ce que l'execution rapporte ; il se trouve qu'ici les deux coincident,
et rien ne le garantissait.

**Ce n'est pas une decouverte neuve, et c'est ce qui la rend grave.** `S21-6` (2026-08-27) a etabli
que le poste de mesure porte **deux** fichiers de toolchains - celui du `HOME` du shell POSIX, que
lit `outils/mvn-jdk21.sh`, et celui du profil utilisateur de la machine virtuelle Java, que lit
Maven - et qu'ils declarent deux JDK differents. Le meme lot a nomme, en section 6 de
`correctif-outillage-de-mesure-s21-1.md`, ce qu'il fallait faire a l'outillage : *"il doit refuser au
lieu d'imprimer"*. **Trois sprints plus tard, l'enveloppe imprime toujours.** Sa ligne du 2026-08-30
dit mot pour mot *"ce JDK rapporte 25.0.2 : la declaration de version n'a pas ete confirmee"*, sur la
sortie d'erreur, a chaque lancement.

**Et elle a ete lue par personne, y compris par moi, le jour meme.** Mes commandes de mesure du
2026-08-30 filtraient la sortie par un motif du genre `Tests run:|BUILD|ERROR`, qui ne retient aucune
des deux lignes de l'enveloppe. **Un avertissement qu'un filtre de lecture ordinaire fait disparaitre
n'est pas un garde-fou** : c'est la propriete que cet `ADR` corrige, et le premier cas mesure est le
mien.

## Decision

### 1. La version citee par une mesure est celle que la machine virtuelle rapporte d'elle-meme

Une phrase de document qui nomme la version de JDK d'une mesure la tient de **l'execution**, jamais
d'une declaration : ni la ligne `Found matching toolchain` du journal, ni le nom du repertoire de
JDK, ni le champ d'un fichier de toolchains, ni la sortie de `outils/mvn-jdk21.sh`. Ces quatre
sources disent ce qui est **declare**, et la declaration est exactement ce qui peut mentir.

### 2. La coherence entre l'exigence du depot et l'execution est verifiee a chaque build

Le pom agregateur declare la version de toolchain que le build reclame. La machine virtuelle qui
execute les tests rapporte la sienne. **Les deux doivent etre egales, et un test le verifie a chaque
build, en CI comme sur un poste.**

**Egalite, jamais plancher.** Un `>=` laisserait passer une execution sur 25 en silence, c'est-a-dire
le defaut meme que cet `ADR` ferme ; un `<=` laisserait passer une execution sur 17. C'est la moitie
de comparaison qu'`ADR-0055` a sanctionnee, et les deux moities sont mordues separement.

### 3. Le fichier de toolchains reste hors du depot, et la regle n'en a pas besoin

C'est un fichier de poste, non commite, hors de portee de tout garde-fou - et il doit le rester
(`ADR-0010`). **Ce qui est dans le depot est l'exigence** (le pom) **et l'affirmation** (les
documents). La regle raccorde ces deux-la a l'execution, sans jamais lire le fichier de poste ni
ecrire un chemin de machine.

### 4. Ce que cette decision ne fait pas

- **Elle ne gouverne pas la machine virtuelle de Maven lui-meme**, et c'est deliberement plus large :
  `maven-enforcer-plugin` exige 17 ou plus recent, parce qu'une toolchain ne gouverne pas la machine
  virtuelle de Maven et que rien n'exige que ce soit la meme (`CLAUDE.md`, prerequis de build). Sur
  le poste de mesure du jour, Maven tourne sur un JDK 25 pendant que les tests tournent sur un 21 :
  **cela est conforme, et ce n'est pas le defaut.**
- **Elle ne corrige pas `outils/mvn-jdk21.sh`.** La correction de son message reste celle que la
  section 6 du correctif de s21 decrit, et elle reste routee : c'est une story d'outillage, pas un
  effet de bord d'un lot de conception. Cet `ADR` en change seulement l'urgence relative - le message
  trompeur subsiste, mais l'affirmation qu'il induisait est desormais tenue par un rouge.

## Regle executable

**Ce n'est pas une regle ArchUnit**, et je le dis plutot que de laisser le silence trancher :
ArchUnit modelise des types Java ; l'objet juge ici est **la version de la machine virtuelle en train
d'executer le test**, qui n'est pas un type. C'est un garde-fou de build, sixieme motif distinct
apres la declaration de feuille de style (`ADR-0059`), la chaine issue du chargement d'une ressource
(`ADR-0060`), la valeur d'une constante compilee (`ADR-0061`), le document HTML servi (`ADR-0062`)
et la cle de schema (`ADR-0070`).

**Fichier** :
`bootstrap/src/test/java/com/recreo/archi/LaMachineVirtuelleDesTestsPorteLaVersionQueLeBuildExigeTest.java`.

> **La machine virtuelle qui execute les tests porte exactement la version de toolchain que le pom
> agregateur exige.**

- **Perimetre derive** (`ADR-0013` obligation 1) : l'exigence est **lue dans le pom agregateur**, dans
  le bloc `<toolchains><jdk><version>` - la declaration meme que `maven-toolchains-plugin` lit pour
  apparier -, jamais recopiee en constante. Deux notions de *"quelle version ce build exige"*
  divergeraient un jour, et c'est cette divergence-la que le test existe pour attraper. La racine du
  depot vient de `LesGardeFousSontMisALEpreuveTest.racineDuDepot`, deja en service.
- **Objet juge** : `Runtime.version().feature()`, dans la machine virtuelle forkee par surefire
  depuis la toolchain resolue par Maven - donc celle qui a execute tous les autres tests du meme
  build. Aucun journal lu, aucun chemin lu.
- **Non-vacuite** : un pom sans bloc `<toolchains>` ou sans version de jdk fait echouer le test avec
  son propre message, au lieu de le rendre vert sur rien ; et l'exigence lue est confrontee a un
  plancher de plausibilite.
- **Morsure sur le mecanisme reel, et non sur une paire fabriquee.** Le juge est pur, donc mordu par
  entrees fabriquees dans les deux sens (21 contre 25, 21 contre 17) - mais la preuve qui compte a
  ete faite sur le mecanisme : un fichier de toolchains declarant `21` et designant une racine de
  JDK 25, passe a Maven par son option de fichier de toolchains, fait apparier ce JDK 25, fait forker
  surefire dessus, et **fait rougir ce test avec son message complet**. Mesure du 2026-08-30 :
  4 tests, 1 echec, `BUILD FAILURE`. Le fichier fabrique vit hors du depot et n'y entre pas
  (`ADR-0010`) ; ce qui est commite est le juge, pas le poste.
- **Le message nomme le prerequis, jamais son emplacement** : il dit d'ou vient l'appariement et quel
  fichier corriger *par sa fonction* - le fichier de toolchains du profil utilisateur de la machine
  virtuelle de Maven - sans ecrire un seul chemin.

**Ce que ce garde-fou ne prouve pas**, ecrit avant qu'on me le reproche :

- il juge la machine virtuelle de **surefire**, pas celle de failsafe. Les deux sont forkees depuis
  la meme toolchain par le meme plugin ; le jour ou l'une cesserait de l'etre, il ne le verrait pas ;
- il ne dit rien de la **version cible du bytecode** produit, gouvernee par `release` ;
- il ne verifie **aucune phrase de document**. Voir la section suivante.

**Compte des regles ArchUnit du depot : inchange a 30.** Ce lot n'ajoute aucun champ `ArchRule`.
Methode : balayage des fichiers `.java` des quatre arbres de modules du worktree, arbre de
contre-exemple `faux-module` exclu, comptage des lignes portant `static final ArchRule`.

## Ce qu'aucun automate ne gardera, et je le dis plutot que d'ecrire une consigne

**La phrase elle-meme n'est pas verifiable.** Un garde-fou qui balaierait les documents commites a la
recherche de *"JDK 21.0.10 de la toolchain declaree"* ne pourrait constater que sa **presence**, pas
sa verite : la mesure qu'elle rapporte a eu lieu un autre jour, sur un autre arbre, dans une session
qui n'existe plus. Il ne saurait pas davantage distinguer une phrase juste d'une phrase fausse - le
chiffre est le meme dans les deux cas, c'est tout le probleme. **Un tel garde-fou serait decoratif.**

Ce qui est verifiable est ce que la regle ci-dessus verifie : **que la chaine decrite par ces phrases
soit celle qui s'execute**. Une fois cela tenu a chaque build, la phrase cesse de pouvoir devenir
fausse pour ce motif-la. C'est la seule facon honnete de garder une affirmation de document : rendre
vrai ce qu'elle affirme, plutot que policer sa formulation.

## Consequences

- **Une toolchain qui ment sur sa version arrete le build**, en nommant les deux valeurs et le
  fichier a corriger par sa fonction. Trois sprints d'avertissement imprime deviennent un rouge.
- **Le cout est nul en CI** : `actions/setup-java` ecrit lui-meme la toolchain a partir de sa version
  d'entree, donc declaration et binaire y coincident par construction.
- **Mauvaise consequence assumee** : un poste dont la toolchain ment ne peut plus construire tant
  qu'elle n'est pas corrigee. C'est voulu - c'est le seul moment ou la correction coute une minute
  plutot qu'une relecture de rapports.
- **Ce qui reste ouvert et que je ne ferme pas ici** : le message de `outils/mvn-jdk21.sh`, qui
  affirme un raccordement qu'il ne verifie pas, et le champ `toolchains lu par Maven` de
  `outils/verifier-jdk.sh`, qui repond sur un autre fichier que celui dont il parle. Les deux sont
  decrits depuis le 2026-08-27, aucun n'est fait, et **cet `ADR` en est la quatrieme occurrence
  documentee**. Ce n'est pas une story d'architecture, c'est une story d'outillage, et elle
  appartient a un planning.

## Alternatives considerees

1. **Faire refuser `outils/mvn-jdk21.sh`.** Ecartee comme mecanisme principal, pour un motif de fond
   et non de charge : l'enveloppe gouverne la machine virtuelle de **Maven**, que `CLAUDE.md`
   autorise a etre un 17 ou plus recent. Lui faire exiger un 21 contredirait une regle ecrite, et
   surtout elle ne verrait toujours pas la toolchain que Maven resout - c'est precisement le
   raccordement qu'elle ne peut pas etablir. Elle doit etre corrigee, elle ne peut pas etre le juge.
2. **Une regle `maven-enforcer-plugin` de plus.** Ecartee : l'enforcer juge la machine virtuelle de
   Maven, pas la toolchain. Il ne voit pas l'objet dont il s'agit.
3. **Comparer, dans le script, la version declaree et la version rapportee.** C'est le point 2 de la
   section 6 du correctif de s21, et il reste bon - mais il garde le fichier que **le shell** lit,
   pas celui que Maven resout. Sur un poste ou les deux different, il conclurait juste sur le mauvais
   fichier. Complementaire, jamais suffisant.
4. **Un garde-fou de balayage sur la phrase des documents.** Ecartee, motif ecrit en clair dans la
   section ci-dessus : il constaterait une presence, pas une verite.
5. **Comparer aussi la version cible du bytecode du jar livre.** Ecartee de ce lot : c'est une
   troisieme propriete, deja constatee une fois dans `S21-6` par lecture des octets 7 et 8 d'une
   classe, et elle merite sa propre decision si quelqu'un la veut gardee. Je ne l'ajoute pas au
   passage pour faire nombre.

---

**Mesure de cloture de ce lot, methode comprise.** `bash outils/mvn-jdk21.sh -B clean verify` a la
racine du reacteur - **BUILD SUCCESS, 811 tests, 0 echec** (49 `domain` + 13 `application` +
332 `adapter-web` + 288 `adapter-content` + 123 `bootstrap` surefire + 6 `failsafe`), sur un JDK
**21.0.10** - version rapportee par la machine virtuelle des tests elle-meme, ce que cet `ADR`
verifie desormais a chaque build. Pas de `-am` a citer : le reacteur est complet par construction.
Les quatre tests de plus par rapport aux 807 du lot precedent sont ceux de ce garde-fou.
