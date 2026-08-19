# [Sprint s15] Confirmation ArchUnit - theo-archi

- **Date** : 2026-08-19. **Auteur** : theo-archi.
- **Arbre juge** : `round-two` a **`4c7cd45`**, la cloture de s15.
- **Provisionnement** : worktree dedie hors de l'arbre du depot, branche `s16-theo` forkee de
  `round-two`, lignee verifiee par `outils/verifier-lignee.sh`.
- **Prerequis** : le `java` du `PATH` de ce poste n'est pas un JDK 21 ; toutes les mesures sont prises
  sur le **JDK 21.0.10** designe par la toolchain de l'utilisateur, via `bash outils/mvn-jdk21.sh`.
- **Pourquoi ce fichier arrive apres la cloture** : `sofia-sm` l'a nomme absent plutot que fabrique, et
  me l'a route. C'est la sixieme fois que cette publication manque, et la premiere ou l'absence est
  nommee a la cloture par quelqu'un d'autre que moi. Je ne fabrique pas de chiffres a posteriori : je
  reexecute la commande sur l'arbre de cloture, aujourd'hui, et c'est ce que la section 1 rapporte.
- **Ce que ce fichier ne fait pas** : aucun Done pedagogique ni son anticipation, aucun arbitrage
  produit, aucune ligne dans `rapport-qualite.md`, dans `squad/conformite/` ni dans le planning.
- **Aucun serveur lance** : l'instance du port 8080 est celle de l'utilisatrice et n'a pas ete touchee.
  Les six tests d'integration prennent un port libre par `new ServerSocket(0)` ou
  `WebEnvironment.RANDOM_PORT` - verifie avant de lancer la commande, precisement pour ne pas entrer
  en conflit avec elle.

## 1. Le resultat de la commande

`bash outils/mvn-jdk21.sh -B clean verify`, jamais un perimetre partiel :

```
recreo ............................................. SUCCESS [  0.570 s]
recreo-domain ...................................... SUCCESS [ 10.509 s]
recreo-application ................................. SUCCESS [  5.148 s]
recreo-adapter-web ................................. SUCCESS [ 30.885 s]
recreo-adapter-content ............................. SUCCESS [  9.330 s]
recreo-bootstrap ................................... SUCCESS [ 49.951 s]
BUILD SUCCESS
Total time:  01:46 min
```

**528 tests, 0 echec, 0 erreur, 0 ignore. 29 regles ArchUnit.**

**Methode du comptage des tests, dans le meme paragraphe que le chiffre.** Somme de l'attribut `tests`
de la balise racine `testsuite` des **83 rapports XML** produits par cette seule execution, sous
`*/target/surefire-reports/` et `*/target/failsafe-reports/` des **cinq modules declares dans
`modules`** du pom agregateur, lus **apres le `clean`** de cette commande. Jamais un balayage lance
depuis la racine du depot, qui verrait les worktrees des autres agents. Repartition : `adapter-web`
**260**, `adapter-content` **121**, `bootstrap` **88** (dont **6 en failsafe**, les six tests
d'integration), `domain` **48**, `application` **11**. Exclus explicitement : les resumes `.txt` de
Surefire, qui ne conservent qu'un seul moteur JUnit par classe et sous-comptent.

**Methode du comptage des regles, deux methodes qui doivent concorder et concordent.** Critere
`ArchRule <nom> =`.

1. balayage des seuls `src/test/java` des cinq modules declares au pom : **29**, repartis en
   `bootstrap` **28** et `adapter-web` **1** (`AssertionsWebTest`) ; `domain`, `application` et
   `adapter-content` en portent zero ;
2. tous les fichiers `.java` du depot dont le chemin contient `src/test/java`, suivis **et** non
   suivis : **31 brut**, dont **2** vivent dans le faux depot de contre-exemple de
   `LesGardeFousSontMisALEpreuveTest`, sous `src/test/resources`, dont le chemin contient
   `src/test/java` sans etre un source de module. **31 - 2 = 29.**

**Rapprochement, sans ecart.** `sofia-sm` annonce 528 tests et 29 regles sur le meme arbre. Je
retrouve les deux, par mes propres commandes et non en recopiant les siennes.

## 2. Ce que le sprint a ajoute, et ce qui n'a pas bouge

**De 507 a la cloture de s14 a 528 : +21 tests. De 29 a 29 regles ArchUnit : aucune.** Le 507 est le
chiffre autoritaire de `sofia-sm`, que je n'ai pas remesure moi-meme sur cet arbre-la et que je cite
comme le sien - la seule mesure de ce fichier prise par mes commandes est celle de la section 1. Les
proprietes de ce sprint portent sur des feuilles de style, des gabarits et des documents assembles -
ArchUnit lit du bytecode, et aucun de ces trois n'y est visible. Le mecanisme automatique de chacune
est un test de module, execute par `mvn verify` comme tout le reste ; c'est le meme motif de
non-encodabilite en ArchUnit que celui d'`ADR-0022`, `ADR-0043` et `ADR-0046`, et il n'a pas change.

## 3. L'inventaire des cliquets sur l'arbre de cloture

C'est la seule information que ce fichier porte et qu'aucun autre document ne republie (`ADR-0013`,
amendement du 2026-08-15). Etat au 2026-08-19 sur `4c7cd45`, lu dans les sources :

| cliquet | dette | plafond | jeu | assertion |
|---|---|---|---|---|
| `LesGardeFousSontMisALEpreuveTest.DETTE_DE_BALAYAGE` (`FaviconTest`, `FrontieresDuJavaScriptTest`) | 2 | 2 | 0 | `hasSize` |
| `LesGardeFousSontMisALEpreuveTest.CRITERES_ENUMERES_DECLARES` (`AucunVocabulaireDeMatiereTest`) | 1 | 1 | 0 | `hasSize` |
| `LeRangDeLaBonneReponse...Test.DETTE_DE_RANG` (`cm1-perimetre`, `cm1-mesures`) | 2 | 2 | 0 | `hasSizeLessThanOrEqualTo` |
| `LeRangDeLaBonneReponse...Test.DETTE_DE_SEUIL_MINIMUM` (`cm1-perimetre`, `cp-soustractions`, `cp-complements-10`) | 3 | 3 | 0 | `hasSizeLessThanOrEqualTo` |
| `ChaquePaquetPorteUnIndiceTest.DETTE_DE_CONTENU` (vide) | 0 | 0 | 0 | nulle par construction |

**Aucun cliquet ne porte de jeu, et chaque exemption nomme encore un objet reel** - les cinq paquets
cites existent tous dans `adapters/adapter-content/src/main/resources/contenu/`, et ce n'est pas ma
parole : les tests l'assertent eux-memes. Aucun plafond n'a bouge pendant s15, et aucune exemption n'a
ete ajoutee.

**Rappel de la distinction etablie en s13** : les deux budgets de production sur le JavaScript maison
(`FrontieresDuJavaScriptTest`, `PLAFOND_LIGNES` et `PLAFOND_OCTETS`) ne sont pas des cliquets
d'exemption et leur marge est ce qu'ils autorisent a dessein. Ils ne figurent pas dans le tableau, et
c'est dit plutot que silencieux.

## 4. Ce que cette publication ne rachete pas

Six absences sur seize sprints clos, dont celle-ci publiee apres la cloture. `ADR-0013` a cesse en s13
d'adosser une clause a cette publication, precisement parce qu'une clause qui depend d'une ceremonie
n'est pas une clause ; ce fichier est donc un rapport utile, pas le mecanisme d'une regle. Ce qu'il
porte et que rien d'autre ne porte reste la section 3.
