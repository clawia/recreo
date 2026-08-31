# [Sprint s24] Rapport qualite - `yanis-qa`

- **Date** : 2026-08-31. **Auteur** : `yanis-qa`.
- **Arbre mesure** : worktree dedie, branche `s24-yanis`, branchee sur `origin/s24-sofia-dev`,
  tete `84d6311` (arbre fusionne de cloture). Aucune mesure de ce rapport ne vient d'une autre
  branche.
- **Je n'ai pas ete invoque pendant le sprint.** Ce rapport arrive apres la review et il ne
  pretend pas l'avoir precedee. Ce qu'il contient d'utile ne vient pas de sa ponctualite mais de
  ce qu'il a casse.

> **Ce rapport ne prononce que le Done technique, et il ne le prononce pas non plus : la CI le
> juge, je le mesure et je le rapporte.** Le Done pedagogique appartient a `nadia-prof` seule, il
> n'est pas mentionne ici et rien de ce qui suit ne s'en approche.

---

## 1. La porte technique, mesuree par moi

### 1.1 La commande et son verdict

```
mvn -B clean verify
```

a la racine du reacteur. **Reacteur complet, aucun `-pl`, donc aucun `-am` a citer et aucune
reserve de perimetre a poser.**

> **BUILD SUCCESS.**

| Module | Tests | Echecs | Erreurs | Ignores |
|---|---:|---:|---:|---:|
| `recreo-domain` | 78 | 0 | 0 | 0 |
| `recreo-application` | 13 | 0 | 0 | 0 |
| `recreo-adapter-web` | 349 | 0 | 0 | 0 |
| `recreo-adapter-content` | 352 | 0 | 0 | 0 |
| `recreo-bootstrap` (surefire) | 125 | 0 | 0 | 0 |
| `recreo-bootstrap` (failsafe) | 6 | 0 | 0 | 0 |
| **Total** | **923** | **0** | **0** | **0** |

**Methode du comptage** : somme des attributs `tests`, `failures`, `errors` et `skipped` de
l'element `<testsuite>` de chaque `TEST-*.xml` de `target/surefire-reports/` et
`target/failsafe-reports/`, sur les rapports produits par ce build apres un `clean`.
**132 rapports** (128 surefire, 4 failsafe). **Perimetre : le depot entier**, les cinq modules du
pom agregateur, sans exclusion.

**Mesure deux fois, a deux heures d'intervalle, sur le meme commit et dans le meme worktree,
encadrant toute la campagne de mutation decrite en section 3** : meme verdict, memes 923, memes
132 rapports. Le second passage servait a verifier qu'aucune mutation n'avait laisse de residu ;
il n'en a laisse aucun.

### 1.2 Sur quelle machine virtuelle, et pourquoi je le dis

Les tests s'executent sur **Java 21.0.10**, valeur lue dans les journaux de demarrage Spring Boot
du build lui-meme, jamais deduite d'une declaration. Le prerequis nomme par `CLAUDE.md` est une
toolchain de type `jdk` et de version `21` ; Maven l'a resolue et le compilateur comme surefire
ont tourne dessus.

**Un ecart d'environnement que je signale sans le corriger, parce qu'il n'appartient pas au
depot** : sur le poste ou j'ai mesure, l'enveloppe `outils/mvn-jdk21.sh` et Maven ne lisent pas le
meme fichier de toolchains, parce que `HOME` et le profil utilisateur de la machine virtuelle de
Maven ne designent pas le meme repertoire. L'enveloppe le dit elle-meme, en clair, des sa premiere
ligne, et c'est exactement ce que l'obstacle 23 lui a fait ajouter : elle annonce la version que le
JDK **rapporte**, pas celle que la toolchain **declare**. Le garde-fou d'environnement a donc
fonctionne, et la version sur laquelle j'ai mesure est celle que j'ecris ci-dessus, pas celle que
l'enveloppe a resolue pour son propre lancement. **Les chiffres de ce rapport sont ceux du build
que Maven a execute.**

### 1.3 Le delta contre la review, sans le maquiller

La review de `sofia-sm` (section 1) mesure **BUILD FAILURE** sur `abc2c9f` : `adapter-content`
rouge, `bootstrap` non execute, **101 rapports, 787 tests, 2 erreurs**, et elle refuse
explicitement de fabriquer un total de depot. Ce refus etait le bon geste et son chiffre etait
exact a sa date.

Deux commits l'ont suivie et sont dans l'arbre que je mesure :

- `0fe674a` (`theo-archi`) - le garde-fou d'`ADR-0072` declare son domaine au lieu de transtyper ;
- `84d6311` (`sofia-sm`) - la traversee de saisie cesse d'etre vide.

**Delta que j'annonce, contre le commit de base `abc2c9f` : de BUILD FAILURE a BUILD SUCCESS, et
de "pas de total de depot" a 923 tests sur 132 rapports.** Je n'annonce pas ce total comme une
progression de couverture : il n'est pas comparable aux 787 de la review, qui portaient sur quatre
modules sur cinq. **Le total de l'arbre fusionne est etabli ici pour la premiere fois du sprint.**

### 1.4 Les autres criteres de sortie du Done technique

| Critere | Etat mesure par moi |
|---|---|
| `mvn -B clean verify` vert a la racine | **Tenu** - section 1.1, deux passages |
| Regles ArchUnit | **31**, methode : comptage des lignes `static final ArchRule` dans les `.java` des cinq arbres de modules, arbre de contre-exemple et copie sous `target/` exclus. Le chiffre de la review se reproduit |
| `git ls-files ... \| bash .github/scripts/verifier-chemins.sh` | **Tenu** - *"Aucun chemin de poste de travail ni nom de compte dans les fichiers verifies"*, code de retour 0, sur l'index complet moins le fichier de cas de test du garde-fou, exclusion que la CI applique |
| Tests instables | **Aucun.** Voir section 7 : 63 executions completes du reacteur sur cet arbre, dont deux sur l'arbre intact |

---

## 2. Les criteres d'acceptation de s24, et le test qui les nomme

La tracabilite est portee par les references de conditions citees dans la Javadoc des methodes de
test et dans les clauses `as(...)` des assertions, pas par le nom des classes - ce depot n'emploie
aucun `@DisplayName`. **Methode** : recherche litterale de chaque identifiant de
condition dans les fichiers de test des cinq modules.

| Story | Conditions citees dans au moins un test | Conditions sans test, et pourquoi |
|---|---|---|
| `S24-SAI-1` | `SAI-1`, `SAI-2a`, `SAI-2c`, `SAI-3`, `SAI-4`, `SAI-5`, `SAI-6`, `SAI-7`, `SAI-8`, `SAI-9a`, `SAI-9b`, `SAI-12`, `SEUIL-2`, `SEUIL-3`, `SEUIL-4` | `SEUIL-5`, `SAI-2b`, `R1` a `R6` : le backlog les designe lui-meme comme *"ce que `nadia-prof` garde en propre, qu'aucune CI ne verra"*. Ce n'est pas un trou de couverture, c'est une frontiere ecrite |
| `S24-SAI-2` | `CPL-9` (via l'entree de catalogue), conditions de paquet portees par les garde-fous de contenu derives | - |
| `S24-NAR`, `S24-PLU`, `S24-FRA` | Chacune porte son identifiant de story dans un test nomme (`PaquetCpTexteNarratifTest`, `PaquetCpPlurielTest`, `PaquetCm1FractionsTest`), plus les garde-fous derives | - |

**Un ecart de nommage, pas de couverture** : `SEUIL-1` n'apparait dans aucun test sous ce nom. La
propriete qu'il porte - *l'enfant voit, au moment du verdict, la chaine sur laquelle ce verdict a
ete rendu* - est couverte, sous les noms `SAI-9a` et `SAI-9b`. Repondre a *"ce critere est-il
couvert ?"* par une recherche sur `SEUIL-1` rend zero et la reponse est fausse. Je le signale
plutot que de le compter comme un manque : la correction est une mention de plus dans une clause
`as(...)`, et elle appartient a l'auteur du test.

**Aucun module n'est sans test** : les cinq modules du pom agregateur produisent des rapports, du
plus petit (`application`, 13 tests) au plus gros (`adapter-content`, 352).

---

## 3. L'etendue reelle du motif, mesuree

`sofia-sm` me route ceci : *"Trois garde-fous de ce depot, en une seule journee, promettaient plus
large que ce qu'ils verifiaient. C'est un motif, pas trois accidents."* Elle ajoute que les trois
etaient **invisibles a la lecture**. J'ai donc mesure, pas lu.

### 3.1 Methode

**Mutation du materiau reel, jamais d'une fixture.** Une mutation est une modification minimale
d'un artefact de production - un paquet de contenu servi, le catalogue, la feuille maison, un
gabarit, ou le code de la fabrique qui sert le contenu - qui viole une propriete qu'un garde-fou
nomme dans son nom, sa Javadoc ou son message d'echec. Pour chaque mutation :

1. l'arbre est remis a l'etat de `84d6311` ;
2. la mutation est appliquee ;
3. **tous les rapports `TEST-*.xml` du depot sont effaces** avant l'execution, sans quoi un module
   non atteint rendrait le verdict de l'execution precedente ;
4. `mvn -B -o verify -Dmaven.test.failure.ignore=true -Dmaven.test.error.ignore=true` a la racine.
   **Reacteur complet, aucun `-pl`.** Les deux drapeaux d'ignorance ne relachent rien : ils font
   que **tous** les modules s'executent meme quand un module amont est rouge, et le verdict est lu
   dans les rapports XML, jamais dans le code de retour ni dans la console ;
5. la liste des classes rouges est relevee et l'arbre est restaure.

Un **controle prealable** verifie que chaque mutation change reellement son fichier. Il a rejete
huit mutations sur les vingt-sept premieres ecrites, toutes sans effet pour une raison de forme
(indentation, fin de ligne, caractere absent du paquet vise). **Une mutation sans effet produit un
faux survivant**, et c'est la premiere facon dont cette campagne pouvait mentir.

**Trois axes, parce que les trois defauts de la journee ne sont pas de la meme famille :**

- **groupe A/B, mutation de la donnee** - la meme violation appliquee au **premier** item d'un
  paquet puis au **dernier**, et au premier puis au dernier paquet du catalogue. C'est l'axe qui
  aurait revele le tirage a hasard constant zero ;
- **groupe C, mutation de l'interface** - feuille maison, gabarits, en-tete de page ;
- **groupe D, mutation du code qui sert la donnee** - la fabrique perd un champ, ou ampute le
  dernier item. C'est l'axe qui separe un garde-fou qui juge **ce qui est servi** d'un garde-fou
  qui relit **ce qui est ecrit**.

### 3.2 Perimetre, et il n'est pas le depot

**Population de reference** : les fichiers de test des cinq modules qui lisent un artefact de
production, derives comme `LesGardeFousSontMisALEpreuveTest` les derive - modules du pom
agregateur, balayage des sources de test, arbres de contre-exemple exclus. Aucune liste ecrite a
la main. **56 fichiers, 54 noms de classe distincts** : `ProvenanceDesClassesAmontEstLeDepotTest`
existe dans trois modules sous le meme nom.

**Ce que la campagne couvre** : **55 mutations**, visant **31 garde-fous nommes**, dont **30
appartiennent a cette population** - le trente-et-unieme,
`ChaqueJeuDuCatalogueOffrePlusDItemsQueDeManchesTest`, ne lit pas `src/main/` et sort donc de la
derivation, mais il est mordu deux fois et je le compte dans le resultat. **30 sur 54, soit 56 %
de la population.** Les 24 autres ne sont pas juges ici : je n'ai pas su fabriquer une mutation
qui viole leur promesse sans en violer une autre en meme temps, ou leur objet (provenance des
classes, concordance des tags de commit, resolution des references de decision) demande une
mutation d'infrastructure hors du perimetre que je me suis donne aujourd'hui. **Je ne conclus donc
rien sur ces 24.**

**Cout mesure** : 75 minutes de calcul pour les 55 mutations, soit **environ 80 secondes par
mutation**, sur le reacteur complet en mode hors ligne sans `clean`. Le chiffre compte pour la
section 4.

### 3.3 Le resultat

| Groupe | Mutations | Garde-fou vise rouge | Vise vert |
|---|---:|---:|---:|
| A/B - donnee de contenu et catalogue | 33 | 29 | 4 |
| C - feuille maison et gabarits | 14 | 13 | 1 |
| D - code de la fabrique | 8 | 6 | 2 |
| **Total** | **55** | **48** | **7** |

**Sur les 7 survivants, 5 ne font rougir aucune classe du depot** ; les 2 autres font rougir des
voisins mais pas celui qui l'annonce.

**L'axe de position ne rend rien.** Onze couples "meme violation, premier item contre dernier
item" ont ete executes. **Les onze rendent un verdict identique dans les deux positions.** Le
defaut du tirage a hasard constant zero **ne se generalise pas** a la famille des garde-fous de
contenu : ils traversent leur population entiere. C'est un resultat negatif, il a coute 22
executions, et il vaut d'etre ecrit - il ferme une hypothese plutot que de la laisser planer.

### 3.4 Les survivants, un par un

**Un survivant sur deux etait un defaut de ma mutation, pas du garde-fou.** Je les ecris tous, y
compris ceux-la, parce que la lecon porte sur la methode autant que sur le resultat.

#### a) `FrontieresDuJavaScriptTest` - un trou reel, et personne ne le voit

**Mutation C7** : ajout de `onclick="this.classList.add('deja-essayee')"` sur le bouton de
proposition du fragment de manche. **Aucune classe du depot ne rougit.** 923 tests, 0 echec.

C'est du comportement de jeu ecrit en JavaScript, dans un gabarit servi a l'enfant. Ce que
`ADR-0008` decide - *"un unique fichier `recreo.js` d'agrement visuel, budget 4 096 octets et 100
lignes, plafond verifie par un test"*, et la liste de vocabulaire interdit qui va avec - ne
l'atteint pas : le garde-fou derive sa population de `static/**/*.js` hors du repertoire tiers, et
son seul balayage de gabarits cherche quatre marqueurs de correction (`data-correct`, `data-bonne`,
`data-solution`, `data-reponse`). **Un gestionnaire d'evenement inline n'est ni un fichier `.js`
ni un marqueur de correction.** Le budget d'`ADR-0008` est contournable par un attribut, sans
limite de taille, et sans qu'aucun test bouge.

C'est la **dette n°4 de `CLAUDE.md`** - *"le comportement des mecaniques loge dans le JavaScript
client : 692 lignes qu'aucun test ne couvrait"* - dont la porte est ouverte. Le depot n'a aucun
`onclick`, `oninput`, `onsubmit` ni `hx-on:` aujourd'hui : **rien n'est casse, la voie est
simplement libre.**

**Je ne corrige pas.** `ADR-0008` et ce garde-fou sont a `theo-archi`. Route en section 6.

#### b) `LesFormesAccepteesDUnPaquetDeSaisieServiSontLeBaremeReelTest` - un paquet lu en partie, sans le dire

**Mutation D2** : la fabrique de saisie ampute le dernier item du paquet (`items.subList(0,
size - 1)`). **Onze classes rougissent. Celle-ci reste verte** (4 tests, 0 echec).

La cause est lisible une fois la mutation posee, et pas avant. Sa traversee borne ses tirages a
`itemsDeclares(...)` - 11 pour `cp-complements-10-saisie` - obtient 10 items parce que la fabrique
en sert 10, puis compare `items` a `tires` : 10 contre 10, l'assertion passe. Elle examine ensuite
les rangs 0 a 9 contre les rangs 0 a 9 du fichier. **Le onzieme item n'est examine par personne,
et rien ne le dit.** C'est `ADR-0064` dans son enonce exact : une lecture partielle silencieuse.

**Le comptage qui rend ce point non anecdotique** : huit garde-fous de contenu derivent leur borne
du meme helper `itemsDeclares`. **Six assertionnent `hasSize(declares)`** - le paquet enumere a
bien ete lu en entier, ou c'est rouge. **Deux ne le font pas**, et ce sont exactement les deux qui
ont ete repares aujourd'hui : celui-ci et `AucunePhraseDuTexteSupportNeSuffitAGagnerLaMajoriteTest`.
Methode du comptage : occurrences litterales de `hasSize(declares)` dans les huit fichiers qui
appellent `ChaquePaquetPorteUnIndiceTest.itemsDeclares`. Un neuvieme fichier, `PaquetCm1FractionsTest`,
sort du compte : il porte une methode locale du meme nom, qui n'est pas ce helper.

**Ce que je n'affirme pas, et pourquoi.** J'ai voulu montrer que la reparation de `84d6311` avait
retire cette morsure, en rejouant D2 avec et sans la reparation. La sonde donnait vert avec, rouge
sans - resultat spectaculaire et **faux**. Un controle que j'ai ajoute ensuite (`E6` : reparation
retiree, **aucune mutation**) rend deja rouge : la version d'avant `84d6311` epinglait *"zero
paquet de type saisie est servi au 2026-08-31"*, declaration devenue caduque des que le paquet est
entre au catalogue. **Mon rouge venait du revert, pas de la mutation, et ma comparaison ne prouvait
rien.** Je la retire. Ce qui reste est mesure et suffit : sur l'arbre d'aujourd'hui, D2 laisse ce
garde-fou vert.

**Je ne corrige pas.** Ce garde-fou est a `sofia-sm`, qui l'a ecrit ce matin. Route en section 6.

#### c) `UnePartDesigneeApparaitUneSeuleFoisDansSonEnonceTest` - un domaine qui se vide sans le declarer

**Mutation D6** : la fabrique de question ne conserve `partDesignee` que sur le premier item.
**Une seule classe rougit, et c'est `PaquetCpNatureMotTest`**, le test de story. Le garde-fou
generique reste vert : quinze items sur seize sortent de son domaine, et il ne compte pas ce qu'il
a ecarte. La consequence pour l'enfant serait qu'aucun mot ne soit marque a l'ecran sur quinze
manches sur seize.

C'est le meme geste que `AucunePhraseDuTexteSupport...` fait explicitement - *"une borne qui
emporterait toute la population servie n'est plus une borne, c'est une exemption generale, et elle
est rouge"* - et que celui-ci ne fait pas. **Severite moindre que a) et b)** : le defaut est
attrape, par un voisin, bruyamment. Route quand meme, parce que la protection tient a l'existence
d'un test de story et pas a une propriete du garde-fou.

#### d) `LeTexteServiPorteLeCaractereDecideJamaisSaNotationTest` - faux survivant, ma mutation etait hors sujet

**Mutation A7** : ecriture de la notation ASCII de l'espace insecable dans l'indice d'un item.
**Zero classe rouge.** J'ai failli l'inscrire comme un trou.

Sa Javadoc dit ce que ma mutation ignorait : dans un scalaire a **guillemets doubles** - le style
de tous les paquets servis - snakeyaml **resout** la sequence, et le texte servi est juste. Le
garde-fou juge le texte servi, il a raison d'etre vert. **Ma mutation avait un perimetre qui
excluait le defaut cherche**, exactement comme un client qui normalise l'entree avant qu'elle
n'atteigne le code teste. Mutation A7bis, meme notation dans un scalaire **a apostrophes** - le
style ou snakeyaml ne resout pas : **rouge**, une classe, la bonne.

#### e) `LesFormesAcceptees...` sous mutation de donnee - hors perimetre par construction

**Mutation A12** : les formes acceptees d'un item du paquet servi sont remplacees par une valeur
jamais decidee. **Zero classe rouge**, et c'est correct : ce garde-fou lit les formes attendues
**dans le fichier YAML** et les confronte a ce que la mecanique accepte. Muter le fichier deplace
les deux cotes ensemble. La sonde juste pour lui est une mutation de **code** : D1 (la fabrique
perd une forme) le rend rouge. **Ce n'est pas un defaut, c'est une mutation mal choisie**, et la
distinction compte pour la section 4 : un outil de mutation qui ne muterait que la donnee
declarerait faussement ce garde-fou decoratif.

### 3.5 Reponse a la question posee

**Combien de garde-fous de ce depot verifient moins que ce que leur nom, leur Javadoc ou leur
message d'echec promettent ?**

Sur les **31 garde-fous mis a l'epreuve** par au moins une mutation de leur propre promesse, mesure
sur l'arbre fusionne `84d6311` :

- **1 ne voit rien** de la classe de defaut que sa decision fondatrice interdit
  (`FrontieresDuJavaScriptTest`, le JavaScript inline dans un gabarit) ;
- **2 laissent passer une violation en silence dans leur propre perimetre**
  (`LesFormesAccepteesDUnPaquetDeSaisieServiSontLeBaremeReelTest` sous amputation d'item,
  `UnePartDesigneeApparaitUneSeuleFoisDansSonEnonceTest` sous perte de champ ; le second est
  attrape par un voisin) ;
- **28 mordent sur chaque mutation qui vise leur promesse.**

**Soit 3 sur 31, environ 10 %.** Ce n'est pas une extrapolation aux 54 de la population : les 24
non eprouves ne sont pas dans ce ratio et je ne les y mets pas.

**Le motif que `sofia-sm` decrit existe, et il est plus etroit qu'une epidemie.** Sa forme n'est
pas "les garde-fous de ce depot promettent trop". Elle est plus precise, et les trois cas de la
journee comme les trois miens la portent tous : **un garde-fou qui derive sa population du depot
est fiable sur la population qu'il voit, et muet sur celle qu'il ne voit pas.** Compter une
population n'est pas la juger ; deriver une borne n'est pas prouver qu'on l'a atteinte ; balayer
un repertoire n'est pas balayer un langage. Les trois defauts de ce matin et les trois d'ici sont
six variantes de la meme phrase.

---

## 4. Par quel moyen on les trouve - mon verdict

La question est : la mutation du materiau reel, qui a marche trois fois sur trois ce matin et
qui a rendu 3 trous sur 31 cet apres-midi, est-elle generalisable a l'echelle du depot, a quel
cout, et sous quelle forme ?

### 4.1 Ce que le cout dit

**80 secondes par mutation sur le reacteur complet.** Une campagne de 55 mutations tient dans 75
minutes de machine. Le cout de calcul n'est pas l'obstacle.

**Ce qui coute est l'ecriture de la mutation.** Chacune des 55 demande de lire la promesse d'un
garde-fou, de comprendre par quelle donnee elle passe, et de fabriquer une violation qui ne viole
que celle-la. J'y ai passe l'essentiel de mon temps, et **huit de mes vingt-sept premieres
mutations etaient sans effet** - detectees par un controle, pas par de la vigilance. Deux autres,
A7 et A12, avaient un effet mais **hors du perimetre du garde-fou vise**, et ont produit des faux
survivants que seule la lecture de leur Javadoc a permis d'ecarter.

**C'est le chiffre qui decide la reponse : 10 mutations sur 55, soit environ une sur cinq, etaient
fausses avant correction.** Un outil generique qui fabriquerait des mutations sans lire les
promesses produirait ce taux-la en permanence, et ses faux survivants coutent plus cher que les
vrais trous qu'il trouve. Un survivant qu'on doit instruire un par un n'est pas un signal, c'est
une file d'attente.

### 4.2 Ce que je propose de ne pas faire

**Je n'ecris pas de consigne generale** du type *"tout garde-fou doit etre mis a l'epreuve par
mutation du materiau reel"*. Ce depot en a deja une, `ADR-0013` obligation 3, avec son jeu fautif
par garde-fou, et elle est respectee : la dette d'exemption est de deux noms sous un plafond de
deux. **Les trois trous d'aujourd'hui sont tous chez des garde-fous qui ont leur jeu fautif et qui
le passent.** Ajouter une obligation de plus a des garde-fous qui satisfont deja la precedente ne
changerait rien : le jeu fautif prouve que le critere mord sur l'objet que son auteur a imagine,
et **le trou est toujours dans l'objet qu'il n'a pas imagine**.

**Je n'ecris pas non plus d'outil de mutation generique.** Section 4.1 : son taux de fausse alerte
serait celui de mes mutations non instruites, et le depot a deja sanctionne deux fois la famille
"detection qui compte l'antidote parmi les malades". Un outil qui mute la donnee seule declarerait
`LesFormesAcceptees...` decoratif alors qu'il ne l'est pas sur cet axe ; un outil qui mute le code
seul manquerait C7, qui n'est pas dans le code.

**`theo-archi` a repondu deux fois ce sprint qu'une propriete n'etait pas formulable, et c'etait la
bonne reponse les deux fois.** Ma reponse est de la meme famille, sur la generalisation, et pas sur
les cas.

### 4.3 Ce que je propose de faire, et qui est plus petit

Trois choses, toutes bornees, toutes verifiables, aucune n'etant une consigne generale.

1. **La clause d'exhaustivite est deja ecrite, elle est juste inegalement appliquee.** Six des
   huit garde-fous qui derivent leur borne d'`itemsDeclares` assertionnent `hasSize(declares)` ;
   deux ne le font pas. Ce n'est pas une regle a inventer, c'est un ecart a fermer, et il est
   comptable en une ligne de recherche. Il appartient aux deux auteurs concernes.
2. **Une mutation est un objet de revue, pas un outil.** Ce qui a trouve les trois trous de ce
   matin et les trois miens n'est pas un automate : c'est quelqu'un qui a demande *"par quelle
   donnee cette promesse passe-t-elle, et que se passe-t-il si je la casse la ?"*. La forme qui
   marche est la **question posee a la revue d'un garde-fou neuf**, avec sa reponse ecrite dans
   le commit - ce que `sofia-sm` a fait ce matin en ecrivant *"reproduit par mutation le
   2026-08-31, fabrique amputee du dernier item : vert avant, rouge apres"*. **Cette phrase-la
   vaut plus qu'un outil**, et elle est deja dans les moeurs du depot.
3. **Ce que j'ai laisse dans le depot pour que ce ne soit pas a refaire** : le tableau complet des
   55 mutations avec leur verdict, en annexe de ce fichier. Une mutation ecrite est reutilisable ;
   une campagne racontee ne l'est pas.

**Et une reserve que je nomme plutot que de la taire** : cette campagne a couvert 30 garde-fous sur
les 54 de la population derivee. La refaire sur les 24 restants demande le meme travail
d'instruction par garde-fou, environ une journee. Je ne dis pas qu'elle ne les vaut pas ; je dis
que **je ne les ai pas mesures et que ce rapport ne dit rien d'eux.**

---

## 5. Les deux defauts d'integration - ce qu'une porte peut, et ce qu'elle ne peut pas

Fait rapporte : vert sur une branche, vert sur l'autre, rouge une fois fusionnees. `theo-archi`
en tire contre lui-meme *"j'ai decrit mon clone en croyant decrire le depot"*.

**Ma reponse, en deux parties, parce que la question en contient deux.**

**Une porte de qualite ne peut pas attraper cette famille, et la raison est structurelle.** Un
garde-fou qui derive sa population du depot est une fonction de l'arbre sur lequel il tourne. Deux
branches qui ajoutent chacune un element - ici un garde-fou d'un cote, un paquet d'un type neuf
dans le repertoire de contenu de l'autre - produisent un croisement qui **n'existe dans aucun des
deux arbres**. Il n'y a rien a mesurer avant la fusion parce que l'objet a mesurer n'est pas encore
la. C'est un conflit semantique de fusion ; Git ne le voit pas, et aucun test non plus. **C'est
structurellement du ressort de la CI sur l'arbre fusionne, et le vert de branche n'a jamais voulu
dire autre chose que "vert sur cet arbre-la".**

**Ce qu'une porte peut, en revanche, c'est decider du mode de defaillance.** Les deux defauts de la
journee ont ete **bruyants** : un transtypage nu qui leve, une compilation de test qui refuse. Ils
ont coute une demi-journee et rien d'autre. La variante dangereuse de la meme situation est
**silencieuse** : un garde-fou dont la derivation exclut sans bruit l'element neuf de l'autre
branche, et qui passe vert sur un objet qu'il n'a jamais regarde. **C'est le trou b) de la section
3.4, en plus grave** : la ou un transtypage nu a fait un rouge en une heure, une borne derivee sans
clause d'exhaustivite aurait fait un vert pour un sprint.

**Ma conclusion, et ce n'est pas une regle mais une preference de conception** : sur cette famille,
un garde-fou a interet a **echouer bruyamment plutot qu'a se defendre**. Le transtypage nu de
`theo-archi` n'etait pas un bon code, mais son mode de defaillance etait le bon. Un `instanceof`
defensif silencieux pose au meme endroit aurait rendu le sprint vert et le defaut invisible - et
c'est exactement ce que le lot 5a a refuse d'avance pour les quatorze garde-fous, avec raison.
**Le choix entre les deux est une decision d'architecture, elle est a `theo-archi`, et je la route
plutot que de la prendre.**

Sur les totaux annonces sur trois arbres differents : la reponse est dans la section 1.3 de ce
rapport et dans la section 1.1 de la review. **Il n'y a de total de depot que sur l'arbre
fusionne**, et il en existe un depuis `84d6311` : 923.

---

## 6. Ce que je route, et a qui

Je ne corrige aucun garde-fou qui ne m'appartient pas. Trois routages a `sofia-sm`, pour
qu'elle les distribue.

| # | Constat mesure | Proprietaire | Severite |
|---|---|---|---|
| 1 | `FrontieresDuJavaScriptTest` et le budget d'`ADR-0008` ne voient pas le JavaScript ecrit inline dans un gabarit. Mutation C7 : `onclick` sur un bouton de proposition, 0 classe rouge sur 923 tests. Aucun `onclick`, `oninput`, `onsubmit` ni `hx-on:` dans le depot aujourd'hui : la voie est libre, rien n'est casse | `theo-archi` (`ADR-0008`) | **Haute** - c'est la porte de la dette n°4 de `CLAUDE.md` |
| 2 | `LesFormesAccepteesDUnPaquetDeSaisieServiSontLeBaremeReelTest` borne ses tirages sur `itemsDeclares` sans assertionner que le paquet a ete lu en entier. Mutation D2 : la fabrique ampute le dernier item, 11 classes rouges, celle-ci verte. 6 des 8 garde-fous qui partagent ce helper portent la clause `hasSize(declares)`, 2 ne la portent pas | `sofia-sm` (auteure, `84d6311`) | **Moyenne** - silencieux dans son perimetre, attrape aujourd'hui par des voisins |
| 3 | `UnePartDesigneeApparaitUneSeuleFoisDansSonEnonceTest` ne compte pas ce que sa borne ecarte. Mutation D6 : la fabrique perd `partDesignee` sur 15 items sur 16, seul `PaquetCpNatureMotTest` rougit | `theo-archi` (`ADR-0062`) | **Basse** - attrape bruyamment, mais par un test de story |

**Deux points d'information, sans demande** :

- `AucunePhraseDuTexteSupportNeSuffitAGagnerLaMajoriteTest` est le second des deux garde-fous sans
  clause `hasSize(declares)`. Sous les mutations que j'ai jouees il mord (A15, A16, D5, D7). Je le
  signale a `theo-archi` comme voisinage du routage 2, pas comme un defaut mesure.
- La reparation d'`ADR-0072` (`0fe674a`) est **portante** : sonde E5, revert de ce seul commit sans
  aucune mutation, une classe rouge immediatement. Le correctif n'est pas decoratif.

---

## 7. Tests instables

**Aucun.**

**Methode, parce qu'une absence sans methode ne vaut rien** : **63 executions completes du
reacteur** sur cet arbre - 2 sur l'arbre intact, 55 avec une mutation chacune, 6 sondes de revert.
Sur les 2 executions sur l'arbre intact, separees de deux heures, meme verdict, memes 923 tests,
memes 132 rapports. Sur les 55 autres, aucune classe
rouge n'est apparue en dehors de celles que la mutation explique : les onze couples de mutations
en miroir (premier item contre dernier item) rendent onze fois exactement la meme liste de classes
rouges, ce qui est un controle de reproductibilite gratuit et que je n'aurais pas su fabriquer
aussi large a dessein.

Je n'ai **relance aucune execution** pour obtenir un vert. Aucun test n'est desactive, ignore ni
exclu : le compte des `skipped` est de 0 sur les 132 rapports.

---

## 8. Ce que ce rapport ne dit pas

- **Rien sur le Done pedagogique.** Il appartient a `nadia-prof`, il ne decoule pas du vert
  ci-dessus, et un increment techniquement irreprochable peut etre refuse.
- **Rien sur les 24 garde-fous non eprouves** de la population derivee de 54.
- **Rien sur ce qu'un enfant voit.** Toutes mes mesures sont des mesures de source et de test. Le
  trou C7 est un trou de garde-fou, pas un defaut vu a l'ecran.
- **Rien sur la comparaison "avant/apres reparation"** du garde-fou des formes acceptees : ma
  sonde etait invalidee par son propre controle, section 3.4 b).

---

## Annexe - les 55 mutations et leur verdict

Colonne "vise" : le garde-fou dont la mutation viole la promesse. Colonne "rouges" : toutes les
classes rouges de l'execution. `mvn -B -o verify` a la racine du reacteur complet, aucun `-pl`,
923 tests executes a chaque ligne.

| Mutation | Vise | Vise rouge | Classes rouges |
|---|---|:-:|---|
| A1p indice vide, item 1 | `ChaquePaquetPorteUnIndiceTest` | oui | 1 |
| A1d indice vide, dernier item | `ChaquePaquetPorteUnIndiceTest` | oui | 1 |
| A2p distracteur egal a la bonne reponse, item 1 | `AucunePropositionNEstEgaleAUneAutreDansUnItemTest` | oui | 3 |
| A2d distracteur egal, dernier item | `AucunePropositionNEstEgaleAUneAutreDansUnItemTest` | oui | 3 |
| A3p quatre propositions, item 1 | `AuPlusTroisPropositionsParItemTest` | oui | 2 |
| A3d quatre propositions, dernier item | `AuPlusTroisPropositionsParItemTest` | oui | 2 |
| A4p indice desaccentue, item 1 | `AucunMotServiNeSEcarteDeSonAccentTest` | oui | 2 |
| A4d indice desaccentue, dernier item | `AucunMotServiNeSEcarteDeSonAccentTest` | oui | 2 |
| A5p cle inconnue, item 1 | `AucuneCleDUnPaquetServiNEstIgnoreeDeToutLeDepotTest` | oui | 1 |
| A5d cle inconnue, dernier item | `AucuneCleDUnPaquetServiNEstIgnoreeDeToutLeDepotTest` | oui | 1 |
| A6p dette typographique en hausse, item 1 | `AucunSigneDoubleNEstDetachableDeSonMotTest` | oui | 1 |
| A6d dette typographique en hausse, dernier item | `AucunSigneDoubleNEstDetachableDeSonMotTest` | oui | 1 |
| A7p notation ASCII, scalaire a guillemets, item 1 | `LeTexteServiPorteLeCaractereDecideJamaisSaNotationTest` | **non** | 0 - mutation hors perimetre, section 3.4 d) |
| A7d notation ASCII, scalaire a guillemets, dernier item | `LeTexteServiPorteLeCaractereDecideJamaisSaNotationTest` | **non** | 0 - idem |
| A7bis notation ASCII, scalaire a apostrophes | `LeTexteServiPorteLeCaractereDecideJamaisSaNotationTest` | oui | 1 |
| A8p part designee en double, item 1 | `UnePartDesigneeApparaitUneSeuleFoisDansSonEnonceTest` | oui | 1 |
| A8d part designee en double, dernier item | `UnePartDesigneeApparaitUneSeuleFoisDansSonEnonceTest` | oui | 1 |
| A9d ecran du dernier item copie du premier | `AucunItemNeRepeteLEcranDUnAutreItemDuMemePaquetTest` | oui | 2 |
| A10d explication retiree, dernier item | `ChaquePaquetExpliqueSesItemsAVariationTest` | oui | 1 |
| A11p trou bouche, item 1 | `ChaquePaquetATrousPorteSonTrouSurChaqueItemTest` | oui | 1 |
| A11d trou bouche, dernier item | `ChaquePaquetATrousPorteSonTrouSurChaqueItemTest` | oui | 1 |
| A12p formes acceptees hors bareme, item 1 | `LesFormesAccepteesDUnPaquetDeSaisieServiSontLeBaremeReelTest` | **non** | 0 - mutation hors perimetre, section 3.4 e) |
| A12d formes acceptees hors bareme, dernier item | `LesFormesAccepteesDUnPaquetDeSaisieServiSontLeBaremeReelTest` | **non** | 0 - idem |
| A13 rang de la bonne reponse rendu predictible | `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` | oui | 2 |
| A14 paquet tronque sous le nombre de manches | `ChaqueJeuDuCatalogueOffrePlusDItemsQueDeManchesTest` | oui | 2 |
| A15 reponse toujours dans la premiere phrase (CP) | `AucunePhraseDuTexteSupportNeSuffitAGagnerLaMajoriteTest` | oui | 2 |
| A16 reponse toujours dans la premiere phrase (CM1) | `AucunePhraseDuTexteSupportNeSuffitAGagnerLaMajoriteTest` | oui | 2 |
| A17p enonce n'est plus le premier champ, item 1 | `ChaquePaquetPorteUnIndiceTest` | oui | 8 |
| A17d enonce n'est plus le premier champ, dernier item | `ChaquePaquetPorteUnIndiceTest` | oui | 8 |
| A18d enonce n'est plus le premier champ, paquet de saisie | `ChaquePaquetPorteUnIndiceTest` | oui | 8 |
| B1 graphie de matiere divergente sur un paquet | `UneMatiereNAQuUneSeuleGraphieDansLeContenuServiTest` | oui | 4 |
| B2 doublon d'entree de catalogue, en queue | `AucuneEntreeDuCatalogueNEnDoubleUneAutreTest` | oui | 1 |
| B3 doublon d'entree de catalogue, en tete | `AucuneEntreeDuCatalogueNEnDoubleUneAutreTest` | oui | 2 |
| C1 anneau de focus retire des pressables | `UnPressableFocaliseAuClavierAfficheUnIndicateurVisibleTest` | oui | 5 |
| C2 plancher de taille enfonce sur le texte support | `TailleDesTextesEtBoutonsTest` | oui | 1 |
| C3 balise viewport retiree | `ViewportEtLargeurTelephoneTest` | oui | 1 |
| C4 ressource tierce hors du repertoire vendorise | `RessourcesTiercesTest` | oui | 1 |
| C5 favicon retire | `FaviconTest` | oui | 1 |
| C6 texte de gabarit change sans arbitrage | `TexteAccentueDesGabaritsTest` | oui | 1 |
| C7 comportement en JavaScript inline | `FrontieresDuJavaScriptTest` | **non** | **0 - trou reel, section 3.4 a)** |
| C8 rognage installe sur un ancetre de pressable | `LHabillageNeRogneJamaisUnElementPressableTest` | oui | 2 |
| C9 habillage anime sous le doigt | `LHabillageResteImmobileTest` | oui | 2 |
| C10 texte dans un sous-arbre masque | `AucunTexteNeVitDansUnSousArbreMasqueTest` | oui | 2 |
| C11 couleur de position recouverte par un etat | `UneCouleurDePositionSurvitATousLesEtatsTest` | oui | 1 |
| C12 sortie plus saillante que l'action | `LActionNeDeclarePasMoinsQueLaSortieTest` | oui | 2 |
| C13 le gabarit lit le descripteur du jeu | `LeChoixDesJeuxNeVitPasDansLaVueTest` | oui | 2 |
| C14 etat de pressable abandonne au tiers | `AucunEtatDePressableNEstAbandonneAuTiersTest` | oui | 1 |
| D1 la fabrique de saisie perd une forme acceptee | `LesFormesAccepteesDUnPaquetDeSaisieServiSontLeBaremeReelTest` | oui | 2 |
| D2 la fabrique de saisie ampute le dernier item | `LesFormesAccepteesDUnPaquetDeSaisieServiSontLeBaremeReelTest` | **non** | **11 - trou reel, section 3.4 b)** |
| D3 la fabrique de question perd l'indice | `ChaquePaquetPorteUnIndiceTest` | oui | 15 |
| D4 la fabrique de question perd le dernier distracteur | `AucunePropositionNEstEgaleAUneAutreDansUnItemTest` | oui | 28 |
| D5 la fabrique de question perd le texte support | `AucunePhraseDuTexteSupportNeSuffitAGagnerLaMajoriteTest` | oui | 3 |
| D6 la fabrique de question perd la part designee | `UnePartDesigneeApparaitUneSeuleFoisDansSonEnonceTest` | **non** | **1 - trou, section 3.4 c)** |
| D7 la fabrique de question ampute le dernier item | `ChaqueJeuDuCatalogueOffrePlusDItemsQueDeManchesTest` | oui | 34 |
| D8 la fabrique de question perd l'explication | `ChaquePaquetExpliqueSesItemsAVariationTest` | oui | 1 |

**Sondes de mise a l'epreuve des reparations du jour**, hors des 55 :

| Sonde | Etat de l'arbre | Verdict |
|---|---|---|
| E5 | revert de `0fe674a` seul, **aucune mutation** | 1 classe rouge - la reparation d'`ADR-0072` est portante |
| E6 | revert de `84d6311` seul, **aucune mutation** | 1 classe rouge - le revert seul suffit a rougir, ce qui **invalide** les sondes E1 a E4 et la comparaison qu'elles pretendaient etablir (section 3.4 b) |
