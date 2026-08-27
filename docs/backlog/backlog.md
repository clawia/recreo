# Backlog produit - recreo

Ce backlog est organise **par sprint**. Chaque story porte un besoin utilisateur, la competence
programme visee (ou "transverse" quand elle n'en cible aucune directement), des criteres
d'acceptation testables en entree/sortie observables, et l'agent pressenti - affecte
nominativement par `sofia-sm` au planning.

La priorisation suit `squad/programme/MATRICE-couverture.md`, pas des demandes ad hoc : chaque
sprint doit reduire un trou visible de la matrice, pas seulement en ajouter un.

## Sprint 0 - Socle technique + premiere mecanique jouable

**Contexte** : `round-two` est une reconstruction from scratch (voir `CLAUDE.md` et
`squad/README.md`). Le depot ne contient aucun code Java. Le perimetre de ce sprint et le choix
de la premiere competence couverte sont actes dans `DEC-0001-perimetre-sprint-0.md`.

**Objectif** : livrer, dans le meme increment, un socle technique verifiable (CI verte, Clean
Architecture, garde-fou de commit) et une premiere mecanique de jeu generique - le **bandit
manchot** - jouable de bout en bout avec un contenu pedagogique reel : les additions dans la
limite de 10 (mathematiques, CP). Ce choix est justifie dans `DEC-0001` : reponse binaire sans
ambiguite, simple a auditer pour `nadia-prof`, mecanique restant entierement generique.

### S0-1 - Un socle applicatif qui se verifie tout seul

- **Besoin** (story d'enabler transverse, portee par l'equipe plutot que par un enfant) : avant
  qu'un enfant puisse jouer a quoi que ce soit, l'equipe a besoin d'un socle applicatif qui
  compile, qui se teste et qui se deploie de maniere reproductible, pour livrer un increment
  reellement jouable des ce sprint et ne pas reproduire les dettes de `main` (garde-fous
  annonces mais absents, modules sans test).
- **Competence programme visee** : transverse.
- **Criteres d'acceptation** :
  - Etant donne le depot a HEAD, quand `mvn verify` est lance depuis la racine, alors la commande
    se termine avec un code de sortie 0 (compilation, tests unitaires, tests d'integration
    Spring Boot - contexte applicatif demarre, parcours HTTP verifie de bout en bout - et
    regles ArchUnit tous verts). Le sprint 0 ne cree pas de module de persistence, aucune story
    ne demandant de durabilite entre deux parties (voir
    `DEC-0003-critere-integration-sans-persistence-sprint-0.md`).
  - Etant donne l'artefact empaquete, quand il est lance via
    `java -jar bootstrap/target/recreo.jar`, alors un serveur HTTP repond localement et une page
    est accessible depuis un navigateur.
  - Etant donne un commit dont le message ne commence pas par un prefixe d'agent connu (`[CAM]`,
    `[SOF]`, `[THEO]`, `[MARC]`, `[LEA]`, `[YAN]`, `[REL]`, `[NAD]`), quand la CI l'evalue, alors
    elle echoue explicitement en citant le prefixe manquant.
  - Etant donne les couches decrites dans `CLAUDE.md` (domain -> application -> adapters ->
    bootstrap), quand la suite ArchUnit tourne, alors elle echoue si une couche interne depend
    d'une couche exterieure ou d'un framework.
- **Agent pressenti** : theo-archi (ADR + regles ArchUnit), mise en oeuvre par marc-dev / lea-dev
  / sofia-sm.

### S0-2 - Jouer une manche du bandit manchot du debut a la fin

- **Besoin** : en tant qu'enfant, je veux lancer une partie, voir une question, choisir une
  reponse parmi plusieurs propositions, savoir tout de suite si j'ai juste ou faux, et enchainer
  sur une nouvelle question sans tout recommencer, pour pouvoir m'entrainer en jouant plutot
  qu'en remplissant une fiche.
- **Competence programme visee** : transverse (la mecanique ne connait aucune matiere ; elle
  affiche ce qui lui est injecte).
- **Criteres d'acceptation** :
  - Etant donne une page d'accueil du jeu, quand l'enfant y arrive, alors il peut demarrer une
    partie sans creer de compte ni renseigner d'information personnelle.
  - Etant donne une manche affichee avec une question et un ensemble de propositions dont une
    seule est correcte, quand l'enfant choisit la proposition correcte, alors l'interface affiche
    un retour "correct" et le score affiche augmente d'un point.
  - Etant donne la meme manche, quand l'enfant choisit une proposition incorrecte, alors
    l'interface affiche un retour "incorrect" qui designe la bonne reponse, et le score
    n'augmente pas.
  - Etant donne une manche terminee, quand l'enfant demande la suite, alors une nouvelle
    question s'affiche et le score cumule des manches precedentes reste visible (le jeu enchaine
    plusieurs manches, ce n'est pas une question isolee).
- **Agent pressenti** : marc-dev.

### S0-3 - Rendre impossible de meler mecanique et contenu

- **Besoin** : en tant que Product Owner, j'ai besoin que le decouplage entre la mecanique de
  jeu et le contenu pedagogique soit verifie automatiquement, pour garantir que le bandit manchot
  pourra servir n'importe quelle matiere future sans etre reecrit, et sans dependre de la
  vigilance de chaque developpeur a chaque nouvelle story.
- **Competence programme visee** : transverse.
- **Criteres d'acceptation** :
  - Etant donne le contenu "additions CP" livre par S0-4, quand une regle d'architecture
    automatisee s'execute, alors elle echoue si le code de la mecanique bandit manchot reference
    directement une valeur ou une classe propre a ce contenu (par exemple la borne 10, le mot
    "addition").
  - Etant donne le meme code une fois le contenu injecte via une abstraction generique, quand la
    regle s'execute, alors elle passe.
- **Agent pressenti** : theo-archi.

### S0-4 - S'entrainer aux petites additions en jouant

- **Besoin** : en tant qu'enfant de CP, je veux m'entrainer sur des petites additions (par
  exemple 3 + 4) en jouant au bandit manchot, pour progresser en calcul mental sans que ce soit
  vecu comme une interrogation ecrite.
- **Competence programme visee** : mathematiques, CP (cycle 2), domaine "Nombres et calculs" -
  "Calcul mental - additions dans la limite de 10" (sous-partie de la ligne du referentiel qui
  couvre aussi "puis 20" - hors perimetre ce sprint, voir `DEC-0001`).
- **Criteres d'acceptation** :
  - Etant donne une manche generee par ce contenu, quand la question s'affiche, alors elle
    presente une addition de deux nombres entiers compris entre 0 et 10 dont la somme est
    elle-meme comprise entre 0 et 10 (jamais superieure a 10).
  - Etant donne cette manche, quand les propositions s'affichent, alors la bonne reponse y
    figure exactement une fois, avec au moins une proposition fausse plausible (un entier proche
    de la bonne reponse), jamais de proposition negative ni non entiere.
  - Etant donne 20 manches jouees a la suite, quand on releve les additions proposees, alors
    elles ne sont pas toutes identiques (variete minimale, pas de question figee repetee).
  - Etant donne une manche, quand la consigne s'affiche, alors elle reste en vocabulaire courant
    de CP (pas de terme comme "somme" ; une formulation du type "combien ca fait ?").
- **Agent pressenti** : lea-dev.

### S0-5 - Un retour comprehensible pour un enfant qui lit encore peu

- **Besoin** : en tant qu'enfant de CP qui debute en lecture, je veux que les consignes et les
  retours du jeu soient courts et faciles a comprendre sans l'aide d'un adulte, pour pouvoir
  jouer seul.
- **Competence programme visee** : transverse (contrainte d'accessibilite pour le niveau CP,
  distincte d'une competence de contenu).
- **Criteres d'acceptation** :
  - Etant donne l'ecran de jeu, quand il s'affiche, alors la consigne tient en une phrase courte
    (moins de dix mots), sans mot hors vocabulaire courant de CP.
  - Etant donne le retour juste/faux, quand il s'affiche, alors il est accompagne d'un signal
    visuel (forme et/ou couleur) distinct du texte, pour qu'un enfant qui ne dechiffre pas
    encore vite puisse le comprendre sans lecture fine (voir
    `DEC-0002-emoji-et-pictogrammes-contenu-enfant.md` sur le choix technique retenu pour ce
    signal, hors emoji dans le gabarit).
- **Agent pressenti** : lea-dev (ou marc-dev, a affecter au planning).

## Sprint 1 - Lever le refus pedagogique de bandit-manchot-additions-cp

**Contexte** : `nadia-prof` a prononce `REFUSE` sur `bandit-manchot-additions-cp`
(`squad/conformite/FICHE-bandit-manchot-additions-cp.md`). Le Done technique est atteint
(`mvn verify` vert, 92 tests) ; le Done pedagogique ne l'est pas, et les deux sont independants.
Le contenu mathematique lui-meme n'a besoin d'aucune correction : 61 manches auditees, aucune
faute arithmetique, aucune strategie de position exploitable, 41 enonces distincts sur 41
manches. Ce que Nadia refuse est ce qui entoure le contenu : le texte lu par l'enfant, un ecran
de score, un ecran d'erreur. Cet increment est suivi dans `squad/validation/FILE-ATTENTE.md`.

**Priorisation** : S1-1 a S1-3 correspondent aux trois motifs de refus - elles bloquent la
validation de cet increment, aucune n'est negociable pour repasser devant Nadia. S1-4 et S1-5
correspondent a deux reserves que Nadia distingue explicitement du reste comme touchant au coeur
du produit (une partie qui ne s'arrete jamais, un enfant en echec qui n'est pas aide) : elles ne
bloquent pas la validation de cet increment mais sont prioritaires sur les autres reserves. S1-6
a S1-9 couvrent les cinq reserves restantes.

### S1-1 - Un texte que l'enfant peut lire sans apprendre une faute (motif de refus 1)

- **Besoin** : en tant qu'enfant de CP qui apprend a lire et a orthographier, je veux que tout ce
  que le jeu m'ecrit soit du francais correct et complet, afin de ne pas memoriser une graphie
  fausse en jouant.
- **Competence programme visee** : transverse - pas de contenu pedagogique specifique, mais
  exigence positive de categorie B ajoutee par l'amendement du 2026-08-04 a
  `squad/decisions/produit/DEC-0002-emoji-et-pictogrammes-contenu-enfant.md`.
- **Criteres d'acceptation** :
  - Etant donne le retour affiche a l'enfant apres une reponse fausse, quand on le relit, alors
    chaque mot portant un accent en francais standard s'affiche avec son accent (par exemple
    "ça", "réponse", "était"), sans faute d'orthographe ni de ponctuation.
  - Etant donne le message affiche a la fin d'une partie, quand on le relit, alors il est
    integralement accentue et sans faute (par exemple "terminée", "joué").
  - Etant donne n'importe quel texte des trois gabarits destines a l'enfant (accueil, ecran de
    jeu, retour), quand le `relecteur-pedagogique` l'audite selon l'exigence ajoutee a
    `DEC-0002`, alors il n'y trouve aucune lettre accentuee manquante et aucune faute
    d'orthographe ou de ponctuation.
- **Lien decision** : `DEC-0002` (amendement du 2026-08-04).
- **Agent pressenti** : a affecter au planning.

### S1-2 - Un score que l'enfant de CP sait lire (motif de refus 2)

- **Besoin** : en tant qu'enfant de CP, je veux voir mon nombre de bonnes reponses sous une forme
  que je sais lire, afin de savoir si je progresse sans avoir besoin qu'un adulte me traduise un
  symbole.
- **Competence programme visee** : transverse.
- **Criteres d'acceptation** :
  - Etant donne une partie en cours, quand le score s'affiche a l'enfant, alors il est presente
    exactement sous la forme `Score : N`, ou `N` est le nombre entier de bonnes reponses depuis
    le debut de la partie (par exemple `Score : 3`), sans aucune barre oblique et sans aucune
    notation `x / y`.
  - Etant donne ce meme affichage, quand l'enfant regarde l'ecran, alors le nombre d'essais
    rates n'apparait nulle part dans le score, ni seul ni mis en rapport avec le nombre de
    reussites.
  - Etant donne l'enonce de la manche en cours (un calcul), quand le score s'affiche au meme
    ecran, alors le texte du score ne contient aucun des caracteres `+`, `-`, `x`, `/`.
- **Lien decision** : `DEC-0004-textes-score-et-seuils-lever-refus-s01.md`, point 1.
- **Agent pressenti** : a affecter au planning.

### S1-3 - Un ecran franchissable quand quelque chose tourne mal (motif de refus 3)

- **Besoin** : en tant qu'enfant, je veux, si le jeu rencontre un probleme, voir un message court
  que je peux lire et un moyen de repartir, afin de ne jamais rester bloque devant un texte que je
  ne comprends pas.
- **Competence programme visee** : transverse.
- **Criteres d'acceptation** :
  - Etant donne une partie que le serveur ne reconnait plus (session perdue, redemarrage), quand
    l'enfant declenche une action de jeu, alors l'ecran affiche exactement le texte
    "Oups ! Le jeu a un souci.", jamais le corps brut d'une reponse technique (pas de texte en
    anglais, pas de code ni de donnees techniques visibles dans la zone de jeu, en particulier
    aucune des chaines "status", "timestamp", "Internal Server Error").
  - Etant donne ce meme ecran, quand l'enfant veut continuer, alors un bouton visible portant
    exactement le texte "Recommencer" lui permet de repartir vers l'accueil sans avoir besoin de
    comprendre un message technique ni de recharger la page manuellement.
- **Note** : la story decrit ce que l'enfant doit voir. La cause et la correction techniques
  appartiennent a `theo-archi` et aux developpeurs.
- **Lien decision** : `DEC-0004-textes-score-et-seuils-lever-refus-s01.md`, point 2.
- **Agent pressenti** : a affecter au planning.

### S1-4 - Une partie qui a une fin et une sortie (reserve 1, priorite coeur produit)

- **Besoin** : en tant qu'enfant, je veux qu'une partie ait un debut et une fin que je peux
  atteindre, avec un moment ou je sais que j'ai fini et un moyen de revenir a l'accueil quand je
  le veux, afin de jouer une session courte plutot que sans fin.
- **Competence programme visee** : transverse.
- **Criteres d'acceptation** :
  - Etant donne une partie qui commence, quand l'enfant joue des manches successives, alors la
    partie se termine automatiquement apres exactement 10 manches, ni avant ni apres.
  - Etant donne la derniere manche de la partie, quand l'enfant y repond, alors un ecran de fin
    s'affiche reellement (contrairement au constat de Nadia : le gabarit existe mais est
    inatteignable avec le contenu actuel), avec le score final dans la forme retenue par S1-2
    (`Score : N`).
  - Etant donne n'importe quel ecran de jeu, quand l'enfant regarde l'ecran, alors un bouton pour
    revenir a l'accueil est visible et utilisable a tout moment, avant meme la fin de la partie.
  - Etant donne un enfant qui recharge la page pendant que sa session HTTP est encore valide,
    quand la page se recharge, alors il retrouve directement l'etat de sa manche en cours, sans
    aucun message affiche (la reprise est silencieuse).
  - Etant donne un enfant qui recharge la page alors que sa session n'est plus retrouvable
    (session perdue ou serveur redemarre), quand la page se recharge, alors l'accueil affiche
    exactement le texte "Ta partie s'est arrêtée. Choisis un nouveau jeu !" au-dessus de la liste
    des jeux (reserve 7 de la fiche, traitee ici avec la reserve 1 comme le recommande Nadia).
- **Lien decision** : `DEC-0004-textes-score-et-seuils-lever-refus-s01.md`, point 3.
- **Agent pressenti** : a affecter au planning.

### S1-5 - Un enfant qui echoue plusieurs fois est aide, pas enferme (reserve 3, priorite coeur produit)

- **Besoin** : en tant qu'enfant qui se trompe plusieurs fois sur la meme question, je veux
  qu'on m'aide a comprendre plutot que de me montrer encore et encore la meme phrase sans rien
  changer, afin de ne pas rester bloque sans progresser ni pouvoir m'arreter.
- **Competence programme visee** : transverse - comportement generique de la mecanique bandit
  manchot face a l'erreur repetee, applicable a tout contenu.
- **Criteres d'acceptation** :
  - Etant donne un meme item auquel l'enfant repond faux a trois reprises, quand il lui est
    represente une nouvelle fois, alors l'item est presente avec un indice qu'il n'avait pas la
    premiere fois.
  - Etant donne trois manches consecutives ratees, quel que soit l'item concerne par chacune,
    quand la manche suivante est presentee, alors c'est un item que l'enfant a deja resolu avec
    succes au moins une fois dans la session en cours ("manche plus simple" au sens : un item
    deja reussi, pas un item mathematiquement plus facile), avant de revenir au tirage normal des
    la manche d'apres.
  - Etant donne ces trois memes echecs consecutifs alors qu'aucun item n'a encore ete reussi dans
    la session, quand la manche suivante est presentee, alors elle suit le tirage normal, sans
    manche plus simple et sans erreur.
  - Etant donne cette meme serie d'echecs, quand elle se poursuit, alors le bouton de sortie
    vers l'accueil (S1-4) reste visible : l'enfant n'a jamais besoin de fermer l'onglet pour
    s'arreter.
- **Note** : le seuil de trois echecs sur le meme item (indice) et le seuil de trois echecs
  consecutifs quel que soit l'item (manche plus simple) sont deux compteurs independants qui
  partagent la meme valeur numerique par souci de coherence, mais ne se declenchent pas
  necessairement ensemble ni sur le meme item. Que remontrer un item deja reussi aide reellement
  l'enfant en difficulte est un jugement pedagogique de fond que cette story ne tranche pas :
  `nadia-prof` en jugera l'effet a l'audit (voir `DEC-0004`, point 4).
- **Lien decision** : `DEC-0004-textes-score-et-seuils-lever-refus-s01.md`, point 4 ; et
  `DEC-0005-indice-s1-5-vit-dans-le-yaml-pas-dans-le-domaine.md` pour l'origine du texte de
  l'indice (contenu YAML, pas une constante du domaine).
- **Agent pressenti** : a affecter au planning.

### S1-6 - Un signal juste/faux qui ne depend pas de distinguer les couleurs (reserve 4)

- **Besoin** : en tant qu'enfant qui ne distingue pas bien certaines couleurs, je veux reconnaitre
  si j'ai juste ou faux grace a un signe que je connais deja de mon cahier, afin de comprendre le
  retour meme sans distinguer le vert du rouge.
- **Competence programme visee** : transverse.
- **Criteres d'acceptation** :
  - Etant donne un retour juste, quand il s'affiche, alors le signal visuel est une coche, la
    couleur restant un second canal d'information et non le seul.
  - Etant donne un retour faux, quand il s'affiche, alors le signal visuel est une croix, meme
    condition sur la couleur.
  - Etant donne ces deux signaux vus sans pouvoir distinguer leur couleur, quand on les compare,
    alors leur forme seule permet de savoir lequel dit juste et lequel dit faux.
  - Etant donne le gabarit corrige, quand on le relit, alors aucun emoji n'y a ete introduit
    (compatible avec `DEC-0002` : forme dessinee en CSS ou en SVG).
- **Agent pressenti** : a affecter au planning.

### S1-7 - Des additions qui font vraiment calculer (reserve 2)

- **Besoin** : en tant qu'enfant de CP, je veux m'entrainer sur des additions qui demandent de
  calculer, afin que mon temps de jeu serve le calcul mental plutot que de constater qu'ajouter
  zero ne change rien un tiers du temps.
- **Competence programme visee** : mathematiques, CP (cycle 2), domaine "Nombres et calculs" -
  "Calcul mental - additions dans la limite de 10" (meme ligne que S0-4,
  `squad/programme/mathematiques/competences-cp.md`).
- **Criteres d'acceptation** :
  - Etant donne 200 tirages effectues avec une graine aleatoire fixee, quand on compte les
    tirages dont au moins un operande est nul, alors ce compte est compris entre 10 et 30 inclus
    (soit entre 5 % et 15 % des tirages), contre 16 sur 61 (26 %) constate ce sprint et une
    esperance theorique de 32 % avant correction.
- **Lien decision** : `DEC-0004-textes-score-et-seuils-lever-refus-s01.md`, point 5.
- **Agent pressenti** : a affecter au planning.

### S1-8 - Des mots d'interface que l'enfant de CP comprend (reserve 6)

- **Besoin** : en tant qu'enfant de CP, je veux que tous les mots affiches par le jeu, pas
  seulement la consigne, soient des mots que je comprends, afin de jouer seul sans qu'un adulte
  ait besoin de me les traduire.
- **Competence programme visee** : transverse.
- **Criteres d'acceptation** :
  - Etant donne le bouton qui permet de continuer apres une manche, quand il s'affiche, alors
    son libelle n'emploie plus le mot "manche".
  - Etant donne l'ecran de fin de partie, quand il s'affiche, alors son texte n'est plus d'un
    registre adulte ("Merci d'avoir joue !" est reformule).
  - Etant donne l'ecran affiche quand aucune partie n'est disponible, quand il s'affiche, alors
    son texte est reformule en vocabulaire courant de CP.
  - Etant donne le retour faux, quand il s'affiche, alors il est formule au present en une seule
    phrase courte (par exemple "Non, c'est 4." plutot que "La bonne réponse était 4.").
  - Etant donne le libelle du bouton d'accueil "Additions CP", quand il s'affiche, alors il ne
    contient plus le grapheme "ti" prononce /si/, non acquis en lecture globale de CP.
- **Agent pressenti** : a affecter au planning.

### S1-9 - Un calcul et un signal plus grands que les boutons (reserve 5)

- **Besoin** : en tant qu'enfant de CP qui lit encore lentement, je veux que le calcul a lire et
  le signal de retour soient les elements les plus visibles de l'ecran, afin de ne pas devoir
  lire le plus petit texte de l'ecran pour jouer.
- **Competence programme visee** : transverse.
- **Criteres d'acceptation** :
  - Etant donne l'ecran de jeu, quand l'enonce de la manche s'affiche, alors sa taille de police
    est nettement superieure a celle des boutons de proposition (au moins 2 rem, contre 1,1 rem
    constate ce sprint).
  - Etant donne le signal juste/faux, quand il s'affiche, alors sa taille est au moins le double
    de celle constatee ce sprint (au moins 3 rem, contre 1,5 rem).
- **Agent pressenti** : a affecter au planning.

## Sprint 2 - Une application habillee, lisible par un enfant de CP, reconnaissable dans le navigateur

**Contexte** : ce sprint n'est pas issu d'un refus pedagogique. Il comble un trou de backlog
constate en rejouant l'application sur `round-two`, code et rendu a l'appui : `recreo.css`
(`adapters/adapter-web/src/main/resources/static/css/recreo.css`) ne declare que 8 selecteurs
(`body`, `.consigne`, `.enonce`, `.proposition`, `.signal`, `.icone-signal`, `.signal.est-juste`,
`.signal.est-faux`). Tous les autres selecteurs utilises par les gabarits - `h1`, `.jeux`,
`.demarrer`, `.retour-accueil`, `.score`, `.fin-de-partie`, `.indice`, `.erreur`, `.pas-de-jeu`,
`.message-reprise`, `.recommencer`, et le bouton "Encore !" du fragment `retour.html` (un
`<button>` sans classe) - n'ont aucune regle et tombent au comportement par defaut du navigateur.
`/favicon.ico` repond 404. Aucune palette de couleurs, aucune typographie au-dela de `sans-serif`,
aucune icone : l'application n'a pas d'identite visuelle.

Ce trou existe parce que `ines-design` a ete creee pendant s01 (amendement 1, section "L'equipe",
de `squad/sprints/s00-socle/retro.md`) pour un mandat large - "design d'interface et
accessibilite visuelle (contraste, tailles de police et de signal, semantique des formes,
ergonomie pour un enfant de CP qui lit encore peu)" - mais elle n'a ete affectee qu'aux deux
stories qui existaient deja au backlog a ce moment-la (S1-6, S1-9). Aucune story d'identite
visuelle, de charte graphique ou d'icone d'application n'avait jamais ete ecrite : ce n'est pas un
manquement d'Ines, c'est un trou du backlog que je corrige ici.

Une partie de ce trou etait deja nommee par `nadia-prof`, en reserve non bloquante pour s02, dans
`squad/conformite/FICHE-bandit-manchot-additions-cp.md` (verdict `VALIDE SOUS RESERVE` du
2026-08-05) : reserve 6 ("aucun texte lu par l'enfant ne devrait passer sous la taille de la
consigne (1,25 rem), et les boutons 'Encore !' et de sortie ne devraient pas etre rendus a la
taille par defaut du navigateur, environ 13 px [...] `.indice` en particulier doit etre au moins
aussi gros que la consigne : c'est le texte destine au lecteur le plus fragile") et reserve 7
("'Recommencer' doit ressembler a un bouton [...] un enfant de six ans ne sait pas qu'un texte
souligne se clique"). S2-1 ci-dessous reprend ces deux reserves telles quelles.

**Frontiere de mandat, valable pour les trois stories de ce sprint** : `ines-design` propose la
forme, la couleur, le contraste et la disposition ; elle ne tranche pas. `nadia-prof` garde seule
le veto pedagogique sur ce que voit l'enfant - aucune de ces stories ne peut passer a
`couvert-valide` une quelconque ligne de `squad/programme/MATRICE-couverture.md`, puisqu'aucune ne
porte de competence programme (voir champ "Competence programme visee" de chacune) : la matrice
n'est donc pas modifiee par ce sprint.

**Contraintes techniques rappelees pour les trois stories** (`CLAUDE.md`) : zero build Node, zero
serveur front separe, tout est servi par le fat jar depuis les ressources statiques de
`adapter-web` ; aucune ressource externe, aucun CDN, aucune police telechargee a l'execution -
l'application doit fonctionner hors ligne ; le comportement reste sur le serveur (`ADR-0008`),
donc aucune de ces stories n'ajoute de comportement au JavaScript client `recreo.js`.

### S2-1 - Aucun texte ni bouton plus petit que la consigne du jeu (reserves 6 et 7 de la fiche bandit-manchot-additions-cp)

- **Besoin** : en tant qu'enfant de CP qui lit encore lentement, je veux que tout texte que je dois
  lire et tout bouton sur lequel j'appuie soit au moins aussi grand que la consigne du jeu, et que
  je reconnaisse un bouton comme un bouton, afin de ne jamais devoir dechiffrer le plus petit texte
  de l'ecran pour savoir quoi faire ni deviner qu'un texte souligne se clique.
- **Competence programme visee** : transverse - accessibilite visuelle pour le niveau CP (meme
  categorie que S0-5, S1-6, S1-9).
- **Criteres d'acceptation** :
  - Etant donne le fichier `recreo.css` livre, quand on releve la taille de police declaree pour
    chaque selecteur qui affiche du texte lu par l'enfant ou un bouton actionnable - au minimum
    `.score`, `.indice`, `.fin-de-partie`, `.erreur`, `.pas-de-jeu`, `.message-reprise`, le bouton
    de la forme "Encore !" du fragment `retour.html`, `.retour-accueil`, `.demarrer` et
    `.recommencer` - alors chacune de ces tailles est declaree explicitement et n'est jamais
    inferieure a celle de `.consigne` (1,25rem).
  - Etant donne n'importe quel bouton actionnable par l'enfant (demarrer une partie, continuer,
    revenir a la liste des jeux, recommencer apres une erreur), quand on releve sa taille de police
    declaree, alors elle est au moins egale a 1,25rem, jamais laissee au defaut du navigateur
    (environ 13px constate ce sprint sur "Encore !" et "Recommencer").
  - Etant donne `.indice`, le texte destine au lecteur le plus fragile (l'enfant qui vient de rater
    trois fois le meme item), quand on releve sa taille declaree, alors elle est au moins egale a
    celle de `.consigne`, conformement a la reserve de `nadia-prof`.
  - Etant donne le titre `<h1>Recreo</h1>` de l'ecran d'accueil, quand on relit `recreo.css` apres
    cette story, alors une regle de taille lui est explicitement declaree, elle ne depend plus
    seulement du comportement par defaut du navigateur pour une balise `h1`.
  - Ce qui n'est pas testable par un automate et releve de la seule porte pedagogique de
    `nadia-prof` : que la hierarchie visuelle obtenue (quel element attire l'oeil en premier, quel
    confort de lecture reel) convienne effectivement a un enfant de CP en train de jouer. Aucun des
    criteres ci-dessus ne pretend mesurer cela ; ils garantissent seulement qu'aucune taille
    declaree ne retombe sous le defaut du navigateur ou sous la consigne.
- **Note de frontiere de mandat** : `ines-design` propose les valeurs de taille et la disposition,
  elle ne tranche pas ; `nadia-prof` garde seule le veto pedagogique sur ce que voit l'enfant.
- **Lien decision** : reserves 6 et 7 de `squad/conformite/FICHE-bandit-manchot-additions-cp.md`
  (verdict du 2026-08-05).
- **Agent pressenti** : ines-design (proposition des valeurs), mise en oeuvre par marc-dev /
  lea-dev / sofia-sm.

### S2-2 - Un habillage visuel qui fait de recreo une application, pas une suite de pages nues

- **Besoin** : en tant qu'enfant, je veux que tous les ecrans du jeu - l'accueil, la manche, le
  retour juste, le retour faux, la fin de partie, l'ecran "il n'y a pas de jeu", l'ecran
  d'erreur - se ressemblent, avec des couleurs et un espacement soignes, afin de reconnaitre que je
  suis dans la meme application partout, plutot que devant du texte brut sans mise en forme.
- **Competence programme visee** : transverse - identite visuelle et ergonomie, aucun contenu
  pedagogique.
- **Criteres d'acceptation** :
  - Etant donne `recreo.css` livre, quand on releve les selecteurs `h1`, `.jeux`, `.demarrer`,
    `.retour-accueil`, `.score`, `.fin-de-partie`, `.indice`, `.erreur`, `.pas-de-jeu`,
    `.message-reprise` et `.recommencer`, alors chacun porte au moins une regle de couleur, de fond
    ou d'espacement explicite ; aucun ne reste sans aucune declaration comme constate ce sprint.
  - Etant donne l'ensemble des couleurs de texte et de fond utilisees par ces ecrans (hors le
    signal juste/faux, deja fixe par S1-6), quand on les releve dans `recreo.css`, alors elles
    proviennent d'un ensemble limite et nomme de valeurs reutilisees a plusieurs endroits du
    fichier, et non de couleurs ponctuelles differentes a chaque selecteur.
  - Etant donne toute ressource visuelle ajoutee par cette story (police, image de fond, couleur),
    quand on l'inspecte, alors elle est servie depuis les ressources statiques de `adapter-web`,
    empaquetee dans le fat jar unique, sans reference a une police telechargee a l'execution ni a
    un CDN (l'application reste utilisable hors ligne).
  - Etant donne `recreo.js`, quand on le relit apres cette story, alors il n'a recu aucune ligne de
    comportement nouveau : l'habillage passe entierement par CSS et par les gabarits Thymeleaf deja
    rendus par le serveur (`ADR-0008`).
  - Ce qui n'est pas testable par un automate et releve de la seule porte pedagogique de
    `nadia-prof` : que l'habillage obtenu (couleurs choisies, ambiance generale, caractere
    accueillant ou anxiogene) soit adapte a un enfant de CP ou de CM1. Aucun critere ci-dessus ne
    pretend juger le gout ou l'adequation du rendu, seulement sa presence, sa coherence formelle
    (palette limitee et reutilisee) et sa conformite technique (hors ligne, sans JavaScript
    nouveau).
- **Note de frontiere de mandat** : `ines-design` propose la palette, le contraste et la
  disposition, elle ne tranche pas ; `nadia-prof` garde seule le veto pedagogique sur ce que voit
  l'enfant.
- **Agent pressenti** : ines-design (proposition de palette et de charte), mise en oeuvre par
  marc-dev / lea-dev / sofia-sm.

### S2-3 - Une icone pour reconnaitre recreo dans mon navigateur

- **Besoin** : en tant qu'enfant qui a deja lance recreo une fois, je veux voir un petit dessin
  reconnaissable dans l'onglet du navigateur ou dans mes favoris, afin de retrouver l'application
  sans avoir besoin de lire son nom.
- **Competence programme visee** : transverse - identite du produit, aucun contenu pedagogique.
- **Contexte et lien avec DEC-0002** : `/favicon.ico` repond 404 aujourd'hui, verifie sur
  l'application lancee ; l'application n'a aucune icone. Une icone d'application vue par l'enfant a
  chaque lancement releve de la categorie B de
  `DEC-0002-emoji-et-pictogrammes-contenu-enfant.md` ("contenu et interface destines a l'enfant
  [...] signaux d'interface purs") et non de sa categorie A (redaction du depot, qui interdit les
  symboles Unicode decoratifs dans le registre professionnel de la squad) : cette interdiction ne
  vise pas le dessin d'une icone d'interface, elle vise la prose du depot. L'icone n'est donc ni
  interdite ni autorisee par principe par cette regle de style ; elle est gouvernee par le meme
  mecanisme que le signal juste/faux de S1-6 - `ines-design` en propose la forme, `nadia-prof` en
  juge l'adequation a un enfant de CP ou de CM1 (exactitude, absence d'ambiguite, absence de biais)
  au cas par cas, comme n'importe quel autre element de categorie B. Voir aussi
  `DEC-0006-perimetre-identite-visuelle-s02.md`, qui documente cette lecture.
- **Criteres d'acceptation** :
  - Etant donne l'application demarree via `java -jar bootstrap/target/recreo.jar`, quand une
    requete HTTP est envoyee sur `/favicon.ico`, alors la reponse a un code 200 (plus de 404).
  - Etant donne cette meme reponse, quand on lit son en-tete `Content-Type`, alors elle annonce un
    type d'image (par exemple `image/x-icon`, `image/png` ou `image/svg+xml`, choix technique
    laisse a `theo-archi` / `marc-dev` / `lea-dev`), jamais un type generique ni une page HTML.
  - Etant donne le fichier d'icone livre, quand on verifie son emplacement, alors il est servi
    comme une ressource statique de `adapter-web`, empaquetee dans le fat jar unique, sans appel
    reseau ni CDN, disponible hors ligne.
  - Etant donne `accueil.html` (le seul document HTML complet ; les autres gabarits sont des
    fragments), quand on le relit, alors il reference explicitement cette icone (par exemple une
    balise `<link rel="icon" ...>`), pour ne pas dependre du seul comportement implicite
    `/favicon.ico` du navigateur.
  - Ce qui n'est pas testable par un automate et releve de la seule porte pedagogique de
    `nadia-prof` : que le dessin retenu soit reconnaissable, non ambigu et adapte a un enfant de CP
    ou de CM1 (comme un pictogramme de contenu, `DEC-0002`, categorie B). Les criteres ci-dessus
    garantissent seulement que l'icone existe, repond, est du bon type et fonctionne hors ligne -
    pas qu'elle est un bon dessin pour un enfant.
- **Note de frontiere de mandat** : `ines-design` propose le dessin et la couleur, elle ne tranche
  pas ; `nadia-prof` garde seule le veto pedagogique sur ce que voit l'enfant.
- **Lien decision** : `DEC-0002-emoji-et-pictogrammes-contenu-enfant.md` (categorie B) ;
  `DEC-0006-perimetre-identite-visuelle-s02.md`.
- **Agent pressenti** : ines-design (proposition du dessin), mise en oeuvre par marc-dev / lea-dev
  / sofia-sm.

## Sprint 3 - Completer la ligne mathematiques CP avant d'en ouvrir une nouvelle

**Contexte et decision de perimetre** : trois sprints (s00 a s02) ont livre un seul jeu, sur une
seule competence, dont la ligne de `squad/programme/MATRICE-couverture.md` n'est toujours pas
`couvert-valide`. `nadia-prof` a pose en s01 une condition ecrite pour qu'elle le devienne (la
repartition des sommes corrigee) et un second constat de fond non tranche (l'indice de S1-5
inatteignable en jeu reel) ; s02 n'a traite ni l'un ni l'autre, absorbe par le chantier d'identite
visuelle (`DEC-0006`). Ces deux constats ont donc chacun deja ete reportes une fois sans etre
instruits. `DEC-0007-perimetre-s03-completer-avant-etendre.md` tranche : **s03 ne cree aucun
contenu de matiere ou de competence nouvelle** ; il complete la competence deja engagee et solde
la dette de gouvernance qui s'est accumulee sur elle (`squad/validation/FILE-ATTENTE.md`, reste
deux sprints sur un verdict `REFUSE` perime, corrige a l'ouverture de ce sprint). Le detail du
raisonnement et des motifs est dans `DEC-0007`, pas repete ici.

S3-1 a S3-3 portent sur le contenu et le comportement du jeu (mathematiques CP). S3-4 a S3-7
reprennent les cinq reserves visuelles nommees par `nadia-prof` a la cloture de s02
(`squad/conformite/FICHE-bandit-manchot-additions-cp.md`, points 2 bis, 3 et 4) qui n'appelaient
aucun arbitrage produit nouveau et peuvent donc etre instruites directement en story. S3-8 traite
le trou d'audit sous ~500 px de large signale par `nadia-prof` en s02, tranche comme perimetre
produit par `DEC-0007` point 5. S3-9 traite une reserve plus ancienne, restee sans decision depuis
s01 (le titre non accentue, reserve 10, tranchee par `DEC-0007` point 7). Aucune de ces neuf
stories ne fait passer une ligne de la matrice a `couvert-valide` par elle-meme : ce statut n'est
prononce que par `nadia-prof`, apres audit de l'increment corrige.

### S3-1 - Des additions qui travaillent tout l'intervalle, pas seulement son bord superieur (condition de nadia-prof pour couvert-valide)

- **Besoin** : en tant qu'enfant de CP, je veux m'entrainer sur des additions qui couvrent
  vraiment les petites comme les grandes sommes, afin que jouer serve a apprendre l'ensemble de
  "calculer dans la limite de 10" et pas seulement sa partie la plus proche de 10.
- **Competence programme visee** : mathematiques, CP (cycle 2), domaine "Nombres et calculs" -
  "Calcul mental - additions dans la limite de 10" (meme ligne que S0-4 et S1-7,
  `squad/programme/mathematiques/competences-cp.md`). Condition ecrite par `nadia-prof` pour que
  cette ligne de `squad/programme/MATRICE-couverture.md` passe a `couvert-valide`.
- **Criteres d'acceptation** :
  - Etant donne 6500 tirages effectues avec une graine aleatoire fixee, quand on releve, pour
    chaque somme s comprise entre 0 et 10, la proportion de tirages produisant cette somme, alors
    chaque proportion est comprise dans la fourchette suivante (cible +/- 2 points de pourcentage,
    calculee sur le tirage reellement compose - tirage uniforme sur les 65 couples valides puis
    filtre d'operandes nuls - et non sur une etape intermediaire de son calcul ; voir
    l'amendement du 2026-08-05 a `DEC-0007` point 2) :

    | Somme | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 | 10 |
    |---|---|---|---|---|---|---|---|---|---|---|---|
    | Fourchette basse | 0 % | 0 % | 1,23 % | 3,16 % | 5,10 % | 7,03 % | 8,97 % | 10,90 % | 12,84 % | 14,77 % | 16,71 % |
    | Fourchette haute | 0 % | 3,29 % | 5,23 % | 7,16 % | 9,10 % | 11,03 % | 12,97 % | 14,90 % | 16,84 % | 18,77 % | 20,71 % |

  - Etant donne le meme echantillon de tirages, quand on compte les tirages produisant l'addition
    "0 + 0", alors ce compte est exactement 0 (le couple est retire du tirage, `DEC-0007` point 2).
  - Etant donne 50 parties completes jouees independamment avec des graines distinctes, quand on
    releve la premiere manche de chacune, alors aucune n'est "0 + 0" (consequence directe du
    critere precedent, verifiee explicitement parce que c'etait le cas le plus grave signale par
    `nadia-prof`, reserve 8 du verdict s01 : "et surtout pas en premiere manche").
  - Etant donne le seuil deja fixe par `DEC-0004` point 5 (5 % a 15 % des tirages avec au moins un
    operande nul, sur 200 tirages a graine fixee - cible pedagogique retenue par l'amendement du
    2026-08-05 : 12,9 %, le reglage deja valide par `nadia-prof` en s01), quand on rejoue ce test
    apres la correction, alors il reste vert : cette story ne doit pas faire sortir la proportion
    d'operandes nuls de la fourchette deja validee par `nadia-prof` en s01.
  - Ce qui n'est pas testable par un automate et releve de la seule porte pedagogique de
    `nadia-prof` : que cette repartition "travaille effectivement toute la competence" au sens
    pedagogique, et pas seulement au sens statistique de la table ci-dessus. Les criteres
    ci-dessus garantissent la forme du tirage, pas le jugement qu'un enfant de CP en tire un
    entrainement equilibre.
- **Note de methode, transmise par `theo-archi`, non bloquante** : faire tourner ce test sur un jeu
  de graines declare (par exemple 0 a 9) plutot que sur une graine unique est recommande pour eviter
  qu'une correction ne passe que par chance de graine ; c'est un choix de forme qui appartient a
  `yanis-qa`, cette story ne l'impose pas comme critere.
- **Lien decision** : `DEC-0007-perimetre-s03-completer-avant-etendre.md`, point 2, tel qu'amende
  le 2026-08-05 ; `DEC-0004-textes-score-et-seuils-lever-refus-s01.md`, point 5, tel que precise
  par son amendement du 2026-08-05 (cible pedagogique 12,9 %, bande inchangee).
- **Agent pressenti** : a affecter au planning.

### S3-2 - Un enfant qui rate trois fois de suite recoit un indice dans la partie en cours

- **Besoin** : en tant qu'enfant qui vient de rater trois questions d'affilee, je veux recevoir un
  coup de pouce sur la question suivante plutot que de continuer a rater sans aucune aide, afin de
  ne pas rester bloque jusqu'a la fin de la partie sans comprendre.
- **Competence programme visee** : transverse - comportement generique de la mecanique bandit
  manchot face a l'echec repete, meme perimetre que S1-5.
- **Criteres d'acceptation** :
  - Etant donne trois reponses fausses consecutives dans une partie, quelle que soit la question
    concernee par chacune (contrairement au declenchement initial de S1-5, qui exigeait le meme
    item trois fois), quand la question suivante est presentee, alors elle est accompagnee d'un
    indice qu'elle n'aurait pas eu sinon.
  - Etant donne une partie de 10 manches ou l'enfant rate les trois premieres manches d'affilee,
    quand la quatrieme manche s'affiche, alors l'indice y est visible (l'aide est donc atteignable
    au plus tard a la quatrieme manche d'une partie qui en compte dix, contre la 14e manche
    constatee inatteignable par `nadia-prof` avec le declenchement initial).
  - Etant donne un enfant qui reussit une manche puis rate de nouveau trois manches consecutives
    plus tard dans la meme partie, quand la manche suivant ce second groupe de trois echecs
    s'affiche, alors l'indice est de nouveau present : le compteur d'echecs consecutifs se remet a
    zero apres chaque reussite (regle deja fixee par S1-5 et `DEC-0004` point 4, inchangee ici) et
    le declenchement de l'indice se comporte de la meme facon.
  - Etant donne ce meme evenement (trois echecs consecutifs), quand a la fois la "manche plus
    simple" (S1-5, item deja reussi) et l'indice se declenchent ensemble, alors les deux
    comportements s'appliquent a la meme question suivante sans se contredire ni s'annuler l'un
    l'autre.
- **Note** : cette story amende le premier critere d'acceptation de S1-5 tel qu'ecrit au sprint 1
  ("un meme item auquel l'enfant repond faux a trois reprises [...] est presente avec un indice") :
  ce critere reposait sur une hypothese de recurrence d'un item dans une partie qui ne se verifie
  quasiment jamais en jeu reel (mesure par `nadia-prof`, un seul retour naturel sur 24 parties). Le
  reste des criteres de S1-5 (manche plus simple, sortie toujours visible) n'est pas modifie.
- **Lien decision** : `DEC-0007-perimetre-s03-completer-avant-etendre.md`, point 3 ;
  `DEC-0004-textes-score-et-seuils-lever-refus-s01.md`, point 4 (inchange sur le reste).
- **Agent pressenti** : a affecter au planning.

### S3-3 - L'indice vit dans le contenu, pas dans une constante du domaine (execution de DEC-0005)

- **Besoin** (story d'enabler transverse, portee par l'equipe plutot que par un enfant) : pour que
  l'indice puisse etre reformule sans recompiler le domaine la prochaine fois que `nadia-prof` en
  demande une correction - ce qu'elle a deja fait une fois en s01 - le texte de l'indice doit venir
  du meme paquet de contenu YAML que la consigne, exactement comme `DEC-0005` l'a tranche.
- **Competence programme visee** : transverse.
- **Criteres d'acceptation** :
  - Etant donne `domain/src/main/java/com/recreo/domain/content/source/
    AdditionsBorneesContentSource.java`, quand on le relit apres cette story, alors il ne contient
    plus aucune constante de texte destine a l'enfant (en particulier l'ancienne constante
    `INDICE`) : le texte de l'indice est recu au constructeur, au meme titre que les parametres
    deja portes par le contenu (`ADR-0006`).
  - Etant donne le paquet de contenu
    `adapters/adapter-content/src/main/resources/contenu/cp-additions-jusqu-a-10.yaml`, quand on
    le relit, alors une entree porte le texte de l'indice, au meme niveau que les parametres
    existants.
  - Etant donne l'application demarree avec ce paquet, quand un indice est declenche en jeu
    (S3-2), alors le texte reellement affiche a l'enfant est identique, caractere pour caractere,
    a celui affiche avant cette story ("Compte sur tes doigts.") : cette story deplace le texte,
    elle ne le change pas.
  - Etant donne le contrat de contenu deja verifie par `DifficulteTest` et les regles ArchUnit
    existantes, quand la suite tourne apres cette story, alors elle reste verte sans qu'aucune
    regle n'ait ete affaiblie pour la faire passer.
- **Lien decision** : `DEC-0005-indice-s1-5-vit-dans-le-yaml-pas-dans-le-domaine.md`.
- **Agent pressenti** : a affecter au planning.

### S3-4 - Le nombre doit peser plus que le bouton qui le porte (reserve 1, S2-1)

- **Besoin** : en tant qu'enfant de CP, je veux que le chiffre de chaque reponse possible soit ce
  que je vois le plus dans le bouton, afin de comparer les propositions par ce qui les distingue
  vraiment plutot que par la forme du bouton, identique pour les trois.
- **Competence programme visee** : transverse - accessibilite visuelle pour le niveau CP (meme
  categorie que S1-9, S2-1).
- **Criteres d'acceptation** :
  - Etant donne un bouton de reponse rendu, quand on releve la taille de police du chiffre qu'il
    affiche, alors elle est au moins egale a 2 rem (contre 1,25 rem constate en s02), soit au
    moins la taille du calcul affiche a l'ecran (`.enonce`).
  - Ce qui n'est pas testable par un automate et releve de la seule porte pedagogique de
    `nadia-prof` : que le rapport entre la taille du chiffre et la largeur du bouton (mesuree a
    2 % en s02) soit desormais equilibre a l'oeil pour un enfant de CP. Le critere ci-dessus fixe
    un plancher de taille, pas un rapport visuel jugeable par un automate ; `ines-design` reste
    libre de grossir le chiffre, de reduire la largeur du bouton, ou les deux.
- **Note de frontiere de mandat** : `ines-design` propose la forme retenue, elle ne tranche pas ;
  `nadia-prof` garde seule le veto pedagogique sur ce que voit l'enfant.
- **Lien decision** : reserve 1 de `squad/conformite/FICHE-bandit-manchot-additions-cp.md`
  (verdict S2-1 du 2026-08-05, point 2 bis).
- **Agent pressenti** : ines-design (proposition), mise en oeuvre par marc-dev / lea-dev /
  sofia-sm.

### S3-5 - Un ecart confortable entre deux boutons de reponse (reserve 2, S2-1)

- **Besoin** : en tant qu'enfant de CP dont les gestes sont encore imprecis, je veux un espace net
  entre deux boutons de reponse, afin qu'un doigt qui derape legerement ne touche jamais le bouton
  voisin.
- **Competence programme visee** : transverse - accessibilite visuelle et motrice pour le niveau
  CP.
- **Criteres d'acceptation** :
  - Etant donne le conteneur `.propositions` declare dans `recreo.css`, quand on releve la valeur
    de sa propriete `gap`, alors elle est explicitement declaree a au moins 1 rem (16 px), contre
    0,75 rem (12 px) constate en s02. Cette declaration est retenue comme critere verifiable a la
    place de "l'ecart reel entre les boites" (formulation initiale, non mesurable par un automate
    du depot faute de moteur de rendu, voir S3-8) : c'est un proxy valide tant que les boutons de
    reponse restent empiles sans marge additionnelle dans un conteneur flex en colonne, disposition
    en place depuis la correction de s02 (`ines-design`, s02, `fd16057`) ; si cette disposition
    change, le proxy cesse d'etre valide et devra etre revu par `yanis-qa` et `theo-archi`.
  - Ce qui n'est pas testable par un automate et releve de la seule porte pedagogique de
    `nadia-prof` : que l'ecart reellement rendu entre deux boutons, une fois la page affichee sur
    un vrai ecran, soit effectivement confortable pour un geste d'enfant de CP. Le critere
    ci-dessus garantit la declaration, pas le rendu.
- **Note de frontiere de mandat** : `ines-design` propose la valeur exacte au-dessus du plancher,
  elle ne tranche pas la question pedagogique de savoir si c'est suffisant ; `nadia-prof` garde
  seule ce veto. Le plancher de 1 rem reprend mot pour mot la reserve 2 de `nadia-prof` sur S2-1
  ("Un ecart d'au moins 1 rem laisserait plus de place a un geste d'enfant.") ; cette story ne
  reinterprete pas sa formulation.
- **Lien decision** : reserve 2 de `squad/conformite/FICHE-bandit-manchot-additions-cp.md`
  (verdict S2-1 du 2026-08-05, point 2 bis) ; `DEC-0007-perimetre-s03-completer-avant-etendre.md`,
  point 4 (cette story est la reponse retenue a la question de l'appui rate compte comme erreur de
  calcul, plutot qu'une mecanique de confirmation).
- **Agent pressenti** : ines-design (proposition), mise en oeuvre par marc-dev / lea-dev /
  sofia-sm.

### S3-6 - Sur l'ecran de fin et sur l'ecran d'erreur, la sortie doit etre ce qui se voit, pas ce qui s'efface (reserve 4, S2-1 et S2-2)

- **Besoin** : en tant qu'enfant arrive a la fin d'une partie ou bloque par une erreur, je veux que
  la seule chose que je peux faire soit aussi la chose la plus visible de l'ecran, afin de savoir
  immediatement ou appuyer plutot que de chercher un bouton discret.
- **Competence programme visee** : transverse - hierarchie visuelle pour le niveau CP, meme
  categorie que S2-1.
- **Criteres d'acceptation** :
  - Etant donne l'ecran de fin de partie, quand on releve le style du bouton de sortie, alors il
    porte un remplissage plein (aplat de couleur), et non le style en contour retenu pour l'ecran
    de jeu et l'ecran de retour depuis S2-1.
  - Etant donne l'ecran d'erreur, quand on releve le style du bouton "Recommencer", alors il porte
    le meme remplissage plein, pour la meme raison (seule action possible sur cet ecran).
  - Etant donne l'ecran de jeu et l'ecran de retour (juste ou faux), quand on releve le style du
    bouton de sortie apres cette story, alors il reste en contour, sans regression sur ce que
    `nadia-prof` a accorde pour S2-1 (le bouton de sortie ne doit pas redevenir plus saillant que
    les boutons de reponse sur ces deux ecrans).
- **Note de frontiere de mandat** : `ines-design` propose la forme exacte du remplissage,
  elle ne tranche pas ; `nadia-prof` garde seule le veto pedagogique.
- **Lien decision** : reserve 4 de `squad/conformite/FICHE-bandit-manchot-additions-cp.md`
  (verdict S2-1 du 2026-08-05, point 2 bis, qui prolonge la reserve 4 de S2-2).
- **Agent pressenti** : ines-design (proposition), mise en oeuvre par marc-dev / lea-dev /
  sofia-sm.

### S3-7 - Une icone qui dessine un objet, pas une lettre (reserve S2-3)

- **Besoin** : en tant qu'enfant qui ne lit pas encore couramment, je veux reconnaitre recreo dans
  l'onglet de mon navigateur grace a un dessin d'objet, afin de ne pas avoir besoin de reconnaitre
  une lettre pour retrouver l'application.
- **Competence programme visee** : transverse - identite du produit, aucun contenu pedagogique.
- **Criteres d'acceptation** :
  - Etant donne le fichier d'icone livre par cette story, quand on l'inspecte, alors il repond
    toujours 200 sur `/favicon.ico`, avec un type d'image (memes criteres techniques que S2-3,
    inchanges).
  - Etant donne la vignette effectivement utilisee par le navigateur a 16 px, quand on l'inspecte,
    alors elle est dessinee pour cette taille et non obtenue par simple reduction du grand dessin
    (constat mesure en s02 : 5 pixels blancs purs sur 256 et 122 couleurs distinctes dans la
    vignette actuelle, signe d'une reduction floue).
  - Ce qui n'est pas testable par un automate et releve de la seule porte pedagogique de
    `nadia-prof` : que le dessin retenu represente effectivement un objet reconnaissable en un
    coup d'oeil pour un enfant de CP ou de CM1, sans lettre ni monogramme (elle a explicitement
    refuse cette derniere forme pour un sprint suivant). Aucun critere ci-dessus ne pretend juger
    si le dessin "dit jeu ou ecole" a un enfant qui ne lit pas : seule `nadia-prof` le peut.
- **Note de frontiere de mandat** : `ines-design` propose le dessin, elle ne tranche pas ;
  `nadia-prof` garde seule le veto pedagogique.
- **Lien decision** : reserve de `squad/conformite/FICHE-bandit-manchot-additions-cp.md` (verdict
  S2-1 du 2026-08-05, point 2 bis, "Ce que je demande pour un sprint suivant").
- **Agent pressenti** : ines-design (proposition du dessin), mise en oeuvre par marc-dev / lea-dev
  / sofia-sm.

### S3-8 - L'application reste jouable sur un ecran de la largeur d'un telephone tenu debout

- **Besoin** : en tant qu'enfant qui joue sur la tablette ou le telephone disponible a la maison
  plutot que sur un ordinateur, je veux que le jeu reste utilisable meme quand l'ecran est etroit
  et tenu a la verticale, afin de pouvoir jouer sur l'appareil que j'ai sous la main.
- **Competence programme visee** : transverse - accessibilite d'affichage, aucun contenu
  pedagogique.
- **Contexte** : `nadia-prof` a signale en s02 que son harnais de rendu ne peut pas juger le
  rendu sous ~500 px de large, sans trancher si un tel support est vise par le produit.
  `DEC-0007-perimetre-s03-completer-avant-etendre.md`, point 5, tranche : oui, 375 px est un
  support vise. `theo-archi`, dans son avis de faisabilite du sprint 3, a trouve un defaut qui
  rend la premisse de cette story caduque avant meme le debat d'outillage : `accueil.html` ne
  porte **aucune** balise `<meta name="viewport">`. Sans elle, un telephone rend la page dans une
  fenetre virtuelle d'environ 980 px puis reduit l'ensemble : la mise en page a 375 px n'est jamais
  reellement exercee sur un vrai telephone, quel que soit par ailleurs l'etat du CSS. Il a etabli
  aussi que les deux criteres centraux ci-dessous portent sur une propriete du rendu, verifiable
  seulement avec un moteur de mise en page ; l'ajouter demanderait la premiere dependance
  d'execution du depot hors JDK et Spring (navigateur sans tete, temps de build accru, nouveau
  mode de defaillance), ce qu'il ne recommande pas pour trois criteres visuels.
- **Perimetre reecrit** : cette story separe explicitement ce qui est verifiable aujourd'hui par
  automate de ce qui ne l'est pas, conformement a `ADR-0013`. Elle ne clot pas `DEC-0007` point 5 :
  seul le prealable ci-dessous est livre ce sprint (affecte a `marc-dev`, deja demarre au planning) ;
  le reste est explicitement reporte, pas tranche par defaut.
- **Criteres d'acceptation testables ce sprint (prealable)** :
  - Etant donne `accueil.html` rendu, quand on relit son `<head>`, alors il porte une balise
    `<meta name="viewport" content="width=device-width...">` (le premier segment de `content` vaut
    exactement `width=device-width`), absente aujourd'hui.
  - Etant donne l'ensemble de `recreo.css` et de tous les gabarits (perimetre derive d'un balayage
    du repertoire `templates/` et du fichier `recreo.css`, jamais une liste de selecteurs enumeree,
    `ADR-0013`), quand on releve toute largeur fixee en pixels, alors aucune ne depasse la largeur
    cible de 375 px et aucune regle ne force une largeur fixe sur un conteneur de mise en page.
- **Ce qui est report, et pourquoi** : les deux criteres suivants ne sont couverts par aucun
  automate du depot aujourd'hui et ne sont pas non plus jugeables par la porte pedagogique de
  `nadia-prof` avant qu'un harnais de rendu sous 500 px existe (question d'outillage deja routee a
  `yanis-qa` et `theo-archi` depuis s02, non tranchee). Ils restent au backlog, sans date, plutot
  que d'etre affirmes sans preuve :
  - Qu'a 375 px de large, aucun element ne depasse la largeur de la fenetre sur l'accueil, l'ecran
    de jeu, le retour juste, le retour faux, la fin de partie et l'ecran d'erreur (aucun
    defilement horizontal necessaire).
  - Qu'a cette meme largeur, chaque bouton actionnable de chaque ecran reste entierement visible et
    atteignable sans chevaucher un autre element.
- **Lien decision** : `DEC-0007-perimetre-s03-completer-avant-etendre.md`, point 5 (le support
  reste vise ; le perimetre effectivement livrable ce sprint est precise ici).
- **Agent pressenti** : `marc-dev` pour le prealable testable ci-dessus (deja demarre). Le reste de
  la story n'a pas de developpeur affecte tant que l'outillage n'est pas tranche par `theo-archi` /
  `yanis-qa`.

### S3-9 - Le nom du jeu s'ecrit comme il se lit ("Récréo")

- **Besoin** : en tant qu'enfant de CP qui dechiffre le titre a chaque fois que j'ouvre le jeu, je
  veux que ce mot s'ecrive avec ses accents comme n'importe quel autre mot que je lis dans le jeu,
  afin de ne pas apprendre une graphie fausse sur le premier mot que je vois.
- **Competence programme visee** : transverse - meme exigence positive de categorie B que S1-1
  (`DEC-0002`, amendement du 2026-08-04), appliquee ici au titre plutot qu'au texte de jeu.
- **Criteres d'acceptation** :
  - Etant donne l'ecran d'accueil rendu, quand on relit le titre affiche, alors il s'ecrit
    "Récréo", accents compris, et non "Recreo".
  - Etant donne tout autre gabarit ou fragment qui affiche le nom du produit (s'il en existe),
    quand on le relit, alors il porte la meme orthographe accentuee, pour qu'aucune des deux
    graphies ne cohabite dans l'application.
- **Lien decision** : `DEC-0007-perimetre-s03-completer-avant-etendre.md`, point 7 (reserve 10 de
  `squad/conformite/FICHE-bandit-manchot-additions-cp.md`, verdict s01).
- **Agent pressenti** : a affecter au planning.

## Sprint 4 - Ouvrir le francais, premiere matiere non mathematique du produit

**Contexte et decision de perimetre** : l'utilisatrice renverse la priorisation de `DEC-0007`
("completer avant etendre") - voir `DEC-0008-la-couverture-reprend-francais-cp-et-cm1.md`. Ce
sprint ouvre le francais, en parallele de l'audit de cloture de s03 sur mathematiques CP (qui
reste du ressort de `nadia-prof`, non affecte par cette ouverture). `DEC-0008` motive le choix du
francais - la matiere la plus eloignee de l'arithmetique parmi celles qui ont deja un referentiel,
donc celle qui teste le plus directement le decouplage mecanique/contenu annonce par `CLAUDE.md`
et `ADR-0004` - et le choix des deux competences precises, sur la base d'un inventaire reel de
`contenu-repris/` plutot que d'une intuition.

**Ce que ces deux stories demandent, et ce qu'elles ne demandent pas** : les deux jeux visent
l'unique forme d'item deja jouable par le domaine (`Question` : `enonce`, `bonneReponse`,
`distracteurs`, `ADR-0004`) et l'unique mecanique existante (bandit manchot). Si l'avis de
faisabilite de `theo-archi` au planning confirme que c'est bien le cas, chaque story est un
paquet YAML au schema deja connu (`ADR-0007`, type `question`) plus une ligne de catalogue -
**aucune ligne de code de mecanique, aucune modification du domaine**. C'est explicitement la
demonstration que `DEC-0008` demande : si l'un des deux contenus ne rentre pas dans cette forme
sans modification de `domain/mechanic` ou de l'interface scellee `Item`, ce n'est pas un detail
d'implementation, c'est un signal a remonter par `theo-archi` a `sofia-sm`, qui route ensuite vers
moi pour reformuler le perimetre de la story concernee.

Aucun contenu repris n'est valide pedagogiquement par sa seule presence dans `contenu-repris/` :
chacun des deux paquets ci-dessous repasse devant `nadia-prof` comme un contenu neuf
(`contenu-repris/README.md`, `CLAUDE.md`).

### S4-1 - Choisir le bon petit mot pour ne plus confondre les homophones grammaticaux (CM1)

- **Besoin** : en tant qu'eleve de CM1, je veux choisir le bon petit mot (par exemple "a" ou "à",
  "et" ou "est") pour completer une phrase, afin de m'entrainer a ne plus les confondre quand
  j'ecris.
- **Competence programme visee** : francais, CM1 (cycle 3), domaine "Orthographe et grammaire" -
  "Distinguer et utiliser correctement les principaux homophones grammaticaux (et/est, son/sont,
  a/à...)" (`squad/programme/francais/competences-cm1.md`). Premiere ligne de matrice couverte
  pour le CM1, tous jeux confondus (`DEC-0008`).
- **Criteres d'acceptation** :
  - Etant donne une manche generee par ce contenu, quand la question s'affiche, alors elle
    presente une phrase a trou portant sur un des couples d'homophones grammaticaux suivants :
    a/à, et/est, on/ont, son/sont, ou/où - jamais un couple hors de cette liste.
  - Etant donne les propositions affichees pour une manche, alors la bonne reponse y figure
    exactement une fois, et l'unique distracteur est l'autre mot du meme couple d'homophones
    (jamais un mot etranger a ce couple).
  - Etant donne l'ensemble des items du paquet, quand on les compte par couple d'homophones,
    alors chacun des cinq couples cites ci-dessus est represente par au moins un item (couverture
    minimale de la competence dans son ensemble, pas seulement d'un sous-ensemble).
  - Etant donne l'application demarree avec ce paquet, quand le test d'integration de chargement
    du contenu s'execute (`CatalogueChargeableIT`, `ADR-0007`), alors une partie demarre sans
    erreur sur ce paquet, avec la mecanique bandit manchot existante et sans qu'aucune ligne de
    `domain/mechanic` ni `domain/mechanics` n'ait ete modifiee pour l'accueillir.
  - Etant donne 20 manches jouees a la suite, quand on releve les phrases proposees, alors elles
    ne sont pas toutes identiques.
  - Ce qui n'est pas testable par un automate et releve de la seule porte pedagogique de
    `nadia-prof` : que les phrases soient d'un niveau de vocabulaire et de longueur adapte a un
    eleve de CM1, que les cinq couples retenus soient suffisants pour "distinguer et utiliser
    correctement" au sens du referentiel, et la conformite du texte au francais correct et
    integralement accentue (`DEC-0002`, amendement du 2026-08-04).
- **Note de frontiere de mandat** : si `theo-archi` etablit au planning que cette forme de contenu
  ne rentre pas dans `Question` sans changement du domaine, cette story est reformulee avant
  developpement plutot que forcee dans une forme qui ne lui convient pas.
- **Lien decision** : `DEC-0008-la-couverture-reprend-francais-cp-et-cm1.md`.
- **Agent pressenti** : a affecter au planning.

### S4-2 - Retrouver le son que fait un mot ecrit (CP)

- **Besoin** : en tant qu'eleve de CP, je veux lire un mot affiche et retrouver quel son il
  contient parmi plusieurs sons proposes, afin de m'entrainer a associer les lettres que je lis au
  son qu'elles font.
- **Competence programme visee** : francais, CP (cycle 2), domaine "Lecture et comprehension de
  l'ecrit" - "Associer les graphemes aux phonemes (voyelles, consonnes simples, puis digraphes
  ch, ou, on...)" (`squad/programme/francais/competences-cp.md`). Cette competence, et non
  "identifier un son dans un mot" (domaine "Langage oral et conscience phonologique"), parce que
  le contenu presente un mot ecrit et travaille la correspondance graphie-son, pas une
  reconnaissance purement orale - voir l'inventaire de `DEC-0008`.
- **Criteres d'acceptation** :
  - Etant donne une manche generee par ce contenu, quand la question s'affiche, alors elle
    presente un mot ecrit et un ensemble de propositions de sons, dont une seule correspond
    effectivement a un son present dans le mot affiche.
  - Etant donne les propositions d'une manche, alors les distracteurs sont des sons absents du
    mot affiche, jamais le meme phoneme note deux fois sous une forme differente.
  - Etant donne l'ensemble des items du paquet, quand on releve les sons couverts, alors les trois
    digraphes cites en exemple par le referentiel (ch, ou, on) y figurent chacun au moins une
    fois, en plus des autres sons deja presents dans la matiere premiere (an, in, oi).
  - Etant donne l'application demarree avec ce paquet, quand le test d'integration de chargement
    du contenu s'execute (`CatalogueChargeableIT`, `ADR-0007`), alors une partie demarre sans
    erreur sur ce paquet, avec la mecanique bandit manchot existante et sans qu'aucune ligne de
    `domain/mechanic` ni `domain/mechanics` n'ait ete modifiee pour l'accueillir.
  - Etant donne 20 manches jouees a la suite, quand on releve les mots proposes, alors ils ne sont
    pas tous identiques.
  - Ce qui n'est pas testable par un automate et releve de la seule porte pedagogique de
    `nadia-prof` : si la notation des sons entre crochets (par exemple "[an]", "[on]") est lisible
    et sans ambiguite pour un enfant de CP qui debute en lecture, si les mots ecrits en majuscules
    dans la matiere premiere doivent etre repris tels quels ou reformules, et le choix des mots
    eux-memes.
- **Note de frontiere de mandat** : meme note que S4-1 sur la faisabilite technique de la forme de
  contenu.
- **Lien decision** : `DEC-0008-la-couverture-reprend-francais-cp-et-cm1.md`.
- **Agent pressenti** : a affecter au planning.

## Sprint 5 - Lever le refus de "Le bon son" et completer les homophones CM1

**Contexte** : `nadia-prof` a rendu son verdict sur les trois increments de s04 le 2026-08-06,
dans trois fiches distinctes : `squad/conformite/FICHE-bandit-manchot-additions-cp.md`,
`FICHE-bandit-manchot-le-bon-son-cp.md`, `FICHE-bandit-manchot-homophones-cm1.md`. Mathematiques
CP passe a `couvert-valide` sans qu'aucune story ne soit necessaire (l'audit porte sur
l'increment de s03, deja fusionne). Francais CM1 est accorde sous reserve, mais cinq couples
d'homophones manquent a "les principaux" et un couple pour dix manches interdit toute variete
entre deux parties. Francais CP est **REFUSE** sur cinq motifs : ce jeu,
`bandit-manchot-le-bon-son-cp` ("Le bon son"), est retire du catalogue jouable jusqu'a sa correction
(`DEC-0009-retrait-le-bon-son-du-catalogue.md`), decision de perimetre distincte des corrections
elles-memes.

**Priorisation** : S5-1 execute le retrait de perimetre (`DEC-0009`) et n'attend aucune des
corrections suivantes pour etre livree. S5-2 a S5-4 corrigent le contenu de "Le bon son" (motifs
1, 2, 3, 4 du refus) ; elles sont necessaires avant tout nouvel audit de ce jeu. Leurs criteres
restent independants sur le fond, mais le premier critere de S5-2 depend techniquement de S5-3 - il
compare la reponse telle qu'affichee a l'enfant, donc sans les crochets que S5-3 retire - et doit
donc etre livre apres elle. S5-5 decrit un besoin dont la solution est un constat d'architecture
(motif 5) : elle est routee a `theo-archi` via `sofia-sm`, pas affectee directement a un
developpeur. S5-6 est la condition ecrite par `nadia-prof` pour que la ligne francais CM1 passe a
`couvert-valide` ; S5-7 traite une reserve distincte (variete) que S5-6 attenue mais ne clot pas
necessairement seule. S5-8 corrige le titre du produit sur decision de l'utilisatrice. S5-9, ecrite
a la suite de l'avis de faisabilite de `theo-archi`, detache du deuxieme critere de S5-2 un besoin
qui demande un champ de contenu inexistant et une decision pedagogique non prise : elle existe au
backlog mais n'entre pas dans le perimetre de ce sprint (`DEC-0011`).

**Aucune des stories S5-2 a S5-5 ne remet automatiquement `bandit-manchot-le-bon-son-cp` au
catalogue.** `DEC-0009` retire ce jeu jusqu'a ce qu'un increment corrige repasse devant
`nadia-prof` et obtienne un nouveau Done pedagogique, constate dans une nouvelle fiche de
conformite : corriger un motif de refus et fusionner le code ne suffit pas a lever le retrait.
Aucune story de ce sprint n'annule S5-1 ; elle ne le sera que par une story ecrite au moment ou
`nadia-prof` aura rendu ce nouveau verdict.

**Perimetre de ce sprint** (`DEC-0011-perimetre-s05-reste-correctif.md`) : s05 reste entierement
correctif, aucune ligne de matrice de plus n'entre `en cours`. Le motif n'est pas celui de
`DEC-0007` (aucune regle generale n'interdit d'ouvrir une matiere avant qu'une autre ne soit
`couvert-valide`) : la reserve de `contenu-repris/` ne contient plus, a ce jour, de candidat en
correspondance integrale avec une ligne de referentiel non encore ouverte - les deux candidats les
plus proches (`textes-trous/cm1-accords.yml`, `textes-trous/cm1-conjugaison.yml`) ne couvrent
chacun qu'une partie de leur competence, exactement le defaut qui vient de bloquer `couvert-valide`
sur les homophones CM1. Ce report est borne au sprint 6, pas indefini (voir `DEC-0011` et la
section "Backlog - sprints suivants" plus bas).

### S5-1 - Retirer "Le bon son" de la liste des jeux jusqu'a sa correction (execution de DEC-0009)

- **Besoin** : en tant qu'eleve de CP, je ne veux pas trouver dans la liste des jeux un jeu qui me
  demande "quel son entends-tu ?" sans qu'aucun son ne soit joue, afin de ne pas apprendre quelque
  chose de faux en jouant, en attendant que ce jeu soit corrige.
- **Competence programme visee** : transverse - decision de perimetre, pas de contenu
  pedagogique.
- **Criteres d'acceptation** :
  - Etant donne le catalogue de jeux servi par l'application, quand l'enfant consulte la liste des
    jeux depuis l'accueil, alors "Le bon son" (`bandit-manchot-le-bon-son-cp`) n'y figure plus.
  - Etant donne un enfant dont la session est deja ouverte sur une manche de ce jeu au moment du
    retrait (reprise depuis `GET /`, ou `GET /parties/{id}/manche`), quand il envoie sa reponse a
    cette manche, alors elle n'aboutit a aucun resultat, juste ou faux, sur le contenu retire :
    elle tombe sur l'ecran d'erreur. **Ce critere reste explicitement ouvert, il n'est pas repute
    ferme par ce comportement** : l'ecran obtenu est un ecran d'erreur, pas l'ecran d'accueil, et
    choisir l'ecran definitif (accueil, message de partie perdue de S1-4, ou autre) est un choix de
    produit qui reste a faire avant de fermer ce critere.
  - Etant donne les deux autres jeux du catalogue (`bandit-manchot-additions-cp`,
    `bandit-manchot-homophones-cm1`), quand on les rejoue apres cette story, alors ils fonctionnent
    exactement comme avant : ce retrait ne touche a aucun autre jeu ni a la mecanique bandit
    manchot.
  - Etant donne le paquet de contenu et la fiche de conformite de ce jeu, quand on les consulte
    apres cette story, alors ils restent presents dans le depot (rien n'est supprime, seul l'acces
    en jeu est coupe) : ils restent la matiere de travail de S5-2 a S5-5.
- **Note** : le choix technique (retirer la ligne du catalogue, la desactiver, ou tout autre
  mecanisme) appartient a `theo-archi` et aux developpeurs ; cette story decrit le comportement
  attendu, pas l'implementation.
- **Note sur le deuxieme critere, corrigee par `theo-archi` (`avis-faisabilite.md`, section S5-1)**
  : la version precedente de cette note disait que la reprise par URL directe restait ouverte et
  qu'un enfant "pourrait terminer une manche sur le contenu refuse". Les deux sont fausses, mesure
  faite dans le code : le chemin par URL directe (`POST /parties/{id}`, `POST /parties/{id}/coups`)
  etait deja ferme **avant** `DEC-0009` - les deux verifient le jeu contre le catalogue et levent
  une exception rendue par l'ecran d'erreur - et aucun enfant ne peut terminer une manche du jeu
  retire, par quelque chemin que ce soit : la premiere reponse envoyee echoue toujours vers l'ecran
  d'erreur. Ce qui reste reellement ouvert, et que le critere ci-dessus decrit desormais : `GET /`
  en reprise et `GET /parties/{id}/manche` affichent encore une question du jeu retire, jusqu'a
  cette premiere reponse qui echoue. `nadia-prof` juge cette suite acceptable pour l'instant
  (`squad/conformite/FICHE-bandit-manchot-additions-cp.md`, section 4.2) au motif que le catalogue
  est charge une fois au demarrage et que les sessions vivent en memoire : redemarrer pour
  appliquer un retrait detruit toutes les sessions, la fenetre d'exposition est nulle et non
  seulement petite. `theo-archi` confirme ce raisonnement et liste, par cout croissant, ce qui le
  rendrait faux et devrait rouvrir ce critere - **le premier n'est pas `adapter-persistence`** :
  (1) `server.servlet.session.persistent=true` ou tout mecanisme qui fait survivre les sessions
  Tomcat a un redemarrage, une ligne de configuration ; (2) Spring Session (Redis, JDBC) ajoute
  pour une autre raison ; (3) `adapter-persistence` (`ADR-0001`, `DEC-0003`), le declencheur que
  `nadia-prof` cite, troisieme dans l'ordre de vraisemblance et non le premier ; (4) un catalogue
  rechargeable sans redemarrage ; (5) plusieurs instances derriere un repartiteur avec un magasin
  de sessions partage. Ce critere reste donc sciemment connu et non couvert par le code actuel,
  plutot que d'etre repute satisfait a tort, et il se rouvre au premier de ces cinq signaux qui
  devient vrai, pas seulement au troisieme.
- **Lien decision** : `DEC-0009-retrait-le-bon-son-du-catalogue.md`.
- **Agent pressenti** : a affecter au planning.

### S5-2 - Le jeu demande reellement d'entendre un son, pas de reperer les memes lettres (motifs 1 et 3 du refus)

- **Besoin** : en tant qu'eleve de CP, je veux que reussir "Le bon son" demande de reconnaitre le
  son que fait le mot que je lis, et pas seulement de retrouver les memes lettres dans la reponse,
  afin que jouer m'entraine vraiment a associer les graphemes aux phonemes.
- **Competence programme visee** : francais, CP (cycle 2), domaine "Lecture et comprehension de
  l'ecrit" - "Associer les graphemes aux phonemes (...)" (meme ligne que S4-2,
  `squad/programme/francais/competences-cp.md`).
- **Criteres d'acceptation** :
  - Etant donne l'ensemble des items du paquet corrige, quand on verifie pour chacun si la reponse
    attendue - telle qu'elle est montree a l'enfant apres S5-3 (sans crochets, en minuscules,
    accents rabattus) - figure litteralement dans le mot ecrit, normalise de la meme facon, alors
    une majorite des items (plus de la moitie, donc au moins 13 sur 24) n'est pas resoluble par
    cette seule recherche visuelle - contre 1 item sur 24 non resoluble avant correction, une fois
    la meme normalisation appliquee (23 des 24 items sont aujourd'hui resolubles par les lettres
    seules, `DENT` etant le seul qui resiste). Ce critere depend de S5-3 : sans le retrait des
    crochets, la comparaison porterait sur un texte que l'enfant ne voit jamais, et serait vert
    sans rien changer au fond.
  - **Retire de cette story, detache en S5-9** : le besoin d'un retour qui explique la variation
    grapheme/phoneme sur un item comme `DENT` demande un champ de contenu qui n'existe pas et une
    decision pedagogique sur le moment ou ce texte s'affiche - ni l'un ni l'autre n'est du contenu
    seul (`theo-archi`, `avis-faisabilite.md`, section S5-2). Voir S5-9.
  - Etant donne qu'aucun fichier audio n'existe pour ce paquet, quand la consigne est reecrite dans
    l'entree de catalogue qui remet ce jeu devant `nadia-prof` pour son audit (branche jetable, voir
    le planning de s05 - la ligne de catalogue ne fusionne pas sur `round-two` avant son verdict,
    `DEC-0009`), alors elle ne contient aucune reference a un son entendu (le verbe "entendre" ou
    ses formes) et decrit l'action de lecture reellement demandee. Ce critere ne porte sur aucun
    fichier de `round-two` pendant ce sprint : la consigne, comme l'entree de catalogue qui la
    porte, n'existe plus dans le depot depuis S5-1.
- **Relation a S5-4** : les deux stories corrigent des motifs distincts du meme paquet (motif 3
  ici, motif 4 dans S5-4) et ne se contredisent pas sur le fond. Elles se contredisaient sur la
  methode de verification : le premier critere ci-dessus rend la majorite des items non resolubles
  par les lettres, ce qui rend caduque toute mesure de S5-4 qui compterait, elle aussi, des lettres
  (`theo-archi`, `avis-faisabilite.md`, sections S5-2 et S5-4). S5-4 range donc son critere du cote
  de la seule porte pedagogique plutot que de garder un automate par lettres. Aucune fusion : les
  deux stories restent distinctes, chacune sur son motif.
- **Note** : cette story revise la conception de l'exercice, pas seulement ses mots ; le contenu
  final (choix des mots, formulation exacte, retours) repasse devant `nadia-prof` avant
  integration, comme tout contenu neuf.
- **Lien decision** : motifs 1 et 3 de `squad/conformite/FICHE-bandit-manchot-le-bon-son-cp.md`.
- **Agent pressenti** : a affecter au planning.

### S5-3 - Les sons proposes sans notation d'adulte (motif 2 du refus)

- **Besoin** : en tant qu'eleve de CP qui apprend que chaque signe ecrit correspond a un son, je
  veux que les sons proposes comme reponse s'ecrivent comme je les lis, sans caractere que je n'ai
  jamais appris, afin de ne pas dechiffrer du bruit a la place d'un son.
- **Competence programme visee** : francais, CP (cycle 2), meme ligne que S5-2.
- **Criteres d'acceptation** :
  - Etant donne une proposition affichee a l'enfant, quand on la relit, alors elle ne contient
    aucun caractere `[` ni `]`.
  - Etant donne les six sons du paquet (an, on, in, ou, oi, ch), quand ils s'affichent comme
    reponse, alors chacun s'ecrit avec exactement les lettres qui le composent, sans aucun signe
    supplementaire.
  - Ce qui n'est pas testable par un automate et releve de la seule porte pedagogique de
    `nadia-prof` : si une marque visuelle distinguant "ceci est un son" est necessaire, et sa forme
    (couleur, encadre, pictogramme) - le motif 2 le laisse explicitement ouvert, cette story ne le
    tranche pas.
- **Lien decision** : motif 2 de `squad/conformite/FICHE-bandit-manchot-le-bon-son-cp.md`.
- **Agent pressenti** : a affecter au planning.

### S5-4 - Chaque item n'admet qu'une seule reponse vraie (motif 4 du refus)

- **Besoin** : en tant qu'eleve de CP qui entend correctement un son, je veux ne jamais etre compte
  faux pour avoir entendu un son reellement present dans le mot simplement parce que le jeu
  n'attendait que l'autre, afin de ne pas apprendre a me mefier de ce que j'entends correctement.
- **Competence programme visee** : francais, CP (cycle 2), meme ligne que S5-2.
- **Criteres d'acceptation** :
  - Ce qui n'est pas testable par un automate et releve de la seule porte pedagogique de
    `nadia-prof`, en jouant et en ecoutant ce qu'un mot fait reellement entendre : que chaque item
    du paquet corrige ne fasse entendre qu'un seul des six sons du jeu, jamais deux - le defaut
    mesure aujourd'hui sur trois items (`MOUCHE`, `MOUTON`, `BALANCOIRE`), ou deux sons du jeu sont
    reellement audibles. Aucun automate ne peut le verifier de facon fiable : compter les lettres
    presentes dans le mot n'est qu'un substitut qui coincide avec l'oreille tant que le grapheme
    suit le phoneme, et il se trompe deja aujourd'hui sur `DENT` (`[an]` note `en` : zero son
    trouve par les lettres, un par l'oreille). Il se degraderait davantage a mesure que S5-2
    reussit a rendre le paquet moins resoluble par les lettres (`theo-archi`,
    `avis-faisabilite.md`, section S5-4) : un automate par lettres serait vert ou rouge par
    coincidence, pas par verification, ce qu'`ADR-0013` interdit d'ecrire.
  - Ce qui n'est pas testable par un automate non plus : la solution retenue pour chaque item
    concerne (retirer l'item, changer le mot, ou reformuler la consigne pour designer une position
    dans le mot) est un choix de contenu que `nadia-prof` revoit comme tout item neuf.
- **Relation a S5-2** : voir la note "Relation a S5-4" de S5-2. Les deux motifs (3 et 4) restent
  distincts, cette story n'est pas fusionnee dans S5-2.
- **Lien decision** : motif 4 de `squad/conformite/FICHE-bandit-manchot-le-bon-son-cp.md`.
- **Agent pressenti** : a affecter au planning.

### S5-5 - Un enfant qui echoue plusieurs fois dans "Le bon son" recoit une aide, comme dans les autres jeux (motif 5 du refus, constat structurel)

- **Besoin** : en tant qu'eleve de CP qui rate plusieurs fois de suite dans "Le bon son", je veux
  recevoir une aide sur la question suivante, comme dans le jeu de calcul, afin de ne pas rester
  bloque sans aucun recours jusqu'a la fin de la partie.
- **Competence programme visee** : transverse - comportement generique de la mecanique bandit
  manchot face a l'echec repete, meme perimetre que S1-5 et S3-2, applique ici a un type de
  contenu qui n'en beneficie pas encore.
- **Constat qui motive la story, pas une solution** : `nadia-prof` a joue une partie complete en
  repondant faux a chaque manche et montre qu'aucun indice n'apparait jamais dans ce jeu, meme
  apres dix echecs consecutifs. La cause n'est pas un oubli de contenu : c'est que
  `QuestionContentSourceFactory` construit chaque `Question` sans champ `indice`, donc un paquet
  `type: question` ne peut porter d'indice quel que soit ce qu'un auteur ecrit dans le YAML - a la
  difference de `type: additions-bornees`, qui en porte un depuis S3-2/S3-3. La meme limite est
  nommee en reserve non bloquante sur `bandit-manchot-homophones-cm1` (reserve 6 de sa fiche) : la
  corriger beneficie aux deux jeux `type: question` existants.
- **Criteres d'acceptation** :
  - Etant donne un item du paquet `cp-le-bon-son` portant un indice dans son fichier de contenu,
    quand l'enfant rate trois manches consecutives dans ce jeu, alors la manche suivante affiche
    cet indice, exactement comme dans `bandit-manchot-additions-cp` (meme declenchement que S3-2).
  - Etant donne le meme mecanisme applique au paquet `cm1-homophones`, quand un indice y est ecrit,
    alors il peut lui aussi s'afficher dans les memes conditions : aucun des deux jeux ne doit
    rester exclu du dispositif d'aide une fois qu'il existe pour les paquets `type: question`.
- **Note de routage** : le constat est d'architecture, pas de produit. La modelisation qui
  permettra a un paquet `type: question` de porter un indice (extension de `Question`, nouveau
  champ, ou toute autre forme) n'est pas de mon ressort et n'est pas prescrite ici. Je remonte ce
  besoin a `sofia-sm`, qui route vers `theo-archi`.
- **Lien decision** : motif 5 de `squad/conformite/FICHE-bandit-manchot-le-bon-son-cp.md` ;
  reserve 6 de `squad/conformite/FICHE-bandit-manchot-homophones-cm1.md`.
- **Agent pressenti** : `theo-archi` (etude de faisabilite et choix de modelisation), mise en
  oeuvre a affecter au planning apres son avis.

### S5-6 - Cinq couples d'homophones qui manquent a "les principaux" (condition de nadia-prof pour couvert-valide)

- **Besoin** : en tant qu'eleve de CM1, je veux m'entrainer aussi sur "ce/se", "ces/ses",
  "la/là/l'a", "mais/mes" et "peu/peut", afin de vraiment m'entrainer sur les homophones
  grammaticaux les plus courants et pas seulement sur une partie d'entre eux.
- **Competence programme visee** : francais, CM1 (cycle 3), domaine "Orthographe et grammaire" -
  "Distinguer et utiliser correctement les principaux homophones grammaticaux"
  (`squad/programme/francais/competences-cm1.md`). Condition ecrite par `nadia-prof` pour que
  cette ligne de `squad/programme/MATRICE-couverture.md` passe a `couvert-valide`.
- **Criteres d'acceptation** :
  - Etant donne l'ensemble des items du paquet corrige, quand on les compte par couple
    d'homophones, alors les cinq couples ajoutes (ce/se, ces/ses, la/là/l'a, mais/mes, peu/peut)
    sont tous representes par au moins un item chacun - c'est la condition ecrite par `nadia-prof`
    au mot pres ("ces cinq couples ajoutes au paquet"). Les cinq couples deja couverts (a/à,
    et/est, on/ont, son/sont, ou/où) ne sont pas remis en cause par cette story.
  - Exigence ajoutee par `camille-po`, distincte de la condition de `nadia-prof` ci-dessus : chaque
    couple, ancien et nouveau, est represente dans les deux sens (par exemple "et" bonne reponse
    dans un item, "est" bonne reponse dans un autre), pour rester coherent avec la forme des dix
    items existants, qui suivent deja ce schema. Si un item ajoute ne le respecte pas pour un
    couple donne, ce n'est pas un motif de refus au titre de la condition ecrite de `nadia-prof` -
    seule l'absence totale d'un des cinq couples l'est. **Pour le couple `la/là/l'a`**, ce critere
    s'applique une fois que `nadia-prof` aura arrete le nombre de distracteurs de cet item (voir la
    note ci-dessous) : il ne precede pas ce choix.
  - Ce qui n'est pas testable par un automate et releve de la seule porte pedagogique de
    `nadia-prof`, en lisant chaque phrase : que chaque item ajoute exige de regarder ailleurs dans
    la phrase qu'immediatement avant le trou pour trouver la bonne reponse (meme exigence que sa
    reserve 2, traitee dans le meme geste sur sa demande explicite) - au moins la moitie des dix
    nouveaux items, le modele qu'elle cite elle-meme etant "Papa et maman font la cuisine.".
    Etablir ou se trouvent le sujet, l'accord ou l'indice qui leve l'ambiguite dans une phrase est
    de l'analyse grammaticale du francais, qu'aucun garde-fou de ce depot ne fait ni ne fera pour
    dix phrases (`theo-archi`, `avis-faisabilite.md`, section S5-6) : un substitut automatise (par
    exemple "le mot precedant le trou n'est pas un pronom") donnerait un vert ou un rouge faux la
    moitie du temps, ce qu'`ADR-0013` interdit d'ecrire. Le seuil "la moitie" reste celui retenu
    par `camille-po` pour donner une mesure a `nadia-prof` en train de lire ; elle n'a chiffre
    aucune proportion elle-meme, elle a nomme l'exigence et cite ce modele.
  - Etant donne les dix items deja existants du paquet, quand on les relit apres cette story, alors
    ils ne sont pas modifies : cette story ajoute, elle ne corrige pas les items existants.
- **Note** : `nadia-prof` lie explicitement les deux exigences qu'elle a ecrites ("ces cinq
  couples ajoutes au paquet, avec la reserve 2 traitee dans le meme geste [...] parce qu'ajouter
  cinq couples faciles ne ferait qu'allonger un exercice de CE2") : cette story ne les separe pas.
  Le critere sur les deux sens, en revanche, n'est pas d'elle : il est signale comme tel ci-dessus
  pour ne pas laisser croire que son refus porterait sur un point qu'elle n'a pas pose.
- **Note sur le couple `la/là/l'a`, ajoutee apres l'avis de `theo-archi`** : ce couple est en
  realite un triplet (trois mots, pas deux), et le test qui epingle les couples du paquet exige
  aujourd'hui un unique distracteur par item - une propriete que `nadia-prof` porte elle-meme au
  credit du paquet (`FICHE-bandit-manchot-homophones-cm1.md` : "aucun distracteur absurde possible,
  et aucune devinette"). Deux sorties existent (un item a deux distracteurs, ou deux items pairwise
  `la`/`là` puis `la`/`l'a`) et **le choix est pedagogique, pas technique** : cette story ne le
  tranche pas, elle est routee a `nadia-prof` pour arbitrage, et l'item ou les items de ce couple
  precis ne sont ecrits qu'une fois son avis recu. Les quatre autres couples (ce/se, ces/ses,
  mais/mes, peu/peut) n'attendent pas cet arbitrage.
- **Lien decision** : "Suites hors de ma main" de
  `squad/conformite/FICHE-bandit-manchot-homophones-cm1.md`.
- **Agent pressenti** : a affecter au planning.

### S5-7 - Une partie de CM1 qui ne se repete pas a l'identique (reserve 1)

- **Besoin** : en tant qu'eleve de CM1, je veux que deux parties du meme jeu ne me presentent pas
  exactement les memes dix phrases dans un ordre different, afin qu'il reste quelque chose a
  apprendre a la deuxieme partie et pas seulement a s'en souvenir.
- **Competence programme visee** : francais, CM1 (cycle 3), meme ligne que S5-6. Contribue a
  `couvert-valide` sans en etre la condition ecrite (voir S5-6 pour la condition).
- **Criteres d'acceptation** :
  - Etant donne le paquet de contenu apres cette story, quand on compte ses items, alors il en
    compte davantage que le nombre de manches d'une partie (10), de sorte qu'une partie ne
    presente jamais l'integralite du paquet.
  - Etant donne deux parties completes jouees a la suite avec des graines distinctes, quand on
    compare les phrases presentees dans chacune, alors elles ne sont pas identiques (au moins une
    phrase differe entre les deux parties).
- **Note** : les dix items ajoutes par S5-6 contribuent deja a ce critere de nombre ; cette story
  reste ecrite independamment parce que la reserve qu'elle traite (aucune variete entre deux
  parties) subsisterait meme si S5-6 n'etait pas livree dans le meme sprint.
- **Lien decision** : reserve 1 de `squad/conformite/FICHE-bandit-manchot-homophones-cm1.md`.
- **Agent pressenti** : a affecter au planning.

### S5-8 - Le nom du produit s'ecrit "Recreo", sans accent (execution de DEC-0010, revert de S3-9)

- **Besoin** : en tant qu'enfant qui ouvre l'application, je veux voir le nom exact du jeu tel que
  ma famille me l'a dit ("Recreo"), afin de retrouver le meme mot partout, a l'ecran comme a
  l'oral.
- **Competence programme visee** : transverse - decision de perimetre sur le nom du produit, pas
  de contenu pedagogique (`DEC-0010`).
- **Contexte** : `DEC-0007` point 7 avait fait passer le titre a "Récréo" (story S3-9), execute
  par `ines-design`. L'utilisatrice corrige explicitement : le produit s'appelle "Recreo", sans
  accent. `DEC-0010` remplace le point 7 de `DEC-0007` en consequence.
- **Criteres d'acceptation** :
  - Etant donne l'ecran d'accueil rendu, quand on relit le titre affiche, alors il s'ecrit
    "Recreo", sans accent, et non "Récréo".
  - Etant donne tout autre gabarit ou fragment qui affiche le nom du produit (`<title>` inclus),
    quand on le relit, alors il porte la meme orthographe non accentuee, pour qu'aucune des deux
    graphies ne cohabite dans l'application.
  - Etant donne un test automatise nouveau, quand `mvn verify` s'execute, alors il verifie que le
    titre rendu par `accueil.html` est exactement "Recreo" - ce test n'existait pour aucune des
    deux graphies avant cette story. `yanis-qa` avait signale ce trou en s03
    (`squad/sprints/s03-completer-mathematiques-cp/rapport-qualite.md`, ligne 217 : "aucun test
    automatise ne l'assertionne : une regression [...] ne serait pas attrapee par `mvn verify`"),
    et il est toujours absent a ce jour, verifie avant l'ecriture de cette story.
- **Note** : cette story n'est pas seulement un changement de texte. Le trou de tracabilite nomme
  par `yanis-qa` en s03 existait deja pour "Récréo" et se reproduirait a l'identique pour "Recreo"
  si le test n'etait pas ecrit dans le meme increment - une deuxieme regression non attrapee sur le
  meme point serait imputable a cette story, pas seulement a S3-9.
- **A signaler, pas a corriger par cette story** : la reserve 10 de `nadia-prof`
  (`squad/conformite/FICHE-bandit-manchot-additions-cp.md`, verdict s01) est enregistree par elle
  comme tenue apres S3-9 ; ce revirement de perimetre la contredit dans les faits sans remettre en
  cause son jugement au moment ou elle l'a rendu. `camille-po` le lui signale explicitement plutot
  que de modifier sa fiche, qui ne lui appartient pas.
- **Lien decision** : `DEC-0010-le-titre-reste-recreo-sans-accent.md`.
- **Agent pressenti** : a affecter au planning.

### S5-9 - Un retour qui explique la variation grapheme/phoneme (detachee de S5-2)

- **Besoin** : en tant qu'eleve de CP qui se trompe sur un item ou le grapheme ecrit differe de la
  transcription phonetique de la bonne reponse (comme "DENT" pour le son note "an"), je veux
  comprendre pourquoi ce mot s'ecrit autrement que ce que j'entends, afin de ne pas retenir que
  j'ai simplement fait une erreur alors que le mot suit une regle differente.
- **Competence programme visee** : francais, CP (cycle 2), meme ligne que S5-2 (grapheme/phoneme,
  `squad/programme/francais/competences-cp.md`).
- **Contexte** : ce besoin etait le deuxieme critere de S5-2. `theo-archi` a montre
  (`avis-faisabilite.md`, section S5-2) qu'il demande un champ de contenu qui n'existe pas
  (`Verdict` ne porte que `juste` et `bonneReponse`) et une decision pedagogique sur le moment
  d'affichage qu'aucune story n'a encore prise : toujours sur les items a variation, seulement sur
  reponse fausse, ou autre. Ni l'un ni l'autre n'est du contenu seul : je la detache plutot que de
  demander a S5-2 de faire une modification de modele sans le dire.
- **Criteres d'acceptation** :
  - **Prealable, non code** : `nadia-prof` arrete sur quels items l'explication s'affiche
    (seulement ceux a variation grapheme/phoneme comme `DENT`, ou tout item ou l'enfant se trompe)
    et a quel moment (systematiquement, seulement sur reponse fausse, seulement une fois). Aucun
    critere ci-dessous n'est ferme avant que ce choix soit ecrit dans cette story ou dans une fiche
    de conformite.
  - Une fois ce choix ecrit : etant donne un item portant une explication de variation dans son
    contenu, quand la condition que `nadia-prof` aura fixee se produit, alors l'enfant lit cette
    explication plutot que le seul retour juste/faux habituel ("Non, c'est [reponse]."). Le champ
    et son affichage sont concus par `theo-archi` une fois cette story rouverte avec sa condition
    ecrite (meme trajet que l'indice de S1-5, `DEC-0005`).
  - Ce qui n'est pas testable par un automate et releve de la seule porte pedagogique de
    `nadia-prof` : le texte exact de chaque explication, item par item.
- **Note de perimetre** : cette story n'entre pas dans le sprint 5 (`DEC-0011` : le sprint reste
  correctif sur "Le bon son" et les homophones CM1). Elle est ecrite ici pour exister au sens du
  depot, pas pour etre codee ce sprint.
- **Lien decision** : motif 3 (deuxieme partie) de
  `squad/conformite/FICHE-bandit-manchot-le-bon-son-cp.md` ; detachee du deuxieme critere de S5-2
  sur avis de `theo-archi`.
- **Agent pressenti** : a affecter, pas avant que `nadia-prof` ait tranche le declencheur.

## Sprint 6 - Un gabarit habille (en-tete, icone, menu, contenu centre) et l'ouverture francais engagee par DEC-0011

**Contexte** : l'utilisatrice revient une deuxieme fois sur le design, en citant colorlib.com comme
inspiration visuelle et en nommant deux manques precis : un en-tete avec une icone et un menu, et un
contenu centre dans la page plutot que colle en haut a gauche. Le constat est verifie sur le code :
`accueil.html` est aujourd'hui un `<h1>` nu suivi d'une liste de boutons, sans aucun conteneur. Le
lot Bootstrap de s04/s05 (`ADR-0014`) a change le rendu de base sans jamais toucher la structure de
la page - Bootstrap 5.3.8 est deja vendorise et fournit la grille, le conteneur centre et les
composants de barre de navigation necessaires a ce que demandent S6-1 et S6-2 ci-dessous, sans
telechargement ni build supplementaire.

`DEC-0012-rhabillage-gabarit-menu-minimal-et-ouverture-s06.md` tranche que ce chantier ouvre le
sprint 6, aux cotes de l'ouverture francais deja engagee par `DEC-0011` (au moins une des deux lignes
`textes-trous/cm1-accords.yml` ou `textes-trous/cm1-conjugaison.yml`, pas encore detaillee en
story a la date de cette decision). Les deux chantiers portent sur des artefacts disjoints et
peuvent avancer en parallele ; si la capacite du sprint ne permet pas les deux, l'ouverture francais
garde la priorite (engagement deja pris envers l'utilisatrice sur le rythme de couverture), et c'est
`sofia-sm` qui le constate au planning, pas `camille-po` par avance.

**Contraintes non negociables, valables pour S6-1 et S6-2** (`CLAUDE.md`, `ADR-0008`, `ADR-0014`) :
aucune ressource externe, aucun CDN, aucune police telechargee a l'execution - l'application doit
fonctionner hors ligne, et le site cite en inspiration est une reference visuelle, pas un fichier a
importer ; zero build Node, zero serveur front separe ; le comportement de jeu reste sur le serveur,
et le JavaScript de Bootstrap (dont ses menus repliables) n'entre pas dans ce depot sans un ADR
nomme qui l'ouvre explicitement (`ADR-0014` point 7) - si une entree de menu venait a exiger un
repli anime, ce serait un besoin technique nouveau, a signaler plutot qu'a supposer acquis.

**Frontiere de mandat, valable pour les deux stories** : `ines-design` propose la forme, la couleur,
le contraste et la disposition ; elle ne tranche pas. `nadia-prof` garde seule le veto pedagogique
sur ce que voit l'enfant. Aucune des deux stories ne porte de competence programme : elles sont
transverses, `squad/programme/MATRICE-couverture.md` n'est pas modifiee par ce chantier.

### S6-1 - Un en-tete qui porte l'identite de recreo et une unique entree de menu vers l'accueil

- **Besoin** : en tant qu'enfant qui ouvre recreo, je veux voir en haut de chaque ecran un petit
  dessin et le nom du jeu, avec un moyen de revenir a la liste des jeux toujours au meme endroit,
  afin de reconnaitre l'application et de savoir ou revenir sans chercher un bouton different sur
  chaque ecran.
- **Competence programme visee** : transverse - identite et navigation du produit, aucun contenu
  pedagogique (meme categorie que S2-2, S2-3).
- **Decision de contenu du menu** (`DEC-0012`) : le menu contient **exactement une entree**,
  "Accueil", equivalente au bouton "Les jeux" deja present dans chaque etat de l'ecran de jeu.
  Aucune autre entree n'est ajoutee : l'accueil n'a nulle part ou aller depuis lui-meme, et un filtre
  par niveau (CP / CM1) n'existe pas encore comme fonctionnalite serveur - l'ouvrir dans le menu
  avant qu'elle existe inventerait une entree qui ne mene nulle part, ce que je m'interdis. Voir
  `DEC-0012` pour le raisonnement complet et la reserve associee en fin de ce document ("Backlog -
  sprints suivants").
- **Cette story n'en retire pas une autre** : le bouton "Les jeux" deja present dans
  `fragments/manche.html` et `fragments/retour.html` n'est pas supprime par cette story. L'entree de
  menu s'y ajoute ; les regrouper en un seul mecanisme est un choix distinct, non decide ici.
- **Criteres d'acceptation testables** :
  - Etant donne `accueil.html` apres cette story, quand on relit sa structure, alors un en-tete
    (contenant l'icone et le menu) est un frere ou un ancetre de la zone `#jeu` remplacee par htmx,
    jamais un descendant : il n'est donc jamais efface ni recree quand `#jeu` est remplace par un
    fragment de jeu.
  - Etant donne cet en-tete, quand on compte ses liens ou boutons qui changent d'ecran, alors il en
    compte exactement un, portant le texte "Accueil" et menant a l'accueil (par exemple via le meme
    mecanisme que le bouton "Les jeux" existant).
  - Etant donne l'icone de l'en-tete, quand on verifie son origine, alors elle est servie depuis les
    ressources statiques de `adapter-web`, empaquetee dans le fat jar, sans reference reseau
    (perimetre deja couvert par `RessourcesTiercesTest`, `ADR-0014`).
  - Etant donne le dessin d'icone deja valide pour `/favicon.ico` (stories S2-3 et S3-7), quand il
    est repris tel quel pour l'en-tete, alors aucun nouveau verdict pedagogique n'est necessaire sur
    ce dessin. Si `ines-design` propose a la place un dessin distinct de celui du favicon, il retombe
    sous la meme porte que tout pictogramme neuf destine a l'enfant (`DEC-0002`, categorie B) et
    attend le verdict de `nadia-prof` avant d'etre considere acquis.
  - Etant donne `recreo.js` apres cette story, quand on le relit, alors il n'a recu aucune ligne
    nouvelle : une entree unique ne demande ni repli ni bascule (deja verifie mecaniquement par
    `FrontieresDuJavaScriptTest`, `ADR-0008` ; cette puce dit l'intention plutot qu'un nouveau test).
- **Critere non tranchable aujourd'hui, signale plutot que suppose acquis** (`ADR-0013`) : le lien
  "Accueil" de l'en-tete doit ne jamais etre rendu plus saillant qu'un bouton de reponse (role
  action) affiche sur le meme ecran de jeu - c'est la meme propriete que protege
  `LActionNeDeclarePasMoinsQueLaSortieTest` (`ADR-0017`) entre un bouton de sortie et un bouton
  d'action **d'un meme fichier de gabarit**. Cette story place le lien de menu dans `accueil.html`,
  hors des fichiers `fragments/manche.html` et `fragments/retour.html` qui portent les boutons
  d'action : `ADR-0017` nomme lui-meme cette frontiere comme non couverte ("aucun couple
  action/sortie ne traverse aujourd'hui cette frontiere, mais rien ne l'empeche demain"). Ce
  "demain" est cette story. Je ne tranche pas comment fermer ce trou (decision technique) ; je le
  signale a `sofia-sm`, qui route vers `theo-archi`, comme prealable a instruire avant que ce critere
  puisse etre verifie par la CI plutot que par `nadia-prof` seule en jouant l'increment.
- **Ce qui n'est pas testable par un automate et releve de la seule porte pedagogique de
  `nadia-prof`** : que l'en-tete et son unique entree soient effectivement compris par un enfant de
  CP ou de CM1 comme "revenir a la liste des jeux" sans explication d'adulte ; que la presence
  permanente de l'en-tete n'ajoute pas de charge visuelle genante sur l'ecran de jeu, ou que
  l'utilisation d'un dessin distinct du favicon (si `ines-design` en propose un) soit adaptee a
  l'enfant (categorie B de `DEC-0002`).
- **Lien decision** : `DEC-0012-rhabillage-gabarit-menu-minimal-et-ouverture-s06.md`.
- **Agent pressenti** : `ines-design` (proposition de la forme), `theo-archi` (avis prealable sur le
  trou nomme ci-dessus), mise en oeuvre par `marc-dev` / `lea-dev` / `sofia-sm`.

### S6-2 - Le contenu de chaque ecran est rassemble au centre de la page, pas colle en haut a gauche

- **Besoin** : en tant qu'enfant qui joue sur un grand ecran, je veux que le jeu et ses boutons
  soient rassembles au milieu de la page plutot que colles dans le coin en haut a gauche, afin de
  regarder au centre de l'ecran comme pour une affiche, plutot que de devoir chercher le jeu dans un
  coin.
- **Competence programme visee** : transverse - mise en page, aucun contenu pedagogique (meme
  categorie que S2-1, S2-2).
- **Criteres d'acceptation testables** :
  - Etant donne `recreo.css` et les gabarits apres cette story, quand on releve la regle qui habille
    le conteneur principal du contenu (celui qui englobe le titre, la liste des jeux, ou le fragment
    de jeu), alors elle declare une largeur maximale et un centrage horizontal explicite (par
    exemple la classe Bootstrap `.container` deja vendorisee par `ADR-0014`, ou une regle maison
    equivalente avec `margin-inline: auto`) - jamais une largeur libre calee par defaut a gauche de
    la fenetre.
  - Etant donne cette meme regle, quand on la relit a la largeur cible de 375 px (`DEC-0007` point
    5, S3-8), alors elle ne fixe aucune largeur en pixels au-dela de cette cible : couverture deja
    assuree par `ViewportEtLargeurTelephoneTest`, qui balaie tout `recreo.css` et tous les gabarits
    sans inscription supplementaire a faire.
  - Etant donne l'en-tete introduit par S6-1, quand on verifie sa mise en page, alors il suit le
    meme centrage que le reste du contenu (icone et menu ne sont pas colles a un bord alors que le
    contenu principal est centre).
  - Etant donne les garde-fous deja acquis sur les elements de jeu (`TailleDesTextesEtBoutonsTest` -
    cible tactile >= 2,75rem et lisibilite >= 1,25rem -, l'ecart `.propositions { gap }` >= 1rem,
    `LActionNeDeclarePasMoinsQueLaSortieTest`), quand cette story est fusionnee, alors ils restent
    tous verts sans qu'aucun seuil n'ait ete abaisse pour les faire passer : le centrage ne se paie
    pas par un recul sur un plancher deja gagne par un refus pedagogique.
- **Ce qui n'est pas testable par un automate et releve de la seule porte pedagogique de
  `nadia-prof`** : que le resultat percu a l'ecran soit effectivement confortable et non anxiogene
  pour un enfant de CP ou de CM1 (meme reserve que S2-2) ; que les marges laterales obtenues soient
  ni trop serrees ni trop aerees pour un enfant qui vise un bouton avec le doigt.
- **Lien decision** : `DEC-0012-rhabillage-gabarit-menu-minimal-et-ouverture-s06.md`.
- **Agent pressenti** : `ines-design` (proposition des valeurs de mise en page), mise en oeuvre par
  `marc-dev` / `lea-dev` / `sofia-sm`.

**Contexte du reste du sprint** (`DEC-0013-perimetre-sprint-6.md`) : le sprint 6 porte deux autres
chantiers en plus du rhabillage ci-dessus. **Chantier 1** (S6-3 a S6-5) ouvre la ligne francais CM1
engagee par `DEC-0011` sur `textes-trous/cm1-conjugaison.yml`, motif du choix dans `DEC-0013`, et
solde les deux points chiffres par `nadia-prof` le 2026-08-07 pour la ligne homophones CM1.
**Chantier 2** (S6-6, S6-7) sort "Le bon son" de quarantaine par un lot indivisible, tranche par
`nadia-prof` elle-meme (avis de conception du 2026-08-06,
`squad/conformite/FICHE-bandit-manchot-le-bon-son-cp.md`). **Chantier 3** (S6-8 a S6-14) instruit
la dette de forme et d'outillage laissee ouverte par s05. Un obstacle nomme par `nadia-prof`
(`GET /parties/{id}/manche` sur une partie terminee, ecran mort sans chemin d'acces trouve) n'est
volontairement pas ecrit en story ici : il reste route a `theo-archi` via `sofia-sm` comme prealable
technique a instruire (`DEC-0013`, point 3).

### S6-3 - Choisir la bonne forme d'un verbe frequent au present pour completer une phrase (CM1)

- **Besoin** : en tant qu'eleve de CM1, je veux choisir la forme correcte d'un verbe frequent
  conjugue au present pour completer une phrase, afin de m'entrainer a conjuguer sans hesiter au
  temps que j'utilise le plus souvent en ecrivant.
- **Competence programme visee** : francais, CM1 (cycle 3), domaine "Orthographe et grammaire" -
  "Conjuguer les verbes frequents au present, futur et imparfait de l'indicatif"
  (`squad/programme/francais/competences-cm1.md`). Deuxieme ligne de matrice ouverte pour le CM1 en
  conjugaison de phrase (apres les homophones grammaticaux), sur le present uniquement - le futur et
  l'imparfait restent explicitement non couverts (`DEC-0013-perimetre-sprint-6.md`).
- **Criteres d'acceptation** :
  - Etant donne une manche generee par ce contenu, quand la question s'affiche, alors elle presente
    une phrase a trou dont la bonne reponse est un verbe frequent conjugue au present de
    l'indicatif, jamais a un autre temps.
  - Etant donne les propositions affichees pour une manche, alors la bonne reponse y figure
    exactement une fois, et chaque distracteur est une autre forme conjuguee du meme verbe au
    present (jamais un verbe different, jamais une forme d'un autre temps).
  - Etant donne l'ensemble des items du paquet, quand on les releve par personne grammaticale
    (je, tu, il/elle, nous, vous, ils/elles), alors chacune des six personnes est representee par au
    moins un item.
  - Etant donne le paquet livre, quand on compare son nombre d'items a la valeur de
    `nombreDeManches` retenue pour ce jeu dans l'entree de catalogue, alors le paquet compte
    strictement plus d'items que de manches par partie (meme propriete deja exigee sur
    `cm1-homophones`) : le paquet repris n'en compte que six, un par personne grammaticale, et ne
    suffit pas en l'etat a remplir une partie sans repeter deux fois le meme item avant son terme.
    Ecrire les formes supplementaires necessaires fait partie de cette story, `contenu-repris/`
    n'etant que de la matiere premiere (`contenu-repris/README.md`).
  - Etant donne chaque item du paquet livre, quand on le relit, alors il porte un champ indice,
    texte destine a l'enfant en francais correct et integralement accentue (`DEC-0002`, amendement
    du 2026-08-04) : le paquet repris n'en porte aucun, et `ADR-0016` interdit qu'un paquet de
    production entre sans indice sur chacun de ses items (plafond de dette a 0 depuis le 2026-08-07).
  - Etant donne l'application demarree avec ce paquet, quand le test d'integration de chargement du
    contenu s'execute (`CatalogueChargeableIT`, `ADR-0007`), alors une partie demarre sans erreur sur
    ce paquet, avec la mecanique bandit manchot existante et sans qu'aucune ligne de
    `domain/mechanic` ni `domain/mechanics` n'ait ete modifiee pour l'accueillir.
  - Etant donne 20 manches jouees a la suite, quand on releve les phrases proposees, alors elles ne
    sont pas toutes identiques.
  - Ce qui n'est pas testable par un automate et releve de la seule porte pedagogique de
    `nadia-prof` : que les phrases et les indices soient d'un niveau de vocabulaire adapte a un
    eleve de CM1, que les verbes retenus soient effectivement "frequents" au sens du referentiel, la
    conformite du texte au francais correct et integralement accentue (`DEC-0002`, amendement du
    2026-08-04), et le jugement sur le fait qu'une competence a trois temps nommes, couverte ici sur
    un seul, justifie de rester `en cours` sans confusion possible avec une couverture complete.
- **Note** : premiere ecriture de cette story silencieuse sur deux points mesures par `theo-archi`
  sur le paquet repris (`squad/sprints/s06-ouvrir-la-conjugaison-et-solder-la-dette/
  avis-faisabilite.md`, section S6-3) - six items pour dix manches, aucun indice. Les deux criteres
  ci-dessus les rendent explicites plutot que de les laisser decouvrir a l'audit.
- **Note de frontiere de mandat** : si `theo-archi` etablit au planning que cette forme de contenu
  (plusieurs distracteurs par item, comme dans la matiere premiere) ne rentre pas dans `Question`
  sans changement du domaine, cette story est reformulee avant developpement plutot que forcee dans
  une forme qui ne lui convient pas.
- **Lien decision** : `DEC-0008-la-couverture-reprend-francais-cp-et-cm1.md` (inventaire de la
  matiere premiere) ; `DEC-0011-perimetre-s05-reste-correctif.md` (engagement d'ouverture au sprint
  6) ; `DEC-0013-perimetre-sprint-6.md` (choix du paquet, part de competence assumee non couverte).
- **Agent pressenti** : a affecter au planning.

### S6-4 - Deux phrases de plus qui obligent a regarder au-dela du mot voisin du trou (condition chiffree de nadia-prof, homophones CM1)

- **Besoin** : en tant qu'eleve de CM1, je veux que davantage de phrases m'obligent a chercher
  l'indice ailleurs que juste a cote du trou, afin de vraiment reflechir plutot que de deviner par la
  seule position du mot dans la phrase.
- **Competence programme visee** : francais, CM1 (cycle 3), meme ligne que S5-6 et S6-3
  ("Distinguer et utiliser correctement les principaux homophones grammaticaux",
  `squad/programme/francais/competences-cm1.md`). Condition ecrite par `nadia-prof` pour que cette
  ligne passe a `couvert-valide`, chiffree une seconde fois le 2026-08-07.
- **Criteres d'acceptation** :
  - Etant donne le paquet `cm1-homophones` apres cette story, quand on compte les items dont le mot
    decisif n'est pas voisin immediat du trou, alors ce compte atteint au moins 6 items sur les 12
    items ajoutes par S5-6 (contre 5 mesures a l'audit du 2026-08-07) : "deux items neufs de plus
    dont le mot decisif n'est pas voisin du trou, pris dans `la/l'a`, `ces/ses` ou `peu/peut`"
    (`squad/conformite/FICHE-bandit-manchot-homophones-cm1.md`, verdict du 2026-08-07, section 3,
    point 1 - cite au mot pres, non redurci ni affaibli).
  - Etant donne les vingt-deux items existants a l'ouverture de cette story (dix d'origine, douze de
    S5-6), quand on les relit apres cette story, alors ils ne sont pas modifies : cette story ajoute
    deux items, elle ne retouche aucun des vingt-deux existants.
  - Etant donne les deux items ajoutes, quand on verifie leur couple d'homophones, alors chacun
    respecte les regles deja ecrites par `nadia-prof` pour sa famille dans l'arbitrage du 2026-08-06
    (par exemple, pour `ces/ses` : une designation explicite pour `ces`, un possesseur nomme pour
    `ses` ; pour `peu/peut` : aucun sujet `je` ni `tu` ; pour `la/l'a` : un test de sens contre un
    test de temps) - regles deja fixees par S5-6, non redefinies ici.
  - Etant donne le contrat deja verifie par le test qui compte les couples du paquet (cite comme
    `PaquetsFrancaisS04Test` dans l'arbitrage de `nadia-prof` du 2026-08-06), quand la suite tourne
    apres cette story, alors elle reste verte sans qu'aucune regle n'ait ete affaiblie pour la faire
    passer.
  - Ce qui n'est pas testable par un automate et releve de la seule porte pedagogique de
    `nadia-prof` : que le mot decisif de chaque nouvel item soit reellement hors de portee d'une
    lecture superficielle, et l'exactitude grammaticale de chaque phrase nouvelle (aucune des deux
    lectures ne doit passer, meme exigence que le point 4 de sa condition).
- **Lien decision** : `squad/conformite/FICHE-bandit-manchot-homophones-cm1.md`, verdict du
  2026-08-07, section 3, point 1 ; poursuite de S5-6.
- **Agent pressenti** : `marc-dev` (auteur des items de S5-6).

### S6-5 - Un indice qui reste du francais quand il montre la phrase substituee (condition chiffree de nadia-prof, homophones CM1)

- **Besoin** : en tant qu'eleve de CM1 qui vient de se tromper trois fois de suite, je veux que
  l'aide qu'on me montre soit elle-meme ecrite dans un francais correct, afin de ne pas apprendre une
  phrase fausse au moment precis ou je cherche de l'aide.
- **Competence programme visee** : francais, CM1 (cycle 3), meme ligne que S6-4. Second et dernier
  point chiffre par `nadia-prof` pour que cette ligne passe a `couvert-valide`.
- **Criteres d'acceptation** :
  - Etant donne l'indice de l'item "J'ai rangé ___ affaires avant de partir à l'école." apres cette
    story, quand on le relit, alors la phrase de substitution qu'il donne en exemple est elle-meme
    du francais correct - contre "J'ai rangé les miennes affaires" avant cette story, phrase que
    `nadia-prof` a nommee comme n'existant pas en francais
    (`squad/conformite/FICHE-bandit-manchot-homophones-cm1.md`, verdict du 2026-08-07, section 3,
    point 2).
  - Etant donne ce meme indice, quand on le compare au modele deja retenu par `nadia-prof`, alors il
    suit la meme forme que l'indice de l'item dont la bonne reponse est "son" : "Remplace par 'tes' :
    'J'ai rangé tes affaires' fonctionne, alors c'est 'mes'." - modele cite au mot pres.
  - Etant donne les vingt et un autres indices du paquet, quand on les relit apres cette story, alors
    ils restent inchanges : cette story corrige une ligne, elle n'en retouche aucune autre.
  - Ce qui n'est pas testable par un automate et releve de la seule porte pedagogique de
    `nadia-prof` : la confirmation, en jouant, que l'indice corrige tient le meme role pedagogique
    que le modele auquel il s'aligne.
- **Lien decision** : `squad/conformite/FICHE-bandit-manchot-homophones-cm1.md`, verdict du
  2026-08-07, section 3, point 2.
- **Agent pressenti** : `marc-dev`.

### S6-6 - "Le bon son" ne travaille plus que les trois nasales an/on/in, en quinze items (recomposition, supersede S5-2)

- **Besoin** : en tant qu'eleve de CP, je veux dire un mot a voix haute et retrouver lequel des trois
  sons `an`, `on` ou `in` il contient, afin de m'entrainer a associer ce que j'entends a ce que je
  lis, meme sans fichier audio dans le jeu.
- **Competence programme visee** : francais, CP (cycle 2), domaine "Lecture et comprehension de
  l'ecrit" - "Associer les graphemes aux phonemes (...)" (meme ligne que S4-2 et l'ex-S5-2,
  `squad/programme/francais/competences-cp.md`).
- **Contexte** : cette story remplace S5-2 dans sa forme ecrite au sprint 5, sur la base de l'avis de
  conception de `nadia-prof` du 2026-08-06
  (`squad/conformite/FICHE-bandit-manchot-le-bon-son-cp.md`, section "Avis de conception") : le
  premier critere de S5-2 ("majorite des items non resolubles par les lettres") est retire, remplace
  par les criteres chiffres ci-dessous, qui sont les siens, cites et non reinterpretes.
- **Criteres d'acceptation** :
  - Etant donne le paquet recompose, quand on compte ses items, alors il en compte exactement 15,
    cinq pour chacun des trois sons `an`, `on`, `in` - les sons `ou`, `oi` et `ch` sortent du paquet
    ("Ce paquet ne travaille plus que les trois voyelles nasales : an, on, in.", avis de conception
    du 2026-08-06, section 3).
  - Etant donne les cinq items de chaque son, quand on releve la graphie qui porte le son dans le mot
    affiche, alors trois items portent la graphie reguliere du son et deux portent une graphie
    alternative courante au CP (par exemple `en`/`em`/`am` pour `an`, `om` pour `on`,
    `im`/`ain`/`ein`/`un` pour `in`) - "15 items, cinq par son, dont trois a graphie reguliere et deux
    a graphie alternative", cite au mot pres (meme avis, section 3).
  - Etant donne chaque item, quand on verifie s'il declare la graphie qui porte le son (le mot, le
    son, la graphie), alors ce champ existe et permet de determiner automatiquement si l'item est
    regulier ou a variation, sans avoir a compter les lettres du mot - besoin que `nadia-prof` signale
    explicitement a `theo-archi` (avis de conception, section 2 : "le besoin est que chaque item
    declare la graphie qui porte le son").
  - Etant donne les trois propositions affichees pour chaque manche, alors elles sont toujours les
    trois memes, `an`, `on`, `in`, sans crochet ni autre notation d'adulte (motif 2 du refus initial,
    deja acquis, a preserver).
  - Etant donne la consigne servie avec ce paquet, quand on la relit, alors elle est exactement
    "Dis le mot. Quel son entends-tu ?" (avis de conception, section 4), et non l'ancienne
    formulation qui interdisait le verbe "entendre".
  - Etant donne un item quelconque du paquet, quand on verifie s'il fait entendre un second son du
    jeu que celui attendu, alors ce n'est jamais le cas (motif 4 du refus initial, deja acquis sur
    les 21 items existants, a reverifier item par item sur les mots neufs).
  - Ce qui n'est pas testable par un automate et releve de la seule porte pedagogique de
    `nadia-prof` : le choix exact des quinze mots (des exemples de travail sont donnes par
    `nadia-prof` dans son avis, pas une liste arretee), la justesse phonetique de chaque item, et
    trois defauts de redaction qu'elle a nommes sur les indices actuels (position du son dans le mot
    de renvoi incoherente, indice de `DENT` trop faible, tournure "Le son s'entend aussi dans..."
    trop lourde pour du CP) - a traiter avec cette recomposition plutot que redecouverts a l'audit.
- **Condition de faisabilite, a lire avant developpement** : `theo-archi` a mesure que le champ
  demande par le troisieme critere ci-dessus n'est lu par aucune fabrique aujourd'hui - le chargeur
  ignore silencieusement toute cle inconnue - et qu'un ADR est requis, conjoint avec S6-7
  (`squad/sprints/s06-ouvrir-la-conjugaison-et-solder-la-dette/avis-faisabilite.md`, section S6-6).
  Le lot doit livrer la lecture de ce champ et son garde-fou dans le meme geste que le contenu YAML,
  pas seulement le YAML.
- **Relation a S6-7** : cette story et S6-7 (retour explicatif) forment **un seul lot indivisible**,
  tranche par `nadia-prof` elle-meme : "S5-2 recomposee et S5-9 sont un seul lot, et se livrent
  ensemble ou pas du tout." (avis de conception, section 5). Aucune des deux ne remet
  `bandit-manchot-le-bon-son-cp` au catalogue a elle seule : `DEC-0009` reste en vigueur jusqu'a un
  nouveau Done pedagogique constate dans une nouvelle fiche de conformite.
- **Lien decision** : avis de conception de `nadia-prof` du 2026-08-06
  (`squad/conformite/FICHE-bandit-manchot-le-bon-son-cp.md`) ; `DEC-0009-retrait-le-bon-son-du-catalogue.md`
  (reste en vigueur) ; remplace S5-2.
- **Agent pressenti** : `theo-archi` (modelisation du champ de graphie, besoin qu'il a lui-meme a
  instruire selon `nadia-prof`), mise en oeuvre par `lea-dev` / `marc-dev`.

### S6-7 - Un retour qui nomme les lettres et le son qu'elles font, sur chaque item a variation (finalise S5-9, prealable tranche par nadia-prof)

- **Besoin** : en tant qu'eleve de CP qui vient de lire un mot ou le son s'ecrit autrement que sa
  graphie habituelle (comme `DENT` pour le son `an`), je veux qu'on me montre les lettres qui font ce
  son, que j'aie repondu juste ou faux, afin de comprendre la variation plutot que de la vivre comme
  une erreur.
- **Competence programme visee** : francais, CP (cycle 2), meme ligne que S6-6
  (`squad/programme/francais/competences-cp.md`).
- **Contexte** : cette story reprend S5-9, dont le prealable ("sur quels items, a quel moment, quel
  texte") est desormais tranche par `nadia-prof` elle-meme dans son avis de conception du 2026-08-06
  (section 5), a sa demande explicite de le faire pour qu'elle ne lui revienne pas. Les criteres
  ci-dessous citent ce prealable, ils ne l'inventent pas.
- **Criteres d'acceptation** :
  - **Sur quels items** : "uniquement les items a variation, ceux dont la graphie differe de la
    notation du son. Sur un item regulier il n'y a rien a expliquer" (avis de conception, section 5).
    Etant donne un des six items a graphie alternative de S6-6, quand l'enfant repond a la manche
    correspondante, alors l'ecran de retour affiche un texte qui nomme les lettres du mot et le son
    qu'elles font, a la place du seul retour juste/faux habituel. Etant donne un des neuf items a
    graphie reguliere, quand l'enfant y repond, alors aucun texte d'explication de variation ne
    s'affiche.
  - **Quand** : "systematiquement, que l'enfant ait repondu juste ou faux [...] Pas 'une seule fois'
    non plus" (meme section). Etant donne un item a variation, que la reponse de l'enfant soit juste
    ou fausse, et quel que soit le nombre de fois ou cet item a deja ete rencontre dans la partie,
    alors le texte d'explication s'affiche a chaque occurrence.
  - **Ce que le texte doit dire** : "nommer les lettres du mot affiche et le son qu'elles font. Forme
    attendue, a valider texte par texte : 'Dans DENT, ce sont les lettres e et n qui font le son an.'
    Une phrase, le mot que l'enfant vient de lire, pas de terme de metalangage." (meme section).
    Etant donne le champ qui porte ce texte dans le contenu, quand on relit le paquet, alors chaque
    item a variation porte son propre texte, distinct par item, dans cette forme.
  - Ce qui n'est pas testable par un automate et releve de la seule porte pedagogique de
    `nadia-prof` : le texte exact de chaque explication, item par item.
- **Condition de faisabilite, a lire avant developpement** : `theo-archi` declare cette story non
  faisable avant une decision d'architecture, qu'il tranche a l'ouverture du lot avec la personne qui
  ecrit les textes, pas en amont dans un fichier
  (`squad/sprints/s06-ouvrir-la-conjugaison-et-solder-la-dette/avis-faisabilite.md`, section S6-7) :
  le texte doit traverser `Question`, la mecanique et l'ecran de retour, et `Verdict` ne porte
  aujourd'hui rien de tel. Il chiffre aussi que son garde-fou n'est pas optionnel : la dette de
  balayage de `LesGardeFousSontMisALEpreuveTest` est pleine, un jeu fautif est donc du dans le lot.
- **Relation a S6-6** : meme lot indivisible, voir S6-6.
- **Lien decision** : avis de conception de `nadia-prof` du 2026-08-06
  (`squad/conformite/FICHE-bandit-manchot-le-bon-son-cp.md`, section 5) ; detachee a l'origine du
  deuxieme critere de S5-2 sur avis de `theo-archi` (s05) ; `DEC-0011-perimetre-s05-reste-correctif.md`
  (sortie hors perimetre s05, close par son entree en s06).
- **Agent pressenti** : `theo-archi` (modelisation du champ et de son affichage), mise en oeuvre par
  `lea-dev` / `marc-dev`.

### S6-8 - Une marge laterale qui ne disparait plus sous 1200 px (reserve 1 chiffree du rhabillage)

- **Besoin** : en tant qu'enfant de CP qui joue sur un ecran etroit (tablette ou telephone), je veux
  que la consigne, l'enonce et les boutons de reponse gardent une marge par rapport aux bords de
  l'ecran, afin de ne jamais lire ma premiere lettre collee au bord.
- **Competence programme visee** : transverse - accessibilite visuelle, meme categorie que S2-1,
  S3-8.
- **Criteres d'acceptation** :
  - Etant donne `recreo.css` apres cette story, quand on mesure le rembourrage lateral du conteneur
    de la zone de jeu a une largeur de fenetre de 360 px, alors il n'est plus nul - contre
    `padding-left: 0` et `padding-right: 0` mesures ce sprint sur `main.container`
    (`squad/conformite/FICHE-bandit-manchot-additions-cp.md`, verdict du 2026-08-07, section A.2,
    point 1).
  - Etant donne cette meme mesure a 360 px, quand on la compare a celle de l'en-tete au meme instant,
    alors les deux rembourrages laterals ne sont plus disproportionnes : l'en-tete mesurait 12 px de
    marge quand le contenu en avait 0. Le contenu retrouve au moins ce meme plancher de 12 px sur ses
    deux cotes.
  - Etant donne les garde-fous deja acquis (`ViewportEtLargeurTelephoneTest`,
    `TailleDesTextesEtBoutonsTest`), quand cette story est fusionnee, alors ils restent verts sans
    qu'aucun seuil n'ait ete abaisse pour les faire passer.
  - Ce qui n'est pas testable par un automate et releve de la seule porte pedagogique de
    `nadia-prof` : elle remesurera a 360 px, comme elle l'annonce elle-meme dans son verdict.
- **Lien decision** : `squad/conformite/FICHE-bandit-manchot-additions-cp.md`, verdict du 2026-08-07,
  section A.2, point 1 ; `DEC-0012-rhabillage-gabarit-menu-minimal-et-ouverture-s06.md`, amendement du
  2026-08-07.
- **Agent pressenti** : `ines-design` (proposition du correctif), mise en oeuvre par `marc-dev` /
  `lea-dev` / `theo-archi`.

### S6-9 - Une seule sortie par ecran de jeu, avec un mot que le CP peut lire (reserves 2 et 3 du rhabillage)

- **Besoin** : en tant qu'enfant de CP qui joue une manche, je veux ne voir qu'un seul bouton pour
  repartir vers la liste des jeux, ecrit avec un mot que je sais dechiffrer, afin de ne pas devoir
  choisir entre deux portes qui font la meme chose.
- **Competence programme visee** : transverse - navigation et lisibilite pour le niveau CP, meme
  categorie que S1-8, S6-1.
- **Decision de contenu, precisant sans la rouvrir `DEC-0012` point 2** : le libelle retenu pour
  l'entree de menu de l'en-tete devient **"Les jeux"**, deja dechiffrable au CP et deja en place dans
  les fragments. Je ne retiens pas "Accueil" : `nadia-prof` a signale que ce choix rendrait sa
  reserve 3 bloquante ("Si la reserve 2 est traitee en gardant 'Accueil' et en retirant 'Les jeux',
  alors ce point devient bloquant").
- **Arbitrage prealable de `nadia-prof` (2026-08-07), qui remplace la forme initiale de cette
  story** : le principe (une seule sortie par ecran de jeu, mot "Les jeux") est confirme, mais pas la
  forme que cette story lui donnait. Elle a joue l'increment avant de trancher
  (`squad/conformite/FICHE-bandit-manchot-additions-cp.md`, section "Arbitrage prealable au
  developpement - S6-9") : retirer le bouton du fragment sur l'ecran de fin de partie y aurait laisse
  "C'est fini !" sans aucune porte visible dans la zone de contenu que l'enfant regarde, la seule
  sortie restante etant un contour de 102 x 53 px dans un bandeau qu'elle a mesure comme n'invitant
  pas au geste - ce qui aurait defait l'etat que `nadia-prof` avait valide en jouant pour S3-6. C'est
  donc **le bouton de la zone de contenu qui survit sur les ecrans de jeu, pas le lien de l'en-tete**.
  Elle precise aussi le perimetre de sa reserve 2 du meme jour : cette reserve laissait ouvert lequel
  des deux boutons partait, en la croyant neutre pedagogiquement ; elle ne l'etait pas, et elle ferme
  elle-meme cette branche plutot que de me laisser un choix qui ne l'etait pas vraiment. La barre "un,
  pas deux" ne change pas.
- **Criteres d'acceptation**, ecran par ecran, repris de son arbitrage :
  - Etant donne l'ecran d'accueil rendu apres cette story, quand on relit l'entree de menu de
    l'en-tete, alors elle est visible et porte le texte "Les jeux" (contre "Accueil" avant cette
    story) - seul changement de texte que cette story introduit.
  - Etant donne chacun des cinq etats de l'ecran de jeu (manche, retour juste en cours de partie,
    retour faux en cours de partie, retour en fin de partie, erreur), quand on relit l'en-tete rendu,
    alors son entree de menu n'y est pas visible : sur ces cinq etats, la seule sortie vers la liste
    des jeux visible par l'enfant est celle deja portee par le fragment.
  - Etant donne l'ecran de manche et l'ecran de retour en cours de partie (`enSuite` vrai), quand on
    relit le fragment rendu apres cette story, alors le bouton `.btn.retour-accueil` portant "Les
    jeux" n'est ni retire, ni renomme, ni redimensionne par rapport a son etat actuel.
  - Etant donne l'ecran de retour en fin de partie (`enSuite` faux), quand on le relit apres cette
    story, alors le bouton `.btn.sortie-pleine` portant "Les jeux" en aplat plein n'est ni retire, ni
    renomme, ni redimensionne : il reste la seule chose pressable de la zone de contenu, l'etat
    valide par `nadia-prof` en S3-6.
  - Etant donne l'ecran d'erreur, quand on le relit apres cette story, alors aucune sortie n'y est
    ajoutee : "Recommencer" reste seul et reste un bouton plein.
  - Etant donne le garde-fou `LActionNeDeclarePasMoinsQueLaSortieTest` (`ADR-0017`), quand cette
    story est fusionnee, alors il reste vert.
  - Ce qui n'est pas testable par un automate et releve de la seule porte pedagogique de
    `nadia-prof`, qui le verifiera en jouant : que l'entree de menu soit reellement invisible sur les
    cinq etats de l'ecran de jeu (aucun automate du depot ne mesure une visibilite aujourd'hui) ; que
    "Les jeux" reste dechiffrable et suffisant comme unique sortie du fragment.
- **Note technique, non tranchee ici** : comment obtenir l'invisibilite de l'entree de menu sur les
  cinq etats sans casser l'en-tete permanent de `DEC-0012` est une question technique que
  `nadia-prof` decrit et laisse expressement a `theo-archi`, qui l'instruit en parallele de ce
  planning.
- **Lien decision** : `squad/conformite/FICHE-bandit-manchot-additions-cp.md`, arbitrage prealable du
  2026-08-07 (S6-9) et verdict du 2026-08-07, section A.2, points 2 et 3 ; precise
  `DEC-0012-rhabillage-gabarit-menu-minimal-et-ouverture-s06.md` point 2 sans le rouvrir.
- **Agent pressenti** : `theo-archi` (mecanisme d'invisibilite de l'entree de menu sur les cinq
  etats), mise en oeuvre par `marc-dev` / `lea-dev`.

### S6-10 - Le bouton d'action reste seul de sa couleur sur l'ecran de retour (reserve 4 du rhabillage)

- **Besoin** : en tant qu'enfant de CP qui vient de repondre, je veux que le bouton sur lequel
  j'appuie pour continuer reste la seule chose bleue et pleine de l'ecran, afin de le reperer du
  premier coup d'oeil meme quand un bandeau de la meme couleur occupe le haut de la page.
- **Competence programme visee** : transverse - hierarchie visuelle pour le niveau CP, meme categorie
  que S3-6.
- **Criteres d'acceptation** :
  - Etant donne l'ecran de retour (juste ou faux) rendu apres cette story, quand on releve la couleur
    de fond du bandeau d'en-tete et celle du bouton d'action "Encore !", alors elles ne sont plus
    identiques - contre `#2e6f9e` mesure des deux cotes ce sprint, le bandeau occupant seize fois la
    surface du bouton (`squad/conformite/FICHE-bandit-manchot-additions-cp.md`, verdict du
    2026-08-07, section A.2, point 4).
  - Cette story retient l'une des deux solutions nommees par `nadia-prof`, au choix d'`ines-design` :
    soit le bandeau porte une nuance distincte de la couleur d'action, soit le bouton d'action
    grandit sur l'ecran de retour precisement (le plus pauvre en signal selon elle).
  - Etant donne les mesures deja acquises au point A.1 de la meme fiche (117 >= 109 >= 102 en largeur
    entre l'action et les deux sorties), quand cette story est fusionnee, alors elles restent
    verifiees telles quelles.
  - Ce qui n'est pas testable par un automate et releve de la seule porte pedagogique de
    `nadia-prof` : que le bleu redevienne percu comme le signal exclusif "appuie ici" par un enfant
    de CP.
- **Lien decision** : `squad/conformite/FICHE-bandit-manchot-additions-cp.md`, verdict du 2026-08-07,
  section A.2, point 4.
- **Agent pressenti** : `ines-design` (proposition de la solution retenue), mise en oeuvre par
  `marc-dev` / `lea-dev`.

### S6-11 - Une zone de jeu qui garde un identifiant unique apres chaque echange htmx

- **Besoin** : en tant qu'enfant qui utilise une aide technique (lecteur d'ecran) pour naviguer dans
  la page, je veux que chaque zone porte un identifiant unique, afin que mon outil puisse
  reellement me dire dans quelle zone je me trouve plutot que d'en trouver deux qui portent le meme
  nom.
- **Competence programme visee** : transverse - accessibilite, meme categorie que S1-6.
- **Criteres d'acceptation** :
  - Etant donne n'importe quel ecran de jeu rendu apres un echange htmx (manche, retour, fin de
    partie, erreur), quand on compte les elements portant l'identifiant `jeu` dans le HTML final,
    alors il y en a exactement un, jamais deux - contre le `main` du gabarit et le `div` du fragment
    recu, tous deux portant `id="jeu"`, constate par `nadia-prof`
    (`squad/conformite/FICHE-bandit-manchot-additions-cp.md`, verdict du 2026-08-07, section E,
    point 1).
  - Etant donne ce meme HTML corrige, quand le garde-fou qui protege la frontiere `hx-target`
    (`ADR-0017`) s'execute, alors il continue de reperer la zone remplacee sans modification de sa
    logique.
- **Lien decision** : constat de `nadia-prof`, `squad/conformite/FICHE-bandit-manchot-additions-cp.md`,
  verdict du 2026-08-07, section E.
- **Agent pressenti** : `theo-archi` (diagnostic puis correction).

### S6-12 - Un contre-exemple de garde-fou qui ne peut jamais s'executer dans l'application reellement servie

- **Besoin** : en tant qu'enfant qui joue a recreo, je ne veux jamais qu'un code ecrit pour demontrer
  un mauvais comportement puisse un jour s'executer reellement a la place du bon, afin que l'ecran
  d'erreur que je vois reste toujours celui que le jeu me promet.
- **Competence programme visee** : transverse - fiabilite du chemin d'erreur, meme perimetre que
  S1-3.
- **Criteres d'acceptation** :
  - Etant donne tout `@SpringBootTest` complet du module `bootstrap`, quand le contexte applicatif
    demarre, alors `GestionnaireErreurBruteContreExemple` (contre-exemple d'`ADR-0011`) n'est jamais
    enregistre comme un bean Spring reel - contre le comportement constate ce sprint, ou
    `scanBasePackages = "com.recreo"` le charge malgre lui et il intercepte l'exception avant
    `GestionnaireDesErreurs`, rendant un 500 a corps brut (trouve par `lea-dev`, commit `94a1a8f`,
    `squad/sprints/s05-lever-le-refus-du-bon-son/review.md`, tableau des obstacles).
  - Etant donne une erreur imprevue provoquee dans un tel test, quand elle traverse le serveur, alors
    c'est `GestionnaireDesErreurs` qui la traite, jamais le contre-exemple.
  - Etant donne `PropositionsAccentueesJouablesIT` (le test qui a revele cette fuite), quand la suite
    tourne apres cette story, alors il reste vert pour la raison qui le rend deja mordant (decodage
    tolerant du corps de requete), sans dependre de cette fuite pour rester vert par accident.
- **Note** : le choix technique (deplacement hors du classpath de production scanne, ou resserrement
  du scan de composants) appartient a `theo-archi`.
- **Lien decision** : constat de `lea-dev`, commit `94a1a8f` ;
  `squad/sprints/s05-lever-le-refus-du-bon-son/review.md`, tableau des obstacles.
- **Agent pressenti** : `theo-archi`.

### S6-13 - Un script qui verifie la lignee d'un worktree avant tout travail

- **Besoin** (story d'enabler transverse, portee par l'equipe plutot que par un enfant) : pour que
  chaque agent qui ouvre un worktree neuf verifie automatiquement qu'il descend bien de `round-two`
  avant d'ecrire une ligne, sans dependre de sa memoire d'une consigne recopiee sprint apres sprint -
  dixieme occurrence d'un provisionnement fautif constatee en s05
  (`squad/sprints/s05-lever-le-refus-du-bon-son/retro.md`, point 4).
- **Competence programme visee** : transverse.
- **Criteres d'acceptation** :
  - Etant donne un worktree provisionne sur une branche qui n'est pas un descendant de `round-two`,
    quand `outils/verifier-lignee.sh` est execute, alors il se termine par un code de sortie non nul
    et affiche un message explicite nommant la branche fautive, sans jamais imprimer de chemin
    absolu de poste de travail.
  - Etant donne un worktree correctement provisionne sur `round-two` ou un descendant, quand le
    script est execute, alors il se termine par un code de sortie 0.
  - Etant donne ce script, quand il est lui-meme teste (meme doctrine que les garde-fous d'`ADR-0009`),
    alors sa suite de test couvre les deux cas ci-dessus.
- **Note de routage** : la mise a jour de `squad/README.md` section 9 pour renvoyer vers ce script
  plutot que vers une commande recopiee en prose appartient a `sofia-sm`, pas a moi.
- **Lien decision** : `squad/sprints/s05-lever-le-refus-du-bon-son/retro.md`, "Amendements au
  process", ligne 1.
- **Agent pressenti** : `sofia-sm`.

### S6-14 - Un script qui verifie la presence d'un JDK 21 sans se fier au seul nom d'un fichier

- **Besoin** (story d'enabler transverse, portee par l'equipe plutot que par un enfant) : pour que la
  presence d'un JDK 21 ne soit plus jugee a tort absente a cause d'un fichier de toolchains mal
  nomme sur le poste de l'utilisatrice - quatre agents concernes rien qu'en s05, meme cause qu'en
  s03 (`squad/sprints/s05-lever-le-refus-du-bon-son/retro.md`, point 5).
- **Competence programme visee** : transverse.
- **Criteres d'acceptation** :
  - Etant donne `outils/verifier-jdk.sh` execute sur un poste donne, quand il tourne, alors il ecrit
    sur la sortie d'erreur un diagnostic libre destine a l'agent qui debloque son poste (sortie de
    `java -version`, valeur de `JAVA_HOME`, nom et emplacement du fichier de toolchains trouve sous
    le depot Maven local) : ce canal peut nommer un chemin de poste, il n'est jamais destine a etre
    commite.
  - Etant donne ce meme script, quand il tourne, alors il ecrit sur la sortie standard, separement
    du diagnostic, un constat citable qui ne porte que des faits, jamais un emplacement : la version
    rendue par `java -version`, la version du JDK effectivement retenu pour la compilation, si
    `JAVA_HOME` est renseignee ou non (jamais sa valeur), et si un fichier de toolchains est lu ou
    non par Maven (jamais son nom ni son emplacement).
  - Etant donne ce constat citable, quand on l'examine quelle que soit la situation du poste, alors
    il ne contient jamais de `/`, jamais de `\`, jamais de lettre de lecteur suivie de `:` - c'est le
    test qui compte, et il est negatif.
  - Etant donne la sortie standard de ce script (le constat citable, jamais la sortie d'erreur),
    quand un agent rapporte le resultat de `mvn verify` dans `rapport-qualite.md` ou
    `confirmation-archunit.md`, alors c'est elle qui est collee telle quelle a la place d'une
    description en prose de la verification.
- **Note** : premiere ecriture de cette story invalidee par `theo-archi`, ses deux criteres se
  contredisaient avec `ADR-0010` (`JAVA_HOME` renseignee est un chemin de poste ; le second critere
  demandait de la coller dans un fichier commite). Version ci-dessus corrigee sur la base de son avis
  de faisabilite (`squad/sprints/s06-ouvrir-la-conjugaison-et-solder-la-dette/avis-faisabilite.md`,
  section S6-14) : deux canaux distincts, le besoin ne change pas.
- **Lien decision** : `squad/sprints/s05-lever-le-refus-du-bon-son/retro.md`, "Amendements au
  process", ligne "Nouveau : outils/verifier-jdk.sh".
- **Agent pressenti** : `sofia-sm`.

## Sprint 7 - Une vraie machine a sous, pas un questionnaire empile

**Contexte et decision de perimetre** : l'utilisatrice a joue l'application et constate que "les
jeux existants ne reposent pas sur un bandit manchot. C'est une machine avec les reponses
proposees dans les colonnes." Verifie contre le code : `fragments/manche.html` rend un
questionnaire a choix multiple (boutons empiles verticalement), et aucun fichier du depot ne
contient de vocabulaire de machine a sous (rouleau, colonne, levier), alors que "bandit manchot"
nomme la mecanique dans 25 `ADR`, dans le catalogue, dans les trois fiches de conformite et dans
quatre lignes de `squad/programme/MATRICE-couverture.md`. C'est la dette n°1 de `CLAUDE.md`
reproduite (une abstraction citee partout, absente du code). `DEC-0015-sprint-7-la-vraie-machine-
a-sous-ferme-la-dette-1.md` tranche : le sprint 7 construit la mecanique de machine a sous decrite
par l'utilisatrice (l'enfant tire un levier, trois colonnes defilent puis s'arretent chacune sur
une proposition, il choisit la colonne juste), en remplacement du rendu actuel, sans toucher aux
contrats de decouplage deja actes (`ADR-0004`, `ADR-0008`).

**Ces quatre stories sont transverses** : aucune ne porte de competence programme, aucune ne fait
bouger une ligne de la matrice. Ce sprint reconstruit une mecanique deja jouee, il n'ouvre aucun
contenu nouveau. Les questions techniques que la reconstruction souleve (mecanisme d'animation
compatible `ADR-0008` et `ADR-0014` point 7, sort de la reponse au clavier, disposition a 360-375
px) sont routees a `theo-archi` et ne sont pas presupposees par les criteres ci-dessous. Les
acquis pedagogiques que ce chantier touche potentiellement (cible tactile, lisibilite, ecart entre
boutons, hierarchie de saillance `ADR-0017`, etat `sortie-pleine` d'`ADR-0022`) sont signales a
`nadia-prof` pour un arbitrage prealable avant developpement (`squad/README.md` section 10),
liste complete dans `DEC-0015`.

### S7-1 - Une vraie machine a sous, pas un choix de bouton empile

- **Besoin** : en tant qu'enfant qui joue au bandit manchot, je veux tirer un levier qui fait
  tourner des colonnes de reponses possibles, puis choisir la colonne qui affiche la bonne
  reponse, pour vivre le jeu comme une vraie machine a sous plutot que comme une liste de boutons
  empiles.
- **Competence programme visee** : transverse - mecanique de jeu, aucun contenu pedagogique
  (`ADR-0004` : la mecanique ne connait aucune matiere).
- **Criteres d'acceptation** :
  - Etant donne un ecran de manche affiche, quand l'enfant le regarde avant d'agir, alors les
    propositions ne sont plus presentees comme une liste verticale de boutons empiles (l'etat
    actuel de `fragments/manche.html`), mais comme des colonnes distinctes, une par proposition.
  - Etant donne l'ecran de manche, quand l'enfant tire le levier, alors une seule action suffit a
    declencher le tour (voir S7-4 : pas de geste ajoute par rapport a l'actuel bouton de
    proposition).
  - Etant donne le resultat du tour, quand l'enfant doit choisir, alors chaque colonne affiche
    exactement une proposition parmi celles fournies par le contenu, sans doublon ni proposition
    manquante par rapport a ce qui est affiche aujourd'hui par les boutons.
  - Etant donne le contrat mecanique/contenu deja en vigueur (`ADR-0004`, `ADR-0008`), quand cette
    story est livree, alors le verdict juste/faux et la designation de la colonne juste restent
    calcules et detenus par le serveur uniquement ; aucun marqueur de correction n'atteint le
    client avant le choix de l'enfant (contrainte existante, non renegociee ici).
  - Ce qui n'est pas testable par un automate et releve de la seule porte pedagogique de
    `nadia-prof` : que le choix par colonne reste aussi comprehensible pour un enfant de CP que
    l'etait le bouton, et que le jeu ne devienne pas plus difficile a comprendre du seul fait de sa
    nouvelle forme.
- **Lien decision** : `DEC-0015-sprint-7-la-vraie-machine-a-sous-ferme-la-dette-1.md`.
- **Agent pressenti** : a affecter au planning (avis de faisabilite de `theo-archi` requis avant
  developpement).

### S7-2 - Le nombre de colonnes suit le nombre de propositions du contenu, jamais fige a trois

- **Besoin** (story d'enabler transverse, portee par l'equipe plutot que par un enfant) : pour que
  la machine a sous puisse un jour jouer un contenu a deux, trois ou quatre propositions sans
  reecrire la mecanique, le nombre de colonnes affichees doit venir du contenu, jamais d'une
  constante de la mecanique.
- **Competence programme visee** : transverse.
- **Criteres d'acceptation** :
  - Etant donne un contenu qui fournit trois propositions (par exemple "additions CP"), quand la
    manche s'affiche, alors trois colonnes sont rendues, ni plus ni moins.
  - Etant donne un contenu qui fournirait un nombre different de propositions (par exemple les
    quatre propositions de "Le bon verbe", `squad/conformite/FICHE-bandit-manchot-conjugaison-
    present-cm1.md`, section 4), quand la manche s'affiche, alors le nombre de colonnes rendues
    correspond exactement au nombre de propositions fournies par ce contenu, sans modification de
    la mecanique elle-meme.
  - Etant donne la regle de decouplage mecanique/contenu deja en vigueur (`ADR-0004`, S0-3), quand
    on relit le code de la machine a sous, alors aucune constante ni aucun test ne fige le nombre
    "trois" comme un invariant de la mecanique.
- **Lien decision** : `DEC-0015-sprint-7-la-vraie-machine-a-sous-ferme-la-dette-1.md`, arbitrage 3.
- **Agent pressenti** : a affecter au planning.

### S7-3 - Lire la question avant que les colonnes ne s'arretent

- **Besoin** : en tant qu'enfant de CP qui lit encore lentement, je veux voir et pouvoir lire la
  question avant que les colonnes ne s'arretent de tourner et que je doive choisir, afin d'avoir le
  temps de comprendre ce qui est demande avant de decider.
- **Competence programme visee** : transverse - accessibilite et lisibilite pour le niveau CP
  (meme categorie que S0-5, S1-9, S2-1).
- **Criteres d'acceptation** :
  - Etant donne une manche qui commence, quand l'enonce s'affiche, alors il est visible et lisible
    avant meme que le levier ne soit tire (il n'attend pas l'arret des colonnes pour apparaitre).
  - Etant donne le defilement des colonnes, quand il est en cours, alors l'enonce reste affiche a
    l'identique, sans animation ni modification, pendant toute la duree du defilement.
  - Ce qui n'est pas testable par un automate et releve de la seule porte pedagogique de
    `nadia-prof` : que la duree et le rythme du defilement laissent effectivement a un enfant de CP
    le temps de lire l'enonce et de se preparer a choisir.
- **Lien decision** : `DEC-0015-sprint-7-la-vraie-machine-a-sous-ferme-la-dette-1.md`, arbitrage 2.
- **Agent pressenti** : a affecter au planning.

### S7-4 - Tirer le levier n'ajoute aucun geste a ce que l'enfant fait deja

- **Besoin** : en tant qu'enfant de CP, je veux que tirer le levier soit le meme geste que celui
  qui me fait deja avancer aujourd'hui (demarrer une partie, passer a la question suivante), afin
  de ne pas avoir un geste de plus a comprendre a chaque question.
- **Competence programme visee** : transverse.
- **Criteres d'acceptation** :
  - Etant donne l'enchainement actuel apres une manche (le bouton "Encore !"), quand il est
    remplace par le geste de tirer le levier, alors ce geste declenche a lui seul a la fois
    l'affichage de la question suivante et le defilement des colonnes, sans etape intermediaire qui
    n'existait pas avant.
  - Etant donne le demarrage d'une partie, quand l'enfant lance le jeu, alors le meme principe
    s'applique : tirer le levier est ce qui demarre la partie, il ne s'ajoute pas au bouton
    "Demarrer" deja existant.
  - Etant donne l'aide de S1-5/S3-2 (indice apres trois echecs consecutifs) et la "manche plus
    simple", quand elles se declenchent, alors elles s'affichent et se comportent exactement comme
    avant : le changement de forme du levier ne modifie aucune de leurs conditions de
    declenchement.
  - Ce qui n'est pas testable par un automate et releve de la seule porte pedagogique de
    `nadia-prof` : qu'un enfant de CP comprenne intuitivement le geste de tirer le levier sans
    explication d'un adulte.
- **Lien decision** : `DEC-0015-sprint-7-la-vraie-machine-a-sous-ferme-la-dette-1.md`, arbitrage 1.
- **Agent pressenti** : a affecter au planning.

## Sprint 8 - La machine retrouve son contexte, trois verites du depot corrigees

**Contexte et decision de perimetre** : l'utilisatrice a joue l'increment du sprint 7 et fait deux
constats, le meme defaut vu sous deux angles. D'abord, les boutons de choix de jeu de l'accueil
portent le decor de levier (`recreo.css`, regle S7-4, `.demarrer::before`) alors que l'accueil
n'est pas une machine, c'est une liste de jeux - le decor n'aurait jamais du deborder de `.encore`
sur `.demarrer`. Ensuite : "il manque une machine dans le contexte (...) sinon on ne comprend pas"
- les colonnes n'ont ni cabinet ni objet qui les encadre, et la metaphore n'est pas lisible sans ce
contexte. `DEC-0017-perimetre-sprint-8-la-machine-retrouve-son-contexte-et-trois-verites-corrigees.md`
tranche que le sprint 8 construit ce contexte - une machine reconnaissable, customisee avec des
images pour enfants, sans les codes du jeu d'argent, degre tranche par l'utilisatrice - sous le
veto pedagogique deja pose par `nadia-prof` le 2026-08-10, qui reste entier. Le meme sprint porte
aussi l'ouverture du futur et de l'imparfait de la conjugaison au present, sur la barre chiffree et
close que `nadia-prof` a ecrite elle-meme.

**Trois verites du depot corrigees hors backlog** (chantier 2 de `DEC-0017`, sans story dediee) :
`catalogue.yaml` remet "Le bon verbe" au catalogue (execution routee a un developpeur par
`sofia-sm`, les deux conditions de `DEC-0015` etant remplies) ;
`squad/programme/MATRICE-couverture.md` et `squad/validation/FILE-ATTENTE.md` sont corriges pour
refleter la levee du refus de "Le bon verbe" prononcee au sprint 7, qu'ils ne portaient plus a
jour.

### S8-1 - Un cabinet reconnaissable autour des rouleaux, et le levier quitte l'accueil

- **Besoin** : en tant qu'enfant qui joue au bandit manchot, je veux voir les colonnes de reponses
  a l'interieur d'un vrai cabinet qui ressemble a une machine, avec des images faites pour moi,
  afin de comprendre que je suis devant une machine et non une simple liste de boutons qui bougent.
- **Competence programme visee** : transverse - habillage de la mecanique, aucun contenu
  pedagogique (`ADR-0004` : la mecanique ne connait aucune matiere).
- **Criteres d'acceptation** :
  - Etant donne l'ecran de manche affiche, quand l'enfant le regarde, alors les colonnes de
    propositions sont entourees d'un element de decor unique (le cabinet) qui les contient toutes
    ensemble et qui est visuellement distinct du reste de l'ecran (une bordure, un fond ou une
    forme dediee, pas seulement la couleur de fond commune a toute la page).
  - Etant donne le cabinet affiche, quand on releve tout texte, `alt`, `aria-label`, `title` ou
    titre de page qu'il porte, alors aucun des mots interdits par le veto de `nadia-prof` du
    2026-08-10 (`FICHE-bandit-manchot-additions-cp.md`, section 1 : "machine a sous", "bandit",
    "manchot", "jackpot", "mise", "miser", "gain", "gagner de l'argent", "casino", "piece",
    "jeton", "credit", "bandit manchot") n'y figure, et aucune image ne represente une piece, un
    jackpot, un symbole de monnaie ou tout autre signe de jeu d'argent.
  - Etant donne les images decoratives que ce chantier ajoute, quand elles sont servies, alors
    elles sont vendorisees et servies depuis `adapter-web`, sans ressource externe ni CDN, et
    l'application reste utilisable hors ligne (`RessourcesTiercesTest`).
  - Etant donne le decor du cabinet, quand on relit `recreo.js`, alors aucune ligne n'y est
    ajoutee ; le decor est implemente en CSS par `clip-path`, comme la coche et la croix
    existantes, et aucune ligne de JavaScript ne devient responsable de `proposition` ou de
    `colonne`.
  - Etant donne les quatre planchers non negociables (cible tactile 2,75 rem, lisibilite
    1,25 rem, chiffre de proposition 2 rem, `gap` 1 rem sur les deux axes), quand le cabinet est
    ajoute, alors aucun des quatre ne recule, mesure a l'egalite exacte : pas de marge de 2 px a
    recuperer.
  - Etant donne les dimensions du decor ajoute, quand on les relit, alors elles sont exprimees en
    `rem` ou en `%`, jamais en pixels.
  - Etant donne l'ecran de fin de partie, quand il s'affiche, alors il ne porte ni levier, ni
    colonne, ni decor de cabinet, ni animation (acquis 6 de `nadia-prof`, deja tenu et a ne pas
    defaire).
  - Etant donne l'ecran d'accueil (liste des jeux), quand un bouton de choix de jeu (`.demarrer`)
    est affiche, alors il ne porte plus l'apparence de levier de machine a sous (le disque
    decoratif pose aujourd'hui par la regle S7-4 de `recreo.css`) : ce n'est pas une machine,
    c'est une liste de jeux. Le bouton "Encore !" (`.encore`), qui relance bien une manche, n'est
    pas concerne par cette correction.
  - Ce que cette story ne tranche pas : si l'apparence retiree de l'accueil reste sur `.encore` et
    sur l'ecran de manche, ou si elle est absorbee par le cabinet lui-meme, n'est pas decide ici -
    c'est une question de forme qu'`ines-design` propose et que `theo-archi` met en oeuvre, sous
    le jugement de `nadia-prof`.
  - Ce qui n'est pas testable par un automate et releve de la seule porte pedagogique de
    `nadia-prof` : qu'un enfant de CP ou de CM1, en regardant l'ecran de manche, reconnaisse
    effectivement une machine plutot qu'un decor abstrait ou incomprehensible ; que les images
    choisies soient percues comme faites pour un enfant et non comme un symbole de jeu d'argent
    qu'aucune liste de mots ne peut epuiser ; et qu'aucune combinaison de forme, de couleur ou
    d'image n'evoque malgre tout un signal de gain a l'arret des colonnes (condition 2 du veto),
    ce qui ne peut etre verifie que sur le rendu reel, en jouant.
- **Lien decision** :
  `DEC-0017-perimetre-sprint-8-la-machine-retrouve-son-contexte-et-trois-verites-corrigees.md`,
  chantier 1 ; veto de `nadia-prof` du 2026-08-10, `FICHE-bandit-manchot-additions-cp.md`,
  section 1.
- **Agent pressenti** : a affecter au planning (avis de faisabilite de `theo-archi` requis,
  proposition de forme d'`ines-design`, arbitrage prealable de `nadia-prof` avant developpement).

### S8-2 - La fin du mouvement des colonnes se termine sans discontinuite visible

- **Besoin** : en tant qu'enfant qui vient d'appuyer sur le bouton de la machine, je veux que les
  colonnes s'arretent completement au lieu de laisser un morceau de voile disparaitre d'un coup,
  afin que mon attention aille au mot affiche plutot qu'a une disparition brusque au bas du
  bouton.
- **Competence programme visee** : transverse.
- **Criteres d'acceptation** :
  - Etant donne le voile decoratif qui traverse chaque colonne pendant le defilement, quand
    l'animation se termine, alors le mouvement se termine sans discontinuite visible, c'est-a-dire
    que la bande soit sortie de la boite avant que l'animation ne rende la main (propriete reprise
    au mot pres de `nadia-prof`, `FICHE-bandit-manchot-additions-cp.md`, section 11).
  - Etant donne le constat mesure par `nadia-prof` avant cette story (13 px de bande encore
    visibles au bas du bouton a 599 ms, plus rien a 600 ms, trois disparitions decalees de 80 ms
    par manche sur un paquet a trois colonnes, soit trente par partie), quand cette story est
    livree et mesuree par la meme methode (image par image sur le rendu reel), alors aucune image
    du mouvement ne montre plus de bande partiellement sortie de la boite au moment ou l'animation
    rend la main.
  - Etant donne les proprietes deja acquises sur ce mouvement (ralentissement progressif, aucun
    depassement, aucun rebond, une colonne montre toujours sa propre proposition ou aucun texte),
    quand cette story est livree, alors aucune d'elles n'est degradee. Comment obtenir la fin
    continue du mouvement n'est pas prescrit ici : c'est une question de forme, propose par
    `ines-design`, mise en oeuvre par `theo-archi`.
  - Ce qui n'est pas testable par un automate et releve de la seule porte pedagogique de
    `nadia-prof` : qu'elle rejoue l'increment et confirme que la disparition brusque a disparu, sur
    le rendu reel et non sur une valeur declaree dans une feuille de style.
- **Lien decision** :
  `DEC-0017-perimetre-sprint-8-la-machine-retrouve-son-contexte-et-trois-verites-corrigees.md`,
  chantier 1 ; `FICHE-bandit-manchot-additions-cp.md`, section 11.
- **Agent pressenti** : a affecter au planning.

### S8-3 - L'explication d'une variation graphemique se distingue visuellement de la consigne

- **Besoin** : en tant qu'enfant de CP qui vient de repondre sur "Le bon son", je veux que
  l'explication qui m'apprend une nouvelle facon d'ecrire un son se remarque autant que le
  bandeau juste/faux qui la precede, afin de ne pas manquer la phrase la plus importante de
  l'ecran.
- **Competence programme visee** : transverse - lisibilite et accessibilite pour un enfant de CP
  qui lit peu et lentement (meme categorie que S0-5, S1-9, S2-1, S7-3). Cette story ne modifie
  aucun contenu pedagogique et ne fait bouger aucune ligne de
  `squad/programme/MATRICE-couverture.md`.
- **Criteres d'acceptation** :
  - Etant donne l'ecran de retour sur un item a variation du paquet "Le bon son" (les six items
    dont la graphie differe du son, par exemple `DENT`), quand la reponse est donnee, juste ou
    fausse, alors l'explication qui nomme la variation ("Dans DENT, ce sont les lettres e et n
    qui font le son an.") est affichee avec une propriete visuelle qui la distingue du texte
    courant et du bandeau juste/faux qui la precede, plutot que d'apparaitre comme du texte de
    paragraphe ordinaire sous ce bandeau.
  - Etant donne les items reguliers du meme paquet, sans explication, quand l'ecran de retour
    s'affiche, alors rien de nouveau n'y est ajoute : cette story ne change que la presentation de
    l'explication existante.
  - Etant donne le texte de l'explication lui-meme, quand cette story est livree, alors il n'est
    pas modifie : la reserve visee porte sur la presentation, pas sur le contenu deja juge exact
    par `nadia-prof`.
  - Ce qui n'est pas testable par un automate et releve de la seule porte pedagogique de
    `nadia-prof` : que cette distinction visuelle attire effectivement l'oeil d'un enfant de CP
    vers la phrase la plus importante de l'ecran, comme elle l'a decrit elle-meme ("ce qui
    l'arrete est le bloc colore ; c'est justement la phrase la plus importante de l'ecran qui est
    la moins signalee").
- **Lien decision** :
  `DEC-0017-perimetre-sprint-8-la-machine-retrouve-son-contexte-et-trois-verites-corrigees.md`,
  chantier 1 ; `FICHE-bandit-manchot-le-bon-son-cp.md`, reserve 6 du 2026-08-10.
- **Agent pressenti** : a affecter au planning.

### S8-4 - Etendre "Le bon verbe" au futur et a l'imparfait de l'indicatif

- **Besoin** : en tant qu'eleve de CM1, je veux choisir la forme correcte d'un verbe frequent
  conjugue au present, au futur ou a l'imparfait pour completer une phrase, en distinguant
  vraiment quel temps est employe, afin de m'entrainer a reconnaitre et a produire les trois
  temps que j'utilise le plus souvent en ecrivant.
- **Competence programme visee** : francais, CM1 (cycle 3), domaine "Orthographe et grammaire" -
  "Conjuguer les verbes frequents au present, futur et imparfait de l'indicatif"
  (`squad/programme/francais/competences-cm1.md`). Cette story vise a faire passer cette ligne de
  `en cours` a `couvert-valide`, sous la barre que `nadia-prof` a ecrite elle-meme et declaree
  close.
- **Criteres d'acceptation**, repris au mot pres des six points de la barre fermee ecrite par
  `nadia-prof` (`FICHE-bandit-manchot-conjugaison-present-cm1.md`, refus du 2026-08-10,
  section 5 : "Rien d'autre. Ce qui n'est pas dans cette liste ne bloquera pas `couvert-valide` et
  ne pourra pas m'etre oppose comme l'ayant bloque.") :
  1. Etant donne le paquet `cm1-conjugaison-present`, quand cette story est livree, alors "le
     futur de l'indicatif et l'imparfait de l'indicatif sont ajoutes, avec les six personnes
     chacun, sur des verbes frequents des trois groupes comme le present les a deja."
  2. Etant donne l'ensemble des items du paquet apres cette story, quand on les relit temps par
     temps, alors "au moins un item par temps oppose deux temps entre eux et pas seulement deux
     personnes d'un meme temps" : au moins un item par temps propose, parmi ses distracteurs, une
     forme conjuguee a un autre temps que la bonne reponse, de sorte que l'enfant doive
     identifier le temps demande et pas seulement choisir entre des formes toutes au meme temps
     (defaut actuel : les quatre propositions sont aujourd'hui toujours au present, le temps est
     donne, jamais choisi).
  3. Etant donne chacun de ces items qui oppose deux temps, quand on relit sa phrase, alors "la
     marque du temps est reperable dans la phrase (un complement comme 'hier', 'demain', 'tous
     les jours') plutot que devinable."
  4. Etant donne l'ensemble des items du paquet apres cette story, quand on les relit un par un,
     alors "aucun item n'admet deux lectures correctes", y compris sur les items qui melangent
     les temps (par exemple un "hier" combine a un present de narration).
  5. Etant donne les indices des items ajoutes ou modifies par cette story, quand on les relit,
     alors "les indices de ces items nomment la marque du temps" et ne se contentent pas de
     nommer la personne.
  6. "Et j'aurai joue l'increment avant de le dire" : le Done pedagogique de cette ligne n'est
     prononce par `nadia-prof` qu'apres qu'elle a joue l'increment livre, jamais sur la seule
     lecture du contenu.
  - Deux reserves neuves du 2026-08-10, sur le meme paquet, entrent aussi dans les criteres de
    cette story :
    - Etant donne les mots ajoutes par le futur et l'imparfait, quand on mesure leur largeur de
      glyphes rendus dans une colonne de 160 px, alors aucun ne sort de sa colonne, sachant que la
      marge la plus etroite du paquet aujourd'hui est de 2,0 px sur `mangeons` (156,0 px de large)
      et que tout mot plus large que 156 px en sortira (`FICHE-bandit-manchot-conjugaison-
      present-cm1.md`, reserve 1 du 2026-08-10) - ce qui concerne nommement des formes comme
      `finissaient` ou `apprendraient`, plus larges que les formes au present deja mesurees.
    - Etant donne le pli mesure a 360 x 640 quand l'indice s'affiche (aujourd'hui cinq items sur
      douze mettent la seconde rangee de propositions entierement sous le pli,
      `FICHE-bandit-manchot-conjugaison-present-cm1.md`, section 4, sous `DEC-0016`), quand des
      items neufs sont ajoutes par cette story, alors leur proportion d'items en defaut ne
      s'aggrave pas par rapport a celle mesuree sur le paquet actuel.
  - Ce qui n'est pas testable par un automate et releve de la seule porte pedagogique de
    `nadia-prof` : que les phrases, les verbes retenus et les indices soient d'un niveau de
    vocabulaire adapte a un eleve de CM1 ; la conformite du texte au francais correct et
    integralement accentue (`DEC-0002`, amendement du 2026-08-04) ; et les six points ci-dessus
    eux-memes, qui restent son jugement au-dela des deux reserves qu'elle a chiffrees.
- **Lien decision** :
  `DEC-0017-perimetre-sprint-8-la-machine-retrouve-son-contexte-et-trois-verites-corrigees.md`,
  chantier 4 ; `FICHE-bandit-manchot-conjugaison-present-cm1.md`, refus du 2026-08-10, section 5.
- **Agent pressenti** : a affecter au planning par `sofia-sm`.

### S8-5 - Deux propositions du paquet de conjugaison sortent de leur colonne dans le cabinet a 360 px

- **Contexte et decision de perimetre** : `DEC-0018-activation-de-le-bon-verbe-corrigee-dans-le-
  sprint-et-denouement-d-un-second-refus.md` tranche la direction (deja arretee par l'utilisatrice) :
  le sprint corrige la disposition plutot que de retirer "Le bon verbe" du catalogue. Cette story en
  est l'execution.
- **Besoin** : en tant qu'eleve de CM1 qui joue a "Le bon verbe", je veux que chaque mot propose en
  reponse tienne entierement a l'interieur de son bouton, afin de pouvoir le lire et le choisir sans
  qu'il s'ecrive sur la paroi de la machine.
- **Competence programme visee** : transverse - habillage de la mecanique (colonne et cabinet),
  aucun contenu pedagogique nouveau (`ADR-0004`). Cette story ne porte aucune ligne de
  `squad/programme/MATRICE-couverture.md` ; elle leve un blocage d'activation qui empeche la ligne
  francais CM1 (conjugaison) d'etre servie sans reserve, voir `DEC-0018`.
- **Criteres d'acceptation** :
  - Etant donne les 25 items du paquet `cm1-conjugaison-present.yaml` et leurs propositions,
    mesures a 360 px de largeur reelle dans le cabinet livre (`outils/mesurer-rendu.sh 360 <url>
    <png> 640`), quand on releve la largeur des glyphes rendus de chaque proposition, alors
    aucune ne depasse sa colonne de 152,00 px : en particulier `mangeons` (156,02 px mesures le
    2026-08-11, deborde de 2,02 px de chaque cote) et `cherchons` (152,17 px, deborde de 0,09 px)
    tiennent desormais a l'interieur de leur bouton, glyphes compris, sans troncature ni
    compression (`FICHE-bandit-manchot-conjugaison-present-cm1.md`, verdict du 2026-08-11,
    section 7).
  - Etant donne les propositions deja conformes du meme paquet (`cherchent`, 148,30 px et moins),
    quand on les remesure apres cette story, alors leur marge ne se degrade pas.
  - Etant donne les six points de la barre fermee de `nadia-prof` du 2026-08-10, deja satisfaits et
    non rouverts par le troisieme audit du 2026-08-11 (`FICHE-bandit-manchot-conjugaison-
    present-cm1.md`, section 1), quand cette story est livree, alors aucun n'est degrade : ni les
    six personnes par temps, ni l'opposition d'au moins un temps entre eux, ni la marque du temps
    dans la phrase, ni l'exactitude des indices.
  - Ce que cette story ne tranche pas : le moyen d'obtenir ce resultat (raccourcir des formes,
    revoir le budget de colonne du cabinet, tout autre moyen) est une question technique et de
    forme, a `theo-archi` et a `ines-design`, saisis par `sofia-sm` avant developpement comme pour
    toute story qui touche un ecran deja arbitre par `nadia-prof` (`squad/README.md` section 10).
  - Ce qui n'est pas testable par un automate et releve de la seule porte pedagogique de
    `nadia-prof` : que l'increment corrige, rejoue en entier, ne reintroduise aucun des motifs
    qu'elle a deja leves, et son verdict sur l'activation renouvelee de "Le bon verbe" au
    catalogue.
- **Lien decision** : `DEC-0018-activation-de-le-bon-verbe-corrigee-dans-le-sprint-et-denouement-
  d-un-second-refus.md` ; `FICHE-bandit-manchot-conjugaison-present-cm1.md`, verdict du
  2026-08-11, section 7.
- **Agent pressenti** : a affecter au planning par `sofia-sm`. Le chiffrage des pistes de largeur
  et l'implementation sont deja engages au moment ou j'ecris cette story ; leur affectation
  nominative reste une decision de capacite de `sofia-sm`, pas la mienne.

## Sprint 9 - Quatre lignes de mathematiques CP en pur contenu, sans aucun travail de rendu

**Contexte et decision de perimetre** : l'utilisatrice a joue l'increment du sprint 8 et tranche
deux choses - le sprint 9 attaque la couverture de mathematiques CP, domaine "Nombres et calculs" ;
et elle n'est pas satisfaite du rendu CSS actuel, donc aucun travail de rendu ce sprint, ni mesure
ni garde-fou CSS neuf ni arbitrage visuel sur des pixels.
`DEC-0019-perimetre-sprint-9-quatre-lignes-textuelles-de-mathematiques-cp-denombrement-et-monnaie-differes.md`
tranche que quatre des six lignes encore `non couvert` de ce domaine entrent au sprint, toutes
exprimables dans l'enveloppe `type: question` deja existante (`ADR-0007`) sans une ligne de Java, de
gabarit ni de feuille de style neuve : soustractions, complements a 10, comparaison de deux nombres
par un signe, et un probleme simple d'addition ou de soustraction. Les deux lignes restantes
(denombrement, monnaie) sont differees : chacune nomme un verbe ("denombrer", "reconnaitre") qui
designe une reconnaissance visuelle qu'aucun texte ne peut porter, et les couvrir sur leur seul
volet textuel reproduirait le motif de couverture partielle deja sanctionne a plusieurs reprises sur
ce produit (voir `DEC-0019` pour le detail et pour la revue de `contenu-repris/quiz/cp-monnaie.yml`).
Le "ranger" de la ligne comparaison est differe pour un motif distinct, de risque de largeur non
verifie plutot que d'impossibilite textuelle - voir `DEC-0019`.

Chaque story ouvre une fiche de conformite sous `squad/conformite/`, remplie a l'exception du bloc
de validation reserve a `nadia-prof` : le contenu de ce sprint repasse devant elle comme tout
contenu neuf, le niveau scolaire et la qualite pedagogique restant entierement de son ressort.

### S9-1 - S'entrainer aux petites soustractions en jouant

- **Besoin** : en tant qu'eleve de CP, je veux m'entrainer a calculer une soustraction dont le
  resultat reste petit, afin de savoir combien il en reste quand j'en enleve quelques-unes.
- **Competence programme visee** : mathematiques, CP (cycle 2), domaine "Nombres et calculs",
  "Calcul mental - soustractions dans la limite de 10" (`squad/programme/mathematiques/
  competences-cp.md`).
- **Criteres d'acceptation** :
  - Etant donne le paquet de contenu de ce jeu, quand on lit chaque item, alors son enonce est une
    soustraction ecrite en chiffres (par exemple "9 - 4 = ?") dont le premier terme, le second
    terme et le resultat sont chacun un nombre entier compris entre 0 et 10 inclus, et le second
    terme n'est jamais superieur au premier (aucun resultat negatif).
  - Etant donne l'ensemble des items, quand on releve le resultat de chacun, alors chaque valeur
    entiere de 0 a 10 apparait au moins une fois parmi les resultats - aucune borne de l'intervalle
    n'est absente, a la difference du defaut deja corrige sur les additions par `DEC-0007`.
  - Etant donne chaque item, quand on compte ses propositions, alors il y en a exactement trois :
    la bonne reponse et deux distracteurs numeriques, chacun different de la bonne reponse et
    compris dans le meme intervalle 0-10.
  - Etant donne les distracteurs de chaque item, quand on les examine, alors au moins un des deux
    correspond a une erreur frequente identifiable (par exemple le resultat de l'addition des deux
    termes plutot que de leur soustraction, ou le resultat obtenu en inversant l'ordre des termes) :
    un distracteur n'est jamais un nombre choisi sans rapport avec l'item.
  - Etant donne le paquet complet, quand on compte ses items, alors il en compte au moins 20, pour
    eviter le defaut deja releve sur "Le bon son" (trop peu d'items pour dix manches).
  - Etant donne chaque item, quand on verifie qu'il porte un indice (`indice`), alors ce champ est
    rempli et non vide, comme l'exige la mecanique bandit manchot pour tout paquet du produit
    (`ADR-0016`).
  - Etant donne le paquet, quand on le charge au demarrage de l'application, alors il valide le
    schema `type: question` d'`ADR-0007` : un fichier YAML et une ligne de catalogue, zero ligne de
    Java, zero gabarit ni feuille de style neuve.
  - Ce qui n'est pas testable par un automate et releve de la seule porte pedagogique de
    `nadia-prof` : l'exactitude et la clarte de chaque enonce et de chaque indice pour un enfant de
    CP, la plausibilite des distracteurs, et si ce contenu entraine reellement le calcul mental
    plutot qu'un exercice de lecture.
- **Lien decision** :
  `DEC-0019-perimetre-sprint-9-quatre-lignes-textuelles-de-mathematiques-cp-denombrement-et-monnaie-differes.md`.
- **Agent pressenti** : a affecter au planning par `sofia-sm` (avis de faisabilite de `theo-archi`
  requis avant affectation, comme pour toute story neuve).

### S9-2 - Trouver le complement a 10

- **Besoin** : en tant qu'eleve de CP, je veux trouver combien il manque pour arriver a 10, afin de
  construire des reperes solides sur les nombres qui vont ensemble jusqu'a 10.
- **Competence programme visee** : mathematiques, CP (cycle 2), domaine "Nombres et calculs",
  "Complements a 10" (`squad/programme/mathematiques/competences-cp.md`).
- **Criteres d'acceptation** :
  - Etant donne le paquet, quand on lit chaque item, alors son enonce demande le complement a 10
    d'un nombre entier compris entre 0 et 10 inclus (par exemple "Combien pour arriver a 10, en
    partant de 6 ?"), et la bonne reponse est l'entier qui, ajoute au nombre de l'enonce, fait
    exactement 10.
  - Etant donne l'ensemble des items, quand on releve le nombre de depart de chacun, alors chaque
    valeur entiere de 0 a 10 apparait au moins une fois - couverture complete de la table des
    complements a 10, pas seulement ses valeurs les plus faciles (5, 9).
  - Etant donne chaque item, quand on compte ses propositions, alors il y en a exactement trois :
    la bonne reponse et deux distracteurs numeriques distincts, dans l'intervalle 0-10.
  - Etant donne les distracteurs de chaque item, quand on les examine, alors au moins un des deux
    correspond a une erreur frequente identifiable (par exemple le nombre de depart lui-meme, ou le
    complement a 9 plutot qu'a 10).
  - Etant donne le paquet complet, quand on compte ses items, alors il en compte au moins 9 -
    strictement plus que les 7 manches declarees pour ce jeu au catalogue (`ADR-0023`), un plancher
    different des trois autres paquets du sprint parce que l'espace des complements a 10 sous forme
    canonique est fini a onze valeurs (0 a 10 inclus) - voir `DEC-0020` pour le calcul complet.
  - Etant donne l'entree de ce jeu dans `catalogue.yaml`, quand on lit `nombreDeManches`, alors elle
    vaut 7 (et non 10 comme les trois autres jeux ouverts ce sprint), decision assumee dans
    `DEC-0020`.
  - Etant donne chaque item, quand on verifie qu'il porte un indice, alors ce champ est rempli et
    non vide (`ADR-0016`).
  - Etant donne le paquet, quand on le charge au demarrage de l'application, alors il valide le
    schema `type: question` d'`ADR-0007`, zero ligne de Java, zero gabarit ni feuille de style
    neuve.
  - Ce qui n'est pas testable par un automate et releve de la seule porte pedagogique de
    `nadia-prof` : idem S9-1.
- **Lien decision** :
  `DEC-0019-perimetre-sprint-9-quatre-lignes-textuelles-de-mathematiques-cp-denombrement-et-monnaie-differes.md`,
  `DEC-0020-s9-2-debloquee-nombre-de-manches-abaisse-pour-les-complements-a-10.md` (deblocage,
  criteres corriges).
- **Agent pressenti** : a affecter au planning par `sofia-sm`, desormais possible sans obstacle
  bloquant (`DEC-0020` leve le blocage nomme par `theo-archi` dans son avis de faisabilite).

### S9-3 - Comparer deux nombres avec les signes <, > et =

- **Besoin** : en tant qu'eleve de CP, je veux dire si un nombre est plus grand, plus petit ou egal
  a un autre, afin de savoir lire et utiliser les signes `<`, `>` et `=` entre deux nombres jusqu'a
  100.
- **Competence programme visee** : mathematiques, CP (cycle 2), domaine "Nombres et calculs",
  "Comparer et ranger des nombres jusqu'a 100 (`<`, `>`, `=`)" (`squad/programme/mathematiques/
  competences-cp.md`). **Story limitee explicitement a la comparaison de deux nombres par un
  signe** : le "ranger" de cette competence (ordonner une suite de trois nombres ou plus) n'entre
  pas dans son perimetre, differe par `DEC-0019` pour un motif de risque de largeur non verifie,
  distinct du motif d'exclusion visuelle des lignes denombrement et monnaie.
- **Criteres d'acceptation** :
  - Etant donne le paquet, quand on lit chaque item, alors son enonce presente deux nombres
    entiers, chacun compris entre 0 et 100 inclus et ecrit separement de l'autre (les deux valeurs
    peuvent etre egales : c'est le cas requis par le critere sur "=" ci-dessous, par exemple
    "58 ___ 58"), separes par un espace a completer (par exemple "38 ___ 52"), et les trois
    propositions possibles sont toujours exactement les trois signes "<", ">", "=" - jamais un
    nombre en proposition.
  - Etant donne l'ensemble des items, quand on releve le signe correct de chacun, alors chacun des
    trois signes est la bonne reponse d'au moins un tiers des items - repartition equilibree entre
    `<`, `>` et `=`, pour eviter qu'un enfant reussisse en devinant toujours le meme signe.
  - Etant donne les items dont la bonne reponse est "=", quand on les compte, alors ils
    representent au moins 20 % des items : l'egalite entre deux ecritures d'un meme nombre doit
    etre reellement travaillee, pas seulement presente comme un cas limite anecdotique.
  - Etant donne les paires de nombres des items dont la bonne reponse est "<" ou ">", quand on
    releve l'ecart entre les deux nombres, alors cet ecart n'est pas systematiquement large : au
    moins un quart de ces items oppose deux nombres separes de 10 ou moins, pour eviter que la
    comparaison se reduise a "le nombre le plus long gagne" sans lire les chiffres.
  - Etant donne le paquet complet, quand on compte ses items, alors il en compte au moins 20.
  - Etant donne chaque item, quand on verifie qu'il porte un indice, alors ce champ est rempli et
    non vide (`ADR-0016`).
  - Etant donne le paquet, quand on le charge au demarrage de l'application, alors il valide le
    schema `type: question` d'`ADR-0007`, zero ligne de Java, zero gabarit ni feuille de style
    neuve - les propositions sont des signes a un seul caractere, largeur triviale au regard des
    planchers de colonne deja mesures sur le catalogue.
  - Ce qui n'est pas testable par un automate et releve de la seule porte pedagogique de
    `nadia-prof` : idem S9-1.
- **Lien decision** :
  `DEC-0019-perimetre-sprint-9-quatre-lignes-textuelles-de-mathematiques-cp-denombrement-et-monnaie-differes.md`.
- **Agent pressenti** : a affecter au planning par `sofia-sm` (avis de faisabilite de `theo-archi`
  requis avant affectation).

### S9-4 - Resoudre un petit probleme d'addition ou de soustraction

- **Besoin** : en tant qu'eleve de CP, je veux lire une petite histoire avec des nombres et trouver
  la reponse, afin de savoir quand additionner et quand soustraire dans une situation de tous les
  jours.
- **Competence programme visee** : mathematiques, CP (cycle 2), domaine "Nombres et calculs",
  "Resoudre des problemes simples d'addition/soustraction en situation (1 etape)"
  (`squad/programme/mathematiques/competences-cp.md`).
- **Criteres d'acceptation** :
  - Etant donne le paquet, quand on lit chaque item, alors son enonce decrit une situation concrete
    en une ou deux phrases, sans notation mathematique explicite (pas de signe "+" ni "-" dans
    l'enonce), qui se resout par une seule operation d'addition ou de soustraction, et la bonne
    reponse est le nombre entier resultat de cette operation.
  - Etant donne l'ensemble des items, quand on compte ceux qui se resolvent par une addition et
    ceux qui se resolvent par une soustraction, alors chaque categorie represente au moins un tiers
    des items - les deux operations sont reellement melangees, l'enfant doit choisir laquelle
    appliquer, pas seulement l'executer.
  - Etant donne chaque enonce, quand on verifie son contexte, alors il ne fait reference a aucune
    piece, aucun billet ni aucune valeur en euros : perimetre distinct de la ligne "monnaie",
    differee par `DEC-0019`. Un enonce sur des billes, des bonbons, des images ou des animaux
    convient ; un enonce sur l'argent n'entre pas dans cette story.
  - Etant donne chaque item, quand on compte ses propositions, alors il y en a exactement trois,
    toutes numeriques, dont deux distracteurs correspondant a une erreur de raisonnement
    identifiable (par exemple le resultat de l'operation inverse de celle attendue par la situation,
    ou un des deux nombres de l'enonce recopie sans calcul).
  - Etant donne le paquet complet, quand on compte ses items, alors il en compte au moins 20.
  - Etant donne chaque item, quand on verifie qu'il porte un indice, alors ce champ est rempli et
    non vide (`ADR-0016`).
  - Etant donne le paquet, quand on le charge au demarrage de l'application, alors il valide le
    schema `type: question` d'`ADR-0007`, zero ligne de Java, zero gabarit ni feuille de style
    neuve.
  - Ce qui n'est pas testable par un automate et releve de la seule porte pedagogique de
    `nadia-prof` : si la situation racontee est comprehensible et vraisemblable pour un enfant de
    CP, et si devoir choisir l'operation constitue reellement ce que le referentiel nomme "resoudre
    un probleme" plutot qu'un calcul mental deguise - en plus des points communs a S9-1.
- **Lien decision** :
  `DEC-0019-perimetre-sprint-9-quatre-lignes-textuelles-de-mathematiques-cp-denombrement-et-monnaie-differes.md`.
- **Agent pressenti** : a affecter au planning par `sofia-sm` (avis de faisabilite de `theo-archi`
  requis avant affectation).

## Backlog - sprints suivants (intentions non detaillees)

Ces intentions ne sont pas des stories pretes a developper. Elles seront detaillees sprint par
sprint, en priorite sur les trous les plus visibles de `squad/programme/MATRICE-couverture.md`.

### Story de correction - Cinq indices fautifs de "Le bon verbe" (execution de DEC-0014)

**Etat au sprint 8 : livree et validee, conservee ici comme trace.** `nadia-prof` a joue
l'increment livre en s07 (commit `10dd74d`) et prononce **VALIDE SOUS RESERVE**, Done pedagogique
accorde ; le REFUSE du 2026-08-10 decrit ci-dessous est leve
(`squad/conformite/FICHE-bandit-manchot-conjugaison-present-cm1.md`, verdict "second audit,
increment livre en s07"). La seconde condition de remise au catalogue (le repli en deux rangees)
est elle aussi remplie et reverifiee sur le rendu reel. Les deux conditions de `DEC-0015` (quatrieme
amendement) etant remplies ensemble, `DEC-0017` tranche la remise de "Le bon verbe" au catalogue
au sprint 8. Le texte qui suit reste tel qu'ecrit au moment du refus, pour l'historique.

A l'exception de cette story, prete a developper des maintenant : le refus de `nadia-prof` du
2026-08-10 (`squad/conformite/FICHE-bandit-manchot-conjugaison-present-cm1.md`) porte sur un
correctif borne, et `DEC-0014-retrait-le-bon-verbe-du-catalogue.md` le route ici plutot que de le
laisser en simple intention. Son entree au planning et l'affectation d'un developpeur restent une
decision de capacite qui appartient a `sofia-sm`, pas a moi.

**Position sur l'entree au sprint 7 (`DEC-0015`, amendee le 2026-08-10)** : le developpement de
cette correction peut entrer au sprint 7. Elle ne touche qu'un contenu YAML
(`cm1-conjugaison-present.yaml`), pas la mecanique ni le gabarit ; elle est independante des quatre
stories du chantier machine a sous (S7-1 a S7-4) et deja prete a developper. Seul point de
vigilance que je remonte a `sofia-sm` : si S7-1 modifie le rendu de `.indice` dans
`fragments/manche.html`, les deux chantiers touchent la meme zone du meme fichier, et un ordre de
sequencement peut eviter un conflit inutile - c'est une question de capacite et d'ordonnancement,
pas de perimetre, elle reste donc a `sofia-sm`.

**Mais un nouveau Done pedagogique sur les indices ne suffit pas a remettre "Le bon verbe" au
catalogue.** L'amendement du 2026-08-10 a `DEC-0015` (avis de faisabilite de `theo-archi`,
`ADR-0026`) mesure qu'a la largeur cible de 375 px (`DEC-0007` point 5), les quatre colonnes que
"Le bon verbe" exigerait sous la machine a sous debordent, alors que trois colonnes tiennent sans
abaisser aucun plancher pedagogique. C'est une seconde condition, independante de la premiere
(indices corriges) : tant qu'elle n'est pas fermee, la reintegration au catalogue n'a pas lieu,
meme si la correction textuelle est validee par `nadia-prof`.

**`nadia-prof` a depuis rendu l'arbitrage prealable sur la reduction a trois propositions
(`squad/conformite/FICHE-bandit-manchot-conjugaison-present-cm1.md`), et sa mesure ferme cette
sortie sur la question de largeur.** Elle accepte la reduction, avec les douze triplets qu'elle
fixe nommement, mais sur son propre motif - la quatrieme proposition etait deja sous le pli a
360 x 640, independamment de tout probleme de colonnes. Elle a mesure qu'a trois colonnes et 360 px,
neuf items sur douze debordent encore et que deux d'entre eux affichent deux propositions
distinctes avec les memes lettres visibles, donc sans reponse identifiable : "personne ne doit
vendre cette reduction comme la reponse au probleme de largeur." La reduction a trois est donc
**acquise sur le fond pedagogique et insuffisante sur la largeur** : elle ne compte plus comme une
sortie qui fermerait a elle seule la condition de remise au catalogue. Il ne reste que deux sorties
possibles, chacune relevant d'une autorite distincte - replier la mise en page au-dela de trois
colonnes, proposition de forme d'`ines-design` jugee ensuite par `nadia-prof` en jouant
l'increment ; ou reporter le jeu hors de ce sprint, decision de perimetre qui m'appartiendrait si
elle s'imposait. La troisieme sortie, assumer le debordement a 375 px, reste exclue : elle
reviendrait a reculer sur `DEC-0007` point 5 sans aucune contrainte qui l'impose. Voir
`DEC-0015-sprint-7-la-vraie-machine-a-sous-ferme-la-dette-1.md`, sections "Amendement du
2026-08-10" et "Second amendement du 2026-08-10".

**`nadia-prof` a depuis eteint sa propre acceptation de la reduction : elle n'est plus une sortie du
tout, meme insuffisante.** Son motif d'origine visait une cause fausse - elle avait lie le defaut du
pli au passage a quatre propositions, puis a mesure "Le bon petit mot" (jeu catalogue, deux
propositions) et y trouve le meme defaut sur 3 items sur 24 : la cause reelle est la longueur de
l'enonce, pas le nombre de propositions. Elle refuse d'inventer un motif de remplacement - quatre
propositions homophones servent mieux la competence que trois, elle l'avait ecrit elle-meme le matin
meme. Les douze triplets restent ecrits dans sa fiche comme trace d'une decision eteinte ; ils ne
sont plus a executer, et le paquet garde ses quatre propositions par item. **`ines-design` a par
ailleurs trouve le repli de mise en page (deux colonnes par rangee a partir de la quatrieme
proposition), que `nadia-prof` a remesure et confirme** : 160 px par colonne a 360 px, 167-168 px a
375 px, ecart tenu, aucune troncature. Ma condition de remise au catalogue n'a donc plus qu'une
sortie candidate, et elle n'est pas encore fermee : le repli est confirme sur une maquette jetable,
non sur le code livre - `nadia-prof` le dit elle-meme, si ce qui est livre differe de sa mesure elle
reexamine. "Le bon verbe" rejoint le catalogue quand, et seulement quand, les cinq indices sont
corriges et valides (condition independante, inchangee, `DEC-0014`) **et** que l'increment livre le
repli en deux rangees tel que mesure, reverifie par `nadia-prof` sur le rendu reel. Si l'increment
livre ne reproduit pas cette mesure, le report du jeu hors du sprint redevient la seule sortie. Voir
`DEC-0015-sprint-7-la-vraie-machine-a-sous-ferme-la-dette-1.md`, section "Quatrieme amendement du
2026-08-10".

- **Besoin** : en tant qu'eleve de CM1 qui vient de se tromper trois fois de suite sur "Le bon
  verbe", je veux que l'aide qui m'est montree m'enseigne une regle de conjugaison vraie, afin de
  ne pas apprendre a ecrire des formes fausses comme "je prende" ou "il alle" au moment precis ou
  je cherche de l'aide.
- **Competence programme visee** : francais, CM1 (cycle 3), meme ligne que S6-3 ("Conjuguer les
  verbes frequents au present, futur et imparfait de l'indicatif",
  `squad/programme/francais/competences-cm1.md`).
- **Criteres d'acceptation**, repris au mot pres du motif de refus
  (`squad/conformite/FICHE-bandit-manchot-conjugaison-present-cm1.md`, verdict du 2026-08-10,
  section 2) :
  - Etant donne les cinq indices des items dont la bonne reponse est `jouent`, `dessines`,
    `cherche`, `range` et `chantent`, quand on les relit apres cette story, alors aucun n'affirme
    plus qu'"avec [telle personne], le verbe se termine par [telle terminaison] au present" sans
    restriction, alors que cette regle n'est vraie pour aucune de ces cinq personnes prise sans
    restriction (contre-exemples deja dans le paquet : `il va`, `ils vont`, `je fais`).
  - Etant donne chacun de ces cinq indices corriges, quand on verifie sa forme, alors il suit l'un
    des trois modeles deja presents ailleurs dans le meme paquet, au choix de l'auteur du contenu :
    temperer par "presque toujours" (modele des items 2 et 3) ; nommer le groupe concerne, par
    exemple "les verbes en -er" (le plus juste des trois pour ces cinq items, tous du premier
    groupe) ; ou nommer le verbe precis, comme aux items 8, 10 et 11.
  - Etant donne l'ensemble des douze indices du paquet apres cette story, quand on les compare deux
    a deux sur une meme personne grammaticale, alors aucun n'est dementi par un autre item du meme
    paquet - en particulier l'indice de l'item `tu` corrige n'est plus contredit par l'item 8
    ("Tu ___ ton exercice", reponse `finis`), et l'indice de l'item `je` corrige n'est plus dementi
    par l'item 4 ("Je ___ mes devoirs", reponse `fais`).
  - Etant donne les sept indices deja justes du paquet (items 1, 2, 3, 4, 8, 10, 11), quand on les
    relit apres cette story, alors ils ne sont pas modifies.
  - Etant donne les douze enonces et leurs propositions, quand on les relit apres cette story,
    alors ils ne sont pas modifies : le refus ne porte que sur les indices, pas sur le contenu
    grammatical lui-meme, deja juge exact par `nadia-prof`.
  - Ce qui n'est pas testable par un automate et releve de la seule porte pedagogique de
    `nadia-prof` : qu'elle rejoue l'increment et prononce un nouveau Done pedagogique avant que ce
    jeu ne puisse rejoindre le catalogue (`DEC-0014`).
- **Lien decision** : `DEC-0014-retrait-le-bon-verbe-du-catalogue.md` ;
  `squad/conformite/FICHE-bandit-manchot-conjugaison-present-cm1.md`, verdict du 2026-08-10,
  section 2.
- **Agent pressenti** : a affecter au planning par `sofia-sm`.

### Story de correction differee - La bonne reponse n'est plus systematiquement le plus grand nombre propose sur les additions de "Resoudre un petit probleme" (reserve de refus S9-4)

**Non affectee a un sprint, reportee hors du sprint 9 par `DEC-0021-magnitude-des-additifs-de-
problemes-additifs-reportee-hors-sprint.md`.** Cette reserve ne conditionne pas la levee du refus du
2026-08-12 sur `bandit-manchot-problemes-additifs-cp`
(`squad/conformite/FICHE-bandit-manchot-problemes-additifs-cp.md`), qui se leve par la seule
correction des deux enonces "ballons"/"fleurs" (section 1 du meme verdict). Elle est neuf a
instruire pour un sprint futur, une fois le correctif en cours livre et le fichier stabilise.

- **Besoin** : en tant qu'eleve de CP, je veux devoir vraiment calculer pour trouver la bonne
  reponse d'un probleme d'addition, afin de ne pas pouvoir gagner en devinant toujours le plus
  grand nombre propose.
- **Competence programme visee** : mathematiques, CP (cycle 2), domaine "Nombres et calculs",
  "Resoudre des problemes simples d'addition/soustraction en situation (1 etape)"
  (`squad/programme/mathematiques/competences-cp.md`).
- **Criteres d'acceptation**, repris au mot pres de la reserve 1 de `nadia-prof`
  (`squad/conformite/FICHE-bandit-manchot-problemes-additifs-cp.md`, verdict du 2026-08-12,
  section "Reserves") :
  - Etant donne les items du paquet dont la bonne reponse s'obtient par une addition, quand on
    compare cette bonne reponse aux deux distracteurs proposes, alors elle n'est pas, sur
    l'ensemble du paquet, systematiquement le plus grand des trois nombres : au moins plusieurs de
    ces items portent un distracteur superieur a la bonne reponse, plausible par une erreur de
    raisonnement identifiable (par exemple un surcomptage), et non un nombre pris au hasard.
  - Etant donne les 11 items de soustraction du paquet, quand on les relit apres cette story, alors
    ils ne sont pas modifies : la reserve ne porte que sur les items d'addition, et `nadia-prof` a
    deja constate qu'elle ne s'y reproduit pas.
  - Etant donne les deux enonces "ballons"/"fleurs" corriges par le correctif du refus du
    2026-08-12, quand on les relit apres cette story, alors ils ne sont pas modifies une seconde
    fois par ce travail : les deux corrections sont independantes.
  - Ce qui n'est pas testable par un automate et releve de la seule porte pedagogique de
    `nadia-prof` : que chaque nouveau distracteur corresponde a une erreur de raisonnement
    plausible pour un enfant de CP, et non a un nombre arbitraire.
- **Lien decision** : `DEC-0021-magnitude-des-additifs-de-problemes-additifs-reportee-hors-
  sprint.md`.
- **Agent pressenti** : a affecter au planning par `sofia-sm`, une fois le correctif en cours du
  sprint 9 livre sur le meme fichier (`cp-problemes-additifs.yaml`).

### Story de raccourcissement - Les enonces les plus longs de "Le bon petit mot" tiennent au-dessus du pli (execution de DEC-0024)

**Non affectee a un sprint.** `DEC-0024-retrait-le-bon-petit-mot-contenu-valide-ecran-trop-court.md`
retire "Le bon petit mot" (`bandit-manchot-homophones-cm1`) du catalogue jouable : son contenu
(`cm1-homophones.yaml`) reste pedagogiquement valide - Done pedagogique **VALIDE**, accorde le
2026-08-10, non rouvert par ce retrait - mais son enonce le plus long ne tient pas au-dessus du
pli sur l'ecran que le sprint 10 construit pour les sept autres jeux du catalogue
(`mesure-ines-budget-vertical-m4.md`, branche `s10-ines-meuble`).

- **Besoin** : en tant qu'eleve de CM1 qui joue a "Le bon petit mot", je veux voir la question, ma
  reponse et l'aide entierement sur l'ecran sans avoir a faire defiler la page, afin de pouvoir
  jouer une partie sans perdre de vue les boutons de reponse au moment ou j'en ai besoin.
- **Competence programme visee** : francais, CM1 (cycle 3), "Distinguer et utiliser correctement
  les principaux homophones grammaticaux (et/est, son/sont, a/à...)"
  (`squad/programme/francais/competences-cm1.md`) - meme ligne que la story d'ouverture S4-1, une
  reouverture du meme paquet, pas une competence neuve.
- **Criteres d'acceptation**, la forme d'abord, executable et non fondee sur un compte de
  caracteres (`DEC-0016` l'interdit nommement) :
  - Etant donne l'enonce le plus long du paquet raccourci, quand il est rendu a 360 px de large
    avec les feuilles reellement servies et mesure par
    `outils/mesurer-rendu.sh 360 <url> <png> 640` sur chacun des trois etats sous le meuble
    (message d'echec, indice, revelation), alors les propositions de reponse restent entierement
    visibles au-dessus du pli sur les trois etats - la meme methode et le meme viewport de
    reference qu'`ines-design` a appliques aux sept autres jeux du catalogue.
  - Etant donne l'ensemble des 24 items du paquet, quand chacun est mesure de la meme facon, alors
    aucun n'est en defaut, y compris l'item aujourd'hui le plus long ("Apres cette longue
    randonnee en montagne, le vieux chien ne ___ plus vraiment courir comme avant.", 97
    caracteres) : le raccourcissement porte sur autant d'items que necessaire pour atteindre ce
    resultat, pas seulement sur le plus long.
  - Ce qui n'est pas testable par un automate et releve de la seule porte pedagogique de
    `nadia-prof` : un paquet raccourci est un contenu modifie, il repasse devant elle comme un
    contenu neuf sur sa barre en six points deja ecrite pour ce paquet
    (`squad/programme/MATRICE-couverture.md`, ligne francais CM1 homophones), pas seulement sur le
    critere de forme ci-dessus. Le retour au catalogue suppose les deux : la mesure de forme **et**
    un Done pedagogique accorde sur le paquet raccourci.
- **Lien decision** : `DEC-0024-retrait-le-bon-petit-mot-contenu-valide-ecran-trop-court.md` ;
  `mesure-ines-budget-vertical-m4.md` (branche `s10-ines-meuble`) pour la mesure d'origine.
- **Agent pressenti** : a affecter au planning par `sofia-sm`, avec `nadia-prof` pour le
  raccourcissement lui-meme (jugement pedagogique sur quels enonces reduire et comment).

### Story de correction - Les indices de "Le bon verbe" nomment la manipulation, pas la reponse (M2, arbitrage de mecanique de nadia-prof)

**Non affectee a un sprint.** `nadia-prof` a mesure, dans son arbitrage prealable de mecanique du
sprint 10 (`FICHE-bandit-manchot-additions-cp.md`, branche `s10-nadia-mecanique`, section 0 bis
point A), que **25 indices sur 25** de `cm1-conjugaison-present.yaml` se terminent en nommant la
forme conjuguee attendue (par exemple : *"Avec 'ils' ou 'elles', le verbe 'jouer' se termine par
'-ent' au present : c'est 'jouent'."*), alors que sa condition **M2** (un indice rattache a la
question ne nomme jamais la reponse) s'applique aux huit jeux du catalogue. Elle nomme elle-meme
son manquement : ces textes avaient ete valides en verifiant qu'ils etaient vrais, jamais qu'ils
revelaient plutot que d'aider. "Le bon verbe" reste au catalogue et ne necessite aucun
raccourcissement ; c'est pourquoi cette story lui est propre et distincte de celle qui precede,
qui rouvre integralement `cm1-homophones.yaml` pour un motif different et pourra corriger ses 22
indices fautifs dans le meme geste que le raccourcissement de ses enonces.

- **Besoin** : en tant qu'eleve de CM1 qui vient de se tromper trois fois de suite sur "Le bon
  verbe", je veux que l'aide qui m'est montree m'aide a trouver la bonne forme du verbe par
  moi-meme, afin de ne pas simplement recopier une reponse qu'on m'a deja donnee.
- **Competence programme visee** : francais, CM1 (cycle 3), "Conjuguer les verbes frequents au
  present, futur et imparfait de l'indicatif" (`squad/programme/francais/competences-cm1.md`) -
  meme ligne que S6-3 et `DEC-0014`, deja `couvert-valide`, non rouverte par cette story.
- **Criteres d'acceptation**, repris au mot pres du motif nomme par `nadia-prof` (M2) :
  - Etant donne chacun des 25 indices du paquet, quand on le relit apres cette story, alors il ne
    se termine plus par la formule "c'est '<forme conjuguee>'" ni par aucune autre formulation qui
    nomme explicitement la reponse attendue de la manche.
  - Etant donne chacun de ces 25 indices corriges, quand on verifie sa forme, alors il continue de
    porter la manipulation qui menait a la reponse (la personne grammaticale et la regle ou
    l'exception de terminaison), seulement ampute de sa conclusion : par exemple, l'indice cite
    ci-dessus devient utilisable en s'arretant a "... se termine par '-ent' au present", sans les
    quatre derniers mots.
  - Etant donne les 25 enonces et leurs propositions, quand on les relit apres cette story, alors
    ils ne sont pas modifies : la story ne porte que sur le texte des indices, pas sur le contenu
    grammatical deja juge exact par `nadia-prof`.
  - Ce qui n'est pas testable par un automate et releve de la seule porte pedagogique de
    `nadia-prof` : qu'elle rejoue l'increment et confirme que chaque indice raccourci aide encore
    reellement l'enfant a trouver la forme, plutot que de simplement lui retirer sa derniere
    phrase.
- **Lien decision** : `FICHE-bandit-manchot-additions-cp.md` (branche `s10-nadia-mecanique`),
  arbitrage de mecanique du 2026-08-12, section 0 bis point A et condition M2 ;
  `DEC-0024-retrait-le-bon-petit-mot-contenu-valide-ecran-trop-court.md`, point 5.
- **Agent pressenti** : a affecter au planning par `sofia-sm`.

### Autres intentions

- **Constat neuf du 2026-08-10 sur "Le bon petit mot" (jeu catalogue, Done accorde, ligne
  `couvert-valide`, inchanges par ce constat)** : sur 3 de ses 24 items, l'affichage de l'indice
  pousse les deux boutons de reponse sous le pli d'un ecran de 360 x 640 (sur l'item 24, plus rien
  n'est visible) ; 0 item sur 24 n'est en defaut sans l'indice. La cause est la longueur de l'enonce
  a 2 rem qui se replie sur cinq ou six lignes, pas le nombre de propositions ni la mise en page en
  colonnes (`squad/conformite/FICHE-bandit-manchot-homophones-cm1.md`, constat du 2026-08-10).
  `nadia-prof` ne fait pas de ce constat un motif de refus, et dit explicitement qu'il ne doit pas
  ralentir le chantier machine a sous en cours. `DEC-0016-hauteur-de-viewport-de-reference-et-
  contenu-au-dessus-du-pli.md` fixe la regle et le viewport de reference (360 x 640) que ce
  correctif devra respecter, sans trancher entre les deux pistes possibles : raccourcir les cinq
  enonces les plus longs (jugement pedagogique, `nadia-prof`) ou traiter la hauteur de l'ecran par
  la mise en page (`theo-archi`, `ines-design`). Non affecte a un sprint ; a instruire quand
  `sofia-sm` juge la capacite disponible. **Devenu bloquant au sprint 10** : voir
  `DEC-0024-retrait-le-bon-petit-mot-contenu-valide-ecran-trop-court.md`, qui retire ce jeu du
  catalogue plutot que de le raccourcir dans ce sprint.
- **Risque de sprint 10, nomme et non ferme : la marge de "Le bon verbe" sous le pli tient a un
  seul pixel.** `ines-design` fait tenir ce jeu sous la condition M4 de `nadia-prof` apres
  resserrement des marges de forme, mais a 1 px de marge avant le pli sur son item le plus charge
  (`mesure-ines-budget-vertical-m4.md`, branche `s10-ines-meuble`, section 3) - elle le nomme
  elle-meme comme "un passage, pas une marge de securite". Ce n'est pas une story : rien n'est
  a developper ici, et la ligne de matrice conjugaison CM1 n'est pas touchee. C'est un point de
  vigilance pour `yanis-qa` et pour le prochain contenu ajoute a ce paquet, puisqu'aucun automate
  du depot ne garde cette propriete (`ADR-0025`, `ADR-0028`) : un enonce ou une reponse ne
  serait-ce qu'un mot plus long que l'actuel plus long du paquet ferait repasser cet etat sous le
  pli sans qu'aucun test ne rougisse.
- La ligne mathematiques CP (calcul mental additions) est `couvert-valide` (2026-08-06) sur la
  borne jusqu'a 10 uniquement : elargir son contenu a la borne jusqu'a 20 (meme mecanique, meme
  jeu) reste a instruire. **Correction du 2026-08-12** : cette puce conflait a tort trois lignes
  distinctes de la matrice (additions, soustractions, complements a 10) comme si elargir la
  premiere suffisait a clore les deux autres - ce n'est pas le cas, chacune est sa propre ligne.
  Les soustractions et les complements a 10 sont desormais des stories a part entiere, S9-1 et
  S9-2, `DEC-0019-perimetre-sprint-9-quatre-lignes-textuelles-de-mathematiques-cp-denombrement-et-
  monnaie-differes.md`.
- `textes-trous/cm1-conjugaison.yml` ouvre la ligne conjugaison CM1 au sprint 6 (S6-3,
  `DEC-0013-perimetre-sprint-6.md`), sur le present de l'indicatif uniquement. `textes-trous/
  cm1-accords.yml` (partie de "accorder en genre et en nombre dans le groupe nominal", l'accord de
  l'adjectif seul, determinant et nom deja fixes dans chaque phrase) reste le candidat non retenu
  cette fois, toujours identifie par `DEC-0008` comme directement compatible avec la forme
  `Question` sans nouvel arbitrage de matiere : a instruire pour un sprint suivant, sans date fixee.
  Le futur et l'imparfait de la ligne conjugaison CM1, laisses dehors par S6-3, restent eux aussi a
  instruire, comme deux extensions distinctes du meme jeu.
- Introduire une deuxieme mecanique de jeu pour les competences que le bandit manchot sert mal
  (association image/mot, memory, texte a trous), en respectant le meme decouplage
  mecanique/contenu. Le jour ou un second jeu existe, trancher la question de process laissee
  ouverte par `DEC-0006` et rendue a `camille-po` par `nadia-prof` : une fiche de conformite
  distincte par jeu, ou un mecanisme different pour tracer un chantier transverse a plusieurs
  jeux.
- Couvrir les tables de multiplication CM1, competence a forte demande et deja presente dans
  `contenu-repris/cm1-tables-multiplication.yml` comme matiere premiere a repasser devant
  `nadia-prof`.
- Etendre le referentiel a d'autres matieres que mathematiques et francais (histoire-geographie,
  sciences, EMC, anglais - dont de la matiere premiere existe deja dans `contenu-repris/`), ce
  qui suppose au prealable d'ecrire leurs referentiels de competences sous
  `squad/programme/<matiere>/`.
- Suivre la file d'attente de validation pedagogique (`squad/validation/FILE-ATTENTE.md`,
  a 1 increment en attente depuis le premier audit de s04 - `bandit-manchot-le-bon-son-cp`) et
  declencher le gel du contenu si elle depasse 2-3 increments non valides en attente.
- Reserves non bloquantes restees hors des stories S5-2 a S5-7, nommees par `nadia-prof` mais qui
  ne fondent aucun refus ni aucune condition ecrite a elles seules, a instruire quand une de ces
  deux lignes reviendra au planning. **Revues au 2026-08-10 a la lumiere de la recomposition de
  "Le bon son" en s06** (`squad/conformite/FICHE-bandit-manchot-le-bon-son-cp.md`, verdict du
  2026-08-10, section 5) :
  - Sur "Le bon son" : des mots trop longs pour le moment de l'annee ou cette competence se
    travaille - **attenuee par la recomposition (mots courts comme `DENT`, `PONT`) mais pas
    fermee** : `TIMBRE`, `MONTRE`, `NOMBRE` et `MANTEAU` restent au-dessus de six lettres ;
    **aucune progression de difficulte dans le tirage - premiere story a instruire sur ce jeu**,
    `nadia-prof` ayant observe en jeu que la premiere manche d'une partie peut tirer une exception
    (`VENT`, graphie `en`) avant que l'enfant ait vu la regle (`an`) ; **le son `[ch]` (consonne)
    mele sans distinction aux cinq autres sons (voyelles) - reserve close, elle disparait de fait
    avec la recomposition puisque `ch` sort du paquet.**
  - Sur "Le bon petit mot" : aucun sujet feminin dans le paquet (`elle`, `elles` jamais employes) ;
    l'item "Papa et maman font la cuisine." presuppose une configuration familiale (non
    stereotypee sur les roles, signale sans en faire un motif) ; l'item "thé ou café" propose un
    choix d'adulte plutot qu'un choix qui parle a un enfant de neuf ans.
  - Commune aux quatre jeux : l'accueil ne mentionne le niveau vise ("CP" ou "CM1") que pour
    "Calcul CP", pas pour "Le bon son", "Le bon petit mot" ni "Le bon verbe" - un enfant peut
    ouvrir le jeu d'un autre niveau sans le savoir. Constat qui empire a chaque jeu de francais
    ajoute (`nadia-prof`, verdict du 2026-08-10 sur "Le bon verbe", reserve 6 de sa fiche) : la
    moitie des jeux du catalogue porte cette mention, l'autre moitie non. **Reaggrave, chiffre par
    `nadia-prof` au troisieme audit du 2026-08-11** (`squad/conformite/FICHE-bandit-manchot-
    conjugaison-present-cm1.md`, verdict du 2026-08-11, section 8, reserve 6) : l'accueil propose
    desormais trois jeux de CM1 non marques a cote d'un seul jeu de CP marque.
  - Reserves nouvelles sur "Le bon son", datees du 2026-08-10, non bloquantes
    (`squad/conformite/FICHE-bandit-manchot-le-bon-son-cp.md`, verdict du 2026-08-10, section 5) :
    quatre des six items a variation forment deux paires de mots rimant sur une seule graphie et
    une seule position (`DENT`/`VENT` en `-ENT`, `OMBRE`/`NOMBRE` en `-OMBRE`) - la correction est
    deja nommee par `nadia-prof` et ne demande aucun arbitrage neuf : remplacer `VENT` par `LAMPE`
    ou `JAMBE` (graphie `am`) et `NOMBRE` par `POMPIER` (graphie `om` en position non finale) ;
    l'explication qui suit la reponse n'est pas distinguee visuellement de la consigne (question de
    mise en forme pour `ines-design`) ; l'explication reprend presque mot pour mot l'indice du meme
    item (sans consequence pedagogique selon elle, a corriger si ce jeu est retouche).
- **Trou de decision produit, constate le 2026-08-10 par `nadia-prof` en auditant "Le bon verbe"**
  (`squad/conformite/FICHE-bandit-manchot-conjugaison-present-cm1.md`, verdict du 2026-08-10,
  section 4) : a une largeur et une hauteur de viewport reellement de 360 x 640, la quatrieme
  proposition de ce jeu (premier jeu du produit a quatre propositions au lieu de trois) est sous le
  pli, 2 px visibles sur 69. Comme l'ordre des propositions est tire, la bonne reponse sera parfois
  celle-la. `nadia-prof` refuse d'en faire un motif de refus, au motif honnete qu'**aucune decision
  du depot ne fixe de hauteur de viewport minimale** - `DEC-0007` point 5 ne fixe qu'une largeur
  cible (375 px), jamais une hauteur. A instruire : une decision produit sur une hauteur de
  viewport cible, sur le meme modele que `DEC-0007` point 5, avant qu'un jeu a quatre propositions
  ou plus revienne au planning.
- **Limite du critere de `nadia-prof` sur les homophones CM1, nommee par elle-meme le 2026-08-10**
  (`squad/conformite/FICHE-bandit-manchot-homophones-cm1.md`, verdict du 2026-08-10, section 1) :
  dans l'item "...ne ___ plus vraiment courir...", le mot `ne`, immediatement a gauche du trou,
  suffit a lui seul a exclure `peu` sans avoir besoin d'aller chercher le mot decisif plus loin.
  Elle ne s'en sert pas pour refuser (la meme limite existe deja sur un item qu'elle a credite en
  s05, et elle ne deplace pas ses poteaux apres coup), mais elle nomme une version plus exigeante
  possible du critere pour une story ulterieure : qu'aucun mot voisin du trou, meme un mot
  grammatical, ne suffise a trancher.
- **Candidat neuf pour une future extension des homophones CM1, nomme par `nadia-prof` le
  2026-08-06 et confirme au verdict du 2026-08-10** : `c'est`/`s'est` (et `sait`/`sais`) satisfait
  ses quatre criteres de "principal" et compte parmi les fautes les plus frequentes au CM1. Elle ne
  l'a pas ajoute a sa condition de `couvert-valide` pour ne pas la faire grossir apres l'engagement
  de l'equipe ; elle le nomme comme premier candidat d'une story ulterieure. Elle signale par
  ailleurs que `squad/programme/francais/competences-cm1.md` gagnerait a porter la liste des dix
  familles retenues (a/à, et/est, son/sont, on/ont, ou/où, ce/se, ces/ses, la/là/l'a, mais/mes,
  peu/peut) a la place de ses points de suspension - remonte une premiere fois le 2026-08-06,
  toujours ouvert, non traite ici.
- Le menu de l'en-tete (S6-1, `DEC-0012`) reste volontairement a une seule entree tant qu'aucune
  deuxieme destination reelle n'existe. Un filtre par niveau (CP / CM1) est le candidat le plus
  visible pour la reserve juste au-dessus ; il suppose d'abord un vrai filtre cote serveur (une
  fonctionnalite a ecrire), pas seulement un lien ajoute dans le menu - ne pas ouvrir l'un sans
  l'autre. **Constat date du 2026-08-07, nomme par `nadia-prof` en instruisant l'arbitrage prealable
  de S6-9** (`squad/conformite/FICHE-bandit-manchot-additions-cp.md`, meme section) : une fois
  l'entree masquee sur les cinq etats de l'ecran de jeu (S6-9), l'accueil devient le seul ecran ou
  elle reste visible, donc le seul ou elle ne mene nulle part - exactement le cas que `DEC-0012`
  exclut elle-meme ("un lien vers l'accueil depuis l'accueil est un ornement"). La suite coherente
  serait de la retirer partout, ce qui rouvrirait `DEC-0012` point 2 et deferait une partie de S6-1.
  **Je ne l'amende pas a ce tour** : la question de ce que porte un en-tete sans aucune destination
  reelle a offrir tant qu'une deuxieme n'existe pas merite d'etre instruite pour elle-meme, avec
  `ines-design` et `theo-archi`, plutot que tranchee au fil d'une story qui corrige un defaut
  different (S6-9 traite la duplication de sortie, pas la fonction du menu). Reserve conservee ici,
  au meme rang que le filtre par niveau, jusqu'a instruction.
- Deux autres constats non bloquants, nommes le meme jour par `nadia-prof` dans la meme section, a
  instruire quand cette ligne de dette de forme reviendra au planning :
  - Sur les cinq etats de l'ecran de jeu, la sortie du fragment est le premier element affiche dans
    la zone de contenu, avant le score et le verdict : sur l'ecran de fin de partie, cela invite a
    partir avant d'avoir lu si la reponse etait juste. Un correctif d'ordre (afficher la sortie sous
    "C'est fini !"), pas de saillance : elle ne le retire donc pas de S6-9.
  - "Recommencer" (ecran d'erreur) n'est pas plus dechiffrable au CP que ne l'etait "Accueil" avant
    S6-9. Cet ecran a deja ete valide en s01 et `nadia-prof` ne le rouvre pas par ce constat ; elle
    le nomme par honnetete au moment ou une lisibilite comparable est demandee ailleurs.
- **Reserves nouvelles du 2026-08-11, chantier du cabinet (S8-1 a S8-3),
  `squad/conformite/FICHE-bandit-manchot-additions-cp.md`, verdict du 2026-08-11, section 8** :
  - Le contour du cabinet ne se distingue des colonnes qu'a un rapport de contraste de 1,71:1
    (`#1d4b6b` contre `#2e6f9e`) - contre la page il ressort a 8,61:1, donc le tour de l'objet reste
    net et rien n'est refuse, mais un enfant qui distingue mal les couleurs lit surtout un aplat
    bleu uniforme. `ines-design` propose un ecart de luminance plus franc entre le cadre et les
    colonnes ; `camille-po` priorise. Non bloquant, a instruire pour les quatre jeux du bandit
    manchot. **Correction du 2026-08-12** : cette puce portait `9,25:1`, une valeur fausse.
    `nadia-prof` a recalcule les cinq paires de contraste pendant l'arbitrage prealable du sprint 10
    (`squad/conformite/FICHE-bandit-manchot-additions-cp.md`, section 4, verdict du 2026-08-12) et
    trouve la cause exacte de son erreur : `9,25:1` est le contraste du cadre contre le blanc pur
    `--recreo-accent-texte`, pas contre le fond de page `--recreo-fond`. La valeur juste, contre la
    page, est `8,61:1` - le chiffre qu'`ines-design` avait avance au sprint 8. Le rapport
    cadre/colonnes (1,71:1 ci-dessus) ne bouge pas, et la reserve reste non bloquante : elle l'ecrit
    elle-meme, la conclusion ne change pas, seul le chiffre qui la portait etait faux.
  - Le defaut de pli du quatrieme cote du cabinet (voir `DEC-0016`, amendement du 2026-08-11) est
    aggrave de 40 px par le cabinet sur "Le bon petit mot" et sur "Le bon verbe" : le budget
    vertical pose par `camille-po` sur "Le bon son" (le cas le moins charge du catalogue et non le
    plus charge, erreur nommee par `nadia-prof`) doit etre repose sur ces deux jeux. Traite par la
    story ouverte a l'amendement du 2026-08-11 de `DEC-0016`, pas ici.
- **Reserves nouvelles du 2026-08-11, chantier de la conjugaison etendue (S8-4),
  `squad/conformite/FICHE-bandit-manchot-conjugaison-present-cm1.md`, verdict du 2026-08-11,
  section 8** :
  - **Amelioration des indices ajoutes par S8-4, non bloquante, deux corrections bornees et
    petites** : trois indices d'imparfait ("faire", "etre", "avoir" irreguliers) laissent croire que
    l'irregularite du verbe explique sa forme, alors qu'a l'imparfait les six terminaisons sont les
    memes pour tous les verbes - rien de faux n'est enseigne, une regularite utile est laissee de
    cote ; et cinq indices de futur ("Demain, ...") ne nomment pas le mot "Demain" comme marque du
    temps, a la difference des indices d'imparfait et de present. Prete a developper (correction de
    quelques mots par indice, sans toucher aux enonces ni aux propositions), non affectee a un
    sprint, a instruire quand `sofia-sm` juge la capacite disponible.
  - **L'imparfait du paquet ne porte aucun verbe du deuxieme groupe**, alors que c'est le temps ou
    apparait l'infixe `-iss-` (`nous finissions`, `ils finissaient`), la difficulte principale de
    l'imparfait au CM1. `nadia-prof` ne l'oppose a rien (sa barre du 2026-08-10 ne le demandait pas)
    et note elle-meme que la forme la plus courte disponible (`finissais`) ne peut pas etre ajoutee
    avant que la largeur de colonne du paquet soit reglee (S8-5) : la marge y est deja nulle. A
    instruire seulement apres S8-5, jamais avant.
  - **Constat mesure, ni motif ni reserve** : seuls 3 items sur 25 opposent deux temps entre eux ;
    le tirage etant sans remise, une partie de dix manches n'en tire aucun dans 19,8 % des cas
    (`C(22,10) / C(25,10)`). Une partie sur cinq environ ne demande donc jamais a l'enfant
    d'identifier le temps employe. Candidat pour une future extension du paquet (ajouter des items
    qui opposent deux temps, ou deplacer la marque temporelle hors du premier mot de la phrase, qui
    est aujourd'hui totalement predictif sur les 13 items neufs) ; non affecte a un sprint.
- **Reserves du 2026-08-11, quatrieme audit de "Le bon verbe" sur l'increment corrige de S8-5**
  (`squad/conformite/FICHE-bandit-manchot-conjugaison-present-cm1.md`, verdict du 2026-08-11,
  "quatrieme audit", section 6). Toutes deux nommees par `nadia-prof` elle-meme comme des reserves,
  pas des motifs, et non converties en motif malgre la tentation : le Done pedagogique de ce jeu est
  accorde et la ligne de matrice passe a `couvert-valide` (voir `squad/programme/
  MATRICE-couverture.md`) independamment de ces deux points.
  - **R1 - les quatre colonnes de ce paquet ne sont confortables a aucune taille que `nadia-prof`
    accepterait, a 360 px.** Sa propre definition d'une colonne lisible (le mot le plus large plus
    1,25 rem de rembourrage de chaque cote, soit 40 px) demande `146,27 + 40 = 186,27 px` pour
    152,00 px disponibles - 152 px etant deja le maximum atteignable, le cadre du cabinet etant a
    son plancher. L'air reel autour du mot le plus large (`mangeons`) est de 2,86 px la ou cette
    definition en demande 20. Ce n'est pas un defaut : le mot tient entierement dans sa boite, sur
    une ligne, et `nadia-prof` l'a verifie sur capture. C'est un inconfort, et la reponse durable
    qu'elle nomme est ailleurs - des formes plus courtes dans le paquet (jugement pedagogique, elle-
    meme), ou une disposition qui n'est pas un 2x2 a 360 px (`theo-archi`, `ines-design`). Non
    affectee a un sprint, a instruire quand ce paquet ou cette disposition revient au planning.
  - **R2 - un paquet futur a quatre propositions heriterait de 30 px en silence.**
    `nombreDePropositions` est un parametre de contenu ; le selecteur qui reduit `.proposition` a
    1,875 rem sous le repli a quatre colonnes (S8-5) s'applique a tout paquet qui en declare quatre,
    sans qu'aucun garde-fou de CI ne mesure la largeur des mots qui y entreraient. Verifie par
    `nadia-prof` le 2026-08-11 : aucun paquet du catalogue autre que celui-ci n'en declare quatre
    aujourd'hui. Elle nomme explicitement qu'un tel paquet devra revenir devant elle avant d'entrer
    dans ce selecteur. A instruire au moment ou une story propose un paquet a quatre propositions
    (ou un item a trois distracteurs), pas avant : ce n'est pas un defaut actuel du catalogue.
  - `nadia-prof` signale aussi qu'elle reste seule garde de l'air des glyphes dans la boite sur ce
    paquet (aucun test ne mesure une largeur de glyphe rendu, `ADR-0025`), et qu'a 2,86 px de marge
    restante, ce defaut reviendra des qu'une forme est ajoutee au paquet. A connaitre pour toute
    story future qui etend ce contenu, sans qu'aucune story ne soit ouverte ici pour ce seul motif.
- **Le rendu CSS actuel est juge insatisfaisant par l'utilisatrice (constat du 2026-08-11, a
  l'ouverture du sprint 9).** Elle ne veut plus de cycle de design pour l'instant : ni nouvelle
  mesure de rendu, ni nouveau garde-fou CSS, ni arbitrage visuel d'`ines-design` ou de `nadia-prof`
  sur des pixels - c'est le motif du sprint 9 tout en contenu (`DEC-0019`). Je n'ouvre ni story ni
  cadrage de solution ici : ce sera une decision separee, quand elle decidera de rouvrir un chantier
  visuel. Ce point est inscrit pour que la squad ne re-instruise pas la question ni ne reprenne un
  travail de rendu de sa propre initiative en attendant.
- **Trois lignes de mathematiques CP restent hors perimetre du sprint 9, differees par `DEC-0019`
  (perimetre-sprint-9-quatre-lignes-textuelles-de-mathematiques-cp-denombrement-et-monnaie-
  differes.md) :**
  - **Denombrer, lire, ecrire et decomposer les nombres jusqu'a 100** - "denombrer" designe compter
    une quantite presentee, un verbe qu'aucun texte ne peut porter ; couvrir seulement "lire,
    ecrire, decomposer" reproduirait le motif de couverture partielle deja sanctionne sur ce
    produit. A instruire, avec un support visuel (objets a compter), quand le gel de rendu sera
    leve.
  - **Reconnaitre et utiliser la monnaie (pieces/billets simples) dans des problemes concrets** -
    "reconnaitre" designe l'apparence d'une piece ou d'un billet, egalement hors de portee du texte
    seul. `contenu-repris/quiz/cp-monnaie.yml` (huit questions, matiere premiere non cablee) ne
    travaille que le volet "utiliser" en toutes lettres et jamais "reconnaitre" ; conserve comme
    candidat pour une story future explicitement scopee sur "utiliser" seul, ou pour une story
    complete une fois des visuels de pieces et billets a portee.
  - **"Ranger" une suite de trois nombres ou plus, volet de la ligne comparaison** - exprimable
    dans le schema existant sans code neuf, mais sur des propositions (des suites de nombres) plus
    larges que celles deja mesurees sur le catalogue (`mangeons`, 156 px) ; a instruire dès qu'une
    mesure de largeur (`outils/mesurer-rendu.sh`, outil existant) confirme que ces suites tiennent
    dans la colonne actuelle, ou dès la levee du gel de rendu.
- **Reserves nouvelles du 2026-08-12, verdicts de `nadia-prof` sur les paquets du sprint 9**
  (`squad/conformite/FICHE-bandit-manchot-soustractions-cp.md`,
  `FICHE-bandit-manchot-comparer-nombres-cp.md`,
  `FICHE-bandit-manchot-problemes-additifs-cp.md`). Aucune de ces reserves ne conditionne la levee
  des trois refus du sprint, qui se leve chacun par la seule correction bornee ecrite par
  `nadia-prof` dans sa fiche. Non affectees a un sprint, a instruire quand `sofia-sm` juge la
  capacite disponible.
  - Sur `bandit-manchot-soustractions-cp` : la consigne du catalogue (`Combien en reste-t-il ?`)
    surmonte un enonce purement chiffre ou le pronom "en" n'a aucun referent a l'ecran (aucun objet
    ni quantite nommee), a la difference du jeu des additions (`Combien ça fait ?`), exact pour une
    ecriture chiffree - a trancher entre changer la consigne ou assumer ce cadrage. Et aucun
    distracteur du paquet n'attaque l'inversion des termes (`3 - 5` traite comme `5 - 3`), pourtant
    citee comme erreur attendue par le critere de la story S9-1.
  - Sur `bandit-manchot-comparer-nombres-cp` : un seul item sur les 16 inegalites compare des
    unites a dizaines egales (`45 ___ 40`) ; a completer par trois ou quatre items de ce type
    (`58 ___ 52`, `37 ___ 34`, `71 ___ 79`). Aucune paire n'oppose deux nombres formes des memes
    chiffres inverses (`24 ___ 42`, `35 ___ 53`), erreur caracteristique de la numeration decimale
    au CP ; a completer par deux items de ce type.
  - Sur `bandit-manchot-comparer-nombres-cp`, constat structurel et non imputable a `lea-dev` : les
    huit egalites du paquet ont toutes la forme `n ___ n`, repondables par simple appariement
    visuel sans mobiliser aucune comparaison de grandeurs. `nadia-prof` ne demande pas de retirer
    les egalites - le seuil de 20 % pose par la story S9-3 reste legitime, `=` doit exister comme
    reponse - mais nomme la seule sortie qui rendrait `=` non trivial : comparer une ecriture a un
    calcul, par exemple `3 + 2 ___ 5`. C'est une extension de perimetre qui m'appartient, pas un
    defaut de developpement ; non ouverte ici, a instruire si cette ligne revient au planning
    au-dela de son perimetre actuel ("comparer" seul, "ranger" deja differe par `DEC-0019`).
  - Sur `bandit-manchot-problemes-additifs-cp` : le mot "fanent" (`Le jardinier a 8 fleurs et 5
    fanent.`) n'est pas du vocabulaire de CP, aussi difficile a decoder qu'a comprendre - a
    remplacer par un verbe transparent ; meme examen, plus leger, sur "adopte". Les trois metiers du
    paquet (fermier, clown, jardinier) sont tous masculins et la seule figure adulte feminine
    (Mamie) est en cuisine, alors que les prenoms d'enfants sont bien equilibres - a corriger en
    feminisant un ou deux metiers. Deux enonces ouvrent leur seconde proposition par une virgule
    suivie d'un chiffre (`Le fermier a 5 chats, 2 partent.`, `Le clown a 8 ballons, 4 s'envolent.`),
    difficile a phraser pour un lecteur debutant - a reecrire avec "et", comme les 20 autres items.
  - Sur `bandit-manchot-soustractions-cp` et `bandit-manchot-problemes-additifs-cp`, explicitement
    nomme par `nadia-prof` sur ces deux fiches : la difficulte est constante a l'interieur d'une
    partie, chaque manche etant tiree du meme vivier sans ordre croissant. Meme mecanique de tirage
    sur les deux autres paquets du sprint, mais nuance a garder : `nadia-prof` juge ce point sans
    objet sur `bandit-manchot-complements-10-cp` (les onze cases de la table ne se hierarchisent
    pas assez nettement pour qu'un ordre vaille mieux que le hasard) et ne le releve pas sur
    `bandit-manchot-comparer-nombres-cp`. Symptome signale, cause dans la mecanique du bandit
    manchot et non dans un contenu particulier - route a `theo-archi` pour avis de faisabilite,
    sans solution technique
    prescrite ici.
- **Constat de `nadia-prof` qui depasse le sprint 9 (2026-08-12), a router a `theo-archi` pour
  faisabilite** (`squad/conformite/FICHE-bandit-manchot-soustractions-cp.md`, verdict du
  2026-08-12, section "Ce dont je reste seule garde apres ce verdict") : 371 tests verts et 28
  regles ArchUnit n'ont pas vu les 48 fautes de francais qui motivent ce refus (correction du
  2026-08-12, second audit de la meme fiche : son releve initial disait 47, il manquait `leves` en
  bas de casse dans `ils restent tous leves`, trouve par `marc-dev`), parce qu'aucun
  garde-fou existant ne lit un indice comme du francais ni ne verifie qu'il est vrai
  (`PropositionsAccentueesJouablesIT` verifie qu'une proposition accentuee reste jouable, une
  robustesse d'encodage, pas une correction de langue ; `ChaquePaquetPorteUnIndiceTest` verifie
  qu'un indice existe et n'est pas vide ; `AucunIndiceNeContreditUnAutreItemDuMemePaquetTest`
  compare des indices entre eux, jamais un indice a la verite). Sur les proprietes qu'elle nomme
  ainsi non couvertes par la CI, elle en nomme une seule comme gardable et refuse d'en prescrire la
  solution : **la regularite de magnitude entre la bonne reponse et les distracteurs d'un item se
  compte, donc peut se tester automatiquement** - un garde-fou pourrait par exemple compter, par
  paquet, la proportion d'items ou la bonne reponse est le maximum des propositions, et refuser
  au-dela d'un seuil. Je le route a `theo-archi` pour un avis de faisabilite, sans cadrer le
  garde-fou moi-meme : ni le seuil, ni le mecanisme (test unitaire, regle ArchUnit ou autre)
  ne sont de mon ressort. Les autres proprietes qu'elle nomme (la verite d'un indice, l'exactitude
  arithmetique d'un item, la plausibilite d'un distracteur, la validite semantique d'une situation)
  ne sont pas nommees gardables par elle et ne sont pas routees ici pour cette raison : elle reste
  seule garde de chacune, en jouant.
- **Reserve nouvelle du 2026-08-12, second audit de `bandit-manchot-soustractions-cp`**
  (`squad/conformite/FICHE-bandit-manchot-soustractions-cp.md`, verdict "second audit", reserve 4) :
  trois indices ecrivent `baisses-en 1 seul` avec le chiffre `1` la ou le mot se lirait mieux (items
  `9 - 1`, `10 - 1`, `6 - 1` - par exemple `Lève 9 doigts, puis baisses-en 1 seul.`). Dans
  l'expression "un seul", le mot fonctionne comme un determinant plus que comme un compte, et un
  lecteur debutant lit `baisses-en 1` puis doit revenir sur `seul`, la ou `baisses-en un seul` se
  dechiffre d'un trait. `nadia-prof` la pose explicitement **comme une reserve nouvelle et non comme
  un durcissement du motif d'orthographe** du refus du 2026-08-12 : elle ne l'avait pas relevee au
  premier audit alors qu'elle avait deja lu les 24 indices, elle n'a donc jamais fait partie de la
  barre qui a leve ce refus. Non affectee a un sprint, a instruire quand ce fichier
  (`cp-soustractions.yaml`) sera rouvert.
- **Constat testable du 2026-08-12, second audit sur les trois paquets corriges du sprint 9, a
  router a `theo-archi` pour faisabilite** (`squad/conformite/FICHE-bandit-manchot-soustractions-
  cp.md`, `FICHE-bandit-manchot-comparer-nombres-cp.md`, `FICHE-bandit-manchot-problemes-additifs-
  cp.md`, sections "Ce dont je reste seule garde apres ce second verdict") : sur 1800 coups joues
  volontairement faux pour forcer l'affichage de l'indice a chaque manche (60 parties par jeu, trois
  jeux), `nadia-prof` a verifie elle-meme que **tous les items d'un paquet sont reellement
  atteignables et que les trois propositions servies a l'ecran sont bien celles du fichier** - zero
  anomalie sur les trois paquets. Aucun test ne tient cette propriete aujourd'hui. Elle la nomme
  explicitement comme **la seule des proprietes dont elle reste seule garde qui serait facile a
  tenir par un test** (formulation reprise de son verdict sur les soustractions), au meme titre que
  la regularite de magnitude deja routee ci-dessus et differee par `DEC-0021`. Je la route a
  `theo-archi` pour un avis de faisabilite, sans cadrer le garde-fou moi-meme : ni le mecanisme (test
  unitaire, regle ArchUnit ou autre) ni un seuil quelconque ne sont de mon ressort - ici il n'y a meme
  pas de seuil a fixer, la propriete attendue est une egalite stricte entre ce que le fichier declare
  et ce que la mecanique sert.
- **Reserve O, seconde moitie, ouverte le 2026-08-13 et toujours ouverte au sprint 12**
  (`squad/conformite/FICHE-bandit-manchot-additions-cp.md`, arbitrage de mecanique) : **il n'existe
  aucun chemin vers l'ecran de catalogue qui garde la partie en cours.** Le seul chemin est le lien
  "Les jeux", qui appelle `abandonner()` sans confirmation ; recharger la page ou revenir en arriere
  ramene l'enfant sur l'ecran de jeu, jamais sur le catalogue. `nadia-prof` note que c'est le meme
  enjeu que la clause 1 de M13 (`DEC-0026`), vu depuis la souris plutot que depuis le clavier, et que
  M13 ne le couvre pas. Non affectee a un sprint ; a instruire avec `theo-archi` pour le moyen
  technique (un chemin de consultation du catalogue qui ne detruise pas la partie).
- **Reserve neuve du 2026-08-13** (meme fiche, meme section) : **le libelle "Les jeux" porte deux
  consequences differentes selon l'ecran ou l'enfant le lit.** Sur l'ecran de manche, l'appui ne
  detruit rien ; sur l'ecran de confirmation, il detruit la partie. Un enfant ne peut donc pas
  apprendre, du premier appui, ce que le second lui coutera. `nadia-prof` precise que ce n'est pas
  une condition sur l'increment livre, seulement un motif qu'elle nomme pour que l'equipe ne le
  decouvre pas trois sprints plus tard sans attribution. Non affectee a un sprint.

### Story de correction differee - Un troisieme garde-fou de rang, sur la longueur du texte des propositions, et un audit des onze paquets du catalogue

**Contexte.** Dans son arbitrage de la voie de rendu du sprint 13
(`squad/conformite/ARBITRAGE-PREALABLE-s13-voie-de-rendu-des-propositions.md`, section 6),
`nadia-prof` corrige son propre constat du 2026-08-14 sur `bandit-manchot-conversions-unites-cm1`
("Les mesures") : elle l'avait alors lu comme une statistique acquise plutot que jouee comme une
strategie. Mesure sur 120 manches reellement jouees : presser systematiquement la proposition la
plus courte gagne **47,5 %** des manches, contre 33,3 % au hasard. Verifie ensuite sur le contenu,
hors de tout rendu : la bonne reponse est la plus courte des trois chaines sur **13 items sur 24**,
la plus longue sur 7, intermediaire sur 4 - comme les trois propositions d'un item ne different que
par leurs zeros, "la plus courte" veut dire "le plus petit nombre". Aucun autre paquet du catalogue
ne depasse 25 % sur ce critere ; "Les mesures" est seul a ce niveau. **Ce n'est pas un motif de
refus** : le Done pedagogique de cette ligne reste `couvert-valide`, non rouvert par cette reserve
(`DEC-0031`, amendement du 2026-08-16, section 6).

- **Besoin** : en tant qu'eleve de CM1 qui joue a un jeu de mathematiques, je veux que la bonne
  reponse ne soit pas devinable a la seule longueur du texte affiche, afin d'etre oblige de convertir
  ou de calculer plutot que de reconnaitre la proposition la plus courte.
- **Competence programme visee** : deux volets distincts.
  - Sur `bandit-manchot-conversions-unites-cm1` : mathematiques, CM1 (cycle 3), domaine "Grandeurs et
    mesures", "Convertir des unites de longueur, masse, contenance dans le systeme metrique"
    (`squad/programme/mathematiques/competences-cm1.md`) - ligne deja `couvert-valide`, non rouverte
    par cette story.
  - Sur le garde-fou de longueur et sur l'audit des dix autres paquets : transverse - pas de contenu
    pedagogique.
- **Criteres d'acceptation** :
  - Etant donne un garde-fou generique de longueur, parallele au garde-fou de rang deja livre par
    `lea-dev`, quand il est applique a un paquet quelconque du catalogue, alors "aucune propriete de
    forme d'une proposition - sa longueur, sa largeur, son rang - ne fait gagner mieux que le hasard"
    (repris au mot pres de l'arbitrage de `nadia-prof`, section 7, point 5).
  - Etant donne le paquet corrige de `bandit-manchot-conversions-unites-cm1`, quand on rejoue au
    moins 120 manches en pressant systematiquement la proposition la plus courte, alors le taux de
    reussite ne reproduit plus 47,5 % et se rapproche du plafond que respectent deja les dix autres
    paquets du catalogue (aucun ne depasse 25 %, chiffre de `nadia-prof`) - sans seuil chiffre fixe
    ici a l'avance : c'est elle qui juge, en rejouant, si la correction suffit, meme discipline que
    la condition d'aboutissement qu'elle a posee pour P-6 sur "Le perimetre".
  - Etant donne cette correction, quand on relit les criteres deja coches de cette ligne, alors ils
    restent tous vrais : cette story ne rouvre ni la ligne "Convertir des unites..." ni son Done
    pedagogique sur un autre motif.
  - Etant donne les onze paquets du catalogue - les dix jeux jouables et `cm1-grands-nombres` en
    cours de verification (`DEC-0031`) -, quand on calcule pour chacun la proportion de manches
    gagnees en pressant systematiquement la proposition la plus courte, alors un audit ecrit liste ce
    taux paquet par paquet, pour que la reserve de "Les mesures" ne reste pas un cas isole non
    recherche ailleurs dans le catalogue.
  - Ce qui n'est pas testable par un automate et releve de la seule porte pedagogique de
    `nadia-prof` : qu'elle rejoue le paquet corrige et confirme, en jouant, que la longueur du texte
    n'est plus un raccourci exploitable, et que l'exactitude de conversion deja acquise n'est pas
    degradee par la correction.
- **Lien decision** : `DEC-0031-retrait-les-grands-nombres-du-catalogue-debordement-de-rendu.md`,
  amendement du 2026-08-16, section 6 ; motif source :
  `squad/conformite/ARBITRAGE-PREALABLE-s13-voie-de-rendu-des-propositions.md`, sections 6 et 7.
- **Agent pressenti** : a affecter au planning par `sofia-sm` (avis de faisabilite de `theo-archi`
  requis sur le garde-fou generique, comme pour toute regle neuve). **Candidate au sprint 14** : le
  sprint 13 ne la porte pas.

## Sprint 10 - La machine est redessinee sur les references de l'utilisatrice, le veto sur la manette est leve, l'ecran intermediaire reste a arbitrer

**Contexte et decision de perimetre** : `DEC-0022` avait acte un manche dessine sur le fronton,
valide par `nadia-prof` en arbitrage prealable. L'utilisatrice a vu ce rendu dans l'application
reelle et l'a refuse - a cette taille, la forme se lit comme un petit rectangle blanc arrondi ou,
agrandie, comme une arche, jamais comme une poignee, constat que `nadia-prof` avait deja ecrit
elle-meme sans en faire un motif de refus. Elle a fourni deux images de reference (un meuble
d'arcade illustre, versions CP et CM1) et pris six decisions neuves, actees par
`DEC-0023-perimetre-sprint-10-repris-sur-references-utilisatrice-veto-manette-leve-ecran-
intermediaire-questionne.md`, qui remplace partiellement `DEC-0022` : le point 2 de `DEC-0022`
(manche immobile, jamais actionne) tient et change de place, du fronton au flanc ; les points 1 et
3 (reessayer la meme question, message d'erreur sous la machine) tombent, le point 6 ci-dessous
les rouvrant tous les deux a la fois.

**Le fait qui commande le budget de pixels de ce sprint** : la reserve de pli, tracee sous
`DEC-0016` et remesuree par `nadia-prof` le 2026-08-12, reste serree : le bas du cadre passe sous
le pli sur **5 etats sur 29** de "Le bon petit mot" et **16 sur 34** de "Le bon verbe". Ce n'est
pas une reserve nommee de `nadia-prof` prise isolement, c'est un constat de mesure trace sous
`DEC-0016` - correction portee ici apres l'avoir propage a tort comme telle. Aucune des quatre
stories de developpement de ce sprint n'aggrave ce constat : chacune est budgetee a 0 px de
hauteur et de largeur ajoutes au cabinet rendu.

Le perimetre technique tient dans un seul fichier de production,
`adapters/adapter-web/src/main/resources/static/css/recreo.css` ; `fragments/manche.html` n'est
pas modifie par les stories S10-1 a S10-4. Chacune d'elles modifie un ecran deja accorde par
`nadia-prof` et suit donc la regle de `squad/README.md` section 10 : proposition de forme
d'`ines-design`, puis arbitrage prealable de `nadia-prof` avant toute ligne de code.

### S10-1 - Un levier a boule, dessine et immobile, sur le flanc du meuble

- **Besoin** : en tant qu'enfant qui joue au bandit manchot, je veux voir un levier a boule dessine
  sur le cote de la machine, afin de mieux reconnaitre que je suis devant une vraie machine
  d'arcade, sans qu'il bouge ni reagisse a ce que je fais.
- **Competence programme visee** : transverse - habillage de la mecanique, aucun contenu
  pedagogique (`ADR-0004`).
- **Criteres d'acceptation** :
  - Etant donne le levier, quand on relit la feuille de style, alors il est declare sur un
    pseudo-element decoratif porte par une classe du flanc du cabinet, en `position: absolute`,
    donc hors du flux du document.
  - Etant donne la hauteur et la largeur totales que le cabinet ajoute a l'ecran de manche, quand
    on les remesure apres cette story par `outils/mesurer-rendu.sh` sur les quatre jeux du
    catalogue, alors elles sont strictement identiques a celles mesurees avant cette story : le
    levier ajoute 0 px de hauteur et 0 px de largeur au document rendu. *Motif* : la reserve de
    pli tracee sous `DEC-0016` (5 etats sur 29 et 16 sur 34 deja sous le pli) ne tolere aucun ajout.
  - Etant donne le levier, quand on relit la feuille de style, alors aucune regle ne lui declare
    `animation`, `transition`, ni de pseudo-classe `:hover`, `:focus` ou `:active` propre (condition
    C3 de `nadia-prof`, "la machine attend, elle ne decide pas",
    `FICHE-bandit-manchot-additions-cp.md`, arbitrage du 2026-08-11).
  - Etant donne le document servi, quand on relit son balisage, alors le levier n'est porte par
    aucun `<button>` ni par aucun element portant `data-role` : il n'entre pas dans la derivation
    `button, a[href]` d'`ADR-0017`, et le bouton qui fait avancer le jeu reste `.demarrer` ou
    `.encore`, inchange.
  - Etant donne la geometrie du levier (fut et boule), quand on la mesure sur une capture a 360 px,
    alors elle est dessinee en position de repos, verticale, sans inclinaison ni troncature qui
    suggererait un mouvement en cours ou a mi-course.
  - Ce que cette story ne tranche pas : la forme exacte du fut et de la boule (`ines-design`
    propose), et le jugement sur la silhouette, qui appartient a `nadia-prof` en arbitrage
    prealable avant toute ligne de code (`squad/README.md` section 10).
  - Ce qui n'est pas testable par un automate et releve de la seule porte pedagogique de
    `nadia-prof` : que la silhouette soit reconnue comme un levier sans qu'un enfant cherche a la
    tirer en s'attendant a un effet - risque residuel nomme par `DEC-0023` et non tranche par la
    levee de son veto sur la manette.
  - **Correctif route par `theo-archi` (avis de faisabilite du sprint 10)** : le levier ajoute le
    premier selecteur de decor du sprint a `CabinetDeLaMachineTest`, et
    `SELECTEURS_DU_CABINET` y est aujourd'hui une liste enumeree a la main - le visage, l'enseigne
    et les etoiles du socle des stories suivantes en ajouteraient neuf de plus sans qu'aucun rouge
    ne le signale si elle reste enumeree. Etant donne cette story, quand on relit
    `CabinetDeLaMachineTest`, alors `SELECTEURS_DU_CABINET` n'est plus une liste ecrite a la main
    mais **derivee** de la feuille maison : toute regle dont le selecteur nomme une classe
    commencant par `cabinet` y entre automatiquement, le nouveau selecteur du levier compris.
- **Lien decision** :
  `DEC-0023-perimetre-sprint-10-repris-sur-references-utilisatrice-veto-manette-leve-ecran-
  intermediaire-questionne.md`, point 3 (levee nommee du veto de `nadia-prof` sur la manette,
  arbitrages du 2026-08-10 et du 2026-08-11).
- **Agent pressenti** : a affecter au planning par `sofia-sm` (proposition de forme d'`ines-design`
  requise, arbitrage prealable de `nadia-prof` avant developpement).

### S10-2 - L'habillage du meuble est redessine en CSS pur, a partir des references de l'utilisatrice

- **Besoin** : en tant qu'enfant qui joue au bandit manchot, je veux que la machine ressemble a un
  vrai meuble d'arcade colore, avec du relief et des decorations sur les cotes, afin de mieux la
  reconnaitre comme un objet plutot que comme un aplat de couleur.
- **Competence programme visee** : transverse - habillage de la mecanique, aucun contenu
  pedagogique (`ADR-0004`).
- **Criteres d'acceptation** :
  - Etant donne le depot a l'issue de cette story, quand on cherche une reference a un fichier
    image ajoute par elle, alors il n'y en a aucune : `RessourcesTiercesTest` reste vert sans
    modification, et le garde-fou qui interdit toute ressource tierce n'est pas amende.
  - Etant donne le corps du cabinet, quand on le relit dans la feuille de style, alors il est
    rendu en deux tons distincts, porte une ombre externe a decalage vertical seul (aucun
    decalage horizontal), et ses coins sont arrondis (`border-radius` non nul).
  - Etant donne les flancs du cabinet, quand on les relit, alors ils portent des decorations
    (etoiles, arcs-en-ciel) declarees par des pseudo-elements ou des degrades, fixes : elles ne
    dependent ni de l'item tire, ni du numero de la colonne, ni de la reponse attendue (condition
    C5 de `nadia-prof`, deuxieme clause).
  - Etant donne la zone sous les rouleaux, quand on la relit, alors une rangee de boutons colores
    y est dessinee en decoration (pseudo-elements ou aplats), sans qu'aucun de ces boutons dessines
    ne porte `data-role` ni ne soit un `<button>` : ce sont des motifs, pas des controles.
  - Etant donne la hauteur et la largeur totales que le cabinet ajoute a l'ecran de manche, quand
    on les remesure apres cette story sur les quatre jeux du catalogue, alors elles restent
    strictement identiques a celles mesurees avant cette story : aucun des ajouts (tons, ombre,
    coins, decorations de flanc, rangee de boutons) ne coute un pixel de hauteur ni de largeur.
  - Etant donne le contour ferme du cabinet deja valide par `nadia-prof` (epaisseur d'au moins
    8 px sur chacun des quatre cotes), quand on le remesure apres cette story, alors il n'est pas
    reduit.
  - Ce que cette story ne tranche pas : le rendu exact (couleurs, formes des etoiles et des arcs-
    en-ciel, valeur d'ombre) - `ines-design` propose, `nadia-prof` juge en arbitrage prealable
    avant toute ligne de code.
  - Ce qui n'est pas testable par un automate et releve de la seule porte pedagogique de
    `nadia-prof` : que cet habillage renforce effectivement la lecture "meuble d'arcade" pour un
    enfant de CP ou de CM1, sans distraire de la lecture des propositions.
- **Lien decision** :
  `DEC-0023-perimetre-sprint-10-repris-sur-references-utilisatrice-veto-manette-leve-ecran-
  intermediaire-questionne.md`, point 1.
- **Agent pressenti** : a affecter au planning par `sofia-sm` (proposition de forme d'`ines-design`
  requise, arbitrage prealable de `nadia-prof` avant developpement).

### S10-3 - Une enseigne nommee et un visage souriant sur le fronton

- **Besoin** : en tant qu'enfant qui joue au bandit manchot, je veux voir un nom et un visage
  souriant sur le fronton de la machine, afin qu'elle ressemble a un vrai jeu d'arcade avec son
  propre nom, plutot qu'a un meuble anonyme.
- **Competence programme visee** : transverse pour le visage (habillage, `ADR-0004`). Le nom de
  l'enseigne est un texte destine a l'enfant et suit a ce titre la porte pedagogique ordinaire de
  tout contenu enfant (francais correct et integralement accentue, aucun mot de la liste interdite
  ci-dessous) - `nadia-prof` le valide comme n'importe quel texte, cette story ne le decide pas.
- **Criteres d'acceptation** :
  - Etant donne le nom retenu pour l'enseigne, quand on le relit, alors il ne contient aucun des
    mots suivants, sous aucune forme fléchie : `bandit`, `manchot`, `machine a sous`, `jackpot`,
    `mise`, `piece`, `jeton`, `credit`, `gain`, `levier`, `manette`, `fente`, `coupelle`, `pari`,
    `tirage`.
  - Etant donne ce meme nom, quand le `relecteur-pedagogique` l'audite, alors il est en francais
    correct, integralement accentue, sans faute d'orthographe ni de ponctuation.
  - Etant donne le visage souriant, quand on capture l'ecran de retour apres une bonne reponse et
    apres une mauvaise reponse sur le meme item, alors les pixels du visage sont identiques sur
    les deux captures (meme construction que la condition C4 de `nadia-prof`, "la machine ne dit
    jamais le resultat") : le visage ne devient jamais un second canal de verdict.
  - Etant donne la hauteur et la largeur totales que le cabinet ajoute a l'ecran de manche, quand
    on les remesure apres cette story, alors elles restent strictement identiques a celles
    mesurees avant cette story.
  - Ce que cette story ne tranche pas : le nom retenu (trois candidats proposes par `camille-po`
    dans `DEC-0023` - "Le Copain Malin", "Tourne-Cervelle", "La Boite a Bonnes Reponses" -,
    `nadia-prof` choisit ou en propose un autre) et le dessin exact du visage (`ines-design`
    propose).
  - Ce qui n'est pas testable par un automate et releve de la seule porte pedagogique de
    `nadia-prof` : que le nom retenu soit comprehensible et approprie pour un enfant de CP comme
    de CM1, et que le visage soit lu comme un visage plutot que comme un decor abstrait.
- **Lien decision** :
  `DEC-0023-perimetre-sprint-10-repris-sur-references-utilisatrice-veto-manette-leve-ecran-
  intermediaire-questionne.md`, points 4 et 5.
- **Agent pressenti** : a affecter au planning par `sofia-sm` (proposition de forme d'`ines-design`
  requise, validation du nom et arbitrage prealable de `nadia-prof` avant developpement).

### S10-4 - Un garde-fou neuf protege l'immobilite de tout l'habillage ajoute ce sprint

- **Besoin** : en tant qu'enfant qui joue au bandit manchot aujourd'hui et dans les mois qui
  suivent, je veux que la machine (levier, boutons dessines, decorations, visage) reste un meuble
  immobile chaque fois que j'y reviens, meme si l'application a change entretemps, afin qu'elle ne
  se mette jamais a agir toute seule sans que quelqu'un l'ait decide et fait valider par ma
  maitresse.
- **Competence programme visee** : transverse - garde-fou de non-regression sur un acquis
  pedagogique deja accorde (condition C3 de `nadia-prof`), aucun contenu pedagogique nouveau.
- **Criteres d'acceptation** :
  - Etant donne la feuille de style servie a l'issue de S10-1 a S10-3, quand le garde-fou neuf
    s'execute, alors il fait echouer toute regle maison dont le sujet nomme une classe d'habillage
    (le cabinet, son bandeau, son socle, le levier de S10-1, les decorations et les boutons
    dessines de S10-2, le visage et l'enseigne de S10-3) et qui declare `transition` ou `animation`,
    ou qui est conditionnee par une pseudo-classe interactive (`:hover`, `:active`, `:focus`,
    `:focus-visible`, `:focus-within`, `:target`) - meme regle qu'`ADR-0033`, dont le perimetre est
    etendu ici aux elements neufs de ce sprint.
  - Etant donne une mutation qui ajoute une telle regle sur un de ces elements neufs, quand
    `mvn -B verify` s'execute, alors ce garde-fou echoue, avec un message qui nomme la classe et la
    propriete fautives.
  - Etant donne les regles existantes sur les elements pressables (`.btn`, `.proposition`) et sur
    leur pseudo-element decoratif (le voile de mouvement des colonnes), quand ce garde-fou
    s'execute, alors il ne les fait pas echouer : seule une classe d'habillage est visee, jamais un
    element pressable ni son pseudo-element decoratif.
  - Etant donne le compte de regles ArchUnit et le compte de cas de test du depot avant cette
    story, quand cette story est livree, alors le premier est inchange (ni le CSS ni le gabarit ne
    sont visibles au bytecode) et le second augmente du nombre de cas neufs, mesure et non estime.
  - Ce que cette story ne tranche pas : si ce garde-fou etend `ADR-0033` (deja ecrit par
    `theo-archi` pour le manche du fronton, non encore fusionne sur `round-two`) ou en cree un
    distinct pour le perimetre neuf de ce sprint - decision de `theo-archi`.
  - Ce qui n'est pas testable par un automate et releve de la seule porte pedagogique de
    `nadia-prof` : que le cabinet et tout son habillage restent effectivement percus comme
    immobiles sur le rendu reel, en jouant (condition C3).
- **Lien decision** :
  `DEC-0023-perimetre-sprint-10-repris-sur-references-utilisatrice-veto-manette-leve-ecran-
  intermediaire-questionne.md` ; `ADR-0033-un-habillage-ne-change-pas-d-etat-sous-le-doigt-de-l-
  enfant.md`, ecrit par `theo-archi` pour le meme motif sur le perimetre du manche seul.
- **Agent pressenti** : a affecter au planning par `sofia-sm` (avis de faisabilite et ecriture ou
  extension de la regle par `theo-archi`).

### S10-5 - Le contour du cabinet se distingue nettement des colonnes (reserve 2 de nadia-prof)

- **Besoin** : en tant qu'enfant qui distingue mal les couleurs, je veux que le contour de la
  machine se voie clairement, different des cases de reponse a l'interieur, afin de reconnaitre la
  fenetre de la machine et pas seulement un aplat bleu uniforme.
- **Competence programme visee** : transverse - lisibilite et accessibilite visuelle, aucun contenu
  pedagogique (meme categorie que S0-5, S1-9, S2-1, S7-3, S8-3). Cette story ne fait bouger aucune
  ligne de `squad/programme/MATRICE-couverture.md`.
- **Criteres d'acceptation**, repris de la reserve 2 de `nadia-prof`
  (`FICHE-bandit-manchot-additions-cp.md`, arbitrage du 2026-08-11, section 8) :
  - Etant donne la couleur du cadre du cabinet et celle des colonnes, quand on calcule le rapport de
    contraste entre les deux sur leurs luminances relatives, alors il est d'au moins 3,20:1 (contre
    1,71:1 mesure par `nadia-prof` le 2026-08-11 entre `#1d4b6b` et `#2e6f9e`).
  - Etant donne la nouvelle variable `--recreo-cabinet-fond` introduite par cette story, quand on
    relit son usage dans la feuille de style, alors elle est distincte de `--recreo-entete-fond` et
    ne la remplace pas : aucune regle qui utilise `--recreo-entete-fond` n'est modifiee par cette
    story, pour ne pas rouvrir la contrainte du crayon de `favicon.ico`.
  - Etant donne la geometrie des colonnes mesuree par `nadia-prof` le 2026-08-11 (largeur de 96 px
    ou 152 px selon le jeu, `gap` de 16 px sur les deux axes, hauteur de 74 px, rembourrage lateral
    de 20 px), quand on la remesure apres cette story sur les quatre jeux du catalogue, alors elle
    est strictement identique : cette story ne change qu'une couleur.
  - Etant donne le contraste du cabinet contre la page (`#f4f7fb`), mesure par `nadia-prof` a
    **8,61:1** (chiffre corrige - la valeur `9,25:1` publiee au sprint 8 mesurait le contraste
    contre le blanc pur `--recreo-accent-texte` et non contre le fond de page), quand on le
    remesure apres cette story, alors il ne diminue pas.
  - Ce qui n'est pas testable par un automate et releve de la seule porte pedagogique de
    `nadia-prof` : que la nouvelle couleur du cadre reste percue comme une machine par un enfant, et
    que le contraste corrige rende effectivement la "fenetre" du cabinet lisible pour un enfant qui
    distingue mal les couleurs.
- **Lien decision** :
  `DEC-0023-perimetre-sprint-10-repris-sur-references-utilisatrice-veto-manette-leve-ecran-
  intermediaire-questionne.md` (reconduction, independante des six points de cette decision) ;
  `FICHE-bandit-manchot-additions-cp.md`, arbitrage du 2026-08-11, section 8, reserve 2.
- **Agent pressenti** : a affecter au planning par `sofia-sm` (proposition de forme d'`ines-design`
  requise pour la valeur de couleur retenue).

### En attente d'arbitrage prealable - le point 6 de DEC-0023 : l'ecran intermediaire disparait

**Aucune story de developpement n'est ouverte sur ce point.** Bonne reponse : la question suivante
se chargerait directement. Mauvaise reponse : l'enfant resterait sur l'ecran de la machine, et un
message s'afficherait sous le meuble. Ce changement modifierait un ecran deja accorde par
`nadia-prof` depuis le sprint 1 (`fragments/retour.html`) et contredit nommement, en l'etat, sa
condition 3 du 2026-08-10 ("le chantier n'ajoute aucun second canal de verdict") et sa condition
C4 du 2026-08-11 ("la machine ne dit jamais le resultat"). `squad/README.md` section 10 impose un
arbitrage prealable avant toute ligne de code sur un ecran deja accorde ; il n'a pas eu lieu.

Trois questions sont routees a `nadia-prof` par `sofia-sm`, ecrites dans `DEC-0023` :

1. Que dit le message sous le meuble sur une mauvaise reponse - revele-t-il la bonne reponse
   immediatement, jamais, ou apres un certain nombre d'essais, et selon quelle articulation avec
   le seuil de trois echecs consecutifs qui declenche deja un indice (`DEC-0004`, S1-5) ?
2. L'explication, affichee aujourd'hui systematiquement juste ou faux (arbitrage du 2026-08-06 de
   `nadia-prof`), survit-elle a la disparition de l'ecran de retour pour une bonne reponse, et sur
   quel ecran s'affiche-t-elle alors ?
3. Si l'enfant reste sur la meme question apres une mauvaise reponse, la proposition fautive
   devient-elle inactive - ce qu'`ADR-0026` interdit aujourd'hui par ses clauses 3 et 4 - ou reste-
   t-elle cliquable ?

Une fois ces trois questions arbitrees, cette section devient une ou plusieurs stories numerotees,
avec des criteres d'acceptation testables repris au mot pres de l'arbitrage de `nadia-prof`, sur le
meme modele que les stories correctives du sprint 1.

- **Reserve nouvelle du 2026-08-12, arbitrage prealable du sprint 10**
  (`squad/conformite/FICHE-bandit-manchot-additions-cp.md`, verdict du 2026-08-12, section 6) : la
  forme rendue du manche se lit comme une arche, pas comme une poignee. `nadia-prof` precise
  explicitement que ce n'est pas un motif de refus - sa frontiere porte sur le geste que la
  silhouette suggere (traction), pas sur sa lecture comme forme - mais que l'utilisatrice a demande
  un manche dessine et doit pouvoir contester cette lecture. Routee a `sofia-sm` pour remontee ;
  n'attend aucune reponse pour que le sprint continue.

## Dette ouverte par DEC-0025 - le pli du bandit manchot, sprint 10 livre avec un defaut assume

`nadia-prof` refuse l'increment du sprint 10 sur deux conditions bloquantes : M4 (message, indice
et revelation au-dessus du pli a 360 x 640, non tenue sur six jeux sur sept) et M13 clause 1 (une
panne technique, hors de cette section). L'utilisatrice a joue l'increment et decide de le livrer,
en assumant le defaut de M4 par ecrit -
`DEC-0025-levee-de-la-condition-m4-le-sprint-10-est-livre-avec-un-defaut-de-pli-assume.md`. Les
trois stories suivantes portent la dette que cette decision ouvre.

### Story de correction - Les enonces et indices des six jeux en defaut de pli sont raccourcis (M4, bloquante, DEC-0025)

**Non affectee a un sprint.**

- **Besoin** : en tant qu'eleve de CP ou de CM1 qui vient de repondre, je veux voir le message qui
  me dit ce qui s'est passe, l'indice et la reponse revelee sans avoir a faire defiler l'ecran,
  afin de savoir tout de suite que ma reponse a compte et ce que je dois faire ensuite.
- **Competence programme visee** : transverse - lisibilite et accessibilite de l'ecran de jeu,
  aucune ligne de `squad/programme/MATRICE-couverture.md` ne bouge : les six paquets vises
  (`cp-comparer-nombres`, `cp-complements-10`, `cp-le-bon-son`, `cp-soustractions`,
  `cp-problemes-additifs`, `cm1-conjugaison-present`) restent ceux deja juges exacts par
  `nadia-prof`, seule leur longueur de texte change.
- **Criteres d'acceptation**, repris de la condition M4 de `nadia-prof` et des mesures qui
  l'accompagnent (`squad/conformite/FICHE-bandit-manchot-additions-cp.md`, verdict du 2026-08-13 ;
  `mesure-ines-hauteur-de-pli.md` et `mesure-ines-plancher-indice-explication.md`, branches
  `s10-ines-hauteur` et `s10-ines-pli`) :
  - Etant donne l'item le plus charge de chacun des six paquets (enonce et indice les plus longs du
    paquet reellement servi), quand on mesure `getBoundingClientRect().bottom` du dernier enfant
    visible de `.message-jeu` a 360 x 640, alors cette valeur est **inferieure ou egale a 640 px**,
    sur les quatre etats (1er/2e echec, revelation, reussite).
  - Etant donne ce meme item, quand on relit son enonce et son indice apres cette story, alors la
    manipulation, le contenu arithmetique ou grammatical, et l'exactitude de la reponse restent
    ceux deja juges exacts par `nadia-prof` : seule la longueur du texte change, jamais son sens.
  - Etant donne les items d'un meme paquet qui ne sont pas les plus charges, quand on les remesure
    apres cette story, alors leur position ne se degrade pas par rapport a l'etat mesure avant
    cette story.
  - Etant donne les indices de `cm1-conjugaison-present` deja vises par la story de correction M2
    ci-dessous, quand les deux stories sont livrees, alors les deux corrections (raccourcir,
    cesser de nommer la reponse) sont compatibles sur les memes textes - une seule reecriture,
    pas deux passes contradictoires.
  - Ce que cette story ne tranche pas : quels enonces ou indices raccourcir et comment formuler la
    version raccourcie (jugement pedagogique, `nadia-prof`) ; si un repli de mise en page
    supplementaire (colonnes, marges) complete le raccourcissement plutot que de le remplacer
    (`theo-archi`, `ines-design`).
  - Ce qui n'est pas testable par un automate et releve de la seule porte pedagogique de
    `nadia-prof` : que la version raccourcie reste comprehensible et pedagogiquement equivalente
    pour l'enfant vise. Elle rejoue l'increment avant tout Done, comme toujours.
- **Lien decision** :
  `DEC-0025-levee-de-la-condition-m4-le-sprint-10-est-livre-avec-un-defaut-de-pli-assume.md`.
- **Agent pressenti** : a affecter au planning par `sofia-sm`, avec `nadia-prof` pour le
  raccourcissement lui-meme.

### Priorite relevee - la story de correction des indices de "Le bon verbe" (M2) devient ce qui rend ce jeu enseignable

La story "Les indices de 'Le bon verbe' nomment la manipulation, pas la reponse (M2, arbitrage de
mecanique de nadia-prof)", deja au backlog ci-dessus, non affectee a un sprint, voit sa priorite
relevee par `DEC-0025`. `nadia-prof` l'ecrit elle-meme dans sa reserve S du 2026-08-13
(`squad/conformite/FICHE-bandit-manchot-additions-cp.md`, arbitrage prealable de l'ampoule,
branche `s10-nadia-ampoule`) : tant que ces 25 indices ne sont pas reecrits, "Le bon verbe" ne peut
recevoir l'ampoule (M21) et ne porte par ailleurs aucune explication de revelation - un enfant qui
rate trois fois y recoit donc la reponse et rien d'autre. Ses mots : *"la reserve A - reecrire les
25 indices de `cm1-conjugaison-present` - cesse d'etre une amelioration et devient ce qui rend ce
jeu enseignable. Priorite a `camille-po`."* Je la reprends telle quelle : cette story n'est pas
recrite ici, seule sa priorite change.

### Story - Une aide appelee par l'enfant (une ampoule), sous les conditions M16 a M21 de nadia-prof - reportee au sprint 11

**Reportee au sprint 11, non livree ce sprint.**

- **Besoin** : en tant qu'eleve qui vient de me tromper, je veux pouvoir demander de l'aide
  moi-meme en appuyant sur une ampoule qui se marque quand je suis bloque, afin de recevoir un
  signal des mon premier echec sans que l'indice ne pousse hors de l'ecran le message qui me dit
  que je me suis trompe.
- **Competence programme visee** : transverse - mecanique du jeu, aucun contenu pedagogique
  nouveau (`ADR-0004`). S'applique aux jeux du catalogue qui portent un indice utilisable ; "Le bon
  verbe" en est exclu par M21 tant que ses 25 indices nomment la reponse (voir story ci-dessus).
- **Criteres d'acceptation**, repris au mot pres des conditions M16 a M21 de `nadia-prof`
  (`squad/conformite/FICHE-bandit-manchot-additions-cp.md`, arbitrage prealable du 2026-08-13,
  branche `s10-nadia-ampoule`) :
  - **M16** : l'ampoule est presente et pressable des le premier rendu de chaque question, avant
    tout essai ; des le premier echec compte sur la question, elle porte une marque non textuelle
    qu'elle ne portait pas avant, au meme endroit, sans deplacer un seul pixel de la page, et cette
    marque disparait avec la question.
  - **M17 - clause bloquante** : un appui sur l'ampoule affiche l'indice entierement au-dessus du
    pli (`y = 640`, 360 px de large), sans jamais recouvrir les propositions ; sur l'ecran de
    revelation, l'indice s'affiche de lui-meme, sans etre demande.
  - **M18** : l'ampoule ne se paie pas avec les pixels du message - le bas du cabinet ne descend
    sur aucun jeu par rapport a son rendu actuel.
  - **M19** : le mot visible porte par l'ampoule est "Aide" (jamais "Indice" ni un mot seul en
    anglais, toute variante revient devant `nadia-prof`) ; un pictogramme peut l'accompagner,
    jamais le remplacer, et il est `aria-hidden` ; le texte est au moins a 1,25 rem ; la cible
    pressable fait au moins 44 x 44 px.
  - **M20** : l'ampoule est hors du contour du cabinet ; l'ouvrir ne compte pas comme un essai, ne
    fait pas avancer la question, ne touche pas au score et ne change pas le message affiche ;
    l'ouvrir ne deplace aucune proposition ; une fois ouvert, l'indice reste ouvert pour les essais
    restants de la question et disparait avec elle ; l'ampoule ne recoit pas le focus apres une
    reponse, et un appui sur Tabulation puis Entree ne detruit jamais la partie (meme defaut que
    M13 clause 1 - cette clause ne peut pas etre tenue tant que M13 clause 1 reste non corrigee).
  - **M21** : sur un paquet dont les indices nomment la bonne reponse, l'ampoule n'est pas rendue
    du tout - ni grisee, ni inerte.
  - Ce que cette story ne tranche pas : la forme de l'ampoule (`ines-design` propose,
    `nadia-prof` arbitre, deja fait dans son principe) ; la route technique - ou vit l'etat
    "ouvert", par quel echange il change (`theo-archi`).
  - Ce qui n'est pas testable par un automate et releve de la seule porte pedagogique de
    `nadia-prof` : qu'une aide appelee montre effectivement quelque chose a l'enfant qui l'appelle
    (M17 clause 1) et qu'une ampoule se marque quand l'enfant est bloque (M16 clause 2) - elle les
    verifie en jouant. Et sa **reserve R**, non tranchee : un enfant de six ans en difficulte
    appelle-t-il reellement une aide qu'il doit demander ? Elle demande une observation d'un enfant
    du niveau vise une fois l'ampoule livree ; si l'observation dit le contraire de son jugement de
    metier, elle allegera M16 clause 2 elle-meme, comme une correction de son constat.
- **Lien decision** :
  `DEC-0025-levee-de-la-condition-m4-le-sprint-10-est-livre-avec-un-defaut-de-pli-assume.md` ;
  `squad/conformite/FICHE-bandit-manchot-additions-cp.md`, arbitrage prealable du 2026-08-13.
- **Agent pressenti** : a affecter au planning par `sofia-sm`, pour le sprint 11 - proposition de
  forme d'`ines-design` requise (l'arbitrage de principe de `nadia-prof` est deja rendu, sous les
  six conditions ci-dessus).

## Dette ouverte par DEC-0026 - une perte de partie sans confirmation, sur l'ecran de revelation et sur l'ecran de reprise

`nadia-prof` refuse le lot de cloture du sprint 10 sur un seul motif bloquant : sur l'ecran de
revelation de M5, quand la proposition qui porte la reponse revelee occupe la derniere position de
l'ordre de tabulation, la premiere tabulation depuis elle atteint "Les jeux" et Entree detruit la
partie sans confirmation - 10 destructions sur 25 revelations mesurees, sur les sept jeux.
L'utilisatrice a joue l'increment et decide de le livrer, en assumant le defaut par ecrit -
`DEC-0026-levee-m13-clause-1-ecran-de-revelation-perte-de-partie-sans-confirmation.md`. **Amendement
du 2026-08-14** : `theo-archi` (`ADR-0038`) a mesure que le meme premier arret de tabulation
destructeur existe aussi sur l'ecran de reprise (`GET /` pendant une partie en cours, rien focalise
au chargement) ; c'est le meme defaut structurel que sur la revelation, pas un second defaut, et
l'amendement de `DEC-0026` etend la levee et son declencheur de reouverture aux deux ecrans. Les
deux stories suivantes portent donc la dette sur les deux ecrans ; `nadia-prof` designe la propriete
a tenir et refuse explicitement de choisir entre elles.

### Story bloquante - Depuis le point de focus, la premiere tabulation ne mene jamais a la sortie (M13 clause 1, ecran de revelation et ecran de reprise)

**Non affectee a un sprint au moment de cette decision ; `DEC-0026` exige qu'elle ou la story
suivante soit planifiee pour le sprint 11.**

**Livree en s11** (`marc-dev`, `687b4be` ; `lea-dev`, `4eaddf0`), couverte par `ADR-0038` et 416
tests. **Ce que cette livraison ne ferme pas** : la reverification au clavier reel de `nadia-prof`
sur l'increment tel qu'il est livre reste due - voir la section "Report du 2026-08-14 (s11,
cloture)" de `DEC-0026-levee-m13-clause-1-ecran-de-revelation-perte-de-partie-sans-confirmation.md`,
qui porte la tache identifiee pour le sprint 12.

- **Besoin** : en tant qu'eleve qui navigue au clavier ou avec une aide technique, que je vienne de
  faire reveler la reponse ou que je recharge la page en cours de partie, je veux que la touche
  suivante ne me fasse jamais quitter ma partie sans que je l'aie demande, afin de ne pas perdre ma
  partie et mon score par une exploration ordinaire du clavier.
- **Competence programme visee** : transverse - accessibilite clavier de l'ecran de jeu, aucune
  ligne de `squad/programme/MATRICE-couverture.md` ne bouge.
- **Criteres d'acceptation**, repris au mot pres de la clause 1 et de la clause 5 de M13, de la
  mesure du second verdict de `nadia-prof`
  (`squad/conformite/FICHE-bandit-manchot-additions-cp.md`, section "Second verdict du
  2026-08-13", point 1), et de la mesure de `theo-archi` sur l'ecran de reprise (`ADR-0038`) :
  - Etant donne un ecran de revelation ou la proposition qui porte la reponse revelee occupe la
    derniere position de l'ordre de tabulation, quand on presse Tabulation une fois puis Entree,
    alors la partie reste en cours (`ecran-de-jeu`) : elle n'est jamais detruite par cette
    sequence, sur aucun des sept jeux du catalogue.
  - Etant donne un chargement de `GET /` pendant une partie en cours (ecran de reprise), ou rien
    n'est focalise au depart, quand on presse Tabulation une fois puis Entree, alors la partie reste
    en cours : elle n'est jamais detruite par cette sequence.
  - Ces deux proprietes sont verifiees sur les 25 revelations du protocole de `nadia-prof` (une par
    jeu, plus les tirages repetes sur "Calcul CP" et "Le bon verbe") et sur l'ecran de reprise pour
    chacun des sept jeux, pas sur un echantillon reduit.
  - Le focus continue de se poser sur la proposition qui porte la reponse revelee (clause 5,
    inchangee) : cette story ne deplace pas ce point de focus, elle change seulement ce qui suit
    la premiere tabulation depuis lui, sur les deux ecrans.
  - Les quatorze cas du premier refus de `nadia-prof` (Tabulation puis Entree apres une bonne et
    une mauvaise reponse, hors ecran de revelation) restent verts apres cette story.
  - Ce que cette story ne tranche pas : le moyen technique (ordre du document, ordre de
    tabulation, place du bouton de sortie, ou la confirmation de la story suivante) - `theo-archi`,
    qui peut aussi choisir de livrer la story suivante a la place de celle-ci si elle couvre la
    meme propriete sur les deux ecrans.
  - Ce qui n'est pas testable par un automate et reste sous la garde de `nadia-prof` : la
    reverification au clavier reel, dans un vrai navigateur, sur les 25 revelations de son
    protocole et sur l'ecran de reprise, avant que cette dette soit consideree fermee (declencheur
    complete par l'amendement du 2026-08-14 a `DEC-0026`).
- **Lien decision** :
  `DEC-0026-levee-m13-clause-1-ecran-de-revelation-perte-de-partie-sans-confirmation.md`, y compris
  son amendement du 2026-08-14 ; `ADR-0038`.
- **Agent pressenti** : a affecter au planning par `sofia-sm`, pour le sprint 11.

### Story - Confirmation avant d'abandonner une partie en cours, sur tout chemin de sortie

**Non affectee a un sprint au moment de cette decision ; `DEC-0026` exige qu'elle ou la story
precedente soit planifiee pour le sprint 11.**

**Livree en s11** (`marc-dev`, `687b4be` ; forme et texte corriges par `lea-dev`, `4eaddf0`). **Ce
que cette livraison ne ferme pas** : meme renvoi que la story precedente - voir la section
"Report du 2026-08-14 (s11, cloture)" de
`DEC-0026-levee-m13-clause-1-ecran-de-revelation-perte-de-partie-sans-confirmation.md`.

- **Besoin** : en tant qu'eleve qui presse "Les jeux" ou recharge la page pendant une partie, que
  ce soit au clavier, a la souris ou par erreur, je veux qu'on me demande si je veux vraiment
  quitter avant que ma partie ne disparaisse, afin de ne jamais la perdre par un geste que je
  n'avais pas mesure.
- **Competence programme visee** : transverse - aucune ligne de
  `squad/programme/MATRICE-couverture.md` ne bouge.
- **Criteres d'acceptation** :
  - Etant donne une partie en cours, quand on declenche `POST /retour` (bouton "Les jeux") ou
    `GET /` par n'importe quel chemin (clavier, souris, rechargement, navigation arriere), alors la
    partie n'est pas immediatement detruite : une confirmation est demandee avant que
    `abandonner()` ne retire les attributs de session du jeu, de la consigne et de la partie.
  - Etant donne cette confirmation, quand l'enfant la refuse ou l'ignore, alors la partie reste en
    cours, inchangee.
  - Etant donne cette confirmation, quand l'enfant l'accepte, alors la partie est abandonnee comme
    aujourd'hui, sans changement de ce comportement.
  - Si cette story est livree, la story precedente (tabulation sur l'ecran de revelation et sur
    l'ecran de reprise) est couverte dans le meme geste : la premiere tabulation depuis la
    revelation, comme celle depuis le chargement de l'ecran de reprise, peut toujours atteindre
    "Les jeux", mais Entree n'y detruit plus la partie sans confirmation.
  - La reserve O de `nadia-prof` est fermee dans le meme geste : *"il n'existe aucun chemin vers le
    catalogue qui ne detruise pas la partie"* cesse d'etre vrai.
  - Ce que cette story ne tranche pas : la forme de la confirmation (modale, ecran intermediaire,
    texte) - `ines-design` propose, `nadia-prof` juge le texte destine a l'enfant ; le moyen
    technique - `theo-archi`.
  - Ce qui n'est pas testable par un automate et reste sous la garde de `nadia-prof` : que le texte
    de la confirmation soit lisible et comprehensible par un enfant de CP, et qu'il ne l'inquiete
    pas a tort.
- **Lien decision** :
  `DEC-0026-levee-m13-clause-1-ecran-de-revelation-perte-de-partie-sans-confirmation.md`, y compris
  son amendement du 2026-08-14 ;
  `squad/conformite/FICHE-bandit-manchot-additions-cp.md`, reserve O.
- **Agent pressenti** : a affecter au planning par `sofia-sm`, pour le sprint 11.

### Reserves non bloquantes du second verdict de nadia-prof, datees du 2026-08-13

Trois reserves neuves, versees par `nadia-prof` a la suite de son second verdict
(`squad/conformite/FICHE-bandit-manchot-additions-cp.md`, section "Second verdict du
2026-08-13" ; `squad/conformite/FICHE-bandit-manchot-conjugaison-present-cm1.md`), non affectees a
un sprint :

- **La marque "deja essayee" n'est pas cumulative apres la revelation, sur "Le bon verbe" (M14
  clause 5)** : une proposition marquee perd sa marque au coup suivant. Constat non bloquant, visible
  sur ce seul jeu.
- **Le fondu de l'accent peint est du mauvais cote et produit l'arete qu'il devait eviter**, arete
  mesuree au pixel par `nadia-prof`. Story de forme, routee a `ines-design` et `theo-archi`.
- **"Le bon son" dit deux fois la meme chose sur son ecran de revelation** : l'explication et
  l'indice du meme item se recouvrent presque mot pour mot. C'est la seule des trois reserves avec
  un effet pedagogique direct - `nadia-prof` la nomme, jugement pedagogique de la reformulation qui
  reste le sien - et sa correction ferme aussi le defaut de pli de M4 sur ce jeu dans le meme geste
  (voir l'amendement du 2026-08-14 de `DEC-0025`).

## Gain vertical - retrait du bouton de sortie du fragment de manche (DEC-0027)

Demande de l'utilisatrice, s11 : gagner de la place verticale sur l'ecran de manche en ne gardant
qu'une seule sortie, celle de l'en-tete, plutot que les deux sorties actuelles (en-tete masque en
jeu, bouton du fragment visible). Voir `DEC-0027-retrait-bouton-sortie-fragment-manche-reouvre-s6-9-
arbitrage-prealable-requis.md`, y compris son amendement du 2026-08-14 : cette forme est celle que
`nadia-prof` avait refusee lors de l'arbitrage prealable de S6-9 (2026-08-07), avant de valider la
forme inverse comme Done pedagogique le 2026-08-10. **L'arbitrage prealable exige par le point 2 de
`DEC-0027` est desormais rendu** (`squad/conformite/FICHE-bandit-manchot-additions-cp.md`, section
"Arbitrage prealable au developpement - la sortie unique dans l'en-tete (DEC-0027, s11), du
2026-08-14") : **le principe est accepte sur les seuls etats rendus par `fragments/manche.html`,
sous six conditions PORTE-1 a PORTE-6 ; la forme instruite (masquage leve sur tout ecran portant
`ecran-de-jeu`) est refusee.**

### Story - Une seule sortie visible en jeu, portee par l'en-tete, sur les seuls etats de fragments/manche.html

**Non affectee a un sprint. Ne peut pas etre planifiee par `sofia-sm` avant que la story
"Confirmation avant d'abandonner une partie en cours" (en developpement par `marc-dev`, s11) soit
livree - PORTE-6.**

- **Besoin** : en tant qu'eleve de CP ou de CM1 en train de jouer une manche, je veux que la zone
  ou je lis la question et les propositions ne soit pas allongee par un bouton de sortie qui existe
  deja ailleurs sur l'ecran, afin de voir plus de la question, du message et de l'indice sans avoir
  a faire defiler la page.
- **Competence programme visee** : transverse - ergonomie et espace d'ecran, pas de contenu
  pedagogique.
- **Perimetre, corrige par l'amendement du 2026-08-14 a `DEC-0027`** : le bouton retire,
  `button.btn.retour-accueil`, n'existe que dans `fragments/manche.html` (manche fraiche, echec et
  revelation). Mais le mecanisme de masquage leve (`body:has(.ecran-de-jeu) .lien-accueil`)
  s'applique a tout ecran portant la classe `ecran-de-jeu`, et `fragments/retour.html` (fin de
  partie) comme `fragments/erreur.html` (erreur) la portent aussi. **Le changement de visibilite de
  l'en-tete ne doit donc atteindre que les etats de `fragments/manche.html`** : l'ecran de fin de
  partie et l'ecran d'erreur restent inchanges, sans lien d'en-tete visible (PORTE-2, PORTE-3).
- **Criteres d'acceptation**, portant les six conditions de `nadia-prof` telles qu'elle les a
  ecrites (`squad/conformite/FICHE-bandit-manchot-additions-cp.md`, section citee ci-dessus,
  sous-section "5. Mes six conditions") :
  - **PORTE-1.** Etant donne l'accueil, quand l'enfant appuie sur la boite de sortie (239, 16)
    (`.lien-accueil`), alors l'ecran rendu n'est plus identique, a l'octet pres, a celui qui
    l'affichait (hors reecriture d'identifiant de session). Prealable non tranche par cette story
    et non chiffre ici : `nadia-prof` accepte deux issues - la boite disparait de l'accueil, ou
    elle y produit un effet - et ne choisit pas laquelle ; c'est une decision produit qui rouvre
    `DEC-0012` point 2, due avant ou avec cette story, jamais apres.
  - **PORTE-2.** Etant donne l'ecran de fin de partie ("C'est fini !"), quand le document est
    assemble, alors il affiche exactement une fois le mot "Les jeux", porte par `sortie-pleine` en
    aplat plein dans la zone de contenu, a la place et a la taille qu'il a aujourd'hui : (12, 108),
    117 x 56 px. Ni un second exemplaire dans l'en-tete, ni un decalage vers le bas. Verification :
    capture a 360 px de cet ecran, un seul "Les jeux" a l'image, son coin superieur gauche a
    (12, 108).
  - **PORTE-3.** Etant donne l'ecran d'erreur, quand le document est assemble, alors il reste tel
    quel : "Recommencer" y est la seule chose pressable, aucune sortie ne s'y ajoute. Verification :
    capture a 360 px de l'ecran d'erreur, un seul bouton a l'image.
  - **PORTE-4.** Etant donne un des trois etats de manche (fraiche, echec, revelation), quand le
    document est assemble, alors la colonne de contenu ne commence plus par une sortie : la
    premiere bande de texte sous le bandeau est le score, jamais un mot de sortie. Verification :
    capture a 360 px de la manche fraiche et de la revelation, premiere bande non vide de la
    colonne x 8-350 = "Score : n".
  - **PORTE-5.** Etant donne la boite de sortie de l'en-tete rendue sur un etat de manche, alors sa
    taille n'est jamais reduite pour recuperer les 9 px gagnes par le bandeau : plancher 109 x
    53 px, la boite mesuree le 2026-08-14, qui est aussi celle du bouton retire. `nadia-prof` la
    designe comme un plancher qu'elle ne relevera pas plus tard.
  - **PORTE-6.** Cette story n'est livree qu'apres la story "Confirmation avant d'abandonner une
    partie en cours" (en developpement par `marc-dev`, s11), jamais avant - voir la section
    "Interaction" plus bas pour le motif.
  - Le gain d'espace vertical obtenu n'est pas chiffre par cette story a l'ecriture : il est mesure
    par qui livre le lot (`theo-archi` ou `ines-design`), sur le rendu reel, et rapporte dans la
    revue plutot que suppose.
- **Limite connue, non couverte par cette story** : `nadia-prof` declare son propre jugement
  degrade sur l'atteignabilite reelle de la boite (239, 16) par une main de six ans - elle ne l'a
  pas eprouvee au doigt sur un telephone avec un enfant, seulement par une geometrie mesuree
  (109 x 53 px, au-dessus de la cible tactile de 44 x 44 px). Elle ne pose volontairement pas ce
  point en condition, faute de moyen du depot pour produire cette observation ; cette story ne le
  resout pas et ne doit pas etre presentee comme le resolvant.
- **Ce que cette story ne fait pas** :
  - Elle ne corrige pas par elle-meme le defaut leve par `DEC-0026` (clause 1 de M13, `ADR-0038`) :
    le bouton de l'en-tete, une fois demasque, porterait la meme requete destructrice
    (`data-role="sortie"`, `hx-post="/retour"`) que celui qu'il remplace. PORTE-6 neutralise ce
    risque en imposant que cette story ne soit jamais livree avant la confirmation avant abandon
    (voir "Interaction" plus bas) ; elle ne le neutralise pas si l'ordre n'est pas respecte.
  - Elle ne rouvre pas et ne referme pas elle-meme le Done pedagogique VALIDE de S6-9 : c'est
    l'arbitrage prealable rendu ci-dessus qui le fait, pas cette story.
  - Elle ne choisit pas le moyen technique de rendre l'en-tete visible en jeu (feuille de style,
    marqueur d'etat, autre) : `theo-archi`.
  - Elle ne compte pas comme l'une des deux stories que `DEC-0026` exige pour son declencheur de
    reouverture (voir la section precedente) : ni son perimetre ni ses criteres ne portent sur la
    tabulation.
- **Interaction avec la story "Confirmation avant d'abandonner une partie en cours" (PORTE-6)** :
  - Cette story ne peut pas etre livree avant que la confirmation avant abandon le soit, quelle que
    soit l'issue retenue pour le second remede possible de `DEC-0026` (correction de l'ordre de
    tabulation). Ce n'est pas une condition sur le lot de `marc-dev` : c'est une condition sur
    celle-ci.
  - Motif de `nadia-prof` : sans confirmation, le geste destructeur qui existe deja aujourd'hui
    (appui unique et non confirme sur "Les jeux") est aujourd'hui a (12, 108), la ou l'enfant appuie
    peu. Le porter en (239, 16) - au ras du bord droit et du haut de l'ecran, la ou une main derape
    et la ou un pointeur passe pour atteindre les commandes du navigateur - augmente la probabilite
    qu'il se declenche par accident, sans qu'elle puisse la chiffrer. Une fois la confirmation
    livree, ce cout disparait entierement : aucun chemin de sortie ne detruit plus la partie d'un
    seul geste, et la position de la porte redevient une pure question de lisibilite.
- **Lien decision** : `DEC-0027-retrait-bouton-sortie-fragment-manche-reouvre-s6-9-arbitrage-
  prealable-requis.md`, y compris son amendement du 2026-08-14 ;
  `squad/conformite/FICHE-bandit-manchot-additions-cp.md`, section citee ci-dessus.
- **Agent pressenti** : a affecter au planning par `sofia-sm`, apres la story de confirmation avant
  abandon (PORTE-6) ; moyen technique a `theo-archi`.

## Sprint 12 - Trois lignes de mathematiques CM1, sur le schema de contenu existant

**Contexte et decision de perimetre** : l'utilisatrice veut que le sprint 12 couvre les lignes de
mathematiques CM1 par la mecanique bandit manchot existante. Sur les douze lignes du referentiel
(`squad/programme/mathematiques/competences-cm1.md`), toutes `non couvert`,
`DEC-0029-perimetre-sprint-12-trois-lignes-mathematiques-cm1-ouvertes-cinq-differees.md` tranche que
trois entrent au sprint, toutes exprimables dans l'enveloppe `type: question` deja existante
(`ADR-0007`) sans une ligne de Java, de gabarit ni de feuille de style neuve : le perimetre d'un
carre ou d'un rectangle, la conversion d'unites de longueur/masse/contenance, et les tables de
multiplication jusqu'a x10. Trois lignes de plus restent differees pour capacite (multiplications et
divisions simples, problemes de durees, problemes a plusieurs etapes) et deux sont differees dans
l'attente d'un arbitrage prealable de `nadia-prof` sur un proxy textuel (lire/ecrire les grands
nombres ; reperage d'une fraction sur une droite numerique) - voir `DEC-0029` pour le detail des huit
lignes candidates et les quatre confirmees hors d'atteinte de cette mecanique (multiplication posee,
division euclidienne, tracer des figures, symetrie axiale).

Chaque story ouvre une fiche de conformite sous `squad/conformite/`, remplie a l'exception du bloc
de validation reserve a `nadia-prof` : le contenu de ce sprint repasse devant elle comme tout
contenu neuf, le niveau scolaire et la qualite pedagogique restant entierement de son ressort.

### S12-1 - Calculer le perimetre d'un carre ou d'un rectangle

- **Besoin** : en tant qu'eleve de CM1, je veux calculer le tour d'un carre ou d'un rectangle a
  partir de la longueur de ses cotes, afin de savoir combien de bordure ou de cloture il faut pour
  en faire le tour.
- **Competence programme visee** : mathematiques, CM1 (cycle 3), domaine "Grandeurs et mesures",
  "Calculer un perimetre de figures usuelles (carre, rectangle)" (`squad/programme/mathematiques/
  competences-cm1.md`).
- **Criteres d'acceptation** :
  - Etant donne chaque item du paquet dont la figure est un carre, quand on lit son enonce, alors
    il donne la longueur d'un seul cote (un nombre entier, une unite de longueur explicite - cm ou
    m), et la bonne reponse vaut exactement quatre fois cette longueur, dans la meme unite.
  - Etant donne chaque item dont la figure est un rectangle, quand on lit son enonce, alors il
    donne sa longueur et sa largeur (deux nombres entiers distincts, meme unite), et la bonne
    reponse vaut exactement deux fois leur somme, dans la meme unite.
  - Etant donne l'ensemble du paquet, quand on compte les items par figure, alors il contient au
    moins autant d'items sur le carre que sur le rectangle, a un pres : ni l'une ni l'autre figure
    nommee par le referentiel n'est reduite a un ou deux exemples isoles.
  - Etant donne chaque item, quand on compte ses propositions, alors il y en a exactement trois :
    la bonne reponse et deux distracteurs numeriques dans la meme unite, chacun correspondant a une
    confusion frequente et identifiable (par exemple le produit des cotes plutot que le double de
    leur somme - confusion avec l'aire -, ou la moitie du perimetre).
  - Etant donne le paquet, quand on verifie chaque item, alors aucun ne porte de figure autre que le
    carre ou le rectangle : le triangle equilateral, present dans `contenu-repris/quiz/
    cm1-perimetre.yml`, sort du perimetre exact de cette ligne ("figures usuelles (carre,
    rectangle)") et n'est pas repris.
  - Etant donne le paquet, quand on compte ses items, alors il en compte au moins 20.
  - Etant donne chaque item, quand on verifie qu'il porte un indice, alors ce champ est rempli et
    non vide.
  - Etant donne le paquet, quand on le charge au demarrage de l'application, alors il valide le
    schema `type: question` d'`ADR-0007` : un fichier YAML et une ligne de catalogue, zero ligne de
    Java, zero gabarit ni feuille de style neuve.
  - Ce qui n'est pas testable par un automate et releve de la seule porte pedagogique de
    `nadia-prof` : l'exactitude et la clarte de chaque enonce et de chaque indice pour un enfant de
    CM1, et la plausibilite des distracteurs (confusion reelle avec l'aire ou avec la moitie du
    perimetre, pas un nombre choisi au hasard).
- **Lien decision** :
  `DEC-0029-perimetre-sprint-12-trois-lignes-mathematiques-cm1-ouvertes-cinq-differees.md`.
- **Agent pressenti** : a affecter au planning par `sofia-sm` (avis de faisabilite de `theo-archi`
  requis avant affectation, comme pour toute story neuve).

### S12-2 - Convertir des longueurs, des masses et des contenances

- **Besoin** : en tant qu'eleve de CM1, je veux convertir une longueur, une masse ou une contenance
  d'une unite du systeme metrique a une autre, afin de savoir exprimer la meme quantite dans l'unite
  qu'on me demande.
- **Competence programme visee** : mathematiques, CM1 (cycle 3), domaine "Grandeurs et mesures",
  "Convertir des unites de longueur, masse, contenance dans le systeme metrique"
  (`squad/programme/mathematiques/competences-cm1.md`).
- **Criteres d'acceptation** :
  - Etant donne chaque item du paquet, quand on lit son enonce, alors il demande de convertir une
    quantite exprimee dans une unite du systeme metrique (longueur : mm, cm, m, km ; masse : g, kg,
    tonne ; contenance : ml, cl, L) vers une autre unite de la meme grandeur, et la bonne reponse
    est numeriquement exacte compte tenu du facteur de conversion entre les deux unites.
  - Etant donne l'ensemble du paquet, quand on repartit ses items par grandeur, alors chacune des
    trois grandeurs nommees par le referentiel - longueur, masse, contenance - est representee par
    au moins un quart des items : aucune des trois n'est absente ni reduite a un seul exemple, pour
    ne pas reproduire le motif de couverture partielle deja sanctionne sur ce produit.
  - Etant donne le paquet, quand on verifie chaque item, alors aucun ne porte une question
    d'estimation d'ordre de grandeur ("quelle est la masse d'une pomme ?") ni d'identification
    d'instrument ("avec quoi mesure-t-on une masse ?") : ces deux formes, presentes dans
    `contenu-repris/quiz/cm1-masses.yml`, `cm1-longueurs.yml` et `cm1-contenances.yml`, ne relevent
    pas du verbe "convertir" que cette ligne nomme.
  - Etant donne chaque item, quand on compte ses propositions, alors il y en a exactement trois ou
    quatre : la bonne reponse et deux ou trois distracteurs numeriques dans l'unite demandee, chacun
    correspondant a une confusion de facteur identifiable (par exemple multiplier par 10 au lieu de
    100, ou l'inverse).
  - Etant donne le paquet, quand on compte ses items, alors il en compte au moins 24, avec au moins
    8 items par grandeur (compatible avec le quart minimal ci-dessus).
  - Etant donne chaque item, quand on verifie qu'il porte un indice, alors ce champ est rempli et
    non vide.
  - Etant donne le paquet, quand on le charge au demarrage de l'application, alors il valide le
    schema `type: question` d'`ADR-0007`, zero ligne de Java neuve.
  - Ce qui n'est pas testable par un automate et releve de la seule porte pedagogique de
    `nadia-prof` : l'exactitude, la clarte et le niveau reel des enonces pour un enfant de CM1, la
    plausibilite des distracteurs, et si le regroupement des trois grandeurs sous un seul jeu reste
    lisible pour l'enfant plutot que confus.
- **Lien decision** :
  `DEC-0029-perimetre-sprint-12-trois-lignes-mathematiques-cm1-ouvertes-cinq-differees.md`.
- **Agent pressenti** : a affecter au planning par `sofia-sm` (avis de faisabilite de `theo-archi`
  requis avant affectation, comme pour toute story neuve).

### S12-3 - S'entrainer aux tables de multiplication jusqu'a x10

- **Besoin** : en tant qu'eleve de CM1, je veux retrouver rapidement le resultat d'une
  multiplication de deux nombres entre 1 et 10, afin de connaitre mes tables de multiplication par
  coeur.
- **Competence programme visee** : mathematiques, CM1 (cycle 3), domaine "Nombres et calculs",
  "Calcul mental - tables de multiplication (jusqu'a x10), rappel automatise"
  (`squad/programme/mathematiques/competences-cm1.md`).
- **Criteres d'acceptation** :
  - Etant donne chaque item du paquet, quand on lit son enonce, alors il est une multiplication
    ecrite en chiffres de deux facteurs entiers, chacun compris entre 1 et 10 inclus (par exemple
    "7 x 8 = ?"), et la bonne reponse est leur produit exact.
  - Etant donne l'ensemble des couples de facteurs (a, b) avec 1 <= a <= b <= 10, quand on les
    compare aux items du paquet, alors chacun des 55 couples distincts est represente par au moins
    un item - couverture complete de la table jusqu'a x10, pas seulement ses produits les plus
    faciles - un couple pouvant etre ecrit dans un ordre ou dans l'autre (7 x 8 ou 8 x 7) au choix
    de l'auteur.
  - Etant donne chaque item, quand on compte ses propositions, alors il y en a exactement trois :
    la bonne reponse et deux distracteurs numeriques distincts, chacun correspondant a une erreur
    frequente identifiable (par exemple un facteur mal lu de un, ou la somme des deux facteurs
    plutot que leur produit).
  - Etant donne le paquet, quand on compte ses items, alors il en compte au moins 55 (un par couple
    minimum, voir ci-dessus).
  - Etant donne chaque item, quand on verifie qu'il porte un indice, alors ce champ est rempli et
    non vide.
  - Etant donne le paquet, quand on le charge au demarrage de l'application, alors il valide le
    schema `type: question` d'`ADR-0007`, et non `additions-bornees` : ce type reste specifique a
    l'addition (`AdditionsBorneesContentSourceFactory`), rien n'y generalise a la multiplication ;
    zero ligne de Java neuve n'est necessaire pour cette story.
  - Ce qui n'est pas testable par un automate et releve de la seule porte pedagogique de
    `nadia-prof` : l'exactitude et la clarte des enonces et des indices pour un enfant de CM1, la
    plausibilite des distracteurs, et si le nombre de manches declare au catalogue permet de
    parcourir l'espace des 55 couples de facon satisfaisante au fil des parties (meme question de
    principe que celle tranchee pour les complements a 10 par `DEC-0020`, a instruire de nouveau ici
    si l'espace fini pose une contrainte comparable).
- **Lien decision** :
  `DEC-0029-perimetre-sprint-12-trois-lignes-mathematiques-cm1-ouvertes-cinq-differees.md`.
- **Agent pressenti** : a affecter au planning par `sofia-sm` (avis de faisabilite de `theo-archi`
  requis avant affectation, comme pour toute story neuve).

### S12-4 - Une manche de calcul mental ne se gagne plus en devinant le nombre du milieu

**Contexte** : `nadia-prof` a rejoue `bandit-manchot-additions-cp` a la cloture de s12 et **retire**
le Done pedagogique qu'elle avait accorde en s01 et confirme en s03
(`squad/conformite/FICHE-bandit-manchot-additions-cp.md`, verdict du 2026-08-15). Sur 66,5 % des
manches, la bonne reponse est le nombre intermediaire des trois propositions affichees ; un enfant
qui presse toujours celui du milieu gagne deux manches sur trois sans additionner. La cause qu'elle
identifie n'est pas le contenu YAML, deja exact, mais la fabrique elle-meme : `AdditionsBorneesContentSource`
tire ses distracteurs par ecarts croissants de part et d'autre de la bonne reponse, ce qui place
mecaniquement celle-ci au centre sur tout contenu qu'elle produira. `squad/programme/
MATRICE-couverture.md` fait redescendre la ligne mathematiques CP "Calcul mental - additions dans la
limite de 10 (puis 20)" de `couvert-valide` a `en cours` en consequence. Cette story corrige la
fabrique, cause racine, en parallele des autres travaux du sprint (branche `s12-marc-fabrique`).

- **Besoin** : en tant qu'eleve de CP, je veux que gagner une manche de calcul mental demande
  vraiment de calculer l'addition, afin de ne pas pouvoir gagner en devinant toujours le nombre du
  milieu sans lire l'enonce.
- **Competence programme visee** : mathematiques, CP (cycle 2), "Calcul mental - additions dans la
  limite de 10 (puis 20)" (`squad/programme/mathematiques/competences-cp.md`) - ligne deja ouverte,
  non rouverte sur son perimetre (borne 10 uniquement) ni sur la repartition des sommes deja levee
  en s03.
- **Criteres d'acceptation**, repris au mot pres du motif de `nadia-prof` :
  - Etant donne le calcul exhaustif sur l'ensemble des couples valides de la fabrique corrigee,
    quand on compte le rang de la bonne reponse (la plus petite, l'intermediaire, ou la plus grande
    des trois propositions) sur chaque item qu'elle peut produire, alors aucun des trois rangs ne
    represente 50 % ou plus des items - meme propriete et meme seuil que le garde-fou generique deja
    livre par `lea-dev` (`LaBonneReponseNEstPasPredictibleSurUnPaquetTest`, `ADR-0013`, `ADR-0039`),
    a appliquer a ce paquet.
  - Etant donne 900 manches jouees en trois series de 300 (une strategie qui presse toujours la plus
    petite proposition, une qui presse toujours l'intermediaire, une qui presse toujours la plus
    grande, sans jamais lire l'enonce), quand on compte les manches gagnees par chacune, alors aucune
    des trois strategies n'atteint 50 % de manches gagnees.
  - Etant donne la meme fabrique corrigee, quand on verifie les criteres deja coches de cette ligne
    (operandes et somme entiers entre 0 et 10, unique bonne reponse par item, distracteurs entiers
    positifs et plausibles, aucune somme superieure a 10), alors ils restent tous vrais : cette story
    ne touche pas ces proprietes, deja acquises.
  - Etant donne la repartition des sommes proposees sur l'intervalle 0-10, quand on la mesure sur un
    grand nombre de manches, alors elle reste conforme a la condition posee par `nadia-prof` en s01
    et levee en s03 (S3-1) : aucune somme ne doit redevenir dominante au point de concentrer le jeu
    sur le bord superieur de l'intervalle. Cette story ne rouvre pas cette condition, elle ne doit
    simplement pas la degrader.
  - Ce qui n'est pas testable par un automate et releve de la seule porte pedagogique de
    `nadia-prof` : qu'elle rejoue l'increment corrige et confirme, en jouant, que le rang de la bonne
    reponse n'est plus predictible sans additionner, et que l'exactitude arithmetique deja acquise
    (900 manches sans une faute) n'a pas ete degradee par la correction.
- **Lien decision** : aucune - correction de cause racine sur un motif deja ecrit par
  `nadia-prof`, pas un changement de perimetre produit. Motif source :
  `squad/conformite/FICHE-bandit-manchot-additions-cp.md`, verdict du 2026-08-15 ; garde-fou
  generique de reference : branche `s12-lea-tables-rang`.
- **Agent pressenti** : `marc-dev`, deja engage sur la branche `s12-marc-fabrique` en parallele de
  ce sprint.

### S12-5 - L'unique lien de sortie n'est plus annonce comme un menu a un enfant qui ecoute l'ecran

**Contexte** : dans son arbitrage prealable de mecanique (`squad/conformite/
FICHE-bandit-manchot-additions-cp.md`, section 4), `nadia-prof` prend l'attribut `aria-label="Menu
principal"` dans son perimetre - "tout texte que le serveur envoie et qu'un enfant peut recevoir
releve de cette porte, qu'il soit affiche a l'ecran ou annonce par une aide technique" - et le
**refuse**, sur quatre motifs cumulatifs : c'est faux (une zone a un seul lien n'est pas un menu) ;
les deux mots ne sont pas du vocabulaire d'un enfant de six ans ; l'application n'a qu'un seul mot
de sortie, "Les jeux", et cette annonce en introduit un second pour la meme destination ; aucun
enfant ne voit ces deux mots nulle part ailleurs dans l'application. Ce refus ne remet en cause
aucun Done pedagogique deja prononce et ne rend aucun jeu injouable : c'est un texte a corriger, pas
un defaut de mecanique.

- **Besoin** : en tant qu'eleve qui utilise un lecteur d'ecran, je veux qu'on m'annonce le lien de
  sortie avec le mot que je connais deja, afin de ne pas chercher un menu qui n'existe pas ni
  apprendre un second mot pour la meme sortie.
- **Competence programme visee** : transverse - pas de contenu pedagogique.
- **Criteres d'acceptation**, repris au mot pres du motif de `nadia-prof` :
  - Etant donne la zone de navigation du gabarit, quand on lit ce qu'un lecteur d'ecran en
    annoncerait, alors soit aucun attribut d'etiquette n'y est porte (une zone a un seul lien qui se
    nomme deja lui-meme "Les jeux" n'a rien a distinguer), soit, si une contrainte technique impose
    un libelle non vide, ce libelle est exactement "Les jeux" - jamais "Menu principal" ni aucun
    autre mot que l'enfant ne voit pas deja a l'ecran.
  - Etant donne l'application entiere, quand on recherche toute occurrence du mot "menu" ou du mot
    "principal" dans un texte annonce (`aria-label`, `title`) ou affiche a l'enfant, alors aucune
    occurrence ne subsiste : l'application garde un seul mot de sortie, "Les jeux", sur les deux
    canaux.
  - Etant donne cette correction, quand on rejoue les cinq ecrans de l'application, alors aucun Done
    pedagogique deja prononce n'est modifie et aucun jeu ne devient injouable : cette story ne
    touche qu'un texte annonce, rien d'autre.
  - Ce qui n'est pas testable par un automate et releve de la seule porte pedagogique de
    `nadia-prof` : ce que l'annonce donne reellement a entendre a l'ecoute reelle d'un lecteur
    d'ecran - sa reserve F11 (aucune ecoute reelle tentee a ce jour par l'equipe) reste ouverte et
    n'est pas levee par cette story.
- **Lien decision** : aucune - correction directe d'un motif de refus de `nadia-prof`.
  `squad/conformite/FICHE-bandit-manchot-additions-cp.md`, arbitrage de mecanique, section 4.
- **Agent pressenti** : a affecter au planning par `sofia-sm` (le moyen technique de porter ou de
  retirer l'etiquette appartient a `theo-archi`, comme pour toute story neuve). **Affectee au
  sprint 13** (`DEC-0030`) : elle etait entierement redigee et sans obstacle, seule son affectation
  manquait.

## Sprint 13 - Trois lignes de plus de mathematiques CM1, deux corrections de reserve sur les tables,
## un chemin de catalogue qui garde la partie

**Contexte et decision de perimetre** : `DEC-0030-perimetre-sprint-13-trois-lignes-mathematiques-
cm1-et-le-plafond-de-la-mecanique-unique.md` ouvre trois lignes de mathematiques CM1 differees pour
capacite au sprint 12 (grands nombres, desormais sans obstacle grace a l'arbitrage prealable rendu le
2026-08-14 ; multiplications et divisions simples ; problemes de durees), corrige deux reserves non
bloquantes sur les tables de multiplication, ouvre le chemin de catalogue nomme par la reserve O
seconde moitie, et affecte la story S12-5 deja redigee. Deux lignes restent differees (problemes a
plusieurs etapes - second report ; notion de fraction simple - premier report), voir `DEC-0030`
sections 1 et 3. La meme decision instruit, en parallele et sans capacite de developpement affectee ce
sprint, la question d'une seconde mecanique de jeu (`DEC-0030`, section 6).

S13-1 ouvre sur un proxy deja arbitre par `nadia-prof` (`ARBITRAGE-PREALABLE-s12-mathematiques-cm1.md`,
section 1, GN-1 et GN-2) : aucun arbitrage neuf n'est demande. S13-2 et S13-3 portent sur un contenu
jamais instruit a ce niveau de detail ; `DEC-0030` demande a `nadia-prof` un arbitrage prealable sur
ces deux lignes avant que le code ne commence, sur le meme modele que le sprint 12.

### S13-1 - Lire, ecrire, decomposer et comparer les grands nombres jusqu'au million

- **Besoin** : en tant qu'eleve de CM1, je veux lire, ecrire, decomposer et comparer des nombres
  entiers jusqu'au million, afin de comprendre comment un grand nombre est organise en classes et en
  positions.
- **Competence programme visee** : mathematiques, CM1 (cycle 3), domaine "Nombres et calculs", "Lire,
  ecrire, decomposer et comparer les nombres entiers jusqu'au million" (`squad/programme/
  mathematiques/competences-cm1.md`) - ligne entiere, sans couverture partielle : les quatre verbes du
  referentiel sont tous servis par ce paquet.
- **Criteres d'acceptation** :
  - Etant donne chaque item qui teste "ecrire" (designation litterale donnee, ecriture chiffree a
    trouver) ou "lire" (ecriture chiffree donnee, designation litterale a trouver), quand on compare
    la bonne reponse a ses distracteurs dans le sens chiffres vers lettres, alors chaque distracteur
    differe de la bonne reponse par la valeur qu'il designe (une classe ou une position confondue -
    par exemple "trois mille cinq cent vingt-et-un" contre "trois cent cinq mille quatre cent
    vingt-et-un", ou un zero intercalaire ignore), jamais par la seule orthographe des mots-nombres
    (condition GN-1, `ARBITRAGE-PREALABLE-s12-mathematiques-cm1.md`, section 1).
  - Etant donne chaque item dans le sens lettres vers chiffres, quand on lit sa designation litterale,
    alors elle est ecrite sans faute et sans ambiguite, avec les traits d'union de la reforme ou sans,
    mais de facon constante dans tout le paquet (condition GN-2, meme section).
  - Etant donne chaque item qui teste "decomposer", quand on lit son enonce, alors il donne un nombre
    entier jusqu'au million et demande sa decomposition en valeurs de position (classes de mille,
    centaines, dizaines, unites), et la bonne reponse est la decomposition exacte.
  - Etant donne chaque item qui teste "comparer", quand on lit son enonce, alors il donne deux nombres
    entiers distincts jusqu'au million et demande le signe exact parmi <, > et = - meme patron que
    `cp-comparer-nombres.yaml`, a l'echelle du million.
  - Etant donne l'ensemble du paquet, quand on repartit ses items par verbe (lire, ecrire, decomposer,
    comparer), alors chacun des quatre est represente par au moins un quart des items : aucun des
    quatre verbes nommes par le referentiel n'est absent ni reduit a un seul exemple isole - pour ne
    pas reproduire le motif de couverture partielle deja sanctionne sur ce produit.
  - Etant donne chaque item, quand on compte ses propositions, alors aucune n'est egale a une autre
    dans le meme item (meme exigence que P-7, C-7, T-5 du sprint 12).
  - Etant donne le paquet, quand on le compte, alors il compte au moins 24 items, avec au moins 6 par
    verbe.
  - Etant donne chaque item, quand on verifie qu'il porte un indice, alors ce champ est rempli, non
    vide, et ne paraphrase pas l'enonce.
  - Etant donne le paquet, quand on le charge au demarrage de l'application, alors il valide le schema
    `type: question` (`ADR-0007`), zero ligne de Java neuve.
  - Ce qui n'est pas testable par un automate et releve de la seule porte pedagogique de
    `nadia-prof` : l'exactitude et la clarte de chaque enonce et de chaque indice pour un enfant de
    CM1, la plausibilite des distracteurs de classe et de position, la constance de l'ecriture
    litterale dans tout le paquet, et si le regroupement des quatre verbes sous un seul jeu reste
    lisible plutot que confus.
- **Lien decision** : `DEC-0030-perimetre-sprint-13-trois-lignes-mathematiques-cm1-et-le-plafond-de-
  la-mecanique-unique.md` ; proxy deja arbitre par `nadia-prof`,
  `ARBITRAGE-PREALABLE-s12-mathematiques-cm1.md`, section 1 (GN-1, GN-2).
- **Agent pressenti** : a affecter au planning par `sofia-sm` (avis de faisabilite de `theo-archi`
  requis avant affectation, comme pour toute story neuve).

### S13-2 - Calcul mental, multiplier ou diviser par 10 et par 100

- **Besoin** : en tant qu'eleve de CM1, je veux multiplier ou diviser rapidement un nombre par 10 ou
  par 100, afin de calculer de tete sans poser l'operation.
- **Competence programme visee** : mathematiques, CM1 (cycle 3), domaine "Nombres et calculs", "Calcul
  mental - multiplications et divisions simples (multiples de 10, 100)" (`squad/programme/
  mathematiques/competences-cm1.md`).
- **Criteres d'acceptation** :
  - Etant donne chaque item du paquet, quand on lit son enonce, alors il est soit une multiplication
    soit une division d'un nombre entier par 10 ou par 100 (par exemple "23 x 10 = ?", "4 500 / 100 =
    ?"), sans unite de grandeur - une operation de calcul mental, pas une conversion de mesure - et la
    bonne reponse est le resultat exact.
  - Etant donne chaque item, quand on verifie le nombre de depart et le resultat, alors les deux sont
    des entiers positifs (une division qui ne tombe pas juste n'est pas un item de cette ligne), et
    aucun nombre decimal n'apparait, ni dans l'enonce ni dans une proposition - le referentiel de ce
    depot ne couvre l'ecriture decimale sur aucune ligne livree.
  - Etant donne l'ensemble du paquet, quand on separe ses items par operation, alors la multiplication
    et la division sont representees a une unite d'ecart pres l'une de l'autre.
  - Etant donne l'ensemble du paquet, quand on separe ses items par facteur, alors les items sur 10 et
    les items sur 100 sont chacun representes par au moins un quart du paquet.
  - Etant donne chaque item, quand on compte ses propositions, alors il y en a exactement trois : la
    bonne reponse et deux distracteurs entiers distincts, chacun correspondant a une erreur de facteur
    identifiable (multiplier par 10 au lieu de 100, diviser au lieu de multiplier, ou l'inverse) -
    jamais deux propositions egales entre elles dans le meme item.
  - Etant donne le paquet, quand on le compte, alors il compte au moins 24 items.
  - Etant donne chaque item, quand on verifie qu'il porte un indice, alors ce champ est rempli, non
    vide, et donne la methode (par exemple "multiplier par 10, c'est ajouter un zero.") plutot que le
    resultat.
  - Etant donne le paquet, quand on le charge au demarrage de l'application, alors il valide le schema
    `type: question` (`ADR-0007`), zero ligne de Java neuve.
  - Ce qui n'est pas testable par un automate et releve de la seule porte pedagogique de
    `nadia-prof` : l'exactitude et la clarte des enonces et des indices pour un enfant de CM1, la
    plausibilite des distracteurs, et si les bornes que je propose ici (10 et 100 seulement, aucune
    ecriture decimale) sont les bonnes - cette story demande un arbitrage prealable, voir la decision
    liee.
- **Lien decision** : `DEC-0030-perimetre-sprint-13-trois-lignes-mathematiques-cm1-et-le-plafond-de-
  la-mecanique-unique.md`, section 5 - arbitrage prealable de `nadia-prof` demande et non encore rendu
  sur ce contenu, contrairement a S13-1.
- **Agent pressenti** : a affecter au planning par `sofia-sm`, apres l'arbitrage prealable de
  `nadia-prof` et l'avis de faisabilite de `theo-archi`.

### S13-3 - Resoudre des problemes de durees

- **Besoin** : en tant qu'eleve de CM1, je veux calculer la duree ecoulee entre deux heures ou trouver
  l'heure d'arrivee a partir d'un depart et d'une duree, afin de resoudre des problemes concrets de
  temps qui passe.
- **Competence programme visee** : mathematiques, CM1 (cycle 3), domaine "Grandeurs et mesures",
  "Resoudre des problemes de durees (calcul d'une duree, d'un horaire d'arrivee)" (`squad/programme/
  mathematiques/competences-cm1.md`).
- **Criteres d'acceptation** :
  - Etant donne chaque item du paquet, quand on lit son enonce, alors il releve de l'une des deux
    formes nommees par le referentiel : soit calculer la duree ecoulee entre une heure de depart et une
    heure de fin toutes deux donnees dans l'enonce, soit calculer l'heure d'arrivee a partir d'une
    heure de depart et d'une duree toutes deux donnees dans l'enonce - jamais un item de simple lecture
    de l'heure ("huit heures et quart, c'est...", competence CP, hors de cette ligne) ni un item de
    conversion d'unite de temps seule ("combien de minutes dans une heure ?", hors de cette ligne).
    `contenu-repris/quiz/cm1-heure.yml` porte deux items reellement conformes a "calcul d'une duree"
    ("de 14h a 16h, combien de temps s'est ecoule ?", le film de 8h30 a 10h) et aucun sur "horaire
    d'arrivee" - matiere premiere partielle, le reste est un contenu neuf.
  - Etant donne l'ensemble du paquet, quand on separe ses items par forme, alors "calcul d'une duree"
    et "horaire d'arrivee" sont toutes deux representees, chacune par au moins un tiers des items.
  - Etant donne chaque item, quand on lit ses heures, alors elles restent dans une seule journee (aucun
    item ne franchit minuit), exprimees en heures et minutes rondes de 5 en 5 minutes, sans seconde.
  - Etant donne chaque item, quand on compte ses propositions, alors il y en a exactement trois : la
    bonne reponse et deux distracteurs plausibles (erreur de report d'heure ou de minute, inversion du
    sens de l'operation, oubli de la retenue de 60 minutes) - jamais deux propositions egales entre
    elles.
  - Etant donne le paquet, quand on le compte, alors il compte au moins 20 items.
  - Etant donne chaque item, quand on verifie qu'il porte un indice, alors ce champ est rempli, non
    vide, et donne la methode plutot que le resultat.
  - Etant donne le paquet, quand on le charge au demarrage de l'application, alors il valide le schema
    `type: question` (`ADR-0007`), zero ligne de Java neuve.
  - Ce qui n'est pas testable par un automate et releve de la seule porte pedagogique de
    `nadia-prof` : l'exactitude et la clarte des enonces et des indices pour un enfant de CM1, la
    plausibilite des distracteurs, et si mes bornes (une seule journee, minutes rondes de 5 en 5) sont
    les bonnes - cette story demande un arbitrage prealable, voir la decision liee.
- **Lien decision** : `DEC-0030-perimetre-sprint-13-trois-lignes-mathematiques-cm1-et-le-plafond-de-
  la-mecanique-unique.md`, section 5 - arbitrage prealable de `nadia-prof` demande et non encore rendu
  sur ce contenu.
- **Agent pressenti** : a affecter au planning par `sofia-sm`, apres l'arbitrage prealable de
  `nadia-prof` et l'avis de faisabilite de `theo-archi`.

### S13-4 - Correction de reserve - Un enonce des tables de multiplication ne revient plus une partie
### sur cinq

**Contexte** : `nadia-prof` mesure, au second verdict du sprint 12, que 99 items ne portent que 83
enonces distincts - les huit carres (2x2 a 9x9) sont chacun portes par trois items au meme enonce -
et que la meme multiplication revient dans **21,6 %** des parties (108 sur 500), contre son estimation
initiale au refus du 2026-08-14 ("environ une fois sur sept", 14,3 %). Elle nomme elle-meme cette
reserve "plus lourde qu'elle ne l'avait dite" (`squad/conformite/FICHE-bandit-manchot-tables-
multiplication-cm1.md`, "Synthese apres les seconds verdicts du sprint 12", reserve A).

- **Besoin** : en tant qu'eleve de CM1 qui joue plusieurs parties de suite au jeu des tables, je veux
  rencontrer une plus grande variete de multiplications, afin de ne pas retomber trop souvent sur la
  meme d'une partie a l'autre.
- **Competence programme visee** : mathematiques, CM1 (cycle 3), "Calcul mental - tables de
  multiplication (jusqu'a ×10), rappel automatise" - ligne deja `couvert-valide` sous reserve, non
  rouverte sur son perimetre ni sur la couverture partielle "sans mesure d'automatisation" deja
  posee.
- **Criteres d'acceptation**, repris au mot et au chiffre pres du motif de `nadia-prof` :
  - Etant donne le paquet corrige, quand on compte le nombre d'enonces distincts (un couple de
    facteurs dans un ordre donne), alors aucun enonce n'est plus repete trois fois a l'identique par
    simple copie : une repetition eventuelle d'un enonce se distingue au moins par ses distracteurs, et
    le nombre d'enonces distincts se rapproche des 99 items du paquet plutot que de s'arreter a 83.
  - Etant donne 500 parties de dix manches jouees en repondant au hasard (meme protocole que le second
    audit de `nadia-prof` du sprint 12), quand on mesure la proportion de parties qui tirent au moins
    deux fois le meme enonce, alors cette proportion se rapproche de l'estimation initiale de
    `nadia-prof` ("une partie sur sept environ", 14,3 %) - sans qu'un seuil exact soit fixe ici : c'est
    elle qui juge, en rejouant, si la correction suffit.
  - Etant donne cette correction, quand on verifie les criteres deja coches de cette ligne (T-1 a T-7,
    `ARBITRAGE-PREALABLE-s12-mathematiques-cm1.md`, section 4), alors ils restent tous vrais : cette
    story ne les rouvre pas.
  - Ce qui n'est pas testable par un automate et releve de la seule porte pedagogique de
    `nadia-prof` : si la variete percue en jeu, sur plusieurs parties reellement jouees, est suffisante
    pour un enfant de CM1.
- **Lien decision** : aucune - correction directe d'une reserve non bloquante de `nadia-prof`.
  `squad/conformite/FICHE-bandit-manchot-tables-multiplication-cm1.md`, "Synthese apres les seconds
  verdicts du sprint 12", reserve A ; affectee par `DEC-0030`.
- **Agent pressenti** : a affecter au planning par `sofia-sm`.

### S13-5 - Correction de reserve - Aucun distracteur des tables de multiplication ne sort de l'espace
### des tables

**Contexte** : `nadia-prof` releve, meme verdict, que les distracteurs de deux items sortent de
l'espace arithmetique possible : 11 sur "1 x 10 = ?" et 110 sur "10 x 10 = ?", alors qu'un produit de
deux facteurs entre 1 et 10 ne vaut jamais 11 ni 110 (reserve B, meme section de la meme fiche).

- **Besoin** : en tant qu'eleve de CM1, je veux que les deux mauvaises reponses proposees pour une
  multiplication restent des produits plausibles de deux nombres entre 1 et 10, afin de ne pas pouvoir
  eliminer un distracteur seulement parce qu'il n'appartient a aucune table.
- **Competence programme visee** : mathematiques, CM1 (cycle 3), meme ligne que S13-4, non rouverte.
- **Criteres d'acceptation**, repris au mot et au chiffre pres du motif de `nadia-prof` :
  - Etant donne l'item "1 x 10 = ?", quand on lit ses distracteurs, alors aucun ne vaut 11.
  - Etant donne l'item "10 x 10 = ?", quand on lit ses distracteurs, alors aucun ne vaut 110.
  - Etant donne l'ensemble du paquet, quand on verifie chaque distracteur, alors chacun est soit un
    produit reel de deux facteurs entre 1 et 10, soit une erreur nommee par la condition T-5
    (`ARBITRAGE-PREALABLE-s12-mathematiques-cm1.md`, section 4 - facteur voisin, ou somme des facteurs
    sur les petits produits seulement) - jamais un nombre hors de cet espace.
  - Ce qui n'est pas testable par un automate et releve de la seule porte pedagogique de
    `nadia-prof` : la plausibilite pedagogique de chaque distracteur corrige.
- **Lien decision** : aucune - correction directe d'une reserve non bloquante de `nadia-prof`.
  `squad/conformite/FICHE-bandit-manchot-tables-multiplication-cm1.md`, meme section, reserve B ;
  affectee par `DEC-0030`.
- **Agent pressenti** : a affecter au planning par `sofia-sm`.

### S13-6 - Un chemin vers le catalogue qui ne detruit pas la partie en cours

**Contexte** : `nadia-prof` note, le 2026-08-13, qu'il n'existe aucun chemin vers l'ecran de catalogue
qui garde la partie en cours - le seul chemin, le lien "Les jeux", appelle `abandonner()` sans
confirmation ; recharger la page ou revenir en arriere ramene l'enfant sur l'ecran de jeu, jamais sur
le catalogue (reserve O, seconde moitie, `squad/conformite/FICHE-bandit-manchot-additions-cp.md`,
arbitrage de mecanique). La premiere moitie de la reserve O (une confirmation avant d'abandonner) est
deja fermee en s11. `DEC-0030` tranche l'arbitrage produit qui manquait : ce chemin est souhaitable,
distinct de la persistance entre deux sessions fermee par `DEC-0028` (celle-ci reste dans la meme
partie, la meme session, le meme onglet).

- **Besoin** : en tant qu'eleve en train de jouer une partie, je veux pouvoir consulter la liste des
  jeux sans perdre ma partie en cours, afin de changer d'avis ou de verifier ce qui existe sans devoir
  recommencer.
- **Competence programme visee** : transverse - pas de contenu pedagogique.
- **Criteres d'acceptation** :
  - Etant donne une partie en cours, quand l'enfant choisit de consulter le catalogue des jeux (chemin
    dont le moyen technique appartient a `theo-archi`), alors la partie en cours n'est pas detruite :
    ses attributs de session (jeu, consigne, score, manche courante) restent intacts.
  - Etant donne ce chemin, quand l'enfant revient ensuite a l'ecran de jeu, alors la manche affichee
    est exactement celle qui etait en cours avant la consultation du catalogue - meme exigence
    d'egalite de document rendu que celle deja posee pour le retour depuis l'ecran de confirmation.
  - Etant donne ce chemin, quand l'enfant choisit depuis le catalogue de lancer un autre jeu, alors la
    partie precedente est abandonnee comme aujourd'hui : ce cas reste un choix explicite de l'enfant,
    pas un accident, et le comportement actuel n'est pas modifie sur ce point.
  - Ce que cette story ne tranche pas : le moyen technique exact du nouveau chemin - `theo-archi` ; la
    forme visuelle - `ines-design` propose ; le texte destine a l'enfant - `nadia-prof` juge.
  - Ce qui n'est pas testable par un automate et releve de la seule porte pedagogique de
    `nadia-prof` : la clarte, pour un enfant de CP, de la difference entre "voir les jeux sans perdre
    ma partie" et "quitter ma partie" - deux actions desormais distinctes qui n'existaient pas l'une
    sans l'autre avant cette story.
- **Lien decision** : `DEC-0030-perimetre-sprint-13-trois-lignes-mathematiques-cm1-et-le-plafond-de-
  la-mecanique-unique.md`, section 7 ; reserve O seconde moitie,
  `squad/conformite/FICHE-bandit-manchot-additions-cp.md`, arbitrage de mecanique, ouverte le
  2026-08-13.
- **Agent pressenti** : a affecter au planning par `sofia-sm`, avis de faisabilite de `theo-archi`
  requis avant affectation.

**Note de cloture (s14, camille-po, 2026-08-17).** Verifie contre le depot avant d'ecrire cette
note : ni `review.md`, ni `RAPPORT.md`, ni `retro.md` de s13 ne mentionnent cette story - elle a ete
affectee au planning (`planning.md`, second lot de `marc-dev`) mais jamais developpee.
**Elle n'est pas reconduite sous cette forme : elle est remplacee par `S14-1`**
(`DEC-0032-perimetre-sprint-14-barre-entree-niveau-arbitrage-prealable-matiere-differee.md`,
section 4). Le besoin qu'elle portait - atteindre le catalogue sans perdre une partie en cours - est
repris par `S14-1`, mais avec une resolution differente : `S14-1` passe par l'ecran de confirmation
existant des qu'une partie est en cours (une action potentiellement destructrice signalee), la ou
cette story visait une consultation qui ne modifie jamais l'etat de la partie, sans confirmation
d'aucune sorte. Cette story n'est plus a affecter au planning.

## Sprint 14 - Une barre d'entree par niveau, toujours visible

**Contexte et decision de perimetre** :
`DEC-0032-perimetre-sprint-14-barre-entree-niveau-arbitrage-prealable-matiere-differee.md`. La
demande de l'utilisatrice porte des points d'entree par niveau et par matiere dans l'en-tete,
toujours visibles. Le sprint 14 n'ouvre que le niveau ; la matiere est differee a s15 le temps de
trancher la nomenclature affichee (accents). `DEC-0032` remplace `S13-6` par la story ci-dessous.

### S14-1 - Une barre d'entree par niveau, toujours visible dans l'en-tete

- **Besoin** : en tant qu'eleve de CP ou de CM1, je veux voir et utiliser a tout moment un bouton
  pour retrouver les jeux de mon niveau, meme pendant que je joue, afin de changer de jeu sans
  devoir d'abord chercher comment sortir de ma partie.
- **Competence programme visee** : transverse - pas de contenu pedagogique. Cette story ne cible
  aucune ligne de `squad/programme/MATRICE-couverture.md` ; elle change la navigation entre des
  jeux qui existent deja.
- **Arbitrages deja rendus par l'utilisatrice, non rouverts par cette story** (`DEC-0032`) :
  1. Les boutons de niveau sont toujours visibles dans l'en-tete, y compris sur un ecran de jeu
     (zone de contenu portant le marqueur `ecran-de-jeu`) - une propriete que le lien "Les jeux"
     actuel n'a pas, puisqu'il est masque sur ce meme marqueur (`ADR-0022`, S6-9).
  2. Un appui sur un bouton de niveau pendant une partie en cours ne detruit pas la partie d'un seul
     geste : il passe par l'ecran de confirmation deja existant (`fragments/confirmation.html`,
     `DEC-0026`, `ADR-0038`, `ADR-0042`), exactement comme le bouton de sortie de l'ecran de manche
     aujourd'hui.
  3. Le filtre par matiere est hors perimetre de cette story (differe a s15).
- **Ce que cette story remplace** : le besoin ouvert par `S13-6` (section "Sprint 13" ci-dessus, note
  de cloture) - un chemin vers le catalogue pendant une partie en cours - est repris ici, mais avec
  la resolution que l'utilisatrice a choisie : le passage par la confirmation existante, et non une
  consultation garantie sans consequence. `S13-6` n'est plus a developper sous sa forme d'origine.
- **Criteres d'acceptation** :
  - Etant donne l'ecran d'accueil, quand la page est rendue (avec ou sans partie en cours), alors
    l'en-tete affiche deux boutons de niveau, "CP" et "CM1", en plus des elements d'en-tete deja
    existants.
  - Etant donne un ecran de jeu (zone de contenu portant le marqueur `ecran-de-jeu` : manche, echec,
    revelation, fin de partie, erreur), quand la page ou le fragment est rendu, alors les deux
    boutons de niveau restent visibles dans l'en-tete - a la difference du lien "Les jeux" actuel,
    masque sur ce meme marqueur (`ADR-0022`) : c'est la propriete "toujours visible" que cette
    story introduit.
  - Etant donne l'ecran d'accueil sans partie en cours, quand l'enfant appuie sur un bouton de
    niveau, alors la liste de jeux affichee se filtre immediatement sur ce niveau, sans passer par
    l'ecran de confirmation - il n'y a rien a proteger.
  - Etant donne une partie en cours, quand l'enfant appuie sur un bouton de niveau, alors l'ecran de
    confirmation existant (`fragments/confirmation.html`) s'affiche avant tout changement, et la
    partie n'est pas encore modifiee a ce stade.
  - Etant donne l'ecran de confirmation ouvert depuis un bouton de niveau, quand l'enfant choisit de
    continuer sa partie, alors elle reprend exactement sur la manche qui etait affichee avant
    l'ouverture de la confirmation, et aucun filtre de niveau n'est applique.
  - Etant donne l'ecran de confirmation ouvert depuis un bouton de niveau, quand l'enfant confirme
    vouloir quitter, alors la partie en cours est abandonnee (meme comportement qu'un abandon
    ordinaire aujourd'hui) et l'ecran d'accueil affiche la liste des jeux filtree sur le niveau
    choisi.
  - Etant donne la liste de jeux filtree sur un niveau, quand on la compare au catalogue complet,
    alors elle contient exactement les jeux dont le niveau du `ContentDescriptor` correspond au
    niveau choisi, et aucun jeu de l'autre niveau - source de donnees deja portee par chaque paquet,
    aucune donnee neuve necessaire.
  - Etant donne l'ecran d'accueil au premier chargement (sans navigation prealable), quand aucun
    bouton de niveau n'a encore ete presse, alors la liste affichee reste le catalogue complet des
    onze jeux, comme aujourd'hui : cette story ajoute un filtre, elle ne retire pas la vue non
    filtree.
  - Ce que cette story ne tranche pas : le moyen technique exact de la barre et du filtrage
    (`theo-archi`) ; la forme visuelle des boutons et le moyen de revenir a la vue non filtree une
    fois un niveau choisi (`ines-design` propose) ; le texte lu par l'enfant sur les boutons et sur
    l'etat filtre (`nadia-prof` juge, integralement accentue et sans faute, `DEC-0002` amende).
  - Ce qui n'est pas testable par un automate et releve de la seule porte pedagogique de
    `nadia-prof` : si un enfant de CP distingue les boutons de niveau d'une sortie de jeu sur un
    ecran de jeu, et si le passage par l'ecran de confirmation est un signal suffisant de
    consequence sur la partie - objet de l'arbitrage prealable ci-dessous.
- **Porte de sortie** : les huit criteres testables ci-dessus tenus et `mvn verify` vert ;
  **arbitrage prealable de `nadia-prof` rendu sur `squad/conformite/FICHE-barre-entree-niveau.md`
  avant toute affectation en developpement (bloquant)** ; texte des boutons et de l'etat filtre juge
  par `nadia-prof` sur l'increment reellement rendu, verdict ecrit sur la meme fiche.
- **Lien decision** :
  `DEC-0032-perimetre-sprint-14-barre-entree-niveau-arbitrage-prealable-matiere-differee.md`.
- **Agent pressenti** : a affecter au planning par `sofia-sm`, apres l'arbitrage prealable de
  `nadia-prof` (bloquant) et l'avis de faisabilite de `theo-archi`.

### S14-2 - Le lien "Les jeux" de l'en-tete disparait, il ne survit qu'a l'accueil ou il ne repondait plus a rien

- **Besoin** : en tant qu'eleve de CP ou de CM1, je veux que chaque boite de l'en-tete sur laquelle
  j'appuie fasse quelque chose, afin de ne pas apprendre a distinguer une sortie qui marche d'une
  boite voisine qui ne fait rien.
- **Competence programme visee** : transverse - pas de contenu pedagogique. Cette story retire un
  element de navigation devenu sans effet ; elle ne cible aucune ligne de
  `squad/programme/MATRICE-couverture.md`.
- **Contexte et obstacle qu'elle leve** : `S14-1` est livree et fusionnee, huit de ses neuf
  conditions bloquantes tenues. La neuvieme, **BARRE-7**
  (`squad/conformite/FICHE-barre-entree-niveau.md`, section 6), reste ouverte et bloque le Done
  pedagogique de `S14-1` dans son ensemble : le lien `a.lien-accueil` de l'en-tete, libelle "Les
  jeux", n'est visible sur aucun ecran de jeu (deja masque par `ADR-0022`) et, sur l'accueil non
  filtre - l'etat de tout premier chargement - un appui dessus rend un document identique a celui
  qui l'affichait. `nadia-prof` a nomme deux issues acceptables sans trancher entre elles ; `DEC-
  0033` retient la premiere : la boite disparait, elle n'est pas remplacee par un comportement
  neuf, motif detaille dans la decision.
- **Criteres d'acceptation** :
  - Etant donne n'importe quel ecran rendu par l'application (accueil avec ou sans filtre de
    niveau, reprise d'une partie en cours, manche, echec, revelation, fin de partie, erreur,
    confirmation), quand on lit le document servi, alors aucun element ne porte plus a la fois la
    classe `lien-accueil` et le texte "Les jeux" dans l'en-tete (`nav.menu`) : cette boite est
    retiree, pas seulement masquee par une regle supplementaire qui laisserait un cas ou elle
    reapparait.
  - Etant donne l'accueil affichant le catalogue complet (etat par defaut, sans filtre de niveau),
    quand l'enfant regarde l'en-tete, alors il n'y voit que l'icone, le titre "Recreo" et les puces
    de niveau ("CP", "CM1") : aucune troisieme boite actionnable a cote d'elles.
  - Etant donne l'ecran de fin de partie et l'ecran de confirmation d'abandon, quand on compte les
    occurrences du mot "Les jeux" sur chacun, alors ce compte est inchange par cette story : une
    fois pour `sortie-pleine` sur la fin de partie (PORTE-2, `DEC-0027`), une fois pour
    `retour-accueil` sur la confirmation. Cette story ne retire que l'exemplaire de l'en-tete,
    jamais une sortie de contenu deja arbitree.
  - Ce que cette story ne tranche pas : le moyen technique du retrait - suppression de l'element du
    gabarit ou extension permanente d'une regle de masquage - et son impact sur
    `LActionNeDeclarePasMoinsQueLaSortieTest` et sur
    `EnTeteEtMenuTest.leMenuDeLEnTeteContientExactementUneEntreeVersLAccueil`, qui comptaient
    jusqu'ici ce lien dans leur population (`theo-archi`, saisine par `sofia-sm`).
  - Ce qui n'est pas testable par un automate et releve de la seule porte pedagogique de
    `nadia-prof` : que le retrait de cette boite suffise, pour un enfant de six ans, a ne plus
    hesiter entre les puces de niveau et une sortie sur l'accueil - `nadia-prof` le rejoue avec les
    huit autres conditions de `FICHE-barre-entree-niveau.md` avant de prononcer le Done pedagogique
    complet de `S14-1`.
- **Porte de sortie** : les trois criteres testables ci-dessus tenus et `mvn verify` vert. Aucun
  arbitrage prealable neuf n'est requis - `nadia-prof` a deja nomme les deux issues acceptables et
  laisse le choix a `camille-po` (`DEC-0033`) ; le Done pedagogique complet de `S14-1`, lui, reste a
  prononcer par `nadia-prof` une fois cette story fusionnee avec le reste de l'increment.
- **Lien decision** :
  `DEC-0033-barre-7-le-lien-les-jeux-de-l-en-tete-disparait-de-l-accueil.md`.
- **Agent pressenti** : a affecter au planning par `sofia-sm`, lot court du sprint 14 en cours.

## Sprint 15 - L'utilisatrice revient sur DEC-0033 apres avoir joue

**Contexte et decision de perimetre** :
`DEC-0034-utilisatrice-retablit-les-jeux-et-unifie-lapparence-des-puces.md`. L'utilisatrice a joue
l'increment de s14 (Done pedagogique VALIDE SOUS RESERVE de `S14-1`/`S14-2`,
`squad/conformite/FICHE-barre-entree-niveau.md`) et rend deux arbitrages qui reviennent sur
`DEC-0033` : le lien "Les jeux" revient dans l'en-tete dans son etat d'avant s14, et les puces de
niveau prennent son apparence sur le fond commun de l'en-tete. Les deux stories ci-dessous portent
chacun de ces arbitrages. Un troisieme retour de l'utilisatrice - les boutons de l'en-tete
disparaissent au survol - est un defaut technique deja diagnostique, hors de ce sprint tel qu'ecrit
ici : il fait l'objet d'une story distincte instruite par `theo-archi`.

### S15-1 - "Les jeux" revient dans l'en-tete, dans son etat d'avant s14

- **Besoin** : en tant qu'eleve de CP ou de CM1, je veux retrouver dans l'en-tete un bouton "Les
  jeux" qui me ramene a la liste complete de tous les jeux, comme avant, afin de revenir au
  catalogue complet meme si je suis arrive sur une liste filtree par mon niveau.
- **Competence programme visee** : transverse - pas de contenu pedagogique. Cette story ne cible
  aucune ligne de `squad/programme/MATRICE-couverture.md` ; elle reintroduit un element de
  navigation retire par `S14-2`.
- **Arbitrage deja rendu, non rouvert par cette story** (`DEC-0034`, arbitrage A) : l'utilisatrice a
  choisi de retablir ce lien dans son etat d'avant s14 - sans role nouveau, sans devenir un bouton
  d'annulation de filtre -, en connaissance du fait que cela reintroduit le defaut nomme BARRE-7 par
  `nadia-prof` (un bouton qui ne fait rien depuis l'accueil non filtre, et qui repond depuis
  l'accueil filtre). Cette story ne cherche pas a corriger ce defaut : elle reproduit fidelement le
  comportement d'avant s14, motif et historique complets dans `DEC-0034`.
- **Criteres d'acceptation** :
  - Etant donne l'ecran d'accueil (catalogue complet ou liste filtree par niveau), quand la page est
    rendue, alors l'en-tete affiche, en plus des puces de niveau, un element de role sortie libelle
    exactement "Les jeux" (`data-role="sortie"`, comme avant s14).
  - Etant donne un ecran de jeu (zone de contenu portant le marqueur que le serveur pose pour un
    ecran de jeu, quel qu'il soit - manche, echec, revelation, confirmation, fin de partie, erreur),
    quand la page ou le fragment est rendu, alors cet element "Les jeux" de l'en-tete n'est pas
    visible - masque exactement comme il l'etait avant s14 (`ADR-0022`, clause 2, forme generalisee
    de son troisieme amendement du 2026-08-17 ; le moyen technique et le marqueur retenu
    appartiennent a `theo-archi`).
  - Etant donne l'accueil affichant le catalogue complet (etat par defaut, sans filtre de niveau),
    quand l'enfant appuie sur "Les jeux", alors le document rendu est identique, texte pour texte, a
    celui qui l'affichait avant l'appui - ce critere constate sciemment le comportement que
    `nadia-prof` a nomme BARRE-7 et que l'utilisatrice a choisi de retablir, il ne le corrige pas.
  - Etant donne l'accueil affichant une liste filtree sur un niveau, quand l'enfant appuie sur "Les
    jeux", alors le catalogue complet (les onze jeux, non filtre) est affiche - comportement
    inchange par rapport a celui livre par `S14-1`.
  - Etant donne l'ecran de fin de partie et l'ecran de confirmation d'abandon, quand on compte les
    occurrences du mot "Les jeux" sur chacun, alors ce compte reste inchange par cette story : une
    fois pour `sortie-pleine` sur la fin de partie (PORTE-2, `DEC-0027`), une fois pour
    `retour-accueil` sur la confirmation. Cette story ajoute un exemplaire du mot sur l'accueil, elle
    n'en ajoute aucun ailleurs.
  - Ce que cette story ne tranche pas : le moyen technique de reintroduction du lien et de son
    masquage, et l'impact sur `LActionNeDeclarePasMoinsQueLaSortieTest` et sur
    `EnTeteEtMenuTest.leMenuDeLEnTeteContientExactementUneEntreeVersLAccueil` (`theo-archi`, saisine
    par `sofia-sm`).
  - Ce qui n'est pas testable par un automate et releve de la seule porte pedagogique de
    `nadia-prof` : elle rejugera cette story au regard de BARRE-7, qu'elle a deja tranchee dans
    l'autre sens - c'est a elle de dire ce qu'elle fait de cette condition face a un arbitrage de
    l'utilisatrice qui la surplombe.
- **Porte de sortie** : les criteres testables ci-dessus tenus et `mvn verify` vert. Le Done
  pedagogique de l'increment resultant (cette story et `S15-2`) reste a prononcer par `nadia-prof`
  sur `squad/conformite/FICHE-barre-entree-niveau.md`, qui n'est pas rouverte par cette seule
  ecriture de story.
- **Lien decision** :
  `DEC-0034-utilisatrice-retablit-les-jeux-et-unifie-lapparence-des-puces.md`.
- **Agent pressenti** : a affecter au planning par `sofia-sm`.

### S15-2 - Les puces de niveau et "Les jeux" partagent la meme apparence, la bande sombre de l'en-tete disparait

- **Besoin** : en tant qu'eleve de CP ou de CM1, je veux que les boutons de l'en-tete se ressemblent
  tous sur le meme fond, afin de ne pas etre surpris par une bande de couleur qui ne veut rien dire.
- **Competence programme visee** : transverse - pas de contenu pedagogique. Cette story change
  l'apparence de l'en-tete ; elle ne cible aucune ligne de `squad/programme/MATRICE-couverture.md`.
- **Arbitrage deja rendu, non rouvert par cette story** (`DEC-0034`, arbitrage B) : l'utilisatrice a
  choisi que les puces "CP" et "CM1" prennent la meme apparence que "Les jeux", sur le fond commun
  de l'en-tete, plutot que sur une bande distincte plus sombre. Elle tranche l'**apparence** des
  trois boutons ; elle ne tranche pas leur **disposition** en un ou deux rangs, question routee a
  `ines-design` puis a `nadia-prof` (voir `DEC-0034` pour le detail, dont la contradiction avec
  BARRE-9 et avec l'arbitrage prealable du 2026-08-17 sur la separation des rangs).
- **Criteres d'acceptation** :
  - Etant donne l'en-tete rendu sur un ecran ou les puces de niveau et "Les jeux" sont visibles en
    meme temps (l'accueil), quand on compare les composantes de boite declarees des trois elements a
    leur etat inactif (couleur de fond, couleur de texte, couleur de bordure), alors les trois
    partagent les memes valeurs de fond et de texte : aucune des trois boites n'est posee sur un
    fond distinct du reste de l'en-tete.
  - Etant donne l'en-tete rendu sur n'importe quel ecran qui montre au moins un des trois elements,
    quand on mesure les bandes de fond de l'en-tete de haut en bas, alors il n'existe plus qu'une
    seule bande de fond : la bande sombre distincte mesuree par `nadia-prof` a 66 px (accueil, manche,
    fin de partie) ou a 16 px (confirmation, erreur) disparait.
  - Etant donne l'ecran de confirmation et l'ecran d'erreur (ecrans ou les puces sont masquees, par
    BARRE-1 et BARRE-2), quand on mesure l'en-tete, alors aucune bande vide ne subsiste a
    l'emplacement ou les puces se seraient affichees - ceci ferme la reserve **S14-A** de
    `nadia-prof`.
  - Ce que cette story ne tranche pas : la disposition en un ou deux rangs des trois boutons a
    360 px, sous la contrainte de gabarit deja mesuree par `nadia-prof` - "Les jeux" 109 x 53 px, les
    puces 56 x 50 px et 74 x 50 px (`ines-design` propose, `nadia-prof` juge) ; aucune de ces deux
    dispositions n'est imposee ni exclue par les criteres ci-dessus.
  - Ce qui n'est pas testable par un automate et releve de la seule porte pedagogique de
    `nadia-prof` : elle rejugera cette story au regard de BARRE-9, qu'elle a deja tranchee dans
    l'autre sens - c'est a elle de dire ce qu'elle fait de cette condition, y compris si elle
    souhaite la reformuler plutot que la lever purement et simplement.
- **Porte de sortie** : les criteres testables ci-dessus tenus, `mvn verify` vert, disposition en un
  ou deux rangs proposee par `ines-design` sur la contrainte de gabarit rappelee ci-dessus. Le Done
  pedagogique de l'increment resultant (cette story et `S15-1`) reste a prononcer par `nadia-prof`
  sur `squad/conformite/FICHE-barre-entree-niveau.md`.
- **Lien decision** :
  `DEC-0034-utilisatrice-retablit-les-jeux-et-unifie-lapparence-des-puces.md`.
- **Agent pressenti** : a affecter au planning par `sofia-sm`, apres proposition de disposition
  d'`ines-design`.

### S15-RETRO-1 - "Les jeux" reste visible dans l'en-tete pendant une manche en cours (regularisation retroactive)

- **Story retroactive** : cette story documente, apres coup, l'arbitrage C de `DEC-0035`, deja
  instruit, code et fusionne avant qu'elle ne soit ecrite. Elle ne rouvre aucun arbitrage et
  n'ajoute rien au comportement livre. Elle comble l'ecart signale par `DEC-0035` elle-meme :
  aucune story de backlog ne portait cet arbitrage.
- **Besoin** : en tant qu'eleve de CP ou de CM1 en train de jouer une manche, je veux voir a tout
  moment un chemin vers "Les jeux" dans l'en-tete, afin de pouvoir changer de jeu sans etre bloque
  sur l'ecran de manche.
- **Competence programme visee** : transverse - pas de contenu pedagogique.
- **Criteres d'acceptation** (constates sur l'increment fusionne `b109f98`, non a redemontrer) :
  - Etant donne l'ecran de manche, quand il est rendu, alors l'element de role sortie "Les jeux"
    de l'en-tete est visible - ce qui renverse, sur arbitrage de l'utilisatrice, celui du
    2026-08-07 (S6-9, `ADR-0022` point 3).
  - Etant donne l'ecran de confirmation d'abandon ou l'ecran de fin de partie, quand il est rendu,
    alors l'element "Les jeux" de l'en-tete n'est pas visible - chacun de ces deux ecrans porte
    deja sa propre sortie, jamais deux a la fois.
  - Etant donne n'importe quel ecran assemble par le serveur, quand on compte les elements de role
    sortie visibles simultanement, alors ce compte ne depasse jamais un.
- **Porte de sortie** : **deja franchie**. Done pedagogique **VALIDE SOUS RESERVE** prononce par
  `nadia-prof` sur l'increment `b109f98` (`squad/conformite/FICHE-barre-entree-niveau.md`, section
  "Done pedagogique du 2026-08-18 (s15), cloture"). Une reserve non bloquante nait de cet
  arbitrage - nommee **S15-C** par `nadia-prof` dans cette meme fiche, a ne pas confondre avec
  l'identifiant de cette story - et reste ouverte, sans action produit distincte a ce stade. Rien
  a planifier.
- **Lien decision** : `DEC-0035`, arbitrage C ; note du 2026-08-18 sous `DEC-0027` ; note du
  2026-08-18 sous `DEC-0034`.
- **Agent pressenti** : aucun - deja livre.

### S15-RETRO-2 - Un seul rang dans l'en-tete a partir de 470 px (regularisation retroactive)

- **Story retroactive** : documente l'arbitrage D de `DEC-0035`, deja instruit, code et fusionne.
  Elle ne rouvre aucun arbitrage.
- **Besoin** : en tant qu'eleve de CP ou de CM1, je veux que l'en-tete tienne sur une seule ligne
  des que mon ecran est assez large, afin de voir d'un coup d'oeil l'icone, le titre, les niveaux
  et "Les jeux".
- **Competence programme visee** : transverse - pas de contenu pedagogique.
- **Criteres d'acceptation** (constates sur l'increment fusionne, non a redemontrer) :
  - Etant donne un ecran d'au moins 470 px de large, quand l'en-tete est rendu, alors l'icone, le
    titre, les puces CP et CM1 et "Les jeux" apparaissent sur un seul rang.
  - Etant donne le plancher de largeur du produit (360 px), quand l'en-tete est rendu, alors il
    peut se replier sur deux rangs, mais aucun element ni aucune partie de texte ne devient
    inatteignable ni ne deborde du document (`scrollWidth` egal a `clientWidth`).
  - Etant donne les six largeurs verifiees par `nadia-prof` (300, 320, 345, 346, 360 et 470 px),
    quand le document est mesure, alors le debordement horizontal est nul aux six.
- **Porte de sortie** : **deja franchie**. Done pedagogique **VALIDE** prononce par `nadia-prof`
  sur l'increment `65da40e` (`squad/conformite/FICHE-barre-entree-niveau.md`, section "Done
  pedagogique du 2026-08-18 (s15), second lot"), sept conditions S15-1 a S15-7 tenues. Rien a
  planifier.
- **Lien decision** : `DEC-0035`, arbitrage D.
- **Agent pressenti** : aucun - deja livre.

### S15-RETRO-3 - Une couleur marque la puce de niveau choisie, eteinte des le demarrage du jeu (regularisation retroactive)

- **Story retroactive** : documente l'arbitrage E de `DEC-0035` et le correctif de regression qui
  l'a suivi, deja instruits, codes et fusionnes. Elle ne rouvre aucun arbitrage.
- **Besoin** : en tant qu'eleve de CP ou de CM1, je veux voir laquelle des deux puces de niveau
  j'ai choisie tant que je n'ai pas encore commence a jouer, afin de savoir sur quelle liste je me
  trouve.
- **Competence programme visee** : transverse - pas de contenu pedagogique.
- **Criteres d'acceptation** (constates sur l'increment fusionne, non a redemontrer) :
  - Etant donne l'accueil affichant une liste filtree par niveau, quand la puce du niveau choisi
    est rendue, alors elle porte un etat visuellement distinct de son etat inactif (inversion
    pleine/contour), mesure a un contraste d'au moins 4,5:1.
  - Etant donne ce meme ecran, quand on lit le texte affiche au-dessus de la liste, alors une
    phrase nomme le filtre en cours ("Pour le CP" ou "Pour le CM1"), independamment de la couleur.
  - Etant donne une partie qui demarre depuis une liste filtree, quand l'ecran de jeu est rendu,
    alors aucune puce ne porte plus la couleur d'etat actif.
  - Etant donne un retour au catalogue complet depuis "Les jeux", une confirmation d'abandon ou
    une annulation, quand l'accueil est de nouveau rendu sans filtre, alors aucune puce ne porte
    la couleur d'etat actif - correction de la regression trouvee par l'utilisatrice sur les trois
    routes concernees, deja livree.
  - Etant donne un chargement de page, quand l'accueil se rend, alors l'etat actif ne survit
    jamais a lui seul a ce chargement (non-persistance).
- **Porte de sortie** : **deja franchie**. Done pedagogique **VALIDE** prononce par `nadia-prof`
  sur l'increment `65da40e`, puis **VALIDE SOUS RESERVE** sur le correctif de regression
  (`squad/conformite/FICHE-barre-entree-niveau.md`, "Statut global a la cloture de s15"). Une
  reserve non bloquante nait de ce lot - nommee **S15-D** par `nadia-prof` dans cette meme fiche,
  absence de phrase sur la route `/niveaux/{niveau}/confirmer` - et reste ouverte, hors perimetre
  de cette story. Rien a planifier.
- **Lien decision** : `DEC-0035`, arbitrage E.
- **Agent pressenti** : aucun - deja livre.

## Sprint 16 - Deux defauts trouves par l'utilisatrice en rejouant l'application livree de s15

### S16-1 - Correction : le message de partie interrompue ne s'affiche que pour une interruption plausible

- **Besoin** : en tant qu'enfant qui revient sur `recreo` apres une pause ordinaire (l'ecran a ete
  range, ou le serveur a simplement eu le temps de m'oublier), je veux retrouver directement la
  liste des jeux, afin de ne pas croire qu'une partie s'est arretee alors que je n'ai rien
  interrompu.
- **Competence programme visee** : transverse - pas de contenu pedagogique. Comportement
  generique de l'accueil, deja pose par `S1-4`.
- **Ce que cette story ne rouvre pas** : le texte du message ("Ta partie s'est arretee. Choisis
  un nouveau jeu !") reste inchange, deja valide par `nadia-prof`
  (`squad/conformite/FICHE-bandit-manchot-additions-cp.md`). Cette story corrige uniquement le
  moment ou il s'affiche.
- **Criteres d'acceptation** :
  - Etant donne le marqueur que le serveur pose pour signaler une partie en cours, quand on
    observe sa duree de validite declaree, alors elle n'excede jamais la duree d'inactivite
    maximale configuree pour la session HTTP a laquelle il se rapporte.
  - Etant donne une session qui a disparu par la seule expiration d'inactivite (aucun
    redemarrage, aucun plantage du serveur), quand l'enfant revient sur "/", alors l'accueil
    affiche directement le catalogue complet, sans le message "Ta partie s'est arretee. Choisis
    un nouveau jeu !" - ce cas se comporte comme une premiere visite.
  - Etant donne une session detruite par un redemarrage ou un arret du serveur survenu pendant
    que le marqueur de partie en cours etait encore valide au sens du premier critere, quand
    l'enfant revient sur "/", alors le message "Ta partie s'est arretee. Choisis un nouveau jeu !"
    continue de s'afficher exactement comme aujourd'hui, au-dessus de la liste des jeux.
  - Etant donne un enfant qui recharge la page pendant que sa session HTTP est encore valide,
    quand la page se recharge, alors il retrouve directement l'etat de sa manche en cours, sans
    aucun message affiche - comportement deja acquis par `S1-4`, non modifie par cette story.
  - Ce que cette story ne tranche pas : le moyen technique qui lie la duree de vie du marqueur a
    la duree d'inactivite maximale de la session (`theo-archi`, DEC-0036).
  - Ce qui n'est pas testable par un automate et releve de la seule porte pedagogique de
    `nadia-prof` : elle rejoue les deux scenarios nommes aux deuxieme et troisieme criteres sur
    l'increment livre et consigne son constat dans
    `squad/conformite/FICHE-bandit-manchot-additions-cp.md` - elle seule peut confirmer que le
    message ne se declenche plus que pour l'evenement qu'elle a deja valide.
- **Porte de sortie** : les quatre premiers criteres testables ci-dessus tenus et `mvn verify`
  vert, puis constat ecrit de `nadia-prof` sur les deux scenarios rejoues.
- **Lien decision** :
  `DEC-0036-message-de-partie-interrompue-borne-a-une-interruption-plausible.md`.
- **Agent pressenti** : a affecter au planning par `sofia-sm`.

### S16-2 - Correction : l'adresse affichee correspond au catalogue complet apres un retour depuis "Les jeux"

- **Besoin** : en tant qu'eleve, je veux que l'adresse affichee par mon navigateur corresponde a
  ce que je vois quand j'appuie sur "Les jeux", afin que si je recharge cette page ou que je la
  retrouve plus tard, je revoie exactement ce que j'avais sous les yeux, et pas une liste filtree
  sur un niveau que je ne suis plus.
- **Competence programme visee** : transverse - pas de contenu pedagogique. Navigation et
  ergonomie de l'en-tete.
- **Ce que cette story ne rouvre pas** : le mot "Les jeux" ne change pas, et le comportement de
  confirmation d'une partie en cours (`DEC-0026`, `ADR-0038`, `ADR-0042`) n'est pas retire.
- **Criteres d'acceptation** :
  - Etant donne l'accueil affichant une liste filtree sur un niveau (par exemple CM1, cinq jeux)
    et aucune partie en cours, quand l'enfant active l'element "Les jeux" de l'en-tete, alors
    l'adresse affichee par le navigateur devient celle du catalogue complet, coherente avec les
    onze jeux desormais affiches - elle ne reste plus celle du niveau quitte.
  - Etant donne l'adresse obtenue au critere precedent, quand l'enfant la recharge (touche
    d'actualisation, nouvel onglet sur la meme adresse, ou signet), alors le catalogue complet
    des onze jeux s'affiche a l'identique.
  - Etant donne un ecran de jeu ou "Les jeux" est visible et une partie en cours, quand l'enfant
    active "Les jeux", alors l'ecran de confirmation d'abandon s'affiche exactement comme avant
    cette story - aucune partie n'est quittee sans confirmation explicite.
  - Etant donne l'ecran de confirmation ouvert par "Les jeux" pendant une partie en cours, quand
    l'enfant confirme vouloir partir, alors il obtient le meme resultat qu'aujourd'hui (catalogue
    complet, partie abandonnee) - cette story ne change que l'adresse affichee quand aucune
    partie n'etait en cours.
  - Ce que cette story ne tranche pas : le moyen technique par lequel l'element "Les jeux" devient
    une navigation complete tout en continuant de porter l'ouverture de l'ecran de confirmation
    quand une partie est en cours (`hx-post="/retour"` porte aujourd'hui cette double
    responsabilite - `theo-archi`, saisine par `sofia-sm`).
  - Ce qui n'est pas testable par un automate et releve de la seule porte pedagogique de
    `nadia-prof` : elle rejoue l'increment et confirme par un constat ecrit dans
    `squad/conformite/FICHE-barre-entree-niveau.md` qu'aucune des conditions deja tenues sur cet
    ecran (marqueur `ecran-porte-sa-sortie`, BARRE, PORTE, S15-1 a S15-9) n'est degradee par le
    changement de mecanisme de navigation.
- **Porte de sortie** : les quatre premiers criteres testables ci-dessus tenus et `mvn verify`
  vert, puis constat ecrit de `nadia-prof`.
- **Lien decision** : aucune - correction directe d'un ecart de navigation non arbitre, pas un
  renversement de decision produit. Contrainte technique routee a `theo-archi` ci-dessus.
- **Agent pressenti** : a affecter au planning par `sofia-sm`.

### S16-3 - Une puce de niveau active garde son apparence sous le pointeur

- **Besoin** : en tant qu'eleve de CP ou de CM1, je veux que la puce du niveau que j'ai choisi
  garde son apparence si ma souris passe dessus ou si je clique dessus par erreur, afin de
  continuer a voir clairement quel niveau est actif.
- **Competence programme visee** : transverse - pas de contenu pedagogique. Garde-fou de
  non-regression sur un arbitrage deja rendu.
- **Ce que cette story ne rouvre pas** : la forme retenue pour l'etat sous pointeur est deja
  arbitree par `nadia-prof` le 2026-08-18 (variante neutre, `squad/conformite/FICHE-barre-entree-
  niveau.md`, section 4) : rien ne change sous le pointeur, exactement comme avant l'entree de la
  bibliotheque tierce en s03 (`ADR-0046`).
- **Criteres d'acceptation** :
  - Etant donne la puce du niveau actif (etat plein), quand un pointeur la survole, l'active ou la
    prend au clavier (`:hover`, `:active`, `:focus-visible`), alors ses couleurs de texte, de fond
    et de bordure restent identiques a son etat actif au repos.
  - Etant donne la puce du niveau inactif (etat contour), quand un pointeur la survole, l'active
    ou la prend au clavier, alors ses couleurs de texte, de fond et de bordure restent identiques
    a son etat inactif au repos - garantie deja tenue par `ADR-0046` pour les huit boutons traites
    en s15, etendue ici a la variante active introduite apres cet ADR.
  - Ce que cette story ne tranche pas : le moyen technique (regle CSS composee, nombre de regles),
    sous les quatre exigences deja ecrites par `theo-archi` dans `ADR-0046`, section "Ce que
    j'exige du lot qui corrigera" (six regles au plus, contour non facultatif, aucun `!important`,
    forme neutre uniquement).
  - Ce qui n'est pas testable par un automate et releve de la seule porte pedagogique de
    `nadia-prof` : elle rejoue et confirme, dans `squad/conformite/FICHE-barre-entree-niveau.md`,
    qu'aucun changement visible n'apparait sous le pointeur - constat qu'elle a deja signale ne
    pouvoir produire qu'a titre de jugement de geometrie, faute d'outillage capable de simuler un
    survol (voir la dependance nommee dans `DEC-0037`).
- **Porte de sortie** : les deux premiers criteres tenus, verifies par le garde-fou derive
  d'`ADR-0046` (mutation testee), et `mvn verify` vert, puis constat ecrit de `nadia-prof`.
- **Lien decision** : `ADR-0046-un-etat-interactif-tiers-oblige-la-feuille-maison-a-le-
  declarer.md` ; arbitrage de `nadia-prof` du 2026-08-18 (`FICHE-barre-entree-niveau.md`).
- **Agent pressenti** : a affecter au planning par `sofia-sm`.

### S16-4 - Une proposition deja essayee garde son apparence sous le pointeur

- **Besoin** : en tant qu'eleve qui joue a un jeu construit sur la mecanique du bandit manchot, je
  veux qu'une proposition que j'ai deja essayee garde son apparence si mon doigt ou ma souris
  passe dessus, afin de continuer a la reconnaitre facilement parmi les autres.
- **Competence programme visee** : transverse - pas de contenu pedagogique. Cette story touche un
  style partage par tous les jeux construits sur la mecanique du bandit manchot, pas seulement
  l'en-tete ou vit l'arbitrage qui l'a tranchee.
- **Ce que cette story ne rouvre pas** : `nadia-prof` a retenu la variante neutre pour
  `.proposition.deja-essayee` le 2026-08-18, sous deux conditions (`FICHE-barre-entree-niveau.md`,
  section 4).
- **Criteres d'acceptation** :
  - Etant donne une proposition marquee "deja essayee", sur chacune de ses quatre positions
    cycliques de fond, quand un pointeur la survole ou l'active, alors elle garde la couleur de
    fond de sa position (condition **S15-8**).
  - Etant donne cette meme proposition, quand un pointeur la survole ou l'active, alors le trait
    qui la marque comme deja essayee garde son epaisseur et sa teinte (condition **S15-9**).
  - Ce que cette story ne tranche pas : le moyen technique, sous les memes quatre exigences
    d'`ADR-0046` que `S16-3`.
  - Ce qui n'est pas testable par un automate et releve de la seule porte pedagogique de
    `nadia-prof` : elle rejoue et confirme sur les quatre positions et sur plusieurs items -
    a la cloture de s15 elle n'avait pu verifier qu'une position et un seul item, et l'a
    elle-meme note comme degrade.
- **Porte de sortie** : les deux premiers criteres tenus, `mvn verify` vert, puis constat ecrit de
  `nadia-prof` sur les quatre positions.
- **Lien decision** : `ADR-0046-un-etat-interactif-tiers-oblige-la-feuille-maison-a-le-
  declarer.md` ; arbitrage de `nadia-prof` du 2026-08-18 (`FICHE-barre-entree-niveau.md`).
- **Agent pressenti** : a affecter au planning par `sofia-sm`.

### S16-5 - Tout libelle rendu par le modele est soumis au meme garde-fou d'accentuation que le texte ecrit en clair

- **Besoin** : en tant qu'eleve, je veux que tout mot ecrit a mon intention sur l'ecran soit
  toujours correctement accentue, meme s'il vient d'un contenu ou d'un catalogue ajoute plus tard
  et non d'un gabarit ecrit a la main, afin de ne jamais lire un mot mal orthographie.
- **Competence programme visee** : transverse - pas de contenu pedagogique. Garde-fou de
  redaction (`DEC-0002`, amendement du 2026-08-04 : tout texte lu par l'enfant est integralement
  accentue).
- **Ce que cette story ne fait pas** : elle ne rend aucun rendu different pour l'enfant. Le texte
  actuel ("CP", "CM1") reste affiche a l'identique.
- **Criteres d'acceptation** :
  - Etant donne un libelle produit par le contenu ou le catalogue et pose dans un gabarit par une
    donnee du modele (`th:text`/`th:utext`) plutot qu'ecrit en clair, quand ce libelle contient au
    moins une lettre qui devrait porter un accent en francais correct et ne le porte pas, alors un
    garde-fou fait echouer la construction, en nommant le libelle fautif - meme garantie que celle
    que `TexteAccentueDesGabaritsTest` porte deja pour le texte ecrit en clair.
  - Etant donne les libelles de niveau actuels ("CP", "CM1"), quand ce garde-fou s'execute, alors
    ils restent verts, avec une exemption ecrite explicitement dans le code du garde-fou, sa
    raison (aucune lettre accentuable dans ces deux chaines) et sa frontiere (elle cesse de
    s'appliquer des qu'un niveau futur porte une lettre accentuable) - demande exacte de
    `nadia-prof` du 2026-08-18 (`FICHE-barre-entree-niveau.md`, section 5).
  - Ce que cette story ne tranche pas : si la nomenclature de matiere, le jour ou elle s'affichera,
    portera une valeur interne elle-meme accentuee ou un libelle d'affichage distinct - question
    d'architecture encore ouverte de `DEC-0032`, point 2, non fermee par ce garde-fou.
- **Porte de sortie** : les criteres testables ci-dessus tenus et `mvn verify` vert. Aucun rendu ne
  changeant pour l'enfant, aucun constat de `nadia-prof` n'est attendu par construction ; elle
  peut neanmoins le demander si elle le juge necessaire.
- **Lien decision** : `DEC-0032-perimetre-sprint-14-barre-entree-niveau-arbitrage-prealable-
  matiere-differee.md`, point 2 ; demande de `nadia-prof` du 2026-08-18
  (`FICHE-barre-entree-niveau.md`, section 5).
- **Agent pressenti** : a affecter au planning par `sofia-sm`.

### S16-6 - La phrase qui nomme le filtre en cours apparait aussi apres un changement de niveau confirme pendant une partie

- **Besoin** : en tant qu'eleve de CP ou de CM1 qui change de niveau alors qu'une partie etait en
  cours, je veux qu'une phrase me dise sur quelle liste je me trouve apres avoir confirme, afin de
  savoir ce que je vois sans avoir a le deviner.
- **Competence programme visee** : transverse - pas de contenu pedagogique.
- **Ce que cette story ne rouvre pas** : elle applique a une route ou elle manque encore la meme
  condition que `S15-2`/`S14-1` appliquent deja a l'accueil (`nadia-prof`, condition **S15-2**,
  couplage phrase/couleur).
- **Criteres d'acceptation** :
  - Etant donne une partie en cours et un changement de niveau confirme par l'enfant (route
    `/niveaux/{niveau}/confirmer`), quand l'accueil filtre qui en resulte est rendu, alors une
    phrase nomme le niveau affiche ("Pour le CP" ou "Pour le CM1"), au-dessus de la liste des
    jeux, exactement comme sur l'accueil filtre atteint sans partie en cours.
  - Etant donne ce meme ecran, quand on compare la puce de niveau active a la phrase affichee,
    alors les deux nomment le meme niveau - aucune des deux ne peut apparaitre sans l'autre
    (reserve **S15-D** fermee).
- **Porte de sortie** : les deux criteres testables ci-dessus tenus et `mvn verify` vert, puis
  constat ecrit de `nadia-prof` dans `squad/conformite/FICHE-barre-entree-niveau.md`.
- **Lien decision** : reserve **S15-D** de `nadia-prof`, nommee dans `squad/sprints/s15-cinq-
  arbitrages-de-l-en-tete/RAPPORT.md`.
- **Agent pressenti** : a affecter au planning par `sofia-sm`.

## Sprint 17 - Reserve S16-A : un enfant au clavier ne voit nulle part ou il est

### S17-1 - Un enfant qui navigue au clavier voit quel pressable est actuellement selectionne

- **Besoin** : en tant qu'eleve qui joue au clavier - parce que je ne peux pas tenir un
  telephone, parce qu'un adulte m'aide, ou parce que c'est l'ordinateur de la classe - je veux
  voir clairement laquelle des propositions ou des puces est actuellement selectionnee quand je
  me deplace avec les touches, afin de savoir ou j'en suis avant d'appuyer, sans avoir a deviner.
- **Competence programme visee** : transverse - pas de contenu pedagogique.
- **Ce que cette story ne rouvre pas** : les conditions **S15-8** et **S15-9** (couleur et trait
  d'une proposition deja essayee), constatees TENUES aux quatre positions, y compris a l'etat de
  focus clavier (`nadia-prof`, `squad/conformite/FICHE-barre-entree-niveau.md`, 2026-08-19). Cette
  story ne porte que sur l'**indicateur de focus lui-meme** : le fait qu'un element recoive
  visiblement le focus, pas l'apparence qu'il garde une fois qu'il l'a.
- **Contexte du defaut, mesure et non suppose** (`nadia-prof`, reserve **S16-A**, 2026-08-19,
  `FICHE-barre-entree-niveau.md`) : sur les sept elements pressables d'un ecran de manche (les
  deux puces de niveau, la sortie d'en-tete, les trois propositions et la proposition marquee),
  le contour visible passe de 3 px a zero au moment ou l'element recoit le focus clavier, et
  l'ombre portee qui devait le remplacer ne vaut rien, avant comme apres. Cause verifiee dans la
  feuille tierce servie : la regle de focus retire le contour natif (`outline: 0`) et le
  remplace par une ombre dont la valeur reference une variable de couleur que seules dix-sept
  classes de variante de la bibliotheque definissent - qu'aucun gabarit de ce produit ne porte.
  L'ombre retombe donc a rien et le contour a deja ete retire par la meme regle : aucun des sept
  pressables n'affiche le moindre indicateur de focus.
- **Criteres d'acceptation** :
  - Etant donne chacun des sept pressables d'un ecran de manche (population ci-dessus), quand cet
    element recoit le focus clavier, alors au moins une propriete de style visible calculee
    differe entre son etat de repos et son etat de focus - methode et sonde deja produites par
    `nadia-prof` le 2026-08-19 (appel du focus, verification de l'etat de focus clavier, lecture
    du style calcule).
  - Etant donne cette propriete qui differe, quand on la nomme, alors elle n'est pas
    exclusivement une couleur - repris au mot pres de `nadia-prof`, 2026-08-19,
    `FICHE-barre-entree-niveau.md`, section 4 : *"un indicateur visible qui ne soit pas seulement
    une couleur"*.
  - Ce que cette story ne tranche pas : le moyen technique (variable CSS reparee, indicateur
    maison neuf porte par la feuille maison, autre forme), sous la responsabilite de
    `theo-archi`, et sous les memes principes qu'`ADR-0046` a deja appliques a `S16-3`/`S16-4`.
  - Ce qui n'est pas testable par un automate et releve de la seule porte pedagogique de
    `nadia-prof` : elle rejoue chacun des sept pressables au clavier sur un ecran de manche et
    confirme, dans `squad/conformite/FICHE-barre-entree-niveau.md`, que l'indicateur est present
    et visible - constat qu'elle vient d'etablir pouvoir produire (elle ne le pouvait pas avant
    le 2026-08-19, faute d'avoir essaye d'atteindre l'etat de focus clavier).
- **Ce qui doit etre arbitre avant tout code** : la forme de l'indicateur (couleur, epaisseur,
  style). A la difference de `S16-3`/`S16-4`, la forme neutre ("rien ne change sous le pointeur")
  ne s'applique pas ici : il n'existe aujourd'hui aucun indicateur a restaurer, donc toute forme
  proposee est une apparence neuve, ce qu'`ADR-0046`, point 4, reserve a un arbitrage prealable
  de `nadia-prof` sur une proposition d'`ines-design`. `nadia-prof` l'a ecrit elle-meme : *"je
  poserai mes conditions sur la forme quand elle me sera proposee ; je ne les invente pas
  d'avance"*.
- **Relation avec la reserve F11 (ecoute reelle de lecteur d'ecran)** : je garde les deux
  distinctes, je ne les rapproche pas. F11 porte sur ce qu'un enfant **entend** au lecteur
  d'ecran et reste bloquee par une capacite que personne dans la squad ne peut produire (aucune
  ecoute reelle tentee a ce jour). S16-A porte sur ce qu'un enfant **voit** au clavier, sans
  lecteur d'ecran, et `nadia-prof` vient de montrer qu'elle peut la mesurer elle-meme et que
  `theo-archi`/`ines-design` peuvent la corriger avec les moyens deja au depot. Traiter les deux
  comme un seul sujet ferait dependre une correction possible aujourd'hui (S16-A) d'une capacite
  qui ne l'est pas (F11), et inversement laisserait croire que fermer S16-A repond a F11 alors
  qu'un enfant qui n'entend pas l'ecran n'est pas aide par un contour qu'il ne voit pas non plus.
- **Porte de sortie** : la forme arbitree prealablement par `nadia-prof`, les deux criteres
  testables ci-dessus tenus sur la forme retenue, `mvn verify` vert, puis constat ecrit de
  `nadia-prof` sur les sept pressables d'un ecran de manche.
- **Lien decision** : reserve **S16-A** de `nadia-prof`, `squad/conformite/FICHE-barre-entree-
  niveau.md`, section 4 (2026-08-19) ; troisieme manifestation nommee par elle de la meme famille
  de defaut que celle qui a motive `ADR-0046` (2026-08-17, en-tete au survol de la souris).
- **Agent pressenti** : a affecter au planning par `sofia-sm`, apres arbitrage prealable
  d'`ines-design` et de `nadia-prof` sur la forme, et avis de faisabilite de `theo-archi`.

### Story de correction - Le bouton nomme a la revelation est absent de l'ordre de tabulation (`S17-C`, `M13`, anterieure a s17)

**Entre au perimetre propose de s18 (`DEC-0041`, camille-po, 2026-08-22).** Restee non affectee a
l'issue de s17 (`DEC-0039`, amendement du 2026-08-19), cette story est reclassee : `DEC-0040`
retire le telephone du perimetre de support, ce qui fait du clavier un chemin de jeu ordinaire sur
le seul support qui reste vise, et le meme defaut vient de se reproduire sur `cm1-synonymes`
(`squad/conformite/FICHE-bandit-manchot-synonymes-cm1.md`, verdict du 2026-08-22) sans etre
reprononce. `nadia-prof` y ajoute un fait qui nuance sa severite sans la lever : une sortie au
clavier existe a la revelation, l'enfant n'est pas enferme, mais il doit presser volontairement une
mauvaise reponse pour que la bonne lui soit rendue. Ses criteres d'acceptation, ecrits ci-dessous,
ne changent pas.

`nadia-prof`, en sondant `S17-1` apres livraison, mesure qu'a la
revelation d'une manche, au clavier, le jeu ecrit "C'est auront. Appuie sur auront." et pose le
focus sur ce bouton avec son anneau - mais que ce bouton est **le seul absent de l'ordre de
tabulation** (`squad/conformite/FICHE-barre-entree-niveau.md`, reserve `S17-C`, 2026-08-19).
**Ce defaut date de `M13` (s10) et n'est pas cause par `S17-1`** : l'indicateur de focus l'a
seulement rendu visible pour la premiere fois. `camille-po` tranche qu'il n'entre pas au perimetre
de s17 (`DEC-0039`, amendement du 2026-08-19) : le defaut est anterieur de sept sprints, son
correctif touche `static/js/recreo.js` (dette 4 de `CLAUDE.md`, "le comportement des mecaniques
loge dans le JavaScript client, que rien ne testait") sans garde-fou pret a l'accueillir
(`RecreoJsDeplaceLeFocusTest` ne modelise aujourd'hui aucun attribut `tabindex`), et son cout n'est
pas cerne sur un sprint deja charge.

- **Besoin** : en tant qu'eleve qui navigue au clavier, je veux pouvoir revenir sur le bouton que
  le jeu vient de me nommer a la revelation de la bonne reponse, afin de pouvoir appuyer dessus
  sans devoir reprendre la partie a la souris.
- **Competence programme visee** : transverse - pas de contenu pedagogique.
- **Cause, lue dans le depot et non devinee** (`nadia-prof`) : `static/js/recreo.js` pose
  `tabindex="-1"` sur sa cible **meme quand cette cible est deja focalisable**. L'attribut n'est
  necessaire que pour les cibles qui ne le sont pas nativement (l'enonce, le signal). Meme effet,
  moins grave, apres chaque echec : la proposition qu'on vient de presser sort aussi du cycle
  jusqu'au rendu suivant.
- **Criteres d'acceptation**, repris au mot pres de la mesure et de la verification de fermeture de
  `nadia-prof` :
  - Etant donne l'ecran de revelation d'une manche, quand on y traverse l'ensemble des elements a
    la tabulation, alors le bouton nomme par le message de revelation ("Appuie sur `<reponse>`.")
    fait partie de l'ordre de tabulation rencontre - verification de fermeture ecrite par
    `nadia-prof` au mot pres.
  - Etant donne une proposition pressee apres une reponse fautive, quand l'ecran se recharge sur la
    meme manche, alors cette proposition n'est pas davantage exclue de l'ordre de tabulation que
    tout autre element focalisable de l'ecran.
  - Etant donne toute cible du script de focus qui est deja nativement focalisable (un bouton, par
    exemple), quand le script lui pose le focus, alors il ne lui ajoute pas `tabindex="-1"` -
    l'attribut reste reserve aux cibles qui en ont besoin pour devenir focalisables (l'enonce, le
    signal).
  - Ce qui n'est pas testable par un automate a ce jour, et releve de la seule sonde de
    `nadia-prof`, faute de garde-fou executable (section 7 de sa sonde, 2026-08-19, "aucun
    garde-fou de la CI ne mesure un etat de focus... c'est moi le garde-fou") : que l'ordre de
    tabulation reel, mesure au clavier dans un vrai navigateur, rencontre effectivement le bouton
    nomme, sur l'ecran de revelation et sur l'ecran de reprise.
- **Ce que cette story ne tranche pas** : le mecanisme du correctif dans `static/js/recreo.js`, et
  si un garde-fou executable peut naitre en etendant le faux DOM de `RecreoJsDeplaceLeFocusTest`
  pour modeliser `tabindex`, ou si cette clause reste verifiee au clavier reel par `nadia-prof`
  seule, comme la clause 1 de `M13` l'est deja - routes a `theo-archi`.
- **Lien decision** : reserve **`S17-C`** de `nadia-prof`,
  `squad/conformite/FICHE-barre-entree-niveau.md`, section "Sonde post-livraison du 2026-08-19
  (s17) sur S17-1" ; `DEC-0039`, amendement du 2026-08-19 ("`S17-C` n'entre pas au perimetre de
  s17").
- **Agent pressenti** : a affecter au planning par `sofia-sm`, apres avis de `theo-archi` sur le
  mecanisme et son garde-fou.

### Note du 2026-08-22 (camille-po, s18) - le motif de report cite ci-dessus est faux sur un point verifiable, sans consequence sur le report lui-meme

`theo-archi` (`squad/sprints/s18-fermer-avant-ouvrir/avis-faisabilite.md`, section 4.1) verifie que
*"`RecreoJsDeplaceLeFocusTest` ne modelise aujourd'hui aucun attribut `tabindex`"*, cite plus haut
comme un des motifs du report a s17, est faux : le faux DOM de ce test porte deja une table
d'attributs et assertionne `tabindex` sur au moins un cas. Ce qu'il ne modelise pas, et qui change
le mecanisme du correctif, est **la focalisabilite native d'un element** (savoir qu'un `<button>`
est focalisable et qu'un `<p>` ne l'est pas). Je ne reecris pas le paragraphe ci-dessus : le motif
de report qu'il donne reste lisible tel qu'ecrit le 2026-08-19, avec sa conclusion juste (cout non
cerne) construite sur une cause partiellement fausse. **Cela ne rouvre rien de ma decision** : le
classement de `S17-C` au perimetre propose de s18 (note ci-dessus) tient independamment de ce point
technique, et le choix du mecanisme reste entierement la main de `theo-archi`.

### S17-2 - Comprendre un texte narratif ou documentaire (francais CM1)

- **Besoin** : en tant qu'eleve de CM1, je veux lire un court texte et repondre a une question sur
  ce que j'ai compris, meme quand la reponse n'est pas ecrite mot pour mot dans le texte, afin de
  m'entrainer a comprendre ce que je lis et pas seulement a le reconnaitre.
- **Competence programme visee** : francais, CM1 (cycle 3), domaine "Lecture et comprehension de
  l'ecrit", "Comprendre un texte narratif ou documentaire (questions explicites et implicites,
  inferences simples)" (`squad/programme/francais/competences-cm1.md`).
- **Ce que cette story ne couvre pas** : la competence voisine du meme domaine, "lire silencieusement
  un texte de longueur croissante et en restituer l'essentiel", reste `non couvert` - elle demande une
  production (un resume dans les mots de l'enfant) que le mecanisme du bandit manchot ne sait pas
  recueillir. Premier report, motif complet dans `DEC-0039`, section 5.
- **Matiere premiere** : aucune. `contenu-repris/quiz/cm1-comprehension.yml` a ete verifie et
  **refuse par `nadia-prof`** (arbitrage prealable du 2026-08-19) - ses huit items sont exactement le
  defaut qu'elle refuse (appariement de mots, zero inference), pas un point de depart a corriger. Ce
  paquet est ecrit integralement neuf, sans economie de reprise.
- **Criteres d'acceptation**, repris au mot pres de l'arbitrage prealable de `nadia-prof`
  (`squad/conformite/FICHE-bandit-manchot-comprehension-cm1.md`, conditions COMP-1 a COMP-5) :
  - Etant donne un item, quand on masque la question et les propositions et qu'on ne garde que le
    texte, alors aucune proposition ne se laisse reperer par la seule presence ou absence de ses mots
    dans le texte (COMP-1) : toutes les propositions d'un item emploient des mots du texte, ou aucune
    ne le fait, jamais une seule.
  - Etant donne un item, quand on masque le texte et qu'on ne garde que la question et les
    propositions, alors un enfant qui n'a pas lu le texte ne fait pas mieux que le hasard (COMP-2) :
    ni fait de culture generale, ni distracteur absurde qui s'elimine seul.
  - Etant donne l'ensemble du paquet, quand on classe chaque item selon que sa reponse est un
    fragment recopie du texte ou exige de relier deux informations distinctes du texte, alors au
    moins un tiers des items sont dans la seconde famille (COMP-3).
  - Etant donne chaque item rendu a 360 x 640, quand on mesure le texte-support, la question et les
    propositions par `outils/mesurer-rendu.sh`, alors l'ensemble tient au-dessus du pli sans defiler,
    sur la totalite des items du paquet (COMP-4).
  - Etant donne le texte-support, quand on compare son style a celui de l'enonce des autres jeux du
    catalogue, alors il porte un style distinct, plus petit, propose par `ines-design` et juge par
    `nadia-prof` sur sa lisibilite avant le premier item ecrit sur sa base (COMP-5) - **le critere
    "sans gabarit ni feuille de style neuve" ne s'applique pas a cette story, leve par `camille-po`
    le 2026-08-19** (`DEC-0039`, amendement du 2026-08-19, section 2).
  - Etant donne le paquet, quand on le compte, alors il compte au moins 20 items.
  - Etant donne chaque item, quand on lit son texte-support, alors il ne depasse pas environ 45 mots
    (limite du proxy nommee par `nadia-prof` : ce jeu ne lira jamais un texte long, seulement des
    textes courts, un texte par question).
  - Ce qui n'est pas testable par un automate et releve de la seule porte pedagogique de
    `nadia-prof` : la difficulte reelle des inferences pour un enfant de CM1, et la justesse du
    francais de chaque texte-support.
- **Ce qui doit etre arbitre avant tout code** : la forme exacte du style du texte-support (COMP-5) -
  proposition d'`ines-design`, mecanisme de `theo-archi`, jugement de lisibilite de `nadia-prof` -
  avant qu'aucun des vingt items ne soit ecrit sur sa base.
- **Lien decision** : `DEC-0039-perimetre-sprint-17-francais-cm1-et-dette-de-len-tete.md`, amendement
  du 2026-08-19.
- **Agent pressenti** : a affecter au planning par `sofia-sm` (avis de faisabilite de `theo-archi`
  requis avant affectation, y compris sur COMP-5).

### Note du 2026-08-19 (camille-po, s17) - S17-2 sort du perimetre de s17, retourne non affectee

`nadia-prof` a rejoue `COMP-5` sur la mise en page reelle de la manche (consigne, question et
propositions comprises, pas seulement le bloc de texte) : le budget compose tient a 4 lignes,
environ 20 mots dans le meilleur assemblage encore realiste, ce qu'elle juge elle-meme
insuffisant pour porter l'inference qu'exige `COMP-3` - a ce budget, le paquet redeviendrait le
reperage litteral qu'elle a refuse en s02. `ines-design` degage 44 px sans cout pour l'enfant (ou
82 px par une voie qu'elle deconseille, en retirant le score de l'ecran) ; `theo-archi` n'apporte
aucun pixel de mecanisme. Il manque 90 a 120 px : aucune des trois contributions ne les fournit,
et meme la voie deconseillee ne referme pas l'ecart. Detail complet dans `DEC-0039`, amendement du
2026-08-19 ("`S17-2` sort du perimetre de s17").

**Consequence sur cette story** : `COMP-5` reste satisfaite (variante nue d'`ines-design`), mais
le critere "au plus 45 mots" ci-dessus est **retire** - il n'a jamais ete atteignable dans la mise
en page reelle - et remplace par `COMP-6` (consigne rendue sur une seule ligne) et `COMP-7` (aucun
nombre de lignes ou de mots ne fait budget ; seul `y <= 640` mesure sur l'ensemble texte-support +
question + propositions gouverne), tous deux repris de
`squad/conformite/FICHE-bandit-manchot-comprehension-cm1.md`. **Cette story n'est plus affectee a
s17** : aucun agent n'y travaille a ce jour, et aucun code de production n'existe pour elle
(verifie : aucune entree de catalogue, aucun fichier de contenu, aucun champ de texte-support dans
`domain`, `application` ou `adapters`). Elle redevient une story de backlog non affectee, au meme
titre que les autres lignes differees de `DEC-0039` section 5, jusqu'a ce qu'un arbitrage de mise
en page ou de mecanisme trouve les 90 a 120 px manquants - arbitrage que je nomme et route a
`sofia-sm` (voir `DEC-0039`), pas que je prends ici.

### S17-3 - Synonymes en contexte (francais CM1, couverture partielle nommee, jamais couvert-valide sur ce seul paquet)

- **Besoin** : en tant qu'eleve de CM1, je veux retrouver, dans une phrase, le mot qui peut en
  remplacer un autre sans changer le sens, afin d'enrichir mon vocabulaire et de choisir des mots
  plus precis dans mes phrases.
- **Competence programme visee** : francais, CM1 (cycle 3), domaine "Vocabulaire", "Utiliser des
  synonymes et antonymes pour preciser ou nuancer un propos" (`squad/programme/francais/
  competences-cm1.md`) - **couverture partielle assumee a l'ouverture** : ce paquet ne porte que des
  synonymes, aucun antonyme, faute de matiere premiere identifiee.
  **Statut, arbitre par `nadia-prof` le 2026-08-19** : les antonymes ne sont pas un prolongement du
  meme geste (comme les cinq autres partialites deja acceptees sur ce produit), ils sont l'autre
  operation. **Cette ligne reste `en cours` quel que soit le verdict pedagogique sur ce paquet, et
  n'entre jamais dans le numerateur du taux de couverture tant que les antonymes n'y sont pas** -
  voir `squad/conformite/FICHE-bandit-manchot-synonymes-cm1.md` et
  `squad/programme/MATRICE-couverture.md`.
- **Criteres d'acceptation**, repris au mot pres de l'arbitrage prealable de `nadia-prof`
  (`squad/conformite/FICHE-bandit-manchot-synonymes-cm1.md`, conditions SYN-1 a SYN-3) :
  - Etant donne chaque item, quand on le lit, alors le mot cible est presente dans une phrase, jamais
    isole : "Dans cette phrase, quel mot peut remplacer `<mot souligne>` sans changer le sens ?" -
    condition bloquante **SYN-1**, sans laquelle l'accord de `nadia-prof` sur le proxy tombe.
  - Etant donne chaque item, quand on essaie chaque proposition a la place du mot souligne dans la
    phrase donnee, alors seule la bonne reponse garde le sens de la phrase - aucun distracteur n'est
    substituable (**SYN-2**).
  - Etant donne chaque item, quand on relit la phrase avec sa bonne reponse, alors aucune des
    propositions presentees comme interchangeables ne serait refusee par un enseignant dans cette
    phrase precise (**SYN-3**) - deux synonymes ne le sont jamais partout.
  - Etant donne le paquet, quand on le compte, alors il compte au moins 20 items.
  - Etant donne chaque item, quand on verifie qu'il porte un indice, alors ce champ est rempli, exact,
    aide a retrouver le sens du mot sans jamais nommer la reponse (condition M2 de l'arbitrage de
    mecanique en vigueur pour le catalogue).
  - Etant donne le paquet, quand on le charge au demarrage de l'application, alors il valide le schema
    `type: question` (`ADR-0007`), zero ligne de Java neuve et zero mecanique d'association par
    paires.
  - Ce qui n'est pas testable par un automate et releve de la seule porte pedagogique de
    `nadia-prof` : la justesse et le niveau de chaque phrase-support pour un enfant de CM1.
- **Lien decision** : `DEC-0039-perimetre-sprint-17-francais-cm1-et-dette-de-len-tete.md`, amendement
  du 2026-08-19.
- **Agent pressenti** : a affecter au planning par `sofia-sm` (avis de faisabilite de `theo-archi`
  requis avant affectation).

### Note du 2026-08-20 (camille-po, s17) - S17-3 REFUSE puis reprise ; le paquet vise seize items, pas vingt

`nadia-prof` a refuse `S17-3` le 2026-08-19 sur un rendu (aucune entree de catalogue, la derniere
proposition 63 a 159 px sous le pli sur 20 items sur 20 - contenu non en cause, `SYN-1` a `SYN-4`
tenues). Elle a ouvert `SYN-5` (bloquante, la derniere proposition visible sans defiler, sur la
totalite du paquet servi) et route a `camille-po`/`theo-archi` la question de savoir ou vivre la
clause invariante qui allongeait chaque enonce. Detail et ma decision de garder `S17-3` au
perimetre plutot que de la sortir comme `S17-2` : `DEC-0039`, amendement du 2026-08-19 ("`S17-3`
refusee sur un rendu, meme famille que `S17-2`, mais pas son declencheur").

`theo-archi` a instruit le mecanisme (`ADR-0052`) : la clause quitte l'enonce pour la consigne du
jeu, deja rendue une fois par ecran plutot que repetee par item. `nadia-prof` a accepte la voie
(sa propre condition `SYN-1` disait deja "le mot souligne", jamais "le mot joli") sous trois
conditions neuves (`SYN-6` a `SYN-8`). `lea-dev` a livre l'entree de catalogue et un item pilote :
mesure a `y = 637,19` pour un pli a 640, **il tient avec 2,81 px de marge**, gain net de 162 px.
`SYN-9` (bloquante, espace insecable dans les guillemets du mot marque) est neuve, cout nul en
pixels.

**Mais `nadia-prof` a aussi mesure que le pire cas n'est pas la phrase la plus longue : c'est le
couple (lignes d'enonce, rangees de propositions).** Une rangee de propositions de plus coute 90 px
et ne depend pas de la phrase. Sa prediction, mesuree sur onze items vus en cherchant le pilote :
**quatre items - `vite`, `ancien`, `poli`, `drole` - ont des propositions sur trois rangees et ne
passeront pas** par le seul deplacement de la clause (environ 87 px sous le pli). Elle ne choisit
pas la suite : raccourcir une etiquette de reponse est un contenu neuf qui repasse par `SYN-2` et
`SYN-3`, un rejugement pedagogique qu'elle route a `camille-po`/`lea-dev`.

**Ma decision, avec son motif complet dans `DEC-0039`, amendement du 2026-08-20** : les quatre
items sortent du paquet plutot que d'etre reecrits. Motif court : reecrire leurs etiquettes est un
cout et un risque pedagogique non bornes (un synonyme aussi juste et plus court n'est pas garanti
d'exister) engage pour tenir un compte d'items qui n'est pas une exigence de `nadia-prof` - verifie
a la source : ses conditions `SYN-1` a `SYN-9` ne portent jamais sur le nombre d'items, et "au
moins 20 items" est ma propre checklist d'ouverture, pas un jugement pedagogique. Retirer un item
deja valide n'est pas le reecrire : aucun repassage par `SYN-2`/`SYN-3` sur les seize qui restent.
`ADR-0023` (plus d'items que de manches) reste tenu a seize contre dix manches.

**Consequence sur les criteres ci-dessus** : le critere "le paquet compte au moins 20 items" est
**remplace par "le paquet compte au moins 16 items"**, seul chiffre change ; aucun autre critere de
cette story n'est modifie. `SYN-5` a `SYN-9` (`squad/conformite/FICHE-bandit-manchot-synonymes-
cm1.md`) s'ajoutent aux criteres deja lies a cette story, reprises au mot pres, comme `SYN-1` a
`SYN-3` l'ont ete a l'ouverture.

**Etat a ce jour** : un item sur seize mesure et tenu (le pilote, 2,81 px de marge) ; quinze restent
a corriger (deplacement de la clause) et a mesurer un par un par `nadia-prof`, dans l'ordre qu'elle
a elle-meme fixe. Aucun Done pedagogique n'est prononce ; le refus du 2026-08-19 reste formellement
en vigueur jusqu'a la mesure complete.

### Note du 2026-08-22 (camille-po, s18) - remesuree a 744 x 551 : `SYN-5` tenue, Done REFUSE sur `SYN-11` neuve ; entre au perimetre propose de s18

`nadia-prof` a remesure `S17-3` contre la reference fixee par `DEC-0040` (`squad/conformite/
FICHE-bandit-manchot-synonymes-cm1.md`, verdict du 2026-08-22) : **`SYN-5` est tenue** - seize
items sur seize au-dessus du pli, 160 tirages, pire marge +19,81 px. Le Done pedagogique reste
**REFUSE**, sur un motif neuf et sans rapport avec la reference d'ecran : en se trompant
volontairement (9 parties, 360 lectures), elle a mesure que sur **cinq items sur seize** le bloc de
retour juste/faux (bandeau et texte du verdict) est **entierement sous le pli** dans les trois
etats d'echec - un enfant qui se trompe une fois n'a ni rature ni mot, et au troisieme echec ne
voit jamais la phrase qui lui donne la reponse. D'ou `SYN-11` (bloquante), `SYN-12` et `SYN-13`
(non bloquantes), qui s'ajoutent aux criteres de cette story au meme titre que `SYN-5` a `SYN-10`.
`S17-C` se reproduit egalement ici, sans etre reprononcee (voir la story de correction dediee) :
une sortie au clavier existe a la revelation, rien a l'ecran ne l'indique.

**Critere d'acceptation ajoute (`SYN-11`)** : etant donne chacun des cinq items dont les
propositions plafonnent a deux rangees, quand l'enfant repond faux (aux trois etats d'echec -
premiere erreur, deuxieme erreur, revelation apres la troisieme), alors le bandeau juste/faux et le
texte du verdict sont entierement visibles au-dessus du pli, sans defiler, mesures a 744 x 551.

**Ce que cette story ne tranche pas** : le mecanisme qui replace le bloc de retour au-dessus du pli
(deplacer le bloc, reduire ce qui le precede, autre voie) - route a `ines-design` et `theo-archi`,
sous le veto pedagogique de `nadia-prof` (`DEC-0041`).

- **Lien decision** : `DEC-0041-perimetre-sprint-18-fermer-trois-dettes-avant-toute-couverture-
  neuve-bpm-1-reclamee-a-nadia-prof.md`.

### Note du 2026-08-22 (camille-po, s18, seconde) - `SYN-12` et `SYN-13` ne sont pas des criteres de lot ; `theo-archi` corrige ma formulation

`theo-archi` (`squad/sprints/s18-fermer-avant-ouvrir/avis-faisabilite.md`, section 2.4) releve que
la phrase ci-dessus ("`SYN-12` et `SYN-13` [...] s'ajoutent aux criteres de cette story au meme
titre que `SYN-5` a `SYN-10`") est inexacte pour les deux, et je ne la reecris pas : elle reste
lisible telle qu'ecrite le 2026-08-22 au matin. **La phrase juste, a lire a sa place :**

- **`SYN-11`** (bloquante) est seule un critere de lot, et il est deja ecrit correctement
  ci-dessus : bon tel qu'ecrit, non encodable, porte de `nadia-prof` seule. Ce qui devient
  encodable depuis `ADR-0055` (fusionne dans ce meme lot) est l'obligation de remesure des que l'un
  des deux membres de la comparaison de largeur change - jamais la mesure elle-meme.
- **`SYN-12`** (indice sous le pli, non bloquante) reste **entierement** au jugement de
  `nadia-prof`, comme elle l'a deja ecrit dans `squad/conformite/FICHE-bandit-manchot-synonymes-
  cm1.md` : son argument ("un verdict visible signale a l'enfant qu'il y a plus bas") depend du
  mecanisme retenu pour fermer `SYN-11`, pas d'un critere que je peux formuler avant que ce
  mecanisme soit choisi. Rien a ecrire ici, rien a encoder.
- **`SYN-13`** (revelation par elimination visuelle, non bloquante) n'est **pas un critere de
  lot** : elle est **conditionnee au mecanisme retenu** pour `SYN-11` - `nadia-prof` l'a ecrit
  elle-meme, "se ferme d'elle-meme si `SYN-11` est fermee par la largeur" (meme fiche). Un critere
  d'acceptation ne peut pas dependre du levier qui le fermera avant que ce levier soit choisi.
  `SYN-13` reste ce qu'elle est deja dans la fiche de conformite : **une observation datee**, a
  reprononcer - ou non - par `nadia-prof` une fois le mecanisme livre et remesure, jamais une
  condition de sortie de ce lot.

Cette story n'ouvre donc, comme critere de lot testable ou porte a la seule verification de
`nadia-prof`, que `SYN-11` - inchangee - et rien de plus sur `SYN-12`/`SYN-13`.

### S17-5 - Antonymes (francais CM1, non affectee, story compagne de S17-3)

- **Besoin** : en tant qu'eleve de CM1, je veux retrouver, dans une phrase, le mot de sens oppose a
  un mot donne, afin de nuancer ou contraster un propos, et pas seulement le preciser par un mot
  proche.
- **Competence programme visee** : francais, CM1 (cycle 3), domaine "Vocabulaire", "Utiliser des
  synonymes et antonymes pour preciser ou nuancer un propos" (`squad/programme/francais/
  competences-cm1.md`) - seconde moitie de la ligne ouverte par `S17-3`. Cette story est necessaire
  pour que la ligne puisse un jour atteindre `couvert-valide` : `nadia-prof` a ecrit que les
  antonymes ne sont pas un prolongement du meme geste que les synonymes, mais l'autre operation
  (`squad/conformite/FICHE-bandit-manchot-synonymes-cm1.md`).
- **Non affectee a ce sprint** : ecrite au backlog sur demande explicite de `nadia-prof` (condition
  SYN-4, non bloquante mais datee : "je demande que la story existe au backlog avant la cloture de
  s17"), sans engager de sprint. Aucune matiere premiere identifiee dans `contenu-repris/` a ce
  jour - a ecrire integralement neuf, sur le meme patron que S17-3 (mot en contexte de phrase, pas
  isole).
- **Criteres d'acceptation attendus, par analogie avec SYN-1 a SYN-3 de S17-3** : mot cible presente
  en contexte de phrase, bonne reponse un antonyme reellement oppose dans cette phrase precise,
  aucun distracteur qui serait lui-meme un antonyme acceptable - a confirmer par un arbitrage
  prealable de `nadia-prof` propre a cette story le jour ou elle est affectee, la reponse rendue pour
  les synonymes ne valant pas automatiquement pour l'operation inverse.
- **Lien decision** : `squad/conformite/FICHE-bandit-manchot-synonymes-cm1.md`, condition SYN-4.
- **Agent pressenti** : non affectee - candidate du backlog, a planifier par `sofia-sm`.

### S17-4 - "Le bon petit mot" revient au catalogue (francais CM1, raccourcissement, pas une ouverture)

- **Besoin** : en tant qu'eleve de CM1, je veux retrouver le jeu sur les homophones grammaticaux dans
  la liste des jeux, avec des phrases qui tiennent entierement a l'ecran, afin de m'entrainer sur les
  homophones sans que la partie ne se bloque a cause d'un texte trop long.
- **Competence programme visee** : francais, CM1 (cycle 3), domaine "Orthographe et grammaire",
  "Distinguer et utiliser correctement les principaux homophones grammaticaux (et/est, son/sont,
  a/à...)" (`squad/programme/francais/competences-cm1.md`) - ligne deja `en cours` depuis `DEC-0024`
  (s10) ; cette story ne l'ouvre pas, elle la fait avancer vers `couvert-valide`.
- **Criteres d'acceptation**, repris au mot pres de `DEC-0024` (points 3 et 5) et precises par les
  conditions BPM-1 a BPM-4 de l'arbitrage prealable de `nadia-prof` du 2026-08-19, sans redurcir ni
  affaiblir aucune des deux sources :
  - Etant donne le paquet `cm1-homophones.yaml` raccourci, quand on rend chaque manche a 360 x 640
    avec `outils/mesurer-rendu.sh` (message d'echec, indice, revelation), alors les propositions de
    reponse restent entierement visibles au-dessus du pli **sur la totalite des 24 items**, jamais un
    critere en nombre de caracteres (`DEC-0016`) ni un echantillon (**BPM-1**).
  - Etant donne chaque item raccourci, quand on essaie la mauvaise proposition dans la phrase
    obtenue, alors elle ne devient jamais grammaticalement acceptable : le raccourcissement ne
    supprime pas la structure de phrase (par exemple un "ne... plus" qui commande le verbe) qui rend
    la bonne reponse determinable (**BPM-2** - risque nomme par `nadia-prof`, absent de `DEC-0024`).
  - Etant donne le paquet raccourci, quand on le compte, alors il compte toujours au moins 24 items :
    le raccourcissement modifie des phrases, il ne supprime aucun item (**BPM-3**).
  - Etant donne les 22 indices sur 24 qui nomment aujourd'hui la reponse au lieu de la manipulation
    qui y mene, quand le paquet est raccourci, alors chacun de ces indices est corrige dans le meme
    geste, pour ne pas rouvrir ce fichier une seconde fois sur un motif distinct.
  - Etant donne le paquet raccourci et corrige, quand `nadia-prof` le rejuge, alors elle le traite
    comme un contenu neuf (**BPM-4**) et applique la barre en six points deja ecrite dans
    `squad/conformite/FICHE-bandit-manchot-homophones-cm1.md` (verdict du 2026-08-10), sans qu'aucun
    point n'y soit ajoute ni retire par cette story.
  - Ce qui n'est pas testable par un automate et releve de la seule porte pedagogique de
    `nadia-prof` : que le raccourcissement des enonces ne perde aucune des dix familles
    d'homophones deja validees, et que chaque indice corrige aide reellement sans nommer la reponse.
- **Reserve non rouverte, a ne pas aggraver** : deux propositions par item donnent une chance sur
  deux au hasard (reserve 5 de `nadia-prof`, s04) - le raccourcissement ne doit pas reduire encore le
  nombre de propositions.
- **Lien decision** : `DEC-0024-retrait-le-bon-petit-mot-contenu-valide-ecran-trop-court.md`,
  affectee au sprint 17 par `DEC-0039-perimetre-sprint-17-francais-cm1-et-dette-de-len-tete.md`,
  section 2 ; conditions precisees par l'arbitrage prealable de `nadia-prof` du 2026-08-19.
- **Agent pressenti** : a affecter au planning par `sofia-sm` (avis de faisabilite de `theo-archi`
  requis avant affectation).

### Note du 2026-08-19 (camille-po, s17) - le critere "22 indices sur 24" de S17-4 est faux, c'est 24 sur 24

En livrant cette story, `lea-dev` a relu `cm1-homophones.yaml` caractere par caractere et corrige
**les 24 indices, pas seulement les 22 annonces** : les deux manquants au compte d'origine (bonne
reponse `On`, bonne reponse `Où`) echappaient a une comparaison sensible a la casse, leur bonne
reponse portant la majuscule de debut de phrase quand l'indice la citait en minuscule. Methode et
chiffre documentes dans la Javadoc de `PaquetsFrancaisS04Test`
(`adapters/adapter-content/src/test/java/com/recreo/adapter/content/PaquetsFrancaisS04Test.java`),
meme correction versee dans `DEC-0024`, section "Correction du 2026-08-19 (lea-dev, s17)".

Je ne reecris pas le critere ci-dessus : il reste lisible tel qu'ecrit le 2026-08-19 au matin.
**Le critere juste, a lire a sa place** : "Etant donne les 24 indices sur 24 qui nomment
aujourd'hui la reponse au lieu de la manipulation qui y mene, quand le paquet est raccourci, alors
chacun de ces indices est corrige dans le meme geste." Le geste demande ne change pas (corriger
tous les indices fautifs dans le meme mouvement que le raccourcissement) : seul le compte affiche
etait faux, et `lea-dev` a corrige au-dela du compte annonce plutot qu'a sa hauteur.

### Note du 2026-08-22 (camille-po, s18) - `BPM-1` reclamee a `nadia-prof`, entre au perimetre propose de s18

`BPM-2`, `BPM-3` et `BPM-4` sont tenues depuis le 2026-08-19, et la reserve sur les trois indices
qui nommaient encore la reponse est fermee depuis le 2026-08-21 (0 indice sur 24). Seule `BPM-1`
manque encore a la condition de retour de `DEC-0024`. Le transfert de sa production a
l'utilisatrice (`DEC-0039`, section 3) est leve par `DEC-0041` : la mesure de rendu qu'il
attendait existe desormais couramment chez `nadia-prof` (`SYN-5`, `SYN-11`). `BPM-1` redevient une
mesure de la squad.

**Critere d'acceptation precise** : un dev ajoute l'entree `bandit-manchot-homophones-cm1` a
`catalogue.yaml` (une entree, zero ligne de Java neuf, zero mecanique neuve, sur le patron des
douze entrees deja presentes), puis `nadia-prof` produit `BPM-1` sur cette entree en incluant les
trois etats d'echec (message, indice, revelation), pas seulement l'etat de repos - la lecon de
`SYN-11` s'applique ici aussi. Si `BPM-1` tient et si le Done complet est prononce sans reserve
bloquante, la ligne francais CM1 "homophones" de `squad/programme/MATRICE-couverture.md` passe a
`couvert-valide` dans le meme geste que le verdict.

- **Lien decision** : `DEC-0041-perimetre-sprint-18-fermer-trois-dettes-avant-toute-couverture-
  neuve-bpm-1-reclamee-a-nadia-prof.md`.

### Note du 2026-08-22 (camille-po, s18, seconde) - le critere `BPM-1` de S17-4 nomme une reference et un outil perimes, corrige par `theo-archi`

Le critere de `S17-4` "repris au mot pres de `DEC-0024`" (ci-dessus, point 3 de la liste des
criteres d'acceptation : *"quand on rend chaque manche a 360 x 640 avec `outils/mesurer-rendu.sh`
[...] alors les propositions de reponse restent entierement visibles au-dessus du pli sur la
totalite des 24 items"*) est **inapplicable sur deux points factuels**, etablis par `theo-archi`
(`squad/sprints/s18-fermer-avant-ouvrir/avis-faisabilite.md`, section 1.2 et 1.4) : la reference
qu'il nomme (360 x 640) n'existe plus depuis `DEC-0040` (744 x 551), et l'outil qu'il nomme
(`outils/mesurer-rendu.sh`) rend une capture d'ecran d'une URL - il n'a aucune prise sur un etat
d'echec, qui s'obtient en postant une mauvaise reponse dans une session de partie, et ne peut donc
structurellement pas produire les trois quarts de ce que ce meme critere et `DEC-0041` demandent
(message d'echec, indice, revelation - pas seulement l'etat de repos).

Je ne reecris pas le critere ci-dessus, ni le point 3 de `DEC-0024` dont il est repris : ils
restent lisibles tels qu'ecrits en s10 et en s17. **Le critere juste, a lire a leur place :**
"Etant donne le paquet `cm1-homophones.yaml` raccourci et son entree de catalogue, quand
`nadia-prof` mesure sur navigateur reel, a `scrollY = 0` verifie - la meme methode que `SYN-5` et
`SYN-11` - l'etat le plus charge de chaque manche (message d'echec, indice, revelation) a la
reference en vigueur (744 x 551 px, `DEC-0040`), alors les propositions de reponse restent
entierement visibles au-dessus du pli sur la totalite des 24 items, jamais un critere en nombre de
caracteres (`DEC-0016`) ni un echantillon (`BPM-1`)." **Le fond du critere ne change pas** - c'est
sa reference et son outil qui avaient vieilli, pas ce qu'il demande a l'enfant de pouvoir voir.

**Ce que je corrige aussi, dans le meme geste : le cout que j'avais chiffre ci-dessus et dans
`DEC-0041` etait sous-estime.** `theo-archi` (meme avis, section 1.3) mesure que cette campagne
porte sur **24 items** aux trois etats d'echec chacun, au pire tirage (`SYN-10`) - un ordre
superieur a la derniere campagne comparable de `nadia-prof` (`SYN-11` : 9 parties, 360 lectures,
sur 16 items seulement). Deux faits bornent cette campagne vers le bas sans en garantir l'issue :
les 24 items ont tous exactement deux propositions (`SYN-10` y coute deux arrangements par item,
non six), et le total de caracteres des deux etiquettes ne depasse jamais sept - une seconde rangee
de propositions y est donc plus couteuse a declencher que sur `cm1-synonymes`. Un fait l'allonge en
sens inverse : la revelation s'atteint en pressant trois fois la meme mauvaise reponse
(`ESSAIS_AVANT_REVELATION = 3`, une proposition deja essayee restant cliquable), donc les trois
etats d'echec sont chacun reellement atteignables et aucun ne peut etre saute. **Le perimetre que
je propose ne change pas pour autant** : la decision de capacite (sequencer cette campagne contre
celles de `SYN-11` et de `S17-C`) reste celle de `sofia-sm`, mais le cout qu'elle sequence doit
etre le vrai, pas celui, trop bas, que j'avais annonce.

### Story de migration - Les paquets de contenu servis portent leur matiere accentuee (execution d'ADR-0049)

**Non affectee a un sprint.** `ADR-0049` (`theo-archi`, s17) decide que la matiere est portee
accentuee par la donnee de contenu elle-meme (`descripteur.matiere`), et non plus en ASCII. La
decision est prise, elle n'est pas executee : les 12 paquets servis sur `round-two` a ce jour
portent toujours `matiere: "Francais"` ou `matiere: "Mathematiques"` en ASCII
(`adapters/adapter-content/src/main/resources/contenu/*.yaml`), et `lea-dev` vient d'ecrire un
treizieme paquet (`cm1-synonymes.yaml`, `S17-3`) dans la meme graphie ASCII, deliberement, pour ne
pas faire rougir `UneMatiereNAQuUneSeuleGraphieDansLeContenuServiTest` (le garde-fou d'`ADR-0049`
refuse qu'une seule matiere porte deux graphies a la fois dans le contenu servi). Cette story
execute la bascule qu'`ADR-0049` a decidee, elle ne rejuge rien de son contenu.

- **Besoin** : en tant qu'eleve de CP ou de CM1, je veux que le mot qui nomme ma matiere soit
  toujours ecrit de la meme facon dans l'application, meme avant que ce mot ne me soit montre sur
  un bouton ou un filtre, afin que le jour ou je le lirai, ce ne soit jamais un mot ecrit
  differemment selon le jeu auquel je joue.
- **Competence programme visee** : transverse - pas de contenu pedagogique.
- **Perimetre a la date d'ouverture (2026-08-19), et qui grandit tant que la story n'est pas
  affectee** : les 12 fichiers suivants, tous `matiere` en ASCII -
  `cm1-conjugaison-present.yaml`, `cm1-grands-nombres.yaml`, `cm1-homophones.yaml`,
  `cm1-mesures.yaml`, `cm1-perimetre.yaml`, `cm1-tables-multiplication.yaml`,
  `cp-additions-jusqu-a-10.yaml`, `cp-comparer-nombres.yaml`, `cp-complements-10.yaml`,
  `cp-le-bon-son.yaml`, `cp-problemes-additifs.yaml`, `cp-soustractions.yaml` - plus
  `cm1-synonymes.yaml` (`S17-3`, sur la branche `s17-lea` non encore fusionnee a ce jour, meme
  graphie ASCII). **Chaque paquet neuf ecrit en ASCII avant que cette story ne soit executee
  agrandit ce perimetre d'un fichier de plus** : je ne fige pas le compte a 12 ni a 13, je le
  fige a la date d'affectation.
- **Criteres d'acceptation** :
  - Etant donne l'ensemble des paquets de contenu servis a la date d'affectation de cette story
    (le perimetre ci-dessus, mis a jour a cette date), quand on lit le champ
    `descripteur.matiere` de chacun, alors aucun ne porte plus la forme ASCII (`Francais`,
    `Mathematiques`) et tous portent la forme accentuee retenue.
  - Etant donne `UneMatiereNAQuUneSeuleGraphieDansLeContenuServiTest` (`ADR-0049`), quand cette
    story est livree, alors ce test reste vert : une seule graphie par matiere dans le contenu
    servi, desormais la forme accentuee plutot que l'ASCII.
  - Etant donne l'assertion de `YamlContentPackLoaderTest` qui compare une valeur de matiere, et
    l'inventaire du garde-fou de `S16-5` qui liste la matiere de chaque entree exposee par le
    port entrant, quand la bascule est faite, alors les deux refletent la graphie accentuee -
    cout nomme explicitement par `ADR-0049` ("douze fichiers de contenu servis [...] plus
    l'assertion [...] et l'inventaire du garde-fou de S16-5").
  - Ce que cette story ne tranche pas, routee a `theo-archi` : le mecanisme de la bascule (un
    commit par fichier ou un script qui les convertit tous, l'ordre, et si un garde-fou neuf
    doit desormais refuser tout paquet neuf depose en ASCII pour que ce cout ne se represente
    jamais) - `ADR-0049` le dit lui-meme, cette decision "ne tranche pas" le mecanisme, seulement
    la forme cible et son unicite.
  - Ce qui n'est pas testable par un automate : que la graphie accentuee retenue
    (`Français`/`Mathématiques`, forme deja portee par `contenu-repris/`) soit du francais
    correct et integralement accentue, releve de `relecteur-pedagogique` (`DEC-0002`, amendement
    du 2026-08-04), et non d'un jugement pedagogique de `nadia-prof` : cette story ne modifie
    aucun enonce, aucune reponse, aucune competence visee des paquets touches, elle ne repasse
    donc pas devant elle comme un contenu neuf.
- **Lien decision** : `ADR-0049-une-matiere-s-ecrit-accentuee-dans-la-donnee-de-catalogue.md`.
- **Agent pressenti** : a affecter au planning par `sofia-sm`, avec `theo-archi` pour le
  mecanisme de la bascule (point ci-dessus).

### Complement du 2026-08-19 (camille-po, s17) - mecanisme etabli par theo-archi, perimetre elargi a trois chaines de plus

`theo-archi` a instruit le mecanisme de la bascule (`squad/sprints/s17-francais-cm1-et-dette/
avis-garde-fous-routes-par-yanis.md`, section 4). Ce que la story ne tranchait pas est desormais
repondu, et remplace le point "ce que cette story ne tranche pas" ci-dessus sur ce seul sujet :

- **Aucun garde-fou neuf n'est du.** La regle d'unicite existante d'`ADR-0049`
  (`UneMatiereNAQuUneSeuleGraphieDansLeContenuServiTest`) bloque symetriquement des que le corpus
  est uniforme : avant la bascule un paquet accentue est rouge, apres la bascule un paquet ASCII
  l'est. Un garde-fou qui interdirait nommement l'ASCII exigerait un dictionnaire, qu'`ADR-0049`
  et `ADR-0039` refusent tous deux.
- **Un seul commit, jamais deux : tout etat intermediaire est rouge par construction.** Ce n'est
  pas une preference d'ordonnancement, c'est une propriete de la regle elle-meme, et **cela devient
  un critere d'acceptation de cette story**, ajoute a ceux deja ecrits : etant donne les fichiers du
  perimetre, quand la bascule est livree, alors elle l'est en un seul commit portant tous les
  fichiers a la fois - jamais un sous-ensemble laisse en ASCII pendant qu'un autre est deja
  accentue.
- **Cout mesure, confirmant et precisant le perimetre deja ecrit** : 12 paquets servis (3 en
  `Francais`, 9 en `Mathematiques`), plus `cm1-synonymes` (`lea-dev`), 2 entrees de l'inventaire de
  `TexteAccentueExposeParLePortEntrantTest` (ses deux clauses sont bidirectionnelles : sans cette
  edition, les deux rougissent), et **zero ligne de code de production** (`grep -rn "\.matiere\(\)"`
  sur `src/main` : aucune occurrence).
- **Deux pieges nommes, a ne pas decouvrir par un rouge** : un remplacement global de `Francais`
  dans le depot casserait la morsure du garde-fou (`paquet-servi-en-ascii.yaml`, sous
  `src/test/resources/contreexemple/`, doit rester en ASCII deliberement) - la bascule touche
  uniquement `src/main/resources/contenu/`. Et `contenu-repris/` n'est pas concerne : ses 61
  fichiers sont deja accentues et ne sont pas servis.

**Decision sur les trois chaines routees par `theo-archi`, avec son motif.** Le meme inventaire de
`TexteAccentueExposeParLePortEntrantTest` porte trois autres chaines non accentuees des memes
descripteurs, marquees "CONNU ET NON CORRIGE ICI" depuis `S16-5` : le domaine
`Lecture et comprehension de l'ecrit` (`cp-le-bon-son.yaml`) et les competences
`Associer les graphemes aux phonemes (voyelles, consonnes simples, puis digraphes ch, ou, on...)`
(meme fichier) et `Resoudre des problemes simples d'addition/soustraction en situation (1 etape)`
(`cp-problemes-additifs.yaml`). **Ces trois chaines entrent au perimetre de cette story.** Motif :
elles vivent dans deux fichiers deja ouverts par cette meme story pour leur `matiere` -
`cp-le-bon-son.yaml` et `cp-problemes-additifs.yaml` sont deux des douze du perimetre ci-dessus -
et le meme inventaire les verifie ; les y laisser obligerait a rouvrir les deux memes fichiers une
seconde fois pour le meme motif (accentuation d'un descripteur), ce que cette squad evite
systematiquement ailleurs (`DEC-0024`, point 5, sur les indices de `cm1-homophones`). Le risque est
nul : aucun gabarit ne rend le domaine ni la competence a un enfant aujourd'hui (verifie par
`theo-archi`), et la graphie cible n'est pas une nuance a trancher - elle recopie mot pour mot le
referentiel deja arbitre (`squad/programme/francais/competences-cp.md`, "Lecture et compréhension
de l'écrit", "Associer les graphèmes aux phonèmes..." ; `squad/programme/mathematiques/
competences-cp.md`, "Résoudre des problèmes simples d'addition/soustraction en situation
(1 étape)"), verifie par moi caractere pres caractere avant d'ecrire cette phrase.

**Sur la coincidence nommee** : l'une des trois chaines, `Lecture et comprehension de l'ecrit`,
est le domaine de la competence que porte `S17-2`, la story que je viens de sortir du perimetre de
s17. **Ce n'est pas la meme chose, et je verifie plutot que de le supposer** : cette chaine ne vient
pas d'un paquet de `S17-2` - aucun fichier `cm1-comprehension.yaml` n'existe, `S17-2` n'a produit
aucun code (deja etabli dans l'amendement de sortie de perimetre). Elle vient de
`cp-le-bon-son.yaml`, un paquet CP deja au catalogue depuis plusieurs sprints, sur une competence
de grapheme/phoneme sans rapport avec la comprehension de texte de `S17-2` - les deux se contentent
de partager le meme intitule de domaine du referentiel francais, un domaine qui regroupe plusieurs
competences a la fois au CP et au CM1. **Cela ne change donc rien a ma decision** : je ne traite ni
plus ni moins ces trois chaines parce que l'une porte le meme nom qu'un domaine deja sorti du
perimetre de s17 par ailleurs - la sortie de `S17-2` reste entierement etrangere a cette story.

- **Critere d'acceptation ajoute** : etant donne les trois chaines ci-dessus, quand la bascule
  livre cette story, alors chacune porte la graphie accentuee du referentiel citee ci-dessus, dans
  le meme commit que la bascule de `matiere`.
- **Lien decision (ajout)** : `squad/sprints/s17-francais-cm1-et-dette/avis-garde-fous-routes-par-
  yanis.md`, section 4.

## Sprint 19 - Contenu seul en francais CP/CM1, conception de la saisie sans code

Perimetre fixe par `DEC-0042-perimetre-sprint-19-contenu-seul-conception-de-la-saisie-lecture-a-
voix-haute-non-couvrable.md`. Quatre stories de contenu servies par la mecanique `bandit-manchot`
existante (`S19-1` a `S19-4`), une story de dette deja chiffree (`S19-5`). Aucune story de code de
mecanique neuve ce sprint : la conception de la saisie (Lot C de `DEC-0042`) avance sans capacite
de developpement affectee, via `theo-archi` et `nadia-prof`.

`S19-6` etait initialement une sixieme story de dette. `DEC-0043` la scinde en `S19-6a` et `S19-6b`
(ci-dessous, apres `S19-5`) et les renvoie toutes deux au backlog de s20, non affectees a s19 :
`nadia-prof` a mesure entre-temps que le correctif de `SYN-16` est bon marche a instruire, mais
son implementation reste une story a part entiere, et `BPM-8` n'a encore recu aucune mesure de
faisabilite.

### S19-1 - Les antonymes (francais CM1, ferme la moitie manquante de S17-3)

- **Besoin** : en tant qu'eleve de CM1, je veux retrouver, dans une phrase, le mot de sens oppose a
  un mot donne, afin de nuancer ou contraster un propos, et pas seulement le preciser par un mot
  proche.
- **Competence programme visee** : francais, CM1 (cycle 3), domaine "Vocabulaire", "Utiliser des
  synonymes et antonymes pour preciser ou nuancer un propos"
  (`squad/programme/francais/competences-cm1.md`) - seconde moitie de la ligne ouverte par `S17-3`.
  Cette story affecte `S17-5`, ecrite au backlog depuis s17 sur demande de `nadia-prof` (condition
  SYN-4) et jamais affectee depuis : le besoin, la competence et le lien decision de `S17-5` restent
  exacts, je ne les reecris pas, je les complete par des criteres fermes et une decision de forme.
- **Matiere premiere verifiee, aucune reutilisable telle quelle** : `contenu-repris/associer/
  cm1-synonymes.yml` (12 paires mot/synonyme) ne contient aucun antonyme - verifie ligne par ligne,
  confirme par `DEC-0039` ("verifie par recherche du mot 'antonyme' et de ses derives dans
  `contenu-repris/` : aucun fichier"). Le paquet est donc integralement neuf.
- **Decision de forme : paquet neuf et jeu neuf au catalogue, pas une extension de
  `cm1-synonymes.yaml`.** Deux options existaient ; je retiens la premiere et j'ecris pourquoi la
  seconde ne tient pas :
  - L'entree de catalogue `bandit-manchot-synonymes-cm1` porte, depuis `ADR-0052`, une consigne
    generique unique et fixe : *"Quel mot peut remplacer le mot entre guillemets sans changer le
    sens ?"* (`adapters/adapter-content/src/main/resources/catalogue.yaml`, verifie). Un item
    d'antonyme repondrait faux a cette question par construction - il n'existe pas de mot qui
    "remplace sans changer le sens" par un contraire. Ajouter des antonymes au paquet
    `cm1-synonymes.yaml` sans changer la consigne produirait un jeu qui pose une question fausse a
    la moitie de ses items ; changer la consigne selon l'item demanderait de revenir a une clause
    portee par l'enonce, exactement ce qu'`ADR-0052` a fait sortir de l'enonce pour gagner de la
    hauteur utile (voir `FICHE-bandit-manchot-synonymes-cm1.md`, section sur `ADR-0052`) - je ne
    rouvre pas ce gain pour cette story.
  - `nadia-prof` a elle-meme qualifie les antonymes d'"**autre operation**", pas d'un prolongement
    du meme geste (`DEC-0039`, section 3) : un jeu distinct, avec son propre titre et sa propre
    consigne, porte cette distinction plus honnetement qu'un paquet unique aux deux operations
    melangees.
  - **Ce que cela ne change pas** : aucune mecanique neuve, aucun code de domaine - une entree de
    catalogue de plus, sur le meme patron que les treize deja servies. La ligne de matrice reste
    servie par "Jeu(x) couvrant" au pluriel (`bandit-manchot-synonymes-cm1`,
    `bandit-manchot-antonymes-cm1`), une fois les deux jeux valides.
  - Identifiants proposes, a confirmer par le developpeur : `id: bandit-manchot-antonymes-cm1`,
    `paquet: cm1-antonymes`, `titre` et `consigne` a rediger par le developpeur sur le meme patron
    que "Les synonymes" (consigne generique invariante, mot cible marque par une ponctuation
    francaise dans l'enonce, jamais un mot isole - voir `SYN-1` et `SYN-6`, meme fiche).
- **Criteres d'acceptation**, par analogie directe avec `SYN-1` a `SYN-9` de `S17-3` (memes motifs,
  operation inverse) :
  - Etant donne chaque item du paquet, quand on lit son enonce, alors le mot cible est presente
    entre guillemets francais a l'interieur d'une phrase complete, jamais isole (repris de `SYN-1`).
  - Etant donne un item, quand on lit sa bonne reponse, alors elle est un antonyme reellement
    oppose au mot cible **dans cette phrase precise** - pas un antonyme du mot pris hors contexte
    (un mot polysemique n'a pas le meme contraire selon le sens retenu dans la phrase).
  - Etant donne un item, quand on lit ses distracteurs, alors aucun n'est lui-meme un antonyme
    acceptable du mot cible dans cette phrase - un seul contraire juste par item.
  - Etant donne les espaces a l'interieur des guillemets francais, quand le paquet est ecrit,
    alors ce sont des espaces insecables (repris de `SYN-9`).
  - Etant donne le paquet complet, quand on le compte, alors il porte au moins 16 items (plancher
    deja retenu sur `cm1-synonymes.yaml` apres retrait des items a trois rangees de propositions -
    ce paquet neuf doit verifier des l'ecriture qu'aucun de ses items ne produit trois rangees a la
    reference 744 x 551, `DEC-0040`, plutot que de le decouvrir a l'audit).
  - Etant donne chaque item, quand on lit son indice, alors il aide a trouver l'antonyme sans le
    nommer (repris du principe deja applique a tous les paquets de ce produit).
  - Ce que cette story ne tranche pas : le mecanisme de rendu de la consigne et de l'enonce
    (deja etabli par `ADR-0052` pour "Les synonymes", a reutiliser tel quel), routee a
    `theo-archi` seulement si un ecart apparait.
  - Ce qui n'est pas testable par un automate et releve de la seule porte pedagogique de
    `nadia-prof`, sur un **arbitrage prealable a rendre avant tout code** (la reponse rendue sur
    le proxy des synonymes ne vaut pas automatiquement pour l'operation inverse, `S17-5`) : que
    chaque antonyme retenu soit un contraire pertinent et univoque pour un CM1 dans le contexte de
    sa phrase, que la profondeur des phrases soit de niveau CM1 (pas CE1/CE2, meme exigence que
    `SYN-2`/`SYN-3`), et que la decision de forme ci-dessus (jeu distinct) soit elle-meme jugee
    lisible par un enfant qui a deja joue "Les synonymes".
- **Lien decision** : `S17-5` (origine), `DEC-0039` section 3, `DEC-0042` section 5.
- **Agent pressenti** : a affecter au planning par `sofia-sm`, arbitrage prealable de `nadia-prof`
  requis avant tout code.

### S19-2 - Accorder en genre et en nombre dans le groupe nominal (francais CM1)

- **Besoin** : en tant qu'eleve de CM1, je veux choisir la bonne terminaison du determinant, du nom
  ou de l'adjectif dans un groupe de mots, afin qu'ils s'accordent tous ensemble en genre et en
  nombre.
- **Competence programme visee** : francais, CM1 (cycle 3), domaine "Orthographe et grammaire",
  "Accorder en genre et en nombre dans le groupe nominal (determinant/nom/adjectif)"
  (`squad/programme/francais/competences-cm1.md`).
- **Matiere premiere verifiee, partiellement reutilisable** : `contenu-repris/textes-trous/
  cm1-accords.yml` (5 items) porte uniquement l'accord de l'adjectif - "le determinant et le nom
  [sont] deja fixes dans chaque phrase" (constat deja verse au backlog, section "Backlog - sprints
  suivants", jamais instruit depuis). La competence du referentiel nomme le determinant, le nom et
  l'adjectif : ce paquet ne teste aujourd'hui qu'un tiers du groupe nominal.
- **Decision de perimetre : le paquet neuf couvre les trois composantes, pas seulement
  l'adjectif.** Je n'ouvre pas cette ligne sur le seul materiau repris, qui reproduirait le motif
  deja sanctionne sur ce produit (couverture partielle non nommee des l'ouverture - additions
  "jusqu'a 10", homophones "principaux", conjugaison "un temps sur trois", tous nommes des
  l'ouverture depuis, jamais decouverts a l'audit). Si le developpeur ou `nadia-prof` juge que les
  trois composantes ne peuvent pas tenir dans un seul paquet a cout raisonnable, la limite de
  couverture doit etre nommee par ecrit dans la fiche de conformite des l'ouverture, sur le modele
  de `DEC-0013` (conjugaison, un temps sur trois nomme d'emblee) - jamais decouverte a l'audit.
- **Criteres d'acceptation** :
  - Etant donne le paquet complet, quand on classe ses items par composante testee (determinant,
    nom, adjectif), alors chacune des trois est representee par au moins un quart des items - aucun
    tiers du groupe nominal n'est cite dans l'enonce sans jamais porter le blanc a completer.
  - Etant donne un item qui teste l'accord du nom (singulier/pluriel), quand on lit ses
    distracteurs, alors ils portent une erreur de nombre reelle sur le nom (par exemple un pluriel
    en `-s` manquant ou ajoute a tort), jamais une erreur d'un autre ordre.
  - Etant donne un item qui teste l'accord du determinant, quand on lit ses distracteurs, alors ils
    portent un determinant dont le genre ou le nombre ne correspond pas au nom qui suit, jamais un
    determinant inexistant en francais.
  - Etant donne un item qui teste l'accord de l'adjectif, quand on lit ses distracteurs, alors ils
    reprennent le patron deja valide sur `cm1-accords.yml` (les quatre formes possibles de
    l'adjectif, une seule accordee juste avec le reste du groupe).
  - Etant donne le paquet complet, quand on le compte, alors il porte au moins 20 items (aucun
    plancher fixe par `nadia-prof` a ce jour ; ce chiffre est ma propre checklist d'ouverture, pas
    une condition pedagogique).
  - Ce que cette story ne tranche pas : le format exact de l'item (une phrase a trou par composante,
    ou un groupe nominal complet avec plusieurs trous) - routee a `theo-archi` si elle a un cout
    different selon la forme retenue, sinon laissee au developpeur.
  - Ce qui n'est pas testable par un automate et releve de la seule porte pedagogique de
    `nadia-prof` : que les trois composantes soient chacune testees a une profondeur de CM1, et que
    la limite de couverture, si les trois ne tiennent finalement pas dans ce paquet, soit nommee
    avant le premier audit et non decouverte pendant.
- **Lien decision** : `DEC-0042`, section 5 ; candidate deja versee au backlog, section "Backlog -
  sprints suivants" ("`textes-trous/cm1-accords.yml` [...] reste le candidat non retenu cette
  fois... a instruire pour un sprint suivant").
- **Agent pressenti** : a affecter au planning par `sofia-sm`.

### S19-3 - Accorder le verbe avec son sujet (francais CM1)

- **Besoin** : en tant qu'eleve de CM1, je veux choisir la bonne forme d'un verbe pour qu'il
  s'accorde avec son sujet, meme quand ce sujet n'est pas juste devant le verbe, afin de ne plus
  confondre "accorder" avec "connaitre sa conjugaison".
- **Competence programme visee** : francais, CM1 (cycle 3), domaine "Orthographe et grammaire",
  "Accorder le verbe avec son sujet (cas frequents, temps simples : present, futur, imparfait)"
  (`squad/programme/francais/competences-cm1.md`).
- **Matiere premiere verifiee, hors sujet** : `contenu-repris/textes-trous/cm1-conjugaison.yml` (6
  items) teste le choix de la bonne personne conjuguee au present, avec le sujet immediatement
  devant le verbe et un pronom sujet non ambigu dans chaque phrase - c'est la matiere qui sert deja
  la ligne "Conjuguer les verbes frequents..." (`cm1-conjugaison-present.yaml`, `couvert-valide`).
  Cette ligne-ci vise l'**accord**, pas la conjugaison : elle suppose que l'enfant sache deja la
  forme et doive reperer a quel sujet elle s'accorde, y compris quand ce sujet est un groupe
  nominal, un sujet compose, ou separe du verbe par un groupe de mots. Aucune matiere premiere
  identifiee ne teste cela ; le paquet est integralement neuf.
- **Distinction a garder de la ligne conjugaison deja couverte, pour ne pas construire un doublon** :
  chaque item de ce paquet doit exiger de reperer le sujet avant de choisir la forme - jamais un
  sujet pronominal isole colle au verbe, qui ne fait que redemander la conjugaison deja testee par
  `cm1-conjugaison-present.yaml`.
- **Criteres d'acceptation** :
  - Etant donne chaque item, quand on lit sa phrase, alors le sujet n'est jamais un pronom
    personnel seul immediatement suivi du verbe - il est soit un groupe nominal ("les enfants du
    voisin ___"), soit un sujet compose ("mon frere et moi ___"), soit separe du verbe par un
    groupe de mots ("les enfants, malgre la pluie, ___").
  - Etant donne un item, quand on lit ses distracteurs, alors au moins un est la forme correcte
    d'un autre nombre ou d'une autre personne que celle du sujet reel - une erreur d'accord, pas une
    erreur de temps ni de verbe.
  - Etant donne le paquet complet, quand on le classe par temps, alors le present, le futur et
    l'imparfait sont chacun representes - la competence du referentiel nomme les trois ; si les
    trois ne tiennent pas dans ce paquet a cout raisonnable, la limite doit etre nommee par ecrit
    dans la fiche de conformite des l'ouverture, sur le modele de `DEC-0013`, jamais decouverte a
    l'audit.
  - Etant donne le paquet complet, quand on le compte, alors il porte au moins 20 items.
  - Ce qui n'est pas testable par un automate et releve de la seule porte pedagogique de
    `nadia-prof` : que chaque sujet propose soit reellement un cas ou un enfant de CM1 risque de se
    tromper d'accord (le risque cible par la competence), et que la profondeur des trois temps, si
    tous les trois sont couverts, soit homogene.
- **Lien decision** : `DEC-0042`, section 5.
- **Agent pressenti** : a affecter au planning par `sofia-sm`.

**Statut du 2026-08-24 - REFUSE, correction en cours dans ce sprint.** `nadia-prof` a audite
l'increment livre et REFUSE (`squad/conformite/FICHE-bandit-manchot-accord-sujet-verbe-cm1.md`,
verdict `ddb0fc4`, branche `s19-nadia`). Cinq motifs, repris avec ses mots pour qu'un critere
correctif ne les affaiblisse pas :

1. **Decisif** - deux items sur vingt-et-un mettent le verdict sous le pli **a la reference du
   produit elle-meme (744 x 551 px, `DEC-0040`)**, pas dans une bande differee : "Demain, les
   enfants du quartier ___ un grand tournoi de football." et "Cet ete, mes cousins ___ une cabane
   au fond du jardin.", verdict a 564,19 px pour un pli a 551 px.
2. Les vingt-et-un indices impriment la reponse exacte en toutes lettres dans leur derniere clause
   ("comme dans 'ils jouent'"), ce qui contredit le critere deja ecrit dans cette fiche ("un indice
   qui donne la methode, pas le resultat") et vide `M5` de son sens : une manche resolue en lisant
   l'indice compte comme une reussite.
3. Sur quinze items a sujet de troisieme personne, le troisieme distracteur est systematiquement
   une forme en `-ons` qu'aucun enfant n'ecrirait, rendant ces items a trois propositions en
   apparence et deux en fait.
4. Deux items emploient un vocabulaire au-dessus du CM1 (*deferler*, *moudre*) et l'un des deux
   enonces se contredit lui-meme.
5. Les indices citent mots et terminaisons entre apostrophes droites plutot qu'avec les guillemets
   francais deja etablis par `SYN-6`/`SYN-9`.

**Issue, le meme jour - la condition posee ci-dessus est remplie, cette section est cloturee sans
story neuve.** `marc-dev` a corrige les cinq motifs (branche fusionnee `round-two` a `6d9c00a`) et
`nadia-prof` a reaudite l'arbre fusionne : **`VALIDE SOUS RESERVE`, aucune reserve bloquante**
(`squad/conformite/FICHE-bandit-manchot-accord-sujet-verbe-cm1.md`, second verdict, commit
`a81324c`, branche `s19-nadia`). Les cinq motifs sont leves un par un et verifies par sa propre
mesure - le motif decisif, notamment : largeur necessaire 647 px pour 680 px offerts (33 px de
marge), contre plus de 700 px au refus. Trois reserves non bloquantes restent ouvertes (`ASV-1` a
`ASV-3`, chantier vertical de s20 pour `ASV-1`) : voir `squad/programme/MATRICE-couverture.md`,
section francais CM1, pour le detail complet. La ligne de matrice "Accorder le verbe avec son
sujet" passe `couvert-valide` le meme jour.

### S19-4 - Distinguer nom et verbe dans une phrase tres simple (francais CP)

- **Besoin** : en tant qu'eleve de CP, je veux dire si un mot d'une phrase courte est un nom ou un
  verbe, afin de commencer a reperer comment une phrase est construite.
- **Competence programme visee** : francais, CP (cycle 2), domaine "Orthographe et grammaire",
  "Distinguer les principaux types de mots (nom, verbe) dans une phrase tres simple"
  (`squad/programme/francais/competences-cp.md`).
- **Matiere premiere verifiee, adaptation reelle requise, pas une reprise** :
  `contenu-repris/mots/cm1-nature-mot.yml` (16 items) classe des mots **isoles** (hors phrase) en
  quatre natures (nom, verbe, adjectif, determinant), a un niveau CM1. La competence CP demande
  exactement deux natures (nom, verbe) et exige que le mot soit presente **dans une phrase tres
  simple**, jamais isole. Aucun des deux ecarts n'est mineur : la forme (mot isole contre mot en
  phrase) et le perimetre (quatre natures contre deux) changent tous les deux. Ce paquet est donc
  une adaptation reelle qui repasse integralement devant `nadia-prof`, pas une simple reprise de
  matiere (`contenu-repris/README.md`).
- **Criteres d'acceptation** :
  - Etant donne chaque item, quand on lit son enonce, alors il presente une phrase tres simple
    (sujet, verbe, et au plus un complement court - vocabulaire et longueur de niveau CP, pas CM1)
    avec le mot cible marque par une ponctuation francaise, jamais un mot isole.
  - Etant donne un item, quand on lit ses propositions, alors elles sont exactement deux : "nom" et
    "verbe" - aucune troisieme nature, la competence CP n'en nommant que deux.
  - Etant donne le paquet complet, quand on le classe par bonne reponse, alors "nom" et "verbe"
    sont chacun representes par au moins un quart des items (repartition equilibree, aucune des
    deux natures dominante).
  - Etant donne le paquet complet, quand on lit le vocabulaire employe (sujets, verbes,
    complements), alors chaque mot est un mot courant de CP, deja rencontre ou plausible a ce niveau
    de lecture - pas un mot du vocabulaire CM1 du paquet repris.
  - Etant donne chaque item, quand on lit son indice, alors il aide a reconnaitre la nature du mot
    (par exemple : "un verbe dit ce qu'on fait") sans jamais nommer la reponse.
  - Etant donne le paquet complet, quand on le compte, alors il porte au moins 16 items.
  - Ce qui n'est pas testable par un automate et releve de la seule porte pedagogique de
    `nadia-prof`, sur un **arbitrage prealable a rendre avant tout code** (competence CP jamais
    instruite a ce niveau de detail) : que les phrases soient reellement lisibles par un enfant de
    CP en cours d'apprentissage de la lecture (longueur, vocabulaire, absence de mot irregulier non
    encore vu), et que le choix nom/verbe ne se resolve jamais par un indice de position plutot que
    par le sens du mot.
- **Lien decision** : `DEC-0042`, section 5.
- **Agent pressenti** : a affecter au planning par `sofia-sm`, arbitrage prealable de `nadia-prof`
  requis avant tout code.

**Criteres correctifs ajoutes le 2026-08-24 - deux reserves BLOQUANTES a l'audit de l'increment
livre** (`squad/conformite/FICHE-bandit-manchot-nature-mot-cp.md`, verdict `ddb0fc4`, branche
`s19-nadia`). Repris avec les mots de `nadia-prof` :

- **`NAT-5`** - "le mot cible est designe autrement que par les guillemets seuls, et jamais par la
  couleur seule." Un renfort visuel du mot cible, au-dela des guillemets francais deja en place,
  doit exister et etre juge par `nadia-prof`. Proposition de forme routee a `ines-design` (voir
  routage de `DEC-0044`), jamais tranchee par la seule couleur.
- **`NAT-9`** - la consigne servie ("Le mot entre guillemets est-il un nom ou un verbe ?") n'est pas
  lisible seule par un enfant de CP et est circulaire : elle nomme un signe (guillemets) que
  l'enfant ne connait pas pour designer le mot dont on parle. Etant donne la consigne, quand un
  enfant de CP la lit seul, alors elle ne nomme aucun signe typographique et reste comprehensible
  sans qu'il ait besoin de connaitre la convention des guillemets. `nadia-prof` propose "Nom ou
  verbe ?", deja le titre du jeu, des lors que le renfort visuel de `NAT-5` existe.

`nadia-prof` ecrit que les deux se reparent ensemble, dans le meme chantier. Reaudit requis avant
tout Done pedagogique sur cette ligne.

**Mise a jour du 2026-08-25 (hors sprint) - `NAT-9` tombe, `NAT-5` seule reste bloquante, sur un
motif renouvele.** L'utilisatrice a elargi son arbitrage sur l'accompagnement du CP a un etat
durable ("un eleve de CP est toujours accompagne par un adulte"), pas seulement le premier essai
(`DEC-0046`, section 3). `nadia-prof` en tire deux consequences ecrites dans
`squad/conformite/FICHE-bandit-manchot-nature-mot-cp.md`, amendements du 2026-08-25 :

- **`NAT-9` est LEVEE.** La consigne servie ("Le mot entre guillemets est-il un nom ou un verbe ?")
  est acceptee telle quelle. **Aucune reformulation n'est plus due** : le critere correctif ci-dessus
  sur `NAT-9` ne s'applique plus et `lea-dev` n'a rien a editer sur ce point.
- **`NAT-5` reste BLOQUANTE, sur un motif recentre.** Ce n'est plus la comprehension des guillemets
  par l'enfant (affaiblie par la presence durable d'un adulte) mais l'asymetrie entre une tache
  constante sur toute la partie (portee par la consigne, lue une fois) et une designation qui change
  a chaque manche (dix mots differents dans dix phrases differentes) : sans renfort visuel, c'est
  l'adulte qui doit pointer le mot dix fois par partie, ce que rien dans le produit ne lui demande de
  faire, et un enfant qui presse au hasard sur cette tache a deux reponses et trois essais finit
  chaque partie a 10 sur 10 sans avoir rien lu. Le libelle de `NAT-5` ne change pas.

Le chantier de s20 (voir `DEC-0047`, section 1, et `S20-1` ci-dessous) porte donc sur `NAT-5` seule.

### S19-5 - Migration ADR-0049 (accentuation des descripteurs de matiere)

**Non affectee jusqu'a ce jour, affectee au sprint 19 par `DEC-0042`.** La story complete, son
perimetre et son cout restent ceux ecrits section "Story de migration - Les paquets de contenu
servis portent leur matiere accentuee (execution d'ADR-0049)" et son complement du 2026-08-19,
plus haut dans ce document : je ne les reecris pas. **Perimetre reverifie a l'ouverture de s19,
inchange** : les 13 paquets servis (`adapters/adapter-content/src/main/resources/contenu/*.yaml`)
portent tous encore `matiere: "Francais"` ou `matiere: "Mathematiques"` en ASCII, sans exception.

**Point de sequencement ajoute, route a `sofia-sm` et `theo-archi`** (`DEC-0042`, section 5) :
l'ordre de fusion entre cette story et `S19-1` a `S19-4` determine la graphie a ecrire dans les
quatre paquets neufs, sous la contrainte symetrique d'`UneMatiereNAQuUneSeuleGraphieDansLeContenu-
ServiTest` - je ne fixe pas cet ordre ici.

- **Lien decision** : `ADR-0049-une-matiere-s-ecrit-accentuee-dans-la-donnee-de-catalogue.md`,
  `DEC-0042`, section 6.
- **Agent pressenti** : a affecter au planning par `sofia-sm`, avec `theo-archi` pour le mecanisme
  de la bascule et l'ordre de fusion.

### S19-6a - Le pli ne mord pas entre 576 et 719 px de viewport, jeux "Les synonymes", "Les antonymes" et "Le bon accord" (SYN-16)

> Scindee de l'ancienne `S19-6` par `DEC-0043` : `BPM-8` (ci-dessous, `S19-6b`) relevait d'un
> mecanisme different et n'avait recu aucune mesure de faisabilite - fait retire depuis, voir
> plus bas. Affectee au perimetre de s20 par `DEC-0047`, suspendue le meme jour (correctif de
> largeur insuffisant), et **retiree le 2026-08-25 (troisieme passage)** : l'utilisatrice sort la
> bande visee de la cible du produit (amendement de `DEC-0040`). Cette story n'a plus d'objet tel
> qu'ecrite ; ce qu'elle portait de reel se retrouve, plus large, en section "Mise a jour du
> 2026-08-25 (troisieme passage)" ci-dessous.

**Mise a jour du 2026-08-25 (troisieme passage, `camille-po`) - la bande sort de la cible sur
arbitrage de l'utilisatrice ; cette story est retiree, un fait plus large est route a `nadia-prof`,
rien n'est ferme a la reference.**

`theo-archi` a chiffre la troisieme lecture que j'avais nommee sans la choisir (amendement
precedent) : reviser la bande visee plutot que le produit. L'utilisatrice tranche, verbatim :
*"amende `DEC-0040`, la bande passe hors cible"*. Trace complet, avec l'arbitrage et sa consequence
sur `DEC-0040`, `DEC-0041` et `DEC-0043` : amendement du 2026-08-25 a `DEC-0047`, et amendement
dedie de `DEC-0040`.

**Ce que j'ecris ici, pour cette story precisement.** `S19-6a` visait a fermer un defaut *"entre
576 et 719 px de viewport"*. Cette largeur de viewport ne designe plus un appareil de la cible :
elle designait une fenetre non maximisee ou partagee sur un ordinateur, et c'est precisement cette
lecture que l'utilisatrice retire. **Je reverse ici un arbitrage qui est le mien** : j'avais
moi-meme tranche a la cloture de s18 (`DEC-0041`, amendement du 2026-08-23) que cette bande
*"designe un appareil reel, pas un vide de reference"*, motif exact qui avait fait entrer `SYN-16`
au perimetre puis a s20. Ce n'est pas une precision que j'apporte a cette decision, c'est un
renversement complet, et je le dis en ces termes pour que personne ne le lise comme une nuance.
**Ce que cela retire du parc vise** : la cible ordinateur ne couvre plus une fenetre de navigateur
non maximisee ou partagee en deux, y compris sur un ecran de 1280 px (le plus etroit identifie par
`ines-design`) - seule une fenetre maximisee, plein ecran, reste couverte sur cet axe. La tablette
n'est pas concernee : son viewport ne varie pas avec une fenetre, `DEC-0040` continue de la couvrir
en entier.

**Ce que cet arbitrage ne fait pas, et c'est le point que je ne laisse pas passer.** `theo-archi` a
mesure qu'a la reference meme du produit (744 x 551, ou le Done est prononce), **95 items sur 296
(32 %) ont deja leur bloc de message sous le pli**, sur dix jeux dont `cm1-synonymes` et
`cm1-antonymes`, tous deux `couvert-valide`. Sa conclusion, que je retiens mot pour mot : *"`SYN-16`
a ete instruit trois sprints comme un defaut de la bande. Il ne l'est pas : la bande aggrave un
ecart qui existe a la reference."* Sortir la bande de la cible retire donc **la part aggravee**
d'un defaut plus large, pas le defaut lui-meme. Je ne le referme pas en fermant cette story.

**Je ne juge aucun de ces 95 items et je ne clos rien a la reference sur la foi de cet arbitrage.**
`theo-archi` refuse lui-meme de les qualifier - *"combien de ces 95 relevent d'une reserve deja
acceptee est la lecture de `nadia-prof`"* - et je le route a elle telle quelle, sans la devancer.
Aucune ligne de `squad/programme/MATRICE-couverture.md` ne bouge par ce constat ; aucun statut de
couverture n'est prononce ici.

**`S19-6a` est retiree du backlog affecte.** Ses criteres d'acceptation, ecrits pour une bande qui
n'est plus visee, ne sont pas repris comme tels par la question de reference : celle-ci porte sur
dix jeux entiers a la reference, pas sur un plafond de largeur dans une bande, et elle n'est pas
mienne a instruire en story tant que `nadia-prof` n'a pas dit ce qu'elle en lit. Je ne rouvre pas le
perimetre de s20 au-dela de cet arbitrage : le planning reste gele par `sofia-sm`.

**Mise a jour du 2026-08-25 (`camille-po`, avis de faisabilite de `theo-archi`, branche `s20-theo`,
commit `7a398b9`) - le correctif de largeur est suspendu, la famille verticale est rouverte.**
J'ai tranche `S19-6a` le 2026-08-25 sur un residu de 3 px sur 144 (141/144 de couverture), dans
l'esprit de `DEC-0025` - un residu assez petit pour etre assume sans discussion. C'etait vrai a
`L = 539` px (largeur necessaire de `cm1-synonymes`, seul pire cas connu a l'ecriture de cette
story). Ce n'est plus vrai a `L = 647` px (`cm1-accord-sujet-verbe`, pire cas que j'ai moi-meme
porte a `DEC-0043` quelques heures plus tot, sans relier les deux gestes). `theo-archi` a refait le
calcul avec la bonne valeur (`ADR-0059`, amendement) : **la couverture tombe a 33 largeurs sur
144**, et **le residu de bande passe de 3 px a 111 px, soit 77 % de la bande que cette story est
chargee de fermer**. La proposition de 620 px d'`ines-design` n'est plus seulement "a confirmer" :
elle est **exclue par l'inegalite du plafond** (`P >= L + R + 40`, section 3.2 a de son avis) - elle
laisse 580 px a une rangee qui en demande 647.

**Assumer 3 px sur 144 et assumer 111 px sur 144 ne sont pas le meme arbitrage, et je refuse de
glisser du premier au second sans le nommer.** J'avais explicitement pose que le produit n'accepte
pas un garde-fou a marge nulle parce qu'"un garde-fou qui cede en silence ne vaut rien" (`DEC-0043`,
section 1) ; un correctif qui ne couvre que 23 % de la bande visee, et qui **laisse precisement hors
de portee le cas d'usage qui justifie la bande** - un ecran de 1280 px partage en deux fenetres, soit
environ 640 px, bien en dessous du nouveau plancher de 687 px (`DEC-0041`) - cederait en silence sur
l'essentiel de ce pour quoi la bande a ete ouverte, tout en ayant l'air ferme. Je ne le retiens pas.

**Decision : je ne fais pas implementer de valeur de plafond ce sprint. Je rouvre la famille
verticale comme piste principale**, exactement celle que j'avais ecartee a l'ouverture de cette
story au seul motif que la largeur couvrait "presque tout" (`DEC-0043`, section 1) - un motif que
le nouveau chiffre retire. `nadia-prof` a deja chiffre le cout vertical des items a quatre
propositions a 68,19 px (et non 43,19 px, `squad/programme/MATRICE-couverture.md`, `AGN-1`) ;
`theo-archi` ecrit lui-meme qu'aucun garde-fou de ce depot n'epingle un terme vertical aujourd'hui
(`ADR-0059`) - c'est un chantier a instruire, pas a implementer a l'aveugle.

**Ce que je ne ferme pas et que je nomme pour l'utilisatrice, sans lui demander de trancher
aujourd'hui** : si la famille verticale s'avere elle aussi trop couteuse, la seule piste restante
est de reviser la bande visee elle-meme - amender `DEC-0040`/`DEC-0041` plutot que le produit. Je
ne le propose pas maintenant : c'est une lecture parmi trois, elle n'est pas mienne a choisir tant
que la seconde (verticale) n'a pas ete chiffree, et je prefere le dire a l'utilisatrice avant qu'elle
ne le decouvre que le pretendre tranchee.

**Ce qui ne bouge pas** : le Done pedagogique deja acquis sur les trois jeux (`cm1-synonymes`,
`cm1-antonymes`, `cm1-accord-sujet-verbe`) est etranger a cette suspension - il se mesure a la
reference 744 x 551, jamais a la bande basse (`DEC-0040`).

**Mise a jour du 2026-08-25 (`camille-po`, `DEC-0047`) - le perimetre reel touche trois paquets, pas
un seul.** `DEC-0043` a ete amendee deux fois apres l'ecriture initiale de cette story, sur des
paquets audites entre-temps par `nadia-prof` : `cm1-antonymes` porte une largeur de rangee
necessaire de 547 px (contre 539 px pour `cm1-synonymes`), et `cm1-accord-sujet-verbe`, valide le
meme jour, porte le pire cas reel a **647 px** et ne couvre rien dans la bande basse tant que cette
story n'est pas livree. Le besoin, le constat et les criteres ci-dessous sont corriges pour porter
les trois jeux ; je ne les laisse pas nommer "Les synonymes" seul alors que le pire cas connu
appartient desormais a un autre jeu.

- **Besoin** : en tant qu'eleve de CM1 qui joue sur un ordinateur dont la fenetre du navigateur
  n'occupe pas tout l'ecran (fenetre partagee, fenetre non maximisee), je veux voir entierement les
  propositions de reponse des jeux "Les synonymes", "Les antonymes" et "Le bon accord", afin de
  jouer dans les memes conditions que sur un ordinateur en plein ecran.
- **Competence programme visee** : transverse - pas de contenu pedagogique.
- **Constat mesure, pas suppose** (`nadia-prof`, `squad/conformite/FICHE-bandit-manchot-synonymes-
  cm1.md`, verdict du 2026-08-23 puis mesure du 2026-08-24 ; `FICHE-bandit-manchot-antonymes-
  cm1.md` ; `FICHE-bandit-manchot-accord-sujet-verbe-cm1.md`, second verdict) : le correctif livre
  pour `SYN-11` ne mord qu'a partir de 720 px de viewport. Entre 576 et 719 px, "Les synonymes"
  garde cinq items sur seize sous le pli (pire cas 594,19 px) ; "Le bon accord" ne couvre rien dans
  la bande a 500 px offerts aujourd'hui et s'y replie integralement. Largeur de rangee
  **necessaire**, mesuree et non supposee : 539 px pour `cm1-synonymes`, 547 px pour
  `cm1-antonymes`, **647 px pour `cm1-accord-sujet-verbe` - le pire cas connu de la bande**. Non
  bloquant pour le Done pedagogique deja rendu sur chacun des trois jeux, qui se mesure contre la
  reference unique 744 x 551 (`DEC-0040`).
- **Perimetre de la bande, tranche par `camille-po` a la cloture de s18** (`DEC-0041`, amendement
  du 2026-08-23) : la bande 576-719 px n'est pas un vide de reference. Un ordinateur de la cible
  produit (1280 px de dalle au plus etroit identifie par `ines-design`) partage en deux fenetres,
  ou simplement non maximise, donne une largeur de viewport dans cette bande - un geste ordinaire
  sur ordinateur, pas un usage marginal. C'est donc un trou reel du produit sur sa cible actuelle,
  et non un appareil hors cible.
- **Criteres d'acceptation - suspendus pour la piste de largeur, a reprendre pour la piste
  verticale une fois chiffree** : les criteres ecrits pour un correctif de largeur supposaient une
  couverture de 141/144 ; ils ne tiennent plus a 33/144 et je ne les affiche plus comme la
  definition de "fini" de cette story. Je ne les efface pas : ils redeviennent utilisables tels
  quels si l'utilisatrice choisit un jour d'assumer le residu de 111 px plutot que de payer la
  piste verticale.
  - Etant donne chacun des trois jeux ("Les synonymes", "Les antonymes", "Le bon accord"), quand on
    rend chaque manche a une largeur de viewport comprise entre la borne basse retenue et 719 px
    inclus, dans l'etat le plus charge (revelation, trois etats d'echec), alors les propositions de
    reponse restent entierement visibles au-dessus du pli, sur la totalite des items des trois
    paquets - **valable pour la piste retenue, largeur ou verticale, quelle qu'elle soit**.
  - Le residu de bande qui reste hors correctif, sous la borne basse retenue, est un residu assume
    et documente avec son motif et son chiffre reel dans la fiche de conformite au moment de la
    mesure finale, pas un silence - **le chiffre a documenter n'est plus 3 px, il doit etre celui de
    la piste effectivement retenue**.
  - Etant donne le correctif livre, quel qu'il soit, alors il ne fait regresser aucune des mesures
    deja tenues a 720 px et au-dela (`SYN-11`), ni la clause "en-tete aligne" ou "aucun element a
    presser ne se rapproche du bord plus que le rembourrage de la zone" de `SYN-14`.
  - Ce que cette story ne tranche pas, entierement route a `theo-archi` : le choix entre la piste de
    largeur (`ADR-0059`, suspendue) et la piste verticale (a chiffrer, avis de faisabilite du).
  - Ce qui n'est pas testable par un automate et releve de la seule porte pedagogique de
    `nadia-prof` : la mesure finale sur navigateur reel, a `scrollY = 0` verifie, sur la bande
    retenue et sur `SYN-14` - y compris une remesure de `SYN-14` si la piste de largeur revient un
    jour, puisqu'elle n'a jamais ete mesuree au-dela d'un plafond de 579 px (avis de faisabilite de
    `theo-archi`, section 3.3).
- **Lien decision** : `DEC-0041`, amendement du 2026-08-23 ("`SYN-16` : la bande 576-719 px designe
  un appareil reel") ; `DEC-0043` ; `DEC-0047` et son amendement du 2026-08-25 (suspension, famille
  verticale rouverte) ; avis de faisabilite de `theo-archi`, `s20-theo` a `7a398b9`.
- **Agent pressenti** : `theo-archi` pour l'avis de faisabilite de la piste verticale (a instruire) ;
  `sofia-sm` pour le sequencement et la capacite, une fois cet avis rendu ; `ines-design` n'a plus
  de valeur a reproposer sur la piste de largeur tant qu'elle n'est pas retenue.

### S19-6b - L'indice reste coupe par le pli entre 576 et 719 px de viewport, jeu "Le bon petit mot" (BPM-8)

> Scindee de l'ancienne `S19-6` par `DEC-0043` au motif de "mecanismes differents" - motif que
> `theo-archi` etablit faux (meme ecran, meme cause, meme population que `S19-6a`, dix jeux et non
> deux, voir amendement du 2026-08-25 a `DEC-0047`). **Retiree le 2026-08-25 (troisieme passage)** :
> la bande qui la motivait sort de la cible du produit, sur le meme arbitrage que `S19-6a`.

**Mise a jour du 2026-08-25 (troisieme passage, `camille-po`) - retiree avec `S19-6a`, pour le meme
motif.** L'utilisatrice sort la bande 576-719 px de la cible (*"amende `DEC-0040`, la bande passe
hors cible"*), et le defaut que cette story visait - l'indice de "Le bon petit mot" coupe par le
pli dans cette bande - ne designe plus un appareil de la cible. `theo-archi` avait par ailleurs
retire, avant cet arbitrage, sa propre declaration d'infaisabilite de `S19-6b` (elle reposait sur
une dependance a `S19-6a` qui n'existe plus) et mesure que "Le bon petit mot" est le cas le moins
cher des dix jeux touches, entierement ferme par le plus modeste des budgets verticaux **dans la
bande** - un fait que je verse au dossier plutot que de le perdre, meme si la bande qui le rendait
pertinent sort de la cible.

**Le meme residu que pour `S19-6a` existe a la reference, plus etroit ici** : `theo-archi` mesure
seulement 2 items sur 296 de "Le bon petit mot" sous le pli a 744 x 551 (deficit 2,19 px) - le plus
faible des dix jeux touches. Meme regle que pour `S19-6a` : je ne le qualifie pas, je le verse a la
lecture de `nadia-prof` avec le reste de la population de dix jeux, je ne clos rien ici.
- **Besoin** : en tant qu'eleve de CM1 qui joue sur un ordinateur dont la fenetre du navigateur
  n'occupe pas tout l'ecran, je veux voir entierement l'indice du jeu "Le bon petit mot", afin de
  jouer dans les memes conditions que sur un ordinateur en plein ecran.
- **Competence programme visee** : transverse - pas de contenu pedagogique.
- **Constat mesure, pas suppose** (`nadia-prof`, `squad/conformite/FICHE-bandit-manchot-homophones-
  cm1.md`, verdict du 2026-08-23) : sous 720 px de viewport, l'indice reste coupe par le pli sur
  six a sept items sur vingt-quatre (mesure a 700 et 719 px). Non bloquant pour le Done pedagogique
  deja rendu (`VALIDE SOUS RESERVE`), qui se mesure contre la reference unique 744 x 551.
- **Perimetre de la bande** : identique a `S19-6a`, tranche par `DEC-0041`.
- **Criteres d'acceptation** :
  - Etant donne le jeu "Le bon petit mot", quand on rend chaque manche a une largeur de viewport
    comprise entre 576 et 719 px inclus, dans l'etat le plus charge, alors l'indice reste
    entierement visible au-dessus du pli, sur la totalite des vingt-quatre items, ou sur la borne
    de bande qu'un avis de faisabilite documentera comme atteignable si la bande entiere s'avere
    hors de portee, sur le meme modele que `S19-6a`.
  - Ce que cette story ne tranche pas et route a `theo-archi` : le mecanisme de correction, dont
    la nature (largeur ou hauteur de contenu) n'est pas encore etablie pour ce jeu.
- **Avis de faisabilite recu le 2026-08-25 (`theo-archi`, `s20-theo` a `7a398b9`) - infaisable ce
  sprint, et le motif n'est pas la capacite.** Deux faits qui deplacent cette story plutot que de la
  debloquer : le mecanisme **n'est pas independant de celui de `S19-6a`**, contrairement a ce que
  `DEC-0043` supposait en scindant - le plafond maison de `S19-6a` porte sur des selecteurs
  partages par tous les jeux (`.entete-contenu`, `.zone-jeu`), donc "Le bon petit mot" recoit tout
  changement de largeur de bande sans qu'aucune ligne ne le vise ; et **aucun chiffre unique ne
  resume ce defaut** comme `L` le faisait pour la largeur - la position de l'indice est la somme des
  hauteurs de tout ce qui le precede dans le flux, ce qui demande une decomposition et non une
  mesure. Une mesure prise aujourd'hui decrirait une mise en page que le denouement de `S19-6a`
  (piste de largeur ou verticale, encore a choisir) va changer. `theo-archi` recommande de mesurer
  `BPM-8` **une fois que `S19-6a` aura effectivement livre un correctif**, quelle que soit la piste
  retenue - pas avant, et pas sur la base d'un merge qui n'a plus lieu ce sprint.
- **Lien decision** : `DEC-0041`, amendement du 2026-08-23 ; `DEC-0043`, section 2 ; `DEC-0047`
  et son amendement du 2026-08-25 ; avis de faisabilite de `theo-archi`, section 4.
- **Agent pressenti** : reste non affectee. Mesure a reprendre par `nadia-prof` une fois qu'un
  correctif de `S19-6a` (largeur ou vertical) est effectivement fusionne, sequencement a
  et l'affectation d'une story deviennent candidates d'un sprint suivant, pas necessairement s20.

## Sprint 20 - `NAT-5` (mot cible designe), une competence neuve en contenu seul, conception de la saisie reprise

Perimetre fixe par `DEC-0047-perimetre-sprint-20-nat-5-en-trois-gestes-scope-limite-a-cp-nature-
mot-reperes-cm1-ouverte-syn-16-etendue.md`, complete par ses deux amendements du 2026-08-25 (les
trois arbitrages de l'utilisatrice sur la saisie, le clavier et l'adulte accompagnant ; puis
l'amendement de `DEC-0040` qui sort la bande basse de la cible). Trois chantiers restent affectes :
le renfort du mot cible (`NAT-5`, `S20-1`) ; une competence neuve en contenu seul (`S20-2`) ; et la
reprise de la conception de la mecanique de saisie, **sans capacite de developpement affectee**, sur
le meme mode que le Lot C de `DEC-0042` a s19 - nommee et routee dans l'amendement du 2026-08-25 a
`DEC-0047`, jamais recopiee ici sous forme de story a criteres d'acceptation.

`S19-6a` et `S19-6b`, ci-dessus, sont **retirees** : la bande 576-719 px qu'elles visaient sort de
la cible du produit sur arbitrage de l'utilisatrice (*"amende `DEC-0040`, la bande passe hors
cible"*), reversant l'arbitrage que j'avais moi-meme rendu a s18. Ce retrait ne ferme pas un defaut
plus large que `theo-archi` a trouve **a la reference meme du produit** (95 items sur 296 sous le
pli, dix jeux dont deux `couvert-valide`) : ce fait est route a `nadia-prof`, non instruit en story
ce sprint, et le planning reste gele par `sofia-sm`. Detail complet : amendement du 2026-08-25
(troisieme passage) a `DEC-0047`.

**Mise a jour du 2026-08-27 (s20, cloture)** : `nadia-prof` a rendu sa mesure independante sur ce
meme defaut (`REP-8`, catalogue servi, 17 jeux, 157 items, methode et perimetre distincts de la
mesure de `theo-archi`), sans qualifier les items de l'un ou l'autre releve. Confrontation chiffree
et backlog : `DEC-0050` et la section "Reserves de cloture s20", sous `S20-2` ci-dessous. Ni ce fait
ni `REP-8` ne sont instruits en story affectee a s21 par ce document.

### S20-1 - Le mot cible de "Nom ou verbe ?" est designe autrement que par les guillemets seuls (`NAT-5`)

- **Besoin** : en tant qu'eleve de CP qui joue a "Nom ou verbe ?", je veux voir clairement quel mot
  de la phrase je dois classer, afin de ne pas dependre d'un signe (les guillemets francais) que je
  n'ai pas encore appris a lire.
- **Competence programme visee** : francais, CP (cycle 2), "Distinguer les principaux types de mots
  (nom, verbe) dans une phrase tres simple" (`squad/programme/francais/competences-cp.md`). Cette
  story ne couvre pas la ligne a elle seule : elle leve la derniere reserve bloquante d'un jeu deja
  livre (`S19-4`).
- **Origine et etat de la reserve, pour ne rien reecrire de faux** : `NAT-5` est la seule reserve
  bloquante restant sur `bandit-manchot-nature-mot-cp` (`squad/conformite/FICHE-bandit-manchot-
  nature-mot-cp.md`, amendements du 2026-08-25). `NAT-9`, qui portait sur la meme fiche, est
  **levee** et ne fait plus partie du perimetre de cette story (voir la mise a jour sous `S19-4`
  ci-dessus). Le jeu est hors catalogue depuis `DEC-0045` et le reste tant que cette story n'est
  pas livree et reauditee.
- **Ce que cette story exige, dans les mots de `nadia-prof`, repris a l'identique plutot que
  traduits en une propriete plus commode a tester** : le mot cible doit etre designe autrement que
  par les guillemets francais seuls, et jamais par la seule couleur. Le motif exact : la consigne
  porte une **tache constante** sur toute la partie, lisible une seule fois ; le marquage doit
  porter la **designation**, qui **change a chaque manche** - sans lui, c'est l'adulte accompagnant
  qui doit pointer le mot dix fois par partie, ce que rien dans le produit ne lui demande de faire.
- **Decision de forme, prise ici et pas ailleurs : le renfort visuel entre en service sur
  `cp-nature-mot` seul, ce sprint. Il n'est pas etendu a `cm1-synonymes` ni `cm1-antonymes`.**
  `ines-design` a mesure, par injection sur l'application servie (branche `hs-ines-nat5`, commit
  `e5a8360`, non fusionnee) que la regle generique proposee (`.mot-designe`) :
  - ne coute rien de mesurable a la reference du produit (744 x 551, `DEC-0040`) ni a 720 px et
    au-dela, sur les trois paquets qui partagent la convention du mot marque entre guillemets
    (`cm1-synonymes`, `cm1-antonymes`, `cp-nature-mot`) ;
  - **cree un residu neuf, mesure, sur `cm1-antonymes` a 640 px** (dans la bande 576-719 px deja
    fragile, `DEC-0043`/`S19-6a` ci-dessus) : un item qui n'etait pas sous le pli le devient, a
    cause du seul renfort ;
  - n'en cree aucun sur `cp-nature-mot` lui-meme a la reference exacte que `nadia-prof` a deja
    utilisee pour auditer ce jeu (744 x 551, 0 item sur 16 ne bascule).

  `cm1-synonymes` et `cm1-antonymes` sont deja `couvert-valide` : `ines-design` ecrit elle-meme
  qu'elle ne recommande pas d'y appliquer ce renfort sans repasser devant `nadia-prof` au titre de
  leur propre Done deja acquis. Etendre le renfort a ces deux jeux ne fait avancer aucune ligne de
  matrice - `NAT-5` ne porte que sur `cp-nature-mot` - et ferait courir un risque mesure a un Done
  deja rendu, dans une bande que `S19-6a` (ci-dessus) est justement charge de fermer ce meme sprint.
  Je ne vois aucune raison produit de payer ce risque maintenant. **Ce que je ne ferme pas** : le
  jour ou `S19-6a` est livree et mesuree, la question de generaliser le marquage aux jeux qui
  partagent deja la convention du mot entre guillemets peut se reposer, sur un terrain plus sur -
  ce n'est pas decide ici, faute d'un motif qui l'exige aujourd'hui.
- **Criteres d'acceptation, corriges le 2026-08-25 (avis de faisabilite de `theo-archi`,
  section 1.2) - je prescrivais une forme de contrat qui n'est ni celle d'`ines-design` ni celle
  que l'architecture retient** : le premier critere ci-dessous disait la donnee "distincte de
  l'avant et de l'apres de la phrase", une formulation a moi, jamais celle d'`ines-design` (qui
  route la forme de la donnee sans la prescrire) ; `theo-archi` refuse un triplet a trois champs
  pour ce motif precis : douze paquets sans mot cible devraient alors porter deux composantes
  vides, la dette 2 de `CLAUDE.md`. Je reprends son verdict plutot que ma propre formulation :
  - Etant donne le controleur qui rend l'enonce d'un item de `cp-nature-mot`, quand on lit le
    contrat qu'il expose au gabarit, alors le mot cible est porte par **une composante facultative
    unique** sur l'item - jamais deduite par un parcours des guillemets dans le gabarit partage
    (`fragments/manche.html`), qui reste ignorant de toute convention de contenu (`ADR-0062`).
  - Etant donne un item qui ne declare aucune part designee (`cm1-synonymes`, `cm1-antonymes`,
    et tout futur paquet sans mot cible), quand le controleur rend son enonce, alors il produit un
    segment unique, non designe, sans branche ni valeur vide portee par le contenu - le jeu non
    marque reste le cas ordinaire, jamais un cas particulier a verifier en plus (`ADR-0062`).
  - Etant donne le rendu servi d'un item de `cp-nature-mot` qui declare une part designee, quand on
    lit le HTML de l'enonce, alors le mot cible est entoure d'un element distinct du reste de la
    phrase, par au moins deux canaux non colores (par exemple une boite de fond, un rembourrage,
    une taille de police superieure) - jamais par la seule couleur.
  - Etant donne les seize items de `cp-nature-mot`, quand on les rend a la reference du produit
    (744 x 551, `DEC-0040`), alors aucun ne bascule a deux lignes du seul fait du renfort (deja
    mesure a 0 sur 16 par `ines-design`, ce critere en exige la non-regression).
  - Etant donne `cm1-synonymes` et `cm1-antonymes`, quand on inspecte leur contenu servi et leur
    rendu, alors aucun des deux ne porte le marquage `.mot-designe` ni aucune donnee de mot cible
    distincte a l'issue de cette story - la portee du changement de contrat reste `cp-nature-mot`
    seul, ce sprint.
  - Ce que cette story ne tranche plus, tranche par `theo-archi` dans `ADR-0062` : la forme exacte
    du contrat est une composante facultative unique sur `Question`, l'enonce restant la chaine
    entiere, et le controleur en derive une suite de segments consommee par le gabarit en une
    boucle unique - ni une deduction sur les guillemets, ni un triplet a trois champs.
  - Ce que cette story ne tranche pas et route a `ines-design`/`nadia-prof` : la regle CSS exacte
    du renfort (couleur de fond, rembourrage, taille de police) - la proposition de la section
    ci-dessus est un candidat, pas une valeur figee.
  - Ce qui n'est pas testable par un automate et releve de la seule porte pedagogique de
    `nadia-prof` : que la forme retenue satisfasse `NAT-5` pour un enfant de CP, et que la consigne
    et le marquage ensemble ne soient plus circulaires. Reaudit complet de `bandit-manchot-nature-
    mot-cp` requis avant tout retour au catalogue (`DEC-0045`, section 2).
- **Lien decision** : `DEC-0044`, `DEC-0045`, `DEC-0046` (levee de `NAT-9`), `DEC-0047` et son
  amendement du 2026-08-25 (avis de faisabilite de `theo-archi`, critere de contrat corrige) ;
  `ADR-0062` (forme du contrat).
- **Agent pressenti** : `theo-archi` (forme du contrat), un developpeur affecte par `sofia-sm`
  (implementation), `ines-design` (regle CSS, deja proposee), `nadia-prof` (verdict de forme et
  reaudit), `sofia-sm` (retour au catalogue dans le meme geste que la fusion du verdict favorable,
  mecanisme de `DEC-0045`).

**Mise a jour du 2026-08-25 - livree, VALIDE, Done pedagogique acquis.** `nadia-prof` a reaudite
l'increment (`round-two` a `f4e0808`, `ADR-0062`) et prononce **VALIDE** : plus aucune reserve
bloquante (`NAT-9` levee le matin, `NAT-5` levee ce soir sur mesure - 16/16 items marques, 0/16
sous le pli, cout nul verifie sur les dix-sept autres jeux). Deux points restent ouverts, non
bloquants : `NAT-10`, `NAT-11` (inchangees) et `NAT-12`, neuve (la boite du marquage n'a pas de
contraste de luminance ; la designation survit neanmoins a la suppression de toute couleur par
l'agrandissement seul, ce que `NAT-5` exigeait). Verification complete et perimetre exact de ce
Done : `DEC-0048`. **Geste 3 execute** : `bandit-manchot-nature-mot-cp` revient au catalogue dans
le meme geste que cette decision. La ligne de matrice "Distinguer les principaux types de mots"
passe `couvert-valide` (14/43, 32,6 %).

### S20-2 - Reperer et corriger la majuscule et la ponctuation dans une phrase donnee (francais CM1)

- **Besoin** : en tant qu'eleve de CM1, je veux dire ce qui manque a une phrase donnee (majuscule
  ou point) ou reconnaitre qu'elle est deja correcte, afin de reperer les reperes qui font qu'une
  phrase est bien ecrite, avant de devoir les appliquer moi-meme dans mon propre texte.
- **Competence programme visee** : francais, CM1 (cycle 3), domaine "Orthographe et grammaire",
  "Reperer et corriger la majuscule et la ponctuation dans une phrase donnee (majuscule initiale,
  point final, majuscule au nom propre, point d'interrogation d'une question)"
  (`squad/programme/francais/competences-cm1.md`, competence detachee et amendee par `DEC-0046`).
- **Forme retenue, dans les mots de `nadia-prof`** (`squad/conformite/ARBITRAGE-PREALABLE-s19-
  quatre-proxys-en-attente-depuis-s13.md`, section B.3) : une phrase unique par item, et des
  propositions courtes qui nomment ce qui manque - "il manque un point", "il manque une
  majuscule", "il manque une majuscule au prenom", "cette phrase est correcte" - jamais quatre
  versions completes de la meme phrase (forme ecartee explicitement, charge cognitive trop haute
  et rangees de propositions trop larges, dette deja rencontree trois fois sur ce produit).
- **Matiere premiere** : aucune identifiee dans `contenu-repris/` sur ce repere precis - le paquet
  est integralement neuf.
- **Criteres d'acceptation**, repris des quatre conditions prealables de `nadia-prof` (`REP-1` a
  `REP-4`, memes references) :
  - Etant donne chaque item, quand on lit sa phrase, alors elle est courte, de vocabulaire CM1, et
    ne contient aucune virgule (`REP-1`).
  - Etant donne le paquet complet, quand on compte les items dont la phrase ne porte aucune erreur,
    alors ils representent au moins un quart du paquet, et leur bonne reponse est "cette phrase est
    correcte" (`REP-2`).
  - Etant donne un item qui porte une erreur, quand on lit ses quatre reperes possibles (majuscule
    initiale, point final, majuscule au nom propre, point d'interrogation), alors un seul est en
    jeu dans cette phrase - jamais une phrase qui cumule deux erreurs a la fois (`REP-3`).
  - Etant donne le paquet complet, quand on le classe par repere teste, alors les quatre reperes de
    l'intitule (majuscule initiale, point final, majuscule au nom propre, point d'interrogation)
    sont chacun representes, aucun ne depassant la moitie des items (`REP-4`).
  - Etant donne chaque item, quand on lit son indice, alors il aide a reperer le signe attendu sans
    jamais nommer la reponse.
  - Etant donne le paquet complet, quand on le compte, alors il porte au moins 16 items.
  - Ce qui n'est pas testable par un automate et releve de la seule porte pedagogique de
    `nadia-prof` : que chaque phrase soit reellement de niveau CM1, que les quatre propositions par
    item soient plausibles sans etre ambigues entre elles, et que la composition du paquet tienne
    reellement `REP-1` a `REP-4` - aucun test de ce depot ne les verifie (meme famille que `DEN-1`
    a `DEN-8` et `NAT-1` a `NAT-8`).
- **Identifiants proposes, a confirmer par le developpeur** : `id: bandit-manchot-reperes-cm1`,
  `paquet: cm1-reperes`.
- **Lien decision** : `DEC-0046`, section 2 ; `ARBITRAGE-PREALABLE-s19-quatre-proxys-en-attente-
  depuis-s13.md`, section B ; `DEC-0047`.
- **Agent pressenti** : a affecter au planning par `sofia-sm`. Aucun arbitrage prealable
  supplementaire n'est du : `nadia-prof` a deja instruit la forme et les quatre conditions avant
  tout code.

**Mise a jour du 2026-08-25 - increment livre, audite, REFUSE.** `nadia-prof` a audite l'increment
(`round-two` a `f4e0808`) et prononce **REFUSE**, sur deux motifs mesures dont elle declare
elle-meme l'origine dans sa propre specification (`squad/conformite/FICHE-bandit-manchot-reperes-
cm1.md`, branche `hs-nadia-arbitrages`, commit `9c8aa7b`). `lea-dev` a tenu `REP-1` a `REP-3` et
l'exactitude des seize enonces ; les deux motifs de refus sont ailleurs. **Le jeu est retire du
catalogue** (`DEC-0048`, puis `DEC-0049` pour ce second motif independant) ; la ligne de matrice
reste `en cours`. Detail complet et gravite du trou de gouvernance associe : `DEC-0049`.

**Criteres correctifs, repris avec les mots de `nadia-prof` pour qu'ils ne soient pas affaiblis** :

- **`REP-7` (pli)** - Etant donne chaque item, quand on lit le verdict et l'indice apres un echec a
  la reference 744 x 551, alors ils restent entierement au-dessus du pli, sur les seize items - pas
  0 sur 16 comme mesure sur l'increment refuse (pire cas 741,2 px pour un pli a 551, soit 190,2 px
  de deficit).
- **`REP-8` (le quatrieme repere)** - Etant donne le paquet complet, quand on lui ajoute un
  cinquieme libelle qui distingue le point d'interrogation du point final, alors au moins trois
  items exigent cette distinction pour etre resolus (une phrase interrogative sans signe final a
  pour bonne reponse ce libelle ; une phrase declarative sans signe final a pour bonne reponse celui
  du point) - pas zero comme mesure (un enfant ignorant totalement le point d'interrogation
  obtenait 16 sur 16).
- Ce que `nadia-prof` ne demande pas : ni de reduire le nombre d'items, ni de toucher aux seize
  phrases, qu'elle juge bonnes des l'ouverture. Elle ne dicte aucun libelle : la forme revient a
  `lea-dev`, la mise en page des cinq propositions a `ines-design` si necessaire.

**Deux reserves non bloquantes, consignees, qui ne motivent pas ce refus** :

- **`REP-5`** - Sur les items 8 et 9, l'indice nomme le mot "prenom", qui est aussi le seul mot du
  libelle correct - un enfant peut apparier les deux mots sans rien savoir (`M21`).
- **`REP-6`** - L'item 15 emploie une espace ordinaire avant un point d'interrogation la ou la
  typographie francaise et le paquet voisin `cp-nature-mot` emploient une insecable ; sans effet
  visible a la reference, mais cet item est precisement celui dont la bonne reponse est "cette
  phrase est correcte".

**Lien decision** : `DEC-0049`. **Agent pressenti** : `lea-dev` pour la correction, `ines-design`
si la mise en page des cinq propositions le demande, `nadia-prof` pour le reaudit.

**Mise a jour du 2026-08-27 (s20, cloture) - increment livre, reaudite, VALIDE SOUS RESERVE.**
`nadia-prof` a reaudite le paquet corrige par `lea-dev` (`round-two` a `77dd467`) et prononce
**VALIDE SOUS RESERVE** : les deux criteres correctifs ci-dessus sont traites - `REP-4` tenue
(simulation de l'enfant qui ignore le point d'interrogation, 16 sur 16 -> 13 sur 16, trois items
exigeant la distinction) ; le pli lisible sur le verdict, 14 items sur 16 apres un echec (0 sur 16
au refus), l'indice restant sous le pli sur 16 items sur 16 mais **non retenu contre ce paquet** -
`nadia-prof` verifie sa propre barre et la trouve non tenue par 8 jeux sur 17 du catalogue servi.
**Le Done pedagogique est acquis, sous reserve.** Le jeu revient au catalogue et la ligne de matrice
passe `couvert-valide` (15/43, 34,9 %) : detail complet, verification personnelle avant de faire
bouger la ligne, et confrontation chiffree avec l'obstacle nomme ci-dessus (95 items sur 296, dix
jeux) : `DEC-0050`.

**Collision de nommage, signalee et non corrigee.** Les criteres correctifs ci-dessus portent les
labels "`REP-7` (pli)" et "`REP-8` (le quatrieme repere)" - des labels que j'avais moi-meme inventes
le 2026-08-25 pour nommer ces deux exigences, avant que `nadia-prof` ne reutilise `REP-7` et `REP-8`
le 2026-08-27 pour deux reserves neuves et sans rapport (`REP-7` : la composition des propositions
predit la reponse sans lire la phrase ; `REP-8` : l'indice reste hors de vue apres un echec, defaut
de catalogue). **Ce sont quatre objets differents portant deux paires de labels identiques.** Je ne
renomme pas mes labels du 2026-08-25 : ils restent lisibles par leur date et leur objet dans ce
paragraphe, et les reecrire effacerait la trace de ce qui a motive la correction de `lea-dev`. Un
lecteur qui cite "`REP-7`" ou "`REP-8`" sur ce jeu doit preciser la date.

**Quatre reserves non bloquantes neuves du 2026-08-27, non affectees a un sprint** (voir la section
dediee ci-dessous, apres `S20-2`) : `REP-7`, `REP-8`, `REP-9`, `REP-10` (nomenclature de
`nadia-prof`, distincte des labels du paragraphe ci-dessus).

### Reserves de cloture s20 (`nadia-prof`, 2026-08-27) - non affectees a un sprint

Quatre reserves non bloquantes, ouvertes par `nadia-prof` a la reaudition de `cm1-reperes`
(`squad/conformite/FICHE-bandit-manchot-reperes-cm1.md`, verdict du 2026-08-27 ; `DEC-0050`). Elles
n'affectent pas le Done pedagogique deja rendu sur `cm1-reperes`. **Non affectees a s21** : le
planning de s21 n'est pas ecrit a la cloture de s20.

#### `REP-7` - Sur `cm1-reperes`, la composition des trois propositions predit la reponse sans lire la phrase

- **Besoin** : en tant qu'eleve de CM1 qui joue a "Majuscule ou point ?", je veux devoir lire la
  phrase pour repondre juste, afin que reussir le jeu demande de connaitre les reperes plutot que
  de reconnaitre la forme des trois boutons proposes.
- **Competence programme visee** : francais, CM1 (cycle 3), "Reperer et corriger la majuscule et la
  ponctuation dans une phrase donnee..." (meme ligne que `S20-2`, deja `couvert-valide`, cette
  story en corrige un residu).
- **Constat chiffre, dans les mots de `nadia-prof`, repris sans les affaiblir** : sur les seize
  items, quand un libelle de majuscule et un libelle de point figurent ensemble avec "Phrase
  correcte", la reponse est toujours "Phrase correcte" (4 items sur 16) ; quand les deux libelles
  non corrects sont de la meme famille (deux majuscules ou deux points), la reponse n'est jamais
  "Phrase correcte" (12 items sur 16) - sans exception, sur les treize compositions distinctes du
  paquet servi. Un enfant qui ne lit pas la phrase gagne 4 items a coup sur et joue les douze autres
  a une chance sur deux : esperance 10 items sur 16, contre 5,33 au hasard pur.
- **Ce que `nadia-prof` ne demande pas** : elle ne demande pas de retirer "Phrase correcte" d'un
  seul item - elle ecrit que `lea-dev` a raison de la garder sur les seize.
- **Criteres d'acceptation** :
  - Etant donne le paquet corrige, quand on enumere ses seize compositions de trois libelles,
    alors la signature "un libelle de majuscule + un libelle de point + Phrase correcte" ne designe
    plus systematiquement "Phrase correcte" comme bonne reponse sur les quatre items ou elle
    apparait aujourd'hui - au moins une partie de ces items porte une composition mixte (deux
    libelles de familles differentes, dont un faux) qui empeche la prediction par la seule forme.
  - Etant donne le paquet corrige, quand on relit les seize items, alors "Phrase correcte" reste
    presente sur les seize items comme bonne ou mauvaise reponse, sans reduction du nombre d'items
    ni des seize phrases (`nadia-prof` le confirme deja bon).
  - Ce qui n'est pas testable par un automate et releve de la seule porte pedagogique de
    `nadia-prof` : que la nouvelle composition des propositions reste plausible sans etre ambigue,
    et que l'esperance d'un enfant qui ne lit pas la phrase redescende reellement pres du hasard
    (5,33 sur 16) - `REP-7` est une propriete de comptage, pas gardee par un test de ce depot,
    meme famille que `REP-1` a `REP-4`.
- **Lien decision** : `DEC-0050`.
- **Agent pressenti** : a affecter au planning par `sofia-sm`, non affectee a s21 par ce document.
  `lea-dev` pour la correction (meme paquet que `S20-2`), `nadia-prof` pour le reaudit.

#### `REP-8` - L'indice reste hors de vue apres un echec sur huit jeux sur dix-sept du catalogue (defaut de catalogue, pas de paquet)

- **Besoin** : en tant qu'eleve qui vient de se tromper dans n'importe quel jeu du catalogue, je
  veux voir l'indice qui m'aide a comprendre mon erreur sans avoir a faire defiler la page, afin de
  recevoir l'aide au moment ou j'en ai besoin plutot que de rester bloque sur un ecran qui semble
  ne rien dire.
- **Competence programme visee** : transverse - pas de contenu pedagogique. `nadia-prof` le nomme
  explicitement : *"Ce n'est pas un defaut de `cm1-reperes` : c'est le premier defaut pedagogique du
  catalogue aujourd'hui"* ; la cause qu'elle mesure n'est pas dans un contenu, elle est dans le
  budget vertical du cabinet et la taille de `.proposition`.
- **Constat chiffre du 2026-08-27** (`nadia-prof`, balayage du catalogue servi a `77dd467`, 744 x
  551, un navigateur et une session neufs par jeu, une partie par jeu, une erreur volontaire par
  item, 17 jeux, 157 items vus - **un plancher, pas un inventaire**) : l'indice passe sous le pli
  sur au moins un item vu dans **8 jeux sur 17**, sur **43 items sur 157** vus, pire enfoncement
  92,19 px. Deux jeux l'enfoncent plus bas que `cm1-reperes` corrige
  (`bandit-manchot-perimetre-cm1` a 685,19 px, `bandit-manchot-grands-nombres-cm1` a 715,19 px) et
  un sur la totalite des items vus (`bandit-manchot-problemes-additifs-cp`, 10 sur 10). Le verdict,
  mesure separement, passe sous le pli sur **3 jeux sur 17**, **5 items sur 157**
  (`bandit-manchot-conjugaison-present-cm1`, `bandit-manchot-perimetre-cm1`,
  `bandit-manchot-grands-nombres-cm1`).
- **Ce que cette mesure n'est pas** : elle n'est ni identique ni independante de l'obstacle nomme au
  planning de s20 (*"95 items sur 296 (32 %), sur dix jeux, ont deja leur bloc de message sous le
  pli"*, `theo-archi`, 2026-08-25). Confrontation complete, perimetre par perimetre :
  `DEC-0050`, section 4. En resume : trois jeux figurent dans les deux mesures
  (`bandit-manchot-problemes-additifs-cp`, `bandit-manchot-perimetre-cm1`,
  `bandit-manchot-grands-nombres-cm1`) - meme famille de defaut, meme cause nommee des deux cotes -
  mais les deux chiffres ne se somment pas : perimetres de jeux differents (10 contre 17), 296 items
  exhaustifs contre 157 items en plancher echantillonne, etat le plus charge (trois echecs et
  revelation) contre un seul echec, un bloc combine (signal, verdict, explication, indice) contre
  deux visibilites mesurees separement (verdict, indice). **Je ne prononce aucun statut de
  couverture sur les items de l'une ou l'autre mesure.**
- **Criteres d'acceptation** :
  - Etant donne un item de n'importe quel jeu du catalogue, quand l'enfant se trompe une fois, alors
    l'indice qui suit est entierement visible a la reference du produit (744 x 551, `DEC-0040`)
    sans avoir a faire defiler la page. **Ce critere n'est pas atteignable par une seule valeur de
    marge ou de rembourrage sans arbitrage de forme** (`nadia-prof` le mesure elle-meme sur
    `cm1-reperes` : au plus 10 caracteres par libelle pour trois propositions sur une rangee ; une
    rangee au lieu de deux vaut 90 px, un enonce d'une ligne au lieu de deux en vaut 48) - la forme
    exacte (budget vertical du cabinet, taille de `.proposition`) revient a `ines-design` et
    `theo-archi`, je ne la prescris pas ici.
  - Ce qui n'est pas testable par un automate et releve de la seule porte pedagogique de
    `nadia-prof` : si un enfant de neuf ans comprend qu'il peut faire defiler la page pour aller
    chercher l'indice, et ce qu'un signal de defilement devrait porter pour le lui dire - elle nomme
    elle-meme ces jugements comme non mesurables par l'outillage du depot.
  - Ce qui reste a mesurer avant tout arbitrage de forme, nomme par `nadia-prof` et non tranche par
    cette story : toutes ses campagnes tournent avec un navigateur sans barre de defilement peinte
    (`--hide-scrollbars`) ; sur `cm1-reperes` le document fait 627 px pour un pli a 551 apres un
    echec, donc 76 px de defilement possible - l'indice est *hors de vue*, pas necessairement
    *inatteignable*, et l'etat avec une barre de defilement classique n'a jamais ete mesure.
- **Lien decision** : `DEC-0050`.
- **Agent pressenti** : `camille-po` (priorisation), `ines-design` et `theo-archi` sur la cause de
  forme - **jamais `lea-dev`** : `nadia-prof` le route explicitement comme une story de catalogue,
  pas une reprise de paquet.

**Mise a jour du 2026-08-27 (ouverture s21) - affectee en tete de sprint.** L'utilisatrice arbitre
le perimetre de s21 : le pli est ferme sur tout le catalogue avant tout ajout de jeu, motif retenu
"les jeux neufs naissent sans le defaut, aucune dette n'est ajoutee", au prix explicitement accepte
de moins de jeux livres a s21 (`DEC-0051`). Les criteres d'acceptation ci-dessus ne changent pas :
c'est leur priorite dans le sprint qui change, pas leur contenu. Reste vrai et non modifie par cette
mise a jour : les criteres restent a affiner en forme par `ines-design` et `theo-archi` avant tout
developpement (aucune valeur de marge ou de rembourrage seule ne les satisfait, la story le dit
elle-meme), et le perimetre exact (catalogue entier a 18 jeux, ou le seul sous-ensemble mesure par
`nadia-prof` a 17 jeux le 2026-08-27) reste a fixer au planning par `sofia-sm`.

#### `REP-9` - Sur `cm1-reperes`, le verdict est coupe en deux sur les deux items a enonce de deux lignes

- **Besoin** : en tant qu'eleve de CM1 qui vient de repondre a un item dont la phrase est longue, je
  veux lire le message qui me dit si j'ai reussi en entier, afin de ne pas devoir deviner la moitie
  d'un mot coupe par le bas de l'ecran.
- **Competence programme visee** : francais, CM1 (cycle 3), meme ligne que `REP-7` ci-dessus.
- **Constat chiffre** : sur les deux items dont l'enonce passe a deux lignes (*"Nous partons demain
  matin pour la montagne"*, *"Pourquoi les feuilles tombent-elles en automne"*), le texte du verdict
  est coupe en deux dans la hauteur des lettres (haut a 534,19 px, pli a 551 px).
- **Correction nommee par `nadia-prof`, qui corrige au meme geste une de ses propres phrases** :
  elle avait ecrit au refus *"ce que je ne demande pas : ni de toucher aux seize phrases"*, lue par
  `lea-dev` comme une interdiction. Elle precise le 2026-08-27 : *"je ne demandais pas de les
  toucher, je ne l'interdisais pas"*. Ramener ces deux enonces a une seule ligne fait remonter le
  verdict a 516,19 px sur les seize items.
- **Criteres d'acceptation** :
  - Etant donne les deux items a enonce de deux lignes du paquet corrige, quand on les relit,
    alors leur phrase tient sur une seule ligne a la reference du produit (744 x 551), sans changer
    le repere qu'ils testent ni leur bonne reponse.
  - Etant donne les seize items du paquet corrige, quand on lit le verdict apres un echec a la
    reference, alors il est entierement au-dessus du pli sur les seize (0 coupe, contre 2 sur 16
    avant cette story).
  - Ce qui n'est pas testable par un automate et releve de la seule porte pedagogique de
    `nadia-prof` : que les deux phrases reformulees restent du meme niveau CM1, avec le meme piege
    ou la meme absence de piege que l'original.
- **Lien decision** : `DEC-0050`.
- **Agent pressenti** : a affecter au planning par `sofia-sm`, non affectee a s21 par ce document.
  `lea-dev` pour la correction, `nadia-prof` pour le reaudit.

#### `REP-10` - Sur `cm1-reperes`, l'indice de l'item 15 nomme la reponse de trop pres

- **Besoin** : en tant qu'eleve de CM1 qui se trompe sur l'item "Ou habite ton cousin Paul ?", je
  veux un indice qui m'aide a reperer le signe attendu sans me donner directement la reponse, afin
  que reussir l'item demande de comprendre le repere plutot que d'apparier deux mots de l'indice.
- **Competence programme visee** : francais, CM1 (cycle 3), meme ligne que `REP-7` ci-dessus.
- **Constat, dans les mots de `nadia-prof`** : l'item 15, dont la bonne reponse est "Phrase
  correcte", porte l'indice *"Cette phrase pose une question et se termine bien."* - *"se termine
  bien"* nomme la reponse d'assez pres pour qu'un enfant apparie sans relire. Meme famille que
  `REP-5`, deja fermee par la correction du 2026-08-26 sur les items 8 et 9 (`M21`, un indice qui
  nomme la reponse est une revelation deguisee).
- **Criteres d'acceptation** :
  - Etant donne l'indice de l'item 15, quand on le relit, alors il n'emploie plus le mot "bien" ni
    aucun mot qui reprend le libelle de la bonne reponse ("Phrase correcte") - sur le modele deja
    retenu pour les items 8 et 9 (aider a reperer le signe, sans nommer le verdict).
  - Ce qui n'est pas testable par un automate et releve de la seule porte pedagogique de
    `nadia-prof` : qu'un enfant de CM1 ne puisse plus apparier l'indice reformule a la reponse sans
    relire la phrase.
- **Lien decision** : `DEC-0050`.
- **Agent pressenti** : a affecter au planning par `sofia-sm`, non affectee a s21 par ce document.
  `lea-dev` pour la correction, `nadia-prof` pour le reaudit.

### `S21-3` recadree (2026-08-27, en cours de sprint) - la reformulation d'enonces n'avait pas d'objet, deux reserves de forme la remplacent

`S21-3` affectait a `lea-dev` la reformulation de 12 enonces sur une seule ligne, pour fermer une
part du deficit de pli sur trois paquets. `lea-dev` a verifie avant d'editer et n'a touche aucun
fichier de contenu ; `nadia-prof` a rejoue les paquets concernes et **s'est donnee tort contre sa
propre mesure du matin** (`round-two` a `3cfe9e3`, 2026-08-27, message de commit) : elle avait
inverse la population des 12 items vises et des 2 items exclus. Les 12 items ont deja un enonce
d'une ligne (48 px) ; les 2 items exclus sont ceux a deux lignes (96 px), et meme les ramener a une
ligne laisse **44,19 px** de deficit sur ces deux-la. **La reformulation d'enonce ne ferme donc rien
de ce que `S21-3` visait.**

En jouant quatre paquets entiers, item par item (`cm1-reperes`, `cm1-accords-groupe-nominal`,
`cm1-mesures`, `cm1-grands-nombres`, outil neuf `squad/outils/nadia-prof/auditer-un-paquet-
entier.py`, memes commit et date), `nadia-prof` ouvre deux reserves neuves qui portent, elles, sur
un levier reel. **`S21-3` change d'objet dans ce sprint pour les porter** : meme paquet
(`cm1-reperes`) que sa premisse d'origine pour l'une, meme famille de defaut (contenu, pas de
mecanique neuve) pour les deux. `lea-dev` reste l'agent pressenti, a la suite de `S21-10` qu'elle
ecrit au moment ou ce document est redige - aucune nouvelle capacite n'est demandee au sprint, la
meme affectation est redirigee vers un travail qui ferme reellement quelque chose.

#### `REP-11` a `REP-14` - Sur `cm1-reperes`, les libelles de proposition depassent le budget de largeur d'une rangee

- **Besoin** : en tant qu'eleve de CM1 qui joue a "Majuscule ou point ?", je veux que les quatre
  boutons de reponse tiennent sur une seule rangee a l'ecran, afin de lire l'indice apres une
  erreur sans avoir a faire defiler la page.
- **Competence programme visee** : francais, CM1 (cycle 3), meme ligne que `REP-7` a `REP-10`
  ci-dessus (`squad/programme/francais/competences-cm1.md`).
- **Constat chiffre, dans les mots de `nadia-prof`** (`round-two` a `3cfe9e3`, 2026-08-27, message
  de commit) : les trois libelles de proposition les plus larges totalisent au plus **522 px** pour
  tenir sur une rangee (barre deja etablie par elle pour le volume de s21,
  `squad/conformite/ARBITRAGE-PREALABLE-s21-trois-paquets-de-volume-cp.md`) ; ils totalisent
  **818,45 px** aujourd'hui sur `cm1-reperes`, et `Majuscule au prénom` a lui seul en mange 62 %.
- **Ce que `nadia-prof` autorise, dans ses mots, cites sans les affaiblir** : *"cinq libelles, cinq
  et distinguables ; du francais, pas un rebus ; la distinction debut de phrase / nom propre
  survit dans les libelles"*. Elle a deja mesure que deux jeux de libelles candidats tiennent sur
  une rangee a l'ecran. **Elle ne dicte aucun libelle** - elle ecrit avoir "deja mal choisi" les
  siens une fois (le refus du 2026-08-26 sur `REP-4`) : l'auteur porte le risque de proposition,
  elle porte le jugement.
- **Criteres d'acceptation** :
  - Etant donne les cinq libelles de proposition du paquet corrige (les quatre reperes existants
    plus le libelle neuf de `REP-4`), quand on additionne la largeur rendue des trois plus larges a
    la reference du produit (744 x 551), alors la somme est au plus **522 px**.
  - Etant donne les cinq libelles, quand on les relit, alors ils restent cinq mots ou groupes de
    mots du francais courant, distinguables entre eux sans ambiguite, et la distinction entre
    majuscule de debut de phrase et majuscule de nom propre reste lisible dans leur formulation -
    aucun libelle n'est un rebus ni une abreviation.
  - Etant donne le paquet corrige, quand on relit ses seize items, alors le repere teste et la
    bonne reponse de chacun restent inchanges (`REP-1` a `REP-4` deja tenues, non rouvertes par
    cette story).
  - Ce qui n'est pas testable par un automate et releve de la seule porte pedagogique de
    `nadia-prof` : que les cinq libelles retenus soient effectivement distinguables pour un enfant
    de CM1 et ne recreent pas la meme confusion que celle deja corrigee sur `REP-4`.
- **Lien decision** : `DEC-0050` (paquet), commit `3cfe9e3` (constat et budget).
- **Agent pressenti** : `lea-dev`, a la suite de `S21-10`. `nadia-prof` pour le reaudit, sur les
  libelles proposes uniquement.

#### `MES-1` - Sur `cm1-mesures`, un distracteur numeriquement implausible degrade a la fois la pedagogie et le pli

- **Besoin** : en tant qu'eleve de CM1 qui joue a "Les mesures", je veux comparer des reponses dont
  l'ecart correspond a une confusion que je pourrais vraiment faire (facteur 10, 100 ou 1000 entre
  unites), afin qu'une mauvaise reponse ecartee le soit parce qu'elle est fausse, pas parce qu'elle
  est absurde.
- **Competence programme visee** : mathematiques, CM1 (cycle 3), "Convertir des unites de
  longueur, masse, contenance dans le systeme metrique" - meme ligne que la fiche de conformite du
  paquet (`squad/conformite/FICHE-bandit-manchot-conversions-unites-cm1.md`), dont un critere deja
  pose par `camille-po` a l'ouverture exige des "distracteurs numeriques plausibles (confusion de
  facteur 10/100/1000), jamais un nombre arbitraire" - critere que ce constat rouvre sur un item
  precis, il ne l'invente pas.
- **Constat chiffre, dans les mots de `nadia-prof`** (`round-two` a `3cfe9e3`, 2026-08-27, message
  de commit) : il manque **12,95 px** pour que l'indice de l'item concerne repasse au-dessus du
  pli, et le distracteur en cause, `4000000 tonnes`, differe de la bonne reponse par un facteur
  **un million** - hors de la plage 10/100/1000 que le critere de la fiche autorise, pour un motif
  independant du pli.
- **Ce que `nadia-prof` observe** : remplacer ce seul distracteur ferme les deux defauts du meme
  geste (le motif pedagogique et le deficit de pli).
- **Criteres d'acceptation** :
  - Etant donne l'item de `cm1-mesures` qui porte le distracteur `4000000 tonnes`, quand on le
    remplace, alors le distracteur neuf differe de la bonne reponse par confusion de facteur 10,
    100 ou 1000, comme l'exige deja le critere pose sur ce paquet.
  - Etant donne cet item corrige, quand on lit l'indice apres un echec a la reference (744 x 551),
    alors il est entierement au-dessus du pli (deficit ramene de 12,95 px a 0).
  - Etant donne le paquet corrige, quand on le relit, alors le repere teste, la bonne reponse et les
    autres distracteurs de cet item restent inchanges.
  - Ce qui n'est pas testable par un automate et releve de la seule porte pedagogique de
    `nadia-prof` : que le distracteur neuf reste une confusion plausible pour un eleve de CM1.
- **Lien decision** : `DEC-0050` (famille de defaut), commit `3cfe9e3` (constat).
- **Agent pressenti** : `lea-dev`, meme lot que `REP-11` a `REP-14`. `nadia-prof` pour le reaudit.

#### Ce qui ne devient pas une story : `GRN-1`/`GRN-2` confirment un obstacle deja route, `ACC-1` en ouvre un neuf hors de mon perimetre

- **`GRN-1`/`GRN-2` (`cm1-grands-nombres`, dans les mots de `nadia-prof`, commit `3cfe9e3`)** :
  *"`GRN-1` refuse le retrait du prefixe 'Ecris en chiffres', seul marqueur de tache d'un paquet
  dont la consigne en annonce quatre"* ; *"`GRN-2` : sur l'enonce le plus long il reste 31,75 px de
  budget, moins de deux caracteres - cet item n'a aucun levier d'enonce"*. Ce constat **confirme et
  chiffre**, sans le remplacer, l'obstacle deja nomme au planning de s21 par `camille-po` ("4 items
  sans levier connu, deux de `bandit-manchot-grands-nombres-cm1`, deux de
  `bandit-manchot-reperes-cm1`", `squad/sprints/s21-fermer-le-pli-avant-le-volume/planning.md`,
  tableau des obstacles) - jusqu'ici trace uniquement dans ce document de sprint, jamais dans le
  backlog : je l'y ecris maintenant, pour que l'absence de levier ne se perde pas au prochain
  sprint. **Il n'y a rien a affecter a `lea-dev` ici** : aucune reformulation ni aucun remplacement
  de distracteur ne ferme ces deux items, `nadia-prof` le dit elle-meme. Reste `en cours` sur la
  matrice, hors perimetre de s21, aucun statut de couverture supplementaire prononce par ce
  document.
- **`ACC-1` (`cm1-accords-groupe-nominal`, dans les mots de `nadia-prof`, commit `3cfe9e3`)** :
  *"je n'autorise AUCUN retrait de proposition. Les 4 formes d'accord sont l'item. Les 2 rangees
  viennent d'une declaration de feuille, pas de la largeur : 7 des 8 tiendraient sous les 464 px
  d'une rangee de quatre."* Ce n'est pas un defaut de contenu : la cause nommee est une regle de
  style, pas une largeur de libelle, et `nadia-prof` la route explicitement a `ines-design` et
  `theo-archi`, sans la trancher elle-meme. Je ne l'affecte pas a `lea-dev` ni a une story de
  contenu pour ce motif. **Agent pressenti** : a affecter au planning par `sofia-sm`, vers
  `ines-design` (diagnostic de la declaration de feuille en cause) puis `theo-archi` si la cause est
  confirmee technique ; non affectee a s21 par ce document faute de capacite nommee sur ces deux
  agents ce sprint.

### Obstacle transverse route depuis `S21-8` - `explication` n'est atteignable que par un detour propre au francais

`nadia-prof` route depuis `S21-8` (arbitrage prealable du volume CP) un constat qui n'est ni un
motif de refus ni une story de s21 : `cp-syllabes`, que `marc-dev` ecrit en ce moment, n'aura aucun
texte d'explication apres une reponse, quelle qu'elle soit.

**Ce que le depot dit deja, verifie ce jour.** `ADR-0024` fait porter `explication` par le verdict
d'un item, mais seulement quand le paquet declare une `graphie` qui differe de la bonne reponse -
mecanisme concu pour "Le bon son" (une graphie ecrite differente du son entendu, par exemple `en`
pour le son `an`). `ChaquePaquetExpliqueSesItemsAVariationTest`
(`adapters/adapter-content/src/test/java/com/recreo/adapter/content/
ChaquePaquetExpliqueSesItemsAVariationTest.java`) le garde de deux facons : un paquet qui declare
`graphie` sur un seul item doit la declarer sur tous, et un item ne peut porter `explication` que
si sa `graphie` differe de sa `bonneReponse`. **`nadia-prof` a deja verifie le detour et l'a
refuse, dans ses mots** (`ARBITRAGE-PREALABLE-s21-trois-paquets-de-volume-cp.md`, section 3, ligne
548) : *"pour `cp-syllabes`, l'enseignement est la segmentation, et elle a sa place sur l'ecran de
retour, pas dans un indice qui donnerait le compte"* ; *"declarer `graphie: 'cho-co-lat'` sur un
paquet de syllabes ferait passer le garde-fou en detournant le sens d'un champ de phonologie : je
ne l'autorise pas."* Le champ `explication` existe sur `Question` sans nom de matiere (`ADR-0024`,
geste 1), mais le seul chemin qui l'atteint aujourd'hui est un declencheur propre a la phonetique
du francais - et le detourner pour l'atteindre autrement est deja instruit et refuse.

**Ce que j'en dis, comme jugement produit et non technique.** Le principe directeur du produit
tient une mecanique decouplee d'un contenu pilote par des regles ; ici, un canal de retour
pedagogique generique (le texte affiche apres une reponse) n'est en pratique accessible qu'a une
seule famille de contenu. C'est une forme plus etroite que la dette 2 de la version precedente
(une configuration entiere taillee pour une matiere) mais de la meme famille : un mecanisme neuf,
ouvert pour un seul besoin constate, se revele fermer les besoins suivants plutot que de rester
neutre. **Cela ne bloque pas `cp-syllabes` a s21** : l'indice reste disponible apres trois echecs,
generique et independant de `graphie` (`S3-2`, `S3-3`), donc l'enfant n'est pas prive de toute aide
- seulement de l'explication affichee apres chaque reponse, que seuls les paquets a variation
phonique portent aujourd'hui. Je ne tranche pas comment l'ouvrir (elargir le perimetre du
garde-fou d'`ADR-0024`, ou concevoir un second canal declare par le paquet sans passer par
`graphie`) : c'est un choix d'architecture, pas un choix de contenu.
- **Competence programme visee** : transverse - pas de contenu pedagogique.
- **Agent pressenti** : a affecter au planning par `sofia-sm`, vers `theo-archi` pour instruire les
  options ; non affectee a s21 (ni motif de refus, ni story, selon `nadia-prof` elle-meme,
  `ARBITRAGE-PREALABLE-s21-trois-paquets-de-volume-cp.md`).

## Backlog - perimetre volume s21, contenu-repris classe par gain de couverture (intentions non detaillees, `DEC-0051`)

Arbitrage de l'utilisatrice du 2026-08-27 (`DEC-0051`) : le pli se ferme sur tout le catalogue
(`REP-8`, affectee en tete de s21 ci-dessus) avant tout ajout de jeu ; la matiere des jeux neufs
vient de `contenu-repris/` avant redaction neuve. Ce qui suit sont des intentions, pas des stories
pretes a developper - meme regle que la section "Backlog - sprints suivants" plus haut. Le
classement complet, avec la methode de verification et les fichiers ecartes, vit dans `DEC-0051`
(sections 3 et 4) ; ce qui suit en est le resume utile au planning.

**Niveau 1 (transposition directe, competence non couverte identifiee)** : `contenu-repris/
syllabes/cp-syllabes.yml` (francais CP, "Denombrer les syllabes orales d'un mot" - premiere ligne
du domaine "Langage oral et conscience phonologique", aujourd'hui a zero ligne couverte) ;
`contenu-repris/quiz/cp-comparer.yml` (mathematiques CP, "Comparer des longueurs, des masses, des
contenances (sans instrument gradue)" - a trier et completer sur la contenance avant audit) ;
`contenu-repris/textes-trous/cp-mot-manquant.yml` (francais CP, "Utiliser des mots precis...") ;
`contenu-repris/quiz/cm1-comprehension.yml` (francais CM1, "Comprendre un texte narratif ou
documentaire...", sous condition : mesure prealable de la longueur d'enonce contre le budget
vertical au coeur de `REP-8`, routee a `theo-archi` et `ines-design` avant tout audit).

**Niveau 2 (transposable, mais arbitrage prealable de `nadia-prof` a poser avant tout
developpement)** : `contenu-repris/mots/cp-premiere-lettre.yml` (candidat sur "Identifier un son
(phoneme) dans un mot", correspondance lettre initiale / phoneme non tranchee) ;
`contenu-repris/fractions/cm1-compare.yml` (candidat sur "Notion de fraction simple... et reperage
sur une droite numerique", risque de couverture partielle deja sanctionne sur ce produit - le
reperage sur une droite numerique n'y figure pas) ; `contenu-repris/associer/cp-chiffre-mot.yml`
(deja arbitre sous `DEN-1` a `DEN-8`, ne tient aucune des huit conditions en l'etat, redaction quasi
entiere a refaire, et resterait `en cours` meme parfaitement tenu par `DEN-2` - priorite basse).

**Niveau 3, ecarte** : les familles `puzzle/`, `memory/`, `phrase/` et la majeure partie
d'`associer/` exigent une mecanique de reordonnancement, d'appariement par memoire ou de remise en
ordre que ce produit n'a pas ; je ne propose aucune mecanique neuve.

**Niveau 4, hors perimetre** : les 24 fichiers de `quiz/` sur des matieres sans referentiel
(histoire, geographie, sciences, EMC) et les fichiers d'anglais n'ont aucune ligne de
`squad/programme/` a laquelle s'accrocher aujourd'hui - ouvrir un referentiel est une decision
d'une autre nature que le choix d'un paquet de s21.

**Goulot de planning, chiffre sur deux sprints** : s20 a audite deux paquets (`RAPPORT.md` de s20,
consommation de tokens de `nadia-prof`), dont un seul sans cycle de refus ; s19 en a audite quatre,
dont un avec cycle de refus complet dans le sprint et un valide sous reserve bloquante non fermee
avant s20. Je tiens **deux a trois paquets neufs** comme le nombre auditable a s21 sans compter un
aleas de refus supplementaire - detail et raisonnement complet dans `DEC-0051`, section 5.

**Agent pressenti** : a affecter au planning par `sofia-sm`, qui choisit le nombre de paquets
retenus parmi les niveaux 1 et 2 ci-dessus, dans la borne donnee. `marc-dev`/`lea-dev` pour la
reprise de matiere (sous le schema `bandit-manchot` en usage, jamais sous la structure d'origine de
`contenu-repris/`), `nadia-prof` pour les arbitrages prealables du niveau 2 et les audits,
`ines-design`/`theo-archi` pour la condition de forme sur `cm1-comprehension`.
- **Lien decision** : `DEC-0051`.
