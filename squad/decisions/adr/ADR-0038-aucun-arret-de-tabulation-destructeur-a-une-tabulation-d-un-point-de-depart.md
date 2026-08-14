# ADR-0038 - Interdire tout arret de tabulation destructeur a une seule tabulation d'un point de depart que le document autorise

- **Statut** : Accepte, amende le 2026-08-14 (s11, apres la livraison du lot de `marc-dev`), complete
  par `ADR-0040` le 2026-08-14 (s12 : une clause 3, et le refus d'exempter l'ecran de confirmation)
- **Date** : 2026-08-14
- **Auteur** : theo-archi
- **Sprint** : s10 (point route par `sofia-sm` a la cloture ; la story qui l'applique appartient a s11)
- **Regle executable** (depuis l'amendement ci-dessous) :
  `adapters/adapter-web/src/test/java/com/recreo/adapter/web/AucunArretDeTabulationNEstDestructeurTest.java`

> **Amendement du 2026-08-14 (s11, apres la fusion du lot de `marc-dev`) - trois choses de cet ADR
> ont cesse d'etre vraies le jour ou son garde-fou a existe.** Le remede retenu est la confirmation
> server-side : `POST /retour` ne detruit plus rien et rend un ecran de confirmation, l'abandon vit
> dans `POST /retour/confirmer`.
>
> 1. **La concordance du point 3 tombe dans le sens sortie -> destructeur, et je ne la repare pas :
>    je la retire.** Elle etait un **moyen** de deriver "destructeur" sans se laisser desamorcer par
>    un renommage d'attribut ; le rejeu reel de la requete la remplace mieux et rend le moyen inutile.
>    Pire que perimee : elle interdisait le remede que le point 5 de cette meme decision benissait.
>    Detail et mesure au point 3.
> 2. **"Deux ecrans rouges" etait faux : ils etaient cinq en clause 1 et un en clause 2.** Je decrivais
>    donc un defaut plus etroit que le reel - plus etroit que celui de `nadia-prof`, qui n'en avait
>    mesure qu'un, mais etroit quand meme. Mesure et liste dans la section "Ce que la production
>    rendrait aujourd'hui".
> 3. **Quatre des huit non-preuves ont change** (3, 4, 5, 8) ; les quatre autres (1, 2, 6, 7) sont
>    verifiees encore vraies de l'implementation livree. La non-preuve 4 etait **fausse** : la souris
>    sur "Les jeux" ne detruit plus rien non plus.
>
> Ce que l'amendement ne change pas : les deux clauses, l'ordre de tabulation du point 1, le perimetre
> du point 4, et le point 6 - un vert ici ne leve toujours pas `DEC-0026`. Le compte des regles
> ArchUnit reste a **28** et cet amendement n'ajoute aucun test.

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

> **Amendement du 2026-08-14 (s12) - il y a une troisieme clause, et elle vit dans `ADR-0040`.** Les
> deux clauses ci-dessus jugent l'arret **qu'une tabulation atteint** ; aucune ne juge le point de
> depart lui-meme. Un point de focus declare **sur** un arret destructeur les laissait donc toutes les
> deux vertes - Entree seule detruisait, sans aucune tabulation - et la mesure du 2026-08-14 le
> confirme sur du materiau reel : `data-focus-cible="true"` pose sur le bouton destructeur de
> `fragments/confirmation.html` laisse les six tests du garde-fou et les 222 du module verts. Ce trou
> n'avait aucun objet avant l'ecran de confirmation, puisque aucun point de focus ne pouvait etre
> destructeur. `ADR-0040` le ferme par une clause 3, encodee dans le meme fichier de test.
>
> **La clause 2 elle-meme n'est ni amendee ni exemptee.** La question de savoir si elle devait epargner
> l'ecran dont la destruction est le second geste delibere a ete posee, mesuree et tranchee par la
> negative dans `ADR-0040` : son rouge sur cet ecran est vrai, pas decoratif.

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

> **Amendement du 2026-08-14 : la derniere phrase est retiree. La concordance etait un moyen, pas une
> propriete a garder pour elle-meme, et le lot de `marc-dev` a rendu le moyen inutile en meme temps
> qu'il en a rendu une moitie fausse.**
>
> **Ce qu'elle servait.** Relire le motif ci-dessus : il ne parle que d'un garde-fou qui **lirait**
> `data-role`, et du desamorcage a un attribut pres. `AucunArretDeTabulationNEstDestructeurTest` ne lit
> jamais `data-role` : il rejoue la requete declaree sur une session fraiche et interroge
> `EtatDePartieEnSession`. Le desamorcage que la concordance devait fermer **n'a plus de prise** -
> mesure : `data-role="sortie"` remplace par `"action"` sur le bouton de `fragments/confirmation.html`
> qui detruit reellement, les deux clauses restent vertes et leur verdict ne bouge pas d'un pouce. Le
> moyen le moins cher n'est plus un moyen.
>
> **Pourquoi je ne garde pas la moitie fausse.** Depuis ce lot, le bouton "Les jeux" de
> `fragments/manche.html` porte `data-role="sortie"` et sa requete ne detruit plus la partie : elle rend
> l'ecran de confirmation. La clause est donc fausse dans le sens sortie -> destructeur. Elle n'est pas
> fausse par accident : le point 5 de cette meme decision benissait ce remede, et une concordance
> stricte l'aurait **interdit** - la seule facon de la tenir serait de remettre la destruction dans une
> requete unique, c'est-a-dire de retablir le defaut. Un ADR qui exige a la fois une chose et son
> contraire n'exige rien ; c'est la clause qui part, pas le remede.
>
> **Ce que `data-role` dit, et que je confondais.** `ADR-0017` point 1 est explicite : cet attribut
> *"ne dit que ce que le bouton fait du point de vue de l'enfant - avancer dans le jeu, ou en sortir"*.
> C'est une **destination**, pas l'effet d'une requete sur la session. "Les jeux" sort bien du jeu, du
> point de vue de l'enfant, meme si une confirmation s'interpose : le role ne mentait pas, c'est mon
> equation role = effet d'une requete qui etait fausse. Aucun role declare n'a change de sens pour
> l'enfant, il n'y a donc rien a affaiblir de ce cote-la.
>
> **Ce qui reste vrai, et qui n'est pas une clause de cet ADR.** Dans l'autre sens - tout arret dont la
> requete detruit la partie se declare `sortie` - la production est conforme : il y a exactement **une**
> requete destructrice dans l'application (`POST /retour/confirmer`), portee par un bouton
> `data-role="sortie"`. Je ne l'inscris pas comme clause, parce qu'une clause de cet ADR se doit une
> regle et que je refuse d'ecrire une derivation dont la population compte un seul membre, deja epingle.
> **Dette nommee, et ce qui la garde en attendant** : la mutation ci-dessus est attrapee par
> `PartieControllerTest.leBoutonDeRetourDemandeConfirmationSansClorePartieEnCours`, qui exige un
> `[data-role=sortie]` sur l'ecran de confirmation - c'est un epinglage sur **un** ecran, pas une
> derivation. Une requete destructrice **neuve**, sur un autre ecran, portee par un bouton declare
> `action`, passerait verte. **Ce qui me fera l'encoder** : la deuxieme requete destructrice de cette
> application. Sa population cessera alors d'etre d'un membre, et la derivation cessera d'etre
> decorative.
>
> Mesure de cette section, methode comprise : mutation appliquee une a la fois sur ce depot, JDK 21.0.10,
> `mvn -B -o -pl adapters/adapter-web test` (215 tests du module, perimetre de la mesure - un garde-fou
> d'un autre module ne serait pas visible ici), depot restaure ensuite. Le seul rouge de la mutation
> d'attribut est le test nomme ci-dessus.

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

> **Etat au 2026-08-14 : le lot a ete livre, et la regle vit dans
> `adapters/adapter-web/src/test/java/com/recreo/adapter/web/AucunArretDeTabulationNEstDestructeurTest.java`.**
> Tout ce qui suit reste lisible comme le cahier des charges qu'il etait ; les deux endroits ou le lot
> s'en est ecarte a bon droit sont amendes sur place (l'exigence de concordance de l'obligation 2) ou
> precises dans les non-preuves.

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

   > **Amendement du 2026-08-14 : la derniere exigence de cette obligation est retiree, et le lot a eu
   > raison de ne pas l'ecrire.** Prise a la lettre, elle demandait un test que la production rendrait
   > **rouge des sa premiere execution** : depuis la confirmation, les cinq ecrans de manche declarent
   > une sortie dont aucune requete ne detruit la partie. C'est exactement l'etat vise, pas un defaut.
   > Le reste de l'obligation 2 est inchange et honore par le lot.
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

> **Amendement du 2026-08-14 : ils etaient cinq, pas deux, et je decrivais donc un defaut plus etroit
> que le reel.** Le garde-fou existe et il a ete casse sur du materiau reel. Mesure : `POST /retour`
> ramene a son `abandonner()` inconditionnel d'avant le lot - la forme exacte de la production que je
> mesurais ci-dessus - puis `mvn -B -o -pl adapters/adapter-web test -Dtest=AucunArretDeTabulationNEstDestructeurTest`
> sur JDK 21.0.10 ; le decompte est lu dans le message d'echec, ecran par ecran, et non dans mes
> attentes.
>
> - **Clause 1, cinq ecrans rouges** : "manche 1, demandee par un appui" (`POST /parties/{jeuId}`),
>   "reprise, une partie en cours" (`GET /`), "manche apres une reponse juste", "manche apres une reponse
>   fausse" et "manche en revelation" (`POST /parties/{jeuId}/coups`). Le premier arret y est le meme
>   `button.retour-accueil` sur les cinq.
> - **Clause 2, un seul ecran rouge** : "manche en revelation" - le seul du corpus dont le point de focus
>   declare n'a aucun arret apres lui, donc le seul ou le rebouclage joue.
>
> Ce que cet ecart dit de mes deux mesures : je n'avais pas tort sur la nature du defaut, j'avais tort
> sur son etendue, et pour une raison qui n'est pas un oubli d'ecran - j'ai raisonne sur les deux ecrans
> ou j'avais joue le chemin de `nadia-prof`, alors que la clause 1 ne depend d'aucun chemin : elle vaut
> sur **tout** ecran a partie en cours, donc sur tous ceux que le corpus produit. `nadia-prof` en avait
> mesure un, moi deux, le garde-fou cinq. C'est l'argument le plus court pour la forme d'enonce choisie
> ici, et il vaut d'etre garde : un enonce qui ne parle pas du chemin trouve plus d'ecrans que celui qui
> a mesure le chemin.

## Ce que ce garde-fou ne prouvera pas, nommement

Un vert ici voudra dire : *le document que le serveur rend n'offre aucun arret destructeur a une
tabulation d'un point de depart qu'il autorise*. Il ne voudra dire aucune des choses suivantes, et
aucune ne doit etre lue dans son vert.

> **Amendement du 2026-08-14 : ces huit non-preuves ont ete relues une par une contre le garde-fou
> livre, parce qu'une liste de non-preuves perimee trompe autant qu'une clause fausse.** Les
> **1, 2, 6 et 7** sont verifiees encore vraies, sans changement. Les **3, 5 et 8** sont precisees : le
> garde-fou en dit un peu plus que je n'avais promis sur 8, un peu moins sur 3 et 5. La **4 etait
> fausse** au moment ou son garde-fou est apparu, et sa correction est en dessous.

1. **Que le focus va la ou le serveur le declare.** La clause 2 suppose son point de depart ; elle ne
   le verifie pas. Ce deplacement est fait par `recreo.js` sur l'evenement `htmx:afterSwap`, et il a
   ete **inoperant depuis la creation du fichier jusqu'au sprint 10** sans qu'aucun test ne le voie.
   `RecreoJsDeplaceLeFocusTest` l'execute maintenant, mais sur un faux DOM et un faux evenement.
   Si ce deplacement se recasse, la clause 2 restera verte en ne gardant plus rien - et c'est alors la
   clause 1 seule qui protege, ce qui est precisement pourquoi elle est ecrite separement.

   > **Precise le 2026-08-14 (s12), et c'est plus net que ce que j'avais ecrit** : `recreo.js` pose
   > `tabindex="-1"` **sur le point de focus** avant de le focaliser. Le document servi et le document
   > vivant n'ont donc pas le meme `A[0]` des que le point de focus declare est lui-meme un arret : le
   > script l'en retire. Aucun ecran n'est dans ce cas aujourd'hui - le point de focus est l'enonce,
   > qui n'est pas un arret, ou une proposition, qui n'est jamais `A[0]`. C'est la mecanique exacte qui
   > rend faux le remede consistant a focaliser le bouton sur de l'ecran de confirmation, et elle est
   > developpee dans `ADR-0040`.
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

   > **Precise le 2026-08-14 : la derivation livree est plus etroite encore, et d'une facon que je
   > n'avais pas ecrite.** Le masquage est evalue **element par element** (`candidat.hasClass(habillage)`)
   > : une regle de masquage qui nommerait la classe d'un **conteneur** ne retirerait pas ses
   > descendants focalisables de `A`. C'est exact aujourd'hui parce que le seul masquage servi nomme
   > `.lien-accueil`, qui est l'element focalisable lui-meme, et faux le jour ou un masquage nommera un
   > bloc. Cela s'ajoute a la liste des formes non vues ci-dessus, sans rien en retirer.
4. **Que la partie ne se perd pas autrement.** L'invariante parle de tabulation. La souris sur "Les
   jeux" detruit la partie sans confirmation, et c'est la **reserve O** de `nadia-prof`, entierement
   ouverte : `DEC-0026` note qu'un seul des deux remedes la ferme. Un vert ici ne dit rien d'elle.

   > **Amendement du 2026-08-14 : cette non-preuve etait fausse - la souris sur "Les jeux" ne detruit
   > plus la partie non plus.** La destruction ne vit plus dans le geste, elle vit dans une requete que
   > seul l'ecran de confirmation declare ; `POST /retour` rend le meme ecran de confirmation quel que
   > soit le dispositif d'entree, et aucune branche du controleur ne distingue une souris d'un clavier.
   > Le remede retenu est celui dont `DEC-0026` disait qu'il ferme la reserve O, et c'est un fait
   > technique : **je ne prononce pas la fermeture de la reserve O, qui est a `nadia-prof`**, je dis
   > seulement que la constatation sur laquelle ce point s'appuyait n'existe plus.
   >
   > Ce qui reste vrai en dessous, et qui est la vraie non-preuve : le garde-fou ne juge que les
   > elements qu'il tient pour des **arrets de tabulation**. Un element atteignable a la souris mais
   > absent de `A` - `tabindex` negatif, `disabled`, masque par la forme contractuelle - ne serait pas
   > rejoue, meme si sa requete detruisait la partie. C'est cette phrase-la qui remplace la precedente.
5. **Que "destructeur" est identifie de facon exhaustive.** La regle rejoue la requete que l'element
   **declare** (`hx-post`, `href`). Un element dont l'action serait decidee ailleurs - un script, une
   soumission implicite de formulaire, un raccourci clavier - ne serait pas vu comme destructeur.

   > **Precise le 2026-08-14 : la lecture livree est ordonnee, et son ordre compte.** Elle prend
   > `hx-post`, sinon `hx-get`, sinon - pour un `type="submit"` - la requete du formulaire englobant
   > avec le couple nom/valeur du bouton, sinon `href`. Un element qui declare les deux, comme le lien
   > de l'en-tete (`hx-post="/retour"` **et** `href="/"`), est donc juge sur sa **seule** requete htmx :
   > c'est ce que htmx fait reellement, et c'est aussi une non-preuve de plus, car un navigateur qui
   > n'executerait pas htmx suivrait le `href`. Aucune des trois formes lisible est un echec, jamais un
   > arret ignore - cette partie de l'obligation 2 est bien honoree.
6. **Que l'enfant s'en sort.** Qu'une confirmation soit lisible par un enfant de CP, qu'un ordre de
   tabulation soit compris, que perdre une partie soit acceptable : rien de cela n'est ici. C'est
   `nadia-prof` pour le jugement et `ines-design` pour la forme.
7. **Que M13 clause 1 est tenue.** Voir le point 6 de la decision : le protocole de `DEC-0026` est le
   seul qui le dise.
8. **Que les ecrans non produits par le corpus tiennent l'invariante.** La lecon d'`ADR-0034` vaut
   ici comme ailleurs : un perimetre derive des routes reste aveugle aux etats qu'aucune requete du
   corpus ne produit. Un etat neuf ou une partie est en cours doit entrer dans le corpus, et
   l'obligation 2 ci-dessus le rend rouge s'il n'y entre pas.

   > **Precise le 2026-08-14, et le lot a ferme la moitie de ce trou.** Les **routes** ne sont plus
   > declaratives : `chaqueRouteDuControleurEstExerceeParCeGardeFou` les lit dans
   > `RequestMappingHandlerMapping` et exige que chacune produise au moins un ecran du corpus. Une route
   > neuve rend donc la suite rouge sans que personne y pense - c'est ce que les trois routes de ce lot
   > (`POST /retour`, `/retour/confirmer`, `/retour/annuler`) ont du faire. Ce qui reste ouvert, et qui
   > est la non-preuve exacte, est l'**etat** : une route deja exercee peut rendre un ecran qu'aucun
   > scenario du corpus ne construit, et cet ecran n'est juge par rien.

Un dernier mot sur la mordancite, parce que le vert facile existe ici et qu'il faut le nommer avant de
le rencontrer : **ce garde-fou passerait au vert si le contenu cessait d'offrir plus d'une
proposition**, ou si les propositions cessaient d'etre focalisables. La clause 1 ne depend d'aucune
des deux, et c'est une deuxieme raison de l'ecrire separement de la clause 2.

> **Amendement du 2026-08-14 : la question que ce paragraphe posait mal, et sa reponse mesuree.** Le
> vrai vert facile de ce garde-fou n'etait pas le contenu, c'etait le remede : une fois qu'aucune
> requete de manche ne detruit plus rien, on peut craindre que les deux clauses ne gardent plus aucun
> objet. **Mesure, et c'est faux pour la clause 1** : les deux boutons de `fragments/confirmation.html`
> echanges de place - le seul changement, aucune autre ligne - et la clause 1 rougit sur l'ecran de
> confirmation, seule et nommement, parce que le premier arret y devient `POST /retour/confirmer`, qui
> detruit reellement. **La clause 1 interdit donc encore quelque chose de precis et de plausible** :
> qu'un ecran de confirmation offre son bouton de destruction en premiere position, la ou une
> tabulation et Entree l'atteignent sans rien lire. La clause 2 n'a rien a juger sur cet ecran, qui ne
> declare aucun `data-focus` ; elle garde son objet sur les cinq ecrans de manche, ou elle est la seule
> a voir le rebouclage depuis le dernier arret.

## Consequences

- **La story de s11 devient verifiable par la CI.** Elle ne cesse pas d'etre verifiee par
  `nadia-prof` : elle cesse d'etre verifiee **seulement** par elle.
- **Un ecran qu'aucune requete du corpus ne produit fera rougir la regle**, y compris un ecran neuf
  parfaitement correct. C'est la friction voulue par l'obligation 2, la meme que celle qu'`ADR-0034` a
  imposee au corpus, et elle coutera un ajout de cas a chaque etat neuf.
- **La concordance du point 3 durcit `ADR-0017` par un autre chemin** : un ecran qui declare une
  sortie sans requete destructrice, ou l'inverse, devient rouge. Un lot qui voudra "faire passer" le
  garde-fou en changeant un attribut sera arrete par la concordance et non par la bonne foi.

  > **Amendement du 2026-08-14 : cette consequence n'a jamais existe et n'existera pas.** La concordance
  > est retiree (point 3), et ce qui devait la rendre utile - le desamorcage par renommage - est ferme
  > autrement, par un critere qui ne lit pas l'attribut. `ADR-0017` n'est ni durci ni affaibli par cet
  > ADR : il garde la saillance, celui-ci garde l'effet des requetes, et les deux ne se rencontrent
  > plus. Ce qui reste a ma charge est la dette nommee au point 3 : une requete destructrice neuve
  > declaree `action` passerait verte.
- **Deux ecrans, et non un, sont rouges aujourd'hui** : la revelation et la reprise. Le remede que le
  lot choisira devra donc etre celui d'un ecran de moins qu'il n'y paraissait - un correctif porte par
  le seul chemin de revelation laisserait la reprise rouge.

  > **Amendement du 2026-08-14 : cinq, pas deux** (clause 1 ; un seul en clause 2). Mesure et liste dans
  > la section "Ce que la production rendrait aujourd'hui". La conclusion pratique de cette puce est
  > inchangee et se trouve renforcee : un correctif porte par le seul chemin de revelation aurait laisse
  > **quatre** ecrans rouges. Le remede livre agit sur la requete, donc sur les cinq d'un coup.
- **Rien n'est encode par cet ADR.** C'est son cout, et il est reel : jusqu'au lot de s11, cette
  invariante est un texte. Elle est datee, sa story est nommee dans `DEC-0026`, et son declencheur de
  reouverture y est ecrit ; c'est le mieux qu'un `ADR` puisse faire sans empieter sur le lot d'un
  developpeur.

  > **Solde le 2026-08-14** : l'invariante n'est plus un texte. `AucunArretDeTabulationNEstDestructeurTest`
  > (six tests) la porte, avec son jeu fautif `manche-revelation-avant-correctif.html` qui est la forme
  > exacte de la production d'avant le lot. Le cout aura donc ete d'un sprint de texte, ce qui etait le
  > pari ; il aurait ete d'un sprint de CI rouge dans l'autre sens.

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

  > **Amendement du 2026-08-14 : ce declencheur s'est produit, et la reformulation qu'il annoncait est
  > fausse. Je ne l'applique pas, et c'est le seul endroit de cet ADR ou j'avais tort sur le fond et non
  > sur un chiffre.** *"Aucune requete d'un seul geste ne detruit une partie en cours"* est **fausse de
  > la production livree**, et elle doit l'etre : l'ecran de confirmation offre exactement une telle
  > requete (`POST /retour/confirmer`, un geste, une partie en cours detruite), et c'est son objet meme.
  > Cette reformulation aurait donc interdit le remede. La propriete qui tient n'est pas "aucune requete
  > d'un geste" mais "aucun **arret de tabulation** de depart n'ouvre sur une requete destructrice" -
  > c'est-a-dire l'enonce d'origine, celui qui parle de tabulation. La formulation tenue est la bonne
  > pour une raison que je n'avais pas vue : **un remede par confirmation deplace la requete destructrice
  > sur un ecran ou elle est legitime, il ne la supprime pas de l'application**, donc un enonce qui
  > l'interdirait partout interdirait le remede. Et la clause 1 ne perd pas son objet : mesure ci-dessus,
  > elle rougit si cet ecran de confirmation offre sa destruction en premier arret.
- **Si un `tabindex` positif devient necessaire quelque part**, l'ordre calcule cesse d'etre lisible
  d'un coup d'oeil et la regle devient le seul endroit qui sait ce que l'enfant parcourt. Je
  prefererais alors interdire les `tabindex` positifs et le dire dans un `ADR`, plutot que de les
  ordonner correctement dans un test que personne ne relit.
- **Si la derivation du masquage se revele fausse une seule fois sur un ecran reel**, l'ensemble `A`
  est faux et les deux clauses avec lui. Ce serait le signal qu'il faut cesser de deduire la
  visibilite d'une feuille de style et la mesurer, donc rejoindre l'outillage de rendu d'`ADR-0025`
  plutot que le corpus de documents.
