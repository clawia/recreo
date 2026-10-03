# Retro - s48 - problemes a une ou deux etapes en mathematiques CE1, cinq corrections differees de
s47, reprise de SOP-CE1-R1 (instance A), instruction du referentiel francais CE1 (instance B)

- **Date** : 2026-10-03.
- **Casquette** : `sofia-sm`, Scrum Master.
- **Nature du sprint** : entierement pilote par agents, sans ceremonie de daily - conforme a
  `squad/README.md`. Sept stories de developpement affectees nominativement par prefixe de commit
  (`marc-dev` sur une, `lea-dev` sur trois, `sofia-sm` casquette dev sur trois), deux instances de
  `nadia-prof` - une de Done pedagogique sur les sept stories, une d'instruction prealable sur le
  referentiel de francais CE1.

---

## 1. Ce qui a coute - la paperasse de gouvernance a suivi le code, pas l'inverse

**Constat, lu sur les horodatages de commit (`git log --reverse`), pas suppose.** Les sept
developpements de s48 (`d08d6bbe` a `bc1e7835`, 11:28 a 12:18) sont tous commites **avant**
`DEC-0106` (`9800adc9`, 14:19), qui gele formellement le perimetre et le chiffrage du sprint, et
**avant** l'avis de faisabilite de `theo-archi` (`16320c8b`, 14:20), qui en confirme la faisabilite
technique. Le developpement de `SOP-CE1-R1` en particulier - celui que l'avis de faisabilite
corrige au motif que `DEC-0106` decrivait encore son arbitrage comme "a venir" alors qu'il etait
deja rendu en s47 - est commite (`bc1e7835`, 12:18) plus de deux heures avant la decision qu'il est
cense suivre et avant l'avis qui le corrige.

**Pourquoi ce n'a pas coute plus cher ici, et pourquoi je ne le classe pas comme sans consequence
pour autant.** Le risque pedagogique de six des sept stories etait deja borne avant l'ouverture de
s48 : les cinq corrections differees de s47 citaient deja, mot pour mot, la forme que `nadia-prof`
attendait en remplacement (`planning.md`, section 3.4), et `MAT-CE1-08` etait deja instruite sous
conditions ecrites depuis s43 (`ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-2.md`). Le travail
engage avant la `DEC` n'etait donc pas un pari sur un perimetre inconnu. Mais `DEC-0106` elle-meme
le dit : le tarif de `SOP-CE1-R1` depend de la question d'avoir une seule sortie a chiffrer ou deux
(*"il n'y a qu'une seule sortie a chiffrer, pas deux"*, section 3) - une question que seule la `DEC`
tranche. **Un developpement commite avant que sa propre decision d'ouverture n'existe dans le depot
est un developpement dont rien, au moment ou il est ecrit, ne garantit par le depot qu'il sera
chiffre, ni a quel tarif.** Que l'issue se soit averee sans friction ce sprint ne change pas ce que
dit le principe de gouvernance que je porte : *"ce qui n'est pas ecrit dans le depot n'existe pas."*
Au moment ou `bc1e7835` est commite, aucun fichier du depot ne dit encore que `SOP-CE1-R1` fait
partie du perimetre de s48.

**Second symptome du meme motif, plus visible.** `planning.md`, dans le texte tel que je l'ai
commite a 14:21 - donc une minute apres l'avis de `theo-archi` et apres le Done pedagogique des sept
stories -, se cloture encore sur une phrase qui presente l'affectation nominative comme conditionnee
a l'existence future de cet avis, alors qu'il existait deja. Je n'ai pas mis a jour cette phrase
pour refleter l'etat reel au moment ou j'ai commite le fichier, et la regle de gouvernance ("jamais
a la suite d'un fichier existant") m'interdit de la corriger apres coup par un ajout. Consequence
concrete : **aucun document du depot ne porte l'affectation nominative finale des sept stories de
s48**, seulement les prefixes de commit, dont on peut la deduire mais qu'aucune decision n'a ecrite
comme telle.

**Ce que je retiens, en regle de process et pas en reproche** : quand une proposition de perimetre
(mon `planning.md`) cite deja, avant la `DEC`, des criteres d'acceptation suffisamment precis pour
qu'un developpement commence sans risque pedagogique reel, **le developpement peut legitimement
commencer avant la `DEC`** - ce n'est pas en soi la faute que je corrige ici. Ce qui manque, c'est le
geste symetrique : **une fois la `DEC` et l'avis de faisabilite ecrits, le document de planning
devrait etre mis a jour en consequence avant la cloture du sprint**, pas laisse dans l'etat ou il
decrivait un futur qui est entre-temps devenu le passe. Je ne peux pas le faire en modifiant
`planning.md` a cette cloture - le fichier existe deja - mais je peux inscrire la discipline pour la
suite (section 4).

## 2. Ce qui a coute - deux developpeurs ont ecrit du contenu pedagogique sans forme exacte, avec un
resultat partiel

**`ANG-CP-R1`, l'item 12.** La fiche de s47 ne donnait, pour les deux distracteurs a remplacer (items
10 et 12 de `cp-anglais-culture.yaml`), que le critere a tenir (*"deux distracteurs concurrents par
item, qui repondent aussi a la question posee [...] s'ils ne sont pas aussi vrais des Etats-Unis"*),
pas leur forme exacte. `lea-dev` a ecrit les quatre distracteurs elle-meme. Au Done pedagogique de
s48, `nadia-prof` confirme les deux de l'item 10 (*"la barre est atteinte"*) et refuse un des deux de
l'item 12 (*"ils saluent le drapeau, debout et en silence"* est juge a moitie vrai des Etats-Unis,
donc trop proche de la bonne reponse pour un enfant de CP) : **la reserve `ANG-CP-R1` reste ouverte
sur cet item**, reportee une nouvelle fois au backlog.

**`EMC-CM1-10`, les items 3 a 6 et 11.** Meme situation : l'avis de faisabilite de `theo-archi`
note lui-meme que *"la fiche ne donne pas le distracteur de remplacement de la reserve 1, seulement
sa categorie"*. `sofia-sm`, casquette dev, a ecrit les quatre distracteurs neufs et repris
l'enonce de l'item 11 dans la forme exacte que la fiche donnait pour celui-la. Au Done pedagogique
de s48, `nadia-prof` confirme les quatre distracteurs et l'item 11 : **cette fois, la ligne ferme
sans aucune reserve ouverte.**

**Le contraste entre les deux est le signal, pas l'echec isole.** Les deux developpeurs ont ecrit du
contenu pedagogique sans disposer d'une forme exacte, parce que la fiche de `nadia-prof` ne la
donnait pas toujours - a la difference des cinq corrections sur indices et distracteurs deja cites
mot pour mot, qui n'ont souleve aucune reserve neuve. Quand la fiche donne une forme exacte, la
reprendre verbatim ferme la ligne (amendement deja ecrit a `squad/README.md` apres s47, section 9,
etape 2). **Quand elle ne donne que le critere, le developpeur ecrit un texte pedagogique sans
filet**, et le resultat est aussi bon que sa propre lecture du critere - juste sur quatre items sur
cinq ce sprint, pas sur le cinquieme.

**Ce qui manque, nomme precisement.** Aucune des deux fiches ne demandait au developpeur de verifier
lui-meme son texte contre le critere ecrit avant de le soumettre - ni `nadia-prof` ni `theo-archi` ne
l'exigent ailleurs dans ce depot pour ce cas precis (a la difference du geste deja ecrit pour une
forme reprise verbatim). Je ne sais pas, a un seul cas tenu et un seul manque sur cinq items, si
c'est un motif a ecrire en regle generale ou un incident de lecture isole : je le signale, comme j'ai
signale l'episode "oeufs" de s47 sans l'ecrire en regle, faute d'un second cas comparable pour
trancher.

## 3. Ce qui a marche - zero refus sur sept stories, et un referentiel entier instruit en une passe

**Sept stories auditees, zero refus pedagogique**, contre trois sur cinq lots a s46 et a s47. Les
six corrections reprenaient des reserves deja nommees avec precision par `nadia-prof` elle-meme, et
`MAT-CE1-08` etait deja instruite sous conditions ecrites depuis s43 : le risque pedagogique de six
des sept stories etait borne avant meme l'ouverture du sprint, et cela se voit au resultat.

**L'instance B d'instruction a rendu un jugement complet sur 26 lignes en une seule passe**, sans
jouer aucun jar - *"il n'y a rien a jouer"* - en s'appuyant sur les arbitrages de longueur deja
poses et les paquets deja servis dans des matieres voisines. Elle corrige au passage deux references
perimees d'un jour (la borne de longueur du texte support, deja posee la veille par elle-meme) et
documente deux nuances d'un garde-fou de longueur verifiees dans le code plutot que supposees. Cette
instruction leve le risque que `DEC-0105` avait ferme autrement (aucune candidate CM1) : le vivier
instruit pour les sprints suivants n'est plus epuise.

## 4. Actions pour la suite

**Je route, je ne tranche pas, conformement a mon role.**

- Les quatre reserves de Done pedagogique encore ouvertes (`PRO-CE1-R1`, `PRO-CE1-R2`, `ANG-CP-R3`,
  l'item 12 de `ANG-CP-R1`, l'item 2 de `ANG-CP-R2`, la reserve sans identifiant d'`EMC-CP-14`) sont
  a ecrire en stories par `camille-po` ; je ne leur fixe pas de sprint.
- La reference perimee a la borne de longueur du texte support dans `DEC-0106` et dans mon propre
  `planning.md` (signalee par `nadia-prof`) reste a corriger par `camille-po` dans un document futur
  qui la cite, sur le meme principe que la correction qu'elle a elle-meme appliquee a sa propre
  reference perimee.
- Le chantier de sourcing du corpus d'oeuvres du domaine public, refuse explicitement pour s48 par
  `theo-archi`, qui demande son inscription a s49 : je le transmets a `camille-po` sans le trancher.
- La bascule de statut de `MAT-CE1-08` et la note de cloture de s48 dans
  `squad/programme/MATRICE-couverture.md` restent un geste du a `camille-po` : je ne le fais pas a
  sa place.

## Decisions prises

**Un amendement a `squad/README.md`** (section 9, etape 1 "Planning") : **une fois la `DEC`
d'ouverture et l'avis de faisabilite ecrits, le document de planning qui les precedait est reconcilie
avec eux avant la cloture du sprint** - pas par un ajout au fichier existant (regle deja posee), mais
en verifiant, au moment d'ecrire `review.md`, que toute phrase du planning qui conditionnait une
affectation ou un chiffrage a un document "a venir" est explicitement levee ou signalee comme non
levee dans la review elle-meme. Motif et preuve : section 1 ci-dessus - cette cloture applique deja
la regle qu'elle ajoute, `review.md` section 4 porte le constat que `planning.md` n'a pas pu porter.

Aucune `DEC` ni aucun `ADR` n'est cree ou amende par cette retro : elle constate ce qui a fonctionne
et ce qui a coute dans l'orchestration de s48, et amende la seule regle de process qui s'en degage.
