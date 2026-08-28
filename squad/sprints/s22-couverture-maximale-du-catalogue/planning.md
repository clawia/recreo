# [Sprint s22] Planning - 2026-08-28

- **Horodatage de debut** : 2026-08-28 17:07
- **Anime par** : sofia-sm
- **Participants** : camille-po, theo-archi, nadia-prof, marc-dev, lea-dev, sofia-sm

## Etat de la fusion d'ouverture

Trois lots ecrits avant ce planning, fusionnes ce jour dans un worktree dedie (`s22-sofia`), depuis
`round-two` a `f0d5d5a` :

| Branche | Tete au moment de la fusion | Contenu |
|---|---|---|
| `s22-camille` | `20f5cd6` | `DEC-0053`, `DEC-index`, backlog, matrice, `squad/TEMPLATES.md` (clause `MNC-1`), trois fiches de conformite |
| `s22-theo` | `27db5fb` | `avis-faisabilite.md` de ce repertoire, 648 lignes |
| `s22-nadia` | `c50067d` | `squad/conformite/PORTE-PEDAGOGIQUE-apres-arbitrage-du-2026-08-28.md` |

**Lignee verifiee avant de fusionner, pas supposee.** Le checkout principal a bascule de branche
pendant que `nadia-prof` ecrivait : ses deux premiers commits (`9f9489e`, `ad66c1f`) sont des
ancetres reels de `s22-camille` (`git merge-base --is-ancestor` confirme), et son troisieme
(`c50067d`, tete de `s22-nadia`) est un report du meme contenu ecrit sous un autre identifiant
(`2969074`, deja present dans `s22-camille`) - verifie par une comparaison de diff (`git diff
e68be6c 2969074` et `git diff ad66c1f c50067d` produisent exactement le meme patch, zero ligne
d'ecart). Consequence tiree avant de fusionner, pas apres : fusionner `s22-camille` seule apportait
deja le contenu complet de `nadia-prof`. Les trois branches ont neanmoins ete fusionnees (`git
merge --no-ff`, dans cet ordre : `s22-camille`, `s22-theo`, `s22-nadia`) pour que l'historique
porte chaque branche dediee ; la fusion de `s22-nadia` produit un merge commit a **zero fichier
modifie**, exactement comme predit - a ne pas compter une seconde fois dans `review.md` ni dans
`RAPPORT.md`. Aucun conflit sur les trois fusions.

Ce defaut de coordination (trois branches dediees demandees sans trois worktrees dedies) est acte
en amendement a `squad/README.md` section 4 et section 9, voir plus bas.

**Nom du repertoire de sprint : conserve.** `theo-archi` l'a cree (`s22-couverture-maximale-du-
catalogue`) faute de perimetre ecrit au moment de son premier commit. Il decrit exactement
l'objectif du sprint et son avis de faisabilite (648 lignes, deja commite et fusionne) le cite a
onze reprises : le renommer casserait ces references pour un gain nul. Je le garde.

**Porte technique, mesuree apres la fusion, pas avant** :

```
bash outils/mvn-jdk21.sh -B clean verify
```

sur le reacteur complet (racine, aucun `-pl`). Resultat et methode de comptage : voir la section
"Porte technique" en fin de document.

## Objectif du sprint

Couverture maximale du catalogue attendu (consigne de l'utilisatrice, 2026-08-28), sans point de
coupe impose (arbitrage 2). Trois paquets CM1 deja arbitres pedagogiquement, zero code a ce jour :
`S13-2` (calcul mental, multiplier/diviser par 10 et 100), `S13-3` (problemes de durees), `S17-2`
(comprehension de texte). Si les trois aboutissent sans cycle de refus supplementaire, la matrice
passe de **18/43 (41,9 %)** a **21/43 (48,8 %)** (`DEC-0053` section 4) - **un objectif de sprint,
jamais un Done** : aucune ligne ne passe `couvert-valide` par ce document, ce statut n'est prononce
que par `nadia-prof` apres audit reel de chaque increment. Si `S17-2` ne se debloque pas a temps,
l'objectif retombe a 20/43 (46,5 %) sur les deux lignes mathematiques seules. **Gele** : il ne
change plus pendant le sprint.

**Fait a nommer, verse par `camille-po` (`DEC-0053` section 7), qui depasse ce sprint et que je ne
tranche pas** : recompte sur la population complete des 25 lignes non `couvert-valide`, le plafond
de couverture atteignable sans jamais ecrire de mecanique neuve est de **36/43 theorique**, **27/43
realiste a court terme** une fois ecartes l'audio (bloque sur une source de voix humaine qui
n'existe pas, hors capacite de developpement) et le visuel (aucune architecture d'item ecrite). A
la borne de trois paquets par sprint et en retirant les trois candidats de s22, il ne reste que
**6 lignes de contenu seul non encore arbitrees** pour alimenter les sprints suivants : **s22 est
l'avant-dernier sprint que la mecanique unique peut remplir seule sur le seul contenu deja
arbitre**, pas les quatre a six qu'estimait `DEC-0030`. Ce n'est pas mon perimetre a trancher ; je
le nomme pour que le prochain sprint de perimetre (`camille-po`) ne le decouvre pas a la cloture.

## Stories retenues, ordre impose

**Contrainte double qui gouverne l'ordre, pas la seule priorite de gain de couverture** : `S17-2`
n'est **jamais livree en dernier** (demande de `nadia-prof` - ses conditions `COMP-1`/`COMP-2`
coutent quarante epreuves avant meme de jouer, et un paquet qui arrive tard ne peut pas absorber
une correction) ; et `S17-2` **commence par le code**, pas par le contenu (`theo-archi` : la
fabrique de contenu ignore sans un mot toute cle YAML inconnue - un `texteSupport:` ecrit avant que
le champ n'existe en Java laisse le build vert et met l'enfant devant un ecran sans texte). Ces deux
contraintes se satisfont ensemble en ouvrant `S17-2` en premier, malgre son gain de matrice classe
en priorite 3 par `camille-po` (`DEC-0053` section 4, un classement par gain de couverture, pas un
ordre de livraison) : elle est la plus longue a construire (code sur cinq fichiers, quatre modules),
donc celle qui a le plus besoin d'avance pour ne pas finir en dernier.

| Ordre | Story | Titre | Competence programme visee | Decision liee | Affectee a |
|---|---|---|---|---|---|
| 1 | `S17-2` | Comprendre un texte narratif ou documentaire | francais, CM1, comprehension de texte | `DEC-0053`, `ADR-0050`, `ADR-0062` (patron `partDesignee`) | `marc-dev` |
| 2 | `S13-2` | Calcul mental - multiplier et diviser par 10 et 100 | mathematiques, CM1, calcul mental | `DEC-0053`, arbitrage prealable du 2026-08-15 | `lea-dev` |
| 3 | `S13-3` | Resoudre des problemes de durees | mathematiques, CM1, grandeurs et mesures | `DEC-0053`, arbitrage prealable du 2026-08-15, `ADR-0041` | `sofia-sm` |

**Sequence imposee a `S17-2`, que le planning ne peut pas inverser** (avis de faisabilite section
3.3) : (1) prealable non technique deja leve par `nadia-prof` dans `PORTE-PEDAGOGIQUE-apres-
arbitrage-du-2026-08-28.md` section 3.3 (`COMP-4` et `COMP-7` tombent, premier motif de `COMP-6`
tombe - fiche `FICHE-bandit-manchot-comprehension-cm1.md` reste a mettre a jour par `camille-po`,
sans quoi la fiche reste perimee mais le developpement n'est plus bloque) ; (2) le champ
`texteSupport` et son classement d'ecran (`AucunItemNeRepeteLEcranDUnAutreItemDuMemePaquetTest`)
dans le meme commit ; (3) le test qui traverse la frontiere HTTP (`ADR-0050` clause 6) ; (4) le
contenu. Ecrire les items avant le champ les ecrirait contre un plafond qui n'existe plus, ou sans
savoir s'ils atteignent l'ecran.

`S13-2` et `S13-3` sont faisables en l'etat, sans une ligne de Java de production (schema `question`
suffisant, arbitrages prealables rendus, verrous de `DEC-0030` leves) : YAML sous `contenu/`, entree
`catalogue.yaml`, classe de garde-fou de paquet - la convention de tout paquet du depot.

**Obstacles techniques pris par `theo-archi` lui-meme, hors affectation aux trois developpeurs** :
- **Obstacle 16, elargi** : cinq garde-fous de contenu bornent leurs tirages a 60 (un sixieme a
  150) alors qu'un paquet du catalogue en declare 99 - 39 items non examines, cinq fois, en
  silence. `ADR-0064` (numero reserve) a ecrire avec sa regle dans le meme geste : bornes derivees
  du paquet plus un meta-garde-fou de source.
- **Obstacle 23** : `outils/mvn-jdk21.sh` affirme un JDK sans le verifier (journal de build
  contradictoire, aucune consequence sur la CI ni le bytecode livre). Lot court, pas d'`ADR`.

**A router explicitement a `theo-archi` pendant le sprint, pas affecte comme story** : la regle 4
du contrat de forme (`ADR-0063` decision 3, "aucun item ne laisse l'indice ni le verdict sous le
pli") a perdu son demandeur avec l'arbitrage 1 ; un enonce non executable qui perd son demandeur
porte sa date plutot que de disparaitre en silence - c'est a `theo-archi` de l'amender, `ADR-0063`
n'etant pas sa decision a moi.

## Conditions de `nadia-prof` pour tenir trois paquets, portees comme conditions du sprint

`camille-po` tient la borne de capacite a trois (moyenne 2,67 sur s19-s21, `DEC-0053` section 3) ;
`nadia-prof` s'est corrigee a trois contre elle-meme apres verification en source primaire -
convergence independante des deux, pas une seule mesure recopiee. Elle retombe a **deux** si deux
cycles de refus surviennent ou si les paquets arrivent dans les deux derniers jours du sprint. Ce
n'est pas un voeu, ce sont des conditions d'execution :

1. Candidats deja arbitres pedagogiquement avant tout code - c'est le cas des trois retenus.
2. **Livraison echelonnee** : pas trois paquets audites le meme jour. L'ordre ci-dessus (`S17-2`,
   `S13-2`, `S13-3`) sert aussi cette condition.
3. Comptes fournis par les auteurs eux-memes dans le rapport de chaque story, jamais recopies d'un
   autre agent sans reverification.
4. Aucun arbitrage de forme route pendant l'audit d'un des trois paquets.
5. Au plus un cycle de refus sur l'ensemble des trois.

## Ce que l'arbitrage 1 change pour tout le catalogue - `MNC-1`

Le pli sort de la porte pedagogique pour tout `Done` prononce a partir du 2026-08-28 (lecture
**large** : indice, verdict, propositions, tout element du meme bloc). Contrepartie ecrite et
opposable, versee par `camille-po` dans `squad/TEMPLATES.md` : la clause `MNC-1` est **obligatoire**
sur tout `Done` `VALIDE` ou `VALIDE SOUS RESERVE` a partir de ce jour, sur les trois paquets de ce
sprint comme sur tout paquet futur. `REP-8` reste ouverte, sa dette est croissante et assumee -
`theo-archi` etablit qu'aucune regle technique executable du depot ne gardait le pli et que rien
n'en devient mort : aucun `ADR` de retrait n'est ouvert. **Risque nomme, a surveiller pendant le
sprint plutot qu'a l'ignorer** : deux garde-fous du depot (`PaquetCm1ReperesTest`, trois motifs pour
`PROPOSITIONS_PAR_ITEM = 3` dont un seul le pli ; `COMP-6`, deux motifs dont un seul le budget
vertical) portent un motif au singulier sur une regle qui en porte plusieurs - relire "le motif est
tombe" comme "la regle n'a plus d'objet" serait faux sur les deux.

## Ce que j'ai tranche sur les cinq amendements proposes a ma retro de s21

Ecrits dans `squad/README.md` (section 4 et section 9) dans le meme geste que ce planning, pas
seulement annonces ici :

1. **Mesurer sur l'union des arbres du sprint avant fusion, quand un lot durcit un lecteur
   partage.** Confirme par `theo-archi` avec trois precisions (avis-faisabilite.md section 5).
   Ecrit.
2. **Mesurer une clause de filtrage contre la population entiere qu'elle gouverne, au moment ou
   elle est ecrite.** Tranche et adopte par `nadia-prof`, bornee aux populations enumerables
   (`PORTE-PEDAGOGIQUE-apres-arbitrage-du-2026-08-28.md` section 4). Ecrit.
3. **Une regle de conduite trouvee en cours de sprint est ecrite dans un fichier du depot avant la
   cloture qui la constate.** Je l'adopte. Un troisieme cas vient de se produire ce jour meme (voir
   "Etat de la fusion" plus haut) : trois branches dediees demandees sans trois worktrees dedies.
   J'ecris les deux (la regle generale et sa consequence sur la paire branche/worktree) dans le
   meme geste.
4. **Distinguer, dans un chiffrage de capacite, un cycle cause par le contenu livre d'un cycle
   cause par la specification prealable.** `camille-po` applique deja cette distinction dans son
   rechiffrage d'ouverture de s22 (`DEC-0053` section 3, tableau sprint par sprint) sans la nommer
   comme un amendement permanent. Je la lis comme une adoption en substance et j'ecris la regle en
   son nom conjointement au mien, qui la reconduit desormais a chaque rechiffrage de capacite.
5. **Regime de retrait du catalogue sur un `REFUSE` (`DEC-0045` section 3).** Reste ouvert, verse
   par `camille-po`, faute d'un troisieme cas. Je ne tranche rien : ce n'est pas mon role.

**Un point route, non tranche, laisse a une prochaine ceremonie** : `theo-archi` propose de
relancer `campagne-budget-vertical.sh` sur le catalogue servi a chaque cloture de sprint et de
commiter le releve, seul moyen de savoir de combien la dette de `REP-8` a grandi (avis-faisabilite
section 2, point 2, "a trancher par `camille-po` et `sofia-sm`"). Je decide ma moitie : je relance
cette mesure a la review de ce sprint. Le choix de la rendre obligatoire a toute cloture future
reste a `camille-po`.

## Avis de faisabilite - theo-archi

| Story | Avis | ADR requis |
|---|---|---|
| `S17-2` | Faisable moyennant du code (cinq fichiers, quatre modules, patron `partDesignee`) | Non - `ADR-0050` a deja acte la decision |
| `S13-2` | Faisable en l'etat | Non |
| `S13-3` | Faisable en l'etat, reserve non bloquante sur `D-2` (redaction, gardee par `nadia-prof` seule) | Non |
| Obstacle 16 (bornes de tirage fixes) | Pris par `theo-archi` | Oui - `ADR-0064`, numero reserve |
| Obstacle 23 (`mvn-jdk21.sh`) | Pris par `theo-archi` | Non |

Avis complet : `squad/sprints/s22-couverture-maximale-du-catalogue/avis-faisabilite.md`.

## Porte technique, mesuree apres fusion

```
bash outils/mvn-jdk21.sh -B clean verify
```

Sur le reacteur complet (racine, `f6f3867` - fusion des trois lots sur `s22-sofia`, avant tout
code de sprint).

**Resultat : BUILD SUCCESS.** Methode : `bash outils/mvn-jdk21.sh -B clean verify` sur le reacteur
complet, aucun `-pl`, apres `clean`. Compte de tests obtenu en sommant le bloc `Results:` de
chaque module (Surefire, plus Failsafe pour `bootstrap`) :

| Module | Tests | Echecs / erreurs |
|---|---|---|
| `recreo-domain` | 49 | 0 |
| `recreo-application` | 13 | 0 |
| `recreo-adapter-web` | 332 | 0 |
| `recreo-adapter-content` | 224 | 0 |
| `recreo-bootstrap` (unitaires) | 98 | 0 |
| `recreo-bootstrap` (integration, Failsafe) | 6 | 0 |
| **Total** | **722** | **0** |

Aucun module sans test. `mvn-jdk21.sh` a compile et execute sur le JDK 21.0.10 de la toolchain
(confirme par les lignes `Found matching toolchain for type jdk` de chaque module) - l'obstacle 23
(l'enveloppe affiche parfois une autre version sans le verifier) n'a eu aucune consequence sur ce
build.

## Decisions prises

Aucune decision de perimetre ou de pedagogie n'est prise par ce document : `DEC-0053` (perimetre,
arbitrages 1 et 2) et la porte pedagogique de `nadia-prof` sont deja ecrites et fusionnees avant ce
planning, je les reprends sans les rouvrir. Ce que ce document tranche en propre, sous ma
responsabilite de Scrum Master : le nom du repertoire de sprint (conserve), l'ordre des trois
stories et leur affectation nominative, les amendements 1 a 4 a `squad/README.md` (ecrits), ma
moitie de la decision sur la remesure de `REP-8` a la review.

## Prochaines etapes

- `marc-dev` ouvre un worktree dedie (`s22-marc`) et commence `S17-2` par le prealable non
  technique deja leve, puis le champ `texteSupport` et son classement d'ecran.
- `lea-dev` ouvre un worktree dedie (`s22-lea`) et commence `S13-2`.
- `sofia-sm` (casquette dev) ouvre un worktree dedie (`s22-sofia-2`) et commence `S13-3` apres
  `S13-2`, pour respecter la livraison echelonnee.
- `theo-archi` ouvre `ADR-0064` avec sa regle et corrige `outils/mvn-jdk21.sh` (obstacle 23), dans
  des worktrees dedies.
- `camille-po` met a jour `FICHE-bandit-manchot-comprehension-cm1.md` (`COMP-4`, `COMP-6`,
  `COMP-7`) pour refleter la levee deja ecrite par `nadia-prof`.
