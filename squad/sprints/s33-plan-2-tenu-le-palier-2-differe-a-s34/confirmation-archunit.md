# Confirmation de la porte technique - s33 (arbre fusionne, huit stories payees sur onze)

- **Auteur** : `theo-archi`. **Date** : 2026-09-10.
- **Arbre mesure** : `round-two` a `b45e5927`, la tete laissee par la derniere fusion de s33
  (section 14 du planning). Checkout principal, arbre de travail **propre** au lancement de la porte
  technique (`git status --short` vide, 09:42).
- **JDK 21.0.10**, version que la **machine virtuelle qui execute les tests rapporte d'elle-meme**
  (`ADR-0071`) et non celle qu'une entree de toolchain declare : `java.version=21.0.10`,
  `java.runtime.version=21.0.10+8-LTS-217`, `Java HotSpot(TM) 64-Bit Server VM`, lus dans le bloc de
  proprietes des rapports Surefire produits par ce build. Enveloppe : `bash outils/mvn-jdk21.sh`.
- **Ce n'est pas un Done.** La porte pedagogique appartient a `nadia-prof` seule, et aucune des trois
  instances n'a audite ce sprint.

**Une ecriture concurrente est arrivee dans ce meme checkout pendant la seance, et je la nomme parce
qu'un chiffre porte sa methode, y compris l'etat de l'arbre au moment ou il a ete pris.**
`camille-po` a ecrit `docs/backlog/backlog.md` (+164 lignes, non commite) a 09:51 et
`squad/sprints/s33-plan-2-tenu-le-palier-2-differe-a-s34/arbitrage-produit-camille.md` (non suivi) a
09:50. Les deux sont **posterieurs** au `mvn -B clean verify` de la section 1, qui a fini a 09:46 sur
un arbre propre : **aucun chiffre de la section 1 ne porte sur ces deux fichiers**. La reprise des
regles de la section 2 a fini a 09:54, donc **avec** ces deux fichiers presents, et les trois
garde-fous du reacteur qui balayent les fichiers redactionnels
(`ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest`,
`ToutesLesReferencesDeDecisionResolventTest`, `ConcordanceDesTagsDAgentTest`) y sont verts. Je n'ai
touche ni l'un ni l'autre.

---

## 1. Les quatre jobs de `.github/workflows/ci.yml`, un par un

`mvn verify` n'est que le contenu du job `build` (`CLAUDE.md`, amendement du 2026-09-08). Les quatre
sont lances, avec les commandes du workflow.

| Job | Commande lancee | Verdict |
|---|---|---|
| `build` - Build et tests (Java 21) | `bash outils/mvn-jdk21.sh -B clean verify` sur la racine, reacteur complet a six modules | **vert.** `BUILD SUCCESS`, six modules `SUCCESS`, 04:01 min, fini a 09:46 |
| `chemins` - Chemins de poste de travail | `bash .github/scripts/verifier-chemins-test.sh`, puis `git ls-files -- . ':!.github/scripts/verifier-chemins-test.sh' \| bash .github/scripts/verifier-chemins.sh` | **vert.** Tous les cas du garde-fou passent ; *"Aucun chemin de poste de travail ni nom de compte dans les fichiers verifies"* |
| `outils` - Juges purs des outils de poste | la boucle du job sur les trois motifs (`outils/*-test.sh`, `squad/outils/*/*-test.sh`, `outils/*-test.py`) | **vert. 9 juges purs executes**, aucun en echec, aucun motif vide |
| `prefixes` - Prefixes de commit par agent | `bash .github/scripts/verifier-prefixes-test.sh`, `bash .github/scripts/commit-atteignable-test.sh`, puis `git log --no-merges --format=%s 1740807e..HEAD \| bash .github/scripts/verifier-prefixes.sh` | **vert.** Les deux garde-fous passent ; *"Tous les commits portent un prefixe d'agent connu"* |

**La plage lancee au job `prefixes` est `1740807e..HEAD`**, c'est-a-dire le sprint entier, et non le
`-1` du repli du workflow : les cinq commits non-merge de s33 sont verifies plutot que supposes.

### Le compte de tests, avec sa methode dans le meme paragraphe

**1 443 tests, 0 echec.** *Methode : somme des totaux que Maven annonce par module a la fin de chaque
module, sur un reacteur complet a six modules lance depuis la racine - 105 (`recreo-domain`) + 13
(`recreo-application`) + 388 (`recreo-adapter-web`) + 693 (`recreo-adapter-content`) + 238
(`recreo-bootstrap`, surefire) + 6 (`recreo-bootstrap`, failsafe).* Le chiffre se raccorde
exactement aux 1 443 que `sofia-sm` annonce aux sections 13 et 14 du planning, par la meme methode.

**Le comptage direct des fichiers `TEST-*.xml` sur le disque en rend moins, et ce n'est pas un
desaccord** : quatre classes s'executent deux fois dans la meme session, sous deux moteurs JUnit
distincts, et le rapport de la premiere execution est ecrase par la seconde -
`AbstractionsCiteesTest`, `ChargementDuContenuTest`, `LesReglesMordentTest` (`bootstrap`) et
`AssertionsWebTest` (`adapter-web`), ce qui est verifiable ligne a ligne dans la sortie console de ce
build. Les autres worktrees de la squad vivent tous **hors** de l'arbre (repertoires freres) et aucun
n'est balaye par cette mesure, qui ne lit que la sortie du reacteur.

---

## 2. Les regles ArchUnit, lancees et comptees, avec leur perimetre et leurs exclusions

**Commande, perimetre nomme, `-am` porte** : `bash outils/mvn-jdk21.sh -B -pl bootstrap -am
-Dtest='com.recreo.archi.**' -Dsurefire.failIfNoSpecifiedTests=false test`. Perimetre : le paquetage
`com.recreo.archi` du seul module `recreo-bootstrap`, ses modules amont construits depuis l'arbre du
worktree et non resolus depuis un depot local. **178 tests, 0 echec, `BUILD SUCCESS`**, fini a 09:54.
Les regles qui vivent hors de ce paquetage (`AssertionsWebTest` dans `recreo-adapter-web`,
`ToutesLesMecaniquesSontTesteesTest` dans `recreo-domain`,
`AucunContreExempleNEstUnBeanDeLApplicationTest` dans `recreo-bootstrap`) sont hors de cette
commande et vertes dans le `verify` complet de la section 1.

Les dix classes que le moteur ArchUnit rapporte, telles qu'il les rapporte :

| Classe | Regles executees |
|---|---|
| `PureteDesCouchesTest` | 2 - `leDomaineNeDependQueDuJdk`, `lApplicationNeDependQueDuJdkEtDuDomaine` |
| `ArchitectureEnCouchesTest` | 2 - `lesDependancesVontVersLInterieur`, `lesAdaptateursNeSeConnaissentPas` |
| `DecouplageMecaniqueContenuTest` | 12 |
| `GestionDesErreursTest` | 4 |
| `AbstractionsCiteesTest` | 4 |
| `DifficulteTest` | 3 |
| `AccesALEtatDeSessionTest` | 2 |
| `ChargementDuContenuTest` | 1 |
| `LeChoixDesJeuxResteDerriereLePortTest` | 1 |
| `LesReglesMordentTest` | 1 - `aucunContreExempleEnProduction` |

**Le compte de regles, avec sa methode et ses exclusions - c'est la ligne ou ce depot s'est deja
trompe de quatre.** *Methode : champs annotes `@ArchTest` et de type `ArchRule`, comptes par le
moteur ArchUnit lui-meme dans son propre rapport par classe, sur le reacteur complet ; aucune liste
ecrite a la main, aucun `grep`.* **32 dans `recreo-bootstrap`** (le tableau ci-dessus) **plus 1 dans
`recreo-adapter-web`** (`aucuneAssertionParExpressionReguliere`) = **33 regles ArchUnit**. Une
trente-quatrieme instance d'`ArchRule` existe, `ScanDeComposantsTest`, verifiee a la main dans un
`@Test` plutot que par le moteur : **34 objets `ArchRule` au total, 33 executes comme regles**.

**Exclusions nommees, parce qu'elles expliquent tous les ecarts possibles avec un autre compte** :
les seize autres classes de `com.recreo.archi` n'utilisent pas ArchUnit - elles balayent du YAML
servi, du Markdown commite ou des feuilles de style, et ce sont des garde-fous, pas des regles
ArchUnit ; les 34 `@Test` de `LesReglesMordentTest` sont des **morsures**, pas des regles ; les
quatre classes de la section 1 apparaissent deux fois dans la liste des classes executees, sous deux
moteurs.

---

## 3. Le diff de s33 contre la Clean Architecture - verifie, pas suppose

*Methode : `git diff --name-only 1740807e..HEAD`, puis recherche des lignes d'import ajoutees sur
tous les fichiers `.java` du diff.*

| Perimetre du diff | Fichiers |
|---|---|
| `src/main/java`, tous modules confondus | **0** |
| Lignes `+import` ajoutees, tous fichiers `.java` du diff | **0** |
| Contenu servi (`catalogue.yaml`, `contenu/*.yaml`) | 8 |
| Tests | 4 |
| `squad/` | 2 |

**Aucune ligne de code de production n'a bouge en s33, et aucune dependance neuve n'a ete
introduite - pas meme dans les tests.** Il n'y avait donc rien a violer par ajout de dependance ;
c'est une constatation sur le diff, pas une inference sur les couches. La verification par les
regles elles-memes est en section 2 : les 16 regles qui gouvernent les couches et le decouplage
mecanique/contenu (`PureteDesCouchesTest` 2, `ArchitectureEnCouchesTest` 2,
`DecouplageMecaniqueContenuTest` 12) s'executent vertes sur l'arbre fusionne.

---

## 4. Les deux regles que j'ai ecrites a s33 mordent - mesure sur le contenu servi, pas sur une fixture

Deux mutations du **paquet reel** `contenu/cm1-histoire-geographie.yaml`, chacune appliquee, mesuree,
puis remise en etat par `git checkout --`. Commande a chaque fois : `bash outils/mvn-jdk21.sh -B -pl
adapters/adapter-content -am -Dtest=PaquetCm1HistoireGeographieTest
-Dsurefire.failIfNoSpecifiedTests=false test`. Perimetre : `recreo-adapter-content` et ses modules
amont, `-am` porte. Arbre de travail **rendu propre** apres coup, verifie.

### 4.1 `S33-HG-04` : l'enumeration ecrite a la main refuse la regression exacte que `HG-R12` paie

Mutation : la bonne reponse de l'item 85 repasse de `"d'Espagne"` a `"les Sarrasins"` - le retour en
arriere precis que la story vient d'interdire.

```
[ERROR] PaquetCm1HistoireGeographieTest.fer3AuMoinsDeuxTachesSurHgCm104LaMoinsServieAuMoinsTroisItems
org.opentest4j.AssertionFailedError:
[HG-CM1-04 : la tache "nommer le mouvement" porte exactement 3 items]
expected: 3L
 but was: 2L
        at ...PaquetCm1HistoireGeographieTest.partageDesTachesEpingle(...java:232)
```

**28 tests, 1 echec**, et il nomme la ligne, la tache et l'effectif. C'est la propriete pour laquelle
j'ai refuse a `marc-dev` de remplacer cette enumeration par une lecture de sous-chaine : un ensemble
ecrit a la main rougit quand la bonne reponse change, une sous-chaine passerait au vert.

### 4.2 `FER-3` epingle : le partage des taches ne peut plus se deplacer en silence

Mutation : un enonce de `HG-CM1-01` est reformule **sans changer sa tache** - *"Quelle grande periode
vient juste apres la Prehistoire"* devient *"...suit juste apres..."*. C'est le mode que `yanis-qa` a
signale le 2026-09-08.

```
[ERROR] PaquetCm1HistoireGeographieTest.fer3AuMoinsDeuxTachesSurHgCm101LaMoinsServieAuMoinsTroisItems
org.opentest4j.AssertionFailedError:
[HG-CM1-01 : la tache "ordre" porte exactement 5 items]
expected: 5L
 but was: 4L
        at ...PaquetCm1HistoireGeographieTest.partageDesTachesEpingle(...java:232)
```

**Le plancher seul aurait laisse passer, et le message le prouve lui-meme** : le partage est passe de
5/5 a 4/6, donc `min` vaut 4, au-dessus de `MINIMUM_PAR_TACHE` qui vaut 3.

**Troisieme mesure, pour ne pas me contenter de ce raisonnement.** J'ai garde la mutation et retire
la seule ligne que mon diff de s33 ajoute a cette methode - l'appel a `partageDesTachesEpingle` - le
reste du corps etant identique a son etat de `1740807e`. Lancement de la seule methode
(`-Dtest=PaquetCm1HistoireGeographieTest#fer3AuMoinsDeuxTachesSurHgCm101LaMoinsServieAuMoinsTroisItems`) :
**`Tests run: 1, Failures: 0`, `BUILD SUCCESS`**. Le partage etait deplace et le garde-fou etait
muet ; c'est cette ligne-la, et elle seule, qui le rend rouge. Le fichier de test a ete remis en
etat.

---

## 5. L'ecart de la section 14 : le recomptage est exact, et quatre autres motifs sont perimes

*Methode, la meme pour toutes les lignes de cette section : extraction des 53 entrees de
`PAQUETS_HORS_DU_DOMAINE_NUMERIQUE` depuis le source de
`LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest`, puis comptage direct sur le paquet servi
correspondant de `contenu/` - nombre d'items, et `1 + len(distracteurs)` somme sur les items qui
portent des propositions. Aucun chiffre repris d'un document. Arbre `b45e5927`.*

### 5.1 Le recomptage de `marc-dev` est exact

`cm1-histoire-geographie` : **110 items, 330 propositions, tous les items a exactement trois
propositions**. Le motif reecrit a `s33-marc2` dit exactement cela. Il porte en plus sa methode
(*"comptage direct sur le paquet servi (`bonneReponse` et `distracteurs`)"*), ce que le motif
precedent n'avait pas - c'est ce qui l'avait laisse vieillir de s27 a s33 sans que personne le voie.

### 5.2 Quatre autres motifs du meme test portent un compte perime, et un cinquieme un chiffre survivant

**48 des 53 entrees sont conformes ; 5 sont en ecart.** Datation par `git log` sur chaque paquet
servi, en recomptant a chaque commit :

| Paquet exempte | Ce que le motif affirme | Reel au `b45e5927` | Perime depuis |
|---|---|---|---|
| `cp-qlm-corps-sens` | 8 items, 24 propositions | **14 items, 42 propositions** | `ad522a2a`, 2026-09-04, **s28** |
| `cp-qlm-etats-eau` | 8 items, **tous** a un seul distracteur | **14 items, 30 propositions** - 12 a deux propositions, **2 a trois** | `ad522a2a`, 2026-09-04, **s28** |
| `cp-qlm-vivant-non-vivant` | 8 items, **tous** a un seul distracteur | **14 items, 31 propositions** - 11 a deux propositions, **3 a trois** | `ad522a2a`, 2026-09-04, **s28** |
| `cm1-anglais` | 20 items, 60 propositions | **25 items, 75 propositions** | `90b01fdd`, 2026-09-08, **s32** |
| `cm1-synonymes` | 16 items, 48 propositions - **puis** *"aucune des 60 propositions ne commence par un chiffre"* | 16 items, 48 propositions | le 60 est un chiffre d'avant le 2026-08-20, laisse au present dans une phrase que le reste du motif corrige |

**Les deux entrees `cp-qlm-etats-eau` et `cp-qlm-vivant-non-vivant` sont perimees deux fois** : sur
le compte, et sur la **forme** - le motif affirme que *tous* les items portent un seul distracteur,
ce qui est faux depuis s28 sur 2 et 3 items respectivement. Un motif qui decrit mal la structure du
paquet qu'il exempte est pire qu'un compte faux : c'est la phrase qui justifie l'exemption.

**Ce n'est pas un faux vert, et il faut le dire aussi nettement que le reste.** Le garde-fou
recalcule la propriete sur le paquet reel a chaque execution, et
`aucunPaquetNEstEcarteEnSilenceDuCalculDeRang` refuse en plus qu'une entree designe un paquet qui
n'est plus hors du domaine. Les cinq paquets ci-dessus sont **reellement** hors du domaine
aujourd'hui. Ce qui pourrit, c'est la **prose** - un chiffre sans methode dans un fichier commite,
la famille que ce depot sanctionne partout ailleurs, et dont l'ecart de la section 14 etait le
cinquieme cas et non le premier.

### 5.3 La regle qui fermerait cette classe, et pourquoi je ne l'ecris pas dans ce geste

**Elle est encodable, et je l'ecris ici mot pour mot pour qu'elle n'ait pas a etre re-instruite.**

> **Enonce.** Dans le motif d'exemption d'une entree de `PAQUETS_HORS_DU_DOMAINE_NUMERIQUE`, tout
> groupe de chiffres suivi de `items` ou de `propositions` vaut exactement le compte mesure sur le
> paquet servi que l'entree designe. Un chiffre perime par un lot posterieur n'est pas conserve dans
> le motif : un motif n'est pas un journal.
>
> **Population, derivee** : les 53 entrees de la table, et toute entree future, sans reecrire la
> regle. **Etat attendu : rouge aujourd'hui, sur 5 entrees sur 53** - c'est ce qui la rend non
> decorative, elle refuse l'etat actuel du depot.
>
> **Ce qu'elle ne tient pas, et je le nomme plutot que de le laisser decouvrir** : un compte ecrit
> en toutes lettres (*"cinq items"*, `cp-qlm-cycle-de-vie`) lui echappe. Elle est donc un plancher
> sur la forme chiffree, pas une garantie sur la prose entiere.

**Je ne l'ecris pas maintenant, et le motif n'est pas technique.** `sofia-sm` a arrete s33 a la
section 14 - *"c'est la derniere fusion possible de s33"* - et aucune branche n'est ouverte pour moi.
Ajouter une regle et corriger cinq motifs dans le meme commit est un geste de la taille d'une story,
sur un sprint clos. **C'est une dette datee, avec son enonce ecrit et son declencheur : elle entre a
s34, la regle et les cinq corrections dans le meme commit** - la regle seule serait rouge, les
corrections seules revieilliraient au prochain lot. Route a `sofia-sm` pour le tirage.

**Aucun `ADR` n'est ecrit ici** : cette regle applique `ADR-0051` (une exemption declare ce qu'elle
exempte) et `ADR-0013` (un garde-fou se prouve mordant) a la prose d'une table existante. Aucune
decision d'architecture neuve n'est prise dans ce document.

---

## 6. `D7` - l'avis technique complet existe deja ; ce que j'y ajoute est une remesure et deux precisions

**L'obstacle n'est pas ouvert faute d'avis de ma part.** L'instruction complete est ecrite dans
`squad/sprints/s33-plan-2-tenu-le-palier-2-differe-a-s34/avis-faisabilite-theo.md`, section 4
(commit `d85cc7b0`) : la cause dans le code, le rayon chiffre, les deux familles de remede, la
troisieme voie qui ne paie pas la story, et la regle qui mordra avec sa population. **Ce qui manque
est l'instruction 1 de `DEC-0061`, due a `nadia-prof` seule** : ce que trois essais doit vouloir dire
quand l'elimination est possible. Je ne peux pas la choisir sans decider a sa place.

### 6.1 Les deux verrous tiennent encore sur l'arbre fusionne - remesure, non citee

*Methode : lecture du code de production de `domain/src/main/java/com/recreo/domain/mechanics/`, et
chargement des 68 fichiers de `contenu/` avec comptage de `1 + len(distracteurs)` par item enumere.
Arbre `b45e5927`.*

- `ESSAIS_AVANT_REVELATION = 3` dans les trois mecaniques (`BanditManchotSession`, `SaisieSession`,
  `DesignationSession`), inchange.
- Les trois `resoudre` construisent toujours `new Score(score.reussites() + (juste ? 1 : 0),
  score.tentatives() + 1)`, **sans jamais lire `essaisSurLaManche`**.
- Le cliquet d'`ADR-0063` : `DETTE_AU_2026_08_27` vaut `{cm1-conjugaison-present: 25,
  cm1-accords-groupe-nominal: 8}`, soit **33**, et la mesure rend **exactement 33 items a quatre
  propositions**. **Le plafond vaut la dette, au chiffre pres**, et les deux noms designent chacun un
  paquet reel.

**Donc "ajouter une proposition" reste indisponible** : trois propositions au plus, trois essais au
moins, et un paquet neuf a quatre propositions est rouge.

### 6.2 Le rayon a de nouveau vieilli, d'un sprint cette fois - je le remplace

| | `1740807e` (mon avis, section 4.3) | `b45e5927` (ici) |
|---|---|---|
| Items enumeres de type `question` | 1 227 | **1 245** |
| dont 2 propositions | 116 | **114** |
| dont 3 propositions | 1 078 | **1 098** |
| dont 4 propositions | 33 | **33** |
| Items ou l'elimination aboutit dans l'allocation | 1 194 - 97,3 % | **1 212 - 97,3 %** |
| Paquets de type `question` entierement dans ce cas | 63 sur 65 | **63 sur 65** |

Le delta se lit entierement dans le diff de s33 : +18 items a trois propositions sur
`cm1-histoire-geographie` (`S33-HG-08-12`), et deux items de `cp-eps-regles-jeu-collectif` passes de
deux a trois propositions (`S33-EPS-JEU`). **64 des 66 entrees de `bandit-manchot` rendent un score
parfait garanti** a un enfant qui presse tout - 63 paquets enumeres plus le paquet engendre
(`cp-additions-jusqu-a-10`, `nombreDePropositions: 3`).

### 6.3 Une correction a mon propre avis, et deux precisions techniques

**Correction.** Mon avis parle de *"trois mecaniques, tout le catalogue"*. *Methode : comptage de la
cle `mecanique` sur les 68 entrees de `catalogue.yaml`.* Le catalogue sert **66
`bandit-manchot` et 2 `saisie`, et zero `designation`**. `DesignationSession` porte le meme defaut de
comptage dans son code, mais **aucune entree ne la joue**, ce qu'une regle epingle explicitement
(`ChaqueMecaniqueDuDomaineEstAtteignableDepuisLeProduitTest`, `ADR-0080` T4 : *"la mecanique de
designation n'est cablee nulle part depuis s28"*). Le remede touchera trois classes pour rester
symetrique, mais il ne change ce qu'un enfant vit que sur deux mecaniques.

**Precision 1 - la famille A se decline en deux, et ce choix-la est le mien.** *A-1* : `resoudre`
cesse de crediter une manche resolue apres un echec ; local aux trois methodes, `Score` reste un
record de deux entiers. *A-2* : `Score` gagne un troisieme champ portant le cout des essais. **A-1
est la forme que je retiens si `nadia-prof` choisit A** - moins de pieces mobiles, et le seul usage
constate est de ne plus crediter, pas d'afficher un troisieme chiffre. **Ce qui me ferait changer
d'avis** : que la decision pedagogique demande de **montrer** a l'enfant ce que ses essais ont coute,
et pas seulement de ne plus le crediter. Alors A-2 devient un usage constate.

**Precision 2 - un remede qui circule et qui n'en est pas un, nomme pour qu'il ne revienne pas.**
Remelanger l'ordre des propositions apres chaque erreur ne paie ni `SCO-1` ni `SCO-3` : cela ne
defait qu'une strategie **positionnelle**, et un enfant qui lit les etiquettes elimine exactement
comme avant. Cela couterait en plus l'arbitrage de mecanique de `nadia-prof` de s10 (`M5`, `M9`,
`M11`), pour une propriete qu'il ne rend pas. A ecarter, comme la troisieme voie deja nommee dans
l'avis (afficher les essais a cote d'un score qui reste parfait).

**Ce qui reste du a `nadia-prof`, et rien d'autre** : une phrase. Quand l'elimination est possible,
l'enfant garde-t-il ses trois essais avec un score qui ne les ignore plus (**A**), ou en a-t-il moins
pour que l'elimination n'aboutisse pas (**B**) ? Des qu'elle est ecrite, l'`ADR`, la regle qui refuse
la strategie aveugle et les quatre non-regressions `SCO-4` a `SCO-7` sortent dans le meme geste.

---

## 7. Un rouge trouve sur le checkout partage, qui n'appartient pas au commit de s33

**J'ai lance sur mon propre fichier la commande de la CI avant de le rendre, et c'est ce geste qui a
trouve autre chose.** *Methode : `bash outils/mvn-jdk21.sh -B -pl bootstrap -am
-Dtest='ToutesLesReferencesDeDecisionResolventTest,ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest,AucuneLigneDeTableauNePortePlusDeCellulesQueSonEnTeteTest,ConcordanceDesTagsDAgentTest'`
- les quatre garde-fous du reacteur qui balayent les fichiers redactionnels, perimetre nomme, `-am`
porte.*

**Sur le checkout partage, `ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest` est rouge, et le
rouge n'est pas dans le commit de s33 : il est dans des fichiers non commites qui ne sont pas les
miens.** Deux mesures a dix minutes d'intervalle, sur une cible qui bouge parce que son auteur est en
train d'ecrire :

| Mesure | Defauts | Identifiants ecrits que `squad/decisions/REGISTRE-identifiants.md` n'alloue pas |
|---|---|---|
| 10:15 | **24** | `HG-R13` a `HG-R18` (`verdicts-nadia-instance-A.md`) ; `EXC-R7` a `EXC-R9`, `PER-R3` (`verdicts-nadia-instance-B.md`) ; `REG-R4`, `REG-R5` (`FICHE-bandit-manchot-regles-de-vie-cp.md`) |
| 10:26 | **26** | les `HG-R*` sont alloues entre-temps ; restent `EXC-R7` a `EXC-R9`, `PER-R3`, `REG-R4`, `REG-R5` sur trois fiches de conformite, plus `EPS-8`, `SEC-R4`, `SEC-R5`, `JEU-R9` a `JEU-R12` (`verdicts-nadia-instance-C.md`, apparu entre les deux mesures) |

**`nadia-prof` audite s33 pendant que j'ecris**, et elle alloue au fur et a mesure : le registre a
bouge entre mes deux mesures. Le garde-fou dit lui-meme le geste attendu (`PAR-5`, `ADR-0085`
clause 2) : l'allocation dans `REGISTRE-identifiants.md` va dans le **meme commit** que le fichier
qui ecrit l'identifiant. **Ce n'est ni une violation d'architecture, ni une regression de s33, ni un
geste de ma part** - c'est un travail en cours. Je le nomme parce qu'il rendra la porte technique
rouge s'il est commite tel quel, et parce que la seule facon de ne pas le decouvrir a la review est
de lancer cette commande avant de commiter.

**La preuve que ce rouge est le leur et non le mien, mesuree plutot que raisonnee.** J'ai cree un
worktree jetable sur `b45e5927`, y ai copie **mon seul fichier** - cette confirmation - et relance
les memes quatre garde-fous : **`Tests run: 5 / 1 / 3 / 7`, 0 echec, `BUILD SUCCESS`**. Le worktree a
ete supprime. Mon fichier passe les quatre, et le depot commite de s33 les passe aussi.

---

## 8. Ce que ce document ne fait pas

Aucun item, aucun paquet, aucune condition pedagogique. Aucun Done prononce - ni technique au sens
d'un Done de sprint, ni pedagogique. Aucun statut de `squad/programme/MATRICE-couverture.md`
deplace ; le taux reste 66 / 138 = 47,8 % tant que les trois instances n'ont pas audite. Aucun
`ADR` neuf, aucun fichier de code modifie : les trois mutations de la section 4 ont ete remises en
etat et l'arbre de travail ne porte, de mon fait, aucune modification. Rien n'est commite, rien
n'est pousse.
