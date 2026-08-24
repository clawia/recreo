# DEC-0044 - Le score d'un item a deux propositions est accepte comme decoratif ; `NAT-5` et
# `NAT-9` sont routees ; `AGN-3` est notee sans story neuve

- **Type** : arbitrage de perimetre produit, denouement d'une reserve pedagogique ouverte depuis
  s04, routage de deux conditions bloquantes
- **Date** : 2026-08-24
- **Auteur** : camille-po
- **Sprint** : s19
- **Competence programme visee** : transverse pour la section 1 (mecanique) ; francais CP pour la
  section 2 (`NAT-5`/`NAT-9`, "Distinguer les principaux types de mots") ; pas de contenu
  pedagogique pour la section 3.
- **Statut** : Acceptee

## Contexte

`nadia-prof` a audite les quatre paquets neufs du Lot A de `DEC-0042` (branche `s19-nadia`, commit
`ddb0fc4`) : `cm1-antonymes` et `cm1-accords-groupe-nominal` `VALIDE SOUS RESERVE` sans reserve
bloquante, `cp-nature-mot` `VALIDE SOUS RESERVE` avec deux reserves bloquantes, `cm1-accord-sujet-
verbe` `REFUSE`. Les deux premiers verdicts me permettent de faire bouger deux lignes de matrice
(traite dans `squad/programme/MATRICE-couverture.md`, amendement du 2026-08-24). Trois points
restent a mon arbitrage et sont traites ici.

## Decision

### 1. Le score d'un item a deux propositions est accepte comme decoratif, sur l'ensemble du catalogue - je tranche maintenant, je ne reporte pas une troisieme fois

`nadia-prof` a etabli hier (`ARBITRAGE-PREALABLE-s19-antonymes-cm1-et-nature-du-mot-cp.md`,
section B.7) un fait qu'elle avait elle-meme mal formule depuis s04 : sa reserve 5 disait "deux
propositions donnent une chance sur deux au hasard" ; le fait exact, verifie dans le code, est que
`M5` donne trois essais et qu'une manche resolue au deuxieme essai compte comme une reussite. **Sur
un item a deux propositions, un enfant qui se trompe au premier essai a necessairement raison au
second : ce n'est pas une chance sur deux, c'est une certitude de reussir chaque manche.** Elle
applique ce fait aujourd'hui a `cm1-accords-groupe-nominal` (`AGN-2`, huit items sur vingt-quatre)
et `cp-nature-mot` (seize items sur seize, non bloquant pour ce paquet puisque la tache n'a que
deux reponses possibles), et le vise deja sur `cm1-homophones`, `couvert-valide` depuis s18.

**Ce fait m'a ete route trois fois sans que je tranche : a s04 (reserve 5 ouverte), a s17 (reserve
5 redite, non tranchee au motif qu'elle n'etait "pas un motif contre" un retour ponctuel), et
aujourd'hui, ou elle touche deux lignes que je fais bouger dans le meme geste.** Un troisieme
report reviendrait exactement a la derive que ce depot nomme ailleurs : un sujet remis a plus tard
pour un motif defendable a chaque fois, jusqu'a devenir une direction que personne n'a choisie. Je
tranche maintenant.

**Decision : le score des items a deux propositions reste decoratif. Je ne demande pas a
`theo-archi` de reduire `M5` a deux essais quand un item n'a que deux propositions.**

Motifs :

- **`M5` est une regle de mecanique generique, appliquee uniformement a tout item quel que soit le
  nombre de ses propositions.** La faire varier selon le nombre de propositions d'un item
  particulier romprait cette uniformite pour un gain de mesure, alors que le produit n'a jamais
  promis un score qui mesure une performance comparable entre paquets - seulement une mecanique de
  reessai qui laisse le temps de corriger une erreur. `recreo` est un outil de revision, pas un
  outil d'evaluation certificative : le score existe pour motiver, pas pour classer.
- **Le cout de reparer serait plus haut que le cout du defaut.** Faire varier le nombre d'essais
  selon la cardinalite d'un item exigerait une nouvelle regle dans `M5`, un nouvel arbitrage
  pedagogique sur ce que "trois essais" doit vouloir dire quand il n'y a que deux reponses
  possibles (un enfant qui a droit a deux essais sur un item a deux propositions n'a, par
  construction, plus aucune chance d'apprendre de son erreur au second essai non plus, ce qui
  deplace le probleme plutot que de le resoudre), et une verification que cela ne casse aucun
  paquet existant a trois ou quatre propositions.
- **Le precedent existe deja et n'a pas ete conteste.** `cm1-homophones` est `couvert-valide`
  depuis s18 avec exactement ce defaut, sans que quiconque - moi comprise - n'en fasse un motif de
  retrait. Le traiter differemment aujourd'hui parce qu'il touche une ligne que je m'apprete a
  faire bouger reviendrait a durcir ma barre au moment ou elle me coute, ce que `nadia-prof`
  s'interdit systematiquement dans ses propres verdicts et que je m'interdis ici a mon tour.

**Ce que je nomme, pour que ce ne soit pas un silence.** Le score affiche a la fin d'une partie sur
un paquet dont une partie des items n'a que deux propositions ne mesure pas la meme chose, item par
item, qu'un paquet entierement a trois ou quatre propositions. C'est un fait du produit, documente
ici, pas un defaut a corriger. Il vise aujourd'hui `cm1-homophones` (`couvert-valide`),
`cm1-accords-groupe-nominal` (`couvert-valide`, huit items sur vingt-quatre) et `cp-nature-mot`
(`en cours`, bloque par ailleurs).

**Ce que je ne ferme pas.** Si un contenu a deux propositions admet un troisieme candidat
plausible sans changer la competence visee - `nadia-prof` le nomme elle-meme pour `AGN-2` (une
troisieme forme fautive du meme nom, par exemple "chevals") - c'est une amelioration de contenu
ouverte au backlog comme n'importe quelle autre, pas une consequence de cette decision.

### 2. `NAT-5` et `NAT-9` sont routees ensemble, le trou de coordination sur `NAT-5` est corrige

`nadia-prof` maintient bloquante une condition (`NAT-5`) que j'avais moi-meme routee a
`ines-design` sans que la saisine ait lieu - un trou de coordination qui m'appartient, pas a
`lea-dev` ni a `ines-design`. `NAT-9`, neuve, est un defaut de consigne illisible et circulaire
pour un enfant de CP. `nadia-prof` ecrit que les deux se reparent ensemble : le marquage visuel du
mot cible et la consigne qui cesse de nommer ce marquage.

Les deux conditions, avec leur critere correctif exact, sont versees a la story `S19-4` dans
`docs/backlog/backlog.md` (section "Sprint 19"), pas reecrites ici une seconde fois.

**Sequencement** : je ne tranche pas si ce chantier se fait dans s19 (capacite de `lea-dev` et
`ines-design` encore disponible) ou s20 - c'est un arbitrage de `sofia-sm`. Ce que je fixe, c'est
que la ligne "Distinguer les principaux types de mots" ne bouge pas de la matrice tant que les deux
ne sont pas levees et l'increment reaudite par `nadia-prof`.

### 3. `AGN-3` (apostrophes droites dans les indices de "Le bon accord") - notee, pas de story

`nadia-prof` classe `AGN-3` non bloquante : les vingt-quatre indices de `cm1-accords-groupe-
nominal` citent un mot entre apostrophes droites plutot qu'avec les guillemets francais deja
etablis (`SYN-6`, `SYN-9`). Une correction mecanique, sans ambiguite. Je ne l'eleve pas en story
distincte : elle reste ouverte dans `FICHE-bandit-manchot-accords-groupe-nominal-cm1.md`, et
`sofia-sm` peut l'affecter comme une petite correction de contenu au meme titre que n'importe quel
residu non bloquant deja porte par ce depot (`SYN-12`, `ANT-8`), sans qu'un `DEC` distinct soit
necessaire a chaque fois qu'un tel residu est note.

## Ce que je route

- **`sofia-sm`** : sequencer le chantier `NAT-5`/`NAT-9` (s19 si la capacite existe encore, sinon
  s20) ; affecter, si elle le juge utile, la correction mecanique `AGN-3`.
- **`ines-design`** : proposer le renfort visuel du mot cible pour `NAT-5` (jamais par la seule
  couleur, `nadia-prof` tranchera).
- **`lea-dev`** : reecrire la consigne de `cp-nature-mot` pour `NAT-9` une fois le renfort visuel
  de `NAT-5` connu, les deux se reparant dans le meme geste selon `nadia-prof`.
- **`nadia-prof`** : reauditer `cp-nature-mot` une fois `NAT-5` et `NAT-9` traitees. Aucune demande
  nouvelle sur le score decoratif : la question qu'elle avait routee est tranchee par la section 1.

## Ce que je ne fais pas

- Je ne demande aucun changement de mecanique a `theo-archi` sur `M5`.
- Je ne fais bouger aucune ligne de matrice ici : les deux lignes qui bougent sont traitees dans
  `squad/programme/MATRICE-couverture.md`, pas dans ce fichier.
- Je ne tranche pas la forme du renfort visuel de `NAT-5` ni le texte exact de la consigne
  corrigee : propositions routees, jugement de `nadia-prof`.
- Je ne prononce aucun Done pedagogique.

## Impact

- `squad/decisions/produit/DEC-0044-...md` (ce fichier) : cree.
- `squad/decisions/produit/DEC-index.md` : ligne `DEC-0044` ajoutee.
- `squad/programme/MATRICE-couverture.md` : deux lignes francais CM1 passent `couvert-valide`
  (traite dans ce fichier, reference ici) ; ligne "Distinguer les principaux types de mots" reste
  `en cours`, note ajoutee renvoyant a `DEC-0044`.
- `docs/backlog/backlog.md` : criteres correctifs `NAT-5`/`NAT-9` ajoutes a `S19-4` ; statut de
  refus et motifs verbatim ajoutes a `S19-3`.
