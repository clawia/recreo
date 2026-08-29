# ADR-0064 - Un garde-fou borne ses tirages par ce que le paquet declare, et une borne ecrite a la main ne descend jamais sous le catalogue

- **Statut** : Accepte
- **Date** : 2026-08-29
- **Auteur** : theo-archi
- **Sprint** : s22, obstacle 16 (ouvert a la cloture de s21, elargi par l'avis de faisabilite de s22)
- **Regles ecrites dans le meme commit** :
  - `bootstrap/src/test/java/com/recreo/archi/UnGardeFouNeBorneJamaisSesTiragesSousLePlusGrandPaquetTest.java`
    (recensement de sources, 4 tests dont 1 morsure sur depot fictif) ;
  - l'assertion d'exhaustivite dans les cinq garde-fous corriges de `adapters/adapter-content`.

## Contexte

**Le probleme n'est pas qu'un garde-fou se trompe, c'est qu'il se taise.** Le 2026-08-28, en
verifiant la population du cliquet d'`ADR-0063` a la demande du `relecteur-pedagogique`, j'ai
trouve que ce garde-fou bornait ses tirages a **60 items par paquet**, valeur ecrite a la main,
alors que `cm1-tables-multiplication` en declare **99**. Sa clause portait donc sur un echantillon
de 60 pour ce paquet-la, sans que rien ne le dise. Corrige le jour meme, sans `ADR` : c'etait un
garde-fou.

**Ce n'en etait pas un.** L'inventaire de l'ouverture de s22
(`squad/sprints/s22-couverture-maximale-du-catalogue/avis-faisabilite.md`, section 4.1) en a nomme
**cinq autres** portant exactement la meme borne de 60, la meme boucle et le meme patron. Cinq
sites ne font pas un accident, ils font une habitude - et une habitude non ecrite n'est pas une
regle.

**La population reelle, avec sa methode, parce que la mienne etait fausse.** Mon releve de s21
cherchait la chaine `int TIRAGES` dans les sources de test : **9 lignes, dont 5 a 60**. Le
recensement ecrit par cet `ADR` ne cherche pas un nom, il cherche une **forme** - une boucle a
trois membres dont le corps demande l'item suivant et alimente l'ensemble des deja-vus. Il rend, le
**2026-08-29, sur `2968591` augmente des corrections de ce lot, sur les 141 sources de
`src/test/java` des cinq modules** : **13 boucles de tirage sans remise**, dans **deux modules**
(`adapters/adapter-content` et `bootstrap`). Mon grep en manquait quatre, dont **une dans `bootstrap`**
(`PropositionsAccentueesJouablesIT`, borne `BUDGET_DE_TIRAGE = 200`) qu'aucune recherche par le nom
`TIRAGES` ne pouvait trouver. **C'est ma propre lecon de s05 appliquee a mon propre releve** : un
perimetre enumere par les noms echoue par omission, et il faut le deriver de la forme.

**Ce que le silence coutait, chiffre avec sa date et son perimetre.** Comptage des 21 fichiers de
`adapters/adapter-content/src/main/resources/contenu/` le **2026-08-29**, une occurrence de
`- enonce:` par item enumere - le meme comptage des deux cotes de la comparaison, ce qui est la
condition pour qu'elle veuille dire quelque chose : **478 items**, **20 paquets enumeres** et
**1 paquet combinatoire** qui n'enumere rien. **Un seul paquet depasse 60** :
`cm1-tables-multiplication`, **99 items**. Les cinq garde-fous en lisaient **60**, donc **39 items
n'etaient examines par personne, cinq fois**. Ce total de 478 concorde avec celui que `nadia-prof`
a etabli le 2026-08-27 en jouant les 20 jeux enumeres, par une methode entierement differente.
**L'arbre mesure, nomme plutot que sous-entendu** : `round-two` a `2968591` augmente de ce seul lot,
c'est-a-dire la branche `s22-theo-2`, qui n'apporte aucun paquet de contenu.

**Ce chiffre a vieilli avant meme d'etre commite, et c'est le cas d'usage de la decision 3.**
Mesure le 2026-08-29 sur les branches de contenu de ce sprint, non fusionnees, par le meme
comptage : `s22-lea` a `04aa35f` porte **22 paquets et 503 items** (`cm1-multiplications-divisions-10-100`,
25 items) ; `s22-sofia-2` a `8da64f5` porte **22 paquets et 502 items** (`cm1-problemes-durees`,
24 items) ; `s22-marc` a `4dedb00` en porte encore 21 et 478, son paquet n'etant pas commite.
**Sur les trois, le plus grand paquet enumere reste `cm1-tables-multiplication`, 99 items** : aucune
des cinq bornes ecrites a la main n'est approchee, et les bornes derivees suivent les paquets neufs
sans que personne ait a y penser. C'est exactement ce que cette decision achete.

**Rapport a `ADR-0051`, et pourquoi ce cas-ci est le plus dangereux des deux.** `ADR-0051` traite
le garde-fou qui n'examine **aucun** de ses objets et exige qu'il le declare. Celui-ci traite le
garde-fou qui en examine **une partie** sans le dire. Le premier finit par se remarquer - un compte
a zero se voit. Le second ne se remarque jamais.

## Decision

### 1. Un garde-fou qui tire sans remise borne ses tirages par ce que le paquet declare

La borne est **derivee du fichier de paquet** - de quoi l'epuiser, plus un tirage pour constater
l'epuisement - et jamais ecrite dans le garde-fou :

```java
int declares = ChaquePaquetPorteUnIndiceTest.itemsDeclares(racine, paquet);
int tirages = declares > 0 ? declares + 1 : TIRAGES_SUR_UN_PAQUET_COMBINATOIRE;
```

Le paquet combinatoire, qui n'enumere aucun item et ne s'epuise jamais, garde une borne
d'echantillon. Cette borne-la n'est pas la borne de la boucle : elle alimente la valeur derivee, et
elle ne s'applique qu'aux paquets dont le catalogue dit qu'ils n'enumerent rien.

### 2. Un paquet enumere lu en partie est un echec nomme, jamais un silence

Chacun des garde-fous concernes assertionne qu'il a tire **autant d'items que le paquet en
declare** :

```java
if (declares > 0) {
    assertThat(items)
            .as("%s declare %d items sur disque et ce garde-fou n'en a tire que %d : la"
                    + " clause porterait sur un echantillon sans le dire", paquet, declares,
                    items.size())
            .hasSize(declares);
}
```

Cette assertion n'est pas la meme chose que la borne derivee, et les deux sont necessaires : la
borne dit **combien on demande**, l'assertion dit **combien on a obtenu**. Un chargeur qui rendrait
40 items pour un paquet qui en declare 99 passerait la premiere et echouerait sur la seconde.

### 3. Une borne ecrite a la main reste tolerable, et ce n'est pas une tolerance sur parole

Cinq bornes ecrites a la main subsistent apres ce lot - **200, 150, 500, 500, 2000** - et **aucune
n'est partielle** : toutes depassent le plus grand paquet enumere du catalogue (99 au 2026-08-29).
Les toucher aujourd'hui serait un changement sans defaut a corriger.

Ce qui est decide, c'est que leur innocuite **cesse d'etre une lecture d'aujourd'hui pour devenir
une propriete verifiee a chaque build** : le recensement compare chaque borne ecrite a la main au
plus grand paquet enumere du catalogue **reel**, et devient rouge **le jour ou le catalogue la
rattrape** - c'est-a-dire avant qu'elle ait pu lire un paquet en partie. Sur un sprint dont la
consigne est *"couverture maximale du catalogue"*, c'est la clause qui travaille.

### 4. Ce que je n'ecris pas, et pourquoi

**Aucun lecteur de tirage partage entre les garde-fous.** Chaque garde-fou garde sa boucle, sa
borne derivee et son assertion d'exhaustivite. Ce qui est partage est le seul comptage de lignes
(`itemsDeclares`), comme `paquets(Path)` l'etait deja. Le motif n'est pas la prudence : c'est que
**cinq garde-fous qui delegueraient a un moteur commun ne prouveraient plus qu'une chose**, et le
recensement de la decision 3 n'aurait plus qu'une seule boucle a examiner. Une regle qui n'a plus
qu'un objet est decorative, quel que soit son enonce.

## Regle executable

**Ce n'est pas une regle ArchUnit, et le compte de regles ArchUnit du depot est inchange.** ArchUnit
lit du bytecode ; une borne litterale dans une boucle de test n'y est pas une propriete lisible. Le
mecanisme est un **recensement de sources**, sur le precedent de `LesGardeFousSontMisALEpreuveTest`
(`ADR-0013`, `ADR-0039`), execute par `mvn verify` comme n'importe quel autre test.

**Fichier** :
`bootstrap/src/test/java/com/recreo/archi/UnGardeFouNeBorneJamaisSesTiragesSousLePlusGrandPaquetTest.java`

```java
static List<String> bornesSousLePlusGrandPaquet(Path racine) {
    int plusGrand = plusGrandPaquetEnumere(racine);
    List<String> defauts = new ArrayList<>();
    for (BoucleDeTirage boucle : bouclesDeTirage(racine)) {
        int valeur = valeurEcriteALaMain(lire(boucle.fichier()), boucle.borne());
        if (valeur >= 0 && valeur < plusGrand) {
            defauts.add(...);
        }
    }
    return defauts;
}
```

### ADR-0013, les trois obligations

1. **Perimetre derive.** Les modules viennent du pom agregateur, les sources de test du balayage de
   ces modules, les paquets du balayage de leurs ressources. Le recensement reprend la derivation de
   perimetre de `LesGardeFousSontMisALEpreuveTest` plutot que d'en ecrire une seconde, qui aurait
   diverge.
2. **Derivation totale.** Chaque boucle trouvee produit un verdict, et le recensement est **rouge
   s'il ne trouve aucun paquet enumere ou aucune boucle** - un critere numerique calcule sur un
   catalogue vide declarerait toute borne acceptable (`ADR-0051`).
3. **Mise a l'epreuve.** Un depot fictif complet, avec son pom agregateur, qui porte **deux**
   tireurs sur le meme paquet de cinq items : l'un borne a trois a la main, l'autre derive. Seul le
   premier doit etre nomme.

### La population designee par sa forme, jamais par un nom

Une boucle entre au recensement si son en-tete est une boucle a trois membres, si son corps demande
l'item suivant, et si **l'ensemble des deja-vus qu'elle passe au tirage est un identifiant que ce
meme corps alimente**. Cette derniere condition n'est pas une precaution : `PaquetsFrancaisS04Test`
tire vingt fois **avec remise** - un ensemble vide et immuable passe a chaque tour - pour constater
une variete, et sa borne de vingt ne lit aucun paquet en partie. Une premiere ecriture de ce
recensement la nommait ; **une regle qui echoue par inclusion est aussi fausse qu'une regle qui
echoue par omission**, et c'est la lecon des `@ExceptionHandler` d'`ADR-0018` sous une autre forme.

### Morsure prouvee sur materiau reel, pas seulement sur ma fixture

| Mutation | Attendu | Constate |
|---|---|---|
| `ChaquePaquetATrousPorteSonTrouSurChaqueItemTest` : borne derivee remise a `60` (l'etat exact d'avant ce lot) | le recensement nomme le garde-fou et l'ecart | `Tests run: 4, Failures: 1` - *"ChaquePaquetATrousPorteSonTrouSurChaqueItemTest : borne de tirage ecrite en clair : 60, alors que le plus grand paquet enumere du catalogue en declare 99. Ce garde-fou lit 39 items de moins que ce paquet ne contient, et ne le dit pas"* |
| `ChaquePaquetPorteUnIndiceTest` : borne derivee remplacee par la borne d'echantillon | l'assertion d'exhaustivite du garde-fou lui-meme rougit | `Tests run: 4, Failures: 1` - *"cm1-tables-multiplication declare 99 items sur disque et ce garde-fou n'en a tire que 60 : la clause porterait sur un echantillon sans le dire"* |
| Depot fictif : le tireur derive doit rester hors du verdict | seul le tireur fautif est nomme | `containsExactly` sur un seul nom, vert |

Les deux premieres mutations portent sur le **catalogue servi**, pas sur une fixture : le chiffre de
39 est celui du contenu que l'enfant recoit.

### Ce que ce recensement ne voit pas, ecrit ici plutot que laisse a deviner

Il resout une borne **ecrite en clair dans la condition** (`tirage < 60`) ou declaree comme entier
litteral dans le meme fichier (`int NOM = 60;`, champ ou variable locale). Une borne **calculee**
(`declares + 1`, `vises * 20`) est tenue pour derivee, ce qui est l'intention. Lui echappent une
borne importee d'une autre classe (`Autre.TIRAGES`) et une borne assemblee (`30 + 30`) : ce sont des
formes qu'aucun garde-fou du depot n'emploie, et qui demandent d'ecrire autre chose que ce qu'on
copie chez le voisin. Il lit aussi le **premier** terme compare de la condition, celui qui compte
les tirages ; une regle d'arret secondaire (`&& sansNouveaute < 200`) n'est pas jugee.

## Consequences

- **Un garde-fou de contenu neuf ecrit avec `for (int tirage = 0; tirage < 60; ...)` est rouge**,
  nomme, avec l'ecart chiffre - y compris s'il est ecrit dans un module qui n'existe pas encore, le
  perimetre venant du pom.
- **Le catalogue peut grossir sans que personne y pense.** C'est la consequence utile pour s22 :
  les trois paquets neufs de ce sprint ne peuvent pas rendre muet un garde-fou existant en silence.
  Si l'un d'eux depassait 150 items, `ChaqueEntreeDuCatalogueChargeBienSonPaquetTest` rougirait
  avant de lire quoi que ce soit en partie.
- **Cout paye immediatement** : cinq garde-fous lisent desormais 99 items au lieu de 60 sur un
  paquet, et le module `adapter-content` execute ses 224 tests en un temps inchange a la seconde
  pres. Ce n'est pas gratuit sur un catalogue dix fois plus gros ; le jour ou ce cout se voit, la
  reponse sera de tirer moins souvent, pas de lire moins d'items.
- **Une mauvaise consequence, nommee.** Le recensement lit du **texte de source**, pas du bytecode :
  il est sensible a la mise en forme du code d'une facon dont une regle ArchUnit ne l'est pas. Une
  boucle ecrite sur plus de 600 caracteres avant son appel de tirage sortirait du perimetre sans que
  rien ne le dise. J'accepte cette limite parce que la propriete gardee n'est pas exprimable
  autrement, et je l'ecris ici plutot que de la laisser decouvrir.
- **Deux copies du comptage des items declares** existent desormais - dans
  `ChaquePaquetPorteUnIndiceTest` (partagee par les cinq garde-fous) et dans
  `AuPlusTroisPropositionsParItemTest` (la sienne, ecrite a la cloture de s21). Je ne fusionne pas :
  toucher le garde-fou d'`ADR-0063`, valide la veille, pour un dedoublonnage cosmetique serait du
  bruit dans un diff. Si une troisieme copie apparait, l'arbitrage change.

## Ce qui me ferait changer d'avis

- **Sur la decision 3** : si une borne ecrite a la main devait un jour etre volontairement
  inferieure au catalogue - un garde-fou trop couteux pour lire tout le catalogue a chaque build -
  la reponse ne serait pas d'affaiblir ce recensement mais d'exiger que ce garde-fou **declare** sa
  lecture partielle, sur le patron d'`ADR-0051`, et de l'ajouter a un cliquet dont le plafond vaut
  exactement la taille de la dette. Rien de tel n'existe aujourd'hui, et je n'ecris pas le cliquet
  d'une dette vide.
- **Sur la decision 4** : trois copies du comptage des items declares, ou un troisieme module qui se
  met a tirer des items. Le lecteur partage deviendrait alors justifie par un usage constate, et le
  recensement continuerait de voir les boucles la ou elles sont.
- **Sur le mecanisme entier** : le jour ou un garde-fou de contenu s'ecrirait autrement qu'avec une
  boucle - un `Stream` borne, une methode de bibliotheque - la forme cherchee ici cesserait de
  designer la population, et le recensement se tairait. C'est le declencheur qui obligerait a le
  reecrire, et il se constate en lisant un diff.

## Alternatives considerees

1. **Ne rien ecrire et corriger les cinq garde-fous.** Ecartee : c'est exactement ce qui a ete fait
   le 2026-08-28 pour le sixieme, et cinq freres du meme defaut sont restes ouverts pendant tout un
   sprint. Une correction sans regle ne protege que le fichier qu'elle touche.
2. **Interdire toute borne ecrite a la main, sans exception.** Ecartee, apres l'avoir prefere.
   Elle obligeait a reecrire quatre garde-fous qui ne lisent rien en partie aujourd'hui, dont un
   fichier de plus de mille lignes, pour un defaut qui n'existe pas - et un lot qui touche quatre
   garde-fous sans defaut a corriger est un lot dont personne ne peut relire le motif. La decision 3
   obtient la meme garantie en ne changeant rien tant qu'il n'y a rien a changer.
3. **Un cliquet nommant les bornes ecrites a la main, plafonne a leur nombre.** Ecartee : le plafond
   aurait valu cinq exemptions le premier jour, et une exemption qui nomme une borne innocente
   apprend a en ajouter une sixieme. Le critere numerique n'a besoin d'aucune exemption.
4. **Un recensement qui cherche la chaine `TIRAGES`.** Ecartee, et c'est celle que j'avais employee
   pour mesurer l'obstacle : elle manque quatre boucles sur treize, dont une dans un autre module.
   Un perimetre enumere par les noms echoue par omission (`ADR-0039`).
5. **Prouver la morsure par un paquet fautif dont l'item defectueux siege au-dela de la borne.**
   Ecartee : les tirages sont sans remise et pseudo-aleatoires, si bien que le rang de l'item non
   lu depend de la graine. La preuve aurait tenu a une fixture accordee a un generateur - la faute
   de s05, dans sa forme la plus difficile a voir. La mutation sur le catalogue reel ne depend
   d'aucune graine : 99 declares, 60 tires, rouge.
