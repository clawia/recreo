# ADR-0104 - Rapprocher deux oeuvres se sert par des propositions qui sont des reproductions, sans mecanique ni forme d'item neuve, et la mecanique ne lit jamais une proposition

- **Statut** : Propose. **Passe a `Accepte` sur une seule reponse, celle de `nadia-prof` sur sa
  condition `C5` clause 1** (aucune image dans une boite de proposition), qui ferme aujourd'hui la
  decision 1 sur sa lettre (section "Ce que je route", point 1). La regle executable de cet `ADR` est
  en vigueur des maintenant : elle tient dans toutes les formes envisagees, y compris le repli
- **Date** : 2026-10-05
- **Sprint** : hors-sprint, instruction prealable (obstacle 13 de la review de s50)
- **Auteur** : `theo-archi`
- **Route par** : `nadia-prof`, `squad/conformite/ARBITRAGE-PREALABLE-s50-art-cp.md`, Q1 (section
  "Ce que j'exige de la forme", et ligne 4 du tableau "Ce qui bloque encore") ; `camille-po`,
  `DEC-0109` section 10.1 ; review de s50, obstacle 13 ; retro de s50, section 4
- **Arbre mesure** : `round-two` a `f68458de`, plus le seul diff de cet `ADR` (trois fichiers de test
  de `bootstrap`, aucun fichier de production). JDK **21.0.10**, version que la machine virtuelle des
  tests rapporte d'elle-meme (propriete `java.version` du rapport Surefire, `ADR-0071`). Enveloppe :
  `bash outils/mvn-jdk21.sh`. Toute mesure partielle porte `-am` et nomme son module
- **Prolonge** : `ADR-0082` (troisieme population d'images, decision 2 : la population se lit sur le
  prefixe `oeuvre-`), `ADR-0073` (un item declare une image par un identifiant), `ADR-0058` (critere
  d'une forme d'item de plus : la facon dont l'enfant repond change), `ADR-0088` (trou B : un
  identifiant technique n'est jamais rendu en texte), `ADR-0004` (decouplage mecanique - contenu)
- **Ne decide aucun contenu, aucune pedagogie, aucun perimetre de sprint.** Il n'engage pas
  `ART-CP-10` (`camille-po`), ne choisit ni les paires ni les distracteurs (`nadia-prof`), n'ecrit
  aucun mot lu par l'enfant (`nadia-prof`) et ne fixe aucune mise en page (`ines-design`)

---

## Contexte

`nadia-prof` a refuse le 2026-10-04 la forme texte d'`ART-CP-10` (*"rapprocher des oeuvres"*) :
montrer une oeuvre et proposer les autres par des mots qui les decrivent est une contrefacon
(`CON-1`), parce que les paires ont ete choisies pour une propriete *"qui ne se voit qu'en
regardant"* - une touche, une maniere de dessiner l'eau, une lumiere de fenetre. Son exigence, dans
ses mots : **"les deux oeuvres visibles ensemble, a une taille ou la propriete rapprochee se voit"**.
Elle n'exige pas le moyen, et en nomme deux qui lui sont recevables : *"propositions en image, deux
images par item"*.

Le depot sait aujourd'hui montrer **une** reproduction par item, dans le bloc `.illustration` de
`fragments/manche.html` (`ADR-0082`, T3). Il ne sait pas en montrer deux. Ce qui manque, mesure sur
l'arbre et non suppose :

| Fait | Ou il se lit |
|---|---|
| Une proposition est rendue par `th:text="${proposition}"` dans un `<button>` | `fragments/manche.html`, `form.propositions` |
| La revelation rend la bonne reponse en texte dans `.reponse-attendue` | `fragments/manche.html` ; `PartieController`, branche `session.revelee()` |
| L'ecran de fin rend la bonne reponse en texte quand la manche n'est pas juste | `fragments/retour.html` ; `PartieController.retourModele`, `reponseAttendueVisible = !verdict.juste()` |
| La mecanique `bandit-manchot` compare la proposition choisie a la bonne reponse par `equals` et ne lit rien d'autre d'une proposition | `BanditManchotSession.jouer`, `melanger` |
| Un garde-fou d'accent lit chaque proposition comme un texte lu par l'enfant | `LeTexteServiPorteLeCaractereDecideJamaisSaNotationTest.textesServis`, consomme par `AucunMotServiNeSEcarteDeSonAccentTest` |
| Une reproduction qu'aucun item ne declare en `illustration` fait rougir le garde-fou de resolution | `LIllustrationDeContenuResoutEtAucuneNeDortTest`, clause *"aucune ne dort"* |

Les trois premiers faits disent qu'un identifiant `oeuvre-...` pose aujourd'hui en proposition
**serait ecrit en toutes lettres a l'enfant**, sur trois ecrans. Les deux derniers disent que le
depot rougirait, mais pour de mauvaises raisons, et je l'ai mesure (decision 6).

**Et une condition pedagogique ecrite ferme la forme la plus directe.** `C5` clause 1 de
`nadia-prof` (2026-08-11, `FICHE-bandit-manchot-additions-cp.md`) : *"aucune image, aucun
pictogramme, aucun aplat decoratif a l'interieur d'une boite de proposition, ni derriere son
texte"*. Elle l'a clarifiee le 2026-08-24 (`ARBITRAGE-PREALABLE-s19-quatre-proxys-en-attente-depuis-s13.md`,
section 0.3) : la clause ne mord pas sur ce qui **constitue** la proposition entiere, elle mord sur
ce qui est **ajoute** a cote ou derriere un texte. Et elle l'a maintenue le 2026-08-16 sur un second
motif (`ARBITRAGE-PREALABLE-s13-voie-de-rendu-des-propositions.md`, section 4) : *"ce qu'un enfant lit
dans une boite de proposition doit rester verifiable par autre chose que moi"*. Une reproduction qui
constitue la proposition entiere tombe entre ces deux textes. **Je ne tranche pas lequel s'applique :
c'est sa condition.**

---

## Decision

### 1. La vue simultanee est un item `question` dont l'illustration et toutes les propositions sont des reproductions

Un item d'`ART-CP-10` montre **une oeuvre de reference** par la composante `illustration` existante
(`ADR-0073`, `ADR-0082`), et propose **des oeuvres** : `bonneReponse` et chaque `distracteurs` sont
des identifiants de reproduction (`oeuvre-[a-z0-9-]+`). L'enfant voit la reference et les oeuvres
proposees sur le meme ecran, et appuie sur celle qui va avec la reference.

**C'est la forme "propositions en image"**, la premiere des deux que `nadia-prof` nomme. Je retiens
celle-la parce qu'elle est la seule ou **le geste de reponse est le rapprochement lui-meme** :
l'enfant designe une oeuvre, pas un mot. "Deux images par item" avec des propositions texte laisse la
reponse ecrite, donc rouvre le motif 1 de Q1 (*"une etiquette ecrite evoque un sujet, jamais une
touche"*).

**Cette decision est suspendue a `C5` clause 1** (Contexte, dernier paragraphe). Ce que je peux
apporter a sa reponse, sans la donner : le premier motif de la clause (le raccourci visuel - *"un
enfant de CP qui a le choix entre dechiffrer `an` et regarder un dessin regarde le dessin"*) n'a pas
d'objet ici, regarder est la competence ; le second motif (un lien verifiable entre ce que le depot
ecrit et ce que l'oeil voit) est tenu **a moitie** - l'identifiant resout vers un fichier dont la
provenance est prouvee (`ADR-0082`, et garde-fou 1 de la decision 7), mais rien d'automatique ne
prouve que l'image est fidele, et c'est deja sa garde sur `ART-CP-09`. **Si elle maintient `C5` sur
ce cas, le repli est ecrit en section "Ce qui me ferait changer d'avis", point 6, avec son prix.**

### 2. Aucune mecanique neuve, aucune forme d'item neuve, aucun champ de domaine, aucun port neuf

Le critere est celui d'`ADR-0058`, repris par `ADR-0073` clause 6 et `ADR-0082` decision 5 : une
forme d'item de plus se justifie quand **la facon dont l'enfant repond** change. Elle ne change pas :
il appuie sur une proposition parmi trois au plus, et le verdict est une egalite. Ce qui change est
**ce que la proposition montre**, et c'est un fait de rendu.

Donc, et c'est tout ce qui ne bouge pas :

- `Item` reste `permits Question, ReponseAEcrire, ZoneADesigner` ; `Question` ne gagne aucune
  composante ;
- `BanditManchot` et `BanditManchotSession` ne changent pas d'une ligne ;
- `application` ne change pas : ni `PartieUseCase`, ni `ContentCatalogPort`, ni `PartieService` ;
- le chargeur ne change pas : les cles YAML sont celles d'aujourd'hui (`illustration`,
  `illustrationTexteAlternatif`, `bonneReponse`, `distracteurs`). Dette 3 de `CLAUDE.md` intacte ;
- le contrat d'echange web ne change pas : meme formulaire, meme route `POST /parties/{jeuId}/coups`,
  meme parametre `propositionChoisie`, dont la valeur est l'identifiant. Reponse en fragment HTML,
  zero JSON.

**Ou vit la notion de vue simultanee** : dans le **contenu**, qui declare des identifiants, et dans
l'**adaptateur web**, qui les rend en images. Nulle part dans `domain` ni dans `application`, qui
n'ont donc rien a gagner en dependance de framework. La mecanique transporte des chaines qu'elle ne
lit pas ; c'est ce que la regle de cet `ADR` rend obligatoire (section "Regle executable").

### 3. La population d'une proposition se lit sur son prefixe, dans l'adaptateur web, une seule fois

`ADR-0082` decision 2 a etabli que le prefixe `oeuvre-` est l'ombre du repertoire `static/oeuvres/`
du cote du rendu, et a ecarte une cle declarative (`illustrationEstUneOeuvre`) : *"une cle declarative
peut mentir - un prefixe verifie contre le repertoire, non"*. **Je l'etends de l'illustration aux
propositions, sans une virgule de plus.**

- `PartieController` derive **un** booleen, `propositionsEnImage`, vrai si et seulement si **toutes**
  les propositions de la manche portent le prefixe `oeuvre-`. C'est le seul endroit de l'adaptateur
  ou le prefixe d'une proposition est lu ; le gabarit branche sur ce booleen, jamais sur un
  `#strings.startsWith` par proposition.
- Le meme controleur derive, pour l'ecran de fin, si `verdict.bonneReponse()` porte ce prefixe.

### 4. Le rendu : une image par bouton, au plafond existant, et jamais l'identifiant en texte

Dans `fragments/manche.html`, quand `propositionsEnImage` est vrai, chaque `<button
class="proposition">` porte une image `@{'/oeuvres/' + ${proposition} + '.jpg'}` au lieu de son
texte. Clauses, chacune verifiable :

1. **Aucun identifiant `oeuvre-` n'est ecrit dans un noeud de texte, un `alt` ou un nom accessible**,
   sur aucun ecran : manche fraiche, echec, revelation, fin de partie. Ce n'est pas un gout : un
   identifiant est de l'ASCII sans accent (`oeuvre-champ-de-ble`) et il nomme le sujet, donc la
   reponse (`CON-5`). C'est la regle qu'`ADR-0088` a deja posee pour la designation.
2. **Le nom accessible de chaque bouton est un texte du gabarit**, identique pour tous les items,
   jamais une donnee de contenu et jamais l'identifiant. Ses mots appartiennent a `nadia-prof`
   (`CON-5` : le texte alternatif *"ne nomme rien"*), sa forme a `ines-design`.
3. **Taille : le plafond existant, rien de neuf.** Chaque image de proposition se rend sous la regle
   `.image-item` (`max-height: 11rem`, soit 176 px CSS, `recreo.css`), d'un fichier de 352 px au
   plus grand cote (`ADR-0082`, plafond de pixels), comme l'oeuvre de reference. **Je ne fixe pas de
   plancher** : il appartient a `nadia-prof` (la propriete se voit-elle ?) et a `ines-design` (le
   pli, la largeur de quatre images sur un ecran de CP). Je fixe que le plafond ne monte pas sans
   amender `ADR-0082` - monter la taille rendue au-dela de 176 px CSS sur un ecran a double densite
   demande des fichiers de plus de 352 px.
4. **Revelation** : `reponseRevelee` n'est pas posee en texte quand `propositionsEnImage` est vrai ; le
   bouton de la bonne oeuvre est deja designe par `data-focus-cible` (`M13`). La marque visuelle qui
   le distingue et la phrase qui l'accompagne sont a `ines-design` et `nadia-prof`.
5. **Fin de partie** : `reponseAttendueVisible` vaut `false` quand la bonne reponse est une
   reproduction - le precedent exact du trou B d'`ADR-0088` pour la designation, et pour le meme
   motif : ni un identifiant ni un libelle positionnel n'apprend quoi que ce soit a cet ecran. Si
   `nadia-prof` veut y revoir l'oeuvre, c'est une image de plus a cet ecran, une ligne de gabarit et
   un cran de cliquet (decision 7, point 5).

### 5. Trois invariants de contenu, gardes par balayage, dus avec le lot

Sur tout item servi dont une proposition porte le prefixe `oeuvre-` :

1. **tout ou rien** : toutes ses propositions le portent. Une rangee melangee rendrait un bouton-texte
   a cote de boutons-images, et ferait juger la moitie de l'item par les garde-fous de texte ;
2. **la reference existe et est une reproduction** : l'item declare une `illustration` prefixee
   `oeuvre-`. Sans elle, il n'y a pas deux oeuvres a rapprocher mais trois a comparer ;
3. **la reference n'est pas une proposition** : sinon l'item se resout en trouvant l'image
   identique, sans rien rapprocher. C'est la contrefacon par identite, et c'est la seule que la
   machine puisse voir seule.

### 6. Ce qu'un identifiant pose en proposition fait aux garde-fous de texte - mesure

**Methode.** Sonde temporaire dans `adapter-content` (non commitee, retiree apres mesure), qui passe
chacun des onze identifiants du vivier d'`ART-CP-09` et d'`ART-CP-10` a
`AucunMotServiNeSEcarteDeSonAccentTest.defautsDe`, avec le lexique reel
(`LexiqueFrancais.charger()`). Commande : `bash outils/mvn-jdk21.sh -B -q -pl adapters/adapter-content
-am test -Dtest=<sonde> -Dsurefire.failIfNoSpecifiedTests=false`.

**Resultat : 2 identifiants sur 11 rougissent**, `oeuvre-champ-de-ble` (`ble` n'est pas une forme du
francais, `ble` accentue l'est) et `oeuvre-jeune-femme-aiguiere`. Les neuf autres passent. Ce serait
un **faux rouge** : la chaine n'est lue par personne.

**La correction a un endroit, et un seul.** `LeTexteServiPorteLeCaractereDecideJamaisSaNotationTest.
textesServis` declare deja, a la construction, si l'enfant lit une chaine (`luParLEnfant`), et en
exclut les formes acceptees d'une saisie pour un motif de la meme famille. Les propositions d'un item
dont `propositionsEnImage` serait vrai y sont declarees `luParLEnfant = false`. Les deux garde-fous
qui consomment ce booleen (`AucunMotServiNeSEcarteDeSonAccentTest`,
`AucunSigneDoubleNEstDetachableDeSonMotTest`) suivent sans etre touches.

Les autres garde-fous qui lisent des propositions restent pertinents sur des identifiants, et je ne
les exempte pas : `AuPlusTroisPropositionsParItemTest` (trois au plus), `AucunePropositionNEstEgale...`,
`AucunItemNeRepeteLEcranDUnAutreItemDuMemePaquetTest`, `LeRangDeLaBonneReponse...` (sans valeur
numerique, hors de son domaine par `ADR-0041`). **Ce que je n'ai pas mesure** : le passage de chaque
garde-fou de rendu de `bootstrap` sur un paquet reel, faute de paquet. Le lot le mesure par
`mvn -B clean verify` sur la racine.

### 7. Ce qui change dans les garde-fous existants, ecrit dans sa forme exacte

| # | Garde-fou | Changement du | Ce qui le rappelle si on l'oublie |
|---|---|---|---|
| 1 | `LIllustrationDeContenuResoutEtAucuneNeDortTest` | Une proposition prefixee `oeuvre-` est une declaration, au meme titre qu'une `illustration` : elle doit resoudre dans `static/oeuvres/`, et elle compte pour *"aucune ne dort"*. Plus une morsure : une proposition `oeuvre-inconnue` reste rouge | Le rouge du garde-fou lui-meme : une oeuvre servie seulement en proposition y est aujourd'hui declaree dormante |
| 2 | Invariants de la decision 5 | Trois clauses, perimetre derive (tout item servi dont une proposition porte le prefixe), mordues **en mutant un item reel** de `cp-decrire-une-oeuvre` en memoire, pas sur une fixture de ma main | Rien aujourd'hui - population vide, donc due avec le premier paquet (`ADR-0051`) |
| 3 | `LeTexteServiPorte...textesServis` | La decision 6, et une morsure : un identifiant rendu `luParLEnfant = true` fait rougir le garde-fou d'accent sur `oeuvre-champ-de-ble` | Le faux rouge mesure en decision 6 |
| 4 | Garde-fou de rendu, `bootstrap/rendu` | Neuf : pour tout item servi a propositions-images, aucun ecran rendu (frais, echec, revelation, fin) ne porte `oeuvre-` dans un noeud de texte, un `alt`, un `aria-label` ou un `title`, et chaque bouton a un nom accessible non vide. Morsure : le gabarit reel mute en memoire en `th:text="${proposition}"` | Rien - avec le 2, **les deux sans alarme** ; c'est la clause 1 de la decision 4 |
| 5 | `RessourcesTiercesTest` | Cliquet de references de production admises **3 -> 4** (`troisReferences...`), la forme `'/oeuvres/' + ${proposition} + '.jpg'` etant la forme deja admise, au terme pres. La morsure d'etroitesse existante reste rouge sur deux termes calcules | Le cliquet exact a 3 |
| 6 | `ChaqueReproductionServiePorteLaPreuveDeLOeuvreEtCelleDeSaPhotographieTest` | Aucune clause ne change. Les deux constantes se relevent sur le vivier livre (section suivante) | Le cliquet exact a 8 et 176 474 |
| 7 | `HabillageDesEcransTest`, gardes de plafond CSS | Seulement si une classe neuve est posee ; reutiliser `.image-item` n'en pose aucune | Le garde-fou d'habillage, qui rougit sur une classe qu'aucune feuille ne nomme |

### 8. Le poids, et les deux constantes de `ChaqueReproductionServie...Test`

**Methode.** Poids servi : `wc -c` sur les huit fichiers de `static/oeuvres/` d'`adapter-web`. Poids
des trois oeuvres propres a `ART-CP-10`, retirees par `1db6348f` (`DEC-0109` section 10.3) : `git
cat-file -s 1db6348f^:<chemin>`. Dimensions : en-tete `SOF` des trois fichiers extraits de
`1db6348f^`.

| Grandeur | Valeur | Source |
|---|---|---|
| Huit reproductions servies | **176 474 octets**, egal a `POIDS_TOTAL_MAXIMUM` | `wc -c`, arbre a `f68458de` |
| `oeuvre-champ-de-ble` | 31 398 octets, 352 x 280 px | `1db6348f^` |
| `oeuvre-averse-sur-le-pont` | 21 707 octets, 242 x 352 px | `1db6348f^` |
| `oeuvre-jeune-femme-aiguiere` | 21 336 octets, 313 x 352 px | `1db6348f^` |
| Les trois | **74 441 octets** | somme |

**Si le vivier livre est exactement ces trois oeuvres en plus des huit** : `REPRODUCTIONS_SERVIES`
passe de **8 a 11**, `POIDS_TOTAL_MAXIMUM` de **176 474 a 250 915**, et
`PLAFOND_DE_PLUS_GRAND_COTE_EN_PIXELS` reste a **352** (les trois fichiers y sont deja). Les trois
entrees du registre `squad/corpus/oeuvres-domaine-public.yaml` se restaurent depuis `1db6348f^`, a
l'octet, avec leurs preuves deja verifiees. **Si `nadia-prof` change une paire, ces deux nombres
sont faux** et le cliquet exact le dira : le lot les releve sur ce qu'il installe, jamais sur ce
tableau. Les huit oeuvres d'`ART-CP-09` servent de distracteurs **sans un octet de plus**.

Ce que cela change au fat jar : +74 441 octets sur environ 21,8 Mo (`ADR-0082`, artefact du
2026-09-04), soit moins de 0,4 %. Rien au packaging.

---

## Regle executable

### La regle ArchUnit, ecrite, verte et mordue dans ce geste

`bootstrap/src/test/java/com/recreo/archi/DecouplageMecaniqueContenuTest.java`, clause **(13)**,
`uneMecaniqueAPropositionsNeLitJamaisCeQuUnePropositionEcrit` :

```java
@ArchTest
static final ArchRule uneMecaniqueAPropositionsNeLitJamaisCeQuUnePropositionEcrit = noClasses()
        .that().resideInAPackage(MECANIQUES)
        .and().resideOutsideOfPackage(SAISIE)
        .should().callMethodWhere(uneLectureDeTexte())
        .orShould().dependOnClassesThat().resideInAPackage("java.util.regex..")
        .allowEmptyShould(false)
        .as("ADR-0104 : une mecanique a propositions ne lit jamais ce qu'une proposition ecrit. ...");
```

`uneLectureDeTexte()` : tout appel d'une methode de `java.lang.String` autre que `equals` et
`hashCode`.

**Ce qu'elle interdit reellement.** La decision 2 laisse `BanditManchotSession` inchangee ; la
tentation exacte de ce chantier est d'y ecrire `proposition.startsWith("oeuvre-")` pour reveler,
compter ou melanger autrement une proposition-image. Ce serait faire entrer une population d'images,
fait de rendu, dans une mecanique. La regle le refuse sous ses deux formes, l'appel de methode de
`String` et l'expression reguliere.

**Perimetre** : toutes les mecaniques sauf la saisie, exclusion nommee avec son motif - le verdict de
la saisie *est* une lecture du texte ecrit (`ADR-0058`, `NormalisationDeSaisie`). L'inclusion est le
defaut : une quatrieme mecanique y entre sans qu'on y pense.

**Mesures, chacune avec sa commande.**

- **Verte sur la production** : `bash outils/mvn-jdk21.sh -B -q -pl bootstrap -am test
  -Dtest='DecouplageMecaniqueContenuTest,LesReglesMordentTest' -Dsurefire.failIfNoSpecifiedTests=false`
  -> `DecouplageMecaniqueContenuTest` 13 tests, 0 echec ; JDK 21.0.10 rapporte par Surefire.
  Population : les classes de production de `mechanics.banditmanchot` et `mechanics.designation`,
  non vide (`allowEmptyShould(false)`).
- **Mordue sur un contre-exemple** (`ADR-0003`) :
  `bootstrap/src/test/java/com/recreo/domain/mechanics/contreexemple/MecaniqueQuiLitSesPropositionsContreExemple.java`,
  refuse par `LesReglesMordentTest.uneMecaniqueAPropositionsQuiLitUnePropositionEstRefusee`, qui exige
  dans le message les **deux** formes, `String.startsWith` et `java.util.regex`. Meme commande ->
  `LesReglesMordentTest` 38 tests, 0 echec.
- **Mordante sur du materiau reel**, parce qu'un contre-exemple de ma main ressemble a ce que
  j'imagine (lecon de s05). Sonde temporaire (non commitee, retiree) qui applique la meme condition
  au paquet `mechanics.saisie` de production, le seul ou une lecture de texte existe : **3 lectures
  reelles trouvees**, `NormalisationDeSaisie -> String.charAt`, `String.length`,
  `String.toLowerCase` ; `SaisieSession -> String.equals` est bien ignore. La condition voit donc
  une vraie lecture, et laisse passer la seule comparaison permise.

- **Reacteur complet** : `bash outils/mvn-jdk21.sh -B clean verify` sur la racine, avec ce diff ->
  **BUILD SUCCESS**, six modules ; Surefire `bootstrap` 295 tests, 0 echec. Un premier passage, avant
  l'ecriture de ce fichier, etait rouge sur un seul test, attendu :
  `ToutesLesReferencesDeDecisionResolventTest` refusait une reference a `ADR-0104` qui ne resolvait
  pas encore. **Jobs de CI lances** : le job `build` (ce `verify`) et le juge du job `chemins`
  (`verifier-chemins.sh` sur les fichiers suivis et les fichiers neufs, vert). **Non lances** : le
  job des prefixes de commit (aucun commit) et le job des outils.

**Ce qu'elle ne voit pas, nomme plutot que decouvert** : une lecture faite par une methode de
`CharSequence` appelee sur un type declare `CharSequence`. Une concatenation `+` est compilee en
`invokedynamic` et n'est pas un appel de methode de `String` : composer un message d'exception reste
permis.

**Compte du depot.** Methode : lignes des fichiers `*.java` suivis par Git (`git ls-files`, ce qui
exclut les worktrees d'agents) qui correspondent a `^\s*(public |private |protected )?static final
ArchRule ` d'une part, a `^\s*@ArchTest\s*$` d'autre part. **Avant ce diff : 38 et 35. Apres : 39
et 36.**

### Ce qui n'est pas de l'ArchUnit, et attend le code de production

Les garde-fous 1 a 5 de la decision 7. **Ils ne sont pas des regles ArchUnit** : leurs objets sont
des paquets YAML, des fichiers d'images et des documents HTML rendus, dont aucun n'est un type Java.
Les garde-fous 2 et 4 **ne peuvent pas etre ecrits avant le lot** : le 2 a une population vide tant
qu'aucun item n'a de propositions-images (`ADR-0051`), et le 4 juge une branche de gabarit qui
n'existe pas - l'ecrire aujourd'hui donnerait une regle verte sur rien. Le mecanisme qui les garde est
la porte technique, `mvn -B clean verify` sur le reacteur complet, dans le meme commit que le code
(`CLAUDE.md`, regles cles).

---

## Consequences

**Les bonnes.**

- `ART-CP-10` devient constructible sans une ligne dans `domain` ni dans `application`, sans mecanique
  ni forme d'item, sans cle YAML neuve. Le principe directeur tient une seconde fois face a un
  changement de nature du contenu.
- Le chantier ne touche que l'adaptateur web et des garde-fous. Le cout est en rendu, la ou est la
  difficulte reelle.
- La regle (13) protege aussi les deux mecaniques existantes au-dela de ce chantier : aucune ne peut
  plus brancher sur le texte d'une proposition sans un `ADR`.

**Les mauvaises, sans les adoucir.**

- **La population d'une proposition repose sur un prefixe.** Un prefixe est une convention de nom ;
  sa force vient des garde-fous qui le verifient des deux cotes (decision 7, points 1 et 2). Sans le
  point 2, une rangee melangee passerait.
- **Le prefixe est lu a trois endroits de l'adaptateur** : la branche d'illustration du gabarit
  (`ADR-0082`), le booleen de manche et le test de fin de partie du controleur.
- **Quatre images sur un ecran de CP.** A 176 px CSS chacune, trois propositions-images ne tiennent
  pas sur une rangee d'un telephone de 360 px. Le pli et le retour a la ligne sont touches, et
  routes.
- **Rien ne garantit que la propriete se voit a 176 px.** `nadia-prof` l'a dit pour la touche de Van
  Gogh. Ce plafond est celui du depot, et le monter coute des fichiers plus lourds et un amendement
  d'`ADR-0082`.
- **La moitie "reconnaitre des oeuvres deja rencontrees" reste non servie** (`nadia-prof`, Q5). Cet
  `ADR` ne chiffre pas la garantie d'ordre qui la servirait : personne ne l'a demandee.
- **Un nombre d'items borne par les paires.** Trois paires donnent au plus six items distincts (chaque
  oeuvre de la paire en reference, une fois). L'inegalite stricte d'`ADR-0023` borne alors
  `nombreDeManches` a 5 dans l'entree de catalogue, ou demande plus de paires.

---

## Alternatives considerees

| Alternative | Pourquoi elle est ecartee |
|---|---|
| **Deux images par item, propositions en texte** (une seconde composante `illustrationSeconde` sur `Question`) | La reponse reste ecrite, donc decrit ce qui est commun : motif 1 de Q1 (*"une etiquette ecrite evoque un sujet, jamais une touche"*). Elle ajoute de plus une composante a `Question` et deux cles au chargeur, pour une forme que `nadia-prof` nomme sans la preferer |
| **Une quatrieme forme d'item typee** (`OeuvresARapprocher`, cles YAML dediees) | Sa seule vraie qualite : les garde-fous de texte ne verraient pas les identifiants. Son prix : une quatrieme permission d'`Item` (huit `switch` rouverts a la troisieme, mesure d'`ADR-0080`), une fabrique, et une session qui ne sait tenir que des `Question` - donc une mecanique neuve qui duplique la boucle de partie de `bandit-manchot` (336 lignes) pour un geste identique. Le critere d'`ADR-0058` n'est pas rempli. La qualite s'obtient a un endroit, decision 6 |
| **Une cle d'item declarant que les propositions sont des images** (`propositionsEnImage: true`) | Ecartee par `ADR-0082` pour le meme motif sur l'illustration : une cle peut mentir, un prefixe verifie contre le repertoire non. Elle ajouterait une composante a `Question` qu'aucun autre paquet ne remplirait |
| **Un montage : une seule image qui juxtapose la reference et les candidates, servie a la mecanique de designation** | Zero code, mais une oeuvre derivee : `ADR-0082` decision 2 n'admet que le fichier source redimensionne, *"sans traitement qui pourrait faire naitre une oeuvre nouvelle"*. Le registre perd sa correspondance un fichier - une oeuvre, et quatre oeuvres dans 352 px les rendent chacune a environ 88 px, la ou la touche ne se voit deja pas a 352 |
| **Une mecanique d'appariement** (une grille d'oeuvres, l'enfant forme les paires) | Un geste vraiment neuf, donc une mecanique, une forme, une session, une route : le cout d'`ADR-0080`. Aucun usage constate ne le demande - l'exigence de `nadia-prof` est deux oeuvres visibles ensemble, que la decision 1 tient |
| **Lire le prefixe dans la session** pour reveler l'oeuvre autrement | C'est exactement ce que la regle (13) interdit : un fait de rendu dans une mecanique |

---

## Cout et decoupage en taches

**Methode.** Les jours sont mon estimation de developpement, sur le precedent mesure d'`ADR-0082`
(T2 a T4, environ deux jours dans son estimation). Le prix d'audit applique le tarif publie par `nadia-prof`
(`ARBITRAGE-PREALABLE-s28`, section 4.3) avec l'arithmetique de `DEC-0109` section 2 ; il est le sien
et sa confirmation lui appartient.

| # | Tache | Qui | Cout | Depend de |
|---|---|---|---|---|
| **T1** | `ADR`, regle (13), contre-exemple et morsure | `theo-archi` | **fait par cet `ADR`** | rien |
| **T2** | Garde-fous 1, 2 et 3 de la decision 7, morsures sur materiau reel mute | dev | ~0,5 j | rien |
| **T3** | Rendu : booleen et test de prefixe du controleur, branche de bouton-image, revelation, fin de partie, garde-fou 4, cliquet 3 -> 4 de `RessourcesTiercesTest` | dev | ~1 j | arbitrages de `ines-design` (mise en page, marque de revelation) et de `nadia-prof` (nom accessible, phrase de revelation, `C5`) |
| **T4** | Corpus : restauration des fichiers et entrees de registre depuis `1db6348f^`, releve des deux constantes | dev | ~0,25 j | le choix des paires par `nadia-prof` |
| **T5** | Paquet `ART-CP-10`, entree de catalogue, fiche de conformite | dev, audit `nadia-prof` | contenu | T2 a T4, et l'arbitrage prealable de `nadia-prof` |

**Developpement : environ 1,75 jour pour le dispositif (T2 a T4)**, qui tient dans un sprint a cote
d'autre chose.

**Audit : 2,0** sur la borne de 5,0 de `nadia-prof` - un lot d'arts plastiques CP, matiere dont elle
ne se declare pas la source (1,5), illustre (+0,5, `REC-1`). **Deux reserves qui sont a elle et a
`camille-po`** : `PAR-11` (+0,5 par instance sur une matiere a conditions inedites) peut s'appliquer a
une forme de presentation qu'elle n'a jamais jugee, ce qui donnerait **2,5** ; et l'arbitrage prealable
sur la forme (decision 4, points 2 a 5) n'est dans aucune ligne publiee de son tarif.

---

## Ce que je route, sans le decider

**A `nadia-prof`** :

1. **Bloquant, et c'est le seul qui conditionne le statut de cet `ADR`.** Ta condition `C5` clause 1
   (*"aucune image [...] a l'interieur d'une boite de proposition"*), lue avec ta clarification du
   2026-08-24 (elle ne mord pas sur ce qui constitue la proposition entiere) et ton motif du
   2026-08-16 (ce qu'on lit dans une boite reste verifiable par autre chose que toi) : mord-elle sur
   une reproduction qui constitue la proposition entiere ? Si oui, la decision 1 tombe et le repli
   de la section suivante, point 6, prend sa place. Ta clause 2 (le jeu d'images **du cabinet** ne
   depend pas de l'item) vise l'habillage du meuble et non le contenu d'une boite : je la lis ainsi,
   confirme-le ou corrige-moi.
2. La forme "propositions en image" sert-elle `ART-CP-10` ? Tu l'as nommee recevable ; je la retiens
   pour le motif de la decision 1, et je ne prononce pas ce qu'elle vaut.
3. Le nom accessible de chaque bouton-image (decision 4, point 2), et la phrase qui accompagne la
   revelation quand la reponse n'est plus un texte (point 4).
4. L'ecran de fin : faut-il y revoir l'oeuvre attendue (point 5) ?
5. Les paires, les distracteurs, le nombre de propositions (deux ou trois), et la visibilite de chaque
   propriete a 176 px CSS.

**A `ines-design`** : la mise en page de trois boutons-images et d'une reference sous le pli, aux
largeurs deja mesurees, et la marque visuelle de la revelation.

**A `camille-po`** : voir le rapport de cet `ADR` et la section "Ce qui me ferait changer d'avis".

---

## Ce qui me ferait changer d'avis

1. **Une proposition-image doit etre un dessin maison** (`static/illustrations/`, sans prefixe). Le
   prefixe ne suffit plus a dire qu'une proposition est une image ; une declaration typee devient
   necessaire, et l'alternative "quatrieme forme d'item" se rediscute.
2. **Une rangee melangee texte et image est demandee par `nadia-prof`.** L'invariant 1 de la decision
   5 tombe, et chaque garde-fou de texte doit alors juger proposition par proposition.
3. **Plus d'un garde-fou de texte doit etre exempte au cas par cas** au lieu du seul point de la
   decision 6. Alors les identifiants sortent des cles `bonneReponse` et `distracteurs`.
4. **`nadia-prof` juge qu'aucune paire ne montre sa propriete a 176 px CSS.** Alors la taille rendue
   monte, les fichiers passent au-dela de 352 px, et `ADR-0082` est amende avec son cliquet de poids.
5. **`camille-po` demande la garantie d'ordre de Q5.** Elle touche `ContentSource` dans `domain`, et
   demande son propre `ADR`.
6. **`nadia-prof` maintient `C5` clause 1 sur une reproduction en proposition.** Repli, ecrit
   maintenant pour n'avoir pas a l'inventer sous pression : **les oeuvres candidates sortent des
   boites et se montrent dans la zone d'illustration**, a cote de la reference, chacune avec un
   numero de rang ; **les boites portent ce numero seul**, un caractere qui constitue la proposition
   entiere (le cas que sa clarification du 2026-08-24 laisse hors de `C5`). Le contenu ne change pas -
   les propositions restent les identifiants -, le controleur rend les images dans l'ordre deja
   melange des propositions et numerote les boutons dans le meme ordre. **Le prix architectural est
   le meme** : zero ligne de `domain`, d'`application` et de chargeur, la regle (13) inchangee, les
   garde-fous 1 a 3 inchanges, le garde-fou 4 etendu aux images de la zone d'illustration, et le
   cliquet de `RessourcesTiercesTest` qui passe aussi a 4. **Le prix pedagogique est a `nadia-prof`** : un
   enfant qui rapproche puis presse "2" lit une etiquette, et c'est l'objection qu'elle a faite au
   quadrillage (*"un enfant qui repond 'A3' en lisant trois etiquettes n'a pas montre ou il est"*).
