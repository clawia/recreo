# DEC-0054 - Deux branches locales non fusionnees (`hs-nadia-arbitrages`, `s20-ines-2`) : contenu
# verifie recapture ou rendu sans objet dans `round-two`, abandon des deux

- **Type** : gouvernance - sort de branches non fusionnees
- **Date** : 2026-08-29
- **Auteur** : camille-po
- **Sprint** : s22
- **Competence programme visee** : transverse - pas de contenu pedagogique. Cette decision statue
  sur le sort de deux branches Git, pas sur une competence du programme. La competence que la
  premiere branche concerne ("Reperer et corriger la majuscule et la ponctuation dans une phrase
  donnee...") est deja suivie par sa propre ligne de `squad/programme/MATRICE-couverture.md`,
  inchangee par cette decision.
- **Statut** : Acceptee

## Contexte

`sofia-sm` a nettoye les 27 worktrees du depot a la cloture de l'ouverture de s22 ; il ne restait
que le checkout principal. Deux branches locales ne survivent pas a `origin` et ne sont pas
ancetres de `round-two`, verifie par `git merge-base --is-ancestor` :

- `hs-nadia-arbitrages`, tete `b611f1b` (parent `77dd467`) ;
- `s20-ines-2`, tete `7b0b5e5` (parent `77dd467`).

Meme methode qu'a `DEC-0038` (s17) : une branche non fusionnee ne veut pas dire un travail perdu -
un commit peut avoir ete recapture ailleurs, sous un autre hash, tout en portant le meme contenu.
J'ai verifie chacune commit par commit, par `git diff` et par lecture des fichiers, avant de
statuer - je ne suppose rien.

## 1. `hs-nadia-arbitrages` (`b611f1b`)

**Ce qu'elle porte, fichier par fichier.** Un seul commit, date du 2026-08-26 17:53:41 : une
reaudition de `cm1-reperes` par `nadia-prof`, sur l'increment corrige par `lea-dev` (`77dd467`).
Trois fichiers, 1266 insertions : `squad/conformite/FICHE-bandit-manchot-reperes-cm1.md` (+155
lignes), une capture d'ecran (`s20-reaudit-reperes-744x551-echec.png`) et un releve de mesure JSON
(`s20-reaudit-reperes-744x551.json`, 1111 lignes). Le verdict qu'elle porte : **VALIDE SOUS
RESERVE**, avec une reserve nommee **`REP-7` bloquante** (le texte du verdict integralement sous
le pli a 744 x 551 sur 2 items sur 16, mesure a 13,19 px manquants) et une reserve **`REP-8` non
bloquante** (l'indice jamais lisible apres un echec, 16 items sur 16 - jugee sans consequence
puisqu'un indice totalement invisible ne peut pas se lire a l'envers, doctrine `SYN-12`).

**Recapture, sous quel identifiant, et etat relatif.** Le meme sujet - la reaudition de
`cm1-reperes` sur ce meme increment `77dd467` - existe dans `round-two` sous le commit `c7ad0b2`
(`[NAD] s20: cm1-reperes REFUSE leve en VALIDE SOUS RESERVE, cp-nature-mot VALIDE confirme`, date du
2026-08-27 09:43:44, **posterieur** de seize heures a `b611f1b`, deja fusionne). `git diff b611f1b
c7ad0b2 -- squad/conformite/FICHE-bandit-manchot-reperes-cm1.md` ne rend pas un patch vide : c'est
une reecriture, pas une recopie a l'identique. Ce que la lecture etablit :

- **Meme verdict de fond** (VALIDE SOUS RESERVE), **meme chiffre pivot** (13,19 px manquants sur 2
  items sur 16 pour le texte du verdict), mais **methode et perimetre plus larges** dans `c7ad0b2` :
  outillage dedie (`squad/outils/nadia-prof/auditer-paquet.py`), ressources du jar verifiees par
  `sha256`, et surtout un **balayage du catalogue entier** (17 jeux, 157 items vus le 2026-08-27,
  releve `squad/conformite/releves-s20/s20-pli-du-catalogue-744x551.json`) absent de `b611f1b`.
- **Reclassement de la reserve pivot** : ce que `b611f1b` nomme `REP-7` **bloquante** devient, dans
  `c7ad0b2`, `REP-9` **non bloquante** - meme defaut, meme chiffre (13,19 px, 2 items sur 16), mais
  `nadia-prof` y ajoute un fait qu'elle n'avait pas encore le 2026-08-26 : deux jeux deja
  `couvert-valide` enfoncent l'indice plus bas que ce residu sans qu'elle ne le leur ait jamais
  oppose, et la barre qu'elle exigeait (cinq libelles courts et deux enonces raccourcis a la fois)
  n'etait pas atteignable par ce paquet sans revenir sur sa propre instruction de ne pas toucher aux
  seize phrases. Elle corrige cette instruction dans `c7ad0b2` ("je ne l'interdisais pas") et leve le
  caractere bloquant sur ce seul motif.
- **Les deux fichiers de releve de `b611f1b`** (`s20-reaudit-reperes-744x551-echec.png`,
  `s20-reaudit-reperes-744x551.json`) **n'existent sous aucun nom** dans l'arbre actuel de
  `round-two` (`find squad/conformite/releves-s20 -iname "*reaudit-reperes-744x551*"` ne rend rien) :
  ils sont remplaces, pas recopies, par la campagne de mesure plus large de `c7ad0b2`
  (`s20-reaudit-reperes-corrige-744x551.json` et les fichiers du balayage catalogue).

**Ce qui n'est recapture nulle part.** Rien. Le seul residu propre a `b611f1b` - le verdict
`REP-7` **bloquante** au lieu de `REP-9` **non bloquante** - n'est pas un contenu manquant : c'est
une conclusion anterieure et moins informee de `nadia-prof` elle-meme sur le meme fait, qu'elle a
explicitement revisee le lendemain sur pieces (balayage catalogue), dans le sens d'un
assouplissement et non d'un durcissement. La ligne de matrice correspondante est deja
`couvert-valide` (`squad/programme/MATRICE-couverture.md`, ligne "Reperer et corriger la majuscule
et la ponctuation..."), portee par `DEC-0050` sur la base de `c7ad0b2`.

**Issue : abandon.** Rien a recuperer : `round-two` porte deja, sous un hash different et sous une
forme plus recente et plus verifiee, le meme sujet trace par la meme auteure, avec un jugement final
que je ne rejuge pas.

## 2. `s20-ines-2` (`7b0b5e5`)

**Ce qu'elle porte, fichier par fichier.** Un seul commit, date du 2026-08-27 08:44:44, base sur
le meme parent `77dd467`. Sept fichiers, 2568 insertions :
`adapters/adapter-web/src/main/resources/static/css/recreo.css` (+26 lignes : `.enonce` gagne
`line-height: 1.3`), `squad/outils/ines-design/mesurer-au-glyphe.py` (outil de mesure au pixel de
texte reellement rendu, corrigeant une premiere version fondee sur `Range()` DOM qui donnait un
faux positif sur une ligne unique), quatre releves JSON avant/apres sur `cm1-reperes` et sur les
huit items de `cm1-accords-groupe-nominal` (`AGN-1`), et un document de correctif
(`squad/sprints/s20-mot-cible-designe-et-conception-de-la-saisie/correctif-ines-rep7-line-height.md`).
Motif ecrit du changement : recuperer les 13,19 px manquants du texte du verdict de `cm1-reperes`
(le `REP-7` bloquante de `hs-nadia-arbitrages`, cite nommement et par son hash dans le document),
sans rendre lisible l'indice de `cm1-accords-groupe-nominal` (`AGN-1`) - `1,3` etant, sur balayage
fin, la seule valeur qui tienne les deux contraintes a la fois.

**Recapture, sous quel identifiant.** `.enonce` ne porte aucune regle `line-height` dans `recreo.css`
a ce jour (`grep -n "\.enonce {" -A3` : `font-size`, `font-weight`, `margin-bottom`, rien d'autre) :
**ce changement de code n'a jamais ete fusionne**, sous ce hash ni sous aucun autre. Il n'a pas non
plus ete recapture sous une autre forme : le residu qu'il visait a fermer a ete traite par un
**mecanisme different**, deja fusionne et livre en production - `0e0bc4e`
(`[MARC] s21: S21-2 - leviers de forme REP-8, marge du score et rembourrage vertical (26/56)`,
2026-08-27 16:13:32), qui ajoute `.score { margin: 0.25rem 0 }` et une regle de rembourrage propre a
`.proposition` (`0.5rem 1.25rem`). Ce mecanisme vient d'une etude d'`ines-design` **posterieure et
plus large** que le commit de cette branche
(`squad/sprints/s21-fermer-le-pli-avant-le-volume/proposition-ines-rep-8.md`, s21, 167 items vus sur
17 jeux catalogue, candidats chiffres marge/rembourrage plutot que `line-height`), acceptee par
`nadia-prof` et livree par `marc-dev` : 26 des 56 occurrences catalogue d'indice sous le pli
fermees, 4 des 6 occurrences de verdict sous le pli fermees. **`s20-ines-2` et le mecanisme livre
ne sont pas le meme changement** ; ils visent le meme symptome par deux leviers differents, et c'est
le second, plus tardif et plus etudie, que `round-two` porte.

**Le motif de ce residu a, depuis, cesse d'exister comme critere de Done pedagogique, sur tout le
catalogue.** L'utilisatrice a arbitre le 2026-08-28 (`DEC-0053`) que le pli sort entierement de la
porte pedagogique de `nadia-prof`, en lecture large confirmee le meme jour
(`squad/conformite/PORTE-PEDAGOGIQUE-apres-arbitrage-du-2026-08-28.md`, section 10.1) : "Je ne
prononce plus de REFUSE sur le motif 'un element du jeu reste hors de vue sans faire defiler la
page' - quel que soit cet element : l'indice, le verdict, une proposition, ou tout autre element du
meme bloc." Cette clause couvre nommement le verdict, donc le sujet exact que `s20-ines-2`
corrigeait. La dette residuelle est desormais tracee sans mesure en pixels, par une mention de
non-couverture inconditionnelle (`MNC-1`) portee sur tout Done pedagogique a partir du 2026-08-28,
et non plus par un chiffre a fermer.

**Verification du point souleve par `theo-archi` (2026-08-28) : cette branche porte-t-elle un
motif de lisibilite propre, independant du pli ?** Lecture integrale du commit et du document de
correctif : les deux mesures citees (texte du verdict sous le pli sur `cm1-reperes`, texte
d'indice qui deviendrait partiellement lisible sur `AGN-1`) sont, l'une et l'autre, formulees
uniquement en termes de visibilite sans defilement a 744 x 551 - aucune des deux ne cite une
justification de lisibilite, de charge cognitive ou de typographie qui tiendrait independamment du
pli. Je ne trouve aucun motif independant ecrit ; s'il en existe un non ecrit, il appartient a
`ines-design` et `nadia-prof` de le dire, pas a moi de le presumer.

**L'outil de mesure, seul element potentiellement reutilisable.**
`squad/outils/ines-design/mesurer-au-glyphe.py` n'existe sous aucun nom dans `round-two`
(`ls squad/outils/ines-design/` : `mesurer-boutons-role.py`, `mesurer-candidats-rep-8.py`,
`mesurer-cout-mot-designe.py`, pas de mesure au pixel de texte reellement rendu) ; l'outil de
`nadia-prof` deja au depot (`squad/outils/nadia-prof/auditer-paquet.py`) mesure des boites
(`getBoundingClientRect`), pas des pixels de glyphe. `theo-archi` a range le 2026-08-28
l'outillage de mesure du pli parmi les elements que l'arbitrage ne touche pas (`DEC-0053` :
"l'outillage de mesure de outils/ ... reste des instruments valides"). Mais `nadia-prof` a ecrit,
le meme jour, qu'elle ne mesure plus le pli en pixels pour aucun usage, y compris qualitatif
(`PORTE-PEDAGOGIQUE...`, section 2 : "aucun chiffre en pixels, puisque je ne mesure plus"). Aucun
autre usage de cet outil n'est demande a ce jour. Je ne le recupere pas moi-meme : un outil non
demande, recupere sur ma seule initiative, serait une abstraction pour une seule operation deja
rendue sans objet.

**Ce qui n'est recapture nulle part.** Le changement de code lui-meme (`.enonce { line-height:
1.3 }`) n'est fusionne sous aucune forme. Mais son objet - fermer un residu de pli sur le verdict
de `cm1-reperes` - est **rendu sans objet** par `DEC-0053`, independamment de toute question de
recapture : aucun Done pedagogique ne peut plus etre retenu sur ce motif, sur ce paquet ni sur
aucun autre.

**Issue : abandon.** Motif : le changement de code n'a jamais ete fusionne, le residu qu'il visait
a ete ferme par un autre mecanisme deja livre et plus largement etudie, et le motif restant a
totalement cesse d'exister comme critere de Done pedagogique par arbitrage de l'utilisatrice
posterieur aux deux branches. Reprendre ce commit ajouterait une regle de style a un residu de
pli que plus personne ne demande de fermer.

## Ce que je tranche seule

Le sort des deux branches (abandon, motive commit par commit ci-dessus), et la verification de
recapture par `git diff`/lecture des fichiers, exactement le geste que `DEC-0038` etablit comme
mien.

## Ce que je route

- **`sofia-sm`** : suppression des deux branches locales (`hs-nadia-arbitrages`, `s20-ines-2`),
  operation de housekeeping Git, pas une decision produit ; je ne l'execute pas depuis ce worktree
  dedie et je ne fusionne ni ne pousse rien.
- **`ines-design` et `nadia-prof`** : si l'une d'elles juge que `s20-ines-2` ou la reserve `REP-7`
  de `hs-nadia-arbitrages` portait un motif de lisibilite independant du pli que ma lecture n'a pas
  trouve, c'est a elle de l'ecrire ; je ne le presume pas et je ne rouvre rien sur ma seule lecture.
- **Aucun autre routage.** Rien vers `theo-archi` : aucune question d'architecture n'est ouverte par
  cette verification, et sa classification de l'outillage de mesure est deja ecrite dans
  `DEC-0053`, je ne fais que la citer.

## Impact

- `squad/decisions/produit/DEC-0054-...md` (ce fichier) : cree.
- `squad/decisions/produit/DEC-index.md` : ligne `DEC-0054` a ajouter dans le meme lot.
- `squad/programme/MATRICE-couverture.md` : inchangee, la ligne concernee est deja
  `couvert-valide` depuis `DEC-0050`.
- `docs/backlog/backlog.md` : inchange, aucune story neuve n'en decoule.
- Aucun fichier de code, aucune fiche de conformite n'est modifie par cette decision : elle
  constate un etat deja present dans `round-two` (branche 1) ou rendu sans objet par un arbitrage
  posterieur (branche 2), elle n'en cree pas.
