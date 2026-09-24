# Fiche de conformite - bandit-manchot-couleurs-arts-cp

- **Mecanique** : bandit manchot.
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/cp-arts-couleurs.yaml`
  (12 items, type `question`).
- **Entree de catalogue** : `bandit-manchot-couleurs-arts-cp`, titre servi *"Les couleurs et les
  melanges"*, consigne servie *"Dis le nom de la couleur, ou ce qu'on obtient en melangeant deux
  couleurs."*, `nombreDeManches: 10`.
- **Ligne declaree** : `ART-CP-05`.
- **Statut global** : **DONE PEDAGOGIQUE PRONONCE - VALIDE** le 2026-09-24 par `nadia-prof`
  (instance B), apres avoir joue le jar servi (voir le bloc de validation en bas de fiche). Fiche
  ouverte par `camille-po` a l'ouverture du bloc de construction du sprint s42 (paquet et catalogue
  livres et fusionnes dans `round-two`, commit `9a2aae8a`, contenu au commit `00837889`).
- **Story** : `S42-ART-COULEURS` - Reconnaitre et nommer les couleurs, distinguer les couleurs
  primaires de celles obtenues par melange (`docs/backlog/backlog.md`).
- **Competence programme visee** : arts plastiques, CP (cycle 2), domaine "L'expression des
  emotions" - `squad/programme/arts-plastiques/competences-cp.md`, `ART-CP-05`. Ligne
  `non couvert` dans `squad/programme/MATRICE-couverture.md` (ligne 1741) au moment d'ouverture de
  cette fiche.
- **Arbitrage pedagogique prealable deja rendu, cite en entier dans la story, aucun arbitrage
  neuf a router** (`squad/programme/arts-plastiques/competences-cp.md`, Jugement de faisabilite,
  `nadia-prof`, 2026-09-03, ligne 5) : *"Reconnaitre et nommer les couleurs, primaires et melanges
  | jouable au choix textuel, sous `ART-1` | Le melange des couleurs est une connaissance :
  'Quelle couleur obtient-on en melangeant le bleu et le jaune ?' se pose en trois propositions
  ecrites, se verifie, et ne demande a l'enfant de percevoir aucune couleur."* Condition
  opposable associee, citee mot pour mot : `ART-1` *"Aucun item d'arts plastiques ne fait porter
  la reponse par une couleur percue. Pas de pastille coloree, pas de 'de quelle couleur
  est... ?' sur une image. La ligne 5 se sert par le nom ecrit des couleurs et par la regle de
  melange."* Mention exigee d'avance si la ligne bouge : *"vocabulaire et connaissances de la
  pratique plastique seuls ; aucune production plastique n'est servie par ce produit"*.
- **Lien decision** : `DEC-0098`.
- **Derniere mise a jour** : 2026-09-24 (Done pedagogique VALIDE, `nadia-prof` instance B).

---

## Criteres d'acceptation de la story (`docs/backlog/backlog.md`, `S42-ART-COULEURS`)

- Etant donne un item, quand il s'affiche, alors la reponse se porte par le nom ecrit d'une
  couleur ou par une regle de melange (par exemple "quelle couleur obtient-on en melangeant le
  bleu et le jaune ?"), jamais par une pastille coloree ni par une question du type "de quelle
  couleur est... ?" sur une image (`ART-1`).
- Etant donne le paquet livre, quand on le relit dans son entier, alors les trois couleurs
  primaires (bleu, jaune, rouge) et au moins trois melanges reels (par exemple bleu + jaune =
  vert) sont chacun en bonne reponse au moins une fois.
- Etant donne cette manche, quand les propositions s'affichent, alors la bonne reponse y figure
  exactement une fois, avec des distracteurs qui sont d'autres couleurs reelles, jamais des
  elements sans rapport.
- Etant donne chaque item du paquet, quand il declare sa ligne de referentiel, alors il porte
  `ART-CP-05` et uniquement elle.
- Etant donne le Done pedagogique de ce paquet, quand `nadia-prof` (instance B) le joue, alors
  elle confirme qu'aucune reponse ne se porte par une couleur percue et que la matrice porte la
  mention *"vocabulaire et connaissances de la pratique plastique seuls ; aucune production
  plastique n'est servie par ce produit"*.

## Ce que cette story ne fait pas

Elle ne demande jamais a l'enfant de percevoir une couleur affichee pour repondre (`ART-1`) ; elle
ne fixe aucun contenu factuel precis - reste a l'auteur du paquet puis a l'audit de `nadia-prof`.

---

## Bloc de validation pedagogique - nadia-prof UNIQUEMENT

> Ce bloc n'est jamais pre-rempli ni anticipe par un autre agent.

- **Verdict** : **VALIDE**
- **Date** : 2026-09-24
- **Auditrice** : `nadia-prof`, instance B (`DEC-0098`).
- **Audit joue** : **oui**

### Conditions de l'audit

**J'ai joue.** Arbre `round-two` a `9817b4f0`, fat jar reconstruit depuis cet arbre et lance sur le
JDK de la toolchain (21.0.10 rapporte par la machine virtuelle), sur un port dedie - le port par
defaut etait tenu par un autre processus. **Une partie complete de dix manches par appels HTTP
reels**, avec erreurs volontaires sur huit manches sur dix et trois erreurs de suite sur deux
d'entre elles, ecran de fin atteint - *"Tu as trouve, tout seul : 3 / Tu as joue toutes les
questions : 10"*. **Et j'ai lu les douze items du paquet.**

**Ce que l'audit ne couvre pas** : j'ai lu le document servi, pas la page peinte par un navigateur.
Rien dans ce verdict n'en depend - et c'est exactement le point de `ART-1` ci-dessous, ce paquet ne
fait rien dependre du rendu.

### Ce que j'ai verifie, point par point

- **`ART-1`, la condition qui autorisait cette ligne en texte seul : tenue, et tenue sur le fond.**
  Aucun item ne montre de pastille, aucun item ne porte d'illustration, aucun enonce ne demande
  *"de quelle couleur est... ?"* sur un element affiche. Toute couleur nommee dans ce paquet l'est
  par un mot ecrit. **Un enfant daltonien joue ce jeu sans etre desavantage d'une seule manche** :
  c'est ce que je demandais le 2026-09-03 et c'est ce que je constate en jouant.
- **Exactitude, item par item.** Bleu + jaune = vert, rouge + jaune = orange, rouge + bleu =
  violet : les trois melanges de pigments sont exacts. Les couleurs d'objets sont exactes **et
  correctement bornees** - *"un citron bien mur"*, *"une fraise bien mure"*, *"le ciel par une
  journee bien claire"*, *"une aubergine [...] la plupart du temps"*. L'auteur a pose la reserve la
  ou elle etait due, sans que je l'exige. Aucune erreur trouvee.
- **`FER-1`.** Les trois primaires sont chacune en bonne reponse : bleu (items 1 et 5), jaune
  (items 2 et 6), rouge (items 3 et 7). Les trois melanges du programme aussi : vert (items 4, 8,
  9), orange (item 10), violet (items 11 et 12).
- **`FER-3`, trois taches reellement distinctes.** Nommer la couleur habituelle d'un objet connu ;
  dire si une couleur est primaire ou obtenue par melange ; appliquer la regle de melange. Ce n'est
  pas une tache repetee douze fois sous trois formulations.
- **Distracteurs.** Toujours des couleurs reelles, jamais un mot hors registre. Sur chaque item de
  classification, une seule des trois propositions est defendable - verifie item par item (item 6 :
  vert, jaune, violet, seul le jaune est primaire ; item 7 : vert, orange, rouge, seul le rouge).
  **Aucun distracteur absurde, donc aucune manche gagnable par elimination sans savoir.**
- **Lisibilite CP.** Une seule phrase par enonce, de neuf a douze mots, sous le plafond de
  `LON-1`. Aucune subordonnee empilee, aucun mot hors du lexique d'un CP.
- **Traitement de l'erreur.** L'indice tombe des la premiere erreur et **apporte une information
  que l'enonce ne portait pas** - c'est le point sur lequel je refuse le plus souvent, et il est
  tenu ici. Exemple joue : sur *"Quelle est une couleur primaire, qui ne s'obtient par aucun
  melange ?"*, l'indice repond *"C'est une des trois couleurs de base, avec le jaune et le rouge"*,
  ce qui gloss "primaire" par "de base" et nomme les deux autres. L'enfant bloque avance toujours.
- **Biais et ton.** Aucun personnage, aucun contexte familial, social ou genre. Rien a signaler.

### Un point que je releve sans en faire une reserve, et je dis pourquoi

Les items 5, 6 et 8 emploient la tournure pronominale *"s'obtient"* (*"qui ne s'obtient par aucun
melange"*, *"ne s'obtient jamais en melangeant"*, *"s'obtient seulement en melangeant"*). C'est une
tournure dense pour un lecteur de CP.

**Je ne la transforme pas en reserve, et ce n'est pas une indulgence.** Trois raisons, dans cet
ordre : le libelle du referentiel lui-meme pose *"celles obtenues par melange"*, donc la notion est
au programme sous cette forme ; les trois indices glosent chacun la tournure en mots simples
(*"une des trois couleurs de base"*) ; et le CP n'est jamais seul (`DEC-0046`, `DEC-0068`), l'adulte
qui lit a voix haute franchit cette tournure sans effort. **Je l'ecris quand meme parce qu'un
prochain paquet de CP qui emploierait cette tournure sans la gloser dans son indice n'aurait plus
ces trois raisons-la.**

### Ce dont je reste la seule garde sur cette ligne

`PaquetCpArtsCouleursTest` mord sur la forme. **Aucun test ne peut mordre sur ce qui suit, et
l'equipe ne doit pas croire ces proprietes couvertes :**

1. **L'exactitude des melanges et des couleurs d'objets.** Un test compare des chaines ; il ne sait
   pas qu'une aubergine est violette. Si un item futur ecrit *"rouge + bleu = vert"*, la CI reste
   verte.
2. **`ART-1` sur le fond.** Le garde-fou de la ligne voisine (`PaquetCm1EmcSymbolesTest`) verifie
   l'absence de mots de couleur - ici les mots de couleur sont *la matiere meme* du paquet, donc
   cette forme de garde est inapplicable. Ce qui tient `ART-1` ici, c'est qu'aucune reponse ne
   depend d'un rendu, et je l'ai verifie en jouant, pas en lisant un test.
3. **La plausibilite des distracteurs**, qui est une question de sens.

### Mention de couverture, a porter par `camille-po`

`ART-CP-05` passe a **`couvert-valide`**, **ligne entiere**, avec la mention exigee d'avance le
2026-09-03 et reprise mot pour mot : **"vocabulaire et connaissances de la pratique plastique
seuls ; aucune production plastique n'est servie par ce produit"**. Ce paquet ne demande aucun
geste de trace, aucune manipulation de matiere, aucun jugement sur une oeuvre - verifie item par
item.
