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
