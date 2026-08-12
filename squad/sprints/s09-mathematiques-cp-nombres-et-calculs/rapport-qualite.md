# Rapport qualite - sprint 9 (mathematiques CP, nombres et calculs)

- **Auteur** : yanis-qa
- **Date** : 2026-08-12
- **Worktree** : dedie, provisionne depuis `round-two` sur une branche neuve (`s09-yanis-qualite`),
  hors de l'arbre du depot principal. Lignee verifiee avant toute ecriture :
  `outils/verifier-lignee.sh` -> "le worktree sur la branche 's09-yanis-qualite' descend de la
  lignee de round-two (ancetre commun trouve dans son historique)".
- **Commit audite** : `0d3e734`, tete de `round-two` au moment de la saisine.
- **Constat de JDK** (`outils/verifier-jdk.sh`) : `java du PATH : 1.8.0_202 | JDK de compilation :
  21.0.10 | JAVA_HOME : renseignee | toolchains lu par Maven : non (fichier present sous un nom
  non lu)`. Meme anomalie de poste que les sprints precedents, toujours ouverte : le fichier de
  toolchains existe sous un nom mal orthographie et Maven ne le lit donc jamais. `JAVA_HOME` a ete
  pointe sur ce JDK 21 pour la duree de chaque commande citee ci-dessous, jamais ecrit dans un
  fichier commite.
- **Branche non fusionnee, hors de mon audit de chiffres techniques** : `s09-nadia-verdicts-2`
  (`2e29ff1`). Verifie par moi : `git diff round-two 2e29ff1 --stat` ne touche que trois fichiers,
  les fiches de conformite des soustractions, des problemes additifs et de la comparaison de
  nombres - aucun code, aucun test. Cette branche porte un second audit de `nadia-prof` (Done
  pedagogique VALIDE SOUS RESERVE sur les trois jeux d'abord refuses, apres correctif) ; je la lis
  pour comprendre le contexte des sections 3 et 4 de ce rapport, mais elle n'entre dans aucun
  chiffre ni verdict technique tant qu'elle n'est pas fusionnee sur `round-two`.

Ce rapport ne recopie aucune mesure sans l'avoir soit rejouee, soit refutee - y compris les
garde-fous cites comme fermant un trou de morsure, que j'ai casses puis remis en etat, pas relus.

## 1. Porte technique - `mvn -B verify`

```
mvn -B verify
```

**Resultat : BUILD SUCCESS**, six modules en SUCCESS, aucun test ignore.

| Module | Tests |
|---|---|
| domain | 43 |
| application | 7 |
| adapter-web | 178 |
| adapter-content | 63 |
| bootstrap (surefire) | 77 |
| bootstrap (failsafe) | 3 |
| **total** | **371** |

**Confirme le chiffre annonce.** Methode : somme de l'attribut `Tests run` du bloc `Results:` de
chaque module dans la sortie du reacteur - c'est l'agregat que Maven calcule lui-meme a partir des
rapports XML, pas une addition manuelle de fichiers.

**Reconciliation complete du delta de ce sprint**, en partant du dernier chiffre etabli
(`squad/sprints/s08-cabinet-de-la-machine/rapport-qualite.md` : 344) puis de l'avis de faisabilite
de ce sprint (347 avant tout code du sprint, `theo-archi` ayant ferme deux dettes de comptage entre
les deux : `AucunVocabulaireDeMatiereTest` etendu et un jeu de plus au catalogue depuis s08) :

```
347 (avant ce sprint)
 +6  ADR-0032, AucunItemNeRepeteLEcranDUnAutreItemDuMemePaquetTest (theo-archi)
 +5  S9-1 soustractions, PaquetCpSoustractionsS9Test (marc-dev)
 +6  S9-2 complements-10, PaquetCpComplements10S9Test (marc-dev)
 +7  S9-3 comparer-nombres, PaquetCpComparerNombresTest (lea-dev)
 +0  S9-4 problemes additifs, aucun test Java neuf (sofia-sm)
= 371
```

Chaque terme verifie par moi sur son propre commit (`5aa950c`, `8b5f2e6`, `8767f4c`, `112338f`,
`771eec5`) : `git show --stat` pour le fichier de test touche, puis compte des `@Test` dans le
fichier. Aucun terme recopie d'un rapport tiers.

**28 regles ArchUnit, confirme independamment par deux methodes qui se recoupent** :

1. Compte des `@ArchTest` dans les dix fichiers de `bootstrap/src/test/java/com/recreo/archi/` et
   dans `adapters/adapter-web/src/test/java/com/recreo/adapter/web/archi/AssertionsWebTest.java` :
   26 + 1 = 27.
2. `ScanDeComposantsTest` (`bootstrap`) declare une `ArchRule` verifiee a la main dans un `@Test`
   plutot que par le mecanisme `@ArchTest` : +1.

27 + 1 = **28**, identique au chiffre annonce.

## 2. Les trois pieges de comptage, et pourquoi mon chiffre n'en porte aucun

**Piege 1 - le resume texte de surefire sous-compte les classes a deux moteurs JUnit, verifie et
quantifie moi-meme.** Quatre classes melangent des methodes `@Test` (moteur Jupiter) et des champs
`@ArchTest` (moteur `archunit-junit5`) : `AbstractionsCiteesTest`, `ChargementDuContenuTest` et
`LesReglesMordentTest` dans `bootstrap`, `AssertionsWebTest` dans `adapter-web`. Le rapport texte
par classe (`.txt`) ne rend que le compte du moteur Jupiter ; le rapport XML de la meme classe
(attribut `tests=`) agrege les deux moteurs :

| Classe | `.txt` (`Tests run`) | XML (`tests=`) | Ecart |
|---|---|---|---|
| `AbstractionsCiteesTest` | 1 | 5 | 4 |
| `ChargementDuContenuTest` | 1 | 2 | 1 |
| `LesReglesMordentTest` | 25 | 26 | 1 |
| `AssertionsWebTest` | 1 | 2 | 1 |
| **total** | | | **7** |

Quatre classes, sept tests d'ecart : exactement le piege nomme dans ma saisine, et je l'ai retrouve
par ma propre lecture des rapports, pas recopie. **Il ne mord pas mon chiffre de 371** : celui-ci
vient du bloc `Results:` du reacteur, qui recompte a partir des XML (donc du bon total par module),
jamais d'une addition manuelle de fichiers `.txt`.

**Piege 2 - un balayage depuis la racine du poste voit les rapports des worktrees des autres
agents.** Confirme sans le reproduire : `git worktree list` montre quatorze worktrees actifs a cote
du mien au moment de cet audit. Je n'ai jamais balaye au-dessus de mon propre worktree ; mon compte
vient exclusivement du reacteur Maven execute par `mvn -B verify` lance depuis la racine de ce seul
worktree, jamais d'un balayage de fichiers `*/target/surefire-reports/` remontant au-dessus.

**Piege 3 - l'addition manuelle des rapports surefire compte deux fois les tests meta qui
invoquent un jeu fautif pour prouver qu'une regle mord.** Je ne l'ai pas reproduit a l'identique
(la saisine le decrit deja et j'ai verifie que mon propre chiffre, tire du reacteur, ne l'emprunte a
aucun moment), mais je confirme le principe : `LesGardeFousSontMisALEpreuveTest` et
`AucunItemNeRepeteLEcranDUnAutreItemDuMemePaquetTest`, entre autres, executent leurs assertions de
mise a l'epreuve (`assertThatThrownBy(...)` sur un jeu fautif) **dans la meme methode `@Test`** que
leur assertion de production - une seule execution JUnit compte les deux, mais celui qui relirait le
corps du test et compterait "un `assertThatThrownBy` = un test" au lieu de compter les methodes
`@Test` se tromperait dans l'autre sens. Le risque est reel des qu'on quitte le chiffre que Maven
calcule lui-meme.

**Ma reponse a la question posee** : **oui, le depot devrait porter un outil qui rend ce compte
autoritatif**, selon la doctrine d'`ADR-0009` (couper la mesure en une fonction pure testee - lire
les rapports XML sous `*/target/surefire-reports` et `*/target/failsafe-reports` des seuls modules
declares au pom agregateur, sommer `tests=` - et une enveloppe non testee qui invoque `mvn` puis
cette fonction). Trois erreurs differentes en deux sprints (piege 1 documente au sprint 8, piege 2
documente avant, piege 3 fabrique un faux ecart ce sprint meme chez `sofia-sm`, qui s'est corrigee
elle-meme) ne sont pas trois malchances : c'est le signal qu'une mesure recalculee a la main par
chaque agent, a chaque sprint, produit un vert qui doit se refaire confiance a lui-meme au lieu
d'un chiffre qu'on peut simplement relire. Je le nomme comme obstacle de qualite (couverture de
gouvernance insuffisante), traite directement selon mon mandat : je recommande l'outil, je ne le
prescris pas en detail - c'est `theo-archi` qui choisit ou il vit (probablement `outils/`, sur le
patron de `verifier-jdk.sh`).

## 3. Mutation d'`ADR-0032` sur du materiau reel de production, pas sur ses propres contre-exemples

`ADR-0032` et son garde-fou `AucunItemNeRepeteLEcranDUnAutreItemDuMemePaquetTest` documentent trois
mutations sur des fixtures ecrites pour le test. Un vert ne prouve rien tant que je ne l'ai pas
casse moi-meme, sur un objet que je n'ai pas prepare pour l'occasion.

**Mutation** : dans `cp-comparer-nombres.yaml` (paquet reel servi en production, 24 items,
`nombreDeManches: 10`), duplication exacte du premier item (`"8 ___ 15"`, meme bonne reponse, memes
distracteurs) - la forme meme du defaut qu'`ADR-0023` laissait passer avant `ADR-0032`.

```
mvn -B -pl adapters/adapter-content test -Dtest=AucunItemNeRepeteLEcranDUnAutreItemDuMemePaquetTest,ChaqueJeuDuCatalogueOffrePlusDItemsQueDeManchesTest
```

**Resultat** :

- `AucunItemNeRepeteLEcranDUnAutreItemDuMemePaquetTest` : **rouge**, 1 echec sur 6, message nommant
  les deux items ("item item-1 : il presente le meme ecran que l'item item-0").
- `ChaqueJeuDuCatalogueOffrePlusDItemsQueDeManchesTest`, execute isolement sur le meme fichier
  mute : **vert**, 3 cas passants. Confirme, sur du materiau reel et non sur la fixture de l'`ADR`,
  que le trou de morsure existe bel et bien : `ADR-0023` seul ne voit pas une copie exacte, et
  seul `ADR-0032` la refuse.

**Restauration** (`git checkout` du fichier) puis reexecution du module complet :
`Tests run: 63, Failures: 0`. `git status --short` : arbre propre.

**Conclusion sur ce point** : le trou de morsure d'`ADR-0023` est reel et sa fermeture par
`ADR-0032` est mordante sur du contenu de production, pas seulement sur ses cinq cas de fixture.

## 4. Trouvaille : trois fiches de `nadia-prof` sous-estiment leur propre garde-fou

En verifiant la tracabilite critere -> test des trois lots refuses puis corriges (section 5), j'ai
lu les trois tests dedies (`PaquetCpSoustractionsS9Test`, `PaquetCpComplements10S9Test`,
`PaquetCpComparerNombresTest`) plutot que de reprendre au mot la description qu'en font les fiches
de conformite. Les trois fiches ecrivent, avec la meme formule, qu'aucun test ne verifie
l'exactitude arithmetique de l'item ("Aucun test ne calcule `a - b == reponse`", "Aucun test ne
calcule `a + reponse == 10`", "Aucun test ne compare les deux nombres de l'enonce au signe
attendu"). **Les trois tests dedies le font deja**, et je l'ai prouve par mutation plutot que par
lecture seule :

| Paquet | Methode de test | Mutation jouee | Resultat |
|---|---|---|---|
| `cp-soustractions` | `chaqueEnonceEstUneSoustractionDeTermesEtDeResultatEntre0Et10SansResultatNegatif` | `9 - 5 = ?` : bonne reponse `4` -> `3` | rouge, "la bonne reponse est le resultat exact de la soustraction : expected 4, was 3" |
| `cp-complements-10` | `chaqueEnonceDemandeLeComplementA10DUnNombreEntre0Et10Inclus` | `7 + ? = 10` : bonne reponse `3` -> `2` | rouge, "le nombre de depart et la bonne reponse font exactement 10 : expected 10, was 9" |
| `cp-comparer-nombres` | `leSigneDeChaqueItemEstLeSigneMathematiquementCorrect` | `56 ___ 60` : bonne reponse `<` -> `>` | rouge, "signe attendu pour '56 ___ 60'" |

Les trois fichiers YAML ont ete restaures a l'identique apres chaque mutation (`git checkout`,
`git status --short` verifie propre) et la suite complete rejouee en vert.

**Ce que cela change, et ce que cela ne change pas.** Cela ne remet en cause aucun des trois
verdicts : les refus de `nadia-prof` portaient sur la langue des indices et sur une propriete
distincte (l'exactitude d'un enonce en situation, non automatisable), jamais sur l'exactitude
arithmetique - qu'elle a par ailleurs verifiee a la main, en plus du test, sur les trois paquets.
Mais la section "Ce dont je reste seule garde apres ce verdict" de chacune des trois fiches
enumere une propriete deja gardee comme si elle ne l'etait pas. Un agent qui lirait cette section
pour decider ou porter un effort neuf ecrirait un test qui existe deja. Je le remonte comme un
defaut de gouvernance a corriger dans les fiches (pas un refus a rouvrir), route a `sofia-sm` pour
que `nadia-prof` corrige la section citee des trois fiches au prochain geste sur ces fichiers.

## 5. Tracabilite critere -> test

- **S9-1 (soustractions, `marc-dev`)** : `PaquetCpSoustractionsS9Test`, cinq methodes, javadoc de
  classe citant S9-1 et `DEC-0019`. Chaque methode porte au moins un critere de la fiche de
  conformite (forme de l'enonce et absence de resultat negatif, couverture des onze resultats,
  forme des propositions, plancher de 20 items, chargement par le catalogue).
- **S9-2 (complements-10, `marc-dev`)** : `PaquetCpComplements10S9Test`, six methodes, javadoc
  citant S9-2 et `DEC-0020`. Verifie en plus la valeur `nombreDeManches: 7` propre a ce jeu
  (`DEC-0020`) et au moins un distracteur plausible par item.
- **S9-3 (comparer-nombres, `lea-dev`)** : `PaquetCpComparerNombresTest`, sept methodes, javadoc
  citant S9-3. Couvre la forme du trou, la justesse du signe (section 4), la repartition des trois
  signes, la part d'egalites, la part d'ecarts petits - les cinq criteres numeriques de la fiche.
- **S9-4 (problemes additifs, `sofia-sm`)** : aucun fichier de test neuf. Le commit `771eec5`
  motive ce choix par le perimetre derive des garde-fous existants
  (`ChaquePaquetPorteUnIndiceTest`, `ChaqueJeuDuCatalogueOffrePlusDItemsQueDeManchesTest`,
  `AucunItemNeRepeteLEcranDUnAutreItemDuMemePaquetTest`, `CatalogueChargeableIT`) ; chiffre exact
  de ce que ces garde-fous couvrent et ne couvrent pas en section 6.
- **`ADR-0032` (`theo-archi`)** : `AucunItemNeRepeteLEcranDUnAutreItemDuMemePaquetTest`, six
  methodes, mise a l'epreuve sur cinq fixtures dediees. Rejoue par ma propre mutation en section 3.

**Aucun module sans test dans ce que la squad a livre ce sprint.** Les cinq commits touchent
`adapter-content` (quatre lots de contenu plus `ADR-0032`) ; aucun ne touche `domain`,
`application`, `adapter-web` ni `bootstrap`, et ces quatre modules n'avaient aucune raison
technique d'etre touches par un sprint de contenu pur - verifie par lecture des cinq `git show
--stat`.

**Aucune assertion HTML par expression reguliere constatee.** Les quatre tests de paquets lisent
des objets `Question` retournes par la chaine de chargement reelle (`YamlContentPackLoader`,
`ContentSource.next`), jamais du balisage HTML.

## 6. Ce qui reste teste et ce qu'un enfant lit - la frontiere, chiffree

**La suite garde l'architecture, le decouplage, la geometrie et la structure des paquets. Elle ne
garde, aujourd'hui, aucune des trois proprietes qui ont motive les trois refus de ce sprint** :
l'orthographe du texte, l'exactitude d'un enonce en situation (le "en" partitif), la verite d'un
fait enonce par un indice ("56 vient juste avant 60"). Je ne traite pas cela comme une lacune
generale a combler d'un bloc : les trois proprietes n'ont pas le meme statut, et je les separe.

**Pas gardable a un cout raisonnable, et je rejoins `nadia-prof` et `theo-archi` sur ce point** :
la validite semantique d'un enonce en situation (le "en" partitif des ballons et des fleurs) et la
verite generale d'une affirmation de langue ou de fait ("56 vient juste avant 60") demandent de
comprendre un texte en francais, pas de le parser. Aucun automate de ce depot ne le fait
aujourd'hui et je ne recommande pas d'en ecrire un : ce serait le meme renoncement qu'`ADR-0025` et
`ADR-0028` sur la mesure de rendu, pour la meme raison de fond - la propriete depend d'une
comprehension que l'outillage du depot n'a pas.

**L'orthographe et l'accentuation du texte affiche a l'enfant : gardable en principe (un
correcteur), mais hors de portee proportionnee a ce sprint.** Un correcteur orthographique
francais fiable est une dependance lourde (dictionnaire, regles de conjugaison, faux positifs sur
le vocabulaire scolaire specialise) pour fermer un defaut qui ne s'est produit qu'une fois en huit
paquets de mathematiques et de francais ecrits jusqu'ici. Je ne le recommande pas comme geste de ce
sprint ; je le nomme comme piste si la frequence des fautes constatees augmentait.

**Les deux proprietes que `nadia-prof` nomme candidates, chiffrees separement.**

1. **La regularite de magnitude entre bonne reponse et distracteurs** (`DEC-0021`, reserve 1 de la
   fiche des problemes additifs) : 11 des 11 items d'addition de `cp-problemes-additifs` ont pour
   bonne reponse le plus grand des trois nombres proposes - verifie par moi sur le fichier, 11
   sur 11. **Techniquement simple a ecrire** : une clause qui compte les items ou la bonne reponse
   est le maximum numerique des propositions et refuse au-dela d'un seuil, sur le modele des
   clauses de repartition deja ecrites dans `PaquetCpComparerNombresTest`. **Le cout reel n'est pas
   le code, c'est le seuil** : personne ne l'a mesure, et la propriete "la bonne reponse est le
   maximum" est parfois legitime (une comparaison ou un `>` strict) et parfois un defaut (un
   probleme d'addition ou l'enfant peut deviner sans calculer) - la distinction n'est pas
   generalisable au type `Question` partage sans y faire entrer un peu de vocabulaire de matiere,
   exactement la dette 2 que `CLAUDE.md` interdit de reproduire. Une clause specifique a un type
   d'operation (addition en situation) est un geste d'une demi-journee ; une clause generale sur
   `Question` risquerait de rougir a tort sur `cp-comparer-nombres`, ou le maximum est parfois la
   bonne reponse par construction du jeu. C'est pourquoi `camille-po` l'a routee a `theo-archi` :
   ce n'est pas un obstacle technique, c'est un choix de perimetre.
2. **L'atteignabilite de tous les items d'un paquet et la conformite des propositions servies au
   fichier** : `nadia-prof` l'a verifiee elle-meme sur **1800 coups** (3 jeux corriges x 60 parties
   x 10 manches, tous joues faux pour forcer chaque indice a l'ecran), zero anomalie sur les trois.
   Elle la nomme "la seule des proprietes dont je reste garde qui serait facile a tenir par un
   test". **Je confirme, et je chiffre pourquoi elle est moins chere que les autres** : lecture de
   `QuestionContentSourceFactory` (section suivante) - le chargeur mappe chaque champ du YAML vers
   `Question` sans transformation (`String.valueOf` direct), donc la conformite au fichier est une
   propriete deterministe du chargeur, pas du hasard de la mecanique. Un test peut l'etablir sans
   jouer une seule manche HTTP :
   - **atteignabilite** : la fonction de "drainage" deja ecrite trois fois dans ce sprint
     (`AucunItemNeRepeteLEcranDUnAutreItemDuMemePaquetTest`, et les trois tests dedies de la
     section 5) tire deja tous les items d'une source finie jusqu'a epuisement. Il suffit d'une
     assertion de plus, comparant le nombre d'items draines au nombre d'entrees `items:` du YAML.
   - **conformite** : une comparaison champ a champ entre l'objet `Question` rendu par la chaine
     reelle et une seconde lecture independante du meme YAML (un `Yaml().load()` direct dans le
     test, sans passer par le chargeur qu'on verifie), pour ne pas se contenter que le chargeur
     s'accorde avec lui-meme.

   **Estimation** : une classe de test unitaire, aucune dependance neuve (SnakeYaml est deja sur le
   classpath du module), reutilisant le patron de drainage deja ecrit quatre fois ce sprint - de
   l'ordre d'une demi-journee, sans code de production, contre 1800 coups HTTP a rejouer a la main
   a chaque nouveau paquet. C'est le geste le plus rentable des deux, et je le recommande a
   `theo-archi` comme prochain garde-fou de contenu si la squad veut reduire ce qui reste sur la
   seule main de `nadia-prof`.

## 7. Le cout reel d'un sprint de contenu pur

**S9-4 a livre zero test Java neuf ; S9-1, S9-2 et S9-3 en ont livre respectivement 5, 6 et 7**
(verifie par moi en section 1, sur les commits de chaque lot). La question posee n'est pas de
blamer `sofia-sm` - son motif est ecrit dans le commit et il est correct sur ce qu'il affirme - mais
de chiffrer ce que les garde-fous derives couvrent reellement pour un paquet neuf, pour trancher si
un test dedie par paquet doit etre exige.

**Ce que les garde-fous derives couvrent deja pour tout paquet neuf, y compris sans test dedie**,
verifie par lecture de leur perimetre (section 0.4 de l'avis de faisabilite de `theo-archi`, que
j'ai retrouve exact) :

- un indice non vide sur chaque item ;
- un trou homogene si le paquet en declare un ;
- strictement plus d'items que de manches, en identifiants **et** en ecrans distincts depuis
  `ADR-0032` ;
- au plus quatre propositions par item ;
- pour `cp-problemes-additifs` specifiquement : chargement effectif par le catalogue
  (`CatalogueChargeableIT` demarre le contexte Spring complet et joue chaque jeu declare).

**Ce qu'ils ne couvrent pour aucun paquet, dedie ou non**, deja nomme en section 6 : la verite
d'un enonce, d'un indice, ou d'une comparaison de nombres. **Ce qu'un test dedie ajoute reellement**
- verifie sur les trois lots qui en ont un, par mutation (section 4) - **c'est precisement
l'exactitude arithmetique de chaque item**, qui n'est derivable d'aucun garde-fou generique parce
qu'elle depend du sens propre a chaque matiere (une soustraction n'est pas un complement, qui n'est
pas une comparaison). C'est la seule propriete que S9-1, S9-2 et S9-3 gardent et que S9-4 ne garde
pas : **aucun test, generique ou existant, ne verifie que les 22 operations de
`cp-problemes-additifs` sont arithmetiquement exactes.** `nadia-prof` l'a fait a la main (22 fois,
verifie dans sa fiche), mais rien ne le referait si le fichier changeait sans qu'elle rejoue.

**Ma reponse** : je recommande d'exiger un test dedie par paquet **quand le paquet declare une
operation verifiable par calcul** (arithmetique, comparaison de nombres) - le cout est faible (5 a
7 methodes, reprenant un patron deja ecrit trois fois) et la propriete qu'il ferme n'est gardee par
rien d'autre. Je ne l'etends pas aux paquets purement textuels sans operation calculable (un
probleme en situation n'a pas de "verite arithmetique" separee de son texte, qui reste hors de
portee de tout automate, section 6) : y exiger un test dedie n'ajouterait qu'une repetition des
garde-fous generiques deja derives. C'est une recommandation, pas une regle que j'ecris moi-meme -
`theo-archi` et `sofia-sm` tranchent le seuil exact ("declare une operation verifiable" doit devenir
un critere net s'il entre au depot).

## 8. Ce qui reste hors de mon perimetre, nomme pour ne pas etre confondu avec le Done technique

- **Done pedagogique** : trois REFUSE puis trois VALIDE SOUS RESERVE sur `s09-nadia-verdicts-2`,
  un VALIDE direct sur `cp-complements-10` (deja fusionne, `c6355e8`). Je ne prononce, ne
  requalifie et ne resume aucun de ces verdicts au-dela des faits verifies par moi en sections 3 et
  4 : c'est la seule autorite de `nadia-prof`.
- **Ligne de matrice** : sur `round-two` (`0d3e734`), `squad/programme/MATRICE-couverture.md` porte
  encore `en cours` pour les soustractions, la comparaison de nombres et les problemes additifs -
  verifie a la lecture. La levee `couvert-valide` que le second audit de `nadia-prof` autorise pour
  les deux premieres (elle ne conditionne rien sur la troisieme, toujours refusee sur le fond
  arithmetique-en-situation) n'est pas executee sur `round-two` tant que
  `s09-nadia-verdicts-2` n'est pas fusionnee : je ne l'anticipe pas.
- **`DEC-0021`** : reserve non bloquante, story au backlog non affectee. Je ne tranche pas s'il
  faut l'ouvrir plus tot ; je chiffre son cout technique en section 6.

## Fichiers cites dans ce rapport

- `adapters/adapter-content/src/test/java/com/recreo/adapter/content/AucunItemNeRepeteLEcranDUnAutreItemDuMemePaquetTest.java`
  (lu, mute une fois sur du contenu reel, restaure - section 3)
- `adapters/adapter-content/src/test/java/com/recreo/adapter/content/ChaqueJeuDuCatalogueOffrePlusDItemsQueDeManchesTest.java`
- `adapters/adapter-content/src/test/java/com/recreo/adapter/content/PaquetCpSoustractionsS9Test.java`
  (lu, mute une fois, restaure - section 4)
- `adapters/adapter-content/src/test/java/com/recreo/adapter/content/PaquetCpComplements10S9Test.java`
  (lu, mute une fois, restaure - section 4)
- `adapters/adapter-content/src/test/java/com/recreo/adapter/content/PaquetCpComparerNombresTest.java`
  (lu, mute une fois, restaure - section 4)
- `adapters/adapter-content/src/main/java/com/recreo/adapter/content/QuestionContentSourceFactory.java`
- `adapters/adapter-content/src/main/java/com/recreo/adapter/content/YamlContentPackLoader.java`
- `adapters/adapter-content/src/main/resources/contenu/cp-comparer-nombres.yaml`,
  `cp-soustractions.yaml`, `cp-complements-10.yaml` (mutes puis restaures, sections 3 et 4)
- `bootstrap/src/test/java/com/recreo/bootstrap/catalogue/PropositionsAccentueesJouablesIT.java`
- `squad/decisions/adr/ADR-0032-deux-items-d-un-meme-paquet-ne-presentent-jamais-le-meme-ecran.md`
- `squad/decisions/produit/DEC-0021-magnitude-des-additifs-de-problemes-additifs-reportee-hors-sprint.md`
- `squad/conformite/FICHE-bandit-manchot-soustractions-cp.md`
- `squad/conformite/FICHE-bandit-manchot-comparer-nombres-cp.md`
- `squad/conformite/FICHE-bandit-manchot-problemes-additifs-cp.md`
- `squad/conformite/FICHE-bandit-manchot-complements-10-cp.md`
- `squad/programme/MATRICE-couverture.md`
- `squad/sprints/s09-mathematiques-cp-nombres-et-calculs/avis-faisabilite.md`
- `squad/sprints/s08-cabinet-de-la-machine/rapport-qualite.md`
