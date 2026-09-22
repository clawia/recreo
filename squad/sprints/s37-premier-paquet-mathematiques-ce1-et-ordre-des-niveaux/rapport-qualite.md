# Rapport qualite de cloture - s37

**Auteur** : `yanis-qa`. **Date** : 2026-09-22. **Arbre mesure** : `s37-sofia` a `9412f659`,
worktree dedie (`git worktree add ../recreo-s37-yanis -b s37-yanis 9412f659`), lignee verifiee
(`git rev-parse --abbrev-ref HEAD` -> `s37-yanis`, `git merge-base --is-ancestor round-two HEAD` ->
0), separe de tout worktree partage (regle de mesure isolee de s30).

Contenu du sprint, pour situer cet audit : deux lots disjoints - `S36-ORDRE-NIVEAUX` (`ADR-0095`,
l'ordre des niveaux de l'en-tete devient une cle de tri explicite portee par
`OrdreDesNiveaux.SEQUENCE`, avec un garde-fou de completude sur le catalogue servi) et `S37-1`
(`ADR-0096`, premier paquet mathematiques CE1 statique, `ce1-additions-jusqu-a-100.yaml`, garde-fou
de repartition par forme `ADD-CE1-1`/`ADD-CE1-2`).

---

## 1. Ecart de comptage 1509 / 1518 - cause trouvee, mesuree, pas supposee

**Consigne recue** : `theo-archi` annonce 1518 tests unitaires sur son propre worktree avant
fusion ; `sofia-sm` recompte independamment sur l'arbre fusionne et trouve 1509, un ecart de 9 non
explique.

**Mesure faite sur mon propre worktree, arbre `9412f659`**, `bash outils/mvn-jdk21.sh -B clean
verify` depuis la racine, JDK de compilation **21.0.10** constate en interrogeant ce JDK (pas la
toolchain declaree) :

```
java du PATH : 17.0.4.1 | JDK de compilation : 21.0.10 | JAVA_HOME : renseignee
| toolchains lu par ce script : oui | toolchains lu par Maven : oui (un autre que celui lu par ce script)
```

Resultat : **BUILD SUCCESS**, 6 modules sur 6, 0 echec, 0 erreur.

**Methode 1 - blocs `[INFO] Results:` apres `clean`**, six blocs sur ce meme lancement :

| Module | Tests run |
|---|---|
| recreo-domain | 111 |
| recreo-application | 17 |
| recreo-adapter-web | 400 |
| recreo-adapter-content | 725 |
| recreo-bootstrap (surefire, unitaires) | 265 |
| recreo-bootstrap (failsafe, integration) | 6 |
| **Total unitaires** | **1518** |
| **Total integration** | **6** |

**Methode 2, independante** : somme de l'attribut `tests="..."` de chaque rapport XML Surefire
(tous modules) sur ce meme lancement : **1518**. Les deux methodes convergent, elles confirment le
chiffre de `theo-archi`.

**Cause du chiffre 1509 de `sofia-sm`, isolee par une troisieme mesure sur le meme arbre** :
somme des lignes `Tests run:` des rapports **`.txt`** Surefire (jamais Failsafe) - la methode que
la section 4 de `squad/README.md` interdit explicitement depuis l'amendement de s11 - donne
**1509** sur cet arbre, exactement le chiffre de `sofia-sm`, exactement l'ecart de 9.

Cinq classes de ce depot melangent, dans une meme classe, un champ statique `@ArchTest ArchRule`
(execute par le moteur propre a `archunit-junit5`) et une ou plusieurs methodes `@Test` ordinaires
(executees par Jupiter). Le rapport `.txt` de la classe n'ecrit alors que le compte du moteur
Jupiter ; le rapport XML agrege correctement les deux moteurs. Ecart mesure classe par classe
(`tests=` XML moins `Tests run:` texte) :

| Classe | Module | Ecart |
|---|---|---|
| `AssertionsWebTest` | adapter-web | 1 |
| `AbstractionsCiteesTest` | bootstrap | 4 |
| `ChargementDuContenuTest` | bootstrap | 1 |
| `LesReglesMordentTest` | bootstrap | 1 |
| `LOrdreDesNiveauxResteUneDonneeTest` | bootstrap | 2 |
| **Total** | | **9** |

C'est la meme famille de defaut que celle deja documentee par l'amendement de s11 (4 classes, ecart
de 7 a l'epoque), a laquelle s'ajoute ce sprint une cinquieme classe : `LOrdreDesNiveauxResteUneDonneeTest`,
livree par `ADR-0095` pour `S36-ORDRE-NIVEAUX`, qui porte elle-meme 2 `@ArchTest` et 3 `@Test` et
contribue 2 des 9 a l'ecart. Ce n'est donc pas une seule fois un chiffre faux : c'est une methode de
comptage deja proscrite par le README, reappliquee, et dont le defaut s'est aggrave d'exactement la
taille du materiau neuf livre ce sprint.

**Conclusion sur l'ecart** : ni une difference d'arbre, ni un module non reconstruit. `sofia-sm` a
mesure sur le meme arbre que `theo-archi` (verifie : les deux mesures portent sur `9412f659`, aucun
fast-forward ulterieur entre les deux), avec une methode de sommation differente et deja identifiee
comme fautive par ce depot. Aucun des deux chiffres n'est un mensonge ; un seul est la methode que
`squad/README.md` section 4 impose.

## 2. Compte de tests autoritaire

**1518 tests unitaires + 6 tests d'integration = 1524, sur `s37-sofia` a `9412f659`**, methode :
somme des blocs `Results:` apres `clean`, recoupee par la somme des attributs `tests=` des rapports
XML Surefire/Failsafe du meme lancement - jamais la somme des lignes `Tests run:` des `.txt`, pour
le motif mesure en section 1.

Ce chiffre est un compte sur mon propre worktree, pas un total sur l'arbre fusionne round-two :
la fusion reste a `sofia-sm` (`squad/README.md` section 4).

**Couverture par module** (fichiers `*Test.java` / `*IT.java` presents, aucun module vide) :

| Module | Fichiers de test |
|---|---|
| domain | 10 |
| application | 2 |
| adapter-web | 49 |
| adapter-content | 98 |
| bootstrap | 49 |

## 3. Mutation de garde-fous recents sur materiau reel

Regle appliquee (amendement s10) : le garde-fou est mis a l'epreuve en mutant l'objet reel qu'il
pretend garder - ici le paquet YAML reellement servi - jamais une fixture ni une copie en memoire
deja ecrite par le garde-fou lui-meme. Les deux candidats du planning sont mordus ci-dessous, tous
deux sur le meme fichier reel : `adapters/adapter-content/src/main/resources/contenu/ce1-additions-jusqu-a-100.yaml`
(32 items, seul paquet CE1 servi a ce sprint). Fichier restaure apres chaque mutation par
recopie depuis une sauvegarde prise avant toute modification, `git status --short` et `git diff
--stat` verifies vides sur ce fichier apres restauration, dans les deux cas.

### 3.1 `ADR-0096` - repartition par forme des additions CE1 (`ADD-CE1-2`)

Mutation : trois items convertis en operande nul (`8 + 5`, `7 + 9`, `12 + 6` deviennent `0 + 5`,
`0 + 9`, `0 + 6`, blanc insecable U+00A0 avant le point d'interrogation preserve, ADR-0067). Effet
attendu : la bande "operande nul" (0-5 %) et la bande "somme <= 20" (10-20 %) sortent toutes deux de
leurs bornes.

Commande : `bash outils/mvn-jdk21.sh -B -pl adapters/adapter-content -am test
-Dtest=ChaquePaquetDAdditionsDeCe1TientSaRepartitionParFormeTest#chaquePaquetDAdditionsDeCe1ServiTientLesHuitBandes
-Dsurefire.failIfNoSpecifiedTests=false`

- **Fichier mute** : rouge, 1 echec sur 1 -
  `java.lang.AssertionError: repartition par forme (ADD-CE1-2) : [ce1-additions-jusqu-a-100 : forme
  "operande nul" a 9.38 % (3 items sur 32), hors de la bande 0-5 % qu'ADD-CE1-2 fixe,
  ce1-additions-jusqu-a-100 : forme "somme <= 20" a 6.25 % (2 items sur 32), hors de la bande
  10-20 % qu'ADD-CE1-2 fixe]`.
- **Fichier restaure** : vert, 10/10 (classe entiere relancee, `chaquePaquetDAdditionsDeCe1ServiTientLesHuitBandes`
  inclus).

Le garde-fou mord bien sur le paquet reellement servi, pas seulement sur ses morsures en memoire
deja ecrites dans le fichier de test.

### 3.2 `ADR-0095` - niveau servi hors sequence

Mutation : `descripteur.niveau` du meme paquet reel change de `"CE1"` a `"CE2"`, une valeur absente
d'`OrdreDesNiveaux.SEQUENCE`.

Commande : `bash outils/mvn-jdk21.sh -B -pl adapters/adapter-content -am test
-Dtest=ChaqueNiveauServiEstNommeDansLaSequenceTest#toutNiveauServiParLeCatalogueEstNommeDansLaSequence
-Dsurefire.failIfNoSpecifiedTests=false`

- **Fichier mute** : rouge, 1 echec sur 1 -
  `java.lang.AssertionError: niveaux servis : [le niveau "CE2" est servi par le catalogue et n'est
  pas nomme dans OrdreDesNiveaux.SEQUENCE [CP, CE1, CM1, CM2]. L'en-tete le rangerait en dernier
  sans rien signaler (ADR-0095) : soit la sequence l'accueille, apres une decision produit sur sa
  place, soit le paquet corrige l'orthographe de son niveau]`.
- **Fichier restaure** : vert, classe entiere relancee.

Les deux garde-fous mordent reellement sur le seul paquet CE1 aujourd'hui servi, et pas seulement
sur les contre-exemples deja commites qu'ils portent chacun pour `ADR-0013` obligation 3.

## 4. Tests instables

Aucun test instable observe. Chaque lancement cite dans ce rapport n'a ete execute qu'une fois ; un
test lance une seule fois n'est pas qualifie de stable pour autant, et ce rapport ne l'affirme pas.

## 5. Ce que ce rapport ne fait pas

Il ne prononce aucun Done pedagogique - `nadia-prof` seule le fait. Il ne juge le niveau scolaire du
paquet `ce1-additions-jusqu-a-100`. Il ne mesure que le job `build` de la CI (`bash
outils/mvn-jdk21.sh -B clean verify`), pas les trois autres jobs de `.github/workflows/ci.yml`
(`chemins`, `prefixes`, `outils`) : ce rapport ne les nomme pas comme verifies parce qu'ils ne l'ont
pas ete ici. Il ne confirme aucune `ArchRule` au sens du mandat de `theo-archi`. Il n'etablit aucun
total sur l'arbre fusionne `round-two` : c'est `sofia-sm` qui l'etablit a l'integration.
