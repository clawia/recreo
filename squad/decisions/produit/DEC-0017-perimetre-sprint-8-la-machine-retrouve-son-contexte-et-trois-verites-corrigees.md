# DEC-0017 - Perimetre du sprint 8 : la machine retrouve son contexte, trois verites du depot corrigees, l'ouverture du futur et de l'imparfait de la conjugaison

- **Type** : portee
- **Date** : 2026-08-11
- **Auteur** : camille-po
- **Sprint** : s08
- **Competence programme visee** : mixte - voir chaque chantier ci-dessous. Le chantier 1 est
  transverse (habillage de la mecanique, aucun contenu pedagogique). Le chantier 4 porte
  francais, CM1 (cycle 3), domaine "Orthographe et grammaire" - "Conjuguer les verbes frequents au
  present, futur et imparfait de l'indicatif" (`squad/programme/francais/competences-cm1.md`).
- **Statut** : Acceptee

## Contexte

L'utilisatrice a joue l'increment du sprint 7 et fait deux constats.

**1. Les boutons de choix de jeu de l'accueil portent un decor de levier qui ne leur revient pas.**
Verifie dans le code avant d'ecrire cette decision :
`adapters/adapter-web/src/main/resources/static/css/recreo.css`, regle S7-4, pose
`.demarrer::before` et `.encore::before` - un disque blanc de 0,6 rem a 55 % d'opacite, en haut au
centre du bouton, un "reflet de capuchon de bouton-poussoir". Le commentaire qui accompagne cette
regle explique le choix : `.demarrer` et `.encore` sont "les deux boutons qui ouvrent deja une
manche". C'est faux pour `.demarrer` : `adapters/adapter-web/src/main/resources/templates/
accueil.html` l'utilise sur les boutons de **choix de jeu** de l'accueil (un bouton par jeu du
catalogue), qui ouvrent une liste de jeux, pas une manche. `.encore`, lui, sert bien le bouton qui
relance une manche depuis l'ecran de retour, et le motif d'origine s'applique a lui sans
correction. L'accueil n'est pas une machine, c'est une liste de jeux, et il ne doit pas en porter
le decor.

**2. "Il manque une machine dans le contexte (...) sinon on ne comprend pas."** L'utilisatrice juge
les colonnes belles, mais rien ne les encadre : aucun cabinet, aucun objet ne les entoure. La
metaphore de machine a sous n'est pas lisible sans ce contexte.

**Les deux constats sont le meme defaut** : le levier a ete livre sans son contexte de machine.

**Le degre est tranche par l'utilisatrice, il n'est pas rediscute ici** : une machine
reconnaissable, customisee avec des images pour enfants, sans les codes du jeu d'argent. C'est
compatible avec le veto pedagogique que `nadia-prof` a pose le 2026-08-10
(`squad/conformite/FICHE-bandit-manchot-additions-cp.md`, section "Arbitrage prealable au
developpement - chantier machine a sous", section 1) : il porte sur l'absence de vocabulaire,
d'imagerie et de signal de jeu d'argent, et sur le refus nomme d'un geste de traction (une
manette) - il ne porte pas sur l'idee de machine elle-meme, que `nadia-prof` n'a jamais refusee.
Ce veto reste entier et gouverne les quatre stories du chantier 1 ci-dessous.

**Hypothese posee, non tranchee ici** : que le disque decoratif corrige au chantier 1 quitte
l'accueil et se retrouve, sous une forme ou une autre, sur l'ecran de jeu, ou le cabinet
l'expliquera. Ce n'est pas une decision : `nadia-prof` confirmera ou non en jouant l'increment
livre, comme pour tout ce que ce chantier propose.

Un recensement des fichiers de pilotage du depot montre par ailleurs trois endroits ou l'etat
ecrit ne correspond plus a l'etat reel (chantier 2), et la fiche de conformite de la conjugaison au
present pose une barre chiffree et close pour l'ouverture du futur et de l'imparfait (chantier 4).

## Decision

Le sprint 8 porte quatre chantiers, numerotes tels qu'ils sont route ci-dessous :

### Chantier 1 - une machine reconnaissable autour des rouleaux

Quatre stories, `docs/backlog/backlog.md` section "Sprint 8" :

- **S8-1** - un cabinet visuel encadre les colonnes sur l'ecran de manche, et le decor de levier
  quitte les boutons de choix de jeu de l'accueil ;
- **S8-2** - la fin du mouvement des colonnes se termine sans discontinuite visible (reserve de
  `nadia-prof`, `FICHE-bandit-manchot-additions-cp.md`, section 11) ;
- **S8-3** - l'explication d'une variation graphemique se distingue visuellement de la consigne
  (reserve 6 de `nadia-prof`, `FICHE-bandit-manchot-le-bon-son-cp.md`, 2026-08-10).

Les criteres verifiables pour "une machine sans les codes du jeu d'argent" sont ecrits story par
story dans S8-1 ; ils sont resumes en synthese plus bas dans cette decision.

### Chantier 2 - trois verites que le depot n'ecrivait plus

Trouvees au recensement d'ouverture de sprint, chacune sur un fichier de pilotage distinct :

1. **`catalogue.yaml` est remis a jour** : "Le bon verbe" (`bandit-manchot-conjugaison-present-cm1`)
   rejoint le catalogue jouable. Les deux conditions posees par `DEC-0015` (quatrieme amendement)
   sont remplies **ensemble**, verifie contre les fiches avant d'ecrire cette decision :
   - les cinq indices fautifs sont corriges et `nadia-prof` a accorde un nouveau Done pedagogique
     (**VALIDE SOUS RESERVE**, commit `10dd74d`,
     `squad/conformite/FICHE-bandit-manchot-conjugaison-present-cm1.md`, verdict "second audit,
     increment livre en s07") ;
   - l'increment livre rend les quatre propositions selon le repli en deux rangees mesure par
     `ines-design` et confirme par `nadia-prof`, et elle a reverifie cette mesure sur le rendu reel
     du code livre (meme fiche, section 3 : 160,0 px par colonne sur les douze items, aucune
     troncature, la quatrieme proposition entierement lisible au-dessus du pli sur les douze
     items), pas sur la maquette jetable qui avait servi a l'amendement precedent.

   **`catalogue.yaml` est du code d'`adapter-content` : cette decision tranche la remise, elle n'en
   execute pas la modification.** L'edition du fichier revient a un developpeur ; `sofia-sm`
   l'affecte au planning comme pour toute story de code.
2. **`squad/programme/MATRICE-couverture.md` est corrigee** : les deux endroits ou la matrice
   portait encore le REFUSE du 2026-08-10 sur la conjugaison sans la levee prononcee au sprint 7
   (le paragraphe narratif de la section francais CM1 et la synthese de cloture du sprint 6) sont
   mis a jour dans le meme geste que cette decision. La ligne elle-meme **ne change pas de
   statut** : elle reste `en cours`, `nadia-prof` n'ayant pas leve la barre chiffree qu'elle a
   ecrite pour `couvert-valide` (voir chantier 4).
3. **`squad/validation/FILE-ATTENTE.md` est corrigee** : le fichier comptait encore un increment
   en attente (`bandit-manchot-conjugaison-present-cm1`) et prescrivait que le sprint suivant
   devait d'abord traiter une reprise deja faite au sprint 7. Corrige dans le meme geste : la file
   compte desormais zero increment en attente, et l'historique trace la levee du refus a sa date
   reelle (2026-08-10, second audit de s07).

### Chantier 4 - etendre "cm1-conjugaison-present" au futur et a l'imparfait

Une story, `docs/backlog/backlog.md` section "Sprint 8", **S8-4**, qui reprend au mot pres les six
points que `nadia-prof` a ecrits comme barre close pour `couvert-valide`
(`squad/conformite/FICHE-bandit-manchot-conjugaison-present-cm1.md`, refus du 2026-08-10,
section 5) : "Rien d'autre. Ce qui n'est pas dans cette liste ne bloquera pas `couvert-valide` et
ne pourra pas m'etre oppose comme l'ayant bloque."

Deux reserves neuves du 2026-08-10, qui touchent le meme paquet, entrent dans les criteres de cette
story : `mangeons` tient a 2,0 px de chaque cote de sa boite de 160 px, et tout mot plus large que
156 px en sortira - or `finissaient` et `apprendraient`, deux formes que l'ouverture du futur et de
l'imparfait rend probables, sont plus larges ; et cinq items sur douze mettent la seconde rangee de
propositions sous le pli a 360 x 640 quand l'indice s'affiche (`DEC-0016`, pas un motif de refus,
mais une contrainte a ne pas aggraver).

### Acquis pedagogiques touches par le chantier 1, signales pour arbitrage prealable de nadia-prof

`squad/README.md` section 10 exige un arbitrage prealable avant developpement pour toute story qui
modifierait un ecran deja accorde. Je signale, sans le trancher :

- les quatre conditions non negociables de la metaphore (`FICHE-bandit-manchot-additions-cp.md`,
  section 1) : aucun vocabulaire ni imagerie de jeu d'argent, aucun signal de gain a l'arret,
  un seul canal de verdict, aucun compteur autre que le score - un cabinet illustre est
  precisement l'endroit ou une image pourrait les enfreindre sans qu'on l'ait cherche ;
- l'acquis 6 de `nadia-prof` (aucun decor de machine, aucune animation sur l'ecran de fin de
  partie) : un cabinet ajoute au chantier ne doit pas s'y propager ;
- les quatre planchers non negociables : cible tactile 2,75 rem, lisibilite 1,25 rem, chiffre de
  proposition 2 rem, `gap` 1 rem sur les deux axes - tenus a l'egalite exacte, sans marge a
  recuperer ;
- la hierarchie de saillance action/sortie d'`ADR-0017` ;
- les deux reserves nommees du chantier (fin du mouvement, S8-2 ; distinction visuelle de
  l'explication, S8-3).

Je nomme cette liste pour que `sofia-sm` saisisse `nadia-prof` sur une liste ecrite, comme pour le
chantier machine a sous de s07.

## Ce qui reste dehors, explicitement

- **Le defaut de pli de "Le bon petit mot"** (cinq items sur vingt-quatre, l'indice pousse les deux
  boutons de reponse sous le pli au moment ou l'enfant recoit son aide,
  `squad/conformite/FICHE-bandit-manchot-homophones-cm1.md`, constat du 2026-08-10 ; regle
  transverse deja fixee par `DEC-0016`) reste hors perimetre de ce sprint. `nadia-prof` maintient
  le Done pedagogique de ce jeu et refuse de deplacer ses propres poteaux apres coup ; la sortie
  demande un arbitrage entre raccourcir les enonces (jugement pedagogique, `nadia-prof`) et traiter
  la hauteur par la mise en page (`ines-design`, `theo-archi`), et cet arbitrage n'est pas ouvert
  ici faute de capacite. Ce n'est pas oublie : `DEC-0016` l'a deja nomme, et cette decision ne fait
  que le rappeler pour qu'il ne soit pas cru clos.
- **Les referentiels des autres matieres** (sciences, histoire-geographie, et tout domaine hors
  mathematiques et francais) restent dehors : aucun referentiel n'existe encore sous
  `squad/programme/` pour eux, et rien dans ce sprint ne les ouvre.

## Justification pedagogique

Transverse pour les chantiers 1 et 2 : ils ne jugent aucun contenu et ne font bouger aucune ligne
de `squad/programme/MATRICE-couverture.md`. Le degre de la metaphore de machine a ete tranche par
l'utilisatrice, pas par moi ; ce que je tranche est un perimetre de stories et la maniere de les
rendre verifiables, sous le veto deja pose par `nadia-prof`, qui reste seule a juger si ce qui sera
livre le respecte. Le chantier 4 ne prejuge d'aucun contenu neuf : il reprend au mot pres une barre
deja ecrite par `nadia-prof` elle-meme, et le contenu qui en resultera repassera devant elle comme
tout contenu neuf.

## Impact

- `docs/backlog/backlog.md`, nouvelle section "Sprint 8" : quatre stories (S8-1 a S8-4).
- `squad/programme/MATRICE-couverture.md` : corrigee dans le meme geste que cette decision (voir
  chantier 2), sans changement de statut sur la ligne conjugaison.
- `squad/validation/FILE-ATTENTE.md` : corrigee dans le meme geste que cette decision (voir
  chantier 2).
- `adapters/adapter-content/src/main/resources/catalogue.yaml` : remise de "Le bon verbe" tranchee
  par cette decision, execution routee a un developpeur via `sofia-sm`.
- Acquis pedagogiques a arbitrer par `nadia-prof` avant developpement du chantier 1, listes
  ci-dessus : a router par `sofia-sm`.
- `squad/decisions/produit/DEC-index.md` : ligne DEC-0017 ajoutee.

## Ce que cette decision ne fait pas

- Elle ne tranche aucune question technique : la forme du cabinet, le mecanisme qui corrige le
  perimetre du capuchon, et la maniere de garder le mouvement continu appartiennent a `theo-archi`
  et aux developpeurs, saisis en parallele par `sofia-sm`.
- Elle ne rouvre pas le veto de `nadia-prof` sur le geste de traction (la manette) : seule
  l'utilisatrice pourrait le faire, et elle ne l'a pas fait ici.
- Elle ne prejuge d'aucun Done pedagogique sur les livrables du chantier 1 ou du chantier 4 : ces
  jugements restent entierement ceux de `nadia-prof`.
- Elle ne fait passer aucune ligne de `squad/programme/MATRICE-couverture.md` a `couvert-valide`.
- Elle ne rouvre pas le defaut de pli de "Le bon petit mot" ni aucun referentiel de matiere non
  encore ouvert.

## Correction documentaire du 2026-08-12

Signale a l'ouverture du sprint 9 : cette decision numerote ses chantiers 1, 2 et 4, sans jamais
ecrire de chantier 3. Verifie contre le depot avant d'ecrire ce paragraphe : c'est un ecart de
numerotation, pas un chantier perdu. Le backlog du sprint 8 porte exactement les stories S8-1 a
S8-5 - S8-1 a S8-3 sous le chantier 1, S8-4 sous le chantier 4, et S8-5 ajoutee ensuite par
`DEC-0018` sans etre rattachee a un numero de chantier de cette decision. Aucune ligne de
`squad/programme/MATRICE-couverture.md` ni aucun livrable ne depend d'un chantier 3 qui n'aurait
jamais existe. Le contenu des chantiers 1, 2 et 4 n'est pas rouvert par ce paragraphe.
