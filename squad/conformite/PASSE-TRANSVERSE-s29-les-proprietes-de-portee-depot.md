# Passe transverse de s29 (`S29-3`) - les proprietes de portee "depot servi entier"

- **Auteur** : `nadia-prof`, **Instance A**. **Date** : 2026-09-06. **Sprint** : s29.
- **Objet** : les proprietes de **portee D** de la table 13.2 de mon arbitrage prealable de s29 -
  celles dont j'ai ecrit qu'elles **ne survivent pas a la parallelisation, par nature**, et
  qu'aucune repartition des lots ne sauve.
- **Clause qui la commande** : `PAR-4` - *"La passe transverse est une tache nommee, affectee, et
  payee. Elle porte sur le depot servi entier et non sur une couture. Prix : 0,5."*
- **Ce document ne prononce aucun Done pedagogique** et ne fait bouger aucune ligne de matrice. Il
  produit des faits et des motifs ; `camille-po` en fait des stories.
- **Perimetre mesure** : le **contenu servi** a `6b2edf1` - 54 paquets, **1056 items enumeres**,
  54 entrees de catalogue -, la mecanique qui l'affiche, et l'espace de noms des identifiants de
  conditions.

---

## 0. Pourquoi cette passe existe, et pourquoi elle n'est pas une passe de couture

Repris de mon arbitrage, parce que le reformuler affaiblirait le motif :

> *"En serie, les trouvailles transverses sont un sous-produit gratuit, parce que j'accumule
> l'image en avancant. La parallelisation ne les perd pas : elle transforme un sous-produit gratuit
> en poste de depense explicite. Elle se paie, se planifie, se nomme - ou elle disparait en
> silence."*

Et j'avais **refuse d'avance** la passe de couture qui parait la reponse evidente : *"la dette des
paquets a voix n'etait a aucune frontiere - aucun des six paquets concernes n'etait au perimetre du
sprint ou je l'ai vue."*

**Ce document confirme ce refus par sa premiere trouvaille.** `REV-1`, ci-dessous, n'est **a aucune
frontiere entre les deux instances** : elle touche des paquets de mon lot, des paquets du lot de
l'Instance B, et des paquets qu'aucune des deux n'avait au perimetre. **Une passe de couture ne
l'aurait pas trouvee. Une lecture de fichier non plus** - elle n'existe dans aucun fichier de
contenu.

---

## 1. `REV-1` - la phrase que la mecanique compose autour de la bonne reponse

**Nouvelle, datee du 2026-09-06. Portee D. Trouvee en jouant, jamais lisible dans le contenu.**

### 1.1 Le fait

Quand un enfant s'est trompe trois fois sur une manche, le produit revele la reponse. La phrase
qu'il lit est composee par la mecanique :

> `"C'est " + reponse + ". Appuie sur " + reponse + "."`

**Une seule expression, a un seul endroit du produit** (`PartieController`, chemin de la
revelation). Elle ne se lit correctement **que si la bonne reponse est un groupe nominal ou une
valeur**. Sur les paquets ou la bonne reponse est une proposition, elle donne :

> *"C'est ce n'est pas un etre vivant. Appuie sur ce n'est pas un etre vivant."*
> *"C'est il se nourrit. Appuie sur il se nourrit."*
> *"C'est elle suit des regles tres strictes et se deroule en public. Appuie sur..."*

**Une double copule, servie a un enfant de six ans qui apprend a lire, au moment precis ou il a
echoue trois fois et ou le produit est cense l'aider.**

### 1.2 La mesure, sur le contenu servi entier

Extraction sur les 54 paquets et les **1056 items enumeres** du contenu servi, par la forme de la
bonne reponse :

| Classe | Definition | Items | Paquets |
|---|---|---|---|
| **A - fautif** | la bonne reponse a un sujet conjugue en tete (`il`, `elle`, `ce n'est`) : *"C'est il pleut."* | **12** | **4** |
| **B - maladroit mais lisible** | `oui` / `non`, ou `a` + infinitif : *"C'est non."*, *"C'est a couper."* | **29** | **5** |

**Classe A, paquet par paquet** : `cp-qlm-cycle-de-vie` (5 items sur 11),
`cp-qlm-vivant-non-vivant` (3 sur 14), `cm1-comprehension` (3 sur 20), `cm1-histoire-geographie`
(1 sur 57).

**Classe B, paquet par paquet** : `cp-son-dans-mot` (12 sur 12), `cp-qlm-cycle-de-vie` (6),
`cm1-emc-droit-devoir` (5 sur 11), `cp-qlm-objet-technique` (5 sur 11), `cm1-comprehension` (1).

**Verifie a l'ecran, pas seulement calcule** : j'ai pousse jusqu'a la revelation, par appels HTTP
reels, sur `cp-qlm-vivant-non-vivant` (item du caillou), `cp-qlm-cycle-de-vie` (item du chien) et
`cp-qlm-objet-technique` (item des ciseaux). Les trois phrases lues a l'ecran sont exactement celles
que la formule predit.

### 1.3 Ce que ce fait dit de mon propre travail, et je le dis avant qu'on me le dise

- **Cette propriete n'est dans aucune de mes conditions.** Ni `CON-1` a `CON-4`, ni `FER-*`, ni
  `HIS-*`, ni `EMC-*`, ni `VOI-*` ne la nomment. **Ma table 13.2 elle-meme ne la classait pas** : elle
  range en portee I *"longueur, vocabulaire, lisibilite"* de l'item, ce qui laisse croire que la
  lisibilite est une propriete de l'item. **Elle ne l'est pas ici** : la phrase fautive n'existe ni
  dans l'item, ni dans le paquet, ni dans le lot - elle est produite par la rencontre d'un champ de
  contenu et d'un gabarit de mecanique. **J'ajoute cette famille a la portee D**, sous le nom : *la
  phrase que la mecanique compose autour d'un champ de contenu*.
- **Elle a traverse tous les sprints de contenu du depot.** Elle touche des lignes que j'ai fermees
  moi-meme, dont trois fermees aujourd'hui et une fermee par l'Instance B ce sprint.
- **Elle n'apparait qu'a la troisieme erreur.** Un audit qui joue proprement, sans s'acharner, ne la
  voit jamais. **Elle est la demonstration que "j'ai joue" ne suffit pas : il faut jouer mal, et
  jusqu'au bout.**

### 1.4 Ce que je demande, et ce que je ne demande pas

**Je decris le symptome. Je ne prescris pas la solution** : la cause est dans le code de l'adapter
web, et **c'est `theo-archi` qui traite les causes techniques**, pas moi.

- **Ce qui doit etre vrai a la fin** : l'enfant qui echoue trois fois lit **une phrase francaise
  correcte** qui lui designe la reponse, quelle que soit la forme grammaticale de cette reponse.
- **Deux chemins existent** et je n'en choisis aucun : changer la phrase composee, ou contraindre la
  forme des bonnes reponses. **Je signale seulement que le second seul ne suffirait pas** - `oui`,
  `non` et les infinitifs sont des reponses legitimes que je ne retirerai pas d'un paquet de CP.
- **La classe A est prioritaire sur la classe B** : 12 items, 4 paquets. La classe B est maladroite,
  pas fautive.
- **Prix de ma reverification quand ce sera fait** : **0,25**, une passe de lecture d'ecran - a
  condition que je puisse la voir a l'ecran, ce qui suppose que le correctif soit livre et jouable.

---

## 2. `CONS-1` - la consigne generique a double en un sprint

**Nouvelle, datee du 2026-09-06. Portee D** : elle vit dans l'homogeneite de registre entre les jeux
qu'un meme enfant enchaine, et **aucun audit de lot ne peut la voir** - dans un lot, une consigne
generique n'a l'air de rien.

### 2.1 Le fait, mesure sur trois etats du catalogue

| Arbre | Jeux au catalogue | Jeux portant *"Lis bien la question, puis choisis la bonne reponse."* |
|---|---|---|
| `790584e` (cloture de s28) | 48 | **5** |
| `961565d` (ouverture de s29) | 48 | **5** |
| `6b2edf1` (aujourd'hui) | **54** | **10** |

**Les six jeux ajoutes a s29 en ont pris cinq.** La consigne generique est desormais celle de
**toutes** les matieres ouvertes depuis s27 : questionner le monde CP (5 jeux),
histoire-geographie CM1, anglais CM1, EMC CP (2 jeux), EMC CM1.

### 2.2 Pourquoi c'est un defaut, et c'est ma propre regle

J'ai ecrit a s28, en payant la dette des paquets a voix : *"la consigne (entree de catalogue) porte
la tache ; l'enonce (item) porte ce qui est propre a l'item."* **Sur ces dix jeux, la consigne ne
porte aucune tache** : elle dit a l'enfant de lire et de choisir, ce que la forme de l'ecran lui dit
deja. Comparee aux consignes qui travaillent - *"Combien manque-t-il pour faire 10 ?"*, *"Quel signe
faut-il mettre ?"*, *"Dis le mot. Quel son entends-tu ?"* - c'est une ligne de lecture offerte a un
enfant qui lit peu, en echange de rien.

**Et le cout se paie a l'enchainement** : un enfant de CP qui joue trois jeux de questionner le monde
d'affilee lit trois fois la meme phrase inutile. **C'est exactement la meme famille de defaut que la
redondance enonce/consigne que je viens de faire payer sur les cinq paquets a voix**, deplacee d'un
cran.

### 2.3 Ce que je demande

**Non bloquant.** Aucune ligne ne se rouvre : les enonces portent la tache et les jeux
s'apprennent. **Ce que je demande, sans fournir les dix textes** : que chaque entree de catalogue
porte une consigne qui **nomme la tache de son jeu**, comme le font 44 des 54 entrees. Je ne dicte
pas dix consignes d'un coup - une consigne se juge contre le paquet qu'elle introduit, et je les
rejouerai. **Regime : rejeu borne, 0,5**, dit d'avance.

**Ce que je demande en plus, et qui ne coute rien** : que la prochaine entree de catalogue **ne
reprenne pas** cette consigne. La tendance mesuree est de +5 par sprint ; c'est elle qui rend le
point urgent, pas les dix jeux d'aujourd'hui.

---

## 3. La dette des paquets a voix - **payee**, verifiee sur les six, et un jeu voisin ecarte

**Portee D declaree** : *"la coherence entre ce qu'un indice demande a l'adulte et ce que `VOI-3`,
`VOI-5`, `VOI-8` lui interdisent."* C'est la dette qui m'a fait ecrire `PAR-4`.

**Etat au 2026-09-06 : payee.** Les six jeux verifies un par un sur le contenu servi ; le detail est
dans chacune des six fiches. Resume :

| Jeu | Enonce | Indice |
|---|---|---|
| `cp-position-syllabe` | corrige, mot pour mot | corrige, **deux** defauts payes |
| `cp-son-dans-mot` | corrige, mot pour mot | corrige, **le pire des cinq** |
| `cp-lettre-du-son` | corrige, mot pour mot | corrige |
| `cp-digraphe-du-son` | corrige, mot pour mot | corrige |
| `cp-voyelle-du-son` | corrige, mot pour mot | corrige |
| `cp-mot-dicte` | inchange, comme demande | inchange, conforme a `VOI-8` |

**Aucune occurrence de *doucement* ni de *lentement* ne subsiste dans un indice de paquet a voix** -
verifie par extraction sur les 54 fichiers de contenu, hors commentaires.

**Un jeu que l'extraction fait remonter et que j'ecarte, pour qu'on ne le rouvre pas** :
`bandit-manchot-syllabes-cp` porte trois indices qui disent *"tout doucement"* et *"tres lentement"*.
**Ce n'est pas un defaut `VOI-3`** : ce jeu **ecrit le mot a l'ecran** et c'est **l'enfant** qui le
prononce ; il n'a aucun `texteAVoixHaute` ni aucune `consigneAdulte`. Ralentir pour frapper les
syllabes est le geste de classe correct. **Verifie sur le catalogue et sur le contenu, pas suppose.**

**Ce que ce paiement ne prouve pas** : `VOI-3`, `VOI-5` et `VOI-8` portent sur ce que l'adulte **fait
de sa voix**, et personne ne le garde, **pas meme moi**. Ce qui est repare, c'est que **le produit
cessait de demander par ecrit le contraire de ce qu'il demande par ailleurs a l'adulte**.

---

## 4. `PAR-5` - l'espace de noms des identifiants : aucune collision creee par s29

**Portee D declaree** : *"l'unicite de sens d'un identifiant de condition."*

**Aucun registre n'existe** - `camille-po` l'a route a `theo-archi` sans le trancher (`DEC-0077`,
section 8.7), et **aucune story de s29 ne le construit** : c'est une decision actee, pas une
omission. En attendant, **le registre c'est moi**, et cette section en tient lieu pour s29.

**Ce que j'ai verifie** : les identifiants **mintes pendant s29** ne collisionnent avec rien.

| Identifiant | Auteur | Collision ? |
|---|---|---|
| `PAR-0` a `PAR-8` | moi, 2026-09-05 | **aucune** - le prefixe `PAR` n'existait nulle part avant |
| `CYC-1` | moi, 2026-09-05 | **aucune** - prefixe `CYC` neuf |
| `EMC-10` | moi, 2026-09-05 | **aucune** - `EMC-1` a `EMC-9` existaient, le 10 etait libre |
| `HG-R6`, `HG-R7` | moi, aujourd'hui | **aucune** - `HG-R1` a `HG-R5` occupes |
| `POS-R1`, `POS-R2` | moi, s29 | **aucune** |

**Verification faite sur un point que j'aurais pu manquer** : `DES-1` a `DES-7` sont cites a la fois
par mon arbitrage du palier 2 (`S29-9`) et par les documents d'`ines-design`. **Ce n'est pas une
collision** : les deux emploient le meme sens (les conditions de ce qu'un dessin servi doit
declarer), verifie sur `DES-6` en particulier, qui dit *"un chiffre n'est jamais redessine deux
fois"* dans les quatre fichiers qui le portent.

**Identifiants que j'alloue aujourd'hui, et je l'ecris parce que `PAR-5` demande qu'une allocation
soit visible avant d'ecrire** :

| Identifiant | Sens | Ou il vit |
|---|---|---|
| `REV-1` | la phrase composee par la mecanique autour de la bonne reponse | ce document, section 1 |
| `CONS-1` | une consigne de catalogue nomme la tache de son jeu | ce document, section 2 |
| `CYV-R1` | l'item de la plante, `cp-qlm-cycle-de-vie` | `FICHE-bandit-manchot-cycle-de-vie-cp.md` |
| `OBJ-R1`, `OBJ-R2` | titre du jeu, distracteurs de la tache fonction | `FICHE-bandit-manchot-objet-technique-cp.md` |

**Les prefixes `REV`, `CONS`, `CYV` et `OBJ` n'existaient nulle part dans le depot avant ce jour** -
verifie par extraction du motif `PREFIXE-nombre` sur `squad/`, `docs/`, tous les `.java` et tous les
`.yaml`.

**Ce que cette section ne fait pas** : elle ne construit pas le registre. **Elle mesure que s29 n'a
pas aggrave le probleme**, et rien de plus. Le risque que `PAR-5` couvre reste entier pour s30, et il
grandit d'un prefixe a chaque sprint.

---

## 5. La coherence de mes propres conditions entre elles - deux ecarts, tous deux de moi

**Portee D declaree** : *"la coherence de mes propres conditions entre elles."* C'est la ligne de ma
table que je suis le plus mal placee pour tenir, et c'est exactement pourquoi elle y figure.

### 5.1 Ma section 7.2 price six reserves comme verbatim ; deux ne l'etaient pas

**Corrige, et le detail est dans `FICHE-bandit-manchot-anglais-cm1.md`, section 2 du bloc de s29.**
`marc-dev` a signale la contradiction sur `VIV-R2` ; **elle vaut aussi sur `ANG-R1`, et c'est moi qui
l'etends** - je n'avais fourni aucun texte de remplacement pour l'item de *montagne*, seulement le
motif du refus.

**Consequence** : le regime du mot pour mot ne couvre que quatre des six reserves. Les deux autres
appelaient un texte neuf, donc un jugement. **J'ai rendu ce jugement aujourd'hui** - `hill`/`colline`
est accepte, `VIV-R2` est tranchee - plutot que de facturer un supplement.

**La lecon, et elle est generale** : *"piste que je nomme sans la prescrire"* est une formule qui met
une reserve hors d'atteinte de l'equipe **et** la fait entrer a tort dans mon tarif le moins cher.
**Une reserve que je n'assortis pas d'un texte, ou d'un critere qu'un tiers peut appliquer sans me
redemander ce que je voulais dire, n'est pas une reserve : c'est une note.**

### 5.2 Un compte de moi qui a vieilli

Mes arbitrages de s28 et de s29 parlent de **neuf lignes `en cours`** en histoire-geographie CM1. Le
chiffre etait exact quand je l'ai ecrit ; il ne l'est plus depuis la fermeture de deux lignes a s28
et de trois aujourd'hui. **Il en reste six.** Signale a `camille-po` pour que le chiffre ne soit pas
recopie ; **je ne touche pas la matrice**.

---

## 6. La continuite cycle 2 / cycle 3 (`CYC-1`) - **vacante**, et ce n'est pas *tenue*

`CYC-1` demande qu'un item de cycle 3 ne contredise **aucun item de cycle 2 deja servi** sur le meme
objet.

**Etat mesure** : les deux heritieres de *questionner le monde* au cycle 3 sont *sciences et
technologie* et *histoire-geographie*. **Aucun paquet de sciences et technologie CM1 n'est servi** -
le lot a ete laisse dehors par la variante retenue. Et **aucun paquet de questionner le monde CP ne
sert un objet d'histoire ou de geographie** : `QLM-CP-14` (frise) et `QLM-CP-15` (autrefois et
aujourd'hui) ne sont pas servies.

**`CYC-1` est donc vacante partout aujourd'hui, et non tenue.** Les deux mots ne disent pas la meme
chose, et faire passer l'un pour l'autre serait annoncer une verification qui n'a pas eu lieu.

**Ou elle mordra en premier, dit d'avance** : la ligne 1 des sciences CM1 (trois etats de la matiere)
contre `QLM-CP-05` (deux etats), que j'ai fermee **au mot pres** en refusant d'avance tout item de CP
mentionnant la vapeur. **L'ajout du gaz au CM1 est une progression et il est permis ; ce qui est
refuse, c'est qu'un item de CM1 dise que le CP avait tort.**

*Un point de coherence que j'ai verifie parce qu'il etait a ma portee* : entre les cinq paquets de
questionner le monde CP servis, aucune contradiction. L'indice de l'arbre sur
`cp-qlm-vivant-non-vivant` (*"un arbre nait, grandit et a des petits (ses graines)"*) et l'item de la
plante sur `cp-qlm-cycle-de-vie` (*"Une plante peut-elle avoir des petits ?"* -> oui, *"Ses graines
donneront de nouvelles plantes"*) **enseignent la meme chose**. C'est de la portee M, pas D, mais je
l'ecris parce que c'etait le risque le plus previsible d'un quatrieme paquet dans une matiere.

---

## 7. Ce que cette passe **n'a pas** couvert

**Dit explicitement, parce qu'une passe transverse dont on ne connait pas les bords laisse croire au
depot qu'il est ratisse.**

- **Je n'ai pas joue les 54 jeux.** J'en ai joue six en entier ce jour (`cycle-de-vie`,
  `objet-technique`, `vivant-non-vivant`, `histoire-geographie`, `son-dans-mot`, et une partie de
  reprise), et j'ai mesure les 1056 items par extraction. **`REV-1` est le seul defaut d'ecran que
  cette passe a cherche** ; il en existe peut-etre d'autres du meme genre, produits par la rencontre
  d'un champ de contenu et d'un gabarit, et je ne les ai pas cherches.
- **Je n'ai pas relu les lots de l'Instance B.** C'est ma propre specification (*"aucune des deux
  instances ne relit l'autre"*) et je m'y tiens. `cm1-emc-droit-devoir` apparait dans la mesure de
  `REV-1` **comme donnee de portee depot**, jamais comme reexamen de son verdict : **je ne reviens
  pas sur ce qu'elle a prononce**, et la ligne qu'elle a fermee reste fermee.
- **Je n'ai pas verifie l'unicite de sens des 147 identifiants existants**, seulement l'absence de
  collision sur ceux que s29 a mintes. La verification complete demande de lire chaque definition ;
  c'est le registre de `PAR-5`, et il n'est pas construit.
- **Je n'ai pas mesure l'homogeneite de registre au-dela des consignes.** Titres, messages de
  reussite, ecrans de fin : `CONS-1` ne porte que sur la consigne. *Un titre m'a saute aux yeux en
  passant - `OBJ-R1` - mais c'est une trouvaille de lot, pas une mesure de depot.*

---

## 8. Ce que je route

| Fait | A qui | Ce qu'il en fait |
|---|---|---|
| **`REV-1`** - la phrase de revelation est fautive sur 12 items de 4 paquets, maladroite sur 29 de 5 autres. Symptome pedagogique, **cause technique** | `theo-archi` pour la cause, `camille-po` pour la story | Je dis ce qui doit etre vrai a la fin ; ni la forme ni la priorite ne m'appartiennent |
| **`CONS-1`** - la consigne generique est passee de 5 a 10 jeux en un sprint | `camille-po` | Rejeu borne, 0,5. **Et le geste gratuit : la prochaine entree ne la reprend pas** |
| **La dette des paquets a voix est payee**, six jeux verifies, un septieme ecarte avec son motif | `sofia-sm`, `camille-po` | La dette de s26 est close. `VOI-3` reste sans garde-fou et le restera |
| **Aucune collision d'identifiant creee par s29** ; quatre prefixes alloues ici | `theo-archi` | Le registre de `PAR-5` reste entier a construire, et il grandit chaque sprint |
| **Ma section 7.2 pricait deux reserves a tort au regime verbatim** (`ANG-R1`, `VIV-R2`) | `camille-po`, `sofia-sm` | Corrige par moi, sans supplement facture |
| **Le compte de neuf lignes `en cours` en histoire-geographie a vieilli : il en reste six** | `camille-po` | A ne pas recopier |
| **`CYC-1` est vacante partout, pas tenue** ; elle mordra sur les etats de la matiere | `camille-po`, developpeurs | A rejouer le jour ou un paquet de sciences CM1 est servi |
| **Ma table 13.2 ne classait pas la famille de `REV-1`** ; je l'ajoute a la portee D | moi, pour s30 | *La phrase que la mecanique compose autour d'un champ de contenu* |

---

## 9. Ce que cette passe a coute, et ce qu'elle a rapporte

**Prix : 0,5**, exactement ce que `PAR-4` avait chiffre. Elle a lu beaucoup et n'a juge la pedagogie
d'aucun item.

**Ce qu'elle a rapporte, et c'est la question que l'utilisatrice posera** : **deux defauts que rien
d'autre dans ce sprint n'aurait trouves.** `REV-1` n'est dans aucun fichier de contenu, dans aucun
test, a aucune frontiere entre les deux instances, et n'apparait qu'a la troisieme erreur d'un enfant
sur une manche. `CONS-1` n'est visible que sur trois etats successifs du catalogue entier.

**Et un fait de methode qui vaut pour la suite** : la parallelisation **n'a pas cause** ces deux
defauts - ils sont anterieurs a s29. **Elle les aurait laisses dormir**, parce qu'en serie je les
aurais rencontres en accumulant, et qu'en parallele personne n'accumule le depot entier. **C'est
exactement ce que `PAR-0` predisait, et c'est verifie plutot que suppose.**
