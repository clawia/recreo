# Accord - l'epinglage du lien d'en-tete se retourne (s15, DEC-0034)

- **Auteur** : theo-archi. **Date** : 2026-08-18.
- **Branche** : `s15-theo-epinglage`, worktree dedie, forkee de `round-two` a `739a161` ; lignee
  verifiee par `outils/verifier-lignee.sh`. Ce lot ne contient que ce fichier : la modification du
  test appartient a `marc-dev`, je ne l'ecris pas a sa place.
- **Objet** : le seul echec de la suite dans `../recreo-s15-marc-sortie`,
  `LActionNeDeclarePasMoinsQueLaSortieTest.leLienDeLEnTeteEstConfronteAuxSeulesActionsDeLAccueil`.
- **Complement de** `squad/sprints/s15-cinq-arbitrages-de-l-en-tete/exigences-theo-sortie-en-tete.md`
  (branche `s15-theo-sortie`), dont la section 3 disait "`ADR-0017` : le garde-fou ne change pas".
  **Cette phrase etait vraie de la clause et fausse de l'epinglage du meme fichier** ; c'est cet
  ecart-la que ce document solde.

## 1. Verdict : l'epinglage se **retourne**, il ne se retire pas

Mon patron de retrait d'epinglage (`ADR-0013`) ne s'applique pas ici, et c'est sa premiere condition
qui le dit : un epinglage se retire quand **sa propriete n'a plus de sujet vivant**. Ici le sujet est
vivant, et il a change de signe.

Assertion par assertion, comme les deux fois de s14 :

- **premiere assertion** - le couple `.btn.demarrer "Jouer"` contre `.btn.lien-accueil "Les jeux"` sur
  l'accueil : **inchangee et toujours vraie**. Le lien se montre toujours sur l'accueil, il y accompagne
  toujours les jeux a ouvrir, et la propriete d'`ADR-0017` y vaut pleinement. Rien a toucher ;
- **seconde assertion** - *le lien n'est confronte a aucune action d'un ecran de jeu* : sa premisse est
  annulee par l'arbitrage de l'utilisatrice du 2026-08-18. Elle **doit** etre fausse, et elle l'est.
  Mais ce qu'elle gardait n'etait pas la premisse : c'etait **l'arbitrage lui-meme, contre une
  defection silencieuse**. Elle rougissait si quelqu'un retirait la regle de masquage en gardant le
  lien. Retournee, elle rougit si quelqu'un **remet** un masquage du lien sur les ecrans de jeu -
  c'est-a-dire si le nouvel arbitrage est defait en silence, exactement le meme service dans l'autre
  sens.

**Le retirer serait donc perdre une garde qui a encore un objet**, et le retirer aujourd'hui pour le
reecrire au prochain arbitrage est ce que ce sprint a deja paye assez cher.

## 2. La forme retournee, et la seule contrainte que j'y mets

Deux assertions, la structure "par les deux bouts" que le remplacement de s06 avait etablie :

- **(a)** le couple de l'accueil existe - inchangee, telle quelle ;
- **(b)** **au moins un couple confronte `.btn.lien-accueil` a `.btn.proposition`** - l'inverse exact de
  l'ancienne. Elle interdit qu'un masquage du lien reapparaisse sur les ecrans de jeu, et elle
  l'interdit sans nommer aucun ecran.

**Contrainte unique, et c'est la seule chose que je refuse** : la forme retournee ne doit pas enumerer
les ecrans ou le lien se montre. "Confronte aux actions de l'accueil **et des manches**" ecrit comme une
liste serait un comptage de la taille du depot au jour ou il est ecrit - la faute que j'ai fait corriger
dans `EnTeteEtMenuTest` en s14. La classe d'action (`.btn.proposition`) est ce qu'`ADR-0017` compare ;
elle suffit, et un cinquieme ecran de jeu entrera sans que personne y pense.

**Le nom de la methode doit cesser de promettre "seules actions de l'accueil"**, qui devient faux. Le
choix du nom est a `marc-dev` ; la contrainte est qu'il ne promette pas de liste d'ecrans.

**Un menage gratuit a faire dans le meme geste** : le predicat teste encore `.btn.encore`, une classe
qui n'existe dans aucun gabarit depuis s10. Verifie : `grep -rn "encore" --include=*.html` sur
`templates/` ne rend qu'un mot francais dans un commentaire. Un disjoint mort dans un predicat de
garde-fou est du poids qui fait croire a une couverture.

**Motif ecrit, exige par `ADR-0013`** : la seconde assertion n'est pas affaiblie, elle est **retournee
sur le meme objet** par un arbitrage de l'autorite au-dessus de la squad, date du 2026-08-18
(`DEC-0034`). Le refus de `nadia-prof` du 2026-08-07 reste lisible dans sa fiche, et cet epinglage cesse
de le citer comme motif - c'est la seule ligne de l'ancien message qui devient fausse.

**Compte** : aucune methode ajoutee ni retiree. Une methode renommee, une assertion retournee, un
disjoint mort supprime.

## 3. Le point de methode : oui, cela generalise - et cela **remplace** mon correctif de s14

Mon correctif de s14 disait : *tout avis portant sur le retrait d'un element de production publie le
releve des sources de test qui nomment cet element en litteral.* Applique a la lettre en s15, il a
fonctionne sur `.retour-accueil` - le releve etait juste et complet - et il **ne pouvait pas** attraper
ce cas : `.lien-accueil` ne disparait pas, il change d'ecrans, et ma commande cherchait un sujet perdu.

**Ce n'est pas un incident a ajouter, c'est que j'avais nomme le declencheur trop etroit.** Le
declencheur juste n'est pas le retrait : c'est **tout changement de l'ensemble des ecrans ou un element
est visible** - apparition, disparition, masquage, demasquage. Les quatre sont la meme operation sur le
meme ensemble, et **la meme commande les couvre toutes les quatre**, sans en ajouter une seconde.
Verifie sur ce cas plutot qu'affirme : `grep -rn "lien-accueil" --include=*.java` sur les sources de
test aurait rendu cet epinglage immediatement.

Je remplace donc la formulation de s14 par celle-ci, plutot que d'empiler une regle de plus :

> **Tout avis qui fait changer les ecrans ou un element est visible publie, avec son resultat, le
> releve des sources de test qui nomment cet element en litteral - dans les deux sens, qu'il en gagne
> ou qu'il en perde.**

Une phrase de moins qu'avant, un perimetre plus large, une seule commande. **Ce qu'elle ne couvre
toujours pas est inchange et je ne le cache pas** : un garde-fou dont la *derivation* lit une source
dont l'element est la derniere instance reste invisible a une commande - c'est une lecture de corps de
methode, et c'est ce qui m'avait echappe en s14 sur `laFormeDeLaProductionAvantCeLotFaitEchouerLaClause1`.

## 4. Ce que ce document ne fait pas

Il ne modifie aucun test - c'est le lot de `marc-dev`. Il ne prononce aucun Done pedagogique. Il ne
rediscute pas l'arbitrage de l'utilisatrice, et il ne juge pas le refus de `nadia-prof`, qui reste
entier dans sa fiche. Et il ne revendique aucun chiffre que je n'ai pas mesure : les comptes de ce lot
sont ceux que `sofia-sm` a pris dans le worktree de `marc-dev`, avec sa commande.
