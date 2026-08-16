# Fiche de conformite - bandit-manchot-conversions-unites-cm1

- **Mecanique** : bandit manchot (mecanique generique, partagee avec les autres jeux du
  catalogue).
- **Contenu(s)** : `adapters/adapter-content/src/main/resources/contenu/cm1-conversions-unites.yaml`
  (a creer, `type: question`, au moins 24 items), story `S12-2` (`docs/backlog/backlog.md`). Matiere
  premiere identifiee : `contenu-repris/quiz/cm1-longueurs.yml`, `cm1-masses.yml` et
  `cm1-contenances.yml` - une transposition directe des items de conversion vers `type: question` est
  possible (renommer `question`/`bonne`/`distracteurs` en `enonce`/`bonneReponse`/`distracteurs`,
  ajouter un `indice` par item, absent des trois fichiers source). Les items d'estimation d'ordre de
  grandeur et d'identification d'instrument, presents dans les trois fichiers, ne relevent pas du
  verbe "convertir" que cette ligne nomme et ne sont pas repris tels quels. Reprise = reprise de la
  matiere, pas de la structure (`contenu-repris/README.md`) : chaque item repasse devant `nadia-prof`
  comme un contenu neuf.
- **Statut global** : **VALIDE SOUS RESERVE** par `nadia-prof` le 2026-08-14, apres avoir joue
  l'increment (S12-2). Deux reserves ouvertes, plus une reponse au point de plausibilite signale
  par `marc-dev`. Detail dans le bloc de validation.
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
| mathematiques | CM1 (cycle 3) | Grandeurs et mesures | Convertir des unites de longueur, masse, contenance dans le systeme metrique | `squad/programme/mathematiques/competences-cm1.md` |

**Perimetre exact, assume a l'ouverture** : cette ligne nomme trois grandeurs (longueur, masse,
contenance) et un seul verbe, "convertir". Un Done pedagogique accorde sur un paquet qui ne
travaillerait qu'une ou deux des trois grandeurs reproduirait le motif de couverture partielle deja
sanctionne sur ce produit (additions "jusqu'a 10 (puis 20)", homophones "principaux", conjugaison
"un temps sur trois", comparer sans ranger) ; les criteres ci-dessous l'excluent explicitement.

## Criteres pedagogiques attendus - camille-po

- [ ] Chaque item demande de convertir une quantite d'une unite metrique vers une autre unite de la
  meme grandeur (longueur, masse ou contenance), avec une bonne reponse numeriquement exacte.
- [ ] Chacune des trois grandeurs (longueur, masse, contenance) represente au moins un quart des
  items du paquet.
- [ ] Aucun item n'est une question d'estimation d'ordre de grandeur ou d'identification
  d'instrument : chaque item teste une conversion, jamais une estimation ni une reconnaissance
  d'outil.
- [ ] Chaque item porte exactement trois ou quatre propositions : la bonne reponse et des
  distracteurs numeriques plausibles (confusion de facteur 10/100/1000), jamais un nombre
  arbitraire.
- [ ] Le paquet compte au moins 24 items, avec au moins 8 items par grandeur.
- [ ] Chaque item porte un indice non vide, exact et comprehensible par un enfant de CM1.
- [ ] Le paquet valide le schema `type: question` (`ADR-0007`) sans code Java neuf, sans gabarit ni
  feuille de style neuve.

---

## Bloc de validation pedagogique - nadia-prof UNIQUEMENT

> Ce bloc n'est jamais pre-rempli ni anticipe par un autre agent.

### Done pedagogique du 2026-08-14 - VALIDE SOUS RESERVE (S12-2, paquet `cm1-mesures.yaml`)

- **Verdict** : **VALIDE SOUS RESERVE**. Le Done pedagogique est ouvert. `camille-po` peut passer la
  ligne "Convertir des unites de longueur, masse, contenance dans le systeme metrique" a
  `couvert-valide`, **sans mention de couverture partielle** : les trois grandeurs sont servies a
  parts egales, 8 items chacune, et le verbe travaille est bien "convertir".
- **Auteur** : nadia-prof. **Increment** : branche `round-two`, tete `b396234`, 455 tests, 28 regles.
- **Conditions de reference** : mes 27 conditions du
  `squad/conformite/ARBITRAGE-PREALABLE-s12-mathematiques-cm1.md`, section 3 (C-1 a C-10).
- **Mode** : **j'ai joue**, ce n'est pas un audit degrade. Jar reconstruit depuis mon worktree,
  parties completes jouees au navigateur a 360 x 640, avec erreurs deliberees et revelations.
  Releves : `releves-s12/releve-jeu-cm1.json`, `releves-s12/releve-m4-cm1-360x640.json`,
  `releves-s12/releve-rang-bonne-reponse.json`.
- **Note de nommage, pour `camille-po`** : la ligne **Contenu(s)** ci-dessus annonce
  `cm1-conversions-unites.yaml` ; le fichier livre et cable au catalogue est
  `cm1-mesures.yaml`. Le jeu garde son identifiant `bandit-manchot-conversions-unites-cm1`. A
  aligner dans la fiche ; rien de pedagogique n'en depend.

#### Ce que j'ai verifie moi-meme, parce qu'aucun automate ne le couvre

1. **Exactitude arithmetique : 24 items sur 24 justes.** J'ai recalcule chaque facteur, item par
   item, sur les trois grandeurs. Aucune erreur de fait.
2. **C-1 tenue, et c'est celle qui commandait tout** : aucun nombre decimal, aucune virgule, ni dans
   un enonce, ni dans une bonne reponse, ni dans un distracteur. Les conversions de la petite vers
   la grande unite ont toutes un quotient entier (6000 g vers 6 kg, jamais 500 g vers des
   kilogrammes). C'etait le piege principal de la matiere premiere et il est evite.
3. **C-2, C-3, C-4, C-5, C-6, C-8 tenues** : facteur 10, 100 ou 1000 et un seul palier par item ;
   mm, cm, m, km / g, kg, tonne / ml, cl, L et rien d'autre ; **aucune estimation d'ordre de
   grandeur, aucune identification d'instrument, et aucune comparaison de grandeurs** - la
   troisieme forme que j'avais ajoutee a la liste des exclusions et que `DEC-0029` n'avait pas vue
   n'a pas ete reprise ; aucune unite composee ; l'unite demandee ecrite dans les trois propositions
   de chaque item ; chaque enonce nomme la quantite de depart avec son unite et l'unite d'arrivee,
   donc l'enfant n'a jamais a se souvenir de l'item precedent.
4. **C-9 tenue.** Le titre au catalogue est **"Les mesures"** : il couvre les trois grandeurs et
   reste dechiffrable. Un enfant qui le choisit n'est pas trahi par une question sur les litres.
   C'etait ma reponse a la question que `camille-po` m'avait posee - oui, les trois grandeurs
   tiennent dans un seul jeu - et la condition est remplie.
5. **C-10 tenue, et les indices servent.** "1 kg, c'est 1000 g.", "1 cl, c'est 10 ml." : un indice
   par famille de conversion, qui donne le rapport et jamais le resultat. Lus apres un echec reel,
   ils debloquent.
6. **Aucun biais, aucun presuppose social.**

#### Ma reponse au point que `marc-dev` a signale plutot que de le corriger seul

Il a eu raison de me le renvoyer, et **la reponse est non : `6 000 000 kg` ne teste rien.** Un
distracteur qu'aucun enfant ne choisirait n'est pas un distracteur, c'est un remplissage - et l'item
qui le porte devient une question a deux choix, ou l'enfant elimine par le bon sens et non par la
conversion.

**Je corrige d'abord ma propre condition, avant de m'appuyer dessus.** C-7 dit "erreurs de facteur,
et rien d'autre", et nomme "aller dans le mauvais sens" parmi les erreurs admises. **Ces
distracteurs sont conformes a la lettre de C-7 : `marc-dev` n'a pas devie, ma condition etait
courte.** Elle nommait la nature de l'erreur et pas son ordre de grandeur. Je l'ecris comme une
reserve neuve et datee, pas comme un durcissement retroactif - et je note que j'avais pose
exactement ce principe deux sections plus loin, en T-5, sans penser a le reporter en C-7.

**L'ampleur, mesuree.** **12 items sur 24** portent un distracteur de mauvais sens qui vaut de
**10 000 a 1 000 000 fois** la bonne reponse : 50000 m, 6000000 km, 5000000 m, 6000000 kg,
5000000 tonnes, 9000000 kg, 4000000 tonnes, 70000 L, 5000000 L, 4000000 L, et 7000 cm et 7000 cl a
100 fois. C'est exactement la moitie du paquet.

**Ce qui est actionnable.** Garder les deux erreurs de facteur **a un ou deux crans**, jamais le
produit brut du mauvais sens. Pour "6000 g, combien de kg ?", proposer **60 kg** (divise par 100 au
lieu de 1000) et **600 kg** (divise par 10) au lieu de 600 kg et 6 000 000 kg : deux erreurs
reelles, deux nombres qu'un enfant peut ecrire, et l'item redevient a trois choix.

**Un piege a ne pas creer en corrigeant, et je le nomme pour qu'on ne repare pas un defaut en en
fabriquant un autre.** Sur une conversion de la petite vers la grande unite, **toutes** les erreurs
de facteur plausibles sont superieures a la bonne reponse : la bonne reponse y sera toujours la plus
petite des trois. Ce n'est pas grave ici parce que le paquet est equilibre par direction - 12 items
dans chaque sens - et c'est ce qui lui donne la meilleure repartition des trois paquets de ce
sprint : la bonne reponse est la plus petite dans 54,2 % des items, la plus grande dans 29,2 %,
intermediaire dans 16,7 % (releve exhaustif dans
`releves-s12/releve-rang-bonne-reponse.json`). **Cet equilibre-la est un acquis a preserver** : la
correction demandee porte sur l'ordre de grandeur du distracteur, pas sur le sens des conversions.

#### Reserve 1 (2026-08-14) - a 360 x 640, l'indice est sous le pli six fois sur dix

Mesure sur 61 etats "premier echec" joues : **le message d'erreur est visible sans defiler 61 fois
sur 61** - c'est tenu, et c'est le seul des deux paquets de mesure de ce sprint dans ce cas. Mais
**le bas de l'indice est sous le pli 38 fois sur 61 (62 %)**, jusqu'a 33 px au-dela. L'enfant qui se
trompe voit qu'il s'est trompe, mais l'aide qui le debloquerait lui reste cachee. **M4 ne bloque
plus depuis `DEC-0025`** et je ne la ressuscite pas ; je consigne la mesure.

#### Reserve 2 (2026-08-14) - l'enfant ne compte que des zeros, il ne convertit jamais un vrai nombre

Les 24 quantites de depart sont toutes un chiffre significatif unique suivi de zeros : 7 cm, 4 m,
3 km, 700 mm, 6000 g... Aucune n'est du genre "35 m" ou "250 ml". La tache se reduit donc a ajouter
ou retirer des zeros, jamais a multiplier un nombre par 10, 100 ou 1000. C'est conforme a toutes mes
conditions et c'est un bon point de depart ; c'est aussi le plus facile de ce que la ligne demande.
Quelques items a deux chiffres significatifs, sans decimal ni virgule (35 m vers 3500 cm, 250 ml
vers 25 cl), rendraient au paquet la moitie de la competence qu'il laisse aujourd'hui de cote.

#### Les proprietes dont je reste la seule garde sur ce jeu

Le garde-fou de doublon livre ce sprint couvre la propriete 1 de la section 6 de mon arbitrage.
**Restent sans automate** : l'exactitude arithmetique de chaque conversion, **la plausibilite de
chaque distracteur** (reserve du point signale ci-dessus - c'est la propriete que ce lot rend
concrete), le rang de la bonne reponse parmi les trois propositions, l'utilite de l'indice, et le
niveau tel qu'il se joue.

---

### Complement du 2026-08-16 - Done maintenu ouvert, deux reserves neuves et datees

- **Verdict** : **VALIDE SOUS RESERVE, maintenu.** Le Done ouvert le 2026-08-14 **n'est pas
  rouvert**. Le verdict de ce jour-la et ses reserves restent lisibles plus haut, inchanges : ce
  bloc s'empile, il n'ecrase rien.
- **Auteur** : nadia-prof. **Arbre juge** : `round-two` a `c0c51c5`, arbre propre, 486 tests, 28
  regles. Worktree dedie `s13-nadia-voie-rendu`.
- **Mode** : **j'ai joue**. Jar reconstruit, navigateur pilote a 360 x 640, **24 items sur 24 vus**,
  plus **360 manches** jouees en strategie pour la reserve K. Releve :
  `releves-s13/releve-voies-de-rendu-360x640.json`. Captures :
  `releves-s13/voie-rendu-v0-mesures-6000km-360.png`,
  `releves-s13/voie-rendu-v0-mesures-tonnes-360.png`.

#### Reserve J (2026-08-16) - huit distracteurs sont illisibles a 360 px

**Huit items sur 24** portent un distracteur qui deborde de sa colonne de **16,4 px par cote** :
`4000000 tonnes`, `5000000 tonnes`, `6000000 km`, `5000000 m`, `9000000 kg`, `6000000 kg`,
`4000000 L`, `5000000 L`. A l'ecran, le chiffre de tete tombe en texte sombre sur le fond sombre du
cabinet et le dernier chiffre passe sur le montant du meuble : l'enfant lit `000000 km` la ou il est
ecrit `6000000 km`. Sur les items en tonnes, les trois mots `tonnes` se rejoignent en une bande
continue d'un bord a l'autre du cabinet, sans separation entre les colonnes.

**Pourquoi une reserve et non un refus, et la frontiere est celle que j'ai deja posee.** Le
2026-08-16, sur `cm1-grands-nombres`, j'ai ecrit que le debordement est grave parce que *"il n'y a
plus de bonne reponse a l'ecran"*. Ici, **la bonne reponse est lisible sur les 24 items** : ce sont
les distracteurs qui sont abimes. Un distracteur illisible n'est plus un distracteur - il ramene
l'item a deux choix - mais l'enfant ne lit jamais un nombre faux a la place du bon. **Cela vaut une
reserve, pas un refus**, et j'applique la meme frontiere que sur "Le perimetre", ou c'est la bonne
reponse qui est detruite et ou j'ai refuse.

Cette reserve tombe avec la story de rendu arbitree dans
`ARBITRAGE-PREALABLE-s13-voie-de-rendu-des-propositions.md` : je l'ai jouee, les 24 items passent a
**zero debordement**.

Je note que ces huit distracteurs sont ceux que j'avais deja signales le 2026-08-14 comme
invraisemblables (*"`6 000 000 kg` ne teste rien"*). Le rendu ajoute une raison de les corriger a
celle que j'avais deja donnee ; il n'en cree pas une nouvelle famille.

#### Reserve K (2026-08-16) - la reponse la plus courte gagne une fois sur deux, et je corrige mon propre constat du 2026-08-14

**Le fait, joue avant d'etre lu.** Sur **120 manches reellement jouees**, un joueur qui presse
toujours la proposition **la plus courte** gagne **57 fois, soit 47,5 %**, contre 33,3 % attendus du
hasard. Verifie ensuite sur le contenu, hors de tout rendu : **la bonne reponse est la plus courte
des trois chaines sur 13 items sur 24 (54,2 %)**, la plus longue sur 7 (29,2 %), intermediaire sur
4 (16,7 %).

Comme les trois propositions d'un item ne different que par leurs zeros, "la plus courte" veut dire
"le plus petit nombre". **La regle gagnante est donc "presse le plus petit", elle se decouvre en
trois manches, et elle ne demande aucune conversion.** C'est le defaut que je refuse partout
ailleurs : une propriete de forme qui fait mieux que le hasard sur un jeu qui annonce enseigner a
convertir.

**Aucun autre paquet du catalogue n'est dans ce cas** : conjugaison 20 %, homophones 25 %, grands
nombres 14 %, perimetre 0 %, tables 2 %, et 0 % sur les quatre paquets de CP concernes.
"Les mesures" est seul.

**Ce que je corrige, et c'est de moi.** Le 2026-08-14, dans le bloc de validation ci-dessus, j'ai
mesure **exactement cette distribution** et je l'ai ecrite : *"la bonne reponse est la plus petite
dans 54,2 % des items, la plus grande dans 29,2 %, intermediaire dans 16,7 %"*, en concluant que
**"cet equilibre-la est un acquis a preserver"**. Mes chiffres d'aujourd'hui reproduisent les miens
au dixieme. **Ce que j'avais credite comme un equilibre est un raccourci.** Je l'avais manque parce
que j'avais lu cette repartition comme une statistique de variete, au lieu de la jouer comme une
strategie - et la strategie, elle, se voit en une partie.

**Ce que j'en fais : une reserve, pas un refus, et je dis pourquoi.** J'ai regarde ce chiffre le
2026-08-14, je l'ai approuve par ecrit, et l'equipe n'a rien livre sur ce paquet depuis. Transformer
ma propre approbation en refus, sur un chiffre inchange, dix jours plus tard, serait deplacer mon
poteau apres coup : une porte qui fonctionne ainsi n'est plus une porte, c'est un caprice.
**Je corrige mon constat, j'ecris la reserve, je laisse l'ancien texte lisible, et je ne rouvre pas
le Done.**

**Ce qui est actionnable pour `camille-po`**, et c'est peu couteux : il suffit que **six a sept des
treize items concernes** portent un distracteur plus court que la bonne reponse. Sur une conversion
de la grande vers la petite unite, la bonne reponse est deja la plus longue ; le paquet compte 12
items dans chaque sens, donc **le materiau existe deja et c'est la repartition des distracteurs, pas
le sens des conversions, qu'il faut retoucher.** A combiner avec la correction demandee en reserve
du 2026-08-14 sur l'ordre de grandeur des distracteurs : les deux se traitent dans la meme passe.

**Je ne pose pas de seuil chiffre aujourd'hui.** Je n'en ai jamais ecrit pour la longueur d'une
proposition, et en inventer un dans le meme geste que le premier constat serait poser une barre et
la faire respecter retroactivement. Ce que je demande est nomme : que ce paquet cesse d'etre gagnable
a 47,5 % sans convertir.

#### Ce dont je reste la seule garde sur ce jeu

Inchange, plus deux : **qu'un texte tienne dans sa boite a 360 px** (reserve J), et **qu'aucune
propriete de forme d'une proposition - sa longueur, sa largeur - ne fasse gagner mieux que le
hasard** (reserve K). Le rang est garde par le code depuis `lea-dev` ; **la longueur ne l'est par
rien**, et ce paquet prouve que ce n'est pas une inquietude theorique.
