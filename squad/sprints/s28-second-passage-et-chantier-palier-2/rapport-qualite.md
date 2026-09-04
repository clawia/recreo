# Rapport qualite - s28, yanis-qa

- **Mesure sur** : `round-two` a `21f3d1d` (dernier commit du sprint, `[MARC] s28: S28-4`).
- **Worktree dedie** : `../recreo-s28-yanis`, branche `s28-yanis`, lignee verifiee par
  `bash outils/verifier-lignee.sh` ("le worktree sur la branche 'round-two' descend de la lignee
  de round-two" - le script nomme la branche mere, pas la mienne, mais l'ancetre commun est
  confirme). Worktree et branche detruits apres mesure (`git worktree remove`,
  `git branch -D`) : aucune trace laissee sur le depot partage.
- **JDK** : celui que la machine virtuelle des tests rapporte d'elle-meme (bannieres de demarrage
  Spring Boot des tests d'integration : *"Starting ... using Java 21.0.10"*), pas celui d'une
  entree de toolchain recopiee (`ADR-0071`) - **21.0.10**. Enveloppe : `bash outils/mvn-jdk21.sh`.
- Pendant ma revue, une modification est apparue dans le worktree principal
  (`squad/programme/MATRICE-couverture.md`, deux corrections orthographiques) sans que je l'aie
  faite : je n'ai touche aucun fichier hors de mon propre worktree dedie, et cette mesure porte
  entierement sur `21f3d1d`, avant cette modification.

## Porte technique - commande, resultat, methode de comptage

```
bash outils/mvn-jdk21.sh -B clean verify
```

**Reacteur complet, aucun `-pl`, executee par moi-meme sur `21f3d1d`.** Resultat : **BUILD
SUCCESS**, les six modules (`recreo`, `recreo-domain`, `recreo-application`, `recreo-adapter-web`,
`recreo-adapter-content`, `recreo-bootstrap`) en `SUCCESS`, tests unitaires et d'integration
(`failsafe`, module `bootstrap`) compris.

**Compte de tests, methode de l'amendement s11** : somme de l'attribut `tests="..."` de chaque
rapport XML sous `target/surefire-reports/` et `target/failsafe-reports/` des cinq modules qui en
produisent, apres `clean` (165 fichiers XML lus) : **1208 tests, 0 echec, 0 erreur**. Je confirme
le chiffre annonce par `marc-dev` (dernier commit du sprint), par ma propre execution, sur mon
propre `clean`, sur l'arbre final du sprint. **Aucun ecart avec le 1208 annonce.**

Je n'ai pas remesure l'entree de sprint (1140 tests a `c6acf3b`) : la demande portait sur la
sortie, et je n'ai pas de motif de douter d'un chiffre d'entree que je n'ai pas a verifier ici.

**Tests instables constates** : aucun. Une seule execution de `mvn -B clean verify` a produit ce
resultat ; je n'ai relance aucun test pour l'obtenir.

**Verdict : porte technique verte.** Ceci ne dit rien du Done pedagogique, qui reste a
`nadia-prof` seule : aucune des lignes discutees ci-dessous (questionner le monde, histoire-
geographie, anglais, calcul pose) n'a de Done pedagogique prononce a ce jour.

---

## 1. Les conditions bloquantes de nadia-prof - gardees, affirmees, ou laissees a elle

Methode : pour chaque condition, j'ai cherche le test qui echoue si la propriete est violee, lu
son code (pas seulement son nom), et classe. Je ne compte comme "gardee" que ce qui echouerait
reellement sur une mutation du contenu ou du code.

### 1.1 Questionner le monde CP (`FER-1`, `FER-2`, `FER-3`, S28-1)

| Ligne | `FER-1` (extension) | `FER-2` (>= 10 items) | `FER-3` (2 taches, min. 3) |
|---|---|---|---|
| 1 - vivant/non-vivant | **gardee** - `chacuneDesDeuxCategoriesEstRepresenteeParmiLesItemsDeCategorisation` fige le compte exact (4 vivants, 7 objets dont 3 de matiere inerte) : retirer un item de matiere inerte fait echouer le test | **non gardee explicitement** (seul le plafond <= 20 est teste), mais le compte fige a 14 la rend indirectement observable | **gardee** - `auMoinsDeuxTachesDistinctesEtLaMoinsServieAuMoinsTroisItems` |
| 3 - corps/sens | **non gardee** | **non gardee** | **non gardee** |
| 5 - etats de l'eau | **gardee** - espace de reponse ferme aux deux etats, distribution moitie/moitie testee | **non gardee explicitement**, meme remarque | **gardee**, et sa precision ("4 items minimum, sous 2 formes") - `laMoitieChangementDEtatPorteAuMoinsQuatreItemsSousDeuxFormes` |

**Defaut trouve : la ligne 3 (corps/sens) n'a recu aucun test neuf.** Le commit `ad522a2`
(S28-1) modifie `cp-qlm-corps-sens.yaml` (8 -> 14 items, ajout des cinq parties du corps et de
l'item "la peau" pour le toucher) mais **ne touche pas**
`PaquetCpQlmCorpsSensTest.java` :

```
git log --oneline -- adapters/adapter-content/src/test/java/com/recreo/adapter/content/PaquetCpQlmCorpsSensTest.java
2711f35 [LEA] s27: voyelles simples (S27-1) et premier paquet Questionner le monde ...
```

Le fichier de test date de s27 et ne verifie que trois proprietes generiques (plafond <= 20 items,
aucune reponse repetee, exactement trois propositions). **Rien ne mord** sur : la presence des
cinq parties du corps neuves en position de bonne reponse (`FER-1`), le seuil de dix items
(`FER-2`), ou l'existence de deux taches distinctes (`FER-3`) - alors que les deux paquets voisins
du meme commit (`PaquetCpQlmEtatsEauTest`, `PaquetCpQlmVivantNonVivantTest`) ont ete etendus pour
ces trois memes conditions. La fermeture de cette ligne, revendiquee par le message de commit, est
donc **affirmee et non gardee** pour un tiers du lot. Je route ce constat a `sofia-sm` pour
`lea-dev` : c'est un oubli de couverture, pas une decision.

### 1.2 Histoire-geographie CM1, composition B (`FER-1`, `FER-2`, `FER-3`, `HIS-1`, `HG-R3`, S28-2)

`PaquetCm1HistoireGeographieTest.java` est etendu et mord reellement :
- `laDistributionParLigneEstExactementCelleDeLaCompositionB` fige la distribution des 37 items sur
  les onze lignes (assertion `containsOnly` exhaustive) - un item deplace vers une ligne non
  touchee par s28 le ferait echouer, prouvant a la fois que les deux lignes ciblees montent a dix
  et qu'aucune ligne neuve n'est ouverte.
- `fer1LesTroisTypesDEspaceSontServisEnBonneReponse` et `fer1LEauLEnergieEtLesAlimentsSontRepresen
  teesEnBonneReponse` : **gardees**.
- `fer2EstTenueSurLesDeuxLignesDeLaCompositionB` : **gardee**, seuil explicite de 10.
- `fer3AuMoinsDeuxTachesSur...` (x2) : **gardees**.
- `hgR3LesAnciensDistracteursAbsurdesOntDisparu` : **gardee** pour la regression precise signalee
  par `nadia-prof` (les deux distracteurs absurdes de l'item rural).
- `HIS-1` (au plus un tiers de restitution, calcule sur le lot) vit dans
  `UneConditionDeLotSeCompteSurLeLotDeMatiereEtNonSurUnFichierTest`, mise a jour pour 37 items :
  **gardee**.

`HIS-2` (aucune double reponse defendable), l'exactitude factuelle, `HIS-3` (source de chaque
fait, dans le commentaire du paquet) et `BIA-2` (aucun type d'espace reduit a un seul trait) sont
**explicitement documentees comme non automatisables** dans le Javadoc de la classe de test, et
c'est coherent avec l'arbitrage de `nadia-prof` elle-meme (section 10 de son arbitrage prealable :
"moi seule", "non" automatisable pour `HIS-2`, l'exactitude, `BIA-*`). Rien n'est faussement
presente comme garde ici.

`HIS-4` (chaque item declare la ligne qu'il sert) est gardee au niveau du depot entier, voir
section 3.

### 1.3 Anglais CM1 (`ANG-4`, `ANG-5`, `REC-5`, `REC-6`, `BIA-1`, `BIA-2`, S28-3)

- `ANG-4` (trois champs lexicaux, 5 mots minimum chacun) : **gardee** -
  `ang4TroisChampsLexicauxServisAuMoinsCinqMotsChacun` verifie a la fois le plancher par champ et
  l'absence de reste (chaque item de la ligne appartient a l'un des trois champs, sans
  recouvrement).
- `REC-6` (trois propositions du meme champ lexical) : **gardee**.
- `FER-2` sur `LVE-CM1-04` : **gardee**.
- `REC-5` (aucun mot transparent en bonne reponse) : **partiellement gardee, et le Javadoc le dit
  lui-meme** - le test verifie l'identite stricte de graphie (casse pres) sur les quinze paires
  francais/anglais servies, mais la quasi-identite (jugement de sens) reste a `nadia-prof`. C'est
  exactement ce que `lea-dev` a ecrit dans son message de commit : je confirme l'ecart, il est
  disclosed correctement et non un defaut cache.
- `ANG-5` (la reponse est un mot seul ou une expression figee, aucune forme grammaticale non
  enseignee) : **defaut trouve - affirmee dans un commentaire du paquet, non gardee, et non
  disclosed comme telle.** `cm1-anglais.yaml` porte le commentaire *"ANG-5 (nadia-prof,
  2026-09-03) : la reponse attendue est un mot seul, jamais une forme grammaticale ..."`, mais
  aucun test de `PaquetCm1AnglaisTest` ne verifie cette propriete, et - a la difference de `REC-5`,
  dont le Javadoc de la classe dit explicitement qu'il est laisse a `nadia-prof` - `ANG-5` n'est
  meme pas mentionnee dans la liste des criteres non couverts. C'est pourtant une propriete
  largement calculable (aucune proposition ne contient d'espace, d'article ou de terminaison
  plurielle) : rien n'empeche de l'ecrire. Je route ce trou a `sofia-sm` pour `lea-dev`.
- `BIA-1` (aucune comparaison erigeant un pays anglophone en norme face a la France) :
  **gardee superficiellement** - `bia1AucunItemDeDecouverteCulturelleNeMentionneLaFrance` verifie
  seulement l'absence du mot "France", ce qui est une garde de forme etroite (une comparaison
  implicite sans le mot "France" passerait).
- `BIA-2` (aucun pays reduit a un seul trait) : **le test nomme `bia2...` ne garde pas la
  propriete qu'il annonce.** `bia2LesBonnesReponsesDeDecouverteCulturelleSontToutesDistinctes`
  verifie que les cinq bonnes reponses de la ligne 7 sont distinctes entre elles - une propriete
  vraie par construction des lors qu'on ne repete jamais un fait, et qui ne detecterait **pas** un
  pays represente par un seul fait isole parmi d'autres pays a plusieurs faits (le defaut exact que
  `BIA-2` interdit). Sur le contenu reel livre, la propriete tient (Royaume-Uni : 2 faits,
  Etats-Unis : 3 faits, verifie par lecture directe de `cm1-anglais.yaml`), mais **par la
  qualite du contenu, pas par ce que le test prouve** : un item neuf sur un troisieme pays servi
  par un seul fait passerait ce test sans le faire echouer. Je route ce trou a `sofia-sm` pour
  `marc-dev`/`theo-archi` : soit le nom du test est trompeur et doit etre corrige, soit la clause
  doit vraiment compter les items par pays.

### 1.4 Calcul pose CM1 (`POS-1` a `POS-4`, S28-4)

- `POS-1` (mention de couverture partielle exigee d'avance) et `POS-3` (chaque distracteur est une
  erreur classique nommee) sont **explicitement documentees comme non calculables** dans le
  Javadoc de `PaquetCm1MultiplicationPoseeTest`/`PaquetCm1DivisionPoseeTest` - ecrites mot pour mot
  dans l'en-tete des paquets pour reprise par `camille-po`, pas des proprietes que le contenu
  charge peut prouver. Coherent avec l'arbitrage de `nadia-prof` (`POS-3` : "moi seule pour le
  jugement ; le marqueur est mordable" - le marqueur textuel existe dans le YAML, sa verite non).
- `POS-2` (meme nombre de chiffres, au moins un distracteur partage le chiffre des unites) :
  **gardee et mordante, verifiee programmatiquement sur les items reels** (multiplication) - la
  commande de commit le dit et je l'ai confirme par lecture du test
  (`lesTroisPropositionsOntLeMemeNombreDeChiffresEtUnDistracteurPartageLeChiffreDesUnites`).
- `POS-4` (les quatre combinaisons de chiffres en multiplication ; diviseur a un seul chiffre et
  reste demande en division) : **gardee** (`lesQuatreCombinaisonsDeChiffresSontServies`, et son
  equivalent division, lu de la meme facon).
- `FER-2` (>= 10 items par ligne) : **gardee** sur les deux paquets.

---

## 2. Le cliquet de `HIS-4` - a zero, et le reste

`ChaquePaquetServiDeclareLesLignesDeReferentielQuIlSertTest` (`bootstrap`) porte
`DETTE_DE_DECLARATION = Set.of()` et `PLAFOND_DE_DETTE = 0`, avec un test dedie
(`laDetteDeDeclarationNePeutNiGrandirNiPourrir`) qui echoue si le plafond ne correspond plus
exactement a la taille de la dette, ou si un nom de la dette ne designe plus un paquet servi
non-declarant. C'est le meme mecanisme que `DETTE_DE_BALAYAGE` d'`ADR-0013`, deja eprouve par ce
depot.

**Verifie que les quatre lots de contenu de s28 n'ont pas fait remonter la dette** : j'ai controle
que les quatre paquets touches ou crees (`cp-qlm-vivant-non-vivant`, `cp-qlm-corps-sens`,
`cp-qlm-etats-eau`, `cm1-histoire-geographie`, `cm1-anglais`, `cm1-multiplication-posee`,
`cm1-division-posee`) declarent tous `lignesDeReferentiel` (lecture directe des fichiers YAML), et
la suite complete que j'ai executee moi-meme (section porte technique) fait tourner ce test en
vert, `DETTE_DE_DECLARATION` vide comprise - si un seul paquet servi avait manque sa declaration,
`laDetteDeDeclarationNePeutNiGrandirNiPourrir` ou `chaquePaquetServiDeclareLesLignesQuIlSert`
aurait echoue sur mon execution.

**Le garde-fou lui-meme mord**, verifie par lecture de ses sept morsures (paquet muet, code
inconnu, code d'un autre niveau, plusieurs lignes sans attribution par item - exactement le
defaut de s27 reproduit -, ligne declaree et jamais servie, code declare deux fois, item qui
repete une attribution deja portee par le paquet) : chacune construit un contre-exemple en memoire
a partir du materiau reel, mute une seule propriete, et verifie un message d'erreur precis. Ce
n'est pas une affirmation, je l'ai lu ligne par ligne.

**Verdict : cliquet a zero, tenu par le sprint, et le mecanisme qui l'empeche de remonter est
reellement mordant.**

---

## 3. Les garde-fous de S28-5 (designation) mis a l'epreuve

`LaBonneZoneNEstPasPredictibleSurUnPaquetTest` (garde-fou 4) et
`AucunEnonceDeDesignationNeNommeLeLibelleDeSaBonneZoneTest` (garde-fou 5) sont **reellement
mordants sur les jeux fautifs commites** (`faux-paquet-designation.yaml`, deux variantes chacun) -
verifie par lecture de leurs `assertThatThrownBy` et de leurs fixtures. Mais **ni l'un ni l'autre
ne balaie `src/main/resources`** : les deux classes le disent elles-memes dans leur Javadoc
(*"Aucun paquet de designation n'existe sous src/main/resources/contenu a ce jour ... une clause
qui scannerait ce repertoire aujourd'hui trouverait une population vide, ce qu'ADR-0051 refuse"*),
et je l'ai confirme independamment : `git ls-files` ne montre aucun fichier
`*designation*.yaml` sous un `src/main/resources` de ce depot, seulement sous des `src/test/
resources/contreexemple`. C'est exactement ce que `sofia-sm` a ecrit dans son message de commit
(*"non cables sur src/main/resources"*) : **je confirme cet ecart, il est correctement disclosed,
ce n'est pas un vert a tort**.

**Point que je remonte, au-dela de la confirmation** : la dette est ecrite dans `ADR-0080`
(amendement du 2026-09-04 par `theo-archi`) sous la forme d'un cliquet promis - *"un garde-fou qui
compte ces paquets ... devient rouge le jour meme ou la dette est due ... sa forme est celle
d'ADR-0067 et son ecriture appartient au lot qui livrera T6"*. **Ce cliquet n'existe pas encore
dans le code a la fin de s28** : j'ai cherche un test de la forme "population vide aujourd'hui,
plafond zero" comparable a celui de `HIS-4` (section 2) pour cette dette et n'en ai trouve aucun.
Rien n'empeche donc, mecaniquement, un premier paquet de designation d'entrer sous
`src/main/resources` a s29 sans que les garde-fous 4, 5 et 6 soient etendus pour le balayer - seul
le texte de l'`ADR` le rappellerait. Ce n'est pas un vert a tort aujourd'hui (le contre-exemple
n'existe pas), mais c'est une dette qui repose sur une lecture humaine du texte de l'`ADR`, pas sur
un garde-fou execute, exactement la famille de risque que `CLAUDE.md` documente ("une dette
annoncee et jamais payee"). Je route ce point a `sofia-sm` pour `theo-archi` : soit le cliquet est
ecrit des maintenant (il ne coute rien, le plafond est zero et gratuit), soit sa non-existence est
assumee comme un risque accepte pour un sprint.

---

## 4. Les trois ecarts signales par les developpeurs - confirmes un par un

| Ecart signale | Verification | Verdict |
|---|---|---|
| `lea-dev` : `REC-5` garde sur l'identite de graphie seule, la quasi-identite reste a `nadia-prof` | Lu `rec5AucuneDesQuinzePairesNePartageLaMemeGraphie` : `isNotEqualToIgnoringCase`, comparaison stricte casse pres, aucune heuristique de proximite | **Confirme, disclosed correctement** |
| `marc-dev` : la prescription "dix items, partie epuisant exactement le vivier" est impossible, `ChaqueJeuDuCatalogueOffrePlusDItemsQueDeManchesTest` exige strictement plus d'items que de manches | Lu le garde-fou : `vises = jeu.nombreDeManches() + 1`, assertion `isGreaterThan(jeu.nombreDeManches())` - egalite refusee par construction. Compte les items reels : 11 dans `cm1-multiplication-posee.yaml` et dans `cm1-division-posee.yaml` (`grep -c` sur les deux fichiers), `nombreDeManches: 10` dans `catalogue.yaml` pour les deux entrees | **Confirme.** 11 > 10, le garde-fou generique est respecte ; la formulation de l'arbitrage ("dix items") ne peut litteralement pas etre tenue sur ce depot, `marc-dev` a choisi la seule issue compatible avec un garde-fou existant plutot qu'une derogation hors mandat |
| `sofia-sm` : les garde-fous 4, 5 et 6 du palier 2 mordent sur fixtures mais ne balaient pas la production | Voir section 3 | **Confirme**, et complete par le point sur le cliquet manquant |

Les trois signalements sont exacts. Aucun n'est un defaut cache : chacun est ecrit noir sur blanc
dans le message de commit correspondant, avant que je ne le verifie.

---

## 5. Couverture par module

| Module | Fichiers `*Test.java` | Etat |
|---|---|---|
| `domain` | 8 | Couvert, y compris la mecanique neuve (`DesignationTest`, 17 tests annonces par `sofia-sm`) |
| `application` | 2 (`PartieServiceTest` - 11 methodes de test sur le service d'orchestration directement, sans contexte Spring -, `ProvenanceDesClassesAmontEstLeDepotTest`) | Couvert. C'est precisement la dette 5 de `CLAUDE.md` ("le service d'orchestration n'etait couvert que par ricochet") : ici il est teste en direct, hors de tout contexte Spring |
| `adapters/adapter-web` | 47 | Couvert |
| `adapters/adapter-content` | 75 (dont les six fichiers de paquet neufs ou etendus de ce sprint) | Couvert |
| `bootstrap` | 31 | Couvert, y compris les tests d'integration Spring Boot (`failsafe`) et `ChaquePaquetServiDeclareLesLignesDeReferentielQuIlSertTest` |

**Aucun module a zero test.** La dette 5 de `CLAUDE.md` (modules `application`, `adapter-web`,
`adapter-persistence` sans aucun test sur `main`) n'est pas reproduite sur `round-two` a ce
sprint - `adapter-persistence` n'existe pas ici par decision produit (`DEC-0028`), ce qui n'est pas
une omission.

---

## Ce que je route

- **A `sofia-sm`, pour `lea-dev`** : `PaquetCpQlmCorpsSensTest.java` n'a recu aucune extension en
  S28-1 alors que le contenu qu'il teste est passe de 8 a 14 items ; `FER-1`, `FER-2` et `FER-3`
  sur la ligne 3 de questionner le monde CP sont affirmes fermes par le commit et gardes par
  aucun test (section 1.1).
- **A `sofia-sm`, pour `lea-dev`** : `ANG-5` est ecrite dans le commentaire de
  `cm1-anglais.yaml` mais n'a ni test ni mention d'exclusion dans le Javadoc de
  `PaquetCm1AnglaisTest`, a la difference de `REC-5` qui documente honnetement sa limite
  (section 1.3).
- **A `sofia-sm`, pour `marc-dev`/`theo-archi`** : le test nomme
  `bia2LesBonnesReponsesDeDecouverteCulturelleSontToutesDistinctes` ne verifie pas la propriete
  `BIA-2` qu'il annonce - il verifie une distinction de faits qui n'empecherait pas un pays reduit
  a un seul trait si un troisieme pays etait ajoute avec un seul item (section 1.3).
- **A `sofia-sm`, pour `theo-archi`** : la dette des garde-fous 4/5/6 de designation (`ADR-0080`)
  n'a pas de cliquet ecrit a ce jour, contrairement a la dette `HIS-4` qui en a un - rien n'empeche
  mecaniquement qu'un paquet de designation entre en production a s29 sans etendre ces
  garde-fous (section 3).
- **Rien d'autre sur la porte elle-meme** : elle est verte, mesuree par moi sur `21f3d1d`,
  reacteur complet, 1208 tests, 0 echec, 0 erreur.

Je ne tranche ni la forme des correctifs ci-dessus (architecture, `theo-archi`), ni si les lots de
questionner le monde, d'histoire-geographie, d'anglais ou de calcul pose sont pedagogiquement
prets - cela reste a `nadia-prof` seule, en jouant.
