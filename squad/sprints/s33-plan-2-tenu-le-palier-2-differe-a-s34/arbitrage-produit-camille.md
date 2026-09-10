# Arbitrage produit - camille-po - les obstacles routes par sofia-sm (planning de s33, section 12)

- **Date** : 2026-09-10.
- **Auteure** : `camille-po`.
- **Sprint** : s33 (arret), obstacles a rendre avant que s34 s'ouvre.
- **Statut** : position produit rendue sur les deux questions de grain. Ni l'une ni l'autre n'est une
  decision conjointe close : celle de `S33-CONS-4` reste ouverte, en attente de la lecture
  pedagogique de `nadia-prof` sur la forme que je propose ; celle du lot de l'effort et du corps
  converge avec sa propre recommandation et peut se lire comme close des qu'elle le confirme.
- **Lien** : `ADR-0081` (routage du grain a `camille-po` et `nadia-prof` ensemble),
  `squad/sprints/s33-plan-2-tenu-le-palier-2-differe-a-s34/planning.md` (section 12),
  `squad/sprints/s33-plan-2-tenu-le-palier-2-differe-a-s34/avis-faisabilite-theo.md` (section 3),
  `squad/conformite/ARBITRAGE-PREALABLE-s33-le-plan-2-applique-a-s33-le-vivier-que-quatre-refus-ont-realimente-et-ce-que-la-borne-de-douze-ne-finance-pas.md`
  (section 5.6), `docs/backlog/backlog.md`.

Ce document ne prononce aucun Done pedagogique et ne fait bouger aucune ligne de
`squad/programme/MATRICE-couverture.md`. Il ne tranche pas non plus a la place de `nadia-prof` la ou
la decision est conjointe : il dit ma position, mon motif, et ce que l'autre option couterait.

---

## 1. Le grain de `S33-CONS-4` (`bandit-manchot-histoire-geographie-cm1`)

### 1.1 Ce qui m'appartient et ce qui reste a `nadia-prof`

`ADR-0081` confie le decoupage a nous deux, jamais a un developpeur ni a `theo-archi` seuls. Je
distingue les deux parts :

- **A moi** : le principe d'organisation du contenu en jeux (le nombre et la nature des paquets qui
  servent une matiere donnee), et l'arbitrage entre le cout d'audit qu'un decoupage engage et la
  duree de vie d'une consigne generique. C'est une decision de contenu au sens de `CLAUDE.md`, pas
  une decision technique : `theo-archi` a deja etabli que le cout dominant n'est pas du code, et
  qu'aucune des deux issues n'en ecrit une ligne de production.
- **A `nadia-prof`** : si le decoupage que je propose degage effectivement des groupes de taches
  pedagogiquement coherents pour un enfant de CM1 - c'est-a-dire si les regroupements que je nomme
  plus bas sont de bons regroupements, ou s'il en faut d'autres - et le texte final de chaque
  consigne, dont le Done pedagogique reste sa seule signature. Je ne propose aucune formulation de
  consigne dans ce document.

### 1.2 Le fait, repris tel qu'elle l'a ecrit

`nadia-prof` a etabli la propriete a tenir, pas la forme : *"la consigne d'un jeu nomme une tache
que l'enfant reconnaitra dans la manche qu'il a devant lui."* Elle a mesure que la consigne actuelle
grossit d'une clause par sprint (12 mots et 0 tache nommee a s27-s30, 22 mots et 4 taches a s31, 31
mots et 5 taches a s32) sans plus informer l'enfant, et que le meme lot a cree quatorze items dont
aucune des cinq clauses ne nomme la tache. `theo-archi` a mesure, sur le materiau reel, que ce paquet
est l'unique exception du depot : 66 des 68 paquets servis servent une seule ligne de referentiel
chacun ; seuls `cm1-histoire-geographie` (11 lignes, 92 items avant `S33-HG-08-12`, 110 apres) et
`cm1-anglais` (2 lignes) en servent plusieurs, et `cm1-anglais` ne pose pas le meme probleme - sa
consigne fait 2 clauses, pas 5, et `CONS-1` n'y mord pas.

### 1.3 Ma position : ni la phrase unique, ni le decoupage ligne par ligne - un decoupage par famille
### de tache

Deux issues avaient ete nommees dans la story (`docs/backlog/backlog.md`, `S33-CONS-4`) : une
consigne qui cesse d'enumerer, ou le jeu qui se scinde. Je retiens la seconde, mais pas dans la
forme la plus couteuse que `theo-archi` a chiffree - un jeu par ligne de referentiel (onze entrees).
**Je propose un decoupage par famille de tache**, c'est-a-dire un jeu par type de question pose a
l'enfant, chaque jeu pouvant continuer a servir plusieurs lignes de referentiel tant qu'elles
partagent la meme tache.

**Motif.**

1. **La propriete que `nadia-prof` a ecrite porte sur la tache, pas sur la ligne de referentiel.**
   Une consigne informe l'enfant si elle nomme la tache qu'il va faire dans la manche - pas si elle
   nomme la ligne du programme que cette tache sert. Les cinq clauses deja servies le montrent
   elles-memes : *"nommer un peuple ou un mouvement de population"* est une seule tache qui sert deja
   deux lignes (`HG-CM1-03` et `HG-CM1-04`) sans que cela pose de probleme a `CONS-1` - c'est le
   partage par ligne qui echoue a onze, pas le partage par tache a deux.
2. **`cm1-anglais` est la preuve que "plusieurs lignes, une consigne" n'est pas le defaut en soi.**
   Cette entree sert deux lignes avec une consigne a une clause et `CONS-1` y est levee depuis
   s06. Le defaut de `cm1-histoire-geographie` n'est pas d'avoir plusieurs lignes sous un jeu ; c'est
   d'avoir cinq taches distinctes sous un jeu, en croissance a chaque nouvelle ligne remplie.
3. **Une phrase unique et non enumerative (l'autre issue nommee dans la story) ne resout rien de
   durable.** Pour rester vraie a onze lignes et cinq taches (et plus, si `HG-CM1-08`/`-12` en
   ouvrent une sixieme), elle devrait soit generaliser au point de redevenir ce que `CONS-1` interdit
   deja - *"repond a la question"* - soit rester specifique et donc continuer de grossir a chaque
   ligne remplie. C'est exactement le mecanisme que `nadia-prof` a mesure en train de se produire :
   je ne vois pas de raison qu'une reformulation ponctuelle l'arrete la ou trois sprints de
   croissance ne l'ont pas arrete.
4. **Le decoupage ligne par ligne (onze entrees) resout la propriete, mais au-dela de ce qu'elle
   demande.** La propriete est satisfaite des qu'un jeu ne sert qu'une tache ; elle ne demande pas
   qu'un jeu ne serve qu'une ligne. Payer les onze portes pedagogiques que `theo-archi` chiffre pour
   une propriete qui se satisfait a cinq (ou six, si `HG-CM1-08`/`-12` en ouvrent une nouvelle) serait
   depenser un cout de conformite sans gain pedagogique supplementaire.

### 1.4 Ce que l'autre grain (la phrase unique) me couterait si je le retenais a sa place

- **Le risque pedagogique reste entier et n'est pas de mon ressort de le lever** : je ne sais pas
  ecrire une phrase de dix a quinze mots qui reste vraie et informative sur cinq taches
  heterogenes (situer dans le temps, associer un roi a son fait, decrire un espace ou une
  ressource, lire un document, nommer un peuple ou un mouvement), et je ne demande pas a
  `nadia-prof` d'en ecrire une avant d'avoir mon avis sur le principe, pour ne pas lui faire
  chercher une formulation dont je pense par avance qu'elle ne tiendra pas.
- **Le mecanisme de croissance n'est pas ferme.** Rien n'empeche une sixieme tache de s'ouvrir avec
  `HG-CM1-08` ou `HG-CM1-12` (le remplissage vient d'ajouter dix-huit items) ; une phrase unique
  devrait alors soit s'allonger une quatrieme fois, soit generaliser encore, ce qui repousse le
  probleme d'un sprint sans le resoudre.
- **Elle est gratuite a court terme et couteuse a ne jamais finir de payer.** C'est l'inverse du
  decoupage : zero porte pedagogique aujourd'hui, une dette de lisibilite qui continue de courir.

### 1.5 Ce que le decoupage ligne par ligne (onze entrees) me couterait si je le retenais a la place
### du decoupage par tache

- **Dix portes pedagogiques neuves au lieu de quatre a cinq**, chiffre par `theo-archi` (avis de
  faisabilite, section 3.1) : une fiche `FICHE-<jeu>.md` par entree neuve, chacune due d'un Done
  pedagogique complet.
- **Les manches passent de dix a neuf sur les lignes a dix items** (`ADR-0023`, `theo-archi`), la ou
  un decoupage par tache regroupant plusieurs lignes garde des paquets a vingt items ou plus et donc
  dix manches.
- **Une quarantaine d'entrees d'inventaire du port entrant** contre une quinzaine environ pour cinq
  a six paquets.
- **Onze tuiles neuves sur l'ecran d'accueil** au lieu de quatre a cinq, question routee a
  `ines-design` dans les deux cas mais dans une proportion tres differente.

### 1.6 Ce qui reste ouvert, et ce que je demande a `nadia-prof`

Je ne fixe pas moi-meme les groupes de taches : c'est un jugement sur ce qu'un enfant de CM1
reconnait comme une seule sorte de question, et il appartient a `nadia-prof`. Ce que je lui demande,
concretement, pour que la decision se ferme :

1. Confirmer ou corriger le regroupement des cinq taches deja servies en autant de jeux futurs
   (aujourd'hui : situer dans le temps ; associer un roi a son fait ; decrire un espace ou une
   ressource ; lire un document ; nommer un peuple ou un mouvement de population).
2. Dire si les items neufs de `HG-CM1-08` (ce que Napoleon Bonaparte change) et de `HG-CM1-12`
   (vivre en ville et en lieu touristique) rejoignent une tache existante ou en ouvrent une
   sixieme.
3. Dire si elle retient plutot la phrase unique pour un sous-ensemble plus restreint de lignes,
   auquel cas ma position ci-dessus ne s'applique qu'au residu.

Tant que ces trois points ne sont pas rendus, `S33-CONS-4` reste bloquee exactement comme le
planning l'a laissee - je ne rouvre pas la story et je ne change pas son critere d'acceptation dans
`docs/backlog/backlog.md`, qui reste correctement formule en "soit ... soit ...".

---

## 2. Le grain du lot de l'effort et du corps (`EPS-CP-10`, `EPS-CP-11`)

### 2.1 Ma position : un seul paquet

Je retiens **un seul paquet** pour les deux lignes, et non deux paquets distincts.

**Motif.** Le tableau que `nadia-prof` a chiffre elle-meme (arbitrage prealable de s33, section
5.6) dit deja tout ce qu'il faut pour trancher : un paquet coute la moitie d'une dette differee
(0,5 contre 1,0), une entree de catalogue au lieu de deux, et surtout **aucune surface de couture**
entre deux moities qui portent le meme objet (l'effort physique). Deux lignes voisines qui partagent
une consigne a deux clauses ne reproduisent pas le defaut de `CONS-4` : c'est deja la forme de
`cp-emc-regles-de-vie` et de `cp-emc-regle-souhait-loi`, sans que `CONS-1` y morde. Le cas qui a
declenche `CONS-4` est cinq taches sous une consigne ; deux lignes qui partagent une seule paire de
clauses n'est pas ce cas.

### 2.2 Ce que deux paquets couteraient

- **Le double de dette differee** (1,0 au lieu de 0,5), sur un budget d'instance qui n'a pas de
  marge : le planning note explicitement que deux paquets font depasser l'instance C au-dela de
  4,0.
- **Une entree de catalogue de plus, une consigne de plus a ecrire et a auditer** pour un gain
  pedagogique que je ne vois pas : les deux lignes portent le meme objet (l'effort physique), pas
  deux objets distincts qui gagneraient a etre separes pour la clarte de l'enfant.
- **Une frontiere de couture ouverte sans necessite**, la ou `nadia-prof` ecrit elle-meme qu'un seul
  paquet en a zero.

### 2.3 Ce qui converge, et ce qui reste a `nadia-prof`

Ma position rejoint la sienne : elle recommandait deja un seul paquet, sans la trancher seule parce
qu'`ADR-0081` nous la confie a deux. Ce point peut donc se lire comme regle des lors qu'elle
confirme. Ce qui reste a elle, comme pour toute consigne : le texte exact des deux clauses, et la
verification, en jouant, que la frontiere corps-et-effort de `DEC-0083` (section 9.2, citee mot pour
mot dans la story ci-dessous) est effectivement tenue par le contenu ecrit.

---

## 3. Les trois postes finances sans story ecrite (planning, section 10)

Aucun des trois n'a ete engage pendant s33 : le planning l'ecrit noir sur blanc en cloture (section
14, "huit stories payees sur onze", aucune des trois ne figure dans ce compte parce qu'aucune
story ne les portait). **Les trois stories ci-dessous sont donc ouvertes pour s34, jamais pour s33
retroactivement** - rien n'est a rejouer sur ce qui a ete livre pendant s33.

Elles sont ajoutees dans `docs/backlog/backlog.md`, section "Backlog ouvert pour s34 - trois postes
finances par la composition de s33 sans story ecrite (`DEC-0085`, section 10)".

### 3.1 `cm1-anglais`, `ANG-R3` a `R6` -> `S34-ANG-CORR`

Quatre reserves non bloquantes, deja formulees mot pour mot par `nadia-prof` (source :
`squad/sprints/s32-trois-instances-et-ouverture-de-l-eps/verdicts-nadia-instance-A.md`, section sur
`LVE-CM1-07`, datee du 2026-09-09). Aucun jugement de ma part a y ajouter : je transcris ses
proprietes en criteres testables et je route l'audit a elle.

### 3.2 La passe transverse de l'instance d'accumulation -> `S34-TRANSVERSE-1`

Story generique, sur le patron de `S32-3` (deja tiree et livree a s32) : une passe d'audit sur le
depot de contenu servi entier, portee par l'instance qui detient l'accumulation (`PAR-12`) au sprint
ou elle est tiree. Aucun jeu neuf, aucun Done pedagogique prononce - elle produit des reserves ou
rien.

### 3.3 Le lot de l'effort et du corps, `EPS-CP-10` et `EPS-CP-11` -> `S34-EPS-CORPS-EFFORT`

Un seul paquet, conformement a la position rendue en section 2. La frontiere corps-et-effort de
`DEC-0083` (section 9.2) est recopiee mot pour mot dans les criteres d'acceptation, comme cette
decision l'exige pour toute specification de lot touchant ces lignes.

---

## 4. Ce que ce document ne fait pas

- Il ne rend pas la decision de grain de `S33-CONS-4` : il propose une forme et route trois
  questions precises a `nadia-prof` (section 1.6).
- Il ne prononce aucune decision conjointe formelle sur le lot de l'effort et du corps : il constate
  une convergence et attend sa confirmation.
- Il ne modifie aucune ligne de `squad/programme/MATRICE-couverture.md`.
- Il ne chiffre aucune des trois stories neuves contre une borne de sprint : c'est le travail du
  planning de s34, par `sofia-sm`.
- Il ne rouvre pas la borne, le plafond ni l'attente de s33 (`DEC-0085`) : le sprint reste arrete
  tel que `sofia-sm` l'a laisse en section 14 de son planning.
