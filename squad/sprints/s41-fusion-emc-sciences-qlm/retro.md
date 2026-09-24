# Retro - s41 - fusion de deux sprints projetes (enseignement moral et civique, sciences et technologie CM1, questionner le monde CP)

- **Date** : 2026-09-24.
- **Casquette** : `sofia-sm`, Scrum Master.
- **Nature du sprint** : entierement pilote par agents, sans ceremonie de daily - conforme a
  `squad/README.md`. Trente commits (`784421ed..6417c67f`), dont douze de fusion : contrairement a
  s40 (chaine de commits directs, aucune fusion), s41 a suivi la consigne explicite de mon propre
  `planning.md` section 4 - chaque developpeur fusionne son lot des qu'il est vert, sur deux
  terrains et deux instances paralleles de `nadia-prof`.

---

## 1. Ce qui a marche

**Deux terrains, deux instances de `nadia-prof` en parallele, zero collision de perimetre.**
`DEC-0095` (section 9.3) verifiait avant l'ouverture du sprint qu'aucune clause de frontiere
n'etait due entre le terrain EMC (instance A) et le terrain sciences CM1 / questionner le monde CP
(instance B), contre le seul critere que `nadia-prof` a ecrit pour en produire une - un objet du
monde reel partage entre des lignes de matieres differentes sur des instances differentes. Verifie
apres coup, sur les six fiches de conformite : aucune ne signale de contradiction avec un contenu
de l'autre terrain. La seule adjacence nommee d'avance (`QLM-CP-04` / `cp-eps-corps-effort`, hors
des deux terrains de ce sprint) a ete verifiee a la main par `nadia-prof` elle-meme et n'existe pas
dans le contenu livre.

**La correction iterative sur `EMC-CP-08` a tenu jusqu'au bout, malgre deux refus.** C'est le
premier lot de ce couple de sprints ou un refus survit a une premiere correction et en demande une
seconde dans le meme sprint. Le mecanisme n'a pas rompu : `nadia-prof` a nomme precisement, au
second refus, ce que sa propre prescription avait de trop permissif - un exemple concret joint a sa
correction contredisait en pratique la clause de verite qu'elle precedait - et elle l'a reecrite
sans exemple substituable avant de redemander. `marc-dev` a corrige exactement ce qui etait demande
aux deux passages, sans toucher aux huit items que `nadia-prof` avait explicitement demande de ne
pas reecrire.

**La discipline de role a tenu sur trois defauts trouves en cours de sprint.** `theo-archi` a trouve
sa propre erreur de methode de comptage de tests (1692 au lieu de 1701) en la comparant a la mesure
de `yanis-qa`, et l'a corrigee lui-meme, dans le meme fichier, en nommant sa propre faute plutot
qu'en la laissant filer. `camille-po` a corrige elle-meme le critere d'acceptation de
`S41-SCI-CLASSER`, qui portait une part du defaut trouve par `nadia-prof` sur ce lot, plutot que de
laisser `lea-dev` compenser un critere fautif par un contenu plus prudent. Aucune de ces trois
corrections n'a improvise hors du role de son auteur.

## 2. Ce qui a coince

**Le risque que `DEC-0095` nommait d'avance sans le trancher s'est materialise, et rien ne le
reconcilie encore.** La section 9.5 de `DEC-0095` ecrivait, avant l'ouverture du sprint : le
terrain 1 (EMC) est compose a 4,5 sur 5,0, sans aucune marge de rejeu individuelle - *"un refus sur
l'un des trois lots d'EMC ferait depasser 5,0 sur cette instance sans reallocation ecrite"*. La
section 9.7 routait explicitement ce point, non bloquant mais *"a peser avant l'ouverture du
planning"*, a `sofia-sm` et a l'utilisatrice. Mon propre `planning.md` (section 6) l'a repris dans
les memes termes - *"point signale a l'ouverture du sprint, non tranche ici"* - sans le peser
davantage. **`EMC-CP-08`, un des trois lots du terrain 1, a ete refuse deux fois.** Le risque nomme
deux fois comme non bloquant n'a jamais ete pese par personne avant qu'il ne se produise, et rien
dans les fichiers du sprint ne reconcilie ce fait avec la clause de marge nulle - ni une `DEC` qui
dirait que le depassement du tarif est accepte, ni une qui le chiffrerait. Je le signale dans
`review.md`, section 7, comme obstacle ouvert : ce n'est pas a moi de le trancher, c'est routable a
`camille-po` et a l'utilisatrice, exactement comme `DEC-0095` l'avait deja route.

**`nadia-prof` a trouve une incoherence dans son propre referentiel, ouverte depuis pres d'un
mois.** `SEM-1`, la condition qu'elle a ecrite elle-meme le 2026-08-31 pour ouvrir `cp-semaine`,
autorise *"mois et leur ordre, saisons"* dans un paquet de mathematiques - alors que le referentiel
de questionner le monde reserve ces memes objets a `QLM-CP-13`. Quatre items de `cp-semaine`
travaillent donc, depuis s25, la meme competence que celle comptee sur `QLM-CP-13`. Ce n'est ni un
defaut de contenu ni une faute d'un developpeur : c'est un ecart entre deux arbitrages ecrits par la
meme auditrice a un mois d'intervalle, qui n'est devenu visible qu'en auditant la seconde ligne
contre la premiere. `camille-po` l'a consigne dans la matrice (note du 2026-09-24) sans le trancher
- c'est la bonne place pour l'instant, une question de double-compte n'est pas un motif de refus.

**Le signalement du gap `MAT-CE1-18` s'est interrompu, pour une raison de circuit, pas d'oubli.**
Signale a chacun des cinq sprints precedents (s36 a s40), toujours par `camille-po`, toujours en
marge d'un document qu'elle redigeait pour un autre motif. Ce sprint-ci, le document qui portait
habituellement ce signalement - `planning.md` - n'a pas ete le premier ecrit : `DEC-0095` est
arrivee directement comme instruction de tache, sans passer par `sofia-sm`, et mon propre
`planning.md` n'a fait que confirmer une affectation deja fixee. Le moment ou le signal se produisait
d'habitude ne s'est simplement pas presente sous la meme forme. Je ne propose pas d'amendement pour
ce point precis : c'est un effet de circuit sur un sprint qui a derive de la ceremonie habituelle
pour un motif produit legitime (`DEC-0095`), pas une regression de discipline.

**Un point signale hors depot, que je traite selon la regle qui existe deja pour ce cas exact.**
Recu comme une instruction de tache, dans les termes suivants, cites mot pour mot et dates du
2026-09-24 : *"Point de fragilite signale par lea-dev pendant le developpement : le worktree
principal partage souffre de collisions transitoires (mvnd, verrous de fichiers) quand plusieurs
agents y lancent mvn en meme temps pendant une fusion."* C'est la forme 3 de `squad/README.md`,
section 2 bis : un enonce recu hors des neuf agents, qui **ne vaut jamais motif** tant qu'il n'a pas
ete verifie contre le depot. Verifie : aucun commit de `lea-dev` ne le porte (`c87afd7a`,
`bddd8233`), et `yanis-qa` ecrit l'inverse dans son rapport de cloture - *"Aucun verrou `mvnd`
transitoire rencontre : `bash outils/mvn-jdk21.sh -B clean verify`, lance une seule fois sur ce
worktree, a rendu `BUILD SUCCESS` du premier coup"*. Ma propre verification de cloture (`review.md`,
section 2) n'en a pas rencontre non plus. **Je ne fais donc pas de ce signalement un obstacle
resolu ni meme confirme** : je le nomme comme point de vigilance de process pour un sprint futur qui
verrait plusieurs agents lancer Maven en parallele sur le meme worktree partage pendant une fusion,
sans lui donner le statut d'un fait etabli qu'aucun fichier de ce sprint ne porte.

## 3. Amendements a `squad/README.md`

**Aucun amendement fait ce sprint.** J'ai considere en ecrire un sur la maniere de traiter un
signalement recu hors depot pendant un developpement, mais la regle existe deja et s'applique telle
quelle : `squad/README.md`, section 2 bis, forme 3, couvre exactement ce cas - un enonce recu hors
des neuf agents ne vaut jamais motif tant qu'il n'est pas verifie, et je l'ai applique ci-dessus
sans avoir besoin de l'etendre. J'ai aussi considere un amendement sur le fait de peser un risque
route comme *"non bloquant mais a peser avant l'ouverture"* - mais ce risque-la (la marge de rejeu
du terrain EMC) est un arbitrage de tarif et de borne d'audit qui appartient a `camille-po` et a
`nadia-prof`, pas un defaut de ma propre ceremonie de planning : je l'ai nomme et route deux fois
(`DEC-0095`, mon `planning.md`), et le nommer une troisieme fois dans un amendement de process
n'ajouterait rien que la table des obstacles de `review.md` ne porte deja.

## Decisions prises

**Aucune decision de sprint neuve n'est prise dans cette retro**, au sens `DEC`/`ADR`. Elle
constate ce qui a fonctionne et ce qui a coince dans l'orchestration de s41 et ne modifie pas
`squad/README.md` - la decision du sprint (`DEC-0095`) est deja enregistree dans `review.md`,
section "Decisions prises", et n'est pas reprise ici une seconde fois.
