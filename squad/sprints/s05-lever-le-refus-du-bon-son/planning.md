# [Sprint s05] Planning - 2026-08-06

- **Horodatage de debut** : 2026-08-06 13:55
- **Anime par** : sofia-sm
- **Participants** : theo-archi, camille-po, lea-dev, marc-dev, yanis-qa, sofia-sm. `ines-design`
  et `nadia-prof` sont citees pour les points qui les concernent, sans tache de code ce sprint.
- **Lu avant d'ecrire ce fichier** : `docs/backlog/backlog.md` (section Sprint 5, S5-1 a S5-8) ;
  `DEC-0009`, `DEC-0010`, `DEC-0011` ; `avis-faisabilite.md` de ce dossier et `ADR-0016` ;
  `squad/conformite/FICHE-bandit-manchot-le-bon-son-cp.md`,
  `FICHE-bandit-manchot-homophones-cm1.md`, `FICHE-bandit-manchot-additions-cp.md` ; les clotures
  de s03 et s04 (`review.md`, `retro.md` des deux dossiers).

## Objectif du sprint

**Le sprint reste entierement correctif (`DEC-0011`) : corriger le contenu de "Le bon son" sur
ses quatre motifs de refus restants (S5-2 a S5-5), completer "Le bon petit mot" jusqu'a la
condition ecrite par `nadia-prof` pour `couvert-valide` (S5-6, S5-7), executer `DEC-0010` (S5-8),
et livrer le garde-fou de hierarchie de saillance que `theo-archi` a chiffre comme faisable.**
Verifiable ainsi : a la review, (a) un jar construit avec "Le bon son" corrige et reintegre a son
seul catalogue a repasse devant `nadia-prof`, sans qu'aucune version de `round-two` livree pendant
le sprint n'ait jamais rendu ce jeu accessible depuis l'accueil (`DEC-0009` tenue a la lettre) ;
(b) `squad/conformite/FICHE-bandit-manchot-homophones-cm1.md` porte les cinq couples ajoutes et un
verdict de `nadia-prof` sur `couvert-valide` ; (c) `mvn verify` est vert et le titre rendu est
"Recreo" partout, avec son test ; (d) `HierarchieDeSaillanceTest` et son ADR existent sur le
disque.

**Gele** : aucune ligne de plus n'entre `en cours` dans `squad/programme/MATRICE-couverture.md`
(`DEC-0011`). Rien dans ce planning ne demande a `camille-po` d'ouvrir une matiere nouvelle.

## L'obstacle de gouvernance route par theo-archi - tranche ici

**`DEC-0009` retire "Le bon son" du catalogue jouable jusqu'a ce qu'un increment corrige repasse
devant `nadia-prof` et obtienne un Done pedagogique - mais `nadia-prof` audite en jouant, et un
jeu absent du catalogue n'est pas jouable.** Lu au pied de la lettre, le retrait interdit l'audit
qui doit le lever. `theo-archi` expose trois sorties (`avis-faisabilite.md`, section S5-2, "Ce qui
conditionne techniquement la remise au catalogue") et me rend le choix.

**Je tranche, sous ma casquette Scrum Master, sans ecrire de `DEC`** : ce n'est pas un arbitrage de
perimetre produit (la substance de `DEC-0009` - le jeu reste injouable sur `round-two` jusqu'au
Done pedagogique - n'est pas touchee), c'est une question de sequencement de fusion, exactement du
meme ordre que le choix des worktrees que j'ai deja tranche sans `DEC` ni `ADR` en s03. Je retiens
une version plus fine que les trois options de `theo-archi`, rendue possible par un fait qu'il a
lui-meme etabli dans `ADR-0016` : **le perimetre du garde-fou de dette et les tests de contenu
(`PaquetsFrancaisS04Test`, `ChaquePaquetPorteUnIndiceTest`) derivent du repertoire `contenu/`, pas
du catalogue.** La correction du paquet `cp-le-bon-son.yaml` (S5-2 a S5-4), l'ecriture de ses
indices et la fermeture de sa part de la dette d'`ADR-0016` (S5-5) peuvent donc fusionner sur
`round-two` **sans jamais reintroduire l'entree dans `catalogue.yaml`** : le jeu reste absent de la
liste servie a l'enfant, `DEC-0009` reste tenue a la lettre sur `round-two` a tout instant du
sprint.

**Seule la ligne d'entree dans `catalogue.yaml` reste hors de `round-two`.** Pour que `nadia-prof`
puisse jouer, `lea-dev` ouvre une branche jetable au-dessus du contenu corrige et deja fusionne,
n'y ajoute que cette ligne de catalogue, construit le jar depuis cette branche pour `nadia-prof`.
Deux issues, aucune n'exige de mecanisme nouveau ni de deuxieme etat de catalogue :

- **`VALIDE` ou `VALIDE SOUS RESERVE`** : la ligne de catalogue de cette branche jetable est
  fusionnee sur `round-two` dans le meme geste que la fiche de conformite qui porte le verdict ;
  `DEC-0009` est executee (le jeu revient), dans le meme commit que sa levee, comme l'amendement de
  la retro de s04 l'exige deja pour toute `DEC` qui change un comportement observable.
- **`REFUSE`** : la branche jetable est abandonnee, rien n'est fusionne, le catalogue de
  `round-two` reste exactement ce qu'il est aujourd'hui.

Si l'audit de `nadia-prof` n'a pas lieu avant la cloture de ce sprint, ce point reste **ouvert**
dans `review.md` plutot que d'etre declare clos par anticipation.

## Stories retenues

| Story | Titre | Competence visee | Affectee a | Statut au planning |
|---|---|---|---|---|
| S5-1 (residuel) | Second critere (reprise de session) | transverse | aucun code ce sprint | **garde volontairement ouvert** ; garde-fou de premisse optionnel, voir plus bas |
| S5-2 | Le jeu demande d'entendre un son | francais CP, graphemes/phonemes | lea-dev | codable pour le contenu ; trois criteres a corriger, voir tableau ci-dessous |
| S5-3 | Sons sans notation d'adulte | francais CP, meme ligne | lea-dev | prete ; mise a jour de `PaquetsFrancaisS04Test` obligatoire, doit preceder S5-2 |
| S5-4 | Un seul son vrai par item | francais CP, meme ligne | lea-dev | codable en contenu ; critere a reecrire en porte pedagogique seule |
| S5-5 (residuel) | Ecrire les indices de `cp-le-bon-son` (24) | transverse | lea-dev | prete ; `ADR-0016` deja tranche par theo-archi, reste le texte |
| S5-6 | Cinq couples d'homophones manquants | francais CM1, homophones grammaticaux | marc-dev | prete pour quatre couples ; `la/la/l'a` **bloque sur nadia-prof** |
| S5-7 | Variete entre deux parties CM1 | francais CM1, meme ligne | marc-dev | prete |
| S5-5 (residuel) | Ecrire les indices de `cm1-homophones` (10 existants + les nouveaux de S5-6) | transverse | marc-dev | prete |
| S5-8 | "Recreo" sans accent (`DEC-0010`) | transverse | sofia-sm | prete |
| (technique, routee par `theo-archi`, pas une story de `camille-po`) | Hierarchie de saillance : un bouton d'action ne declare pas moins qu'un bouton de sortie | transverse, pas de competence programme | theo-archi | prete ; `ADR-0017` et sa regle dans le meme commit |
| (technique, optionnel) | Garde-fou de premisse sur S5-1 (session persistante, Spring Session, `adapter-persistence`, catalogue rechargeable) | transverse | theo-archi | **retenu**, non bloquant, voir "Ce que je retiens de l'avis de theo-archi" |

`ines-design` n'a aucune story ce sprint : aucun critere visuel ou CSS n'entre en s05. Elle reste
disponible si `theo-archi` a besoin d'un avis sur le placement de l'attribut de role qu'il pose
sur les gabarits qu'elle a dessines, mais rien ne bloque sur elle.

## Ce que je retiens de l'avis de theo-archi

### Le garde-fou de hierarchie de saillance - affecte a theo-archi, prioritaire apres l'increment "Le bon son"

C'est le sujet le plus important du sprint apres la levee du refus : trois defauts visibles par un
enfant ont deja traverse une CI verte, et `nadia-prof` ecrit qu'en attendant elle est le garde-fou,
en jouant. `theo-archi` a chiffre le cout (environ 150 lignes de test, deux jeux d'artefacts
fautifs, un attribut de role sur huit boutons dans quatre gabarits) et refuse explicitement de
livrer une regle approximative sans ADR ni preuve de morsure. Je retiens sa recommandation telle
qu'ecrite : `HierarchieDeSaillanceTest` dans `adapter-web`, perimetre derive des gabarits rendus,
role lu dans un attribut inerte du balisage, critere "un bouton d'action ne declare, sur aucune
propriete qui fabrique sa boite, une valeur inferieure a celle d'un bouton de sortie du meme
ecran". `ADR-0017` est le prochain numero libre (`squad/decisions/adr/ADR-index.md`, dernier
numero utilise : `ADR-0016`) ; `theo-archi` le confirme au moment de l'ecrire si un autre agent en
a pris un entre-temps.

### Le garde-fou de premisse sur S5-1 - retenu, non bloquant

`theo-archi` propose un test qui echoue le jour ou une des cinq conditions qui rouvriraient le
second critere de S5-1 devient vraie (`server.servlet.session.persistent=true` en tete de liste,
la moins chere), avec un message qui nomme explicitement "le second critere de S5-1 se rouvre". Il
ne l'a pas ecrit faute d'acceptation d'equipe prealable. **Je la donne ici** : le cout est faible
(une trentaine de lignes, deriv de la liste de modules du POM agregateur et des fichiers de
configuration de `bootstrap`, aucun fichier partage avec `lea-dev` ou `marc-dev`), et le benefice
ferme un trou de tracabilite deja signale deux fois par `nadia-prof`. Non bloquant pour la cloture
si `theo-archi` juge que le garde-fou de saillance prime sur son temps disponible ce sprint ; dans
ce cas il le dit a la review plutot que de le laisser disparaitre sans trace.

### Criteres qui ne verifient pas ce qu'ils croient verifier - routes a camille-po

`theo-archi` en trouve six. **Aucun n'entre dans le sprint sous sa forme ecrite au backlog** ; je
route chacun a `camille-po` pour reecriture, comme en s03. Le travail de contenu, lui, n'attend
pas la reecriture litterale : `lea-dev` et `marc-dev` codent contre l'intention que `theo-archi` a
documentee et mesuree dans `avis-faisabilite.md`, qui fait foi jusqu'a ce que `camille-po` ait
ecrit la version corrigee ; le critere n'est declare satisfait a la review qu'une fois les deux
concordent.

| Critere | Ce qu'il dit | Pourquoi il ne mesure pas cela | Action, et qui code en attendant |
|---|---|---|---|
| S5-1, 2e | "il est renvoye vers l'accueil" | le code ne fait pas cela (ecran d'erreur des la premiere reponse) | reste ouvert ; aucun code ce sprint, voir "S5-1 residuel" |
| S5-2, 1er | "une majorite des items n'est pas resoluble par recherche visuelle" | vert des aujourd'hui, 24/24, tant que la reponse porte des crochets | reformuler sur la reponse normalisee (sans crochets, casse et accents rabattus) ; `lea-dev` code contre cette normalisation, apres S5-3 |
| S5-2, 2e | "l'item est accompagne d'un retour qui explique la variation" | un champ `explication` qui n'existe pas, decision pedagogique du moment d'affichage non tranchee | **detache en story a part**, hors perimetre de S5-2 ce sprint ; `camille-po` l'ecrit avec `nadia-prof`, `theo-archi` la concoit quand elle existe |
| S5-2, 3e | "la consigne ne parle plus de son entendu" | la consigne n'existe plus dans le depot depuis S5-1 | rattacher a la remise au catalogue (branche jetable de `nadia-prof`, voir plus haut) ; `lea-dev` ecrit la consigne corrigee dans cette branche, pas sur `round-two` cette fois |
| S5-4, 1er | "un seul son du jeu est present dans le mot" | mesure les lettres, pas les sons ; se degrade a mesure que S5-2 reussit | reecrire "porte pedagogique seule" ; `lea-dev` corrige ou retire les trois items concernes, `nadia-prof` juge |
| S5-6, "regarder ailleurs" | "au moins la moitie des items obligent a regarder ailleurs" | aucun automate de ce depot ne fait d'analyse grammaticale | reecrire "porte pedagogique seule" ; `marc-dev` ecrit les dix items, `nadia-prof` compte en lisant |

### Le triplet `la/la/l'a` - route a nadia-prof, pas tranche ici

`S5-6` casse une propriete que `nadia-prof` porte au credit du paquet ("aucun distracteur absurde
possible, et aucune devinette", `FICHE-bandit-manchot-homophones-cm1.md`) : `la`, `là` et `l'a`
sont trois mots, pas deux, et le test actuel exige exactement un distracteur par item. `theo-archi`
nomme deux sorties sans trancher (un item a deux distracteurs, ou deux items pairwise) : **c'est
une question pedagogique, je la route a `nadia-prof` sans choisir a sa place.** `marc-dev` commence
par les quatre couples non ambigus (`ce/se`, `ces/ses`, `mais/mes`, `peu/peut`) et attend l'avis de
`nadia-prof` avant d'ecrire l'item ou les deux items du couple `la/là/l'a`. Le critere de
`camille-po` sur la representation dans les deux sens s'applique une fois ce choix fait ; il ne le
precede pas.

## Sequencement et parallelisation

**Deux clusters, pas trois worktrees disjoints comme en s03** : cette fois, un fichier de test est
reellement partage entre les deux clusters de contenu, et je le dis plutot que de fabriquer une
disjonction qui n'existe pas.

- **Cluster "Le bon son"** (`lea-dev`) : `contenu/cp-le-bon-son.yaml`, la consigne (dans la branche
  jetable seulement), `PaquetsFrancaisS04Test` (constante des six sons, sans crochets).
- **Cluster "Le bon petit mot"** (`marc-dev`) : `contenu/cm1-homophones.yaml`,
  `PaquetsFrancaisS04Test` (listes des couples autorises et des paires).
- **Point de contact reel, sur un seul champ** :
  `ChaquePaquetPorteUnIndiceTest.DETTE_DE_CONTENU = Set.of("cm1-homophones", "cp-le-bon-son")`
  (`adapters/adapter-content`). Les deux lots retirent chacun leur propre entree de ce meme
  ensemble une fois leurs indices ecrits.

**Consigne de fusion, pour eviter le defaut de fusion nomme par la retro de s01 (deux agents sur le
meme contrat en parallele)** :

1. `lea-dev` ouvre son worktree la premiere et fusionne la premiere sur `round-two` (S5-3, puis
   S5-2, puis S5-4, puis les indices et le retrait de `cp-le-bon-son` de `DETTE_DE_CONTENU`).
2. `marc-dev` ouvre son worktree a partir de `round-two`, mais **ne touche a
   `DETTE_DE_CONTENU` qu'apres avoir rebase sur le `round-two` post-fusion de `lea-dev`**, et retire
   uniquement `"cm1-homophones"` de l'etat alors present (qui ne contiendra deja plus
   `"cp-le-bon-son"`). S'il ecrit ses dix indices existants et ses indices nouveaux avant que
   `lea-dev` ait fusionne, il committe cette partie sans toucher au champ partage, et revient sur
   ce seul champ au moment de fusionner.
3. Si les deux lots ferment chacun leur part de la dette dans ce sprint,
   `ADR-0016.DETTE_DE_CONTENU` devient vide et son `PLAFOND_DE_DETTE` (2 aujourd'hui) tombe a 0 -
   je le note ici pour que `theo-archi` le confirme a la review plutot que de le decouvrir par
   surprise dans un diff.
4. `theo-archi` (garde-fou de saillance, garde-fou de premisse optionnel) et `sofia-sm` (S5-8)
   travaillent chacun dans un troisieme et un quatrieme worktree, disjoints des deux premiers par
   construction (aucun des deux ne touche `contenu/` ni `PaquetsFrancaisS04Test`).
5. Chaque agent qui fusionne le fait en serie sur `round-two`, jamais deux a la fois ; c'est
   `sofia-sm` qui orchestre l'ordre (lea-dev, puis marc-dev, puis theo-archi et sofia-sm dans
   n'importe quel ordre entre eux, les deux derniers etant disjoints des quatre premiers).

**Verification de lignee, avant tout commit dans un worktree neuf (amendement de la retro de s03,
section 9 etape 2, applique ici pour la deuxieme fois)** : chaque agent qui ouvre un worktree
verifie, avant son premier commit, que sa branche de depart descend de `round-two` et non de
`main` :

```
git merge-base --is-ancestor round-two HEAD && echo "lignee saine" || echo "ATTENTION : reinitialiser sur round-two"
```

Si la commande dit d'ATTENTION, l'agent reinitialise sur `round-two` avant d'ecrire une seule
ligne, et le note dans son premier commit. Je l'ecris explicitement plutot que de supposer que
l'amendement de s03 s'applique tout seul : c'est exactement le defaut qui a force trois agents a
reinitialiser leur branche, une fois deja constate.

## Consigne JDK

Chaque agent qui rapporte un resultat de `mvn verify` positionne `JAVA_HOME` sur un JDK 21 avant de
lancer Maven, et cite la version majeure obtenue par `java -version` sous ce `JAVA_HOME` dans son
rapport - jamais seulement "vert" ou "un JDK 21 n'existait pas" (amendement de la retro de s03,
section 9 etape 4). **Le chemin exact du JDK ne s'ecrit dans aucun fichier commite** : le
prerequis est un JDK 21 correctement reference par le fichier de toolchains Maven de l'utilisateur
(`~/.m2/toolchains.xml`, nomme et orthographie exactement ainsi - la cause du JDK 25 utilise par
erreur en s03 etait un fichier nomme `toochains.xml`, jamais lu par Maven). Si `java -version` sous
le `JAVA_HOME` retenu n'affiche pas `21`, l'agent ne conclut pas qu'aucun JDK 21 n'existe : il
verifie le nom exact du fichier de toolchains avant de conclure quoi que ce soit sur les JDK
presents sur le poste.

## Obstacles ouverts a ce planning

| Description | Categorie | Agent route | Decision / artefact attendu | Statut |
|---|---|---|---|---|
| `DEC-0009` lue au pied de la lettre interdit l'audit qui doit lever le refus de "Le bon son" | process | sofia-sm | tranche ci-dessus (branche jetable pour l'audit, fusion de la ligne de catalogue seulement apres verdict) ; aucun fichier `DEC` ou `ADR` requis, decision de sequencement actee par ce planning | **clos pour ce planning ; le verdict de nadia-prof reste ouvert** |
| Six criteres de S5-1, S5-2, S5-4, S5-6 ne verifient pas ce qu'ils croient verifier | produit | camille-po | reecriture de chaque critere, voir tableau ci-dessus | ouvert, route |
| S5-6 : le couple `la/la/l'a` est un triplet, casse la propriete "un unique distracteur" que nadia-prof porte au credit du paquet | pedagogie | nadia-prof | choix entre un item a deux distracteurs et deux items pairwise | ouvert, route ; bloquant pour cet item precis seulement |
| S5-2, 2e critere : un texte d'explication de variation grapheme/phoneme demande un champ qui n'existe pas | produit + technique | camille-po (ecrit la story), theo-archi (concoit une fois la story ecrite) | nouvelle story au backlog, hors perimetre de s05 | ouvert, route, non bloquant pour s05 |
| Garde-fou de hierarchie de saillance (troisieme defaut visible par un enfant a traverser une CI verte) | technique | theo-archi | `ADR-0017` et `HierarchieDeSaillanceTest` dans le meme commit | route, prise en charge ce sprint |
| Garde-fou de premisse sur S5-1 (reouverture du second critere) | technique | theo-archi | test derive du POM et de la configuration `bootstrap` | route, retenu, non bloquant |

Rappel : aucun des obstacles ci-dessus n'est `clos` au sens du depot, hormis celui explicitement
marque comme tel ; aucun fichier `DEC` ou `ADR` nouveau n'existe encore sur le disque pour les
autres a l'heure ou ce planning est ecrit.

## Decisions prises

Aucune `DEC` ni aucun `ADR` n'est cree pendant ce planning : `DEC-0009`, `DEC-0010`, `DEC-0011` et
`ADR-0016` sont anterieurs et deja sur le disque. La seule decision prise ici est une decision de
process qui m'appartient, comme le choix des worktrees en s03 : le sequencement de la fusion de
"Le bon son" (contenu sur `round-two` tout de suite, ligne de catalogue seulement apres le verdict
de `nadia-prof`, sur une branche jetable), et l'ordre de fusion des deux clusters de contenu. Elle
est actee par ce fichier lui-meme, pas par un `ADR` ou une `DEC` : elle ne porte ni sur le produit
ni sur l'architecture du code.

## Prochaines etapes

**Demarrent maintenant** :

- `lea-dev` ouvre son worktree, verifie sa lignee, et enchaine S5-3 (sons sans crochets, mise a
  jour de `PaquetsFrancaisS04Test`), S5-2 (consigne et majorite des items, contre la normalisation
  documentee ci-dessus), S5-4 (les trois items a double reponse, contre l'avis de `nadia-prof` une
  fois recu sur le triplet - non bloquant pour S5-4 elle-meme), puis les 24 indices de
  `cp-le-bon-son` et le retrait de son entree de `DETTE_DE_CONTENU`.
- `marc-dev` ouvre son worktree, verifie sa lignee, ecrit les quatre couples non ambigus de S5-6,
  S5-7 (variete), et les indices CM1 existants et nouveaux ; attend l'avis de `nadia-prof` pour
  l'item ou les items du couple `la/la/l'a` avant de clore S5-6 ; rebase avant de toucher
  `DETTE_DE_CONTENU`.
- `theo-archi` ouvre son worktree, verifie sa lignee, ecrit `ADR-0017` et
  `HierarchieDeSaillanceTest` dans le meme commit ; ecrit le garde-fou de premisse sur S5-1 si le
  temps le permet ce sprint.
- `sofia-sm` ouvre son worktree, verifie sa lignee, livre S5-8 (`Recreo` sans accent, test jsoup
  derive du repertoire de gabarits).
- `camille-po` reecrit, en parallele, les six criteres routes plus haut, et ecrit la story qui
  detache le texte d'explication de variation de S5-2.
- `yanis-qa` traduit chaque critere corrige en test au fil des commits de chaque worktree, et
  prepare la reconciliation du comptage si `LesReglesMordentTest` bouge encore.

**Des que la branche jetable de `nadia-prof` existe (apres la fusion des indices et corrections de
contenu de `lea-dev` sur `round-two`)** : `lea-dev` y ajoute la seule ligne de catalogue, construit
le jar, et `nadia-prof` audite.

**Des que les deux clusters de contenu et les deux worktrees techniques sont verts separement** :
`sofia-sm` les fusionne en serie sur `round-two`, dans l'ordre donne ci-dessus.

**Une fois l'arbre fusionne** : `yanis-qa` fait tourner `mvn verify` dans son propre clone isole et
ecrit le rapport qualite ; `theo-archi` confirme ArchUnit dans le sien ; `relecteur-pedagogique`
releve les livrables non executables ; `nadia-prof` rend son verdict sur "Le bon son" (branche
jetable) et sur `bandit-manchot-homophones-cm1` (round-two, condition `couvert-valide`).
