# ADR-0022 - Rendre la visibilite d'un habillage permanent dependante d'un marqueur d'etat pose par le serveur, et retirer d'un appariement les boutons qui ne se montrent plus ensemble

- **Statut** : Accepte, son contexte amende le 2026-08-14 (un chiffre perime et la comparaison qu'il
  portait ; la decision et ses garde-fous sont inchanges), amende le 2026-08-17 (s14 : ce que `S14-1`
  fait tomber, clause par clause, et la clause qui tombe n'est pas dans cet `ADR`), **amende une
  seconde fois le 2026-08-17 (s14, apres `S14-2` / `DEC-0033`) : le point 2 perd toute instance en
  production, le point 1 garde la sienne et perd le garde-fou qui l'exigeait**, **puis une troisieme
  fois le meme jour (s14, apres le refus pedagogique de `nadia-prof`) : le point 2 retrouve des sujets
  et sa forme est generalisee, parce qu'une clause qui oscille avec un arbitrage etait mal formee**
- **Date** : 2026-08-07
- **Auteur** : theo-archi
- **Sprint** : s06
- **Amende** : ADR-0017 (l'extension du 2026-08-07 ; l'amendement est ecrit dans le corps d'ADR-0017)
- **Complete** : ADR-0008 (le comportement de jeu vit sur le serveur), ADR-0014 point 7 (le
  JavaScript de la bibliotheque tierce n'entre pas), ADR-0015 (deriver le perimetre de la source qui
  gouverne le rendu), ADR-0020 (l'unite de verification d'un ecran est le document assemble)
- **Story** : S6-9, apres l'arbitrage prealable de `nadia-prof` du 2026-08-07
  (`squad/conformite/FICHE-bandit-manchot-additions-cp.md`, section "Arbitrage prealable au
  developpement - S6-9")

## Contexte

`nadia-prof` a tranche l'arbitrage que je lui avais route au planning. Elle confirme le principe de
S6-9 - une seule sortie par ecran de jeu, et le mot est "Les jeux" - et **refuse la forme** : la
sortie qui survit sur un ecran de jeu est le bouton de la zone de contenu, pas le lien de l'en-tete,
et `sortie-pleine` est conserve tel quel sur l'ecran de fin de partie. Elle a joue avant de trancher
et chiffre son motif : le lien de l'en-tete mesure 102 x 53 px en contour dans un coin, contre un
aplat de 117 x 56 px au milieu du texte que l'enfant vient de lire ; garder le premier comme seule
porte ferait de l'ecran de fin de partie un cul-de-sac.

> **Amendement du 2026-08-14 (s11) : le chiffre "102 x 53 px" est perime, et la comparaison qu'il
> portait ne compare pas ce que je lui faisais dire. La decision ci-dessous ne bouge pas, et je dis
> pourquoi plutot que de le supposer.**
>
> **1. Le chiffre. `109 x 53 px`, pas `102 x 53 px`.** `nadia-prof` l'avait mesure le 2026-08-07 sur le
> libelle "Accueil" ; le **point 3 de la decision ci-dessous** remplace ce libelle par "Les jeux", plus
> large. Le chiffre etait donc perime par cet `ADR` lui-meme, avant meme son acceptation, et il a
> survecu un sprint et demi. Elle l'a remesure et corrige de sa main
> (`squad/conformite/FICHE-bandit-manchot-additions-cp.md`, section "0 bis", point 1), `camille-po` en a
> tire l'amendement de `DEC-0027` ; ce document-ci est le troisieme et dernier porteur du chiffre faux.
>
> **Ce que j'ai verifie moi-meme, et par quoi** - lecture des composantes de boite de `recreo.css`, pas
> une capture :
>
> - **La hauteur est derivable, et elle vaut bien 53 px.** `.lien-accueil` declare `font-size: 1.25rem`
>   (20 px), `padding: 0.6rem 1.1rem` (9,6 et 17,6 px), `border: 0.125rem` (2 px), et herite le
>   `line-height: 1.5` de `.btn` : 30 + 2 x 9,6 + 2 x 2 = **53,2 px**. Le meme calcul sur
>   `.sortie-pleine` (`padding: 0.75rem 1.25rem`, bordure de `.btn` a 1 px) donne **56 px**, exactement
>   le second chiffre cite ci-dessus, qu'elle a remesure inchange.
> - **La largeur absolue n'est pas derivable de ce depot, et je ne fais donc pas semblant de la
>   verifier.** Aucun fichier maison ne declare de `font-family` ; la famille vient de
>   `--bs-font-sans-serif` de la bibliotheque vendorisee, c'est-a-dire de la police systeme du poste. Les
>   109 px sont sa mesure sur son rendu, et je la reprends comme telle. Ce qui se verifie de mon cote est
>   la **coherence** des deux chiffres entre eux : leur ecart declare vaut 2 x (20 - 17,6) de rembourrage
>   moins 2 x (2 - 1) de bordure, soit **+2,8 px** pour l'aplat, et l'ecart mesure est de 8 px ; les
>   5,2 px restants sont l'avance du meme mot en gras a 20 px, ce qui est l'ordre de grandeur attendu.
>   Rien ne detonne, et rien de plus n'est demontre par la.
> - **Et surtout : les deux boites sont egales par construction, quelle que soit la police.**
>   `.lien-accueil` et `.retour-accueil` declarent les **memes** composantes - `display`, `min-height`,
>   `font-size`, largeur de bordure, `border-radius`, `padding` - ne se distinguent que par la couleur du
>   texte et de la bordure, et portent le **meme libelle** depuis le point 3 ci-dessous. `recreo.css` le
>   dit en clair depuis S6-1 : le lien *"reprend deliberement les memes composantes de boite que
>   `.retour-accueil`"*. Cette egalite-la ne depend d'aucune mesure, et c'est elle qui compte pour la
>   suite.
>
> **2. Le raisonnement qui tombe avec le chiffre.** La phrase ci-dessus oppose `.lien-accueil` a un
> "aplat de 117 x 56 px", qui est `.sortie-pleine`. **Ces deux elements ne s'affichent jamais sur le meme
> ecran** : le premier ne se montre que sur l'accueil (c'est l'objet de cette decision), le second n'est
> rendu que par l'ecran de fin de partie. Une comparaison de tailles entre deux ecrans ne dit rien de ce
> qu'un enfant voit - c'est la doctrine meme d'`ADR-0017` ("deux boutons d'un meme ecran") et
> d'`ADR-0031`. Sur un ecran de manche, les deux candidats a la sortie sont `.lien-accueil` et
> `.retour-accueil`, et le point 1 ci-dessus dit qu'ils ont **la meme boite**. **Il n'y avait donc aucun
> ecart de taille a opposer sur une manche.** `nadia-prof` en tire la meme conclusion contre son propre
> argument, et le perimetre de son motif - un ecran, pas cinq - est sa correction, dans sa fiche, pas la
> mienne a ecrire.
>
> **3. Ce que cet amendement ne change pas, verifie clause par clause et non affirme.** Aucun des cinq
> points de la decision, aucune consequence et aucun garde-fou ne raisonne sur une taille : le marqueur
> d'etat, la forme contractuelle du masquage, le libelle, la conservation de `sortie-pleine` et la
> derivation de l'appariement depuis les masquages sont tous des proprietes declaratives. Le garde-fou
> amende, `LActionNeDeclarePasMoinsQueLaSortieTest`, compare des **composantes declarees** (`font-size`,
> `min-height`, les deux axes de `padding`) et jamais une boite rendue - `ADR-0017` note lui-meme que la
> largeur, venant du libelle, n'est pas comparable. Aucun chiffre de ce depot n'est a recalculer, et la
> suite reste verte a 415 tests. Ce qui aurait bouge, si je l'avais ecrit : une justification du masquage
> par "le lien est plus petit, il ne peut pas etre la seule porte". Elle n'est pas dans cet `ADR`, et
> c'est le seul motif pour lequel la correction se limite au contexte.
>
> **4. Ma part dans la survie du chiffre, parce qu'elle est nommable.** Je l'ai recopie **sans sa
> methode** - ni le libelle sur lequel il avait ete pris, ni l'ecran, ni le fait qu'il datait d'avant le
> renommage que le meme `ADR` decidait. Un chiffre cite avec sa methode aurait rendu sa peremption
> visible le jour meme. C'est ma propre regle, appliquee ici a mes frais.

> **Amendement du 2026-08-17 (s14) - `S14-1` demande la visibilite permanente de deux boutons sur un
> ecran de jeu. `DEC-0032` et la fiche de conformite disent que cette story "contredit `ADR-0022`".
> J'ai verifie clause par clause au lieu de le reprendre ou de le nier, et la reponse est plus utile
> que les deux : aucune des cinq clauses de cet `ADR` ne tombe, et la clause qui tombe est ailleurs.**
>
> **Ce qui survit, clause par clause, et pourquoi.**
>
> - **Point 1, le marqueur `ecran-de-jeu` pose par le serveur sur la zone de contenu** : intact, et
>   `S14-1` en depend. C'est ce marqueur qui permet a un habillage permanent de dependre de l'etat sans
>   qu'aucun echange htmx traverse l'en-tete.
> - **Point 2, la forme contractuelle `body:has(.ecran-de-jeu) .lien-accueil { display: none }`** :
>   intacte, mot pour mot. La barre de niveaux **n'y est pas ajoutee** - c'est l'absence de regle qui
>   produit la visibilite permanente, et il n'y a donc rien a ecrire de ce cote. Le garde-fou
>   `chaqueEcranInsereDansLaZonePorteLeMarqueurQueLaFeuilleExige` continue d'exiger le marqueur sur
>   chaque fragment, pour cette regle-la. Mesure : les deux puces posees dans l'en-tete avec leur
>   feuille, `LEntreeDeMenuNeSurvitPasAUnEcranDeJeuTest` reste a **4 tests sur 4**.
> - **Point 3, le libelle "Les jeux" et l'unicite du mot de sortie** : intact, et c'est la clause qui
>   **interdit** de declarer une puce de niveau `data-role="sortie"`. Mesure du 2026-08-17 : deux puces
>   `sortie` libellees "CP"/"CM1" font echouer `leLienDeMenuEtLaSortieDeLaZoneDeContenuPortentLeMemeMot`
>   et lui seul. Loin d'etre contredite par `S14-1`, cette clause en gouverne le vocabulaire -
>   `ADR-0043` en tire le troisieme role.
> - **Point 4, `sortie-pleine` conserve tel quel** : intact, aucun rapport avec cette story.
> - **Point 5, un appariement ne confronte que des boutons qu'un couple masquage/marqueur laisse
>   visibles ensemble** : intact, et il **retrouve sa population de production**. Cet `ADR` ecrivait,
>   en consequence : *"le mecanisme de traversee de frontiere d'`ADR-0017` n'a plus aucun objet en
>   production [...] il redeviendra portant le jour ou un element permanent portera un bouton visible
>   sur un ecran de jeu"*. **`S14-1` est ce jour-la.** Le mecanisme garde en 2026-08-07 sans population
>   redevient exact et utile sans qu'une ligne change.
>
> **Ce qui tombe, et ou il vit.** La propriete que `DEC-0032` attribue a cet `ADR` - "un ecran de jeu
> ne porte qu'une seule sortie visible" - n'est **aucune de ses cinq clauses**. C'est l'arbitrage de
> `nadia-prof` du 2026-08-07, rapporte dans le contexte ci-dessus, et cet `ADR` ne l'encode
> qu'indirectement, en masquant un element. La clause executable qui tombe est ailleurs, et elle est
> nommee ici parce que personne ne l'avait vue :
>
> ```java
> Elements liensDeNavigation = page.select("header button, header a[href]");
> assertThat(liensDeNavigation)
>         .as("DEC-0012 : le menu de l'en-tete contient exactement une entree")
>         .hasSize(1);
> ```
>
> `EnTeteEtMenuTest.leMenuDeLEnTeteContientExactementUneEntreeVersLAccueil`, un **comptage fixe** qui
> releve `DEC-0012` et non cet `ADR`. Mesure du 2026-08-17, deux puces posees dans l'en-tete : cette
> assertion est l'un des trois seuls echecs de `adapter-web` (233 tests). Elle doit etre remplacee dans
> le commit de la barre, par la propriete derivee que l'avis de faisabilite de s14 specifie - un
> element de role `sortie` dont le libelle est "Les jeux", plus un point d'entree par niveau distinct
> du catalogue - jamais par un nombre releve a la main. **Un comptage fixe n'est pas une propriete :
> c'est la taille du depot au jour ou il a ete ecrit.**
>
> Ce que cet amendement ne change pas : aucune ligne de la decision, aucune consequence, aucun
> garde-fou de cet `ADR`. Il ne prononce rien sur l'arbitrage prealable de `nadia-prof`, qui reste
> entier et qui reste le sien.

> **Second amendement du 2026-08-17 (s14, apres la livraison de `S14-2` / `DEC-0033`) - le sujet du
> masquage disparait, et l'`ADR` n'est ni faux, ni remplace, ni sans objet. Ce qu'il perd et ce qu'il
> garde, clause par clause et non en bloc.**
>
> `S14-2` retire le lien "Les jeux" de l'en-tete, donc `a.lien-accueil` de tout gabarit, donc la regle
> `body:has(.ecran-de-jeu) .lien-accueil { display: none }` de la feuille servie. **Deux garde-fous
> independants refusent de garder la regle sans son element** - celui de cet `ADR` (*"une regle qui
> masque une classe absente est decorative"*) et celui d'`ADR-0031` (un selecteur qui ne peut plus
> jamais s'appliquer). Le retrait suit leur verdict ; il ne l'esquive pas.
>
> **Etat des cinq clauses apres `S14-2`.**
>
> | Clause | Population en production | Ce qui la garde |
> |---|---|---|
> | **1. Le marqueur `ecran-de-jeu` sur la zone de contenu** | **pleine** : les quatre fragments le portent toujours | **plus rien, et c'est le defaut de ce lot** - voir ci-dessous |
> | **2. La forme contractuelle du masquage dans la feuille** | **vide** | le mecanisme reste exact et **eprouve sur deux jeux fautifs** (`fragment-sans-marqueur`, `feuille-sans-masquage`) ; seule la clause qui l'exigeait *en production* est retiree |
> | **3. Le libelle "Les jeux", un seul mot de sortie** | **pleine** : `sortie-pleine` et `retour-accueil` | `leLienDeMenuEtLaSortieDeLaZoneDeContenuPortentLeMemeMot`, vert et non vacuant - verifie, pas suppose |
> | **4. `sortie-pleine` conserve tel quel** | pleine | inchange |
> | **5. L'appariement derive des masquages** | le **mecanisme de traversee de frontiere** garde sa population - les puces de niveau de `S14-1` sont permanentes et rien ne les masque ; la **dependance au masquage**, elle, n'a plus d'objet, la liste des masquages servis etant vide | `aucuneSortieNeDeclarePlusQuUneActionDuMemeEcran` et `rolesDominesJamaisConfrontes` (`ADR-0043`) |
>
> **Pourquoi un amendement de constat, et non un `ADR` neuf ni un changement de statut.** Aucune
> clause n'est devenue fausse : le mecanisme fait exactement ce qu'il decrit, et il le refera le jour
> ou un habillage permanent devra disparaitre sur certains ecrans. Aucun `ADR` posterieur ne le
> remplace - `DEC-0033` retire un **element**, pas une decision. Et un `ADR` neuf ne deciderait rien
> de neuf. **La doctrine appliquee ici est celle que cet `ADR` s'est donnee a lui-meme en s06** pour le
> mecanisme d'`ADR-0017` : *"Un mecanisme juste, prouve mordant sur un objet neuf, dont la population
> de production est momentanement vide, n'est pas un garde-fou decoratif. Ce qui serait decoratif [...]
> c'est l'epinglage qui pretendait que la production l'exerce."* Le point 2 est desormais dans cette
> situation, et il y est traite comme il l'avait prescrit pour autrui.
>
> **Ce qui le rouvrirait, ecrit pour ne pas etre redecouvert** : le prochain habillage permanent dont
> la visibilite doit dependre de l'ecran. Le couple declaratif, sa forme contractuelle et son garde-fou
> sont prets et eprouves ; il n'y aura rien a reinventer, et `LEntreeDeMenuNeSurvitPasAUnEcranDeJeuTest`
> redeviendra portant par la seule ecriture d'une regle de masquage. Un signal secondaire vaut d'etre
> note : `AucunArretDeTabulationNEstDestructeurTest` continue de lire `masquages(RACINE_PRODUCTION)`
> dans ses clauses vivantes, sur une liste desormais vide. Ce n'est pas un defaut - rien n'est masque,
> donc rien n'est a retirer de l'ordre de tabulation - mais c'est un mecanisme a population vide de
> plus, et il redeviendra portant par le meme geste.
>
> **La consequence que je refuse de laisser passer, et qui conditionne mon accord sur ce lot.** La
> clause 1 garde sa population et **perd le seul garde-fou qui l'exigeait**. Le marqueur
> `ecran-de-jeu` n'est plus consomme par la feuille ; il est consomme par
> `LEcranDeJeuDeclareCommentIlEstArriveTest` (`ADR-0027`), **qui derive sa population d'ecrans par
> `select(".ecran-de-jeu")` sur les reponses reelles**. Sa non-vacuite est globale ("au moins un ecran
> de jeu est rendu"), jamais par fragment. Un cinquieme fragment qui oublierait le marqueur sortirait
> donc **silencieusement** du perimetre d'`ADR-0027`, et la suite resterait verte : c'est exactement la
> faute que la table de garde-fous de cet `ADR` fermait ligne 1 (*"Un fragment neuf oublie le marqueur
> | les ecrans sont derives du repertoire de gabarits"*), et la famille de defaut que la retro de s05 a
> sanctionnee deux fois.
>
> **La propriete de la clause 1 est donc re-portee, decouplee du masquage** : *tout gabarit insere dans
> la zone echangee porte le marqueur d'etat*. Elle ne se derive plus de la feuille, qui ne le nomme
> plus ; elle se derive du **consommateur** du marqueur. L'enonce, l'emplacement et la preuve de
> morsure sont ecrits dans la section "Accord sur le lot S14-2" de
> `squad/sprints/s14-points-d-entree-par-niveau/confirmation-archunit.md`, et cette clause est une
> **condition de cloture de `S14-2`**, pas une regle due par un lot futur - la difference avec la dette
> d'`ADR-0042` est la, et elle est tenue par la porte de cloture, pas par une promesse.

> **Troisieme amendement du 2026-08-17 (s14, apres le refus pedagogique de `nadia-prof`) - celui-ci
> n'est pas un constat : il change la forme de la clause 2, parce que la forme d'origine obligeait a
> amender cet `ADR` a chaque fois que `nadia-prof` change d'avis. Elle l'a change deux fois en un jour ;
> c'est la clause qui est en cause, pas elle.**
>
> **Le declencheur que j'ai ecrit ce matin s'est produit ce soir.** `nadia-prof` refuse le Done : sur
> l'ecran de confirmation l'enfant a **quatre** boites pressables au lieu des deux que son arbitrage
> autorise, sur l'ecran d'erreur **trois** au lieu d'une - les puces de niveau survivent la ou elles ne
> doivent pas. Le mecanisme de masquage retrouve donc des sujets de production, et il en retrouve
> **deux ecrans sur quatre**, ce que la clause 2 d'origine ne savait pas dire.
>
> **Ce que la forme d'origine presupposait sans l'ecrire, et qui est faux.** Elle nommait *un*
> habillage (`.lien-accueil`), *un* marqueur (`ecran-de-jeu`), et son garde-fou en tirait que **tout**
> gabarit insere dans la zone devait porter ce marqueur - vrai quand le masquage vise la famille
> entiere des ecrans de jeu, **faux des qu'il en vise un sous-ensemble**. Ici les puces doivent
> disparaitre sur la confirmation et sur l'erreur, et **rester** sur la manche (c'est tout l'objet de
> `S14-1`) comme sur la fin de partie. Le marqueur `ecran-de-jeu`, porte par les quatre fragments, est
> structurellement incapable de porter cette distinction : reutilise tel quel, il entrainerait la fin de
> partie par erreur.
>
> **Clause 2, forme generalisee. Trois changements, et aucun ne touche la forme du selecteur.**
>
> 1. **Un masquage keye un marqueur d'etat quelconque, pas `ecran-de-jeu`.** La forme contractuelle
>    `body:has(.<marqueur>) .<habillage> { display: none }` est **inchangee, caractere pour
>    caractere** ; ce qui cesse d'etre fige est *quel* marqueur elle nomme. Un ecran declare autant de
>    marqueurs que d'etats qu'il veut rendre lisibles a la feuille.
> 2. **Un masquage peut viser un sous-ensemble d'ecrans.** Le garde-fou n'exige plus que **tout** ecran
>    insere porte le marqueur d'un masquage : il exige que le marqueur soit **pose par au moins un**
>    ecran insere, et que l'habillage masque soit **pose par au moins un element permanent**. La liste
>    des ecrans concernes cesse d'etre une propriete du garde-fou pour devenir **une donnee du
>    gabarit** - un attribut de classe, ajoute ou retire.
> 3. **Le garde-fou n'exige plus qu'un masquage existe en production.** L'assertion
>    `masquages(...).isNotEmpty()` est retiree. **C'est elle, et rien d'autre, qui a produit tout le
>    desordre de ce sprint** : c'est elle qui a rendu le test rouge quand `S14-2` a retire le dernier
>    masquage, c'est son retrait force qui a emporte la clause du marqueur, et c'est ce retrait qui a
>    laisse la clause 1 orpheline. **Un garde-fou ne doit pas exiger que son mecanisme soit employe** -
>    il doit exiger que le mecanisme, **quand il est employe**, le soit des deux cotes.
>
> **Ce que la clause 2 ne dit toujours pas, et qui reste hors de portee** : que l'element masque soit
> reellement invisible. C'est une propriete du rendu, `nadia-prof` l'a nommee comme sienne en s06 et
> elle vient de la mesurer par capture - c'est ainsi que ce refus est arrive, et c'est la division du
> travail voulue. Le garde-fou lit un couple declaratif ; elle regarde l'ecran.
>
> **Pourquoi un amendement et non un `ADR` neuf.** Le mecanisme, son motif, ses trois alternatives
> ecartees (`hx-swap-oob`, `th:if`, deplacer l'element dans la zone) et son garde-fou sont ceux de cet
> `ADR` ; rien de neuf n'est decide sur le *comment*. Ce qui change est l'**etendue** d'une clause
> existante, generalisee de "un habillage sur une famille d'ecrans" a "un habillage sur les ecrans que
> le serveur nomme". Un `ADR` neuf dupliquerait tout le contexte pour n'ajouter qu'un quantificateur.
>
> **Ce qui reste a `.ecran-de-jeu`, et ce qui ne change pas de statut.** Le selecteur `body:has()`
> revient, mais il revient sur un **marqueur neuf**, pas sur celui-la. `ecran-de-jeu` n'est toujours
> nomme par **aucun** selecteur qui l'utilise pour styler quoi que ce soit : son seul consommateur reste
> `LEcranDeJeuDeclareCommentIlEstArriveTest` (`ADR-0027`), et il ne lui reste dans la feuille qu'une
> regle vide pour satisfaire `HabillageDesEcransTest`. **La raison qui en faisait une classe n'est donc
> pas revenue, et ma recommandation de migration vers un attribut ne change pas de statut : elle reste
> differee, candidat de story.** Elle en sort meme renforcee : ce sprint montre que deux marqueurs de
> nature differente coexistent desormais - l'un que la feuille selectionne, donc une classe legitime,
> l'autre purement declaratif, donc un attribut selon la doctrine qu'`ADR-0027` a ecrite pour lui-meme.
> Les confondre en une seule classe est precisement ce qui a fait croire, en s14, que `ecran-de-jeu`
> pouvait porter les deux proprietes.
>
> **Les regles executables de cet amendement sont des conditions de cloture du lot en vol**, pas des
> regles dues par un lot futur : elles sont enumerees dans
> `squad/sprints/s14-points-d-entree-par-niveau/confirmation-archunit.md`, section 7. **Et je borne
> l'usage que je fais de ce levier pour la seconde fois** : si le lot ferme sans ces clauses, c'est cet
> amendement-ci qui doit etre annule, pas la dette qui doit etre ouverte. Un `ADR` amende dont la regle
> n'existe pas est ce que `ADR-0042` a coute a ce depot.

> **Verification du 2026-08-17 (s15) - la reforme de la clause 2 mesuree sur le cas pour lequel elle
> a ete ecrite. Ce n'est pas un amendement : aucune clause ne change.**
>
> `DEC-0034` fait revenir "Les jeux" dans l'en-tete, donc `.lien-accueil` et son masquage : la
> population de la clause 2 change pour la **quatrieme fois en un jour**. Le troisieme amendement
> ci-dessus a ete ecrit pour que ce cas ne coute aucun fichier de garde-fou ; plutot que de l'affirmer,
> je l'ai mesure.
>
> Methode, dans le meme paragraphe : mutation du depot reel a `5b50000`, `.lien-accueil` et `.menu`
> remis dans `accueil.html` et dans `recreo.css` dans leur forme d'avant `S14-2` (relevee dans
> l'historique, `cc68304`), masquage `body:has(.ecran-de-jeu) .lien-accueil { display: none }` compris ;
> `bash outils/mvn-jdk21.sh -B verify` apres `clean`, puis mutation annulee, `git status` propre.
>
> **Resultat sur la reforme : zero fichier de garde-fou.**
> `LEntreeDeMenuNeSurvitPasAUnEcranDeJeuTest` reste **vert, 6 cas sur 6**, avec un masquage qui
> reapparait sur un marqueur (`ecran-de-jeu`) qu'aucun selecteur maison n'utilisait plus, coexistant
> avec celui de `S14-2` (`ecran-exclusif`). C'est exactement la propriete visee par les trois
> changements : un marqueur quelconque, un sous-ensemble d'ecrans, et plus aucune exigence que le
> mecanisme soit employe. Verts aussi, sans qu'une ligne bouge :
> `UnEcranNeGouvernePasLApparenceDUnAutreTest`, `HabillageDesEcransTest`,
> `AucunArretDeTabulationNEstDestructeurTest`, `LActionNeDeclarePasMoinsQueLaSortieTest`,
> `TailleDesTextesEtBoutonsTest`. Les seules mentions de `.lien-accueil` qui subsistent dans le code de
> test sont trois lignes de Javadoc et de commentaire, aucune expression executable.
>
> **Ce que le retour coute quand meme, et qui n'est pas cet ADR** : 2 echecs dans
> `EnTeteEtMenuTest`, sur les deux assertions qui encodent `DEC-0033`/`BARRE-7`. Ce rouge est correct -
> un garde-fou qui encode un arbitrage pedagogique doit rougir quand l'arbitrage tombe - et son
> retrait est une consequence de `DEC-0034` a acter par `camille-po` avec `nadia-prof`, pas un geste
> d'architecture. Un troisieme echec s'y ajoute depuis `ADR-0046`, dans le cliquet d'etats interactifs :
> `.lien-accueil` est un bouton de contour sur fond sombre sans etat declare, c'est-a-dire le defaut du
> jour sur le point de revenir par le meme chemin.

> **Note du 2026-08-18 (s15) - la premisse de cet `ADR` est renversee par l'utilisatrice, et aucune de
> ses clauses ne bouge. Ce n'est pas un amendement : c'est la mesure de celui d'hier.**
>
> L'utilisatrice a rejoue l'increment et tranche que **la sortie vit dans l'en-tete, y compris pendant
> une partie**. C'est le renversement complet de l'arbitrage de `nadia-prof` du 2026-08-07 rapporte en
> contexte ci-dessus - *"la sortie qui survit sur un ecran de jeu est le bouton de la zone de contenu,
> pas le lien de l'en-tete"*. Elle est l'autorite au-dessus de la squad, l'arbitrage ne se rediscute
> pas, et le refus de `nadia-prof` reste lisible dans sa fiche. **Un lecteur de cet `ADR` doit savoir
> que sa premisse a change**, et c'est le seul objet de cette note.
>
> **Ce que ce renversement coute a cet `ADR` : rien.** La population de masquage change pour la
> cinquieme fois - `.lien-accueil` masque sur trois ecrans sur quatre au lieu de quatre sur quatre, et
> plus du tout sur la manche. Depuis le troisieme amendement, une telle bascule coute **trois regles
> CSS et deux attributs de classe** : zero clause d'`ADR` retiree ou ajoutee, zero fichier de garde-fou
> touche sur ce mecanisme, et l'appariement de saillance d'`ADR-0017` se recompose seul depuis les
> masquages servis (point 5). Le troisieme amendement annoncait cette mesure - *"si l'humain change
> d'avis, combien de fichiers de garde-fou bougent ? La bonne reponse est zero"* - et le geste suivant
> l'a prise.
>
> **Ce qui survit et se trouve confirme par le renversement lui-meme** : le **point 3**. Toutes les
> sorties portent toujours "Les jeux", ou qu'elles vivent, donc
> `leLienDeMenuEtLaSortieDeLaZoneDeContenuPortentLeMemeMot` reste vert et non vacuant. Cette clause
> portait sur le **mot** et jamais sur l'ecran qui le porte ; c'est ce qui lui permet de traverser
> intacte un renversement de l'arbitrage qui l'avait motivee. Une clause bien formee survit au
> jugement qui l'a fait naitre.
>
> Le detail - marqueur neuf, trois regles, deux clauses de garde-fou dues au lot, releve des sources
> qui perdent leur sujet - est dans
> `squad/sprints/s15-cinq-arbitrages-de-l-en-tete/exigences-theo-sortie-en-tete.md`.

Elle me laisse deux questions techniques, et c'est tout ce que cet ADR traite.

### Question 1 - un element permanent qui doit disparaitre sur certains ecrans

`DEC-0012` et le critere d'acceptation de S6-1, livre en s05, font de l'en-tete un element
**permanent** : frere ou ancetre de la zone `#jeu`, jamais efface ni recree par un echange htmx.
`nadia-prof` demande maintenant que son unique entree de menu ne soit **pas visible** sur les cinq
etats de l'ecran de jeu (manche, retour juste, retour faux, fin de partie, erreur), tout en restant
visible sur l'accueil. Elle nomme ce qu'elle veut voir et me rend le comment, explicitement.

Le probleme n'est pas cosmetique, et il n'a pas de solution evidente :

- **Le serveur ne rend que la zone `#jeu`.** Tous les declencheurs du depot posent
  `hx-target="#jeu" hx-swap="innerHTML"`. L'en-tete est le frere de cette zone : aucune reponse du
  serveur ne le traverse. Un `th:if` sur l'entree de menu ne vaudrait donc que pour un chargement de
  page complet, et laisserait l'en-tete **perime** apres chaque echange htmx - c'est-a-dire faux
  exactement dans le cas que `nadia-prof` decrit.
- **Aucun JavaScript n'entre ici.** Le comportement de jeu vit sur le serveur (ADR-0008), le
  JavaScript de Bootstrap n'entre pas sans un ADR qui l'ouvre nommement (ADR-0014 point 7), et
  `FrontieresDuJavaScriptTest` garde le budget.
- **La solution ne doit pas rouvrir ADR-0020.** Je viens de fermer, dans le commit precedent, un
  identifiant duplique dans le document assemble. Toute solution qui ajoute un identifiant ou un
  second mecanisme d'echange doit etre pesee contre ce risque.

### Question 2 - un appariement qui survivrait a la disparition de son sujet

`nadia-prof` nomme elle-meme le symptome, et elle a raison. Mon extension d'ADR-0017 du 2026-08-07
attribue a **tous** les ecrans un bouton ecrit hors de la zone echangee, et confronte donc le lien de
l'en-tete a `.btn.proposition` et a `.btn.encore`. Si l'entree de menu cesse d'etre visible sur les
ecrans de jeu, ces boutons ne s'afficheront plus jamais ensemble : les confronter ne mesure plus une
situation que l'enfant rencontre. **C'est la famille de defaut que la retro de s05 a sanctionnee deux
fois** - un garde-fou vert qui ne verifie plus rien - et cette fois elle serait de mon fait.

## Decision

**Un habillage permanent dont la visibilite depend de l'ecran declare son masquage dans la feuille
maison ; l'ecran, lui, declare son etat par un marqueur que le serveur pose dans la zone echangee. Un
appariement de saillance ne confronte que des boutons que ce couple laisse visibles ensemble.**

1. **Le serveur pose un marqueur d'etat sur la zone de contenu, jamais sur l'en-tete.** Chaque
   fragment insere dans `#jeu` porte la classe `ecran-de-jeu` sur sa racine. Cette classe ne porte
   aucun style propre : elle dit un etat, et c'est tout ce qu'elle fait.

2. **La feuille maison declare le masquage, sous une forme contractuelle** :

   ```css
   body:has(.ecran-de-jeu) .lien-accueil {
       display: none;
   }
   ```

   L'entree de menu **reste presente dans le document** - `DEC-0012` et S6-1 sont tenus a la lettre,
   l'en-tete n'est ni efface ni recree. C'est sa **visibilite** qui depend de l'etat, jamais son
   existence. `nadia-prof` a d'ailleurs pris soin d'ecrire qu'elle s'interdisait de demander sa
   suppression.

3. **Le libelle de l'entree de menu devient "Les jeux".** Un seul mot de sortie circule sur toute
   l'application. C'est le geste par lequel `nadia-prof` leve sa reserve 3, et le seul changement de
   texte que l'arbitrage autorise.

4. **Les deux branches du `th:class` de `fragments/retour.html` et le bouton de
   `fragments/manche.html` ne sont pas touches.** Ni retires, ni renommes, ni redimensionnes.
   `sortie-pleine` est conserve tel quel.

5. **Un appariement de saillance derive des masquages la liste des ecrans ou un habillage hors zone
   reste visible.** Le lien de l'en-tete n'est plus confronte aux actions des ecrans de jeu, parce
   qu'il ne s'y montre plus ; il reste confronte aux actions de l'accueil, ou il se montre. Le doute
   joue dans le sens de la comparaison : un habillage qu'aucun masquage ne nomme reste confronte a
   tous les ecrans, comme avant.

### Pourquoi un selecteur relationnel, et pas les deux autres voies

**`hx-swap-oob` (echange hors bande).** Chaque fragment de jeu embarquerait une version vide de
l'en-tete, que htmx substituerait a l'originale. C'est la voie la plus tentante parce qu'elle garde
tout cote serveur. Je l'ecarte pour un motif que j'ai ecrit moi-meme il y a un commit : ADR-0020 nomme
comme **dette explicite et mode de defaillance le plus dangereux** le fait que son garde-fou
reimplemente `hx-swap="innerHTML"` et **assemblerait le mauvais document en restant vert** devant tout
autre `hx-swap`. Introduire `hx-swap-oob` declencherait cette dette immediatement. Elle exigerait de
plus un identifiant sur l'en-tete et la duplication de son balisage dans trois fragments - deux
choses qu'ADR-0020 vient de retirer du depot.

**Un `th:if` cote serveur sur l'entree de menu.** Ecarte parce qu'il est simplement faux : l'en-tete
n'est pas re-rendu par un echange htmx, donc la condition ne serait evaluee qu'au chargement complet.
L'entree resterait affichee pendant toute une partie commencee depuis l'accueil.

**Le selecteur relationnel a un avantage que je n'avais pas anticipe et que je verifie** : il traite
gratuitement le cas du **chargement complet pendant une partie en cours**. `GET /` avec reprise rend
`fragments/manche` a l'interieur de `#jeu` ; le document assemble porte donc le marqueur, et l'entree
est masquee sans qu'aucune condition serveur n'ait a le savoir. Une solution `th:if` aurait demande de
traiter ce cas a part.

## Regle executable

### Pourquoi ce n'est pas une regle ArchUnit

Meme motif qu'ADR-0020, et il n'a pas change : ArchUnit raisonne sur des classes compilees, des
dependances et des annotations. Rien ici n'est une dependance - ce sont une declaration CSS et un
attribut de gabarit.

### Le garde-fou neuf

Fichier : `adapters/adapter-web/src/test/java/com/recreo/adapter/web/LEntreeDeMenuNeSurvitPasAUnEcranDeJeuTest.java`

**Ce qu'il ne fait pas, dit avant ce qu'il fait** : il ne mesure aucune visibilite. Une visibilite est
une propriete du rendu, et aucun automate du depot ne rend une page. `nadia-prof` a ecrit elle-meme
que "que l'entree de menu soit reellement invisible sur les cinq etats" reste une propriete dont elle
est seule garde, et je ne pretends pas la lui prendre.

**Ce qu'il interdit reellement** : que l'un des deux termes du couple declaratif existe sans l'autre.
C'est la seule facon dont cette propriete se perdra, et il y en a exactement quatre formes, toutes
fermees :

| Faute possible | Ce qui la refuse |
|---|---|
| Un fragment neuf oublie le marqueur | les ecrans sont derives du repertoire de gabarits ; tout gabarit qui n'est pas la coquille est un ecran insere, donc doit le porter |
| La regle de masquage disparait de la feuille | le garde-fou echoue faute de masquage, avant meme de juger un ecran |
| Le marqueur est renomme d'un cote seulement | le masquage nomme le marqueur ; l'ecart devient un defaut nomme |
| La coquille porte elle-meme le marqueur | l'entree serait masquee partout, y compris sur l'accueil : refuse explicitement |

**Perimetre derive, et la coquille n'est pas nommee.** La coquille est identifiee par le fait que son
document **contient la zone `hx-target`** - jamais par son nom de fichier ni par son repertoire. Tout
autre gabarit est un ecran insere dans cette zone. Un fragment neuf entre donc dans le controle sans
qu'on l'y inscrive, et c'est le seul point qui compte : la regression viendra du quatrieme fragment,
pas des trois qui existent.

**Derivation totale (ADR-0013 obligation 2).** Sont des **echecs**, jamais des cas ignores : aucune
zone `hx-target`, plusieurs zones distinctes, aucune coquille, plusieurs coquilles, aucun fragment,
aucun gabarit, aucun masquage, et un masquage dont la classe n'est portee par aucun gabarit - ce
dernier fermant le cas de la regle de masquage qui ne masque rien, donc decorative.

**Un second epinglage, sur le texte** : `leLienDeMenuEtLaSortieDeLaZoneDeContenuPortentLeMemeMot`
releve le libelle de **tous** les elements `data-role="sortie"` du depot et exige qu'ils portent tous
le meme mot, "Les jeux". Il n'enumere pas les sorties, il les derive de l'attribut de role. Si
quelqu'un remet "Accueil" sur le lien de menu, la reserve 3 de `nadia-prof` se rouvre - elle l'a levee
par ce geste precis et pas par un autre.

### Le garde-fou amende

Fichier : `adapters/adapter-web/src/test/java/com/recreo/adapter/web/LActionNeDeclarePasMoinsQueLaSortieTest.java`

L'attribution des ecrans a un bouton hors zone passe de "tous les ecrans" a "tous les ecrans ou aucun
masquage ne le cache" :

```java
    private static List<String> ecransOuIlResteVisible(Map<String, Document> documents,
                                                      List<String> classes,
                                                      List<Masquage> masquages) {
        List<String> visibles = new ArrayList<>();
        for (Map.Entry<String, Document> ecran : documents.entrySet()) {
            boolean masque = masquages.stream()
                    .anyMatch(masquage -> classes.contains(masquage.habillage())
                            && masqueSur(ecran.getValue(), masquage));
            if (!masque) {
                visibles.add(ecran.getKey());
            }
        }
        return visibles;
    }
```

La liste des masquages vient de la feuille servie, pas d'une liste ecrite dans le test : c'est la
feuille qui gouverne ce rendu, donc c'est d'elle que le perimetre descend (ADR-0015).

### L'epinglage supprime, et son motif ecrit

`leLienDeLEnTeteEstConfronteAuxActionsDesEcransDeJeu`, ecrit le 2026-08-07, est **supprime** le meme
jour. Motif, exige par ADR-0013 et par la consigne de `sofia-sm` : il affirmait que le lien de
l'en-tete est confronte a `.proposition` et a `.encore`. Apres l'arbitrage, ces boutons ne s'affichent
plus jamais ensemble. Le maintenir aurait exige de mesurer un ecran que l'enfant ne rencontre jamais,
c'est-a-dire de garder vert un appariement sans objet.

Il est **remplace, pas retire**, par `leLienDeLEnTeteEstConfronteAuxSeulesActionsDeLAccueil`, qui tient
la propriete par ses deux bouts :

- la premiere assertion exige que le couple restant existe - l'accueil, seul ecran ou le lien se
  montre, ou il accompagne les boutons de titre de jeu. La propriete d'ADR-0017 y vaut pleinement :
  un enfant qui regarde la liste des jeux ne doit pas voir la sortie plus saillante qu'un jeu a
  ouvrir ;
- la seconde exige que les couples retires **ne reviennent pas**. Elle rougit si quelqu'un retire la
  regle de masquage en gardant le lien dans l'en-tete, c'est-a-dire si l'arbitrage est defait en
  silence.

**Ce que je ne cache pas** : le mecanisme de traversee de frontiere de l'extension du 2026-08-07 - un
bouton hors zone confronte aux boutons d'un autre fichier - **n'a plus aucun objet en production**,
puisque le seul bouton hors zone est desormais confronte a un bouton du meme fichier. Je le garde
malgre cela, et je dis pourquoi plutot que de le presenter comme actif : il reste **correct**, il
reste **eprouve** par son jeu fautif `entete-permanente-plus-grosse` qui le fait rougir, et il
redeviendra portant le jour ou un element permanent portera un bouton visible sur un ecran de jeu. Un
mecanisme juste, prouve mordant sur un objet neuf, dont la population de production est
momentanement vide, n'est pas un garde-fou decoratif - c'est exactement ce que la mise a l'epreuve sur
objet neuf d'ADR-0013 sert a rendre acceptable. Ce qui serait decoratif, et que je supprime, c'est
l'**epinglage** qui pretendait que la production l'exerce.

### La preuve que ces garde-fous mordent

Methode : une mutation a la fois appliquee au depot, puis `mvn -B -o -pl adapters/adapter-web test`
restreint aux trois classes qui gardent cette propriete
(`LActionNeDeclarePasMoinsQueLaSortieTest`, `LEntreeDeMenuNeSurvitPasAUnEcranDeJeuTest`,
`EnTeteEtMenuTest`), depot restaure ensuite. La colonne de droite liste les tests reellement rouges,
releves dans la sortie de Maven - pas les tests que j'attendais rouges. Le perimetre de la mesure est
donc ces trois classes : un quatrieme garde-fou ailleurs dans le depot ne serait pas visible ici, et
la derniere ligne de cette section donne la mesure sur le depot entier.

| Mutation appliquee | Tests rouges mesures |
|---|---|
| La regle `body:has(.ecran-de-jeu) .lien-accueil { display: none }` retiree de la feuille maison | `LEntreeDeMenuNeSurvitPasAUnEcranDeJeuTest.chaqueEcranInsereDansLaZonePorteLeMarqueurQueLaFeuilleExige` **et** `LActionNeDeclarePasMoinsQueLaSortieTest.leLienDeLEnTeteEstConfronteAuxSeulesActionsDeLAccueil` |
| `class="ecran-de-jeu"` retire de `fragments/retour.html` seulement | les **memes deux** tests |
| Le libelle du lien de menu revenu a "Accueil" | `LEntreeDeMenuNeSurvitPasAUnEcranDeJeuTest.leLienDeMenuEtLaSortieDeLaZoneDeContenuPortentLeMemeMot`, `leLienDeLEnTeteEstConfronteAuxSeulesActionsDeLAccueil` **et** `EnTeteEtMenuTest.leMenuDeLEnTeteContientExactementUneEntreeVersLAccueil` |

Les deux premieres mutations ne sont couvertes par **rien d'autre** que les deux garde-fous nommes :
ce sont des mutations de mecanismes qui n'existaient pas avant ce commit, il n'y a donc pas d'etat
anterieur auquel les comparer. La troisieme etait deja couverte a `HEAD` par `EnTeteEtMenuTest` et par
l'ancien epinglage ; sa couverture a donc **change de porteur sans laisser de trou**, ce que la
troisieme ligne mesure.

Deux jeux fautifs neufs, permanents dans la suite, sous
`adapters/adapter-web/src/test/resources/contreexemple/LEntreeDeMenuNeSurvitPasAUnEcranDeJeuTest/` :

- `fragment-sans-marqueur` : deux ecrans inseres dans la meme zone, l'un declare le marqueur, l'autre
  l'oublie. La feuille de ce jeu declare bien le masquage, donc c'est l'oubli du gabarit qui est juge
  et rien d'autre. C'est l'objet neuf d'ADR-0013 : un jeu de gabarits que personne n'a inscrit nulle
  part, portant la faute qu'un fragment neuf commettra ;
- `feuille-sans-masquage` : la feuille perd sa regle, et elle est jugee **avec les gabarits de la
  production**. C'est la forme de la regression qui ne se voit pas dans un diff de gabarit.

**Ce que la premiere ligne du tableau prouve accessoirement, et qui repond a la question 2 de
`nadia-prof`** : retirer la regle de masquage fait revenir les appariements avec `.proposition` et
`.encore`. L'appariement depend donc reellement de la feuille, par la seule voie de `masquages()`.
Sans cet amendement, il n'aurait pas pu en dependre du tout - la logique d'appariement ne lisait que
la position dans le DOM et les conditions Thymeleaf, toutes deux **aveugles a une regle CSS**. C'est
la mesure de ce que l'appariement serait devenu : vert, et vide.

## Consequences

- **Le depot depend desormais du selecteur relationnel `:has()`.** C'est la consequence la plus
  serieuse et je ne l'habille pas : `:has()` est du CSS recent, et un navigateur qui ne le connait
  pas ignore la regle entiere - l'entree de menu redeviendrait visible sur les ecrans de jeu, sans
  aucun signal. **Le mode de defaillance est silencieux cote enfant**, meme si aucun automate n'en
  est complice. Ce qui rend ce risque acceptable ici : la degradation ramene exactement l'etat que
  `nadia-prof` vient d'auditer et de juger "valide sous reserve", pas un ecran casse ; et le depot
  vendorise deja Bootstrap 5.3.8, dont la ligne de base navigateurs est de la meme generation. Ce qui
  me ferait changer d'avis : une cible de deploiement nommee ou `:has()` manque.
- **Une classe de gabarit qui ne porte aucun style.** `ecran-de-jeu` dit un etat, pas une apparence.
  `HabillageDesEcransTest.aucuneClassePoseeSurUnElementNEstOrpheline` l'accepte parce que la feuille
  la nomme dans le selecteur de masquage - verifie, pas suppose. Je note l'inconfort : un lecteur
  peut chercher un bloc `.ecran-de-jeu { }` et n'en pas trouver.
- **Un couple declaratif a deux termes, dans deux fichiers de nature differente**, un gabarit et une
  feuille. C'est plus fragile qu'une condition unique, et c'est precisement pourquoi le garde-fou
  neuf existe : sans lui, la desynchronisation des deux termes serait muette.
- **Le mecanisme de traversee de frontiere d'ADR-0017 perd sa population de production.** Nomme
  ci-dessus, garde avec son motif.
- **Trois etats de l'ecran de jeu perdent une porte visible sur cinq**, du point de vue du nombre de
  boutons : c'est le but, et c'est `nadia-prof` qui l'a mesure. Ce n'est pas ma decision et je ne la
  reinstruis pas.

## Alternatives considerees

1. **`hx-swap-oob`.** Rejetee : declencherait immediatement la dette explicite d'ADR-0020, et
   demanderait un identifiant plus la duplication du balisage de l'en-tete dans trois fragments.
2. **Un `th:if` cote serveur sur l'entree de menu.** Rejetee : faux par construction, l'en-tete n'est
   pas re-rendu par un echange htmx.
3. **Deplacer l'entree de menu dans la zone echangee**, ou le serveur la controle entierement.
   Rejetee, et c'est la seule dont le rejet ne m'appartient pas entierement : elle detruirait le
   critere d'acceptation de S6-1 et le point 2 de `DEC-0012`, que `nadia-prof` a valides le meme jour,
   et elle ferait a S6-1 exactement ce qu'elle reproche a S6-9 de faire a S3-6. Je ne prends pas cette
   liberte.
4. **Supprimer l'entree de menu.** Rejetee pour la meme raison, et `nadia-prof` s'est explicitement
   interdit de la demander.
5. **Supprimer l'extension d'ADR-0017 du 2026-08-07 plutot que l'amender.** Rejetee : le mecanisme est
   correct et il est le seul a fermer le point 5 d'ADR-0017. Ce qui etait faux n'est pas le mecanisme,
   c'est l'hypothese implicite qu'un bouton hors zone est visible partout.
6. **Ne rien changer a `LActionNeDeclarePasMoinsQueLaSortieTest`, et livrer seulement le masquage.**
   Rejetee, et c'est le coeur de la question 2 : la suite serait restee **entierement verte** en
   confrontant des boutons que l'enfant ne voit plus ensemble. C'est le defaut que la retro de s05 a
   sanctionne deux fois, et il aurait ete de mon fait.

## Ce qui reste non gardable, et le dit

- **Que l'entree de menu soit effectivement invisible sur les cinq etats.** Propriete du rendu.
  `nadia-prof` l'a nommee comme sienne et annonce qu'elle la verifiera en jouant, etat par etat, y
  compris l'ecran d'erreur.
- **Que `:has()` soit honore par le navigateur de l'enfant.** Aucun automate du depot ne rend une
  page ; le garde-fou verifie que la regle est ecrite, pas qu'elle s'applique. C'est la meme limite
  qu'ADR-0017 nomme pour lui-meme depuis s05, et elle vaut ici avec une consequence plus large,
  puisqu'il s'agit d'une regle dont l'echec entier est possible plutot que d'une valeur mal lue.
- **Que "Les jeux" soit dechiffrable au CP.** `nadia-prof` a ecrit son avis - le `x` final muet ne se
  deduit pas, mais le mot nomme ce que l'enfant fait - et annonce qu'elle le rejugera sur le libelle
  rendu.
