# Lecture de `nadia-prof` - le diff de l'accord de `ce1-qlm-generations` (QLM-CE1-12) et le titre de son entree de catalogue

Date : 2026-10-29. Stories : `S57-QLM-CE1-12-ACCORD`, critere 3, et `S57-QLM-CE1-ENTREES-CATALOGUE`, critere 2
(`docs/backlog/backlog.md` ; `DEC-0120` A.290). Ce fichier tient la condition que j'ai ecrite dans
`lecture-nadia-accord-ce1-qlm-generations.md`, section 3 (ce dossier) : lire le `git diff` du commit de `marc-dev` avant
`verify`.

Lu : `git diff 3b32a74c f7f9f443` en entier (commit `f7f9f443`, `[MARC]`, branche `s57-p5-marc-12-accord`, non fusionnee, lue
sans la checkout) ; le rapport de `marc-dev` (`git show 376c3866:` suivi du chemin de
`rapport-marc-dev-palier-5-accord-ce1-qlm-generations.md`, ce dossier) ; `releve-relecteur-palier-5-ce1-qlm-generations.md` ;
`avis-theo-palier-5-garde-g12-3-accord-ce1-qlm-generations.md` ; le paquet a `f7f9f443` en entier ; `FICHE-ce1-qlm-12.md`,
section 1 ; les entrees de questionner le monde CE1 de `catalogue.yaml`.

Ce fichier ne modifie ni le paquet, ni le test, ni le jeu fautif, ni le catalogue, ni la matrice, ni le backlog, ni la fiche,
ni mes ecrits existants. Aucun Done nouveau, aucun verdict d'item rouvert, aucun identifiant alloue.

## 1. Mes trois conditions, lues sur le diff

`git diff --numstat 3b32a74c f7f9f443` : trois fichiers, le paquet (5 lignes ajoutees, 5 retirees), la classe
`PaquetCe1QlmGenerationsTest` (18, 11) et le jeu fautif `generations-fautives.yaml` (24, 14). Dans le paquet,
`git diff -U0` rend quatre blocs : lignes 25, 53 et 54 (commentaire), 96 et 131 (enonces). Comparaison ligne a ligne des deux
versions du paquet sous Python (162 lignes de part et d'autre) : lignes differentes 25, 53, 54, 96, 131, aucune autre.

| Condition | Tenue | Preuve |
|---|---|---|
| 1. Seules les lignes `enonce:` des items 3 et 8 changent, egales a l'octet au texte de la section 3 du releve | **Oui** (ecart d'espace tranche ci-dessous) | Lignes 96 et 131 seules parmi les lignes d'items. Script : le texte de la section 3 du releve, espace avant *?* remplacee par `U+00A0`, est retrouve a l'identique a la ligne 96 (item 3) et a la ligne 131 (item 8) ; sans ce remplacement, aucune egalite |
| 2. Item 11, propositions, bonnes reponses et indices inchanges | **Oui** | Aucune ligne d'item hors 96 et 131 ne differe ; l'item 11 (ligne 152) et les indices `:99`, `:134`, `:155` sont hors des blocs du diff |
| 3. Hors du paquet, le test et, si `theo-archi` le demande, le commentaire d'en-tete du paquet | **Oui** | Hors du paquet : la classe de test et son jeu fautif, rien d'autre. Commentaire d'en-tete : voir ci-dessous |

**L'espace avant *?*.** Lu a l'octet (`cat -A` sur le diff) : les deux enonces corriges portent `c2 a0` avant *?*, soit
`U+00A0`. Le fichier du releve porte `U+0020` a cet endroit dans sa table de la section 3 (code `0x20`, lu sous Python), alors
que sa propre phrase de controle annonce `U+00A0` et dit que seul le groupe *du plus âgé au plus jeune* change. Les douze
enonces du paquet a `f7f9f443` portent tous `U+00A0` avant *?*, aucun `U+0020` (compte sous Python), et le commentaire d'en-tete
du paquet (`TYP-1`, `ADR-0067`) exige l'insecable. **L'ecart est conforme a ma condition.** J'ai ecrit *"espace insecable avant
*?* compris"* dans la condition elle-meme : c'est l'insecable que j'exigeais, et le releve juge l'accord, pas l'espace. Le
`U+0020` du releve est une perte a la recopie dans le fichier du releve, pas un texte propose a l'enfant ; il n'est pas a moi de
le corriger.

Correction de mon propre constat, avant de m'y appuyer : ma table de `lecture-nadia-accord-ce1-qlm-generations.md`, section 2,
compte 18 mots pour les items 3 et 8 en annoncant l'insecable. Sur le texte brut du releve, `split(' ')` rend 19, parce que son
espace avant *?* est ordinaire. Sur le texte du paquet, avec l'insecable, c'est 18 : mon chiffre vaut pour le texte servi, et
la longueur en caracteres (93 et 94) est la meme dans les deux cas.

**Le commentaire d'en-tete.** Trois lignes de commentaire changent : la ligne 25 (*"Generations, de la plus agee a la plus
jeune : ..."*) et les lignes 53 et 54 (la phrase *"L'enonce dit le sens : ..."*, recoupee sur deux lignes). Ce sont les citations
`L3` et `L4` de l'avis de `theo-archi` (section 4), qui en donne le texte et ecrit *"mon avis est de les changer"*. Les deux
textes du diff sont ceux de l'avis, a la recoupe de ligne pres. **Elles sont dans ce que j'ai autorise** : ma condition 3
admettait le commentaire d'en-tete si `theo-archi` le demandait, et il le demande. Aucune autre ligne de commentaire du paquet ne
change, et aucune n'est servie a l'enfant.

Ce qui n'est pas a moi : `theo-archi` renvoie a `camille-po` (son point `L9` bis) la question de savoir si ces trois lignes sortent
du `git diff` que le critere 3 de la story autorise a la lettre. Je n'ai trouve aucune trace ecrite de sa reponse (recherche de
*"L9 bis"* dans `squad/` et `docs/`, seul l'avis de `theo-archi` repond). Ma condition est tenue quelle que soit cette reponse ;
la lecture du critere de la story est a `camille-po`.

**Le jeu fautif, compte dans "le test".** Je lis le jeu fautif comme une partie du test, comme `theo-archi` (`ADR-0013`,
obligation 3) : c'est le contre-exemple de la garde, il n'est servi a aucun enfant. Il change de quinze enonces : les treize
enonces `gen` passent a *de la plus âgée à la plus jeune* (liste de l'avis, section 2.4, `J2`), et deux sondes neuves (74 et
75) sont ajoutees en fin de fichier, avec l'en-tete du jeu complete. Quatorze lignes ajoutees portent la forme feminine : les
treize enonces `gen` et la sonde 75.

## 2. Le jeu fautif et la table fermee, lus pour le sens

Mon texte de `R12-2` B (`lecture-nadia-accord-ce1-qlm-generations.md`, section 1) admet trois formes, chacune pour son groupe.
La table `SENS_DU_GROUPE` du test les porte une a une :

| Groupe que la suite range | Forme que j'ai ecrite | Constante du test, lue sur l'enonce mis a plat |
|---|---|---|
| trois générations (`gen`) | *de la plus âgée à la plus jeune* | `"de la plus agee a la plus jeune"` |
| trois prenoms de l'enonce (`pre`) | *du plus âgé au plus jeune* | `"du plus age au plus jeune"` |
| la suite d'evenements admise (`evt`) | *du premier au dernier* | `"du premier au dernier"` |

**Les trois formes sont les miennes, mot pour mot.** Les constantes sont sans accent parce que la garde lit le texte mis a plat ;
les mots et leur ordre sont les miens.

**Ce que la garde refuse, contre ce que j'ai ecrit.** J'ai demande qu'elle refuse un enonce B qui n'ecrit pas la forme de son
groupe, y compris un enonce qui ecrit la forme d'un autre groupe :

- un enonce sans aucune des trois formes : refuse par la premiere lecture (*"n'écrit aucun des trois sens"*), sonde 17 ;
- des générations rangees au masculin : refuse par la seconde lecture, sonde 74, fragment `pas celui de son groupe (gen)`. Son
  enonce est celui des items 3 et 8 avant correction, a l'identique pour l'item 3 : la garde refuse exactement la faute du
  releve ;
- des prenoms ranges au feminin : refuse, sonde 75, fragment `pas celui de son groupe (pre)`. Son enonce est celui de l'item 11
  avec la forme feminine : la forme des générations ne fuit pas vers les prenoms ;
- une vie rangee avec une forme d'age : toujours refuse, sonde 26, inchangee.

Le premier cas de la colonne *"Doit etre refuse"* (un distracteur inverse exact, sondes 16 et 18) et la colonne *"Doit passer"*
sont inchanges, comme je l'avais ecrit.

**Un cas que la garde ne lit pas, et que je ne demande pas d'ajouter.** La seconde lecture verifie que l'enonce *contient* la
forme de son groupe. Un enonce qui ecrirait sa forme **et** celle d'un autre groupe passerait. Aucun item du paquet ne le fait,
et ma phrase *"un enonce qui ecrit la forme d'un autre groupe"* visait un enonce qui l'ecrit a la place de la sienne, ce que les
sondes 74 et 75 couvrent. Je ne releve pas ma barre apres coup : ce cas reste a ma lecture, pour tout item neuf de la tache
ordre.

**Ce dont je reste la seule garde** : qu'un enonce n'ecrive qu'une forme de sens ; que le groupe d'une suite soit celui que
l'enfant lit (la garde le deduit des elements de la bonne reponse). Les accents de la forme (*âgée*, *à*) ne sont lus par aucune
garde, puisque le texte est mis a plat : ils sont au `relecteur-pedagogique`, comme la grammaire de toute forme neuve. Je
n'ajoute rien a ce qui est ecrit a la fiche, section 5, sur ce point.

## 3. Le titre de l'entree de catalogue, lu comme un CE1

**Ce que le paquet fait.** Douze items, deux moities (`GEN-CE1-1`) : sept sur les générations d'une famille (items 1, 3, 4, 6,
8, 9 et 11 : de quelle generation est un personnage, ranger trois générations ou trois prenoms) et cinq sur les moments d'une
vie (items 2, 5, 7, 10 et 12 : quel moment vient juste avant ou juste apres, ranger trois moments). La competence ecrite au
descripteur nomme les deux : *"les générations d'une famille [...] et quelques événements de la vie d'un personnage"*. Le mot
que l'enfant lit dans les enonces des cinq items de vie est *moment* (*"quel moment vient juste après"*, *"range les moments"*).

**La proposition de `marc-dev`, *"Les générations d'une famille"*.** Elle ne promet rien de faux, mais elle n'annonce que la
moitie du jeu : un enfant qui l'ouvre lit, sur environ quatre manches sur dix, *"Dans la vie de Sara, quel moment vient juste
après sa naissance ?"*, que le titre ne lui a pas fait attendre. `marc-dev` le note lui-meme. Elle partage aussi le mot
*famille* avec *"Les familles d'aliments"*, sur la meme page : deux titres de questionner le monde CE1 qui commencent par
*Les* et portent *famille*, pour un lecteur qui reconnait les mots par leur debut. Je ne la retiens pas.

**L'alternative, *"La famille et les âges de la vie"*.** Elle nomme les deux moities, mais avec deux mots que le paquet ne
travaille pas : *âge* invite un nombre, et le paquet n'en a aucun (`R12-4`) ; *les âges de la vie* designe d'ordinaire
l'enfance, l'age adulte, la vieillesse, alors que le paquet range des moments (naissance, ecole, college, premier emploi). Elle
garde *famille*, avec le meme risque de confusion. Je ne la retiens pas.

**Titre a retenir, mot pour mot :**

> Les générations et les moments de la vie

Pourquoi : il nomme les deux moities avec les deux mots que l'enfant lit dans les enonces, *générations* et *moments* ; il ne
promet ni nombre, ni frise dessinee, ni famille de l'enfant (`BIA-4`) ; il ne partage aucun mot porteur avec les neuf titres
listes de `catalogue.yaml` (*Grandir chez les animaux et les plantes* parle de grandir, pas de moments ; aucun de ces titres ne
porte *générations* ni *moments*). Huit mots courts, une ligne, un de plus que *"Grandir chez les animaux et les plantes"*.
*Générations* est le mot le plus long ; il est lu, au singulier ou au pluriel, dans six enonces du paquet (items 1, 3, 4, 6, 8
et 9), et c'est le mot que le jeu apprend. Il prolonge le titre de ma sonde
(*"Les générations"*, fiche, section 1).

**La consigne commune, *"Lis bien, puis choisis la bonne réponse."*, avec 10 manches.** Elle convient. Les douze items sont des
questions ecrites a trois propositions, sans image, sans voix, sans texte support (en-tete du paquet, `TRV-CE1-1`) ; la tache
ordre propose des suites, et *choisis la bonne réponse* reste exact pour une suite. Dix manches sur douze items tiennent
`FER-2` (plus d'items que de manches), comme les huit entrees de questionner le monde CE1 deja au catalogue, toutes a 10 manches et a
cette consigne dans `catalogue.yaml` (lu sous Python).

Ce titre et cette consigne sont la piece du critere 2 de `S57-QLM-CE1-ENTREES-CATALOGUE` pour la ligne `QLM-CE1-12` : le commit
de ce fichier doit etre ancetre du commit de l'entree. L'orthographe du titre est au `relecteur-pedagogique` (critere 4), pas a
moi. Le titre et la consigne seront joues sur le jar avec l'entree reelle, au geste que `sofia-sm` sequence ; ils ne le sont pas
ici.

## 4. Cout, et ce que cela ouvre pour `verify`

**Rang.** Cette lecture du diff est la **troisieme lecture de `-12`**, comme je l'ai ecrit
(`lecture-nadia-accord-ce1-qlm-generations.md`, section 3) : elle tient la condition de cette lecture, elle n'en ouvre pas une
autre. Je n'en fixe pas le prix (`DEC-0120` A.272, regle point 3 ; A.283 pour son classement, qui n'est pas a moi). La lecture
du titre est celle du critere 2 de la story d'entrees, deja prevue par A.290 ; je ne la chiffre pas non plus.

**Condition pour `verify` : satisfaite.** Le diff de `f7f9f443` tient mes trois conditions ; l'ecart d'espace est conforme ; les
trois lignes de commentaire sont dans ce que j'ai autorise ; la table fermee porte mes trois formes et refuse ce que j'ai demande
de refuser. **Je n'exige aucune correction** : aucune quatrieme lecture n'est due. Un commit qui changerait encore une ligne
servie a l'enfant avant `verify` la rendrait due, et je ne la chiffre pas.

**Ce que cela ne fait pas.** Le Done de `-12` reste **VALIDE SOUS RESERVE** (2026-10-27), reserve `V1` inchangee ; aucun verdict
d'item n'est rouvert. L'ajout date que j'ai annonce a la fiche (*"l'accord est corrige et le diff est conforme"*) n'est pas ecrit
ici : il s'ecrit a `FICHE-ce1-qlm-12.md`, sous mon bloc et sans le reecrire, apres la fusion de `f7f9f443`, au geste que
`sofia-sm` designe. `verify` reste a lancer apres fusion ; je ne l'ai pas lance et ne juge pas la porte technique.

**Signe** : `nadia-prof`, s57, palier 5, 2026-10-29.
