# Avis de faisabilite technique - s33 (`theo-archi`)

Quatre stories portees a *"a determiner"* par le planning de s33, section 4. Cet avis remplit cette
colonne et rien d'autre : il ne repriorise rien, ne redige aucune story, n'ecrit aucun item, aucune
consigne, aucun terme de remplacement, et ne prononce aucun Done.

## 0. Ce sur quoi tout ce qui suit a ete mesure

**Worktree dedie `s33-theo`, cree sur `1740807e` (`git worktree add ../recreo-s33-theo -b s33-theo
1740807e`), lignee verifiee.** Toute mesure de cet avis est faite depuis ce worktree et depuis aucun
autre. Les worktrees de `lea-dev` et de `marc-dev` sont des repertoires freres et ne sont jamais
balayes ; les branches `s33-marc` (`4adc751f`) et `s33-sofia` (`556cad9f`) sont livrees et non
fusionnees, et **aucun chiffre de cet avis ne porte sur leur contenu** - il porte sur l'arbre de
`1740807e` augmente de mon seul diff.

**JDK 21.0.10**, version **rapportee par la machine virtuelle qui execute les tests** et non par
l'entree de toolchain qui la designe (`ADR-0071`) : `java.version=21.0.10`,
`java.runtime.version=21.0.10+8-LTS-217`, `Java HotSpot(TM) 64-Bit Server VM`, lus depuis un test
temporaire execute par Surefire puis retire (il n'est pas dans le diff).

**Commandes.** Porte technique : `bash outils/mvn-jdk21.sh -B clean verify` sur la racine, reacteur
complet a six modules. Les mesures partielles de cet avis portent toutes `-am` et nomment leur
perimetre a cote de leur chiffre : `bash outils/mvn-jdk21.sh -B -pl adapters/adapter-content -am
-Dtest=... test` et `-pl bootstrap -am -Dtest=... test`.

**Chaque chiffre porte sa methode dans le meme paragraphe.** Les comptes de contenu de cet avis sont
lus sur les fichiers servis (`catalogue.yaml` et `contenu/*.yaml` d'`adapter-content`), jamais
recopies d'un document, et je nomme leur perimetre a chaque fois.

---

## 1. `S33-HG-04` - **faisable en l'etat, sans ADR.** Une ligne de test a mettre a jour dans le meme commit

**Avis : faisable en l'etat.** Aucun `ADR` requis. `marc-dev` peut engager la story.

### 1.1 Le rayon reel, mesure et non estime

*Methode : lecture du paquet servi `contenu/cm1-histoire-geographie.yaml` champ par champ (enonce,
bonneReponse, distracteurs, indice, explication, texteSupport) et recherche du terme dans l'arbre
entier ; worktree `s33-theo` a `1740807e`.*

| Ce qui porte le terme | Occurrences |
|---|---|
| `bonneReponse` de l'item 85 (`HG-CM1-04`) | **1** |
| Tout autre champ de tout autre item, tous paquets servis confondus | **0** |
| Code de test | **1** - `PaquetCm1HistoireGeographieTest`, l'enumeration `Set.of(...)` de `fer3AuMoinsDeuxTachesSurHgCm104LaMoinsServieAuMoinsTroisItems` |
| Fichiers commites non executables (commentaires du paquet, backlog, arbitrages) | plusieurs, aucun n'est un garde-fou |

**Le remplacement du terme ne touche donc rien d'autre que cet item.** Le mot n'est ni un
distracteur ailleurs, ni un indice, ni une valeur reprise par un autre paquet.

### 1.2 Ce qui rougit, et ce que ca coute

Mesure directe, et non raisonnement : j'ai remplace la bonne reponse de l'item 85 par une valeur
sans contenu (`MUTATION-SANS-VALEUR-DE-CONTENU`), lance `-pl adapters/adapter-content -am
-Dtest=PaquetCm1HistoireGeographieTest`, puis remis le fichier en etat.

**Un seul test rouge sur les 24 de la classe**, et il nomme la tache et l'effectif :

```
[HG-CM1-04 : la tache "nommer le mouvement" porte exactement 3 items]
expected: 3L but was: 2L
```

**Le geste complet est donc : un champ de contenu, une entree d'enumeration, dans le meme commit.**

### 1.3 L'enumeration n'est pas le defaut, et c'est la reponse a la question posee

La question qui m'est posee est de savoir si cette enumeration litterale est le symptome d'un test
qui derive mal son perimetre, famille qu'`ADR-0013` sanctionne. **Elle ne l'est pas, et le distinguo
compte pour les prochaines fois.**

Le **perimetre** de ce test est derive : `itemsDeLaLigne("HG-CM1-04")` lit le paquet servi par le
meme chemin de classpath que le chargeur de production, et le compte du paquet est garde a
l'egalite stricte. Ce qui est enumere n'est pas le perimetre, c'est le **classificateur de tache** -
et il l'est parce que **la tache qu'un item sert n'est un champ d'aucun schema de ce depot.**
`FER-3` est une condition sur les taches ; aucun garde-fou ne peut donc la juger sans reconstruire
la tache a partir d'autre chose. Il n'y a que trois endroits ou la lire, et je les ai tous les trois
sous les yeux dans ce seul fichier : la bonne reponse contre un ensemble ecrit a la main
(`HG-CM1-03`, `-04`, `-11`, `-13`), une sous-chaine de l'enonce ecrite a la main (`HG-CM1-01`,
`-02`), une propriete structurelle des propositions (aucun cas ici).

**Ces trois lectures ne se valent pas, et la difference est le mode de defaillance, pas le style :**

- une bonne reponse contre un ensemble ecrit a la main **rougit** quand la bonne reponse change -
  c'est exactement le geste de cette story, et c'est ce qui rend la mise a jour obligatoire dans le
  meme commit ;
- une sous-chaine d'enonce **passe au vert** quand l'enonce est reformule, en deplacant le partage
  des taches en silence. C'est le motif d'`ADR-0089` decision E, ou ce mode a bloque `EXC-R5`, et
  c'est ce que `yanis-qa` a signale le 2026-09-08 sur les deux tests de ce fichier qui lisent encore
  l'enonce.

**Donc l'enumeration reste, et elle est le bon patron.** Ce qui manquait aux six partages de taches
de ce fichier, c'est l'autre moitie : ils ne verifiaient qu'un **plancher** (`min >= 3`), muet dans
les deux sens ou le partage se deplace vraiment. Je l'ai corrige dans ce geste ; c'est la section 5.

### 1.4 Si `nadia-prof` ouvre un quatrieme item sur cette ligne

Elle a etabli que le plafond de trois items sur la tache de nomination n'est pas structurel sur
`HG-CM1-04`. **Cote technique, un item de plus sur cette ligne coute trois mises a jour**, toutes
rouges d'abord, aucune ambigue :

1. `NOMBRE_D_ITEMS_ATTENDU` de `PaquetCm1HistoireGeographieTest` - egalite stricte, 92 aujourd'hui ;
2. le compte du lot de matiere dans `UneConditionDeLotSeCompteSurLeLotDeMatiereEtNonSurUnFichierTest`
   - `isEqualTo(92)` sur *"Histoire-geographie / CM1"* ;
3. l'enumeration et l'effectif epingle de la tache de nomination (3 -> 4).

**Le terme de remplacement ne m'appartient pas** : c'est du contenu, il va au developpeur et au
verdict de `nadia-prof`. Je note seulement une contrainte technique verifiable qui pese sur lui : le
paquet est declare hors du domaine du calcul de rang numerique dans
`LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest`, au motif qu'aucune de ses propositions ne
commence par un chiffre. Une bonne reponse commencant par un chiffre ferait donc rougir cette
declaration, par un chemin qui n'a rien a voir avec `HG-R12`.

---

## 2. `S33-EXC-R1` - **faisable en l'etat, sans ADR.** Le geste de s32 est confirme sur l'arbre de s33

**Avis : faisable en l'etat.** Aucun `ADR` requis, le mecanisme est deja couvert par `ADR-0089`.
`lea-dev` peut engager la story sans m'attendre davantage.

### 2.1 La mesure de s32 tient, et je l'ai refaite sur cet arbre plutot que de la citer

*Methode : remplacement de la consigne de `bandit-manchot-exclusion-cp` dans `catalogue.yaml` par une
valeur sans contenu, puis **`bash outils/mvn-jdk21.sh -B clean verify` sur la racine** - reacteur
complet, pas une mesure partielle - puis remise en etat du fichier. Worktree `s33-theo` a
`1740807e`.*

**Quatre tests rouges sur tout le reacteur, tous dans la meme classe, et pas un ailleurs.** Le
depouillement des rapports Surefire des six modules ne rend qu'un seul fichier en echec :

| Test rouge | Classe |
|---|---|
| `touteChaineExposeeParLeCatalogueReelEstInventoriee` | `TexteAccentueExposeParLePortEntrantTest` |
| `chaqueChaineDeLInventaireEstEncoreExposeeParLeCatalogueReel` | idem |
| `lesNiveauxSansLettreAccentuableRestentVertsSansFigurerDansLInventaire` | idem |
| `unNiveauNeufHorsDeLInventaireEtHorsExemptionFaitEchouerLeGardeFou` | idem |

Le cinquieme test de la classe, `unTitreDeJeuNeufHorsDeLInventaireFaitEchouerLeGardeFou`, **reste
vert** : il ne soumet au juge qu'un seul jeu fabrique, pas le catalogue entier.

**Rien d'autre ne bouge.** La consigne de ce jeu n'existe qu'a deux endroits du depot : la donnee
servie et cette entree d'inventaire. Aucun test ne borne sa longueur, aucun ne verifie qu'elle nomme
une tache (voir section 3.3), aucun autre module ne la lit.

**Le geste est donc mecanique : une chaine dans `catalogue.yaml`, la meme chaine dans l'inventaire,
meme commit.** C'est mot pour mot ce que `marc-dev` et `sofia-sm` ont fait chacun pour leurs propres
ajouts, et le cout est le meme.

### 2.2 L'inventaire est-il une forme a garder ? Oui, et le motif n'est pas la commodite

C'est la deuxieme fois en deux sprints qu'il faut le mettre a jour a la main, et la question est
legitime. **Ma reponse est de le garder, et elle est mesurable plutot qu'esthetique.**

L'inventaire n'est pas une liste de fichiers ni de cles : c'est une **liste d'autorisation sur les
valeurs** qu'une donnee servie expose a une vue, et son perimetre, lui, **est derive par
reflexion** sur les composantes de `GameDefinition` et de son descripteur (`ADR-0015`, `ADR-0039`).
Le jour ou une vue rendra une chaine de plus, elle entre dans ce perimetre sans que personne ne
touche ce fichier. Ce qui est ecrit a la main, c'est **ce qui a ete arbitre** - et c'est precisement
ce qu'on veut voir bouger dans un diff.

Ce que la mise a jour a la main achete, et qui n'a pas d'autre acheteur aujourd'hui : **une chaine
lue par un enfant ne change pas en silence.** Les deux versants du garde-fou le tiennent ensemble -
une chaine servie hors inventaire est rouge, et une entree d'inventaire que le catalogue ne produit
plus est rouge aussi. Un inventaire plus large que ce qu'il garde n'est pas un inventaire ; c'est la
lecon d'`ADR-0039`, et elle a deja ete payee ici.

**Ce que je concede** : le cout est reel et il croit avec le catalogue (68 entrees aujourd'hui). Il
existe une forme moins couteuse - deriver l'inventaire du fichier servi - mais elle **supprime
exactement la propriete qu'on achete** : un inventaire calcule depuis la donnee qu'il juge est vert
par construction, quelle que soit la valeur, et c'est la famille de faux vert que ce depot combat
depuis `ADR-0013`. **Le geste manuel n'est pas le prix d'un defaut de conception, c'est le prix de
la garde.** Je le garde, et j'ecris ce qui me ferait changer d'avis : une seule chose, un mecanisme
qui rende visible dans un diff le changement d'une chaine servie **sans** demander de la recopier -
et je n'en connais pas dans ce depot.

---

## 3. `S33-CONS-4` - **non evaluable avant la decision de grain**, mais trois choses le sont sans elle

**Avis : non evaluable en l'etat.** La decision de grain appartient a `camille-po` et `nadia-prof`
(`ADR-0081`) ; je ne la tranche pas et je n'en prejuge pas. **Voici ce qui est evaluable quand
meme**, pour que la decision se prenne avec un cout devant les yeux plutot qu'apres.

### 3.1 Le cout technique de scinder un paquet servant plusieurs lignes en plusieurs entrees de catalogue

**Premier fait, et il est structurant : le decoupage par entree de catalogue n'existe pas
aujourd'hui.**

*Methode : lecture de `YamlContentPackLoader` (seule implementation de `ContentCatalogPort`,
`ADR-0007`) et de `catalogue.yaml` ; worktree `s33-theo` a `1740807e`.* Une entree de catalogue porte
exactement `id`, `titre`, `consigne`, `mecanique`, `paquet`, `nombreDeManches` et un
`consigneAdulte` facultatif. **Aucune de ces cles ne restreint les items servis.** Deux entrees
pointant le meme fichier serviraient les 92 memes items ; et cette voie est de toute facon fermee par
une regle executable - `AucuneEntreeDuCatalogueNEnDoubleUneAutreTest` refuse deux entrees qui jouent
**la meme mecanique sur le meme paquet** (`ADR-0045`). Le meme paquet sous deux mecaniques
differentes passe ; deux fois `bandit-manchot`, non.

**Consequence : scinder, c'est scinder le fichier de paquet.** Il n'y a pas d'option intermediaire
sans changement de schema, et un changement de schema n'est pas gratuit non plus : `ADR-0070` exige
qu'aucune cle d'un paquet servi ne soit ignoree de tout le depot, donc une cle neuve demande un
lecteur et sa preuve.

**Deuxieme fait : la population du probleme est de deux paquets, pas de soixante-huit.**

*Methode : chargement des 68 fichiers de `contenu/` et comptage de la cle `lignesDeReferentiel`.*
**68 entrees de catalogue, 68 fichiers de paquet servis, correspondance un pour un aujourd'hui.**
Deux fichiers servent plus d'une ligne : `cm1-histoire-geographie` (**11 lignes, 92 items**) et
`cm1-anglais` (**2 lignes, 25 items**). **Les 66 autres servent une ligne chacun.** Le grain que
`CONS-4` met en cause n'est donc pas une propriete generale de l'architecture : c'est **un fichier**,
et il est l'exception.

**Troisieme fait : le chiffre de la consigne, avec sa distribution.** *Methode : comptage des mots
separes par espace sur les 68 consignes de `catalogue.yaml`.* Mediane **10** mots, moyenne 10,5,
minimum 4, **maximum 31** - et ce maximum est celui du paquet a 11 lignes. Le deuxieme plus long fait
**20** mots. Neuf consignes depassent 15 mots. **L'outlier est a onze mots du suivant, et c'est le
seul paquet a onze lignes** : la correlation que `nadia-prof` a lue en jouant se lit aussi dans la
donnee.

**Le cout d'un decoupage en N entrees, poste par poste**, mesure sur les garde-fous existants et non
estime :

| Poste | Ce qui bouge | Rouge d'abord ? |
|---|---|---|
| Donnee | N-1 fichiers de paquet neufs, 92 items redistribues, **aucun texte d'item modifie** | non |
| `ADR-0081` | un paquet qui ne sert **qu'une** ligne interdit la cle `ligneDeReferentiel` sur ses items : les 92 cles partent, ou le decoupage s'arrete a des groupes de deux lignes ou plus | **oui**, `ChaquePaquetServiDeclareLesLignesDeReferentielQuIlSertTest` |
| `ADR-0023` | chaque entree offre **strictement plus** d'items que de manches. `HG-CM1-08` et `HG-CM1-12` portent **1 item chacune** aujourd'hui : elles ne peuvent pas devenir une entree avant d'etre remplies. Une ligne a 10 items plafonne sa partie a **9 manches**, contre 10 aujourd'hui | **oui**, `ChaqueJeuDuCatalogueOffrePlusDItemsQueDeManchesTest` |
| Lot de matiere | `isEqualTo(1)` sur le nombre de fichiers du lot *"Histoire-geographie / CM1"* devient N ; la somme des items reste 92 | **oui** |
| Inventaire du port entrant | environ **4 chaines par paquet neuf** (identifiant, titre, consigne, competence du descripteur ; matiere et domaine restent partages) - soit une quarantaine d'entrees pour un decoupage en onze | **oui** |
| Declaration hors domaine de rang | l'exemption de `LeRangDeLaBonneReponse...` nomme `cm1-histoire-geographie` ; une declaration qui survit a son paquet est rouge, donc elle se decline en N | **oui** |
| Test de paquet | `PaquetCm1HistoireGeographieTest`, 591 lignes et 24 tests, se decoupe ou se parametre | **oui** |
| Titres | `ADR-0078` interdit qu'un titre porte son niveau ; N titres neufs a ecrire, hors de mon ressort | **oui** si viole |
| Interface | 68 -> 68+N-1 tuiles sur l'accueil. **Aucun garde-fou ne borne ce nombre** ; consequence de conception, a `ines-design` | non |
| Conformite | N-1 fiches `FICHE-<jeu>.md` neuves, chacune due d'un Done pedagogique. **69 fiches aujourd'hui, aucune regle automatique ne les compte** | non |

**Ce que je retiens et que je ne chiffre pas plus loin** : le cout technique est **entierement en
tests et en donnee, sans une ligne de code de production**, et il est mecanique - chaque poste
ci-dessus est rouge avant d'etre corrige, aucun n'est silencieux. Le cout **dominant** n'est pas
technique : c'est **N-1 portes pedagogiques neuves** et une partie qui passe de dix a neuf manches
sur les lignes a dix items. Ces deux-la ne sont pas de mon ressort et je ne les mets pas dans une
balance a leur place.

### 3.2 Ce que le schema et le chargeur unique permettent en l'etat : le decoupage, oui ; le filtre, non

`ADR-0007` tient : un seul chargeur, une seule enveloppe de schema, un registre de fabriques. **Il
n'oppose aucun obstacle a N paquets de plus** - un paquet de plus est un fichier de plus, sans une
ligne de code. C'est exactement ce que ce depot a achete en refusant les neuf familles de chargeurs
de la version precedente.

**Ce qu'il ne permet pas en l'etat : servir une partie d'un fichier.** Aucune cle, ni de catalogue ni
de paquet, ne le dit, et `ADR-0045` ferme la voie du doublon d'entree. Si la decision de grain
demandait cette forme-la plutot que le decoupage du fichier, **ce serait un `ADR` neuf de ma part**,
avec sa regle, et je le dis d'avance pour que la decision ne se prenne pas en croyant que c'est
gratuit.

### 3.3 `CONS-1` : ce qu'un automate peut tenir, et ce qu'il ne tiendra jamais

**A ne pas se raconter** : le garde-fou de `CONS-1` sur ce paquet
(`cons1LaConsigneNommeLaTacheDeNommerUnPeupleOuUnMouvementDePopulation`) verifie que le texte du
catalogue **contient une clause**. Il est vert des que la phrase est presente. **Il ne mesure aucune
couverture d'items**, et il ne peut pas : "cette consigne nomme la tache de cet item" est une
lecture de sens. Le compte de `nadia-prof` - 14 items neufs dont la tache n'est nommee par aucune des
cinq clauses, le compte des items non nommes plus grand apres le lot qu'avant - **n'est pas
reproductible par un automate, et je ne pretends pas le rendre executable.**

Ce qui est executable, et qui n'existe pas aujourd'hui : **aucune regle ne borne la charge de
lecture d'une consigne, a aucun niveau.** `ADR-0089` plafonne les **enonces de CP** a quinze mots et
deux phrases ; les 68 consignes, elles, ne sont bornees par rien. Le mecanisme, lui, est deja ecrit
et balaye deja le contenu servi : **un plafond sur la consigne est une extension de population du
balayeur existant, pas un garde-fou neuf.** Le plafond, en revanche, est une valeur pedagogique -
il appartient a `nadia-prof`, et j'ai mis la distribution de la section 3.1 a sa disposition pour
qu'elle le fixe sur des donnees. Je n'ecris pas cette regle avant qu'elle donne le chiffre : une
regle dont j'inventerais le seuil serait ma decision deguisee en mesure.

**Et le lien avec le grain, qui est la vraie raison pour laquelle cette section existe** : un
plafond de mots sur une consigne qui doit nommer onze taches est **insatisfiable**. Aucune des deux
regles ne peut etre ecrite independamment de la decision de grain. C'est pour cela que
`nadia-prof` a raison de router la cause et non la phrase, et c'est pour cela que je ne propose
aucune regle ici.

---

## 4. `S33-SCO-1` - **instruit, non remede.** La cause est nommee, le rayon chiffre, et le remede est bloque par une instruction qui n'est pas de moi

**Avis : instruction rendue, remede non choisi - et il ne peut pas l'etre aujourd'hui.**

`DEC-0061`, section 5, ordonne deux instructions et l'ordre est explicite : **1 avant 2.** Le point 1
est *"le resultat pedagogique attendu : ce qu'un enfant doit pouvoir faire d'une manche quand le
nombre de propositions rend l'elimination possible, et ce que trois essais doit vouloir dire dans ce
cas"*, du a `nadia-prof` **et a elle seule**. Le point 2, la forme de la correction, est de moi.

**Le point 1 n'a pas ete rendu.** Ce que `nadia-prof` a produit depuis le 2026-08-31 - et ce qu'elle
a produit est solide - ce sont **trois mesures du fait** (2026-09-08 sur l'anglais, 2026-09-09 huit
parties par l'instance A, 2026-09-09 cinq parties), le routage du remede vers moi, et la remise a 0,0
du cout de son audit. Aucun de ces gestes n'est le resultat attendu. **Je ne peux pas choisir la
forme sans le choisir a sa place**, et le choix, on va le voir, est un choix pedagogique deguise en
choix technique. Je m'arrete donc a l'instruction, et elle est complete.

### 4.1 La cause, dans le code, et elle est triple

*Methode : lecture des trois sessions de `domain/src/main/java/com/recreo/domain/mechanics/` sur
l'arbre `s33-theo` a `1740807e`. Aucune mesure de build dans ce paragraphe.*

`BanditManchotSession.jouer` evalue `estLaBonneReponse` **avant** d'incrementer `essaisSurLaManche`,
puis appelle `resoudre(true)` ; `resoudre` fait `score.reussites() + 1` **sans jamais lire
`essaisSurLaManche`**. Une manche resolue au troisieme appui vaut donc exactement une manche resolue
au premier.

**Le meme geste existe trois fois**, a l'identique : `BanditManchotSession.resoudre`,
`SaisieSession.resoudre`, `DesignationSession.resoudre`. Les trois construisent
`new Score(score.reussites() + (juste ? 1 : 0), score.tentatives() + 1)`. **Et `Score` est un record
de deux entiers** (`reussites`, `tentatives`) : il ne transporte aucune information sur le cout des
essais. Aucun remede ne peut donc s'exprimer sans toucher soit l'increment, soit le record.

### 4.2 Pourquoi le score reste parfait, et le point qui n'est ecrit nulle part

Le fait est connu. Ce qui ne l'est pas, et qui est le coeur de mon instruction : **l'elimination
n'est pas une propriete accidentelle du contenu, elle est garantie par deux regles executables de ce
depot, chacune juste separement.**

- `ADR-0063` / `AuPlusTroisPropositionsParItemTest` : **aucun item ne presente plus de trois
  propositions**, hors une dette au cliquet exact de 33 items sur deux paquets, qui ne peut que
  diminuer. Un paquet neuf a quatre propositions est **rouge**.
- `ESSAIS_AVANT_REVELATION = 3` dans les trois mecaniques : **trois essais par manche**.

**Trois propositions au plus, trois essais au moins : sur tout item conforme, un enfant qui presse
tout atteint la bonne reponse dans son allocation, et `resoudre(true)` la lui credite.** La
consequence pratique est que **"augmenter le nombre de propositions" n'est pas un remede
disponible** - c'est une regle acceptee qui l'interdit, et son cliquet ne remonte pas. Des trois
formes que `DEC-0061` disait concevables sans en designer aucune, celle-la est fermee par une
decision anterieure. Je le note parce que je ne l'ai vu ecrit nulle part.

### 4.3 Le rayon, chiffre aujourd'hui - la mesure de s24 a vieilli d'un facteur deux

*Methode : chargement des 68 fichiers de `contenu/` servis, comptage `1 + len(distracteurs)` par
item enumere, plus lecture des parametres du seul paquet engendre. Worktree `s33-theo` a
`1740807e`.*

| | Compte |
|---|---|
| Items enumeres de type `question` | **1 227** |
| dont **2** propositions | 116 |
| dont **3** propositions | 1 078 |
| dont **4** propositions | 33 (la dette exacte d'`ADR-0063`) |
| **Items ou l'elimination aboutit dans l'allocation d'essais** (au plus 3 propositions) | **1 194 - 97,3 %** |
| Paquets de type `question` dont **tous** les items sont dans ce cas | **63 sur 65** |
| Paquet engendre (`additions-bornees`), parametre `nombreDePropositions: 3` | 1, entierement dans ce cas |
| Entrees de catalogue jouees par `bandit-manchot` | **66** ; par `saisie`, **2** |

**Ce que cela donne par entree de catalogue** : **64 des 66 entrees de `bandit-manchot` rendent un
score parfait garanti** a un enfant qui presse tout - 63 paquets enumeres plus le paquet engendre.
Une entree est melangee (`cm1-accords-groupe-nominal` : 8 items a deux propositions, 8 a trois, 8 a
quatre), une seule est immunisee (`cm1-conjugaison-present`, ses 25 items a quatre propositions).
**Mon compte, fait sur les fichiers de paquet, retombe sur les 64 sur 66 que `nadia-prof` avait
comptes sur les entrees de catalogue : deux chemins, un chiffre.**

**La mesure de `DEC-0061` - 582 items sur 615, 94,6 pour cent - etait juste au 2026-08-30 et ne
decrit plus le depot** : le denominateur a double (615 -> 1 227) et la proportion a monte (94,6 ->
97,3 pour cent). Elle n'a pas ete faussee, elle a vieilli ; je la remplace en nommant la mienne.

**Ce qui n'est pas dans cette population** : les **2 entrees de `saisie`.** L'elimination y est
impossible, l'espace des reponses n'etant pas enumere. Le defaut de comptage, lui, y est identique -
`SaisieSession.resoudre` credite une reussite apres deux echecs comme apres aucun. **Deux choses
distinctes, que je nomme separement pour qu'un remede ne soit pas cadre sur la mauvaise** : le
comptage qui ne distingue pas celui qui savait (trois mecaniques, tout le catalogue) et l'elimination
garantie (deux regles, 64 entrees sur 68).

### 4.4 Les deux familles de remede, ce que chacune change pour l'enfant, et pourquoi le choix n'est pas technique

Je les decris, je ne choisis pas. Elles ne se distinguent pas par leur cout - il est faible dans les
deux cas - mais par ce qu'elles retirent a l'enfant.

**Famille A - crediter moins.** `reussites` cesse de compter une manche resolue apres un ou plusieurs
echecs. `essaisSurLaManche` est deja dans la session : la modification est locale a `resoudre` dans
les trois classes, `Score` peut rester a deux champs. **Pour l'enfant : il garde ses trois essais et
son droit a se tromper, et le chiffre final cesse de dire qu'il savait.** Satisfait `SCO-1`, `SCO-2`,
`SCO-3` tels qu'ils sont ecrits.

**Famille B - permettre moins.** L'allocation d'essais descend sous le nombre de propositions (une
valeur fixe plus basse, ou une allocation derivee du nombre de propositions). L'elimination devient
impossible parce que la revelation arrive avant le dernier choix. **Pour l'enfant : il perd un essai,
et une manche se termine par une revelation la ou il aurait trouve.** Satisfait les memes criteres,
mais **touche `M5`, `M9` et `M11`**, c'est-a-dire l'arbitrage de mecanique de `nadia-prof` de s10 -
qui n'est pas rouvrable par moi.

**Une troisieme voie qui ne satisfait pas la story** : garder le score et afficher a cote le nombre
d'essais depenses. Cout minimal, aucune mecanique touchee - mais `SCO-3` exige que **le score final
ne soit pas parfait**, et cette voie le laisse parfait. Je la nomme pour qu'elle ne revienne pas
comme une economie : elle ne paie pas la story ecrite.

**Le choix entre A et B est un choix sur ce qu'un essai apprend a un enfant.** C'est mot pour mot
l'instruction 1 de `DEC-0061`, et c'est pourquoi je m'arrete ici. **Ce dont j'ai besoin pour ecrire
l'`ADR` est une seule phrase de `nadia-prof`** : quand l'elimination est possible, l'enfant garde-t-il
ses trois essais avec un score qui ne les ignore plus (A), ou en a-t-il moins pour que l'elimination
n'aboutisse pas (B) ? **Je ne demande pas un remede, je demande le resultat attendu.** Des qu'il est
ecrit, l'`ADR` et sa regle sortent dans le meme geste - la mecanique est cernee, le rayon est de
trois methodes.

### 4.5 La regle qui mordra, sa population, et pourquoi aucune regle existante n'attrape ce defaut

**Une regle non executable n'est pas une regle. Voici la mienne, avec sa population et son etat
attendu.**

Elle ne peut pas etre une regle ArchUnit : elle ne porte sur aucune dependance ni aucun paquetage,
mais sur un calcul mene sur le catalogue servi. **Le mecanisme automatique qui la garde est un test
du domaine sur la population derivee du catalogue**, sur le patron exact de
`LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` - lequel est aussi le precedent qui
explique le trou.

> **Enonce.** Sur toute entree du catalogue servi dont l'espace des reponses est enumere, une
> strategie aveugle - qui ne lit pas l'enonce et presse chaque proposition non encore essayee -
> n'obtient pas de reussite comptee, donc pas de score parfait sur une partie complete.
>
> **Population, derivee et non nommee** : les entrees de `catalogue.yaml` dont la mecanique presente
> un choix parmi des reponses enumerees - **66 aujourd'hui**, plus toute entree future, sans que la
> regle soit reecrite. Les entrees de `saisie` en sortent **par une propriete calculee** - l'item ne
> porte aucune proposition - et non par une liste de noms ; le garde-fou le declare, comme
> `ADR-0051` l'exige, plutot que de les ignorer.
>
> **Etat : rouge aujourd'hui, sur 64 des 66 entrees.** C'est la propriete qui rend cette regle non
> decorative : elle refuse l'etat actuel du produit. Elle entre donc **avec** le remede, jamais
> avant, et son passage au vert est la preuve du remede - pas une phrase de rapport.

**Pourquoi rien ne l'attrapait depuis s04, et c'est ma propre lecon retournee contre ce depot.** Une
regle existe pourtant contre une strategie aveugle :
`LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` refuse qu'une strategie **a rang constant**
gagne la majorite des manches. Son predicat enumere **une** strategie aveugle - toujours la plus
petite, l'intermediaire, la plus grande - et ne voit pas celle qui **presse tout**. *Un perimetre
enumere par cas echoue par inclusion autant que par omission* : le depot a une regle contre le fait
de gagner sans lire, elle est verte, et la strategie qui gagne **cent pour cent** des manches est en
dehors de son predicat. Voila la seule reponse honnete a *"ce defaut aurait pu etre attrape par un
test des s04"*.

Les quatre criteres de non-regression de la story - `SCO-4` a `SCO-7` - sont **verts aujourd'hui et
executables sans aucune decision**. Ils bornent le remede plutot que de le produire. **Je ne les
ecris pas dans ce geste** : ecrits maintenant, ils epingleraient le comportement actuel, dont une
partie est exactement ce que le remede doit changer, et `SCO-7` (*"une reponse juste ne coute jamais
un essai"*) est vrai dans la famille A comme dans la famille B mais s'ecrit differemment selon
l'allocation retenue. Ils appartiennent au commit du remede, avec l'`ADR`. **Je le dis comme une
dette datee, pas comme un oubli** : quatre proprietes verifiables du domaine ne sont couvertes par
aucun test a ce jour, et elles le seront dans le meme commit que `D7`.

---

## 5. Ce que j'ai change dans ce geste, et la preuve qu'il mord

Un seul fichier, **un test, aucune ligne de code de production, aucun contenu** :
`adapters/adapter-content/src/test/java/com/recreo/adapter/content/PaquetCm1HistoireGeographieTest.java`,
+39 lignes, 0 suppression.

**Ce qui manquait.** Les six partages de taches de ce fichier (`FER-3` sur `HG-CM1-01`, `-02`, `-03`,
`-04`, `-11`, `-13`) calculaient une classe par un classificateur et **l'autre par soustraction**,
puis ne verifiaient qu'un plancher (`min >= 3`). Deux modes de defaillance restaient muets : un item
classe du mauvais cote reste au-dessus du plancher, et un classificateur qui cesse de reconnaitre un
item verse cet item dans l'autre tache sans que rien ne bouge.

**Ce que j'ai ajoute.** Les deux effectifs de chaque partage sont **epingles a leur valeur mesuree** -
`HG-CM1-01` 5/5, `-02` 5/5, `-03` 3/7, `-04` 3/7, `-11` 3/7, `-13` 4/6 - par une methode partagee qui
nomme la ligne, la tache et l'effectif attendu. *Methode des six valeurs : comptage des items de
chaque ligne du paquet servi en rejouant exactement le classificateur de chaque test ; verifie
ensuite par l'execution, 24 tests verts.*

**La preuve de morsure, sur du materiau reel et non sur une fixture de ma main.** Deux mutations du
paquet servi, chacune lancee puis remise en etat, `-pl adapters/adapter-content -am
-Dtest=PaquetCm1HistoireGeographieTest` :

| Mutation | Avant mon diff | Apres mon diff |
|---|---|---|
| La bonne reponse de l'item 85 devient une valeur hors de l'enumeration - **le geste de `S33-HG-04`** | rouge (le plancher tombe a 2) | rouge, et le message nomme la tache et l'effectif |
| Un enonce d'`HG-CM1-01` est reformule sans changer sa tache (*"vient juste apres"* -> *"suit juste"*) - **le mode que `yanis-qa` a signale** | **vert. 24 tests sur 24, alors que le partage est passe de 5/5 a 4/6 en silence** | rouge : `HG-CM1-01 : la tache "ordre" porte exactement 5 items, expected: 5L but was: 4L` |

La seconde ligne est mesuree, pas raisonnee : j'ai garde la mutation de l'enonce et remis le fichier
de test dans son etat de `1740807e` - **24 tests verts**, la classe entiere passe sur un partage
deplace. C'est le faux vert que `yanis-qa` avait route ; il est ferme.

**Ce que cette regle interdit reellement a quelqu'un qui essaierait de mal faire** : ajouter un item
sur une de ces six lignes sans dire quelle tache il sert, et reformuler un enonce en deplacant le
partage des taches. Les deux etaient possibles hier sur le paquet le plus travaille du depot.

**Ce qu'elle n'est pas.** Elle ne rend pas la tache derivable - **la tache d'un item reste un champ
d'aucun schema**, et je n'ouvre pas ce chantier ici : une cle `tache` toucherait `ADR-0007`,
`ADR-0070` et 68 paquets pour une population de six partages, ce qui est une abstraction taillee sur
un usage que j'imagine. **Ce qui me ferait changer d'avis** : que la meme mise a jour a la main
revienne sur une troisieme matiere, ou qu'un partage se deplace malgre l'epinglage. Alors la cle
declarative devient un usage constate et j'ecris l'`ADR`.

**Aucun `ADR` neuf n'est requis pour ce diff** : il applique `ADR-0013` (un garde-fou se prouve
mordant) et `ADR-0089` decision E (la tache d'un item ne se lit pas dans une phrase de son enonce) a
un fichier qui les respectait a moitie. Le nombre de regles du depot est inchange ; c'est la morsure
de six d'entre elles qui change.

**Risque de fusion, nomme parce qu'il est reel** : mes lignes ajoutees dans la methode `FER-3` de
`HG-CM1-04` sont a huit lignes de l'enumeration que `marc-dev` doit mettre a jour pour `S33-HG-04`,
dans le meme fichier. **Recommandation a `sofia-sm` : fusionner `s33-theo` avant `s33-marc`** - mon
diff est un test seul, independant de tout contenu - et laisser `marc-dev` reprendre son travail
par-dessus. Dans l'autre ordre, il devra reporter un effectif epingle a la main.

---

## 6. Deux corrections a des avis deja ecrits, dont un des miens

Je les ecris parce qu'un chiffre faux dans un fichier commite se propage.

**6.1 `S33-HG-08-12` n'est pas *"aucun code hors contenu"*.** Le planning, section 4, la juge
faisable en l'etat au motif qu'*"aucune assertion de test ne code en dur le texte touche"*. C'est
vrai du **texte**, et faux du **compte** : *methode - comptage des items par ligne sur le paquet
servi* - `HG-CM1-08` et `HG-CM1-12` portent **1 item chacune** aujourd'hui, sur 92. Les remplir fait
bouger deux egalites strictes, dans deux modules : `NOMBRE_D_ITEMS_ATTENDU` de
`PaquetCm1HistoireGeographieTest` et l'`isEqualTo(92)` du lot *"Histoire-geographie / CM1"* de
`UneConditionDeLotSeCompteSurLeLotDeMatiereEtNonSurUnFichierTest`. **Cela ne change pas l'avis - la
story reste faisable en l'etat, sans `ADR`** - mais `marc-dev` doit savoir que ces deux tests
rougiront et que la mise a jour va dans son commit.

**6.2 Une exemption de garde-fou dont le motif ne decrit plus son objet, et c'est un des miens.** La
declaration hors domaine de `cm1-histoire-geographie` dans
`LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` porte le motif *"20 items sur 20 [...]
aucune des 60 propositions n'est numerique"*. *Methode : comptage sur le paquet servi.* Le paquet
porte **92 items et 276 propositions**. **Le garde-fou n'est pas faux** - il recalcule la propriete
sur le paquet reel, et `aucunPaquetNEstEcarteEnSilenceDuCalculDeRang` la verifie a chaque execution :
c'est le **motif** qui a vieilli de s27 a s32 sans que personne le reprenne, et il vieillira une
troisieme fois avec `S33-HG-08-12` (+18 items). Ce n'est pas un faux vert, c'est un chiffre sans
methode dans un fichier commite - la famille que je sanctionne partout ailleurs. **Je ne le corrige
pas dans ce geste** : il n'est pas dans le perimetre de cet avis et le corriger seul le referait
vieillir au prochain lot. **Je le route au developpeur qui touchera ce paquet a s33**, avec la
formule qui le rend recalculable : le motif nomme le compte **et sa methode**, ou il ne nomme pas de
compte du tout.

---

## 7. Ce que je ne tranche pas, et a qui je le rends

| Question | Categorie | A qui |
|---|---|---|
| Le terme qui remplace *"les Sarrasins"* | Contenu / pedagogie | developpeur, puis verdict de `nadia-prof` |
| Un quatrieme item sur `HG-CM1-04` | Pedagogie | `nadia-prof` (elle a deja ouvert la porte) |
| Le grain de `bandit-manchot-histoire-geographie-cm1` | Produit / pedagogie | `camille-po` et `nadia-prof` (`ADR-0081`) |
| Le plafond de mots d'une consigne | Pedagogie | `nadia-prof` ; le mecanisme est a moi et il existe deja |
| **Le resultat attendu de `SCO-1` : trois essais, ou un score qui les compte** | Pedagogie | **`nadia-prof`, instruction 1 de `DEC-0061`, bloquante pour l'`ADR`** |
| Le prix d'audit de `N-1` fiches de conformite en cas de decoupage | Capacite pedagogique | `nadia-prof` |
| L'ordre de fusion des branches de s33 | Sequencement | `sofia-sm` (recommandation en section 5) |

---

## 8. La porte technique, les quatre jobs de `.github/workflows/ci.yml` un par un

Lances sur mon diff, dans le worktree `s33-theo`, avec les commandes des jobs. **Je nomme les quatre
plutot que d'en deduire trois d'un `mvn verify`** - c'est ce que l'amendement du 2026-09-08 de
`CLAUDE.md` exige, et le job `chemins` est reste rouge un sprint entier faute de cette discipline.

| Job | Ce que j'ai lance | Resultat |
|---|---|---|
| **`prefixes`** | `bash .github/scripts/verifier-prefixes-test.sh`, `bash .github/scripts/commit-atteignable-test.sh`, puis `git log --no-merges --format=%s -1 \| bash .github/scripts/verifier-prefixes.sh` sur mon commit | **vert** |
| **`chemins`** | `bash .github/scripts/verifier-chemins-test.sh`, puis `git ls-files -- . ':!.github/scripts/verifier-chemins-test.sh' \| bash .github/scripts/verifier-chemins.sh` | **vert** - *"Aucun chemin de poste de travail ni nom de compte dans les fichiers verifies"* |
| **`outils`** | la boucle du job sur les trois motifs (`outils/*-test.sh`, `squad/outils/*/*-test.sh`, `outils/*-test.py`) | **vert - 9 juges purs executes**, aucun motif vide |
| **`build`** | `bash outils/mvn-jdk21.sh -B clean verify` sur la racine, puis `-pl bootstrap -am package` | **vert - 1 439 tests, 0 echec**, fat jar reempaquete |

**Le compte de 1 439 porte sa methode** : somme des totaux que Maven annonce par module sur un
reacteur complet a six modules - 105 (`domain`) + 13 (`application`) + 388 (`adapter-web`) + 689
(`adapter-content`) + 238 (`bootstrap`, tests unitaires) + 6 (`bootstrap`, tests d'integration).
**Identique au 1 439 mesure par `sofia-sm` a l'ouverture de s33**, et c'est attendu : mon diff
n'ajoute **aucune methode `@Test`**, il ajoute des assertions dans six tests existants. Le compte
direct des fichiers `surefire-reports` sur le disque en rend moins, pour le motif deja etabli au
planning - quatre classes s'executent deux fois dans la meme session et leur rapport est ecrase par
la seconde execution.

**Aucun job n'est rouge.**
