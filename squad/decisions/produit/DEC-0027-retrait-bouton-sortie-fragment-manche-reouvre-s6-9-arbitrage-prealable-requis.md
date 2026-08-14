# DEC-0027 - Le retrait du bouton de sortie du fragment de manche reouvre l'arbitrage valide de S6-9 / ADR-0022 : arbitrage prealable de nadia-prof requis avant developpement

- **Type** : perimetre de story, arbitrage prealable requis avant developpement
- **Date** : 2026-08-14
- **Auteur** : camille-po
- **Sprint** : s11
- **Competence programme visee** : transverse - aucune ligne de
  `squad/programme/MATRICE-couverture.md` ne bouge.
- **Statut** : Acceptee

## Contexte

L'utilisatrice demande, mot pour mot : *"on peut gagner de la place avec le bouton les jeux dans
la partie en cours en gardant uniquement celui dans le header"*. Motif : gain de place vertical.

J'ai relu les sources avant d'ecrire, plutot que de reprendre un etat mesure a la cloture de s10 :

- Le document porte deux sorties, toutes deux `data-role="sortie"` et toutes deux
  `hx-post="/retour"` : `a.btn.lien-accueil` dans l'en-tete permanent d'`accueil.html`, et
  `button.btn.retour-accueil` dans `fragments/manche.html` (le seul fragment qui rend aujourd'hui
  la manche en cours, echec et reussite compris depuis que S10 a fusionne ces etats -
  `fragments/retour.html` ne rend plus que la fin de partie, avec sa propre sortie
  `sortie-pleine`, hors du perimetre de cette demande).
- Le premier est masque pendant une partie par la feuille maison :
  `body:has(.ecran-de-jeu) .lien-accueil { display: none }` (`ADR-0022`). "Garder uniquement
  celui du header" suppose donc de demasquer ce lien pendant une partie, sinon il ne reste aucune
  sortie visible en jeu. Je ne choisis pas comment - c'est un moyen technique, route a
  `theo-archi`.

## Ce que je trouve en verifiant, et que je ne peux pas taire

**Cette forme a deja ete demandee, arbitree et refusee par `nadia-prof`, puis son inverse valide
comme Done pedagogique.** `ADR-0022`, arbitrage prealable du 2026-08-07 (S6-9) :

> "S6-9 est confirmee sur son principe - une seule sortie par ecran de jeu, et le mot est 'Les
> jeux' - et refusee sur la forme qu'elle donne a ce principe : la sortie qui survit sur un ecran
> de jeu est le bouton de la zone de contenu, pas le lien de l'en-tete."

Son motif est chiffre : un lien d'en-tete en contour blanc de 102 x 53 px dans un coin, contre un
aplat de 117 x 56 px au milieu du texte que l'enfant vient de lire ; garder le premier comme seule
porte ferait de l'ecran "cul-de-sac", et *"un enfant de six ans qui ne trouve pas la sortie n'en
cherche pas une seconde, il attend un adulte."* Elle a rejoue ce jugement en jouant l'increment
livre et prononce **Done pedagogique VALIDE** le 2026-08-10
(`squad/conformite/FICHE-bandit-manchot-additions-cp.md`, section "Verdict - s06, S6-10 et S6-9
prononces sur le rendu reel") : *"le cul-de-sac que je craignais n'existe pas [...] la sortie est
au meme endroit sur les cinq etats et ne change que de forme."*

La demande de l'utilisatrice inverse exactement la forme que `nadia-prof` a refusee puis validee
en sens contraire. Le motif de l'utilisatrice (gain vertical) est un motif produit legitime et
nouveau - il n'existait pas en s06 - mais il ne rend pas caduque l'arbitrage pedagogique deja
rendu ; il en est un motif de reexamen, pas un remplacement silencieux.

**Cette story ne corrige pas le defaut leve par `DEC-0026`.** L'en-tete demasque porterait
exactement la meme requete destructrice que le bouton qu'il remplacerait
(`data-role="sortie"`, `hx-post="/retour"`). Sur un document ainsi modifie, le premier arret de
tabulation (`A[0]` au sens d'`ADR-0038`) resterait cet element : toujours destructeur, seulement
deplace. La clause 1 d'`ADR-0038` resterait donc rouge sur cette forme, telle qu'elle l'est
aujourd'hui. La story versee au backlog le dit explicitement, pour qu'elle ne soit pas lue comme
un remede a la dette de `DEC-0026`.

## Decision

**1. La story est versee a `docs/backlog/backlog.md`**, avec ses criteres d'acceptation, non
affectee a un sprint.

**2. Elle ne peut pas passer en developpement sans un arbitrage prealable de `nadia-prof`, sur le
modele de celui de S6-9 du 2026-08-07.** `sofia-sm` ne l'affecte pas au planning avant que cet
arbitrage soit rendu et ecrit dans `squad/conformite/FICHE-bandit-manchot-additions-cp.md`. La
question posee, formulee sans la trancher :

> Un enfant de CP retrouve-t-il la sortie si elle ne vit plus que dans l'en-tete pendant une
> partie ? Le bouton de l'en-tete, a la place et a la taille qu'il aurait alors, est-il atteignable
> et comprehensible pour cet enfant ?

`ines-design` propose une forme si l'en-tete doit changer d'apparence pour rester lisible sans le
bouton du fragment ; `nadia-prof` arbitre, sur le meme protocole que S6-9 - joue avant de trancher,
motif chiffre.

**3. Cette decision ne prononce aucun jugement pedagogique et ne rouvre pas le Done pedagogique
VALIDE de S6-9 par elle-meme.** Ce Done reste ecrit tel quel dans
`squad/conformite/FICHE-bandit-manchot-additions-cp.md` tant que `nadia-prof` ne l'a pas
elle-meme reconsidere a la lumiere du motif de gain vertical. Je ne prends pas cette liberte a sa
place.

**4. Cette decision ne choisit aucun moyen technique** - ni le mecanisme de demasquage de l'en-tete,
ni l'impact sur `LEntreeDeMenuNeSurvitPasAUnEcranDeJeuTest` et
`LActionNeDeclarePasMoinsQueLaSortieTest` (`ADR-0022`). Route a `theo-archi` par `sofia-sm`, apres
l'arbitrage du point 2.

**5. Cette story n'est pas une des deux stories que `DEC-0026` exige pour son declencheur de
reouverture.** Elle ne ferme ni la clause 1 de M13 (`ADR-0038`), ni la reserve O de `nadia-prof`. Si
`sofia-sm` doit choisir une seule story pour tenir l'engagement de `DEC-0026` au sprint 11, ce ne
peut pas etre celle-ci.

**6. Ordre recommande avec les deux stories de `DEC-0026`** (dette bloquante, engagement de sprint
deja pris) : voir la section correspondante dans le backlog, reprise ici parce qu'elle releve d'un
arbitrage de perimetre plutot que d'un choix technique -

- Si le remede retenu pour `DEC-0026` est la **confirmation avant abandon** (story 2), cette story
  devient a faible risque une fois ce remede livre : aucun chemin de sortie, header ou fragment,
  ne detruirait plus la partie d'un seul geste, et retirer l'un des deux boutons ne change rien a
  cette propriete. L'ordre entre les deux n'est alors pas contraint.
- Si le remede retenu est la **correction de l'ordre de tabulation** (story 1), cette story ne doit
  pas etre livree avant que ce remede soit choisi et pose : retirer un des deux elements de sortie
  du document changerait la population que ce remede reordonne, et risquerait de defaire un travail
  deja fait. Dans ce cas, cette story suit le remede de `DEC-0026`, elle ne le precede pas.
- Dans les deux cas, la dette bloquante de `DEC-0026` - deja un engagement de sprint 11, sur un
  motif de perte de donnee sans confirmation - passe avant cette story, qui est un gain d'espace
  soumis de plus a un arbitrage prealable non encore rendu.

**7. Aucun chiffre de gain vertical n'est avance ici.** Le motif de l'utilisatrice est le gain de
place ; sa mesure appartient a qui la fait (`theo-archi` ou `ines-design`, sur le rendu reel), pas
a moi. La story le porte comme "a etablir", pas comme un chiffre suppose.

## Impact

- `docs/backlog/backlog.md` : une story neuve, section "Gain vertical - retrait du bouton de sortie
  du fragment de manche (DEC-0027)", non affectee a un sprint.
- `squad/decisions/produit/DEC-index.md` : ligne DEC-0027 ajoutee.
- `squad/programme/MATRICE-couverture.md` : inchangee.
- `squad/conformite/FICHE-bandit-manchot-additions-cp.md` : inchangee par cette decision ; le
  verdict VALIDE de S6-9 y reste tel qu'ecrit, en attente de l'arbitrage prealable demande au
  point 2.

## Ce que cette decision ne fait pas

- Elle ne tranche pas si un enfant de CP retrouve la sortie sous cette forme : jugement de
  `nadia-prof`, a rendre avant tout developpement.
- Elle ne choisit aucun moyen technique de demasquage ni d'impact sur les garde-fous d'`ADR-0022` :
  `theo-archi`.
- Elle ne propose aucune forme d'en-tete : `ines-design`, si l'arbitrage du point 2 l'exige.
- Elle ne rouvre pas le Done pedagogique VALIDE de S6-9 : seule `nadia-prof` peut le reconsiderer.
- Elle ne leve, ne ferme et ne remplace aucune des deux stories de `DEC-0026` ; elle n'est pas
  candidate a leur declencheur de reouverture.
- Elle ne chiffre aucun gain de place.
