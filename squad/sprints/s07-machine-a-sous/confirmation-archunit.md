# [Sprint s07] Confirmation ArchUnit et jugement de conformite - theo-archi

- **Date** : 2026-08-10
- **Auteur** : theo-archi
- **Arbre juge** : `round-two`, commit `d53181b` - l'increment du sprint fusionne (mon lot
  `ADR-0026`/`ADR-0027`, le lot `camille-po`, les deux arbitrages de `nadia-prof`, la proposition
  d'`ines-design`, le planning de `sofia-sm`, le lot `marc-dev` et le lot `lea-dev`) - **plus les
  fichiers du present commit `[THEO]`** : deux corrections a mon propre garde-fou, leurs trois jeux
  fautifs, l'amendement d'`ADR-0027` et deux lignes au tableau de corrections d'`ADR-index.md`.
- **Provisionnement** : worktree dedie **hors de l'arbre du depot**, forke de `round-two`. Lignee
  verifiee avant toute lecture, par le script et non a la main :
  ```
  $ outils/verifier-lignee.sh
  le worktree sur la branche 'round-two' descend de la lignee de round-two (ancetre commun trouve
  dans son historique).
  ```
  Le script a ete corrige deux fois depuis mon signalement de s06 ; il rend desormais le bon verdict
  par un test d'ancetre commun, ce qui couvre les trois formes saines - worktree neuf sur la tete,
  worktree dont la base a avance depuis le provisionnement, et branche de meme nom.
- **Prerequis** : `JAVA_HOME` sur un JDK 21, positionne pour la duree de chaque commande et jamais
  ecrit dans un fichier commite. Constat citable rendu par `outils/verifier-jdk.sh` sur ce poste :
  `java du PATH : 1.8.0_202 | JDK de compilation : 21.0.10 | JAVA_HOME : vide | toolchains lu par
  Maven : non (fichier present sous un nom non lu)`. **Toutes les mesures de ce document sont prises
  sur le JDK 21.0.10**, jamais sur le `java` du `PATH`, qui ne compile pas.
- **Ce que je ne fais pas ici** : aucun Done pedagogique ni son anticipation - il appartient a
  `nadia-prof` seule. Aucun arbitrage produit. Aucune reouverture de la decision de capacite de
  `sofia-sm` sur `DEC-0016`. Aucune ligne dans `rapport-qualite.md`, `squad/conformite/` ou
  `docs/backlog/backlog.md`.

## 1. Le resultat de la commande

`mvn -B clean verify`

```
recreo ............................................. SUCCESS [  0.198 s]
recreo-domain ...................................... SUCCESS [  7.936 s]
recreo-application ................................. SUCCESS [  1.494 s]
recreo-adapter-web ................................. SUCCESS [ 15.337 s]
recreo-adapter-content ............................. SUCCESS [  2.477 s]
recreo-bootstrap ................................... SUCCESS [ 29.418 s]
BUILD SUCCESS
Total time:  57.249 s
```

**314 tests, 0 echec, 0 erreur, 0 ignore.**

**Methode de ce comptage, dans le meme paragraphe que le chiffre.** Somme de l'attribut `tests` de la
balise racine `<testsuite>` des **55 rapports XML** produits par cette seule execution - **52** sous
`*/target/surefire-reports/` et **3** sous `*/target/failsafe-reports/`, soit 311 cas unitaires et 3
cas d'integration. Perimetre parcouru : les repertoires `target` des cinq modules declares dans
`<modules>` du pom agregateur, jamais un balayage lance depuis la racine du depot. **Exclu
explicitement** : les rapports `.txt` de Surefire, qui ne conservent qu'un seul moteur JUnit par
classe et donnaient 221 au lieu de 228 en s05 ; et toute execution anterieure, puisque **le comptage
est fait apres le `clean` de cette commande** - une suite mutee laisse des rapports qui produisent un
total juste avec des echecs faux, et je m'y suis pris moi-meme une fois au sprint 5. Repartition :
`adapter-web` 153, `bootstrap` 73 + 3, `domain` 43, `adapter-content` 35, `application` 7.

**Rapprochement avec le chiffre de `sofia-sm`, qui n'est pas le meme et doit rester lisible.** Elle
mesure **310** sur `d53181b` ; je mesure **314** parce que je juge `d53181b` **plus mon commit**, qui
ajoute quatre cas a `adapter-web` (un cas de production pour la clause 8, trois cas de morsure). Sur
`d53181b` seul, avec la meme methode, j'ai mesure **310** : les deux comptages concordent sur le meme
arbre, et l'ecart est entierement le mien. Les deux chiffres sont comparables parce qu'ils sont faits
de la meme facon, non fusionnes.

Aucun echec rencontre en chemin sur l'execution finale. Les echecs que ce document cite sont tous
**provoques** par des mutations que j'ai ecrites et defaites, section 3.

## 2. Les regles reellement executees, et ce que ce nombre ne mesure pas

**28 `ArchRule` declarees, 28 reellement executees, dont 27 par le moteur ArchUnit et 1 par un
`@Test`.** Inchange depuis la cloture de s06.

**Methode, perimetre, source de la liste, exclusions - dans ce paragraphe.** Perimetre : les fichiers
`.java` **suivis par Git** (`git ls-files '*.java'`), quel que soit le module ; critere : une
declaration `static final ArchRule`. Le comptage brut rend **30**, dans douze fichiers. Exclusion, une
seule et elle est nommee : les **2** declarations de
`bootstrap/src/test/resources/contreexemple/LesGardeFousSontMisALEpreuveTest/faux-module/src/test/java/com/recreo/faux/RegleContreExemple.java`,
qui est une **ressource de test** - le faux module que `LesGardeFousSontMisALEpreuveTest` balaie pour
se mettre lui-meme a l'epreuve, jamais compile ni execute comme regle du depot. Reste **28**, dans
onze classes reelles, aucun nom en double. Trois pieges ecartes, ce sont ceux qui m'ont fait annoncer
29 regles pour 25 reelles en s05 :

1. **Le faux depot de contre-exemple**, ci-dessus.
2. **Les worktrees d'agents.** Ils vivent hors de l'arbre du depot depuis la retro de s05 ; partir de
   `git ls-files` protege de toute facon d'un depot ou ils vivraient dedans.
3. **Les occurrences en prose.** Le mot `ArchRule` apparait dans les `ADR` et dans `squad/` ; ces
   fichiers ne sont pas dans le perimetre.

**Deux faits de la cloture de s06, repris et remesures ici plutot que redecouverts.**

- Le depot compte **27 champs `@ArchTest` pour 28 `ArchRule`** (mesure : meme perimetre, critere
  `@ArchTest`). La 28e est
  `ScanDeComposantsTest.aucuneClasseDeTestSurLeClasspathDeLApplicationNePorteDeStereotype`
  (`ADR-0021`), declaree en `ArchRule` et executee par un `.check(...)` explicite depuis **deux**
  methodes `@Test`, parce qu'elle a besoin d'un jeu de classes construit a la main. **Un comptage par
  `@ArchTest` annoncerait 27** ; ce n'est pas une regle oubliee. Que les 28 soient toutes passees a un
  `.check(` n'est pas verifie par moi mais par
  `LesGardeFousSontMisALEpreuveTest.aucuneRegleArchUnitNEchappeAuContreExemple`, qui est vert.
- **Un balayage du disque apres `verify` rend 32**, pas 28 ni 30 : `bootstrap/target/test-classes/`
  recoit une copie des ressources de test, faux module compris. Mesure : 32 occurrences dans 13
  fichiers, dont 2 dans la copie de `target/`. C'est exactement la famille de divergence qui fait
  qu'un chiffre sans methode ne vaut rien.

**Ce que ce nombre ne mesure pas, et c'est le point le plus important de cette section.** Le compte de
regles ArchUnit **n'est pas** la mesure de la couverture de garde-fous du sprint, et le lire ainsi
donnerait exactement le faux. Il vaut **28 avant et 28 apres** : mesure au commit de cloture de s06
(`28a419f`) et a `HEAD`, meme methode aux deux, `git grep -h "static final ArchRule"` rend 30 brut aux
deux revisions. **Le sprint 7 n'a ajoute aucune `ArchRule`**, et il a ajoute :

| Garde-fou de s07 | Auteur | `ArchRule` | Cas | Ce qu'il garde |
|---|---|---|---|---|
| `LAnimationRetardeMaisNeDetermineRienTest` | theo-archi | 0 | 21 | ADR-0026 clauses 1 a 4, ADR-0027 clauses 5 a 8 : ce qu'une feuille maison declare du mouvement, du texte et de la decoupe |
| `LEcranDeJeuDeclareCommentIlEstArriveTest` | theo-archi | 0 | 6 | ADR-0027 : sur la sortie reelle du serveur, aucun ecran de jeu arrivant par un chargement de page ne declare `par-un-geste` |
| `AucunIndiceNeContreditUnAutreItemDuMemePaquetTest` | marc-dev | 0 | 3 | la coherence interne d'un paquet : aucun indice general non tempere n'est dementi par la bonne reponse d'un autre item du meme paquet |
| `FrontieresDuJavaScriptTest`, amende | theo-archi | 0 | 3 | ADR-0026 regle 2 : le vocabulaire du tirage rejoint celui du verdict |
| `TailleDesTextesEtBoutonsTest`, amende | theo-archi puis lea-dev | 0 | 19 | l'ecart entre deux reponses sur les **deux** axes, et le repli en deux rangees scope a la quatrieme proposition |
| `PartieControllerTest`, amende | lea-dev | 0 | 21 | S7-2 : le nombre de propositions rendu suit le contenu, mesure a 2, 3 et 4 |

**Aucun n'est une `ArchRule`, et aucun ne pouvait l'etre** : ArchUnit lit du bytecode, il ne lit pas
une feuille de style, ne rend pas une reponse HTTP et ne compte pas les items d'un YAML. Les deux
`ADR` du sprint le disent chacun dans leur section executable. **Le bon resume d'une phrase : le
sprint a ajoute zero regle ArchUnit et trois classes de garde-fou, plus quatre amendements de
garde-fous existants.**

## 3. Jugement de conformite - le lot de `lea-dev` aux contrats `ADR-0026` et `ADR-0027`

Juge sur le code de `d53181b`, pas sur son rapport. **Verdict : conforme sur les quatre points que
`sofia-sm` me demande, et il decouvre deux trous dans mon propre garde-fou** - que je ferme dans ce
commit plutot que de les laisser en prose.

### 3.1 `clip-path` et la clause "jamais un autre mot" : sa forme est meilleure que la mienne, et ma clause ne la tenait pas

**Sur le fond, elle a raison, et son argument est exact.** `clip-path: inset(...)` sur
`.proposition::after` decoupe **la boite du voile**, qui est une boite generee a cote du texte et non
le texte. Le mot du serveur n'est ni efface, ni remplace, ni rogne a aucun instant, et les images cles
font varier `top` et `bottom` ensemble de sorte que la bande reste dans la boite - `from { inset(0 0
80% 0) }` a `to { inset(80% 0 0 0) }`. La forme est **plus sure** que `transform` avec
`overflow: hidden`, qui aurait fait vivre le mot dans une fenetre decoupee et posait la question du
focus clavier hors zone visible que j'avais nommee dans `ADR-0026`. Elle n'a ajoute aucun element
d'habillage et aucune classe pour l'obtenir. Rien a reprocher a la decision.

**Sur le garde-fou, non : ma clause ne la couvrait pas, et le residu que j'avais nomme n'est pas le
bon.** J'ecrivais dans `ADR-0027` section 5 que "jamais un autre mot" etait gardee sur "les deux
seules formes qu'un rouleau reel peut prendre" - un leurre rendu par le serveur, un mot fabrique par
`content` - et que le residu etait **un mot peint dans une image**.

Reponse directe a la question posee : **`clip-path` n'est pas un cas voisin de ce residu, c'en est un
different et plus proche du defaut.** Un mot peint dans une image est un mot que le depot devrait
d'abord decider d'introduire, et aucun automate ne lirait une image ; une decoupe geometrique, elle,
ne fabrique aucun texte mais **en retranche**, et un mot retranche peut etre un autre mot. Ce n'est
pas une hypothese de laboratoire : sur `cm1-homophones`, dont `ADR-0026` releve les deux propositions
`sont` et `son`, une decoupe laterale rend la bonne reponse identique a la fausse. Et le defaut est a
**un caractere** de la production : le `::after` retire du selecteur.

**Mesure, pas raisonnement.** Mutation reelle de `recreo.css` a `d53181b`, ajout de
`.proposition { clip-path: inset(0 45% 0 0) }` : **149 cas verts sur 149**. Ni la clause 3 (qui
n'enumere que `display`, `visibility`, `pointer-events`, `opacity`), ni la clause 7 (qui ne lit que
`content`), ni aucun autre garde-fou du depot ne bronchait. Production restauree.

**Ferme par la clause 8**, dans `LAnimationRetardeMaisNeDetermineRienTest` : aucune regle maison
permanente ne declare `clip-path` sur une classe portee par un `[data-role=action]` dont le **sujet**
du selecteur - son dernier compose, combinateurs cherches hors des crochets - n'est pas `::before` ou
`::after`. Morsure remesuree apres correction : la meme mutation rend maintenant

```
recreo.css : .proposition : cette regle declare clip-path sur [proposition], des classes que porte un
bouton d'action, et son sujet est l'element lui-meme et non un pseudo-element decoratif [::before,
::after]. Elle retranche donc une partie du mot que le serveur a envoye, et un mot retranche peut
etre un autre mot : sur cm1-homophones, une decoupe laterale transforme "sont" en "son", c'est-a-dire
la bonne reponse en la fausse
```

Et le jeu fautif `voile-rogne-sans-toucher-le-texte` prouve que la forme de `lea-dev` reste **verte** :
sans ce cas, la clause serait une interdiction de `clip-path` deguisee en clause de lisibilite, et
aucun jeu fautif ne le dirait. Ce que la clause ne ferme pas est ecrit dans l'amendement d'`ADR-0027`
avec son motif : `overflow: hidden`, parce qu'`ADR-0026` accorde nommement cette latitude a
`ines-design` et que la fermer ici retirerait une latitude que j'ai donnee ; et la troncature par
largeur, qui est la famille de debordement qu'`ADR-0025` a declaree non gardable.

### 3.2 Le second trou, trouve en mutant sa production : la barre de duree ne voyait aucun de ses retards

Ce point n'etait pas dans la liste de `sofia-sm`, et c'est le plus grave des deux.

`lea-dev` ecrit le decalage entre colonnes dans **une regle par colonne** :
`[data-arrivee=par-un-geste] .proposition:nth-child(2)::after { animation-delay: 0.08s; }`, et de meme
pour la troisieme et la quatrieme. C'est la seule facon de l'ecrire : un decalage par colonne ne peut
pas vivre dans un raccourci `animation` partage. Ces regles ne declarent pas `animation:`, elles
n'entraient donc pas dans la population des animations de ma clause 6, et **aucun de ces trois retards
n'etait confronte au plafond de 0,25 s de `nadia-prof`**.

**Mesure** : `animation-delay: 3s` sur la quatrieme colonne de `recreo.css`, c'est-a-dire 3,6 s
d'attente entre l'appui de l'enfant et l'arret de la derniere colonne, paye dix fois par partie -
**149 cas verts sur 149**. Production restauree.

Mon jeu fautif `decalage-trop-grand` ecrivait son retard **dans le raccourci**. C'est la troisieme
occurrence dans ce depot de la lecon de s05 : *une fixture ecrite par l'auteur d'une regle ressemble a
ce qu'il imagine, pas a ce que le monde ecrit.* La valeur de `lea-dev` etait conforme (0,24 s au plus,
et 0,84 s de total) ; c'est le garde-fou qui ne la verifiait pas. Ferme par
`verifierLesRetardsEcritsAPart` : tout `animation-delay` d'une feuille maison hors `@keyframes` et
hors repli est confronte a 0,25 s, et **duree la plus longue plus retard le plus long** au plafond de
1,2 s - une borne superieure, le doute jouant dans le sens du controle (`ADR-0013` obligation 2).
Morsure remesuree : la meme mutation rend **2 defauts** dans la meme assertion, `retard de 3.0s,
au-dela du decalage maximal de 0.25s` et `duree la plus longue + retard le plus long = 3.6s, au-dela
du plafond de 1.2s`. Jeu fautif neuf : `retard-ecrit-a-part`, qui ecrit le retard la ou la production
l'ecrit.

### 3.3 L'epinglage supprime : le motif tient, et la regle n'a perdu aucun perimetre

Elle a supprime `aucuneAnimationNEstEncoreDeclareeEnProduction`. **Confirme, sur les deux questions.**

Le motif est exactement celui que le message de l'epinglage prescrivait, mot pour mot : *"la premiere
animation maison vient d'entrer dans le depot [...] supprime cet epinglage, elles portent desormais
sur un objet reel"*. Le rouleau de `.proposition::after` **est** la premiere animation maison du
depot - verifie et pas suppose : la population des animations de production, qui etait vide quand j'ai
ecrit la regle, contient aujourd'hui exactement une regle, et c'est celle-la. L'epinglage affirmait
que ce compte valait **zero** ; le garder aurait rendu la suite rouge sur un fait vrai. Son plafond
valait exactement la taille de la dette, zero, ce qui est pourquoi il rougit du premier coup.

**Aucun perimetre perdu**, et je le verifie sur le code plutot que sur le motif : les clauses 1 et 2
n'etaient pas portees par l'epinglage, elles etaient portees par `animations(RACINE_PRODUCTION)`, que
`lea-dev` n'a pas touche. L'epinglage ne verifiait rien d'autre que la vacuite de cette population -
c'etait son seul objet. Ce que le depot perd, il faut le dire : **il n'y a plus d'automate qui
avertirait si cette population redevenait vide** un jour, et les clauses 1, 2, 5 et 6 repasseraient
alors vertes a vide en silence. Ce n'est pas un defaut du lot de `lea-dev`, c'est la contrepartie
normale d'un epinglage consomme ; et la clause 8 que j'ajoute porte, elle, son propre epinglage de
non-vacuite (`examinees` non vide, avec le message qui dit quoi faire le jour ou le voile disparait).
Je prefere cette forme a un epinglage inverse "au moins une animation existe", qui serait la meme
chose ecrite deux fois.

### 3.4 `.proposition` devient la colonne : `ADR-0020`, `ADR-0017` et les classes orphelines

Verifie sur les trois, en lisant les gabarits et le CSS, pas seulement en constatant du vert.

- **`ADR-0020`, classes et jamais d'identifiants** : le lot de `lea-dev` **ne touche aucun gabarit** -
  son diff porte sur `recreo.css` et trois classes de test. Aucun identifiant n'entre nulle part, et
  le fragment de manche continue de ne pas reprendre l'identifiant de la zone qui le recoit. La
  contrainte 2 de la section 5 d'`ADR-0026` est tenue trivialement, parce qu'elle a fait mieux que ce
  que j'avais prevu : `.propositions` et `.proposition` existaient deja, elle n'a introduit **aucune
  classe** et **aucun element d'habillage**. `flex: 1 1 0` sur le bouton lui-meme.
- **`HabillageDesEcransTest.aucuneClassePoseeSurUnElementNEstOrpheline`** : ce garde-fou juge dans le
  sens gabarit vers feuille - toute classe posee sur un element doit etre nommee par une regle servie.
  Un lot qui n'ajoute que des regles CSS ne peut pas creer d'orpheline **par construction** ; c'est
  d'ailleurs ce garde-fou qui m'avait refuse la premiere version du marqueur d'arrivee en classe et
  m'a fait choisir un attribut. Vert, et pour la bonne raison.
- **`ADR-0017`, saillance comparee par `data-role`** : la contrainte 1 de la section 5 d'`ADR-0026`
  est tenue - l'element cliquable reste le `<button type="submit" class="btn proposition"
  data-role="action">`, la colonne **est** ce bouton. Aucune valeur de `data-role` n'apparait ni ne
  disparait, aucune couleur ni taille de police ne change : ce que le garde-fou compare est
  inchange, et les trois epinglages de couples restent verts sans qu'une ligne bouge. Le levier prend
  `border-radius`, un `box-shadow` et un disque decoratif en `::before` a `content: ""` - aucune de
  ces trois proprietes n'entre dans la comparaison de saillance, et aucune ne touche la cible
  tactile.
- Un point mesure au passage plutot que suppose : `border-radius: 999px` sur `.demarrer` et `.encore`
  est bien une valeur en pixels au-dela de 375, et **ce n'est pas une violation** de la contrainte 3
  de la section 5. La population de `ViewportEtLargeurTelephoneTest` est `width`, `min-width`,
  `max-width` - lu dans le code, `isLargeurFixeeOuBornee` - et un rayon d'angle n'est pas une largeur.
  Les dimensions du disque decoratif, elles, sont en `rem` ; la colonne est en pourcentage
  (`calc(50% - 0.5rem)`).

### 3.5 Deux stories sans code : une verifiee, une seulement partiellement, et l'ecart n'est pas de son fait

**S7-4 a demande du code**, et le dire autrement serait faux : `recreo.css` gagne trente lignes pour
l'apparence du levier (`.demarrer`, `.encore`, leur `::before`). Ce que S7-4 n'a demande, conformement
a la section 3 d'`ADR-0026`, c'est **aucune route, aucun etat de session, aucune valeur de `data-role`,
aucun bouton**. Ses trois criteres sont verifies par des tests preexistants que le lot n'a pas
touches : les deux routes qui ouvrent une manche sont inchangees, et
`PartieControllerTest.unIndiceApparaitApresTroisEchecsSurLeMemeItem` tient le troisieme critere - le
declenchement de l'indice apres trois echecs n'est pas modifie. Story satisfaite, verification reelle.

**S7-3 n'a demande aucune ligne, et sa verification est partielle.** Son premier critere - l'enonce est
visible avant qu'on doive choisir - est verifie par des assertions preexistantes qui lisent le texte
de `.enonce` sur le fragment de manche et sur la page de reprise ; l'enonce et les propositions
arrivent dans **la meme reponse**, ce qui est la propriete demandee et elle est gardee. Son second
critere - l'enonce reste affiche **a l'identique, sans animation ni modification**, pendant tout le
defilement - est **vrai en fait et garde par personne** : c'est vrai parce que la seule animation du
depot vise `.proposition::after`, et rien n'interdirait a la prochaine regle de viser `.enonce`. Je ne
l'ecris pas comme garde-fou et je dis pourquoi : ce serait une regle contre un defaut que le depot n'a
pas etabli, et le critere appartient a une story dont `yanis-qa` et `nadia-prof` tiennent la porte, pas
a mes contrats. **Destinataire nomme** : `yanis-qa` pour la verification de la story, `nadia-prof` pour
le confort de lecture pendant le mouvement. Le declencheur qui me ferait l'ecrire : une deuxieme
animation maison visant autre chose que `.proposition::after`.

Un dernier point de forme, sans consequence sur le fond : le nom de la methode
`leReplniEnDeuxRangeesEstScopeALaQuatriemePropositionSeulement` porte une coquille (`Replni`). Ce n'est
ni une violation de contrat ni un defaut de garde-fou, je le signale et je n'y touche pas - ce n'est
pas mon lot.

Une remarque de perimetre que je remonte sans la trancher : le premier critere de S7-3 dit "avant meme
que le levier ne soit tire", ce qui n'est satisfiable que sur la forme de levier que j'ai **refusee**
dans `ADR-0026` section 3 - sur la forme retenue, l'ecran de manche n'existe pas avant l'appui. La
lettre du critere et la decision d'architecture ne se recouvrent pas ; l'esprit, si. Question de
redaction de story, donc `camille-po` et `sofia-sm`, pas moi.

## 4. Jugement de conformite - le lot de `marc-dev` a `ADR-0013`

**Verdict : conforme sur les obligations 1 et 3, et il porte un defaut d'`ADR-0003` que je nomme sans
le corriger a sa place.**

- **Obligation 1, perimetre derive** : tenue, et verifiee dans le code. `paquets(Path racine)` balaie
  `racine.resolve("contenu")` et retient tout `*.yaml` ; aucune liste de paquets n'est ecrite. Un
  paquet neuf portant un indice general dementi est juge des qu'il existe, sans qu'on inscrive son nom
  nulle part. L'assertion de non-vacuite sur cette population est presente. Le `Pattern` qui reconnait
  la forme fautive est une **liste de fautes a reprocher**, ce que l'obligation 1 autorise nommement
  au meme titre qu'un vocabulaire interdit - ce n'est pas une liste d'objets a controler.
- **Obligation 3, mise a l'epreuve** : tenue, et sur un objet reel. Trois cas : le jeu fautif qui
  reproduit la contradiction manche 7 / manche 10, le meme jeu corrige qui redevient vert - sans quoi
  la regle pourrait etre insatisfiable - et la clause de production. J'ai remesure sa **morsure sur le
  fichier de production** plutot que sur parole : en reintroduisant le seul indice de la manche 7 dans
  `cm1-conjugaison-present.yaml`, la suite rend **1 echec sur 35** avec le message
  `paquet cm1-conjugaison-present : l'indice de "Tu ___ un dessin." enonce une regle generale
  (terminaison '-es') que la bonne reponse "finis" de l'item "Tu ___ ton exercice avant la
  recreation." dement`. C'est exactement le defaut que `nadia-prof` a trouve en jouant, et le
  garde-fou le nomme item par item. Production restauree.
- **Ce qui n'est pas tenu, et c'est `ADR-0003` plutot qu'`ADR-0013`** : la clause de production est
  **verte sur une population vide**, et rien ne le dit. Mesure et methode : le motif
  `[Aa]vec '...', le verbe se termine par '-...'` applique aux quatre paquets de
  `adapters/adapter-content/src/main/resources/contenu/` rend **0 correspondance** - c'est mecanique,
  puisque la correction du meme commit a justement fait disparaitre cette forme des cinq indices
  fautifs. Le garde-fou peut mordre, il est prouve mordant, et il ne controle **rien** sur l'arbre
  livre. C'est la situation que j'ai rencontree moi-meme sur les clauses 1 et 2 d'`ADR-0026` et pour
  laquelle j'ai ecrit un epinglage dont le plafond valait exactement zero. Ici il n'y en a pas.
- **Ce que j'en fais** : je le nomme, je ne l'ecris pas a sa place, et je ne bloque pas la porte
  technique pour cela. Motif : la propriete gardee est reelle, la morsure est prouvee sur la
  production, et l'ecart est un epinglage manquant sur le garde-fou d'un autre - le corriger moi-meme
  au moment de la porte technique le priverait du constat. **Dette ouverte, destinataire `marc-dev`,
  contenu exact** : une assertion qui affirme que le nombre d'indices de production reconnus par le
  motif vaut exactement **zero** aujourd'hui, avec le message qui dit de la supprimer le jour ou ce
  n'est plus vrai. Une ligne, la meme forme que celle que j'ai ecrite en s07 et que `lea-dev` a
  consommee ce sprint.

## 5. Clean Architecture et plafonds de dette apres la fusion complete

- Les regles de couches et de purete sont vertes. Le sprint n'a fait entrer **aucun code de
  production dans `domain` ni dans `application`** : les deltas de production sont `recreo.css`,
  `fragments/manche.html`, `PartieController` et un fichier de contenu YAML. `AucunVocabulaireDeMatiereTest`
  reste vert alors que `marc-dev` a ecrit du vocabulaire de grammaire - il vit dans un YAML de
  `adapter-content` et dans un test, pas dans le domaine, ce qui est la frontiere qu'`ADR-0024` a
  posee et qu'elle tient un sprint de plus sans intervention.
- **Le plafond de dette de balayage vaut 2**, et la dette compte exactement deux noms (`FaviconTest`,
  `FrontieresDuJavaScriptTest`). Le plafond vaut la taille de la dette, pas une place de libre.
- **Le plafond de dette de contenu vaut 0** (`ADR-0016`), et le paquet corrige par `marc-dev` est
  passe sans y entrer.
- **L'epinglage d'`ADR-0026` a ete consomme, pas contourne** : c'est le seul plafond du depot dont la
  valeur a change ce sprint, il valait zero, l'objet est apparu, il a rougi, il a ete supprime avec son
  motif ecrit a deux endroits. C'est le cycle de vie qu'on attend d'un epinglage, et c'est la premiere
  fois qu'il se produit en entier dans ce depot.

## 6. Avis : porte architecture **ouverte**

La compilation, les tests unitaires, les tests d'integration Spring Boot, les 28 regles ArchUnit et
les garde-fous de balayage sont verts sur l'arbre fusionne plus ce commit, avec les chiffres et les
methodes ci-dessus. Les deux lots respectent les contrats sur lesquels ils ont ete ecrits. Les deux
ecarts que ce jugement trouve sont dans **mon** garde-fou, pas dans leur code, et ils sont fermes ici
avec leur morsure mesuree avant et apres.

Une dette ouverte et nommee, qui ne bloque pas cette porte : l'epinglage de non-vacuite manquant sur
`AucunIndiceNeContreditUnAutreItemDuMemePaquetTest`, destinataire `marc-dev`, contenu exact en
section 4.

**Ce n'est pas le Done du sprint.** Le Done pedagogique appartient a `nadia-prof`, qui juge en jouant,
et ce sprint lui laisse nommement : la courbe et le rebond du defilement, le lien percu entre l'appui
et le mouvement, la lisibilite d'un mot dans une colonne de 101 px, l'ecran obtenu sous reduction de
mouvement, le residu du mot peint dans une image, et le texte des cinq indices corriges.

## 7. Une phrase pour le planning suivant, sur `DEC-0016`

Je n'ecris pas son mecanisme de garde, conformement a l'arbitrage de capacite de `sofia-sm`, et je
donne l'element de chiffrage qu'elle attend : **outiller la mesure a 360 x 640 est peu couteux** - la
hauteur est aujourd'hui la seule des deux dimensions que `outils/mesurer-rendu.sh` ne prend pas en
parametre - **mais en faire un automate rouge ou vert est couteux et probablement a refuser**, parce
que "la reponse est au-dessus du pli" est une propriete de pixels rendus, c'est-a-dire exactement la
famille qu'`ADR-0025` a declaree non gardable par un automate de ce depot.
