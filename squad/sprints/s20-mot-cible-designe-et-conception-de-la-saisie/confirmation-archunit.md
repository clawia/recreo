# s20 - confirmation de la suite ArchUnit, `theo-archi`

**Ce fichier porte deux mesures, pas une.** La premiere, du **2026-08-25**, valait sur mon lot
documentaire seul ; elle affirmait qu'aucun fichier de code ni de test n'etait modifie, ce qui etait
vrai **de mon lot** et a cesse de l'etre **du sprint** des la fusion de `S20-1`. Je ne l'efface pas -
elle est la base d'un delta cite ailleurs dans le depot, et effacer une mesure pour la remplacer
rendrait ce delta inverifiable. Je la date et je place la mesure de cloture a cote, avec l'ecart.

**Machine virtuelle des tests, pour les deux mesures** : JDK **21.0.10**, celui de la toolchain
declaree, lu dans le journal du build. Le `java` du `PATH` de ce poste n'est pas celui-la ; la
commande passe par l'enveloppe du depot, qui lit la racine du JDK dans `~/.m2/toolchains.xml`.

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

## Mesure 1 - 2026-08-25, arbre `s20-theo` a `c48c0e6`

Prise avant la fusion de `S20-1`, sur mon lot documentaire (`ADR-0062`), qui ne touchait ni code ni
test.

- `bash outils/mvn-jdk21.sh -B clean verify` a la racine - **BUILD SUCCESS, 668 tests, 0 echec.**
  Methode : somme des blocs `Results:` du journal Maven, comptage apres `clean`, reacteur complet
  (48 `domain` + 13 `application` + 328 `adapter-web` + 181 `adapter-content` + 92 `bootstrap`
  surefire + 6 `failsafe`). Pas de `-am` a citer : le reacteur est complet par construction.
- Suite ArchUnit ciblee, `-pl bootstrap -am`, paquet `com.recreo.archi` seul - **71 tests, 0 echec.**
- **30 regles `ArchRule` reelles.**

## Mesure 2 - 2026-08-27, arbre de cloture `round-two` a `9ccbb1d`

Arbre propre, reacteur libere par `yanis-qa` apres son rapport. Contient tout ce que le sprint a
fusionne depuis `c48c0e6` : le contrat de mot cible d'`ADR-0062` (`marc-dev`), la forme visuelle
`.mot-designe` (`ines-design`), `cm1-reperes` et sa correction (`lea-dev`), les editions de
`catalogue.yaml` (`DEC-0048` / `DEC-0049`) et la correction de
`TexteAccentueExposeParLePortEntrantTest`.

### Reacteur complet - la porte technique

```
bash outils/mvn-jdk21.sh -B clean verify
```

**BUILD SUCCESS, 694 tests, 0 echec, le 2026-08-27 sur `9ccbb1d`.** Methode : somme des six blocs
`[INFO] Results:` du journal Maven, comptage apres `clean`, six modules du reacteur complet, aucun
`-am` a citer puisque la mesure n'est pas partielle.

| Module | Tests |
|---|---|
| `recreo-domain` (surefire) | 49 |
| `recreo-application` (surefire) | 13 |
| `recreo-adapter-web` (surefire) | 330 |
| `recreo-adapter-content` (surefire) | 198 |
| `recreo-bootstrap` (surefire) | 98 |
| `recreo-bootstrap` (failsafe) | 6 |
| **Total** | **694** |

**694 est reconstruit par mon execution, pas recopie.** `yanis-qa` annonce le meme chiffre sur
`77dd467` dans `rapport-qualite.md` section 1 ; l'amendement s17 exige de verifier un chiffre repris
contre sa source primaire avant recopie, et la source primaire d'un compte de tests est un journal
de build, pas un autre document. Les deux mesures concordent module par module. Elles portent sur
deux commits differents - `77dd467` pour la sienne, `9ccbb1d` pour la mienne - et `9ccbb1d`
n'ajoute que son rapport, donc aucun test : la concordance est attendue, et c'est parce qu'elle
etait attendue qu'elle valait d'etre verifiee plutot que supposee.

### Suite ArchUnit seule - mesure partielle, donc `-am` et perimetre nomme

```
bash outils/mvn-jdk21.sh -B -pl bootstrap -am test -Dtest='com.recreo.archi.*Test' \
  -Dsurefire.failIfNoSpecifiedTests=false
```

**Bloc `Results:` du journal : `Tests run: 71, Failures: 0, Errors: 0, Skipped: 0`. BUILD SUCCESS.**
Le 2026-08-27, sur `9ccbb1d`. Perimetre : le module `bootstrap` et ses dependances (`-am`), classes
du paquet `com.recreo.archi` uniquement - **pas le depot**. Ce chiffre ne compte ni les garde-fous de
balayage d'`adapter-content` et d'`adapter-web`, ni `AssertionsWebTest`, qui portent d'autres
familles de regles.

Je cite le bloc `Results:` et le compte **avant** le verdict, et non l'inverse : c'est ce que la
section 6 du rapport de `yanis-qa` instruit a partir de mon propre faux vert de ce sprint (voir plus
bas).

### Compte des regles ArchUnit

**30 regles `ArchRule` reelles, le 2026-08-27 sur `9ccbb1d`, reparties sur 12 fichiers** (11 dans
`bootstrap`, 1 dans `adapter-web`). Trois methodes convergent, et je les donne toutes les trois
parce qu'un compte a une seule methode n'a pas de temoin :

1. **Declarations de champ, contre-exemples exclus** : balayage des `.java` du worktree, `target/` et
   tout chemin contenant un segment `contreexemple` exclus, motif `static final ArchRule` ou
   `static ArchRule` - **30**.
2. **Toutes les occurrences, defalquees** : 35 lignes portent la sous-chaine `ArchRule `, dont **3**
   dans le faux depot de contre-exemple de `LesGardeFousSontMisALEpreuveTest` (jamais executees comme
   regles) et **2** dans ce meme fichier qui ne sont pas des declarations - une constante de chaine
   et un texte de message. 35 - 3 - 2 = **30**.
3. **Par les annotations, avec sa correction connue** : **29** `@ArchTest`, plus **1** regle executee
   par un `.check()` explicite et donc non annotee -
   `aucuneClasseDeTestSurLeClasspathDeLApplicationNePorteDeStereotype` dans `ScanDeComposantsTest`,
   passee deux fois au `.check()`, sur les sources et sur la production. 29 + 1 = **30**. C'est
   exactement le piege de sous-estimation d'une unite que `ADR-0058` nomme dans sa methode ; il est
   verifie ici, pas suppose.

**Perimetre du balayage** : le worktree `round-two` seul. Les sept worktrees d'agents de ce sprint
vivent en repertoires freres, hors de l'arbre balaye ; c'etait l'une des trois causes de divergence
du compte errone de s05 (29 annoncees sans methode, 25 reelles), et elle ne joue pas ici.

## L'ecart entre les deux mesures

| | 2026-08-25, `c48c0e6` | 2026-08-27, `9ccbb1d` | Ecart |
|---|---|---|---|
| Tests, reacteur complet | 668 | 694 | **+26** |
| Suite ArchUnit ciblee (`com.recreo.archi`) | 71 | 71 | **0** |
| Regles `ArchRule` reelles | 30 | 30 | **0** |
| Verdict | BUILD SUCCESS | BUILD SUCCESS | inchange |

Repartition du **+26**, verifiee ligne a ligne contre les deux tableaux : +1 `domain` (48 -> 49),
0 `application` (13 -> 13), +2 `adapter-web` (328 -> 330), +17 `adapter-content` (181 -> 198),
+6 `bootstrap` surefire (92 -> 98), 0 `failsafe` (6 -> 6). Somme : +26.

**Ce que l'ecart dit, et c'est le point qui m'interesse comme architecte** : le sprint ajoute 26
tests et **zero regle ArchUnit**. Ce n'est pas un manque. `ADR-0062` annonce explicitement qu'il
n'ajoute aucune `ArchRule` et que ses deux clauses sont des garde-fous de balayage ; l'ecart mesure
confirme cette annonce au lieu de la croire. Un ADR qui aurait promis une regle ArchUnit et laisse
le compte a 30 serait, lui, un ADR incomplet - et ce tableau est ce qui permettrait de le voir.

**Les deux clauses d'`ADR-0062` sont vertes sur l'arbre de cloture**, elles qui etaient rouges a
dessein le 2026-08-25 : `LaPartDesigneeDUnItemServiEstMarqueeALEcranTest` (clause 1, `bootstrap`,
6 tests) et `UnePartDesigneeApparaitUneSeuleFoisDansSonEnonceTest` (clause 2, `adapter-content`,
5 tests), lues dans le journal du `clean verify` du 2026-08-27. Le cliquet d'`ADR-0050` qu'`ADR-0062`
annoncait faire rougir est referme :
`AucunItemNeRepeteLEcranDUnAutreItemDuMemePaquetTest` passe a 10 tests, 0 echec.

## `ADR-0062` est-il complet au sens de `CLAUDE.md` ?

**Oui, et son garde-fou n'est pas une regle ArchUnit a bon droit.**

`CLAUDE.md` exige qu'un ADR porte sa regle executable ecrite dans le meme geste ; ma propre consigne
ajoute que si la decision n'est pas encodable en ArchUnit, l'ADR ecrit **pourquoi** et **quel autre
mecanisme automatique** la garde. `ADR-0062` fait les deux, et le motif tient a la nature des objets
juges, pas a une facilite : ArchUnit modelise des types Java et leurs relations. Or les objets de
cette decision sont **le document HTML servi** par le gabarit et **le contenu rendu par le chargeur
reel**. Aucune relation entre types Java ne dit si un `<span>` porte la bonne marque autour du bon
mot. Une regle ArchUnit ecrite ici aurait juge l'existence d'une classe ou d'un paquet - c'est-a-dire
autre chose que la decision, donc une regle decorative au sens de mon propre reflexe 2. C'est le
quatrieme motif distinct de cette famille dans le depot, apres `ADR-0059`, `ADR-0060` et `ADR-0061`.

Ce qui rend ces deux clauses des garde-fous et non des souhaits, verifie sur l'arbre et pas lu dans
l'ADR :

- **Elles existent, et elles s'executent dans la porte technique.** Les deux fichiers annonces sont
  presents et leurs 11 tests sont dans les blocs `Results:` du `clean verify` du 2026-08-27.
- **Elles mordent dans les deux sens.** La seconde moitie de la clause 1 - marque **absente** partout
  ou l'item ne declare rien - est celle qui ferme le contournement par deduction des guillemets :
  sans elle la clause constaterait que ce qui est marque est marque, et ne dirait rien de ce qui l'est
  a tort. `yanis-qa` a passe une mutation de plus, sur du contenu de production et non sur une fixture,
  contre la clause 2 (`rapport-qualite.md`, section 5.1) : `cp-nature-mot.yaml` edite pour que la
  part designee apparaisse deux fois - **rouge**, puis vert apres restauration verifiee.
- **Elles ne peuvent pas passer a vide.** Clause de non-vacuite d'`ADR-0051` : zero item declarant
  une part designee est un echec, pas un vert.
- **Leur perimetre est derive, pas enumere.** Aucun nom de paquet n'entre dans les deux fichiers -
  la lecon d'`ADR-0061`, appliquee la ou la tentation existait.

**Une derive documentaire a corriger, et elle est de la meme famille que celle de ce fichier.** Le
statut d'`ADR-0062` et sa ligne d'index disent tous deux *"sa regle est ecrite ici et elle est rouge
sur l'arbre du jour"*. C'etait vrai le 2026-08-25 et c'est faux depuis la fusion de `S20-1`. Une
phrase qui decrit l'etat de l'arbre se perime avec l'arbre : je l'amende avec sa date dans les deux
endroits plutot que de la reecrire, meme geste qu'ici.

## Le vert qui ne prouvait rien - le fait, et ou il est instruit

**Ma premiere commande de mesure ciblee, le 2026-08-25, etait fausse et elle a rendu BUILD SUCCESS.**
J'avais ecrit `-Dtest='com.recreo.archi.*'` sans le suffixe `Test` : le motif ne designe **aucune**
classe reelle, et `-Dsurefire.failIfNoSpecifiedTests=false` - la meme option qu'une mesure ciblee
legitime doit porter - a converti "zero test selectionne" en succes. Le journal ne contient alors
aucune ligne `Tests run:` ni aucun bloc `Results:`, et le build est vert.

`yanis-qa` l'a **reproduit independamment** le 2026-08-27 (`rapport-qualite.md`, section 6) au lieu
de me croire sur parole, dans les deux sens : la commande fautive rend BUILD SUCCESS sans bloc
`Results:`, la commande corrigee rend 71 tests. C'est ainsi qu'un fait entre au depot.

Ce que j'en tiens dans ma conduite de mesure, et que ce fichier applique : **le compte de tests se
lit dans le bloc `Results:` avant que le vert soit cite, jamais l'inverse.** Un `BUILD SUCCESS` sans
bloc `Results:` visible n'atteste rien. Aucune correction de code n'est due - `ADR-0051` interdit la
vacuite **a l'interieur** d'une regle, aucun plugin Maven n'arrete son equivalent **au niveau de la
commande qui la lance**. C'est la meme famille que la clause de `CLAUDE.md` sur les mesures
partielles sans `-am` : le mode de defaillance n'est pas l'echec, c'est l'ecart muet.

## Un fait confirme au passage

Le journal de la commande ciblee montre trois classes deux fois - `AbstractionsCiteesTest`,
`ChargementDuContenuTest`, `LesReglesMordentTest` - sur les 13 classes du paquet `com.recreo.archi`.
Ce sont les **deux moteurs JUnit** que `ADR-0058` nomme dans sa methode de comptage, et dont le
rapport texte de surefire ne garde qu'un. Le total de 71 est celui du bloc `Results:` du journal
Maven, pas celui de `surefire-reports`. Fait deja constate le 2026-08-25, inchange le 2026-08-27.

## Decisions prises

Trois, toutes de portee documentaire et de conduite de mesure ; **aucune decision d'architecture
n'est prise ici, et aucun ADR neuf n'est ouvert** - ce lot ne modifie aucun fichier de code ni de
test.

1. **Une mesure datee ne se remplace pas, elle se date et se double.** Ce fichier portait une mesure
   vraie de mon lot et fausse du sprint. La corriger en l'ecrasant aurait rendu inverifiable le delta
   `+26` que `rapport-qualite.md` cite en s'appuyant sur elle. Les deux mesures cohabitent avec leur
   date, leur commit et leur ecart. **Ce que cela coute** : ce fichier grossit a chaque cloture qui
   le rejoue. **Ce qui me ferait changer d'avis** : le jour ou un tel fichier porte plus de trois
   mesures, elles descendent dans un tableau unique et le texte ne garde que la derniere.
2. **Une phrase qui decrit l'etat de l'arbre porte sa date, dans l'ADR comme dans l'index.**
   `ADR-0062` et sa ligne d'index affirmaient une regle rouge ; elle est verte depuis la fusion de
   `S20-1`. Amendes tous deux avec la date, pas reecrits. C'est la meme famille de defaut que la
   reference morte de `CLAUDE.md` corrigee en s14 : un document que tous les agents lisent et qui
   decrit un arbre qui n'existe plus.
3. **Le compte de tests se lit avant que le vert soit cite.** Regle de conduite, pas de code : aucun
   plugin ne l'arrete, donc elle vit ici et dans `rapport-qualite.md` section 6, et elle s'applique a
   toute mesure ciblee citee dans un fichier commite.

**Note sur l'arbre que ce lot laisse derriere lui.** Les chiffres ci-dessus sont mesures sur
`9ccbb1d`. Le present lot n'ajoute que du document - ce fichier, le statut d'`ADR-0062` et sa ligne
d'index - mais deux garde-fous lisent des fichiers du depot et non du seul code Java. J'ai donc
rejoue `bash outils/mvn-jdk21.sh -B clean verify` a la racine **apres commit**, le 2026-08-27 :
**BUILD SUCCESS, 694 tests, 0 echec**, meme repartition module par module. S'y ajoutent les deux
garde-fous de redaction que porte la CI et non Maven, lances comme elle les lance :
`verifier-chemins-test.sh` (le garde-fou mord) puis `verifier-chemins.sh` sur `git ls-files` (aucun
chemin de poste ni nom de compte), et `ConcordanceDesTagsDAgentTest` sur mon propre message de
commit - 1 test, 0 echec, perimetre `bootstrap` et ses dependances (`-am`), classe unique, **pas le
depot**. Citer un vert mesure avant son propre commit serait la meme faute que celle que ce fichier
corrige.

**Ce que je ne prononce pas** : le Done pedagogique, qui n'appartient qu'a `nadia-prof`. Aucun
fichier de `squad/conformite/` n'est touche par ce lot.
