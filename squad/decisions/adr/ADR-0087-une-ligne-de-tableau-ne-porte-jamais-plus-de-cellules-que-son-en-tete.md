# ADR-0087 - Une ligne de tableau ne porte jamais plus de cellules que son en-tete, et la largeur attendue est celle de cet en-tete

- **Statut** : Accepte
- **Date** : 2026-09-08
- **Sprint** : s31 (cloture)
- **Auteur** : theo-archi
- **Route par** : `nadia-prof`, conclusion de s31, section 5.4 ; fait verifie par `sofia-sm` avant
  routage

## Contexte

`nadia-prof` a trouve, en verifiant les chiffres de `sofia-sm`, que **deux lignes de
`squad/programme/MATRICE-couverture.md` portent huit cellules pour sept colonnes** : celles de
`bandit-manchot-melanges-cm1` et de `bandit-manchot-objet-technique-cm1`. Une barre verticale non
echappee y ouvre une colonne absente de l'en-tete. **Les deux seules des 209 lignes de programme.**

Ce que porte la huitieme cellule : le resultat de **deux des six stories du sprint** - les reaudits
du 2026-09-07 levant `MEL-R1`, `MEL-R2`, `MEL-R3`, `OBT-R1` et `OBT-R2`. Le texte est juste, il est
dans le fichier, et **un rendu markdown ne l'affiche pas** : la specification GitHub Flavored
Markdown ignore les cellules qui depassent le compte de l'en-tete. Le statut et les comptes de
couverture ne sont pas touches ; ce qui disparait est la trace du paiement, dans le document qui
pilote la couverture du programme.

Elle ne prescrit aucune forme et route une phrase : *"qu'aucun garde-fou ne compte les cellules d'une
ligne de matrice est pour `theo-archi`"*. Le contenu des deux lignes appartient a `camille-po`, qui
le corrige en parallele de cet ADR.

**Ce que le depot avait deja, et qui ne pouvait pas voir ce defaut** : `relecteur-pedagogique` relit
les livrables non executables, et cette famille-la est precisement celle qu'une relecture ne voit
pas - le texte manquant est present a la lecture de la source et absent au rendu, si bien que le
relire ne le fait pas manquer. Aucun automate du depot ne lisait la structure d'un tableau markdown.

## Decision

### 1. Aucune ligne de corps ne porte plus de cellules que l'en-tete de son tableau

La cellule en trop n'est affichee par aucun rendu. Son texte est donc ecrit, commite, et lu par
personne.

### 2. La largeur attendue est derivee de l'en-tete du tableau juge, jamais ecrite dans la regle

C'est ce qui rend la regle applicable a tout le depot au lieu de la matrice seule.
`MATRICE-couverture.md` porte dix-huit tableaux de programme a sept colonnes **et** des tableaux de
synthese, de bilan et de ceremonie a deux, trois, quatre ou cinq colonnes ; les 639 fichiers markdown
du depot en portent 2 159 de largeurs qui vont de deux a huit. **Un motif qui exigerait sept cellules
partout serait rouge sur des lignes legitimes** - la forme exacte du faux rouge qu'`ADR-0013` refuse.
Une largeur derivee de l'en-tete ne peut pas l'etre : la comparaison est interne au tableau, comme
celle d'`ADR-0045`.

### 3. Le perimetre est derive du depot, pas de la matrice

Tout fichier `.md` sous la racine derivee du pom agregateur, sauf les repertoires de sortie de build
(`target`), les arbres de contre-exemple (`contreexemple`, dont la raison d'etre est de porter des
defauts) et `.git`. Meme derivation que `ADR-0066`.

**Motif de ne pas se limiter a `squad/programme/`** : le defaut n'a rien de particulier a la matrice,
et la regle l'a prouve avant d'etre commitee en trouvant **un troisieme cas que personne n'avait
signale**, dans un fichier que je tiens moi-meme - `ADR-index.md` cachait de la meme facon
**l'amendement du 2026-08-29 de la ligne `ADR-0063`**, un paragraphe entier. Un perimetre restreint a
la matrice aurait ete vert sur mon propre defaut.

**La derivation ne l'est qu'a moitie, et c'est dit** : elle est derivee du repertoire mais figee sur
l'extension `.md`. C'est le defaut que le lot F de s25 a corrige sur le job `outils` ; ici
l'extension **est** le critere d'objet - un tableau markdown n'est rendu comme un tableau que dans un
fichier rendu comme du markdown - et non un accident de balayage. Le jour ou le depot servira du
markdown sous une autre extension, la population changera de definition et pas seulement de filtre.

### 4. La dette existante est arretee par un cliquet exact, elle n'est pas corrigee ici

*Cette section decrit l'etat du 2026-09-08 au moment de la decision. La dette a ete payee le meme
jour ; voir l'amendement au bas de cet ADR.*

Les deux lignes de la matrice sont exemptees par leur nom de jeu, avec un plafond de **2**, parce que
le fichier appartient a `camille-po` et qu'elle y ecrit au moment ou cet ADR est ecrit. Le troisieme
cas, `ADR-index.md`, n'est pas exempte : il est **corrige** dans le meme commit, parce que ce fichier
est le mien.

**Consequence a connaitre d'avance, et non a decouvrir** : le cliquet est en egalite, donc la
correction de `camille-po` **rend ce garde-fou rouge**. Le message d'echec nomme l'exemption a retirer
et la valeur a inscrire ; le cout est de deux lignes dans un fichier de test, paye une fois, par qui
merge en second. C'est la mecanique de `DETTE_DE_BALAYAGE` (`ADR-0013`) et du cliquet d'`ADR-0067`,
et son prix est le meme.

## Regle executable

`bootstrap/src/test/java/com/recreo/archi/AucuneLigneDeTableauNePortePlusDeCellulesQueSonEnTeteTest.java`

**Ce n'est pas une regle ArchUnit**, et le compte de regles ArchUnit du depot est **inchange, 33** :
l'objet juge est un fichier markdown du depot, qu'ArchUnit ne modelise pas. C'est la meme famille que
`ADR-0066` et `ADR-0085`, garde-fous de gouvernance textuelle executes par la suite de `bootstrap`
dans le job `build`.

**Cinq clauses.**

| Clause | Ce qu'elle tient |
|---|---|
| `leDepotReelNAffichePasMoinsQuIlNEcrit` | la clause vivante, sur les 639 fichiers, dette exemptee |
| `laDetteNommeExactementLesLignesEnSurplusQuiRestent` | le cliquet : rouge si une ligne en surplus s'ajoute, rouge si un nom de la dette ne designe plus rien, rouge si le plafond cesse de valoir la taille de la dette |
| `leBalayageLitLesTableauxReelsDuDepot` | `ADR-0051` : le balayage dit combien de fichiers, de tableaux et de lignes il a lus, pour qu'un vert ne puisse pas venir d'un motif qui ne rend plus rien |
| `lesDeuxLignesReellesDeLaMatriceFontEchouerLaRegle` | la morsure, sur materiau reel |
| `lExemptionEstUneExemptionEtNonUnPerimetre` | l'exemption est une exemption et non un perimetre : le meme materiau passe quand on le nomme, et echoue quand on ne le nomme pas (nommee `laMemeDetteExempteCesDeuxLignesEtRienDAutre` jusqu'a l'amendement du 2026-09-08) |

**La morsure porte sur du materiau reel, jamais sur une fixture de ma main** - la lecon de s05. Le
contre-exemple est
`bootstrap/src/test/resources/contreexemple/AucuneLigneDeTableauNePortePlusDeCellulesQueSonEnTeteTest/extrait-matrice.md`,
recopie octet pour octet de `MATRICE-couverture.md` **a la revision `7a3c8cc8`** : l'en-tete de sept
colonnes et les deux lignes de huit cellules telles que `nadia-prof` les a trouvees. La preuve survit
donc a la correction de `camille-po`, ce qu'une morsure prise sur le fichier vivant ne ferait pas.

**Et la morsure a ete verifiee dans le sens vivant avant d'etre figee** : dette videe, le garde-fou
nomme les deux lignes reelles du fichier reel en une execution - `squad/programme/MATRICE-couverture.md:1762`
et `:1772`, chacune avec le debut du texte invisible. Bloc de la mesure : `Tests run: 5, Failures: 4`
sur le worktree `s31-theo-matrice`, JDK que la machine virtuelle des tests rapporte : **21.0.10**
(`ADR-0071`).

**Methode des chiffres cites ici, dans le meme paragraphe.** Perimetre : celui de la section 3, sur
l'arbre de `7a3c8cc8`. **639 fichiers markdown, 2 159 tableaux, 12 492 lignes de corps, 3 lignes en
surplus.** Les 648 fichiers `.md` que `git ls-files` rend comptent en plus les **9** qui vivent sous
un arbre de contre-exemple, hors perimetre par construction. Les quatre chiffres sont rendus a
l'identique par deux implementations independantes - le garde-fou lui-meme et un balayage ecrit a
part pour le controler.

## Ce que cette regle interdit reellement, et ce qu'elle ne voit pas

**Ce qu'elle interdit** : qu'un texte entre au depot dans une cellule qu'aucun lecteur ne verra. Elle
l'a prouve sur trois cas reels, dont un que personne n'avait signale.

**Ce qu'elle ne juge pas, avec son chiffre.** Une ligne qui porte **moins** de cellules que son
en-tete n'est pas refusee : le rendu la complete de cellules vides et aucun texte ne disparait. Le
depot en porte **9**, dans six fichiers, tous anterieurs a ce sprint :

| Fichier | Lignes |
|---|---|
| `squad/conformite/FICHE-bandit-manchot-nature-mot-cp.md` | 126, 127 |
| `squad/conformite/FICHE-barre-entree-niveau.md` | 2178, 2180 |
| `squad/sprints/s05-lever-le-refus-du-bon-son/rapport-qualite.md` | 248 |
| `squad/sprints/s08-cabinet-de-la-machine/planning.md` | 191 |
| `squad/sprints/s14-points-d-entree-par-niveau/planning.md` | 148, 149 |
| `squad/sprints/s15-cinq-arbitrages-de-l-en-tete/proposition-ines-etat-actif-et-rang-unique.md` | 55 |

**Dette nommee, chiffree, non encodee, et le motif de ne pas l'encoder est un fait de rendu** : rien
n'y est invisible. Un cliquet de 9 lignes sur six fichiers dont cinq sont des documents de ceremonie
clos couterait un diff a chaque correction, pour garder une propriete qui ne fait perdre aucun texte.
**Declencheur ecrit** : le jour ou un deficit fait perdre du sens - une colonne decalee, une valeur
lue sous le mauvais intitule - la clause se retourne en egalite sur cette table-la.

**Le dernier cas de cette table est d'une autre nature, et il nomme la limite du balayage** : dans
`proposition-ines-etat-actif-et-rang-unique.md`, une ligne de tableau est repliee sur deux lignes
source (55 et 56). Le rendu en fait deux lignes, la seconde n'ouvrant pas par une barre verticale.
**C'est la seule ligne de corps des 12 492 dans ce cas**, et c'est la seule que le balayage ne lit
pas : il reconnait une ligne de corps a sa barre verticale d'ouverture. Ce que cela laisse passer :
une ligne en surplus qui omettrait sa barre d'ouverture. Population aujourd'hui : **1 objet, en
deficit et non en surplus**.

**Trois autres choses qu'elle ne voit pas**, ecrites parce qu'un garde-fou muet sur ses trous laisse
croire qu'il n'en a pas :

- **une ligne de delimitation dont la largeur dement l'en-tete** : le tableau entier cesse d'etre un
  tableau et se rend en paragraphe. Population mesuree : **0**. Non encodee - un motif ajoute d'avance
  sur une population vide est le faux rouge symetrique du faux vert combattu ici (meme motif que le
  refus d'ajouter `squad/outils/*/*-test.py` au job `outils`) ;
- **un tableau cite dans un bloc de code** : le balayage ne suit pas les blocs de code, donc un
  exemple fautif cite dans un ADR pour l'expliquer serait compte comme un defaut reel. Consequence
  immediate et assumee : **cet ADR-ci ne recopie aucune des lignes fautives** - il les designe. Le
  contre-exemple, lui, vit sous `contreexemple`, hors perimetre ;
- **une cellule en trop vide** : elle est refusee comme les autres, alors qu'elle ne cache aucun
  texte. Population mesuree : **0**. Choix de la simplicite - une branche de moins, et une ligne mal
  formee reste mal formee.

## Consequences

### Bonnes

- Le defaut ne peut plus entrer en silence, dans aucun fichier markdown du depot, y compris ceux de
  `squad/` qui n'ont pas de porte technique propre.
- Un cas reel, jamais signale, est trouve et corrige par la regle avant qu'elle soit commitee.
- Le message d'echec nomme le fichier, la ligne, les deux largeurs et le debut du texte invisible :
  la correction ne demande aucune enquete.

### Mauvaises

- **La correction de `camille-po` rend ce garde-fou rouge** (section 4). C'est voulu et c'est ecrit,
  mais c'est un cout de coordination reel entre deux branches d'un meme sprint.
- **Le balayage lit 639 fichiers a chaque `mvn verify`.** Mesure sur le worktree : la classe entiere
  s'execute en 2,8 s, dont l'essentiel est ce balayage. C'est du temps de build paye a chaque commit.
- **Un cliquet gele une dette et peut faire croire qu'elle est traitee.** Les deux lignes de la
  matrice restent invisibles au rendu tant que `camille-po` ne les a pas corrigees ; ce garde-fou ne
  raconte pas le contraire.
- La regle ne dit rien de la **justesse** de ce qu'une cellule contient. Un tableau bien forme peut
  mentir.

### Ce qui me ferait changer d'avis plus tard

- Si une famille de fichiers markdown legitimement mal formes entrait au depot - une donnee generee,
  un export - le perimetre devrait exclure par une propriete, jamais par un nom de fichier, et cette
  propriete serait a ecrire ici avant d'etre codee.
- Si le deficit faisait perdre du sens une seule fois, la section "ce qu'elle ne juge pas" se
  retourne en clause, avec le cliquet exact de la table ci-dessus.
- Si le cout de balayage devenait visible dans le temps de build, la population se restreindrait aux
  fichiers modifies par le commit - ce qui demande de faire entrer git dans un test, et ce depot ne
  l'a jamais fait.

## Alternatives considerees

1. **Un motif qui exige sept cellules sur les lignes de la matrice.** Ecartee, et c'est l'alternative
   qu'il fallait ecarter la premiere : la matrice porte des tableaux de deux a sept colonnes, un tel
   motif serait rouge sur des lignes legitimes. La question posee etait celle du **perimetre derive**
   autant que celle du comptage.
2. **Un garde-fou de shell dans le job `chemins` ou un juge pur de `outils/`.** Ecartee : le critere a
   besoin de la structure d'un tableau, pas d'une expression reguliere ligne a ligne, et les
   garde-fous de gouvernance textuelle du depot (`ADR-0066`, `ADR-0085`) vivent deja dans la suite de
   `bootstrap`. Deux emplacements pour la meme famille divergeraient.
3. **Une etape de relecture par `relecteur-pedagogique`.** Ecartee sur le fait qui fonde cet ADR : la
   relecture n'a pas vu ces deux lignes en deux sprints, et elle ne pouvait pas - le texte est present
   dans la source qu'elle lit et absent du rendu qu'elle ne lit pas.
4. **Corriger les deux lignes moi-meme et ne rien encoder.** Ecartee deux fois : le contenu appartient
   a `camille-po`, et une correction sans regle laisse revenir le defaut - c'est la definition d'une
   decision qui n'a pas ete prise (`CLAUDE.md`).
5. **Encoder aussi le deficit, avec son cliquet de 9.** Ecartee sur le fait de rendu : rien n'y est
   invisible. Le declencheur est ecrit plus haut.

## Amendement du 2026-09-08 (cloture s31) - la dette est payee, et la preuve du mecanisme d'exemption change de source

**Ce qui est arrive, dans l'ordre.** `camille-po` a corrige les deux lignes de la matrice
(`eecb331f`, `SCI-CM1-02` et `SCI-CM1-12` dans le meme commit) ; `sofia-sm` a fusionne mon commit en
second et a paye la dette - `DETTE_DE_CELLULES` vide, `PLAFOND_DE_DETTE = 0`. **Le rouge annonce en
section 4 est arrive**, il a ete paye la ou il devait l'etre, et le plafond ne remonte pas : la dette
est **payee**, pas reduite - trois cas sur trois corriges, dont `ADR-index.md` par moi dans le commit
qui a cree la regle.

**Et il a casse une troisieme clause, que mon annonce n'avait pas prevue.** La clause qui prouvait que
l'exemption est une exemption et non un perimetre s'executait sur le contre-exemple **avec la dette
vivante en argument**. Dette vide, elle n'exempte plus rien : elle est devenue rouge **sans avoir rien
detecte**, ayant seulement perdu l'entree qui lui donnait un sens. Etat mesure par `sofia-sm` sur le
reacteur complet : 186 fichiers `TEST-*.xml`, 1 396 tests, **1 echec**, celui-la et lui seul.

### Ce que je tranche, et le motif

**Les noms de cette clause sont desormais fournis par la clause elle-meme** (`NOMS_DU_CONTRE_EXEMPLE`,
deux noms figes), et non par la dette vivante. **Deux proprietes, deux clauses, deux materiaux** :

| Propriete | Clause | Materiau |
|---|---|---|
| Quel defaut le depot porte **aujourd'hui**, et quelles exemptions designent encore quelque chose de reel | le cliquet | le depot reel |
| Ce que le **mecanisme** d'exemption fait d'un nom qu'on lui donne | la clause de mecanisme | l'extrait de l'historique, fige |

**Motif** : le mecanisme est une propriete de la fonction `(racine, dette)`, pas de l'etat du depot.
Les faire dependre du meme argument couplait deux choses qui ne varient pas ensemble, et le couplage
ne s'est vu que le jour ou la dette est passee a zero. **Une preuve de mecanisme qui ne vaut que
pendant que le depot est en dette ne vaut plus rien le jour ou il n'y est plus - c'est-a-dire
exactement quand le garde-fou doit encore etre digne de confiance.**

**Le defaut que `sofia-sm` voyait sans savoir le peser - un jeu de noms fige rend la preuve
insensible a la dette reelle - est reel, et c'est le but, pas un effet de bord.** Ce qui rendrait
cette clause decorative n'est pas l'origine de ses noms : ce serait qu'elle passe alors que le
mecanisme est casse. Elle ne peut pas, parce que **la preuve est un couple sur le meme materiau** :
avec un jeu de noms vide, le contre-exemple est **rouge** en nommant les deux lignes ; avec les deux
noms, il est **vert**. Chaque moitie garde ce que l'autre ne garde pas.

**Prouve par mutation, pas affirme** (`bootstrap`, JDK que la machine virtuelle des tests rapporte :
21.0.10) :

| Mutation | Effet attendu | Mesure |
|---|---|---|
| L'argument d'exemption cesse d'etre honore (le filtre ignore `dette`) | la clause de mecanisme rougit | rouge, `lExemptionEstUneExemptionEtNonUnPerimetre` |
| Le jeu de noms fige perd un nom en silence | la clause de mecanisme rougit | rouge, sur le compte de lignes en surplus de l'extrait |
| Le materiau fige cesse de porter le defaut | **les deux moities** rougissent | rouge, la clause de mecanisme **et** la morsure |

**Deux choses de plus, dans le meme geste.**

1. **Un defaut de mon message d'echec, trouve par `sofia-sm` en payant la dette.** Il prescrivait
   `PLAFOND_DE_DETTE - 1`, calcule nom par nom dans une boucle qui s'arretait au premier echec :
   quand les deux noms tombent ensemble - ce qui est arrive - il prescrivait **1** la ou la reponse
   etait **0**. L'assertion porte desormais sur l'ensemble des noms encore vivants, donc la valeur
   prescrite est juste quel que soit le nombre de noms qui tombent. C'est la meme famille que le
   chiffre sans methode : une prescription juste dans le cas qu'on a en tete et fausse dans le cas
   general.
2. **Ce que le cliquet examine maintenant, dit plutot que suppose** (`ADR-0051`) : la moitie *"une
   exemption ne pourrit pas"* porte sur **zero objet**, la dette etant vide. Ce qui continue de
   mordre est l'egalite elle-meme - **le depot porte exactement zero ligne en surplus** -, et c'est
   la clause la plus forte des trois : sans exemption possible, la premiere ligne invisible qui entre
   est rouge.

**Ce que je ne change pas** : `PLAFOND_DE_DETTE` reste a **0**, la Javadoc de `sofia-sm` reste - elle
est exacte, elle date son geste et elle n'attribue rien a personne a tort -, et le nombre de clauses
reste **cinq**. Aucune propriete n'est perdue par cet amendement, une seule change de materiau.

## Impact

- `bootstrap/src/test/java/com/recreo/archi/AucuneLigneDeTableauNePortePlusDeCellulesQueSonEnTeteTest.java` : cree ; amende le 2026-09-08 (le jeu de noms du contre-exemple, la clause de mecanisme, l'assertion et le message du cliquet).
- `bootstrap/src/test/resources/contreexemple/AucuneLigneDeTableauNePortePlusDeCellulesQueSonEnTeteTest/extrait-matrice.md` : cree.
- `squad/decisions/adr/ADR-index.md` : ligne `ADR-0087` ajoutee ; **et la ligne `ADR-0063` corrigee**,
  son amendement du 2026-08-29 etant invisible au rendu pour la meme cause.
- Aucun fichier de `squad/programme/` modifie par cette decision.
