# s37 - Avis de faisabilite (`theo-archi`)

- **Date** : 2026-09-21
- **Base** : `round-two` a `f118c17a`, stories de `camille-po` a `67ebefe0`
- **Worktree** : `s37-theo`, ouvert depuis `round-two` a `f118c17a`

Deux stories, deux verdicts de nature differente. `S36-ORDRE-NIVEAUX` est **faisable et livree**
avec son `ADR` et ses regles. `S37-1` est **faisable sous une condition qui n'est pas technique** :
la question que `camille-po` me route a une reponse mesuree, et cette reponse deplace la decision
vers `nadia-prof` au lieu de la fermer.

## 1. Methode des mesures citees ici

Toutes les mesures de la section 2 portent sur la **classe de production reelle**
`AdditionsBorneesContentSource`, instanciee avec le descripteur et les parametres indiques, jamais
sur une reimplementation de l'algorithme.

- **Protocole** : 200 000 tirages par graine, comme `DEC-0007` ; `dejaVus` vide a chaque tirage
  pour isoler la propriete mesuree de l'evitement des doublons ; graines fixees et declarees.
- **Perimetre de build** : `-pl domain -am`, module `domain` seul mais avec ses dependances
  reconstruites (`CLAUDE.md`, troisieme exigence de build).
- **Version** : JDK **21.0.10**, valeur rapportee par la machine virtuelle qui a execute les
  tests via `bash outils/mvn-jdk21.sh`, jamais celle qu'une entree de toolchain declare
  (`ADR-0071`). Fait a signaler au passage : sur ce poste, l'entree de toolchain que **le shell**
  lit et celle que **Maven** lit ne sont pas le meme fichier, et elles ne designent pas le meme
  JDK ; l'enveloppe le dit en premiere ligne et resout le bon. Une mesure lancee sans elle aurait
  porte un autre numero.
- **Mesures jetables** : les deux classes de mesure ont ete ecrites dans le worktree, executees,
  puis supprimees ; la mutation de la constante (section 2.2) a ete annulee. `git status
  --porcelain` vide apres coup. Elles ne sont pas commitees, parce qu'un test de mesure sans cible
  n'assertionne rien : le test de mesure **qui restera** est celui que le 6e critere de `S37-1`
  demande, et il ne peut s'ecrire qu'une fois la cible de `nadia-prof` connue.

## 2. `S37-1` - la constante `ACCEPTATION_OPERANDE_NUL_SUR` a `sommeMaximale = 100`

### 2.1 La question posee, et pourquoi la reponse n'est pas "recalibrer"

`camille-po` demande si cette constante doit etre recalibree pour `sommeMaximale = 100` ou si le
mecanisme reste valable a cette echelle. **Ni l'un ni l'autre : a cette echelle le mecanisme ne
fait plus rien d'utile, et aucune valeur de la constante ne peut le lui faire faire.**

Proportion de tirages comportant au moins un operande nul, constante inchangee (valeur 3) :

| `sommeMaximale` | graine 2026 | graine 1 | graine 7 |
|---|---|---|---|
| 10 (production `MAT-CP-03`) | 12,868 % | 12,850 % | 12,836 % |
| 20 | 6,503 % | 6,596 % | 6,545 % |
| 100 | 1,323 % | 1,352 % | 1,319 % |

La ligne 10 reproduit les 12,92 % de l'amendement du 2026-08-05 a `DEC-0004` : le protocole est
donc le bon, ce qui donne son poids a la ligne 100. A `sommeMaximale = 100`, la proportion est
**1,32 %**, soit un quart de la borne basse de la bande `DEC-0004` (5 %) et un dixieme de la cible
de 12,9 %.

### 2.2 Le plafond du levier, mesure par mutation et non deduit

La constante a ete portee a 1 puis a 2 dans la classe de production, la mesure relancee, puis
l'arbre restaure. A `sommeMaximale = 100`, graine 2026 :

| Constante | Proportion d'operande nul |
|---|---|
| 1 (le filtre accepte tout, mecanisme neutralise) | 3,862 % |
| 2 | 1,967 % |
| 3 (production) | 1,323 % |

**Le filtre ne sait que retirer des tirages a operande nul ; il ne peut jamais en ajouter.** La
valeur 1 est donc son plafond, et ce plafond vaut **3,86 %** - sous la borne basse de `DEC-0004`.
Aucune valeur de cette constante ne produit une proportion de la bande CP a l'echelle 100.

La cause est arithmetique et se lit dans la classe : les couples valides sont en O(n²)
(5 150 a n = 100, contre 65 a n = 10) et les couples a operande nul en O(n) (200 contre 20). La
part brute passe mecaniquement de 30,8 % a 3,88 %. **A `sommeMaximale = 100`, le probleme que
cette constante a ete ecrite pour corriger n'existe plus.**

### 2.3 Ce que cela laisse comme options, et a qui revient chacune

**Ce qui est a moi** : le mecanisme. Trois cas, et ils dependent tous de la meme reponse
pedagogique.

| Cible CE1 fixee par `nadia-prof` | Ce que cela coute techniquement |
|---|---|
| Environ 1,3 % (ce que le code produit deja) | **Rien.** Ni ligne de Java, ni cle YAML. `sommeMaximale: 100` et c'est tout. |
| Une autre valeur dans `]0 ; 3,86 %]` | **Une cle de paquet.** La constante devient un parametre lu par `AdditionsBorneesContentSourceFactory`, au meme titre que `sommeMaximale` - un changement de donnee, conforme a `ADR-0006`. Pas d'`ADR` neuf : `ADR-0006` a deja decide que les bornes de ce generateur vivent dans son paquet. |
| Plus de 3,86 % | **Un mecanisme neuf** (sur-echantillonnage, l'inverse du filtre actuel). Je le deconseille pour un seul paquet : je prefere alors le paquet statique de type `question`, comme `cp-soustractions.yaml` et tous les paquets de calcul CM1. |

**Un defaut latent trouve en mesurant, et il ne depend d'aucune reponse.** La constante est un
`private static final` de la classe : elle est **partagee par tous les paquets de type
`additions-bornees`**. Il n'y en a qu'un aujourd'hui, donc personne ne l'a vu. `S37-1` en cree un
second. Tant que la cible CE1 reste celle que le code produit deja, le partage ne coute rien ;
des qu'elle en differe, une constante partagee reglant deux paquets est la famille de defaut
d'`ADR-0006`, et elle se paie en la descendant dans le paquet.

**Ce qui n'est pas a moi** : la cible CE1 elle-meme. `camille-po` l'a deja routee a `nadia-prof`,
et `DEC-0004` est explicite - les 12,9 % sont qualifies de propres au *"programme de calcul de
CP"*, jamais de transverses.

### 2.4 Le point que personne n'a pose, et que je route aussi a `nadia-prof`

La question de l'operande nul est la plus petite des deux. Mesure sur la meme classe,
`sommeMaximale = 100`, 200 000 tirages, graine 2026 :

- somme moyenne **67,1**, somme mediane **71** ;
- **35,97 %** des tirages ont une somme superieure a 80 ; **4,10 %** une somme inferieure ou egale
  a 20 ; **1,04 %** une somme inferieure ou egale a 10 ;
- **1,76 %** seulement des tirages sont un nombre a un chiffre plus un nombre a un chiffre ;
- **42,23 %** des tirages demandent une retenue (la somme des chiffres des unites atteint 10).

Le tirage etant uniforme sur les couples, la probabilite d'une somme `s` croit lineairement avec
`s` : la somme 100 est cent fois plus frequente que la somme 1. Un paquet ainsi genere propose donc
presque exclusivement des additions a deux chiffres groupees autour de 70, dont deux sur cinq avec
retenue.

Ce n'est pas un defaut technique - c'est la forme du tirage, et `DEC-0007` point 2 avait
precisement fixe une **table de repartition par somme** pour `sommeMaximale = 10` parce que cette
forme est une question pedagogique. **Aucune table equivalente n'existe pour 100.** Que cette forme
convienne au premier paquet de mathematiques CE1 est une question de programme, pas
d'architecture : je la route a `nadia-prof` dans le meme geste que la precedente, et je ne la
tranche pas. Si la reponse est non, le generatif est le mauvais outil pour ce paquet quelle que
soit la cible d'operande nul, parce que sa forme n'est pas pilotable sans mecanisme neuf.

### 2.5 Trois points operationnels pour le developpeur, mesures

- **Un distracteur peut depasser 100.** Sur une somme de 100, les distracteurs sont 101 et 102.
  ~~Mesure : **1,32 %** des distracteurs depassent la borne a `sommeMaximale = 100`~~, contre
  **12,15 %** a `sommeMaximale = 10` - la situation est donc moins frequente qu'au CP, ou elle est
  deja servie et validee. Ce n'est pas un blocage, c'est un fait a poser devant `nadia-prof`
  puisque le paquet promet *"jusqu'a 100"*.

  **Correction du 2026-09-21, trouvee par `nadia-prof` (arbitrage, section 5), et elle a raison.**
  Le chiffre juste est **2,96 %**, plus du double. `1,32 %` etait **exactement** mon propre chiffre
  d'operande nul de la meme page : une recopie, pas une mesure. Le calcul le confirme - un
  distracteur depasse 100 quand la somme vaut 98, 99 ou 100 et que le rang tire place assez de
  voisins au-dessus, soit environ 2,9 % sur la combinatoire. La conclusion ne bouge pas (le CP sert
  deja 26,20 % de manches dont un distracteur depasse sa borne, valide), et c'est precisement ce
  qui rend la faute instructive : **un chiffre recopie d'un autre chiffre survit tant que sa
  conclusion tient**, et le prochain pourrait porter sur autre chose. Ce que j'en tire pour mes
  propres mesures : les trois autres chiffres de cette section viennent de la meme execution que le
  tableau 2.1 et sont reproduits par la mesure independante de `nadia-prof` (section 1 de son
  arbitrage, six chiffres sur six a l'ecart de graine pres) ; celui-la etait le seul que je n'avais
  pas lu dans une sortie de mesure. Le garde-fou qui manque n'est pas technique : c'est de ne
  jamais ecrire un pourcentage qui ne soit pas la copie d'une ligne de sortie.
- **Le 4e critere de la story est vide si le generatif est retenu.** `additions-bornees` produit un
  enonce qui **est** l'expression (`"34 + 28"`) et aucun enonce distinct ; `LON-CE1-1` et
  `LON-CE1-2` n'ont alors rien a juger. Le critere ne devient portant que si le paquet statique
  est retenu.
- **`CE1` est deja nomme dans la sequence d'`ADR-0095`** : le garde-fou de completude livre a
  `S36-ORDRE-NIVEAUX` laisse passer ce paquet sans rien a faire, et la puce CE1 se rangera entre
  CP et CM1 sans ligne de code. Rappel du garde-fou voisin qui, lui, mordra : `ADR-0078` refuse un
  titre de jeu qui nomme son niveau - pas de *"Additions CE1"*.

### 2.6 Verdict `S37-1`

**Faisable en l'etat, sous une condition qui n'est pas technique.** Le chemin le moins cher -
`sommeMaximale: 100` dans un paquet YAML, zero ligne de Java - est ouvert et le reste tant que la
cible CE1 d'operande nul est celle que le code produit deja (environ 1,3 %). Il n'y a **rien a
recalibrer** : la constante est inerte a cette echelle et son plafond mesure, 3,86 %, est sous la
bande CP. Ce qui bloque un engagement ferme n'est ni un `ADR` ni un refactor, ce sont **deux
reponses de `nadia-prof`** - la cible d'operande nul (deja routee par `camille-po`) et la forme de
la distribution des sommes (section 2.4, que je route maintenant). Tant qu'elles manquent, le 6e
critere d'acceptation n'est pas ecrivable : il compare une mesure a une cible qui n'existe pas.

## 3. `S36-ORDRE-NIVEAUX` - faisable, et livree

**Faisable en l'etat, moyennant un `ADR`.** `ADR-0095` est ouvert, accepte, et le mecanisme est
dans l'arbre avec ses regles dans le meme geste.

**Le mecanisme retenu** : une **cle de tri**, `OrdreDesNiveaux.SEQUENCE` dans `application`,
appliquee par `PartieService.niveauxDuCatalogue()`. Le niveau reste la `String` que le paquet
declare ; `ADR-0006` n'est pas rouverte, ce que le 4e critere exige. Les deux autres formes que la
story citait sont ecartees dans l'`ADR` : une cle dans le descripteur de chaque paquet remet le
meme ordinal dans plus de quarante fichiers editables independamment (dette 2 de `CLAUDE.md`), un
comparateur dans un adaptateur contredit `ADR-0044`.

**Ce qui a demande le plus d'attention n'est pas le tri.** Un tri sans garde-fou de completude
passe les quatre criteres de la story et ne garantit l'ordre que des niveaux deja connus : le jour
ou un paquet declare `CE2`, ou ecrit `ce1` en minuscules, il est range en dernier sans que rien ne
rougisse. La moitie de cet `ADR` qui interdit quelque chose est donc
`ChaqueNiveauServiEstNommeDansLaSequenceTest`, qui refuse tout niveau servi absent de la sequence,
sur une population **derivee du catalogue servi** par le recensement existant plutot que recopiee.

**Le second point mesure** : la regle neuve qui refuse l'enumeration du niveau n'est pas un doublon
d'`ADR-0006`. La regle d'`ADR-0006` censee tenir cette propriete **laisse passer la forme la plus
probable** - un `enum Niveau` pose dans `com.recreo.domain.content`, que son `estPermis` admet.
C'est verifie par assertion, pas affirme : les deux contre-exemples sont passes aux deux regles
dans le meme test, la neuve rougit et celle d'`ADR-0006` reste verte.

**Le cout, nomme** : ajouter un niveau reste une donnee cote contenu, mais cesse d'etre gratuit
cote code - un paquet d'un niveau que la sequence ne nomme pas rend le build rouge. `CE1` y est
deja, `S37-1` ne paie rien ; un futur `CE2` paiera une decision produit sur sa place, ce qui est
l'intention.

**Ce qui reste a faire sur cette story** : rien de technique. Je l'ai livree entiere plutot que de
laisser un type declare et non cable - c'est la dette 1 de `CLAUDE.md`, et la laisser a moitie pour
preserver une tache aurait ete la reproduire. L'affectation du budget ainsi libere est a
`sofia-sm` ; je ne repriorise pas.

## 4. Porte technique de ce lot

`mvn -B clean verify` sur la racine du worktree `s37-theo`, JDK **21.0.10** rapporte par la machine
virtuelle des tests : **BUILD SUCCESS**, cinq modules verts, **0 echec**. Les trois autres jobs de
`.github/workflows/ci.yml` - `prefixes`, `chemins`, `outils` - sont lances sur ce diff et reportes
dans `review.md` : `mvn verify` n'est que le contenu du job `build`, et annoncer la porte technique
sur sa seule foi est la faute que la cloture de s31 a sanctionnee.

Comptes de regles, avec leur methode : occurrences litterales dans `src/test/java` des cinq modules
declares au pom agregateur, `target/` exclu, mesurees le 2026-09-21 - `static final ArchRule`
**34 -> 36**, `@ArchTest` **33 -> 35**. Les deux comptes different d'une unite avant comme apres,
pour la cause qu'`ADR-0094` a deja nommee : une `ArchRule` du depot n'est pas annotee. Les deux
regles neuves le sont, le delta est donc de +2 des deux cotes.

## 5. Addendum du 2026-09-21 - le mecanisme retenu pour `S37-1`, apres l'arbitrage

`nadia-prof` a tranche les deux questions que la section 2 lui routait
(`squad/conformite/ARBITRAGE-s37-operande-nul-et-forme-du-tirage-a-l-echelle-100.md`). Elle ferme
le chemin a zero ligne de Java et me laisse le choix du mecanisme. **`ADR-0096`** le tranche.

**Retenu : un paquet statique de type `question`**, et `ADD-CE1-2` encodee en garde-fou de contenu
dont le perimetre derive de la forme des items. Les cinq motifs sont dans l'`ADR` ; les deux qui
decident sont mesures :

- **Le statique est la norme du depot pour le calcul, pas l'exception.** Comptage du 2026-09-21 :
  21 paquets de mathematiques servis, **20 de type `question`, 1 generatif** - et ce dernier est le
  premier paquet jamais ecrit. Tous les paquets de calcul CM1 sont statiques.
- **Le juge reste independant de ce qu'il juge.** La partition ordonnee doit etre encodee de toute
  facon pour le garde-fou. Avec un paquet statique elle est ecrite une fois, dans le test, et rien
  en production ne la connait. Avec un generateur qui tire la forme avant le couple, la meme
  partition vit aussi en production et le garde-fou la duplique ou la partage.

**La regle est la table, jamais le moyen** : le garde-fou compte des couples et ne regarde pas le
type du paquet. Un generateur qui tiendrait les huit bandes passerait sans qu'une ligne bouge. Ce
qui me ferait basculer est ecrit dans l'`ADR` : un **deuxieme** paquet demandant la meme table.

### Ce qui est pret pour `marc-dev`, et ce qui ne l'est pas

Livre sur la branche `s37-theo-mecanique`, `mvn -B clean verify` vert :

- `adapters/adapter-content/src/main/resources/contenu/ce1-additions-jusqu-a-100.yaml` - **32
  items**, repartition **exacte** : 0 operande nul, 5 sommes <= 20 (15,62 %), 3 doubles (9,38 %),
  5 complements a la dizaine (15,62 %), 5 ajouts d'un nombre rond (15,62 %), 6 deux-chiffres-plus-
  un-chiffre (18,75 %), 4 sans retenue (12,50 %), 4 avec retenue (12,50 %). Les huit bandes sont
  tenues avec de la marge des deux cotes. Rang de la bonne reponse **31,25 / 37,50 / 31,25 %**,
  dans la bande que `nadia-prof` a fixee en s01 et au-dessus de son plancher de 10 %
  (`LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest`, vert). **Aucun distracteur ne depasse
  100** - la voie statique eteint au passage l'observation de la section 2.5.
- L'entree de catalogue, le garde-fou et son jeu fautif, l'extension de l'inventaire du port
  entrant.

**Ce qui n'est pas de moi et reste du, ecrit plutot que laisse croire couvert.** Trois chaines de
prose sont des **propositions**, pas des decisions d'architecture : le titre *"Les grandes
additions"*, la consigne, et les 32 indices. `nadia-prof` garde l'indice nommement (section 6.4 de
son arbitrage : *"rien ne garantit qu'il aide a 36 + 28"*), plus l'exactitude de chaque enonce et
la plausibilite de chaque distracteur (6.5), plus les quatre autres points de sa section 6. Le
travail de `marc-dev` est de les reprendre autant qu'il le juge utile : le garde-fou lui dit
immediatement s'il sort des bandes, ce qui rend l'iteration de contenu bon marche. C'est la ce que
j'ai voulu lui livrer - **la table rendue executable**, pas un contenu a prendre tel quel.

**Consequence de planning que je signale sans la trancher** (`sofia-sm`) : entre `S36-ORDRE-NIVEAUX`
livree et ce lot, la part de `S37-1` qui restait chiffrable a beaucoup diminue. Je ne repriorise
pas.

### Ce que les deux lots de s37 donnent ensemble, mesure

Le paquet CE1 est declare **en dernier** dans `catalogue.yaml`. Sur le contexte Spring assemble,
`PartieUseCase.niveauxDuCatalogue()` rend **`[CP, CE1, CM1]`** - mesure le 2026-09-21, classe de
mesure supprimee apres coup. Sans `ADR-0095`, l'en-tete aurait affiche CP, CM1, CE1 le jour ou ce
paquet est entre. La story ecrite *"pour une propriete qui n'etait pas encore mise a l'epreuve"*
l'a ete au sprint suivant sa redaction.
