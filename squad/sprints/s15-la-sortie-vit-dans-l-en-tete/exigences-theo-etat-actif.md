# Exigences d'architecture - un seul rang, et un etat actif sur la puce choisie

- **Auteur** : theo-archi. **Date** : 2026-08-18.
- **Branche** : `s15-theo-etat-actif`, worktree dedie, forkee de `round-two` a `b109f98` ; lignee
  verifiee par `outils/verifier-lignee.sh`.
- **Destinataire** : `marc-dev`. Les sections 1 et 2 sont ce qu'il attend.
- **Ce que je ne fais pas** : rediscuter les deux demandes, tranchees par l'autorite au-dessus de la
  squad ; choisir la couleur, la teinte ou la forme du second porteur (`ines-design` propose,
  `nadia-prof` juge).
- **Aucun chiffre de rendu revendique** : je n'ai pas de navigateur. Le seul chiffre de largeur qui
  circule dans cette saisine (environ 400 px de contenu, retour a la ligne sous environ 420 px) n'est
  pas de moi et je ne le reprends pas comme mesure - je dis en section 3 pourquoi il n'est pas
  derivable de ce depot.

## 1. Le mecanisme : deux moities, deux mecanismes, et c'est la conclusion principale

**La demande 2 se decompose en deux proprietes dont les besoins de rafraichissement sont opposes.** C'est
ce qui interdit de chercher un seul mecanisme.

| Moitie | L'evenement | Ce que l'en-tete doit apprendre | Un echange htmx l'atteint-il ? |
|---|---|---|---|
| **(i) marquer CP actif** | l'enfant presse une puce, donc appelle la route du niveau | quel niveau est choisi, une **donnee variable** | non, mais **un rendu complet est disponible** : c'est la puce elle-meme qui declenche |
| **(ii) l'etat cesse au demarrage du jeu** | l'enfant presse un titre de jeu, `POST /parties/{jeuId}` | rien de variable : seulement "on est dans un jeu" | non, et **aucun rendu complet n'est disponible** : le geste vise `#jeu` |

**(i) exige donc un rendu de document complet ; (ii) ne peut etre obtenu que sans rafraichir
l'en-tete.** Une seule voie ne peut pas servir les deux.

### 1.1 Pour (i) : voie (a), les puces deviennent des liens de navigation complete - **retenue**

Les puces perdent `hx-get`/`hx-target` et gardent leur `href` vers la route de niveau qu'`ADR-0044`
point 2 a deja imposee. Le serveur rend le document entier, en-tete comprise, et pose la classe d'etat
actif sur la puce du niveau demande - **par la donnee**, jamais par une regle CSS par niveau.

- **Cout, nomme** : un chargement de page complet a chaque appui sur une puce, la ou tout le reste de
  l'application echange un fragment. C'est exactement le prix qu'`ADR-0044` point 3 avait chiffre
  d'avance pour ce cas ; il devient du.
- **Ce que ca ne casse pas, verifie** : `ADR-0038` juge la requete **declaree** d'un arret, en
  preferant `hx-post`, puis `hx-get`, puis - pour un lien - le `href`. Une puce sans attribut htmx est
  donc rejouee sur son `href`, `GET /niveaux/{niveau}`, qui ne detruit pas la partie : les quatre
  clauses restent vertes. Aucune route neuve, donc rien a ajouter au corpus.
- **Ce que ca gagne** : l'etat actif est un `th:class` sur la puce, donc **une donnee** - ajouter un
  niveau ne demande ni regle CSS ni ligne de code (`ADR-0006`).

### 1.2 Pour (ii) : la piste de `sofia-sm` est la bonne, et je la valide

Une regle relationnelle keyee sur le marqueur que le serveur pose deja dans la zone echangee neutralise
l'apparence active des qu'un ecran de jeu est affiche, **sans qu'aucun rafraichissement d'en-tete soit
necessaire** et sans une ligne de JavaScript. Le mecanisme existe, il est eprouve, et il est re-evalue a
chaque echange par le navigateur.

**Ce que cela fait de la forme contractuelle d'`ADR-0022` : rien, et c'est voulu.** La forme ne connait
que `display: none`, et cette regle-ci ne masque pas : elle neutralise une apparence. Elle **ne sera donc
pas lue par `masquages()`**, et c'est le bon comportement, pas une limite a contourner :

- `AucunArretDeTabulationNEstDestructeurTest` ne retirera pas la puce de l'ordre de tabulation - **juste**,
  elle reste visible et focalisable ;
- `LActionNeDeclarePasMoinsQueLaSortieTest` continuera de la confronter aux actions - **juste**, elle
  s'affiche toujours ;
- la clause D ne comptera rien de moins - **juste**, aucune sortie n'est masquee par cette regle.

**Elargir la forme contractuelle a d'autres proprietes que `display: none` serait le seul vrai danger
ici** : trois garde-fous lisent `masquages()` en croyant y trouver "ce qui est invisible". Une regle de
neutralisation d'apparence y entrerait comme un masquage et leur ferait calculer un document faux en
restant verts. **Je refuse cet elargissement, et c'est la seule chose que je refuse dans cette saisine.**

### 1.3 Les deux voies que j'ecarte, et leurs motifs mesures

**`hx-swap-oob` : ecarte, et le motif de s14 ne tient pas seulement, il s'est aggrave.** La dette
d'`ADR-0020` n'est pas une objection de principe : son garde-fou **reimplemente** `hx-swap="innerHTML"`
en posant le fragment dans la zone (`coquille.select(cible).html(fragment)`). Un fragment portant une
copie hors bande de l'en-tete serait donc assemble **a l'interieur de `#jeu`** au lieu de s'y substituer :
le document juge porterait deux en-tetes, dont un imbrique. Et depuis s14 ce document assemble est la
matiere de bien plus de clauses qu'en s06 - les quatre d'`ADR-0038`, l'appariement d'`ADR-0017`, la
clause D sur l'unicite de la sortie visible. **Un assemblage faux les corromprait toutes en restant
vert**, ce qui est le mode de defaillance le plus dangereux que ce depot ait nomme. `hx-swap-oob`
exigerait de plus un identifiant sur l'en-tete, qu'`ADR-0020` a retire du depot.

**Une regle CSS par niveau : ecartee, et pour la meme raison qu'en s14.** Un selecteur relationnel ne peut
pas exprimer "la puce dont le libelle vaut le marqueur" : il faudrait une regle par niveau, donc ajouter
un niveau cesserait d'etre une donnee (`ADR-0006`). C'est la seule chose que le mecanisme relationnel ne
sait pas faire, et c'est exactement pourquoi (i) et (ii) se separent.

**Deux voies que personne n'a proposees et que je ferme d'avance, parce qu'un lot presse y arrive
naturellement** : une ligne de JavaScript qui basculerait une classe - interdite par `ADR-0008`, bridee
par `FrontieresDuJavaScriptTest`, et **invisible a tout garde-fou declaratif de ce depot** ; et deplacer
la barre de niveaux dans la zone echangee - c'est l'alternative 3 qu'`ADR-0022` a rejetee, elle
detruirait `DEC-0012` et dupliquerait le balisage dans quatre gabarits.

## 2. Ce que `marc-dev` doit livrer

**Production**

1. Un seul rang dans l'en-tete, dans l'ordre demande, "Les jeux" pousse a droite. **Le rang declare
   `flex-wrap: wrap`** - voir section 3 : ce n'est pas un detail de mise en page, c'est le seul endroit
   ou le mode de defaillance de la demande 1 devient une declaration.
2. Les puces perdent leurs attributs htmx et gardent leur `href` (section 1.1).
3. Une classe d'etat actif posee **par le serveur** sur la puce du niveau demande, en `th:class`, jamais
   par une regle par niveau.
4. Une regle maison qui **neutralise** cette apparence quand la zone porte le marqueur d'ecran de jeu
   (section 1.2). Elle ne prend pas la forme contractuelle du masquage, et elle ne doit pas la prendre.

**Garde-fous - deux clauses, et une seule est vraiment neuve**

- **Clause E - l'etat actif cesse, et cela ne depend pas d'une relecture.** Toute classe qu'un point
  d'entree peut porter **conditionnellement** (lue dans les litteraux du `th:class` du gabarit, jamais
  ecrite dans le test) est nommee par une regle maison qui la neutralise sur le marqueur d'ecran de jeu.
  Population derivee du gabarit ; **non-vacuite exigee** : au moins une classe conditionnelle existe.
  Ce qu'elle interdit reellement : que la regle de neutralisation soit retiree ou renommee pendant que
  la classe reste posee - l'etat actif persisterait alors pendant une partie, silencieusement, et c'est
  precisement la moitie de la demande que rien d'autre ne garde. Le sens inverse - la classe renommee,
  la regle laissee - est **deja ferme** par `UnEcranNeGouvernePasLApparenceDUnAutreTest` (`ADR-0031`),
  qui refuse un jeton qu'aucun ecran ne peut poser : je ne demande pas de clause pour cela.
- **Clause F - l'etat actif ne repose pas sur la couleur seule** (section 5). La regle maison de la
  classe active declare **au moins une composante qui n'est pas une couleur**. Elle ne juge pas laquelle
  et ne peut pas juger si un enfant la percoit.

**Ce que je ne demande pas** : aucune clause sur l'ordre des elements du rang, aucune sur la position de
"Les jeux" a droite. Ce sont des proprietes de rendu, et les inventer en declaratif donnerait un
garde-fou qui a l'air de garder la demande 1 sans la garder.

**Une consequence de la demande 2 qu'il faut voir venir, et qui n'est pas une exigence de moi mais du
depot** : `AucunEtatDePressableNEstAbandonneAuTiersTest` derive sa population **de la feuille**, pas d'une
liste. Une classe active qui declare `color`, `background-color` ou `border` entre donc dans cette
population toute seule, et le garde-fou exigera que ses etats `:hover`/`:active` soient declares en
maison plutot que laisses a la bibliotheque vendorisee. C'est du travail en plus, et il est derive :
personne n'a a s'en souvenir, la CI le dira.

## 3. Le debordement a 360 px : non gardable, et ce cas ne differe pas - mais son mode de defaillance l'est

**Non gardable, meme motif qu'`ADR-0025`, et je le verifie plutot que de le citer.** Le chiffre annonce -
environ 400 px de contenu pour un plancher produit de 360 px - est une **mesure sur un rendu**, pas une
derivation : la largeur d'un libelle depend de la police, et aucun fichier maison ne declare de
`font-family`. Elle vient de `--bs-font-sans-serif`, c'est-a-dire de la police systeme du poste -
c'est l'amendement du 2026-08-14 a `ADR-0022` qui l'a etabli, sur ce point precis. Un garde-fou qui
additionnerait des largeurs declarees mentirait, et il mentirait dans le sens le plus dangereux : vert
sur un poste, faux sur celui de l'enfant.

**Ce cas ne differe donc pas d'`ADR-0025`. Ce qui differe est que le debordement est ici accepte
d'avance**, en connaissance de cause, par l'autorite qui fixe le plancher de 360 px. Ce n'est pas une
regression a attraper : c'est une decision, et un garde-fou n'a pas a juger une decision.

**Ce qui est gardable, et c'est la seule chose que je demande sur ce point** : **le mode de defaillance**.
A largeur insuffisante, un rang de boutons fait l'un ou l'autre - il **passe a la ligne**, ou il
**deborde horizontalement**. Le premier est ce que l'utilisatrice a accepte ; le second couperait des
boutons hors de l'ecran, sans aucun signal. La difference entre les deux est une **declaration**
(`flex-wrap`), donc lisible, donc gardable - c'est la forme qu'`ADR-0028` juge acceptable : garder une
cause, jamais un effet. D'ou l'exigence 1 de la section 2.

**Ce que cette clause ne prouve pas, dit avant qu'on le lui fasse dire** : ni qu'un enfant voie les trois
boutons a 360 px, ni ou la ligne se casse, ni que le resultat soit lisible. `nadia-prof` seule le
constate, par capture, et elle est la seule a en avoir les moyens - c'est la dependance que j'ai nommee
en s14 section 7.4, et rien ici ne la reduit.

## 4. Le releve, dans les deux sens - ma regle de methode de la veille appliquee

**Methode** : `grep -rln "<motif>" --include=*.java` sur les sources de test des cinq modules, hors
`target/`, sur l'arbre `b109f98`. Motifs : `chip-niveau`, `bandeau-niveaux`, `lien-accueil`,
`entete-contenu`, `nav.menu`. La regle reecrite hier demande les deux sens - ce qu'un element perd et ce
qu'il gagne - et cette saisine est du cote "gagne" : rien ne disparait, une classe et un rang unique
apparaissent.

**Sens "gagne" - ce qui voit la nouveaute sans qu'on l'inscrive :**

| Source | Ce qu'elle voit |
|---|---|
| `AucunEtatDePressableNEstAbandonneAuTiersTest` | **le point important du releve.** Sa population est **derivee de la feuille**, donc la classe active y entre seule et son obligation d'etats declares s'appliquera d'office. Son unique mention litterale de `.chip-niveau` (l. 404) est un **epinglage de mecanisme** au repos, pas son critere : il reste vrai, la puce gardant sa regle de repos |
| `UnEcranNeGouvernePasLApparenceDUnAutreTest` | lit les litteraux des `th:class` : la classe active devient un jeton, et un jeton qu'aucun ecran ne peut poser est un echec. C'est ce qui ferme le sens inverse de la clause E |
| `HabillageDesEcransTest` | toute classe posee doit etre nommee par une feuille servie : la classe active devra l'etre, ou disparaitre |
| `TexteAccentueDesGabaritsTest` | inchange - aucun texte neuf n'est ajoute par cette demande |

**Sens "perd" - deux sujets a surveiller, aucun bloquant :**

| Source | Ce qui bouge |
|---|---|
| `LEntreeDeMenuNeSurvitPasAUnEcranDeJeuTest` (l. 649-650) | ses deux chaines de preuve nomment `.chip-niveau` et `.bandeau-niveaux`. **Si le rang unique fait disparaitre `.bandeau-niveaux` de la production**, le jeu fautif qui oppose le conteneur a l'element focalisable garde sa valeur d'algorithme mais cesse de citer un nom reel. Ce n'est pas un echec : c'est un fixture, et son motif - nommer le conteneur au lieu du pressable - reste la faute a interdire. **A signaler dans le commit, pas a corriger par zele** |
| `LActionNeDeclarePasMoinsQueLaSortieTest` (l. 647, 653) | l'epinglage retourne hier compare des composantes declarees, pas une mise en page : un rang unique ne le touche pas |
| `CabinetDeLaMachineTest` (l. 103) | mention en Javadoc, aucune assertion |

**Ce que le releve n'attrape pas, inchange et redit** : un garde-fou dont la **derivation** lit une source
dont l'element serait la derniere instance. Ici il n'y en a pas - mais c'est une lecture qui l'etablit,
pas la commande.

## 5. Le second porteur de l'etat actif : oui, un garde-fou peut exiger qu'il existe

**Il peut, et sans jamais juger lequel.** La regle maison de la classe active est lisible ; exiger qu'elle
declare **au moins une composante qui n'est pas une couleur** est une lecture de declarations, du meme
genre que celles que `TailleDesTextesEtBoutonsTest` et `LActionNeDeclarePasMoinsQueLaSortieTest` font
depuis s05. C'est la clause F.

**Ce qu'elle interdit reellement** : que l'etat actif se reduise a un changement de teinte - le cas
exact que la regle d'accessibilite de ce depot vise, et le cas le moins cher a ecrire pour un lot presse.

**Ce qu'elle ne peut pas faire, et qui doit etre ecrit a cote d'elle** : dire si le second porteur est
**percu**. Un contour de 0,0625rem satisferait la clause et ne serait vu par personne. La clause garantit
qu'un second canal **existe** ; qu'il porte reellement l'information reste a `nadia-prof`, sur capture,
comme toutes les proprietes de rendu de ce depot. Une clause qui pretendrait davantage serait pire
qu'absente : elle donnerait un vert a lire comme une garantie d'accessibilite.

## 6. Ce que cet avis ne fait pas

Il ne choisit ni teinte, ni forme, ni libelle - `ines-design` propose, `nadia-prof` juge. Il ne prononce
aucun Done pedagogique. Il ne repriorise rien. Et il ne revendique aucune mesure de rendu : la seule
largeur citee dans cette saisine n'est pas de moi, et la section 3 dit pourquoi ce depot ne peut pas la
produire.
