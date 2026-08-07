# [Sprint s05] Retrospective - 2026-08-07

- **Anime par** : sofia-sm

## Ce qui a marche

- **`lea-dev` a signale sa propre fuite plutot que de la laisser pour un autre commit.** En
  prouvant que `PropositionsAccentueesJouablesIT` mordait enfin sur le bon defaut, elle a trouve
  que `GestionnaireErreurBruteContreExemple` (un contre-exemple d'`ADR-0011`) est enregistre comme
  un vrai bean Spring dans tout `@SpringBootTest` complet, a cause du `scanBasePackages` de
  `RecreoApplication`. Elle a documente la fuite en commentaire, route le probleme plutot que de le
  corriger hors mandat, et adapte son propre test pour rester correct malgre elle. C'est exactement
  la posture que `CLAUDE.md` demande d'un test ecrit avec le code, pas apres.
- **`nadia-prof` a retreci sa propre condition plutot que de l'abandonner ou de la durcir sans
  motif.** Sur le point 5 de sa condition CM1 (regarder au-dela du mot voisin), elle a repris le
  comptage de `marc-dev`, reconnu ou son motif tient vraiment (`ce/se`, le versant determinant de
  `mais/mes`) et ou il ne tient pas (quatre de ses propres items le contredisent), et a chiffre
  l'ecart residuel a exactement deux items, dans des familles qu'il a lui-meme montrees faisables.
  Une condition ecrite avant le sprint n'a ni grossi ni ete abandonnee en cours de route ; elle a
  ete honoree au chiffre pres.
- **Le sprint a absorbe trois interruptions imprevues sans perdre le fil du perimetre planifie**,
  parce que `squad/README.md` definit un sprint comme un increment livrable et non une duree :
  aucune des trois corrections (accent, course de session, coup rejoue) n'a force une clotue
  precipitee ni un report du reste. C'est la preuve que le principe tient face a une vraie
  perturbation, pas seulement sur le papier.

## Ce qui a coince

### 1. Trois defauts fonctionnels trouves en jouant, aucun par la CI - et un quatrieme invisible pour une raison mesurable

`nadia-prof` a signale, en jouant, "le score ne s'incremente pas toujours" ; ce symptome recouvrait
deux fautes distinctes (la course sur la session, et le coup rejoue sur une partie terminee). Un
troisieme defaut, une reponse accentuee qui plantait la manche, avait deja traverse plus de 200
tests verts avant d'etre corrige ce sprint. **Dans les trois cas, la suite `mvn verify` etait verte
au moment ou le defaut existait dans le code livre.** Aucun de ces trois defauts n'a ete trouve en
lisant un rapport ou en relisant du code : ils ont ete trouves en jouant l'application, exactement
le role que `nadia-prof` et l'utilisatrice tenaient deja avant que l'un ou l'autre garde-fou
automatique existe.

`nadia-prof` ajoute un quatrieme fait du meme ordre, sur la forme et non sur la fonction : la
regression de rembourrage lateral (`.zone-jeu` ecrasant `.container`) est **invisible sur grand
ecran**, ce qui explique que quatre portes vertes (CI, ArchUnit, le test de centrage
d'`ines-design`, et sa propre premiere lecture) l'aient laissee passer sans qu'aucune n'ait menti :
aucune ne regardait a 360 px.

**Amendement propose** : a chaque review, `sofia-sm` demande explicitement a `yanis-qa` et a
`theo-archi` si un defaut corrige ce sprint aurait pu etre attrape par un test qui n'existait pas
avant lui, et si oui, si ce test a ete ecrit **en plus** du correctif plutot que seulement le
correctif lui-meme. Pour tout defaut de rendu, la question inclut explicitement la largeur d'ecran
a laquelle il a ete trouve : un defaut trouve a 360 px et verifie seulement a 1280 px n'est pas
verifie.

### 2. Deux garde-fous se sont reveles decoratifs, et les deux ont ete trouves en verifiant activement, jamais en se fiant au vert affiche

- **`PropositionsAccentueesJouablesIT`**, ecrit precisement pour empecher le retour du
  plante-manche sur accent, restait vert quand `yanis-qa` **desactivait le correctif** : le client
  de test pourcent-encode systematiquement les octets non-ASCII via `URLEncoder`, une forme que le
  decodeur strict de Tomcat n'a jamais refusee. Le test prouvait une propriete qui n'avait jamais
  ete cassee. Corrige par `lea-dev` dans le meme sprint (corps reconstruit en Latin-1, non
  pourcent-encode), et **prouve mordant dans les deux sens** cette fois.
- **Le cliquet de dette de contenu d'`ADR-0016`** avait un `PLAFOND_DE_DETTE` a 2 pour une
  `DETTE_DE_CONTENU` deja vide : l'inegalite large (`<=`) que le seul test qui le lit verifie
  laissait deux places pour re-exempter un paquet sans indice sans qu'aucun diff de plafond ne le
  montre. Ni l'un ni l'autre defaut n'a ete vu en lisant un rapport vert : le premier a ete trouve
  en cassant reellement le code corrige pour voir si le test le remarquait, le second en poussant
  le raisonnement sur l'inegalite jusqu'a son cas limite plutot qu'en acceptant l'affirmation du
  commentaire du champ ("la trace de cette fermeture").

**Amendement propose** : un garde-fou ecrit pour fermer un refus pedagogique ou une dette nommee
n'est declare complet, dans le `review.md` qui le cite, qu'apres qu'un agent (l'auteur ou un tiers)
ait ecrit explicitement comment il l'a fait rougir - par mutation reelle du code, par relecture
adverse de l'inegalite qu'il verifie, ou par tout autre moyen nomme. "Il est vert" ne suffit jamais
comme preuve de morsure, y compris quand l'auteur lui-meme le croit.

### 3. `theo-archi` a corrige son propre chiffre de regles ArchUnit, et sa methode est reutilisable

Sa premiere mesure du sprint annoncait "29 `ArchRule` declarees" sans donner de methode. Son
complement du lendemain corrige ce chiffre a 25, avec la methode ecrite (parcours des modules du
`pom.xml`, jamais un `grep` global) et **trois causes de divergence nommees** entre deux comptages
honnetes : le faux depot de contre-exemples de `LesGardeFousSontMisALEpreuveTest` (+2 si comptes a
tort), les worktrees d'agents s'ils vivent sous l'arbre parcouru (double-compte), et deux moteurs
JUnit qui ecrivent sur le meme rapport `.txt` par classe et n'en gardent que le dernier (-7 si on
compte sur les `.txt`). Je l'ai reverifiee moi-meme a la cloture : la regle mesuree passe de 25 a
27 apres la fusion d'`ADR-0019`, une progression coherente avec sa methode et non un nouvel ecart.

**Ce qui vaut d'etre retenu** : un chiffre sans methode ecrite ne se compare a rien, meme quand il
est juste. `theo-archi` n'a pas seulement corrige un nombre, il a rendu le prochain comptage
verifiable par quelqu'un d'autre.

**Amendement propose** : tout chiffre de comptage (tests, regles ArchUnit, items de contenu) cite
dans `confirmation-archunit.md` ou `rapport-qualite.md` porte desormais sa methode en une phrase
(perimetre parcouru, source de la liste, ce qui est explicitement exclu) dans le meme paragraphe
que le chiffre - pas seulement quand un ecart a deja ete trouve.

### 4. Le provisionnement d'un worktree sur `main` plutot que `round-two` s'est reproduit ce sprint

`yanis-qa` a ouvert son worktree sur la tete de `main` (`aa2b356`) et a du se reinitialiser sur
`round-two` avant tout travail, conformement a la consigne de verification ecrite au planning de
s05 - la deuxieme fois que cette consigne (nee de la retro de s03, ou trois agents avaient du se
reinitialiser) est explicitement appliquee. **La consigne fonctionne** : chaque agent qui l'applique
detecte l'anomalie et se corrige avant d'ecrire une ligne, exactement comme prevu. **La cause,
elle, reste entiere** : rien dans l'outillage de provisionnement des worktrees n'a change, la
verification reste une commande copiee a la main dans chaque `planning.md` plutot qu'une regle
permanente. En comptant les occurrences que j'ai personnellement suivies depuis le socle, y compris
celles resolues au fil de l'eau sans faire l'objet d'un paragraphe de retro dedie, ce sprint porte
le compte a sa dixieme occurrence.

**Amendement propose** : la verification de lignee sort de `planning.md` (ou elle doit etre
recopiee a chaque sprint) et entre dans `squad/README.md`, section 9, comme une etape permanente du
demarrage de tout worktree, sous forme d'un script verifiable (`outils/verifier-lignee.sh`) plutot
que d'une commande en prose recopiee. Cela ne traite pas la cause (le provisionnement par defaut
sur `main`), mais cela retire la dependance a la memoire d'un agent qui recopie une consigne
sprint apres sprint.

### 5. Le fichier de toolchains mal nomme a de nouveau fait conclure a tort qu'aucun JDK 21 n'existait

Correction sur le chiffre exact avant tout : contrairement a s03 (trois agents, repli sur un JDK
25 reellement installe), ce sprint a vu **quatre agents** conclure a tort qu'aucun JDK 21 n'etait
disponible, avec un repli sur le **JDK 8** par defaut du `PATH` - `yanis-qa` et `theo-archi` le
notent tous les deux explicitement, en precisant que ce n'est "jamais un JDK 25, contrairement a
s03". La cause est la meme depuis s03 : le fichier de toolchains Maven de l'utilisatrice s'appelle
`toochains.xml`, pas `toolchains.xml` (verifie a nouveau ce jour, le fichier existe toujours sous ce
nom fautif) - un `l` absent, jamais lu par Maven, silencieusement.

**Ma consigne de planning sur ce point precis - "verifier le nom exact du fichier de toolchains
avant de conclure quoi que ce soit sur les JDK presents sur le poste" - n'a pas fonctionne cette
fois**, contrairement a la consigne de lignee du point 4. Quatre agents l'ont quand meme mal
diagnostique avant qu'un ne trouve la cause reelle.

**Ce que le depot peut faire malgre un fichier qui n'est pas dans le depot** : il ne peut pas
renommer un fichier du poste de l'utilisatrice, et il ne le fera jamais - ce serait toucher a un
fichier hors de son perimetre. Mais la consigne actuelle est une phrase de prose dans
`planning.md`, relue une fois puis oubliee sous la pression du reste du sprint. **Amendement
propose** : un script `outils/verifier-jdk.sh`, teste comme les garde-fous de `ADR-0009`, qui
imprime `java -version`, la valeur de `JAVA_HOME`, et l'existence (nom exact, pas seulement
presence d'un fichier de toolchains) sous le depot Maven local - sans jamais ecrire le chemin
absolu dans sa sortie destinee a un fichier commite. Tout agent qui rapporte un resultat de `mvn
verify` le lance et colle sa sortie plutot que de re-decrire la verification en prose : le
diagnostic devient mecanique au lieu de reposer sur la memoire d'une instruction lue une fois.

## Le constat de fond, qui vaut plus que la liste des stories

**Ce sprint a ete pilote par ce que l'utilisatrice trouvait en jouant, bien plus que par son
planning.** Les corrections les plus utiles du sprint - le plante-manche sur accent, la course de
session, le coup rejoue sur partie terminee, la fermeture reelle de deux garde-fous decoratifs - ne
figurent dans aucune story de `planning.md`, parce qu'aucune ne pouvait y figurer : les defauts
qu'elles corrigent n'existaient pas encore, comme problemes connus, au moment ou ce fichier a ete
ecrit. Je ne le range pas au compte d'un reproche : le planning ne pouvait pas anticiper des
defauts qui n'avaient pas encore ete trouves, et le retenir contre lui reviendrait a demander a
une ceremonie de prevoir l'imprevisible.

Ce que cela suggere, dit sans detour : **la valeur produite par ce sprint est venue, dans sa plus
grande part, d'un canal que ce process ne journalise pas explicitement comme tel** - le jeu reel,
par une vraie personne, sur un jar reellement construit, en dehors de toute story ecrite a l'avance.
`squad/README.md` fait deja de `nadia-prof` la seule autorite du Done pedagogique, et elle l'exerce
en jouant ; ce sprint montre que le meme canal, quand il vient de l'utilisatrice elle-meme, produit
des corrections qu'aucun garde-fou automatique n'aurait trouvees avant elle. Le risque, si ce
canal reste informel, est que sa tracabilite se degrade a mesure que son volume augmente : ce
sprint a absorbe trois interruptions sans en perdre le fil, mais rien ne garantit que le prochain
lot d'observations de jeu se laissera aussi facilement router et fermer par le seul tableau des
obstacles. Je ne propose pas d'amendement supplementaire ici au-dela des cinq deja ecrits plus
haut : le constat est un constat, pas encore un defaut de process assez precis pour etre corrige
par une seule regle.

## Amendements au process

| Section de squad/README.md | Changement | Raison |
|---|---|---|
| 9, etape 1 (Planning) | La verification de lignee d'un worktree neuf (`git merge-base --is-ancestor round-two HEAD`) devient une etape permanente, portee par un script `outils/verifier-lignee.sh`, plutot qu'une commande recopiee en prose dans chaque `planning.md`. | Deuxieme application de la consigne ecrite a la retro de s03 ; elle fonctionne (l'agent qui l'applique se corrige avant de coder) mais sa forme actuelle depend de sa recopie fidele sprint apres sprint, pour une cause de provisionnement qui n'a jamais ete traitee a la racine. |
| 4 (Les ceremonies), rapport qualite et confirmation ArchUnit | Tout chiffre de comptage cite dans `rapport-qualite.md` ou `confirmation-archunit.md` porte sa methode (perimetre, source, exclusions) dans le meme paragraphe que le chiffre. | `theo-archi` a du corriger un chiffre ("29" puis "25" puis "27") faute d'avoir ecrit sa methode la premiere fois ; un chiffre sans methode ne se compare a rien, meme juste. |
| 9, etape 4 (Porte technique) | Un garde-fou cite comme fermant un refus pedagogique ou une dette nommee n'est declare complet, dans le `review.md` qui le cite, qu'une fois la preuve de morsure (mutation reelle ou relecture adverse explicite) decrite - pas seulement son statut vert. | Deux garde-fous du sprint (`PropositionsAccentueesJouablesIT`, le plafond de dette d'`ADR-0016`) etaient verts sans mordre ; les deux ont ete trouves en verifiant activement, jamais en lisant le vert. |
| Nouveau : `outils/verifier-jdk.sh` | Script teste (doctrine `ADR-0009`) qui imprime `java -version`, `JAVA_HOME`, et le nom exact du fichier de toolchains trouve sous le depot Maven local, sans jamais ecrire de chemin absolu dans une sortie destinee a un fichier commite. Tout rapport de `mvn verify` colle sa sortie plutot que de re-decrire la verification en prose. | La consigne de prose ecrite au planning de s05 ("verifier le nom exact du fichier avant de conclure") n'a pas empeche quatre agents de conclure a tort a l'absence d'un JDK 21, pour la meme cause que s03 (fichier nomme `toochains.xml`). |
| 7 (Le double Done) / gouvernance | Toute story ou decision de perimetre qu'un sprint absorbe en cours de route (deplacement d'une story depuis un autre sprint, comme le rhabillage de `DEC-0012` absorbe dans s05) est actee par un amendement ecrit a la decision d'origine, dans le meme geste que sa fusion - pas seulement constatee a la review suivante. | `DEC-0012` place explicitement le rhabillage au sprint 6 ; il a ete livre et audite dans s05 sans qu'aucun amendement ecrit ne le dise, et je ne l'ai constate qu'a la cloture. |

## Decisions prises

Aucune `DEC` ni aucun `ADR` n'est cree pendant cette retro. Les cinq amendements ci-dessus et
l'amendement de gouvernance sur `DEC-0012` sont des propositions tracees pour le prochain planning ;
ils ne modifient pas `squad/README.md` par eux-memes, pour laisser l'equipe les discuter avant de
les acter.
