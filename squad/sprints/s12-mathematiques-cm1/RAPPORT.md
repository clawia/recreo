# [Sprint s12] Rapport de sprint - trois lignes de mathematiques CM1 ouvertes, un Done pedagogique de douze sprints perdu et retrouve le meme jour

- **Fin** : 2026-08-15 15:06 (`review.md`)
- **Debut** : aucun horodatage officiel - pas de `planning.md` pour ce sprint (voir `retro.md`,
  point 1). Le premier commit `s12` fusionne sur `round-two` est horodate 2026-08-14 18:02:53,
  cite pour reperage, pas comme un debut de sprint officiel.

## Increment livre

Trois jeux de mathematiques CM1 (perimetre de figures usuelles, conversions d'unites, tables de
multiplication) entrent au catalogue sur une matrice qui ne comptait aucune ligne CM1 entamee.
Perimetre et conversions sont **VALIDE SOUS RESERVE** des le premier audit ; les tables, refusees
une premiere fois (la bonne reponse n'etait jamais la plus petite des trois propositions), sont
corrigees et revalidees **VALIDE SOUS RESERVE** le jour meme.

**Le fait le plus lourd du sprint n'etait pas au planning** : `cp-additions-jusqu-a-10`, `VALIDE`
depuis douze sprints, a perdu son Done pedagogique en cours de sprint (une strategie "toujours
l'intermediaire" gagnait 66,45 % des manches sans lire l'enonce, cause trouvee dans la fabrique de
contenu et non dans le paquet), puis l'a retrouve le jour meme apres correction, revalide sur
12 000 manches reellement jouees.

Plus : l'abandon de la persistance acte (`DEC-0028`, amendement d'`ADR-0001`) ; deux criteres de
garde-fous derives d'une phrase universelle (`ADR-0039`) ; l'interdiction qu'un point de focus
declare soit lui-meme destructeur (`ADR-0040`) ; une clause de focus encodee pour tout ecran a
partie en cours ; la reserve N de `nadia-prof` levee au clavier (69 arrivees sur 69, contre 12 sur
69 avant correction).

**Ce sprint n'est ni un succes simple ni un incident isole : la meme famille de defaut - un
garde-fou vert qui ne rencontre pas ou ne demande pas la bonne chose - est apparue sous trois
formes techniques distinctes (perimetre enumere en s11, critere enumere puis echantillon trop
court en s12) plus une variante humaine vieille de douze sprints, chacune revelee par la
precedente.**

## Decisions du sprint

| Reference | Titre | Type | Statut |
|---|---|---|---|
| ADR-0039 | Un critere se derive des que la promesse est universelle, et une enumeration passe du cote autorise | Architecture | Accepte |
| ADR-0040 | Un point de focus declare n'est jamais destructeur, l'ecran de confirmation n'est pas exempte | Architecture | Accepte |
| ADR-0001 | Decouper le build en cinq modules Maven et orienter les dependances vers l'interieur | Architecture | Accepte a l'origine ; **amende ce sprint** - declencheur de creation d'`adapter-persistence` ferme par `DEC-0028` |
| ADR-0013 | Un garde-fou derive son perimetre et se prouve mordant | Architecture | Accepte a l'origine ; **amende ce sprint** - la case "critere enumere" de son tableau etait trop indulgente, corrigee par `ADR-0039` |
| ADR-0014 | Vendoriser une bibliotheque CSS tierce et deriver les garde-fous du rendu | Architecture | Accepte a l'origine ; **amende ce sprint** - une limite nommee sur `JAVASCRIPT_TIERS_REFUSE` ne gardait rien, corrigee par `ADR-0039` |
| ADR-0038 | Interdire tout arret de tabulation destructeur a une tabulation d'un point de depart | Architecture | Accepte a l'origine ; **amende ce sprint** - clause 3 ajoutee par `ADR-0040`, ferme l'ecart que ses deux clauses d'origine ne couvraient pas |
| DEC-0028 | Abandon de la persistance : recreo ne conserve aucune partie entre deux sessions | Produit | Acceptee ; **completee le 2026-08-15** - la phrase plus large d'`ADR-0001` reste gardee, non etendue |
| DEC-0029 | Perimetre du sprint 12 : trois lignes de mathematiques CM1 ouvertes, cinq differees | Produit | Acceptee |

**Huit decisions actees ou modifiees ce sprint** (deux `ADR` neufs, quatre `ADR` amendes, une `DEC`
neuve completee, une `DEC` neuve). Aucune section n'est laissee vide.

## Etat des deux Done

| Done | Juge par | Etat |
|---|---|---|
| Technique | CI (`mvn -B clean verify`) | **Vert.** Rejoue independamment sur l'arbre fusionne (`round-two` a `295e007`) apres un `clean` complet, JDK 21.0.10 designe par `JAVA_HOME` (JDK par defaut du `PATH` en JRE 8) : BUILD SUCCESS, six modules, **464 tests**, **28 regles ArchUnit** (27 par `@ArchTest`, 1 invoquee a la main sur un perimetre distinct, methode inchangee depuis s06). Garde-fou de chemins en code de sortie 0. |
| Pedagogique | nadia-prof | **Quatre verdicts, tous VALIDE ou VALIDE SOUS RESERVE a la cloture.** Perimetre et mesures (conversions) : `VALIDE SOUS RESERVE` des le premier audit. Tables de multiplication : `REFUSE` puis `VALIDE SOUS RESERVE` ("sans mesure d'automatisation") apres correction de `lea-dev`, rejoue par `nadia-prof` le jour meme. Additions CP : `VALIDE` depuis s03, `REFUSE` en cours de sprint (biais de fabrique), `VALIDE` sans reserve retrouve le jour meme apres correction de `marc-dev`. Aucun de ces verdicts n'est prononce, simule ou retire par moi - je les reconstruis depuis `squad/programme/MATRICE-couverture.md` et les fiches de conformite, pas sur parole (`review.md`). |

## Obstacles

**Resolus** :

| Description | Agent route | Decision | Clos le |
|---|---|---|---|
| Fabrique d'additions place mecaniquement la bonne reponse au rang intermediaire (66,45 %), Done pedagogique de douze sprints retire | marc-dev | correction de la fabrique, revalidee `VALIDE` par `nadia-prof` | 2026-08-15 |
| Distracteurs des tables de multiplication toujours inferieurs au produit (bonne reponse jamais la plus petite, plus grande sur 64,4 %) | lea-dev | correction (voisins du dessus), revalidee `VALIDE SOUS RESERVE` par `nadia-prof` | 2026-08-15 |
| Test de non-negativite des distracteurs d'additions vert a tort sur un echantillon de 50 tirages a une graine | marc-dev | echantillon porte a 5000 tirages | 2026-08-15 |
| P-6 (perimetre) enumerait deux familles de distracteurs et fabriquait un rang intermediaire a 90 % | nadia-prof | condition P-6 neuve, non retroactive | 2026-08-15 |
| Un point de focus declare pouvait etre lui-meme destructeur (Entree seule detruisait la partie) | theo-archi | `ADR-0040`, clause 3 | 2026-08-14 |
| Critere enumere par liste de refus, vert par defaut sur un objet non nomme (`alpine.min.js` reel non refuse) | theo-archi | `ADR-0039` | 2026-08-14 |
| Declencheur de creation d'`adapter-persistence` laisse ouvert par `ADR-0001` | theo-archi, camille-po | `DEC-0028`, amendement d'`ADR-0001` et de `CLAUDE.md` | 2026-08-14 |
| Reflexe de nommer ce qu'un releve automatique peut et ne peut pas voir, avant de le citer comme preuve | sofia-sm (arbitrage) | accepte, `squad/README.md` section 9 amende | 2026-08-15 |
| Quatorze worktrees et branches de sprint sur le disque | sofia-sm (casquette dev) | worktrees supprimes apres verification de lignee | 2026-08-15 |

**Encore ouverts** :

| Description | Agent route | Depuis | Ce qui manque |
|---|---|---|---|
| `confirmation-archunit.md` absent pour la quatrieme fois d'affilee (s09 a s12), alors qu'`ADR-0013` en fait le lieu de la discipline de comptage de `theo-archi` | theo-archi, sofia-sm (suivi) | s09 | le fichier lui-meme ; le contenu qu'il devrait porter est reconstruit dans `review.md`, mais le fichier que l'ADR nomme reste absent |
| Deux reserves de `nadia-prof` sur les tables (un enonce sur cinq revient plutot qu'un sur sept ; deux distracteurs hors de l'espace des tables) | camille-po | 2026-08-15 | transformation en stories, `camille-po` les nomme deja hors perimetre de ce sprint |
| Reserve O de `nadia-prof`, seconde moitie ("aucun chemin vers le catalogue ne preserve la partie en cours") | camille-po | anterieur a s11 | arbitrage produit |
| Story S12-5 (`aria-label="Menu principal"` refuse, a remplacer par "Les jeux" ou rien) | camille-po (priorite), sofia-sm (planning) | 2026-08-15 | affectation a un sprint |
| Reserve F11 de `nadia-prof` (emplacement accessible de l'enseigne, aucune ecoute reelle au lecteur d'ecran) | nadia-prof, ines-design | anterieur a s10 | une ecoute reelle |
| Libelle "x10" (lettre) contre "×10" (signe multiplie), duplique a six endroits du depot | relecteur-pedagogique, camille-po | 2026-08-15 | arbitrage sur lequel des deux signes est correct, laisse ouvert a dessein par la correction de ce sprint |
| Obstacle de methode rapporte : le `relecteur-pedagogique` aurait d'abord valide sur un JDK Temurin 25 en croyant le JDK 21 absent, puis rejoue et corrige lui-meme son rapport | relecteur-pedagogique | 2026-08-15 | aucune trace sur le depot ; rapporte, non etabli par moi |
| Deux paquets (`cp-soustractions`, `cp-problemes-additifs`) exactement a 50,0 % sur le garde-fou de rang, un seul item d'ecart | nadia-prof (deja values comme justes), yanis-qa (nomme la fragilite) | 2026-08-15 | rien de bloquant - fait a surveiller, pas une action |

## Couverture programme

`squad/programme/MATRICE-couverture.md`, section "Synthese apres les seconds verdicts du sprint 12"
(2026-08-15) : **8 lignes `couvert-valide`, 3 `en cours`, 35 `non couvert`, sur 46** - contre 5 / 3
/ 38 a la cloture de s10 (dernier etat stable avant s12). Deux mouvements en sens opposes puis un
retour : mathematiques CM1 gagne deux lignes (perimetre, conversions) puis une troisieme sous
reserve (tables) ; mathematiques CP perd sa ligne d'additions en cours de sprint puis la retrouve
le jour meme. Le nombre de lignes `non couvert` ne bouge pas.

## Consommation de tokens

**Aucun `RAPPORT-tokens.md` n'existe pour ce sprint, et ce n'est pas un chiffre a zero : c'est une
mesure impossible a produire, faute d'un `planning.md`** - meme situation qu'aux sprints 2, 4 et
10.

```
python outils/rapport-tokens.py --sprint s12
```

rend :

```
  ignore s02-identite-visuelle : pas d'horodatage de debut dans planning.md
  ignore s04-ouvrir-le-francais : pas d'horodatage de debut dans planning.md
  ignore s10-la-forme-du-bandit-manchot : pas d'horodatage de debut dans planning.md
  ignore s12-mathematiques-cm1 : pas d'horodatage de debut dans planning.md
Sprint inconnu : s12
```

Le script construit la fenetre temporelle d'un sprint exclusivement a partir de l'horodatage de
debut lu dans `planning.md` ; ce sprint n'en a pas (`retro.md`, point 1). Je n'ai pas fabrique de
`planning.md` pour satisfaire l'outil - meme raisonnement que pour les sprints 2, 4 et 10, qui
portent la meme absence dans leurs propres `RAPPORT.md`. `squad/rapports/JOURNAL-tokens.md` n'est
donc pas mis a jour pour ce sprint non plus.
