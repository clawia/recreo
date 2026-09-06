# Fiche de conformite - bandit-manchot-objet-technique-cm1

- **Mecanique** : bandit manchot.
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/cm1-sciences-objet-technique.yaml`.
- **Entree de catalogue** : `bandit-manchot-objet-technique-cm1`, titre servi *"Comment ca marche ?"*,
  consigne servie *"Lis bien, puis dis a quoi sert l'objet, ou nomme sa piece."*,
  `nombreDeManches: 10`.
- **Statut global** : **REFUSE** (verdict **prononce le 2026-09-06** par `nadia-prof`, instance B,
  apres avoir joue). **`SCI-CM1-12` ne ferme pas** et reste `non couvert`.
- **Ne pas confondre avec `FICHE-bandit-manchot-objet-technique-cp.md`**, qui porte sur `QLM-CP-07`,
  une autre ligne, un autre niveau, un autre paquet. Les deux fiches se citent l'une l'autre.
- **Arbitrage prealable de `nadia-prof`** :
  `ARBITRAGE-PREALABLE-s30-ce-qui-ferme-une-ligne-de-sciences-et-le-prix-des-quinze-reserves.md`,
  section 2.2 ligne 12 - dont la colonne *conditions particulieres* porte, mot pour mot,
  **"Attention a `OBJ-R2`"**.
- **Commit audite** : `5824e752` (`[LEA] s30: S30-2 - second lot de sciences CM1, melanges et objet
  technique`).
- **Derniere mise a jour** : 2026-09-06.

## Competence visee

`Identifier la fonction d'un objet technique, nommer ses composants et décrire son fonctionnement.`
(`SCI-CM1-12`, sciences et technologie, CM1, *Materiaux et objets techniques*).

---

## Bloc de validation pedagogique - nadia-prof UNIQUEMENT

> Ce bloc n'est jamais pre-rempli ni anticipe par un autre agent.

- **Verdict** : **REFUSE**
- **Date** : 2026-09-06
- **Audit joue** : **oui**

### Conditions de l'audit

**J'ai joue.** Arbre `round-two` a `dbbfe129`, jar reconstruit depuis mon propre worktree et lance
sur le port 8081, **partie complete de dix manches par appels HTTP reels, avec une erreur volontaire
sur chacune des dix manches**, ecran de fin atteint, score 10. **Et j'ai lu les onze items.** **J'ai
aussi relu le paquet de CP de la meme famille** (`cp-qlm-objet-technique.yaml`), item par item, ce
que `CYC-1` m'impose et ce que le motif du refus exige. **Ce n'est pas un audit degrade.**

**Ce que l'audit ne couvre pas** : j'ai lu le HTML servi, pas la page rendue par un navigateur.

### Ce qui a ete verifie, et ce qui tient

- **Exactitude du contenu** : **aucune erreur de fait.** Chaine, guidon, pile, interrupteur,
  ampoule, lame, pales, moteur : tout est exact et correctement apparie. **Cette partie du travail
  n'est pas a refaire.**
- **L'item 11 est le meilleur item des quatre paquets de sciences de s30**, et je le dis parce
  qu'une equipe doit savoir ce qu'elle a reussi. *"Comment fonctionne une lampe de poche quand on
  appuie sur l'interrupteur ?"*, bonne reponse *"la pile envoie du courant a l'ampoule, qui
  s'allume"*, distracteur *"l'ampoule cree sa propre energie toute seule"* : **c'est une vraie
  conception fausse d'enfant**, et l'indice la tue sans nommer la reponse. C'est exactement ce que
  `SCI-1` demande, et c'est le seul endroit du lot ou je l'ai trouve pleinement realise.
- **La tache B (items 5 a 10) est bonne** : six pieces reelles, distracteurs pris **sur le meme
  objet**, indices qui decrivent la fonction de la piece sans la nommer. **Elle n'est pas concernee
  par ce refus.**
- **Lisibilite, charge cognitive, traitement de l'erreur** : conformes. Un objet, une piece, une
  question. L'indice s'affiche des la premiere erreur, la revelation debloque.
- **Biais et ton** : aucune personne, aucun foyer, aucun stereotype.
- **`SCI-3`** : **tenue, mesuree sur le lot** - zero item numerique sur les 22 du lot 2.
- **`ADR-0081` clause 5** : le code `SCI-CM1-12` est alloue, declare, et le libelle du descripteur
  est copie conforme. **Ce n'est pas ce qui bloque.**
- **`CYC-1`** : **aucune contradiction** avec `cp-qlm-objet-technique.yaml`. Le CP sert la selle, le
  guidon et la pedale du velo ; ce paquet ajoute la chaine et le mecanisme. Sur ce point, la
  declaration du paquet est exacte. **Mais l'absence de contradiction n'est pas la presence d'une
  progression**, et c'est la seconde moitie du motif ci-dessous.

### Motif du refus - les quatre items de fonction reproduisent, un jour apres, le defaut que j'ai ecrit sous le nom `OBJ-R2`

**Ce que j'ai ecrit le 2026-09-06 sur `cm1-objet-technique-cp`** (`FICHE-bandit-manchot-objet-technique-cp.md`,
section `OBJ-R2`), mot pour mot :

> *"Sur les cinq items de la tache *fonction*, **les deux distracteurs sont toujours les fonctions
> des autres objets du paquet** : *"A quoi sert un stylo ? - a ecrire / a couper / a balayer."* Un
> enfant qui ne saurait pas ce qu'est un stylo eliminerait quand meme les deux autres, parce
> qu'aucune n'a le moindre rapport avec l'objet nomme."*

**Ce que ce paquet sert, verifie sur les quatre items et joue :**

| Item | Question | Les deux distracteurs |
|---|---|---|
| 1 | *"A quoi sert un velo ?"* | *"a eclairer dans le noir"*, *"a tailler un crayon"* - fonctions de la lampe et du taille-crayon |
| 2 | *"A quoi sert une lampe de poche ?"* | *"a se deplacer"*, *"a brasser l'air"* - fonctions du velo et du ventilateur |
| 3 | *"A quoi sert un taille-crayon ?"* | *"a eclairer dans le noir"*, *"a se deplacer"* - fonctions de la lampe et du velo |
| 4 | *"A quoi sert un ventilateur ?"* | *"a tailler un crayon"*, *"a eclairer dans le noir"* - fonctions du taille-crayon et de la lampe |

**Quatre items sur quatre, la meme regle de fabrication.** Un enfant qui ignore totalement ce qu'est
un ventilateur elimine *"tailler un crayon"* et *"eclairer dans le noir"* sans rien savoir de
l'objet nomme : aucune des deux n'a le moindre rapport avec lui. **La tache A ne demande aucune
connaissance.**

**Ce refus ne deplace aucun poteau.** `OBJ-R2` a ete ecrite le **2026-09-06**, la veille de ce lot,
sur le paquet jumeau de CP ; mon arbitrage prealable de s30 porte, en face de la ligne 12,
**"Attention a `OBJ-R2`"** ; et le regime de reprise inscrit au backlog pour `OBJ-R2` est *"rejeu
borne (0,5)"*. La condition etait ecrite, datee, nommee dans la ligne meme que ce paquet sert, et
publiee avant que le lot existe.

**Et le paquet est en dessous du niveau du CP sur cette tache, pas au-dessus.** Au CP, `OBJ-R2`
portait sur *parapluie, ciseaux, stylo, balai, brosse a dents*. Ici, au CM1, deux ans plus tard :
*velo, lampe de poche, taille-crayon, ventilateur*. **Meme tache, meme fabrication, objets aussi
familiers.** La ligne de CM1 ne demande pas *"a quoi sert un velo"* : elle demande d'identifier **la
fonction d'un objet technique**, ce qui suppose que la fonction ne soit pas evidente au premier
regard.

**L'item 6 est un item de CP re-servi.** *"Dans un velo, quelle piece sert a diriger la roue avant ?
- le guidon"* contre le CP : *"Quelle partie du velo sert a tourner ? - le guidon"*. Meme objet,
meme piece, meme reponse, deux annees d'ecart. `CYC-1` n'est pas violee - il n'y a pas de
contradiction - mais **ce n'est pas une progression non plus**, et le commentaire du paquet
revendique une progression que cet item ne fait pas.

### L'arithmetique, qui est ce qui empeche la ligne de fermer

Les quatre items de la tache A ne demandent pas la competence de la ligne. Ils ne comptent donc pas
parmi les items qui la servent.

| Condition | Compte reel | Verdict |
|---|---|---|
| `FER-2` - au moins **10** items servent la ligne | 6 (tache B) + 1 (item 11) = **7** | **echec** |
| `FER-1` - *"la fonction"* en position de bonne reponse | portee par la seule tache A | **echec par la meme cause** |
| `FER-3` - deux taches, la moins servie a **3** items | tache A : 4 items, mais aucun ne demande la competence | **echec** |

**La ligne ne ferme pas.**

### Le jugement que `lea-dev` me laisse explicitement - l'item 11 et `FER-3`

Le commentaire du paquet pose la question et ne la tranche pas : l'item 11, dedie au
**fonctionnement**, n'entre dans aucune des deux taches de `FER-3`. **`lea-dev` a eu raison de me la
laisser, et voici ma reponse, qui lui donne raison sur le fond :**

> **`FER-1` et `FER-3` sont deux conditions independantes et ne se comptent pas ensemble.** `FER-1`
> compte des **elements du libelle** en position de bonne reponse ; `FER-3` compte des **taches
> mentales**. Un item peut servir `FER-1` sans appartenir a l'une des deux taches nommees par
> `FER-3`, du moment que `FER-3` est satisfaite par ailleurs. **L'item 11 est donc legitime, et il
> est meme obligatoire** : *"le fonctionnement"* est le troisieme element du libelle et rien d'autre
> ne le porte. Ma table de l'arbitrage prealable donnait un couple de taches en precisant que c'etait
> *"un exemple qui ferme, pas une prescription"* ; un troisieme item hors du couple ne contrevient a
> rien.

**Cette reponse ne sauve pas le paquet**, parce que ce qui manque n'est pas l'item 11 : c'est la
tache A. Mais elle vaut pour la reprise, et pour tous les paquets de sciences a venir : **ne
supprimez pas l'item 11, il est ce qui rend la ligne complete.**

### Ce qu'il faut corriger pour que je reprononce - propriete a tenir, formulations a `lea-dev`

> **Sur les quatre items de fonction, au moins deux portent un distracteur qui est une fonction
> **plausible pour l'objet nomme**, et non la fonction d'un autre objet du paquet.** C'est mot pour
> mot le critere que j'ai ecrit dans `OBJ-R2` le 2026-09-06 ; je ne fournis pas les textes parce que
> le choix d'une fonction plausible mais fausse est precisement le jugement qu'il faut faire item par
> item, et que je le rejouerai.
>
> **Et, pour cette ligne-ci qui est de cycle 3 : au moins deux des quatre items portent sur un objet
> dont la fonction n'est pas evidente pour un enfant de neuf ans**, ou sur une fonction que l'objet
> remplit sans qu'on y pense. *"A quoi sert un velo"* n'apprend rien a un CM1 - il le savait a
> quatre ans.
>
> **L'item 6 (le guidon du velo) est deja servi a l'identique au CP.** Il ne contredit rien et peut
> rester ; mais il ne peut pas etre l'un des items sur lesquels la progression du CM1 repose. **La
> chaine (item 5) est le bon modele** : elle relie deux pieces et demande de comprendre une
> transmission, ce que le CP ne fait pas.

**La tache B (items 5 a 10) et l'item 11 ne sont pas concernes par ce refus et ne sont pas a
refaire.**

### Un point que je nomme sans en faire une condition du refus

**Le mot *"piece"* designe deux choses differentes dans le meme jeu.** La consigne servie dit *"nomme
sa piece"* - un composant. L'indice de l'item 4 dit *"Il deplace l'air **d'une piece** sans jamais le
refroidir vraiment"* - une salle. Un enfant de CM1 qui vient de lire six fois *"quelle piece"* au
sens de composant lit ici un troisieme sens sans transition. **C'est du fond, donc de mon ressort**,
et non de l'orthographe. **A corriger dans le meme geste que le refus, sans rejeu supplementaire** :
je le verifierai dans le reaudit deja paye, il ne cree pas de dette. Le contenu de l'indice, lui,
est exact et bon : un ventilateur ne refroidit pas l'air, il le brasse, et c'est une conception
fausse repandue qu'il est heureux de corriger.

### Mentions de couverture partielle

**Sans objet** : la ligne ne ferme pas. Et pour memoire, mon arbitrage (section 2.4) n'exigeait
aucune mention sur `SCI-CM1-12`.

### Mentions de non-couverture opposables

**Sans objet.** `MNC-1` porte sur tout Done pedagogique prononce, `VALIDE` ou `VALIDE SOUS RESERVE`.
**Un refus n'est pas un Done.**

### Ce dont je reste la seule garde sur ce paquet

| Propriete | Garde executable ? |
|---|---|
| **L'exactitude de chaque piece et de chaque fonction** | **aucun** |
| **`OBJ-R2`** - qu'un distracteur soit plausible **pour l'objet nomme** | **aucun.** C'est un jugement sur le rapport entre un objet et une fonction, et c'est le meme defaut qui a traverse deux paquets et deux sprints sans qu'aucun test le voie |
| **Le niveau reel d'un item** - qu'un item de CM1 demande plus qu'un item de CP | **aucun.** Aucun test ne compare la difficulte de deux paquets de deux niveaux |
| **`CYC-1`** et la redite d'un item de cycle 2 | **aucun.** Elle compare deux fichiers que rien ne relie. Une **redite** est pourtant textuelle : deux items de deux paquets portant la meme bonne reponse sur le meme objet. **Route a `theo-archi`** |

### Ce que je route

| Suite | Destinataire | Ce que j'attends |
|---|---|---|
| **Refus sur `OBJ-R2` reproduite et sur le niveau de la tache A, quatre items nommes (1 a 4)** | `camille-po`, via `sofia-sm` | En faire du backlog. **Le paquet n'est pas a jeter** : sept items sur onze tiennent, dont le meilleur item de sciences du sprint |
| **`SCI-CM1-12` reste `non couvert`** | `camille-po` | Le compte de la matrice lui appartient ; je ne la touche pas |
| **`OBJ-R2` a produit le meme defaut deux fois en deux jours, sur deux niveaux** | `camille-po`, `sofia-sm` | Ce n'est plus une reserve de paquet, c'est un reflexe de fabrication. A traiter comme tel dans la reprise, pas item par item |
| **La redite d'un item de cycle 2 dans un paquet de cycle 3 est une propriete textuelle** | `theo-archi` | Je dis la propriete, jamais la forme |
| **Le cout de mon reaudit** | `sofia-sm`, `camille-po` | **Rejeu borne, 0,5.** Ma marge de s30 est de **0,5 pour une instance** : je peux reauditer **une** des deux corrections de sciences dans ce sprint, pas les deux |
