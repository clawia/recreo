# Avis de faisabilite - une seule sortie, celle de l'en-tete (story s11)

- **Auteur** : theo-archi
- **Date** : 2026-08-14
- **Sprint** : s11 (planning)
- **Demande instruite** : retirer `button.btn.retour-accueil` de `fragments/manche.html` et ne garder
  qu'une seule sortie, `a.btn.lien-accueil` de `accueil.html`, pour gagner de la place verticale.
  Garder "uniquement celle de l'en-tete" suppose de lever
  `body:has(.ecran-de-jeu) .lien-accueil { display: none }` : les deux gestes sont instruits ensemble.

> Ce fichier vivait dans un repertoire `s11` sans slug ; `sofia-sm` l'a range dans
> `s11-la-tabulation-ne-detruit-plus-la-partie` en ouvrant le planning du sprint.

## Verdict

**Faisable techniquement, et ce n'est pas la question qui decide.** Trois faits, dans l'ordre de leur
poids :

1. **Le gain est reel mais petit : 44 px**, mesures ci-dessous. Il ramene **un** jeu sur les trois
   aujourd'hui en defaut de pli entierement au-dessus du pli, plus l'etat "Bravo !" d'un second.
2. **Il ne corrige pas la clause 1 d'`ADR-0038`** : il deplace le meme defaut du bouton du fragment
   vers le lien de l'en-tete. La lecture de l'utilisatrice est exacte, et la raison est plus forte
   qu'une question de position - voir la demonstration en section 2.
3. **Il defait un arbitrage de `nadia-prof` du 2026-08-07**, qui a explicitement refuse ce geste :
   *"retirer le bouton du fragment laisserait la fin de partie sans porte visible la ou l'enfant
   regarde"* (motif recopie dans `recreo.css`, au-dessus de la regle de masquage, et epingle par une
   assertion de `LActionNeDeclarePasMoinsQueLaSortieTest` ecrite pour rougir exactement dans ce cas).
   **Cet arbitrage n'est pas a moi.** Je ne le leve pas et je ne le conteste pas : il remonte a
   `sofia-sm`, et le remede eventuel a `nadia-prof`.

Aucun `ADR` n'accompagne cet avis, et c'est un choix motive : voir la derniere section.

## 1. Le gain vertical, mesure

### Methode, en entier

- Application reconstruite depuis ce worktree sur un **JDK 21.0.10** (`JAVA_HOME` pose le temps de la
  commande ; le `java` du `PATH` de ce poste est un JRE 8 qui ne compile pas, constat rendu par
  `bash outils/verifier-jdk.sh`), servie sur un port local dedie, arretee ensuite par son PID.
- Etats de jeu obtenus **par requetes HTTP reelles avec porte-cookies**, puis `GET /` pour recuperer
  le **document assemble** de l'ecran de manche : c'est le detour d'`ADR-0028`, un ecran de manche
  s'obtenant par un POST qu'aucun outil de capture ne fait. Le document est servi sur un second port
  local avec une seule ligne ajoutee, une `base` qui fait resoudre feuilles et scripts sur
  l'application elle-meme.
- **Les deux etats compares portent le meme item.** L'etat modifie n'est pas rejoue : il est **derive
  du meme document assemble**, par retrait du seul `button.btn.retour-accueil` (equivalent exact de
  son retrait du gabarit, `hx-swap` inserant le fragment tel quel) et par une declaration posterieure
  `body:has(.ecran-de-jeu) .lien-accueil{display:inline-flex}` (equivalent exact du retrait de la
  regle de masquage, `.lien-accueil` declarant deja ce `display`). Rejouer aurait tire un autre item,
  donc un autre repli de texte, donc compare deux hauteurs qui ne parlent pas de la meme phrase.
  **Le gabarit et la feuille ont bien ete modifies pour de vrai, mais pour l'epreuve des garde-fous de
  la section 3, pas pour la mesure de hauteur** ; ils sont revertis, l'arbre est propre.
- Capture par la commande versionnee du depot, navigateur designe par la variable `CHROME`, sans
  quatrieme argument (page entiere, iframe de 1400 px) :

  ```
  outils/mesurer-rendu.sh 360 <url> <png>
  ```

- Positions relevees **au pixel sur l'image**, par aplats de la feuille maison servie (methode
  d'`ADR-0028`) : `#1d4b6b` pour le bandeau d'en-tete et la bordure du cabinet, `#061420` pour le
  socle, `#ffebee` pour le signal d'erreur, `#b45309` pour la bordure de l'indice. Mesure en pixels
  entiers ; les ecarts de `DEC-0025` sont au centieme, releves par `nadia-prof` avec son propre
  outillage.

### Ce que la mesure donne

Neuf captures, trois jeux (`bandit-manchot-conjugaison-present-cm1`,
`bandit-manchot-problemes-additifs-cp`, `bandit-manchot-le-bon-son-cp`), trois etats (manche fraiche,
echec, revelation), deux variantes :

| Variante | En-tete | Deplacement de tout ce qui suit | Gain vertical net |
|---|---|---|---|
| Production du jour | y 0-75 (76 px) | reference | - |
| Bouton du fragment retire, **masquage conserve** | y 0-75 (76 px) | **-53 px** | **53 px** |
| Bouton du fragment retire, **masquage leve** (la demande) | y 0-84 (**85 px**) | **-44 px** | **44 px** |

**Le chiffre de la demande est 44 px, pas 53.** Demasquer le lien de l'en-tete fait grandir le
bandeau de **9 px** : `.lien-accueil` porte `font-size: 1.25rem` sur une hauteur de ligne heritee de
1,5, plus `padding: 0.6rem 1.1rem` et une bordure de 0,125rem, ce qui fait une boite d'environ 53 px
la ou `min-height: 2.75rem` n'en promet que 44, et cette boite devient l'element le plus haut du
bandeau devant l'icone de 44 px. Ces 9 px sont payes **sur tous les ecrans de jeu**, pas une fois.
Corollaire de la meme mesure : l'accueil, qui affiche deja ce lien, porte deja un bandeau de 85 px ;
le changement uniformise l'en-tete a 85 px partout au lieu de 76 px en jeu.

Les 44 px sont une **translation constante** : verifiee identique sur les trois jeux et les trois
etats, sur le haut du cabinet comme sur le bas du bloc de message (bas de l'indice 702 -> 658,
674 -> 630, 606 -> 562, 578 -> 534 ; bas du signal 606 -> 562, 702 -> 658). C'est attendu - un
element de hauteur fixe retire au-dessus de tout le reste, a largeur inchangee, ne change aucun
repli de texte - et c'est ce qui autorise a appliquer le gain aux ecarts deja releves plutot qu'a
remesurer 261 etats.

### Combien de jeux repassent sous le pli

**Le chiffre de reference n'est pas six.** L'enonce de la demande cite les six jeux des sections
"Ce que la levee de M4 coute a l'enfant" et "La dette, chiffree" de `DEC-0025` ; **l'amendement du
2026-08-14 de ce meme fichier** les corrige apres remesure de `nadia-prof` sur le lot de cloture
(261 etats de message, 35 ecrans de revelation) : l'etat reel de M4 est **trois jeux sur sept**. Le
resserrement de marges a ferme les trois autres. Six est le chiffre d'avant le lot de cloture, et ce
code n'existe plus.

Les 44 px appliques aux ecarts de cet amendement :

| Jeu et etat | Ecart au pli aujourd'hui | Avec 44 px | Passe ? |
|---|---|---|---|
| Le bon son, revelation | -23,19 px | +20,81 px | **oui** |
| Le bon verbe, "Bravo !" | -35,19 px | +8,81 px | **oui** |
| Le bon verbe, echec et revelation | -63,19 px | -19,19 px | non |
| Petits problemes | -63,19 px et -83,19 px | -19,19 px et -39,19 px | non |
| Les complements a 10 (deja au-dessus, marge la plus juste) | +2,81 px | +46,81 px | marge confortee |

**Un jeu sur les trois repasse entierement au-dessus du pli** ("Le bon son"), plus l'etat de reussite
d'un second ("Le bon verbe"), qui reste en defaut sur ses deux autres etats. "Petits problemes" ne
ferme sur aucun de ses etats. Aucun jeu ne passe **de** l'un **a** l'autre par un demi-remede : les
deux jeux qui restent rouges le restent de 19 a 39 px.

Deux limites, nommees :

- ces ecarts sont ceux de `nadia-prof`, sur **ses** pires items ; les items que j'ai tires, eux, sont
  plus courts (mon "Le bon son" en revelation tient a 578, soit 62 px de marge). Le gain est
  constant, l'ecart auquel il s'applique ne l'est pas, et c'est elle qui detient le pire cas ;
- un vert de pli n'est pas M4. M4 se prononce en jouant, par `nadia-prof`, et `DEC-0025` fixe son
  declencheur de reouverture aux **trois stories de raccourcissement de textes**, dont celle-ci n'est
  pas une.

## 2. La clause 1 d'`ADR-0038` : la lecture de l'utilisatrice est exacte, et pour une raison plus forte

Verifie. Dans `accueil.html`, `<header class="entete">` precede `<main id="jeu">` ; le lien y est
**rang 0 de l'ordre du document**, aucun `tabindex` n'existe dans aucun gabarit de production, donc
demasque il devient `A[0]`. Il declare `hx-post="/retour"`, dont la route appelle `abandonner()` :
**`A[0]` est destructeur, clause 1 rouge**. Le defaut n'est pas corrige, il change de porteur.

**La question posee - y a-t-il une position, un ordre de document, ou une sortie unique satisferait
les deux clauses sans confirmation prealable - a une reponse nette : non, pas avec les arrets de
tabulation qui existent sur cet ecran.** Demonstration sur les deux clauses telles qu'`ADR-0038` les
ecrit :

- clause 2 porte sur **tout** point de focus que le serveur peut declarer, et `data-focus-cible` peut
  designer **n'importe laquelle** des propositions (celle que l'enfant vient de presser, celle qui
  porte la reponse revelee). Le successeur de chaque proposition dans `A` doit donc etre non
  destructeur : **la sortie ne peut suivre immediatement aucune proposition** ;
- clause 1 exige que la sortie ne soit pas `A[0]`, donc qu'elle ait un predecesseur dans `A` ;
- sur cet ecran, les seuls autres arrets de tabulation **sont** les propositions. Le predecesseur de
  la sortie est donc necessairement une proposition, ce que la clause 2 refuse.

Les deux clauses sont contradictoires pour toute position. Ce raisonnement ne compte pas les sorties :
il vaut a l'identique pour une, pour deux, pour dix. **Le nombre de sorties est hors sujet pour
`ADR-0038`** ; ce qui compte est qu'un arret destructeur soit atteignable en une tabulation depuis un
point de depart que le document autorise.

Deux sorties du raisonnement, et une seule tient :

- **interposer un arret de tabulation non destructeur** entre la derniere proposition et la sortie,
  la sortie etant alors le dernier arret : les deux clauses passent. Mais cela **ajoute** un element
  focalisable a l'ecran, c'est-a-dire l'inverse du motif de la demande, et cela met la sortie derriere
  toutes les propositions au clavier, ce qui est une question pour `nadia-prof` et `ines-design`, pas
  pour moi ;
- **rendre la requete de la sortie non destructrice** - la confirmation portee par le serveur, comme
  un ecran (`ADR-0038` point 5, avec sa condition d'architecture : pas un dialogue de navigateur).
  C'est le seul remede qui ferme les deux clauses sans rien ajouter a l'ordre de tabulation, et il
  ferme dans le meme geste la **reserve O** de `nadia-prof` (la souris sur la sortie detruit aussi la
  partie sans confirmation), que ce changement-ci laisse entiere.

**Consequence de planning, et c'est le point a ne pas laisser passer** : cette story **ne peut pas
etre presentee comme un remede a M13 clause 1**. Si elle est retenue, elle l'est pour ses 44 px, et
la confirmation avant abandon reste due.

## 3. Les garde-fous qui cassent - la liste, mesuree et non estimee

Gabarit et feuille reellement modifies dans ce worktree, `mvn -B clean verify` execute, puis
revertis. **Six echecs dans cinq classes de test**, tous dans `adapter-web` (206 tests du module,
6 en echec, `BUILD FAILURE`) :

| Test | Ce que dit l'echec | Rouge a juste titre ? |
|---|---|---|
| `LEntreeDeMenuNeSurvitPasAUnEcranDeJeuTest.chaqueEcranInsereDansLaZonePorteLeMarqueurQueLaFeuilleExige` | *"la feuille maison doit declarer au moins un masquage de la forme `body:has(.<marqueur>) .<habillage> { display: none }`"* | **Oui.** Le couple declaratif d'`ADR-0022` disparait avec la regle ; le garde-fou n'a plus d'objet et le dit au lieu de passer vert. C'est le comportement voulu. |
| `LActionNeDeclarePasMoinsQueLaSortieTest.leLienDeLEnTeteEstConfronteAuxSeulesActionsDeLAccueil` | *"masque sur les ecrans de jeu, ce lien n'y accompagne plus aucune action"* | **Oui, et c'est le rouge le plus important des six.** Sa javadoc dit qu'il a ete ecrit pour cela : *"elle rougirait si quelqu'un retirait la regle de masquage tout en gardant le lien dans l'en-tete, c'est-a-dire si l'arbitrage etait defait en silence"*. Le depot signale ici que la demande renverse l'arbitrage du 2026-08-07. |
| `CabinetDeLaMachineTest.leCabinetEntoureLesPropositionsEtRienDAutreQuiSePresse` | *"l'ecran de manche doit porter une sortie"* | **Oui sur la propriete, non sur le perimetre.** Il derive de `[data-role=sortie]`, ce qui est correct, mais il lit **le fragment** : la sortie de l'en-tete lui est structurellement invisible. Sous la story il doit etre porte sur le **document assemble** (`ADR-0020`), sinon il est rouge sur un ecran qui a bel et bien une sortie. |
| `HabillageDesEcransTest.aucuneClassePoseeSurUnElementNEstOrpheline` | *"la classe 'ecran-de-jeu' [...] n'est nommee par aucune feuille servie"* | **Oui, et il nomme un reste reel.** `ecran-de-jeu` n'a jamais porte de style propre : sa seule mention en CSS **est** le selecteur de masquage. Retirer la regle en fait un marqueur que rien ne lit. |
| `PartieControllerTest.leBoutonDeRetourAlAccueilEstVisibleSurLaManche` | assertion sur `select("button.retour-accueil")` non vide | **Non, par accident.** Il enumere un element au lieu de deriver la propriete (S1-4, 3e critere : un ecran de jeu offre une sortie visible). Cette propriete reste vraie sous la story, mais dans l'en-tete, que la reponse du fragment ne contient pas. |
| `PartieControllerTest.leBoutonDeRetourAlAccueilEstVisibleApresUneReponse` | idem | **Non, par accident**, meme motif. |

**Le piege de ces deux derniers** : le correctif le moins cher est de les supprimer, et les supprimer
retire le 3e critere de S1-4 du depot sans qu'aucun autre test ne le reprenne. Ils doivent etre
**re-derives sur le document assemble**, pas effaces.

**La clause de non-vacuite d'`ADR-0017` tient, et elle tient mal.** Elle est globale
(*"aucun couple action/sortie n'a ete confronte : cette regle ne prouverait rien"*), donc :

- sous la demande instruite (masquage leve), l'ecran de jeu confronte `.btn.lien-accueil` a
  `.btn.proposition`, la clause tient **non vide**, et - mesure, non suppose - les assertions de
  saillance de cette classe **passent** : ce lien ne depasse pas une proposition. La propriete
  d'`ADR-0017` survit au changement ;
- sous la variante sans levee de masquage (bouton retire, lien toujours masque), l'ecran de jeu
  n'aurait **plus aucune sortie** et la clause resterait verte sur le seul couple de l'accueil. Elle
  ne verrait pas la perte. C'est exactement le vert facile qu'`ADR-0038` point 3 avait nomme, et il
  n'est arrete ici que par `CabinetDeLaMachineTest`, qui lit le fragment.

## 4. Le garde-fou qui deviendrait vert sans plus rien garder

**Deux, et le second est le plus tenace.**

1. **`PartieControllerTest.lEcranDeFinDePartieRendLaSortieCommeUnBoutonPlein`**, seconde assertion :
   `assertThat(fragment.select("button.retour-accueil")).isEmpty()`. Elle epinglait que l'ecran de fin
   de partie rend la sortie en aplat (`.sortie-pleine`) et **non** en contour. Une fois
   `retour-accueil` absent de tout gabarit, cette assertion est satisfaite par une absence universelle
   : elle passe verte en ne distinguant plus rien. Elle reste verte sans que personne ne la touche.

2. **`HabillageDesEcransTest.laSortieResteEnContourSurLEcranDeJeuEtDeRetour`**, et avec elle la liste
   epinglee `SELECTEURS_HABILLES_PAR_S2_2`, qui nomme `.retour-accueil`. Les deux lisent le **bloc CSS**
   `.retour-accueil` et verifient qu'il contient `transparent`. Le bloc reste dans `recreo.css` apres
   la story ; aucun gabarit ne pose plus la classe. **Le test continue de garder une regle qu'aucun
   element rendu n'utilise, et son nom parle d'un "ecran de jeu" ou cette classe n'existe plus.**
   Verifie empiriquement : sur le build fautif, les six echecs ne comprennent **aucune** plainte sur
   une regle CSS qui ne nomme aucun jeton de gabarit. Le depot garde le sens gabarit vers CSS
   (`aucuneClassePoseeSurUnElementNEstOrpheline`) et **pas** le sens inverse. C'est la maladie
   qu'`ADR-0031` a nommee - *une regle ne nommant aucun jeton pose par un gabarit ne mord pas* - non
   encore encodee dans ce sens.

**Obligation minimale si la story est retenue** : le lot supprime le bloc CSS `.retour-accueil` dans
le meme commit que le retrait du bouton, ce qui fait rougir les deux tests du point 2 et force a les
traiter au lieu de les laisser verts. Les laisser tous les deux verts sur du code mort serait le
cinquieme garde-fou sans objet de ce chantier.

## 5. Ce que je ne decide pas

- **Faut-il une sortie visible dans la zone de contenu ?** Arbitrage de `nadia-prof` du 2026-08-07,
  motive par ou l'enfant regarde. Le renverser est un geste produit et pedagogique : `sofia-sm` puis
  `nadia-prof`. Je livre le cout (44 px, un jeu ferme) et le prix (un arbitrage defait, `ADR-0038`
  non corrige, la reserve O intacte).
- **Que raccourcir dans les enonces et les indices**, seul geste qui retranche vraiment de la hauteur
  selon `DEC-0025` : `camille-po` et `nadia-prof`.
- **La place de la sortie dans l'ordre de tabulation**, si la voie de l'arret interpose etait
  instruite : `nadia-prof` et `ines-design`.

## 6. Aucun `ADR` n'accompagne cet avis, et pourquoi

**`ADR-0038` n'a pas a etre amende : une sortie unique ne change pas son perimetre.** Il est enonce
sur les arrets de tabulation d'un document assemble et sur l'effet destructeur d'une requete, jamais
sur un nombre de sorties ni sur un fichier. La section 2 ci-dessus n'ajoute rien a sa doctrine : elle
en **derive** un resultat d'impossibilite avec ses propres clauses, sur la production du jour.
Ecrire un `ADR` pour republier une consequence de l'`ADR` precedent gonflerait l'index sans ajouter
une regle, et ce depot a deja paye pour des ecrits qui ne mordent pas.

Ce qui me ferait en ecrire un :

- **si la story est retenue** : le sens CSS vers gabarit du point 4.2 doit devenir une regle - une
  declaration de la feuille maison qui ne nomme aucun jeton pose par un gabarit servi est du code
  mort garde par un test vert. C'est encodable dans `adapter-web`, les deux derivations existent deja
  (`blocsMaison`, le balayage des gabarits) ; ce n'est pas une `ArchRule`, ArchUnit lisant du
  bytecode. Je ne l'ecris pas aujourd'hui : la story n'est pas decidee, et un garde-fou ecrit avant
  la decision qu'il garde serait rouge sur une production que personne n'a mandate de changer ;
- **si `CabinetDeLaMachineTest` est porte du fragment au document assemble** : le perimetre "un ecran
  de jeu offre une sortie" cesse d'etre une propriete de gabarit pour devenir une propriete d'ecran
  assemble, ce qui est un deplacement de doctrine et merite son ecrit.

## Verification

`mvn -B clean verify` dans ce worktree, sur un **JDK 21.0.10** designe par `JAVA_HOME` le temps de la
commande : **BUILD SUCCESS**.

**Chiffres, avec la methode dans le meme paragraphe.** Comptage lu apres un `clean` sur les lignes
`Results:` du journal, module par module : `domain` 46, `application` 7, `adapter-web` 206,
`adapter-content` 64, `bootstrap` 77 en surefire et 6 en failsafe. Total **406 tests**, aucun echec,
aucun ignore.

**Regles ArchUnit : 28, inchange.** Methode : balayage de la forme `ArchRule <nom> =` dans les
`src/test/java` des cinq modules de code du pom agregateur, execute **dans ce worktree seul** -
`adapter-web` 1, `bootstrap` 27. Les trois causes de divergence deja etablies sont neutralisees ici :
le faux depot de contre-exemple vit sous `src/test/resources` et n'entre pas dans ce perimetre, les
worktrees des autres agents vivent hors de cet arbre, et le compte n'est pas lu sur un rapport texte
dont un des deux moteurs JUnit n'apparait pas.

**Etat de l'arbre** : les deux fichiers de production modifies pour l'epreuve de la section 3
(`fragments/manche.html`, `static/css/recreo.css`) sont revertis ; `git status` ne rapporte aucune
modification de production. **Zero octet de ce que voit un enfant ne change par cet avis.**

**Serveurs** : les deux processus lances pour la mesure (application sur un port dedie, service de
fichiers pour les documents assembles) ont ete arretes **par leur PID**. L'instance de l'utilisatrice
sur le port 8080 n'a pas ete touchee.
