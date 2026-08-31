# DEC-0065 - Perimetre du sprint 25 : objectif de couverture maximale, la reserve arbitree ne vaut qu'une ligne, deux moities textuelles posees a `nadia-prof` comme seule voie d'elargissement

- **Statut** : Proposee
- **Date** : 2026-08-31
- **Auteur** : `camille-po`
- **Sprint** : s25 (ouverture)
- **Arbre de reference** : worktree `s25-camille`, ouvert sur `round-two` a `130ba64`
- **Remplace** : rien. **Amende** : rien.
- **Document qui la fonde** :
  `squad/programme/INSTRUCTION-s25-les-16-lignes-hors-couvert-valide.md` (instruction ligne par
  ligne des 16 lignes hors `couvert-valide`, ecrite avant cette decision et non apres)

## Contexte

L'utilisatrice demande s25 avec un objectif unique et explicite, dans ses mots : **"planifie s25
pour avoir la plus grande couverture"**.

Cette decision dit ce que cet objectif permet reellement d'atteindre, ce qu'il coute, et le conflit
qu'il ouvre avec le rang que la squad a range en tete a la cloture de s24. **Elle ne resout pas ce
conflit** : elle l'ecrit, chiffre les deux lectures, et le route.

---

## 1. Les trois chiffres de depart, verifies par moi

| Chiffre | Verdict | Methode et perimetre |
|---|---|---|
| Couverture **27/43 = 62,8 %** | **exact** | Comptage automatise des quatre tableaux de matiere de `squad/programme/MATRICE-couverture.md`, colonne `Statut` : 27 `couvert-valide`, 15 `non couvert`, 1 `en cours`, 4 `non couvrable en l'etat`, total 47. Denominateur 47 - 4 = 43. **Aucun module compile, la question du `-am` ne se pose pas** |
| **16 lignes** hors `couvert-valide` (15 + 1) | **exact** | Meme comptage |
| Catalogue a **31 jeux** | **exact** | `grep -c '^  - id:' adapters/adapter-content/src/main/resources/catalogue.yaml` = 31, a comparer aux 31 fichiers de `adapters/adapter-content/src/main/resources/contenu/` : **egalite**, aucun ecart dans un sens ni dans l'autre |

**Le chiffre de 926 tests ne figure pas dans ce tableau** parce que je ne l'ai pas reproduit :
aucune decision de ce document n'en depend, et un chiffre recopie sans methode se recopie ensuite
avec l'autorite de celui qui l'a mesure. Il est a `sofia-sm` et a la CI de s24.

---

## 2. Le fait central de s25, et il n'est pas contournable par un choix de perimetre

**L'inventaire de la reserve qui m'a ete transmis est exact, et je l'ai reverifie plutot que de le
croire** - `NAR`, `PLU` et `FRA` consommees a s24 ; restent `SUB` et `DEN` ; `DEN` ne rapporte aucune
ligne par `DEN-2`.

> **La reserve de paquets arbitres contient deux entrees. Une seule rapporte une ligne.**
>
> **Le plafond de s25 sans ouvrir de famille neuve est 28/43 (65,1 %).**

**Et le chiffre qui donne a ce fait sa vraie portee** : `DEC-0053`, section 7.1, que j'ai ecrite
moi-meme le 2026-08-28, calculait le *"plafond realiste a court terme, ecartant l'audio et le
visuel"* a **27/43 (62,8 %)**. **Le produit y est aujourd'hui, au dixieme de point.** Des 9 lignes
de contenu seul qui composaient ce plafond, 8 sont vertes et `SUB` est la neuvieme.

**Ce n'est pas une surprise, c'est une echeance qui arrive a la date prevue.** Le mur de la
mecanique unique, pose a l'utilisatrice par `DEC-0057` le 2026-08-30, est atteint. s25 est le
premier sprint qui se tient dessus.

---

## 3. Le perimetre - cinq lots, ranges par ce que l'objectif de l'utilisatrice designe

**Ce rang est le mien. L'affectation, la capacite et le decoupage sont a `sofia-sm` ; tout arbitrage
prealable de contenu est a `nadia-prof` ; toute forme technique est a `theo-archi`.**

| Rang | Lot | Story | Lignes gagnees | Etat d'instruction |
|---|---|---|---|---|
| 1 | **`SUB`** - reperer les substituts d'un personnage (francais CM1) | `S25-SUB-1` | **+1** | **Complet.** `SUB-1` a `SUB-10`, rendues le 2026-08-30. **Rien a attendre** |
| 2 | **`SCO`** - le score cesse d'etre parfait par elimination | `S25-SCO-1` | 0 | Bloque par deux instructions dans l'ordre : le resultat attendu (`nadia-prof`), puis la forme et l'`ADR` (`theo-archi`) |
| 3 | **`MON`** - moitie *"utiliser"* de la monnaie (maths CP) | `S25-MON-1` | **+1 si et seulement si** section 5 | **Aucun arbitrage.** Non developpable en l'etat |
| 4 | **`JOU`** - moitie *"se reperer dans la journee, la semaine"* (maths CP) | `S25-JOU-1` | **+1 si et seulement si** section 5 | **Aucun arbitrage.** Non developpable en l'etat |
| 5 | **`DEN`** - lire, ecrire, decomposer jusqu'a 100 (maths CP) | `S25-DEN-1` | **0**, par `DEN-2` | **Complet** depuis le 2026-08-24 |

**Trois choses que ce tableau dit et qu'il ne faut pas adoucir :**

- **Un seul lot du perimetre garantit une ligne, et c'est le lot 1.** Les lots 3 et 4 dependent
  d'une reponse que je n'ai pas ; les lots 2 et 5 n'en rapportent aucune.
- **Le lot 5 est dernier et c'est l'objectif de l'utilisatrice qui l'y met**, pas un jugement sur sa
  valeur. `nadia-prof` ecrit que `DEN` couvre *"le trou le plus visible du CP mathematique"* et
  qu'*"un compteur qui affiche zero sur ce paquet decrit une convention de matrice, pas ce que
  l'enfant gagne"*. **Je verse sa phrase entiere plutot que le rang seul**, et je note que dans un
  sprint dont l'objectif serait la valeur pour l'enfant et non la couverture, ce lot serait deuxieme
  ou troisieme.
- **Ce perimetre n'est pas tenable en entier par la porte pedagogique.** Section 6.

### 3.1 `SUB` prend le quota `COMP-2` de s25, et cela ferme une story

`nadia-prof` n'accepte **qu'un seul paquet portant une propriete du type de `COMP-2` par sprint**.
`SUB-6` en est une (masquer le texte support, item par item, sur la totalite du paquet). `SUB` prend
donc le quota, et **`S25-NAR-1` ne peut pas etre prise a s25** : une reecriture du paquet narratif
rouvre `NAR-6`, qui est du meme type.

**`S25-NAR-1` est donc reportee. C'est son premier report, et son motif est ecrit ici :** le quota
`COMP-2` est unique, `SUB` rapporte une ligne la ou `S25-NAR-1` n'en rapporte aucune (la ligne est
verte depuis le 2026-08-31), et `nadia-prof` a ecrit de cette story *"je ne le demande pas et je ne
le bloque pas"*. **Si elle est reportee une seconde fois a s26, ce paragraphe-ci est ce qu'il faudra
relire, et le motif devra etre neuf.**

### 3.2 `SUB` n'est pas reportee, et le probleme que `DEC-0059` m'avait laisse est clos

`DEC-0059`, section 2, avait reporte `SUB` en comptant ce report comme le premier, sur un motif
unique : la regle de composition, `NAR` ayant ete preferee. J'ai ecrit a la cloture de s24 que **ce
motif etait consomme et qu'un second report devrait porter un motif neuf**.

**Il n'y a pas de second report : `SUB` est au rang 1.** La question tombe par la voie qui la ferme
plutot que par une justification.

**Et j'ecris d'avance ce qui ne pourra pas servir de motif si `sofia-sm` ne peut pas la loger** : la
composition `COMP-2` n'est plus disponible comme motif (aucun autre paquet de ce type n'est au
perimetre), et le desequilibre francais CP contre francais CM1 s'est reduit de 18,2 points en un
sprint. **Il resterait la capacite de developpement, qui est un motif de `sofia-sm`, et il devrait
etre ecrit par elle, pas par moi.**

---

## 4. Le conflit que je n'arbitre pas : l'objectif de l'utilisatrice et le rang de la squad ne pointent pas au meme endroit

**Le fait, dit sans le maquiller** : les trois postes que la squad a ranges en tete a la cloture de
s24 **ne rapportent aucune ligne de couverture**.

| Poste | Proprietaire | Lignes | Ce qu'il est |
|---|---|---|---|
| Le score parfait par elimination (`DEC-0061`, `S25-SCO-1`) | `nadia-prof` puis `theo-archi`, puis `sofia-sm` | **0** | Sur 94,6 % des items du catalogue, un enfant qui ne sait rien finit la partie avec un score parfait. **Un jeu sur trente et un permet d'echouer** |
| Le gestionnaire d'evenement inline invisible du garde-fou (`yanis-qa`, rapport qualite de s24, routage 1) | `theo-archi` (`ADR-0008`) | **0** | **La porte de la dette n. 4 de `CLAUDE.md`**, grande ouverte : un `onclick` sur un bouton de proposition ne fait rougir aucune des 923 classes de tests. Rien n'est casse aujourd'hui, la voie est libre |
| Le cliquet typographique qui ne voit pas les gabarits (`relecteur-pedagogique`, s24, section 6.1) | `theo-archi` (`ADR-0067`) | **0** | Le cliquet derive son perimetre des paquets. **11 occurrences dans les gabarits et les constantes de message, dont 3 neuves a s24**, entrees sous une porte verte |

### 4.1 Ce que chaque lecture coute

**Lecture "couverture maximale d'abord"** - s25 prend les lots 1, 3, 4, 5 et laisse les trois postes.

- **Ce qu'elle gagne** : de 1 a 3 lignes, soit 28 a 30 sur 43 (65,1 % a 69,8 %).
- **Ce qu'elle coute, poste par poste** :
  - Sur le score : **deuxieme report de `DEC-0061`**, sur un motif qui devra etre neuf - et deux des
    motifs disponibles a s24 sont deja tombes (voir 4.2). Surtout, **c'est le seul poste de ce
    backlog dont le prix augmente mecaniquement a chaque sprint d'attente** : chaque jeu neuf est un
    jeu de plus dans la partition a rejouer le jour de la correction. s25 ajouterait 1 a 3 jeux.
  - Sur la dette n. 4 : la porte reste ouverte pendant un sprint de plus. **Le risque n'est pas
    theorique de facon symetrique** - il ne se realise que si quelqu'un ecrit du JavaScript inline,
    ce que personne n'a fait a ce jour. Mais le jour ou cela arrive, **rien ne le voit**, et c'est
    la definition d'un faux vert que ce depot a deja sanctionnee plusieurs fois.
  - Sur le cliquet : la croissance continue **exactement la ou le cliquet ne peut pas la voir**.
    3 orphelins neufs en un sprint est un taux, pas un incident.
- **Et le cout que je dois signaler contre cette lecture-la, parce qu'elle est celle que l'objectif
  designe** : elle vide la reserve arbitree a zero. **A la cloture de s25, il ne restera aucune
  ligne developpable sans famille neuve**, et s26 s'ouvrira sur le mur, sans marge.

**Lecture "rang de la squad d'abord"** - s25 prend les trois postes et ce que la capacite laisse.

- **Ce qu'elle gagne** : un score qui veut dire quelque chose, deux garde-fous qui gardent ce que
  leur nom dit.
- **Ce qu'elle coute** : la couverture stagne a 27 ou 28 sur 43 dans le sprint ou l'utilisatrice a
  demande le maximum ; et la reserve reste vide, donc s26 affronte le meme mur avec un sprint
  d'arbitrages de retard en plus.

### 4.2 Le fait qui rend le conflit moins large qu'il n'en a l'air - et c'est un fait, pas un arbitrage

**Le cout de `S25-SCO-1` a la porte pedagogique a ete chiffre par `nadia-prof` le 2026-08-31, et il
est beaucoup plus bas que ce que j'avais ecrit.**

- J'avais decrit l'issue 1 de `DEC-0060` comme *"la correction s'etale sur cinq a sept sprints
  d'audit"*.
- Elle a etabli le regime **R-B**, audit de mecanique : la propriete changee est distinguee par le
  nombre de propositions d'un item, croise avec le mode de production et le moment de l'indice ;
  **cinq cellules, couvertes par trois jeux**, aucun des 24 autres ne presentant un cas que ces
  trois ne presentent pas. **Cout : trois jeux, hors compte de paquets.** Sa phrase :
  *"il n'est pas de cinq a sept sprints, il est d'un sprint"*.
- **Ce qui compte dans sa borne** : le texte que l'enfant lit apres la correction, s'il change.
  *"Il est commun aux 27, donc c'est un R-A, pas 27."* **Un paquet, au plus.**

> **Consequence arithmetique, et je la donne comme une arithmetique et non comme une
> recommandation** : la borne de `nadia-prof` etant de 4 a 5 paquets, `S25-SCO-1` en consomme **au
> plus 1**, ce qui laisse **3 a 4 paquets de contenu**. Le perimetre de la section 3 en demande 4
> (`SUB`, `MON`, `JOU`, `DEN`). **Les deux lectures ne sont pas exclusives a la borne de la porte
> pedagogique** ; elles peuvent l'etre a la capacite de developpement, qui est a `sofia-sm`, et a la
> charge d'arbitrage prealable, qui n'est pas pricee (section 6).

**Deux motifs de report de `DEC-0061` sont tombes le 2026-08-31 et ne peuvent plus resservir** - je
les nomme pour que le motif du second report, s'il a lieu, ne les reprenne pas :

- *"le cout de la correction est inconnu"* : il est chiffre, trois jeux plus au plus un paquet.
- *"la file de reaudit des 27 est ouverte de toute facon"* : elle est **fermee**, `nadia-prof` ayant
  discharge les lots de s24 sous R-C. **Ce motif-la jouait en faveur de la correction et il tombe :
  je le signale contre mon propre rang, comme a s24.**

**Ce que je ne fais pas ici** : je ne dis pas laquelle des deux lectures l'emporte. L'objectif est
de l'utilisatrice, la capacite est de `sofia-sm`, et deux des trois postes sont a `theo-archi`.

---

## 5. Ce que je ne tranche pas, et a qui je le pose

### 5.1 A `nadia-prof` - la question qui decide le plafond de s25

**Une moitie textuelle d'une ligne classee "famille visuelle" peut-elle porter cette ligne a
`couvert-valide` avec une mention de couverture partielle nommee, ou reste-t-elle `en cours` comme
`DEN-2` l'impose ?**

Le fait qui la fonde est verifie dans le depot, pas suppose :

- **Monnaie (maths CP)** - la moitie *"utiliser [...] dans des problemes concrets"* est servable
  sans aucune image. `contenu-repris/quiz/cp-monnaie.yml` porte 8 items de ce genre, et j'avais
  etabli ce fait des `DEC-0019` : *"il ne travaille que 'utiliser'"*. Seule la moitie *"reconnaitre"*
  demande de voir une piece.
- **Heure (maths CP)** - le referentiel separe lui-meme la ligne par un point-virgule :
  *"Se reperer dans la journee, la semaine ; lire l'heure pleine sur une horloge"*. La premiere
  moitie est servable sans image ; `contenu-repris/quiz/cp-durees.yml` porte 11 items dont 7 dessus.

**Deux precedents de votre main s'opposent, et je ne peux pas choisir entre eux** : `FRA-9` et
`LIA-10` ont fait passer une ligne au vert avec une mention que vous avez dictee ; `DEN-2` maintient
`en cours`, *"non negociable"*, et vous l'avez reconfirmee le 2026-08-30. **Le critere qui les
separe est pedagogique et il est a vous.**

**Trois demandes distinctes, dans cet ordre** :

1. **Le critere** : qu'est-ce qui fait qu'une moitie porte sa ligne, et qu'une autre ne la porte
   pas ? Une reponse de principe vaut mieux qu'une reponse par cas, parce qu'elle servira aux lignes
   suivantes.
2. **Les deux arbitrages prealables**, si le critere les autorise : conditions ecrites pour etre
   reprises telles quelles en criteres de story, comme `SUB-1` a `SUB-10`.
3. **La mention exacte** que la matrice devra porter le jour ou l'une de ces lignes bougerait, dans
   vos mots. **Je ne l'ecrirai pas a votre place** : `NAR-11` a montre en s24 qu'une reformulation
   par moi d'un motif de votre main trahit ce qu'il vise.

**Sans reponse a 1, les lots 3 et 4 sortent du perimetre et le plafond de s25 est 28/43.** Sans
reponse a 2, ils y restent mais ne sont pas developpables : **une story qui citerait une plage de
conditions au lieu de les recopier est exactement le defaut de `CPL-3`, et je ne le referai pas.**

### 5.2 A `nadia-prof` - le resultat attendu de `S25-SCO-1`, du depuis le 2026-08-31

**Que doit pouvoir faire un enfant d'une manche quand le nombre de propositions rend l'elimination
possible, et que doit vouloir dire "trois essais" dans ce cas ?** `DEC-0061`, section 5, point 1.
**C'est la premiere des deux instructions bloquantes, et `theo-archi` ne peut pas commencer avant.**

### 5.3 A `theo-archi`, par `sofia-sm` - deux demandes, aucune decision de ma part

- **La forme de la correction de `M5` et son `ADR`**, apres 5.2 et jamais avant (`DEC-0061`,
  section 5, point 2). **Une exigence de `nadia-prof` s'y ajoute et elle n'est pas de forme** : sa
  partition R-B n'est valide que si l'`ADR` **ecrit que la propriete changee ne depend d'aucune
  autre variable que celles qu'elle a partitionnees**. *"Sans elle, ma partition est un echantillon
  qui s'ignore, et je ne l'emploierai pas."*
- **Un ordre de grandeur pour l'architecture d'un item visuel** - comment un item porte une donnee
  de dessin. **Un ordre de grandeur, pas une conception**, et rien ne l'a jamais demande
  formellement. Sans lui, aucune des 4 lignes visuelles n'est planifiable a un horizon connu.

### 5.4 A l'utilisatrice, par `sofia-sm` - trois questions, dont deux ouvertes depuis longtemps

- **La source de la voix.** Elle bloque **4 lignes** - localiser une syllabe, identifier un son,
  decoder par assemblage, la portion digraphes des graphemes - *"quel que soit le nombre de
  sprints"*. Aucune capacite de developpement n'y change quoi que ce soit : il faut un enregistrement
  de voix humaine par syllabe et par phoneme, et *"aucun agent de cette squad ne peut le produire"*.
  Une synthese vocale est une alternative, que `nadia-prof` rejugerait. **Cette question coute une
  reponse, pas un sprint, et elle n'a jamais ete posee frontalement.**
- **L'ordre entre l'audio et le visuel.** `nadia-prof` a rendu le 2026-08-15 un ordre explicite -
  *"audio, puis visuel, puis saisie"* - motive sur le fait que la conscience phonologique est la
  competence la plus predictive de la lecture et que *"ces trois lignes sont les seules qui ne
  peuvent recevoir aucun contenu, pas meme un morceau"*. La saisie a ete construite en premier, sur
  votre arbitrage rendu en connaissance du cout. **Ouvrir le visuel ensuite mettrait l'audio en
  troisieme position sur trois apres l'avoir classe premier. Qu'il soit choisi, pas subi.**
- **Le statut de quatre lignes qui ne bougeront jamais** : copier un mot (`R1`, graphomotrice),
  ecrire un mot en autonomie (`R6`, l'appareil produit les lettres), restituer l'essentiel et
  rediger un texte (production libre, jugement humain). Elles sont `non couvert` alors qu'aucun
  sprint ne les atteindra. **Les reclasser ferait passer le denominateur de 43 a 39 et la couverture
  affichee de 62,8 % a 69,2 % sans ecrire une ligne de code. C'est precisement pourquoi je ne le
  fais pas moi-meme dans le sprint ou on me demande de maximiser cet indicateur**, et pourquoi le
  gain d'affichage est ecrit dans la question plutot que decouvert apres.

---

## 6. La capacite - ce que la borne autorise reellement, et ce que je ne promets pas

**La borne, dans les mots de `nadia-prof`, reconfirmee le 2026-08-30** : *"quatre a cinq paquets de
contenu par sprint, sur une mecanique existante"*, l'audit d'une mecanique neuve etant **hors
compte**. A la cloture de s24 elle a ecrit : *"Quatre paquets, plus un audit de mecanique neuve. Je
suis a ma borne, pas en dessous."*

**Ce que le perimetre de la section 3 lui demande, en son unite a elle :**

| Objet | Regime | Compte contre sa borne |
|---|---|---|
| `SUB` | R-A, type `COMP-2` | **1** - et c'est le paquet le plus cher du lot : elle a ecrit que son audit lui couterait *"le plus cher apres `NAR`"*, et que `SUB-4`, `SUB-5` et `SUB-6` restent dans sa seule main, item par item |
| `MON` | R-A | 1 |
| `JOU` | R-A | 1 |
| `DEN` | R-A | 1 |
| `S25-SCO-1` - la partition de mecanique | **R-B** | **0** - hors compte |
| `S25-SCO-1` - le texte de retour, s'il change | R-A | **0 ou 1** |
| **Total** | | **4 a 5** |

**Le perimetre entier tient dans la borne. Et je ne le presente pas comme tenable pour autant, pour
deux raisons qu'aucun de ces chiffres ne porte :**

1. **Deux arbitrages prealables neufs (`MON`, `JOU`) plus un critere de principe plus le resultat
   attendu de `S25-SCO-1` ne sont pas comptes dans cette borne.** Elle compte des **paquets**.
   L'arbitrage de s23 - huit candidats - lui a pris un document de 88 kilo-octets, et il a ete rendu
   dans un sprint ou elle auditait par ailleurs. **Je nomme ce cout plutot que de le decouvrir en
   fin de sprint, exactement comme elle a nomme le 2026-08-30 le cout d'audit d'une mecanique neuve
   que sa borne n'avait jamais price.**
2. **Elle a ecrit qu'elle etait a sa borne, pas en dessous, sur un sprint de quatre paquets.** Le
   maximum que l'utilisatrice demande est borne par un audit humain qui ne se comprime pas.

> **Ce que la borne autorise reellement, et je n'irai pas au-dela : quatre paquets de contenu au
> plus, dont un seul du type `COMP-2`. Si les deux arbitrages neufs de la section 5.1 doivent etre
> rendus dans le meme sprint, la question de savoir si quatre paquets restent tenables est a
> `nadia-prof` et je la lui pose avec le reste.**

**Ordre de sacrifice en cas de conflit de capacite, et il est de moi parce qu'il traduit l'objectif
de l'utilisatrice** : `DEN` sort en premier (zero ligne), puis `JOU`, puis `MON`. **`SUB` ne sort
pas** : c'est la seule ligne garantie du sprint, et sa sortie serait un second report dont le motif
devrait etre ecrit par `sofia-sm` et non par moi.

---

## 7. La file de reaudit de `DEC-0063` - premier arbitrage de rang, rendu ici plutot que redecouvert

`DEC-0063`, section 6, point 2, ecrivait : *"Elle est comptee a partir d'aujourd'hui : ouverte a
s24, le 2026-08-31, **premier arbitrage de rang du au planning de s25**."* **Je le rends.**

**Verdict : la file est sans objet pour les 27 jeux, et ce n'est pas moi qui l'ai videe.**
`nadia-prof` a discharge les lots de s24 sous R-C le 2026-08-31 - *"Les 27 jeux existants ne sont
pas rejoues, et le chemin C est ecarte pour s24"* - et l'amendement de `DEC-0063` l'a acte le jour
meme. **Aucun reaudit n'est du. La file n'est pas reportee : elle n'a plus d'objet.**

**Ce qui reste du, et qui est autre chose** : la **capture de rendu de reference**, chiffree par
`theo-archi` et **non financee**. `nadia-prof` : *"reste la seule voie pour un lot qui modifie un
corps qui s'execute"*. Elle devient due **le jour ou un lot change du code partage et pretend un
rendu invariant** - c'est le regime R-C, et la section 4 de sa clause dit ce qu'une telle mesure doit
contenir pour qu'elle s'y appuie.

**Un point de sequencement qui evite une erreur de lecture, et il joue en faveur du lot 2** :
`S25-SCO-1` **ne demande pas cette capture**. Il change le comportement que l'enfant recoit, donc il
releve de **R-B** - la partition de trois jeux - et non de R-C. **Croire que la capture bloque
`S25-SCO-1` serait une erreur, et je l'ecris avant que quelqu'un ne la fasse.**

**Rang que je donne a la capture : nomme, non engage a s25, proprietaire technique.** Elle ne
rapporte aucune ligne et aucun lot du perimetre ne la reclame. **Si elle n'est toujours ni engagee
ni arbitree a l'ouverture de s26, c'est un report et il devra etre ecrit comme tel.**

---

## 8. Ce que je route

| Suite | Destinataire | Ce que je demande |
|---|---|---|
| Le critere de couverture partielle nommee, puis les arbitrages de `MON` et `JOU` (5.1) | `nadia-prof`, par `sofia-sm` | **Avant toute affectation.** C'est la question qui decide le plafond du sprint |
| Le resultat attendu de `S25-SCO-1` (5.2) | `nadia-prof`, par `sofia-sm` | Premiere des deux instructions bloquantes, du depuis le 2026-08-31 |
| La tenabilite de quatre paquets a cote de deux arbitrages neufs (section 6) | `nadia-prof` | Qu'elle le dise a l'ouverture, pas en fin de sprint |
| L'`ADR` de `M5`, avec la clause d'independance des variables qu'exige la partition R-B (5.3) | `theo-archi`, par `sofia-sm` | Apres 5.2 et jamais avant |
| L'ordre de grandeur d'une architecture d'item visuel (5.3) | `theo-archi`, par `sofia-sm` | Un ordre de grandeur, pas une conception |
| Le JavaScript inline invisible d'`ADR-0008` et le cliquet d'`ADR-0067` (section 4) | `theo-archi`, par `sofia-sm` | Je les range, je ne les tranche pas : ils sont a lui |
| Le conflit objectif contre rang (section 4), avec ses deux couts chiffres | `sofia-sm`, puis l'utilisatrice | Un choix, pas un defaut de planning |
| La source de la voix, l'ordre audio contre visuel, le statut des quatre lignes (5.4) | l'utilisatrice, par `sofia-sm` | Qu'aucune des trois ne soit reportee en silence |
| `S25-NAR-1` reportee - **premier report, s25** (3.1) | moi-meme, a relire a l'ouverture de s26 | Un second report demandera un motif neuf, ecrit a cote de celui-ci |
| `DEC-0061` : si `S25-SCO-1` n'est pas engagee, **second report** | moi-meme, a la cloture | Deux motifs sont tombes le 2026-08-31 et ne peuvent plus servir (4.2) |

---

## 9. Ce que cette decision ne fait pas

- Elle **ne fait bouger aucune ligne** de `squad/programme/MATRICE-couverture.md` et n'en retire
  aucune. Une ligne ne passe au vert que par un verdict de `nadia-prof`.
- Elle **ne prononce aucun Done pedagogique** et n'en anticipe aucun. Les plafonds de la section 2 et
  de l'instruction sont des plafonds, pas des previsions : ils supposent qu'un paquet ecrit dans le
  sprint passe la porte du premier coup, ce qui n'est jamais arrive sur ce produit.
- Elle **ne reclasse aucune ligne** en `non couvrable en l'etat`, alors que la section 5.4 en propose
  quatre : le gain d'affichage serait le mien, la decision ne l'est pas.
- Elle **ne tranche rien de technique** : ni la forme de la correction de `M5`, ni le perimetre d'un
  garde-fou, ni la maniere dont un item porterait une donnee de dessin.
- Elle **n'arbitre pas le conflit de la section 4** et ne le presente pas comme resolu par
  l'arithmetique de 4.2 : celle-ci montre que la borne de la porte pedagogique ne les oppose pas ;
  la capacite de developpement peut les opposer, et elle n'est pas de moi.
- Elle **ne conteste aucun refus pedagogique** et n'en reouvre aucun.
