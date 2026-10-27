# s57 - palier 5 : avis de theo-archi sur les gardes de `-06`, `-10`, `-12` apres la lecture des premiers jets par nadia-prof

- **Auteur** : `theo-archi`. Date : 2026-10-27 ; debut non lu par `date`, premiere lecture 13:47:39 (UTC+01:00), fin 14:05:26 (section 8).
- **Arbre** : worktree `s57-p5-theo-apres-lecture`, base `round-two` a `a516d40b` (`git rev-parse`). Les trois paquets y sont dans leur premier jet (dernier commit qui les touche : `bdfed668`).
- **Repond a** : les sections *"Ce qui revient a theo-archi"* de `lecture-nadia-premier-jet-ce1-qlm-aimant.md` (section 5, `T1` a `T4`), `lecture-nadia-premier-jet-ce1-qlm-paysages.md` (section 6, `T1` a `T6`) et `lecture-nadia-premier-jet-ce1-qlm-generations.md` (section 6, `X1`), et verifie contre les gardes les corrections de contenu qu'elle prescrit a `marc-dev` (`-06` points 1 a 5 ; `-10` `L1` a `L5` ; `-12` `K1` a `K6`).
- **Lu** : ces trois lectures en entier ; mes avis `avis-theo-palier-5-gardes-ce1-qlm-06-08.md`, `avis-theo-palier-5-ajustements-gardes-ce1-qlm-06-08.md`, `avis-theo-palier-5-gardes-ce1-qlm-10-12.md`, `avis-theo-palier-5-ajustements-gardes-ce1-qlm-10-12.md`, `avis-theo-palier-5-dernier-ajustement-gardes-ce1-qlm-05-a-12.md` en entier ; `PaquetCe1QlmAimantTest.java`, `PaquetCe1QlmPaysagesTest.java`, `PaquetCe1QlmGenerationsTest.java` en entier ; les trois paquets ; les sondes 12 a 19 de `aimant-fautif.yaml`, 12 de `paysages-fautifs.yaml`, 22 a 24 de `generations-fautives.yaml`.
- **Ce que ce fichier ne fait pas** : il ne reecrit aucun de mes avis (les passages abandonnes sont en section 6, avec leur marqueur) ; il n'ecrit ni test, ni jeu fautif, ni contenu ; il ne cree aucun identifiant de condition (`T1`..., `L1`..., `K1`..., `X1` sont les reperes de `nadia-prof`, propres a chacune de ses lectures ; `g12` prolonge la numerotation locale `g1` a `g11` de `-06`) ; il ne tranche rien de pedagogique et ne prononce aucun Done ; il ne touche ni les stories ni les fichiers de `camille-po`.
- **Aucune regle ArchUnit, aucun `ADR`** : ce sont des gardes de contenu sur du YAML, lues par le test de chaque paquet, dans la forme d'`ADR-0013` (jeu fautif commite), comme mes sept avis precedents. Aucune ne porte sur une dependance entre classes.

## 0. Verdict, une ligne par ajustement

| Lot | Repere | Forme retenue | Sonde refusee | Doit passer |
|---|---|---|---|---|
| `-06` | `T1` | aucun code ; la colonne *Doit passer* de `g3` dans mon avis du 2026-10-26 (2.1) porte un item refuse, corrigee ici (section 6, `M1`) | *n'attire pas*, *la gomme* ; *le clou*, *la bille en verre* | *n'attire pas*, *la gomme* ; *le clou*, *l'agrafe* (sonde 19 du jeu fautif, deja dans le test) |
| `-06` | `T2` | **garde neuve `g12`**, par egalite sur la liste fermee : un item de A *n'attire pas* dont la bonne reponse est *papier d'aluminium* ; un item de B dont les deux groupes distracteurs portent *papier d'aluminium* | items 6, 5 et 12 du premier jet, tels quels | items 1 et 2 du premier jet ; items 5, 6, 12 corriges |
| `-06` | `T3` | `G10` inchangee, en mots | - | - |
| `-06` | `T4` | `g8` et la source de `g1` inchangees | - | - |
| `-10` | `T1` | `G10-2` : pour chaque element partage nomme, le paysage de la bonne reponse est l'un de ses paysages | *"On voit un sommet et un port."*, *la montagne* ; *la ville*, *la campagne* | l'item 1 |
| `-10` | `T2` | `G10-2` : refus d'un item A dont les propositions contiennent *le littoral* et *la ville*, **clause placee en dernier** dans la boucle de `G10-2` | items 3 et 6 du premier jet | `L1`, `L2` |
| `-10` | `T3` | `G10-4` B : aucune forme de `ELEMENT_DANS_UN_INDICE` dans l'indice ; sur le littoral, recherche faite sur l'indice prive de la zone de l'exemple | B montagne, indice *"Pense à la neige."* | items 2 et 5 |
| `-10` | `T4` | `FORME_PAYSAGE` : *campagnes*, *littoraux* ajoutes | indice de A *"Compare les campagnes."* | - |
| `-10` | `T5` | *piste*, *pistes*, *ski* ajoutes aux formes de `ELEMENT_DANS_UN_INDICE` seulement, pas a `FORME_ELEMENT` | indice de A *"Regarde s'il y a des pistes."* | - |
| `-10` | `T6` | rien : *seul* n'entre dans aucune liste | - | - |
| `-12` | `X1` | `G12-3`, suite `evt` : refus d'un distracteur de permutation `(1,2,0)` | *"entrée à l'école, naissance, premier emploi"* ; *"entrée à l'école, premier emploi, naissance"* | l'item 12, dans son premier jet comme sous `K3` et `K4` |
| `-12` | `G12-6` | **rien ne change** : `K1`, `K2`, `K4` et `K3` passent toutes les gardes | - | - |

**Corrections de `nadia-prof` qui ne passent pas une garde : aucune.** Ses textes exacts, appliques a une copie du depot, passent toutes les gardes en place et toutes les gardes ajustees (section 5). Deux points d'ecriture pour `marc-dev`, qui ne sont pas des refus de garde : 4.6.

## 1. `-06` (`ce1-qlm-aimant`)

### 1.1 `T1` : le tableau de `g3`

`nadia-prof` a raison. Mon tableau de 2.1 (avis du 2026-10-26) range dans la colonne *Doit passer* l'item *n'attire pas*, *la gomme* ; *le clou*, *la bille en verre*, que la colonne *Doit refuser* de la meme ligne porte aussi. Il a deux propositions non attirees (*la gomme* et *la bille en verre* sont `SANS_METAL`) : `g3` le refuse, et c'est sa regle 2 de A. **Le test est juste** : sa sonde 19 de passage est *"Lequel de ces objets l'aimant n'attire-t-il pas ?"*, *la gomme* ; *le clou*, *l'agrafe*, que j'ecris a la place dans la correction (section 6, `M1`). Aucun changement de code.

### 1.2 `T2` : `g12`, le papier d'aluminium lu comme du papier

**Je l'encode.** Motif : la propriete est lisible par egalite sur la liste fermee, que le test tient deja ; et elle refuse trois items **reels**, ecrits par un developpeur qui tenait la lettre de toutes les gardes, et non une fixture de ma main. Ce n'est pas une regle decorative : sans elle, le premier jet, items 5, 6 et 12 compris, passe les 48 tests du paquet (mesure de `nadia-prof`, section *Mesures lancees* de sa lecture) ; avec elle, ces trois items sont refuses et eux seuls (section 5).

**Forme**, methode `verifierQueLePapierDAluminiumNeDonnePasLaReponse`, appelee par un test sur le paquet :

```java
if (estA(item) && !demandeLAttire(item)) {
    assertThat(nomsDe(item, item.bonneReponse()))
            .as("AIM-CE1-2 (g12) : '%s' demande ce que l'aimant n'attire pas et sa bonne réponse est le "
                    + "papier d'aluminium", item.enonce())
            .doesNotContain("papier d'aluminium");
}
if (estB(item)) {
    assertThat(item.distracteurs().stream()
            .allMatch(distracteur -> nomsDe(item, distracteur).contains("papier d'aluminium")))
            .as("AIM-CE1-2 (g12) : les deux groupes distracteurs de '%s' portent le papier d'aluminium",
                    item.enonce())
            .isFalse();
}
```

`nomsDe` est celui du test : nom sans article en A, trois noms coupes sur la virgule en B, apres `plat` (l'apostrophe typographique est donc lue comme la droite).

| Sonde | Attendu | Obtenu |
|---|---|---|
| item 6 du premier jet (*le papier d'aluminium* ; *le clou*, *le trombone*) | refus | refus, message A |
| items 5 et 12 du premier jet | refus | refus, message B, chacun |
| items 1 a 4, 7 a 11 du premier jet | passe | passe, chacun |
| les 12 items corriges (points 1 a 5 de sa section 4) | passe | passe |

Le jeu fautif n'a pas a recopier ces items : la sonde lit le premier jet tel qu'il est, et `marc-dev` le reecrit dans le jeu fautif sous trois marqueurs `sonde NN`, textes identiques.

**Mutants.** *"Un seul groupe distracteur suffit"* en B : tue par l'item 2 du premier jet, qu'elle admet et dont un seul groupe porte *papier d'aluminium* (verifie par execution). En A, *"toute polarite"* et *"toute proposition"* sont **equivalents** a la forme retenue sous `g3` : en *attire*, la bonne reponse est `FER` ; en *n'attire pas*, les deux distracteurs le sont. Aucune sonde ne peut les tuer, et je n'en ecris pas.

**Ce que `g12` ne garde pas** : un autre nom qui donnerait la reponse par une lecture fausse (*la feuille de papier* est `SANS_METAL`, sans premisse elle ne compte pas, et `g11` la refuse sous la premisse) ; *papier d'aluminium* dans un seul groupe distracteur, qu'elle admet (items 1 et 2). Lu par elle.

### 1.3 `T3`, `T4`

`G10` inchangee, en mots ; aucune garde en caracteres (sa reserve de jeu, section 1 de sa lecture). `g8` (matiere de la bonne reponse dans l'indice) et la source de `g1` (refus d'une source qui nomme un test) inchangees.

### 1.4 Ses corrections passent les gardes en place, et `G9` compte toujours un item de A

Textes exacts de sa section 4, points 1 a 5, appliques a une copie (section 5) : **les 48 tests du paquet, le test transverse et le test de rang passent.** Calcule par le test sur la copie corrigee :

- **quart** : 8 items comptent sur 12, **dont 4 de A** (items 1, 3, 6, 7) : `G9` (au moins un) tient avec trois items de reserve ; l'item 6 corrige compte (premisse, *la barquette en aluminium* `METAL`, *le fil de fer* et *l'épingle* `FER`) ;
- `g3`, `g9`, `g11` sur l'item 6 corrige : une seule non attiree, aucun mot commun entre *fil de fer* et *épingle*, aucun objet `SANS_METAL` ;
- `g7` sur les items 5 et 12 corriges : deux `FER` et un `METAL` dans chaque groupe distracteur ;
- `G10` : le bon groupe est le seul plus court dans 2 items de B sur 4 (9 et 12), au plafond, comme son recompte le dit ;
- `g8`, `g2`, `g10` (`AIM-CE1-2`) et le transverse sur l'indice neuf de l'item 9 : passe.

## 2. `-10` (`ce1-qlm-paysages`)

### 2.1 `T1` : le paysage de la bonne reponse porte chaque partage nomme

**Forme**, dans la boucle des elements de `G10-2`, avant la clause du distracteur :

```java
assertThat(PARTAGE.get(element))
        .as("G10-2 : '%s' nomme l'élément partagé %s, que le paysage de la bonne réponse (%s) n'a pas",
                item.enonce(), element, bonne)
        .contains(bonne);
```

Sa sonde (*"On voit un sommet et un port. Quel est ce paysage ?"*, *la montagne* ; *la ville*, *la campagne*) est refusee par cette clause seule ; la forme actuelle la laisse passer, *la ville* etant un paysage de *port* (lecture du code, `:397-403`, le trou `P2` que `marc-dev` a signale). L'item 1 passe.

### 2.2 `T2` : le littoral et la ville jamais proposes ensemble

**Forme**, a la fin de la boucle de `G10-2`, apres les clauses des elements :

```java
Set<String> proposes = new TreeSet<>();
item.propositions().forEach(proposition -> paysage(proposition).ifPresent(proposes::add));
assertThat(proposes.containsAll(List.of(LITTORAL, VILLE)))
        .as("G10-2 (R10-2) : '%s' propose le littoral et la ville ensemble", item.enonce())
        .isFalse();
```

**La place est mesuree, pas choisie.** Ecrite en tete de la boucle, la clause fait rougir un test existant : la sonde 12 de `paysages-fautifs.yaml` (*"On voit une plage et des immeubles."*, *le littoral* ; *la ville*, *la campagne*) est attendue avec le message *"élément propre d'un autre paysage"* et recevait celui de `T2`. En dernier, chaque sonde existante garde son message.

| Sonde | Attendu | Obtenu |
|---|---|---|
| item 3 du premier jet (*"On voit une plage et un port."*, *le littoral* ; *la ville*, *la montagne*) | refus | `T2` seule |
| item 6 du premier jet (*"On voit des immeubles et un port."*, *la ville* ; *le littoral*, *la campagne*) | refus | `T2` seule |
| `L1` et `L2` (items 3 et 6 corriges) | passe | passe |
| sonde 12 existante | refus, son message | refus, son message |

**Consequence que je dis, sans garde neuve.** `T1` et `T2` ensemble ferment *le port* en A, ce que sa phrase de `R10-2` ecrit : *port* n'a que le littoral et la ville ; `T1` met l'un en bonne reponse, la clause du distracteur de `G10-2` met l'autre en distracteur, et `T2` refuse la paire. Aucune clause propre a *port* : elle ne pourrait etre la seule a mordre sur aucune sonde.

### 2.3 `T3` : un indice de B ne nomme aucun element hors de l'exemple

**Forme**, dans `verifierLesIndices`, tache element, apres la verification du littoral (`G10-4`, `G13`) ou de `G12` :

```java
if (lieu.isPresent()) {
    String horsExemple = lieu.get().equals(LITTORAL) ? sansLExemple(indice) : indice;
    Matcher autre = ELEMENT_DANS_UN_INDICE.matcher(horsExemple);
    boolean nommeAutre = autre.find();
    assertThat(nommeAutre)
            .as("G10-4 (R10-4 B) : l'indice « %s » (tâche élément) nomme un élément de R10-1 hors de "
                    + "l'exemple : %s", item.indice(), nommeAutre ? autre.group() : "")
            .isFalse();
}
```

`sansLExemple` retire de l'indice la zone qui va de *par exemple* au premier `.`, `!` ou `?` qui suit, ou a la fin : la meme zone que celle dont `G13` lit les segments. La verification par proposition (`G10-4` B, *"nomme une proposition de son item"*) reste : elle seule refuse un exemple qui est une proposition (sonde 50), et elle passe avant, les sondes 41, 42 et 50 gardent leur message.

| Sonde | Attendu | Obtenu |
|---|---|---|
| sa sonde : *"Lequel ne voit-on pas à la montagne ?"*, *la mer* ; *le sommet*, *la forêt*, indice *"Pense à la neige."* | refus | `T3` seule (*neige*) |
| de moi : B littoral, *"Sur le littoral, il y a par exemple la mer. Pense à la neige."* | refus | `T3` seule ; tue le mutant qui n'appliquerait `T3` qu'hors du littoral |
| de moi : B ville, indice *"Pense aux pistes."* | refus | `T3` seule (*pistes*, par `T5`) |
| items 2 et 5 | passe | passe |
| item 5 sous le mutant qui ne retire pas l'exemple | - | refuse (*mer*) : le mutant meurt sur le materiau reel |
| sondes existantes 43, 45, 46, 51, 53 | passe | passe |

### 2.4 `T4` et `T5`

**`T4`** : `FORME_PAYSAGE` porte *campagnes* vers la campagne et *littoraux* vers le littoral, a cote de *montagnes* et *villes*. Sondes : indice de A *"Compare les campagnes."* et *"Pense aux littoraux."*, refusees par `G10-4` (*"nomme un des quatre paysages"*) ; la forme actuelle les laisse passer, ni l'une ni l'autre n'etant une forme de paysage ou d'element (lecture du code). `FORME_PAYSAGE` sert aussi a reconnaitre une proposition : *"les campagnes"* y devient la campagne, comme *"les montagnes"* l'est deja ; aucun item ne l'ecrit.

**`T5`** :

```java
private static final List<String> FORMES_DE_L_INDICE_SEULEMENT = List.of("piste", "pistes", "ski");

private static final Pattern ELEMENT_DANS_UN_INDICE = motifBorne(java.util.stream.Stream.concat(
        FORME_ELEMENT.keySet().stream(), FORMES_DE_L_INDICE_SEULEMENT.stream()).toList());
```

`motifBorne` trie deja du plus long au plus court : *pistes de ski* est trouve avant *pistes*. `FORME_ELEMENT` ne change pas.

| Sonde | Attendu | Obtenu |
|---|---|---|
| indice de A *"Regarde s'il y a des pistes."* (sa sonde) | refus | `G11`, *pistes* |
| indice de A *"Pense au ski."* (de moi) | refus | `G11`, *ski* |
| enonce *"On voit des pistes et une ferme. Dans quel paysage est-on ?"* (de moi) | refus | `G10-1`, *pistes* : tue le mutant qui ajouterait les trois formes a `FORME_ELEMENT` |

**Materiau reel** : le corpus ecrit deja cette forme dans un indice. `cm1-histoire-geographie-nommer-en-geographie.yaml` porte *"Pense à la neige et aux pistes."* : un auteur de ce depot nomme les pistes de ski par *pistes*, et `G11` actuelle le laisserait passer dans un indice de `-10`.

### 2.5 `T6`

Rien. *Seul* n'est dans aucune des listes (`JUGEMENT`, `FORME_ELEMENT`, `FORME_PAYSAGE`), et aucun ajustement ne l'y met.

### 2.6 Ses textes passent `G10-1` a `G13`

Textes exacts de sa section 5, appliques a la copie (`L1`, `L2` avec leurs distracteurs et la declaration `TRV-CE1-2 : a, a` de l'item 6 ; `L3`, `L4` avec l'insecable avant *:*). **Les 53 tests du paquet, le transverse et le test de rang passent sous les gardes en place ; les 54 du paquet passent sous les gardes ajustees.** Par garde :

| Texte | `G10-1` | `G10-2` (`T1`, `T2`) | `G10-4`, `G11` (`T3` a `T5`) | `G10-5`, `G10-6` | `G12`, `G13` | Transverse |
|---|---|---|---|---|---|---|
| `L1` *plage*, *falaise* ; *le littoral* ; *la montagne*, *la campagne* | passe | passe : *falaise* {littoral, montagne}, *la montagne* en distracteur | indice inchange, passe | passe | sans objet (A) | passe |
| `L2` *immeubles*, *rivière* ; *la ville* ; *la montagne*, *la campagne* | passe | passe : *rivière* {montagne, campagne, ville} | indice inchange, passe | passe | sans objet | passe |
| `L3` *"Pense d'abord au mot qui ne va qu'avec un seul paysage."* | - | - | passe : aucun paysage, aucun element | passe | sans objet | passe, distinct de l'indice de l'item 1 |
| `L4` *"Un des mots ne va qu'avec un seul paysage : trouve-le."* | - | - | passe | passe (*trouve-le*) | sans objet | passe, avec `U+00A0` avant *:* |

`G10-7` sur le paquet corrige : 8 items A et 5 items B, comptes de l'en-tete inchanges, chaque paysage bonne reponse d'au moins un item A, l'item B *sur le littoral* present.

**Ecart de citation, sans effet sur les gardes** : la colonne *Avant* de `L2` cite *"On voit des immeubles et un port. Dans quel paysage est-on ?"* ; le fichier porte *"... Quel est ce paysage ?"* (`ce1-qlm-paysages.yaml`, item 6). J'ai applique sa colonne *Apres*, *"... Dans quel paysage est-on ?"*, que `FORME_A` admet. Qu'elle veuille cette question ou l'autre est a elle ; les deux passent.

### 2.7 Un constat de precision sur `T3`, sans changement de forme

`ELEMENT_DANS_UN_INDICE` cherche sur `plat`, sans accent, comme `G11`. Sur les indices du corpus (section 5), *ferme* est trouve 8 fois et *fermes* 2 fois ; **une seule** de ces dix occurrences est une ferme (*"ses fermes espacées"*). Les autres sont *fermé* privé de son accent (*"Poing fermé"*, quatre fois ; *"un placard fermé"*, `ce1-qlm-besoins-de-la-plante` ; *"volets fermés"*) ou le verbe (*"on ferme les volets"*, *"On les ferme la nuit"*). Ecrit dans un indice de B de `-10`, un tel mot serait refuse a tort par `T3`, comme il l'est deja en A par `G11`. Le faux rouge est visible et se reecrit ; je ne change pas la normalisation, qui servirait un usage que personne n'a ecrit dans `-10`. **Ce qui me ferait changer** : un indice de `-10` refuse pour *fermé* ou pour le verbe.

## 3. `-12` (`ce1-qlm-generations`)

### 3.1 `X1` : *naissance* en dernier

**Forme**, dans `verifierLesSuites`, pour chaque distracteur, apres le refus de l'ordre inverse :

```java
private static final List<Integer> NAISSANCE_EN_DERNIER = List.of(1, 2, 0);

if (groupe.equals(EVT)) {
    assertThat(permutation)
            .as("G12-3 (R12-2 B) : « %s » (distracteur de '%s') met naissance en dernier, juste après le "
                    + "premier emploi, où elle se lit comme la naissance de son enfant", distracteur, item.enonce())
            .isNotEqualTo(NAISSANCE_EN_DERNIER);
}
```

La bonne suite d'evenements etant `SUITE_ADMISE` (deja asserte plus haut), la permutation `(1,2,0)` est exactement *"entrée à l'école, premier emploi, naissance"* : une ligne de permutation, comme `ORDRE_INVERSE`, plutot qu'une egalite de texte.

| Sonde (bonne suite *"naissance, entrée à l'école, premier emploi"*, enonce `K3`, indice `K4`) | Attendu | Obtenu |
|---|---|---|
| *"entrée à l'école, naissance, premier emploi"* ; *"entrée à l'école, premier emploi, naissance"* (sa sonde) | refus | `X1` seule ; `G12-2` et `G12-4` passent |
| les memes, dans l'autre ordre | refus | `X1` ; tue le mutant qui ne lirait que le premier distracteur |
| *"naissance, premier emploi, entrée à l'école"* ; *"entrée à l'école, premier emploi, naissance"* | refus | `X1` |
| *"entrée à l'école, naissance, premier emploi"* ; *"premier emploi, naissance, entrée à l'école"* (l'item 12) | passe | passe |
| *"naissance, premier emploi, entrée à l'école"* ; *"premier emploi, naissance, entrée à l'école"* | passe | passe |
| l'item 12 du premier jet, et sous `K3` | passe | passe |
| sondes 22 et 23 existantes (generations, chacune avec la rotation `(1,2,0)`) | passe | passe : tuent le mutant qui appliquerait `X1` a tout groupe |

### 3.2 `G12-6` contre `K1`, `K2`, `K4`, et `K3`

**Tous passent ; rien ne doit changer.**

- **`K1`** *"Pars de Mia et suis les liens jusqu'à Tom."* (item 6, bonne reponse *celle des enfants*), **`K2`** *"Pars d'Inès et remonte d'une génération à la fois."* (item 9, *celle des grands-parents*), **`K4`** *"Compare deux moments à la fois, puis le troisième."* (item 12, premier element *naissance*) : `FORME_FERMEE` ne trouve **aucune** forme dans aucun des douze indices du paquet corrige (impression du test sur la copie). *Liens* et *génération* ne sont pas des formes fermees : la table porte les generations nommees (*enfant(s)*, *parent(s)*, *grand(s)-parent(s)*, *arrière-grand(s)-parent(s)*) et les cinq evenements, pas le mot *génération*, que `R12-7` ne vise pas. *Les plus longues formes d'abord* (`T9`) ne joue ici aucun role, aucune forme n'etant presente. La premiere moitie de `G12-6` (*vit avec*, *tu*, *toi*, possessif et parente) ne trouve rien non plus : *jusqu'à*, *Tom*, *suis* ne sont pas *tu*. `G12-5` : ni chiffre ni *ans* (aucun des trois n'ecrit *dans*, et la borne laisse *dans* passer, sonde 47).
- **`K3`** *"Dans la vie de Jade, quelle suite range les moments du premier au dernier ?"* : `G12-3` lit *du premier au dernier* par `contains` sur `plat`, et le groupe `evt` veut ce sens ; `G12-1` ne trouve aucun moment de la journee (*moments* n'en est pas un) ; `G12-5` laisse *Dans* (borne). Passe.

Ce que la garde ne lit pas dans ces textes, et qui reste a elle : qu'une methode soit vraie sur tout le paquet (*remonte d'une génération* sur l'item 9) et qu'un indice ne dise pas la reponse par la parente.

## 4. Pour `marc-dev`, ce que les formes demandent au commit des corrections

1. `-06` : `g12` (1.2), son test sur le paquet, et trois sondes refusees qui recopient les items 5, 6 et 12 du premier jet, plus une sonde de passage : l'item 2 du premier jet (tue le mutant *un seul groupe*).
2. `-10` : `T1` et `T2` dans `G10-2` (`T2` en dernier, 2.2), `T3` et `sansLExemple` (2.3), `T4`, `T5` (2.4), avec les sondes des tableaux de 2.1 a 2.4, chacune jugee sur le message de sa garde ; les items 3 et 6 du premier jet recopies comme sondes de `T2`.
3. `-12` : `X1` (3.1) et ses sondes ; rien d'autre.
4. Les trois en-tetes de tests (`Ce que ce test ne garde pas`) : retirer de celui de `-10` *"que le paysage de la bonne reponse porte chaque element partage que l'enonce nomme"*, desormais garde par `T1`.
5. Aucune garde ajustee ne refuse un des textes corriges ; aucun total que les tests recomptent ne bouge (`-06` 8 et 4 ; `-10` 8 et 5 ; `-12` 8 et 4).
6. **Deux points d'ecriture, pas des refus** : `L4` porte un *:* qui exige `U+00A0` avant lui (`TYP-1`, sa section 5 le dit) ; l'enonce de `L2` est sa colonne *Apres* (2.6).

## 5. Mesure

- **Methode** : copie de l'arbre a `a516d40b` par `git archive`, hors du depot, dans un repertoire temporaire, non commitee. Sur cette copie : (A) les textes exacts de `nadia-prof` appliques par un script qui exige une seule occurrence de chaque texte avant, puis les tests du module lances tels qu'ils sont ; (C) les memes textes, plus les formes de ce fichier recopiees dans les trois classes de test et trois tests de sonde, puis le module relance ; (M) un test de mesure du corpus. Chaque lancement : `bash outils/mvn-jdk21.sh -B -pl adapters/adapter-content -am test -Dsurefire.failIfNoSpecifiedTests=false` (avec `-Dtest=` pour la mesure), **`java.version=21.0.10` dans les rapports surefire**. Les sondes du premier jet sont lues dans une copie des trois paquets prise avant correction, et non recopiees a la main. **Ce n'est pas le test** : la preuve dans le depot sera l'execution des classes que `marc-dev` commitera.
- **Perimetre** : le module `adapter-content` et ses dependances (`-am`), ni un `verify`, ni la porte technique. Un incident dit : le premier repertoire de copie, au nom generique dans un repertoire temporaire partage avec d'autres agents, a disparu apres la lecture des rapports de (A) ; (C) a ete refait dans un repertoire au nom propre, sur une copie neuve.
- **(A), gardes en place, textes corriges** : **2 542 tests, 0 echec, 0 erreur**, 201 classes du module ; dont `PaquetCe1QlmAimantTest` 48, `PaquetCe1QlmPaysagesTest` 53, `PaquetCe1QlmGenerationsTest` 39, transverse 18, rang 28.
- **(C), gardes ajustees, textes corriges** : **2 545 tests, 0 echec** (les 2 542 et mes trois tests de sonde). Un premier lancement de (C), `T2` en tete de `G10-2`, a rendu 1 echec, la sonde 12 existante (2.2) : c'est ce qui a fixe la place de la clause.
- **Premier jet sous les gardes ajustees** : `-06` `g12` refuse les items 5, 6 et 12, et eux seuls ; `-10`, les six verificateurs de contenu appeles item par item (78 verdicts) refusent les items 3 et 6, par `T2` seule, et rien d'autre ; `-12`, toutes les gardes de contenu passent sur les douze items. **Les motifs ajustes ne mordent sur aucun autre texte deja ecrit de ces paquets.**
- **(M), corpus servi** : les 196 fichiers `*.yaml` de `src/main/resources/contenu/` ; 189 lus par `PaquetLu.lirePaquet`, 7 exclus parce qu'il ne les lit pas (`ce1-mots-invariables`, `ce1-symetrie`, `cp-additions-jusqu-a-10`, `cp-complements-10-saisie`, `cp-frise-de-la-journee`, `cp-mot-dicte`, `cp-plan-de-la-classe`) ; 3 255 items, 3 255 indices. `ELEMENT_DANS_UN_INDICE` avec `T5` : 36 indices touches, dont 2 dans `ce1-qlm-*` (l'exemple *la mer* de l'item 5 de `-10`, que `T3` retire ; *fermé* de `ce1-qlm-besoins-de-la-plante`, que le test de `-10` ne lit pas) ; *piste*, *pistes*, *ski* seuls : 1 (2.4) ; *campagnes*, *littoraux* : 0. Aucune de ces gardes ne lit un autre fichier que son paquet : **aucun test existant ne change d'etat** hors des trois paquets.

## 6. Passages de mes avis que j'abandonne

Mes avis ne sont pas reecrits. Chaque passage ci-dessous y reste et cesse de valoir.

| Code | Passage | Marqueur |
|---|---|---|
| `M1` | `avis-theo-palier-5-gardes-ce1-qlm-06-08.md`, 2.1, ligne `g3`, colonne *Doit passer* : *"n'attire pas, la gomme ; le clou, la bille en verre"* | [corrige le 2026-10-27, `theo-archi`, `avis-theo-palier-5-gardes-apres-lecture-ce1-qlm-06-10-12.md`, section 1.1 : cet item a deux propositions non attirees et `g3` le refuse ; l'item qui passe est *la gomme* ; *le clou*, *l'agrafe*] |
| `M2` | `avis-theo-palier-5-gardes-ce1-qlm-10-12.md`, 1.1, *"`FORME_PAYSAGE` : littoral, montagne(s), campagne, ville(s)"* | [corrige le 2026-10-27, `theo-archi`, ce fichier, section 2.4 : *campagnes* et *littoraux* s'ajoutent] |
| `M3` | `avis-theo-palier-5-ajustements-gardes-ce1-qlm-10-12.md`, 1.1, *"Restent admis, un par distracteur : `(1,0,2)` et `(0,2,1)` (echanges de voisins), `(1,2,0)` et `(2,0,1)` (rotations)."* en tant qu'il vaut pour la suite d'evenements | [corrige le 2026-10-27, `theo-archi`, ce fichier, section 3.1 : sur la suite d'evenements, `(1,2,0)` est refusee ; sur les generations et les prenoms, le passage vaut] |

## 7. Ce qui reste lu par `nadia-prof`

En plus de ce que ses trois lectures et mes avis precedents listent :

- `-06` : un nom autre que *papier d'aluminium* qui donnerait la reponse par une lecture fausse ; *papier d'aluminium* dans un seul groupe distracteur de B (admis par elle) ; que *fil de fer* ne devienne pas le signe du bon groupe ; la longueur en caracteres du bon groupe (sa reserve).
- `-10` : un element nomme dans un indice par un mot hors des formes (*le sable*, *les vagues*, *l'eau*, ses exemples) ; *fermé* ou le verbe *fermer* refuses a tort par `T3` et `G11` (2.7) ; une definition dans l'explication de *littoral* ; la question choisie pour `L2` (2.6).
- `-12` : qu'une methode d'indice reste vraie sur tous les items du paquet ; qu'un indice ne dise pas la reponse par la parente ou par la vie ; l'autre rotation, *"premier emploi, naissance, entrée à l'école"*, qu'elle admet.

## 8. Actions, verifications

| Code | Quoi | Qui |
|---|---|---|
| `A1` | Section 4, points 1 a 4, dans le commit des corrections de `-06`, `-10`, `-12`, avec les textes de `nadia-prof` | `marc-dev` |
| `A2` | Lire 1.2 (`g12`, ce qu'elle ne garde pas) et 2.6 (la question de `L2`) | `nadia-prof`, par `sofia-sm` |

**Cout** : je ne le chiffre pas ; le classer est a `sofia-sm`.

**Duree** : debut non lu par `date` ; premiere lecture 13:47:39 ; fin **14:05:26**, lue par `date` apres les deux verifications ci-dessous, avant le commit.

**Verifications avant commit**, ce fichier present dans l'arbre et indexe, base `a516d40b`. Il ne cree ni ne renomme aucun identifiant de condition.

- `bash outils/mvn-jdk21.sh -B -q -pl bootstrap -am test -Dtest=ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest -Dsurefire.failIfNoSpecifiedTests=false` : rapport *tests="10" errors="0" skipped="0" failures="0"*, `java.version=21.0.10`. Perimetre : ce seul test, module `bootstrap` et ses dependances.
- `bash .github/scripts/verifier-chemins.sh < /dev/null` : *"Aucun chemin de poste de travail ni nom de compte dans les fichiers verifies."*, sortie 0.
- Aucun `mvn verify`, aucun autre job de `.github/workflows/ci.yml` lance.

**Signe** : `theo-archi`, s57, palier 5, 2026-10-27.
