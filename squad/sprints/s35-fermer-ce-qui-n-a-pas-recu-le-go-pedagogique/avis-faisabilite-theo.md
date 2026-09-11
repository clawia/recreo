# Avis de faisabilite technique - s35 (`theo-archi`)

- **Objet** : les six stories de `docs/backlog/backlog.md`, section *"Backlog ouvert pour s35"*,
  ecrites par `camille-po` sur `s35-camille` (`faaa0dc2`, non fusionnee).
- **Arbre mesure** : `round-two` a `60e1c836`, worktree dedie `s35-theo`. Le backlog est lu sur
  `faaa0dc2`, la seule branche qui le porte.
- **Methode, une fois pour toutes** : **aucun build n'a ete lance.** Toutes les mesures de cet
  avis sont statiques - lectures de fichiers, comptages par motif, arithmetique sur des valeurs
  lues. Aucun chiffre ci-dessous n'est un compte de tests et aucun ne suppose une machine
  virtuelle. La ou l'execution serait la bonne preuve, cet avis nomme **la commande due au lot**
  plutot que de la remplacer par une estimation. C'est la meme discipline qu'`ADR-0080` s'est
  imposee a sa redaction.
- **Nom de ce repertoire** : le slug d'un sprint appartient a `sofia-sm` (`squad/README.md`,
  section 2). Celui-ci reprend l'objectif que `camille-po` a ecrit ; **si elle en gele un autre,
  ce repertoire se renomme**, l'avis ne bouge pas.
- **Ce que cet avis ne fait pas** : il ne repriorise rien, il ne rediffe aucune story, il ne
  prononce aucun Done, il ne tranche aucune question pedagogique. Chaque fois qu'une question s'y
  est presentee, elle est nommee et rendue a `nadia-prof` ou a `camille-po`.

## 0. Verdict, en une table

| Story | Verdict | Ce qui le decide |
|---|---|---|
| `S35-SCO1-ECRAN-FIN` | **faisable en l'etat** | Les deux nombres existent deja dans `Score` et arrivent deja au modele de l'ecran de fin ; l'ecran est **un seul** fragment pour les trois mecaniques. Rien a ajouter au domaine |
| `S35-EXC-INDICES` | **faisable en l'etat**, une contrainte de caractere a respecter | Contenu seul. Aucun garde-fou ne s'y oppose, un seul l'attend au piege (ligature hors Latin-1) |
| `S35-QLM14-FRISE` | **faisable moyennant un `ADR`** - `ADR-0091`, ouvert par cet avis | La moitie opposable de `FRI-R1` n'est gardee par rien aujourd'hui et elle est encodable ; le pictogramme (`FRI-R2`) est un travail de dessin, hors garde-fou |
| `S35-QLM11-PLANCLASSE` | **faisable avec reserve** - `ADR-0092`, ouvert par cet avis | `ZON-15` premiere moitie (*"le libelle tient dans son cadre a 1,25rem"*) **n'est pas encodable et n'est pas independante de l'appareil** : la police servie est celle du systeme. La ligne reste fermable, mais par l'oeil de `nadia-prof` sur l'ecran servi, pas par une arithmetique |
| `S35-ANG-R3` | **faisable en l'etat**, sans regle neuve, et le motif est deja ecrit | `ADR-0086` a deja refuse d'encoder la propriete exacte qu'`ANG-R3` demande, chiffres a l'appui. Rien ne change de ce cote ; le risque est ailleurs (la longueur d'une proposition n'est gardee par rien) |
| `S35-CONS-4` | **infaisable en l'etat** - un blocage mesure, qui demande une decision de `camille-po` avant l'execution | La quatrieme entree (`HG-CM1-14`, **10 items**) avec `nombreDeManches: 10` **fait rougir** `ChaqueJeuDuCatalogueOffrePlusDItemsQueDeManchesTest`, qui exige **strictement plus** d'items que de manches. Ce n'est pas un detail d'implementation : c'est `DEC-0064` contre `ADR-0023`, et la sortie est une derogation ecrite |

**Deux `ADR` ouverts, pas trois, et le motif de ne pas en ouvrir un troisieme est en section 1.4.**

---

## 1. `S35-SCO1-ECRAN-FIN` - faisable en l'etat

### 1.1 Les deux nombres existent deja, et c'est la seule chose qu'il fallait verifier

`domain/src/main/java/com/recreo/domain/mechanic/Score.java` porte deux champs :

```java
public record Score(int reussites, int tentatives) {
}
```

- `reussites` : depuis `ADR-0090`, **les manches trouvees du premier coup** - c'est
  `BanditManchotSession.resoudre` qui n'incremente que sur `reussieDuPremierCoup`.
- `tentatives` : **les manches resolues**, incrementees d'une unite a chaque resolution, et c'est
  ce compteur qui termine la partie (`nouveauScore.tentatives() >= nombreDeManches`).

Les deux informations que `SCO-R1` demande - *"ce qu'il a trouve seul, et qu'il a fait toutes les
questions"* - **sont donc deja dans l'objet que le controleur pose au modele** de l'ecran de fin
(`PartieController`, `modele.addAttribute("score", session.score())`). **Aucune session n'est
retouchee, aucun champ n'est ajoute, le calcul de score n'est pas rouvert** : le critere
d'acceptation qui l'exigeait est tenu par construction, pas par vigilance.

### 1.2 Le second critere est tenu par construction lui aussi

*"Un ecran commun, pas trois variantes"* : l'ecran de fin est
`adapters/adapter-web/src/main/resources/templates/fragments/retour.html`, **un seul fragment**,
rendu pour les trois mecaniques. Il n'y a pas trois textes a changer, il y en a un.

### 1.3 La forme technique, que la story me route explicitement

**Decision : deux elements distincts, les mots invariants dans le gabarit, chaque nombre seul dans
son propre element.** Jamais une phrase qui concatene un nombre dans du texte.

Le motif n'est pas esthetique, il est mesure dans ce depot : `REV-1` et `ADR-0083` ont paye, sur ce
fichier exactement, le defaut de la concatenation - `"Non, c'est " + ${bonneReponse}` rendait *"Non,
c'est ce n'est pas un etre vivant."* sur douze items. Le gabarit porte encore aujourd'hui, a la
ligne 25, la forme que cette decision ecarte (`'Score : ' + ${score.reussites()}`), et c'est
precisement la ligne que `SCO-R1` refuse.

Ce que le lot doit savoir avant de commiter :

- **`TexteAccentueDesGabaritsTest.TEXTES_LUS_PAR_L_ENFANT`** est un inventaire **par egalite de
  noeud**, jamais par sous-chaine : tout texte neuf ecrit en clair dans le gabarit doit y entrer,
  et un texte qui n'y est pas est **rouge**. Ce garde-fou ne valide pas le texte, il refuse qu'il
  change en silence - la formulation reste a `nadia-prof`.
- **Le texte lui-meme n'est pas de moi.** Il est lu par un enfant de CP : `nadia-prof` l'ecrit ou
  le juge. Je tranche la forme, pas les mots.

### 1.4 La regle executable, et pourquoi elle n'appelle **pas** d'`ADR`

La moitie opposable de `SCO-R1` est encodable et elle doit l'etre, sans quoi un gabarit qui
reperdrait un des deux nombres serait invisible :

> **Fichier** : `bootstrap/src/test/java/com/recreo/bootstrap/rendu/LEcranDeFinDePartiePorteLesDeuxNombresTest.java`
> (module `bootstrap`, seul module qui voit `adapter-web` **et** `adapter-content`).
> **Perimetre derive** : pour **chaque mecanique presente dans le catalogue reel** - jamais une
> liste de trois noms ecrite dans le test -, une entree est prise, une partie est jouee jusqu'a son
> terme par MockMvc, et l'ecran de fin doit porter **deux elements distincts**, l'un contenant
> `score.reussites()`, l'autre `score.tentatives()`. Le patron existe trois fois dans
> `bootstrap/rendu` (`LIllustrationDUnItemServiAtteintLEcranDeMancheTest` et ses deux voisins).
> **Ce qu'elle interdit reellement** : qu'un gabarit reduise l'ecran de fin a un seul nombre - le
> defaut exact que `nadia-prof` a vu -, **et** qu'une quatrieme mecanique arrive avec un ecran de
> fin qui n'en porte qu'un. Le perimetre derive de la mecanique, donc la quatrieme est jugee sans
> qu'on y pense.

**Pas d'`ADR`, et le precedent est dans le depot** : une condition pedagogique nommee (`HIS-1`,
`ZON-2`, `EXC-R4`...) est tenue par un garde-fou qui la cite, sans `ADR` intermediaire. Il n'y a ici
**aucune decision d'architecture a prendre** : les deux nombres existent, l'ecran est unique, la
forme (un nombre par element) est celle qu'`ADR-0083` a deja decidee. Ecrire un `ADR` pour redire
`ADR-0083` gonflerait le compte sans rien decider.

### 1.5 Le cas que `nadia-prof` dit ne pas avoir compte

*"L'ecran de fin apres une manche resolue par revelation"* : sur cet ecran, `juste` vaut faux et le
gabarit rend *"Tu n'as pas trouve."*. Les deux nombres neufs sont **hors du bloc `.signal`**, donc
rendus dans les deux cas - c'est une propriete de position dans le gabarit, et le garde-fou de la
section 1.4 la couvre des que le corpus joue une partie qui finit sur une revelation. **A nommer au
planning** : ce corpus-la existe deja (`AucuneReponseDeFinDePartieNEstOrphelineDeSaQuestionTest`
joue des parties completes), il n'y a pas de mecanique a ecrire pour l'obtenir.

---

## 2. `S35-EXC-INDICES` - faisable en l'etat

### 2.1 Contenu seul, et aucune structure ne bouge

Les cinq `indice` de la tache B, trois `bonneReponse`/`distracteurs` (`EXC-R11`) et une phrase de
distracteur (`EXC-R12`) sont des chaines d'un `yaml` deja servi
(`adapters/adapter-content/src/main/resources/contenu/cp-emc-exclusion.yaml`). Aucun schema, aucun
type, aucune cle : `ChaquePaquetPorteUnIndiceTest` exige deja qu'un indice existe, il ne juge pas
son texte.

### 2.2 Les garde-fous que ce diff va traverser, nommes pour qu'ils ne soient pas decouverts en CI

| Garde-fou | Ce qu'il verifiera sur ce diff |
|---|---|
| `AucunIndiceNeContreditUnAutreItemDuMemePaquetTest` | Cinq indices reecrits d'un coup dans un paquet qui en porte d'autres : c'est le seul garde-fou que cette story peut faire rougir par son propre contenu |
| `AucunItemNeRepeteLEcranDUnAutreItemDuMemePaquetTest` | `EXC-R11` change trois `bonneReponse` et leurs distracteurs - deux items ne doivent pas devenir le meme ecran |
| `AucunEnonceDeCpNeDepasseQuinzeMotsNiDeuxPhrasesTest` | `cp-emc-exclusion.yaml` y porte une dette de **1**, et le mecanisme est **l'egalite exacte**, pas un plafond : un enonce trop long de plus **ou de moins** est rouge. Cette story ne touche pas les enonces ; si elle en touche un par ricochet, le chiffre se corrige **dans le meme diff** |
| `AucunMotServiNeSEcarteDeSonAccentTest`, `AucunSigneDoubleNEstDetachableDeSonMotTest` | Texte francais neuf : accentuation et signes doubles jugees contre le lexique, pas contre un gout |

### 2.3 Le piege reel de ce sprint, et il vaut aussi pour `S35-ANG-R3`

`bootstrap/src/test/java/com/recreo/bootstrap/catalogue/PropositionsAccentueesJouablesIT.java`
**jette** `IllegalStateException` sur toute **proposition** qui porte un caractere hors Latin-1 :

```java
throw new IllegalStateException(
        "caractere hors Latin-1, cette IT ne sait pas le reproduire : \"" + proposition + "\"");
```

C'est le rouge reel trouve a la cloture de s34, sur une ligature `oe`, et le stopgap n'est qu'un
stopgap. **Consequence operationnelle pour s35, a dire au planning plutot qu'a la CI** : aucune
**proposition** neuve (`bonneReponse`, `distracteurs`) ne porte `oe` ou `ae` en ligature - *coeur*,
*soeur*, *noeud*, *voeu*, *oeuf* s'ecrivent en deux lettres tant que la decision de fond n'est pas
prise. Le sujet de `cp-emc-exclusion` (une moquerie, ce qu'un enfant ressent) rend ce mot-la
probable : c'est pour cela que je l'ecris ici et pas ailleurs. L'`enonce` et l'`indice` ne sont pas
concernes - seules les propositions traversent le corps de formulaire.

### 2.4 Aucune regle neuve, et le motif

`EXC-R10` demande qu'un indice *"dise pourquoi la conduite choisie ne marche pas"*. C'est un
jugement de sens sur un texte francais ; aucun automate de ce depot ne le porte, et ecrire une
regle qui chercherait un mot-cle (*"attendre"*, *"parce que"*) serait **exactement la faute de
s05** - une fixture qui ressemble a ce que j'imagine, verte sur elle-meme et fausse dans le monde.
`nadia-prof` reste le seul juge de cette clause, et la story le dit deja.

---

## 3. `S35-QLM14-FRISE` - faisable moyennant `ADR-0091`

### 3.1 La moitie opposable de `FRI-R1` est encodable, et rien ne la garde aujourd'hui

Lecture de `cp-frise-de-la-journee.yaml`, item 1 (methode : lecture du fichier servi, pas d'un
resume) :

```
enonce                     : "Montre le lever."
illustrationTexteAlternatif: "Frise de la journee d'ecole, six moments sur un axe - le lever,
                              le petit-dejeuner, l'ecole du matin, le dejeuner, l'ecole de
                              l'apres-midi, le gouter."
zones (id / libelle)       : lever / "le premier", petit-dejeuner / "le deuxieme",
                             ecole-matin / "le troisieme", dejeuner / "le quatrieme",
                             ecole-apres-midi / "le cinquieme", gouter / "le sixieme"
indice                     : "C'est le tout premier moment de la journee."
```

Le texte alternatif **enumere les zones dans l'ordre de la frise** et les libelles **sont les rangs
de cet ordre** : les deux ensemble forment la table de correspondance que `FRI-R1` refuse. **Trois
textes** d'un item de designation peuvent se substituer au dessin - l'enonce, l'indice, le texte
alternatif - et le depot n'en garde qu'**un** :
`AucunEnonceDeDesignationNeNommeLeLibelleDeSaBonneZoneTest` (garde-fou 5 d'`ADR-0080`) juge
l'enonce contre le libelle. **Le texte alternatif n'est compare a rien, l'indice n'est compare a
rien.** C'est la meme famille de trou que celui qu'`ADR-0076` a ferme : une propriete tenue sur un
terme et pas sur les deux autres.

### 3.2 Ce que j'ouvre : `ADR-0091`, deux clauses, deux garde-fous, operateurs deja dans le depot

`squad/decisions/adr/ADR-0091-...md`, **statut `Propose`**, ses deux regles **dues avec le lot** de
cette story - leur code est ecrit dans l'`ADR`, pas promis.

- **Clause 1** - *le texte alternatif d'un item de designation ne nomme aucune de ses zones.*
  Operateur : les jetons de l'**identifiant** de chaque zone, cherches comme **suite contigue** de
  jetons dans le texte alternatif, avec le tokeniseur d'`ADR-0076` **reutilise tel quel** (celui
  que garde-fou 5 reutilise deja).
  **Rouge aujourd'hui** : sur l'item 1, `lever`, `petit-dejeuner`, `dejeuner` et `gouter` sont
  nommes - **4 zones sur 6**, par lecture ; `ecole-matin` et `ecole-apres-midi` ne le sont pas au
  sens contigu (*"l'ecole du matin"* insere `du`). Le chiffre exact appartient au garde-fou une
  fois ecrit ; ce qui est etabli par la lecture, c'est qu'il **mord**.
  **Vert sur un texte alternatif honnete** : *"Frise de la journee d'ecole, six moments de gauche
  a droite."* ne nomme aucune zone au sens de cet operateur. La clause n'interdit donc pas de dire
  ce que l'image **est** - c'est exactement la distinction que `nadia-prof` a ecrite.
- **Clause 2** - *l'indice ne porte aucun jeton distinctif du libelle de sa bonne zone.*
  **Le jeton distinctif est derive, pas liste** : un jeton du libelle de la bonne zone qu'**aucun
  autre libelle du meme item** ne porte. Sur l'item 1, les six libelles partagent `le` ; seul
  `premier` distingue. L'indice *"C'est le tout premier moment"* porte `premier` : **rouge**.
  Aucune liste de mots-outils n'est ecrite - c'est ce qui evite la faute de s05.
  **Verifie vert la ou il doit l'etre**, par lecture, sur les items 8, 10 et 11 : le jeton
  distinctif de *"le premier"* (item 8) est `premier`, absent de *"C'est le repas du soir."* ;
  celui de *"le deuxieme, en bas"* (item 10) est `bas`, absent de son indice. **La clause 2 ne
  rougit que la ou `nadia-prof` a mis le doigt.**

**Ce que l'`ADR` dit ne pas garder**, plutot que de le laisser decouvrir : l'identifiant d'une zone
est choisi par l'auteur du paquet. Des identifiants opaques (`z1`...`z6`) passeraient la clause 1
en rendant le paquet illisible. C'est nomme dans l'`ADR`, rendu a la relecture item par item, et
ce n'est pas une raison de ne pas ecrire la clause : une regle qui ferme la porte qu'on a vue
franchir vaut mieux qu'aucune.

### 3.3 `FRI-R2`, le pictogramme du lever - hors de tout garde-fou, et je le dis

*"Un rectangle large avec un petit rectangle et un trait"* qui ne se lit pas comme un lit est un
travail de dessin. Aucun automate ne juge qu'une forme **ressemble a** un lit ; le seul juge est
l'oeil, a la taille servie et au triple agrandissement, et c'est ce que la story prevoit. Ce qui
**est** garde du cote technique et ne bouge pas : `ChaqueIllustrationDeContenuDeclareSaGeometrieEtResteSousLeCliquetDePoidsTest`
(geometrie declaree, cliquet de poids) et `LIllustrationDeContenuResoutEtAucuneNeDortTest` - un
`svg` retouche les traverse, et ils rougissent si le fichier grossit au-dela du cliquet ou si sa
geometrie declaree ne suit pas.

### 3.4 Ce que cette story ne doit pas se croire autorisee a faire

`ZON-10` (les libelles positionnels) **n'est pas amendee** par `FRI-R1` - `nadia-prof` l'ecrit. Or
la tentation technique existe : rendre les libelles semantiques (*"le lever"*) ferait tomber la
table de correspondance d'un coup. **Ce serait un changement de decision pedagogique, pas une
correction**, et il n'appartient ni au lot ni a moi. `ADR-0091` porte donc sur les deux textes,
jamais sur les libelles.

---

## 4. `S35-QLM11-PLANCLASSE` - faisable avec reserve, et `ADR-0092`

### 4.1 Le fait qui change la nature de la reserve : la police servie est celle de l'appareil

**Mesure, avec sa methode.** Recherche de `font-family` et de `@font-face` dans
`adapters/adapter-web/src/main/resources/static/css/recreo.css` le 2026-09-11 : **aucune
occurrence**. Inventaire de `static/vendor/` : **un seul fichier**, `bootstrap.min.css`
(`org.webjars:bootstrap:5.3.8`, empreinte dans `vendor/provenance.txt`), **aucun fichier de
police**. La pile declaree par ce fichier est
`--bs-font-sans-serif: system-ui, -apple-system, "Segoe UI", Roboto, ...` : **une pile de polices
systeme.**

**Consequence, et elle est structurelle** : la largeur rendue d'un libelle a `1,25rem` **depend de
l'appareil de l'enfant**. La propriete que `ZON-15` demande dans sa premiere moitie - *"a la taille
servie, le libelle tient entierement dans son cadre"* - **n'est vraie ni fausse en soi** : elle est
vraie sur certains systemes et fausse sur d'autres, a cadre et a libelle identiques.

Deux corollaires, que je pose parce qu'ils decident la suite :

1. **Aucune regle de CI ne peut tenir cette moitie.** Il faudrait epingler une avance par
   caractere ; ce nombre serait mesure sur **une** machine, et `ADR-0055` exige d'epingler les
   deux membres d'une comparaison - le second membre, ici, n'existe pas dans le depot. Un tel
   chiffre serait la faute de s05 sous une autre forme : une mesure de ma main presentee comme une
   propriete du monde. **Je ne l'ecrirai pas.**
2. **Agrandir les cadres ne ferme pas la question, il la deplace.** Quel que soit le cadre choisi
   au moment de la redaction, un systeme a police plus large le deborde. C'est un fait a rendre a
   `nadia-prof` : sa condition, telle qu'elle est ecrite, **n'est pas independante de l'appareil**.
   Ce n'est pas une objection a sa condition - un libelle illisible reste illisible - c'est
   l'information qui manquait pour choisir comment la tenir.

### 4.2 Le levier technique que personne n'a encore nomme, et il est gratuit

Arithmetique sur les valeurs lues dans `recreo.css` (`.zone`) et dans le gabarit
(`fragments/designation.html`) :

```
.zone { min-height: 2.75rem; min-width: 2.75rem; padding: 0.75rem 1.25rem;
        border: 0.125rem solid ...; font-size: 1.25rem; }   box-sizing: border-box (regle universelle)
gabarit : style="left:..%; top:..%; width:..%; height:..%"   -> le cadre est une taille FIXE
```

Le pave horizontal coute **2 x 1,25rem = 40 px**, la bordure **2 x 2 px = 4 px** : **44 px de la
largeur du cadre partent avant le premier caractere.** Sur `armoire` (56 x 77 px mesures au jar par
`lea-dev`), il reste **12 px** de boite de contenu ; sur `porte` (63 x 56 px), **19 px**. C'est
l'ordre de grandeur que `nadia-prof` a mesure (*"16 a 23 px"*), obtenu par un autre chemin.

**Ce que cela ouvre** : `nadia-prof` a nomme deux leviers - *"c'est le cadre ou le libelle qui
change, jamais la police"*. **Il y en a un troisieme, et il ne coute rien sur une propriete deja
gardee** : le pave de `.zone`. Le plancher de cible tactile est tenu par `min-height` et
`min-width` (2,75rem), **jamais par le pave** ; le reduire ne fait descendre aucune zone sous le
plancher et ne touche pas
`AucuneZoneDesignableNeDescendSousLaCibleTactileTest`, dont le juge compare
`min(largeur%, hauteur%) x plafond` au plancher. Sur `armoire`, passer le pave horizontal a `0` rend
la boite de contenu de **12 px a 52 px** - un facteur quatre, sans toucher un cadre ni un libelle.

**Ce n'est pas ma decision de forme** : `.zone` est une regle d'habillage, elle appartient a
`ines-design`, et le lot arbitre. Ce qui est de moi est de dire que **le levier existe, qu'il est
mesure, et qu'il ne casse rien** - sans quoi le lot arbitrera entre deux termes au lieu de trois.

### 4.3 Ce que j'ouvre : `ADR-0092`

`squad/decisions/adr/ADR-0092-...md`, **statut `Propose`**. Il tranche l'obstacle transverse
(section 7 ci-dessous), il ecrit le fait de la section 4.1, il nomme les trois leviers, et sa
section *Regle executable* dit **pourquoi ArchUnit ne peut pas tenir la propriete** et **quels deux
mecanismes la gardent a la place** :

1. **la derniere clause d'`Accepte` d'`ADR-0080`**, deja decidee et **deja due a ce lot-ci** : un
   garde-fou de `bootstrap` qui **sert l'ecran de manche du jeu reel de designation** et y rejoue
   les criteres des garde-fous 1 et 3. `ADR-0080`, amendement du 2026-09-11, section 4 : *"elle
   appartient au lot qui achevera `QLM-CP-11`"*. Elle n'est pas un travail neuf que j'ajoute : elle
   est **au perimetre de cette story depuis s34**, et le planning doit la voir ;
2. **un cliquet exact** sur le nombre de paquets de designation servis, dont le plafond vaut
   **exactement** ce nombre a la fin de s35 - `1` si `QLM-CP-11` n'est pas livree, `2` si elle
   l'est, la valeur etant posee **dans le diff qui sert le paquet**. Il rougit le jour ou un
   troisieme paquet de designation entre en production sans que la propriete de lisibilite du
   libelle soit tenue autrement que par un oeil.

### 4.4 Le reste de la story, verifie

- **`ZON-16`** (au plus trois mots, un seul repere) : c'est un jugement sur six libelles, par
  `nadia-prof`. **Encodable ?** Le comptage de mots l'est ; *"un seul repere positionnel"* ne l'est
  pas sans une liste de reperes ecrite a la main, donc non. Je n'ecris pas la moitie facile d'une
  condition dont l'autre moitie fait le travail : `nadia-prof` l'ecrit elle-meme, *"ni l'une ni
  l'autre seule ne suffit"*.
- **Le plancher de cible tactile n'est pas rouvert** : la story le dit, le garde-fou 3 ne le voit
  pas changer, et la mesure d'`ADR-0080` (12,5 % du plus petit cote, six cadres a 15 %, 52,8 px
  contre 44 px) reste valide telle quelle.
- **`ZON-1` a `ZON-14`** : `LaBonneZoneNEstPasPredictibleSurUnPaquetTest`,
  `AucunEnonceDeDesignationNeNommeLeLibelleDeSaBonneZoneTest` et
  `ChaqueCleFacultativeDItemEstLueParLaFabriqueDeSonTypeTest` derivent tous leur perimetre des
  **paquets de type `designation` servis** : le plan de classe entre dans leur population **sans
  une ligne de changement**. C'est ce qu'on attend d'un perimetre derive, et c'est verifie plutot
  que suppose.
- **Le cliquet `AucunPaquetDeDesignationNEntreEnProductionSansEtendreLesTroisBalayagesTest`** vaut
  `1` aujourd'hui et **doit passer a `2` dans le diff qui sert le paquet** - egalite exacte, pas
  plafond. Un lot qui sert le paquet sans toucher ce chiffre est rouge, et c'est voulu.
- **`ADR-0091` s'applique aussi au plan de classe** : son texte alternatif ne doit nommer aucune de
  ses six zones. A dire au lot maintenant, pas apres l'audit.

---

## 5. `S35-ANG-R3` - faisable en l'etat, sans regle neuve

### 5.1 La propriete qu'`ANG-R3` demande a deja ete refusee a l'encodage, avec ses chiffres

`ANG-R3` : *"l'enfant ne doit pas pouvoir ecarter une proposition par la seule lecture de
l'enonce"*. C'est, mot pour mot, la premiere des **trois proprietes qu'`ADR-0086` a explicitement
refuse d'encoder** (section *"Les trois proprietes que je n'encode pas, et pourquoi"*). Le motif y
est ecrit avec ses mesures, et rien de ce sprint ne le change : juger que *"la tour Eiffel"* est
hors sujet devant une question sur New York demande de savoir ou sont les monuments du monde, pas
de comparer deux chaines.

**Consequence pour le planning** : cette story **n'appelle aucune regle et aucun `ADR`**, et ce
n'est pas un manque - c'est une decision deja prise, avec sa methode. Le quatrieme passage de
`nadia-prof` sur cet item est le seul juge possible, et la story l'a prevu.

### 5.2 Le risque technique reel, qui n'est pas celui-la

*"Le mot 'imperiale' est explique la ou l'enfant le rencontre - dans l'enonce ou dans une
proposition"*. **Si l'explication est mise dans une proposition, elle en fait la plus longue des
trois**, et la longueur d'une proposition n'est gardee par **rien** : c'est la troisieme propriete
qu'`ADR-0086` a refuse d'encoder, pour le meme motif de faux rouge. Le defaut serait alors un
`PLA-1` par un autre chemin - la forme designe la reponse - **et aucun automate ne le verrait**.

**Avis technique, une phrase** : expliquer le mot **dans l'enonce** ne coute rien et ne cree aucun
ecart de forme entre les trois propositions ; l'expliquer dans une proposition en cree un que rien
ne garde. Le choix reste au lot et a `nadia-prof` ; ce qui precede est le prix de chacun.

### 5.3 Les garde-fous que ce diff traverse

- **`LaFormeDesPropositionsNeDesigneJamaisLaBonneReponseTest`** (`ADR-0086`, trois clauses de
  moule) : remplacer deux distracteurs par deux monuments de New York cree trois propositions de
  meme famille - **c'est le cas que la clause 2 regarde** (le moule des quatre premiers mots). A
  verifier avant de commiter, pas apres.
- **`LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest`** porte, pour `cm1-anglais`, un motif
  d'exemption **chiffre** (*"25 items sur 25 ... 75 propositions"*). Substituer des distracteurs ne
  change pas ces comptes ; **en ajouter un les change**, et un motif perime est la faute que j'ai
  payee cinq fois a s34. Si le lot ajoute une proposition, il recompte le motif **dans le meme
  diff**.
- **`PropositionsAccentueesJouablesIT`** : section 2.3 ci-dessus, meme contrainte de ligature sur
  toute proposition neuve.
- **`AucunItemNeRepeteLEcranDUnAutreItemDuMemePaquetTest`** : deux items du paquet ne deviennent pas
  le meme ecran.

---

## 6. `S35-CONS-4` - infaisable en l'etat : un blocage mesure, et sa sortie

### 6.1 Le blocage, et il est arithmetique

**Mesure, avec sa methode.** Comptage des attributions item par item dans
`adapters/adapter-content/src/main/resources/contenu/cm1-histoire-geographie.yaml` le 2026-09-11,
motif `ligneDeReferentiel: "HG-CM1-nn"`, sur l'arbre `60e1c836` :

```
HG-CM1-01  10     HG-CM1-06  10     HG-CM1-12  10
HG-CM1-02  10     HG-CM1-07  10     HG-CM1-13  10
HG-CM1-03  10     HG-CM1-08  10     HG-CM1-14  10
HG-CM1-04  10     HG-CM1-11  10                        total 110
```

La table des quatre entrees de `S34-CONS-4` retombe bien sur 110 (25 + 45 + 30 + 10), et la
quatrieme entree - *Lire un document* - sert **`HG-CM1-14` seule, soit exactement 10 items**.

`ChaqueJeuDuCatalogueOffrePlusDItemsQueDeManchesTest` (`ADR-0023`) exige :

```java
int vises = jeu.nombreDeManches() + 1;
assertThat(itemsDistincts(jeu.contenu(), vises))
        ...
        .isGreaterThan(jeu.nombreDeManches());
```

**Strictement plus**, et l'egalite est refusee **comme le defaut**, avec son motif ecrit
(*"un paquet aussi long que la partie ne produit aucune variete d'une partie a l'autre - c'est la
reserve que nadia-prof a posee en s04 sur les homophones, dix items pour dix manches"*).
**Une entree *Lire un document* a 10 items et `nombreDeManches: 10` est donc rouge**, et `DEC-0064`
impose par ailleurs `10` a toute entree *"sauf derogation ecrite"*.

**Trois textes du depot, tous valides, ne peuvent pas etre tenus ensemble** : la table des quatre
entrees, le defaut de `DEC-0064`, et `ADR-0023`. Ce n'est pas une difficulte d'implementation qu'un
developpeur resoudra en chemin : **c'est une decision qui manque**, et elle n'est pas de moi.

### 6.2 Les trois sorties, et celle que je recommande

| # | Sortie | Cout | Qui decide |
|---|---|---|---|
| S1 | **Une derogation ecrite** : *Lire un document* porte `nombreDeManches` < 10 | Une `DEC`, sur le patron exact de `DEC-0020` (7 manches pour un paquet de 11 items, motif ecrit). Zero ligne de code, zero risque technique | **`camille-po`**, et `nadia-prof` si la longueur d'une partie est en jeu (`DEC-0064`, section 4) |
| S2 | **Un onzieme item sur `HG-CM1-14`** | Contenu neuf : redaction, `HIS-1`/`HIS-2`/`HIS-3`, audit de `nadia-prof`. Contredit deux fois la story (*"elle ne cree aucun item neuf"*) et l'objectif du sprint | `camille-po` + `nadia-prof` |
| S3 | **Fondre *Lire un document* dans une autre entree** | Rouvre la decision de grain d'`ADR-0081`, que la story exclut explicitement | `camille-po` + `nadia-prof` |

**S1**, et le precedent est exact : `DEC-0020` a fait ce geste pour la meme cause arithmetique.
**Prealable de planning** : tant que cette `DEC` n'existe pas, la story n'est pas engageable sans
garantir un rouge. C'est le quatrieme sprint sur ce sujet ; il serait couteux qu'il devienne le
quatrieme sprint ou l'on decouvre le blocage a l'execution.

### 6.3 La forme technique de la scission, que la story me route

**Quatre paquets `yaml`, quatre entrees de catalogue.** Une entree de catalogue designe **un**
paquet (`catalogue.yaml`, cle `paquet:`) ; servir quatre entrees depuis un seul fichier demanderait
une notion de **selection d'items** qui n'existe nulle part et qui ferait entrer dans le schema une
cle que le chargeur unique devrait interpreter - `ADR-0007` d'un cote, la dette 3 de `CLAUDE.md` de
l'autre. Quatre fichiers, aucune notion neuve, le chargeur ne change pas d'une ligne.

**Les cinq gestes mecaniques que le lot doit connaitre avant de commencer**, tous mesures sur le
code :

1. **`HG-CM1-07` est declaree par deux paquets** (5 items dans *Situer dans le temps*, 5 dans
   *Nommer, en histoire*). **C'est permis** : le controle *"un code declare deux fois"* de
   `ChaquePaquetServiDeclareLesLignesDeReferentielQuIlSertTest` porte sur les **referentiels de
   `squad/programme`**, jamais sur les paquets, et `FER-2` se compte *"tous paquets confondus"*.
   Verifie en lisant le juge, pas suppose.
2. **Le paquet *Lire un document* ne sert qu'une ligne : ses items doivent **perdre** leur cle
   `ligneDeReferentiel`.** Le meme juge en fait une faute explicite - *"l'item porte
   'ligneDeReferentiel' alors que le paquet ne sert qu'une ligne"*. Recopier les items tels quels
   rend ce paquet rouge.
3. **`HIS-1` (au plus un tiers de restitution) ne bouge pas, a une condition.** Il se compte **sur
   le lot de matiere** derive des descripteurs (`ADR-0079`,
   `UneConditionDeLotSeCompteSurLeLotDeMatiereEtNonSurUnFichierTest`), pas sur le fichier : si les
   quatre paquets gardent `matiere: "Histoire-géographie"` et `niveau: "CM1"`, le lot reste le meme
   population et le tiers reste calcule sur 110 items - **23 restitution sur 110, soit 20,9 %**
   (comptage des marqueurs `# HIS-1:` le 2026-09-11 : 23 restitution, 63 association, 14 ordre,
   10 document). **Si un descripteur derive, le lot se scinde et le taux se recalcule par paquet**,
   ou rien ne garantit plus le tiers. C'est le seul endroit de cette scission ou une inattention de
   descripteur change un verdict.
4. **`LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest`** porte une entree d'exemption
   **chiffree** pour `cm1-histoire-geographie` (*"110 items ... 330 propositions"*). La clef
   disparait avec le fichier : **quatre motifs neufs, recomptes**, et l'ancien retire dans le meme
   diff. Un motif d'exemption qui ne nomme plus rien de reel est la faute que j'ai payee cinq fois
   a s34.
5. **`PaquetCm1HistoireGeographieTest`** est cable sur `PAQUET = "cm1-histoire-geographie"` (650+
   lignes). Il se scinde ou derive son perimetre des quatre paquets. **C'est le gros du travail
   technique de cette story**, et il n'apparait dans aucun critere d'acceptation.

**Ce qui ne pose aucun probleme, verifie** : `AucuneEntreeDuCatalogueNEnDoubleUneAutreTest` (quatre
identifiants, quatre titres, quatre paquets distincts), `AucunTitreDeJeuNePorteSonNiveauTest` (les
quatre titres proposes ne portent pas de niveau), `ChaqueEntreeDuCatalogueChargeBienSonPaquetTest`
(derive du catalogue).

**Un effet de bord a signaler a `sofia-sm`** : le catalogue passe de **70 a 73 entrees** (comptage
de `contenu/` : 70 fichiers, catalogue a 70 entrees). L'ecart encore ouvert *"71 titres de
l'accueil contre trois mesures a 70"* devient plus difficile a diagnostiquer apres la scission
qu'avant. **A trancher avant, pas apres** - ce n'est pas un prealable technique, c'est un ordre de
passage.

---

## 7. L'obstacle transverse : le recouvrement des boutons de zone sur le dessin

Route a `ines-design` et a moi a la cloture de s34. **Position : il est traite a part, et il n'est
absorbe ni par `S35-QLM11-PLANCLASSE` ni par `S35-QLM14-FRISE`.** Trois motifs, du plus fort au
plus faible.

**a) Ce n'est pas un defaut d'un paquet, c'est une consequence de la decision 4 d'`ADR-0080`.** Une
zone est un `<button>` en `position: absolute` pose sur la boite de l'image, a l'endroit exact que
son cadre declare, avec un fond translucide (`rgba(255, 255, 255, 0.55)`) et son libelle a
l'interieur. **Il recouvre donc le dessin qu'il designe par construction**, sur tout paquet de
designation present et futur. Le faire corriger par chaque paquet, dans ses propres cadres, c'est
garantir qu'il rouvre au paquet suivant - le motif exact que ce depot a sanctionne plusieurs fois.

**b) Absorbe dans les stories, il disparaitrait derriere une condition qui ne le couvre pas.**
`ZON-15` porte une troisieme clause (*"ne recouvre aucun trait du dessin que sa zone designe"*), et
il est tentant de croire que la tenir sur les six cadres du plan de classe ferme le sujet. Elle ne
le ferme pas : elle le tient **sur six cadres, sur un dessin, a une taille de police**. C'est un
perimetre enumere qui se croit general - la famille de defaut que j'ai deja payee.

**c) Sa forme naturelle n'est pas encodable, et il faut le dire maintenant.** Verifier qu'un bouton
ne recouvre pas un trait demande de comparer la boite rendue du bouton aux traits du `svg` ; la
premiere depend de la police du systeme (section 4.1), et `ADR-0025` a **decide** qu'une mesure de
rendu n'est pas un garde-fou de CI (*"il a besoin d'un navigateur, que rien dans le depot ne peut
garantir"*). Ce n'est donc pas un garde-fou qui manque par negligence : c'est une propriete dont le
depot sait, depuis s06, qu'il ne la garde pas automatiquement.

**Ce que `ADR-0092` decide a la place, plutot que de reporter une cinquieme fois** :

1. la propriete est **nommee, datee et rendue a l'oeil** - `nadia-prof` sur l'ecran servi, paquet
   par paquet, ce qui est deja son geste ;
2. la derniere clause d'`Accepte` d'`ADR-0080` est **ecrite dans le lot de `QLM-CP-11`** (elle y
   est due depuis s34) : a partir de la, tout ce qui **est** derivable de l'ecran reel de
   designation - les zones sont des boutons, leurs cadres sont ceux du contenu, le plancher de
   cible tactile est atteint - est vu par la CI et non plus au jar ;
3. un **cliquet exact** empeche qu'un troisieme paquet de designation entre en production pendant
   que la propriete reste tenue par un oeil seul ;
4. le levier gratuit de la section 4.2 (le pave de `.zone`) est **ecrit** pour que le prochain
   arbitrage de forme ait trois termes au lieu de deux.

**Ce que je ne fais pas** : choisir la forme. Ou vit le libelle d'une zone, ce qu'un bouton montre
du dessin qu'il designe, ce qu'un enfant de CP doit pouvoir lire sans deplacer son doigt - c'est
`ines-design` qui propose et `nadia-prof` qui tranche. `ADR-0092` borne le terrain et dit ce qui
sera verifiable quel que soit leur choix.

---

## 8. Ce que le planning doit retenir, et ce que je dois a ce sprint

**Prealables avant d'engager** :

- `S35-CONS-4` : la `DEC` de derogation de `nombreDeManches` sur *Lire un document*
  (`camille-po`). **Sans elle, la story produit un rouge garanti.**
- `S35-QLM11-PLANCLASSE` : le lot porte **aussi** la derniere clause d'`Accepte` d'`ADR-0080`, due
  depuis s34. A chiffrer dans son affectation, sinon elle glissera une cinquieme fois.

**Ce que je dois, comme lot** : les deux `ADR` ouverts ici passent de `Propose` a `Accepte` **quand
leurs regles sont dans l'arbre, pas avant** - `ADR-0091` deux garde-fous de contenu, `ADR-0092` un
garde-fou de rendu dans `bootstrap` et un cliquet. Le code des trois est ecrit dans les `ADR` ;
c'est le lot qui le commite, avec ses jeux fautifs (`ADR-0013`, obligation 3).

## 9. Ce que j'ai lance sur mon propre diff, et ce que je n'ai pas lance

**Le geste que `CLAUDE.md` reclame depuis la cloture de s31 - lancer la commande de la CI sur son
propre diff avant de commiter - applique a ce diff, qui ne touche que trois fichiers de `squad/`.**

| Commande | Perimetre | Resultat |
|---|---|---|
| `bash .github/scripts/verifier-chemins.sh` | tout le depot (le job `chemins` en entier) | **vert** - *"Aucun chemin de poste de travail ni nom de compte dans les fichiers verifies."* |
| `bash outils/mvn-jdk21.sh -B -q -pl bootstrap -am test -Dtest='ToutesLesReferencesDeDecisionResolventTest,ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest,AucuneLigneDeTableauNePortePlusDeCellulesQueSonEnTeteTest'` | **mesure partielle, avec `-am`** : les trois garde-fous de `bootstrap` qui lisent `squad/` - references de decision, identifiants de condition alloues, cellules de tableau | **vert** - 3, 7 et 5 tests, **0 echec** (rapports Surefire de `bootstrap`) |

**Methode, dans le meme paragraphe que les chiffres** : arbre `60e1c836` plus les trois fichiers de
ce lot, worktree dedie `s35-theo`, JDK **21.0.10** - version que l'enveloppe rapporte apres avoir
resolu la toolchain, non celle que l'entree de toolchain declare (`ADR-0071`). **Le `-am` est porte**
(`CLAUDE.md`, prerequis de build). **Ce perimetre est trois classes de test d'un module, pas le
depot** : il couvre exactement les garde-fous que ce diff peut faire rougir, et rien d'autre.

**Ce que je n'ai pas lance, et qui reste du** : `mvn -B clean verify` sur la racine, et les trois
autres jobs de `.github/workflows/ci.yml` (`build`, `outils`, `prefixes`). **Aucune phrase de cet
avis n'annonce la porte technique** : la confirmation de la porte, jobs nommes un par un, est un
geste de review, et l'amendement du 2026-09-08 de `CLAUDE.md` dit exactement pourquoi un `mvn
verify` seul ne la vaut pas.
