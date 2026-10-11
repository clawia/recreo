# Arbitrage prealable - accueil par matiere (V1 deux etages, V2 cartes groupees, bascule en session)

- **Auteure** : `nadia-prof`. **Date** : 2026-10-11 17:42 (UTC+02:00), lue par `date`.
- **Chantier** : hors sprint, ouvert par `DEC-0117` (statut `Proposee`). Story `HS-ACCUEIL-LIBELLES` ;
  conditions sur `HS-ACCUEIL-V1`, `HS-ACCUEIL-V2`, `HS-ACCUEIL-BASCULE`.
- **Arbre** : worktree `nadia-accueil`, base `aa99f18d` (tip de `round-two`), `bash outils/verifier-lignee.sh`
  sort 0.
- **Ce que ce document n'est pas** : un Done pedagogique. Aucun code n'existe. Le Done sera prononce en
  jouant le jar, sur `FICHE-accueil-par-matiere.md`, et pourra etre refuse pour ce que seul le rendu montre,
  conditions tenues ou non.

## 0. Ce que j'ai lu, ce que j'ai joue, et ce que je declare degrade

**Lu** : `DEC-0117`, la section "Chantier hors sprint : accueil par matiere" de `docs/backlog/backlog.md`,
l'avis de `theo-archi` (`squad/sprints/hors-sprint-accueil-vues/avis-faisabilite.md`), `ADR-0105`, la
proposition d'`ines-design` (`squad/conformite/releves-accueil/proposition-ines-accueil-vues.md`),
`FICHE-barre-entree-niveau.md` (arbitrage du 2026-08-18 section 4 a 7, arbitrage du 2026-08-17 section 6
et 9, table de correspondance), `DEC-0053`, `accueil.html` et `PartieController` (routes `/`, `/retour`,
`/niveaux/{niveau}`).

**Regarde** : les 17 captures d'`ines-design`. **Rendu moi-meme**, pour verifier que ses captures montrent
bien ses maquettes : `maquette-v1-etage1-CP` a 390, `maquette-v1-etage2-CM1-langues-vivantes`,
`maquette-v2-CP-gris` et `maquette-v1-etage1-CE1` a 744, toutes a 551 de haut, par
`bash outils/mesurer-rendu.sh <largeur> "file:///<racine>/squad/conformite/releves-accueil/<maquette>.html" <png> 551`,
un Chrome designe par la variable `CHROME`. Les quatre images concordent avec les siennes (a 390 : 4 tuiles
entieres sur 8, comme son tableau 4.2). Mes images restent hors du depot.

**Degrade, et je le dis** : ce sont des maquettes statiques, pas le jar. Rien n'a ete presse, aucune route
n'existe, aucun parcours clavier n'a ete fait. Tout ce que je tranche ici porte sur des mots, des images et
des comportements decrits ; le comportement reel se juge a l'audit.

**Les decisions de l'utilisatrice** que je ne rouvre pas sont celles que `camille-po` consigne dans
`DEC-0117` section 2 : V2 par defaut, un bouton de l'accueil bascule, le choix vit en session HTTP,
libelles et pictogrammes arbitres par moi avant tout code.

## 1. Un fait du produit actuel qui gouverne trois reponses

`/retour` (le bouton "Les jeux", sur une manche comme sur la fin de partie) rend `accueil :: zoneAccueil`
avec `partie.jeuxDisponibles()` et declare l'adresse `/` (`PartieController.retour`, constante
`ADRESSE_CATALOGUE_COMPLET`). **Aujourd'hui, apres une partie lancee depuis `/niveaux/CP`, "Les jeux"
ramene a la liste des 147 jeux des trois niveaux, sans niveau.** C'est ma condition S15-5 (le filtre ne
survit pas a une sortie de partie), tenue.

Consequence : la page `/` n'est pas seulement le premier chargement. **C'est l'ecran ou l'enfant revient
apres chaque partie.** `DEC-0117` laisse `/` inchange tant que `Q-U1` est sans reponse ; tant que ce sera
le cas, le chantier ne touchera ni la premiere page, ni la page de retour, c'est-a-dire les deux ecrans ou
la plainte de l'utilisatrice ("on perd les enfants au scroll") se produit le plus souvent.

**Contradiction a lever par `camille-po`** : le critere "Tenu en session" d'`HS-ACCUEIL-BASCULE` exige
qu'apres "Les jeux" depuis un jeu la session revienne "en V1". "Les jeux" mene a `/`. Ce critere n'est
satisfaisable que si `/` est modifie, ce que `DEC-0117` section 5 exclut avant `Q-U1`.

## 2. Verdicts sur les points N1 a N8 d'`ines-design`

### N1 - libelles courts : ACCEPTES

Le mot que l'enfant emploie en classe, quand il en existe un, et le nom du programme sinon.

| Matiere (graphie du depot) | Libelle arbitre | Motif |
|---|---|---|
| Français | **Français** | inchange |
| Mathématiques | **Maths** | le mot des enfants des le CP ; le nom complet passe sur deux lignes |
| Langues vivantes (anglais) | **Anglais** | seule langue servie ; une seconde langue serait une ligne de donnee neuve, donc un arbitrage neuf |
| Sciences et technologie | **Sciences** | CM1 seulement ; le mot de la classe |
| Questionner le monde | **Questionner le monde** | garde : c'est le nom ecrit a l'emploi du temps de CP. "questionner" contient `-tion`, difficile a dechiffrer en CP ; le pictogramme porte la reconnaissance. A rejuger en jouant, sans condition |
| Histoire-géographie | **Histoire-géographie** | CM1, lisible a ce niveau |
| Enseignement moral et civique | **Vivre ensemble** | les 22 titres servis (regles de l'ecole, politesse, moquerie, droits, symboles de la Republique ; lus dans `maquette-v1-etage2-tous-emc.html`) relevent tous du vivre ensemble ; "Enseignement moral et civique" fait trois lignes et aucun mot n'est d'un enfant de CP |
| Arts plastiques | **Arts plastiques** | le nom employe en classe |
| Éducation musicale | **Musique** | le mot des enfants |
| Histoire des arts | **Histoire des arts** | CM1 |
| Éducation physique et sportive | **Sport** | le mot des enfants ; les cinq titres servis (regles de securite, jeu collectif, corps et effort, arbitre ; `maquette-v1-etage2-tous-eps.html`) sont lisibles sous ce mot |

L'orthographe et l'accentuation de ces libelles sont controlees par le `relecteur-pedagogique`
(`DEC-0002`, amendement du 2026-08-04), pas par moi.

### N2 - pictogrammes : DIX ACCEPTES, UN REFUSE

Mon critere, ecrit le 2026-08-17 (`FICHE-barre-entree-niveau.md`, arbitrage du 2026-08-17, section 9) :
**un pictogramme n'est acceptable que s'il retire un effort de lecture, jamais s'il en ajoute un.**

| Libelle | Pictogramme (`pictos/`) | Verdict |
|---|---|---|
| Français | livre ouvert | ACCEPTE |
| Maths | regle graduee | **REFUSE** |
| Anglais | bulle "Hi" | ACCEPTE : "Hi" est le premier mot d'anglais appris en CP, la bulle dit "parler" ; pas de drapeau, qui designe un pays et non une langue |
| Sciences | fiole | ACCEPTE |
| Questionner le monde | loupe sur une feuille | ACCEPTE : la loupe est l'outil d'observation du cycle 2 |
| Histoire-géographie | globe | ACCEPTE |
| Vivre ensemble | deux personnes | ACCEPTE |
| Arts plastiques | palette | ACCEPTE |
| Musique | deux croches | ACCEPTE |
| Histoire des arts | tableau dans son cadre | ACCEPTE |
| Sport | ballon | ACCEPTE |

**Motif du refus de la regle, actionnable.** Une regle dit "mesurer". Sur les 13 jeux de mathematiques du
CP servis aujourd'hui, un seul porte sur les grandeurs ("Compare les grandeurs") ; huit portent sur les
nombres et le calcul (additions, soustractions, complements a 10, comparer et ranger les nombres, nombres
jusqu'a 100, petits problemes). Methode : titres lus dans `maquette-v1-etage2-CP-mathematiques.html`. Le
pictogramme montre ce que l'enfant fait le plus souvent : **des signes d'operation, au moins `+` et `=`**
(le `-` en plus si le dessin reste lisible a 48 px). Ce sont des signes que l'enfant de CP lit des le
premier trimestre, valables aussi en CM1. Pas de chiffre dans le dessin. Le nouveau fichier revient devant
moi avant le code de la vue (condition `APM-2`).

### N3 - libelles du bouton et icone : ACCEPTES

- **"Tout voir"** (de l'etage 1 de V1 vers V2) : deux mots, dechiffrables en CP, et vrais : V2 montre tous
  les jeux du niveau.
- **"Par matière"** (de V2 vers l'etage 1, et retour de l'etage 2 vers l'etage 1) : je l'accepte, et je dis
  sa faiblesse. "matiere" n'est pas un mot d'enfant de six ans, et V2 est deja rangee par matiere. Je
  l'accepte quand meme pour deux raisons. (1) **Le meme mot mene toujours au meme ecran**, les tuiles,
  depuis V2 comme depuis l'etage 2 : un code, un sens. (2) **Il ne ressemble ni a la sortie ni a un jeu** :
  aucun titre de jeu ne commence par "Par", alors qu'"Les matières" aurait partage son premier mot avec
  "Les jeux" et la forme des titres ("Les figures", "Les syllabes"). Une erreur sur ce bouton est
  reversible et ne coute rien : aucune partie n'est perdue, un appui sur "Tout voir" la defait.
- **L'icone dans le bouton** : ACCEPTEE. Elle dessine l'ecran de destination (quatre carres pour les
  tuiles, trois lignes pour la liste), donc elle retire un effort de lecture. `BARRE-4` reste entiere : elle
  vise les puces de niveau, qui ne recoivent rien.
- **O2 (deux segments, coche sur la vue courante) : REFUSEE.** A la vue par defaut, la coche montrerait un
  etat que l'enfant n'a pas produit, ce que ma condition S15-5 interdit, et elle met un symbole dans un
  controle, ce que j'ai refuse le 2026-08-18 (section 7, point 6).

### N4 - V2 par defaut : ACCEPTEE

C'est une decision de l'utilisatrice ; ma part est sa lecture de S15-5 (section 3, `Q-N1`). Avec O1, aucun
controle ne marque la vue courante : l'ecran est la vue. L'enfant ne rencontre donc aucun etat actif qu'il
n'a pas produit.

### N5 - ordre des matieres : TRANCHE, et il differe de la proposition sur un point

**Français, Maths, Questionner le monde, Sciences, Histoire-géographie, Vivre ensemble, Anglais, Arts
plastiques, Musique, Histoire des arts, Sport.**

Motif : lire, ecrire et compter d'abord ; puis comprendre le monde ; puis vivre ensemble ; puis la langue
etrangere, les arts et le corps. Un seul ordre pour tous les niveaux et les deux vues : une matiere absente
d'un niveau est sautee, l'ordre relatif des autres ne change jamais. Ecart avec `ines-design` : "Anglais"
passe du rang 3 au rang 7. Au rang 3, en CP, une matiere d'un seul jeu passait devant les 12 jeux de
Questionner le monde. L'ordre ne depend pas du nombre de jeux, qui change ; il depend de la matiere.

### N6 - teintes : DIX ACCEPTEES, UNE REFUSEE

- **La couleur est un doublon, jamais le signal** (`M11`, clause 1) : le mot et le pictogramme portent la
  matiere. Tenu par la proposition, et c'est la condition `APM-3`.
- **Les quatre teintes voisines d'une couleur de sens (juste, faux, indice, bloc adulte) : ACCEPTEES**, a
  une condition : ces couleurs de sens ne vivent que sur les ecrans de jeu, et la teinte de matiere ne
  doit jamais y entrer (`APM-5`). Elles ne partagent alors jamais un ecran.
- **L'emplacement 10 (`#d8dde4`, contour `#4a5a6b`, "Histoire des arts") : REFUSE.** C'est un gris parmi
  des couleurs : un enfant, et un adulte, lit une tuile grise comme "ne marche pas". Motif lu sur
  `capture-v1-etage1-CM1-744x551.png`. Il faut une teinte qui ne soit pas un gris (`APM-6`), rejugee en
  jouant.
- Le repli neutre (`teinte-neutre`) n'est pas necessaire ; je ne le refuse pas.

### N7 - page sans niveau : mon avis pour `Q-U1`, section 4

### N8 - le bandeau colle de 48 px : ACCEPTE

Il repond a la moitie de la plainte que V2 doit regler ("on n'a aucune idee de la matiere associee") : a
tout moment du defilement, le nom de la matiere est a l'ecran (`capture-v2-CP-defile-692-744x551.png`).
8,7 % de 551 px pour cela, je l'accepte.

**`DEC-0053` couvre-t-il le pli de l'accueil ? Pas a la lettre.** Son arbitrage 1 porte sur "un element du
jeu (indice, verdict, proposition)", et l'accueil n'est pas un jeu. **Je pose le meme regime par ma propre
decision** : le pli de l'accueil n'est pas un motif de `REFUSE`. C'est ce que j'ai deja ecrit pour
`R-BARRE-2` le 2026-08-17 : faire defiler une liste est un geste normal. Les seuils de cartes visibles
(10 en V2, `min(n, 6)` a l'etage 2) restent des criteres de produit de `camille-po`, mesures par
`yanis-qa` (`DEC-0117` section 6, point 3). **Je lui signale l'ecart** : la maquette donne 8 cartes
entieres a 744 en V2, pas 10 ; sa propre regle de renvoi s'applique, je ne la tranche pas.

Ce que je garde : un bandeau colle ne doit pas recouvrir la carte qui recoit le focus clavier. Non
gardable (avis de `theo-archi`, section 6) ; je le joue (section 6).

## 3. Les questions qui me sont posees

### `Q-N1` - le choix de vue en session contredit-il S15-5 ? Non, a deux conditions

S15-5 refuse qu'un enfant voie **un etat actif qu'il n'a pas produit**, pour un motif ecrit : un enfant
devant une liste deja filtree croit que ses jeux ont disparu. Le choix de vue ne cache rien : en V1,
l'etage 1 montre toutes les matieres du niveau et "Tout voir" est a l'ecran ; en V2, tout est la. Un
enfant qui herite de la vue d'un frere dans la meme session voit une presentation, pas une selection.

Ce qui ferait la contradiction, et je l'interdis :
1. **Que la session retienne autre chose que la vue.** La matiere de l'etage 2 et le niveau vivent dans
   l'adresse, jamais en session. Un etage 2 retenu serait un filtre qui survit : l'enfant suivant
   trouverait les seuls jeux de Sciences (`APM-8`).
2. **Qu'un controle marque la vue courante** (coche, plein) : c'est O2, refusee (`APM-7`).

### `Q-N2` - depuis un jeu en V1, "Les jeux" ramene a quel etage ? Jamais a l'etage 2

"Les jeux" mene au premier ecran de la vue retenue, jamais a l'etage 2 de la matiere jouee (`APM-9`).
Motifs : l'etage 2 est une matiere choisie, donc un filtre, et S15-5 dit qu'il ne survit pas a une sortie
de partie ; son adresse porte aussi le niveau, que S15-5 ne laisse pas survivre non plus ; et "Les jeux"
doit avoir une seule sorte de destination. Le cout, je le nomme : l'ecran de fin de partie n'a pas de
bouton pour rejouer (`fragments/retour.html` ne porte que "Les jeux"), donc rejouer le meme jeu ou en
prendre un autre de la meme matiere coute un appui sur la tuile puis un sur la carte.

**Quel niveau ?** Aucun aujourd'hui : "Les jeux" mene a `/` (section 1). La reponse complete depend donc
de `Q-U1`. Si `/` demande le niveau (mon avis, section 4), "Les jeux" ramene au choix du niveau, et le
niveau choisi s'ouvre dans la vue retenue en session. **Je n'accorde pas d'avance** que "Les jeux" ramene
au niveau du jeu joue : ce serait amender ma condition S15-5 sur la sortie de partie, et cela demande un
arbitrage ecrit, pas une consequence de route.

### Q3 de `theo-archi` - une tuile pendant une partie : meme confirmation que la puce ? Oui

Pendant une partie, l'accueil n'est pas rendu (`GET /` rend la reprise) ; une tuile n'est atteinte que par
l'historique du navigateur ou une adresse tapee. Tout geste qui quitterait une partie passe par **le meme
ecran de confirmation, avec les memes mots** que la puce de niveau : un seul ecran a apprendre. Apres
confirmation, l'enfant arrive a l'etage 2 demande, avec sa phrase de niveau. La route de bascule de vue
n'abandonne jamais une partie (F9 de `theo-archi`, que je reprends). Condition `APM-11`.

### Q4 - trois matieres n'ont qu'un jeu : que montre l'etage 2 ? Un etage 2 a une carte

Une tuile ouvre toujours l'etage 2, meme pour un seul jeu (`APM-10`). Une tuile qui lancerait parfois un
jeu et parfois un ecran aurait deux sens. Le cout est un appui ; en echange l'enfant lit le nom du jeu
avant de le lancer. Rendu verifie a 744 sur `maquette-v1-etage2-CM1-langues-vivantes.html` : un bandeau
"Anglais", une carte "Anglais", rien ne parait casse. A noter pour l'audit : en CM1, le seul jeu d'anglais
s'intitule "Anglais", comme son bandeau (en CP, "Le monde anglophone") ; ce n'est pas un motif.

### Q6 - le bouton s'adresse a l'enfant ou a l'adulte ? A l'enfant

L'adulte qui accompagne l'enfant de CP (`DEC-0046`, `DEC-0068`) ne vaut comme moyen de jeu que sur les
lignes ou la voix est la competence ; il ne dispense d'aucune exigence de lisibilite (`CLAUDE.md`, "L'enfant
de CP n'est jamais seul"). Le bouton est donc ecrit pour un enfant qui lit peu : deux mots courts, une icone
qui dessine la destination. C'est ce que N3 accepte.

### Q7 - forme du pictogramme, couleur par matiere ? Un fichier dessine, et une couleur en doublon

**Un fichier SVG, pas un caractere.** Un emoji est dessine par le systeme : l'enfant verrait une autre image
sur une autre tablette, et je ne peux pas juger une image que je ne vois pas. Les onze fichiers sont juges
une fois, ici (N2). **Une couleur par matiere : oui**, comme doublon (N6), ecrite dans la donnee de la
matiere et jamais dans une regle de feuille (clause 6 de `theo-archi`, section 5).

## 4. `Q-U1` - que montre `/` sans niveau ? Mon avis ; l'utilisatrice tranche

`/` est le premier ecran et l'ecran de retour apres chaque partie (section 1).

- **(c) `/` demande le niveau d'abord : mon avis.** C'est la seule option qui regle la plainte la ou elle se
  produit le plus : ni 147 jeux, ni jeux d'un autre niveau. Le cout : un appui sur la puce du niveau apres
  chaque partie. S15-5 reste intact : aucune puce pleine sur `/`.
- **(a) `/` garde la liste actuelle : acceptable**, c'est l'etat d'aujourd'hui. Il ne regle rien sur ces
  deux ecrans, et la vue retenue en session y serait invisible apres chaque partie.
- **(b) `/` groupe par matiere les trois niveaux : je la refuse.** Sous le bandeau "Maths", 39 jeux de trois
  niveaux, et aucun titre ne dit son niveau (`ADR-0078`). Le melange existe deja en (a), mais en (b) il
  prend l'apparence d'une selection : un enfant de CP lit "voici les jeux de maths" et lance un jeu de CM1
  sans moyen de le savoir.

## 5. Mes conditions bloquantes pour le lot, `APM-1` a `APM-11`

Elles decrivent ce que l'enfant voit ; les moyens sont a `theo-archi`. Chacune se verifie sans moi, sauf
mention.

- **`APM-1`. Les onze libelles sont ceux du tableau de N1**, ecrits dans la donnee de la matiere et nulle
  part ailleurs. Une matiere neuve sans libelle arbitre n'est pas servie avec un libelle de repli invente :
  elle revient devant moi.
- **`APM-2`. Le pictogramme de Maths est refait sur des signes d'operation** (N2) et revient devant moi
  avant le code de la vue. Les dix autres fichiers sont ceux de `pictos/` au commit `e568fc6e` ; tout
  changement d'un fichier revient devant moi.
- **`APM-3`. Le pictogramme n'est jamais seul et ne parle pas.** Chaque tuile et chaque bandeau portent
  leur libelle en texte ; l'image a un texte alternatif vide, le mot est le nom accessible. Aucun
  pictogramme ni icone sur une carte de jeu ni sur une puce de niveau (`BARRE-4` inchangee).
- **`APM-4`. L'ordre des matieres est celui de N5**, le meme dans les deux vues et a tous les niveaux,
  ecrit une seule fois dans la donnee.
- **`APM-5`. La teinte de matiere n'entre sur aucun ecran de jeu** : ni manche, ni revelation, ni fin de
  partie, ni confirmation, ni erreur.
- **`APM-6`. Aucune teinte de matiere n'est un gris.** L'emplacement 10 recoit une teinte ; je la rejuge en
  jouant.
- **`APM-7`. Le bouton de vue est O1** : un seul bouton dans la zone de l'accueil, "Par matière" (quatre
  carres) sur V2, "Tout voir" (trois lignes) sur l'etage 1, "Par matière" (chevron) sur l'etage 2. Aucune
  marque d'etat (coche, plein) ; jamais dans l'en-tete ; jamais sur un ecran de jeu ; jamais le mot "Les
  jeux" (`BARRE-5`).
- **`APM-8`. La session ne retient que la vue.** La matiere de l'etage 2 et le niveau ne vivent que dans
  l'adresse.
- **`APM-9`. "Les jeux" ne mene jamais a un etage 2.** Il mene au premier ecran de la vue retenue, sur ce
  que `/` montrera apres `Q-U1`.
- **`APM-10`. Une tuile ouvre toujours l'etage 2 de sa matiere**, meme d'un seul jeu ; elle ne lance jamais
  un jeu.
- **`APM-11`. Une tuile ou un etage 2 atteint pendant une partie rend la confirmation de la puce de
  niveau**, memes mots ; apres confirmation, l'etage 2 demande. La bascule de vue n'abandonne jamais une
  partie.

## 6. Ce qui doit devenir un garde-fou, et ce qui reste a mon audit

**A encoder** (le moyen est a `theo-archi`, le test au lot) :

| Condition | Propriete a garder |
|---|---|
| `APM-1`, `APM-4` | une ligne par matiere servie, libelle non vide, ordre lu dans la donnee (clauses 1 a 4 de `theo-archi`, section 5) |
| `APM-3` | chaque tuile et chaque bandeau ont un noeud texte non vide ; l'image de matiere a un `alt` vide ; aucune image dans `.demarrer` ni `.chip-niveau` |
| `APM-5` | aucun gabarit d'ecran de jeu ne porte la teinte de matiere |
| `APM-7` | le bouton de vue est absent de l'en-tete et des ecrans de jeu ; au plus un "Les jeux" visible sur chacun des ecrans neufs (V2, etage 1, etage 2) |
| `APM-8` | le porteur de session n'ecrit qu'un seul attribut d'affichage, la vue |
| `APM-9`, `APM-11` | routes rejouees avec et sans partie en cours (F10 de `theo-archi` les exerce deja) |
| `APM-10` | toute tuile pointe vers la route de l'etage 2, verifie sur une matiere a un seul jeu |
| `APM-6` | candidat : une borne de saturation sur les teintes. `theo-archi` dit si c'est gardable ; d'ici la, c'est moi |

**Je reste seule garde, en jouant, de ce qui suit.** Qu'on ne le croie pas couvert par la CI :

1. que les onze pictogrammes se reconnaissent a leur taille rendue, a 744 et a 390, et que le nouveau
   pictogramme de Maths se lise "maths" ;
2. que la teinte qui remplace le gris ne se lise pas "desactive" ;
3. qu'un enfant ne confonde pas "Par matière" ou "Tout voir" avec "Les jeux" (reserve 5 d'`ines-design`) ;
4. qu'un bandeau colle ne recouvre pas la carte qui recoit le focus clavier ;
5. que le premier ecran de V2 dise a l'enfant dans quelle matiere il est (reserve 1 d'`ines-design` : une
   seule matiere au premier ecran a 744) ;
6. que l'etage 2 d'un seul jeu ne paraisse pas casse ;
7. le parcours complet : choisir un niveau, basculer, jouer, "Les jeux", et retrouver la vue retenue.

## 7. Prix et routage

**Prix pour ma capacite** (bareme de s29 reconduit en s30, `PAR-10`) : l'audit du lot livre vaut **1,0**
(trois niveaux, deux vues, le parcours de retour) ; le rejugement du pictogramme de Maths et de la teinte
de l'emplacement 10, s'ils arrivent ensemble, vaut **0,25**. A deduire par `sofia-sm` du planning de s57.

| Destinataire | Ce que je route |
|---|---|
| `ines-design` | le pictogramme de Maths (`APM-2`) et la teinte de l'emplacement 10 (`APM-6`) ; l'ordre de N5 |
| `theo-archi` | le second ADR (donnee de matiere : libelle, pictogramme fichier, teinte, ordre) et les garde-fous de la section 6 |
| `camille-po` | la contradiction de la section 1 (critere "Tenu en session" contre `/` inchange) ; l'ecart de 8 cartes contre 10 (N8) ; `APM-1` a `APM-11` dans les criteres des stories |
| `sofia-sm` | mon avis sur `Q-U1` (section 4) a soumettre a l'utilisatrice avec ses options ; le prix ci-dessus |
| `lea-dev` | rien avant le pictogramme de Maths rejuge et le second ADR |

## Impact

- Ce fichier : cree.
- `squad/decisions/REGISTRE-identifiants.md` : `APM-1` a `APM-11` alloues, note datee, ligne d'etat courant.
- Aucun fichier sous `domain/`, `application/`, `adapters/`, `bootstrap/` ; ni `FICHE-accueil-par-matiere.md`,
  dont le bloc de validation attend le jeu.
