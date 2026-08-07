# ADR-0017 - Comparer deux boutons d'un meme ecran, et dire ce que cette comparaison ne voit pas

- **Statut** : Accepte
- **Sprint** : s05
- **Date** : 2026-08-06
- **Auteur** : `theo-archi`
- **Regle executable** :
  `adapters/adapter-web/src/test/java/com/recreo/adapter/web/LActionNeDeclarePasMoinsQueLaSortieTest.java`

> **Amendement du 2026-08-07 (porte architecture de s05, apres la fusion de S6-1) - la limite 5
> etait la bonne, et elle s'est ouverte le sprint suivant.** J'avais ecrit *"un ecran est un fichier
> de gabarit"* et *"aucun couple action/sortie ne traverse aujourd'hui cette frontiere, mais rien ne
> l'empeche demain"*. Demain, c'etait S6-1 : l'en-tete permanent pose un lien `data-role="sortie"`
> dans `accueil.html`, hors de la zone que htmx echange, donc affiche par-dessus les boutons
> d'action de `fragments/manche.html` et de `fragments/retour.html`. `ines-design` a rendu ce lien
> metriquement inferieur a tout bouton d'action et l'a ecrit dans sa feuille, mais **rien ne gardait
> cette propriete** : sa regle, la mienne, restait verte quoi qu'il arrive a `.lien-accueil`.
> C'etait le quatrieme defaut de forme visible par un enfant que la CI aurait laisse passer. La
> limite est fermee ci-dessous ; le detail est dans la section "Ce que cette regle ne couvre pas",
> point 5.

## Contexte

Trois fois de suite, un defaut de hierarchie visible par un enfant a traverse une CI entierement
verte. La cause est la meme les trois fois, et c'est `nadia-prof` qui l'a nommee a la levee de son
dernier refus : **aucun garde-fou de ce depot ne compare deux selecteurs entre eux sur un meme
ecran.** Les regles verifient qu'une declaration est presente ou qu'une valeur depasse un plancher ;
aucune ne verifie qu'un bouton est plus gros qu'un autre.

Les trois occurrences :

1. **s02, refus de S2-1.** Le bouton de sortie passe a 115 x 47 px et devient la seule zone coloree
   pendant que le bouton de reponse reste a 46 x 41 px, gris. Ce qui appelle la main est ce qui
   quitte le jeu.
2. **s02, correction acceptee.** La sortie fait 0,13 fois la surface d'une reponse. `nadia-prof`
   accepte.
3. **Lot Bootstrap (`6c83c97`).** Le rembourrage maison est retire du bloc des boutons pleins au
   profit de celui de `.btn`, mais pas de `.retour-accueil` qui n'etait pas dans ce bloc. Le bouton
   qui quitte redevient 30 % plus grand que celui qui continue ; le rapport action/sortie tombe de
   1,15 a 0,77. **Tous les tests etaient verts.**

Trois comparaisons entre selecteurs existaient deja dans `TailleDesTextesEtBoutonsTest`. Ce qui
manquait n'etait donc pas la comparaison en soi : c'est qu'aucune ne portait sur des **boutons**,
qu'aucune ne comparait la **boite** plutot que la police, et surtout que **leurs paires etaient
ecrites a la main**. Meme faute de perimetre qu'`ADR-0013`, sur une population de paires.

Mon avis de faisabilite de s05 concluait que c'est couvrable **sans navigateur sans tete**, parce
que la regression de `6c83c97` etait entierement declarative. Je n'avais pas ecrit la regle dans ce
commit-la, pour une raison qui tient toujours et qui gouverne tout ce qui suit : **une regle de
saillance approximative rendrait la quatrieme occurrence plus probable**, en donnant l'illusion
d'une couverture la ou `nadia-prof` relacherait sa vigilance. Mieux vaut ne rien ecrire qu'ecrire un
garde-fou decoratif.

## Decision

**Sur un meme ecran, un bouton de sortie ne declare aucune composante de boite superieure a celle
d'un bouton d'action du meme ecran.**

1. **Chaque bouton porte son role dans le balisage**, par l'attribut inerte
   `data-role="action"` ou `data-role="sortie"`. Aucune feuille et aucun script ne s'en sert : il ne
   dit que ce que le bouton fait du point de vue de l'enfant - avancer dans le jeu, ou en sortir. Un
   bouton qui ne le porte pas, un role inconnu, un role calcule par une expression sont des
   **echecs**, jamais des membres ignores (`ADR-0013`, obligation 2).
2. **Le perimetre derive du repertoire de gabarits et du balisage.** Aucune liste de boutons, aucune
   table classe -> role ecrite dans le test. Un bouton neuf entre dans le controle sans que personne
   y pense - ou fait echouer la suite s'il n'a pas de role.
3. **Les composantes comparees sont `font-size`, `min-height`, et les deux axes du raccourci
   `padding`.** Chacune doit etre declaree par **exactement une** regle maison sur chaque bouton
   compare. Une composante qu'aucune regle maison ne declare est un echec : elle vient alors d'une
   bibliotheque tierce ou du defaut du navigateur, et ne garantit rien (`ADR-0014`, point 6).
4. **Deux etats ne sont confrontes que s'ils peuvent s'afficher ensemble.** L'appariement lit les
   conditions Thymeleaf (`th:if`, `th:unless`, la condition d'un `th:class` ternaire) sur le bouton
   et ses ancetres. Une condition que le garde-fou ne reconnait pas textuellement laisse les deux
   etats comparables : le doute produit une comparaison de plus, jamais une de moins.
5. **Le garde-fou prend deux racines**, celle des gabarits et celle des feuilles. Elles sont la meme
   en production. Les separer permet de rejouer un etat passe du CSS sous les gabarits d'aujourd'hui
   sans recopier un seul gabarit.

### Pourquoi `data-role` dans le balisage et non une table dans le test

Les deux formes sont totales : un bouton neuf produirait un rouge dans les deux cas. J'ai tranche
pour le balisage parce que la source de verite du role reste l'ecran, la ou le bouton est ecrit, et
que ce qui vit dans un test finit par etre lu comme une convention de test. C'est du balisage, donc
le mandat d'`ines-design`. Elle ne travaille pas sur ce lot : j'ai pose l'attribut au minimum - une
paire cle-valeur par bouton, six boutons, aucun changement de structure, aucun changement de classe
- et je le porte a sa connaissance par cet ADR. Si elle prefere un autre nom ou un autre porteur, le
changement est local a une constante du garde-fou et a six attributs.

### Pourquoi `border-width` n'est pas compare, alors que mon avis de faisabilite le proposait

Le rejeu de l'etat que `nadia-prof` a **accepte** en s02 l'a tranche, et c'est la seule raison qui
vaille : dans cet etat, la sortie porte un contour de 0,125rem que l'action n'a pas. Comparer
`border-width` rendrait rouge un etat qu'elle a valide en jouant. Le contour est un moyen de rester
visible **en etant moins saillant** ; ce n'est pas une composante de domination. `min-width` sort
pour une raison voisine : la sortie ne le declare pas, sa largeur venant de son libelle et de son
rembourrage, et l'exiger produirait un rouge sur un etat correct.

C'est le rejeu qui a corrige ma conception, pas l'inverse. Je le note parce que c'est la meme lecon
qu'`ADR-0015` : une fixture ecrite par l'auteur d'une regle ressemble a ce qu'il imagine.

## Regle executable

Fichier : `adapters/adapter-web/src/test/java/com/recreo/adapter/web/LActionNeDeclarePasMoinsQueLaSortieTest.java`

Ce n'est pas une `ArchRule` : la propriete porte sur des artefacts de rendu, pas sur des types Java.
C'est un test JUnit qui echoue dans `mvn verify` comme n'importe quel autre, et il est recense par
`LesGardeFousSontMisALEpreuveTest` au meme titre que les autres garde-fous de balayage.

Le nom de la classe est celui que mon avis de faisabilite avait annonce, et il est deliberement plus
etroit que "la hierarchie de saillance est respectee". Une saillance est un rapport de surfaces sur
un rendu ; ce qui est lu ici est declaratif. Le garde-fou promet exactement ce qu'il verifie :
l'action ne declare pas moins que la sortie.

Dix tests, dont deux ajoutes par l'amendement du 2026-08-07 :

| Test | Ce qu'il tient |
|---|---|
| `aucuneSortieNeDeclarePlusQuUneActionDuMemeEcran` | la propriete, sur la production |
| `surLEcranDeRetourLaSortieEstConfronteeAEncoreMaisPasDansSonEtatDeFinDePartie` | epinglage de l'appariement : le couple que `6c83c97` a inverse est bien confronte, la sortie de fin de partie ne l'est pas |
| `leLienDeLEnTeteEstConfronteAuxActionsDesEcransDeJeu` | epinglage de la frontiere fermee : le lien d'`accueil.html` est confronte a `.proposition` et a `.encore`, qui vivent dans `fragments/` |
| `uneSortieDHabillageQuiDepasseLActionDUnAutreFichierFaitEchouerLeGardeFou` | `ADR-0013` obligation 3 sur la frontiere : jeu fautif ou la comparaison au sein d'un fichier reste verte et ou seule la traversee rougit |
| `unEcranNeufOuLaSortieDepasseLActionFaitEchouerLeGardeFou` | `ADR-0013` obligation 3, objet neuf |
| `unBoutonSansRoleFaitEchouerLeGardeFou` | `ADR-0013` obligation 2 |
| `unRoleCalculeFaitEchouerLeGardeFou` | `ADR-0013` obligation 2 |
| `leRejeuDuLotBootstrapFaitEchouerLeGardeFou` | occurrence 3 |
| `leRejeuDuRefusDeSDeuxFaitEchouerLeGardeFou` | occurrence 1 |
| `leRejeuDeLEtatAccepteEnSDeuxResteVert` | occurrence 2 |

### La mise a l'epreuve sur les trois occurrences reelles

`ADR-0013` obligation 3 exige un objet neuf, et il y en a un. Mais un objet neuf ecrit par l'auteur
de la regle prouve que la regle attrape ce que son auteur imagine. Le vrai test est de rejouer les
etats qui sont **reellement passes**. Les trois jeux d'artefacts de rejeu contiennent la feuille du
commit concerne, octet pour octet, tiree de l'historique du depot. Les gabarits de `rejeu-s02-*`
sont ceux du meme commit, a l'attribut de role pres, qui est le seul ajout ; `rejeu-lot-bootstrap`
n'a pas de gabarits du tout, parce que ceux de `6c83c97` sont ceux d'aujourd'hui - `git diff`
entre ce commit et la production est vide sur `templates/`.

**Occurrence 3, lot Bootstrap (`6c83c97`)** - rouge, sur les cinq boutons d'action qui ont perdu
leur rembourrage maison, et sur eux seuls :

```
ecran templates/fragments/retour.html : .btn.encore "Encore !" ne recoit padding d'aucune regle
maison (selecteurs vus : [.btn, .encore]) ; sa boite depend alors d'une bibliotheque tierce ou du
defaut du navigateur, et sa saillance ne se compare a rien (ADR-0014)
```

`.btn.retour-accueil` n'est pas dans la liste des defauts : il avait garde son rembourrage. C'est
exactement la forme de la regression, et le message la nomme.

**Occurrence 1, refus de S2-1 en s02 (`3be5a86`)** - rouge sur les trois composantes comparees de
l'ecran de jeu :

```
ecran templates/fragments/manche.html : la sortie .retour-accueil "Les jeux" declare taille de
police a 1,25rem quand l'action .proposition "" se contente de 1,10rem
```

puis le meme constat sur `rembourrage haut et bas` (0,75rem contre 0,50rem) et sur `rembourrage
gauche et droite` (1,25rem contre 1,00rem). Trois composantes sur trois : c'est la mesure de
`nadia-prof`, 115 x 47 px contre 46 x 41 px, retrouvee dans les declarations.

**Occurrence 2, correction acceptee en s02 (`fd16057`)** - vert. Une regle de saillance qui
rougirait sur un etat que `nadia-prof` a valide en jouant serait affaiblie au premier sprint ou elle
generait, et la quatrieme occurrence passerait par la breche ainsi ouverte.

### Mesure de morsure sur le depot reel

Faite puis annulee : porter le rembourrage de `.retour-accueil` de `0.6rem 1.1rem` a
`0.9rem 1.6rem` dans `recreo.css` rend le test rouge en nommant les deux ecrans, les deux couples et
les deux axes, et **sans** nommer `.sortie-pleine` - la sortie de fin de partie n'ayant aucun bouton
d'action a concurrencer sur son ecran. C'est la forme exacte de ce que le garde-fou doit voir et de
ce qu'il doit laisser passer.

### Amendement du 2026-08-07 : la frontiere entre fichiers de gabarit

**Ce qui change dans le code.** Un ecran n'est plus un fichier. `etats()` lit d'abord la cible htmx
declaree par les gabarits (`hx-target`), puis, pour chaque bouton, decide s'il appartient a la zone
echangee ou a l'habillage qui l'entoure. Un bouton d'habillage produit un etat **par ecran du
depot** ; un bouton de la zone echangee produit un etat pour son seul fichier. La comparaison, elle,
ne bouge pas.

Deux traces sont distinguees dans les messages, parce qu'elles ne servent pas la meme personne : un
defaut de **lecture** nomme le fichier source - c'est la qu'on corrige - un defaut de **comparaison**
nomme l'ecran - c'est la que l'enfant voit les deux boutons ensemble.

**Ce que la regle interdit reellement.** Le jeu fautif
`contreexemple/LActionNeDeclarePasMoinsQueLaSortieTest/entete-permanente-plus-grosse/` est construit
pour qu'un garde-fou qui n'aurait pas ferme cette frontiere le laisse passer : la sortie de
l'habillage y est **plus petite** que l'action ecrite dans le meme fichier, et **plus grosse** que
celle du fragment. Verifie et pas suppose - en revenant a l'ancienne semantique, un ecran = un
fichier, ce jeu fautif redevient vert et le test qui l'exige rouge echoue :

```
LActionNeDeclarePasMoinsQueLaSortieTest.uneSortieDHabillageQuiDepasseLActionDUnAutreFichierFaitEchouerLeGardeFou
LActionNeDeclarePasMoinsQueLaSortieTest.leLienDeLEnTeteEstConfronteAuxActionsDesEcransDeJeu
Tests run: 10, Failures: 2
```

**Ce que ca coute.** La population de couples grandit avec le nombre de gabarits : chaque bouton
d'habillage est confronte a chaque bouton d'action du depot. Sur quatre gabarits c'est negligeable.
Sur quarante, un defaut de style d'habillage produira une liste de messages tres redondante. La
reponse ne sera pas de reduire les couples, mais de regrouper les messages.

Le lien de S6-1 passe cette comparaison sans rien changer a la feuille de `ines-design` : elle
l'avait deja aligne sur `.retour-accueil`. La difference est qu'auparavant cette propriete tenait a
son attention, et qu'elle tient maintenant a un test.

### Ce qui change dans `TailleDesTextesEtBoutonsTest`

`padding` entre dans `PROPRIETES_GARDEES` : une classe tierce en `!important` defairait un
rembourrage maison comme elle defait une taille de police, et c'est desormais une garantie
pedagogique. Quatre lecteurs de source deviennent visibles dans le paquet, pour que les deux
garde-fous lisent le CSS par le meme code plutot que par deux copies qui derivent - `ADR-0015` a
montre ce que coute une lecture subtile dupliquee (le piege des variables `--bs-*`).

## Ce que cette regle ne couvre pas

Cette section est la plus importante de l'ADR. `nadia-prof` doit savoir sur quoi elle reste le seul
garde-fou, sinon elle relachera sa vigilance sur ce que la CI pretend couvrir.

1. **La regle ne compare pas des surfaces.** La largeur rendue depend du nombre de glyphes du
   libelle : "Encore !" et "Les jeux" ne font pas la meme largeur a rembourrage egal. Un libelle
   rallonge sur un bouton de sortie l'agrandit sans qu'aucune declaration ne change, et la regle
   reste verte. C'est la limite principale, et elle est structurelle : la mesurer demanderait des
   metriques de police, donc un moteur de rendu.
2. **La regle ne voit pas la couleur.** L'occurrence 1 avait deux composantes : la sortie etait plus
   grosse **et** la seule zone coloree de l'ecran. La regle attrape la premiere, pas la seconde. Un
   ecran ou l'action et la sortie auraient exactement la meme boite mais ou seule la sortie serait
   en aplat passerait au vert. Comparer une saillance chromatique demande un calcul de luminance
   contre le fond, et je n'en ecris pas un a l'estime.
3. **La regle ne voit pas la position ni l'espace.** Un bouton de sortie place en tete d'ecran attire
   la main avant un bouton d'action de meme taille place plus bas. Rien ici ne lit l'ordre du
   document ni les marges.
4. **La regle ne voit pas ce qui se calcule.** Un rembourrage exprime en `calc()`, en `%`, en `em`
   ou par une variable CSS n'est pas compare - il produit un rouge explicite plutot qu'un vert, mais
   la comparaison n'a pas lieu. Idem pour un raccourci `padding` a trois ou quatre valeurs.
5. **~~Un ecran est un fichier de gabarit.~~ Ferme le 2026-08-07.** Un ecran est desormais la zone
   que htmx echange, plus tout ce qui l'entoure et lui survit. Le garde-fou lit `hx-target` dans les
   gabarits ; un bouton ecrit **hors** de cette zone, dans le fichier qui la contient, est confronte
   aux boutons de **tous** les ecrans, y compris ceux d'un autre fichier. La lecture reste derivee :
   la cible n'est pas ecrite dans le test, elle est lue dans les gabarits, et **plusieurs cibles
   distinctes sont un echec** plutot qu'un choix arbitraire - le garde-fou ne saurait plus dire ce
   qui survit a un changement d'ecran. Ce qui reste hors de portee : un habillage qui ne serait pas
   un frere de la zone echangee dans le meme fichier (une inclusion serveur de second niveau, par
   exemple). Ce cas n'existe pas dans le depot et je n'ecris pas de regle sur un cas jamais constate.
6. **Une meme regle maison qui declarerait deux fois la meme propriete est mal lue** : le garde-fou
   prend la premiere declaration, le navigateur applique la derniere.
7. **La regle ne dit rien de ce qui est juste.** Elle interdit l'inversion, pas la quasi-egalite :
   une action a 0,76rem de rembourrage contre une sortie a 0,75rem passe. Le rapport de 0,13 que
   `nadia-prof` a juge bon en s02 n'est pas encode, parce qu'un seuil de rapport serait un choix
   pedagogique, et ce n'est pas le mien a faire.

Les points 1, 2, 3 et 7 restent la charge de `nadia-prof`, en jouant. La CI ne les couvre pas et ne
pretend pas les couvrir.

## Consequences

**Bonnes.**

- La regression de `6c83c97` serait devenue rouge le jour meme, sur un message qui nomme le bouton,
  l'ecran et la propriete manquante, au lieu d'attendre un audit joue.
- Tout bouton neuf entre dans le controle sans que personne y pense, ou fait echouer la suite.
- Le rembourrage des boutons devient une garantie maison declaree, au meme titre que la taille de
  police et la cible tactile. Une mise a jour de la bibliotheque tierce ne peut plus le deplacer en
  silence.

**Mauvaises, et il faut les dire.**

- **Six gabarits portent un attribut qui n'existe que pour un test.** C'est un cout reel : du
  balisage qui ne sert pas au rendu. Je l'assume parce que l'alternative - une table dans le test -
  deplace la connaissance du role hors de l'ecran.
- **La regle est plus stricte que la realite sur un point** : elle exige que chaque composante soit
  declaree en maison sur **tous** les boutons, y compris ceux qui n'ont aucun vis-a-vis sur leur
  ecran (`.demarrer`, `.recommencer`). C'est deliberement conservateur - une boite completement
  declaree est comparable le jour ou un bouton de sortie apparait a cote - mais cela ferme la porte
  a une delegation legitime a Bootstrap sur ces boutons-la.
- **Une regle de plus a maintenir sur une population de paires.** Si l'appariement conditionnel se
  revele fragile a l'usage, la reponse n'est pas de le desactiver mais de le rendre plus
  conservateur - plus de couples confrontes, jamais moins.
- **Le vert de cette regle peut etre mal lu.** C'est le risque que la section "ce qu'elle ne couvre
  pas" existe pour contenir, et le seul moyen de le tenir est que cette section soit lue.

## Ce qui me ferait changer d'avis

- **Un rouge que `nadia-prof` juge faux.** Si la regle refuse un ecran qu'elle accepte en jouant, la
  conception est mauvaise, pas l'ecran. La reponse serait de retirer la composante fautive de la
  liste comparee, en le documentant ici comme `border-width` l'est aujourd'hui.
- **Une deuxieme occurrence de defaut sur la largeur du libelle.** Si un defaut reel passe par la
  limite 1, alors le rendu doit etre mesure, et l'arbitrage entre le cout d'un navigateur sans tete
  et celui d'une quatrieme occurrence change. Aujourd'hui aucune des trois occurrences ne serait
  passee par la.
- **Un troisieme garde-fou qui lirait le CSS maison.** Deux, aujourd'hui, se partagent quatre
  lecteurs. Un troisieme justifierait d'extraire ces lecteurs dans une classe de lecture dediee ;
  deux ne le justifient pas.

## Alternatives considerees

**Un navigateur sans tete (Playwright, Selenium).** Mesurerait les vraies surfaces et couvrirait les
limites 1, 2 et 3. Ecarte pour la meme raison qu'en s03 sur S3-8, et la raison n'a pas change : le
cout d'entree - un binaire de navigateur a installer en CI, un temps de suite qui change d'ordre de
grandeur, une classe entiere de tests instables - est disproportionne face a trois occurrences dont
**aucune** n'avait besoin d'un moteur de rendu pour etre vue. Si la limite 1 produit un defaut reel,
cet arbitrage est a refaire.

**Un rapport de surfaces estime declarativement** (largeur = rembourrage + nombre de caracteres x
une avance de glyphe supposee). Ecarte net. C'est exactement le garde-fou approximatif que je me
suis interdit d'ecrire : il donnerait un chiffre qui a l'air d'une surface sans en etre une, et la
premiere fois qu'il se tromperait, c'est la regle qu'on assouplirait, pas le facteur.

**Un seuil de rapport** ("la sortie fait au plus 0,5 fois l'action"). Ecarte pour deux raisons :
un rapport de composantes declarees n'est pas un rapport de surfaces, et surtout **le seuil est un
choix pedagogique**. Ce n'est pas a moi de le poser. La regle interdit l'inversion, ce qui est une
propriete d'architecture ; le bon rapport reste a `nadia-prof`.

**Deriver le role d'une table classe -> role dans le test.** Aussi totale que l'attribut, et sans
toucher au balisage d'`ines-design`. Ecartee : elle deplace la connaissance du role hors de l'ecran,
et une classe renommee y devient un rouge obscur ("classe inconnue") plutot qu'un rouge qui parle.

**Faire varier le role avec la branche Thymeleaf** (`data-role` calcule, `sortie` quand la partie
continue et `action` a la fin de partie, ou la sortie est la seule action possible). Defendable :
c'est ainsi que S3-6 decrit ce bouton. Ecartee parce qu'elle obligeait a apparier les branches d'une
expression de role avec celles d'une expression de classe, par index et par egalite textuelle de
conditions - de la machinerie fragile pour un resultat que l'appariement conditionnel donne
gratuitement. Le bouton quitte le jeu dans les deux cas ; ce qui change, c'est qu'a la fin de partie
il n'a plus de concurrent, et cela se lit dans les conditions.
