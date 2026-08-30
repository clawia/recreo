# Avis de faisabilite - sprint 23

- **Auteur** : `theo-archi`
- **Date** : 2026-08-30
- **Branche** : `s23-theo`, worktree dedie, depuis `round-two` a `890732f`
- **Ce document ne prononce aucun Done, ne repriorise rien, et ne decide rien de produit ni de
  pedagogique.**

---

## 0. Porte technique - le resultat de la commande, pas une impression

```
bash outils/mvn-jdk21.sh -B clean verify
```

Reacteur complet, JDK de la toolchain **21.0.10**. **BUILD SUCCESS.**

**Methode du compte de tests** : somme des attributs `tests` des rapports `TEST-*.xml` de surefire et
de failsafe, tous modules, apres un `clean verify` a la racine - **119 rapports, 772 tests, 0 echec,
0 erreur, 0 ignore**. Jamais lu dans les rapports texte : `LesReglesMordentTest` est execute par deux
moteurs JUnit et le rapport texte n'en garde qu'un.

**772 contre 761 a la cloture de s22, soit +11**, et les onze se rattachent nommement : 3 clauses
pour `ADR-0065`, 3 pour `ADR-0066`, 5 pour `ADR-0067`. Aucun test existant n'a change de verdict.

**Note d'environnement, non commitee ailleurs et sans valeur portable** : le poste sur lequel j'ai
mesure resout ses artefacts par un miroir d'entreprise injoignable ; j'ai lance Maven avec un fichier
de reglages temporaire pointant le depot central public. La CI (`actions/setup-java`, `mvn -B verify`)
n'a pas ce probleme, et aucune de mes mesures ne depend de ce contournement.

---

## 1. Le garde-fou d'accentuation - **faisable**, ecrit, et voici ce qu'il ne verra pas

### 1.1 La reponse courte

**Faisable, et fait.** `ADR-0065`, `AucunMotServiNeSEcarteDeSonAccentTest`, 3 clauses, vert sur le
contenu servi actuel et le catalogue, rouge sur le defaut reel de s22.

**Mecanisme** : un **lexique morphologique du francais exterieur au depot**
(`org.languagetool:french-pos-dict:0.7`, CC BY-SA 4.0, 1,4 Mio, **portee test**, jamais dans le fat
jar). Un mot servi est fautif quand il **n'est pas une forme du francais** et qu'une autre facon
d'accentuer les memes lettres, elle, **en est une**. Le critere porte sur le squelette du mot - ses
lettres, tout diacritique retire - ce qui couvre l'accent manquant **et** l'accent errone pour le
meme prix.

**Cout** : une dependance tierce de plus, en portee test, et 0,95 s de chargement par execution
(503 915 formes flechies indexees une fois). Zero ligne de production.

### 1.2 Pourquoi le lexique ne pouvait pas venir du depot - le chiffre qui a tranche

J'ai d'abord teste la voie gratuite : comparer les paquets entre eux (une forme non accentuee dont
une soeur accentuee existe ailleurs dans le contenu servi). Elle detecte "ecrire", "zero" et "zeros"
sur l'arbre de `be63ce3^`. Elle aurait donc **passe l'examen que je lui aurais fait passer**. Elle
est pourtant a jeter, et la mesure le dit :

- **213 des 274 formes accentuees distinctes du contenu servi, soit 78 pour cent, n'apparaissent que
  dans un seul paquet.** Un critere derive du corpus est donc aveugle au vocabulaire neuf -
  c'est-a-dire au vocabulaire d'un paquet neuf, les quatre a cinq par sprint que cet avis doit rendre
  possibles. C'est `ADR-0045` en principe, et c'est ici un chiffre.
- **Sur le defaut reel, la detection tenait au hasard** : la forme accentuee de "zeros" n'existait que
  dans `cm1-grands-nombres`, celle de "ecrire" que dans `cm1-comprehension`, un paquet livre le meme
  sprint. Retirer l'un des deux du catalogue rendait le critere muet.

*Methode de ces chiffres* : les 24 paquets de `adapters/adapter-content/src/main/resources/contenu/`,
champs servis a l'enfant uniquement, charges par le chargeur reel, chaque paquet enumere lu en entier
(`ADR-0064`) ; arbre `be63ce3^` pour la mesure qui inclut le paquet fautif, arbre du 2026-08-30 pour
les autres.

### 1.3 **Ce que le garde-fou ne verra pas** - la section que `nadia-prof` doit lire avant de compter dessus

**1. La classe des homographes, et elle est grosse.** Le critere ne peut rien dire d'un mot qui, sans
son accent, est **lui aussi** un mot francais : "a" et "a accent grave", "ou" et "ou accent grave",
"la", "des", "sur", "du", "cote" et ses trois soeurs, et tous les couples present/participe passe
("il termine" / "il a termine"). Trancher demande la syntaxe de la phrase, qu'aucun lexique ne porte.

> **Mesure : 232 des 1 331 mots distincts du contenu servi, soit 17 pour cent, tombent dans cette
> classe.** Elle contient la faute d'accentuation la plus frequente du francais, et **elle contient
> le quatrieme mot du defaut de s22**. Sur les vingt-cinq indices fautifs, le garde-fou aurait nomme
> "ecrire", "zero" et "zeros" - **jamais le "a droite"**.

Ce trou n'est pas seulement ecrit : il est **grave dans un test**
(`laFauteDAccentSurUnMotQuiExisteSansAccentResteInvisible`), qui s'execute sur l'indice reel de
`be63ce3^` et exige exactement deux defauts nommes, aucun portant sur "a". Il echoue le jour ou
quelqu'un croirait avoir ferme ce trou sans l'avoir ferme.

**2. Un mot absent du lexique et sans soeur accentuee** n'est pas juge : prenoms inventes,
onomatopees, fragments d'elision. 13 formes distinctes aujourd'hui ("kofi", "malo", "elio", les deux
moities de "aujourd'hui", les terminaisons verbales servies telles quelles par les paquets de
conjugaison). Aucune n'est fautive, mais rien ici ne le prouve.

**3. La couverture du lexique est un pari.** Un mot francais rare absent du lexique, dont le
squelette porterait une soeur accentuee, produirait un **faux rouge**. Mesure : zero sur les
1 331 mots distincts d'aujourd'hui. Le mode de defaillance est bruyant, jamais silencieux.

**4. Le texte pose par un gabarit** reste au perimetre de `TexteAccentueDesGabaritsTest`, inchange.

### 1.4 La morsure est prouvee sur du materiau historique, pas sur une fixture

Le contre-exemple n'est pas de ma main : c'est
`cm1-multiplications-divisions-10-100.yaml` **tel qu'il etait a `be63ce3^`**, l'etat exact de l'arbre
la veille du refus de `nadia-prof`, commite sous
`contreexemple/AucunMotServiNeSEcarteDeSonAccentTest/accent-manquant/`. Le test exige le rouge en
nommant "ecrire", "zero" et "zeros".

### 1.5 Deux defauts reels trouves en chemin, et signales plutot que tus

**(a) Le perimetre du texte servi avait cesse de suivre son objet.**
`LeTexteServiPorteLeCaractereDecideJamaisSaNotationTest` (`ADR-0060`), sur lequel je m'appuie, ne
ramassait **ni `texteSupport` ni `partDesignee`** - le premier est le plus long texte servi du depot,
ajoute par `ADR-0050` a s22, le second est le fragment mis en evidence par `ADR-0062`. Les deux
traversaient **les deux clauses** de ce garde-fou sans etre lus. Perimetre elargi dans ce commit.

**(b) L'elargissement a immediatement trouve un defaut de production livre vert a s22.**
`cm1-comprehension.yaml`, item *"Que contient probablement ce carton ?"* : les guillemets francais de
son texte-support encadraient une **espace ordinaire** au lieu de l'insecable qu'exigent `SYN-9`,
`ANT-5` et `ADR-0060` clause 2. Corrige (deux caracteres, aucune lettre changee). **Signale a
`nadia-prof` et a `sofia-sm`** : c'est un caractere decide par une condition pedagogique deja rendue,
pas un arbitrage neuf, et il ne change rien a ce que l'enfant lit.

**(c) Et un troisieme, trouve par le rouge de (b).**
`LeTexteSupportDUnItemReelAtteintLEcranDeMancheTest` (`ADR-0050`, decision 6) promet *"exactement son
texte"* et lisait l'ecran par `Element.text()` de jsoup, **qui normalise les blancs et remplace
l'espace insecable par une espace ordinaire**. Ce garde-fou ne pouvait donc pas voir le seul
caractere que sa promesse rendait critique. Corrige en `wholeText()`.

> **A verser au dossier de `yanis-qa`, et je ne le traite pas ici** : `.text()` apparait
> **66 fois** dans les tests d'`adapter-web` et de `bootstrap` (methode : `grep -rn "\.text()"` sur
> `src/test`, `target/` exclu). Toutes ne comparent pas un texte exact, mais **chacune de celles qui
> le font est aveugle a l'espace insecable**. C'est la meme famille que le defaut ci-dessus, et cela
> touche directement le moyen de `TYP-1` (section 4.1).

---

## 2. Coherence charte / `DEC` / `ADR` - **partiellement mecanisable**, et je nomme le reste

### 2.1 Ce que j'ai mesure avant de proposer, parce que deux mecanismes evidents se sont reveles vides

- **Les neuf chartes d'agent ne citaient collectivement que six identifiants de decision** avant s22 :
  `camille-po` deux, `ines-design` un, `theo-archi` un, `yanis-qa` un, `nadia-prof` un ; `lea-dev`,
  `marc-dev` et `sofia-sm` **aucun**.
- **Une seule decision de tout `squad/decisions/` nomme une charte par son chemin** : `DEC-0056`,
  ecrite la veille.

**Le lien qu'un garde-fou de coherence devrait verifier n'existe pas encore dans les artefacts.**
Toute regle qui l'aurait suppose aurait ete verte en n'examinant presque rien.

### 2.2 Ce qui est mecanisable, et qui est ecrit - `ADR-0066`

**Clause 1 - aucune reference de decision ne pointe vers rien.** C'est la famille du defaut du
2026-08-17 : `CLAUDE.md`, le fichier que tous les agents lisent, a renvoye deux jours vers
"`ADR-0045`, section Prerequis de mesure", qui n'existait pas. *Methode du perimetre* : tout fichier
texte sous la racine derivee du pom agregateur, hors `.git`, hors `target`, hors arbres de
contre-exemple, binaires ecartes a l'octet nul et non a l'extension (`ADR-0036`) - **1 445 fichiers,
121 identifiants distincts, 11 196 occurrences**. Le tissu de references de ce depot n'etait verifie
par rien.

**Clause 2 - une charte nommee dans la section `## Impact` d'une decision cite cette decision.**
C'est la cause exacte du cas de s22, dans les mots de `DEC-0056` : *"un texte de decision qui n'a
jamais ete reporte dans les deux fiches qu'il concernait"*.

**La regle a mordu son auteur avant quiconque** : rouge a sa premiere execution, en nommant
`ADR-0065` et `ADR-0066`, cites par les sources du meme sprint et pas encore ecrits.

**Et elle a trouve un defaut de son propre critere dans ce meme rouge** : `ADR-0066` *mentionne* deux
chartes en rapportant le contexte de `DEC-0056`, sans rien decider a leur sujet, et la clause 2 le
declarait fautif - **un perimetre qui echoue par inclusion**, la faute que j'ai moi-meme nommee a
s22. D'ou la restriction a la section `## Impact`, seule marque de ce depot qui distingue statuer de
mentionner.

### 2.3 Ce qui n'est **pas** mecanisable, et que je refuse d'encoder mal

1. **La contradiction en langue naturelle.** Une charte qui cite `DEC-0002` et dit exactement le
   contraire est verte. C'est le coeur de la demande de `sofia-sm`, et aucun automate de ce depot ne
   le fera. Une regle qui pretendrait le faire donnerait un vert qui ne prouve rien.
2. **Une decision qui statue sur une charte sans nommer son chemin** dans son Impact. Le nom d'un
   agent apparait dans presque toutes les decisions ; son chemin n'apparait que quand on statue sur
   sa charte, et c'est la seule ancre non ambigue disponible.
3. **Une decision sans section `## Impact`** : **63 des 122 decisions** n'en portent pas, presque
   toutes des `ADR` (59 sur 64). La clause 2 ne s'exerce donc aujourd'hui que sur **2 paires**. Je
   l'ecris plutot que de le gommer, et `ADR-0066` porte le declencheur de sa propre suppression : si
   elle en est encore la dans trois sprints, elle sera a retirer.
4. **Une reference vers une section inexistante d'un `ADR` qui, lui, existe** - la moitie du defaut
   du 2026-08-17 que la clause 1 n'attrape pas.

**Ce que je propose a `sofia-sm`, sans le decider** : la seule chose qui rendrait la clause 2 utile
est une convention de redaction - *une decision qui statue sur une charte la nomme par son chemin
dans son Impact*. C'est un choix de process, pas d'architecture.

---

## 3. Cout technique de la mecanique de saisie, si elle etait decidee

**Je ne demande pas son ouverture et je n'en decide pas.** Ce qui suit est un chiffrage, pose
maintenant pour ne pas etre a faire dans l'urgence a s24.

### 3.1 La conception est faite. Ce qui reste est du code, pas de la decision

`ADR-0058` et ses trois amendements ont tranche : la forme d'item (`ReponseAEcrire`, `Item permits`),
le mode strict declare par le contenu, la normalisation possedee par la mecanique, l'interdiction
qu'un contrat porte du comportement, le voyage de la chaine sur le verdict (`SAI-2c`). `SAI-9` a ete
tranchee le 2026-08-24 en lecture (b), et l'amendement que je m'engageais a ecrire n'est pas du.

**Rien de comparable a `ADR-0050`/`texteSupport` ne reste a concevoir.** Trois regles restent
**dues avec le lot de code** et sont rouges ou inexistantes aujourd'hui : `R-A` (l'adaptateur ne
nomme aucune mecanique hors de son point de descente), `R-B` (aucune distance entre chaines,
gardable par un test unitaire et non par ArchUnit), `R-D` (la chaine voyage sur le verdict, et la
manche a deja tourne).

### 3.2 Modules touches : **4 sur 5**

| Module | Ce qu'il recoit |
|---|---|
| `domain` | `ReponseAEcrire`, `Item permits` etendu, paquet `mechanics/saisie` (session, verdict propre, normalisation), tests de mecanique dont `R-B` et `R-D` |
| `adapters/adapter-content` | une fabrique de plus dans `FABRIQUES`, la lecture de schema du mode strict, un paquet YAML |
| `adapters/adapter-web` | retypage d'`EtatDePartie`, `EtatDePartieEnSession`, `PartieController` ; un ecran de saisie ; le formulaire |
| `bootstrap` | une entree de catalogue, les tests d'integration de bout en bout |
| `application` | **rien** - `Move` et `GameSession` ne bougent pas (`ADR-0058`, section 5) |

**Ordre de grandeur, derive du seul precedent** : `bandit-manchot` pese **4 fichiers, 346 lignes** en
production et **693 lignes** de test dans `domain`. La saisie sera du meme ordre, `R-B` et `R-D` en
plus.

**`R-A`, mesure aujourd'hui et non recopiee** : `adapters/adapter-web` cite
`com.recreo.domain.mechanics` sur **10 lignes, 5 en `src/main` (3 fichiers) et 5 en `src/test`
(3 fichiers)** - methode : `grep -rn "domain\.mechanics" --include=*.java`, `target/` exclu, arbre du
2026-08-30. *`ADR-0058` annoncait "20 occurrences dans 3 fichiers" a s19 ; les deux comptes ne
comptent pas la meme chose et le mien porte sa methode.*

### 3.3 **Le vrai cout n'est pas la ou on l'attend : onze garde-fous de contenu deviennent rouges, et pas d'un rouge lisible**

**Dix garde-fous derivent leur perimetre du repertoire `contenu/` et font `(Question) item`.** Le
jour ou un paquet de type `saisie` apparait dans ce repertoire, ils ne rougissent pas : ils
**plantent** sur une `ClassCastException`.

```
AucunIndiceNeContreditUnAutreItemDuMemePaquetTest      AuPlusTroisPropositionsParItemTest
AucunItemNeRepeteLEcranDUnAutreItemDuMemePaquetTest    ChaquePaquetATrousPorteSonTrouSurChaqueItemTest
AucunePropositionNEstEgaleAUneAutreDansUnItemTest      ChaquePaquetExpliqueSesItemsAVariationTest
ChaquePaquetPorteUnIndiceTest                          LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest
LeTexteServiPorteLeCaractereDecideJamaisSaNotationTest UnePartDesigneeApparaitUneSeuleFoisDansSonEnonceTest
```

*Methode* : intersection des sources de test qui contiennent `(Question)` et qui derivent leur
perimetre du repertoire (`paquets(` ou `resolve("contenu")`), `target/` exclu, arbre du 2026-08-30.

**Le onzieme est le mien** : `AucunMotServiNeSEcarteDeSonAccentTest` passe par `textesServis` et herite
du meme plantage. Deux autres derivent du repertoire sans caster
(`LaValeurDUnePropositionEstDeriveeDeCeQuElleEcritTest`,
`UneMatiereNAQuUneSeuleGraphieDansLeContenuServiTest`) et sont a relire, pas forcement a changer.

**Ce que cela coute reellement, et c'est plus qu'un cast a corriger** : pour chacun de ces onze, il
faut **decider par ecrit** si sa propriete a un sens sur un item a reponse ecrite. Trois exemples qui
ne se traitent pas pareil :

- `AuPlusTroisPropositionsParItemTest` (`ADR-0063`) n'a **aucun objet** sur un item de saisie -
  `ADR-0051` exige alors qu'il le declare, pas qu'il se taise ;
- `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` n'a pas de rang a juger, meme conclusion ;
- `ChaquePaquetPorteUnIndiceTest` et `AucunIndiceNeContreditUnAutreItemDuMemePaquetTest` gardent tout
  leur sens : un item de saisie porte un indice.

**Chiffre a retenir pour le planning : le lot d'ouverture de la saisie porte onze decisions de
perimetre de garde-fou en plus de son code**, et aucune n'est mecanique.

### 3.4 Deux garde-fous a etendre, sans decision a prendre

- `TexteAccentueExposeParLePortEntrantTest` : son inventaire s'etend de l'entree de catalogue neuve,
  comme a chaque jeu.
- `ToutesLesMecaniquesSontTesteesTest` derive de `com.recreo.domain.mechanics` : il exigera un test
  pour la mecanique neuve, ce qui est l'effet voulu.

### 3.5 Un fait neuf pour `SAI-6`, `SAI-7` et `SAI-2b`, et je le signale sans en tirer de conclusion

`ADR-0058` ecrit que ces trois conditions ne sont gardees par rien parce que *"il faudrait un
dictionnaire, et aucun n'entre dans ce depot"*. **Depuis `ADR-0065`, un lexique du francais entre
dans ce depot, en portee test.**

Ce que cela change, exactement, et rien de plus : il devient possible de verifier qu'une forme
declaree acceptable **est un mot francais**, et que la faute visee par un mode strict **en est un
aussi** (`SAI-2b`). Ce que cela ne change pas : rien ne dira jamais qu'une forme **manque** a la
liste d'un mot, ni qu'elle est **pedagogiquement** a sa place. `nadia-prof` reste la seule garde du
bareme, comme `ADR-0058` l'ecrit. **La phrase d'`ADR-0058` est datee et devra etre amendee le jour ou
la saisie s'ouvre - pas avant, et pas par ce document.**

---

## 4. Dettes ouvertes a la cloture de s22 - ce que je prends, ce que je reporte

### 4.1 `TYP-1`, le moyen - **PRIS pour moitie**, et la moitie conjointe reste ouverte

`nadia-prof` confie le moyen a `theo-archi` **et** `sofia-sm`. Je ne tranche donc pas seul le
caractere, et j'ai pris ce qui m'appartient : `ADR-0067` decide que **le moyen vit dans la donnee
servie et jamais dans le rendu** (motif deja juge : `ADR-0060`, clause 2, qui exige deja l'insecable
dans la donnee), et pose un **cliquet exact** qui arrete la croissance de la dette.

**Le cliquet est agnostique au caractere, delibere** : son critere est l'absence d'espace
**ordinaire** devant un signe double, si bien que U+00A0, l'insecable etroite U+202F ou le retrait
pur et simple de l'espace le rendent vert de la meme facon. **Le choix reste entier pour `sofia-sm`
et moi, et il peut etre pris apres coup sans rien reecrire.**

**Ce qu'il juge** : non pas `TYP-1`, qui porte sur une ligne rendue a un viewport, mais sa
**condition suffisante dans la donnee**. Zero espace ordinaire devant un signe double implique zero
orphelin **a tout viewport** - ce qui couvre au passage les 360 px que le releve de `nadia-prof` ne
couvrait pas.

**La dette, avec sa methode** : texte servi par le chargeur reel (enonce, bonne reponse,
distracteurs, indice, explication, texte support, part designee), chaque paquet enumere lu en entier,
plus les seuls `titre` et `consigne` de `catalogue.yaml` - **517 occurrences dans 16 paquets,
23 dans le catalogue, 540 au total, 17 fichiers**. *Ce n'est pas les 511 de `nadia-prof` : elle
lisait les fichiers, commentaires compris, ce cliquet lit ce que l'enfant recoit. Les deux sont
exacts, ils ne comptent pas la meme chose.*

**Ce que le cliquet ne fait pas, et je l'ecris sans le gommer** : les 540 occurrences restent,
18 orphelins sont visibles a l'ecran aujourd'hui, et geler une dette n'est pas la traiter. Les lots 1,
2 et 3 de `nadia-prof` restent entiers, et leur ordre appartient a `camille-po`.

**Ce que je route** :
- a **`sofia-sm`** : le choix du caractere, avec le fait mesure qui doit l'eclairer - la section 1.5
  etablit que `Element.text()` de jsoup est **aveugle a l'espace insecable**, et qu'il est employe
  66 fois dans les tests de rendu. Le moyen retenu doit etre verifiable par les tests qui existent ;
- a **`ines-design`** et **`nadia-prof`** : U+202F est la forme typographique francaise correcte
  devant un signe double, mais sa restitution depend des polices servies. C'est une mesure de rendu,
  pas une preference d'architecte. **Je ne l'ai pas mesuree.**

### 4.2 Obstacle 21 - 4 items sans levier connu - **REPORTE**, et ce n'est pas ma dette

Les quatre items (`cm1-grands-nombres` x2, `cm1-reperes` x2) sont sans levier **pedagogique** connu.
Rien dans l'architecture ne les bloque et rien dans l'architecture ne les debloquera.

**Declencheur ecrit** : le premier des quatre pour lequel `nadia-prof` ou `camille-po` nomme un
levier - une forme d'item, une mecanique, une reformulation. Ce jour-la je chiffre le cout technique
du levier nomme, dans la journee. **Sans levier nomme, il n'y a rien a chiffrer, et un avis de
faisabilite sur un probleme non pose serait du remplissage.**

### 4.3 Obstacle 25 residuel - cinq scripts executes a l'import - **REPORTE**, declencheur inchange

Cinq scripts de `nadia-prof` sont executes a l'import et ne sont testes par aucun juge pur. Le
declencheur ecrit a s22 tient tel quel : **le premier de ces cinq scripts qui recoit un juge pur
execute par l'etape CI** (`ADR-0025`). Je n'ai pas de fait neuf a verser depuis s22, et je ne le
prends pas a s23 : les trois lots de ce document consomment deja le sprint.

**Ce que je n'ai pas fait et que je nomme plutot que de le laisser croire** : je n'ai pas re-verifie
que les cinq scripts sont toujours cinq. Le chiffre est celui de s22, repris tel quel.

---

## 5. Ce que je route, en une ligne chacun

| A qui | Quoi |
|---|---|
| `nadia-prof` | La classe des homographes reste a la main : **17 pour cent du vocabulaire servi**, dont la faute la plus frequente du francais. Sa borne de capacite doit etre posee en le sachant (section 1.3) |
| `nadia-prof` | Un guillemet francais a espace ordinaire corrige dans `cm1-comprehension.yaml` : caractere decide par `SYN-9`/`ANT-5`, aucune lettre changee, aucun verdict rouvert (section 1.5) |
| `sofia-sm` | Le choix du caractere de `TYP-1`, decision conjointe, avec le fait de `jsoup.text()` a integrer (section 4.1) |
| `sofia-sm` | Une convention de redaction rendrait `ADR-0066` clause 2 utile ; c'est du process, je ne le decide pas (section 2.3) |
| `yanis-qa` | **66 occurrences de `.text()`** dans les tests de rendu, chacune aveugle a l'espace insecable la ou elle compare un texte exact (section 1.5) |
| `camille-po` | Le lot d'ouverture de la saisie porte **onze decisions de perimetre de garde-fou** en plus de son code (section 3.3) |
| `camille-po` | Les lots 1, 2 et 3 de `TYP-1` restent entiers ; le cliquet les rend mesurables, il ne les fait pas (section 4.1) |
