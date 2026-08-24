# ADR-0061 - Un test ne recopie pas l'association que le catalogue porte, et l'absence d'entree reste le seul mecanisme de retrait

- **Statut** : Accepte
- **Date** : 2026-08-24
- **Auteur** : theo-archi
- **Sprint** : hors sprint - reliquat de la cloture de s19, question routee par `sofia-sm` et par
  `camille-po`

## Contexte

`DEC-0045` retire l'entree `bandit-manchot-nature-mot-cp` de `catalogue.yaml` et garde le paquet
`cp-nature-mot` au depot, inchange. `sofia-sm` a execute le retrait ; le reacteur est devenu rouge
sur `PaquetCpNatureMotTest.lEntreeDeCatalogueDesigneLeBonPaquet`. Elle n'a ni adapte le test ni
contourne le rouge, et elle a nomme l'etat des garde-fous de `bootstrap` comme une **inconnue
reelle** - le reacteur s'arretait sur `adapter-content`, donc `bootstrap` n'avait jamais tourne sur
cet arbre. Trois questions m'ont ete routees : que doit assertionner un test de paquet quand le
paquet reste servi sans etre expose ; quel est l'etat reel des garde-fous de `bootstrap` ; faut-il
un mecanisme de statut explicite dans le schema du catalogue.

### Ce que le rouge est reellement, et il ne s'agit pas d'une question ouverte

`ADR-0045` a tranche cette question le 2026-08-17, avec son chiffre et sa methode. Il a acte le
retrait de cinq methodes figees, une par entree, qui fixaient le nom du paquet en constante Java a
cote de l'identifiant du jeu, et il en a ecrit le motif :

> **une constante Java par jeu recopie le catalogue dans un second endroit.** C'est la famille de la
> dette 3 de la version precedente, et le couplage exact qui a casse en s13 : `DEC-0031` a retire
> `bandit-manchot-grands-nombres-cm1` du catalogue et la methode de test est restee derriere,
> corrigee a la main dans un commit separe.

**Rien n'a garde cette decision.** Le motif est revenu en s17 (`99e2ee6`, `PaquetCm1SynonymesTest`),
puis quatre fois en s19 (`087b078`, `99d0a75`, `1a53ee4`, `1ff200d`), sous un autre nom de methode -
`lEntreeDeCatalogueDesigneLeBonPaquet` au lieu de `leJeuDuCatalogue...ChargeBienLePaquet...`. Aucun
document de `squad/` ne mentionne `ID_DE_CATALOGUE` : la reintroduction n'a jamais ete decidee ni
relue contre l'`ADR` qui l'interdisait. Elle a casse une seconde fois, a l'identique, au premier
retrait d'entree venu.

C'est donc la famille que ce depot sanctionne le plus souvent : **une regle ecrite, acceptee, et
qu'aucun automate ne tient**. La reponse a la question de `sofia-sm` n'est pas un arbitrage neuf,
c'est l'application d'`ADR-0045` plus le garde-fou qui lui manquait.

### Ce que le retrait coute reellement, mesure et non deduit

**Methode.** Perimetre : reacteur complet, `mvn -B clean verify` sur la racine, jamais une mesure
partielle. Le rouge de `sofia-sm` a ete rejoue sur son etat exact
(commit de fusion de `DEC-0045`), avec `-Dmaven.test.failure.ignore=true` pour que le reacteur
depasse `adapter-content` et atteigne `bootstrap`, ce que son execution n'avait jamais fait. JDK de
la toolchain declaree, `21.0.10`. Six modules construits, aucun ignore.

| Etat mesure | Modules rouges | Tests en echec |
|---|---|---|
| Retrait sur l'arbre de s19 (etat de `sofia-sm`) | `adapter-content`, **`bootstrap`** | **2** |
| Retrait sur l'arbre corrige par cet `ADR` | `bootstrap` seul | **1** |
| Arbre corrige, sans retrait | aucun | 0 (668 tests) |

**L'inconnue de `sofia-sm` etait fondee : le retrait faisait bien rougir plus que le seul test
qu'elle avait vu.** Le second echec est
`TexteAccentueExposeParLePortEntrantTest.chaqueChaineDeLInventaireEstEncoreExposeeParLeCatalogueReel`,
dans `bootstrap`, sur quatre chaines devenues orphelines - l'identifiant, le titre, la consigne et
la competence du jeu retire. Ce rouge-la est **correct et voulu** : c'est le versant qui empeche
l'inventaire de pourrir, et son propre message porte la marche a suivre (*"Si la chaine a disparu,
l'entree part dans le meme commit"*). Il n'appelle aucune decision, seulement l'edition qu'il
prescrit.

Les deux autres garde-fous de coherence du catalogue -
`ChaqueEntreeDuCatalogueChargeBienSonPaquetTest` et `AucuneEntreeDuCatalogueNEnDoubleUneAutreTest` -
sont restes **verts** sous le retrait, tous les deux : leur population se derive du catalogue, donc
une entree retiree en sort au lieu de les faire mordre. C'est exactement ce qu'`ADR-0045` avait
prevu.

### Les deux formes de couplage, qui n'en sont pas une seule

Le balayage de `adapter-content` a trouve six endroits qui nomment une entree de catalogue. Ils se
rangent en deux familles, et les confondre ferait ecrire la mauvaise regle.

1. **La recopie de l'association** (5 occurrences : `PaquetCm1SynonymesTest`,
   `PaquetCm1AntonymesTest`, `PaquetCm1AccordSujetVerbeTest`, `PaquetCm1AccordsGroupeNominalTest`,
   `PaquetCpNatureMotTest`). Chacune declare le couple `PAQUET` / `ID_DE_CATALOGUE` en constantes et
   verifie `entree.get("paquet")` contre `PAQUET`. C'est le motif d'`ADR-0045`, et il ne garde rien :
   ce qu'il compare, `catalogue.yaml` le dit deja.
2. **Le jugement d'une propriete de l'entree exposee** (`ADR-0052` : la consigne reste generique et
   ne nomme aucune bonne reponse ; et `PaquetCm1MesuresTest.leTitreDuJeuAnnonceLesTroisGrandeurs`,
   `C-9`). Celle-la garde une propriete reelle, qui n'existe que tant que le jeu est expose. Elle
   **doit** rougir quand le jeu quitte le catalogue - au meme titre que l'inventaire de `bootstrap`,
   et pour la meme raison : une clause sans objet part avec son objet.

Les quatre clauses de la famille 2 portaient la famille 1 en supplement, greffee sur la meme
recherche d'entree. C'est ce qui a rendu le defaut invisible pendant trois sprints.

## Decision

### 1. Aucun test ne recopie en constantes l'association (entree, paquet) du catalogue servi

Un test de paquet charge son contenu **par le nom du paquet**, jamais par l'entree qui l'expose. Un
test qui juge une propriete de l'entree exposee trouve cette entree **par le paquet qu'elle sert**,
et son echec, le jour d'un retrait, nomme le retrait.

### 2. La clause purement associative est retiree, les clauses de consigne sont conservees

`PaquetCpNatureMotTest.lEntreeDeCatalogueDesigneLeBonPaquet` est supprimee : elle ne portait que la
famille 1. Les quatre clauses de consigne (`ADR-0052`) sont conservees, amputees de la ligne
`assertThat(entree.get("paquet")).isEqualTo(PAQUET)` et de la constante `ID_DE_CATALOGUE` ; elles
resolvent desormais leur entree par `PAQUET`.

### 3. L'absence d'entree reste le seul mecanisme de retrait - pas de champ de statut dans le schema

C'est la question que `camille-po` route sans la trancher. Je la tranche : **non**, et le motif
n'est pas la prudence.

- **Le mecanisme de retrait n'a pas cause ce rouge.** La cause est un test qui violait `ADR-0045`.
  Un champ `statut:` ne l'aurait pas empeche : `PaquetCpNatureMotTest` cherchait l'entree par son
  identifiant, il l'aurait trouvee avec `statut: retire` a cote, et serait **passe vert sur un jeu
  retire**. Le drapeau transforme le rouge d'aujourd'hui en faux vert.
- **Le statut d'un jeu vit deja dans la fiche de conformite**, et `DEC-0045` section 3 en fait la
  seule autorite. L'ecrire aussi dans `catalogue.yaml` recopierait une intention qui vit ailleurs -
  la meme faute qu'au point 1, un cran plus haut.
- **L'etat "non expose" est derivable, aujourd'hui, sans aucun champ** : c'est l'ensemble des
  paquets de `contenu/` qu'aucune entree ne designe. *Methode et chiffre : les fichiers `*.yaml` du
  repertoire de contenu de `adapter-content` moins les valeurs du champ `paquet` de
  `catalogue.yaml`, sur `round-two` a `75e48c3`, le 2026-08-24 : **17 paquets, 17 entrees, ensemble
  vide**.* Les quatre retraits precedents (`DEC-0009`, `DEC-0014`, `DEC-0024`, `DEC-0031`) ont tous
  ete rendus ; `DEC-0045` sera le premier occupant vivant de cet etat. Un champ de schema pour un
  etat a zero occupant est une abstraction anticipee, ce que ce depot refuse.
- **Ce qui me ferait changer d'avis**, ecrit maintenant pour qu'on n'ait pas a le deviner : le jour
  ou plusieurs jeux sont retires en meme temps et ou la question *"pourquoi celui-la n'est-il pas
  expose ?"* n'a plus de reponse dans le depot sans lire cinq `DEC` ; ou le jour ou un retrait doit
  etre reversible sans toucher au catalogue. Aucune des deux conditions n'est realisee.

## Regle executable

**Fichier** :
`adapters/adapter-content/src/test/java/com/recreo/adapter/content/AucunTestNeRecopieUneAssociationDuCatalogueTest.java`

```java
static void verifierQuAucuneClasseNeRecopieUneAssociation(Collection<Class<?>> classes,
                                                          Map<String, String> associations) {
    assertThat(associations)
            .as("ADR-0051 : cette clause ne prouve rien si le catalogue servi ne porte aucune association")
            .isNotEmpty();
    assertThat(classes)
            .as("ADR-0051 : cette clause ne prouve rien si elle n'examine aucune classe")
            .isNotEmpty();

    List<String> recopies = new ArrayList<>();
    for (Class<?> classe : classes) {
        Set<String> constantes = constantesTextuellesDe(classe);
        for (Map.Entry<String, String> association : associations.entrySet()) {
            if (constantes.contains(association.getKey())
                    && constantes.contains(association.getValue())) {
                recopies.add("%s recopie l'association \"%s\" vers \"%s\""
                        .formatted(classe.getName(), association.getKey(), association.getValue()));
            }
        }
    }

    assertThat(recopies).as("ADR-0061 : ...").isEmpty();
}
```

**Ce n'est pas une regle ArchUnit**, et le motif est le meme qu'`ADR-0059` et `ADR-0060` : l'objet
juge est la **valeur** d'une constante compilee, qu'ArchUnit n'expose pas dans son modele. Le
mecanisme automatique qui garde la decision est un balayage par reflexion des classes compilees du
module, execute par la meme commande de porte technique que tout le reste (`mvn -B verify`). **Ce
lot n'ajoute aucune `ArchRule` ; le compte de regles ArchUnit du depot est donc inchange par lui.**

**Les deux populations se derivent, aucune ne s'enumere** (`ADR-0013` obligation 1) : les
associations viennent de `catalogue.yaml` lui-meme, les objets examines sont toutes les classes
compilees du module de test, balayees depuis la racine ou elles vivent. Une entree neuve entre dans
la population, et une entree retiree en sort, sans qu'une ligne du garde-fou ne change.

**Polarite** (`ADR-0039`) : un objet neuf - un paquet de plus, avec son fichier de test ecrit
normalement - reste **vert** ; c'est la copie du couple qui rougit. Le mode de defaillance d'un
ajout legitime est un vert, celui du geste fautif un rouge qui se nomme.

### Morsure prouvee sur materiau reel, pas sur une fixture

La lecon de s05 s'applique a ce garde-fou comme aux autres : une fixture ecrite par l'auteur d'une
regle ressemble a ce qu'il imagine. La preuve porte donc sur l'arbre tel qu'il etait ecrit. Le
garde-fou a ete execute **avant** toute correction, sur les cinq classes que s17 et s19 avaient
livrees, et il les a nommees toutes les cinq en une execution :

```
Expecting empty but was: ["...PaquetCm1AccordsGroupeNominalTest recopie l'association "bandit-manchot-accords-groupe-nominal-cm1" vers "cm1-accords-groupe-nominal"",
    "...PaquetCm1AccordSujetVerbeTest recopie l'association "bandit-manchot-accord-sujet-verbe-cm1" vers "cm1-accord-sujet-verbe"",
    "...PaquetCm1AntonymesTest recopie l'association "bandit-manchot-antonymes-cm1" vers "cm1-antonymes"",
    "...PaquetCm1SynonymesTest recopie l'association "bandit-manchot-synonymes-cm1" vers "cm1-synonymes"",
    "...PaquetCpNatureMotTest recopie l'association "bandit-manchot-nature-mot-cp" vers "cp-nature-mot""]
```

Une premiere ecriture de la clause n'en nommait qu'une seule, par une assertion dans la boucle :
elle a ete reecrite pour les collecter toutes. Un garde-fou qui nomme un cinquieme de ce qu'il
trouve fait recommencer la mesure quatre fois.

Deux bornes permanentes accompagnent la clause, sur des valeurs volontairement absentes du catalogue
servi (donc vertes au balayage reel) : une classe qui recopie le couple **doit** faire echouer la
verification, et une classe qui ne nomme que le paquet **doit** passer - c'est ce que fait tout test
de paquet legitime, et une regle qui refuserait aussi ce cas-la serait ininstallable.

### Ce que ce garde-fou ne couvre pas

1. **Il ne lit que des constantes, pas des litteraux en ligne.** La valeur d'un champ
   `static final String` se lit exactement dans le bytecode ; un litteral ecrit dans le corps d'une
   methode ne se distinguerait d'une reference a une fiche de conformite ou a un catalogue fabrique
   qu'en devinant l'intention, et une recherche de sous-chaine attraperait
   `FICHE-bandit-manchot-additions-cp.md` - la faute exacte de s05. Prix nomme : un test qui resout
   une entree par un identifiant ecrit en ligne echappe a la clause. Un cas existe,
   `PaquetCm1MesuresTest.leTitreDuJeuAnnonceLesTroisGrandeurs`, et il **n'est pas le meme defaut** :
   il releve de la famille 2. Sa faiblesse est ailleurs, elle est nommee en consequences.
2. **Il ne couvre que les classes de test de `adapter-content`.** Un test de paquet ecrit ailleurs
   lui echapperait ; aucun n'existe, les paquets et le catalogue vivent dans ce module.
3. **Il ne juge pas la justesse de l'association** ecrite dans le catalogue. C'est precisement la
   propriete qu'`ADR-0045` a echangee, chiffre a l'appui, et qui appartient a `nadia-prof` et a la
   fiche de conformite. Cet `ADR` ne la restitue pas et ne pretend pas le faire.
4. **Il ne verifie pas qu'un jeu portant une reserve bloquante est hors du catalogue.** C'est la
   regle generale de `DEC-0045` section 3. Elle suppose de decider, par automate, si une reserve
   d'une fiche de conformite est "bloquante" et "encore ouverte" : deux jugements pedagogiques lus
   dans du texte libre. Un garde-fou bati sur une recherche de mot dans ces fiches serait de la
   famille du critere enumere vert par defaut. **Dette explicite, ouverte ici plutot que passee sous
   silence** ; sa fermeture suppose que les reserves portent une forme lisible par machine, ce qui
   est un choix de format de fiche appartenant a `nadia-prof` et a `camille-po`.

## Consequences

- Le retrait de `DEC-0045` peut etre rejoue en **une seule fois et en vert**. Il coute exactement
  deux editions, mesurees : l'entree hors de `catalogue.yaml`, et quatre chaines hors de
  l'inventaire de `TexteAccentueExposeParLePortEntrantTest` (l'identifiant, le titre, la consigne, la
  competence). La derniere de ces quatre chaines ferme la liste : la virgule de la ligne precedente
  devient le terminateur.
- `cp-nature-mot` reste **entierement couvert** en tant que paquet, expose ou non : mesure, les 181
  tests de `adapter-content` restent verts sous le retrait. La crainte que le paquet perde sa
  couverture en quittant le catalogue est infondee ; aucune des huit clauses restantes de
  `PaquetCpNatureMotTest` ne passe par le catalogue.
- **Une propriete est perdue et je la nomme** : plus rien ne verifie qu'une entree de catalogue
  designe le paquet qu'un auteur de test croyait. Ce n'est pas une perte de ce lot - `ADR-0045`
  l'avait deja actee et chiffree (les cinq methodes figees attrapaient 55 mutations sur 121, en
  laissaient 66) ; ce lot cesse de faire semblant qu'elle etait revenue.
- **Faiblesse heritee, non traitee ici** :
  `PaquetCm1MesuresTest.leTitreDuJeuAnnonceLesTroisGrandeurs` resout son entree par un identifiant
  en ligne et un `orElseThrow()` nu. Son rouge, le jour ou "Les mesures" quitterait le catalogue,
  serait un `NoSuchElementException` sans message - correct sur le fond, illisible sur la forme. Je
  ne le reecris pas dans ce lot : il n'est pas sur le chemin de `DEC-0045` et le toucher elargirait
  un lot deja etendu a quatre fichiers de test qui ne sont pas les miens.
- Le garde-fou lit les classes compilees par reflexion, `setAccessible` compris. C'est une piece
  mobile de plus qu'un balayage de fichiers, et elle depend de la disposition du repertoire de
  classes du module. En contrepartie elle lit des valeurs exactes, la ou un balayage de source
  lirait des sous-chaines.
- Si deux entrees servaient un jour le meme paquet sous deux mecaniques - ce qu'`ADR-0004` et
  `ADR-0012` promettent explicitement - les clauses de consigne reecrites n'en jugeraient qu'une.
  Aucune n'existe : une seule mecanique est servie.

## Alternatives considerees

- **Rendre le test conditionnel a l'exposition** (*"si l'entree existe, alors..."*). Ecartee : un
  test qui ne verifie rien quand la condition est fausse est vert par defaut sur tout jeu non
  expose, c'est-a-dire exactement la polarite qu'`ADR-0039` refuse. Et il aurait masque le fait que
  la clause ne gardait rien meme quand la condition etait vraie.
- **Faire sortir le paquet en meme temps que l'entree.** Ecartee, et sans avoir a invoquer
  `DEC-0045` qui le refuse deja : la mesure montre que rien ne l'exige techniquement. Le paquet reste
  charge, teste et vert sans son entree. Il n'y a pas de consequence cachee a signaler a
  `camille-po` sur ce point.
- **Un champ `statut:` dans le schema du catalogue.** Ecartee, motifs en decision, section 3.
- **Une regle qui interdit a tout test de nommer un identifiant vivant du catalogue.** Ecartee apres
  verification : elle aurait rougi sur
  `AucuneEntreeDuCatalogueNEnDoubleUneAutreTest.unCatalogueQuiDoubleUnIdentifiantDeJeuFaitEchouerLeGardeFou`,
  qui nomme `bandit-manchot-perimetre-cm1` dans une **mise a l'epreuve sur un catalogue fabrique** -
  un usage legitime. Elle aurait aussi rougi sur l'inventaire de `bootstrap`, qui est deja garde a
  deux versants. Il aurait fallu l'exempter par une liste de noms, c'est-a-dire remplacer un critere
  derive par un critere enumere.
- **Analyser le pool de constantes des fichiers de classe** plutot que les seuls champs, pour
  attraper aussi les litteraux en ligne. Ecartee : elle attrape le litteral fautif et, du meme
  geste, la mise a l'epreuve legitime ci-dessus, qu'il faudrait exempter par son nom. Plus de pieces
  mobiles pour une couverture qui se paie en exemptions enumerees.
- **N'ecrire aucun `ADR` et se contenter de corriger les cinq classes**, `ADR-0045` ayant deja
  decide. Ecartee : c'est precisement ce qui a ete fait en s14, et le motif est revenu cinq fois en
  deux sprints. Une decision que rien n'execute est un souhait, et ce depot a deja sanctionne
  plusieurs fois cette famille-la.

## Impact

- `adapters/adapter-content/src/test/java/.../AucunTestNeRecopieUneAssociationDuCatalogueTest.java`
  (le garde-fou) : cree.
- `PaquetCpNatureMotTest` : clause `lEntreeDeCatalogueDesigneLeBonPaquet`, constante
  `ID_DE_CATALOGUE`, aide `catalogueCharge()` et trois imports retires ; note d'en-tete ajoutee.
- `PaquetCm1SynonymesTest`, `PaquetCm1AntonymesTest`, `PaquetCm1AccordSujetVerbeTest`,
  `PaquetCm1AccordsGroupeNominalTest` : constante `ID_DE_CATALOGUE` retiree, clause de consigne
  conservee et rebranchee sur `entreeQuiSertLePaquet()`.
- `squad/decisions/adr/ADR-index.md` : ligne `ADR-0061` ajoutee.
- `catalogue.yaml` : **inchange**. Le retrait de `DEC-0045` est le geste de `sofia-sm` et n'est pas
  execute ici.
