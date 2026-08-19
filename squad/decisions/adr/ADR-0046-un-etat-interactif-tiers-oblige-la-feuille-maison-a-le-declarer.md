# ADR-0046 - Un etat interactif declare par la feuille tierce oblige la feuille maison a le declarer

- **Statut** : Accepte, **amende trois fois**. Deux fois le 2026-08-19 (s16) : son exclusion de
  `:focus-visible` etait trop grossiere - c'est une propriete de cet etat qu'il faut exclure, pas
  l'etat entier ; et le meme jour (s16) : sa derivation ne voyait pas un selecteur
  compose, donc ni ne l'exigeait ni ne le comptait. Trou trouve par `sofia-sm`, chiffre par
  `nadia-prof`, correction due par le lot de `S16-3`/`S16-4`. **Troisieme amendement le 2026-08-19
  (s17) : sa derivation ne comparait aucune specificite - elle verifiait que la maison declare, jamais
  qu'elle l'emporte.** Defaut mesure sous appui reel par `nadia-prof` (deux pressables a 1,64:1), la
  feuille corrigee par `marc-dev` (`S17-D`), la derivation corrigee ici avec sa clause
- **Date** : 2026-08-17
- **Auteur** : theo-archi
- **Sprint** : s15 (ouverture)
- **Amende** : `ADR-0014`, son point 5 et l'une de ses limites nommees. Le point 5 reste vrai sur ce
  qu'il affirme de l'ordre de chargement ; c'est le "donc" qu'il en tire qui est faux, et la limite
  qui devait couvrir ce cas l'ecartait sur un fait faux.

> **Amendement du 2026-08-19 (s16) - un trou dans ma derivation, trouve par mutation et non par
> lecture, et le blocage qui le retenait est leve.**
>
> **Le fait.** `sofia-sm` a retire les etats interactifs du selecteur compose `.chip-niveau.est-actif`
> dans la feuille de production : **les sept tests de cet `ADR` sont restes verts**. `nadia-prof` a
> chiffre ce que le defaut produirait en faisant enumerer les regles par le navigateur - sans cette
> regle, l'etat de survol simple l'emporte et **la puce active prend l'apparence d'une puce inactive
> sous le doigt**. Elle note que ce defaut est plus discret que celui qui a motive cet `ADR`, donc plus
> susceptible de passer.
>
> **La cause, lue dans le code et non supposee.** `etatsMaison` et `exigencesDuTiers` retiennent une
> regle seulement si `applicables.contains(elementDuCompose(reste))`. `elementDuCompose` coupe au
> premier deux-points, donc pour `.chip-niveau.est-actif:hover` il rend `.chip-niveau.est-actif`
> **entier** - une chaine que `selecteursApplicables` ne produit jamais, puisqu'elle rend des selecteurs
> simples. La regle **sort donc du perimetre**, dans les deux sens : ses etats ne sont ni exiges ni
> comptes. Ce n'est pas un alias mal calcule, c'est une population incomplete.
>
> **La correction : un selecteur compose est un sujet distinct.** Deux appuis d'une meme classe de base
> qui declarent des apparences differentes sont deux pressables du point de vue de l'enfant, et chacun
> doit declarer ses etats. La population se derive de la meme source que la clause E de s15 - les
> combinaisons de classes qu'**un meme element peut porter**, litteraux du gabarit compris - jamais
> d'une liste de selecteurs ecrite dans le test.
>
> **Ce que la correction reclamera, mesure et non craint.** Methode : enumeration des selecteurs maison
> composes de deux classes ou plus, commentaires retires, sur la feuille servie de `4c7cd45`. Six
> familles. `.signal.est-juste` et `.signal.est-faux` ne sont pas des pressables et restent hors
> population par construction. `.chip-niveau.est-actif` declare deja ses deux etats : elle passera au
> vert **en etant enfin verifiee**. Il reste **exactement un** cas qui rougira :
> `.proposition.deja-essayee`, pressable, composee, qui ne declare aujourd'hui aucun etat interactif.
>
> **Pourquoi cette correction n'a pas eu lieu en s15, et pourquoi elle a lieu maintenant.** J'avais
> refuse de corriger la derivation sans arbitrage, parce que le rouge de `.proposition.deja-essayee`
> demandait une decision qui n'est pas la mienne : cette proposition doit-elle declarer sa propre
> apparence sous le doigt, ou celle de `.proposition` continue-t-elle de valoir pour elle ?
> **`nadia-prof` a tranche la forme neutre le 2026-08-18**, avec un motif qui vaut d'etre repris parce
> qu'il protege la coherence de l'ensemble : la variante que `ines-design` recommandait aurait detruit
> la premisse **globale** sur laquelle elle avait accorde l'aplat actif quelques heures plus tot. Le
> blocage est donc leve, et la correction entre au perimetre de s16.
>
> **Un seul lot pour les deux stories, et ce n'est pas un choix d'organisation.** La correction de la
> derivation est **indivisible** : le jour ou elle entre, les deux rouges apparaissent ensemble. Livrer
> `S16-3` d'abord obligerait soit a corriger la derivation et laisser `.proposition.deja-essayee`
> rouge - une CI rouge entre deux lots -, soit a la corriger deux fois. **Les deux stories partagent une
> seule cause et doivent partager un seul lot** ; ce sont leurs regles CSS de production qui sont
> distinctes, pas le garde-fou.
>
> **Les quatre exigences de la section "Ce que j'exige du lot qui corrigera" restent en vigueur telles
> quelles** - six regles au plus, contour non facultatif, aucune declaration forcee, forme neutre
> uniquement - et elles s'appliquent desormais aux deux familles.
>
> **Le compte des regles ArchUnit ne bouge pas** (29) : cette correction est un test de `adapter-web`.

> **Second amendement du 2026-08-19 (s16) - mon exclusion de `:focus-visible` etait trop grossiere.
> J'ai exclu un etat la ou il fallait exclure une propriete de cet etat, et cette coarseness a
> produit une contradiction entre cet `ADR` et mon propre avis de faisabilite de s16.**
>
> **La contradiction, d'abord, parce qu'elle est de moi des deux cotes.** Le critere de `S16-3` demande
> que la puce active garde ses couleurs sous `:hover`, `:active` **et `:focus-visible`**. Mon avis de
> faisabilite de s16 a ecrit que ce troisieme etat "doit passer par la constante qui enumere les etats
> gardes, sans quoi le critere serait vrai a l'ecrit et garde par rien". Le point 5 de cet `ADR`, que
> j'avais ecrit deux jours plus tot, **l'interdit** et nomme le test qui rougirait. `lea-dev` a refuse
> d'implementer et a route plutot que de trancher seule : elle a eu raison, et c'est a moi de dire
> lequel des deux documents disait faux.
>
> **Ce qui etait faux, et ce n'est ni le critere ni l'intuition de mon avis : c'est le perimetre de
> l'exclusion du point 5.** Mesure sur la feuille vendorisee servie, aujourd'hui, methode comprise -
> lecture des corps de regles dont le selecteur porte `:focus-visible` et mentionne `.btn`,
> commentaires retires :
>
> ```
> .btn:focus-visible -> color, background-color, border-color, outline, box-shadow
> ```
>
> **Ce n'est donc pas seulement une ombre portee.** La bibliotheque y ecrase aussi **les trois
> couleurs** avec ses valeurs de survol. Le point 5 disait vrai sur l'anneau et faux par omission sur
> le reste : sous focus clavier, la puce active perd bien ses couleurs au profit de celles de la
> bibliotheque, et c'est exactement le defaut que cet `ADR` existe pour fermer. **Le critere de `S16-3`
> nommait donc un defaut reel, et mon avis avait raison de vouloir le garder.**
>
> **Et le motif du point 5 reste vrai, lui aussi mesure.** Inclure l'etat entier ferait naitre une
> exigence sur `box-shadow` : `.demarrer` declare `box-shadow` au repos (verifie : bloc
> `border-radius / box-shadow / position`), la bibliotheque declare `box-shadow` a cet etat, et
> `touche()` les apparie. Satisfaire cette exigence demanderait a la maison de declarer une ombre
> portee **a l'etat de focus**, donc d'ecraser l'anneau de focus clavier dont `ADR-0030` point 2
> depend. Un garde-fou qui pousse a une regression d'accessibilite reste pire que pas de garde-fou.
>
> **Les deux faits ensemble donnent la forme juste, et elle est plus etroite que mon exclusion :**
>
> > **`:focus-visible` entre dans les etats gardes, et `box-shadow` sort des proprietes comparees a cet
> > etat - a cet etat seulement.**
>
> Les trois couleurs redeviennent exigees ; l'anneau de focus reste a la bibliotheque. **Ce n'est pas
> une exception concedee, c'est la forme que ce depot emploie deja deux fois** : `ADR-0017` exclut
> `border-width` de ses composantes comparees, `ADR-0033` exclut `transform`, et chaque fois pour la
> meme raison - une propriete dont la difference est un moyen legitime, pas une domination.
>
> **Ce que la correction change au cout que `lea-dev` avait estime, et c'est verifiable.** Elle avait
> etabli qu'ajouter l'etat rouvrirait une dette d'**ombre portee** sur les huit boutons deja traites.
> Avec `box-shadow` hors des proprietes comparees a cet etat, cette dette **n'apparait pas** : il ne
> reste que la redeclaration des trois couleurs a l'etat de focus, sur les pressables dont la maison
> gouverne ces couleurs au repos. Son constat etait juste sur la forme que je lui avais donnee ; il ne
> vaut plus sur celle-ci, et c'est ma faute et non la sienne.
>
> **Ce que le lot doit livrer, et le seul point de sequencement.** `ETATS_GARDES` gagne
> `:focus-visible` ; les proprietes comparees deviennent dependantes de l'etat, `box-shadow` etant
> ecartee au seul `:focus-visible` ; et
> `lAnneauDeFocusClavierEstUneOmbrePorteeTierceEtCetEtatResteHorsDuPerimetre` est **reformule, jamais
> supprime** - il epingle toujours les trois memes faits, sa troisieme assertion passant de "cet etat
> est hors du perimetre" a "`box-shadow` est hors des proprietes comparees a cet etat". Son nom doit
> suivre. **Sequencement** : `yanis-qa` travaille en ce moment sur la mutation-morsure de ce fichier ;
> le lot qui porte cet amendement passe apres lui, et je ne touche pas ce fichier dans le commit qui
> ecrit cet amendement - c'est la seule raison pour laquelle la regle n'est pas ici.
>
> **La porte de sortie de `S16-3` reste donc partiellement ouverte sur ce seul point**, comme
> `sofia-sm` l'a tenue, et elle se ferme avec ce lot. Le fond de la story - `:hover` et `:active` - est
> tenu et vert depuis le lot de `lea-dev`. **Le compte des regles ArchUnit ne bouge pas** (29) : cette
> correction est un test de `adapter-web`.
>
> **Ce que je retire de cette contradiction, pour moi.** Mon avis de faisabilite a lu un critere et
> raisonne sur son encodabilite **sans le confronter a l'`ADR` accepte qui gouverne le garde-fou
> concerne**, alors que cet `ADR` etait de ma main et datait de deux jours. C'est la faute que je
> sanctionne chez les autres sous le nom d'enumeration non verifiee, ici sous une autre forme : un avis
> qui prescrit une extension de perimetre doit relire la decision qui a fixe ce perimetre, et dire si
> elle tombe. Le mien ne l'a pas fait.

## Contexte

**L'utilisatrice a joue l'application livree en s14 et a vu un defaut que rien du depot n'a vu : les
boutons de l'en-tete disparaissent au survol de la souris.** La CI est verte a 510 tests, `yanis-qa`
a mute huit garde-fous sur du materiau reel, `nadia-prof` a produit des captures et prononce son
verdict. Aucun de ces trois chemins ne passe par un pointeur.

Le mecanisme est entierement declaratif, et je l'ai verifie par lecture des deux feuilles plutot que
de le croire :

- `.chip-niveau` (`static/css/recreo.css`) declare `color`, `background-color: transparent` et
  `border: 0.125rem solid var(--recreo-accent-texte)`, et **aucun etat de survol** ;
- la feuille vendorisee declare `.btn:hover{color:var(--bs-btn-color);background-color:var(--bs-btn-bg);border-color:var(--bs-btn-border-color)}`,
  et la base `.btn` pose ces trois variables aux valeurs d'un bouton nu - texte sombre, fond
  transparent, bordure transparente ;
- `.btn:hover` vaut **(0,2,0)** de specificite contre **(0,1,0)** pour `.chip-niveau`. Sur la bande
  volontairement sombre du bandeau de niveaux, la puce recoit donc du texte sombre et **plus aucun
  contour** : elle s'efface.

### Ce que ce defaut dit d'`ADR-0014`, et c'est le vrai sujet

Le point 5 d'`ADR-0014` fonde toute la doctrine de rendu du depot :

> **La feuille maison est chargee en dernier dans tout gabarit qui charge des feuilles.** Ce n'est pas
> cosmetique : c'est ce qui fait qu'une regle maison de meme specificite l'emporte sur la regle
> tierce, donc que les planchers declares ici sont ceux que l'enfant voit.

**L'hypothese de "meme specificite" ne tient pas des que la regle tierce porte une pseudo-classe.**
Et `ADR-0014` avait bien nomme cette limite, dans sa section "Ce que cette regle ne couvre pas" :

> **La specificite CSS n'est pas calculee.** [...] Un selecteur tiers de specificite superieure a une
> classe (par exemple `#id .btn`) l'emporterait sans etre vu. **Bootstrap n'en contient pas** ; le jour
> ou une bibliotheque en contiendrait, c'est cette limite qu'il faudrait lever.

Cette limite etait ecrite et elle n'a rien garde, pour une raison qui vaut d'etre nommee : **son
illustration a fait passer pour exotique un cas qui est la norme.** Il n'y faut aucun identifiant,
une pseudo-classe suffit ; et la conclusion "Bootstrap n'en contient pas" est fausse au sens qui
compte. Mesure, methode dans le meme paragraphe : decoupage de
`static/vendor/bootstrap.min.css` (232 111 octets, Bootstrap 5.3.8) en couples prelude-corps par
`}` puis par `,`, commentaires retires, soit **2 549 regles et 2 959 selecteurs**, dont **139
portent une pseudo-classe interactive** (`:hover`, `:focus`, `:focus-visible`, `:focus-within`,
`:active`, `:target`, `:visited`, `:link`). C'est exactement la classe de faute que ce depot s'est
deja reprochee deux fois : **nommer un trou dans un `ADR` ne le garde pas** (`ADR-0013`), et une
consolation ecrite a cote d'une limite en tient lieu de garde-fou (`ADR-0039`, sur
`JAVASCRIPT_TIERS_REFUSE`).

### Le perimetre reel, mesure et non estime

Methode, dans le meme paragraphe : sonde jetable non commitee, lue apres un `clean`, sur les
gabarits et les deux feuilles de `src/main/resources`. Les pressables et leurs classes viennent du
balayage des gabarits (`button, a[href]`, branches de `th:class`/`th:classappend` comprises), soit
**8 etats de bouton rendus** portant **7 classes maison** - tous portent `btn`. Pour chacun, on
retient les regles tierces dont le sujet est un selecteur applicable porteur d'un etat interactif,
et l'on croise les proprietes qu'elles declarent avec celles que la feuille maison declare au repos
sur le meme element, la relation raccourci/longhand comprise.

| Etat tiers | Regles tierces qui le portent sur nos boutons | Proprietes maison qu'il ecrase | Pressables touches |
|---|---|---|---|
| `:hover` | `.btn:hover`, `.btn-check+.btn:hover`, `.btn-group>.btn:hover` | `color`, `background-color`, `border` | 8 sur 8 |
| `:active` | `.btn:first-child:active`, `:not(.btn-check)+.btn:active` | `color`, `background-color`, `border` | 8 sur 8 |
| `:focus-visible` | `.btn:focus-visible`, `.btn-check:checked+.btn:focus-visible` | `color`, `background-color`, `border`, `box-shadow` | 8 sur 8 |

**Aucun bouton de l'application n'echappe au mecanisme, sur aucun ecran.** Ce n'est donc pas un
defaut des puces de niveau : c'est un defaut de tous les pressables, qui n'est **visible** que la ou
la difference entre la couleur maison et la couleur de bouton nu est grande - c'est-a-dire sur les
deux fonds sombres du produit, le bandeau d'en-tete (`--recreo-entete-fond`) et le bandeau de
niveaux (`--recreo-cabinet-fond`). Sur le fond de page clair, le meme mecanisme joue et ne se voit
presque pas.

**Les planchers de `nadia-prof` reellement perdus, et lesquels ne le sont pas.** Perdu : le contraste
du texte, et le contour d'un bouton de contour - `.chip-niveau` et `.retour-accueil` sont les deux
boutons dont la lisibilite **est** leur bordure, et c'est cette bordure qui devient transparente.
Non perdus : la taille de police, la hauteur minimale et le rembourrage - aucune regle d'etat de la
bibliotheque ne les declare, ce que le garde-fou de `!important` d'`ADR-0014` gardait deja par une
autre porte. Sur quels ecrans un enfant les perd : l'accueil (les deux puces de niveau, sur bande
sombre - le defaut vu), et **au survol comme a l'appui**, les quatre etats de manche et la fin de
partie, ou `.retour-accueil` et `.sortie-pleine` perdent leur couleur choisie. Le verdict sur ce que
cela coute a un enfant reste a `nadia-prof` ; je ne fais que nommer les surfaces.

### Pourquoi `ADR-0033` ne l'a pas attrape - les deux raisons, et aucune n'est un oubli

`ADR-0033` s'appelle "un habillage ne change pas d'etat sous le doigt de l'enfant" et interdit
nommement les pseudo-classes interactives. Il ne pouvait structurellement pas voir ce defaut :

1. **Son objet est plus etroit que son titre.** Son point 3 derive l'habillage par **complementaire**
   du pressable : *"toute classe posee dans un gabarit, moins toute classe portee par un element
   `button, a[href]`"*. `.chip-niveau` est portee par un `<button>` : elle est pressable, donc elle
   n'est **pas** un habillage, donc elle est hors du perimetre de cet `ADR`. Le titre parle du doigt
   de l'enfant ; la clause parle de ce que l'enfant ne presse pas.
2. **Son sens est l'inverse de celui qu'il faudrait ici.** C'est une **interdiction** faite a la
   feuille maison de declarer une pseudo-classe interactive. Ce defaut-ci exige une **obligation** de
   la declarer. Meme si `.chip-niveau` etait un habillage, `ADR-0033` serait vert : la feuille maison
   n'en declare aucune - c'est precisement le probleme.

Son garde-fou voit donc parfaitement les pseudo-classes, et sa mesure de s10 les avait meme comptees
dans la feuille tierce - *"la feuille vendorisee en porte 47 (`:hover`) et 12 (`:focus-visible`), et
aucune clause maison ne les lit"*. Ce constat etait exact et je l'avais range du bon cote de la
mauvaise frontiere : je l'ai lu comme "rien a garder" alors qu'il disait "rien ne les lit".

### Ce que la squad n'a pas vu, et pourquoi

**Un survol n'est ni un etat du document servi, ni un etat par defaut du rendu.** Il n'existe que
pendant qu'un pointeur est pose sur un element, et aucun des trois instruments du depot ne peut le
produire :

- la CI lit des declarations et un arbre DOM statique ; c'est ce qu'`ADR-0014` disait deja d'elle
  (*"aucun moteur de rendu n'existe dans ce depot"*) ;
- `outils/mesurer-rendu.sh` prend une URL et une largeur, et rasterise l'etat par defaut. **Il ne
  simule aucun pointeur** - il n'a ni parametre d'element ni parametre d'evenement, et lui en donner
  un supposerait un pilotage de navigateur que ce depot n'a pas ;
- les huit mutations de `yanis-qa` eprouvent des garde-fous ; aucune ne pouvait eprouver un
  garde-fou qui n'existait pas.

**Verdict : la moitie declarative de cette propriete etait gardable, et cet `ADR` la garde.** Ce
n'est pas le debordement d'`ADR-0025`, dont la cause meme est un fait de rendu. Ici le defaut est
**entierement lisible dans deux fichiers de texte** : une regle maison sans etat, une regle tierce
avec etat, une comparaison de specificite qui ne demande aucun navigateur. Ce qui reste non gardable
est plus etroit et je le nomme au point 5 de la decision : **ce que l'enfant voit a cet etat**, une
fois que la feuille maison le declare.

## Decision

**Des que la feuille tierce declare un etat interactif garde sur un selecteur qu'un element
pressable porte, la feuille maison declare ce meme etat pour toute propriete qu'elle gouverne au
repos sur ce meme element.**

**1. L'obligation, pas l'apparence.** La clause exige que la feuille maison **declare** l'etat ; elle
ne dit rien de la valeur. Une puce qui s'assombrit au survol, une qui ne change pas, une qui inverse
son contour : les trois satisfont la clause. **Quelle apparence un bouton prend sous le pointeur est
un arbitrage de `nadia-prof`, pas une decision d'architecture**, et cet `ADR` ne la prend pas. Ce
qu'il rend impossible est de n'avoir rien decide.

**2. Le perimetre est derive de bout en bout** (`ADR-0013` obligation 1). Aucune classe de bouton,
aucune propriete de Bootstrap n'est ecrite dans le garde-fou :

- les pressables et leurs classes viennent de `TailleDesTextesEtBoutonsTest` - **la meme population
  et les memes selecteurs applicables** que les planchers de taille et de cible tactile. Un bouton
  neuf entre dans le controle le jour ou le gabarit le pose ;
- les etats et les proprietes viennent de la **feuille vendorisee elle-meme**. Une montee de version
  qui ajoute une propriete a `.btn:hover` allonge l'exigence sans qu'aucun fichier de test change ;
- le cote maison vient de ce que la feuille maison declare reellement au repos : une propriete
  qu'elle ne gouverne pas n'est jamais exigee a un etat.

**3. La relation entre proprietes est lue sur les noms, jamais dans une table.** `.chip-niveau`
declare le raccourci `border` ; `.btn:hover` declare la longhand `border-color`. C'est **par ce
couple-la** que le contour disparait, et une egalite de noms ne le voit pas. La relation est donc :
`q` touche `p` si `q` vaut `p`, si `q` commence par `p-`, ou si `p` commence par `q-`. Trois lignes,
aucune table de raccourcis a tenir a jour, et le jour ou une bibliotheque declare `border-block-color`
la relation le voit deja.

**4. Les etats gardes sont `:hover` et `:active`. `:focus-visible` en est exclu, et l'exclusion est
mesuree, pas argumentee.** La bibliotheque vendorisee y declare l'anneau de focus clavier sous forme
d'ombre portee, sur lequel ce depot s'appuie **nommement** (`ADR-0030` point 2 ; c'est tout l'objet de
`LHabillageNeRogneJamaisUnElementPressableTest`). Mesure : inclure cet etat exigerait un `box-shadow`
maison sur `.demarrer`, qui declare une ombre de relief au repos - donc pousserait le developpeur soit
a ecraser l'anneau de focus, soit a le recopier a la main. **Un garde-fou qui pousse a une regression
d'accessibilite est pire que pas de garde-fou.** L'exclusion n'est pas laissee a la bonne foi d'un
lecteur futur : `lAnneauDeFocusClavierEstUneOmbrePorteeTierceEtCetEtatResteHorsDuPerimetre` epingle
les trois faits dont elle depend et rougit le jour ou l'un tombe. `:focus-within`, `:target`,
`:visited` et `:link` n'y sont pas non plus, et pour une raison differente : la feuille vendorisee ne
les declare sur aucun selecteur qu'un de nos boutons porte, et une exigence sans objet est
decorative.

**Consequence assumee, et elle est reelle : `:focus-visible` reste un trou.** Un enfant au clavier
voit, sur les huit boutons, le meme effacement de couleur qu'au survol, en plus de l'anneau. Ce trou
est nomme ici plutot que decouvert plus tard ; le refermer demande de savoir ce que la feuille maison
doit declarer **sans** defaire l'anneau, et c'est un arbitrage de `nadia-prof` et d'`ines-design`, pas
une regle que je peux ecrire seul. **Declencheur ecrit : le jour ou l'une des deux repond, cet `ADR`
est repris.**

**5. Ce que cette clause ne garde pas, et qui ne bouge pas de main.** Elle ne compare aucune valeur
et ne calcule aucun contraste : un `color: #1c2733` ecrit a la main sur fond sombre la satisfait. Le
verdict sur ce que l'enfant voit a un etat reste entier a `nadia-prof` (`ADR-0025`), et cet `ADR` ne
l'abrege pas d'un mot - il lui donne seulement une regle a lire au lieu d'une absence a deviner.

**6. Tant que la dette de production n'est pas payee, elle vaut exactement son plafond.** Le
correctif est un geste de feuille de style qui appartient a un lot de developpement et a un arbitrage
d'apparence ; je ne l'ecris pas dans cet `ADR`. Le garde-fou est donc livre avec un **cliquet exact** :
l'ensemble des exigences non satisfaites mesure sur la production doit valoir **exactement**
l'ensemble declare, dans les deux sens. Une exigence en plus - un pressable neuf, une propriete
gouvernee de plus au repos, une montee de version - rougit. Une exigence honoree qui resterait
declaree rougit **aussi**, ce qui oblige le lot qui corrige a faire baisser le plafond du meme pas.
**Un plafond qui depasse la dette n'est pas un cliquet** : celui-ci vaut la dette au triple pres, et
c'est verifie par un test, pas par ma parole.

### Pourquoi un `ADR` neuf, et non un amendement d'`ADR-0014`

Le precedent est celui d'`ADR-0033`, et il s'applique mot pour mot. Cet `ADR` **renverse une garantie
explicitement ecrite dans `ADR-0014`** et etablit qu'une de ses limites nommees ecartait le cas reel
sur un fait faux. Un renversement loge dans le fichier qui portait la garantie n'apparait pas dans
`ADR-index.md` : un lecteur de l'index verrait `ADR-0014` inchange et n'aurait aucun moyen de savoir
que la phrase sur laquelle repose toute la doctrine de rendu du depot a un trou. `ADR-0014` reste
`Accepte` - sa vendorisation, sa provenance, son ordre de chargement, son point 6 sont intacts et
c'est ce qui rend le correctif possible - et le renversement porte son propre numero. Sa limite est
barree sur place et renvoie ici, comme `ADR-0015` et `ADR-0039` l'ont fait pour les deux autres
limites du meme fichier.

## Regle executable

`adapters/adapter-web/src/test/java/com/recreo/adapter/web/AucunEtatDePressableNEstAbandonneAuTiersTest.java`

Sept cas. **Ce n'est pas une regle ArchUnit** : ArchUnit lit du bytecode, et ni une feuille de style
ni un gabarit n'y sont visibles. Le compte de regles ArchUnit du depot reste **29** - methode
rejouee plutot que relayee : recherche de la forme `ArchRule <nom> =` dans les `src/test/java` des
cinq modules, executee dans ce worktree seul, **29** (1 dans `adapter-web`, 28 dans `bootstrap`) ;
le meme balayage rend **28** annotations `@ArchTest`, la 29e regle etant celle d'`AssertionsWebTest`,
executee par un `.check()` explicite.

### La moitie tierce n'est jamais une fixture de ma main

Le garde-fou prend les gabarits et la feuille maison a la racine qu'on lui donne, et la **feuille
tierce toujours a la racine de production**. Ce n'est pas une commodite, c'est la lecon de s05 encodee
dans une signature : la moitie tierce de ce mecanisme est ce qu'un garde-fou peut le plus facilement
se tromper a imaginer, et une fixture ecrite de ma main ressemblerait a ce que je crois que Bootstrap
ecrit. C'est exactement ainsi qu'en s05 la detection d'un `!important` a passe sa propre epreuve en
echouant dans le monde. Aucun jeu fautif de cet `ADR` ne peut donc etre prouve contre une
bibliotheque inventee, et une montee de version rejoue les trois d'un coup.

### Preuve de morsure - mutations reelles, chacune annulee ensuite

Mesures sur `bash outils/mvn-jdk21.sh -B verify`, JDK **21.0.10** designe par la toolchain `jdk 21`
declaree dans `~/.m2/toolchains.xml` (le `java` du `PATH` de ce poste est un JRE 8) ; `git status`
confirme qu'aucune mutation ne subsiste.

| Mutation | Attendu | Mesure |
|---|---|---|
| aucune - etat livre du depot | la clause de production est **rouge** sans le cliquet | **36 exigences non satisfaites**, 32 distinctes apres dedoublonnage des deux etats rendus de `.proposition` : `(:hover, :active)` x `(color, background-color)` sur les 7 classes pressables, plus `border` sur les deux boutons de contour |
| le plafond perd les trois entrees de `.chip-niveau` | rouge nommant les six exigences manquantes | **rouge, 1 echec sur 7**, sur `laDetteDEtatsDeLaProductionVautExactementSonPlafond`, message listant `Exigence[.chip-niveau, :hover, border]` et les cinq autres |
| le plafond gagne une entree `.bouton-disparu` qui n'existe nulle part | rouge : une exemption qui ne nomme rien de reel n'est pas un cliquet | **rouge, 1 echec sur 7**, message nommant les deux exigences attendues et introuvables |
| **le correctif reel applique a `.chip-niveau`** (`:hover, :active` avec les trois proprietes), plafond baisse d'autant | **vert de bout en bout** | **BUILD SUCCESS, 517 tests, 0 echec.** C'est la mesure qui compte le plus : la clause **permet** le correctif, elle n'est pas une interdiction du bouton de contour deguisee, et le correctif ne fait rougir **aucun** autre garde-fou - `LHabillageResteImmobileTest` (`ADR-0033`) reste vert parce que `.chip-niveau` est pressable et donc hors de l'habillage |
| `.lien-accueil` remis dans l'en-tete comme avant `S14-2` | rouge sur le cliquet : un pressable neuf sans etat declare | **rouge, 1 echec sur 7** ici, plus 2 echecs dans `EnTeteEtMenuTest` qui n'appartiennent pas a cet `ADR` (voir la verification d'`ADR-0022` ci-dessous) |

### Mise a l'epreuve sur objets neufs (`ADR-0013` obligation 3)

Trois jeux d'artefacts sous
`adapters/adapter-web/src/test/resources/contreexemple/AucunEtatDePressableNEstAbandonneAuTiersTest/`.
L'objet neuf est un bouton de contour qui n'existe dans aucun gabarit de production, et le meme
gabarit sert aux trois - seule la feuille maison change :

- **`etat-jamais-declare`** : la regle maison a l'air complete - texte, aplat, contour, taille, cible
  tactile - et ne declare aucun etat. Rouge nommant `.bouton-neuf declare color au repos` ;
- **`etat-declare-des-deux-cotes`** : le meme, corrige **par le seul ajout des deux regles d'etat**.
  Vert. Sans ce jeu, la clause serait une interdiction du bouton de contour deguisee en clause
  d'etat et aucun jeu fautif ne le dirait - la lecon de la seconde mutation d'`ADR-0025`, deja
  reprise par `ADR-0033` ;
- **`contour-oublie-au-survol`** : **le piege du correctif a moitie fait, et c'est celui qui compte.**
  Les deux etats sont declares, `color` et `background-color` sont reprises : un relecteur humain voit
  un survol traite. Le contour est oublie, et le bouton perd sa bordure sous le pointeur exactement
  comme la puce que l'utilisatrice a vue s'effacer. **Il faut la relation raccourci/longhand du point 3
  pour que ce jeu rougisse** ; sans elle il serait vert, et c'est la mesure qui etablit que ce point
  n'est pas une precaution decorative.

### Ce que ce garde-fou ne pourrait structurellement pas voir

- **Un etat declare sur une balise nue.** Les selecteurs applicables d'un bouton sont ses classes des
  qu'il en porte une, et les huit en portent. Mesure sur la feuille vendorisee : la seule regle d'etat
  de balise nue qui atteindrait un de nos boutons est `a:hover`, et elle ne declare qu'une propriete
  personnalisee (`--bs-link-color-rgb`), donc rien qui ecrase une declaration maison - un non-sujet
  mesure, pas suppose. Le jour ou une bibliotheque declare une vraie propriete sur `button:hover`,
  cette clause ne la voit pas.
- **La condition structurelle d'une regle tierce n'est pas evaluee.** `.btn:first-child:active` et
  `.btn-check+.btn:hover` sont traites comme atteignables parce que leur sujet est un `.btn`. C'est le
  sens d'`ADR-0013` obligation 2 - ce qu'on ne sait pas classer est traite comme dangereux - mais
  **ici la surcollecte grossit une obligation**, donc son mode de defaillance est un rouge, pas un
  vert silencieux. Mesure sur la feuille livree : elle n'ajoute **aucune** exigence, les proprietes
  que ces regles declarent en plus (`z-index`) n'etant gouvernees par aucune regle maison au repos.
- **Un `!important` tiers sur une propriete d'etat.** C'est la porte d'`ADR-0014`, et son garde-fou ne
  regarde que trois proprietes (`font-size`, `min-height`, `padding`) : un `.fs-6`-comme utilitaire en
  `!important` sur `color` passerait chez lui comme chez moi. Non aggrave par cet `ADR`, non ferme par
  lui.
- **Une regle d'etat nichee dans une regle-@.** Le decoupage par blocs `{ }` ne comprend pas
  l'imbrication - la meme limite qu'`ADR-0014` nomme pour sa detection de `!important`. Les etats de
  `.btn` de la bibliotheque sont declares au premier niveau et sont donc vus.
- **Ce que l'enfant voit.** Point 5 de la decision. C'est la frontiere, elle ne bouge pas.

## Verification

`bash outils/mvn-jdk21.sh -B clean verify`, sur le JDK **21.0.10** designe par la toolchain `jdk 21`
declaree dans `~/.m2/toolchains.xml` : **BUILD SUCCESS**.

**Chiffres et methode dans le meme paragraphe.** Comptage valide **apres un `clean`**, lu sur les
lignes `Results:` du journal, module par module : `domain` 48, `application` 11, `adapter-web` 249,
`adapter-content` 121, `bootstrap` 82 en surefire et 6 en failsafe. Total **517 tests**, contre **510**
sur `round-two` a `5b50000` mesure de la meme facon dans ce meme worktree avant le premier commit :
les 7 de plus sont les 7 cas d'`AucunEtatDePressableNEstAbandonneAuTiersTest`, et rien d'autre.
`.github/scripts/verifier-chemins.sh` : aucun chemin de poste de travail dans les fichiers verifies.

## Verification d'`ADR-0022` - ce que coute reellement le retour de `.lien-accueil`

Le troisieme amendement d'`ADR-0022`, ecrit ce matin, a ete fait pour qu'un changement d'avis humain
sur *quels ecrans masquent quel habillage* ne coute aucun fichier de garde-fou. `DEC-0034` fait
revenir "Les jeux" dans l'en-tete, donc fait revenir `.lien-accueil` et son masquage : **c'est la
quatrieme fois en un jour que la population de cette clause change, et l'occasion de verifier la
reforme au lieu de l'affirmer.**

Mesure, methode dans le meme paragraphe : mutation du depot reel a `5b50000`, `.lien-accueil` et
`.menu` remis dans `accueil.html` et dans `recreo.css` dans leur forme d'avant `S14-2` (relevee dans
l'historique, `cc68304`), masquage `body:has(.ecran-de-jeu) .lien-accueil { display: none }` compris ;
`bash outils/mvn-jdk21.sh -B verify` apres `clean`, puis mutation annulee.

**Sur la reforme d'`ADR-0022` : zero fichier de garde-fou.** `LEntreeDeMenuNeSurvitPasAUnEcranDeJeuTest`
reste **vert, 6 cas sur 6**, avec un masquage qui reapparait sur un marqueur (`ecran-de-jeu`) qu'aucun
selecteur maison n'utilisait plus, coexistant avec celui de `S14-2` (`ecran-exclusif`). C'est
exactement la propriete pour laquelle les trois changements de la clause 2 ont ete ecrits - un
marqueur quelconque, un sous-ensemble d'ecrans, et plus aucune exigence que le mecanisme soit
employe. Restent verts aussi, sans qu'une ligne bouge : `UnEcranNeGouvernePasLApparenceDUnAutreTest`,
`HabillageDesEcransTest`, `AucunArretDeTabulationNEstDestructeurTest`,
`LActionNeDeclarePasMoinsQueLaSortieTest` et `TailleDesTextesEtBoutonsTest`. Les seules mentions de
`.lien-accueil` qui subsistent dans le code de test sont **trois lignes de Javadoc et de commentaire**,
aucune expression executable.

**Mais le retour n'est pas gratuit, et il faut le dire.** Sans cet `ADR`, la mutation rend **2 echecs
dans 1 fichier**, `EnTeteEtMenuTest`, sur les deux assertions qui encodent `DEC-0033`/`BARRE-7` - "le
lien Les jeux de l'en-tete est retire, jamais remplace par un comportement neuf" et "rien
d'actionnable dans l'en-tete hors de ses points d'entree par niveau". **Ce rouge-la est correct et il
n'est pas le mien** : ces deux assertions encodent un arbitrage pedagogique que l'utilisatrice vient
de renverser en connaissance de cause, et un garde-fou qui encode un arbitrage doit rougir quand
l'arbitrage tombe. Les retirer est une consequence de `DEC-0034` a acter par `camille-po` avec
`nadia-prof`, pas un geste d'architecture.

**Avec cet `ADR`, le retour coute un troisieme echec, dans mon fichier, et il est voulu** : le cliquet
du point 6 rougit parce que `.lien-accueil` est un pressable de contour neuf, sur le fond sombre de
l'en-tete, sans aucun etat declare - c'est-a-dire **le defaut du jour, sur le point de revenir par le
meme chemin**. Total mesure du retour de `.lien-accueil` : **3 echecs, 2 fichiers**, dont un a retirer
par decision de produit et un a payer par six lignes de feuille de style.

## Amendement du 2026-08-19 (s17) - declarer ne suffit pas, il faut l'emporter

**Troisieme trou de la meme famille dans la meme derivation, et le plus proche du coeur de cet
`ADR`.** Les deux premiers portaient sur ce que la derivation ne **voyait** pas (une propriete d'un
etat, un selecteur compose) ; celui-ci porte sur ce qu'elle **verifie** : elle exigeait qu'un etat
soit *declare* par la feuille maison, jamais qu'il l'*emporte*.

**Le fait, mesure par `nadia-prof` sous un appui reel** : deux pressables passent de 9,25:1 a
**1,64:1** et `.demarrer` perd son aplat, alors que la feuille maison declarait bien l'etat presse.
Cause entierement declarative : `.btn:first-child:active` et `:not(.btn-check)+.btn:active` valent
**(0,3,0)** contre **(0,2,0)** pour le sujet maison. Le meme mecanisme effacait l'anneau de focus
livre le meme sprint. **Le point 5 d'`ADR-0014` ne joue qu'a specificite egale** - c'est ecrit dans
cet `ADR` depuis son premier amendement, et sa propre derivation ne le verifiait pas. Une regle qui
verifie un proxy de ce qu'elle promet est un souhait avec un `assert` autour.

**La correction.** Une clause neuve dans le meme garde-fou :
`verifierQueLaMaisonLEmporteSurChaqueRegleTierce`. Pour chaque exigence deja **satisfaite** au sens
de la clause de declaration, la specificite de la regle maison qui la satisfait doit etre **au moins
egale** a celle de la regle tierce la plus specifique qui l'imposait. L'egalite suffit : la feuille
maison est chargee apres la tierce (`ADR-0014`), elle gagne donc les egalites ; seule une specificite
strictement inferieure est un defaut.

**Ce que la lecture de specificite fait, avec le sens de ses erreurs.** Elle compte identifiants,
classes/attributs/pseudo-classes, elements, et suit la specification pour les pseudo-classes
fonctionnelles : `:is()`, `:not()` et `:has()` prennent la specificite de leur argument le plus
specifique, `:where()` vaut zero. **Ce n'est pas un raffinement theorique** : sans le traitement de
`:not()`, la forme reelle `:not(.btn-check)+.btn:active` vaudrait (0,2,0) et passerait pour perdante
alors qu'elle gagne - la clause serait verte sur le defaut meme qu'elle ferme. **Une construction
qu'elle ne sait pas lire est un echec**, jamais un cas ignore : une specificite maison surevaluee est
un faux vert, et refuser ce qu'on ne sait pas lire est la seule direction sure.

**L'asymetrie qui fait le fond de cet amendement, et qui a failli me faire ecrire une clause fausse.**
La clause de declaration rattache une regle tierce a un bouton par son seul **compose du meme
element**, sans verifier qu'un prefixe (ancetre, frere, pseudo-classe structurelle) s'applique
vraiment. Elle **sur-collecte** donc, et c'est sans danger la-bas : elle exige une declaration de
plus, ce qui ne casse rien. **La meme sur-collecte est fausse pour une comparaison de specificite** :
exiger que la maison batte une regle qui ne concourt jamais obligerait a prefixer des selecteurs pour
un adversaire imaginaire. **Mesure, et c'est le chiffre qui a decide de la forme de la clause** : sans
predicat d'applicabilite, la clause rend **14 motifs sur 8 pressables**, tous imputables a la seule
regle `.btn-check+.btn:hover` - qu'aucun gabarit de ce depot ne peut declencher, `btn-check` n'y
existant nulle part. La clause n'evalue donc la specificite que contre les regles tierces qui
s'appliquent **reellement** au bouton rendu, evaluees par le moteur de selection sur l'element du
gabarit avec le jeu de classes de cet etat rendu. Un selecteur tiers que le moteur ne sait pas
evaluer est un echec, jamais un cas ignore.

**Le predicat d'applicabilite n'entre pas dans la clause de declaration**, et ce n'est pas un oubli :
l'y mettre retirerait des exigences a une clause qui, elle, gagne a sur-collecter, et ferait tomber le
cliquet de dette qui vaut exactement son plafond. Un garde-fou qu'on affaiblit en passant, pour
corriger un autre, est la faute que ce depot a deja sanctionnee.

**Mesure de ce lot** (JDK 21.0.10, `bash outils/mvn-jdk21.sh -pl adapters/adapter-web -am test`,
`-am` porte parce que la mesure est partielle) : **57 exigences declarees** sont comparees sur la
production servie, **toutes satisfaites**. Le garde-fou passe de **7 a 11 tests**.

**La morsure, sur du materiau reel.** La feuille servie est copiee puis **remise dans sa forme
d'avant `S17-D`** : l'ancetre `#jeu` que `marc-dev` a ajoute est retire, `.demarrer:active` redevient
(0,2,0) face a `.btn:first-child:active` (0,3,0) - l'etat exact de la feuille au moment ou
`nadia-prof` a mesure 1,64:1. **Et c'est la mesure qui compte le plus dans ce lot** : sur cette meme
feuille mutee, la clause de declaration reste **verte**. La declaration est la, elle ne gagne pas ;
sans la clause neuve, la CI ne voit rien. Deux autres morsures portent sur la lecture de specificite
elle-meme (parenthese non fermee, caractere illisible), et une clause epingle les valeurs des
**formes reelles des deux feuilles** plutot que de selecteurs inventes pour elle.

**Ce que cet amendement ne ferme pas, nomme avec son declencheur.** La clause credite une regle maison
a un sujet **sans verifier que son propre prefixe d'ancetre s'applique** : `#jeu .demarrer:active`
serait creditee a `.demarrer` meme si ce bouton vivait hors de `#jeu`. L'angle mort est **herite** de
la clause de declaration, pas introduit ici - mais il devient plus expose depuis que l'ajout d'un
ancetre est la sortie recommandee pour gagner de la specificite. **Dette explicite**, avec ce qui la
rendrait due : le premier lot qui ajoute un ancetre a une regle d'etat dont le pressable peut etre
rendu **hors** de cet ancetre. Je ne la ferme pas ici parce qu'un ancetre peut etre rendu sous
condition par le gabarit (`th:if`), et qu'un predicat trop strict produirait des rouges sur des
ancetres conditionnels parfaitement legitimes - c'est-a-dire l'inverse du defaut qu'on corrige.
**Seconde limite heritee** : `blocsTiers` aplatit la feuille et rend la condition d'une regle-`@`
comme une cle de selecteur ; les regles tierces nichees sous `@media` sont invisibles a cette
derivation, avant comme apres cet amendement.

**Ce que `marc-dev` a mesure et qui reste vrai** : ajouter une classe au **sujet** maison (`.btn`)
pour gagner de la specificite ferait de `btn` une classe connue de la feuille maison et changerait
l'identite calculee de **chaque** bouton du produit. La sortie est un ancetre reel separe par un
combinateur, et le message d'echec de la clause neuve le dit a qui la rencontrera.

## Consequences

- **Le point 5 d'`ADR-0014` cesse d'etre une garantie pour devenir une condition partielle.** L'ordre
  de chargement ne suffit plus a affirmer que les planchers declares sont ceux que l'enfant voit : il
  faut l'ordre **et** l'absence de `!important` tiers (`ADR-0014`) **et** la declaration des etats
  (cet `ADR`). Trois conditions au lieu d'une, et c'est la troisieme qui manquait depuis s03.
- **Une dette de production ouverte et chiffree, avec son cliquet** : 32 exigences distinctes, sur 7
  classes pressables, portant sur `color`, `background-color` et `border`. Elle ne peut plus grandir
  sans qu'un test le dise, et elle ne peut pas etre reduite en silence.
- **Un trou nomme et non ferme** : `:focus-visible`, point 4. Un enfant au clavier subit le meme
  effacement de couleur, et il faut un arbitrage pour le fermer sans defaire l'anneau de focus.
- **Mauvaise consequence assumee** : une clause de plus qui rougit un lot pour une raison qui n'est
  pas celle du lot. Toute story qui ajoute un bouton doit desormais ajouter deux regles d'etat, et le
  cout est reel - six lignes de feuille par bouton neuf. Je le prefere au bouton invisible.
- **Seconde mauvaise consequence** : un lecteur peut croire, en voyant cette clause verte, que le
  survol est correct. Il ne l'est pas : il est **declare**. Le point 5 le dit, et le nom du test ne le
  dit pas.
- **Aucun octet de ce que voit un enfant n'est change par cet `ADR`.** Il n'ajoute que du test. Le
  defaut vu par l'utilisatrice reste entier jusqu'au lot qui le corrige.

## Ce que j'exige du lot qui corrigera

1. **Six regles au plus, pas une de plus que la dette.** Un couple `:hover, :active` par groupe de
   boutons qui partagent deja une regle de repos, reprenant les proprietes que le cliquet nomme -
   `color`, `background-color`, et `border` sur les deux boutons de contour. Le plafond du point 6
   baisse dans **le meme commit**, sinon le cliquet rougit et il a raison.
2. **Le contour n'est pas facultatif.** Le jeu fautif `contour-oublie-au-survol` existe pour ce
   geste-la precisement : declarer `color` et `background-color` en oubliant `border` laisse le defaut
   entier sur les deux seuls boutons dont la lisibilite est leur bordure.
3. **Aucun `!important`, aucun selecteur renforce pour gagner la specificite.** La forme attendue est
   une regle maison portant la pseudo-classe : `.chip-niveau:hover` vaut (0,2,0) comme `.btn:hover`,
   et l'ordre de chargement fait le reste - c'est le point 5 d'`ADR-0014` retabli, pas contourne.
4. **L'apparence se demande, elle ne s'invente pas.** Si le lot veut autre chose que "l'etat reprend
   les valeurs du repos", c'est un arbitrage prealable de `nadia-prof`. La forme neutre - rien ne
   change sous le pointeur - est celle qui restaure ce que l'enfant voyait avant l'entree de la
   bibliotheque en s03, et c'est la seule que le lot peut ecrire sans rien demander.
5. **Ne pas toucher `:focus-visible`.** Le point 4 dit pourquoi, et
   `lAnneauDeFocusClavierEstUneOmbrePorteeTierceEtCetEtatResteHorsDuPerimetre` rougira si quelqu'un
   ajoute cet etat au perimetre en passant.

## Alternatives considerees

**Amender `ADR-0014` d'un point 9.** Ecartee pour la raison de gouvernance de la section dediee : un
renversement de garantie invisible dans l'index n'est pas un renversement.

**Calculer la specificite et comparer les regles.** C'etait la reponse litterale a la limite nommee
par `ADR-0014` ("la specificite CSS n'est pas calculee"), et je l'ecarte. Elle demande un moteur de
cascade - specificite, ordre, heritage, valeurs de variables, contextes de regles-@ - c'est-a-dire un
navigateur ecrit dans un test. Et elle repondrait a une question qui n'est pas la bonne : savoir
**qui gagne** ne dit pas ce que l'enfant doit voir. La clause retenue ne compare rien et exige que
la feuille maison ait un avis, ce qui est plus fort et infiniment moins de pieces mobiles.

**Interdire tout etat interactif dans la feuille tierce, ou retoucher le fichier vendorise pour les
retirer.** Ecartee sans hesitation : `ADR-0014` point 4 interdit de retoucher un octet vendorise, et
une empreinte qui ne correspond plus casse toute mise a jour lisible en diff. Le probleme n'est pas
que la bibliotheque declare des etats - c'est normal et souvent utile - c'est que nous n'en
declarions aucun.

**Poser une classe tierce de theme (`btn-outline-light`) sur les puces plutot qu'ecrire des etats
maison.** C'est la facon dont on utilise Bootstrap, et c'est exactement ce qu'`ADR-0014` point 6
refuse : *"une bibliotheque tierce porte le rendu de base ; elle ne porte jamais une exigence de
`nadia-prof`, parce que sa prochaine version peut la retirer sans que ce depot le sache"*. Le refus
tient toujours, et cet `ADR` en est la demonstration a l'envers : c'est en laissant la bibliotheque
gouverner trois proprietes qu'on a perdu un plancher.

**Etendre `ADR-0033` aux pressables plutot qu'ecrire cette obligation.** Tentant - le titre
d'`ADR-0033` couvre deja le doigt de l'enfant - et faux. `ADR-0033` **interdit** de declarer un etat ;
etendu aux pressables, il interdirait le correctif. Les deux clauses disent des choses opposees sur
des populations complementaires, et c'est coherent : sur ce que l'enfant ne presse pas, un etat est
un defaut ; sur ce qu'il presse, l'absence d'etat en est un.

**Attendre le lot du correctif pour ecrire la clause.** Ecartee, meme choix de calendrier
qu'`ADR-0030` et `ADR-0033` : une regle qui arrive apres le lot arrive apres la revue qui l'aurait
attrapee. Et ici l'argument est plus fort qu'un principe - le lot du correctif est precisement celui
ou l'on peut oublier le contour, et le jeu fautif qui garde cet oubli doit exister **avant** lui.

## Ce qui me ferait changer d'avis

- **Si un arbitrage de `nadia-prof` demande une apparence d'etat differente par bouton**, la clause ne
  change pas - elle n'exige que la declaration. C'est le signe qu'elle est au bon niveau.
- **Si la dette du cliquet n'est pas payee dans les deux sprints**, le cliquet devient ce qu'`ADR-0042`
  a coute a ce depot : un `ADR` accepte dont la regle vit dans une exemption permanente. Le remede
  n'est pas d'allonger le delai, c'est de retirer le cliquet et de laisser la clause rouge visible.
- **Si la relation raccourci/longhand du point 3 produit un faux rouge** sur une propriete que je n'ai
  pas prevue, c'est cette relation qu'il faut resserrer, pas la clause qu'il faut affaiblir.
