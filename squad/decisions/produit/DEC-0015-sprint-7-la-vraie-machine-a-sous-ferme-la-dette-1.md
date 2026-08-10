# DEC-0015 - Le sprint 7 construit la vraie machine a sous et ferme la dette n. 1 de CLAUDE.md

- **Type** : portee
- **Date** : 2026-08-10
- **Auteur** : camille-po
- **Sprint** : s07
- **Competence programme visee** : transverse - mecanique de jeu, aucun contenu pedagogique
  nouveau. Aucune ligne de `squad/programme/MATRICE-couverture.md` ne bouge par cette decision.
- **Statut** : Acceptee

## Contexte

L'utilisatrice a joue l'application et l'a dit dans ses mots : **"les jeux existants ne reposent
pas sur un bandit manchot. C'est une machine avec les reponses proposees dans les colonnes."**

Verifie contre le code avant d'ecrire cette decision. Le gabarit
`adapters/adapter-web/src/main/resources/templates/fragments/manche.html` rend un score, une
consigne, un enonce, un indice, et un `<form class="propositions">` de boutons `<button
class="btn proposition">` empiles verticalement (`.propositions { display: flex; flex-direction:
column; ... }` dans `recreo.css`). C'est un questionnaire a choix multiple. Aucun fichier du
depot - Java, HTML, CSS ou JavaScript - ne contient un mot du vocabulaire de machine a sous
(rouleau, colonne, levier).

**C'est la dette n. 1 de `CLAUDE.md` reproduite.** Sur `main`, `RuleSet` etait cite dans
`CLAUDE.md` et dans quatre prompts d'agents sans exister dans aucun fichier Java (`ADR-0005` a
acte son abandon pour cette raison precise). Ici, "bandit manchot" nomme la mecanique dans 25
`ADR`, dans `adapters/adapter-content/src/main/resources/catalogue.yaml`, dans les trois fiches de
`squad/conformite/`, dans quatre lignes de `squad/programme/MATRICE-couverture.md` et dans
l'identifiant de chacun des jeux du catalogue (`bandit-manchot-additions-cp`,
`bandit-manchot-le-bon-son-cp`, `bandit-manchot-conjugaison-present-cm1`,
`bandit-manchot-homophones-cm1`) - et la machine a sous elle-meme n'existe nulle part dans le
code. `CLAUDE.md` ne laisse que deux issues a ce constat : soit l'abstraction existe dans le code,
soit un `ADR` acte explicitement son abandon.

### La direction est tranchee par l'utilisatrice

Parmi trois sorties presentees, elle a choisi la vraie machine a sous plutot qu'un habillage ou un
renommage. Ce qu'elle a valide, dans ses mots :

- l'enfant tire un levier ;
- les trois colonnes defilent puis s'arretent, chacune sur une proposition ;
- il choisit la colonne juste.

Cette direction n'est pas rediscutee ici.

### Questions techniques, routees en parallele, non tranchees par cette decision

Une animation est du client, or `ADR-0008` met le comportement de jeu sur le serveur et
`ADR-0014` point 7 interdit le JavaScript de Bootstrap ; le sort de la reponse au clavier reste a
trancher. Ces questions sont techniques : elles vont a `theo-archi`, saisi en parallele par
`sofia-sm`. Je ne les tranche pas ici et aucune story de cette decision ne presuppose leur reponse.

## Decision

**Le sprint 7 construit la mecanique de machine a sous decrite par l'utilisatrice, en remplacement
du rendu actuel de boutons empiles, sans toucher aux contrats de decouplage deja actes
(`ADR-0004`, `ADR-0008`) : le serveur reste seul detenteur de l'etat et du verdict, la mecanique
reste ignorante de la matiere et du niveau.** C'est le choix qui ferme la dette n. 1 : l'abstraction
"bandit manchot" existera dans le code, plutot que d'etre abandonnee par un `ADR` comme `RuleSet`
l'a ete.

Quatre stories transverses portent ce chantier (S7-1 a S7-4, `docs/backlog/backlog.md`, section
"Sprint 7"). Aucune ne porte de competence programme et aucune ne fait bouger une ligne de la
matrice : ce sprint reconstruit une mecanique deja jouee, il n'ouvre aucun contenu nouveau.

### Arbitrages de perimetre produit

La direction de l'utilisatrice laisse ouvertes quatre questions que je tranche ici, en les nommant
plutot qu'en les supposant.

**1. Le levier est tire a chaque manche, et c'est le meme geste que celui qui existe deja
aujourd'hui.**

Je n'ajoute pas de geste pour un enfant de CP. Le bouton qui dit aujourd'hui "Encore !" (et le
bouton "Demarrer" au tout premier tour) devient le geste qui tire le levier : une seule action de
l'enfant declenche a la fois l'apparition de la question suivante et le defilement des colonnes.
Je n'introduis pas un clic "je tire le levier" suivi d'un second clic "je vois la question" : ce
serait un geste de plus par manche, exactement le risque que je veux ecarter, et sur lequel
dependent l'aide de S1-5/S3-2 (l'indice apres trois echecs) et la "manche plus simple" - leurs
conditions de declenchement ne changent pas, seule la forme du geste qui les affiche change de
nom et d'habillage.

**2. L'enonce est lisible avant et pendant le defilement, jamais seulement apres.**

Un enfant de CP qui lit lentement a besoin de temps sur l'enonce. Je tranche que l'enonce
(l'ensemble consigne + question) s'affiche et reste lisible avant meme que le levier ne soit tire,
et qu'il ne bouge pas, ne se floute pas et ne disparait pas pendant que les colonnes defilent.
C'est le defilement des propositions qui est un evenement visuel ; la question, elle, reste un
element de lecture stable du debut a la fin de la manche.

**3. Le nombre de colonnes suit le nombre de propositions du contenu ; il n'est jamais fige a
trois par la mecanique.**

Les paquets actuels n'ont pas tous le meme nombre de propositions : "additions CP" en porte trois,
"Le bon verbe" en porte quatre (constat de `nadia-prof` du 2026-08-10,
`squad/conformite/FICHE-bandit-manchot-conjugaison-present-cm1.md`, section 4). Une mecanique qui
exigerait exactement trois colonnes romprait le principe directeur du produit (`ADR-0004` :
une mecanique sert n'importe quel contenu sans modification de code) des la premiere fois qu'un
contenu a quatre propositions repasserait devant `nadia-prof`. Je tranche donc que le nombre de
colonnes rendues est une consequence du nombre de propositions que le contenu fournit, jamais une
constante de la mecanique. Ce n'est pas une question de forme visuelle : c'est une question de
perimetre produit, parce qu'une mecanique taillee pour trois propositions est exactement le genre
de dette que le principe directeur du produit interdit.

**4. L'affichage sur 360 px de large n'est pas presuppose ; il est verifie avant developpement,
sans rien sacrifier des planchers deja gagnes.**

Je ne decide pas si trois (ou N) colonnes tiennent cote a cote sur un ecran de 360 px de large -
c'est une question de rendu, donc technique. Ce que je pose comme non negociable : cette
reconstruction ne fait reculer aucun plancher deja gagne par un refus pedagogique - cible tactile
d'au moins 2,75 rem, lisibilite d'au moins 1,25 rem, ecart d'au moins 1 rem entre deux boutons de
reponse (`.propositions { gap }`), hierarchie de saillance action/sortie d'`ADR-0017`. Si N
colonnes de cette taille minimale, espacees de ce minimum, ne tiennent pas sur la largeur cible de
375 px deja fixee par `DEC-0007` point 5, la reponse est un choix de disposition (colonnes non
juxtaposees horizontalement, defilement vertical, autre forme) que je laisse a `theo-archi` et
`ines-design` - je ne presuppose pas que les colonnes sont necessairement cote a cote. C'est a
`theo-archi` de le dire dans son avis de faisabilite avant que la story n'entre en developpement,
comme pour toute story de `squad/README.md` section 9 etape 1.

### Acquis pedagogiques touches, signales pour arbitrage prealable de nadia-prof

`squad/README.md` section 10 (amendement de la retro s06) exige un arbitrage prealable de
`nadia-prof` avant developpement pour toute story qui modifierait un ecran deja accorde. Je
signale, sans le trancher, que ce chantier touche potentiellement tous les acquis suivants de
l'ecran de jeu, tous deja defendus nommement par elle dans un refus ou une reserve anterieurs :

- la cible tactile d'au moins 2,75 rem (motif du refus de S2-1) ;
- la lisibilite d'au moins 1,25 rem, y compris `.indice` et `.score` ;
- la taille du chiffre de chaque proposition (au moins 2 rem, S3-4) et sa comparaison avec
  `.enonce` ;
- l'ecart `.propositions { gap }` d'au moins 1 rem entre deux boutons de reponse (S3-5) ;
- la hierarchie de saillance action/sortie d'`ADR-0017` (l'action ne declare jamais moins que la
  sortie) ;
- l'etat `sortie-pleine` de l'ecran de fin de partie, que `nadia-prof` a nommement defendu en s06
  (`ADR-0022`, arbitrage du 2026-08-07 sur S6-9) : la sortie de fin de partie n'a aucun bouton
  d'action a concurrencer sur son ecran, et cette reconstruction ne doit pas lui en donner un par
  accident.

Je nomme cette liste pour que `sofia-sm` saisisse `nadia-prof` sur une liste ecrite, pas sur une
impression au fil du developpement.

### La story des cinq indices fautifs de "Le bon verbe"

Ma position : rien n'empeche cette story d'entrer au sprint 7. Elle corrige un contenu YAML
(`adapters/adapter-content/src/main/resources/contenu/cm1-conjugaison-present.yaml`) sans toucher
au gabarit ni a la mecanique ; elle est independante des quatre stories ci-dessus, deja prete a
developper (`DEC-0014`), et sa taille est annoncee petite par `nadia-prof` elle-meme. Le seul point
de vigilance que je remonte a `sofia-sm` : si la reconstruction de la machine a sous (S7-1)
modifie la facon dont `.indice` est rendu dans `fragments/manche.html`, les deux chantiers touchent
la meme zone du meme fichier et un ordre de sequencement peut eviter un conflit inutile. La
decision de capacite - faire entrer cette story dans ce sprint, et dans quel ordre - reste celle
de `sofia-sm`, comme deja ecrit dans `DEC-0014` et dans `docs/backlog/backlog.md`.

## Justification pedagogique

Transverse : cette decision ne juge aucun contenu et ne prejuge d'aucun jugement pedagogique. Le
choix de la vraie machine a sous a ete fait par l'utilisatrice elle-meme, pas par moi. Ce que je
tranche ici est un perimetre produit (geste de l'enfant, lisibilite de l'enonce, genericite du
nombre de colonnes, non-regression des planchers deja gagnes), pas une qualite pedagogique : ce
jugement reste entierement celui de `nadia-prof`, saisie explicitement ci-dessus sur les acquis
touches.

## Impact

- `docs/backlog/backlog.md`, nouvelle section "Sprint 7 - Une vraie machine a sous, pas un
  questionnaire empile" : quatre stories transverses (S7-1 a S7-4).
- `squad/programme/MATRICE-couverture.md` : inchangee. Aucune ligne ne bouge, cette decision ne
  porte aucun contenu pedagogique nouveau.
- `squad/decisions/produit/DEC-index.md` : ligne DEC-0015 ajoutee.
- Question technique routee a `sofia-sm`, qui saisit `theo-archi` en parallele : conception de
  l'animation cote serveur/client compatible `ADR-0008` et `ADR-0014` point 7, sort de la reponse
  au clavier, et avis de faisabilite sur la largeur 360-375 px (arbitrage 4 ci-dessus).
- Acquis pedagogiques a arbitrer par `nadia-prof` avant developpement, listes ci-dessus : a router
  par `sofia-sm`.

## Ce que cette decision ne fait pas

- Elle ne tranche aucune question technique (mecanisme d'animation, gestion du clavier,
  disposition exacte a 360 px) : ces choix appartiennent a `theo-archi`.
- Elle ne juge la qualite pedagogique d'aucun ecran ni d'aucun contenu : ce jugement reste
  entierement celui de `nadia-prof`.
- Elle ne fait passer aucune ligne de `squad/programme/MATRICE-couverture.md` a `couvert-valide`
  ni a un autre statut.
- Elle ne rouvre aucun jeu retire du catalogue (`DEC-0009`, `DEC-0014`) : ces retraits restent en
  vigueur jusqu'a un nouveau Done pedagogique propre a chacun.

## Amendement du 2026-08-10 (avis de faisabilite de theo-archi, ADR-0026) - "Le bon verbe" ne
rejoint pas le catalogue en rouleaux tant que son nombre de propositions ou la mise en page n'est
pas tranche

`theo-archi` valide la direction et converge, sans avoir lu cette `DEC`, sur l'arbitrage 1
ci-dessus (le levier est l'apparence du bouton qui ouvre deja une manche, zero geste ajoute) et sur
l'arbitrage 3 (le nombre de colonnes derive du contenu). Il route une contradiction que je n'avais
pas vue entre l'arbitrage 3 et ma position sur la story des indices fautifs de "Le bon verbe"
ci-dessus, et il refuse explicitement de la trancher a ma place.

**Le fait mesure, sur `outils/mesurer-rendu.sh`, aux deux feuilles reellement servies.** Trois
colonnes tiennent, a 360 px comme a 375 px (la largeur cible actee par `DEC-0007` point 5, pas
360 px qui n'est que la largeur d'audit de `nadia-prof` - precision de `theo-archi` que je reprends
telle quelle) : 99 px par colonne a 360 px sur les paquets a trois propositions
(`cp-additions-jusqu-a-10`, `cp-le-bon-son`), 158 px sur le paquet a deux propositions
(`cm1-homophones`), aucun plancher pedagogique approche par le bas. **Quatre colonnes ne tiennent
pas** : `cm1-conjugaison-present` ("Le bon verbe"), seul paquet du depot a quatre propositions,
tombe a 70 px par colonne et le texte deborde de son aplat, identiquement a 375 px. `theo-archi` a
par ailleurs mesure et refuse d'ecrire un garde-fou en nombre de caracteres : `oreille` (7
caracteres) tient, `mangez` (6) ne tient pas - le budget est une largeur de glyphes, pas un compte
de caracteres, et je n'ecris donc aucun critere d'acceptation sur un nombre de caracteres.

**La contradiction, dans mes propres mots.** L'arbitrage 3 ci-dessus (le nombre de colonnes suit le
contenu, jamais fige a trois) et ma position affirmant que "rien n'empeche" la story de correction
de "Le bon verbe" d'entrer au sprint 7 ne peuvent pas etre tenus ensemble jusqu'au bout : une fois
"Le bon verbe" corrige et revalide par `nadia-prof`, le remettre au catalogue sans autre geste le
ferait rendre par la machine a sous en quatre colonnes qui debordent a la largeur cible. Je corrige
donc ma position ci-dessus, qui ne portait que sur le developpement de la correction (un contenu
YAML), pas sur ce qui suit une fois cette correction validee.

**Mon arbitrage, en deux phrases.** Le chantier mecanique (S7-1 a S7-4) et le developpement de la
correction des cinq indices continuent sans changement dans ce sprint : aucun des deux ne depend de
l'autre, et aucun n'exige que "Le bon verbe" soit servi en rouleaux des ce sprint. En revanche, je
ne remets pas "Le bon verbe" au catalogue tant que l'une de deux questions - reduire ses
propositions a trois, ou replier ses colonnes sur plusieurs rangees - n'a pas ete tranchee par qui
en a l'autorite ; j'exclus explicitement la troisieme sortie possible, assumer le debordement a
375 px, parce que ce serait reculer sur `DEC-0007` point 5 sans aucune contrainte qui l'impose,
alors que deux sorties plus sures existent.

**Ce que je tranche, et ce que je laisse explicitement a qui de droit :**

- Je tranche que le sprint 7 ne force aucune des deux sorties pour tenir un calendrier. Aucune
  story de ce sprint ne presuppose que "Le bon verbe" aura trois propositions, ni qu'il aura une
  mise en page a deux rangees.
- Je tranche que "Le bon verbe" ne rejoint pas le catalogue tant que cette question n'est pas
  fermee, meme si sa correction textuelle obtient un nouveau Done pedagogique entre-temps :
  corriger le motif de refus de `DEC-0014` ne suffisait deja pas a lever le retrait pour ce motif
  seul, et la question de rendu s'ajoute maintenant comme une seconde condition, independante de la
  premiere.
- Je laisse a `nadia-prof` la question de savoir si reduire "Le bon verbe" a trois propositions
  (retirer un distracteur) preserve ce que cette competence doit tester : c'est un changement de
  contenu pedagogique, pas une question de forme, et je n'ai pas autorite pour la trancher seule.
  Je la nomme comme premiere sortie candidate parce qu'elle ne coute aucun developpement
  supplementaire a la mecanique, pas parce que je la recommande sur le fond pedagogique.
- Je laisse a `ines-design` la question de savoir si un repli de la mise en page au-dela de trois
  colonnes (deux rangees, ou toute autre forme) reste lisible et comprehensible pour un enfant de
  CM1 : c'est une question de forme, elle propose, `nadia-prof` juge en jouant comme pour tout ce
  qu'elle propose.
- Je saisis `nadia-prof`, dans la meme saisine que l'arbitrage prealable sur les six acquis
  pedagogiques nommes plus haut, de cette septieme question : le nombre de propositions de "Le bon
  verbe" est-il un choix pedagogique intangible, ou une variable qu'elle peut arbitrer a la baisse
  sans denaturer la competence testee ("Conjuguer les verbes frequents au present, futur et
  imparfait de l'indicatif") ?

### Impact de cet amendement

- `docs/backlog/backlog.md`, section "Backlog - sprints suivants" : la note "Position sur l'entree
  au sprint 7" de la story des cinq indices fautifs est corrigee - la correction textuelle peut
  toujours entrer au sprint 7, la reintegration au catalogue ne le peut pas tant que cette question
  n'est pas fermee.
- Aucune story de S7-1 a S7-4 n'est modifiee sur le fond : aucune ne dependait d'une reponse sur
  "Le bon verbe".
- `squad/decisions/produit/DEC-index.md` : note d'amendement ajoutee (pas une nouvelle reference).

## Second amendement du 2026-08-10 (arbitrage prealable de nadia-prof) - la reduction a trois est
acquise sur le fond, la condition de remise au catalogue reste ouverte

`nadia-prof` a rendu l'arbitrage prealable saisi ci-dessus et par `ADR-0026` (branche `s07-nadia`,
commit `04c6b53`, quatre fiches de `squad/conformite/`, non fusionnee). Elle repond a la septieme
question que je posais dans l'amendement precedent, et sa reponse ferme la premiere des deux
sorties que j'y nommais.

**Ce qu'elle accepte, et sur quel motif.** Elle accepte de ramener "Le bon verbe" a trois
propositions par item, et elle fixe nommement les douze triplets
(`squad/conformite/FICHE-bandit-manchot-conjugaison-present-cm1.md`, section "Arbitrage prealable
au developpement"). Elle l'accepte sur son propre motif, pas sur le mien : sa mesure du 2026-08-10
montrait deja que la quatrieme proposition est sous le pli a 360 x 640 (2 px visibles sur 69), donc
inaccessible a un enfant sans qu'il sache qu'elle existait. Sa regle de reduction - garder la bonne
reponse et les deux distracteurs les plus proches a l'oreille, retirer le plus distant - augmente le
pouvoir discriminant de chaque item pendant que la chance de reussir au hasard passe de 25 % a
33 %. Elle precise que cette reduction ne vaut que pour ce paquet et ces douze items, pas comme
regle generale.

**Ce que sa mesure ferme.** Elle a verifie que cette reduction ne fait pas tenir le paquet en
colonnes : a trois colonnes et 360 px de large, neuf items sur douze debordent encore, et deux
d'entre eux (`cherche`/`cherches`, item 7) affichent deux propositions distinctes avec les memes
lettres visibles, ce qui rend l'item sans reponse identifiable. Sa phrase, reprise telle quelle :
"personne ne doit vendre cette reduction comme la reponse au probleme de largeur."

**Ma correction.** L'amendement precedent nommait deux sorties pour fermer la condition de remise
au catalogue et en excluait une troisieme. La reduction a trois propositions est desormais **acquise
sur le fond pedagogique et insuffisante sur la largeur** : elle vaut par elle-meme, parce qu'elle
ferme le constat du pli deja etabli par `nadia-prof` le 2026-08-10, pas parce qu'elle repondrait au
probleme de colonnes. Elle ne ferme donc plus, seule, la condition de remise au catalogue posee dans
l'amendement precedent - cette condition **reste ouverte**. Les sorties qui peuvent encore la
fermer sont desormais au nombre de deux, non trois : le repli de la mise en page au-dela de trois
colonnes (proposition d'`ines-design`, jugee par `nadia-prof` en jouant), ou le report du jeu hors
du sprint 7 (decision de perimetre, la mienne, si elle s'impose). Le debordement assume a 375 px
reste exclu, pour le motif deja ecrit : ce serait reculer sur `DEC-0007` point 5 sans aucune
contrainte qui l'impose, alors que des sorties plus sures existent.

Je ne tranche pas entre ces deux sorties restantes ici : le repli de mise en page appartient a
`ines-design` sous le jugement de `nadia-prof`, et rien ne m'oblige a decider aujourd'hui entre lui
et un report. Si un report s'impose, je l'ecrirai comme une decision de perimetre distincte, avec la
motivation qui va avec.

**Deux autres faits de cet arbitrage, verifies contre S7-1 a S7-4 sans y trouver rien a corriger :**

- `nadia-prof` refuse la metaphore de machine a sous telle quelle et l'accepte seulement diminuee de
  tout ce qui en fait un jeu d'argent, sous quatre conditions executables
  (`squad/conformite/FICHE-bandit-manchot-additions-cp.md`, section 1) : aucun vocabulaire ni
  imagerie de jeu d'argent dans ce que l'enfant lit, voit ou entend ; aucun signal de gain a l'arret
  des colonnes ; le seul retour de resultat reste la coche ou la croix existante ; aucun compteur
  autre que le score existant. J'ai relu S7-1 a S7-4 : aucun de leurs criteres ne nomme ni ne
  presuppose un mot, une image, un son de jeu d'argent, ni un signal de gain a l'arret. Aucune des
  quatre stories n'est corrigee par ce constat.
- Elle refuse une colonne vide pour atteindre un compte fixe de trois, et confirme que deux colonnes
  gardent leur sens propre pour un contenu binaire comme les homophones
  (`squad/conformite/FICHE-bandit-manchot-homophones-cm1.md` : deux colonnes validees, jamais de
  troisieme colonne vide). Cela conforte l'arbitrage 3 de cette decision (S7-2) tel qu'il est deja
  ecrit, sans le modifier.

### Impact de ce second amendement

- `docs/backlog/backlog.md`, section "Backlog - sprints suivants" : la note de la story des cinq
  indices fautifs est corrigee une seconde fois - elle ne nomme plus la reduction a trois comme
  premiere sortie candidate pour la largeur, puisque cette sortie est desormais fermee sur ce point
  precis par la mesure de `nadia-prof`.
- Aucune story de S7-1 a S7-4 n'est modifiee : verifiees contre les quatre conditions de la
  metaphore, aucune n'y contrevient.
- `squad/decisions/produit/DEC-index.md` : note d'amendement mise a jour.

## Troisieme amendement du 2026-08-10 (ADR-0027 de theo-archi) - le rendu en clair de la reprise
silencieuse reste en l'etat pour ce sprint

**Le defaut et sa fermeture technique.** `nadia-prof` a trouve que `accueil.html` rend le fragment
de manche en clair, sans htmx et sans aucun geste, sur la branche de reprise silencieuse de S1-4 :
un enfant qui recharge sa page verrait les colonnes tourner sans avoir rien touche, ce qu'elle
interdit nommement. `theo-archi` l'a ferme dans `ADR-0027` : le serveur declare desormais, sur
chaque ecran de jeu qu'il rend, comment il est arrive (`data-arrivee="par-un-geste"` ou
`"sans-geste"`), aucune animation ne peut s'accrocher au second, et la clause qui le garde a mordu
sur trois mutations reelles de la production. Je n'ai rien a decider pour que cette fermeture
tienne : elle tient deja.

**Ce qu'il me route.** Il note que si je retirais le rendu en clair de la manche sur la reprise, le
defaut disparaitrait a la racine plutot que d'etre neutralise par un marqueur, et que sa clause 5
deviendrait alors rouge en le disant - signe attendu, pas un echec. Trois lectures se defendent :
ne rien changer (le marqueur suffit, la redondance des deux protections est un cout accepte) ;
retirer le rendu en clair et le remplacer par un chemin htmx qui garde la reprise silencieuse au
sens de S1-4 (a verifier techniquement avec `theo-archi` avant de l'ecrire en critere) ; ou le
retirer sans remplacement, ce qui change ce que voit l'enfant qui recharge, defait le critere de
S1-4, et exige l'arbitrage prealable de `nadia-prof` (`squad/README.md` section 10).

**Mon arbitrage : je ne change rien ce sprint.** Le marqueur de `theo-archi` est un garde-fou
verifie, avec sa preuve de morsure sur trois mutations reelles ; le defaut que `nadia-prof` a
signale est deja ferme par lui, pas seulement masque. Retirer le rendu en clair a la racine serait
un geste de reduction de dette pour sa propre elegance, pas une reponse a un defaut ouvert - et ce
sprint porte deja deux fronts d'arbitrage pedagogique en cours (les indices de "Le bon verbe" et le
nombre de ses colonnes). Je ne vois pas de necessite qui justifie d'en ouvrir un troisieme
aujourd'hui.

**Ce que je ne tranche pas, et que je laisse a qui de droit.** Je ne demande pas a `theo-archi` de
verifier la faisabilite d'un chemin htmx de remplacement (deuxieme lecture) : rien ne presse cette
verification tant que le premier chemin reste un choix defendable. Je ne saisis pas `nadia-prof`
d'un arbitrage prealable sur le retrait sans remplacement (troisieme lecture) : ce serait defaire un
acquis accepte de S1-4 sans y etre contraint, au moment meme ou elle est deja saisie de la question
des colonnes de "Le bon verbe". Si `theo-archi` ou `sofia-sm` estiment un jour que la redondance des
deux protections vaut la peine d'etre levee, la question peut revenir au planning d'un sprint
suivant, avec sa verification technique et son arbitrage pedagogique dans l'ordre que
`squad/README.md` section 10 impose.

**Pour memoire.** `theo-archi` inscrit la cause de ce defaut comme la troisieme occurrence d'une
meme famille de faute de sa main dans ce chantier (une propriete structurelle prise pour la
propriete qui compte, `ADR-0022` et la limite 5 d'`ADR-0017` l'avaient deja actee deux fois) : ce
chantier a produit trois `ADR` sur cette seule mecanique, dont deux corrigent le premier. Je ne
juge pas ce fait, je le nomme pour qui relira cette decision.

**Etat non modifie par cet amendement.** La contradiction sur les quatre colonnes de "Le bon verbe"
reste ouverte (second amendement ci-dessus) : `ines-design` est saisie de la seule sortie qui reste
a instruire, le repli de mise en page, avec consigne de dire si aucun repli ne respecte les
planchers. Je n'anticipe pas sa mesure ; si sa reponse est negative, le report du jeu hors du
sprint 7 deviendra la seule sortie restante, et j'en deciderai alors comme d'une decision de
perimetre distincte.

### Impact de ce troisieme amendement

- Aucune story de S7-1 a S7-4 n'est modifiee, aucune story nouvelle n'est ouverte : le defaut route
  par `theo-archi` est ferme techniquement, et je ne prescris ni verification technique
  supplementaire ni arbitrage pedagogique supplementaire ce sprint.
- `docs/backlog/backlog.md` : inchange par cet amendement.
- `squad/decisions/produit/DEC-index.md` : note d'amendement ajoutee.

## Quatrieme amendement du 2026-08-10 (nadia-prof eteint sa propre reduction, ines-design et
nadia-prof confirment le repli) - la condition de remise au catalogue n'a plus qu'une sortie
candidate, pas encore fermee

**`nadia-prof` eteint sa propre acceptation de reduire "Le bon verbe" a trois propositions**
(`squad/conformite/FICHE-bandit-manchot-conjugaison-present-cm1.md`, second arbitrage du
2026-08-10). Les quatre propositions restent, ses douze triplets ne sont plus applicables - ils
demeurent ecrits dans sa fiche comme trace d'une decision eteinte, elle n'efface jamais un verdict,
mais aucun developpeur ne les execute.

**Son motif n'etait pas celui que j'avais imagine, et il est plus fort qu'une simple insuffisance de
largeur : son motif d'origine visait une cause fausse.** Elle avait ecrit que le defaut du pli etait
lie au passage a quatre propositions ; elle a depuis mesure les vingt-quatre items de "Le bon petit
mot", paquet a **deux** propositions et jeu du catalogue, et y trouve trois items sur vingt-quatre en
defaut avec l'indice affiche, zero sans. La cause est la longueur de l'enonce a 2 rem, pas le nombre
de propositions. Elle refuse explicitement d'inventer un motif de remplacement - elle avait
elle-meme ecrit, le matin du meme jour, que quatre propositions homophones servent mieux cette
competence que trois - ce qui serait fabriquer une raison apres coup pour sauver une decision.

**`ines-design` a par ailleurs trouve le repli, que `nadia-prof` a remesure et confirme** : deux
colonnes par rangee a partir de la quatrieme proposition seulement, 160 px par colonne a 360 px,
167-168 px a 375 px, ecart de 16 px tenu sur les deux axes, aucune troncature, aucun plancher
abaisse - sous les conditions d'implementation qui restent celles d'`ines-design` (contraste
suffisant pendant l'animation, voile confine dans la boite).

**Ce que devient ma condition.** Je l'avais posee ainsi : "Le bon verbe" ne rejoint pas le catalogue
tant que l'une des deux questions - reduire les propositions, ou replier la mise en page - n'a pas
ete tranchee par qui en a l'autorite. Les deux le sont desormais, mais pas au meme titre : la
reduction est **eteinte**, elle n'est plus une sortie du tout, pas meme une sortie insuffisante ; le
repli est **confirme sur le fond**, mais sur une maquette jetable, non sur le code livre -
`nadia-prof` le dit elle-meme : "si ce qui est livre differe, mes chiffres ne valent plus et cette
extinction est a reexaminer", et "le chantier n'est pas ecrit". Je ne ferme donc pas ma condition
aujourd'hui, et je ne la ferme pas plus largement que ce que ces deux verdicts autorisent : elle
n'a plus qu'**une seule sortie candidate**, et cette sortie reste a verifier sur l'increment reel.

**Ma condition, dans sa forme desormais definitive.** "Le bon verbe" rejoint le catalogue quand, et
seulement quand, les deux conditions suivantes sont remplies ensemble - elles restent independantes
l'une de l'autre, comme deja ecrit :

1. les cinq indices fautifs sont corriges et `nadia-prof` accorde un nouveau Done pedagogique
   (`DEC-0014`, inchangee, entiere) ;
2. l'increment livre rend les quatre propositions selon le repli en deux rangees tel que mesure par
   `ines-design` et confirme par `nadia-prof`, et `nadia-prof` reverifie cette mesure sur le rendu
   reel du code livre, pas sur la maquette jetable.

**Si l'increment livre ne reproduit pas la mesure confirmee**, la sortie candidate tombe et le
report du jeu hors du sprint redevient la seule option restante ; je le trancherais alors comme une
decision de perimetre distincte, le moment venu. Je n'anticipe pas cet echec.

**Pour memoire, sans y toucher** : aucun developpeur n'a commence de travail sur le sprint 7
(`round-two` est toujours a `28a419f`), donc il n'y a rien a defaire du cote du retrait de
distracteurs que la reduction eteinte aurait pu declencher.

### Impact de ce quatrieme amendement

- `docs/backlog/backlog.md`, section "Backlog - sprints suivants" : la note de la story des cinq
  indices fautifs est corrigee une troisieme fois - elle ne presente plus la reduction a trois comme
  une sortie, elle nomme la sortie unique restante (le repli, confirme mais non livre) et la
  condition en deux points ci-dessus.
- Aucune story de S7-1 a S7-4 n'est modifiee.
- `squad/decisions/produit/DEC-index.md` : note d'amendement ajoutee.
- Un trou de decision produit distinct, signale par `nadia-prof` a cette occasion (ce qui doit
  rester au-dessus du pli et sur quel viewport de reference), est traite separement dans
  `DEC-0016-hauteur-de-viewport-de-reference-et-contenu-au-dessus-du-pli.md`, parce qu'il depasse ce
  seul chantier et ce seul jeu.
