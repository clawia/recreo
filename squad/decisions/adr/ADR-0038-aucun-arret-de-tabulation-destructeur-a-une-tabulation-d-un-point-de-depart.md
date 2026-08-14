# ADR-0038 - Interdire tout arret de tabulation destructeur a une seule tabulation d'un point de depart que le document autorise

- **Statut** : Accepte
- **Date** : 2026-08-14
- **Auteur** : theo-archi
- **Sprint** : s10 (point route par `sofia-sm` a la cloture ; la story qui l'applique appartient a s11)

## Contexte

`nadia-prof` a refuse le Done pedagogique du sprint 10 sur un seul motif : sur l'ecran de revelation,
*"quand cette proposition est la derniere de l'ordre de tabulation, la premiere tabulation atteint
'Les jeux' et Entree detruit la partie sans confirmation"*. Sa loi, sur 25 revelations et sans une
exception : la partie est detruite **si et seulement si** la reponse revelee occupe la derniere
position. `DEC-0026` leve ce motif comme condition de livraison, sur decision de l'utilisatrice, et
laisse le moyen technique a moi.

Le refus a ete prononce au clavier, dans un navigateur. `RecreoJsDeplaceLeFocusTest` le dit lui-meme
dans sa javadoc : *"la clause 1 de M13 reste verifiee au clavier, dans un vrai navigateur, hors de la
CI"*. `yanis-qa` affirme au contraire que la propriete est **derivable du document**, et fonde son
affirmation sur une lecture du gabarit : le bouton de sortie precede le formulaire des propositions.

**J'ai verifie, et il a raison. Voici sur quoi.**

### Ce que j'ai mesure, et comment

Je n'ai pas lu le gabarit : j'ai fait rendre l'ecran par le controleur reel et assemble le document
comme htmx l'assemble (le mecanisme d'`ADR-0020`, deja outille par le corpus de
`ToutEcranEchangeEstUnDocumentJouableTest`, dont l'ecran *"manche en revelation, la partie continue"*
fait partie). Sur ce document, les candidats a l'ordre de tabulation, dans l'ordre du document :

| Rang | Element | `data-role` | `data-focus-cible` | `tabindex` | Requete declaree |
|---|---|---|---|---|---|
| 0 | `a.btn.lien-accueil` de l'en-tete permanent | `sortie` | - | aucun | `hx-post="/retour"`, `href="/"` |
| 1 | `button.btn.retour-accueil` du fragment | `sortie` | - | aucun | `hx-post="/retour"` |
| 2 | `button.btn.proposition.deja-essayee` | `action` | `false` | aucun | soumission du formulaire |
| 3 | `button.btn.proposition` | `action` | **`true`** | aucun | soumission du formulaire |

Trois faits que cette mesure ajoute a la lecture de `yanis-qa`, et qui comptent :

- **le document porte deux sorties, pas une.** Le lien de l'en-tete permanent en est une, et il vient
  **avant** le bouton du fragment. Une regle qui ne regarderait que le fragment se tromperait de
  premier element ;
- **le rang 0 n'est pas un arret de tabulation sur cet ecran.** `body` porte le marqueur
  `ecran-de-jeu` (verifie sur le document assemble), et la feuille maison declare
  `body:has(.ecran-de-jeu) .lien-accueil { display: none }` : un element masque n'est pas focalisable.
  Le premier arret reel est donc le rang 1, l'autre sortie. **La conclusion est la meme, la
  demonstration n'est pas la meme** - et une regle qui ignorerait le masquage se tromperait de premier
  arret des que ce masquage changerait ;
- **aucun `tabindex`, aucun `disabled` dans aucun gabarit de production** (recherche sur les quatre
  gabarits). L'ordre de tabulation est donc aujourd'hui l'ordre du document - **aujourd'hui
  seulement** : un seul `tabindex="1"` ajoute demain le changerait sans changer l'ordre du document.

**La loi de `nadia-prof` s'explique alors entierement par le document.** Les arrets de tabulation
sont `[sortie du fragment, proposition 1, ... , proposition N]`. Le focus va sur la proposition
revelee. Si elle est la derniere, la tabulation suivante ne trouve rien apres elle et revient au
premier arret : la sortie, puis Entree, puis `POST /retour`, qui appelle `abandonner()`. Si elle n'est
pas la derniere, la tabulation atteint une autre proposition et Entree soumet une reponse : la partie
survit. *Si et seulement si* la derniere position - exactement son releve, sans navigateur.

**La story de s11 change donc de nature** : elle est verifiable par la CI, et pas seulement au clavier
de `nadia-prof`. Ce qui suit dit **quelle** propriete est verifiable, et surtout ce qu'un vert ne
prouvera pas - parce que ce sprint a produit quatre garde-fous verts dont la propriete commune etait
de n'avoir jamais rencontre leur objet, et que je n'ecris pas le cinquieme.

### Le piege de formulation, et pourquoi j'ecarte l'enonce evident

La formulation qui vient d'abord - *"aucune proposition n'est le dernier arret de tabulation"* - est
fausse dans les deux sens. Elle **interdit** un document sain (celui ou le premier arret n'est pas
destructeur : la tabulation y revient sans rien casser) et elle **autorise** le defaut (il suffit
d'ajouter un arret apres les propositions ; si cet arret est une sortie, la partie se perd toujours,
en une tabulation au lieu d'un tour complet). Le defaut ne tient pas a la **position** d'un element
mais au **role** de celui qu'on atteint. C'est pourquoi la decision ci-dessous parle d'un arret
destructeur et jamais d'un rang.

## Decision

**1. L'invariante est une propriete du document assemble, enoncee sur son ordre de tabulation.** Soit
un ecran assemble rendu **alors qu'une partie est en cours**, et soit `A` la suite de ses **arrets de
tabulation** :

- un element est un arret de tabulation s'il est focalisable et dans l'ordre de tabulation :
  naturellement focalisable (`a[href]`, `button`, `input`, `select`, `textarea`) et non `disabled`, ou
  porteur d'un `tabindex` positif ou nul ; un `tabindex` negatif l'exclut ;
- en sont retires les elements qu'une **regle de masquage servie** retire du rendu : un element
  masque n'est pas focalisable ;
- `A` est ordonnee comme l'ordre de tabulation du document : `tabindex` positifs par valeur
  croissante d'abord, puis les autres dans l'ordre du document. **L'ordre du document ne suffit
  pas** : il coincide avec l'ordre de tabulation tant qu'aucun `tabindex` n'existe, ce qui est l'etat
  du jour et n'est pas une garantie.

**2. Deux clauses, et elles ne disent pas la meme chose.**

- **Clause 1** : `A[0]` n'est pas destructeur. C'est l'arret qu'une tabulation atteint quand **rien
  n'est focalise** - un ecran fraichement charge, ou un focus que rien n'a deplace - et c'est aussi
  celui vers lequel la tabulation revient depuis le dernier arret du document. **C'est cette clause
  qui ferme le defaut mesure par `nadia-prof`.**
- **Clause 2** : pour **tout** point de focus que le serveur peut declarer sur cet ecran, s'il existe
  un arret de tabulation apres lui dans `A`, cet arret n'est pas destructeur. *Tout* point de focus,
  et non celui que le tirage du jour a marque : la position de la reponse revelee vient du contenu, et
  une regle qui ne jugerait que le tirage rendu serait verte quinze fois sur vingt-cinq - la faute de
  mesure exacte que `nadia-prof` s'est reprochee a elle-meme.

Les deux clauses ensemble couvrent le defaut **sans que la regle ait a decider si le navigateur
reboucle dans le document ou passe par sa propre interface**. C'est voulu : le rebouclage est
precisement la partie qui depend du navigateur, et `yanis-qa` a refuse de l'affirmer sans l'avoir
observee sur deux navigateurs. La clause 1 se justifie sans lui.

**3. Un arret est destructeur si la requete qu'il declare laisse la session sans partie en cours.**
C'est un **effet mesure**, pas un attribut lu. `data-role="sortie"` est une declaration utile, et
elle doit concorder ; elle ne peut pas etre le critere a elle seule, pour une raison verifiee et non
supposee : la facon la moins chere de rendre un tel garde-fou vert serait de rebaptiser le bouton de
sortie en `data-role="action"`, et **rien ne l'attraperait** - `ADR-0017` refuse un bouton sans role
et un role calcule, mais sa clause de non-vacuite est globale (*"aucun couple action/sortie n'a ete
confronte"*), donc un ecran qui n'en declare plus aucune la laisse verte tant qu'un autre ecran en
fournit un. **La concordance est donc une clause, pas un commentaire** : tout arret dont la requete
declaree detruit la partie porte `data-role="sortie"`, et reciproquement.

**4. Le perimetre est l'ecran ou une partie est en cours, et cette condition se mesure.** Sur
l'accueil sans partie, la sortie **est** le premier arret de tabulation et c'est sans consequence :
il n'y a rien a detruire. Le corpus d'ecrans assembles produit chaque ecran par une requete reelle
sur une session reelle ; "une partie est en cours" y est un fait de session, jamais une supposition
tiree du balisage.

**5. Cette invariante ne choisit aucun remede, et les deux remedes que `DEC-0026` chiffre la
satisfont** - c'est la raison de l'ecrire ainsi plutot qu'en termes d'ordre du document :

- *demander confirmation avant de quitter une partie* : la requete de la sortie cesse de detruire la
  partie, elle rend un ecran de confirmation. L'arret cesse d'etre destructeur, les deux clauses
  passent. **A une condition d'architecture, qui est de moi et pas d'elle** : la confirmation vit sur
  le serveur, comme un ecran. Une confirmation portee par un dialogue de navigateur declenche par du
  JavaScript sortirait du perimetre d'`ADR-0008` et redeviendrait invisible a tout automate ;
- *corriger l'ordre de tabulation sur l'ecran de revelation* : le premier arret n'est plus la sortie.
  Le simple deplacement du bouton **apres** le formulaire ne suffit pas et l'invariante le dit : la
  clause 2 le refuse, parce que le dernier point de focus aurait alors la sortie pour voisin. C'est
  exactement ce qu'on veut d'une invariante - qu'elle distingue un remede d'un deplacement du defaut.

Le choix entre les deux appartient a `sofia-sm` et `camille-po` ; `DEC-0026` en a deja chiffre le
cout, et je n'ajoute rien a cet arbitrage.

**6. Un vert sur cette invariante ne leve pas `DEC-0026`.** Le declencheur de reouverture de
`DEC-0026` exige la reverification *"au protocole exact de `nadia-prof` : au clavier reel, Tabulation
puis Entree, sur les 25 revelations"*. Ce garde-fou est une condition **necessaire** de plus, jamais
la porte pedagogique, et personne ne doit lire son vert comme le verdict de `nadia-prof`.

## Regle executable - obligations du lot de s11, et pourquoi je n'ecris pas le test

**Je n'ecris pas le test ici, et ce n'est pas une entorse a la doctrine de ce depot : c'est le partage
de travail que `sofia-sm` a fixe.** La story n'est pas planifiee, et le lot appartiendra a un
developpeur. Ce que je dois a ce depot, et qui est ci-dessous, c'est un enonce assez precis pour
qu'il n'y ait rien a inventer, et la liste des obligations que la revue verifiera.

**Ce n'est pas une regle ArchUnit** - le compte des regles ArchUnit reste a **28**, methode inchangee
(declarations `ArchRule <nom> =` sous `src/test/java` des modules du pom agregateur). ArchUnit juge
des classes et leurs dependances ; la propriete porte sur un document rendu et sur une feuille de
style. Le mecanisme automatique est un test du module `adapter-web`, execute par `mvn verify` comme
tout le reste.

**Emplacement recommande** : le module `adapter-web`, aupres du corpus d'ecrans assembles, parce que
les trois derivations dont ce garde-fou a besoin y existent deja et qu'aucune n'est a reecrire :

- le **corpus d'ecrans assembles** et son perimetre derive des routes du controleur
  (`ToutEcranEchangeEstUnDocumentJouableTest`, `ADR-0020`), qui contient deja l'ecran de revelation ;
- la **derivation du masquage servi** - le couple `body:has(.<marqueur>) .<habillage>{display:none}`
  lu dans la feuille maison (`LEntreeDeMenuNeSurvitPasAUnEcranDeJeuTest`, `ADR-0022`) ;
- la **lecture des marqueurs dans le gabarit plutot que dans le test** (`ADR-0015`, `ADR-0034`) : les
  valeurs que `data-focus` peut prendre et l'attribut par lequel une proposition se declare cible se
  lisent dans `fragments/manche.html`, jamais recopiees dans le test.

**Obligations d'`ADR-0013`, les trois, sans dispense :**

1. **perimetre derive** : les ecrans du corpus derive des routes et des etats (`ADR-0034`) ; les
   points de focus des declarations du gabarit ; les arrets de tabulation du document et de la
   feuille servie ; le caractere destructeur de la requete rejouee, jamais d'une liste d'elements ;
2. **derivation totale** : sont des **echecs**, jamais des cas ignores - un ecran avec une partie en
   cours qu'aucun etat du corpus ne produit ; un `data-focus` qui designe un element absent du
   document ; une valeur de `tabindex` que la regle ne sait pas ordonner ; zero arret de tabulation ;
   zero point de focus declarable sur un ecran de manche ; et un ecran ou aucune requete declaree ne
   detruit la partie alors qu'un `data-role="sortie"` y est declare (la concordance du point 3, dans
   les deux sens) ;
3. **mise a l'epreuve, et elle est deja disponible sur du materiau reel** : `LesGardeFousSontMisALEpreuveTest`
   exigera un jeu fautif de tout garde-fou qui lit `src/main/`. Le jeu fautif de celui-ci **ne doit
   pas etre une fixture imaginee** : l'etat de la production du jour **est** le cas fautif. La
   sequence attendue du lot est donc : ecrire la regle, **constater le rouge sur la production avant
   correctif et l'inscrire dans l'ADR ou la revue**, puis corriger la production, puis conserver la
   forme d'avant correctif comme jeu fautif. C'est la seule fois de ce sprint ou un garde-fou peut
   rencontrer son objet avant que l'objet ne soit repare ; ce serait du gaspillage de ne pas s'en
   servir.

**Ce que la production rendrait aujourd'hui, mesure et non suppose** : sur l'ecran de revelation du
corpus, `A[0]` est `button.retour-accueil`, dont `hx-post="/retour"` appelle `abandonner()`. La clause
1 est donc **rouge sur la production du jour**, et la clause 2 l'est aussi sur le point de focus
declare par cet ecran (`data-focus="bouton"`, cible sur la derniere proposition), qui n'a aucun arret
apres lui - donc dont le voisin est `A[0]`. Le meme rouge tombe sur l'ecran de reprise (`GET /`
pendant une partie), ou le gabarit d'accueil rend le fragment de manche : `A[0]` y est la meme sortie.
Ce second ecran est un cas que `nadia-prof` n'a pas mesure - un chargement de page, rien de focalise,
une tabulation, Entree - et la clause 1 le couvre. **Je le signale a `sofia-sm` et a `nadia-prof`
plutot que de l'inscrire a son releve : c'est un fait technique, l'evaluation lui appartient.**

## Ce que ce garde-fou ne prouvera pas, nommement

Un vert ici voudra dire : *le document que le serveur rend n'offre aucun arret destructeur a une
tabulation d'un point de depart qu'il autorise*. Il ne voudra dire aucune des choses suivantes, et
aucune ne doit etre lue dans son vert.

1. **Que le focus va la ou le serveur le declare.** La clause 2 suppose son point de depart ; elle ne
   le verifie pas. Ce deplacement est fait par `recreo.js` sur l'evenement `htmx:afterSwap`, et il a
   ete **inoperant depuis la creation du fichier jusqu'au sprint 10** sans qu'aucun test ne le voie.
   `RecreoJsDeplaceLeFocusTest` l'execute maintenant, mais sur un faux DOM et un faux evenement.
   Si ce deplacement se recasse, la clause 2 restera verte en ne gardant plus rien - et c'est alors la
   clause 1 seule qui protege, ce qui est precisement pourquoi elle est ecrite separement.
2. **Que la premiere tabulation d'un vrai navigateur atteint l'element que la regle calcule.**
   `nadia-prof` mesure avec un outillage pilote ; `yanis-qa` a explicitement refuse d'affirmer que le
   rebouclage observe se reproduit dans un navigateur de bureau avec sa barre d'adresse, faute de
   l'avoir observe sur les deux. La regle lit un document, elle n'appuie sur aucune touche. Sur le
   rebouclage, elle est **plus stricte** que la realite possible, jamais plus lache. Sur l'ordre
   lui-meme, elle peut etre **plus lache** : un navigateur qui n'ordonnerait pas selon la
   specification, une extension, un lecteur d'ecran avec son propre mode de parcours ne sont pas
   modelises.
3. **Que ce qui est masque est bien invisible.** La derivation du masquage ne comprend **qu'une
   forme contractuelle** (`body:has(.<marqueur>) .<habillage>{display:none}`). `visibility: hidden`,
   l'attribut `hidden`, `inert`, un `display: none` porte par un **ancetre**, un masquage ecrit dans
   une media query, un element pousse hors de l'ecran : rien de tout cela n'est vu. L'ecart va dans
   les deux sens - un element cru visible qui ne l'est pas fausse `A[0]` autant que l'inverse -
   et il ne se ferme pas par une lecture de plus : il se fermerait par un moteur de rendu, qui n'existe
   pas dans ce depot (`ADR-0014`).
4. **Que la partie ne se perd pas autrement.** L'invariante parle de tabulation. La souris sur "Les
   jeux" detruit la partie sans confirmation, et c'est la **reserve O** de `nadia-prof`, entierement
   ouverte : `DEC-0026` note qu'un seul des deux remedes la ferme. Un vert ici ne dit rien d'elle.
5. **Que "destructeur" est identifie de facon exhaustive.** La regle rejoue la requete que l'element
   **declare** (`hx-post`, `href`). Un element dont l'action serait decidee ailleurs - un script, une
   soumission implicite de formulaire, un raccourci clavier - ne serait pas vu comme destructeur.
6. **Que l'enfant s'en sort.** Qu'une confirmation soit lisible par un enfant de CP, qu'un ordre de
   tabulation soit compris, que perdre une partie soit acceptable : rien de cela n'est ici. C'est
   `nadia-prof` pour le jugement et `ines-design` pour la forme.
7. **Que M13 clause 1 est tenue.** Voir le point 6 de la decision : le protocole de `DEC-0026` est le
   seul qui le dise.
8. **Que les ecrans non produits par le corpus tiennent l'invariante.** La lecon d'`ADR-0034` vaut
   ici comme ailleurs : un perimetre derive des routes reste aveugle aux etats qu'aucune requete du
   corpus ne produit. Un etat neuf ou une partie est en cours doit entrer dans le corpus, et
   l'obligation 2 ci-dessus le rend rouge s'il n'y entre pas.

Un dernier mot sur la mordancite, parce que le vert facile existe ici et qu'il faut le nommer avant de
le rencontrer : **ce garde-fou passerait au vert si le contenu cessait d'offrir plus d'une
proposition**, ou si les propositions cessaient d'etre focalisables. La clause 1 ne depend d'aucune
des deux, et c'est une deuxieme raison de l'ecrire separement de la clause 2.

## Consequences

- **La story de s11 devient verifiable par la CI.** Elle ne cesse pas d'etre verifiee par
  `nadia-prof` : elle cesse d'etre verifiee **seulement** par elle.
- **Un ecran qu'aucune requete du corpus ne produit fera rougir la regle**, y compris un ecran neuf
  parfaitement correct. C'est la friction voulue par l'obligation 2, la meme que celle qu'`ADR-0034` a
  imposee au corpus, et elle coutera un ajout de cas a chaque etat neuf.
- **La concordance du point 3 durcit `ADR-0017` par un autre chemin** : un ecran qui declare une
  sortie sans requete destructrice, ou l'inverse, devient rouge. Un lot qui voudra "faire passer" le
  garde-fou en changeant un attribut sera arrete par la concordance et non par la bonne foi.
- **Deux ecrans, et non un, sont rouges aujourd'hui** : la revelation et la reprise. Le remede que le
  lot choisira devra donc etre celui d'un ecran de moins qu'il n'y paraissait - un correctif porte par
  le seul chemin de revelation laisserait la reprise rouge.
- **Rien n'est encode par cet ADR.** C'est son cout, et il est reel : jusqu'au lot de s11, cette
  invariante est un texte. Elle est datee, sa story est nommee dans `DEC-0026`, et son declencheur de
  reouverture y est ecrit ; c'est le mieux qu'un `ADR` puisse faire sans empieter sur le lot d'un
  developpeur.

## Alternatives considerees

- **Ecrire la regle maintenant, dans ce lot.** Ecarte : `sofia-sm` l'a explicitement exclu, et le
  correctif de production qui va avec appartient a la story. Ecrire la regle sans le correctif
  laisserait la CI **rouge** a la cloture d'un sprint livre, ce qui est le plus sur moyen de faire
  desactiver une regle.
- **Enoncer l'invariante sur l'ordre du document plutot que sur l'ordre de tabulation.** Ecarte :
  les deux coincident aujourd'hui parce qu'aucun gabarit ne porte de `tabindex` (verifie), et la
  regle deviendrait fausse en silence le jour ou l'un en porterait. Un garde-fou dont le nom parle de
  tabulation et qui lit l'ordre du document est la maladie exacte d'`ADR-0013`.
- **Enoncer l'invariante par la position ("aucune proposition n'est le dernier arret")**. Ecarte pour
  la raison developpee en contexte : elle interdit un document sain et autorise le defaut deplace.
- **Fonder "destructeur" sur `data-role="sortie"` seul.** Ecarte sur un fait verifie : la clause de
  non-vacuite d'`ADR-0017` est globale, donc rebaptiser la sortie d'un ecran en `action` ne rougirait
  nulle part. Un critere qu'on desamorce en changeant un attribut n'est pas un critere.
- **Attendre un harnais de navigateur pilote dans la CI** (jouer reellement Tabulation puis Entree).
  Ecarte : le `CLAUDE.md` exclut toute chaine front, la CI n'a pas de navigateur, et `ADR-0025` a
  deja acte que la mesure de rendu vit hors de la CI, dans une commande versionnee. Ce serait de plus
  refaire le travail de `nadia-prof` avec moins de fidelite que le sien.
- **Confier la propriete a `nadia-prof` et ne rien encoder** - ce que l'etat du depot fait
  aujourd'hui. Ecarte : le defaut est **derivable du document**, et laisser a l'humaine ce qu'un
  automate peut tenir est ce qui a coute deux verdicts a ce sprint. Ce qui lui reste est la liste
  ci-dessus, qui est longue et qui est a elle.

## Ce qui me ferait changer d'avis

- **Si le remede retenu est la confirmation server-side**, la clause 2 perd presque tout son objet :
  plus aucun arret ne serait destructeur, et la regle passerait verte sur tous les ecrans sans jamais
  rien interdire. Il faudrait alors la reformuler sur la propriete qui reste, *aucune requete d'un
  seul geste ne detruit une partie en cours*, qui ne parle plus de tabulation du tout - et qui
  fermerait la reserve O dans le meme mouvement.
- **Si un `tabindex` positif devient necessaire quelque part**, l'ordre calcule cesse d'etre lisible
  d'un coup d'oeil et la regle devient le seul endroit qui sait ce que l'enfant parcourt. Je
  prefererais alors interdire les `tabindex` positifs et le dire dans un `ADR`, plutot que de les
  ordonner correctement dans un test que personne ne relit.
- **Si la derivation du masquage se revele fausse une seule fois sur un ecran reel**, l'ensemble `A`
  est faux et les deux clauses avec lui. Ce serait le signal qu'il faut cesser de deduire la
  visibilite d'une feuille de style et la mesurer, donc rejoindre l'outillage de rendu d'`ADR-0025`
  plutot que le corpus de documents.
