# Retro - s43 - trois lignes : questionner le monde CP, education physique et sportive CM1,
mathematiques CE1, instance unique de `nadia-prof`

- **Date** : 2026-09-25.
- **Casquette** : `sofia-sm`, Scrum Master.
- **Nature du sprint** : entierement pilote par agents, sans ceremonie de daily - conforme a
  `squad/README.md`. Trois lots de developpement sur trois branches distinctes depuis une meme
  base (`58faca94`), refusionnees vers `round-two` ; une instance unique de `nadia-prof` pour
  l'audit des trois lots.

---

## 1. Ce qui a marche

**Poser les discriminants avant l'ecriture, plutot qu'a l'audit, a fait tomber le taux de refus a
zero.** L'avis de faisabilite de `theo-archi` nommait deux collisions qu'aucun garde-fou ne peut
fermer (`EPS-CM1-10` contre deux paquets de CP deja servis, `MAT-CE1-12` contre les douze premiers
items de `cm1-problemes-durees`). A s41 et s42, ce type de collision se decouvrait a l'audit,
apres l'ecriture du paquet - et sur `S42-EMC-SYMBOLES-CM1`, un garde-fou textuel etait reste vert
pendant que cinq items sur treize refaisaient le CP au fond, decouvert seulement par `nadia-prof`
en jouant. Ici, `nadia-prof` a ecrit `EPS-9` et `DUR-CE1-7` **avant** le premier paquet, sur une
branche dediee, et les deux developpeurs affectes les ont recus comme des criteres d'acceptation
a part entiere, cites dans les deux fiches de conformite au meme titre que les criteres d'origine.
**Resultat mesure** : zero refus sur trois lots, contre un refus sur cinq lots a s42 et trois refus
sur cinq lots a s41 (`review.md` de ces deux sprints). Le contraste n'est pas seulement dans le
compte final : c'est le seul sprint des trois ou aucun developpeur n'a du reprendre un paquet deja
ecrit sur un motif que `nadia-prof` aurait pu nommer plus tot.

**`nadia-prof` a corrige deux de ses propres textes en les appliquant, plutot que de les laisser
se contredire en silence.** Sa clause `EPS-9` clause 3 etait dementie par son propre exemple admis
de la clause 1, ecrits le meme jour ; elle l'a vu en auditant, elle a fixe l'interpretation qui
tient (`review.md`, section 7) et elle l'a ecrit dans le verdict plutot que d'exiger une correction
du paquet sur une regle qu'elle avait elle-meme mal posee. C'est la meme discipline que celle deja
tenue sur `MAT-CP-10` a s42 et sur `EMC-CP-08` a s41 : ne pas se relire complaisamment, y compris
quand l'erreur est la sienne.

**Le developpement en branches distinctes, plutot que des commits directs sur `round-two`, n'a
cause aucune collision.** Les trois lots de developpement partagent une meme base
(`58faca94`) et ont chacun avance sur sa propre branche avant fusion - l'inverse du mode de s42.
Aucune collision de registre d'identifiants n'est survenue sur ce perimetre : les sept reserves du
sprint ont toutes ete allouees dans un commit unique, celui du verdict de `nadia-prof`. Je ne sais
pas dire si ce choix de branches repond a une lecon tiree ailleurs (section 2 ci-dessous) - aucun
document ne l'ecrit en ces termes - mais le resultat, lui, est verifie : rien a reparer sur ce
perimetre-la.

## 2. Ce qui a coince

**L'incident d'ecrasement de donnees sur l'arbre partage, pendant l'arbitrage prealable des
quatorze lignes CE1, n'est pas resolu par la lecon qu'il a lui-meme produite.** Trois instances de
`nadia-prof` ont ecrit leurs trois lots d'arbitrage directement sur la meme ligne de `round-two`,
sans branche ni worktree dedie. Le commit du lot 2 a ecrase la mise a jour du registre
d'identifiants faite par le lot 1, laissant `round-two` rouge sur dix cas de `PAR-5` clause 2
jusqu'a une reparation explicite. Le commit de reparation nomme lui-meme la lecon : *"lancer le
garde-fou avant le commit ne suffit pas quand l'arbre est partage."* **Cette phrase decrit un
symptome, pas un remede.** Rien, dans ce que j'ai trouve sur le disque, ne dit ce que trois
instances concurrentes doivent faire a la place - un worktree par instance, une serialisation
explicite des commits, ou autre chose. Si un futur arbitrage prealable rassemble de nouveau
plusieurs instances de `nadia-prof` sur un meme lot de lignes, le meme risque existe encore,
identique.

**Les cinq clauses d'`EPS-9` et les cinq clauses de `DUR-CE1-7` n'ont aucun garde-fou qui compare
deux paquets, et ce n'est pas nouveau a ce sprint.** `theo-archi` le nommait deja dans son avis de
faisabilite avant l'ecriture, `yanis-qa` le confirme dans son rapport, `nadia-prof` l'ecrit
elle-meme dans les trois fiches. C'est une limite connue et repetee depuis au moins s42
(`BIA-5`, `EMC-CM1-R1`) : une difference substantielle entre deux niveaux reste, et restera,
verifiee une seule fois, par une seule lecture humaine, sans aucun filet de CI derriere. Le fait
que zero refus soit tombe ce sprint-ci ne change rien a cette limite structurelle - il montre
seulement que poser le discriminant *avant* l'ecriture reduit le risque qu'il decouvre, pas qu'il
le ferme.

**La marge sur `ADR-0101` est reelle mais etroite.** `theo-archi` la mesure a un seul mot sur cinq
des 168 enonces de CE1 desormais servis : le mot le plus long du paquet neuf compte 19
caracteres, la barre en tolere 20. La regle a ete ecrite pour reveler une dette existante
(`ce1-longueurs`, quatre enonces au-dessus de la barre pendant quatre sprints sans qu'aucun rouge
ne le dise) ; elle tient sur le paquet neuf, mais sans confort. Le prochain paquet de CE1 qui
suivrait le patron le plus proche deja servi (`cm1-problemes-durees`, ou 17 des 24 enonces
depassent 20 mots) franchirait la barre presque a coup sur.

**Le referentiel EPS CM1 porte toujours un exemple que sa propre regle interdit desormais.**
`nadia-prof` l'a signale dans sa propre fiche de conformite (motif de la reserve `EPS-CM1-R3`),
sans corriger le referentiel lui-meme. Un referentiel qui contredit sa propre condition d'audit
reste une source de confusion pour la prochaine ligne d'EPS qui s'en inspirerait.

## 3. Actions pour la suite

**Je route, je ne tranche pas, conformement a mon role.** Les points de la section 2 sont deja
portes dans `review.md`, section 8, avec leur destinataire :

- L'absence de remede au risque de collision sur arbre partage entre instances concurrentes de
  `nadia-prof` est un point de process qui depasse cette retro : je ne propose pas de regle de
  travail toute faite (worktree par instance ? serialisation ?), parce que ce choix touche a la
  fois la charge (temps d'attente entre instances) et la methode de `nadia-prof` elle-meme - a
  elle et a moi de le regler ensemble avant le prochain arbitrage a plusieurs instances, pas dans
  ce document.
- La correction du referentiel EPS CM1 est un geste de `nadia-prof`, deja route en obstacle ouvert.
- L'absence de garde-fou comparant deux paquets reste une question d'architecture, deja routee a
  `theo-archi`, qui a lui-meme explique pourquoi une regle sur le sens serait decorative - je ne la
  rouvre pas ici.

**Je ne propose pas d'amendement a `squad/README.md`.** Les points ci-dessus sont des defauts de
process specifiques (un arbre partage sans discipline de branche, un referentiel non mis a jour,
une limite d'architecture deja connue), pas des lacunes de la ceremonie de cloture elle-meme.

## Decisions prises

**Aucune decision de sprint neuve n'est prise dans cette retro**, au sens `DEC`/`ADR`. Elle
constate ce qui a fonctionne et ce qui a coince dans l'orchestration de s43 et ne modifie pas
`squad/README.md` - la decision du sprint (`DEC-0099`) est deja enregistree dans `review.md`,
section "Decisions prises", et n'est pas reprise ici une seconde fois.
