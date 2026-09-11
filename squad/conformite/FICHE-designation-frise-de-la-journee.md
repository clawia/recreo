# Fiche de conformite - La journee, moment par moment (`designation-frise-de-la-journee`)

- **Mecanique** : `designation` (**mecanique neuve du produit**, `ADR-0080`, `ADR-0088`)
- **Contenu(s)** : `cp-frise-de-la-journee` (11 items, deux dessins)
- **Statut global** : **VALIDE SOUS RESERVE** (REFUSE au premier passage le 2026-09-11, **refus leve
  au second passage du 2026-09-11, s35** - les deux blocs de validation sont lisibles ci-dessous,
  dans cet ordre)
- **Etat au 2026-09-11 (s35, `S35-QLM14-FRISE`)** : **`FRI-R1` et `FRI-R2` sont LEVEES.** La passe
  image effacee, rejouee sur les onze items, tombe a **5 sur 11** contre 9 sur 11 a s34, et le
  pictogramme du lever se lit comme un lit aux deux tailles. **`QLM-CP-14` passe `couvert-valide` et
  la mention de couverture partielle de la matrice tombe.** Reserves ouvertes, toutes non
  bloquantes : **`FRI-R6`** (le texte alternatif de la frise du soir annonce *quatre moments de
  gauche a droite* pour un dessin de quatre moments sur **trois places**, deux d'entre eux empiles -
  **la plus lourde, a payer avant que la formule ne soit recopiee**), **`FRI-R7`** (l'indice de
  l'item 9 nomme un rang que les libelles ne portent pas), et `FRI-R3`, `FRI-R4`, `FRI-R5`
  inchangees.
- **Derniere mise a jour** : 2026-09-11 (s35)

> **Fiche ouverte par `nadia-prof`, instance B de s34, et non par `camille-po`.** Le jeu est neuf, la
> mecanique est neuve, aucune fiche n'existait. J'ai rempli les sections descriptives depuis la story
> `S33-PAL2-1` du backlog, depuis `catalogue.yaml` et depuis le referentiel, sans rien y ajouter de
> mon cru ; **le bloc de validation est le mien, comme toujours.** Si `camille-po` veut reprendre la
> main sur les sections qui lui reviennent, elle le peut : elles ne portent aucun jugement.

## Competences visees

| Matiere | Niveau | Domaine | Competence | Reference au referentiel |
|---|---|---|---|---|
| Questionner le monde | CP (cycle 2) | Se situer dans le temps | Situer des evenements les uns par rapport aux autres sur une frise chronologique simple (avant, apres, en meme temps) | `QLM-CP-14`, `squad/programme/questionner-le-monde/competences-cp.md` |

`QLM-CP-11` (plan de la classe) **n'est pas servie par ce jeu** : `cp-plan-de-la-classe.yaml` et
`plan-classe.svg` n'ont pas ete commites, `lea-dev` les ayant retires de son commit devant un defaut
de geometrie et de lisibilite qui n'etait pas de son ressort (planning de s34, section 15.2). Cette
ligne n'entre pas dans cette fiche et reste `non couvert`.

## Criteres pedagogiques attendus - camille-po

Repris de `docs/backlog/backlog.md`, story `S33-PAL2-1`, et des huit conditions `ZON-7` a `ZON-14` de
l'arbitrage prealable de s29.

- [x] Onze items, `nombreDeManches: 10` (`DEC-0064`), plancher de dix (`FER-2`)
- [x] Deux taches distinctes, la moins servie a trois items au moins (`FER-3`) - `T-B` a 5 items,
      `T-A` a 6
- [x] Chacune des trois relations de la parenthese est demandee par au moins un item (`FER-1`) :
      *avant* (items 5 et 7), *apres* (item 6), *en meme temps* (items 10 et 11)
- [x] Deux dessins au minimum (`ZON-8` de s29, section 4.4)
- [x] Trois zones au moins par item, six au plus au CP (`ZON-8`) - 6 et 4
- [x] Les evenements sont ceux de la vie de l'enfant, dessines et jamais ecrits (`DES-7`)
- [x] L'ordre de declaration des zones est l'ordre de lecture de l'image (`ZON-11`, tel que tranche
      sur une frise le 2026-09-10)
- [x] Deux zones ne se chevauchent pas et aucune ne deborde du dessin (`ZON-13`)
- [x] Une seule forme de libelle sur tout le paquet (`ZON-10`) - positionnelle
- [x] Un item, un geste (`ZON-14`)
- [x] L'ecran de fin de partie porte la question qu'il commente (`S33-PAL2-TROU-A`, `ADR-0088`
      clause A)
- [x] L'ecran de fin de partie ne montre ni identifiant ni libelle de zone (`S33-PAL2-TROU-B`,
      `ADR-0088` clause B)
- [ ] **Aucun texte ne remplace l'image (`ZON-1`)** - **non tenue, neuf items sur onze**
- [ ] **Le texte alternatif dit ce que l'image est, jamais ou est la reponse (`ZON-3`)** - **non
      tenue, sur les deux dessins**
- [ ] **La cible se trouve sur le dessin seul, zones masquees (`ZON-9`)** - **non tenue sur le
      pictogramme du lever**

---

## Bloc de validation pedagogique - nadia-prof UNIQUEMENT

> Ce bloc n'est jamais pre-rempli ni anticipe par un autre agent.

- **Verdict** : **REFUSE**
- **Date** : 2026-09-11
- **Auteur** : `nadia-prof`, instance B de s34
- **Arbre** : `round-two` a `a7852398` (fusion des quatre branches, porte technique verte)
- **Audit joue** : **oui.** Jar lance sur le JDK 21.0.10 de la toolchain, port 8099. Contenu du jar
  compare au contenu de l'arbre avant de jouer, identique sur les trois paquets audites - je ne juge
  pas un jar plus vieux que l'arbre. **Une partie complete de dix manches toutes justes** (ecran de
  fin atteint), **une partie avec une erreur volontaire par manche**, **une partie a epuiser les
  trois essais sur une meme manche**. **Les onze items ont ete vus a l'ecran.**
- **Audit rendu, non degrade sur la partie visuelle, et je dis comment** : j'ai joue par requetes
  HTTP reelles, donc lu le HTML servi ; **mais l'ecran de manche a en plus ete rendu dans un
  navigateur** (Chrome, feuilles `bootstrap.min.css` et `recreo.css` servies par l'application,
  dessins servis par l'application, largeurs de conteneur 768 px et 1024 px), capture en image et
  mesure zone par zone. **C'est la premiere fois que je regarde un ecran de designation, et c'est la
  seule facon de tenir `ZON-9`.**

### Ce que je refuse, et les deux motifs sont anterieurs a ce sprint

#### `FRI-R1` - le texte alternatif enumere les moments dans leur ordre, donc la reponse se trouve sans l'image (`ZON-1`, `ZON-3`)

**Le fait.** Les deux dessins servent un texte alternatif qui enumere leurs moments **dans l'ordre de
la frise** :

- *"Frise de la journee d'ecole, six moments sur un axe - le lever, le petit-dejeuner, l'ecole du
  matin, le dejeuner, l'ecole de l'apres-midi, le gouter."*
- *"Frise de la routine du soir, quatre moments - le diner, se brosser les dents et mettre son pyjama
  en meme temps, puis le coucher."*

Les libelles des zones sont des rangs : *"le premier"*, *"le deuxieme"*, ... **Le texte alternatif et
les libelles forment ensemble une table de correspondance complete entre un moment et son rang.**

**La passe image effacee, item par item, exhaustive - c'est le test meme de `ZON-1`** : *"en effacant
l'image et en gardant l'enonce, le texte alternatif, l'indice, les libelles, la reponse reste-t-elle
trouvable ?"*

| # | Enonce | Trouvable sans l'image ? | Par quoi |
|---|---|---|---|
| 1 | *Montre le lever.* | **oui** | 1er nomme du texte alternatif -> *le premier*. **Et l'indice, *"C'est le tout premier moment de la journee"*, donne le libelle mot pour mot** |
| 2 | *Montre le petit-dejeuner.* | **oui** | 2e nomme -> *le deuxieme* |
| 3 | *Montre l'ecole du matin.* | **oui** | 3e nomme -> *le troisieme* |
| 4 | *Montre le dejeuner.* | **oui** | 4e nomme -> *le quatrieme* |
| 5 | *Montre ce qui vient juste avant l'ecole du matin.* | **oui** | 3e nomme, donc le rang precedent -> *le deuxieme* |
| 6 | *Montre ce qui vient juste apres le dejeuner.* | **oui** | 4e nomme, donc le rang suivant -> *le cinquieme* |
| 7 | *Montre ce qui vient juste avant le petit-dejeuner.* | **oui** | 2e nomme, donc le rang precedent -> *le premier* |
| 8 | *Montre le diner.* | **oui** | 1er nomme -> *le premier* |
| 9 | *Montre le coucher.* | **oui** | dernier nomme (*"puis le coucher"*) -> *le troisieme* |
| 10 | *Montre ce qui se passe en meme temps que se brosser les dents.* | **partiellement** | le texte alternatif donne le couple, pas lequel est en haut : une chance sur deux, et l'ordre d'enumeration la fait pencher |
| 11 | *Montre ce qui se passe en meme temps que mettre son pyjama.* | **partiellement** | idem |

**Neuf items sur onze se gagnent entierement sans regarder le dessin.** `ZON-1` dit ce qui arrive
alors, et le disait avant que ce lot existe : *"l'item est une contrefacon et le lot est refuse a la
porte, quelle que soit la qualite du dessin."*

**C'est exactement la defaillance que j'avais nommee d'avance et dont je m'etais declaree seule
garde** (arbitrage prealable de s29, section 6) : *"Ce qu'elle n'attrape pas, et je reste sa seule
garde : la paraphrase. [...] ne contient aucun mot du libelle et donne la reponse entiere. Aucun
automate ne verra cela, et je le verifie item par item."* **Correction a ma propre formulation, faite avant d'arbitrer dessus.** J'allais ecrire que le
garde-fou 5 d'`ADR-0080` compare le texte alternatif au libelle. **C'est faux, verifie dans le test
et non dans l'`ADR`** : `AucunEnonceDeDesignationNeNommeLeLibelleDeSaBonneZoneTest` compare
l'**enonce** au libelle de la bonne zone, decoupe en mots. **Il ne lit pas le texte alternatif, et
aucun autre test du depot ne le lit contre quoi que ce soit** - le seul qui le touche sur un paquet
de designation verifie son accentuation. Le defaut est donc **plus large** que je n'allais le dire :
ce n'est pas qu'un garde-fou regarde a cote, c'est que **rien ne regarde**. Et la Javadoc de ce test
le dit elle-meme, en me citant : *"cette moitie-la reste entierement a la relecture de nadia-prof,
item par item ; ce garde-fou n'y pretend pas."* **Quatre jobs verts, et neuf items sur onze se
repondent sans image.**

**Ce que la reparation demande, et elle est petite** : **deux lignes.** Un texte alternatif honnete
dit ce que l'image **est**, pas ce qu'elle contient dans l'ordre - *"Frise de la journee d'ecole, six
moments dessines sur un axe du temps"* suffit et tient `ZON-3`. **Je n'ecris pas les deux textes**
(`RES-2`) ; j'ecris la propriete qu'ils doivent tenir. L'indice de l'item 1 est a reprendre dans le
meme geste : il donne le libelle de la bonne zone.

**Ce que je tranche dans le meme mouvement, parce que la lecture aurait pu aller ailleurs.** On
pouvait croire que ce sont les **libelles** qui fautent, les rangs portant sur une frise la relation
meme que les items 5 a 7 demandent (`ZON-2`). **Ce n'est pas ma lecture, et je ne fais pas porter le
defaut a `ZON-10`.** Un rang dit ou est la zone, pas quel moment elle porte ; pour s'en servir,
l'enfant doit d'abord lire le dessin. **Ce sont les rangs plus le texte alternatif qui ferment la
boucle, jamais les rangs seuls.** Retirer l'enumeration du texte alternatif repare `ZON-1` et `ZON-2`
ensemble et laisse `ZON-10` intacte. **`ZON-10` n'est pas amendee et les libelles positionnels
restent obligatoires sur ce paquet.**

#### `FRI-R2` - le pictogramme du lever ne se lit pas comme un lit (`ZON-9`)

**Le fait, vu au rendu et non lu dans le marquage.** Dessin `frise-journee-ecole.svg` a la taille
servie (640 x 320), zones masquees, premiere colonne : un rectangle large portant un petit rectangle
en haut a gauche et un trait horizontal, plus **un trait vertical detache qui deborde de trente
unites au-dessus du rectangle**. Le marquage annonce *"un lit avec sa tete de lit, son oreiller et sa
couverture"* ; **ce qui se voit est une enveloppe, une carte ou un ecran.** Je ne trouve pas le lit,
au rendu comme au triple agrandissement.

`ZON-9` : *"Si je ne le trouve pas, aucun enfant ne le trouvera, et l'item est refuse."* **L'item 1
(*Montre le lever.*) est refuse sur ce seul motif**, independamment de `FRI-R1`. L'item 7, qui vise
la meme zone par une relation (*juste avant le petit-dejeuner*), n'a pas besoin que le pictogramme
soit lisible et n'est pas touche.

**Ce n'est pas un defaut d'`ines-design` seule** : trois des quatre defauts de rendu qu'elle a
elle-meme trouves et corriges avant de livrer sont de cette famille (section 3 de sa note), et elle
les a trouves en regardant. Celui-ci a survecu. **La forme du remede est a elle ; la propriete est a
moi et elle est deja ecrite** : *le pictogramme d'un moment se reconnait a la taille servie, sans son
commentaire.*

### Ce que je ne compte pas dans le refus, et je le dis pour ne pas deplacer mes poteaux

**Trois faits importants trouves au rendu, dont aucun n'est un motif de refus de ce lot** :

> **`FRI-R4` - deux libelles debordent de leur bouton.** Mesure dans le navigateur, aux deux largeurs
> de conteneur : les zones de la frise de la journee rendent 96 x 240 px, dont 92 px de boite de
> contenu apres le pave de `.zone` ; *"le quatrieme"* demande 94 px et *"le cinquieme"* 95 px. **Le
> texte deborde de 2 a 3 px et touche le bord du bouton voisin.** C'est la meme famille de defaut que
> celui pour lequel `lea-dev` a retire le plan de sa livraison - plus petit, et il est passe.
>
> **`FRI-R5` - a l'ecran de manche, la revelation apres trois essais n'est qu'un deplacement de
> focus.** Verifie en jouant : au troisieme echec le verdict passe de *"Ce n'est pas ca. Essaie
> encore !"* a *"Ce n'est pas ca."*, la bonne zone recoit `data-focus-cible="true"` et
> `recreo.js` lui donne le focus. **Aucune regle de `recreo.css` ne vise cet attribut** : la seule
> marque est l'anneau de focus du navigateur. Rendu avec focus programmatique et sans geste
> anterieur, cet anneau est bien visible (`:focus-visible` vrai, mesure). **Mais l'enfant vient de
> presser une zone au doigt ou a la souris**, et c'est le cas ou un navigateur donne `:focus` sans
> `:focus-visible`. **Un enfant de CP peut donc voir un ecran identique au precedent, a deux mots
> pres, sans savoir que la manche est finie ni ou etait la reponse.** Sur le bandit manchot, la meme
> situation sert un bloc explicite (*"Appuie sur la bonne reponse :"*) - joue et vu le meme jour. **Le
> chemin fonctionne** : la pression suivante, sur n'importe quelle zone, passe a la manche d'apres,
> donc un enfant bloque avance.
>
> **`FRI-R3` - le pictogramme du diner porte mal le repas du soir.** Un bol vide, tres plat a la
> taille servie, lisible seulement a l'agrandissement ; et l'autre frise sert un bol pour le
> petit-dejeuner. **Le dessin ne dit pas *le soir*.** Item 8 jouable par le rang sur l'axe, donc je ne
> le refuse pas ; mais l'enfant n'apprend rien du dessin.

**Et le fait le plus lourd des trois, que je nomme sans en faire un motif parce que la condition qui
le juge, je l'ecris aujourd'hui** : **les boutons de zone couvrent le dessin qu'ils designent.**
`.zone` pose un fond blanc a 55 % et un texte gras de 20 px par-dessus l'image ; sur la frise de la
journee, les six boutons couvrent la totalite du dessin. Au rendu, **les six pictogrammes sont des
fantomes gris derriere le texte, l'axe du temps est interrompu par les boites, et l'ecran ne se lit
plus comme une frise mais comme six cartes numerotees**. La strategie la plus rapide devient alors :
lire le rang, jamais le dessin - `FRI-R1` par un second chemin. **Ce fait n'est pas un defaut du
contenu de `lea-dev`** : il vient de la feuille de style contre des cadres de cette forme, et il
frappera **tous** les paquets de designation a venir. Il est route ci-dessous.

### Les deux conditions neuves, datees du 2026-09-11 - elles ne valent pas contre ce lot

**Elles repondent au point que `sofia-sm` m'a route** (planning de s34, section 15.2 : *"le plancher
de lisibilite [...] `nadia-prof`"*). Je les ecris comme des conditions **nouvelles et datees**, et non
comme un durcissement de `ZON-9` ou de `ZON-13` : **une barre qu'on releve apres coup fait de ma porte
un caprice.** Elles s'appliquent au prochain passage et a tout paquet de designation ulterieur,
jamais retroactivement au verdict d'aujourd'hui.

> **`ZON-15` - Le libelle d'une zone ne descend pas sous le plancher de lisibilite, et il tient dans
> son cadre sans couvrir le dessin.** Le libelle d'une zone est un texte qu'un enfant de CP doit lire
> pour presser le bon bouton : ce n'est pas un texte de second rang. **Le plancher est de 1,25rem, il
> est de moi, il n'est pas neuf et `ADR-0050` (decision 4) le tient deja sur toute taille declaree par
> la feuille maison.** Aucune geometrie de dessin ne justifie de le baisser : un libelle de 14 px sur
> un meuble etroit ne serait pas un compromis, ce serait un texte qu'un enfant de six ans ne lit pas.
> **Deux conditions s'y ajoutent, parce qu'une hauteur de caractere ne suffit pas** - a la taille
> servie, le libelle (a) **tient entierement dans son cadre**, sans deborder ni toucher une zone
> voisine, et (b) **ne recouvre aucun trait du dessin que sa zone designe**. Si (a) et (b) ne peuvent
> pas tenir ensemble a 1,25rem, **c'est le cadre ou le libelle qui change, jamais la police.**
> **Ce que je ne tranche pas** : par ou passe le remede - agrandir les cadres, sortir le libelle de
> la boite, poser un fond opaque hors du dessin. C'est de la forme, elle appartient a `ines-design`
> qui propose et a `theo-archi` qui en porte le cout. Je donne la propriete, jamais la regle CSS.

> **`ZON-16` - Un libelle positionnel de CP compte au plus trois mots et ne porte qu'un seul repere.**
> *"en haut, au centre"*, *"en bas, a gauche"*, *"le deuxieme, en haut"* passent. *"a gauche, contre
> le mur"* et *"a droite, sur le mur"* ne passent pas : le second repere n'ajoute rien a ce que
> l'enfant doit trouver et double la longueur d'un texte pose sur un meuble de 56 px de large. **Le
> libelle est du texte lu par l'enfant, donc il est a moi** - c'est la moitie de l'obstacle du plan de
> la classe qui m'appartient, et je la rends ici sans toucher a la geometrie.

**Ce que ces deux conditions changent pour `QLM-CP-11`, dit pour que `camille-po` puisse en faire une
story.** Le plancher ne bouge pas : les six cadres du plan (`armoire` 56 x 77 px, `porte` 63 x 56 px)
n'offrent que 16 a 23 px de boîte de contenu apres le pave de `.zone`, donc **aucun libelle n'y tient
a 1,25rem**, meme d'un mot. **La sortie est donc double et les deux moities sont necessaires** : les
cadres a `ines-design` (`ZON-15`), les six libelles a moi (`ZON-16`). Ni l'une ni l'autre seule ne
suffit.

### Ce que je porte au credit de ce lot, et ce n'est pas une politesse

- **La mecanique est jouable de bout en bout, du premier ecran a la fin de partie**, sur la premiere
  livraison, sur trois couches, sans un ecran casse en trois parties completes. `ZON-7` (recevabilite)
  est franchie : ce lot **entre** dans mon audit, ce qu'aucun lot de designation n'avait pu faire.
- **`S33-PAL2-TROU-A` et `S33-PAL2-TROU-B` sont payees, verifiees a l'ecran de fin** : la question
  rappelee est bien celle de la derniere manche (*"Montre le petit-dejeuner."*), et l'ecran ne montre
  ni identifiant de zone ni libelle. **`ADR-0088` clause B tient en pratique et je ne la rouvre pas.**
- **`ZON-11` tel que je l'ai tranche le 2026-09-10 est servi exactement.** Sur la frise du soir,
  l'ordre des boutons est `diner`, `brosse-dents`, `pyjama`, `coucher` - l'ordre de l'axe d'abord,
  puis le haut avant le bas dans la colonne partagee. **La liste ne dit jamais le troisieme avant le
  deuxieme.** Le repli que j'avais ecarte n'a pas ete tente et `ZON-13` est tenue : les deux cadres
  empiles partagent leur abscisse et sont separes de 2 % en ordonnee.
- **`FER-1` est tenue sur la parenthese conjonctive** : *avant*, *apres* et *en meme temps* sont
  chacune demandees, et *en meme temps* - celle que j'avais nommee la plus facile a oublier et la plus
  difficile a dessiner - est servie par deux items et par une geometrie qui la montre.
- **Aucun fait faux n'est enseigne.** L'ordre des moments de la journee et de la soiree est juste,
  aucun enonce ne depasse dix mots, et le vocabulaire (*juste avant*, *juste apres*, *en meme temps*)
  est celui du referentiel.

### Ce dont je reste la seule garde sur ce paquet

| Propriete | Garde executable ? |
|---|---|
| **`ZON-1`, `ZON-3`** - qu'aucun texte servi ne remplace l'image | **aucun**, et le defaut d'aujourd'hui le prouve : le garde-fou 5 d'`ADR-0080` compare l'**enonce** au libelle de la bonne zone et **ne lit pas le texte alternatif** ; aucun test du depot ne le confronte a quoi que ce soit. **C'est moi, en effacant l'image item par item** |
| **`ZON-9`** - qu'un pictogramme se lise a la taille servie | **aucun**, et aucun n'est concevable. **C'est moi, en regardant** |
| **`ZON-12`** - qu'un schema soit exact | **aucun** |
| **`ZON-15` (b)** - qu'un libelle ne couvre pas le dessin | **aucun a ce jour**, et le fait le plus lourd de cet audit est de cette famille |
| **`ZON-8`, `ZON-11`, `ZON-13`, `ZON-14`** | derivables d'un paquet ; **leur forme appartient a `theo-archi`** et je ne prejuge pas si elles sont deja encodees |

### Ce que je route

| Suite | Destinataire | Ce que j'attends |
|---|---|---|
| **`FRI-R1`** - deux textes alternatifs et l'indice de l'item 1 | `lea-dev`, via `sofia-sm` | **Bloquant.** Un texte alternatif qui dit ce que l'image est sans enumerer ses moments dans l'ordre. **Je n'ecris pas les deux textes** (`RES-2`) |
| **`FRI-R2`** - le pictogramme du lever | `ines-design` (forme), via `sofia-sm` | **Bloquant.** *Le pictogramme d'un moment se reconnait a la taille servie, sans son commentaire.* La forme est a elle |
| **`FRI-R3`, `FRI-R4`** | `ines-design` | Non bloquants, a reprendre dans le meme geste que `FRI-R2` |
| **`FRI-R5`** - la revelation d'une zone n'a pas de marque visible | `ines-design` (forme visible), `theo-archi` (si une classe est due) | Non bloquant ici, **mais c'est la reponse a la question qu'`ADR-0088` m'a routee** : *"l'enfant sort-il d'une partie de designation sans savoir ou etait la reponse ?"* - **oui, et je ne rouvre pas la clause B pour autant.** L'ecran de fin n'a pas a porter la zone ; c'est l'ecran de manche, ou le dessin est encore la, qui doit la montrer, et il ne la montre qu'au clavier |
| **Les boutons de zone couvrent le dessin** | `ines-design`, `theo-archi` | **Le fait le plus lourd de cet audit.** Il n'est pas un defaut de ce contenu et il frappera tous les paquets de designation. `ZON-15` (b) le nomme a partir d'aujourd'hui |
| **`ZON-15`, `ZON-16` appliquees a `QLM-CP-11`** | `camille-po`, `ines-design` | Les deux moities de l'obstacle du plan de la classe : les cadres a `ines-design`, les six libelles a moi. **Aucune ne suffit seule** |
| **Le mouvement de matrice** | `camille-po` | **Aucun.** `QLM-CP-14` reste `non couvert`, `QLM-CP-11` reste `non couvert`. Je ne touche pas `MATRICE-couverture.md` |

---

## Bloc de validation pedagogique - nadia-prof UNIQUEMENT - second passage, s35 (`S35-QLM14-FRISE`)

> Ce bloc n'est jamais pre-rempli ni anticipe par un autre agent. **Il s'ajoute au refus du
> 2026-09-11 sans en effacer une ligne** : le premier bloc reste lisible au-dessus, avec ses deux
> motifs. Un refus efface n'a aucune valeur de preuve.

- **Verdict** : **VALIDE SOUS RESERVE** - **premier `VALIDE` de cette fiche**
- **Date** : 2026-09-11 (s35)
- **Auteur** : `nadia-prof`
- **Arbre** : `s35-integration` a `c1b326d4`, worktree `s35-nadia`
- **Ce que ce verdict autorise** : `FRI-R1` et `FRI-R2` sont **levees**. `QLM-CP-14` peut passer
  `couvert-valide`, **et la mention de couverture partielle de la matrice ("moitie
  anteriorite/posteriorite seule") tombe** - `FER-1` etant deja verifiee tenue sur les trois
  relations a s34, je ne la redemande pas.
- **Audit joue** : **oui.** Jar reconstruit depuis mon propre worktree, lance sur le JDK `21.0.10`
  de la toolchain. **Une partie complete de dix manches avec une erreur volontaire sur chacune**,
  pour lire les dix indices servis, plus une passe sur le onzieme item. **Les onze items ont ete vus
  a l'ecran, les deux dessins et leurs deux textes alternatifs ont ete lus tels qu'ils sont servis.**
- **Audit degrade sur un point, et je le declare** : a s34 j'avais rendu l'ecran de manche **dans un
  navigateur**. Cette fois je ne l'ai pas fait. J'ai rendu les deux dessins **a partir de leur
  geometrie**, a la taille servie (640 x 320) et au triple agrandissement, et mesure la place des
  libelles **avec les metriques reelles de la police systeme de l'appareil a 1,25rem gras**, celle
  que la pile `--bs-font-sans-serif` designe faute de `@font-face` servi. **Le controle qui rend
  cette methode credible** : elle reproduit exactement la mesure que j'avais faite au navigateur a
  s34 sur `FRI-R4` - *"le quatrieme"* et *"le cinquieme"* debordent de **2 a 3 px** de leur bouton.
  Deux methodes independantes, le meme chiffre. Je m'appuie dessus sur cette base et pas davantage.

### 1. `FRI-R1` est **levee**, et je dis exactement contre quoi je la mesure

Mon refus portait une phrase : *"Un texte alternatif honnete dit ce que l'image est, pas ce qu'elle
contient dans l'ordre."* **Les deux textes servis aujourd'hui, lus a l'ecran :**

| Dessin | Texte alternatif servi | Dit-il ce que l'image est ? | Enumere-t-il ses moments ? |
|---|---|---|---|
| `frise-journee-ecole` | *"Frise de la journee d'ecole, six moments de gauche a droite."* | oui | **non** |
| `frise-soir-routine` | *"Frise de la routine du soir, quatre moments de gauche a droite."* | oui | **non** |

**La table de correspondance complete a disparu.** Aucune zone n'est nommee dans aucun des deux
textes. Le libelle de la bonne zone n'est plus derivable de la lecture du texte alternatif.

**L'indice de l'item 1** passe de *"C'est le tout premier moment de la journee"* - qui donnait *"le
premier"* mot pour mot - a ***"C'est le moment ou l'on se reveille, avant tout le reste."*** Il ne
reprend plus aucun jeton du libelle. **La clause est tenue a la lettre.**

### 2. La passe image effacee, rejouee sur les onze items - **5 sur 11, contre 9 sur 11**

Mon critere etait chiffre : *"moins de neuf items sur onze se resolvent sans regarder le dessin"*.
**Je l'ai recomptee item par item, avec la seule information dont un enfant dispose quand l'image
est effacee : l'enonce, le texte alternatif, et la liste des libelles de zone.**

| Item | Ce qui est demande | Bonne zone -> libelle | Resoluble sans le dessin ? |
|---|---|---|---|
| 1 | *Montre le lever.* | `lever` -> *le premier* | **oui** - se lever est le premier moment d'une journee, l'enfant le sait de sa vie |
| 2 | *Montre le petit-dejeuner.* | `petit-dejeuner` -> *le deuxieme* | **oui**, de justesse - le repas qui suit le lever |
| 3 | *Montre l'ecole du matin.* | `ecole-matin` -> *le troisieme* | **non** - rien ne dit **quels** six moments la frise porte |
| 4 | *Montre le dejeuner.* | `dejeuner` -> *le quatrieme* | **non**, meme raison |
| 5 | *juste avant l'ecole du matin* | `petit-dejeuner` -> *le deuxieme* | **non** - il faut d'abord placer l'ecole du matin |
| 6 | *juste apres le dejeuner* | `ecole-apres-midi` -> *le cinquieme* | **non** |
| 7 | *juste avant le petit-dejeuner* | `lever` -> *le premier* | **oui** - ce qui precede le petit-dejeuner est le premier moment |
| 8 | *Montre le diner.* | `diner` -> *le premier* | **oui** - premier moment d'une routine du soir |
| 9 | *Montre le coucher.* | `coucher` -> *le troisieme* | **oui** - le dernier libelle de la liste |
| 10 | *en meme temps que se brosser les dents* | `pyjama` -> *le deuxieme, en bas* | **non** - deux candidats sur quatre, sans moyen de choisir. Un tirage a pile ou face n'est pas une resolution |
| 11 | *en meme temps que mettre son pyjama* | `brosse-dents` -> *le deuxieme, en haut* | **non**, meme raison |

**Cinq sur onze.** Le critere est tenu, et largement.

**Et je tranche la lecture du residu plutot que de la laisser ouverte, parce qu'elle compte.** Les
cinq items qui restent resolubles sans le dessin le sont **parce que l'enfant connait la chronologie
de sa propre journee** et que les libelles sont des rangs. **Ce n'est plus une contrefacon : c'est
`QLM-CP-14` elle-meme**, exercee sur des rangs. A s34 l'enfant lisait la reponse dans un texte ;
aujourd'hui il la deduit d'un savoir qu'on veut justement lui faire travailler. **La difference
n'est pas de degre, elle est de nature**, et c'est pour cela que je leve.

`ZON-10` n'est pas amendee, comme la story le prevoit, et je ne rouvre pas les libelles positionnels
de ce paquet.

### 3. `FRI-R2` est **levee** - je trouve le lit

Mon refus decrivait ce que je voyais : *"un rectangle large avec un petit rectangle et un trait, plus
un trait vertical detache qui deborde au-dessus. Je ne trouve pas le lit."*

**Regarde a la taille servie puis au triple agrandissement, le pictogramme reforme donne** : un
montant vertical haut et etroit a gauche, **ferme et accole** au sommier, tous deux posant sur la
meme base ; un oreiller en petit rectangle arrondi pose sur le sommier contre le montant, et un pli
de couverture en trait a l'interieur du sommier. **C'est la silhouette classique d'un lit vu de cote,
et je la reconnais aux deux tailles.** Les trois defauts que j'avais nommes sont tous corriges : le
trait detache est devenu un montant ferme, il ne deborde plus au-dessus, et il partage la base du
sommier au lieu de flotter.

L'item 7, qui vise la meme zone par une relation, n'est pas concerne et n'a pas ete retouche.

### 4. Ce que j'ai regarde parce que `theo-archi` me l'a route, et ma reponse : **oui, c'est un probleme reel, non, il n'est pas bloquant**

`theo-archi` signale (confirmation ArchUnit de s35) que l'item 9 porte l'indice *"C'est le tout
dernier moment de la journee"* alors que le libelle de sa bonne zone est *"le troisieme"*, et que le
garde-fou d'`ADR-0091` laisse passer ce cas, aucun jeton du libelle n'etant repris litteralement.
**Je l'ai joue.**

**Ce que j'y trouve n'est pas celui des deux defauts qu'il soupconnait, et c'est pire nomme
autrement.** Le probleme n'est pas que l'indice donne la reponse par paraphrase - c'est qu'il **nomme
un rang dans une echelle que le dessin n'offre pas**. La frise du soir porte **quatre moments** et
**trois places** sur l'axe, parce que deux moments sont empiles a la meme place. Un enfant qui vient
de compter quatre dessins s'entend dire *"le tout dernier"* et cherche un quatrieme bouton : il n'en
existe pas, le dernier libelle est *"le troisieme"*. **Deux systemes de comptage se croisent sur le
meme ecran.**

**Pourquoi ce n'est pas bloquant** : l'indice n'arrive qu'apres une erreur, la bonne zone est
presente et atteignable, l'enfant qui presse *"le troisieme"* obtient *"Bravo"*, et l'accroc est un
temps d'hesitation, pas une reponse fausse. **L'item 9 n'etait pas dans le perimetre de mon refus et
je ne l'y ajoute pas apres coup** ; je le date comme une reserve nouvelle.

> **`FRI-R7`** - **reserve neuve, datee du 2026-09-11 (s35), non bloquante.**
>
> - **Objet** : *sur la frise du soir, un indice ne nomme pas un rang que les libelles ne portent
>   pas.* L'indice de l'item 9 dit *le tout dernier* la ou le dernier libelle est *le troisieme*, sur
>   un dessin de quatre moments.
> - **Mode de defaillance** : **renumeroter les libelles de la frise du soir** pour que le coucher
>   devienne *"le quatrieme"*. Cela detruirait la relation *en meme temps* que le partage du rang 2
>   encode, et rouvrirait l'arbitrage que j'ai rendu le 2026-09-10 sur `ZON-11`. **Le geste est du
>   cote de l'indice** : dire le fait du monde (*"c'est le moment ou l'on s'endort, apres tout le
>   reste"*) plutot qu'un rang.
> - **Perimetre, mesure sur l'etat d'arrivee** : **un indice, sur un item.**
> - **Regime et prix** : **mot pour mot, 0,25.**

### 5. `FRI-R6` - **reserve neuve, datee du 2026-09-11 (s35), non bloquante** : le texte alternatif de la frise du soir decrit une disposition qui n'est pas celle du dessin

**C'est le point le plus lourd de cet audit, et il est de ma main : mon propre critere demandait
*"une frise de tant de moments, disposee ainsi"*. Le nombre est juste ; la disposition est fausse.**

**Mesure, sur le dessin servi et sur son texte.** `frise-soir-routine.svg` porte **trois graduations**
sur l'axe du temps - le diner, puis une seule place partagee par la brosse a dents et le pyjama
empiles, puis le coucher. **Quatre moments, trois places, deux d'entre eux l'un au-dessus de
l'autre.** Le texte alternatif servi dit : *"Frise de la routine du soir, quatre moments de gauche a
droite."*

**Quatre moments de gauche a droite decrit une file de quatre places. Ce n'est pas le dessin.** Et
l'ecart ne tombe pas n'importe ou : **il tombe exactement sur la relation que les items 10 et 11
testent.** Un enfant qui travaille sur ce texte - parce qu'il ne voit pas, ou parce que l'adulte qui
l'accompagne le lui lit, ce que la premisse du produit prevoit - s'entend decrire une succession la
ou l'exercice porte sur la simultaneite. **On lui donne le contraire de ce qu'il doit trouver.**

- **Objet** : *le texte alternatif d'un dessin de frise dit la disposition reelle du dessin, y
  compris quand deux moments partagent une place sur l'axe.*
- **Mode de defaillance** : **nommer les deux moments empiles** pour dire leur partage, ce qui
  rouvrirait `FRI-R1` et violerait la clause 1 d'`ADR-0091`. **Le partage se dit sans nommer
  personne** - *"trois places sur l'axe ; a la place du milieu, deux moments l'un au-dessus de
  l'autre"* nomme zero zone.
- **Perimetre, mesure sur l'etat d'arrivee** : **un texte alternatif**, repete sur les quatre items
  du dessin du soir. Le texte de la frise d'ecole est juste et ne bouge pas.
- **Pourquoi je ne refuse pas dessus, et je l'ecris pour qu'on puisse me le reprocher** : `FRI-R1`
  etait une reserve sur **la table de correspondance**, c'est-a-dire sur le fait que le texte donnait
  la reponse. **Cette dishonnetete-la a disparu.** Ce qui reste est une inexactitude de description,
  sur un canal que l'enfant voyant n'emprunte pas, et refuser dessus laisserait `QLM-CP-14` non
  couverte pour un cinquieme sprint alors que les deux motifs que j'avais nommes bloquants sont tous
  les deux payes. **Mais je le dis sans detour : si cette phrase etait un enonce et non un texte
  alternatif, elle serait un refus a elle seule, parce qu'une erreur factuelle dans un contenu
  scolaire en est un.** Elle est a payer avant que la formule ne soit recopiee sur un paquet de
  designation de plus.
- **Regime et prix** : **mot pour mot, 0,25.**

### 6. Ce que je verifie et qui ne fonde aucune demande

- **Les onze indices, exactitude et niveau** : justes, 9 a 14 mots, aucun mot hors de portee d'un CP.
- **Les trois relations de `FER-1`** : *avant* (items 5, 7), *apres* (item 6), *en meme temps* (items
  10, 11). Rejouees, toutes trois servies, et la geometrie **montre** la simultaneite. `FER-3` : six
  et cinq items.
- **`ZON-11`** : l'ordre des boutons dans le formulaire est celui de l'axe puis du haut vers le bas
  dans la colonne partagee - exactement ce que j'ai tranche le 2026-09-10. Inchange.
- **`ZON-13`** : aucun chevauchement de cadres, verifie sur les deux dessins. L'intervalle de 2 %
  entre les deux cadres empiles tient.
- **`ZON-14`** : un item, un geste.
- **L'ecran de fin de partie** porte la question qu'il commente et ne montre ni identifiant ni
  libelle de zone (`ADR-0088`, clauses A et B). Verifie a l'ecran : *"Bravo, c'est juste ! / Montre
  le lever."*
- **Les deux nombres de l'ecran de fin** : justes sur cette mecanique aussi. Verdict separe dans
  `PASSE-TRANSVERSE-s35-ecran-de-fin-de-partie.md`.
- **`FRI-R4`** : remesure, 2 a 3 px de debordement sur *"le quatrieme"* et *"le cinquieme"*, chiffre
  identique a celui de s34. **Reserve inchangee, non bloquante, non rouverte.**

### 7. Ce que je ne compte pas, pour ne pas deplacer mes poteaux

- **`ZON-15` ne s'applique pas a ce paquet, et je le dis parce que j'aurais pu m'en servir.** Je
  l'ai ecrite le 2026-09-11 en declarant explicitement *"elles ne valent pas contre le lot
  d'aujourd'hui"*, et la story de s35 la reserve au plan de la classe (*"elle ne touche pas la
  geometrie des cadres ni la taille des libelles du plan de classe - story separee"*). **Les
  libelles de la frise recouvrent bien des traits des pictogrammes qu'ils designent - je l'ai vu -
  et je ne l'oppose pas a ce lot.** Ce sera opposable au prochain lot de frise, pas a celui-ci.
- **`FRI-R3`** (le bol du diner ne porte pas le repas du soir) et **`FRI-R5`** (la revelation apres
  trois essais n'est qu'un deplacement de focus) restent ouvertes, non bloquantes, hors perimetre.
- **Le recouvrement general des boutons de zone sur le dessin** reste le defaut transverse route a
  `ines-design` et `theo-archi`, borne par `ADR-0092`. Je ne le compte pas ici.

### 8. Ce dont je reste la seule garde sur ce paquet

| Propriete | Garde executable ? |
|---|---|
| **Le texte alternatif ne nomme aucune zone** (`ADR-0091`, clause 1) | **garde, et mis a l'epreuve sur le fichier de production** par `yanis-qa` (rapport de s35, section 3.1 : rouge sur le fichier remis a son etat fautif, vert restaure). **Je ne suis plus seule garde de ce point**, et je le porte au credit du lot |
| **L'indice ne reprend aucun jeton distinctif du libelle** (`ADR-0091`, clause 2) | **garde, meme mise a l'epreuve.** Mais il compare des **jetons** : *"avant tout le reste"* pour *"le premier"*, *"le tout dernier"* pour *"le troisieme"*, **passent tous les deux**. **Je reste seule garde de la paraphrase**, comme je l'avais annonce a s29 |
| **L'exactitude du texte alternatif contre le dessin** (`FRI-R6`) | **aucun.** Aucun test ne lit un dessin. C'est moi, en regardant |
| **La passe image effacee** - combien d'items se gagnent sans le dessin | **aucun**, et `ADR-0091` le dit lui-meme. `yanis-qa` le declare comme limite de mesure (section 6 de son rapport). **C'est moi, en jouant, et le chiffre de cet audit est 5 sur 11** |
| **Qu'un pictogramme se lise** (`ZON-9`) | **aucun.** C'est moi, en regardant, a la taille servie et au triple agrandissement |

### 9. Ce que je route

| Suite | Destinataire | Ce que j'attends |
|---|---|---|
| **Le mouvement de matrice** | `camille-po` | **`QLM-CP-14` -> `couvert-valide`**, et **la mention de couverture partielle tombe** |
| **`FRI-R6`** - le texte alternatif de la frise du soir | `camille-po` | Non bloquante mais **prioritaire sur les autres reserves de ce paquet** : la formule sera recopiee sur le prochain paquet de designation. **Je n'ecris pas le texte** (`RES-2`), je dis ce qu'il doit rendre |
| **`FRI-R7`** - l'indice de l'item 9 | `camille-po` | Non bloquante |
| **`FRI-R3`, `FRI-R4`, `FRI-R5`** | `camille-po` | Inchangees, ouvertes, non bloquantes |
| **La paraphrase qu'`ADR-0091` clause 2 ne voit pas** | `theo-archi`, pour information | **Je ne demande pas de garde-fou** : `ADR-0091` dit lui-meme ce qu'il ne garde pas. Je demande qu'on n'ecrive nulle part que cette clause couvre la paraphrase |
