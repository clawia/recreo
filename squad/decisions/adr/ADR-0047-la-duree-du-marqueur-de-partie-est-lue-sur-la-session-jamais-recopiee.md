# ADR-0047 - Deriver la duree de vie du marqueur de partie de la duree d'inactivite de la session, lue et jamais recopiee

- **Statut** : **Propose.** Il devient `Accepte` le jour ou sa regle executable existe, c'est-a-dire
  dans le commit du lot de `S16-1`. **Il ne doit pas etre accepte avant** : sa regle serait rouge sur
  la production d'aujourd'hui, puisque c'est justement le defaut qu'elle nomme.
- **Date** : 2026-08-19
- **Auteur** : theo-archi
- **Sprint** : s16 (avis de faisabilite, avant tout commit de code)
- **Complete** : `ADR-0019` (un seul type touche la session HTTP), `DEC-0028` (aucune persistance),
  `DEC-0036` (le message borne a une interruption plausible)
- **Regle executable** : due par le lot de `S16-1`, enoncee ci-dessous au mot. **Pourquoi je ne
  l'ecris pas dans ce commit** : elle mesure une relation entre deux durees dont l'une n'existe pas
  encore dans la production ; l'ecrire aujourd'hui livrerait une CI rouge a l'ouverture d'un sprint,
  et une regle rouge est une regle qu'on desactive. Le statut `Propose` porte cette attente au lieu de
  la cacher, et c'est la difference avec `ADR-0042`, accepte en s13 avec une regle qui n'existe
  toujours pas.

## Contexte

L'utilisatrice revient sur l'application apres une pause ordinaire et lit le message de partie
interrompue alors qu'elle n'a rien interrompu. `DEC-0036` a tranche le produit : ce message ne doit
s'afficher que pour une interruption plausible. `camille-po` en tire un invariant sans chiffrer aucune
duree - **le marqueur ne reste jamais valide au-dela du moment ou la session aurait de toute facon
expire** - et route le mecanisme ici, en demandant une **lecture** de la duree de session plutot
qu'une duplication de sa valeur.

**Le defaut, lu dans la production et non suppose.** `PartieController.marquerPartieEnCours` construit
le cookie, pose son chemin, et **n'appelle jamais `setMaxAge`**. Un cookie sans `Max-Age` est un cookie
de session **de navigateur** : il survit tant que l'onglet vit, et il ne sait rien de la session HTTP.
Les deux durees n'ont donc aujourd'hui aucun rapport - l'une est gouvernee par le navigateur de
l'enfant, l'autre par le conteneur.

**Et il n'y a aucune duree a lire dans un fichier de configuration** : `bootstrap/src/main/resources`
ne contient que `META-INF`, aucun `application.properties`. La duree d'inactivite est donc celle du
conteneur par defaut, qu'aucun fichier du depot ne nomme. **Toute regle qui recopierait un nombre
serait fausse le jour ou quelqu'un configurerait cette duree**, et elle serait fausse en silence : le
marqueur survivrait a la session sans qu'aucun test ne bouge. C'est exactement ce que `camille-po`
refuse, et elle a raison de le refuser avant de connaitre le mecanisme.

## Decision

**1. La duree de vie du marqueur est la duree d'inactivite de la session, lue a l'execution.** Le
marqueur declare son age maximal egal a ce que rend `HttpSession.getMaxInactiveInterval()` - la valeur
que le conteneur applique reellement, en secondes, quelle qu'elle soit et d'ou qu'elle vienne. Aucun
nombre n'entre dans le depot, ni dans la production ni dans le test.

**2. Cette lecture vit dans `EtatDePartieEnSession`, et ce n'est pas un choix de gout : c'est la seule
place que le depot autorise.** `AccesALEtatDeSessionTest.unSeulTypeToucheLaSessionHttp` interdit a
toute classe autre que `com.recreo.adapter.web.EtatDePartieEnSession` de dependre de
`jakarta.servlet.http.HttpSession`, et sa javadoc precise que l'interdiction attrape aussi la forme
obtenue par `HttpServletRequest.getSession()`. Lire la duree depuis `PartieController` rendrait donc
cette regle rouge - et elle aurait raison. **`ADR-0019` repond donc a la question de placement sans
etre amende** : le porteur de l'etat de session expose la duree, le controleur la consomme.

**3. Le marqueur reste un detail d'adaptateur, et la frontiere ne bouge pas.** Le cookie est construit
et pose par `adapter-web` ; `application` et `domain` n'apprennent rien. **`DEC-0028` est tenue a la
lettre** : rien n'est conserve, et la seule chose que ce changement fait est de faire **mourir**
quelque chose plus tot. Une decision qui reduit une duree de vie ne peut pas introduire de
persistance.

**4. Ce que la decision ne fait pas.** Elle ne configure aucune duree de session - ce serait une
decision de deploiement, et ce depot n'en a pas besoin pour tenir l'invariant. Elle ne touche pas le
texte du message, deja valide. Elle ne distingue pas un redemarrage d'un plantage : les deux
produisent le meme etat observable - un marqueur encore valide, aucune session - et c'est cet etat-la
qui merite le message.

## Regle executable, enoncee au mot pour le lot de S16-1

> **Sur une reponse qui pose le marqueur de partie en cours, la duree de validite declaree du marqueur
> est egale a la duree d'inactivite maximale de la session HTTP de cette meme requete.**

- **Perimetre derive** : les reponses viennent de vraies requetes sur de vraies sessions, comme le
  corpus d'`ADR-0038` ; la duree attendue est **lue sur la session de la requete jugee**, jamais
  ecrite dans le test. Le garde-fou ne connait aucun nombre, donc il suit une configuration future
  sans etre relu.
- **Derivation totale** : une reponse qui pose le marqueur sans declarer de duree, une duree negative
  ou nulle, et une session dont la duree d'inactivite n'est pas positive sont des **echecs**, jamais
  des cas ignores. Le cas de la duree absente est celui de la production d'aujourd'hui : c'est lui qui
  rendra la regle rouge avant le correctif.
- **Mise a l'epreuve** : la forme d'aujourd'hui **est** le cas fautif. Le lot doit constater le rouge
  avant correctif et l'inscrire, puis corriger, puis conserver la forme d'avant correctif comme jeu
  fautif - la sequence qu'`ADR-0038` a etablie, et la seule fois ou l'etat du depot fournit
  gratuitement l'epreuve.
- **Ce n'est pas une regle ArchUnit**, et le compte reste a **29** : ArchUnit lit des classes et leurs
  dependances, pas la valeur d'un en-tete de reponse. Le mecanisme automatique est un test de
  `adapter-web`, execute par `mvn verify`.

## Consequences

- **Le marqueur meurt avec la session, donc le message ne se declenche plus que pour une interruption
  qui n'est pas l'expiration.** C'est l'invariant demande, obtenu sans chiffrer une duree.
- **Un cout reel** : le marqueur cesse de survivre au-dela de la duree d'inactivite. Un enfant qui
  reviendrait plusieurs heures apres un plantage ne verrait plus le message. C'est voulu - au-dela de
  cette duree, l'interruption cesse d'etre distinguable d'une pause ordinaire, et c'est precisement la
  frontiere que `DEC-0036` demande.
- **`EtatDePartieEnSession` gagne un accesseur qui n'est pas un etat de partie.** Je le note comme un
  inconfort plutot que de le taire : ce type porte "ce que la session dit", et la duree en fait
  partie, mais son nom parle de partie. Ce qui me ferait changer d'avis : un second besoin de lire une
  propriete de session etrangere a la partie ; il faudrait alors un type dedie plutot qu'un accesseur
  de plus, et `ADR-0019` devrait nommer deux porteurs au lieu d'un.
- **Aucun fichier de configuration n'est ajoute**, donc aucun garde-fou de chemin ni de secret n'est
  concerne.

## Alternatives considerees

1. **Recopier une duree dans le code ou dans un fichier de proprietes.** Ecartee : deux sources de
   verite pour une meme duree, dont la divergence est **silencieuse** - le marqueur survivrait a la
   session sans qu'un test bouge. C'est ce que `camille-po` a refuse d'avance, et c'est la dette 2 de
   la version precedente sous une autre forme : une valeur qu'il faut se souvenir de tenir a jour.
2. **Configurer explicitement la duree d'inactivite et y adosser le marqueur.** Ecartee pour ce lot :
   elle ne rend pas l'invariant plus vrai, elle ajoute un fichier de configuration et une decision de
   deploiement que rien ne demande. La lecture a l'execution vaut pour toute valeur, y compris
   celle-la si elle est configuree un jour.
3. **Supprimer le marqueur et deduire l'interruption autrement.** Ecartee : sans marqueur, un premier
   passage et un retour apres plantage sont indiscernables, et le message disparaitrait pour tous les
   cas - ce qui contredit le troisieme critere de `S16-1` et l'arbitrage deja rendu sur ce message.
4. **Lire la duree dans le controleur.** Ecartee par une regle existante et non par preference :
   `AccesALEtatDeSessionTest` la refuse, et elle a raison de la refuser.

## Ce qui reste non gardable, et le dit

- **Qu'une session ait reellement expire par inactivite.** Aucun automate de ce depot ne fait passer le
  temps. Le garde-fou tient la **relation entre les deux durees** ; que la consequence observable soit
  la bonne apres une vraie pause est le constat de `nadia-prof`, et `S16-1` le lui demande nommement.
- **Que le navigateur honore la duree declaree.** Le garde-fou lit ce que le serveur declare, jamais ce
  qu'un navigateur en fait - la meme limite qu'`ADR-0022` nomme pour son selecteur relationnel.
