# Porte technique - lot `theo-archi` de s22 (`s22-theo-2`)

- **Auteur** : `theo-archi`
- **Date** : 2026-08-29
- **Branche** : `s22-theo-2`, partie de `round-two` a `2968591`
- **Contenu du lot** : `ADR-0064` et sa regle (obstacle 16, elargi de 1 a 5 garde-fous) ;
  `outils/mvn-jdk21.sh` (obstacle 23) ; amendement de la **regle 4** d'`ADR-0063`.

## 1. La commande, sa version de JDK, son perimetre

```
bash outils/mvn-jdk21.sh -B clean verify
```

**Reacteur complet**, cinq modules, aucune mesure partielle citee ici. Le JDK sur lequel la mesure
porte est celui que `maven-toolchains-plugin` resout et qui compile et execute les tests :
**21.0.10**, lu dans la ligne de demarrage des tests d'integration Spring Boot
(*"Starting ... using Java 21.0.10"*). La machine virtuelle qui execute Maven, elle, est une 25.0.2
sur ce poste, et c'est le sujet du lot 2 ci-dessous.

```
BUILD SUCCESS - Total time: 01:26 min
recreo, recreo-domain, recreo-application, recreo-adapter-web, recreo-adapter-content,
recreo-bootstrap : SUCCESS
```

## 2. Le compte de tests, avec sa methode

**Methode** : somme des attributs `tests`, `failures`, `errors`, `skipped` de l'element
`<testsuite>` des rapports **XML** `TEST-*.xml` de `target/surefire-reports/` et
`target/failsafe-reports/` des cinq modules, apres le `clean verify` ci-dessus. Les deux moteurs
sont comptes separement.

**Pourquoi le XML et pas le `.txt`, dit ici plutot que subi.** J'ai d'abord agrege les en-tetes
`Tests run:` des rapports **texte**, et ce compte est **faux par defaut de 7** : il rend 331 pour
`adapter-web` et **96** pour `bootstrap`, la ou Maven imprime lui-meme `Tests run: 102` pour
`bootstrap` a la fin de sa phase de test. **Cause identifiee plutot que supposee**, par comparaison
fichier par fichier des deux formats : **quatre classes divergent**, toutes portees par le moteur
JUnit d'ArchUnit - `AbstractionsCiteesTest` (XML 5, texte 1), `ChargementDuContenuTest` (2 / 1),
`LesReglesMordentTest` (29 / 28) et `AssertionsWebTest` (2 / 1). Le rapport texte y resume le jeu
en une ligne quand le XML enumere chaque regle comme un cas. C'est la troisieme des causes de
divergence que j'avais nommees en s21 en annoncant 29 regles pour 25 reelles - *deux moteurs JUnit
dont les rapports texte n'en gardent qu'un*. **Le chiffre ci-dessous est celui des XML, et il
concorde avec les lignes `Results` de Maven.**

| Module | Surefire | Failsafe |
|---|---|---|
| `domain` | 49 | - |
| `application` | 13 | - |
| `adapters/adapter-web` | 332 | - |
| `adapters/adapter-content` | 224 | - |
| `bootstrap` | 102 | 6 |
| **Total** | **720** | **6** |

**726 tests, 0 echec, 0 erreur, 0 ignore.**

**Regles ArchUnit : 30, inchange.** Methode : le compte n'est pas un `grep`, c'est la derivation du
depot lui-meme - `LesGardeFousSontMisALEpreuveTest.toutesLesRegles(racine)`, qui balaie les sources
de test des modules declares par le pom agregateur et compte les `ArchRule` declarees puis passees a
un `check`. Mesure le 2026-08-29 sur cet arbre. **`ADR-0064` n'ajoute aucune regle ArchUnit** : son
mecanisme est un recensement de sources, comme `ADR-0013` et `ADR-0039`.

**Garde-fous de balayage recenses : 50** (meme derivation, meme date).

**Juges purs des outils** : `bash outils/verifier-jdk-test.sh` rend **23 cas**, tous verts (14 avant
ce lot). L'etape CI `Tester les juges purs des outils` balaie `outils/*-test.sh` et
`squad/outils/*/*-test.sh` ; elle les execute donc sans qu'aucune liste ne soit a tenir.

## 3. `ADR-0064` - la preuve que la regle mord

Trois mutations, **deux sur materiau reel** (le contenu servi a l'enfant) et une sur le depot
fictif. Aucune ne depend d'une graine de tirage.

| Mutation | Attendu | Constate |
|---|---|---|
| `ChaquePaquetATrousPorteSonTrouSurChaqueItemTest` : borne derivee remise a `60`, l'etat exact d'avant ce lot | le recensement nomme le garde-fou et chiffre l'ecart | `Tests run: 4, Failures: 1` - *"ChaquePaquetATrousPorteSonTrouSurChaqueItemTest : borne de tirage ecrite en clair : 60, alors que le plus grand paquet enumere du catalogue en declare 99. Ce garde-fou lit 39 items de moins que ce paquet ne contient, et ne le dit pas"* |
| `ChaquePaquetPorteUnIndiceTest` : borne derivee remplacee par la borne d'echantillon | l'assertion d'exhaustivite du garde-fou lui-meme rougit, en nommant le paquet | `Tests run: 4, Failures: 1` - *"cm1-tables-multiplication declare 99 items sur disque et ce garde-fou n'en a tire que 60 : la clause porterait sur un echantillon sans le dire"* |
| Depot fictif : deux tireurs sur le meme paquet de cinq items, l'un borne a trois a la main, l'autre derive | seul le fautif est nomme | `containsExactly` sur un seul nom, vert - la regle ne peut pas echouer par inclusion |

**Ce que ces mutations valent, et ce qu'elles ne valent pas.** Les deux premieres portent sur le
catalogue reel : le chiffre de 39 est celui du contenu que l'enfant recoit, pas d'une fixture de ma
main. La troisieme, elle, est une fixture, et elle ne prouve qu'une chose - que la comparaison
nomme le bon des deux tireurs.

**Un defaut de ma propre regle, trouve avant commit et nomme ici.** La premiere ecriture du
recensement designait sa population par la presence de `chargerPaquet(` et par une boucle. Elle
nommait `PaquetsFrancaisS04Test`, qui tire vingt fois **avec remise** - un ensemble vide et immuable
passe a chaque tour - pour constater une variete, et dont la borne de vingt ne lit aucun paquet en
partie. **Une regle qui echoue par inclusion est aussi fausse qu'une regle qui echoue par
omission.** Le critere exige desormais que l'ensemble des deja-vus passe au tirage soit un
identifiant que le corps de la boucle alimente.

## 4. Le recomptage des 39 items : ce qu'il revele

**Population, avec sa date et son perimetre** : les 21 fichiers de
`adapters/adapter-content/src/main/resources/contenu/`, le **2026-08-29**, une occurrence de
`- enonce:` par item enumere. **478 items**, **20 paquets enumeres** et **1 paquet combinatoire**
qui n'enumere rien. **Un seul paquet depasse 60** : `cm1-tables-multiplication`, **99 items**. Ce
chiffre vieillira dans ce sprint meme - trois paquets neufs sont annonces - et c'est pourquoi il
porte sa date.

**Verdict : aucun defaut cache.** Les cinq garde-fous lisent desormais les 99 items de ce paquet et
sont verts. Ce n'est pas une deduction, c'est le resultat du `clean verify` ci-dessus. Deux points
meritent d'etre nommes plutot que resumes :

- **Le compteur d'objets reels d'`AucunIndiceNeContreditUnAutreItemDuMemePaquetTest` reste a 0.**
  Ce garde-fou compte les indices qui portent la forme generale non temperee, et un test exige que
  ce compte vaille exactement zero. **Les 39 items jusque-la non lus n'en contiennent aucun** : la
  clause reste verte sans objet, pour la meme raison qu'avant, et non parce qu'elle ne les regardait
  pas.
- **Les 39 ne sont pas necessairement les memes d'un garde-fou a l'autre** : les graines de tirage
  different (7, 7, 11, 7, 7). Le chiffre est un **compte par garde-fou**, pas un ensemble commun.
  C'est aussi pourquoi la preuve de morsure ne repose sur aucune graine.

**Ce que ces 39 items ne sont pas.** Je n'affirme aucun defaut pedagogique :
`cm1-tables-multiplication` a son Done pedagogique, et ce lot ne prononce rien de pedagogique. Ce
qui etait en cause etait **le silence**, pas le contenu - la distinction d'`ADR-0051`.

**Un ecart trouve sur ma propre mesure de s21, qui est le resultat le moins confortable de ce lot.**
Mon releve d'ouverture cherchait la chaine `int TIRAGES` : **9 lignes, dont 5 a 60**. Le recensement
d'`ADR-0064`, qui derive sa population de la **forme** de la boucle, en trouve **13 dans deux
modules**, dont une dans `bootstrap` (`PropositionsAccentueesJouablesIT`, `BUDGET_DE_TIRAGE = 200`)
qu'aucune recherche par ce nom ne pouvait voir. Aucune des quatre boucles manquees n'est partielle
aujourd'hui, donc l'omission n'a rien coute - mais elle etait de la famille exacte que je reproche
aux autres, et la corriger etait le seul moyen d'ecrire une regle qui ne soit pas decorative.

## 5. Obstacle 23 - `outils/mvn-jdk21.sh`

**Etat : ferme.** L'enveloppe ne repete plus la version qu'une toolchain **declare** ; elle affiche
ce que le JDK resolu **rapporte**, et ne confirme la declaration que lorsqu'elle est confirmee. La
transformation est pure et vit dans `outils/verifier-jdk.sh` (`version_majeure_java`,
`ligne_de_lancement`), couverte par le juge existant `outils/verifier-jdk-test.sh`, lui-meme execute
par l'etape CI des juges purs. **Aucun `ADR` :** `ADR-0009` et `ADR-0010` portent deja la doctrine,
seule l'implementation mentait.

**Ce que la premiere ligne dit sur ce poste, avant et apres :**

- avant : `Maven lance sur le JDK de la toolchain jdk 21 : 25.0.2` - une phrase qui nomme 21 et
  affiche 25 dans la meme ligne ;
- apres : `Maven lance sur le JDK que designe la toolchain jdk 21, et ce JDK rapporte 25.0.2 : la
  declaration de version n'a pas ete confirmee`, suivi du diagnostic qui nomme la cause possible -
  Maven lit le fichier de toolchains du profil utilisateur de **sa** machine virtuelle, qui n'est
  pas forcement le repertoire de `HOME`.

**Morsure prouvee** : `ligne_de_lancement` remise a sa forme d'avant (afficher la version demandee
quoi qu'il arrive) fait echouer **3 des 23 cas** du juge pur, exactement les trois ou la declaration
n'est pas confirmee (25.0.2, 1.8.0_202, inconnue).

**Le build reste juste, et ce lot ne change rien a ce qu'il compile.** `maven-toolchains-plugin`
resout un **21.0.10** a chaque module, et c'est lui qui compile et execute les tests - la ligne
*"using Java 21.0.10"* du `verify` ci-dessus le constate. L'enveloppe s'arrete desormais si le JDK
resolu est anterieur a 17, ce que `maven-enforcer-plugin` exige de la machine virtuelle de Maven ;
sur ce poste, elle ne s'arrete pas et lance Maven sur le 25.0.2 comme avant.

## 6. `ADR-0063` regle 4 - la forme retenue pour l'amendement

**Forme : un amendement date, insere dans la section Decision sous les regles 2 et 3, plus une
phrase datee dans les Consequences. Rien n'est efface.**

La regle 4 - *"aucun item ne laisse l'indice ni le verdict sous le pli a 744 x 551"* - **perd son
demandeur** avec `DEC-0053` arbitrage 1 du 2026-08-28, lecture large confirmee (indice, verdict,
propositions). Elle **passe d'etape de revue avant fusion a conseil de premier recours**, au regime
exact des regles 2 et 3.

**Pourquoi la dater plutot que la retirer** : c'est ma propre formule de l'avis de faisabilite,
section 1.4. Un enonce non executable qui disparait sans trace ment autant qu'un enonce qui reste
en vigueur sans demandeur ; et c'est le seul type d'enonce qu'un lecteur peut lire comme opposable
sans qu'une CI le detrompe.

**La relecture au singulier, verifiee plutot qu'affirmee.** Le risque que j'avais nomme moi-meme est
qu'on relise au singulier un motif qui en porte plusieurs. J'ai donc relu chaque motif du reste
d'`ADR-0063` avant d'ecrire l'amendement, et l'amendement l'ecrit :

- la **decision 1** (au plus trois propositions) est intacte - le pli n'y est qu'un motif
  d'ouverture, et l'`ADR` disait deja *"Cette regle ne ferme pas `REP-8` et ne pretend pas le
  faire"* ;
- le **cliquet de la decision 2** est intact : il compte des propositions, pas des pixels ;
- **`PROPOSITIONS_PAR_ITEM = 3` de `PaquetCm1ReperesTest` est intact**, et c'est le point fin :
  cette constante porte **trois** motifs de `nadia-prof` dont **un seul** est le pli. A quatre
  propositions, le pool de cinq libelles rend l'item soluble par elimination (`REP-4`) ; a deux,
  l'enfant a une chance sur deux sans connaitre la distinction. **Cet amendement ne requalifie rien
  d'autre que la regle 4 elle-meme.**

**Aucun test du depot ne change de verdict** du fait de cet amendement, et aucun ne peut le faire :
la regle 4 n'etait executee par rien. C'est verifiable dans le `verify` ci-dessus, qui ne porte
aucune modification de test liee au pli.

## 7. Deux points signales, non traites, avec leur declencheur

- **`squad/outils/ines-design/mesurer-au-glyphe.py` importe `PIL` au niveau du module**
  (`from PIL import Image`, ligne 49). **Sans effet aujourd'hui** : aucun fichier du depot ne
  reference ce script, et l'etape CI `Tester les juges purs des outils` ne balaie que les
  `*-test.sh` ; `squad/outils/ines-design/` n'en porte aucun. **Je ne le touche pas** : c'est
  l'outil d'un autre agent, et deplacer un import sans le juge qui l'exige serait un changement sans
  garde - le motif exact pour lequel les cinq scripts de l'obstacle 25 residuel restent reportes.
  **Declencheur, ecrit pour etre verifiable** : le jour ou un `squad/outils/ines-design/*-test.sh`
  existe et charge ce module, l'etape CI echoue a l'import sur un agent sans Pillow ; l'import passe
  alors dans la fonction qui s'en sert, comme `import websocket` le 2026-08-28. C'est la clause
  d'`ADR-0025`, telle quelle.
- **Obstacle 14, troisieme declencheur propre a s22** : *si un lot de ce sprint touche `recreo.css`
  dans la bande des colonnes de propositions, la condition est reverifiee avant fusion.* **Ce lot ne
  touche pas `recreo.css`** - `git diff --stat` ne porte aucun fichier de `adapters/adapter-web`. Le
  declencheur reste arme pour les autres lots du sprint, et il reste un `grep`, pas une story.

## 8. Ce que ce lot ne prononce pas

Aucun statut de couverture, aucun Done technique de sprint, aucun Done pedagogique. La fusion
appartient a `sofia-sm`.
