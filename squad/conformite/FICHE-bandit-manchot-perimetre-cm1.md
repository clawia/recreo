# Fiche de conformite - bandit-manchot-perimetre-cm1

- **Mecanique** : bandit manchot (mecanique generique, partagee avec les autres jeux du
  catalogue).
- **Contenu(s)** : `adapters/adapter-content/src/main/resources/contenu/cm1-perimetre.yaml` (a
  creer, `type: question`, au moins 20 items), story `S12-1` (`docs/backlog/backlog.md`). Matiere
  premiere identifiee : `contenu-repris/quiz/cm1-perimetre.yml` (8 de ses 11 questions calculent un
  perimetre de carre ou de rectangle a partir de cotes donnes en texte ; 2 portent sur un triangle
  equilateral, hors du perimetre exact de cette ligne, non repris ; 2 sont definitionnelles,
  conservables mais ne comptent pas comme du calcul). Reprise = reprise de la matiere (enonces,
  valeurs), pas de la structure du fichier (`contenu-repris/README.md`) : chaque item repasse devant
  `nadia-prof` comme un contenu neuf, et chaque item doit porter un `indice`, absent du fichier
  source.
- **Statut global** : **VALIDE SOUS RESERVE** par `nadia-prof` le 2026-08-14, apres avoir joue
  l'increment (S12-1). Trois reserves ouvertes, dont la premiere corrige ma propre condition P-6.
  Detail dans le bloc de validation.
- **Arbitrage prealable de mecanique en vigueur pour tout le catalogue** (`squad/conformite/
  FICHE-bandit-manchot-additions-cp.md`, branche `s10-nadia-mecanique` et audits suivants) :
  conditions M1 a M13, dont deux ont ete bloquantes pour d'autres jeux du catalogue - M4 (le message
  d'erreur doit etre vu sans defiler a 360 x 640) et la clause 1 de M13 (apres une reponse, la
  tabulation ne detruit jamais la partie sans confirmation). Ces deux points devront etre mesures sur
  ce jeu une fois son contenu et son rendu reels disponibles ; rien n'est mesure ici a l'ouverture de
  la fiche, faute d'increment a mesurer.
- **Complement du 2026-08-15** : **ma reserve 1 ne suffit pas, et P-6 change pour les lots a venir.**
  Le verdict du 2026-08-14 et ses trois reserves sont **inchanges**, le Done reste ouvert, le lot
  livre n'est pas rouvert. Mais P-6, qui nommait exactement deux familles de distracteurs (l'aire et
  la moitie du perimetre), **produit mecaniquement le rang intermediaire** et refabriquera le meme
  defaut au lot suivant. Nouveau texte, date et non retroactif : **quatre familles permises**, dont
  la formule du carre appliquee au rectangle (`4 x L`), qui est l'erreur la plus frequente en classe
  et qui depasse **toujours** le perimetre ; et surtout une **condition d'aboutissement** portant sur
  le resultat et non sur la liste - *aucun des trois rangs de la bonne reponse ne doit etre
  majoritaire sur un paquet*. Chiffres du 2026-08-14 reconfirmes en rejouant : **91,7 %** de reussite
  en pressant toujours le nombre du milieu (110 manches sur 120), **90,0 %** au comptage exact.
- **Derniere mise a jour** : 2026-08-15 (complement de `nadia-prof` sur P-6)

## Competences visees

| Matiere | Niveau | Domaine | Competence | Reference au referentiel |
|---|---|---|---|---|
| mathematiques | CM1 (cycle 3) | Grandeurs et mesures | Calculer un perimetre de figures usuelles (carre, rectangle) | `squad/programme/mathematiques/competences-cm1.md` |

**Perimetre exact, assume a l'ouverture** : le referentiel nomme "carre" et "rectangle", pas de
triangle ni d'autre polygone. Un Done pedagogique accorde sur un paquet qui ne porterait que ces
deux figures couvre la ligne en entier, sans reste connu a ce jour - a la difference des lignes
partielles deja rencontrees sur ce produit (additions "jusqu'a 10 (puis 20)", homophones
"principaux", comparer/ranger).

## Criteres pedagogiques attendus - camille-po

- [ ] Chaque item sur un carre donne un seul cote (nombre entier, unite explicite) ; la bonne
  reponse vaut quatre fois ce cote, dans la meme unite.
- [ ] Chaque item sur un rectangle donne une longueur et une largeur (deux entiers distincts, meme
  unite) ; la bonne reponse vaut deux fois leur somme, dans la meme unite.
- [ ] Le paquet compte au moins autant d'items sur le carre que sur le rectangle, a un pres.
- [ ] Chaque item porte exactement trois propositions : la bonne reponse et deux distracteurs
  numeriques plausibles (confusion avec l'aire, moitie du perimetre), jamais un nombre arbitraire.
- [ ] Aucun item ne porte de figure autre que le carre ou le rectangle.
- [ ] Le paquet compte au moins 20 items.
- [ ] Chaque item porte un indice non vide, exact et comprehensible par un enfant de CM1.
- [ ] Le paquet valide le schema `type: question` (`ADR-0007`) sans code Java neuf, sans gabarit ni
  feuille de style neuve.

---

## Bloc de validation pedagogique - nadia-prof UNIQUEMENT

> Ce bloc n'est jamais pre-rempli ni anticipe par un autre agent.

### Complement du 2026-08-15 - ma reserve 1 ne suffit pas : P-6 doit changer pour les paquets a venir, et voici son nouveau texte

> **Ce que cette section fait, et ce qu'elle ne fait pas.** Elle ne touche **ni** le verdict du
> 2026-08-14 **ni** aucune de ses trois reserves : le Done pedagogique de ce jeu reste **ouvert**, la
> ligne reste eligible a `couvert-valide`, et **le lot livre n'est pas rouvert**. Elle repond a une
> question qui m'est posee - ma reserve 1 couvre-t-elle tout ? - et la reponse est **non** : elle
> couvre le paquet livre, elle ne couvre pas les paquets que ma condition P-6 fera ecrire demain.

- **Date** : 2026-08-15. **Auteur** : nadia-prof. **Sprint** : s12.
- **Mode** : **j'ai rejoue**, jar reconstruit avec un JDK 21. **120 manches** jouees en pressant
  toujours le nombre intermediaire des trois : **110 gagnees, 91,7 %**. Le comptage exact sur les 20
  items de calcul du paquet donne **90,0 %** (18 sur 20). Mes deux chiffres du 2026-08-14 sont donc
  **confirmes**, l'un par le jeu, l'autre par le compte. Je ne corrige rien de ma reserve 1.

#### Ma reserve 1 couvre le paquet livre, et rien au-dela

Elle est ecrite comme un fait sur `cm1-perimetre.yaml` : le taux, la cause, et un moyen actionnable
sur **ce** paquet (augmenter la part des figures dont l'aire est inferieure au perimetre). Tout cela
tient et je n'y touche pas.

Mais **P-6 n'est pas un fait sur un paquet, c'est une condition**, et une condition sert au lot
suivant. Telle que je l'ai ecrite, elle nomme **exactement deux** familles de distracteurs et
**interdit les autres** : l'aire et la moitie du perimetre. Or la moitie du perimetre est
**toujours** inferieure au perimetre, et l'aire ne lui est superieure qu'a partir d'un cote de 5.
Consequence arithmetique, verifiee figure par figure : un carre de cote 5 ou plus ne **peut pas**
donner autre chose que le rang intermediaire, et aucun carre ne peut jamais donner le rang le plus
petit. **La condition, et non celui qui l'applique, produit le resultat.**

Donc : celui qui ecrira le prochain paquet de perimetre, ou d'aire, ou tout paquet de grandeurs
adosse a P-6, refabriquera le meme defaut en tenant ma condition a la lettre - exactement comme
`marc-dev` l'a fait, sans devier d'un mot. **Une reserve corrige un paquet ; seule une condition
neuve empeche le suivant.**

#### Le nouveau texte de P-6, applicable aux lots a partir du 2026-08-15

Je l'ecris comme une **condition neuve et datee**, pas comme un durcissement retroactif de l'ancienne.
**L'ancienne P-6 reste lisible dans mon arbitrage prealable et le lot de `marc-dev` a ete juge
contre elle**, correctement. Un lot deja livre ne devient pas fautif parce que j'ecris mieux
aujourd'hui.

**P-6 (version du 2026-08-15).** Les distracteurs d'un item de perimetre sont des erreurs reelles
d'enfant, prises dans cette liste - qui est un **plancher de ce qui est permis**, pas une
prescription de ce qu'il faut mettre sur chaque item :

| Famille | Valeur | Position par rapport a la bonne reponse |
|---|---|---|
| l'aire | `c x c` ou `L x l` | au-dessus des que le cote depasse 4, en dessous sinon |
| la moitie du perimetre | `2 x c` ou `L + l` | toujours en dessous |
| un cote oublie | `3 x c` ou `2L + l` | toujours en dessous |
| **la formule du carre appliquee au rectangle** | `4 x L` | **toujours au-dessus**, puisque `L > l` |

La quatrieme est celle qui manquait, et c'est **la plus frequente des quatre en classe** : l'enfant
qui a retenu "le perimetre, c'est fois quatre" l'applique au rectangle. Elle est donc plausible au
premier chef, et elle depasse le perimetre **toujours**, sans condition sur la taille des cotes.

**Et la condition d'aboutissement, qui est la vraie nouveaute** : la liste des familles ne suffit
pas, parce que c'est en croyant fermer la question par une liste que je l'ai ouverte. Ce que P-6
exige desormais porte sur le **resultat** : *sur l'ensemble d'un paquet, aucun des trois rangs de la
bonne reponse - la plus petite, l'intermediaire, la plus grande - ne doit etre majoritaire.* Celui
qui ecrit le paquet choisit ses familles et ses figures comme il veut, du moment que le compte final
tient. C'est verifiable par un tiers sans me le redemander, et c'est la meme propriete que le
garde-fou generique de `lea-dev` mesure.

**Une precision de securite, heritee de ma reserve 1 et de P-7, a ne pas perdre en chemin** : deux
propositions egales sont interdites, donc le carre de cote 2 (aire 4, moitie 4) et le carre de
cote 4 (perimetre 16, aire 16) restent exclus, ainsi que le rectangle 3 x 6 (perimetre 18, aire 18).
Elargir la liste des familles multiplie ces collisions : elles se verifient item par item.

#### Ce que je demande, et a qui

- A `camille-po`, via `sofia-sm` : une story pour le nouveau texte de P-6, qui vaut pour tout lot de
  grandeurs et mesures a venir. Ma reserve 1 reste ce qu'elle est - une correction du paquet livre -
  et les deux ne se remplacent pas.
- A personne : aucune reprise de `cm1-perimetre.yaml` n'est exigee par cette section. Elle l'etait
  deja par la reserve 1, au meme niveau, ni plus ni moins.

#### Une propriete dont je reste la seule garde, en plus de celles du 2026-08-14

Le garde-fou de `lea-dev`, une fois fusionne, prendra le **taux majoritaire** de ce paquet - il le
verra rouge a 90,0 %, ce qui est le comportement attendu et non un incident. Il ne verra pas, en
revanche, qu'ici **le rang le plus petit est a 0,0 %** : la bonne reponse n'est **jamais** la plus
petite des trois, sur les 20 items. Un enfant qui apprend "jamais le plus petit" elimine une
proposition sur trois, gratuitement, **a chaque manche**. C'est la reserve neuve que j'ouvre le meme
jour dans `FICHE-bandit-manchot-additions-cp.md`, section 6, et `cm1-perimetre` en est le cas le plus
net du catalogue. Non bloquant aujourd'hui, aucun Done retire.

### Done pedagogique du 2026-08-14 - VALIDE SOUS RESERVE (S12-1, paquet `cm1-perimetre.yaml`)

- **Verdict** : **VALIDE SOUS RESERVE**. Le Done pedagogique est ouvert. `camille-po` peut passer la
  ligne "Calculer un perimetre de figures usuelles (carre, rectangle)" a `couvert-valide`, sans
  mention de couverture partielle : le referentiel ne nomme que ces deux figures et le paquet les
  porte toutes les deux.
- **Auteur** : nadia-prof. **Increment** : branche `round-two`, tete `b396234`, 455 tests, 28 regles.
- **Conditions de reference** : mes 27 conditions du
  `squad/conformite/ARBITRAGE-PREALABLE-s12-mathematiques-cm1.md`, section 2 (P-1 a P-10).
- **Mode** : **j'ai joue**, ce n'est pas un audit degrade. Jar reconstruit depuis mon propre
  worktree avec un JDK 21, application lancee, jeu joue au navigateur - manches justes, manches
  fausses, trois echecs jusqu'a la revelation, parties completes de dix manches, a 360 x 640.
  Releves : `releves-s12/releve-jeu-cm1.json`, `releves-s12/releve-m4-cm1-360x640.json`,
  `releves-s12/releve-rang-bonne-reponse.json`,
  `releves-s12/perimetre-echec-avec-indice-360x640.png`.

#### Ce que j'ai verifie moi-meme, parce qu'aucun automate ne le couvre

1. **Exactitude arithmetique : 22 items sur 22 justes.** J'ai recalcule chaque perimetre, chaque
   aire et chaque moitie. Les dix carres (cotes 3, 5, 6, 7, 8, 9, 10, 11, 12, 15) et les dix
   rectangles (6x4, 8x3, 12x5, 7x2, 9x4, 15x6, 20x5, 11x3, 14x6, 13x5) sont exacts, et les vingt
   distracteurs valent bien l'aire et la moitie du perimetre annoncees. Aucune erreur de fait.
2. **P-7, le piege que j'avais nomme, est evite.** Le carre de cote 4 (perimetre 16, aire 16) et le
   rectangle 3 x 6 (perimetre 18, aire 18) sont **absents du paquet**. C'etait la condition la plus
   facile a manquer et elle est tenue.
3. **P-1 a P-6, P-8, P-9 tenues.** Entiers de 3 a 20, aucun decimal ; centimetre et metre seulement,
   la meme unite dans l'enonce et dans les trois propositions, l'unite ecrite partout ; jamais deux
   unites dans un item ; aucun item inverse ; deux items definitionnels, pas un troisieme ; enonces
   d'une seule phrase interrogative, 15 mots au plus ; quatre indices distincts qui donnent la
   methode et jamais le resultat.
4. **Utilite de l'indice, verifiee en la lisant apres un echec reel** : "Multiplie la longueur d'un
   cote par quatre." et "Additionne la longueur et la largeur, puis multiplie par deux." sont des
   methodes executables par un CM1, pas des paraphrases. Elles aident.
5. **Aucun biais, aucun presuppose social** : le paquet est entierement en enonce geometrique, il ne
   suppose rien de la vie de l'enfant.

#### Reserve 1 (2026-08-14) - la bonne reponse est le nombre du milieu neuf fois sur dix, et c'est ma condition P-6 qui le produit

**Le fait, mesure.** Sur les 20 items de calcul, la bonne reponse est **le nombre intermediaire des
trois dans 18 cas**, la plus grande dans 2, et **la plus petite dans aucun**. Un enfant qui ne sait
rien et presse systematiquement le nombre du milieu reussit **90 %** des manches, contre 33,3 % par
hasard. Verifie aussi en jouant, par la strategie symetrique : "toujours le plus grand nombre" ne
donne que 8,3 % (10 manches sur 120 jouees), ce qui confirme que la bonne reponse n'est presque
jamais la plus grande.

**La cause est ma propre condition, et je le dis avant de m'appuyer sur le constat.** P-6 nomme
exactement deux familles de distracteurs et interdit les autres : l'aire et la moitie du perimetre.
Or la moitie du perimetre est **toujours** inferieure au perimetre, et l'aire lui est superieure des
que les cotes depassent 4. La consequence arithmetique est mecanique : la bonne reponse tombe au
milieu. **`marc-dev` n'a pas devie de ma condition, il l'a appliquee** ; c'est la condition qui
etait courte. Je ne durcis pas P-6 apres coup et je ne refuse pas ce lot pour cela : j'ouvre une
reserve neuve et datee.

**Ce qui est actionnable, et la contrainte a ne pas ignorer.** Les erreurs reelles de perimetre sont
presque toutes inferieures au perimetre (oublier un cote, n'additionner que la longueur et la
largeur) ; l'aire est la seule famille plausible qui passe au-dessus. On ne peut donc pas equilibrer
ce paquet en inventant des distracteurs. Le moyen qui reste, et il suffit : **augmenter la part des
figures dont l'aire est inferieure au perimetre**, c'est-a-dire les petits cotes - le carre de
cote 3 et le rectangle 7 x 2 le font deja, ils sont 2 sur 20. Le carre de cote 1 (perimetre 4, aire
1, moitie 2) et des rectangles a cote 1 ou 2 conviennent ; **le carre de cote 2 est a exclure**
(aire 4 et moitie 4, deux propositions egales). Le but est de casser la constance, pas d'atteindre
un equilibre parfait.

#### Reserve 2 (2026-08-14) - a 360 x 640, l'enfant ne voit ni le message d'erreur ni l'indice

**Mesure sur 67 etats "premier echec" joues : le bas du message est sous le pli 67 fois sur 67**, de
**27 a 75 px** au-dela, et le bas de l'indice **67 fois sur 67**, de **111 a 159 px** au-dela. Pire
cas releve : "Quel est le perimetre d'un rectangle de longueur 11 cm et de largeur 3 cm ?", message
a 715 px et indice a 799 px pour un pli a 640. La capture
`releves-s12/perimetre-echec-avec-indice-360x640.png` montre ce que l'enfant voit : l'enonce sur
quatre lignes, les trois propositions, la marque barree sur celle qu'il vient d'essayer - et rien
d'autre. Le jeu ne lui dit pas ce qui s'est passe, et ne lui donne pas l'aide qui le debloquerait.

**Ce n'est pas un motif de refus, et je motive au lieu de decreter.** **M4 ne bloque plus depuis
`DEC-0025`**, ou l'utilisatrice a assume par ecrit le defaut de pli du sprint 10. Je ne la
ressuscite pas comme condition bloquante sur un lot qui n'en etait pas averti. Mais le fait est
neuf et il merite d'etre ecrit : **"Le perimetre" entre au catalogue avec ce defaut sur 100 % de ses
echecs**, la ou "Calcul CP" le tenait. Ses enonces sont les plus longs des trois paquets de ce
sprint, et c'est ce qui pousse tout le reste sous le pli. Sur l'echelle de `DEC-0025`, il se place
entre "Comparer les nombres" (-11 px) et "Petits problemes" (-107 px).

#### Reserve 3 (2026-08-14) - aucune mise en situation, le mot "perimetre" n'est jamais accroche a un objet

P-10 autorisait jusqu'a un tiers d'enonces habilles et disait qu'ils sont bienvenus ; le paquet n'en
porte **aucun**. C'est conforme a la lettre de ma condition, qui etait un plafond et non un plancher,
et je ne le compte pas comme un manquement. Mais un CM1 qui fait vingt calculs de contour sans
jamais rencontrer une cloture, une bordure ou un cadre apprend une formule plutot qu'une grandeur.
Deux ou trois items habilles - une cour d'ecole, un terrain de sport, un cadre de photo - suffiraient.

#### Deux corrections de fiche, pour `camille-po`

- La ligne **Contenu(s)** de cette fiche annonce toujours "8 de ses 11 questions" transposables
  depuis `contenu-repris/quiz/cm1-perimetre.yml`. **Elles sont sept**, comme je l'ai etabli dans mon
  arbitrage prealable (section 2). L'ecart n'a change aucune decision, le paquet livre en compte
  vingt de calcul.
- Les huit criteres pedagogiques de `camille-po` sont **tenus tous les huit** ; je les coche ici par
  mon verdict, pas dans leur bloc, qui lui appartient.

#### Les proprietes dont je reste la seule garde sur ce jeu

Pour que l'equipe ne croie pas couvert ce qui ne l'est pas : le garde-fou de doublon de proposition
livre ce sprint couvre desormais la propriete 1 de la section 6 de mon arbitrage. **Restent sans
automate** : l'exactitude arithmetique de chaque reponse, la plausibilite de chaque distracteur,
**le rang de la bonne reponse parmi les trois propositions** (reserve 1 - c'est la propriete neuve
que ce sprint fait apparaitre, et personne ne la mesure aujourd'hui), l'utilite de l'indice, et le
niveau tel qu'il se joue.

---

### Done pedagogique du 2026-08-16 - REFUSE (nouveau verdict, un item, un motif)

- **Verdict** : **REFUSE**. Le Done ouvert le 2026-08-14 est **rouvert**. `camille-po` ne peut plus
  compter cette ligne comme `couvert-valide` tant que ce motif tient.
- **Auteur** : nadia-prof. **Arbre juge** : `round-two` a `c0c51c5`, arbre propre, 486 tests, 28
  regles. Worktree dedie `s13-nadia-voie-rendu`.
- **Le verdict du 2026-08-14 n'est pas efface** : il reste lisible plus haut, avec ses trois
  reserves, qui restent ouvertes et inchangees. J'empile, je n'ecris pas par-dessus.
- **Mode** : **j'ai joue**. Jar reconstruit depuis mon worktree, navigateur pilote a 360 x 640,
  parties completes, **22 items sur 22 vus**. Releve :
  `releves-s13/releve-voies-de-rendu-360x640.json`. Capture :
  `releves-s13/voie-rendu-v0-perimetre-collision-360.png`.

#### Le motif, et il est unique

Sur l'item **`Le perimetre d'une figure, c'est...`**, a 360 px, **la bonne reponse n'est pas
lisible**. Les trois propositions debordent de leur colonne de 96 px :

| proposition | largeur du texte | debord par cote | ce que l'enfant voit |
|---|---|---|---|
| `le nombre de ses cotes` | 118,0 px | 11,0 px | `nombre` commence sur le fond sombre du cabinet |
| `la longueur de son contour` (**bonne reponse**) | 136,6 px | 20,3 px | `longueur` est percute par le `nombre` de la colonne voisine ; le `r` final de `contour` passe sous la boite de droite |
| `sa surface interieure` | 146,6 px | 25,3 px | `interieure` **sort de l'ecran** par la droite |

Sur la capture, `nombre` et `longueur` se recouvrent lettre sur lettre : l'enfant a sous les yeux un
mot qui n'existe pas. **La bonne reponse de cet item n'est pas presentee a l'enfant.**

C'est le motif exact sur lequel j'ai refuse `cm1-grands-nombres` les 2026-08-15 et 2026-08-16 :
**un enfant lit a l'ecran autre chose que ce qui est ecrit, et une erreur de fait dans un contenu
scolaire est un motif de refus a elle seule - que la cause soit un YAML ou un rendu ne change rien
pour lui.** Je ne peux pas tenir ce motif sur un jeu et l'oublier sur celui-ci parce que celui-ci
porte deja mon Done.

**Etendue exacte, et je ne la gonfle pas** : **un seul item sur 22**. Les 21 autres ne debordent
d'aucun pixel. Le contenu du paquet n'est pas en cause : l'arithmetique, les distracteurs, les
indices et le niveau restent ceux que j'ai valides le 2026-08-14.

#### Correction de mon propre constat, avant qu'on s'appuie dessus

Le 2026-08-14 j'ai joue ce jeu a 360 x 640 et je n'ai pas vu ce debordement. **J'avais mesure les
hauteurs et pas les largeurs** - le meme angle mort que j'ai deja confesse sur `cm1-grands-nombres`
le 2026-08-16, ou j'avais manque sept items debordants au premier audit. **C'est la deuxieme fois.**
Ce n'est donc pas une exigence neuve posee apres coup : c'est un fait qui existait le 2026-08-14 et
que mon audit a manque. Je l'ecris pour que la regle en sorte : **tant qu'aucun automate ne mesure
la largeur d'un texte rendu, un audit de rendu mesure la largeur autant que la hauteur.**

#### Comment ce refus se leve

**Sans toucher au YAML.** Il tombe le jour ou la voie de rendu tranchee dans
`ARBITRAGE-PREALABLE-s13-voie-de-rendu-des-propositions.md` (voie 4, la colonne recule devant son
contenu) est livree sous mes conditions VR-1 a VR-7. Je l'ai verifie en jouant cet etat : sur les
22 items, **zero debordement**, et sur cet item precis les trois propositions deviennent
integralement lisibles (`releves-s13/voie-rendu-voie4-perimetre-360x640.png`).

**Une seule story leve ce refus**, et c'est la meme que celle de `cm1-grands-nombres` et des huit
items des "Mesures" : la cause est unique et la corriger une fois les corrige tous.

#### Ce que je ne demande pas

**Je ne demande pas le retrait du jeu du catalogue.** Le perimetre d'un retrait appartient a
`camille-po`. Mon avis, non contraignant et qu'elle peut ecarter : 21 items sur 22 sont bons, le
defaut porte sur un item, et le correctif est deja arbitre - retirer le jeu couterait plus a l'enfant
de CM1 que de le laisser une iteration de plus. `DEC-0031` a retire un jeu qui n'avait jamais ete
valide ; ce n'est pas le meme cas.

#### Ce dont je reste la seule garde sur ce jeu

Inchange, plus une : **qu'un texte tienne dans sa boite a 360 px**. Ce refus en est la preuve - le
defaut a traverse une CI verte pendant deux sprints, et il etait deja la quand j'ai ouvert le Done.
