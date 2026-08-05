# [Sprint s03] Planning - 2026-08-05

- **Horodatage de debut** : 2026-08-05 19:05
- **Anime par** : sofia-sm
- **Participants** : theo-archi, camille-po, lea-dev, ines-design, marc-dev, yanis-qa, sofia-sm

## Objectif du sprint

Completer la ligne mathematiques CP de `MATRICE-couverture.md` (S3-1 a S3-3 : repartition des
sommes, indice sur trois echecs consecutifs quel que soit l'item, indice deplace dans le contenu
YAML) et lever les six reserves visuelles et redactionnelles restantes de `nadia-prof` sur
`bandit-manchot-additions-cp` (S3-4 a S3-7, S3-9, plus le seul prealable testable de S3-8 : la
balise viewport manquante), pour que l'increment repasse devant `nadia-prof` en fin de sprint et
que le statut `couvert-valide` de cette ligne soit tranche - dans un sens ou dans l'autre - plutot
que reporte une troisieme fois. **Gele : la story S3-8 complete n'entre pas dans cet objectif tant
que `camille-po` n'a pas rearbitre ses criteres (voir "Sequencement et blocages")** ; seul son
prealable technique (balise viewport) y entre.

## Stories retenues

| Story | Titre | Competence programme visee | Decision liee | Affectee a | Statut au planning |
|---|---|---|---|---|---|
| S3-1 | Des additions qui travaillent tout l'intervalle | mathematiques CP, calcul mental additions <=10 | DEC-0007 pt.2, amendement DEC-0004 pt.5 a trancher | lea-dev | **bloquee** : attend l'arbitrage de camille-po sur le seuil |
| S3-2 | Un indice atteignable dans la partie en cours | transverse | DEC-0007 pt.3, DEC-0004 pt.4 (inchange) | lea-dev | prete |
| S3-3 | L'indice vit dans le contenu, pas dans le domaine | transverse | DEC-0005 | lea-dev | prete |
| S3-4 | Le nombre doit peser plus que le bouton | transverse | reserve 1, S2-1 | ines-design | prete |
| S3-5 | Un ecart confortable entre deux boutons | transverse | reserve 2, S2-1 ; DEC-0007 pt.4 | ines-design | prete pour le code, critere a reformuler par camille-po avant que yanis-qa l'automatise |
| S3-6 | La sortie doit se voir sur fin et erreur | transverse | reserve 4, S2-1/S2-2 | ines-design | prete, contrainte d'implementation notee ci-dessous |
| S3-7 | Une icone qui dessine un objet | transverse | reserve, S2-1 | ines-design | prete |
| S3-8 | Jouable a largeur telephone (375px) | transverse | DEC-0007 pt.5, rearbitrage a venir | marc-dev (prealable seul) | **bloquee** sur son critere central ; le prealable viewport est detache et demarre maintenant |
| S3-9 | "Recreo" devient "Récréo" | transverse | DEC-0007 pt.7, reserve 10 | ines-design (texte), yanis-qa (dette orpheline) | prete |

Neuf stories sont ecrites au backlog ; huit sont codables des maintenant en tout ou partie (S3-2,
S3-3, S3-4, S3-5, S3-6, S3-7, S3-9 pleinement, S3-8 pour son seul prealable). Trois developpeurs
actifs (`lea-dev`, `ines-design`, `marc-dev`) plus `yanis-qa` sur les tests et la dette : c'est la
meme taille d'equipe qu'au sprint 1, qui avait absorbe neuf stories similaires. **Je ne retire donc
aucune story pour une raison de capacite** - ce n'est pas ma decision de prioriser, et rien
n'indique que l'effectif est le facteur limitant. Le facteur limitant reel est ailleurs : S3-1 et
la partie non-prealable de S3-8 dependent d'un arbitrage de `camille-po` qui n'existe pas encore a
l'heure de ce planning. Si ces arbitrages tardent, l'increment reellement livre sera plus petit que
les neuf stories ecrites - je le dis maintenant plutot que de le decouvrir a la review.

`sofia-sm`, casquette dev : aucune tache de code affectee ce sprint, comme au sprint 1. Elle merge
les trois worktrees et route les obstacles.

## Avis de faisabilite - theo-archi

Reproduit et condense depuis `squad/sprints/s03-completer-mathematiques-cp/avis-faisabilite.md`,
qui fait foi sur le detail. **Aucun ADR nouveau requis pour les neuf stories.**

| Story | Avis | Point non tranche par l'architecture |
|---|---|---|
| S3-1 | Faisable, mais deux des quatre criteres se contredisent tels qu'ecrits (mesure sur 200 000 tirages) | seuil de `DEC-0004` pt.5 a amender - route camille-po |
| S3-2 | Faisable, deux pieges d'implementation nommes | aucun, implementation uniquement |
| S3-3 | Faisable, le chargeur unique l'accepte sans nouvelle fabrique | aucun |
| S3-4 | Faisable, comparer `.proposition` a `.enonce`, jamais au litteral `2` | aucun |
| S3-5 | Faisable ; "ecart reel entre les boites" n'est mesurable par aucun automate du depot, seule la declaration `gap` l'est | reformulation du critere - route camille-po |
| S3-6 | Faisable ; contrainte forte du garde-fou corrige de `yanis-qa` (font-size + min-height par classe) | aucun, implementation uniquement |
| S3-7 | Faisable, y compris le critere de vignette 16px | aucun |
| S3-8 | Faisable en partie seulement ; le critere central demande un navigateur sans tete, non recommande ce sprint ; le prealable (balise viewport absente) est testable des maintenant | perimetre et reecriture des criteres - route camille-po |
| S3-9 | Faisable, piege de balayage nomme (le mot "recreo" est partout dans les chemins de ressources, hors sujet) | aucun |

## Sequencement et blocages

1. **Rien ne demarre sur S3-1 avant que `camille-po` ait tranche le seuil `DEC-0004` pt.5.**
   `theo-archi` a mesure, sur 200 000 tirages, que le tirage uniforme sur les 65 couples seul
   viole le critere d'operandes nuls (30,7 %), et que le filtre actuel (1 tirage nul accepte sur 5,
   8,1 %) fait sortir les sommes 1 et 10 de leurs fourchettes. Sa sortie mesuree : porter le filtre
   a 1 sur 3 (12,92 % d'operandes nuls, exactement le reglage deja valide par `nadia-prof` en s01)
   satisfait les quatre criteres a la fois, **a condition de recalculer la table de cibles sur le
   tirage compose et non sur le tirage nu** (sinon 4 graines sur 10 echouent sur un test a graine
   unique). C'est un changement de seuil de `DEC-0004`, donc **camille-po tranche, pas moi ni
   theo-archi**. Je route avec le materiau mesure ci-dessus ; je n'anticipe pas sa decision.
   `theo-archi` recommande aussi que le test tourne sur un jeu de graines declare (0 a 9) plutot
   qu'une graine unique - point de forme pour `yanis-qa`, pas un prealable au demarrage.

2. **S3-2 s'appuie sur la longueur de partie deja fixee par `ADR-0012`** (`nombreDeManches: 10`
   dans `catalogue.yaml`) : rien a changer la, mais `lea-dev` doit connaitre deux pieges nommes par
   `theo-archi` avant de coder :
   - **le compteur `echecsConsecutifsApres` est remis a zero avant que l'indice soit calcule** dans
     `BanditManchotSession.tirerManche` (la "manche plus simple" s'applique en premier, ligne 120,
     l'indice se calcule apres, ligne 131). Une implementation naive qui lirait la valeur
     **sortante** du compteur echouerait precisement le quatrieme critere de la story (reussite
     puis trois nouveaux echecs) ; l'indice doit se calculer sur la valeur **entrante**
     `echecsConsecutifs`.
   - **`echecsParItem` et `SEUIL_ECHECS_SUR_LE_MEME_ITEM` doivent partir dans le meme commit** :
     l'ancien declencheur devient mort des que le nouveau (trois echecs consecutifs, tout item)
     est en place, et `BanditManchotSession` porte deja quatorze parametres de constructeur -
     c'est l'occasion d'en retirer un plutot que d'en ajouter.
   - Recommandation non bloquante de `theo-archi` : publier le seuil minimal de manches en dessous
     duquel l'indice ne peut pas s'afficher, et l'asserter contre le vrai catalogue dans
     `CatalogueChargeableIT` plutot que d'ecrire `4` en dur dans un test. Je la transmets a
     `lea-dev`, je ne l'impose pas : c'est un choix d'implementation, pas une condition de
     faisabilite.

3. **S3-8 n'entre dans le sprint que par son prealable.** `theo-archi` ne recommande pas d'ajouter
   un navigateur sans tete en CI pour trois criteres visuels (premiere dependance hors JDK et
   Spring) ; ce n'est pas a lui ni a moi de trancher si un jour ce cout se justifie - route a
   `camille-po` (perimetre) et `theo-archi` / `yanis-qa` (outillage, deja route en obstacle
   ouvert depuis s02). Ce qui **est** actionnable maintenant, sans attendre cet arbitrage :
   `accueil.html` ne porte **aucune** balise `<meta name="viewport">`, ce que `theo-archi` juge
   plus important que le reste de la story ("un telephone rend la page a environ 980px virtuels
   puis reduit, la mise en page a 375px n'est jamais exercee sur un vrai telephone"). C'est un
   defaut reel, independant du debat de perimetre, et il est testable par automate des maintenant
   (presence de la balise, `width=device-width`, plus l'absence de largeur fixee en pixels au-dela
   de la cible, garde-fou a perimetre derive - `git ls-files` sur `recreo.css` et les gabarits,
   jamais une liste). J'affecte ce prealable a `marc-dev` maintenant. **La story S3-8 complete ne
   se cloture pas pour autant** : `camille-po` doit encore reecrire ses criteres en separant
   explicitement ce qui est verifiable de ce qui ne l'est pas avant que le reste de la story soit
   codable.

## Parallelisation et consigne de travail

**Decision : trois worktrees isoles, un par developpeur, pas de partage d'arbre pendant le
developpement ; l'audit qui suit la fusion se fait lui aussi en arbres isoles, un par agent
auditeur.**

Motif tire de ce qui s'est reellement passe, pas d'un principe abstrait :

- **Le mode worktree a paye au sprint 1** : trois arbres separes, verts chacun, ont livre plus vite
  qu'un sequencement serie. Mais un vrai defaut de fusion est apparu quand **deux developpeurs
  differents** ont implemente, chacun dans son worktree, des fragments du **meme contrat**
  (le format du score `DEC-0004`) : une assertion trop large a laisse passer une divergence reelle
  jusqu'a la fusion. Ce risque n'existe que quand deux personnes touchent la meme logique en
  parallele sans se voir.
- **Le mode arbre partage a coute plus cher au sprint 1, a la phase d'audit** : une fois les trois
  worktrees fusionnes, `yanis-qa` et `theo-archi` ont travaille sur le **meme** arbre principal en
  meme temps que d'autres agents. Consequence mesuree : un commit d'un tiers a emporte des fichiers
  en cours d'indexation, puis une reinitialisation dure a detruit tout le travail non commite de
  plusieurs agents a la fois, qui a du etre refait. La retro de s01 avait deja propose que l'audit
  se fasse lui aussi en arbres isoles ; je l'applique maintenant pour la premiere fois plutot que de
  laisser la proposition dormir.

Pour s03, les deux risques ne se recoupent pas de la meme façon selon le cluster de stories :

- **S3-1, S3-2, S3-3 touchent a plusieurs reprises les memes fichiers** (`AdditionsBorneesContentSource`
  et sa fabrique, `BanditManchotSession`) : **un seul developpeur** (`lea-dev`) les prend toutes les
  trois, dans un seul worktree, en sequence. Deux developpeurs sur ces fichiers en parallele
  reproduiraient exactement le defaut de fusion du sprint 1.
- **S3-4, S3-5, S3-6, S3-7, S3-9 touchent a plusieurs reprises `recreo.css` et les gabarits** : meme
  raisonnement, **un seul developpeur** (`ines-design`, qui a deja code CSS et gabarits directement
  en s01 et s02) les prend toutes les cinq, dans un second worktree, en sequence.
- **Le prealable de S3-8 ne touche ni les fichiers du premier cluster ni ceux du second de la meme
  facon** (un `<meta>` dans `accueil.html`, une regle generale sur `recreo.css` sans toucher aux
  regles que pose `ines-design`) : troisieme worktree, `marc-dev` seul.

Ces trois worktrees sont donc **disjoints par construction** (domaine/mecanique vs. CSS/gabarits vs.
balise d'en-tete), ce qui rend leur parallelisation sure au sens ou aucun des trois developpeurs
n'implemente une partie du meme contrat qu'un autre en meme temps - contrairement au defaut de s01.

**Consigne ecrite, a suivre par tous les agents de ce sprint :**

1. Chaque developpeur commite exclusivement dans son propre worktree (`.claude/worktrees/s03-maths`
   pour `lea-dev`, `.claude/worktrees/s03-forme` pour `ines-design`,
   `.claude/worktrees/s03-viewport` pour `marc-dev`), jamais sur la branche partagee `round-two`
   pendant le developpement.
2. **Aucun `git reset --hard`, aucun `git stash`, aucun `git add -A`** sur un arbre ou un autre
   agent pourrait avoir du travail non commite - en particulier `sofia-sm` sur la branche
   d'integration une fois les worktrees fusionnes. `git add` cite les fichiers un par un.
3. `sofia-sm` fusionne les trois worktrees **en serie**, un a la fois, sur `round-two`, jamais
   pendant qu'un autre agent y ecrit.
4. Une fois la fusion terminee, `yanis-qa` et `theo-archi` auditent chacun dans **son propre**
   clone ou worktree isole du commit fusionne, jamais sur l'arbre d'integration partage - c'est
   l'application, cette fois reelle et non plus seulement proposee, de l'amendement de la retro
   de s01 (section 9, etape 4).
5. Tout test qui compare une valeur fixee par une `DEC` ou un `ADR` (texte exact, seuil exact,
   forme exacte) compare a la valeur litterale exacte, jamais a une forme qui tolererait aussi une
   version concurrente ou perimee - deuxieme lecon de s01, qui reste valable meme avec des worktrees
   disjoints.

## Contrainte d'implementation a transmettre a ines-design (S3-4, S3-5, S3-6)

`yanis-qa` a corrige `TailleDesTextesEtBoutonsTest` pour exiger, **pour chaque classe portee par un
bouton**, une declaration `font-size` **et** une declaration `min-height`, toutes deux en rem. Une
classe modificatrice du type `class="retour-accueil principal"` rend le build rouge tant que
`.principal` ne declare pas elle-meme les deux proprietes. Pour S3-6 (bouton de sortie en aplat sur
fin de partie et ecran d'erreur), `ines-design` doit donner a chaque bouton une classe propre et
complete (par exemple `.sortie-finale` qui declare tout), pas une classe modificatrice qui compte
sur l'union avec une autre regle. La question de faire evoluer le garde-fou pour raisonner sur
l'union des regles applicables reste ouverte et appartient a `yanis-qa`, pas a `ines-design`.

## Obstacles ouverts a ce planning

| Description | Categorie | Agent route | Decision / artefact attendu | Statut |
|---|---|---|---|---|
| S3-1 : le filtre d'operandes nuls et la table de cibles se contredisent tels qu'ecrits ; sortie mesuree existe (filtre 1/3, table recalculee) mais touche un seuil de `DEC-0004` | produit | camille-po | amendement de `DEC-0004` ou nouvelle `DEC` | ouvert, bloquant pour S3-1 |
| S3-8 : le critere central n'est verifiable par aucun automate du depot sans navigateur sans tete, non recommande ce sprint ; le prealable (balise viewport absente) n'est couvert par aucun critere ecrit | produit + technique | camille-po (perimetre), theo-archi / yanis-qa (outillage, deja ouvert depuis s02) | reecriture de la story separant verifiable / non-verifiable | ouvert, bloquant pour le reste de S3-8 (prealable detache et lance) |
| S3-5 : "ecart reel entre les boites" n'est mesurable par aucun automate du depot ; seule la declaration `gap` du conteneur l'est | produit | camille-po | reformulation du critere sur la declaration, ou assomption explicite du proxy | ouvert, non bloquant pour le code, bloquant pour le test de yanis-qa |
| `TexteAccentueDesGabaritsTest` omet `fragments/erreur.html` (dette orpheline signalee par theo-archi depuis s02, sans proprietaire) | qualite / technique | yanis-qa | correction faisant deriver la liste des gabarits du repertoire `templates/` (trois lignes, ferme le trou par construction) | route, proprietaire nomme, echeance ce sprint (review) |
| Comptage divergent entre yanis-qa (22 cas) et theo-archi (21 cas) sur `LesReglesMordentTest`, non arbitre depuis s02 | qualite / technique | yanis-qa, theo-archi | reconciliation ecrite (meme protocole, meme commit audite) dans le rapport qualite et la confirmation ArchUnit de ce sprint | ouvert, route |
| La liste des neuf tags d'agent existe a trois endroits (`verifier-prefixes.sh`, `CLAUDE.md`, `squad/README.md`) sans rien qui verifie leur concordance ; theo-archi me la route sans la trancher | qualite / technique | yanis-qa (avec theo-archi) | decision ecrite a la review : soit un garde-fou de concordance, soit une verification manuelle documentee et le motif de ne pas automatiser | ouvert, route |
| Quatre stories (S3-4, S3-5, S3-6, S3-9) portent vers un garde-fou a perimetre enumere, ce qu'`ADR-0013` interdit | technique | ines-design (implementation), yanis-qa (garde-fou) | vigilance a la review, pas une violation constatee aujourd'hui | a surveiller, non bloquant |

Rappel : aucun des obstacles ci-dessus n'est `clos`. Aucun fichier `DEC` ou `ADR` nouveau n'existe
sur le disque a l'heure ou ce planning est ecrit pour trancher l'un d'entre eux ; je ne les
qualifie donc jamais autrement que `ouvert` ou `route`.

## Decisions prises

Aucune `DEC` ni aucun `ADR` n'est cree pendant ce planning : `DEC-0007` (perimetre du sprint) et
`ADR-0013` (garde-fous) sont anterieurs et deja sur le disque, l'avis de faisabilite de
`theo-archi` (`f82422e`) egalement. Les trois arbitrages produit que ce planning route
(seuil de `DEC-0004`, reecriture de S3-8, reformulation de S3-5) n'ont pas encore de fichier :
je ne les anticipe pas ici, ils seront nommes dans `review.md` et `RAPPORT.md` une fois ecrits.

La seule decision prise pendant ce planning est une decision de process qui m'appartient : le
choix de la parallelisation en trois worktrees disjoints plutot qu'un arbre partage ou un
sequencement entierement serie, avec la consigne ecrite ci-dessus. Elle n'est pas une `DEC` ni un
`ADR` (elle ne porte ni sur le produit ni sur l'architecture du code) ; elle est actee par ce
fichier lui-meme, conformement au principe que le depot est la seule memoire partagee.

## Prochaines etapes

**Demarrent maintenant** :

- `lea-dev` ouvre `.claude/worktrees/s03-maths` et commence par S3-3 (deplacement du texte de
  l'indice dans le YAML, aucun prealable), puis S3-2 (indice sur echecs consecutifs, deux pieges
  ci-dessus). S3-1 reste en attente de l'arbitrage de `camille-po`.
- `ines-design` ouvre `.claude/worktrees/s03-forme` et enchaine S3-4, S3-5 (implementation du
  `gap: 1rem`, sans attendre la reformulation du critere), S3-6 (avec la contrainte de classe
  complete ci-dessus), S3-7, S3-9 (texte accentue du titre uniquement ; la dette orpheline du
  balayage des gabarits reste chez `yanis-qa`).
- `marc-dev` ouvre `.claude/worktrees/s03-viewport` et corrige la balise `<meta name="viewport">`
  absente d'`accueil.html`, avec son garde-fou a perimetre derive.
- `yanis-qa` ecrit les tests de S3-2, S3-3, S3-4, S3-6, S3-7, S3-9 des que chaque commit atterrit
  dans son worktree d'origine (tracabilite vers les criteres), corrige `TexteAccentueDesGabaritsTest`
  pour `fragments/erreur.html`, et engage la reconciliation du comptage avec `theo-archi`.
- `camille-po` tranche, en parallele : le seuil `DEC-0004` pt.5 pour S3-1, la reecriture des
  criteres de S3-8, la reformulation du critere de S3-5.

**Des que camille-po a tranche le seuil de S3-1** : `lea-dev` la code dans le meme worktree.

**Des que les trois worktrees sont verts separement** : `sofia-sm` les fusionne en serie sur
`round-two`, jamais deux a la fois.

**Une fois l'arbre fusionne** :

1. `yanis-qa` fait tourner `mvn verify` dans son propre clone isole et ecrit le rapport qualite.
2. `theo-archi` confirme ArchUnit dans son propre clone isole, avec le tableau regle par regle.
3. `relecteur-pedagogique` releve les livrables non executables.
4. `nadia-prof` rejoue l'increment et prononce son verdict dans
   `squad/conformite/FICHE-bandit-manchot-additions-cp.md`, y compris sur la condition
   `couvert-valide` qu'elle a elle-meme posee pour S3-1.
