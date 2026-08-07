# ADR-0019 - Rendre indivisible tout cycle lire-calculer-ecrire sur l'etat de partie, et cesser de demander un coup a une partie terminee

- **Statut** : Accepte
- **Date** : 2026-08-07
- **Auteur** : theo-archi
- **Sprint** : s05
- **Complete** : ADR-0008 (l'etat de partie reste dans la session HTTP, rien de cela ne change)

## Contexte

L'utilisatrice a signale, en jouant, que "le score ne s'incremente pas toujours, sur les deux
jeux". Deux defauts distincts ont ete trouves derriere ce symptome. Les deux se declenchent par un
**double appui** - deux appuis rapproches, ou deux propositions touchees avant que le premier retour
ne s'affiche, ce qu'un enfant de CP produit sans y penser.

**Defaut 1, reproduit par `yanis-qa`** (`squad/sprints/s05-lever-le-refus-du-bon-son/rapport-qualite.md`,
section 1). `PartieController.jouer` lisait l'etat de partie dans la session HTTP, calculait l'etat
suivant, puis le reecrivait. Aucune synchronisation nulle part : ni dans `adapter-web`, ni dans
`application`. Deux `POST /parties/{jeuId}/coups` assez rapproches calculent leur etat suivant a
partir du **meme** etat de depart, et la derniere ecriture ecrase l'autre. **14 pertes sur 40
essais, 35 %**, avec deux fils synchronises par une barriere. A chaque perte, l'enfant recoit bien
"Bravo, c'est juste !" avec son point, et le point a disparu de l'etat qui fait foi une requete plus
tard. Aucune erreur, aucune trace.

**Defaut 2, trouve en verifiant que la regle de journalisation d'ADR-0018 gardait encore quelque
chose.** Rejouer le dernier coup d'une partie terminee - ce que le second appui d'un double appui
produit exactement - leve `IllegalStateException: la partie est terminee, plus aucune manche a
jouer` depuis `BanditManchotSession.jouer`, et rend "Oups ! Le jeu a un souci." a l'enfant.

### Meme racine ? Non, et c'est ce qui commande la correction

Les deux naissent du meme geste et vivent dans le meme cycle, mais ce ne sont pas deux symptomes
d'une seule faute :

- Le defaut 1 est une **absence d'exclusion mutuelle**. Il exige que les deux requetes se recouvrent.
- Le defaut 2 est atteignable avec des requetes **strictement sequentielles** : il suffit qu'un coup
  arrive apres qu'un autre a termine la partie. La concurrence n'y joue aucun role.

Le point qui tranche est celui-ci, et il n'est pas confortable. **Corriger le defaut 1 seul aggrave
le defaut 2.** Aujourd'hui, deux derniers appuis simultanes lisent tres souvent tous les deux un
etat encore `EN_COURS` : ils reussissent tous les deux, un increment est perdu en silence, et
l'enfant ne voit pas d'ecran d'erreur. Une fois le verrou pose, le second appui lit
systematiquement l'etat `TERMINEE` que le premier vient d'ecrire, et leve. Une perte intermittente
et muette deviendrait un ecran d'erreur systematique. Poser le verrou sans traiter le refus de la
mecanique serait donc une regression visible pour l'enfant.

Conclusion : **un seul geste, parce que les deux fautes vivent dans le meme cycle et que fermer
l'une sans l'autre est pire que ne rien faire ; deux clauses, parce que ce sont deux fautes.**

### Ce que le depot impose comme cadre

- Il n'y a **aucun module de persistance**. Les sessions vivent en memoire, dans un seul processus.
  Toute solution qui reposerait sur une ecriture optimiste en base suppose un module qui n'existe
  pas, pour un defaut qui existe.
- Le comportement de jeu vit sur le serveur (ADR-0008). Rien ici ne le deplace.
- `application` definit ses ports sans Spring (ADR-0002). `PartieService` est sans etat : il recoit
  l'etat en parametre et rend le suivant. Il n'y a donc rien a verrouiller dans `application`, et
  aucune infrastructure a y faire entrer. Le seul etat mutable partage du depot est la session HTTP,
  et elle vit dans `adapter-web`.

Verifie et pas suppose : **le code de production ne contient aucun champ non final**, dans aucun des
cinq modules. La session HTTP est la seule chose que deux requetes puissent modifier ensemble.

## Decision

**Un cycle lire-calculer-ecrire sur l'etat de partie est indivisible, et un seul type du depot a le
droit de toucher la session HTTP.**

1. **`EtatDePartieEnSession` (`adapter-web`) est le seul type qui touche `HttpSession`.** Il expose
   les quatre operations dont le controleur a besoin - lire un instantane, demarrer, abandonner,
   jouer un coup - et chacune s'execute sous le verrou de la session. Le controleur ne recoit plus
   `HttpSession` en parametre et ne le nomme plus.

2. **Le verrou est celui que designe `WebUtils.getSessionMutex`, et `HttpSessionMutexListener` est
   enregistre dans `bootstrap`.** Sans ce listener, le verrou est l'objet `HttpSession` lui-meme,
   dont le conteneur ne garantit pas de rendre la meme instance a deux requetes. Deux requetes qui
   verrouillent deux objets differents ne se serialisent pas du tout, et la panne serait totalement
   silencieuse : le code resterait identique, le verrou ne verrouillerait plus rien.

3. **Un coup recu sur une partie deja terminee ne rejoue rien et rend l'etat final tel quel.** Le
   refus de la mecanique reste entier - `BanditManchotSession.jouer` continue de lever si on lui
   demande une manche qui n'existe plus, et un test le verifie. Ce qui change est que l'adaptateur
   cesse de le lui demander. Une entree utilisateur atteignable n'est pas une erreur de
   programmation.

4. **La lecture d'un ecran lit les trois attributs de session ensemble**, sous le meme verrou
   (`EtatDePartie`, un instantane). Les lire un par un exposait a un etat mixte : le jeu de la
   partie qui commence avec la session de celle qui vient d'etre abandonnee. Ce defaut-la n'a jamais
   ete constate ; il coute une ligne a fermer une fois que le verrou existe, et rien a garder.

Ce que cette decision **ne change pas** : l'etat de partie reste dans la session HTTP, aucun module
de persistance n'est cree, `application` et `domain` ne sont pas touches, aucune dependance nouvelle
n'entre dans le build.

### Ce que l'enfant voit, et qui doit en juger

**Ce n'est pas ma decision.** La clause 3 change ce qu'un enfant voit apres un double appui sur la
derniere manche : il voyait "Oups ! Le jeu a un souci.", il revoit desormais **l'ecran de fin de
partie que son premier appui venait de produire**, score compris.

J'ai choisi le comportement le moins surprenant plutot que d'en inventer un : le second appui ne
produit rien de neuf, donc l'ecran ne change pas. Les autres possibilites - un message qui dit que
la partie est finie, un retour direct a la liste des jeux, un ecran de fin different du premier -
sont des choix pedagogiques. **`nadia-prof` tranche**, et `camille-po` decide si cela merite une
story. En attendant son avis, le depot tient le comportement ci-dessus, et il est epingle par un
test nomme (`unSecondAppuiSurLaDerniereMancheRevoitLEcranDeFinDePartie`) pour qu'un changement d'avis
se voie dans un diff.

## Regle executable ArchUnit

Fichier : `bootstrap/src/test/java/com/recreo/archi/AccesALEtatDeSessionTest.java`

```java
    @ArchTest
    static final ArchRule leSeulPorteurDEtatDeSessionExiste = classes()
            .that().haveFullyQualifiedName(PORTEUR)
            .should().haveOnlyFinalFields()
            .andShould().bePackagePrivate()
            .allowEmptyShould(false)
            .as("ADR-0019 : EtatDePartieEnSession existe et n'est visible que de son paquet");

    @ArchTest
    static final ArchRule unSeulTypeToucheLaSessionHttp = noClasses()
            .that().doNotHaveFullyQualifiedName(PORTEUR)
            .should().dependOnClassesThat().haveFullyQualifiedName(SESSION_HTTP)
            .as("ADR-0019 : seul EtatDePartieEnSession touche la session HTTP, et il le fait sous verrou");
```

**Ce que la seconde interdit reellement a quelqu'un qui essaierait de mal faire** : un second
controleur, ou une route neuve de celui-ci, qui refasse le cycle a la main. C'est exactement la
faute qui a produit le defaut, et c'est la seule facon qu'elle revienne. La regle attrape les
**deux** formes de l'acces, parce qu'un appel de methode sur `HttpSession` est une dependance vers
ce type que la session ait ete declaree en parametre de controleur ou tiree de
`HttpServletRequest.getSession()`. Ne fermer que la premiere aurait laisse la seconde, plus
discrete, passer sans un mot.

La premiere regle n'est pas decorative non plus, meme si elle a l'air d'un simple controle de forme.
Elle empeche l'exemption de la seconde de designer un type disparu : si `EtatDePartieEnSession`
etait supprime, `unSeulTypeToucheLaSessionHttp` deviendrait une interdiction absolue, verte le jour
ou plus personne ne toucherait la session - c'est-a-dire le jour ou l'etat de partie aurait demenage
sans que cet ADR soit relu.

Contre-exemples et tests de non-vacuite, conformement a ADR-0003 :

| Regle | Contre-exemple | Ce qu'il prouve |
|---|---|---|
| `unSeulTypeToucheLaSessionHttp` | `ControleurQuiToucheLaSessionContreExemple` (`bootstrap/src/test/java/com/recreo/adapter/web/contreexemple/`) : une route neuve qui lit, incremente et reecrit un attribut de session, en tirant la session de la requete | la forme exacte du defaut, dans sa variante la plus discrete |
| `leSeulPorteurDEtatDeSessionExiste` | aucune classe fautive : la faute serait une absence. C'est `allowEmptyShould(false)` qui mord, comme pour ADR-0005 et ADR-0011 | le porteur ne peut pas disparaitre en silence |

Dans `LesReglesMordentTest` : `unControleurQuiToucheLaSessionHttpEstRefuse` et
`unPorteurDEtatDeSessionAbsentEstRefuse`, avec `hasMessageContaining` sur la classe fautive et sur
le motif (`jakarta.servlet.http.HttpSession`).

### Ce qu'ArchUnit ne peut pas garder, et par quoi c'est garde

**ArchUnit ne voit pas un bloc `synchronized`.** Il voit des dependances entre types, pas les
instructions de prise de moniteur. La regle ci-dessus ne prouve donc pas qu'un verrou est pris ;
elle prouve qu'un seul type peut le prendre. La propriete restante - ce type le prend vraiment, et
sur le bon objet - est tenue par trois automates, tous dans `mvn verify` :

**1. La preuve d'exclusion mutuelle, deterministe dans les deux sens.**
Fichier : `adapters/adapter-web/src/test/java/com/recreo/adapter/web/EtatDePartieEnSessionTest.java`

Deux fils entrent dans le meme calcul de coup, et ce calcul attend un partenaire pendant un delai de
grace de 250 ms. Si le verrou tient, le second fil ne peut pas entrer tant que le premier n'a pas
ecrit : le premier attend pour rien, le second lit ensuite l'etat que le premier a ecrit, et les
deux tentatives sont comptees. Si le verrou ne tient pas, les deux fils se retrouvent dans le calcul
avec le meme etat de depart, sans aucune attente, et une tentative disparait. **Le delai n'est jamais
dans le chemin du rouge** : un depot casse echoue immediatement, un depot sain paye 250 ms une fois.

**2. La reproduction du symptome signale, de bout en bout en HTTP.**
Fichier : `adapters/adapter-web/src/test/java/com/recreo/adapter/web/DeuxCoupsSimultanesTest.java`

Une bonne reponse et une mauvaise, envoyees ensemble sur la meme session depuis deux fils
synchronises par une barriere, puis relecture de l'etat qui fait foi. Le score doit valoir 1 a chaque
essai, quel que soit l'ordre de traitement. C'est le protocole de `yanis-qa`, ramene au niveau du
controleur pour qu'il tienne dans la CI sans serveur.

Ce test est **probabiliste dans le sens du rouge, jamais dans celui du vert** : un depot corrige ne
perd le point a aucun essai, un depot casse ne le perd pas a tous. Mesure sur ce depot, correction
retiree : **19 pertes sur 60 essais, 32 %** - le meme ordre de grandeur que les 35 % de `yanis-qa`
sur le jar, ce qui confirme au passage que MockMvc recouvre bien la meme fenetre. Avec 60
repetitions, la probabilite qu'un depot casse traverse sans une seule perte est de 0,68 puissance
60, moins d'une chance sur un milliard. **Je ne juge donc pas ce test trop instable pour la CI**, et
la raison est precise : son instabilite ne peut produire qu'un faux vert, jamais un faux rouge, et
les repetitions ramenent le faux vert sous le seuil du negligeable. Un test de course a une seule
tentative aurait ete l'inverse de ce qu'il faut - vert deux fois sur trois sur un depot casse, donc
decoratif.

**3. La presence du listener de verrou dans le contexte assemble.**
Fichier : `bootstrap/src/test/java/com/recreo/bootstrap/VerrouDeSessionEnregistreTest.java`

La clause 2 ne porte pas sur une dependance mais sur un bean, et sa panne serait silencieuse.
Ce test verifie que le contexte contient bien un enregistrement d'`HttpSessionMutexListener`.

Les deux premiers ont ete verifies mordants par mutation, avant d'etre gardes : verrou remplace par
un objet neuf a chaque appel (rouge sur les deux tests de concurrence), puis clause 3 retiree (rouge
sur `unCoupRecuSurUnePartieTermineeNeRejoueRienEtRendLEtatFinal` et sur
`unSecondAppuiSurLaDerniereMancheRevoitLEcranDeFinDePartie`). Aucune de ces mutations n'a fait
rougir un autre test du depot : avant cet ADR, **aucun automate ne couvrait ce cas**.

## Consequences

- **Deux requetes d'une meme session sont desormais serialisees, y compris quand elles n'en avaient
  pas besoin.** Un enfant ne joue qu'une session ; deux enfants sur deux sessions ne s'attendent
  jamais. Le cout reel est nul a l'echelle du produit, et je prefere l'assumer plutot que de
  distinguer les routes qui ecrivent de celles qui lisent - une distinction qu'il faudrait maintenir
  a chaque route neuve, et dont l'oubli serait silencieux.
- **Un appel etranger s'execute sous le verrou** : `EtatDePartieEnSession.jouer` recoit le calcul du
  coup en parametre et l'appelle en tenant le moniteur. C'est une mauvaise pratique en general, et
  il faut la nommer plutot que la cacher. Elle est acceptable ici pour une raison verifiable :
  l'operateur passe est un appel a `PartieUseCase`, sans entree-sortie, sans attente et sans verrou a
  lui - `application` et `domain` n'en contiennent aucun, et ArchUnit interdit deja a `domain` de
  dependre d'autre chose que du JDK. Le jour ou le calcul d'un coup ferait une entree-sortie, cette
  conception serait a revoir avant elle.
- **Deux types de production de plus** (`EtatDePartieEnSession` et l'instantane `EtatDePartie`), 70
  lignes de code hors commentaires. Le controleur en perd 13 : il ne manipule plus d'attributs de
  session, ne nomme plus `HttpSession`, et sa methode de lecture d'etat a disparu.
- **Un bean de plus dans `bootstrap`**, dont la seule fonction est de rendre stable un objet que le
  conteneur ne promet pas de conserver.
- **Mauvaise consequence assumee** : le second appui d'un double appui sur la derniere manche est
  desormais **silencieux**. Il ne leve plus, donc il ne laisse plus de ligne `ERROR` (ADR-0018), et
  il ne compte pas. C'est voulu - ce n'est pas un defaut, c'est un enfant qui appuie deux fois - mais
  cela veut dire que si un jour un double appui devenait frequent au point de meriter une mesure,
  rien dans le journal ne le dirait. Je n'ajoute pas de compteur pour un besoin que personne n'a
  exprime.
- Le verrou porte sur la session HTTP, donc sur un seul processus. Cette decision cesserait d'etre
  suffisante le jour ou l'application tournerait en plusieurs instances. Voir plus bas.

## Ce que cette regle ne couvre pas, dit avant qu'on le decouvre

- **Elle ne couvre qu'un seul processus.** Un verrou de session en memoire ne serialise rien entre
  deux instances de l'application derriere un repartiteur de charge. Aujourd'hui il n'y en a qu'une,
  le fat jar se lance avec `java -jar`, et il n'existe aucun module de persistance ou une session
  pourrait vivre. Ce qui rendrait cette decision insuffisante : le jour ou une deuxieme instance
  serait deployee, ou ou les sessions seraient externalisees. Je ne l'anticipe pas, et la regle
  ArchUnit survivrait telle quelle a ce changement - c'est l'implementation du porteur qui
  changerait, pas son unicite.
- **Elle ne dit rien de ce que l'enfant voit.** Ni la clause 3 ni la regle ne prononcent quoi que ce
  soit de pedagogique. `nadia-prof` peut demander un autre ecran sans qu'aucune regle de cet ADR
  bouge.
- **Elle ne garde pas les autres etats mutables partages, parce qu'il n'y en a aucun.** Verifie :
  aucun champ non final dans le code de production des cinq modules. Le jour ou un composant Spring
  porterait un champ mutable - un cache, un compteur - il serait hors du perimetre de cette regle, et
  c'est un perimetre qu'il faudrait elargir plutot qu'une regle a copier.
- **Le porteur pourrait etre contourne en le rendant lui-meme bavard.** Rien n'empeche d'ajouter une
  methode a `EtatDePartieEnSession` qui rendrait la session HTTP a l'appelant. La regle mordrait
  aussitot, parce que l'appelant dependrait alors du type, mais elle ne mordrait pas si le porteur
  gagnait une methode non verrouillee. C'est un fichier de 96 lignes, `final`, avec un seul champ,
  dont la Javadoc dit pourquoi chaque methode est verrouillee ; je m'en remets a la revue sur ce
  point plutot que d'inventer une regle sur la forme du corps d'une methode.

## Alternatives considerees

- **Verrouiller dans `application`, ou faire de `PartieService` le gardien de l'etat.** Ecartee sans
  hesiter : `application` ne connait pas la session HTTP et ne doit pas la connaitre (ADR-0002), et
  y loger un etat mutable ferait entrer de l'infrastructure dans une couche qui doit rester un
  calcul. `PartieService` est sans etat, et c'est cela qui le rend testable.
- **Un verrou global, statique.** La plus simple a ecrire et la plus facile a prouver correcte.
  Ecartee : elle serialise deux enfants qui n'ont rien a partager. Le cout est invisible sur deux
  jeux et devient une panne de classe entiere le jour ou trente enfants jouent ensemble, sans que
  rien n'ait change dans le code entre-temps. Une mauvaise conception qui ne se manifeste qu'a
  l'usage reel est exactement ce qu'on ne veut pas laisser derriere soi.
- **Une table de verrous indexee par identifiant de session.** Fonctionnellement equivalente a ce qui
  est retenu, mais elle exige une eviction - donc un `HttpSessionListener` de toute facon - plus une
  table concurrente et sa politique de nettoyage. Plus de pieces mobiles pour le meme resultat, et
  une fuite memoire possible si l'eviction est oubliee. Le verrou porte par la session meurt avec
  elle sans que personne ait a y penser.
- **Deplacer l'etat vers un magasin qui supporte l'ecriture optimiste** (piste citee par `yanis-qa`).
  Ecartee : elle suppose le module de persistance qu'ADR-0001 et DEC-0003 n'ouvrent qu'a la premiere
  story qui a besoin de conserver une donnee entre deux sessions. Aucune ne l'a demande. Creer un
  module pour corriger une course serait resoudre un probleme de concurrence par une decision de
  stockage.
- **Empecher le double appui cote navigateur** (desactiver les boutons apres le premier appui).
  Ecartee comme correction : ADR-0008 loge le comportement de jeu sur le serveur, et un garde-fou
  d'interface ne protege pas d'une requete rejouee. Cela reste une **amelioration d'interface
  legitime**, qui appartient a `ines-design` et `nadia-prof`, pas une alternative a celle-ci.
- **Rendre `BanditManchotSession.jouer` tolerant, en se rendant lui-meme quand la partie est
  terminee.** Ecartee : la mecanique a raison de refuser, et un refus qui devient un silence rend
  indetectable un vrai defaut d'appel. C'est a l'adaptateur, qui seul sait qu'une requete peut
  arriver deux fois, de ne pas poser la question.

## Ce qui me ferait changer d'avis

- **Une deuxieme instance de l'application, ou des sessions externalisees.** Le verrou en memoire
  cesserait de suffire ; il faudrait un verrou porte par le magasin de sessions, et cet ADR serait
  amende sans que sa regle change.
- **Un calcul de coup qui ferait une entree-sortie** (chargement paresseux de contenu, appel a un
  service). Tenir un verrou de session pendant une entree-sortie serait a refuser ; il faudrait
  alors sortir le calcul du bloc verrouille et le remplacer par une comparaison-et-echange sur
  l'etat lu.
- **Un besoin mesure de laisser deux requetes d'une meme session progresser en parallele.** Je ne le
  vois pas venir sur un jeu ou l'enfant repond une chose a la fois, mais s'il arrivait, la reponse
  serait de distinguer lecture et ecriture, pas de retirer le verrou.
- **Un avis de `nadia-prof` sur ce que doit voir un enfant apres un double appui.** Il changerait la
  clause 3 sans toucher a la regle ni au verrou.
