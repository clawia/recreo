# ADR-0011 - Rendre toute erreur imprevue en HTML francais, jamais un corps technique brut

- **Statut** : Accepte
- **Date** : 2026-08-05
- **Auteur** : theo-archi
- **Sprint** : s01

## Contexte

Motif de refus 3 de `squad/conformite/FICHE-bandit-manchot-additions-cp.md` (story S1-4 du
backlog) : quand la session HTTP ne reconnait plus la partie (session perdue, redemarrage),
`nadia-prof` a reproduit un coup joue sans partie en cours et recu un corps HTTP 500 brut,
`{"timestamp":"...","status":500,"error":"Internal Server Error","path":"..."}`, insere dans la
zone de jeu par htmx. Lu dans le code : `PartieController.sessionEnCours` leve une
`IllegalStateException` quand `httpSession` ne porte pas l'attribut de jeu attendu, et
`PartieController.jeuOuEchec` leve une `NoSuchElementException` sur un identifiant de jeu inconnu.
Aucune des deux n'est interceptee : Spring Boot les laisse remonter jusqu'a son mecanisme de page
d'erreur par defaut, qui rend du JSON pour cette requete htmx.

ADR-0008 dit deja que le serveur detient l'etat et le verdict, et que chaque coup de l'enfant
recoit un fragment Thymeleaf rendu serveur. Cet ADR ne dit rien du chemin d'erreur : le principe
"rendu serveur, jamais de JSON cote vues" (`CLAUDE.md`, section Stack) s'applique de fait a tout ce
qu'`adapter-web` renvoie, mais rien ne l'a jusqu'ici encode pour le cas ou une requete echoue. C'est
exactement le trou que ce sprint revele.

## Decision

**Toute exception non geree qui atteint la frontiere HTTP de `adapter-web` est rendue en fragment
HTML francais, jamais en corps technique brut ; la correction vit entierement dans `adapter-web`.**

Motif du perimetre : le probleme est un defaut d'`adapter-web`, pas de cablage. Rien dans
`bootstrap` n'a besoin de changer - il n'y a pas de route hors du controleur existant a corriger, et
la page d'erreur par defaut de Spring Boot (`BasicErrorController`, configuree dans `bootstrap` via
les proprietes `server.error.*`) reste en place pour tout ce que cet ADR ne couvre pas explicitement
(voir Consequences). Etendre son perimetre a des routes hors du jeu n'est demande par aucune story
de ce sprint.

Mise en oeuvre attendue :

1. Une classe annotee `@org.springframework.web.bind.annotation.ControllerAdvice` dans
   `com.recreo.adapter.web`, avec une methode `@ExceptionHandler` qui intercepte au minimum
   `IllegalStateException` et `NoSuchElementException` (les deux exceptions actuellement levees par
   `PartieController`) et renvoie le nom de vue `fragments/erreur :: erreur`, statut HTTP `200`. Le
   statut `200` n'est pas cosmetique : htmx ne substitue le fragment dans la zone de jeu que sur une
   reponse de succes ; renvoyer un statut d'erreur laisserait a nouveau l'enfant devant un ecran
   fige ou devant le corps brut du navigateur, selon la configuration htmx par defaut.
2. Le gabarit `fragments/erreur.html`, au meme titre que `manche.html` et `retour.html` : un message
   court en francais integralement accentue (categorie B, exigence positive de `DEC-0002` amendee)
   et un bouton ou lien qui ramene a `/`. Il est deja couvert, sans aucune modification de regle,
   par les trois gardes d'ADR-0008 qui balaient tout `.html` sous `templates/` :
   `FrontieresDuJavaScriptTest.aucunGabaritNeMarqueLaBonneReponse` et l'absence de logique cote
   client restent valables sur ce nouveau fichier des sa creation.
3. Une exception qu'aucun `@ExceptionHandler` ne nomme explicitement (un bug reellement imprevu)
   doit rester rare et visible : elle continue de remonter vers le comportement par defaut de
   Spring Boot. Ce n'est pas la corriger que cette story demande - elle demande de couvrir le
   symptome constate, reproduit et nomme. Voir "Ce qui me ferait changer d'avis" pour la limite.

## Regle executable ArchUnit

Deux regles de bytecode, plus un test de comportement que ArchUnit ne peut pas rendre.

Fichier : `bootstrap/src/test/java/com/recreo/archi/GestionDesErreursTest.java`

```java
package com.recreo.archi;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.methods;

@AnalyzeClasses(packages = "com.recreo", importOptions = ImportOption.DoNotIncludeTests.class)
class GestionDesErreursTest {

    private static final String ADVICE = "org.springframework.web.bind.annotation.ControllerAdvice";
    private static final String HANDLER = "org.springframework.web.bind.annotation.ExceptionHandler";
    private static final String RESPONSE_BODY = "org.springframework.web.bind.annotation.ResponseBody";
    private static final String RESPONSE_ENTITY = "org.springframework.http.ResponseEntity";

    /** Une porte de rendu d'erreur existe. Sans elle, ce test echoue par vacuite (ADR-0003, dispense 1). */
    @ArchTest
    static final ArchRule uneAdviceDErreurExiste = classes()
            .that().areAnnotatedWith(ADVICE)
            .should().resideInAPackage("com.recreo.adapter.web..")
            .allowEmptyShould(false)
            .as("ADR-0011 : une porte de rendu d'erreur existe dans adapter-web");

    /** Le contournement le plus probable : repondre par du JSON plutot que par une vue. */
    @ArchTest
    static final ArchRule uneErreurNeRendPasUnCorpsBrut = methods()
            .that().areAnnotatedWith(HANDLER)
            .should().notBeAnnotatedWith(RESPONSE_BODY)
            .andShould().notHaveRawReturnType(RESPONSE_ENTITY)
            .as("ADR-0011 : un gestionnaire d'erreur rend une vue HTML, jamais un corps technique");
}
```

Verifie contre `archunit-1.5.0.jar` (classes extraites et lues par `javap`, pas suppose) :
`MembersThat.areAnnotatedWith(String)`, `MembersShould.notBeAnnotatedWith(String)`,
`CodeUnitsShould.notHaveRawReturnType(String)` et `MethodsShouldConjunction.andShould()` existent
tous avec cette signature.

Contre-exemples, tous deux dans `bootstrap/src/test/java` :

| Regle | Contre-exemple | Ce qu'il prouve |
|---|---|---|
| `uneAdviceDErreurExiste` | evaluee sur un paquet sans classe `@ControllerAdvice` | `allowEmptyShould(false)` mord, meme mecanisme que les regles d'existence d'ADR-0005 |
| `uneErreurNeRendPasUnCorpsBrut` | `com/recreo/adapter/web/contreexemple/GestionnaireErreurBruteContreExemple.java` : une classe `@RestControllerAdvice` dont la methode `@ExceptionHandler` retourne `ResponseEntity<String>` | reproduit exactement le mode de defaillance constate (un corps de reponse qui n'est pas une vue) |

Test de non-vacuite attendu dans `LesReglesMordentTest` :
`unGestionnaireDErreurQuiRendUnCorpsBrutEstRefuse`, verifiant `hasMessageContaining` sur le nom de
la classe fautive et sur `ResponseEntity`.

**Ce qu'ArchUnit ne peut pas garder, et par quoi c'est garde a la place.** Que le fragment
effectivement rendu soit court, en francais correctement accentue, et qu'il ne contienne aucun des
marqueurs techniques constates (`"status"`, `"timestamp"`, `Internal Server Error`) est une
propriete du **contenu** de la reponse, pas de sa structure. Elle est gardee par un test
d'integration dans `adapter-web`, sur le modele deja etabli par ADR-0008 (jsoup, jamais
`java.util.regex`, deja interdit dans ce module par `AssertionsWebTest`) :

Fichier attendu : `adapters/adapter-web/src/test/java/com/recreo/adapter/web/ErreurDeSessionPerdueTest.java`,
un test `@SpringBootTest` ou `@WebMvcTest` qui reproduit exactement le scenario de `nadia-prof`
(coup joue sans demarrage prealable de partie) et verifie : statut HTTP `200`, `Content-Type`
`text/html`, presence d'un bouton ou lien vers `/` (lu par jsoup), et absence des chaines
`"status"`, `"timestamp"`, `Internal Server Error` dans le corps (comparaison de sous-chaine, pas
une expression reguliere).

## Consequences

- Deux fichiers de production de plus (`@ControllerAdvice`, `fragments/erreur.html`) et deux
  fichiers de test dans `bootstrap`, plus un test d'integration dans `adapter-web`. Cout comparable
  a une regle ArchUnit ordinaire (ADR-0003).
- La page d'erreur par defaut de Spring Boot reste active pour tout ce qui n'est ni
  `IllegalStateException` ni `NoSuchElementException` levees par `PartieController`. Une route qui
  n'existe pas (`/nimportequoi`) continuera de repondre par le mecanisme par defaut de Spring Boot :
  aucune story de ce sprint ne demande de le couvrir, et l'etendre sans besoin constate serait de
  l'anticipation. Nomme ici pour ne pas le taire.
- Mauvaise consequence assumee : la regle `uneErreurNeRendPasUnCorpsBrut` ne verifie que la
  **forme** de la reponse (pas de `@ResponseBody`, pas de `ResponseEntity`), pas son **contenu**.
  Un gestionnaire qui renvoie une vue Thymeleaf contenant malgre tout un message en anglais
  passerait cette regle ; c'est le test d'integration nomme ci-dessus qui ferme ce trou.

## Alternatives considerees

- **Configurer `server.error.whitelabel.enabled=false` et une page d'erreur JSP/HTML statique
  globale dans `bootstrap`.** Couvre toutes les routes, mais deplace la decision dans le module de
  cablage plutot que dans l'adaptateur qui sert le jeu, et une page d'erreur statique ne peut pas
  offrir le bouton "recommencer" cible sur le jeu en cours. Ecarte pour ce sprint ; a reconsiderer
  si une deuxieme famille d'erreurs hors du jeu doit etre couverte.
- **Laisser htmx intercepter l'evenement `htmx:responseError` et injecter le message cote
  client.** Deplacerait une decision de rendu vers le JavaScript, contraire au principe central
  d'ADR-0008 ("le serveur detient l'etat et le verdict") et consommerait une part du budget de
  4 096 octets pour un besoin que le serveur sait deja traiter. Ecarte.
- **Rediriger (302) vers `/` plutot que de rendre un fragment.** Plus simple, mais perd le message
  explicatif que le critere d'acceptation demande explicitement ("un message court que je peux
  lire"), et une redirection recharge toute la page au lieu de rester dans l'echange htmx en cours.
  Ecarte.

## Ce qui me ferait changer d'avis

- Une deuxieme famille d'erreurs constatee hors du perimetre de `PartieController` (par exemple une
  erreur sur une route de contenu statique) : je generaliserais le `@ExceptionHandler` a
  `Exception.class` et je reverrais alors si la page d'erreur par defaut de Spring Boot doit etre
  desactivee globalement dans `bootstrap`.
- Si htmx elle-meme changeait son comportement par defaut sur les statuts non-2xx dans une version
  future du fichier vendu, je revererais le choix du statut `200` a la lumiere du nouveau
  comportement plutot que de le garder par habitude.
