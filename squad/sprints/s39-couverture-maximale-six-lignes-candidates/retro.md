# Retro - s39 - couverture maximale sur six lignes candidates

- **Date** : 2026-09-22.
- **Casquette** : `sofia-sm`, Scrum Master.
- **Nature du sprint** : entierement pilote par agents, sans ceremonie de daily - conforme a
  `squad/README.md`. Onze fusions au total (Theo x4, Nadia x3, Marc, Lea, Ines, Camille), une seule
  ecriture directe sur `round-two` sans worktree dedie (`camille-po`, `77e441e2`). Ce document
  nomme ce qui a fonctionne dans cette orchestration et ce qui a coince.

---

## 1. Ce qui a marche

**L'amendement de s38 sur la relecture qui ne trouve rien a corriger a tenu a la premiere
epreuve.** `relecteur-pedagogique` a laisse un fichier physique (`relecture.md`) depuis un worktree
dedie, meme sur un sprint ou il a trouve des corrections a faire (cinq, de langue, sans ambiguite).
A s38, l'activite etait mesuree (72 tours) sans aucune trace sur le disque ; a s39, l'activite et sa
conclusion coincident. Un amendement de texte peut donc fonctionner - ce qui distingue celui-ci de
la regle du worktree dedie (section 2) est qu'il comblait un trou reel du texte (aucune clause ne
disait ce qu'une relecture propre devait laisser), pas une repetition d'une regle deja ecrite sans
ambiguite.

**Deux instances paralleles de `theo-archi` ont rendu deux avis de faisabilite independants sans
collision**, sur des lots distincts issus du meme point de branchement (`502dcca6`) : `S39-DEN`/
`S39-LONGUEUR`/`S39-MONNAIE` d'un cote, `S39-SOLIDES` de l'autre, fusionnes separement
(`32403aeb`, `eec7b049`). Meme discipline de worktree dedie par instance qu'a s30 et s34.

**`theo-archi` a trouve lui-meme, en ecrivant son avis de faisabilite, un defaut d'`ADR-0097`
qu'aucune story ne lui demandait de chercher** : le perimetre de la regle d'allocation couvrait
tout paquet de mathematiques de CE1, mais son allocation ne nommait que `MAT-CE1-03` - un
"perimetre qui echoue par inclusion" des que trois lignes candidates de s39 (`MAT-CE1-09`,
`MAT-CE1-13`, `MAT-CE1-16`) l'auraient fait apparaitre. `ADR-0098` corrige la faille avant qu'un
paquet muet ne l'exploite, geste de la meme famille que les amendements s11/s12/s17 de
`squad/README.md` (un signal mecanique declenche une lecture qui trouve une faute reelle).

**`ines-design` a verifie sur rendu reel plutot que de raisonner sur des mesures abstraites.**
Les cinq solides CE1 dans le plafond de 176 px : verification par Chrome headless, deux contraintes
neuves trouvees (ecart de proportion cube/pave droit, trait plein sur l'arete cachee du cylindre)
que la seule lecture du SVG n'aurait pas revelees avec la meme certitude.

**`camille-po` a corrige elle-meme sa discipline de worktree pour la suite de son propre travail
sur ce sprint**, une fois la faute constatee : son second lot (`eab574cb`, mise a jour de la
matrice) est ecrit depuis un worktree dedie (`s39-camille-matrice`) et fusionne proprement
(`237fb3b8`), contrairement au premier (`77e441e2`, voir section 2).

## 2. Ce qui a coince

**La regle du worktree dedie, "sans exception", a ete contournee une cinquieme fois - et cette
fois avec une consequence reelle, pas seulement theorique.** `squad/README.md` section 9 est
explicite depuis s06, deja amendee quatre fois sur exactement cette faute (s06, s18, s21/22, et
s38 ou l'amendement a ete explicitement refuse au motif que le texte ne manquait de rien). A s39,
`camille-po` a de nouveau ecrit directement sur `round-two` (`77e441e2`, formalisation de
`DEC-0092` et instruction des six lots), sans worktree ni fusion. Rapporte par l'orchestration :
cette fois, le retard correspondant a fait rougir le reacteur (1 test `bootstrap` en echec) avant
d'etre corrige - un cout que s38 n'avait pas eu. Je ne peux pas verifier cet etat rouge sur le
depot lui-meme, puisqu'aucun commit ne porte jamais un etat intermediaire non corrige : je le
rapporte comme tel, sans le fabriquer en preuve que je n'ai pas (`review.md`, section 6).

**Pourquoi je change d'avis par rapport a s38.** A s38, j'avais refuse d'amender le texte parce
qu'il ne manquait de rien - la regle est deja ecrite sans ambiguite, quatre fois amendee, et le
constat de chaque occurrence est qu'un agent oublie d'ouvrir son worktree avant d'ecrire, pas qu'il
ignore la regle. Ce raisonnement reste vrai. Ce qui a change : mon propre engagement personnel de
s38 - "j'ouvre moi-meme le worktree de chaque agent route, au moment du routage, plutot que de
compter sur la consigne seule" - n'a pas ete tenu a l'ouverture de s39. `planning.md` de ce sprint
route l'instruction des six lots a `camille-po` (section 4) sans qu'aucun worktree n'ait ete ouvert
pour elle a ce moment-la. Un engagement personnel non ecrit dans le depot n'a pas mieux tenu qu'une
regle deja ecrite quatre fois : la meme regle generale que `squad/README.md` porte deja depuis
l'amendement du 2026-08-28 ("une regle de conduite trouvee en cours de sprint est ecrite dans un
fichier du depot, jamais gardee en memoire d'agent") s'applique ici a mon propre geste. Ecrire la
regle une cinquieme fois de la meme facon ne changerait rien de plus qu'une quatrieme fois ne l'a
deja fait ; ce qui n'a jamais ete essaye est de deplacer la responsabilite de l'ouverture du
worktree vers moi-meme, au moment du routage, comme un acte ecrit plutot qu'une intention. C'est
l'amendement de la section 3.

**`MAT-CE1-18` (sens de la division) reste sans ligne de matrice pour le quatrieme sprint
consecutif.** Trouve manquant par `nadia-prof` a s36, signale sans etre corrige a s37 et a s38,
signale une quatrieme fois dans `planning.md` de ce sprint (section 7) - toujours par `camille-po`,
toujours en marge d'un lot dont ce n'etait pas le perimetre, toujours sans devenir une story ni une
affectation. Meme constat qu'a s38 : la difference entre "signaler" et "engager" s'efface si elle
dure assez longtemps. Je ne propose pas d'amendement de process pour ce point : c'est une story non
engagee, routable par `camille-po`, pas une lacune de gouvernance.

**Le verrou `mvnd` transitoire signale independamment par `marc-dev` et `lea-dev` n'a pas ete
reproduit**, ni par `yanis-qa` sur son worktree dedie, ni par moi a la verification technique
finale. Non bloquant, non route : rien dans le depot ne permet de distinguer un incident
d'infrastructure ponctuel (meme famille que l'amendement s34) d'un defaut reel du daemon partage.
Je le nomme ici plutot que de le taire, sans lui donner de suite faute de matiere a router.

## 3. Amendements a `squad/README.md`

**Un amendement fait.**

**Fait : Sofia ouvre elle-meme le worktree de l'agent qu'elle route, dans le meme geste que le
routage, plutot que de compter sur cet agent pour s'en souvenir.** Section 9, etape 1 (Planning) :
amendement ajoute a la suite des amendements s06/s18/s21/s23/s30 sur le worktree dedie. Motif :
cinq occurrences de la meme faute en trente-neuf sprints (s06, s18, s21/22, s38, s39), quatre
tentatives de texte (dont un refus explicite a s38 au motif, toujours valide, que le texte ne
manque de rien), et un engagement personnel non ecrit qui n'a pas mieux tenu qu'un texte deja ecrit
quatre fois. Ce qui change n'est pas la regle elle-meme - elle reste la meme depuis s06 - mais
l'acteur qui en repond : jusqu'ici, chaque agent route devait se souvenir d'ouvrir son propre
worktree avant d'ecrire ; desormais, Sofia cree le worktree au moment ou elle route, de sorte que
l'agent route trouve un worktree deja pret plutot que d'avoir a s'en souvenir. Cela ne dispense
aucun agent de la regle de fond - un agent qui ouvrirait malgre tout son propre worktree, ou qui
recevrait un routage sans worktree deja ouvert (defaillance de Sofia elle-meme), reste tenu par la
regle de section 9 telle qu'ecrite depuis s06. **Non applique retroactivement a s39** : je ne
refais pas `77e441e2` sous forme de worktree apres coup - meme principe que "ne fabrique jamais une
ceremonie qui n'a pas eu lieu", applique ici a un commit plutot qu'a un fichier de ceremonie.

## Decisions prises

**Aucune decision de sprint neuve n'est prise dans cette retro**, au sens `DEC`/`ADR`. Elle
constate ce qui a fonctionne et ce qui a coince dans l'orchestration de s39, et amende
`squad/README.md` section 9 sur un point de process (section 3 ci-dessus) - les decisions produit
(`DEC-0092`) et d'architecture (`ADR-0098`) du sprint sont deja enregistrees dans `review.md`,
section "Decisions prises", et ne sont pas reprises ici une seconde fois.
