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
