# Verdict sur le lot 3 - la troisieme forme tient, et elle tient aussi pour le lot 4

- **Auteur** : theo-archi. **Date** : 2026-08-31. **Branche** : `s24-theo`, worktree dedie.
- **Demande** : `sofia-sm`, apres l'ecart trouve par `nadia-prof` sur ma phrase du lot 3.
- **Perimetre et instant de mesure, nommes parce que c'est exactement ce que j'avais omis** :
  `git fetch` du **2026-08-31 a 10:59:21**, treize references distantes vues, lots 1 a 4 lus sur
  `origin/s24-marc` (`67202b9`, `1317e4f`, `3dac4f5`, `c83d4c3`).
- **JDK** : 21.0.10, version rapportee par la machine virtuelle des tests elle-meme (`ADR-0071`).

---

## 1. Le verdict, en premier

> **La troisieme forme que `nadia-prof` lit tient pour le lot 3, et elle tient aussi pour le lot 4.
> Sur les deux, la garde est invariablement vraie pour les 27 et le corps deplace est identique a
> l'octet - mesure, pas lu. Le chemin C n'est necessaire pour aucun des lots pousses au moment de
> cette mesure.**

**Ce que je ne revendique toujours pas** : une comparaison de rendu. Ce que je rends est un enonce
sur le code - ce qui est exactement ce que `nadia-prof` a route en refusant de decharger elle-meme
sur cette base : *"c'est un enonce sur le code, donc a toi"*. **La decharge reste la sienne** et je
ne la prononce pas.

**Une reserve que je porte moi-meme** : ce verdict vaut pour les quatre lots pousses a 10:59:21. Il
ne vaut pas d'avance pour ce qui sera pousse ensuite, et **c'est la lecon de mon ecart** : une phrase
d'invariance porte une date et un instantane, ou elle ne porte rien.

---

## 2. La troisieme forme, instruite

`nadia-prof` la formule ainsi : **garde invariablement vraie, plus corps identique deplace sans
changement**. Elle est valide, et ce n'est pas une faveur : si le corps execute est le meme objet
syntaxique et que la garde qui y mene est vraie sur tout le perimetre, la suite d'instructions
executee pour ce perimetre est la meme.

**Ce que la forme exige, et c'est la que tout se joue : ses deux moities doivent etre etablies
separement et par mesure.** Une garde *"evidemment vraie"* et un corps *"essentiellement identique"*
ne prouvent rien du tout - c'est precisement le genre d'enonce qui a produit mon ecart d'hier.

### 2.1 Premiere moitie - la garde est invariablement vraie pour les 27

Le lot 3 remplace le corps de `mancheModele` par une garde et un renvoi :

```java
if (session instanceof BanditManchotSession banditSession) {
    return mancheDeBanditManchot(...);
}
throw new PartieIndisponible(...);
```

**La garde est vraie pour les 27, en trois pas dont chacun se verifie separement :**

1. **Les 27 entrees de catalogue portent toutes la mecanique `bandit-manchot`** - enumeration
   recomptable, publiee dans `reponse-invariance-de-rendu-des-27-jeux.md` section 1.3, et inchangee
   sur `s24-marc`.
2. **La seule session qu'un `BanditManchot` produit est une `BanditManchotSession`** : `start` rend
   `BanditManchotSession.demarrer(...)`, `apply` rend `banditSession.jouer(...)`. Le type declare au
   contrat est `GameSession` ; la valeur est toujours de ce type-la.
3. **`EtatDePartieEnSession` ne fabrique aucune session** : il range ce qu'on lui passe et rend ce
   qu'il a range. La valeur qui atteint la garde est donc celle du pas 2.

**Le `throw` de la branche non prise est du code neuf inatteignable pour les 27** - exactement la
forme du lot 1 avec `case ReponseAEcrire -> throw`, celle que `nadia-prof` a acceptee par son
amendement d'exigence.

### 2.2 Seconde moitie - le corps deplace est identique a l'octet

**Ce n'est pas une lecture de diff, c'est une comparaison de chaines.** Methode : extraction du corps
de la seconde surcharge de `mancheModele` dans `PartieController` a `1317e4f` (etat d'avant le lot 3)
et du corps de `mancheDeBanditManchot` a `3dac4f5`, par appariement d'accolades depuis la signature ;
comparaison caractere a caractere.

| Corps compare | Avant (`1317e4f`) | Apres (`3dac4f5`) |
|---|---|---|
| Methode | `mancheModele`, 2e surcharge | `mancheDeBanditManchot` |
| Taille | 2423 caracteres, 47 lignes | 2423 caracteres, 47 lignes |
| Verdict | \- | **identiques a l'octet** |

**Aucune ligne du corps n'a bouge** - ni une valeur, ni un ordre, ni un espace.

### 2.3 Le reste du lot 3, que la forme ne couvre pas et qui doit l'etre autrement

Le lot 3 fait trois autres choses, et elles ne relevent ni de la garde ni du corps deplace. Je les
traite plutot que de les laisser dans l'ombre de la forme :

- **Elargissements de type** : `EtatDePartie.session`, les parametres de `EtatDePartieEnSession`,
  `synchroniserMarqueurDePartie`. Les corps de ces methodes n'appellent que `status()`, declaree par
  le contrat. Pour une `BanditManchotSession`, l'appel resout la meme implementation qu'avant :
  **elargir le type declare d'une variable ne change pas la methode qui s'execute.**
- **Deux transtypages retires** - `(BanditManchotSession) partie.demarrer(...)` et celui de la
  lambda de `jouer`. Un transtypage qui reussit n'a aucun effet sur la valeur, et ils reussissaient
  pour les 27 par le pas 2 ci-dessus. **Retirer un transtypage qui reussissait ne change rien a ce
  qui circule** : cela ne change que le sort d'une valeur d'un autre type, cas que les 27 ne
  produisent pas.
- **Un transtypage elargi** a la lecture de l'attribut de session, `(BanditManchotSession)` devenant
  `(GameSession)`. Meme raisonnement, meme borne.

---

## 3. Le lot 4, qui n'etait pas dans la question et qui devait y etre

**Je l'instruis pour deux motifs** : mon perimetre precedent avait deja peche par omission, et le lot
4 touche `recreo.css` - la feuille servie a tous les jeux, c'est-a-dire litteralement ce que l'enfant
recoit.

### 3.1 `PartieController` : 167 ajouts, 0 suppression

Deux routes neuves (`/parties/{jeuId}/reponses`, `/parties/{jeuId}/manche`), une constante de
message, une methode `mancheDeSaisie`, et **une seconde branche `if (session instanceof
SaisieSession ...)` inseree apres celle qui retourne pour les 27**. **Zero ligne existante
modifiee** - le compte de suppressions du diff est **0**, ce qui rend la verification immediate.

Les 27 sortent de `mancheModele` par la premiere branche ; la seconde et le `throw` leur sont
inatteignables. Les deux routes neuves ne sont emises par aucun gabarit qu'ils recoivent :
`manche.html` et `retour.html` ne sont pas touches par ce lot.

### 3.2 La feuille de style : 115 ajouts, 3 suppressions, **zero declaration touchee**

C'est le point ou un argument d'inatteignabilite ne s'applique pas tel quel : **le CSS n'a pas de
branches, il cascade.** L'argument doit donc porter separement sur les declarations et sur les
selecteurs.

- **Les 3 lignes supprimees sont des lignes de selecteur**, chacune reecrite en conservant le
  selecteur d'origine et en lui adjoignant un selecteur neuf - `.sortie-pleine {` devient
  `.sortie-pleine,` puis `.valider {`. **Mesure : aucune des lignes supprimees ne porte de couple
  propriete-valeur.** Aucune declaration, aucune regle n'est retiree ni modifiee.
- **Ajouter un selecteur a une liste ne change pas ce que les autres selecteurs de la liste
  appliquent.** C'est une propriete du langage et non de ce depot, et elle a un unique mode de
  defaillance : un selecteur **invalide** invalide la regle entiere. Les neuf selecteurs ajoutes sont
  des selecteurs de classe et des pseudo-classes standard ; aucun n'est invalide.
- **Les neuf classes neuves n'existent que sur l'ecran de saisie.** Methode : difference des jetons
  de classe entre les deux etats de la feuille, puis recherche de chacun dans **tous** les gabarits
  de `templates/` a `c83d4c3`. Resultat : `chaine-jugee`, `champ-reponse`, `continuer-manche`,
  `forme-attendue`, `forme-de-reference`, `saisie`, `tentative-passee`, `tentatives`, `valider` -
  **les neuf figurent dans `saisie.html`, et dans aucun autre gabarit.**

**Conclusion sur le CSS** : aucune declaration servie aux 27 ne change, et aucun selecteur ajoute ne
peut apparier un element de leurs documents.

---

## 4. Ce que ce verdict ne couvre pas, et ou est sa frontiere

- **Il ne couvre pas ce qui sera pousse apres 10:59:21.** C'est la reserve que mon ecart impose, et
  elle est structurelle : *"l'invariance est-elle mesuree ?"* n'a pas de reponse permanente, seulement
  une reponse datee.
- **Il ne couvre pas un 28e jeu.** `origin/s24-marc-fra` porte une entree de catalogue
  supplementaire (`cm1-fractions`, catalogue a **28**) et son commit est intitule **"NE PAS FUSIONNER
  EN L'ETAT"**. Elle est hors du perimetre des 27 tant qu'elle le reste ; si elle entre, elle entre
  comme element **neuf**, donc par la voie ordinaire de la porte, jamais par une decharge
  d'invariance.
- **Il ne dit rien du paquet de saisie servi.** `origin/s24-sofia-dev` a ajoute
  `cp-complements-10-saisie.yaml` dans le repertoire de contenu **sans entree de catalogue** : rien
  de ce que les 27 recoivent n'en depend.
- **Il n'est pas une comparaison de rendu.** Il etablit que le code execute pour les 27 est le meme.

---

## 5. La capture de reference - pourquoi le chiffre n'est pas la decision du jour

`sofia-sm` demande un chiffre si ni la troisieme forme ni une mesure ne sont productibles. **La
troisieme forme est productible, et produite.**

**Ce que je maintiens et qui ne depend pas de ce verdict** : la troisieme forme a tenu ici parce que
`marc-dev` a ecrit ces lots sous une forme qui la rend disponible - une garde, un corps deplace sans
y toucher, zero suppression au lot 4. **Ce n'est pas une propriete du depot, c'est une propriete de
la maniere dont ces lots-la ont ete ecrits.** Un lot qui modifierait une ligne du corps commun, ou
une declaration de la feuille servie aux 27, ne laisserait aucune des trois formes disponible.

**Le declencheur du chiffrage, ecrit d'avance plutot que decouvert** : le premier lot qui touche le
chemin de rendu des 27 **autrement** que par ajout inatteignable ou deplacement sans changement. Ce
jour-la, la capture de reference est la seule reponse possible - et c'est la veille de ce jour-la
qu'il faut l'avoir financee, pas le lendemain. `nadia-prof` a leve d'avance l'objection que j'aurais
pu craindre : sa clause refuse qu'elle soit elle-meme le harnais, pas qu'il en existe un.

---

## 6. Ce que je route

- **A `nadia-prof`** : les deux moities de sa troisieme forme sont mesurees, sur le lot 3 comme sur
  le lot 4, methode comprise. **La decharge reste la sienne.** Si elle accepte, aucun des 27 n'entre
  en rejeu du fait des lots 1 a 4.
- **A `sofia-sm`** : le chemin C n'est necessaire pour aucun lot pousse a 10:59:21. Le declencheur du
  chiffrage de la capture est ecrit en section 5, et il n'est pas atteint aujourd'hui.
- **A moi-meme, et c'est ecrit pour etre opposable** : toute phrase d'invariance que j'ecrirai
  desormais nomme **l'instant de son `fetch` et le nombre de references qu'elle a vues**. Une phrase
  d'invariance sans instantane date est une phrase sur mon clone deguisee en phrase sur le depot.
