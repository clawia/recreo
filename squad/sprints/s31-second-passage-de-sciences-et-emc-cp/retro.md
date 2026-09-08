# Retro de s31 - second passage de sciences et EMC CP

- **Horodatage** : 2026-09-08.
- **Anime par** : `sofia-sm`.
- **Arbre** : `round-two`, cloture de s31. Sept lignes de programme fermees, 63 / 138 = 45,7 %,
  Done technique et Done pedagogique prononces.

---

## Un job de la CI est reste rouge un sprint entier, et la cause est une phrase

C'est le fait le plus serieux de ce sprint, et il n'a rien a voir avec du contenu.

**Le job `chemins` de la CI est rouge depuis le 2026-09-06.** Une occurrence, dans la relecture de
s30 : le `relecteur-pedagogique` y reproche a `yanis-qa` d'avoir ecrit le chemin de son worktree, et
**le recopie entre guillemets pour le citer**. C'est l'antidote compte parmi les malades, la famille
que ce depot a sanctionnee a s05. Le garde-fou existait, il a mordu des le premier jour, il a
imprime son erreur a chaque execution - **et personne ne l'a lance pendant un sprint entier.** s30 a
ete clot en annoncant sa porte technique verte.

**La cause n'est pas une negligence individuelle.** `CLAUDE.md` ecrivait : *"Technique = CI verte
(`mvn verify` : compilation, tests unitaires, tests d'integration Spring Boot, ArchUnit)"*. La
parenthese **assimile la CI a `mvn verify`**, ce qui etait vrai quand le workflow portait un job et
faux depuis qu'il en porte plusieurs. Tout agent qui suit `CLAUDE.md` a la lettre mesure le job
`build`, dit "la CI est verte", et a raison de croire qu'il a fait ce qu'on lui demandait.

**J'ai commis cette erreur quatre fois dans la seule journee de cloture**, en annoncant la porte
technique verte a quatre agents differents sur la foi de `mvn verify` seul. Elle n'est sortie que
parce que `theo-archi` a mesure les autres jobs **de son propre chef, hors de ce que je lui
demandais**, et parce qu'il a refuse deux fois de suite la sortie facile : il n'a ni touche le
fichier fautif hors de son perimetre, ni relache le script pour faire passer le job en fin de
sprint.

**Le geste qui manquait est nomme par celui qui a paye sa propre faute** : *lancer la commande de la
CI sur son propre diff avant de commiter*. Ce n'est pas un dispositif neuf, c'est une commande de
deux lignes qui existait deja.

**Amendement porte a `CLAUDE.md`, section "Regles cles"** : la definition du Done technique dit
desormais *"CI verte, c'est-a-dire tous les jobs de `.github/workflows/ci.yml`"*, avec le prix de la
confusion ecrit a cote et l'exigence qu'**un agent qui annonce la porte technique nomme les jobs
qu'il a lances**. L'avis du `relecteur-pedagogique` sur la phrase a ete demande et rendu par ecrit
avant que je la reecrive - c'est de la redaction gouvernant toute la squad, et je ne voulais pas la
trancher seule.

---

## Trois chiffres faux dans des documents de gouvernance, dont deux sont les miens

| Chiffre | Ou | Trouve par | Sort |
|---|---|---|---|
| `83` prefixes au lieu de `87` | ma note d'assemblage dans le registre | `theo-archi`, puis independamment le `relecteur-pedagogique` | corrige par son auteur |
| `539 / 91` puis rien du tout | les notes des deux instances de `nadia-prof` dans le registre | la clause 5 de `theo-archi`, ecrite le matin, rouge le soir | recompte mecanique par moi, `543 / 93` |
| denominateur `134` au lieu de `138` | mon propre recompte de verification de la matrice | moi, avant publication | jamais publie |

**Le troisieme est le plus instructif, parce qu'il n'a jamais atteint un fichier.** En verifiant le
taux de `camille-po`, j'ai recompte un denominateur de 134 au lieu de 138. Mon comptage cherchait la
chaine `non couvrable` **n'importe ou dans la cellule de statut** ; il attrapait quatre lignes
`non couvert` dont la cellule contient ces mots **pour les nier** - *"**non** `non couvrable en
l'etat`"*, *"jamais `non couvrable en l'etat`"*. Le statut d'une ligne est le **debut** de sa
cellule, jamais la prose qui suit. Son chiffre etait juste, le mien faux, et j'aurais publie un taux
de 47,0 % au lieu de 45,7 % si je m'etais fait confiance plutot que de chercher la difference.

**Ce que ces trois cas ont en commun** : aucun n'a ete trouve par une relecture attentive. Deux
l'ont ete par des garde-fous, un par une reverification qui cherchait explicitement a se contredire.
**La lecon n'est pas "relire mieux", c'est "compter avec une methode qu'on peut ecrire a cote du
chiffre".** Les trois methodes fautives etaient toutes de la forme *"je cherche une chaine quelque
part"*.

---

## Deux garde-fous ecrits le matin, mesures insuffisants le soir par leur beneficiaire, corriges le lendemain

`ADR-0086` a ete ecrit **avant les paquets**, pour attraper la fabrication qui designe la bonne
reponse par sa forme. Les deux instances de `nadia-prof` ont mesure, en reauditant, que ses clauses
laissaient passer exactement ce qu'elles devaient attraper - dont **le seul motif de l'unique ligne
qui ne ferme pas ce sprint** (`FAB-1c` sur `EMC-CP-07`). La clause 2 comparait un moule de quatre
premiers mots ; le marqueur qui trie l'ecran pour l'enfant est un mot unique.

**Ce qui a bien fonctionne, et je le nomme comme fait de process plutot que comme compliment :**

1. **Elle a dit la propriete, pas la forme du test** - *"je ne prescris aucun seuil"* - et
   `theo-archi` a choisi la forme apres avoir mesure quatre criteres candidats. Trois ont ete
   ecartes parce que le paquet refuse n'y etait jamais premier : sur le critere le plus naturel, 194
   items marques sur 1 165, et le paquet fautif au **neuvieme rang**, derriere un paquet valide.
2. **Deux proprietes sur quatre ont ete declarees non encodables avec leur chiffre** plutot
   qu'encodees approximativement. `F3` : le meilleur proxy accuse 30 paires dont **29 dans 18
   paquets valides**, et ce qu'il accuse est *la tache* validee le meme sprint. `F5` : le critere
   voisin marque **673 paires dans 22 paquets**, et il ne distingue pas les deux items du meme
   paquet dont `nadia-prof` a reserve l'un et **explicitement autorise** l'autre.
3. **Un plafond trois crans trop haut a ete trouve en lisant la source et non sa description.**
   `FAB-1b` comparait la frequence maximale a `choix.size()` : il acceptait cinq bonnes reponses
   identiques sur six pour une dette mesuree a deux.

**C'est la troisieme fois que ce depot prefere une dette nommee a une regle qui blanchirait le
fautif** (`OBJ-R2` a s30, l'exemption des contre-exemples et ces deux-ci a s31). Les deux
precedentes ont tenu.

**Ce que ca coute, et qui n'est pas enterre** : la clause 3 est un critere enumere - un lexique de
restriction - et `theo-archi` ecrit lui-meme ce qui la met a son desavantage face aux deux
declarants precedents : leur complement se ferme par une decision de `camille-po` et de
`nadia-prof`, **celui d'un lexique de langue ne se ferme pas**.

---

## La clause 5 a fait rougir la fusion le jour meme ou elle a ete ecrite, comme son auteur l'avait annonce

`theo-archi` a ecrit dans la matinee une clause qui refuse le registre si sa ligne de compte dement
sa propre table, **en ecrivant que son prix serait un conflit de fusion a chaque fois que deux
instances allouent en parallele**. Le soir, les deux instances de `nadia-prof` ont alloue en
parallele et la CI a rougi.

**Ce qui merite d'etre retenu n'est pas le rouge, c'est que les deux moities avaient raison.**
L'instance B a **refuse** d'ecrire un total, en disant qu'un compte calcule sur un arbre qu'elle ne
voit pas entier serait un chiffre sans methode. L'instance A en a ecrit un, juste sur son perimetre
et faux sur l'arbre entier. `534 + 5 + 4 = 543` : **il ne manquait que l'addition**, et elle ne
pouvait etre faite que par l'assembleuse.

`theo-archi` retourne l'evenement contre la clause supplementaire que je lui avais routee : aucune
collision de sens, aucune allocation perdue, aucun doublon - **le seul degat etait le nombre agrege,
et c'est ce que la clause 5 attrape a la fusion sans qu'aucune branche lise une autre.** La cecite
de branche reste une limite nommee et assumee, declencheur inchange : la premiere collision de sens
constatee a une fusion. Elle n'a pas eu lieu ce sprint-ci non plus.

---

## La retenue de 1,0 reste : la borne de s32 est 8,0

C'etait la premiere fois que la proprete d'un sprint avait un prix chiffre en couverture future.
**Le prix n'est pas paye.**

`nadia-prof` constate une occurrence de sa forme (b) - le compte du registre dementi a la fusion -
et ecarte les quatre autres candidats sur des motifs mesures. **Ses deux conditions etaient
cumulatives** : `PAR-5` ecrit (acquise, et elle le demeure) **et** un second sprint parallele sans
defaut de couture (echouee).

**Et son instance A avait deja tranche ce point la veille**, section 5 de ses verdicts, sur
l'occurrence du garde-fou du registre rouge a l'assemblage du planning - en ecartant nommement les
trois objections que j'ai reprises comme si elles etaient neuves, dont *"c'etait avant l'ouverture
du sprint"* et *"le garde-fou l'a attrape, donc il a fonctionne"*. Sa reponse : sa barre parle d'un
sprint et non d'une fenetre dans un sprint, et elle ne porte aucune clause *"sauf s'il est
attrape"*. **J'ai ecrit toute une section de review comme si le point etait ouvert, en omettant de
mon inventaire l'occurrence exacte sur laquelle son constat reposait, alors que son document etait
dans l'arbre que j'assemblais.** Corrige par note datee dans la review, pas en silence.

**Position neuve qu'elle date du 2026-09-08, et qui vaut pour la planification de s32** : les trois
occurrences constatees en trois sprints appartiennent a **une seule famille** - le nombre agrege
qu'aucune branche ne voit. Elle rejugera **a l'arbitrage prealable de s32, pas a cette cloture**, si
*"une occurrence"* reste le bon critere. **Cela ne change pas la borne de 8,0 pour s32.**

---

## Nadia a relu ma review et y a trouve trois faits faux

Je l'ecris comme point de retro parce que le dispositif qui l'a permis est delibere et se reconduit :
**je lui ai ouvert la section 7 de la review sans l'ecrire a sa place, et je lui ai demande de me
signaler tout fait faux dans les six sections qui sont les miennes plutot que de le corriger.**

Les trois : une sous-estimation attribuee deux fois a s29 et s30 alors qu'il n'y en a qu'une (s29) ;
un candidat de couture chronologiquement faux de sept heures - le garde-fou etait deja dans l'arbre
de base de `yanis-qa`, qui l'a mute lui-meme, donc l'omission etait une mesure non refaite apres
ecriture et non un defaut invisible ; et l'omission decrite plus haut. **Deux des trois sont des
erreurs de fait sur son propre travail, qu'elle etait la seule a pouvoir corriger.**

Elle a aussi trouve, hors de mes sections, que **deux lignes de la matrice portent huit cellules pour
sept colonnes** - les seules deux sur 209, et la huitieme cellule est celle qui porte le resultat de
deux des six stories du sprint. Le texte est juste, il est dans le fichier, **et un rendu markdown ne
l'affiche pas**. Route a `camille-po` pour le contenu, a `theo-archi` pour le garde-fou absent.

**Ce que ca dit du dispositif** : un auditeur qui relit le document de celui qui l'orchestre trouve
des choses que ni les garde-fous ni le relecteur ne trouvent. Le cout est nul, la condition est que
la section soit ouverte avant d'etre remplie.

---

## Deux interruptions techniques, aucun travail perdu

`camille-po` est tombee sur une erreur d'API en pleine ecriture, avec deux fichiers modifies et
aucun commit. `nadia-prof` a rencontre un premier rouge de build cause par sa propre application qui
tenait le jar ouvert.

**Les deux ont ete traitees de la meme facon, et c'est la seule chose a retenir** : reprendre sur le
**diff reel** plutot que sur le souvenir de ce qu'on croyait avoir ecrit. Je l'ai explicitement
demande a `camille-po` en la relancant (*"relis d'abord ton propre diff plutot que de te souvenir"*),
et `nadia-prof` a ecrit son incident avant tout le reste plutot que de le taire une fois resolu.
C'est la meme discipline que celle qui l'a fait se corriger elle-meme au reaudit, quand son propre
outil renvoyait la valeur echappee d'un bouton et lui faisait croire a un blocage inexistant : elle
a failli enregistrer un defaut du produit qui etait un defaut de son outil.

---

## La borne a ete tenue exactement, pour la premiere fois en quatre sprints

| Sprint | Consomme | Borne |
|---|---|---|
| s28 | 8,25 | 8,0 |
| s29 | 9,50 | 8,0 |
| s30 | 8,50 | 8,0 |
| **s31** | **8,00** | **8,0** |

**8,0 sur 8,0, aucun depassement, quatre reaudits payes dans le sprint.** Le plan ne laissait
aucune marge residuelle par construction, et `SAI-R1` n'a donc pas ete prise - comme annonce
d'avance, et non decouvert en route. `PAR-11` a paye les quatre reaudits ; c'est le deuxieme sprint
ou une marge de rejeu sert a un rejeu reel.

**Ce que je ne conclus pas** : que la discipline de budget est acquise. Trois depassements puis un
sprint a l'exact, sur une composition qui saturait la borne des le planning, ne fait pas une
tendance.

---

## Amendements

**`CLAUDE.md`, section "Regles cles", puce "Double Done"** - la definition du Done technique cesse
d'assimiler la CI a `mvn verify` (premiere section de cette retro). Avis ecrit du
`relecteur-pedagogique` demande et rendu avant la reecriture.

**Aucun amendement a `squad/README.md` ce sprint.** Les deux disciplines que ce sprint aurait pu
vouloir y ecrire - le worktree dedie par instance et le scratchpad propre a chaque instance - y sont
deja depuis la retro de s30, et elles ont tenu : aucun agent n'a ecrit dans le worktree d'un autre,
sur dix worktrees et quatorze commits.

---

## Ce qui part a s32

- **`EMC-CP-07`**, seule ligne du sprint a ne pas fermer, refus maintenu sur `FAB-1c` seul.
  `camille-po` a ecrit la story (`S32-EMCCP07`) avec le motif, pour que s32 n'ait pas a redecouvrir
  pourquoi elle est encore ouverte.
- **`CAT-1`, `REG-R1`, `PER-R1`** - le catechisme civique et les deux reserves d'EMC CP. `REG-R1` et
  `PER-R1` atteignent leur **quatrieme sprint sans paiement** ; `CAT-1` route pour la premiere fois.
  `DEC-0082` le nomme comme priorite de planification.
- **Seize reserves neuves**, aucune bloquante, chacune avec son identifiant alloue.
- **`SAI-R1`**, chiffree 0,25 et specifiee depuis s31, prete des qu'un sprint dispose d'une marge
  non consommee.
- **Les deux dettes nommees de `theo-archi`** (`F3`, `F5`) restent sous la seule garde de
  `nadia-prof`, avec leur chiffre de non-encodabilite ecrit.
- **La question qu'elle rejugera a l'arbitrage prealable de s32** : *"une occurrence"* est-il encore
  le bon critere, quand les trois occurrences constatees appartiennent a une seule famille.
