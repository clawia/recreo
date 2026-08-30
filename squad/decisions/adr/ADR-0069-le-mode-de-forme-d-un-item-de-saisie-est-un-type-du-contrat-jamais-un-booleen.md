# ADR-0069 - Le mode de forme d'un item de saisie est un type du contrat de contenu, jamais un booleen

- **Statut** : Accepte. **La regle qui le tient existe et est verte depuis s01** - `ADR-0006`,
  `DifficulteTest.leContratDeContenuNePorteQueDuTexte`. Ce lot lui ajoute, dans le meme commit, la
  **morsure qui manquait sur la forme non chiffree** et son jeu fautif, sans quoi la portee reelle de
  la regle restait a decouvrir par un lot de code.
- **Sprint** : s24. **Date** : 2026-08-30. **Auteur** : theo-archi.
- **Arbre mesure** : worktree `s24-theo`, branche `round-two` a `ea546cf`. Toutes les mesures de cet
  `ADR` sont faites sur cet arbre, sur le JDK **21.0.10** - version que la machine virtuelle des tests rapporte d'elle-meme, jamais
  celle qu'une toolchain declare (`ADR-0071`) - par
  `bash outils/mvn-jdk21.sh`.
- **Corrige** : `ADR-0058` section 1, clause *"un booleen `formeExacteExigee`"*. Le corps
  d'`ADR-0058` n'est pas reecrit ; il recoit un quatrieme amendement date qui renvoie ici.
- **Prolonge** : `ADR-0006` (le contrat de contenu ne porte que du texte), `ADR-0004` (les deux
  contrats fermes), `ADR-0039` (une propriete calculee plutot qu'une enumeration).
- **Ne code aucune mecanique, ne prononce aucun Done.**

## Contexte

`ADR-0058` a concu la mecanique de saisie sans qu'aucune capacite de developpement y soit affectee,
a s19 puis a s20. Sa section 1 decide la forme de l'item neuf et tranche explicitement une question
de forme :

> *"**Pourquoi un booleen et pas un enum a deux constantes** : `nadia-prof` nomme deux etats et deux
> seulement, et un enum de deux constantes est une piece mobile de plus sans usage constate."*

Ce raisonnement est bon et je le maintiens **comme raisonnement**. Il a un defaut qui n'est pas de
raisonnement : **il n'a jamais ete confronte aux regles vertes du depot.** `ADR-0058` a mesure ce
qu'il fallait mesurer sur les contrats fermes (deux sondes, section 4) et n'a pas mesure la forme
qu'il decidait sur le contrat de contenu.

**La mesure, faite le 2026-08-30 et non deduite.** J'ai pose dans l'arbre l'item que la section 1
decrit - `record ReponseAEcrire(ItemId, String enonce, String formeDeReference,
List<String> formesAcceptees, Optional<String> indice, Optional<String> explication,
boolean formeExacteExigee) implements Item` - et lance
`bash outils/mvn-jdk21.sh -B -o -pl bootstrap -am test -Dtest=DifficulteTest
-Dsurefire.failIfNoSpecifiedTests=false`. **3 tests, 1 echec**, message integral :

```
Architecture Violation [Priority: MEDIUM] - Rule 'ADR-0006 : le contrat de contenu porte du texte,
jamais des bornes de matiere' was violated (1 times):
com.recreo.domain.content.ReponseAEcrire.formeExacteExigee cite le type boolean, qui n'est ni du
texte, ni une collection du JDK, ni un type du contrat de contenu
```

Le `-am` est porte parce que la mesure est partielle (`CLAUDE.md`, prerequis de build) ; le
perimetre est le module `bootstrap` et ses amonts, pas le depot.

**La meme sonde donne deux autres faits, que je verse ici parce qu'ils dimensionnent le lot et qu'on
les croirait plus grands qu'ils ne sont.**

1. **Ouvrir la couture scellee coute exactement deux arrets de compilation**, pas plus, et les deux
   sont a l'endroit exact ou une mecanique suppose qu'il n'existe qu'une forme d'item :
   `BanditManchotSession.java:165` (production, le `switch` de `questionDe`) et
   `PropositionsAccentueesJouablesIT.java:171` (test de `bootstrap`). C'est la couture d'`ADR-0004`
   qui fait son travail : elle ne laisse pas la seconde forme entrer en silence. Les
   **transtypages** `(Question) item` des garde-fous de contenu, eux, compilent sans un mot - ils
   sont un cout de paquet, pas un cout de type, et je les chiffre dans l'avis de faisabilite.
2. **Une fois ces deux arrets leves et le booleen remplace par un enum du paquet de contrat, la
   phase `test` du reacteur est verte** : `bash outils/mvn-jdk21.sh -B -o -pl bootstrap -am test`,
   **BUILD SUCCESS, 0 echec** (perimetre : les cinq modules declares, surefire seul - la phase
   `test` n'execute pas les tests d'integration de failsafe). Aucun autre garde-fou ne rougit sur
   l'apparition de la seconde forme d'item, **tant qu'aucun paquet de saisie n'est pose sur disque**.

## Decision

### 1. Le mode de forme est un type du paquet de contrat de contenu

L'item de saisie declare le mode que `SAI-2a` exige par une **composante dont le type vit dans
`com.recreo.domain.content`** - un enum a deux constantes, `FORME_LIBRE` et `FORME_EXACTE`. Jamais
un booleen, jamais une grandeur, jamais une chaine libre.

**Mesure de la forme retenue, sur le meme arbre et la meme commande** : le meme item avec cette
composante a la place du booleen rend **3 tests, 0 echec, BUILD SUCCESS**. La forme decidee ici est
verte, la forme decidee par `ADR-0058` est rouge, et les deux sont mesurees sur le meme arbre le
meme jour.

**Ce que la regle tient, et ce qu'elle ne tient pas.** `leContratDeContenuNePorteQueDuTexte` admet
un type du paquet de contrat ; elle tient donc *"ni booleen ni grandeur"*, elle ne tient pas *"un
enum plutot qu'un record"*. Je l'ecris plutot que de laisser croire que la regle porte plus qu'elle
ne porte. Ce que le nom du type sera n'est pas non plus tenu par une regle : `ExigenceDeForme` est
la proposition que je fais au lot, `ModeDeComparaison` en est une autre. Le nom n'est pas ce qui se
decide ici.

**Le nom n'est pas indifferent pour autant, et c'est un point de conception.** Le contrat de contenu
declare **ce que l'exercice exige de la forme de la reponse** ; il ne declare pas comment une
mecanique s'y prend pour comparer. Un nom qui parle de comparaison ferait dire au contenu ce que la
mecanique fait, la ou `ADR-0004` tient les deux separes.

### 2. Ce que cette decision ne change pas

- **`SAI-2a` est servie exactement comme `ADR-0058` section 2 le decide** : le mode vit sur l'item,
  la commodite "au niveau du paquet, sinon au niveau de l'item" est un fait de schema lu par la
  fabrique, et le code ne le deduit jamais de la matiere ni du niveau.
- **Les sections 3 a 8 d'`ADR-0058`, ses trois amendements, `R-A`, `R-B`, `R-C` et `R-D` sont
  intacts.** Une seule phrase de la section 1 est corrigee.
- **Le declencheur de reouverture ecrit par `ADR-0058`** - *"un troisieme mode reellement demande
  par un paquet"* - n'est pas ce qui a joue. Ce qui a joue est une regle verte, et la difference
  compte : la conception n'a pas ete revue parce qu'on a imagine un besoin, elle l'a ete parce
  qu'une mesure l'a refusee.

## Regle executable

### Deja ecrite et verte, c'est elle qui tient la decision

**Fichier** : `bootstrap/src/test/java/com/recreo/archi/DifficulteTest.java`, champ
`leContratDeContenuNePorteQueDuTexte`. Aucune ligne n'y est modifiee par ce lot.

### Ecrite dans le meme commit que cet ADR - la morsure qui manquait

**Fichier** : `bootstrap/src/test/java/com/recreo/archi/LesReglesMordentTest.java`,
`unModeDeComparaisonEnBooleenDansLeContratDeContenuEstRefuse`. **Jeu fautif** :
`bootstrap/src/test/java/com/recreo/domain/content/ModeDeSaisieEnBooleenContreExemple.java`.

```java
assertThatThrownBy(() -> DifficulteTest.leContratDeContenuNePorteQueDuTexte
        .check(contreExemples("com.recreo.domain.content")))
        .isInstanceOf(AssertionError.class)
        .hasMessageContaining("com.recreo.domain.content.ModeDeSaisieEnBooleenContreExemple.formeExacteExigee")
        .hasMessageContaining("boolean");
```

- **Ce qu'elle interdit reellement.** Les trois morsures que cette regle avait deja portent toutes
  sur une **grandeur** : `int sommeMaximale`, `Set<Operation> operations`, `int nombreDeManches`.
  Elles laissaient lire la regle comme un refus des bornes chiffrees, et c'est ainsi qu'`ADR-0058` a
  pu decider un booleen sans se croire concerne. Cette morsure-ci epingle la portee non chiffree :
  **un etat a deux valeurs pose sur un type que toutes les matieres traversent est refuse pour le
  meme motif que la borne d'arithmetique** - une composante qu'aucune autre forme d'item ne remplit
  autrement que par une valeur par defaut.
- **Le jeu fautif n'est pas une fixture de mon imagination** : c'est la sonde du 2026-08-30,
  recopiee dans sa composante fautive, reduite a une seule violation pour que sa presence ne fasse
  pas rougir `ADR-0004` (1) en meme temps.
- **Non-vacuite** : la regle est passee a `.check(` sur un ensemble de classes non vide ; le
  recensement d'`ADR-0013` (`LesGardeFousSontMisALEpreuveTest`) l'exige deja de toute `ArchRule`
  declaree et le verifie.

**Compte des regles ArchUnit du depot : inchange a 30.** Ce lot n'ajoute aucun champ `ArchRule`.
**Methode** : balayage des fichiers `.java` des quatre arbres de modules du worktree
(`domain`, `application`, `adapters`, `bootstrap`), arbre de contre-exemple `faux-module` exclu,
comptage des lignes portant `static final ArchRule` - **30**. Les deux autres occurrences de la
chaine `ArchRule ` dans le depot sont une constante et une phrase de Javadoc de
`LesGardeFousSontMisALEpreuveTest`, et non des declarations de champ ; c'est la divergence que la
section "Regles executables" d'`ADR-0058` avait deja documentee.

## Consequences

- **Un lot de saisie ne decouvre pas sa premiere contrainte en compilant.** C'est le seul benefice
  reel de cet `ADR` : la sonde a coute une heure aujourd'hui, elle aurait coute une reprise de
  conception au milieu d'un sprint.
- **Le contrat de contenu gagne un type.** `com.recreo.domain.content` passe de six types a sept le
  jour du lot. Mauvaise consequence assumee : c'est une piece mobile de plus, exactement celle
  qu'`ADR-0058` refusait, et elle n'est justifiee par aucun usage produit - elle l'est par une
  contrainte structurelle.
- **La portee de `leContratDeContenuNePorteQueDuTexte` cesse d'etre a decouvrir.** Elle est
  desormais epinglee des deux cotes : chiffree et non chiffree.
- **Ce que cet `ADR` ne ferme pas** : rien n'empeche une composante future d'etre un enum a deux
  constantes que douze paquets de choix devraient remplir. La regle ne juge pas *ou* la composante
  est posee - c'est `ADR-0058` section 1 qui la pose sur l'item de saisie et non sur `Question`, et
  c'est une decision, pas un garde-fou.

## Alternatives considerees

1. **Assouplir `estPermis` pour admettre `boolean`.** Ecartee. C'est affaiblir une regle verte pour
   une composante, et un assouplissement ecrit pour un cas n'a jamais de plafond : `boolean` admis,
   la question suivante sera `Boolean` dans un `Optional`, puis un `int` *"qui n'est pas une borne
   de matiere"*. Le patron d'exemption de ce depot exige un nom, une date et un plafond egal a la
   dette ; ici il n'y a pas de dette a plafonner, il y a une conception a corriger.
2. **Loger le mode au niveau du paquet seulement**, dans `ContentSource`, sur le modele
   d'`indicesNommentLaReponse()`. Ecartee : `SAI-2a` exige explicitement la declaration **au niveau
   de l'item** quand un paquet melange les deux cas, et `nadia-prof` l'a ecrit comme une
   observation nommee dans sa section 8. Ce serait resoudre une contrainte technique en retirant une
   condition pedagogique, ce qui n'est pas a moi.
3. **Declarer le mode en `String`.** Passe la regle - c'est du texte - et je l'ecarte quand meme :
   une chaine libre demande une comparaison litterale quelque part, donc une valeur ecrite deux
   fois, donc un ecart muet le jour ou l'une des deux change. C'est la famille de defaut que ce
   depot sanctionne, obtenue en contournant une regle plutot qu'en la servant.
4. **Un quatrieme amendement d'`ADR-0058` plutot qu'un `ADR` neuf.** Ecartee : les trois amendements
   d'`ADR-0058` disent tous *"aucune ligne du corps ne bouge"*. Ici une clause du corps est
   remplacee, et une decision remplacee doit avoir sa propre ligne d'index, sans quoi elle ne se
   trouve que par une lecture integrale d'un fichier de 643 lignes. `ADR-0058` recoit malgre tout un
   amendement date, court, qui renvoie ici : les deux mecanismes servent, ils ne s'excluent pas.

---

**Mesure de cloture de ce lot, methode comprise.** `bash outils/mvn-jdk21.sh -B clean verify` a la
racine du reacteur - **BUILD SUCCESS, 807 tests, 0 echec** (49 `domain` + 13 `application` +
332 `adapter-web` + 288 `adapter-content` + 119 `bootstrap` surefire + 6 `failsafe`), JDK 21.0.10 de
la toolchain - version rapportee par la machine virtuelle des tests elle-meme, jamais lue sur une
declaration (`ADR-0071`). Pas de `-am` a citer : le reacteur est complet par construction. Ce lot ajoute
**cinq tests** - les quatre d'`AucuneCleDUnPaquetServiNEstIgnoreeDeToutLeDepotTest` et la morsure
d'`ADR-0069` ; je n'ai pas rejoue le build de reference avant le lot, et le delta se lit dans le
diff plutot que dans un chiffre que je n'aurais pas mesure.
