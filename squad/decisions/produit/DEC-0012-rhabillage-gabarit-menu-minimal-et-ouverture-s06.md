# DEC-0012 - Le rhabillage du gabarit (en-tete, icone, menu, contenu centre) ouvre le sprint 6 ; le menu reste a une seule entree

- **Type** : perimetre produit, priorisation de sprint, contenu d'une fonctionnalite (menu)
- **Date** : 2026-08-06
- **Auteur** : camille-po
- **Sprint** : s05/s06
- **Competence programme visee** : transverse - decision de perimetre et de contenu de navigation,
  aucune competence pedagogique.
- **Statut** : Acceptee

## Contexte

L'utilisatrice revient une deuxieme fois sur le design ("le template de base de recreo est vide a
mon gout"), en citant colorlib.com comme inspiration visuelle et en nommant deux manques precis :
un en-tete avec icone et menu, et un contenu centre dans la page. Sa remarque est fondee : le lot
Bootstrap de s04/s05 (`ADR-0014`) a change le rendu de base sans jamais toucher la structure de la
page - `accueil.html` reste aujourd'hui un `<h1>` nu suivi d'une liste de boutons, sans aucun
conteneur, colle en haut a gauche (verifie sur `adapters/adapter-web/src/main/resources/templates/
accueil.html`).

Deux questions se posent avant d'ecrire les stories : ou les caser dans le planning, et ce que
contient reellement le menu demande.

### Ou caser ces stories

`DEC-0011-perimetre-s05-reste-correctif.md` a borne s05 a la correction de "Le bon son" et a
l'achevement de la ligne homophones CM1, et engage explicitement s06 sur l'ouverture d'au moins une
des deux lignes candidates de `textes-trous/`. s05 porte deja neuf stories, dont une routee a
`theo-archi` pour etude de faisabilite (S5-5) et une dont l'arbitrage pedagogique reste ouvert
(S5-6, couple `la/la/l'a`). Ajouter un chantier de gabarit a un sprint deja charge, en cours
d'execution, reproduirait le trou nomme par moi-meme en s02 - une story mal placee qui absorbe la
capacite d'un sprint sans que cela soit dit d'avance (voir le contexte du Sprint 2 dans
`docs/backlog/backlog.md`).

Je choisis donc d'ouvrir le **sprint 6**, aux cotes de l'ouverture francais deja engagee par
`DEC-0011`. Je ne retire ni ne reporte cet engagement : les deux chantiers portent sur des artefacts
disjoints (gabarits et feuille de style maison pour le rhabillage ; paquet YAML de contenu et,
selon le candidat retenu, un arbitrage `theo-archi`/`nadia-prof` pour l'ouverture francais) et
peuvent avancer sur des lots distincts sans se bloquer l'un l'autre. Je n'affirme pas pour autant
qu'ils tiennent tous les deux dans la meme fenetre de temps : la sequence reelle a l'interieur du
sprint 6 est une question de capacite, donc de planning, qui appartient a `sofia-sm`. Si elle
constate au planning que les deux ne rentrent pas, l'ouverture francais engagee par `DEC-0011`
garde la priorite, parce qu'elle est deja une promesse ecrite a l'utilisatrice sur le rythme de
couverture du programme ; le rhabillage se decale alors au sprint suivant plutot que l'inverse.

### Ce que contient le menu

L'application n'a aujourd'hui que deux ecrans atteignables : l'accueil (liste des jeux) et une
partie en cours (manche, retour, fin de partie, erreur, pas-de-jeu - autant d'etats du meme ecran de
jeu, pas des destinations distinctes). Une seule action de navigation existe : le bouton "Les jeux"
present dans chaque fragment de l'ecran de jeu, qui ramene a l'accueil.

Un menu qui listerait plusieurs entrees inventerait des destinations qui n'existent pas :

- un lien vers l'accueil depuis l'accueil est un ornement (on y est deja) ;
- un filtre par niveau (CP / CM1) est un vrai besoin potentiel, mais aucune fonctionnalite de
  filtrage n'existe cote serveur aujourd'hui ; l'ouvrir maintenant serait batir une entree de menu
  pour une fonctionnalite non ecrite, ce que je m'interdis explicitement (voir la reserve deja
  nommee en fin de `docs/backlog/backlog.md`, "l'accueil ne mentionne le niveau vise que pour
  'Calcul CP'") ;
- reprendre une partie interrompue n'a pas besoin d'entree de menu : la reprise (`S1-4`) est deja
  automatique et silencieuse au chargement de l'accueil.

**Decision : le menu contient exactement une entree, "Accueil"**, qui rend visible et accessible
depuis n'importe quel etat de l'ecran de jeu la meme action que le bouton "Les jeux" existant. Ce
n'est pas une entree qui ne mene nulle part : c'est la seule destination reelle du produit en dehors
de l'ecran en cours. Le menu grandira quand une deuxieme destination reelle existera (un filtre par
niveau qui a ete construit, une deuxieme mecanique de jeu, etc.), pas avant, et cette croissance sera
actee par une nouvelle decision au moment ou elle deviendra reelle.

Une seule entree ne demande aucun repli ni bascule (pas de menu "hamburger") : elle peut rester
visible en permanence, y compris a la largeur cible de 375 px (`DEC-0007` point 5), sans JavaScript
de plus. `ADR-0014` point 7 est explicite : le JavaScript de Bootstrap (dont ses menus repliables)
n'entre pas dans ce depot. Si le menu devait un jour compter assez d'entrees pour justifier un repli
sur petit ecran, ce serait un besoin technique nouveau, a router vers `theo-archi` avant d'etre
suppose acquis - pas aujourd'hui.

### Le risque de regression nomme par ADR-0017, et ce que je n'y tranche pas

`ADR-0017` protege la hierarchie entre un bouton d'action et un bouton de sortie **au sein d'un
meme fichier de gabarit**, et nomme lui-meme la limite : "*Aucun couple action/sortie ne traverse
aujourd'hui cette frontiere [entre `accueil.html` et les fragments qu'il inclut], mais rien ne
l'empeche demain.*" Un en-tete place dans `accueil.html`, donc hors de `fragments/manche.html` et
`fragments/retour.html`, avec une entree qui ramene a l'accueil, est exactement ce "demain" : le
lien du menu et les boutons de reponse ou "Encore !" pourraient s'afficher ensemble sans qu'aucun
garde-fou actuel ne les compare.

Je ne tranche pas ici comment fermer ce trou - ce serait une decision technique. J'ecris dans
`docs/backlog/backlog.md` (story S6-1) le critere produit que ce risque doit respecter (le lien de
menu ne doit jamais surpasser un bouton de reponse), et je le signale explicitement comme un besoin
a instruire par `theo-archi` via `sofia-sm` avant que ce critere puisse etre verifie par la CI plutot
que par `nadia-prof` seule en jouant.

## Decision

1. Les deux stories de rhabillage (en-tete/icone/menu, contenu centre) ouvrent le **sprint 6**,
   detaillees dans `docs/backlog/backlog.md` (S6-1, S6-2).
2. Le menu de l'en-tete contient **exactement une entree**, "Accueil", equivalente au bouton "Les
   jeux" existant. Aucune autre entree n'est ajoutee par ces stories.
3. L'ouverture francais engagee par `DEC-0011` pour le sprint 6 n'est ni retiree ni reportee par
   cette decision. Si la capacite du sprint ne permet pas les deux chantiers, l'ouverture francais
   garde la priorite ; c'est `sofia-sm` qui le constate au planning, pas moi qui le decide ici par
   avance.
4. Le risque nomme par `ADR-0017` (comparaison de saillance qui ne traverse pas la frontiere entre
   `accueil.html` et ses fragments) est signale a `sofia-sm`, qui route vers `theo-archi`, comme
   prealable technique a instruire avant que le critere correspondant de S6-1 puisse etre verifie
   par la CI.

## Ce que cette decision ne fait pas

- Elle ne tranche aucune question technique : ni le mecanisme de vendorisation ou de gabarit
  (`theo-archi`), ni la forme visuelle de l'en-tete, de l'icone ou du menu (`ines-design`).
- Elle ne prejuge pas du verdict pedagogique de `nadia-prof` sur le rendu obtenu.
- Elle ne cree ni ne modifie aucune ligne de `squad/programme/MATRICE-couverture.md` : ces deux
  stories sont transverses, sans competence pedagogique visee.
- Elle n'ouvre pas de fiche de conformite : ces stories ne portent pas de contenu pedagogique
  propre a un jeu, elles modifient un gabarit partage par tous les jeux existants.

## Impact

- `docs/backlog/backlog.md` : ajout de la section "Sprint 6" et des stories S6-1, S6-2.
- `squad/decisions/produit/DEC-index.md` : ligne ajoutee pour `DEC-0012`.

## Ce qui reste non testable par construction

- Que le menu a une seule entree reste suffisant pour l'utilisatrice et pour un enfant qui joue est
  un jugement d'usage, pas une propriete que ce depot peut prouver ; je le revise si l'un ou l'autre
  le contredit explicitement.
- Que les deux chantiers du sprint 6 (rhabillage, ouverture francais) tiennent effectivement dans la
  meme fenetre de sprint est un engagement de planification, pas une garantie : voir la meme reserve
  posee par `DEC-0011`.
