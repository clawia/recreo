# DEC-0013 - Perimetre du sprint 6 : trois chantiers actes, choix de textes-trous/cm1-conjugaison.yml pour l'ouverture francais engagee par DEC-0011

- **Type** : perimetre produit, priorisation de sprint, choix de matiere premiere
- **Date** : 2026-08-07
- **Auteur** : camille-po
- **Sprint** : s06
- **Competence programme visee** : transverse - decision de perimetre, une competence precise est
  choisie au corps de la decision, elle n'en constitue pas l'objet.
- **Statut** : Acceptee

## Contexte

`DEC-0011-perimetre-s05-reste-correctif.md` engage le sprint 6 sur l'ouverture d'au moins une des
deux lignes candidates de `textes-trous/` (`cm1-accords.yml`, `cm1-conjugaison.yml`), en assumant
explicitement, cette fois, qu'elle ne couvrira qu'une partie de sa competence de referentiel.
`DEC-0012-rhabillage-gabarit-menu-minimal-et-ouverture-s06.md` place par ailleurs le rhabillage du
gabarit (en-tete, icone, menu, contenu centre) au meme sprint. Ces deux chantiers restent d'actualite,
mais l'etat reel du produit a la cloture de s05 (`squad/sprints/s05-lever-le-refus-du-bon-son/
RAPPORT.md`, section "Obstacles", et `review.md`, `retro.md` du meme dossier) ajoute un troisieme
chantier que je ne peux pas laisser hors planning sans reproduire le defaut deja sanctionne en s02 -
une charge de travail reelle qui existe sans etre nommee dans aucune story :

1. **Deux increments de contenu restent a corriger avant de pouvoir etre representes a
   `nadia-prof`** : les deux points precis qu'elle a chiffres le 2026-08-07 pour lever sa condition
   sur la ligne francais CM1 (homophones), et le rhabillage du gabarit, deja livre et audite en s05
   mais porteur de quatre reserves non closes.
2. **"Le bon son" reste hors catalogue** (`DEC-0009`, tenue) sur un lot que `nadia-prof` a elle-meme
   declare indivisible dans son avis de conception du 2026-08-06
   (`squad/conformite/FICHE-bandit-manchot-le-bon-son-cp.md`, section "Avis de conception", point 5) :
   la recomposition du paquet (ex-S5-2) et le retour explicatif (ex-S5-9) se livrent ensemble ou pas
   du tout.
3. **Une dette de forme et d'outillage** s'est accumulee sans proprietaire de backlog : les quatre
   reserves chiffrees du rhabillage, un identifiant HTML duplique apres chaque echange htmx, un
   contre-exemple de garde-fou enregistre comme un vrai bean Spring, et deux scripts de verification
   proposes en retro de s05.

Je n'écarte aucun de ces trois chantiers au profit d'un seul : ce sont trois obstacles ecrits par
`nadia-prof`, par `yanis-qa`/`theo-archi` ou par `sofia-sm` elle-meme sur l'increment de s05, pas une
dette choisie par politique. Si la capacite du sprint ne permet pas les trois, c'est `sofia-sm` qui
le constate au planning, comme `DEC-0012` le prevoit deja pour l'arbitrage entre rhabillage et
ouverture francais.

## Choix du paquet `textes-trous/` : `cm1-conjugaison.yml`

Les deux candidats restent, comme au moment de `DEC-0011`, en correspondance **partielle** avec leur
ligne de referentiel :

- `cm1-accords.yml` instancie l'accord en genre et en nombre de l'adjectif (cinq schemas
  masculin/feminin singulier/pluriel), alors que la competence du referentiel
  (`squad/programme/francais/competences-cm1.md`, "Accorder en genre et en nombre dans le groupe
  nominal (determinant/nom/adjectif)") nomme trois classes de mots. Le determinant et le nom sont
  deja fixes dans chaque phrase du paquet : seule la troisieme classe, l'adjectif, varie reellement
  d'un item a l'autre.
- `cm1-conjugaison.yml` instancie six formes du present de l'indicatif (une par personne
  grammaticale : je, tu, il, nous, vous, elles), alors que la competence
  ("Conjuguer les verbes frequents au present, futur et imparfait de l'indicatif") nomme trois
  temps explicitement enumeres.

**Je retiens `cm1-conjugaison.yml`.** Le motif tient a la nature de la partie laissee dehors, pas a
une preference de contenu, que je n'ai pas a juger. La competence de conjugaison enumere trois temps
nommement (present, futur, imparfait) : le paquet en couvre exactement un, ce qui se dit et se
verifie sans ambiguite ("le present est couvert, le futur et l'imparfait ne le sont pas"). La
competence d'accord, elle, nomme trois classes de mots dont deux (determinant, nom) ne sont pas de
vraies variables dans le paquet retenu - elles sont deja correctement accordees dans l'enonce fourni
a l'enfant, si bien que dire "un tiers de la competence est couvert" serait une approximation que je
ne peux pas etayer aussi proprement. Retenir le decoupage le plus net evite de reproduire, sous une
autre forme, l'imprecision de perimetre qui a coute la ligne homophones CM1 en s04 (`DEC-0011`,
point 1).

**Ce que cette ligne couvrira, et ce qu'elle laissera dehors, assume des maintenant** :

- Couvert : le present de l'indicatif, sur des verbes frequents, aux six personnes grammaticales.
- Non couvert, et ce n'est pas cache : le futur et l'imparfait de l'indicatif, les deux autres temps
  nommes par la meme ligne du referentiel. Ils restent au backlog des sprints suivants
  (`docs/backlog/backlog.md`), sans date fixee a ce jour, comme deux extensions distinctes de ce
  meme jeu plutot que comme un defaut cache de cette ouverture.

La ligne `francais | CM1 | Orthographe et grammaire | Conjuguer les verbes frequents au present,
futur et imparfait de l'indicatif` de `squad/programme/MATRICE-couverture.md` passe donc a `en
cours` par ce planning, jamais a `couvert-valide` par une decision de planning - ce statut n'est
prononce que par `nadia-prof`, et elle jugera une competence deliberement partielle a ce stade.

## Decision

1. Le sprint 6 porte trois chantiers, tous actes par ce planning :
   - **Chantier 1** : ouverture de la ligne francais CM1 sur `cm1-conjugaison.yml` (present de
     l'indicatif uniquement) et achevement des deux points chiffres par `nadia-prof` le 2026-08-07
     sur la ligne homophones CM1 (`squad/conformite/FICHE-bandit-manchot-homophones-cm1.md`,
     verdict du 2026-08-07, section 3).
   - **Chantier 2** : sortie de quarantaine de "Le bon son", livree comme un seul lot indivisible
     (recomposition du paquet sur les trois nasales an/on/in, retour explicatif de la variation
     grapheme/phoneme), conformement a l'avis de conception de `nadia-prof` du 2026-08-06.
   - **Chantier 3** : la dette de forme et d'outillage nommee au contexte ci-dessus, detaillee en
     stories dans `docs/backlog/backlog.md`.
2. Le paquet retenu pour l'ouverture francais du chantier 1 est `textes-trous/cm1-conjugaison.yml`,
   pour le motif ecrit ci-dessus.
3. Le cas `GET /parties/{id}/manche` sur une partie terminee, signale par `nadia-prof` comme un ecran
   mort sans chemin d'acces trouve, n'est pas ecrit en story par ce planning : il reste un prealable
   technique route a `theo-archi` via `sofia-sm`, a instruire avant qu'une story puisse en decrire
   le comportement attendu.
4. Si la capacite du sprint ne permet pas les trois chantiers, c'est `sofia-sm` qui le constate au
   planning et priorise, comme `DEC-0012` le prevoit deja pour l'arbitrage entre le rhabillage et
   l'ouverture francais.

## Ce que cette decision ne fait pas

- Elle ne tranche aucune question technique : la modelisation du champ de graphie et du texte de
  retour du chantier 2, le cadrage du scan de composants du chantier 3, et le prealable
  `GET /parties/{id}/manche` restent du ressort de `theo-archi`.
- Elle ne prejuge d'aucun verdict de `nadia-prof`, y compris sur la question, deliberement partielle,
  de la ligne conjugaison CM1.
- Elle ne fait passer aucune ligne de `squad/programme/MATRICE-couverture.md` a `couvert-valide` :
  seule la ligne conjugaison CM1 entre `en cours` par ce planning.
- Elle ne rouvre pas `DEC-0012` sur le fond : l'amendement necessaire sur l'absorption de S6-1/S6-2
  dans s05 est ecrit directement dans `DEC-0012-rhabillage-gabarit-menu-minimal-et-ouverture-s06.md`,
  pas ici.

## Impact

- `docs/backlog/backlog.md` : section Sprint 6 completee (S6-3 a S6-14).
- `squad/programme/MATRICE-couverture.md` : ligne conjugaison CM1 passee `en cours` (jeu et
  mecanique renseignes), section "Perimetre de s06" ajoutee.
- `squad/conformite/FICHE-bandit-manchot-conjugaison-present-cm1.md` : fiche ouverte, bloc de
  validation vide.
- `squad/decisions/produit/DEC-index.md` : ligne ajoutee pour `DEC-0013`.

## Ce qui reste non testable par construction

- Que les trois chantiers tiennent effectivement dans la meme fenetre de sprint reste un engagement
  de planification, pas une garantie (meme reserve que `DEC-0011` et `DEC-0012`).
- Que le futur et l'imparfait soient effectivement traites dans un sprint ulterieur n'est pas garanti
  par cette decision : je le documenterai au moment ou une story les instruira reellement, plutot que
  d'affirmer ici une date certaine.
