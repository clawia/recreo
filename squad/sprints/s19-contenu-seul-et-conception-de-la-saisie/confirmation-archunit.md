# [Sprint s19] Confirmation ArchUnit - porte technique, cloture

- **Date** : 2026-08-24. **Auteur** : `theo-archi`.
- **Branche** : `s19-theo-3`, worktree dedie, ouvert avant toute ecriture.
- **Arbre juge** : `round-two` a `51301d7`, SHA releve sur la branche et lignee verifiee
  (`git merge-base --is-ancestor 51301d7 HEAD`), puis mon propre lot par-dessus.
- **Machine de mesure** : Maven lance par `outils/mvn-jdk21.sh`, donc sur le JDK de la toolchain
  declaree - **JDK 21.0.10**. Le `java` du `PATH` de ce poste est un JRE 8 qui ne compile pas ; c'est
  bien la toolchain qui a compile et execute, la ligne du script le dit a chaque lancement.

> **Amendement date du 2026-08-27 (`theo-archi`, s21, story `S21-6`) - la mesure ci-dessus est
> intacte, c'est la phrase qui l'explique qui est corrigee.** Le chiffre **21.0.10 est exact** :
> reverifie le 2026-08-27 a trois sources sur un `bash outils/mvn-jdk21.sh -B clean verify` a la
> racine du reacteur - les lignes `Toolchain in maven-compiler-plugin` et
> `Toolchain in maven-surefire-plugin` du journal, les proprietes `java.version` et
> `java.vm.version` d'un rapport surefire produit par cette execution, et la version majeure 65 de
> l'en-tete d'une classe du fat jar. **Ce qui est faux, c'est le raccordement affirme** :
> `outils/mvn-jdk21.sh` lit `${HOME}/.m2/toolchains.xml`, ou `HOME` est la variable du shell POSIX,
> tandis que Maven resout sa toolchain dans `${user.home}/.m2/toolchains.xml`, ou `user.home` est le
> profil utilisateur de la machine virtuelle Java. Sur le poste de mesure ce sont **deux fichiers
> differents** : le 2026-08-27, celui du shell declare un **JDK 25.0.2** et celui que Maven lit un
> **JDK 21.0.10**. L'enveloppe pose donc `JAVA_HOME` sur le 25.0.2, qui gouverne la machine
> virtuelle de **Maven lui-meme**, pendant que Maven compile et execute les tests sur le 21.0.10 de
> **sa** toolchain. Le script imprime pourtant `toolchain jdk 21 : 25.0.2` a chaque lancement, sans
> jamais comparer les deux valeurs. **Ce que la phrase d'origine aurait du dire** : le 21.0.10 est
> etabli par le journal du build et par les rapports surefire, jamais par la ligne de diagnostic de
> l'enveloppe. Aucun chiffre n'est remplace ici, et la phrase d'origine reste lisible au-dessus.

Un "je pense que c'est bon" ne vaut rien : les deux mesures ci-dessous portent la commande et sa
sortie.

---

## 1. Mesure 1 - l'arbre fusionne par `sofia-sm`, avant mes ecritures

```
bash outils/mvn-jdk21.sh -B clean verify
```

Reacteur **complet** (six modules), donc aucun `-am` a citer : le `-am` ne concerne que les mesures
partielles, et il n'y en a qu'une dans ce document, en section 3, ou il est porte et dit.

```
[INFO] Reactor Summary for recreo 0.1.0-SNAPSHOT:
[INFO] recreo ............................................. SUCCESS
[INFO] recreo-domain ...................................... SUCCESS
[INFO] recreo-application ................................. SUCCESS
[INFO] recreo-adapter-web ................................. SUCCESS
[INFO] recreo-adapter-content ............................. SUCCESS
[INFO] recreo-bootstrap ................................... SUCCESS
[INFO] BUILD SUCCESS
```

Totaux par module, lus sur les six lignes `Results:` du journal Maven :

| Module | Surefire | Failsafe |
|---|---|---|
| `recreo-domain` | 48 | - |
| `recreo-application` | 13 | - |
| `recreo-adapter-web` | 328 | - |
| `recreo-adapter-content` | 170 | - |
| `recreo-bootstrap` | 92 | 6 |

**657 tests, 0 echec, 0 erreur, 0 ignore. BUILD SUCCESS.** Le chiffre annonce par `sofia-sm` est
confirme a l'unite pres, sur reacteur complet.

**Note de methode qui vaut pour quiconque recompte** : les totaux se lisent dans le **journal Maven**,
jamais dans les rapports texte de `surefire-reports`. `LesReglesMordentTest` est execute par deux
moteurs JUnit - 1 test par le moteur ArchUnit, 28 par Jupiter - et le rapport texte n'en garde qu'un.
C'est l'une des trois causes de divergence que j'avais identifiees en s14 apres avoir annonce 29
regles sans methode ; elle est toujours active sur cet arbre.

---

## 2. Le compte de regles ArchUnit, avec sa methode

C'est le chiffre qui entrera dans le `RAPPORT.md`, et j'ai moi-meme etabli que le "29" affiche
pendant trois ADR etait inverifiable faute de methode. La voici, dans le meme paragraphe que le
chiffre.

**Perimetre** : tous les fichiers `.java` de l'arbre du worktree. **Exclusions** : `target/` (des
copies compilees du meme code), et les fichiers sous un chemin `contreexemple` (du code fautif
volontaire, qui ne s'execute jamais comme regle). **Critere** : une declaration de champ
`static final ArchRule`.

```
grep -rn "static final ArchRule " --include=*.java . | grep -v "/target/" | grep -v "/contreexemple/"
```

| Fichier | Regles |
|---|---|
| `adapters/adapter-web/.../archi/AssertionsWebTest.java` | 1 |
| `bootstrap/.../archi/AbstractionsCiteesTest.java` | 4 |
| `bootstrap/.../archi/AccesALEtatDeSessionTest.java` | 2 |
| `bootstrap/.../archi/ArchitectureEnCouchesTest.java` | 2 |
| `bootstrap/.../archi/ChargementDuContenuTest.java` | 1 |
| `bootstrap/.../archi/DecouplageMecaniqueContenuTest.java` | 8 |
| `bootstrap/.../archi/DifficulteTest.java` | 3 |
| `bootstrap/.../archi/GestionDesErreursTest.java` | 4 |
| `bootstrap/.../archi/LeChoixDesJeuxResteDerriereLePortTest.java` | 1 |
| `bootstrap/.../archi/LesReglesMordentTest.java` | 1 |
| `bootstrap/.../archi/PureteDesCouchesTest.java` | 2 |
| `bootstrap/.../archi/ScanDeComposantsTest.java` | 1 |
| **Total** | **30** |

**30 regles ArchUnit reelles.** Trois precisions qui font la difference entre ce chiffre et les
chiffres voisins qu'on pourrait obtenir :

1. **29 de ces 30 portent `@ArchTest`** (comptage independant :
   `grep -rn "@ArchTest" --include=*.java . | grep -v "/target/" | grep -v "/contreexemple/"` rend
   **29**). La trentieme,
   `ScanDeComposantsTest.aucuneClasseDeTestSurLeClasspathDeLApplicationNePorteDeStereotype`, est
   invoquee par un `@Test` ordinaire. Un comptage des seuls `@ArchTest` rend donc 29 et n'est pas
   faux ; il ne compte simplement pas la meme chose.
2. **2 champs `ArchRule` supplementaires existent dans l'arbre et sont hors compte** :
   `RegleContreExemple.uneRegleEprouvee` et `RegleContreExemple.uneRegleJamaisEprouvee`, dans le faux
   depot sur lequel `LesGardeFousSontMisALEpreuveTest` se prouve mordant. Ce sont deux instances d'un
   type `ArchRule` fictif declare dans le meme fichier ; elles ne s'executent jamais. Le comptage brut
   sans exclusion rend donc **32**.
3. **`ADR-0059` et `ADR-0060` n'ajoutent aucune regle ArchUnit**, et ce n'est pas un defaut de
   rigueur : leurs objets sont une declaration de feuille de style confrontee au document servi
   (`ADR-0059`) et une chaine de caracteres produite par le chargement d'une ressource (`ADR-0060`).
   ArchUnit juge des types Java. Chacun porte a la place un garde-fou de balayage dans le module qui
   possede l'artefact, avec ses jeux fautifs commites - la section "Regles executables" de chaque ADR
   ecrit ce choix et son motif.

**Le compte de regles ArchUnit du sprint s19 est donc de 30, inchange depuis `ADR-0058`** (29 avant
ce lot-la). Ce qui a augmente ce sprint, ce sont les garde-fous de balayage, qui se comptent
autrement et ne doivent pas etre additionnes aux regles.

---

## 3. Mesure 2 - apres mon lot de cloture (`ADR-0060` et son garde-fou)

Mon lot ajoute un garde-fou de balayage dans `adapter-content`, ses quatre jeux fautifs, l'`ADR-0060`,
la ligne d'index et l'amendement de mon avis. Aucune ligne de production n'est touchee.

```
bash outils/mvn-jdk21.sh -B clean verify
```

```
[INFO] Reactor Summary for recreo 0.1.0-SNAPSHOT:
[INFO] recreo ............................................. SUCCESS [  0.458 s]
[INFO] recreo-domain ...................................... SUCCESS [  7.255 s]
[INFO] recreo-application ................................. SUCCESS [  3.284 s]
[INFO] recreo-adapter-web ................................. SUCCESS [ 27.578 s]
[INFO] recreo-adapter-content ............................. SUCCESS [  9.729 s]
[INFO] recreo-bootstrap ................................... SUCCESS [ 38.016 s]
[INFO] BUILD SUCCESS
[INFO] Total time:  01:26 min
```

| Module | Surefire | Failsafe |
|---|---|---|
| `recreo-domain` | 48 | - |
| `recreo-application` | 13 | - |
| `recreo-adapter-web` | 328 | - |
| `recreo-adapter-content` | **179** | - |
| `recreo-bootstrap` | 92 | 6 |

**666 tests, 0 echec, 0 erreur, 0 ignore. BUILD SUCCESS.** Ecart avec la mesure 1 : **+9 tests, +0
regle ArchUnit**, et les neuf sont les miens - une clause de production, trois morsures de jeu fautif,
un cas corrige, et **quatre mutations d'une chaine reellement servie absorbees du garde-fou de
`yanis-qa`** (section 6). Une mesure intermediaire de ce document annoncait 662 avant cette
absorption ; elle est remplacee, pas conservee a cote.

**La mesure partielle de mon lot, avec son perimetre et son `-am`.** Pendant l'ecriture, la boucle
courte a ete :

```
bash outils/mvn-jdk21.sh -B -pl adapters/adapter-content -am test \
  -Dtest=LeTexteServiPorteLeCaractereDecideJamaisSaNotationTest \
  -Dsurefire.failIfNoSpecifiedTests=false
```

Perimetre : **le seul module `adapters/adapter-content` et ses modules amont**, jamais le depot. Le
`-am` est porte parce que sans lui Maven resout `recreo-domain` et `recreo-application` depuis le
depot local de l'utilisateur, ou dort la derniere version installee - le mode de defaillance n'est
pas l'echec, c'est l'ecart muet (`CLAUDE.md`). Le verdict de la porte technique est celui de la
mesure sur reacteur complet ci-dessus, pas celui-ci.

**Morsure du garde-fou neuf prouvee sur du materiau reel, pas seulement sur ma fixture.** Une fixture
ecrite par l'auteur d'une regle ressemble a ce qu'il imagine, pas a ce que le monde ecrit - c'est mon
propre constat de s05. J'ai donc mute **un octet du contenu servi** : dans `cm1-antonymes.yaml`, le
`U+00A0` qui suit le premier guillemet ouvrant remplace par `U+0020`. Resultat de la meme commande
partielle : **1 echec sur 5**, sur `chaqueTexteServiEnProductionPorteSesCaracteres`, message de la
clause 2. Mutation annulee ; `git status` propre sur ce fichier avant tout commit, et le `git diff
--numstat` sur le paquet est vide.

---

## 4. Le garde-fou de chemins

`mvn verify` ne le lance pas ; la CI, si. Lance ici sur les fichiers suivis, dans la forme exacte du
workflow :

```
git ls-files -- . ':!.github/scripts/verifier-chemins-test.sh' | bash .github/scripts/verifier-chemins.sh
Aucun chemin de poste de travail ni nom de compte dans les fichiers verifies.
```

Code de sortie **0**, mon lot inclus. Aucun chemin absolu, aucun nom de compte, aucun emplacement de
JDK n'entre dans le depot par mes ecritures - la racine du JDK 21 de ce poste est lue dans le fichier
de toolchains de l'utilisateur par `outils/mvn-jdk21.sh`, et n'est ecrite nulle part.

---

## 5. Collision de garde-fous avec `yanis-qa`, et mon arbitrage

`sofia-sm` a route le meme obstacle a `yanis-qa` et a moi, en parallele. Deux garde-fous, meme paquet
Java, meme famille de defaut : le mien et
`AucunTexteServiNePorteUnLookAlikeDEspaceInsecableTest` (`s19-yanis`, `c9a5052`). Le detail complet
est dans l'amendement d'`ADR-0060` ; voici ce dont `sofia-sm` a besoin pour arbitrer la fusion.

**Recouvrement** : deux clauses sur trois sont identiques jusqu'au predicat - l'espace insecable dans
les guillemets francais, et la non-vacuite d'`ADR-0051` sur ces guillemets. Le corpus est le meme
(paquets du repertoire, items tires par le chargeur reel, memes cinq champs).

**La difference, mesuree et non lue.** Sa classe copiee sans modification dans mon worktree, une seule
mutation posee sur du contenu **reellement servi** - un `indice` de `cm1-antonymes.yaml` reecrit en
scalaire a apostrophes, portant la notation **loin de tout guillemet francais** :

| Mutation, seule la casse hexadecimale change | Sa regle | La mienne |
|---|---|---|
| graphie **minuscule** | **7 tests sur 7 verts** | 1 echec sur 5 |
| graphie **majuscule** | 1 echec sur 7 | 1 echec sur 5 |

Commande, perimetre nomme et `-am` porte :
`bash outils/mvn-jdk21.sh -B -pl adapters/adapter-content -am test -Dtest=...`, le seul module de
contenu et ses amonts. Mutation et copie annulees ensuite ; `git status` propre avant tout commit.

**Verdict** : son critere de notation est bati sur deux sous-chaines litterales et laisse passer la
graphie minuscule de la sequence qu'il nomme - la faute etablie en s11 avec lui-meme, un critere
enumere sur le nom d'un incident clos. Il ne s'est pas vu parce que sa clause des guillemets rattrape
le cas ou la notation touche un guillemet, ce qui etait le cas du declencheur de s19. **Les deux
regles se recouvrent, une seule doit rester, et c'est la mienne** - parce que son critere est une
categorie la ou l'autre est une liste. Sa regle ne mord rien que la mienne laisse passer.

**Ce que je ne fais pas** : je ne supprime pas son fichier. Le travail d'un autre agent ne disparait
pas dans mon lot ; `sofia-sm` arbitre la fusion. Si elle garde les deux, la frontiere doit etre
nommee dans les deux Javadoc - mais il n'y a pas de frontiere a nommer ici, il y a un doublon.

**Ce que je lui prends, parce que sa forme est meilleure que la mienne sur ce point** : ses mises a
l'epreuve par **mutation d'une chaine reellement servie**, rejouees par la CI a chaque build, la ou je
n'avais qu'une mutation manuelle attestee en prose. Quatre epreuves de plus dans mon fichier, avec
deux ameliorations : le materiau est **derive du repertoire** au lieu d'un paquet nomme, et la graphie
mutee est la minuscule - la trouvaille ci-dessus devient un test au lieu de rester une phrase.

**Sur les comptes** : il mesure 664 apres son lot, moi 666 apres le mien, tous deux depuis 657. Ni
l'un ni l'autre ne decrit l'arbre final, qui depend de son arbitrage. Les deux totaux ne s'additionnent
pas : si les deux fichiers etaient fusionnes, le total serait 673, et sept de ces tests garderaient
deux fois la meme propriete.

---

## 6. Ce que cette confirmation atteste, et ce qu'elle n'atteste pas

**Atteste** : la porte technique est verte sur reacteur complet, deux fois, avant et apres mon lot -
compilation, tests unitaires, tests d'integration Spring Boot, ArchUnit, garde-fou de chemins.

**N'atteste pas** : le Done pedagogique, qui n'appartient qu'a `nadia-prof`. Et pas davantage la
qualite du contenu servi : `ADR-0060` ajoute un automate qui juge les caracteres d'un texte, jamais ce
que ce texte dit.

**Point de convergence a verifier avec `yanis-qa`** : il produit son propre compte en parallele. Si
son total de tests differe de 657 sur l'arbre fusionne, la premiere piste est le journal Maven contre
les rapports texte (section 1, note de methode) ; s'il differe de 666, la seconde est de savoir si son
arbre porte mon lot (voir la section 5 : nos deux lots ne s'additionnent pas). Si son compte de **regles** differe de 30, la piste est l'une des trois
precisions de la section 2 - `@ArchTest` contre champ `ArchRule`, le faux depot, ou l'addition des
garde-fous de balayage aux regles. Une divergence n'est pas une erreur de l'un des deux : c'est une
trouvaille tant que les deux methodes ne sont pas ecrites cote a cote.
