# Avis de priorite pedagogique - laquelle des trois familles de mecanique manque le plus a un enfant - nadia-prof

> **Ce fichier est un avis, pas une decision et pas un verdict.** `DEC-0030` (section 5, sixieme
> demande) me demande mon avis "en tant que professeure des ecoles et non en tant que juge d'un
> increment". `camille-po` reste seule a decider quelle mecanique est due et quand ; `theo-archi`
> rend le cout en parallele ; **l'utilisatrice arbitre**. Rien ici n'ouvre ni ne ferme un Done.

- **Date** : 2026-08-15. **Auteur** : nadia-prof. **Sprint** : s13.
- **Mode** : je n'ai rien joue - il n'y a rien a jouer, aucune de ces trois mecaniques n'existe. J'ai
  lu les 35 lignes `non couvert` de `squad/programme/MATRICE-couverture.md`, les deux referentiels
  de CP et les deux de CM1, et le decoupage en quatre besoins de `DEC-0030` section 4.
- **Increment de reference** : branche `s13-nadia-arbitrages`, ouverte sur `round-two` a `9430cfd`,
  464 tests, 28 regles - inchanges de mon fait, je n'ecris que de la documentation.

## Ce que je corrige avant de m'appuyer dessus

Le compte de `camille-po` est **juste et je le reprends** : contenu seul 19 lignes entieres plus une
partielle, visuel 6 plus une partielle, saisie 5, audio 3 plus une ligne a part. J'ai reverifie les
trois lignes d'audio contre le referentiel de francais CP, mot pour mot : *"Denombrer les syllabes
orales d'un mot"*, *"Localiser une syllabe dans un mot"*, *"Identifier un son (phoneme) dans un
mot"*. Ce sont bien les trois lignes du domaine **"Langage oral et conscience phonologique"**, et ce
domaine n'en compte pas d'autre : **il est a zero sur trois, et l'audio le ferme entierement.**

Une correction que je fais a ma propre pratique de comptage, et elle porte : j'ai moi-meme ecrit le
2026-08-06, sur un cas voisin, que *"sans son, un digraphe transparent ne peut donner lieu qu'a une
recherche de lettres"*. Je n'en avais pas tire la consequence de couverture. `camille-po` l'a tiree
avant moi et elle a raison.

## Mon ordre : audio, puis visuel, puis saisie

Je reponds a la question posee - **laquelle manque le plus a un enfant de CP ou de CM1** - et non a
la question du meilleur rendement de couverture, qui n'est pas la mienne.

### 1. Audio - le plus petit en lignes, et le premier

**Le motif tient en une phrase : ces trois lignes ne sont pas une matiere parmi d'autres, ce sont les
fondations de la lecture, et elles se travaillent a l'oreille avant l'oeil.** Un enfant de CP qui
n'entend pas que "cha-peau" a deux syllabes, qui ne sait pas dire si le son [a] est au debut ou a la
fin de "chat", n'apprendra pas a lire par la voie du dechiffrage - il apprendra a reconnaitre des
mots par leur silhouette, et il decrochera au CE1 sur les mots qu'il n'a jamais vus. C'est la
competence la plus predictive de la reussite en lecture, et c'est celle qu'un maitre de CP travaille
**tous les jours, oralement, en collectif**, avant et pendant l'apprentissage du code.

**Ces trois lignes sont les seules des 35 qui ne peuvent recevoir aucun contenu, pas meme un
morceau.** C'est ce qui les distingue de toutes les autres. Une syllabe n'existe qu'a l'oral : un
enfant a qui l'on montre `chapeau` ecrit et a qui l'on demande de compter les syllabes ne compte pas
des syllabes, il compte des groupes de lettres - et il tombe juste par accident tant que le mot est
regulier. Un phoneme, c'est pire : `eau`, `au`, `o` sont un seul son et trois graphies, et toute la
difficulte de la conscience phonologique est justement de **detacher le son de la lettre**. Un
exercice ecrit ne peut pas seulement l'affaiblir, **il enseigne le contraire de ce qu'il annonce**.
Sur les lignes visuelles et de saisie, l'attente coute une case vide. Ici, elle coute une case qu'on
ne peut pas remplir de travers, ce qui est mieux, mais qui restera vide indefiniment.

**Le fait qu'on me demande de peser - c'est un domaine de CP - change mon ordre, et je le dis
franchement : c'est la raison principale pour laquelle je place l'audio devant.** Le CP est l'annee
ou l'enfant apprend a lire ; ce qui se joue cette annee-la ne se rattrape pas au meme cout apres. Un
CM1 prive des lignes visuelles - la symetrie, les figures a tracer, la droite numerique - garde vingt
autres lignes ouvertes et rencontrera ces notions en classe avec une regle et une equerre, qui sont
les bons outils pour elles de toute facon. Un CP prive de conscience phonologique n'a **rien** qui la
remplace dans ce catalogue, et son annee ne recommence pas.

**Un benefice supplementaire que le compte par lignes ne voit pas, et je le donne comme une piste, pas
comme un argument decisif** : la capacite d'emettre un son sert aussi a **dire l'enonce a haute voix**.
Ma remarque la plus repetee sur ce produit depuis le sprint 0 est qu'un enfant de CP lit peu et
lentement, et que chaque mot de consigne se paie. Une application qui peut parler peut rendre lisible
tout le catalogue CP, pas seulement trois lignes. **Je ne sais pas si les deux capacites sont la meme
chose techniquement** - jouer un son enregistre et synthetiser une phrase ne sont pas le meme travail
- et c'est a `theo-archi` de le dire, pas a moi. S'il repond que c'est le meme socle, cet argument
compte double ; s'il repond que non, mon ordre ne change pas pour autant.

**Ce que je sors explicitement du compte** : *"Lire a voix haute un texte court avec fluidite"*. Elle
exige que l'application **recoive** la voix de l'enfant, pas seulement qu'elle en emette une. Je suis
`camille-po` sur ce point sans reserve, et j'ajoute un avis de fond qu'on ne m'a pas demande mais qui
evitera une deception : **la fluidite de lecture s'evalue par un adulte qui ecoute**, avec un
jugement sur le ton, les liaisons, le respect de la ponctuation, qu'aucune reconnaissance vocale ne
rend. Cette ligne-la, je ne la reclamerai pas.

### 2. Visuel - le plus large, et le second

**Six lignes entieres et une partielle : c'est le plus gros manque, et je le mets second sans
hesitation.** Trois raisons, dans l'ordre de leur poids.

- **Ces notions se rencontrent ailleurs, et mieux.** L'heure s'apprend sur une horloge murale, la
  monnaie avec de la vraie monnaie, une figure plane se trace avec une regle. Un ecran est un
  substitut acceptable pour s'entrainer, il n'est pas le lieu premier de l'apprentissage. Ce n'est
  pas vrai de la conscience phonologique, qui n'a pas d'objet materiel.
- **Une partie de ces lignes est deja servie a moitie sans mecanique neuve.** Le pictogramme-caractere
  ouvert par `DEC-0019` porte deja du denombrement et pourrait porter de la reconnaissance de figures
  - deux des quatre questions de validite de proxy que `camille-po` me pose portent exactement
  la-dessus. L'audio n'a pas d'equivalent : il n'y a pas de demi-son.
- **Une mecanique visuelle mal faite enseigne faux, et c'est celle que je voudrais voir construite
  avec le plus de soin, donc pas dans la precipitation.** Une horloge dont les aiguilles sont a peu
  pres placees, un quadrillage dont les cases ne sont pas carrees, une droite graduee dont les
  intervalles ne sont pas egaux : sur chacun de ces objets, l'a-peu-pres est une erreur de contenu,
  pas un defaut de forme. J'ai deja refuse l'encadrement comme proxy de la droite numerique en
  ecrivant que *"cette competence exige une ligne reellement dessinee et graduee"* - je maintiens, et
  j'ajoute qu'une ligne mal graduee serait pire que pas de ligne du tout.

### 3. Saisie - cinq lignes, et le dernier

**Ce n'est pas parce qu'il y a peu de lignes, c'est parce que le gain pedagogique par ligne y est le
plus faible et le risque d'enseigner mal le plus eleve.**

- **Les deux lignes d'operation posee** - la multiplication a plusieurs chiffres, la division
  euclidienne - se font **sur du papier**, en colonnes, avec les retenues ecrites. C'est le geste
  lui-meme qui est la competence : aligner, retenir, poser. Une saisie a l'ecran, quelle qu'en soit
  la forme, ne reproduira jamais ce geste, et un CM1 a plus a gagner d'un cahier que d'un ecran sur
  ces deux lignes-la. Une mecanique de saisie les ferait passer en `couvert-valide` sans qu'elles
  soient reellement mieux servies - c'est exactement ce contre quoi j'ecris depuis le debut :
  **je prefere une case vide a une case fausse.**
- **La saisie de texte libre au CP se heurte a l'enfant lui-meme.** Il ecrit lentement, il cherche ses
  lettres sur un clavier qu'il ne connait pas, et il fait des fautes d'orthographe sur des mots qu'il
  sait par ailleurs. Un jeu qui compte faux une reponse juste mal orthographiee est un jeu qui
  decourage, et un jeu qui accepte tout n'evalue rien. Entre les deux, il faut un jugement sur la
  faute que personne ici ne sait automatiser - et moi non plus.
- **La ligne *"Relire et corriger son texte"* n'appartient sans doute meme pas a cette famille** :
  corriger **son** texte suppose qu'il y ait un texte de l'enfant. C'est la quatrieme question de
  validite de proxy de `camille-po`, et j'y repondrai a part.

## Ce que je ne dis pas, et la condition qui ferait bouger mon ordre

- **Je ne dis pas que l'audio est le moins cher, ni le plus faisable.** Je n'en sais rien et ce n'est
  pas ma competence. Si `theo-archi` chiffre l'audio a un ordre de grandeur au-dessus des deux
  autres, l'arbitrage appartient a l'utilisatrice, et mon avis n'est qu'une des deux entrees.
- **Une condition ferait changer mon ordre, et je l'ecris maintenant pour qu'on ne la decouvre pas
  apres coup** : si la capacite audio ne peut pas rendre un son **net, identique a chaque ecoute, et
  reecoutable a la demande par l'enfant**, alors je prefere que ces trois lignes restent vides. Un
  son qu'on entend une seule fois transforme un exercice de conscience phonologique en test de
  memoire, et un enfant qui a mal entendu est un enfant bloque. Ce n'est pas une condition d'audit,
  c'est le seuil en dessous duquel l'ouverture de ce domaine ne vaudrait pas la peine.
- **Trois choses que j'exigerai le jour ou un increment audio arrivera**, dites maintenant pour
  qu'aucun lot ne soit jete apres coup : le son est **rejouable autant de fois que l'enfant le veut**
  et le bouton qui le rejoue ne coute pas un essai ; aucune manche n'est **chronometree** ; et
  **rien d'essentiel ne passe par le son seul** - un enfant sourd ou un ordinateur sans son doivent
  pouvoir jouer le reste du catalogue exactement comme avant. Le jour venu, je rendrai un arbitrage
  prealable en bonne et due forme, comme pour tout contenu neuf.
- **Je ne fixe aucun sprint et je ne priorise aucune story.** L'horizon de quatre a six sprints est
  l'estimation de `camille-po`, je n'ai pas de raison de la contredire.

## Suites hors de ma main

- `camille-po` : mon ordre est **audio, visuel, saisie**, et il ne suit pas le compte de lignes -
  motif ci-dessus. Le fait que le domaine ferme par l'audio soit un domaine de **CP** est la raison
  principale, pas un argument d'appoint.
- `theo-archi` : une seule question de ma part, et elle peut changer le poids d'un de mes arguments -
  **emettre un son enregistre et dire une phrase synthetisee reposent-ils sur le meme socle
  technique ?** Si oui, la capacite audio rend lisible tout le catalogue CP en plus d'ouvrir trois
  lignes.
- `sofia-sm` : les quatre questions de validite de proxy de `DEC-0030` section 4 ne sont **pas**
  traitees par ce fichier et restent dues. Deux d'entre elles - le denombrement par
  pictogramme-caractere et les figures planes - touchent directement au perimetre de la famille
  visuelle ci-dessus.
