# ADR-0020 - Juger un ecran sur le document assemble apres l'echange htmx, et non sur le fragment ni sur la page entiere

- **Statut** : Accepte
- **Date** : 2026-08-07
- **Auteur** : theo-archi
- **Sprint** : s06
- **Complete** : ADR-0008 (le comportement de jeu vit sur le serveur), ADR-0013 (un garde-fou derive
  son perimetre et se prouve mordant), ADR-0019 (ferme le meme cas cote commande)
- **Stories** : S6-11 (identifiant unique apres chaque echange htmx), et le prealable technique route
  par `DEC-0013` point 3 (`GET /parties/{jeuId}/manche` sur une partie terminee)

## Contexte

Deux defauts distincts ont ete signales a la cloture de s05, et laisses ouverts faute de diagnostic
(`squad/sprints/s05-lever-le-refus-du-bon-son/RAPPORT.md`, "Encore ouverts", lignes 5 et 6 ;
`review.md`, tableau des obstacles, lignes 131 et 132). Ils n'ont rien de commun dans leur symptome.
Ils ont tout en commun dans la raison pour laquelle aucun automate ne les a vus.

### Defaut 1 - un document qui porte deux fois `id="jeu"`

Constate par `nadia-prof` en jouant (`FICHE-bandit-manchot-additions-cp.md`, verdict du 2026-08-07,
section E, point 1), jamais diagnostique. Le defaut est reel, et il a **deux chemins distincts**, pas
un. Les deux se lisent dans les gabarits du 2026-08-07 :

1. **Le fragment reprend l'identifiant de la zone qui le recoit.** Les trois fragments de jeu
   s'ecrivaient `<div th:fragment="manche" id="jeu">`, `<div th:fragment="retour" id="jeu">`,
   `<div th:fragment="erreur" id="jeu">`. Tous les declencheurs du depot posent
   `hx-target="#jeu" hx-swap="innerHTML"` : htmx insere le fragment **dans** `#jeu`, il ne remplace
   pas `#jeu`. Le document assemble porte donc `<main id="jeu"><div id="jeu">`. C'est le chemin que
   `nadia-prof` decrit ("le `main` du gabarit et le `div` du fragment recu").
2. **Le gabarit se reinjecte dans lui-meme.** `accueil.html` s'ecrivait
   `<main id="jeu" class="container zone-jeu" th:fragment="zoneAccueil">` : le `th:fragment` porte
   **sur** le `<main>`, donc le fragment rendu par `POST /retour` contient la balise `<main>`
   elle-meme. Insere en `innerHTML` dans `#jeu`, il produit `<main id="jeu"><main id="jeu">` - et,
   accessoirement, empile deux fois `.container` et `.zone-jeu`, donc deux fois leur rembourrage.
   Ce second chemin n'avait ete nomme par personne.

Ce que ca coute reellement, et ce n'est pas cosmetique : la specification HTML fait de `id` un
identifiant **unique** dans le document. `hx-target="#jeu"` n'a plus de cible determinee - c'est le
premier element trouve qui gagne, et rien ne garantit lequel restera le premier apres un
remaniement de gabarit. Une aide technique qui suit un identifiant n'a plus de cible unique non plus,
ce qui est le besoin ecrit de S6-11.

### Defaut 2 - `GET /parties/{jeuId}/manche` sur une partie terminee rend un ecran mort

Constate par `nadia-prof` le meme jour. `PartieController.continuer` verifiait `etat.porte(jeuId)`
- le jeu correspond et une session existe - mais jamais le statut de cette session.
`EtatDePartie.porte` est vrai sur une session `TERMINEE` : seul `portePartieEnCours` regarde le
statut. Le rendu de `fragments/manche` s'ensuivait avec `enonce` vide et `propositions` vide, soit
la consigne, un `<p class="enonce"></p>` ouvert-ferme, et aucun bouton de reponse. L'enfant n'a plus
rien a faire de cet ecran.

**Ce chemin est-il atteignable par un enfant ? Reponse instruite, et elle n'est plus "non".**
Au 2026-08-07 la reponse ecrite etait "aucun chemin trouve". Elle etait juste sur le cas evident et
incomplete sur un autre. `GET /parties/{jeuId}/manche` n'est emis que par le formulaire "Encore !" de
`fragments/retour.html`, lui-meme rendu sous `th:if="${enSuite}"` : quand la partie se termine,
`enSuite` est faux et le bouton n'existe pas. **En un seul onglet, aucun chemin n'existe** - je le
confirme apres relecture, et j'ajoute que le rechargement n'en ouvre pas non plus, parce que
`GET /` exige `status() == EN_COURS` pour reprendre une partie et rend la liste des jeux sinon.

Mais l'etat de partie vit dans la **session HTTP**, donc il est partage par tous les onglets du meme
navigateur, alors que le bouton "Encore !" vit dans du **HTML deja rendu**, qui ne se met pas a jour
tout seul. Un enfant qui a deux onglets ouverts sur recreo - ou qui revient sur un onglet laisse de
cote - termine sa partie dans l'un, et l'autre affiche toujours un ecran de retour avec son bouton
"Encore !". L'appuyer emet exactement cette requete sur une session `TERMINEE`. Aucune URL tapee a la
main, aucun outil de developpeur : un appui sur un bouton affiche.

Je ne sais pas dire si un enfant de CP ouvre deux onglets, et ce n'est pas a moi de le dire. Ce que
je peux dire est ceci : **la propriete "ce chemin est inatteignable" ne repose pas sur le code, elle
repose sur une hypothese de comportement d'utilisateur.** Une telle propriete n'est pas gardable par
un automate, donc elle pourrira sans que personne le voie. Le controle coute trois lignes a l'endroit
ou toutes les routes passent deja. Je corrige, et je ne fais pas peser sur un futur relecteur la
charge de redemontrer une hypothese sur le nombre d'onglets.

### Ce que ces deux defauts ont en commun, et qui commande la decision

Le depot comptait, la veille de cet ADR, des tests sur `PartieController` et des tests d'integration
HTTP. **Aucun ne regardait le document que l'enfant a sous les yeux.** Chacun regardait soit la page
entiere rendue par `GET /` - ou aucun fragment n'a encore ete echange -, soit un fragment isole,
hors de la coquille qui le recoit.

Or l'ecran que voit l'enfant est **un troisieme objet** : la coquille permanente, moins le contenu de
la zone echangee, plus le fragment recu. Le defaut 1 vit exactement dans l'ecart entre le fragment
seul et l'assemblage - `<div id="jeu">` est irreprochable tant qu'on ne le regarde pas dans son
hote. Le defaut 2 vit dans l'ecart entre "la route repond 200 avec le bon gabarit", ce que les tests
verifiaient, et "cet ecran est jouable", que personne ne verifiait.

**C'est pourquoi un seul ADR, et non deux** : la decision n'est pas "corriger deux bogues", c'est
fixer l'unite de verification d'un ecran. Les deux defauts en sont les deux premieres applications.
Precedent de forme : ADR-0019, un geste et deux clauses.

## Decision

**L'unite de verification d'un ecran de recreo est le document assemble apres l'echange htmx. Un
ecran est juge sur cet objet, jamais sur le fragment seul ni sur la page avant echange.**

1. **Un identifiant est porte par un seul element du document assemble.** En consequence directe :
   **aucun fragment ne porte l'identifiant de la zone qui le recoit.** L'identifiant de la zone
   echangee est declare une seule fois dans tout le depot, par le `<main>` d'`accueil.html`.

2. **Un fragment qui est reinjecte dans la zone echangee ne contient pas la balise de cette zone.**
   Le fragment `zoneAccueil` est porte par un `th:block`, qui ne rend aucune balise. Poser
   `th:fragment` sur un element porteur de classes ou d'identifiant, quand ce fragment est destine a
   etre insere en `innerHTML` dans cet element meme, duplique tout ce que cet element porte.

3. **Un ecran de jeu n'est jamais rendu pour une partie terminee.** Le controle vit dans
   `mancheModele`, seul point de passage de l'ecran de jeu, et non dans chaque route : toute route qui
   demande cet ecran - celles d'aujourd'hui comme celles de demain - obtient l'ecran de fin de partie
   a la place. Symetrique du choix d'ADR-0019 clause 3, qui a ferme le meme cas cote commande.

4. **Aucun ecran ne rend un paragraphe vide.** Un `th:text` nourri d'une valeur absente produit une
   balise ouverte-fermee : c'est la forme que prenait l'ecran mort, et c'est la forme generale de "le
   modele n'a pas nourri ce que le gabarit annonce". Cette clause est le garde-fou de la clause 3 :
   elle interdit la **famille** du defaut, pas seulement son occurrence.

### Ce que l'enfant voit, et qui doit en juger

**Ce n'est pas ma decision.** La clause 3 change un ecran : la lecture d'une manche sur une partie
terminee montrait un ecran mort, elle montre desormais l'ecran de fin de partie, score compris. J'ai
retenu le comportement deja tranche par ADR-0019 pour le cas symetrique, plutot que d'en inventer un
second : deux chemins qui aboutissent au meme etat de partie montrent le meme ecran. `nadia-prof`
tranche si un autre ecran vaudrait mieux ; en attendant, le comportement est epingle par un test
nomme (`laLectureDUneMancheSurUnePartieTermineeRendLEcranDeFinDePartie`) pour qu'un changement d'avis
se voie dans un diff.

## Regle executable

### Pourquoi ce n'est pas une regle ArchUnit, et ce que ca serait de vouloir l'ecrire

ArchUnit importe des **classes compilees** et raisonne sur des dependances, des annotations, des
signatures. Aucune des quatre clauses ne porte sur une dependance : elles portent sur des octets de
HTML produits a l'execution par Thymeleaf, a partir d'un gabarit, d'un modele et d'un etat de
session. ArchUnit ne lit pas un gabarit et ne rend pas une vue.

On pourrait en approcher une par balayage textuel des gabarits - chercher `id=` dans les fichiers de
`templates/fragments/` et le refuser. Je l'ecarte, et le motif est le meme qui a fonde ADR-0015 :
**ce garde-fou deriverait de la mauvaise source.** Il jugerait le texte du gabarit alors que la
propriete porte sur le document assemble. Il serait aveugle a un identifiant produit par un
`th:attr`, aveugle au chemin 2 du defaut 1 (ou l'identifiant fautif est dans `accueil.html`, pas dans
un fragment), et il ne saurait rien dire des clauses 3 et 4. Un garde-fou qui manque le second des
deux chemins du defaut qui le motive est decoratif sur la moitie de son sujet.

### Le mecanisme automatique qui garde les quatre clauses

Fichier : `adapters/adapter-web/src/test/java/com/recreo/adapter/web/ToutEcranEchangeEstUnDocumentJouableTest.java`

Il assemble reellement le document, comme htmx le fait dans le navigateur : la coquille rendue par
`GET /`, dont le contenu de la zone echangee est remplace par le fragment que le serveur a rendu.
L'objet juge est le resultat de cet assemblage, jamais le fragment recu.

```java
    /** L'assemblage que htmx fait dans le navigateur : innerHTML de la zone remplace par le fragment. */
    private Document assembler(String fragment) throws Exception {
        Document coquille = Jsoup.parse(corps(get("/")));
        coquille.select(zoneEchangee(coquille)).html(fragment);
        return coquille;
    }
```

Deux clauses jugees sur chaque ecran du corpus, `aucunEcranNePorteDeuxFoisLeMemeIdentifiant` et
`aucunEcranNeRendUnParagrapheVide`, plus l'epinglage positif de la clause 3.

**Les trois obligations d'ADR-0013, et comment chacune est tenue** :

1. **Perimetre derive de la source.** La liste des routes vient de
   `RequestMappingHandlerMapping.getHandlerMethods()`, filtre sur `PartieController` - donc du
   controleur lui-meme, pas d'une enumeration ecrite dans le test.
   `chaqueRouteDuControleurEstExerceeParCeGardeFou` compare par **egalite** l'ensemble des routes
   exercees a l'ensemble des routes declarees : une route neuve que ce garde-fou n'exerce pas le rend
   **rouge**, au lieu de sortir du controle en silence. C'est la lecon du perimetre enumere, appliquee
   au perimetre de ce garde-fou meme. Le selecteur de la zone echangee est lui aussi lu dans la sortie
   du serveur (`hx-target`), jamais ecrit ici.

2. **Derivation totale, aucun cas ignore.** `zoneEchangee` echoue si la coquille declare plusieurs
   cibles `hx-target` distinctes **ou aucune** : le garde-fou ne saurait plus quoi assembler, et il
   le dit en rouge au lieu de choisir. `identifiantDeLaZone` echoue si la cible n'est pas un
   identifiant. `leCorpusDEcransNEstPasVide` ferme la non-vacuite dans les deux sens : un corpus vide,
   un ecran sans aucun identifiant (la clause 1 y serait vide), aucun paragraphe nulle part (la
   clause 2 y serait vide).

3. **Mise a l'epreuve.** Voir la section suivante.

### La preuve que ce garde-fou mord

Trois preuves, deux permanentes dans la suite et une par mutation reelle du code.

**Preuve 1, permanente, clause 1 - `unFragmentQuiReprendLIdentifiantDeLaZoneFaitEchouerLeGardeFou`.**
Elle ne construit aucune fixture. Elle prend le fragment **tel que le serveur vient de le rendre**,
lui repose l'identifiant de la zone sur sa racine, et exige que le garde-fou compte cet identifiant
deux fois. C'est l'etat du depot la veille de cet ADR, octet pour octet. Le motif de cette forme est
la lecon de s05 inscrite dans ADR-0015 : une fixture ecrite par l'auteur d'une regle ressemble a ce
qu'il imagine, la sortie de Thymeleaf ressemble a ce que le monde produit.

**Preuve 2, permanente, clause 4 - `unEcranDontLEnonceEstVideFaitEchouerLeGardeFou`.** Meme methode :
l'ecran de jeu reellement rendu, dont on vide l'enonce. Elle verifie d'abord que l'enonce existe et
que c'est bien un paragraphe - sans quoi la mutation ne muterait rien et le test passerait en
prouvant zero.

**Preuve 3, par mutation reelle du code, executee avant de commiter.** Les deux preuves ci-dessus
mutent une sortie ; celle-ci mute le depot. Les corrections ont ete retirees, une par une, et la
suite relancee :

Methode : une mutation a la fois, appliquee au depot, `mvn -B -pl adapters/adapter-web test` sur ce
seul garde-fou, puis depot restaure. L'ecran cite est celui que le message d'echec nomme.

| Mutation appliquee | Resultat attendu | Resultat mesure |
|---|---|---|
| `id="jeu"` remis sur `<div th:fragment="manche">` | rouge clause 1 | **rouge**, `aucunEcranNePorteDeuxFoisLeMemeIdentifiant`, ecran "manche 1" (`POST /parties/{jeuId}`) |
| `th:fragment="zoneAccueil"` remis sur le `<main id="jeu">` a la place du `th:block` | rouge clause 1 | **rouge**, meme test, ecran "retour explicite a la liste des jeux" (`POST /retour`) - c'est le chemin 2, celui que personne n'avait nomme |
| Le controle de statut retire de `mancheModele` (clause 3) | rouge clauses 3 et 4 | **rouge** sur `laLectureDUneMancheSurUnePartieTermineeRendLEcranDeFinDePartie` **et** sur `aucunEcranNeRendUnParagrapheVide`, ecran "lecture d'une manche sur une partie terminee" |

Un mot sur ce que ces messages ne disent pas, pour que personne ne leur prete plus qu'ils ne portent :
les deux clauses parcourent le corpus dans une boucle et **echouent sur le premier ecran fautif**.
Chacune des deux premieres mutations salit en realite plusieurs ecrans du corpus - la premiere touche
tous ceux qui rendent le fragment de manche - et le message n'en nomme qu'un. La mutation est prouvee
detectee, elle n'est pas prouvee detectee partout.

Le detail qui compte dans la troisieme ligne : la clause 4 attrape le defaut 2 **sans le nommer**.
Elle ne sait rien des parties terminees ; elle refuse un paragraphe vide, et l'ecran mort en portait
un. C'est ce qui fait qu'elle interdit la famille et pas seulement l'occurrence.

**Aucune de ces trois mutations n'a fait rougir un autre test du depot.** Methode de cette mesure,
parce qu'elle est la plus importante de la section : pour chacune des trois mutations, le fichier
`ToutEcranEchangeEstUnDocumentJouableTest.java` a ete **retire de l'arbre**, puis
`mvn -B -o clean verify` lance sur les cinq modules du pom agregateur. Resultat des trois :
**BUILD SUCCESS**, zero test rouge. C'est la mesure de l'angle mort - avant cet ADR, les deux defauts
pouvaient etre reintroduits sur un `mvn verify` vert, et le sont restes deux sprints.

## Consequences

- **Un garde-fou de plus qui demarre un contexte web** (`@WebMvcTest`), et qui joue une partie
  complete de bout en bout a chaque execution. Cout mesure sur ce depot : **2,6 a 3,1 s pour ses sept
  tests**, releve sur les executions de la section precedente. C'est le prix de juger l'objet reel
  plutot qu'un substitut.
- **Le corpus d'ecrans est ecrit a la main, et c'est la faiblesse assumee de ce garde-fou.** Les
  **routes** sont derivees et leur oubli est rouge ; les **etats** d'une meme route ne le sont pas.
  `GET /` est exerce dans deux etats (avec et sans partie en cours), `POST /coups` dans trois, et
  rien ne prouve qu'ils soient tous. Un etat non exerce sort du controle en silence. Je ne sais pas
  deriver l'ensemble des etats d'une route sans ecrire un explorateur qui serait plus complexe que ce
  qu'il garde, et je prefere nommer la limite que l'habiller. Ce qui la reduit : l'egalite sur les
  routes force au moins un passage par chacune.
- **La clause 4 est plus large que le defaut qu'elle ferme, et elle genera un jour.** Un ecran qui
  voudrait legitimement un paragraphe vide - un emplacement reserve a un message absent - la ferait
  rougir. C'est voulu : ce depot a paye deux fois le garde-fou taille sur l'occurrence exacte. Le jour
  ou ce besoin arrive, il se traite en nommant l'element concerne dans une exemption qui se voit dans
  un diff, pas en supprimant la clause.
- **Une contrainte permanente sur l'ecriture des gabarits** : `th:fragment` ne se pose plus sur un
  element destine a recevoir son propre fragment. Elle est ecrite en commentaire a l'endroit exact ou
  la faute a ete commise, dans `accueil.html`, et gardee par la clause 1.
- **Trois lignes de plus dans `PartieController`** et une methode privee extraite (`retourModele`),
  qui existait deja en double dans `jouer`. Le controleur perd une duplication au passage.
- **Le rembourrage double du chemin 2 disparait**, ce qui touche a la matiere de S6-8 (la marge
  laterale sous 1200 px) sans la traiter : S6-8 porte sur `.zone-jeu { padding: 2rem 0 }`, une
  declaration, pas sur un empilement. Je le signale pour que le lot de S6-8 ne croie pas la
  regression deja fermee.

## Alternatives considerees

1. **Ne rien faire sur le defaut 2, en actant qu'il n'est pas atteignable par un enfant.** C'etait
   l'option ouverte par `DEC-0013` point 3, et je l'ai instruite avant de l'ecarter. Motif du rejet
   ecrit plus haut : l'inatteignabilite ne repose pas sur le code mais sur une hypothese de nombre
   d'onglets, donc rien ne peut la garder, donc elle pourrira sans bruit. Trois lignes valent mieux
   qu'une hypothese non gardable.
2. **Poser le controle de statut dans `continuer` plutot que dans `mancheModele`.** Rejete : c'est un
   perimetre enumere par route. La lecon du `@ExceptionHandler` - nommer deux types n'est pas nommer
   deux situations - vaut aussi pour les routes. Le point de passage unique couvre la route de demain.
3. **Renommer l'identifiant des fragments plutot que le retirer** (`id="jeu-contenu"` par exemple).
   Rejete : ca ferme le symptome mesure et laisse la faute. Deux identifiants dans le document, dont
   un que rien ne cible, est une invitation a se tromper de cible au prochain gabarit. La clause 1
   interdit la duplication, quel que soit l'identifiant.
4. **Un garde-fou par balayage textuel des gabarits.** Rejete, motif dans la section "Regle
   executable" : mauvaise source, aveugle au chemin 2 et aux clauses 3 et 4.
5. **Deux ADR separes, un par defaut.** Rejete : la decision utile est l'unite de verification, et
   elle est commune. Deux ADR auraient produit deux garde-fous qui assemblent chacun le meme
   document, et aucun des deux n'aurait dit pourquoi les tests existants n'avaient rien vu.

## Ce qui reste non gardable, et le dit

- Que le document assemble par ce garde-fou soit bien celui que htmx produit dans un vrai navigateur.
  L'assemblage est une reimplementation de `hx-swap="innerHTML"` en trois lignes de Jsoup. Elle est
  fidele pour `innerHTML`, et **elle est fausse pour tout autre `hx-swap`** : le jour ou un gabarit
  utilise `outerHTML` ou `beforeend`, ce garde-fou assemblera le mauvais document en restant vert.
  Il n'y a aujourd'hui aucun autre `hx-swap` dans le depot ; ce que je n'ai pas ecrit, faute de
  savoir le faire sans arbitraire, est la regle qui refuserait un `hx-swap` que l'assemblage ne sait
  pas imiter. **C'est une dette explicite, et c'est le mode de defaillance le plus dangereux de ce
  garde-fou** : un vert qui ne prouve plus rien.
- Que l'ecran de fin de partie soit le bon ecran pour un enfant qui arrive par la lecture d'une
  manche terminee. Porte pedagogique de `nadia-prof`, comme pour ADR-0019 clause 3.
