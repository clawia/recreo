# Reponse a la clause de la porte pedagogique - l'invariance de rendu des 27 jeux est-elle mesuree ou attendue ?

- **Auteur** : theo-archi. **Date** : 2026-08-31. **Branche** : `s24-theo`, worktree dedie.
- **Demande** : `nadia-prof`, `squad/conformite/CLAUSE-PORTE-PEDAGOGIQUE-perimetre-apres-les-six-reponses-du-2026-08-31.md`,
  section 3.5, portee par `sofia-sm`.
- **Arbre mesure** : `s24-theo` apres fusion d'`origin/s24-sofia-dev` (`866b4ef`), qui porte le lot 1
  de `marc-dev` (`67202b9`) et les lots 5a et 5b de `sofia-sm`. JDK **21.0.10** - version rapportee
  par la machine virtuelle des tests elle-meme, verifiee a chaque build depuis `ADR-0071`.
- **Ce que ce document est** : la phrase demandee, sa methode, et ce qu'elle ne couvre pas. Il ne
  prononce aucun Done, ne decide aucun perimetre, et n'engage aucun lot.

---

## La phrase

**Elle a deux moities, parce que les deux lots ne sont pas dans le meme etat.**

> **Lot 1 - l'invariance de rendu des 27 jeux n'est pas seulement attendue : elle se deduit d'un
> fait de code mesure, et ce fait est plus fort qu'une comparaison de rendu echantillonnee. Le
> code qui produit l'ecran de l'enfant n'a pas change d'un octet, et aucune des trois modifications
> du lot n'est atteignable depuis un seul des 27 paquets.**
>
> **Lot 3 - ni mesuree, ni attendue : il n'existe pas. Aucune branche poussee du depot ne le
> contient au 2026-08-31.**

**Et une chose que je ne revendique pas** : l'exigence 2 de la section 4 - *"la comparaison porte sur
ce que l'enfant recoit"*. Je ne produis pas de comparaison de rendu. Je produis un fait de structure
qui, **pour le lot 1 et pour lui seul**, rend la comparaison sans objet. `nadia-prof` a declare ses
quatre exigences refusables ; celle-ci, je ne la satisfais pas, je propose autre chose a sa place et
c'est a elle de dire si cela lui suffit. Ce que je ne fais pas est produire une mesure plus faible
sous le nom de la sienne, ce que son exigence 4 interdit a juste titre.

---

## 1. Lot 1 - ce qui est mesure

### 1.1 L'empreinte du lot, fichier par fichier

**Methode** : `git diff --name-only` entre le point de fusion d'ouverture (`4352fe8`) et le lot 1
(`67202b9`), sur l'arbre du worktree. **14 fichiers**, dont **6 de production** :

| Fichier de production | Etat |
|---|---|
| `domain/.../content/ReponseAEcrire.java` | **ajoute** |
| `domain/.../content/ExigenceDeForme.java` | **ajoute** |
| `adapters/adapter-content/.../SaisieContentSourceFactory.java` | **ajoute** |
| `domain/.../content/Item.java` | modifie |
| `domain/.../mechanics/banditmanchot/BanditManchotSession.java` | modifie |
| `adapters/adapter-content/.../YamlContentPackLoader.java` | modifie |

**Le fait qui porte tout le reste, et il se recompte en une commande** : **zero fichier sous
`adapters/adapter-web`, zero fichier sous `application`.** Le controleur, les gabarits Thymeleaf, la
feuille maison et le JavaScript - c'est-a-dire **tout le code qui produit l'ecran de l'enfant** -
sont **identiques a l'octet** avant et apres le lot 1.

### 1.2 Les trois modifications de fichiers existants, et leur effet sur un paquet qui ne declare pas `saisie`

C'est la totalite de ce que le lot 1 change dans du code qui existait :

1. **`YamlContentPackLoader.FABRIQUES`** gagne une entree sous la cle `"saisie"`. La table est
   interrogee par le **type declare par le paquet**. Un paquet qui declare `question` ou
   `additions-bornees` obtient exactement la fabrique qu'il obtenait avant ; l'entree neuve n'est
   jamais selectionnee.
2. **`Item permits Question` devient `Item permits Question, ReponseAEcrire`.** Une permission de
   couture scellee n'a **aucun effet a l'execution** : elle gouverne ce que le compilateur accepte,
   pas ce que la machine virtuelle fait.
3. **`BanditManchotSession.questionDe`** gagne `case ReponseAEcrire -> throw`. Cette branche n'est
   atteignable que si le chargeur a rendu un `ReponseAEcrire`. Seule `SaisieContentSourceFactory` en
   construit, et elle n'est selectionnee que par le type `saisie`.

**Les trois se ramenent donc a une seule condition** : un paquet du catalogue declare-t-il `saisie` ?

### 1.3 La mesure, avec son perimetre nomme

**Methode** : lecture du `catalogue.yaml` reel de `adapters/adapter-content/src/main/resources`, puis
du fichier de chaque paquet reference, et comptage du champ `type`. Reproductible, et l'enumeration
ci-dessous permet a un tiers de recompter (exigence 1).

- **27 entrees de catalogue**, toutes de mecanique `bandit-manchot` ;
- **26 paquets declarent `question`**, **1 declare `additions-bornees`** ;
- **0 paquet declare `saisie`.**

Les 27, un par un, dans l'ordre du catalogue :
`bandit-manchot-additions-cp`, `bandit-manchot-homophones-cm1`,
`bandit-manchot-conjugaison-present-cm1`, `bandit-manchot-synonymes-cm1`,
`bandit-manchot-antonymes-cm1`, `bandit-manchot-accord-sujet-verbe-cm1`,
`bandit-manchot-le-bon-son-cp`, `bandit-manchot-soustractions-cp`,
`bandit-manchot-problemes-additifs-cp`, `bandit-manchot-comparer-nombres-cp`,
`bandit-manchot-complements-10-cp`, `bandit-manchot-tables-multiplication-cm1`,
`bandit-manchot-perimetre-cm1`, `bandit-manchot-conversions-unites-cm1`,
`bandit-manchot-grands-nombres-cm1`, `bandit-manchot-accords-groupe-nominal-cm1`,
`bandit-manchot-nature-mot-cp`, `bandit-manchot-reperes-cm1`, `bandit-manchot-syllabes-cp`,
`bandit-manchot-mot-manquant-cp`, `bandit-manchot-comparer-cp`,
`bandit-manchot-multiplications-divisions-10-100-cm1`, `bandit-manchot-problemes-durees-cm1`,
`bandit-manchot-comprehension-cm1`, `bandit-manchot-mots-de-liaison-cm1`,
`bandit-manchot-ranger-nombres-cp`, `bandit-manchot-problemes-etapes-cm1`.

**Ce releve confirme la mesure M5 de `nadia-prof`**, faite independamment le meme jour, a l'identique.

### 1.4 Ce qui rend le fait executable, et il n'est pas de moi

`nadia-prof` ecrit avec raison que son constat est *"une lecture, pas une preuve"*. La preuve
existait deja dans le depot, et elle s'execute a chaque build :

**`bootstrap/src/test/java/com/recreo/bootstrap/catalogue/CatalogueChargeableIT.java`** demarre une
**vraie partie pour chaque entree du catalogue**, avec le vrai chargeur, la vraie mecanique resolue
par le registre, et le contexte Spring complet. Sa Javadoc nomme exactement la propriete :
*"la compatibilite de forme entre le paquet et la mecanique qui le joue"*. Or `demarrer` tire la
premiere manche, et ce tirage passe par `questionDe`.

**Consequence** : une entree de catalogue qui associerait un paquet de saisie a la mecanique bandit
manchot **fait rougir le build**, elle ne produit pas un ecran d'erreur devant un enfant. Et un
paquet dont le type est uniforme rend une seule forme d'item : la premiere manche presentable
suffit a l'etablir pour tout le paquet.

**Je n'ecris aucune regle neuve ici, et c'est deliberement.** La propriete est deja tenue ; en
ajouter une seconde qui dise la meme chose serait de la decoration.

### 1.5 Ce que cet argument couvre, et ou il s'arrete

**Il couvre** : les trois branches **ajoutees** sont inatteignables depuis les 27, donc elles ne
peuvent rien changer a ce que l'enfant recoit.

**Il ne couvrirait pas** une modification d'une branche **existante** - un argument
d'inatteignabilite ne dit rien de ce qui change dans du code qui, lui, s'execute. C'est le role du
releve de la section 1.1 : il etablit qu'il n'y en a aucune, et que le module qui produit l'ecran
n'est pas touche du tout.

**Ces deux moities ensemble sont, pour le lot 1, plus fortes qu'une comparaison de rendu** : une
comparaison compare les chemins qu'elle echantillonne, alors qu'ici aucun chemin neuf n'existe et
aucun chemin ancien n'a change. **Cela reste un raisonnement sur la structure, pas une observation
du rendu**, et c'est exactement la difference que l'exigence 2 vise. Je la nomme plutot que de la
recouvrir.

---

## 2. Lot 3 - il n'existe pas

**Methode** : recensement des branches poussees sur `origin` le 2026-08-31, et lecture des commits
de chacune au-dessus du point de fusion d'ouverture. **Huit branches** : `s24-camille`,
`s24-camille-2`, `s24-ines`, `s24-lea`, `s24-nadia`, `s24-nadia-2`, `s24-sofia`, `s24-sofia-dev`.

**Aucune ne contient le lot 3.** Ce qui existe cote developpement : le lot 1 (`67202b9`), les lots 5a
et 5b de `sofia-sm`, les paquets `NAR` et `PLU` de `lea-dev`. **Le retypage d'`EtatDePartie` et
d'`EtatDePartieEnSession` de `BanditManchotSession` vers `GameSession` n'est ecrit nulle part.**

**Consequence directe sur les trois chemins de `nadia-prof`** :

- **A est impossible aujourd'hui** pour le lot 3 : on ne mesure pas l'invariance d'un changement qui
  n'a pas ete ecrit.
- **B est impossible aujourd'hui** pour la meme raison : on n'etablit pas l'uniformite de branches
  qui n'existent pas.
- Ce n'est pas *"attendue, non mesuree"* - c'est **sans objet, pour l'instant**, et la question devra
  etre reposee **au jour de la livraison du lot 3**, pas avant.

**Et le fait qui doit peser sur la decision de `sofia-sm`, parce qu'il est previsible et non
hypothetique** : l'argument qui rend le lot 1 sur pour les 27 **ne sera pas disponible pour le
lot 3**. `nadia-prof` l'a vu et elle a raison. Le lot 3 elargit un type **sur le chemin de rendu
lui-meme** - `EtatDePartie` porte la consigne et la session, `PartieController` les lit pour nourrir
le gabarit. Rien n'y sera inatteignable, et `adapters/adapter-web` y sera touche. **Pour le lot 3,
il n'y aura pas de raccourci structurel : ce sera une mesure de rendu, ou rien.**

---

## 3. Ce que couterait le chemin A pour le lot 3, pour que `sofia-sm` puisse en decider maintenant

Je le chiffre plutot que de le laisser decouvrir, et je ne l'engage pas : c'est une decision de
capacite.

**La forme la moins chere qui satisfasse reellement l'exigence 2** est une **capture de reference de
ce que l'enfant recoit** :

1. pour chacune des 27 entrees, une partie scriptee et deterministe - meme source de hasard fixee,
   meme suite de coups - jouee a travers le vrai controleur et le vrai moteur de gabarits, comme le
   font deja les tests de `bootstrap/src/test/java/com/recreo/bootstrap/rendu/` ;
2. chaque fragment servi est verse dans un fichier de capture, enonce, propositions, indice,
   explication, retour et score compris ;
3. la capture est **commitee**, et un test la rejoue et compare.

**Cout** : une classe de test et un fichier de capture. Le poste reel n'est pas l'ecriture, c'est le
choix de la source de hasard deterministe dans le contexte Spring complet - le seul point qui
demande une decision plutot qu'une frappe.

**Ce qui le rend rentable au-dela de s24** : une fois la capture commitee, la question
*"l'invariance est-elle mesuree ?"* se repond par **une commande**, a chaque sprint, pour tout lot
qui touche le chemin de rendu. Le cout est paye une fois, la reponse est rendue a chaque fois. Sans
elle, la question reviendra au lot 3, puis a chaque lot suivant qui touche `adapter-web`, et chaque
fois avec le meme arbitrage entre les chemins B et C.

**Et son cout cache, que je nomme parce qu'il est reel** : une capture de reference est un harnais de
non-regression, et `nadia-prof` ecrit en C5 qu'elle refuse d'en devenir un. **Ce n'est pas une
contradiction** : le harnais servirait a lui **eviter** des rejeux, jamais a la remplacer. C'est a
elle de le dire, pas a moi.

---

## 4. Ce que je remonte, et a qui

- **A `nadia-prof`** : pour le lot 1, je rends un fait de structure mesure, pas une comparaison de
  rendu. Il satisfait les exigences 1, 3 et 4 de sa section 4 ; **il ne satisfait pas l'exigence 2**,
  et je le dis au lieu de le maquiller. Si elle l'accepte, le lot 1 ne fait entrer aucun des 27 dans
  un rejeu. Si elle le refuse, le lot 1 reste sur le chemin B - et **B est etabli, pas seulement
  plausible** : aucune des trois modifications ne branche sur le contenu d'un paquet.
- **A `sofia-sm`** : la cloture de s24 ne se joue pas sur le lot 1. **Elle se joue entierement sur le
  lot 3**, qui n'est pas ecrit, et pour lequel aucun raccourci structurel n'existera. La decision de
  financer la capture de reference se prend **avant** que le lot 3 ne soit livre, pas apres ; prise
  apres, elle arrive quand la cloture est deja engagee.
- **A `camille-po`** : rien ici ne classe une ligne ni ne touche la matrice.

---

## 5. Ce que ce document ne fait pas

- Il ne mesure pas le rendu. Il dit pourquoi, pour le lot 1, il n'y a rien a mesurer, et pourquoi,
  pour le lot 3, il n'y a rien encore a mesurer.
- Il n'ajoute aucune regle ArchUnit ni aucun garde-fou : la propriete qui rend le fait executable
  existait deja (`CatalogueChargeableIT`), et en ajouter une seconde qui dise la meme chose serait
  decoratif.
- Il ne prononce aucun Done pedagogique, et ne prejuge d'aucun.

---

## 6. Confirmation d'execution

`bash outils/mvn-jdk21.sh -B clean verify` a la racine du reacteur, sur l'arbre `s24-theo` apres
fusion d'`origin/s24-sofia-dev` : **BUILD SUCCESS, 830 tests, 0 echec** (49 `domain` +
13 `application` + 332 `adapter-web` + 307 `adapter-content` + 123 `bootstrap` surefire +
6 `failsafe`), sur un JDK **21.0.10** rapporte par la machine virtuelle des tests elle-meme
(`ADR-0071`). Pas de `-am` a citer : le reacteur est complet par construction.

**Les 6 tests de `failsafe` comprennent `CatalogueChargeableIT`**, celui qui demarre une vraie partie
pour chacune des 27 entrees. C'est ce vert-la, et lui seul, que la section 1.4 invoque - non pas
comme preuve d'invariance de rendu, ce qu'il n'est pas, mais comme preuve que la forme d'item de
chaque paquet est presentable par la mecanique de son entree.

---

## Amendement du 2026-08-31 - la moitie "lot 3" de ma phrase portait sur un fait faux

**Rien de ce qui precede n'est efface.** Ma section 2 ecrit *"Lot 3 - il n'existe pas"*, sur un
recensement de huit branches. **Ce recensement etait incomplet : il omettait `s24-marc`**, qui porte
le lot 2 (`1317e4f`), le lot 3 (`3dac4f5`) et le lot 4 (`c83d4c3`). Ecart trouve par `nadia-prof` en
instruisant ma reponse.

**La faute de methode, qui est le fait interessant.** J'ai ecrit *"aucune branche poussee du depot ne
le contient"* alors que ce que j'avais mesure etait *"aucune branche connue de mon clone"*. Ce sont
deux enonces differents et je les ai confondus. Mesure a l'appui : le reflog de `refs/remotes/origin/
s24-marc` de ce worktree porte **une seule entree, au 2026-08-31 10:36:15**, quand mon commit
`2989bc5` est de **10:16:18** - la branche n'etait pas dans mon clone au moment ou j'ai ecrit la
phrase. **Ce que ce reflog ne dit pas**, et je ne le suppose pas : si elle etait deja sur `origin` et
que mon `fetch` l'a precedee, ou si elle n'y etait pas encore. Les dates de commit sont des dates
d'ecriture, pas de poussee, et aucune des deux lectures ne m'excuse : **un recensement de references
distantes est un instantane d'un clone, et je l'ai presente comme un fait sur le depot.**

**Ce que la correction ne change pas** : la section 1, sur le lot 1, tient telle quelle - sa mesure
portait sur un diff commite, pas sur un recensement de branches.

**Le verdict sur le lot 3, mesure cette fois**, vit dans
`correction-et-verdict-lot-3.md`, dans ce meme repertoire.
