# Avis de faisabilite - l'achevement du palier 2 (`theo-archi`)

> **Le nom de ce repertoire appartient a `sofia-sm`.** `s32-instruction-prealable` nomme le moment ou
> ce fichier est ecrit - avant tout planning et avant toute decision de perimetre - et **pas** le
> contenu du sprint. Si elle nomme le sprint autrement, le fichier suit le repertoire et rien
> d'autre ne change.

> **Ce que cet avis ne fait pas.** Il ne decide aucun perimetre de sprint, il n'ecrit aucun paquet
> de contenu, il ne pose aucune condition pedagogique, il ne prononce aucun Done et il ne deplace
> aucune ligne de matrice. Il n'ecrit ni dans `squad/decisions/produit/` ni dans
> `docs/backlog/backlog.md`, ou `camille-po` travaille en parallele. L'utilisatrice tranche.

---

## 0. Ce sur quoi tout ce qui suit a ete mesure

**Worktree dedie `s32-theo`, cree sur `f503b4e5`** (`git worktree add ../recreo-s32-theo -b s32-theo
f503b4e5`), lignee verifiee. Toute mesure de cet avis est faite depuis ce worktree et depuis aucun
autre ; les worktrees des autres agents sont des repertoires freres et ne sont jamais balayes.

**JDK 21.0.10**, version **rapportee par la machine virtuelle qui execute les tests** et lue dans la
sortie de demarrage de Spring Boot du journal de build, jamais l'entree de toolchain qui la designe
(`ADR-0071`).

**Chaque chiffre de ce document porte sa methode dans le meme paragraphe.** Les mesures partielles
du reacteur portent `-am` et nomment leur module.

---

## 1. Etat de la porte technique, avant de parler du palier 2

**J'ai lance les quatre jobs de `.github/workflows/ci.yml` sur l'arbre de mon worktree**, et je les
nomme un par un plutot que d'annoncer une porte que je n'aurais pas mesuree (`CLAUDE.md`,
amendement du 2026-09-08).

| Job | Commande lancee | Verdict |
|---|---|---|
| `chemins` | `bash .github/scripts/verifier-chemins-test.sh` puis `git ls-files -- . ':!.github/scripts/verifier-chemins-test.sh' \| bash .github/scripts/verifier-chemins.sh` | **vert** |
| `prefixes` | `bash .github/scripts/verifier-prefixes-test.sh` et `bash .github/scripts/commit-atteignable-test.sh` (l'etape de verification elle-meme lit le contexte GitHub d'un push et n'est pas reproductible en local telle quelle - je le dis plutot que de l'annoncer verte) | **vert sur ses deux garde-fous** |
| `outils` | le balayage de l'etape, sur les trois motifs du workflow | **vert, 9 juges purs** |
| `build` | `bash outils/mvn-jdk21.sh -B clean verify` sur la racine | **ROUGE** |

**Le rouge n'est pas de mon diff, et il n'est pas du palier 2.** Le worktree etait vierge quand je
l'ai lance : un echec sur les 218 tests surefire de `recreo-bootstrap`,
`ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest.leDepotReelEstCoherent`, qui refuse deux
identifiants ecrits par `f503b4e5` -
`squad/conformite/ARBITRAGE-PREALABLE-s32-trois-instances-ma-borne-a-douze-le-terrain-sans-image-et-le-prix-d-une-ligne-marginale.md`
nomme **deux identifiants de condition de prefixe `PAR` que `squad/decisions/REGISTRE-identifiants.md`
n'alloue pas** - il s'arrete a `PAR-11`. C'est exactement ce qu'`ADR-0085` clause 2 interdit, et le
garde-fou fait son travail.

**Je n'ecris pas les deux identifiants ici, et le motif est une propriete du garde-fou qui merite
d'etre nommee.** Son perimetre est **tout le depot** : un identifiant non alloue rend rouge
**chaque** fichier qui l'ecrit. Un rapport qui citerait les deux verbatim rejoindrait donc la liste
des fautifs - **le rouge est contagieux par citation**. Ce n'est pas un defaut du garde-fou, c'est
la consequence exacte de sa clause 2, et le registre a d'ailleurs une section d'homonymes plafonnee
pour les cas ou une citation est legitime. Ici la bonne reponse est l'allocation, pas une exemption :
le fichier fautif est identifie sans ambiguite, et les deux identifiants s'y lisent.

**Je ne le corrige pas** : allouer un identifiant demande de savoir ce qu'il nomme, et ces deux-la
sont les proprietes de `nadia-prof`. **Je le route a `nadia-prof`, avec copie a `sofia-sm`** : deux
lignes a ajouter au tableau du registre, et la porte technique redevient mesurable.

**Ce que ce rouge coute a cet avis, dit franchement** : la phase `verify` s'est arretee sur cet echec,
donc **les tests d'integration failsafe (`*IT`) ne se sont pas executes du tout**. Aucun chiffre de
cet avis ne repose sur eux.

**1396 tests surefire**, methode dans le meme paragraphe : somme des cinq totaux par module du
journal de `bash outils/mvn-jdk21.sh -B clean verify` sur la racine - `recreo-domain` 99,
`recreo-application` 13, `recreo-adapter-web` 388, `recreo-adapter-content` 678,
`recreo-bootstrap` 218. **Ce n'est pas le total du depot** et il n'est pas comparable aux 1402 de la
cloture de s31 : failsafe n'a pas tourne, et deux moteurs JUnit ne rendent pas un rapport unique.

**33 `ArchRule`**, methode : motif `static final ArchRule` sur les arbres de sources des cinq
modules, `/target/` exclu, et le faux module de contre-exemple de `LesGardeFousSontMisALEpreuveTest`
exclu - il en declare 2, ce qui explique qu'un comptage naif rende 35. **Concordant par un second
chemin** : `LesReglesMordentTest` execute 33 tests, mesure partielle
`mvn -pl bootstrap -am -Dtest=LesReglesMordentTest`, module `recreo-bootstrap`. C'est exactement le
compte qu'`ADR-0080` annoncait pour l'apres-lot ; ses clauses 10 et 11 sont dans l'arbre.

### 1.1 Les quatre jobs relances sur mon propre diff, avant de commiter

C'est le geste qui manquait a s30 et a s31, et il est fait ici plutot que promis.

- **`chemins`** : **vert**. Garde-fou teste, puis `git ls-files` de tout le depot passe dans
  `verifier-chemins.sh` - *"Aucun chemin de poste de travail ni nom de compte dans les fichiers
  verifies."*
- **`prefixes`** : **vert**. Les deux garde-fous testes, et mon message de commit passe dans
  `verifier-prefixes.sh` - *"Tous les commits portent un prefixe d'agent connu."*
- **`outils`** : **vert**, 9 juges purs.
- **`build`** : **rouge, et du meme unique echec qu'avant mon diff.**
  `bash outils/mvn-jdk21.sh -B clean verify` sur la racine, reacteur complet : quatre modules verts
  (`recreo-domain` 99, `recreo-application` 13, `recreo-adapter-web` 388, `recreo-adapter-content`
  678), `recreo-bootstrap` **224 tests, 1 echec**. L'echec est
  `ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest.leDepotReelEstCoherent`, et **son message ne
  nomme qu'un seul fichier fautif, celui de `f503b4e5`** - aucun des miens.

**218 tests avant mon diff, 224 apres : les six que la regle de la section 6.5 apporte, et pas un
echec de plus.** Verts dans ce meme journal :
`ChaqueMecaniqueDuDomaineEstAtteignableDepuisLeProduitTest` 6,
`AucuneLigneDeTableauNePortePlusDeCellulesQueSonEnTeteTest` 5 (`ADR-0087`, les tableaux de
`ADR-index.md` que j'ai edites), `ToutesLesReferencesDeDecisionResolventTest` 3,
`LesGardeFousSontMisALEpreuveTest` 10, `LesReglesMordentTest` 33, et le cliquet de designation
d'`ADR-0080` 6.

**Je n'annonce donc pas la porte technique verte** : elle ne l'est pas, pour un motif qui n'est ni le
mien ni celui du palier 2, et qui se solde par deux lignes dans le registre.

---

## 2. Le constat qu'on m'a demande de verifier : confirme sur l'essentiel, corrige sur quatre points

**Confirme, et c'est le fait qui gouverne tout le reste : personne ne peut jouer le palier 2, et ce
qui manque est le chemin de jeu, pas des items.**

- `domain` porte la mecanique complete : `Designation` (48 lignes), `DesignationSession` (251),
  `DesignationMove` (11), `DesignationVerdict` (18), plus `Zone` (12), `ZoneADesigner` (72) et
  `CadreDeZone` (47) dans le contrat de contenu, et `DesignationTest`. Methode : `wc -l`.
- `adapter-content` porte le chargement : `DesignationContentSourceFactory`, enregistree dans le
  registre de `YamlContentPackLoader` sous la cle `designation`, plus les deux garde-fous nommes
  designation de ses tests.
- **Catalogue : 66 entrees, 64 `bandit-manchot`, 2 `saisie`, zero `designation`.** Methode :
  `grep -c "^  - id:"` et `grep -o "mecanique: .*" | sort | uniq -c` sur
  `adapters/adapter-content/src/main/resources/catalogue.yaml`. Le compte de `nadia-prof` est exact.
- `ADR-0080` est bien au statut **Propose**.

**Correction 1 - `application` n'a rien a recevoir. Zero ligne de production.** Le constat parle de
trois couches manquantes ; il y en a deux. `PartieUseCase` expose `demarrer(String jeuId)` et
`jouer(String jeuId, GameSession session, Move coup)` : le port est deja polymorphe sur le contrat, il
ne nomme aucune mecanique, et `PartieService` resout la mecanique par `MechanicRegistryPort` a partir
du `MechanicId` de l'entree de catalogue. **Un port ou un cas d'usage de plus dans `application`
serait une piece mobile qu'aucun usage constate ne demande.** Le module entier fait 174 lignes de
production ; il en fera 174 apres le palier 2.

**Correction 2 - `EtatDePartieEnSession` et `EtatDePartie` n'ont rien a recevoir non plus.** Le
constat annonce une descente dans `EtatDePartieEnSession` ; il n'y en a pas et il ne peut pas y en
avoir. Les deux types sont **types sur `GameSession`**, et `jouer` recoit le calcul du coup en
`UnaryOperator<GameSession>`. Ce n'est pas un hasard : c'est `ADR-0058` R-A, et la clause ArchUnit 9
(`adapterWebNeNommeAucuneMecaniqueHorsDeSonPointDeDescente`) **interdit** a ces deux fichiers de
nommer une mecanique. Y descendre rendrait la clause 9 rouge.

**Correction 3 - il n'y a pas de "gabarits plus les fragments homonymes".** `templates/` porte
**six fichiers** en tout : une page, `accueil.html` (128 lignes), et cinq fragments,
`fragments/confirmation.html` (68), `erreur.html` (20), `manche.html` (165), `retour.html` (78),
`saisie.html` (128) - 587 lignes au total. Methode : `wc -l`. Aucun gabarit n'existe en double.

**Correction 4 - le CSS du palier 2 existe deja, et il n'est pas un residu.**
`.image-designation { max-height: 22rem; }` est dans `recreo.css` avec l'arbitrage d'`ines-design`
sur la taille d'image et son arithmetique. **C'est la dependance externe qu'`ADR-0080` declarait
manquante pour T4.** Elle ne manque plus.

---

## 3. Le compte de lignes : je confirme 10 par le motif, et j'en trouve une onzieme que le motif ne pouvait pas voir

**Methode, puisque le compte vient d'un recompte a la main et non d'un document** :
`grep -c "palier 2"` sur `squad/programme/MATRICE-couverture.md` rend **12 occurrences**. L'une
(ligne 1415) est de la prose de section, pas une ligne de tableau. **Restent 11 lignes de matrice**,
dont **1 est `non couvrable en l'etat`** - education physique et sportive CM1, *"suivre un
itineraire a l'aide d'un plan"*, dont la cellule dit elle-meme *"recouvrement a ne pas
double-compter avec histoire-geographie CM1"*. **Les 10 autres sont `non couvert`, et la repartition
par matiere de `nadia-prof` est exacte** : 3 questionner le monde CP, 2 histoire-geographie CM1,
1 arts plastiques CM1, 2 histoire des arts CM1, 2 sciences et technologie CM1.

**Ce qui n'est pas exact, et ce n'est pas un detail de forme** : le constat dit que les 10 *"portent
toutes le motif 'palier 2 requis'"*. **Quatre lignes seulement portent cette chaine**
(`grep -c "palier 2 requis"` rend 4). Les six autres nomment le palier 2 dans une autre formulation.
Un compte fonde sur un motif de texte qui ne vaut que pour 4 de ses 10 membres n'est pas
reproductible, et c'est ce qui a permis a l'ecart suivant d'exister.

**L'onzieme ligne, et le motif pour lequel aucun recompte par motif ne pouvait la trouver.**
`| mathematiques | CP | Espace et geometrie | Se reperer et decrire des deplacements sur un
quadrillage simple | - | - | non couvert |` : la cellule de statut porte **`non couvert` et rien
d'autre**. Pas de motif, pas de mention du palier 2, pas de renvoi. Elle est donc invisible a
`grep "palier 2"`, et pourtant c'est **la ligne 1 des six** que l'arbitrage prealable de
`nadia-prof` du 2026-09-05 instruit
(`ARBITRAGE-PREALABLE-s29-le-palier-2-ce-qu-un-paquet-de-designation-doit-apporter.md`, section 4.1),
avec ses deux dessins minimum, son plafond de grille a 4 x 4 et sa mention exigee d'avance.

**Cet ecart n'est pas neuf : `ADR-0080` l'avait signale le 2026-09-03** - *"avec un ecart signale :
la ligne du quadrillage ne porte pas son motif dans sa cellule"*. Il est toujours la cinq jours et un
sprint plus tard. **Je le route a `camille-po`**, qui tient la matrice : ce n'est pas un deplacement
de ligne, c'est une cellule de motif a remplir, et je ne touche pas la matrice.

**Deuxieme lecture, et elle change l'arithmetique du rendement sans que je la tranche.** Les 10
lignes ne sont pas homogenes. Cinq nomment le palier 2 comme **la seule** condition manquante -
`QLM-CP-11` (plan simple), `QLM-CP-12` (carte ou globe), `HG-CM1-09` (carte et planisphere),
`HG-CM1` (situer son lieu de vie) et histoire des arts CM1 (se reperer dans un musee). Les cinq
autres portent une moitie **deja jouable en texte** et reservent au palier 2 la seconde moitie :
`QLM-CP-14` (le placement sur une frise), arts plastiques CM1 (la composition et le cadrage),
histoire des arts CM1 (le placement sur une frise), et deux lignes de sciences et technologie CM1 -
dont l'une ecrit noir sur blanc *"designer un organe sur un schema serait du palier 2, **non
exige**"*.

**Ce que cela veut dire, et ce que je ne dis pas.** Le palier 2 **touche** 10 lignes `non couvert`
(11 avec le quadrillage). Combien il en **ferme** n'est pas une question d'architecture : la vue
d'ensemble de `nadia-prof` repond deja *"oui"*, *"non - mention exigee"* ou *"sous condition de
taille d'image"* ligne par ligne, et elle est la seule autorite la-dessus. **Je nomme l'ecart entre
les deux comptes plutot que de le laisser se resoudre par le silence, et je le route a
`nadia-prof`.**

---

## 4. Ce qui reste exactement a ecrire

**T1, T2, T3 et T5 d'`ADR-0080` sont livres.** T4 et T6 restent. Voici T4 fichier par fichier, avec
la taille du geste la ou je peux la mesurer.

### 4.1 `application` : rien

Voir la correction 1. **Zero fichier, zero ligne.**

### 4.2 `bootstrap` : un bean, quatre lignes

`bootstrap/src/main/java/com/recreo/bootstrap/config/MecaniquesConfiguration.java` : un
`@Bean GameMechanic designation() { return new Designation(); }` et son import.
`ContenuConfiguration` n'a rien a recevoir - le registre des fabriques de paquet vit dans
`YamlContentPackLoader` et connait deja la cle `designation`.

### 4.3 `adapters/adapter-web` : la troisieme branche du point de descente, et un fragment

**`PartieController.java`** - 700 lignes, 41 187 octets. Il recoit :

1. une route `@PostMapping("/parties/{jeuId}/designations")` avec `@RequestParam String zoneDesignee`,
   qui construit un `DesignationMove` et passe par `PartieUseCase.jouer`, sur le patron exact de
   `repondre()` (10 lignes) ;
2. une troisieme branche dans `mancheModele(...)`, `if (session instanceof DesignationSession ...)`
   (3 lignes) ;
3. une methode `mancheDeDesignation(...)`, homologue de `mancheDeSaisie` (60 lignes) et de
   `mancheDeBanditManchot` (78 lignes) ;
4. une methode `retourDeDesignation(...)`, homologue de `retourDeSaisie` (12 lignes).

**`fragments/designation.html`** - fragment neuf, homologue de `fragments/saisie.html` (128 lignes) :
l'image, les boutons de zone positionnes par le cadre que le contenu declare, le message, l'indice,
la revelation, le bloc adulte.

**`recreo.css`** - les regles de position d'un bouton de zone sur la boite de l'image. Le plafond de
taille d'image existe deja (correction 4) ; ce qui manque est le positionnement.

### 4.4 `adapter-content` : une entree de catalogue

`catalogue.yaml` : une entree `mecanique: designation`, plus le paquet YAML lui-meme, qui est T6.

### 4.5 Deux trous que ni `ADR-0080` ni le constat ne nomment, et que T4 ne peut pas eviter

**Trou A - `DesignationVerdict` ne porte pas d'enonce, et l'ecran de fin de partie en rend un.**
`fragments/retour.html` rend `${enonceRevele}`, pose par le controleur depuis le verdict. `Verdict`
(bandit manchot) et `SaisieVerdict` portent tous deux un champ `enonce`, obligatoire, par `ADR-0084`
REV-3 - *la reponse d'une fin de partie voyage avec la question qu'elle commente*.
`DesignationVerdict` est `(boolean juste, String bonneZone, Optional<String> explication)` : **pas
d'enonce**. A cet instant la session n'a plus de manche courante, donc le verdict est la seule trace
possible de l'item joue. **C'est un residu de T2 dans `domain`, pas un travail d'adaptateur**, et il
faut le savoir avant T4 plutot que le decouvrir a la review.

**Trou B - `bonneZone` est un identifiant, et `retour.html` en fait un texte lu par un enfant.**
`fragments/retour.html` rend `${bonneReponse}` dans `<p class="reponse-attendue">`.
`DesignationVerdict.bonneZone()` et `DesignationSession.bonneZoneRevelee()` rendent un `Zone.id()` -
la valeur que le formulaire renvoie, jamais un libelle : `Zone` distingue explicitement `id` de
`libelle`, et `ADR-0080` decision 4 l'exige. Servir cet ecran en l'etat afficherait un identifiant
technique a l'enfant. **Ce qui est de moi** : le verdict doit transporter quelque chose de
presentable, ou bien la designation doit avoir son propre fragment de fin de partie ; les deux sont
faisables et la premiere est moins chere. **Ce qui n'est pas de moi et que je route a `nadia-prof`** :
si l'enfant doit voir le libelle de la zone attendue, ou autre chose, ou rien -
`ADR-0080` section 8 lui laisse deja *"le libelle d'une zone, sa formulation"*.

### 4.6 Le decoupage de `PartieController` : ce n'est pas une option, c'est un interdit

**On me demande si la troisieme mecanique entre dans un fichier de 41 Ko ou si elle force un
decoupage. Elle y entre, et elle doit y entrer.**

`DecouplageMecaniqueContenuTest` clause 9 est ecrite ainsi :
`POINT_DE_DESCENTE = "com.recreo.adapter.web.PartieController"`, **un nom pleinement qualifie et non
un motif**, et sa Javadoc dit pourquoi : *"un motif laisserait entrer un second point de descente le
jour ou quelqu'un nommerait sa classe de la meme famille, et R-A tient precisement a ce que ce point
soit un et qu'il soit nomme"*. Extraire `mancheDeDesignation` dans un `MancheDeDesignation.java` ou
un `DesignationController.java` d'`adapter-web` rend **la clause 9 rouge**, immediatement.

**Donc : le cout est que `PartieController` grossit d'environ 85 lignes** (les quatre gestes de 4.3),
soit de l'ordre de 785 lignes apres T4. **C'est un cout, pas une amelioration a saisir en passant.**
Sa Javadoc dit deja ce qu'il faudra pour en sortir : *"Deux branches nommees plutot qu'une
abstraction de dispatch : une abstraction ecrite pour deux cas est une abstraction ecrite pour un
usage imagine, et theo-archi recommande cette forme-la tant que deux mecaniques existent"*, et
clause 9 ajoute *"le jour d'une troisieme mecanique, c'est la forme qui change, pas cette regle"*.

**Mon avis, et le declencheur que je m'ecris a moi-meme.** A trois branches, une abstraction de
dispatch cesse d'etre imaginee. **Mais je refuse de la faire dans le meme geste que T4** : elle
demanderait d'amender `ADR-0058` R-A et la clause 9, donc un `ADR`, et melanger un `ADR`
d'architecture avec le premier lot qui rend une mecanique jouable est la maniere de rater les deux.
**Ce qui me fera l'ouvrir** : que la troisieme branche, une fois ecrite, se lise comme les deux
autres a plus de la moitie de ses lignes. Cela se constate sur le diff de T4, pas avant. Tant que ce
n'est pas constate, trois branches nommees dans un fichier de 785 lignes valent mieux qu'une
abstraction prematuree adossee a un `ADR` d'amendement.

---

## 5. Les clauses d'`ADR-0080` qui deviennent dues, et celles qui restent a population vide

**Etat des huit regles de la section 6 d'`ADR-0080`, verifie fichier par fichier.**

| Regle | Etat au 2026-09-08 | Ce qui la rend due |
|---|---|---|
| Clause ArchUnit 10 `uneMecaniqueIgnoreLaGeometrie` | **ecrite, verte** (`DecouplageMecaniqueContenuTest`, ligne 175) | livree avec T1 |
| Clause ArchUnit 11 `aucunRecordDuContratDeContenuNeDeclareDeMethode` | **ecrite, verte** (ligne 208) | livree avec T1 |
| Garde-fou 1 `ChaqueZoneDesignableEstUnBoutonDuFormulaireDeCoupTest` | **n'existe pas** | **due avec T4** : sa population est l'ecran de manche reellement servi, qui n'existe pas |
| Garde-fou 2 amendement de `FrontieresDuJavaScriptTest.INTERDITS_JS` | **n'existe pas** | **due avec T4**, mais **ecrivable et verte des maintenant** : sa population est le JavaScript maison, qui existe (543 octets) et ne contient aucun des sept mots |
| Garde-fou 3 `AucuneZoneDesignableNeDescendSousLaCibleTactileTest` | **n'existe pas** | **due avec T4** ; ses deux termes existent tous les deux maintenant - le plancher `CIBLE_TACTILE_REM` et le plafond `.image-designation` |
| Garde-fou 4 `LaBonneZoneNEstPasPredictibleSurUnPaquetTest` | **ecrit, mordant sur fixture** | **son balayage de production est du avec T6** |
| Garde-fou 5 `AucunEnonceDeDesignationNeNommeLeLibelleDeSaBonneZoneTest` | **ecrit, mordant sur fixture** | **son balayage de production est du avec T6** |
| Garde-fou 6 `ChaqueCleFacultativeDItemEstLueParLaFabriqueDeSonTypeTest` | **ecrit, mordant sur fixture** | **son balayage de production est du avec T6** |

**La regle du garde-fou 2 est la seule qui peut etre ecrite en avance, et elle doit l'etre.** Son
objet - la liste d'interdits du JavaScript maison - existe et est non vide ; `ADR-0051` est
satisfait ; et `ADR-0080` a deja verifie les sept mots **sur le materiau reel et non sur une
fixture**. Une regle ecrite le jour ou elle gene est une regle qu'on negocie.

**Les garde-fous 1 et 3 restent a population vide, et `ADR-0051` interdit de les ecrire avant T4.**
Leur objet est le **document servi** d'un ecran de designation. Ecrire aujourd'hui un balayage sur
une population de zero ecran, c'est exactement ce qu'`ADR-0051` rend rouge. Ils ne sont pas
"remis a plus tard" : ils sont **indissociables du geste qui cree leur objet**, et un lot T4 qui les
omettrait serait incomplet, pas en retard.

**Est-ce que `ADR-0080` passe a `Accepte` par ce lot ? Non, pas par T4 seul.** Ce qu'il faut, et je
l'ecris comme critere et non comme intention :

1. **T4 livre**, avec les garde-fous 1, 2 et 3 ecrits et mordants ;
2. **T6 livre**, avec les trois balayages de production des garde-fous 4, 5 et 6 etendus et le
   plafond de `AucunPaquetDeDesignationNEntreEnProductionSansEtendreLesTroisBalayagesTest` monte a
   son compte reel dans le meme diff ;
3. **les deux trous de 4.5 tranches** - `DesignationVerdict` porte son enonce, et la question du
   libelle est arbitree par `nadia-prof` ;
4. **les huit regles vertes sur leur population reelle**, pas sur une fixture.

**T4 seul ne suffit pas, et c'est structurel** : trois des huit regles ne changent d'objet qu'a T6.
`ADR-0080` reste donc *Propose* jusqu'a ce que le premier paquet de designation soit servi. **Je
l'amende plutot que de le laisser dire des choses fausses** - voir la section 8.

---

## 6. Ce qui casse

### 6.1 Trois garde-fous deviennent rouges le jour de l'entree de catalogue, et c'est voulu

**Ils sont ecrits pour ca**, et ils sont la meilleure nouvelle de cet avis : l'entree de catalogue ne
peut pas passer avant T4, et le build le dira.

1. **`AucunPaquetDeDesignationNEntreEnProductionSansEtendreLesTroisBalayagesTest`**
   (`bootstrap/src/test/java/com/recreo/archi/`) - le cliquet a plafond zero. Rouge des que le
   premier paquet de type `designation` apparait sous `src/main/resources`, avec un message qui nomme
   les trois balayages a etendre. **Il faut le monter a 1 dans le meme diff que T6.**
2. **`AucuneReponseDeFinDePartieNEstOrphelineDeSaQuestionTest`** - il **leve** sur une forme d'item
   qu'aucun ecran servi ne sait jouer, et sa Javadoc dit deja *"le jour ou un paquet de designation
   est servi, c'est ici que la question de son ecran de fin de partie se pose, en rouge"*. C'est le
   garde-fou qui trouvera le trou A de 4.5 si personne ne l'a lu.
3. **`AucunTexteServiNEnchasseLaReponseDansUnePhraseTest`** - meme forme, meme motif.

**Et un quatrieme, qui n'etait pas ecrit pour ca mais qui mord quand meme** :
**`LaConsigneDUnJeuServiAtteintLEcranDeMancheTest`** itere sur **tout** `jeuxDisponibles()` et
demande a chacun son ecran de manche. Une entree de designation sans branche de descente rend
`PartieIndisponible` puis l'ecran d'erreur, qui ne porte pas de consigne : **rouge**.

### 6.2 Un garde-fou devient rouge des la creation de la route, avant tout paquet

**`ToutEcranEchangeEstUnDocumentJouableTest`** (`adapter-web`) derive la liste des routes de
`RequestMappingHandlerMapping` et l'asserte en `containsExactlyInAnyOrderElementsOf(routesDeclarees())`
contre son inventaire d'ecrans. **La route `POST /parties/{jeuId}/designations` le rend rouge a la
seconde ou elle est declaree**, tant que son inventaire ne la couvre pas. C'est le comportement
voulu d'un perimetre derive, et c'est le premier rouge que T4 rencontrera.

### 6.3 Cinq garde-fous derivent leur perimetre et **changent de verdict tout seuls**, en vert

Cinq garde-fous de `adapter-web` balaient `src/main/resources/templates` avec `Files.walk` :
`AucunTexteNeVitDansUnSousArbreMasqueTest`, `HabillageDesEcransTest`,
`TexteAccentueDesGabaritsTest`, `TailleDesTextesEtBoutonsTest`, `FrontieresDuJavaScriptTest`. **Le
fragment `designation.html` entre dans leur population a la seconde ou le fichier existe**, sans
qu'une ligne de test change - vert s'il est conforme, rouge sinon. Deux d'entre eux balaient aussi
`static/`, donc les regles CSS neuves y entrent de meme. C'est `ADR-0013` qui fonctionne.

**Un cas voisin a ne pas confondre** : `ViewportEtLargeurTelephoneTest` ne juge que les fichiers
**directement** sous `templates/`, jamais `templates/fragments/`. Un fragment n'entre donc **pas**
dans sa population, et il ne changera pas de verdict.

### 6.4 Cinq garde-fous derivent leur perimetre et **restent verts en ne prouvant rien**

**C'est le point le plus important de cette section, et c'est un defaut, pas une observation.**

Cinq garde-fous de rendu de `bootstrap/src/test/java/com/recreo/bootstrap/rendu/` construisent leur
propre catalogue de preuve en relisant le catalogue reel, et **`continue` sur tout paquet qui n'est ni
`question` ni `saisie`** :

- `LIllustrationDUnItemServiAtteintLEcranDeMancheTest`
- `LeBlocAdulteDUnItemReelAtteintLEcranDeMancheTest`
- `LeTexteSupportDUnItemReelAtteintLEcranDeMancheTest`
- `LaPartDesigneeDUnItemServiEstMarqueeALEcranTest`
- `ChaqueColonneQueLeContenuPeutProduirePorteSonDecalageTest`

**Un paquet de designation sera ecarte de leur population en silence.** Les deux garde-fous de 6.1
levent sur une forme inconnue ; ces cinq-la l'ignorent. **Le plus grave est le premier** : le
garde-fou qui prouve que l'illustration d'un item servi atteint l'ecran de manche est celui qui, par
ce `continue`, ecartera exactement la seule mecanique ou **l'illustration est obligatoire** -
`ZoneADesigner` la refuse vide dans son constructeur compact.

**A traiter dans le meme geste que T6**, et non plus tard. Pour chacun, deux formes possibles : lever
au lieu de `continue` (la forme de 6.1, la plus honnete), ou traiter le cas. **La premiere est la
bonne pour les cinq** : elle ne coute que la ligne du `continue`, et elle transforme un vert muet en
un rouge qui pose la question.

### 6.4 bis Correction du 2026-09-08, apres l'arbitrage de `nadia-prof` - le cinquieme n'est pas de la meme famille que les quatre autres

**Le chiffre de 6.4 est faux d'un cas, et l'erreur est de moi.** J'ai ecrit que les **cinq** font
`continue` sur un paquet ni `question` ni `saisie`. C'est vrai de quatre.
`ChaqueColonneQueLeContenuPeutProduirePorteSonDecalageTest` **ne branche pas du tout sur le type** :
il lit les `items` par une forme generique. Un paquet de designation, qui declare des items sans
distracteurs, y vaut **1 colonne** - donc aucun grief, aucun rouge, et le plafond etant un maximum,
**aucun effet sur son verdict**.

Ce qui est reellement faux chez lui n'est donc pas un `continue`, c'est une **promesse ecrite** : sa
Javadoc annonce qu'*"une troisieme forme, le jour ou elle arrivera, fera rougir ce garde-fou au lieu
de passer en silence"*. La promesse tient pour un paquet de **forme** illisible et pas pour un paquet
d'un **type** neuf dont la forme se lit par accident. Cinq cas, deux mecanismes.

**Les cinq sont payes dans le meme commit que cet avis est corrige** : les quatre levent sur un type
non instruit, le cinquieme ajoute un grief et poursuit son balayage. Aucun ne pose de branche
`designation` - c'est un rouge date, pas une exemption ecrite d'avance. Ce qui est decide vit dans
`ADR-0088`, clause C ; ce qui est mesure y est aussi.

**Et le lot T6 herite donc de neuf rouges et non de quatre.** Les quatre de 6.1 et 6.2, plus les cinq
de 6.4. C'est le prix de la derivation totale, et il est ecrit ici pour ne pas etre decouvert au
planning.

### 6.5 La regle a ecrire dans le meme geste, et je l'ecris avec cet avis plutot que de la promettre

**Le fait, mesure** : `com.recreo.domain.mechanics` porte trois tranches ;
`MecaniquesConfiguration` publie deux beans. `Designation` est dans l'arbre depuis s28 et **aucune
ligne de `bootstrap` ne la nomme**. Pendant quatre sprints, 1396 tests verts, la clause 7 d'`ADR-0004`
verte, la clause 10 d'`ADR-0080` verte - et le constat a du etre fait **a la main** par `nadia-prof`
en comptant le catalogue servi. **C'est la dette 1 de `CLAUDE.md` par son autre face** : celle-la
nommait une abstraction documentee et jamais implementee, celle-ci nomme une mecanique implementee,
testee unitairement, et inatteignable depuis le produit. Meme mode de defaillance : tout est vert.

**La regle est ecrite, elle est dans mon diff, et elle est verte.**
`bootstrap/src/test/java/com/recreo/archi/ChaqueMecaniqueDuDomaineEstAtteignableDepuisLeProduitTest.java`,
**6 tests verts**, mesure partielle
`mvn -pl bootstrap -am -Dtest=ChaqueMecaniqueDuDomaineEstAtteignableDepuisLeProduitTest`, module
`recreo-bootstrap`, JDK 21.0.10.

- **Ce qu'elle interdit reellement** : qu'une mecanique existe dans `domain` sans qu'aucun type de
  `bootstrap` en depende. Le critere est la dependance reelle du graphe (`getDirectDependenciesToSelf`),
  pas la presence d'un nom dans un fichier - un bean commente ne trompe pas le predicat. Elle
  **decrit l'etat du depot** au lieu de borner un risque imagine : c'est la preuve la plus forte
  qu'elle n'est pas decorative.
- **Pourquoi un cliquet et non une regle seche** : une regle seche serait rouge aujourd'hui, donc
  incommittable, donc reportee au jour ou elle gene - donc negociee.
  `PLAFOND_DE_MECANIQUES_NON_CABLEES` vaut **exactement 1**, la taille de la dette, et il est refuse
  en egalite **dans les deux sens** : il descend a zero dans le meme diff que le bean de 4.2.
- **Ce qu'elle ne garde pas, et qui le garde** : un bean ne rend pas une mecanique jouable. La route
  est gardee par `ToutEcranEchangeEstUnDocumentJouableTest` (6.2), l'ecran par
  `LaConsigneDUnJeuServiAtteintLEcranDeMancheTest` (6.1), l'entree de catalogue par
  `CatalogueChargeableIT`. **Une propriete gardee a moitie qui se croit gardee en entier est la
  faute que ce depot a sanctionnee plusieurs fois** ; elle est decoupee, et chaque moitie porte le
  nom de son garde.
- **Une seconde regle que j'ai concue puis jetee, et je le dis plutot que de gonfler le compte.**
  J'allais ecrire *"chaque `mecanique` du catalogue resout dans le registre"*. Elle existe deja en
  substance : `CatalogueChargeableIT` appelle `partie.demarrer(jeu.id())` pour **chaque** entree, et
  `MechanicRegistryPort` etant `parIdentifiant::get`, un identifiant inconnu rend `null` et
  `PartieService.demarrer` leve. Le seul defaut est la forme du message - une `NullPointerException`
  la ou un nom serait plus clair -, ce qui est une gene de diagnostic, pas un trou. **Une regle de
  plus sur une propriete deja tenue serait decorative.**

**Compte des `ArchRule` du depot : inchange, 33.** Ce garde-fou n'est pas une `ArchRule` : il importe
les classes avec `ClassFileImporter` mais son juge est un cliquet a compte, ce qu'une `ArchRule` ne
sait pas exprimer. Meme forme que le cliquet de designation d'`ADR-0080`. `LesGardeFousSontMisALEpreuveTest`
(10 tests) et `LesReglesMordentTest` (33 tests) restent verts avec lui dans l'arbre - mesure
partielle `mvn -pl bootstrap -am`, module `recreo-bootstrap`.

---

## 7. Le cout en lots, et sa repartition

**Base de mesure : le lot qui a ouvert la mecanique de saisie a l'ecran**, c'est-a-dire l'homologue
exact de T4. Commit `c83d4c3d`, `[MARC] s24: lot 4`. Methode : `git show --numstat c83d4c3d`.

- **production : 400 lignes** - `PartieController` +167, `recreo.css` +115, `fragments/saisie.html`
  +118.
- **tests : 802 lignes ajoutees sur 10 fichiers** - dont `EcranDeSaisieTest` 336 et un garde-fou de
  rendu de `bootstrap` 203, le reste etant l'extension de huit garde-fous existants.
- **Reserve sur ce chiffre, dite plutot que tue** : c'est le **commit d'ouverture**, pas toute
  l'histoire de la saisie. Des commits ulterieurs l'ont raffine (`ADR-0069`, `SAI-8`). Le chiffre
  borne par le bas.

**T4 est plus cher que ce lot-la, sur deux postes precis et un seul allegement.**

- **Plus cher** : le positionnement des zones sur la boite de l'image n'a pas d'homologue - la saisie
  posait un champ dans un flux, la designation pose *n* boutons a des coordonnees declarees par le
  contenu. Et le garde-fou 3 epingle **deux** termes derives (`ADR-0055`), pas un.
- **Plus cher** : les deux trous de 4.5 ajoutent une composante a un record du `domain` et rouvrent
  ses sites de construction.
- **Moins cher** : l'arbitrage d'`ines-design` sur la taille d'image **existe deja**, avec son
  arithmetique et ses deux largeurs de container verifiees. C'est le poste ou `ADR-0080` prevoyait
  une surprise.

**Ce que je peux dire du cout, et ce que je ne peux pas.**

| Lot | Perimetre | Modules | Depend de | Faisabilite |
|---|---|---|---|---|
| **L-a** | garde-fou 2 (les sept interdits du JavaScript maison) et la regle de 6.5 portee a zero **avec** le bean de 4.2 | `adapter-web`, `bootstrap` | rien | **faisable en l'etat, un demi-lot** |
| **L-b** | trou A (`DesignationVerdict` porte son enonce) et sites de construction | `domain` | rien | **faisable en l'etat, un demi-lot** |
| **L-c** | T4 : route, troisieme branche, `mancheDeDesignation`, `retourDeDesignation`, `fragments/designation.html`, positionnement CSS, garde-fous 1 et 3 | `adapter-web` | L-a, L-b, et trou B tranche par `nadia-prof` | **un lot plein, le plus lourd du chantier** |
| **L-d** | les cinq `continue` de 6.4 changes en levee | `bootstrap` | rien | **faisable en l'etat, un demi-lot** |
| **L-e** | T6 : le paquet, ses dessins, l'entree de catalogue, l'extension des trois balayages de production, la montee du cliquet | `adapter-content`, `adapter-web` | L-c, L-d, et le corpus de dessins | **un lot plein** |

**Repartition. C'est un avis, `sofia-sm` affecte.** Trois developpeurs, et une contrainte qui n'est
pas negociable : **L-c est indivisible**, parce que tout ce qu'il touche converge sur un seul fichier
que la clause 9 interdit de decouper (4.6). Deux developpeurs sur `PartieController` en parallele
produiraient un conflit sur les memes 85 lignes.

- **L-c a un seul developpeur, sur un sprint entier.** C'est la tache qu'`ADR-0080` avait deja
  designee comme la seule a toucher a la fois au rendu, au style et a une valeur epinglee.
- **L-a, L-b et L-d sont trois demi-lots independants les uns des autres**, sur trois modules
  differents, et **tous trois faisables avant L-c**. Ils vont a deux autres developpeurs et ils
  paient les prerequis de L-c.
- **L-e vient apres L-c**, et c'est le seul lot du chantier qui demande un corpus de dessins.

**Le chiffre que je n'ai pas, et je le dis comme manquant plutot que de l'estimer : le cout des
dessins.** L'arbitrage de `nadia-prof` du 2026-09-05 exige un a deux dessins minimum par ligne
(section 4), sa section 8.3 dit ce dont elle a besoin pour juger un plan ou une carte, et le depot
sert aujourd'hui **24 illustrations** - methode : comptage des fichiers de
`adapters/adapter-web/src/main/resources/static/illustrations/` - **toutes** des figures geometriques
(12) ou des pieces de monnaie (12). **Un planisphere n'est ni l'un ni l'autre.** Je ne sais pas
combien coute un dessin de cette
famille, ni si `ines-design` peut le produire, et je refuse d'inventer le chiffre. **La mesure qui
manque : un dessin de la famille "plan ou carte", produit et mesure, avec ses quatre cadres de zone
et son poids sous le cliquet.** Elle est faisable en un demi-lot et elle rend le cout de L-e
chiffrable ; sans elle, L-e n'est pas chiffre.

---

## 8. Ou cet avis vit, et pourquoi il vit a deux endroits

**On m'a demande de dire si la bonne place est `squad/sprints/` ou un amendement d'`ADR-0080`. La
reponse est les deux, et la frontiere est nette.**

**Ce qui est un avis, et vit ici** : ce qui reste a ecrire, la taille du geste, le decoupage en lots,
la repartition entre developpeurs, les garde-fous qui vont rougir, le rang. Rien de tout cela n'est
une decision d'architecture : ce sont des faits mesures et un avis de faisabilite, qui perime au
prochain sprint. Un `ADR` qui porterait ca serait un `ADR` qu'on relit dans six mois en croyant y
lire une decision.

**Ce qui est une decision, et vit dans `ADR-0080`** : son statut, ce que ses tables promettent, et
les regles dues. **`ADR-0080` dit aujourd'hui des choses fausses**, et un `ADR` qui dit des choses
fausses est pire qu'un `ADR` absent :

1. sa section de faisabilite dit *"T4 est faisable moyennant un arbitrage qui n'existe pas"* -
   l'arbitrage existe, et sa valeur est dans `recreo.css` ;
2. elle dit *"T6 est infaisable en s28"* et *"un arbitrage prealable de `nadia-prof` [...] n'est pas
   ecrit"* - il est ecrit depuis le 2026-09-05 ;
3. elle ne dit rien des deux trous de 4.5, qu'elle n'avait pas vus ;
4. elle ne dit rien de la regle de 6.5, qui n'existait pas.

**J'amende donc `ADR-0080` dans le meme geste que cet avis**, et **son statut reste `Propose`** :
trois de ses huit regles ne changent d'objet qu'a T6, et un statut `Accepte` sur des regles qui
mordent encore sur fixture serait le contraire de ce que cet `ADR` demande a tout le monde.
`ADR-index.md` est mis a jour dans le meme commit.

---

## 9. Le rang : les trois sprints peuvent le porter a cote du contenu, et voici a quelle condition

**Ce que je peux dire.**

Le chantier tient en **cinq lots dont trois sont des demi-lots** (section 7), et **quatre des cinq
sont sur des modules disjoints du contenu**. L-a touche `adapter-web` et `bootstrap` ; L-b touche
`domain` ; L-c touche `adapter-web` ; L-d touche `bootstrap`. **Un lot de contenu du Plan 2 touche
`adapter-content/src/main/resources` et rien d'autre.** Sur les quatre premiers lots, **il n'y a pas
de conflit de fichier avec un lot de contenu**, et il n'y a pas non plus de conflit de garde-fou :
les garde-fous que le palier 2 fait rougir (6.1, 6.2) ne sont pas ceux qu'un paquet de contenu neuf
fait rougir.

**Donc, sur les trois sprints s32 a s34 : L-a, L-b, L-c et L-d peuvent se conduire a cote du contenu
du Plan 2, sans que l'un chasse l'autre**, a une condition de sequencement et une seule : **L-c a un
developpeur pour lui seul pendant son sprint**, parce que `PartieController` est indivisible. Avec
trois developpeurs et un triplement de capacite d'audit, cela laisse deux developpeurs au contenu
pendant le sprint de L-c, et trois pendant les autres.

**L-e est le seul lot qui entre en concurrence avec le contenu**, et il y entre deux fois : il
consomme de la capacite de developpement **et** de la capacite d'audit de `nadia-prof`, sur un
terrain a conditions inedites - elle a chiffre ce terrain-la 1,0 a 1,5 par ligne au lieu de 0,5.

**Ce que je ne peux pas dire, et la mesure qui manque.**

**Je ne peux pas dire si L-e tient dans s34.** Il lui faut des dessins de la famille "plan ou carte",
et je n'ai pas leur cout (section 7). **La mesure qui manque, nommee precisement pour qu'elle soit
commandable** : un dessin de cette famille, produit, servi, mesure - ses quatre cadres de zone, son
poids sous le cliquet du palier 1, et le verdict de `nadia-prof` sur son exactitude a la taille
d'image du palier 2. **Un seul dessin suffit a la mesure.** Elle est faisable en un demi-lot, elle
peut se faire des s32 en parallele de tout le reste, et **sans elle le rang de L-e n'est pas
instruit** - ni par moi, ni par personne.

**Et une consequence de sequencement que je nomme pour qu'elle ne soit pas decouverte a la review** :
**L-a a L-d ne ferment aucune ligne de matrice.** Le rendement de 10 lignes dont parle `nadia-prof`
est **entierement porte par L-e**. Un plan qui prendrait L-a a L-d et laisserait L-e pour s35 aurait
paye tout le chemin de jeu et n'aurait ferme aucune ligne - ce qui est un choix defendable, la dette
etant alors reduite a des dessins et a un audit, mais **c'est un choix, et il doit etre fait les yeux
ouverts.**

---

## 10. Ce que je route, et a qui

| # | Objet | A qui |
|---|---|---|
| `R1` | Deux identifiants de condition de prefixe `PAR` ecrits par `f503b4e5` ne sont pas alloues dans `squad/decisions/REGISTRE-identifiants.md`, qui s'arrete a `PAR-11` : le job `build` est **rouge** (section 1) | `nadia-prof`, copie `sofia-sm` |
| `R2` | La cellule de statut de la ligne du quadrillage (mathematiques CP, espace et geometrie) ne porte **aucun** motif, donc aucun recompte par motif ne la trouve. Ecart signale par `ADR-0080` le 2026-09-03, non paye (section 3) | `camille-po` |
| `R3` | L'ecart entre *"10 lignes touchees"* et *"n lignes fermees"* : cinq des dix portent une moitie deja jouable en texte, dont une qui ecrit *"non exige"* (section 3) | `nadia-prof` |
| `R4` | Trou B : ce que l'enfant voit de la zone attendue sur l'ecran de fin de partie - un libelle, autre chose, ou rien (section 4.5) | `nadia-prof` |
| `R5` | Le cout d'un dessin de la famille "plan ou carte", que je refuse d'estimer (sections 7 et 9) | `ines-design`, copie `sofia-sm` |
| `R6` | L'affectation des cinq lots, dont la contrainte d'indivisibilite de L-c (section 7) | `sofia-sm` |

---

## 11. Ce que je conclus

**Le constat qu'on m'a demande de verifier est juste sur l'essentiel** : personne ne peut jouer le
palier 2, et ce qui manque est le chemin de jeu, pas des items.

**Il manque deux couches, pas trois** : `application` n'a rien a recevoir, et `bootstrap` n'a qu'un
bean de quatre lignes a recevoir. **Tout le poids est dans `adapter-web`.**

**La troisieme mecanique entre dans `PartieController` et doit y entrer** : la clause 9 nomme un
point de descente unique en nom pleinement qualifie, et l'extraire la rendrait rouge. Le cout est
que ce fichier passe d'environ 700 a 785 lignes. **C'est un cout, et je m'ecris le declencheur qui me
fera rouvrir la question sur le diff de T4, pas avant.**

**Deux trous qu'`ADR-0080` n'avait pas vus** : `DesignationVerdict` ne porte pas d'enonce la ou
`ADR-0084` l'exige, et `bonneZone` est un identifiant la ou l'ecran de fin de partie rend un texte
lu par un enfant.

**Cinq garde-fous resteront verts en ne prouvant rien** si personne ne change leur `continue`, dont
celui de l'illustration - qui ecarterait la seule mecanique ou l'illustration est obligatoire.

**Et la regle qui manquait depuis quatre sprints est ecrite avec cet avis**, verte, cliquet a un,
plafond exactement egal a la dette : une mecanique que `bootstrap` ne cable pas n'est jouable par
personne, et jusqu'a aujourd'hui aucun automate du depot ne le disait.

**Les trois sprints peuvent porter les quatre premiers lots a cote du contenu du Plan 2.** Le
cinquieme - celui qui ferme les lignes - n'est pas chiffrable sans une mesure que je n'ai pas, et je
la nomme : **un dessin de plan ou de carte, produit et mesure.**
