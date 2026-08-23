# [Sprint s18] Rapport de cloture - fermer avant ouvrir

## L'increment livre, en une phrase

Trois dettes de francais CM1 fermees avant tout contenu neuf - le retour au catalogue des
homophones grammaticaux (`BPM-1`), un defaut de securite pedagogique sur les synonymes (`SYN-11`,
un enfant qui se trompait ne voyait jamais la reponse) et une sortie clavier absente a la
revelation (`S17-C`) - et la ligne de matrice des homophones passe `couvert-valide` pour la
premiere fois depuis le sprint 10.

## Les decisions du sprint

| Numero | Titre | Statut |
|---|---|---|
| `DEC-0041` | Perimetre de s18 : fermer trois dettes existantes avant d'ouvrir du contenu neuf | Acceptee, amendee trois fois en cours de sprint (corrections de criteres, `SYN-16`, cloture) |
| `ADR-0055` | La largeur demandee par une rangee est epinglee - complete `ADR-0054` | Accepte |
| `ADR-0056` | Un seuil de largeur maison est juge sous une regle-`@` comme au niveau superieur ; un ancrage de mutation designe un seul endroit | Accepte, amende une fois (le seuil recoit sa valeur : 744 px) |
| `ADR-0057` | La focalisabilite est declaree par le gabarit, un script qui ne la pose plus ne suffit pas a la garder | Accepte |
| `DEC-0007` | Amendement du 2026-08-23 (troisieme note) : le seuil technique de largeur telephone (S3-8) est revise a 744 px, jamais supprime | Amendement accepte |

Aucune decision de ce sprint n'a ete refusee ni retiree. Le detail de chacune, avec sa preuve de
morsure quand elle en porte une, vit dans `review.md`.

## Etat des deux Done

- **Technique (CI)** : vert. `mvn clean verify` sur l'arbre fusionne final (`9741dfe`) : `BUILD
  SUCCESS`, six modules. **621 tests par la methode de reference** (reacteur et rapports XML,
  concordants), **614 par les rapports texte** - ecart de 7, documente depuis `ADR-0054`, cause
  intacte et non un defaut de cette mesure. **29 regles ArchUnit**, inchange sur tout le sprint.
  Trois garde-fous de depot verts (`prefixes`, `chemins`, `outils`).
- **Pedagogique (`nadia-prof`)** : trois verdicts, tous prononces sur l'increment reellement servi,
  jamais sur une regle injectee ou une promesse. **`S17-4` VALIDE SOUS RESERVE** (aucune reserve
  bloquante) - `BPM-1` tenue, `BPM-5` fermee. **`S17-3` VALIDE SOUS RESERVE** (aucune reserve
  bloquante) - `SYN-11` fermee, mais la ligne de matrice ne bouge pas, `SYN-4` (antonymes) manquant
  toujours. **`S17-C` VALIDE, sans reserve** - la seule mesure du sprint sans rien a reprocher au
  premier passage.

Les deux Done sont independants, comme le veut le principe du depot : un increment technique
irreprochable (621 tests verts) a d'abord ete **refuse** pedagogiquement deux fois le matin meme du
2026-08-23 (`BPM-5`, `SYN-11` non encore fermees sur l'increment injecte), avant d'etre accorde
l'apres-midi sur l'increment corrige et **servi**.

## Le mouvement de couverture

**9/46 (19,6 %) -> 10/46 (21,7 %)**, gain de 2,1 points. Une seule ligne bouge : francais CM1,
homophones grammaticaux, `en cours` -> `couvert-valide` (`DEC-0024`, s10). **La serie que
`DEC-0037` nommait - quatre sprints consecutifs (s14 a s17) sans mouvement reel de couverture -
s'arrete a s18.** `camille-po` refuse d'en faire une victoire d'ensemble et je porte les deux
moities de son constat : `S17-3` (synonymes) obtient le meme type de verdict pedagogique
favorable mais reste `en cours`, faute d'antonymes - "le chantier francais CM1 n'est qu'a moitie
sorti de la stagnation qu'il visait a rompre."

## Obstacles resolus et obstacles encore ouverts

**Resolus ce sprint** : les deux defauts d'un meme critere de `S17-4` (corriges par `camille-po`) ;
`SYN-13` requalifiee en observation ; le mecanisme de mise en page de `SYN-11` (arbitre par
`ines-design` puis `nadia-prof`, `SYN-11` fermee) ; la charge de mesure de `nadia-prof` sur trois
campagnes lourdes (sequencee sans qu'aucune ne soit creditee sans etre jouee) ; trois trouvailles de
garde-fou trouvees en essayant de defaire un correctif plutot qu'en relisant du texte, corrigees
avec preuve de morsure (`ADR-0056`, `ADR-0057`) ; un ecart de process (matrice editee hors worktree
dedie, meme famille que l'incident de s06) detecte avant tout commit, sans perte.

**Encore ouverts, aucun bloquant** : `SYN-16`/`BPM-8` (le correctif ne mord pas entre 576 et
719 px de viewport - `camille-po` a tranche que cette bande designe un appareil reel, le mecanisme
reste a instruire par `theo-archi`) ; l'hypothese de fenetre maximisee non nommee dans l'inventaire
d'appareils d'`ines-design` ; `SYN-12` (3 items sur 16 perdent la fin de leur indice) ; `BPM-6`
clause 2 (touche morte a la revelation) ; `BPM-7` (marge de 9,81 px sous le seuil de bruit de
police, declencheur de remesure explicite) ; `M22` (l'ecran `erreur.html` sans cible de focus
declaree, audit degrade) ; `M23` (rien n'annonce que le troisieme echec revele la reponse) ; la
migration `ADR-0049`, hors perimetre, candidate du prochain sprint. Le detail complet, avec
destinataire et etat, est dans le tableau des obstacles de `review.md`.

## Consommation de tokens

Genere par `python outils/rapport-tokens.py --sprint s18` ; le detail complet vit dans
`RAPPORT-tokens.md` et dans `squad/rapports/JOURNAL-tokens.md`.

| Agent | Modele | Tours | Input | Output | Cache cree | Cache lu |
|---|---|---:|---:|---:|---:|---:|
| `camille-po` | claude-sonnet-5 | 212 | 424 | 99 147 | 2 155 636 | 69 198 156 |
| `sofia-sm` | claude-sonnet-5 | 204 | 408 | 74 518 | 1 829 662 | 39 675 099 |
| `theo-archi` | claude-opus-5 | 298 | 596 | 161 881 | 1 751 020 | 54 664 857 |
| `marc-dev` | claude-sonnet-5 | 131 | 262 | 38 252 | 291 196 | 13 564 763 |
| `lea-dev` | claude-sonnet-5 | 238 | 476 | 99 340 | 843 495 | 37 551 154 |
| `nadia-prof` | claude-opus-5 | 442 | 884 | 243 650 | 5 584 497 | 95 443 048 |
| `ines-design` | claude-sonnet-5 | 75 | 150 | 48 642 | 835 160 | 7 862 181 |
| orchestrateur (fil principal) | claude-opus-5 | 95 | 190 | 243 034 | 1 573 991 | 66 678 088 |
| **Total** | | **1695** | **3 390** | **1 008 464** | **14 864 657** | **384 637 346** |

Aucune invocation mesuree sur cette fenetre pour `yanis-qa` et `relecteur-pedagogique` : je le dis
explicitement plutot que de laisser un chiffre absent se lire comme un zero.
