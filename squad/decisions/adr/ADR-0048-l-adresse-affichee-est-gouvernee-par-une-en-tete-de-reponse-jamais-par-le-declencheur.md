# ADR-0048 - Faire gouverner l'adresse affichee par une en-tete de reponse, pour qu'un declencheur unique garde sa double responsabilite

- **Statut** : **Propose.** Il devient `Accepte` dans le commit du lot de `S16-2`, avec sa regle. Meme
  motif qu'`ADR-0047` : la regle porte sur une en-tete que la production ne pose pas encore.
- **Date** : 2026-08-19
- **Auteur** : theo-archi
- **Sprint** : s16 (avis de faisabilite, avant tout commit de code)
- **Complete** : `ADR-0020` (l'unite de verification est le document assemble), `ADR-0038` et
  `ADR-0042` (aucun arret destructeur, la confirmation), `ADR-0044` (le point d'entree est une route)
- **Regle executable** : due par le lot de `S16-2`, enoncee au mot ci-dessous.

## Contexte

L'utilisatrice filtre sur CM1, appuie sur "Les jeux", obtient les onze jeux - et l'adresse affichee
reste celle du niveau quitte. Recharger cette adresse rend une liste filtree que l'enfant ne regarde
plus. `S16-2` demande que l'adresse corresponde a ce qui est affiche.

**L'asymetrie vient de mon propre arbitrage de s15, et il faut le dire avant de proposer quoi que ce
soit.** Pour que la puce de niveau puisse porter un etat actif, j'ai fait des puces des **navigations
completes** (`ADR-0044`, amendement du 2026-08-18) : le serveur rend alors l'en-tete, donc l'adresse
suit. "Les jeux" est reste un **echange htmx**, parce qu'il porte une seconde responsabilite que les
puces n'ont pas.

**Cette seconde responsabilite est la contrainte dure du probleme.** `hx-post="/retour"` ouvre l'ecran
de confirmation quand une partie est en cours (`DEC-0026`, `ADR-0038` point 5, `ADR-0042`) et ne
detruit rien lui-meme. Un lien nu vers `/` perdrait cela : une navigation complete vers `/` pendant une
partie rend la manche par reprise, jamais la confirmation - l'enfant quitterait sans qu'on le lui
demande, ou bien ne quitterait pas du tout. **Le declencheur ne peut donc pas cesser d'etre un echange
htmx, et l'adresse doit pourtant changer.** Ces deux exigences ne se contredisent que si l'on croit que
l'adresse est une propriete du declencheur.

## Decision

**1. L'adresse affichee est gouvernee par une en-tete de la reponse, decidee par le serveur, jamais par
un attribut du declencheur.** Le declencheur reste ce qu'il est - un echange htmx vers la route de
retour. C'est la **reponse** qui dit, quand elle rend le catalogue complet, que l'adresse a montrer est
celle du catalogue complet ; et qui ne dit rien quand elle rend l'ecran de confirmation.

**2. La condition est l'ecran rendu, jamais le geste.** Le serveur sait, et lui seul, laquelle des deux
reponses il produit : la confirmation si une partie est en cours, le catalogue sinon. La meme branche
qui choisit le fragment choisit de poser ou non l'en-tete d'adresse. **Une seule condition, deja
ecrite, gouverne les deux** - il n'y a pas de second predicat a tenir en accord avec le premier.

**3. Pourquoi une en-tete de reponse et pas un attribut du declencheur.** Un attribut serait pose une
fois pour toutes sur l'element, donc **inconditionnel** : l'adresse deviendrait celle du catalogue
complet meme quand la reponse est un ecran de confirmation, c'est-a-dire au moment ou l'enfant n'a rien
quitte du tout. L'adresse mentirait exactement dans le cas que cette story vient corriger, en sens
inverse.

**4. Pourquoi cette en-tete ne rouvre pas la dette d'`ADR-0020`, contrairement a l'echange hors
bande.** J'ai ecarte `hx-swap-oob` deux fois, en s14 puis en s15, sur un motif precis : le garde-fou
d'`ADR-0020` reimplemente l'echange en posant le fragment **dans** la zone, donc une copie hors bande
de l'en-tete serait assemblee imbriquee, et le document assemble est la matiere des quatre clauses
d'`ADR-0038`, de l'appariement d'`ADR-0017` et de la clause d'unicite de la sortie visible. **Une
en-tete de reponse ne touche pas le corps** : le fragment rendu est exactement celui d'aujourd'hui,
l'assemblage est inchange, et aucune clause qui lit un document ne voit la difference. C'est la seule
raison pour laquelle j'accepte ici un mecanisme de la meme famille que celui que j'ai refuse deux
fois, et elle est structurelle, pas de circonstance.

**5. Ce que la decision ne fait pas.** Elle ne change ni le mot "Les jeux", ni la route, ni le
comportement de confirmation, ni la maniere dont les puces naviguent. Elle ne rend pas l'adresse
gouvernable par le client : aucune ligne de JavaScript maison n'entre, et le budget d'`ADR-0008` n'est
pas touche.

## Regle executable, enoncee au mot pour le lot de S16-2

> **La reponse de la route de retour declare l'adresse du catalogue complet si et seulement si elle
> rend le catalogue complet ; elle ne la declare pas quand elle rend l'ecran de confirmation.**

- **Perimetre derive** : les deux reponses viennent de vraies requetes sur de vraies sessions - une
  avec partie en cours, une sans - construites comme le corpus d'`ADR-0038` construit les siennes. Le
  test ne nomme aucun ecran par son nom de fichier : il distingue les deux cas par l'**etat de session**
  mesure, comme `partieEnCoursSur` le fait deja.
- **Les deux sens, parce qu'un seul ne prouverait rien** : l'absence de l'en-tete sur la confirmation
  est aussi importante que sa presence sur le catalogue. Un lot qui poserait l'en-tete
  inconditionnellement passerait un test ecrit dans un seul sens, et l'adresse mentirait sur l'ecran de
  confirmation.
- **Derivation totale** : une reponse qui declare une adresse vide, ou qui en declare une sur un
  troisieme ecran qu'aucun de ces deux cas ne produit, sont des **echecs**, jamais des cas ignores.
- **Mise a l'epreuve** : la production d'aujourd'hui ne pose aucune en-tete d'adresse, donc le sens
  "presence" est rouge avant le correctif - l'etat du depot fournit la moitie de l'epreuve. Pour
  l'autre moitie, la mutation est l'en-tete posee inconditionnellement, sur du materiau reel et non sur
  une fixture.
- **Ce n'est pas une regle ArchUnit**, et le compte reste a **29** : ArchUnit ne voit pas une en-tete de
  reponse. Le mecanisme est un test de `adapter-web`.

## Consequences

- **Le declencheur garde sa double responsabilite sans qu'aucun garde-fou d'`ADR-0038` ne bouge** : la
  requete rejouee est la meme, son effet sur la session est le meme, donc l'arret reste non destructeur
  et les quatre clauses restent vertes sans etre relues.
- **Une nouvelle famille de couplage entre le serveur et le navigateur entre dans le depot** : une
  en-tete de reponse. C'est la consequence la plus serieuse et je ne l'habille pas - jusqu'ici, tout ce
  que le serveur disait au navigateur passait par le corps ou par un attribut de gabarit, tous deux
  lisibles par les garde-fous existants. Une en-tete n'est lue par aucun d'eux, sauf par la regle
  ci-dessus. **Ce qui rend le risque acceptable** : cette en-tete ne gouverne que l'adresse affichee,
  jamais un contenu ni un etat de partie ; un navigateur qui l'ignorerait rendrait exactement le
  comportement d'aujourd'hui, c'est-a-dire le defaut, jamais un ecran casse. **Ce qui me ferait changer
  d'avis** : une seconde en-tete de reponse qui gouvernerait autre chose que l'adresse. Il faudrait
  alors un garde-fou qui recense les en-tetes que le serveur pose, comme le depot recense les routes,
  plutot qu'une regle par en-tete.
- **`ADR-0044` n'est pas rouvert.** Son point 2 (le point d'entree est une route) et son point 4 (le
  niveau ne vit que dans l'URL) sont ce sur quoi cette decision s'appuie ; son point 3, deja amende le
  2026-08-18, parle du mode de rendu de l'**en-tete de page**, que cette decision ne touche pas. **La
  population de masquage ne bouge pas non plus** : aucun element ne gagne ni ne perd d'ecran.

## Alternatives considerees

1. **Faire de "Les jeux" un lien nu.** Ecartee : elle perd la confirmation, donc elle rouvre le defaut
   que `DEC-0026` a ferme et que `nadia-prof` avait refuse. C'est la seule alternative que la story
   nomme, et c'est celle qu'il faut ecarter explicitement.
2. **Un attribut de poussee d'adresse sur le declencheur.** Ecartee au point 3 : inconditionnelle, donc
   fausse sur l'ecran de confirmation.
3. **`hx-swap-oob` sur l'en-tete.** Ecartee pour la troisieme fois, meme motif, developpe au point 4.
4. **Une redirection cote serveur.** Ecartee : elle remplacerait le corps de la reponse, donc le
   fragment que le corpus assemble deviendrait vide, et les clauses qui lisent le document assemble
   jugeraient un ecran qui n'existe pas. C'est le seul mecanisme voisin qui casserait vraiment quelque
   chose, et il faut le dire pour qu'il ne soit pas essaye.
5. **Deux declencheurs, un par cas.** Ecartee : l'enfant verrait deux boutons "Les jeux", ce que
   l'unicite du mot de sortie interdit (`ADR-0022` point 3), et le choix entre les deux dependrait du
   client alors que seul le serveur connait l'etat de la partie.

## Ce qui reste non gardable, et le dit

- **Que le navigateur affiche reellement l'adresse declaree.** Le garde-fou lit ce que le serveur pose,
  jamais la barre d'adresse - meme limite qu'`ADR-0022` pour son selecteur relationnel et qu'`ADR-0038`
  pour le rebouclage de tabulation.
- **Qu'un enfant tire un benefice de l'adresse.** Recharger, garder un signet, retrouver la page :
  jugement de `nadia-prof` sur l'increment livre, et `S16-2` le lui demande nommement.
