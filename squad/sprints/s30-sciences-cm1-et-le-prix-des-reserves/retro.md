# Retro de s30 - sciences CM1 et le prix des reserves

## `PAR-9` a fait exactement ce pour quoi elle a ete ecrite - le defaut suivant etait dans sa marge, pas dans sa clause

`nadia-prof` avait ecrit `PAR-9` a l'ouverture de s30 contre elle-meme, pour corriger le defaut de
`PAR-8` qui avait laisse s29 couter 9,50 sur une borne de 8,0 sans que la composition qui le
produisait soit arbitree a l'avance (deux plafonds individuels de 5,0 sommant a 10,0, sans que rien
ne tienne leur somme). **Elle a tenu son objet.** Le depassement de s30 (8,50 sur 8,0) n'est pas un
depassement silencieux comme celui de s29 : il est **arbitre par ecrit avant d'etre consomme**
(`DEC-0080`), chiffre au centime pres, et reste sous le plafond dur de chaque instance. C'est
exactement ce que `PAR-9` promettait, et `nadia-prof` le dit elle-meme contre son propre
depassement plutot que de le taire.

Le defaut suivant n'etait pas dans `PAR-9` : il etait dans le **niveau** ou elle calculait sa marge.
`PAR-9` allouait 0,5 de marge de rejeu par instance, a parts egales du sprint - mais c'est une autre
clause de `nadia-prof`, `PAR-2` (une matiere et un niveau ne se partagent jamais entre deux
instances), qui a concentre les deux seuls lots de contenu neuf du sprint, donc les deux seuls
reaudits possibles, sur l'Instance B. Une marge dimensionnee au niveau du sprint et un risque
concentre sur une seule instance ne pouvaient pas coincider : l'Instance B avait besoin de 1,0
(deux reaudits a 0,5), elle n'en avait que 0,5. **`nadia-prof` corrige ce point par `PAR-11`** : la
marge se dimensionne desormais par instance sur son exposition reelle, jamais par part egale de la
marge du sprint. C'est la meme famille de defaut que celle que `PAR-9` avait deja corrigee dans
`PAR-8` - un total qui tient sans que sa repartition ne tienne - rencontree une seconde fois, un
cran plus bas, et corrigee de la meme facon : par ecrit, avant la cloture, par celle dont c'est le
role.

## La marge de rejeu a servi a un rejeu reel pour la premiere fois dans ce depot

L'Instance A a consomme sa marge de 0,5 entierement sur le reaudit de `CONS-1`, et non sur un lot de
contenu neuf. `nadia-prof` l'ecrit elle-meme comme un fait de gouvernance et non comme un grief :
une marge nommee a l'ouverture, non allouee, et depensee dans le sprint sur un rejeu reel, c'est le
comportement pour lequel la marge avait ete concue - et c'est la premiere fois qu'il se produit
depuis que ce depot connait la marge de rejeu (s29). Un dispositif ecrit pour un cas d'usage l'a
rencontre et servi exactement comme prevu, sans amendement necessaire.

## La couture s'est rompue une seconde fois, sur deux stories du meme jour et de la meme developpeuse

`nadia-prof` avait nomme la couture comme premier mode de defaillance de la parallelisation des s29.
Elle s'est realisee deux fois a s30, et la seconde fois porte un trait nouveau : ce n'est plus une
consigne ecrite contre l'etat d'avant du paquet (le defaut de `vivant-non-vivant-cp`, ou `CONS-1`
a ete redigee contre le paquet avant que `VIV-R2`, livree le meme jour par la meme developpeuse, ne
lui ajoute une troisieme tache) - c'est **la meme couture qui casse une seconde fois sur un paquet
different**, `histoire-geographie-cm1`, avec deux tentatives de correction insuffisantes avant
qu'une troisieme ne tienne. `nadia-prof` le nomme elle-meme sans se dedouaner : *"cette troisieme
tache, c'est moi qui l'ai creee, en prescrivant `VIV-R2` mot pour mot... ce n'est pas une faute de
`lea-dev` seule : c'est une couture."* Ce n'est pas non plus une faute de `lea-dev` sur
`histoire-geographie-cm1` : la consigne devait resumer quatre familles de taches sur cinquante-sept
items, et deux reecritures successives n'en ont nomme que deux puis trois avant qu'une lecture
complete du comptage ne les nomme toutes les quatre. **Le fait qui merite d'etre retenu n'est ni
l'un ni l'autre incident pris seul, c'est que le meme mode de defaillance, nomme une fois, s'est
reproduit deux fois dans le meme sprint sur deux paquets differents** - la couture entre une story
qui modifie le contenu d'un paquet et une story qui modifie sa consigne au catalogue reste un point
de rupture que ce depot n'a pas encore appris a garder mecaniquement, seulement a corriger apres
coup.

## Un defaut fabrique par la correction d'un autre defaut - troisieme cas en deux jours

`OBT-R2` (les deux indices neufs de `objet-technique-cm1` eliminent les deux distracteurs a la fois
et revelent la reponse) est le troisieme cas de cette famille en deux jours, apres `REV-2`
(corrigee en reparant `REV-1`) et `REV-3` (une cause anterieure, revelee en corrigeant `REV-1` et
`REV-2` au meme registre). `nadia-prof` le dit elle-meme : *"c'est le motif meme du rejeu"* - elle
ne valide aucune correction sans la rejouer, precisement parce que rendre un distracteur plausible
(le remede a `OBJ-R2`) et rendre un indice muet sur les deux distracteurs a la fois sont deux
exigences qui peuvent se contredire sans qu'aucune des deux, prise seule, ne le revele. **Trois
occurrences de la meme famille en deux jours ne sont plus une coincidence** : corriger un defaut de
contenu sur un item scolaire cree un risque specifique et repete sur les autres proprietes du meme
item, et ce risque n'a aujourd'hui d'autre garde que le rejeu integral d'un auditeur humain - aucun
garde-fou de ce depot ne le couvre, et `nadia-prof` le signale explicitement pour que l'equipe ne le
croie pas couvert par la reprise elle-meme.

## Un fichier de test ecrase par `Write` sans relecture - meme famille que les trois incidents de s29, et la lecon n'a pas totalement tenu

`marc-dev` a ecrase `PaquetCm1EmcFaitOpinionTest.java`, ecrit par `lea-dev` a s29, sans le relire
avant d'ecrire par-dessus - six tests d'origine perdus, plus une regression d'encodage sur un
caractere insecable. C'est la meme famille que les trois incidents d'orchestration de s29 (travail
d'un agent embarque dans celui d'un autre), et `nadia-prof` demande explicitement qu'il soit traite
comme une recidive et non comme un fait isole. **Ce qui a tenu** : `marc-dev` l'a detecte lui-meme,
avant tout commit errone, en comparant son propre decompte de tests a la mesure d'entree du sprint -
un reflexe de verification qui a fonctionne exactement comme il devrait. **Ce qui n'a pas tenu** :
rien dans le process de ce depot n'empeche mecaniquement d'ecrire par-dessus un fichier qu'on n'a
pas lu ; la defense reste entierement portee par la vigilance de chaque agent au moment de committer,
et elle a failli une fois de plus avant d'etre rattrapee. Une lecon tiree en retro trois fois
(s29, s30) sans devenir une discipline verifiable reste une lecon qu'on relit, pas une lecon
qu'on applique - le meme constat que celui fait sur `yanis-qa` et les fichiers de test manquants a
s28/s29, cette fois sur un autre defaut de la meme famille generale (ecrire sans avoir lu ce qui
existait deja).

## Un defaut d'orchestration qui est le mien, pas celui d'un agent : le scratchpad partage des deux instances

Les deux instances de `nadia-prof` partageaient le meme scratchpad d'agent pendant leurs reaudits
en parallele, et l'une a ecrase le fichier de l'autre. Ce n'est pas un defaut de contenu ni de
jugement pedagogique : c'est un defaut de la facon dont j'ai fait tourner les deux instances en
parallele, dans l'infrastructure qui les porte, pas dans les regles de mesure que j'ai deja
generalisees au planning. **Ce qui a tenu** : l'Instance A a detecte la collision et verifie
explicitement qu'aucun contenu de l'autre instance n'avait contamine ses propres fichiers avant de
rendre son verdict - la meme discipline de verification que celle qui a permis de detecter le
fichier ecrase par `marc-dev`. **Ce qui doit changer** : la regle de worktree dedie que j'ai
generalisee a ce planning couvre le reacteur (le repertoire `target/`, les mesures Maven) ; elle ne
couvrait pas le scratchpad de travail des agents eux-memes, qui est une ressource differente et qui
s'est revelee partagee la ou je la croyais isolee par instance. C'est exactement la meme famille de
lecon que celle de s29 (une discipline qui existe pour une ressource ne se generalise pas d'elle-meme
a une ressource voisine tant que personne ne l'a rendue explicite) et c'est pourquoi je la porte en
amendement plus bas plutot que de me contenter de la constater.

## Quatre agents ont trouve une erreur dans leur propre mesure avant de la publier - la discipline de reverification tient, des deux cotes

Ce sprint donne quatre exemples concrets, chacun avant publication et non decouvert par un tiers
apres coup :

- **`theo-archi`** a trouve qu'un premier comptage de fichiers etait fausse par la decoration de
  l'enveloppe de shell de son poste (un resultat de 1 fichier Java de production touche la ou il y
  en avait 0) ; corrige en ecrivant la liste dans un fichier puis en la relisant, plutot qu'en comptant
  au fil d'un tube.
- **`lea-dev`** a pris un artefact d'encodage de son propre harnais de test pour un defaut
  applicatif, verifie et ecarte avant de le remonter comme un obstacle qui n'en etait pas un.
- **`nadia-prof`, Instance A**, a trouve que sa propre barre de reference (44 des 54 entrees de
  catalogue nommant leur tache) etait un comptage d'une autre chose (les entrees ne portant pas une
  chaine generique exacte) - et la correction **retourne un de ses propres verdicts** : elle
  s'appretait a refuser une consigne d'anglais pour une forme qu'elle avait deja creditee sur six
  entrees de sa propre classe de reference, sans les avoir vues comme telles.
- **`nadia-prof`, Instance B**, a pris un indice pour une erreur de fait avant verification, et
  s'est corrigee elle-meme avant de l'ecrire comme motif : *"un motif faux est pire qu'un motif
  absent."*

**Ce que ces quatre cas disent ensemble** : aucun n'a ete trouve par un tiers, chacun a ete trouve
par son propre auteur avant publication, et le troisieme a coute a `nadia-prof` de revenir sur son
propre jugement plutot que de laisser une barre mal posee produire un refus injuste. C'est la
discipline que ce depot demande depuis plusieurs sprints (un chiffre se reverifie, il ne se relaie
pas) qui fonctionne ici dans les deux sens : elle rattrape une erreur technique comme elle rattrape
un jugement pedagogique appliique inegalement.

## Troisieme depassement consecutif de la borne - la tendance baisse, le fait se repete

s28 : +0,25. s29 : +1,50. s30 : +0,50. La tendance est a la baisse d'un ordre de grandeur, et ce
sprint est le premier ou le depassement est arbitre et chiffre **avant** d'etre consomme plutot que
decouvert au comptage de cloture - ce que `PAR-9` a directement produit. Mais c'est aussi le
troisieme sprint de suite ou le cout reel depasse la borne annoncee, et `nadia-prof` a elle-meme
ecrit, dans son propre arbitrage prealable, qu'*"une borne qu'on depasse deux sprints de suite n'est
plus une borne"*. Je ne tranche pas ici si la borne doit changer de nature - c'est un arbitrage de
l'utilisatrice et de `camille-po`, pas de moi - mais je porte le fait tel qu'il est, sans le
minimiser parce que sa forme s'est amelioree : une borne depassee trois fois de suite, meme de
moins en moins, reste une borne qui ne tient pas comme borne.

## Amendements a `squad/README.md`

**Deux amendements, tous deux issus de ce sprint et tous deux dus.**

**Premier amendement, section 9 - la discipline de mesure par worktree dedie, generalisee au
planning de s30, a tenu.** Tous les agents mesurant en cours de sprint (`yanis-qa`, `theo-archi`,
`marc-dev`, `lea-dev`, les deux instances de `nadia-prof`, moi-meme) l'ont fait depuis leur propre
worktree, et l'assemblage n'a trouve aucun recouvrement de fichier sur les vingt-huit paires
verifiees a travers les trois vagues de fusion de ce sprint. C'etait note comme du a cette retro au
planning ; c'est formalise ici : la section 9 du `README` porte desormais, a cote de la regle du
premier commit deja ecrite depuis s06, la meme regle etendue explicitement a toute mesure du
reacteur, premiere ou non, tranchee et verifiee tenue a s30.

**Second amendement, section 9 - le scratchpad d'un agent n'est pas couvert par la regle de
worktree dedie, et il doit l'etre quand deux instances du meme agent tournent en parallele.** Le
worktree dedie isole l'arbre Git et le `target/` de compilation ; il n'isole pas necessairement les
ressources de travail temporaires d'un agent (scratchpad, fichiers de brouillon hors du depot). Deux
instances de `nadia-prof` partageant le meme scratchpad se sont ecrasees l'une l'autre ce sprint,
sans consequence de contenu grace a la verification de l'Instance A, mais sans qu'aucune regle
existante ne l'ait empechee. A partir de s31, toute paire d'instances du meme agent tournant en
parallele recoit un scratchpad aussi dedie que son worktree - la meme discipline, etendue a la
ressource qui n'avait pas encore ete nommee.

## Decisions prises (creees ou modifiees ce sprint)

| Reference | Titre | Type | Statut |
|---|---|---|---|
| `ADR-0084` | La reponse d'une fin de partie voyage avec la question qu'elle commente (`REV-3`) | Architecture | Accepte |
| `DEC-0078` | `CONS-1` - la consigne generique cesse d'etre reprise par une entree neuve de catalogue | Produit | Acceptee, amendee le 2026-09-07 (population corrigee a 43/54, `CONS-3` routee) |
| `DEC-0079` | Strategie de s30 : sciences et technologie CM1 en bloc, quatre arbitrages de l'utilisatrice, budget par instance (`PAR-9`) | Produit | Acceptee, amendee le 2026-09-07 (`PAR-11` referencee) |
| `DEC-0080` | Cloture de s30 : les deux paquets de sciences et `CONS-1` corriges et reaudites dans le sprint, troisieme depassement assume | Produit | Acceptee, executee (amendee le 2026-09-07, les deux reaudits confirmes au centime pres) |
