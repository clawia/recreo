# [Sprint s07] Planning - 2026-08-10

- **Horodatage de debut** : 2026-08-10 14:05
- **Anime par** : sofia-sm
- **Participants** : camille-po, theo-archi, nadia-prof, ines-design (decisions et propositions
  deja rendues avant ce planning, citees ci-dessous), marc-dev, lea-dev, sofia-sm (developpement a
  venir), yanis-qa (traduction des criteres en tests au fil des commits).
- **Lu avant d'ecrire ce fichier** : `docs/backlog/backlog.md`, section "Sprint 7" (S7-1 a S7-4) et
  section "Backlog - sprints suivants" (story de correction des cinq indices de "Le bon verbe") ;
  `DEC-0015-sprint-7-la-vraie-machine-a-sous-ferme-la-dette-1.md` et ses quatre amendements ;
  `DEC-0016-hauteur-de-viewport-de-reference-et-contenu-au-dessus-du-pli.md` ;
  `ADR-0026-une-animation-retarde-mais-ne-determine-rien-le-levier-n-est-pas-un-bouton-de-plus.md` ;
  `ADR-0027-un-ecran-de-jeu-declare-comment-il-est-arrive-le-mouvement-suit-le-geste.md` ;
  `ADR-index.md` et `DEC-index.md` a jour ; les quatre fiches de `squad/conformite/` fusionnees
  (arbitrage prealable et arbitrage de forme de `nadia-prof` du 2026-08-10, en particulier
  `FICHE-bandit-manchot-additions-cp.md` sections 1, 2, 3, 6.5 et la reserve chiffree sur
  `mangeons`, et `FICHE-bandit-manchot-conjugaison-present-cm1.md` sur l'extinction de la reduction
  a trois propositions) ; `squad/sprints/s07-machine-a-sous/proposition-ines-machine-a-sous.md`.
- **Lignee verifiee avant d'ecrire ce fichier** : `outils/verifier-lignee.sh` execute dans chacun
  des quatre worktrees dedies (`s07-camille-po`, `theo-s07-machine-a-sous`, `s07-nadia`,
  `s07-ines-design`) avant fusion, verdict sain dans les quatre cas. Le depot principal est
  `round-two`, verifie a `28a419f` avant la premiere fusion.

## Fusion des quatre lots

Perimetres verifies par `git diff --stat` avant chaque fusion, pas sur la seule parole de chaque
agent :

| Lot | Branche | Tete | Perimetre verifie |
|---|---|---|---|
| camille-po | `s07-camille-po` | `82a7509` | `docs/backlog/backlog.md`, deux `DEC` produit, `DEC-index.md`. Aucun fichier de code. |
| theo-archi | `theo-s07-machine-a-sous` | `9c2f1c2` | `PartieController.java`, `fragments/manche.html` (marqueur `data-arrivee`), deux tests neufs et un amende, dix-neuf contre-exemples de test, deux `ADR`, `ADR-index.md`. |
| nadia-prof | `s07-nadia` | `e00deaa` | quatre fiches de `squad/conformite/` uniquement. |
| ines-design | `s07-ines-design` | `f4cc598` | un seul fichier, `squad/sprints/s07-machine-a-sous/proposition-ines-machine-a-sous.md`. |

Fusionnes dans cet ordre (produit, architecture, pedagogie, design) par quatre `git merge --no-ff`
successifs, **aucun conflit** : les quatre perimetres ne se recouvrent a aucun endroit. Tete de
`round-two` apres fusion : **`5588b5202e0e44fc7f52d1bf9d4947c3bb40fb51`**.

### Porte technique verifiee avant ce planning

- `mvn -B clean verify`, `JAVA_HOME` positionne sur le JDK 21.0.10 designe par le fichier de
  toolchains le temps de la commande (`outils/verifier-jdk.sh` : `java` du `PATH` en 1.8.0_202,
  JDK de compilation 21.0.10, `JAVA_HOME` vide hors commande, toolchains non lu par Maven) :
  **BUILD SUCCESS**, six modules en SUCCESS.
- Compte de tests, methode : somme de l'attribut `Tests run` de chaque module dans le rapport
  reacteur, **apres un `clean`**, rejoue une seconde fois pour confirmation independante :
  43 (domain) + 7 (application) + 148 (adapter-web) + 32 (adapter-content) + 73 (bootstrap,
  surefire) + 3 (bootstrap, failsafe) = **306**, conforme au chiffre annonce par `theo-archi`
  apres `ADR-0027`.
- Compte de regles ArchUnit, methode : recherche de `ArchRule\s+\w+\s*=` dans les fichiers `.java`
  suivis par Git, exclusion des deux declarations du faux module de test-ressources de
  `LesGardeFousSontMisALEpreuveTest` (jamais compile comme regle du depot) : **28**, inchange.
- Garde-fous de CI, joues localement dans l'ordre ou `ci.yml` les execute : `verifier-prefixes-test.sh`,
  `commit-atteignable-test.sh`, `verifier-chemins-test.sh`, puis les trois suites
  `outils/*-test.sh` (`mesurer-rendu-test.sh`, `verifier-jdk-test.sh`, `verifier-lignee-test.sh`) -
  **tous verts**. Puis application des garde-fous eux-memes sur le contenu reel fusionne :
  `verifier-prefixes.sh` sur les commits de `28a419f` a HEAD (tous prefixes, y compris `[INES]`) et
  `verifier-chemins.sh` sur `git ls-files` (aucun chemin de poste ni nom de compte) - **verts**.

## Objectif du sprint (gele)

**A la review, l'ecran de manche rend une vraie machine a sous - colonnes qui defilent puis
s'arretent chacune sur une proposition, un geste declencheur qui reutilise le bouton d'ouverture de
manche existant, l'enfant choisit la colonne juste - en remplacement du questionnaire a boutons
empiles, sans qu'aucun plancher pedagogique deja gagne (cible tactile, lisibilite, ecart entre
boutons, hierarchie de saillance, ecran de fin de partie sans decor) ne recule, et `nadia-prof`
rend son verdict en jouant l'increment livre.** Les cinq indices fautifs de "Le bon verbe"
(`cm1-conjugaison-present.yaml`) sont corriges et soumis a un nouveau Done pedagogique dans le
meme sprint si la capacite le permet, sans que cela conditionne l'objectif ci-dessus.

**Gele** : aucune ligne de `squad/programme/MATRICE-couverture.md` ne bouge ce sprint (`DEC-0015`,
`DEC-0016`) ; ce sprint reconstruit une mecanique deja jouee, il n'ouvre aucun contenu nouveau.
"Le bon verbe" ne rejoint pas le catalogue ce sprint : la seconde condition de sa reintegration (le
repli de mise en page en deux rangees, confirme par `nadia-prof` sur une maquette jetable mais non
sur du code livre) n'est pas dans le perimetre des quatre stories retenues et n'est pas ouverte par
ce planning.

## Sequencement et arbitrage de capacite

Deux lots de developpement, independants l'un de l'autre, aucune histoire de dependance entre eux :

1. **Chantier mecanique (S7-1 a S7-4) - priorite la plus haute, ne cede pas.** C'est la demande
   explicite de l'utilisatrice et la fermeture de la dette n°1 de `CLAUDE.md`. L'avis de
   faisabilite prealable de `theo-archi` (etape 1 de `squad/README.md` section 9) est deja rendu,
   sous forme de deux `ADR` plutot que d'un simple avis en prose : `ADR-0026` chiffre la mise en
   page et les regles executables, `ADR-0027` ferme le defaut de la reprise silencieuse qu'il
   avait laisse ouvert. L'arbitrage prealable de `nadia-prof` sur les acquis pedagogiques touches
   (etape 10 de `squad/README.md`) est egalement rendu, dans les quatre fiches fusionnees.
2. **Story de correction des cinq indices de "Le bon verbe" - priorite secondaire, cede en
   premier si la fenetre est courte.** Deux motifs. D'abord, son benefice produit immediat -
   remettre "Le bon verbe" au catalogue - reste bloque ce sprint quoi qu'il arrive : `DEC-0015`
   (quatrieme amendement) pose une seconde condition independante, le repli de mise en page livre
   et reverifie sur le rendu reel, qui n'entre pas dans le perimetre des quatre stories retenues.
   Corriger les indices sans lever cette seconde condition ne debloque donc rien ce sprint. Ensuite,
   verifie contre les criteres de `DEC-0015` : cette story ne touche que
   `cm1-conjugaison-present.yaml`, jamais `fragments/manche.html` ni la mecanique - le risque de
   conflit signale par `camille-po` ("si S7-1 modifie le rendu de `.indice`, les deux chantiers
   touchent la meme zone du meme fichier") ne se materialise donc pas : aucun des deux lots ne
   touche le meme fichier. Elle cede en priorite parce qu'elle est la moins urgente, pas parce
   qu'un conflit reel l'exige.

**Consequence sur l'audit de `nadia-prof`.** Un seul audit pedagogique est attendu quoi qu'il
arrive - la machine a sous jouee de bout en bout. Un second, la correction des indices, n'a lieu
que si ce lot est effectivement engage et livre.

## Contraintes qui pesent sur le developpement du chantier mecanique

A lire integralement avant d'ecrire une ligne de gabarit ou de feuille de style. Chaque contrainte
est deja gardee par un `ADR` ou par une fiche de conformite ; cette section les rassemble, elle ne
les invente pas.

- **La metaphore est refusee, la forme validee** (`nadia-prof`, `FICHE-bandit-manchot-additions-cp.md`
  section 1, confirme par `DEC-0015` second amendement) : aucun mot, aucune image, aucun son de jeu
  d'argent ; aucun signal de gain a l'arret des colonnes ; le seul retour de resultat reste la coche
  ou la croix existante ; aucun compteur autre que le score existant.
- **Le levier n'est pas une manette.** `nadia-prof` a confirme nommement son veto sur tout geste de
  traction (fleche vers le bas, manette a demi baissee, glissement). `ines-design` propose un
  bouton-poussoir rond sur `.demarrer` et `.encore` uniquement (`proposition-ines-machine-a-sous.md`,
  section 3) : ce n'est pas un bouton nouveau, c'est l'apparence de celui qui ouvre deja une manche
  (`ADR-0026` section 3) - zero route, zero etat de session en plus, `data-role="action"` inchange.
- **Duree du defilement** : 0,3 s a 1,2 s de l'appui a l'arret de la derniere colonne, decalage
  0,25 s au plus, duree plus decalage au plus 1,2 s (`ADR-0027` clause 6). 0,6 s (courbe `ease-out`)
  et 0,08 s de decalage cumulatif par colonne sont la valeur proposee par `ines-design`. **Trois de
  ces seuils sont gardes par la CI** (`LAnimationRetardeMaisNeDetermineRienTest` etendu) : un
  depassement sera rouge en CI, pas signale a la review.
- **Jamais un autre mot** : a chaque instant, une colonne montre sa propre proposition ou aucun
  texte. Garde par construction dans la forme retenue par `ines-design` (voile translucide devant
  un bouton immobile, jamais une bande de mots qui defile) et par deux clauses executables
  (`ADR-0027` regle 2 : tout texte dans `.propositions` est une proposition ; `content` ne vaut
  jamais que la chaine vide ou `none`).
- **Le repli au-dela de trois colonnes** : deux colonnes par rangee a partir de la quatrieme
  proposition seulement, les cas a deux et trois inchanges a l'octet pres du CSS qui les regit
  (`ines-design` section 5, remesure et confirmee par `nadia-prof` sur le fond). **Confirme sur une
  maquette jetable, non sur du code livre** - ne pas presenter cette confirmation comme un Done
  avant que `nadia-prof` ne l'ait reverifiee sur le rendu reel. Aucun paquet du catalogue actuel
  n'a quatre propositions ; ce repli n'est donc pas une condition de sortie de S7-1 a S7-4, il est
  une latitude ouverte si la capacite le permet.
- **Les planchers** : cible tactile 2,75 rem, lisibilite 1,25 rem y compris le mot du levier,
  chiffre de proposition 2 rem, `gap` 1 rem **sur les deux axes** (`TailleDesTextesEtBoutonsTest`
  amende, regle 3 d'`ADR-0026`). Aucun n'est abaisse. **Fait a connaitre** : a 360 px l'ecart rendu
  vaut exactement le `gap` declare (16 px), il n'y a **aucune marge de 2 px** a recuperer.
- **Reserve chiffree, pas un motif de refus** (`nadia-prof`, meme fiche, section sur la reponse a
  `ines-design`) : sur `mangeons` (paquet "Le bon son", repli a quatre colonnes), il ne reste que
  3 px d'air a droite du mot dans sa colonne de 160 px, contre 10 a 18 px sur les deux items
  qu'`ines-design` avait testes. Rien ne deborde ni n'est tronque, mais la marge est etroite et
  depend de la police du poste. A garder en tete si un developpeur ajuste ces valeurs.
- **L'ecran de fin de partie** ne porte aucun levier, aucune colonne, aucun decor, aucune animation.
  `ADR-0027` le rend structurellement vrai (le marqueur `data-arrivee` n'est pose que sur l'ecran de
  manche, jamais sur `fragments/retour.html`) : ne pose pas ce marqueur sur l'ecran de retour sans
  en avoir mesure la consequence (l'acquis redeviendrait garde par `nadia-prof` seule).
- **`prefers-reduced-motion`** doit laisser un ecran lisible **et jouable** immobile
  (`ADR-0026` clause 2, forme litterale exigee). `nadia-prof` verifiera le resultat sur le rendu
  livre, pas seulement la presence de la regle CSS - point qu'elle n'a pas pu verifier elle-meme
  faute d'increment ecrit.
- **Reponse au clavier** : aucune ligne a ecrire pour la preserver (`ADR-0026`, section "La reponse
  au clavier") a condition de ne pas desactiver, masquer en permanence hors `@keyframes`, ni
  retirer `type="submit"` du bouton de proposition - les quatre clauses de
  `LAnimationRetardeMaisNeDetermineRienTest` gardent deja cette contrainte.

## Stories retenues

| Story | Titre | Competence visee | Affectee a | Sequence |
|---|---|---|---|---|
| S7-1 | Une vraie machine a sous, pas un choix de bouton empile | transverse, mecanique de jeu | lea-dev | chantier mecanique, ne cede pas |
| S7-2 | Le nombre de colonnes suit le nombre de propositions du contenu | transverse | lea-dev | chantier mecanique, meme lot |
| S7-3 | Lire la question avant que les colonnes ne s'arretent | transverse, lisibilite CP | lea-dev | chantier mecanique, meme lot |
| S7-4 | Tirer le levier n'ajoute aucun geste a ce que l'enfant fait deja | transverse | lea-dev | chantier mecanique, meme lot |
| Correction des cinq indices fautifs de "Le bon verbe" | execution de `DEC-0014` | francais CM1, conjugaison present (contenu YAML uniquement) | marc-dev | secondaire, cede en premier si la fenetre est courte |

Les quatre stories du chantier mecanique sont affectees a un seul developpeur (`lea-dev`) parce
qu'elles forment un lot cohesif sur le meme gabarit et la meme feuille de style, dans la continuite
de la proposition d'`ines-design` : les fractionner entre deux developpeurs multiplierait les
risques de conflit sur `fragments/manche.html` et `recreo.css` sans aucun benefice de parallelisme
reel. La correction des indices, isolee sur un fichier YAML distinct, va a `marc-dev` en parallele.

`yanis-qa` traduit chaque critere d'acceptation retenu en test au fil des commits des deux
worktrees et fait tourner la suite. `theo-archi` reste disponible tout le sprint pour tout obstacle
technique remonte en cours de developpement, en particulier sur la forme exacte du marqueur
`data-arrivee` et sur `pointer-events` du pseudo-element decoratif, deux points qu'`ines-design`
remonte explicitement sans les trancher (section 8 de sa proposition).

## Obstacles a ce planning

| Description | Categorie | Destinataire | Statut |
|---|---|---|---|
| Reserve chiffree d'`ines-design` sur son choix de pire cas au nombre de lettres plutot qu'a la largeur de glyphes (`mangeons`, 3 px d'air) | pedagogie / methode de mesure | `ines-design` | transmis pour ses prochaines mesures. N'affaiblit en rien la mesure deja rendue (rien ne deborde), ne bloque pas ce planning. |
| Mecanisme de garde de `DEC-0016` (viewport de reference 360 x 640, propriete "enonce, indice et propositions visibles sans defilement") | technique | `theo-archi` | **routé, n'entre pas dans la capacite de developpement du sprint 7.** `DEC-0016` elle-meme ne choisit pas le mecanisme et le route "le moment venu" ; je saisis `theo-archi` d'un avis de faisabilite en parallele du sprint, sans lui ouvrir de story ni de capacite ce sprint - le chantier mecanique et la correction des indices suffisent a la fenetre. A planifier une fois l'avis rendu. |
| Correction du defaut de pli de "Le bon petit mot" (3 items sur 24, boutons sous le pli quand l'indice s'affiche, jeu catalogue `couvert-valide`) | produit | `camille-po` (deja tranche) | **n'entre pas au sprint 7.** `camille-po` l'a deja ecrit dans `DEC-0016` : "ce n'est pas une story prete a developper ce sprint", le Done pedagogique de "Le bon petit mot" reste accorde et rien ne bloque. Reste une intention non detaillee au backlog, son entree a un sprint futur restera une decision de ma capacite. |

**Fait de gouvernance, pas un obstacle a decider ici** : `theo-archi` inscrit dans `ADR-index.md`
que la cause du defaut corrige par `ADR-0027` est la **troisieme occurrence d'une meme famille de
faute de sa main** - une propriete structurelle (l'element entre dans le document) prise pour la
propriete qui compte (l'element est arrive par un geste), apres `ADR-0022` et la limite 5
d'`ADR-0017`. Je ne le tranche pas, je le porte a la retro de ce sprint plutot que de le laisser se
diluer dans le detail technique d'`ADR-0027`.

## Decisions prises

Aucun `ADR` ni aucune `DEC` n'est cree pendant ce planning : `DEC-0015`, `DEC-0016`, `ADR-0026` et
`ADR-0027` sont anterieurs et deja sur le disque, fusionnes ci-dessus. Les decisions prises ici
m'appartiennent en tant que Scrum Master et n'ouvrent ni `ADR` ni `DEC` :

1. **L'ordre des deux lots et celui qui cede en cas de fenetre courte** (section "Sequencement et
   arbitrage de capacite" ci-dessus) : le chantier mecanique (S7-1 a S7-4) ne cede pas, la
   correction des cinq indices cede en premier.
2. **Les affectations nominatives** de la section "Stories retenues" ci-dessus : `lea-dev` porte
   le lot mecanique en un seul bloc, `marc-dev` porte la correction des indices.
3. **Le mecanisme de garde de `DEC-0016` n'entre pas dans la capacite du sprint 7** : `theo-archi`
   est saisi d'un avis de faisabilite en parallele, sans story ni developpement ce sprint.
4. **La correction du defaut de pli de "Le bon petit mot" n'entre pas au sprint 7**, conformement a
   la position deja ecrite par `camille-po` dans `DEC-0016`.

## Prochaines etapes

**Demarrent maintenant** :

- `lea-dev` ouvre un worktree dedie, verifie sa lignee (`outils/verifier-lignee.sh`), enchaine
  S7-1 a S7-4 en un seul lot sur `fragments/manche.html` et `recreo.css`, en suivant la proposition
  d'`ines-design` et les regles executables d'`ADR-0026`/`ADR-0027`, tests dans le meme commit.
- `marc-dev` ouvre un worktree dedie, verifie sa lignee, corrige les cinq indices de
  `cm1-conjugaison-present.yaml` selon les criteres d'acceptation de la story (repris au mot pres
  du motif de refus de `nadia-prof`), sans toucher aux douze enonces ni aux sept indices deja
  justes.
- `yanis-qa` traduit chaque critere en test au fil des commits des deux worktrees.
- `theo-archi` reste disponible pour tout obstacle technique remonte en cours de developpement, et
  reçoit en parallele la saisine sur le mecanisme de garde de `DEC-0016` (hors capacite de ce
  sprint).

**Une fois les deux lots fusionnes sur `round-two`** : `nadia-prof` joue l'increment de la machine
a sous de bout en bout et rend son verdict ; si la correction des indices est livree, elle audite
ce second lot separement.

**Une fois l'arbre fusionne et les deux verdicts rendus** : `yanis-qa` fait tourner `mvn verify`
sur JDK 21 et ecrit le rapport qualite ; `theo-archi` confirme ArchUnit ; `relecteur-pedagogique`
releve les livrables non executables ; `sofia-sm` ecrit `review.md` puis `RAPPORT.md` et lance le
rapport de tokens.
