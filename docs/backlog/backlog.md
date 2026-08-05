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

## Backlog - sprints suivants (intentions non detaillees)

Ces intentions ne sont pas des stories pretes a developper. Elles seront detaillees sprint par
sprint, en priorite sur les trous les plus visibles de `squad/programme/MATRICE-couverture.md` -
en particulier le fait qu'a l'issue du sprint 0, le francais n'a encore aucune ligne `en cours`,
et que `DEC-0007` reporte volontairement l'ouverture de cette matiere apres s03 (voir ce sprint).

- Une fois la ligne mathematiques CP effectivement `couvert-valide` (ou, si `nadia-prof` refuse
  encore l'increment corrige de s03, une fois son nouveau motif traite) : elargir le contenu
  "additions CP" a la borne jusqu'a 20, et couvrir les soustractions et les complements a 10 (meme
  mecanique, meme niveau) pour clore completement cette ligne de la matrice.
- Ouvrir la premiere competence de francais couverte par une mecanique (a choisir en fonction de
  ce que le bandit manchot sait bien servir - probablement une competence a reponse fermee, par
  exemple les homophones grammaticaux CM1 - plutot qu'une competence orale comme la segmentation
  syllabique, qui demandera probablement une autre mecanique ou un autre mode d'interaction).
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
  actuellement a 0 increment en attente apres correction a l'ouverture de s03) et declencher le
  gel du contenu si elle depasse 2-3 increments non valides en attente.
