# Confirmation de la porte technique - s34 (arbre fusionne, le palier 2 servi)

- **Auteur** : `theo-archi`. **Date** : 2026-09-11.
- **Arbre mesure** : `round-two` a `a7852398`, la tete laissee par la derniere fusion de s34
  (planning, section 14). **Toutes les mesures de ce document sont prises dans un worktree dedie,
  detache sur ce commit**, jamais dans le checkout partage : trois instances de `nadia-prof`
  auditent en parallele pendant cette seance (section 7). Le checkout partage etait propre
  (`git status --short` vide) a 09:35 et a 10:13 ; il ne l'etait plus a 10:24, et la
  section 7 en tire les consequences. **Toutes les mesures des sections 1 a 6 portent sur
  `a7852398` seul.**
- **JDK 21.0.10**, version que la **machine virtuelle qui execute les tests rapporte d'elle-meme**
  (`ADR-0071`), lue dans le bloc de proprietes des rapports Surefire de ce build :
  `java.version=21.0.10`, `java.runtime.version=21.0.10+8-LTS-217`,
  `java.vm.name=Java HotSpot(TM) 64-Bit Server VM`. Enveloppe : `bash outils/mvn-jdk21.sh`.
- **Toute mesure partielle du reacteur porte `-am` et nomme son perimetre** (`CLAUDE.md`,
  "Prerequis de build").
- **Ce n'est pas un Done.** La porte pedagogique appartient a `nadia-prof` seule.

**Ce sprint est le premier depuis longtemps qui touche du code de production, et sur trois
couches.** C'est le point de ce document : les sections 3 et 4 portent sur ce que ce diff-la peut
casser, les sections 5 et 6 sur les deux `ADR` dont le statut depend de ce qui vient d'etre livre.

---

## 1. Les quatre jobs de `.github/workflows/ci.yml`, un par un

`mvn verify` n'est que le contenu du job `build` (`CLAUDE.md`, amendement du 2026-09-08). **Je ne
deduis aucun job d'un autre.**

| Job | Commande lancee | Verdict |
|---|---|---|
| `build` | `bash outils/mvn-jdk21.sh -B clean verify` sur la racine, reacteur complet a six modules | **vert.** `BUILD SUCCESS`, six modules `SUCCESS`, 05:26 min |
| `chemins` | `bash .github/scripts/verifier-chemins-test.sh`, puis l'union de `git ls-files` et `git ls-files --others --exclude-standard` passee a `verifier-chemins.sh`, avec l'exclusion que le job porte lui-meme | **vert.** *"Tous les cas passent : le garde-fou de chemins mord, et sur le bon motif."*, puis *"Aucun chemin de poste de travail ni nom de compte dans les fichiers verifies."* |
| `outils` | la boucle du job sur ses trois motifs (`outils/*-test.sh` 5, `squad/outils/*/*-test.sh` 2, `outils/*-test.py` 2) | **vert. 9 juges purs executes**, aucun en echec, aucun motif vide |
| `prefixes` | `verifier-prefixes-test.sh`, `commit-atteignable-test.sh`, puis `git log --no-merges --format=%s b45e5927..HEAD` passe a `verifier-prefixes.sh` | **vert.** Les deux garde-fous passent ; *"Tous les commits portent un prefixe d'agent connu."* |

**La plage passee au job `prefixes` est `b45e5927..HEAD`**, c'est-a-dire le sprint entier depuis la
cloture de s33, et non le repli du workflow.

### Le compte de tests, avec sa methode dans le meme paragraphe

**1464 tests, 0 echec, 0 erreur, 0 ignore.** *Methode : somme des totaux que Maven annonce par
module a la fin de chaque module, sur un reacteur complet a six modules lance depuis la racine -
111 (`recreo-domain`) + 13 (`recreo-application`) + 397 (`recreo-adapter-web`) + 699
(`recreo-adapter-content`) + 238 (`recreo-bootstrap`, surefire) + 6 (`recreo-bootstrap`,
failsafe).* Le chiffre se raccorde exactement aux 1464 que `sofia-sm` annonce a la section 14 du
planning, par la meme methode et sur le meme commit. **Delta avec les 1443 de la cloture de s33 :
+21.**

---

## 2. Les regles ArchUnit, lancees et comptees par le moteur

**Commande, perimetre nomme, `-am` porte** : `bash outils/mvn-jdk21.sh -B -pl bootstrap -am
-Dtest='com.recreo.archi.**' -Dsurefire.failIfNoSpecifiedTests=false test`. Perimetre : le
paquetage `com.recreo.archi` du seul module `recreo-bootstrap`, ses modules amont construits depuis
l'arbre du worktree et non resolus depuis un depot local. **178 tests, 0 echec, `BUILD SUCCESS`.**

Les dix classes que le moteur ArchUnit rapporte, telles qu'il les rapporte :

| Classe | Regles executees |
|---|---|
| `DecouplageMecaniqueContenuTest` | 12 |
| `GestionDesErreursTest` | 4 |
| `AbstractionsCiteesTest` | 4 |
| `DifficulteTest` | 3 |
| `PureteDesCouchesTest` | 2 - `leDomaineNeDependQueDuJdk`, `lApplicationNeDependQueDuJdkEtDuDomaine` |
| `ArchitectureEnCouchesTest` | 2 - `lesDependancesVontVersLInterieur`, `lesAdaptateursNeSeConnaissentPas` |
| `AccesALEtatDeSessionTest` | 2 |
| `ChargementDuContenuTest` | 1 |
| `LeChoixDesJeuxResteDerriereLePortTest` | 1 |
| `LesReglesMordentTest` | 1 - `aucunContreExempleEnProduction` |

**Le compte, avec sa methode et ses exclusions - c'est la ligne ou ce depot s'est deja trompe de
quatre.** *Methode 1 : champs annotes `@ArchTest` et de type `ArchRule`, comptes par le moteur
ArchUnit dans son propre rapport par classe, sur le reacteur ; aucune liste ecrite a la main.*
**32 dans `recreo-bootstrap`** (le tableau ci-dessus) **plus 1 dans `recreo-adapter-web`**
(`AssertionsWebTest.aucuneAssertionParExpressionReguliere`, hors de cette commande, verte au
`verify` complet de la section 1) = **33 regles ArchUnit executees.**

*Methode 2, independante : motif `static final ArchRule` sur les arbres de sources du worktree,
`/target/` exclu.* Elle rend **35**, dont **2** dans le faux module de contre-exemple de
`LesGardeFousSontMisALEpreuveTest`, qui n'est pas compile avec le reacteur : **33.** Les deux
methodes concordent. **Un trente-quatrieme objet `ArchRule` existe**, `ScanDeComposantsTest`,
verifie a la main dans un `@Test` plutot que par le moteur : **34 objets, 33 executes comme
regles.** Compte inchange depuis s32 : ce sprint n'ajoute aucune `ArchRule`, et `ADR-0090` dit
lui-meme pourquoi sa regle n'en est pas une.

**Exclusions nommees, parce qu'elles expliquent tout ecart avec un autre compte** : les autres
classes de `com.recreo.archi` n'emploient pas ArchUnit - elles balaient du YAML servi, du Markdown
commite ou des feuilles de style, ce sont des garde-fous et pas des regles ArchUnit ; les 34
`@Test` de `LesReglesMordentTest` sont des **morsures**, pas des regles ; quatre classes
apparaissent deux fois dans la liste des classes executees, sous deux moteurs JUnit.

---

## 3. La Clean Architecture face au diff de s34 - verifiee, pas supposee

*Methode : `git diff --stat b45e5927..a7852398` restreint a `*/src/main/java/*`, puis recherche des
lignes d'import ajoutees sur tous les fichiers `.java` du diff, tests compris.*

| Perimetre | Mesure |
|---|---|
| Fichiers de `src/main/java` touches | **8** : `PartieController` (+121), `MecaniquesConfiguration` (+12), et six fichiers de `domain/mechanics` (remede d'`ADR-0090`, +54 net) |
| Lignes `+import` ajoutees dans `src/main/java` | **0** |
| Lignes `+import` ajoutees, tous fichiers `.java` du diff | **0** |
| Lignes de JavaScript ajoutees ou modifiees | **0** |

**Aucune dependance neuve n'est introduite nulle part par ce sprint** - c'est une constatation sur
le diff, pas une inference. Les seuls imports de `PartieController` qui bougent sont trois types de
`com.recreo.domain.mechanics.designation`, deja presents dans le domaine depuis s28, et le sens de
la dependance va vers l'interieur.

Les seize regles qui gouvernent les couches et le decouplage mecanique/contenu sont vertes sur cet
arbre (section 2) : `PureteDesCouchesTest` 2, `ArchitectureEnCouchesTest` 2,
`DecouplageMecaniqueContenuTest` 12. **`domain` ne depend que du JDK, `application` que du JDK et
du domaine, `bootstrap` est seul a voir les adaptateurs, et les adaptateurs ne se connaissent
pas** - les quatre proprietes que ce sprint pouvait casser en premier, puisqu'il traverse trois
couches.

**Et la regle centrale du chantier mord, prouvee par mutation plutot qu'affirmee.** J'ai ecrit dans
`DesignationSession` la conception qu'`ADR-0080` refuse - `zoneContenant(int x, int y)`, qui compare
une abscisse a `cadre.x()` et additionne `cadre.largeur()` -, compile, mesure, puis revert :

> `uneMecaniqueIgnoreLaGeometrie` : **rouge, 12 tests 1 echec**, *"Architecture Violation [...] was
> violated (6 times)"*, message d'`ADR-0080` en toutes lettres.

C'est le seul point du systeme ou cette conception se trahit, et il mord.

---

## 4. La dette 4 - ce qui peut etre teste cote serveur l'est-il ?

La dette 4 de `CLAUDE.md` est *"le comportement des mecaniques loge dans le JavaScript client : 692
lignes qu'aucun test ne couvrait, derriere huit classes Java de 55 lignes qui ne faisaient que
deleguer"*. **Ce sprint sert une mecanique entiere sur trois couches avec zero ligne de JavaScript
ajoutee** (`recreo.js` : 543 octets, inchange par ce sprint). Le verdict est calcule dans
`DesignationSession`, le controleur ne fait que peupler un modele, et le gabarit ecrit la geometrie
que le contenu declare sans aucune arithmetique. **La dette 4 n'est pas reproduite.**

Ce qui est teste cote serveur, mesure et non deduit :

| Propriete | Ou elle est jugee | Sur quoi |
|---|---|---|
| Le verdict et le comptage de la designation | `DesignationTest`, `UneMancheResolueApresUnEssaiInfructueuxNEstPasUneReussiteTest` (`domain`) | session reelle, sans navigateur |
| L'ecran de manche est un formulaire de vrais boutons, jamais une image cliquable | `ChaqueZoneDesignableEstUnBoutonDuFormulaireDeCoupTest` (`adapter-web`, MockMvc et Jsoup) | **un jeu de fixture**, `jeu-designation` |
| Aucune zone sous le plancher de cible tactile | `AucuneZoneDesignableNeDescendSousLaCibleTactileTest` (`adapter-web`) | **un jeu de fixture**, `jeu-designation` |
| Le client n'envoie jamais de coordonnee, cote script | `FrontieresDuJavaScriptTest` | le **materiau reel**, `recreo.js` |
| La bonne zone n'est pas predictible, l'enonce ne nomme pas son libelle, chaque cle facultative est lue | trois balayages d'`adapter-content` | le **paquet reel** `cp-frise-de-la-journee` |

**Le trou, nomme plutot que laisse decouvrir : aucun test ne sert l'ecran de manche du jeu reel de
designation.** `adapter-web` ne depend que de `recreo-application` et de `recreo-domain` (verifie
dans son `pom.xml`) : il **ne peut pas** charger un paquet d'`adapter-content`, et ses deux
garde-fous de rendu mordent donc sur un jeu fabrique dans son propre module de test. Le module qui
peut faire les deux est `bootstrap`, et il le fait deja trois fois pour les autres mecaniques
(`LaPartDesigneeDUnItemServiEstMarqueeALEcranTest`,
`LeTexteSupportDUnItemReelAtteintLEcranDeMancheTest` et
`LIllustrationDUnItemServiAtteintLEcranDeMancheTest` servent `jeu.id()` du catalogue reel par
MockMvc). **Sept garde-fous de `bootstrap/rendu` ont recu ce sprint une branche `designation`, et
les sept l'ecartent.** Chaque exclusion est motivee et honnete prise une par une - une designation
ne declare ni part designee, ni texte-support, ni reponse de fin de partie. **Leur effet cumule ne
l'est pas : le paquet `cp-frise-de-la-journee` n'atteint jamais un ecran servi dans aucun test du
depot.** C'est la moitie manquante de la section 5.

---

## 5. `ADR-0080` - je ne bascule pas a `Accepte`, et voici la mesure qui l'en empeche

Le critere d'`Accepte` est ecrit comme critere dans l'amendement du 2026-09-08, section 6 : **T4
livre**, **T6 livre** avec les trois balayages de production etendus et le cliquet monte dans le
meme diff, **les deux trous de la section 3 tranches**, et **les huit regles vertes sur leur
population reelle, pas sur une fixture**. Je les prends un par un.

**T4 : livre.** `PartieController.designer` et `mancheDeDesignation`, `fragments/designation.html`,
`MecaniquesConfiguration` publie `Designation`.
`ChaqueMecaniqueDuDomaineEstAtteignableDepuisLeProduitTest` baisse son cliquet de 1 a 0 dans le meme
diff, ce qui est le geste exact que ce garde-fou exigeait.

**T6 : livre pour une ligne sur deux.** Catalogue servi, mesure sur `catalogue.yaml` : **70 entrees,
67 `bandit-manchot`, 2 `saisie`, 1 `designation`** (`designation-frise-de-la-journee`, paquet
`cp-frise-de-la-journee`, 11 items). Le palier 2 est jouable. `cp-plan-de-la-classe` (`QLM-CP-11`)
n'est pas servi. Le cliquet
`AucunPaquetDeDesignationNEntreEnProductionSansEtendreLesTroisBalayagesTest` monte de 0 a 1, **en
egalite avec le compte reel**, et il refuse dans les deux sens : sa morsure 3 exige qu'un plafond
superieur au compte reel soit rouge lui aussi. **C'est un cliquet, pas une liste.**

**Les deux trous : tranches.** `ADR-0088` est `Accepte` et implemente -
`DesignationVerdict(boolean juste, Optional<String> explication, String enonce)` porte son enonce
(trou A) et ne porte plus aucune zone (trou B). `S33-PAL2-TROU-A` et `S33-PAL2-TROU-B` sont payees.

**Les huit regles sur leur population reelle : six sur huit. C'est ce qui bloque.** `lea-dev` ecrit
que les huit mordent desormais sur du contenu reel et precise que c'est a moi de le constater. **Je
constate six, et je dementis les deux autres, par mesure.**

*Methode : mutation du materiau reel, appliquee, mesuree, puis remise en etat par `git checkout --`,
dans un worktree dedie. Commandes `mvn -pl <module> -am -Dtest=<classe>`.*

| Regle | Population apres ce lot | Morsure mesuree sur le reel |
|---|---|---|
| Clause 10 `uneMecaniqueIgnoreLaGeometrie` | les types de production de `domain.mechanics` | **oui**, section 3 |
| Clause 11 `aucunRecordDuContratDeContenuNeDeclareDeMethode` | les records de `domain.content`, dont `Zone`, `CadreDeZone` et `ZoneADesigner` livres | **verte sur population non vide**, deja le cas avant ce lot |
| Garde-fou 1, bouton de formulaire | **jeu de fixture d'`adapter-web`** | **non** - voir ci-dessous |
| Garde-fou 2, interdits du JavaScript | `recreo.js`, materiau reel depuis toujours | **oui** : les sept mots ajoutes sont verifies sur le script reel, aucun n'y figure |
| Garde-fou 3, plancher de cible tactile | **jeu de fixture d'`adapter-web`** | **non** - voir ci-dessous |
| Garde-fou 4, bonne zone non predictible | `cp-frise-de-la-journee`, par `YamlContentPackLoader` | **oui.** Quatre `bonneZone` mutees vers `lever` : *"paquet designation-frise-de-la-journee : la bonne zone porte le meme identifiant sur 6 items sur 11"* |
| Garde-fou 5, enonce ne nomme pas le libelle | idem | **oui.** Enonce mute en *"Montre le premier moment."* : *"l'enonce [...] nomme le libelle de sa bonne zone (\"le premier\") comme suite contigue de mots"* |
| Garde-fou 6, cles facultatives lues | idem, par la chaine reelle | **oui.** `DesignationContentSourceFactory` mutee pour perdre `indice` : deux items de `cp-frise-de-la-journee` nommes, *"la fabrique de ce type de paquet ne la lit pas"* |

Les garde-fous 4 et 5 portent chacun leur clause de non-vacuite
(`assertThat(paquetsDeDesignationExamines).isPositive()`, motif `ADR-0051` cite dans le message), et
le garde-fou 6 en porte deux. **Ce ne sont pas des balayages qui seraient verts sur zero paquet.**

**La population d'un seul paquet suffit-elle a ce que je constate ? Pour ces six-la, oui, et c'est
mesure.** Les trois balayages de contenu jugent des proprietes **par paquet** - predictibilite
interne, contrefacon par enonce, cle lue - et un paquet de 11 items les exerce reellement, comme le
montrent les trois mutations ci-dessus, qui nomment toutes le paquet reel et aucun autre. `QLM-CP-11`
aurait ajoute une population, pas une propriete. **Un seul paquet ne suffit pas pour autre chose**,
et c'est le point suivant.

**Les garde-fous 1 et 3 mordent sur un jeu fabrique, et ce n'est pas un choix de `lea-dev` : c'est
structurel.** `adapter-web` ne depend pas d'`adapter-content` (section 4). Et le prix de cet ecart
vient d'etre paye en vrai, ce sprint :

> **Le garde-fou 3 aurait laisse passer le defaut qui a fait retirer `cp-plan-de-la-classe`.**
> *Methode : son juge est une fonction pure, `griefs(zones, plafondDeHauteurRem, plancherRem)`, qui
> compare `min(largeur%, hauteur%) / 100 x plafond` au plancher. Les deux termes sont derives -
> plafond `.image-designation { max-height: 22rem }` lu dans `recreo.css`, plancher
> `CIBLE_TACTILE_REM = 2,75` epingle par `TailleDesTextesEtBoutonsTest`. Le seuil vaut donc
> 2,75 / 22 = **12,5 %** du plus petit cote. Applique aux six cadres de la table d'`ines-design` :
> `tableau` 34 x 15, `bureau` 28 x 20, `fenetre` 18 x 40, `armoire` 16 x 22, `coin-lecture` 34 x 20,
> `porte` 18 x 16 - **plus petit cote 15 %, soit 3,3 rem ou 52,8 px, au-dessus du plancher de 44 px
> sur les six.*** Les 56 x 77 px que cette arithmetique donne pour `armoire` et les 63 x 56 px pour
> `porte` retombent au pixel sur ce que `lea-dev` a mesure au jar, ce qui verifie la derivation.
> **Les six zones passent le garde-fou 3, et trois d'entre elles rendent un libelle qui deborde de
> son bouton.** *Le plancher de cible tactile n'est pas la propriete "le libelle tient dans sa
> zone".* Deux proprietes voisines, une seule gardee, et c'est la seconde qui a coute une ligne de
> matrice ce sprint.

**Decision : le statut reste `Propose`.** Ce que `Accepte` exige et qui n'est pas fait tient en une
phrase, et elle est ecrite dans l'`ADR` en amendement plutot qu'ici, pour ne pas pourrir dans un
fichier de sprint : **un garde-fou de `bootstrap` qui sert l'ecran de manche du jeu reel de
designation et y rejoue les criteres des garde-fous 1 et 3.** Le mecanisme existe et est deja employe
trois fois dans `bootstrap/rendu` ; la population n'est plus vide depuis ce sprint ; le cout est un
fichier. **Je ne l'ecris pas dans ce geste** : ce document est une confirmation avant review, pas un
lot de developpement, et ecrire un garde-fou neuf apres la fusion des quatre branches ferait entrer
du code qu'aucune story ne porte.

---

## 6. `ADR-0090` - je bascule a `Accepte`, et je montre que ma regle mord encore

**Ce que `sofia-sm` affirme, verifie par moi-meme et non repris de son rapport.**

*Methode : le meme test, la meme commande, sur deux commits, dans un worktree detache -
`mvn -B -pl domain -am -Dtest='UneMancheResolueApresUnEssaiInfructueuxNEstPasUneReussiteTest'
-Dsurefire.failIfNoSpecifiedTests=false test`.*

| Commit | Ce qu'il porte | Verdict |
|---|---|---|
| `2c3463b3` (tete de `s34-theo`) | l'`ADR` et sa regle, sans le remede des trois sessions | **rouge**, 3 tests 1 echec, sur `uneMancheTrouveeApresUnEssaiFauxNEstPasComptee`, message de `DEC-0061` |
| `5bb6f9db` (fusion de `s34-theo` sur l'arbre qui porte deja `s34-sofia`) | les deux | **vert**, 3 tests sur 3 |
| `a7852398` (tete de s34) | tout | **vert**, au `verify` complet de la section 1 |

**Le rouge construit se referme exactement a cette fusion, et pas avant.** C'est ce que l'`ADR`
avait ecrit d'avance, et c'est verifie sur les commits plutot que deduit de l'ordre des fusions.

**Le scenario couvre bien la troisieme mecanique** : les trois scenarios du fichier nomment
`com.recreo.domain.mechanics.banditmanchot.BanditManchot`,
`com.recreo.domain.mechanics.designation.Designation` et
`com.recreo.domain.mechanics.saisie.Saisie`, et celui de la designation joue deux `ZoneADesigner`
avec un essai faux puis la bonne zone.

**La regle mord toujours - par mutation, pas par affirmation.** J'ai ecrit une mecanique concrete
neuve dans `domain/mechanics` - une implantation de `GameMechanic` qui ne fait rien -, compile,
mesure, puis supprimee :

> `chaqueMecaniqueConcreteDuDomainePorteSonScenario` : **rouge, 3 tests 1 echec**, *"ADR-0090 :
> chaque mecanique concrete du domaine est jugee par un scenario de ce fichier, et aucun scenario ne
> juge une mecanique disparue"*, avec le nom complet de la mecanique neuve dans le message.

**Une mecanique neuve sans scenario rend cette regle rouge le jour ou son type est ecrit.** C'est la
seule chose pour laquelle ArchUnit est le bon outil ici, et elle fonctionne.

**Le temoin de remplacement est bien celui que l'`ADR` avait ecrit d'avance** :
`DeuxCoupsSimultanesTest` lit desormais `score.tentatives()` sur l'etat de session et exige 1, la ou
il lisait `score.reussites()`. Une assertion remplacee, pas un test reecrit, et la propriete
d'`ADR-0019` - aucune ecriture n'en ecrase une autre - reste celle qui est gardee.

**Decision : `ADR-0090` passe de `Propose` a `Accepte`.** Sa regle existe, elle est executee par la
CI, elle a ete rouge avant son remede et verte apres, et sa derivation de population mord. L'index
est mis a jour dans le meme geste.

---

## 7. Le registre d'identifiants - la regle de poste n'a pas tenu, et voici le cout mesure

`ADR-0085` clause 3 avait un remede structurel que j'ai route a `sofia-sm` plutot que d'ecrire un
`ADR` dont la regle executable aurait ete *"ce script existe"* (avis de s34, section 3.3). Elle l'a
ecrit dans `squad/README.md`, section 9 : *"Une instance de `nadia-prof` qui ecrit dans
`squad/decisions/REGISTRE-identifiants.md` le fait depuis son propre worktree, jamais dans le
checkout partage."*

**J'avais commence a ecrire que la regle avait tenu. Elle n'a pas tenu, et je corrige avant de
rendre plutot qu'apres.** Mes deux premieres lectures du checkout partage etaient trop tot :

| Observation | 09:35 | 10:13 | **10:24** |
|---|---|---|---|
| `git status --short` du checkout partage | vide | vide | **18 fichiers**, dont `squad/decisions/REGISTRE-identifiants.md` |
| Worktrees de `nadia-prof` ouverts | 1 | 2 | 2 |
| Ecritures `squad/` dans ces worktrees | - | - | **zero, dans les deux** |

**Les trois instances ont ecrit dans le checkout partage, et aucune n'a ecrit dans le sien.** Les
deux worktrees ouverts (`git status --short` vide dans les deux, et aucun fichier de verdict sur leur
disque) n'ont servi qu'a lancer le jar. La regle etait ecrite depuis la veille, dans le fichier que
tous les agents lisent ; **trois instances sur trois l'ont contournee**, ce qui est le meme compte
que celui deja porte par `squad/README.md` pour la regle du worktree dedie, contournee deux fois sur
dix-huit sprints par deux agents differents apres avoir ete relue.

**`nadia-prof` instance B declare son propre manquement dans le fichier meme**, et c'est la seule
raison pour laquelle il est lisible sans enquete : *"je declare le manquement de poste plutot que de
le taire : j'ai ecrit depuis le checkout partage et non depuis un worktree dedie, contrairement a la
regle que `sofia-sm` a inscrite dans `squad/README.md`, section 9. Le mode de defaillance que cette
regle ferme - le faux vert emprunte - est donc ouvert sur ce diff."*

**Le cout, mesure et non suppose.** *Methode : les 18 fichiers non commites du checkout partage
copies dans mon worktree de mesure par-dessus `a7852398`, puis les quatre garde-fous de texte lances
dessus - `mvn -B -pl bootstrap -am -Dtest='ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest,
ToutesLesReferencesDeDecisionResolventTest,AucuneLigneDeTableauNePortePlusDeCellulesQueSonEnTeteTest,
ConcordanceDesTagsDAgentTest'`. Le checkout partage n'est pas touche.*

| Arbre juge | Verdict |
|---|---|
| `a7852398` seul | **vert**, 7 tests sur `ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest` |
| `a7852398` plus les 18 fichiers non commites | **rouge**, 7 tests 1 echec : **8 identifiants ecrits et non alloues** - `SEC-R6` (`FICHE-bandit-manchot-regles-securite-eps-cp.md`) et `EFF-R1` a `EFF-R7` (`verdicts-nadia-instance-C.md`). Les trois autres garde-fous restent verts |

**C'est le mode 1, le faux rouge, pour la cinquieme occurrence**, et il est transitoire : l'instance
C n'a pas encore alloue ce qu'elle vient d'ecrire, et rien ne dit qu'elle ne le fera pas. **Ce rouge
n'est le verdict de rien de commite** ; il dit seulement que l'arbre que trois instances partagent ne
sera jamais un commit tel quel. **Le mode 2, le faux vert emprunte, est ouvert par construction sur
ce diff** : l'instance B a porte la ligne d'etat courant a 623 / 102 sur le fichier qu'elle avait
sous les yeux, l'instance A l'a portee ensuite a 632 / 102, et **les deux chiffres sont justes chacun
sur son arbre**. Aucune des deux ne sera juste si une troisieme allocation arrive - c'est
l'arithmetique que `sofia-sm` devra refaire a la fusion, exactement comme a s33.

**Ce que cette mesure etablit, et c'est ce qui m'interesse.** Une regle de poste ecrite dans
`squad/README.md` n'a pas suffi a une seule instance sur trois, le lendemain de sa redaction. Le
motif n'est pas la negligence : deux de ces instances avaient un worktree ouvert et ont quand meme
ecrit ailleurs. **Ce que j'ai route a `sofia-sm` a s34 etait le bon destinataire et le mauvais
mecanisme**, et je l'ecris contre moi : j'avais conclu qu'aucun test ne peut voir cette regle parce
qu'un fichier ne porte pas le nom de qui l'ecrit, et j'en avais deduit qu'une regle de poste ferait
l'affaire. La premiere moitie reste vraie ; **la seconde vient d'etre dementie par l'usage**.

**Ce que je n'en conclus pas, et pourquoi je n'ouvre pas d'`ADR` aujourd'hui.** Le seul mecanisme qui
supprimerait la cause est une contrainte d'orchestration - une instance ne recoit pas le chemin du
checkout partage -, et cela ne se decide ni dans un `ADR` ni dans un test : cela appartient a qui
lance les instances. **Je le route a `sofia-sm` avec cette mesure a l'appui**, et je note que c'est
la deuxieme fois que je route ce defaut sans qu'un mecanisme executable le ferme. **Ce qui me ferait
changer d'avis et ecrire un `ADR`** : un mode 2 qui coute autre chose qu'une correction arithmetique
a la fusion - une allocation perdue, ou deux conditions differentes portant le meme identifiant dans
un commit. Ce n'est pas encore arrive ; le jour ou cela arrive, la dette cesse d'etre une gene
locale.

---

## 8. Le faux vert de la population vide - encodable a moitie, avec son chiffre et ses trous

**Le fait.** `PaquetCpEmcExclusionTest.laTacheDAdulteNeSertPlusDeCandidatAuRabais` etait **vert sur
une population vide** : sa boucle parcourait `itemsDAdulte()`, que la refonte de la tache B vidait,
et le corps ne s'executait plus. C'est la classe de defaut qu'`ADR-0051` interdit aux regles, et
qu'aucun automate ne surveille sur les methodes de test ordinaires. Il a ete mesure dans mon avis
prealable de s34 (section 1) ; `marc-dev` l'a paye dans `S34-EXC-TACHEB` en posant un
`assertThat(tacheB).isNotEmpty()` et en tirant la tache B comme complement de la tache A.

**Est-ce encodable en garde-fou general ? La propriete exacte ne l'est pas. Un proxy structurel
l'est, et il mord sur le materiau reel.**

**Ce qui n'est pas encodable, et pourquoi.** *"Ce test serait vert si sa population devenait vide"*
demande, pour chaque population, de savoir si elle **peut** devenir vide. Aucun modele de types ne le
dit ; la seule mesure exacte est une mutation par methode. Perimetre mesure sur l'arbre a
`a7852398` : **1446 methodes `@Test` dans 198 fichiers de test**, dont **502 parcourent une
collection** (`for`, `forEach` ou `stream`). 502 mutations taillees a la main ne sont pas un
garde-fou.

**Le proxy, et sa population mesuree.** Une methode `@Test` dont **la seule assertion vit a
l'interieur d'une boucle `for-each`**, sans aucune assertion avant elle et sans aucune clause de
non-vacuite dans le corps. *Methode : balayage des fichiers `*Test.java` et `*IT.java` de chaque
arbre, decoupage par methode sur l'equilibre des accolades, recherche de
`isNotEmpty|isPositive|isGreaterThan|hasSize|isNotZero` dans le corps.*

| Arbre | Methodes attrapees |
|---|---|
| `b45e5927` (cloture de s33) | **42** |
| `a7852398` (tete de s34) | **41** |

**La seule difference entre les deux listes est `laTacheDAdulteNeSertPlusDeCandidatAuRabais`**, et le
proxy l'attrape sur l'etat historique du depot, avant la refonte - **sur du materiau reel et non sur
une fixture de ma main**, ce qui est la lecon de s05 appliquee a ma propre proposition. Dans ce
fichier-la il n'attrape qu'elle : aucun faux positif sur le cas connu.

**Ce que le proxy n'est pas, borne d'avance pour que personne ne le croie plus fort qu'il n'est.**
Sur trois des 41 examinees, **une seule** est de la meme famille exacte que le defaut paye - une
boucle sur une **sous-population filtree**
(`PaquetCm1ProblemesEtapesTest.lesIndicesDesItemsCorrigesParLaReserveBNommentLeVerbeDeLeurEnonce`,
qui ne juge que les positions 21, 22 et 23 et deviendrait vide si le paquet raccourcissait). Les deux
autres bouclent sur le paquet entier, qu'un autre garde-fou empeche de se vider. **Le proxy attrape
la forme, pas le risque**, et sur cet echantillon les deux tiers sont la forme sans le risque.

**Ce qui manque pour en faire une regle, nomme exactement, comme je l'ai fait pour `RES-1`.**

1. **Le proxy est une expression reguliere sur du source Java.** Ce depot n'a aucun garde-fou qui
   juge la structure d'une methode Java par un motif de texte, et c'est la famille de defaut de s05 :
   un motif qui passe sa propre epreuve et echoue sur ce que le monde ecrit. La seule forme robuste
   derive la population du **bytecode de test** - une methode dont toutes les invocations
   d'assertion sont a l'interieur d'une boucle - ce qu'ArchUnit ne sait pas exprimer et qu'aucune
   bibliotheque du depot ne fournit aujourd'hui.
2. **Le cout d'entree est de 41 lignes reparties sur une vingtaine de fichiers de test de contenu**,
   dont je ne suis pas l'auteur et dont le perimetre appartient aux developpeurs. Poser la regle
   seche la rendrait rouge et donc incommittable, donc negociee - le mode de defaillance
   qu'`ADR-0088` a nomme.
3. **La forme disponible aujourd'hui est un cliquet, et son plafond est connu : 41.** Rouge des que
   42, donc *"aucune methode de cette forme n'entre en plus"*, la dette existante restant a payer par
   ceux qui possedent ces fichiers. C'est le patron d'`ADR-0067` et d'`ADR-0013`, et le plafond
   vaudrait **exactement** la dette, pas un de plus.

**Je n'ecris ni la regle ni l'`ADR` dans ce geste**, pour deux motifs que je prefere dire :
l'objection 1 n'est pas levee, et un cliquet dont le critere est fragile epingle un chiffre qui
bougera pour des raisons de mise en forme plutot que de qualite - un cliquet qui rougit sans qu'une
dette ait bouge apprend a l'equipe a le lire comme du bruit, ce qui est la meme faute par l'autre
bout. **Ce qui me ferait changer d'avis** : une seconde occurrence de ce defaut - ce serait la
troisieme fois que cette famille se paie -, ou un moyen de deriver la population du bytecode plutot
que du texte. **Ceci est une dette ouverte et datee, pas un refus definitif.**

---

## 9. Ce que ce document ne fait pas

- **Il ne prononce aucun Done pedagogique.** `QLM-CP-14` est techniquement livree ; ce qu'elle vaut
  pour un enfant de CP appartient a `nadia-prof` seule.
- **Il ne tranche rien de produit ni de pedagogique.** Le plancher de lisibilite d'un libelle de
  zone, deja route a `nadia-prof` par `sofia-sm` (planning, section 15.2), reste chez elle ; la
  section 5 n'y ajoute qu'un fait technique - le garde-fou 3 ne garde pas cette propriete et ne peut
  pas la garder.
- **Il ne commite rien et ne pousse rien.** Le worktree de mesure est detache, il n'a produit aucun
  commit, et il est supprime a la fin de cette seance.
- **Il ne modifie aucun fichier de code.** Les cinq mutations des sections 3, 5 et 6 sont appliquees
  puis remises en etat dans le worktree de mesure, jamais dans le checkout partage.
- **Il n'ecrit dans le checkout partage que ses propres fichiers** : ce document, l'amendement
  d'`ADR-0080`, le statut d'`ADR-0090` et les deux lignes d'`ADR-index.md`. La copie des 18 fichiers
  non commites faite en section 7 va **du** checkout partage **vers** mon worktree de mesure, jamais
  l'inverse, et aucun fichier d'un autre agent n'est touche.
- **Il n'alloue aucun identifiant de condition neuf** : il n'en cree aucun, il cite des identifiants
  deja alloues. La ligne d'etat courant de `squad/decisions/REGISTRE-identifiants.md` n'est pas
  touchee par moi, et le rouge de la section 7 n'est pas le mien.
- **Deux fichiers de decision changent** : `ADR-0090` passe a `Accepte`, `ADR-0080` recoit un
  amendement qui constate l'etat mesure et nomme la seule clause qui manque a son `Accepte`.
  `ADR-index.md` est mis a jour pour les deux.
