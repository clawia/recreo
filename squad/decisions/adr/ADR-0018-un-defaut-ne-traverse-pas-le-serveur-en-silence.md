# ADR-0018 - Un defaut ne traverse pas le serveur en silence, et n'emprunte pas le canal de l'attendu

- **Statut** : Accepte
- **Date** : 2026-08-06
- **Auteur** : theo-archi
- **Sprint** : s05
- **Complete** : ADR-0011 (dont il ne remet en cause ni l'ecran, ni le statut 200)

## Contexte

Un defaut de production a traverse 200 tests verts. Toute reponse contenant un caractere accentue
casse la manche sur `bandit-manchot-homophones-cm1` et affiche l'ecran d'erreur. La correction
technique appartient a `lea-dev` ; cet ADR porte sur ce que le defaut revele du chemin d'erreur.

Tout ce qui suit est mesure sur le jar de `099e750`, pas suppose.

**Fait 1 - la reproduction.** Deux sessions neuves sur le meme point d'entree :
`propositionChoisie=a` rend un verdict normal, `propositionChoisie=à` rend
`Oups ! Le jeu a un souci.`. Les deux en HTTP 200.

**Fait 2 - le silence, verifie et pas rapporte.** Journal complet de l'application apres avoir
declenche l'erreur : **21 lignes, toutes de demarrage**. Pas une ligne sur l'echec. Un defaut reel,
reproductible, qui casse une partie sur deux jeux, ne laissait aucune empreinte. Pour apprendre ce
qui s'etait passe, il a fallu **relancer l'application** avec
`--logging.level.org.springframework.web=DEBUG` et lire la ligne du `DispatcherServlet`, pas la
notre. Un diagnostic qui exige de redemarrer le serveur dans une autre configuration n'est pas un
diagnostic : c'est une deuxieme enquete.

**Fait 3 - le plus genant, et personne ne l'avait vu.** L'exception n'est ni une session perdue ni
un jeu inconnu. C'est :

```
org.apache.tomcat.util.http.InvalidParameterException: Character decoding failed.
Parameter [propositionChoisie] with value [?] has been ignored.
```

Et, lu par `javap` sur `tomcat-embed-core-11.0.22.jar` extrait du jar lui-meme :

```
public class org.apache.tomcat.util.http.InvalidParameterException extends java.lang.IllegalStateException
```

Une exception **du conteneur**, levee pendant le decodage des parametres, avant meme que le
controleur soit appele - la pile le montre : `Parameters.processParameters` puis
`RequestParamMethodArgumentResolver`. Elle etait attrapee par une methode nommee
`partieIntrouvable()`, et rendue a l'enfant comme une session perdue.

### Ce que ce fait dit d'ADR-0011

ADR-0011 a eu raison sur l'ecran et sur le statut 200 : `nadia-prof` avait refuse du JSON brut rendu
a un enfant, htmx ne substitue le fragment que sur une reponse de succes, rien de cela n'est en
cause et rien de cela ne change.

Ce qui est faux est son point 3, ecrit noir sur blanc : *"Une exception qu'aucun `@ExceptionHandler`
ne nomme explicitement (un bug reellement imprevu) doit rester rare et visible."* Elle n'a ete ni
rare ni visible, et la raison n'est pas un oubli d'ecriture. **Nommer deux types n'est pas nommer
deux situations.** Un `@ExceptionHandler` attrape un type **et tout son sous-arbre**, et
`IllegalStateException` est le sous-arbre le plus peuple du JDK : n'importe quelle couche du serveur
peut en lever une. ADR-0011 raisonnait sur une liste de situations et encodait une liste de types.
L'ecart entre les deux est exactement l'espace ou ce defaut s'est loge.

Le mode de defaillance merite d'etre nomme parce qu'il est le symetrique de celui d'ADR-0013. Un
perimetre enumere echoue par **omission** : un objet qu'on n'a pas inscrit n'est jamais juge, et
rien ne le dit. Un perimetre enumere **par types** echoue en plus par **inclusion** : un objet qu'on
n'a jamais inscrit est juge quand meme, traite comme ce qu'il n'est pas, et rien ne le dit non plus.
Les deux sont silencieux ; le second est plus trompeur, parce qu'il produit une reponse qui a l'air
correcte.

**Fait 4 - le canal etait aussi trop etroit, mesure au passage.** Une exception hors du sous-arbre,
sur la route du jeu elle-meme (`POST /parties/{jeuId}/coups` sans le parametre `propositionChoisie`)
rend `{"timestamp":"...","status":400,...}`. C'est le motif de refus 3 de `nadia-prof`, intact, sur
une route qu'ADR-0011 affirmait couvrir. ADR-0011 avait nomme ce trou pour les routes **hors** du
jeu ; il existe aussi **dans** le jeu. Voir "Ce que cette regle ne couvre pas".

## Decision

**L'enfant voit le meme ecran ; le journal distingue les deux causes.**

Une erreur attendue et un defaut inattendu ne doivent pas emprunter le meme chemin - mais ce qui les
separe n'est pas l'ecran, c'est la trace. Trois obligations.

1. **Une situation prevue est nommee par un type que recreo declare.** `PartieIndisponible`, dans
   `com.recreo.adapter.web`, remplace `IllegalStateException` et `NoSuchElementException` aux deux
   points de levee de `PartieController`. Rien hors de recreo ne peut lever ce type : c'est la seule
   facon qu'un canal reserve a l'attendu ne soit pas emprunte par autre chose.

2. **Tout le reste est un defaut, et part en `ERROR` avec sa pile.** Le gestionnaire general attrape
   `RuntimeException`. Attraper large n'a jamais ete le danger ; le danger etait d'attraper large en
   ayant l'air d'attraper etroit, et sans rien dire.

3. **Aucun gestionnaire d'erreur ne rend un ecran sans journaliser.** Une reponse rendue a l'enfant
   n'est pas une trace : l'enfant n'est pas un canal de supervision.

Ce que doit contenir la ligne pour etre exploitable, et pourquoi chaque morceau :

| Element | Sans lui |
|---|---|
| Methode et chemin HTTP | on sait qu'il y a eu un defaut, pas ou |
| Classe et message de la cause | on ne distingue pas ce defaut d'une session perdue - c'est precisement le fait 3 |
| Pile, pour un defaut seulement | le message de Tomcat seul ne dit pas que l'echec est dans la resolution d'argument |

Niveaux : `INFO` pour la situation prevue (une ligne, pas de pile - une session perdue est un
fonctionnement normal), `ERROR` pour le defaut. La separation ne vaut que si elle tient dans les
deux sens : si l'attendu partait aussi en `ERROR`, `ERROR` redeviendrait du bruit qu'on n'ouvre plus.

Ce que cette decision **ne change pas** : l'ecran de l'enfant, son texte, le statut 200, le fragment
`fragments/erreur`, et le raisonnement d'ADR-0011 qui les justifie.

## Regle executable ArchUnit

Fichier : `bootstrap/src/test/java/com/recreo/archi/GestionDesErreursTest.java`

Deux regles ajoutees aux deux d'ADR-0011. **Les deux mordent sur le code tel qu'il etait avant cet
ADR** - ce n'est pas une precaution, c'est la mesure qui les a fait ecrire.

```java
    @ArchTest
    static final ArchRule toutGestionnaireDErreurJournalise = methods()
            .that().areAnnotatedWith(HANDLER)
            .should(appelerLeJournal())
            .as("ADR-0018 : un gestionnaire d'erreur laisse une trace dans le journal");

    @ArchTest
    static final ArchRule unGestionnaireNommeUnTypeDeRecreoOuLaCaptureGenerale = methods()
            .that().areAnnotatedWith(HANDLER)
            .should(neNommerQueDesTypesDeRecreoOuLaCaptureGenerale())
            .as("ADR-0018 : un gestionnaire nomme un type de recreo, ou assume la capture generale");
```

La seconde lit les types nommes par l'annotation et, si elle est vide, les parametres de la methode.
Les deux formes sont valides en Spring ; ne lire que la premiere laisserait la seconde echapper sans
un mot, ce qui est l'obligation de derivation totale d'ADR-0013 violee dans la regle qui l'invoque.
Un gestionnaire qui ne nomme aucun type des deux facons est une violation, pas un membre ignore.

Verifie contre `archunit-1.5.0.jar` par `javap`, pas suppose : `JavaMethod.getAnnotationOfType(String)`,
`JavaAnnotation.get(String)`, `JavaCodeUnit.getRawParameterTypes()` et
`JavaCodeUnit.getMethodCallsFromSelf()` existent avec ces signatures.

Contre-exemples, un par mode de defaillance, tous deux dans
`bootstrap/src/test/java/com/recreo/adapter/web/contreexemple/` :

| Regle | Contre-exemple | Ce qu'il prouve |
|---|---|---|
| `toutGestionnaireDErreurJournalise` | `GestionnaireMuetContreExemple` : nomme un type de recreo, rend bien une vue, ne journalise rien | la forme exacte du gestionnaire mesure sur `099e750` - correct sur tous les autres criteres, muet sur celui-la |
| `unGestionnaireNommeUnTypeDeRecreoOuLaCaptureGenerale` | `GestionnaireTypeDuJdkContreExemple` : journalise correctement, mais attrape `IllegalStateException` | le sous-arbre par lequel une exception du conteneur s'est fait passer pour une session perdue |

Chaque contre-exemple ne viole qu'une regle : chacune est donc prouvee seule, et un vert sur l'une
ne peut pas etre emprunte a l'autre.

Tests de non-vacuite dans `LesReglesMordentTest` :
`unGestionnaireDErreurQuiNeJournalisePasEstRefuse` et
`unGestionnaireQuiAttrapeUnTypeDuJdkEstRefuse`, avec `hasMessageContaining` sur la classe fautive et
sur le motif (`org.slf4j.Logger`, `java.lang.IllegalStateException`).

### Ce qu'ArchUnit ne peut pas garder, et par quoi c'est garde

ArchUnit voit qu'un journal est appele. Il ne voit pas **ce qui est ecrit dedans**, ni **a quel
niveau**, ni si la cause y figure. Un gestionnaire qui appellerait `JOURNAL.debug("erreur")` sans
argument passerait les deux regles - et serait aussi inutile que le silence qu'elles remplacent.

C'est un test de comportement qui ferme ce trou, et c'est lui qui repond a la question posee : un
garde-fou qui verifie qu'une exception **non prevue** laisse une trace **est** atteignable, a une
condition - qu'on accepte de faire lever une exception d'un type neuf par un double du port entrant,
plutot que d'essayer de deviner par analyse statique ce qu'un journal contiendra.

Fichier : `adapters/adapter-web/src/test/java/com/recreo/adapter/web/UneErreurImprevueLaisseUneTraceTest.java`

- `DefautJamaisVu` est declare **dans ce test**, n'existe nulle part en production, et n'est nomme
  par aucun `@ExceptionHandler` du depot. C'est l'obligation 3 d'ADR-0013 tenue a la lettre : la
  preuve se fait sur un **objet neuf**, pas sur la mutation d'un type deja connu. Muter un type
  connu n'aurait prouve que le critere ; c'est le perimetre qui etait en cause.
- Un `ListAppender` Logback branche sur le journal de `GestionnaireDesErreurs` collecte les
  evenements reels. On asserte le **niveau** (`ERROR`), la **classe de la cause** portee par le
  `ThrowableProxy`, et la presence de la methode et du chemin HTTP dans le message formate.
- Le test symetrique verifie qu'une situation prevue produit exactement un evenement `INFO`. Sans
  lui, on garderait la moitie de la decision : "tout journaliser" et "journaliser utilement" ne sont
  pas la meme propriete.
- L'ecran de l'enfant est asserte dans le meme test (200, `text/html`, message francais, absence de
  `"status"`, `"timestamp"`, `Internal Server Error`), par jsoup, jamais par expression reguliere
  (ADR-0008, deja interdit dans ce module par `AssertionsWebTest`).

### Mesures, apres correction, sur le jar reellement construit

Le meme scenario que le fait 1, sur le jar reconstruit, **au niveau de journalisation par defaut** :

```
ERROR ... c.r.adapter.web.GestionnaireDesErreurs : defaut imprevu sur POST /parties/bandit-manchot-homophones-cm1/coups
org.apache.tomcat.util.http.InvalidParameterException: Character decoding failed. Parameter [propositionChoisie] ...
	at org.apache.tomcat.util.http.Parameters.processParameters(Parameters.java:350)
	...
	at org.springframework.web.method.annotation.RequestParamMethodArgumentResolver.resolveName(...)
```

Le meme point d'entree sans partie en cours :

```
INFO  ... c.r.adapter.web.GestionnaireDesErreurs : partie indisponible sur POST /parties/bandit-manchot-homophones-cm1/coups : aucune partie en cours pour le jeu bandit-manchot-homophones-cm1
```

L'ecran rendu a l'enfant est identique dans les deux cas, et identique a celui d'avant cet ADR.

## Consequences

- Une classe de production de plus (`PartieIndisponible`), un gestionnaire de plus, deux regles, deux
  contre-exemples, un test de comportement. Cout comparable a un ADR ordinaire du depot.
- **Le journal grossit.** Une session perdue est un evenement banal - un enfant qui revient le
  lendemain sur un onglet ouvert en produit une. Une ligne `INFO` par occurrence est le prix de la
  separation ; c'est peu, et c'est reglable par configuration sans toucher au code.
- **Mauvaise consequence assumee** : `RuntimeException` est une capture tres large, et un defaut
  qu'on aurait prefere voir echouer bruyamment est desormais rendu a l'enfant comme un ecran
  presentable. C'etait deja vrai avant cet ADR pour tout le sous-arbre d'`IllegalStateException` ; ce
  qui change est qu'il ne peut plus etre silencieux. Je prefere large et trace a etroit et muet.
- Le sous-arbre du chargeur de contenu (`YamlContentPackLoader` leve une dizaine
  d'`IllegalStateException`) n'est pas concerne : il s'execute a la creation du bean, donc au
  demarrage, et un echec y arrete l'application avec sa pile. Mesure, pas suppose. S'il devenait
  paresseux un jour, ses erreurs tomberaient desormais dans la capture generale, en `ERROR`, ce qui
  est le bon comportement.

## Ce que cette regle ne couvre pas, dit avant qu'on le decouvre

- **Les exceptions du framework qui ne sont pas des `RuntimeException` restent hors du chemin.**
  Verifie par `javap` : `NoResourceFoundException` et `MissingServletRequestParameterException`
  heritent de `jakarta.servlet.ServletException`, qui est une exception controlee. Consequence
  mesuree sur le jar reconstruit : un parametre manquant sur la route du jeu rend toujours du JSON
  brut en 400, et une ressource statique absente rend toujours du JSON en 404. C'est **voulu** pour
  la seconde - une requete de feuille de style ne doit pas recevoir un fragment de jeu en 200 - et
  c'est une **dette ouverte** pour la premiere. Je ne la ferme pas dans cet ADR : aucune story ne la
  demande, aucun enfant ne l'a rencontree, et la fermer proprement demande de distinguer les
  requetes htmx des autres. Nommee ici pour qu'elle ne soit pas redecouverte comme une surprise.
- **Aucun automate ne verifie que la ligne de journal est *lue*.** Le depot n'a pas de collecte de
  journaux, et en inventer une pour deux jeux serait de l'anticipation. Ce que garantit cet ADR est
  que la trace **existe** et qu'elle est exploitable une fois ouverte.
- **La regle sur les types ne juge que les `@ExceptionHandler`.** Un `try`/`catch` ecrit dans un
  controleur qui avalerait une exception lui echapperait. Aucun n'existe aujourd'hui - les deux
  seuls `catch` du code de production sont dans `YamlContentPackLoader` et relancent la cause. Le
  jour ou il y en aura un, c'est le perimetre de la regle qu'il faudra elargir.
- **Un autre chemin du depot a la meme forme, et je ne le ferme pas ici.**
  `BanditManchotSession.tirerManche` : quand le paquet de contenu est epuise avant `nombreDeManches`,
  la session passe en `TERMINEE` et l'enfant recoit l'ecran de fin de partie normal. Un contenu trop
  court est donc indiscernable d'une partie terminee, sans trace. Mesure : les deux paquets du
  catalogue portent 22 items et une source combinatoire infinie, pour `nombreDeManches: 10` - le cas
  n'est **pas** atteignable aujourd'hui, et `CatalogueChargeableIT` ne verifie que la borne basse
  (`nombreDeManches >= longueurMinimale`), jamais la haute. Je le nomme sans ecrire de regle : le
  defaut n'a jamais ete constate, et une regle sur un usage imagine est ce que l'ADR-0013 appelle
  decoratif. Ce qui la ferait ecrire : un paquet fini plus court que son `nombreDeManches` entrant au
  catalogue. C'est un arbitrage de couverture, donc `yanis-qa` et `camille-po`, pas moi.

## Alternatives considerees

- **Ajouter seulement la journalisation, en gardant les deux types du JDK.** La plus economique, et
  elle aurait suffi a rendre le defaut visible : la ligne aurait porte
  `InvalidParameterException: Character decoding failed`. Ecartee pour une raison precise : avec un
  seul canal on ne peut pas distinguer les niveaux, et il faudrait brancher sur le type **dans** le
  gestionnaire - c'est-a-dire re-encoder le deguisement dans un `if`. Et le nom `partieIntrouvable()`
  continuerait de mentir sur ce qu'il traite.
- **Un `@ExceptionHandler(Exception.class)` global.** Couvrirait aussi le trou du fait 4. Ecarte
  apres verification : il attraperait `NoResourceFoundException` et rendrait un fragment de jeu en
  HTTP 200 pour une ressource statique absente. Le remede serait pire.
- **Restreindre l'advice par `@ControllerAdvice(assignableTypes = PartieController.class)`.**
  Permettrait la capture generale sans risque sur les ressources statiques. Ecarte : `assignableTypes`
  est une **liste de controleurs ecrite a la main**, donc un perimetre enumere en production, et un
  second controleur lui echapperait en silence - la faute d'ADR-0013 commise pour la corriger. Le
  choix de `RuntimeException` obtient le meme resultat sans enumerer personne.
- **Laisser remonter les defauts vers la page d'erreur par defaut de Spring Boot, comme ADR-0011 le
  prevoyait.** C'est l'etat mesure : elle rend du JSON a l'enfant. C'est le refus de `nadia-prof`.
  Ecarte.
- **Journaliser les parametres de la requete.** Le defaut aurait saute aux yeux (`value [Ã ]`).
  Ecarte : la pile et le message de Tomcat nomment deja le parametre fautif, et prendre l'habitude
  d'ecrire l'entree utilisateur dans le journal est une porte qu'on n'a aucune raison d'ouvrir.

## Ce qui me ferait changer d'avis

- Si une deuxieme famille d'erreurs controlees (`ServletException`) atteint reellement un enfant, je
  ferme la dette du fait 4, et je le ferai en distinguant les requetes htmx des autres plutot qu'en
  elargissant la capture a tout.
- Si le volume des lignes `INFO` de session perdue devient un probleme mesure, je les descends en
  `DEBUG` - mais jamais les `ERROR`, qui sont la raison d'etre de cet ADR.
- Si un second controleur apparait dans `adapter-web`, je verifie que la capture generale le couvre
  bien sans rien enumerer, et j'ecris la regle qui le prouve si ce n'est pas evident.
