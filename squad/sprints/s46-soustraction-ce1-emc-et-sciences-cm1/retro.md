# Retro - s46 - soustraction posee CE1, emotion et institutions d'EMC CM1 (instance A) et
mouvement/phenomenes de sciences CM1 (instance B), dispositif a deux instances paralleles de
`nadia-prof`

- **Date** : 2026-10-02.
- **Casquette** : `sofia-sm`, Scrum Master.
- **Nature du sprint** : entierement pilote par agents, sans ceremonie de daily - conforme a
  `squad/README.md`. Cinq lots de developpement affectes nominativement des l'ouverture du
  planning (`marc-dev` seul sur le lot de complexite moderee, `lea-dev` et `sofia-sm` casquette
  dev sur deux lots chacun), deux instances de `nadia-prof` auditant chacune un sous-ensemble
  disjoint des cinq lots.

---

## 1. Ce qui a coute - le trou de mesure reactorielle de `marc-dev`

**Une mesure partielle sur un seul module a laisse passer un rouge que seul le reacteur complet
voyait, et cette famille de defaut etait deja connue du depot.** `marc-dev` a mesure son lot
(`MAT-CE1-05`) avec `bash outils/mvn-jdk21.sh -B -pl adapters/adapter-content -am test`, qui
construit les dependances amont du module mesure (`domain`, `application`) mais ne construit ni
n'execute jamais les modules avals qui dependent de lui. `TexteAccentueExposeParLePortEntrantTest`,
dans `bootstrap`, balaie le catalogue reel et verifie que chaque chaine qu'il expose (identifiant,
titre, consigne, competence) est inventoriee ; trois chaines neuves de
`bandit-manchot-soustractions-posees-ce1` y manquaient. Le rouge n'est apparu qu'a la fusion
complete des cinq lots (`8ab9ebf1`), mesure par `theo-archi` dans sa confirmation ArchUnit (section
5 : *"5 tests, 3 echecs, BUILD FAILURE"*), trouve avant cela par moi en preparant la mesure de
cloture, et corrige par moi (commit `346274c8`, 2026-10-02 00:33:41) avant que `yanis-qa` ne
mesure.

**Ce n'est pas une premiere occurrence de cette famille.** `squad/README.md`, section 9, cite deja
un incident du meme genre a s29 : *"une consigne de mesure limitee au module qui a laisse passer
un rouge que seul le reacteur complet voyait"*, et `CLAUDE.md` nomme la regle generale depuis :
*"toute mesure partielle du reacteur porte `-am`"*. **`marc-dev` a suivi cette regle a la lettre,
et elle n'a pas suffi** : `-am` construit ce dont le module mesure a besoin (ses dependances),
jamais ce qui a besoin de lui (ses dependants). `bootstrap` depend d'`adapter-content`, pas
l'inverse ; aucune combinaison de `-pl adapter-content -am` ne le construit ni ne l'execute. La
regle ecrite jusqu'ici fermait un defaut (`-pl` sans `-am`, qui lit un ancien jar installe) et pas
l'autre (`-pl ... -am`, qui ne voit jamais un module aval). J'ai amende `squad/README.md`, section
9, etape 2, pour nommer cette distinction et la regle qui en decoule : toute mesure partielle d'un
commit qui touche `catalogue.yaml`, `squad/decisions/REGISTRE-identifiants.md`, ou tout autre
fichier de contenu servi, inclut desormais `bootstrap` dans son perimetre.

**Ce que cet incident n'a pas coute.** Le correctif est intervenu avant toute mesure de cloture
(`yanis-qa`, `theo-archi`) : aucun des deux rapports de porte technique n'a jamais vu ce rouge sur
l'arbre qu'il mesurait. Le cout reel est celui d'un geste refait deux fois (la mesure de `marc-dev`,
puis la mienne pour le corriger) plutot qu'une porte technique faussement verte.

## 2. Ce qui a marche - trois refus pedagogiques reels, tous leves dans la meme matinee

**Le dispositif a deux instances a produit trois refus reels sur trois lots distincts
(`MAT-CE1-05` une fois, `EMC-CM1-09` deux fois, `SCI-CM1-16` deux fois), et chacun a ete leve par
une correction testee et revalidee dans le meme bloc de travail**, sans qu'aucun ne reste ouvert a
la cloture. Deux motifs recurrents parmi ces cinq refus : une erreur d'exactitude factuelle
(`EMC-CM1-09` item 4, la vitesse votee au lieu d'etre fixee par arrete du maire ; `SCI-CM1-16`
item 2, le Vesuve presente comme un lieu distinct du volcan) et une incoherence interne au paquet
lui-meme introduite par une correction precedente (`EMC-CM1-09` items 10-11, une formulation
exclusive sans source ; `SCI-CM1-16` item 11, une consigne de securite qui contredit l'item 7 du
meme paquet). Le second motif est notable : **deux corrections ont chacune introduit une erreur de
la meme famille que celle qu'elles corrigeaient** - `nadia-prof` le nomme elle-meme sur
`SCI-CM1-16`, en ecrivant que le motif du second refus est *"la meme erreur que celle de l'item 4
au premier passage"*. Cela n'a coute aucune perte : chaque correction a ete rejouee en entier par
l'instance
qui l'avait refusee, pas seulement l'item modifie, ce qui est precisement ce qui a permis de voir
la contradiction nouvelle plutot que de la laisser passer sur la foi du diff.

**L'amendement pedagogique prealable a `SOP-CE1-5`, ecrit avant l'engagement du sprint, a tenu sans
reprise.** `MAT-CE1-05` etait **infaisable en l'etat** selon l'avis de `theo-archi` (les trois
formes d'origine du distracteur rendaient la bonne reponse toujours predictible) ; `nadia-prof` a
leve ce blocage par une quatrieme forme ("emprunt reporte deux fois") avant le planning, et le seul
refus que ce lot a subi ensuite (`SOP-CE1-4` sur deux items) est sans rapport avec cet amendement :
la condition neuve elle-meme a tenu du premier coup.

## 3. Ce qui reste a risque - `SOP-CE1-R1`, un arbitrage que `nadia-prof` s'adresse a elle-meme

**`MAT-CE1-05` ferme avec une reserve d'une nature differente des dix autres reserves du sprint.**
Les reserves `EMC-CM1-R5` a `EMC-CM1-R7`, `MVT-CM1-R1` a `MVT-CM1-R4` et `PHN-CM1-R1` a `PHN-CM1-R4`
(ces dernieres levees) sont des demandes de correction de contenu, routables a `camille-po` puis a
un developpeur. `SOP-CE1-R1` ne l'est pas : `nadia-prof` y constate que, sur les deux items sans
emprunt du paquet, sa propre condition (`SOP-CE1-5`) n'admet que la somme comme distracteur, et
qu'une somme depasse toujours le premier terme - un enfant peut donc l'ecarter sans poser
l'operation, par un simple sens de grandeur. Elle ecrit elle-meme que *"la limite vient de ma
condition, pas du paquet"*, et demande un arbitrage qui ne revient qu'a elle : admettre une
cinquieme forme de distracteur pour ces items, ou accepter la limite par ecrit. **J'ai route cette
reserve comme une story d'arbitrage adressee a `nadia-prof` elle-meme, distincte des stories de
correction de contenu ordinaires** (`docs/backlog/backlog.md`), parce qu'aucun developpeur ne peut
honorer ce critere d'acceptation avant que `nadia-prof` ait tranche sur sa propre clause - le
paquet n'est pas a reprendre avant cet arbitrage, motif qu'elle a ecrit elle-meme. Le risque que je
retiens : si cette story est un jour affectee a un planning sans que quiconque relise qu'elle
attend d'abord un arbitrage et non un correctif, un developpeur pourrait tenter une correction de
contenu qui ne repond pas a la question posee.

## 4. Actions pour la suite

**Je route, je ne tranche pas, conformement a mon role.**

- `SOP-CE1-R1` reste routee a `nadia-prof` elle-meme (section 3) ; je ne lui fixe pas de sprint,
  c'est a `camille-po` de l'engager quand l'arbitrage sera rendu.
- Les quatre stories de correction differee non bloquantes (`EMC-CM1-R5`/`R6`, `EMC-CM1-R7`,
  `MVT-CM1-R1` a `R4`) sont deja ecrites dans le backlog par `camille-po` ; je ne leur fixe pas de
  sprint non plus.
- L'amendement de `squad/README.md` section 9 (etape 2, mesure partielle incluant `bootstrap`) est
  deja ecrit dans ce meme geste de retro ; aucune action supplementaire n'est due de ma part sur ce
  point.
- La question d'une troisieme instance de `nadia-prof` reste refusee par elle (section 7 de
  `review.md`) ; je ne la relance pas, les trois conditions cumulatives de reouverture n'etant pas
  reunies.

## Decisions prises

**Un amendement a `squad/README.md`** (section 9, etape 2 "Developpement") : toute mesure
partielle d'un commit qui touche `catalogue.yaml`, `squad/decisions/REGISTRE-identifiants.md`, ou
tout autre fichier de contenu servi, inclut desormais `bootstrap` dans son perimetre de mesure, en
plus du module modifie. Motif et preuve : section 1 ci-dessus.

Aucune `DEC` ni aucun `ADR` n'est cree ou amende par cette retro : elle constate ce qui a fonctionne
et ce qui a coute dans l'orchestration de s46, et amende la seule regle de process qui s'en degage.
