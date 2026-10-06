# Fiche de conformité - designation-symetrie-ce1

- **Mécanique** : désignation (`designation`, forme d'item `ZoneADesigner`) ; aucune mécanique ni forme
  d'item neuve.
- **Contenu(s)** : à créer, avec `illustration` et `illustrationTexteAlternatif` non vide sur chaque item.
  Story `S51-MAT-SYMETRIE` (`docs/backlog/backlog.md`), décisions `DEC-0110` (sections 1 et 5) et `DEC-0111`
  (section 5). Développeur de paquet et de dessin : `lea-dev` (planning de s52). Nom de fichier de contenu et
  identifiant d'entrée de catalogue fixés par le développeur au premier commit ; le nom de cette fiche est
  proposé par `camille-po` et renommé si l'identifiant diffère.
- **Statut global** : en construction - **non engagée à s52** : `MAT-CE1-17` est non engagée (budget 1,5
  libéré, destination s53, `squad/sprints/s52-deux-instances-ce1-francais-maths/planning.md`). Cette fiche
  est ouverte pour qu'une première livraison, si U4 la permet, ne trouve pas le dépôt sans fiche. Elle ne
  re-engage rien. Conditions suspensives non remplies à l'ouverture : condition (1) moitié message du
  cliquet (U1) et condition (2) quadrillage 6 x 6 et légende à 36 libellés (U2, U3), `DEC-0111` section 5.2
  et son amendement A.2.
- **Dernière mise à jour** : 2026-10-06 (ouverture, `camille-po`, s52).

## Compétences visées

| Matière | Niveau | Domaine | Compétence | Référence au référentiel |
|---|---|---|---|---|
| mathématiques | CE1 (cycle 2) | Espace et géométrie | Reconnaître un axe de symétrie et compléter une figure par symétrie sur quadrillage | `squad/programme/mathematiques/competences-ce1.md`, `MAT-CE1-17` |

Arbitrage préalable : `squad/conformite/ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-3.md`, section 3,
verdict **accepté sous conditions `SYM-CE1-1` à `SYM-CE1-3`** ; `couvert-valide` atteignable avec une
mention. Bornes de grille `DEP-CE1-2` (même fichier, section 1).

## Critères pédagogiques attendus - camille-po

- [ ] Mécanique `designation`, forme d'item `ZoneADesigner` ; `nombreDeManches` : 8 (`DEC-0111` section
  5.4).
- [ ] Item de reconnaissance d'axe : l'énoncé ne décrit pas la propriété (*"qui partage la figure en deux
  parties superposables"*) ; la réponse est entièrement dans le dessin (`SYM-CE1-1`).
- [ ] Au moins la moitié des items de reconnaissance d'axe portent une ligne candidate qui coupe la figure en
  deux parties de même aire sans être un axe (`SYM-CE1-2`).
- [ ] Item de complétion : axe tracé sur un quadrillage de 4 x 4 à 6 x 6 cases de même taille, toutes
  désignables, exactement une case manquante de l'autre côté de l'axe, et la case attendue n'est pas la
  seule case vide touchant la figure (`SYM-CE1-3`, `DEP-CE1-2`).
- [ ] *Reconnaître un axe* et *compléter par symétrie* portent chacun au moins trois items ; au moins dix
  items servent la ligne ; le paquet porte strictement plus d'items que de manches (`FER-2`, `FER-3`,
  `DEC-0064`).
- [ ] Chaque item porte au moins trois zones (`ZON-8`) ; deux zones d'un même item ne se chevauchent pas
  (`ZON-13`) ; libellés de zone de la même forme, aucun ne nomme la cible (`ZON-10`) ; l'énoncé demande une
  seule désignation (`ZON-14`).
- [ ] La bonne zone n'est ni toujours la première ni toujours la dernière déclarée ; ni l'énoncé ni
  l'indice ne nomment le libellé de la bonne zone.
- [ ] Zones de désignation d'au moins 16 unités de côté (plancher d'`ines-design`, 296 px à 320 px de
  fenêtre ; amendement A2bis d'`ADR-0080` attendu, U1).
- [ ] Mention de couverture reprise mot pour mot dans la matrice : *"reconnaitre un axe de symetrie, et
  completer une figure par symetrie a raison d'une case par item ; completer une figure a plusieurs cases
  reste non couvert, aucune mecanique ne recoit une suite de gestes dans une meme manche"* (graphie de la
  source, `docs/backlog/backlog.md`).
- [ ] Texte servi à l'enfant intégralement accentué, orthographe et ponctuation irréprochables (`DEC-0002`).

---

## Bloc de validation pédagogique - nadia-prof UNIQUEMENT

> Ce bloc n'est jamais pré-rempli ni anticipé par un autre agent.

### Verdict du 2026-10-06 (`nadia-prof`, s52, instance C) : **VALIDE SOUS RESERVE** - historique : `SYM-CE1-R1` levee au second passage ci-dessous

**Done pedagogique prononce.** `camille-po` peut passer `MAT-CE1-17` a `couvert-valide`, avec la mention
fixee d'avance (arbitrage de s43, lot 3, section 3), mot pour mot dans la matrice : *"reconnaitre un axe
de symetrie, et completer une figure par symetrie a raison d'une case par item ; completer une figure a
plusieurs cases reste non couvert, aucune mecanique ne recoit une suite de gestes dans une meme manche"*.
La reserve `SYM-CE1-R1` ne bloque pas le mouvement de ligne.

#### 1. Ce que j'ai fait

**Audit joue.** Arbre `s52-nadia-instance-c` a `fb1c7ad3`, propre ; aucun Maven en cours (liste des
processus). Jar construit une fois (`bash outils/mvn-jdk21.sh -B -DskipTests package`), copie dans mon
scratchpad sous un nom portant le sha, lance depuis la copie sur le JDK 21, port 18871.

**Huit parties completes de huit manches et une partielle, appels HTTP reels** : six en pressant les
zones dans l'ordre servi jusqu'a la resolution (erreur, deuxieme erreur, revelation vues sur tout le
paquet), une sans faute (ecran de fin), une en pressant d'abord toutes les zones fausses, et une partielle
arretee sur la revelation d'une grille. **Les 11 items ont ete tires**, de 3 a 6 fois chacun sur les six
premieres parties.

**Zones masquees (`ZON-9`), sur les onze dessins, avant de lire le paquet.** Chaque manche captee dans la
page reelle (gabarit, feuille de style et fragment servis par le jar), rendue a 320 px de fenetre
(`outils/mesurer-rendu.sh 320`, Chrome headless), l'image de designation servie a 296 px : une fois avec
les zones et la legende cachees, une fois avec. Sur le rendu masque j'ai designe l'axe ou la case, puis
revele : **sur les onze items, la bonne zone declaree couvre l'endroit que j'avais trouve**, et le jeu la
credite. Je n'ai lu le YAML et les SVG qu'apres. Mon script affichait les enonces seuls ; l'identifiant de
la zone juste n'a ete lu qu'a la revelation.

| Dessin | Ce que j'ai trouve, zones masquees | Zone juste servie |
|---|---|---|
| figure a, rectangle | la verticale du milieu | onglet en haut |
| figure b, triangle | la verticale par le sommet | onglet en bas |
| figure c, carre | la verticale du milieu (l'autre verticale est decalee) | onglet en bas |
| figure d, maison | la verticale par le toit | onglet en haut |
| figure e, rectangle debout | l'horizontale du milieu (la verticale est decalee) | onglet au milieu, a gauche |
| figure f, cerf-volant | l'horizontale par les deux pointes laterales | onglet au milieu, a gauche |
| grille a, 6 x 6, axe vertical | colonne 4, ligne 1 | `case-1-4` |
| grille b, 6 x 6, axe horizontal | colonne 2, ligne 4 | `case-4-2` |
| grille c, 4 x 4, axe vertical | colonne 3, ligne 4 | `case-4-3` |
| grille d, 6 x 6, axe vertical | colonne 4, ligne 4 | `case-4-4` |
| grille e, 4 x 4, axe horizontal | colonne 3, ligne 4 | `case-4-3` |

**Je n'ai hesite sur aucun item entre un leurre et l'axe.** Ma regle (reponses U3, section 3.1) ne
declenche donc aucun refus.

#### 2. Ancrage au programme

`MAT-CE1-17` : *"Reconnaître un axe de symétrie et compléter une figure par symétrie sur quadrillage."*
Les deux verbes sont servis, sur dessin : six reconnaissances, cinq completions. **C'est bien cette
competence** : aucun enonce ne decrit la propriete, aucun ne se resout en comparant deux moities sans
penser au pliage (`SYM-CE1-2`). La completion est retrecie a une case par item, ce que la mention dit.

#### 3. Conditions, et les doutes de `lea-dev`

| Condition | Tenue | Ce que j'ai verifie |
|---|---|---|
| `SYM-CE1-1` | **oui** | *"Touche le numéro de l'axe de symétrie."* : l'enonce nomme l'axe sans le definir ; la reponse est dans le dessin |
| `SYM-CE1-2` | **oui** | 4 figures sur 6 portent une ligne de meme aire qui n'est pas un axe, recalculee sur les SVG apres le jeu : diagonale du rectangle (a, e), mediane du triangle issue d'un sommet de la base (b), droite oblique par le centre du carre (c). Une seule ligne par item est un axe |
| `SYM-CE1-3` | **oui** | Une seule case manque sur chaque grille ; la case attendue n'est jamais la seule candidate qui touche la figure (verifie sur les cinq) ; axes vertical et horizontal servis |
| `DEP-CE1-2`, bornes | **oui** | 4 x 4 et 6 x 6. Le 5 x 5 est exclu, et c'est juste : l'axe doit tomber sur une ligne de grille, il faut un nombre pair de colonnes |
| `ZON-8` | **oui** | 3 zones par figure, 4 par grille |
| `ZON-10` | **oui** | Libelles positionnels sur tout le paquet (*"la ligne qui finit en haut"*, *"colonne 4, ligne 1"*), aucun ne nomme la cible ni ne juge la ligne |
| `ZON-11` | **non** | Voir `SYM-CE1-R1` |
| `ZON-13` | **oui** | Aucun chevauchement ; sur les six figures, chaque onglet recoit l'extremite de sa ligne et aucun ne contient un segment d'une autre ligne (verifie sur le rendu revele, puis sur les coordonnees) |
| `ZON-14` | **oui** | Un geste par manche |
| Plancher de zone (`DEC-0111` A.7) | **oui** | 16 unites sur les figures et les grilles 6 x 6 (47,4 px a 296), 24 sur les 4 x 4 (71 px) |
| Rang de la bonne zone | **oui** | Premiere declaree 2 fois sur 11, derniere 2 fois sur 11 |
| `FER-2`, `FER-3` | **oui** | 11 items, 8 manches ; deux taches de 6 et 5 items |

**Candidates du quadrillage** (mes reponses U3, section 3.2), verifiees grille par grille :

| Grille | Attendue | Glissement | Voisine | Autre case qui touche la figure |
|---|---|---|---|---|
| a | 1-4 | 1-6 | 1-5 | 2-6 |
| b | 4-2 | 6-2 | 5-2 | 5-1 |
| c | 4-3 | 4-4 | 3-3 | 1-4 |
| d | 4-4 | 4-6 | 4-5 | 1-4 |
| e | 4-3 | 3-3 | 4-4 | 3-1 |

(ligne-colonne.) Sur les 4 x 4, le glissement et la voisine a un cran de l'axe tombent sur la meme case ;
`lea-dev` prend alors la voisine a un cran sur l'autre direction (3-3 en c, 4-4 en e). C'est loyal :
c'est encore une case qu'on obtient en comptant mal d'une case. Quatre candidates sur les 4 x 4, sur 16
cases, n'est pas trop serre.

**Leurres paralleles** : 8 unites de l'axe sur les rectangles a et e (23 px a 296), 14 sur le carre c
(41 px). Je les ai vus decales sans mesurer, zones masquees. Loyaux.

**Le leurre le plus proche d'un axe, dit plutot que tu** : la mediane du triangle b. Le triangle a une base
de 52 unites et deux cotes de 58 ; la mediane coupe le cote oppose a 97 degres. Un triangle equilateral
en ferait un axe. Ici il ne l'est pas, le triangle est visiblement plus haut que large, et la verticale
par le sommet est l'axe evident. Je n'ai pas hesite ; je l'ecris pour que le prochain dessin de triangle
ne s'approche pas davantage de l'equilateral.

**Onglet au bout de la ligne, mes deux conditions U3** : tenues. La consigne et l'enonce demandent de
toucher le numero (*"Regarde bien le dessin, puis touche le bon numéro."*, *"Touche le numéro de l'axe de
symétrie."*). L'onglet de l'axe est en haut (a, d), en bas (b, c), a gauche au milieu (e, f).

**Libelles des lignes** : ils disent ou finit la ligne, jamais ce qu'elle est. Recevable.

**Indices** : *"Imagine que tu plies la figure le long de chaque ligne."* donne la methode du pliage, sans
la reponse. *"Compte les cases entre la case pleine et le trait épais, puis compte autant de l'autre
côté."* donne la methode de la reflexion case a case ; il suppose que l'enfant a reconnu la case pleine
sans partenaire, ce qui est le debut de la tache, et il marche aussi quand cette case touche l'axe
(zero case, la case juste a cote). Les deux s'affichent des la premiere erreur et restent a la
revelation.

**Legende** : 3 lignes sur les figures, 4 sur les grilles.

#### 4. Niveau reel

Consigne de 9 mots, enonces de 7 et 10 mots, une phrase. Dessins a 296 px, trait de la figure epais,
lignes candidates tiretees de meme trait : rien ne designe l'axe. Sur les grilles, l'axe epais reste
visible entre deux cases pleines. Sur une erreur, *"Ce n'est pas ça. Essaie encore !"* et l'indice ; a la
revelation, la bonne zone recoit le focus et les zones essayees sont marquees. L'enfant bloque avance.

Le mot *ligne* designe une ligne tracee sur les figures et une rangee de cases dans la legende des grilles.
Les deux sens ne se rencontrent jamais sur le meme ecran, et *ligne* pour une rangee est le mot de la
classe. Je le note sans en faire une reserve.

#### 5. Exactitude, biais et ton

Onze reponses exactes, verifiees au rendu puis sur les coordonnees : chaque axe declare est un axe, chaque
leurre n'en est pas un, chaque case attendue est l'image de la case orpheline. Aucun personnage, aucun
contexte. Ton neutre. Les textes alternatifs sont ceux que j'avais proposes.

#### 6. La reserve

> **`SYM-CE1-R1` - Les zones ne sont pas declarees dans l'ordre de lecture de l'image (`ZON-11`), sur
> les onze items.** Les numeros que l'enfant voit sur le dessin et l'ordre de tabulation suivent l'ordre
> de declaration, qui saute d'un bord a l'autre. Sur la grille a, la premiere rangee porte, de gauche a
> droite, *2, 3, 1*, et le *4* en dessous ; sur la figure a, le *1* est en bas a droite, le *2* en haut, le
> *3* a gauche. Au clavier, l'enfant traverse les zones dans cet ordre.
>
> **Correction a faire** : sur chaque item, declarer les zones de haut en bas puis de gauche a droite,
> sur le coin superieur gauche de leur cadre. Ni les cadres ni les libelles ne changent. Recompte fait
> sur le paquet servi : dans cet ordre, la bonne zone serait premiere 4 fois sur 11 et derniere 3 fois
> sur 11 ; si la troisieme clause de `LaBonneZoneNEstPasPredictibleSurUnPaquetTest` rougissait quand meme,
> c'est un onglet ou une candidate qui se deplace, jamais l'ordre de lecture qui se sacrifie.
>
> **Ce n'est pas une barre neuve** : `ZON-11` est ecrite depuis s29 et vaut pour tout paquet de
> designation. Elle n'est pas encodee (`ADR-0080`, A2, section 3), et elle reste dans ma main.
>
> **Pourquoi c'est une reserve et pas un refus** : `ZON-11` porte sur la navigation et la coherence des
> numeros, pas sur ce que l'enfant apprend. Aucun item n'enseigne quelque chose de faux, et un numero hors
> d'ordre ne cache aucune zone. C'est la lecture que j'ai faite a s34 sur la frise (*"rien de faux n'est
> enseigne"*). La correction est un reordonnancement de declarations, sans dessin a refaire.

#### 7. Ce dont je reste la seule garde

`PaquetCe1SymetrieTest` et `LesFiguresDeSymetrieMontrentCeQueLeursItemsAffirmentTest` existent (lus par
leur nom, non executes par moi) ; A2 tient le non-chevauchement et le rang. **Aucun automate ne verifie** :
que l'axe se trouve sur le dessin seul a la taille servie et que la bonne zone le couvre (`ZON-9`), qu'un
leurre se distingue de l'axe sans mesurer, qu'une candidate soit une erreur qu'un enfant fait, que
l'ordre de declaration soit l'ordre de lecture (`ZON-11`), et que le plancher de 44 px tienne sur les
items autres que la premiere manche (jusqu'a A3). Ces proprietes n'ont ete verifiees qu'ici, en jouant.

> **`MNC-1`** - Ce Done ne couvre pas que l'enfant voie, sans faire defiler la page, un element
> quelconque du jeu, y compris le retour qui suit sa reponse - verdict, indice, proposition. Cette
> propriete est sortie de la porte pedagogique par arbitrage de l'utilisatrice du 2026-08-28
> (`DEC-0053`) ; sa dette est ouverte, croissante et assumee. Aucune mesure ne l'a instruite sur ce
> paquet.

**Porte technique** : non lancee par moi. Ce Done pedagogique suppose la CI verte sur l'arbre fusionne ;
`sofia-sm` la verifie a la fusion.

---

### Second passage du 2026-10-06 (`nadia-prof`, s52, instance C) : **VALIDE**, `SYM-CE1-R1` levee

**Je ne rouvre pas le paquet entier** : je juge la correction de `lea-dev` (`bc7cf6a0`) contre la barre
que j'ai ecrite dans `SYM-CE1-R1`, et je verifie que rien d'autre n'a bouge. `MAT-CE1-17` reste
`couvert-valide` ; la mention de couverture est inchangee, mot pour mot celle du premier passage. La
reserve tombe. Le premier verdict et le texte de `SYM-CE1-R1` restent ci-dessus, en historique.

#### 1. Ce que j'ai fait

Arbre `s52-nadia-instance-c` a `bc7cf6a0`, aucun processus Java ni Maven en cours avant la construction.
Jar construit une fois (`bash outils/mvn-jdk21.sh -B -DskipTests package`), copie dans mon scratchpad sous
un nom portant le sha, lance depuis la copie sur le JDK 21, port 18872.

**Ce qui a change, lu sur le depot** : `git diff --stat HEAD~2 HEAD` touche `ce1-symetrie.yaml` et deux
tests (`173b48f7`, fins de ligne) ; **aucun SVG**. Comparaison item par item du YAML de `HEAD~2` et de
`HEAD`, par script de poste : en-tete identique ; sur les 11 items, enonce, illustration, texte
alternatif, `bonneZone` et indice identiques, et **le meme ensemble de zones** (identifiant, libelle,
cadre). Seul l'ordre de declaration a change.

**Sept parties completes de huit manches** sur le jar a `bc7cf6a0`, zones pressees dans l'ordre servi
jusqu'a la resolution : les 11 items tires, de 3 a 6 fois chacun. Chaque manche captee et rendue a 320 px
de fenetre, zones masquees puis revelees.

**Limite, dite** : les dessins sont les memes octets qu'au premier passage, et je connaissais les
reponses. Mon second regard zones masquees n'est donc pas aveugle ; il confirme que les dessins servis
sont ceux que j'ai juges. **Sur les 11 items, la bonne zone servie est celle que j'avais trouvee au
premier passage**, et le jeu la credite.

#### 2. La barre de `SYM-CE1-R1`, contre ce qui est servi

| Propriete | Tenue | Ce que j'ai verifie |
|---|---|---|
| Numeros vus dans l'ordre de lecture | **oui, 11 items sur 11** | Sur le fragment servi, les numeros 1 a 3 (figures) ou 1 a 4 (grilles) suivent l'ordre du haut vers le bas puis de la gauche vers la droite des cadres. Grille a : *1, 2, 3* de gauche a droite sur la premiere rangee, *4* en dessous. Figure a : *1* en haut, *2* a gauche, *3* en bas a droite |
| Ordre de tabulation | **oui** | Les boutons de zone n'ont pas de `tabindex` : la tabulation suit leur ordre dans le document, qui est celui des numeros |
| Legende | **oui** | Les libelles de la legende sont, dans l'ordre, ceux des boutons 1, 2, 3 (4) |
| Cadres, libelles, identifiants, dessins | **inchanges** | Voir la section 1 |

**Rang de la bonne zone**, recompte sur le paquet servi : premiere 4 fois sur 11, derniere 3 fois sur 11,
le chiffre que j'avais annonce dans la reserve. Ni toujours la premiere ni toujours la derniere.

#### 3. Mes autres conditions

Rien de ce qu'elles jugent n'a bouge : memes dessins, memes cadres, memes libelles, memes enonces, memes
indices, memes bonnes zones. `SYM-CE1-1` a `-3`, `DEP-CE1-2`, `ZON-8`, `-10`, `-13`, `-14`, le plancher de
zone et `FER-2`/`FER-3` tiennent comme au premier passage. `ZON-11` tient desormais.

#### 4. Ce dont je reste la seule garde

Inchange par rapport au premier passage. **`ZON-11` n'est toujours pas encodee** (`ADR-0080`, A2, section 3). Le jour ou un item est ajoute ou
reordonne, rien d'automatique ne verra un ordre de declaration qui n'est plus l'ordre de lecture. Je
reste la seule garde de cette propriete, avec `ZON-9`, les leurres, les candidates et le plancher de
44 px hors de la premiere manche.

> **`MNC-1`** - Ce Done ne couvre pas que l'enfant voie, sans faire defiler la page, un element
> quelconque du jeu, y compris le retour qui suit sa reponse - verdict, indice, proposition. Cette
> propriete est sortie de la porte pedagogique par arbitrage de l'utilisatrice du 2026-08-28
> (`DEC-0053`) ; sa dette est ouverte, croissante et assumee. Aucune mesure ne l'a instruite sur ce
> paquet.

**Porte technique** : non lancee par moi. Ce Done pedagogique suppose la CI verte sur l'arbre fusionne ;
`sofia-sm` la verifie a la fusion.
