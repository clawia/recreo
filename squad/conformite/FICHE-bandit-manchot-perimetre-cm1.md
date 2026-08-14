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
- **Derniere mise a jour** : 2026-08-14 (verdict de `nadia-prof`)

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
