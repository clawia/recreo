# Lot 5a - Tri des garde-fous de contenu face a un item de saisie

- **Sprint** : s24. **Date** : 2026-08-31. **Auteure** : `sofia-sm`, **casquette developpeuse**.
- **Lot** : 5a, tel que `planning.md` section 5.3 b) le scinde du lot 5. Ne depend de rien, demarre
  au jour 1.
- **Arbre mesure** : worktree dedie `s24-sofia-dev`, branche `s24-sofia-dev`, tete de branchement
  `s24-sofia` (planning du sprint). Lignee verifiee avant la premiere ecriture par
  `git merge-base --is-ancestor s24-sofia HEAD` et `git merge-base --is-ancestor round-two HEAD`,
  vrais tous les deux.
- **Ce document ne code rien.** Il decide, pour chacun des garde-fous nommes, ce qu'il fait d'un item
  de saisie et **pourquoi**. Le code qui execute ces decisions est le lot 5b.
- **Ce document ne decide ni le schema YAML du paquet de saisie, ni la fabrique, ni le nom du type
  d'item** : c'est le lot 1 (`marc-dev`), sous `ADR-0069` et `ADR-0070`. Quand une decision d'ici
  contraint le lot 1, elle le dit et elle dit sur quel garde-fou elle s'appuie - elle ne choisit pas
  a sa place.

---

## 1. La mesure, refaite par moi, avec sa methode et son perimetre

`theo-archi` mesure, sur un paquet de sonde depose dans `contenu/` puis retire :
**288 tests, 8 echecs, 28 erreurs, 13 classes**, perimetre `adapters/adapter-content` avec `-am`.
La consigne etait de **citer et de verifier**, pas de croire. J'ai repose une sonde et mesure
moi-meme.

### 1.1 Ce que ma sonde contient

`theo-archi` a mesure sur un arbre ou le lot 1 n'existe pas. Une sonde y est un fichier YAML que le
chargeur **refuse** (`type de contenu inconnu`). J'ai voulu mesurer l'etat que le lot 5b affrontera
reellement : un paquet de saisie **qui se charge**. Ma sonde est donc plus grosse que la sienne, et
elle est temporaire de bout en bout - **rien de ce qui suit n'est commite** :

- `domain` : `Item permits Question, ReponseAEcrire`, le record `ReponseAEcrire` de la section 1
  d'`ADR-0058` corrigee par `ADR-0069` (un enum du paquet de contrat, jamais un booleen), et la
  branche manquante du `switch` de `BanditManchotSession.questionDe` ;
- `adapter-content` : une fabrique `saisie` enregistree dans la table du chargeur ;
- `contenu/` : un paquet de complements a 10 en saisie, onze items, `enonce` en premier champ.

### 1.2 Les quatre mesures, chacune avec son perimetre

Commande, identique aux quatre :
`bash outils/mvn-jdk21.sh -B -o -pl adapters/adapter-content -am test`.
**Le `-am` est porte parce que la mesure est partielle** (`CLAUDE.md`) ; **le perimetre est le module
`adapter-content` et ses amonts, jamais le depot**. Comptage : agregation des attributs `tests`,
`failures` et `errors` des `TEST-*.xml` de `target/surefire-reports/`.

| # | Etat de l'arbre | Tests | Echecs | Erreurs | Classes rouges |
|---|---|---|---|---|---|
| 0 | Reference, sans sonde | 288 | 0 | 0 | 0 |
| A | Sonde chargeable, matiere ecrite comme partout ailleurs | 288 | 8 | 26 | **12** |
| B | Meme sonde, matiere ecrite en ASCII | 288 | 8 | 27 | 13 |
| C | Sonde A **plus une entree au catalogue** | 288 | 8 | **27** | **13** |

**Le total de 288 ne bouge dans aucun etat** : aucun test n'est ajoute ni retire par une sonde, ce
sont les memes 288 qui changent de verdict. Le chiffre de `theo-archi` est de la meme famille que le
mien, son ordre de grandeur tient, et **sa cause est celle qu'il nomme** : les transtypages
`(Question) item`, dont chaque erreur mesuree porte la trace -
`ClassCastException: class ...ReponseAEcrire cannot be cast to class ...Question`.

**Un fait que le comptage brut cache et qui compte pour le lot 5b** : les **8 echecs** ne sont pas une
seconde cause. Ce sont des clauses de **mise a l'epreuve** - `assertThatThrownBy(...)
.isInstanceOf(AssertionError.class)` sur le contenu de production - qui recoivent une
`ClassCastException` la ou elles attendent un `AssertionError`. Autrement dit, **la sonde ne rend pas
seulement ces garde-fous rouges : elle leur retire leur propre morsure.** Ils sont rouges ici parce
que leur assertion sur le type de l'echec est stricte ; ecrite plus mollement, elle serait passee
**verte sur une cause fausse**.

### 1.3 D'ou vient l'ecart avec ses chiffres, plutot que de le taire

**13 classes chez lui, 12 chez moi a l'etat A, et ce ne sont pas les memes 13.**

1. **`UneMatiereNAQuUneSeuleGraphieDansLeContenuServiTest` est dans sa liste et pas dans la mienne.**
   Etat B contre etat A : ce garde-fou passe au rouge **quand et seulement quand la sonde ecrit sa
   matiere autrement que les autres paquets**. Il ne lit pas les items, il lit le descripteur, que
   tout paquet porte quel que soit son type. **Sa presence dans les treize est une propriete de la
   sonde, pas une propriete de l'item de saisie.** C'est le seul des treize dont l'inscription ne se
   reproduit pas, et il fallait poser la sonde pour le voir.
2. **`ChaqueEntreeDuCatalogueChargeBienSonPaquetTest` est dans ma liste et pas dans la sienne.** Sa
   sonde etait posee dans `contenu/` **sans entree au catalogue** ; `S24-SAI-2` en ajoute une. Etat C
   contre etat A : ce garde-fou-la se reveille a l'entree de catalogue, pas au depot du fichier.
   **Le lot 5b a donc un quatorzieme poste que le lot 5, tel qu'il est ecrit, ne compte pas.**
3. **26 erreurs a l'etat A, 27 a l'etat C, 28 chez lui.** L'ecart residuel d'une erreur tient a la
   composition de sa sonde, que je n'ai pas sous les yeux. Je ne le devine pas et je le laisse ecrit
   comme un ecart.

**Ce que je retiens de l'exercice** : le nombre 13 se reproduit, la **liste** ne se reproduit pas. Une
sonde mesure ce qu'elle contient. C'est pourquoi le tri ci-dessous porte sur **quatorze** garde-fous -
les treize nommes par l'avis de faisabilite, plus celui que le catalogue reveille - et pourquoi chaque
ligne dit **ce qui la declenche**.

### 1.4 Deux faits de plus, que la sonde donne et qu'il faut avoir avant le lot 5b

- **Le reacteur complet n'est pas mesurable tant que le lot 1 n'est pas la.** `bash
  outils/mvn-jdk21.sh -B -o test` sur la racine s'arrete a la **compilation des tests de
  `bootstrap`** : `PropositionsAccentueesJouablesIT.java:[171,32] the switch expression does not
  cover all possible input values`. C'est le second des deux arrets qu'`ADR-0069` annonce, au
  caractere pres, et il est **bruyant**. **Consequence de sequencement** : aucun garde-fou de
  `bootstrap`, regles ArchUnit comprises, ne dit quoi que ce soit d'un paquet de saisie tant que cet
  arret n'est pas leve par le lot 1.
- **`adapter-web` ne bouge pas** : 332 tests, 0 echec, 0 erreur, avec la sonde chargee et le paquet au
  catalogue. Aucun garde-fou de ce module ne derive du contenu des items. Le lot 4 reste seul
  responsable de son perimetre.

---

## 2. Le principe du tri, ecrit une fois plutot que quatorze

`theo-archi` refuse d'avance *"un transtypage defensif silencieux (`if (item instanceof Question)`
sans motif ecrit) dans les treize"*, et il a raison : treize exemptions muettes posees en une fois
sont la famille de defaut que ce depot sanctionne depuis s05. Voici ce que j'y substitue, et c'est la
meme forme pour les quatorze.

**Trois questions, dans cet ordre, pour chaque garde-fou :**

1. **Sur quoi porte sa promesse ?** Le texte que l'enfant **lit** ? Ce qu'il **presse** ? Ce que le
   paquet **declare** ? La reponse decide tout le reste, et elle se lit dans le motif du garde-fou,
   pas dans son code.
2. **Un item de saisie porte-t-il l'objet de cette promesse ?** S'il le porte, le garde-fou est
   **etendu**. S'il ne le porte pas, il est **borne**.
3. **Comment la borne se relit-elle dans six mois ?** Une borne n'est jamais un `instanceof` sans
   phrase. Elle est **une population comptee et affirmee non vide** : le garde-fou dit combien
   d'items il a juges et combien il a ecartes, et un garde-fou qui ecarterait tout est rouge. C'est
   la correction du 2026-08-12 sur `AucunIndiceNeContreditUnAutreItemDuMemePaquetTest` appliquee
   d'avance, plutot qu'apres que trois fiches de conformite aient credite un garde-fou d'une garantie
   qu'il ne tenait pas.

**Ce que cela veut dire concretement pour le lot 5b** : le classement d'un item - *presente des
propositions* / *demande une reponse ecrite* - est **une** decision, ecrite une fois, et chaque
garde-fou declare **son propre domaine** par rapport a elle, avec son motif a lui. Ce qui est partage
est le classement ; ce qui ne l'est jamais est le critere garde - exactement comme `itemsDeclares` et
`paquets` sont deja partages sans qu'aucune assertion ne le soit (`ChaquePaquetPorteUnIndiceTest`,
Javadoc d'`itemsDeclares` : *"ce qui est partage est un comptage de lignes, jamais le critere
garde"*).

---

## 3. Les quatorze decisions

`E` = etendu a l'item de saisie. `B` = borne explicitement, avec motif. La colonne *declencheur* dit
ce qui met le garde-fou en presence de l'item.

| # | Garde-fou | Declencheur | Sens | Ce qu'il devient |
|---|---|---|---|---|
| 1 | `AuPlusTroisPropositionsParItemTest` | repertoire | **B** | Aucune proposition a compter |
| 2 | `AucunIndiceNeContreditUnAutreItemDuMemePaquetTest` | repertoire | **E** | L'indice et la reponse existent tous les deux |
| 3 | `AucunItemNeRepeteLEcranDUnAutreItemDuMemePaquetTest` | repertoire | **E** | Identite d'ecran definie pour la forme de saisie |
| 4 | `AucunMotServiNeSEcarteDeSonAccentTest` | repertoire | **E** partiel | Sur ce qui est lu, jamais sur ce qui est compare |
| 5 | `AucunSigneDoubleNEstDetachableDeSonMotTest` | repertoire | **E** partiel | Idem, et le paquet neuf part a zero |
| 6 | `AucunePropositionNEstEgaleAUneAutreDansUnItemTest` | repertoire | **B** | Aucune proposition a comparer |
| 7 | `ChaquePaquetATrousPorteSonTrouSurChaqueItemTest` | repertoire | **E** | Le critere ne lit que l'enonce |
| 8 | `ChaquePaquetPorteUnIndiceTest` | repertoire | **E** | La promesse d'aide vaut en saisie, et davantage |
| 9 | `LaValeurDUnePropositionEstDeriveeDeCeQuElleEcritTest` | repertoire | **B** | Il garde les angles morts du n° 10 |
| 10 | `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` | repertoire | **B** | Il n'y a plus de rang a presser |
| 11 | `LeTexteServiPorteLeCaractereDecideJamaisSaNotationTest` | repertoire | **E** partiel | Clause 1 partout, clause 2 sur le seul texte lu |
| 12 | `UneMatiereNAQuUneSeuleGraphieDansLeContenuServiTest` | repertoire | **E** sans changement | Il lit le descripteur, pas l'item |
| 13 | `UnePartDesigneeApparaitUneSeuleFoisDansSonEnonceTest` | repertoire | **B** | La composante n'existe pas sur l'item de saisie |
| 14 | `ChaqueEntreeDuCatalogueChargeBienSonPaquetTest` | **catalogue** | **E** | Hors des treize ; mesure a l'etat C |

**Sept etendus, six bornes, un etendu sans qu'une ligne change.** Aucune decision muette : chacune a
sa section ci-dessous.

---

## 4. Les motifs, un par garde-fou

### 4.1 `AuPlusTroisPropositionsParItemTest` - **borne**

**Promesse** : un item ne presente jamais plus de trois propositions, pour ne pas tomber dans le repli
a quatre colonnes qui a pousse le bas du cabinet a 492 px sur `cm1-reperes` (`ADR-0063`).

**Motif de la borne** : la promesse porte sur ce que l'enfant **presse** et sur la place que cela prend
a l'ecran. Un item de saisie ne presente aucune proposition ; le repli a quatre colonnes n'a pas
d'objet. Etendre ce garde-fou reviendrait a compter les **formes acceptees** comme si elles etaient
affichees - elles ne le sont jamais - et un paquet honnete comme les complements a 10, qui en declare
deux par item et pourrait en declarer davantage, deviendrait rouge sans qu'aucun enfant n'ait rien vu
de trop. **Ce serait un faux rouge par construction.**

**Ce que la borne doit porter** : le compte d'items ecartes, affirme non vide. Et **les clauses 2, 3 et
4 du cliquet ne bougent pas** : la dette exempte deux paquets nommes, elle ne peut ni grandir, ni
descendre, ni pourrir, et un paquet de saisie n'y entre jamais - il n'a rien a y faire.

### 4.2 `AucunIndiceNeContreditUnAutreItemDuMemePaquetTest` - **etendu**

**Promesse** : aucun indice enoncant une regle generale non temperee n'est dementi par la bonne reponse
d'un autre item du meme paquet. Motif d'origine : le refus de `cm1-conjugaison-present`, *"le jeu se
contredisait devant l'enfant, dans le seul endroit ou il enseigne"*.

**Motif de l'extension** : la propriete met en rapport **un indice** et **une reponse juste**. Un item
de saisie porte les deux - l'indice comme `Question`, et la forme de reference qui est sa bonne
reponse. **Rien dans la propriete ne suppose des boutons.** Un paquet de saisie qui affirmerait une
regle generale dementie par un autre de ses items se contredirait devant l'enfant exactement de la meme
facon.

**Ce que l'extension doit porter** : le compteur d'indices reellement examines - celui que la correction
du 2026-08-12 a introduit - **compte aussi les indices de saisie**. Sans cela le garde-fou resterait
vert sur un paquet de saisie entier sans l'avoir lu, et ce vert-la est precisement celui que cette
correction a achete.

### 4.3 `AucunItemNeRepeteLEcranDUnAutreItemDuMemePaquetTest` - **etendu, et c'est le plus exigeant**

**Promesse** (`ADR-0032`) : deux items d'un meme paquet fini ne presentent jamais le meme ecran, faute
de quoi le tirage sans remise sur les identifiants redevient un tirage **avec** remise sur ce que
l'enfant lit.

**Motif de l'extension** : la propriete ne parle pas de propositions, elle parle de **ce que l'ecran
montre**. Elle vaut mot pour mot en saisie, et la pression qu'elle refuse y est **plus forte** : la
Javadoc de ce garde-fou nomme elle-meme les complements a 10 comme le cas ou l'espace d'items est du
meme ordre que le nombre de manches. C'est notre paquet.

**L'identite d'ecran d'un item de saisie est son enonce seul.** Motif : la forme de reference et les
formes acceptees ne sont pas montrees avant la reponse - la premiere apparait apres, au retour
(`SAI-8`), les secondes ne sont jamais montrees. Deux items de saisie de meme enonce sont donc le meme
ecran, sans exception a inventer.

**Et il y a un vert silencieux ici, que la sonde a revele et que l'avis de faisabilite ne porte pas.**
`verifierQueChaqueComposanteDUnItemEstClassee` promet noir sur blanc qu'*"une composante de plus rend ce
garde-fou rouge tant que personne n'a ecrit si l'ecran la montre"*. Elle tient cette promesse par
reflexion **sur `Question.class` uniquement**. Une composante de plus portee par un **type nouveau**
n'est donc classee par personne, et la clause reste **verte**. Mesure : a l'etat A, cette classe rougit
par transtypage, jamais par cette clause-la ; les composantes de l'item de saisie traversent sans que
rien ne les voie. **C'est la meme famille que l'omission par enumeration de types que le planning nomme
deja pour les pressables d'`adapter-web`** (critere de sortie 3), et c'est un **second** vert
silencieux, a cote de celui qu'`avis-faisabilite.md` avait trouve (section 4.8 ci-dessous).

**Decision** : la clause de classement porte sur **toutes les formes permises par `Item`**, derivees de
la scellure et jamais enumerees a la main. Une troisieme forme d'item - l'operation posee est deja
nommee par `ADR-0058` section 8 - rendra ce garde-fou rouge le jour ou elle entrera, ce qui est
exactement ce que sa Javadoc promet aujourd'hui sans le tenir.

### 4.4 `AucunMotServiNeSEcarteDeSonAccentTest` - **etendu sur le texte lu, borne sur les formes acceptees**

**Promesse** (`ADR-0065`) : aucune chaine **lue par un enfant** ne contient un mot francais qui
prendrait un accent et ne le porte pas.

**Etendu** a l'enonce, a l'indice, a l'explication et **a la forme de reference** : ces quatre chaines
sont lues par l'enfant, la derniere au retour de `SAI-8`. Le defaut d'origine - vingt-cinq indices non
accentues livres sous une CI verte - se reproduirait a l'identique dans un paquet de saisie.

**Borne sur les formes acceptees, et le motif est le coeur du tri** : une forme acceptee n'est pas lue,
elle est **comparee a ce que l'enfant ecrit**. `CPL-3` accepte l'ecriture en lettres du nombre, et
`SAI-1` et `SAI-3` tolerent explicitement l'absence d'accent et la majuscule. Une regle qui exigerait
qu'une forme acceptee porte son accent rendrait **rouge la tolerance elle-meme**, c'est-a-dire
rougirait sur exactement ce que `nadia-prof` a demande. **Un garde-fou qui rougit sur un mot etranger a
son objet est la faute mesuree en s05**, et `ADR-0058` la nomme encore a propos de la liste de
vocabulaire de matiere.

**La borne n'est pas un trou** : les formes acceptees restent gardees par le n° 11, clause 1 - une
notation d'echappement y est un defaut dans tous les cas.

### 4.5 `AucunSigneDoubleNEstDetachableDeSonMotTest` - **etendu sur le texte lu, borne sur les formes acceptees**

**Promesse** (`ADR-0067`, cliquet de `TYP-1`) : aucune espace ordinaire immediatement avant `?`, `!`,
`;` ou `:` dans le texte servi - condition suffisante, dans la donnee, pour qu'aucun signe double ne se
retrouve orphelin en bout de ligne.

**Etendu**, meme partage qu'au 4.4 et pour le meme motif : ce qui est lu est garde, ce qui est compare
ne l'est pas.

**Et cette extension a un cout immediat sur `S24-SAI-2`, que je constate ici plutot que de le decouvrir
au lot 5b.** Le cliquet vaut **en egalite** et *"un paquet absent de la table doit valoir zero - un
paquet neuf n'a aucune dette a heriter"*. Or `cp-complements-10` figure a la table pour **14**
occurrences, et ces quatorze sont les onze enonces `a + ? = 10` - une espace ordinaire devant le `?` -
plus trois indices a deux-points. **Un paquet de saisie qui recopierait ces enonces naitrait avec
quatorze orphelins et serait rouge le jour de son depot.**

**Decision, et elle est de moi parce qu'`ADR-0067` laisse le moyen a `theo-archi` et a moi** : le paquet
de saisie pose **l'espace insecable** devant son `?` et devant ses deux-points. Ce n'est pas une
invention : le depot le fait deja - *"24 enonces posent desormais l'espace insecable devant leur `?`
final"* - et le paquet contre-exemple d'`ADR-0065` porte l'insecable devant ses 25 points. **Ce que
l'enfant lit ne change pas d'un caractere visible**, ce qui met cette decision hors de la porte
pedagogique ; je la porte quand meme a la fiche, parce que `nadia-prof` lit le fichier.

**Ce que je ne fais pas** : toucher aux 14 de `cp-complements-10`. Le paquet existant est audite et sa
dette appartient a `camille-po` pour l'ordre (`ADR-0067`). **Le cliquet ne descend pas tout seul** : un
fichier qui descend sous son compte declare est rouge lui aussi.

### 4.6 `AucunePropositionNEstEgaleAUneAutreDansUnItemTest` - **borne**

**Promesse** : deux propositions d'un meme item ne coincident jamais, sans quoi *"un enfant qui choisit
le doublon n'a rien fait de mal en le choisissant"*.

**Motif de la borne** : la faute que cette regle decrit est un **choix** rendu indecidable. Sans choix,
elle n'a pas d'objet. Sa Javadoc affirme que *"toute mecanique qui presente un choix parmi des
propositions traverse ce garde-fou"* - la saisie n'en presente aucun, et la phrase reste vraie sans
retouche.

**Ce que je signale sans le decider** : *"deux formes acceptees identiques dans un meme item"* est de la
meme famille formelle, mais **ce n'est pas la meme faute** - un doublon dans une liste de tolerances ne
coute rien a l'enfant, il est sans effet sur ce qu'il obtient. Ecrire une regle neuve pour cela sortirait
du mandat du lot 5a, qui est d'etendre ou de borner l'existant. Route en section 6, non engage a s24.

### 4.7 `ChaquePaquetATrousPorteSonTrouSurChaqueItemTest` - **etendu**

**Promesse** : un paquet qui utilise la convention du trou (le marqueur de trois soulignes) sur au moins
un item la tient sur **tous** ses items. Defaut d'origine : douze items livraient une phrase deja
complete, la bonne reponse ecrite en clair, sous une consigne qui demandait de completer.

**Motif de l'extension** : le critere ne lit que **l'enonce**, et son perimetre se derive du paquet
lui-meme - un paquet qui n'emploie jamais le marqueur n'y entre pas. Un paquet de saisie est exactement
le contenu ou la convention du trou est la plus tentante : `CPL-6` demande que l'enfant comprenne que sa
saisie **prend la place du `?`**. Si un auteur ecrit le trou sur un item, la regle doit valoir sur les
onze. **L'extension est gratuite et le perimetre reste auto-derive.**

**Ce que cela n'implique pas** : la forme retenue pour `S24-SAI-2` reste `?`, parce que c'est celle que
`CPL-6` cite. Le choix entre les deux est pedagogique et il appartient a `nadia-prof` ; le garde-fou ne
tranche rien, il exige seulement la coherence de ce qui a ete choisi.

### 4.8 `ChaquePaquetPorteUnIndiceTest` - **etendu, et c'est le plus important du tri**

**Promesse** (`ADR-0016`) : chaque item porte un indice, parce que la mecanique promet une aide et ne
peut afficher que ce que le contenu lui donne. Refus d'origine : dix manches ratees sans qu'aucune aide
n'apparaisse.

**Motif de l'extension** : l'item de saisie porte `indice` et `explication` *"comme sur `Question`"*
(`ADR-0058`, section 1), l'ecran de saisie porte l'indice sous le meuble, et **`CPL-5` n'existerait pas
si l'indice n'etait pas affiche** : `nadia-prof` fait reecrire quatre indices precisement parce qu'ils
seront lus. La promesse est plus forte en saisie que sur des boutons, pas plus faible.

**Ce que l'extension interdit, et il faut l'ecrire** : rendre ce garde-fou vert en declarant
`indicesNommentLaReponse` au niveau du paquet. Le code l'accepterait - M21 est prevue pour cela - et la
mecanique cesserait d'afficher les indices, ce qui **viderait la promesse de S3-2 au lieu de la tenir**
et transformerait `CPL-5` en formalite. **Cette cle n'est pas le remede a `CPL-5` ; la reecriture des
quatre indices l'est.**

**La seule defaillance muette du tri de `theo-archi` se ferme ici.** `itemsDeclares` compte les lignes
commencant par `- enonce:` pour borner ses tirages (`ADR-0064`). Un item de saisie dont le premier champ
serait autre chose ferait rendre **zero** a ce comptage ; la clause `hasSize(declares)` - celle qui
refuse qu'un garde-fou porte sur un echantillon sans le dire - est alors sautee, et le paquet est juge
sur un tirage borne **sans que rien ne le dise**. Un vert silencieux, pas un rouge.

**Decision, en deux parties, parce qu'une seule ne suffit pas :**

1. **L'item de saisie porte `enonce` en premier champ**, comme les autres. C'est la contrainte que ce
   document pose au lot 1, et c'est la moins chere : ma sonde l'applique et le comptage est juste a
   l'etat A. `ADR-0070` mord dans le meme sens - une cle de schema n'est jamais ignoree en silence.
2. **La convention cesse d'etre tenue par la seule discipline.** Un fichier qui porte une liste `items:`
   et dont `itemsDeclares` rend zero est **refuse**, avec le nom du fichier. Aujourd'hui, zero signifie
   indistinctement *"paquet combinatoire, rien a enumerer"* et *"paquet enumere que je n'ai pas su
   lire"* ; seul le premier sens est legitime, et il se reconnait a l'absence de la cle `items:`. **Une
   convention qu'un garde-fou suppose sans la verifier n'est pas une convention, c'est un souhait** -
   c'est mot pour mot ce que `CLAUDE.md` dit d'une regle d'architecture.

### 4.9 `LaValeurDUnePropositionEstDeriveeDeCeQuElleEcritTest` - **borne**

**Promesse** (`ADR-0041`) : garder les deux angles morts de la lecture de valeur du n° 10 - l'exclusion
silencieuse d'items hors du domaine, et le reste non consomme qui rend un ordre faux.

**Motif de la borne** : ce garde-fou est **entierement au service du n° 10** ; il ne juge rien d'autre
que la fiabilite de son taux de rang. Le n° 10 etant borne (4.10), celui-ci l'est par la meme raison, et
l'etendre serait le laisser garder un calcul que plus personne ne fait.

**Sa table `PAQUETS_A_ITEMS_ECARTES` est un constat exact, dans les deux sens**, et un paquet de saisie
n'y entre pas : il n'est pas *melange*, il est **hors du domaine en entier**, cas que la conception
d'`ADR-0041` traite deja - *"sur un paquet entierement hors du domaine, aucun taux n'est calcule et rien
n'est affirme."* La borne n'ajoute donc aucun mecanisme : elle nomme un cas deja prevu.

### 4.10 `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` - **borne, et c'est la borne la mieux payee**

**Promesse** : une strategie qui presse toujours le meme rang, sans lire l'enonce, ne gagne pas plus de
la moitie des manches d'un paquet. Refus d'origine : 64,6 % de reussite sans lire, sur les 99 items des
tables de multiplication.

**Motif de la borne** : la propriete decrit **une strategie de pression**. En saisie il n'y a ni rang, ni
bouton, ni ordre a exploiter. Et la garantie que ce garde-fou achete n'est pas perdue - elle est obtenue
**par construction**, et c'est `nadia-prof` qui l'a chiffree, pas moi : *"un enfant qui ne sait rien
obtient aujourd'hui 7 manches sur 7. En saisie, avec trois essais sur onze reponses possibles, il en
obtient au plus trois sur onze par manche. La saisie est la premiere mecanique de ce produit ou un
enfant qui ne sait pas peut echouer."* **Borner ce garde-fou ne relache rien ; c'est la mecanique qui
rend son objet sans objet.**

**Ce qui ne bouge pas** : sa dette exemptee (`cm1-perimetre`, `cm1-mesures`) et son plafond restent
intacts. Un paquet de saisie n'y entre pas et ne les fait pas descendre.

### 4.11 `LeTexteServiPorteLeCaractereDecideJamaisSaNotationTest` - **etendu, avec ses deux clauses separees**

**Promesse** (`ADR-0060`), en deux clauses : **1** aucun texte servi ne porte la notation d'un caractere
a la place du caractere (contre-barre, entite de caractere HTML, notation de point de code) ; **2** le
caractere decide est present la ou une decision pedagogique l'exige (insecables a l'interieur des
guillemets francais, `SYN-9`, `ANT-5`).

**Clause 1 - etendue a tout, formes acceptees comprises.** Motif : une notation d'echappement dans une
forme acceptee ne se compare a **rien de ce qu'un enfant peut taper** - il faudrait qu'il ecrive les six
caracteres ASCII de la notation. **La tolerance ne se declenche alors jamais, et personne ne le voit** :
c'est une tolerance morte, exactement le mode de defaillance que ce garde-fou existe pour rendre
visible. C'est aussi ce qui fait que la borne du 4.4 n'est pas un trou.

**Clause 2 - bornee aux textes lus.** Motif : *"le caractere decide"* renvoie a des decisions de
**rendu** - ce qui doit ou ne doit pas se couper a l'ecran. Une forme acceptee ne s'affiche pas ; il n'y
a aucun caractere decide a y exiger.

**Point de methode** : `textesServis` de cette classe est la source de perimetre du 4.4. **Les deux
extensions sont donc un seul geste de code et deux decisions distinctes.** Ce qui est partage est
l'inventaire des chaines ; ce qui ne l'est pas est ce que chaque garde-fou en exige - et c'est
precisement pour cela que l'inventaire doit rendre **quelle chaine vient de quel champ**, sans quoi les
deux decisions ci-dessus ne sont pas exprimables.

### 4.12 `UneMatiereNAQuUneSeuleGraphieDansLeContenuServiTest` - **etendu, sans qu'une ligne change**

**Promesse** (`ADR-0049`) : une matiere s'ecrit d'une seule facon dans tout le contenu servi.

**Motif** : ce garde-fou lit le **descripteur** par le chargeur reel, jamais les items. Tout paquet en
porte un, quel que soit son type. **Il est deja etendu, et il l'etait avant ce document.** Mesure : etat
A vert, etat B rouge - il mord sur un paquet de saisie exactement comme sur un paquet de choix, des que
la graphie diverge.

**La seule consequence pour `S24-SAI-2`** est une consigne d'auteur, pas une consigne de code : le paquet
ecrit sa matiere accentuee, comme les vingt-sept autres. **C'est le garde-fou le moins cher des
quatorze, et il est dans la liste des treize pour une raison qui n'est pas la sienne** (section 1.3).

### 4.13 `UnePartDesigneeApparaitUneSeuleFoisDansSonEnonceTest` - **borne**

**Promesse** (`ADR-0062`) : pour chaque item qui **declare** une part designee, cette valeur apparait
exactement une fois dans son enonce.

**Motif de la borne** : `ReponseAEcrire` ne porte pas `partDesignee` (`ADR-0058`, section 1). Le critere
n'a pas d'objet : il ne s'agit pas d'une exemption, mais d'une **composante absente**. Le garde-fou se
derive deja de la declaration - *"aucun item declarant nulle part ne rend cette clause vraie a vide"* -
donc la borne est portee par sa non-vacuite existante, jamais par un `instanceof` de plus.

**Ce que la borne doit garantir en plus** : que l'ajout futur de `partDesignee` a l'item de saisie ne
passe pas en silence. C'est exactement ce que la decision du 4.3 achete - le classement des composantes
sur **toutes** les formes de `Item` - et c'est pourquoi ces deux lignes se tiennent l'une l'autre.

### 4.14 `ChaqueEntreeDuCatalogueChargeBienSonPaquetTest` - **etendu** (hors des treize)

**Promesse** : pour chaque entree du catalogue, les deux chemins de chargement du chargeur reel -
`games()` en bloc et `chargerPaquet(id)` isolement - rendent les memes items.

**Motif de l'extension** : la propriete ne parle ni de propositions, ni de texte lu ; elle parle de
**deux chemins de code qui doivent coincider**. Elle vaut pour tout type de contenu et elle vaut
**davantage** au moment ou une seconde fabrique entre dans la table du chargeur - c'est exactement
l'endroit ou deux chemins peuvent diverger.

**Pourquoi il n'est pas dans les treize** : il se declenche a l'entree de catalogue, pas au depot du
fichier (etat C contre etat A). **Il est donc du perimetre du lot 5b**, qui depose cette entree, et le
planning ne le compte pas. Je le compte ici.

---

## 5. Ce que ce tri coute au lot 5b, en clair

1. **Un classement d'item ecrit une fois**, et les garde-fous qui declarent leur domaine par rapport a
   lui, chacun avec son motif dans sa Javadoc (n° 1, 6, 9, 10, 13, plus la moitie bornee de 4, 5 et 11).
2. **Un inventaire de textes servis qui dit de quel champ vient chaque chaine** (n° 4, 5, 11).
3. **Deux verts silencieux fermes** : le classement des composantes sur toutes les formes de `Item`
   (n° 3), et le refus d'un `items:` compte a zero (n° 8).
4. **Une extension gratuite** (n° 7), **une extension deja acquise** (n° 12), **un garde-fou de
   catalogue** que le lot 5 ne comptait pas (n° 14).
5. **Une contrainte posee au lot 1** : `enonce` en premier champ de l'item de saisie.
6. **Deux contraintes posees au paquet `S24-SAI-2`** : espaces insecables devant `?` et `:`, matiere
   accentuee comme partout ailleurs.

**Ce qui reste vrai apres ce tri et qu'il ne faut pas croire ferme** : ces quatorze decisions sont
ecrites, elles ne sont pas executees. **Le lot 5a est un document ; seul le lot 5b rend les garde-fous
verts ou bornes dans le code**, et le critere de sortie 4 du sprint (`planning.md`, section 10) porte
sur le second, pas sur le premier.

---

## 5 bis. Amendement du 2026-08-31, apres execution du lot 5b : quatorze ne suffit pas

**Ce que l'execution a mesure et que la section 1 n'avait pas vu.** Le lot 1 fusionne et le paquet
depose, `mvn -B clean verify` a la racine rend **deux garde-fous de plus**, et tous les deux vivent
dans `bootstrap` - hors du perimetre `adapter-content` sur lequel les deux sondes avaient ete
mesurees. Ils ne se declenchent pas au depot du fichier, mais a l'**entree de catalogue**, comme le
n° 14.

| Garde-fou | Module | Ce qu'il exige |
|---|---|---|
| `TexteAccentueExposeParLePortEntrantTest` | `bootstrap` | Toute chaine exposee par le port entrant est inventoriee. Message du garde-fou : *"soit `nadia-prof` l'a arbitree et l'inventaire s'etend dans le meme commit, soit cette chaine n'aurait pas du entrer"* |
| `LaConsigneDUnJeuServiAtteintLEcranDeMancheTest` | `bootstrap` | La consigne et l'enonce du jeu declare atteignent son **ecran de manche rendu** |

**Ce que cela change au sequencement, et c'est un fait, pas un arbitrage.** Le second exige un
**ecran** : il n'y en a pas tant que les lots 2 et 4 n'ont pas livre la mecanique et son fragment.
**L'entree de catalogue de `S24-SAI-2` ne peut donc pas etre verte avant les lots 2 et 4**, alors que
le **paquet dans `contenu/` ne depend que du lot 1**. Les deux etaient comptes comme un seul geste
par le planning (*"lot 5b et `S24-SAI-2` en une seule fusion"*).

**Ce que j'en fais, et c'est du sequencement, donc de mon ressort de developpeuse** : je livre le
paquet dans `contenu/` avec les quatorze garde-fous verts, et **je retiens l'entree de catalogue**
jusqu'a ce que les lots 2 et 4 soient la. Mesure a l'appui, sur l'arbre de ma branche : avec l'entree
de catalogue, `bootstrap` rend 123 tests et **4 echecs** ; sans elle, **0**.

**Ce que je route et ne tranche pas** : le titre et la consigne de l'entree de catalogue - *"Ecris le
complement a 10"* et *"Ecris le nombre qui manque pour arriver a 10."* - sont **du texte neuf lu par
un enfant**. `CPL-1` a `CPL-8` gouvernent le paquet, aucune ne gouverne l'entree de catalogue. Ce sont
deux chaines a porter a la fiche pour l'audit de `nadia-prof`, et le garde-fou de `bootstrap` le dit
lui-meme. Voir la ligne F de la section 6.

---

## 6. Ce que ce document route et ne tranche pas

| # | Objet | Categorie | Destinataire |
|---|---|---|---|
| A | **Le lot 5 compte quatorze garde-fous, pas treize** : `ChaqueEntreeDuCatalogueChargeBienSonPaquetTest` se declenche a l'entree de catalogue que `S24-SAI-2` depose. Mesure : etat C, section 1.2 | Technique, perimetre d'un lot | `theo-archi` pour constat ; `sofia-sm` casquette Scrum Master pour la capacite |
| B | **`verifierQueChaqueComposanteDUnItemEstClassee` ne tient pas sa promesse pour une composante d'un type neuf** (section 4.3). Le lot 5b la corrige ; le fait merite d'etre nomme a la review, car c'est un garde-fou qui affirme plus qu'il ne verifie | Qualite | `yanis-qa` ; `theo-archi` si la correction touche une regle |
| C | *"Deux formes acceptees identiques dans un meme item"* - meme famille formelle que le n° 6, faute differente. Candidat a une regle neuve, **non engage a s24** | Technique | `theo-archi` |
| D | L'espace insecable devant `?` et `:` du paquet de saisie : moyen laisse ouvert par `ADR-0067`, decide ici, **porte a la fiche** parce que `nadia-prof` lit le fichier meme si l'enfant ne voit aucune difference | Information, aucune demande d'arbitrage | `nadia-prof` |
| E | Le choix entre `?` et le marqueur de trou dans l'enonce d'un item de saisie (n° 7) reste pedagogique. `CPL-6` cite `?` et je m'y tiens | Pedagogie | `nadia-prof`, si elle veut le rouvrir |
| F | **Le titre et la consigne de l'entree de catalogue de `S24-SAI-2` sont du texte neuf lu par un enfant, et aucune condition `CPL` ne les gouverne.** `TexteAccentueExposeParLePortEntrantTest` exige qu'une chaine exposee par le port entrant ait ete arbitree avant d'entrer dans son inventaire (section 5 bis) | Pedagogie | `nadia-prof`, a la fiche de `S24-SAI-2` |
| G | **L'entree de catalogue de `S24-SAI-2` depend des lots 2 et 4, pas seulement du lot 1**, ce que l'ordre de fusion du planning ne prevoit pas (section 5 bis). Le paquet est livre sans elle | Sequencement, capacite | `sofia-sm` casquette Scrum Master |

**Aucun obstacle n'est clos par ce document**, et aucun ne le sera sur une intention annoncee : le
fichier `ADR` ou `DEC` qui tranche existe sur le disque, ou l'obstacle reste ouvert.
