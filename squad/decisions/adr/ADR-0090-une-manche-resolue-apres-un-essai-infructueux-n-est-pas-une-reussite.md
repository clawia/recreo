# ADR-0090 - Une manche resolue apres un essai infructueux n'est pas comptee comme une reussite

- **Statut** : Propose
- **Sprint** : s34
- **Date** : 2026-09-10
- **Auteur** : `theo-archi`
- **Story** : `S34-SCO1-REMEDE`
- **Decisions liees** : `DEC-0061` (instruction 1), `DEC-0060`, `ADR-0019`, `ADR-0063`, `ADR-0013`,
  `ADR-0051`
- **Mesures** : `squad/sprints/s34-dernier-sprint-du-plan-2-palier-2-engage/avis-faisabilite-theo.md`,
  section 2

## Contexte

Le probleme n'est pas une preference de comptage, c'est un fait mesure et reste ouvert neuf
sprints. Sur une manche a deux ou trois propositions, un enfant qui presse successivement toutes
les propositions finit toujours par presser la bonne, et `resoudre(true)` credite alors la manche
comme une reussite : `score.reussites()` monte de un, quel que soit le nombre d'essais deja
depenses. **Une strategie qui ne connait aucune reponse obtient un score parfait**, sur 94,6 pour
cent des items du catalogue (`DEC-0060`, `SCO-3`). Le score cesse ainsi de dire ce qu'il pretend
dire a l'enfant et a l'adulte qui l'accompagne.

Ce qui manquait n'etait pas le remede - il tient en une expression par mecanique - mais la phrase
qui dit **quoi** faire, et elle appartient a la pedagogie. `nadia-prof` l'a rendue le 2026-09-10,
et elle demande qu'elle soit citee mot pour mot :

> *"Quand l'elimination est possible, l'enfant garde ses trois essais, et le score cesse de compter
> comme trouvee une manche qu'il n'a pas trouvee du premier coup."*

C'est la **famille A** : le nombre d'essais ne bouge pas, le comptage change. La famille B - reduire
le nombre d'essais quand l'elimination est possible - a ete refusee par `nadia-prof`, et son premier
motif est que l'enfant y perd le droit de se reprendre.

Trois faits de perimetre, mesures plutot que supposes (avis de s34, sections 2.1 a 2.4) :

1. **Le geste vit dans la mecanique, jamais dans le contenu.** Zero fichier de `contenu/` touche, et
   les 693 tests de `recreo-adapter-content` restent verts sous le remede. Les 66 entrees de
   `bandit-manchot` passent toutes par la meme session.
2. **Trois classes, deux ecritures.** `BanditManchotSession` et `DesignationSession` portent un champ
   `essaisSurLaManche` ; `SaisieSession` n'en a pas - elle porte `List<String> tentatives`, et la
   condition s'y ecrit `tentatives.isEmpty()`. L'affirmation *"le changement est identique"* est
   vraie pour ce que l'enfant vit, inexacte pour le code.
3. **Un seul test du depot entier rougit sous le remede** : `DeuxCoupsSimultanesTest`
   (`adapter-web`), et ce n'est pas une objection au remede - voir Consequences.

`ESSAIS_AVANT_REVELATION = 3` ne bouge pas dans aucune des trois sessions : c'est la difference meme
entre les familles A et B. `ADR-0063` n'est ni amende ni contourne : le remede ne depend pas du
nombre de propositions.

## Decision

1. **Sur toute mecanique du domaine, une manche resolue apres au moins un essai infructueux n'est
   pas comptee comme une reussite.** Elle reste comptee comme une manche presentee : `tentatives()`
   monte de un, `reussites()` ne monte pas.
2. **Une manche trouvee du premier essai reste comptee comme une reussite.** Cette moitie borne le
   remede par le haut et n'est pas decorative : sans elle, une mecanique qui ne crediterait plus rien
   satisferait la premiere moitie sans rien tenir.
3. **`Score` reste un record de deux entiers**, `ESSAIS_AVANT_REVELATION` reste a 3, et le nombre de
   propositions d'un item n'entre pas dans la condition. Le remede est une expression dans
   `resoudre` de chaque session, et rien d'autre.
4. **Le texte que l'ecran de fin de partie montre a l'enfant n'est pas decide ici.** `nadia-prof`
   s'en declare la seule garde. Cet `ADR` decide ce que le score compte, pas ce que l'ecran dit.

## Regle executable

**Fichier** : `domain/src/test/java/com/recreo/domain/archi/UneMancheResolueApresUnEssaiInfructueuxNEstPasUneReussiteTest.java`
(classe neuve, ecrite dans le meme geste que cet `ADR`).

**Pourquoi ce n'est pas une regle ArchUnit, et ce que cela coute.** La propriete est un
comportement, pas une forme : aucun modele de types ne dit si un compteur monte. ArchUnit sert ici a
la seule chose pour laquelle il est le bon outil - **deriver la population** du graphe de classes,
pour qu'une mecanique neuve entre dans la regle le jour ou son type est ecrit, et non le jour ou
quelqu'un pense a l'ajouter (`ADR-0013`). Le jugement, lui, est un scenario joue sur la session
reelle de chaque mecanique, sans navigateur, sans graine et sans jugement humain.

Trois methodes :

| Methode | Ce qu'elle juge |
|---|---|
| `chaqueMecaniqueConcreteDuDomainePorteSonScenario` | La population vient de `com.recreo.domain.mechanics..`, filtree sur l'implantation de `GameMechanic` (non interface, non abstraite), jamais d'une liste ecrite dans le fichier. Une mecanique neuve sans scenario rend ce test rouge. Clause de non-vacuite d'`ADR-0051` incluse |
| `uneMancheTrouveeApresUnEssaiFauxNEstPasComptee` | La decision 1, `SCO-1` et `SCO-2` generalisees aux trois mecaniques : un essai faux, puis la bonne reponse, alors `tentatives() == 1` et `reussites() == 0` |
| `uneMancheTrouveeDuPremierEssaiResteUneReussite` | La decision 2, `SCO-4` : la bonne reponse du premier coup, alors `reussites() == 1` et `tentatives() == 1` |

**Etat mesure, dans un worktree dedie sur `f705d62a`, JDK 21.0.10 rapporte par la machine virtuelle
des tests, `mvn -pl domain -am -Dtest=<classe>` :**

- **sans le remede : rouge**, sur `uneMancheTrouveeApresUnEssaiFauxNEstPasComptee`, avec le message
  de `DEC-0061` ; les deux autres methodes vertes ;
- **avec le remede applique aux trois sessions** (`juste && essaisSurLaManche == 0` sur deux d'entre
  elles, `juste && tentatives.isEmpty()` sur `SaisieSession`), **applique puis revert dans le meme
  worktree, jamais commite** : **vert, 3 tests sur 3, sur les trois mecaniques**.

**Ce que cette regle interdit reellement.** Elle interdit qu'une mecanique credite une manche que
l'enfant n'a pas trouvee du premier coup - le defaut mesure. Et elle interdit le contournement
symetrique, une mecanique qui ne crediterait plus rien. La question de savoir ce qu'une regle
interdirait a quelqu'un qui essaierait de mal faire a deux reponses ici, et c'est le motif pour
lequel la seconde methode existe.

**Ce que cette regle ne tient pas, nomme plutot que laisse decouvrir.** `SCO-3` est ecrite au niveau
du catalogue - *le score final d'une strategie aveugle n'est pas parfait*. Sous le remede, un score
parfait redevient possible pour une strategie aveugle **chanceuse** : c'est une propriete de la
graine, pas du produit, et une regle qui l'epinglerait serait un faux rouge en attente. Elle n'est
donc pas encodee, et la regle ci-dessus rend `SCO-3` vraie en esperance - c'est dit ici plutot que
sous-entendu. `SCO-5`, `SCO-6` et `SCO-7` sont deja tenues, mecanique par mecanique, par
`BanditManchotTest`, `DesignationTest` et `SaisieTest` ; elles ne sont pas recopiees.

**Correction d'un enonce que j'avais ecrit a s33, et elle va contre moi.** Mon avis de s33 ecrivait
que la regle devait dire qu'*"une strategie aveugle n'obtient pas de reussite comptee"*. C'est faux
sous la famille A : une strategie qui presse dans l'ordre affiche tombe sur la bonne du premier
appui environ une fois sur trois, et cette manche-la doit compter - c'est un enfant qui a repondu
juste du premier coup. J'avais mesure le remede sur l'etat de depart et ecrit son enonce sur cet
etat-la, la faute exacte que `RES-2` interdit.

## Consequences

- **Le temoin de `DeuxCoupsSimultanesTest` est detruit par le remede, et il est remplacable.** Ce
  test garde `ADR-0019` - le double appui qui faisait perdre un point, signale par l'utilisatrice et
  reproduit par `yanis-qa` a 14 pertes sur 40 essais. Il joue une bonne reponse et une mauvaise en
  parallele, soixante fois, et exige que le score qui fait foi vaille **toujours 1**. Sous le remede,
  ce chiffre depend de l'ordre reellement applique : si la mauvaise passe la premiere, la manche a
  coute un essai et la bonne qui suit ne se credite plus - **c'est exactement ce que la decision
  demande**. La propriete que `ADR-0019` protege, *aucune ecriture n'en ecrase une autre*, reste
  vraie ; c'est le temoin choisi qui cesse d'en etre un.
  **Le temoin de remplacement** : sous verrou, `score.tentatives()` vaut exactement **1** apres les
  deux appuis simultanes, quel que soit l'ordre ; sans verrou, la derniere ecriture ecrase l'autre et
  il peut valoir **0**. Une assertion remplacee, pas un test reecrit. **Ce geste appartient a
  `sofia-sm`** (planning de s34, section 7) : cet `ADR` l'ecrit pour qu'elle le trouve, et pour que
  ce rouge attendu ne soit pas lu comme une regression d'`ADR-0019` ni de la concurrence.
- **Les 27 jeux dont le score change.** Le comptage devient plus severe pour tout paquet ou
  l'elimination est possible. Le sort des Done pedagogiques deja prononces sur ces jeux n'est pas
  tranche ici : la question est posee a l'utilisatrice par `DEC-0060`, section 8, `Q2`.
- **Un enfant qui se reprend voit desormais un score plus bas qu'avant.** C'est l'effet voulu, et
  c'est aussi ce qui rend le texte de l'ecran de fin de partie une question pedagogique ouverte, a
  `nadia-prof` seule.
- **Cette regle est rouge le jour ou elle est ecrite**, et le reste tant que le remede n'est pas
  applique. C'est voulu : une regle qui epinglerait aujourd'hui le comportement que le remede doit
  changer ne garderait rien. La branche `s34-theo` porte donc un rouge par construction, et devient
  verte a la fusion avec l'implementation des trois sessions. **Ce rouge est nomme ici pour que
  personne ne le decouvre a la fusion.**
- **Le cout d'entretien d'une mecanique neuve monte d'un scenario** (six lignes). C'est le prix de la
  derivation totale, et il est prefere a l'alternative connue : une regle qui ne juge que les
  mecaniques auxquelles quelqu'un a pense.

## Alternatives considerees

- **Famille B - reduire le nombre d'essais quand l'elimination est possible.** Ecartee par
  `nadia-prof`, decision pedagogique : l'enfant y perd le droit de se reprendre. Elle aurait touche
  `ESSAIS_AVANT_REVELATION` et fait dependre la mecanique du nombre de propositions de l'item, c'est
  a dire du contenu.
- **Augmenter le nombre de propositions pour que l'elimination cesse d'etre exhaustive.** Ecartee :
  `ADR-0063` la borne, et surtout elle ne repare pas le comptage - elle rend seulement le defaut plus
  couteux a exploiter. Mon avis de s33 la disait indisponible ; sous la famille A l'argument devient
  inutile, le remede ne depend plus du nombre de propositions.
- **Remelanger les propositions apres une erreur.** Refusee par `nadia-prof` et par moi : elle ne
  defait qu'une strategie de position, pas l'elimination.
- **Encoder `SCO-3` au niveau du catalogue.** Ecartee : faux rouge en attente sur une graine
  chanceuse (voir ci-dessus).
- **Porter le compte des essais dans `Score`** (un troisieme entier, ou un record par manche).
  Ecartee : aucun usage constate en aurait besoin, la propriete se juge sur deux entiers, et un champ
  de plus rouvrirait tous les sites de construction du score. Ce qui me ferait changer d'avis : un
  besoin ecrit de montrer a l'enfant le nombre d'essais depenses, qui appartiendrait a `nadia-prof`
  et pas a moi.
