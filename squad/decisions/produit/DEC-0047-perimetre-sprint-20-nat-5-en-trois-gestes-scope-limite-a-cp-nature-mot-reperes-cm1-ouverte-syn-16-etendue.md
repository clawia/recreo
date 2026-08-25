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
