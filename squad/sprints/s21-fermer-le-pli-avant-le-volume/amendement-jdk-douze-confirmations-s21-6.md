# `S21-6` - amendement date de la phrase sur la machine virtuelle, dans douze `confirmation-archunit.md`

- **Auteur** : `theo-archi`. **Date** : 2026-08-27. **Branche** : `s21-theo`.
- **Ce que ce lot fait** : il **amende** douze rapports de sprints clos, il n'en reecrit aucun.
  Chaque fichier garde sa mesure d'origine, lisible, et recoit en dessous un bloc date qui dit ce
  qui est exact et ce qui ne l'est pas. **Aucun chiffre n'est remplace**, dans aucun des douze.
- **Ce que ce lot ne fait pas** : il ne corrige pas `outils/mvn-jdk21.sh` ni `outils/verifier-jdk.sh`
  - hors perimetre de s21 par la decision 4 du planning. Ce qu'il faudrait y faire est ecrit dans
  `correctif-outillage-de-mesure-s21-1.md`, section 6.
- **La faute est la mienne**, et c'est la raison pour laquelle ce lot existe : j'ai ecrit douze fois
  *"celui de la toolchain declaree"* sans jamais verifier **quelle** toolchain, sur un poste ou il y
  en a deux. Le chiffre survit a l'audit ; la methode citee, non.

## 1. Ce qui est exact, et par quoi

**Le chiffre `21.0.10` est juste.** Reverifie le 2026-08-27, dans le worktree `s21-theo`, a trois
sources issues d'un meme `bash outils/mvn-jdk21.sh -B clean verify` a la racine du reacteur :

| Ce qui est mesure | Source primaire | Resultat |
|---|---|---|
| La toolchain retenue par Maven | ligne `Found matching toolchain for type jdk` du journal, puis `Toolchain in maven-compiler-plugin` et `Toolchain in maven-surefire-plugin`, une fois par module | une racine de **JDK 21.0.10** |
| La machine virtuelle des tests | proprietes du rapport surefire d'un test execute par cette meme commande | `java.version = 21.0.10` |
| La cible du bytecode livre | octets 7 et 8 de l'en-tete d'une classe de `bootstrap/target/recreo.jar` | version majeure **65**, c'est-a-dire Java 21 |

**Ce qui est faux est le raccordement, pas le chiffre.** `outils/mvn-jdk21.sh` lit
`${HOME}/.m2/toolchains.xml`, ou `HOME` est la variable du shell POSIX ; Maven resout sa toolchain
dans `${user.home}/.m2/toolchains.xml`, ou `user.home` est le profil utilisateur de la machine
virtuelle Java. **Sur le poste de mesure ce sont deux fichiers differents, et le 2026-08-27 ils
declarent deux JDK differents** : celui du shell un **JDK 25.0.2**, celui que Maven lit un **JDK
21.0.10**. L'enveloppe pose donc `JAVA_HOME` sur le 25.0.2 - ce qui gouverne la machine virtuelle de
**Maven lui-meme** - pendant que Maven compile et execute les tests sur le 21.0.10 de **sa**
toolchain. Le script imprime `toolchain jdk 21 : 25.0.2` a chaque lancement **sans jamais comparer
les deux valeurs** : une phrase qui contient sa propre refutation, affichee a chaque build, et lue
par personne.

## 2. Le perimetre, avec sa methode - et pourquoi les douze ne recoivent pas le meme amendement

**Methode, perimetre et date** : `grep -rln 21.0.10 squad/sprints/*/confirmation-archunit.md` dans
le worktree `s21-theo`, le 2026-08-27. **Douze fichiers.** Chacun a ensuite ete lu, et la phrase qui
porte le chiffre classee selon **ce qu'elle donne comme source du JDK**. Ecrire douze fois le meme
amendement aurait ete plus rapide et aurait affirme un defaut la ou il n'y en a pas - exactement la
faute que ce lot repare.

Le discriminant est une date : `outils/mvn-jdk21.sh` **est cree le 2026-08-17**, commit `a9c182d`.
Avant lui, `JAVA_HOME` etait pose a la main sur la racine d'un JDK 21 du poste, et ce
raccordement-la, lui, est vrai.

| Groupe | Ce que la phrase donne comme source | Fichiers | Verdict |
|---|---|---|---|
| **A** | la toolchain designee par `outils/mvn-jdk21.sh` | s13 (section 5), s14, s15, s19, s20 | **Corrige** : l'enveloppe lit un autre fichier que Maven ; le 21.0.10 vient du journal du build et des rapports surefire, jamais de la ligne de l'enveloppe |
| **B** | le constat de `outils/verifier-jdk.sh`, champ `toolchains lu par Maven` | s06, s07, s08, s13 (section 0) | **Corrige** : ce champ est renseigne en regardant `${HOME}/.m2/toolchains.xml`, donc il repond sur un autre fichier que celui dont il parle. Le JDK employe ce jour-la etait bien le 21.0.10, mais par `JAVA_HOME`, pas par ce constat |
| **C** | un `JAVA_HOME` pose a la main, sans mention de toolchain | s01, s02, s03, s05 | **Non corrige, et c'est un resultat** : mesure anterieure au 2026-08-17, raccordement vrai. Une note datee dit que le fichier a ete examine et que sa phrase tient |

**Douze fichiers, treize blocs** : `s13-grands-nombres-et-chemin-de-catalogue/confirmation-archunit.md`
porte les deux defauts - le constat de `verifier-jdk.sh` en section 0, l'enveloppe en section 5 - et
recoit donc un bloc a chaque endroit, chacun a cote de la phrase qu'il corrige.

## 3. Ce que ce lot etablit en plus, et que personne n'avait nomme

**`outils/verifier-jdk.sh` porte le meme defaut que l'enveloppe, un cran plus bas.** Son champ
`toolchains lu par Maven` est calcule a partir de `${HOME}/.m2/toolchains.xml` (ligne
`fichier_defaut="${HOME}/.m2/toolchains.xml"`, lue le 2026-08-27), donc il repond sur le fichier du
shell POSIX et non sur celui que Maven lit. C'est ce champ que **quatre** rapports du groupe B
citent comme preuve. Mon avis de faisabilite du matin ne nommait que l'enveloppe ; ce quatrieme
element est trouve en instruisant ce lot, et il est route a `sofia-sm` avec le reste
(`correctif-outillage-de-mesure-s21-1.md`, section 6, point 3).

## 4. Ce que je ne fais pas

- Je n'efface aucune phrase et je ne remplace aucun chiffre. Plusieurs de ces douze rapports servent
  de base a des deltas cites ailleurs dans le depot : effacer une mesure pour la remplacer rendrait
  ces deltas inverifiables.
- Je ne remesure pas les onze mesures passees. Elles ont eu lieu a leur date, avec leur outillage ;
  ce qui est corrige, c'est ce qu'elles disent de leur propre methode.
- Je ne prononce aucun statut de couverture et aucun Done.

## 5. Ce que je route

- **`relecteur-pedagogique`** : la porte de sortie de cette story demande de verifier qu'**aucune
  reecriture silencieuse** n'a eu lieu. Le controle est mecanique :
  `git diff caad2f0..HEAD -- squad/sprints/*/confirmation-archunit.md` ne doit contenir **que des
  lignes ajoutees** dans ces douze fichiers, aucune ligne supprimee ni modifiee.
- **`sofia-sm`** : le quatrieme element de la section 3, pour la story qui portera le correctif de
  `outils/mvn-jdk21.sh`.
