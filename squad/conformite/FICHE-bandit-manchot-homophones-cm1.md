# Fiche de conformite - bandit-manchot-homophones-cm1

- **Mecanique** : bandit manchot (mecanique generique, partagee avec
  `bandit-manchot-additions-cp` et `bandit-manchot-le-bon-son-cp`).
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/cm1-homophones.yaml`,
  24 items depuis s06 (22 a l'audit de s05, 10 a celui de s04), `type: question`, chacun porteur
  d'un indice. Matiere premiere issue de `contenu-repris/`.
- **Statut global au 2026-08-19 (s17), sur le paquet raccourci de `S17-4`** : **VALIDE SOUS
  RESERVE** sur les trois conditions de mon ressort. **`BPM-3` tenue** (24 items avant et apres,
  aucun supprime, toujours deux propositions ; enonce le plus long 97 -> 56 caracteres, mediane
  39 -> 29,5, onze items reecrits). **`BPM-2` tenue** : les onze items raccourcis gardent la
  structure qui commande leur reponse, aucune ambiguite neuve - **et j'ai failli enregistrer un
  defaut qui n'en etait pas un** sur le couple `ces/ses`, verifie puis retire avant de l'ecrire.
  **`BPM-4` : la relecture item par item est faite**, les 24 reponses et les 24 indices sont exacts.
  **Reserve** : **3 indices sur 24 nomment encore la reponse** (items 3 `et`, 10 `ou`, 21 `peu`), la
  ou ils etaient **24 sur 24** avant ce lot - la forme negative deja employee aux items 2 et 9 les
  ramenerait a zero. **Ce verdict est une moitie de la condition de retour, jamais la condition
  entiere** : `BPM-1` n'est pas produite, je ne la simule pas, et l'entree de catalogue reste dehors.
  **Mise a jour du 2026-08-21 : cette reserve est FERMEE** - `lea-dev` a reformule les trois indices le
  jour meme, **0 indice sur 24 ne nomme plus la reponse**, recompte par moi sur le fichier servi. Le
  verdict ci-dessus n'est pas reecrit ; il est a jour de ce qu'il jugeait, et cette ligne dit l'etat
  courant (note datee "ma reserve sur les trois indices est FERMEE", plus bas dans cette fiche).
- **Statut global** : **VALIDE** (Done pedagogique accorde le 2026-08-06, reconduit le 2026-08-07,
  **verdict VALIDE sans reserve bloquante le 2026-08-10**). C'est le premier contenu de CM1 du
  produit.
- **Ligne de matrice** : **passe a `couvert-valide`.** Ma condition en six points est **levee** au
  2026-08-10 - motif au verdict du meme jour. C'est la deuxieme ligne du produit a l'atteindre.
  **Arbitrage prealable au developpement du chantier machine a sous du 2026-08-10** (bloc en fin de
  fiche, arbitrage complet dans `FICHE-bandit-manchot-additions-cp.md`) : **deux colonnes validees**,
  158 px chacune, aucune reserve ; **jamais de troisieme colonne vide** ; et l'exigence qui vise ce jeu
  nommement, **aucune colonne ne fait defiler un mot autre que le sien**. Ce n'est pas un Done
  pedagogique et le VALIDE du 2026-08-10 n'est pas touche.
  **Constat neuf du 2026-08-10, mesure** (bloc en fin de fiche) : sur **3 des 24 items**, l'affichage
  de l'indice pousse les **deux** boutons de reponse sous le pli a 360 x 640, et sur l'item 24 il n'en
  reste rien a l'ecran ; **0 sur 24 sans l'indice**. Cause : la longueur de l'enonce a 2 rem, ni le
  nombre de propositions ni les colonnes. **Ce n'est pas un motif** - aucune decision du depot ne fixe
  de hauteur de viewport minimale - **le Done pedagogique reste accorde et la ligne reste
  `couvert-valide`**. A instruire par `camille-po`. Ce constat corrige une inference fausse que j'avais
  faite le meme jour dans `FICHE-bandit-manchot-conjugaison-present-cm1.md`.
  **Chantier machine a sous, Done pedagogique prononce le 2026-08-10 sur l'increment livre (commit
  `10dd74d`) : VALIDE SOUS RESERVE** - verdict transverse complet dans
  `FICHE-bandit-manchot-additions-cp.md`. C'est sur ce jeu que j'ai verifie, image par image sur
  l'animation reelle, qu'**aucune colonne ne montre jamais un mot autre que le sien** : l'exigence qui
  visait ce jeu nommement est tenue. **Le comptage du defaut de pli est corrige : cinq items sur 24, et
  non trois** - mesure en jouant, 41 etats d'item balayes ; et **le passage en colonnes n'a rien gagne
  sur ce defaut**, contrairement au credit que je lui avais accorde. Ni l'un ni l'autre n'est un motif :
  **le Done reste accorde et la ligne reste `couvert-valide`.**
  **Arbitrage prealable au developpement du cabinet visible du 2026-08-11** (bloc en fin de fiche,
  arbitrage complet dans `FICHE-bandit-manchot-additions-cp.md`) : cabinet **compatible avec mon veto
  sous six proprietes verifiables**, **plancher de colonne de 106 px** pour ce paquet a deux
  colonnes, `gap` de 1 rem **refuse comme payeur du cadre**, et **cabinet refuse sur l'ecran de
  retour**. Ce n'est pas un Done pedagogique, il est degrade, et le VALIDE SOUS RESERVE du
  2026-08-10 n'est pas touche. **Le defaut de pli de ce jeu n'est pas rouvert** : mon Done est
  maintenu et je ne deplace pas mes poteaux.
- **Arbitrage prealable de mecanique du 2026-08-12** (`DEC-0023` point 6 : l'ecran intermediaire
  disparait) : ecrit dans `FICHE-bandit-manchot-additions-cp.md`, bloc de validation, section
  "Arbitrage prealable au developpement - la mecanique apres la disparition de l'ecran
  intermediaire". **Il vaut pour ce jeu comme pour les huit du catalogue**, la mecanique etant
  commune : conditions M1 a M13, dont **deux bloquantes** - M4 (le message d'erreur doit etre vu
  sans defiler a 360 x 640) et la clause 1 de M13 (apres une reponse, le focus ne va jamais sur la
  sortie, qui detruit la partie sans confirmation). Ce n'est pas un Done pedagogique, rien n'est
  code.
  **Ce jeu est vise nommement par trois points** : la **reserve A** du 2026-08-12 - **22 de ses 24
  indices** nomment la bonne reponse entre guillemets -, la condition **M2** qui lui interdit donc
  d'afficher son indice sur la question courante en l'etat, et la **reserve C** - ses items n'ont que
  deux propositions, ou se tromper une fois designe la reponse par logique et ou le reessai
  n'apporte rien.
- **Second verdict du 2026-08-13 sur le lot de cloture du sprint 10 (`643c6f4`)** : ce jeu reste
  **retire du catalogue par `DEC-0024`** et n'a pas ete joue. Le REFUSE du jour ne porte pas sur lui.
  Note d'audit en tete du bloc de validation - dont ce fait, pour le jour ou il reviendra : a **deux
  propositions**, la reponse revelee y occuperait la derniere position **une fois sur deux**, ce qui en
  ferait le jeu le plus touche par le defaut de M13 clause 1.
- **Statut global au 2026-08-23 (s18), sur l'entree de catalogue de `S17-4` (arbre `1ba179d`)** :
  **`BPM-1` TENUE** - 24 items sur 24, aux trois etats d'echec, aux deux arrangements, a 744 x 551
  (`DEC-0040`) ; pire bas de proposition 411,19 px, marge +139,81 px ; 23 parties, 624 lectures,
  48 paires sur 48, `scrollY = 0` sur les 624. **`M4` tenue** sur les 24 (pire bas de verdict
  474,19 px). **Done pedagogique complet REFUSE** : sur **8 items de 24** (11, 12, 13, 15, 16, 18,
  22, 23), l'indice est coupe par le pli et l'enfant perd la fin de sa derniere phrase - sur quatre
  d'entre eux le mot perdu est *"fonctionne."*, le verbe qui distingue la forme positive de la forme
  negative employee ailleurs dans le meme paquet, ce qui rend l'indice indecidable. Conditions
  neuves datees : **`BPM-5`** (bloquante) et **`BPM-6`** (non bloquante). **La ligne de matrice ne
  bouge pas et reste `en cours`.** Verdict complet en fin de fiche ; aucun verdict anterieur n'est
  reecrit.
- **Derniere mise a jour** : 2026-08-23 (la ligne ci-dessous, du 2026-08-13, est conservee telle
  qu'ecrite ce jour-la)
- **Derniere mise a jour** : 2026-08-13

## Competences visees

| Matiere | Niveau | Domaine | Competence | Reference au referentiel |
|---|---|---|---|---|
| francais | CM1 (cycle 3) | Orthographe et grammaire | Distinguer et utiliser correctement les principaux homophones grammaticaux (et/est, son/sont, a/à...) | `squad/programme/francais/competences-cm1.md` |

## Criteres pedagogiques attendus - camille-po

> Section vide a l'ouverture de cette fiche : elle appartient a `camille-po` et je ne la
> pre-remplis pas. Mes reserves ci-dessous sont ecrites pour pouvoir etre converties en criteres
> et en stories sans avoir a me redemander ce que je voulais dire.

---

## Bloc de validation pedagogique - nadia-prof UNIQUEMENT

> Ce bloc n'est jamais pre-rempli ni anticipe par un autre agent.

### Note datee du 2026-08-19 (s17) - ma reserve sur les trois indices est FERMEE

**Fait, recompte par moi sur le fichier servi** (`round-two` a `693fdcc`), jeton par jeton, avec la
methode corrigee - insensible a la casse, celle dont la note du meme jour dans
`FICHE-bandit-manchot-additions-cp.md` explique pourquoi l'ancienne rendait 22 au lieu de 24 :
**0 indice sur 24 ne nomme la bonne reponse.** Ils etaient **24 sur 24** avant `S17-4`, **3 sur 24**
apres, et les trois items que j'avais nommes (3 `et`, 10 `ou`, 21 `peu`) ont recu la forme negative
que j'avais designee comme la reparation, deja employee aux items 2 et 9 du meme paquet.

**Ce que cela ferme et ce que cela ne ferme pas.** La reserve du verdict ci-dessous est **fermee** :
sur ce point, `M2` est desormais tenue par la totalite du paquet, et la consequence que j'assumais
depuis le 2026-08-12 - ce jeu ne peut pas afficher son indice sur la question courante - **devient
levable pour tout le paquet**. **Cela ne fait pas revenir le jeu au catalogue** : la condition de
retour de `DEC-0024` demande `BPM-1` **et** un Done pedagogique complet, `BPM-1` n'est pas produite,
et je ne la simule pas.

**Ce dont je reste seule garde** : aucun garde-fou ne compte les indices qui nomment leur reponse. Ce
comptage est de moi, a la main, un jour donne. Le vingt-cinquieme item ecrit plus tard ne sera verifie
par personne.

### Verdict du 2026-08-19 (s17) sur S17-4, BPM-2 / BPM-3 / BPM-4 - VALIDE SOUS RESERVE, et ce n'est que la moitie de la condition de retour

> **Ce que cette section fait.** Elle rend les trois jugements de contenu que `DEC-0024` et mon
> arbitrage prealable du 2026-08-19 me reservent sur le paquet raccourci - **`BPM-2`**, **`BPM-3`**,
> **`BPM-4`** - sur l'increment `ed7eb52` (contenu de `lea-dev`, `96e6b23` et `d21c952`). Elle
> n'efface aucun verdict anterieur.
>
> **Ce qu'elle ne fait pas, et il faut le lire avant le verdict.** **`BPM-1` n'est pas produite, et je
> ne la simule pas.** `DEC-0039` en a transfere la production a l'utilisatrice sans amender la
> condition. `camille-po` a tranche que **l'entree de catalogue reste dehors** jusqu'a ce que `BPM-1`
> existe **et** qu'un Done pedagogique complet soit prononce ; `lea-dev` a defait l'entree qu'elle
> avait restituee. **Ce verdict-ci est donc une moitie de la condition de retour, jamais la condition
> entiere**, et il ne rend ce jeu jouable par aucun enfant aujourd'hui.
>
> - **Verdict** : **VALIDE SOUS RESERVE**, sur les trois conditions de mon ressort.
> - **Audit joue** : **non, et ce n'est pas un choix** - le jeu n'a pas d'entree au catalogue, comme
>   depuis `DEC-0024`. Jugement de contenu, sur le fichier servi, item par item. C'est exactement le
>   perimetre que `DEC-0024` et le planning m'ont reserve ce sprint.

#### 1. `BPM-3` - TENUE, verifiee par comptage et non par la note de commit

24 items avant, **24 items apres**. Aucun supprime : le diff est fait de substitutions, pas de
coupes. **Chaque item porte toujours deux propositions** - ma reserve 5 de s04 (une chance sur deux
au hasard) n'est ni levee ni **aggravee**, ce que j'avais exige nommement.

**Le raccourcissement est reel et je le mesure** : enonce le plus long **97 -> 56 caracteres**,
mediane **39 -> 29,5**, **onze items** reecrits. Le travail demande etait *"une reecriture, pas une
coupe"* ; c'en est une.

#### 2. `BPM-2` - TENUE, et j'ai failli enregistrer un defaut qui n'en est pas un

**Ma verification, celle que j'avais ecrite** : lire les 24 phrases raccourcies et essayer la mauvaise
proposition dans chacune ; aucune ne doit passer. **Je l'ai faite sur les 24, et d'abord sur les onze
qui ont change.** Sur les onze, la structure qui commande la reponse survit au raccourcissement :
le *"ne... plus"* de l'item 24, le participe contre le present de l'item 14, le nom apres le trou des
items 12 et 15, la conjonction de l'item 19. **Aucune ambiguite neuve.**

**Et voici ce que j'ai failli ecrire.** J'ai d'abord classe **deux items en echec** - le couple
`ces/ses`, *"Regarde ___ oiseaux la-bas !"* et *"Lea range son cahier et ___ crayons."* - au motif que
les deux propositions y sont grammaticalement bien formees. **Je me suis arretee avant de l'enregistrer
et j'ai refait le raisonnement, et il est faux.** Dans la premiere phrase, `ses` exigerait un
possesseur que la phrase ne donne pas, et `la-bas` fournit le geste que le demonstratif demande ; dans
la seconde, `ces` exigerait un geste absent, et `son cahier` fournit le possesseur. **Chaque phrase
exclut bien une des deux propositions**, par ce qu'elle contient et par ce qu'elle ne contient pas.
Les deux items sont determinables, et ma verification de s05 - que je citais de memoire dans mon
arbitrage - **tient**.

**Ce que je note quand meme, et qui est une observation, pas une reserve** : ces deux items sont les
seuls du paquet ou l'exclusion passe par la **necessite du contexte** et non par l'**impossibilite
grammaticale**. Ce sont les deux plus exigeants du paquet pour un CM1. Ils sont bien construits - ils
se repondent l'un l'autre - et leurs deux indices disent exactement la regle. Je les laisse.

#### 3. `BPM-4` - la relecture item par item est faite : VALIDE SOUS RESERVE

**Exactitude** : les **24 bonnes reponses sont justes**, verifiees une a une en relisant la phrase
completee. Les **24 indices sont exacts** : aucun ne dit quelque chose de faux sur la langue.

**Le progres, et il est considerable, sur le point qui a fonde ma reserve A du 2026-08-12** :
**24 indices sur 24 nommaient la reponse avant ce lot ; ils sont 3 sur 24 apres.** Compte fait par moi,
jeton par jeton, sur le fichier servi et sur son etat d'avant (`96e6b23^`). La forme retenue - donner
la **manipulation** et s'arreter avant la conclusion - est celle que je demandais, et *"Remplace par
'avait' : 'Il avait mange une pomme' fonctionne."* est exactement l'indice que j'avais decrit comme
complet sans ses quatre derniers mots.

#### 4. La reserve, nommee et actionnable

**Trois indices sur vingt-quatre nomment encore la bonne reponse**, et je donne les trois :

| Item | Reponse | Indice livre | Ce qui la nomme |
|---|---|---|---|
| 3 | `et` | *"Remplace par 'et puis' : 'Papa et puis maman font la cuisine' fonctionne."* | `et` y figure deux fois |
| 10 | `ou` | *"Remplace par 'ou bien' : 'Tu veux du the ou bien du cafe ?' fonctionne."* | `ou` y figure deux fois |
| 21 | `peu` | *"Remplace par 'un peu' : 'il reste un peu de pain' fonctionne."* | `peu` y figure deux fois |

**Ce n'est pas de la negligence, et je le dis parce que le motif compte pour la reparation.** Sur ces
trois couples, le mot-test classique **contient** la reponse : on teste `et` par *"et puis"*, `ou` par
*"ou bien"*, `peu` par *"un peu"*. La forme positive ne peut pas ne pas nommer la reponse.

**La reparation existe deja dans ce meme fichier**, et c'est pourquoi cette reserve est actionnable
sans que j'aie a dessiner quoi que ce soit : **la forme negative**, employee aux items 2 et 9.
*"Remplace par 'avait' : 'Je vais avait l'ecole' ne fonctionne pas."* ne nomme pas `a`. Le meme geste
sur les trois items ci-dessus tomberait a **0 sur 24**.

**Pourquoi une reserve et non un refus.** L'indice n'apparait qu'apres un echec ; un indice qui nomme
la reponse transforme une aide en revelation, ce que `M5` refuse - c'est reel et c'est pourquoi je
l'ecris. Mais **21 sur 24 sont desormais corrects la ou 0 l'etait**, la consequence que j'avais
assumee (ces jeux n'affichent pas leur indice sur la question courante) devient **levable pour la
quasi-totalite du paquet**, et refuser le lot qui produit ce progres serait le contraire de ce qu'il
faut faire. **Trois items a retoucher ne valent pas un refus ; les laisser sans les nommer, si.**

#### 5. Une observation datee du 2026-08-19, non bloquante, que je ne compte pas contre ce lot

**Item 3, *"Papa ___ maman font la cuisine."*** presuppose un foyer avec un papa et une maman. Ce
n'est ni blessant ni caricatural - les deux parents y cuisinent ensemble - mais c'est un contexte que
tous les enfants n'ont pas, et c'est la seule phrase du paquet dans ce cas.

**Je la note comme une observation et pas comme une reserve, pour une raison de methode qui
m'engage** : **cet item n'a pas ete raccourci**. `BPM-4` me fait rejuger *"chaque item raccourci"* ;
celui-ci ne l'est pas, il est dans le paquet depuis s04 et je l'ai valide deux fois. **Ouvrir une
reserve sur du contenu ancien a l'occasion d'un lot qui n'y touche pas, ce serait deplacer mes
poteaux.** Je l'ecris, datee, pour que `camille-po` en fasse ce qu'elle veut le jour ou ce paquet
sera rouvert - pas pour la mettre au passif de ce sprint.

#### 6. Ce que ce verdict ne fait pas

- **Il ne fait pas revenir le jeu au catalogue** et ne prejuge pas de `BPM-1`. La condition de retour
  de `DEC-0024` reste entiere : `BPM-1` **et** un Done pedagogique complet. **Je n'ai la moitie que
  d'une des deux.**
- **Il ne leve pas `M4`** sur ce jeu, qui en est le pire cas du catalogue, ni ma **reserve C** (deux
  propositions ne se reessaient pas), ni ma **reserve 5 de s04**.
- **Il ne rouvre aucune condition tenue** et ne durcit aucune des quatre `BPM`.
- **Il ne dit rien de la forme.** Le raccourcissement change ce que l'enfant lit ; **ce qu'il voit**
  reste a mesurer, et cette mesure porte un nom, `BPM-1`, et elle n'est pas de moi ce sprint.

#### 7. Ce dont je reste seule garde

**Aucun garde-fou de la CI ne compte les indices qui nomment leur reponse.** Le comptage des sections
3 et 4 est de moi, a la main, un jour donne, avec une methode que je viens de corriger - la note
datee du meme jour dans `FICHE-bandit-manchot-additions-cp.md` dit pourquoi mon comptage precedent
rendait 22 au lieu de 24. **Rien n'empeche le vingt-cinquieme item, ecrit plus tard, de renommer sa
reponse sans que personne ne le voie.** Et `yanis-qa` a etabli ce sprint que
`LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` **ne voit rien de ce paquet non plus** -
ses propositions ne sont pas numeriques : sur `cm1-homophones` comme sur `cm1-synonymes`, la propriete
*"on ne gagne pas sans lire"* n'est gardee par rien d'automatique.

### Note d'audit - second verdict du 2026-08-13 : ce jeu n'a pas davantage ete joue

- **Aucun verdict neuf, pour la meme raison.** "Le bon petit mot" reste retire du catalogue par
  `DEC-0024` et n'apparait pas dans les sept jeux servis a `643c6f4`. Le **REFUSE** que je prononce ce
  jour sur les sept autres fiches - M13 clause 1 sur l'ecran de revelation - **ne porte pas sur celle-ci**,
  et rien de ce qui existait sur ce jeu n'est rouvert.
- **Ce que le lot de cloture change pour le jour ou ce jeu reviendra** :
  - **M13 clause 1 le concernera**, et de la meme facon que les sept autres : le defaut suit la
    position tiree de la bonne reponse, pas le contenu. Ce jeu porte **deux propositions**, donc la
    reponse revelee y occupe la derniere position **une fois sur deux** - ce serait le jeu le plus
    touche du catalogue.
  - **M4 reste son obstacle**, et le resserrement de marges de ce lot lui rendra les memes 24 a 44 px
    qu'aux autres : tres loin de ce qu'il lui faut.
  - **M21 lui est applicable telle quelle** : ses 22 indices sur 24 qui nomment la reponse pourraient
    etre neutralises par la meme cle de paquet, sans une ligne de code neuve. Ce serait un
    contournement, pas la reparation - la reparation reste de couper la fin de ces 22 phrases.
> **Note datee du 2026-08-19 (s17), `nadia-prof` - le chiffre "22 indices sur 24" ecrit deux fois
> ci-dessus est faux : c'est 24 sur 24.** Les deux phrases restent ecrites telles que je les ai
> ecrites ; cette note dit ce qu'elles ont de faux.
>
> **Cause** : j'ai compte la bonne reponse dans l'indice **sensiblement a la casse**, et les deux
> items dont la reponse porte une majuscule de debut de phrase - `On` et `Ou` - ont un indice qui la
> nomme en minuscules. Recompte par moi ce jour, jeton par jeton, sur le fichier d'avant `S17-4`
> (`96e6b23^`) : **22 sur 24 a la casse pres, 24 sur 24 sans la casse**. La note longue, avec ce que
> j'en tire sur ma facon de compter, est dans `FICHE-bandit-manchot-additions-cp.md`, au tableau du
> 2026-08-12.
>
> **Consequence sur le raisonnement : aucune.** M2 etait enfreinte par la quasi-totalite du paquet,
> elle l'etait par sa totalite. La reparation nommee - couper la fin de la phrase - vaut pour 24
> items et non 22.



### Note d'audit - ce jeu n'a pas ete joue au chantier du bandit manchot, du 2026-08-13

- **Aucun verdict neuf, et ce n'est pas un oubli.** "Le bon petit mot" est **retire du catalogue par
  `DEC-0024`** : il n'apparait pas dans les sept jeux servis, je n'ai donc pas pu le jouer, et je
  n'invente pas un verdict sur un jeu absent.
- **Rien de ce qui existait sur ce jeu n'est rouvert.** Son contenu reste `couvert-valide`, ses
  conditions anterieures ne sont ni rouvertes ni durcies, et le refus du chantier du bandit manchot
  prononce ce jour sur les sept autres fiches **ne porte pas sur celle-ci**.
- **Ce que je signale pour le jour ou ce jeu reviendra**, et qui n'est pas une condition neuve :
  - **M4 sera son obstacle**, et c'est le motif meme de son retrait par `DEC-0024`. Mon releve du
    2026-08-11 donnait le bas du cabinet a **546..846 px** avec indice sur ce jeu, le pire du
    catalogue. Le chantier livre place le message **sous** le meuble : sur ce jeu il serait plus bas
    encore que les 251 px de deficit mesures aujourd'hui sur "Le bon verbe".
  - **M2 le concerne aussi** : ses **22 indices sur 24** nomment la bonne reponse (reserve A du
    2026-08-12, non rouverte). *(chiffre corrige le 2026-08-19 : 24 sur 24, note datee ci-dessus)* Ils sont les plus faciles a corriger du lot - il n'y a que la fin de
    la phrase a couper.
  - **Ma reserve C reste ouverte** : un item a deux propositions ne peut pas etre reessaye, se
    tromper une fois y designe la reponse par logique, et le reessai que la mecanique livree apporte
    n'y sert a rien. Ce jeu tirerait un benefice reel d'une troisieme proposition.
- **Aucune ligne de `squad/programme/MATRICE-couverture.md` ne bouge.**

### Arbitrage prealable au developpement - silhouette du manche, relief et contraste du cabinet (s10), du 2026-08-12

**Ce bloc n'est pas un Done pedagogique et ne touche aucun verdict de cette fiche.** L'arbitrage
complet est ecrit une seule fois, dans `FICHE-bandit-manchot-additions-cp.md`, bloc du 2026-08-12.
**Rien n'est code.** Ce qui concerne **ce jeu-ci**, mesure sur lui aujourd'hui - 29 etats distincts
joues, 360 x 640 :

1. **Le pixel de relief ne coute aucun etat sur ce jeu.** Bas du cabinet reellement observe : 454,
   502, 550, 580, 598, **628**, puis 676, 694, 724, 772. Entre le dernier bas visible (**628**) et le
   premier bas coupe (676) il y a **48 px** : **aucun etat n'a son bas entre 629 et 639**, donc un
   decalage d'1 px n'en fait basculer aucun. C'est ce jeu qui a la marge la plus confortable des deux
   jeux exposes, et ce n'est donc pas sur lui que le pixel se mesure (condition N6, mesuree sur "Le
   bon verbe").
2. **Je corrige mes chiffres du 2026-08-11 sur ce jeu, sans les effacer.** J'avais ecrit un bas de
   cabinet de `y = 450` a `y = 846` ; je remesure aujourd'hui **454** au plus haut et **772** au plus
   bas sur mon echantillon, soit **+4 px** sur la borne basse. L'ecart va dans l'autre sens que celui
   de "Le bon verbe" (-2 px) : ce n'est donc pas un decalage systematique de methode, et **je ne sais
   pas reconstituer la cause**. Ce sont les chiffres d'aujourd'hui qui doivent etre cites.
3. **Le point 2 du verdict ci-dessous n'est pas atteint dans son sens** : 11 etats sur 30 hier, **5 sur
   29** sur mon echantillon d'aujourd'hui, qui n'est pas la meme population (je n'ai pas revu les 30
   etats un par un ce jour-la). Le defaut de `DEC-0016` est le meme, il n'est ni aggrave ni reduit par
   ce lot, et je ne le rouvre pas comme barre.
4. **Rien de ce lot ne touche la largeur des colonnes** : 152 px par colonne, inchange, condition N7.

### Verdict courant - chantier du cabinet (S8-1, S8-2, S8-3), du 2026-08-11 : VALIDE SOUS RESERVE

**Le verdict complet est ecrit une seule fois, dans `FICHE-bandit-manchot-additions-cp.md`, bloc du
2026-08-11** : la mecanique et l'habillage sont communs aux quatre jeux du bandit manchot. **Audit
joue, non degrade** : onze parties de ce jeu, les vingt-quatre items vus, vingt-deux d'entre eux aussi
avec leur indice obtenu par trois echecs consecutifs reels, mesures a 360 x 640. Ce qui concerne **ce
jeu-ci** :

1. **Le budget horizontal est tenu largement.** Colonnes a **152,00 px** contre le plancher de 106 px
   que j'avais fixe pour ce paquet a deux colonnes ; `mais` fait 69,70 px et garde **41,14 px d'air**
   de chaque cote. Aucune troncature, aucune coupure, police a 32 px sur les vingt-quatre items.
   **Mon Done du 2026-08-06 n'est pas atteint par le cabinet.**
2. **Ma reserve 1 de la section 8 de l'autre fiche mord ici, et c'est le jeu ou elle mord le plus
   longtemps.** Le quatrieme cote du cabinet passe sous le pli sur **11 etats sur 30** : 9 des 22
   etats avec indice et 2 des 8 sans. Bas du cabinet mesure de `y = 450` a `y = 846`.
   **Ce n'est pas un motif de refus, et je dis pourquoi plutot que de le laisser deviner** : c'est le
   defaut de pli de `DEC-0016`, que j'ai etabli moi-meme en s07 sur ce jeu precisement (deux boutons a
   `y = 717` et `y = 790`), que j'ai refuse d'opposer a ce Done a l'epoque, et que `DEC-0017` a laisse
   hors du perimetre de ce sprint. Le cabinet l'aggrave de **40 px** - et ma limite de 150 px avait
   ete posee sur "Le bon son", le jeu le plus court, ce qui est mon erreur et non celle d'un
   developpeur. Je ne deplace pas mes poteaux : je maintiens mon Done et j'inscris le chiffre.
3. **Mes reserves de contenu du 2026-08-06 restent ouvertes et inchangees**, aucune n'a ete traitee ce
   sprint et aucune n'etait censee l'etre.

### Arbitrage prealable au developpement - cabinet visible de la machine, du 2026-08-11

**Ce bloc n'est pas un Done pedagogique et il est degrade** : le cabinet n'est pas ecrit. Il ne
touche aucun verdict anterieur. L'arbitrage complet est ecrit une seule fois, dans
`FICHE-bandit-manchot-additions-cp.md`, bloc du 2026-08-11. Ce qui concerne **ce jeu-ci** :

1. **Ce jeu est le cas a deux colonnes, et son plancher de largeur est de 106 px** a 360 px : le mot
   le plus large est `sont`, mesure a 65,5 px, plus les 2 x 20 px de rembourrage declare. Un cadre de
   8 px de chaque cote ramene les colonnes de 160 a **152 px**, donc largement au-dessus.
2. **Deux colonnes restent validees**, comme le 2026-08-10 : l'exercice **est** binaire et deux
   colonnes disent la verite de l'exercice. **Un cabinet ne change rien a cela** : je refuse
   toujours toute colonne vide, muette ou desactivee ajoutee pour atteindre trois, y compris si le
   cadre paraissait mieux rempli a trois. La fidelite de la metaphore n'apprend pas a lire.
3. **C'est ce jeu qui garde le `gap` a l'egalite exacte**, et c'est ici que je le repete : le `gap`
   de 1 rem est **refuse comme payeur du cadre**, sur les deux axes. 16 px declares, 16 px rendus,
   remesures le 2026-08-11. Il n'y a pas 2 px a recuperer.
4. **Le cabinet ne va pas sur l'ecran de retour** ni sur l'ecran de fin de partie.
5. **Le defaut de pli de ce jeu reste hors perimetre**, comme demande : mon Done du 2026-08-10 est
   maintenu, y compris le comptage corrige a cinq items sur 24, et je ne le rouvre pas ici.

### Verdict courant - chantier machine a sous (S7-1 a S7-4), du 2026-08-10

- **Verdict** : **VALIDE SOUS RESERVE.** Done pedagogique du chantier accorde. Le verdict complet et
  son unique reserve sont dans `FICHE-bandit-manchot-additions-cp.md`, bloc "Verdict courant -
  chantier machine a sous". **Il vaut pour ce jeu.** Le Done pedagogique du contenu de ce jeu, accorde
  le 2026-08-10, n'est pas modifie et sa ligne de matrice reste `couvert-valide`.
- **Date** : 2026-08-10. **Commit audite** : `10dd74d`. Audit **joue**, non degrade.
- **Ce que j'ai verifie sur ce jeu precisement** : c'est ici que j'ai fait la verification du point 3
  de ma liste, "aucun autre mot que le sien ne passe dans une colonne", parce que c'est le jeu ou le
  defaut serait le plus grave. Animation reelle figee et deplacee image par image sur l'item `là` /
  `la` : **a aucun instant une colonne ne montre autre chose que son propre mot**, ni un mot vide. Le
  detail est en section 3 du verdict transverse.

| Point | Mesure sur ce jeu |
|---|---|
| colonnes | 2 colonnes de **160 px** a 360 px |
| mot le plus large du paquet | `sont`, **65,5 px** dans 160 px |
| troncature, coupure, compression | **aucune**, sur les 24 items |
| duree du defilement | **778 ms**, decalage de 167 ms entre les deux colonnes |
| ecran immobile | lisible et jouable, voile entierement invisible |

#### Je corrige mon propre comptage du defaut de pli : cinq items sur 24, et non trois

`yanis-qa` confirme sur le code livre que le defaut persiste, et il a raison. En le remesurant, je
trouve **plus** que ce que j'avais compte. Balayage de 41 etats d'item distincts sur huit parties, a
360 x 640, indice affiche :

| Enonce (debut) | Lignes | Bas de l'indice | Boites de reponse | Etat |
|---|---|---|---|---|
| "Après cette longue randonnée en montagne..." | 6 | 705 | **717-791** | les deux boutons entierement sous le pli |
| "Tom cherchait sa gomme depuis le début du cours..." | 5 | 641 | 653-727 | les deux entierement sous le pli |
| "Après la récréation, tous les élèves ___ rangent..." | 4 | 579 | 591-665 | les deux mots sous le pli |
| "Léa a perdu son livre depuis lundi, mais elle ___..." | 4 | 579 | 591-665 | les deux mots sous le pli |
| "Même si le ciel est bien dégagé ce matin, il ___..." | 4 | 579 | 591-665 | les deux mots sous le pli |
| les 19 autres items | 1 a 3 | - | - | au-dessus du pli |
| les 24 items **sans** l'indice | - | - | - | **aucun** en defaut |

J'avais ecrit trois items le 2026-08-10, en mesurant depuis les enonces. **Il y en a cinq**, mesures en
jouant. C'est une correction d'un de mes propres constats, dans le sens de la gravite.

**Et je corrige aussi un credit que j'avais accorde au chantier a tort.** J'avais ecrit que la mise en
page en colonnes "rend le pli confortable la ou il etait juste". C'est faux ici : les colonnes ne
remontent pas la premiere rangee de boutons, dont la position ne depend que de la hauteur de l'enonce
et de l'indice. Mesure sur l'item le plus long : les deux boutons sont a **717-791** sur le code livre,
exactement la ou j'avais releve 717-790 avant le chantier. **Le passage en colonnes n'a rien gagne sur
ce defaut**, et personne ne doit croire qu'il l'a traite. Le detail et la part que je declare degradee
sur cette comparaison sont en section 10, point D, du verdict transverse.

**Rien de tout cela n'est un motif, et je ne change pas d'avis.** J'avais choisi de ne pas l'opposer a
un jeu dont j'avais accorde le Done, parce qu'aucune decision du depot ne fixait de hauteur de
viewport et que je ne fabrique pas une barre en cours d'audit. `DEC-0016` fixe maintenant la regle, ce
qui est le bon endroit pour elle - mais elle est posterieure a l'increment, et l'opposer a ce jeu
aujourd'hui serait exactement le deplacement de poteaux que je refuse. **Le Done pedagogique reste
accorde, la ligne reste `couvert-valide`, et le chiffre corrige part chez `camille-po`.**

- **Ligne de matrice** : **inchangee**, `couvert-valide`. Ce chantier ne porte aucune competence.

---

### Verdict - premier audit, contenu livre en s04

- **Verdict** : **VALIDE SOUS RESERVE.** Done pedagogique accorde. Ce jeu peut etre mis devant
  une classe de CM1.
- **Date** : 2026-08-06.
- **Commit audite** : `c801eb8` (`HEAD` de `round-two`).
- **Moyens** : audit **joue**, non degrade. Fat jar construit et lance en local (JDK 21.0.10),
  parties completes jouees en repondant juste et en me trompant volontairement, y compris jusqu'a
  l'ecran de fin. Rendu reel mesure au navigateur sans tete.

#### 1. Exactitude du contenu - irreprochable, verifiee item par item

Les dix phrases, leur bonne reponse et leur distracteur, un par un :

| Enonce | Attendu | Distracteur | Verdict |
|---|---|---|---|
| "Il ___ mangé une pomme." | `a` | `à` | juste, auxiliaire avoir |
| "Je vais ___ l'école." | `à` | `a` | juste, preposition |
| "Papa ___ maman font la cuisine." | `et` | `est` | juste, "font" au pluriel interdit "est" |
| "Le chat ___ noir." | `est` | `et` | juste |
| "Les enfants ___ partis." | `sont` | `son` | juste |
| "Il prend ___ cartable." | `son` | `sont` | juste |
| "Ils ___ un chien." | `ont` | `on` | juste |
| "___ joue dans la cour." | `On` | `Ont` | juste, majuscule correcte en tete de phrase |
| "___ vas-tu ?" | `Où` | `Ou` | juste |
| "Tu veux du thé ___ du café ?" | `ou` | `où` | juste |

**Zero erreur factuelle.** Chaque phrase n'admet qu'une seule lecture grammaticalement correcte :
il n'y a pas d'item ou les deux propositions passeraient. Les cinq couples annonces (a/à, et/est,
on/ont, son/sont, ou/où) sont tous presents, chacun deux fois, une fois dans chaque sens - c'est
la bonne facon de construire ce type d'exercice, parce qu'elle interdit a l'enfant de retenir
"c'est toujours celui avec l'accent".

**Les distracteurs sont plausibles par construction** : le distracteur est l'autre membre du
couple d'homophones. Il n'y a donc aucun distracteur absurde possible, et aucune devinette. C'est
le point fort de ce paquet.

#### 2. Rattachement au referentiel - exact

La ligne du referentiel CM1 est "Distinguer et utiliser correctement les principaux homophones
grammaticaux (`et/est`, `son/sont`, `a/à`...)". C'est bien cette competence-la que l'increment
travaille, pas une competence voisine plus facile. Rien a redire.

#### 3. Niveau et lisibilite - conformes au CM1

Phrases de cinq a sept mots, vocabulaire entierement courant, aucune tournure obscure. Un enfant
de CM1 lit cela sans effort, ce qui est ce qu'on veut : l'effort doit porter sur le choix du mot,
pas sur le dechiffrage de la phrase. La consigne "Quel mot complète la phrase ?" fait cinq mots
et est exacte. Le trou est marque par trois traits soulignes, bien visibles a 32 px gras.

#### 4. Biais et ton

Pas de condescendance, pas d'infantilisation, aucun classement ni comparaison a d'autres enfants.
Deux points mineurs, portes en reserve et non en motif : voir reserves 3 et 4.

#### 5. Ce que ce verdict ne dit pas

Il ne porte que sur le contenu `cm1-homophones` et sur les libelles de ce jeu. **La saillance des
boutons sur l'ecran de retour est refusee separement**, au titre du chantier Bootstrap, dans
`FICHE-bandit-manchot-additions-cp.md`. Ce refus concerne l'habillage commun aux trois jeux, donc
celui-ci aussi ; il ne remet pas en cause le Done pedagogique accorde ici sur le contenu.

#### Reserves - a convertir en stories par camille-po

1. **Dix items pour une partie de dix manches : chaque partie est identique.** L'enfant voit
   l'integralite du paquet a chaque fois, seul l'ordre change. Des la deuxieme partie il ne
   reste plus rien a apprendre, il ne reste qu'a se souvenir. Il faut plus d'items que de manches.
2. **Les phrases sont trop courtes pour travailler la competence a la profondeur du CM1.** Dans
   neuf items sur dix, le sujet est colle au trou ("Le chat ___ noir.", "Ils ___ un chien."), ce
   qui rend le choix quasi automatique. Le seul item qui demande vraiment de raisonner est
   "Papa ___ maman font la cuisine.", parce qu'il faut aller chercher "font" apres le trou. C'est
   le modele a suivre : la competence ne se travaille que quand la phrase oblige a regarder
   ailleurs que juste avant le trou. En l'etat, l'exercice est juste, mais il est de niveau CE1 ou
   CE2 plutot que CM1 - consolidation, pas apprentissage.
3. **Aucun sujet feminin dans tout le paquet.** "Il" apparait deux fois, "Ils" une fois ; "elle"
   et "elles" n'apparaissent jamais. Sur dix phrases dans un paquet de grammaire, c'est un
   desequilibre evitable, d'autant que le remplacement est gratuit ("Elle ___ mangé une pomme.").
4. **"Papa ___ maman font la cuisine." presuppose une configuration familiale.** Une occurrence
   sur dix, et par ailleurs non stereotypee sur les roles puisque les deux parents cuisinent
   ensemble - ce qui est a porter au credit de l'item. Je le signale sans en faire un motif :
   tous les enfants n'ont pas un papa et une maman. "Léa ___ Tom font la cuisine." dirait la
   meme chose grammaticalement.
5. **Deux propositions donnent une chance sur deux au hasard.** C'est inherent a l'exercice - on
   ne peut pas proposer "et" comme distracteur de "a/à" - et je ne le compte donc pas comme un
   defaut de conception. Mais il faut en tirer la consequence : **le score de ce jeu ne mesure
   pas grand-chose**, un enfant qui repond au hasard finit a 5 sur 10. A garder en tete le jour ou
   le score servira a autre chose qu'a s'encourager.
6. **Aucune aide n'est disponible dans ce jeu.** Meme constat structurel que pour
   `bandit-manchot-le-bon-son-cp`, motif 5 de sa fiche : un paquet `type: question` ne peut pas
   porter d'indice, parce que `QuestionContentSourceFactory` construit chaque `Question` sans
   champ `indice`. C'est moins grave ici qu'au CP - un enfant de CM1 est plus autonome, et
   l'increment reste utilisable sans - donc c'est une reserve et non un motif. Mais quand le
   dispositif sera ouvert aux paquets `question`, ce jeu doit en beneficier : "Remplace par
   *avait* pour t'aider." est exactement le genre d'indice qui apprend quelque chose.
7. **L'accueil ne dit pas a quel niveau s'adresse le jeu.** "Le bon petit mot" ne porte aucune
   mention de CM1 alors que "Calcul CP" porte la sienne. Question produit pour `camille-po`.
8. **"Tu veux du thé ___ du café ?"** propose a un enfant de choisir entre deux boissons
   d'adulte. Ce n'est pas grave et ce n'est pas faux, mais dans un paquet de dix phrases on peut
   trouver un choix qui parle a un enfant de neuf ans.

#### Suites hors de ma main

- `squad/programme/MATRICE-couverture.md`, ligne
  `francais | CM1 | Orthographe et grammaire | Distinguer et utiliser correctement les principaux
  homophones grammaticaux` : **reste `en cours`, ne passe pas encore a `couvert-valide`.**
  Motif, et il est franc : le Done pedagogique est accorde, mais le Done et la matrice ne disent
  pas la meme chose - le Done porte sur l'increment, la matrice porte sur la couverture d'une
  competence. La competence dit "**les principaux** homophones grammaticaux" et le referentiel
  laisse la liste ouverte par des points de suspension. Le paquet en couvre cinq : a/à, et/est,
  on/ont, son/sont, ou/où. **Manquent, parmi ceux qu'un CM1 rencontre couramment : ce/se, ces/ses,
  la/là/l'a, mais/mes, peu/peut.** Cinq couples sur une dizaine, ce n'est pas "les principaux".
  **Condition pour que je fasse passer cette ligne a `couvert-valide` : ces cinq couples ajoutes
  au paquet, avec la reserve 2 traitee dans le meme geste** (des phrases qui obligent a regarder
  ailleurs que juste avant le trou), parce qu'ajouter cinq couples faciles ne ferait qu'allonger
  un exercice de CE2. Je n'ai pas edite cette ligne, elle appartient a `camille-po`.
  **Addendum du 2026-08-06** : cette condition est precisee, sans etre rouverte, par l'arbitrage
  ci-dessous ("Arbitrage du 2026-08-06"). C'est desormais ce texte-la qui dit ce que j'attends.

---

### Arbitrage du 2026-08-06 - la famille `la/là/l'a` et le contour de "les principaux"

- **Nature** : ce n'est pas un audit et ce n'est pas un verdict. Rien n'a ete livre depuis le
  Done pedagogique ci-dessus, qui reste acquis tel quel. Je tranche ici **ma propre condition**,
  saisie par `theo-archi` via `sofia-sm` sur son avis de faisabilite de s05 (S5-6).
- **Ce que j'ai relu** : `cm1-homophones.yaml` (inchange, 10 items), la ligne du referentiel CM1,
  la section S5-6 du backlog et la section S5-6 de l'avis de faisabilite.

#### 1. D'abord, une confusion a lever : ce n'est pas ma propriete qui casse

`theo-archi` ecrit que le triplet casse "la propriete que `nadia-prof` porte au credit du paquet".
Je relis ce que j'ai ecrit, et ce n'est pas tout a fait cela. La propriete que j'ai creditee est :
**toute proposition affichee appartient a la meme famille d'homophones que la bonne reponse**,
donc aucun distracteur absurde et aucune devinette. Trois propositions tirees de `la/là/l'a`
respectent cette propriete integralement.

Ce qui casse, c'est `distracteurs.hasSize(1)`, c'est-a-dire l'**operationnalisation** de ma
propriete dans `PaquetsFrancaisS04Test`, qui est plus etroite que ce que j'ai valide. C'est une
distinction qui compte pour la suite : le jour ou un paquet aura besoin de trois propositions de
la meme famille, je ne le refuserai pas au motif de cette propriete-la. Et j'ajoute que trois
propositions au lieu de deux vont dans le sens de ma reserve 5, pas contre elle : une chance sur
trois mesure un peu mieux qu'une chance sur deux.

Je ne choisis donc pas ci-dessous pour sauver un test. Je choisis pour la raison qui suit.

#### 2. Ce que je retiens : `la/là/l'a` entre en deux paires, pivotant sur `la`

**Decision : deux paires d'items, `la`/`là` et `la`/`l'a`. La paire `là`/`l'a` n'est pas
demandee. Un seul distracteur par item, comme partout ailleurs dans le paquet.**

Le motif est pedagogique et il tient en deux points.

**Ce sont deux apprentissages differents, pas un.** `la` contre `là` est une affaire d'accent, et
le test que l'enfant applique est un test de sens : "est-ce que je peux dire *ici* ?". C'est
exactement la meme forme que `ou/où`, deja dans le paquet et deja validee. `la` contre `l'a` est
une tout autre affaire : c'est un verbe cache derriere une elision, et le test est un test de
temps, "est-ce que je peux dire *l'avait* ?". C'est la forme de `on/ont`, de `son/sont`, de
`a/à`. Un item a trois propositions demande a l'enfant de tenir les deux tests en meme temps, et
de choisir lequel appliquer avant de l'appliquer. Au CM1, c'est une marche de plus que la
competence n'en demande, et elle est ajoutee au moment precis ou l'enfant decouvre la famille.
On enseigne une regle a la fois ; on ne les fusionne qu'une fois chacune acquise.

**`la` est le pivot des fautes reelles.** Ce qu'un enfant de CM1 ecrit de travers, c'est `la` a la
place de `là` (accent oublie) et `la` a la place de `l'a` (apostrophe et verbe oublies). Les deux
fautes ont le meme mot fautif. La paire `là`/`l'a` ne correspond a aucune hesitation courante :
un enfant qui ecrit `là` pour `l'a` a d'abord rate `l'a`. Deux items pivotant sur `la` couvrent
donc les deux erreurs qui existent ; le troisieme appariement travaillerait une erreur qui n'en
est pas une.

**Ce que cela coute, et je l'assume** : deux items au lieu d'un, quatre une fois le point 5
applique. C'est plus de contenu a ecrire, pas moins. Ce n'est pas le choix commode.

**Consequence pour `PaquetsFrancaisS04Test`** : la famille se declare comme deux couples,
`la`/`là` et `la`/`l'a`. Le paquet compte alors onze couples pour dix familles. Que le test dise
"couple" la ou je dis "paire d'un meme ensemble d'homophones" ne me gene pas ; qu'il continue
d'exiger que le distracteur soit un homophone de la bonne reponse, si.

#### 3. Les quatre autres couples : deux d'entre eux cachent bien quelque chose

Aucun des quatre n'est un triplet dans la forme que prendra le paquet. Mais deux portent un
risque du meme ordre, et il vaut mieux le nommer maintenant qu'au moment de l'audit.

**`ces`/`ses` - le plus dangereux des cinq, et de loin.** Ce n'est pas un probleme de troisieme
membre, c'est un probleme d'ambiguite : dans la plupart des phrases, **les deux reponses sont
grammaticalement correctes**. "Il regarde ___ photos." accepte `ces` comme `ses`. Or la propriete
que j'ai creditee au paquet est que chaque phrase n'admet qu'une seule lecture. Un item
`ces`/`ses` mal ecrit la detruit vraiment, et il apprend a l'enfant que la reponse se devine.
La regle a respecter : pour `ces`, la phrase porte une designation explicite (`là`, `là-bas`,
`ici`, une exclamation qui montre) ; pour `ses`, le possesseur est nomme dans la phrase.
Je dis franchement la limite : sur ce couple, la levee d'ambiguite est **pragmatique et non
grammaticale**. Le seuil que j'appliquerai a l'audit : un adulte doit trouver l'autre lecture
franchement incongrue, pas seulement moins probable. J'auditerai ces deux items un par un et je
refuserai tout item ou les deux lectures passent.

**`peu`/`peut` et `mais`/`mes` - la famille a des membres absents des propositions.**
`peu`/`peut` vit a cote de `peux` ; `mais`/`mes` vit a cote de `mets`, `met` et `m'est`. Une
phrase dont la vraie reponse est un de ces mots-la, avec deux propositions qui ne le contiennent
pas, apprend une fausse alternative a l'enfant : elle lui fait choisir entre deux mots faux.
Regles : **aucun sujet `je` ni `tu` dans un item `peu`/`peut`** ("Tu ___ venir" appelle `peux`) ;
**aucun contexte du verbe *mettre*** dans un item `mais`/`mes`.

**`ce`/`se` - le plus propre des quatre.** Une seule prudence : eviter les tournures ou les deux
existent, `ce sera` et `se sera` etant toutes deux du francais correct ("Il ___ sera trompé").

**Phrases modeles**, pour lever toute discussion sur ce que je demande. Elles illustrent le
couple et la levee d'ambiguite, **pas** la profondeur exigee par ma reserve 2 : la moitie des
items neufs doit en plus obliger a regarder ailleurs que juste avant le trou.

| Famille | Un sens | L'autre sens |
|---|---|---|
| `la`/`là` | "Pose ton cartable ___, près de la porte." -> `là` | "Léa cherche ___ règle dans sa trousse." -> `la` |
| `la`/`l'a` | "Tom a perdu sa gomme, puis il ___ retrouvée." -> `l'a` | "Il ___ regarde souvent." -> `la` |
| `ce`/`se` | "___ matin, il pleut." -> `Ce` | "Le chat ___ cache sous le lit." -> `se` |
| `ces`/`ses` | "Regarde ___ oiseaux là-bas !" -> `ces` | "Il range son cahier et ___ crayons." -> `ses` |
| `mais`/`mes` | "Il fait froid ___ le soleil brille." -> `mais` | "J'ai perdu ___ clés." -> `mes` |
| `peu`/`peut` | "Il ___ pleuvoir cet après-midi." -> `peut` | "Il reste ___ de pain." -> `peu` |

#### 4. Ce que "les principaux homophones" recouvre pour un CM1

Le referentiel du depot ecrit "les principaux homophones grammaticaux (`et/est`, `son/sont`,
`a/à`...)" et laisse la liste ouverte par des points de suspension. `camille-po` doit ecrire un
critere, elle ne peut pas ecrire des points de suspension. Voici ce que je mets derriere le mot.

**Ma reference, dite honnetement.** Les programmes de cycle 3 traitent la distinction des
homophones grammaticaux dans l'orthographe grammaticale et **ne donnent pas de liste fermee**. Il
n'y a donc pas de liste officielle a citer, et je n'en inventerai pas une. Ce qui suit est la
liste sur laquelle convergent les progressions de CM1 telles que je les ai pratiquees en classe :
c'est un jugement professionnel, et il s'assume comme tel.

**Le critere, qui vaut mieux que la liste**, parce qu'il permet de trancher un cas non prevu. Un
couple est "principal" au CM1 quand les quatre conditions sont reunies :

1. les deux membres sont des **mots grammaticaux** (determinant, pronom, preposition, conjonction,
   forme verbale d'auxiliaire), pas des mots de vocabulaire ;
2. la confusion est **frequente dans les ecrits d'enfants** de cet age ;
3. il existe une **manipulation que l'enfant peut appliquer seul** (remplacer par un autre mot,
   changer le temps, changer le nombre) ;
4. la distinction est necessaire **pour ecrire**, pas seulement pour lire.

**La liste qui en decoule au CM1**, dix familles : `a/à`, `et/est`, `son/sont`, `on/ont`, `ou/où`,
`ce/se`, `ces/ses`, `la/là/l'a`, `mais/mes`, `peu/peut`. Ce sont les cinq deja couverts plus les
cinq de ma condition. C'est ce que `camille-po` peut ecrire en critere, au mot pres.

**Ce que la liste laisse dehors, et que je nomme plutot que de le taire :**

- **`c'est`/`s'est` (et `sait`/`sais`) satisfait mes quatre criteres.** C'est meme l'une des
  fautes les plus frequentes au CM1. **Je ne l'ajoute pourtant pas a ma condition**, et le motif
  n'est pas pedagogique : j'ai ecrit ma condition, l'equipe s'est engagee dessus, et une condition
  qui grossit apres avoir ete engagee n'est plus une condition, c'est un deplacement de but. Le
  `couvert-valide` que je prononcerai portera donc sur dix familles, avec cette limite **nommee et
  non cachee**. `c'est`/`s'est` est le premier candidat d'une story ulterieure, et je le signale
  des maintenant a `camille-po` pour qu'elle le range dans le backlog des sprints suivants.
- **`leur`/`leurs`** : homophones, mais ce qui se joue la est un accord en nombre, pas une
  discrimination de nature. Cela se travaille avec les accords du groupe nominal, ligne voisine du
  referentiel. Hors de cette competence.
- **`sans`/`s'en`, `dans`/`d'en`, `quand`/`quant`/`qu'en`** : criteres 1 et 3 satisfaits, critere 2
  non - ces confusions apparaissent surtout au CM2 et au college. Hors de "principaux" au CM1.

**Point pour `camille-po` via `sofia-sm`** : le referentiel `competences-cm1.md` gagnerait a porter
cette liste a la place de ses points de suspension. Je ne l'edite pas, ce fichier n'est pas le
mien - mais tant qu'il reste ouvert, chaque audit rejouera cet arbitrage.

#### 5. "Dans les deux sens" : je la reprends a mon compte, elle devient bloquante

`camille-po` a ajoute a S5-6 l'exigence que chaque couple soit represente dans les deux sens, en
prenant soin de l'attribuer explicitement a elle-meme et de la declarer non bloquante pour ma
condition. L'attribution etait juste : je ne l'avais pas demandee. **Je la reprends, et elle
devient bloquante pour `couvert-valide`.**

Le motif, et il est de couverture, pas de style. La competence dit "**distinguer et utiliser**
correctement". Un couple present dans un seul sens ne fait jamais produire le second membre :
si `peut` n'est jamais la bonne reponse, l'enfant n'a jamais eu a choisir `peut`, il a seulement
appris a reconnaitre `peu`. La moitie de la competence n'est pas travaillee, donc pas couverte.
C'est exactement le registre de la ligne de matrice, et c'est pourquoi cela ne peut pas rester une
preference de forme. J'avais d'ailleurs deja porte ce point au credit du paquet dans mon verdict,
en notant que chaque couple y figure "une fois dans chaque sens" ; il serait incoherent de saluer
la propriete sur cinq couples et de l'abandonner sur les cinq suivants.

**Ce que cela veut dire precisement**, y compris pour la famille a trois membres, puisque
`theo-archi` demande que ce soit ecrit sans ambiguite : l'exigence porte sur les **paires**, pas
sur les familles. Avec la decision du point 2, `la/là/l'a` compte pour deux paires, et chacune
doit apparaitre dans ses deux sens - soit quatre items, ou `la` est la bonne reponse deux fois.
C'est comptable sur les bonnes reponses du paquet, donc automatisable, comme `theo-archi` le
demandait.

**Ce que cela coute** : douze items neufs au lieu de dix (cinq familles, six paires, deux sens).
Et c'est un effet secondaire heureux : vingt-deux items pour dix manches, cela entame serieusement
ma reserve 1 - l'enfant ne verra plus l'integralite du paquet a chaque partie.

#### 6. Recapitulatif de ma condition, dans sa forme desormais definitive

Pour que je fasse passer la ligne francais CM1 de `MATRICE-couverture.md` a `couvert-valide` :

1. les cinq familles manquantes sont ajoutees : `ce/se`, `ces/ses`, `la/là/l'a`, `mais/mes`,
   `peu/peut` ;
2. `la/là/l'a` entre en deux paires pivotant sur `la` (`la`/`là` et `la`/`l'a`), un seul
   distracteur par item ;
3. chaque paire, ancienne et nouvelle, est representee dans les deux sens ;
4. aucun item n'admet deux lectures correctes - vigilance particuliere sur `ces`/`ses`, et aucun
   item dont la vraie reponse serait un membre absent des propositions (`peux`, `mets`, `met`) ;
5. au moins la moitie des items neufs oblige a regarder ailleurs que juste avant le trou
   (reserve 2, inchangee) ;
6. et j'aurai joue l'increment avant de le dire.

Rien d'autre. Ce qui n'est pas dans cette liste - dont `c'est`/`s'est` - ne bloque pas
`couvert-valide` et ne pourra pas m'etre oppose comme l'ayant bloque. Je n'ai edite ni le backlog,
ni la matrice, ni le referentiel : ce sont les fichiers de `camille-po`.

---

### Verdict du sprint s05 - audit du 2026-08-07, le paquet a 22 items

- **Verdict sur l'increment** : **VALIDE SOUS RESERVE.** Le Done pedagogique accorde en s04 est
  reconduit et elargi aux douze items neufs et aux vingt-deux indices. Ce jeu peut etre mis devant
  une classe de CM1, et il est nettement meilleur qu'a mon audit precedent.
- **Verdict sur la ligne de matrice** : **la condition n'est PAS levee. La ligne
  `francais | CM1 | Orthographe et grammaire | ... homophones grammaticaux` reste `en cours`.**
  Deux points precis manquent, section 3.
- **Commit audite** : `c0b5241` (`HEAD` de `round-two`).
- **Moyens** : audit **joue**, non degrade. `mvn -B clean package` depuis un arbre propre sous un
  `JAVA_HOME` sur le JDK 21 reel du poste (`java -version` : `21.0.10`), jar relance en local sur
  un port distinct de celui de l'utilisatrice. Parties completes jouees : une en repondant juste
  de bout en bout (score 10 sur 10), une en me trompant a chaque manche, une en enchainant les
  echecs pour faire venir l'aide. Ecrans mesures au navigateur sans tete en 1280 x 900 et en
  360 x 640, dans les memes conditions que mes refus precedents.

#### 1. Le defaut signale par l'utilisatrice est corrige - verifie en jeu

Les phrases n'affichent plus la reponse. Les vingt-deux enonces portent un trou `___`, et je l'ai
vu a l'ecran manche apres manche, pas seulement dans le fichier. C'etait le defaut le plus grave
possible pour ce jeu : un exercice a trou sans trou ne demande rien a l'enfant.

Effet secondaire mesure et reel : **une partie tire dix items distincts sur vingt-deux**, et deux
parties consecutives ne presentent pas les memes phrases. **Ma reserve 1 est levee** - l'enfant ne
voit plus l'integralite du paquet a chaque partie, il lui reste quelque chose a apprendre a la
deuxieme.

#### 2. Ma condition en six points, verifiee point par point en jouant

| Point de ma condition | Verdict |
|---|---|
| 1. Les cinq familles manquantes sont ajoutees (`ce/se`, `ces/ses`, `la/là/l'a`, `mais/mes`, `peu/peut`) | **tenu** - les cinq sont la, jouees a l'ecran |
| 2. `la/là/l'a` en deux paires pivotant sur `la`, un seul distracteur par item | **tenu** - `la`/`là` et `la`/`l'a`, jamais `là`/`l'a`, deux propositions par item partout |
| 3. Chaque paire, ancienne et nouvelle, dans les deux sens | **tenu** - onze paires, vingt-deux items, `la` bonne reponse deux fois |
| 4. Aucun item n'admet deux lectures correctes | **tenu** - detail ci-dessous |
| 5. Au moins la moitie des items neufs oblige a regarder ailleurs que juste avant le trou | **NON TENU** - 5 items sur 12, il en faut 6 |
| 6. J'aurai joue l'increment | **tenu** |

##### Point 4 - mes trois risques nommes, leves un par un

**`ces`/`ses`, celui que j'annoncais comme le plus dangereux : les deux items passent mon seuil.**
Je les ai regardes un par un, en cherchant l'autre lecture plutot qu'en la supposant absente.

- "Regarde ___ oiseaux là-bas, sur la branche !" -> `ces`. La designation explicite que j'exigeais
  est presente deux fois, par `là-bas` et par l'exclamation. Et `ses` n'est licencie par rien :
  aucun possesseur n'est nomme dans la phrase, donc le possessif y pend dans le vide. Un adulte
  trouve l'autre lecture incongrue, pas seulement moins probable. C'est mon seuil, il est atteint.
- "Léa range son cahier et ___ crayons avant de partir." -> `ses`. Le possesseur est nomme
  (`Léa`), et le parallele avec `son cahier` verrouille la coordination. `ces` demanderait un
  geste de designation que rien ne porte. Tenu.

Les deux items respectent ma regle **dans les deux sens** : `ces` n'est retenu que la ou il y a
designation, `ses` que la ou il y a un possesseur nomme. C'est exactement ce que je demandais et
c'est le point que je craignais le plus.

**`peu`/`peut` : aucun sujet `je` ni `tu`.** Les deux items ont `il` pour sujet ("Il reste ___ de
pain...", "...il ___ encore pleuvoir..."). `peux` n'est jamais la vraie reponse cachee. Tenu.

**`mais`/`mes` : aucun contexte du verbe *mettre*.** Les deux items emploient `brille` et `rangé`.
Ni `mets`, ni `met`, ni `m'est` ne sont la vraie reponse d'un item. Tenu.

J'ajoute les deux autres familles, verifiees au meme titre : `ce`/`se` evite le piege `ce sera` /
`se sera` que j'avais nomme ; `la`/`l'a` distingue proprement `range` (present) de `retrouvé`
(participe). **Zero erreur factuelle sur les vingt-deux enonces.**

##### Point 5 - l'ecart signale par marc-dev : son motif est recevable a moitie, ma condition tient

`marc-dev` me signale de lui-meme que seuls 4 de ses 12 items exigent de regarder au-dela du mot
voisin du trou. **Qu'il le signale plutot que de le masquer est ce qui permet de trancher au lieu
de decouvrir**, et je le porte a son credit.

Mon propre comptage en donne 5 et non 4 - je compte "Pose ton cartable ___, à côté du mien." parce
que le voisin de droite est une virgule et que la levee vient de `à côté du mien`. Que ce soit 4
ou 5 ne change rien : **il en faut 6.**

**Son motif est vrai la ou il le dit.** Pour `ce`/`se`, et pour le versant determinant de
`mais`/`mes` et de `la`/`là`, la discrimination *est* determinant contre verbe, ou determinant
contre conjonction : le diagnostic porte sur le mot voisin par nature de la categorie
grammaticale. Les indices l'ecrivent d'ailleurs eux-memes ("Le mot qui suit le trou est un nom
('cahier')"). Forcer ces items-la a regarder plus loin fabriquerait de la complexite postiche, et
il a raison de dire que ce serait courir vers l'ambiguite, c'est-a-dire vers mon point 4, qui est
bloquant. **Je ne lui demanderai pas cela.**

**Mais son motif ne couvre pas tout le paquet, et il le prouve lui-meme.** Quatre de ses items -
"...elle ___ enfin retrouvé...", "Regarde ___ oiseaux là-bas...", "...son cahier et ___
crayons...", "...il ___ encore pleuvoir..." - obligent bel et bien a regarder plus loin, et ils
sont excellents. Ils sortent de `la/l'a`, de `ces/ses` et de `peu/peut`, c'est-a-dire des familles
ou la place du mot decisif est libre. **Ce qui est possible trois fois l'est cinq fois.** Le motif
"c'est intrinsequement local" est donc vrai pour certaines familles et pas pour celles-la.

**Je ne leve donc pas le point 5, et je le retrecis pour qu'il soit tenable** : je ne demande pas
que six items quelconques deviennent non locaux, je demande **deux items neufs de plus dont le mot
decisif n'est pas voisin du trou, pris dans `la`/`l'a`, `ces`/`ses` ou `peu`/`peut`** - les trois
familles ou marc-dev a deja montre que cela se fait sans forcer. `ce`/`se` et le versant
determinant de `mais`/`mes` sont explicitement dispenses. Cela porte le paquet a 24 items, ce qui
va dans le sens de ma reserve 1.

Je precise pourquoi je ne cede pas alors que l'ecart est d'un item ou deux. Ma condition a ete
ecrite avant le sprint, l'equipe s'est engagee dessus, et j'ai refuse pour ce motif d'y ajouter
`c'est`/`s'est` en cours de route. Une condition qui ne grossit pas apres coup ne retrecit pas non
plus parce que ce serait plus rapide. Ce que je fais en revanche, c'est nommer exactement le
reste a faire pour qu'il soit petit, borne et actionnable sans me redemander ce que je voulais.

#### 3. Ce qui manque pour `couvert-valide`, exactement, et rien de plus

Deux points. Ils sont les seuls.

1. **Deux items neufs de plus dont le mot decisif n'est pas voisin du trou**, pris dans
   `la`/`l'a`, `ces`/`ses` ou `peu`/`peut` (point 5 ci-dessus). Les cinq items neufs deja non
   locaux comptent, il ne s'agit pas de tout reecrire.
2. **L'indice de "J'ai rangé ___ affaires avant de partir à l'école." est a corriger.** Il dit
   *"Remplace par 'les miennes' : ce sont les affaires à moi, alors c'est 'mes'."* C'est le seul
   des quinze indices de substitution du paquet qui **ne montre pas la phrase substituee**, et il
   ne la montre pas parce qu'elle n'existe pas : un enfant qui applique l'instruction obtient
   *"J'ai rangé les miennes affaires"*, qui n'est pas du francais. La conclusion de l'indice est
   juste, sa methode ne l'est pas. Or l'indice est **le seul endroit de ce jeu ou l'on enseigne
   quelque chose** - tout le reste est un choix entre deux boutons - et il est servi a l'enfant qui
   vient d'echouer trois fois de suite, celui qui est le moins en etat de reperer que l'aide est
   fausse. La correction tient en une ligne et le modele est deja dans le paquet, a l'item `son` :
   *"Remplace par 'tes' : 'J'ai rangé tes affaires' fonctionne, alors c'est 'mes'."*

Une fois ces deux points faits, je rejoue et je fais passer la ligne. Rien d'autre ne bloque : les
points 1, 2, 3, 4 et 6 de ma condition sont tenus et ne seront pas rouverts.

#### 4. Ce qui est bon et que je dis, parce qu'un controle qui ne dit rien quand ca va bien ne prouve rien

- **Les vingt et un autres indices sont justes et bien tournes.** La manipulation est nommee, la
  phrase substituee est montree, la conclusion suit. "Remplace par 'à cet endroit'", "Remplace par
  'l'avait'", "'là-bas' montre ce qu'on regarde" : c'est de l'enseignement, pas de la correction.
  C'etait exactement ce que ma reserve 6 appelait, et elle est levee.
- **L'aide se declenche a la bonne manche.** Joue reellement : trois echecs consecutifs, l'indice
  apparait a la quatrieme et pas avant. **Ma reserve 6 est levee.**
- **Les phrases neuves sont d'un autre calibre que les anciennes** : huit a treize mots, des
  subordonnees ("Même si le ciel est bien dégagé ce matin..."), des propositions coordonnees. Le
  vocabulaire reste entierement accessible a un CM1 et l'effort porte ou il doit porter.
- **Les distracteurs restent tous des homophones de la bonne reponse.** Aucune devinette, aucun
  distracteur absurde, sur les vingt-deux items.
- **Aucun biais nouveau.** Les items neufs font apparaitre `Léa` quatre fois comme sujet agissant,
  ce qui corrige en partie mon desequilibre de la reserve 3 sans qu'on me l'ait demande. Aucune
  configuration familiale presupposee dans les douze items neufs, aucun stereotype de role.

#### 5. Reserves nouvelles - a convertir en stories par camille-po, non bloquantes

9. **"___ cahier appartient à mon petit frère." propose `ce` et `se` en minuscules en tete de
   phrase.** Les deux autres items qui commencent par le trou capitalisent leurs propositions
   (`On`/`Ont`, `Où`/`Ou`). C'est la seule incoherence interne du paquet. L'enfant ne lit jamais la
   phrase reconstituee, donc il ne voit pas de majuscule manquante, mais dans un exercice
   d'orthographe on ne propose pas comme bonne reponse un mot mal capitalise pour sa place.
   Capitaliser les deux propositions, comme aux deux autres items.
10. **L'indice donne la reponse en plus de la methode.** "...alors c'est 'peu'." Apres trois
    echecs, montrer le raisonnement complet est defendable, et je ne le refuse pas ; mais la
    manche portant l'indice devient un point gratuit. Si le score doit un jour mesurer quelque
    chose, l'indice devra s'arreter a la manipulation et laisser l'enfant l'appliquer.
11. **L'indice de "Il ___ range soigneusement dans son cartable." emploie "un participe"**, sans
    "passe". C'est le mot le plus abstrait des vingt-deux indices, et l'item est deja le plus
    subtil du paquet puisque tout se joue sur un accent absent. Il reste au niveau, mais il est en
    haut de la fourchette CM1 : "ce n'est pas 'rangé', donc ce n'est pas 'l'a'" dirait la meme
    chose avec un mot de moins.
12. **Le pronom `la` de "Il ___ range soigneusement dans son cartable." n'a aucun antecedent.**
    L'enfant ne peut pas verifier ce que `la` remplace. Ce n'est pas faux et cela n'empeche pas de
    repondre, mais une phrase isolee qui emploie un pronom sans referent apprend une tournure
    bancale.

Mes reserves 2, 3, 4, 5, 7 et 8 de l'audit precedent restent ouvertes en l'etat, sauf la 3 que les
items neufs ameliorent sans la fermer. Les reserves 1 et 6 sont levees, dites ci-dessus.

#### 6. Habillage et correctifs communs aux deux jeux

Le rhabillage du gabarit (en-tete, menu, centrage) et les deux correctifs fonctionnels du score
concernent les trois jeux et pas celui-ci en propre. **Je les tranche dans
`FICHE-bandit-manchot-additions-cp.md`, verdict du 2026-08-07**, qui est depuis s02 le lieu ou
j'ecris l'habillage commun. Resume, pour qu'on n'ait pas a chercher : l'en-tete est **accorde sous
reserves**, le comportement du double appui est **valide**, et **aucun de ces verdicts ne remet en
cause le Done pedagogique accorde ici**.

---

### Verdict du sprint s06 - audit du 2026-08-10, le paquet a 24 items : ma condition est levee

- **Verdict sur l'increment** : **VALIDE.** Done pedagogique accorde, sans reserve bloquante. Les
  reserves qui restent ouvertes sont nommees en section 5 et aucune ne conditionne quoi que ce soit.
- **Verdict sur la ligne de matrice** : **ma condition en six points est LEVEE. La ligne
  `francais | CM1 | Orthographe et grammaire | ... homophones grammaticaux` passe a
  `couvert-valide`.** `camille-po` peut editer la ligne.
- **Commit audite** : `be60b10` (`HEAD` de `round-two`).
- **Moyens** : audit **joue**, non degrade. Worktree dedie hors de l'arbre du depot, lignee
  verifiee (`outils/verifier-lignee.sh`). JDK constate par `outils/verifier-jdk.sh` : `java du
  PATH : 1.8.0_202 | JDK de compilation : 21.0.10 | JAVA_HOME : vide | toolchains lu par Maven :
  non (fichier present sous un nom non lu)` ; `JAVA_HOME` pointe sur ce JDK 21 le temps de la
  commande, jamais ecrit dans un fichier commite. Fat jar reconstruit, lance sur un port dedie.
  **Treize parties completes jouees** - cinq en me trompant a chaque manche pour faire venir l'aide,
  puis huit de plus dans le seul but de faire sortir du tirage les items que je devais lire a
  l'ecran. Les vingt-quatre enonces ont ete vus a l'ecran, et vingt-trois des vingt-quatre indices.

#### 1. Les deux items neufs que je demandais : tenus, un par un

Ma condition du 2026-08-07 demandait, au chiffre pres, **deux items neufs de plus dont le mot
decisif n'est pas voisin du trou**, pris dans `la`/`l'a`, `ces`/`ses` ou `peu`/`peut`. Les deux sont
livres, et ils sont pris dans deux des trois familles que j'avais nommees.

**Item 23, famille `la`/`l'a`** : *"Tom cherchait sa gomme depuis le début du cours, et son voisin
___ enfin repérée sous la table."* -> `l'a`.

- Le voisin de gauche est `voisin`, un nom : il ne tranche pas, puisque `la` derriere un nom sujet
  est parfaitement possible comme pronom ("son voisin la regarde").
- Le voisin de droite est `enfin`, un adverbe : il elimine la lecture determinant, mais **pas** la
  lecture pronom.
- **Le mot decisif est `repérée`**, un participe passe, qui exige l'auxiliaire. Il est separe du trou
  par `enfin`. C'est exactement la structure de l'item 13 que j'avais deja creditee en s05
  ("...elle ___ enfin retrouvé ce matin"). **Tenu.**
- Exactitude : l'accord de `repérée` au feminin est juste, le pronom `l'` a un antecedent explicite
  (`sa gomme`) - ce qui repond au passage, sans qu'on me l'ait demande, au defaut que je signalais en
  reserve 12. Le melange imparfait / passe compose est du francais correct.

**Item 24, famille `peu`/`peut`** : *"Après cette longue randonnée en montagne, le vieux chien ne
___ plus vraiment courir comme avant."* -> `peut`.

- **Le mot decisif est `courir`**, un infinitif, separe du trou par `plus vraiment`. C'est la
  structure de l'item 22 que j'avais deja creditee en s05 ("...il ___ encore pleuvoir avant le
  soir"). **Tenu.**
- Exactitude : le sujet n'est ni `je` ni `tu`, donc `peux` n'est pas la vraie reponse cachee - ma
  regle du 2026-08-06 sur cette famille est respectee. L'indice montre la phrase substituee en
  entier et elle est du francais : *"Remplace par 'pouvait' : 'le vieux chien ne pouvait plus
  vraiment courir comme avant' fonctionne, alors c'est 'peut'."*

**Le compte est donc de sept items neufs non locaux sur quatorze** (les cinq de s05 plus ces deux).
Il en fallait sept. **Point 5 de ma condition : tenu.**

##### Une limite de mon propre critere, que je nomme et qui ne bloque rien

Je l'ecris parce que je l'ai vue en relisant l'item 24 et qu'il serait malhonnete de la taire.

Dans "ne ___ plus", **le mot `ne`, immediatement a gauche du trou, exige a lui seul un verbe** et
suffit donc a exclure `peu`. Mon critere, tel que je l'ai ecrit ("le mot decisif n'est pas voisin du
trou"), regarde ou se trouve le mot qui licencie la reponse ; ici c'est `courir`, et le critere est
tenu. Mais il existe un indice local qui permet de s'en passer.

**Cette limite ne peut pas servir a refuser l'item 24, parce qu'elle vaut a l'identique pour
l'item 22 que j'ai deja credite** : dans "il ___ encore pleuvoir", le `il` immediatement a gauche
exige lui aussi un verbe. Refuser l'un en gardant l'autre serait relever ma barre apres coup, et
rendrait ma condition inatteignable dans cette famille apres que l'equipe s'y est engagee. **Je ne
deplace pas mes poteaux : les deux items comptent.**

Ce que j'en fais a la place : **une remarque neuve et datee du 2026-08-10**, pour une story
ulterieure si `camille-po` veut aller plus loin - une version plus exigeante du critere demanderait
qu'aucun mot voisin du trou, meme un mot grammatical, ne suffise a trancher. Je ne l'exige pas
aujourd'hui, et elle ne pourra pas etre presentee comme ayant bloque `couvert-valide`.

#### 2. L'indice de "J'ai rangé ___ affaires" : corrige, et lu a l'ecran

Le second des deux points qui manquaient. L'indice servi par l'application est desormais :

> Remplace par 'tes' : 'J'ai rangé tes affaires' fonctionne, alors c'est 'mes'.

C'est **le texte que j'avais dicte, au mot pres**, et je l'ai lu sur la reponse HTTP apres l'avoir
fait apparaitre en jeu, pas seulement dans le fichier YAML. La methode est desormais applicable : un
enfant qui substitue `tes` obtient une phrase francaise, ce qui n'etait pas le cas de *"les miennes
affaires"*. Les quinze indices de substitution du paquet montrent maintenant tous la phrase
substituee. **Point tenu.**

#### 3. Ma condition en six points, recapitulee et close

| Point de ma condition du 2026-08-06 | Verdict au 2026-08-10 |
|---|---|
| 1. Les cinq familles manquantes sont ajoutees | **tenu** (acquis en s05) |
| 2. `la/là/l'a` en deux paires pivotant sur `la`, un seul distracteur par item | **tenu** (acquis en s05) |
| 3. Chaque paire, ancienne et nouvelle, dans les deux sens | **tenu** - onze paires, les deux sens partout ; les items 23 et 24 ne font que renforcer `l'a` et `peut` |
| 4. Aucun item n'admet deux lectures correctes | **tenu** - re-verifie sur les deux items neufs, section 1 |
| 5. Au moins la moitie des items neufs oblige a regarder ailleurs que juste avant le trou | **tenu** - 7 sur 14 |
| 6. J'aurai joue l'increment | **tenu** - treize parties |

**Cette condition est close. Elle ne sera pas rouverte, et rien ne s'y ajoute.** Le
`couvert-valide` que je prononce porte sur les dix familles que j'avais listees le 2026-08-06, avec
la limite que j'avais nommee ce jour-la et que je re-nomme ici pour qu'elle reste visible :
**`c'est`/`s'est` (et `sait`/`sais`) n'y est pas.** Il satisfait mes quatre criteres et c'est l'une
des fautes les plus frequentes au CM1 ; je ne l'ai pas ajoute a ma condition parce qu'une condition
qui grossit apres avoir ete engagee n'est plus une condition. Il reste le premier candidat d'une
story ulterieure. `couvert-valide` veut donc dire ici : **les dix familles principales sont
travaillees dans les deux sens, avec une profondeur de CM1 sur la moitie des items neufs** - pas
"tous les homophones grammaticaux du francais".

#### 4. Ce que j'ai verifie en plus, et qui tient

Un controle qui ne dit rien quand ca va bien ne prouve rien.

- **Zero erreur factuelle sur les vingt-quatre enonces**, dont les deux neufs, re-verifies un par
  un. Chaque phrase n'admet qu'une seule lecture grammaticalement correcte.
- **Les vingt-quatre distracteurs sont tous des homophones de la bonne reponse.** Aucune devinette.
- **Le tirage fonctionne** : sur cinq parties consecutives j'ai vu vingt-deux enonces distincts sur
  vingt-quatre, et aucune partie ne repetait la precedente. Ma reserve 1, levee en s05, le reste, et
  vingt-quatre items pour dix manches est la bonne proportion.
- **L'aide se declenche a la bonne manche** : trois echecs consecutifs, indice a la quatrieme, verifie
  a nouveau en jeu.
- **Accents intacts a l'ecran** sur les deux items neufs (`repérée`, `randonnée`), verifies sur la
  reponse HTTP et sur le rendu au navigateur, pas seulement dans le fichier.
- **Aucun biais nouveau.** Les deux items neufs introduisent `Tom` comme sujet et un chien age ;
  aucune configuration familiale presupposee, aucun stereotype de role.
- **Rendu reel** : a 1280 px comme a une largeur de viewport reellement de 360 px, l'enonce, les deux
  propositions et l'indice tiennent au-dessus du pli, et l'entree de menu de l'en-tete est bien
  masquee sur les ecrans de jeu (voir `FICHE-bandit-manchot-additions-cp.md`, verdict S6-9 du
  2026-08-10).

#### 5. Reserves ouvertes - aucune n'est bloquante, aucune ne l'a jamais ete

Elles restent a convertir en stories par `camille-po`. Je les recapitule pour qu'on n'ait pas a
reconstituer l'historique : **2** (les phrases anciennes restent locales), **3** (desequilibre des
sujets, ameliore mais non ferme), **4** ("Papa ___ maman" presuppose une configuration familiale),
**5** (deux propositions donnent une chance sur deux, le score ne mesure pas grand-chose), **7**
(l'accueil ne dit pas le niveau), **8** ("du thé ou du café" est un choix d'adulte), **9** (`ce`/`se`
proposes en minuscules en tete de phrase, seule incoherence interne du paquet), **10** (l'indice
donne la reponse en plus de la methode), **11** ("un participe" est le mot le plus abstrait des
indices), **12** (le pronom `la` de l'item 14 n'a aucun antecedent). Les reserves 1 et 6 sont
levees depuis s05.

Reserve nouvelle, datee du 2026-08-10, non bloquante :

13. **La distribution des vingt-quatre items n'est plus equilibree entre les deux sens.** Avec les
    items 23 et 24, `l'a` est la bonne reponse deux fois contre une pour `la` dans cette paire, et
    `peut` deux fois contre une pour `peu`. Mon point 3 exigeait la presence des deux sens, pas leur
    egalite, donc il est tenu ; mais un enfant qui joue dix manches a desormais un peu plus de
    chances de rencontrer le membre verbal que le membre determinant de ces deux paires. Un item de
    plus dans chacun des deux sens manquants remettrait le paquet a plat, a vingt-six items. Sans
    consequence sur ce que le jeu enseigne.

#### 6. Ce dont je reste seule garde sur ce jeu

`PaquetsFrancaisS04Test` epingle les couples, l'unicite du distracteur apparie, la variete sur vingt
manches, la non-regression des dix items d'origine et le texte corrige de l'indice `mes`. **Ce
qu'aucun test ne dit, et que je porte seule :**

1. **Qu'aucun item n'admette deux lectures correctes.** C'est mon point 4, le plus dangereux du
   paquet sur `ces`/`ses`, et il se juge phrase par phrase par un adulte qui cherche l'autre
   lecture. Aucun garde-fou ne peut le faire.
2. **Que le mot decisif ne soit pas voisin du trou.** Mon point 5 est un jugement sur la syntaxe de
   chaque phrase ; `marc-dev` et moi l'avons compte differemment (4 contre 5) sur le meme paquet, ce
   qui montre bien qu'il n'est pas mecanisable en l'etat.
3. **Que les indices enseignent une manipulation applicable.** L'indice `mes` est desormais epingle
   par un test, mais seulement dans sa lettre : rien n'empeche un indice futur d'instruire une
   substitution qui n'est pas du francais. Le refus que je prononce le meme jour sur
   `bandit-manchot-conjugaison-present-cm1` porte exactement sur ce point-la.

#### 7. Suites hors de ma main

- `squad/programme/MATRICE-couverture.md`, ligne `francais | CM1 | Orthographe et grammaire |
  Distinguer et utiliser correctement les principaux homophones grammaticaux` : **passe de
  `en cours` a `couvert-valide`**, sur ce verdict. Je n'ai pas edite cette ligne, elle appartient a
  `camille-po`, et le texte de la note de matrice doit dire ce que la section 3 dit : dix familles,
  `c'est`/`s'est` nomme et hors perimetre.
- `squad/programme/francais/competences-cm1.md` gagnerait toujours a porter la liste des dix
  familles a la place de ses points de suspension. Point deja remonte le 2026-08-06 et toujours
  ouvert ; il n'a pas bloque `couvert-valide` et n'aurait pas du.
- Les dix reserves de la section 5 sont du backlog pour `camille-po`. Aucune ne conditionne le
  statut de la ligne.

---

### Arbitrage prealable au developpement - chantier machine a sous (S7-1 a S7-4), du 2026-08-10

**Ce bloc n'est pas un Done pedagogique et il ne touche pas au verdict VALIDE du 2026-08-10.**
L'arbitrage complet est ecrit une seule fois, dans `FICHE-bandit-manchot-additions-cp.md`, arbitrage
du 2026-08-10 : la mecanique est commune aux quatre jeux et je ne recopie pas dix pages dans quatre
fichiers. Ce qui concerne **ce jeu-ci** en particulier :

1. **Deux colonnes, et c'est valide.** Les 24 items de ce paquet portent une bonne reponse et un seul
   distracteur. Deux colonnes ne trahissent rien : l'exercice **est** binaire, `son` ou `sont`, `a` ou
   `à`. Mesure sur le rendu a 360 px : **158 px par colonne**, tres confortable pour des mots de deux a
   quatre lettres. Aucune reserve.
2. **Jamais de troisieme colonne vide** pour atteindre trois. Un enfant presse ce qui ressemble a un
   bouton ; une case muette est une reponse qu'il essaiera.
3. **L'exigence qui vise ce jeu nommement, et c'est sur lui qu'elle compte le plus : aucune colonne ne
   doit faire defiler un mot autre que le sien.** Faire passer `son` et `sont` en boucle dans chaque
   colonne, dix fois par partie, c'est faire lire a l'enfant la forme fausse plus souvent que la juste.
   La memoire orthographique est visuelle. Sur un jeu d'homophones, c'est un contresens complet, et
   aucun automate ne le verra : **je reste seule garde de ce point**, et c'est sur ce jeu que je le
   verifierai en priorite sur le rendu livre.

---

### Constat neuf, mesure, du 2026-08-10 - l'indice pousse les deux reponses sous le pli sur trois items

**Ce bloc ne retire rien.** Le Done pedagogique du 2026-08-10 **reste accorde**, la ligne de matrice
**reste `couvert-valide`**, et aucun de mes verdicts anterieurs sur ce jeu n'est modifie. C'est un
constat neuf et date, a instruire par `camille-po`, et je dis en fin de bloc pourquoi ce n'est pas un
motif.

#### Le symptome, en deux phrases

**Sur trois des vingt-quatre items de ce paquet, quand l'indice s'affiche, les deux boutons de
reponse descendent sous la ligne de flottaison d'un telephone de 360 x 640, et sur l'item 24 il n'en
reste rien du tout a l'ecran.** La cause n'est ni le nombre de propositions ni la mise en page en
colonnes : c'est l'enonce a 2 rem qui se replie sur cinq ou six lignes, et le defaut existe donc a
l'identique dans la disposition empilee servie aujourd'hui.

#### La mesure

Ecrans de manche complets - bandeau, "Les jeux", score, consigne, enonce, indice, propositions -
construits sur les deux feuilles reellement servies et la structure reelle de
`fragments/manche.html`. Un ecran par item, les vingt-quatre items du paquet avec leurs enonces,
leurs propositions et leurs indices reels. Captures a une largeur de viewport reellement de 360 px
par `outils/mesurer-rendu.sh 360 <url> <png>`, navigateur designe par la variable `CHROME`. Pli lu a
640 px. Position des boites relevee au pixel par balayage des aplats de `--recreo-accent`, position du
mot par balayage des glyphes clairs a l'interieur de la boite.

**Resultat : 3 items sur 24 avec l'indice affiche, 0 sur 24 sans l'indice.**

| Item | Enonce | Boite des reponses | Bas du mot | Ce que l'enfant voit |
|---|---|---|---|---|
| 22 | "Même si le ciel est bien dégagé ce matin, il ___ encore pleuvoir avant le soir." | 591 - 664 | 647 | boutons visibles, **le mot est coupe** |
| 23 | "Tom cherchait sa gomme depuis le début du cours, et son voisin ___ enfin repérée sous la table." | 639 - 712 | 688 | **2 px de bouton sur 74**, aucun mot |
| 24 | "Après cette longue randonnée en montagne, le vieux chien ne ___ plus vraiment courir comme avant." | 717 - 790 | 773 | **rien du tout**, 0 px sur 74 |
| 13 et 16 | les deux enonces longs suivants | 591 - 664 | **640 exactement** | au bord exact du pli |

Les 21 autres items tiennent, le plus bas a 599. Il n'y a donc pas de defaut general sur ce paquet :
il y a une queue de cinq items longs, dont trois franchissent et deux touchent le bord.

**Et voici ce qui rend ce constat serieux plutot que cosmetique.** J'ai remesure les cinq items
limites **sans l'indice**, c'est-a-dire dans l'etat ou ils s'affichent avant trois echecs
consecutifs :

| Item | Bas du mot sans indice | Bas du mot avec indice |
|---|---|---|
| 13 | 510 | 640 |
| 16 | 510 | 640 |
| 22 | 517 | **647** |
| 23 | 558 | **688** |
| 24 | 613 | **773** |

**Aucun des cinq ne franchit le pli sans l'indice. Trois le franchissent avec.** Le defaut ne se
declenche donc qu'au moment ou l'enfant vient d'echouer trois fois de suite et recoit son aide :
l'ecran lui donne son indice **et lui retire ses boutons de reponse dans le meme geste**. Il lit
l'aide, puis il ne trouve plus ou repondre.

C'est exactement la structure aggravante de mon refus des cinq indices fautifs de "Le bon verbe" du
meme jour : le defaut est servi a l'enfant le moins en etat de le surmonter. Sur ce jeu-ci, la
consequence est plus benigne - l'enfant peut faire defiler la page, il n'apprend rien de faux - mais
la structure est la meme et je la nomme.

#### Je corrige un de mes propres constats avant de m'appuyer sur celui-ci

Le 2026-08-10, dans `FICHE-bandit-manchot-conjugaison-present-cm1.md`, section 4 de mon refus, j'ai
ecrit :

> *"Sur le meme viewport, "Le bon son" (trois propositions) tient entierement au-dessus du pli,
> verifie par la meme mesure - le defaut est bien lie au passage a quatre propositions et non a
> l'habillage en general."*

**L'inference etait fausse, et elle est de moi.** J'avais verifie "Le bon son", dont les enonces sont
des mots seuls en capitales et tiennent tous sur une ligne, et j'en ai conclu une cause. La cause
n'est pas le nombre de propositions : c'est la longueur de l'enonce. Un paquet a **deux**
propositions perd ses deux boutons sous le pli la ou "Le bon verbe" en perdait un sur quatre. La
mesure ci-dessus est le demenagement de cette erreur, et elle a une consequence directe sur ma
propre decision du meme jour, que je tire dans
`FICHE-bandit-manchot-conjugaison-present-cm1.md`, second arbitrage du 2026-08-10.

#### Pourquoi ce n'est pas un motif de refus

Trois raisons, et la premiere suffit.

1. **Aucune decision du depot ne fixe de hauteur de viewport minimale.** `DEC-0007` point 5 ne parle
   que de largeur. Je l'ai ecrit deux fois le 2026-08-10, une fois pour refuser de faire de ce defaut
   un motif contre "Le bon verbe" que je refusais par ailleurs. **Un defaut que je n'ai pas voulu
   opposer au paquet que j'ai refuse, je ne vais pas l'opposer apres coup au paquet que j'ai valide.**
   Ce serait deplacer mes poteaux, et dans le sens le plus indefendable.
2. **Le Done pedagogique de ce jeu a ete prononce en jouant**, et rien de ce que j'ai joue n'est
   demenati : les enonces sont exacts, les indices sont justes, la competence est celle annoncee.
3. **L'enfant n'apprend rien de faux et n'est pas bloque** : la page defile. C'est une gene reelle,
   pas une erreur d'enseignement.

#### Le destinataire, nomme

**`camille-po`.** C'est le meme besoin que celui que j'ai decrit ce matin au point 6.5 de mon verdict
courant dans `FICHE-bandit-manchot-additions-cp.md` - "une regle sur ce qui doit rester au-dessus du
pli, et sur quel viewport de reference" - avec une difference qui change sa priorite et pas sa nature :
**je le croyais limite a un paquet retire du catalogue, il touche un jeu qu'un enfant peut ouvrir
aujourd'hui.** Je decris le besoin, je n'ecris pas la story et je ne fixe pas sa priorite.

Deux pistes existent et je n'en choisis aucune, parce qu'aucune n'est de mon ressort : reduire la
longueur des enonces de la queue de cinq items, ce qui serait du contenu et reviendrait donc devant
moi ; ou traiter la hauteur de l'ecran, ce qui serait de l'habillage et passerait par `theo-archi` et
`ines-design`. La seconde traite la cause, la premiere traite un symptome sur un paquet.

**Rien de ceci n'est un obstacle au chantier machine a sous.** Le passage en colonnes **ameliore**
cette situation de 180 px sur les paquets a trois et quatre propositions ; sur celui-ci, a deux
propositions, il ne change rien ni en bien ni en mal, une rangee de deux occupant la meme hauteur
qu'avant. Ce constat n'est pas une raison de ralentir S7-1.

#### Ce dont je reste seule garde sur ce point

- **Que ce qui compte reste au-dessus du pli quand l'enonce est long.** Aucun automate de ce depot ne
  voit une position rendue, et `ADR-0025` a deja etabli pourquoi.
- **Audit degrade a declarer** : j'ai mesure a 360 x 640, sur la pile de polices systeme de ma
  machine, avec un pli lu a 640 px qui n'est fixe par aucune decision. Un appareil plus court, une
  police plus large, une barre d'adresse plus haute deplacent tous les nombres ci-dessus. Ce que je
  tiens pour etabli : la queue de cinq items longs existe, et l'indice est ce qui la fait basculer.

---

### Verdict du 2026-08-23 (s18) sur `S17-4` - `BPM-1` **TENUE**, Done pedagogique complet **REFUSE**

> **Ce que cette section fait.** Elle produit `BPM-1`, la mesure de rendu que `DEC-0041` m'a
> reclamee apres que `DEC-0039` en avait transfere la production a l'utilisatrice, et elle rend le
> Done pedagogique complet que `DEC-0024` exige **dans le meme geste** que `BPM-1`. Elle n'efface
> et ne reecrit aucun verdict anterieur.
>
> - **`BPM-1` : TENUE**, sur les 24 items, aux trois etats d'echec, aux deux arrangements, a la
>   reference en vigueur. Je l'accorde sans reserve et je ne la rouvre plus.
> - **Done pedagogique complet : REFUSE**, sur un motif **neuf, date de ce jour, qu'aucune de mes
>   quatre conditions `BPM` ne couvrait** : sur **8 items de 24**, l'indice est coupe par le pli et
>   l'enfant perd exactement les mots qui portent sa conclusion.
> - **Consequence** : la ligne francais CM1 "homophones" de `squad/programme/MATRICE-couverture.md`
>   **ne passe pas `couvert-valide`** et **reste `en cours`**. L'entree de catalogue posee par
>   `lea-dev` n'est pas remise en cause par ce verdict : c'est elle qui a rendu la mesure possible.

#### 0. L'arbre mesure, releve par moi avant tout travail

Worktree dedie `s18-nadia-bpm1`, forke de `round-two` a **`8046256`** (SHA releve par moi, pas
recopie de la demande), puis `merge s18-lea` - **fusion en avance rapide**, la branche du dev etant
un descendant direct de cette tete. L'arbre mesure est donc **`1ba179d`** exactement, avec
`8046256` pour ancetre verifie. Diff par rapport a `round-two` : deux fichiers, l'entree de
catalogue et son garde-fou de texte accentue.

`bash outils/mvn-jdk21.sh -B clean verify` sur la **racine** du reacteur, donc perimetre complet et
sans la question du `-am` : **BUILD SUCCESS, 612 tests, 0 echec** (48 + 13 + 312 + 144 + 89 + 6,
somme faite sur les six blocs `Results` du journal, pas recopiee). **Le Done technique est acquis :
le jeu est evaluable**, ce qui n'etait pas vrai depuis `DEC-0024`.

#### 1. Methode

Reference mesuree : **viewport 744 x 551 px** (`DEC-0040`), `deviceScaleFactor` a 1, mode non
mobile, navigateur reel pilote par son protocole de deverminage, application servie depuis le fat
jar construit sur cet arbre. **Jeu au clavier seul**, tabulation et Entree, jamais un appel HTTP
fabrique. C'est la methode de `SYN-5` et de `SYN-11`, et non `outils/mesurer-rendu.sh` : cet outil
rend la capture d'une URL, un etat d'echec s'obtient en postant, et `theo-archi` a etabli qu'il ne
peut structurellement pas atteindre les trois quarts de ce que ce critere demande. `camille-po` a
reecrit le critere en consequence ; c'est la forme reecrite que je mesure.

**Les trois etats d'echec sont reellement atteints, aucun n'est simule.** La revelation s'obtient
en pressant **trois fois la meme mauvaise proposition** (`ESSAIS_AVANT_REVELATION = 3`, une
proposition deja essayee restant cliquable) : je l'ai verifie sur le code du domaine avant de
compter dessus, et chaque revelation de la campagne porte son texte `C'est ... Appuie sur ...` lu
a l'ecran, jamais deduit.

**Campagne** : **23 parties**, **624 lectures** dont **580 retenues au tableau** (les autres
appartiennent a des manches interrompues par un artefact de mon propre outillage, section 5.2),
**48 paires (item, arrangement) sur 48**, chaque paire vue dans **les quatre etats** - repos,
premier echec, deuxieme echec, revelation. Chaque item vu **12 a 40 fois**. **`scrollY = 0` verifie
avant chaque lecture : 0 lecture a `scrollY` non nul sur 624.**

**`SYN-10` appliquee, et c'est elle qui me permet d'ecrire "stable" plutot que "je n'ai pas
regarde".** Les 24 items ont exactement deux propositions - verifie sur les 580 lectures, jamais
suppose -, donc **deux arrangements par item**, et les deux ont ete joues sur les 24. Le fait
declaratif qui bornait la campagne vers le bas est exact : le total de caracteres des deux
etiquettes ne depasse jamais **sept** (items 5, 6, 19, 20, 21, 22 et 24 l'atteignent). Une rangee
de propositions coute **74 px** dans un conteneur de **500 px** - la `container` Bootstrap plafonne
la zone de contenu, la largeur utile n'est pas 744 px, exactement comme sur `cm1-synonymes` -, et
**aucun item de ce paquet ne passe jamais a deux rangees**.

#### 2. `BPM-1` contre 744 x 551 - TENUE sur les 24, aux trois etats, aux deux arrangements

Le bas de la derniere proposition ne prend que **deux valeurs sur les 580 lectures**, et il ne
depend ni de l'etat ni de l'arrangement, seulement du nombre de lignes de l'enonce :

| Pire bas | Lignes d'enonce | Marge au pli de 551 | Items |
|---|---|---|---|
| 363,19 px | 1 (48 px) | **+187,81 px** | 1 a 10, 14, 17, 19, 20, 21, 24 (16 items) |
| 411,19 px | 2 (96 px) | **+139,81 px** | 11, 12, 13, 15, 16, 18, 22, 23 (8 items) |

**Vingt-quatre items sur vingt-quatre placent leurs deux propositions entierement au-dessus du pli,
dans les trois etats d'echec comme au repos, dans les deux arrangements. `BPM-1` est tenue.** La
marge la plus faible est de **139,81 px**, soit presque deux rangees de boutons : ce n'est pas une
tenue de justesse, et je n'y attache aucune condition. **`BPM-1` est close.** Le motif du refus
ci-dessous ne la rouvre pas et ne la redurcit pas ; si le defaut de la section 3 est repare,
personne n'a a remesurer `BPM-1`.

**`SYN-10` est satisfaite** : zero variance. Aucun des 24 items ne varie selon le tirage ni selon
l'etat, sur 12 a 40 lectures chacun. C'est prevu et je dis pourquoi, pour qu'on ne me credite pas
d'une precaution inutile : a deux etiquettes de sept caracteres au plus dans 500 px, l'ordre des
deux boutons ne peut pas changer la hauteur de la rangee. **`SYN-10` reste neanmoins la raison pour
laquelle j'ai fait 48 paires et non 24** - c'est elle qui a fait tomber mon chiffre de 2,81 px en
s17, et je ne l'abandonne pas parce qu'elle ne rapporte rien une fois sur deux.

**Et je credite ce qui doit l'etre : `M4` est tenue sur ce jeu.** Le message d'echec - le bandeau et
son texte - est **entierement visible sans defiler sur les 24 items**, pire bas **474,19 px**, marge
**+76,81 px**. Ce jeu etait nomme comme le pire cas de `M4` dans le catalogue ; a la reference en
vigueur, il ne l'est plus. Le defaut de pli que j'avais mesure a 360 x 640 le 2026-08-10 (cinq items
sur 24, apres correction de mon propre comptage de trois) **ne se reproduit pas ici** : a 744 x 551
la cause a disparu, et le dire est la seule chose honnete a faire.

#### 3. Le motif du refus - l'indice est decapite sur 8 items de 24, et sur quatre il devient indecidable

**Le fait, mesure ligne par ligne.** L'indice s'affiche des le premier echec et jusqu'a la
resolution de la manche. Sur les **8 items dont l'enonce fait deux lignes**, le bloc d'indice va de
**503,19 px a 583,19 px** : **47,81 px de ses 80 px sont au-dessus du pli, les 32,19 px du bas sont
dessous.** L'indice n'est donc pas invisible - il est **coupe en plein milieu de sa derniere
phrase**. J'ai releve, caractere par caractere par mesure de boite de ligne, ce que l'enfant lit et
ce qu'il perd a `scrollY = 0` :

| Item | Reponse | Ce que l'enfant lit | Ce qu'il perd |
|---|---|---|---|
| 11 | `là` | *Remplace par 'à cet endroit' : 'Pose ton cartable à cet* | *endroit' fonctionne.* |
| 12 | `la` | *Le mot qui suit le trou est un nom ('règle') : il faut un* | *déterminant.* |
| 13 | `l'a` | *Remplace par 'l'avait' : 'elle l'avait enfin retrouvé'* | *fonctionne.* |
| 15 | `ce` | *Le mot qui suit le trou est un nom ('cahier') : il faut un* | *déterminant.* |
| 16 | `se` | *Le mot qui suit le trou est un verbe conjugué* | *('rangent') : il faut un pronom.* |
| 18 | `ses` | *Le cahier et les crayons appartiennent à Léa : il faut un* | *mot de possession, pas un mot qui montre du doigt.* |
| 22 | `peut` | *Remplace par 'pouvait' : 'il pouvait encore pleuvoir'* | *fonctionne.* |
| 23 | `l'a` | *Remplace par 'l'avait' : 'son voisin l'avait enfin repérée'* | *fonctionne.* |

**Pourquoi ce n'est pas une gene de lecture mais une perte de sens, et sur quatre items une
inversion possible.** Ce paquet emploie **deux formes d'indice de substitution, et les deux sont
correctes** : la forme positive *"... fonctionne."* sur 14 items, et la forme negative *"... ne
fonctionne pas."* sur 4 items (2, 3, 10, 21) - c'est **moi** qui ai demande la forme negative en
s17 et qui l'ai comptee comme la reparation. Or **les quatre items tronques qui portent une
substitution (11, 13, 22, 23) sont tous de forme positive, et le mot qu'ils perdent est exactement
le verbe qui distingue les deux formes.** L'enfant lit *"Remplace par 'l'avait' : 'elle l'avait
enfin retrouvé'"* et la phrase s'arrete la. Il ne sait pas si la substitution marche ou ne marche
pas. **S'il tranche dans le mauvais sens - et une chance sur deux le porte a le faire - l'indice le
conduit a la mauvaise reponse.** Un indice qui peut etre lu comme le contraire de ce qu'il dit est
pire qu'un indice absent, et ce jeu n'a que deux propositions.

Sur les quatre autres (12, 15, 16, 18), la perte n'inverse rien mais elle emporte **l'operatoire
entier** : *"il faut un"* sans *"déterminant"*, *"est un verbe conjugué"* sans *"il faut un
pronom"*. Ce qui reste a l'ecran ne nomme plus rien a faire.

**Ce que cela coute a la competence, et c'est le coeur de mon refus.** Le referentiel demande de
*"distinguer et utiliser correctement les principaux homophones grammaticaux"*
(`squad/programme/francais/competences-cm1.md`, "Orthographe et grammaire") - et j'ai verifie que
c'est bien **cette** competence que l'increment travaille, pas une voisine plus facile. **Distinguer
`la` de `l'a`, c'est faire la substitution ; l'indice est le seul endroit du jeu ou cette
manipulation est enseignee.** Le message d'echec dit *"Ce n'est pas ça. Essaie encore !"* et
n'apprend rien ; la revelation dit *"C'est l'a. Appuie sur l'a."* et donne la reponse sans la
raison. **Sur ces 8 items, l'enfant qui se trompe termine la manche en connaissant la reponse et
jamais le pourquoi** : il retient un mot, pas une regle. Et ce ne sont pas 8 items quelconques - ce
sont `la/là/l'a`, `ce/se`, `ces/ses`, `peu/peut`, c'est-a-dire **quatre des cinq familles que
j'avais exigees en s05 pour lever ma condition de `couvert-valide`**. La revelation de l'item 13 est
capturee : l'enfant y voit la reponse et la phrase d'indice toujours coupee au meme endroit.

**Releves** (viewport 744 x 551, `scrollY = 0`, captures de l'application servie) :
`squad/conformite/releves-s18/s18-bpm1-744x551-item-11-echec-indice-tronque.png`, `...-item-12-...`,
`...-item-13-...`, `...-item-23-...`,
`s18-bpm1-744x551-item-13-revelation-indice-tronque.png`, et pour contraste
`s18-bpm1-744x551-item-24-echec-indice-entier.png`.

**Pourquoi un refus et non une reserve.** J'ai pese les deux, et je dis les deux poids.
`camille-po` a leve le transfert de `BPM-1` pour faire bouger une ligne de couverture apres cinq
sprints d'immobilite, l'entree de catalogue est posee, `BPM-1` est tenue, le contenu est acquis, et
un `VALIDE SOUS RESERVE` sans reserve bloquante suffirait a faire passer la ligne. **Mais
`couvert-valide` decrirait un enfant capable d'ouvrir l'application aujourd'hui et de travailler
cette competence** : sur un tiers du paquet il ne peut pas, et sur quatre items il peut etre conduit
a la mauvaise reponse par l'aide elle-meme. **Je ne signe pas cela, et le fait que ce refus coute
la seule progression de couverture du sprint n'entre pas dans mon verdict** - il n'a pas a y entrer,
et si je le laissais entrer ma porte ne vaudrait plus rien. Un refus renvoie l'increment a
`camille-po`, qui en fait du backlog ; il ne bloque pas le sprint suivant.

**Ce que ce refus n'est pas.** Ce n'est **pas** un durcissement de `BPM-1`, qui portait sur les
propositions de reponse et qui est accordee. Ce n'est **pas** un durcissement de `M4`, qui portait
sur le message d'erreur et qui est tenue. Ce n'est **pas** une reouverture du contenu : les 24
phrases, les 24 reponses et les 24 indices sont justes dans le fichier, `BPM-2`, `BPM-3` et `BPM-4`
restent tenues et je ne les rejuge pas. **Le defaut est dans ce que le pli laisse lire d'un indice
correct**, et sa cause est de mise en page, pas de contenu. **Aucune de mes conditions ecrites ne
couvrait la position de l'indice** - je l'avais mesuree a 360 x 640 le 2026-08-10, je l'avais
declaree "pas un motif" faute de decision sur la hauteur de viewport, et depuis `DEC-0040` cette
decision existe. C'est donc une **condition neuve et datee** (section 6), pas un poteau deplace.

#### 4. Ce que je verifie de ma propre main avant de m'appuyer dessus

**Mon comptage du 2026-08-21 tient.** Recompte aujourd'hui sur le fichier servi de cet arbre, jeton
par jeton, insensible a la casse : **0 indice sur 24 ne nomme la bonne reponse.** Les trois items
que j'avais nommes (3 `et`, 10 `ou`, 21 `peu`) portent bien la forme negative. Je n'avance pas ce
chiffre sur la foi de ma propre note : je l'ai refait.

**Et je verifie la consequence technique de ce comptage, que je n'avais jamais verifiee.** `M21`
est implementee par une cle facultative `indicesNommentLaReponse` du paquet
(`QuestionContentSourceFactory`), et `BanditManchotSession.indice()` masque **tous** les indices du
paquet quand elle vaut vrai. Cette cle est **absente** de `cm1-homophones.yaml`, donc fausse, donc
les indices s'affichent - ce qui est desormais le bon reglage puisqu'aucun ne nomme plus la
reponse. **Personne n'avait a la changer et personne ne l'a changee** : le reglage est juste parce
que son defaut l'est, pas par decision. Je le note parce qu'un futur item mal ecrit ne declenchera
aucun garde-fou.

#### 5. Observations datees du 2026-08-23, **non bloquantes**, que je ne compte pas contre ce lot

**5.1. A la revelation, la reponse revelee sort de l'ordre de tabulation - mesure, pas deduit.**
`recreo.js` pose `tabindex="-1"` sur son point de focus avant de l'appeler ; a la revelation ce
point de focus **est le bouton de la reponse revelee**. Le focus est bien dessus, donc Entree
avance. Mais l'enfant qui quitte ce bouton par tabulation **ne peut plus jamais y revenir** : j'ai
releve l'ordre de tabulation reel apres l'avoir quitte, et il ne contient que `CP`, `CM1`,
`Les jeux` et **la mauvaise proposition**. J'ai presse cette mauvaise proposition : elle poste, et
**l'ecran est identique** - meme verdict, meme enonce, meme marque, score inchange. Le seul chemin
restant est de represser Entree apres ce non-evenement, parce que le focus revient alors sur la
reponse revelee (`M13` clause 5, `M14`). **Ce n'est pas une impasse fermee, c'est un chemin qu'un
enfant de neuf ans ne trouve pas.**
Je l'ecris comme observation et **pas** comme motif, pour trois raisons que j'assume : elle porte
sur la mecanique partagee par les treize jeux et non sur ce paquet ; elle touche le clavier, dont
le lot de `marc-dev` (`S17-C`) est en cours de livraison et **n'est pas sur l'arbre que je mesure** ;
et le mecanisme B qu'a retenu `theo-archi` - `tabindex` declare dans les gabarits sur les seules
cibles non focalisables, script reduit a `focus()` sans plus muter aucun attribut - **retirerait la
cause**, un bouton n'ayant pas besoin qu'on le rende focalisable. **Je ne valide pas sur cette
promesse et je n'arbitre pas ce lot ici** : je nomme le symptome, date, pour que `camille-po` en
fasse ce qu'elle veut et pour que la remesure sache quoi regarder.

**5.2. Un artefact de mon propre outillage, que je declare pour qu'il ne soit pas pris pour un
defaut.** Sur **15 des 23 parties**, un appui envoye a vitesse machine a declenche une soumission
**native** du formulaire au lieu de l'echange htmx : navigation complete vers
`/?propositionChoisie=<valeur>`, document recharge, compteur de ma sonde perdu. J'ai cherche a
savoir si un enfant peut l'atteindre plutot que de le supposer : **36 appuis a delai humain - 12 a
0,3 s, 12 a 1,0 s, 12 a 2,0 s apres l'echange precedent - donnent 0 soumission native sur 36.** Je
n'ai donc **aucune raison d'en faire un defaut** et je n'en fais pas une condition. Je l'ecris quand
meme, avec son chiffre, parce que quelqu'un le rencontrera et devra savoir qu'il a ete vu et cerne.
Consequence si elle etait un jour atteignable, pour epargner l'enquete : l'appui n'avance pas la
manche, la page se recharge et la partie reprend a l'identique (le message et l'indice sont
reconstruits depuis la session, rien n'est perdu). **Aucune lecture du tableau de la section 2 ne
vient d'une partie interrompue** : les 580 lectures retenues sont toutes des manches completes.

**5.3. Une fragilite nommee, qui ne tombe pas aujourd'hui.** Sur les **16 items a enonce d'une
ligne**, l'indice tient au-dessus du pli avec **15,81 px** de marge (12 items) ou **45,81 px**
(items 4, 6, 8, 20). Une ligne d'indice de plus, une police un peu plus large, un en-tete un peu
plus haut, et ces 12 items rejoignent les 8 de la section 3. Je le dis maintenant pour que la
reparation ne vise pas 8 items mais la cause.

**5.4. L'observation du 2026-08-19 sur l'item 3 n'est pas rouverte.** *"Papa ___ maman font la
cuisine."* presuppose toujours un foyer avec un papa et une maman. Cet item n'a pas ete raccourci,
je l'ai valide deux fois, et je ne le mets pas au passif de ce lot-ci non plus. Elle reste une
observation datee du 2026-08-19, disponible pour `camille-po`.

#### 6. Conditions neuves, datees du 2026-08-23

- **`BPM-5` (bloquante), 2026-08-23.** A la reference en vigueur (744 x 551 px, `DEC-0040`), **le
  texte de l'indice est lisible en entier sans defiler, sur la totalite des 24 items et dans les
  trois etats d'echec** - pas son debut, pas sa premiere ligne : la totalite de sa derniere phrase,
  ponctuation finale comprise. Mesure par la meme methode que `BPM-1` (navigateur pilote,
  `scrollY = 0` verifie a chaque lecture, pire tirage de chaque item par `SYN-10`), jamais par un
  comptage de caracteres (`DEC-0016`) ni sur un echantillon. **Cette condition est neuve** : elle
  ne redurcit ni `BPM-1`, qui portait sur les propositions et qui est accordee, ni `M4`, qui portait
  sur le message d'erreur et qui est tenue. **Elle vise la cause et non les 8 items** : reparer 8
  enonces sans toucher a la mise en page laisserait 12 items a 15,81 px du pli (section 5.3).
- **`BPM-6` (non bloquante), 2026-08-23.** Apres la revelation, la proposition qui porte la reponse
  revelee **reste atteignable au clavier**, et aucune proposition atteignable ne rend un ecran
  strictement identique a celui qui la precede. Non bloquante ici parce qu'elle porte sur la
  mecanique partagee et non sur ce paquet, et parce que le lot `S17-C` de `marc-dev`, absent de
  l'arbre que je mesure, en retire peut-etre la cause. **A verifier sur l'increment livre, pas sur
  l'intention.**

**Ce que je ne demande pas.** Aucune suppression d'item : `BPM-3` interdit de payer un probleme de
pli en retirant du contenu, et la reparation de `BPM-5` est de mise en page. Aucune reduction du
nombre de propositions : ma reserve 5 de s04 - deux propositions donnent une chance sur deux au
hasard - reste ouverte et ne doit pas etre aggravee. Aucun raccourcissement des indices dont je
viens de valider la forme : c'est le pli qui doit ceder, pas l'aide.

#### 7. Ce que ce verdict ne fait pas

- **Il ne juge pas `SYN-11`** ni la proposition de forme d'`ines-design` : c'est l'arbitrage suivant
  dans l'ordre gele par `sofia-sm`, et je le rends apres celui-ci. Je note seulement, sans le
  trancher, que le defaut de la section 3 vit dans **la meme region de mise en page** que celui pour
  lequel j'ai refuse `S17-3` le 2026-08-22, sur la **meme mecanique partagee** - le bloc de retour
  sous le meuble. **Cela ne vaut ni promesse ni validation anticipee** : si un correctif de cette
  region est livre, `BPM-5` se remesure sur le livre.
- **Il ne juge pas la correction clavier de `marc-dev`**, qui n'est pas sur l'arbre mesure.
- **Il ne rejuge pas le contenu.** `BPM-2`, `BPM-3` et `BPM-4` restent tenues, les 24 phrases
  raccourcies sans qu'aucune soit supprimee, et ma reserve sur les indices reste **fermee**.
- **Il ne remet pas en cause l'entree de catalogue de `lea-dev`** : sans elle je n'avais rien a
  mesurer, et la question de la laisser ou non en attendant `BPM-5` appartient a `camille-po`, pas
  a moi. Ce que je dis de mon cote est simple : **en l'etat, un enfant de CM1 qui ouvre ce jeu et
  se trompe recoit, sur un tiers des items, une aide coupee.**
- **Il ne fait rien bouger dans `squad/programme/MATRICE-couverture.md`.** La ligne reste
  `en cours`.

#### 8. Ce dont je reste seule garde, et il faut le lire comme une limite de ce verdict

- **La position de tout ce que l'enfant lit sous le meuble.** Aucun garde-fou de la CI ne voit une
  position rendue - la CI est verte a 612 tests sur l'arbre exact ou je mesure ce defaut. `BPM-1`,
  `BPM-5`, `M4` et la famille des plis sont **des mesures de moi, un jour donne, a une reference
  donnee**. Tant qu'aucun garde-fou n'existe, c'est moi le garde-fou, et je le suis en jouant.
- **Le comptage des indices qui nomment leur reponse.** Toujours a la main, toujours de moi. Le
  vingt-cinquieme item ecrit plus tard ne sera verifie par personne.
- **Ce que je ne garantis pas** : ce qu'un lecteur d'ecran annonce (transfere hors de mes mains en
  s17, jamais couvert par aucun de mes Done), ce qu'un enfant reel comprend, et tout rendu sur une
  largeur, une hauteur ou une pile de polices que je n'ai pas mesurees. **Audit degrade a
  declarer** : j'ai mesure sur la pile de polices systeme de cette machine ; une police plus large
  deplace les nombres de la section 3, et **dans le sens qui aggrave** - les 12 items a 15,81 px de
  marge basculeraient avant les autres.
