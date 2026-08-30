# ADR-0067 - Le moyen de `TYP-1` vit dans la donnee servie, et la dette est arretee par un cliquet exact avant d'etre corrigee

- **Statut** : Accepte pour la partie qui m'appartient seul (le cliquet). **Le choix du caractere
  reste ouvert**, il est confie a `theo-archi` et `sofia-sm` conjointement.
- **Date** : 2026-08-30
- **Sprint** : s23
- **Auteur** : theo-archi

## Contexte

`nadia-prof` a ecrit `TYP-1` le 2026-08-28 comme une **propriete de rendu** : *"un signe de
ponctuation double (`?`, `!`, `;`, `:`) ne doit jamais pouvoir se retrouver seul en debut ou en fin
de ligne, separe du mot qu'il accompagne"*. Elle ecrit explicitement qu'elle laisse le **moyen** a
`theo-archi` et `sofia-sm`, et qu'elle ne demande aucun garde-fou - tout en notant que `TYP-1` est
*"de toutes mes conditions, la seule qui soit exactement verifiable sans heuristique"*.

Etat a la cloture de s22 : **18 signes orphelins mesures au navigateur reel, dont 4 neufs apparus
pendant le sprint**. `catalogue.yaml` ne porte aucune espace insecable et aucun garde-fou n'en exige.

## Decision

### 1. Le moyen vit dans la donnee servie, jamais dans le rendu

Le caractere qui empeche la coupure est ecrit **dans le paquet et dans le catalogue**, comme
n'importe quel autre caractere du texte lu par un enfant. Il n'est pose ni par un gabarit, ni par un
filtre, ni par une regle de style.

**Motif, et il est deja juge dans ce depot** : `ADR-0060` a decide que *"un texte servi porte le
caractere decide, jamais sa notation"*, et sa clause 2 exige deja l'espace insecable a l'interieur
des guillemets francais - dans la donnee. Le meme caractere pour le meme motif ne peut pas vivre a
deux endroits differents selon le signe qu'il precede. Poser l'insecable au rendu ferait par ailleurs
mentir tous les garde-fous de contenu, qui jugent ce que le chargeur rend.

### 2. La dette est arretee avant d'etre corrigee

**540 occurrences sur 17 fichiers** ne se corrigent pas dans le sprint qui les mesure, sur des
paquets deja juges, par un architecte qui n'ecrit pas le contenu. Ce qui est faisable aujourd'hui, et
qui coute zero geste a qui que ce soit, est d'**arreter la croissance** : un cliquet exact,
fichier par fichier.

**Methode du chiffre, dans le meme paragraphe.** Perimetre : le texte servi par le chargeur reel
(enonce, bonne reponse, distracteurs, indice, explication, texte support, part designee), chaque
paquet enumere lu en entier (`ADR-0064`), plus les seuls `titre` et `consigne` de `catalogue.yaml`.
Critere : une espace ordinaire (U+0020) immediatement suivie de `?`, `!`, `;` ou `:`. Resultat :
**517 dans les paquets sur 16 fichiers, 23 dans le catalogue, 540 au total**, arbre du 2026-08-30.

**Ce chiffre n'est pas celui de `nadia-prof`, et l'ecart n'est pas une erreur.** Elle a releve
**511 occurrences sur 17 fichiers** le 2026-08-28 en lisant les fichiers ; je lis le texte servi.
Commentaires et cles YAML entrent dans son compte et pas dans le mien, `texteSupport` et
`partDesignee` entrent dans le mien. Les deux sont exacts, ils ne comptent pas la meme chose, et
aucun des deux ne se lit sans sa methode.

### 3. Ce que le cliquet garde, et ce qu'il ne garde pas

Il ne juge **pas** `TYP-1`, qui porte sur une ligne rendue a un viewport donne. Il juge sa
**condition suffisante dans la donnee** : une espace ordinaire devant un signe double est le seul
endroit ou le moteur de rendu a le droit de couper. Zero espace ordinaire a cet endroit implique zero
orphelin, **a tout viewport, sans ouvrir un navigateur** - ce qui repond au passage a la limite que
`nadia-prof` nomme elle-meme (*"tout ceci est mesure a 744 x 551 et a lui seul, 360 px n'est pas
couvert"*).

L'implication ne vaut que dans ce sens. **Ce que le cliquet ne verra pas** :

- un mauvais repli du a autre chose qu'un signe double - une ligne peut etre laide sans qu'aucun
  signe ne soit orphelin ;
- le rendu lui-meme : aucune ligne n'est mesuree ici, et la seule preuve que la propriete est tenue
  a l'ecran reste le releve au navigateur de `nadia-prof` ;
- **les 540 occurrences existantes**, que le cliquet gele au lieu de les refuser. Un enfant les voit
  aujourd'hui, et il les verra demain ; le cliquet ne raconte pas le contraire.

## Ce qui reste a decider a deux, et que je n'ai pas tranche seul

**Quel caractere ecrire** : l'espace insecable ordinaire (U+00A0, deja en service dans ce depot par
`ADR-0060` clause 2), l'espace insecable etroite (U+202F, la forme typographique francaise correcte
devant un signe double), ou le retrait pur et simple de l'espace.

**Le cliquet est volontairement agnostique** : les trois le rendent vert de la meme facon, parce que
son critere est l'absence d'espace **ordinaire** et non la presence d'un caractere particulier. Le
choix peut donc etre pris apres coup sans rien reecrire ici. Ce qui le decidera, et que je n'ai pas
mesure : U+202F est correct mais sa restitution depend des polices servies, et le depot epingle sa
feuille de style par empreinte (`ADR-0014`) - c'est une mesure de rendu, donc une mesure de
`ines-design` et de `nadia-prof`, pas une preference d'architecte.

## Regle executable

`adapters/adapter-content/src/test/java/com/recreo/adapter/content/AucunSigneDoubleNEstDetachableDeSonMotTest.java`.

**Ce n'est pas une regle ArchUnit** : l'objet juge est le texte rendu par le chargeur reel. Le compte
de regles ArchUnit du depot est inchange.

**Quatre clauses**, dont trois morsures. Le cliquet est **en egalite et non en plancher**
(`ADR-0055`, `ADR-0063`) : il est rouge quand un fichier monte, rouge quand un fichier baisse sans
que la table suive - le message donne le nombre a inscrire - rouge quand un fichier porte des
occurrences sans figurer dans la table, et rouge quand la table nomme un fichier qui n'en porte plus.

**Les morsures portent sur la declaration, pas sur le contenu**, et c'est le bon objet : c'est la
declaration qui change le jour ou un paquet neuf arrive. Le materiau, lui, est reel - le paquet de
production qui porte le plus d'occurrences, derive et non nomme.

**Une mesure gardee parce qu'elle a failli me couter une fausse preuve** : j'ai d'abord voulu prouver
la morsure sur le paquet historique de `be63ce3^`, celui qui sert de contre-exemple a `ADR-0065`.
**Il porte deja l'espace insecable devant ses 25 points d'interrogation**, et la morsure passait au
vert sans rien prouver. Un contre-exemple pour une regle n'est pas un contre-exemple pour une autre ;
le constater coute une execution, le supposer coute un garde-fou decoratif.

## Consequences

### Bonnes

- La croissance s'arrete au commit qui suit celui-ci. Les 4 orphelins neufs de s22 n'ont plus
  d'equivalent possible a s23.
- Un paquet neuf naitra conforme, sans que personne n'ait a s'en souvenir : il n'est dans aucune
  table, donc son compte doit valoir zero.
- Les lots 1, 2 et 3 de `nadia-prof` deviennent mesurables : chaque baisse est un diff dans la table.

### Mauvaises

- **Une table de 17 lignes a maintenir**, et chaque correction de contenu oblige a la toucher. C'est
  le prix de l'egalite, et il est paye a chaque commit de contenu qui touche un de ces fichiers.
- **Un cliquet gele une dette et peut faire croire qu'elle est traitee.** Elle ne l'est pas : les
  540 occurrences sont intactes, et 18 orphelins sont visibles a l'ecran aujourd'hui.
- Un contenu neuf paie une contrainte que les anciens ne paient pas. C'est assume : l'inverse
  reviendrait a exiger 540 corrections avant tout contenu neuf.

### Ce qui me ferait changer d'avis

- Si la table ne baissait dans aucun des trois prochains sprints, le cliquet serait a relire comme un
  aveu plutot que comme un outil, et la question a poser serait celle de la priorite, pas celle du
  garde-fou.
- Si `ines-design` mesurait que U+202F casse le rendu sur les polices servies, la section "ce qui
  reste a decider" se ferme sur U+00A0 par elimination, sans que ce fichier change ailleurs.

## Alternatives considerees

1. **Exiger zero occurrence tout de suite.** Ecartee : rendrait la porte technique rouge sur
   540 corrections que personne n'a commandees, dans des fichiers qui appartiennent aux auteurs de
   paquets, et dont l'ordre appartient a `camille-po`.
2. **Un cliquet sur le total seul (540).** Ecartee : il laisserait un fichier monter de dix pendant
   qu'un autre baisse de dix. Un plafond global n'interdit pas la croissance, il interdit la
   croissance nette - ce n'est pas la meme propriete.
3. **Poser l'insecable au rendu, dans un gabarit ou un filtre.** Ecartee en section 1 : contredit
   `ADR-0060`, et rendrait aveugles tous les garde-fous qui jugent le texte du chargeur.
4. **Mesurer les lignes rendues plutot que la donnee.** Ce serait juger `TYP-1` elle-meme, et non sa
   condition suffisante. Ecartee pour ce sprint : cela demande le rendu d'un document a un viewport,
   donc l'outillage que `ADR-0028` a deja refuse d'etendre, et cela ne couvrirait qu'un viewport a la
   fois - la limite que le releve de `nadia-prof` porte deja.
5. **Ne rien faire et attendre le lot 1.** Ecartee : les 4 orphelins neufs de s22 sont arrives
   pendant que le lot 1 attendait. Une dette sans cliquet grandit a la vitesse du contenu.

## Impact

- `adapters/adapter-content/src/test/java/com/recreo/adapter/content/AucunSigneDoubleNEstDetachableDeSonMotTest.java` : cree.
- `squad/decisions/adr/ADR-index.md` : ligne `ADR-0067` ajoutee.
- Aucun fichier de contenu modifie par cette decision.
