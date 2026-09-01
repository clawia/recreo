# DEC-0067 - Etat du produit a la cloture de s25 : la reserve retombe a une seule ligne, et rang propose pour s26

- **Statut** : Acceptee, **amendee le 2026-09-01** : la reserve passe d'une ligne a cinq apres l'arbitrage de l'utilisatrice sur la voix et le verdict de `nadia-prof` sur les cinq lignes ; plafond recompte a 35/41 = 85,4 %, rang de s26 revise. Voir l'amendement en fin de document.
- **Date** : 2026-09-01
- **Auteur** : `camille-po`
- **Sprint** : s25 (cloture)
- **Arbre de reference** : worktree `s25-camille`, sur l'arbre de cloture `834c6bc` augmente des
  verdicts de `nadia-prof` du 2026-09-01
- **Ce que ce document fait** : il constate ce que les quatre verdicts laissent, solde les deux
  reports que je portais, et propose un rang pour s26. **Il ne fixe aucun perimetre** : le perimetre
  de s26 s'ecrira a son ouverture, apres l'arbitrage prealable de `nadia-prof` et la capacite de
  `sofia-sm`.

---

## 1. Ce que le sprint a produit, et les deux chiffres qu'il ne faut pas additionner

**Recompte par moi, methode nommee** : comptage automatise des quatre tableaux de matiere de
`squad/programme/MATRICE-couverture.md`, colonne `Statut`, sur ce seul fichier, apres report des
quatre verdicts. Aucun module compile - la question du `-am` ne se pose pas.

| Etat | Vertes | Denominateur | Taux |
|---|---|---|---|
| **Ouverture de s25, 2026-08-31** | 27 | **43** | **62,8 %** |
| Apres `DEC-0066`, meme jour | 27 | **41** | 65,9 % |
| **Cloture de s25, 2026-09-01** | **31** | **41** | **75,6 %** |

**31 / 41 = 75,6 %.** Je reproduis le ratio annonce par une lecture independante, et c'est
**exactement le plafond que `DEC-0065` avait pose en section B.3** - quatre paquets, quatre lignes
distinctes. **C'est arrive une fois ; ce n'est pas une methode de prevision**, et l'historique de ce
produit ne l'avait jamais donne.

> **Le sprint affiche +12,8 points et n'en a produit que 9,7.** Les 3,1 autres viennent du
> reclassement de `DEC-0066` - deux lignes sorties du denominateur sur arbitrage de l'utilisatrice,
> **sans qu'un seul numerateur bouge**. **Les quatre lignes sont le seul progres reel.**

**Ce que je dois signaler contre le chiffre dont je reponds** : les quatre lignes sont **trois en
mathematiques CP et une en francais CM1**, c'est-a-dire dans les deux zones deja les mieux servies.
**Le francais CP reste le point le plus faible du produit - 5 sur 10, 50,0 % - et il n'a rien gagne a
s25** : ses 4,5 points d'affichage viennent de la sortie de *"Copier un mot"* du denominateur, pas
d'un jeu.

---

## 2. Ce qui reste, et le fait central de s26

**Dix lignes ne sont pas `couvert-valide` : 9 `non couvert`, 1 `en cours`.** Ce qui les bloque, apres
les arbitrages de s25 :

| Ce qui bloque | Lignes | Detail |
|---|---|---|
| **Une borne que je dois faire poser** | **1** | `LIS` - restituer l'essentiel (fr. CM1). Arbitree `LIS-1` a `LIS-10`. **Seule ligne du produit qui soit arbitree et qui bouge** |
| Le palier 2 - propositions positionnees sur l'image | 1 | Quadrillage (maths CP), `QUA-1` a `QUA-5` |
| Le palier 3 - manipulation d'une collection | 1 | `DEN` (maths CP) - et le paquet textuel, arbitre, **ne bouge pas la ligne** (`DEN-2`) |
| La saisie positionnee - une troisieme mecanique | 2 | Multiplication posee, division euclidienne (maths CM1), `POS-1` a `POS-6`, que `nadia-prof` *"ne reclame pas"* |
| **La source de la voix** | **5** | Localiser une syllabe, identifier un son, decoder par assemblage, les graphemes (`en cours`), et ecrire un mot en autonomie - qui a besoin de l'audio **et** de la saisie |

> **Le fait central de s26 : la reserve de lignes arbitrees retombe a une seule, comme a l'ouverture
> de s25 - et cette fois son deblocage est une question que je porte, pas une capacite qui manque.**
>
> **Plafond de s26 sans ouvrir de palier ni de famille : 32 / 41 = 78,0 %.**

**Un fait de comptage a ne pas perdre** : un paquet d'horloge (`HOR-1` a `HOR-8`, arbitre) leverait
la mention de couverture partielle de la ligne de l'heure et **ne rapporterait aucune ligne**, celle-ci
etant deja verte par `SEM`. Il reste un bon paquet ; il ne compte pas dans un objectif de couverture.

**Et un fait qui n'a pas bouge de trois sprints** : les cinq lignes d'audio se debloquent par **une
reponse de l'utilisatrice sur la source de la voix**, pas par de la capacite. C'est le plus gros
gisement restant, et il est ferme par une question jamais tranchee.

---

## 3. Les deux reports que je portais - ce qu'ils deviennent

### 3.1 `SUB` - le report est **eteint**, pas reconduit

`DEC-0059` avait reporte `SUB` a s24 en comptant ce report comme **le premier**. J'ai ecrit a la
cloture de s24 que le motif en etait consomme et qu'un second devrait porter un motif neuf, puis
mis `SUB` au rang 1 de s25 *"sans second report possible"*.

**`bandit-manchot-substituts-cm1` est `VALIDE` depuis le 2026-09-01, au second passage.** Le compteur
s'arrete a un. **Il n'y aura pas de motif de second report a ecrire, et c'est la seule facon propre
de fermer un compteur de report.**

*Ce que je note pour la suite, parce que le sprint l'a montre : `SUB` a ete **refusee** le 2026-08-31
avant d'etre validee le lendemain. Un rang 1 ne garantit pas une ligne ; il garantit qu'on ne la
reporte pas.*

### 3.2 `S25-NAR-1` - **second report**, et il fusionne avec un fait neuf

**Premier report compte a s25** (`DEC-0065`, section 3.1) : le quota `COMP-2` etait pris par `SUB`,
qui rapportait une ligne la ou `NAR-1` n'en rapporte aucune.

**Ce report devient le second a la cloture de s25, et son motif ne peut pas etre le meme** - le quota
`COMP-2` de s26 est libre, `SUB` etant verte et sans reaudit du.

**Je ne le reconduis pas a l'identique : je le fusionne.** `nadia-prof` a mesure le 2026-09-01 un
**second** defaut sur le meme paquet - section 4 ci-dessous -, et les deux se corrigent dans le meme
geste de reecriture. **Une story `S26-NAR-1` porte desormais les deux**, et le compteur de report la
suit : **deux reports, s25 et s26 si elle n'est pas prise.** `DEC-0007` a tenu trois reports
d'affilee, chaque fois pour un motif defendable ; **c'est ce paragraphe qu'il faudra relire avant
d'ecrire le troisieme.**

### 3.3 `S25-LIS-1` - **premier report tenu**, et son second motif est tombe, pas le premier

**Premier report compte a s25**, sur deux motifs : `LIS-6` la rendait exclusive de `SUB`, et sa
mesure de pli n'etait pas rendue.

**Les deux ont bouge, et pas dans le meme sens :**

- **Le premier motif est eteint** : `SUB` est verte, le quota `COMP-2` de s26 est libre.
- **Le second est rendu, negativement** : `ines-design` a mesure que le texte support n'est pas ce
  qui deborde - *"ce ne sont pas les mots qui debordent, ce sont les propositions"*. **La ligne ne
  s'ouvre pas tant qu'une borne sur la longueur des propositions n'existe pas**, et cette borne est
  `DV-4`, routee a `nadia-prof` et a moi le 2026-08-31.

> **Si `LIS` n'est pas prise a s26, ce sera un second report, et son motif ne pourra pas etre
> "`DV-4` n'est pas rendue" - parce que la poser est de moi.** C'est exactement pourquoi elle est au
> rang 1 de la section 5.

---

## 4. Le fait que `nadia-prof` me route sur `cp-texte-narratif`, et il est a moi

**Le fait, avec sa methode, refait par elle exhaustivement sur les vingt items** : *pour chaque item,
la position, dans le texte support, de la proposition qui est la bonne reponse, parmi les trois
propositions telles qu'elles y figurent.*

> **La bonne reponse est la premiere proposition citee sur 9 items sur 20, soit 45,0 pour cent.**
> Hasard a trois propositions : 33,3 pour cent.

**C'est la strategie qui a fait refuser `cm1-substituts`, sur un paquet valide a s24.**

**Elle ne retire pas le Done, et ses trois motifs sont les bons** : `NAR-11` dit en toutes lettres
qu'elle ne s'applique pas a ce paquet ; aucune condition `NAR-1` a `NAR-10` ne porte cette propriete ;
un verdict prononce ne se reecrit pas. **Je ne le lui demande pas et je ne le conteste pas.**

**Ce qu'elle nomme a la place, et que je verse comme le fait de produit qu'il est :**

> *"Un raccourci de surface devient disqualifiant quand il est **renforce** : quand il gagne assez
> souvent pour que l'enfant ne soit jamais corrige. A 94,8 pour cent, l'enfant qui prend le raccourci
> a presque toujours raison - **rien dans le jeu ne lui apprend qu'il a tort**, et il en sort
> conforte. A 45,0 pour cent, il a tort plus d'une fois sur deux : le jeu le contredit plus souvent
> qu'il ne le confirme, et la strategie ne survit pas a la partie."*

**Elle ne pose aucun seuil chiffre et ecrit pourquoi** - *"je n'ai pas de mesure qui en fonde un"* -,
et elle ajoute : *"45,0 pour cent reste au-dessus du hasard de douze points, et je ne le trouve pas
satisfaisant. C'est une reserve, pas un motif."*

**Ce que j'en fais, et ce que je refuse d'en faire :**

- **J'en fais du backlog** - `S26-NAR-1`, fusionnee avec le report de la section 3.2. Sa demande est
  reprise dans ses mots : *"ramener la position de la reponse au hasard, comme `cm1-substituts` vient
  de le faire"*. **Ce n'est pas urgent et cela ne bloque rien** : ce sont ses termes, pas mon
  adoucissement.
- **Je n'invente aucun seuil.** `NAR-11` est sans seuil par son choix explicite, redit le 2026-09-01.
  Le seuil qui existe est celui du garde-fou que `marc-dev` a grave - *aucune position au-dessus de la
  moitie* - et **il appartient a l'`ADR` que `theo-archi` ecrirait**, pas a une story de moi.
- **Je ne demande a personne de rouvrir un Done**, et je note que ce fait est arrive dans le depot
  parce que `marc-dev` a ecrit *"je prefere le dire que le garder"* sur un defaut qui ne lui etait pas
  reproche.

**Ce que ce fait dit du produit, au-dela de ce paquet** : **`cp-texte-narratif` est le seul autre
paquet a texte support du CP, et il n'est garde ni par le garde-fou "une phrase suffit" ni par celui
que `cm1-substituts` a fait naitre.** C'est de `nadia-prof`, route a `theo-archi` et `yanis-qa`, et
c'est le motif de rang de la section 5.

---

## 5. Le rang que je propose pour s26

**Ce que ce rang est** : mon rang, et rien d'autre. **L'affectation et la capacite sont a
`sofia-sm`**, l'arbitrage prealable de tout contenu est a `nadia-prof`, la forme de tout garde-fou
est a `theo-archi`. **Aucun chiffre de couverture n'est projete** : un objectif de sprint n'est pas
un Done.

### Rang 1 - `DV-4`, la borne de longueur des propositions. **De moi, et elle ne coute aucune tete.**

**Motif : c'est le seul verrou du produit qui, une fois leve, rend une ligne developpable - et sa
levee ne demande aucun developpeur.** Sans elle, s26 n'a **aucune** ligne arbitree a prendre, et le
second report de `LIS` porterait un motif dont je serais la cause.

Je la pose a `nadia-prof` avec le materiau qu'`ines-design` a produit et qui n'existait pas quand la
question a ete ouverte. **C'est aussi la moitie de l'arbitrage *"Instruire dans s25"* que j'avais
annonce prendre en sacrifiant le lot G** (`DEC-0065`, section B.4) : je la tiens.

### Rang 2 - `S25-SCO-1`, le score parfait par elimination. **Troisieme report s'il n'est pas engage.**

**Le compteur, en clair** : premier report a s24 (`DEC-0061`, section 4), **second a s25** sur un
motif de capacite et de dependance ecrit par `sofia-sm`. **Un report a s26 serait le troisieme.**

> *"`DEC-0007` a tenu la couverture programme fermee trois sprints d'affilee, chaque fois pour un
> motif defendable, jusqu'a ce que l'utilisatrice demande elle-meme pourquoi le produit n'avait qu'un
> seul jeu."* **Je l'ai ecrit deux fois contre ce sujet ; c'est la troisieme fois qu'il faut le
> lire.**

**Et ce qui n'a pas bouge : le prix monte tout seul.** s25 a ajoute **quatre jeux** au catalogue,
dont **deux illustres, d'une famille dont la partition d'audit n'existe pas encore**. Le cout de
`nadia-prof` reste petit - trois jeux hors compte de paquets, regime `R-B` -, mais la partition qui
le rend petit devra etre refaite sur un catalogue qui a change de nature.

**Ce qui reste du avant tout code, et n'a pas bouge depuis le 2026-08-31** : le resultat attendu, de
`nadia-prof` ; puis la forme et l'`ADR`, de `theo-archi`, **avec la clause d'independance des
variables sans laquelle sa partition serait un echantillon qui s'ignore**.

### Rang 3 - Le trou d'`ADR-0008`. **A `theo-archi`. Deuxieme sprint consecutif, et il s'est elargi.**

**Le fait de s25, mesure par `yanis-qa`, et il est plus large que celui de s24** :

| Mutation | Ce qu'elle pose dans le gabarit servi a l'enfant | Classes rouges |
|---|---|---|
| `M1` - un attribut `onclick` sur le bouton de proposition | rejeu exact de `C7` de s24 | **0 sur 1 018 tests** |
| `M7` - **un element `<script>` de dix lignes** qui ecoute le clic, lit `sessionStorage` et tient un `score` | **neuf** | **0 sur 1 018 tests** |

**`sessionStorage` et `score` sont deux mots de la liste de vocabulaire interdit d'`ADR-0008`** : la
liste existe, les mots sont dans un fichier servi a l'enfant, et rien ne rougit. Ce que s24 decrivait
comme *"un budget contournable par un attribut"* est contournable par **un bloc entier**.

**Trois faits qui font mon rang, et aucun n'est de moi** : c'est la porte de la **dette n. 4 de
`CLAUDE.md`** ; **s25 a cree des gabarits neufs et deux chaines de rendu** ; et le critere de sortie
pose par `sofia-sm` est **une commande humaine hors CI** qui, `yanis-qa` l'ecrit, *"ne voit pas
`M7`"* - un element `<script>` ne porte aucun attribut de la forme `on...=`.

**Etat de fait, sans dramatiser, et ce sont ses mots** : *"Rien n'est casse. La voie est libre, et
elle est plus large qu'a s24."*

### Rang 4 - Le garde-fou *"un rang suffit"*, avec sa clause de non-vacuite. **A `theo-archi` pour l'`ADR` ; le rang est a moi.**

**Ce qu'il vaudrait, chiffres de `marc-dev` reproduits par `yanis-qa` avec son propre instrument** -
et je donne les deux instruments parce qu'ils ne rendent pas le meme nombre sur le meme etat :

| Etat mesure | Instrument **positionnel** (`yanis-qa`, la strategie du garde-fou) | Instrument **lexical** (`nadia-prof`, le premier prenom du texte) |
|---|---|---|
| `cm1-substituts` **refuse** | 16 / 20 = **80,0 %** | 19 / 20 = **95,0 %** |
| `cm1-substituts` servi | 7 / 20 = **35,0 %** | 7 / 20 = **35,0 %** |
| `cp-texte-narratif` servi | 9 / 20 = **45,0 %** | - |

*Hasard a trois propositions : 33,3 %. Les deux instruments sont de la meme famille prise par deux
bouts, ils ne se substituent pas l'un a l'autre, et **je ne fusionne pas leurs chiffres**.*

**Au seuil existant - aucune position au-dessus de la moitie - le garde-fou serait vert sur les deux
paquets servis et rouge sur l'etat refuse.** Il aurait attrape le defaut **avant les 210 manches**
qu'il a fallu pour le trouver a la main.

**Et la moitie qui manquait, trouvee par `yanis-qa`** : **le domaine derive est vide sur
`cm1-comprehension`** - 20 items a texte support, 0 juge. *"Une generalisation sans clause de
non-vacuite par paquet y serait verte sans rien examiner"*, ce qu'il nomme **`CPL-3` une quatrieme
fois, creee par le garde-fou cense fermer la troisieme**.

**Mon rang tient sur cette moitie-la autant que sur la premiere** : un garde-fou qui attrape le
defaut connu mais qui est vert et vide sur un paquet voisin serait une promesse plus large que ce
qu'il verifie - **exactement le motif que j'ai fait mien a la cloture de s24**.

### Rang 5 - Les trois identifiants que rien n'execute. `MON-6`, `SUB-8`, `DES-6`.

**Le motif est de `nadia-prof` et je le reprends tel quel** : *"une condition que seule ma main tient
tombera le jour ou je ne relirai pas."*

| Identifiant | Ce que `yanis-qa` mesure | Proprietaire |
|---|---|---|
| `SUB-8` | Sa **moitie calculable** est encodee ailleurs depuis s24 - `PaquetCpTexteNarratifTest` porte la meme propriete pour `NAR-7`, **dans le meme module**, par une table declaree. *"Le precedent existe, l'ecart est un oubli d'encodage"* | `marc-dev` |
| `DES-6` | Annoncee comme devant mordre *"sur la chaine de la monnaie"* ; **cette chaine est servie depuis s25, avec des chiffres traces**, et rien ne l'execute | `ines-design` et `nadia-prof` pour la propriete, `theo-archi` pour le perimetre |
| `MON-6` | Non retroactive, **population vide aujourd'hui** : le trou ne s'ouvrira qu'au paquet de composition suivant. *"La forme de `CPL-3` prise a l'avance"* | `nadia-prof`, puis `theo-archi` |

**Rang 5 et non plus haut, et le motif est de moi** : aucune des trois ne decrit un defaut qu'un
enfant rencontre aujourd'hui. **`SUB-8` est la plus mure des trois** - le precedent est ecrit, le
module est le meme, et le cout est celui d'un test.

### Rang 6 - `S26-NAR-1`, la reprise de `cp-texte-narratif`. **Non urgente, et c'est elle qui le dit.**

Section 4. Deux defauts fusionnes en une story : *"aucune phrase seule ne suffit"* (2026-08-31) et la
position de la reponse a 45,0 % (2026-09-01). **`nadia-prof` : *"ce n'est pas urgent et cela ne bloque
rien"*.** Elle consommera le quota `COMP-2` du sprint qui la prendra.

### Ce que je ne range pas, et qui n'est pas a moi

`CON-6` clause `(b')` - **insatisfaisable telle qu'elle est ecrite** sur un paquet a reponses uniques,
et verte sur les deux paquets servis une fois restreinte. **Le domaine est a `nadia-prof`**, qui a
deja adopte la restriction de `yanis-qa`, **et l'encodage a `theo-archi`.** Je le nomme pour qu'il ne
tombe pas entre les deux, et je ne lui donne pas de rang.

---

## 6. Ce que je route

| Suite | Destinataire | Ce que je demande |
|---|---|---|
| **`DV-4`** - une borne sur la longueur des propositions, avec le materiau d'`ines-design` | `nadia-prof`, par `sofia-sm` | **Sans elle, s26 n'a aucune ligne arbitree a prendre.** C'est la moitie de l'arbitrage *"Instruire dans s25"* que j'avais annonce tenir |
| **La source de la voix** - **cinq** lignes, le plus gros gisement restant | l'utilisatrice, par `sofia-sm` | Ouverte depuis le 2026-08-28, posee a s25, non rendue. **Elle coute une reponse, pas un sprint** |
| Le resultat pedagogique attendu de la correction de `M5` | `nadia-prof`, par `sofia-sm` | Premiere des deux instructions bloquantes, due depuis le 2026-08-31 |
| L'`ADR` de `M5`, avec la clause d'independance des variables | `theo-archi`, par `sofia-sm` | Apres le precedent et jamais avant |
| `ADR-0008` aveugle a l'attribut **et au bloc `<script>`** dans un gabarit | `theo-archi`, par `sofia-sm` | **Deuxieme sprint consecutif de routage.** Je range, je ne tranche pas |
| Le garde-fou *"un rang suffit"*, **avec une clause de non-vacuite par paquet** | `theo-archi` pour l'`ADR`, `yanis-qa` pour la mesure | La moitie qui manquait est de `yanis-qa` et elle est decisive |
| `CON-6` `(b')` - le domaine, puis l'encodage | `nadia-prof` puis `theo-archi` | Qu'elle ne tombe pas entre les deux |
| `SUB-8`, `DES-6`, `MON-6` | `marc-dev`, `ines-design`, `theo-archi` | *"Une condition que seule ma main tient tombera le jour ou je ne relirai pas"* |
| `S26-NAR-1` - deux defauts fusionnes, **second report** | moi-meme, a relire a l'ouverture de s27 | Un troisieme report demandera un motif neuf a cote de celui-ci |
| `S25-LIS-1` - **premier report tenu**, et son motif de s26 ne pourra pas etre `DV-4` | moi-meme | Parce que poser `DV-4` est de moi |
| Le francais CP a 50,0 %, sans gain reel a s25 | moi-meme, au rang de s26 | Un objectif de couverture a servi les zones deja pleines. **Je le porte plutot que de le laisser dans une moyenne** |

---

## 7. Ce que cette decision ne fait pas

- Elle **ne prononce aucun Done** et n'en anticipe aucun. Les quatre verdicts sont de `nadia-prof`,
  je les ai lus et reportes.
- Elle **ne fixe aucun perimetre de s26** : le rang de la section 5 est un ordre de valeur, pas une
  affectation, et la capacite de `nadia-prof` comme celle de `sofia-sm` restent a rendre.
- Elle **n'additionne pas** le gain de denominateur et le gain de lignes, ici ni ailleurs.
- Elle **ne demande a `nadia-prof` de rouvrir aucun verdict**, ni sur `cp-texte-narratif` ni sur les
  paquets valides sous reserve levee.
- Elle **ne pose aucun seuil chiffre** sur la position d'une reponse : `NAR-11` est sans seuil par le
  choix explicite de son autrice, et le seuil du garde-fou appartient a l'`ADR` qui l'ecrira.
- Elle **ne tranche rien de technique** : ni le perimetre d'`ADR-0008`, ni la forme d'un garde-fou,
  ni le domaine de `CON-6`.
- Elle **ne reprend aucun chiffre de la porte technique** a son compte : `1 018` tests est la mesure
  de quatre autres agents, je ne l'ai pas reproduite, et rien ici n'en depend - `nadia-prof` ecrit
  elle-meme *"aucun verdict fonde sur les 1 018 tests verts"*.

---

## Amendement du 2026-09-01 - la reserve passe d'une ligne a cinq, et c'est le renversement exact de la section 2

**Statut de la decision apres cet amendement : Acceptee, amendee le 2026-09-01.**

**La section 2 de ce document est fausse depuis le jour ou elle a ete ecrite, et pas par ma faute
d'instruction** : elle rangeait cinq lignes derriere *"la source de la voix"* en les comptant comme
fermees. **Elles ne l'etaient pas ; elles attendaient une reponse qui est arrivee le meme jour.**
Le texte reste lisible a sa place ; cet amendement le corrige ici.

### B.1 Ce qui a change, et les deux sources

- **L'utilisatrice, 2026-09-01** : la voix est celle d'un adulte present, et la portee est bornee aux
  seules lignes ou la voix est la competence - `DEC-0068` et son amendement.
- **`nadia-prof`, 2026-09-01** : **quatre des cinq lignes sont servies par ce moyen, une est
  refusee** - `ARBITRAGE-PREALABLE-s26-la-voix-est-un-adulte.md`, sections 3 et 4.

### B.2 Le plafond, recompte par moi

*Methode : comptage automatise des quatre tableaux de matiere de
`squad/programme/MATRICE-couverture.md`, colonne `Statut`, sur ce seul fichier, a
`96a80b4`. Denominateur 41 apres les six `non couvrable en l'etat`. Aucun module compile - la
question du `-am` ne se pose pas.*

| Etat | Compte | Taux |
|---|---|---|
| Aujourd'hui | 31 / 41 | **75,6 %** |
| Si les **quatre lignes acceptees** sont servies **et validees** | **35 / 41** | **85,4 %** |
| Si `LIS` s'y ajoute | 36 / 41 | 87,8 % |

**Je reproduis le 85,4 pour cent de `nadia-prof` a la decimale par une lecture independante**, et
j'ajoute les deux reserves qu'elle attache elle-meme a ce chiffre : la cinquieme ligne y est **deja
comptee comme fermee**, et **quatre lignes ne font pas quatre paquets**.

### B.3 Un fait que son compte ne fait pas apparaitre, et qui est de mon perimetre

**Les quatre lignes acceptees sont toutes les quatre en francais CP.**

| Matiere / niveau | Aujourd'hui | Si les quatre sont validees |
|---|---|---|
| mathematiques CP | 9 / 11 = 81,8 % | inchange |
| mathematiques CM1 | 8 / 10 = 80,0 % | inchange |
| **francais CP** | **5 / 10 = 50,0 %** | **9 / 10 = 90,0 %** |
| francais CM1 | 9 / 10 = 90,0 % | inchange |

**Le point le plus faible du produit deviendrait son mieux servi**, et le domaine *"Langage oral et
conscience phonologique"* - **1 sur 3 depuis le sprint 0** - se fermerait entierement.

**Je le signale parce que j'ai signale l'inverse a la cloture de s25 contre mon propre chiffre** :
les quatre lignes de s25 etaient allees dans les deux zones deja les mieux servies, et j'avais ecrit
que le francais CP *"n'a rien gagne a s25"*. **Un sprint dont l'objectif est la couverture et qui
corrige en meme temps le desequilibre est une coincidence, pas une methode** - mais elle merite
d'etre lue, et elle rejoint l'ordre que `nadia-prof` avait pose le 2026-08-15 et que ce produit n'a
jamais suivi : **audio d'abord, parce que c'est du CP et que l'annee ne recommence pas.**

### B.4 Combien de paquets - c'est a moi, et je reponds avec la question que je ne tranche pas

`nadia-prof` ecrit : *"Quatre lignes, ce n'est pas quatre paquets. Celle des graphemes et des
phonemes demande **deux moities** [...] Je ne prejuge pas du nombre de paquets : c'est
`camille-po`."*

**Mon compte, et il porte une question ouverte :**

| Ligne | Paquets | Motif |
|---|---|---|
| Localiser une syllabe | **1** | `VOI-2`, `VOI-3`, `VOI-4` |
| Identifier un son (phoneme) | **1** | `VOI-2`, `VOI-3`, `VOI-5`, `VOI-6` |
| Associer les graphemes aux phonemes | **1 ou 2** | Deux moities, `VOI-7`. **Question ouverte ci-dessous** |
| Ecrire de maniere autonome un mot simple | **1** | Saisie alphabetique, ouverte par la levee de `SAI-11` |
| **Total** | **4 ou 5** | |

**La question que je ne tranche pas, et elle est a `nadia-prof`** : la ligne des graphemes exige la
clause 1 (voyelles et consonnes simples) **et** la clause 2 (`ou`, `oi`, `ch`), la clause 3
conservant les trois nasales du jeu existant. **Un seul paquet neuf servant les deux clauses
satisfait-il la barre du 2026-08-10, ou en faut-il deux ?** C'est un jugement sur le niveau et sur la
distribution des items, pas sur un decoupage technique. **Je le pose plutot que de le decider, et
c'est ce chiffre qui fait la difference entre 4 et 5 paquets.**

**Reponse du 2026-09-01, rendue par `nadia-prof` - il en faut deux.** Elle mesure sur le materiel
reel (`ARBITRAGE-PREALABLE-s26-la-voix-est-un-adulte.md`, addendum, section 11) que le melange des
deux clauses dans un paquet reproduit la forme exacte de `CON-6` - la famille minoritaire (`ch`)
n'est jamais distracteur sur le paquet refuse d'origine, `P(bonne / presente) = 100 %` - et que les
deux clauses n'admettent pas la meme condition de distribution (un tiers d'items pour trois
digraphes, contre un espace de 22 lettres). **Le total est donc cinq paquets, pas quatre, et il
tombe exactement sur ma borne haute sans marge** (section B.5 ci-dessous, inchangee sur le fond).
Stories, criteres et fiches : `docs/backlog/backlog.md`, section "Backlog - perimetre s26" ;
`squad/conformite/FICHE-bandit-manchot-lettre-du-son-cp.md` et `FICHE-bandit-manchot-digraphe-du-
son-cp.md`.

### B.5 Ce que la capacite rend, et le fait de composition qui en decoule

**La borne de `nadia-prof` est inchangee - quatre a cinq paquets sur une mecanique existante - et
elle refuse de la reviser ici** : *"Une mecanique a voix est neuve, et je la pricerai quand je saurai
ce qu'elle est - pas avant, et pas pour arranger un compte."*

> **Fait de composition, et c'est de l'arithmetique, pas un arbitrage : les quatre lignes a voix
> consomment 4 a 5 paquets, c'est-a-dire la borne entiere. `LIS` ne tient pas a cote.**

**Ce qui decide si cette borne tient reellement, et ce n'est pas de moi** : `nadia-prof` price un
**audit de mecanique neuve** en plus de sa borne. **Or `ADR-0073` a etabli a s25 qu'une famille
d'items neuve n'est ni une forme d'item neuve ni une mecanique neuve** - le palier 1 visuel n'a rien
coute a sa borne pour ce motif, et *"la ligne de plus n'est pas de moi, elle est de `theo-archi`"*.

**Si la famille a voix est un champ d'item et non une mecanique, le meme raisonnement s'applique et
sa borne tient telle quelle. Si c'en est une, son audit s'ajoute et elle le pricera.** **C'est une
mesure de `theo-archi`, elle n'a pas ete demandee, et elle decide le nombre de lignes que s26 peut
porter.** Je la route en section B.7.

### B.6 Le rang de s26, revise - la section 5 est amendee sur ses deux premiers rangs

**Ce que je change** : les quatre lignes a voix entrent au rang 1, `DV-4` descend au rang 2 en
gardant son motif, et le reste de la section 5 est inchange.

| Rang | Poste | Lignes |
|---|---|---|
| **1** | **Les quatre lignes a voix** - localiser une syllabe, identifier un son, les graphemes, ecrire un mot en autonomie | **+4**, jusqu'a 35 / 41 = 85,4 % |
| 2 | **`DV-4`**, la borne de longueur des propositions - **de moi, et sans cout de developpeur** | 0, mais elle debloque `LIS` |
| 3 | `S25-SCO-1`, le score parfait par elimination - **troisieme report s'il n'est pas engage** | 0 |
| 4 | Le trou d'`ADR-0008` - **deuxieme sprint, elargi** | 0 |
| 5 | Le garde-fou *"un rang suffit"*, avec sa clause de non-vacuite | 0 |
| 6 | Les trois identifiants que rien n'execute - `MON-6`, `SUB-8`, `DES-6` | 0 |
| 7 | `S26-NAR-1`, la reprise de `cp-texte-narratif` - **non urgente, ses mots** | 0 |

**Le motif du rang 1, et il n'est pas seulement arithmetique** : quatre lignes pour la borne entiere,
sur le niveau le plus creux du produit, en fermant un domaine reste a 1 sur 3 depuis le sprint 0.
**Et c'est l'ordre que `nadia-prof` avait pose le 2026-08-15 - "audio, puis visuel, puis saisie" -
que ce produit a inverse deux fois, la premiere sur arbitrage de l'utilisatrice, la seconde faute
d'une source.** Il n'y a plus de motif de l'inverser une troisieme fois.

**Pourquoi `DV-4` reste au rang 2 et n'est pas repoussee** : elle ne coute aucune tete de developpeur
et je l'ai annoncee comme mienne. **Je la pose a s26 quoi qu'il arrive du reste.** Ce qui change est
ce qu'elle achete : `LIS` ne tient plus dans la borne de ce sprint, donc `DV-4` prepare s27 au lieu
d'ouvrir s26.

> **`S25-LIS-1` sera donc un second report a s26, et son motif est neuf et ecrit d'avance : la borne
> d'audit de `nadia-prof` est entierement consommee par les quatre lignes a voix.** Ce n'est plus
> *"`DV-4` n'est pas rendue"* - je la rends - **c'est une contrainte de composition, et elle
> n'appartient pas a ma diligence.** Un troisieme report a s27 devra porter autre chose.

**Ce que ce rang ne resout pas, et je le garde lisible pour la troisieme fois** : les rangs 3 a 6
**ne rapportent aucune ligne**, l'objectif de l'utilisatrice est toujours la couverture, et **s26
ouvrirait une famille de plus.** Le trou d'`ADR-0008` en est a son deuxieme sprint de routage et il
s'est elargi ; le score par elimination en serait a son **troisieme report**, ce que `DEC-0007` a
sanctionne ; et **une famille neuve, c'est encore des gabarits neufs et un champ de contenu neuf**,
c'est-a-dire le terrain exact des deux dettes que je range depuis deux sprints. **Je n'arbitre pas ce
conflit - la capacite est a `sofia-sm` et deux des postes sont a `theo-archi` - et je refuse qu'il
disparaisse sous un plafond de 85,4 pour cent.**

### B.7 Ce que cet amendement route

| Suite | Destinataire | Ce que je demande |
|---|---|---|
| **La ligne des graphemes : un paquet neuf ou deux ?** (B.4) | `nadia-prof` | C'est ce qui fait la difference entre 4 et 5 paquets, donc entre une borne tenue et une borne au maximum |
| **La famille a voix est-elle un champ d'item ou une mecanique ?** (B.5) | `theo-archi`, par `sofia-sm` | **Le precedent d'`ADR-0073` est exact et il n'a jamais ete applique a ce cas.** Sa reponse decide si un audit de mecanique s'ajoute a la borne de `nadia-prof` |
| **`VOI-12` est bloquante** - l'ecran dit a l'adulte ce qu'il ne doit pas faire, la ou il lit | `theo-archi` et `ines-design` pour la forme, `nadia-prof` pour le texte | *"Sans `VOI-12`, je refuserai les paquets a voix."* Ce n'est pas un ornement et cela ne se decouvre pas a la porte |
| **`VOI-10` avant toutes les autres en test** | `theo-archi`, `yanis-qa` | Elle referme le canal de fuite **par construction** - meme geste qu'`ADR-0073` sur l'illustration - au lieu d'une liste d'interdits |
| **`VOI-3`, `VOI-5`, `VOI-8` ne sont gardees par personne** | `sofia-sm` pour le rapport de sprint ; **moi pour les stories et les fiches** | **Une premiere sur ce depot.** Elles doivent etre ecrites comme non gardees partout ou elles apparaissent, faute de quoi elles seront lues comme les autres |
| **La lettre accentuee sur tablette, toujours sans reponse** | l'utilisatrice, par `sofia-sm` | **Elle ne bloque plus** - `SAI-1` la couvre - et `nadia-prof` la signale pour qu'elle ne soit pas oubliee, pas pour qu'elle soit traitee maintenant |
| **La ligne refusee reste `non couvert`**, pas `non couvrable en l'etat` | moi-meme | `nadia-prof` *"constate une impasse"* et nomme deux voies. **Le critere de la matrice n'est pas rempli et je ne bouge rien** |
| **Le conflit couverture contre dette, troisieme sprint** | `sofia-sm`, puis l'utilisatrice | Un choix, pas un defaut de planning. **Il s'aggrave d'une famille de mecanique** |

### B.8 Ce que cet amendement ne fait pas

- Il **ne fait bouger aucune ligne de la matrice** : aucun paquet n'existe, aucun Done n'est
  prononce, et le plafond de B.2 est un plafond - il suppose que quatre paquets neufs passent la
  porte du premier coup, ce qui n'est arrive qu'une fois sur ce produit.
- Il **ne revise pas la borne de `nadia-prof`** et ne lui demande rien au-dela de ce qu'elle a ecrit.
- Il **ne decide pas le nombre de paquets de la ligne des graphemes** : la question est a elle.
- Il **ne tranche rien de technique** : ni la nature de la famille a voix, ni la forme des quatre
  textes, ni le garde-fou de `VOI-10`.
- Il **ne reclasse aucune ligne**, ni celle qui est refusee, ni celle de la reconnaissance vocale.
- Il **n'efface pas la section 2**, qui rangeait cinq lignes derriere un obstacle qui n'en etait pas
  un. **Elle reste lisible avec sa date, et B.1 dit pourquoi elle est fausse.**
