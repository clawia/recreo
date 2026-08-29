# `M4` et le balayage des conditions de mecanique - nadia-prof, 2026-08-29

- **Auteure** : `nadia-prof`. **Date** : 2026-08-29. **Sprint** : s22.
- **Branche** : `s22-nadia-2`, worktree dedie, ouverte sur `round-two` a `2968591`.
- **Ce que ce document est** : l'etat reel de `M4` aujourd'hui, le sort de son declencheur de
  reouverture, et le balayage des treize conditions de mecanique `M1` a `M13` contre l'arbitrage de
  l'utilisatrice du 2026-08-28. Trois questions posees par `camille-po`, qui a refuse de les
  trancher parce qu'elles vivent dans mon bloc exclusif. Elle a eu raison.
- **Ce que ce document n'est pas** : ni un Done pedagogique, ni un audit, ni une reouverture d'un
  Done prononce avant le 2026-08-28. `cm1-perimetre` et `cm1-reperes` ne sont pas touches.
- **Ou il vit, et pourquoi ici** : dans un fichier a part. **Je ne touche aucune fiche de
  conformite** - `camille-po` en edite en parallele, et le bloc `M1` a `M13` est cite par dix-neuf
  d'entre elles. La propagation lui revient (section 5).
- **Ce que je declare degrade** : je n'ai rien joue et rien mesure. Il n'y a rien a jouer : ce
  document statue sur des conditions ecrites, contre des decisions ecrites. Tout ce que j'affirme
  ci-dessous est verifiable dans le depot par un tiers, sans me le redemander.

---

## 1. L'etat reel de `M4` aujourd'hui - trois couches, dans l'ordre ou elles se sont posees

Le texte de `M4` dans `FICHE-bandit-manchot-additions-cp.md` dit encore, mot pour mot :

> *"`M4` - le message est vu sans defiler, sur les huit jeux, a 360 x 640. **Condition bloquante, et
> c'est la seule de ce bloc que je qualifie ainsi.** Le message, l'indice de `M1` et la revelation de
> `M5` sont entierement au-dessus du pli (`y = 640`)..."*

**Ce texte ne dit plus ce que je pense, et il ne le disait deja plus avant hier.** Trois couches se
sont posees dessus, et il faut les tenir separees parce qu'elles n'ont pas la meme portee :

| Date | Ce qui s'est passe | Effet sur `M4` |
|---|---|---|
| **2026-08-13** | `DEC-0025`, arbitrage de l'utilisatrice qui joue l'increment de s10 et choisit de le livrer en connaissance du defaut | `M4` **cesse d'etre bloquante**. Elle reste une condition de ma porte, non tenue, avec un declencheur de reouverture |
| **2026-08-22** | `DEC-0040`, la cible devient ordinateur et tablette, reference 744 x 551 | La reference **360 x 640** que `M4` porte dans son texte **cesse d'exister**. La condition continue de vivre en citant un ecran que le produit a abandonne |
| **2026-08-28** | Arbitrage de l'utilisatrice, lecture large, trace par `DEC-0053` | `M4` **sort entierement de ma porte**. Ce n'est plus une condition non bloquante : c'est une propriete que je ne juge plus |

**La reponse a la question 1, en une phrase : `M4` n'est pas une condition levee, c'est une
propriete sortie, et les deux ne sont pas la meme chose.** Une condition levee reste mienne, non
tenue, et peut redevenir bloquante ; une propriete sortie ne m'appartient plus du tout. `M4` a ete
la premiere pendant quinze jours ; elle est la seconde depuis hier.

**Le compte de la fiche est faux, et il l'est deux fois.** L'en-tete annonce *"Treize conditions
`M1` a `M13`, dont **deux bloquantes** : `M4` (...) et la clause 1 de `M13`"*.

1. **`M4` ne bloque plus depuis le 2026-08-13**, et ne m'appartient plus depuis le 2026-08-28.
2. **La clause 1 de `M13` est tenue depuis le 2026-08-14** - *"tenue, pour la premiere fois de ce
   projet"*, zero partie detruite sur cinquante-trois cas ou j'ai presse Tabulation puis Entree
   (verdict `VALIDE SOUS RESERVE` du 2026-08-14, reverification exigee par `DEC-0026`).

**Attention a ne pas confondre les deux corrections, parce qu'elles ne disent pas la meme chose** :
`M4` **sort**, tandis que la clause 1 de `M13` **reste bloquante par nature et se trouve
satisfaite**. Une condition bloquante tenue n'est pas une condition qui a cesse de bloquer : si elle
cassait demain, elle bloquerait de nouveau. **Le compte juste aujourd'hui est donc : sur les treize,
une seule condition est bloquante par nature - la clause 1 de `M13` - et elle est tenue.**

**Ce que ce texte faux produit concretement.** Dix-neuf fiches de conformite citent le bloc
`M1` a `M13` comme *"arbitrage prealable de mecanique en vigueur pour tout le catalogue"*. Parmi
elles, **huit** recopient la formule au present *"dont **deux bloquantes** - `M4` (le message
d'erreur doit etre vu sans defiler...)"*, et **quatre** une variante au passe *"dont deux ont ete
bloquantes pour d'autres jeux du catalogue"*. **Les trois paquets neufs de ce sprint vont citer ce
meme bloc.** Sans cette correction, `marc-dev`, `lea-dev` et `sofia-sm` travaillent aujourd'hui
contre une condition bloquante qui n'existe plus, sur une reference d'ecran qui n'existe plus.

---

## 2. Le declencheur de reouverture de `M4` : il tombe, et je le dis plutot que de le laisser dormir

`DEC-0025`, point 4, ecrit :

> *"**Declencheur de reouverture de `M4`.** (...) `M4` revient devant `nadia-prof` pour remesure, sur
> le rendu reel a 360 x 640, des que les trois stories de la section suivante sont livrees, en tout
> ou en partie ; elle n'est pas close par une seule d'entre elles a elle seule sur les jeux qu'elle
> ne couvre pas."*

**Ce declencheur est sans objet, pour deux motifs independants, dont chacun suffit.**

1. **Son objet m'est retire en entier.** Il prescrit deux gestes, et deux seulement : une **remesure
   par moi**, puis une **reevaluation** de `M4` comme condition bloquante. L'arbitrage du 2026-08-28
   retire le premier (*"la mesure n'est plus necessaire a la validation du jeu"*) et le second (je ne
   prononce plus de `REFUSE` sur ce motif). **Un declencheur dont le seul aboutissement est une
   decision que je ne peux plus prendre ne declenche rien.**
2. **Sa reference est morte depuis le 2026-08-22.** Il prescrit une remesure *"sur le rendu reel a
   360 x 640"*. `DEC-0040` a remplace cette reference par 744 x 551. Le declencheur etait donc deja
   partiellement vide **six jours avant** l'arbitrage d'hier, et personne - moi comprise - ne l'avait
   ecrit. **C'est la troisieme fois en deux jours que je trouve une de mes conditions vivant sur une
   reference que le produit avait abandonnee** - apres `COMP-4`, `COMP-6` et `COMP-7`. Le mecanisme
   est le meme et il est de moi : j'ecris une condition avec un chiffre dedans, et rien ne me ramene
   la relire quand le chiffre change.

**Je declare donc le declencheur de `DEC-0025` point 4 sans objet, a compter du 2026-08-28.** Ce que
j'ai le droit de declarer et ce que je n'ai pas le droit de faire, pour que la frontiere soit nette :
**le declencheur s'adresse a moi** (*"`M4` revient devant `nadia-prof`"*), c'est une obligation sur ma
propre porte, et je peux dire que je ne l'executerai pas et pourquoi. **Je n'amende pas `DEC-0025`** :
elle appartient a `camille-po` et elle reste exacte pour l'etat qu'elle decrivait le 2026-08-13. La
trace lui revient (section 5).

**Ce qui le remplace, parce qu'un declencheur retire sans remplacant est un trou.** `M4` ne peut
revenir dans ma porte que par l'un des deux gestes suivants, et par aucun autre :

- **un arbitrage de l'utilisatrice** qui l'y reinscrit ;
- **un garde-fou executable** qui juge la propriete sans moi.

**Jamais par une mesure de moi, ni par un constat que j'aurais fait en jouant.** C'est exactement la
condition d'extinction que j'ai posee pour `MNC-1` le 2026-08-28, et c'est volontairement la meme :
deux clauses de moi qui gouvernent la meme propriete ne doivent pas avoir deux regimes de retour.

**Et je m'applique ma clause anti-contournement, ici precisement, parce que c'est ici qu'elle est le
plus tentante.** `M4` porte le motif le plus fort que j'aie jamais ecrit sur ce produit - *"si cette
ligne est sous le pli, le jeu devient muet sur l'erreur (...) le motif ne sera pas negociable"*. **Ce
motif ne rentre pas par la lisibilite, ni par la charge cognitive, ni par le traitement de l'erreur,
ni par `M1`, ni par `M6`, ni par `M11`.** Il est sorti. S'il reapparaissait sous un autre nom dans un
motif que j'ecris, c'est moi qu'il faudrait corriger.

---

## 3. Le balayage des treize conditions

**Methode, et pourquoi je balaie plutot que de traiter la seule que `camille-po` a nommee.** C'est
l'obligation que j'ai adoptee le 2026-08-28 : une condition qui gouverne une population enumerable
est mesuree contre cette population entiere au moment ou elle est ecrite, pas seulement testee sur
les cas qui la mettent en defaut. **La population est ici enumerable et petite : treize conditions**,
et le cout du balayage se compte en minutes contre le cout d'un paquet ecrit contre une condition
morte.

Trois passes, dans cet ordre :

1. lecture de l'enonce de chacune des treize, clause par clause ;
2. balayage lexical du bloc de definition (`FICHE-bandit-manchot-additions-cp.md`, section de
   l'arbitrage de mecanique) sur `pli`, `640`, `defiler` ;
3. second balayage sur un autre vocabulaire, pour ne pas dependre du premier : `visible`,
   `hors de vue`, `invisible`, `sans defiler`, `scroll`.

**Resultat des deux balayages lexicaux, et c'est le fait central de ce document** : dans tout le
bloc des treize, les mots `pli`, `640` et `defiler` n'apparaissent **que dans le paragraphe de
`M4`**. Les seules autres occurrences sont dans des paragraphes de motif, pas dans une clause. Le
mot `visible` apparait une fois hors de `M4`, dans `M9` (*"une marque visible"*), au sens de
*perceptible* - ses cinq clauses portent sur le contraste, la boite, la police, la pressabilite et
le fait de ne designer aucune reponse, aucune sur un ecran.

**`M4` etait le seul point par lequel les treize conditions touchaient le pli.**

| Condition | Objet | Etat apres l'arbitrage |
|---|---|---|
| **`M1`** | l'indice porte sur la question ou l'enfant est bloque, et arrive des le premier echec | **intacte** - moment d'apparition et rattachement, aucun ecran |
| **`M2`** | l'indice ne nomme jamais la reponse attendue | **intacte** - contenu |
| **`M3`** | la partie presente le nombre de questions annonce, et ne finit jamais sur un essai faux | **intacte** |
| **`M4`** | le message, l'indice et la revelation entierement au-dessus du pli | **SORTIE DE MA PORTE** |
| **`M5`** | revelation au troisieme echec, et l'enfant fait le geste juste une fois | **intacte** |
| **`M6`** | un seul endroit ou le jeu parle de la reponse ; le meuble rigoureusement identique | **intacte** - unicite d'emplacement et invariance du meuble, aucune barre d'ecran |
| **`M7`** | l'explication n'arrive qu'apres que la reponse est acquise, jamais au-dessus d'une question neuve | **intacte** |
| **`M8`** | rien ne bouge au-dessus du point ou l'enfant appuie | **intacte, et son poids augmente** - voir ci-dessous |
| **`M9`** | la proposition deja essayee porte une marque qui ne nomme aucune reponse (cinq clauses) | **intacte**, y compris le plancher tactile de 44 px |
| **`M10`** | aucun mouvement de colonne sur une question inchangee | **intacte** |
| **`M11`** | le chemin juste garde un signal immediat, meme endroit et meme force que le chemin faux | **intacte** - voir la precision ci-dessous |
| **`M12`** | les textes exacts, francais integralement accentue | **intacte** - contenu |
| **`M13`** | apres une reponse, le focus va sur le jeu et jamais sur la sortie (cinq clauses) | **intacte**, clause 1 bloquante et **tenue depuis le 2026-08-14** |

**Deux precisions que le tableau ne porte pas et qui comptent.**

**`M8` devient plus importante, pas moins.** Elle interdit que quoi que ce soit bouge au-dessus du
doigt de l'enfant, et impose que tout ce que le jeu ajoute apparaisse **sous** le meuble. Tant que
`M4` existait, elle avait un contrepoids : ce qui descend doit rester au-dessus du pli. **Ce
contrepoids a disparu, et `M8` reste seule.** Elle est aujourd'hui la seule chose qui empeche de
"regler" un depassement vertical en poussant les propositions - c'est-a-dire en fabriquant, sous le
doigt de l'enfant, le defaut que j'ai refuse le plus fermement de tout ce produit. **Je ne la
durcis pas et je ne lui ajoute rien** : je dis qu'elle porte desormais seule une charge qu'elle
partageait.

**`M11` n'est pas touchee, et c'est contre-intuitif, donc je le motive.** Elle exige un signal de
reussite *"immediat, au meme endroit et de meme force que le chemin faux"*. Aucune de ses quatre
clauses ne dit *au-dessus du pli* : elles disent le meme emplacement que `M6`, la meme geometrie de
boite que le signal d'echec, l'ecran qui suit immediatement l'appui, et qu'une question revelee
n'est pas une reussite. **Ce que l'arbitrage retire n'est pas `M11`, c'est ma capacite a refuser
quand ce signal, correctement place et correctement date, se trouve sous le pli.** La condition
tient, sa consequence visuelle n'est plus garantie - et c'est exactement ce que `MNC-1` inscrit
desormais sur chaque Done.

---

## 4. Au-dela des treize - `M14` a `M21`, balayees parce que ma propre regle l'exige

**Ce balayage n'a pas ete demande et je le fais quand meme.** Les fiches neuves ne citent que
`M1` a `M13`, mais `M14` a `M21` sont des conditions de mecanique de moi, en vigueur, et ma regle du
2026-08-28 dit de mesurer contre **la population entiere** qu'une clause gouverne. Ne balayer que la
partie citee serait refaire le defaut de `SEG-1` : une clause fausse qui survit parce que personne ne
tire le cas qui la revele.

| Condition | Etat apres l'arbitrage |
|---|---|
| **`M14`** (l'appui a cote apres la revelation, cinq clauses) | **intacte** |
| **`M15`** (l'indice se lit comme une partie de la question, un seul indice a la fois) | **intacte** |
| **`M16`** (l'ampoule presente des le premier rendu, et marquee des le premier echec) | **intacte** |
| **`M17`** clause 1 | **SE SCINDE** - voir ci-dessous |
| **`M17`** clause 2 (sur l'ecran de revelation, l'indice s'affiche sans etre demande) | **intacte** |
| **`M18`** (l'ampoule ne se paie pas avec les pixels du message) | **SORT DE MA PORTE** |
| **`M19`** (l'ampoule porte le mot ecrit "Aide") | **intacte** |
| **`M20`** (l'ampoule hors du cadre, jamais une reponse ; plancher de 20 px de texte et 44 px de cible) | **intacte** |
| **`M21`** (pas d'ampoule la ou il n'y a pas d'indice utilisable) | **intacte** |

**`M18` sort.** Son objet entier est la non-regression verticale - *"le bas du cabinet ne descend sur
aucun jeu"* -, sa verification est une mesure de hauteurs en pixels, et son enjeu declare est le pli
(*"il ne reste que 5 px entre le bas du cabinet et le pli"*). C'est la meme famille que `M4`, sur un
autre element. Elle sort pour le meme motif, sans discussion.

**`M17` clause 1 se scinde, et c'est le seul endroit du balayage ou je coupe une clause en deux.**
Elle porte deux interdictions dans la meme phrase :

- *"l'indice ouvert est entierement au-dessus du pli (`y = 640`)"* - **tombe**, meme motif que `M4` ;
- *"je refuse (...) un indice qui s'ouvre en recouvrant les propositions - un indice sert a etre lu
  **en regardant les choix**, pas a la place d'eux"* - **tient**.

**Pourquoi la seconde moitie n'est pas un contournement, et je m'oblige a le justifier plutot qu'a
l'affirmer.** Un recouvrement n'est pas un hors-de-vue. **Faire defiler la page repare un pli ; cela
ne repare pas un calque pose par-dessus.** Ce sont deux defauts differents, avec deux remedes
differents et deux instruments differents - le depot porte deja les deux, separement
(`squad/outils/nadia-prof/lire-le-pli-a-l-ecran.py` et `lire-le-recouvrement-a-l-ecran.py`), et cette
separation est anterieure a l'arbitrage, donc elle n'a pas ete fabriquee pour lui. L'arbitrage du
2026-08-28 nomme *"hors de vue sans faire defiler la page"* ; il ne nomme pas le recouvrement, et je
ne l'y lis pas.

**Consequence sur le statut de `M17`** : elle avait ete declaree *"clause bloquante, et c'est la
seule de ce bloc"*. **Elle reste bloquante**, par sa moitie survivante et par sa clause 2. Ce qui
change est ce qu'il faut mesurer pour la tenir : le recouvrement, plus jamais le pli.

---

## 5. Ce que je route, et qui n'est pas de moi

Je ne touche aucune fiche. **Sans propagation, ce document ne repare rien** : dix-neuf fiches
continueront d'annoncer une condition bloquante qui n'existe plus, et c'est precisement le defaut
que ce lot constate.

| Suite | Destinataire | Ce que je demande |
|---|---|---|
| **L'en-tete de `FICHE-bandit-manchot-additions-cp.md`** annonce *"dont deux bloquantes : `M4` et la clause 1 de `M13`"*. **Faux deux fois** (section 1) | `camille-po` | Corriger en : `M4` sortie de la porte pedagogique le 2026-08-28 ; clause 1 de `M13` bloquante et tenue depuis le 2026-08-14. **Sans effacer le texte d'origine de `M4`** : une note datee a cote, comme le depot le pratique |
| **Huit fiches recopient la formule au present** (`additions-cp`, `comparer-nombres-cp`, `complements-10-cp`, `conjugaison-present-cm1`, `homophones-cm1`, `le-bon-son-cp`, `problemes-additifs-cp`, `soustractions-cp`) et **quatre au passe** (`conversions-unites-cm1`, `grands-nombres-cm1`, `perimetre-cm1`, `tables-multiplication-cm1`) | `camille-po` | Un renvoi date vers ce document a chaque site de citation. **Je ne demande pas de reecrire les dix-neuf** : un renvoi suffit et coute moins qu'une reecriture qui se re-perimera |
| **`DEC-0025` point 4, declencheur de reouverture de `M4`** : je le declare sans objet a compter du 2026-08-28 (section 2) | `camille-po` | Le tracer. **Je n'amende pas `DEC-0025`** : elle reste exacte pour l'etat du 2026-08-13, et son point 4 n'est pas efface, il est date comme sans objet |
| **`M18` sort ; `M17` clause 1 se scinde et `M17` reste bloquante par sa moitie survivante** (section 4) | `camille-po`, `theo-archi` | A porter partout ou `M16` a `M21` sont citees. `M17` se mesure desormais au recouvrement, plus jamais au pli |
| **`M8` porte seule une charge qu'elle partageait avec `M4`** (section 3) | `theo-archi`, `sofia-sm`, `marc-dev`, `lea-dev` | A savoir avant de corriger un depassement vertical : la solution qui consiste a pousser les propositions vers le bas reste interdite par `M8`, et `M4` ne la contrepese plus |
| **Les trois paquets neufs de s22 citeront ce bloc** | `sofia-sm` | Que les auteurs lisent cette page avant de citer *"`M1` a `M13`, dont deux bloquantes"* |

---

## 6. Ce que ce document ne fait pas

- **Il ne rouvre aucun Done prononce avant le 2026-08-28.** `cm1-perimetre` et `cm1-reperes` sont
  dans ce cas et ne sont pas touches, conformement a ce qui m'a ete demande et a ma propre pratique :
  un verdict garde sa date et son motif.
- **Il ne prononce aucun Done pedagogique** et n'en anticipe aucun.
- **Il ne modifie aucune fiche de conformite, aucune `DEC`, aucun contenu, aucun fichier de code.**
- **Il ne durcit aucune condition.** Sept conditions sortent ou se reduisent (`M4` en entier, `M18`
  en entier, la moitie de pli de `M17` clause 1) ; aucune ne gagne une exigence. `M8` et `M17` ne
  sont pas durcies : je dis ce qu'elles portent desormais, je ne leur ajoute rien.
- **Il ne cree aucune story.** Je produis des motifs ; `camille-po` en fait du backlog.
