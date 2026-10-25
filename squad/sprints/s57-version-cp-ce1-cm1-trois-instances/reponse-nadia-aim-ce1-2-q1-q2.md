# s57 - palier 5 : reponse de nadia-prof a Q1 et Q2 de theo-archi sur la garde d'`AIM-CE1-2`

- **Auteure** : `nadia-prof`, professeure des ecoles, seule autorite sur le Done pedagogique.
- **Date** : 2026-10-25. Heure non lue : le shell etait indisponible a l'ecriture.
- **Arbre** : branche `s57-p5-nadia-q1q2`, base `2616a0ba` annoncee par l'agent lanceur, **non verifiee par `git`** (shell
  indisponible).
- **Type** : reponse a deux questions routees (`avis-theo-palier-5-neuf-lots-qlm-ce1.md` section 5, `Q1` et `Q2`). **Aucun Done**,
  aucune condition de Done neuve, **aucun identifiant neuf**. Ni code, ni paquet, ni `DEC`, ni matrice, ni backlog, ni registre.
- **Je n'ai rien joue** : le paquet `ce1-qlm-aimant` n'existe pas.
- **Lu** : `avis-theo-palier-5-neuf-lots-qlm-ce1.md` (sections 0, 1, 5) ; ma `reponse-nadia-palier-5-qlm-items-et-a229.md`
  (section 3, `N3`) ; story `S57-BR-QLM-CE1-06` (`docs/backlog/backlog.md`, lignes 24151-24166).
- Les phrases sont ecrites ici sans accents ; dans le test, elles s'ecrivent avec leurs accents, comme les sondes de `N3`.

## Resume

| Code | Question | Reponse |
|---|---|---|
| `Q1` | *"L'aimant n'attire pas tous les metaux."* doit-elle passer ? | **Oui.** La garde gagne une exemption etroite : la negation du tout, pas toute negation |
| `Q2` | Une bonne reponse generique (*"les metaux"*) doit-elle etre refusee ? | **Oui, en bonne reponse.** Le motif est l'exactitude, deja ecrite ; ma condition reste telle quelle. En distracteur : pas de garde, je lis |

## 1. Q1 : la phrase juste refusee

**Reponse : elle doit passer.**

**Motif.** Ma condition interdit de dire *"que l'aimant attire les metaux"*. Cette phrase dit le contraire, et elle est exacte : le
fer est attire, l'aluminium, le cuivre et l'or ne le sont pas. C'est mot pour mot ce que la story demande d'apprendre (besoin de
`-06` : *"savoir qu'il n'attire pas tous les objets en metal"*). Une garde qui refuse la phrase que la ligne doit enseigner pousse le
developpeur a ne jamais l'ecrire, ni dans une explication ni dans un indice, alors que c'est la phrase qu'un enfant doit garder
apres une erreur sur le papier d'aluminium.

**Ce que l'exemption couvre, et rien de plus.** La negation **du tout** : *ne ... pas tous*, *ne ... pas toutes*, *ne ... pas tout ce
qui*. Pas une negation quelconque. *"L'aimant n'attire pas les metaux."* est fausse (le clou en fer est en metal) : la forme de
`T1` la refuse aujourd'hui, et l'exemption ne doit pas la rouvrir. Je ne demande pas d'etendre la garde a d'autres phrases
fausses : ce que la forme de `T1` laisse passer hors de ses quatre designations (*"L'aimant n'attire aucun metal."*, par exemple)
reste a ma lecture (`QC9`, exactitude).

**Ce que cela change a la garde de theo-archi.** Trois sondes de plus, ecrites en litteraux a cote des sept :

| Sonde | Attendu |
|---|---|
| *"L'aimant n'attire pas tous les metaux."* | **passe** |
| *"Les objets en metal ne sont pas tous attires par l'aimant."* | **passe** (la negation de ma troisieme sonde rouge ; elle verifie que l'exemption ne tient pas a la seule forme *n'attire*) |
| *"L'aimant n'attire pas les metaux."* | **refusee** (elle mord le jour ou l'exemption est ecrite trop large) |

La forme de l'exemption est a `theo-archi`.

**Le faux rouge que `theo-archi` cite a cote** (*"Lequel des objets en metal l'aimant attire-t-il ?"*) : non demande, je le
tranche en une ligne. Il peut rester refuse. La phrase est juste, mais la reformuler (*"Lequel de ces objets l'aimant
attire-t-il ?"*) ne coute rien a l'enfant, et le refus est visible au premier `verify`.

## 2. Q2 : la bonne reponse generique

**Reponse : refusee en bonne reponse.**

**Motif.** Un item *"Qu'attire un aimant ?"* dont la bonne reponse est *"les metaux"* **recompense** la conception fausse que la
ligne doit defaire : l'enfant qui la choisit recoit *juste*. C'est plus grave qu'un indice qui la dit. Et c'est une erreur de fait :
l'aimant n'attire pas le papier d'aluminium. **Je n'ajoute rien a ma condition, et je ne la reecris pas** : elle nomme enonce,
indice et explication, et elle reste ainsi. Le refus repose sur deux regles deja ecrites :

1. **l'exactitude** : une reponse attendue fausse est un motif de refus a elle seule, sur toute ligne ;
2. **`AIM-CE1-1`** : ce qui est attire, ce sont des objets en fer ou en acier ordinaire (*"clou, trombone, epingle, aiguille"*).
   Une categorie de matiere n'est pas un objet, donc pas une cible de la ligne.

**En distracteur** : pas de garde. *"les metaux"* face a *"le clou"* et *"la gomme"* fait un distracteur absurde ou un item
discutable (`TRV-CE1-2`, une seule proposition acceptable) selon l'enonce. Cela se juge item par item ; je le lis a la lecture
avant `verify`.

**Ce que cela change a la garde de theo-archi.** La designation generale de `T1` s'applique aussi a la **bonne reponse** d'un item
dont l'enonce porte une forme d'*attirer*. Deux sondes de plus :

| Sonde | Attendu |
|---|---|
| enonce *"Qu'attire un aimant ?"*, bonne reponse *"les metaux"* | **refusee** |
| enonce *"Lequel de ces objets l'aimant attire-t-il ?"*, bonne reponse *"le trombone"*, distracteurs *"le papier d'aluminium"*, *"la gomme"* | **passe** |

La forme est a `theo-archi`. `camille-po` reporte ces sondes et celles de `Q1` au critere 2 de `S57-BR-QLM-CE1-06`, et cite
comme motif de `Q2` l'exactitude et `AIM-CE1-1`, pas une condition neuve.

## 3. Ce que je garde seule

La garde ne voit ni les paraphrases sans *attirer* (*colle*, *s'accroche*, *attrape*), ni une liste de metaux nommes presentee
comme attiree, ni une designation generale hors des quatre formes, ni la negation fausse qui n'en porte aucune. **Ces quatre cas
restent sous ma seule garde, a la lecture** (`QC9`). Les sondes de cette reponse ne les couvrent pas.

**Signe** : `nadia-prof`, s57, palier 5, 2026-10-25.
