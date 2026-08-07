# ADR-0021 - Interdire a un contre-exemple de garde-fou de porter un stereotype Spring, et verifier qu'aucun n'est un bean du contexte assemble

- **Statut** : Accepte
- **Date** : 2026-08-07
- **Auteur** : theo-archi
- **Sprint** : s06
- **Complete** : ADR-0003 (prouver que chaque regle ArchUnit mord, par un contre-exemple), ADR-0011
  (rendre toute erreur imprevue en HTML francais), ADR-0013 (un garde-fou derive son perimetre et se
  prouve mordant)
- **Story** : S6-12

## Contexte

`lea-dev` a trouve ceci en s05, en verifiant que `PropositionsAccentueesJouablesIT` mordait vraiment
(commit `94a1a8f`). Elle l'a documente en commentaire et route plutot que corrige, parce que c'etait
hors du mandat de son propre lot - le bon reflexe, et c'est ce qui fait que le sujet arrive ici avec
une mesure au lieu d'un soupcon
(`squad/sprints/s05-lever-le-refus-du-bon-son/review.md`, tableau des obstacles, ligne 133).

**Le mecanisme, verifie et pas suppose.** `RecreoApplication` porte
`@SpringBootApplication(scanBasePackages = "com.recreo")`. L'elargissement est legitime et motive en
commentaire : le controleur d'`adapter-web` ne vit pas sous `com.recreo.bootstrap`. Mais un scan de
composants ne connait pas la frontiere entre le classpath de production et le classpath de test - il
connait des paquets. Or les contre-exemples de garde-fou de `bootstrap` vivent, **volontairement**,
dans les paquets qu'ils imitent : `com.recreo.adapter.web.contreexemple`,
`com.recreo.domain.contreexemple`, `com.recreo.application.contreexemple`. Ils sont donc sous
`com.recreo`, et tout `@SpringBootTest` complet a les deux classpaths.

**Combien, et par quelle methode.** Six classes. Methode :
`git grep -l -E "^@(Component|Service|Controller|RestController|ControllerAdvice|RestControllerAdvice|Configuration|Repository)"`
sur `HEAD` (commit `095547c`), restreint a `bootstrap/src/test/**` et `adapters/*/src/test/**`. Ce
perimetre inclut les sources de test des cinq modules du pom agregateur ; il exclut le faux depot de
`bootstrap/src/test/resources/contreexemple/`, qui n'est jamais compile ni scanne. Les six :

| Classe | Stereotype porte | Ce qu'elle faisait reellement dans un contexte complet |
|---|---|---|
| `GestionnaireErreurBruteContreExemple` | `@RestControllerAdvice` | interceptait `IllegalStateException` **avant** `GestionnaireDesErreurs` et rendait un 500 a corps brut |
| `GestionnaireMuetContreExemple` | `@ControllerAdvice` | advice concurrente sur `PartieIndisponible` |
| `GestionnaireTypeDuJdkContreExemple` | `@ControllerAdvice` | advice concurrente sur un type du JDK |
| `ControleurQuiToucheLaSessionContreExemple` | `@Controller` | servait reellement du HTTP sur `/contre-exemple/compteur`, et touchait la session HTTP hors du porteur unique d'ADR-0019 |
| `DomainePollueContreExemple` | `@Component` | bean inutile, sans effet visible |
| `ApplicationPollueeContreExemple` | `@Service` | bean inutile, sans effet visible |

Les deux dernieres lignes sont benignes. Les quatre premieres ne le sont pas, et la premiere est une
inversion complete de la doctrine du depot : **le faux depot qui existe pour prouver qu'une regle
mord decidait de ce que le produit sous test repondait.** Un test d'integration qui verifiait le
chemin d'erreur d'ADR-0011 pouvait etre vert parce que le contre-exemple d'ADR-0011 avait pris la
place du gestionnaire reel, ou rouge sans que le produit ait le moindre defaut. Dans les deux cas, la
mesure ne portait plus sur le produit.

Le detail qui rend ce defaut particulierement mauvais : un `@ExceptionHandler` qui nomme un type
**plus specifique** gagne sur celui qui nomme son parent, et ce n'est pas un ordre qu'on lit dans un
fichier. La presence de la bonne advice ne suffisait donc pas ; il fallait l'absence de toute autre.
C'est la meme lecon qu'ADR-0018 avait deja payee - un `@ExceptionHandler` attrape un type et tout son
sous-arbre, nommer deux types n'est pas nommer deux situations.

### Ce que ce defaut dit de la doctrine d'ADR-0003

ADR-0003 demande a chaque regle un contre-exemple qui doit la faire echouer. Il n'a jamais dit ce
qu'un contre-exemple n'a **pas** le droit de faire. Six classes ecrites pour etre refusees par un
automate se sont mises a s'executer dans le produit, et pendant deux sprints personne ne l'a vu -
parce que la seule chose qu'on leur demandait etait de faire rougir leur regle, et qu'elles le
faisaient tres bien. **La faute n'est pas dans le scan, elle est dans le silence d'ADR-0003 sur ce
sujet.** C'est ce silence que cet ADR remplit.

## Decision

**Un contre-exemple de garde-fou n'est jamais un bean de l'application. Il porte la faute qu'il
imite dans sa structure, jamais dans un stereotype Spring.**

1. **Aucune classe des sources de test d'un module ou demarre un contexte Spring complet ne porte de
   stereotype Spring**, direct ou obtenu par meta-annotation. `@TestComponent` - donc
   `@TestConfiguration`, qui en herite - est hors de cette interdiction : le `TypeExcludeFilter` que
   Spring Boot pose lui-meme retire deja ces classes du scan de l'application. Ce n'est pas une
   tolerance, c'est la frontiere exacte de ce que le scan voit.

2. **Un contre-exemple exprime sa faute par la structure que sa regle examine, pas par une
   annotation de commodite.** Les six classes ont ete reecrites sur ce principe, et **aucune regle
   n'a ete affaiblie** :
   - les trois advices : la regle d'ADR-0011 porte sur la methode annotee `@ExceptionHandler` et sur
     son type de retour, jamais sur l'annotation de classe. Retirer `@ControllerAdvice` ne change
     rien a ce qu'elle voit ;
   - le controleur de session : la regle d'ADR-0019 porte sur la dependance a `HttpSession`, pas sur
     le stereotype. L'annotation de route reste, elle dit ce que la classe imite ;
   - les deux classes polluees : la pollution est desormais un **appel a un utilitaire de Spring**
     (`StringUtils.hasText`) au lieu d'une annotation. La regle d'ADR-0002 refuse toute dependance
     hors du JDK et du domaine : elle mord identiquement sur les deux formes. Cette forme-ci est
     d'ailleurs plus proche de la dette reelle - personne ne pose un stereotype sur une entite de
     domaine, tout le monde est tente d'y appeler un utilitaire de chaine.

3. **La portee du scan de `RecreoApplication` n'est pas modifiee.** Motif au paragraphe suivant.

### Pourquoi ni resserrer le scan, ni deplacer les contre-exemples

La story me laissait explicitement le choix entre deux voies. J'en ai retenu une troisieme, et je
dois dire pourquoi les deux autres sont moins bonnes.

**Resserrer le scan** (enumerer les paquets de production au lieu de `com.recreo`) ferme le symptome
et cree un perimetre enumere - la faute exacte qu'ADR-0013 interdit. Chaque paquet neuf de production
devrait etre ajoute a la main, et l'oubli produirait un bean manquant au demarrage : un defaut
d'execution, pas un rouge de CI. J'echangerais un defaut visible en test contre un defaut visible en
production.

**Deplacer les contre-exemples hors des paquets scannes** (un `com.recreo.contreexemple` a plat, ou
un paquet hors `com.recreo`) coute plus cher qu'il n'y parait : plusieurs regles derivent leur
perimetre du paquet qu'elles surveillent, et un contre-exemple qui doit prouver qu'une regle mord sur
`com.recreo.domain..` doit **etre** dans `com.recreo.domain..`. Le deplacer rendrait la moitie des
contre-exemples inoperants. C'est leur emplacement qui fait leur valeur.

**La voie retenue attaque la cause et non le symptome** : sans stereotype, aucun scan ne peut
enregistrer la classe, quel que soit le perimetre du scan, aujourd'hui comme apres un remaniement de
paquets. Elle a de plus l'avantage d'etre verifiable par ArchUnit, ce que les deux autres ne sont pas.

## Regle executable ArchUnit

Fichier : `bootstrap/src/test/java/com/recreo/archi/ScanDeComposantsTest.java`

```java
    static final ArchRule aucuneClasseDeTestSurLeClasspathDeLApplicationNePorteDeStereotype = noClasses()
            .that().resideInAPackage("com.recreo..")
            .and().areNotMetaAnnotatedWith(COMPOSANT_DE_TEST)
            .should().beMetaAnnotatedWith(COMPOSANT)
            .as("ADR-0021 : aucune classe des sources de test de ce module n'est candidate au scan de "
                    + "composants de l'application");
```

`COMPOSANT` vaut `org.springframework.stereotype.Component` et `COMPOSANT_DE_TEST` vaut
`org.springframework.boot.test.context.TestComponent`. Le perimetre est l'import derive du classpath
de test par `ImportOption.OnlyIncludeTests`, jamais une liste de classes.

**Ce que cette regle interdit reellement a quelqu'un qui essaierait de mal faire** : elle attrape la
cause, pas les six occurrences. `beMetaAnnotatedWith(Component)` couvre `@Component`, `@Service`,
`@Repository`, `@Controller`, `@RestController`, `@Configuration`, `@ControllerAdvice`,
`@RestControllerAdvice` - toutes meta-annotees `@Component` - et couvrira le stereotype que
quelqu'un ecrira demain, parce que tout stereotype Spring l'est par construction. Enumerer les huit
annotations aurait ete un perimetre enumere, donc faux par omission des le neuvieme.

**Non-vacuite.** `aucuneClasseDeTestNeSeraEnregistreeParLeScanDeComposants` verifie d'abord que
l'import contient deux temoins - `GestionnaireErreurBruteContreExemple` et `CatalogueDeTest` - avant
de lancer la regle. Un `ImportOption` qui cesserait de reconnaitre le repertoire des classes de test
rendrait sinon cette regle verte sur un ensemble vide.

### La preuve que la regle mord

**Sur du materiau reel, pas sur une fixture de ma main.** C'est la lecon de s05 que je m'applique
ici : en s05 la detection d'un `!important` cherchait une sous-chaine qu'un nom de variable de la
bibliotheque contenait, et la regle passait sa propre epreuve en echouant dans le monde. Je n'ecris
donc aucune classe fautive pour eprouver celle-ci. Je lui donne a juger **les classes de production
de ce depot**, qui portent de vrais stereotypes - un direct (`@Component` sur le filtre) et trois
obtenus par meta-annotation (`@Controller`, `@ControllerAdvice`, `@Configuration`) - et j'exige le
rouge, en nommant les quatre :

```java
    @Test
    void unStereotypeSpringSurUneClasseDuPerimetreEstRefuse() {
        assertThatThrownBy(() ->
                aucuneClasseDeTestSurLeClasspathDeLApplicationNePorteDeStereotype.check(production()))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("com.recreo.adapter.web.PartieController")
                .hasMessageContaining("com.recreo.adapter.web.GestionnaireDesErreurs")
                .hasMessageContaining("com.recreo.adapter.web.CorpsFormulaireEnUtf8ToleranteFilter")
                .hasMessageContaining("com.recreo.bootstrap.config.PartieConfiguration");
    }
```

Une regle incapable de voir ces quatre-la ne verrait pas non plus un stereotype pose sur une classe
de test. L'epreuve porte donc sur le **critere**, sur des classes que je n'ai pas ecrites pour elle.

**Mutation reelle du code, executee avant de commiter.** `@RestControllerAdvice` remis sur
`GestionnaireErreurBruteContreExemple`, c'est-a-dire l'etat du depot a `HEAD` : resultats en fin de
section suivante, avec les deux tests qui rougissent et la mesure de l'angle mort.

## Ce qu'ArchUnit ne peut pas garder, et par quoi c'est garde

**La regle ci-dessus garde la cause que la structure porte ; elle ne garde pas la propriete
elle-meme.** ArchUnit lit des classes, pas un contexte assemble. Un contre-exemple declare par une
methode `@Bean` d'une configuration de test, ou tire par un `@Import`, n'a aucun stereotype : il
passerait la regle et serait pourtant un bean reel. La propriete "aucun contre-exemple n'est un bean
de l'application" ne porte pas sur une dependance, elle porte sur le **contenu du contexte**.

Elle est donc gardee sur le seul objet qui la porte, un contexte Spring complet - celui-la meme que
le defaut de s05 avait pollue.

Fichier : `bootstrap/src/test/java/com/recreo/bootstrap/AucunContreExempleNEstUnBeanDeLApplicationTest.java`

Trois tests, un `@SpringBootTest` :

1. `desContreExemplesSontBienSurLeClasspathDeCeContexte` - non-vacuite. Sans lui, les deux suivants
   passeraient sur un ensemble vide le jour ou les contre-exemples demenageraient hors du paquet
   scanne, **en annoncant garder une propriete qu'ils ne garderaient plus**. C'est le vert silencieux
   que ce depot a deja paye deux fois.
2. `aucunBeanDuContexteNEstUnContreExemple` - la propriete. Les types de tous les beans du contexte
   sont confrontes a la convention de nom des faux depots (suffixe `ContreExemple`), celle qu'ADR-0003
   a etablie et que `LesReglesMordentTest.aucunContreExempleEnProduction` garde deja. **La population
   est derivee du classpath de test, aucune liste de classes n'est ecrite** : un contre-exemple neuf
   entre automatiquement dans le controle.
3. `laSeuleAdviceDErreurDuContexteEstCelleDeLApplication` - la forme precise qu'avait pris le defaut,
   et le second critere de S6-12. Elle est ecrite **par egalite et non par inclusion** :
   `hasSize(1)` puis le type attendu. Verifier que `GestionnaireDesErreurs` est present aurait ete
   vrai pendant tout le sprint 5, defaut compris, puisqu'il etait bien la - simplement precede par un
   autre. C'est la lecon du perimetre enumere : nommer ce qu'on veut ne dit rien de ce qu'on ne veut
   pas.

Les deux garde-fous ne se recouvrent pas, et c'est pourquoi il en faut deux : le premier voit une
cause qu'aucun contexte n'a besoin de demarrer pour reveler, le second voit une propriete qu'aucune
analyse statique n'atteint.

### Preuve de morsure, mesuree

Mutation : `@RestControllerAdvice` remis sur `GestionnaireErreurBruteContreExemple` et son import
retabli - l'etat exact du depot a `HEAD` (`095547c`). Methode : mutation appliquee au depot,
`mvn -B clean verify` sur les cinq modules du pom agregateur, depot restaure ensuite. Le reacteur
complet est ici une exigence de methode, pas une commodite : voir la remarque qui suit le tableau.

| Mesure | Resultat |
|---|---|
| `ScanDeComposantsTest.aucuneClasseDeTestNeSeraEnregistreeParLeScanDeComposants` | **rouge** : `Class <...GestionnaireErreurBruteContreExemple> is meta-annotated with @Component`, 1 violation |
| `AucunContreExempleNEstUnBeanDeLApplicationTest.aucunBeanDuContexteNEstUnContreExemple` | **rouge** : la classe est bien un bean du contexte assemble |
| `AucunContreExempleNEstUnBeanDeLApplicationTest.laSeuleAdviceDErreurDuContexteEstCelleDeLApplication` | **rouge** : deux advices au lieu d'une |
| `desContreExemplesSontBienSurLeClasspathDeCeContexte` (non-vacuite) | **vert**, comme il doit l'etre : le perimetre n'est pas vide |
| `mvn -B -o clean verify` avec la mutation **et les deux garde-fous neufs retires de l'arbre** | **BUILD SUCCESS**, zero test rouge |

La derniere ligne est la mesure de l'angle mort, et c'est la seule qui compte vraiment : le defaut
que `lea-dev` a trouve a la main traversait un `mvn verify` vert. Il l'a fait pendant deux sprints.

**Une fragilite de `unStereotypeSpringSurUneClasseDuPerimetreEstRefuse`, trouvee en mesurant et non
en relisant.** Ma premiere mesure a lance `mvn -B -o -pl bootstrap test` au lieu du reacteur complet.
Ce test-la est alors devenu **rouge pour une raison etrangere a la mutation** : un module isole
resout ses modules freres depuis le depot Maven local, et l'artefact `recreo-adapter-web` qui y est
installe est anterieur a s05. Il ne contient pas `CorpsFormulaireEnUtf8ToleranteFilter`, l'une des
quatre classes que l'epreuve nomme, alors qu'il contient les trois autres, plus anciennes. Le message
d'echec disait `to contain "com.recreo.adapter.web.CorpsFormulaireEnUtf8ToleranteFilter" but did not`.

Je le consigne au lieu de le lisser, parce que c'est une propriete reelle de ce garde-fou : **son
perimetre est le classpath, donc il depend de la facon dont le build est invoque.** Le mode de
defaillance est un **faux rouge**, jamais un faux vert - la regle ne peut pas devenir aveugle par ce
chemin, elle peut seulement accuser a tort. C'est le bon sens de l'erreur, et c'est pourquoi je ne
change pas l'epreuve : nommer quatre classes est ce qui prouve qu'elle voit un stereotype **direct**
(`@Component` sur le filtre) autant qu'un stereotype obtenu par meta-annotation. Ce qu'il faut en
retenir, et qui est desormais ecrit ici : **ce garde-fou se mesure sur le reacteur complet.** Un
`-pl` isole n'invalide pas le depot, il invalide la mesure.

## Consequences

- **Un contre-exemple est desormais moins ressemblant a la faute qu'il imite**, et il faut le dire
  franchement plutot que de le presenter comme un progres pur. Un vrai `@ControllerAdvice` fautif
  porterait l'annotation ; le contre-exemple ne la porte plus. Ce que la regle examine est intact -
  la methode `@ExceptionHandler` et son type de retour - mais un lecteur pourrait croire, en ouvrant
  la classe, qu'elle n'imite plus rien. C'est pourquoi chaque classe porte desormais, en Javadoc, la
  phrase qui dit ce que sa regle regarde vraiment. **Le risque residuel est qu'une regle future soit
  ecrite sur l'annotation de classe et se croie eprouvee par un contre-exemple qui ne la porte
  plus** : elle serait alors verte sans rien garder. C'est la dette explicite de cette decision, et
  la parade est `LesReglesMordentTest`, qui exige de chaque regle un `assertThatThrownBy` reel.
- **Deux garde-fous de plus, dont un qui demarre un contexte Spring complet.** Cout mesure : de
  l'ordre de trois secondes sur `bootstrap`.
- **La route `/contre-exemple/compteur` n'existe plus dans les tests d'integration.** Elle n'y avait
  rien a faire ; aucun test ne l'appelait.
- **La regle est portee par un module et un seul.** Voir la limite ci-dessous.

## Ce que cette regle ne couvre pas

- **Les sources de test des autres modules.** Le perimetre est le classpath de test de `bootstrap`,
  seul module ou un `@SpringBootTest` complet demarre. Les sources de test de `domain`,
  `application`, `adapter-web` et `adapter-content` ne sont pas sur ce classpath et echappent a la
  regle. C'est la meme limite que `LesGardeFousSontMisALEpreuveTest` nomme pour lui-meme. Ce qui les
  couvre partiellement : `@WebMvcTest` n'enregistre que la couche web et son propre filtre exclut
  tout ce qui n'est ni controleur, ni advice, ni convertisseur. Une classe de test d'`adapter-web`
  annotee `@ControllerAdvice` y echapperait. **Il n'en existe aucune a ce jour**, verifie par la
  commande `git grep` citee au contexte, et je ne duplique pas la regle dans quatre modules pour un
  cas qui n'existe pas - ce serait quatre copies a maintenir pour un usage anticipe, pas constate.
  Ce qui me ferait changer d'avis : la premiere classe de test annotee d'un stereotype hors de
  `bootstrap`.
- **Un bean declare par `@Bean` dans une `@TestConfiguration`** n'est pas vu par la regle ArchUnit.
  Il est vu par `AucunContreExempleNEstUnBeanDeLApplicationTest`, mais seulement si son type suit la
  convention de nom `*ContreExemple`. Un faux depot nomme autrement echapperait aux deux. La
  convention de nom est deja gardee par `LesReglesMordentTest.aucunContreExempleEnProduction` ; je
  m'appuie sur elle plutot que d'en inventer une seconde.

## Alternatives considerees

1. **Resserrer `scanBasePackages`.** Rejetee, motif au corps de la decision : perimetre enumere,
   dont l'oubli devient un defaut d'execution au lieu d'un rouge de CI.
2. **Deplacer les contre-exemples hors des paquets scannes.** Rejetee : leur emplacement fait leur
   valeur, plusieurs regles derivent leur perimetre du paquet surveille.
3. **Ajouter un `TypeExcludeFilter` maison qui retire les classes a suffixe `ContreExemple`.**
   Rejetee, et c'etait la plus tentante : elle laisse le stereotype en place - donc les
   contre-exemples restent parfaitement ressemblants - et retire les classes du scan. Deux motifs de
   rejet. D'une part elle fait entrer dans le **code de production** un type dont la seule raison
   d'etre est de composer avec des classes de test, ce qui est exactement le genre de couplage que ce
   depot refuse ailleurs. D'autre part elle ne protege que du scan de composants : une classe
   annotee `@ControllerAdvice` reste candidate a d'autres mecanismes de detection, et la propriete
   deviendrait vraie pour une raison etroite plutot que par construction. Ce qui me ferait revenir
   dessus : si la consequence nommee plus haut - un contre-exemple moins ressemblant - produisait un
   jour une regle verte a tort, ce filtre redeviendrait le meilleur choix.
4. **Ne garder que le test de contexte, sans regle ArchUnit.** Rejetee : le test de contexte voit la
   consequence, la regle voit la cause. Sans la regle, un stereotype pose sur une classe de test d'un
   module sans `@SpringBootTest` ne serait vu par rien.
5. **Ne garder que la regle ArchUnit, sans test de contexte.** Rejetee, motif a la section "ce
   qu'ArchUnit ne peut pas garder" : un `@Bean` de configuration de test n'a pas de stereotype.
