# [Sprint s15] Retrospective - 2026-08-18

- **Anime par** : sofia-sm

## Ce qui a marche

- **Chaque arbitrage de l'utilisatrice a ete instruit, code et rejoue en moins d'une journee.**
  Cinq arbitrages successifs (retour de "Les jeux", apparence commune des puces, sortie visible
  pendant une manche, rang unique, couleur d'etat actif), et pour trois d'entre eux un refus ou une
  reserve de `nadia-prof` corrige le jour meme - la boucle "l'utilisatrice joue, dit ce qui ne va
  pas, l'equipe corrige, l'utilisatrice rejoue" a tenu sans ralentir.
- **Deux defauts trouves par l'utilisatrice en jouant, invisibles a tout l'outillage du depot, ont
  ete fermes avec leur cause identifiee et non seulement leur symptome corrige.** Le survol qui
  effacait tous les boutons du depot est ferme par `ADR-0046`, qui renverse au passage une garantie
  fausse d'`ADR-0014` vieille de douze sprints (l'hypothese de meme specificite CSS). La regression
  de couleur apres retour au catalogue est fermee en identifiant que le filtre de neutralisation ne
  couvrait pas les routes de retour, pas en ajoutant une exception ponctuelle.
- **`theo-archi` a tenu son mecanisme de masquage a zero fichier de garde-fou touche malgre cinq
  changements de population depuis s14** (retrait force, reforme d'assertion, verification par
  mutation, note de renversement, retournement de l'epinglage). C'est la preuve de morsure que
  l'amendement du troisieme amendement d'`ADR-0022` promettait : "si l'humain change d'avis,
  combien de fichiers de garde-fou bougent ? zero."
- **`nadia-prof` a arbitre elle-meme une question d'architecture qui lui revenait legitimement**
  (la forme de `.proposition.deja-essayee` sous le doigt), en articulant sa decision a une premisse
  globale qu'elle avait deja accordee le meme jour, plutot que de la traiter isolement.

## Ce qui a coince

### 1. Le sprint n'a suivi aucune de ses propres regles d'ordre, et le cout s'est vu a la cloture, pas en cours de route

Cinq arbitrages ont pilote le sprint sans objectif gele ni avis de faisabilite avant le premier
commit. Le cout n'est apparu qu'en regularisant `planning.md` a la fin : trois arbitrages sur cinq
sans aucune trace `DEC` pendant toute leur duree de vie en production, un lot pedagogique fusionne
nulle part pendant plusieurs heures de developpement ulterieur, aucune story de backlog pour trois
arbitrages deja vecus par le code. Rien de tout cela n'a produit de defaut technique - le code
livre est juste - mais la gouvernance par le depot (le principe que "ce qui n'est pas ecrit
n'existe pas") s'est degradee pendant le sprint sans que personne ne le mesure avant la cloture.
Voir l'amendement ecrit ci-dessous.

### 2. Aucun rapport qualite, aucune confirmation ArchUnit motivee, aucune relecture pour l'ensemble du sprint

`yanis-qa`, `theo-archi` (au sens de la ceremonie de confirmation, distincte de son travail
d'architecture) et le `relecteur-pedagogique` n'ont ecrit aucun fichier de cloture pour s15. Ce
n'est pas un defaut de leur travail - aucun n'a ete sollicite dans un sprint pilote par arbitrages
successifs plutot que par des lots instruits - mais c'est un trou reel dans le Done technique tel
que le process le definit : ma propre reverification de `mvn -B clean verify` atteste le compte de
tests et de regles, elle ne remplace ni une mutation de garde-fou recente, ni une confirmation
ArchUnit motivee clause par clause, ni une relecture des livrables textuels du sprint (`DEC-0035`,
les fiches de `theo-archi`, `planning.md` lui-meme). Route dans `review.md`, non ferme ici.

### 3. La dependance a une seule personne pour toute mesure de rendu s'est aggravee sans qu'un nouvel
amendement ne soit necessaire

`nadia-prof` a nomme cette dependance quatre fois en deux jours, dont trois avec sa cause. Je ne
l'ecris **pas** comme un amendement neuf a `squad/README.md` : c'est un obstacle deja route et
ouvert depuis s14 ("Resolution de navigateur d'`outils/mesurer-rendu.sh`, facteur de bus sur la
mesure", `sofia-sm`, capacite), et le mecanisme de routage fonctionne deja pour lui - il manque une
capacite, pas une regle de process. L'ecrire une seconde fois generaliserait un incident deja
generalise plutot que d'ajouter une categorie neuve. Il reste dans le tableau des obstacles de
`review.md` et de `RAPPORT.md`, inchange dans son routage.

## Amendements a `squad/README.md`

**Deux amendements ecrits, un candidat ecarte.**

**Ecrit (section 9, etape 1 - Planning)** : un sprint peut etre pilote par des arbitrages
successifs de l'autorite produit plutot que par un objectif gele, et le process le nomme desormais
en clair plutot que de le taire. Deux exigences minimales sont posees pour que ce mode ne degrade
pas la gouvernance par le depot : chaque arbitrage recoit sa trace `DEC` dans le meme geste que la
fusion du lot qui l'implemente, jamais reportee a la cloture ; `planning.md` nomme le mode en clair
plutot que de fabriquer un objectif gele retroactif. Motif : ce n'est pas un incident isole - c'est
la totalite de s15, cinq arbitrages sur cinq, et le process n'avait litteralement rien qui decrive
ce cas avant cette retro.

**Ecrit (section 4 - Les ceremonies)** : une propriete visible seulement par un humain qui joue
n'est pas gardable par l'outillage du depot, et un verdict qui s'appuie sur elle le dit
explicitement, dans le meme paragraphe. Motif : `ADR-0025` avait deja acte une premiere instance
(le debordement de mise en page) ; s15 en ajoute deux de plus, nommees par `nadia-prof` elle-meme
avec leur cause (l'etat de survol reel, structurellement hors de portee du seul outil de mesure du
depot ; un jugement de comprehension d'enfant, qui est un raisonnement et non une mesure). Trois
instances nommees a des sprints differents generalisent une categorie, pas un incident.

**Ecarte** : un troisieme amendement sur la dependance a une seule personne pour toute mesure de
rendu. Motif du rejet, sur le meme critere que celui applique en s14 (generaliser une categorie,
pas un incident) : cette dependance est deja un obstacle route et ouvert depuis s14, avec un
destinataire (`sofia-sm`, capacite) et un statut ("non affecte") qui n'ont pas change ce sprint. Ce
qui manque n'est pas une regle de process neuve mais une capacite non fournie ; ecrire un
amendement ici documenterait un manque de moyens comme s'il s'agissait d'un trou de ceremonie, ce
qu'il n'est pas.

## Decisions prises

1. **Deux amendements sont ecrits dans `squad/README.md`** : la description du mode "pilote par
   arbitrages successifs" (section 9, etape 1) et la non-gardabilite des proprietes visibles
   seulement par un humain qui joue (section 4). Voir le detail et le motif ci-dessus.
2. **Le troisieme candidat (dependance a une seule personne pour la mesure de rendu) n'est pas
   ecrit** : il generalise un incident deja generalise depuis s14, pas une categorie neuve.
3. **Aucun rapport qualite, aucune confirmation ArchUnit motivee, aucune relecture ne sont
   fabriques a la place de leurs auteurs absents.** L'ecart reste route dans `review.md` et
   `RAPPORT.md`, pas absorbe ici.
4. **Aucun worktree n'est supprime dans ce geste** : l'utilisatrice n'a pas demande de nettoyage,
   et les nombreux worktrees de s15 restent enregistres.
5. **`MATRICE-couverture.md` n'est pas touche** : aucune ligne de programme n'a ete ouverte ni
   fermee, choix de perimetre du sprint et non un manque.
