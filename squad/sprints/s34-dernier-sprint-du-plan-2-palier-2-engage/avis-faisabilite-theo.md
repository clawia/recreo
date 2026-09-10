# Avis de faisabilite technique - s34 (`theo-archi`)

- **Auteur** : `theo-archi`. **Date** : 2026-09-10.
- **Arbre mesure** : `round-two` a `48635ba0`, tete laissee par la cloture de s33. Rien n'est pousse.
- **JDK 21.0.10** - `java.runtime.version=21.0.10+8-LTS-217`, `Java HotSpot(TM) 64-Bit Server VM`,
  **valeurs que la machine virtuelle qui execute les tests rapporte d'elle-meme** dans le bloc de
  proprietes des rapports Surefire de mes builds, jamais lues dans l'entree de toolchain qui la
  designe (`ADR-0071`). Enveloppe : `bash outils/mvn-jdk21.sh`.
- **Ou j'ai mesure.** Le checkout partage porte, pendant que j'ecris, les fichiers non commites de
  `camille-po` et de `nadia-prof`. **Toutes les mesures de build de ce document sont prises dans un
  worktree detache sur `48635ba0`**, ou je n'ai copie que mes propres fichiers, puis retire. C'est
  exactement le geste dont la section 4.2 traite.
- **Ce document ne fait pas** : il ne prononce aucun Done, ni technique ni pedagogique ; il ne rejuge
  aucun verdict de s33 ; il ne redige aucune story, n'en priorise aucune et n'affecte personne ; il
  ne touche ni `docs/backlog/backlog.md`, ni `squad/programme/MATRICE-couverture.md`, ni les fichiers
  de `camille-po` et de `nadia-prof` en cours d'ecriture. **Rien n'est commite, rien n'est pousse.**
- **Emplacement.** Le repertoire de sprint de s34 n'existe pas encore ; ce fichier est a la racine de
  `squad/sprints/` et `sofia-sm` le deplacera au planning.

---

## 1. Les deux tests d'`EMC-CP-07` - **reprenables sans toucher a une clause, et la reprise passe sur le contenu d'aujourd'hui**

**Avis : oui, les deux tests sont reprenables sans qu'aucune clause pedagogique ne cede. Le defaut
est bien dans les tests, `nadia-prof` a raison sur les deux. Cout : une vingtaine de lignes dans un
seul fichier de test, aucun fichier de production, aucun contenu.** Et le point qui compte pour son
prealable : **la reprise est verte sur le contenu servi d'aujourd'hui comme sur celui d'arrivee**,
elle n'a donc pas a attendre la correction de contenu pour etre ecrite.

### 1.1 La mesure d'abord - ce que la sortie refusee fait rougir, sur le reacteur entier

*Methode : mutation du paquet servi `contenu/cp-emc-regle-souhait-loi.yaml` dans un worktree detache
sur `48635ba0` - les items 4, 5 et 6 redeviennent binaires (`une loi` retire de toutes leurs
propositions, l'item 4 prenant `une règle qui vaut pour tous` en bonne reponse), rien d'autre.
`bash outils/mvn-jdk21.sh -B clean verify` sur la racine, reacteur complet a six modules, avec
`-Dmaven.test.failure.ignore=true` pour que les six modules s'executent tous plutot que de s'arreter
au premier rouge. Worktree supprime, arbre partage jamais touche.*

> **1 443 tests, 2 echecs, et ce sont exactement les deux que `nadia-prof` nomme.** Tous deux dans
> `PaquetCpEmcRegleSouhaitLoiTest` ; les cinq autres modules sont verts.

```
[ERROR] PaquetCpEmcRegleSouhaitLoiTest.bin1AuPlusLaMoitieDesItemsDeClassementSontBinaires:136
        [BIN-1 : au plus la moitie des 6 items de classement sont binaires (2 propositions), or 6 le sont]
[ERROR] PaquetCpEmcRegleSouhaitLoiTest.fer1LesTroisTermesSontEnBonneReponseDeLaTacheDeClassement:105
        [FER-1 : regle, souhait et loi doivent tous etre representes en bonne reponse]
```

**Ce que cette mesure achete, et c'est le motif pour lequel je l'ai faite plutot que de raisonner.**
Elle etablit qu'**aucun troisieme garde-fou ne mord**. Je m'attendais a en trouver un : rendre les
items 4, 5 et 6 binaires porte a six le nombre d'items de la tache A qui partagent le meme couple de
propositions, et la clause 1 d'`ADR-0086` refuse trois items ou plus coules dans le meme moule de
propositions **et portant la meme bonne reponse**. Elle ne mord pas ici parce que le groupe porte
**deux** bonnes reponses distinctes - `une règle qui vaut pour tous` et `un simple souhait` - et que
la clause exige une seule. **Je l'avais deduit avant de mesurer ; je le dis parce que la deduction
n'aurait pas suffi, et parce que c'est le genre de rouge qui aurait ete decouvert au milieu de la
branche.**

### 1.2 `fer1...` - le test lit le troisieme terme dans la mauvaise tache

**Clause `FER-1`, mot pour mot** (`ARBITRAGE-PREALABLE-s28...`, section 2) : *"Chaque element
enumere par le libelle de la competence [...] est servi par au moins un item ou il est en position de
bonne reponse."*

**Ce que le test exige** : que l'ensemble des bonnes reponses **de la seule tache de classement**
soit exactement `{"une règle qui vaut pour tous", "un simple souhait", "une loi"}` -
`containsExactlyInAnyOrderElementsOf`, sur `itemsDeClassement()`.

**Les deux ecarts, et ils vont dans le meme sens.** Le test ajoute une restriction que la clause ne
porte pas - *dans la tache de classement* - et il transforme la troisieme clause du libelle
(*"reconnaitre qu'une loi s'impose a tous"*) en une **etiquette litterale a presser**. Sur le paquet
servi, cette troisieme clause est deja servie en position de bonne reponse par les cinq items de la
tache B, dont chaque bonne reponse designe qui la loi oblige. **La clause est tenue ; c'est le test
qui la mesure ailleurs.**

**La reprise, et je la donne ecrite parce qu'un avis qui dit *"c'est reprenable"* sans montrer quoi
ne coute rien a qui l'ecrit :**

```java
/** FER-1 : les deux categories que la premiere moitie du libelle demande de distinguer. */
private static final Set<String> CATEGORIES_DU_CLASSEMENT = Set.of(
        "une règle qui vaut pour tous", "un simple souhait");

@Test
void fer1LesDeuxCategoriesDuClassementSontChacuneEnBonneReponse() {
    assertThat(itemsDeClassement().stream().map(Question::bonneReponse)
            .collect(Collectors.toSet()))
            .as("FER-1, premiere moitie du libelle : regle et souhait sont chacun servis en"
                    + " position de bonne reponse")
            .containsAll(CATEGORIES_DU_CLASSEMENT);
}

@Test
void fer1LaSecondeMoitieDuLibelleEstServieParUneTacheQuiNeClassePas() {
    List<Question> portee = itemsDePortee();
    assertThat(portee).as("FER-1 : la seconde moitie du libelle est servie").isNotEmpty();
    for (Question item : portee) {
        assertThat(item.bonneReponse())
                .as("item %s : la portee de la loi se sert en designant qui elle oblige, jamais"
                        + " en reclassant la situation", item.id().value())
                .isNotIn(CATEGORIES_DU_CLASSEMENT);
    }
}
```

**Ce que cette reprise interdit reellement** - la question que je me pose sur chaque regle que
j'ecris. Elle interdit qu'une des deux categories du classement cesse d'etre servie en bonne reponse,
et elle interdit que la tache de portee disparaisse ou se replie sur un troisieme classement. **Elle
n'interdit plus** que `une loi` soit en bonne reponse d'un item de classement : c'est `EMC-11`, ce
n'est pas `FER-1`, et je le dis plutot que de laisser croire que ce test la couvre (section 1.5).

### 1.3 `bin1...` - le test mesure sur la tache, la clause mesure sur le paquet

**Clause `BIN-1`, mot pour mot** (`ARBITRAGE-PREALABLE-s31...`, section 5) : *"Sur une ligne dont la
tache principale est un classement a **deux** categories, **au plus la moitie des items d'un paquet**
porte deux propositions."* Le paragraphe de motif dit meme pourquoi le paquet est la bonne unite :
*"un paquet de fermeture porte onze items pour dix manches (`DEC-0064`), donc le tirage est presque
exhaustif et une propriete comptee sur le paquet vaut pour la partie."*

**Ce que le test mesure** : `binaires <= classement.size() / 2`, ou `classement` est la seule tache A.
Le denominateur est donc six au lieu de onze. **C'est un durcissement d'un facteur deux, jamais
decide, et il vient d'une seule expression.**

**La reprise tient en un mot :**

```java
@Test
void bin1AuPlusLaMoitieDesItemsDuPaquetSontBinaires() {
    List<Question> items = items();
    long binaires = items.stream().filter(item -> item.distracteurs().size() == 1).count();
    assertThat(binaires)
            .as("BIN-1 : au plus la moitie des %d items du paquet portent deux propositions,"
                    + " or %d les portent", items.size(), binaires)
            .isLessThanOrEqualTo(items.size() / 2);
}
```

### 1.4 Le cout, et le fait qui repond a la condition de `nadia-prof`

| | |
|---|---|
| Fichiers touches | **1**, `adapters/adapter-content/src/test/java/com/recreo/adapter/content/PaquetCpEmcRegleSouhaitLoiTest.java` |
| Fichiers de production touches | **0** |
| Fichiers de contenu touches | **0** |
| Methodes de test | **2 remplacees par 3** ; une constante renommee et retrecie |
| Volume | une vingtaine de lignes, plus la Javadoc qui dit pourquoi |

> **Et le fait qui compte pour son prealable : les trois methodes de reprise sont vertes sur le
> contenu servi d'aujourd'hui.** Verifie contre le paquet a `48635ba0` : les bonnes reponses de la
> tache A contiennent `règle` et `souhait` ; aucune des cinq bonnes reponses de la tache B n'est une
> categorie de classement ; et le paquet porte **3 items binaires sur 11**, tres au-dessous de 5.
> **La reprise n'a donc pas a attendre le contenu** : elle peut etre commitee avant l'ouverture de la
> branche, et elle sera encore verte apres l'une comme l'autre des deux sorties.

**Je ne l'ecris pas dans l'arbre, et je dis pourquoi.** Aucune story ne la porte, aucune branche
n'est ouverte, et ces deux tests se relisent contre le contenu qu'ils vont garder. **Quel commit la
porte est un choix de `sofia-sm`** ; ce qui etait du avant l'ouverture est l'avis, et il est ici.

### 1.5 Ce que la reprise ne couvre pas, nomme plutot que laisse decouvrir

- **`EMC-11` n'est encodee par aucun des trois tests.** Le refus numero 2 de l'arbitrage prealable de
  s34 exige que `une loi` quitte les propositions des items 5 et 6 ; apres la reprise, plus aucun
  test ne nomme `une loi`. **C'est encodable** - *aucune proposition de la tache de classement n'est
  `une loi`* - et cette clause-la, elle, est **rouge sur le contenu d'aujourd'hui**. Elle appartient
  donc au commit qui change le contenu, jamais avant. Je la donne pour qu'elle ne soit pas a
  re-instruire : `assertThat(itemsDeClassement()).allSatisfy(item -> assertThat(item.propositions())
  .doesNotContain("une loi"))`.
- **La reprise ne dit rien de la justesse d'un item.** `PLA-1`, `CAT-1`, `EMC-5` et l'exactitude des
  faits de droit restent la garde de `nadia-prof` seule, comme elle l'ecrit.

### 1.6 Sortie (a) ou sortie (b) - **(a) est la moins couteuse techniquement, et l'ecart n'est pas mince**

**Je ne choisis pas : le choix est pedagogique et il est a `nadia-prof`.** Je chiffre les deux.

| | **(a)** un des items 4/5/6 passe en tache B | **(b)** un douzieme item ternaire en tache B |
|---|---|---|
| Items du paquet apres coup | **11** | **12** |
| Items binaires | **5** | **6** |
| `BIN-1` mesuree sur le paquet | 5 sur 11 - **strictement sous la moitie**, une marge d'un demi-item | 6 sur 12 - **exactement la moitie**, **aucune marge** |
| `FER-2` (dix items au moins) | 11, tenue | 12, tenue |
| `DEC-0064` (plus d'items que de manches) | 11 pour 10 manches, tenue **au plancher exact**, inchangee | 12 pour 10, une marge |
| `FER-3` (la tache la moins servie, trois items au moins) | 5 et 6, tenue | 6 et 6, tenue |
| Items reellement rediges | **1 transpose** + 2 items dont on retire une proposition | **1 invente de bout en bout** + 3 items dont les propositions changent |
| Faits de droit neufs a verifier | **0 ou 1**, selon l'item deplace (voir ci-dessous) | **1**, obligatoirement |
| Items neufs a auditer pour `nadia-prof` | 0 | 1 |

**Deux points decident, et le second est le plus lourd.**

1. **La marge de `BIN-1`.** Sous (b) la clause est satisfaite a egalite : le jour ou un item binaire
   s'ajoute a la tache A, ou ou un item de la tache B disparait, le paquet redevient rouge. Sous (a)
   il reste un demi-item de marge. **Une clause tenue a egalite n'est pas tenue, elle est en
   equilibre.**
2. **Le fait de droit.** `nadia-prof` ecrit que le choix *"depend d'un fait de droit a verifier et de
   son cout"*. Verifie sur le paquet servi : **des trois items candidats, un seul se transpose sans
   fait neuf.** L'item 4 porte deja une obligation legale - le casque a velo avant douze ans - et sa
   tache A a ete confirmee bonne par `nadia-prof` au refus du 2026-09-07 ; le passer en tache B sous
   la forme *"Une loi ... Qui doit ... ?"* reemploie un fait deja servi et deja valide. Les items 5
   (le tour a la cantine) et 6 (le souhait d'Aya) ne portent **aucune loi** : les deplacer obligerait
   a en inventer une, c'est-a-dire a payer le meme cout que (b) tout en perdant un item de la
   tache A. **Sous (a), le cout technique depend donc entierement de l'item retenu, et c'est l'item 4
   qui coute zero fait neuf.**

**Deux reserves techniques que je pose sans les trancher.** Sous (a), la tache A tombe a cinq items
contre six a la tache B : savoir si le classement reste *"la tache principale"* au sens ou `BIN-1`
l'ecrit est une lecture de la clause, donc de `nadia-prof`. Et sous (a) comme sous (b), le nouvel
item de tache B doit porter **deux distracteurs** (`chaqueItemDeLaTacheDePorteeProposeExactementDeux\
Distracteurs`), une bonne reponse **distincte des cinq autres** (`fab1a...`) et ne pas etre le
sixieme item ou tous les distracteurs restreignent alors que la bonne reponse ne restreint pas -
c'est la clause 3 d'`ADR-0086`, celle-la meme qui a refuse ce paquet a s31, et elle mord sur le
paquet entier.

---

## 2. Le cout technique du remede de `DEC-0061` instruction 1 - **mesure, et l'affirmation de `nadia-prof` est vraie sur le contenu, incomplete sur le code**

**Sa phrase, rendue le 2026-09-10, telle qu'elle demande qu'elle soit citee** : *"Quand l'elimination
est possible, l'enfant garde ses trois essais, et le score cesse de compter comme trouvee une manche
qu'il n'a pas trouvee du premier coup."* C'est la **famille A**, et c'est la forme `A-1` que mon avis
de s33 retenait : `resoudre` cesse de crediter, `Score` reste un record de deux entiers.

### 2.1 Ce que j'ai mesure, plutot que verifie sur pieces

*Methode : dans un worktree detache sur `48635ba0`, le remede `A-1` applique aux trois sessions du
domaine, puis `bash outils/mvn-jdk21.sh -B clean verify` sur la racine, reacteur complet a six
modules, `-Dmaven.test.failure.ignore=true` pour que les six s'executent. Worktree supprime.*

| Module | Tests | Echecs sous le remede |
|---|---|---|
| `recreo-domain` | 105 | **0** |
| `recreo-application` | 13 | **0** |
| `recreo-adapter-web` | 388 | **1** |
| `recreo-adapter-content` | 693 | **0** |
| `recreo-bootstrap` (surefire) | 238 | **0** |
| `recreo-bootstrap` (failsafe) | 6 | **0** |
| **Total** | **1 443** | **1** |

> **Un seul test du depot entier rougit sous le remede** :
> `DeuxCoupsSimultanesTest.deuxCoupsSimultanesNeFontJamaisPerdreLePointGagne`.

### 2.2 L'affirmation de `nadia-prof`, verifiee point par point

| Ce qu'elle affirme | Verdict | Ce que j'ai mesure |
|---|---|---|
| *"le changement est identique sur les 66 entrees"* | **vrai pour ce que l'enfant vit** | Le remede vit dans la mecanique, pas dans une entree. Les 66 entrees de `bandit-manchot` passent toutes par `BanditManchotSession` |
| *"ne touche aucun item"* | **vrai, et mesure** | Zero fichier de `contenu/` touche, et les **693** tests de `recreo-adapter-content` restent verts |
| *"identique"* au sens du code | **inexact, et c'est le seul point** | Le geste est identique dans `BanditManchotSession` et `DesignationSession`, qui portent un champ `essaisSurLaManche` ; **`SaisieSession` n'en a pas** - elle porte `List<String> tentatives`, et la condition s'y ecrit `tentatives.isEmpty()`. Trois classes, deux ecritures |
| *"ce que j'ai a verifier est la mecanique une fois"* | **vrai pour son audit** | Rien dans ma mesure ne contredit son 0,5 |

**Et le point qu'elle ne pouvait pas voir, parce qu'il est dans un test** : le remede **detruit
l'assertion** d'un garde-fou de non-regression existant.

### 2.3 Le seul rouge, et pourquoi ce n'est pas une objection au remede

`DeuxCoupsSimultanesTest` garde `ADR-0019` - le double appui qui faisait perdre un point, signale par
l'utilisatrice et reproduit par `yanis-qa` a 14 pertes sur 40 essais. Il joue **une bonne reponse et
une mauvaise en parallele**, sur la meme session, soixante fois, et exige que le score qui fait foi
vaille **toujours 1**.

**Sous le remede, ce chiffre depend de l'ordre reellement applique**, et ce n'est pas un defaut de
verrou : si la mauvaise est traitee la premiere, la manche a coute un essai, et la bonne qui suit ne
se credite plus - **c'est exactement ce que la decision de `nadia-prof` demande**. La propriete que
`ADR-0019` protege - *aucune ecriture n'en ecrase une autre* - reste vraie ; c'est le **temoin** que
ce test avait choisi qui cesse d'en etre un.

**Le temoin de remplacement existe et survit au remede** : sous verrou, `score.tentatives()` vaut
exactement **1** apres les deux appuis simultanes, quel que soit l'ordre ; sans verrou, la derniere
ecriture ecrase l'autre et il peut valoir **0**. C'est une assertion, pas une reecriture de test.

### 2.4 `ESSAIS_AVANT_REVELATION` et `ADR-0063` - **aucun des deux ne bouge**, verifie

- **`ESSAIS_AVANT_REVELATION = 3`** dans les trois sessions, inchange. La famille A ne touche pas
  l'allocation d'essais : c'est **la difference meme entre A et B**, et c'est le motif numero 1 du
  refus de B par `nadia-prof`.
- **`ADR-0063` n'est ni amende, ni contourne, ni rendu inutile.** *Methode : chargement des 68
  fichiers de `contenu/`, comptage de `1 + len(distracteurs)` par item enumere, arbre `48635ba0`.*
  **1 245 items enumeres : 114 a deux propositions, 1 098 a trois, 33 a quatre.** Le cliquet
  `DETTE_AU_2026_08_27` vaut `{cm1-conjugaison-present: 25, cm1-accords-groupe-nominal: 8}`, soit
  **33**, et la mesure rend **exactement 25 et 8 sur ces deux paquets** : **le plafond vaut la dette,
  au chiffre pres, et chacun de ses deux noms designe encore un paquet reel.** Ces chiffres sont
  identiques a ceux de ma confirmation de s33 sur `b45e5927`, ce qui est attendu : aucun fichier de
  contenu n'a bouge entre les deux tetes.
- **Ce qui change, en revanche, c'est un argument que j'avais ecrit.** Mon avis de s33 disait
  qu'*"augmenter le nombre de propositions n'est pas un remede disponible"* parce qu'`ADR-0063`
  l'interdit. C'etait vrai, et **sous la famille A cet argument cesse d'etre necessaire** : le remede
  ne depend plus du nombre de propositions. `ADR-0063` reste ce qu'il est, pour son propre motif.

### 2.5 Une correction a l'enonce de regle que j'avais ecrit a s33, et elle va contre moi

Mon avis de s33, section 4.5, ecrivait la regle qui doit entrer avec le remede :

> *"une strategie aveugle [...] n'obtient pas de reussite comptee, donc pas de score parfait sur une
> partie complete."*

**La premiere moitie est fausse sous la famille A, et je la retire.** Une strategie qui presse les
propositions dans l'ordre affiche tombe sur la bonne du **premier** appui environ une fois sur trois ;
cette manche-la est comptee, et elle doit l'etre - c'est un enfant qui a repondu juste du premier
coup, et la decision de `nadia-prof` ne lui retire rien. **J'avais mesure le remede sur l'etat de
depart et ecrit son enonce sur cet etat-la** : c'est la faute que `RES-2` interdit a `nadia-prof`, et
je viens de la commettre sur ma propre regle.

**L'enonce juste, et il est plus simple :**

> **Sur toute mecanique du domaine, une manche resolue apres au moins un essai infructueux n'est pas
> comptee comme une reussite.**
>
> **Population, derivee** : les trois sessions du paquetage `domain/mechanics`, sur le patron de
> `ToutesLesMecaniquesSontTesteesTest` - une mecanique neuve y entre le jour ou elle existe.
> **Deterministe, aucun tirage, aucune graine.** **Etat attendu : rouge sur les trois aujourd'hui,
> verte avec le remede.**
>
> **Ce qu'elle ne tient pas** : `SCO-3` est ecrite au niveau du catalogue - *le score final ne doit
> pas etre parfait*. Sous le remede, un score parfait redevient possible pour une strategie aveugle
> **chanceuse** (les dix premiers appuis justes) ; c'est une propriete de la graine, pas du produit,
> et une regle qui l'epinglerait serait un faux rouge en attente. **Je n'encode pas `SCO-3` au
> catalogue** ; la regle ci-dessus la rend vraie en esperance et je le dis plutot que de laisser
> croire l'inverse.

### 2.6 Le chiffrage, pour le planning

| Poste | Cout |
|---|---|
| `BanditManchotSession.resoudre`, `DesignationSession.resoudre` | 1 expression chacune |
| `SaisieSession.resoudre` | 1 expression, **ecrite differemment** (`tentatives.isEmpty()`) |
| `DeuxCoupsSimultanesTest` | 1 assertion remplacee, plus la Javadoc qui dit pourquoi le temoin change |
| La regle de la section 2.5, avec sa mise a l'epreuve | 1 classe de test neuve, ~60 lignes |
| `SCO-4` a `SCO-7`, les quatre non-regressions que mon avis de s33 a differees a ce commit | ~40 lignes |
| L'`ADR` qui porte la decision et cite la phrase de `nadia-prof` | 1 |
| **Contenu, catalogue, schema, vues** | **0** |

> **Une demi-journee de developpement, un `ADR`, un seul test existant a reecrire.** Le remede est
> petit ; ce qui a coute neuf sprints n'etait pas sa taille, c'etait la phrase qui manquait.

**Ce que je ne fais pas ici** : je n'ecris pas l'`ADR`. Il sort **avec** le remede, dans le meme
commit que sa regle, comme mon avis de s33 l'annonce - une regle qui epingle aujourd'hui le
comportement que le remede doit changer ne garde rien. **Et le texte de l'ecran de fin de partie
n'est pas de moi** : `nadia-prof` s'en declare la seule garde, et son refus numero 6 le borne
d'avance.

---

## 3. Mes trois dettes de s33

### 3.1 Les cinq motifs d'exemption a comptes perimes - **regle ecrite, cinq motifs corriges, meme diff**

**C'est la dette que j'avais datee a s33 avec son enonce ecrit d'avance. Elle est payee.**

**Fichier** :
`adapters/adapter-content/src/test/java/com/recreo/adapter/content/LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest.java`

**La clause, cinquieme de ce garde-fou** :

> Tout groupe de chiffres suivi du mot `items` ou du mot `propositions`, dans un motif d'exemption de
> `PAQUETS_HORS_DU_DOMAINE_NUMERIQUE`, vaut le total mesure sur le paquet servi que l'entree designe.

**Le comptage, avec sa methode dans le meme paragraphe.** *Extraction des 53 entrees de la table
depuis la source, puis comptage direct sur le paquet servi correspondant - nombre d'items, et
`1 + len(distracteurs)` somme sur les items qui portent des propositions. Arbre `48635ba0`.*
**53 motifs, 131 chiffres ecrits, 122 exacts, 9 faux repartis sur 5 entrees.**

**La preuve de la morsure, sur le materiau reel et non sur une fixture de ma main.** La regle a ete
lancee sur les motifs **tels que `48635ba0` les porte**, remis en place depuis `HEAD` :

```
Expecting empty but was: [
  "paquet cp-qlm-etats-eau : le motif d'exemption ecrit "8 items", le paquet servi en porte 14. ...",
  "paquet cp-qlm-corps-sens : ... "8 items", ... 14. ...",
  "paquet cp-qlm-corps-sens : ... "24 propositions", ... 42. ...",   (deux fois)
  "paquet cm1-anglais : ... "20 items", ... 25. ...",
  "paquet cm1-anglais : ... "60 propositions", ... 75. ...",         (deux fois)
  "paquet cp-qlm-vivant-non-vivant : ... "8 items", ... 14. ...",
  "paquet cm1-synonymes : ... "60 propositions", ... 48. ..."]
```

**Neuf defauts, sur les cinq entrees annoncees, aux chiffres annonces.** Apres les cinq corrections,
la meme regle est verte, et le reacteur complet aussi (section 5).

**Les corrections, avec la mesure de chacune** (comptage direct sur le paquet servi, 2026-09-10) :

| Entree | Ce que le motif affirmait | Ce que le paquet porte |
|---|---|---|
| `cp-qlm-corps-sens` | 8 items, 24 propositions | **14 items, 42 propositions**, tous a trois propositions |
| `cp-qlm-etats-eau` | 8 items, **tous** a un seul distracteur | **14 items, 30 propositions** : 12 a deux propositions, **2 a trois** |
| `cp-qlm-vivant-non-vivant` | 8 items, **tous** a un seul distracteur | **14 items, 31 propositions** : 11 a deux propositions, **3 a trois** |
| `cm1-anglais` | 20 items, 60 propositions | **25 items, 75 propositions** |
| `cm1-synonymes` | une phrase citant 60 propositions, qu'une phrase suivante corrigeait deja | **16 items, 48 propositions** ; le 60 est retire |

**Deux mises a l'epreuve, dans les deux sens.** `unCompteFauxDansUnMotifFaitEchouerLeGardeFou` mute
le motif d'une entree reelle en `999 items, 999 propositions` et exige **deux** messages nommant le
paquet et chacun des deux chiffres ; `unMotifDontLesComptesSontExactsPasseLeGardeFou` verifie le sens
inverse sur un paquet reel, sans quoi une clause qui refuserait **tout** motif chiffre laisserait la
meme trace rouge.

**Ce que la clause ne tient pas, ecrit dans sa Javadoc plutot que laisse decouvrir** :

- **un compte ecrit en toutes lettres lui echappe** (*"cinq items"*, `cp-qlm-cycle-de-vie`) : c'est un
  plancher sur la forme chiffree, pas une garantie sur la prose entiere ;
- **elle ne juge pas la forme qu'un motif decrit.** Deux des cinq entrees etaient fausses **aussi**
  sur la forme - elles affirmaient que *tous* leurs items portaient un seul distracteur, faux depuis
  s28 sur deux et trois items. **Cette moitie-la se corrige a la main et rien ne la garde** ;
- **consequence assumee sur la redaction** : un motif dit des **totaux**. Un compte partiel ecrit en
  chiffres serait rouge alors qu'il serait juste. *Mesure sur les 53 motifs du jour : aucun n'en
  ecrit un, et le seul qui nomme un sous-ensemble l'ecrit en toutes lettres.*

**Un refactoring minimal l'accompagne, et je le nomme** : `itemsTires` bornait deja sa liste aux items
qui portent des propositions, ce qui rendait un paquet de saisie vide. La clause compte les **items**
d'un paquet, y compris ceux d'un paquet de saisie ; la boucle de tirage est donc extraite en
`tousLesItemsTires`, et `itemsTires` l'appelle. **Aucun comportement existant ne change.**

**Aucun `ADR` neuf.** Cette clause applique `ADR-0051` (une exemption declare ce qu'elle exempte) et
`ADR-0013` (un garde-fou se prouve mordant) a la prose d'une table existante. Aucune decision
d'architecture n'est prise ici, et le compte de regles `ArchUnit` du depot est inchange - ce
garde-fou balaie du YAML servi, ArchUnit lit du bytecode.

### 3.2 Le litteral mort de `PaquetCm1HistoireGeographieTest` - **corrige, et la mort est prouvee**

Route par le `relecteur-pedagogique` (s33, relecture, section 2). `fer1SurHgCm112LaPratiqueSeCultiver\
EstDesormaisServie` acceptait trois motifs, dont `"évènement culturel"` a l'accent grave ; la
correction orthographique du meme sprint a fait disparaitre cette graphie du contenu servi. Le test
restait vert par un autre motif, **donc rien ne le disait**.

**Corrige** : `"évènement culturel"` devient `"événement culturel"`. **Et la correction est prouvee
dans les deux sens plutot qu'affirmee** - deux mesures, dans le worktree detache, avec le predicat
reduit a ce seul motif :

| Etat du predicat | Verdict |
|---|---|
| `enonce.contains("événement culturel")` seul | **vert** - le motif corrige correspond a un enonce servi |
| `enonce.contains("évènement culturel")` seul | **rouge**, `FER-1 : au moins un item sert la pratique "se cultiver"` |

Le second rouge est la mesure qui manquait : **la branche etait bien morte**, et elle se lisait comme
si elle gardait encore.

### 3.3 Le defaut structurel du registre d'identifiants - **je ne prends pas de decision d'architecture, et voici pourquoi**

**Le fait, tel que la retro de s33 l'a chiffre** : trois instances ecrivant `REGISTRE-identifiants.md`
en parallele sur le checkout partage ont fait rougir
`ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest` (24 puis 26 identifiants non alloues, a dix
minutes d'intervalle), et **deux agents ont du batir un worktree isole ou jetable pour prouver que le
rouge n'etait pas le leur**. C'est la troisieme occurrence. **J'en ai bati un troisieme aujourd'hui,
pour ce document meme.**

**Le defaut, ecrit precisement, parce que sa formulation decide du remede.** Ce garde-fou balaie
`Files.walk` depuis la racine, c'est-a-dire **l'arbre de travail**. Quand plusieurs agents ecrivent
dans le meme checkout, il rend donc un verdict sur un etat **qui ne sera jamais un commit**. Ce n'est
pas un defaut d'`ADR-0085` : la clause 2 est juste, et le geste qu'elle impose - allouer dans le meme
commit que l'ecriture - est exactement ce qui a fait trouver la collision du 2026-09-03.

**Le defaut a deux modes, et le second est le plus grave. Je le nomme parce qu'aucun des trois
constats precedents ne l'a nomme.**

1. **Le faux rouge**, mesure deux fois a s33 : un fichier non commite d'un autre agent ecrit un
   identifiant que le registre n'alloue pas encore. Bruyant, transitoire, il se resorbe a la fusion.
2. **Le faux vert**, qui n'a jamais ete nomme et qui a deja ete paye une fois : l'allocation **non
   commitee** d'un agent fait passer la citation d'un autre. Celui-ci commite une citation dont
   l'allocation n'est pas dans son commit, et la CI rougit **a la fusion, sur son commit a lui**.
   C'est exactement l'incident que l'amendement du 2026-09-07 d'`ADR-0085` decrit et que `sofia-sm` a
   ferme par quatre allocations mecaniques.

**Ce que cela decide, et c'est pourquoi je ne construis rien.**

- **Le remede qui supprime la cause est une regle de poste** : *une instance ecrit dans son propre
  worktree, jamais dans le checkout partage.* Elle supprime les deux modes. **Aucun test ne peut la
  voir** : un fichier ne porte pas le nom de qui l'ecrit. Elle appartient a `squad/README.md` et a
  `sofia-sm`, et je la lui route avec sa phrase (section 4).
- **Le remede qui supprime le cout - empaqueter la relance isolee que trois agents ont maintenant
  batie a la main - ne supprime que le mode 1.** Il ne voit pas le faux vert, qui est le mode
  couteux. Je le chiffre : un script d'`outils/` et son juge pur, sur le patron de
  `verifier-lignee.sh`, **de l'ordre de 200 lignes plus l'`ADR`**. Il achete deux worktrees a la main
  par sprint parallele et **ne garde rien**.
- **Un `ADR` dont la regle executable serait *"ce script existe"* documenterait un outil, pas une
  decision.** C'est exactement le geste que ma charte m'interdit. **Je n'ecris donc pas d'`ADR` ici**,
  et je dis nettement que c'est la troisieme fois que cette dette est nommee sans etre payee - avec,
  cette fois, la raison pour laquelle la payer avec un outil ne la paierait pas.
- **Ce qui protege le depot est intact** : les deux modes sont fermes a la fusion par la CI. Ce qui
  est degrade est l'experience locale, et c'est de la que vient le vrai risque - **un agent qui
  apprend a lire un rouge comme *"ce n'est pas le mien"* finit par le lire ainsi le jour ou il l'est.**

**Et le defaut s'est produit sur moi pendant cette seance, dans ses deux modes a la fois.** La mesure
est en section 5.1 : le meme document rend ce garde-fou **rouge** sur un arbre detache a `48635ba0`
et **vert** sur le checkout partage, parce que trois des identifiants qu'il cite ne sont alloues que
dans le diff non commite de `nadia-prof`. **Quatrieme occurrence, et la premiere ou les deux cotes
sont mesures.** Elle porte une contrainte de sequencement reelle pour `sofia-sm`, ecrite la-bas.

---

## 4. La part opposable de `RES-1` - **non executable en l'etat, et voici exactement ce qui manque**

**La propriete routee** : *dans une fiche de conformite, toute reserve porte un objet distinct de son
remede.* `nadia-prof` la donne comme la seule part mecanique de son dispositif. **Je l'ai verifiee
plutot que de la croire, et ma conclusion est qu'elle ne l'est pas encore - a deux declarations
pres.**

### 4.1 La mesure, sur les 69 fiches

*Methode : balayage des 69 fichiers `squad/conformite/FICHE-*.md` a `48635ba0`. Une reserve y est
reperee par son identifiant de la forme `PREFIXE-Rn`. Deux formes d'ecriture sont comptees
separement : le titre de section qui nomme la reserve, et le bloc de citation qui l'enonce.*

| | Compte |
|---|---|
| Titres de section nommant une reserve, toutes fiches | **131** |
| Blocs de citation ouvrant une reserve (`> **\`ID\` - ...**`) | **8** |
| dont dates du 2026-09-10 ou apres | **6** (`EXC-R7`, `EXC-R8`, `EXC-R9`, `PER-R3`, `REG-R4`, `REG-R5`) |
| dont portant une partie **etiquetee** `Objet` | **0** |
| Blocs de citation sans aucune date | **2** (`RGA-R1`, `RGA-R2`) |

### 4.2 Ce qui manque, nomme exactement

1. **Une reserve n'a pas de bloc.** Un identifiant apparait dans un titre, dans une phrase, dans une
   cellule de tableau, et plusieurs fois par fiche a des statuts differents - *ouverte*, *levee*,
   *tranchee*. **Un automate ne peut pas deriver *"le texte de la reserve X"* d'une fiche**, et un
   perimetre qu'on croit derive et qui ne l'est pas est un perimetre faux (`ADR-0015`).
2. **Aucune partie d'une reserve n'est etiquetee.** Les cinq parties de `RES-1` n'existent sous forme
   de puces etiquetees que dans la section 5 de l'arbitrage prealable de s34, qui **n'est pas une
   fiche** et dont les puces portent un **poste**, pas une reserve. Zero des 8 blocs de fiche en
   porte.
3. **Sans 1 et 2, *"distinct de son remede"* est un jugement sur de la prose**, et aucun automate ne
   le rend. C'est ce que `nadia-prof`, `yanis-qa` et moi avons ecrit chacun de notre cote, et la
   mesure ci-dessus le confirme au lieu de le supposer.

### 4.3 Pourquoi je n'ecris pas la regle aujourd'hui, chiffres a l'appui

J'ai construit la regle que j'allais ecrire, et je l'ai mesuree avant de l'ecrire. **Sa population
serait de 6 blocs, et les 6 seraient dans son exemption.** Une regle dont la totalite de la population
est exemptee ne juge rien : c'est la population vide qu'`ADR-0051` refuse, et **un cliquet dont le
plafond vaut la population entiere n'est pas un cliquet, c'est une liste.** Elle serait decorative au
sens exact du terme - elle n'interdirait rien a personne le jour ou elle entre.

**La seule facon de la rendre mordante aujourd'hui serait de reecrire les six reserves du
2026-09-10 pour leur ajouter un objet.** Leurs objets existent, `nadia-prof` les a ecrits dans son
arbitrage - mais les recopier dans ses fiches serait ecrire du texte pedagogique a sa place. **Je ne
le fais pas.**

### 4.4 Ce qui la rendrait executable - une declaration, et elle coute une ligne par reserve

**La forme est a moi, elle me l'a explicitement laissee.** La voici, aussi petite que je sais la
faire :

> **Une reserve ouverte dans une fiche de conformite est *un* bloc de citation.** Sa premiere ligne
> est `> **\`ID\` - <qualification>, datee du AAAA-MM-JJ, <bloquante|non bloquante>.**` - c'est deja
> ce que `nadia-prof` ecrit sur six blocs sur huit. **Le bloc porte une ligne
> `> **Objet** : ...`.** C'est la ligne qui manque, et c'est la seule chose qui s'ajoute.

**La regle qui suit, prete a etre ecrite** : tout bloc de reserve d'une fiche, date du jour ou apres,
porte une ligne `Objet` dont le texte n'est identique a aucune autre ligne du bloc. Perimetre derive
des fiches, aucun nom de reserve dans la source. **Elle entre le jour ou la premiere reserve est
ecrite dans cette forme** - c'est le meme regime que la regle de la section 2.5, qui entre avec son
remede : une regle entre avec ce qu'elle garde, jamais avant.

**Et je borne d'avance ce qu'elle vaudra**, pour que personne ne la croie plus forte qu'elle : elle
verifie **la presence** d'un objet, jamais sa qualite, et elle ne distingue pas un objet d'une
paraphrase du remede ecrite sous l'etiquette *objet*. `nadia-prof` demande exactement cela - *"il ne
peut verifier que sa presence"* - et **la presence est ce qui manquait dans les quatre cas de s33**.

**Deux trous mesures, dits maintenant** : un bloc **sans date** echappe a la regle (il y en a 2
aujourd'hui, tous deux anterieurs a `RES-1`), et une reserve ecrite en titre de section plutot qu'en
bloc y echappe aussi. **C'est un plancher sur une forme declaree, pas une garantie sur une fiche
entiere.**

---

## 5. La porte technique sur mon propre diff - les quatre jobs, nommes un par un

**Je ne deduis aucun job d'un autre et je ne confonds pas la CI avec `mvn verify`** (`CLAUDE.md`,
amendement du 2026-09-08).

| Job de `.github/workflows/ci.yml` | Ce que j'ai lance | Resultat |
|---|---|---|
| `build` | `bash outils/mvn-jdk21.sh -B clean verify` sur la racine, reacteur complet a six modules, **lance deux fois** : dans un worktree detache sur `48635ba0` ne portant que mes deux fichiers de test, puis sur le checkout partage tel qu'il est, document compris et fichiers des deux autres agents presents | **vert les deux fois.** `BUILD SUCCESS`, six modules `SUCCESS`, 02:37 min puis 05:49 min. **1 446 tests, 0 echec, 0 erreur, 0 ignore, aux deux executions** |
| `chemins` | `.github/scripts/verifier-chemins-test.sh`, puis `verifier-chemins.sh` sur **l'union des fichiers suivis et non suivis**, avec l'exclusion que le job porte lui-meme | **vert.** *"Tous les cas passent : le garde-fou de chemins mord, et sur le bon motif."*, puis *"Aucun chemin de poste de travail ni nom de compte dans les fichiers verifies."* |
| `outils` | la boucle du job sur ses trois motifs (`outils/*-test.sh`, `squad/outils/*/*-test.sh`, `outils/*-test.py`) | **vert. 9 juges purs executes**, aucun en echec, aucun motif vide |
| `prefixes` | `verifier-prefixes-test.sh` et `commit-atteignable-test.sh` | **vert** tous les deux. **Aucun commit de moi a verifier** : je ne commite pas |

**Le compte de tests, avec sa methode dans le meme paragraphe.** **1 446** = somme des totaux que
Maven annonce par module a la fin de chaque module, sur un reacteur complet a six modules lance
depuis la racine : 105 (`recreo-domain`) + 13 (`recreo-application`) + 388 (`recreo-adapter-web`) +
**696** (`recreo-adapter-content`) + 238 (`recreo-bootstrap`, surefire) + 6 (`recreo-bootstrap`,
failsafe). **Le delta avec les 1 443 de la cloture de s33 vaut exactement +3**, et ce sont mes trois
methodes de la section 3.1.

**Pourquoi `build` est lance deux fois, et ce que chaque execution dit.** Le checkout partage porte
les fichiers non commites de `camille-po` et de `nadia-prof` ; un `verify` lance dessus juge un arbre
qui ne sera jamais un commit tel quel (section 3.3). La premiere execution ne porte donc que sur
`48635ba0` plus mes deux fichiers - **le perimetre exact dont je reponds**. La seconde porte sur
l'arbre assemble d'aujourd'hui, document compris ; elle rend le meme chiffre, et c'est elle qui
repond a la question *"et une fois tout mis ensemble ?"*. **Les deux sont vertes, et je donne les
deux plutot que celle qui m'arrange.** Les jobs `chemins`, `outils` et `prefixes` sont lances sur le
checkout partage, fichiers non suivis inclus.

**Aucun identifiant de condition neuf n'est alloue par ce document** : il n'en cree aucun, il cite
des identifiants deja alloues. La ligne d'etat courant de `squad/decisions/REGISTRE-identifiants.md`
n'est pas touchee par moi.

### 5.1 Le defaut de la section 3.3 s'est produit sur moi pendant cette seance, et je le mesure au lieu de le raconter

**Les memes trois fichiers rendent deux verdicts opposes selon l'arbre ou on les juge.** C'est la
quatrieme occurrence, et la premiere ou les deux cotes sont mesures dans la meme heure.

| Arbre juge | Commande | Verdict |
|---|---|---|
| worktree detache sur `48635ba0` + **mes trois fichiers seuls** | `bash outils/mvn-jdk21.sh -B -pl bootstrap -am -Dtest='ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest,ToutesLesReferencesDeDecisionResolventTest,AucuneLigneDeTableauNePortePlusDeCellulesQueSonEnTeteTest,ConcordanceDesTagsDAgentTest' -Dsurefire.failIfNoSpecifiedTests=false test` | **rouge**, 16 tests, 1 echec : *"ce document ecrit `RES-1`, `RES-2`, `COH-1`, que le registre n'alloue pas"* |
| checkout partage, avec les fichiers non commites de `nadia-prof` presents | **la meme commande, au mot pres** | **vert**, 16 tests, 0 echec |

**Ni l'un ni l'autre verdict n'est faux ; ils portent sur deux arbres differents, et un seul des deux
deviendra un commit.** Les trois identifiants sont bien alloues - par `nadia-prof`, dans son diff non
commite, qui alloue les sept identifiants de son arbitrage. Sur son arbre a elle, mon document est
sain ; sur `48635ba0`, il ne l'est pas.

**Ce que cela donne concretement, et c'est le mode 2 de la section 3.3, en direct** : **mon vert du
checkout partage est un vert emprunte.** Si mon document etait commite avant l'arbitrage de
`nadia-prof`, la CI serait rouge **sur mon commit**, pour des identifiants qui ne sont pas de moi.

> **La contrainte de sequencement qui en decoule, et c'est a `sofia-sm`** : ce document ne peut pas
> entrer dans un commit anterieur a celui qui porte l'arbitrage prealable de s34 et les sept
> allocations qui l'accompagnent. **Ce n'est pas une precaution, c'est une mesure.**

*Cette section est la raison pour laquelle je ne me contente pas, en 3.3, de dire que le defaut est
transitoire.*

---

## 6. Ce que je route, et a qui

| Ce que je route | A qui | Ce que je demande |
|---|---|---|
| **La sortie d'`EMC-CP-07`** : (a) est la moins couteuse techniquement, et parmi les trois items candidats **seul l'item 4 se transpose sans fait de droit neuf** | `nadia-prof` | Le choix reste pedagogique et il est a elle. Je donne l'ecart de cout, pas une preference |
| **La reprise des deux tests**, ecrite et verte sur le contenu d'aujourd'hui | `sofia-sm` | Quel commit la porte est a elle. Elle n'a pas a attendre le contenu |
| **`EMC-11` est encodable et rouge aujourd'hui** ; sa regle appartient au commit de contenu | `sofia-sm` | Une ligne dans la story, pas une decision |
| **Le remede de `DEC-0061`** : une demi-journee, un seul test existant a reecrire, l'`ADR` et sa regle dans le meme commit | `sofia-sm` | Une story, quand elle voudra. Je n'en priorise aucune |
| **Le temoin de `DeuxCoupsSimultanesTest` change avec le remede** - `ADR-0019` n'est pas affaibli, son temoin cesse d'en etre un | `yanis-qa`, `sofia-sm` | Qu'on ne lise pas ce rouge comme une regression de concurrence |
| **La regle de poste** : *une instance ecrit dans son propre worktree, jamais dans le checkout partage* - c'est le seul remede qui ferme les **deux** modes du defaut du registre | `sofia-sm` | Un amendement a `squad/README.md`. **Ce n'est pas encodable, et c'est pourquoi je ne l'encode pas** |
| **La forme d'une reserve** : un bloc, une ligne `Objet`. Une ligne par reserve, et la regle suit dans le meme geste | `nadia-prof` | La forme est de moi, l'adoption est d'elle. **Je n'ecris pas la regle avant qu'une reserve la porte** |
| **`COH-1`** - un paquet ne se contredit pas avec lui-meme | reste ouvert | Je ne l'ai pas instruite dans ce document. Elle demanderait qu'un item declare l'objet dont il parle, ce que le schema ne porte pas ; **je le dis comme un non-fait, pas comme un avis** |
| **Les tests de paquet manquants pour l'EPS** (`FER-2`, `FER-3`, `BIN-1`, `FAB-1a`, `FAB-1b`) | reste ouvert, **quatrieme routage** | Non instruit ici faute de place. `nadia-prof` les compte a la main pour la troisieme fois, et elle a raison de le dire |

---

## 7. Ce que ce document ne fait pas

- **Aucun Done prononce**, ni technique ni pedagogique. La porte pedagogique appartient a
  `nadia-prof` seule.
- **Aucun `ADR` neuf.** Deux decisions d'architecture etaient candidates et **je n'en prends aucune**,
  avec le motif ecrit pour chacune : le remede du registre partage (section 3.3), qui serait un outil
  deguise en decision ; la regle de `RES-1` (section 4.3), qui serait decorative le jour ou elle
  entre. Le compte de regles `ArchUnit` du depot est inchange.
- **Aucun fichier de production, de contenu, de catalogue ou de vue n'est modifie.** Deux fichiers de
  test le sont, dans le seul module `recreo-adapter-content`.
- **Les trois mutations de mesure - le paquet d'EMC, les trois sessions, le predicat de
  l'histoire-geographie - ont toutes vecu dans un worktree detache**, supprime, et n'ont jamais
  touche l'arbre partage.
- **Je ne redige aucune story, n'en priorise aucune, n'affecte personne**, et je ne dis rien du
  perimetre de s34 ni de la borne de `nadia-prof`.
- **Je ne dis rien de ce que je n'ai pas mesure** : `COH-1` n'est pas instruite, les tests de paquet
  d'EPS non plus, et le palier 2 n'est pas rouvert.
- **Rien n'est commite, rien n'est pousse.**
