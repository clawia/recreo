# DEC-0032 - Perimetre du sprint 14 : la barre d'entree par niveau ouvre sous arbitrage prealable, le filtre par matiere est differe a s15, S13-6 est remplacee

- **Type** : perimetre de sprint, arbitrage prealable requis avant developpement sur un point
- **Date** : 2026-08-17
- **Auteur** : camille-po
- **Sprint** : s14
- **Competence programme visee** : transverse pour l'essentiel de ce document - la barre d'entree ne
  cible aucune ligne neuve de `squad/programme/MATRICE-couverture.md`, elle change la navigation
  entre des jeux qui existent deja. La section 4 de cette decision fait neanmoins bouger deux lignes
  deja ouvertes de mathematiques CM1 (dette routee de s13, voir cette section).
- **Statut** : Acceptee sur le perimetre (ce qui entre, ce qui sort, ce qui est differe) ; le
  developpement de la story qu'elle ouvre (section 1) reste bloque tant que l'arbitrage prealable
  qu'elle exige n'est pas rendu.

## Contexte

L'utilisatrice demande des points d'entree dans la page de recreo, sous forme de boutons dans
l'en-tete, **toujours visibles** : par niveau (CP / CM1) et par matiere (francais / mathematiques /
...). Elle a deja rendu trois arbitrages que je ne rouvre pas :

1. **Toujours visible, y compris sur un ecran de jeu.** Un appui sur un bouton d'entree pendant une
   partie en cours ne detruit pas la partie : il passe par l'ecran de confirmation qui existe deja
   (`fragments/confirmation.html`, `DEC-0026`, `ADR-0038`, `ADR-0042`). Elle sait que cela rouvre
   l'arbitrage de `nadia-prof` du 2026-08-07 (`ADR-0022`, S6-9, une seule sortie par ecran de jeu)
   et l'assume.
2. **Le filtre par niveau seul est ouvert en s14.** Le filtre par matiere est differe a s15, le temps
   que je tranche la nomenclature affichee (section 3).
3. **Les boutons de matiere seront derives du catalogue** le jour ou ils ouvrent : une matiere sans
   jeu n'affiche pas de bouton - intention actee ici, non implementee ce sprint.

## Decision

### 1. Le filtre par niveau ouvre, sous arbitrage prealable bloquant

La story `S14-1 - Une barre d'entree par niveau, toujours visible dans l'en-tete`
(`docs/backlog/backlog.md`, section "Sprint 14") est versee au backlog avec ses criteres
d'acceptation. Elle ajoute, dans l'en-tete, deux boutons de niveau visibles en permanence - y
compris sur un ecran de jeu, ou seul le lien "Les jeux" survit aujourd'hui, masque par
`body:has(.ecran-de-jeu) .lien-accueil { display: none }` (`ADR-0022`).

**Cette story reouvre l'arbitrage de S6-9.** `ADR-0022` a tranche, le 2026-08-07, qu'un ecran de jeu
ne porte qu'une seule sortie visible, et `nadia-prof` a motive ce principe par le risque qu'un enfant
de six ans hesite entre plusieurs elements qui ressemblent a une sortie. Ajouter deux boutons
actionnables et visibles en permanence sur ce meme ecran est exactement le geste que cet arbitrage
visait a empecher, meme si leur consequence sur la partie passe desormais par une confirmation. Sur
le modele de `DEC-0027` (retrait du bouton de sortie du fragment de manche, meme reouverture),
**cette story ne peut pas etre affectee au planning avant qu'un arbitrage prealable de `nadia-prof`
soit rendu**, ecrit dans `squad/conformite/FICHE-barre-entree-niveau.md`, fiche que j'ouvre dans le
meme geste que cette decision (section "Ce que je produis" du mandat de ce sprint). La question
posee, sans la trancher :

> Un enfant de CP retrouve-t-il la sortie et ne confond-il pas les boutons de niveau avec une sortie
> de jeu, si l'en-tete porte desormais trois elements actionnables visibles en permanence sur un
> ecran de jeu (le lien "Les jeux", habituellement masque, restant masque ; les deux boutons de
> niveau, nouvellement visibles) ? Le passage par l'ecran de confirmation deja existant est-il un
> signal suffisant pour qu'il comprenne qu'appuyer sur un bouton de niveau pendant une partie a une
> consequence sur cette partie ?

Je ne prends pas cette liberte a la place de `nadia-prof`, et je ne prejuge pas sa reponse. Si elle
refuse le principe, la story reste au backlog sous la forme que son refus autorise, exactement comme
`DEC-0027` l'a fait sur son propre sujet.

### 2. Le filtre par matiere est differe a s15, sur une question de nomenclature a trancher

Le filtre par matiere n'ouvre pas ce sprint. Motif : les paquets de contenu portent aujourd'hui
`matiere: "Francais"` et `matiere: "Mathematiques"`, ecrits en ASCII, comme valeur interne jamais
montree a un enfant jusqu'ici. Le jour ou un bouton de matiere affiche ce mot, il devient du texte lu
par un enfant, et `DEC-0002` (amendement du 2026-08-04) exige que ce texte soit "integralement
accentue, orthographe et ponctuation irreprochables". "Francais" et "Mathematiques" ne le sont pas.

Ce qui reste a trancher avant s15, et que je ne tranche pas ici pour ne pas decider a la place de qui
doit l'instruire :

- **Si la valeur interne devient elle-meme la valeur accentuee** ("Français", "Mathématiques"),
  auquel cas aucune correspondance supplementaire n'est necessaire, mais je ne sais pas si cette
  valeur est comparee ailleurs dans le code par egalite de chaine d'une facon que l'accent romprait
  - question technique, pour `theo-archi`.
- **Si un libelle d'affichage distinct de la valeur interne est introduit** (la valeur reste
  l'identifiant technique, un libelle accentue est porte a cote pour l'en-tete) - question
  d'architecture sur l'endroit ou vit cette correspondance (contenu, domaine, ou uniquement la vue),
  egalement pour `theo-archi`.

Je route cette question a `sofia-sm`, qui la route a `theo-archi`, avant que le filtre par matiere ne
soit instruit en story pour s15. Ce report n'est pas un nouveau report de la couverture programme au
sens de `DEC-0007` : c'est une question de forme d'un point d'entree transverse, aucune ligne de
`squad/programme/MATRICE-couverture.md` n'est retardee par ce point.

### 3. Intention versee pour le jour ou le filtre par matiere ouvrira

Quand le filtre par matiere sera instruit, les boutons de matiere seront **derives du catalogue** :
une matiere sans jeu au catalogue n'affiche pas de bouton pour elle. Je le verse ici comme intention
produit, pas comme un critere de cette story ni de celle de s15 tant qu'elle n'est pas ecrite.

### 4. S13-6 n'est pas developpee sous sa forme d'origine : elle est remplacee par S14-1

`S13-6 - Un chemin vers le catalogue qui ne detruit pas la partie en cours` a ete planifiee au sprint
13 (`planning.md`, affectee a `marc-dev` en second lot), `ines-design` a produit deux variantes
chiffrees (`proposition-ines-catalogue-en-partie.md`) et `theo-archi` un avis de faisabilite, mais
aucun code n'a ete livre : ni `review.md`, ni `RAPPORT.md`, ni `retro.md` de s13 ne la mentionnent, et
je l'ai verifie moi-meme avant d'ecrire cette section plutot que de reprendre l'affectation du
planning sur parole.

**S13-6 ne sera pas developpee sous la forme que j'avais ecrite.** Son critere central etait qu'une
consultation du catalogue **ne modifie jamais l'etat de la partie**, sans confirmation d'aucune
sorte - une lecture pure. La demande de l'utilisatrice pour s14 resout le meme besoin (atteindre le
catalogue pendant une partie) par un mecanisme different, qu'elle a choisi elle-meme : passer par
l'ecran de confirmation existant, donc une action potentiellement destructrice signalee, plutot
qu'une consultation garantie sans consequence. `S14-1` **absorbe et remplace** S13-6 : le besoin
initial de `nadia-prof` (reserve O, seconde moitie, `FICHE-bandit-manchot-additions-cp.md`,
arbitrage de mecanique) est repris par `S14-1`, mais sa resolution technique et son experience pour
l'enfant ne sont plus celles que S13-6 decrivait. Je ne laisse pas S13-6 orpheline une seconde fois :
son sort est ecrit ici, et une note est ajoutee dans `docs/backlog/backlog.md` a sa suite.

### 5. Dette routee de s13 - matrice et file d'attente mises a jour

`RAPPORT.md` de s13 (section "Encore ouverts") route deux points a moi :

- **`squad/programme/MATRICE-couverture.md` et `squad/validation/FILE-ATTENTE.md` non mis a jour
  depuis les verdicts du 2026-08-16 (soir).** J'ai lu les deux fiches de conformite concernees
  avant d'ecrire : `nadia-prof` a rendu, ce soir-la, un verdict **VALIDE SOUS RESERVE** sur
  `bandit-manchot-grands-nombres-cm1` (troisieme passage, refus des 2026-08-15 et 2026-08-16 leves
  sur le motif de rendu, reserve J ouverte et non bloquante - huit items sur 28 placent une
  proposition entierement sous le pli a 360 x 640) et un verdict **VALIDE SOUS RESERVE** sur
  `bandit-manchot-perimetre-cm1` (refus du 2026-08-16 leve sur son motif exact, une reserve neuve non
  bloquante sur la largeur des boites versee au dossier). Les deux lignes de
  `squad/programme/MATRICE-couverture.md` passent a `couvert-valide` dans ce meme geste, et les deux
  increments sortent de `squad/validation/FILE-ATTENTE.md`. Detail complet dans la matrice
  elle-meme, section "Synthese apres les verdicts du sprint 13 (2026-08-16, soir)".
- **Le couple retrait-catalogue / methode-de-test non garde par un mecanisme, et le quatrieme axe de
  rang non mesure** (`rapport-qualite.md` de s13, sections 4.1, 4.2 et 3) : ce sont des candidats de
  story a arbitrer avec `theo-archi`, pas des points que je tranche seule. Je les route a `sofia-sm`
  pour saisine de `theo-archi` :
  - le motif de retrait d'un jeu du catalogue retire aujourd'hui, a la main, la methode de test qui
    verifie que l'entree charge le bon paquet (quatre classes reproduisent ce motif -
    `PaquetCm1MesuresTest`, `PaquetCm1PerimetreTest`, `PaquetCpComplements10S9Test`,
    `PaquetCpSoustractionsS9Test` - plus `PaquetCm1GrandsNombresTest`, deja touchee une fois ce
    sprint) ; rien n'assure que la restitution de l'entree restitue la methode dans le meme geste ;
  - un quatrieme axe de rang (la position a l'ecran d'une proposition) est enumerable par lecture de
    code mais n'est verifie par aucune mesure sur les sorties reelles de la mecanique.
  Je ne prescris aucun mecanisme : c'est une question d'architecture de test, comme `yanis-qa`
  l'ecrit lui-meme dans son rapport.

## Ce que cette decision ne fait pas

- Elle ne tranche pas si un enfant de CP retrouve la sortie sous la forme de `S14-1` : jugement de
  `nadia-prof`, a rendre avant tout developpement (section 1).
- Elle ne choisit aucun moyen technique - ni pour la barre d'entree, ni pour la representation de la
  matiere accentuee : route a `theo-archi` par `sofia-sm` (sections 1 et 2).
- Elle ne propose aucune forme visuelle de la barre : `ines-design`, une fois l'arbitrage prealable
  rendu.
- Elle ne prononce aucun Done pedagogique et ne rejuge aucun contenu : les deux verdicts de la
  section 4 restent entierement ceux de `nadia-prof`, non rediscutes ici.
- Elle n'ouvre aucune ligne neuve de `squad/programme/MATRICE-couverture.md` : la barre d'entree est
  transverse, et la section 4 ne fait que refleter des verdicts deja rendus sur des lignes deja
  ouvertes.
- Elle ne fixe pas la nomenclature affichee pour les matieres : question ouverte pour s15 (section
  2), qui touche a la fois une decision technique (ou vit la correspondance) et une decision de
  redaction pour un enfant.

## Impact

- `docs/backlog/backlog.md` : story `S14-1` ajoutee, section "Sprint 14" ; note de cloture ajoutee a
  la suite de `S13-6` renvoyant a cette decision.
- `squad/conformite/FICHE-barre-entree-niveau.md` : fiche ouverte par moi, bloc de validation vide,
  section d'arbitrage prealable a remplir par `nadia-prof` avant toute affectation en developpement.
- `squad/programme/MATRICE-couverture.md` : les lignes mathematiques CM1 "Lire, ecrire, decomposer et
  comparer les nombres entiers jusqu'au million" et "Calculer un perimetre de figures usuelles (carre,
  rectangle)" passent a `couvert-valide` (section 5).
- `squad/validation/FILE-ATTENTE.md` : les deux increments ci-dessus en sortent (section 5).
- `squad/decisions/produit/DEC-index.md` : ligne DEC-0032 ajoutee.
- Signale a `sofia-sm`, hors de ce fichier : saisine de `theo-archi` sur le couple retrait-catalogue
  / methode-de-test et sur le quatrieme axe de rang (section 5) ; saisine de `theo-archi` sur la
  representation de la matiere accentuee avant que s15 n'instruise le filtre (section 2).

## Ce qui reste non testable par construction

- Qu'un enfant de six ans distingue un bouton de niveau d'un bouton de sortie sur un ecran de jeu est
  un jugement que seule une observation reelle, par `nadia-prof`, peut rendre - aucun automate de ce
  depot ne mesure la comprehension d'un enfant.
- Que le passage par l'ecran de confirmation soit percu comme un signal de consequence, et non comme
  un obstacle arbitraire, releve du meme jugement.
