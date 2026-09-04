# Retro de s28 - second passage et chantier palier 2

## Ce qui a marche

### Deux agents ont trouve un defaut chez eux-memes, cinq fois en tout

`nadia-prof` a corrige son propre travail quatre fois dans ce seul sprint : elle a declare son
motif de s27 pour ne pas fermer de ligne "epuise" et remplace par une mesure de densite
(`1cf80f4`) ; elle a trouve, en jouant `S28-4`, que sa propre prescription du calcul pose
("dix items, partie epuisant exactement le vivier") etait incompatible avec un garde-fou existant
du depot et l'a retiree plutot que de la maintenir (`537a0e1`) ; elle a trouve que sa propre
prescription du paquet du vivant etait incomplete - elle avait autorise l'opposition binaire sans
verifier le libelle du pole non-vivant qu'elle connaissait - et l'a ecrit sans l'attenuer ("la cause
est chez moi") ; et au reaudit, elle a corrige un chiffre qu'elle avait elle-meme annonce (douze
mots et non onze sur `COR-R4`). `theo-archi` a repare, dans le meme commit que celui qui
l'implementait, un conflit entre `ADR-0080` et sa propre regle de la veille (`ADR-0006`) - trouve
avant que quiconque d'autre ne le decouvre en ecrivant le code.

C'est la meilleure chose du sprint, et elle merite d'etre nommee pour ce qu'elle est : deux agents
sur les proprietes qui leur appartiennent le plus (le jugement pedagogique pour l'une, la
coherence des decisions d'architecture pour l'autre) ont trouve leurs propres erreurs avant
qu'un tiers ne les leur signale. Rien de comparable ne s'est produit chez `camille-po`,
`marc-dev`, `lea-dev` ou `sofia-sm` ce sprint : ce n'est pas une critique, ce sont des roles dont
le materiau (arbitrages produits, code, decisions de gestion) laisse moins de prise a ce type
d'auto-verification que ne le fait un jugement pedagogique repete sur du contenu reel ou une
regle d'architecture appliquee a un cas nouveau.

### Deux defauts qui n'ont ete trouves que parce que quelqu'un a regarde apres coup

`yanis-qa` a trouve que `PaquetCpQlmCorpsSensTest` n'avait recu aucune extension alors que le
paquet qu'il teste passait de 8 a 14 items dans le meme commit que ses deux paquets freres, tous
deux etendus pour les memes conditions (`FER-1` a `FER-3`). Il a trouve qu'un test nomme pour
garder `BIA-2` ("aucun pays reduit a un seul trait") verifiait en realite une propriete plus
faible et toujours vraie sur le contenu existant (la distinction des reponses entre elles). Dans
les deux cas, la CI etait verte : aucun garde-fou execute n'a signale l'ecart, seule une lecture
humaine du diff contre son intention l'a fait.

**Ce qui aurait attrape ca plus tot, et ce qui ne l'aurait pas fait.** Le premier defaut avait un
signal disponible au moment meme du commit : deux paquets soeurs du meme diff recevaient une
extension de test, un troisieme ne la recevait pas, sur une meme famille de conditions
(`FER-1` a `FER-3`). Une regle de symetrie - "les paquets d'un meme lot qui declarent les memes
conditions recoivent une extension de test de taille comparable" - n'existe dans aucun outil de ce
depot ; elle demanderait une heuristique fragile (compter les lignes de test par paquet ne dit
rien sur ce qu'elles couvrent) et resterait elle-meme une clause a verifier contre sa population,
exactement le defaut que la retro de s27 a nomme. Le second est plus dur : un nom de test qui ment
sur ce qu'il garde ne se detecte par aucune mesure automatique, seulement par la lecture du corps
de l'assertion contre l'intention du nom - ce que `yanis-qa` a fait, et ce qu'aucune regle de
`CLAUDE.md` ne demande explicitement de faire a chaque commit plutot qu'a la revue de sprint.
**La question reste ouverte plutot que close par une fausse reponse** : aucun automatisme credible
ne remplace la lecture qu'a faite `yanis-qa`, et la reponse honnete est que ce sprint l'a trouve
au bon moment - avant la fermeture de la ligne, pas apres - mais un sprint sans `yanis-qa` a la
lecture aurait laisse passer les deux.

## Ce qui a coince

### Trois items factuellement faux ont ete servis en production, et c'est un jugement humain, pas un test, qui les a vus

Le paquet du vivant repondait "un objet" a "Du sable, qu'est-ce que c'est ?" et a "De la neige,
qu'est-ce que c'est ?" - une erreur de fait sur le libelle meme de la ligne qu'il pretend servir,
et le jeu felicitait l'enfant qui la choisissait. Aucun test ne l'a vu : le test qui garde la
classification du paquet (`chacuneDesTroisReponsesEstRepresenteeParmiLesItemsDeCategorisation`)
verifie que trois chaines de caracteres sont presentes dans les bonnes categories du fichier, pas
que ces chaines disent une chose vraie. `nadia-prof` l'ecrit elle-meme dans la matrice : "le test
garde des chaines de caracteres, pas un fait." C'est la troisieme fois que ce depot rencontre ce
type d'ecart (l'exactitude factuelle d'histoire-geographie et de questionner le monde est deja
listee depuis s27 parmi les conditions sans garde executable), mais c'est la premiere fois qu'il
produit un item concretement faux, en ligne, felicite par le produit, pendant plusieurs heures du
sprint. Aucun outil ne remplacera jamais ce jugement pour ce type de propriete ; ce que ce sprint
montre, c'est le cout reel - pas seulement theorique - de compter sur une seule paire d'yeux
humains pour l'attraper, un cout que la section suivante chiffre.

### Le sprint a depasse sa borne, et la marge de rejeu etait a zero par construction

La borne de `nadia-prof` (5,0 sur 5) est concue pour n'avoir aucune marge : c'est le principe
depuis `ADR-0079`. Cela veut dire qu'un refus survenant en fin de sprint n'a, par construction,
aucun espace pour se corriger dans le meme sprint - la seule issue prevue est de reporter la
correction au sprint suivant, ce que `nadia-prof` avait explicitement choisi de faire
("elle ne serait pas rejouee dans s28"). L'utilisatrice a arbitre le contraire pour ne pas laisser
trois items faux en production le temps d'un sprint entier, et ce choix est defendable - mais il a
ete pris dans l'urgence, en reaction a un refus deja rendu, pas prevu par la structure du sprint.
**Ce qu'il faut en tirer pour s29** n'est pas "reduire la borne pour se menager une marge" - une
borne avec de la marge cesse d'etre une borne et le sprint suivant gonflerait pour la remplir,
lecon deja tiree a plusieurs reprises dans ce depot. C'est plutot que la question "que se passe-t-il
si un lot est refuse pres de la fin du sprint" a une reponse par defaut (report au sprint suivant,
item faux laisse en ligne le temps du report) qui n'a jamais ete ecrite comme un choix assume avant
qu'elle ne se pose en vrai. Elle merite d'etre posee a `camille-po` et `nadia-prof` avant s29,
pas redecouverte au prochain refus.

### Une dette payee en avance, une autre non - la difference est le prix, pas la volonte

Le cliquet des garde-fous 4/5/6 de designation, que `theo-archi` avait lui-meme renvoye au lot qui
livrerait `T6` (a s29 au plus tot), a ete paye immediatement par `sofia-sm` des que `yanis-qa` a
signale son absence. La dette des paquets a voix (six jeux, cinq indices), elle, reste due depuis
s26. La difference n'est pas une question de volonte ou de priorite mal jugee : le cliquet de
designation coutait zero au moment ou il a ete ecrit - son plafond est a zero paquet servi
aujourd'hui, et l'ecrire ne demandait qu'un developpeur disponible dans le sprint. La dette des
paquets a voix coute une capacite que le sprint n'avait pas : c'est un audit de `nadia-prof`, au
tarif de 0,25, sur un sprint deja a sa borne de 5,0 avant meme le depassement du point precedent.
**Une dette qui ne coute rien se paie des qu'elle est vue ; une dette qui coute une capacite rare
attend que cette capacite existe.** Ce n'est pas un principe nouveau, mais ce sprint l'illustre
avec un ecart net entre deux dettes visibles au meme moment, traitees differemment pour un motif
ecrit et non par defaut.

### Le taux de tests a monte de 82, et ce chiffre ne dit qu'une chose

Le sprint est entre a 1140 tests et sort a 1222, soit +82 sur le reacteur complet. Ce chiffre dit
que le volume de code teste a suivi le volume de contenu et de mecanique livre (quatre lots de
contenu, une mecanique neuve, un cliquet de dette). **Il ne dit rien sur la qualite de ce que ces
tests gardent** : deux des quatre trouvailles de `yanis-qa` etaient precisement des tests presents
en nombre suffisant en apparence (le paquet du vivant et de l'anglais avaient chacun des suites de
test qui passaient) mais qui ne gardaient pas la propriete qu'ils pretendaient garder ou ne
gardaient rien de neuf sur le contenu ajoute. Un compte de tests qui monte est un signal de volume,
pas un signal de couverture pedagogique - la meme lecon que celle tiree en s27 sur les regles
"justes en intention, fausses en extension", appliquee ici aux tests eux-memes plutot qu'aux regles
d'architecture qu'ils verifient.

## Amendements a `squad/README.md`

Aucun amendement a `squad/README.md` n'est propose ce sprint. La regle de coordination posee a la
retro de s27 (aucun agent ne touche au worktree ni a la branche d'un autre) n'a pas ete mise en
defaut : `yanis-qa` a explicitement note dans son rapport avoir travaille dans un worktree dedie,
detruit apres mesure, et avoir signale plutot que corrige une modification apparue hors de son
perimetre pendant sa revue.

## Decisions prises (creees ou modifiees ce sprint)

| Reference | Titre | Type | Statut |
|---|---|---|---|
| `DEC-0074` | Quatre arbitrages de l'utilisatrice pour s28 (perimetre, palier 2, referentiels, execution) | Produit | Acceptee, amendee le meme jour (prix reel 5,0) |
| `DEC-0075` | Corpus de reproductions d'oeuvres et droits d'usage, question ouverte | Produit | Proposee |
| `DEC-0076` | Arbitrage de cloture : corriger et reauditer le paquet refuse dans le sprint plutot que trois items faux en production | Produit | Acceptee et executee |
| `ADR-0080` | Une designation sur image est un choix parmi des zones enumerees, le client n'envoie jamais une coordonnee | Architecture | Propose, deux amendements (exemption `ADR-0006`, cliquet des garde-fous de designation) |
| `ADR-0081` | Une ligne de referentiel a un code alloue, un item servi ne sert qu'une ligne | Architecture | Accepte |
