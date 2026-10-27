# s57 - lecture avant `verify` du premier jet de `ce1-qlm-eau-trois-etats` (`S57-BR-QLM-CE1-05`)

- **Auteure** : `nadia-prof`, professeure des ecoles, seule autorite sur le Done pedagogique.
- **Date** : debut 2026-10-27 12:08:51 (UTC+01:00), ecriture 12:27:18, lues par `date` ; fin en section 9 (session interrompue
  puis reprise, dit en section 9).
- **Arbre** : branche `s57-p5-nadia-lecture05`, base `round-two` a `bdfed668`, lue par `git rev-parse`.
- **Commit lu** : `e6694d47` (`[MARC]`, 2026-10-27 11:32:36 +0100), premier jet de `marc-dev`, ancetre de `bdfed668` (lu par
  `git merge-base --is-ancestor`).
- **Type** : lecture avant `verify` (`QC7`). **Aucun Done, aucun verdict de lot**, aucun identifiant neuf, aucune condition neuve.
  Je n'ai modifie ni le paquet, ni les tests, ni le jeu fautif.
- **Audit degrade, dit** : rien n'est joue a l'ecran, le paquet n'est pas au catalogue (`QC3`). J'ai joue chaque item sur le papier,
  comme un enfant de CE1, y compris en me trompant et en cherchant les raccourcis (la plus longue, l'intrus de forme, le mot qui
  revient).
- **Lu** : `ce1-qlm-eau-trois-etats.yaml` (12 items) ; `PaquetCe1QlmEauTroisEtatsTest.java` ; le jeu fautif
  `contreexemple/PaquetCe1QlmEauTroisEtatsTest/contenu/eau-fautive.yaml` (53 sondes) ; l'entree de rang du paquet
  (`LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest.java:1415`) ; story `S57-BR-QLM-CE1-05`, criteres 1 a 12 et leurs
  blocs de corrections ; mes trois arbitrages de `-05` ; `ETE-CE1-1`, `ETE-CE1-2`, `NRC-CE1-1`, `QMT-CE1-1`, `-2`, `TRV-CE1-2`,
  `-5`, `-6` dans leurs mots (`ARBITRAGE-PREALABLE-s57-instruction-questionner-le-monde-et-emc-ce1.md`, 2 et 3.5) ; les avis
  d'ajustement de `theo-archi` (`-04`/`-05`, sections 1 et 3 ; dernier ajustement, sections 2 et 8) ; `DEC-0120` A.272, A.273 ; ma
  lecture de `-04` comme modele ; les voisins `cp-qlm-etats-eau` et `cm1-sciences-etats-matiere`.
- **Mesures lancees** :
  - recompte par script (Python, hors du depot, non commite) : mots par la regle de `PaquetLu.mots`, longueur en caracteres
    comptes comme `String.length()` de `ChaquePaquetDeQuestionnerLeMondeDeCe1TientLesConditionsTransversesTest.java:198-199`, comptes de
    *rien ne change*, sur le paquet puis sur une copie portant mes corrections ;
  - `bash outils/mvn-jdk21.sh -B -q -pl adapters/adapter-content -am test
    -Dtest='PaquetCe1QlmEauTroisEtatsTest,ChaquePaquetDeQuestionnerLeMondeDeCe1TientLesConditionsTransversesTest'
    -Dsurefire.failIfNoSpecifiedTests=false`, JDK 21.0.10 (propriete `java.version` des rapports) : **48 tests, 0 echec, 0 erreur**
    pour le test du paquet, **18, 0, 0** pour le test transverse. Perimetre : ces deux classes, module `adapter-content` et ses
    dependances, sur le paquet tel que commite. Ce n'est ni un `verify`, ni la porte technique.

## Resume

| Point | Reponse |
|---|---|
| Items | **9 justes, 3 a corriger, 0 refuse en l'etat** (section 2) |
| Autocontrole A.273 | **a** (indice qui donne la reponse) : aucun. **b** (distracteur ecarte sans la competence, ou seconde reponse) : 3 items (4, 8, 12). **c** (source circulaire) : aucune |
| Proprietes que je garde seule | `ETE-CE1-2` (eau liquide decrite sans le mot *eau*) : **tenue** ; `TRV-CE1-2` non garde (paraphrase de *rien ne change*) : **tenue** ; `QMT-CE1-2` (faits) : **exacts**, item 4 a preciser ; `CYC-1` : **tenu** ; `NRC-CE1-1` au-dela du texte : **tenu** ; `TRV-CE1-6` : **tenu** (section 1) |
| Doutes de `marc-dev` | reponses en section 3 ; aucun correctif de forme pour la longueur |
| Reouverture d'une formulation | **aucune** ; une correction de mon propre exemple de 2.4, sans effet sur le paquet (section 7) |
| Cout | **1,0 au bareme a cette ecriture** ; 1,5 si mes corrections appellent une seconde lecture (section 8) |

---

## 1. Ce que j'ai verifie avant les items

- **`FER-1`, les cinq elements du libelle en bonne reponse** : *vapeur d'eau* (items 1, 4, 6, 8), *eau liquide* (3, 10, 9), *glace*
  (7), *la fonte* (5, 12), *le gel* (2). Lecture sur le libelle entier, confirmee le 2026-10-25 : je la confirme une seconde fois.
  Mes corrections n'en retirent aucun. **Tenu.**
- **`ETE-CE1-1`, le tiers** : 4 items sur 12 ont *la vapeur d'eau* pour bonne reponse, le plus petit entier dont le triple atteint 12
  (critere 9). **Tenu.**
- **`FER-3`, deux operations (je suis la seule garde)** : en tache vapeur (6 items), l'enfant reconnait l'etat de l'eau qu'il ne voit
  plus, ou ce qu'est ce qu'il voit (la buee, le brouillard) ; en tache temperature (6 items), il prevoit ce que la temperature fait a
  de la glace, a de l'eau liquide, ou aux deux. Aucun item de vapeur ne demande de prevoir ; aucun item de temperature ne demande de
  reconnaitre l'eau invisible. **Tenue sur les 12 items**, et sur les trois items corriges.
- **`QMT-CE1-2`, exactitude des faits (je suis la seule garde)**, contre l'instruction 3.5 et `cm1-sciences-etats-matiere` :
  - la buee et le brouillard sont de toutes petites gouttes d'eau liquide (3.5) : items 3, 10, exacts ;
  - l'eau du linge, de la flaque, du sol devient vapeur sans bouillir (3.5, *"le linge seche sans bouillir"* ; CM1, la flaque de la
    cour) : items 1, 6 exacts ; **item 4 a preciser** : une flaque *"dehors"* peut etre sur de la terre, ou une partie de son eau
    s'enfonce dans le sol ; la bonne reponse n'est alors vraie que pour une partie de l'eau (2.1) ;
  - l'eau qui bout devient vapeur (CM1, la casserole) : item 8, exact ;
  - le gel et la fonte relies au froid, au chaud et au seuil de 0 degre (3.5) : items 2, 5, 7, 9, 11, 12, exacts. L'item 7 (eau et
    glace sous 0 degre toute la journee, *seulement de la glace*) et l'item 11 (glace seule sous 0 degre, *rien ne change*) disent la
    meme chose dans les deux sens : sous 0 degre, l'eau gele et la glace reste de la glace ;
  - la source est l'instruction 3.5, hors du paquet et de ses tests, comme ma regle 7 le dit : **pas de source circulaire (c)**.
- **`ETE-CE1-2`, eau liquide decrite sans le mot *eau* (je suis la seule garde ; `F3` et `G7` cote eau, non gardes)** : l'item 11, seul
  a *rien ne change*, ne decrit qu'un glacon, sans flaque, sans mare, sans pluie, sans verre ; il dit *sous 0 degré* pour *toute la
  journée*. *L'eau gèle* est propose aux items 2 (verre d'eau), 5 (mare gelee en surface : l'eau liquide est sous la glace) et 12
  (pichet d'eau) : chaque fois l'eau liquide est decrite. **Tenue.**
- **`TRV-CE1-2`, paraphrase de *rien ne change* (non garde, je le lis)** : aucune proposition ne paraphrase *rien ne change* (ni *il
  ne se passe rien*, ni *il reste de la glace*, ni *le glaçon reste pareil*). *De la glace et de l'eau* (items 7, 9) est l'etat de depart,
  donc la forme d'etat de *rien ne change* : je l'ai pese et je le garde (section 3, `b-B3B4`). **Tenue.**
- **`CYC-1` (je suis la seule garde)** : `cm1-sciences-etats-matiere` dit la flaque de la cour qui n'a *"pas disparu"* et devient
  *"un gaz invisible"*, le miroir couvert de *"petites gouttes d'eau"*, l'eau qui bout qui devient *"vapeur d'eau, un gaz
  invisible"*, le glacon qui devient flaque, l'eau liquide au congelateur qui devient glacon. Aucun item ne le contredit.
  `cp-qlm-etats-eau` dit fondre et geler, sans vapeur : aucun item ne le contredit. **Tenu.**
- **`NRC-CE1-1` au-dela du texte (je suis la seule garde)** : le CP sait *fondre = glacon dehors quand il fait chaud* et *geler = eau
  dehors quand il fait tres froid*. Tache vapeur : rien au CP. Tache temperature : les items 2, 9, 12 (congelateur, soleil) sont de la
  forme de mes exemples de 2.2 (glace et eau ensemble) que j'ai admise le 2026-10-26 ; je ne releve pas cette barre. Les items 5, 7,
  11 demandent le seuil de 0 degre, que le CP n'a pas. **Tenu.**
- **`TRV-CE1-6` (je suis la seule garde)** : aucun prenom, aucun possessif, aucun *chez toi*, *ta maison*, *ton congélateur*. *Du
  linge* est le mot meme de `ETE-CE1-2` ; *le congélateur* et *la douche* sont ceux de mes exemples ; *la cour* est la cour de
  l'ecole pour un enfant de CE1 ; *une mare*, *la route*, *un pichet* ne supposent rien que l'enfant possede. **Tenu.**
- **`QMT-CE1-1`** : aucun des quinze mots exclus dans un champ lu par l'enfant ; *s'evapore* n'est que dans un commentaire.
- **Indices (`TRV-CE1-5`, regle 5)** : les douze comparent deux situations ; aucun ne nomme la vapeur, ne dit *invisible* ou *ne se
  voit pas*, n'enonce le sens de la temperature ; 7 a 11 mots ; tous distincts. **Une reserve de jeu, pas une correction** : les
  quatre indices des items a la vapeur d'eau (*"Compare le linge mouillé et le linge sec."*...) comparent l'avant et l'apres de la
  meme situation ; ils ne donnent pas la reponse, mais ils n'aident guere un enfant bloque a choisir entre *elle disparaît* et *elle
  devient de la vapeur d'eau*. Je regarderai a l'ecran si un enfant bloque avance. Ma regle 5 est tenue a la lettre ; je ne la durcis
  pas.
- **Lisibilite CE1, `LON-CE1-1`, `-2`, `-5`** : enonces de 11 a 19 mots (18 pour l'item 4 corrige), deux ou trois phrases, la
  derniere est la question ; propositions de 2 a 6 mots. *Pichet*, *surface*, *réchauffe*, *au-dessus de 0 degré* sont lisibles au
  CE1 avec l'adulte de la classe ; ce sont, sauf *pichet*, les mots de mes exemples.
- **Rang** : 12 items, 36 propositions, toutes textuelles. Mes corrections ne changent ni le nombre d'items ni le nombre de
  propositions : l'entree de rang garde son motif (`QC10`).
- **Accents, orthographe, ponctuation** : au `relecteur-pedagogique` (`DEC-0002`, `DEC-0056`). Je n'ai rien vu qui manque.

## 2. Verdict par item

Numerotation : ordre du fichier ; entre parentheses, les reperes de `marc-dev` (`V`, `E`, `B`). *Juste* : rien a changer. *A
corriger* : correction demandee avant `verify` ; restee telle quelle a l'audit, ce serait une reserve. Aucun item n'est refuse en
l'etat : aucun fait n'est faux au CE1.

| # | Ligne | Tache, sujet | Bonne reponse ; distracteurs | Indice | Verdict | Motif |
|---|---|---|---|---|---|---|
| 1 (V1) | `:74` | vapeur, linge au soleil | *elle devient de la vapeur d'eau* ; *elle disparaît*, *elle devient de la buée* | *Compare le linge mouillé et le linge sec.* | **juste** | Le fait de `ETE-CE1-2` (sans bouillir). La conception et la buee sont les deux pieges de la ligne ; la buee ne se forme pas sur du linge au soleil |
| 2 (B1) | `:80` | temperature, glacon dans un verre d'eau au congelateur | *l'eau gèle* ; *le glaçon fond*, *rien ne change* | *Compare le congélateur et une journée d'été.* | **juste** | Mon exemple de 2.2, a l'identique. *Le glaçon fond* est le changement que le froid empeche |
| 3 (E1) | `:86` | vapeur, buee du miroir | *de l'eau liquide* ; *de la vapeur d'eau*, *du brouillard* | *Compare un miroir sec et un miroir couvert de buée.* | **juste** | L'item que `ETE-CE1-2` protege. *Du brouillard* vaut mieux que *de la glace* de mon exemple (section 7). L'indice mene aux gouttes qu'on voit et qu'on touche |
| 4 (V2) | `:92` | vapeur, flaque au soleil | *elle devient de la vapeur d'eau* ; *elle n'existe plus*, *elle devient du brouillard* | *Compare la flaque du matin et celle du soir.* | **a corriger** | Deux motifs, 2.1 : *du brouillard* s'ecarte par *le soleil brille toute la journée*, sans rien savoir de la vapeur (defaut **b**) ; une flaque *dehors* peut etre sur de la terre |
| 5 (B2) | `:98` | temperature, mare gelee rechauffee au-dessus de 0 degre | *la glace fond* ; *l'eau gèle*, *rien ne change* | *Compare une mare en hiver et une mare en été.* | **juste** | Mon exemple de 2.2, a l'identique. *L'eau gèle* a un objet : l'eau liquide sous la glace (section 3, `G7`) |
| 6 (V3) | `:104` | vapeur, sol de la cour apres la pluie | *elle devient de la vapeur d'eau* ; *elle a disparu*, *elle devient de la buée* | *Compare le sol juste après la pluie et le lendemain.* | **juste** | Le sol *mouillé* de la cour est un sol dur : l'eau qui le mouille s'en va dans l'air. Aucune proposition ne parle de l'eau qui coule ou s'enfonce : une seule reponse defendable (section 3) |
| 7 (B3) | `:110` | temperature, eau et glace sous 0 degre toute la journee | *seulement de la glace* ; *seulement de l'eau liquide*, *de la glace et de l'eau* | *Compare un verre dehors en hiver et en été.* | **juste** | La forme de mon exemple de 2.2. Ecarter *de la glace et de l'eau*, c'est savoir que le froid agit : la competence |
| 8 (V4) | `:116` | vapeur, eau qui bout dans une casserole | *elle devient de la vapeur d'eau* ; *elle n'existe plus*, *elle devient un nuage* | *Compare le niveau de l'eau avant et après.* | **a corriger** | *Un nuage* est ce que la situation produit : l'enfant voit le petit nuage blanc au-dessus de la casserole, c'est la fumee blanche de ma regle 3 sous un autre nom. Une part de l'eau qui manque y passe : seconde reponse defendable (2.2) |
| 9 (B4) | `:122` | temperature, eau et glace au soleil toute la journee | *seulement de l'eau liquide* ; *seulement de la glace*, *de la glace et de l'eau* | *Compare un verre au soleil et un verre au congélateur.* | **juste** | Miroir de l'item 7 : seule la temperature distingue les deux. *Au soleil* est un changement de temperature que j'ai admis (2.1 du 2026-10-26) |
| 10 (E2) | `:128` | vapeur, brouillard sur la route | *de l'eau liquide* ; *de la vapeur d'eau*, *de la fumée* | *Compare une matinée de brouillard et une matinée claire.* | **juste** | *De la fumée* est plausible (un enfant croit le brouillard fait de fumee), jamais juste : un brouillard n'est pas fait de fumee |
| 11 (B5) | `:134` | temperature, glacon seul sous 0 degre | *rien ne change* ; *le glaçon fond*, *le glaçon disparaît* | *Compare le glaçon dehors un jour d'été et un jour d'hiver.* | **juste** | Mon exemple de la regle 2, a l'identique. *Le glaçon disparaît* est faux sous 0 degre |
| 12 (B6) | `:140` | temperature, glacons dans un pichet au soleil | *les glaçons fondent* ; *l'eau gèle*, *les glaçons disparaissent* | *Compare le pichet au soleil et le pichet au congélateur.* | **a corriger** | *Les glaçons disparaissent* est vrai dans la langue de tous les jours : au soleil, il n'y a plus de glacons a la fin. Seconde reponse defendable (2.3) |

### 2.1 Item 4 : un distracteur que le soleil ecarte, une flaque sans sol

**Le distracteur.** Un enfant de CE1 sait que le brouillard va avec les matins gris, pas avec *"le soleil brille toute la journée"*. Il
l'ecarte par la meteo de l'enonce, et l'item devient un choix entre deux. C'est le defaut **b** d'A.273, pour la meme raison que ma
regle 3 refuse *la glace au soleil*. *La buée* ne s'ecarte pas ainsi : l'enfant ne l'associe ni au soleil ni a l'ombre.

**Le sol.** *"Une flaque est dehors"* ne dit pas sur quoi. Sur de la terre, une partie de l'eau s'enfonce : la bonne reponse reste la
seule defendable des trois, mais elle n'est plus vraie pour toute l'eau de la flaque. *Dans la cour*, comme la flaque de
`cm1-sciences-etats-matiere`, met la flaque sur un sol dur.

**Le remplacement** : enonce *"Une flaque est dans la cour. Le soleil brille toute la journée. Que devient l'eau de la flaque ?"* (18
mots), distracteurs *elle n'existe plus*, *elle devient de la buée*. Recompte par script : la bonne reponse reste strictement la plus
longue (31 contre 18 et 23).

### 2.2 Item 8 : ce que la casserole produit

Ma regle 3 nomme *la fumée blanche pour une casserole qui bout* ; `ETE-CE1-2` dit que la buee, le nuage, le brouillard et la fumee
blanche sont la meme chose, de toutes petites gouttes. Ce qu'un enfant voit au-dessus de la casserole, il l'appelle *de la fumée* ou
*un nuage*. *Elle devient un nuage* est donc l'element que la situation produit, sous le nom que l'enfant lui donne.

**Le remplacement** : *elle devient du brouillard*. Le brouillard est de la famille de la buee (un seul par item, regle 3 tenue), et
aucun enfant n'appelle *brouillard* ce qui sort d'une casserole. *La buée* n'est pas retenue : la cuisine ou l'eau bout couvre de
buee la vitre et le couvercle, ce qui la rapproche de ce que la situation produit. Ce choix reste a ma lecture a l'ecran.

L'item 8 reste le seul ou l'eau bout : `X5` et les sondes `s7`, `s8`, `s21` qui le lisent ne changent pas.

### 2.3 Item 12 : la disparition des glacons est vraie

*La disparition* est la conception a deplacer quand elle porte sur **l'eau** (*elle n'existe plus*). Portee sur **les glacons** d'un
pichet au soleil, elle decrit ce que l'enfant voit : a la fin de la journee, il n'y a plus de glacons. Un enfant qui la choisit a raison
dans sa langue, et un adulte ne peut pas lui dire qu'il se trompe. A l'item 11, *le glaçon disparaît* est faux, parce que le froid
empeche la fonte : c'est la meme proposition, fausse la-bas, vraie ici.

**Le remplacement** : *rien ne change*. La regle 4 l'admet (l'item 11 l'a pour bonne reponse) ; au soleil, il est faux. Ce que cela
coute, dit : *rien ne change* passe de 2 a 3 distracteurs pour 1 bonne reponse [calcul par script]. Ma regle dit *au moins un* ; je
ne la durcis pas, et je regarderai a l'ecran si l'enfant l'ecarte sans lire. La bonne reponse devient strictement la plus longue
(19 contre 10 et 14).

## 3. Les doutes et les points de `marc-dev`

| Code | Doute ou point | Reponse | Motif |
|---|---|---|---|
| `a-V` | Indices : dans les 4 items a la vapeur d'eau, la bonne reponse et la buee reprennent *devient* | **pas un defaut a** | Le defaut a porte sur l'indice ; aucun indice ne reprend *devient* ni ne nomme la vapeur. *Que devient l'eau ?* appelle *elle devient ...* : la forme suit la question |
| `a-B3B4` | *seulement de ...* repete dans les propositions des items 7 et 9 | **juste** | Le mot repete oblige a lire la fin (*glace* ou *eau liquide*), et c'est la temperature qui la decide |
| `b-V` | Les 4 conceptions sous 3 formes, jamais justes, seule proposition qui n'est pas en *elle devient* : choisir entre les deux *devient* donne une chance sur deux | **juste** | La conception n'est pas un distracteur qu'on ecarte sans la competence : c'est celui que l'enfant vise par la ligne choisit vraiment. L'enfant qui l'ecarte a deja appris que l'eau ne disparait pas ; il lui reste a choisir entre la vapeur et la buee, qui est le second contenu de `ETE-CE1-2`. Une proposition toujours fausse parce que le fait est toujours faux n'est pas *rien ne change*, qui est vrai dans une situation et que l'enfant doit pouvoir choisir. Les trois formes sont bonnes ; rien a unifier |
| `b-B3B4` | *de la glace et de l'eau* distracteur des items 7 et 9 | **juste** | C'est l'etat de depart, donc la forme d'etat de *rien ne change*, que je ne peux faire juste dans aucun item (contradictions du 2026-10-27, 1.2, *exclu*). Il est ma proposition de 2.2, et il n'est jamais seul : l'ecarter laisse *glace* contre *eau liquide*, que la temperature decide. Deux items seulement ; je regarde a l'ecran |
| `b-V2` | Flaque dehors toute la journee : l'eau peut s'infiltrer | **la seule defendable des trois, mais a preciser** | Section 2.1 |
| `b-V3` | Sol de la cour apres la pluie : meme doute | **juste** | La cour est un sol dur ; aucune proposition ne parle d'infiltration ni de ruissellement. L'eau qui *mouille* le sol est celle qui s'en va dans l'air |
| `b-V4` | *Un nuage* pour la casserole | **a corriger** | Section 2.2 |
| `b-E` | *Du brouillard* et *de la fumée* plausibles sans etre surs | **justes** | Plausibles et faux : c'est ce que je demande a un distracteur. Ni l'un ni l'autre n'est ce dont la buee ou le brouillard est fait |
| `b-B` | Items 2, 5, 11 : le changement que la temperature empeche en distracteur | **juste** | C'est le distracteur attendu de ma regle 2 corrigee (dernier lot, 6.2). L'item 12 a le sien (*l'eau gèle*) ; c'est son autre distracteur qui est a corriger |
| `c-B5` | *Rien ne change* repose sur la regle 2, pas sur un mot de 3.5 ; rattache a *la glace fond quand on la chauffe* et au seuil de 0 degre | **rattachement juste, rien a ajouter** | 3.5 dit *0 degré pour la fonte et le gel* : sous ce seuil, la glace ne fond pas. C'est le seuil lu dans l'autre sens, pas un fait neuf. La source reste 3.5 (regle 7) ; mon arbitrage n'a pas a y etre cite |
| `TRV-CE1-2` | Bonne reponse strictement la plus longue dans 5 items sur 12 (seuil 6) ; toujours la plus longue dans les 4 items a la vapeur d'eau : correctif de forme ? | **non** | Mesure par script : 5 sur 12 au premier jet, **6 sur 12 apres mes corrections** (l'item 12 s'ajoute), a la limite de *la moitie au plus*, tenue. La regle est ecrite pour le paquet ; une regle par tache serait une barre neuve apres le premier jet. Un correctif demanderait d'allonger *buée* ou la conception au-dela de *vapeur d'eau* par des mots qui ne portent rien. Ce que l'enfant retrouve d'un item a l'autre n'est pas la longueur, c'est la meme reponse dans quatre situations : c'est le fait a apprendre. Et *la plus longue* le trompe aux items 3 et 10, ou *de la vapeur d'eau* est le distracteur le plus long. Une modification ulterieure qui ferait un septieme item ou la bonne reponse est la plus longue serait refusee par le test transverse |
| `n=12` | 12 items pour que le tiers soit exact | **confirme** | Critere 9 : 4 pour 11 et 12 items |
| `RNC` | *Rien ne change* bonne reponse du seul item 11 ; *sous 0 degré* aussi a l'item 7, dont la bonne reponse est *seulement de la glace* | **confirme** | Regle 2 : 0 degre dans un item de B. Les items 7 et 11 se repondent (section 1) |
| `phrases` | Propositions-phrases en tache vapeur | **confirme** | *Que devient l'eau ?* appelle une phrase ; les items de la buee (*De quoi est faite ...*) appellent *de l'eau liquide*. Aucune ne depasse 6 mots |
| `nombres` | Motif qui accepte *vingts?*, *cents?* | **confirme** | Plus large que mes exemples, dans le sens de ma regle (*cent degrés* refuse) ; forme a `theo-archi` |
| `T05-3f` | *0 degré* lu sur les items sans tache calculee | **confirme** | Un tel item est deja refuse par `FER-3` ; le second message ne refuse rien que mes regles admettent |
| `TRV-CE1-6` | *Du linge*, *congélateur*, *cour* supposent-ils un foyer ? | **non** | Section 1 |
| `G7` | Item 5 : *l'eau gèle* a un objet, l'enonce n'a pas le mot *eau* | **confirme, tenu** | La mare gelee *en surface* est de l'eau liquide sous la glace. C'est le cas que `theo-archi` a laisse a ma lecture (dernier ajustement, 2.2) |
| `A-B` | Aucun item de B ne porte un element de la buee ; la tache A ne propose jamais *rien ne change* | **confirme** | Regle 3 de A : conception ou buee ; *rien ne change* n'y a pas de place (dernier lot, 2.2) |
| `FER-1` | Cinq elements en bonne reponse (critere 2) | **confirme** | Section 1 |

## 4. Ce que je demande a `marc-dev`

Texte exact, a appliquer tel quel. Rien d'autre ne change : ni les autres enonces, ni les bonnes reponses, ni les indices, ni l'en-tete,
sauf la ligne de commentaire du point 4. L'espace avant `?` reste insecable (`TYP-1`), comme dans le texte actuel.

**Dans `adapters/adapter-content/src/main/resources/contenu/ce1-qlm-eau-trois-etats.yaml`** :

| Point | Item, ligne | Texte actuel | Texte a ecrire |
|---|---|---|---|
| 1 | 4, `:92` | `- enonce: "Une flaque est dehors. Le soleil brille toute la journée. Que devient l'eau de la flaque ?"` | `- enonce: "Une flaque est dans la cour. Le soleil brille toute la journée. Que devient l'eau de la flaque ?"` |
| 2 | 4, `:94` | `distracteurs: ["elle n'existe plus", "elle devient du brouillard"]` | `distracteurs: ["elle n'existe plus", "elle devient de la buée"]` |
| 3 | 8, `:118` | `distracteurs: ["elle n'existe plus", "elle devient un nuage"]` | `distracteurs: ["elle n'existe plus", "elle devient du brouillard"]` |
| 4 | 12, `:139` | `# TRV-CE1-2 : d, f` | `# TRV-CE1-2 : d, e` |
| 5 | 12, `:142` | `distracteurs: ["l'eau gèle", "les glaçons disparaissent"]` | `distracteurs: ["l'eau gèle", "rien ne change"]` |

Les lignes `# TRV-CE1-2 : a, b` des items 4 et 8 ne changent pas : la buee et le brouillard sont de la famille b. Les deux lignes de
compte de l'en-tete (6 et 6) et la liste des familles ne changent pas.

**Dans `PaquetCe1QlmEauTroisEtatsTest.java`**, consequence du point 5, dans le meme commit (ligne `T1` de la section 5) : la sonde `s12`
(`:747`) attend *"regle 4 : rien ne change est distracteur de 2"* ; apres le point 5, le paquet prive de l'item 11 porte *rien ne
change* en distracteur dans 3 items (2, 5, 12), et le fragment devient *"regle 4 : rien ne change est distracteur de 3"* [lecture du
code de `defautsDeLaRegleQuatre`, non mesure : je n'ai pas mute le paquet].

**Recompte, sur une copie portant les points 1 a 5** [calcul par script] : item 4, 18 mots, trois phrases ; propositions de 2 a 6
mots ; bonne reponse strictement la plus longue dans 6 items sur 12 (1, 4, 6, 8, 9, 12) ; *rien ne change* bonne reponse de 1 item,
distracteur de 3 ; un seul element de la famille de la buee par item de vapeur ; 12 items, 36 propositions.

**Ce dont je reste la seule garde sur ce paquet, apres correction** : l'exactitude de chaque fait contre 3.5 et le CM1, la flaque sur un
sol dur comprise ; qu'un distracteur de vapeur ne soit pas ce que la situation produit (le nuage d'une casserole) ni ce que sa meteo
ecarte (le brouillard au soleil) ; qu'une disparition ne soit pas vraie dans la langue de l'enfant (les glacons au soleil) ; l'eau
liquide decrite sans le mot *eau* ; les paraphrases de *rien ne change* et le rapport de ses distracteurs a sa bonne reponse ; qu'un
indice aide un enfant bloque ; `FER-3` item par item ; `NRC-CE1-1` sur le sujet et `CYC-1` ; `TRV-CE1-6`. **Aucune n'est couverte par
un test**, sauf ce que `T2` couvrirait si `theo-archi` l'encode.

## 5. Ce qui revient a `theo-archi` (forme), une ligne par garde

| Repere | Garde | Ajustement |
|---|---|---|
| `T1` | `s12` (`PaquetCe1QlmEauTroisEtatsTest.java:747`) | Aucune forme neuve : le fragment suit le compte du paquet corrige, *"distracteur de 3"*. A ecrire par `marc-dev` avec le point 5 |
| `T2` | regle 3 de A, element que la situation produit | Si tu le juges encodable : dans un item de vapeur dont l'enonce porte `BOUILLIR`, refus d'un distracteur qui porte *nuage* ou *fumée*. Sonde refusee : l'item 8 du premier jet ; passe : l'item 8 corrige. Sinon *non garde : `TRV-CE1-2`*, je le lis |
| `T3` | `TRV-CE1-2`, une seule acceptable, item de fonte | Si tu le juges encodable : dans un item de temperature dont la bonne reponse est une forme de la fonte, refus d'un distracteur qui porte une forme de *disparaître*. Sonde refusee : l'item 12 du premier jet ; passe : l'item 11 (*le glaçon disparaît*, bonne reponse *rien ne change*). Sinon *non garde : `TRV-CE1-2`*, je le lis |
| `T4` | aucune garde | Le brouillard que la meteo de l'enonce ecarte (item 4) et le sol de la flaque : *non garde : `TRV-CE1-2`* et *non garde : `QMT-CE1-2`*, je les lis. Aucune liste de meteos ni de sols ne se ferme |

## 6. Ce que je ne demande pas

- Que les quatre items a la vapeur d'eau aient une autre bonne reponse que *elle devient de la vapeur d'eau* : c'est la competence,
  appliquee a quatre situations.
- Qu'un indice de vapeur dise ou va l'eau : ce serait la reponse, ou *ne se voit pas*, que ma regle 5 interdit.
- Une seconde bonne reponse *rien ne change* : le tiers et le nombre d'items en changeraient, et ma regle dit *au moins un*.

## 7. Cette lecture rouvre-t-elle une formulation ?

**Non.** Aucun texte de la story ni de mes trois arbitrages de `-05` n'est a reecrire. Le nuage de la casserole est la fumee blanche de
ma regle 3 sous un autre nom ; le brouillard au soleil est le defaut **b** d'A.273 ; la disparition des glacons est `TRV-CE1-2`
(*une seule acceptable*). Aucune ne pose une regle pour `-05` ni pour un autre lot. Rien a reporter par `camille-po`.

**Correction de mon propre ecrit, sans effet sur le paquet [corrige le 2026-10-27, `nadia-prof`, ce fichier, section 7]** : mon
exemple de 2.4 de `arbitrage-nadia-premiers-jets-ce1-qlm-04-05.md` donnait, pour la buee du miroir apres la douche, les distracteurs
*de la vapeur d'eau*, *de la glace*. *De la glace* sur un miroir apres une douche chaude est un etat que la temperature de la situation
rend impossible, ce que ma regle 3 refuse : mon exemple contredisait ma regle. `marc-dev` a ecrit *du brouillard*, qui la tient. Le
texte d'origine reste dans ce fichier ; la clause du critere 4 reportee dans la story ne cite pas ces distracteurs et ne change pas.

## 8. Cout

**Au bareme** (1,0 par lot, *"lecture avant `verify` et correction qui la suit comprises"*) : **1,0** a cette ecriture. Mes corrections
touchent trois items et une ligne de test : si `marc-dev` les applique telles qu'ecrites, leur relecture est une seconde lecture,
**0,5 hors marge** (`DEC-0120` A.272, A.270 point 4), soit **1,5**. Aucun seuil n'est ecrit pour `-05`, et je n'en deduis aucun. Je ne
convertis pas une duree en bareme : aucune regle ne le fait.

**Autocontrole A.273** : 3 items a corriger sur 12, contre 2 a `-04` et 5, 6, 8 a `-01`, `-02`, `-03`. Les trois relevent du defaut
**b**, et deux etaient dans les doutes que `marc-dev` a ecrits (items 4 et 8) : la procedure a fonctionne. Le troisieme (item 12) n'y
etait pas.

## 9. Duree et verifications

**Duree**, mesuree par `date` : debut **12:08:51**, fin **13:40:44**, apres les verifications et avant le commit, soit **1 h 31 min 53 s**, dont la mesure des
deux tests et trois executions du garde-fou des identifiants. **Ce chiffre surestime le travail, dit** : ma session s'est
arretee apres l'ecriture (12:27:18) et la premiere execution du garde-fou ; l'agent lanceur m'a relancee pour completer et
commiter. L'heure de l'arret n'a pas ete lue par `date` : je ne la retranche pas et je n'ecris pas de duree active estimee. Aucun seuil de duree ne s'applique a une lecture.

**Verifications avant commit**, ce fichier present dans l'arbre, base `bdfed668`. Il n'ecrit ni ne renomme aucun identifiant de
condition : `T1` a `T4`, `a-V`, `b-B3B4`... sont des reperes de demande, comme ceux de `marc-dev` et de `theo-archi`.

- `bash outils/mvn-jdk21.sh -B -pl bootstrap -am test -Dtest=ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest`, telle
  quelle : *"BUILD FAILURE"* sur `recreo-domain`, *"No tests matching pattern "ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest"
  were executed!"*. Le garde-fou n'a pas ete lance ; meme symptome que dans mes fichiers precedents.
- La meme commande avec `-Dsurefire.failIfNoSpecifiedTests=false`, premiere execution : *"Tests run: 10, Failures: 1"*, *"BUILD
  FAILURE"* : le garde-fou lisait comme identifiant non alloue une forme que j'avais ecrite en section 0 pour nommer l'unite de
  longueur de `String.length()`. **Faute de ma main**, corrigee en reformulant ces mots (*"longueur en caracteres comptes comme
  `String.length()`"*), sans allocation : ce n'etait pas un identifiant de condition.
- Seconde execution, ce fichier corrige : *"Tests run: 10, Failures: 0, Errors: 0, Skipped: 0"*, *"BUILD SUCCESS"*. Perimetre : ce
  seul test, module `bootstrap` et ses dependances ; ni un `verify`, ni la porte technique. Seules les lignes de duree de cette
  section ont ete ecrites apres elle ; elles ne portent aucun identifiant.
- `bash .github/scripts/verifier-chemins.sh < /dev/null` : *"Aucun chemin de poste de travail ni nom de compte dans les fichiers
  verifies."*, sortie 0.
- Aucun autre job de `.github/workflows/ci.yml` lance.

## 10. Routage

| A qui | Quoi |
|---|---|
| `marc-dev` | section 4, points 1 a 5, et la sonde `s12` |
| `theo-archi` | section 5, `T1` a `T4` |
| `sofia-sm` | le cout est en section 8 ; la seconde lecture, s'il y en a une, s'inscrit sur la ligne hors marge |
| `camille-po` | rien (section 7) |

## 11. Ce que ce document ne fait pas

Il ne prononce aucun Done et aucun verdict de lot, n'ecrit aucune condition neuve, n'alloue aucun identifiant, ne modifie ni le
paquet, ni un test, ni le jeu fautif, ni le catalogue, ni `DEC`, ni matrice, ni registre, ni backlog.

**Signe** : `nadia-prof`, s57, palier 5, 2026-10-27.
