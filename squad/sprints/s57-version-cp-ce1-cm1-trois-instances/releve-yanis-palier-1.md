# [Sprint s57] Releve de `yanis-qa`, palier 1 : relecture independante des postes 5 et 3, reponse a `Q-C1`

- **Auteur** : `yanis-qa`, instance transverse. **Date** : 2026-10-14. **Arbre mesure** : `round-two` a `f95736b5`, worktree et branche `s57-yanis-relecture` (aucun commit de code ni de contenu, un seul fichier ajoute : celui-ci).
- **Ce que ce fichier ne fait pas** : il ne prononce ni Done technique ni Done pedagogique. Les mutations ont ete faites sur le worktree et restaurees par `git checkout` apres chacune (`git status` vide apres chaque lancement). Aucun fichier de code ou de contenu n'est modifie par le commit.
- **Perimetre** : (1) fusion du poste 5 (`d67204de`) et remplacement de l'assertion de la propriete 8 par le poste 3 (`7dc40fb0`, fusionne `65d00d8d`) ; (2) `Q-C1`. Les fusions des postes 4 et 6 ne sont pas relues ici.

## Resume

| Code | Constat | Etat |
|---|---|---|
| F1 | La propriete 8 amendee affaiblit un point que l'amendement ne demande pas : un distracteur **egal au verbe cite** passe desormais. L'ancienne assertion le refusait. Mutation `P5` : verte sur `f95736b5`, rouge sur `14d6cf47` | **ecart**, a `sofia-sm` pour `marc-dev` |
| F2 | Fusion du poste 5 : l'union des deux cotes est exacte, aucune assertion retiree ni affaiblie, resolution conforme a ce que `cloture-palier-1.md` section 4 annonce | **aucun ecart** |
| F3 | Comptes : catalogue 146, 147, 148, 148, 148 ; tests **2548** remesures par moi sur l'arbre fusionne | **confirmes** |
| F4 | Le garde-fou d'inventaire mord dans les deux sens (7 mutations) ; les gardes du paquet `ce1-accord-groupe-nominal` mordent (5 mutations). **La valeur `nombreDeManches: 10` n'est gardee par aucun test** (mutation `C8`) | F4 : non garde, dit |
| F5 | `Q-C1` : le test de vue du poste 8 peut tenir `alt`, `title`, `aria-label`, sous la forme de la section 4. **Une divergence a trancher** : l'exemption `aria-hidden` ne vaut pas pour `title` ni pour les noeuds de texte (Q1) | **reponse**, 2 questions |

## 1. Mesures

**Porte `build`** : `bash outils/mvn-jdk21.sh -B clean verify`, racine, sans `-pl`, sur `f95736b5`, branche `s57-yanis-relecture` avant tout ajout (arbre identique a `round-two`). Sortie : `BUILD SUCCESS`, `Total time: 11:24 min`, les six modules du reacteur `SUCCESS`.

| Module | Tests (ligne `Tests run` Surefire ou Failsafe) | Echecs, erreurs, ignores |
|---|---|---|
| domain | 120 | 0, 0, 0 |
| application | 31 | 0, 0, 0 |
| adapter-web | 486 | 0, 0, 0 |
| adapter-content | 1534 | 0, 0, 0 |
| bootstrap (Surefire) | 371 | 0, 0, 0 |
| bootstrap (Failsafe) | 6 | 0, 0, 0 |
| **Total** | **2548** | 0, 0, 0 |

- **Methode** : somme des six lignes ci-dessus, relues dans la sortie Maven, puis recomptees a part par l'attribut `tests` des fichiers `TEST-*.xml` de chaque module (meme total, 2548). **JDK 21.0.10**, valeur de `java.version` dans les 59 rapports Surefire de `bootstrap` (la JVM des tests, pas l'entree de toolchain).
- **Ce que ce total est** : le total de l'arbre `f95736b5`. **Je n'ai pas remesure** les totaux intermediaires de `cloture-palier-1.md` (2520, 2541, 2542) ni la base 2498 : je ne les confirme pas. Mon delta par rapport au total annonce par `sofia-sm` pour l'arbre final : 0 (2548 contre 2548).
- **Catalogue** : `git show <sha>:adapters/adapter-content/src/main/resources/catalogue.yaml` suivi de `grep -c "^  - id:"` : `6ca84317` 146 ; `c935d764` 147 ; `d67204de` 148 ; `14d6cf47` 148 ; `65d00d8d` 148 ; `f95736b5` 148. `grep -c "paquet: <nom>$"` sur `f95736b5` : `ce1-phrases-types` 1, `ce1-accord-groupe-nominal` 1, `cm1-sciences-extraire-information` 1, `ce1-verbe-sujet` 1.

## 2. Poste 5 : la fusion `d67204de`

### 2.1 Resolution du conflit relue

`d67204de` a pour parents `c935d764` (poste 4 fusionne) et `3459ddb8` (branche du poste 5). Methode : le diff de la fusion contre **chacun** de ses parents, sur les deux fichiers en conflit, doit egaler exactement ce que l'autre parent apporte.

| Fichier | `git diff c935d764 d67204de` | `git diff 3459ddb8 d67204de` | Apport propre du poste 5 (`72931c94`) | Apport propre du poste 4 (`1f6642ab`) |
|---|---|---|---|---|
| `catalogue.yaml` | 6 lignes ajoutees, 0 retiree : l'entree `bandit-manchot-accord-groupe-nominal-ce1` | 6 lignes ajoutees, 0 retiree : l'entree `bandit-manchot-phrases-types-ce1` | 6 + | 6 + |
| `TexteAccentueExposeParLePortEntrantTest.java` | 9 ajoutees, 0 retiree | 9 ajoutees, 0 retiree | 9 + | 9 + |

La fusion est l'union exacte : chaque parent a 0 ligne retiree par rapport a la fusion, et ce que la fusion ajoute a chacun est le contenu integral de l'autre. L'entree du poste 4 porte `nombreDeManches: 10` (lue dans la fusion, ligne partagee dans le conflit). Aucune assertion retiree, aucune borne modifiee.

### 2.2 Inventaire `TexteAccentueExposeParLePortEntrantTest`, juge dans les deux sens

Le test a deux sens : `touteChaineExposeeParLeCatalogueReelEstInventoriee` (toute chaine exposee est dans l'inventaire) et `chaqueChaineDeLInventaireEstEncoreExposeeParLeCatalogueReel` (toute entree de l'inventaire est encore exposee). Mutations lancees avec `bash outils/mvn-jdk21.sh -B -pl bootstrap -am test -Dtest='TexteAccentueExposeParLePortEntrantTest,Aucun*Test,Chaque*Test,CatalogueDeTest,LesJeuxRetires*Test' -Dsurefire.failIfNoSpecifiedTests=false` : 60 tests dans adapter-web, 180 dans adapter-content, 110 dans bootstrap au premier bilan complet.

| Code | Mutation | Motif trouve | Resultat |
|---|---|---|---|
| C1 | titre du poste 5 dans `catalogue.yaml` : `accordés` devient `accordes` | 1 | rouge, 4 echecs dont `chaqueChaineDeLInventaireEstEncoreExposeeParLeCatalogueReel` et `touteChaineExposeeParLeCatalogueReelEstInventoriee` |
| C2 | entree du poste 5 supprimee de `catalogue.yaml` | 1 | rouge, 1 echec dans bootstrap : `chaqueChaineDeLInventaireEstEncoreExposeeParLeCatalogueReel` ; lancement de tout adapter-content (1534 tests) : 1 echec, `PaquetCe1AccordGroupeNominalTest.laConsigneDuJeuEstCelleDesEntreesExistantes` |
| C3 | entree du poste 5 dupliquee sous un second identifiant | 1 | rouge, 1 echec : `AucuneEntreeDuCatalogueNEnDoubleUneAutreTest` |
| C4 | `nombreDeManches: 25` sur l'entree du poste 5 (24 items) | 1 | rouge, 1 echec : `ChaqueJeuDuCatalogueOffrePlusDItemsQueDeManchesTest` |
| C5 | `nombreDeManches` retire de l'entree du poste 4 | 1 | rouge, 21 erreurs de chargement dans adapter-content (la cle est obligatoire) |
| C6 | ligne d'inventaire du titre `Des mots bien accordés` retiree du test | 1 | rouge, 3 echecs dont `touteChaineExposeeParLeCatalogueReelEstInventoriee` |
| C7 | ligne d'inventaire de l'identifiant `bandit-manchot-phrases-types-ce1` retiree du test | 1 | rouge, 3 echecs dont `touteChaineExposeeParLeCatalogueReelEstInventoriee` |

- **Observation sur C1** : les 180 tests d'adapter-content lances (dont les `Aucun*Test`) restent verts, y compris le garde-fou de l'accent des mots servis. `accordes` est une forme existante (verbe accorder), le garde-fou ne la lit pas comme un accent retire. **L'inventaire est le seul garde de l'accent de ce titre.** Ce n'est pas un defaut, c'est la raison d'etre de l'inventaire.
- **C8, non garde** : `nombreDeManches: 5` a la place de `10` sur l'entree du poste 4 : `bash outils/mvn-jdk21.sh -B -pl adapters/adapter-content -am test -Dtest='Aucun*Test,Chaque*Test,Paquet*Test' -Dsurefire.failIfNoSpecifiedTests=false` : 1401 tests dans adapter-content, 0 echec. Aucun test ne fixe la valeur 10 ; seule la plage (cle presente, pas plus de manches que d'items) est gardee. La ligne que le conflit partageait est correcte dans la fusion (relue en 2.1), mais **une version fausse de cette ligne resterait verte**. A `camille-po` de dire si 10 est une exigence ; sinon rien a faire.

### 2.3 Paquet `ce1-accord-groupe-nominal` : mutations de mon choix

`bash outils/mvn-jdk21.sh -B -pl adapters/adapter-content -am test -Dtest=PaquetCe1AccordGroupeNominalTest -Dsurefire.failIfNoSpecifiedTests=false` : 21 tests. Mutations sur le YAML du paquet, restaurees apres chacune. Aucune ne reprend les trois de `rapport-sofia-dev.md`.

| Code | Mutation | Motif trouve | Resultat |
|---|---|---|---|
| Q1 | un indice passe a 13 mots (premier item) | 1 (premiere formulation : 2, ancre rendue unique avant lancement) | rouge : `trv5ChaqueIndiceCompteAuPlusDouzeMots...` |
| Q2 | un distracteur identique a la bonne reponse | 1 | rouge : `trv7LePaquetGardeSesVingtQuatreItemsEtChacunATroisPropositionsDistinctes` et `gna3...` |
| Q3 | distracteur `les petitx chats` (aucune famille) | 1 | rouge : `gna3...` |
| Q4 | distracteur `une petite fillette` (mot different) | 1 | rouge : `gna3...` |
| Q5 | bonne reponse de sept mots | 1 | rouge : `lon5ChaquePropositionTientEnSixMots...` et `gna3...` |

Les lignes *"non garde"* de `rapport-sofia-dev.md` (`K8`) restent vraies : je ne les ai pas contredites. Q2 montre toutefois que le doublon bonne reponse et distracteur est garde par `trv7` : la ligne *"une seule reponse juste par item"* est partiellement couverte (les propositions distinctes), pas le cas de deux graphies jugees equivalentes.

## 3. Poste 3 : l'assertion de la propriete 8 (`7dc40fb0`)

### 3.1 Inventaire des assertions, ancien contre nouveau

Sources : `git show 7dc40fb0 -- adapters/adapter-content/src/test/java/com/recreo/adapter/content/PaquetCe1VerbeSujetTest.java` et l'amendement du 2026-10-14 de `squad/conformite/ARBITRAGE-PREALABLE-s56-instance-a-francais-ce1.md`. Cas juge sur un item de sujet avec bonne reponse avant le verbe cite.

| Cas d'un item | Ancienne propriete 8 | Nouvelle propriete 8 et tests neufs | Sens |
|---|---|---|---|
| deux distracteurs apres le verbe | vert | vert | inchange |
| un distracteur avant, un apres | rouge | vert si complement en tete avec virgule et indice autre que celui de la place | **assoupli, voulu** (amendement) |
| deux distracteurs avant | rouge | rouge (`au plus un distracteur avant le verbe`) | inchange |
| aucun distracteur apres le verbe (hors phrase) | vert (rang -1 ignore) | rouge (`au moins un distracteur apres`) | **durci** |
| **un distracteur egal au verbe cite** | **rouge** (rang non strictement superieur) | **vert** | **assoupli, non demande** |
| un distracteur avant, sans virgule apres le complement | rouge | rouge (`vsjCe1R1LeDistracteurAvantLeVerbeOuvreLaPhraseParUneVirgule`) | inchange |
| moins de 3 items avec un distracteur avant | sans objet | rouge (`vsjCe1R1TroisItemsDeSujetAuMoinsOntUnDistracteurAvantLeVerbe`) | **durci**, borne exacte (voir P2) |
| indice de la place sur un item a distracteur avant | sans objet | rouge (`vsjCe1R1SurUnItemADistracteurAvantLIndiceNeDitPas...`) | **durci** |

Les assertions de la bonne reponse avant le verbe, de `J1`, de la propriete 4 et du plancher de formes de sujet sont inchangees (lues dans le diff : aucune ligne retiree dans ces blocs).

### 3.2 Mutations

`bash outils/mvn-jdk21.sh -B -pl adapters/adapter-content -am test -Dtest=PaquetCe1VerbeSujetTest -Dsurefire.failIfNoSpecifiedTests=false` : 28 tests, vert sur `f95736b5` avant toute mutation. Mutations sur `ce1-verbe-sujet.yaml`.

| Code | Mutation | Motif trouve | Resultat |
|---|---|---|---|
| P1 | item `Sur le toit, les oiseaux mangent...` : second distracteur `sur le`, soit deux avant le verbe | 1 | rouge, 2 echecs : `vsjCe12...AuPlusUnAvant` et `vsjCe1R1...OuvreLaPhraseParUneVirgule` |
| P2 | item `Le soir, elle pose...` : distracteurs `la table`, `le livre`, soit 2 items sur 3 avec un distracteur avant | 1 | rouge, 1 echec : `vsjCe1R1TroisItemsDeSujetAuMoinsOntUnDistracteurAvantLeVerbe` |
| P3 | `Sur le toit, les oiseaux` devient `Sur le toit les oiseaux` (virgule retiree) | 1 | rouge, 1 echec : `vsjCe1R1...OuvreLaPhraseParUneVirgule` |
| P4 | item `Le matin, un grand bus...` : indice remplace par `Le sujet est avant le verbe : regarde les mots devant.` | 1 | rouge, 1 echec : `vsjCe1R1SurUnItemADistracteurAvant...` |
| **P5** | item `Elles rangent les jouets dans le sac.` : distracteurs `rangent`, `le sac` (le verbe cite lui-meme) | 1 | **vert, 28 tests, 0 echec** |

**Controle de P5 sur l'arbre d'avant** : meme mutation, meme commande, worktree detache sur `14d6cf47` (premier parent de `65d00d8d`, sans le poste 3) : 22 tests, **1 echec**, `vsjCe12LeSujetEstAvantLeVerbeCiteEtLesDeuxDistracteursApres`. Le meme texte devient donc vert apres le poste 3. Ce worktree jetable est supprime.

### 3.3 F1 : ce qui est affaibli, et ce que cela couvre

- **Le texte de l'amendement** (*"au moins un distracteur est apres le verbe ... et au plus un est avant"*) ne dit rien du cas rang egal. Le code compte `avant` par `rang < rangDuVerbe` et `apres` par `rang > rangDuVerbe` : un distracteur au meme rang que le verbe n'est ni l'un ni l'autre.
- **Ce que la condition ecrite exige** : `VSJ-CE1-3` (arbitrage de s56, ligne 82) : *"(a) le groupe apres le verbe et (b) un autre nom de la phrase"*. Le verbe n'est ni l'un ni l'autre. Avant le poste 3, l'ancienne propriete 8 refusait ce cas par effet de bord, et aucun autre test du paquet ne le refuse (P5 vert).
- **Portee** : aucun item actuel ne declenche le cas (script de poste sur le YAML : 15 items de sujet sur 24, aucun distracteur egal au verbe cite). C'est une garde qui n'existe plus, pas un defaut de contenu.
- **A faire, a `marc-dev` via `sofia-sm`** : refuser un distracteur au rang du verbe cite (par exemple `rang == rangDuVerbe` compte comme une erreur nommee `VSJ-CE1-3`), avec un jeu fautif. Je ne l'ecris pas : ce palier m'interdit de modifier du code.
- **Deux limites de l'amendement, ecrites et non des ecarts** : l'indice de la place n'est refuse que sous sa forme exacte (`isNotEqualTo`) ; *"de temps ou de lieu, jamais une personne"* n'est pas decidable par le test et reste a `nadia-prof` (l'amendement le dit lui-meme, point 1).

## 4. `Q-C1` : le test de vue du poste 8 peut-il tenir `D7` sur `alt`, `title`, `aria-label` ?

**Oui, sous la forme ci-dessous.** Je n'ecris pas le test du poste 8. Ce qui suit est la forme qui tient et les conditions pour qu'elle morde.

### 4.1 Forme

1. **Rendu** : l'ecran de fin est rendu par le controleur reel et le gabarit reel (`MockMvc`, `Jsoup.parseBodyFragment` sur la reponse), comme `PartieControllerPropositionsEnImageTest`. Le corps de la reponse est le fragment `retour` seul : la racine du test est ce corps, donc le `<title>` d'une page complete n'est pas en cause.
2. **Textes** : tous les noeuds de texte non blancs de la racine (`select("*")` puis `textNodes()`), compares **un a un** a la liste fermee de `D7` (`Les jeux`, `Tu as trouvé, tout seul :`, `Tu as joué toutes les questions :`, les deux nombres du score, `Bravo, c'est juste !`, `Tu n'as pas trouvé.`, `C'est fini !`). Les deux nombres se comparent a `score.reussites()` et `score.tentatives()` du modele de la partie jouee, pas a un motif de chiffres. Chaines exactes : le gabarit actuel emploie des espaces simples avant `:` et `!` (lu dans `retour.html`, lignes 44 a 62) ; un passage a l'espace insecable rougira, ce qui est voulu (c'est un texte lu par un enfant).
3. **Attributs** : `select("[alt],[title],[aria-label]")` ; pour chaque element hors `aria-hidden`, chacune des trois valeurs est vide ou appartient a la meme liste. Exemption : `e.closest("[aria-hidden=true]") != null` (l'element lui-meme ou un ancetre). Alternative equivalente : `:is([alt],[title],[aria-label]):not([aria-hidden=true] *, [aria-hidden=true])`.
4. **Mesure faite de ces deux formes** : jsoup 1.23.1 (version du POM parent), programme autonome hors depot : les deux selecteurs rendent les 4 elements attendus sur un document de 6 elements portant l'un des trois attributs, dont 2 sous `aria-hidden` ; la forme des textes et des attributs rend 0 sortie sur un ecran conforme (structure de `retour.html`) et releve `alt=celle-ci`, `aria-label=les deux`, `title=l'autre` sur un ecran mute, sans relever ceux de l'icone `aria-hidden`. **Non mesure de bout en bout** : le test du poste 8 n'existe pas, et je n'ai pas branche la forme sur le controleur.

### 4.2 Conditions pour qu'elle morde

- **Donnees reelles** (`ADR-0013`, amendement du 2026-08-06). La fixture de `PartieControllerPropositionsEnImageTest` porte `Enonce.` en enonce et `Optional.empty()` en indice et en explication : sur elle, une mutation qui remet l'explication ou l'indice sur l'ecran de fin resterait verte. Le paquet `cp-rapprocher-deux-oeuvres.yaml` porte enonce, indice, explication et `illustrationTexteAlternatif` sur ses 6 items. **Ce paquet n'est pas dans `catalogue.yaml`** (`grep` sur `rapprocher` : aucune entree) : un test qui derive du catalogue verrait zero jeu. Precedent : `LExplicationDUnItemReelAtteintLEcranDeRetourTest` lit le paquet reel par `Yaml` dans `bootstrap` et le sert par un `@WebMvcTest`.
- **Population derivee, pas nommee** : les items dont la bonne reponse porte le prefixe `oeuvre-` (regle de `retourModele`, `PREFIXE_DE_REPRODUCTION`, `ADR-0104`), derives du contenu lu, avec une assertion que la population n'est pas vide (sinon vert vide).
- **Les deux issues** : l'ecran de fin juste et l'ecran de fin revele, par jeu (le gabarit les branche par `th:if` sur `juste`). Le controleur (`retourModele`, `PartieController` lignes 856 a 861) pose `explication` sur toute fin et `questionRappeleeVisible = !juste` ; **le test sera donc rouge sur l'arbre actuel avec ces donnees** : l'explication est rendue sur les deux issues (`retour.html` ligne 104), l'enonce rappele sur la revelation (ligne 96). C'est le comportement voulu : le poste 8 le rend vert par la voie (b) de `D6`. Je le verrai rouge avant de le voir vert.
- **Gardes de non-vacuite** : l'ecran contient `.fin-de-partie`, et les noeuds de texte releves sont au moins ceux de la liste attendue ; l'item joue porte un enonce, un indice, une explication non vides.
- **Mutations a faire rougir, que je jouerai au review du poste 8** : enonce rappele ; explication sur l'ecran ; `aria-label` portant l'enonce sur `.signal` ; `alt` portant un texte d'item sur un `<img>` ; `title` portant un texte d'item sur `.verdict` ; texte d'item dans un element `aria-hidden`. Aucune n'est jouee aujourd'hui : l'ecran du poste 8 n'existe pas.

### 4.3 Deux points que la forme ne tranche pas

- **Q1 (a `nadia-prof`, via `sofia-sm`)** : l'exemption `aria-hidden` de `D7` couvre-t-elle `title` et les noeuds de texte ? `aria-hidden` retire un element du lecteur d'ecran, pas de l'oeil, et un `title` s'affiche au survol pour un enfant qui voit. Un `title` sous `aria-hidden` serait invisible au test si l'exemption est large. **Ma forme par defaut est la plus stricte : exemption pour `alt` et `aria-label` seulement, aucune pour `title` ni pour les noeuds de texte.** Elle ne produit aucun faux rouge aujourd'hui (l'icone `aria-hidden` n'a ni texte ni attribut). Si `nadia-prof` veut l'exemption large, c'est une decision a ecrire.
- **Q2 (a `nadia-prof`)** : `D7` ne nomme que trois attributs. D'autres portent un texte qu'un enfant ou un lecteur d'ecran recoit : `aria-description`, `aria-roledescription`, `aria-labelledby` et `aria-describedby` (renvoient a un autre texte), `placeholder`, `value`. Un `<img>` sans `alt` expose son nom de fichier a certains lecteurs d'ecran. Aucun n'est present dans `retour.html` aujourd'hui. Je ne les ajoute pas sans sa reponse.

## 5. Jobs de `ci.yml` lances, avec leur sortie

Arbre `f95736b5` plus ce fichier. Chaque ligne est une etape du job de ce nom dans `.github/workflows/ci.yml`.

- **`build`** : etape *Verify* : `bash outils/mvn-jdk21.sh -B clean verify` (le workflow lance `mvn -B verify` ; l'enveloppe lance le meme Maven sur le JDK 21 de la toolchain), `BUILD SUCCESS`, 2548 tests, 0 echec, 0 erreur, 0 ignore (section 1). **Etape *Package du fat jar* : non lancee.**
- **`prefixes`** : `verifier-prefixes-test.sh`, sortie 0 (*"Tous les cas passent"*) ; `commit-atteignable-test.sh`, sortie 0 ; `git log --no-merges --format=%s 6ca84317..f95736b5` dans `verifier-prefixes.sh`, 12 sujets, sortie 0 (*"Tous les commits portent un prefixe d'agent connu."*) ; `verifier-garde-fou-accent-test.sh`, sortie 0 (21 cas) ; `verifier-garde-fou-accent.sh "6ca84317..f95736b5"`, sortie 0 (*"0 commit(s) le touchent dans la plage, tous [THEO]"*). La base de la plage est choisie par moi, `github.event.before` n'existant pas en local : la CI distante n'est pas lue.
- **`outils`** : les trois motifs du workflow, 11 juges purs executes (5 + 2 + 4), tous sortie 0.
- **`chemins`** : `verifier-chemins-test.sh`, sortie 0 (*"Tous les cas passent"*) ; `git ls-files` (moins le test du garde-fou, 2813 fichiers, ce fichier suivi dans l'index) dans `verifier-chemins.sh`, sortie 0 (*"Aucun chemin de poste de travail ni nom de compte dans les fichiers verifies."*).
- **Hors jobs, meme arbre** : `AucuneLigneDeTableauNePortePlusDeCellulesQueSonEnTeteTest` (`-pl bootstrap -am`), 5 tests, 0 echec, ce fichier dans l'index.

## 6. Limites

- Les totaux intermediaires de `cloture-palier-1.md` (2520, 2541, 2542, 2498) ne sont pas remesures par moi.
- Aucun jar n'a ete lance, aucun port ouvert. Pas de lecture de la CI distante.
- Les mutations du paquet `ce1-accord-groupe-nominal` ne couvrent pas la justesse pedagogique de chaque marque : c'est a `nadia-prof` seule.
- `Q-C1` : la forme est mesuree sur des selecteurs et un document fabriques, pas sur le controleur.

## 7. Questions

- **Q1** (a `nadia-prof`) : exemption `aria-hidden` pour `title` et les noeuds de texte (4.3).
- **Q2** (a `nadia-prof`) : extension de `D7` a `aria-description`, `aria-roledescription`, `aria-labelledby`, `aria-describedby`, `placeholder`, `value` (4.3).
- **Q3** (a `sofia-sm`, pour `marc-dev`) : F1, garde du distracteur au rang du verbe cite sur `PaquetCe1VerbeSujetTest`.
- **Q4** (a `camille-po`) : `nombreDeManches: 10` est-il une exigence a garder par un test (F4, C8) ?

## Decisions prises

**Aucune `ADR` ni `DEC` n'est creee ou amendee par ce fichier.**
