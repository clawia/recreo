# Fiche de conformité - bandit-manchot-decrire-une-oeuvre-cp

- **Mécanique** : bandit manchot.
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/cp-decrire-une-oeuvre.yaml`
  (8 items, type `question`, une œuvre par item).
- **Entrée de catalogue** : `bandit-manchot-decrire-une-oeuvre-cp`, titre servi *"Décrire une
  œuvre"*, consigne servie *"Regarde l'œuvre, puis dis ce que tu vois."*, `nombreDeManches: 7`.
- **Ligne déclarée** : `ART-CP-09` (`lignesDeReferentiel`), sans les couleurs (`ART-1`).
- **Story** : `S50-ART`, livrée par `marc-dev` (`1db6348f`), en-tête du paquet corrigé par le
  `relecteur-pedagogique` (`655b319b`). Done technique prononcé par `yanis-qa` (`bfb8e3c4`).
- **Arbitrage préalable de `nadia-prof`** : `ARBITRAGE-PREALABLE-s50-art-cp.md` (2026-10-04) - Q4
  (sans couleurs, règles pour le développeur), Q2 (texte visible dans une reproduction),
  transposition de `REC-1` à `REC-4`, cadre du marché aux chevaux.
- **Statut global** : **REFUSÉ**, premier passage, le 2026-10-04, par `nadia-prof` (instance B,
  `DEC-0109` section 3), **après avoir joué le jar servi**. Trois motifs, tous corrigeables dans le
  seul fichier de contenu (section 9).
- **Dernière mise à jour** : 2026-10-04 (premier audit, s50).

---

## 1. Ce que j'ai joué, et comment

**Audit non dégradé.** Arbre `round-two` à `655b319b`. Jar construit par
`bash outils/mvn-jdk21.sh -B -pl bootstrap -am package -DskipTests` (JDK 21.0.10 rapporté par
l'enveloppe), lancé sur un port dédié servi par mon propre processus. Avant de jouer, j'ai vérifié
que le paquet YAML et les huit fichiers `static/oeuvres/*.jpg` contenus dans le jar sont
identiques à l'octet à ceux de l'arbre. Aucun job de la CI lancé par moi : le Done technique est
celui de `yanis-qa`.

**Parties jouées : 12, soit 84 manches**, chaque œuvre tirée entre 10 et 12 fois. Deux parties
sans faute, deux avec une erreur volontaire à chaque manche, deux avec deux erreurs volontaires à
chaque manche, six au hasard entre ces trois conduites : 77 propositions fausses pressées au
total. J'ai lu l'écran rendu après chaque coup : premier écran de l'item, écran après une erreur,
après deux erreurs, et l'écran de fin.

**Comment j'ai jugé à 176 px.** Pour chacun des huit items, j'ai mis l'écran réellement servi par
le jar dans un navigateur sans interface, avec la feuille de style servie, à deux réglages : 1280
px de large à densité 1, et 820 px de large à densité 2 (une tablette). La règle `.image-item`
(`max-height: 11rem`) plafonne bien chaque reproduction à **176 px CSS de haut** ; j'ai mesuré la
largeur rendue de chaque image sur la capture (de 129 px pour l'Arcimboldo à 344 px pour le marché
aux chevaux). J'ai regardé chaque image à cette taille-là, pas le fichier source, et j'ai appliqué
mon test de `REC-1` transposée tel que je l'ai écrit : **propositions masquées, je réponds à
l'énoncé en regardant l'œuvre ; si ma première réponse n'est pas la réponse attendue, l'item est
refusé.** Ensuite seulement j'ai regardé les propositions, pour vérifier que chaque distracteur est
absent de l'image.

**Ce que l'enfant voit sur une manche** : la consigne, l'œuvre, l'énoncé, trois propositions.
Après une erreur : *"Ce n'est pas ça. Essaie encore !"* et l'indice de l'item ; la proposition
pressée reste à sa place. Après deux erreurs, même message, même indice, la dernière proposition
reste à presser. Aucun écran ne plante, aucune image ne manque.

## 2. Les huit items, un par un

| # | Œuvre | Énoncé, bonne réponse, distracteurs | Ma première réponse, propositions masquées, à 176 px | Distracteurs absents de l'image ? | Verdict de l'item |
|---|---|---|---|---|---|
| 1 | `oeuvre-hippopotame` | *"Cette œuvre, qu'est-ce que c'est ?"* - *une statue* ; *une peinture*, *un dessin* | **"Un hippopotame."** | Oui | **Refusé** (motif 3) |
| 2 | `oeuvre-moissonneurs` | *"Que vois-tu sur cette œuvre ?"* - *un champ de blé* ; *une plage*, *un désert* | Un champ de blé avec des gens qui travaillent | Oui. La baie du fond est une mince bande d'eau lointaine, sans rivage visible : ce n'est pas une plage | Tenu |
| 3 | `oeuvre-chambre` | *"Que vois-tu sur cette œuvre ?"* - *une chambre* ; *une cuisine*, *une salle de classe* | Une chambre, avec un lit | Oui. La table au pichet rend *une cuisine* plausible sans la rendre juste | Tenu |
| 4 | `oeuvre-quatre-saisons` | *"Quelle forme reconnais-tu sur cette œuvre ?"* - *un visage* ; *une maison*, *un bateau* | **"Un arbre avec des fruits."** | Oui | **Refusé** (motif 2) |
| 5 | `oeuvre-grande-vague` | *"Que vois-tu sur cette œuvre ?"* - *une vague* ; *un désert*, *une forêt* | Une grosse vague | Oui | Tenu |
| 6 | `oeuvre-marche-aux-chevaux` | *"Que vois-tu sur cette œuvre ?"* - *des chevaux* ; *des vaches*, *des moutons* | Des chevaux | Oui | Tenu |
| 7 | `oeuvre-rue-sous-la-pluie` | *"Que vois-tu sur cette œuvre ?"* - *une rue* ; *une plage*, *une montagne* | Des gens sous des parapluies, dans une rue | Oui | Tenu, indice à reprendre (section 9, point d) |
| 8 | `oeuvre-femme-au-luth` | *"Que vois-tu sur cette œuvre ?"* - *une femme* ; *un homme*, *un enfant* | Une dame qui joue de la musique | Oui | **Refusé** (motif 1, indice) |

Le test de la première réponse admet, pour une scène, une réponse qui nomme le lieu ou le sujet
attendu avec d'autres choses vues (items 2, 3 et 7) : c'est ce qu'un enfant dit d'un tableau.
Il ne l'admet pas quand la première réponse ne contient pas du tout la réponse attendue (items 1
et 4). C'est la seule manière dont j'ai lu ma propre transposition, et je l'ai appliquée
identiquement aux huit items.

## 3. `oeuvre-quatre-saisons` - le risque que j'avais signalé, constaté

**Le visage ne se voit pas à 176 px.** Méthode : écran servi, densité 1 et densité 2, image rendue
à 129 x 176 px CSS. Ce que j'y vois sans savoir ce que je cherche : un tronc sombre sur un fond
presque noir, des branches en haut, des grappes de raisin et des pommes, deux bottes de paille en
bas. Le visage est de trois quarts, fait d'écorce dans un tableau très sombre ; je le retrouve
parce que je connais l'œuvre, pas parce qu'il se reconnaît. **`REC-2` transformée n'est pas
tenue** (*"ce que l'item demande se reconnaît sans hésiter à 176 px"*), et **`REC-1` transposée
non plus** : ma première réponse est *"un arbre avec des fruits"*.

Ce qui se passe alors en jouant : *une maison* et *un bateau* sont absents, l'enfant trouve *un
visage* **par élimination**. L'item est réussi sans que l'enfant ait vu ce qu'on lui demande de
dire : c'est une devinette, pas une description. L'indice (*"Regarde l'ensemble de l'image, de haut
en bas, sans t'arrêter aux détails."*) demande un geste abstrait à un enfant de six ans et ne
l'aide pas à voir un nez ou un œil.

**Rattrapable, sans retirer l'œuvre.** Ce qui se voit sans hésiter à 176 px, je l'ai noté : des
fruits (raisin, pommes, cerises), des branches. Un item qui **dit** que c'est un visage et demande
**avec quoi il est fait** passerait mon test (ma première réponse serait *"avec des fruits"*), et
ne se répond pas sans l'image (`ART-4`) : rien ne permet de deviner qu'un visage est fait de
fruits plutôt que de pierres. Je ne rédige pas l'item : la barre est la section 9, motif 2.

**Le retirer est aussi recevable pour moi, mais il a un coût que je ne résous pas.** Sept items
pour sept manches rompt l'inégalité stricte d'`ADR-0023`
(`ChaqueJeuDuCatalogueOffrePlusDItemsQueDeManchesTest`) et le critère *"huit items, huit
œuvres"* de `S50-ART`. Baisser les manches à six ou remplacer l'œuvre est une décision de
`camille-po`, chiffrée par `theo-archi` ; une œuvre de remplacement repasserait devant moi
(fidélité, 176 px, `ART-5`). Remonté en section 10.

## 4. `REC-1` transposée - chaque distracteur absent de l'image

**Tenue sur les huit items** (méthode : section 1, distracteurs regardés après ma première
réponse, à 176 px). Deux cas regardés de près : *une plage* devant les moissonneurs (la mer du fond
n'a pas de rivage visible) et *un enfant* devant la femme au luth (la figure est une jeune femme,
coiffe et robe d'adulte ; aucun enfant n'est peint). Ce sont des distracteurs plausibles, pas des
réponses défendables.

**Mais `REC-1` transposée commence par la première réponse, et l'item 1 y échoue.** *"Cette œuvre,
qu'est-ce que c'est ?"* devant une figurine d'hippopotame appelle *"un hippopotame"*, de n'importe
quel enfant. La question qu'on veut lui poser - est-ce un objet ou une image - n'est pas dans
l'énoncé : il la découvre en lisant les propositions. La distinction elle-même est la bonne, celle
que j'ai admise en Q4 (règle 3) : l'ombre sous les pattes montre un objet posé, et elle se voit à
176 px. C'est l'énoncé qui ne la demande pas.

## 5. `REC-2` transformée - seul le sujet principal

**Tenue sur sept items, pas sur l'item 4** (section 3). Méthode : à 176 px, j'ai vérifié que ce qui
est demandé occupe l'image ou en est le centre éclairé. Le champ de blé occupe presque toute
l'image des moissonneurs ; la vague, les chevaux, la chambre, la rue aussi. La femme au luth est le
point le plus éclairé du tableau, environ un tiers de la largeur ; c'est le cas le plus petit, et il
se reconnaît. Aucun item ne porte sur un petit personnage ni sur un détail. Aucun item ne fait
compter (`REC-4`). Aucun item ne demande ce que l'enfant ressent ou trouve beau (`ART-2`).

## 6. Aucune couleur (`ART-1`)

**Tenu.** Méthode : recherche, sur toutes les lignes non commentées du paquet et sur l'entrée de
catalogue, des noms de couleur usuels (bleu, rouge, jaune, vert, blanc, noir, gris, doré, orange,
violet, rose, marron, brun, beige, argenté, clair, sombre) et du mot *couleur* : aucune occurrence.
Puis lecture des huit énoncés, 24 propositions, huit indices et du texte alternatif, à l'écran : la
même absence. Aucune proposition ne se distingue des autres par une couleur. Le texte alternatif
est identique sur les huit items et ne nomme rien (`CON-5`) : *"Une reproduction d'œuvre à regarder
attentivement avant de répondre."*

## 7. Texte visible dans les images - mes deux conditions de Q2

**Tenues pour les deux œuvres.** Méthode : recherche dans le paquet des mots *japonais*, *écriture*,
*lettre*, *signature*, *cartouche*, *lire*, *titre*, *cadre* (aucune occurrence hors du libellé de
compétence, qui contient *décrire*), lecture des 24 textes à l'écran, et regard sur l'image servie.

- `oeuvre-grande-vague` : le cartouche vertical et la colonne de signature sont **présents** en haut
  à gauche de l'image rendue. Le fichier n'est pas recadré. Aucun texte du paquet n'en parle.
- `oeuvre-rue-sous-la-pluie` : la trace de signature est **présente** en bas à gauche, illisible à
  176 px et encore illisible agrandie quatre fois depuis le fichier de 352 px. Fichier non recadré.
  Aucun texte du paquet n'en parle.

## 8. `ART-5`, le libellé de compétence, le cadre doré, la fidélité

**`ART-5` - tenu sur ces huit œuvres, au plus juste.** Époques : Moyen Empire égyptien (vers 1878
avant notre ère), puis 1565, 1590, 1663, 1831, 1855, 1877, 1889. Aires : Égypte, Japon, et six
œuvres européennes (Flandre, Italie, Provinces-Unies, France). Genre des artistes : Rosa Bonheur
pour le marché aux chevaux, six hommes nommés, un artisan anonyme. Genres d'œuvre : une figurine,
une estampe, six peintures. Ni une seule époque, ni une seule aire, ni un seul genre d'artiste :
la condition est tenue. Je note qu'elle l'est avec une seule artiste femme et six œuvres sur huit
entre 1565 et 1889 en Europe : **une œuvre retirée ou remplacée ne doit pas ôter l'égyptienne, la
japonaise ni celle de Rosa Bonheur.** Méthode : registre `squad/corpus/oeuvres-domaine-public.yaml`
relu à `655b319b`, huit entrées, une par fichier servi.

**Le libellé de compétence - ne tient pas aujourd'hui, à cause des items 1 et 4.** *"Décrire une
œuvre : dire ce qu'on y voit (sujet, formes et matériaux)."* Retirer *couleurs* est juste, c'est
ma décision de Q4. Mais **chaque mot de la parenthèse est servi par un seul item** : *formes* par
l'item 4, *matériaux* par l'item 1, et ces deux items sont refusés. Servis aujourd'hui par des
items qui tiennent : *sujet* seul. Le libellé ne s'affiche pas à l'enfant (aucun gabarit ne le
rend) ; il compte parce qu'il est repris par la mention de la matrice. Barre : section 9, motif 4.

**Le cadre doré de `oeuvre-marche-aux-chevaux` - recevable.** Je l'ai écrit d'avance (*"recevable
tant qu'aucun item ne porte dessus"*) et je ne déplace pas cette barre : aucun texte du paquet ne
parle du cadre, et l'item porte sur les chevaux. Constaté à 176 px : le cadre est un liseré mince
sur les quatre côtés, le tableau est entier à l'intérieur, rien n'est masqué. Un enfant ne prend
pas le cadre pour une partie du sujet.

**Fidélité.** Méthode : à l'œil, à 176 px et sur le fichier de 352 px, contre la composition que je
connais. Cinq œuvres ne sont ni retournées ni recadrées (la vague déferle depuis la gauche, le lit
de la chambre est à droite, le couple de Caillebotte à droite du réverbère, la fenêtre de Vermeer
à gauche, l'arbre de Bruegel à droite du centre) ; l'hippopotame est un objet photographié entier,
sans sens de lecture. **Pour l'Arcimboldo et le marché aux chevaux, je n'ai pas confronté le
fichier à la fiche de l'institution** : je ne connais pas assez leur sens pour l'affirmer de
mémoire. Je le ferai au second passage, pour l'Arcimboldo seulement s'il reste au paquet.

## 9. Motifs du refus, et la barre qui lève chacun

Les trois premiers motifs appliquent des barres écrites **avant** le paquet : `REC-1` et `REC-2`
transposées (arbitrage préalable, 2026-10-04), et l'exactitude du contenu, qui est un motif de
refus à elle seule. Aucune barre n'est relevée.

**Motif 1 - Erreur factuelle dans l'indice de l'item 8 (`oeuvre-femme-au-luth`).** L'indice dit
*"Regarde la personne **debout** près de la fenêtre."* Dans le tableau, la jeune femme est **assise**
à une table : ses jambes sont cachées par la table et le dossier d'une chaise est au premier plan.
Je l'ai vérifié à 176 px et sur le fichier agrandi. L'enfant lit cet indice après une erreur,
c'est-à-dire au moment où il cherche à comprendre. **Barre** : l'indice ne dit plus *debout* ;
toute formulation exacte me convient (*assise* est exact, *près de la fenêtre* aussi).

**Motif 2 - Item 4 (`oeuvre-quatre-saisons`) : la réponse attendue ne se voit pas à 176 px.**
Section 3. **Barre** : soit l'item est réécrit de sorte que, propositions masquées, à 176 px, ma
première réponse à l'énoncé soit la réponse attendue, et que chaque distracteur soit absent de
l'image ; soit l'œuvre sort du paquet, avec la décision de la section 10, point 2.

**Motif 3 - Item 1 (`oeuvre-hippopotame`) : l'énoncé ne pose pas la question à laquelle les
propositions répondent.** Section 4. **Barre** : l'énoncé demande ce qu'est l'objet (une statue ou
une image), plus ce qu'il représente, de sorte que *"un hippopotame"* ne soit plus une réponse
spontanée ; il reste court pour un lecteur de CP. Exemple recevable, non imposé : *"Cet
hippopotame, est-ce une statue, une peinture ou un dessin ?"* L'indice actuel (*"...ou si elle est
plate comme une image"*) se reprend dans le même geste : l'enfant regarde **une image** sur un
écran, et lui demander si la chose est plate comme une image le fait douter de ce qu'il voit. Ce
qui se voit est l'ombre sous les pattes : c'est elle qu'un indice peut montrer.

**Motif 4 - Le libellé promet ce que le paquet ne sert pas.** Section 8. **Barre** : chaque mot
entre parenthèses du libellé (*sujet*, *formes*, *matériaux*) est servi par au moins un item qui
tient mon test de `REC-1` transposée ; un mot qui ne l'est pas sort du libellé **et** de la mention
de matrice que j'ai écrite en Q4. Ce motif se lève de lui-même si les motifs 2 et 3 sont levés avec
des items qui servent encore les formes et les matériaux. Si l'item 4 devient *"avec quoi ce
visage est-il fait ?"*, il sert les matériaux, plus les formes : *formes* sort alors du libellé.

**Non bloquant, à corriger dans le même passage.**

- (d) Indice de l'item 7 (`oeuvre-rue-sous-la-pluie`) : *"Observe les personnes qui marchent sous
  leurs parapluies."* L'enfant qui vient de presser *une plage* n'y trouve rien qui le détrompe :
  des gens sous des parasols, c'est aussi une plage. Un indice qui montre ce qui fait une rue
  (les maisons, le trottoir) l'aiderait. Seul, ce point n'aurait pas motivé un refus.

## 10. Remontées, sans les résoudre

| # | Point | À qui |
|---|---|---|
| 1 | Faire des motifs 1 à 4 et du point (d) le travail d'un second passage. Tous se corrigent dans le seul fichier `cp-decrire-une-oeuvre.yaml` (et le libellé du descripteur), sans code Java, si l'Arcimboldo est gardé et réécrit | `camille-po`, puis le développeur |
| 2 | Si l'Arcimboldo est **retiré** plutôt que réécrit : sept items pour sept manches rompt l'inégalité stricte d'`ADR-0023` et le critère *"huit items, huit œuvres"* de `S50-ART`. Choisir entre baisser `nombreDeManches` à 6, remplacer l'œuvre (restaurable depuis l'historique Git, à repasser devant moi), ou ajouter un second item sur une œuvre déjà servie. Les cliquets de reproductions (`REPRODUCTIONS_SERVIES`, poids, `ITEMS_QUI_DECLARENT_UNE_ILLUSTRATION`) bougent avec ce choix | `camille-po` (décision), `theo-archi` (prix et cliquets) |
| 3 | `ART-CP-09` reste `non couvert` dans la matrice : ce refus ne la ferme pas | `camille-po` |

## 11. Ce dont je reste la seule garde

`PaquetCpDecrireUneOeuvreTest` vérifie le vivier exact, l'absence de mots de couleur et l'absence
de mention du texte visible : les sections 6 et 7 sont donc doublées par un automate, sur la liste
de mots qu'il porte. **Aucun automate ne vérifie**, et je reste la seule garde, en jouant :

- que la réponse attendue se voit à la taille de rendu, et que ma première réponse est celle-là
  (`REC-1` et `REC-2` transposées) - c'est là que les motifs 2 et 3 sont passés, CI verte ;
- que chaque distracteur est absent de l'image ;
- l'exactitude de ce que les indices disent du tableau - c'est là que le motif 1 est passé ;
- que le libellé ne promet que ce qui est servi ;
- la fidélité de chaque reproduction et `ART-5` sur le corpus servi.

---

## Bloc de validation

> **Done pédagogique : REFUSÉ** (premier passage).
>
> **Prononcé le 2026-10-04 par `nadia-prof`, instance B, après avoir joué le jar servi** (arbre à
> `655b319b`, 12 parties, 84 manches, 77 erreurs volontaires, chaque œuvre vue au moins dix fois à
> sa taille de rendu de 176 px).
>
> **Motifs** : (1) l'indice de l'item `oeuvre-femme-au-luth` dit *debout* d'une femme assise - erreur
> factuelle ; (2) l'item `oeuvre-quatre-saisons` demande *un visage* qui ne se voit pas à 176 px et
> se trouve par élimination ; (3) l'énoncé de l'item `oeuvre-hippopotame` appelle *"un hippopotame"*,
> pas la distinction statue ou image que les propositions demandent ; (4) en conséquence, *formes*
> et *matériaux* du libellé ne sont servis par aucun item qui tient. Barres exactes : section 9.
>
> **Ce qui tient déjà et ne sera pas rejugé au second passage, sauf si le fichier change** : les
> items 2, 3, 5, 6, 7 (sujet, distracteurs, taille), l'absence de couleur, mes deux conditions sur
> le texte visible, le cadre doré, `ART-5` sur ces huit œuvres, le texte alternatif. Reste à
> faire au second passage : la fidélité du marché aux chevaux (et de l'Arcimboldo s'il reste),
> section 8.
>
> **Mouvement de ligne** : aucun. `ART-CP-09` reste `non couvert`. Le mouvement, quand il viendra,
> est écrit par `camille-po`, pas par moi.
