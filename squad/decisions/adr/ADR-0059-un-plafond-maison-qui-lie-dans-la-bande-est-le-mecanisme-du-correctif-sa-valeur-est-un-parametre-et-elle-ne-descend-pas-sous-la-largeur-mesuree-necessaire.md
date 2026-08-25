# ADR-0059 - Un plafond maison qui lie dans la bande est le mecanisme du correctif de pli, sa valeur est un parametre, et elle ne descend jamais sous la largeur mesuree necessaire

- **Statut** : Accepte. **Sa regle est ecrite, verte, et mise a l'epreuve dans le meme commit** -
  `adapters/adapter-web/src/test/java/com/recreo/adapter/web/UnPlafondMaisonNAffamePasLaRangeeTest.java`,
  7 tests : deux cas verts, quatre morsures sur le materiau reel et un jeu fautif commite en cas rouge.
- **Sprint** : s19. **Date** : 2026-08-24. **Auteur** : theo-archi.
- **Arbre mesure** : worktree dedie, branche `round-two` a `d607df4` (SHA releve sur la branche),
  plus le lot non fusionne `s19-nadia` (`cf78a0d`), lu depuis son propre worktree. JDK 21.0.10 de la
  toolchain declaree, `bash outils/mvn-jdk21.sh`.
- **Prolonge** : `ADR-0054` (la largeur offerte est une fonction epinglee), `ADR-0055` (la largeur
  demandee est epinglee), `ADR-0056` (un seuil qu'on evite en nichant n'est pas un seuil),
  `ADR-0051` (un garde-fou qui n'examine aucun de ses objets le declare), `DEC-0040` (reference
  744 x 551, telephone hors perimetre).
- **Ne prononce aucun Done, ne livre aucun correctif.** Cet ADR decide **par quel mecanisme** un
  correctif de pli s'ecrit et **quelle contrainte** sa valeur doit respecter. La valeur elle-meme
  n'est pas ecrite ici, et ce n'est pas un oubli : voir la decision 3.

## Contexte

**Mon avis de faisabilite de s19 (section 6) fermait `S19-6` comme infaisable sur la bande entiere,
faute d'un chiffre.** Je le nommais : *"680 px est une largeur suffisante, la largeur necessaire n'a
jamais ete mesuree"*. `nadia-prof` l'a mesure le 2026-08-24 - **539 px** - et mon verdict tombe :
l'arithmetique tient, la conclusion change. L'amendement date vit dans l'avis, section 6.5 ; cet ADR
ne le recopie pas.

**Ce qui reste a decider est un mecanisme, et il n'existait pas.** Un correctif de largeur est
desormais faisable de 579 a 719 px de viewport - 141 largeurs entieres sur 144 - et personne n'a
ecrit par quelle declaration, sous quelle contrainte, ni gardee par quoi.

**Le trou de garde-fou que j'ouvre cet ADR pour fermer, et il est precis.**
`LaLargeurOffreeAUneRangeeEstUneFonctionEpingleeTest` (`ADR-0054`) est un **detecteur de
changement** : il dit qu'une declaration a bouge. Il ne dit pas si la valeur nouvelle vaut quelque
chose. Le chemin le moins cher pour ecrire le correctif est de recopier la valeur que la feuille
tierce sert deja au palier - `max-width: 540px` - ou n'importe quelle valeur ronde en dessous du
necessaire. Alors : la table rougit, son auteur ajoute deux lignes, la CI redevient verte, **et le
pli reste exactement ou il est.** Un correctif qui ne corrige rien, livre sous une CI verte, dans le
fichier meme que ce depot a ecrit pour garder cette famille de defaut. C'est la forme exacte du
defaut que `ADR-0055` a sanctionnee (une moitie de comparaison gardee) transposee d'un plancher a un
plafond.

## Decision

### 1. Le mecanisme : une declaration maison qui **lie** dans la bande, et rien d'autre

Le correctif de pli est **une declaration de plafond lateral, dans `recreo.css`, qui l'emporte sur le
plafond de la feuille tierce a l'interieur de la bande 576-719,98 px**, portee sur les memes
selecteurs que le correctif de s18 (`.entete-contenu` et `.zone-jeu`, portee arbitree par
`nadia-prof` le 2026-08-23, motif mesure). Ce n'est pas un mecanisme neuf : c'est **exactement** le
mecanisme de s18 (`SYN-11`), etendu vers le bas.

Deux formes le realisent, et **le choix entre elles n'est pas d'architecture** :

- **abaisser la borne basse de la regle de s18** de 720 px a 576 px, en gardant sa valeur ;
- **ajouter un second bloc**, borne a 576-719,98 px, avec sa propre valeur.

Elles couvrent **les memes 141 largeurs de viewport** (demonstration en decision 2) et ne different
que par l'endroit ou la zone cesse de s'etaler bord a bord. C'est une question de forme :
`ines-design` propose, `nadia-prof` tranche sous `SYN-14`.

**Ce que la declaration ne fait pas** : elle ne touche a aucun terme de colonne (`ADR-0055` n'est pas
concerne), a aucun terme vertical, et a aucun seuil de la feuille tierce. Les deux litteraux de bande
restent ceux de l'escalier tiers (`576px`, et `719.98px` derive du `720px` de la marche suivante),
pour le motif d'`ADR-0056` : un litteral invente a cote d'un litteral tiers cree un intervalle ou
personne ne sait qui gouverne.

### 2. L'arithmetique du mecanisme, et le fait qu'elle rend la valeur libre

Soit `V` le viewport, `P` le plafond maison, `L` la largeur de rangee necessaire (539 px aujourd'hui),
et 40 px le retranchement de la chaine (24 px de rembourrage de `.container` + 16 px de bordure de
`.cabinet`).

> **rangee = min(V, P) - 40**, et les seize items tiennent sur une rangee si et seulement si
> `min(V, P) - 40 >= L`.

**Cette formule reproduit les onze mesures au navigateur de `nadia-prof`, pas seulement les trois
qu'elle a nommees.** Ses trois campagnes de largeur : `P = 540` -> 500 px, `P = 578` -> 538 px,
`P = 579` -> 539 px. Et ses **huit** mesures de bords lateraux a `P = 579` se derivent de la meme
formule, que personne n'avait confrontee : le bord gauche de la zone vaut `(V - min(V, P)) / 2`, soit
0 a 576, 578 et 579 px, puis 0,5 - 10,5 - 30,5 - 60,5 - 70,0 px a 580, 600, 640, 700 et 719 px. **Ce
sont ses huit chiffres, au dixieme.** L'arithmetique d'`ADR-0054` est donc reproduite une cinquieme
fois, sur un axe qu'elle n'avait jamais servi.

**Le resultat qui rend la valeur libre**, et c'est le point le plus utile de cet ADR :

- si `P >= L + 40`, les items tiennent des que `V >= L + 40`, **quelle que soit la valeur de `P`** -
  parce qu'en dessous de `P` le plafond ne lie pas et la rangee vaut `V - 40` ;
- si `P < L + 40`, **aucun** viewport de la bande ne tient, parce que le plafond lie partout au-dessus
  de `P` et y capte la rangee a `P - 40`.

**La couverture est donc binaire en `P` : 141 largeurs sur 144, ou zero.** Ce que `P` gouverne n'est
pas ce qui est corrige, c'est **ou le centrage commence** - sous `P` la zone s'etale bord a bord, au-
dessus elle est centree. C'est pour cela que la valeur peut etre routee sans que le mecanisme bouge.

**Les trois pixels que rien ne peut atteindre restent hors de portee de toute valeur.** A `V = 576`,
577 et 578, la rangee ne peut pas depasser `V - 40`, soit 536, 537 et 538 px. C'est une propriete du
viewport, pas de la feuille : aucune declaration ne les corrige. Les couvrir demande de reduire `L`
(largeur demandee, `ADR-0055`) ou de recuperer du budget vertical - deux familles hors de cet ADR.

### 3. La valeur du plafond est un parametre, et sa seule contrainte est une inegalite

**La valeur n'appartient pas a l'architecture.** `nadia-prof` route la reserve de police a
`ines-design`, qui propose, et se reserve de trancher ; `camille-po` decide de la tolerance que le
produit accepte. Cet ADR n'ecrit aucune valeur de `P` et sa regle n'en contient aucune.

**Ce que l'architecture pose est une inegalite, et une seule** :

> **`P >= L + R + 40`**, ou `L` est la largeur de rangee mesuree necessaire (539 px, `nadia-prof`,
> 2026-08-24) et `R` la reserve de police que le produit choisit.

Trois consequences a nommer, parce qu'elles decident la valeur sans que je la decide :

- **`R = 0` fait tenir le correctif a la borne.** `P = 579` capte la rangee a exactement 539 px des
  579 px de viewport. Une pile de polices 0,6 % plus large porte `L` a 543 px, et **la bande entiere
  tombe** - pas trois pixels, les 141. C'est le mode de defaillance que `nadia-prof` nomme, et
  l'arithmetique le confirme : un plafond au strict necessaire transforme une degradation en
  effondrement.
- **`R > 0` degrade proprement.** Avec `P = L + R + 40`, une croissance de police jusqu'a `R` px ne
  coute que des viewports au pied de la bande (le plancher de couverture monte de `L + 40` a
  `L' + 40`), jamais la bande entiere. Pour absorber les 0,6 % que `nadia-prof` cite, il faut
  `P >= 583`.
- **La reserve au pied de la bande est nulle quelle que soit la valeur.** A `V = 579`, la rangee vaut
  539 px pour tout `P >= 579` : aucune valeur n'achete de marge la ou le viewport est la contrainte.
  `R` protege le correctif contre une **remesure de `L`**, il n'achete pas de robustesse a 579 px.

**Ce que coute une valeur haute** : le centrage commence plus haut (`P = 680` ne centre plus rien en
dessous de 680 px), et la ligne de texte s'allonge au sommet de la bande. Les deux sont des faits de
forme, sous `SYN-14` et sous la porte pedagogique.

**Ce qui rouvrirait cet ADR** : une remesure de `L` par `nadia-prof`, ou une decision de `camille-po`
qui changerait `R`. Aucune des deux ne touche le mecanisme ni le texte de la regle - **elles changent
une ligne de constante chacune**, et c'est le critere auquel j'ai construit ce qui suit.

## Regles executables

**Fichier** :
`adapters/adapter-web/src/test/java/com/recreo/adapter/web/UnPlafondMaisonNAffamePasLaRangeeTest.java`.
Ce n'est pas une regle ArchUnit et ce n'en est pas une par defaut de rigueur : ArchUnit juge des
types Java, et l'objet ici est une declaration de feuille de style confrontee au document servi.
C'est le mecanisme automatique de la famille `ADR-0054`/`ADR-0055`/`ADR-0056`, dans le module ou
elle vit.

> **Aucun plafond maison pose sur la chaine d'une rangee repliable ne laisse a cette rangee moins que
> la largeur mesuree necessaire, reserve comprise.**

```java
private static final int LARGEUR_DE_RANGEE_NECESSAIRE_PX = 539;
private static final int RETRANCHEMENT_MESURE_PX = 40;
private static final int RESERVE_DE_POLICE_PX = 0;
static final int PLAFOND_MINIMAL_PX =
        LARGEUR_DE_RANGEE_NECESSAIRE_PX + RETRANCHEMENT_MESURE_PX + RESERVE_DE_POLICE_PX;

static void verifierQuAucunPlafondMaisonNAffameUneRangee(Document document, Path racine) {
    List<Regle> maison = reglesMaison(racine);
    List<Element> rangees = rangeesRepliables(document, maison);

    List<String> examines = new ArrayList<>();
    for (Regle regle : maison) {
        int position = debutDeDeclaration(regle.corps(), PLAFOND);
        if (position < 0 || regle.selecteur().contains("::")) {
            continue;
        }
        if (designes(document, sansPseudoClasses(regle.selecteur())).stream()
                .noneMatch(element -> gouverneUneRangee(element, rangees))) {
            continue;
        }
        String valeur = valeurDeclaree(regle.corps(), position, PLAFOND);
        examines.add(regle.origine() + " -> " + valeur);
        assertThat(enPixels(regle, valeur))
                .as("ADR-0059 : le plafond maison declare par %s affame la rangee. [...]")
                .isGreaterThanOrEqualTo(PLAFOND_MINIMAL_PX);
    }

    assertThat(examines)
            .as("ADR-0051 : aucun plafond maison ne borne la largeur d'une rangee dans ce "
                    + "document, donc ce garde-fou n'examine aucun objet et ne prouve rien. [...]")
            .isNotEmpty();
}
```

**Ce qu'elle interdit reellement, et la reponse n'est pas "rien".** Elle interdit le seul chemin par
lequel un correctif de pli peut etre livre sous une CI verte sans rien corriger : un plafond maison
dont la valeur est en dessous du necessaire mesure. Ce chemin est nomme, pas imagine - c'est celui
qu'une main prudente prend, en recopiant la valeur que la feuille tierce sert deja au palier, et la
morsure 1 le reproduit exactement. Elle interdit aussi qu'une remesure de `L` reste sans effet : le
jour ou `nadia-prof` porte le necessaire a 543 px, c'est cette constante qui monte et **la CI rougit
jusqu'a ce que la feuille suive**.

**Ce qu'elle n'interdit pas, et je le dis avant qu'on me le demande.** Elle ne mesure aucune position
rendue (`ADR-0025`, `ADR-0028`), ne dit pas qu'un item tient sur une rangee, et ne dit rien du budget
vertical - voir la section suivante. Elle ne dit rien non plus des viewports sous `L + 40`, ou aucune
declaration ne peut rien.

**Perimetre derive** (`ADR-0013` obligation 1) : les regles **maison** qui declarent `max-width` sur
un element qui est une rangee repliable ou l'ancetre de l'une d'elles. La population des rangees est
celle qu'`ADR-0054` et `ADR-0055` derivent deja (`flex-wrap` autre que `nowrap`), reutilisee et non
recopiee. Aucun nom de classe, aucune bande, aucun seuil n'est ecrit dans le fichier. La feuille
tierce est **hors** perimetre : son escalier n'est pas notre decision, et `ADR-0054` le garde deja
contre un changement muet.

**Derivation totale** (obligation 2) : un plafond exprime autrement qu'en pixels est un **echec**, pas
un cas saute - le garde-fou ne saurait pas dire ce qu'il laisse a la rangee. Zero plafond maison
examine est un echec aussi (`ADR-0051`).

**Non-vacuite, mesuree et non supposee** : la production en declare **deux** aujourd'hui
(`.entete-contenu` et `.zone-jeu`, `max-width: 720px` sous `@media (min-width: 720px) and
(max-width: 767.98px)`), tous deux au-dessus du plancher, et la morsure 1 prouve qu'ils sont bien
dans le perimetre en les faisant rougir apres mutation.

**Quatre morsures sur le materiau reel, plus un jeu fautif commite** (obligation 3) - la feuille maison servie, mutee par
l'implementation unique d'`ADR-0057`, jamais une fixture ecrite de ma main :

1. **La valeur tierce recopiee** : `max-width: 720px` -> `540px`. Rouge. C'est la faute que cet ADR
   existe pour attraper.
2. **La borne est au pixel** : `PLAFOND_MINIMAL_PX - 1` rouge, `PLAFOND_MINIMAL_PX` vert, dans le
   meme test. Sans cette paire, une regle qui refuserait tout plafond sous 300 px rendrait le meme
   vert sur la production - c'est elle qui prouve que la constante est bien le critere.
3. **Une unite que le garde-fou ne convertit pas** : `45rem`. Rouge. `rem` est l'unite que cette
   feuille emploie partout ailleurs, donc l'ecrire ici est le geste naturel de quelqu'un qui suit le
   style du fichier.
4. **Tout plafond maison retire** : la declaration de s18 supprimee. Rouge par la clause
   d'`ADR-0051`, la ou le garde-fou serait sinon vert a vide.

**Et le jeu fautif commite, sans lequel les quatre morsures ne prouveraient que la moitie.** Elles
travaillent toutes sur les noms de la production : elles montrent que la regle mord, pas que sa
derivation est derivee. Le jeu fautif de
`adapters/adapter-web/src/test/resources/contreexemple/UnPlafondMaisonNAffamePasLaRangeeTest/` ne
partage aucun nom avec la production - `.rangee-de-choix` au lieu de `.propositions`, `.panneau` au
lieu de `.zone-jeu`, une bande 620-899,98 px qui n'est celle d'aucune feuille de ce depot - et il est
joue en **cas vert** (plafond 640 px) et en **cas rouge** (plafond 520 px, 480 px laisses a une
rangee qui en demande 539). Sans son cas vert, le cas rouge ne prouverait qu'une chose : que la regle
reconnait les noms de la production.

**Mesure, methode comprise** :

- **Regle seule** : `bash outils/mvn-jdk21.sh -B -pl adapters/adapter-web -am test
  -Dtest=UnPlafondMaisonNAffamePasLaRangeeTest -Dsurefire.failIfNoSpecifiedTests=false` - **7 tests,
  0 echec**. Perimetre : le module `adapters/adapter-web` et ses dependances (`-am`), pas le depot.
- **Reacteur complet** : `bash outils/mvn-jdk21.sh -B clean verify` a la racine - **624 tests,
  0 echec, BUILD SUCCESS** avant ce lot, **631, 0 echec** apres. Les sept de plus sont ceux de ce
  fichier, et `adapter-web` passe de 321 tests sur 42 classes a 328 sur 43. Pas de `-am` a citer : le reacteur est complet par construction.
- **Repartition par module, comptee sur les rapports XML de surefire et de failsafe de cet arbre**
  (`<testsuite tests="...">`, une somme par module) : 48 `domain` + 13 `application` +
  **321 `adapter-web` sur 42 classes** + **144 `adapter-content` sur 23 classes** + 92 `bootstrap`
  surefire + 6 `failsafe` = 624 avant ce lot. **Cette repartition corrige une etiquette inversee
  d'`ADR-0058`**, trouvee par `sofia-sm` a la relecture de mon lot et verifiee ici par le meme
  comptage : le total de 624 etait juste, les deux noms de module etaient echanges.
- **Compte des regles ArchUnit** : inchange par ce lot - **30 regles reelles**, comptees comme
  `ADR-0058` le decrit. Ce fichier n'est pas une regle ArchUnit et ne s'y ajoute pas.

## Ce qui reste ouvert, et pourquoi je ne l'ecris pas aujourd'hui

**Aucun garde-fou de ce depot n'epingle un terme vertical.** Le constat de mon avis (section 6.3.2)
tient, et il a change de nature : la famille verticale n'est plus une route rivale, elle est ce qui
garde la marge du correctif de largeur. Le correctif ne vaut que par un chiffre vertical - le verdict
remonte a 504,19 px pour un pli a 551 px, soit **+46,81 px** - et cette marge est tenue par des
declarations que rien ne compare a rien. Le rembourrage de `.zone-jeu` vaut 32 px en haut ; un lot de
forme qui le porte a 2,5 rem consomme 8 px de cette marge sans qu'aucun test ne le dise.

**Je n'ecris pas ce garde-fou dans ce lot, et le motif n'est pas la charge.** Le perimetre honnete
n'est pas la chaine d'ancetres du bloc de verdict : la position verticale du verdict est la somme des
hauteurs de **tout ce qui le precede dans le flux**, freres compris, et un garde-fou qui ne
regarderait que les ancetres resterait vert pendant qu'un rembourrage de `.enonce` pousse le verdict
sous le pli. Ce serait une regle decorative, du genre exact que ce depot sanctionne. Le perimetre
juste est le flux au-dessus du verdict, et une table epinglee sur ce perimetre serait epinglee
**contre une mise en page que le correctif de `S19-6` est sur le point de changer** - perimee le jour
de son premier usage, alors que `ADR-0054` etablit qu'une table s'epingle dans le commit qui change la
mise en page, avec la remesure qui en decoule.

**Declencheur, date et nomme** : le premier lot qui touche un terme vertical du flux au-dessus du
verdict - correctif de `S19-6` compris - porte cet `ADR` et sa table, avec la mesure de `nadia-prof`
du meme commit. **C'est une dette explicite, elle est la mienne, et elle est datee du 2026-08-24.**

## Consequences

- **Un correctif de pli peut etre affecte a un lot sans rouvrir la conception.** Ce qui reste a
  decider est la valeur de `P` et la forme de la declaration, ni l'une ni l'autre d'architecture.
- **Un correctif qui ne corrige rien ne peut plus etre livre vert.** C'est le seul apport reel de cet
  ADR, et il est etroit : il ne rend aucun correctif juste, il en rend une famille impossible.
- **Trois pixels de viewport restent non couverts par ce mecanisme, definitivement.** 576, 577 et 578
  px sont hors de portee de toute valeur de plafond. Les couvrir est une autre famille. Que le
  produit s'en contente est une decision de `camille-po`.
- **La table d'`ADR-0054` et cette regle rougissent ensemble sur le meme commit**, et c'est voulu :
  la premiere dit qu'une declaration a bouge, la seconde dit si la valeur nouvelle tient. Deux rouges
  pour un geste, avec deux messages differents. Mauvaise consequence assumee : un auteur presse peut
  lire le second comme un doublon du premier.
- **Une remesure de `L` fait rougir la CI jusqu'a ce que la feuille suive.** C'est le comportement
  voulu, et il coute : `nadia-prof` ne peut plus remesurer sans qu'un lot de code s'ensuive.
- **Le compte de tests du module `adapter-web` monte de 7** ; le compte de regles ArchUnit ne bouge
  pas.
- **`copieDesFeuilles` passe en visibilite de paquet** dans
  `LaLargeurOffreeAUneRangeeEstUneFonctionEpingleeTest` plutot que d'etre recopiee une troisieme
  fois - le declencheur qu'`ADR-0056` avait ecrit pour `muter`. La deuxieme copie, dans
  `LaLargeurDemandeeParUneRangeeEstEpingleeTest`, reste ou elle est : je ne la touche pas dans un lot
  qui ne la concerne pas.

## Alternatives considerees

1. **Ecrire la valeur du plafond dans cet ADR.** Ecartee : la reserve de police est une question de
   forme et de tolerance produit, routee par `nadia-prof` a `ines-design` et a `camille-po` le
   2026-08-24. Un ADR qui figerait 579 px devrait etre rouvert au premier ajustement de police, et
   c'est exactement le genre de valeur que ce depot a paye cher a figer ailleurs.
2. **Une regle qui compare le plafond a un plancher ecrit en dur dans son message, sans constante
   nommee.** Ecartee : la remesure de `L` est certaine (la mesure porte sa propre reserve), et une
   regle qu'il faut relire pour changer un nombre est une regle qu'on finit par contourner.
3. **Etendre la table epinglee d'`ADR-0054` d'une colonne "valeur minimale".** Ecartee : la table est
   un releve de ce qui est declare, et lui faire porter un critere melangerait deux natures de rouge
   dans un seul message. `ADR-0055` a deja etabli qu'une table de releve et un plancher sont deux
   objets.
4. **Ne rien garder et s'en remettre a la relecture.** Ecartee, et c'est le motif du depot entier :
   le defaut vise passe une CI verte et ne laisse aucune trace.
5. **Garder la relation `P >= L + R + 40` par un test de rendu au navigateur.** Ecartee : ce depot
   n'a pas de navigateur en CI (`ADR-0025`), et la mesure au navigateur appartient a `nadia-prof`,
   hors CI par construction.
6. **Faire porter la regle sur la feuille tierce aussi.** Ecartee : l'escalier tiers a 540 px est
   **sous** le plancher, et le refuser rendrait la regle rouge sur du materiau que nous ne
   choisissons pas. C'est precisement pour cela que la bande existe : la declaration maison est ce
   qui l'emporte, et c'est elle qui est jugee.

---

## Amendement du 2026-08-25 (s20) - le pire cas passe de 539 a 647 px : le mecanisme tient, trois de mes affirmations non

- **Auteur** : theo-archi. **Fait neuf** : `DEC-0047`, section 3, etend `S19-6a` a **trois** jeux
  nommes et corrige le pire cas connu de la bande a **647 px** (`cm1-accord-sujet-verbe`,
  `couvert-valide` depuis s19), contre 539 px (`cm1-synonymes`) et 547 px (`cm1-antonymes`).
- **Mode** : arithmetique et lecture de code, sur l'arbre `s20-theo` a `c48c0e6`. Aucune mesure de
  navigateur n'est de moi ; les largeurs necessaires sont celles de `nadia-prof`.
- **Rien de ce qui precede n'est efface.**

### 1. Ce qui tient, et c'est ce pour quoi cet `ADR` a ete construit

**Le mecanisme ne bouge pas.** La declaration maison qui l'emporte dans la bande, les deux
selecteurs, les deux litteraux de bande derives de l'escalier tiers, le texte de la regle : rien ne
change. **La demonstration ne bouge pas non plus** : la couverture reste binaire en `P`, avec le
meme seuil `L + 40`, et `P` gouverne toujours seulement l'endroit ou le centrage commence. L'`ADR`
avait ecrit son declencheur de reouverture - *"une remesure de `L` par `nadia-prof`"* - et il vient
de se produire exactement comme prevu.

### 2. La valeur : `P >= 687 + R`, et 620 px est exclue

Avec `L = 647` et le retranchement mesure de 40 px, l'inegalite de la decision 3 devient :

> **`P >= 687 + R`**

- **La proposition de 620 px d'`ines-design` est exclue**, et pas seulement "non confirmee" comme
  `DEC-0047` le formulait prudemment. A `P = 620`, la rangee vaut au mieux 580 px dans la bande, la
  ou `cm1-accord-sujet-verbe` en demande 647 : **elle ne corrige aucune largeur de la bande pour ce
  jeu**. Elle avait ete calculee sur un pire cas de 547 px, ou elle etait juste.
- **La marge de police non nulle exigee par `camille-po`** se lit `R > 0`, donc `P > 687`
  strictement. Pour absorber les 0,6 % de croissance de pile de polices que `nadia-prof` cite,
  `R >= 4` et donc `P >= 691`.
- **La reserve au pied de la bande reste nulle quelle que soit la valeur** : a `V = 687`, la rangee
  vaut 647 px pour tout `P >= 687`. Le troisieme point de la decision 3 est inchange dans sa forme
  et deplace de 579 a 687 dans ses chiffres. `R` protege contre une **remesure de `L`**, il n'achete
  aucune robustesse au pied de la bande.

### 3. Ce que je corrige, et le plus couteux est de moi

**a) "141 largeurs de viewport sur 144, ou zero" est perime.** La phrase est vraie a `L = 539`. A
`L = 647`, aucune valeur de `P` ne peut couvrir un viewport sous 687 px, parce que la rangee y vaut
au mieux `V - 40`. La couverture devient **33 largeurs sur 144** (687 a 719 px inclus), ou zero.
**Le residu de bande passe de 3 px a 111 px**, soit 77 % de la bande que `S19-6a` est chargee de
fermer. La demonstration tient, ses chiffres non - et ce chiffre-la a ete recopie tel quel dans
`ADR-index.md`, ou il se lit isole de sa condition. Index corrige dans le meme geste.

Consequence que je nomme sans la trancher : **`camille-po` et `nadia-prof` arbitrent desormais un
residu de 111 px**, pas de trois. Le critere de `S19-6a` sur le residu assume les accueille tel
quel, mais il a ete ecrit quand le residu valait trois pixels, et une tolerance ne s'accorde pas a
l'aveugle. L'autre levier existe et n'est pas le mien : reduire `L`, la largeur **demandee** par la
rangee de `cm1-accord-sujet-verbe` (famille `ADR-0055`), au lieu d'augmenter ce qu'on lui offre.

**b) "Elles changent une ligne de constante chacune" est faux, et c'etait le critere auquel j'ai
construit la regle.** J'ai verifie au lieu de me relire. Porter
`LARGEUR_DE_RANGEE_NECESSAIRE_PX` de 539 a 647 porte `PLAFOND_MINIMAL_PX` de 579 a 687, et **le jeu
fautif commite bascule avec** : son **cas vert** declare `max-width: 640px` dans
`adapters/adapter-web/src/test/resources/contreexemple/UnPlafondMaisonNAffamePasLaRangeeTest/plafond-maison-suffisant/static/css/feuille-maison.css`,
donc il devient **rouge** apres la remesure.

Le cout reel d'une remesure de `L` est donc de **deux fichiers**, et le second n'est pas une
correction mecanique : c'est le cas vert **sans lequel les quatre morsures ne prouvent que la
moitie**, comme cet `ADR` l'ecrit lui-meme. Sa valeur se rechoisit en connaissance de cause - au-
dessus du nouveau plancher et clairement au-dessus, pour qu'il continue de prouver ce qu'il prouve -
pas en la remontant distraitement jusqu'a ce que le vert revienne. **Les trois constantes nommees
d'une ligne chacune ne suffisent pas a rendre une remesure gratuite, et l'annoncer gratuite etait
precisement le genre d'affirmation qu'un lecteur reprend sans la verifier.**

**La production reste verte** : les deux plafonds maison declares valent `720px`, au-dessus du
plancher de 687.

**c) Le declencheur de la dette verticale, tel qu'ecrit, produirait un travail perime.** J'avais
ecrit : *"le premier lot qui touche un terme vertical du flux au-dessus du verdict - correctif de
`S19-6` compris - porte cet `ADR` et sa table"*. Deux lots de s20 touchent ce flux : `S19-6a`, et
`S20-1` dont le renfort `.mot-designe` agrandit la boite de ligne de `.enonce` de +7,19 px
uniformement et jusqu'a +55,19 px sur un item qui bascule a deux lignes (mesures d'`ines-design`,
`e5a8360`). **Une table epinglee par le premier des deux serait fausse a la fusion du second** -
exactement le defaut que je citais en refusant de l'ecrire a s19.

**Le declencheur devient** : la table est portee par **le dernier** lot de s20 qui touche un terme
vertical de ce flux, avec la mesure de `nadia-prof` du meme commit. **Ce n'est pas un desserrement,
et je pose la borne qui l'empeche d'en devenir un** : aucun de ces lots n'est fusionne en declarant
la dette fermee, et le point de controle appartient a `sofia-sm` au planning de s20. Si un seul des
deux lots entre finalement au sprint, c'est celui-la, et le declencheur d'origine s'applique mot
pour mot.

### 4. Ce que cet amendement ne change pas

Les decisions 1 et 2, le texte de la regle, son perimetre derive, sa derivation totale, ses quatre
morsures et son jeu fautif sont inchanges. **Aucun fichier de test n'est modifie par cet
amendement** : la remesure de `L` appartient au lot qui livre `S19-6a`, avec la valeur que
`nadia-prof` aura tranchee, et l'ecrire ici sans elle reviendrait a decider la valeur a sa place.

**Mesure du jour**, sur `s20-theo` a `c48c0e6`, arbre propre : `bash outils/mvn-jdk21.sh -B clean
verify` a la racine - **BUILD SUCCESS, 668 tests, 0 echec** (48 `domain` + 13 `application` +
328 `adapter-web` + 181 `adapter-content` + 92 `bootstrap` surefire + 6 `failsafe`), JDK **21.0.10**
de la toolchain declaree. Pas de `-am` a citer, le reacteur est complet par construction.
`UnPlafondMaisonNAffamePasLaRangeeTest` y porte toujours ses **7 tests, 0 echec**.
**Compte des regles ArchUnit : 30 regles reelles, inchange.**

---

## Second amendement du 2026-08-25 (s20) - la dette verticale recoit sa mesure, et son declencheur change de nature

- **Auteur** : theo-archi. **Fait neuf** : `camille-po` ne maintient pas le correctif de largeur et
  rouvre la famille verticale. Le mecanisme de cet `ADR` n'est donc plus au perimetre de s20 ; sa
  **dette** l'est.
- **Rien de ce qui precede n'est efface.**
- **Mesure complete, methode comprise** :
  `squad/sprints/s20-mot-cible-designe-et-conception-de-la-saisie/mesure-budget-vertical.md`, appuyee
  sur deux outils commites dans `squad/outils/theo-archi/`. Ce qui suit n'en reprend que ce qui
  touche cet `ADR`.

### 1. La decision et la regle sont intactes ; elles n'ont plus d'objet ce sprint

Aucune ligne des decisions 1 a 3 ne devient fausse. L'inegalite `P >= L + R + 40` reste vraie, le
mecanisme reste celui qu'il faudrait si un plafond revenait un jour, et
`UnPlafondMaisonNAffamePasLaRangeeTest` continue de garder ce qu'il garde - les deux plafonds maison
declares valent `720px`, au-dessus du plancher, la production est verte. **`ADR-0059` n'est ni
remplace ni rejete : il est sans emploi tant qu'aucun plafond n'est decide.** C'est un etat different
de "faux", et je l'ecris plutot que de laisser un lecteur choisir entre les deux.

**Ce qui l'a rendu sans emploi n'est pas une erreur de l'`ADR`** : c'est mon propre recalcul de la
couverture (33 largeurs sur 144, premier amendement du jour) plus un motif produit que je n'avais pas
vu et que `camille-po` apporte - le plancher de 687 px met hors de portee l'ecran de 1280 px partage
en deux, soit environ 640 px, **le cas d'usage meme qui justifiait la bande**. Un correctif dont le
plancher exclut sa propre raison d'etre n'est pas un correctif ; c'est une observation que
l'arithmetique rendait disponible et que seule elle a formulee.

### 2. Une propriete de cet `ADR` est desormais verifiee sur un second axe et sur 251 items

La decision 2 demontre que la feuille tierce capte la zone a 540 px dans toute la bande, donc que la
rangee y recoit 500 px **quelle que soit la largeur du viewport**. Cette arithmetique avait ete
reproduite cinq fois sur des mesures de largeur. Elle vient de l'etre **verticalement** : sur
**251 items mesures a 576 px et a 719 px, le bas du bloc de message est identique au centieme, zero
exception**. La bande n'a pas de gradient - c'est une seule mise en page, et toute mesure prise
n'importe ou dedans vaut pour elle entiere.

**Consequence pratique qui vaut d'etre gardee** : le releve d'`ines-design` a 640 px et celui de
`nadia-prof` a 700 et 719 px decrivent le meme ecran et se comparent directement, ce que personne
n'avait le droit d'affirmer avant cette verification.

### 3. La dette verticale : elle est mesuree, et elle est plus grande que ce que cet `ADR` supposait

Cet `ADR` ecrivait : *"le correctif ne vaut que par un chiffre vertical - le verdict remonte a
504,19 px pour un pli a 551 px, soit +46,81 px - et cette marge est tenue par des declarations que
rien ne compare a rien"*. Le chiffre de 504,19 px est **exact** et mon harnais le reproduit au
centieme. **La marge de 46,81 px, elle, ne decrit pas l'ecran** : elle s'arrete au bas du texte du
verdict, alors que l'indice vit en dessous et qu'il est l'objet meme de `BPM-8`. Sur le materiau
reel, le bas du bloc de message descend jusqu'a **859,19 px**, soit un deficit de **308,19 px** sous
un pli a 551 - et **134 items sur 294, sur dix jeux servis sur quinze, sont sous le pli dans la
bande**.

Face a cela, le budget vertical recuperable vaut **80 px** sans rouvrir d'arbitrage acte, **136 px**
en rouvrant le meuble. Il ferme 90 puis 114 des 134 items, et **aucun** des dix items a trois
rangees. La dette existe, elle est instruite, et **la piste qu'elle gardait ne ferme pas la bande**.

### 4. Le declencheur change de nature, et je dis pourquoi ce n'est pas un desserrement

J'avais promis une **table** de termes verticaux, portee par le premier lot touchant ce flux, puis -
premier amendement du jour - par le dernier lot de s20.

**Cette table n'aurait pas attrape le lot qui declenche la dette.** Le declencheur restant est
`S20-1`, dont le renfort `.mot-designe` ajoute 7,19 px a la boite de ligne de `.enonce`. Cette
croissance est **dependante du contenu** - un `font-size` sur un fragment de phrase, pas une
declaration de rembourrage. Une table de termes fixes, la seule qu'un test de CI puisse lire faute de
navigateur dans `mvn verify`, serait **verte** pendant que `.mot-designe` pousse 46 % des items plus
bas. Ce serait une regle decorative au sens exact ou ce depot emploie le mot : elle interdirait
quelque chose de reel, mais **pas ce qui a motive son ecriture**.

**Le declencheur devient donc une mesure et non une table.** Tout lot qui touche un terme vertical du
flux au-dessus du verdict fait tourner `squad/outils/theo-archi/campagne-budget-vertical.sh` avant et
apres, et porte les deux chiffres dans son commit. **Ce n'est pas un garde-fou automatique et je ne
le presente pas comme tel** : c'est une mesure obligatoire, plus faible qu'un test, et plus honnete
qu'un test vert qui ne regarde pas l'objet.

**Ce qui rendrait la table due, et donc l'`ADR` vertical du** : le jour ou un lot de forme touche un
terme **fixe** de cette pile - `.entete`, `.zone-jeu`, `.score`, le meuble, les rembourrages de
`.signal` et de `.indice`. Sur ce lot-la, la table est ecrivable, lisible par un test de CI, et elle
attraperait exactement ce que le lot ferait. **Declencheur ecrit, date du 2026-08-25, et il reste ma
dette.**

### 5. Ce que cet amendement ne change pas

Les decisions 1 a 3, le texte de la regle, son perimetre derive, ses quatre morsures, son jeu fautif
et le premier amendement du jour sont inchanges. Aucun fichier de test n'est modifie.

**Mesure du jour**, sur `s20-theo`, arbre propre : `bash outils/mvn-jdk21.sh -B clean verify` a la
racine - **BUILD SUCCESS, 668 tests, 0 echec**, JDK **21.0.10** de la toolchain declaree ; pas de
`-am` a citer, le reacteur est complet par construction. **Compte des regles ArchUnit : 30 regles
reelles, inchange** - ce lot n'ajoute aucune `ArchRule` et ne touche aucun fichier de code. Les
campagnes de rendu ne sont pas des tests de ce compte : elles ont besoin d'un navigateur, et elles
declarent leur perimetre dans leur propre rapport.
