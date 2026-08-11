# DEC-0018 - L'activation de "Le bon verbe" est corrigee dans le sprint plutot que retiree, denouement d'un second refus, et sort de la ligne de matrice

- **Type** : perimetre produit, suite d'un refus pedagogique
- **Date** : 2026-08-11
- **Auteur** : camille-po
- **Sprint** : s08
- **Competence programme visee** : francais, CM1 (cycle 3), domaine "Orthographe et grammaire" -
  "Conjuguer les verbes frequents au present, futur et imparfait de l'indicatif"
  (`squad/programme/francais/competences-cm1.md`).
- **Statut** : Acceptee

## Contexte

`nadia-prof` a rendu un troisieme audit sur `bandit-manchot-conjugaison-present-cm1`, sur
l'increment livre le 2026-08-11 (commit `533967d`, tete de `round-two`, chantier 2 de `DEC-0017`
puis S8-4) : **REFUSE**
(`squad/conformite/FICHE-bandit-manchot-conjugaison-present-cm1.md`, verdict du 2026-08-11).

Ce verdict porte deux jugements distincts, qu'elle separe elle-meme et que je reprends sans les
confondre :

1. **Sa barre fermee de six points, ecrite le 2026-08-10 pour l'ouverture du futur et de
   l'imparfait (S8-4), est LEVEE.** Les six points sont satisfaits, verifies un par un, et
   depasses sur le point 2. Elle ecrit nommement : "le contenu de S8-4, [...] que je valide et
   dont je dis le bien". Ce jugement n'est pas ce qui motive le refus.
2. **Le motif du refus, unique, chiffre et neuf, porte sur l'activation de ce jeu au catalogue.**
   A 360 px de largeur reelle, dans le cabinet livre le meme jour (S8-1), les quatre colonnes de ce
   paquet mesurent 152,00 px et deux propositions du paquet sont plus larges que leur propre
   boite :

   | Proposition | Largeur des glyphes | Boite du bouton | Air de chaque cote |
   |---|---|---|---|
   | `mangeons` | 156,02 px | 152,00 px | **-2,02 px** |
   | `cherchons` | 152,17 px | 152,00 px | **-0,09 px** |

   `mangeons` s'ecrit sur la paroi du cabinet, dans les deux colonnes ou il apparait. Ce n'est pas
   une troncature - rien n'est coupe ni comprime - c'est une reponse qui sort de la chose qu'il
   faut presser.

`nadia-prof` nomme elle-meme trois sorties possibles pour ce motif, sans choisir entre elles :
reduire le paquet a des formes plus courtes (jugement pedagogique, elle-meme), ne pas mettre
quatre colonnes dans le cabinet a 360 px (`theo-archi`), ou retirer le jeu du catalogue le temps
de trancher sa disposition (moi). **L'utilisatrice a tranche cette question directement, en
dehors de cette fiche** : le sprint corrige la disposition a quatre colonnes plutot que de
retirer l'activation. Je ne rouvre pas ce choix et je ne propose pas d'alternative ; je le
redige, avec ce qu'il implique et ce qui reste a decider autour de lui.

**Ce que cette decision n'est pas : une reconduction automatique de `DEC-0014`.** La derniere fois
que ce jeu a ete refuse (2026-08-10, premier audit), j'ai retire "Le bon verbe" du catalogue le
temps de sa correction. La direction retenue aujourd'hui est differente - corriger dans le sprint,
sans retrait - et c'est un choix de l'utilisatrice, pas une reevaluation de ma part du motif de
`DEC-0014` ni de son mecanisme, que je reprends d'ailleurs a l'identique pour le denouement d'un
second refus (section suivante).

## Decision

**1. Direction du sprint, non rouverte.** Le sprint 8 corrige la disposition a quatre colonnes du
cabinet pour "Le bon verbe" plutot que de retirer le jeu du catalogue pendant la correction.
Execution : story `S8-5` de `docs/backlog/backlog.md`, section "Sprint 8".

**2. Perimetre exact de la correction.**

Ce qui est couvert par `S8-5`, et seulement cela :

- que chaque proposition du paquet `cm1-conjugaison-present.yaml`, glyphes compris, tienne a
  l'interieur de sa colonne de 152,00 px a 360 px de largeur reelle, sans troncature ni
  compression - en particulier `mangeons` et `cherchons` ;
- que les propositions deja conformes ne se degradent pas.

Ce que `S8-5` ne rouvre pas :

- **la barre fermee de six points de S8-4** : elle est levee, verifiee point par point par
  `nadia-prof` le 2026-08-11, et ne repasse pas devant elle a ce titre ;
- **le defaut de pli** que `DEC-0016` regit et que son amendement du 2026-08-11 traite
  separement ;
- **la reserve de contraste interieur du cabinet** (1,71:1), reserve de forme non bloquante,
  suivie dans `docs/backlog/backlog.md`, section "Autres intentions" ;
- **le contenu grammatical, les enonces, les distracteurs ou les indices** des vingt-cinq items,
  deja juges exacts.

Le moyen retenu pour tenir la colonne (raccourcir des formes, revoir le budget de colonne,
tout autre moyen) n'est pas prescrit ici : c'est une question technique et de forme, a
`theo-archi` et `ines-design`.

**3. Denouement d'un second refus - les deux issues, decidees maintenant.**

- **Si le second verdict de `nadia-prof` sur l'increment corrige n'est pas un REFUSE** (`VALIDE`
  ou `VALIDE SOUS RESERVE`) : "Le bon verbe" reste au catalogue sans autre geste requis par cette
  decision. Toute reserve non bloquante qu'elle nommerait suit le traitement ordinaire (backlog,
  non bloquant).
- **Si le second verdict est de nouveau REFUSE** - que le motif soit une recidive du meme defaut
  de largeur ou un motif nouveau - **"Le bon verbe" est retire de
  `adapters/adapter-content/src/main/resources/catalogue.yaml`, dans le meme geste que ce second
  verdict est enregistre**, par le mecanisme deja pose par `DEC-0009` et `DEC-0014`. Cette
  execution est routee a un developpeur par `sofia-sm` au moment du verdict, sans attendre une
  nouvelle decision de perimetre : je la pose maintenant precisement pour ne pas reproduire le
  defaut nomme par `DEC-0009` en s04 - une decision de retrait ecrite mais non executee, qui a
  laisse un jeu refuse jouable jusqu'a ce qu'un tiers le remarque.

Cette decision ne prejuge pas laquelle des deux issues se produira ; elle fixe seulement, pour
chacune, ce qui en decoule, afin que le second verdict n'ait pas besoin d'un nouveau `DEC` pour
etre applique.

**4. Ligne de matrice - francais CM1, conjugaison.**

**La ligne reste `en cours`.** `nadia-prof` ecrit explicitement que sa barre levee "autorise" le
passage a `couvert-valide`, sans elle-meme trancher le moment, et me laisse cette decision. Je
choisis d'attendre le second verdict de la section 3, pour trois raisons :

- La colonne "Statut" de cette matrice est definie par "`nadia-prof` a rendu un verdict `VALIDE`
  sur la fiche de conformite correspondante" (`squad/programme/MATRICE-couverture.md`, "Comment la
  lire"). Le verdict actuellement inscrit en tete de cette fiche, sur ce commit, est `REFUSE`. Je
  ne fais pas dire a la matrice qu'une fiche activement refusee couvre une competence de facon
  validee : un lecteur qui ne descend pas jusqu'a la section 1 du verdict lirait le contraire de
  ce qui est vrai aujourd'hui.
- Le precedent de mathematiques CP montre que `couvert-valide` n'a jamais exige un verdict
  global sans aucune reserve (le Done y restait `VALIDE SOUS RESERVE`) ; il montre en revanche
  qu'il n'a jamais ete accorde sous un verdict global `REFUSE`. Je tiens cette meme regle ici :
  le contenu est juge et la barre est levee, ce que je ne conteste pas, mais le verdict global de
  cet increment reste `REFUSE` et je ne le contourne pas en isolant la seule partie qui m'arrange.
- La gouvernance de ce depot a deja paye le prix de statuts deplaces puis repris sans y avoir ete
  contraints (retro s05, s06). Attendre un verdict propre avant de bouger la ligne evite d'avoir a
  la reculer si le second audit refuse de nouveau, ce qui serait le cas si je la faisais avancer
  aujourd'hui.

**Ce que je decide pour la suite, afin que ce ne soit pas a redemander** : au premier verdict de
`nadia-prof` sur l'increment corrige de `S8-5` qui n'est pas un `REFUSE`, la ligne passe a
`couvert-valide` dans le meme geste, sans que la barre en six points de S8-4 soit rouverte ou
reauditee - elle reste levee depuis le 2026-08-11. Si ce second verdict est de nouveau `REFUSE`,
la ligne reste `en cours`, au meme rythme que le retrait de la section 3.

## Justification pedagogique

Transverse pour l'essentiel : le motif refuse porte sur l'affichage, pas sur un contenu. Le seul
point pedagogique de cette decision est le choix d'attendre un verdict propre avant de faire
avancer la ligne de matrice, et il ne juge aucun contenu - il tient la coherence de ce que la
matrice affirme. Le jugement sur le contenu lui-meme, deja rendu par `nadia-prof`, n'est ni repris
ni conteste ici.

## Impact

- `docs/backlog/backlog.md`, section "Sprint 8" : nouvelle story `S8-5`.
- `squad/validation/FILE-ATTENTE.md` : `bandit-manchot-conjugaison-present-cm1` rentre dans la
  file, en attente de reprise (1 increment en attente), corrige dans le meme geste que cette
  decision.
- `squad/programme/MATRICE-couverture.md` : note ajoutee sur la ligne francais CM1 (conjugaison)
  pour refleter ce troisieme audit ; la ligne elle-meme ne change pas de statut.
- `adapters/adapter-content/src/main/resources/catalogue.yaml` : inchange par cette decision ;
  son edition eventuelle (retrait) n'aura lieu qu'au second verdict, si celui-ci est un `REFUSE`
  (section 3).
- `squad/decisions/produit/DEC-index.md` : ligne DEC-0018 ajoutee.

## Ce que cette decision ne fait pas

- Elle ne rouvre pas la direction tranchee par l'utilisatrice (corriger plutot que retirer) : je
  la redige, je ne la redebats pas.
- Elle ne tranche aucune question technique sur la maniere de faire tenir les propositions dans
  leur colonne : `theo-archi` et `ines-design`.
- Elle ne rouvre pas la barre en six points de S8-4, ni le defaut de pli de `DEC-0016`, ni la
  reserve de contraste du cabinet : chacun suit son propre traitement, nomme ci-dessus.
- Elle ne prejuge pas du contenu du second verdict de `nadia-prof` : elle fixe seulement ce qui en
  decoule dans chacun des deux cas.
- Elle ne fait passer aucune autre ligne de `squad/programme/MATRICE-couverture.md` a
  `couvert-valide` : l'objectif du sprint reste gele (`DEC-0017`).
