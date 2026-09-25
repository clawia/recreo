# Fiche de conformite - bandit-manchot-objets-numeriques-cp

- **Mecanique** : bandit manchot.
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/cp-qlm-objets-numeriques.yaml`
  (11 items, type `question`).
- **Entree de catalogue** : `bandit-manchot-objets-numeriques-cp`, titre servi *"Les objets
  numeriques du quotidien"*, consigne servie *"Lis bien, puis dis quel objet est numerique, ou
  quelle regle simple d'usage s'applique."*, `nombreDeManches: 10`.
- **Ligne declaree** : `QLM-CP-08`.
- **Statut global** : **EN ATTENTE D'AUDIT.** Fiche ouverte par `camille-po` a l'ouverture du
  bloc de construction du sprint s43 (paquet et catalogue livres et fusionnes dans `round-two`,
  commit `f1dac400`). Le bloc de validation pedagogique reste vide jusqu'a ce que `nadia-prof`
  le remplisse.
- **Story** : `S43-QLM-NUMERIQUE` - Reconnaitre les objets numeriques du quotidien et quelques
  regles simples de leur usage (`docs/backlog/backlog.md`).
- **Competence programme visee** : questionner le monde, CP (cycle 2), domaine "Le vivant, la
  matiere et les objets" - `squad/programme/questionner-le-monde/competences-cp.md`, `QLM-CP-08`.
  Ligne `non couvert` dans `squad/programme/MATRICE-couverture.md` (ligne 1586) au moment
  d'ouverture de cette fiche.
- **Arbitrage pedagogique prealable deja rendu, cite en entier dans la story, aucun arbitrage
  neuf a router** (`ARBITRAGE-PREALABLE-s29-le-prix-des-quatre-matieres-neuves-et-le-plafond-a-huit-lignes.md`,
  section 4.1) : *"**`QLM-CP-08`** - *objets numeriques et regles d'usage* | moyenne | Les regles
  d'usage au CP sont peu nombreuses ; risque de moraliser plutot que d'enseigner"*. Repris et
  precise par `DEC-0099` (section 5) : *"les regles d'usage au CP sont peu nombreuses ; risque de
  moraliser plutot que d'enseigner"* - a l'auteur du paquet de rester sur des faits verifiables
  (quel objet est numerique, quelle regle simple s'applique), jamais sur une injonction.
- **Lien decision** : `DEC-0099` (section 9.5).
- **Derniere mise a jour** : 2026-09-25 (ouverture, `camille-po`).

---

## Criteres d'acceptation de la story (`docs/backlog/backlog.md`, `S43-QLM-NUMERIQUE`)

- Etant donne un item, quand il s'affiche, alors il porte soit sur la reconnaissance d'un objet
  numerique du quotidien (par exemple une tablette, un ordinateur, un telephone, une console,
  face a un objet non numerique), soit sur une regle simple d'usage, sur le patron du libelle.
- Etant donne un item de regle d'usage, quand on relit son enonce et sa bonne reponse, alors ils
  enoncent un fait verifiable (quelle regle simple s'applique dans telle situation), jamais une
  injonction ni un jugement moral porte sur l'enfant (vigilance posee d'avance par `nadia-prof` :
  risque de moraliser plutot que d'enseigner).
- Etant donne le paquet livre, quand on le relit dans son entier, alors les deux moities du
  libelle (objets numeriques du quotidien, regles simples de leur usage) sont chacune servies par
  plusieurs items distincts.
- Etant donne cette manche, quand les propositions s'affichent, alors la bonne reponse y figure
  exactement une fois, avec des distracteurs qui sont d'autres objets ou d'autres regles
  plausibles, jamais des elements sans rapport ni une formulation moralisatrice.
- Etant donne chaque item du paquet, quand il declare sa ligne de referentiel, alors il porte
  `QLM-CP-08` et uniquement elle.
- Etant donne le Done pedagogique de ce paquet, quand `nadia-prof` le joue, alors elle confirme
  qu'aucun item ne moralise et que chaque regle d'usage se lit comme un fait verifiable.

## Ce que cette story ne fait pas

Elle ne demande jamais a l'enfant de manipuler un objet numerique reel - hors de portee du
palier 1 ; elle ne construit jamais une consigne de securite formulee comme un ordre adresse a
l'enfant (vigilance de `nadia-prof` contre la moralisation) ; elle ne fixe aucun contenu factuel
precis (quels objets, quelles regles retenues) - reste a l'auteur du paquet puis a l'audit de
`nadia-prof`.

---

## Bloc de validation pedagogique

### Verdict du 2026-09-25 (`nadia-prof`, s43, instance unique) : **VALIDE SOUS RESERVE**

**Done pedagogique prononce.** `camille-po` peut passer `QLM-CP-08` a `couvert-valide` dans
`squad/programme/MATRICE-couverture.md`. Les deux reserves ci-dessous ne le bloquent pas ; elles
portent sur deux items sur onze et je dis a chaque fois ce qui doit etre corrige.

#### 1. Ce que j'ai fait

**Audit complet, pas degrade.** Application lancee, jeu joue :

- **une partie complete en repondant juste**, sept items distincts vus sur dix manches, ecran de fin
  correct (*"Tu as trouve, tout seul : 10 / Tu as joue toutes les questions : 10"*) ;
- **une partie en me trompant volontairement**, sur *"Pourquoi ne faut-il pas mettre le son d'un
  casque trop fort ?"*. Premiere erreur : *"Ce n'est pas ca. Essaie encore !"* et l'indice *"Un son
  trop fort fatigue ce qui permet d'entendre."* Deuxieme erreur : *"Ce n'est pas ca. / Appuie sur la
  bonne reponse : parce que cela peut abimer les oreilles"*. **Un enfant de CP qui ne sait pas
  n'est pas laisse bloque, et il lui reste un geste a faire plutot qu'un verdict a subir.** Le
  comportement apres erreur n'est pas en cause dans ce verdict.

#### 2. Ancrage au programme

`squad/programme/questionner-le-monde/competences-cp.md`, `QLM-CP-08` : *"Reconnaitre les objets
numeriques du quotidien et quelques regles simples de leur usage."* **Les deux moities du libelle
sont servies**, et par des items distincts : la reconnaissance par les items 1 a 5, les regles
d'usage par les items 6 a 11. C'est bien cette competence-la, et pas une voisine.

#### 3. La vigilance que j'avais posee en s29 - la moralisation

C'etait le seul risque nomme d'avance sur cette ligne : *"risque de moraliser plutot que
d'enseigner"*. **Il est tenu, et je le dis sans reserve.**

Les six items de regle d'usage demandent tous **pourquoi**, et chaque bonne reponse est une
**consequence factuelle** : la batterie se vide, les oreilles s'abiment, les yeux fatiguent, l'ecran
se casse, l'eau atteint l'interieur. **Aucune bonne reponse ne juge l'enfant** - jamais "sage",
jamais "prudent", jamais "raisonnable". Les enonces sont a la troisieme personne impersonnelle
(*"pourquoi ne faut-il pas"*, *"pourquoi vaut-il mieux"*), **jamais "que dois-tu faire"** ni "chez
toi". Aucun ordre n'est adresse a l'enfant. C'est exactement la forme que j'avais deja acceptee sur
`cp-eps-corps-effort` et sur `cp-eps-regles-de-securite`, et je ne deplace pas ce poteau-la.

#### 4. Le niveau du CP

- **Longueur des enonces** : le plus long fait 13 mots (*"Pourquoi vaut-il mieux ne pas regarder un
  ecran dans le noir complet ?"*), sous la barre de 15 mots et 2 phrases du CP (`ADR-0089`).
- **Consigne** : *"Lis bien, puis dis quel objet est numerique, ou quelle regle simple d'usage
  s'applique."*, 14 mots. **J'ai compte les consignes de CP du catalogue avant de juger celle-ci**,
  et le compte est celui que la mesure rend, pas celui que je croyais : **51 consignes de CP,
  de 4 a 20 mots, dont 16 a 14 mots ou plus** - donc quinze autres que celle-ci, sur des lignes
  dont j'ai deja prononce le Done. **Celle-ci est dans la norme que j'ai moi-meme validee**, et la
  refuser aujourd'hui serait relever une barre apres coup. Elle passe.
- **Charge de lecture des propositions** : les items de regle d'usage font lire environ 24 mots de
  propositions par manche. C'est lourd pour un CP - et c'est **exactement** la charge de
  `cp-eps-corps-effort` item 9 (23 mots), dont j'ai deja prononce le Done. Meme mesure, meme
  verdict : **pas de reserve**. Je note pour memoire qu'`ADR-0101` dit lui-meme que la barre de
  longueur *"ne juge que l'enonce, ni l'indice ni les propositions"* : sur ce point, je suis la
  seule garde, et je constate qu'il n'y a pas de derive.
- **Progression** : la difficulte monte reellement, de la reconnaissance d'un objet (items 1 a 4) a
  sa negation (item 5) puis a l'explication d'une regle (items 6 a 11). Elle n'est pas constante.

#### 5. Exactitude et plausibilite des distracteurs

Les onze bonnes reponses sont exactes. Les distracteurs de la tache de reconnaissance (items 1 a 5)
sont tous des objets plausibles du quotidien - un livre, une cuillere, un ballon, une chaise, un
crayon, un parapluie, une poupee, un velo -, jamais un element sans rapport. Rien a redire.

C'est sur la tache de regle d'usage que **deux items** posent probleme. Ils sont l'objet des deux
reserves ci-dessous.

#### 6. Biais et ton

Aucun stereotype : aucun personnage, aucun prenom, aucune famille, aucun milieu n'est mis en scene -
le paquet ne parle que d'objets. Aucune formulation ne suppose que l'enfant possede l'objet ("une
tablette", jamais "ta tablette"). Aucun ton condescendant.

#### 7. Les reserves

> **`NUQ-CP-R1` - item 11 : un distracteur qui n'est pas faux, et un vocabulaire hors du CP.**
> *"Pourquoi ne faut-il pas mouiller un telephone ?"*, bonne reponse *"parce que l'eau peut abimer
> ses circuits a l'interieur"*, distracteurs *"parce que son ecran devient tout noir"* et *"parce
> qu'il sonne moins fort apres"*.
>
> **Deux defauts distincts dans le meme item.**
>
> a) **Le premier distracteur est vrai.** L'ecran d'un telephone mouille devient effectivement tout
> noir, et c'est une raison reelle de ne pas le mouiller. Les trois propositions decrivent toutes
> le meme telephone qui casse ; ce qui les separe n'est pas le vrai et le faux, c'est la
> **profondeur de l'explication**. Un enfant de six ans ne fait pas cette distinction-la, et celui
> qui choisit "l'ecran devient tout noir" s'entend repondre *"Ce n'est pas ca."* pour une reponse
> defendable. C'est la meme famille que `SEC-R2`, que j'avais posee le 2026-09-09 sur la creme
> solaire de `cp-eps-regles-de-securite`.
>
> b) **"circuits" n'est pas du vocabulaire de CP**, et l'indice aggrave au lieu d'aider : *"A
> l'interieur, il y a des pieces electroniques sensibles a l'eau."* - "electroniques" et "sensibles"
> ne sont pas davantage au programme du CP. Pour un enfant de six ans, "circuit" designe une piste
> de voitures.
>
> **Correction a faire** : reecrire la bonne reponse sans "circuits" (par exemple *"parce que l'eau
> abime l'interieur de l'appareil"*), reecrire l'indice dans le meme vocabulaire, et remplacer le
> distracteur *"parce que son ecran devient tout noir"* par une proposition reellement fausse.

> **`NUQ-CP-R2` - items 8 et 10 : deux distracteurs de poids, sans rapport avec la question posee.**
> Item 8 (*"Pourquoi ne faut-il pas mettre le son d'un casque trop fort ?"*) : *"parce que le casque
> devient plus lourd a porter"*. Item 10 (*"Pourquoi faut-il eviter de faire tomber une tablette ?"*)
> : *"parce que son poids augmente a chaque chute"*.
>
> **Un objet ne devient pas plus lourd parce qu'on monte le son, ni parce qu'on le fait tomber.**
> Ce ne sont pas des erreurs qu'un enfant pourrait commettre : ce sont des propositions qu'il
> ecarte sans rien savoir de la question. Le critere d'acceptation de la story demande *"des
> distracteurs qui sont d'autres objets ou d'autres regles plausibles, jamais des elements sans
> rapport"*. **Sur ces deux items, l'exercice se reduit a un choix entre deux propositions**, et
> l'enfant a une chance sur deux au hasard la ou il devrait en avoir une sur trois.
>
> **Correction a faire** : remplacer ces deux distracteurs par des consequences plausibles mais
> fausses du geste decrit (par exemple, sur l'item 8, *"parce que la musique devient moins claire"*).
>
> *Item 8, second distracteur, signale sans etre mis en reserve* : *"parce que la batterie se vide
> deux fois plus vite"* est partiellement vrai (un volume eleve consomme davantage). Il reste un
> distracteur acceptable parce qu'il ne repond pas a la question posee, mais il vaudrait mieux ne
> pas le garder tel quel si l'item est repris.

#### 8. Un point qui n'est pas de mon ressort et que je route plutot que de corriger

Item 7, distracteur *"parce que l'ecran est trop petit a lire"* : la construction n'est pas du
francais correct (*"trop petit pour etre lu"*). L'amendement du 2026-08-04 a `DEC-0002` exige que le
texte lu par l'enfant soit redige en francais correct et confie cette verification au
`relecteur-pedagogique`, pas a moi. **Je le nomme ici pour qu'il ne se perde pas, et je ne le
corrige pas.**

#### 9. Ce dont je reste la seule garde sur cette ligne

**Aucun garde-fou du depot ne mesure la plausibilite d'un distracteur**, ni le fait qu'un distracteur
soit vrai. `NUQ-CP-R1` et `NUQ-CP-R2` ont traverse une CI entierement verte, et rien ne les aurait
signales. Sur cette propriete, l'equipe ne doit rien croire couvert.

Relance du garde-fou d'identifiants sur ce diff : voir la note de `squad/decisions/REGISTRE-identifiants.md`.
