# DEC-0035 - Trois arbitrages de l'utilisatrice du sprint 15 n'avaient aucune trace ecrite : sortie permanente en tete, rang unique, couleur d'etat actif

- **Type** : regularisation de gouvernance - trois arbitrages successifs de l'utilisatrice, deja
  implementes et deja juges au moins en partie par `nadia-prof`, sans qu'aucune decision produit ne
  les acte
- **Date** : 2026-08-18
- **Auteur** : camille-po
- **Sprint** : s15 (cloture)
- **Competence programme visee** : transverse - pas de contenu pedagogique. Ces trois arbitrages
  touchent la navigation et l'apparence de l'en-tete ; aucun ne cible une ligne de
  `squad/programme/MATRICE-couverture.md`.
- **Statut** : Acceptee (ces arbitrages sont deja vecus par le code fusionne sur `round-two` ; cette
  decision les enregistre, elle ne les instruit pas et ne les rediscute pas)

## Ce que ce fichier fait, et pourquoi il existe

En regularisant retroactivement le planning de s15
(`squad/sprints/s15-cinq-arbitrages-de-l-en-tete/planning.md`), `sofia-sm` constate que le sprint a
ete pilote par **cinq arbitrages successifs de l'utilisatrice**, rendus en jouant l'application, et
que **deux seulement sont traces par une decision produit**
(`DEC-0034-utilisatrice-retablit-les-jeux-et-unifie-lapparence-des-puces.md`, arbitrages A et B).
Les trois suivants sont deja fusionnes sur `round-two`, l'un deja juge par `nadia-prof` avec un
Done pedagogique prononce, et **aucun n'existe dans un fichier `DEC`**. Ce fichier les trace, dans
l'ordre ou ils ont ete rendus.

Je ne rediscute aucun des trois : ils viennent de l'autorite au-dessus de la squad, qui a joue le
produit avant chaque arbitrage. Ce qui m'appartient est de nommer qui a decide quoi et quand, et
d'ecrire ce que chacun coute a un enfant - fait mesure par la squad, jamais une reserve de ma part.

**Une limite que je signale plutot que de la cacher.** A la difference de `DEC-0034`, qui citait
les mots de l'utilisatrice pour les arbitrages A et B, **aucun fichier du depot ne porte de
citation verbatim** pour les arbitrages C, D et E ci-dessous. Je les reconstitue depuis les commits
de `marc-dev` et de `theo-archi`, qui les rapportent sans les citer ("l'utilisatrice renverse
l'arbitrage de nadia-prof du 2026-08-07", "deux demandes de l'utilisatrice, deja instruites"). Cette
paraphrase est la meilleure source ecrite disponible aujourd'hui ; ce n'est pas la parole de
l'utilisatrice elle-meme, et je ne pretends pas le contraire.

## Chronologie generale, pour qu'un lecteur dans six mois comprenne l'en-tete qu'il trouve

| Qui | Quand | Quoi |
|---|---|---|
| `nadia-prof` | 2026-08-07 (S6-9) | arbitrage prealable : une seule sortie par ecran de jeu, le mot est "Les jeux" ; masquee sur les ecrans de jeu (`ADR-0022` point 3) |
| `camille-po` | 2026-08-14 | `DEC-0027` : la demande de retirer le bouton de sortie de la manche pour ne garder que celui de l'en-tete reouvre S6-9 ; arbitrage prealable requis |
| `nadia-prof` | 2026-08-14 | arbitrage prealable de `DEC-0027` : principe accepte sur les seuls etats de `fragments/manche.html`, forme instruite **refusee**, six conditions PORTE-1 a PORTE-6 |
| `nadia-prof` | 2026-08-17 | BARRE-7 (S14-1/S14-2) rendue bloquante par les puces de niveau ; refus puis Done VALIDE SOUS RESERVE sur l'increment corrige |
| `camille-po` | 2026-08-17 | `DEC-0033` : le lien "Les jeux" est retire de tout le depot, y compris l'accueil |
| **utilisatrice** | **2026-08-17** | **arbitrages A et B** : le lien revient dans l'en-tete dans son etat d'avant s14 ; les puces prennent son apparence - traces par `DEC-0034` |
| **utilisatrice** | **2026-08-18** | **arbitrage C** : la sortie "Les jeux" reste visible dans l'en-tete y compris pendant une manche en cours - non trace avant ce fichier |
| `theo-archi`, `marc-dev` | 2026-08-18 | second marqueur `ecran-porte-sa-sortie`, retournement de l'epinglage, fusion `b109f98` |
| `nadia-prof` | 2026-08-18 | Done pedagogique **VALIDE SOUS RESERVE** sur l'arbitrage C (increment `b109f98`) ; dans le meme geste, **arbitrage prealable** sur les deux demandes suivantes |
| **utilisatrice** | **2026-08-18** | **arbitrages D et E** : un seul rang dans l'en-tete ; une couleur sur la puce de niveau choisie, eteinte au demarrage du jeu - non traces avant ce fichier |
| `theo-archi`, `ines-design`, `marc-dev` | 2026-08-18 | mecanisme en deux moities, forme chiffree, amendement d'`ADR-0044`, fusion `65da40e` |
| `nadia-prof` | 2026-08-18 | Done pedagogique **VALIDE** sur D et E (commit `726e0c4`) - **non fusionne sur `round-two`**, voir note plus bas |
| **utilisatrice (en jouant)** | 2026-08-18 | trouve une regression sur l'arbitrage E : la puce garde sa couleur apres un retour au catalogue complet |
| `theo-archi`, `marc-dev` | 2026-08-18 | correctif technique (regle positive et conditionnelle, fusion `6fad9a9`) |

## Arbitrage C - la sortie "Les jeux" reste visible dans l'en-tete pendant une manche en cours

**Ce que l'utilisatrice a rendu**, tel que rapporte par le commit qui l'implemente : *"L'utilisatrice
renverse l'arbitrage de nadia-prof du 2026-08-07 (S6-9) : 'Les jeux' doit rester dans l'en-tete y
compris pendant une partie."* (`b7eda0b`, `marc-dev`).

### Ce que cela defait, nomme sans etre attenue

Ceci renverse **l'arbitrage prealable de `nadia-prof` du 2026-08-07 (S6-9, `ADR-0022` point 3)** :
une seule sortie par ecran de jeu, et c'est le bouton de la zone de contenu qui survit, jamais le
lien de l'en-tete - arbitrage que `nadia-prof` avait ensuite **rejoue et confirme en Done
pedagogique VALIDE** le 2026-08-10. Il annule aussi, de fait, les six conditions PORTE-1 a PORTE-6
que `nadia-prof` avait posees le 2026-08-14 dans l'arbitrage prealable de `DEC-0027` (principe
accepte sur la manche seule, forme refusee pour les autres ecrans) : la forme livree en s15 n'est
ni celle que `DEC-0027` avait instruite ni couverte par elle, elle est neuve et jugee pour la
premiere fois ici.

**Deux comptes de conditions bloquantes tombent au passage**, mesures par `nadia-prof` elle-meme
sur l'increment livre (`squad/conformite/FICHE-barre-entree-niveau.md`, section "Done pedagogique
du 2026-08-18") :

- **BARRE-3** ("au plus une porte lue avant l'enonce sur un ecran de manche") : **compte annule**.
  Sur une manche, l'enfant lit desormais trois elements actionnables avant l'enonce - "Les jeux",
  puis les deux puces de niveau - alors que la condition en tolerait un seul. `nadia-prof` l'ecrit
  elle-meme : *"une condition qu'on ne peut plus tenir sans defaire un arbitrage n'est plus une
  condition, c'est un souhait."* Elle garde la substance sous une autre forme : *"la colonne de
  contenu d'un ecran de jeu ne commence jamais par une porte"* (verifie tenue, devient la condition
  **S15-6** du lot suivant).
- **BARRE-9** ("les puces de niveau et la sortie Les jeux ne partagent jamais le meme rang") :
  **annulee par l'arbitrage B** de `DEC-0034`, et l'arbitrage C rend ce partage de rang effectif
  pendant une manche egalement, pas seulement sur l'accueil.

### Ce que cela ferme, et le mecanisme retenu

L'implementation ne se contente pas de demasquer le lien partout : `theo-archi` introduit un
**second marqueur**, `ecran-porte-sa-sortie` (fait structurel, verifiable contre le document -
"cet ecran rend sa propre sortie" - distinct du jugement pedagogique que porte `ecran-exclusif`),
qui masque le lien d'en-tete sur la confirmation et la fin de partie, pendant que la manche cesse
d'etre masquee. Deux clauses ArchUnit neuves gardent la coherence : le marqueur ne peut pas mentir
(porte si et seulement si l'ecran rend un `data-role="sortie"`), et au plus une sortie visible par
ecran assemble. `nadia-prof` credite cette forme d'avoir referme, par un chemin qu'elle n'avait pas
prevu, **sa reserve S14-A** (bande sombre residuelle) et **la seconde moitie de sa reserve S14-B**
(le chemin de retour aux onze jeux existe desormais, visible en permanence).

### Statut pedagogique

`nadia-prof` a prononce, sur l'increment fusionne `b109f98` : **Done pedagogique VALIDE SOUS
RESERVE**, motif en substance : *"l'increment applique fidelement les arbitrages, et il ferme trois
defauts [...]. Ce qu'il coute a un enfant [...] decoule de decisions qui ne sont pas celles de
l'equipe, et je l'inscris comme defait par arbitrage, jamais comme un motif contre le lot."* Une
reserve neuve nait de cet arbitrage combine a l'arbitrage B : **S15-C**, *"rien ne distingue plus, a
l'oeil, le bouton qui montre tout de ceux qui filtrent"* - non bloquante, et que `nadia-prof` note
elle-meme comme susceptible d'etre fermee par le lot suivant (arbitrages D et E) si ses conditions
sont tenues.

## Arbitrage D - un seul rang dans l'en-tete

**Ce que l'utilisatrice a rendu**, tel que rapporte par le commit qui l'implemente : *"Un seul
rang : icone, titre, CP, CM1, puis 'Les jeux' pousse a droite"* (`f46aa5a`, `marc-dev`). Elle
**ecarte** la variante en deux rangs qu'elle avait approuvee la veille (arbitrage B, mise en oeuvre
d'`ines-design`), et elle **accepte d'avance** le retour a la ligne aux largeurs qui ne permettent
pas un rang complet.

### Ce que cela coute, mesure et non suppose

`ines-design` chiffre deux seuils sur le rendu reel avant que le choix ne soit tranche
(`squad/sprints/s15-cinq-arbitrages-de-l-en-tete/proposition-ines-etat-actif-et-rang-unique.md`) :

- **le rang unique complet n'apparait qu'a partir de 470 px** (elle corrige elle-meme une
  estimation anterieure de 420 px, donnee a l'utilisatrice, et documente l'ecart) ;
- **le groupe icone-titre-CP-CM1 a besoin de 346 px pour ne jamais se rompre**, soit une marge de
  **14 px seulement** au-dessus du plancher de 360 px que le produit s'est deja donne. Sous ce
  seuil, le mode de defaillance de la forme retenue (variante 1, flex simple) est que "CM1" change
  de voisin de ligne - visible et reversible, aucune fonction perdue - contre un debordement de
  page entiere pour la variante ecartee.

**A la largeur plancher du produit (360 px), l'en-tete reste donc a deux rangs** : le rang unique
n'est qu'un comportement des largeurs superieures a 470 px, jamais celui du plancher. L'utilisatrice
a ete informee de ce chiffre et **a refuse d'abaisser le plancher de 360 px** pour l'atteindre -
elle accepte le retour a la ligne plutot que de renoncer au plancher.

### Statut pedagogique

`nadia-prof` a rendu un **arbitrage prealable** le 2026-08-18, dans le meme geste que son Done sur
l'arbitrage C : variante 1 (flex simple) **retenue**, variante 2 (groupe insecable) **refusee**
pour son mode de panne (une partie de "CM1" **inatteignable** sans geste de defilement, sous le
plancher). Sept conditions bloquantes S15-1 a S15-7 sont posees pour le lot a venir.

Un **Done pedagogique VALIDE** existe sur l'increment resultant, mesure par `nadia-prof` elle-meme :
**zero debordement de document aux six largeurs verifiees (300, 320, 345, 346, 360 et 470 px)**,
**en-tete a 143 px a 360 px** (contre 151 px avant ce lot, exactement la borne qu'elle avait posee).
Ce verdict existe dans le commit `726e0c4` mais **n'est pas fusionne sur `round-two`** - voir la
note plus bas, deja routee par `sofia-sm` a elle-meme dans `planning.md`, que je ne reroute pas une
seconde fois.

## Arbitrage E - une couleur sur la puce de niveau choisie, eteinte au demarrage du jeu

**Ce que l'utilisatrice a rendu**, tel que rapporte par le meme commit : la seconde des *"deux
demandes de l'utilisatrice"* - une couleur qui marque la puce pressee, qui s'eteint quand la partie
commence.

### Ce que cela defait

Ceci renverse la **premiere phrase du point 3 de `ADR-0044`**, ecrite par `theo-archi` lui-meme le
2026-08-17 : *"L'en-tete permanent ne porte aucun etat que le serveur ne peut pas rafraichir"* et son
corollaire, *"l'etat filtre est annonce dans la zone echangee"*. `theo-archi` amende son propre ADR
le 2026-08-18 pour l'ecrire : cette phrase tombe, parce qu'elle etait *"le choix par defaut du point
3, pas un fait technique"*. Le fait technique qui survit (*"aucun echange htmx n'atteint l'en-tete"*)
est ce qui rend la branche conditionnelle que ce meme ADR avait deja nommee **desormais l'etat de
l'application** : marquer une puce active exige un rendu de document complet, puisque l'en-tete ne
peut pas etre rafraichi par un echange partiel.

**Le prix avait ete nomme d'avance, avant meme que l'arbitrage ne le rende du** : *"chaque appui sur
une puce devient un rendu de document complet, sans echange htmx [...] un chargement de page a
chaque changement de niveau, la ou tout le reste de l'application echange un fragment."*
(`ADR-0044`, redaction du 2026-08-17). Les puces perdent donc leurs attributs htmx et redeviennent
des liens simples vers la route de niveau ; l'etat actif est pose par le serveur en `th:class`, et
neutralise par une regle maison quand la zone porte le marqueur d'ecran de jeu.

### Ce que `nadia-prof` a exige, de sa propre initiative

Dans son arbitrage prealable du 2026-08-18, `nadia-prof` accepte l'etat actif (plein blanc contre
contour, contraste mesure a 9,25:1) sous trois conditions qu'elle qualifie elle-meme de jambes d'un
meme accord : la couleur n'est pas celle d'une action (`--recreo-accent` ecarte, ce qui aurait donne
a la puce l'apparence du bouton "Jouer"), l'extinction est due au demarrage du jeu et ne survit pas
a un chargement de page (non-persistance, condition **S15-5**), et **une phrase qui nomme le
filtre** est due **meme sans la couleur** - "Pour le CP" / "Pour le CM1" - parce que la phrase et la
couleur ne disent pas la meme chose (la phrase dit ce qui est montre, la puce dit comment en
changer) et que seule la phrase est certaine d'apparaitre au moment ou l'enfant presse, l'en-tete
n'etant jamais rafraichi par l'echange qui suit cet appui.

### Statut pedagogique

Meme arbitrage prealable que D (sept conditions S15-1 a S15-7, dont S15-2 a S15-5 portent
specifiquement sur E), meme Done pedagogique VALIDE non fusionne (`726e0c4`) : contraste percu
9,25:1 mesure sur les pixels rendus, phrase "Pour le CP" verifiee presente dans le fragment
echange.

## Le correctif de regression trouve par l'utilisatrice - ce que j'en fais, et pourquoi

**Le defaut** : l'utilisatrice presse "CM1" puis "Les jeux" ; la liste montre les onze jeux et la
puce "CM1" garde sa couleur active. Cause diagnostiquee (`5b2ccc0`, `marc-dev`) : la regle de
neutralisation du premier lot ne visait que le marqueur d'ecran de jeu, et les routes qui rendent le
catalogue complet sans filtre (`POST /retour` et deux routes voisines) ne portent ni ce marqueur ni
aucun filtre - la puce restait donc pleine sans qu'aucune regle ne l'eteigne.

**Ce que j'en fais : ce correctif n'entre pas dans cette trace en tant qu'arbitrage neuf.** Ce n'est
pas une decision produit distincte, c'est une non-conformite entre le code livre et une condition
que `nadia-prof` avait deja posee **avant** ce lot, dans le meme arbitrage prealable qui couvre D et
E : **S15-5**, *"aucun enfant ne voit un etat actif qu'il n'a pas produit [...] le filtre ne survit
ni a une sortie de partie, ni a un chargement de page."* Presser "Les jeux" depuis une liste filtree
pour revenir au catalogue complet est exactement le cas que cette condition vise ; le defaut trouve
par l'utilisatrice est une implementation incomplete de S15-5, pas un renversement d'une decision
produit ni un arbitrage nouveau de sa part. `theo-archi` le corrige en changeant la forme de la
regle (une regle positive et conditionnelle plutot qu'une neutralisation qui devait enumerer ce
qu'elle defaisait) - c'est un choix technique, pas un choix produit.

**Je justifie plutot que de trancher par defaut** : si ce correctif avait change ce que l'enfant voit
au-dela de ce que S15-5 decrivait deja - par exemple si l'utilisatrice avait, a cette occasion,
redemande la persistance du filtre ou une autre regle de disparition - j'aurais du l'ecrire ici comme
un arbitrage F. Ce n'est pas le cas : la regle corrigee produit exactement le comportement que la
condition S15-5 exigeait deja, sur un cas que la premiere forme de la regle avait manque. Une
decision produit ici enregistrerait une decision qui n'a pas ete prise une seconde fois.

## Ce que ces trois arbitrages ne font pas

- Ils ne prononcent aucun Done pedagogique : celui de C est deja ecrit par `nadia-prof`
  (`b109f98`) ; celui de D et E existe (`726e0c4`) mais n'est pas encore la reference du depot tel
  qu'il se lit sur `round-two`.
- Ils n'ouvrent ni ne ferment aucune ligne de `squad/programme/MATRICE-couverture.md`.
- Ils ne choisissent aucun moyen technique : le second marqueur, le mecanisme en deux moities, la
  regle de neutralisation puis sa forme positive sont des choix de `theo-archi`, deja ecrits dans
  `squad/sprints/s15-cinq-arbitrages-de-l-en-tete/`.
- Ils ne ferment pas la reserve **S15-C** (trois boites de meme apparence, roles differents) : elle
  reste ouverte, `nadia-prof` ayant elle-meme indique que le lot D/E peut la fermer si ses
  conditions sont tenues sur l'increment qu'elle rejugera.

## Notes - consequences que je signale sans en faire des conditions

- **Le Done pedagogique VALIDE sur D et E (`726e0c4`) n'est pas fusionne sur `round-two`.** Verifie
  par moi (`git merge-base --is-ancestor 726e0c4 6fad9a9` repond non) avant d'ecrire cette note.
  `sofia-sm` l'a deja route a elle-meme dans `planning.md` comme obstacle ouvert ; je ne le
  reroute pas, je le signale seulement pour qu'un lecteur de cette decision ne croie pas que
  `FICHE-barre-entree-niveau.md`, dans son etat actuel sur `round-two`, porte le verdict complet -
  elle s'arrete au Done de C.
- **Aucune citation verbatim de l'utilisatrice n'existe pour C, D et E**, a la difference de A et
  B. Ce n'est pas une omission de ce fichier : je n'ai trouve aucune trace de ce type dans le
  depot pour ces trois arbitrages, seulement leur paraphrase par `marc-dev` et `theo-archi`. Je le
  signale comme un ecart de methode par rapport a `DEC-0034`, sans le corriger en inventant une
  citation que je n'ai pas.

## Impact

- `squad/decisions/produit/DEC-index.md` : ligne DEC-0035 ajoutee.
- `squad/decisions/produit/DEC-0027-retrait-bouton-sortie-fragment-manche-reouvre-s6-9-arbitrage-prealable-requis.md` :
  note datee du 2026-08-18 ajoutee sous ses amendements existants, renvoyant vers l'arbitrage C
  ci-dessus.
- `squad/decisions/produit/DEC-0034-utilisatrice-retablit-les-jeux-et-unifie-lapparence-des-puces.md` :
  note datee du 2026-08-18 ajoutee, sur le point precis ou son propre texte est depasse par
  l'arbitrage C.
- `squad/conformite/FICHE-barre-entree-niveau.md` : **inchangee par cette decision.** Les verdicts
  de `nadia-prof` qu'elle porte restent tels qu'ecrits ; cette decision ne fait que les nommer et
  les dater dans un fichier de gouvernance produit.
- `squad/programme/MATRICE-couverture.md` : inchangee.
- `docs/backlog/backlog.md` : **non modifie par cette decision.** Contrairement a `DEC-0034`, qui
  avait ouvert `S15-1` et `S15-2`, je n'ecris pas ici de stories retroactives pour les arbitrages C,
  D et E : cela deborderait le mandat que j'ai recu pour cette regularisation (tracer la decision
  produit, pas reconstituer un backlog apres coup). Je le signale comme un ecart avec ma propre
  regle ("une story qui n'est pas dans le backlog n'existe pas") plutot que de le combler par une
  ecriture que personne ne m'a demandee ; a router si `sofia-sm` juge qu'il doit etre comble avant
  cloture complete de s15.

## Ce que je route

- **`sofia-sm`** : fusionner le commit `726e0c4` (Done pedagogique VALIDE de `nadia-prof` sur D et
  E), lignee deja verifiee par elle-meme selon son propre commit - deja route dans `planning.md`,
  je ne fais que le rappeler ici pour qu'il ne se perde pas entre deux fichiers.
- **`nadia-prof`** : Done pedagogique du correctif de regression (lot 5, `6fad9a9`), reporte par
  l'utilisatrice a la fin du sprint - deja route dans `planning.md`.
- **Personne, pour la reserve S15-C** : elle reste ouverte, non bloquante, et sa fermeture depend du
  Done complet sur l'increment D/E une fois fusionne - pas d'une action produit distincte a ce
  stade.
