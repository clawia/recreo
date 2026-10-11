# [Sprint s56] Avis de faisabilite - theo-archi

- **Date** : 2026-10-09 21:04 (UTC+02:00), lu par `date`.
- **Auteur** : theo-archi. Branche `s56-theo-avis`, worktree dedie, base `dfcec37c` (tip de `round-two`, planning de s56 fusionne). `bash outils/verifier-lignee.sh` sort 0
  (*"descend de la lignee de round-two"*), `git status --short` vide avant ecriture.
- **Perimetre** : `AV1` a `AV8` du planning (`AV6` = `A4`, `AV7` = `A14`) ; les stories du planning de s56 (`DEC-0116` section 7, backlog "Sprint 56") ; `ADR-0104` ;
  `rapport-lea-dev-mecanique.md` de s55. L'agent qui m'a lance nomme `AV1` a `AV7` ; le planning en compte huit, je reponds aussi a `AV8`, qui m'est route par `DEC-0116` 4.
  Ce commit n'ajoute que ce fichier : aucun `ADR`, aucun Java, aucun YAML, aucune CSS.
- **JDK des mesures** : **21.0.10**, propriete `java.version` des rapports `TEST-*.xml` de Surefire (`ADR-0071`). Enveloppe `bash outils/mvn-jdk21.sh`. Toute mesure partielle porte `-am`.
- **Methode des mesures sur un autre arbre** : deux worktrees jetables ouverts en `--detach` (l'un sur `c315c024`, parent de `a3606b2f` ; l'autre sur `dfcec37c`), voisins du
  checkout, supprimes apres mesure. Aucune mutation n'a touche ce worktree.

## 1. Verdict par story

| Story | Verdict | Condition ou motif |
|---|---|---|
| `S55-ART-CP-10-PAQUET` | **faisable en l'etat**, sans `ADR` | prerequis de `lea-dev` en section 6 ; quatre constantes, pas deux, montent dans le commit du fichier |
| `S55-ART-CP-10-PLAFOND` | **faisable en l'etat**, sans `ADR` | forme et prix en section 2 ; ce n'est pas une regle ArchUnit |
| `S56-ART-CP-10-ECHEC` | **faisable en l'etat** par la voie de l'image, sans `ADR` | section 4 ; le critere de la marque reste a amender (`camille-po`, apres `Q1`) |
| Entree de catalogue du paquet | **faisable en l'etat** | l'assertion "perimetre reel vide" du garde-fou 4 bascule dans ce commit (section 6) |
| `S56-BR-FRA-CE1-13` | **faisable en l'etat** | un piege connu : la consigne porte la question (section 6, point 5) |
| `S55-ACV-CE1-R2`, `S55-CLM-CE1-R1`, `S55-EMC-CM1-R8`, `S55-EMC-CM1-R9` | **faisables en l'etat** | edits de contenu sous les garde-fous existants ; aucun avis technique de plus |
| `S56-ARB-A`, `S56-ARB-B`, `S56-ART-CP-10-OEUVRES`, `S56-ART-CP-10-FORME`, `S56-AUD-ART-CP-10`, rejeux `ACV-CE1-R1`, `EXC-R16` | sans objet technique | portes de `nadia-prof` ; la voie technique de `Q1`, `Q5`, `Q6` est en section 4 |

**Infaisable en l'etat : rien parmi les stories.** Deux reponses ne sont pas completes et je le dis la ou elles sont : la cause de `A4` sur GitHub (section 7) et la garde de la forme 3 (section 8, refus).

## 2. `AV1` - le garde-fou du plafond de 176 px (`S55-ART-CP-10-PLAFOND`)

### 2.1 Ce qui tient le plafond aujourd'hui, mesure

L'image d'une proposition porte `.image-item`, mais `.propositions-oeuvres .proposition img` (specificite (0,2,1), `recreo.css` lignes 1795 a 1803) remplace son `max-height: 11rem`
par `max-height: 100%`. Le plafond tient par l'arithmetique de la case (lignes 1777 a 1788) : `max-width: calc(11rem + 0.5rem + 0.25rem)`, `aspect-ratio: 1 / 1`, `padding: 0.25rem`,
`box-sizing: border-box` universel (ligne 58), bordure de `.btn` de la feuille vendorisee, bordure de `0.125rem` de l'image. Releve de s55 (F4 de ma confirmation) : boite d'image
au plus 178 px CSS, dessin au plus 174 px.

**Correction de mon propre constat F4 de s55.** J'y ecrivais que `ChaqueIllustrationDeContenuDeclareSaGeometrieEtResteSousLeCliquetDePoidsTest` *"lit le seul bloc `.image-item`"*.
C'est faux : il porte `PLAFOND_DE_RENDU_EN_PIXELS = 176` en constante (ligne 172) et ne lit pas `.image-item` dans la feuille (`grep -rn '".image-item"' --include=*.java` ne rend rien).
Le plafond de base n'est donc garde par rien non plus. Mesure : `.image-item { max-height: 11rem }` mute en `14rem` (ligne 449), worktree jetable sur `dfcec37c`,
`bash outils/mvn-jdk21.sh -B clean verify -pl bootstrap -am -Dmaven.test.failure.ignore=true` (perimetre : reacteur de `bootstrap` avec `-am`, cinq modules) :
**0 echec, 0 erreur sur 2307 tests** (120, 18, 407, 1423, 333 et 6 d'integration), `BUILD SUCCESS`, worktree jetable supprime ensuite. La mutation de la case (`calc(11rem` vers `calc(14rem`, ligne 1780) rend 0 echec sur 2307 (`DEC-0116` 0.3, `camille-po`, non relancee par moi).

### 2.2 Forme

Un test JUnit dans `adapter-web`, a cote des lecteurs de feuille existants (`TailleDesTextesEtBoutonsTest.blocsMaison`, `proprieteEnRemSiExiste`), qui derive de `recreo.css` :

1. **Le plafond de base** : `max-height` de `.image-item`, en rem, fois 16, est egal a `PLAFOND_DE_RENDU_EN_PIXELS` (176). C'est l'ancre : elle lie la feuille au nombre decide
   (`ADR-0082`, `ADR-0104` decision 4.3) au lieu de suivre la feuille. Une egalite ici, parce qu'un plafond de base qui change est un amendement d'`ADR-0082`.
2. **Le plus grand cote du dessin d'une proposition** : somme des termes en rem du `calc` de `max-width` de `.propositions.propositions-oeuvres .proposition`, moins deux fois
   son `padding`, moins deux fois la **plus petite** largeur de bordure declaree sur une image sous `.propositions-oeuvres` (toutes regles confondues, `border: none` vaut 0),
   **au plus** egal au plafond du point 1. Aujourd'hui : 11,75 - 0,5 - 0,25 = 11,0 rem, egal a 11 rem. La bordure de `.btn` (feuille vendorisee) n'est pas comptee : l'omettre
   surestime le dessin, donc le test reste du bon cote.
3. **Les conditions dont l'arithmetique depend** : la case declare `aspect-ratio: 1 / 1` (sans elle `max-height: 100%` ne borne plus la hauteur d'une reproduction haute) ;
   l'image declare `max-width: 100%` et `max-height: 100%`.
4. **Fermeture du perimetre, derivee du selecteur et non d'une liste** : aucune autre regle de la feuille maison dont le selecteur contient `propositions-oeuvres` ou
   `ecran-deux-oeuvres` et dont le dernier compose vise la case (`.proposition`) ou l'image (`img`) ne declare `width`, `height`, `min-width`, `min-height`, `max-width`,
   `max-height`, `padding`, `aspect-ratio` ou `flex-basis`. La rangee (`.propositions-oeuvres`, qui declare son propre `padding`) n'est ni l'une ni l'autre. Les regles d'etat
   (`.deja-essayee`, `[data-oeuvre-revelee=true]`) peuvent declarer une bordure ou un `outline` : le point 2 prend la plus petite bordure.

**Pourquoi "au plus" et non "egal"**, contre le mot *"l'egale"* de mon critere de s55 : c'est un plafond. Une case plus petite (reponse de `nadia-prof` a `Q2`, releve `Q7`
d'`ines-design`) ne viole rien ; une egalite la ferait rougir a tort. L'egalite reste au point 1, sur l'ancre.

### 2.3 Morsures exigees, sur la feuille reelle mutee en memoire (lecon de s05 : pas une fixture de ma main)

| # | Mutation | Clause qui doit rougir |
|---|---|---|
| P1 | ligne 1780, `calc(11rem` vers `calc(14rem` (critere du backlog) | 2 |
| P2 | `padding: 0.25rem` de la case vers `0` | 2 |
| P3 | `border: none` ajoute a `.propositions-oeuvres .proposition.deja-essayee img` | 2 (plus petite bordure) |
| P4 | `max-height: 100%` de l'image vers `none` | 3 |
| P5 | `aspect-ratio: 1 / 1` retire | 3 |
| P6 | regle `.propositions-oeuvres .proposition img { min-height: 14rem; }` ajoutee | 4 |
| P7 | `.image-item { max-height: 11rem }` vers `14rem` | 1 |

Et le critere du backlog sur l'arbre : la mutation P1 dans le fichier, `bash outils/mvn-jdk21.sh -B clean verify -pl bootstrap -am -Dmaven.test.failure.ignore=true`, au moins un echec nomme.

### 2.4 Ce qu'il ne voit pas, nomme

Une regle de la feuille vendorisee ; une regle dont le selecteur atteint l'image sans nommer `propositions-oeuvres` ni `ecran-deux-oeuvres` (par exemple `.cabinet img`) ; un attribut
`style` du gabarit. Le rendu reel, qui seul tranche, reste un releve a l'ecran (`Q7`, audit).

### 2.5 Prix, et pourquoi pas une regle ArchUnit

**Environ 0,25 j de `lea-dev`**, une classe de test, aucune ligne de production. Hors `PAR`, 0,0 pour `nadia-prof`. **Pas d'`ArchRule`** : l'objet est une feuille CSS, pas un type
Java ; le mecanisme qui le garde est le job `build` (`mvn -B verify`). Les comptes d'`ArchRule` et d'`@ArchTest` ne bougent pas. **Pas d'`ADR`** : la decision existe (`ADR-0104` 4.3,
`ADR-0082`) ; ce test paie la dette F4 ouverte a s55, il ne decide rien de neuf.

## 3. `AV2` - la question de corpus de la regle 2 de `nadia-prof`

**Le corpus de provenance prouvee atteignable a s56 compte 28 reproductions, pas 11.** `1db6348f` (`[MARC] s50`, *"vivier reduit a huit oeuvres"*) a retire vingt reproductions et
leurs entrees de registre ; `1db6348f^` les porte toutes, sous le meme garde-fou qu'aujourd'hui (`REPRODUCTIONS_SERVIES = 28`, `POIDS_TOTAL_MAXIMUM = 692_809` dans
`ChaqueReproductionServiePorteLaPreuveDeLOeuvreEtCelleDeSaPhotographieTest` a `1db6348f^`). Le motif du retrait est *"pas d'usage dans le paquet qui s'ecrit maintenant"*
(`DEC-0109` 10.3), pas un defaut de preuve, et la meme section nomme la restauration Git comme le moyen de les servir. Les huit fichiers servis aujourd'hui sont les memes blobs
qu'a `1db6348f^` (`git ls-tree` des deux arbres, `diff` vide).

Commandes : `git ls-tree -l 1db6348f^ adapters/adapter-web/src/main/resources/static/oeuvres/` (28 fichiers) ; `git show 1db6348f^:squad/corpus/oeuvres-domaine-public.yaml | grep -c "identifiant:"` (28).

**Ce que je ne reponds pas** : qu'un distracteur *"partage le sujet sans la propriete"* est un jugement sur l'image, a `nadia-prof` seule (`S56-ART-CP-10-OEUVRES`). Le registre prouve
les droits, pas la fidelite ni le sujet (en-tete du registre, *"CE QUE CE REGISTRE NE PROUVE PAS"*). Une 29e reproduction demande un sourcing (`ADR-0082` T2 a T4 : fiche CC0
lue par une personne) qu'aucun agent ne peut faire sans acces au web : **infaisable a s56 sans fichier fourni par l'utilisatrice**.

## 4. `AV3` - voie de la marque de l'echec (`Q1`), structure de `Q5` et `Q6`

**`Q1`, voie : la regle `.propositions-oeuvres .proposition.deja-essayee img`, c'est-a-dire l'image et non le bouton.** Motifs mesures :

- Une bordure en tirets sur cette regle passe toute la suite : 0 echec sur 2303 (F3 de ma confirmation de s55, commande
  `bash outils/mvn-jdk21.sh -B clean verify -pl bootstrap -am -Dmaven.test.failure.ignore=true`). L'image n'est pas un pressable : `ADR-0046` ne s'y applique pas.
- Sur le sujet `.proposition.deja-essayee`, une `border` ou un `outline` font naitre les exigences d'etats d'`ADR-0046` (E2, E3 de `lea-dev`) : la voie est possible mais plus chere,
  et elle touche l'anneau de focus de `S17-1`.
- Le pseudo-element du bouton (`::after`) est le voile, mis a `content: none` sous `.propositions-oeuvres` (`ADR-0026`) et positionne sur toute la case : une marque par lui se dessinerait
  sur l'image, ce que `X3` refuse. Une image n'a pas de pseudo-element. `filter` est hors de la propriete demandee (une marque de forme) et la feuille s'interdit deja de filtrer un dessin
  (commentaire de `.image-item`).

**Ce que la voie impose a la forme, sans la choisir** : la marque est une propriete de la boite de l'image (style, largeur ou couleur de bordure, `outline`, `outline-offset`). Une bordure plus
epaisse reduit le dessin (`border-box`), elle ne monte pas le plafond ; une bordure retiree le monterait de 4 px, et le garde-fou de la section 2 (clause 2, P3) le refuse. **D'ou l'ordre du planning,
que je confirme : `PLAFOND` avant `ECHEC`**, pour que la marque soit jugee par le garde-fou. Le choix de la marque est a `ines-design` (proposition) et `nadia-prof` (jugement).

**Garde de la marque, prix** : un test du meme fichier que la section 2, qui exige que `.propositions-oeuvres .proposition.deja-essayee img` declare une propriete de bordure ou d'`outline`
differente de celle de `.propositions-oeuvres .proposition img`, en plus de l'`opacity`. Il ne juge pas que la marque se voit : c'est l'audit. Environ 0,1 j, dans le commit d'`ECHEC`.

**`Q5`, meuble conserve ou retire.** Conserver : zero code. Retirer : **pas par `th:if`** sur le cabinet, que `ADR-0030` point 1.c interdit et que `CabinetDeLaMachineTest` (lignes 145 et 146)
mord ; la feuille a deja la forme pour masquer un habillage sur un ecran marque, `body:has(.<marqueur>) .<habillage> { display: none }` (`recreo.css` lignes 183 a 193, contrat de
`LEntreeDeMenuNeSurvitPasAUnEcranDeJeuTest`). Seuls les freres decoratifs (`.cabinet-bandeau`, `.cabinet-levier`, `.cabinet-socle`) seraient masques, jamais `.cabinet`, qui porte le formulaire.
**Non mesure** : l'effet sur l'animation du levier (`ADR-0026`). Si `nadia-prof` retient "retirer", c'est une story a ecrire, avec cette mesure ; je ne l'ouvre pas sans son verdict.

**`Q6`, rang annonce et nom de groupe.** Nom de groupe : `role="group"` et `aria-label` sur le `form.propositions`, un attribut, aucune structure neuve. Rang : `aria-posinset` et `aria-setsize`
ne sont pas admis sur un bouton ; un rang annonce demande soit une liste (`role="list"`, chaque bouton dans un `role="listitem"`, ce qui change les enfants du conteneur `flex` et les
selecteurs `.propositions-oeuvres .proposition`), soit le rang dans le nom accessible (des mots, donc a `nadia-prof`, aucune structure). Taille de la phrase de revelation : une
regle de la feuille, sous `TailleDesTextesEtBoutonsTest`. Les trois tiennent dans `adapter-web`, sans `ADR`.

## 5. `AV4` - les valeurs de T4 apres le choix des oeuvres

Elles ne se fixent pas avant `S56-ART-CP-10-OEUVRES`. **La methode, exacte** : pour les `r` reproductions restaurees,
`REPRODUCTIONS_SERVIES = 8 + r` et `POIDS_TOTAL_MAXIMUM = 176_474 + somme des tailles`, chaque taille lue par `git cat-file -s 1db6348f^:<chemin>` ;
`PLAFOND_DE_PLUS_GRAND_COTE_EN_PIXELS` reste 352 : le plus grand cote des 28 fichiers de `1db6348f^` est 352 (segment `SOF` de chaque JPEG lu par un script Python hors depot
sur `git cat-file blob`). Fichiers et entrees de registre se restaurent a l'octet depuis `1db6348f^`.

| Reproduction restaurable | Octets | Reproduction restaurable | Octets |
|---|---|---|---|
| `oeuvre-amerique-ancienne` | 16 118 | `oeuvre-fresque-romaine` | 30 804 |
| `oeuvre-amphore-grecque` | 21 004 | `oeuvre-grande-jatte` | 20 595 |
| `oeuvre-autoportrait-deux-eleves` | 25 217 | `oeuvre-jeune-femme-aiguiere` | 21 336 |
| `oeuvre-autoportrait-leyster` | 24 248 | `oeuvre-licorne-captive` | 40 958 |
| `oeuvre-averse-sur-le-pont` | 21 707 | `oeuvre-photo-acropole` | 23 821 |
| `oeuvre-bateau-egyptien` | 10 783 | `oeuvre-photo-notre-dame` | 21 245 |
| `oeuvre-belles-heures` | 30 417 | `oeuvre-photo-pyramides` | 29 309 |
| `oeuvre-carreau-iznik` | 50 170 | `oeuvre-rhinoceros` | 39 747 |
| `oeuvre-champ-de-ble` | 31 398 | `oeuvre-vase-ming` | 13 123 |
|  |  | `oeuvre-vase-shang` | 13 661 |

Somme des vingt : 516 335 octets ; 176 474 + 516 335 = 692 809, la valeur de `1db6348f^`, ce qui recoupe la table. Les valeurs 11 et 250 915 d'`ADR-0104` decision 8 restent justes
si et seulement si les trois oeuvres propres a `ART-CP-10` sont exactement celles restaurees.

**Une borne que le choix doit connaitre** : *"aucune ne dort"* (`LIllustrationDeContenuResoutEtAucuneNeDortTest`). Ne se restaure que ce qu'un item du paquet declare, en reference ou en
proposition.

## 6. `AV5` et prerequis de `lea-dev` pour le paquet (T4, T5)

**Decoupage, propose a `lea-dev`, qui decide avec moi (`DEC-0116` 3.2) :**

1. **Commit du paquet** (`S55-ART-CP-10-PAQUET`) : le fichier YAML, les reproductions restaurees, leurs entrees de registre et **quatre** constantes, dans **un seul** commit.
   Inseparables : des reproductions restaurees sans le fichier qui les declare rougissent *"aucune ne dort"* ; un fichier qui propose une reproduction absente rougit la resolution
   (morsure G1b de `lea-dev`). Les deux garde-fous lisent les repertoires de contenu, pas le catalogue : le fichier seul suffit a les faire tourner.
2. `S55-ART-CP-10-PLAFOND` : le test seul.
3. `S56-ART-CP-10-ECHEC` : une regle CSS et sa garde (section 4).
4. Entree de catalogue : `catalogue.yaml`, l'inventaire de `TexteAccentueExposeParLePortEntrantTest`, et l'assertion du garde-fou 4 qui exige aujourd'hui un perimetre reel vide.

**Prerequis, chacun lu dans le code :**

1. **Quatre constantes montent dans le commit du fichier, le backlog en nomme trois** : `ITEMS_A_PROPOSITIONS_EN_IMAGE` (0, `UnItemAPropositionsEnImageTientSesTroisInvariantsTest` ligne 69),
   `REPRODUCTIONS_SERVIES` et `POIDS_TOTAL_MAXIMUM` (critere 4), **et `ITEMS_QUI_DECLARENT_UNE_ILLUSTRATION`** (81, `LIllustrationDeContenuResoutEtAucuneNeDortTest` ligne 189,
   egalite exacte ligne 736) : chaque item du paquet declare une `illustration` `oeuvre-`, il monte de `N`. Precedent : `cp-decrire-une-oeuvre` l'a monte de 56 a 64 a s50.
   Un cliquet qui monte de la taille exacte du paquet n'est pas un assouplissement (C5).
2. **Le garde-fou 4 bascule a l'entree de catalogue**, pas avant : `LesPropositionsEnImageAtteignentLEcranSansJamaisSonIdentifiantTest`, l'assertion *"aucun jeu reel du catalogue ne
   sert de propositions-images"* (apres la ligne 362) devient la liste exacte du jeu branche. Le paquet hors catalogue ne la touche pas.
3. **Prealables a fusionner avant le worktree du paquet** : `S56-ARB-A` et `S56-ART-CP-10-OEUVRES` (ancetre exige, backlog). Mon avis est ce fichier.
4. **Prealables de l'entree de catalogue** : `PLAFOND` et `ECHEC` ancetres, `S56-ART-CP-10-FORME` et `Q7` ecrits. L'entree de catalogue porte `nombreDeManches` strictement inferieur au
   nombre d'items (`ADR-0023`) : avec trois paires et chaque oeuvre une fois en reference, six items au plus, donc 5 manches au plus (`ADR-0104`, consequences).
5. **Piege pour `marc-dev` (`-13`)** : l'en-tete de `ce1-verbe-sujet.yaml` dit que *"la question est portee par la consigne du jeu"*. Une consigne de catalogue qui finit par `?` derriere une espace
   ordinaire monte la dette `TYP-1` de `catalogue.yaml` et rougit le cliquet exact d'`AucunSigneDoubleNEstDetachableDeSonMotTest` : le caractere est U+00A0 (`ADR-0067`). Meme piege
   qu'a `ce1-classes-de-mots` (avis de s55, T3, simulation A, passage 1). Les items du paquet sont deja en U+00A0 : `grep -nP ' [?!:;]'` sur le fichier ne rend que des lignes de commentaire de l'en-tete (lignes 4 a 12).

## 7. `AV6` = `A4` - la cause du rouge de CI que `a3606b2f` corrige

**Mecanisme, reproduit.** Sur l'arbre d'avant le correctif, un jar dont toutes les entrees portent un horodatage fixe fait rougir `ProvenanceDesClassesAmontEstLeDepotTest`, parce que le
garde-fou compare l'entree du `.class` dans le jar (fixee vers 1980) au `.java` du checkout.

- Commande, worktree jetable sur `c315c024` (`a3606b2f^`) : `SOURCE_DATE_EPOCH=318211200 bash outils/mvn-jdk21.sh -B clean verify -pl application -am -Dmaven.test.failure.ignore=true`
  (perimetre : `domain` et `application`). **Resultat : `application` 1 echec sur 17**, `laClasseDeDomainVientDuTargetDuDepotEtNonDuDepotLocal`, message :
  *"GameMechanic (module domain) : le .class compile (...domain/target/recreo-domain-0.1.0-SNAPSHOT.jar, horodatage 1980-01-31T23:00:00Z) est plus ancien que son .java source
  (...GameMechanic.java, horodatage 2026-10-09T19:03:27.5542252Z). Deux causes possibles [...]"* (chemins de poste retires de la citation).
- Meme commande sans la variable : **0 echec sur 17**, `BUILD SUCCESS`. La variable est la seule difference.
- Avec le correctif, la meme variable rend vert (ma confirmation de s55, section 5, ligne "Repli `SOURCE_DATE_EPOCH`").
- Lecture du code qui rend la variable operante : `maven-archiver` 3.6.5, `MavenArchiver.parseBuildOutputTimestamp` lit `System.getenv("SOURCE_DATE_EPOCH")` quand
  `project.build.outputTimestamp` est absent (bytecode lu par `javap -c` sur le jar du depot local Maven). `maven-jar-plugin` 3.5.0 passe `${project.build.outputTimestamp}` par defaut.

**Declencheur sur GitHub : non etabli.** Ni le POM du depot, ni `spring-boot-starter-parent` 4.1.0, ni `spring-boot-dependencies` 4.1.0 ne fixent `project.build.outputTimestamp`
(`grep` sur les trois POM), et `.github/workflows/ci.yml` ne pose pas `SOURCE_DATE_EPOCH`. Il ne reste que l'environnement du runner, que je ne peux pas lire : `gh run list` rend
`HTTP 404` depuis ce poste, et aucun fichier du depot ne cite le journal du job rouge du 2026-10-08. Le message de `a3606b2f` (ma main, s55) affirme le mecanisme sans citer ce
journal. **Ce qui trancherait** : le journal du job `build` rouge sur le commit pousse avant `a3606b2f`, lu par l'utilisatrice (`A3`) ; s'il porte `horodatage 1980-...`, la cause est
celle-ci ; s'il porte autre chose, `a3606b2f` a corrige un mecanisme reel mais pas ce rouge-la. Je ne l'ecris pas comme cause tant que ce journal n'est pas lu.

## 8. `AV7` = `A14` - la formule de la forme 3 peut-elle etre gardee par un test ?

**Refus motive.** La seule propriete decidable sur le texte est *"un fichier qui ecrit `forme 3` contient la formule `recu de MASTER, sans source dans le depot`"* (blancs normalises,
la formule etant souvent coupee en fin de ligne). Mesures :

| Mesure | Commande | Resultat |
|---|---|---|
| Elle aurait mordu `R1` de s55 | `git show 303c6a82:<DEC-0115>`, `grep -c "forme 3"` et `grep -c "recu de MASTER, sans source dans le depot"` | 7 et 0 : rouge, a juste titre |
| Elle n'aurait pas mordu `R2` de s53 | meme lecture de `DEC-0112` | 0 `forme 3`, 0 `MASTER` : *"Relaye de l'utilisatrice"* ne porte aucun marqueur. Une attribution absente ne se lit pas |
| Ce qu'elle dit de l'arbre | `git grep -l "forme 3" -- squad docs CLAUDE.md .claude`, puis la formule cherchee sur chaque fichier aux blancs normalises | **47 fichiers rouges sur 65** |

Les 47 melangent des attributions sans formule et des **mentions de la forme elle-meme** (*"ni de MASTER (forme 3) au sens de la section 2 bis"*, planning de s53 ligne 29 ;
*"la borne de la forme 3"*). Le texte ne separe pas une attribution d'une mention : c'est le jugement que la convention confie au `relecteur-pedagogique` (`DEC-0062` section 4).
Une exemption de 47 fichiers laisserait une regle qui ne mord que sur les fichiers neufs, et y mordrait autant une mention qu'une attribution. Et elle se satisfait d'une seule
occurrence de la formule n'importe ou dans le fichier : `DEC-0115` passe aujourd'hui (formule ajoutee par l'amendement `08e4c9ad`) alors que sa ligne 294 etiquette encore "forme 3"
sans elle. **Elle interdirait donc reellement une chose** (un fichier neuf qui dit "forme 3" sans jamais ecrire la formule), et en manquerait deux sur les deux formes de defaut
observees. Je ne l'ecris pas.

**Ce qui me ferait changer d'avis** : une convention qui donne a l'attribution de forme 3 une forme structurelle (par exemple un bloc de citation dont la premiere ligne est la formule).
Alors "tout bloc de citation recu porte la formule" devient decidable. C'est un amendement de `squad/README.md` section 2 bis et de `DEC-0062`, a `sofia-sm`, `camille-po` et a
l'utilisatrice, pas a moi.

**Constat route, sans jugement** : `DEC-0114` (lignes 13, 52, 79) etiquette des sources "forme 3" sans la formule, ce qui ressemble a une troisieme occurrence que la relecture de s55 ne
compte pas. A verifier par le `relecteur-pedagogique`.

## 9. `AV8` - deux instances de francais CE1 et `ADR-0065`

**Sans objet a s56** : une seule instance porte du francais CE1 (A). Pour s57, ma reponse de s55 (avis de s55, T5) tient sans changement : un amendement d'`ADR-0065` et de
`AucunMotServiNeSEcarteDeSonAccentTest` est une story de `theo-archi`, une a la fois, fusionnee avant tout lot qui en depend ; le lot de la seconde instance attend. Ce n'est pas
encodable en `ArchRule` (l'objet est un `ADR` et une liste de test) ; le seul automate est le conflit de fusion sur ce fichier de test, qui signale la collision sans l'empecher.

## 10. `ADR` et regles ArchUnit

**Aucun `ADR` ouvert.** Aucune story de s56 ne demande une decision d'architecture neuve : le plafond et la marque executent `ADR-0104` (4.3, 7) et `ADR-0082` ; `Q5` et `Q6`
tiennent sous `ADR-0030`, `ADR-0046` et `ADR-0026` tels qu'ecrits. Un `ADR` s'ouvrirait si `nadia-prof` retient "retirer le meuble" et que la mesure de l'animation du levier
contredit la forme `body:has(...)`. Comptes ArchUnit inchanges : 39 `ArchRule`, 36 `@ArchTest` (methode d'`ADR-0104` : `git ls-files '*.java'`, puis les deux motifs de lignes ; recompte sur `dfcec37c`).
`ADR-index.md` n'a rien a changer.

## 11. Jobs de `.github/workflows/ci.yml` lances pour ce commit

Sur ce worktree, avec ce fichier, avant commit :

- **`build`** : `bash outils/mvn-jdk21.sh -B clean verify` depuis la racine, `BUILD SUCCESS`, 2307 tests, 0 echec, 0 erreur (120, 18, 407, 1423, 333 et 6 d'integration).
  L'etape "Package du fat jar" du job (`mvn -B -pl bootstrap -am package`) n'est pas lancee.
- **`chemins`** : `bash .github/scripts/verifier-chemins-test.sh` sort 0 ; `git ls-files` (moins le test du garde-fou) plus ce fichier, passes a `verifier-chemins.sh` : sort 0.
- **`outils`** : les juges de `outils/*-test.sh`, `squad/outils/*/*-test.sh` et `outils/*-test.py` : 10 lances, 10 sortis 0.
- **`prefixes`** : `verifier-prefixes-test.sh` et `commit-atteignable-test.sh` sortent 0 ; le sujet de ce commit passe dans `verifier-prefixes.sh` apres sa creation, hors de ce fichier.

Aucun de ces resultats n'est celui de la CI distante, que je n'ai pas lue.

## Amendement du 2026-10-09, 21:55 (theo-archi) - voie de O1, plafond d'`ines-design`, T4 de `CHOIX-OEUVRES-s56-art-cp-10.md`

Annotation datee ; rien au-dessus n'est reecrit. Base : `bb4041c8` (`round-two`, fusionne dans cette branche par avance rapide). Sources lues :
`ARBITRAGE-s56-forme-art-cp-10.md` (bloc du 2026-10-09 21:37, `Q1` et critere de la marque), `releves-s56/proposition-ines-forme-art-cp-10.md`
(sections 1.4 et 2.2), `CHOIX-OEUVRES-s56-art-cp-10.md` (sections 2 a 4). **Methode** : deux simulations dans des worktrees jetables en `--detach`
sur `bb4041c8`, supprimes apres mesure, chacune lancee par `bash outils/mvn-jdk21.sh -B clean verify -pl bootstrap -am -Dmaven.test.failure.ignore=true`
(perimetre : reacteur de `bootstrap` avec `-am`, cinq modules, 2307 tests). Les fichiers simules sont recopies des documents ci-dessus, pas ecrits par moi,
sauf l'en-tete `schema`, `id`, `descripteur` du paquet (modele de `cp-decrire-une-oeuvre.yaml` ; identifiant de ma main, `cp-rapprocher-des-oeuvres`).

### A.1 `AV3` remplace : la voie de O1 est faisable moyennant un amendement d'`ADR-0026`, et elle est gardable

Ma section 4 designait la boite de l'image ; `nadia-prof` retient O1. **Simulation S-O1** : la regle candidate de la section 1.4 d'`ines-design`
recopiee telle quelle (`padding-bottom: 3.25rem` sur `.propositions-oeuvres:has(.proposition.deja-essayee)` ; `.propositions-oeuvres .proposition.deja-essayee::before`),
`marque-echec-candidate.svg` copie en `static/images/marque-echec.svg`, et sa forme du plafond (A.2). **Resultat : 1 echec sur 2307.**

| Garde-fou nomme par l'agent lanceur | Verdict mesure par S-O1 |
|---|---|
| `ADR-0027`, pseudo-elements (`content` autre que vide ou `none` refuse ; `clip-path` hors sujet pseudo-element) | vert : `content: ""` est la forme admise, celle des deux barres du signal faux |
| `ADR-0046`, etats (`AucunEtatDePressableNEstAbandonneAuTiersTest` et sa famille) | vert : le `::before` ne declare rien que la feuille vendorisee ecrase sous `:hover`, `:active` ou `:focus-visible` |
| `HabillageDesEcransTest` | vert : aucune classe neuve |
| `ADR-0030` (`LHabillageNeRogneJamaisUnElementPressableTest`, `LHabillageResteImmobileTest`, `CabinetDeLaMachineTest`) | vert |
| `RessourcesTiercesTest` (`url()` vers un fichier local) | vert : la cible existe |
| **`ADR-0026` clause 3**, `LAnimationRetardeMaisNeDetermineRienTest.aucuneRegleMaisonNeRendUneActionInactionnable` | **rouge** : *"`.propositions-oeuvres .proposition.deja-essayee::before` : cette regle declare pointer-events: none sur [proposition, deja-essayee], des classes que porte un bouton d'action"* |

**Ce rouge est un faux rouge, et la regle est la mienne.** La clause 3 retient toute regle dont le selecteur nomme une classe d'action, sans regarder
son sujet ; `pointer-events: none` sur un `::before` ne rend pas le bouton inactionnable, il rend la pastille transparente au doigt. C'est l'echec par
inclusion d'un perimetre enumere par classes. Retirer `pointer-events: none` n'est pas une issue : un pseudo-element appartient a la boite de frappe
du bouton, la pastille etendrait de 40 px vers le bas la zone qui represse une oeuvre deja essayee. `nadia-prof` l'exige d'ailleurs (critere 3).

**Issue : amender `ADR-0026` clause 3** comme `ADR-0027` clause 8 l'est deja dans le meme fichier de test : une regle dont le **sujet** (dernier compose)
est un pseudo-element decoratif `::before` ou `::after` sort de la clause 3. Morsures exigees : `.proposition { pointer-events: none }` et
`.proposition.deja-essayee { pointer-events: none }` restent rouges ; `.proposition.deja-essayee::before { pointer-events: none }` passe ; un selecteur
qui contient `::before` sans finir par lui reste rouge. **Prix : environ 0,25 j, `theo-archi`**, l'amendement d'`ADR-0026` et le test dans un seul commit,
**avant** le worktree de `S56-ART-CP-10-ECHEC`. Pas une `ArchRule` : l'objet est une feuille CSS. **Poste sans ligne au planning : demande d'amendement a
`sofia-sm`** (action A5 de s55). Je ne l'ouvre pas dans ce commit, qui ne touche aucun code.

**Garde de la marque O1** (remplace le paragraphe "Garde de la marque" de la section 4) : un test de feuille dans `adapter-web` pour les criteres 1 a 5,
7 et 8 de `nadia-prof`, et une clause de rendu au banc du garde-fou 4 (`bootstrap/rendu`) pour le critere 6. Environ 0,25 j, dans le commit d'`ECHEC`.

| # | Mutation, sur la feuille, le SVG ou le gabarit reels mutes en memoire | Critere qui doit rougir |
|---|---|---|
| E1 | regle `::before` retiree | 1 |
| E2 | `filter: grayscale(1)` ajoute a `.propositions-oeuvres .proposition.deja-essayee img` | 2 |
| E3 | `pointer-events: none` retire | 3 |
| E4 | `top: calc(100% + 0.25rem)` devient `top: 0` | 3 |
| E5 | remplissage du disque du SVG passe a `#c62828` (croix sur disque a 1:1) | 4 |
| E6 | `deja-essayee` remplace par `[data-oeuvre-revelee=true]` dans le selecteur de la marque | 5 |
| E7 | `max-height: 9rem` ajoute a `.propositions-oeuvres .proposition.deja-essayee img` | 7 |
| E8 | a la revelation, le gabarit retire `deja-essayee` des boutons essayes | 6 |
| E9 | `content: "x"` sur le `::before` | clause 2 d'`ADR-0027`, existante |

**Deux precisions pour `camille-po`, qui recopie le critere mot pour mot.** Le critere 7 vise *"`.proposition` ou son `img`"* ; la regle de O1 declare
`width`, `height` et `margin-left` sur `.proposition.deja-essayee::before`. Lu "dont le sujet est `.proposition` ou `img`", il laisse passer O1 ; lu
"dont le selecteur contient `.proposition`", il refuse O1. La lecture est a `nadia-prof` ; je signale l'ambiguite. Le critere 4 lit le fond du meuble :
le test le lit dans `:root`, pas en constante.

### A.2 `AV1` amende : la forme d'`ines-design` remplace l'arithmetique de la case

`max-width: min(100%, 11rem)` et `max-height: min(100%, 11rem)` sur `.propositions-oeuvres .proposition img` portent le plafond sur l'image elle-meme.
S-O1 la contient : **elle passe toute la suite** (le seul rouge de S-O1 est celui de A.1). Avec `box-sizing: border-box`, la boite de l'image fait au
plus 176 px bordure comprise, le dessin 172.

**Le garde-fou de la section 2 change de forme, en plus simple :**

1. inchange : `max-height` de `.image-item` fois 16 egale `PLAFOND_DE_RENDU_EN_PIXELS` (176) ;
2. remplace : toute regle de la feuille maison dont le selecteur contient `propositions-oeuvres` et dont le sujet est `img` ne declare `max-width` et
   `max-height` que sous la forme `min(100%, <x>rem)`, `x` fois 16 au plus 176, et la regle de base declare les deux ;
3. remplace : aucune de ces regles ne declare `min-width`, `min-height`, ni `width` ou `height` autres que `auto` ;
4. retire : l'arithmetique de la case (`calc`, `padding`, `aspect-ratio`, plus petite bordure). La case peut grandir sans que l'image grandisse, et
   les pseudo-elements de la case (la marque de O1) sont hors perimetre, seul le sujet `img` etant lu.

Morsures, qui remplacent P1 a P7 : P1' `min(100%, 11rem)` vers `min(100%, 14rem)` (clause 2) ; P2' vers `100%` (clause 2) ; P3' `max-height: 14rem` ajoute a
la regle `.deja-essayee img` (clause 2) ; P4' `min-height: 14rem` ajoute (clause 3) ; P5' `.image-item` a `14rem` (clause 1).

**Consequence sur le critere du backlog de `S55-ART-CP-10-PLAFOND`** : la mutation *"ligne 1780, `calc(11rem` vers `calc(14rem`"* doit y rendre au moins
un echec ; avec la forme d'`ines-design` elle n'en rend legitimement aucun (la case grandit, l'image non). **A amender par `camille-po`** : la mutation
de reference devient P1'. Prix inchange, environ 0,25 j ; les deux lignes de CSS d'`ines-design` entrent dans le commit du garde-fou, qui les juge.

### A.3 T4 de `CHOIX-OEUVRES-s56-art-cp-10.md` : coherent avec `AV4` ; le commit du paquet demande deux retouches de plus et bute sur `ADR-0024`

**Arithmetique : coherente.** Les onze identifiants des six items, moins les huit servis, donnent exactement les quatre reproductions nommees ;
`8 + 4 = 12` ; `176 474 + 31 398 + 21 707 + 21 336 + 24 248 = 275 163` (tailles de ma section 5) ; six items a propositions-images, `0 -> 6` ; six items
illustres, `81 -> 87`. `oeuvre-hippopotame` reste declaree par `cp-decrire-une-oeuvre` : rien ne dort.

**Simulation S-PAQ** : les quatre JPEG et les quatre entrees de registre restaures depuis `1db6348f^`, le fichier de la section 3 recopie, les quatre
constantes aux valeurs du document. **Resultat : 6 echecs sur 2307** (4 dans `adapter-content`, 2 dans `bootstrap`), ranges en trois causes :

| # | Echecs | Cause | Ce que le commit du paquet doit faire |
|---|---|---|---|
| K1 | `ChaquePaquetExpliqueSesItemsAVariationTest`, 1 : *"ce paquet declare la graphie de ses items, donc tous"* | **bloquant** : la regle 1 d'`ADR-0024` fait entrer dans son controle tout paquet qui porte `explication`, et exige alors `graphie` sur chaque item et une explication sur les seuls items a variation. Le paquet porte une `explication` sur ses six items, sans graphie | voir ci-dessous |
| K2 | `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest`, 3 | le paquet n'est declare nulle part ; ses bonnes reponses ne sont pas numeriques. Les trois echecs sont attribues a cette seule cause par leur message ; a confirmer au `verify` de `lea-dev` | une entree de `PAQUETS_HORS_DU_DOMAINE_NUMERIQUE` avec son motif, comme `cp-decrire-une-oeuvre` (ligne 1352). Un ajout, pas un assouplissement |
| K3 | `LIllustrationDeContenuResoutEtAucuneNeDortTest.unIdentifiantDOeuvreResoutDesQueLeFichierDeReproductionExiste` et `.uneOeuvreServieSeulementEnPropositionNEstPasDormante`, 2 | `oeuvre-autoportrait-leyster` n'est montree qu'en proposition. Ces deux morsures lisent le repertoire reel des reproductions mais une population de declarations privee des propositions reelles (surcharge a quatre arguments, ou une seule proposition mutee) | passer aux deux morsures les propositions reelles du depot. **Le garde-fou reel, `chaqueIllustrationDeclareeParUnPaquetServiResoutEtAucunFichierNeDort`, est vert** : ma section 6 (*"le fichier seul suffit a les faire tourner"*) tient pour lui, pas pour ces deux morsures, ecrites quand toute reproduction etait une illustration |

**K1, deux issues, et le choix n'est pas a moi seul.** (a) `nadia-prof` retire les six `explication` du paquet ; la phrase de `M4` reste la seule parole
de la revelation. (b) J'amende `ADR-0024` : le controle de la regle 1 n'entre que sur `graphie` ; un paquet sans graphie qui porte `explication` la porte
sur tous ses items ou sur aucun. **Ce que (b) perd, nomme** : une faute de frappe `explicaton` sur tous les items d'un paquet sans graphie ne rougit plus,
le chargeur ignorant une cle inconnue. **Prix de (b)** : environ 0,25 j, `theo-archi`, amendement et test dans un seul commit, avant le commit du paquet.
**Je recommande (b)** : `ADR-0024` decision 1 a voulu `explication` sans nom de matiere, et c'est sa regle executable qui l'a liee au francais par
inclusion. Le choix touche un mot lu par l'enfant : `nadia-prof` d'abord. **Poste sans ligne au planning.**

### A.4 Ce qui change dans les sections 1 et 10

`S56-ART-CP-10-ECHEC` : faisable **moyennant l'amendement d'`ADR-0026`** (A.1). `S55-ART-CP-10-PAQUET` : faisable en l'etat **si (a)**, **moyennant
l'amendement d'`ADR-0024` si (b)** (A.3, K1) ; K2 et K3 sont des retouches du meme commit, a ajouter aux prerequis de ma section 6. `S55-ART-CP-10-PLAFOND` :
faisable en l'etat, critere du backlog a amender (A.2). Section 10 : deux amendements d'`ADR` existants sont desormais appeles, aucun `ADR` neuf. Ordre
propose a `sofia-sm` : les deux amendements avant le worktree du paquet, parce que K1 bloque son premier commit et que l'amendement d'`ADR-0026` doit
preceder `ECHEC`. Aucun n'est ecrit dans ce commit ; `ADR-index.md` inchange.

### A.5 Jobs lances pour cet amendement

Sur ce worktree (`bb4041c8` plus cet amendement), avant commit, JDK 21.0.10 rapporte par Surefire :

- **`build`** : `bash outils/mvn-jdk21.sh -B clean verify` depuis la racine, `BUILD SUCCESS`, 2307 tests, 0 echec, 0 erreur (120, 18, 407, 1423, 333 et 6
  d'integration). L'etape "Package du fat jar" n'est pas lancee.
- **`chemins`** : `verifier-chemins-test.sh` sort 0 ; `git ls-files` (moins le test du garde-fou) passe a `verifier-chemins.sh` : sort 0.
- **`prefixes`** : `verifier-prefixes-test.sh` et `commit-atteignable-test.sh` sortent 0 ; le sujet du commit passe dans `verifier-prefixes.sh` apres sa creation.
- **`outils`** : non lance pour cet amendement ; aucun outil n'est touche depuis la mesure de la section 11.


## Amendement du 2026-10-11 (theo-archi) - `B2`, `B3`, `B5`, `B6`, `B7`, `B8` : garde-fou propose ou refus

Annotation datee ; rien au-dessus n'est reecrit. Source : `ARBITRAGE-PREALABLE-s56-instance-b-art-cp-10-et-emc-cm1.md` (section 1, et la liste
*"Ce qu'un test peut mordre"*) ; `rapport-lea-dev.md` de s56, section 5 (les six conditions **non gardees**). Critere, celui de mon avis de s55
(T2) : un test est du quand la condition est une propriete decidable d'un item nomme, et qu'il interdirait reellement quelque chose.

**Etat du paquet servi, lu sur les fichiers et non sur un tableau** (script Python hors depot, lecture des segments `SOF` des JPEG de
`static/oeuvres/` et du YAML `cp-rapprocher-deux-oeuvres.yaml` sur `bdd6dbe4`) : six items ; aucune bonne oeuvre seule de son format parmi
les trois propositions, ni seule a partager celui de la reference ; references uniques ; couples (reference, bonne) uniques et symetriques ;
`oeuvre-hippopotame` absente ; un seul enonce, un seul texte alternatif. **Les six conditions tiennent aujourd'hui** ; la question est ce qui
les garde demain.

| Condition | Verdict | Forme, ou motif du refus |
|---|---|---|
| `B2` texte alternatif de la reference, le meme partout, celui de `cp-decrire-une-oeuvre` | **garde-fou propose** | les `illustrationTexteAlternatif` du paquet forment une seule valeur, egale a celle de `cp-decrire-une-oeuvre` lue dans son fichier (pas recopiee). Interdit reellement : un texte alternatif qui nomme le sujet d'une reference, donc la paire (`X1`). Morsure : un item mute avec *"Une chambre"* |
| `B3` reference unique, couple unique, paires dans les deux sens | **garde-fou propose** | references deux a deux distinctes ; couples (reference, bonne) distincts ; l'ensemble des couples est symetrique. Derive du fichier, sans liste des paires. Interdit reellement la mutation `M7` de `lea-dev` (item 2 rendu identique a l'item 1), restee verte |
| `B5` aucune bonne oeuvre seule de son format | **garde-fou propose**, dans `bootstrap` | le format (largeur ou hauteur) se lit dans le segment `SOF` du JPEG servi ; pour chaque item, la bonne oeuvre n'est ni seule de son format parmi les trois propositions, ni seule a partager celui de la reference. `bootstrap` voit les deux modules (contenu et `static/oeuvres/`). Morsure : un distracteur echange contre une oeuvre de l'autre format. Une image carree ferait rougir le test plutot que d'etre classee : aucune n'existe aujourd'hui |
| `B6` aucun objet en volume en proposition | **refus** | la propriete *"image plate"* n'est dans aucune donnee : ni le registre ni le paquet ne portent le support. Le seul test possible est une liste de noms (`oeuvre-hippopotame`) : il garderait le cas connu et passerait vert sur la prochaine restauration d'un objet (`1db6348f^` en porte au moins quatre : amphore, vase Ming, vase Shang, bateau). C'est l'echec par omission d'un perimetre enumere. `B4` ferme le vivier a s56 sur une seule statuette : `nadia-prof` la juge a l'audit. **Ce qui me ferait changer d'avis** : la restauration d'un second objet en volume ; alors un champ de support dans le registre (`ADR-0082`), et le test se derive de lui |
| `B7` 4 a 6 items | **garde-fou propose**, a faible valeur | le compte d'items du paquet entre 4 et 6. Interdit reellement un paquet reduit a une paire (refus nomme par `nadia-prof`). La borne haute ne peut etre franchie qu'avec de nouvelles paires, donc de nouvelles oeuvres ; elle reste ecrite par coherence. `nombreDeManches` est deja juge par `ADR-0023` |
| `B8` enonce identique sur tous les items | **garde-fou propose, partiel** | un seul enonce dans le paquet, et dix mots au plus (comptage d'`ADR-0101`). **Pas le texte litteral** : `nadia-prof` accepte *"un autre texte qui tient la barre"* ; figer le sien refuserait un texte recevable. Que l'enonce demande *"la facon dont l'oeuvre est faite"* reste a elle |

**Forme et proprietaire.** `B2`, `B3`, `B7`, `B8` : un test de paquet `PaquetCpRapprocherDeuxOeuvresTest` dans `adapter-content`, sur le
modele des tests de paquet existants, avec ses jeux fautifs sous `contreexemple/`. `B5` : un test dans `bootstrap/archi`, a cote des
garde-fous d'`ADR-0104`. Ce sont des tests de contenu, pas des regles d'architecture : **a l'auteure du paquet, `lea-dev`**, environ
0,25 j pour les quatre premiers et 0,25 j pour `B5`. Aucun `ADR`, aucune `ArchRule`. **Poste sans ligne au planning** : a `sofia-sm`. Tant
qu'il n'est pas ecrit, la ligne *"non garde"* de `rapport-lea-dev.md` reste exacte.
