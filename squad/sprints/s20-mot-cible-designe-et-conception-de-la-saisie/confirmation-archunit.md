# s20 - confirmation de la suite ArchUnit, `theo-archi`

- **Arbre** : `s20-theo` a `c48c0e6` (`round-two`), plus le lot documentaire de ce sprint. **Aucun
  fichier de code ni de test n'est modifie par ce lot** : les deux chiffres ci-dessous valent donc
  avant et apres.
- **Machine virtuelle des tests** : JDK **21.0.10**, celui de la toolchain declaree, lu dans le
  journal du build. Le `java` du `PATH` de ce poste n'est pas celui-la ; la commande passe par
  l'enveloppe du depot, qui lit la racine du JDK dans le fichier de toolchains de l'utilisateur.

## Les deux commandes et leurs resultats

**Reacteur complet - la porte technique :**

```
bash outils/mvn-jdk21.sh -B clean verify
```

**BUILD SUCCESS, 668 tests, 0 echec.** Repartition lue sur les lignes de recapitulation de surefire
et de failsafe du meme journal : 48 `domain` + 13 `application` + 328 `adapter-web` +
181 `adapter-content` + 92 `bootstrap` surefire + 6 `failsafe` = 668. Pas de `-am` a citer : le
reacteur est complet par construction.

**Suite ArchUnit seule - mesure partielle, donc `-am` et perimetre nomme :**

```
bash outils/mvn-jdk21.sh -B -pl bootstrap -am test -Dtest='com.recreo.archi.*Test' \
  -Dsurefire.failIfNoSpecifiedTests=false
```

**71 tests, 0 echec.** Perimetre : le module `bootstrap` et ses dependances (`-am`), classes du
paquet `com.recreo.archi` uniquement - **pas le depot**. Ce chiffre ne compte ni les garde-fous de
balayage d'`adapter-content` et d'`adapter-web`, ni `AssertionsWebTest`, qui portent d'autres
familles de regles.

**Compte des regles ArchUnit : 30 regles reelles, inchange par ce lot** - il n'ajoute aucune
`ArchRule`. Methode : balayage des sources `.java` du worktree, `target/` exclu, comptage des
declarations de champ `ArchRule` ; 35 lignes portent la sous-chaine `ArchRule `, dont **3** dans le
faux depot de contre-exemple de `LesGardeFousSontMisALEpreuveTest` (jamais executees comme regles)
et **2** dans ce meme fichier qui ne sont pas des declarations (une constante de chaine et un texte
de message). 35 - 3 - 2 = 30.

## Le vert qui ne prouvait rien, et qui a failli etre cite ici

**Ma premiere commande etait fausse et elle a rendu BUILD SUCCESS.** J'avais ecrit
`-Dtest='com.recreo.archi.*'` (sans le suffixe `Test`) : le motif ne designe **aucune** classe, et
`-Dsurefire.failIfNoSpecifiedTests=false` - la meme option que je porte a dessein pour les mesures
ciblees - a converti "zero test selectionne" en succes. Le journal ne contient aucune ligne
`Tests run` et le build est vert.

Je l'ecris parce que c'est exactement le mode de defaillance que ce depot sanctionne ailleurs : un
garde-fou qui n'examine aucun objet et qui passe. `ADR-0051` l'interdit **a l'interieur** d'une
regle ; rien ne l'interdit **au niveau de la commande qui la lance**. La seule protection est de
lire le nombre de tests avant de citer le vert, et non l'inverse. Aucune correction de code n'est
due : c'est une regle de conduite de mesure, et elle rejoint la clause de `CLAUDE.md` sur les
mesures partielles - un chiffre sans son perimetre, et ici un vert sans son compte, decrivent autre
chose que ce qu'on croit mesurer.

## Un fait confirme au passage

Le journal de la seconde commande montre trois classes deux fois - `LesReglesMordentTest`
(1 test puis 28), `AbstractionsCiteesTest` (4 puis 1), `ChargementDuContenuTest` (1 puis 1). Ce sont
les **deux moteurs JUnit** que `ADR-0058` nomme dans sa methode de comptage, et dont le rapport
texte de surefire ne garde qu'un. Le total de 71 est celui du journal Maven, pas celui de
`surefire-reports`.
