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
