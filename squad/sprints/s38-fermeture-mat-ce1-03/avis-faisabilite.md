# s38 - Avis de faisabilite (`theo-archi`)

- **Date** : 2026-09-22
- **Base** : `round-two` a `0c215ee4`, stories de `camille-po` dans `docs/backlog/backlog.md`,
  section "Sprint 38" (non commitees a l'heure de cet avis)
- **Perimetre de cet avis** : les **quatre** lots de `docs/backlog/backlog.md`, section
  "Sprint 38". Sections 1 a 6 : `S38-CORR`, rendues avant l'arbitrage. Sections 7 a 12 :
  `S38-SOUSTRACTIONS`, `S38-COMPLEMENTS-DIZAINE` et `S38-MOITIES`, rendues apres l'arbitrage
  pedagogique prealable de `nadia-prof` du 2026-09-22
  (`squad/conformite/ARBITRAGE-s38-soustractions-complements-dizaine-et-moities-ce1.md`,
  commit `11da48d6`).
- **Ce que cet avis ne vaut pas** : il ne vaut pas livraison. L'affectation nominative du
  developpement revient a `sofia-sm` (`DEC-0091`, section 2.2). Aucun code n'est ecrit avec cet
  avis.

---

## 1. Verdict

**`S38-CORR` est faisable en l'etat, sans `ADR` et sans regle ArchUnit neuve.** Six valeurs a
changer dans un seul fichier de donnees. Aucune classe Java, aucun schema, aucun contrat ne bouge.

Une seule chose peut faire echouer ce lot, et ce n'est pas le calcul : **le caractere d'espace
devant les deux-points des cinq indices**. Section 4.

## 2. Ce qui est a corriger, et ou

`S37-1` a livre un **paquet statique** (`ADR-0096`), pas un generateur. Les deux reserves vivent
donc integralement dans le YAML servi, et nulle part ailleurs :

- `adapters/adapter-content/src/main/resources/contenu/ce1-additions-jusqu-a-100.yaml`
  - `ADD-CE1-R1` : cle `indice` des cinq items des lignes 70, 74, 78, 82, 86 (`40 + 25`,
    `30 + 47`, `60 + 18`, `50 + 33`, `20 + 36`).
  - `ADD-CE1-R2` : cle `distracteurs` de l'item de la ligne 138 (`48 + 26`), aujourd'hui
    `["75", "76"]`.

`contenu-repris/` n'est pas concerne : ce paquet est neuf, ecrit a s37. Aucun test enumere ce
paquet a l'exception de `ChaquePaquetDAdditionsDeCe1TientSaRepartitionParFormeTest` (ligne 255,
clause de non-vacuite du recensement), et aucun fichier de test ne recopie ses indices ni ses
distracteurs - verifie par recherche de `ce1-additions` dans tout le Java du depot : une seule
occurrence, celle-la. **Aucun test n'est donc a modifier.**

## 3. Ce que les garde-fous existants disent de cette correction

Mesure de reference, avant toute modification : `-pl adapters/adapter-content -am test`,
**725 tests, 0 echec**, `BUILD SUCCESS`, JDK 21.0.10 tel que la machine virtuelle des tests le
rapporte via `outils/mvn-jdk21.sh`. Perimetre : trois modules (`domain`, `application`,
`adapter-content`), pas le depot ; la porte technique reste `mvn -B clean verify` plus les quatre
jobs de `.github/workflows/ci.yml`.

### 3.1 Ce que la correction ne peut pas casser

- **`ADD-CE1-2` (repartition par forme)** et **`ADD-CE1-1` (operande nul)** :
  `ChaquePaquetDAdditionsDeCe1TientSaRepartitionParFormeTest` ne lit **que** la cle `enonce`
  (motif `ADDITION`, `formeDe(a, b)`). Ni `indice` ni `distracteurs` n'entrent dans son juge. Les
  32 enonces ne bougent pas, les huit bandes sont invariantes par construction.
- **Exactitude des 32 sommes** : aucune `bonneReponse` ne bouge.
- **`AucunItemNeRepeteLEcranDUnAutreItemDuMemePaquetTest`** : l'ecran est le couple
  `enonce + ensemble de propositions`, et l'indice n'y entre pas (Javadoc, ligne 69). Le nouvel
  ensemble de `48 + 26` (`{74, 64, 75}` ou `{74, 64, 76}`) ne collisionne avec aucun autre item.
- **`AucunePropositionNEstEgaleAUneAutreDansUnItemTest`** et
  **`AuPlusTroisPropositionsParItemTest`** : `64` est distinct de `74` et du distracteur conserve,
  et l'item reste a trois propositions.
- **`AucunIndiceNeContreditUnAutreItemDuMemePaquetTest`** : sa population est la forme "regle
  generale non temperee" de la conjugaison ; un indice arithmetique n'y entre pas.
- **`AucunMotServiNeSEcarteDeSonAccentTest`** : les cinq textes de remplacement ne contiennent
  aucun mot qui demande un accent.

### 3.2 Ce qui bouge, sous les seuils

`LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` classe chaque item par le rang de la
bonne reponse parmi ses trois propositions triees. Mesure sur les 32 items du fichier servi :

| | plus petite | intermediaire | plus grande |
|---|---|---|---|
| Avant | 10 (31,25 %) | 12 (37,50 %) | 10 (31,25 %) |
| Apres (`64` remplace `75` **ou** `76`) | 9 (28,12 %) | 13 (40,62 %) | 10 (31,25 %) |

Les deux seuils restent tenus : majorite stricte < 50 %, rang le plus rare >= 10 %. Le paquet
n'entre ni dans `DETTE_DE_RANG` ni dans `DETTE_DE_SEUIL_MINIMUM`. **Les deux choix techniques ouverts
par le critere d'acceptation (`75` ou `76`) sont equivalents du point de vue des garde-fous** ; la
plausibilite du distracteur conserve releve de `nadia-prof` au reaudit, pas de moi.

## 4. Le seul vrai risque de regression, et il est typographique

**`AucunSigneDoubleNEstDetachableDeSonMotTest` (TYP-1, `ADR-0067`) est un cliquet en egalite, pas
un plafond.** `DETTE_PAR_FICHIER` ne contient pas `ce1-additions-jusqu-a-100`, et un contenu qui
porte des occurrences sans figurer dans la table fait rougir le garde-fou. Mesure sur le fichier
servi : **0 occurrence** d'un signe double precede d'une espace ordinaire, sur 96 textes lus.
Le fichier porte 53 espaces insecables U+00A0, dont un devant chaque deux-points d'indice.

**`FICHE-bandit-manchot-additions-ce1.md`, section 7.1, ecrit ses cinq textes de remplacement avec
une espace ordinaire devant les deux-points** - c'est du Markdown, pas du YAML, et c'est normal.
Le critere d'acceptation dit *"le texte servi est exactement"* ce texte. Copier-coller ces cinq
chaines telles quelles dans le YAML porte la dette du fichier de 0 a 5 et **rend le build rouge**.

Ce n'est pas une contradiction entre le critere et la regle : `ADR-0067` et
`LeTexteServiPorteLeCaractereDecideJamaisSaNotationTest` distinguent precisement le **caractere
servi** de sa **notation**. L'espace insecable est le caractere que le depot a decide pour cette
position ; il ne change pas le texte que `nadia-prof` a ecrit, il l'ecrit correctement.

**Consigne pour le developpeur affecte** : ecrire U+00A0 devant les deux-points des cinq indices,
le meme caractere que les 32 indices du fichier portent deja. Verification en une commande apres
edition : `-pl adapters/adapter-content -am -Dtest=AucunSigneDoubleNEstDetachableDeSonMotTest
-Dsurefire.failIfNoSpecifiedTests=false test`.

## 5. Ce que je ne fais pas, et ce que je remonte

- **Aucun `ADR`.** Une correction de donnees dans un paquet existant ne revele aucune regle
  transverse neuve : les cinq proprietes qui la contraignent sont deja encodees, et je les ai
  listees plutot que presumees. Ecrire un `ADR` ici serait documenter apres coup une decision
  deja prise ailleurs.
- **Aucune regle ArchUnit neuve.** La propriete *"un indice, suivi a la lettre, mene a une
  proposition presente a l'ecran"* serait le garde-fou que `ADD-CE1-R1` appelle, et je ne le
  propose pas : il demanderait d'executer le francais d'un indice, ce qu'aucun automate de ce
  depot ne sait faire. `nadia-prof` l'a deja ecrit en section 7.1 (*"le seul endroit qu'aucun
  garde-fou du depot ne lit : l'indice"*). **C'est une dette nommee, pas une regle manquante que
  je cache** ; elle reste gardee par la porte pedagogique.
- **Aucun risque justifiant de router ailleurs avant affectation.** Le lot ne rouvre aucun
  arbitrage : les six textes sont ceux de `nadia-prof`, repris mot pour mot. `sofia-sm` peut
  affecter des maintenant.
- **Ce que l'affecte doit rendre avec son commit** : la porte technique complete
  (`mvn -B clean verify` **et** les quatre jobs de `.github/workflows/ci.yml`, nommes un par un),
  puis le reaudit de `nadia-prof` qui leve - ou non - `ADD-CE1-R1` et `ADD-CE1-R2` item par item.
  Le statut de `MAT-CE1-03` ne bouge pas par ce lot seul.

## 6. Complexite

**Triviale.** Six valeurs, un fichier, zero ligne de Java. Le temps du lot est celui du reaudit
pedagogique, pas celui de l'edition.

---

# Partie 2 - les trois sous-parties restantes de `MAT-CE1-03`

- **Date** : 2026-09-22, apres `11da48d6`
- **Entree** : `ARBITRAGE-s38-soustractions-complements-dizaine-et-moities-ce1.md`, six clauses
  (`SOU-CE1-1`, `SOU-CE1-2`, `CDZ-CE1-1`, `CDZ-CE1-2`, `MOI-CE1-1`, `MOI-CE1-2`)

## 7. Verdicts, en une ligne chacun

| Lot | Verdict |
|---|---|
| `S38-SOUSTRACTIONS` | **Faisable moyennant un `ADR`.** Paquet statique, une clause a encoder en garde-fou derive. Un cas degenere de sa section 1.4 est a ecarter, et il l'est gratuitement. |
| `S38-MOITIES` | **Faisable moyennant le meme `ADR`.** Meme forme. Une contrainte de dimensionnement a connaitre avant d'ecrire les items. |
| `S38-COMPLEMENTS-DIZAINE` | **Infaisable en l'etat.** La section 2.3 de l'arbitrage, appliquee a la lettre, fait rougir un garde-fou existant sur **tout** paquet possible, quelles que soient les valeurs choisies. Rouvre a `nadia-prof`, section 10. |

Les trois verdicts sont des avis. Aucun ne vaut affectation (`DEC-0091`, section 2.2).

## 8. Le choix de mecanisme : statique pour les trois, et `ADR-0096` ne le decide pas deja

`ADR-0096` a decide le statique **pour le premier paquet CE1**, nommement. Sa decision 3 - *"la
regle est la table, jamais le moyen"* - est generale, sa decision 2 ne l'est pas. Trois paquets
neufs demandent donc une decision ecrite, et **un seul `ADR` pour les trois** suffit : c'est la
meme question posee trois fois, pas trois questions.

Les arguments d'`ADR-0096` se transposent, et l'un d'eux se renforce :

- **Le paquet statique reste la norme du depot** : 21 paquets de mathematiques servis a s37, 20 de
  type `question`, 1 generatif (`ADR-0096`, justification 1). Le premier paquet CE1 en fait 22 et
  21.
- **La condition de reversibilite qu'`ADR-0096` s'etait ecrite n'est pas atteinte.** Elle disait :
  *"ce qui me ferait changer d'avis : un deuxieme paquet demandant la meme table"*. `SOU-CE1-2`
  (sept cases, emprunt) et `MOI-CE1-2` (quatre cases, parite) sont des tables **differentes** de
  `ADD-CE1-2` et l'une de l'autre. Chaque table sert un paquet et un seul : rien a mutualiser.
- **Le juge reste independant de ce qu'il juge** (`ADR-0096`, justification 4). Avec un generateur
  portant la partition en production, le garde-fou verifierait le tirage avec le code du tirage.
- **La conformite est exacte sur une liste, estimee sur un tirage** - `nadia-prof` le redit
  elle-meme dans le troisieme critere de chaque story ("mesuree sur la liste des items pour un
  paquet statique, ou sur 200 000 tirages a graine fixee pour un paquet generatif").

**Cout constate, pas estime** : le patron exact de `S37-1` (commit `9412f659`) est de cinq
fichiers et **zero ligne de Java de production** - une entree dans `catalogue.yaml`, un YAML de
contenu, un test de garde-fou derive, son jeu fautif commite (`ADR-0013`, obligation 3), et
l'extension de l'inventaire de `TexteAccentueExposeParLePortEntrantTest` (titre, consigne,
competence). Trois lots, trois fois ce patron.

## 9. La question de conception que l'`ADR` doit trancher, et c'est la seule

Le perimetre d'un garde-fou derive doit se deduire de la **forme des items**, jamais d'une liste de
noms (`ADR-0013`). Pour deux des trois lots, cette derivation est mecanique ; pour le troisieme,
elle ne l'est pas, et c'est le seul vrai point de conception du sprint.

- **Soustractions** : `a - b = ?`, motif sur l'enonce, symetrique exact de la classe `ADDITION`
  d'`ADD-CE1-2`. Mecanique.
- **Complements a la dizaine** : `a + ? = d`, motif sur l'enonce. Mecanique. **Et il ne collisionne
  pas avec `ADD-CE1-2`** : verifie sur le motif reel
  (`ChaquePaquetDAdditionsDeCe1TientSaRepartitionParFormeTest`, champ `ADDITION`), `47 + ? = 50` ne
  s'apparie pas, `retenir()` rejette le paquet entier des son premier item, et le paquet sort de la
  population des additions au lieu d'y entrer a moitie.
- **Moities** : l'enonce est du francais libre (*"Quelle est la moitie de 58 ?"*). Un motif sur le
  mot *"moitie"* serait un perimetre derive **en apparence** : un paquet neuf qui formulerait
  autrement sortirait de la population **en silence**, et le garde-fou passerait au vert sans avoir
  rien lu. C'est la famille de defaut que ce depot sanctionne le plus souvent, et elle serait ici de
  ma main.

Ma preference, a inscrire dans l'`ADR` avec ce qui me la ferait changer : **faire declarer sa forme
au descripteur du paquet** plutot que la deviner dans son francais, et faire de l'absence de
declaration un rouge - un paquet muet n'est alors pas un paquet exempte. Ce qui me ferait changer
d'avis : un motif d'enonce qu'une clause de `nadia-prof` fixerait elle-meme, ce qui rendrait la
declaration redondante.

## 10. `S38-COMPLEMENTS-DIZAINE` - pourquoi il est infaisable en l'etat

**Le fait, et il est demontre, pas estime.** `CDZ-CE1-1` borne la bonne reponse : `d - a` est un
entier de **1 a 9**. La section 2.3 prescrit les **deux** distracteurs, sans laisser de choix :
`unite(a)`, un chiffre de 1 a 9, et `d`, un multiple de dix de **10 a 100**. Donc, sur **tout** item
conforme, `d > 9 >= bonne reponse` et `d > 9 >= unite(a)` : **`d` est toujours la plus grande des
trois propositions, et la bonne reponse ne l'est jamais**. Pas *rarement* : jamais, sur tout paquet
que `CDZ-CE1-1` et 2.3 autorisent a exister.

`LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` verifie qu'aucun des trois rangs ne tombe
sous `SEUIL_MINIMUM = 0.10`. Le rang *"la plus grande"* vaudrait **0,0 %**. Le paquet est rouge a la
livraison, et il ne peut pas etre exempte : `DETTE_DE_SEUIL_MINIMUM` porte trois noms pour
`PLAFOND_DE_DETTE_SEUIL_MINIMUM = 3` - le cliquet est ferme, exactement comme il a ete ecrit pour
l'etre.

**Ce n'est pas une contrainte d'outillage qui gene une bonne idee pedagogique.** Ce garde-fou encode
le refus de `nadia-prof` du 2026-08-14 sur `cm1-tables-multiplication`, et sa reserve du
2026-08-15 : un rang quasi vide *"elimine une proposition gratuitement a presque chaque manche"*.
Ici l'enfant apprend en une partie que la grosse proposition n'est jamais la reponse, et il elimine
une proposition sur trois sans lire l'enonce. C'est le defaut que sa propre porte existe pour
attraper.

**Second effet, plus doux mais a citer** : la bonne reponse `10 - unite(a)` est plus petite que
`unite(a)` si et seulement si `unite(a) > 5`. `CDZ-CE1-2` impose les neuf complements, donc les neuf
valeurs d'unite ; a repartition egale, le rang *"intermediaire"* vaut **5/9 = 55,6 %**, au-dessus de
`SEUIL_MAJORITE = 0,5`. Celui-la, contrairement au premier, est evitable en ponderant les items
vers `unite(a) > 5`.

**Ce que je ne fais pas** : je ne choisis pas le distracteur de remplacement. La plausibilite d'une
erreur d'enfant est a `nadia-prof`, et elle a ecrit en 2.3 le motif de chacun des deux qu'elle
prescrit. **Ce que je lui route**, via `sofia-sm` : sur au moins un item sur dix, les deux
distracteurs doivent etre **inferieurs** a la bonne reponse. Comme la bonne reponse vaut au plus 9,
cela demande de remplacer `d` sur ces items-la par une valeur d'un seul chiffre. La question qui lui
revient est donc : *quelle erreur d'enfant, sur un complement a la dizaine, produit un nombre plus
petit que le complement lui-meme ?*

**Tant que cette reponse n'est pas ecrite, le lot n'est pas engageable** : le livrer tel quel, c'est
livrer un paquet rouge, et le livrer en exemptant le garde-fou, c'est ouvrir un cliquet ferme pour
faire passer un defaut que sa proprietaire a deja refuse une fois.

## 11. `S38-SOUSTRACTIONS` et `S38-MOITIES` - ce qui est verifie, et les deux pieges

### 11.1 Les deux tables sont satisfaisables en nombres entiers, pas seulement en pourcentages

`nadia-prof` a verifie la satisfaisabilite en pourcentages (60 <= 100 <= 145 pour `SOU-CE1-2`,
45 <= 100 <= 115 pour `MOI-CE1-2`). Sur un paquet **fini**, la quantification peut rendre une bande
inatteignable ; je l'ai donc reverifiee en comptes entiers, sur la taille du paquet de s37 (32
items), bornes incluses :

- `SOU-CE1-2` : `(0, 0, 6, 5, 7, 7, 7)` totalise 32 et tient les sept bandes.
- `MOI-CE1-2` : `(0, 14, 9, 9)` totalise 32 et tient les quatre bandes.

Aucune des deux tables n'impose donc de taille de paquet particuliere. Rappel du plancher du
depot : `ChaqueJeuDuCatalogueOffrePlusDItemsQueDeManchesTest` exige plus d'items que de manches.

### 11.2 Piege 1 - la forme 1 de `SOU-CE1-2` n'admet aucun distracteur conforme

Sur un item a soustracteur nul (`b = 0`), la bonne reponse est `a`. Des trois erreurs identifiables
de la section 1.4 : `a + b` vaut `a`, **donc la bonne reponse elle-meme** ; `b` vaut `0`, que la
meme phrase exclut (*"entiers positifs"*) ; l'emprunt oublie n'est defini que sur la forme 7. Aucune
ne subsiste, et l'exigence *"au moins un distracteur correspond a une erreur identifiable"* ne peut
pas etre tenue.

**Ce n'est pas bloquant, et c'est gratuit** : la forme 1 est bornee `0 % a 5 %` **sans plancher** -
`SOU-CE1-1` le dit en gras. Un paquet **sans aucun item a soustracteur nul** est pleinement
conforme. Consigne au developpeur affecte : zero item de forme 1. Je le nomme plutot que de le
laisser decouvrir a l'ecriture du douzieme item.

### 11.3 Piege 2 - le rang de la bonne reponse, sur ces deux lots aussi

Meme garde-fou qu'en section 10, mais ici il ne bloque pas : les sections 1.4 et 3.4 disent *"au
moins un distracteur"*, donc le **second** est libre, et c'est ce degre de liberte qui manque a
`S38-COMPLEMENTS-DIZAINE`.

Toutes les erreurs identifiables des deux sections produisent une valeur **superieure** a la bonne
reponse, a une exception : `n / 2 - 1` pour les moities. Sans attention, les deux paquets rendraient
donc la bonne reponse *"la plus grande"* sur 0 % de leurs items - le meme rouge, atteint par
distraction au lieu de l'etre par construction. **Consigne** : sur au moins 10 % des items de chaque
paquet, les deux distracteurs sont inferieurs a la bonne reponse, le second etant alors choisi
librement. Pour les moities, cela demande `n >= 6` sur ces items-la ; `MOI-CE1-2` ligne 2 en offre
largement.

### 11.4 Deux points mineurs, signales sans etre tranches

- **`a + b` peut depasser 100** comme distracteur d'une soustraction (`87 - 30` donne `117`). Aucune
  regle encodee ne l'interdit ; `nadia-prof` a compte *"distracteur > 100 : aucun"* sur les
  additions de s37 sans en faire une clause. Le distracteur `b` reste disponible sur ces items. A sa
  main a l'audit.
- **La forme 3 de `MOI-CE1-2` n'offre que huit valeurs** (`n` multiple de dix de 30 a 100). Sa bande
  `15 % a 30 %` plafonne donc un paquet sans enonce repete a environ 53 items. Sans effet a 32 ;
  a savoir avant de viser plus grand.

## 12. Ce que chacun des trois lots devra porter, et ce qui reste hors de tout automate

**Contraintes du depot qui s'appliquent a tout paquet neuf** - elles ne sont pas dans l'arbitrage
parce qu'elles ne sont pas pedagogiques, et elles font rougir la porte technique si on les oublie :

- **Un `indice` sur chaque item.** `ChaquePaquetPorteUnIndiceTest` porte `DETTE_DE_CONTENU` vide et
  `PLAFOND_DE_DETTE = 0` : aucun paquet neuf n'herite d'une exemption. `nadia-prof` a explicitement
  laisse les indices hors de son arbitrage (section 4) et les jugera a l'audit - ce sont donc trois
  jeux d'indices a ecrire, et c'est le vrai volume de redaction des trois lots.
- **Espace insecable U+00A0 devant `?`, `:`, `!` et `;`** dans tout texte servi. Meme cliquet en
  egalite qu'en section 4 : un paquet neuf absent de `DETTE_PAR_FICHIER` doit etre a zero
  occurrence. Un enonce de moities finissant par un point d'interrogation est le cas le plus expose.
- **Titre sans niveau** (`ADR-0078`), **inventaire du port entrant** a etendre (titre, consigne,
  competence, cf. `9412f659`), **enonce plus propositions unique** dans le paquet, **accents
  exacts** (le mot "moitie" s'ecrit accente dans un texte lu par l'enfant).

**Ce qu'aucun garde-fou ne verra, et que `nadia-prof` garde seule** - elle l'ecrit en section 5 de
son arbitrage et je ne le recopie que pour qu'il ne se perde pas entre l'avis et la livraison : que
le paquet travaille reellement la sous-partie qu'il declare et non `MAT-CE1-04`/`MAT-CE1-05`, la
progression a l'interieur d'une partie, la lisibilite (`LON-CE1-1`, `LON-CE1-2`, qui ne sont
encodees nulle part - verifie par recherche dans tout le Java du depot), l'exactitude arithmetique
de chaque distracteur, et la coherence de chaque indice. Les six clauses chiffrees, elles, seront
executables.

**Ce que je ne fais pas, et ce que j'attends** : je n'ecris pas l'`ADR` ici - il se redige avec la
regle qu'il rend verifiable, dans le meme geste, et cette regle appartient au lot engage. Je
n'affecte personne. `sofia-sm` peut engager `S38-SOUSTRACTIONS` et `S38-MOITIES` des maintenant ;
`S38-COMPLEMENTS-DIZAINE` attend la reponse de `nadia-prof` a la question de la section 10, et sans
elle la fermeture de `MAT-CE1-03` a `couvert-valide` dans ce sprint n'est pas atteignable.
