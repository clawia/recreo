# ADR-0035 - Aucun texte rendu ne vit dans un sous-arbre masque : le masque suit le decor, pas le conteneur

- **Statut** : Accepte
- **Date** : 2026-08-12
- **Auteur** : theo-archi
- **Sprint** : s10 (reconciliation avec `proposition-ines-meuble.md`)
- **Ne remplace rien**, n'amende rien. `ADR-0022` gouverne le masquage **par la feuille de style** (un
  habillage permanent declare sa regle de masquage) ; celui-ci gouverne le masquage **de l'arbre
  d'accessibilite**. Deux mots identiques, deux objets sans intersection.

## Contexte

`ines-design` a remonte la question et ne l'a pas tranchee, ce qui est exactement son role
(`proposition-ines-meuble.md`, section 2.2) : l'enseigne du meuble - le nom de la machine - vivrait
dans `.cabinet-bandeau`, qui porte `aria-hidden="true"` depuis S8-1 parce qu'il ne contenait alors
qu'une etoile decorative. Un enfant qui utilise un lecteur d'ecran n'entendrait donc pas le nom de la
machine.

**Le defaut structurel n'est pas l'attribut, c'est ce a quoi il est accroche.** `aria-hidden` est
pose sur le **conteneur** parce que, le jour ou il a ete ecrit, tout ce que ce conteneur portait etait
decoratif. Ce n'est pas une propriete du conteneur, c'est une propriete de son contenu **d'hier**. Le
jour ou du texte y entre, l'attribut devient faux **sans qu'aucune ligne ne change de sens** : personne
ne relit un attribut qui etait juste. C'est la meme forme de defaut qu'`ADR-0016` (un plafond qui
survit a sa dette) et que la lecon du perimetre enumere : une declaration qui decrit un etat, la ou
elle devrait decrire une intention.

**Mesure, methode dans le meme paragraphe.** Balayage de
`adapters/adapter-web/src/main/resources/templates/` a `b15bda1`, quatre gabarits : **trois** elements
portent `aria-hidden="true"` - `.cabinet-bandeau`, `.cabinet-socle`, `.icone-signal` - et **aucun** ne
contient de texte, ni en clair ni par `th:text`. La propriete est donc vraie aujourd'hui, et rien ne
la tient.

## Decision

### 1. La regle

**Aucun element rendu par le serveur ne pose de texte a l'interieur d'un sous-arbre porteur de
`aria-hidden="true"`.** Le texte compte sous ses deux formes : ecrit en clair dans le gabarit, ou pose
au rendu par `th:text` ou `th:utext`.

### 2. Ce que la regle ne decide pas, et qui appartient a `nadia-prof`

Elle ne dit **pas** ou l'enseigne doit vivre. Elle rend les quatre structures possibles egales devant
un meme controle : celle qui demasque le bandeau, celle qui resserre le masque sur le decor, celle qui
sort l'enseigne du bandeau, celle qui renonce a l'enseigne. Trois de ces quatre passent ; la quatrieme
- l'enseigne posee dans le bandeau masque, telle que la proposition l'ecrit - echoue. L'inventaire
chiffre des quatre est dans `avis-faisabilite-ecran-intermediaire.md`, section 9.

### 3. Le gabarit, et non le rendu

Le defaut existe des l'ecriture du gabarit, avant qu'aucune requete ne soit servie, et ne depend
d'aucune valeur de modele. Le lire dans le gabarit le prend donc plus tot **et sur une population
complete** : les quatre gabarits, y compris les branches qu'un corpus d'ecrans ne traverse pas. C'est
la lecon d'`ADR-0034` prise dans l'autre sens - un corpus de sorties ne voit que les etats qu'il joue,
une lecture de gabarit voit toutes les branches et aucun etat.

## Regle executable

**Fichier** :
`adapters/adapter-web/src/test/java/com/recreo/adapter/web/AucunTexteNeVitDansUnSousArbreMasqueTest.java`

Quatre clauses : la regle, sa non-vacuite, et ses deux mises a l'epreuve.

**Perimetre derive** (`ADR-0013` obligation 1) : les gabarits viennent d'un balayage de `templates/`,
les porteurs de masque d'une selection sur l'attribut, le texte de la lecture de chaque sous-arbre.
Aucune classe, aucun nom de fichier, aucun selecteur n'est ecrit dans le test. Un gabarit neuf, un
masque neuf entrent dans le controle sans que ce fichier change.

**Derivation totale** : les deux formes du texte sont comptees. Ne compter que le texte en clair aurait
laisse passer exactement le cas d'`ines-design`, dont le texte vient du modele.

**Non-vacuite** : `laProductionPorteBienDesSousArbresMasques` epingle le fait que des masques existent.
Le jour ou il n'y en a plus, la regle ne juge plus rien, et le message dit de supprimer le garde-fou
avec son motif plutot que de le laisser vert a vide.

### Preuves de morsure, sur du materiau que je n'ai pas ecrit

| Objet mis a l'epreuve | Attendu | Mesure |
|---|---|---|
| `enseigne-dans-le-bandeau-masque` : le balisage de la section 2.2 de `proposition-ines-meuble.md`, **repris tel quel** | rouge, en nommant l'element, l'attribut et le porteur du masque | rouge, "span.cabinet-enseigne porte th:text dans le sous-arbre masque de div.cabinet-bandeau" |
| `enseigne-en-clair` : le meme nom ecrit en clair dans le gabarit | rouge, par l'autre branche de la lecture | rouge, "ecrit du texte dans le sous-arbre masque" |
| Production a `b15bda1` | vert, sur une population de trois masques | vert |

**Le premier jeu fautif n'est pas de ma main, et c'est le point.** La lecon de s05 dit qu'une fixture
ecrite par l'auteur d'une regle ressemble a ce qu'il imagine. Ici, le contre-exemple est le balisage
qu'une conceptrice a reellement propose avant qu'on lui reponde : il n'y a aucun ecart possible entre
ce que la regle refuse et ce que le monde a ecrit.

**Pourquoi ce n'est pas une `ArchRule`.** Ce qui est juge est un arbre HTML, pas une dependance entre
types. Le compte de regles ArchUnit du depot reste **28**, methode inchangee (voir `ADR-0031`).

## Consequences

**Bonnes.**

- La question d'`ines-design` a une reponse executable au lieu d'un avis : quelle que soit la structure
  que `nadia-prof` choisit, la CI refuse celle qui rend le nom de la machine inaudible.
- Le controle est en place **avant** que la story soit codee. C'est la premiere fois de ce sprint qu'un
  garde-fou precede son objet plutot que de le suivre.
- Tout masque futur - un arc-en-ciel, une bande decorative de plus - entre dans le controle le jour ou
  le gabarit le pose.

**Mauvaises, et je les nomme.**

- **La regle ne dit rien de l'inverse** : un texte qui *devrait* etre masque et ne l'est pas (un decor
  rendu en caracteres, par exemple) n'est pas vu. C'est une dette assumee, sans population aujourd'hui
  - tout le decor du depot est dessine par `content: ""`, jamais par des caracteres, et `ADR-0027`
  clause 7 le tient deja.
- **`aria-hidden="false"` et les valeurs calculees ne sont pas lues.** La selection porte sur la valeur
  `true` litterale. Un `th:attr="aria-hidden=${...}"` echapperait. Je ne l'ecris pas aujourd'hui parce
  que la population est vide et qu'une clause sur un objet inexistant serait decorative ; la condition
  de reouverture est la premiere valeur de masque calculee qui entre dans un gabarit.
- La regle refuse une forme qui est parfois legitime ailleurs (un texte de rechange masque a cote d'une
  icone). Si ce besoin apparait, il se traite par un texte **hors** du sous-arbre masque, pas par une
  exemption - et c'est ecrit ici pour que l'exemption ne soit pas la premiere idee.

## Alternatives considerees

- **Repondre a `ines-design` dans l'avis, sans regle.** Rejete : c'est un souhait, et la story sera
  codee par quelqu'un d'autre, dans un autre lot, apres un arbitrage. Un avis ne survit pas a ce
  trajet ; un test rouge, oui.
- **Lire le rendu plutot que le gabarit.** Rejete par la section 3 : un corpus d'ecrans ne voit que les
  etats qu'il joue, et je viens de mesurer dans `ADR-0034` ce que cela coute.
- **Interdire `aria-hidden` sur un conteneur, et l'exiger sur les seules feuilles de l'arbre.** Rejete :
  c'est la bonne pratique dans le cas present, mais elle serait fausse en general - un bloc entierement
  decoratif a le droit d'etre masque d'un seul geste, et c'est le cas des trois masques
  d'aujourd'hui. La regle doit refuser le **defaut** (du texte inaudible), pas imposer une **forme**.
- **Attendre l'arbitrage de `nadia-prof`.** Rejete : la regle est vraie sous les quatre structures
  possibles. Attendre n'aurait rien appris et aurait laisse la story se coder sans controle.

## Ce qui me ferait changer d'avis

- Si `nadia-prof` juge que le nom de la machine **n'est pas** porteur de sens et doit rester decoratif,
  la regle reste juste et le depot reste vert : le nom ne serait alors pas un texte du gabarit, mais un
  decor dessine, hors de sa portee. La regle ne serait pas devenue inutile, elle serait devenue sans
  objet **sur ce point-la** - et sa non-vacuite continue de porter sur les trois masques existants.
- Si une valeur de masque calculee apparait, la selection litterale devient trop etroite et doit etre
  elargie, avec un jeu fautif de plus.

## Verification

`mvn -B verify` sur l'arbre de ce lot, `JAVA_HOME` sur un JDK 21.0.10 : **BUILD SUCCESS, 378 tests,
0 echec** (374 avant cet ADR, 371 avant ce lot).
