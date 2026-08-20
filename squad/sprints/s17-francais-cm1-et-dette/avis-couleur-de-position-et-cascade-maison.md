# Avis de mecanisme - la regle qui aurait attrape le defaut de `S15-8`

- **Auteur** : theo-archi. **Date** : 2026-08-19.
- **Branche** : `s17-theo-position`, worktree dedie, forkee de **`round-two` a `246d149`** - SHA
  releve par `git rev-parse round-two`, pas repris d'un message ; lignee verifiee par
  `outils/verifier-lignee.sh`. **`s17-marc` (`f66504c`) y est fusionne** avant finalisation, selon le
  patron qui evite a `round-two` de passer par un etat rouge.
- **Repond a** : `nadia-prof`, qui a mesure sur l'increment livre que le correctif `S17-D` avait
  defait `S15-8`, et a la demande de la regle **generale** qui l'aurait attrape.
- **Ce que je ne fais pas** : je ne juge aucune pedagogie, je n'ecris pas le test de `marc-dev`
  (celui qui prouve que **son** correctif tient), et je n'ouvre pas `S17-C`.

## Verdict en une page

| Question | Verdict |
|---|---|
| 1. La regle generale | **"Une declaration dont le sujet designe une position survit a tout etat interactif"**, formulee sur **l'issue de la cascade** et non sur une comparaison de specificite. Pas plus large : un etat qui recouvre le repos est la definition d'un etat |
| 1 bis. Meme famille que l'amendement 3 d'`ADR-0046` ? | **Meme invariant, autre population.** La-bas maison contre **tiers**, ici maison contre **maison**. Donc un garde-fou distinct, pas une extension |
| 2. Ferme-t-elle la dette d'ancetre que j'ai nommee la veille ? | **Non.** Le declencheur est tombe, mais ce n'est pas cette dette qui s'est realisee - deux trous voisins nes du meme geste, dans deux directions opposees. La dette reste ouverte, et elle est desormais partagee par deux garde-fous |
| 3. Ce que la regle ne verra pas | **Aucun etat rendu.** Elle lit des declarations. `nadia-prof` reste seule garde de l'effet, et c'est exactement pourquoi un correctif vert a pu defaire `S15-8` |

## 0. Ce que je nomme avant tout le reste : ma part

Mon amendement 3 d'`ADR-0046`, ecrit la veille, a verifie que `S17-D` **tient au sens de la
specificite** : 57 exigences comparees, toutes satisfaites, morsure prouvee sur la feuille remise dans
sa forme d'avant le correctif. **Ce vert n'etait pas faux ; il etait etroit.** Il compare la feuille
maison a la feuille **tierce**, et le defaut du jour vit entre **deux regles maison**. J'ai ferme une
porte et laisse la fenetre voisine ouverte, le meme jour, avec la meme phrase - *declarer ne suffit
pas, il faut l'emporter* - appliquee a une seule des deux populations.

## 1. La regle generale, et pourquoi pas plus large

### Ce que j'ai verifie a la source

Lecture de la feuille servie sur `246d149`, et **execution de la clause neuve sur cet arbre**, qui
reproduit le defaut a partir des seules declarations, sans navigateur :

- `#jeu .proposition:active` vaut **(1,2,0)** ; les quatre regles `.propositions
  .proposition:nth-child(4n+k)` valent **(0,3,0)**. Un identifiant domine la colonne des classes quel
  que soit le compte de celles-ci ;
- **8 motifs** releves : les quatre regles de position x `background-color` et `color`.

*(Precision de comptage, dite plutot que tue : `nadia-prof` lit (0,3,0) -> (1,3,0) la ou je lis
(0,2,0) -> (1,2,0). Nous comptons `:active` dans la meme colonne ; l'ecart d'une unite vient du
selecteur exact releve. **La conclusion ne depend pas de ce chiffre** - des qu'un identifiant entre,
la colonne des classes ne decide plus - et je ne "corrige" pas sa mesure, je note ou nos deux lectures
divergent.)*

### Le fait qui commande la forme de la regle

`nadia-prof` ecrit : *"la protection tenait par une egalite de specificite ecrite en commentaire et
executee par personne."* C'est exact, et il y a un cran de plus a lire dans le fichier : **la
protection existait sous deux formes differentes**, et une seule etait une egalite.

| Pressable | Regle d'etat | Regle de position | Ce qui protegeait |
|---|---|---|---|
| `.proposition` | `.proposition:active` **(0,2,0)** | `.propositions .proposition:nth-child(4n+1)` **(0,3,0)** | une **marge** de specificite |
| `.proposition.deja-essayee` | `.proposition.deja-essayee:active` **(0,3,0)** | la meme, **(0,3,0)** | une **egalite**, plus l'ordre du fichier |

**Celle qui a cede est la marge, pas l'egalite.** C'est decisif pour le choix de la regle : un
registre des egalites de specificite - la forme d'`ADR-0051`, qui etait ma premiere idee - aurait
declare la protection de `.deja-essayee` et **laisse passer celle qui a reellement cede**. La regle
doit donc porter sur **l'issue de la cascade** (la plus specifique gagne ; a specificite egale, la
derniere ecrite), qui couvre les deux formes d'un seul enonce.

### La regle, et ce qu'elle refuse d'etre

> **Une regle maison dont le sujet designe une position ne peut pas etre recouverte, sur une propriete
> qu'elle declare, par une regle maison a etat interactif qui s'applique au meme element.**

**Elle n'est pas "aucun etat ne recouvre une declaration de repos"** : un etat qui recouvre le repos
est **la definition** d'un etat, et `.demarrer:hover` doit continuer de peindre par-dessus
`.demarrer`. Ce qui est protege est ce dont la valeur **depend de la position** : la position n'est
pas une apparence que l'interaction a le droit de renegocier, c'est un **reperage**.

**Le perimetre protege se derive** : une regle est "de position" quand **son sujet** porte une
pseudo-classe positionnelle (`:nth-child()`, `:first-child`, ...). Aucun selecteur n'est ecrit dans le
test. Le sujet, et non le selecteur entier : dans `.propositions:has(.proposition:nth-child(4))
.proposition`, la position est une condition portee par un **autre** element, et ce que la regle
habille ne suit pas la position - cette regle-la n'est donc pas protegee, a juste titre.

### Meme famille que l'amendement 3 d'`ADR-0046` ? Meme invariant, autre population

**Oui pour l'invariant** : *une declaration ne vaut que si elle l'emporte dans la cascade*. **Non pour
la population** : `ADR-0046` compare la feuille maison a la feuille **tierce** ; ici les deux regles
sont **maison**. La clause vit donc dans son propre garde-fou, et ce n'est pas une preference de
rangement : le nom, la Javadoc et le **cliquet de dette** d'`ADR-0046` parlent de la bibliotheque
vendorisee, et sa dette compte des exigences nees d'elle. Y loger une clause maison contre maison la
rendrait invisible a la relecture et fausserait le sens de ce cliquet - la faute qu'`ADR-0015` a deja
sanctionnee (un garde-fou dont le nom promet autre chose que ce qu'il garde).

## 2. La dette d'ancetre : le declencheur est tombe, la dette n'est pas payee

J'ai ecrit la veille, dans l'amendement 3 d'`ADR-0046` : *"le prefixe d'ancetre d'une regle maison
n'est pas verifie non plus"*, declencheur *"le premier lot qui ajoute un ancetre a une regle d'etat
dont le pressable peut etre rendu hors de cet ancetre"*. Le lot qui a produit le defaut du jour est
bien celui qui ajoute un ancetre a une regle d'etat. **Et pourtant ce n'est pas cette dette qui s'est
realisee.** Les confondre ferait croire qu'elle est payee.

- **La dette** porte sur un ancetre qui **ne s'applique pas** : la clause d'`ADR-0046` crediterait
  alors une specificite que l'enfant n'obtient jamais - un **faux vert**. Verifie le jour meme :
  `.demarrer` vit bien sous `<main id="jeu">`, l'ancetre s'applique, **la dette ne s'est pas
  realisee**.
- **Le defaut du jour** porte sur un ancetre qui **s'applique parfaitement** et fait gagner la regle
  d'etat **trop haut**, contre une autre regle maison qu'aucune clause n'examinait.

**Meme geste, deux trous voisins, deux directions opposees.** `ADR-0053` ferme le second. Le premier
reste ouvert, declencheur inchange, **et il est desormais partage par deux garde-fous** : la clause
neuve a le meme angle mort et le paie dans l'autre sens - une regle d'etat portee par un ancetre que
le pressable n'a pas serait comptee comme recouvrante alors qu'elle ne s'applique pas, donc un **faux
rouge**, visible. Le cout de cette dette augmente ; sa nature ne change pas.

## 3. Ce que cette regle ne verra pas

- **Aucun etat rendu.** Elle lit des **declarations**, jamais une couleur sous le doigt. Aucun
  garde-fou de ce depot ne mesure un etat rendu (`ADR-0025`, `ADR-0028`) : c'est exactement ce que
  `nadia-prof` ecrit, et c'est pour cela qu'un correctif vert de bout en bout a pu defaire `S15-8`
  sans que rien ne le dise. **Elle reste seule garde de l'effet** ; ce qui est garde ici est la
  **cause** declarative. Je le repete pour la troisieme fois de ce sprint parce que c'est la seule
  facon d'empecher qu'un vert soit lu comme une couverture.
- **Le prefixe d'ancetre**, des deux cotes - voir la section 2.
- **Les regles nichees sous une regle-`@`** : une regle de position ou d'etat qui n'existerait que
  sous une `@media` serait comparee a des regles qui ne s'appliquent pas dans la meme condition.
  Aucune n'existe aujourd'hui sur ces sujets ; le jour ou l'une apparait, la clause peut rougir a
  tort, et l'`ADR` le dit.
- **Le sens de la couleur** : qu'elle contraste, qu'elle ne porte pas un second indice, qu'elle soit
  la bonne teinte - `nadia-prof` et `ines-design`, pas moi.
- **Ce que la regle ne verra pas non plus, et que personne ne verra** : une propriete rendue dont la
  valeur depend d'une relation de cascade **entre une regle maison et une regle maison sur autre chose
  qu'une position**. Cette famille-la existe (l'ordre du fichier tranche des egalites ailleurs) et
  reste gardee par la relecture seule. Je ne l'encode pas aujourd'hui : le perimetre protege ne serait
  plus derivable, il faudrait un marqueur explicite dans la feuille et une decision de `nadia-prof`
  sur ce qui merite d'etre protege. **Je le nomme pour que ce silence-la soit choisi et non subi.**

## 4. La clause sur la forme de `marc-dev`, et contre quelle forme la morsure est prouvee

**Le correctif du proprietaire est fusionne dans ce lot** (`git merge s17-marc`, commit `f66504c`,
avance rapide). Sa forme est l'**inverse** de celle que j'avais prise a titre provisoire pendant que
son lot n'existait pas : il **abaisse le sujet presse** - `#jeu .proposition:active` devient
`.propositions .proposition:active`, soit **(0,3,0)**, exactement egal aux quatre regles cycliques,
l'ordre du fichier redevenant decisif - la ou j'avais **releve les regles de position** a (1,3,0).
**Ma correction provisoire est retiree** : le depot n'a pas besoin de deux corrections du meme
defaut, et la sienne evite l'identifiant que la mienne repandait sur quatre regles de plus.

**Ma clause est verte sur sa forme.** C'est le test de ce que j'ecrivais en la posant : elle exige
que la regle de position **l'emporte**, pas qu'elle soit ecrite d'une facon. Elle est verte sur les
deux formes - egalite tranchee par l'ordre (la sienne) comme marge de specificite (la mienne) - et
elle est rouge sur les deux facons de la perdre. **Si elle avait rougi sur sa forme, c'est ma clause
qui aurait ete trop etroite** ; ce n'est pas le cas, et le fait est mesure et non affirme : les quatre
methodes de la classe passent sur l'arbre fusionne, et son propre garde-fou de lot
(`LaCouleurDeLaPositionCycliqueSurvitSousLAppuiTest`, 2 tests) ainsi que celui de `S17-D`
(`UnPressableAppuyeConserveSonContrasteEtSonAnneauDeFocusTest`, 4 tests) passent aussi.

**La morsure reste prouvee contre le defaut reel**, pas contre un cas construit : la feuille servie
est copiee puis le sujet presse est rendu a son **ancetre identifiant**, donc (1,2,0) contre (0,3,0)
pour les quatre regles cycliques - l'etat exact que `nadia-prof` a mesure sous appui sur l'increment
livre. Si cette forme corrigee changeait, la mutation echouerait **avec son message** - *"la mutation
doit porter sur la forme corrigee que la feuille servie porte vraiment"* - plutot que de devenir
silencieusement vide : un rouge exact, qui demande de reecrire la mutation, jamais un vert qui ne
prouverait plus rien.

**Une seconde morsure, sur un objet neuf, pour le chemin que la premiere ne couvre pas.** La mutation
de la feuille reelle prouve le chemin de la **specificite** ; elle ne prouve pas celui de l'**ordre**.
Un jeu fautif neuf, jamais livre, porte donc deux regles a la **meme** specificite (0,3,0) avec la
regle d'etat ecrite **apres** - aucun identifiant, aucune marge, le defaut tenant au seul ordre de
redaction, c'est-a-dire exactement ce que le commentaire de la feuille demandait de ne jamais faire.
Et son **corrige** doit rester vert : sans ce cas-la, la clause serait une interdiction de declarer un
etat sur un pressable colore par sa position, alors qu'elle interdit seulement qu'il la recouvre.

## 5. Ce que ce lot mesure, avec sa methode

- **Etat du defaut avant correctif, mesure par la clause elle-meme sur `246d149`** : **8 motifs**,
  quatre regles de position x deux proprietes (`background-color` et `color`),
  `#jeu .proposition:active` a (1,2,0) contre (0,3,0). La clause reproduit le defaut de
  `nadia-prof` **a partir des seules declarations**, sans navigateur.
- **Base de ce lot** : `f66504c` (`[MARC] s17: repare S15-8 sans reperdre S17-D sur
  .proposition:active`), lui-meme au-dessus de `246d149`. Fusion par avance rapide dans
  `s17-theo-position`, lignee verifiee.
- **Porte technique** : `bash outils/mvn-jdk21.sh -B clean verify` a la racine du worktree,
  **BUILD SUCCESS**, reacteur complet (6 modules), JDK 21.0.10 (toolchain declaree). **589 tests** :
  48 (`domain`) + 13 (`application`) + 294 (`adapter-web`) + 141 (`adapter-content`) + 87
  (`bootstrap`, surefire) + 6 (`bootstrap`, failsafe). Comptes lus sur les six lignes de synthese de
  module du journal, jamais sur une somme de lignes par classe.
- **Delta mesure contre ma base, pas deduit** : `adapter-web` passe de **290** a **294** tests, les
  cinq autres modules etant inchanges - soit **585 -> 589, +4**, exactement les quatre methodes de la
  classe neuve (la clause sur la feuille servie, la morsure sur la feuille reelle mutee, la morsure du
  jeu fautif d'ordre, son corrige vert). Le 290 vient d'une execution reelle sur ma base, mes
  modifications mises de cote (`git stash -u`), pas d'une soustraction.
- **Mesures partielles** : toutes portent `-am` et nomment leur module.
- **Le compte des regles ArchUnit ne bouge pas** : **29**. La clause neuve n'en est pas une - l'objet
  juge est une feuille de style.
- **Surface de visibilite elargie** (`private` -> paquet) sur quatre methodes et un record deja
  existants, pour que la clause neuve lise **exactement** les memes regles, le meme ordre de fichier
  et les memes etats interactifs que les clauses voisines. Motif ecrit dans les Javadoc concernees :
  deux lectures libres de diverger finiraient par juger deux feuilles differentes, et ce depot a deja
  paye cette faute.
