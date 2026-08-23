# ADR-0056 - Un seuil qu'on evite en nichant la declaration n'est pas un seuil, et une morsure a l'ancrage ambigu ne mord pas ce qu'elle nomme

- **Statut** : Accepte, ses deux regles ecrites dans le meme commit
  (`ViewportEtLargeurTelephoneTest`, un cas fautif commite de plus ;
  `LaLargeurOffreeAUneRangeeEstUneFonctionEpingleeTest` et
  `LaLargeurDemandeeParUneRangeeEstEpingleeTest`, une clause de plus chacun).
- **Sprint** : s18. **Date** : 2026-08-23. **Auteur** : theo-archi.
- **Arbre mesure** : `round-two` a `80462564229e3c3f6cba2b331ba54f2e4769aea9`, SHA releve par
  `git rev-parse HEAD` sur le worktree neuf, jamais repris d'un message de commit.
- **Route par** : personne. Les deux defauts sont trouves en instruisant la demande de confirmation
  arithmetique qu'`ines-design` m'adresse pour `SYN-11`
  (`squad/sprints/s18-fermer-avant-ouvrir/proposition-ines-bloc-de-retour-visible.md`, section
  "Ce que je remonte"). **Ils sont tous les deux les miens**, et aucun des deux ne se voyait en
  lisant : ils se sont vus en appliquant la declaration proposee a un arbre et en mesurant.
- **Complete** : `ADR-0054` et `ADR-0055` (dont les morsures portent le second defaut), `ADR-0013`
  (obligation 3, mise a l'epreuve), `ADR-0046` (la limite `@media` heritee), `ADR-0030` (pas de
  troisieme decoupage de blocs CSS dans ce module), `ADR-0051` (un garde-fou qui n'examine aucun de
  ses objets le declare - meme famille, autre mecanisme).

## Contexte

`ines-design` propose, pour remonter le bloc de retour juste/faux au-dessus du pli sur `SYN-11`,
d'elargir la largeur **offerte** a la rangee de propositions sur le seul palier 576-767 px, par une
declaration maison scopee a ce palier sur `.zone-jeu`. Elle me route nommement la confirmation
arithmetique du plafond de 720 px, et j'avais ecrit en instruisant `SYN-11` qu'elargir le plafond par
une declaration maison est *"autorise et bruyant"*.

**J'ai verifie en appliquant, pas en lisant.** La declaration a ete posee sur `recreo.css` dans un
worktree, la suite mesuree, la declaration retiree ; elle n'est dans aucun commit. Ce que la mesure
rend contredit une moitie de ma phrase : le geste est bruyant, mais **pas la ou je l'annonçais**, et
il est *interdit* la ou je le croyais autorise - par une clause qui ne le voyait pas.

### Defaut 1 - un seuil que le nichage evite, et qui donc n'existait pas sous `@media`

`ViewportEtLargeurTelephoneTest.verifierAucuneLargeurFixeeAuDelaDeLaCible` interdit, dans **toute**
feuille maison servie, une largeur declaree en pixels au-dela de la cible telephone : *"max-width ne
doit jamais depasser 375px"*. Le plafond de 720 px que la proposition pose est donc, tel que cette
clause est ecrite, **interdit**.

**Il passait en vert.** Le balayage de largeur decoupait la feuille sur les accolades : pour un bloc
`@media`, le texte avant la premiere accolade devenait le "selecteur" et **tout le corps du bloc**
devenait le corps de la regle. La premiere declaration de ce corps se lisait alors comme une
propriete nommee *"`.zone-jeu` accolade `max-width`"* - un nom que `isLargeurFixeeOuBornee` ne
reconnait pas, donc une largeur qui n'entrait dans aucune population. **La meme declaration etait
jugee au niveau superieur du fichier et invisible d'un cran vers l'interieur.**

Mesure, sur la declaration proposee, JDK 21.0.10, `-am` porte :

| Ou la declaration est ecrite | `aucuneLargeurNestFixeeAuDelaDeLaCibleTelephone` |
|---|---|
| `.zone-jeu { max-width: 720px }` au niveau superieur | **rouge** (720 > 375) |
| la meme sous `@media (min-width: 576px) and (max-width: 767.98px)` | **vert** |

Ce n'est pas un perimetre choisi : c'est un seuil qu'il suffit d'indenter pour ne plus rencontrer.
**Le mode de defaillance est un faux vert**, la famille que ce depot a deja sanctionnee plusieurs
fois, et il etait realise par le premier correctif de forme du sprint.

**Ce que ce defaut fait a mon propre avis.** J'ai ecrit que le geste etait "autorise et bruyant" en
raisonnant sur la cascade (`recreo.css` charge apres Bootstrap, meme specificite, l'ordre tranche -
ce point-la est exact et garde par `RessourcesTiercesTest.verifierFeuilleMaisonChargeeEnDernier`).
Je n'ai pas verifie qu'aucune **autre** clause ne l'interdisait. Il y en avait une, elle etait verte,
et son vert ne voulait rien dire.

### Defaut 2 - un ancrage de mutation qui designe deux endroits, et une morsure qui porte ailleurs

`ADR-0054` et `ADR-0055` prouvent leurs regles par des mutations du materiau reel. Chacune passe par
un `muter(racine, chemin, avant, apres)` qui verifiait que l'ancrage **existe** puis appelait
`String.replace`, qui reecrit **toutes** les occurrences.

Deux des sept morsures d'`ADR-0054` ont pour ancrage le texte `.zone-jeu {`. Il est unique
aujourd'hui - verifie, et non suppose : une occurrence dans `recreo.css`. **La declaration proposee
en ajoute une seconde.** A partir de ce jour-la, ces deux morsures mutaient deux endroits alors
qu'elles en nomment un, et la mesure montre ce que cela produit : leur **premiere** assertion - celle
qui constate qu'un autre garde-fou reste vert sur la mutation, c'est-a-dire precisement ce qu'elles
prouvent - tombait pour une cause qui n'etait pas la leur. Une morsure qui echoue pour une autre
raison que la sienne ne prouve plus rien ; et rien n'annonçait le glissement.

**C'est la lecon de s05 sur une autre face.** En s05 la fixture ressemblait a ce que j'imaginais
plutot qu'a ce que le monde ecrit. Ici l'ancrage etait exact quand je l'ai ecrit, et c'est **l'arbre
qui a bouge sous lui**. Un ancrage n'est pas une propriete du texte cherche, c'est une propriete du
couple (texte, arbre) - et rien ne le tenait.

## Decision

**Une seule propriete, deux mecanismes : un garde-fou qui ne rencontre pas son objet a cause de sa
propre lecture est un faux vert, jamais un perimetre.** `ADR-0051` disait qu'un garde-fou qui
n'examine aucun de ses objets le declare ; celui-ci dit qu'il ne s'arrange pas pour ne pas les
rencontrer.

1. **Une largeur declaree en pixels dans une feuille maison est jugee au meme titre, qu'elle soit
   ecrite au niveau superieur du fichier ou sous une regle-`@`.** Le balayage de
   `ViewportEtLargeurTelephoneTest` passe au lecteur qui garde le contexte de regles-`@`, celui
   qu'`ADR-0054` avait deja generalise pour la meme cause. Aucun troisieme decoupage de blocs CSS
   n'est introduit dans ce module (`ADR-0030`).
2. **Le seuil de 375 px n'est pas touche, et sa valeur n'est pas la mienne a changer.** Cet `ADR`
   rend la clause capable de voir ; il ne decide pas ce qu'elle doit accepter. Voir la section
   suivante, qui route la question.
3. **Une mutation de mise a l'epreuve porte sur un ancrage qui designe exactement un endroit.** Zero
   est un echec (la morsure ne mute rien et prouverait un vert de complaisance) ; plus d'un est un
   echec (la morsure porte sur des endroits que son auteur n'a pas nommes). L'ancien controle -
   *"l'ancrage existe"* - est remplace, pas double : il etait la moitie qui ne mord pas, exactement
   la forme qu'`ADR-0055` a sanctionnee sur les planchers de largeur.
4. **Aucun ancrage n'est reecrit dans ce lot.** Les dix ancrages des deux fichiers designent un seul
   endroit aujourd'hui - mesure, non suppose. Le lot qui ajoutera un second bloc `.zone-jeu` verra
   deux morsures rougir en nommant l'ancrage devenu ambigu, et devra les reancrer dans le meme
   commit. C'est le comportement voulu : un rouge au moment ou l'ambiguite nait, pas une derive
   muette.

**Ce qui n'est pas decide ici** : la largeur de reference et le sort du seuil de 375 px, la forme
retenue pour `SYN-11`, et tout ce qui releve du rendu. Aucun octet de ce que voit un enfant n'est
change par cet `ADR` ; il n'ajoute que du test.

## Ce que cet `ADR` route, et a qui

**A `camille-po` - le seuil de 375 px, dont la premisse a ete retiree du perimetre.** La clause
s'appelle *"la largeur cible telephone"* et vient de S3-8 (sprint 3), quand le telephone etait la
cible. `DEC-0040` (2026-08-22) a sorti la bande sous 576 px du perimetre, et la reference produit est
desormais 744 x 551. Depuis ce lot, la clause **voit** ce qu'elle jugeait deja en droit : elle
interdit donc a la feuille maison tout plafond en pixels au-dela de 375, alors que la feuille tierce
en declare cinq entre 540 et 1320 px et n'est pas jugee (`ADR-0014`, la provenance decide du
perimetre). **Consequence directe et mesuree : la variante recommandee par `ines-design` ne peut pas
etre implementee tant que ce seuil n'a pas ete revu.**

**L'argument technique que je fournis, et qui n'est pas une decision** : les trois proprietes que
cette clause borne d'une seule inegalite ne portent pas le meme risque. `width: N` et `min-width: N`
en pixels **empechent** un element de retrecir sous la cible, donc debordent ; `max-width: N` est un
plafond, et un element sous `max-width: 720px` vaut au plus la largeur disponible - a 375 px de
viewport, il vaut 375 px. Un `max-width` au-dela de la cible **ne peut pas** produire le debordement
que le message de la clause invoque. C'est un fait sur le modele de boite, pas une preference ; ce
qu'on en fait - relever le seuil, le scoper a `width` et `min-width`, ou le garder tel quel et
refuser la variante - est une decision de reference produit, donc la sienne. Je ne l'ai pas prise et
je ne l'ai pas prise par omission : la clause reste telle qu'elle est, et elle est desormais visible.

**A `nadia-prof`** : rien de nouveau. Cet `ADR` ne juge aucun rendu, ne leve aucun refus, et ne
touche a aucune de ses mesures.

## Regles executables

**Regle 1** -
`adapters/adapter-web/src/test/java/com/recreo/adapter/web/ViewportEtLargeurTelephoneTest.java`

Le balayage de largeur des feuilles maison passe de `largeursPxDuCss` (decoupage sur accolades,
supprime) a `largeursPxDesFeuillesMaison`, qui itere sur `reglesMaison(racine)` - le lecteur
caractere par caractere a pile d'accolades, qui rend `(fichier, contexte de regle-@, selecteur,
corps)`. L'assertion de derivation totale suit le meme deplacement : *"aucune regle maison sous
`<racine>/static`"* est un echec, comme *"aucune feuille maison"* l'etait.

**Mise a l'epreuve** : un cas fautif commite de plus,
`src/test/resources/contreexemple/ViewportEtLargeurTelephoneTest/largeur-excessive-sous-media/`.
Il porte **le meme selecteur fictif et la meme valeur** que `largeur-excessive`, qui existait deja
et etait deja rouge - `.conteneur-fictif { width: 600px }` - **a la seule difference du nichage sous
une requete de media**. Les deux cas ne se distinguent que par la variable testee, ce qui est la
seule facon de prouver que c'est le nichage qui est ferme et rien d'autre. Avant ce lot, ce cas-la
etait vert.

**Regle 2** -
`adapters/adapter-web/src/test/java/com/recreo/adapter/web/LaLargeurOffreeAUneRangeeEstUneFonctionEpingleeTest.java`
et
`adapters/adapter-web/src/test/java/com/recreo/adapter/web/LaLargeurDemandeeParUneRangeeEstEpingleeTest.java`

Dans les deux `muter`, l'assertion d'existence devient une assertion d'unicite, qui nomme le nombre
trouve, le fichier et l'ancrage. Comptage par `indexOf` et non par expression reguliere :
`AssertionsWebTest` l'interdit dans ce module (`ADR-0008`), et un ancrage de mutation contient des
accolades et des points, qui ne sont pas neutres pour un moteur d'expressions.

**Mise a l'epreuve, sur materiau reel et non sur une fixture** : dans chaque fichier, une clause
appelle `muter` avec un ancrage qui apparait plusieurs fois dans la feuille maison **servie**
(`font-size:`) et exige le rouge. Rien n'est fabrique pour l'occasion : le fait dont ce test depend
est une propriete de la feuille de production.

**Deux copies de `muter`, deux copies d'`occurrences`, et pas d'extraction.** Les deux fichiers ont
deja leur `muter` prive depuis `ADR-0055`, et la duplication y avait ete assumee. Trois lignes
similaires valent mieux qu'une abstraction prematuree : ce qui justifierait l'extraction est un
troisieme appelant, pas un second. **Ce qui me ferait changer d'avis** : le jour ou un troisieme
fichier de ce module mute du materiau reel, les trois copies partent dans un seul endroit, et ce
sera le meme geste que l'extraction de `rangeesRepliables` en `ADR-0055` - le partage d'une
mecanique entre trois clauses, pas l'anticipation d'un besoin.

**Mesure de ce lot** (JDK 21.0.10 par la toolchain declaree). `bash outils/mvn-jdk21.sh -B clean
verify` sur la racine du worktree : **BUILD SUCCESS**, six modules, **615 tests, 0 echec**.
**Methode de ce chiffre** : somme des six totaux par module que Surefire et Failsafe impriment sur la
racine apres `clean` (48 + 13 + **315** + 144 + 89 + 6), tests d'integration compris - meme methode
et meme perimetre qu'`ADR-0054` (604) et `ADR-0055` (612). **L'ecart de 3 avec 612 est entierement
les trois clauses ajoutees par ce lot**, toutes dans `adapter-web`, et c'est le seul module dont le
total bouge. Le comptage par rapports texte de classe rend un chiffre plus bas, pour la cause deja
etablie en `ADR-0054` (quatre classes executees par deux moteurs JUnit, un rapport texte par
classe) ; le chiffre a citer est celui du reacteur.

## La chaine au pixel sous la declaration proposee, puisqu'`ines-design` la demande

**Confirmee, et 680 px est exact.** La chaine d'`ADR-0054` est
`main#jeu.container.zone-jeu` -> `div.ecran-de-jeu` -> `div.cabinet` -> `form.propositions`. Sous
`box-sizing: border-box`, le rembourrage lateral de `.container` (2 x 12 px) est pris **dans** le
plafond, `.ecran-de-jeu` ne declare aucun terme lateral, et la bordure de `.cabinet` en retire
2 x 8 px : **720 - 24 - 16 = 680**, par la meme arithmetique qui rend 540 - 24 - 16 = 500 et que la
mesure au navigateur de `nadia-prof` a validee au pixel le 2026-08-22. `.propositions` ne declare ni
marge laterale ni rembourrage (`padding: 0`, et `margin: 0` dans le cabinet), donc rien ne s'ajoute
apres le cabinet.

**Ce que la chaine devient exactement** : le plafond passe de 540 a 720 px sur la bande 576-767 px,
et **rien d'autre ne bouge**. La marche des 768 px et les trois au-dela sont inchangees ; a 744 px de
viewport, la rangee offre 680 px la ou elle offrait 500. La declaration de la feuille tierce n'est ni
retiree ni modifiee : elle reste dans la table, et c'est la maison qui l'emporte parce qu'elle est
chargee apres, a specificite egale - fait garde par
`RessourcesTiercesTest.verifierFeuilleMaisonChargeeEnDernier`, pas par un commentaire.

**Le mot juste sur ce que "confirme" veut dire ici, et ce qu'il ne veut pas dire.** 680 est le
resultat d'un calcul de ma main, sur des declarations litterales, par une arithmetique validee contre
une mesure faite dans une **autre** configuration. Ce n'est pas une mesure. Et il y a une
consequence que `nadia-prof` seule peut fermer : apres ce correctif, **aucune ligne de la table
d'`ADR-0054` ne porterait plus de consequence etablie par un navigateur sur le palier de reference**.
La ligne des 540 px y resterait - elle est declaree - mais sa consequence mesuree, 500 px de rangee,
serait perimee, puisqu'une declaration maison l'emporterait sur elle a 744 px. Le commentaire de la
table le dit aujourd'hui et deviendrait faux ; il est a corriger dans le meme commit que le
correctif, et la remesure est due.

**La table gagne exactement une ligne**, relevee sur la sortie du test et non redigee a la main :

```
"form.propositions <- main#jeu.container.zone-jeu | recreo.css | @media (min-width: 576px) and (max-width: 767.98px) | .zone-jeu | max-width: 720px",
```

Elle se place apres la ligne
`form.propositions <- main#jeu.container.zone-jeu | recreo.css | - | * | box-sizing: border-box`, ce
qui porte la table de 25 a 26 lignes. Une seule rangee est concernee : l'en-tete ne porte pas
`zone-jeu`. **La chaine de caracteres du contexte est le litteral de la feuille**, espaces compris -
la table n'y resout rien, donc la requete de media doit etre recopiee telle qu'elle est ecrite. La
borne haute `767.98px` n'est pas inventee : c'est le litteral que la bibliotheque elle-meme emploie
pour le haut de cette bande, releve sur la feuille servie. Sans elle, la declaration s'appliquerait
aussi a 992 px et au-dela, ou elle **abaisserait** le plafond de 960 a 720.

**`ADR-0055` n'est pas touche** : ses 34 lignes sont indexees par (rangee, ecart) et (rangee,
colonne, regle), et `.zone-jeu` n'est ni une rangee ni une colonne. Mesure plutot que deduit :
9 tests, 0 echec sur ce fichier avec la declaration appliquee. **Aucun de ses deux membres n'a
besoin d'etre amende.**

## L'ecart en-tete / zone de jeu : evitable par mecanisme, et pas gratuitement

`ines-design` signale qu'entre 576 et 767 px la zone de jeu deviendrait visiblement plus large que
la barre d'en-tete au-dessus d'elle, et route le rendu a `nadia-prof`. **Je ne juge pas s'il est
acceptable ; je reponds a la seule question de mecanisme.**

**Il est evitable, et le mecanisme est d'une ligne** : le meme bloc de media prend un selecteur de
plus, `.entete-contenu, .zone-jeu`. Le lecteur de ce module decoupe les selecteurs sur la virgule,
les deux elements sont sur des ancetres differents et la cascade se resout comme pour la zone de jeu
seule. Ce n'est donc pas une consequence inevitable du scope : le scope a la zone de jeu **produit**
l'ecart, il ne l'impose pas.

**Ce que ce mecanisme coute, et pourquoi ce n'est pas un choix neutre.** `.entete-contenu` declare
`flex-wrap: wrap` : c'est une **rangee repliable** au sens d'`ADR-0054` et d'`ADR-0055`, et les deux
tables la portent deja. Elargir son plafond change donc la largeur offerte a une **seconde** rangee,
dont le repli n'a ete mesure par personne - les deux `ADR` le disent explicitement de ses lignes.
La table d'`ADR-0054` passerait de 25 a 27 lignes au lieu de 26, et le rang de l'en-tete entrerait
dans le perimetre de la remesure due a `nadia-prof`. Un troisieme mecanisme - redefinir `.container`
lui-meme sous ce palier dans la feuille maison - obtient le meme alignement en elargissant **tout**
element porteur de cette classe, donc en touchant la classe gouvernante que la derivation d'`ADR-0054`
lit sur la feuille tierce : plus large en portee, sans rien resoudre de plus.

**Le choix entre les deux n'est pas mecanique** : "un ecart visible entre deux blocs" contre "une
seconde rangee dont la largeur offerte change sans mesure". Les deux sont des questions de rendu et de
coherence d'ensemble, donc `nadia-prof` et, si elle le juge genant, `camille-po`. Je fournis les deux
mecanismes et leur cout en table ; je n'en retiens aucun.

## Consequences

- **Un faux vert de plus d'un an de portee potentielle est ferme.** Le seuil de largeur de la
  feuille maison ne s'evite plus en indentant la declaration d'un cran.
- **Une decision de reference produit devient exigible avant un correctif de forme**, alors qu'elle
  ne l'etait pas hier. C'est le but : le correctif de `SYN-11` ne peut plus passer par un trou.
- **Mauvaise consequence, assumee** : la clause de largeur devient plus severe qu'elle ne l'a jamais
  ete, sur un seuil dont la premisse est perimee. Elle bloque donc un correctif que personne n'a
  encore juge, et le debloquer demande une decision qui n'est pas de mon ressort. Je le prefere a un
  seuil qui ne s'applique qu'a ceux qui ne savent pas comment l'eviter.
- **Seconde mauvaise consequence** : deux morsures d'`ADR-0054` rougiront le jour du correctif pour
  une raison qui n'est pas la propriete qu'elles gardent. C'est le prix de l'unicite d'ancrage, et il
  est visible et nomme, au lieu d'etre une derive silencieuse.
- **Deux garde-fous de plus a la charge du meta-garde-fou**, sans exemption ni plafond releve : le
  cas fautif nichee est commite sous le repertoire que `LesGardeFousSontMisALEpreuveTest` exige, et
  les deux clauses d'unicite mordent sur du materiau reel.
- **Une duplication de plus assumee** (`occurrences`, deux fois), avec le declencheur ecrit de son
  extraction.

## Alternatives considerees

**Ne rien ecrire et nommer la dette avec son declencheur.** Ecartee sur le meme motif qu'`ADR-0055`,
et il est plus fort ici : **le declencheur est realise ce sprint, par le correctif en cours
d'arbitrage**. Un garde-fou qui arrive apres le lot qu'il devait rendre visible n'a jamais garde ce
lot.

**Relever le seuil de 375 px dans le meme geste, pour que la variante passe.** Ecartee, et c'est le
vrai arbitrage de cet `ADR` : la largeur de reference est une decision de `camille-po`
(`ADR-0054` le dit deja de sa propre table), et choisir le nombre qui laisse passer la variante que
je suis en train d'instruire serait decider la forme a la place de ceux qui l'arbitrent. Je fournis
l'argument sur `max-width`, pas la valeur.

**Scoper le seuil a `width` et `min-width` en laissant `max-width` non borne.** Techniquement
defendable - c'est l'argument que je route - et ecartee ici pour deux raisons independantes. D'abord
elle **relache** un garde-fou dans le lot qui instruit la variante qu'elle debloque : le geste et son
beneficiaire seraient les memes, ce qui est exactement ce que je reproche a une regle ecrite apres
coup. Ensuite un `max-width` maison hors de la chaine d'une rangee n'est epingle par rien : le
relacher ouvrirait un trou la ou `ADR-0054` n'a de table que pour la chaine. Si `camille-po` retient
cette lecture, elle vient avec la question de ce qui epingle les autres, et c'est un lot, pas une
ligne.

**Faire echouer la clause sur toute largeur en pixels qu'elle ne sait pas attribuer a un selecteur**,
plutot que de corriger le lecteur. Ecartee : c'est une piece mobile de plus pour une propriete que le
lecteur existant rend correctement, et cela aurait laisse deux lectures de feuilles se contredire
dans le meme module.

**Extraire `muter` et `occurrences` dans une classe utilitaire de test.** Ecartee : deux appelants,
tous deux ecrits cette semaine, aucun troisieme en vue. Le declencheur de l'extraction est ecrit
plus haut.

**Reancrer les deux morsures des maintenant sur un ancrage qui resterait unique apres le
correctif.** Ecartee : cela demanderait de deviner la forme exacte de la declaration que
`nadia-prof` n'a pas encore arbitree, donc d'ecrire un ancrage contre un arbre imaginaire - la faute
meme que cet `ADR` corrige, commise dans l'autre sens.
