# Avis technique - les quatre tests de `PaquetCpEmcExclusionTest` face a la refonte de la tache B

- **Auteur** : `theo-archi`. **Date** : 2026-09-10. **Route par** : `nadia-prof`, arbitrage du
  2026-09-10, section 4.4 (symptome nomme, cause routee), via `sofia-sm`.
- **Du avant** l'ouverture de `S34-EXC-TACHEB` par `marc-dev`, au meme titre que le prealable
  d'`EMC-CP-07`.
- **Arbre mesure** : `round-two` a `f705d62a`. **JDK 21.0.10** (`java.runtime.version=21.0.10+8-LTS-217`),
  valeur que la machine virtuelle des tests rapporte d'elle-meme dans le bloc de proprietes des
  rapports Surefire, jamais lue dans l'entree de toolchain qui la designe (`ADR-0071`).
- **Ce document ne fait pas** : il ne prononce aucun Done, ne redige aucun item, ne tranche aucune
  question pedagogique - ni la conjonction de `C-2` et d'`EXC-R7`, ni le degre de variation exige de
  la bonne reponse. Ces deux points restent a `nadia-prof`, et ils sont nommes en section 5.

## 1. La mesure d'abord, parce qu'une des quatre reponses n'etait pas deductible

*Methode : dans un worktree detache sur `f705d62a`, les cinq items de la tache B remplaces par une
**sonde de refonte** - enonce qui installe une moquerie repetee, trois conduites en propositions, la
bonne reponse nommant l'adulte que la situation rend present et **variant** d'un item a l'autre (trois
valeurs sur cinq items), les deux autres propositions etant des conduites sans adulte et sans charge
de ton. La tache A n'est pas touchee. `bash outils/mvn-jdk21.sh -B clean verify` sur la racine,
reacteur complet a six modules, `-Dmaven.test.failure.ignore=true` pour que les six s'executent.
Worktree supprime, arbre partage jamais touche.*

**La sonde n'est pas du contenu et ne pretend pas l'etre** : elle a la forme que la refonte aura, pas
sa qualite pedagogique. Elle sert a savoir quels garde-fous mordent, et sur quel motif.

> **`PaquetCpEmcExclusionTest` : 7 tests, 3 echecs.** Et le fait qui n'etait pas deductible :
> **`laTacheDAdulteNeSertPlusDeCandidatAuRabais` ne rougit pas. Il devient vert a vide.**

| Methode | Sous la sonde | Motif |
|---|---|---|
| `chaqueItemAppartientAExactementUneDesDeuxTaches` | **rouge** | *item item-7 : sa bonne reponse "le dire a sa maitresse" n'entre dans exactement une des deux taches* |
| `fer3AuMoinsDeuxTachesDistinctesEtLaMoinsServieAuMoinsTroisItems` | **rouge** | *la tache d'identification de l'adulte est servie* - elle compte zero item |
| `laTacheDAdulteRepresenteLesTroisAdultesEnBonneReponse` | **rouge** | *les trois adultes valides doivent tous etre representes en bonne reponse* |
| `laTacheDAdulteNeSertPlusDeCandidatAuRabais` | **vert, sur une population vide** | sa boucle parcourt `itemsDAdulte()`, qui ne rend plus aucun item : le corps ne s'execute jamais |
| `fer2AuMoinsDixItems`, `fab1aLaTacheDeClassementSertSesDeuxCategories...`, `chaqueItemProposeExactementDeuxDistracteurs` | verts | la tache A n'est pas touchee, le paquet garde onze items et deux distracteurs par item |

**Ce que cette mesure achete, et c'est le motif pour lequel je l'ai faite plutot que de raisonner sur
le fichier.** `nadia-prof` nomme `laTacheDAdulteNeSertPlusDeCandidatAuRabais` comme *"l'ecart le plus
large des quatre"* et ecrit qu'il *"interdit l'objet meme de la refonte"*. **Sur le premier point elle
a raison, sur le second la mesure dit autre chose** : il n'interdit pas la refonte, **il cesse
d'exister apres elle**. C'est le mode de defaillance que ce depot combat depuis `ADR-0013` et qu'il a
paye a `ADR-0088` - un garde-fou qui garde encore quelque chose le jour ou on le lit, et plus rien le
jour ou le contenu bouge, **sans qu'aucun rouge ne le dise**. Trois methodes bloquent la refonte ; la
quatrieme la laisserait passer en ne gardant plus rien.

## 2. Pour chacune des quatre : durcissement jamais decide, ou clause legitime ?

| Methode | Verdict | Motif |
|---|---|---|
| `laTacheDAdulteNeSertPlusDeCandidatAuRabais` | **durcissement jamais decide**, et faux vert en prime | La condition de `nadia-prof` a s31 est *"pas de bouton ecartable au ton"*. Le test exige que **chaque** distracteur soit l'un de trois adultes litteraux : ce n'est ni la clause, ni une approximation de la clause - c'est une autre propriete, plus dure, qui n'a jamais ete decidee. Sa Javadoc dit d'ailleurs autre chose que son assertion : elle annonce que *"le bouton au rabais 'a personne' ne reapparait pas"*, ce que l'assertion ne dit pas |
| `laTacheDAdulteRepresenteLesTroisAdultesEnBonneReponse` | **durcissement jamais decide** | `FAB-1a` demande que la bonne reponse **varie** ; le test exige `containsExactlyInAnyOrder` de trois valeurs nommees d'avance. Trois valeurs litterales ne sont pas une variation : elles sont une liste de contenu recopiee dans un test, et c'est le contenu qui devient impossible a changer, pas la propriete qui devient tenue |
| `chaqueItemAppartientAExactementUneDesDeuxTaches` | **clause legitime, critere fautif** | La propriete - aucun item invisible de tous les comptages - est celle qu'`ADR-0088` a payee sur cinq garde-fous de rendu, et je l'ai ecrite moi-meme a s32. Ce qui casse n'est pas elle, c'est le **critere d'appartenance** : deux ensembles de chaines litterales, dont l'un est precisement ce que la refonte remplace |
| `fer3AuMoinsDeuxTachesDistinctesEtLaMoinsServieAuMoinsTroisItems` | **clause legitime, critere fautif** | `FER-3` est une clause de `nadia-prof`, non contestee. Elle ne rougit que parce qu'elle compte les taches avec le meme critere litteral que la precedente. **Un seul defaut, deux methodes** |

**Le defaut commun aux quatre est un seul, et il porte un nom** : `ADULTES_VALIDES`, trois chaines de
contenu recopiees dans le fichier de test et promues au rang de definition de la tache B. C'est la
meme famille que les deux tests d'`EMC-CP-07` repris ce matin (`f705d62a`) - un test plus dur que la
clause qu'il pretend mesurer - et c'est aussi la dette generale que l'index d'`ADR-0089` chiffre :
**25 fichiers sur 95 tests de paquet lisent l'identite d'une tache par une operation de chaine**
(majorant, toutes ces occurrences ne sont pas des classements de tache). Ce paquet-ci a deja paye
cette dette une fois, a s32, quand le critere etait la sous-chaine `"À qui"` de l'enonce ; il l'avait
alors deplacee de l'enonce vers la bonne reponse, sans la payer.

## 3. La forme que je recommande, et ce qu'elle interdit encore

**Je ne redige pas ce test a la place de `marc-dev` ; je donne la forme et ce qu'elle garde.** Elle
est exactement celle que la reprise d'`EMC-CP-07` de ce matin emploie, et pour le meme motif.

1. **La tache A garde son critere litteral**, et lui seul. Ses deux categories sont inchangees,
   `nadia-prof` les a confirmees, et rien dans `S34-EXC-TACHEB` ne les touche.
2. **La tache B se definit comme le complement** : tout item qui n'est pas de la tache A. Le partage
   redevient exhaustif **par construction** plutot que par assertion - un item ne peut plus tomber
   hors des deux sacs, quel que soit ce que la refonte ecrit. `FER-3` recompte alors juste, sans
   changer d'un mot.
3. **Ce que la disparition de `chaqueItemAppartientAExactementUneDesDeuxTaches` coute, dit plutot que
   tu** : cette methode devient tautologique sous le complement, donc decorative, donc a retirer et
   non a garder pour la forme. Ce qu'elle garantissait de reel est repris par une clause qui, elle,
   mord : **aucune bonne reponse de la tache B n'est une categorie de classement** - c'est la forme
   qui empeche la tache B de se replier sur un second classement, et c'est la clause que j'ai ecrite
   ce matin sur le paquet voisin.
4. **Le garde-fou de regression du bouton au rabais est reecrit sur ce qui a ete retire**, pas sur ce
   qui doit rester : **aucune proposition du paquet, dans aucune des deux taches, n'est le bouton
   `"a personne"`** que `nadia-prof` a fait retirer a s31. Perimetre derive du paquet entier, donc
   insensible a la refonte, et rouge le jour ou ce bouton revient - ce que la version actuelle ne
   ferait pas.
5. **La variation de la bonne reponse de la tache B** (`C-2`) est encodable **sans seuil invente** :
   la tache B ne porte pas une bonne reponse unique. **Tout seuil plus strict** - deux valeurs, trois,
   toutes distinctes - **est une decision pedagogique et appartient a `nadia-prof`**, pas a moi
   (section 5).

## 4. Deux garde-fous que la sonde a fait rougir et que personne n'a nommes - ce sont les deux pieges de la reecriture

Ils ne concernent aucune des quatre methodes, ils sont **de ma sonde**, et c'est precisement ce qui
les rend utiles : ce sont les deux fautes qu'une reecriture de cinq enonces commet sans le vouloir.

- **`TYP-1` / `ADR-0067`** - `AucunSigneDoubleNEstDetachableDeSonMotTest.leCliquetDeLaDetteDeTyp1EstExact` :
  *"cp-emc-exclusion porte 5 espace(s) ordinaire(s) devant un signe double et n'est pas dans la table
  de dette"*. Mes cinq enonces posent une espace ordinaire devant leur `?`. **Le paquet servi
  aujourd'hui pose l'espace insecable partout** ; la reecriture doit la reposer, caractere par
  caractere.
- **`LON-1` / `ADR-0089`** - `AucunEnonceDeCpNeDepasseQuinzeMotsNiDeuxPhrasesTest`, deux fois :
  *"cp-emc-exclusion.yaml : 2 enonces depassent 15 mots la ou la dette en tolere 1 - item 2 (16 mots),
  item 10 (16 mots)"*. L'item 2 est **de la tache A**, il est deja dans la dette declaree ; l'item 10
  est de ma sonde. **Le cliquet est a egalite** : un enonce trop long de plus est rouge, et un enonce
  trop long de moins l'est aussi tant que la table de dette n'est pas mise a jour dans le meme diff.

## 5. Ce que je ne tranche pas, et a qui cela revient

| Question | A qui | Motif |
|---|---|---|
| **`C-2` et `EXC-R7` sont-elles satisfaisables ensemble** - nommer l'adulte dans la bonne reponse pendant que l'enonce installe le lieu est ce qui a produit `EXC-R7` | `nadia-prof` | Elle signale elle-meme ne pas l'avoir verifie, et demande a `marc-dev` de lui router le cas plutot que d'arbitrer seul. **Ma sonde ne repond pas a cette question** : elle a la forme de la refonte, pas la garantie qu'elle ne se gagne pas en appariant deux mots |
| **Le degre de variation exige de la bonne reponse de la tache B** | `nadia-prof` | Ce qui est encodable sans rien inventer est *"pas une reponse unique"*. Tout seuil au-dela est un jugement sur ce qu'un enfant retient |
| **Si une conduite du genre *attendre que cela s'arrete* est un candidat acceptable** (`CAT-1`, `PLA-1`) | `nadia-prof` | Elle l'ecrit : elle ne l'autorise ni ne l'interdit d'avance, elle le juge a l'ecran. **Ma sonde en emploie**, et cela n'emporte rien |

## 6. Ce que ce document engage

Aucun fichier de test, de contenu ou de production n'est modifie par lui. La reecriture de
`PaquetCpEmcExclusionTest` appartient au commit de `S34-EXC-TACHEB`, avec le contenu qu'elle garde -
un test de paquet reecrit avant le contenu qu'il juge garderait un etat que personne n'a decide.
