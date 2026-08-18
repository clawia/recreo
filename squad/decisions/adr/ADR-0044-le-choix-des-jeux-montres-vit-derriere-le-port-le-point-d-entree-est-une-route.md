# ADR-0044 - Loger le choix des jeux montres derriere le port entrant, faire du point d'entree une route et non un parametre, et refuser tout etat de filtre dans l'en-tete permanent

- **Statut** : Accepte, **amende le 2026-08-18 (s15) : la premiere phrase du point 3 tombe, sa branche
  conditionnelle devient l'etat de l'application, et le cout que j'y avais chiffre est du**
- **Date** : 2026-08-17
- **Auteur** : theo-archi
- **Sprint** : s14 (avis de faisabilite S14-1 ; contrat de controleur signale comme manquant par
  `ines-design`, section 8 de `proposition-ines-barre-de-niveaux.md`)
- **Complete** : `ADR-0006` (ajouter un niveau est une donnee), `ADR-0022` (un habillage permanent
  n'est pas re-rendu par un echange htmx), `ADR-0038` (le perimetre derive des routes du controleur),
  `DEC-0028` (aucune persistance)
- **Regles executables**, ecrites dans le commit de cet ADR :
  - `bootstrap/src/test/java/com/recreo/archi/LeChoixDesJeuxResteDerriereLePortTest.java`
    (**regle ArchUnit**, la 29e du depot), eprouvee par
    `LesReglesMordentTest.uneVueQuiFiltreLeCatalogueParNiveauEstRefusee` ;
  - `adapters/adapter-web/src/test/java/com/recreo/adapter/web/LeChoixDesJeuxNeVitPasDansLaVueTest.java`
    (le versant que le bytecode ne voit pas), avec son jeu fautif.

> **Amendement du 2026-08-18 (s15) - le point 3 avait deux moities, une affirmation et une branche
> conditionnelle. L'affirmation tombe ; la branche s'active ; et c'est moi qui avais ecrit les deux, avec
> le prix de la seconde.**
>
> L'utilisatrice a rejoue l'increment et demande que **la puce pressee prenne une couleur** pour dire quel
> niveau est choisi, et que cet etat **cesse quand le jeu demarre**. Elle est l'autorite au-dessus de la
> squad et l'arbitrage ne se rediscute pas.
>
> **Clause par clause, ce qui tombe et ce qui survit.**
>
> - *"L'en-tete permanent ne porte aucun etat que le serveur ne peut pas rafraichir"*, et le corollaire
>   *"l'etat filtre est annonce dans la zone echangee"* : **tombent**. C'etait le choix par defaut du
>   point 3, pas un fait technique.
> - *"Aucun echange htmx n'atteint l'en-tete"* : **survit, c'est un fait**, et c'est lui qui gouverne tout
>   le reste. Il force la solution a se scinder en deux moities, parce que marquer une puce active et
>   effacer cet etat au demarrage du jeu n'ont pas le meme besoin de rafraichissement.
> - *"Si `nadia-prof` juge qu'un enfant a besoin de voir dans l'en-tete sur quel niveau il est, alors - et
>   seulement alors - chaque appui sur une puce devient un rendu de document complet, sans echange htmx.
>   Le cout est nomme : un chargement de page a chaque changement de niveau."* : **cette branche est
>   desormais l'etat de l'application**. Le declencheur a joue - depuis l'utilisatrice plutot que depuis
>   `nadia-prof`, ce qui ne change rien a son contenu - et le cout que j'avais chiffre est du. **Le point 3
>   n'a pas ete refute : sa branche minoritaire est devenue la majoritaire.**
> - **Point 4, *"le niveau demande voyage dans l'URL, jamais dans un attribut de session"* : survit
>   entierement.** L'etat actif est calcule au rendu depuis la variable de chemin, jamais conserve.
>   `DEC-0028`, `ADR-0019` et `AccesALEtatDeSessionTest` restent intacts, et c'est ce qui rend cet
>   amendement peu couteux.
> - **Points 1 et 2 : intacts.** La selection vit toujours derriere le port entrant, et le point d'entree
>   reste une route - c'est meme cette route qui porte desormais l'etat actif, donc le point 2 est ce qui
>   rend la voie retenue possible.
>
> **Les deux voies que le point 3 ecartait restent ecartees, et l'une pour un motif aggrave.** La regle CSS
> par niveau contredit toujours `ADR-0006` frontalement. `hx-swap-oob` est pire qu'en s14 : le garde-fou
> d'`ADR-0020` reimplemente `hx-swap="innerHTML"` en posant le fragment **dans** la zone, donc un en-tete
> hors bande y serait assemble imbrique, et le document assemble est desormais la matiere des quatre
> clauses d'`ADR-0038`, de l'appariement d'`ADR-0017` et de la clause D. Un assemblage faux les
> corromprait toutes en restant vert.
>
> **Ce que cet amendement n'ouvre pas** : la forme contractuelle du masquage d'`ADR-0022` **n'est pas
> elargie**. La regle qui neutralise l'apparence active sur un ecran de jeu ne masque rien et ne doit pas
> etre lue comme un masquage : trois garde-fous lisent `masquages()` en y cherchant ce qui est invisible,
> et y faire entrer une neutralisation d'apparence leur ferait calculer un document faux en restant verts.
>
> Le mecanisme retenu, ses deux moities, les deux clauses dues au lot et le releve sont dans
> `squad/sprints/s15-cinq-arbitrages-de-l-en-tete/exigences-theo-etat-actif.md`.

## Contexte

`S14-1` demande que la liste des jeux se filtre sur un niveau. `ines-design` signale que deux choses
manquent au contrat et refuse de les inventer depuis un gabarit : **le parametre de niveau courant** a
lire pour marquer la puce active, et **la route** qu'un appui declenche pendant une partie.

Quatre faits du depot, mesures et non supposes, cadrent la reponse.

**1. La donnee existe et personne ne la lit.** `ContentDescriptor(matiere, niveau, domaine,
competence)` est porte par chaque `GameDefinition` depuis s00. Recherche sur les sources de
production des cinq modules : **`niveau()` et `matiere()` ne sont appeles nulle part**. Le filtre de
`S14-1` sera le premier lecteur de ce champ. Il n'y a donc rien a casser, et rien a deduire d'un
usage anterieur.

**2. Le catalogue porte deux niveaux distincts, et ce nombre n'est pas une constante.** Methode :
valeurs de `niveau` des paquets references par une entree `- id:` de
`adapters/adapter-content/src/main/resources/catalogue.yaml`, comptees sur `e30566b` - **11 entrees**,
6 sur `"CP"`, 5 sur `"CM1"`. Le douzieme paquet du repertoire (`cm1-homophones`) n'a pas d'entree ;
c'est un retrait de catalogue deja acte, pas un oubli. `ADR-0006` pose qu'ajouter un niveau est **une
donnee, jamais un enum** : deux boutons ecrits en clair dans un gabarit contrediraient cette
decision, et le critere d'acceptation qui dit "deux boutons, CP et CM1" invite exactement a les
ecrire.

**3. Le perimetre du garde-fou de tabulation derive des routes, pas des parametres.**
`AucunArretDeTabulationNEstDestructeurTest.chaqueRouteDuControleurEstExerceeParCeGardeFou` lit les
routes dans `RequestMappingHandlerMapping` et exige que **chacune** produise au moins un ecran du
corpus, par une comparaison exacte. Une route neuve rend donc la suite rouge tant que personne ne
l'exerce - c'est la friction voulue par `ADR-0038`. Un **parametre de requete** sur une route
existante (`GET /?niveau=cp`) n'ajoute aucune route : il entre en silence, et aucun ecran du corpus
n'est ajoute pour lui.

**4. Aucun echange htmx n'atteint l'en-tete.** C'est le contexte d'`ADR-0022`, mot pour mot : tous
les declencheurs posent `hx-target="#jeu"`, l'en-tete est le frere de cette zone, aucune reponse du
serveur ne le traverse. Un `th:if` sur un element de l'en-tete "ne vaudrait que pour un chargement de
page complet, et laisserait l'en-tete perime apres chaque echange htmx". **Un marqueur de niveau
courant pose dans l'en-tete est donc faux par construction des le premier echange**, et c'est le
defaut exact qu'`ADR-0022` a refuse pour l'entree de menu.

## Decision

**1. Le choix des jeux montres vit derriere le port entrant.** `PartieUseCase` gagne deux methodes,
implementees par `PartieService` dans le module `application` :

```java
List<String> niveauxDuCatalogue();

List<GameDefinition> jeuxDuNiveau(String niveau);
```

- `niveauxDuCatalogue()` rend les valeurs de `niveau` **distinctes**, dans l'ordre du catalogue. C'est
  cette methode, et rien d'autre, qui dit combien de points d'entree l'en-tete porte : un niveau qui
  entre au catalogue fait apparaitre son bouton sans qu'aucune ligne de gabarit ni de controleur
  change. C'est `ADR-0006` rendu executable au lieu d'etre seulement ecrit ;
- `jeuxDuNiveau(String)` rend le sous-ensemble du catalogue. `jeuxDisponibles()` **ne change pas** et
  reste la vue non filtree : la story ajoute un filtre, elle n'en retire pas.

**Aucun port sortant neuf.** `ContentCatalogPort.games()` rend deja tout ; selectionner est une regle
de catalogue, pas une capacite de source de contenu. **Aucun changement dans `domain`** : le champ
existe, et le domaine continue de ne connaitre aucun vocabulaire de matiere ni de niveau
(`AucunVocabulaireDeMatiereTest`, dont le perimetre - `domain/mechanics` et le paquet racine de
`domain/content` - n'est ni touche ni etendu par cette decision).

**Pourquoi deux methodes et non une avec un parametre facultatif.** `jeuxDisponibles(String niveau)`
ou `null` vaudrait "tous" ferait d'une absence une valeur, et ce depot a une dette nommee sur ce
motif exact (dette 2 de la version precedente : des valeurs factices pour dire "cela ne me concerne
pas"). Deux methodes, deux sens, aucun sentinel.

**Pourquoi dans `application` et non trois lignes de `.filter` dans le controleur.** Ce n'est pas la
quantite de code qui tranche - le filtre fait une ligne des deux cotes - c'est **ce que l'adaptateur
saurait**. Un controleur qui filtre sait deux choses : ou un jeu range son niveau, et quels niveaux
existent. La seconde est celle qui coute : elle transforme "ajouter un niveau" en "modifier
l'adaptateur web". La regle ArchUnit ci-dessous encode cette frontiere-la, et pas une preference de
mise en page du code.

**2. Le point d'entree par niveau est une route, jamais un parametre de requete.** Le fait 3 le
decide seul : une route neuve entre dans le perimetre du garde-fou de tabulation **par derivation**,
un parametre y entre en silence. Le choix du chemin exact appartient au lot ; ce que cet ADR exige
est qu'il soit un **chemin**, de la forme `/niveaux/{niveau}`, et que la valeur du niveau soit une
variable de chemin et non un litteral repete par route.

**3. L'en-tete permanent ne porte aucun etat que le serveur ne peut pas rafraichir.** Il porte les
points d'entree ; il ne porte pas la marque du niveau courant. L'etat "cette liste est filtree sur
tel niveau" est annonce **dans la zone echangee**, la ou le serveur gouverne le rendu a chaque
reponse. C'est la reponse directe a la question d'`ines-design` : **il n'y a pas de parametre de
niveau courant a lire dans l'en-tete, et ce n'est pas un oubli de contrat, c'est une impossibilite
structurelle** (fait 4).

Si `nadia-prof` juge qu'un enfant a besoin de voir dans l'en-tete sur quel niveau il est, alors -
et seulement alors - **chaque appui sur une puce devient un rendu de document complet**, sans echange
htmx, ce qui rafraichit l'en-tete gratuitement. Le cout est nomme : un chargement de page a chaque
changement de niveau, la ou tout le reste de l'application echange un fragment. Les deux formes sont
possibles, aucune n'est gratuite, et le choix est pedagogique et non technique. Les deux voies
ecartees le sont, elles, pour des motifs techniques : `hx-swap-oob` reste refuse par `ADR-0022`
(il declenche la dette explicite d'`ADR-0020`), et un selecteur relationnel par niveau
(`body:has(.niveau-cp) .puce-cp`) demanderait **une regle CSS par niveau**, ce qui contredit
`ADR-0006` frontalement - ajouter un niveau cesserait d'etre une donnee.

**4. Le niveau demande voyage dans l'URL, jamais dans un attribut de session.** `DEC-0028` : rien
n'est conserve entre deux sessions, et l'etat d'une partie vit dans la session HTTP. Un filtre de
navigation n'est pas un etat de partie : il est porte par la requete qui le demande. Consequence
concrete : `EtatDePartieEnSession` **n'est pas touche**, donc `ADR-0019` et
`AccesALEtatDeSessionTest` non plus, et le lot n'a aucun cycle lire-calculer-ecrire neuf a rendre
indivisible.

## Regles executables

### 1. La regle ArchUnit - la 29e du depot

Fichier : `bootstrap/src/test/java/com/recreo/archi/LeChoixDesJeuxResteDerriereLePortTest.java`

```java
@ArchTest
static final ArchRule aucunAdaptateurDeRenduNeLitLeDescripteurDeContenu = noClasses()
        .that().resideInAnyPackage("com.recreo.adapter.web..", "com.recreo.bootstrap..")
        .should().dependOnClassesThat()
        .haveFullyQualifiedName(DESCRIPTEUR)
        .as("ADR-0044 : le choix des jeux montres vit derriere le port entrant, jamais dans "
                + "l'adaptateur de rendu ni dans le cablage");
```

**Ce qu'elle interdit reellement** : le chemin le moins cher, et rien d'autre. Un
`.filter(jeu -> jeu.descripteur().niveau().equals(niveau))` dans `PartieController` compile, marche,
et passe toutes les autres regles du depot. Mesure du 2026-08-17, sur la production mutee et non sur
une fixture : cette methode ajoutee a `PartieController`, `bash outils/mvn-jdk21.sh -B -o -pl
bootstrap -am test -Dtest=LeChoixDesJeuxResteDerriereLePortTest`, JDK 21.0.10 - **1 echec sur 1**,
message *"Method `PartieController.jeuxDuNiveau(java.lang.String)` calls method
`ContentDescriptor.niveau()` in (PartieController.java:310)"*. Arbre restaure, `git status
--porcelain` vide.

**Pourquoi une dependance de type et non un appel de methode nomme.** Un critere sur `niveau()`
enumererait un des quatre accesseurs et manquerait le cinquieme champ ajoute demain - *un perimetre
enumere echoue par omission autant que par inclusion*. La dependance de type ferme les quatre a la
fois sans en nommer aucun.

**Perimetre, et pourquoi `adapter-content` en est exclu.** Ce n'est pas une exemption concedee : c'est
lui qui **construit** le descripteur en lisant le YAML (`ADR-0007`), il ne peut pas ignorer ce type.
`bootstrap` y entre parce qu'il n'a aucun motif d'en lire un : il cable, il ne selectionne pas.

**Mise a l'epreuve permanente** : `LesReglesMordentTest.uneVueQuiFiltreLeCatalogueParNiveauEstRefusee`
sur `VueQuiFiltreLeCatalogueContreExemple`, ecrit comme le chemin le moins cher l'ecrirait vraiment -
le catalogue recu par le port, puis filtre dans l'adaptateur.

### 2. Le versant que le bytecode ne voit pas

Fichier : `adapters/adapter-web/src/test/java/com/recreo/adapter/web/LeChoixDesJeuxNeVitPasDansLaVueTest.java`

**Nomme avant d'etre decouvert** : un gabarit Thymeleaf n'est pas du bytecode.
`th:if="${jeu.descripteur().niveau() == niveauChoisi}"` echapperait entierement a ArchUnit, et c'est
le **second** chemin le moins cher vers le meme defaut. Cette clause refuse qu'un gabarit nomme
`descripteur`, dans la valeur d'un attribut ou dans son texte. Les commentaires en sont exclus a
dessein : un commentaire qui explique pourquoi la vue ne lit pas le descripteur ne doit pas rougir a
cause du mot qu'il cite.

**Perimetre derive** : balayage de `templates/`, jamais une liste de gabarits. **Derivation totale** :
aucun gabarit releve, ou aucun gabarit qui lise le moindre attribut du modele, sont des **echecs** -
le second parce qu'une application dont les vues ne lisent aucun modele n'a rien a garder ici, et que
ce vert-la ne doit pas passer pour une garantie. **Mise a l'epreuve** : `filtre-dans-la-vue`, un
gabarit qui ecrit le filtre par niveau avec la syntaxe que Thymeleaf demande reellement ; rouge
mesure, message *"lit le descripteur de contenu dans th:if"*.

**Un seul accesseur nomme, et c'est voulu** : `descripteur` est le seul chemin par lequel une vue peut
atteindre la matiere, le niveau, le domaine ou la competence. Le fermer ferme les quatre sans en
enumerer aucun.

### Ce que ces deux regles ne gardent pas

- **Que le filtre soit juste.** Qu'un jeu de CM1 n'apparaisse pas sous CP est une propriete du calcul,
  pas de sa localisation ; c'est au lot de l'asserter sur `PartieService`, et l'avis de faisabilite de
  s14 dit sous quelle forme.
- **Que les puces soient derivees du catalogue plutot qu'ecrites en clair.** Les deux regles
  ci-dessus interdisent que la vue **lise le descripteur** ; elles ne peuvent pas interdire qu'un
  gabarit ecrive `CP` et `CM1` en litteral avec deux `href` fixes. Cette propriete-la est gardable, et
  elle l'est par une clause du lot que l'avis de faisabilite specifie : le nombre de points d'entree
  rendus dans l'en-tete est egal au nombre de niveaux distincts que `niveauxDuCatalogue()` rend.
  **Dette nommee tant que le lot n'a pas livre**, avec son declencheur : le commit qui pose la barre.
- **Qu'un troisieme champ du descripteur ne fuie pas par une valeur deja extraite.** Un controleur qui
  passerait `niveauDuJeu` au modele sous un autre nom passerait vert des deux cotes - le defaut serait
  alors dans le controleur, ou la regle ArchUnit l'attend, et non dans la vue.

## Consequences

- **Le lot recoit son contrat avant d'ecrire une ligne**, ce qui est l'ordre que ce projet s'est donne
  apres avoir jete un lot en s13.
- **Une famille d'implementations est desormais refusee par la CI** : le catalogue filtre dans
  l'adaptateur web ou dans le cablage, par le code comme par le gabarit. Le message nomme la methode
  et la ligne.
- **Le port entrant grossit de deux methodes.** C'est le cout, et il est reel : `PartieUseCase`
  passait de trois a cinq methodes, et une interface qui grossit a chaque story finit par ne plus rien
  dire. Ce qui rend ces deux-la acceptables : elles repondent a un usage **constate** dans la story du
  jour, pas anticipe, et elles suppriment deux savoirs de la couche externe. Ce qui me ferait changer
  d'avis : une troisieme facon de selectionner (par matiere en s15, puis par autre chose) qui rendrait
  la liste de methodes absurde. Il faudrait alors un seul point d'entree prenant un critere - et ce
  critere devrait etre une donnee du catalogue, jamais un enum.
- **La forme "un rendu de document complet par appui" reste ouverte.** Elle n'est ni prescrite ni
  interdite ici : elle depend de l'arbitrage de `nadia-prof` sur l'etat actif, et son cout est ecrit
  au point 3.
- **Mauvaise consequence assumee** : la regle ArchUnit est plus large que la propriete visee. Elle
  interdit **toute** lecture du descripteur par l'adaptateur web, y compris une lecture legitime -
  afficher le niveau a cote d'un titre de jeu, par exemple. Le jour ou une vue en aura besoin, la
  bonne reponse ne sera pas d'assouplir la regle mais de preparer la valeur du cote application ;
  c'est ecrit ici pour que la question ne se reglee pas par un `@ArchIgnore`.

## Alternatives considerees

1. **Filtrer dans le controleur.** Ecartee au point 1 de la decision : le cout n'est pas le code,
   c'est le savoir deplace dans la couche externe, et il contredit `ADR-0006`.
2. **Un parametre de requete sur `GET /`** (`/?niveau=cp`). Ecartee sur un fait verifie :
   `chaqueRouteDuControleurEstExerceeParCeGardeFou` compare des **routes**. Un parametre n'ajoute
   aucune route, donc n'ajoute aucune obligation d'ecran au corpus d'`ADR-0038` - et un ecran a partie
   en cours qu'aucun scenario ne construit n'est juge par rien (non-preuve 8 d'`ADR-0038`). La forme
   qui achete du silence au garde-fou est ecartee pour cette seule raison ; elle etait sinon la plus
   courte.
3. **Un attribut de session pour le niveau courant.** Ecartee : `DEC-0028` ne conserve rien, un filtre
   de navigation n'est pas un etat de partie, et tout acces a la session doit passer par
   `EtatDePartieEnSession` (`ADR-0019`). Cela ajouterait un cycle a rendre indivisible pour une
   information que l'URL porte deja.
4. **`hx-swap-oob` pour rafraichir l'en-tete.** Ecartee, et le refus n'est pas neuf : `ADR-0022` l'a
   ecarte parce qu'il declenche immediatement la dette explicite d'`ADR-0020` - dont le garde-fou
   reimplemente `hx-swap="innerHTML"` et **assemblerait le mauvais document en restant vert**. Rien
   n'a change de ce cote.
5. **Un selecteur relationnel par niveau pour l'etat actif.** Ecartee : une regle CSS par niveau fait
   d'un ajout de niveau une modification de feuille de style, ce qu'`ADR-0006` refuse. Le mecanisme
   d'`ADR-0022` ne se generalise pas ici, et c'est le seul endroit de ce lot ou il ne se generalise
   pas.
6. **Un enum `Niveau` dans le domaine.** Ecartee sans hesiter : `ADR-0006` l'a deja tranche en s00, et
   c'est la dette 2 de la version precedente.
7. **Ecrire les deux regles plus tard, avec le lot.** Ecartee pour le motif qu'`ADR-0043` developpe :
   `ADR-0042` a confie sa regle a un lot annule, et cette regle n'existe toujours pas.

## Ce qui reste non gardable, et le dit

- **Que le filtre serve a un enfant.** Qu'un enfant de CP trouve son niveau, comprenne qu'une liste
  est filtree et sache revenir a la liste complete : `nadia-prof`, et rien ici n'y touche.
- **Que l'en-tete reste lisible avec deux puces de plus.** C'est un cout de rendu, mesure par
  `ines-design` (+66 px a deux puces) et non gardable par un automate de ce depot (`ADR-0028`).
- **Que `niveauxDuCatalogue()` rende des valeurs presentables.** "CP" et "CM1" sont des donnees de
  contenu ; leur graphie est une question de redaction pour un enfant (`DEC-0002`), pas une propriete
  d'architecture. La question voisine de la matiere accentuee est instruite dans l'avis de
  faisabilite de s14, pour s15.
