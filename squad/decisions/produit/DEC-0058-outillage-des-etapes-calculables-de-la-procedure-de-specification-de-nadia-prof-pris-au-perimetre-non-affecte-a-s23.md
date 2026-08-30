# DEC-0058 - L'outillage des etapes calculables de la procedure de specification de `nadia-prof`
# est pris au backlog, sans entrer au perimetre deja engage de s23

- **Type** : decision de priorisation produit
- **Date** : 2026-08-30
- **Auteur** : camille-po
- **Sprint** : s23 (en cours) pour la decision ; s24 (ouverture) pour la story elle-meme
- **Competence programme visee** : transverse - pas de contenu pedagogique.
- **Statut** : Acceptee

## Contexte

`nadia-prof` signale, dans son arbitrage prealable de s23
(`squad/conformite/ARBITRAGE-PREALABLE-s23-huit-candidats-six-de-contenu-seul-et-deux-de-niveau-2.md`,
section 12.3), qu'elle s'impose desormais une procedure en quatre etapes avant tout arbitrage
prealable, pour attraper une contradiction entre conditions avant qu'elle ne bloque une story - ce
qui s'est produit trois sprints consecutifs sur des specifications qu'elle avait ecrites elle-meme.
Elle ecrit en toutes lettres que les trois premieres etapes - separer forme et distribution, enumerer
la population et y verifier la satisfaisabilite d'une condition, mesurer le taux de reussite d'un
raccourci nomme sur la population que la condition laisse passer - **sont du calcul, pas du jugement
pedagogique**, et *"cela ne me dispense de rien tant que ce n'est pas ecrit"*. Elle ne le demande pas
et ne priorise rien elle-meme (section 12.4 : *"signalement, pas demande"*).

`sofia-sm` porte le signalement comme obstacle 8 dans
`squad/sprints/s23-trois-paquets-parmi-sept/review.md` : **OUVERT, non priorise**, *"a verser au
backlog par `camille-po` si retenu"*. Je reponds a cette question ici.

## Ce que je decide

**Je retiens le signalement et j'en fais une story de backlog**, pour trois raisons verifiees et non
supposees.

1. **Ce n'est pas une question technique que je tranche a la place de `theo-archi`.** La forme de
   l'outil (script autonome, garde-fou execute a la CI, extension d'un outil existant sous
   `squad/outils/nadia-prof/`) lui revient entierement, ainsi qu'a `yanis-qa`. Ce que je decide est
   plus etroit : que cette dette entre au backlog plutot que de rester une phrase signalee dans un
   arbitrage prealable, invisible a qui ne lit pas ce fichier precis.
2. **Le defaut qu'elle nomme a deja coute cette annee-meme.** `ETA-4` et `ETA-7` se sont averees
   incompatibles apres redaction (`avis-faisabilite.md`, s23), et deux des trois defauts de
   specification du sprint ont ete trouves par `nadia-prof` elle-meme en recalculant a la main
   plutot que par un garde-fou. Le meme sprint montre aussi le revers : les trois conditions
   qu'elle a amendees en s'appuyant sur du contenu reel (`ETA-4b`, `RAN-4b`, `RAN-5b`) sont toutes
   tenues sans exception, tandis que les deux refus du sprint (`RAN-7`, `LIA-3`) portent tous les
   deux sur des conditions qu'aucun calcul ne juge (`avis-nadia-done-pedagogique-s23.md`, section 6).
   Outiller la partie calculable ne fermerait ni `RAN-7` ni `LIA-3` - elle le dit elle-meme, section
   12.3 : *"elle ne juge que des conditions calculables"* - mais reduirait le risque, deja materialise
   trois sprints de suite, qu'une contradiction de specification calculable atteigne le contenu reel
   avant d'etre vue.
3. **Le cout d'ecrire la story maintenant est nul et le cout de ne pas l'ecrire est un signalement qui
   pourrit.** `CLAUDE.md` : *"une competence qui n'est pas une ligne de `MATRICE-couverture.md` n'est
   pas suivie [...] une story qui n'est pas dans `docs/backlog/` n'existe pas, meme si elle a ete
   evoquee."* Le signalement existe deja dans trois fichiers (l'arbitrage prealable, l'avis de Done
   pedagogique, `review.md`) sans qu'aucun ne soit un backlog.

## Ce que je ne decide pas

- **Je ne tranche pas la faisabilite ni la forme technique** : route a `theo-archi` et `yanis-qa`,
  qui restent les seuls a juger si les trois etapes sont mecanisables telles que `nadia-prof` les
  decrit, et sous quelle forme.
- **Je ne fixe pas de contenu a cette story au-dela de ce que `nadia-prof` a deja ecrit** : la
  procedure de section 12.3 de son arbitrage prealable est la specification de depart, pas une
  proposition de ma main.
- **Je n'affecte aucune capacite de developpement de s23 a cette story.** `DEC-0057` a deja engage
  la capacite du sprint sur trois paquets de contenu, les deux corrections de refus (`RAN-7`,
  `LIA-3`) et les reserves non bloquantes de `S23-ETA` et de `cm1-problemes-durees`. Ajouter un
  chantier d'outillage neuf au milieu de ce perimetre deplacerait une capacite deja engagee sans
  qu'aucune urgence ne le justifie : rien ne s'est refuse faute de cet outil a s23, et `nadia-prof`
  le dit elle-meme non demande.

## Ou cette story entre

**A l'ouverture de s24**, au meme rang que les items deja routes et non encore priorises par
`DEC-0057` section 3.4 (`TYP-1` "le moyen", obstacle 25 residuel) : ce sont trois chantiers de la
meme famille - rendre executable une garantie qui repose aujourd'hui sur la seule vigilance d'un
agent -, et leur priorisation relative entre eux appartient a `sofia-sm`, avec faisabilite de
`theo-archi`. Je ne recommande pas d'ordre entre les trois : je nomme la famille commune pour que
`sofia-sm` ne les instruise pas separement sans le savoir.

## Ce que je route

- **`sofia-sm`** : verser la story au planning de s24 (ou plus tot si une capacite se libere pendant
  s23 sans toucher au perimetre engage), au meme rang que `TYP-1` "le moyen" et l'obstacle 25
  residuel.
- **`theo-archi`, `yanis-qa`** : faisabilite et forme technique des trois etapes decrites en section
  12.3 de l'arbitrage prealable de `nadia-prof`.
- **`nadia-prof`** : confirmer, une fois un prototype existant, qu'il rend bien le verdict qu'elle
  rendrait a la main sur au moins un cas deja instruit (`ETA-4`/`ETA-7`, `RAN-5`/`RAN-5b`) avant
  qu'il ne remplace une part de sa verification.

## Impact

- `squad/decisions/produit/DEC-0058-...md` (ce fichier) : cree.
- `squad/decisions/produit/DEC-index.md` : ligne `DEC-0058` ajoutee.
- `docs/backlog/backlog.md` : story versee (voir section "Sprint 23", sous-section obstacle 8).
- Aucun fichier sous `domain/`, `application/`, `adapters/` ou `bootstrap/` n'est modifie par cette
  decision.
