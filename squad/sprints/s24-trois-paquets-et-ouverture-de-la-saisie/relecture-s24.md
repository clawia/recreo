# Relecture pedagogique - s24 (trois paquets et ouverture de la saisie)

- **Auteur** : relecteur-pedagogique. **Date** : 2026-08-31.
- **Arbre relu** : `origin/s24-sofia-dev` a `84d6311`, l'arbre fusionne complet. Worktree dedie,
  branche `s24-relecteur`, checkout principal jamais touche.
- **Perimetre etabli par moi-meme** : `git log --no-merges` et `git diff --name-only` entre `ea546cf`
  (dernier commit de s23) et `84d6311` - **40 commits non-merge, 120 fichiers touches**.
- **Je n'ai pas ete invoque pendant le sprint.** Cette relecture arrive apres le Done technique et
  apres la review, et non entre les deux. Ce qui suit est donc a reprendre dans `review.md` par
  `sofia-sm` plutot qu'a y avoir figure.

---

## 1. Ce que j'ai corrige

**Une seule correction, et c'est celle qui m'etait routee.**

| # | Fichier | Ce qui etait ecrit | Ce qui est ecrit | Source |
|---|---|---|---|---|
| 1 | `squad/README.md`, section 7 | *"`mvn verify` vert - compilation, tests unitaires, tests d'integration Spring Boot, ArchUnit, **et integration H2 une fois `adapter-persistence` cree**"* | La clause H2 est retiree ; la phrase s'arrete a ArchUnit | `DEC-0028` (2026-08-14) supprime la persistance du produit ; `CLAUDE.md` ecrit que `adapters/adapter-persistence` **n'existe pas et ne sera pas cree**. Le fichier de process decrivait une porte technique que ni le depot ni la decision ne portent |

C'est l'obstacle 7 de `review.md`, route a moi, et il est ferme. **Je ne l'ai pas elargi** : je n'ai
touche a aucune autre phrase de la section, et le fond de la porte technique n'est pas de mon
ressort.

**Aucune autre correction.** Ce n'est pas un sprint pauvre en texte - c'est le contraire - c'est que
le texte y est propre. Le detail est en section 2.

---

## 2. Le contenu lu par l'enfant - controle systematique, `DEC-0002` amendement du 2026-08-04 et `DEC-0056`

**Quatre paquets neufs relus mot par mot, pas echantillonnes** : `cp-texte-narratif` (20 items),
`cp-pluriel` (21), `cm1-fractions` (24), `cp-complements-10-saisie` (11). Soit **76 items**, et pour
le paquet narratif l'enonce, le texte support, la bonne reponse, les deux distracteurs et l'indice
de chacun.

**Verdict : zero faute. Aucune correction a appliquer.**

Methode, parce qu'un verdict sans methode ne vaut rien :

- **Lecture integrale a l'oeil** de toutes les chaines servies des quatre paquets. C'est la classe
  que le garde-fou d'`ADR-0065` laisse a la main - les homographes, environ 17 % des mots servis - et
  c'est la matiere que `nadia-prof` a signalee comme neuf a trente-huit fois plus dense sur le paquet
  narratif que sur les paquets de calcul : participes et verbes conjugues a trancher dans leur
  phrase.
- **Extraction du vocabulaire servi** : 319 mots distincts sur les quatre paquets, relus un par un.
- **Recherche ciblee des homographes non accentues** dans le texte servi (a, ou, la, du, sur, mur,
  tache, cote, pres, ete, ecrit, eleve, reponse) : **zero occurrence** d'une forme non accentuee la
  ou l'accent est du.
- **Inventaire des caracteres non-ASCII** des quatre fichiers. Aucun tiret cadratin, aucun guillemet
  typographique, aucun emoji, aucun symbole decoratif. Les seuls caracteres hors ASCII sont les
  lettres accentuees attendues et l'espace insecable de `TYP-1`.

Deux verifications de coherence que j'ai faites parce que le paquet narratif se decrit lui-meme :

- **Les accents des capitales sont poses** : "Ou" porte son accent aux sept enonces de lieu, "Ecris"
  aux six items de forme (b) de `cm1-fractions`. Une capitale non accentuee dans un texte lu par un
  enfant qui apprend a lire n'est pas un detail typographique, et il n'y en a pas.
- **Les distracteurs volontairement fautifs de `cp-pluriel`** (*"une assiettes"*, *"des branche"*)
  sont le bareme de l'exercice et non des fautes : `PLU-4` les fait deriver de la bonne reponse. Je
  ne les corrige pas et je le dis pour qu'un relecteur suivant ne les corrige pas non plus.

**Ce que le paquet declare sur lui-meme est vrai**, recompte contre ses items plutot que cru :
`NAR-7` (dix prenoms feminins et dix masculins distincts, les vingt presents), la repartition
premiere/seconde phrase des items "Ou" (4 et 3) et "Qui" (3 et 4), le cas le plus serre de `NAR-2`
(item 2, 17 mots sur 18), `PLU-5` (10 pluriels contre 11 singuliers, plancher a 7), `FRA-4` (dix
numerateurs a 1 sur 24), `FRA-5e-1` (famille (a) sur quatre des six items de forme (d)), `FRA-8` (six
supports de partage, aucun rattache a quelqu'un). **Aucun ecart.**

**Ce que je ne juge pas** : le niveau scolaire, le vocabulaire, le ton et le fond de ces textes sont
la porte de `nadia-prof`, qui prononce en parallele. Une seule formulation m'a fait m'arreter sans
que je la corrige - *"On en court 1. Quelle fraction est courue ?"* (`cm1-fractions`, item 18) : la
construction transitive de "courir" est defendable en grammaire, sa lisibilite au CM1 ne m'appartient
pas. **Signale a `nadia-prof`, pas corrige.**

---

## 3. Les conventions de redaction sur les 120 fichiers touches

Balayage mecanique, pas impression de lecture.

| Controle | Perimetre | Resultat |
|---|---|---|
| Tiret cadratin, tiret demi-cadratin | 120 fichiers | **0** |
| Guillemets typographiques, apostrophe courbe | 120 fichiers | **0** |
| Emoji, symbole Unicode decoratif | 120 fichiers | **0** |
| Chemin de poste, nom de compte, credential | index complet, `verifier-chemins.sh` dans les memes termes que la CI | **0** |
| Liens relatifs des documents modifies | 35 fichiers `.md` | **0 lien casse** |
| References `ADR-00NN` et `DEC-00NN` | 120 fichiers | **0 reference non resolue** |

Une seule occurrence hors ASCII a examiner, et elle est legitime : le signe de multiplication dans
*"tables de multiplication (jusqu'a x10)"*, cite depuis le libelle de programme dans
`MATRICE-couverture.md`, `docs/backlog/backlog.md` et le test du port entrant. C'est un operateur
mathematique qui porte du sens, pas une decoration, et il est anterieur a s24.

Sur les commentaires qui paraphrasent le code : je n'en ai trouve aucun dans les classes neuves. Les
Javadoc d'`ExigenceDeForme`, `NormalisationDeSaisie`, `SaisieVerdict` et `ContinuerMove` disent
toutes pourquoi la forme retenue plutot que ce que le code fait, y compris quand elles ecartent
nommement la forme concurrente. C'est le registre attendu.

---

## 4. `DEC-0062` - application du critere binaire dont je suis le verificateur

Le critere de la section 4 de `DEC-0062` : *"Pour chaque chiffre et chaque affirmation de fait d'un
fichier relu : sa provenance est-elle sous l'une des trois formes ? Si la forme est 1, la reference
resout-elle ?"* Applique aux documents de s24, ou la formule d'attribution de forme 3 est employee
**17 fois sur sept fichiers**.

### 4.1 Ce qui est conforme

Les trois faits recus de MASTER sont ecrits sous la forme 3 dans `review.md` section 4, `RAPPORT.md`
section 6, `retro.md` section 2 et `SUITES-s24` section 1, chacun avec sa date, chacun cite entre
guillemets, et **aucun ne vaut motif** : le chiffre de couverture est explicitement remplace par les motifs de
`DEC-0059`, l'affirmation sur le JDK est demontee et `ADR-0071` se fonde sur une reproduction de
mecanisme et non sur elle, l'annonce sur le lot 3 est datee contre un horodatage de commit. La borne
tient partout ou je l'ai verifiee.

J'ai recompte la matrice moi-meme plutot que de reprendre le chiffre : **47 lignes dans les quatre
tableaux de matiere, 24 `couvert-valide`, 4 `en cours`, 15 `non couvert`, 4 `non couvrable en
l'etat`, denominateur atteignable 43**. C'est exactement ce que `review.md` section 4.1 annonce, avec
sa methode. **Conforme.**

### 4.2 Trois ecarts, aucun corrigeable par moi

**(a) Le meme enonce recu de MASTER est cite avec deux verbes differents.** `DEC-0062` exige qu'un
enonce de forme 3 soit *"cite mot pour mot, jamais resume"*. Or :

- `DEC-0062` section 1 (`camille-po`) : *"l'ouverture de la saisie **fera** passer la couverture de 28
  a 33 lignes sur 43"* ;
- `review.md` section 4.1, `RAPPORT.md` section 6 et `planning.md` section 4 (`sofia-sm`) :
  *"... **ferait** passer ..."*.

Les deux ne peuvent pas etre le mot pour mot. **Je ne tranche pas** : je n'ai pas acces au canal, et
choisir un verbe serait fabriquer la citation que la convention protege. **A trancher par `sofia-sm`
et `camille-po`** - celle qui a recu l'enonce dit lequel est le sien, l'autre aligne.

**(b) La fausse attribution que `DEC-0062` a ete ecrite pour corriger est toujours dans le depot.**
`planning.md` section 4 porte encore *"Ce chiffre est faux, **et il est de moi**"*. C'est la forme que
`DEC-0062` section 3.1 nomme **interdite**, et sa correction sous la forme 3 est routee a `sofia-sm`
en section 5 et reconduite en A.5. **Non faite, et absente du tableau des obstacles de `review.md`** -
donc, au sens du principe de gouvernance de ce depot, elle n'existe pas.

**(c) Une attribution de forme 1 qui ne resout pas.** Trois documents attribuent a `nadia-prof` le
constat qui a declenche `ADR-0071` :

- `planning.md` ligne 379 : *"`nadia-prof` a constate qu'un fichier de toolchains du poste declare une
  toolchain `jdk` de version `21` en designant une racine de JDK 25"* ;
- `avis-faisabilite.md` section 8 : *"`sofia-sm` route un constat de `nadia-prof`"* ;
- `complement-jdk-declare-contre-execute.md` en-tete : *"Demande : `sofia-sm`, apres un constat de
  `nadia-prof`"*, avec l'enonce entre guillemets.

**Aucun des trois ne donne de fichier ni de section**, et le constat ne figure dans aucun des cinq
documents que `nadia-prof` a commites a s24 (`ARBITRAGE-D5`, `ARBITRAGE-FRA`,
`ARBITRAGE-PREALABLE-s24`, `CLAUSE-PORTE-PEDAGOGIQUE`, `SUITES-s24`). J'ai cherche la chaine sur
l'arbre suivi : elle n'apparait que dans les trois documents ci-dessus. **La reference ne resout
pas.** Or `DEC-0062` forme 1 exige *"le fichier et la section ou cet agent l'a ecrit"*, et pose qu'*"un
renvoi qui ne pointe vers rien est de la meme famille de defaut qu'un chiffre sans methode"*.

Deux issues, et **c'est a `sofia-sm` de dire laquelle** : ou le constat existe dans un document de
`nadia-prof` que je n'ai pas trouve, et la reference se complete ; ou il a ete transmis autrement, et
c'est un **quatrieme** fait de forme 3, a ecrire comme tel. Je note que le fait attribue a MASTER en
section 4.2 de `review.md` - *"toutes les mesures locales tournaient sur un JDK 25"* - et ce
constat-ci sont deux enonces distincts sur le meme objet, ce qui rend la distinction d'autant plus
utile a poser.

### 4.3 La convention n'est ecrite nulle part ou un agent la lirait

`DEC-0062` section 4 se defend d'etre *"un garde-fou annonce mais absent"* - la dette 7 de
`CLAUDE.md` - en disant qu'elle est verifiee par `relecteur-pedagogique`. **Etat du disque au
2026-08-31 :**

- `squad/README.md` ne contient pas une seule occurrence de MASTER ni de la convention. L'amendement
  est route a `sofia-sm` en `DEC-0062` section 5 puis A.5, avec la mention *"le statut `Acceptee` rend
  l'amendement du a partir d'aujourd'hui"*. **Non fait.**
- `.claude/agents/relecteur-pedagogique.md` ne contient ni `DEC-0062`, ni MASTER, ni le critere.
  L'ajout est route a `sofia-sm`. **Non fait.**

Je l'ai applique ce sprint parce que le brief me l'a demande nommement. **Un prochain relecteur
invoque sans ce brief n'aura aucun moyen de savoir qu'il doit l'appliquer.** Les deux amendements sont
routes a `sofia-sm` et **ne figurent dans aucun tableau d'obstacles** : ils sont a inscrire dans
`review.md`.

**Je n'ecris ni l'un ni l'autre moi-meme** : `squad/README.md` est le fichier de process de
`sofia-sm`, et ma propre fiche d'agent est de la configuration, que je ne modifie pas.

---

## 5. Ce que je route a son auteur

| # | Point | A qui | Pourquoi je ne le corrige pas |
|---|---|---|---|
| 1 | *"JDK **21.0.10** de la toolchain declaree"*, porte technique de `proposition-ines-ecran-de-saisie.md` (ligne 654) - formule qu'`ADR-0071` disqualifie comme methode | `ines-design` | Le document est le sien. `camille-po` et `theo-archi` ont tous deux refuse ce sprint de reecrire le document d'un autre agent, et `sofia-sm` apres eux. Je confirme l'obstacle 6 de `review.md` plutot que de le contourner. **Le chiffre est juste** (`ADR-0071` l'etablit) ; c'est la formule qui ne l'est pas |
| 2 | Le verbe du fait recu de MASTER, "fera" contre "ferait" (section 4.2.a) | `sofia-sm` et `camille-po` | Choisir revient a fabriquer la citation |
| 3 | La signature *"il est de moi"* de `planning.md` section 4 (section 4.2.b) | `sofia-sm` | Routee a elle par `DEC-0062` section 5, et c'est sa propre attribution |
| 4 | La reference de forme 1 a `nadia-prof` qui ne resout pas (section 4.2.c) | `sofia-sm` | Elle seule sait par quel canal le constat lui est arrive |
| 5 | Les deux amendements dus de `DEC-0062` - `squad/README.md` et la fiche de `relecteur-pedagogique` (section 4.3) | `sofia-sm` | Fichier de process et configuration d'agent |
| 6 | *"On en court 1. Quelle fraction est courue ?"* (`cm1-fractions`, item 18) | `nadia-prof` | Grammaticalement defendable ; sa lisibilite au CM1 n'est pas ma porte |
| 7 | Trois noms pour un seul concept (section 6.2) | `theo-archi` | Le terme canonique est une decision, et l'un des trois vit dans la section Regle executable d'un `ADR` accepte |
| 8 | Les signes doubles detachables du texte rendu par les gabarits (section 6.1) | `nadia-prof` pour la condition, `theo-archi` et `sofia-sm` pour le moyen | `ADR-0067` laisse le moyen ouvert et je ne le choisis pas |

---

## 6. Deux ecarts que je juge systemiques et non isoles

### 6.1 `TYP-1` ne voit pas la moitie du texte que l'enfant lit

`ADR-0067` porte la condition de `nadia-prof` : *"un signe de ponctuation double (?, !, ;, :) ne doit
jamais pouvoir se retrouver seul en debut ou en fin de ligne, separe du mot qu'il accompagne"*. Son
cliquet, `AucunSigneDoubleNEstDetachableDeSonMotTest`, derive son perimetre du **texte servi par les
paquets de contenu**. Un paquet neuf y entre a zero, et les quatre paquets de s24 y entrent a zero :
verifie, et c'est propre.

**Mais l'enfant lit aussi ce que les gabarits ecrivent, et aucun cliquet ne le voit.** Comptage sur
l'arbre relu, commentaires HTML exclus, chaines techniques exclues (selecteurs de fragment Thymeleaf,
messages d'exception) :

| Source | Occurrences rendues avec une espace ordinaire avant un signe double | Dont neuves a s24 |
|---|---|---|
| `fragments/saisie.html` | 3 - "Score : ", "Tu as ecrit : ", "On ecrit :" | **3** |
| `fragments/retour.html` | 3 - "Score : ", "Bravo, c'est juste !", "C'est fini !" | 0 |
| `fragments/manche.html` | 1 - "Score : " | 0 |
| `fragments/erreur.html` | 1 - "Oups ! Le jeu a un souci." | 0 |
| `PartieController` (constantes de message) | 3 - "Ta partie s'est arretee. Choisis un nouveau jeu !", "Bravo !", "Ce n'est pas ca. Essaie encore !" | 0 |
| **Total** | **11** | **3** |

**Pourquoi c'est systemique et pas une liste de onze corrections.** Le cliquet existe, dit `ADR-0067`,
pour *"arreter la croissance"* : *"quatre orphelins neufs sont apparus a s22"*. Or trois orphelins
neufs viennent d'apparaitre a s24, **dans le perimetre exact ou le cliquet ne peut pas les voir**, et
ils ont franchi une porte technique verte. Un cliquet qui ne couvre qu'une des deux sources du texte
rendu arrete la croissance a un endroit et l'autorise a l'autre.

Ce n'est pas un reproche a l'auteur du lot 4 : `fragments/saisie.html` reprend `'Score : '` de
`fragments/manche.html` a l'identique, ce qui est la bonne facon d'ecrire un second fragment. C'est le
perimetre du cliquet qui est en cause, pas la main qui a copie.

**Je ne corrige rien ici, et c'est un choix et non de la prudence** : `ADR-0067` ecrit en toutes
lettres que le choix entre l'insecable ordinaire, l'insecable etroite et le retrait de l'espace
*"reste ouvert"*. Poser un caractere dans onze chaines rendues reviendrait a trancher ce choix par un
commit de relecture, sur du texte lu par un enfant, sans que `nadia-prof` l'ait vu. **Signale, avec
son compte et sa methode.**

### 6.2 Un seul concept, trois noms, et le code disqualifie l'un d'eux

Le concept introduit par le lot 1 - ce que l'exercice exige de la forme d'une reponse ecrite - porte
trois noms dans le meme increment :

| Nom | Ou |
|---|---|
| **exigence de forme** | `ExigenceDeForme`, cle YAML `exigenceDeForme`, `SaisieContentSourceFactory`, les quatre fixtures et le paquet servi |
| **mode de forme** | Titre et section 1 d'`ADR-0069`, `ADR-0070` ligne 41, `ADR-index`, `avis-faisabilite` lignes 40, 73 et 287, `review.md` ligne 166, `RAPPORT.md` ligne 84 |
| **mode de saisie**, **mode de comparaison** | `ModeDeSaisieEnBooleenContreExemple`, et le nom de la regle mordue, `unModeDeComparaisonEnBooleenDansLeContratDeContenuEstRefuse` |

Ce n'est pas une preference de style. La Javadoc d'`ExigenceDeForme` **motive** son nom :

> *"Le nom dit ce que l'exercice **exige**, jamais comment une mecanique s'y prend pour comparer : un
> nom qui parlerait de comparaison ferait dire au contenu ce que la mecanique fait, la ou `ADR-0004`
> tient les deux separes."*

Or la troisieme ligne du tableau est exactement le nom que ce paragraphe ecarte, et elle vit dans la
section **Regle executable** d'`ADR-0069`, c'est-a-dire a l'endroit du depot qui fait foi. Le
raisonnement est ecrit et il est bon ; c'est le nommage qui ne l'a pas suivi partout.

**Je ne renomme pas.** Un renommage de classe se repercute dans `LesReglesMordentTest` par une chaine
assertee, dans le corps d'`ADR-0069` et dans `ADR-index` : ce n'est plus du texte, et le choix du
terme canonique appartient a l'auteur de l'`ADR`. **Route a `theo-archi`**, avec la remarque qu'un des
trois noms est deja arbitre par une Javadoc du meme sprint.

---

## 7. Deux constats de fraicheur, verifies et rendus a `sofia-sm`

Ils ne sont pas des defauts de redaction, mais ils rendent faux ce qu'un lecteur lit aujourd'hui.

1. **`review.md` a ete ecrit une seule fois (`abc2c9f`) et deux commits l'ont suivi.** Son obstacle 1
   dit *"le garde-fou d'`ADR-0072` transtype `(Question)` et casse le build apres fusion"*, etat
   *"OUVERT - bloque le Done technique"*. Le commit `0fe674a`, posterieur, s'intitule *"le garde-fou
   declare son domaine au lieu de transtyper en aveugle"* et se declare *"cause unique du rouge de la
   porte technique apres fusion"*. La review decrit donc un rouge que l'arbre qu'elle documente ne
   porte plus.
2. **`DEC-0063` section 3 est echue** : la mention datee due sur les lignes de matrice *"au moment de
   la fusion des lots 1 et 3"*. `review.md` la porte deja en obstacle 5 avec l'etat *"OUVERT, echu"*.
   Je confirme sur l'arbre : la fusion a eu lieu, la mention n'est pas portee. Proprietaire
   `camille-po`, inchange.

---

## 8. Ce que cette relecture ne fait pas

- Elle **ne prononce aucun Done**, ni technique ni pedagogique.
- Elle **ne tranche ni le produit ni l'architecture** : les huit points de la section 5 partent a leur
  proprietaire, aucun n'est decide ici.
- Elle **ne touche a aucune logique de code**. Un seul fichier de contenu ou de code n'est modifie :
  aucun. La seule modification de cette branche, hors ce document, retire une clause de phrase de
  `squad/README.md`.
- Elle **ne juge pas le niveau scolaire, le vocabulaire, le ton ni le fond** des textes lus par un
  enfant. Cette porte est a `nadia-prof` et elle prononce en parallele.
