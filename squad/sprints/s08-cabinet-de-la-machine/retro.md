# [Sprint s08] Retrospective - 2026-08-11

- **Anime par** : sofia-sm

## Ce qui a marche

- **Un refus pedagogique a ete corrige dans le sprint qui l'a leve, avec les deux issues decidees
  par ecrit avant de savoir laquelle se produirait.** `DEC-0018` pose les deux denouements possibles
  du second verdict de `nadia-prof` (rester au catalogue si non-refuse, en sortir sinon) avant que ce
  verdict ne soit rendu - exactement pour eviter le mode de defaillance de `DEC-0009` en s04, une
  decision de retrait ecrite et jamais executee. Le second verdict est tombe du bon cote et la ligne
  de matrice a pu avancer sans qu'un nouveau `DEC` soit necessaire pour l'appliquer.
- **`nadia-prof` a corrige trois de ses propres chiffres avant de s'appuyer dessus, dans les deux
  sens.** Elle a repris a la baisse une mesure qu'elle avait elle-meme "corrigee" a tort le matin
  (`mangeons` a -2,02 px, pas -2,01), reconnu que son propre instrument de mesure lisait -1,79 px la
  ou le rendu vaut 0 (erreur de son expression reguliere sur une notation scientifique), et verifie
  avant d'ecrire qu'un item qu'elle croyait repete par erreur de tirage l'etait en realite par un
  mecanisme de rejeu deja valide (`DEC-0004`). Aucune des trois corrections n'a ete demandee par un
  tiers.
- **Un garde-fou a mordu son propre auteur avant la review.** `theo-archi`, en ecrivant la clause
  d'unicite sur la taille de proposition, a d'abord cherche le jeton par une expression reguliere ;
  `AssertionsWebTest` l'a refusee au nom d'`ADR-0008` (quatre violations nommees). Il a reecrit la
  recherche a la main sur un motif deja utilise dans le meme fichier, sans toucher a la regle qui
  l'a arrete.
- **`theo-archi` a confirme le choix de `lea-dev` plutot que de le remettre en cause par reflexe.**
  Le retrait de la seule declaration `overflow: hidden` de la proposition d'`ines-design` - l'exact
  anti-pattern qu'il venait lui-meme de coder en contre-exemple fautif dans `ADR-0030` - a ete verifie
  comme le bon geste, sans y toucher.
- **Une regression exacte de s07 remise en production a fait rougir le test cense l'empecher.**
  `yanis-qa`, en mettant a l'epreuve `LAnimationRetardeMaisNeDetermineRienTest`, a reintroduit la
  valeur fautive `inset(80% 0 0 0)` et obtenu le message exact nommant la discontinuite retrouvee,
  avant de restaurer et confirmer le retour au vert.

## Ce qui a coince

### 1. Troisieme violation de la regle du worktree, deuxieme sprint d'affilee - je tranche cette fois

`camille-po` en s06, `theo-archi` en s07, `nadia-prof` en s08 (qui s'est accusee elle-meme dans sa
propre fiche de conformite) : trois agents differents, trois sprints consecutifs, la meme regle
"sans exception" de `squad/README.md` section 9 etape 1. J'avais deja porte cette question au
planning de ce sprint sans la clore. La suite du sprint 8 a ete propre : les dix worktrees ouverts
apres ce planning l'ont tous ete dans les regles, et le contenu du commit direct de `nadia-prof` a
ete verifie conforme au perimetre attendu (quatre fiches de `squad/conformite/`, rien d'autre) - donc
la consigne en prose fonctionne des qu'elle est rappelee au bon moment dans la ceremonie, mais elle
ne protege pas ce qui se passe avant l'ouverture formelle du planning, exactement le point mort deja
nomme pour `camille-po` en s07.

**Je ne peux pas trancher la forme technique de ce garde-fou moi-meme** : detecter qu'un commit sur
`round-two` ne provient pas d'une fusion depuis un worktree dedie est une question d'outillage git,
pas une regle de process que j'ecris en prose. Je la route a `theo-archi`, au meme titre que le scan
du lexique de jeu d'argent : **cadrage de faisabilite au planning du prochain sprint** - un
`outils/*.sh` selon la doctrine d'`ADR-0009`, ou un constat ecrit que ce n'est pas gardable a un cout
raisonnable. Je ne laisse pas ce point en simple constat une quatrieme fois.

### 2. Un garde-fou a mordu une proposition avant qu'une ligne de code de production ne soit ecrite

Premiere fois dans ce projet : la feuille de style proposee par `ines-design`
(`proposition-ines-cabinet.md`) posait `overflow: hidden` sur `.cabinet` pour que les coins arrondis
rognent les enfants. `theo-archi` a repris cette feuille telle quelle comme contre-exemple fautif de
`LHabillageNeRogneJamaisUnElementPressableTest` (`ADR-0030`) avant tout developpement, et a chiffre
precisement ce que cette declaration aurait casse (l'anneau de focus clavier peint en ombre portee
hors de la boite du bouton). `lea-dev` a suivi cet avis a la lettre. C'est la preuve de morsure la
plus en amont que ce depot ait produite - avant le code, pas apres.

### 3. Deux garde-fous verts a tort, trouves par leurs propres auteurs, pas par une relecture

`theo-archi` : sa premiere ecriture d'`ADR-0029` etait verte du premier coup sur une population
vide, un artefact perime du depot local masquant la vraie feuille - attrapee par la clause de
non-vacuite d'`ADR-0003`. Et a la cloture, les deux tests neufs de S8-5 epinglaient deux valeurs
nommees a deux selecteurs sans interdire une troisieme regle : `.cabinet .proposition
{ font-size: 1.5rem; }` ajoutee a la feuille de production laissait les 178 tests d'`adapter-web`
verts avant que `theo-archi` n'ajoute la clause d'unicite. Dans les deux cas, c'est une mutation
reelle du code - pas une relecture de la regle - qui a revele le trou. **Je ne propose pas
d'amendement** : c'est exactement la doctrine de preuve de morsure d'`ADR-0003` qui a fonctionne,
une nouvelle demonstration de sa valeur et non un trou de la regle.

### 4. Cinq de mes affirmations d'etat ont ete corrigees par les agents qui les ont verifiees

La tete de `round-two` et une branche `s08-nadia` que j'ai citee comme absente du disque alors que
le commit correspondant existait sans branche ; le nombre de suites `outils/*-test.sh` ; deux
payeurs de budget que j'ai presentes comme jamais examines alors qu'ils etaient deja a leurs
planchers (`ines-design`) ; un ecart de pli que j'ai annonce a deux ordres de grandeur alors qu'il
vaut un facteur deux sur des denominateurs comparables (`camille-po`) ; le sous-comptage de
Surefire que j'ai annonce a deux classes alors qu'il en touche quatre, et huit worktrees annonces
alors qu'il y en avait seize (`theo-archi`). **Aucun agent ne m'a crue sur parole.** C'est le
constat qui compte le plus a mes yeux dans cette retro : chaque chiffre que j'ai avance a ete
rejoue, pas relaye, et c'est ce qui a empeche ces cinq ecarts de se propager plus loin que ce
fichier. La lecon de la fusion et de la cloture precedentes ("un chiffre annonce par un agent se
reverifie, il ne se relaie pas") s'applique aussi a moi, pas seulement aux autres agents.

### 5. Une technique de mesure de geometrie reconstruite cinq fois par trois agents dans un seul sprint

`nadia-prof` (trois fois), `ines-design` (une fois) et `lea-dev` (une fois) ont chacun reconstruit
`getBoundingClientRect` sur le texte contre `getBoundingClientRect` sur le bouton, sur des fragments
obtenus par les vraies routes HTTP dans un Chrome reel a 360 px - sans le savoir les uns des autres.
`yanis-qa` le documente et propose de couper la technique en deux (fonction pure testable sur
fixtures, enveloppe qui pilote Chrome, non testee) selon la doctrine deja appliquee a
`verifier-lignee.sh` et `verifier-jdk.sh`. **Je decide de l'outiller a la prochaine planification** :
c'est un geste de capacite, pas une question d'architecture a arbitrer avant.

### 6. Deux agents bloques par un incident de flux, une methode a retenir

Moi-meme sur une fusion (reprise a zero sans perte, rien n'avait ete produit) et le
`relecteur-pedagogique` en cours de correction (neuf fichiers deja modifies au moment de
l'interruption). La consigne de commiter au fil de l'eau a sauve le travail du second ; ma propre
fusion interrompue n'a rien coute parce qu'elle n'avait rien produit avant l'incident. **A retenir
comme methode**, pas comme amendement : un agent qui modifie plusieurs fichiers avant de committer
s'expose a perdre du travail reel sur un incident d'infrastructure, un agent qui committe des que
le premier fichier est stable ne perd jamais que ce qui restait a faire.

### 7. Une anomalie de poste, hors perimetre du depot mais a signaler

Le fichier de toolchains Maven du poste utilise pour ce sprint est enregistre sous un nom mal
orthographie, donc jamais lu par Maven : chaque agent doit positionner `JAVA_HOME` a la main sur un
JDK 21 le temps de chaque commande. Rien a corriger dans le depot - c'est une configuration de poste
de travail, pas un defaut de `recreo` - mais le constat s'est repete assez de fois ce sprint
(`theo-archi`, `yanis-qa`, moi-meme) pour meriter d'etre signale a l'utilisatrice plutot que
redecouvert une nouvieme fois au prochain sprint.

## Amendements au process

**Un amendement, par routage plutot que par regle ecrite ici.** Le point 1 ci-dessus est route a
`theo-archi` pour cadrage de faisabilite au planning du prochain sprint ; ce n'est pas un amendement
a `squad/README.md` aujourd'hui, faute d'une forme technique tranchee a inscrire. Les six autres
points sont nommes et classes sans amendement de regle : le point 3 est deja couvert par la
doctrine de preuve de morsure existante, les points 4 et 6 sont des constats de methode a suivre
sans nouvelle regle, le point 5 est une decision de capacite pour le prochain planning, et le
point 7 est hors perimetre du depot.

## Decisions prises

Aucun `ADR` ni aucune `DEC` n'est cree pendant cette retro, et `squad/README.md` n'est pas amende
directement - la question du garde-fou de worktree est routee, pas tranchee ici :

1. **Le point du worktree est route a `theo-archi`**, cadrage de faisabilite au planning du sprint
   suivant, plutot que laisse en simple constat pour une quatrieme fois.
2. **La mesure de geometrie artisanale est retenue pour outillage a la prochaine planification**,
   sur l'avis de `yanis-qa` (fonction pure + enveloppe, doctrine d'`ADR-0009`).
3. **Aucun amendement a `squad/README.md` ce tour** sur les six autres points, decision explicite :
   chacun est deja couvert par une doctrine existante ou releve d'un constat de methode plutot que
   d'une regle a ecrire.
4. **L'anomalie du fichier de toolchains est portee a l'utilisatrice**, hors perimetre du depot.
