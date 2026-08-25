# DEC-0047 - Perimetre du sprint 20 : `NAT-5` entre en trois gestes, scope limite a `cp-nature-mot`
# ce sprint ; une competence neuve ouvre en contenu seul ; `SYN-16`/`S19-6a` s'etend a trois jeux

- **Type** : portee, arbitrage de perimetre
- **Date** : 2026-08-25
- **Auteur** : camille-po
- **Sprint** : s20
- **Competence programme visee** : francais, CP (section 1, `NAT-5`) ; francais, CM1 (section 2,
  competence neuve) ; transverse - pas de contenu pedagogique (section 3, `S19-6a`).
- **Statut** : Acceptee

## Contexte

s19 ferme sur **13/43 (30,2 %)**, denominateur corrige a la cloture (`DEC-0046`). Un seul
arbitrage explicite de l'utilisatrice ouvre s20 : *"prevoit ca pour s20"*, en reponse a l'etat de
`NAT-5`, la derniere reserve bloquante du jeu "Nom ou verbe ?" (`bandit-manchot-nature-mot-cp`),
hors catalogue depuis `DEC-0045`.

Trois faits, tous verifies avant d'ecrire cette decision, en changent la forme par rapport a ce
qu'on pouvait croire savoir a la cloture de s19 :

1. **`NAT-5` n'est pas un chantier de design, c'est un chantier de pipeline.** `ines-design` a
   verifie au rendu servi, pas a la source (branche `hs-ines-nat5`, commit `e5a8360`, non
   fusionnee) : `.enonce` est un noeud texte unique. Aucune regle CSS ne peut viser une sous-chaine
   d'un noeud texte. Marquer le mot cible exige que le controleur l'expose distinctement de l'avant
   et de l'apres de la phrase - un changement de contrat, pas une feuille de style de plus. Elle a
   refuse le contournement qui l'aurait debloquee sans ce changement (parser les guillemets dans le
   gabarit partage), au motif que le gabarit generique connaitrait alors une convention de contenu
   que les jeux sans mot cible n'ont jamais.
2. **`NAT-9` est levee, `NAT-5` tient sur un motif renouvele.** L'utilisatrice a elargi son
   arbitrage sur l'accompagnement du CP a un etat durable (`DEC-0046`, section 3). `nadia-prof` en
   tire, dans `squad/conformite/FICHE-bandit-manchot-nature-mot-cp.md` (deux amendements du
   2026-08-25) : `NAT-9` tombe entierement, la consigne servie est acceptee telle quelle ; `NAT-5`
   tient, mais sur l'asymetrie entre une tache constante (la consigne, lue une fois) et une
   designation qui change a chaque manche (dix fois par partie), que rien dans le produit ne demande
   a l'adulte accompagnant de fournir a la place de l'ecran.
3. **Appliquer le renfort aux jeux deja valides degraderait un Done deja acquis, dans la bande que
   `S19-6a` est charge de fermer ce meme sprint.** `ines-design` a mesure par injection sur
   l'application servie que la regle generique proposee (`.mot-designe`) ne coute rien de mesurable
   a la reference du produit et a 720 px et au-dela, mais qu'a 640 px elle fait passer un item de
   "Les antonymes" (`couvert-valide`) d'au-dessus a en-dessous du pli - une regression mesuree sur
   un jeu que je viens de faire passer `couvert-valide` un sprint plus tot.

## Decision

### 1. `NAT-5` entre au perimetre de s20, en trois gestes, scope limite a `cp-nature-mot`

**Geste 1 (technique, routee)** : le controleur expose le mot cible comme une donnee distincte de
l'avant et de l'apres de l'enonce. Je ne tranche pas la forme du contrat - c'est `theo-archi`, sur
`ADR-0052` et le reste du pipeline de rendu.

**Geste 2 (contenu et forme visuelle)** : le gabarit pose un element distinct autour du mot cible
(la proposition d'`ines-design`, `.mot-designe`, est un candidat, pas une valeur figee), juge par
`nadia-prof` sur au moins deux canaux non colores.

**Geste 3 (catalogue)** : une fois `nadia-prof` reauditee sans reserve bloquante restante,
l'entree revient dans `catalogue.yaml` au meme geste que la fusion du verdict, sur le mecanisme deja
etabli par `DEC-0045` (absence d'entree, pas de drapeau).

**Decision de perimetre que je prends ici et qui n'etait pas ecrite avant ce document : le renfort
visuel entre en service sur `cp-nature-mot` seul, ce sprint. Il n'est pas etendu a `cm1-synonymes`
ni `cm1-antonymes`.**

Motifs :

- **Aucune ligne de matrice n'exige cette extension.** `NAT-5` porte sur "Distinguer les principaux
  types de mots", pas sur "Utiliser des synonymes et antonymes...", deja `couvert-valide`. Etendre
  le renfort aux deux jeux CM1 ne fait avancer aucune competence ce sprint.
- **Le cout mesure n'est pas nul.** `ines-design` l'ecrit elle-meme : elle ne recommande pas
  d'appliquer le renfort aux deux jeux CM1 sans qu'ils repassent devant `nadia-prof` au titre de
  leur propre Done deja acquis - une reouverture de porte pedagogique que rien n'exige ce sprint.
- **Le risque touche exactement la bande que `S19-6a` (section 3 ci-dessous) est chargee de fermer
  dans le meme sprint.** Faire porter aux deux memes jeux, dans la meme fenetre de temps, un
  correctif qui retire du budget vertical (`S19-6a`) et un renfort qui en consomme (le renfort
  ajoute 7,19 px a la hauteur de l'enonce, mesure par `ines-design`) est le genre de collision que
  ce depot demande de nommer avant de la laisser survenir, pas apres.

**Ce que je ne ferme pas.** Le jour ou `S19-6a` est livree et mesuree, la question de generaliser
le marquage aux jeux qui partagent deja la convention du mot entre guillemets peut se reposer, sur
un terrain plus sur. Je ne l'ecarte pas par principe ; je ne vois simplement aucun motif qui
l'impose aujourd'hui, et un changement qui touche un Done deja acquis merite d'avoir un motif.

Le detail complet, les criteres d'acceptation et le routage par agent vivent dans `S20-1`
(`docs/backlog/backlog.md`), pas ici.

### 2. Une competence neuve ouvre en contenu seul, entierement instruite

"Reperer et corriger la majuscule et la ponctuation dans une phrase donnee..." (`DEC-0046`,
section 2) est servable par la mecanique existante, sans arbitrage restant : `nadia-prof` a deja
pose la forme (une phrase, quatre propositions qui nomment ce qui manque) et quatre conditions de
composition (`REP-1` a `REP-4`) avant tout code. J'ouvre `S20-2` et la fiche correspondante
(`squad/conformite/FICHE-bandit-manchot-reperes-cm1.md`), remplie a l'exception du bloc de
validation. La ligne de matrice passe `non couvert` -> `en cours`, jeu propose
`bandit-manchot-reperes-cm1`.

### 3. `S19-6a` entre au perimetre de s20, perimetre etendu a trois jeux nommes

`DEC-0043` avait deja ecrit que `S19-6a` (le correctif de largeur pour la bande 576-719 px) serait
candidate de s20 des lors qu'elle serait affectee, et posait explicitement une question qu'elle ne
tranchait pas encore : *"cette story cible-t-elle tous les paquets de la bande ou seulement ceux
nommes a l'ouverture ? question que je poserai au moment ou `S19-6a` sera affectee"*. C'est ce
moment. Deux audits rendus apres l'ecriture initiale de la story ont change le pire cas connu :
`cm1-antonymes` porte 547 px de largeur necessaire, et `cm1-accord-sujet-verbe` (`couvert-valide`
depuis s19) porte le pire cas reel a **647 px**, sans couvrir la bande basse aujourd'hui.

**Je tranche : les trois jeux nommes** (`cm1-synonymes`, `cm1-antonymes`, `cm1-accord-sujet-verbe`),
et non les seuls jeux connus a l'ouverture de la story. Motif : les trois portent deja un Done
pedagogique acquis a la reference du produit, et rien ne distingue leur droit a la meme bande
basse - retenir seulement le premier jeu nomme reviendrait a fermer une bande "pour de faux" sur
les deux jeux qui l'ont rejointe depuis. **Consequence que je nomme sans la trancher moi-meme** : la
proposition de 620 px d'`ines-design` a ete calculee sur un pire cas de 547 px, avant que le pire
cas ne passe a 647 px. Elle n'est donc plus confirmee par ce document ; `ines-design` la reexamine
sur 647 px avant que `nadia-prof` ne tranche la valeur retenue. Le detail complet et les criteres
d'acceptation corriges vivent dans `S19-6a` (`docs/backlog/backlog.md`), mise a jour dans le meme
geste que cette decision.

`S19-6b` (`BPM-8`) n'entre pas au perimetre de developpement de s20 : elle n'a toujours recu aucune
mesure de faisabilite. Je route a `theo-archi`, via `sofia-sm`, la demande d'avis de faisabilite sur
son propre mecanisme (hauteur de contenu, pas largeur de rangee) - le meme premier pas qui a rendu
`S19-6a` affectable. Si cet avis nomme un chiffre manquant et qu'une mesure de `nadia-prof` le
comble a temps, `S19-6b` peut encore entrer en cours de sprint sur ce mecanisme deja rode ; sinon,
elle reste candidate d'un sprint suivant.

## Ce que je nomme et ne tranche pas - arbitrages de l'utilisatrice

Trois questions restent hors de mon autorite ou hors de portee de cette decision, et je les nomme
plutot que de les trancher a sa place ou de les laisser se perdre :

- **Clavier physique ou virtuel.** Deja posee, elle pese desormais sur le CP comme sur le CM1
  depuis que `nadia-prof` a leve `SAI-11` sur l'alphabetique (`ARBITRAGE-PREALABLE-s19-mecanique-
  de-saisie.md`, second amendement du 2026-08-25) : un clavier virtuel peut corriger ou completer
  une orthographe non produite par l'enfant, ce qui serait grave sur un paquet en mode strict comme
  les homophones. Elle ne pese sur rien que j'ouvre ce sprint (`NAT-5` et `S20-2` sont tous les
  deux a choix parmi des propositions, sans saisie), mais elle bloquera la premiere story de saisie
  le jour ou elle s'ouvrira.
- **Rien, dans le produit, ne s'adresse a l'adulte accompagnant.** `nadia-prof` le nomme elle-meme,
  deux fois, dans ses amendements du 2026-08-25 sur `NAT-5` : le CP entier repose desormais sur la
  presence durable d'un adulte, et le produit ne lui dit jamais rien - ni ce qu'on attend de lui, ni
  qu'il doit designer le mot dix fois par partie tant que `NAT-5` n'est pas livree. Elle me le
  route comme un point produit, sans le trancher. Je fais de meme vers l'utilisatrice : c'est une
  question de perimetre (le produit s'adresse-t-il un jour a un adulte, ou reste-t-il concu pour
  s'adresser uniquement a l'enfant) qui depasse une story corrective.
- **Priorite de la premiere story de saisie.** `ADR-0058` est ecrit, `SAI-11` est levee sur
  l'alphabetique au CP comme au CM1, personne ne l'a implementee. Je ne l'ouvre pas ce sprint : le
  perimetre ci-dessus (`NAT-5`, `S20-2`, `S19-6a`) consomme deja une capacite de developpement que
  je ne double pas par une mecanique neuve dans le meme geste, et les deux questions ci-dessus
  (clavier, adulte) gagnent a avancer avant le premier increment plutot que pendant. C'est ma
  proposition de priorite, pas un arbitrage impose : si l'utilisatrice prefere l'ouvrir a la place
  d'un des trois candidats ci-dessus, c'est son choix a faire, pas le mien a lui imposer.

## Ce que je nomme et diffère, sans en faire un troisieme report

- **`S19-6b` (`BPM-8`)** : premiere fois qu'elle est nommee candidate de sprint (elle etait deja
  "non prete" a s19) ; ce n'est pas un report au sens de `CLAUDE.md`, la demande d'avis de
  faisabilite qui la debloquerait est routee dans le meme geste que cette decision (section 3).
- **La dette nommee par `ADR-0061`** : la regle generale de `DEC-0045` section 3 (un jeu portant
  une reserve bloquante sort du catalogue) n'est encodee par aucun test, faute de forme lisible par
  machine dans les fiches de conformite - `theo-archi` l'ecrit lui-meme comme "dette explicite,
  ouverte ici plutot que passee sous silence", et l'attribue a `nadia-prof` et `camille-po`. Nommee
  ici pour la premiere fois dans un document de perimetre. Je ne l'instruis pas ce sprint : la
  capacite de s20 est deja engagee sur les trois chantiers ci-dessus, et concevoir un format de
  reserve lisible par machine est un travail de conception a part entiere, pas une correction a
  glisser dans un sprint charge. Je la garde visible plutot que de la laisser retomber dans
  `ADR-0061` seul : un prochain sprint qui ouvre du temps de conception y trouvera cette ligne.
- **`AGN-3`** (apostrophes droites dans les indices de "Le bon accord") : reste ce que `DEC-0044`
  en a fait, une petite correction ouverte dans sa fiche, que `sofia-sm` peut affecter a tout moment
  sans DEC dediee. Je ne la reouvre pas ici.

## Ce que je ne fais pas

- Je ne tranche aucun mecanisme technique : la forme du contrat qui expose le mot cible reste a
  `theo-archi` ; le mecanisme de correction de `S19-6a` reste celui deja outille (`ADR-0054` a
  `ADR-0056`).
- Je ne tranche aucune valeur de plafond en pixels ni aucune regle CSS exacte : `ines-design`
  propose, `nadia-prof` tranche.
- Je ne prononce aucun Done pedagogique et ne fais passer aucune ligne a `couvert-valide`.
- Je ne fusionne pas `hs-ines-nat5` moi-meme : hors de mon perimetre d'ecriture, geste de
  `sofia-sm`.
- Je ne decide ni le clavier physique/virtuel, ni si le produit doit un jour s'adresser a l'adulte
  accompagnant, ni si la premiere story de saisie doit remplacer un des trois chantiers ci-dessus :
  arbitrages de l'utilisatrice, nommes ci-dessus, non tranches ici.

## Ce que je route

- **`sofia-sm`** : sequencer les trois gestes de `NAT-5` (`S20-1`), affecter `S20-2` et `S19-6a` au
  planning selon la capacite reelle, transmettre a `theo-archi` la demande d'avis de faisabilite
  pour `S19-6b`, et faire remonter a l'utilisatrice, via le canal deja utilise, les trois questions
  de la section "Ce que je nomme et ne tranche pas".
- **`theo-archi`** : forme du contrat qui expose le mot cible (`S20-1`) ; avis de faisabilite sur
  `BPM-8` (`S19-6b`) ; confirmation du mecanisme de correction de largeur sur les trois jeux nommes
  de `S19-6a`.
- **`ines-design`** : regle CSS du renfort visuel de `NAT-5` (deja proposee, a confirmer sur
  `cp-nature-mot` seul) ; reexamen de la marge de police de `S19-6a` sur le pire cas a 647 px.
- **`nadia-prof`** : juger la forme du renfort de `NAT-5` et reauditer `bandit-manchot-nature-mot-
  cp` en entier ; auditer `S20-2` une fois le paquet livre, contre `REP-1` a `REP-4` ; trancher la
  valeur de plafond de `S19-6a` une fois la proposition d'`ines-design` recue sur le pire cas a
  jour.

## Impact

- `squad/decisions/produit/DEC-0047-...md` (ce fichier) : cree.
- `squad/decisions/produit/DEC-index.md` : ligne `DEC-0047` ajoutee.
- `docs/backlog/backlog.md` : section "Sprint 20" ajoutee (`S20-1`, `S20-2`) ; note ajoutee sous
  `S19-4` (levee de `NAT-9`) ; `S19-6a` mis a jour (perimetre etendu a trois jeux, pire cas corrige
  a 647 px) ; `S19-6b` note de routage de l'avis de faisabilite.
- `squad/programme/MATRICE-couverture.md` : note ajoutee sous "Distinguer les principaux types de
  mots" (levee de `NAT-9`) ; ligne "Reperer et corriger la majuscule et la ponctuation..." passee
  `non couvert` -> `en cours` ; synthese chiffree mise a jour (13/43, 30,2 %, inchange).
- `squad/conformite/FICHE-bandit-manchot-reperes-cm1.md` : cree, rempli a l'exception du bloc de
  validation.
- Aucun fichier sous `domain/`, `application/`, `adapters/` ni `bootstrap/` n'est modifie par cette
  decision.

## Amendement du 2026-08-25 (meme jour) - l'utilisatrice rend les trois arbitrages nommes en section "Ce que je nomme et ne tranche pas"

Les trois questions que j'avais nommees sans les trancher recoivent une reponse le jour meme. Je
n'efface ni ne reecris la section d'origine : elle reste lisible telle qu'ecrite, a l'heure ou ces
trois reponses etaient encore dues. Cette section trace les trois, avec la consequence de chacune
sur le perimetre de s20.

### 1. La premiere story de saisie : oui, en conception seule - un quatrieme chantier s'ajoute, sans capacite de developpement

**Decision de l'utilisatrice** : la conception de la mecanique de saisie reprend a s20, sur le
meme mode que le Lot C de `DEC-0042` a s19 - `theo-archi` et `nadia-prof` instruisent ce qui manque
encore, **aucun developpeur n'y est affecte**. Ma proposition de ne pas ouvrir cette mecanique ce
sprint est donc suivie pour le code et depassee pour la conception.

**Ce que cela change a mon perimetre** : s20 porte un **quatrieme chantier**, purement
documentaire - reprise de la conception de la mecanique de saisie. Rien ne bouge dans
`docs/backlog/backlog.md` sous forme de story a criteres d'acceptation testables : le meme choix
qu'a s19, ou la conception n'a jamais pris la forme d'une story, seulement de decisions et
d'arbitrages prealables. Ce que je nomme comme du au chantier, sans le trancher moi-meme :

- **Le retypage de l'etat de partie que `theo-archi` a deja mesure pour `SAI-12`** (`ADR-0058`,
  amendement du 2026-08-24, section 2) reste la piece connue de ce chantier. Je ne rouvre pas son
  chiffrage : il est fait, et `ADR-0058` dit lui-meme qu'il retire un champ de l'etat de partie au
  lieu d'en ajouter un.
- **Ce que les deux arbitrages ci-dessous (sections 2 et 3) ajoutent a instruire**, nomme et non
  tranche ici : la consequence du clavier qui corrige sur `SAI-2` (section 2) ; et, si elle change
  quoi que ce soit a la lecture des cinq refus `R1` a `R5` ou a la liste des lignes candidates deja
  posee a s19 (`ARBITRAGE-PREALABLE-s19-mecanique-de-saisie.md`), le fait que l'adulte accompagnant
  ne recoive jamais aucune instruction du produit (section 3) - je ne prejuge pas que cela change
  quoi que ce soit a la conception de la saisie, je nomme seulement que `theo-archi` et
  `nadia-prof` gardent la question ouverte pendant qu'ils instruisent.

### 2. Le clavier : ordinateur et tablette, correction automatique acceptee - une question soumise a `nadia-prof`, sans reponse presumee

**Decision de l'utilisatrice** : la saisie vaudra sur les deux appareils de la cible (`DEC-0040`),
et un clavier virtuel qui corrige et complete est accepte, en connaissance de sa consequence dans
les mots de `nadia-prof` - un paquet en mode strict peut crediter une reponse que l'enfant n'a pas
produite. Les deux options qui l'auraient evitee (neutraliser la correction, limiter la saisie au
clavier physique) sont ecartees.

**Ce que cela ferme** : la question "clavier physique ou virtuel", ouverte depuis que `SAI-11` a
ete levee sur l'alphabetique (section "Ce que je nomme et ne tranche pas" ci-dessus), ne reste plus
ouverte en tant que telle.

**Ce que cela ouvre, et que je ne referme pas a sa place** : `nadia-prof` recoit en ce moment meme
la consequence de cet arbitrage sur sa propre condition `SAI-2` (le mode strict, declare par le
contenu, qu'`ADR-0058` a fixe dans le contrat). Un clavier qui corrige rend cette declaration
fausse sans que rien ne le signale a l'ecran. **Trois issues me paraissent possibles - la condition
tient, elle est amendee, ou le mode strict disparait de son arbitrage - et je n'en suggere aucune
ici.** La reponse est **entierement celle de `nadia-prof`**, et elle entre dans ce que `theo-archi`
instruit au chantier de conception de la section 1. Je n'ai rien ecrit ailleurs dans ce document ni
dans `docs/backlog/backlog.md` qui presume laquelle des trois issues sera retenue - verifie en
relisant `S20-1` et `S19-6a`, aucune des deux ne cite `SAI-2` ni le mode strict.

### 3. L'adulte accompagnant : non, jamais - le statu quo conforte le motif de `NAT-5`

**Decision de l'utilisatrice** : tout ce qui est a l'ecran reste ecrit pour l'enfant. Le produit ne
s'adresse jamais a l'adulte, meme si tout le CP repose desormais sur sa presence durable
(`DEC-0046`, section 3). L'adulte lit et explique ce qu'il veut, sans qu'aucun ecran ne le guide.

**Consequence que j'ecris, parce qu'elle vaut d'etre gardee plutot que redecouverte** : cette
reponse confirme, plutot qu'elle n'affaiblit, le motif qui porte `NAT-5` depuis le 2026-08-25.
`nadia-prof` avait deja ecrit qu'un adulte durablement present peut lever une designation
circulaire, mais que rien dans le produit ne lui demande de le faire (`squad/conformite/FICHE-
bandit-manchot-nature-mot-cp.md`, second amendement, section 3). L'utilisatrice vient de confirmer
que rien ne le lui demandera jamais : **une consigne ou un marquage qui ne tiennent que si un
adulte comble le trou restent un defaut du produit, meme quand un adulte est effectivement present
pour le combler**, puisque le produit ne peut ni le savoir ni le lui demander. `S20-1`
(`docs/backlog/backlog.md`) n'a besoin d'aucune reecriture pour porter cette consequence : son
critere sur `NAT-5` est deja formule sans supposer que l'adulte intervient, exactement pour cette
raison.

### Ce que je route, mis a jour

- **`theo-archi`** : ouvrir le chantier de conception de la saisie (section 1) ; instruire, dans ce
  chantier, la consequence du clavier correcteur sur `SAI-2` une fois la reponse de `nadia-prof`
  recue, sans la presumer entre-temps.
- **`nadia-prof`** : trancher laquelle des trois issues nommees en section 2 s'applique a `SAI-2`.
- **`sofia-sm`** : verser ce chantier de conception au planning de s20 sans lui affecter de
  developpeur, sur le meme mode que le Lot C de `DEC-0042`.

### Ce que je ne fais pas, precise

- Je ne trancherai pas, ici ni ailleurs dans ce sprint, l'issue de `SAI-2` : c'est `nadia-prof`.
- Je n'ouvre aucune story de code pour la saisie ce sprint : le chantier reste de la conception.

### Impact de cet amendement

- `squad/decisions/produit/DEC-0047-...md` (ce fichier) : cette section ajoutee.
- `docs/backlog/backlog.md` : section "Sprint 20", paragraphe d'introduction mis a jour pour nommer
  le quatrieme chantier (conception, sans story).
- Aucun autre fichier n'est modifie par cet amendement.
