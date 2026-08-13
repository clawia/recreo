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

### 1 bis. Amendement du 2026-08-12 - la reserve H de `nadia-prof`, et elle avait raison

**Elle endosse la regle et la declare trop large d'une clause**, et son argument est juste : *"En
interdisant les deux d'un seul mouvement, la regle force le decor dans l'oreille de l'enfant - elle
obtient sur ce cas l'inverse de ce qu'elle cherche."* Masquer un decor **est** le bon usage
d'`aria-hidden` ; ma regle le rendait impossible.

Sa condition de fond, qui n'etait pas celle que j'avais anticipee : **le nom de l'enseigne ne doit pas
etre annonce**, et les quatre structures que j'avais inventoriees l'annoncent toutes les quatre. Ce
n'est pas l'attribut qui la gene, ni que le nom soit annonce : c'est **ou** il l'est - le fronton vit
entre l'enonce et la premiere proposition, et *"pour un enfant qui ecoute, il n'y a pas de au-dessus, il
n'y a qu'un avant"*.

**La regle amendee, en trois clauses qui se tiennent l'une l'autre :**

1. Un texte sous un masque est refuse, **sauf** si l'element qui le porte declare `data-decor` avec un
   motif non vide.
2. La declaration vit **sur l'element qui porte le texte**, jamais sur un ancetre. Sur un ancetre, elle
   reconstituerait exactement le defaut de cet `ADR` - une declaration posee sur un conteneur, qui
   devient fausse des que son contenu change.
3. **Tout element qui declare `data-decor` vit sous un masque.** Sans cette clause, la reserve serait
   une porte : un decor declare mais non masque est **annonce**, donc sa declaration dit le contraire de
   ce que le document fait. C'est cette clause-la qui tient la condition de `nadia-prof` par la
   structure, et non par la relecture.

`data-decor` est un attribut et non une classe, pour la raison d'`ADR-0017` (`data-role`) et
d'`ADR-0027` (`data-arrivee`) : ce qu'il declare n'est pas un habillage. Sa valeur est le motif, en
clair - **une declaration sans motif est un contournement, pas une intention**, et le vide est refuse.

**Le plafond.** La population de production vaut **zero** a ce jour, et un test l'epingle a zero. Le
jour ou l'enseigne y entre, ce nombre passe a 1 et le test le dit : la hausse est un acte delibere,
gagne devant `nadia-prof`, jamais une derive. C'est la lecon du plafond d'`ADR-0016` appliquee a ma
propre exemption.

**Pourquoi j'amende maintenant plutot que d'inscrire le declencheur.** Ma doctrine dit qu'une
abstraction se justifie sur un usage constate. J'allais donc reporter, avec un argument que je croyais
solide : aucun decor textuel n'existe dans ce depot, tout le decor etant dessine par `content: ""`
(`ADR-0027` clause 7). **Cet argument etait faux, et c'est `nadia-prof` qui le rend faux** : la
cinquieme structure qu'elle **prefere** est precisement un texte de decor masque. L'usage n'est pas
anticipe, il est demande par l'autorite pedagogique, en toutes lettres, et elle nomme le nom retenu ("Le
Copain Malin"). Reporter aurait ete refuser d'ecrire la regle du chemin qu'on va prendre.

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
| **Amendement** `enseigne-declaree-comme-decor` : la cinquieme structure que `nadia-prof` prefere - le nom en texte, sous le masque, l'element declarant son motif | **vert**, et le jeu porte bien un decor declare | vert, 1 decor declare. Sans ce cas vert, une clause impossible a satisfaire passerait pour une clause exigeante (lecon du jeu `animation-avec-repli` d'`ADR-0026`) |
| **Amendement** `decor-declare-hors-du-masque` : le motif declare, aucun masque au-dessus | rouge : la declaration ne remplace pas le masque | rouge, "span.cabinet-enseigne" |

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

## Ce que cet `ADR` ne garantit pas, et `nadia-prof` l'ecrit mieux que moi

*"`ADR-0035` garantit qu'aucun texte ne se cache dans un masque. Il ne garantit rien sur ce qu'un enfant
entend. Personne dans cette equipe n'a jamais ecoute cet ecran, moi comprise."*

Trois clauses executables ne remplacent pas une ecoute. Ce que les miennes tiennent est **structurel** :
un texte de jeu n'est jamais masque, un decor masque l'est deliberement et par ecrit, et un decor declare
n'est jamais annonce. Ce qu'elles ne peuvent pas dire : ce que le lecteur d'ecran prononce reellement,
dans quel ordre, et si le visage sans nom accessible ajoute du bruit. **Aucun Done pedagogique ne sera
prononce sur l'enseigne avant cette ecoute**, et cette phrase est d'elle, pas de moi.

## Verification

`mvn -B verify` sur l'arbre de ce lot, `JAVA_HOME` sur un JDK 21.0.10 : **BUILD SUCCESS, 382 tests,
0 echec** (371 avant ce lot).
