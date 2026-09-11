# [Sprint 34] Rapport de sprint - dernier-sprint-du-plan-2-palier-2-engage

- **Debut** : 2026-09-10 (aucune heure tracee dans `planning.md`)
- **Fin** : 2026-09-11 (aucune heure tracee dans `review.md`)
- **Duree effective** : au moins un jour ; aucune des deux ceremonies ne porte d'heure, et
  aucune n'est inventee pour combler ce manque (voir `RAPPORT-tokens.md`).

## Increment livre

Six lignes du programme ferment (`HG-CM1-12`, `EMC-CP-07`, `EPS-CP-04`, `EPS-CP-08`, `EPS-CP-10`,
`EPS-CP-11`), portant la couverture de 69/138 (50,0 %) a 75/138 (54,3 %) une fois les mouvements
de matrice executes par `camille-po` ; les deux lignes du palier 2 engagees ce sprint
(`QLM-CP-11`, `QLM-CP-14`) restent non couvertes, l'une refusee pour deux motifs pedagogiques et
l'autre jamais livree.

## Decisions du sprint

**Deux `ADR` changent de statut, tous deux mesures par `theo-archi` a la confirmation de la porte
technique du 2026-09-11** :

| Decision | Statut | Ce qui a change |
|---|---|---|
| `ADR-0090` - cesser de compter comme une reussite une manche resolue apres au moins un essai infructueux | `Propose` -> **`Accepte`** | Le rouge construit se referme exactement a la fusion de `s34-theo` sur l'arbre qui porte deja le remede de `s34-sofia` (rouge sur `2c3463b3`, vert sur `5bb6f9db`) ; une mecanique concrete neuve sans scenario rend la regle rouge, verifie par ajout puis retrait |
| `ADR-0080` - la designation sur image comme choix parmi des zones enumerees | reste **`Propose`**, amende | Six de ses huit regles mordent desormais sur du contenu reel (`cp-frise-de-la-journee`) ; deux mordent sur un jeu fabrique d'`adapter-web`, structurellement, et l'une des deux (le plancher de cible tactile) aurait laisse passer le defaut qui a fait retirer `cp-plan-de-la-classe` du sprint. La clause manquante a `Accepte` est nommee dans l'amendement |

**Aucun `DEC` n'est cree ni amende par les ceremonies de ce sprint.** `DEC-0086` (strategie du
sprint, registre de dette a l'ouverture) a ete redigee et amendee par `camille-po` avant
l'ouverture du planning et integree dans le premier commit de s34 comme un prealable ecrit sur le
disque - elle gouverne s34, elle n'est pas un produit des ceremonies de s34. Le registre de dette
mesure a la cloture (section suivante) applique la methode de `camille-po` sans la ratifier : la
ratification lui revient, comme un mouvement de `DEC-0086` a faire ou a refuser, pas comme un
fait acquis par cette review.

## Etat des deux Done

| Done | Juge par | Etat | Reference |
|---|---|---|---|
| Technique | CI (quatre jobs de `.github/workflows/ci.yml` : `build`, `chemins`, `outils`, `prefixes`) | **vert**, les quatre jobs relances et nommes un par un par `sofia-sm` sur l'arbre de travail complet (`a7852398` plus les huit documents de sprint), confirme independamment par `yanis-qa` et `theo-archi` sur `a7852398` seul. Un rouge reel (`PropositionsAccentueesJouablesIT` contre une ligature hors Latin-1) a ete trouve et corrige par un stopgap dans ce meme geste, avant d'annoncer la porte verte | `review.md` section 10, `rapport-qualite.md`, `confirmation-archunit.md` |
| Pedagogique | `nadia-prof` | **mixte, ligne par ligne** : 6 VALIDE SOUS RESERVE avec Done pedagogique prononce (`HG-CM1-12`, `EMC-CP-07`, `EPS-CP-04`, `EPS-CP-08`, `EPS-CP-10`, `EPS-CP-11`), 1 VALIDE SOUS RESERVE sans mouvement de ligne (`S34-ANG-CORR`, correction sur une ligne deja `couvert-valide`), 3 REFUSE (`S34-SCO1-REMEDE`, `S34-EXC-TACHEB`, `QLM-CP-14`) | `verdicts-nadia-instance-A.md`, `-B.md`, `-C.md` |

## Obstacles

**Resolus** :

| Description | Agent route | Decision ou geste | Clos le |
|---|---|---|---|
| `HG-CM1-12` refusee a s33 (`HG-R17`, bloquante) | nadia-prof (audite), marc-dev | verdict instance A, section 3 - VALIDE SOUS RESERVE, ligne fermee | 2026-09-11 |
| `EMC-CP-07` refusee quatre passages consecutifs | nadia-prof (audite), theo-archi (prealable technique), marc-dev | verdict instance B, section 2 - VALIDE SOUS RESERVE, ligne fermee au cinquieme passage | 2026-09-11 |
| `EPS-CP-04`, `EPS-CP-08` refusees a s33 un second passage | nadia-prof (audite), sofia-sm (dev) | verdict instance C, sections 3.1 et 3.2 - les deux motifs bloquants payes a l'objet | 2026-09-11 |
| Cinq motifs d'exemption a comptes perimes sur `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` | theo-archi | corrige avant l'ouverture du sprint, recompte independamment par `yanis-qa` (rapport-qualite.md, section 3.2), aucun compte perime | 2026-09-11 |
| Instruction 1 de `DEC-0061` (ce que trois essais doit vouloir dire quand l'elimination est possible), ouverte neuf sprints | theo-archi (ADR), sofia-sm (implementation) | `ADR-0090`, Accepte - le remede est mesure juste sur les trois mecaniques | 2026-09-11 |
| Le lot de l'effort et du corps (`EPS-CP-10`, `EPS-CP-11`) sans story engageable a temps a s33 | camille-po (story), sofia-sm (dev) | `S34-EPS-CORPS-EFFORT` livree et fermee | 2026-09-11 |
| Deux stories du meme commit qui se defont l'une l'autre (amendement de la retro de s33) | marc-dev, lea-dev, sofia-sm | discipline tenue ce sprint - aucune occurrence signalee par les trois instances | 2026-09-11 |

**Encore ouverts** :

| Description | Agent route | Depuis | Ce qui manque |
|---|---|---|---|
| Six mouvements de `squad/programme/MATRICE-couverture.md`, avec deux mentions de couverture partielle neuves | camille-po | s34 | executer les mouvements (review.md, section 2) |
| `S34-CONS-4` (decoupage de `bandit-manchot-histoire-geographie-cm1` en quatre entrees), non engagee | camille-po (story), marc-dev (execution des quatre consignes), nadia-prof (audit) | s34 (routee des s33) | fenetre de sprint pour l'ouvrir |
| `S34-SCO1-REMEDE` refusee (`SCO-R1`, bloquante) : l'ecran de fin de partie ne montre que le nombre reduit | camille-po | s34 | story qui porte l'ecran, pas le score |
| `S34-EXC-TACHEB` refusee (`EXC-R10`, bloquante) : les cinq indices de la tache B n'ont pas ete touches | camille-po, marc-dev | s34 | reprise des cinq indices dans un meme geste avec `EXC-R11`, `EXC-R12` |
| `QLM-CP-14` refusee (`FRI-R1`, bloquante) : deux textes alternatifs contrefont l'image | lea-dev, via sofia-sm | s34 | deux textes alternatifs et l'indice de l'item 1 |
| `QLM-CP-14` refusee (`FRI-R2`, bloquante) : le pictogramme du lever ne se lit pas a la taille servie | ines-design, via sofia-sm | s34 | forme reconnaissable du pictogramme |
| `QLM-CP-11` jamais livree : geometrie de deux cadres et plancher de lisibilite d'un libelle de zone | ines-design (geometrie), nadia-prof (plancher) | s34 (quatrieme sprint sur cette ligne) | les deux moities, aucune ne suffisant seule |
| Le recouvrement des boutons de zone sur le dessin qu'ils designent, transverse a tout paquet de designation | ines-design, theo-archi | s34 | garde-fou ou correction de forme |
| La regle de poste du registre d'identifiants (ecrire depuis un worktree dedie) n'a tenu pour aucune des trois instances - cinquieme occurrence | theo-archi (contre lui-meme), sofia-sm (orchestration) | s34 (cinquieme fois) | contrainte d'orchestration plutot qu'une sixieme regle de conduite |
| Le faux vert en population vide (proxy structurel, 41/198 methodes) | theo-archi | s34 (troisieme occurrence) | cliquet possible, non pose, dette datee |
| L'ecart "71 titres de l'accueil" contre trois mesures independantes a "70" | nadia-prof, via sofia-sm | s34 | methode citee si 71 est volontaire, ou correction a 70 |
| `PropositionsAccentueesJouablesIT` ne sait pas reproduire une ligature hors Latin-1 (`oe`), stopgap applique | theo-archi (question technique), relecteur-pedagogique (l'occurrence reste sans ligature en attendant) | s34 | decision de fond, trouvee et corrigee dans le meme geste que cette review |

## Couverture programme

- `HG-CM1-12`, `EMC-CP-07`, `EPS-CP-04`, `EPS-CP-08`, `EPS-CP-10`, `EPS-CP-11` : `en cours` ou
  `non couvert` -> `couvert-valide` (mouvements attendus, non executes par cette review - a
  `camille-po`, review.md section 2).
- `QLM-CP-11`, `QLM-CP-14` : restent `non couvert`, aucun mouvement.
- Taux de couverture : 69/138 (50,0 %) a l'ouverture, **75/138 = 54,3 %** une fois les six
  mouvements executes - projection arithmetique recomptee independamment sur les dix-huit
  tableaux (review.md, section 2), pas encore un fait ecrit dans
  `squad/programme/MATRICE-couverture.md`.

## Registre de dette

Mesure a la cloture, methode de `camille-po` appliquee sans etre ratifiee (review.md, section 7) :
**9,0 a l'ouverture -> 9,5 a la cloture**, malgre six lignes fermees. La hausse vient entierement
d'un poste neuf (`cp-eps-corps-effort`, +0,5, sept reserves non soldees faute de fenetre) pendant
que deux paquets qui se clarifient (`cp-emc-regle-souhait-loi`, `cp-eps-regles-de-securite`)
rouvrent chacun une dette de meme montant dans le meme geste. Ratification a `camille-po`.

## Consommation de tokens

`python outils/rapport-tokens.py --sprint s34` **echoue** : `planning.md` et `review.md` ne
portent qu'une date sur leurs lignes d'horodatage, sans heure, et cette absence est une decision
explicite de la review (*"je n'invente pas d'heure de debut que le planning n'a pas ecrite"*) et
non un oubli a corriger apres coup. L'outil ne peut donc calculer aucune fenetre pour ce sprint.
Detail complet, sortie de commande incluse, dans
`squad/sprints/s34-dernier-sprint-du-plan-2-palier-2-engage/RAPPORT-tokens.md`. Aucun chiffre
n'est recopie a la main ni invente a la place.
