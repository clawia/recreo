# DEC-0034 - L'utilisatrice retablit le lien "Les jeux" dans l'en-tete et unifie l'apparence des puces de niveau : DEC-0033 est remplacee

- **Type** : arbitrage de l'utilisatrice, rendu apres avoir joue l'increment livre de s14 ; remplace
  une decision produit anterieure et contredit deux arbitrages prealables de `nadia-prof`
- **Date** : 2026-08-17
- **Auteur** : camille-po (transcription de l'arbitrage de l'utilisatrice, qui est l'autorite)
- **Sprint** : s15 (ouverture)
- **Competence programme visee** : transverse - pas de contenu pedagogique. Cette decision touche
  la navigation et l'apparence de l'en-tete ; elle ne cible aucune ligne de
  `squad/programme/MATRICE-couverture.md`.
- **Statut** : Acceptee

## Contexte

Sprint 14 est clos. `nadia-prof` a prononce, le 2026-08-17, le Done pedagogique de `S14-1` et de
`S14-2` sous la forme **VALIDE SOUS RESERVE** (`squad/conformite/FICHE-barre-entree-niveau.md`,
section "Done pedagogique du 2026-08-17 ... second verdict du jour"), sur l'increment corrige
`23267ea` : neuf conditions bloquantes tenues (BARRE-1 a BARRE-9), deux reserves non bloquantes
(S14-A, S14-B).

Parmi ces neuf conditions, **BARRE-7** portait un historique long : `nadia-prof` avait constate des
le 2026-08-14 (PORTE-1, `DEC-0027`) qu'appuyer sur le lien "Les jeux" de l'en-tete depuis l'accueil
non filtre rend un document identique a celui qui l'affiche - un bouton qui ne repond a rien. `S14-1`
a rendu cette condition bloquante en ajoutant, dans la meme zone, deux puces qui, elles, repondent.
`nadia-prof` a laisse deux issues acceptables sans trancher entre elles : que la boite disparaisse,
ou qu'elle fasse autre chose que rendre l'ecran identique. `camille-po` a choisi la premiere
(`DEC-0033-barre-7-le-lien-les-jeux-de-l-en-tete-disparait-de-l-accueil.md`) : le lien est retire de
tout le depot, pas seulement masque. `nadia-prof` a valide ce choix sans reserve propre ("S14-2 -
... VALIDE, sans reserve propre", meme fiche, section 6).

**L'utilisatrice a ensuite joue l'application livree** et rend deux arbitrages qui reviennent sur
`DEC-0033`, en connaissance du motif que je lui ai expose. Ils s'actent, ils ne se rediscutent pas.
Un troisieme retour - les boutons de l'en-tete disparaissent au survol - est un defaut technique
deja diagnostique et route a `theo-archi` (`.chip-niveau` sans regle `:hover` propre, ecrasee par
`.btn:hover` de la bibliotheque tierce a specificite superieure) ; il n'entre pas dans cette
decision et n'y est cite que pour dire qu'il ne s'y trouve pas.

## Arbitrage A - "Les jeux" revient dans l'en-tete, dans son etat d'avant s14

**Decision de l'utilisatrice** : le lien "Les jeux" reprend sa place dans l'en-tete, sous la forme
qu'il avait juste avant le sprint 14 - un lien qui rend l'accueil, sans role nouveau, sans devenir
un bouton d'annulation de filtre. L'option "lui donner un vrai role" a ete presentee et ecartee par
elle.

### Ce que cela implique, et que je ne contourne pas

Ceci **reintroduit exactement le defaut que `nadia-prof` avait rendu bloquant sous BARRE-7** : un
bouton qui, depuis l'accueil non filtre - l'etat de tout premier chargement et celui auquel toute
navigation finit par revenir -, rend un document identique a celui qui l'affiche ; et qui, depuis
l'accueil filtre par niveau, repond en rendant le catalogue complet. **Le meme bouton dit tantot
oui, tantot non, selon un etat que l'enfant ne voit pas.** C'est mot pour mot le motif que
`nadia-prof` a ecrit dans `FICHE-barre-entree-niveau.md` (BARRE-7) et dans `DEC-0027` (PORTE-1).

L'utilisatrice passe outre ce motif **en connaissance de cause** : je le lui ai expose avant qu'elle
tranche, et elle a choisi cette option plutot que l'alternative que je proposais (un role neuf pour
le lien). C'est son arbitrage a rendre, pas le mien a lui imposer ou a lui refuser.

**Le refus pedagogique de `nadia-prof` sur ce point n'est ni efface ni reecrit.** Il reste lisible
en entier dans `squad/conformite/FICHE-barre-entree-niveau.md` (le premier verdict du 2026-08-17,
et l'arbitrage prealable qui l'a precede) et dans `DEC-0027` (PORTE-1, amendement du 2026-08-14). Ce
qui change n'est pas ce qu'elle a constate - c'est constate, c'est ecrit, ca reste vrai - mais qu'un
arbitrage de l'utilisatrice, posterieur et informe, choisit de vivre avec ce defaut plutot que de le
faire disparaitre. La hierarchie des autorites du depot le permet explicitement : l'utilisatrice est
au-dessus de la squad, `nadia-prof` reste seule autorite sur le Done pedagogique tant que
l'utilisatrice ne se prononce pas elle-meme.

### Chronologie complete, pour qu'un lecteur dans six mois comprenne le bouton qu'il trouve

| Qui | Quand | Quoi |
|---|---|---|
| `nadia-prof` | 2026-08-07 (S6-9) | Arbitrage prealable : une seule sortie par ecran de jeu, le mot est "Les jeux" ; le lien de l'en-tete est masque sur les ecrans de jeu (`ADR-0022`) |
| `nadia-prof` | 2026-08-14 | Constate PORTE-1 (`DEC-0027`) : sur l'accueil non filtre, le lien ne fait rien - non bloquant a l'epoque, faute de filtre qui le rendrait visible |
| `nadia-prof` | 2026-08-17 (arbitrage prealable S14-1) | BARRE-7 : la meme boite devient bloquante, parce que S14-1 installe a cote d'elle deux puces qui repondent |
| `camille-po` | 2026-08-17 | `DEC-0033` : resout BARRE-7 en retirant le lien de tout le depot (premiere des deux issues que `nadia-prof` avait laissees ouvertes) |
| `nadia-prof` | 2026-08-17 (Done pedagogique) | VALIDE SOUS RESERVE, BARRE-7 tenue par le retrait, S14-2 valide sans reserve propre |
| **l'utilisatrice** | **2026-08-17, apres avoir joue** | **Retablit le lien, en connaissance du motif BARRE-7/PORTE-1** : cette decision |

### Ce que devient `DEC-0033`

`DEC-0033` est **remplacee par la presente decision** sur son acte central (le retrait du lien).
Elle reste au depot, non effacee : c'est elle qui documente pourquoi le retrait avait ete choisi et
quel motif il fermait, et c'est ce motif que l'arbitrage de l'utilisatrice rouvre sciemment. Son
statut passe a "Remplacee par DEC-0034".

`DEC-0027` (amendement du 2026-08-17, "PORTE-1 fermee par DEC-0033") recoit une note similaire : la
fermeture qu'il enregistrait n'est plus l'etat du depot, remplacee par le present arbitrage. Voir
"Impact" ci-dessous.

## Arbitrage B - les puces CP et CM1 s'affichent "comme Les jeux"

**Decision de l'utilisatrice, dans ses mots** : les puces de niveau prennent la meme apparence que
le lien "Les jeux" - meme traitement visuel, sur le fond de l'en-tete - et la bande sombre distincte
qui les porte aujourd'hui disparait.

### Ce que cela ferme

Ceci repond a sa troisieme observation ("le second cadre me surprend") et **ferme au passage la
reserve S14-A** de `nadia-prof` : une bande sombre `#081c2c` de 16 px, vide de tout contenu,
subsistait sur l'ecran de confirmation et sur l'ecran d'erreur, la ou les puces sont masquees. Si
la bande de fond des puces disparait au profit du fond commun de l'en-tete, ce residu disparait
avec elle.

### Ce que cela implique, et que je ne contourne pas non plus

Ceci **contredit un arbitrage prealable que `nadia-prof` avait rendu et transforme en condition
bloquante**. Sa reponse a sa propre question 5 ("la separation avec Les jeux") disait : *"les puces
sont sur un second rang, a gauche, sur une bande plus sombre, et Les jeux reste au premier rang, a
droite : les deux ne sont jamais adjacents, ils sont diagonalement opposes"* - et elle en a fait
**BARRE-9** : *"les puces de niveau et la sortie Les jeux ne partagent jamais le meme rang, a
aucune largeur"*, motif nomme : deux boites de forme voisine, cote a cote, dont l'une change de
niveau et l'autre quitte le jeu, est exactement la confusion qu'on lui demandait d'eviter.

L'utilisatrice tranche l'**apparence** des trois boutons - elle ne dit pas qu'ils doivent se
retrouver sur le meme rang, elle dit qu'ils doivent se ressembler sur le meme fond. Je note la
tension sans la resoudre a sa place : un fond commun et l'absence de bande separee rendent une
disposition sur deux rangs distincts moins evidente a lire qu'aujourd'hui, mais rien dans sa
formulation n'exige de fusionner les rangs. **Cette question de disposition reste ouverte et n'est
pas de mon ressort ni de celui de `nadia-prof` en premier** : elle revient a `ines-design`, qui
proposera une forme, jugee ensuite par `nadia-prof` au regard de BARRE-9 et de la contrainte de
largeur.

**Contrainte de largeur a ne pas perdre en route** : a 360 px, l'en-tete doit loger trois boutons
de meme gabarit visuel - "Les jeux" mesure 109 x 53 px, les puces 56 x 50 px ("CP") et 74 x 50 px
("CM1"), mesures de `nadia-prof` (`FICHE-barre-entree-niveau.md`, BARRE-8). Que ces trois boites
tiennent sur un ou deux rangs, avec ou sans bande de fond distincte, reste a demontrer sur le rendu
reel, pas a supposer.

## Ce que cette decision ne fait pas

- Elle ne choisit aucun moyen technique : ni la forme du masquage du lien "Les jeux" sur les ecrans
  de jeu (qui reutilise la forme generalisee de la clause 2 d'`ADR-0022`, troisieme amendement du
  2026-08-17 - le choix du marqueur garde par `theo-archi`), ni la feuille de style qui unifie
  l'apparence des trois boutons.
- Elle ne tranche pas la disposition en un ou deux rangs des trois boutons a 360 px - routee a
  `ines-design`.
- Elle ne prononce aucun Done pedagogique. Le Done pedagogique de `S14-1`/`S14-2`, deja prononce
  VALIDE SOUS RESERVE sur l'increment `23267ea`, ne couvre pas cet arbitrage : l'increment qui en
  resultera devra etre rejoue et rejuge par `nadia-prof`, y compris sur des conditions (BARRE-7,
  BARRE-9) qu'elle a deja tranchees dans l'autre sens.
- Elle n'ouvre et ne ferme aucune ligne de `squad/programme/MATRICE-couverture.md`.

## Impact

- `docs/backlog/backlog.md`, section "Sprint 15" : deux stories neuves - `S15-1` (retour du lien
  "Les jeux" dans l'en-tete, etat d'avant s14) et `S15-2` (apparence commune des trois boutons de
  l'en-tete, disparition de la bande sombre).
- `squad/decisions/produit/DEC-index.md` : ligne DEC-0034 ajoutee ; ligne DEC-0033 mise a jour,
  statut "Remplacee par DEC-0034".
- `squad/decisions/produit/DEC-0033-barre-7-le-lien-les-jeux-de-l-en-tete-disparait-de-l-accueil.md` :
  non modifiee dans son corps (l'historique reste lisible tel qu'ecrit le 2026-08-17), une note de
  tete ajoutee renvoyant a la presente decision.
- `squad/decisions/produit/DEC-0027-retrait-bouton-sortie-fragment-manche-reouvre-s6-9-arbitrage-
  prealable-requis.md` : note ajoutee sous son amendement du 2026-08-17, constatant que la
  fermeture de PORTE-1 qu'il enregistrait ne reflete plus l'etat du depot a compter de cette
  decision.
- `squad/conformite/FICHE-barre-entree-niveau.md` : **inchangee par cette decision.** Les verdicts
  de `nadia-prof` qui y sont ecrits (arbitrage prealable, refus, Done VALIDE SOUS RESERVE) restent
  tels quels ; ils portent sur l'increment `23267ea`, pas sur celui que cette decision ouvre.
  L'increment qui en resultera devra y recevoir un Done pedagogique distinct, une fois developpe.
- `squad/programme/MATRICE-couverture.md` : inchangee.
- Route a `sofia-sm`, hors de ce fichier : affectation au planning de s15, saisine de `theo-archi`
  pour le moyen technique et l'impact aux garde-fous cites en note de routage, saisine
  d'`ines-design` pour la disposition, saisine de `nadia-prof` pour le Done pedagogique de
  l'increment resultant.

## Note - consequence que je signale sans en faire une condition

Le retour du lien "Les jeux" restaure aussi, sur l'ecran de fin de partie et sur l'ecran de
confirmation, une situation ou deux boutons de sortie porteront le meme mot ("Les jeux" en aplat
dans la zone de contenu, "Les jeux" en contour dans l'en-tete si jamais l'en-tete cessait d'y etre
masque) : ce n'est pas un cas nouveau, c'etait deja la geometrie d'avant s14, et rien dans les
arbitrages A et B ne la change. Je le note pour qu'on ne le redecouvre pas comme une surprise le
jour ou quelqu'un lira `ADR-0022` point 3 sans en avoir suivi l'historique.

## Ce que je route

- **`sofia-sm`** : affectation au planning de s15 de `S15-1` et `S15-2` ; saisine de `theo-archi`
  pour le moyen technique de reintroduction du lien "Les jeux" (impact sur
  `LActionNeDeclarePasMoinsQueLaSortieTest`, sur
  `EnTeteEtMenuTest.leMenuDeLEnTeteContientExactementUneEntreeVersLAccueil` et sur la forme
  generalisee de la clause 2 d'`ADR-0022`) et pour l'unification visuelle des trois boutons ; le
  defaut de survol des boutons de l'en-tete (deja diagnostique) reste une saisine distincte de
  `theo-archi`, sur laquelle je ne me prononce pas ici - **je n'ai pas d'avis arrete sur le fait
  qu'elle doive rejoindre le meme lot ou en former un a part** : elle touche la meme zone d'ecran
  que `S15-1`/`S15-2` et pourrait s'y corriger a cout marginal si le meme fichier de style est
  rouvert, mais elle n'a pas de dependance fonctionnelle avec eux et peut se livrer independamment.
  A `sofia-sm` d'en decider selon la charge du sprint.
- **`ines-design`** : disposition en un ou deux rangs des trois boutons a 360 px, sous la
  contrainte de gabarit rappelee ci-dessus (109 x 53, 56 x 50, 74 x 50 px) et sous BARRE-9 tel
  qu'ecrit - a moins que `nadia-prof` ne le leve elle-meme au vu de la forme proposee.
- **`nadia-prof`** : reverification pedagogique de l'increment resultant. Elle devra rejuger deux
  conditions qu'elle a deja tranchees dans l'autre sens (BARRE-7 et BARRE-9) au regard d'un
  arbitrage de l'utilisatrice qui les surplombe ; c'est a elle de dire ce qu'elle en fait, y
  compris si elle souhaite reformuler ces conditions plutot que les lever purement et simplement.

## Note du 2026-08-18 (s15) - la section "Ce que cette decision ne fait pas" supposait un masquage qu'un arbitrage posterieur retire

Cette decision ecrivait, au sujet du lien "Les jeux" retabli par l'arbitrage A : *"elle ne choisit
aucun moyen technique : ni la forme du masquage du lien Les jeux sur les ecrans de jeu (qui
reutilise la forme generalisee de la clause 2 d'ADR-0022 [...])"*. Cette phrase supposait que le
lien resterait masque sur les ecrans de jeu, comme il l'etait depuis S6-9 (2026-08-07) - seul le
retrait sur l'accueil non filtre etait en cause dans l'arbitrage A.

**Un troisieme arbitrage, rendu le lendemain (2026-08-18), retire ce masquage sur la manche** : la
sortie d'en-tete reste desormais visible pendant une partie en cours, ce que l'arbitrage A seul
n'avait pas demande. Le masquage sur les ecrans de jeu que cette decision anticipait comme
inchange **ne l'est plus** que sur la confirmation et la fin de partie, via un second marqueur
neuf. Le detail, la chronologie complete et ce que cet arbitrage coute (BARRE-3, BARRE-9) sont
dans `DEC-0035-trois-arbitrages-de-s15-non-traces-sortie-permanente-rang-unique-etat-actif.md`,
arbitrage C.

**Ce que cette note ne fait pas** : elle ne corrige aucune autre partie de `DEC-0034`, dont les
arbitrages A et B restent exacts et inchanges tels qu'ecrits le 2026-08-17. Elle situe seulement la
phrase ci-dessus par rapport a un arbitrage qui lui succede.
