# ADR-0045 - Un critere derive de l'artefact qu'il juge n'en juge que la coherence interne

- **Statut** : Accepte
- **Date** : 2026-08-17
- **Auteur** : theo-archi
- **Sprint** : s14

## Contexte

La section 8.1 de `squad/sprints/s14-points-d-entree-par-niveau/avis-faisabilite.md` prescrivait
d'inverser le sens de derivation d'un garde-fou de catalogue : non plus une methode figee par entree,
mais **une clause unique dont la population vient de `catalogue.yaml`**. Elle concluait *"pas d'ADR :
la doctrine qui la justifie est `ADR-0013` obligation 1, deja acceptee, et je n'ecris pas un `ADR`
pour appliquer un `ADR`"*, et elle nommait d'avance un trou - un paquet hors catalogue, qui doit
rester vert.

`lea-dev` a livre le mecanisme (`ChaqueEntreeDuCatalogueChargeBienSonPaquetTest`, cinq methodes
retirees, morsure prouvee sur le materiau de production) **et a nomme un autre trou, que mon avis
n'avait pas vu** : la clause derive le paquet attendu de `catalogue.yaml`, donc elle ne peut plus
detecter une mauvaise association ecrite dans le catalogue. Les cinq methodes remplacees fixaient le
nom du paquet en constante Java, independante du catalogue ; cette propriete-la, elles la gardaient.

**Ma conclusion "pas d'ADR" etait fausse, et pas parce qu'appliquer `ADR-0013` demanderait un `ADR`.**
Elle etait fausse parce que le mecanisme prescrit **retire une propriete**, et retirer une propriete
est une decision. Un mecanisme qui echange une couverture contre une autre ne s'instruit pas dans une
recommandation de planning ; il se tranche, avec le chiffre des deux couvertures.

### Ce que la derivation echange, mesure plutot qu'estime

**Methode du chiffre, dans le meme paragraphe que lui.** Espace des mutations considerees : une seule
edition dans `adapters/adapter-content/src/main/resources/catalogue.yaml`, le champ `paquet` d'une
entree remplace par le nom d'un autre paquet **existant** de
`adapters/adapter-content/src/main/resources/contenu/`. Etat du 2026-08-17 sur `c1abb47` : **11
entrees, 12 paquets**, donc 11 autres paquets par entree, soit **121 mutations**. Parmi les 11 autres
paquets d'une entree, 10 sont deja servis par une autre entree et 1 ne l'est pas (`cm1-homophones`,
retrait deja acte).

| Forme du garde-fou | Mutations attrapees sur 121 | Ce qui lui echappe |
|---|---|---|
| cinq methodes figees (avant s14) | **55** (5 entrees x 11 cibles) | les 6 entrees qu'aucune methode ne nommait, quelle que soit la cible : **66** |
| clause derivee du catalogue (livree) | **0** | tout : le paquet attendu est celui que le catalogue designe |
| clause derivee + invariants internes (cet `ADR`) | **110** (toute cible deja servie par une autre entree) | les 11 renvois vers le seul paquet hors catalogue |

Les 55 anciennes et les 110 nouvelles se recouvrent sur 50. **Gain net : 60 mutations attrapees qui
ne l'etaient pas** (les 6 entrees jamais nommees, vers une cible servie). **Regression stricte : 5** -
une des cinq entrees nommees renvoyee vers `cm1-homophones`. Et **6 mutations qu'aucune des deux
formes n'attrape** : une entree jamais nommee renvoyee vers `cm1-homophones`.

### Pourquoi aucune reecriture du garde-fou ne restitue la propriete perdue

Deux faits, et ils ferment la question :

- **un critere derive de `catalogue.yaml` ne peut pas juger `catalogue.yaml`.** C'est une propriete de
  la derivation, pas un defaut d'ecriture : ce que le test attend est ce que le fichier dit, donc le
  fichier a toujours raison ;
- **une constante Java par jeu recopie le catalogue dans un second endroit.** C'est la famille de la
  dette 3 de la version precedente, et le couplage exact qui a casse en s13 : `DEC-0031` a retire
  `bandit-manchot-grands-nombres-cm1` du catalogue et la methode de test est restee derriere, corrigee
  a la main dans un commit separe.

Ce que la derivation peut encore juger, en revanche, ce sont les **invariants internes** de
l'artefact : les proprietes que le fichier peut se dire a lui-meme, sans reference a une intention qui
vit ailleurs. C'est cette part-la qui est recoltee ici, et elle attrape **le geste exact** que
`sofia-sm` a nomme - le paquet du perimetre assigne au jeu des mesures - parce qu'un paquet reassigne
apparait deux fois.

## Decision

**Trois clauses.**

1. **Un garde-fou dont le critere derive de l'artefact qu'il juge ne se credite que de la coherence
   interne de cet artefact.** Il ecrit dans sa source la propriete qu'il ne juge pas et l'autorite qui
   la tient. La forme de reference est la Javadoc de `ChaqueEntreeDuCatalogueChargeBienSonPaquetTest`,
   ecrite par `lea-dev` dans le commit qui livre le mecanisme.
2. **Il recolte la coherence interne qui se derive.** Pour le catalogue, deux invariants : **le couple
   mecanique + paquet est unique**, et **l'identifiant de jeu est unique**. Le couple et non le paquet
   seul, parce qu'`ADR-0012` fait de la longueur de partie un attribut de *cette association mecanique
   + paquet* : un meme paquet rejoue par une seconde mecanique est une addition legitime, et un critere
   ecrit sur le paquet seul la refuserait.
3. **Aucune donnee redondante n'est ajoutee a l'artefact pour simuler le reste.** La justesse de
   l'association - que le jeu intitule "Les mesures" serve bien un paquet de mesures - n'est pas
   encodable, et elle n'est pas orpheline : elle appartient a `nadia-prof`, par la fiche de conformite,
   seul document du depot ou un jeu est joue. La perte est nommee dans cet `ADR` et dans la source du
   garde-fou, jamais laissee a une relecture.

## Regle executable

**`adapters/adapter-content/src/test/java/com/recreo/adapter/content/AucuneEntreeDuCatalogueNEnDoubleUneAutreTest.java`**,
ecrite dans le commit qui porte cet `ADR`, avec ses trois catalogues fautifs sous
`adapters/adapter-content/src/test/resources/contreexemple/AucuneEntreeDuCatalogueNEnDoubleUneAutreTest/`.

```java
    assertThat(entrees.stream().map(Entree::association).toList())
            .as("ADR-0045 : deux entrees de %s jouent la meme mecanique sur le meme paquet. [...]",
                    catalogue)
            .doesNotHaveDuplicates();

    assertThat(entrees.stream().map(Entree::id).toList())
            .as("ADR-0045 : deux entrees de %s portent le meme identifiant de jeu. games() en rend"
                    + " deux que rien ne distingue, et la seconde est inatteignable en silence",
                    catalogue)
            .doesNotHaveDuplicates();
```

**Ce n'est pas une regle ArchUnit, et le compte du depot reste a 29 avec sa methode** (section 5 de
`squad/sprints/s14-points-d-entree-par-niveau/confirmation-archunit.md`). Le motif est celui
d'`ADR-0013` et d'`ADR-0039` : ArchUnit lit du bytecode - des types, des membres, des appels - et
l'objet juge ici est un fichier YAML de ressources, qu'aucune `ArchRule` ne voit. C'est un test JUnit,
qui echoue dans `mvn verify` comme n'importe quel autre.

Il lit le catalogue **la ou il vit**, sous `src/main/resources`, et non par le classpath : la propriete
jugee est celle du fichier servi, pas celle du chargeur. Effet de bord voulu, et c'est le moyen le
moins cher de rester visible : le recensement de mise a l'epreuve reconnait un balayage de production
au litteral `src/main/` (non-preuve 5).

**Prerequis de mesure.** Le `java` du `PATH` de ce poste n'est pas un JDK 21 ; toutes les mesures sont
prises sur le **JDK 21.0.10** designe par la toolchain de l'utilisateur, via `bash outils/mvn-jdk21.sh`.
Toute mesure partielle porte **`-am`** : mesure du 2026-08-17,
`-pl adapters/adapter-content test -Dtest=LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest`
**sans** `-am` rend **1 echec sur 15** sur `cp-additions-jusqu-a-10`, et **0 sur 15** avec `-am` sur le
meme arbre : sans `-am`, le module se lie au `recreo-domain` installe dans le depot local, pas a celui
du reacteur. Un faux rouge cette fois-la ; ce serait un faux vert la prochaine.

### Morsure mesuree, sur le catalogue de production mute

Une mutation a la fois, arbre restaure et verifie propre par `git status --porcelain` apres chacune.

| Mutation du catalogue servi | Commande | Mesure |
|---|---|---|
| l'entree des mesures designe `cm1-perimetre` (le geste nomme par `sofia-sm`) | `-B -o -pl adapters/adapter-content -am test` (48 + 11 + 121 tests) | **1 seul echec de tout le lot**, celui de ce garde-fou. `ChaqueEntreeDuCatalogueChargeBienSonPaquetTest` reste vert, le garde-fou frere `ChaqueJeuDuCatalogueOffrePlusDItemsQueDeManchesTest` reste vert, les 19 autres classes de contenu restent vertes |
| l'entree des mesures designe `cm1-homophones`, le seul paquet hors catalogue | `-B -o verify`, suite entiere | **BUILD SUCCESS**, 0 echec. Le jeu intitule "Les mesures" sert des homophones et **rien dans ce depot ne le voit** : c'est la non-preuve 1, mesuree et non deduite |
| trois catalogues fautifs jamais servis : couple double, identifiant double, et le corrige (meme paquet sous deux mecaniques) | `-pl adapters/adapter-content test -Dtest=AucuneEntreeDuCatalogueNEnDoubleUneAutreTest` | **4 tests, 0 echec** : les deux morsures rougissent sur le message attendu, et le corrige passe - un garde-fou ecrit sur le paquet seul l'aurait refuse |

## Ce que ce garde-fou ne prouve pas

Une liste de non-preuves est un engagement a reverifier, et une des huit d'`ADR-0038` s'est revelee
franchement fausse au sprint suivant.

1. **L'unicite n'est pas la justesse.** Un catalogue entierement mal associe mais injectif est vert.
   Les 11 renvois vers `cm1-homophones` en sont la forme atteignable en une edition, et l'un d'eux est
   **mesure** : suite entiere verte. `cm1-homophones` porte 24 items, donc le garde-fou frere
   (*strictement plus d'items que de manches*) ne le rattrape pas non plus.
2. **Une transposition a deux editions reste injective**, donc verte : deux entrees qui echangent
   leurs paquets. Deduit du critere, non mesure - c'est une consequence directe de l'injectivite, pas
   une observation.
3. **Ce garde-fou ne lit aucun paquet.** Il juge le fichier de catalogue seul ; qu'un paquet designe
   existe et se charge est garde ailleurs, par le chargeur reel (`games()` echoue sur un fichier
   introuvable) et par les balayages de `adapter-content`.
4. **Il juge le catalogue servi, pas les catalogues de test.** Un catalogue fautif d'un autre garde-fou
   peut doubler ce qu'il veut ; c'est voulu, ce sont des jeux fautifs.
5. **Le recensement de mise a l'epreuve ne voit pas les balayages qui n'atteignent la production que
   par le classpath.** `LesGardeFousSontMisALEpreuveTest` reconnait un balayage au litteral `src/main/`
   dans sa source. Mesure du 2026-08-17, methode : balayage des `src/test/java` des cinq modules
   declares au pom, **107 sources**, celles qui contiennent `src/main/` d'un cote, celles qui ne
   l'ont pas et qui atteignent la production par `getResourceAsStream` ou
   `new YamlContentPackLoader` de l'autre. **32 sources recensees, 12 invisibles.** Sur ces 12, **2
   sont des balayages** derives du catalogue - `ChaqueEntreeDuCatalogueChargeBienSonPaquetTest`, sans
   jeu fautif, et `ChaqueJeuDuCatalogueOffrePlusDItemsQueDeManchesTest`, qui en porte un de lui-meme -
   et **10 sont des epinglages** d'un paquet nomme, du chargeur ou d'un item reel, qu'`ADR-0013` n'a
   jamais vises. Onze des douze n'ont pas de jeu fautif ; elargir le perimetre a toute lecture de
   classpath donnerait donc **11 rouges, dont 10 faux et un seul juste**.

   **Deux faits de plus, mesures par accident en ecrivant ce lot, et ils empechent de fermer la dette
   en elargissant.** Premier fait : ce perimetre **se desamorce en reecrivant un commentaire**. En
   ajoutant a la Javadoc du garde-fou de catalogue la methode d'un chiffre - qui contenait le chemin
   `src/main/resources/contenu/` - j'ai fait entrer la classe dans la population, et
   `aucunGardeFouDeBalayageNEchappeALaMiseALEpreuve` est passe **rouge sur `clean verify`**
   (*"Expecting empty but was: [\"ChaqueEntreeDuCatalogueChargeBienSonPaquetTest\"]"*), pour un
   changement qui ne touche aucune assertion. Le litteral retire de la prose, la classe est ressortie
   et la suite est verte. C'est le desamorcage a un mot pres qu'`ADR-0038` a deja nomme comme le moyen
   le moins cher. Second fait, et il est plus dur : **ce garde-fou-la ne peut structurellement pas
   avoir de jeu fautif de la forme exigee.** Son critere derive de son materiau, donc aucun catalogue
   fautif ne peut le faire rougir - le seul defaut qu'il attrape vit dans le chargeur, pas dans un
   fichier de ressources. Elargir le perimetre lui demanderait donc l'impossible, et l'exempter par
   `DETTE_DE_BALAYAGE` demanderait de faire remonter un plafond qui *"ne remonte jamais"*. La dette est
   nommee ici avec son declencheur, elle n'est pas fermee par ce lot, et je n'enumere pas une seconde
   route d'acces pour faire semblant.

## Consequences

- **Un doublon volontaire devient un rouge.** Le meme paquet, la meme mecanique, deux longueurs de
  partie - une version courte d'un jeu existant - ferait echouer la clause 2. C'est assume et le
  message du garde-fou le dit en clair : le critere devient alors le triplet avec `nombreDeManches`,
  ou il se retire, et c'est `camille-po` qui tranche, pas moi.
- **La suite passe de 502 a 506 tests** (methode en section 5 du fichier de confirmation du sprint).
  Aucune regle ArchUnit ajoutee, le compte reste **29**.
- **La perte de couverture de `ChaqueEntreeDuCatalogueChargeBienSonPaquetTest` reste entiere sur ce
  qu'aucun invariant interne n'atteint**, et elle est desormais ecrite dans un `ADR` en plus de sa
  Javadoc. Ce qui reste au garde-fou derive est etroit et vrai : il epingle que `games()` resout le
  paquet par le champ `paquet` de l'entree, et non par une convention de nommage ou un cache mal cle.
- **Une dette de perimetre est ouverte sur le recensement de mise a l'epreuve** (non-preuve 5), avec
  son declencheur : un troisieme balayage derive du catalogue, ou tout balayage neuf qui n'atteint la
  production que par le classpath.
- `ADR-0039` recoit une non-preuve de plus le meme jour, la neuvieme, qui renvoie ici : sa clause 1
  autorise *"une propriete calculee de l'objet"* sans dire de quelle source le calcul a le droit de
  tirer.

## Alternatives considerees

- **Restituer la propriete par une constante Java par jeu**, c'est-a-dire revenir a ce que s14 vient
  de supprimer. Ecartee par les deux chiffres : **55 mutations sur 121** contre 110, et une seconde
  copie du catalogue dont le couplage a deja casse en s13 (`DEC-0031`, methode orpheline corrigee a la
  main dans un commit separe).
- **Un champ redondant dans l'entree de catalogue** - le niveau attendu, verifie contre le descripteur
  du paquet charge. Ecartee par mesure : le catalogue porte **6 entrees CP et 5 CM1**, le repertoire
  **6 paquets CP et 6 CM1**, donc un tel controle n'attrape que les mutations qui traversent le niveau,
  **66 sur 121** - et **il est aveugle au cas exact que `sofia-sm` a nomme**, `cm1-perimetre` au jeu des
  mesures, deux entrees CM1. Il couterait onze lignes de donnee dupliquee que chaque paquet devrait
  tenir a jour, c'est-a-dire la famille de la dette 2 de la version precedente, pour une couverture
  inferieure et mal ciblee.
- **Deriver le paquet du nom de l'entree** par convention de nommage. Ecartee par mesure : **2 des 11
  entrees ne la suivent pas** (`bandit-manchot-additions-cp` designe `cp-additions-jusqu-a-10`,
  `bandit-manchot-conversions-unites-cm1` designe `cm1-mesures`). Ce serait deux faux rouges le jour de
  son ecriture, et une regle de nommage la ou `ADR-0006` et `ADR-0012` veulent une donnee.
- **Un quatrieme recensement dans `LesGardeFousSontMisALEpreuveTest`**, sur le modele de l'aveu
  `critere enumere assume` : un garde-fou qui derive son critere de l'artefact juge le declarerait, et
  la liste serait plafonnee. Ecartee, et c'est la reponse a la question de recurrence que `sofia-sm`
  pose. Trois raisons, dans cet ordre : la population mesuree est de **2** et les deux declarent deja
  leur trou en prose ; un aveu ne mord que sur celui qui le porte, ce qui est la non-preuve 1
  d'`ADR-0039`, ecrite par moi ; et `ADR-0039` dit lui-meme qu'un troisieme recensement a aveu ferait
  de la clause 3 *"un refuge et non une dette"*. Un mecanisme de plus sur une population de deux qui se
  declare deja serait decoratif.
- **Ne rien faire et acter la perte seche.** Ecartee : 110 mutations sur 121 pour une classe de test de
  quarante lignes et trois catalogues fautifs, sans une ligne de production touchee et sans un nom de
  paquet ecrit dans un test.

## Ce qui me ferait changer d'avis

- **Si le produit veut deux entrees sur le meme couple mecanique + paquet** (une version courte d'un
  jeu), la clause 2 devient fausse en l'etat : le critere passe au triplet avec `nombreDeManches`, ou
  il se retire. C'est une decision de `camille-po`, et le message du garde-fou la lui renvoie.
- **Si une mauvaise association atteint `nadia-prof`** - une reserve de fiche de conformite qui dit
  qu'un jeu servait le contenu d'un autre - alors le cout du champ redondant se rediscute, et le
  chiffre a battre est **66 sur 121**.
- **Si un troisieme balayage derive du catalogue apparait sans jeu fautif**, la dette de perimetre du
  recensement (non-preuve 5) cesse d'etre supportable : le perimetre s'elargit, au prix des **10 faux
  rouges** mesures, ou il se scinde en deux populations.
- **Si un garde-fou du depot est trouve par quelqu'un d'autre en train de deriver son critere de
  l'artefact qu'il juge sans le declarer**, la clause 1 ne suffit pas et le quatrieme recensement
  cesse d'etre decoratif. La population a battre est **2**, et sa methode est en non-preuve 5.
