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

## Amendement du 2026-08-14 (s11, arbitrage prealable rendu) - le perimetre couvre trois ecrans et non un, le chiffre de 2026-08-07 est perime, verdict de nadia-prof : principe accepte sous six conditions, forme instruite refusee

`nadia-prof` a rendu l'arbitrage prealable exige au point 2 ci-dessus
(`squad/conformite/FICHE-bandit-manchot-additions-cp.md`, section "Arbitrage prealable au
developpement - la sortie unique dans l'en-tete (DEC-0027, s11), du 2026-08-14"). J'ai verifie
moi-meme, en lisant les gabarits, les deux faits qui corrigent cette decision.

**1. Le perimetre ecrit ci-dessus est faux, et c'est le point qui compte le plus.** J'avais ecrit,
dans "Ce que je trouve en verifiant" : "`fragments/retour.html` ne rend plus que la fin de partie,
avec sa propre sortie `sortie-pleine`, hors du perimetre de cette demande." C'est exact du
**fragment** - `button.btn.retour-accueil`, le bouton que la demande retire, n'existe que dans
`fragments/manche.html` - et faux de l'**ecran** : `fragments/retour.html` (ligne 11) et
`fragments/erreur.html` (ligne 7) portent tous deux `class="ecran-de-jeu"`, le meme marqueur que
`fragments/manche.html` (ligne 28). Lever `body:has(.ecran-de-jeu) .lien-accueil { display: none }`
demasque donc le lien de l'en-tete sur les **trois** ecrans qui portent ce marqueur, pas sur un
seul. Le perimetre reel de cette demande est : manche fraiche, echec et revelation
(`fragments/manche.html`), fin de partie (`fragments/retour.html`), erreur
(`fragments/erreur.html`).

Consequences mesurees par `nadia-prof`, que je reprends sans les rejuger : sur l'ecran de fin de
partie, "Les jeux" apparaitrait deux fois - le lien de l'en-tete et l'aplat `sortie-pleine`, a 49 px
l'un de l'autre - et le bandeau qui passe de 76 a 85 px ferait descendre `sortie-pleine` de 9 px au
lieu de lui faire gagner quoi que ce soit ; sur l'ecran d'erreur, "Les jeux" apparaitrait a cote de
"Recommencer", deux mots pour une seule destination, ce qui defait l'acquis de S6-9 - un seul mot
de sortie dans toute l'application.

**2. Le chiffre "102 x 53 px" que je cite tel quel ci-dessus, dans "Ce que je trouve en
verifiant", est perime.** Il datait de la mesure faite par `nadia-prof` le 2026-08-07 sur le
libelle "Accueil", que S6-9 a depuis remplace par "Les jeux", plus large. Remesure par elle
aujourd'hui : **109 x 53 px**, soit la boite exacte du bouton que cette demande retirerait. Il n'y
avait donc aucun ecart de taille a opposer sur une manche - voir la fiche, section "0 bis", point
2. Le meme chiffre perime figure aussi dans `ADR-0022` (`theo-archi`, 2026-08-07) ; je ne corrige
pas ce document, qui n'est pas le mien, et je le signale a `sofia-sm` pour routage.

**Verdict de `nadia-prof`, que je ne rends pas moi-meme et que je reprends tel qu'ecrit** : "la
forme instruite est REFUSEE. Le principe est ACCEPTE sur les seuls etats rendus par
`fragments/manche.html`, sous six conditions PORTE-1 a PORTE-6." Voir
`squad/conformite/FICHE-bandit-manchot-additions-cp.md`, section citee ci-dessus, pour le detail
des six conditions et leur motif ; je ne les reproduis pas ici pour ne pas dupliquer un texte qui
n'est pas le mien.

**Ce que cet amendement ne change pas.** Rien du corps de la decision ci-dessus n'est efface : le
perimetre et le chiffre errones restent lisibles tels qu'ecrits le 2026-08-14, cette section dit
seulement ce qui est correct a compter d'aujourd'hui. Le point 3 ci-dessus reste vrai sans
changement : cette decision ne prononce toujours aucun jugement pedagogique par elle-meme, c'est
`nadia-prof` qui vient de le rendre, dans sa fiche.

### Impact de cet amendement

- `docs/backlog/backlog.md` : la story "Gain vertical - retrait du bouton de sortie du fragment de
  manche" est reecrite avec le perimetre corrige (trois ecrans) et les six conditions PORTE-1 a
  PORTE-6 portees en criteres d'acceptation.
- `squad/decisions/produit/DEC-index.md` : note d'amendement ajoutee, statut de la ligne DEC-0027
  mis a jour.
- `squad/programme/MATRICE-couverture.md` : inchangee, comme au point 5 de la decision d'origine.
- `squad/conformite/FICHE-bandit-manchot-additions-cp.md` : inchangee par cet amendement, qui ne
  fait que reprendre et referencer le verdict que `nadia-prof` y a deja ecrit.
- Signale a `sofia-sm`, hors de ce fichier : le chiffre "102 x 53 px" perime dans `ADR-0022`, pour
  routage vers `theo-archi`.

## Amendement du 2026-08-17 (s14, PORTE-1 fermee par DEC-0033)

**PORTE-1 restait ouverte depuis le 2026-08-14** : le constat que le lien "Les jeux" de l'en-tete
rend, depuis l'accueil, un ecran identique a celui qui l'affiche - `nadia-prof` avait ecrit que
"deux issues [lui] conviennent : cette boite disparait de l'accueil, ou elle y fait quelque chose",
sans trancher, le choix restant a `camille-po`.

`S14-1` (barre d'entree par niveau, sprint 14) installe deux puces de niveau dans la meme zone
d'en-tete, qui repondent l'une et l'autre, et rend cette condition **bloquante** sous le nom
**BARRE-7** (`squad/conformite/FICHE-barre-entree-niveau.md`, section 6, arbitrage du 2026-08-17) :
le Done pedagogique de `S14-1` ne pouvait pas etre prononce tant que le choix entre les deux issues
n'etait pas fait.

**`DEC-0033-barre-7-le-lien-les-jeux-de-l-en-tete-disparait-de-l-accueil.md` tranche : la premiere
issue est retenue.** Le lien de l'en-tete est retire de tout ecran, y compris l'accueil, plutot que
de lui donner un comportement neuf - motif detaille dans cette decision. PORTE-1 est donc **fermee**
par `DEC-0033`, pas par le present document : rien dans les sections d'origine de `DEC-0027`
ci-dessus n'est modifie par cet amendement, qui se limite a dater la fermeture et a renvoyer vers la
decision qui la prononce.

### Impact de cet amendement

- `docs/backlog/backlog.md` : story neuve `S14-2`, section "Sprint 14", portant le retrait du lien
  et ses criteres d'acceptation.
- `squad/decisions/produit/DEC-index.md` : note d'amendement ajoutee.
- `squad/conformite/FICHE-barre-entree-niveau.md` : inchangee par cet amendement ; le verdict sur
  BARRE-7 y reste celui deja ecrit par `nadia-prof`.

## Note du 2026-08-17 (s15) - la fermeture de PORTE-1 ne reflete plus l'etat du depot

Cet amendement enregistrait une fermeture de PORTE-1 par le retrait du lien. **Cette fermeture ne
reflete plus l'etat du depot** : l'utilisatrice, apres avoir joue l'increment livre, retablit le
lien "Les jeux" dans l'en-tete dans son etat d'avant s14, en connaissance du motif meme de PORTE-1
(`DEC-0034-utilisatrice-retablit-les-jeux-et-unifie-lapparence-des-puces.md`). Le paragraphe
ci-dessus n'est pas corrige : au moment ou `DEC-0033` l'a ecrit, la fermeture etait exacte. Ce qui
change est posterieur, rendu par une autorite au-dessus de `nadia-prof` et de `camille-po`, et ce
n'est pas une reouverture au sens ou la squad l'entend habituellement - personne ne remet en cause
le motif de `nadia-prof`, l'utilisatrice choisit de vivre avec.

## Note du 2026-08-18 (s15) - un troisieme arbitrage va plus loin que ce que le principe accepte ici couvrait, et il est juge sous une forme neuve

L'amendement du 2026-08-14 enregistrait un verdict de `nadia-prof` qui **acceptait le principe**
d'une sortie unique dans l'en-tete, **mais seulement sur les etats de `fragments/manche.html`**, et
**refusait la forme instruite** parce qu'elle demasquait aussi la confirmation et l'erreur, ou elle
aurait fait lire "Les jeux" deux fois ou aux cotes d'un second mot de sortie.

Le 2026-08-18, l'utilisatrice rend un arbitrage qui va plus loin que ce que ce principe couvrait :
la sortie d'en-tete reste visible pendant une manche **sans limitation aux six conditions PORTE-1 a
PORTE-6** que cet amendement portait, et sous une **forme technique neuve** - un second marqueur
structurel (`ecran-porte-sa-sortie`) qui masque le lien sur la confirmation et la fin de partie
sans reproduire le defaut que la forme refusee ici aurait cause. Cette forme n'est ni celle que
`DEC-0027` avait instruite, ni couverte par son verdict : elle est jugee pour la premiere fois par
`nadia-prof` sur l'increment `b109f98`, avec un verdict **VALIDE SOUS RESERVE** distinct de celui
enregistre ci-dessus. Le detail complet, la chronologie et ce que cet arbitrage coute sont dans
`DEC-0035-trois-arbitrages-de-s15-non-traces-sortie-permanente-rang-unique-etat-actif.md`,
arbitrage C.

**Ce que cette note ne fait pas** : elle ne retire ni ne corrige le verdict du 2026-08-14 ci-dessus,
qui reste exact pour la forme qu'il jugeait a l'epoque. Elle situe seulement l'arbitrage de s15 par
rapport a lui, pour qu'un lecteur ne confonde pas les six conditions PORTE avec les conditions
posees par `nadia-prof` sur la forme livree en s15.
